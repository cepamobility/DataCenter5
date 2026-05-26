package persistence.alert;


import java.io.Serializable;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.SessionBean;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;

import persistence.alert.tables.Alert;
import persistence.alert.tables.ContractorData;
import persistence.hibernate.HibernateUtil;
import persistence.util.ExceptionHandler;
import persistence.util.Util;

import communication.administration.user.UserSessionData;
import communication.alert.AlertConstants;
import communication.alert.AlertData;
import communication.alert.FleetAlertReminderData;
import communication.exception.CommunicationProblemException;
import communication.exception.DeslogedException;
import communication.exception.InvalidDataException;
import communication.util.ListResult;


/**
 * @ejb.bean name="AlertReport"
 *           display-name="Name for AlertReport"
 *           description="Description for AlertReport" 
 *           jndi-name="ejb/AlertReport"
 *           type="Stateless"
 *           view-type="both"
 */

public abstract class AlertReportBean implements SessionBean, Serializable { 

    private ExceptionHandler handler = new ExceptionHandler(AlertReportBean.class);
    
	/**
     * @ejb.create-method 
	 * @ejb.permission role-name = "administrator,alertview,fleetinsertdata"
	 * 
	 */	
	public void ejbCreate(){
		
	};

	
	 /**
	  * Lista de Alertas para una corporacion
     * @ejb.permission role-name = "administrator,alertview"
     * @ejb.interface-method
     */
	public ListResult findAlertList(String startDay,String startMonth,String startYear,String endDay,String endMonth,String endYear,
	        String type,String priority,String email,Integer finalized,int first,int count,UserSessionData userSessionData) throws Exception {
	    ListResult listResult=null;
	    try{    
	    	Session session = HibernateUtil.currentSession();
	    	int corporationId=userSessionData.getFilter().getCorporationId();
	    	
	    	
	    	String queryCountStr = " select  count(*) from alert, datatypealerttype,datatypealertpriority ";
		    if((email != null) && (!email.equals(""))) {
		    	queryCountStr += ", alertto ";
		    }
		    queryCountStr +="where alert.corporation="+corporationId+" and " +
		    		" alert.alerttype= datatypealerttype.resourcebundleid and alert.alertpriority=datatypealertpriority.resourcebundleid ";
		    
		    if((email != null) && (!email.equals(""))) {
		    	queryCountStr += " and alert.alertid=alertto.alert ";
		    }
		    if(finalized != null) {
		    	switch(finalized.intValue()){
	        		case AlertConstants.ACTIVE:
	        			queryCountStr += " and alert.enddate is null ";
	        			break;
	        		case AlertConstants.FINALIZED:
	        			queryCountStr += " and alert.enddate is not null ";
	        			break;
		    	}
	        }
	    	if(Util.isDate(startDay,startMonth,startYear)) {
	    		queryCountStr+=" and alert.lastdate >= '"+startYear+"-"+startMonth+"-"+startDay+"'";
	    	}
	    	if(Util.isDate(endDay,endMonth,endYear)) {
	    		queryCountStr += " and alert.lastdate <= '"+endYear+"-"+endMonth+"-"+endDay+"'";
		    }
	    	if((type != null) && (!type.equals("")) ) {
	    		queryCountStr += " and datatypealerttype.resourcebundleid = '"+type+"'";
	        }
	    	if((priority != null) && (!priority.equals("")) ) {
	    		queryCountStr += " and datatypealertpriority.resourcebundleid = '"+priority+"'";
	        }
	        if((email != null) && (!email.equals(""))) {
	        	queryCountStr += " and alertto.email = '"+email+"'";
	        }
	      
	    	Query queryCount = session.createSQLQuery(queryCountStr).addScalar("count",Hibernate.INTEGER);
            Integer total = (Integer)queryCount.uniqueResult();
            
	    	String queryStr = " select  alert.alertid ,datatypealerttype.resourcebundleid as type ," +
	    			"datatypealertpriority.resourcebundleid as priority ,alert.startdate, " +
	    			" alert.lastdate,alert.subject,alert.enddate " +
	    			" from alert, datatypealerttype,datatypealertpriority ";
	    	if((email != null) && (!email.equals(""))) {
	    		queryStr += ", alertto ";
	    	}
	    	queryStr +="where alert.corporation="+corporationId+" and alert.alerttype=datatypealerttype.resourcebundleid and alert.alertpriority=datatypealertpriority.resourcebundleid ";
	    	if((email != null) && (!email.equals(""))) {
	    		queryStr += " and alert.alertid=alertto.alert ";
	    	}
	    	
	    	if(finalized != null) {
		    	switch(finalized.intValue()){
	        		case AlertConstants.ACTIVE:
	        			queryStr += " and alert.enddate is null ";
	        			break;
	        		case AlertConstants.FINALIZED:
	        			queryStr += " and alert.enddate is not null ";
	        			break;
		    	}
	        }
	    	
	    	if(Util.isDate(startDay,startMonth,startYear)) {
	    		queryStr+=" and alert.lastdate >= '"+startYear+"-"+startMonth+"-"+startDay+"'";
	    	}
	    	if(Util.isDate(endDay,endMonth,endYear)) {
	    		queryStr += " and alert.lastdate >= '"+endYear+"-"+endMonth+"-"+endDay+"'";
		    }
	    	if((type != null) && (!type.equals("")) ) {
	    		queryStr += " AND datatypealerttype.resourcebundleid = '"+type+"'";
	        }
	    	if((priority != null) && (!priority.equals("")) ) {
	    		queryStr += " AND datatypealertpriority.resourcebundleid = '"+priority+"'";
	        }
	        if((email != null) && (!email.equals(""))) {
	        	queryStr += " AND alertto.email = '"+email+"'";
	        }
	      
	        queryStr += " ORDER BY lastdate DESC";
	        
	        System.out.println (queryStr);
	        
	        SQLQuery query = session.createSQLQuery(queryStr);
	        query.addScalar("alertid",Hibernate.INTEGER);
	        query.addScalar("type",Hibernate.STRING);
	        query.addScalar("priority",Hibernate.STRING);
	        query.addScalar("startdate",Hibernate.DATE);
	        query.addScalar("lastdate",Hibernate.DATE);
	        query.addScalar("subject",Hibernate.STRING);
	        query.addScalar("enddate",Hibernate.DATE);
	    	
	    
	
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
	            elements.add(new AlertData((Object[])it.next()));
	        }
	        listResult = new ListResult(elements,total.intValue());
	        
	    }catch(Exception e){
			Object[] parameters = {startDay,startMonth,startYear,endDay,endMonth,endYear,type,priority,email,finalized,first,count};
	        handler.getException(e,"findAlertList",userSessionData,parameters);
	    }
	    return listResult;
	}
	
	 /**
     * @ejb.permission role-name = "administrator,alertview,fleetinsertdata"
     * @ejb.interface-method
     */
	public  AlertData findAlertByType(Integer corporation,String alertType,UserSessionData userSessionData) throws Exception {
		AlertData alertData = null;
		try{	
				Session session = HibernateUtil.currentSession();
				
				String queryStr = " select  alert" +
					       " from Alert as alert " +
					       " where corporation="+corporation.intValue()+" " +
					       		"and alert.alertType.resourceBundleId = '" + alertType+"'";
					        
				Query query = session.createQuery(queryStr);
				
				List alertColl = query.list();
			    Iterator iter = alertColl.iterator();
			    Alert alert=null;
			    while (iter.hasNext()) {
					alert = (Alert) iter.next();
				}
			    if (alert!=null)
			    	alertData = alert.getAlertData();
			    
			    //session.flush();
				//session.close();
		
		}catch(Exception e){
			Object[] parameters = {corporation,alertType};
			handler.getException(e,"findAlertByType",userSessionData,parameters);
			   
		}
		
		return alertData;
	}
	
	 /**
     * @ejb.permission role-name = "administrator,alertview"
     * @ejb.interface-method
     */
	public  AlertData findAlertByPrimaryKey(Integer alertPrimaryKey,UserSessionData userSessionData) throws Exception {
		
		AlertData alertData = null;
		
		try{	
				Session session = HibernateUtil.currentSession();
				
				String queryStr = " select  alert" +
					       " from Alert as alert " +
					       " where alertid = " + alertPrimaryKey;
					        
				Query query = session.createQuery(queryStr);
				
				List alertColl = query.list();
				if (alertColl != null){
					Iterator iter = alertColl.iterator();
					Alert alert=null;
					while (iter.hasNext()) {
						alert = (Alert) iter.next();
						alertData = alert.getAlertData();
					}
				
				}
		
		}catch(Exception e){
			Object[] parameters = {alertPrimaryKey};
			handler.getException(e,"findAlertByPrimaryKey",userSessionData,parameters);
			   
		}
		
		return alertData;
	}

	
	//	Retorna la lista de mails que se encuentran en la tabla AlerTo para una corporación y un tipo de alerta determinado
	
	/**
     * @ejb.permission role-name = "administrator,alertview"
     * @ejb.interface-method
     */
	public  Collection getTo(Integer corporation,int alertType,UserSessionData userSessionData) throws Exception {
	Collection result = null;
	try {
		Session session=HibernateUtil.currentSession();
		Query query;
		List list = new LinkedList();
		Iterator it;
		String sql  = "SELECT  email" +
		" FROM alert a,alertto t" +
		" where t.alert = a.alertid " +
		" and a.alertType.resourceBundleId = '" + alertType +"'"+
		" and t.corporation = " + corporation;
		
		//query = session.createSQLQuery(sql);
		query = session.createSQLQuery(sql)
		.addScalar("email",Hibernate.STRING);

		list = query.list();	
		if (!list.isEmpty()){
			it = list.iterator(); 
			result = new LinkedList();
			while(it.hasNext()) {
				Object[] row = (Object[])it.next(); 
				if (row != null){
					String email = (String)row[0];
					result.add(email);
				}
			}
		}
	
	} catch (Exception e) {		
		Object[] parameters = {corporation,alertType};
		handler.getException(e,"getTo",userSessionData,parameters);
		
	}
	return result;
	}

	
		
	/**
     * @ejb.permission role-name = "administrator,alertview"
     * @ejb.interface-method
     */
	public AlertData findAlertWithExtraInformation(String type,String extrainformation,UserSessionData userSessionData) throws Exception {
		
		AlertData alertData = null;
		
		try{	
				Session session = HibernateUtil.currentSession();
				
				String queryStr = " select  alert" +
					       " from Alert as alert " +
					       " where alert.alertType.resourceBundleId='"+type+
					       "' and alert.enddate is null and alert.extradata.value = '" + extrainformation+"'";
					        
				Query query = session.createQuery(queryStr);
				
				List alertColl = query.list();
			    Iterator iter = alertColl.iterator();
			    Alert alert=null;
			    while (iter.hasNext()) {
					alert = (Alert) iter.next();
					alertData = alert.getAlertData();
				}
			    
			   
		}catch(Exception e){
			Object[] parameters = {type,extrainformation};
			handler.getException(e,"findAlertWithExtraInformation",userSessionData,parameters);
			
		}
		
		return alertData;
	}
	
	
	
	
	/**
	 * Listado de alertas para enviar el dia de hoy
     * @ejb.permission role-name = "administrator,alertview"
     * @ejb.interface-method
     */
	public  Collection getTodayAlert() throws Exception {
		
		Collection result = null;
		
		try{	
			Session session = HibernateUtil.currentSession();
			String queryStr = " select  alert" +
				       " from Alert as alert " +
				       // " where alert.lastdate >= CURRENT_DATE and alert.lastdate <= CURRENT_DATE + 1" ;
				       // Fecha finalizacion nula o mayor o igula que hoy
				       " where (alert.enddate is null or alert.enddate >= CURRENT_TIMESTAMP) " +
				       // Fecha de proximo envio no nula y menor o igual que hoy
				       " and (alert.nextdate is not null and alert.nextdate <= CURRENT_TIMESTAMP) " ;
						
			
			Query query = session.createQuery(queryStr);
			
			List alertColl = query.list();
			if (alertColl != null && alertColl.size() > 0){
				Iterator iter = alertColl.iterator();
				Alert alert;
				result = new LinkedList();
				while (iter.hasNext()) {
					alert = (Alert) iter.next();				
					result.add(alert.getAlertData());
				}
			}

		}catch(Exception e){
			e.printStackTrace();		
		}
		return result;
	}
	
	/**
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws CommunicationProblemException
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,alertview"
     */
    public List getAlerts(String alerts,UserSessionData userSessionData) throws Exception {
    	List result = new LinkedList();
		try{	
				Session session = HibernateUtil.currentSession();
				
				String queryStr = " select  alert" +
					       " from Alert as alert " +
					       " where alertid in (" + alerts+")";
					        
				Query query = session.createQuery(queryStr);
				
				List alertColl = query.list();
				
			    Iterator iter = alertColl.iterator();
			    Alert alert=null;
			    while (iter.hasNext()) {
					alert = (Alert) iter.next();
					result.add(alert.getAlertData());
				}
			  
		
		}catch(Exception e){
			Object[] parameters = {alerts};
			handler.getException(e,"getAlerts",userSessionData,parameters);
			   
		}
		
		return result;
	}
    
    
    /**
     * @ejb.permission role-name = "administrator,alertview,fleetinsertdata"
     * @ejb.interface-method
     */
	public  List  getCorporationReminderAlert(FleetAlertReminderData reminder,UserSessionData userSessionData) throws Exception {
		
		// FleetAlertReminderData fleetAlertReminderData = null;
//		List result = new LinkedList();
//		
//		try{	
//				Session session = HibernateUtil.currentSession();
//				
//				String queryStr = " select  fleetAlertReminder" +
//					       " from FleetAlertReminder as fleetAlertReminder ";
//					      
//					        
//				int corporation=reminder.getCorporation().intValue();
//				if(corporation==CorporationData.JJ){
//					if (reminder.getCountry().intValue()!=119){
//						reminder.setDivOrg21(new Integer(0));
//					}else{
//						reminder.setDivOrg22(new Integer(0));
//					}
//					queryStr=queryStr+ " where corporation="+corporation+" and country = " + reminder.getCountry().intValue()+" and divorg21 = " +reminder.getDivOrg21().intValue()+
//					" and divorg22 = " +reminder.getDivOrg22().intValue()+" and divorg23 = 0";
//				}else if((corporation==CorporationData.MONSANTO)||(corporation==CorporationData.LILLY)){
//					queryStr=queryStr+ " where corporation="+corporation+" and country = " + reminder.getCountry().intValue()+" and divorg21 = 0 and divorg22 = 0 and divorg23 = 0"; 
//				}else if(corporation==CorporationData.YOLI){
//					queryStr=queryStr+ " where corporation="+corporation+" and country = 0 and divorg21 = 0 and divorg22 = 0 and divorg23 = 0"; 
//				}else{
//					queryStr=queryStr+ " where corporation=9999999"; 
//					
//				}
//				
//				
//				Query query = session.createQuery(queryStr);
//				
//				List alertColl = query.list();
//			    Iterator iter = alertColl.iterator();
//			    FleetAlertReminder fleetAlertReminder = null;
//			    while (iter.hasNext()) {
//			    	fleetAlertReminder = (FleetAlertReminder) iter.next();
//			    	fleetAlertReminder.getType();
//			    	AlertData alert=findAlertByType(fleetAlertReminder.getCorporation(),fleetAlertReminder.getType().getResourceBundleId(),userSessionData);
//			    	if (alert!=null)
//			    		result.add(alert);
//			    	
//				}
//			    
//			    
//			    //session.flush();
//				//session.close();
//		
//		}catch(Exception e){
//			Object[] parameters = {reminder};
//			handler.getException(e,"getCorporationReminderAlert",userSessionData,parameters);
//			   
//		}
//		
//		return result;
		return null;
		
	}
	
	
	
	/**
     * @ejb.permission role-name = "administrator,alertview"
     * @ejb.interface-method
     */
	public  Hashtable  getContractorsMail(UserSessionData userSessionData) throws Exception {
		//Este Método se usa para FOSA
		
		Hashtable list = new Hashtable();
		try{	
				Session session = HibernateUtil.currentSession();
				
				String queryStr = " select  contractorData from ContractorData as contractorData ";
					      
				
				Query query = session.createQuery(queryStr);
				
				List contractors= query.list();
			    Iterator iter = contractors.iterator();
			    ContractorData contractorData= null;
			    while (iter.hasNext()) {
			    	contractorData = (ContractorData) iter.next();
			    	list.put(contractorData.getId(),contractorData.getContractorDataData());
				}
		
		}catch(Exception e){
			Object[] parameters = {};
			handler.getException(e,"getCorporationMail",userSessionData,parameters);
			   
		}
		
		return list;
		
	}
	

	
	

	
	
		
}
