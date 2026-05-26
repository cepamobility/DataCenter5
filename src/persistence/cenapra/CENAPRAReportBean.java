package persistence.cenapra;

import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;

import persistence.cenapra.tables.CENAPRAActivity;
import persistence.cenapra.tables.CENAPRAActivityRegistry;
import persistence.cenapra.tables.CENAPRAParticipant;
import persistence.hibernate.HibernateUtil;
import persistence.util.ExceptionHandler;
import persistence.util.Util;
import persistence.util.UtilityQuery;
import business.util.MemoryLog;

import communication.activity.tables.ActivityListData;
import communication.activity.tables.CEPAActivityConstants;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.cenapra.CENAPRAParticipantListData;
import communication.cenapra.CENAPRAParticipantListFullData;
import communication.cenapra.tables.ActivityData;
import communication.cenapra.tables.ParticipantData;
import communication.cenapra.tables.ParticipantMainAttributes;
import communication.cenapra.tables.ParticipantRegistryData;
import communication.cenapra.tables.ReportActivityData;
import communication.exception.DeslogedException;
import communication.exception.InvalidDataException;
import communication.language.Text;
import communication.util.ListResult;

/**
 * @ejb.bean name="CENAPRAReport" display-name="Name for CENAPRAReport"
 *          description="Description for CENAPRAReport"
 *          jndi-name="ejb/CENAPRAReport" type="Stateless"
 *  
 */
public abstract class CENAPRAReportBean implements SessionBean {

    private ExceptionHandler handler;
	
    /**
     * @ejb.create-method
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     * @throws CreateException
     */
    public void ejbCreate() throws CreateException {}
    
	/**
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
	 * @ejb.interface-method
	 */
	public ParticipantData findParticipantByPrimaryKey(Integer id, UserSessionData userSessionData) throws Exception {
        MemoryLog.getInstance().log(MemoryLog.START,"findParticipantByPrimaryKey",getClass());
		if (id == null) {
			throw new InvalidDataException("findParticipantByPrimaryKey","id = null");
		}
		if (userSessionData == null) {
			throw new DeslogedException(	"findParticipantByPrimaryKey","session is null");
		}
		try{			
		    Query query = HibernateUtil.currentSession().createQuery("SELECT participant FROM CENAPRAParticipant AS participant "+
                    UtilityQuery.corporationDivisionsRestictions("participant",userSessionData)+" AND id = :id ");
		    query.setParameter("id", id);
            Object result = query.uniqueResult();
            if(result != null) {
                return ((CENAPRAParticipant)result).getData();
            }
        }  catch(Exception e){
			Object[] parameters = {id};
            handler.getException(e,"findParticipantByPrimaryKey",userSessionData,parameters);
        }
        MemoryLog.getInstance().log(MemoryLog.END,"findParticipantByPrimaryKey",getClass());
		return null;
	}
	
