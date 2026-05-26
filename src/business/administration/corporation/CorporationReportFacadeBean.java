/*
 * Created on 30-sep-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package business.administration.corporation;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.SessionBean;

import persistence.activity.ActivityReportUtil;
import persistence.administration.corporation.CorporationReport;
import persistence.administration.corporation.CorporationReportHome;
import persistence.administration.corporation.CorporationReportUtil;
import persistence.driver.DriverReportUtil;
import persistence.event.EventReportUtil;
import persistence.vehicle.VehicleReportUtil;
import business.observer.MessagesManager;
import business.observer.WrappedObserver;
import business.util.ExceptionHandler;

import communication.activity.tables.CEPAActivityScheduleData;
import communication.administration.corporation.tables.CorporationConfigData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.CorporationDirectoryData;
import communication.administration.corporation.tables.InstructorRegistryData;
import communication.administration.corporation.tables.MenuOptionData;
import communication.administration.corporation.tables.PointAccountData;
import communication.administration.corporation.tables.PointDetailBayerData;
import communication.administration.corporation.tables.PointMoveData;
import communication.administration.corporation.tables.PointsDefinitionData;
import communication.administration.corporation.util.DPAUnconformityFilter;
import communication.administration.corporation.util.DPAUnconformityItemFilter;
import communication.administration.dpa.CorrectiveActionDPAData;
import communication.administration.dpa.LevelOfServiceDriverDPAData;
import communication.administration.dpa.LevelOfServiceVehicleDPAData;
import communication.administration.dpa.UnconformityDPAData;
import communication.administration.user.UserSessionData;
import communication.administration.user.tables.UserCorporationData;
import communication.driver.tables.DriverPointData;
import communication.exception.CommunicationProblemException;
import communication.exception.DeslogedException;
import communication.exception.InvalidDataException;
import communication.fleet.tables.FleetBayerData;
import communication.language.Text;
import communication.util.ListResult;
import communication.util.tables.AppFileData;
import communication.util.tables.ResumeData;

/**
 * @ejb.bean name="CorporationReportFacade"
 *           display-name="Name for CorporationReportFacade"
 *           description="Description for CorporationReportFacade"
 *           jndi-name="ejb/CorporationReportFacade"
 *           type="Stateless"
 *           view-type="both"
 * 
 * @ejb.ejb-ref 
 * 			ejb-name ="CorporationReport"
 * 			ref-name = "ejb/CorporationReport"
 * 			view-type ="remote"
 * 
 * @jboss.ejb-ref-jndi
 * 			jndi-name = "ejb/CorporationReport" 
 * 			ref-name = "CorporationReport"
 *
 * @ejb.ejb-ref 
 * 			ejb-name ="DriverReport"
 * 			ref-name = "ejb/DriverReport"
 * 			view-type ="remote"
 * 
 * @jboss.ejb-ref-jndi
 * 			jndi-name = "ejb/DriverReport" 
 * 			ref-name = "DriverReport"
 *
 * @ejb.ejb-ref 
 * 			ejb-name ="VehicleReport"
 * 			ref-name = "ejb/VehicleReport"
 * 			view-type ="remote"
 * 
 * @jboss.ejb-ref-jndi
 * 			jndi-name = "ejb/VehicleReport" 
 * 			ref-name = "VehicleReport"
 *
 * @ejb.ejb-ref 
 * 			ejb-name ="EventReport"
 * 			ref-name = "ejb/EventReport"
 * 			view-type ="remote"
 * 
 * @jboss.ejb-ref-jndi
 * 			jndi-name = "ejb/EventReport" 
 * 			ref-name = "EventReport"
 *
 * @ejb.ejb-ref 
 * 			ejb-name ="ActivityReport"
 * 			ref-name = "ejb/ActivityReport"
 * 			view-type ="remote"
 * 
 * @jboss.ejb-ref-jndi
 * 			jndi-name = "ejb/ActivityReport" 
 * 			ref-name = "ActivityReport"
 *
 * @ejb.ejb-ref ejb-name = "ActivityReport" ref-name = "ejb/ActivityReport" view-type =
 *              "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/ActivityReport" ref-name = "ActivityReport"
 * 
 * @ejb.util generate="physical"
 *
 */
public abstract class CorporationReportFacadeBean implements SessionBean {

    ExceptionHandler handler = new ExceptionHandler(CorporationReportFacadeBean.class);

    /**
	 * Create method
	 * 
	 * @ejb.create-method
	 * @ejb.permission role-name = "administrator,systemaccess,resetpassword,addreport,dpachecklist, drivercreate,cepascheduleactivityupdate"
	 *  
	 */
	public void ejbCreate()
			throws javax.ejb.CreateException {

/*		if (userSessionData == null) {
			throw new CreateBeanException("Has ocurred error in business.corporation.CorporationReportFacde(ejbCreate), session data is empty","generic.error.deslogued");
		}
		this.userSessionData = userSessionData;
*/
	}
	
