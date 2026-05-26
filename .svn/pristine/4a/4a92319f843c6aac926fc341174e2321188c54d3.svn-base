/*
 * Created on 28-oct-2005
 */
package business.cenapra;


import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;

import persistence.cenapra.CENAPRAReport;
import persistence.cenapra.CENAPRAReportUtil;
import business.util.ExceptionHandler;
import business.util.MemoryLog;

import communication.administration.user.UserSessionData;
import communication.cenapra.tables.ActivityData;
import communication.cenapra.tables.ParticipantData;
import communication.cenapra.tables.ParticipantRegistryData;
import communication.exception.DeslogedException;
import communication.exception.InvalidDataException;
import communication.language.Text;
import communication.util.ListResult;
 
/**
 * @ejb.bean name = "CENAPRAReportFacade" jndi-name = "ejb/CENAPRAReportFacade" type =
 *           "Stateless" transaction-type = "Container"
 * 
 * @ejb.ejb-ref ejb-name = "CENAPRAReport" 
 * 				ref-name = "ejb/CENAPRAReport" 
 * 				view-type = "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/CENAPRAReport" ref-name = "CENAPRAReport"
 * 
 * @ejb.ejb-ref ejb-name = "ActivityReport" 
 *              ref-name = "ejb/ActivityReport" 
 *              view-type = "remote"  
 * @jboss.ejb-ref-jndi jndi-name = "ejb/ActivityReport" 
 * 				ref-name = "ActivityReport"
 *
 * @ejb.util generate="physical"
 */
public abstract class CENAPRAReportFacadeBean implements SessionBean {

    private ExceptionHandler handler = new ExceptionHandler(CENAPRAReportFacadeBean.class);

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
	 */
	public void ejbCreate() throws CreateException {

	}

	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
	 */
	public ListResult findList(Hashtable parameters, UserSessionData userSessionData) throws Exception {
        MemoryLog.getInstance().log(MemoryLog.START,"findList",getClass());
        ListResult returnListResult = new ListResult(new LinkedList(),0);
        try{
            CENAPRAReport report = CENAPRAReportUtil.getHome().create();
            int first = 0;
            int count = Integer.parseInt((String)parameters.get("pageSize"));
            if(((Boolean)parameters.get("all")).booleanValue()) {
                count=-1;
            }else{
                first=(Integer.parseInt((String)parameters.get("pageNum"))-1) * Integer.parseInt((String)parameters.get("pageSize"));
            }
	        returnListResult = report.findList(parameters,first,count,userSessionData);
        } catch (Exception e) {
        	Object[] parameters2 = new Object[1];
        	parameters2[0] = parameters;
            handler.handleException("findList",e,userSessionData,parameters2);
        }
        MemoryLog.getInstance().log(MemoryLog.END,"findList",getClass());
        return returnListResult;
	}
	
    /**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
	 */
	public ParticipantData findParticipantByPrimaryKey(Integer id,UserSessionData userSessionData) throws Exception{
        MemoryLog.getInstance().log(MemoryLog.START,"findParticipantByPrimaryKey",getClass());
        ParticipantData data=null;
        try{
            if (id == null) {
                throw new InvalidDataException("findParticipantByPrimaryKey","id = null");
            }
            CENAPRAReport report = CENAPRAReportUtil.getHome().create();
            data = report.findParticipantByPrimaryKey(id,userSessionData);
        } catch(Exception e){
        	Object[] parameters = new Object[1];
        	parameters[0] = id;
            handler.handleException("findParticipantByPrimaryKey",e,userSessionData,parameters);
        }
        MemoryLog.getInstance().log(MemoryLog.END,"findParticipantByPrimaryKey",getClass());
		return data;
	}

