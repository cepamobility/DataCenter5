package business.activity;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;

import org.apache.log4j.Logger;
import org.json.JSONArray;

import persistence.activity.ActivityReport;
import persistence.activity.ActivityReportHome;
import persistence.activity.ActivityReportUtil;
import persistence.driver.DriverReportUtil;
import persistence.log.LoggerFormatting;
import business.util.ExceptionHandler;
import business.util.MemoryLog;
import communication.activity.report.ActivityAssesmentData;
import communication.activity.report.ActivityDeliveredData;
import communication.activity.report.ActivityExpiryData;
import communication.activity.report.ActivityReportData;
import communication.activity.report.ActivityReportGTData;
import communication.activity.report.ActivityReportTabletData;
import communication.activity.report.ActivityReportTabletDataType;
import communication.activity.report.AllActivitiesGeneralReportData;
import communication.activity.report.DriversBTWAssistData;
import communication.activity.report.DriversWithoutBTWData;
import communication.activity.report.DriversWithoutTOJData;
import communication.activity.report.HeavyReportData;
import communication.activity.tables.ActivityAttendanceData;
import communication.activity.tables.ActivityData;
import communication.activity.tables.ActivityObservationData;
import communication.activity.tables.ActivityRegistryData;
import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.activity.tables.CheckListData;
import communication.activity.tables.DriverMaxExpiry;
import communication.activity.tables.InstructorActivity;
import communication.activity.tables.ItemData;
import communication.activity.tables.PointData;
import communication.activity.tables.PointObservationData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.administration.user.tables.UserData;
import communication.driver.tables.DriverData;
import communication.exception.CommunicationProblemException;
import communication.exception.DeslogedException;
import communication.exception.InvalidDataException;
import communication.language.Text;
import communication.security.SecurityConstants;
import communication.util.ListResult;

/**
 * @ejb.bean name="ActivityReportFacade"
 *           display-name="Name for ActivityReportFacade"
 *           description="Description for ActivityReportFacade"
 *           jndi-name="ejb/ActivityReportFacade"
 *           type="Stateless"
 *           view-type="remote"
 *           
 * @ejb.ejb-ref ejb-name = "ActivityReport" 
 *               ref-name = "ejb/ActivityReport"
 *               view-type ="remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/ActivityReport"
 *                      ref-name = "ActivityReport"
 *           
 * @ejb.ejb-ref ejb-name = "DriverReport" 
 *               ref-name = "ejb/DriverReport"
 *               view-type ="remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/DriverReport"
 *                      ref-name = "DriverReport"
 *           
 * @ejb.util generate="physical"
 * 
 */
public abstract class ActivityReportFacadeBean implements SessionBean {
    
	private Logger logger=Logger.getLogger(ActivityReportFacadeBean.class);
    private LoggerFormatting logFormatting=new LoggerFormatting();
    ExceptionHandler handler = new ExceptionHandler(ActivityReportFacadeBean.class);
	/**
	 * @ejb.permission role-name = "administrator,cepascheduleactivityviewlist,cepascheduleactivityview,cepascheduleactivityedit,cepascheduleactivitycreate,cepascheduleactivityupdate,cepascheduleactivityregistrycreate,cepascheduleactivityregistryupdate,driverviewowndata,imeseviactivityview,eventcreate,eventview,jjreporter,dpachecklist"
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
	}
	
    /**
     * @ejb.permission role-name = "cepascheduleactivityviewlist,cepascheduleactivityview,administrator,dpachecklist"
     * @ejb.interface-method
     */
    public ListResult findActivityList(String startDay,String startMonth,
    		String startYear,String endDay,String endMonth,String endYear,
    		String type,String country,String city,String state,int first,
    		int count,UserSessionData userSessionData) throws Exception{
        
    	ListResult result = null;
        
    	try{
        
        	ActivityReport activityReport = ActivityReportUtil.getHome().create();
            result = activityReport.findActivityList(startDay,startMonth,startYear,endDay,endMonth,endYear,type,country,city,state,first,count,userSessionData);
        
        } catch(Exception e){           
        	Object [] parameters = new Object[12];
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
            handler.handleException("findActivityList",e, userSessionData, parameters);
        }
        return result;
    }

	/**
     * @ejb.permission role-name = "cepascheduleactivityviewlist,cepascheduleactivityview,administrator"
	 * @throws InvalidDataException
	 * @throws DeslogedException
	 * @throws CommunicationProblemException
	 * @ejb.interface-method
	 */
	public ListResult findCEPAScheduleActivityList(String attribute, String value,
			int first,int count,UserSessionData userSessionData)
		throws Exception{
		
		try{
			
			if (attribute == null) {
				throw new InvalidDataException("findCEPAScheduleActivityList", "attribute == null");
			}
			ActivityReportHome activityHome = ActivityReportUtil.getHome();
			ActivityReport activityReport = activityHome.create();
			return activityReport.findCEPAScheduleActivityList(value,userSessionData,getAttribute(attribute,userSessionData),first,count);
		
		} catch(Exception e){			
			Object[] parameters = new Object[4];
			parameters[0] = attribute;
			parameters[1] = value;
			parameters[2] = first;
			parameters[3] = count;
			handler.handleException("findCEPAScheduleActivityList",e, userSessionData,parameters);
		}
		return null;
	}
	
	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityview,cepascheduleactivitycreate,cepascheduleactivityupdate,cepascheduleactivitydelete,cepascheduleactivityregistrycreate,cepascheduleactivityregistryupdate,cepascheduleactivityregistrydelete,cdactivitycreate,cdactivitymodify,driverviewowndata"
	 * 
	 * @param userPK
	 * @return  UserData
	 * @throws Exception 	 
	 */
	public CEPAActivityScheduleData findCEPAActivityScheduleByPrimaryKey(Integer id, UserSessionData userSessionData)throws Exception {
		return findCEPAActivityScheduleByPrimaryKey(id,true,userSessionData);
	}
	
	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityview,cepascheduleactivitycreate,cepascheduleactivityupdate,cepascheduleactivitydelete,cepascheduleactivityregistrycreate,cepascheduleactivityregistryupdate,cepascheduleactivityregistrydelete,cdactivitycreate,cdactivitymodify,driverviewowndata"
	 * 
	 * @param userPK
	 * @return  UserData
	 * @throws Exception 	 
	 */
	public CEPAActivityScheduleData findCEPAActivityScheduleByPrimaryKey(Integer id, boolean restrictions, UserSessionData userSessionData)throws Exception {
		CEPAActivityScheduleData data=null;
		try{
			//Check data
		    if (id != null){
				//UserReport access
				ActivityReportHome home = ActivityReportUtil.getHome();
				ActivityReport activityReport = home.create();
				data = activityReport.findCEPAActivityScheduleByPrimaryKey(id,restrictions,userSessionData);
			}
			return data;
		} catch(Exception e){
			Object [] parameters = new Object[1];
			parameters[0] = id; 
			handler.handleException("findCEPAActivityScheduleByPrimaryKey",e, userSessionData, parameters);
		}
		return data;
	}
	