	/**
	 * @ejb.interface-method 
     * @ejb.permission role-name = "administrator,corporationedit"
	 * @param name
	 * @return
	 * @throws InvalidDataException
	 * @throws CommunicationProblemException
	 * @throws DeslogedException
	 */
	public Collection findCorporationByLikeName(String name,UserSessionData userSessionData) throws Exception {

		if (userSessionData == null) {
			throw new DeslogedException("findCorporationByLikeName","userSessionData = null");
		}
		if (name == null) {
			throw new InvalidDataException("findCorporationByLikeName","name = null");
		}

		CorporationReport crpReport=null;
		List result=null;
		try {

			CorporationReportHome home = CorporationReportUtil.getHome();
			crpReport = home.create();
			result=crpReport.findCorporationByLikeName(name, userSessionData);
		}catch (Exception e) {
			Object[] parameters = new Object[1];
			parameters[1] = name;
            handler.handleException("findCorporationByLikeName",e,userSessionData,parameters);
		}
		
		return result;
	}
	
	
	/**
	 * @ejb.interface-method 
	 * @ejb.permission role-name = "administrator,systemaccess" 
	 */
	public String addDCMessageObserver(UserSessionData userSessionData)	throws Exception {
		WrappedObserver wo=new WrappedObserver();
		MessagesManager.getInstance().addObserver(userSessionData.getCorporationId(), wo);
		return wo.getMessage();
	}
	
	 
	
	
	/**
	 * @ejb.interface-method 
	 * @ejb.permission role-name = "administrator,systemaccess" 
	 */
	public void sendDCMessageObserver(String m,UserSessionData userSessionData)	throws Exception {
		MessagesManager.getInstance().notify(userSessionData.getCorporationId(), m);
	}
	
	
	
	/**
	 * @ejb.interface-method 
     * @ejb.permission role-name = "administrator,systemaccess,resetpassword,uploadcdcimsa"
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public CorporationData findCorporationByPrimaryKey(Integer id,UserSessionData userSessionData)	throws Exception {

		if (userSessionData == null) {
			throw new DeslogedException("findCorporationByPrimaryKey","userSessionData = null");
		}
		if (id == null) {
			throw new InvalidDataException("findCorporationByPrimaryKey","id = null");
		}

		CorporationReport crpReport=null;
		CorporationData crpData=null;
		try {

			CorporationReportHome home = CorporationReportUtil.getHome();
			crpReport = home.create();
			crpData=crpReport.findCorporationByPrimaryKey(id, userSessionData);
		}catch (Exception e) {
			Object[] parameters = new Object[1];
			parameters[0] = id;			
			handler.handleException("findCorporationByPrimaryKey",e,userSessionData,parameters);
		}
		return crpData;
	}

	
	/**
	 * @ejb.interface-method 
     * @ejb.permission role-name = "administrator,systemaccess,corporationedit,resetpassword"
	 * @param name
	 * @param userRequest
	 * @return
	 * @throws Exception
	 */
	public CorporationDirectoryData findCorporationDirectoryByPrimaryKey(Integer id, UserSessionData userSessionData) throws Exception 
	{
		if (userSessionData== null) {
			throw new DeslogedException("findCorporationByPrimaryKey","userSessionData = null");
		}
		if (id == null) {
			throw new InvalidDataException("findCorporationByPrimaryKey","id = null");
		}

		CorporationReport crpReport=null;
		CorporationDirectoryData crpData=null;
		try {

			CorporationReportHome home = CorporationReportUtil.getHome();
			crpReport = home.create();
			crpData = crpReport.findCorporationDirectoryByPrimaryKey(id, userSessionData);
		}catch (Exception e) {
			Object[] parameters = new Object[1];
			parameters[0] = id;
			handler.handleException("findCorporationDirectoryByPrimaryKey",e,userSessionData,parameters);
		}
		return crpData;
	}
	
	/**
	 * @ejb.interface-method 
	 * @ejb.permission role-name = "administrator,systemaccess" 
	 */ 
	public Collection findAllCountries(UserSessionData userSessionData) throws Exception {
	    if (userSessionData == null) {
			throw new DeslogedException("findAllCountries","userSessionData = null");
		}
		CorporationReport crpReport=null;
		try {
			CorporationReportHome home = CorporationReportUtil.getHome();
			crpReport = home.create();
			return crpReport.findAllCountries(userSessionData);
		} catch (Exception e) {
			Object[] parameters = null;
			handler.handleException("findAllCountries",e,userSessionData,parameters);
		}
        return null;
	}
	
	/**
	 * @ejb.interface-method 
	 * @ejb.permission role-name = "administrator,systemaccess"
	 */
	public Collection findCorporationAttributesByUser(String userId,UserSessionData userSessionData) throws Exception {
	    if (userSessionData == null) {
            throw new DeslogedException("findCorporationAttributesByUser","userSessionData = null");
		}
		CorporationReport crpReport=null;
		try {
			CorporationReportHome home = CorporationReportUtil.getHome();
			crpReport = home.create();
			return crpReport.findCorporationAttributesByUser(userSessionData,userId);
		}catch (Exception e) {
			Object[] parameters = new Object[1];
			parameters[0] = userId;
			handler.handleException("findCorporationAttributesByUser",e,userSessionData,parameters);
		}
        return null;
	}
	
