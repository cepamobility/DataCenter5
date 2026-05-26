package business.vehicle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.jboss.logging.Logger;

import persistence.activity.tables.Item;
import persistence.activity.tables.ItemValueStr;
import persistence.administration.corporation.CorporationABM;
import persistence.administration.corporation.CorporationABMUtil;
import persistence.administration.corporation.CorporationReport;
import persistence.administration.corporation.CorporationReportUtil;
import persistence.administration.corporation.tables.PointMove;
import persistence.administration.tables.TransporterLink;
import persistence.administration.user.UserReport;
import persistence.administration.user.UserReportUtil;
import persistence.driver.DriverABM;
import persistence.driver.DriverABMUtil;
import persistence.driver.DriverReport;
import persistence.driver.DriverReportUtil;
import persistence.event.EventABM;
import persistence.event.EventABMUtil;
import persistence.event.EventReportUtil;
import persistence.log.LoggerFormatting;
import persistence.vehicle.VehicleABM;
import persistence.vehicle.VehicleABMHome;
import persistence.vehicle.VehicleABMUtil;
import persistence.vehicle.VehicleReport;
import persistence.vehicle.VehicleReportHome;
import persistence.vehicle.VehicleReportUtil;
import business.administration.corporation.CorporationABMFacade;
import business.administration.corporation.CorporationABMFacadeUtil;
import business.alert.handler.Util;
import business.driver.DriverABMFacadeBean;
import business.util.ExceptionHandler;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CheckListData;
import communication.activity.tables.ItemData;
import communication.activity.tables.ItemValueStrData;
import communication.activity.tables.PointData;
import communication.administration.corporation.tables.CorporationConfigData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.PointAccountData;
import communication.administration.corporation.tables.PointMoveData;
import communication.administration.corporation.tables.PointsDefinitionData;
import communication.administration.dpa.UnconformityDPAData;
import communication.administration.dpa.UnconformityItemDPAData;
import communication.administration.dpa.UnconformityTypeDPADataType;
import communication.administration.user.UserSessionData;
import communication.administration.user.tables.UserData;
import communication.alert.AlertConstants;
import communication.datatype.tables.TypeUseDataType;
import communication.datatype.tables.VehicleContractDataType;
import communication.driver.tables.DriverData;
import communication.event.report.ReparationNewDataSource;
import communication.event.tables.ReparationData;
import communication.exception.DeslogedException;
import communication.exception.InvalidDataException;
import communication.language.Text;
import communication.security.SecurityConstants;
import communication.util.LogActions;
import communication.util.MailSender;
import communication.vehicle.tables.ComplaintData;
import communication.vehicle.tables.DPAGoalData;
import communication.vehicle.tables.DPAVehicleActiveData;
import communication.vehicle.tables.TrailerCheckData;
import communication.vehicle.tables.TrailerData;
import communication.vehicle.tables.UnconformityData;
import communication.vehicle.tables.VehicleCheckData;
import communication.vehicle.tables.VehicleContractData;
import communication.vehicle.tables.VehicleData;
import communication.vehicle.tables.VehicleMaintenanceData;
import communication.vehicle.tables.VehiclePursuitRouteData;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * @ejb.bean name="VehicleABMFacade"
 *           display-name="Name for VehicleABMFacade"
 *           description="Description for VehicleABMFacade" 
 *           jndi-name="ejb/VehicleABMFacade"
 *           type="Stateless"
 *           view-type="remote"
 * 			 transaction-type = "Container"
 * 
 * @ejb.ejb-ref ejb-name = "VehicleABM" ref-name = "ejb/VehicleABM" view-type =
 *              "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/VehicleABM" ref-name = "VehicleABM"
 * @jboss.ejb-ref-jndi jndi-name = "ejb/UserReport" ref-name = "UserReport"
 *
 * @ejb.ejb-ref ejb-name = "VehicleReport" ref-name = "ejb/VehicleReport" view-type =
 *              "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/VehicleReport" ref-name = "VehicleReport"
 *
 *
 * @ejb.ejb-ref ejb-name = "EventABM" ref-name = "ejb/EventABM" view-type =
 *              "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/EventABM" ref-name = "EventABM"
 *
 * @ejb.ejb-ref ejb-name = "EventReport" ref-name = "ejb/EventReport" view-type =
 *              "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/EventReport" ref-name = "EventReport"
 *
 * @ejb.ejb-ref ejb-name = "VehicleReportFacade" ref-name = "ejb/VehicleReportFacade" view-type =
 *              "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/VehicleReportFacade" ref-name = "VehicleReportFacade"

 * @ejb.ejb-ref ejb-name = "DriverABM" ref-name = "ejb/DriverABM" view-type ="remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/DriverABM" ref-name = "DriverABM"
 *
 * @ejb.ejb-ref ejb-name = "DriverReport" ref-name = "ejb/DriverReport" view-type ="remote"
 *              
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/DriverReport" ref-name = "DriverReport"
 * 														
 * @jboss.ejb-ref-jndi jndi-name = "ejb/CorporationABM" ref-name = "CorporationABM"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/CorporationABMFacade" ref-name = "CorporationABMFacade"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/CorporationReport" ref-name = "CorporationReport"
 * 
 * @ejb.ejb-ref 
 *          ejb-name ="DataTypeReport"
 *          ref-name = "ejb/DataTypeReport"
 *          view-type ="remote"
 * 
 * @jboss.ejb-ref-jndi
 *          jndi-name = "ejb/DataTypeReport" 
 *          ref-name = "DataTypeReport"
 * 
 * 
 *  * @ejb.util generate="physical"
 */

public class VehicleABMFacadeBean implements SessionBean {

