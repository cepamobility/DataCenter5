/*
 * Created on 27-oct-2005
 */
package business.cenapra;

import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

import persistence.cenapra.CENAPRAABM;
import persistence.cenapra.CENAPRAABMUtil;
import persistence.cenapra.CENAPRAReport;
import persistence.cenapra.CENAPRAReportUtil;
import business.util.ExceptionHandler;
import business.util.MemoryLog;

import communication.administration.user.UserSessionData;
import communication.cenapra.tables.ActivityData;
import communication.cenapra.tables.ParticipantData;
import communication.exception.DeslogedException;
import communication.exception.InvalidDataException;
import communication.exception.InvalidDeleteException;

/**
 * 
 * @ejb.bean name = "CENAPRAABMFacade" jndi-name = "ejb/CENAPRAABMFacade" type =
 *           "Stateless" transaction-type = "Container"  
 * 
 * @ejb.ejb-ref ejb-name = "CENAPRAABM" ref-name = "ejb/CENAPRAABM" view-type =
 *              "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/CENAPRAABM" ref-name = "CENAPRAABM"
 * 
 * @ejb.ejb-ref ejb-name = "CENAPRAReport" ref-name = "ejb/CENAPRAReport" view-type =
 *              "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/CENAPRAReport" ref-name = "CENAPRAReport"
 * 
 * @ejb.ejb-ref ejb-name = "ActivityABM" ref-name = "ejb/ActivityABM" view-type =
 *              "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/ActivityABM" ref-name = "ActivityABM"
 * 
 * @ejb.util generate="physical"
 * 
 */
public abstract class CENAPRAABMFacadeBean  implements javax.ejb.SessionBean {

    private ExceptionHandler handler = new ExceptionHandler(CENAPRAABMFacadeBean.class);

	/**
	 * Create method
	 * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
	 * @ejb.create-method
	 */
	public void ejbCreate() throws javax.ejb.CreateException {

	}
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
	 * @throws Exception 
	 */
	public void createParticipant(ParticipantData data,String comment,UserSessionData userSessionData) throws Exception {
        try{	
            if(userSessionData == null) {
                throw new DeslogedException("createParticipant","session = null");
            }
            if(data == null) {
                throw new InvalidDataException("createParticipant","participant = null");
            }
		
            CENAPRAABM abm = CENAPRAABMUtil.getHome().create();
            abm.createParticipant(data, comment, userSessionData);
	
        } catch (Exception exception) {
        	Object[] parameters = new Object[2];
        	parameters[0] = data;
        	parameters[1] = comment;
            handler.handleException("createParticipant",exception, userSessionData, parameters);
        } 
	}

	/**
	 * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
	 */
	public void updateParticipant(ParticipantData data,String comment,UserSessionData userSessionData) throws Exception {
	    MemoryLog.getInstance().log(MemoryLog.START,"updateParticipant",getClass());
        try{	
            if (userSessionData == null) {
                throw new DeslogedException("updateParticipant","session = null");
            }
            if (data == null) {
                throw new InvalidDataException("updateParticipant","data = null");
            }

            CENAPRAABM abm = CENAPRAABMUtil.getHome().create();
            abm.updateParticipant(data, comment, userSessionData);
        } catch (Exception exception) {
        	Object[] parameters = new Object[2];
        	parameters[0] = data;
        	parameters[1] = comment;
        	
            handler.handleException("updateParticipant",exception, userSessionData, parameters);
        } 
        MemoryLog.getInstance().log(MemoryLog.END,"updateParticipant",getClass());
	}

    /**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
     */
    public void deleteParticipants(String list, String comment,UserSessionData userSessionData) throws Exception {
        MemoryLog.getInstance().log(MemoryLog.START,"deleteParticipants",getClass());
        try{    
            if (userSessionData == null) {
                throw new DeslogedException("deleteParticipants","session = null");
            }
            CENAPRAReport report = CENAPRAReportUtil.getHome().create();
            CENAPRAABM participant = CENAPRAABMUtil.getHome().create();
            StringTokenizer tokenizer = new StringTokenizer(list,",");
            while(tokenizer.hasMoreTokens()) {
                Integer participantId = new Integer(tokenizer.nextToken());
                if(report.hasActivities(participantId,userSessionData)) {
                    throw new InvalidDeleteException("cenapra.deleteparticipant.hasactivities","");
                }
                participant.deleteParticipant(participantId,comment,userSessionData);
            }
        } catch (Exception exception) {
        	Object[] parameters = new Object[2];
        	parameters[0] = list;
        	parameters[1] = comment;
            handler.handleException("deleteParticipants",exception, userSessionData, parameters);
        } 
        MemoryLog.getInstance().log(MemoryLog.END,"deleteParticipants",getClass());
    }

    /**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
     */
    public void createActivity(ActivityData data, String comment, UserSessionData userSessionData) throws Exception {
        MemoryLog.getInstance().log(MemoryLog.START,"createActivity",getClass());
        try{    
            if (userSessionData == null) {
                throw new DeslogedException("createActivity","session = null");
            }

            CENAPRAABM activity = CENAPRAABMUtil.getHome().create();
            activity.createActivity(data,comment,userSessionData);
            
        } catch (Exception exception) {
        	Object[] parameters = new Object[2];
        	parameters[0] = data;
        	parameters[1] = comment;
            handler.handleException("createActivity",exception, userSessionData, parameters);
        } 
        MemoryLog.getInstance().log(MemoryLog.END,"createActivity",getClass());
    }
    