	/**
	 * @ejb.interface-method 
	 * @ejb.permission role-name = "administrator,systemaccess"
	 */
	public Collection<UserCorporationData> findCorporationRoleByUser(String userId,UserSessionData userSessionData) throws Exception {
	    if (userSessionData == null) {
            throw new DeslogedException("findCorporationRoleByUser","userSessionData = null");
		}
		try {
			return CorporationReportUtil.getHome().create().findCorporationRoleByUser(userSessionData,userId);
		}catch (Exception e) {
			Object[] parameters = {userId};
			handler.handleException("findCorporationRoleByUser",e,userSessionData,parameters);
		}
        return null;
	}

	/**
     * Returns all corporations (CorporationAttributes) that a user can get.
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess"
     */ 
    public String findCorporationName(Integer corporation,UserSessionData userSessionData) throws Exception {
        if (userSessionData == null) {
            throw new DeslogedException("findCorporationName","userSessionData = null");
        }
        CorporationReport crpReport=null;
        try {
            CorporationReportHome home = CorporationReportUtil.getHome();
            crpReport = home.create();
            return crpReport.findCorporationName(corporation,userSessionData);
        }catch (Exception e) {
        	Object[] parameters = new Object[1];
        	parameters[0] = corporation;
            handler.handleException("findCorporationName",e,userSessionData,parameters);
        }
        return null;
    }
    
    /**
     * Returns menu options.
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess"
     */ 
    public Collection findMenuOption(UserSessionData userSessionData) throws Exception {
        if (userSessionData == null) {
            throw new DeslogedException("findMenuOption","userSessionData = null");
        }
        CorporationReport crpReport=null;
        try {
            CorporationReportHome home = CorporationReportUtil.getHome();
            crpReport = home.create();
            List menu=(List)crpReport.findMenuOption(userSessionData);
            Collections.sort(menu);
            return menu;
            
        }catch (Exception e) {
        	Object[] parameters = null;
            handler.handleException("findMenuOption",e,userSessionData,parameters);
        }
        return null;
    }
    
    /**
     * Returns menu options.
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess"
     */ 
    public Collection findAllMenuOption(UserSessionData userSessionData,boolean driver) throws Exception {
        if (userSessionData == null) {
            throw new DeslogedException("findMenuOption","userSessionData = null");
        }
        CorporationReport crpReport=null;
        try {
            CorporationReportHome home = CorporationReportUtil.getHome();
            crpReport = home.create();
            List menu=(List)crpReport.findAllMenuOption(userSessionData,driver);
            Collections.sort(menu);
            return menu;
            
        }catch (Exception e) {
        	Object[] parameters = new Object[1];
        	parameters[0] = driver;
            handler.handleException("findAllMenuOption",e,userSessionData,parameters);
        }
        return null;
    }
    
    /**
     * Returns menu options.
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess"
     */ 
    public Collection findNoSelectedOption(UserSessionData userSessionData,boolean enable) throws Exception {
        if (userSessionData == null) {
            throw new DeslogedException("findNoSelectedOption","userSessionData = null");
        }
        CorporationReport crpReport=null;
        try {
            CorporationReportHome home = CorporationReportUtil.getHome();
            crpReport = home.create();
            return crpReport.findNoSelectedOption(userSessionData,enable);
            
        }catch (Exception e) {
        	Object[] parameters = new Object[1];
        	parameters[0] = enable;
            handler.handleException("findNoSelectedOption",e,userSessionData,parameters);
        }
        return null;
    }
    
    /**
     * Returns menu options.
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess"
     */ 
    public Collection allMenuItems(UserSessionData userSessionData,boolean enable) throws Exception {
        if (userSessionData == null) {
            throw new DeslogedException("allMenuItems","userSessionData = null");
        }
        CorporationReport crpReport=null;
        try {
            CorporationReportHome home = CorporationReportUtil.getHome();
            crpReport = home.create();
            return crpReport.allMenuItems(userSessionData,enable);
            
        }catch (Exception e) {
        	Object[] parameters = new Object[1];
        	parameters[0] = enable;
            handler.handleException("findMenuOption",e,userSessionData,parameters);
        }
        return null;
    }
    
    /**
     * Returns menu options.
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess"
     */ 
    public Collection getActivityMails(CEPAActivityScheduleData activityData, UserSessionData userSessionData) throws Exception {
    	Collection mails = new LinkedList();
        if (userSessionData == null) {
            throw new DeslogedException("getActivityMails","userSessionData = null");
        }
        CorporationReport crpReport=null;
        try {
            CorporationReportHome home = CorporationReportUtil.getHome();
            crpReport = home.create();
           // mails.add("rodriguez.jme@gmail.com");
            
        }catch (Exception e) {
        	Object[] parameters = new Object[1];
        	parameters[0] = activityData;
            handler.handleException("getActivityMails",e,userSessionData,parameters);
        }
        return mails;
    }
 
	/**
     * Returns menu options.
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,addreport"
	 * @throws Exception
	 */
	public Collection<AppFileData> getAppFiles(UserSessionData userSession) throws Exception {
		Collection<AppFileData> files = new LinkedList<AppFileData>();
        if (userSession == null) {
            throw new DeslogedException("getAppFiles","userSessionData = null");
        }
        try {
            files = CorporationReportUtil.getHome().create().getAppFiles(userSession);
        }catch (Exception e) {
        	Object[] parameters = new Object[0];
            handler.handleException("getAppFiles",e,userSession,parameters);
        }
        return files;
	}