    /**
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     * @ejb.interface-method
     */
    public Collection findParticipants(String list,UserSessionData userSessionData) throws Exception{
        Collection result = null;
        try{
            CENAPRAReport report = CENAPRAReportUtil.getHome().create();
            result = report.findParticipants(list,userSessionData);
        } catch(Exception e){  
        	Object[] parameters = new Object[1];
        	parameters[0] = list;
            handler.handleException("findParticipants",e,userSessionData,parameters);
        }
        return result;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     */
    public Object[][] getParticipantHistory(Integer id,UserSessionData userSessionData) throws Exception{
        MemoryLog.getInstance().log(MemoryLog.START,"getParticipantHistory",getClass());
        Object[][] data = null;
        try{
            if (id == null) {
                throw new InvalidDataException("getParticipantHistory","id = null");
            }
            CENAPRAReport report = CENAPRAReportUtil.getHome().create();
            data = report.getParticipantHistory(id,userSessionData);
        } catch(Exception e){
        	Object[] parameters = new Object[1];
        	parameters[0] = id;
            handler.handleException("getParticipantHistory",e,userSessionData,parameters);
        }
        MemoryLog.getInstance().log(MemoryLog.END,"getParticipantHistory",getClass());
        return data;
    }

    /**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     */
    public ParticipantRegistryData getParticipantRegistry(Integer registryId,UserSessionData userSessionData) throws Exception {
        MemoryLog.getInstance().log(MemoryLog.START,"getParticipantRegistry",getClass());
        ParticipantRegistryData registry = null;
        try{    
            if (userSessionData == null) {
                throw new DeslogedException("getParticipantRegistry","session = null");
            }
            CENAPRAReport report = CENAPRAReportUtil.getHome().create();
            registry = report.getParticipantRegistry(registryId,userSessionData);
        } catch (Exception exception) {
        	Object [] parameters = new Object [1];
        	parameters[0] = registryId;
            handler.handleException("getParticipantRegistry",exception, userSessionData, parameters);
        } 
        MemoryLog.getInstance().log(MemoryLog.END,"getParticipantRegistry",getClass());
        return registry;
    }

    /**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     */
    public Collection getActivityParticipants(Integer activity,UserSessionData userSessionData) throws Exception {
        MemoryLog.getInstance().log(MemoryLog.START,"getActivityParticipants",getClass());
        Collection participants = new LinkedList();
        try{    
            if (userSessionData == null) {
                throw new DeslogedException("getActivityParticipants","session = null");
            }
            CENAPRAReport report = CENAPRAReportUtil.getHome().create();
            participants = report.getActivityParticipants(activity,userSessionData);
        } catch (Exception exception) {
        	Object[] parameters = new Object[1];
        	parameters[0] = activity;
            handler.handleException("getActivityParticipants",exception, userSessionData, parameters);
        } 
        MemoryLog.getInstance().log(MemoryLog.END,"getActivityParticipants",getClass());
        return participants;
    }

    /**
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     * @ejb.interface-method
     */
    public ListResult findActivityList(String startDay,String startMonth,String startYear,
    		String endDay,String endMonth,String endYear,String type,String country,
    		String city,String state,String folio,int first,int count,UserSessionData userSessionData) throws Exception{
        ListResult result = null;
        try{
            CENAPRAReport activityReport = CENAPRAReportUtil.getHome().create();
            result = activityReport.findActivityList(startDay,startMonth,startYear,endDay,endMonth,endYear,type,country,city,state,folio,first,count,userSessionData);
        } catch(Exception e){           
        	Object[] parameters = new Object[12];
        	parameters[0] = startDay;
        	parameters[1] = startMonth;
        	parameters[2] = startYear;
        	parameters[3] = endDay;
        	parameters[4] = endMonth;
        	parameters[5] = endYear;
        	parameters[6] = type;
        	parameters[7] = country;
        	parameters[8] = city;
        	parameters[9] = state;
        	parameters[10] = first;
        	parameters[11] = count;
        	
            handler.handleException("findActivityList",e,userSessionData,parameters);
        }
        return result;
    }
    
    /**
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     * @ejb.interface-method
     */
    public ActivityData getActivity(Integer activity,UserSessionData userSessionData) throws Exception {
        ActivityData data = null;
        try{
            CENAPRAReport activityReport = CENAPRAReportUtil.getHome().create();
            data = activityReport.getActivity(activity,userSessionData);
        } catch(Exception e){           
        	Object[] parameters = new Object[1];
        	parameters[0] =activity;
            handler.handleException("getActivity",e,userSessionData,parameters);
        }
        return data;
    }

    /**
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     * @ejb.interface-method
     */
    public Collection findActivities(String list,UserSessionData userSessionData) throws Exception{
        Collection result = null;
        try{
            CENAPRAReport activityReport = CENAPRAReportUtil.getHome().create();
            result = activityReport.findActivities(list,userSessionData);
        } catch(Exception e){   
        	Object[] parameters = new Object[1];
        	parameters[0] = list;
            handler.handleException("findActivities",e,userSessionData,parameters);
        }
        return result;
    }
    
    /**
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     * @ejb.interface-method
     */
    public Collection getRegistryItems(Integer registry, Integer check, UserSessionData userSessionData) throws Exception {
        try{
            CENAPRAReport activityReport = CENAPRAReportUtil.getHome().create();
            return activityReport.getRegistryItems(registry,check,userSessionData);
        } catch(Exception e){           
        	Object[] parameters = new Object[2];
        	parameters[0] = registry;
        	parameters[1] = check;        	
            handler.handleException("findActivities",e,userSessionData,parameters);
        }
        return new LinkedList();
    }

    /**
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     * @ejb.interface-method
     */
    public Hashtable getCorrectAnswers(UserSessionData userSessionData) throws Exception {
        try{
            CENAPRAReport activityReport = CENAPRAReportUtil.getHome().create();
            return activityReport.getCorrectAnswers(userSessionData);
        } catch(Exception e){           
            handler.handleException("findActivities",e,userSessionData,null);
        }
        return new Hashtable();
    }

    /**
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     * @ejb.interface-method
     */
    public ListResult getParticipantList(String firstName,String lastName,String activity, String divorg,
    		boolean all,int first,int count,UserSessionData userSessionData) throws Exception {
        
    	try{
        
        	CENAPRAReport activityReport = CENAPRAReportUtil.getHome().create();
            return activityReport.getParticipantList(firstName,lastName,activity,divorg,all,first,count,userSessionData);
        
        } catch(Exception e){
        	Object[] parameters = new Object[6];
        	parameters[0] = firstName;
        	parameters[1] = lastName;
        	parameters[2] = activity;
        	parameters[3] = all;
        	parameters[4] = first;
        	parameters[5] = count;        	
            handler.handleException("getParticipantResult",e,userSessionData,parameters);
        }
        return null;
    }

    /**
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     * @ejb.interface-method
     */
    public ListResult getParticipantResult(String firstName,String lastName,
    		Collection activities,Date from,Date to,boolean all,int checkListType,
    		int first,int count,UserSessionData userSessionData) throws Exception {
        
    	try{
        
        	CENAPRAReport activityReport = CENAPRAReportUtil.getHome().create();
            return activityReport.getParticipantResult(firstName,lastName,activities,from,to,all,checkListType,first,count,userSessionData);
        
        } catch(Exception e){    
        	Object[] parameters = new Object[9];
        	parameters[0] = firstName;
        	parameters[1] = lastName;
        	parameters[2] = activities;
        	parameters[3] = from;
        	parameters[4] = to;
        	parameters[5] = all;
        	parameters[6] = checkListType;
        	parameters[7] = first;
        	parameters[8] = count;
            handler.handleException("getParticipantResult",e,userSessionData,parameters);
        }
        return null;
    }

    /**
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     * @ejb.interface-method
     */
    public Collection getCorrectAnswers(String ids, UserSessionData userSessionData) throws Exception {
        try{
            CENAPRAReport activityReport = CENAPRAReportUtil.getHome().create();
            return activityReport.getAnswers(ids,userSessionData);
        } catch(Exception e){     
        	Object[] parameters = new Object[1];
        	parameters[0] = ids;
            handler.handleException("getCorrectAnswers",e,userSessionData,parameters);
        }
        return null;
    }

    /**
     * @ejb.permission role-name = "administrator,systemaccess"
     * @ejb.interface-method
     */
    public Collection getActivityResume(UserSessionData userSessionData,Text messages) throws Exception {
        try{
            CENAPRAReport activityReport = CENAPRAReportUtil.getHome().create();
            return activityReport.getActivityResume(userSessionData,messages);            
        } catch(Exception e){           
        	Object[]p =new Object[1];
        	p[0] = messages;        	
            handler.handleException("getActivityResume",e,userSessionData,p);
        }
        return null;
    }
   
}