	/**
	 * @throws Exception
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
	 */
	public ListResult findList(Hashtable parameters,int first,int count, UserSessionData userSessionData) throws Exception {
        MemoryLog.getInstance().log(MemoryLog.START,"findList",getClass());
	    try{	
	    	int corporation = userSessionData.getFilter().getCorporationId().intValue();
	        Session session = HibernateUtil.currentSession();
	        Integer total = 0;

	        String sql = "SELECT COUNT(*) AS c FROM cenapraparticipants WHERE corporation = "+String.valueOf(corporation);
	        
	        if(parameters.containsKey("activityType") && !Util.empty((String)parameters.get("activityType")) && !parameters.get("activityType").equals(ParticipantData.ALL)) {
	        	if(parameters.get("activityType").equals(ParticipantData.WITH_ACTIVITY)) {
	        		sql += " AND activity ";
	        	}else {
	        		sql += " AND NOT activity ";
	        	}
	        }
	        if(parameters.containsKey("firstName") && !Util.empty((String)parameters.get("firstName"))) {
	        	sql += " AND lower(firstname) LIKE '%"+((String)parameters.get("firstName")).toLowerCase()+"%' "; 
	        }
	        if(parameters.containsKey("lastName") && !Util.empty((String)parameters.get("lastName"))) {
	        	sql += " AND lower(lastname) LIKE '%"+((String)parameters.get("lastName")).toLowerCase()+"%' "; 
	        }
	    	Query query = session.createSQLQuery(sql).addScalar("c", Hibernate.INTEGER);
	    	total = new Integer(((Integer) query.list().iterator().next()).intValue());
	        
            Collection<ParticipantMainAttributes> result = new LinkedList<ParticipantMainAttributes>();
	    	if(total > 0) {
		        sql = "SELECT participant.id,participant.firstname,participant.lastname,participant.birthdate,d1.resourcekey,";
		        sql += (corporation == CorporationData.CENAPRA) ? "participant.city " : "d2.resourcekey AS city,d3.resourcekey AS locality "; 
	            sql += " FROM cenapraparticipants AS participant " +
	    		" JOIN divorgitemlevel1s d1 ON participant.divorg1 = d1.id ";
	    		if(corporation != CorporationData.CENAPRA) {
	    			sql += " JOIN divorgitemlevel2s d2 ON d1.divorgitemlevel2 = d2.id ";
	    			sql += " JOIN divorgitemlevel1s d3 ON participant.divorg2 = d3.id  ";
	    		}
	    		sql += " WHERE corporation = "+String.valueOf(corporation);
	            if(parameters.containsKey("firstName") && !Util.empty((String)parameters.get("firstName"))) {
	            	sql += " AND lower(firstname) LIKE '%"+((String)parameters.get("firstName")).toLowerCase()+"%' "; 
	            }
	            if(parameters.containsKey("lastName") && !Util.empty((String)parameters.get("lastName"))) {
	            	sql += " AND lower(lastname) LIKE '%"+((String)parameters.get("lastName")).toLowerCase()+"%' "; 
	            }
	            if(parameters.containsKey("activityType") && !Util.empty((String)parameters.get("activityType")) && !parameters.get("activityType").equals(ParticipantData.ALL)) {
		        	if(parameters.get("activityType").equals(ParticipantData.WITH_ACTIVITY)) {
	            		sql += " AND participant.activity ";
	            	} else {
	            		sql += " AND NOT participant.activity ";
	            	}
	            }
	            sql += " ORDER BY lastname";
	            
	            SQLQuery sqlquery = session.createSQLQuery(sql).addScalar("id",Hibernate.INTEGER).addScalar("firstname",Hibernate.STRING).addScalar("lastname",Hibernate.STRING).addScalar("birthdate",Hibernate.DATE).addScalar("resourcekey",Hibernate.STRING).addScalar("city",Hibernate.STRING);
	            if(corporation != CorporationData.CENAPRA) {
	            	sqlquery.addScalar("locality", Hibernate.STRING);
	            }
	            if(count != -1) {
		            if(total.intValue() < first) {
	                    first = (total.intValue() % count == 0) ? total.intValue() - count : total.intValue() - (total.intValue() % count);
			        }
			    }
	            sqlquery.setFirstResult(first);
	            if(count != -1) {
	            	sqlquery.setMaxResults(count);
	            }
	
	            Collection list = sqlquery.list();
	            if(list != null) {
	            	Iterator iter = list.iterator();
	            	while (iter.hasNext()) {
	            		result.add(new ParticipantMainAttributes((Object[])iter.next()));
	            	}
	            }
	    	}
	    	
            MemoryLog.getInstance().log(MemoryLog.END,"findList",getClass());
            return new ListResult(result, total.intValue());
	    }catch (Exception e) {
			Object[] parame = {parameters,first,count};
            handler.getException(e,"findList",userSessionData,parame);
        }
        return null;
	}

    /**
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     * @ejb.interface-method
     */
    public Collection findParticipants(String list,UserSessionData userSessionData) throws Exception {
        Collection elements = new LinkedList();
        try{    

            Session session = HibernateUtil.currentSession();
            String queryStr = "SELECT p.firstName,p.lastName,p.birthDate "; 
            queryStr += " FROM CENAPRAParticipant AS p ";
            queryStr += UtilityQuery.corporationDivisionsRestictions("p",userSessionData);
            queryStr += " AND p.id IN ("+list+")";
            Query query = session.createQuery(queryStr);
            return query.list();
        }catch(Exception e){
			Object[] parameters = {list};
            handler.getException(e,"findParticipants",userSessionData,parameters);
        }
        return elements;
    }

    /**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     */
    public Object[][] getParticipantHistory(Integer participant, UserSessionData userSessionData) throws Exception {
        try {
            Session session = HibernateUtil.currentSession();
            String sql = "SELECT car.id,ca.date,ca.type FROM cenapraactivityregistry car JOIN cenapraactivity ca ON car.activity = ca.id WHERE ca.state = '"+CEPAActivityConstants.DICTADA+"' AND participant = "+String.valueOf(participant);
            Query q = session.createSQLQuery(sql).addScalar("id",Hibernate.INTEGER).addScalar("date",Hibernate.DATE).addScalar("type",Hibernate.STRING);
            List l = q.list();
            Object[][] result = new Object[l.size()][3];
            Iterator it = l.iterator();
            int i = 0;
            while(it.hasNext()) {
                result[i] = (Object[])it.next();
                i++;
            }
            return result;
        }catch (Exception e) {
			Object[] parameters = {participant};
            handler.getException(e,"getParticipantHistory",userSessionData,parameters);
        }
        return new Object[0][0];
    }
    