    /**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
     */
    public void updateActivity(ActivityData data, String comment, UserSessionData userSessionData) throws Exception {
        MemoryLog.getInstance().log(MemoryLog.START,"updateActivity",getClass());
        try{    
            if (userSessionData == null) {
                throw new DeslogedException("updateActivity","session = null");
            }

            CENAPRAABM activity = CENAPRAABMUtil.getHome().create();
            activity.updateActivity(data,comment,userSessionData);
            
        } catch (Exception exception) {
        	Object[] parameters = new Object[2];
        	parameters[0] = data;
        	parameters[1] = comment;
            handler.handleException("updateActivity",exception, userSessionData, parameters);
        } 
        MemoryLog.getInstance().log(MemoryLog.END,"updateActivity",getClass());
    }

    /**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
     */
    public void addActivityParticipants(Collection list, Integer activityId, UserSessionData userSessionData) throws Exception {
        MemoryLog.getInstance().log(MemoryLog.START,"addActivityParticipants",getClass());
        try{    
            if (userSessionData == null) {
                throw new DeslogedException("addActivityParticipants","session = null");
            }
            CENAPRAABM activity = CENAPRAABMUtil.getHome().create();
            Iterator it = list.iterator();
            while(it.hasNext()) {
                activity.createParticipantRegistry(activityId,new Integer((String)it.next()),"",userSessionData);
            }
        } catch (Exception exception) {
        	Object[] parameters = new Object[2];
        	parameters[0] = list;
        	parameters[1] = activityId;
            handler.handleException("addActivityParticipants",exception, userSessionData, parameters);
        } 
        MemoryLog.getInstance().log(MemoryLog.END,"addActivityParticipants",getClass());
    }

    /**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
     */
    public void deleteActivityParticipants(String list, UserSessionData userSessionData) throws Exception {
        MemoryLog.getInstance().log(MemoryLog.START,"deleteActivityParticipants",getClass());
        try{    
            if (userSessionData == null) {
                throw new DeslogedException("deleteActivityParticipants","session = null");
            }
            CENAPRAABM activity = CENAPRAABMUtil.getHome().create();
            activity.deleteActivityRegistryValues(list,userSessionData);
            activity.deleteActivityParticipants(list,userSessionData);
        } catch (Exception exception) {
        	Object[] parameters = new Object[1];
        	parameters[0] = list;
            handler.handleException("deleteActivityParticipants",exception, userSessionData, parameters);
        } 
        MemoryLog.getInstance().log(MemoryLog.END,"deleteActivityParticipants",getClass());
    }

    /**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
     */
    public void deleteActivities(String list, String comment,UserSessionData userSessionData) throws Exception {
        MemoryLog.getInstance().log(MemoryLog.START,"deleteActivities",getClass());
        try{    
            if (userSessionData == null) {
                throw new DeslogedException("deleteActivities","session = null");
            }
            CENAPRAABM activity = CENAPRAABMUtil.getHome().create();
            StringTokenizer tokenizer = new StringTokenizer(list,",");
            while(tokenizer.hasMoreTokens()) {
                Integer activityId = new Integer(tokenizer.nextToken());
                activity.deleteActivityRegistryValues(activityId,userSessionData);
                activity.deleteActivityParticipants(activityId,userSessionData);
                activity.deleteActivity(activityId,comment,userSessionData);
            }
        } catch (Exception exception) {
        	Object[] parameters = new Object[2];
        	parameters[0] = list;
        	parameters[1] = comment;
            handler.handleException("deleteActivities",exception, userSessionData, parameters);
        } 
        MemoryLog.getInstance().log(MemoryLog.END,"deleteActivities",getClass());
    }
    
    
    /**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
     */
    public void saveActivityRegistryValues(Collection[] items, Integer registryId,
    		String observations, int[] checks,Integer note, String number, UserSessionData userSessionData) throws Exception {
        MemoryLog.getInstance().log(MemoryLog.START,"saveActivityRegistryValues",getClass());
        try{    
            if (userSessionData == null) {
                throw new DeslogedException("saveActivityRegistryValues","session = null");
            }
            CENAPRAABM activity = CENAPRAABMUtil.getHome().create();
            
            activity.deleteRegistryValues(registryId,userSessionData);
            
            for(int i = 0; i < 3; i++) {
                Iterator it = items[i].iterator();
                while(it.hasNext()) {
                    Integer item = (Integer)it.next();
                    activity.createActivityRegistryValue(item,registryId,new Integer(checks[i]),userSessionData);
                }
            }
                
            activity.updateRegistryCheck(registryId,observations,note,number,userSessionData);
            
        } catch (Exception exception) {
        	Object[] parameters = new Object[4];
        	parameters[0] = items;
        	parameters[1] = registryId;
        	parameters[2] = observations;
        	parameters[3] = checks;        	
            handler.handleException("saveActivityRegistryValues",exception, userSessionData, parameters);
        } 
        MemoryLog.getInstance().log(MemoryLog.END,"saveActivityRegistryValues",getClass());
    }

}
