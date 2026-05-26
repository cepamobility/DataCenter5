package persistence.alert;


import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.SessionBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import persistence.alert.tables.Alert;
import persistence.alert.tables.ContractorData;
import persistence.hibernate.HibernateUtil;
import persistence.util.ExceptionHandler;
import persistence.util.LogAction;

import communication.administration.user.UserSessionData;
import communication.alert.AlertData;


/**
 * @ejb.bean name="AlertABM"
 *           display-name="Name for AlertABM"
 *           description="Description for AlertABM" 
 *           jndi-name="ejb/AlertABM"
 *           type="Stateless"
 *           view-type="both"
 */

public abstract class AlertABMBean implements SessionBean, Serializable { 

    private ExceptionHandler handler = new ExceptionHandler(AlertABMBean.class);
    
	/**
     * @ejb.create-method 
	 * @ejb.permission role-name = "administrator,alertview,fleetinsertdata"
	 * 
	 */	
	public void ejbCreate(){
		
	};

	/**
	 * @ejb.permission role-name = "administrator,alertview" 
	 * @ejb.interface-method view-type = "both"
	 * @param data
	 * @param userRequest
	 */
	
	public Integer createAlert(AlertData data, String comment,UserSessionData userRequest) throws Exception {
		new LogAction("createAlert");
		try{
			Session session=HibernateUtil.currentSession();
			Alert alert=new Alert(data,session);
			Integer id;
			id = (Integer)session.save(alert);
			alert.setAlertId(id);
			return id;
		
		} catch (Exception e) {
			Object[] parameters = {data,comment};
			handler.getException(e,"createAlert",userRequest,parameters);
		}	
		return null;
			
	}
	
	
	/**
	 * @ejb.permission role-name = "administrator,alertview,fleetinsertdata" 
	 * @ejb.interface-method view-type = "both"
	 * @param data
	 * @param userRequest
	 */
	@TransactionAttribute (TransactionAttributeType.REQUIRES_NEW)
	public  void updateAlert(AlertData data,String comment, UserSessionData userRequest) throws Exception {
		new LogAction("updateAlert");
		try{
			Session session=HibernateUtil.currentSession();
			Alert alert = (Alert)session.load(Alert.class,data.getAlertId());
			alert.updateAlert(data,session);
			session.update(alert);

			
		} catch (Exception e) {
			Object[] parameters = {data,comment};
			handler.getException(e,"updateAlert",userRequest,parameters);
		}	
	}
	
	/**
	 * @ejb.permission role-name = "administrator,alertview" 
	 * @ejb.interface-method view-type = "both"
	 * @param data
	 * @param userRequest
	 */
	@TransactionAttribute (TransactionAttributeType.REQUIRES_NEW)
	public void deleteAlert(AlertData data, String comment) throws Exception {
		
		try{			
			Alert alert = null; 

			Session session = HibernateUtil.currentSession();
			alert = (Alert) session.load(Alert.class, data.getAlertId());

			session.delete(alert); 
			
		}	catch(Exception e){
			e.printStackTrace();

		}
		
		
	}
	
	
	