    /**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     */
    public ParticipantRegistryData getParticipantRegistry(Integer registry, UserSessionData userSessionData) throws Exception {
        try {
            Session session = HibernateUtil.currentSession();
            Query q = session.createQuery("SELECT registry FROM CENAPRAActivityRegistry registry WHERE id = "+String.valueOf(registry));
            List list = q.list();
            if(list.size() > 0) {
                CENAPRAActivityRegistry registryObj = (CENAPRAActivityRegistry)list.iterator().next();
                return registryObj.getData(userSessionData);
            }
        }catch (Exception e) {
			Object[] parameters = {registry};
            handler.getException(e,"getParticipantRegistry",userSessionData,parameters);
        }
        return null;
    }

    /**
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     * @ejb.interface-method
     */
    public ListResult findActivityList(String startDay,String startMonth,String startYear,String endDay,String endMonth,String endYear,
            String type,String country,String city,String state,String folio,int first,int count,UserSessionData userSessionData) throws Exception {
        ListResult listResult=null;
        try{    
            Session session = HibernateUtil.currentSession();
            String queryCountStr = "SELECT COUNT(*) AS count FROM cenapraactivity AS activity "+UtilityQuery.corporationDivisionsRestictionsSQL("activity",userSessionData);
            if(Util.isDate(startDay,startMonth,startYear)) {
                queryCountStr += " AND activity.date >= '"+startYear+"-"+startMonth+"-"+startDay+"'";
            }
            if(Util.isDate(endDay,endMonth,endYear)) {
                queryCountStr += " AND date(activity.date) <= '"+endYear+"-"+endMonth+"-"+endDay+"'";
            }
            if((type != null) && (!type.equals(""))) {
                queryCountStr += " AND activity.type = '"+type+"' ";
            }
            if((country != null) && (!country.equals(""))) {
                queryCountStr += " AND activity.country = "+country;
            }
            if(!Util.empty(city)) {
                queryCountStr +=" AND lower(activity.city) = '"+city.toLowerCase()+"'";
            }        
            if((state != null) && (!state.equals(""))) {
                queryCountStr += " AND activity.state = '"+state+"'";
            }
            if((folio != null) && (!folio.equals(""))) {
                queryCountStr += " AND activity.id IN (SELECT activity FROM cenapraactivityregistry WHERE number = "+folio+") ";
            }
            Query queryCount = session.createSQLQuery(queryCountStr).addScalar("count",Hibernate.INTEGER);
            Integer total = (Integer)queryCount.uniqueResult();
            
            String[] fields = userSessionData.getFilter().getFields("activity");
            String queryStr = "SELECT activity.id,activity.type,c.resourcekey,activity.date,activity.city,activity.state"+fields[0]; 
            queryStr += " FROM cenapraactivity AS activity,country c" +fields[1];
            queryStr += UtilityQuery.corporationDivisionsRestictionsSQL("activity",userSessionData);
            queryStr += " AND activity.country = c.id "+fields[2];
            if(Util.isDate(startDay,startMonth,startYear)) {
                queryStr += " AND activity.date >= '"+startYear+"-"+startMonth+"-"+startDay+"'";
            }
            if(Util.isDate(endDay,endMonth,endYear)) {
                queryStr += " AND date(activity.date) <= '"+endYear+"-"+endMonth+"-"+endDay+"'";
            }
            if((type != null) && (!type.equals(""))) {
                queryStr += " AND activity.type = '"+type+"' ";
            }
            if((country != null) && (!country.equals(""))) {
                queryStr += " AND activity.country = "+country;
            }
            if(!Util.empty(city)) {
                queryStr +=" AND lower(activity.city) = '"+city.toLowerCase()+"'";
            }        
            if((state != null) && (!state.equals(""))) {
                queryStr += " AND activity.state = '"+state+"'";
            }
            if((folio != null) && (!folio.equals(""))) {
                queryStr += " AND activity.id IN (SELECT activity FROM cenapraactivityregistry WHERE number = "+folio+") ";
            }
            queryStr += " ORDER BY date DESC";
            SQLQuery query = session.createSQLQuery(queryStr);
            query.addScalar("id",Hibernate.INTEGER);
            query.addScalar("type",Hibernate.STRING);
            query.addScalar("resourcekey",Hibernate.STRING);
            query.addScalar("date",Hibernate.DATE);
            query.addScalar("city",Hibernate.STRING);
            query.addScalar("state",Hibernate.STRING);
            StringTokenizer token = new StringTokenizer(fields[3],",");
            while(token.hasMoreTokens()) {
                query.addScalar(token.nextToken(), Hibernate.STRING);
            }

            if(count != -1) {
                if(total.intValue() < first) {
                    if(total.intValue() % count == 0) {
                        first = total.intValue() - count;
                    }else {
                        first = total.intValue() - (total.intValue() % count);
                    }
                }
                query.setFirstResult(first);
                query.setMaxResults(count);
            }
            
            Iterator it = query.list().iterator();
            Collection elements = new LinkedList();
            while(it.hasNext()) {
                elements.add(new ActivityListData((Object[])it.next()));
            }
            listResult = new ListResult(elements,total.intValue());
        }catch(Exception e){
			Object[] parameters = {startDay,startMonth,startYear,endDay,endMonth,endYear,type,country,city,state,folio,first,count};
            handler.getException(e,"findActivityList",userSessionData,parameters);
        }
        return listResult;
    }
    
