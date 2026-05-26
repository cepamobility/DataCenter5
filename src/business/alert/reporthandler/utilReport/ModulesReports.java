/**
 * Created on 5-feb-2009
 * CEPA
 * DataCenter 5
 */
package business.alert.reporthandler.utilReport;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import persistence.driver.tables.Driver;
import persistence.hibernate.HibernateUtil;
import persistence.util.Util;
import persistence.util.UtilityQuery;

import communication.activity.tables.CEPAActivityConstants;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.alert.AlertDriverData;
import communication.driver.report.DriverReportDataType;
import communication.driver.report.FOSADriver;
import communication.driver.report.FOSADriverReportDataType;
import communication.driver.tables.DriverData;
import communication.util.ListResult;

public class ModulesReports implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger for this class
	 */
	
	//private ExceptionHandler handler = new ExceptionHandler(ModulesReports.class);
    public ModulesReports() {
        super();
    }
    
    public static ListResult getFOSAReportDrivers(Hashtable<String,String> inputParameters, 
    		Integer[][] divOrgs, Integer[][] divGeos, int first, int count,
    		UserSessionData userSessionData) throws Exception {
    
        try {
            Session session = HibernateUtil.currentSession();
            
            
            Hashtable<Integer,Object> result = new Hashtable<Integer,Object>();
            Hashtable<String,Object> parameters = new Hashtable<String,Object>();
            String queryStr = UtilityQuery.corporationDivisionsRestictions("driver",userSessionData);
                    
            if(inputParameters.containsKey("firstName") && !Util.empty((String)inputParameters.get("firstName"))) {
                queryStr += " AND LOWER(firstName) LIKE LOWER(:firstName) ";
                parameters.put("firstName","%"+inputParameters.get("firstName")+"%");
            }
            if(inputParameters.containsKey("lastName") && !Util.empty((String)inputParameters.get("lastName"))) {
                queryStr += " AND LOWER(lastName) LIKE LOWER(:lastName) ";
                parameters.put("lastName","%"+inputParameters.get("lastName")+"%");
            }
            if(inputParameters.containsKey("corporationId") && !Util.empty((String)inputParameters.get("corporationId"))) {
                queryStr += " AND corporationId = (:corporationId) ";
                parameters.put("corporationId",inputParameters.get("corporationId"));
            }
            if(inputParameters.containsKey("birthDate")) {
                Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");     
                queryStr += " AND birthDate = '"+dateFormat.format(inputParameters.get("birthDate"))+"' ";
            }
            
            HashSet set = new HashSet();
            String divOrgStr = "";
            if(divOrgs != null && divOrgs.length > 0) {
                for(int j = 0; j < divOrgs.length; j++) {
                    if(divOrgs[j] != null && divOrgs[j].length > 0) {
                        divOrgStr = "";
                        divOrgStr = " and driver.divOrg"+String.valueOf(j+1)+" in (";
                        set.clear();
                        for(int i = 0; i < divOrgs[j].length; i++) {
                            set.add(divOrgs[j][i]);
                        }
                        Iterator it = set.iterator();
                        int i = 0;
                        while (it.hasNext()){
                            String div = ((Integer)it.next()).toString();
                            divOrgStr += div;
                            divOrgStr += (i == set.size()-1) ? ") " : ",";
                            i++;
                        }
                        queryStr+=divOrgStr;
                    }
                }
            }   
            /* Modificacion de DivOrgs para Performance */
            if(divGeos != null) {
                if(divGeos[0] != null && divGeos[0].length > 0) {
                    queryStr += "and driver.corpCountry.pk.country in (";
                    for(int i = 0; i < divGeos[0].length; i++) {
                        queryStr += divGeos[0][i];
                        queryStr += (i == divGeos[0].length-1) ? ")" : ",";
                    }
                }
            }
            if(inputParameters.containsKey("status") && !Util.empty((String)inputParameters.get("status"))) {
                switch (Integer.parseInt((String)inputParameters.get("status"))) {
                    case DriverData.ACTIVE:
                        queryStr += " and exclusionDate = null";
                        break;
                    case DriverData.EXCLUDED:
                        queryStr += " and exclusionDate != null";
                        break;
                }
            }
            
            if(inputParameters.containsKey("fines") && !Util.empty((String)inputParameters.get("fines"))) {
                String fines = (String)inputParameters.get("fines");
                if(!fines.equals("0")){
                    if (fines.equals("1")){
                        queryStr += " and driver.id in (select event.driver.id from Fine as event where event.driver.id = driver.id and event.status <> 1 )";
                    }else if (fines.equals("2")){
                        queryStr += " and " +inputParameters.get("fineCount")+" <= (select count(*) from Fine as event where event.driver.id = driver.id and event.status <> 1 )";
                    }else if (fines.equals("3")){
                        queryStr += " and driver.id not in (select event.driver.id from Fine as event where event.driver.id = driver.id and event.status <> 1 )";
                    }
                }
            }
            if(inputParameters.containsKey("accidents") && !Util.empty((String)inputParameters.get("accidents"))) {
                String accidents = (String)inputParameters.get("accidents");
                if(!accidents.equals("0")){
                    if (accidents.equals("1")){
                        queryStr += " and driver.id in (select event.driver.id from Event as event where event.driver.id = driver.id and event.accident is not null and event.status <> 1 )";
                    }else if (accidents.equals("2")){
                        queryStr += " and "+inputParameters.get("accidentCount")+" <= (select count(*) from Event as event where event.driver.id = driver.id and event.accident is not null and event.status <> 1 )";
                    }else if (accidents.equals("3")){
                        queryStr += " and driver.id not in (select event.driver.id from Event as event where event.driver.id = driver.id and event.accident is not null and event.status <> 1 )";
                    }
                }
            }
            if(inputParameters.containsKey("incidents") && !Util.empty((String)inputParameters.get("incidents"))) {
                String incidents = (String)inputParameters.get("incidents");
                if(!incidents.equals("0")){
                    if (incidents.equals("1")){
                        queryStr += " and driver.id in (select event.driver.id from Event as event where event.driver.id = driver.id and event.incident is not null and event.status <> 1 )";
                    }else if (incidents.equals("2")){
                        queryStr += " and "+inputParameters.get("incidentCount")+" <= (select count(*) from Event as event where event.driver.id = driver.id and event.incident is not null and event.status <> 1 )";
                    }else if (incidents.equals("3")){
                        queryStr += " and driver.id not in (select event.driver.id from Event as event where event.driver.id = driver.id and event.incident is not null and event.status <> 1 )";
                    }
                }
            }
            
            if(userSessionData.isForest() && userSessionData.getFilter().getCorporationId().intValue() != CorporationData.KLABIN) {

                String bps = (String)inputParameters.get("bps");
                if(bps != null && bps.equals("yes")){
                    queryStr += " and bpsregistry is not null";
                }else if(bps.equals("no")){
                    queryStr += " and bpsregistry is null";
                }
                
                String apt = (String)inputParameters.get("aptitudResult");
                String type = (String)inputParameters.get("aptitudStatus");
                if(apt != null && Integer.parseInt(apt) == DriverData.APT) {
                    queryStr += " and id in (select test.pk.driver from AptitudTest test where test.expiry > (:dateTest) and test.apt = 't')";
                    parameters.put("dateTest",Calendar.getInstance().getTime());
                }else if(apt != null && Integer.parseInt(apt) == DriverData.NOT_APT) {
                    queryStr += " and id not in (select test.pk.driver from AptitudTest test where test.expiry > (:dateTest) and test.apt = 't')";
                    parameters.put("dateTest",Calendar.getInstance().getTime());
                }else if(type != null && Integer.parseInt(type) == DriverData.NOT_EXPIRED) {
                    queryStr += " and id in (select test.pk.driver from AptitudTest test where test.expiry > (:dateTest))";
                    parameters.put("dateTest",Calendar.getInstance().getTime());
                }else if(type != null && Integer.parseInt(type) == DriverData.EXPIRED) {
                    queryStr += " and id not in (select test.pk.driver from AptitudTest test where test.expiry > (:dateTest))";
                    parameters.put("dateTest",Calendar.getInstance().getTime());
                }

                String controlResult = (String)inputParameters.get("alcoholResult");
                type = (String)inputParameters.get("alcoholStatus");
                Calendar c = Calendar.getInstance();
                c.add(Calendar.YEAR,-1);
                if(controlResult != null && Integer.parseInt(controlResult) != DriverData.ALL) {
                    queryStr += " and id in (select control.pk.driver from AlcoholControl control where control.pk.date > (:dateControl) and control.result = (:result))";
                    parameters.put("dateControl",c.getTime());
                    parameters.put("result",controlResult);
                }else if(type != null && Integer.parseInt(type) == DriverData.NOT_EXPIRED) {
                    queryStr += " and id in (select control.pk.driver from AlcoholControl control where control.pk.date > (:dateControl))";
                    parameters.put("dateControl",c.getTime());
                }else if(type != null && Integer.parseInt(type) == DriverData.EXPIRED) {
                    queryStr += " and id not in (select control.pk.driver from AlcoholControl control where control.pk.date > (:dateControl))";
                    parameters.put("dateControl",c.getTime());
                }
                
                String postiveAlcohol = (String)inputParameters.get("postiveAlcohol"); 
                if(postiveAlcohol != null && Integer.parseInt(postiveAlcohol) == DriverData.ANY_POSITIVE) {
                    queryStr += " and id in (select control.pk.driver from AlcoholControl control where control.result = "+DriverData.POSITIVE+")";
                } else if(postiveAlcohol != null && Integer.parseInt(postiveAlcohol) == DriverData.WITHOUT_POSITIVE) {
                    queryStr += " and id not in (select control.pk.driver from AlcoholControl control where control.result = "+DriverData.POSITIVE+")";
                }
            }
            
            
            Query query1 = session.createQuery("select count(driver) from Driver as driver "+queryStr);
            Enumeration keys = parameters.keys();
            while(keys.hasMoreElements()) {
                String parameter = (String)keys.nextElement();
                query1.setParameter(parameter,parameters.get(parameter));
            }
            // Integer total = (Integer)query1.uniqueResult();
            Integer total = ((Long)query1.uniqueResult()).intValue();

            queryStr += " order by lastname";
            Query query2 = session.createQuery("SELECT DISTINCT driver FROM Driver AS driver "+queryStr);
            keys = parameters.keys();
            while(keys.hasMoreElements()) {
                String parameter = (String)keys.nextElement();
                query2.setParameter(parameter,parameters.get(parameter));
            }    
            if(count != -1) {
                query2.setFirstResult(first);
                query2.setMaxResults(count);
            }
            List userColl = query2.list();
            Iterator iter = userColl.iterator();
            userColl=null;
            String ids = "";
            while (iter.hasNext()) {
                Driver driver = (Driver) iter.next();
                ids += (ids.length() == 0) ? String.valueOf(driver.getId()) : ","+String.valueOf(driver.getId());
                result.put(driver.getId(),driver.getFOSADriverReportData((String)inputParameters.get("type")));
            }   
            
            if(ids.length() > 0) {
            	String activities = "'" + CEPAActivityConstants.PESADOS_1
											+ "','" + CEPAActivityConstants.BTW3 + "','"
											+ CEPAActivityConstants.BTW3UPDATE + "','"
											+ CEPAActivityConstants.BTW3RENOVACION + "','"
											+ CEPAActivityConstants.BTW3SEGUNDARENOVACION + "','"
											+ CEPAActivityConstants.EVALUACIONENCABINAPESADO + "','" 
											+ CEPAActivityConstants.EVALUACIONENCABINA + "'";
				if (userSessionData.getFilter().getCorporationId() == CorporationData.WEYERHAEUSER) {
					activities += ",'" + CEPAActivityConstants.RADAR + "','"
							+ CEPAActivityConstants.INDUCTION_TRAINING + "'";
				}  
            	
                String sqlCount = "SELECT ar.driver,ca.type,count(*) as count, max(ca.date) as date, max(ca.dateexpiry) as dateexpiry, ar.note " +
                        " FROM activityregistry ar " +
                        " JOIN cepaactivity ca ON ca.activityid = ar.activity " +
                        " WHERE ca.type IN (" + activities + ")" +
                        " AND ar.driver IN ("+ids+") " +
                        " AND ca.statE = 'datatype.scheduledactivitystate.dictada' " +
                        " GROUP BY driver,type,note " +
                        " ORDER BY date DESC";
                Query q = session.createSQLQuery(sqlCount).
                addScalar("driver",Hibernate.INTEGER).
                addScalar("type",Hibernate.STRING).
                addScalar("count",Hibernate.INTEGER).
                addScalar("date",Hibernate.DATE).
                addScalar("dateexpiry",	Hibernate.DATE).
                addScalar("note",Hibernate.STRING);
                List list = q.list();
                if (list != null){
	                Iterator it = list.iterator();
	                while(it.hasNext()) {
	                    Object[] data = (Object[])it.next();
	                    FOSADriver fosaDriver = (FOSADriver) result.get((Integer)data[0]);
	                    if (data[1].equals(CEPAActivityConstants.PESADOS_1) || 
	                    	data[1].equals(CEPAActivityConstants.BTW3) ||
	                    	data[1].equals(CEPAActivityConstants.BTW3UPDATE) ||
	                    	data[1].equals(CEPAActivityConstants.BTW3RENOVACION) ||
	                    	data[1].equals(CEPAActivityConstants.BTW3SEGUNDARENOVACION)
	                    	)
	                    {
	                    	// Actividades de Pesados
	                        if(fosaDriver.getLast() == null) {
	                            fosaDriver.setCount(((Integer)data[2]).intValue());
	                            fosaDriver.setLast((Date)data[3]);
	                            
	                            if (data[5].equals(CEPAActivityConstants.APRUVED)
										|| data[5].equals(CEPAActivityConstants.APRUVEDWITHOBSERVATIONS)) {
									fosaDriver.setLastExpiry((Date) data[4]);
								} else {
									fosaDriver.setLastExpiry(null);
								}
	                            
	                        }else {
	                            fosaDriver.addCount(((Integer)data[2]).intValue());
	                            
	                        }
	                    }else if (data[1].equals(CEPAActivityConstants.EVALUACIONENCABINA) ||
	                    		data[1].equals(CEPAActivityConstants.EVALUACIONENCABINAPESADO) ){
	                    	// Evaluaciones en Cabina
	                    	if(fosaDriver.getLastEC() == null) {
	                            fosaDriver.setLastEC((Date)data[3]);
	                        }
	                    }else if (data[1].equals(CEPAActivityConstants.INDUCTION_TRAINING) ||
	                    		data[1].equals(CEPAActivityConstants.INDUCTIONTRAINING2)){
	                    	// Actividades de Inducciones
	                    	if (fosaDriver.getLastInduction() == null){
	                    		fosaDriver.setLastInduction((Date)data[3]);
	                    	}
	                    }else if (data[1].equals(CEPAActivityConstants.RADAR)){
	                    	// Radar
	                    	if (fosaDriver.getLastRadar() == null) {
								fosaDriver.setLastRadar((Date) data[3]);
								fosaDriver.setRadarNote((String) data[5]);
							}
	                    }
	                }
                }
            }
           // session.flush();
            //session.close();

            Enumeration enumDriver = result.elements();
            Collection drivers = new LinkedList();
            while(enumDriver.hasMoreElements()) {
                drivers.add(enumDriver.nextElement());
            }
            
            
            return new ListResult(drivers,total.intValue());
        } catch (Exception e) {
            e.printStackTrace();
        	//handler.getException(e,"getFOSAReportDrivers",userSessionData.getFilter().getLoginName());
        }
        return null;
    }

	public static ListResult getFOSAReportDriversGabinete(
			Hashtable<String, String> inputParameters, Integer[][] divOrgs,
			Integer[][] divGeos,
			UserSessionData userSessionData) {
		try {
            Session session = HibernateUtil.currentSession();
            
            
            Hashtable<Integer,Object> result = new Hashtable<Integer,Object>();
            Hashtable<String,Object> parameters = new Hashtable<String,Object>();
            String queryStr = UtilityQuery.corporationDivisionsRestictions("driver",userSessionData);
                    
          
            
            HashSet set = new HashSet();
            String divOrgStr = "";
            if(divOrgs != null && divOrgs.length > 0) {
                for(int j = 0; j < divOrgs.length; j++) {
                    if(divOrgs[j] != null && divOrgs[j].length > 0) {
                        divOrgStr = "";
                        divOrgStr = " and driver.divOrg"+String.valueOf(j+1)+" in (";
                        set.clear();
                        for(int i = 0; i < divOrgs[j].length; i++) {
                            set.add(divOrgs[j][i]);
                        }
                        Iterator it = set.iterator();
                        int i = 0;
                        while (it.hasNext()){
                            String div = ((Integer)it.next()).toString();
                            divOrgStr += div;
                            divOrgStr += (i == set.size()-1) ? ") " : ",";
                            i++;
                        }
                        queryStr+=divOrgStr;
                    }
                }
            }   
            /* Modificacion de DivOrgs para Performance */
            if(divGeos != null) {
                if(divGeos[0] != null && divGeos[0].length > 0) {
                    queryStr += "and driver.corpCountry.pk.country in (";
                    for(int i = 0; i < divGeos[0].length; i++) {
                        queryStr += divGeos[0][i];
                        queryStr += (i == divGeos[0].length-1) ? ")" : ",";
                    }
                }
            }
            queryStr += " and exclusionDate = null";
            
            
            
            queryStr += " and id not in (select test.pk.driver from AptitudTest test where test.expiry > (:dateTest) and test.apt = 't' )";
            Calendar cal=Calendar.getInstance();
            cal.add(Calendar.MONTH, 1);
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            
            parameters.put("dateTest",cal.getTime());
            
            
            Query query1 = session.createQuery("select count(driver) from Driver as driver "+queryStr);
            Enumeration keys = parameters.keys();
            while(keys.hasMoreElements()) {
                String parameter = (String)keys.nextElement();
                query1.setParameter(parameter,parameters.get(parameter));
            }
            // Integer total = (Integer)query1.uniqueResult();
            Integer total = ((Long)query1.uniqueResult()).intValue();

            queryStr += " order by lastname";
            Query query2 = session.createQuery("SELECT DISTINCT driver FROM Driver AS driver "+queryStr);
            keys = parameters.keys();
            while(keys.hasMoreElements()) {
                String parameter = (String)keys.nextElement();
                query2.setParameter(parameter,parameters.get(parameter));
            }    
           
            List userColl = query2.list();
            Iterator iter = userColl.iterator();
            userColl=null;
            Collection drivers = new LinkedList();
            while (iter.hasNext()) {
                Driver driver = (Driver) iter.next();
                Query queryExpiry=session.createQuery("select max(test.expiry) from AptitudTest test where test.pk.driver.id=(:idDriver) group by test.pk.driver");
                queryExpiry.setParameter("idDriver",driver.getId());
                Object o=queryExpiry.uniqueResult();
                Date expiry=null;
                if(o!=null){
                	expiry=(Date)o;
                }
                drivers.add(new AlertDriverData((DriverReportDataType)driver.getFOSADriverReportData("detailed"), expiry));
            }   
            
            
            return new ListResult(drivers,total.intValue());
        } catch (Exception e) {
            e.printStackTrace();
        	//handler.getException(e,"getFOSAReportDrivers",userSessionData.getFilter().getLoginName());
        }
        return null;
	}
	
	public static ListResult getFOSAReportDriversTraining(Hashtable<String,String> inputParameters, 
    		Integer[][] divOrgs, Integer[][] divGeos, 
    		UserSessionData userSessionData) throws Exception {
    
        try {
            Session session = HibernateUtil.currentSession();
            
            
            Hashtable<Integer,Object> result = new Hashtable<Integer,Object>();
            Hashtable<String,Object> parameters = new Hashtable<String,Object>();
            String queryStr = UtilityQuery.corporationDivisionsRestictions("driver",userSessionData);
                    
          
            
            HashSet set = new HashSet();
            String divOrgStr = "";
            if(divOrgs != null && divOrgs.length > 0) {
                for(int j = 0; j < divOrgs.length; j++) {
                    if(divOrgs[j] != null && divOrgs[j].length > 0) {
                        divOrgStr = "";
                        divOrgStr = " and driver.divOrg"+String.valueOf(j+1)+" in (";
                        set.clear();
                        for(int i = 0; i < divOrgs[j].length; i++) {
                            set.add(divOrgs[j][i]);
                        }
                        Iterator it = set.iterator();
                        int i = 0;
                        while (it.hasNext()){
                            String div = ((Integer)it.next()).toString();
                            divOrgStr += div;
                            divOrgStr += (i == set.size()-1) ? ") " : ",";
                            i++;
                        }
                        queryStr+=divOrgStr;
                    }
                }
            }   
            /* Modificacion de DivOrgs para Performance */
            if(divGeos != null) {
                if(divGeos[0] != null && divGeos[0].length > 0) {
                    queryStr += "and driver.corpCountry.pk.country in (";
                    for(int i = 0; i < divGeos[0].length; i++) {
                        queryStr += divGeos[0][i];
                        queryStr += (i == divGeos[0].length-1) ? ")" : ",";
                    }
                }
            }
            if(inputParameters.containsKey("status") && !Util.empty((String)inputParameters.get("status"))) {
                switch (Integer.parseInt((String)inputParameters.get("status"))) {
                    case DriverData.ACTIVE:
                        queryStr += " and exclusionDate = null";
                        break;
                    case DriverData.EXCLUDED:
                        queryStr += " and exclusionDate != null";
                        break;
                }
            }
            
           
              
            
            
            
            Query query1 = session.createQuery("select count(driver) from Driver as driver "+queryStr);
            Enumeration keys = parameters.keys();
            while(keys.hasMoreElements()) {
                String parameter = (String)keys.nextElement();
                query1.setParameter(parameter,parameters.get(parameter));
            }
            // Integer total = (Integer)query1.uniqueResult();
            Integer total = ((Long)query1.uniqueResult()).intValue();

            queryStr += " order by lastname";
            Query query2 = session.createQuery("SELECT DISTINCT driver FROM Driver AS driver "+queryStr);
            keys = parameters.keys();
            while(keys.hasMoreElements()) {
                String parameter = (String)keys.nextElement();
                query2.setParameter(parameter,parameters.get(parameter));
            }    
            
            List userColl = query2.list();
            Iterator iter = userColl.iterator();
            userColl=null;
            String ids = "";
            HashMap<Integer,DriverData> resultDrivers=new HashMap<Integer, DriverData>();
            while (iter.hasNext()) {
                Driver driver = (Driver) iter.next();
                ids += (ids.length() == 0) ? String.valueOf(driver.getId()) : ","+String.valueOf(driver.getId());
                result.put(driver.getId(),driver.getFOSADriverReportData((String)inputParameters.get("type")));
                resultDrivers.put(driver.getId(), driver.getDriverData());
            }   
            
            if(ids.length() > 0) {
            	String activities = "'" + CEPAActivityConstants.PESADOS_1
											+ "','" + CEPAActivityConstants.BTW3 + "','"
											+ CEPAActivityConstants.BTW3UPDATE + "','"
											+ CEPAActivityConstants.BTW3RENOVACION + "','"
											+ CEPAActivityConstants.BTW3SEGUNDARENOVACION + "','"
											+ CEPAActivityConstants.EVALUACIONENCABINAPESADO + "','" 
											+ CEPAActivityConstants.EVALUACIONENCABINA + "'";
				if (userSessionData.getFilter().getCorporationId() == CorporationData.WEYERHAEUSER) {
					activities += ",'" + CEPAActivityConstants.RADAR + "','"
							+ CEPAActivityConstants.INDUCTION_TRAINING + "'";
				}  
            	
                String sqlCount = "SELECT ar.driver,ca.type,count(*) as count, max(ca.date) as date, max(ca.dateexpiry) as dateexpiry, ar.note " +
                        " FROM activityregistry ar " +
                        " JOIN cepaactivity ca ON ca.activityid = ar.activity " +
                        " WHERE ca.type IN (" + activities + ")" +
                        " AND ar.driver IN ("+ids+") " +
                        " AND ca.statE = 'datatype.scheduledactivitystate.dictada' " +
                        " GROUP BY driver,type,note " +
                        " ORDER BY date DESC";
                Query q = session.createSQLQuery(sqlCount).
                addScalar("driver",Hibernate.INTEGER).
                addScalar("type",Hibernate.STRING).
                addScalar("count",Hibernate.INTEGER).
                addScalar("date",Hibernate.DATE).
                addScalar("dateexpiry",	Hibernate.DATE).
                addScalar("note",Hibernate.STRING);
                List list = q.list();
               
                if (list != null){
	                Iterator it = list.iterator();
	                while(it.hasNext()) {
	                    Object[] data = (Object[])it.next();
	                    
	                    FOSADriverReportDataType fosaDriver = (FOSADriverReportDataType) result.get((Integer)data[0]);
	                    if (data[1].equals(CEPAActivityConstants.PESADOS_1) || 
	                    	data[1].equals(CEPAActivityConstants.BTW3) ||
	                    	data[1].equals(CEPAActivityConstants.BTW3UPDATE) ||
	                    	data[1].equals(CEPAActivityConstants.BTW3RENOVACION) ||
	                    	data[1].equals(CEPAActivityConstants.BTW3SEGUNDARENOVACION)
	                    	)
	                    {
	                    	// Actividades de Pesados
	                        if(fosaDriver.getLast() == null) {
	                            fosaDriver.setCount(((Integer)data[2]).intValue());
	                            fosaDriver.setLast((Date)data[3]);
	                            
	                            if (data[5].equals(CEPAActivityConstants.APRUVED)
										|| data[5].equals(CEPAActivityConstants.APRUVEDWITHOBSERVATIONS)) {
									fosaDriver.setLastExpiry((Date) data[4]);
								} else {
									fosaDriver.setLastExpiry(null);
								}
	                            
	                        }else {
	                            fosaDriver.addCount(((Integer)data[2]).intValue());
	                            
	                        }
	                    }/*else if (data[1].equals(CEPAActivityConstants.EVALUACIONENCABINA) ||
	                    		data[1].equals(CEPAActivityConstants.EVALUACIONENCABINAPESADO) ){
	                    	// Evaluaciones en Cabina
	                    	if(fosaDriver.getLastEC() == null) {
	                            fosaDriver.setLastEC((Date)data[3]);
	                        }
	                    }else if (data[1].equals(CEPAActivityConstants.INDUCTION_TRAINING) ||
	                    		data[1].equals(CEPAActivityConstants.INDUCTIONTRAINING2)){
	                    	// Actividades de Inducciones
	                    	if (fosaDriver.getLastInduction() == null){
	                    		fosaDriver.setLastInduction((Date)data[3]);
	                    	}
	                    }else if (data[1].equals(CEPAActivityConstants.RADAR)){
	                    	// Radar
	                    	if (fosaDriver.getLastRadar() == null) {
								fosaDriver.setLastRadar((Date) data[3]);
								fosaDriver.setRadarNote((String) data[5]);
							}
	                    }*/
	                    
	                    
	                }
                }
            }
            
           
           // session.flush();
            //session.close();

            Enumeration enumDriver = result.elements();
            Collection drivers = new LinkedList();
            Calendar cal=Calendar.getInstance();
            cal.add(Calendar.MONTH, 1);
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            while(enumDriver.hasMoreElements()) {
            	FOSADriverReportDataType fosaDriver = (FOSADriverReportDataType)enumDriver.nextElement();
            	Calendar calAux=Calendar.getInstance();
            	if(fosaDriver.getLastExpiry()!=null){
            		calAux.setTime(fosaDriver.getLastExpiry());
            	}
            	 if(fosaDriver.getLastExpiry()==null || cal.compareTo(calAux)>=0){
            		 drivers.add(new AlertDriverData(fosaDriver, fosaDriver.getLastExpiry()));
                 }else{
                	 String g="";
                 }
              
            }
            
            
            return new ListResult(drivers,total.intValue());
        } catch (Exception e) {
            e.printStackTrace();
        	//handler.getException(e,"getFOSAReportDrivers",userSessionData.getFilter().getLoginName());
        }
        return null;
    }
    
}