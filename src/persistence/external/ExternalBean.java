/*
 * Created on 27-oct-2005
 */
package persistence.external;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ejb.SessionBean;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import persistence.activity.tables.CheckList;
import persistence.activity.tables.Item;
import persistence.activity.tables.Point;
import persistence.hibernate.HibernateUtil;
import persistence.util.ExceptionHandler;
import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverData;
import communication.external.instructors.ActivityRestData;
import communication.external.instructors.ChecklistObservationData;
import communication.external.instructors.ChecklistRestData;
import communication.external.instructors.ChecklistResultRestData;
import communication.external.instructors.CorporationRestData;
import communication.external.instructors.DriverRestData;
import communication.external.instructors.ItemRestData;
import communication.external.instructors.PointRestData;
import communication.language.Text;

/**
 * @ejb.bean name="External" display-name="External Service"
 *      description="Description for ExternalBean" jndi-name="ejb/External"
 *      type="Stateless"
 *  
 */
public abstract class ExternalBean implements SessionBean {
	
	public static String NOT_APPROVED = "generic.activity.note.notapruved";
	public static String APPROVED  ="generic.activity.note.apruved";
	public static String APPOVED_WITH_OBS = "generic.activity.note.apruvedwithobservations";

	
	public static Integer TEORICO_ASISTENCE_BTW1 = 8201;
	public static Integer TEORICO_ASISTENCE_BTW2 = 9001;
	public static Integer PRACTICO_ASISTENCE_BTW1 =8202;
	public static Integer PRACTICO_ASISTENCE_BTW2 = 9002;
	public static Integer NOVATO_BTW1 = 8301;
	
    private ExceptionHandler handler = new ExceptionHandler(ExternalBean.class);
    