    /**
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     * @ejb.interface-method
     */
    public ActivityData getActivity(Integer activityId, UserSessionData userSessionData) throws Exception {
        try {
            Session session = HibernateUtil.currentSession();
            CENAPRAActivity activity = (CENAPRAActivity)session.load(CENAPRAActivity.class,activityId);
            if(activity != null) {
                return activity.getData();
            }
        }catch(Exception e){
			Object[] parameters = {activityId};
            handler.getException(e,"getActivity",userSessionData,parameters);
        }
        return null;
    }

    /**
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     * @ejb.interface-method
     */
    public Collection getActivityParticipants(Integer activity, UserSessionData userSessionData) throws Exception {
        Collection participants = new LinkedList();
        try {
            Session session = HibernateUtil.currentSession();
            Query q = session.createQuery("SELECT registry FROM CENAPRAActivityRegistry registry WHERE registry.activity.id = "+String.valueOf(activity));
            Iterator list = q.list().iterator();
            while(list.hasNext()) {
                participants.add(((CENAPRAActivityRegistry)list.next()).getData(userSessionData));
            }
        }catch (Exception e) {
			Object[] parameters = {activity};
            handler.getException(e,"getActivityParticipants",userSessionData,parameters);
        }
        return participants;
    }

    /**
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     * @ejb.interface-method
     */
    public Collection findActivities(String list,UserSessionData userSessionData) throws Exception {
        Collection elements = new LinkedList();
        try{    

            Session session = HibernateUtil.currentSession();
            String[] fields = userSessionData.getFilter().getFields("activity");
            String queryStr = "SELECT activity.id,'' as type,c.resourcekey,activity.date,activity.city,activity.state"+fields[0]; 
            queryStr += " FROM cenapraactivity AS activity,country c" +fields[1];
            queryStr += UtilityQuery.corporationDivisionsRestictionsSQL("activity",userSessionData);
            queryStr += " AND activity.country = c.id "+fields[2];
            queryStr += " AND activity.id IN ("+list+")";
            queryStr += " ORDER BY date DESC";
            SQLQuery query = session.createSQLQuery(queryStr);
            query.addScalar("id",Hibernate.INTEGER);
            query.addScalar("type",Hibernate.STRING);
            query.addScalar("resourcekey",Hibernate.STRING);
            query.addScalar("date",Hibernate.DATE);
            query.addScalar("city",Hibernate.STRING);
            query.addScalar("state",Hibernate.STRING);
            StringTokenizer token = new StringTokenizer(fields[3],",");
            while(token.hasMoreTokens()) {
                query.addScalar(token.nextToken(), Hibernate.STRING);
            }

            Iterator it = query.list().iterator();
            while(it.hasNext()) {
                elements.add(new ActivityListData((Object[])it.next()));
            }
        }catch(Exception e){
			Object[] parameters = {list};
            handler.getException(e,"findActivities",userSessionData,parameters);
        }
        return elements;
    }
    
    /**
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     * @ejb.interface-method
     */
    public boolean hasActivities(Integer participantId, UserSessionData userSessionData) throws Exception {
        try {
            Session session = HibernateUtil.currentSession();
            Query q = session.createQuery("SELECT COUNT(*) FROM CENAPRAActivityRegistry registry WHERE registry.participant.id = "+String.valueOf(participantId));
            Long count = (Long)q.uniqueResult();
            return (count.intValue() > 0);
        }catch (Exception e) {
			Object[] parameters = {participantId};
            handler.getException(e,"hasActivities",userSessionData,parameters);
        }
        return false;
    }
    
    /**
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     * @ejb.interface-method
     */
    public Collection getRegistryItems(Integer registry, Integer check, UserSessionData userSessionData) throws Exception {
        Collection items = new LinkedList();
        try {
            Session session = HibernateUtil.currentSession();
            Query q = session.createSQLQuery("SELECT item FROM cenapraactivityregistryvalues WHERE registry = "+String.valueOf(registry)+" AND checklist = "+String.valueOf(check)).addScalar("item",Hibernate.INTEGER);
            items.addAll(q.list());
        }catch(Exception e) {
			Object[] parameters = {registry,check};
            handler.getException(e,"getRegistryItems",userSessionData,parameters);
        }
        return items;
    }
    