	/**
     * Returns menu options.
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,addreport"
	 * @throws Exception
	 */
	public AppFileData getAppFile(Integer id,UserSessionData userSession) throws Exception {
        if (userSession == null) {
            throw new DeslogedException("getAppFile","userSessionData = null");
        }
        try {
            return CorporationReportUtil.getHome().create().getAppFile(id,userSession);
        }catch (Exception e) {
        	Object[] parameters = {id};
            handler.handleException("getAppFile",e,userSession,parameters);
        }
        return null;
	}

	/**
     * Returns menu options.
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,addreport"
	 * @throws Exception
	 */
	public Collection<String> getAppDevices(UserSessionData userSession, String appId) throws Exception {
		Collection<String> files = new LinkedList<String>();
        if (userSession == null) {
            throw new DeslogedException("getAppDevices","userSessionData = null");
        }
        try {
            files = CorporationReportUtil.getHome().create().getAppDevices(userSession,appId);
        }catch (Exception e) {
        	Object[] parameters = new Object[0];
            handler.handleException("getAppDevices",e,userSession,parameters);
        }
        return files;
	}

	
	/**
     * .
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivitycreate,cdactivitycreate,vehiclechecklist,dpachecklist,driverview,vehicleview"
	 * @throws Exception
	 */
	public UnconformityDPAData findUnconformityDPAById(UserSessionData userSession, Integer id) throws Exception {
        try {
           return CorporationReportUtil.getHome().create().findUnconformityDPAById(userSession, id);
        }catch (Exception e) {
        	Object[] parameters = new Object[0];
            handler.handleException("findUnconformityDPAById",e,userSession,parameters);
        }
        return null;
	}
	
	/**
     * .
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivitycreate,cdactivitycreate,vehiclechecklist,dpachecklist,vehicleview"
	 * @throws Exception
	 */
	public UnconformityDPAData findUnconformityDPAByCheckId(UserSessionData userSession, Integer id) throws Exception {
        try {
           return CorporationReportUtil.getHome().create().findUnconformityDPAByCheckId(userSession, id);
        }catch (Exception e) {
        	Object[] parameters = new Object[0];
            handler.handleException("findUnconformityDPAById",e,userSession,parameters);
        }
        return null;
	}
	
	/**
     *  
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,driveredit,driverupdate,driverview"
	 * @throws Exception
	 */
	public ArrayList<UnconformityDPAData> findUnconformityDPAByDriverId(UserSessionData userSession, Integer id) throws Exception {
		 try {
	           return CorporationReportUtil.getHome().create().findUnconformityDPAByDriverId(userSession, id);
        }catch (Exception e) {
        	Object[] parameters = new Object[0];
            handler.handleException("findUnconformityDPAByDriverId",e,userSession,parameters);
        }
        return null;
	}
	
	
	/**
     * .
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivitycreate,cdactivitycreate,vehiclechecklist,dpachecklist,driverview,vehicleview"
	 * @throws Exception
	 */
	public UnconformityDPAData findUnconformityDPAByTelematicControlId(UserSessionData userSession, Integer id) throws Exception {
        try {
           return CorporationReportUtil.getHome().create().findUnconformityDPAByTelematicControlId(userSession, id);
        }catch (Exception e) {
        	Object[] parameters = new Object[0];
            handler.handleException("findUnconformityDPAById",e,userSession,parameters);
        }
        return null;
	}
	
	/**
     * .
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivitycreate,cdactivitycreate,driverview,vehicleview,vehiclechecklist,dpachecklist"
	 * @throws Exception
	 */
	public ListResult findUnconformityDPAByFilter(UserSessionData userSession, DPAUnconformityFilter filter) throws Exception {
        try {
           return CorporationReportUtil.getHome().create().findUnconformityDPAByFilter(userSession, filter);
        }catch (Exception e) {
        	Object[] parameters = new Object[]{filter};
            handler.handleException("findUnconformityDPAByFilter",e,userSession,parameters);
        }
        return null;
	}
	
	/**
     * .
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivitycreate,cdactivitycreate,driverview,vehicleview,vehiclechecklist,dpachecklist"
	 * @throws Exception
	 */
	public Collection findCorrectiveActionsByUnconformityId(UserSessionData userSession, Integer id) throws Exception {
        try {
           return CorporationReportUtil.getHome().create().findCorrectiveActionsByUnconformityId(userSession, id);
        }catch (Exception e) {
        	Object[] parameters = new Object[]{id};
            handler.handleException("findCorrectiveActionsByUnconformityId",e,userSession,parameters);
        }
        return null;
	}
	
	/**
     * Returns menu options.
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,cepascheduleactivitycreate,cdactivitycreate,vehiclechecklist,dpachecklist,driverview,vehicleview"
	 * @throws Exception
	 */
	public CorrectiveActionDPAData findCorrectiveActionById(UserSessionData userSession, Integer id) throws Exception {
	    try {
	           return CorporationReportUtil.getHome().create().findCorrectiveActionById(userSession, id);
	        }catch (Exception e) {
	        	Object[] parameters = new Object[]{id};
	            handler.handleException("findCorrectiveActionById",e,userSession,parameters);
	        }
	        return null;
		}
	