    /**
     * Init transaction
     * @ejb.create-method
     * @ejb.permission role-name = "systemaccess"
     */
    public void ejbCreate() throws javax.ejb.CreateException { }

 
    /**
	 * @ejb.permission role-name ="systemaccess"
	 * @ejb.interface-method
	 */
	public ArrayList<DriverData> test() throws Exception {
	
		try {
			Query query = HibernateUtil.currentSession().createSQLQuery("select id, firstName, lastName from drivers as d where d.exclusionDate is null and d.corporation not in(14, 39) ");
		
			ArrayList<DriverData> res=new ArrayList<DriverData>();
			List<Object[]> list=query.list();
			for(Object[] d : list){
				try{
					DriverData data=new DriverData();
					data.setId((Integer)d[0]);
					data.setFirstName((String)d[1]);
					data.setLastName((String)d[2]);
					res.add(data);	
				}catch(Throwable th){
					
				}
				
			}
			
			return res;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		return new ArrayList<DriverData>();
	}
	
	  /**
		 * @ejb.permission role-name ="systemaccess"
		 * @ejb.interface-method
		 */
		public ArrayList<CorporationRestData> sync(Integer lastId, String activitiesIds, Date  lastUpdateDate, Text messages,UserSessionData userRequest) throws Exception {
		
			try {
				if(lastUpdateDate==null){
					Calendar cal=Calendar.getInstance();
					cal.add(Calendar.YEAR,-100);
					lastUpdateDate=cal.getTime();
				}
				
				
				Query queryCorp=HibernateUtil.currentSession().createSQLQuery("select c.id, c.name  from corporations as c inner join usercorporations as uc on(uc.id=c.id and uc.loginname='"+userRequest.getFilter().getLoginName()+"' ) ");
				List<Object[]> listCorpo=queryCorp.list();
				HashMap<Integer, CorporationRestData> valuesCorp=new HashMap<Integer, CorporationRestData>();
				for(Object[] row :listCorpo){
					CorporationRestData corp=new CorporationRestData();
					corp.setId((Integer)row[0]);
					corp.setName((String)row[1]);
					corp.setNewDrivers(new ArrayList<DriverRestData>());
					corp.setUpdateDrivers(new ArrayList<DriverRestData>());
					corp.setActivities(new ArrayList<ActivityRestData>());
					
					
					String sql= " select ca.type,cf.checklist, ch.version, ai.activityId , d1.resourcekey as d1n, d2.resourcekey as d2n, d3.resourcekey as d3n,  count.resourcekey as cn, date as date"+  
							" from cepaactivityinstructor as ai  "+
							" inner join cepaactivity as ca on (ca.activityId=ai.activityId and ca.state='datatype.scheduledactivitystate.prevista') "+ 
							" inner join cepaactivitydefinition as cf on (ca.type= cf.resourcekeyname) "+
							" inner join checklist as ch on(ch.id=cf.checklist) "+
							" left outer join divorgitemlevel1s as d1 on (d1.id=ca.divOrg1)"+
							" left outer join divorgitemlevel1s as d2 on (d2.id=ca.divOrg2)"+
							" left outer join divorgitemlevel1s as d3 on (d3.id=ca.divOrg3)"+
							" left outer join country as count on (count.id=ca.country)"+
							" Where ai.addedresults=false and ai.loginname='"+userRequest.getFilter().getLoginName()+"' and ca.corporation="+corp.getId()+" and ai.activityId not in("+activitiesIds.replaceAll("\"","")+")";
					
					Query queryActivities=HibernateUtil.currentSession().createSQLQuery(sql);
					List<Object[]> activities=queryActivities.list();
					for(Object[] row2:activities){
						String key=(String)row2[0];
						if(messages.getText(key).toLowerCase().contains("tablet")){
							ActivityRestData activity=new ActivityRestData();
							activity.setName(messages.getText(key));
							activity.setChecklist((Integer)row2[1]);
							activity.setVersion((Integer)row2[2]);
							activity.setId((Integer)row2[3]);
							activity.setParticipants(new ArrayList<DriverRestData>());
							activity.setCorporationId(corp.getId());
							activity.setDivOrg1Name((String)row2[4]);
							activity.setDivOrg2Name((String)row2[5]);
							activity.setDivOrg3Name((String)row2[6]);
							activity.setCountryName((String)row2[7]);
							Date date=(Date)row2[8];
							SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyy");
							activity.setDate(format.format(date));
							
							if(activity.getCountryName()!=null){
								activity.setCountryName(messages.getText(activity.getCountryName()));
							}
							
							// Observaciones generales de la actividad
							String queryStr =  " SELECT idobservation, g.text, o. obsorder "+
										" FROM cepaactivityregistryobservations o, generalmessages g "+
										" WHERE resourcekeyname = '"+ key +"' and g.labelkey = o.text  "+
										" and g.language = '"+ userRequest.getLenguage() +"'   ";
							Query query = HibernateUtil.currentSession().createSQLQuery(queryStr).
									addScalar("idobservation", Hibernate.INTEGER).
									addScalar("text",Hibernate.STRING).
									addScalar("obsorder", Hibernate.INTEGER);
							
							List list = query.list();					
							if (list != null && list.size() > 0){
									Iterator it = list.iterator();
									
									while (it.hasNext()){
										Object[] row3 = (Object[]) it.next();
										String id = (row3[0] != null) ? ((Integer)row3[0]).toString() : null;
										String text = (String) row3[1];
										Integer obsorder = (Integer)row3[2];							
										ChecklistObservationData obs = new ChecklistObservationData();
										obs.setId(Integer.valueOf(id));
										obs.setLabel(text);
									    obs.setOrder(obsorder); 
									    activity.getObservations().add(obs);
									}
							}
							
							
							String sqlP="select d.id, d.firstName, d.lastName, d.corporationId, d.corporation  from activityregistry as ar inner join drivers as d on(d.id=ar.driver) where ar.activity=(:activity) and ar.hasresult=false";
							Query queryP=HibernateUtil.currentSession().createSQLQuery(sqlP);
							queryP.setParameter("activity", activity.getId());
							List<Object[]> listP=queryP.list();
							for(Object[] d:listP){
								DriverRestData data=new DriverRestData();
								data.setId((Integer)d[0]);
								data.setFirstName((String)d[1]);
								data.setLastName((String)d[2]);
								data.setCorporationid((String)d[3]);
								data.setCorporation((Integer)d[4]);
								
								activity.getParticipants().add(data);
							}
							
							corp.getActivities().add(activity);
							valuesCorp.put(corp.getId(), corp);
							
						}
					}
					
				   	
					Query query = HibernateUtil.currentSession().createSQLQuery("select d.id, d.firstName, d.lastName, d.corporationId, d.corporation  from drivers as d where d.corporation="+corp.getId()+" and d.exclusionDate is null and  d.id> "+lastId);
					
					
					List<Object[]> list=query.list();
					for(Object[] d : list){
						DriverRestData data=new DriverRestData();
						data.setId((Integer)d[0]);
						data.setFirstName((String)d[1]);
						data.setLastName((String)d[2]);
						data.setCorporationid((String)d[3]);
						data.setCorporation((Integer)d[4]);
						corp.getUpdateDrivers().add(data);
					}
				
				}
				
				return new ArrayList<CorporationRestData>(valuesCorp.values());
				
				
			} catch (Exception e) {
					e.printStackTrace();
			}
			 
			return new ArrayList<CorporationRestData>();
		}
		
		
	 /**
	 * @ejb.permission role-name ="systemaccess"
	 * @ejb.interface-method
	 */
	public ChecklistRestData getChecklist(Integer id, Text messages, UserSessionData userRequest){
			ChecklistRestData res=new ChecklistRestData();
			try{
				
				CheckList checklist=(CheckList)HibernateUtil.currentSession().get(CheckList.class,id);
				res.setVersion(checklist.getVersion());
				res.setPoints(new ArrayList<PointRestData>());
				res.setId(checklist.getId());
				ArrayList<Point> auxPoints=new ArrayList<Point>(checklist.getPoints());
				Collections.sort(auxPoints,new Comparator<Point>(){

					@Override
					public int compare(Point o1, Point o2) {
						// TODO Auto-generated method stub
						Integer o1i=o1.getPointOrder();
						Integer o2i=o2.getPointOrder();
						return o1i.compareTo(o2i);
					}
					
				});
				for(Point p:auxPoints){
					PointRestData pointRestData=new PointRestData();
					pointRestData.setId(p.getId());
					pointRestData.setOrder(p.getPointOrder());
					pointRestData.setName(messages.getText( p.getText()));
					pointRestData.setItems(new ArrayList<ItemRestData>());
					
					String queryStr =  " SELECT po.idobservation, g.text, po.obsorder, po.point "+
							" FROM pointobservations po, point p, generalmessages g  "+
							" WHERE p.id = po.point and p.checklist = "+ id + " and po.point="+p.getId()+
							" and g.labelkey = po.text and g.language = '" + userRequest.getLenguage() +"'   " +
							" ORDER BY p.pointorder, po.obsorder  ";
					Query query = HibernateUtil.currentSession().createSQLQuery(queryStr).
						addScalar("idobservation", Hibernate.INTEGER).
						addScalar("text",Hibernate.STRING).
						addScalar("obsorder", Hibernate.INTEGER).
						addScalar("point", Hibernate.INTEGER);
				
					Iterator<Object[]> iterObs=query.list().iterator();
					while(iterObs.hasNext()){
						Object[] row=iterObs.next();
						ChecklistObservationData item=new ChecklistObservationData();
						item.setId((Integer)row[0]);
						item.setOrder((Integer)row[2]);
						item.setLabel((String)row[1]);
						pointRestData.getObservations().add(item);
					}
					
					
					ArrayList<Item> auxItem=new ArrayList<Item>( p.getItems());
					Collections.sort(auxItem,new Comparator<Item>(){

						@Override
						public int compare(Item o1, Item o2) {
							// TODO Auto-generated method stub
							Integer o1i=o1.getItemOrder();
							Integer o2i=o2.getItemOrder();
							return o1i.compareTo(o2i);
						}
						
					});
					
					for(Item i:auxItem){
						ItemRestData itemRestData=new ItemRestData();
						itemRestData.setName(messages.getText( i.getText()));
						itemRestData.setId(i.getId());
						itemRestData.setIdRadio(i.getIdRadio());
						itemRestData.setDisabledItems(i.getDisabledItems());
						itemRestData.setType(i.getType());
						itemRestData.setOrder(i.getItemOrder());
						
						pointRestData.getItems().add(itemRestData);
					}
					res.getPoints().add(pointRestData);
				}	
				
			
				
			}catch(Throwable th){
				
			}
			
			return res;
		}
		
	
	/**
	 * @ejb.permission role-name ="systemaccess"
	 * @ejb.interface-method
	 */
	public boolean saveResults(ArrayList<ChecklistResultRestData> results,Text messages,UserSessionData userRequest) throws Exception {
		
		Session session=HibernateUtil.currentSession();
		
		for(ChecklistResultRestData res:results){
			Integer idDriver = null;
			Integer idActividad = res.getActivityId();
			Integer divorg1 = null;
			Integer divorg2 = null;
			Integer divorg3 = null;
			Integer geolevel1 = null;
			Integer country = null;
			
			if (res.isTemp()){
				
				// TODO: El driver temporal nuevo hereda de la actividad sus DivOrgs y  GeoLevel1
				String queryStr = "SELECT divorg1, divorg2, divorg3, geolevel1, country FROM cepaactivity  "+
						" WHERE activityid = " + idActividad;
				Query query = session.createSQLQuery(queryStr).
								addScalar("divorg1",Hibernate.INTEGER).
								addScalar("divorg2",Hibernate.INTEGER).
								addScalar("divorg3",Hibernate.INTEGER).
								addScalar("geolevel1",Hibernate.INTEGER).
								addScalar("country",Hibernate.INTEGER);
				Object[] cepaactivity = (Object[]) query.uniqueResult();
				divorg1 = (Integer) cepaactivity[0];
				divorg2 = (Integer) cepaactivity[1];
				divorg3 = (Integer) cepaactivity[2];
				geolevel1 = (Integer) cepaactivity[3];
				country=(Integer) cepaactivity[4];
				
								
						
				// Creo el conductor temporal, y obtengo el idDriver
				queryStr = "SELECT nextval('driver_sequence') as id ";
				query = session.createSQLQuery(queryStr).addScalar("id", Hibernate.INTEGER);
				idDriver = (Integer) query.uniqueResult();		
				String createdfromtablet = "true";
				
			 	queryStr = 
					" INSERT INTO drivers (corporation,id,firstname,lastname,birthdate,sex,country," +
					" email,corporationid" +
					",temporal,drives,employee," +
					" licensenotexpiry, divorg1, divorg2, divorg3, geolevel1, createdfromtablet ) values ( " +
					res.getCorpId() + ","+ idDriver +" , '"+
					res.getFirstName().toUpperCase()+ "','" +
					res.getLastName().toUpperCase()+"','" +
					"1900-01-01','"+
					res.sexoDT()+ "', " +
					country +",'" +
					res.getEmail() + "' , '" +
					res.getCorporationId()+ "', " +
					" true, true,true,false,"+divorg1+","+divorg2+","+divorg3+","+geolevel1+","
					+ createdfromtablet + " ); ";
				
				query = session.createSQLQuery(queryStr);
				int inserted = query.executeUpdate();
				
			}else{
				idDriver = res.getDriverId();
			}
			
			String instructor = "";
			try {
				// Nombre Instructor
				String queryStr = " SELECT firstname, lastname FROM users WHERE loginname = '" + userRequest.getFilter().getLoginName() +"' ;" ;
				Query query = session.createSQLQuery(queryStr).
					addScalar("firstname",Hibernate.STRING).
					addScalar("lastname",Hibernate.STRING);
				Object [] user = (Object[]) query.uniqueResult();
				instructor = (String)user[0] + " " + (String)user[1];	
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			int inserted = 0;

			String cepaCourse = "true"; // TODO, por ahora son todos cursos de cepa
			
			String vehicleType = "";
					
			String novato = "false"; // leer de la lista de items.... OK				
			String teoricoAsistence = "true";  // leer de la lista de items.... OK				
			String practicoAsistence = "true";  // leer de la lista de items.... OK
			
			String hasResult = "true";
			
			String result = "";
			Integer idActivityRegistry = null;

			//Verifico si el conductor no está ya agregado a la actividad
			String queryStr = "SELECT activityregistryid as id FROM activityregistry WHERE driver = " + idDriver + " AND activity = " + idActividad;
			Query query = session.createSQLQuery(queryStr).addScalar("id", Hibernate.INTEGER);
			List list = query.list();

			
			if (res.getResult().equalsIgnoreCase("approved") || res.getResult().equalsIgnoreCase("A")) 
				result = APPROVED;
			else if (res.getResult().equalsIgnoreCase("notapproved")) 
				result =NOT_APPROVED;
			else if (res.getResult().equalsIgnoreCase("approvedwithobservations"))
				result = APPOVED_WITH_OBS;
			else 
				result = res.getResult().trim();
		
			if(list.size() > 0) {
				//Limpio la actividad para volverla a cargar
				idActivityRegistry = (Integer)list.iterator().next();
				queryStr  = "delete from activityregistryitems where activityregistry="+idActivityRegistry;
				query = session.createSQLQuery(queryStr);
				inserted = query.executeUpdate();

				queryStr  = "delete from activityregistryitems where activityregistry="+idActivityRegistry;
				query = session.createSQLQuery(queryStr);
				inserted = query.executeUpdate();
				
				queryStr  = "delete from activityregistrypointsobservations where activityregistry="+idActivityRegistry;
				query = session.createSQLQuery(queryStr);
				inserted = query.executeUpdate();
				
				queryStr  = "delete from activityregistryobservations where activityregistry="+idActivityRegistry;
				query = session.createSQLQuery(queryStr);
				inserted = query.executeUpdate();
				
				
			}else {
				
				
				// Agrego el activityRegistry para ese driver			
				queryStr = "SELECT nextval('activityregistry_sequence') as id ";
				query = session.createSQLQuery(queryStr).addScalar("id", Hibernate.INTEGER);
				idActivityRegistry = (Integer)query.uniqueResult();
				
				
				queryStr  = 
					" INSERT INTO activityregistry (activityregistryid, driver, activity, note, instructor," +
					" cepacourse, vehicletype, novato, teoricoasistence,	practicoasistence, "+ 
					" hasresult, phrd, circulationareahighway, circulationarearoad, circulationareasuburban, " +
					" circulationareaurban) values ( " + idActivityRegistry + ", " + idDriver + ", " + idActividad + ", '" + result + 
					"', '" + instructor + "', "+
						cepaCourse + ", '" + vehicleType + "' , " + novato + ", "+teoricoAsistence + " , " +
						practicoAsistence + "," + hasResult +", false, false,false,false,false); ";
				query = session.createSQLQuery(queryStr);
				inserted = query.executeUpdate();
			}
			
			queryStr  = 
					" INSERT INTO activityregistrylog (registry, userid, date, action, comment, driver, activity, note, instructor)" +
					" values ( " + idActivityRegistry + ", '"+userRequest.getFilter().getLoginName()+"', NOW(), 0,'Inserted by tablet', " + idDriver + ", " + idActividad + ", '" + result + "'," +
							" '" + instructor + "'); ";
				query = session.createSQLQuery(queryStr);
				inserted = query.executeUpdate();

			// 3 Guardo los items en activityregistryitems
			// Para cada punto (itero sobre puntos), para cada item (itero sobre items) -> Insert item registry
			// 4. Agrego las observaciones de los puntos seleccionados.
			// Para cada punto, itero sobre puntos, para cada observacion itero sobre observacion ->
			// insert observacion del punto.
				
			Iterator<PointRestData> it = res.getPoints().iterator();
			while (it.hasNext()){
				PointRestData point = it.next();
				 ArrayList<ItemRestData> items = point.getItems();
				
				if (items != null && items.size() > 0){
					Iterator<ItemRestData> it2 = items.iterator();
					while (it2.hasNext()){
						ItemRestData item=it2.next();
						
						queryStr = "insert into activityregistryitems (activityregistry, item) values (" +
								idActivityRegistry + "," + item.getId() + " ); ";
						query = session.createSQLQuery(queryStr);
						inserted = query.executeUpdate();
							
							
							// Items especiales:
							// teoricoAsistence
							// BTW1 : 8201    BTW2: 9001
							if (item.equals(TEORICO_ASISTENCE_BTW1) || item.equals(TEORICO_ASISTENCE_BTW2) ){
								teoricoAsistence = "false";
							}
							
							// practicoAsistence
							// BTW1 : 8202    BTW2: 9002
							if (item.equals(PRACTICO_ASISTENCE_BTW1) || item.equals(PRACTICO_ASISTENCE_BTW2) ){
								practicoAsistence = "false";
							}
							
							// novato
							// BTW1: 8301
							if (item.equals(NOVATO_BTW1)){
								novato = "true";
							}
							
							// vehicleType
							
						}
					}
				
				
					ArrayList<ChecklistObservationData> observaciones = point.getObservations();
					if (observaciones != null && observaciones.size() > 0){
						Iterator<ChecklistObservationData> it2 = observaciones.iterator();
						while (it2.hasNext()){
							ChecklistObservationData obs = (ChecklistObservationData)it2.next();								
							queryStr = "insert into activityregistrypointsobservations (activityregistry, idpoint, idobs) values (" +
								idActivityRegistry + "," + point.getId() +","+ obs.getId() + " ); ";
							query = session.createSQLQuery(queryStr);
							inserted = query.executeUpdate();
							
						}
					}		
			}
			
			
			// Actualizo teoricoAsistence, practicoAsistence, novato y vehicleType
			queryStr = "UPDATE activityregistry SET novato = " + novato + " , teoricoasistence = " + teoricoAsistence +
					",	practicoasistence = " +practicoAsistence + " where activityregistryid = " + idActivityRegistry + " ;";
			query = session.createSQLQuery(queryStr);
			inserted = query.executeUpdate();
			
			
			
			// 5. Agrego las observaciones generales para este registro/driver
			ArrayList<ChecklistObservationData> observaciones = res.getObservations();
			if (observaciones != null && observaciones.size() > 0){
				Iterator<ChecklistObservationData> it2 = observaciones.iterator();
				while (it2.hasNext()){
					ChecklistObservationData obs = (ChecklistObservationData)it2.next();
					queryStr = "insert into activityregistryobservations (activityregistry, idobs) values (" +
							idActivityRegistry + "," + obs.getId() + " ); ";
					query = session.createSQLQuery(queryStr);
					inserted = query.executeUpdate();
					
				}
			}	
		
		
		}
		
		return true;
	}
}