    /**
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     * @ejb.interface-method
     */
    public Hashtable getCorrectAnswers(UserSessionData userSessionData) throws Exception {
        Hashtable answers = new Hashtable();
        try {
            Session session = HibernateUtil.currentSession();
            Query q = session.createSQLQuery("SELECT checklist,item FROM cenapracorrectanswers").addScalar("checklist",Hibernate.INTEGER).addScalar("item",Hibernate.INTEGER);
            Iterator it = q.list().iterator();
            while(it.hasNext()) {
                Object[] data = (Object[])it.next();
                if(answers.containsKey(data[0])) {
                    ((Collection)answers.get(data[0])).add(data[1]);
                }else {
                    Collection list = new LinkedList();
                    list.add(data[1]);
                    answers.put(data[0],list);
                }
            }
        }catch(Exception e) {
			Object[] parameters = {};
            handler.getException(e,"getCorrectAnswers",userSessionData,parameters);
        }
        return answers;
    }
    
    /**
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     * @ejb.interface-method
     */
    public ListResult getParticipantList(String firstName,String lastName,String activity,String divorg,boolean all,int first,int count,UserSessionData userSessionData) throws Exception {
        Integer total = new Integer(0);
        Collection list = new LinkedList();
        try {
            Session session = HibernateUtil.currentSession();
            
            String where = " FROM cenapraparticipants cp JOIN divorgitemlevel1s div ON cp.divorg1 = div.id JOIN divorgitemlevel2s div2 ON div.divorgitemlevel2 = div2.id ";
            if(!activity.equals("-1") && !activity.equals("0")) {
                where += " JOIN cenapraactivityregistry car ON car.participant = cp.id JOIN cenapraactivity ca ON ca.id = car.activity ";
            }
            where += " WHERE cp.corporation = " +String.valueOf(userSessionData.getFilter().getCorporationId());
            if(!Util.empty(firstName)) {
                where += " AND lower(firstname) LIKE '%"+firstName.toLowerCase()+"%' ";
            }
            if(!Util.empty(lastName)) {
                where += " AND lower(lastname) LIKE '%"+lastName.toLowerCase()+"%' ";
            }
            if(!Util.empty(divorg)) {
                where += " AND divorg1 = "+divorg+" ";
            }
            if(activity.equals("-1")) {
                where += " AND cp.id NOT IN (SELECT participant FROM cenapraactivityregistry) ";
            }else if(!activity.equals("0")) {
                where += " AND ca.type = '"+activity+"' ";
            }
            
            Query queryCount = session.createSQLQuery("SELECT COUNT(*) AS count "+where).addScalar("count",Hibernate.INTEGER);
            total = (Integer)queryCount.uniqueResult();
            
            String sql = "SELECT cp.id,cp.firstname,cp.lastname,div.resourcekey AS div,div2.resourcekey AS div2";
            if(all) {
                sql += ",birthdate,population,organization,cp.state as st,cp.city,delegation," +
                        "address,postalcode,phone,movile,email,medical," +
                        "paramedical,nurse,police,vialinspector,teacher," +
                        "prevent,otherprofession,education,othereducation,security,securitytrainning,securitydesc," +
                        "teachexperience,first,second,third,otherteach,cp.observations ";
            }
            if(!activity.equals("-1") && !activity.equals("0")) {
                sql += ",ca.type,ca.date,ca.city as acCity,ca.instructor ";
            }
            SQLQuery query = session.createSQLQuery(sql+where);
            query.addScalar("id",Hibernate.INTEGER);
            query.addScalar("firstname",Hibernate.STRING);
            query.addScalar("lastname",Hibernate.STRING);
            query.addScalar("div",Hibernate.STRING);
            query.addScalar("div2",Hibernate.STRING);
            if(all) {
                query.addScalar("birthdate",Hibernate.DATE);
                query.addScalar("population",Hibernate.STRING);
                query.addScalar("organization",Hibernate.STRING);
                query.addScalar("st",Hibernate.STRING);
                query.addScalar("city",Hibernate.STRING);
                query.addScalar("delegation",Hibernate.STRING);
                query.addScalar("address",Hibernate.STRING);
                query.addScalar("postalcode",Hibernate.STRING);
                query.addScalar("phone",Hibernate.STRING);
                query.addScalar("movile",Hibernate.STRING);
                query.addScalar("email",Hibernate.STRING);
                query.addScalar("medical",Hibernate.BOOLEAN);
                query.addScalar("paramedical",Hibernate.BOOLEAN);
                query.addScalar("nurse",Hibernate.BOOLEAN);
                query.addScalar("police",Hibernate.BOOLEAN);
                query.addScalar("vialinspector",Hibernate.BOOLEAN);
                query.addScalar("teacher",Hibernate.BOOLEAN);
                query.addScalar("prevent",Hibernate.BOOLEAN);
                query.addScalar("otherprofession",Hibernate.STRING);
                query.addScalar("education",Hibernate.STRING);
                query.addScalar("othereducation",Hibernate.STRING);
                query.addScalar("security",Hibernate.BOOLEAN);
                query.addScalar("securitytrainning",Hibernate.BOOLEAN);
                query.addScalar("securitydesc",Hibernate.STRING);
                query.addScalar("teachexperience",Hibernate.BOOLEAN);
                query.addScalar("first",Hibernate.BOOLEAN);
                query.addScalar("second",Hibernate.BOOLEAN);
                query.addScalar("third",Hibernate.BOOLEAN);
                query.addScalar("otherteach",Hibernate.STRING);
                query.addScalar("observations",Hibernate.STRING);
            }
            if(!activity.equals("-1") && !activity.equals("0")) {
                query.addScalar("type",Hibernate.STRING);
                query.addScalar("date",Hibernate.DATE);
                query.addScalar("acCity",Hibernate.STRING);
                query.addScalar("instructor",Hibernate.STRING);
            }
            query.setFirstResult(first);
            if(count != -1) {
                query.setMaxResults(count);
            }
            String ids = "";
            Iterator it = query.list().iterator();
            Hashtable elements = new Hashtable();
            while(it.hasNext()) {
                CENAPRAParticipantListData data = (all) ? new CENAPRAParticipantListFullData((Object[])it.next()) : new CENAPRAParticipantListData((Object[])it.next()); 
                elements.put(data.getId(),data);
                if(ids.length() > 0) {
                    ids += ",";
                }
                ids += String.valueOf(data.getId());
            }
            
            if(ids.length() > 0) {
                if(activity.equals("0")) {
                    sql = "SELECT car.participant,ca.type,ca.date,ca.city,ca.instructor FROM cenapraactivityregistry car " +
                            "JOIN cenapraactivity ca ON ca.id = car.activity " +
                            "WHERE participant IN ("+ids+")";
                    query = session.createSQLQuery(sql).addScalar("participant",Hibernate.INTEGER).addScalar("type",Hibernate.STRING).addScalar("date",Hibernate.DATE).addScalar("city",Hibernate.STRING).addScalar("instructor",Hibernate.STRING);
                    it = query.list().iterator();
                    while(it.hasNext()) {
                        Object[] data = (Object[])it.next();
                        CENAPRAParticipantListData listData = (CENAPRAParticipantListData)elements.get(data[0]);
                        listData.setActivity((String)data[1]);
                        listData.setDate((Date)data[2]);
                        listData.setCity((String)data[3]);
                        listData.setInstructor((String)data[4]);
                    }
                }
            }
            
            Enumeration elementEnum = elements.elements();
            while(elementEnum.hasMoreElements()) {
                list.add(elementEnum.nextElement());
            }
        }catch(Exception e) {
			Object[] parameters = {firstName,lastName,activity,divorg,all,first,count};
            handler.getException(e,"getParticipantList",userSessionData,parameters);
        }
        return new ListResult(list,total.intValue());
    }