	/**
     * 
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,driverupdate,driverdelete,drivercreate"
	 * @throws Exception
	 */
	public LevelOfServiceDriverDPAData findLevelOfServiceDriverDPAByDriverId(UserSessionData userSession, Integer id) throws Exception {
		try {
	           return CorporationReportUtil.getHome().create().findLevelOfServiceDriverDPAByDriverId(userSession, id);
	        }catch (Exception e) {
	        	Object[] parameters = new Object[]{id};
	            handler.handleException("findLevelOfServiceDriverDPAByDriverId",e,userSession,parameters);
	        }
	        return null;
		}
      
	/**
     * 
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,driverupdate,driverdelete,drivercreate"
	 * @throws Exception
	 */
	public LevelOfServiceDriverDPAData findLevelOfServiceDriverDPAById(UserSessionData userSession, Integer id) throws Exception {
		try {
	           return CorporationReportUtil.getHome().create().findLevelOfServiceDriverDPAById(userSession, id);
	        }catch (Exception e) {
	        	Object[] parameters = new Object[]{id};
	            handler.handleException("findLevelOfServiceDriverDPAById",e,userSession,parameters);
	        }
	        return null;
		}
     
	/**
     * 
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,driverupdate,driverdelete,drivercreate"
	 * @throws Exception
	 */
	public Collection findLevelOfServiceDriversDPAByDriverId(UserSessionData userSession, Integer id) throws Exception {
		try {
	           return CorporationReportUtil.getHome().create().findLevelOfServiceDriversDPAByDriverId(userSession, id);
	        }catch (Exception e) {
	        	Object[] parameters = new Object[]{id};
	            handler.handleException("findLevelOfServiceDriverDPAById",e,userSession,parameters);
	        }
	        return null;
		}
 
	/**
     * 
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,driverupdate,driverdelete,drivercreate"
	 * @throws Exception
	 */
	public void exportLevelOfServiceDriversDPA(UserSessionData userSession,Text messages, OutputStream output,String divOrgs,Date date, String active) throws Exception {
		try {
	           CorporationReportUtil.getHome().create().exportLevelOfServiceDriversDPA(userSession,messages, output,divOrgs,date,active);
	        }catch (Exception e) {
	        	Object[] parameters = new Object[]{};
	            handler.handleException("exportLevelOfServiceDriversDPA",e,userSession,parameters);
	        }
	 }
	
	
	/**
     * 
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,vehicledelete,vehiclecreate"
	 * @throws Exception
	 */
	public LevelOfServiceVehicleDPAData findLevelOfServiceVehicleDPAByVehicleId(UserSessionData userSession, Integer id) throws Exception {
		try {
	           return CorporationReportUtil.getHome().create().findLevelOfServiceVehicleDPAByVehicleId(userSession, id);
	        }catch (Exception e) {
	        	Object[] parameters = new Object[]{id};
	            handler.handleException("findLevelOfServiceVehicleDPAByVehicleId",e,userSession,parameters);
	        }
	        return null;
		}
      
	/**
     * 
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,vehicledelete,vehiclecreate"
	 * @throws Exception
	 */
	public LevelOfServiceVehicleDPAData findLevelOfServiceVehicleDPAById(UserSessionData userSession, Integer id) throws Exception {
		try {
	           return CorporationReportUtil.getHome().create().findLevelOfServiceVehicleDPAById(userSession, id);
	        }catch (Exception e) {
	        	Object[] parameters = new Object[]{id};
	            handler.handleException("findLevelOfServiceVehicleDPAById",e,userSession,parameters);
	        }
	        return null;
		}
     
	/**
     * 
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,vehicledelete,vehiclecreate"
	 * @throws Exception
	 */
	public Collection findLevelOfServiceVehiclesDPAByDriverId(UserSessionData userSession, Integer id) throws Exception {
		try {
	           return CorporationReportUtil.getHome().create().findLevelOfServiceVehiclesDPAByVehicleId(userSession, id);
	        }catch (Exception e) {
	        	Object[] parameters = new Object[]{id};
	            handler.handleException("findLevelOfServiceVehiclesDPAByDriverId",e,userSession,parameters);
	        }
	        return null;
		}
	
	/**
     * 
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,vehicledelete,vehiclecreate"
	 * @throws Exception
	 */
	public void exportLevelOfServiceVehiclesDPA(UserSessionData userSession,Text messages, OutputStream output,String divOrgs,Date date) throws Exception {
		try {
	           CorporationReportUtil.getHome().create().exportLevelOfServiceVehiclesDPA(userSession,messages, output,divOrgs,date);
	        }catch (Exception e) {
	        	Object[] parameters = new Object[]{};
	            handler.handleException("exportLevelOfServiceVehiclesDPA",e,userSession,parameters);
	        }
	 }
	