	/**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityview,cepascheduleactivitycreate,cepascheduleactivityupdate,cepascheduleactivitydelete,cepascheduleactivityregistrycreate,cepascheduleactivityregistryupdate,cepascheduleactivityregistrydelete,driverviewowndata,eventcreate,eventview,jjreporter"
     * @throws InvalidDataException
     * @throws Exception
     */
    public Collection<CEPAActivityScheduleRegistryData> findActivityByDriver(Integer driverId,UserSessionData userSessionData,boolean dictated) throws Exception {
        try{
            if (driverId == null) {
                throw new InvalidDataException("findActivityByDriver","id = null");
            }
            ActivityReportHome home = ActivityReportUtil.getHome();
        	ActivityReport activityReport = home.create();
            Collection<CEPAActivityScheduleRegistryData> activities = (userSessionData.isDriver()) ? activityReport.findActivityByDriver(userSessionData,userSessionData.getDriverData().getId(),dictated) : activityReport.findActivityByDriver(userSessionData,driverId,dictated);
            switch(userSessionData.getCorporationId()) {
            	case CorporationData.FORESTALORIENTAL:
            		activities = addProvisionals(activities,driverId,userSessionData);
            		break;
            	case CorporationData.FORESTALES:
                    if(userSessionData.getCorporationId() == CorporationData.FORESTALES) {
                    	Integer[] links = DriverReportUtil.getHome().create().getDriverLink(driverId, userSessionData);
                    	for(int i = 0; i < links.length; i++) {
                    		if(links[i] != null) {
                    			activities.addAll(activityReport.findActivityByDriverForestales(userSessionData, links[i], dictated, i, driverId));
                    			if(i == 0) {
                            		activities = addProvisionals(activities,links[i],userSessionData);
                    			}
                    		}
                    	}
                    }
            		break;
            }
            return activities;
        } catch (Exception e) {
        	Object [] parameters = new Object[2];
        	parameters[0] = driverId;
        	parameters[1] = dictated;
            handler.handleException("findActivityByDriver",e, userSessionData, parameters);
        }
        return null;
    }    
	
	
    private Collection<CEPAActivityScheduleRegistryData> addProvisionals(Collection<CEPAActivityScheduleRegistryData> activities,Integer driverId, UserSessionData userSessionData) throws Exception {
    	Object[][] provisionals = DriverReportUtil.getHome().create().getProvisionalBTW(driverId, userSessionData);
    	for(int i = 0; i < provisionals.length; i++) {
    		boolean exists = false;
    		Iterator<CEPAActivityScheduleRegistryData> itActivities = activities.iterator();
    		while(itActivities.hasNext()) {
				CEPAActivityScheduleRegistryData activity = itActivities.next();
				String type = activity.getActivity().getType().getResourceBundleId();
    			if(type.equals(CEPAActivityConstants.PESADOS_1) ||
    					type.equals(CEPAActivityConstants.BTW3UPDATE) ||
    					type.equals(CEPAActivityConstants.BTW3RENOVACION) ||
    					type.equals(CEPAActivityConstants.BTW3)) {
    				if(dateEquals(activity.getDate(),(Date)provisionals[i][0])) {
    					exists = true;
    				}
    			}
    		}
    		if(!exists) {
    			activities.add(new CEPAActivityScheduleRegistryData(driverId,(Date)provisionals[i][0],(String)provisionals[i][1],null,CEPAActivityConstants.PROVISIONAL_BTW));
    		}
    	}
    	return activities;
    }
	
	private boolean dateEquals(Date date1, Date date2) {
		Calendar d1 = Calendar.getInstance();
		d1.setTime(date1);
		Calendar d2 = Calendar.getInstance();
		d2.setTime(date2);
		return (d1.get(Calendar.YEAR) == d2.get(Calendar.YEAR) && d1.get(Calendar.MONTH) == d2.get(Calendar.MONTH) && d1.get(Calendar.DATE) == d2.get(Calendar.DATE));
	}