	private ExceptionHandler handler = new ExceptionHandler(VehicleABMFacadeBean.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Logger for this class
	 */
	private Logger logger = Logger.getLogger(DriverABMFacadeBean.class);
	private LoggerFormatting logFormatting = new LoggerFormatting();
	
	private class ThreadProcessChecklist extends Thread{
		public UserSessionData userSessionData;
		public Integer vehiclechecklistId;
		public String user;
		public String password;
		
		public void run(){
			try{
				String urlString="http://localhost:8080/datacenter/webapi/batch/processChecklist?vehicleCheckId="+vehiclechecklistId+"&u="+user+"&p="+password;
				if(SecurityConstants.isProductionServer()){
					urlString="http://localhost:8080/datacenter/webapi/batch/processChecklist?vehicleCheckId="+vehiclechecklistId+"&u="+user+"&p="+password;
				}
				
				URL url = new URL(urlString);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != 200 && conn.getResponseCode() != 204) {
					throw new RuntimeException("Failed Access ProcessChecklist System : HTTP error code : " + conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String output;
				StringBuilder builder=new StringBuilder("");
				while ((output = br.readLine()) != null) {
					builder.append(output);
				}

				System.out.println("Checklist procesado "+builder.toString());
				
				conn.disconnect();
				
			}catch(Throwable th){
				th.printStackTrace();
			}
		}
	}
	
	private class ThreadNotifyMPChecklist extends Thread{
		public UserSessionData userSessionData;
		public Integer vehiclechecklistId;
		public String user;
		public String password;
		
		public void run(){
			try{
				String urlString="http://localhost:8080/datacenter/webapi/batch/notifyMP?vehicleCheckId="+vehiclechecklistId+"&u="+user+"&p="+password;
				if(SecurityConstants.isProductionServer()){
					urlString="http://localhost:8080/datacenter/webapi/batch/notifyMP?vehicleCheckId="+vehiclechecklistId+"&u="+user+"&p="+password;
				}
				
				URL url = new URL(urlString);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != 200 &&   conn.getResponseCode() != 204) {
					throw new RuntimeException("Failed Access Assesment System : HTTP error code : "
							+ conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String output;
				StringBuilder builder=new StringBuilder("");
				while ((output = br.readLine()) != null) {
					builder.append(output);
				}

				System.out.println("Checklist procesado "+builder.toString());
				
				conn.disconnect();
			}catch(Throwable th){
				th.printStackTrace();
			}
		}
	}
	
	public VehicleABMFacadeBean() {
		super();		
	}

	/**
	 * Create method
	 * 
	 * @ejb.create-method
     * @ejb.permission role-name = "administrator,vehiclecreate,vehicleupdate,vehicledelete,drivercreatevehicle,drivervehicleupdate,vehiclechecklist,dpachecklist,jjreporter,systemaccess" 
	 *  
	 */
	public void ejbCreate() throws javax.ejb.CreateException {		
	}

	
	public void setSessionContext(SessionContext ctx)throws EJBException,RemoteException {
	}


	public void ejbRemove() throws EJBException, RemoteException {
	}

	
	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}
	
	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehiclecreate,drivercreatevehicle,dpachecklist"
	 * @param data
	 * @throws Exception
	 */	
	public Integer vehicleCreate(VehicleData data, String comment, UserSessionData userSessionData) throws Exception {
		VehicleABM vehicleABM = null;
		try {	
						
            if(userSessionData.isDriver() && !data.getDriver().getId().equals(userSessionData.getDriverData().getId())) {
                throw new InvalidDataException("vehicleCreate","Invalid driver");
            }
			VehicleABMHome home = VehicleABMUtil.getHome();
			vehicleABM = home.create();
			
			Integer id = vehicleABM.vehicleCreate(data,userSessionData,comment);
			data.setId(id);

			if(userSessionData.isCorporation(new int[]{CorporationData.FORESTALORIENTAL, CorporationData.WEYERHAEUSER})) {
				CorporationABMFacade corpABMFacade=CorporationABMFacadeUtil.getHome().create();
				corpABMFacade.saveVehicleAccount(data.getId(), userSessionData);
			}

			if(userSessionData.isCorporation(new int[] {CorporationData.FORESTALORIENTAL,CorporationData.MONTES_DE_PLATA,CorporationData.WEYERHAEUSER})) {
				Iterator it = data.getDivOrgsIterator();
				if(userSessionData.isCorporation(CorporationData.WEYERHAEUSER)) {
					it.next();
					it.next();
				}
				Integer idDivOrg = ((communication.administration.corporation.tables.ItemData)((DivOrgData)it.next()).getDivOrgLevels().getItemIterator().next()).getId();
	        	int isContractor = communication.administration.corporation.util.Util.getTransporterContractor(idDivOrg);

	        	if(isContractor != -1) {
	        		VehicleReport vehicleReport = VehicleReportUtil.getHome().create();
	        		int[] values = vehicleReport.getVehicleDataLinks(data.getMatricula(), isContractor, userSessionData);
	        		Integer upm = (userSessionData.isCorporation(CorporationData.FORESTALORIENTAL)) ? id : null;
	        		Integer mdp = (userSessionData.isCorporation(CorporationData.MONTES_DE_PLATA)) ? id : null;
	        		Integer lumin = (userSessionData.isCorporation(CorporationData.WEYERHAEUSER)) ? id : null;
	        		
	        		if(values[0] == 0) {
	        			if(isContractor == CorporationData.FRAYLOG) {	
							data.replaceDivOrg(1,TransporterLink.FRAYLOG_DEFAULT_DIVISION, null, null);
							data.replaceDivGeo(null,TransporterLink.FRAYLOG_REGION,TransporterLink.FRAYLOG_COUNTRY);
			        		data.setUse(new TypeUseDataType(TypeUseDataType.INSTITUTIONAL));
			        		data.setDriver(null);
			        		data.setCorporation(new CorporationData(CorporationData.FRAYLOG,"",null,null,null,null));
			        		Integer frayId = vehicleABM.vehicleCreate(data, userSessionData,comment);
			        		sendVehicleEmail(data, userSessionData);
			        		vehicleABM.saveVehicleLinks(frayId, upm, mdp, lumin, CorporationData.FRAYLOG, userSessionData);
	        			}
	        			if(isContractor == CorporationData.SOCTMA) {	
							data.replaceDivOrg(1,TransporterLink.SOCTMA_DEFAULT_DIVISION, null, null);
							data.replaceDivGeo(null,TransporterLink.SOCTMA_REGION,TransporterLink.SOCTMA_COUNTRY);
			        		data.setUse(new TypeUseDataType(TypeUseDataType.INSTITUTIONAL));
			        		data.setDriver(null);
			        		data.setCorporation(new CorporationData(CorporationData.SOCTMA,"",null,null,null,null));
			        		Integer soctmaId = vehicleABM.vehicleCreate(data, userSessionData,comment);
			        		vehicleABM.saveVehicleLinks(soctmaId, upm, mdp, lumin, CorporationData.SOCTMA, userSessionData);
	        			}
	        			if(isContractor == CorporationData.SAN_NICOLAS) {
	        				switch(userSessionData.getCorporationId()) {
	        					case CorporationData.FORESTALORIENTAL:
	    							data.replaceDivOrg(1,TransporterLink.SAN_NICOLAS_UPM, null, null);
	    							break;
	        					case CorporationData.MONTES_DE_PLATA:
	    							data.replaceDivOrg(1,TransporterLink.SAN_NICOLAS_MDP, null, null);
	    							break;
	        					case CorporationData.WEYERHAEUSER:
	    							data.replaceDivOrg(1,TransporterLink.SAN_NICOLAS_LUMIN, null, null);
	    							data.removeDivOrgs(1);
	    							break;
	        				}
	    	            	data.addDivOrg(TransporterLink.SAN_NICOLAS_COSTCENTER_ID,TransporterLink.SAN_NICOLAS_COSTCENTER_NAME,TransporterLink.SAN_NICOLAS_DEAFAULT_COSTCENTER,null,null);
							data.replaceDivGeo(null,TransporterLink.SAN_NICOLAS_REGION,TransporterLink.SAN_NICOLAS_COUNTRY);
			        		data.setUse(new TypeUseDataType(TypeUseDataType.INSTITUTIONAL));
			        		data.setDriver(null);
			        		data.setCorporation(new CorporationData(CorporationData.SAN_NICOLAS,"",null,null,null,null));
			        		Integer snId = vehicleABM.vehicleCreate(data, userSessionData,comment);
			        		vehicleABM.saveVehicleLinks(snId, upm, mdp, lumin, CorporationData.SAN_NICOLAS, userSessionData);
	        			}
					}else {
						if(userSessionData.isForest()) {
							Integer[] links = new Integer[4];
			        		links[0] = values[0];
			        		links[1] = (userSessionData.isCorporation(CorporationData.FORESTALORIENTAL)) ? id : values[1];
			        		links[2] = (userSessionData.isCorporation(CorporationData.MONTES_DE_PLATA)) ? id : values[2];
			        		links[3] = (userSessionData.isCorporation(CorporationData.WEYERHAEUSER)) ? id : values[3];
							vehicleABM.updateVehicleTransporterLinks(links, userSessionData);
						}
					}
	        	}
			}
			return id;

		} catch(Exception e){
			Object[] p = new Object[3];
        	p[0] = data;
        	p[1] = comment;
        	handler.handleException("vehicleCreate",e, userSessionData, p);
	    }
		return null;
	}


	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicledelete"
	 * @param data
	 * @throws Exception
	 */
	public void vehicleDelete(VehicleData data, String comment,UserSessionData userSessionData)
		throws Exception {
		
		
		VehicleABM vehicleABM = null;
		try {

			VehicleABMHome home = VehicleABMUtil.getHome();
			vehicleABM = home.create();

            vehicleABM.desassociateTrailers(data.getId(),userSessionData);
			vehicleABM.vehicleDelete(data,userSessionData,comment);

		} catch(Exception e){
			Object[] p = new Object[3];
        	p[0] = data;
        	p[1] = comment;
        	handler.handleException("vehicleDelete",e, userSessionData, p);
		}
	}
	
	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate,dpachecklist"
	 * @param data
	 * @throws Exception 
	 */
	public void vehicleUpdate(VehicleData data, String comment,UserSessionData userSessionData) 
		throws Exception {
		vehicleUpdate(data,comment,false,userSessionData);
	}
		
	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate,dpachecklist"
	 * @param data
	 * @throws Exception 
	 */
	public void vehicleUpdate(VehicleData data, String comment,boolean asignDriver,UserSessionData userSessionData) 
		throws Exception {
		VehicleABM vehicleABM = null; 
		try {
			VehicleReport vehicleReport = VehicleReportUtil.getHome().create();
			
			boolean changed = false;
			Integer[] divOrgs = null;
			if(!asignDriver) {
				// Nueva funcionalidad para todas las empresas menos FOSA y WEYERHAEUSER
				// Si cambian las divisiones organizacionales del vehÃ­culos se cambian las del conductor
				if(userSessionData.getCorporationId() != CorporationData.WEYERHAEUSER && userSessionData.getCorporationId() != CorporationData.FORESTALORIENTAL) {
					if(data.getDriver() != null) {
						divOrgs = data.getDivOrgIds();
						changed = vehicleReport.hasChangedDivisions(data.getId(),divOrgs,userSessionData);
					}
				}
			}

			VehicleABMHome home = VehicleABMUtil.getHome();
			vehicleABM = home.create();
			vehicleABM.vehicleUpdate(data,userSessionData,comment,LogActions.UPDATE);
			
        	Integer divOrgId = ((communication.administration.corporation.tables.ItemData)((DivOrgData)data.getDivOrgsIterator().next()).getDivOrgLevels().getItemIterator().next()).getId();
        	boolean isContractor = communication.administration.corporation.util.Util.isFraylogContractor(divOrgId);
			if(userSessionData.isCorporation(new int[] {CorporationData.FORESTALORIENTAL, CorporationData.MONTES_DE_PLATA})) {
/*				Integer[] links = {null,null,null}; 
				int[][] values = vehicleReport.getVehicleDataLinks(data.getMatricula(), userSessionData);
				
				if(values[0][0] == 0 && isContractor) {
					if(userSessionData.isCorporation(CorporationData.FORESTALORIENTAL)) { 
						if(values[2][0] != 0 && !communication.administration.corporation.util.Util.isFraylogContractor(values[2][1])) {
							data.replaceDivOrg(1,TransporterLink.FRAYLOG_INACTIVE,null,null);
						}else {
							data.replaceDivOrg(1,TransporterLink.FRAYLOG_DEFAULT_DIVISION,null,null);
						}
					}else{
						if(values[1][0] != 0 && !communication.administration.corporation.util.Util.isFraylogContractor(values[1][1])) {
							data.replaceDivOrg(1,TransporterLink.FRAYLOG_INACTIVE,null,null);
						}else {
							data.replaceDivOrg(1,TransporterLink.FRAYLOG_DEFAULT_DIVISION,null,null);
						}
					}
					data.replaceDivGeo(null,TransporterLink.FRAYLOG_REGION,TransporterLink.FRAYLOG_COUNTRY);
	        		data.setUse(new TypeUseDataType(TypeUseDataType.INSTITUTIONAL));
	        		data.setDriver(null);
	        		data.setCorporation(new CorporationData(CorporationData.FRAYLOG,"",null,null,null,null));
	        		links[0] = vehicleABM.vehicleCreate(data, userSessionData,comment);
					if(userSessionData.isCorporation(CorporationData.FORESTALORIENTAL)) { 
						links[1] = data.getId();
						links[2] = values[2][0];
					}else {
						links[1] = values[1][0];
						links[2] = data.getId();
					}
		        	vehicleABM.saveVehicleLinks(links[0], links[1], links[2], null, userSessionData);
				}else {
					if(values[0][0] != 0) {
						boolean frayLogContractor  = values[0][1] != TransporterLink.FRAYLOG_INACTIVE;
						boolean contractOther = true;
						if(userSessionData.isCorporation(CorporationData.FORESTALORIENTAL)) { 
							contractOther = (values[2][1] == 0)  || communication.administration.corporation.util.Util.isFraylogContractor(values[2][1]);
						}else{
							contractOther = (values[1][1] == 0)  || communication.administration.corporation.util.Util.isFraylogContractor(values[1][1]);
						}
						Integer frayId = null;
						if(!isContractor && frayLogContractor) {
							frayId = TransporterLink.FRAYLOG_INACTIVE;
						}
						if(isContractor && !frayLogContractor && contractOther) {
							frayId = TransporterLink.FRAYLOG_DEFAULT_DIVISION;
						}
						if(frayId != null) {
							vehicleABM.vehicleUpdateContractor(values[0][0], frayId, userSessionData);
						}	        		
					}
				}*/
			}
			if(userSessionData.isCorporation(CorporationData.CONTRACTOR_FORESTAL)) {
				Integer[] links = vehicleReport.getVehicleLinks(data.getId(), userSessionData);
				for(int i = 1; i < links.length; i++) {
					if(links[i] != null) {
						data.setId(links[i]);
						vehicleABM.vehicleSimpleUpdate(data,userSessionData, comment, LogActions.UPDATE);
					}
				}
			}
			if(asignDriver) {
				DriverReport driverReport = DriverReportUtil.getHome().create();
				DriverData driverData = driverReport.findDriverByPrimaryKey(data.getDriver().getId(), userSessionData);

				vehicleABM.updateDivisions(data.getId(),driverData.getDivOrgIds(),userSessionData);
				
			}else if(changed && (userSessionData.checkPermission(SecurityConstants.MODIFY_DRIVER)
					|| userSessionData.checkPermission(SecurityConstants.DRIVER_MODIFY_OWN_DATA))) {
				DriverABM driverABM = DriverABMUtil.getHome().create();
				driverABM.updateDivisions(data.getDriver().getId(),divOrgs,userSessionData);
			}
		} catch(Exception e){
			Object[] p = new Object[3];
        	p[0] = data;
        	p[1] = comment;
        	handler.handleException("vehicleUpdate",e, userSessionData, p);
        }
	}

	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate"
	 * @param data
	 * @throws Exception
	 */
	public void changeVehicleUse(Integer vehicle,Integer driver,String comment,UserSessionData userSessionData)
		throws Exception {

		try {
			DriverReport driverReport = DriverReportUtil.getHome().create();
			DriverData driverData = driverReport.findDriverByPrimaryKey(driver, userSessionData);
			
			VehicleData vehicleData = VehicleReportUtil.getHome().create().findVehicleByPrimaryKey(vehicle,userSessionData);
			vehicleData.setDriver(driverData);

			VehicleABM vehicleABM = VehicleABMUtil.getHome().create();
			vehicleABM.vehicleUpdate(vehicleData,userSessionData,comment,LogActions.VEHICLE_CHANGE_USE);

			vehicleABM.updateDivisions(vehicle,driverData.getDivOrgIds(),userSessionData);

		} catch (Exception e) {
			Object[] parameters = {vehicle,driver,comment};
            handler.handleException("changeVehicleUse", e, userSessionData, parameters);
        }
	}

	/**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,vehicleupdate"
     * 
     * @param data
     * @throws Exception
     */
    public void vehicleExclude(String vehicles,Date exclusionDate, String comment,
    		UserSessionData userRequest) throws Exception {
        
    	VehicleABM vehicleABM = null; 
        try{    
            
            VehicleReportHome reportHome = VehicleReportUtil.getHome();
            VehicleReport vehicleReport = reportHome.create();

            VehicleABMHome home = VehicleABMUtil.getHome();
            vehicleABM = home.create();

            StringTokenizer tokenizer = new StringTokenizer(vehicles,",");
            while(tokenizer.hasMoreTokens()) {
                String vehicleId = tokenizer.nextToken();
                VehicleData vehicleData = vehicleReport.findVehicleByPrimaryKey(new Integer(vehicleId),userRequest);
                vehicleData.setExclusionDate(exclusionDate);
                
                if(userRequest.getCorporationId() != CorporationData.ALLERGAN) {
	                // Nueva funcionalidad, cuando se desactiva un vehÃ­culo se desasocia de su conductor
	                vehicleData.setDriver(null);
	                vehicleData.setUse(new TypeUseDataType(TypeUseDataType.INSTITUTIONAL));
                }
                
                vehicleABM.vehicleUpdate(vehicleData, userRequest, comment, LogActions.EXCLUDE);
            }
        } catch (Exception exception) {
        	Object[] p = new Object[3];
        	p[0] = vehicles;
        	p[1] = exclusionDate;
        	p[2] = comment;
            handler.handleException("vehicleExclude",exception, userRequest, p);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,vehicleupdate"
     * 
     * @param data
     * @throws Exception
     */
    public void vehicleInclude(String vehicles,String comment,
    		UserSessionData userRequest) throws Exception {
        VehicleABM vehicleABM = null; 
        try{    
            if (userRequest == null) {
                throw new DeslogedException("Class business.vehicle.VehicleABMFacade(vehicleUpdate), session data is empty","generic.error.deslogued");
            }

            VehicleReportHome reportHome = VehicleReportUtil.getHome();
            VehicleReport vehicleReport = reportHome.create();

            VehicleABMHome home = VehicleABMUtil.getHome();
            vehicleABM = home.create();

            StringTokenizer tokenizer = new StringTokenizer(vehicles,",");
            while(tokenizer.hasMoreTokens()) {
                String vehicleId = tokenizer.nextToken();
                VehicleData vehicleData = vehicleReport.findVehicleByPrimaryKey(new Integer(vehicleId),userRequest);
                vehicleData.setExclusionDate(null);
                vehicleABM.vehicleUpdate(vehicleData, userRequest, comment, LogActions.INCLUDE);
            }
        } catch (Exception exception) {
        	Object[] p = new Object[2];
        	p[0] = vehicles;
        	p[1] = comment;
            handler.handleException("vehicleInclude",exception,userRequest,p);
        }
    }
    
	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehiclecreate,drivercreatevehicle,vehiclechecklist,dpachecklist,jjreporter"
	 * @param data
	 * @throws Exception
	 */	
	public Integer createOrUpdateVehicleCheck(VehicleCheckData data, String comment,
			UserSessionData userSessionData) throws Exception {

		VehicleABM vehicleABM = null;
		try {		
			
			
			VehicleABMHome home = VehicleABMUtil.getHome();
			vehicleABM = home.create();
			
			Integer vehicleCheckId = vehicleABM.createOrUpdateVehicleCheck(data,comment,userSessionData);

			 
			
			return vehicleCheckId;

		} catch (Exception exception) {
			Object[] p = new Object[2];
			p[0] = data;
			p[1] = comment;
			handler.handleException("createOrUpdateVehicleCheck", exception, userSessionData,p);
	    }
		return null;

	}
	
	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehiclecreate,drivercreatevehicle,vehiclechecklist,dpachecklist"
	 * @param data
	 * @throws Exception
	 */	
	public Integer createOrUpdateVehicleCheckUPMMDP( LinkedList imagesData,VehicleCheckData data, String comment,
			UserSessionData userSessionData,Text messages) throws Exception {
		System.out.println("createOrUpdateVehicleCheckUPMMDP");
		VehicleABM vehicleABM = null;
		try {		
						
			VehicleABMHome home = VehicleABMUtil.getHome();
			vehicleABM = home.create();
			//Pasar a mayusculas y quitar punto en matriculas
			String driver = null;
			Collection<Integer> newItems = new LinkedList<Integer>();
			if ((data.getName() != null) && ((data.getName().equals("80")) || (data.getName().equals("123")) || (data.getName().equals("79")))) {
				Iterator<ItemValueStrData> itemsIter=data.getItemValueSet().iterator();
				 
				while(itemsIter.hasNext()) {
					ItemValueStrData itemData=itemsIter.next();
					int itemId = itemData.getItem().getId().intValue(); 
					if(itemId == 11477 || itemId == 11478 || itemId == 11479 || itemId == 11480 || itemId == 11481 || itemId == 11416) {
						if(itemData.getItemValue()!=null){
							itemData.setItemValue( itemData.getItemValue().replace(".", "").toUpperCase());	
						}
					}
					if(itemId == 11472) {
						driver = itemData.getItemValue();
					}
/*					if(userSessionData.isCorporation(CorporationData.FORESTALORIENTAL) && itemData.getItemValue().equals("2") &&
							(itemId == 17000 || itemId == 17001 || itemId == 17002 || itemId == 17012)) {
						//itemData.setItemTag("Su vehiculo se hubiera detenido inmediatamente");
						newItems.add(itemId);
					}
					if(userSessionData.isCorporation(CorporationData.FORESTALORIENTAL) && itemData.getItemValue().equals("2") &&
							(itemId == 17005 || itemId == 17006 || itemId == 17015 || itemId == 17016)) {
						//itemData.setItemTag("Su vehiculo tendría 48 horas para el arreglo");
						newItems.add(itemId);
					}*/
				}
			}
			
			boolean newCheck = data.getVehicleCheckId() == null;
			Integer vehicleCheckId = vehicleABM.createOrUpdateVehicleCheck(data,comment,userSessionData);
			uploadCheckListImagesBase64(imagesData, vehicleCheckId, userSessionData);
			
			if(Util.isNumber(driver) && data.getName().equals("80") && newCheck) {
				Collection drivers = DriverReportUtil.getHome().create().findDriverByCorporationId(driver, userSessionData);
				if(drivers.size() > 0) {
					DriverABMUtil.getHome().create().addDriverCheck(((DriverData)drivers.iterator().next()).getId(), vehicleCheckId, userSessionData);
				}
			}

			if(!doNotProccess(data)) {
				if(userSessionData.isCorporation(new int[] {CorporationData.FORESTALORIENTAL,CorporationData.MONTES_DE_PLATA,CorporationData.WEYERHAEUSER})) {
					VehicleReport vehicleReport = VehicleReportUtil.getHome().create();
					Integer[] ids = vehicleReport.getVehicleLinks(data.getVehicle().getId(), userSessionData);
					if(ids != null) {
						ReparationData reparation = data.getFraylogReparation(userSessionData.getFilter().getFirstName().toLowerCase(),userSessionData.getFilter().getLastName().toLowerCase(), userSessionData.getCorporationId());
						if(reparation != null) {
							VehicleData vehicle = vehicleReport.findVehicleTransporter(ids[0], userSessionData);
							if(vehicle != null) {
								reparation.setVehicle(vehicle);
								if(reparation != null) {
									EventABM eventABM = EventABMUtil.getHome().create();
									Integer repId = eventABM.createReparation(reparation, userSessionData);
									reparation = EventReportUtil.getHome().create().findReparationByPrimaryKey(repId, userSessionData);
			
						    		MailSender sender = new MailSender();
			
						    		if(userSessionData.isCorporation(CorporationData.FRAYLOG)) {
						    			Collection<Integer> sendTo = new LinkedList<Integer>();
						    			boolean isVolvo = reparation.getVehicle().getTrade().getResourceBundleId().equals("Volvo");
					        			String subdivision = reparation.getVehicle().getData(CEPAActivityScheduleData.DIV_ORG,1,2); 
					        			if(subdivision.equals("Kemira - FB") || subdivision.equals("MDP - FB") || subdivision.equals("UPM - FB")) {
								    		if(reparation.checkGroup(1)) { //camion
								    			if(isVolvo) {
								    				sendTo.add(ReparationData.FRAYLOG_FB);
								    				sendTo.add(ReparationData.TALLER);
								    			}
							    				sendTo.add(ReparationData.MANTENIMIENTO);
								    		}
								    		if(reparation.checkGroup(2)) { //caja de carga
								    			if(!sendTo.contains(ReparationData.FRAYLOG_FB))
								    				sendTo.add(ReparationData.FRAYLOG_FB);
								    			sendTo.add(ReparationData.METALURGIA);
								    			if(!sendTo.contains(ReparationData.MANTENIMIENTO))
								    				sendTo.add(ReparationData.MANTENIMIENTO);
								    		}
								    		if(reparation.checkGroup(3)) { //gomeria
								    			sendTo.add(ReparationData.GOMERIA);
								    			sendTo.add(ReparationData.GOMERIA_2);
								    			sendTo.add(ReparationData.RESPAGNOLO);
								    		}
								    		if(reparation.checkGroup(4)) { //seguridad
								    			sendTo.add(ReparationData.SIG);
								    		}
					        			} else if(subdivision.equals("Celulosa - PdT") || subdivision.equals("UPM - PdT")) {
					        				sendTo.add(ReparationData.RESPAGNOLO);
								    		if(reparation.checkGroup(1)) { //metalurgia
								    			if(isVolvo)
								    				sendTo.add(ReparationData.FRAYLOG_CN);
								    			else {
									    			if(!sendTo.contains(ReparationData.MANTENIMIENTO))
									    				sendTo.add(ReparationData.MANTENIMIENTO);
								    			}
								    		}
								    		if(reparation.checkGroup(2)) { //metalurgia
								    			if(!sendTo.contains(ReparationData.FRAYLOG_CN))
								    				sendTo.add(ReparationData.FRAYLOG_CN);
								    		}
								    		if(reparation.checkGroup(3)) { //gomeria
								    			sendTo.add(ReparationData.GOMERIA);
								    			sendTo.add(ReparationData.GOMERIA_2);
								    			sendTo.add(ReparationData.GOMERIA_LOSTOROS);
								    		}
								    		if(reparation.checkGroup(4)) { //seguridad
								    			sendTo.add(ReparationData.SIG);
								    		}
					        			} else if(subdivision.equals("MDP - Centro")) {
								    		if(reparation.checkGroup(1)) { //camion
								    			if(isVolvo)
								    				sendTo.add(ReparationData.FRAYLOG_DZ);
								    			if(!sendTo.contains(ReparationData.MANTENIMIENTO))
								    				sendTo.add(ReparationData.MANTENIMIENTO);
								    			sendTo.add(ReparationData.C_PEREZ);
								    		}
								    		if(reparation.checkGroup(2)) { //metalurgia
								    			if(!sendTo.contains(ReparationData.FRAYLOG_DZ))
								    				sendTo.add(ReparationData.FRAYLOG_DZ);
								    			if(!sendTo.contains(ReparationData.MANTENIMIENTO))
								    				sendTo.add(ReparationData.MANTENIMIENTO);
								    			if(!sendTo.contains(ReparationData.C_PEREZ))
								    				sendTo.add(ReparationData.C_PEREZ);
								    		}
								    		if(reparation.checkGroup(3)) { //gomeria
								    			if(!sendTo.contains(ReparationData.GOMERIA))
								    				sendTo.add(ReparationData.GOMERIA);
								    			if(!sendTo.contains(ReparationData.GOMERIA_2))
								    				sendTo.add(ReparationData.GOMERIA_2);
								    			if(!sendTo.contains(ReparationData.MANTENIMIENTO))
								    				sendTo.add(ReparationData.MANTENIMIENTO);
								    			if(!sendTo.contains(ReparationData.C_PEREZ))
								    				sendTo.add(ReparationData.C_PEREZ);
								    		}
								    		if(reparation.checkGroup(4)) { //seguridad
								    			if(!sendTo.contains(ReparationData.SIG))
								    				sendTo.add(ReparationData.SIG);
								    			if(!sendTo.contains(ReparationData.C_PEREZ))
								    				sendTo.add(ReparationData.C_PEREZ);
								    		}
					        			}
					        			Iterator<Integer> it = sendTo.iterator();
					        			while(it.hasNext()) {
					    	    			sendReparationEmail(sender, reparation, messages, userSessionData, it.next());
					        			}
						    		}else if(userSessionData.isCorporation(CorporationData.SOCTMA)){
						    			sendReparationEmail(sender, reparation, messages, userSessionData, ReparationData.SOCTMA);
						    		}else {
						    			sendReparationEmail(sender, reparation, messages, userSessionData, -1);
						    		}
								}
							}
						}
					}
				}
				/*if(newItems.size() > 0 && true) {
					sendMailNewItems(data.getVehicle(), newItems, messages, userSessionData);
				}*/
	
				if(AlertConstants.isProductionServer()) {
					System.out.println("AGREGAR CHEQUEO. isProductionServer");
					if((data.getName() != null) && ((data.getName().equals("80")) || (data.getName().equals("123")) || (data.getName().equals("79")) || (data.getName().equals("84")))) {
						//UPM MDP
						try{
							//procesarCheckList(vehicleCheckId,messages,userSessionData);
							
							System.out.println("ThreadProcessChecklist");
	
							UserReport userReport=UserReportUtil.getHome().create();
							UserData userData=userReport.findUserByPrimaryKey(userSessionData.getFilter().getLoginName(), userSessionData);
						    ThreadProcessChecklist threadStart=new ThreadProcessChecklist();
						    threadStart.userSessionData=userSessionData;
						    threadStart.vehiclechecklistId=vehicleCheckId;
						    threadStart.user=userData.getLoginName();
						    threadStart.password=userData.getPassword();
						    threadStart.start();
						}catch(Throwable th){
							th.printStackTrace();
							//Se guarda si o si el checklist, si hay error en la notificación
							
						}
					}else 		
						if(data.getName()!=null && (data.getName().equals("92"))&& userSessionData.getCorporationId()==CorporationData.MONTES_DE_PLATA){
							//UPM Mercancias peligrosas
							try{
								//notifyChecklistMP(vehicleCheckId,messages,userSessionData);
								
								 
								ThreadNotifyMPChecklist threadStart=new ThreadNotifyMPChecklist();
								UserReport userReport=UserReportUtil.getHome().create();
								UserData userData=userReport.findUserByPrimaryKey(userSessionData.getFilter().getLoginName(), userSessionData);
							    
							    threadStart.userSessionData=userSessionData;
							    threadStart.vehiclechecklistId=vehicleCheckId;
							    threadStart.user=userData.getLoginName();
							    threadStart.password=userData.getPassword();
							    
							    threadStart.start();
							}catch(Throwable th){
								th.printStackTrace();
								//Se guarda si o si el checklist, si hay error en la notificación
								
							}
						}
					}else {
						System.out.println("procesarCheckList("+vehicleCheckId+");");
						procesarCheckList(vehicleCheckId, messages, userSessionData);
					}
			}			
			return vehicleCheckId;

		} catch (Exception exception) {
			Object[] p = new Object[2];
			p[0] = data;
			p[1] = comment;
			handler.handleException("createOrUpdateVehicleCheckUPMMDP", exception, userSessionData,p);
	    }finally{
	    	System.out.println("Fin_createOrUpdateVehicleCheckUPMMDP");
	    }
		return null;

	}
	
	private boolean doNotProccess(VehicleCheckData data) {
		Collection<String> v = new LinkedList<String>();
		v.add("OTP3868");
		v.add("ATP5280");
		v.add("ATP2459");
		v.add("ATP6290");
		v.add("ATP5266");
		v.add("ATP6115");
		v.add("ATP3770");
		v.add("ATA4152");
		v.add("RTP3742");
		v.add("NTP5679");
		v.add("MTP5353");
		v.add("MTP3620");
		v.add("ATP6251");
		v.add("PTP1843");
		if(v.contains(data.getVehicle().getMatricula())) {
			Calendar c = Calendar.getInstance();
			c.setTime(data.getDate());
			int d = c.get(Calendar.DATE); 
			return (d == 18 || d == 19) && c.get(Calendar.MONTH) == Calendar.JANUARY && c.get(Calendar.YEAR) == 2024;	
					
		}
		return false;
	}


	private void sendReparationEmail(MailSender sender, ReparationData reparation, Text messages, UserSessionData userSessionData, int type) throws Exception {
    	VehicleData vehicle = reparation.getVehicle();
		String[] fileValues = generateReparationReport(reparation, type, messages, userSessionData.getCorporationId());
    	
        String plate = vehicle.getMatricula();
        if(vehicle.getAlias() != null)
        	plate = vehicle.getAlias()+" ("+plate+")";

		Collection<String> files = new LinkedList<String>();
		Collection<String> names = new LinkedList<String>();
		files.add(fileValues[1]);
		names.add(fileValues[0]);
		
		String user = "";
		String email = "";
		String corporation = "Fraylog";
		if(type == -1) {
			corporation = "San Nicolas";
			HashMap<String, CorporationConfigData> map = CorporationReportUtil.getHome().create().getCorporationConfig(vehicle.getCorporation().getId(), userSessionData);
			int id = vehicle.getDivOrgId(1);
			if(id > 0) {
				String key = String.valueOf(id);
				if(id == 660897 || id == 660896) {
					key += "_"+vehicle.getDivOrgId(0);
				}
				if(map.containsKey("reparation_user_"+key)) {
					user = map.get("reparation_user_"+key).getValue();
					if(map.containsKey("reparation_mail_"+key)) {
						email = map.get("reparation_mail_"+key).getValue();
					}
				}
			}
			if(user.length() == 0 || email.length() == 0) {
				if(map.containsKey("reparation_user")) {
					user = map.get("reparation_user").getValue();
				}
				if(map.containsKey("reparation_mail")) {
					email = map.get("reparation_mail").getValue();				
				}
			}else {
				String[] listUser = user.split(",");
				String[] listEmail = email.split(",");
				for(int i = 0; i < listUser.length; i++) {
					String userSN = listUser[i];
					String emailSN = listEmail[i];
					
					Collection<String> addressTo = new LinkedList<String>();
					Collection<String> bcc = new LinkedList<String>();

					String link = "http://fdmpro.cepasafedrive.com/datacenter/home.jsp?reporter="+userSN+"&reparation="+reparation.getId();
					addressTo.add(emailSN);
					if(!AlertConstants.isProductionServer()) {
						link = "http://localhost:8080/datacenter/home.jsp?reporter="+userSN+"&reparation="+reparation.getId();
					}
					
					if(type == ReparationData.MANTENIMIENTO) {
						//bcc.add("cepasafedrive@gmail.com");
					}
					String body = "<html>" +
							"<div>Informamos de el ingreso de una solicitud de reparaci&oacute;n.</div>";
					if(userSessionData.isCorporation(CorporationData.FRAYLOG) && reparation.getInserted() != null) {
						body += "<div>Fecha y hora del ingreso: "+new SimpleDateFormat("yyyy-MM-dd HH:mm").format(reparation.getInserted())+".</div>";
					}
					body += "<div>Los datos en archivo adjunto.</div>" +
							"<div>Para ingresar las reparaciones realizadas ingrese a <a href='"+link+"'>"+link+"</a></div>" +
							"<div>Atte.</div>" +
							"<div>CEPA Safe Drive.</div>" +
							"</html>";
					boolean sended = false;
					int count = 0;
					while(!sended && count < 10) {
						try {
							sended = sender.sendImageMandrill(addressTo, corporation + " FDM", AlertConstants.EMAIL_SUPPORT, 
									"Sol. de Rep. Ingresada - "+plate, body, files,
									names, 
									"", "", bcc,null);
						}catch (Exception e) {
							e.printStackTrace();
						}
						count++;
					}
				}
				return;
			}
		}else {
			if(vehicle.getCorporation().getId().equals(CorporationData.SOCTMA)) {
				corporation = "Soctma";
			}
			user = ReparationData.LOGIN_REPARATION_USERS[type];
			email = ReparationData.LOGIN_REPARATION_EMAILS[type];
		}
		
		if(!AlertConstants.isProductionServer()) {
			email = "jrodriguez@cepasafedrive.com";
		}
		
		Collection<String> addressTo = new LinkedList<String>();
		Collection<String> bcc = new LinkedList<String>();

		String link = "http://fdmpro.cepasafedrive.com/datacenter/home.jsp?reporter="+user+"&reparation="+reparation.getId();
		addressTo.add(email);
		if(!AlertConstants.isProductionServer()) {
			link = "http://localhost:8080/datacenter/home.jsp?reporter="+user+"&reparation="+reparation.getId();
		}
		
		if(type == ReparationData.MANTENIMIENTO) {
			//bcc.add("cepasafedrive@gmail.com");
		}
		String body = "<html>" +
				"<div>Informamos de el ingreso de una solicitud de reparaci&oacute;n.</div>";
		if(userSessionData.isCorporation(CorporationData.FRAYLOG) && reparation.getInserted() != null) {
			body += "<div>Fecha y hora del ingreso: "+new SimpleDateFormat("yyyy-MM-dd HH:mm").format(reparation.getInserted())+".</div>";
		}
		body += "<div>Los datos en archivo adjunto.</div>" +
				"<div>Para ingresar las reparaciones realizadas ingrese a <a href='"+link+"'>"+link+"</a></div>" +
				"<div>Atte.</div>" +
				"<div>CEPA Safe Drive.</div>" +
				"</html>";
		boolean sended = false;
		int count = 0;
		while(!sended && count < 10) {
			try {
				sended = sender.sendImageMandrill(addressTo, corporation + " Data Center", AlertConstants.EMAIL_SUPPORT, 
						"Sol. de Rep. Ingresada - "+plate, body, files,
						names, 
						"", "", bcc,null);
			}catch (Exception e) {
				e.printStackTrace();
			}
			count++;
		}
	}

	public String[] generateReparationReport(ReparationData reparation, int type, Text messages, int corporation) throws Exception {
		VehicleData vehicle = reparation.getVehicle();
		String[] fileValues = new String[2]; 
				
        String plate = vehicle.getMatricula();
        if(corporation == CorporationData.FRAYLOG && vehicle.getAlias() != null)
        	plate = vehicle.getAlias()+" ("+plate+")";

        fileValues[0] = "Reparacion "+plate+".pdf";
        switch(corporation) {
	        case CorporationData.FRAYLOG:
	    		fileValues[1] = AlertConstants.TEMP_DIRECTORY_REPARATIONS_FRAYLOG+"out/"+fileValues[0];
	    		break;
	        case CorporationData.SAN_NICOLAS:
	    		fileValues[1] = AlertConstants.TEMP_DIRECTORY_REPARATIONS_SANNICOLAS+"out/"+fileValues[0];
	    		break;
	        default:
	    		fileValues[1] = AlertConstants.DEFAULT_DIRECTORY_REPARATIONS+"out/"+fileValues[0];
	    		break;
	    }


        HashMap<String,Object> parameters2 = new HashMap<String,Object>();
        parameters2.put("header", AlertConstants.TEMP_DIRECTORY_REPARATIONS_FRAYLOG+"header.jpg");
        parameters2.put("footer", AlertConstants.TEMP_DIRECTORY_REPARATIONS_FRAYLOG+"footer.jpg");
        parameters2.put("logo_cepa", AlertConstants.TEMP_DIRECTORY_REPARATIONS_FRAYLOG+"logo_cepa.png");
        switch(corporation) {
	        case CorporationData.FRAYLOG:
	        	parameters2.put("logo_fraylog", AlertConstants.TEMP_DIRECTORY_REPARATIONS_FRAYLOG+"logo_fraylog.png");
	    		break;
	        case CorporationData.SAN_NICOLAS:
	        	parameters2.put("logo_fraylog", AlertConstants.TEMP_DIRECTORY_REPARATIONS_SANNICOLAS+"logo_sannicolas.png");
	    		break;
	    }
        parameters2.put("title", messages.getText("generic.data.reparation"));
        parameters2.put("obsGText", messages.getText("generic.messages.observations"));
        parameters2.put("back1", AlertConstants.TEMP_DIRECTORY_REPARATIONS_FRAYLOG+"back1.jpg");
        parameters2.put("back2",AlertConstants.TEMP_DIRECTORY_REPARATIONS_FRAYLOG+"back2.jpg");
        DriverData driver = reparation.getDriver();
        
        parameters2.put("dateName", messages.getText("generic.event.eventdata.date")+":");
        parameters2.put("date", Util.formatDate(reparation.getDate()));
        parameters2.put("kmsName", messages.getText("generic.reparation.kms")+":");
        if(reparation.getKms() != null)
        	parameters2.put("kms", String.valueOf(reparation.getKms()));

        parameters2.put("driverName", messages.getText("generic.data.driver")+":");
        parameters2.put("driver", driver.getFirstName()+" "+driver.getLastName());
        parameters2.put("plateName", messages.getText("generic.data.vehicle")+":");
        parameters2.put("plate", plate);
        parameters2.put("tradeName", messages.getText("generic.vehicle.vehicledata.trade")+":");
        parameters2.put("trade", messages.getText(vehicle.getTrade().getResourceBundleId()));
        parameters2.put("modelName", messages.getText("generic.vehicle.vehicledata.model")+":");
        parameters2.put("model", vehicle.getModel());
        parameters2.put("yearName", messages.getText("generic.vehicle.vehicledata.year")+":");
        parameters2.put("year", String.valueOf(vehicle.getYear()));
        parameters2.put("obsText", messages.getText("generic.event.eventdata.comment"));
        parameters2.put("obsValue", reparation.getObservation());

        ReparationNewDataSource dataSourceUn = new ReparationNewDataSource(reparation, type, messages);
        //JasperCompileManager.compileReportToFile(AlertConstants.TEMP_DIRECTORY_REPARATIONS_FRAYLOG+"reparationnew.jrxml", AlertConstants.TEMP_DIRECTORY_REPARATIONS_FRAYLOG+"reparationnew.jasper");
        FileInputStream input2 = new FileInputStream(new File(AlertConstants.TEMP_DIRECTORY_REPARATIONS_FRAYLOG+"reparationnew.jasper"));

        JasperReport jasperReport = (JasperReport)JRLoader.loadObject(input2);
        FileOutputStream out2 = new FileOutputStream(new File(fileValues[1]));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters2, dataSourceUn);
       
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out2);
        exporter.exportReport();       
        out2.flush();
        out2.close();

        return fileValues;
	}

	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehiclecreate,drivercreatevehicle,vehiclechecklist,dpachecklist"
	 * @param data
	 * @throws Exception
	 */	
	public Integer createOrUpdateVehicleCheckSyngentaBR( LinkedList imagesData,VehicleCheckData data, String comment,
			UserSessionData userSessionData,Text messages) throws Exception {
		System.out.println("createOrUpdateVehicleCheckSyngentaBR");
		VehicleABM vehicleABM = null;
		try {		
						
			VehicleABMHome home = VehicleABMUtil.getHome();
			vehicleABM = home.create();
			//Pasar a mayusculas y quitar punto en matriculas
						
			Integer vehicleCheckId = vehicleABM.createOrUpdateVehicleCheck(data,comment,userSessionData);
			uploadCheckListImagesBase64(imagesData, vehicleCheckId, userSessionData);
			
			try{
				notifyChecklistSyngentaBR(vehicleCheckId, messages, userSessionData); 
			}catch(Throwable th){
				th.printStackTrace();
				//Se guarda si o si el checklist, si hay error en la notificación
				
			}
			
			
			return vehicleCheckId;

		} catch (Exception exception) {
			Object[] p = new Object[2];
			p[0] = data;
			p[1] = comment;
			handler.handleException("createOrUpdateVehicleCheckSyngentaBR", exception, userSessionData,p);
	    }finally{
	    	System.out.println("createOrUpdateVehicleCheckSyngentaBR");
	    }
		return null;

	}
	
	private void notifyChecklistSyngentaBR(Integer vehicleCheckId, Text messages,UserSessionData userSys) throws Exception {
		// TODO Auto-generated method stub
		VehicleReport vehicleReport=VehicleReportUtil.getHome().create();
		VehicleReportFacade vehicleReportFacade=VehicleReportFacadeUtil.getHome().create();
	    CorporationABM corpABM=CorporationABMUtil.getHome().create();
	    
		VehicleCheckData data=vehicleReport.findVehicleCheck(vehicleCheckId, userSys); 
		VehicleData vehicleData=vehicleReport.findVehicleByPrimaryKey(data.getVehicle().getId(), userSys);
		CheckListData checkList = vehicleReport.getCheckList(new Integer(data.getName()),userSys);
		
		String pathReport=vehicleReportFacade.reportChecklistPDFSyngentaBR("checklistSBR.pdf", checkList, data, userSys, messages);
		 
			
        MailSender sender = new MailSender();
		Collection bcc = new LinkedList();
		Collection<String> files = new LinkedList();
		Collection<String> names = new LinkedList();
		
		files.add(pathReport);
		names.add("checklistSBR_"+vehicleData.getMatricula()+".pdf");
		 
		String castomerEmail="f.millan@cepasafedrive.com";
		
		//bcc.add("fcarriquiry@cepasafedrive.com");
		
		Collection addressTo = new LinkedList();
		boolean test=false;
		
		if(data.getSerie()!=null && data.getSerie().contains("@")){
			addressTo.add(data.getSerie());
			bcc.add(castomerEmail);
			bcc.add("iveicular@cepasafedrive.com");
		}else{
			addressTo.add(castomerEmail);
			addressTo.add("iveicular@cepasafedrive.com");
		}
		UserReport userReport=UserReportUtil.getHome().create();
		String mail=userReport.findUserByPrimaryKey(userSys.getFilter().getLoginName(), userSys).getEmail();
		bcc.add(mail);
		
		String body="";
		body+="<p>Em anexo enviamos o resumo.</p>";
		String subject= "CEPA - Placa."+vehicleData.getMatricula()+"- Checklist Syngenta";
		boolean sended = false;
		int count = 0;
		while(!sended && count < 10 && (bcc.size()+addressTo.size())>0 ) {
			try {
				sended =sender.sendImageMandrill(addressTo, AlertConstants.DIGITAL_SIGNATURE_SYNGENTA_BR, AlertConstants.EMAIL_SYNGENTA_BR, subject, 
						Util.getCompleteHTMLMail(body,AlertConstants.DIGITAL_SIGNATURE_SYNGENTA_BR, AlertConstants.EMAIL_SYNGENTA_BR), 
						files, names, AlertConstants.EMAIL_SYNGENTA_BR,
						AlertConstants.DIGITAL_SIGNATURE_SYNGENTA_BR, bcc, null);
			}catch (Exception e) {
				e.printStackTrace();
			}
			count++; 
		}

		 
	}
	
	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehiclecreate,drivercreatevehicle,dpachecklist,systemaccess"
	 * @param data
	 * @throws Exception
	 */	
	public void notifyChecklistMP(Integer vehicleCheckId, Text messages,UserSessionData userSys) throws Exception {
		// TODO Auto-generated method stub

		VehicleReport vehicleReport=VehicleReportUtil.getHome().create();
		VehicleReportFacade vehicleReportFacade=VehicleReportFacadeUtil.getHome().create();
	    CorporationABM corpABM=CorporationABMUtil.getHome().create();
	    CorporationReport corpReport=CorporationReportUtil.getHome().create();
		CorporationABMFacade corpABMFacade=CorporationABMFacadeUtil.getHome().create();
		
		VehicleCheckData data=vehicleReport.findVehicleCheck(vehicleCheckId, userSys); 
		VehicleData vehicleData=vehicleReport.findVehicleByPrimaryKey(data.getVehicle().getId(), userSys);
		CheckListData checkList = vehicleReport.getCheckList(new Integer(data.getName()),userSys);
		
		String pathReport=vehicleReportFacade.reportChecklistPDFMP("checklistMP.pdf", checkList, data, userSys, messages);
		boolean negativePoints=false;
		Iterator<PointData> points = checkList.getSortPoints();
		while(points.hasNext() && !negativePoints ) {
			PointData point = (PointData)points.next();
			Iterator items = point.getSortItems();
			if(items.hasNext()) {
				ItemData item = (ItemData)items.next();
				int value = -1;
				String pointComment = null;
				if(data != null) {
					String checkValue = data.getValueFromItem(item.getId()).trim();
					value = (checkValue == null || !checkValue.equals("2")) ? -1 : 2;
				}
				if(value==2){
					negativePoints=true;//Si es Seguimiento en ruta, no hay parada y va siempre la notificacion.
				}
			}
		}
		
		if(negativePoints){
			
	        MailSender sender = new MailSender();
			Collection bcc = new LinkedList();
			Collection<String> files = new LinkedList();
			Collection<String> names = new LinkedList();
			
			String guiaName="Guia_No_Conforidades_Contratistas.pdf";
			String guiaFile=AlertConstants.TEMP_DIRECTORY_DPA+"checklist/Guia_No_Conforidades_Contratistas.pdf";
		
			 files.add(pathReport);
			 names.add("checklistMP_"+vehicleData.getMatricula()+".pdf");
			
			 Iterator iter= data.getVehicle().getDivOrgsIterator();
			 String transportista="";
			 Integer divId=0;
			 if(iter.hasNext()){
				DivOrgData divData=(DivOrgData)iter.next();
				
				Collection colAux=divData.getDivOrgLevels().getItems();
				if(colAux!=null){
					Iterator iter2=divData.getDivOrgLevels().getItemIterator();
					if(iter2.hasNext()){
						communication.administration.corporation.tables.ItemData item=(communication.administration.corporation.tables.ItemData)iter2.next();
						divId=item.getId();
						transportista=item.getName();
					}
				}
			 }
			 
			String castomerEmail="fcarriquiry@cepasafedrive.com";
			
			//bcc.add("fcarriquiry@cepasafedrive.com");
			
			Collection addressTo = new LinkedList();
			boolean test=false;
			
			if(SecurityConstants.isProductionServer() || test){
				
				castomerEmail="fcarriquiry@cepasafedrive.com";
			} 
			 
			addressTo.add(castomerEmail);
			
			String body="";
			body+="<p>Adjunto le enviamos el &uacute;ltimo checklist de mercanc&iacute;as peligrosas realizado por CEPA el d&iacute;a de hoy.</p>";
			String subject= "CEPA -"+"Check list MP Notificación"+" "+transportista;
			boolean sended = false;
			int count = 0;
			while(!sended && count < 10 && (bcc.size()+addressTo.size())>0 ) {
				try {
					sended =sender.sendImageMandrill(addressTo, "CEPA DataCenter", "datacenter@cepasafedrive.com",
							subject, Util.getCompleteHTMLMail(body,"CEPA DataCenter", "datacenter@cepasafedrive.com")
							, files, names, "datacenter@cepasafedrive.com", "CEPA DataCenter", bcc,null);
				}catch (Exception e) {
					e.printStackTrace();
				}
				count++; 
			}

		}	
	}

	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehiclecreate,drivercreatevehicle,dpachecklist,systemaccess"
	 * @param data
	 * @throws Exception
	 */	
	public void procesarCheckList(Integer vehicleCheckId,Text messages,UserSessionData userSys) throws NumberFormatException, Exception {
		// Checklist 80 Pesados UPM y MDP
			
			VehicleReport vehicleReport=VehicleReportUtil.getHome().create();
			VehicleReportFacade vehicleReportFacade=VehicleReportFacadeUtil.getHome().create();
		    CorporationABM corpABM=CorporationABMUtil.getHome().create();
		    CorporationReport corpReport=CorporationReportUtil.getHome().create();
			CorporationABMFacade corpABMFacade=CorporationABMFacadeUtil.getHome().create();
			
		    HashMap<String,CorporationConfigData> conf= corpReport.getCorporationConfig(userSys.getCorporationId(), userSys);
			
			VehicleCheckData data=vehicleReport.findVehicleCheck(vehicleCheckId, userSys); 
			VehicleData vehicleData=vehicleReport.findVehicleByPrimaryKey(data.getVehicle().getId(), userSys);
			
			boolean mp = data.getName().equals("92");
			boolean internos = data.getName().equals("84");
			boolean pesados = data.getName().equals("80") || internos;
			boolean seguimiento = data.getName().equals("79");
		    boolean preventivo = data.getName().equals("123");

			boolean sendToSIGMDP=false;
			
			CheckListData checkList = vehicleReport.getCheckList(new Integer(data.getName()),userSys);
			UnconformityDPAData unconf=new UnconformityDPAData();
			unconf.setComment(data.getObs());
			unconf.setCheck(vehicleCheckId);
			unconf.setDate(data.getDate());
			unconf.setUserId(userSys.getFilter().getLoginName());
			unconf.setComment(data.getInspectedby());
			unconf.setStatus(UnconformityDPAData.STATUS_NEW);
			unconf.setType(new UnconformityTypeDPADataType(UnconformityTypeDPADataType.TYPE_VEHICLE_CHECK));
			//Se manda notificación solo si tiene puntos o general que indica paraada inmediata, 24 o 48 horas
			boolean negativePoints=false;
			boolean war=false;
			boolean notifItems=false;
			
			Iterator<PointData> points = checkList.getSortPoints();
			String obsNegativePoints="Inspector:"+data.getInspectedby();
			String lastPoint="";
			String driver="";
			while(points.hasNext()) {
				PointData point = (PointData)points.next();
				if(point.isProcesable(vehicleData, userSys.getCorporationId())) {
					Iterator items = point.getSortItems();
					if(items.hasNext()) {
						ItemData item = (ItemData)items.next();
						if(item.getText().equals("forestales.check2.h1") || item.getText().equals("forestales.checkinternal2.h1")){
							driver=messages.getText(data.getValueFromItem(item));
						}
						int value = -1;
						String pointComment = null;
						if(data != null) {
							String checkValue = data.getValueFromItem(item.getId()).trim();
							value = (checkValue == null || !checkValue.equals("2")) ? -1 : 2;
						}//
						if(value==2) {
							if(conf.containsKey("unconformities_cc_items") && conf.get("unconformities_cc_items").getValue().contains(item.getId().toString())){
								notifItems=true;
							}
							
							
							if(obsNegativePoints.length()>0){
								obsNegativePoints+="-";
							}
							if(!lastPoint.equals(messages.getText(point.getText()))){
								obsNegativePoints+=" Categoría:"+messages.getText(point.getText())+"\n\r";
								lastPoint=messages.getText(point.getText());
							}
							
							pointComment=data.getValueFromCommentPoint(point.getId());
							UnconformityItemDPAData itemU=new UnconformityItemDPAData();
							itemU.setComment(pointComment);
							itemU.setNotify(true);
							itemU.setType(item.getText());
							Calendar cal=Calendar.getInstance();
							cal.setTime(data.getDate());
							String tag=data.getValueTagFromItem(item.getId());
							cal.add( Calendar.DATE, tag!=null?Integer.valueOf(tag):0);
							itemU.setExpirity(cal.getTime());
							obsNegativePoints+=" Item:"+messages.getText(item.getText())+",Obs:"+pointComment+",Tiempo Reparación:"+communication.administration.corporation.util.Util.getTiempoReparacion(checkList.getId(), tag!=null?Integer.valueOf(tag):-1)+"\n\r";
									
							//if(tag!=null && (Integer.valueOf(tag)>-1 && Integer.valueOf(tag) <3  )){
							unconf.getItems().add(itemU);//Se guarda siempre
							negativePoints=true;
							if(tag!=null && Integer.valueOf(tag)==0){
								war=true;
							}
							//}
							if(seguimiento){
								negativePoints=true;//Si es Seguimiento en ruta, no hay parada y va siempre la notificacion.
								war=false;
								
							}
							
							if(userSys.isCorporation(CorporationData.MONTES_DE_PLATA)){
								if(war || conf.get("unconformities_cc_items").getValue().contains(item.getId().toString())){
									sendToSIGMDP=true;
								}
							}
						}
					}
				}
			}
			
			if(negativePoints){
				if(pesados && unconf.getItems().size() > 0){//Solo checklist pesados genera no conformidad
					corpABM.createOrUpdateUnconformityDPA(unconf, userSys);
				}

		        String fileName=data.getVehicleCheckId()+"_"+vehicleData.getMatricula().replace(" ", "_")+".pdf";
		        String file = vehicleReportFacade.reportChecklistPDF(fileName, checkList, data, userSys, messages);
		        
		        MailSender sender = new MailSender();
				Collection bcc = new LinkedList();
				Collection<String> files = new LinkedList();
				Collection<String> names = new LinkedList();
				
				String guiaName="Guia_No_Conforidades_Contratistas.pdf";
				String guiaFile=AlertConstants.TEMP_DIRECTORY_DPA+"checklist/Guia_No_Conforidades_Contratistas.pdf";
				
				files.add(file);
				names.add(fileName);
				
				
				if(pesados){
					files.add(guiaFile);
					names.add(guiaName);
				}
				
				 Iterator iter= data.getVehicle().getDivOrgsIterator();
				 String transportista="";
				 Integer divId=0;
				 if(iter.hasNext()){
					DivOrgData divData=(DivOrgData)iter.next();
					if(userSys.getCorporationId()==CorporationData.WEYERHAEUSER){
						if(iter.hasNext()){
							 divData=(DivOrgData) iter.next();
							 if(iter.hasNext()) {
								 divData=(DivOrgData)iter.next();
							 }else{
								 transportista="---";
							 }
						}else{
							transportista="---";
						}
				
					}
					
					Collection colAux=divData.getDivOrgLevels().getItems();
					if(colAux!=null && !transportista.equals("---")){
						Iterator iter2=divData.getDivOrgLevels().getItemIterator();
						if(iter2.hasNext()){
							communication.administration.corporation.tables.ItemData item=(communication.administration.corporation.tables.ItemData)iter2.next();
							divId=item.getId();
							transportista=item.getName();
						}
					}
				 }
				 
				
				 //En este campo se carga el ID del contratista en SIG.
				Integer contractorIDSIG=0;
				 
				String contractorMail=vehicleReport.getContractorDataMail(divId, userSys);
				if(contractorMail != null)
					contractorMail = contractorMail.replaceAll(";", ",");

				if(userSys.isCorporation(CorporationData.MONTES_DE_PLATA) && sendToSIGMDP){
					//Integracion con sistema SIG de Montes del Plata
					corpABM.saveMDPSIGAsyncCall(contractorIDSIG,transportista,driver,data.getDate(),file, obsNegativePoints, vehicleData.getMatricula(), Integer.valueOf(data.getName()), data.getVehicleCheckId(), userSys);
			 	} 
				
				Collection addressTo = new LinkedList();
				
				UserReport userReport=UserReportUtil.getHome().create();
				//Copia al instructor que crea el checklist
				String mail=userReport.findUserByPrimaryKey(userSys.getFilter().getLoginName(), userSys).getEmail();
				addressTo.add(mail);

				if(userSys.isCorporation(CorporationData.COFUSA)) {
					if(data.getResult() == 2) {
						addressTo.add("javier.tejera@ciaforestal.com.uy");	
						addressTo.add("denisse.quintana@ciaforestal.com.uy"); 
						addressTo.add("mauricio.demello@ciaforestal.com.uy");	
					}
				} else if(!mp || userSys.getCorporationId()!=CorporationData.MONTES_DE_PLATA){
					if(contractorMail!=null && contractorMail.trim().length()>0){
						String[] aux=contractorMail.split(",");
						for(int i =0; i<aux.length;i++){
							if(preventivo) {
								if(!aux[i].toLowerCase().contains("@upm.com")){
									addressTo.add(aux[i]);
								}
							}else {
								addressTo.add(aux[i]); 
							}
						}
					}
					
				
						
					if(seguimiento &&  userSys.isCorporation(CorporationData.MONTES_DE_PLATA)){
						bcc.add("sebastian.sorondo@montesdelplata.com.uy");
						
					}
					
					if(notifItems){
						//Solo mails a personas en la lista de los que reciben si se marco determinado item
						//Pero ademas hay que filtrar que sean los responsables del contratista
						//para eso se pone el correo en el contratista y en la lista de items.
						if(conf.containsKey("unconformities_email_cc_items")){
							String[] aux=conf.get("unconformities_email_cc_items").getValue().split(",");
							for(int i =0; i<aux.length;i++){
								String[] aux2=contractorMail.split(",");
								addressTo.add(aux[i]);
							}
						}	
					}
					
					if(conf.containsKey("unconformities_email_cc_all") && !preventivo){
						String[] aux=conf.get("unconformities_email_cc_all").getValue().split(",");
						for(int i =0; i<aux.length;i++) {
							addressTo.add(aux[i]);
						}
					}
					if(war){
						if(conf.containsKey("unconformities_email_cc_detinmediata")){
							String[] aux=conf.get("unconformities_email_cc_detinmediata").getValue().split(",");
							for(int i =0; i<aux.length;i++){
								addressTo.add(aux[i]);
							}
						}
					}
					
				}else{
					//Es mercancias peligrosas y es montes del plata
					addressTo.add("gonzalo.montandon@montesdelplata.com.uy");
					addressTo.add("pablo.acosta@montesdelplata.com.uy");
				}
				
					
				 
				String body="";
				if(pesados){//Checklist pesados
					addressTo.add("dlinale@cepamobility.com");
					body = "<p>Estimado Contratista adjunto le enviamos la fallas del veh&iacute;culo "+data.getVehicle().getMatricula()+" verificadas en el chequeo vehicular realizado por CEPA el d&iacute;a de hoy.</p>"+
						  "<p>Una vez reparadas las fallas le agradecemos ingresar al Data Center para corregir la NO conformidad.</p>"+
						  "<p>Muchas Gracias</p>";
				}else if(seguimiento){//Seguiento en ruta
					body= "<p>Estimado Contratista adjunto le enviamos la faltas/fallas del veh&iacute;culo "+data.getVehicle().getMatricula()+" verificadas en el Seguimiento en Ruta realizado por CEPA el d&iacute;a de hoy.</p><br/>"+
						  "<p>Muchas Gracias</p>";
				} else if (preventivo) {
			    	body = "<p>Estimado Contratista adjunto le enviamos la faltas/fallas del veh&iacute;culo " + data.getVehicle().getMatricula() + " verificadas en el Chequeo Preventivo realizado por CEPA el d&iacute;a de hoy.</p><br/>" + "Muchas Gracias<br/>";
				}
				String corpStr=userSys.getCorporationId()==CorporationData.MONTES_DE_PLATA?"MDP":userSys.getCorporationId()==CorporationData.FORESTALORIENTAL?"UPM":userSys.getCorporationId()==CorporationData.WEYERHAEUSER?"Lumin":userSys.getCorporationId()==CorporationData.COFUSA?"COFUSA":"";		
			    String subject = pesados ? "CEPA -" + corpStr + " Check list Notificación" + (internos ? " INTERNOS " : "") : "CEPA - Seguimiento En Ruta - Notificación";
			    if (preventivo) {
			        subject = "CEPA -" + corpStr + " Chequeo Preventivo";
			    }
				boolean sended = false;
				int count = 0;
				while(!sended && count < 10 && (bcc.size()+addressTo.size())>0 ) {
					try {
						sended =sender.sendImageMandrill(addressTo, "CEPA DataCenter", "datacenter@cepasafedrive.com",
								subject, Util.getCompleteHTMLMail(body,"CEPA DataCenter", "datacenter@cepasafedrive.com")
								, files, names, "datacenter@cepasafedrive.com", "CEPA DataCenter", bcc,null);
					}catch (Exception e) {
						e.printStackTrace();
					}
					count++; 
				}
				
				if(seguimiento){
					corpABMFacade.newSeguimientoRuta(vehicleData, checkList, data, userSys);
				} 

				//CorporationReportFacade corpReport=CorporationReportFacadeUtil.getHome().create();
			//corpReport.sendDCMessageObserver(userSys.getFullName()+"_C_"+data.getVehicle().getId()+"_Checklist no aprobado "+data.getVehicle().getMatricula(), userSys);
			
		}
	}
	
		

	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehiclecreate,drivercreatevehicle,vehiclechecklist"
	 * @param data
	 * @throws Exception
	 */	
	public Integer uploadCheckListImages(List images,Integer checkId, UserSessionData userRequest) throws Exception {

		VehicleABM vehicleABM = null;
		try {		
			
			
			VehicleABMHome home = VehicleABMUtil.getHome();
			vehicleABM = home.create();
			
			vehicleABM.uploadCheckListImages(images, checkId, userRequest);

		} catch (Exception exception) {
			Object[] p = {};
			
			handler.handleException("uploadCheckListImages", exception, userRequest,p);
	    }
		return null;

	}
	
	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,dpachecklist,vehiclecreate,drivercreatevehicle,vehiclechecklist"
	 * @param data
	 * @throws Exception
	 */	
	public Integer uploadCheckListImagesBase64(List images,Integer checkId, UserSessionData userRequest) throws Exception {

		VehicleABM vehicleABM = null;
		try {		
			
			
			VehicleABMHome home = VehicleABMUtil.getHome();
			vehicleABM = home.create();
			
			vehicleABM.uploadCheckListImagesBase64(images, checkId, userRequest);

		} catch (Exception exception) {
			Object[] p = {};
			
			handler.handleException("uploadCheckListImages", exception, userRequest,p);
	    }
		return null;

	}
	
	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehiclecreate,drivercreatevehicle,vehiclechecklist"
	 * @param data
	 * @throws Exception
	 */	
	public Integer deleteCheckListImages(List images,Integer checkId, UserSessionData userRequest) throws Exception {

		VehicleABM vehicleABM = null;
		try {		
			
			
			VehicleABMHome home = VehicleABMUtil.getHome();
			vehicleABM = home.create();
			
			vehicleABM.deleteCheckListImages(images, checkId, userRequest);

		} catch (Exception exception) {
			Object[] p = {};
			
			handler.handleException("deleteCheckListImages", exception, userRequest,p);
	    }
		return null;

	}
	
	
	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehiclecreate,drivercreatevehicle"
	 * @param data
	 * @throws Exception
	 */	
	public void createCheckLists(Collection list, UserSessionData userSessionData) 
		throws Exception {
		
		VehicleABM vehicleABM = null;
		try {		
						
			VehicleABMHome home = VehicleABMUtil.getHome();
			vehicleABM = home.create();
			
			Iterator it = list.iterator();
			while(it.hasNext()) {
				vehicleABM.createOrUpdateVehicleCheck((VehicleCheckData) it.next(),"Inserted by File",userSessionData);
			}

		} catch (Exception exception) {
			Object[] p = new Object[1];
			p[0] = list;
			handler.handleException("createCheckLists", exception, userSessionData,p);
	    }
	}
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehiclecreate,drivercreatevehicle,vehiclechecklist"
     * @param data
     * @throws Exception
     */ 
    public Integer createOrUpdateTruckCheck(VehicleCheckData data, Collection trailers ,
    		String comment,UserSessionData userSessionData) throws Exception {

        VehicleABM vehicleABM = null;
        try {       
             
            
            VehicleABMHome home = VehicleABMUtil.getHome();
            vehicleABM = home.create();
            
            Integer vehicleCheckId = vehicleABM.createOrUpdateVehicleCheck(data,comment,userSessionData);
            
            Iterator it = trailers.iterator();
            while(it.hasNext()) {
                TrailerCheckData trailerData = (TrailerCheckData)it.next(); 
                vehicleABM.createOrUpdateTrailerCheck(trailerData,vehicleCheckId,comment,userSessionData);
            }

            return vehicleCheckId;

        } catch (Exception exception) {
        	Object[] p = new Object[3];
        	p[0] = data;
        	p[1] = trailers;
        	p[2] = comment;
            handler.handleException("createOrUpdateTruckCheck", exception,userSessionData,p);
        }
        return null;
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehiclecreate,drivercreatevehicle,vehiclechecklist"
     * @param data
     * @throws Exception
     */ 
    public Integer createOrUpdateVehiclePursuitRoute(VehiclePursuitRouteData data, String comment,
    		UserSessionData userSessionData) throws Exception {

        VehicleABM vehicleABM = null;
        try {       
                        
            VehicleABMHome home = VehicleABMUtil.getHome();
            vehicleABM = home.create(); 
            return vehicleABM.createOrUpdateVehiclePursuitRoute(data,comment,userSessionData);

        } catch (Exception exception) {
        	Object[] p = new Object[2];
        	p[0] = data;
        	p[1] = comment;
            handler.handleException("createOrUpdateVehiclePursuitRoute", exception, userSessionData,p);
        }
        return null;
    }
    
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,cepascheduleactivitydelete,cepascheduleactivityregistrydelete,cdactivitydelete,vehiclechecklist"
	 * @param data
	 * @throws Exception
	 */
	public void deleteVehiclePursuitRoute(Integer id, String comment,
			UserSessionData userSessionData) throws Exception {
		try{	
						
			VehicleABM vehicleABM=VehicleABMUtil.getHome().create();
			vehicleABM.deleteVehiclePursuitRoute(id,comment,userSessionData);		
			
		} catch (Exception exception) {
			Object[]p = new Object[2];
			p[0] = id;
			p[1] = comment;
			handler.handleException("deleteVehiclePursuitRoute", exception, userSessionData, p);
		}
	}
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,cepascheduleactivitydelete,cepascheduleactivityregistrydelete,cdactivitydelete"
	 * @param data
	 * @throws Exception
	 */
	public void deleteVehicleCheck(Integer id, String comment,
			UserSessionData userSessionData) throws Exception {
		try{	
						
			VehicleABM vehicleABM=VehicleABMUtil.getHome().create();
			vehicleABM.deleteVehicleCheck(id,comment,userSessionData);
			
		} catch (Exception exception) {
			Object[]p=new Object[2];
			p[0] = id;
			p[1] = comment;
			handler.handleException("deleteVehicleCheck", exception, userSessionData,p);
		}
	}
		
    /**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,vehiclecreate"
     * @throws Exception
     */
    public void createVehicles(Collection vehicles, String note, UserSessionData userSessionData) throws Exception {
        VehicleABM vehicleABM = null;
        try {       
            
            Iterator it = vehicles.iterator();
            CorporationData corp = new CorporationData(userSessionData.getFilter().getCorporationId(),null,null,null,null,null);
            while(it.hasNext()) {
                VehicleData data = (VehicleData)it.next();
                data.setCorporation(corp);
                String valid = data.validate(userSessionData);
                if (valid != null) {
                    throw new InvalidDataException("createVehicles",valid);
                }
    
                VehicleABMHome home = VehicleABMUtil.getHome();
                vehicleABM = home.create();      
                try {
                	String comment = (note == null || note.length() == 0) ? "Inserted by file" : note; 
                    vehicleABM.vehicleCreate(data, userSessionData,comment);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    
        } catch (Exception exception) {
        	Object[]p=new Object[1];
        	p[0] = vehicles;
            handler.handleException("createVehicles",exception,userSessionData,p);
        } 
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate,dpachecklist"
     * @throws Exception
     */
    public void associateTrailer(Integer vehicleId, Collection trailers, 
    		UserSessionData userSessionData) throws Exception {
        try{
            VehicleABMHome home = VehicleABMUtil.getHome();
            VehicleABM vehicleABM = home.create();      
            Iterator it = trailers.iterator();
            while(it.hasNext()) {
                vehicleABM.associateTrailer(vehicleId, new Integer((String)it.next()), userSessionData);
            }
        } catch (Exception e) {
        	Object[]p = new Object[2];
        	p[0] = vehicleId;
        	p[1] = trailers;
            handler.handleException("associateTrailer",e,userSessionData,p);
        }   
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate,dpachecklist"
     * @throws Exception
     */
    public void desassociateTrailer(Integer vehicleId, Collection trailers, 
    		UserSessionData userSessionData) throws Exception {
        try{
            VehicleABMHome home = VehicleABMUtil.getHome();
            VehicleABM vehicleABM = home.create();      
            Iterator it = trailers.iterator();
            while(it.hasNext()) {
                vehicleABM.desassociateTrailer(vehicleId, new Integer((String)it.next()), userSessionData);
            }
        } catch (Exception e) {
        	Object[] p = new Object[2];
        	p[0] = vehicleId;
        	p[1] = trailers;
            handler.handleException("desassociateTrailer",e,userSessionData,p);
        }   
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate,dpachecklist"
     * @throws Exception
     */
    public Integer createTrailer(TrailerData trailer, UserSessionData userSessionData)
    	throws Exception {
        try{
            VehicleABMHome home = VehicleABMUtil.getHome();
            VehicleABM vehicleABM = home.create();      
            return vehicleABM.createTrailer(trailer,userSessionData); 
        } catch (Exception e) {
        	Object[] p = new Object[1];
        	p[0] = trailer;
            handler.handleException("createTrailer",e,userSessionData,p);
        }   
        return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate,dpachecklist"
     * @throws Exception
     */
    public void updateTrailer(TrailerData trailer, UserSessionData userSessionData) throws Exception {
        try{
            VehicleABMHome home = VehicleABMUtil.getHome();
            VehicleABM vehicleABM = home.create();      
            vehicleABM.updateTrailer(trailer,userSessionData); 
        } catch (Exception e) {
        	Object[] p = new Object[1];
        	p[0] = trailer;
            handler.handleException("updateTrailer",e,userSessionData,p);
        }   
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate"
     * @throws Exception
     */
    public void deleteTrailer(Collection ids, UserSessionData userSessionData) throws Exception {
        try{
            VehicleABMHome home = VehicleABMUtil.getHome();
            VehicleABM vehicleABM = home.create();
            Iterator it = ids.iterator();
            while(it.hasNext()) {
                vehicleABM.deleteTrailer(new Integer((String)it.next()), userSessionData);
            }
        } catch (Exception e) {
        	Object[]p = new Object[1];
        	p[0] = ids;
            handler.handleException("deleteTrailer",e, userSessionData,p);
        }   
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate"
     * @throws Exception
     */
    public void trailerExclude(Collection ids, Date exclusionDate, String string, UserSessionData userSessionData)  throws Exception {
        try{
            VehicleABMHome home = VehicleABMUtil.getHome();
            VehicleABM vehicleABM = home.create();
            Iterator it = ids.iterator();
            while(it.hasNext()) {
                vehicleABM.trailerExclude(new Integer((String)it.next()), exclusionDate, userSessionData);
            }
        } catch (Exception e) {
        	Object[] p = new Object[3];
        	p[0] = ids;
        	p[1] = exclusionDate;
        	p[2] = string;
        	
            handler.handleException("trailerExclude",e,userSessionData,p);
        }   
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate"
     * @throws Exception
     */
    public void trailerInclude(Collection ids, String string, UserSessionData userSessionData)  throws Exception {
        try{
            VehicleABMHome home = VehicleABMUtil.getHome();
            VehicleABM vehicleABM = home.create();
            Iterator it = ids.iterator();
            while(it.hasNext()) {
                vehicleABM.trailerInclude(new Integer((String)it.next()), userSessionData);
            }
        } catch (Exception e) {
        	Object[]p = new Object[2];
        	p[0] = ids;
        	p[1] = string;
            handler.handleException("trailerInclude",e,userSessionData,p);
        }   
    }
    
    /**
     * 
     * @param items
     * @param item
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unused")
	private boolean contains(Collection items, Item item) {
		boolean contiene = false;	
	   	Iterator itItems = items.iterator();
	 	// Tengo que fijarme si el orden del item es el correcto:
	 	while (itItems.hasNext() && ! contiene)
	 	{
	 		ItemValueStr itemValueStr = (ItemValueStr)itItems.next();
	 		contiene = (itemValueStr.getItem().getId().equals(item.getId()));
		}
		return contiene;
	}
    
    
  /*  private void addItemAlert(Collection items, Integer CheckId, UserSessionData userSessionData) {
		data.setVehicleCheckId(CheckId);
	
		AlertData alert;
		try {
			alert = AlertABMBean.findAlertByType(userSessionData.getFilter().getCorporationId(),
					AlertConstants.VEHICLECHECKLISTITEMS);

			Iterator it = items.iterator();
			ItemValueStr itemValue;
			while (it.hasNext()) {
				String[] values = ((String)it.next()).split("-");
				if(values.length == 2) {
					itemValue = (ItemValueStr) it.next();
					// Solo doy de alta alertas para aquellos items que la fecha lÃ­mite de reparaciÃ³n es distinta de null
					if (values[2] !=null) {

					Item item = itemValue.getItem();
					VehicleChecklistItemAlertData alertCheck = VehicleCheckllistItemsAlertHandler.findAlertByVehicleCheckItem(v.getVehicle().getId(),item.getId());
					
					if (alertCheck == null) {
						VehicleCheckllistItemsAlertHandler.createVehicleChecklistItemAlert
						(new VehicleChecklistItemAlertData(null,alert,v.getVehicleCheckData(),itemValue.getItem().getId(),new Date()));
					}	
				} 
			}
		}
		catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("addItemAlert:" + e.getMessage());
				e.printStackTrace();
			}
			//}
		}
    
    
    private void deleteItemAlert(Collection items,VehicleCheck v,UserSessionData userSessionData) {

    	// Elimino alertas que corresponden a checklist del mismo vehiculo para items del formulario de checklist que no fueron seteados en el actual checklist		
    	try {	
    	VehicleReportHome home = VehicleReportUtil.getHome();
    	VehicleReport vehicleReport = home.create();      
        CheckListData checklist = vehicleReport.findVehicleCheckList(userSessionData);
        
        Iterator it1= checklist.getPoints().iterator();
    	Point point;
    	Iterator itItems;
    		
        while (it1.hasNext()) {
        	point = (Point)it1.next();
       	 itItems = point.getItems().iterator();
     	while (itItems.hasNext())
     	{
     		Item itemCheck = (Item)itItems.next();
     		if (!contains(items,itemCheck)) {
     			//Borro Alerta si es que existe
     			System.out.println("Borro " + itemCheck.getId());
     			VehicleChecklistItemAlertData alertCheck = VehicleCheckllistItemsAlertHandler.findAlertByVehicleCheckItem(v.getVehicle().getId(),itemCheck.getId());
     			if (alertCheck != null) {
     				VehicleCheckllistItemsAlertHandler.deleteVehicleChecklistItemAlert(alertCheck);
     			}	
     		}
     		else {
     			System.out.println("NO Borro " + itemCheck.getId());
     		}
     		
        	}
        }
    	} catch (Exception e) {
    		System.out.println("deleteItemAlert:" + e.getMessage());
    	}
    	}
    */
  /*  /**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehiclecreate,drivercreatevehicle"
	 * @param data
	 * @throws InvalidDataException
	 * @throws CommunicationProblemException
	 * @throws DeslogedException
	 */	
	/*public void vehicleChecklistAlertGenerate(VehicleCheckData data,Integer vehicleCheckId,
			UserSessionData userSessionData) throws Exception {

	// Genero alertas	
	AlertData alert1 = AlertABMBean.findAlertByType(userSessionData.getFilter().getCorporationId(),
				AlertConstants.VEHICLECHECKLIST);
	
	Collection to = alert1.getTargets(userSessionData.getFilter().getCorporationId());
	
	String body = "BODY Alerta general de Checklist";
	
	AlertHandlerFactory af = new AlertHandlerFactory();
	af.getAlertHandler(AlertConstants.VEHICLECHECKLIST,to,
			body, alert1.getSubject())
			.alertar();		
	
	// Agrego Alerta Puntual
	Session session=HibernateUtil.currentSession();
	VehicleCheck v = new VehicleCheck();
	session.load(v,vehicleCheckId);
	
	addItemAlert(data,vehicleCheckId,v,userSessionData);
	
	deleteItemAlert(data.getItemValueSet(),v,userSessionData);

	System.out.println("FIN CHECKLIST");

	}
	*/
	

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     * @throws Exception
     */
    public void createUnconformity(Integer vehicleId, UnconformityData unconformity, UserSessionData userSessionData) throws Exception {
    	VehicleABM vehicleABM = null;
        try{    
           
            VehicleABMHome home = VehicleABMUtil.getHome();
            vehicleABM = home.create();      
            vehicleABM.createUnconformity(vehicleId,unconformity,userSessionData);
        } catch (Exception exception) {
        	Object[] parameters = new Object[2];
        	parameters[0] = vehicleId;
        	parameters[1] =  unconformity;
            handler.handleException("createUnconformity",exception, userSessionData, parameters);
        } 
    }
    

    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     * @throws Exception
     */
    public void deleteUnconformity(Integer vehicleId, Collection unconformities, UserSessionData userSessionData) throws Exception {
    	VehicleABM vehicleABM = null;
        try{    
           
            VehicleABMHome home = VehicleABMUtil.getHome();
            vehicleABM = home.create();      
            
            Iterator it = unconformities.iterator();
            while(it.hasNext()) {
            	vehicleABM.deleteUnconformity(vehicleId,(String)it.next(),userSessionData);
            }
        } catch (Exception exception) {
        	Object[] parameters = new Object[2];
        	parameters[0] = vehicleId;
        	parameters[1] = unconformities;
            handler.handleException("deleteUnconformity",exception, userSessionData, parameters);
        } 
    }
    
    /**
     * Contratos de Alquiler de los Vehiculos (Allergan)
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     * @throws Exception
     */
    public void createVehicleContract(Integer vehicleId, VehicleContractData contract, UserSessionData userSessionData) throws Exception {
    	VehicleABM vehicleABM = null;
        try{    
           
            VehicleABMHome home = VehicleABMUtil.getHome();
            vehicleABM = home.create();      
            vehicleABM.createVehicleContract(vehicleId,contract,userSessionData);
        } catch (Exception exception) {
        	Object[] parameters = new Object[2];
        	parameters[0] = vehicleId;
        	parameters[1] =  contract;
            handler.handleException("createVehicleContract",exception, userSessionData, parameters);
        } 
    }
    
    /**
     * Contratos de Alquiler de los Vehiculos (Allergan)
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     * @throws Exception
     */
    public void createVehiclesContracts(Collection<Integer> vehiclesId, Date date, 
    		VehicleContractDataType type, UserSessionData userSessionData) throws Exception {
    	VehicleABM vehicleABM = null;
        try{    
           
            VehicleABMHome home = VehicleABMUtil.getHome();
            vehicleABM = home.create();      
            
            if (vehiclesId != null && vehiclesId.size() > 0){
            	Iterator it = vehiclesId.iterator();
            	while(it.hasNext()) {
            		Integer vehicleId = (Integer) it.next();
            		if (vehicleId != null && date != null && type != null){
            			VehicleContractData contract = new VehicleContractData(vehicleId,date,type,false);
            			vehicleABM.createVehicleContract(vehicleId,contract,userSessionData);
            		}
            	}
            }
        } catch (Exception exception) {
        	Object[] parameters = new Object[2];
        	parameters[0] = vehiclesId;
        	parameters[1] = date;
        	parameters[2] = type;
            handler.handleException("createVehiclesContracts",exception, userSessionData, parameters);
        } 
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     * @throws Exception
     */
    public void deleteVehicleContract(Integer vehicleId, Collection contracts, UserSessionData userSessionData) throws Exception {
    	VehicleABM vehicleABM = null;
        try{    
           
            VehicleABMHome home = VehicleABMUtil.getHome();
            vehicleABM = home.create();      
            
            Iterator it = contracts.iterator();
            while(it.hasNext()) {
            	vehicleABM.deleteVehicleContract(vehicleId,(String)it.next(),userSessionData);
            }
        } catch (Exception exception) {
        	Object[] parameters = new Object[2];
        	parameters[0] = vehicleId;
        	parameters[1] = contracts;
            handler.handleException("deleteVehicleContract",exception, userSessionData, parameters);
        } 
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     * @throws Exception
     */
    public void deleteSimpleVehicleCheck(Collection checks, UserSessionData userSessionData) throws Exception {
        try{
        	VehicleABM vehicleABM = null;
            VehicleABMHome home = VehicleABMUtil.getHome();
            vehicleABM = home.create();      
            Iterator it = checks.iterator();
            while(it.hasNext()) {
            	vehicleABM.deleteSimpleVehicleCheck(new Integer((String)it.next()),userSessionData);
            }
        }catch(Exception e) {
			Object[] parameters = {checks};
            handler.handleException("deleteSimpleVehicleCheck",e, userSessionData, parameters);
        }
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     */
    public void createVehicleMaintenance(VehicleMaintenanceData data, UserSessionData userSessionData) throws Exception {
        try{
        	VehicleABM vehicleABM = null;
            VehicleABMHome home = VehicleABMUtil.getHome();
            vehicleABM = home.create();      
            vehicleABM.createVehicleMaintenance(data,userSessionData);
        }catch(Exception e) {
			Object[] parameters = {data};
            handler.handleException("createVehicleMaintenance",e, userSessionData, parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     */
    public void createVehicleMaintenances(Collection<VehicleMaintenanceData> maintenances, UserSessionData userSessionData) throws Exception {
        try{
        	Calendar c = Calendar.getInstance();
        	VehicleABM vehicleABM = VehicleABMUtil.getHome().create();
        	
        	Iterator it = maintenances.iterator();
    		CorporationReport corporationReport = CorporationReportUtil.getHome().create();
    		CorporationABM corporationABM = CorporationABMUtil.getHome().create();
    		PointsDefinitionData definition = null;
        	while(it.hasNext()) {
        		VehicleMaintenanceData data = (VehicleMaintenanceData)it.next();
                Integer id = vehicleABM.createVehicleMaintenance(data,userSessionData);
                if(userSessionData.isCorporation(CorporationData.BAYER_MX)) {
            		c.setTime(data.getDate());
                	if(definition == null) {
            			ArrayList<PointsDefinitionData> definitions = corporationReport.getPointsDefinitions(PointsDefinitionData.VEHICLE_MAINTENANCE,userSessionData);
            			definition = definitions.iterator().next();
                	}
                	PointAccountData driverAccount = corporationReport.getPointAccount(PointAccountData.DRIVER, data.getDriver().getId(), userSessionData);
                	PointMoveData move = new PointMoveData(definition,c,data.getVehicle().getMatricula());
                	move.setActualTotal(driverAccount.getTotal());
                	move.setAccount(driverAccount.getId());
                	move.setDescription(String.valueOf(id));
                	corporationABM.savePointMove(move, userSessionData);
                }
        	}
        }catch(Exception e) {
			Object[] parameters = {maintenances};
            handler.handleException("createVehicleMaintenance",e, userSessionData, parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     */
    public void updateVehicleMaintenance(VehicleMaintenanceData data, UserSessionData userSessionData) throws Exception {
        try{
        	VehicleABM vehicleABM = null;
            VehicleABMHome home = VehicleABMUtil.getHome();
            vehicleABM = home.create();      
            vehicleABM.updateVehicleMaintenance(data,userSessionData);
        }catch(Exception e) {
			Object[] parameters = {data};
            handler.handleException("updateVehicleMaintenance",e, userSessionData, parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     */
    public void deleteVehicleMaintenance(Collection maintenances, UserSessionData userSessionData) throws Exception {
        try{
        	VehicleABM vehicleABM = VehicleABMUtil.getHome().create(); 
            
			CorporationABMFacade corpABMFacade=CorporationABMFacadeUtil.getHome().create();
            Iterator it = maintenances.iterator();
            while(it.hasNext()) {
            	PointMove move = vehicleABM.deleteVehicleMaintenance(new Integer((String) it.next()),userSessionData);
            	if(move != null)
            		corpABMFacade.removeMove(new PointAccountData(move.getAccount()), move.getData(), userSessionData);
            }
        }catch(Exception e) {
			Object[] parameters = {maintenances};
            handler.handleException("deleteVehicleMaintenance",e, userSessionData, parameters);
        }
    }
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     */
    public void createComplaint(ComplaintData data, UserSessionData userSessionData) throws Exception {
        try{
        	VehicleABM vehicleABM = null;
            VehicleABMHome home = VehicleABMUtil.getHome();
            vehicleABM = home.create();      
            vehicleABM.createComplaint(data,userSessionData);
        }catch(Exception e) {
			Object[] parameters = {data};
            handler.handleException("createComplaint",e, userSessionData, parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     */
    public void updateComplaint(ComplaintData data, UserSessionData userSessionData) throws Exception {
        try{
        	VehicleABM vehicleABM = null;
            VehicleABMHome home = VehicleABMUtil.getHome();
            vehicleABM = home.create();      
            vehicleABM.updateComplaint(data,userSessionData);
        }catch(Exception e) {
			Object[] parameters = {data};
            handler.handleException("updateComplaint",e, userSessionData, parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     */
    public void deleteComplaint(Integer id, UserSessionData userSessionData) throws Exception {
        try{
        	VehicleABM vehicleABM = null;
            VehicleABMHome home = VehicleABMUtil.getHome();
            vehicleABM = home.create();      
           	vehicleABM.deleteComplaint(id,userSessionData);
        }catch(Exception e) {
			Object[] parameters = {id};
            handler.handleException("deleteComplaint",e, userSessionData, parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     */
    public void updateVehicleContract(VehicleContractData contract, int action, UserSessionData userSessionData) throws Exception {
        try{
        	VehicleABM vehicleABM = VehicleABMUtil.getHome().create();      
           	vehicleABM.updateVehicleContract(contract, action,userSessionData);
        }catch(Exception e) {
			Object[] parameters = {contract,action};
            handler.handleException("updateVehicleContract",e, userSessionData, parameters);
        }
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleview,systemaccess"
     */
    public void createOrUpdateDPAGoals(ArrayList<DPAGoalData> goals, UserSessionData userSessionData) throws Exception {
        try{
        	VehicleABM vehicleABM = VehicleABMUtil.getHome().create();
        	for(DPAGoalData d: goals){
        		vehicleABM.createOrUpdateDPAGoals(d, userSessionData);	
        	}
           	
        }catch(Exception e) {
			Object[] parameters = {};
            handler.handleException("createOrUpdateDLAGoals",e, userSessionData, parameters);
        }
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleview,systemaccess"
     */
    public void createOrUpdateDPAVehicleActive(ArrayList<DPAVehicleActiveData> vehicleActives, UserSessionData userSessionData) throws Exception {
        try{
        	VehicleABM vehicleABM = VehicleABMUtil.getHome().create();
        	for(DPAVehicleActiveData d: vehicleActives){
        		vehicleABM.createOrUpdateDPAVehicleActive(d, userSessionData);	
        	}
           	
        }catch(Exception e) {
			Object[] parameters = {};
            handler.handleException("createOrUpdateDPAVehicleActive",e, userSessionData, parameters);
        }
    }

	private void sendMailNewItems(VehicleData vehicle, Collection<Integer> items, Text messages, UserSessionData userSessionData) throws Exception {

		VehicleReport vehicleReport=VehicleReportUtil.getHome().create();

		Collection<String> emails = new LinkedList<String>();
		String contractorMail=vehicleReport.getContractorDataMail(vehicle.getDivOrgId(0), userSessionData);
		if(contractorMail != null)
			contractorMail = contractorMail.replaceAll(";", ",");
		String[] aux=contractorMail.split(",");
		for(int i =0; i<aux.length;i++){
			emails.add(aux[i]);
		}

		String body = "<p>Estimado Contratista,</p>";
		body += "<p>En el chequeo realizado al vehículo "+vehicle.getMatricula()+" se detectó que faltan algunos ítems, la falta de estos hubiera provocado las siguientes No Conformidades:</p>";
		body += "<ul>";
		Iterator<Integer> it = items.iterator();
		while(it.hasNext()) {
			int v = it.next();
			String key = messages.getText("forestales.check2.v"+String.valueOf(v-16999));
			switch(v) {
				case 17000: case 17001: case 17002: case 17012:
					body += "<li>"+key+" (48 horas)</li>";
					break;
				case 17005: case 17006: case 17015: case 17016: 
					body += "<li>"+key+" (Detención Inmediata)</li>";
					break;
			}
		}
		body += "</ul>";
		body += "<p>A partir del 15 de agosto se pondrán en funcionamiento estas acciones.</p>";

        MailSender sender = new MailSender();
		String subject= "Chequeo Nuevos Items";
		boolean sended = false;
		int count = 0;
		while(!sended && count < 10 && (emails.size())>0 ) {
			try {
				sended =sender.sendImageMandrill(emails, "CEPA DataCenter", "datacenter@cepasafedrive.com",
						subject, Util.getCompleteHTMLMail(body,"CEPA DataCenter", "datacenter@cepasafedrive.com")
						, new LinkedList(), new LinkedList(), "datacenter@cepasafedrive.com", "CEPA DataCenter", new LinkedList(), null);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			count++; 
		}
	}

	private void sendVehicleEmail(VehicleData vehicle, UserSessionData userSessionData) {
    	try {
	    	MailSender sender = new MailSender();
	
	    	Collection to = new LinkedList();
	    	if(AlertConstants.isProductionServer()) {
	    		to.add("mmunoz@fraylog.com.uy"); 
	    		to.add("mberrondo@fraylog.com.uy");
	    	}else{
		    	to.add("jrodriguez@cepasafedrive.com");
	    	}
			
			//to.add("jrodriguez@cepadc.com");
	    	Collection bcc = new LinkedList();
	    	bcc.add("cepasafedrive@gmail.com");

	    	String subject = "Nuevo vehículo ingresado";
			
			String body = "<body>"+
							"<p><span style='font-family:\"Lucida Grande\", \"Lucida Sans Unicode\",Tahoma, sans-serif';font-size:1.15em;color: #666666;'>Acaba de ser ingresado un nuevo vehículo en FleetDataManager Fraylog:</span></p>"+
							"<p>"+
							"<span style='font-family:\"Lucida Grande\", \"Lucida Sans Unicode\",Tahoma, sans-serif';font-size:1.15em;color: #666666;'>"+
							"Matrícula: "+vehicle.getMatricula()+"<br>"+
							"</span>"+
							"</p>"+
							"<p>"+
							"<span style='font-family:\"Lucida Grande\", \"Lucida Sans Unicode\",Tahoma, sans-serif';font-size:1.75em;color: #666666;'>"+
							"Equipo Data Center"+
							"</span>"+
							"</p>"+
							"</body>";
			
			boolean sended = false;
			int count = 0;
			while(!sended && count < 10) {
				try {
			    	sended = sender.sendImageMandrill(to, "Fraylog DataCenter", AlertConstants.EMAIL_SUPPORT, subject,communication.administration.corporation.util.Util.toHTMLString(body), new LinkedList<String>(), new LinkedList<String>(), "monsantobr@cepadc.com", "CEPA DataCenter", bcc, null);
				}catch (Exception e) {
					e.printStackTrace();
				}
				count++;
			}
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
	}
}