	/**
     * 
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,vehicledelete,vehiclecreate,vehicleview"
	 * @throws Exception
	 */
	public ArrayList<ArrayList<String>> exportUnconformityItemsCheckList(UserSessionData userSession,Text messages, OutputStream output,DPAUnconformityItemFilter filter) throws Exception {
	
			try {
	           return CorporationReportUtil.getHome().create().exportUnconformityItemsCheckList(userSession,messages, output,filter);
	        }catch (Exception e) {
	        	Object[] parameters = new Object[]{};
	            handler.handleException("exportUnconformityItemsCheckListDPA",e,userSession,parameters);
	        }
		
			return new ArrayList<ArrayList<String>>();
	}
	

	/**
     * 
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,vehicledelete,vehiclecreate,vehicleview"
	 * @throws Exception
	 */
	public void exportUnconformityItemsCheckListDPA(UserSessionData userSession,Text messages, OutputStream output,DPAUnconformityItemFilter filter) throws Exception {
		
		try {
	           CorporationReportUtil.getHome().create().exportUnconformityItemsCheckListDPA(userSession,messages, output,filter);
	        }catch (Exception e) {
	        	Object[] parameters = new Object[]{};
	            handler.handleException("exportUnconformityItemsCheckListDPA",e,userSession,parameters);
	        }
		
     }
	
	/**
     * 
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,driverupdate,driverdelete,drivercreate,driverview"
	 * @throws Exception
	 */
	public void exportUnconformityItemsDriverDPA(UserSessionData userSession,Text messages, OutputStream output,DPAUnconformityItemFilter filter) throws Exception {
		
		try {
	           CorporationReportUtil.getHome().create().exportUnconformityItemsDriverDPA(userSession,messages, output,filter);
	        }catch (Exception e) {
	        	Object[] parameters = new Object[]{};
	            handler.handleException("exportUnconformityItemsDriverDPA",e,userSession,parameters);
	        }
		
     }

	/**
     * 
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,vehicledelete,vehiclecreate,vehicleview"
	 * @throws Exception
	 */
	public ArrayList<ArrayList<String>> findUnconformityItemsCheckListFORESTAL(UserSessionData userSession,Text messages, DPAUnconformityItemFilter filter) throws Exception {
		try {
	           return CorporationReportUtil.getHome().create().findUnconformityItemsCheckListFORESTAL(userSession,messages, filter);
	        }catch (Exception e) {
	        	Object[] parameters = new Object[]{};
	            handler.handleException("findUnconformityItemsCheckListFORESTAL",e,userSession,parameters);
	        }
		return new ArrayList<ArrayList<String>>();
	}
	/**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,drivercreate"
	 * @param data
	 * @throws Exception 
	 */
	public Integer[] getTransporterLink(Integer transporter,UserSessionData userSession) throws Exception {
		try{
            return CorporationReportUtil.getHome().create().getTransporterLink(transporter, userSession);
    	}catch (Exception e) {
			Object[] parameters = {transporter};
            handler.handleException("getTransporterLink",e,userSession,parameters);
    	}
		return null;
	}
	
	  /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleview"
	 * @throws Exception
	 */
    public ArrayList<InstructorRegistryData> listAllInstructorRegistry(Date startDate, Date endDate,UserSessionData userSessionData)  throws Exception {
    	
    	try{
    		 
    		 return CorporationReportUtil.getHome().create().listAllInstructorRegistry(startDate, endDate, userSessionData);
     	} catch (Exception e) {
			Object[] parameters = {userSessionData};
    		handler.handleException("listAllInstructorRegistry",e,userSessionData,parameters);
    	}	
    	
    	return null;
    } 
	
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleview"
	 * @throws Exception
	 */
    public ArrayList<InstructorRegistryData> listInstructorRegistry(Date startDate, Date endDate,UserSessionData userSessionData)  throws Exception {
    	
    	try{
    		 
    		 return CorporationReportUtil.getHome().create().listInstructorRegistry(startDate, endDate, userSessionData);
     	} catch (Exception e) {
			Object[] parameters = {userSessionData};
    		handler.handleException("listInstructorRegistry",e,userSessionData,parameters);
    	}	
    	
    	return null;
    } 
    
    /**
   	 * @ejb.interface-method
        * @ejb.permission role-name = "administrator,corporationmodify,systemaccess"
   	 * Modify a corporation
   	 * @param data - Data with the change of the corporationmenuItems
   	 * @param userRequest - Logged user
        * @throws Exception
   	 */
   	public HashMap<String, CorporationConfigData> getCorporationConfig(Integer corporationId,UserSessionData userSession) throws Exception {
   		try{
   		 
   		 return CorporationReportUtil.getHome().create().getCorporationConfig(corporationId, userSession);
    	} catch (Exception e) {
			Object[] parameters = {corporationId};
   		handler.handleException("listInstructorRegistry",e,userSession,parameters);
   	}	
   	
   	return null;
   } 
   	
   	/**
   	 * @ejb.interface-method
        * @ejb.permission role-name = "administrator,corporationmodify, drivercreate, systemaccess"
   	 * Modify a corporation
   	 * @param data - Data with the change of the corporationmenuItems
   	 * @param userRequest - Logged user
        * @throws Exception
   	 */
   	public ArrayList<PointsDefinitionData> getPointsDefinitions(UserSessionData userSession) throws Exception {
   		try{
   			return CorporationReportUtil.getHome().create().getPointsDefinitions(userSession);
    	} catch (Exception e) {
			Object[] parameters = {};
			handler.handleException("getPointsDefinitions",e,userSession,parameters);
    	}	
   	   	return null;
   	} 
   	