	/**
     * @ejb.permission role-name = "administrator,alertview"
     * @ejb.interface-method
     */
	public void finalizeAlertsWithExtraInformation(String idVehicle,String type,String [] information,UserSessionData userSessionData) throws Exception {
		
		AlertData alertData = null;
		String comment="";
		try{	
				Session session = HibernateUtil.currentSession();
				
				String queryStr = " select  alert" +
					       " from Alert as alert " +
					       " where alert.alertType.resourceBundleId= '"+type+"' and alert.extradata.value like '"+idVehicle+"%'" +
					       " and alert.enddate is null and alert.extradata.value not in ( ";
					        
				
				String extraInformation;
				for(int j = 0; j < information.length ; j++) {
					extraInformation = information[j];
					 queryStr+=	(j == information.length-1) ? "'"+extraInformation+"')" : "'"+extraInformation+"' ,";
				}
				
				Query query = session.createQuery(queryStr);
				
				List alertColl = query.list();
			    Iterator iter = alertColl.iterator();
			    Alert alert=null;
			    Date date =new Date();
			    while (iter.hasNext()) {
					alert = (Alert) iter.next();
					alertData = alert.getAlertData();
					alertData.setEnddate(date);
					updateAlert(alertData,comment,userSessionData);
				}
			    
			   
		}catch(Exception e){
			Object[] parameters = {idVehicle,type,information};
			handler.getException(e,"finalizeAlertsWithExtraInformation",userSessionData,parameters);			
		}
	}
	
	
	/**
     * @ejb.permission role-name = "administrator,alertview"
     * @ejb.interface-method
     */
	public void finishAlert(Integer idAlert,UserSessionData userSessionData) throws Exception {
		
		AlertData alertData = null;
		String comment="";
		try{	
				Session session = HibernateUtil.currentSession();
				
				String queryStr = " select  alert" +
					       " from Alert as alert " +
					       " where alert.enddate is null and alert.alertId="+idAlert.intValue();
					        
				
				Query query = session.createQuery(queryStr);
				
				List alertColl = query.list();
			    Iterator iter = alertColl.iterator();
			    Alert alert=null;
			    Date date =new Date();
			    while (iter.hasNext()) {
					alert = (Alert) iter.next();
					alertData = alert.getAlertData();
					alertData.setEnddate(date);
					updateAlert(alertData,comment,userSessionData);
				}
			    
			   
		}catch(Exception e){
			Object[] parameters = {idAlert};
			handler.getException(e,"finishAlert",userSessionData,parameters);
			
		}
	}
	
	
	

	
	/**
     * @ejb.permission role-name = "administrator,alertview"
     * @ejb.interface-method
     */
	public  void  deleteContractorMail(Collection mailId,UserSessionData userSessionData) throws Exception {

		try{
			ContractorData contractor=null;
			Integer id=null;
			mailId=getContractorMailtoDelete(mailId,userSessionData);
			Session session=HibernateUtil.currentSession();
			Iterator it = mailId.iterator();
			while(it.hasNext()){
				id= (Integer)it.next();
				contractor = (ContractorData)session.load(ContractorData.class,id);
				session.delete(contractor); 
			}
		
		}catch(Exception e){
			Object[] parameters = {mailId};
			handler.getException(e,"deleteCorporationMail",userSessionData,parameters);
		   
		}
	}
	
	private  Collection  getContractorMailtoDelete(Collection mailId,UserSessionData userSessionData) throws Exception {

		//Este Método se usa para FOSA
		
		Collection list = new LinkedList();
		try{	
				Session session = HibernateUtil.currentSession();
				if (mailId.size()>0){
					String queryStr = " select  contractorData from ContractorData as contractorData  where contractorData.id not in ( ";
					Iterator it = mailId.iterator();
					Integer id=null;
					if(it.hasNext()){
						id= (Integer)it.next();
						queryStr+=id;
					}
					while(it.hasNext()){
						id= (Integer)it.next();
						queryStr+=","+id;
					}
					queryStr+=")";
				
					Query query = session.createQuery(queryStr);
				
					List contractors= query.list();
					Iterator iter = contractors.iterator();
					ContractorData contractorData= null;
					while (iter.hasNext()) {
						contractorData = (ContractorData) iter.next();
						list.add(contractorData.getContractorDataData());
					}
			    }
		
		}catch(Exception e){
			Object[] parameters = {mailId};
			handler.getException(e,"getContractorMailtoDelete",userSessionData,parameters);
			   
		}
		
		return list;
	}
	
	
	
	
	
	
	/**
     * @ejb.permission role-name = "administrator,alertview"
     * @ejb.interface-method
     */
	public  void createUpdateContractorMail(String [] values,UserSessionData userSessionData) throws Exception {
		try{
			String mail=null;
			Session session=HibernateUtil.currentSession();
			Query query = session.createQuery("SELECT contractorData FROM ContractorData contractorData WHERE id = "+values[0]);
            Object result = query.uniqueResult();
            if (result!=null){
            	mail=null;
            	ContractorData contractor = (ContractorData)result; 
            	if ((values[1]!=null)&&(!values[1].equals("mailna")))
					mail=values[1];
            	contractor.setMail(mail);
				session.update(contractor);
			}else{
				mail=null;
				if ((values[1]!=null)&&(!values[1].equals("mailna")))
					mail=values[1];
				ContractorData createContractorMail=new ContractorData(new Integer(values[0]),userSessionData.getFilter().getCorporationId(),mail,1);
				session.save(createContractorMail);
			}	
		
		}catch(Exception e){
			Object[] parameters = {values};
			handler.getException(e,"createUpdateContractorMail",userSessionData,parameters);
			   
		}

	}
		
}