    /**
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     * @ejb.interface-method
     */
    public ListResult getParticipantResult(String firstName,String lastName,Collection types,Date from,Date to,boolean all,int checkListType,int first,int count,UserSessionData userSessionData) throws Exception {
        Integer total = new Integer(0);
        Collection list = new LinkedList();
        int corporationId = userSessionData.getFilter().getCorporationId().intValue();
        try {
            String where = "FROM cenapraparticipants cp " +
                "JOIN cenapraactivityregistry car ON car.participant = cp.id " +
                "JOIN cenapraactivity ca ON ca.id = car.activity " +
                "JOIN divorgitemlevel1s div ON ca.divorg1 = div.id "+
                "JOIN divorgitemlevel2s div2 ON div.divorgitemlevel2 = div2.id "+
                "WHERE cp.corporation = " +String.valueOf(corporationId);
            if(!Util.empty(firstName)) {
                where += " AND lower(firstname) LIKE '%"+firstName.toLowerCase()+"%' ";
            }
            if(!Util.empty(lastName)) {
                where += " AND lower(lastname) LIKE '%"+lastName.toLowerCase()+"%' ";
            }
            if(types.size() > 0) {
                where += " AND (ca.type IN (";
                Iterator it = types.iterator();
                while(it.hasNext()) {
                    where += "'"+String.valueOf(it.next())+"'";
                    if(it.hasNext()) {
                        where += ",";
                    }
                }
                where += "))";
            }/*else {
                where += " AND (ca.type IN ('datatype.scheduledactivitytype.cenapramultiplicadores','datatype.scheduledactivitytype.cenapramultiplicadoresnew','datatype.scheduledactivitytype.cenaprareplicadores'))";                
            }*/
            if(from != null) {
                where += " AND ca.date >= "+Util.formatSQL(from,0);
            }
            if(to != null) {
                where += " AND ca.date <= "+Util.formatSQL(to,1);
            }

            Session session = HibernateUtil.currentSession();

            SQLQuery query = session.createSQLQuery("SELECT COUNT(*) AS count "+where);
            query.addScalar("count",Hibernate.INTEGER);
            total = (Integer)query.uniqueResult();
            
            String sql = "SELECT cp.id,cp.firstname,cp.lastname,div.resourcekey AS div,div2.resourcekey AS div2";
            if(all) {
                sql += ",birthdate,population,organization,cp.state as st,cp.city,delegation," +
                        "address,postalcode,phone,movile,email,medical," +
                        "paramedical,nurse,police,vialinspector,teacher," +
                        "prevent,otherprofession,education,othereducation,security,securitytrainning,securitydesc," +
                        "teachexperience,first,second,third,otherteach,cp.observations";
            }
            sql += ",ca.type,ca.date,ca.city as acCity,ca.instructor,car.id as registry ";
            query = session.createSQLQuery(sql+" "+where);
            query.addScalar("id",Hibernate.INTEGER);
            query.addScalar("firstname",Hibernate.STRING);
            query.addScalar("lastname",Hibernate.STRING);
            query.addScalar("div",Hibernate.STRING);
            query.addScalar("div2",Hibernate.STRING);
            if(all) {
                query.addScalar("birthdate",Hibernate.DATE);
                query.addScalar("population",Hibernate.STRING);
                query.addScalar("organization",Hibernate.STRING);
                query.addScalar("st",Hibernate.STRING);
                query.addScalar("city",Hibernate.STRING);
                query.addScalar("delegation",Hibernate.STRING);
                query.addScalar("address",Hibernate.STRING);
                query.addScalar("postalcode",Hibernate.STRING);
                query.addScalar("phone",Hibernate.STRING);
                query.addScalar("movile",Hibernate.STRING);
                query.addScalar("email",Hibernate.STRING);
                query.addScalar("medical",Hibernate.BOOLEAN);
                query.addScalar("paramedical",Hibernate.BOOLEAN);
                query.addScalar("nurse",Hibernate.BOOLEAN);
                query.addScalar("police",Hibernate.BOOLEAN);
                query.addScalar("vialinspector",Hibernate.BOOLEAN);
                query.addScalar("teacher",Hibernate.BOOLEAN);
                query.addScalar("prevent",Hibernate.BOOLEAN);
                query.addScalar("otherprofession",Hibernate.STRING);
                query.addScalar("education",Hibernate.STRING);
                query.addScalar("othereducation",Hibernate.STRING);
                query.addScalar("security",Hibernate.BOOLEAN);
                query.addScalar("securitytrainning",Hibernate.BOOLEAN);
                query.addScalar("securitydesc",Hibernate.STRING);
                query.addScalar("teachexperience",Hibernate.BOOLEAN);
                query.addScalar("first",Hibernate.BOOLEAN);
                query.addScalar("second",Hibernate.BOOLEAN);
                query.addScalar("third",Hibernate.BOOLEAN);
                query.addScalar("otherteach",Hibernate.STRING);
                query.addScalar("observations",Hibernate.STRING);
            }
            query.addScalar("type",Hibernate.STRING);
            query.addScalar("date",Hibernate.DATE);
            query.addScalar("acCity",Hibernate.STRING);
            query.addScalar("instructor",Hibernate.STRING);
            query.addScalar("registry",Hibernate.INTEGER);
            query.setFirstResult(first);
            if(count != -1) {
                query.setMaxResults(count);
            }
            Iterator it = query.list().iterator();
            String ids = "";
            Hashtable elements = new Hashtable();
            while(it.hasNext()) {
                CENAPRAParticipantListData participantData = (all) ? new CENAPRAParticipantListFullData((Object[])it.next()) : new CENAPRAParticipantListData((Object[])it.next()); 
                if(!elements.containsKey(participantData.getId())) {
	                if(ids.length() > 0) {
	                    ids += ",";
	                }
	                ids += String.valueOf(participantData.getRegistry());
	                elements.put(participantData.getId(),participantData);
                }
            }
    
            if(ids.length() > 0) {
                if(checkListType == 1) {
                    String sql2 = "SELECT car.participant,i.itemorder " +
                            "FROM cenapraactivityregistryvalues carv " +
                            "JOIN cenapraactivityregistry car ON car.id = carv.registry " +
                            "JOIN items i ON i.id = carv.item " +
                            "JOIN point p ON i.point = p.id " +
                            "WHERE car.id IN ("+ids+") AND p.checklist NOT IN (22,52) " +
                            "ORDER BY participant,p.checklist,p.pointorder";
                    query = session.createSQLQuery(sql2);
                    query.addScalar("participant",Hibernate.INTEGER);
                    query.addScalar("itemorder",Hibernate.INTEGER);
                    it = query.list().iterator();
                    Integer participant = 0;
                    int index = 0;
                    while(it.hasNext()) {
                        Object[] data = (Object[])it.next();
                        if(((Integer)data[0]).intValue() != participant) {
                        	participant = ((Integer)data[0]).intValue();
                        	index = 0;
                        }
                       	((CENAPRAParticipantListData)elements.get(data[0])).addItem(index,data[1]);
                        index++;
                    }
                }else {
                	int id = (corporationId == CorporationData.CENAPRA) ? 22 : 52;
                    String sql2 = "SELECT car.participant,i.point,i.text " +
                    "FROM cenapraactivityregistryvalues cav " +
                    "JOIN cenapraactivityregistry car ON car.id = cav.registry " +
                    "JOIN items i ON i.id = cav.item " +
                    "WHERE registry IN ("+ids+") " +
                    "AND checklist = "+id+" ORDER BY participant";
                    query = session.createSQLQuery(sql2);
                    query.addScalar("participant",Hibernate.INTEGER);
                    query.addScalar("point",Hibernate.INTEGER);
                    query.addScalar("text",Hibernate.STRING);
                    it = query.list().iterator();
                    while(it.hasNext()) {
                        Object[] data = (Object[])it.next();
                        ((CENAPRAParticipantListData)elements.get(data[0])).addItem(data[1],data[2]);
                    }
                }
            }
            
            Enumeration elementEnum = elements.elements();
            while(elementEnum.hasMoreElements()) {
                list.add(elementEnum.nextElement());
            }
        }catch(Exception e) {
			Object[] parameters = {firstName,lastName,types,from,to,all,checkListType,first,count};
            handler.getException(e,"getParticipantResult",userSessionData,parameters);
        }
        return new ListResult(list,total.intValue());
    }
    