   	/**
   	 * @ejb.interface-method
        * @ejb.permission role-name = "administrator,corporationmodify, drivercreate"
   	 * Modify a corporation
   	 * @param data - Data with the change of the corporationmenuItems
   	 * @param userRequest - Logged user
        * @throws Exception
   	 */
   	public HashMap<String,PointsDefinitionData> getPointsDefinition(String[] keys, UserSessionData userSession) throws Exception {
   		return getPointsDefinition(keys, new Integer(userSession.getCorporationId()), userSession);
   	}

   	/**
   	 * @ejb.interface-method
        * @ejb.permission role-name = "administrator,corporationmodify, drivercreate"
   	 * Modify a corporation
   	 * @param data - Data with the change of the corporationmenuItems
   	 * @param userRequest - Logged user
        * @throws Exception
   	 */
   	public HashMap<String,PointsDefinitionData> getPointsDefinition(String[] keys, Integer corporation,UserSessionData userSession) throws Exception {
   		try{
   			return CorporationReportUtil.getHome().create().getPointsDefinition(keys,corporation,userSession);
    	} catch (Exception e) {
			Object[] parameters = {};
			handler.handleException("getPointsDefinition",e,userSession,parameters);
    	}	
   	   	return null;
   	}
   	/**
   	 * @ejb.interface-method
        * @ejb.permission role-name = "administrator,corporationmodify, drivercreate,cepascheduleactivityupdate, systemaccess"
   	 * Modify a corporation
   	 * @param data - Data with the change of the corporationmenuItems
   	 * @param userRequest - Logged user
        * @throws Exception
   	 */
   	public ArrayList<PointsDefinitionData> getPointsDefinitions(String type,UserSessionData userSession) throws Exception {
   		try{
   			return CorporationReportUtil.getHome().create().getPointsDefinitions(type,userSession);
    	} catch (Exception e) {
			Object[] parameters = {};
			handler.handleException("getPointsDefinitions",e,userSession,parameters);
    	}	
   	   	return null;
   	}
   	
 	 
   	
   	
   	/**
   	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,corporationmodify,dpachecklist, drivercreate, driverview, jjreporter, driverviewowndata"
   	 * Modify a corporation
   	 * @param data - Data with the change of the corporationmenuItems
   	 * @param userRequest - Logged user
        * @throws Exception
   	 */
   	public PointAccountData getPointAccount(Integer type,Integer id, UserSessionData userSession) throws Exception {
   		try {
   			return CorporationReportUtil.getHome().create().getPointAccount(type, id, userSession);
    	} catch (Exception e) {
			Object[] parameters = {type,id};
			handler.handleException("getPointAccount",e,userSession,parameters);
    	}	
   	   	return null;
   	}

   	/**
   	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,corporationmodify,dpachecklist, driverview, vehicleview"
   	 * Modify a corporation
   	 * @param data - Data with the change of the corporationmenuItems
   	 * @param userRequest - Logged user
        * @throws Exception
   	 */
   	public PointAccountData getPointAccount(Integer id, UserSessionData userSession) throws Exception {
   		try {
   			return CorporationReportUtil.getHome().create().getPointAccount(id, userSession);
    	} catch (Exception e) {
			Object[] parameters = {id};
			handler.handleException("getPointAccount",e,userSession,parameters);
    	}	
   	   	return null;
   	}

	/**
   	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,driverview"
     * @throws Exception
   	 */
   	public Collection<PointMoveData> getPointMoves(Integer account,UserSessionData userSessionData) throws Exception {
   		Collection<PointMoveData> values = new LinkedList<PointMoveData>();
   		try{
   			return CorporationReportUtil.getHome().create().getPointMoves(account,userSessionData);
    	} catch (Exception e) {
			Object[] parameters = {account};
			handler.handleException("getPointMoves",e,userSessionData,parameters);
		}
   		return values;
   	}

	/**
   	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,driverview"
     * @throws Exception
   	 */
   	public PointMoveData getPointMove(Integer id,UserSessionData userSessionData) throws Exception {
   		try{
   			return CorporationReportUtil.getHome().create().getPointMove(id,userSessionData);
    	} catch (Exception e) {
			Object[] parameters = {id};
			handler.handleException("getPointMove",e,userSessionData,parameters);
		}
   		return null;
   	}

   	
	/**
   	 * @ejb.interface-method
        * @ejb.permission role-name = "administrator,corporationmodify, drivercreate,dpachecklist,pointconfupdate, systemaccess"
   	 * Modify a corporation
   	 * @param data - Data with the change of the corporationmenuItems
   	 * @param userRequest - Logged user
        * @throws Exception
   	 */
   	public PointsDefinitionData getPointsDefinitions(String type,String ref,UserSessionData userSession) throws Exception {
   		try{ 
   			return CorporationReportUtil.getHome().create().getPointsDefinitions(type,ref,userSession);
    	} catch (Exception e) {
			Object[] parameters = {type,ref};
			handler.handleException("getPointsDefinitions",e,userSession,parameters);
		}
   		return null;
   	}
   	