	private int getAttribute(String attrName,UserSessionData userSessionData) 
		throws Exception{
		try{
			if (attrName.equals("date")) {
				return ActivityData.DATE;
			}
			
			if (attrName.equals("type")) {
				return ActivityData.TYPE;
			}
			
			if (attrName.equals("state")) {
				return ActivityData.STATE;
			}			
			
		
		} catch(Exception e){
			Object[] parameters = new Object[1];
			parameters[0] = attrName;
			handler.handleException("getAttribute",e, userSessionData, parameters);
		}
		return ActivityData.DATE;
	}
		

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "vehicleupdate,administrator,cepascheduleactivityupdate,cepascheduleactivitydelete,cdactivitymodify,cdactivitydelete"
     */
    public List getActivities(String activities,UserSessionData userSessionData)
        throws Exception{
        
        try{
                       
        //UserReport access
        	ActivityReportHome home = ActivityReportUtil.getHome();
        	ActivityReport activityReport = home.create();
            List list = activityReport.getActivities(activities,userSessionData);
            return list;
            
        } catch (Exception e) {
        	Object [] parameters = new Object[1];
        	parameters[1] = activities;
        	handler.handleException("getActivities",e, userSessionData, parameters);
        }
        return null;
    }
    
    
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityview"
     * Advanced search of drivers, includes the most important parameters to find
     * @param activity - type of activity
     * @param first - first index of the search to return
     * @param count - number of elements to return
     * @throws InvalidDataException
     * @throws CommunicationProblemException
     * @throws DeslogedException
     */ 
    public ListResult simpleSearch(Date initialDate,Date lastDate,String type,
    		String country,String city,String state,int first,int count,
    		UserSessionData userSessionData)
        throws Exception {
        
    	try {
        	
    		ActivityReportHome activityHome = ActivityReportUtil.getHome();
        	ActivityReport activityReport = activityHome.create(); 
            return activityReport.simpleSearch(userSessionData,initialDate,
            		lastDate,type,country,city,state,first,count);
       
        } catch (Exception e) {
        	Object[] parameters = new Object[8];
        	parameters[0] = initialDate;
        	parameters[1] = lastDate;
        	parameters[2] = type;
        	parameters[3] = country;
        	parameters[4] = city;
        	parameters[5] = state;
        	parameters[6] = first;
        	parameters[7] = count;        	
        	handler.handleException("simpleSearch",e, userSessionData, parameters);
        }
        return null;
    }	
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityupdate,cdactivitymodify"
     */
    public Collection<ActivityObservationData> getObservations(Integer activityId,UserSessionData userSessionData)
        throws Exception{
        
        try{
                       
        	//UserReport access
        	ActivityReportHome home = ActivityReportUtil.getHome();
        	ActivityReport activityReport = home.create();
            return activityReport.getObservations(activityId,userSessionData);
            
        } catch(Exception e){
        	Object [] parameters = new Object[1];
        	parameters[0] = activityId;
        	handler.handleException("getObservations",e,userSessionData,parameters);
        }
        return null;
    }
        	
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityupdate,cepascheduleactivityregistrycreate,cepascheduleactivityregistryupdate,cdactivitycreate,cdactivitymodify"
     */
    public ItemData getFormItem(String pointText,String itemText,UserSessionData userSessionData)
        throws Exception{
        
        try{
        	//UserReport access
        	ActivityReportHome home = ActivityReportUtil.getHome();
        	ActivityReport activityReport = home.create();
            return activityReport.getFormItem(pointText,itemText,userSessionData);

        } catch(Exception e){
        	Object[] parameters = new Object[2];
        	parameters[0] = pointText;
        	parameters[1] = itemText;
        	handler.handleException("getFormItem",e,userSessionData, parameters);
        }
        return null;
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityupdate,cepascheduleactivityregistrycreate,cepascheduleactivityregistryupdate,cdactivitycreate,cdactivitymodify"
     */
    public ItemData getItemById(Integer idItem, UserSessionData userSessionData)
        throws Exception{
        
        try{
        	//UserReport access
        	ActivityReportHome home = ActivityReportUtil.getHome();
        	ActivityReport activityReport = home.create();
            return activityReport.getItemById(idItem,userSessionData);

        } catch(Exception e){
        	Object[] parameters = new Object[1];
        	parameters[0] = idItem;        	
        	handler.handleException("getItemById",e,userSessionData, parameters);
        }
        return null;
    }

    /**
     * Devuelve el PointObservationData dado el idObservation
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityupdate,cepascheduleactivityregistrycreate,cepascheduleactivityregistryupdate,cdactivitycreate,cdactivitymodify"
     */
    public PointObservationData getObservationPointById(Integer idObservationPoint, UserSessionData userSessionData)
        throws Exception{
        
        try{
        	//UserReport access
        	ActivityReportHome home = ActivityReportUtil.getHome();
        	ActivityReport activityReport = home.create();
            return activityReport.getObservationPointById(idObservationPoint,userSessionData);

        } catch(Exception e){
        	Object[] parameters = new Object[1];
        	parameters[0] = idObservationPoint;        	
        	handler.handleException("getObservationPointById",e,userSessionData, parameters);
        }
        return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityupdate,cepascheduleactivityview,cepascheduleactivityregistrycreate,cepascheduleactivityregistryupdate,cdactivitycreate,cdactivitymodify"
     */
    public Collection<DriverData> getParticipants(Integer activityId, UserSessionData userSessionData) 
    	throws Exception{
        try{
                       
        //UserReport access
        	ActivityReportHome home = ActivityReportUtil.getHome();
        	ActivityReport activityReport = home.create();
            return activityReport.getParticipants(activityId,userSessionData);
            
        } catch(Exception e){
        	Object[] parameters = new Object[1];
        	parameters[0] = activityId;
        	handler.handleException("getParticipants",e, userSessionData, parameters);
        }
        return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityupdate,cepascheduleactivityview,cepascheduleactivityregistrycreate,cepascheduleactivityregistryupdate,cdactivitycreate,cdactivitymodify"
     */
    public Integer getParticipantCount(Integer activity, boolean participants,UserSessionData userSessionData) throws Exception{
        Integer value = new Integer(0);
        try{
            ActivityReport activityReport = ActivityReportUtil.getHome().create();
            value = activityReport.getParticipantCount(activity,participants,userSessionData);
        } catch(Exception e){
        	Object[] parameters = new Object[1];
        	parameters[0] = activity;
            handler.handleException("getParticipantCount",e, userSessionData, parameters);
        }
        return value;
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityupdate,cepascheduleactivityregistrycreate,cepascheduleactivityregistryupdate,cdactivitycreate,cdactivitymodify"
     */
    public Collection getItemsResult(Integer activityId, Integer driverId, 
    		UserSessionData userSessionData) throws Exception{
        try{
            
            //UserReport access
            	ActivityReportHome home = ActivityReportUtil.getHome();
            	ActivityReport activityReport = home.create();
                return activityReport.getItemsResult(activityId,driverId,userSessionData);
                
            } catch(Exception e){
            	Object [] parameters = new Object[2];
            	parameters[0] = activityId;
            	parameters[1] = driverId;
            	handler.handleException("getItemsResult",e, userSessionData,parameters);
            }return null;
        }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityupdate,cepascheduleactivityregistrycreate,cepascheduleactivityregistryupdate,cepascheduleactivityview,cdactivitycreate,cdactivitymodify"
     */
    public boolean hasResult(Integer activityId, Integer driverId, UserSessionData userSessionData) 
    	throws Exception{    
        try{
            
            //UserReport access
            	ActivityReportHome home = ActivityReportUtil.getHome();
            	ActivityReport activityReport = home.create();
                return activityReport.hasResult(activityId,driverId,userSessionData);
            
        	} catch(Exception e){
        		Object[] parameters = new Object[2];
        		parameters[0] = activityId;
        		parameters[1] = driverId;
            	handler.handleException("hasResult",e,userSessionData,parameters);
            }
        return false;
        
        }
       
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityupdate,cdactivitymodify"
     */
    public boolean existsAnyResultForActivity(Integer activityId, UserSessionData userSessionData)
    	throws Exception{    
        try{
            
            //UserReport access
            	ActivityReportHome home = ActivityReportUtil.getHome();
            	ActivityReport activityReport = home.create();
                return activityReport.existsAnyResultForActivity(activityId,userSessionData);
                
            } catch(Exception e){
            	Object[] parameters = new Object[1];
            	parameters[0] = activityId;
            	handler.handleException("existsAnyResultForActivity",e,userSessionData,parameters);
            } return false;
        }
        
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityupdate,cdactivitymodify"
     */
    public boolean existsAnyDriverWithOutResultForActivity(Integer activityId, 
    		UserSessionData userSessionData) throws Exception{    
        try{
            
            //UserReport access
            	ActivityReportHome home = ActivityReportUtil.getHome();
            	ActivityReport activityReport = home.create();
                return activityReport.existsAnyDriverWithOutResultForActivity(activityId,userSessionData);
                
           } catch(Exception e){
        	   	Object[] parameters = new Object[1];
        	   	parameters[0] = activityId;
            	handler.handleException("existsAnyDriverWithOutResultForActivity",e,userSessionData,parameters);
            }
           return false;
        }
   
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityupdate,cdactivitymodify"
     */
    public boolean existsAnyTemporalDriverForActivity(Integer activityId, 
    		UserSessionData userSessionData) throws Exception{    
        try{
            
            //UserReport access
            	ActivityReportHome home = ActivityReportUtil.getHome();
            	ActivityReport activityReport = home.create();
                return activityReport.existsAnyTemporalDriverForActivity(activityId,userSessionData);
                
           } catch(Exception e){
        	   	Object[] parameters = new Object[1];
        	   	parameters[0] = activityId;
            	handler.handleException("existsAnyTemporalDriverForActivity",e,userSessionData,parameters);
            }
           return false;
        }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityupdate,cdactivitymodify"
     */
    public boolean existsCeroOrOneDriverWithOutResultForActivity(Integer activityId, 
    		UserSessionData userSessionData,Integer idDriver) throws Exception{    
        try{
            
            //UserReport access
            	ActivityReportHome home = ActivityReportUtil.getHome();
            	ActivityReport activityReport = home.create();
                return activityReport.existsCeroOrOneDriverWithOutResultForActivity(activityId,userSessionData,idDriver);
                
        } catch (Exception e) {
        	Object[] parameters = new Object[2];
        	parameters[0] = activityId;
        	parameters[1] = idDriver;
        	handler.handleException("existsCeroOrOneDriverWithOutResultForActivity",e,userSessionData,parameters);
        }
        return false;
   
    }
    
        
    
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityupdate,cepascheduleactivityregistrycreate,cepascheduleactivityregistryupdate,cepascheduleactivityregistryview,cepascheduleactivityview,cdactivitycreate,cdactivitymodify,driverviewowndata,jjreporter"
     */
    public CEPAActivityScheduleRegistryData getCompleteResult(Integer activityId,Integer driverId, UserSessionData userSessionData) throws Exception{  
    	try{
          	ActivityReportHome home = ActivityReportUtil.getHome();
           	ActivityReport activityReport = home.create();
            return activityReport.getCompleteResult(activityId,driverId,null,userSessionData);
        } catch (Exception e) {
        	Object[] parameters = new Object[2]; 
        	parameters[0] = activityId;
        	parameters[1] = driverId;
           	handler.handleException("getCompleteResult",e,userSessionData,parameters);
        }
        return null;
    }  
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityupdate,cepascheduleactivityregistrycreate,cepascheduleactivityregistryupdate,cepascheduleactivityregistryview,cepascheduleactivityview,cdactivitycreate,cdactivitymodify,driverviewowndata,jjreporter"
     */
    public CEPAActivityScheduleRegistryData getCompleteResult(Integer activityId,Integer driverId, Text messages, UserSessionData userSessionData) throws Exception{  
    	try{
          	ActivityReportHome home = ActivityReportUtil.getHome();
           	ActivityReport activityReport = home.create();
            return activityReport.getCompleteResult(activityId,driverId,messages, userSessionData);
        } catch (Exception e) {
        	Object[] parameters = new Object[2]; 
        	parameters[0] = activityId;
        	parameters[1] = driverId;
           	handler.handleException("getCompleteResult",e,userSessionData,parameters);
        }
        return null;
    }  
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivitycreate,cepascheduleactivityupdate,cepascheduleactivityregistrycreate,cepascheduleactivityregistryupdate,cdactivitycreate,cdactivitymodify"
     */
    public boolean existsRegistry(Integer activityId, Integer driverId,UserSessionData userSessionData)
    	throws Exception {    
        try{
            
            //UserReport access
            	ActivityReportHome home = ActivityReportUtil.getHome();
            	ActivityReport activityReport = home.create();
                return activityReport.existsRegistry(activityId,driverId,userSessionData);                
                
            } catch(Exception e){
            	Object[] parameters = new Object[2];
            	parameters[0] = activityId;
            	parameters[1] = driverId;
            	handler.handleException("existsRegistry",e,userSessionData,parameters);
            }
            return false;
    }   
  
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivitycreate,cepascheduleactivityupdate,cepascheduleactivityregistrycreate,cepascheduleactivityregistryupdate,cdactivitycreate,cdactivitymodify"
     */
    public CEPAActivityScheduleRegistryData getRegistry(Integer activityId, Integer driverId,
    		UserSessionData userSessionData) throws Exception {
    	
        try{
            
            //UserReport access
            	ActivityReportHome home = ActivityReportUtil.getHome();
            	ActivityReport activityReport = home.create();
                return activityReport.getRegistry(activityId,driverId,userSessionData);
                
            } catch(Exception e){
            	Object[] parameters = new Object[2];
            	parameters[0] = activityId;
            	parameters[1] = driverId;
            	handler.handleException("getRegistry",e,userSessionData,parameters);
            }return null;
    }   
      
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityview,cepascheduleactivityregistryview,driverviewowndata"
     */
    
    public ActivityReportData getReportActivity(Integer activityId,Integer driverId,
    		int first, int count, UserSessionData userSessionData) throws Exception {
        try{
        	ActivityReportHome home = ActivityReportUtil.getHome();
        	ActivityReport activityReport = home.create();
            return new ActivityReportData(activityReport.getReportActivity(activityId,driverId,first,count,userSessionData));
        }
        catch (Exception e) {
        	Object[] parameters = new Object[4];
        	parameters[0] = activityId;
        	parameters[1] = driverId;
        	parameters[2] = first;
        	parameters[3] = count;
        	handler.handleException("getReportActivity",e,userSessionData,parameters);
        }
        return null;
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityview,cepascheduleactivityregistryview,driverviewowndata"
     */
    
    public ActivityReportTabletData getReportTabletActivity(Integer activityId,Integer driverId,
    		int first, int count, Text messages, UserSessionData userSessionData) throws Exception {
        try{
        	ActivityReportHome home = ActivityReportUtil.getHome();
        	ActivityReport activityReport = home.create();
            return new ActivityReportTabletData(activityReport.getReportTabletActivity(
            		activityId,driverId,first,count,messages,userSessionData));
        }
        catch (Exception e) {
        	Object[] parameters = new Object[4];
        	parameters[0] = activityId;
        	parameters[1] = driverId;
        	parameters[2] = first;
        	parameters[3] = count;
        	parameters[4] = messages;
        	handler.handleException("getReportActivity",e,userSessionData,parameters);
        }
        return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityview,cepascheduleactivityregistryview,driverviewowndata"
     */
    
    public ActivityReportTabletDataType getReportTabletActivity(Integer activityId,Integer driverId,Text messages, UserSessionData userSessionData) throws Exception {
        try{
        	ActivityReportHome home = ActivityReportUtil.getHome();
        	ActivityReport activityReport = home.create();
            return activityReport.getReportTabletActivity(activityId,driverId,0,-1,messages,userSessionData);
        }
        catch (Exception e) {
        	Object[] parameters = {activityId,driverId,messages};
        	handler.handleException("getReportActivity",e,userSessionData,parameters);
        }
        return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,activityreport"
     */
 /*   public ActivityReportGTData getReportGeneralTraining(String periodFrom, Integer yearFrom, String periodTo, Integer yearTo, Integer[][] divOrgs, Integer[][] divGeos, int first, int count, UserSessionData userSessionData) throws Exception {
        try{            
        	ActivityReportHome home = ActivityReportUtil.getHome();
        	ActivityReport activityReport = home.create();
            return new ActivityReportGTData(activityReport.getReportGeneralTraining(periodFrom, yearFrom, periodTo, yearTo,  divOrgs, divGeos, first, count, userSessionData));
        } catch(Exception e){
            handler.handleException("getReportGeneralTraining",e,userSessionData,parameters);
        }
        return null;
    }
   */ 
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityview"
     * Advanced search of drivers, includes the most important parameters to find
     * @param activity - type of activity
     * @param first - first index of the search to return
     * @param count - number of elements to return
     * @throws InvalidDataException
     * @throws CommunicationProblemException
     * @throws DeslogedException
     */ 
    public ActivityReportGTData getReportGeneralTraining(UserSessionData userSessionData,
    		Date initialDate,Date lastDate,String type,String state, Integer[][] divOrgs, 
    		Integer[][] divGeos,int first,int count)
        throws  Exception {
        try {
        	ActivityReportHome activityHome = ActivityReportUtil.getHome();
        	ActivityReport activityReport = activityHome.create(); 
            return new ActivityReportGTData(activityReport.getReportGeneralTraining(userSessionData,initialDate,lastDate,type,state,divOrgs,divGeos,first,count));
        } catch (Exception e) {
        	Object[] parameters = new Object[8];
        	parameters[0] = initialDate;
        	parameters[1] = lastDate;
        	parameters[2] = type;
        	parameters[3] = state;
        	parameters[4] = divOrgs;
        	parameters[5] = divGeos;
        	parameters[6] = first;
        	parameters[7] = count;
        	handler.handleException("getReportGeneralTraining",e,userSessionData,parameters);
        }
        return null;
    }	
    
    
    

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,activityreport,query"
     * 
     * @param first - first index of the search to return
     * @param count - number of elements to return
     * @throws InvalidDataException
     * @throws CommunicationProblemException
     * @throws DeslogedException
     */ 
    public ListResult getReportCommentaryDrive(UserSessionData userSessionData,
    		Date initialDate,Date lastDate, Integer[][] divOrgs,Integer[][] divGeos,
    		int first,int count, String commentaryDriveType,String countCD,
    		int driverState,String vehicleAllowed, String type)throws  Exception {
        try {
            ActivityReportHome activityHome = ActivityReportUtil.getHome();
            ActivityReport activityReport = activityHome.create();     	
            return activityReport.getReportCommentaryDrive(userSessionData,initialDate,lastDate,divOrgs,divGeos,first,count,commentaryDriveType, countCD, driverState,vehicleAllowed,type);
        } catch (Exception e) {
        	Object[] parameters = new Object[11];
        	parameters[0] = initialDate;
        	parameters[1] = lastDate;
        	parameters[2] = divOrgs;
        	parameters[3] = divGeos;
        	parameters[4] = first;
        	parameters[5] = count;
        	parameters[6] = commentaryDriveType;
        	parameters[7] = countCD;
        	parameters[8] = driverState;
        	parameters[9] = vehicleAllowed;
        	parameters[10] = type;
            handler.handleException("getReportCommentaryDrive",e,userSessionData,parameters);
        }
        return null;
    }	

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,activityreport,query"
     * 
     * @param first - first index of the search to return
     * @param count - number of elements to return
     * @throws InvalidDataException
     * @throws CommunicationProblemException
     * @throws DeslogedException
     */ 
    public ListResult getReportWithOutCommentaryDrive(UserSessionData userSessionData,
    		Date initialDate,Date lastDate, Integer[][] divOrgs,Integer[][] divGeos,
    		int first,int count, String commentaryDriveType,int driverState,
    		String vehicleAllowed, String type)throws  Exception {
        try {
        	ActivityReportHome activityHome = ActivityReportUtil.getHome();
        	ActivityReport activityReport = activityHome.create(); 
            return activityReport.getReportWithOutCommentaryDrive(userSessionData,initialDate,lastDate,divOrgs,divGeos,first,count,commentaryDriveType,driverState,vehicleAllowed,type);
        } catch (Exception e) {
        	Object[] parameters = new Object[10];
        	parameters[0] = initialDate;
        	parameters[1] = lastDate;
        	parameters[2] = divOrgs;
        	parameters[3] = divGeos;
        	parameters[4] = first;
        	parameters[5] = count;
        	parameters[6] = commentaryDriveType;
        	parameters[7] = driverState;
        	parameters[8] = vehicleAllowed;
        	parameters[9] = type;
        	handler.handleException("getReportWithOutCommentaryDrive",e,userSessionData,parameters);
        }
        return null;
    }	 
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityregistrydelete,cepascheduleactivityregistryupdate,cdactivitydelete,cdactivitymodify"
     */
    public List getDrivers(String drivers,UserSessionData userSessionData)throws Exception{
       try{
           ActivityReportHome home = ActivityReportUtil.getHome();
           ActivityReport activityReport = home.create();
           List list = activityReport.getDrivers(drivers,userSessionData);
           return list;
       } catch(Exception e){
    	   Object[] parameters = new Object[1];
    	   parameters[0] = drivers;
    	   handler.handleException("getDrivers",e,userSessionData,parameters);
       }
       return null;
    }
        
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     */
    public AllActivitiesGeneralReportData findReportList(String[] type, Integer monthFrom, 
    		Integer yearFrom, Integer monthTo, Integer yearTo, Integer[][] divOrgs, 
    		Integer[][] divGeos, String[] state, int first, int count, UserSessionData userSessionData) throws Exception {
        try  {
            ActivityReportHome activityHome = ActivityReportUtil.getHome();
            ActivityReport activityReport = activityHome.create();                
            return new AllActivitiesGeneralReportData(activityReport.findReportList(type,monthFrom,yearFrom,monthTo,yearTo, divOrgs,divGeos,state,first,count,userSessionData),userSessionData);            
            
        }catch(Exception e) {
        	Object[] parameters = new Object[9];
        	parameters[0] = type;
        	parameters[1] = monthFrom;
        	parameters[2] = yearFrom;
        	parameters[3] = monthTo;
        	parameters[4] = yearTo;
        	parameters[5] = divOrgs;
        	parameters[6] = divGeos;
        	parameters[7] = first;
        	parameters[8] = count;        	
            handler.handleException("findReportList",e,userSessionData,parameters);
        }
        return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     */
    public Object[][] getParticipantsRegistry(Integer activityId, UserSessionData userSessionData) throws Exception {
        try{
            ActivityReportHome home = ActivityReportUtil.getHome();
            ActivityReport activityReport = home.create();
            return activityReport.getParticipantsRegistry(activityId,userSessionData);
        }catch(Exception e) {
        	Object[] parameters = new Object[1];
        	parameters[0] = activityId;        	
            handler.handleException("getParticipantsRegistry",e,userSessionData,parameters);
        }
        return null;
    }
 
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityupdate,cepascheduleactivityregistrycreate,cepascheduleactivityregistryupdate,cepascheduleactivityview,cdactivitycreate,cdactivitymodify"
     */
    public Object[][] getParticipantsRegistryObservations(Integer activityId, UserSessionData userSessionData) throws Exception {
        try{
            ActivityReportHome home = ActivityReportUtil.getHome();
            ActivityReport activityReport = home.create();
            return activityReport.getParticipantsRegistryObservations(activityId,userSessionData);
        }catch(Exception e) {
        	Object[] parameters = new Object[1];
        	parameters[0] = activityId;
            handler.handleException("getParticipantsRegistryObservations",e,userSessionData,parameters);
        }
        return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityview,cepascheduleactivityregistryview"
    */ 
    public ActivityExpiryData driverWithExpiredActivities(UserSessionData userSessionData,
    		Date initialDate,Date lastDate, String[] type,Integer[][] divOrgs,
    		Integer[][] divGeos,String driverType,String[] vehicleAllowed,int third,int first,int count) throws Exception {
        try{
        	ActivityReportHome home = ActivityReportUtil.getHome();
        	ActivityReport activityReport = home.create();
        	return new ActivityExpiryData(activityReport.driverWithExpiredActivities(userSessionData,initialDate,lastDate,type,divOrgs,divGeos,driverType,vehicleAllowed,third,first,count),userSessionData);
        }catch (Exception e) {
        	Object[] parameters = new Object[9];
        	parameters[0] = initialDate;
        	parameters[1] = lastDate;
        	parameters[2] = type;
        	parameters[3] = divOrgs;
        	parameters[4] = divGeos;
        	parameters[5] = driverType;
        	parameters[6] = vehicleAllowed;
        	parameters[7] = first;
        	parameters[8] = count;
        	handler.handleException("driverWithExpiredActivities",e,userSessionData,parameters);
        }
        return null;
    }
    
    /*
    public Collection  driverWithExpiredActivities(UserSessionData userSessionData,
    		Date initialDate,Date lastDate, String type) {
        try{
        	ActivityReportHome home = ActivityReportUtil.getHome();
        	ActivityReport activityReport = home.create();
            return activityReport.driverWithExpiredActivities(userSessionData,initialDate,lastDate,type);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    } 
    */   
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityview,cepascheduleactivityregistryview"
    */ 
    public ActivityDeliveredData driverWithDeliveredActivities(UserSessionData userSessionData,
    		Date initialDate,Date lastDate, String[] type,Integer[][] divOrgs,Integer[][] divGeos,
    		String driverType,String[] vehicleAllowed,int first,int count) throws Exception {
        try{
        	ActivityReportHome home = ActivityReportUtil.getHome();
        	ActivityReport activityReport = home.create();
        	return new ActivityDeliveredData(activityReport.driverWithDeliveredActivities(userSessionData,initialDate,lastDate,type,divOrgs,divGeos,driverType,vehicleAllowed,first,count),userSessionData);
        }catch (Exception e) {
        	Object [] parameters = new Object[9];
        	parameters[0] = initialDate;
        	parameters[1] = lastDate;
        	parameters[2] = type;
        	parameters[3] = divOrgs;
        	parameters[4] = divGeos;
        	parameters[5] = driverType;
        	parameters[6] = vehicleAllowed;
        	parameters[7] = first;
        	parameters[8] = count;
        	handler.handleException("driverWithDeliveredActivities",e,userSessionData,parameters);
        }
        return null;
    }    
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityview,cepascheduleactivityregistryview"
    */ 
    public DriversWithoutBTWData driversWithoutBTW(UserSessionData userSessionData,
    		Date initialDate,Date lastDate,Integer[][] divOrgs,Integer[][] divGeos,
    		String driverType,String[] vehicleAllowed,int first,int count) throws Exception {
        try{
        	ActivityReportHome home = ActivityReportUtil.getHome();
        	ActivityReport activityReport = home.create();
        	return new DriversWithoutBTWData(
        			activityReport.driversWithoutBTW(userSessionData,initialDate,
        					lastDate,divOrgs,divGeos,driverType,vehicleAllowed,first,count),
        			userSessionData);
        }catch (Exception e) {
        	Object[] parameters = new Object[8];
        	parameters[0] = initialDate;
        	parameters[1] = lastDate;
        	parameters[2] = divOrgs;
        	parameters[3] = divGeos;
        	parameters[4] = driverType;
        	parameters[5] = vehicleAllowed;
        	parameters[6] = first;
        	parameters[7] = count;
        	
        	handler.handleException("driversWithoutBTW",e,userSessionData,parameters);
        }
        return null;
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityview,cepascheduleactivityregistryview"
    */ 
    public DriversWithoutTOJData driversWithoutTOJ(UserSessionData userSessionData,
    		Integer[][] divOrgs,Integer[][] divGeos,
    		String driverType,String[] vehicleAllowed) throws Exception {
        try{
        	ActivityReportHome home = ActivityReportUtil.getHome();
        	ActivityReport activityReport = home.create();
        	return new DriversWithoutTOJData(
        			activityReport.driversWithoutTOJ(userSessionData,divOrgs,divGeos,driverType,vehicleAllowed),
        			userSessionData);
        }catch (Exception e) {
        	Object[] parameters = {divOrgs,divGeos,driverType,vehicleAllowed};
        	handler.handleException("driversWithoutTOJ",e,userSessionData,parameters);
        }
        return null;
    }
    
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityview,cepascheduleactivityregistryview"
    */
    public DriversBTWAssistData driversBTWAssist(UserSessionData userSessionData,
    		Date initialDate,Date lastDate,Integer[][] divOrgs,Integer[][] divGeos,
    		String status,int first,int count, boolean notPracticoAsistence,
    		boolean notTeoricoAsistence, String type, String vehicleAllowed) throws Exception {
        try{
        	ActivityReportHome home = ActivityReportUtil.getHome();
        	ActivityReport activityReport = home.create();
        	return new DriversBTWAssistData(activityReport.driversBTWAssist(userSessionData,initialDate,lastDate,divOrgs,divGeos,status,first,count,notPracticoAsistence,notTeoricoAsistence,type,vehicleAllowed),userSessionData);
        }catch (Exception e) {
        	Object[] parameters = new Object[11];
        	parameters[0] = initialDate;
        	parameters[1] = lastDate;
        	parameters[2] = divOrgs;
        	parameters[3] = divGeos;
        	parameters[4] = status;
        	parameters[5] = first;
        	parameters[6] = count;
        	parameters[7] = notPracticoAsistence;
        	parameters[8] = notTeoricoAsistence;
        	parameters[9] = type;
        	parameters[10] = vehicleAllowed;
        	handler.handleException("driversBTWAssist",e,userSessionData,parameters);
        }
        return null;
    }
    
    /**
     * Retorna los Drivers que no hicieron la Evaluacion en Cabina.
     * Se usa principalmente para Forestales.
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query,cepascheduleactivityview"
    */ 
    public ListResult driversWithOutEC(UserSessionData userSessionData,
    		Date initialDate,Date lastDate,Integer[][] divOrgs,Integer[][] divGeos,
    		int status,int first,int count, String firstName, String lastName) throws Exception{
        try{
        	ActivityReportHome home = ActivityReportUtil.getHome();
        	ActivityReport activityReport = home.create();
        	return activityReport.driversWithOutEC
        			(userSessionData,initialDate,lastDate,divOrgs,
        					divGeos,status,first,count,firstName,lastName);
        }catch (Exception e) {
        	Object[] parameters = new Object[9];
        	parameters[0] = initialDate;
        	parameters[1] = lastDate;
        	parameters[2] = divOrgs;
        	parameters[3] = divGeos;
        	parameters[4] = status;
        	parameters[5] = first;
        	parameters[6] = count;
        	parameters[7] = firstName;
        	parameters[8] = lastName;
        	handler.handleException("driversWithOutEC",e,userSessionData,parameters);
        }
        return null;
    }
       
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     */    
    public Collection findCommentaryDriveLog(UserSessionData userSessionData,
    		String fromMonth, Integer fromYear, String toMonth, Integer toYear,
    		Integer[][] divOrgs,Integer[][] divGeos,int order1,int order2,
    		int order3,int order4) throws Exception {
        
    	MemoryLog.getInstance().log(MemoryLog.START,"findCommentaryDriveLog",getClass());
        Collection result = null;
        
        try{
            ActivityReportHome home = ActivityReportUtil.getHome();
            ActivityReport activityReport = home.create();
            result = activityReport.findCommentaryDriveLog(userSessionData,fromMonth,fromYear,toMonth,toYear,divOrgs,divGeos,order1,order2,order3,order4);
            
        }catch (Exception e) {
        	Object[] parameters = new Object[10];
        	parameters[0] = fromMonth;
        	parameters[1] = fromYear;
        	parameters[2] = toMonth;
        	parameters[3] = toYear;
        	parameters[4] = divOrgs;
        	parameters[5] = divGeos;
        	parameters[6] = order1;
        	parameters[7] = order2;
        	parameters[8] = order3;
        	parameters[9] = order4;
            handler.handleException("findCommentaryDriveLog",e,userSessionData,parameters);
        }
        MemoryLog.getInstance().log(MemoryLog.END,"findCommentaryDriveLog",getClass());
        return result;
    }
    
    /**
     * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,cepascheduleactivityview" 
     * @param userSessionData
     * @return result of the search    
     * @throws Exception	
     * 
     *  Devuelve las actividades que tienen vencimiento.
     */    
    public ListResult getActivitiesWithExpiryTime(UserSessionData userSessionData) throws Exception {
        MemoryLog.getInstance().log(MemoryLog.START,"getActivitiesWithExpiryTime",getClass());
        ListResult listResult = null;
    	try{
    	    ActivityReportHome home = ActivityReportUtil.getHome();
            ActivityReport activityReport = home.create();
            listResult = activityReport.getActivitiesWithExpiryTime(userSessionData);
    	}catch (Exception e) {
    		Object[] parameters = null;
            handler.handleException("getActivitiesWithExpiryTime",e,userSessionData,parameters);
        }
        MemoryLog.getInstance().log(MemoryLog.END,"getActivitiesWithExpiryTime",getClass());
        return listResult;
    }
    
    /**
     * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,cepascheduleactivityview,cepascheduleactivitycreate,cepascheduleactivityregistrycreate,cepascheduleactivityedit,cepascheduleactivityregistryedit,cepascheduleactivityupdate,cepascheduleactivitydelete,cepascheduleactivityregistrydelete,cepascheduleactivityviewlist,cepascheduleactivityview,cepascheduleactivitysupervise,cdactivitycreate,cdactivitymodify,cdactivitydelete"
     * @param userSessionData
     * @param type   
     * @throws Exception
     * 
     * Devuelve la cantidad de años en que vence la actividad "type".
     * 0 significa que no vence
     * >0 cantidad de años de vencimiento
     */
    public int getTimeExpiry(String type, String country, UserSessionData userSessionData) throws Exception{
        MemoryLog.getInstance().log(MemoryLog.START,"getTimeExpiry",getClass());
        int time = 0;
    	try{
    	    ActivityReportHome home = ActivityReportUtil.getHome();
            ActivityReport activityReport = home.create();
            time = activityReport.getTimeExpiry(type,country,userSessionData);
    	}catch (Exception e) {
    		Object[] parameters = new Object[1];
    		parameters[0] = type;    		                                 
            handler.handleException("getTimeExpiry",e,userSessionData,parameters);
    	}
        MemoryLog.getInstance().log(MemoryLog.END,"getTimeExpiry",getClass());
    	return time;
    }
   
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityview,cepascheduleactivityregistryview,query"
     * @param userSessionData
     * @return
     * @throws Exception
     * 
     * Devuelve la lista de resourcekeyname de las CEPAAtivityDefinition
     * que tienen true en hasTeoricoAsistence y en hasPracticoAsistence
     */
    public ListResult getActivitiesWithTeoricoAndPracticoAsistence(UserSessionData userSessionData) throws Exception{
        MemoryLog.getInstance().log(MemoryLog.START,"getActivitiesWithTeoricoAndPracticoAsistence",getClass());
        ListResult listResult = null;
    	try{
    		  ActivityReportHome home = ActivityReportUtil.getHome();
              ActivityReport activityReport = home.create();
              listResult = activityReport.getActivitiesWithTeoricoAndPracticoAsistence(userSessionData);
      	}catch (Exception e) {
      		  Object [] parameters = null;
              handler.handleException("getActivitiesWithTeoricoAndPracticoAsistence",e, userSessionData, parameters);
      	}
        MemoryLog.getInstance().log(MemoryLog.END,"getActivitiesWithTeoricoAndPracticoAsistence",getClass());
      	return listResult;
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityview,cepascheduleactivityregistryview,query"
     */
    public HeavyReportData getReportHeavy(Integer idActivity,Integer idDriver,Text messages,
    		UserSessionData userSessionData) throws Exception {
        MemoryLog.getInstance().log(MemoryLog.START,"getReportHeavy",getClass());
        HeavyReportData heavyReportData = null; 
        try {
            CEPAActivityScheduleRegistryData registryData = getCompleteResult(idActivity,idDriver,userSessionData);   
            CEPAActivityScheduleData activityData = findCEPAActivityScheduleByPrimaryKey(idActivity,userSessionData);
            activityData.getDivOrgSet();
            CheckListData checkList = activityData.getDefinition().getCheckList();
            List<PointData> points = (List<PointData>)checkList.getPoints(); 
            
            Object[][] values = new Object[checkList.getItemCount()][3];
            
            Collections.sort(points);
            Iterator<PointData> itPoints = points.iterator();
            int index = 0;
            while (itPoints.hasNext()) {
                PointData point = itPoints.next();
                LinkedList<ItemData> items = (LinkedList<ItemData>)point.getItems();
                Collections.sort(items);
                Iterator<ItemData> itItems = items.iterator();
                while (itItems.hasNext()) {
                    ItemData item = itItems.next();
                    values[index][0] = messages.getText(point.getText());
                    values[index][1] = messages.getText(item.getText());
                    values[index][2] = registryData.getValueFromItem(item);
                    index++;
                }
            }
            
            heavyReportData = new HeavyReportData(activityData,registryData,values,messages);
            
        }catch(Exception e) {
        	Object[] parameters = new Object[3];
        	parameters[0] = idActivity; 
        	parameters[1] = idDriver; 
        	parameters[2] = messages;
            handler.handleException("getReportHeavy",e, userSessionData, parameters);
        }
        MemoryLog.getInstance().log(MemoryLog.END,"getReportHeavy",getClass());
        return heavyReportData;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess,dpachecklist" 
     */
    public CheckListData findCheckList(Integer checkId,UserSessionData userSessionData) throws Exception {
        MemoryLog.getInstance().log(MemoryLog.START,"findCheckList",getClass());
        CheckListData checkListData = null; 
        try {
            ActivityReport activityReport = ActivityReportUtil.getHome().create();
            checkListData = activityReport.findCheckList(checkId,userSessionData);
        }catch (Exception e) {
        	Object [] parameters = new Object[1];
        	parameters[0] = checkId;
            handler.handleException("findCheckList",e,userSessionData,parameters);
        }
        MemoryLog.getInstance().log(MemoryLog.END,"findCheckList",getClass());
        return checkListData;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess" 
     */
    public Collection findCommentaryDriveResult(String firstName,String lastName,
    		int status,String activityType,Date start,Date end,Integer[][] divOrgs,
    		Integer[][] divGeos,String vehicleAllowed,UserSessionData userSessionData) throws Exception {
        MemoryLog.getInstance().log(MemoryLog.START,"findCommentaryDriveResult",getClass());
        
        Collection result = null;
        
        try {
        
        	ActivityReport activityReport = ActivityReportUtil.getHome().create();
            result = activityReport.findCommentaryDriveResult(firstName,lastName,status,activityType,start,end,divOrgs,divGeos,vehicleAllowed,userSessionData);
        
        }catch (Exception e) {
        	Object [] parameters = new Object[9];
        	parameters[0] = firstName;
        	parameters[1] = lastName;
        	parameters[2] = status;
        	parameters[3] = activityType;
        	parameters[4] = start;
        	parameters[5] = end;
        	parameters[6] = divOrgs;
        	parameters[7] = divGeos;
        	parameters[8] = vehicleAllowed;
            handler.handleException("findCommentaryDriveResult",e, userSessionData, parameters);
        }
        MemoryLog.getInstance().log(MemoryLog.END,"findCommentaryDriveResult",getClass());
        return result;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess" 
     */
    public Collection findCommentaryDriveRiskResult(String firstName,String lastName,
    		int status,Date start,Date end,Integer[][] divOrgs,Integer[][] divGeos,
    		String vehicleAllowed,UserSessionData userSessionData) throws Exception {
        
    	MemoryLog.getInstance().log(MemoryLog.START,"findCommentaryDriveRiskResult",getClass());
        Collection result = null;
        
        try {
           
        	ActivityReport activityReport = ActivityReportUtil.getHome().create();
            result = activityReport.findCommentaryDriveRiskResult(firstName,lastName,status,start,end,divOrgs,divGeos,vehicleAllowed,userSessionData);
       
        }catch (Exception e) {
        	Object [] parameters = new Object[8];
        	parameters[0] = firstName;
        	parameters[1] = lastName;
        	parameters[2] = status;
        	parameters[3] = start;
        	parameters[4] = end;
        	parameters[5] = divOrgs;
        	parameters[6] = divGeos;
        	parameters[7] = vehicleAllowed;        	
        	
            handler.handleException("findCommentaryDriveRiskResult",e, userSessionData,parameters);
        }
        MemoryLog.getInstance().log(MemoryLog.END,"findCommentaryDriveRiskResult",getClass());
        return result;
    }

    /**
     * @ejb.permission role-name = "administrator"
     * @ejb.interface-method
     */
    public ListResult findCepaSystemActivities(String startDay,String startMonth,
    		String startYear,String endDay,String endMonth,String endYear,
    		String type,String country,String city,String state,int first,
    		int count,UserSessionData userSessionData) throws Exception{
        ListResult result = null;
        try{
            ActivityReport activityReport = ActivityReportUtil.getHome().create();
            result = activityReport.findCepaSystemActivities(startDay,startMonth,startYear,endDay,endMonth,endYear,type,country,city,state,first,count,userSessionData);
        } catch(Exception e){           
        	Object[] p = new Object[12];
        	p[0] = startDay;
        	p[1] = startMonth;
        	p[2] = startYear;
        	p[3] = endDay;
        	p[4] = endMonth;
        	p[5] = endYear;
        	p[6] = type;
        	p[7] = country;
        	p[8] = city;
        	p[9] = state;
        	p[10] = first;
        	p[11] = count;
            handler.handleException("findCepaSystemActivities",e,userSessionData,p);
        }
        return result;
    }

    /**
     * @ejb.permission role-name = "administrator,imeseviparticipantview,imeseviactivityview"
     * @ejb.interface-method
     */
    public communication.cepasystem.tables.ActivityData getCepaSystemActivity(
    		Integer activity,UserSessionData userSessionData) throws Exception {
    	communication.cepasystem.tables.ActivityData data = null;
        try{
        	ActivityReport activityReport = ActivityReportUtil.getHome().create();
            data = activityReport.getCepaSystemActivity(activity,userSessionData);
        } catch(Exception e){           
        	Object[] p = new Object[1];
        	p[0] = activity;
            handler.handleException("getCepaSystemActivity",e, userSessionData ,p);
        }
        return data;
    }

    /**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator"
     */
    public Collection getCepaSystemParticipants(Integer activity,UserSessionData userSessionData) throws Exception {
        MemoryLog.getInstance().log(MemoryLog.START,"getCepaSystemParticipants",getClass());
        Collection participants = new LinkedList();
        try{    
            if (userSessionData == null) {
                throw new DeslogedException("getCepaSystemParticipants","session = null");
            }
            ActivityReport report = ActivityReportUtil.getHome().create();
            participants = report.getCepaSystemParticipants(activity,userSessionData);
        } catch (Exception exception) {
        	Object[] p = new Object[1];
        	p[0] = activity;
            handler.handleException("getCepaSystemParticipants",exception,userSessionData,p);
        } 
        MemoryLog.getInstance().log(MemoryLog.END,"getCepaSystemParticipants",getClass());
        return participants;
    }

    /**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator"
     */
    public ListResult findCepaSystemDriverList(Hashtable parameters,int first,int count, UserSessionData userSessionData) throws Exception {
        MemoryLog.getInstance().log(MemoryLog.START,"findCepaSystemDriverList",getClass());
        ListResult participants = null;
        try{    
            if (userSessionData == null) {
                throw new DeslogedException("findCepaSystemDriverList","session = null");
            }
            ActivityReport report = ActivityReportUtil.getHome().create();
            participants = report.findCepaSystemActivityDrivers(parameters,first,count,userSessionData);
        } catch (Exception exception) {
        	Object[] p = new Object[3];
        	p[0] = parameters;
        	p[1] = first;
        	p[2] = count;
            handler.handleException("findCepaSystemDriverList",exception,userSessionData,p);
        } 
        MemoryLog.getInstance().log(MemoryLog.END,"findCepaSystemDriverList",getClass());
        return participants;
    }

    /**
     * @ejb.permission role-name = "administrator"
     * @ejb.interface-method
     */
    public Collection findCepaSystemActivities(String list,UserSessionData userSessionData) throws Exception{
        Collection result = null;
        try{
            ActivityReport activityReport = ActivityReportUtil.getHome().create();
            result = activityReport.findCepaSystemActivities(list,userSessionData);
        } catch(Exception e){      
        	Object[] p = new Object[1];
        	p[0] = list;
            handler.handleException("findCepaSystemActivities",e,userSessionData,p);
        }
        return result;
    }

	/**
	 * @throws Exception
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
	 */
    public Collection getCepaSystemResults(Integer[] from, Integer[] to,String[] activities,Integer[] divOrgs[],Integer divGeos,UserSessionData userSessionData) throws Exception {
        Collection result = null;
        try{
            ActivityReport activityReport = ActivityReportUtil.getHome().create();
            result = activityReport.getCepaSystemResults(from, to, activities, divOrgs, divGeos, userSessionData);
        } catch(Exception e){      
        	Object[] p = {from, to, activities, divOrgs, divGeos};
            handler.handleException("getCepaSystemResults",e,userSessionData,p);
        }
        return result;
    }

    /**
     * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,cepascheduleactivityview,query" 
     */    
	public Collection<DriverMaxExpiry> getMaxExpiry(UserSessionData userSessionData,String[] type,Integer[][] divOrgs,Integer[] divGeos,Date from,Date to,int status,String firstName,String lastName,String allowed) throws Exception {
		Collection<DriverMaxExpiry> lines = new LinkedList<DriverMaxExpiry>();
		try {
			lines.addAll(ActivityReportUtil.getHome().create().getMaxExpiry(userSessionData, type, divOrgs, divGeos, from, to, status, firstName, lastName, allowed));
        } catch(Exception e){      
        	Object[] p = {type, divOrgs, divGeos, status, firstName, lastName, allowed};
            handler.handleException("getMaxExpiry",e,userSessionData,p);
        }
        return lines;
    }
	
    /**
     * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,systemaccess" 
     */    
	public boolean isInstructorActivities(UserSessionData userSessionData) throws Exception {
		try {
			return ActivityReportUtil.getHome().create().isInstructorActivities(userSessionData);
        } catch(Exception e){      
        	Object[] p = {};
            handler.handleException("isInstructorActivities",e,userSessionData,p);
        }
        return false;
	}

    /**
     * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,systemaccess" 
     */    
	public Collection<InstructorActivity> getInstructorActivities(String instructor, int page, UserSessionData userSessionData) throws Exception {
		try {
			Collection<InstructorActivity> activities = ActivityReportUtil.getHome().create().getInstructorActivities(instructor, page, userSessionData);
			Collections.sort((List<InstructorActivity>) activities);
			return activities;
        } catch(Exception e){      
        	Object[] p = {};
            handler.handleException("getInstructorActivities",e,userSessionData,p);
        }
        return new LinkedList<InstructorActivity>();
	}

    /**
     * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,systemaccess" 
     */    
	public Collection<UserData> getActivityInstructors(UserSessionData userSessionData) throws Exception {
		try {
			return ActivityReportUtil.getHome().create().getActivityInstructors(userSessionData);
        } catch(Exception e){      
        	Object[] p = {};
            handler.handleException("getActivityInstructors",e,userSessionData,p);
        }
        return new LinkedList<UserData>();
	}

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityupdate,cepascheduleactivityregistrycreate,cepascheduleactivityregistryupdate,cepascheduleactivityview,cdactivitycreate,cdactivitymodify,driverviewowndata,jjreporter"
     */    
	public HashMap<String, Integer> getMediaActivity(String type, Integer corporation, Integer country,UserSessionData userSessionData) throws Exception {
		try {
			return ActivityReportUtil.getHome().create().getMediaActivity(type,corporation,country,userSessionData);
        } catch(Exception e){      
        	Object[] p = {};
            handler.handleException("getMediaActivity",e,userSessionData,p);
        }
        return new HashMap<String, Integer>();
	}

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityupdate,query,jjreporter"
     */    
	public boolean existsTodayActivity(Integer driver, String activityType, UserSessionData userSessionData)throws Exception {
		try {
			return ActivityReportUtil.getHome().create().existsTodayActivity(driver, activityType, userSessionData);
        } catch(Exception e){      
        	Object[] p = {};
            handler.handleException("getMediaActivity",e,userSessionData,p);
        }
        return false;
	}

    /**
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws CommunicationProblemException
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivityupdate,cepascheduleactivityview,cepascheduleactivityregistrycreate,cepascheduleactivityregistryupdate,cdactivitycreate,cdactivitymodify"
     */
	public ActivityRegistryData[] getParticipantData(Integer activity, UserSessionData userSessionData) throws Exception {
		try {
			return ActivityReportUtil.getHome().create().getParticipantData(activity, userSessionData);
        } catch(Exception e){      
        	Object[] p = {};
            handler.handleException("getParticipantData",e,userSessionData,p);
        }
        return null;
	}
	
	/**
     * @ejb.permission role-name = "driverview,administrator"
	 * @ejb.interface-method
	 */
	public ArrayList<ActivityAssesmentData> findAssesments(Integer driver,UserSessionData userSessionData) throws Exception{
		 
		try{
		/*	
			String urlString="http://localhost:8089/assesment/webapi/assessmentws/userassessments?user="+SecurityConstants.ASSESMENT_USER+"&password="+SecurityConstants.ASSESMENT_PASSWORD+"&driver="+driver;
			if(SecurityConstants.isProductionServer()){
				urlString="http://da.cepadc.com/assesment/webapi/assessmentws/userassessments?user="+SecurityConstants.ASSESMENT_USER+"&password="+SecurityConstants.ASSESMENT_PASSWORD+"&driver="+driver;
			}
			
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed Access Assesment System : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String output;
			StringBuilder builder=new StringBuilder("");
			while ((output = br.readLine()) != null) {
				builder.append(output);
			}

			
			conn.disconnect();
			
			JSONArray arrayJSON= new JSONArray(builder.toString());
			ArrayList<ActivityAssesmentData> res=new ArrayList<ActivityAssesmentData>();
			for(int i=0; i< arrayJSON.length(); i++){
				JSONArray values=(JSONArray) arrayJSON.get(i);
				ActivityAssesmentData data=new ActivityAssesmentData();
				data.setId(values.getInt(0));
				data.setName(values.getString(1));
				data.setUser(values.getString(2));
				data.setDate(values.getString(3));
				data.setTestComplete(values.getBoolean(4));
				res.add(data);
			}
		
			return res;*/
		} catch(Exception e){			
			Object[] parameters = new Object[1];
			parameters[0] = driver;
			handler.handleException("findAssesments",e, userSessionData,parameters);
		}
		return new ArrayList<ActivityAssesmentData>();
	}
	
	/**
	 * @throws InvalidDataException
	 * @throws DeslogedException
	 * @throws CommunicationProblemException
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,eventcreate,finecreate,cepascheduleactivityview,cepascheduleactivityupdate,cepascheduleactivityregistrycreate,cepascheduleactivityregistryupdate,cdactivitycreate,cdactivitymodify,driverviewowndata"
	 */
	public Collection<ActivityAttendanceData> getAttendanceList(Integer idActivity, UserSessionData userSessionData) throws Exception {
		Collection<ActivityAttendanceData> values = new LinkedList<ActivityAttendanceData>();
		try {
			values.addAll(ActivityReportUtil.getHome().create().getAttendanceList(idActivity,userSessionData));
		} catch(Exception e){			
			Object[] parameters = new Object[1];
			parameters[0] = idActivity;
			handler.handleException("getAttendanceList",e, userSessionData,parameters);
		}
		return values;
	}
}