    /**
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     * @ejb.interface-method
     */
    public Collection getAnswers(String ids, UserSessionData userSessionData) throws Exception {
        try {
            Session session = HibernateUtil.currentSession();
            String sql = "SELECT i.id FROM items i " +
                "JOIN point p ON i.point = p.id " +
                "WHERE i.id IN (SELECT item FROM cenapracorrectanswers) " +
                "AND p.checklist IN ("+ids+") " +
                "ORDER BY p.checklist,pointorder";
            Query query = session.createSQLQuery(sql).addScalar("id",Hibernate.INTEGER);
            return query.list();
        }catch(Exception e) {
			Object[] parameters = {ids};
            handler.getException(e,"getAnswers",userSessionData,parameters);
        }
        return new LinkedList();
    }

    /**
     * @ejb.permission role-name = "administrator,systemaccess"
     * @ejb.interface-method
     */
    public Collection getActivityResume(UserSessionData userSessionData, Text messages) throws Exception {
    	Hashtable hash = new Hashtable();
        try {
            Session session = HibernateUtil.currentSession();
            String sql = "SELECT d2.id,d2.resourcekey,ca.type,COUNT(*) AS c " +
            		"FROM cenapraactivity ca " +
            		"JOIN divorgitemlevel1s d1 ON ca.divorg1 = d1.id " +
            		"JOIN divorgitemlevel2s d2 ON d1.divorgitemlevel2 = d2.id " +
            		"WHERE ca.corporation = " +String.valueOf(userSessionData.getFilter().getCorporationId())+" "+
            		"GROUP BY type,d2.id,d2.resourcekey";
            Query query = session.createSQLQuery(sql).addScalar("id",Hibernate.INTEGER).addScalar("resourcekey",Hibernate.STRING).addScalar("type",Hibernate.STRING).addScalar("c",Hibernate.INTEGER);
            Iterator it = query.list().iterator();
            while(it.hasNext()) {
            	Object[] data = (Object[])it.next();
            	if(hash.containsKey(data[0])) {
            		ReportActivityData activity = (ReportActivityData)hash.get(data[0]);
            		activity.setActivities((String)data[2],(Integer)data[3]);
            	}else {
            		ReportActivityData activity = new ReportActivityData((Integer)data[0],(String)data[1]);
            		activity.setActivities((String)data[2],(Integer)data[3]);
            		hash.put(data[0], activity);
            	}
            }
            
            sql = "SELECT d1.divorgitemlevel2,ca.type,COUNT(*) AS c " +
    			"FROM cenapraactivity ca " +
    			"JOIN divorgitemlevel1s d1 ON ca.divorg1 = d1.id " +
    			"JOIN cenapraactivityregistry car ON car.activity = ca.id " +
        		"WHERE ca.corporation = " +String.valueOf(userSessionData.getFilter().getCorporationId())+" "+
    			"GROUP BY type,d1.divorgitemlevel2";
            query = session.createSQLQuery(sql).addScalar("divorgitemlevel2",Hibernate.INTEGER).addScalar("type",Hibernate.STRING).addScalar("c",Hibernate.INTEGER);
            it = query.list().iterator();
            while(it.hasNext()) {
            	Object[] data = (Object[])it.next();
            	if(hash.containsKey(data[0])) {
            		ReportActivityData activity = (ReportActivityData)hash.get(data[0]);
            		activity.setParticipants((String)data[1],(Integer)data[2]);
            	}
            }
    	}catch(Exception e) {
			Object[] parameters = {messages};
            handler.getException(e,"getActivityResume",userSessionData,parameters);
        }
    	Collection result = new LinkedList();
    	Enumeration values = hash.elements();
    	while(values.hasMoreElements()) {
    		result.add(values.nextElement());
    	}
        return result;
    }
}