   	/**
   	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     * @throws Exception
   	 */
   	public Collection<DriverPointData> getDriverPoints(String firstName, String lastName, int status, Integer[][] divOrgs, Integer[][] divGeos, String operation, boolean resumed, UserSessionData userSessionData) throws Exception {
   		Hashtable<Integer,DriverPointData> drivers = new Hashtable<Integer,DriverPointData>();
   		Collection<DriverPointData> driverValues = new LinkedList<DriverPointData>();
   		try {
   			Collection values = CorporationReportUtil.getHome().create().getDriverPoints(firstName,lastName,status,divOrgs,divGeos,operation,userSessionData, resumed);
   			Iterator it = values.iterator();
   			while(it.hasNext()) {
   				Object[] data = (Object[]) it.next();
   				Integer driverId = (Integer) data[0];
   				if(drivers.containsKey(driverId)) {
   					drivers.get(driverId).addCategory(data, resumed);
   				}else {
   					drivers.put(driverId, new DriverPointData(data, resumed, userSessionData.getCorporationId()));
   				}
   			}
   			Enumeration<DriverPointData> d = drivers.elements();
   			while(d.hasMoreElements()) 
   				driverValues.add(d.nextElement());
    	} catch (Exception e) {
			Object[] parameters = {firstName,lastName,status,divOrgs,divGeos,operation};
			handler.handleException("getDriverPoints",e,userSessionData,parameters);
		}
   		return driverValues;
   	}

   	/**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     */
   	public FleetBayerData getPointRanges(UserSessionData userSessionData) throws Exception {
   		try {
   			return CorporationReportUtil.getHome().create().getPointRanges(userSessionData);
    	} catch (Exception e) {
			Object[] parameters = {};
			handler.handleException("getPointRanges",e,userSessionData,parameters);
		}
   		return null;
   	}


    /**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     */
   	public Object[] getPointDetail(UserSessionData userSessionData) throws Exception {
   		try {
   			Collection[] details = CorporationReportUtil.getHome().create().getPointDetail(userSessionData);
   			PointDetailBayerData[] totals = new PointDetailBayerData[details.length+1];
   			totals[details.length] = new PointDetailBayerData("total",-1,"");
   			for(int i = 0; i < details.length; i++) {
   				Collection detailCollection = details[i];
   				totals[i] = new PointDetailBayerData("bayer.points.category"+i, -1,"");
   				Iterator it = detailCollection.iterator();
   				while(it.hasNext()) {
   					PointDetailBayerData detailBayerData = (PointDetailBayerData)it.next();
   					totals[i].sumTotals(detailBayerData);
   					totals[details.length].sumTotals(detailBayerData);
   				}
   			}
   			return new Object[]{details,totals};
    	} catch (Exception e) {
			Object[] parameters = {};
			handler.handleException("getPointDetail",e,userSessionData,parameters);
		}
   		return null;
   	}

   	/**
   	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     * @throws Exception
   	 */
   	public HashMap getElectroincs(UserSessionData userSessionData) throws Exception {
   		try {
   			return CorporationReportUtil.getHome().create().getElectroincs(userSessionData);
    	} catch (Exception e) {
			Object[] parameters = {};
			handler.handleException("getElectroincs",e,userSessionData,parameters);
		}
   		return null;
	}

   	/**
   	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     * @throws Exception
   	 */
   	public ResumeData getResume(UserSessionData userSessionData) throws Exception {
   		ResumeData resume = new ResumeData();
   		try {
   			resume.setDrivers(DriverReportUtil.getHome().create().getDriverResume(userSessionData));
   			resume.setVehicles(VehicleReportUtil.getHome().create().getVehicleResume(userSessionData));
   			resume.setEvents(EventReportUtil.getHome().create().getEventResume(userSessionData));
   			resume.setActivities(ActivityReportUtil.getHome().create().getActivityResume(userSessionData));
   			
   		} catch (Exception e) {
			Object[] parameters = {};
			handler.handleException("getResume",e,userSessionData,parameters);
   		}
   		return resume;
   	}

	 /**
     * Returns menu options.
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator"
	 * @throws Exception
	 */
	public Collection<MenuOptionData> getMenuOptions(UserSessionData userSessionData) throws Exception {
   		try {
   			return CorporationReportUtil.getHome().create().getMenuOption(userSessionData);
    	} catch (Exception e) {
			Object[] parameters = {};
			handler.handleException("getMenuOption",e,userSessionData,parameters);
		}
   		return new LinkedList<MenuOptionData>();
	}

	 /**
     * Returns menu options.
     * @ejb.interface-method
     * @ejb.permission role-name = "systemaccess"
	 * @throws Exception
	 */
	public boolean isCepaPoints(Integer coporationId, UserSessionData userSessionData) throws Exception {
   		try {
   			return CorporationReportUtil.getHome().create().isCepaPoints(coporationId,userSessionData);
    	} catch (Exception e) {
			Object[] parameters = {coporationId};
			handler.handleException("isCepaPoints",e,userSessionData,parameters);
		}
   		return false;
	}

}
