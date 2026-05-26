/*
 * Created on 28-oct-2005
 */
package business.vehicle;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.naming.NamingException;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.jboss.logging.Logger;

import persistence.driver.DriverReport;
import persistence.driver.DriverReportHome;
import persistence.driver.DriverReportUtil;
import persistence.log.LoggerFormatting;
import persistence.vehicle.VehicleReport;
import persistence.vehicle.VehicleReportBean;
import persistence.vehicle.VehicleReportHome;
import persistence.vehicle.VehicleReportLocal;
import persistence.vehicle.VehicleReportLocalHome;
import persistence.vehicle.VehicleReportUtil;
import business.util.ExceptionHandler;
import business.util.MemoryLog;
import communication.activity.tables.CheckListData;
import communication.activity.tables.ItemData;
import communication.activity.tables.PointData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.util.DPACheckListXLSFilter;
import communication.administration.corporation.util.Util;
import communication.administration.dpa.UnconformityItemDPAData;
import communication.administration.user.UserSessionData;
import communication.alert.AlertConstants;
import communication.driver.report.DPATelematicControlFilter;
import communication.driver.tables.DriverMainAttributes;
import communication.driver.tables.TelematicControlData;
import communication.exception.CommunicationProblemException;
import communication.exception.DCException;
import communication.exception.DeslogedException;
import communication.exception.InvalidDataException;
import communication.fleet.tables.OdometerData;
import communication.language.Text;
import communication.util.ListResult;
import communication.vehicle.report.CheckListACUDataSource;
import communication.vehicle.report.CheckListImageData;
import communication.vehicle.report.CheckListUPMDataSource;
import communication.vehicle.report.ChecklistReportHead;
import communication.vehicle.report.PictureDPADataSource;
import communication.vehicle.report.ReportCheckListData;
import communication.vehicle.report.ReportCheckListDetailedData;
import communication.vehicle.report.ReportVehiclePursuitRouteData;
import communication.vehicle.report.TrailerWithoutCheckListData;
import communication.vehicle.report.VehicleCheckReportDataType;
import communication.vehicle.report.VehicleCheckReportDetailedDataType;
import communication.vehicle.report.VehiclePursuitReportDataType;
import communication.vehicle.tables.ComplaintData;
import communication.vehicle.tables.DPAGoalData;
import communication.vehicle.tables.DPAVehicleActiveData;
import communication.vehicle.tables.TrailerData;
import communication.vehicle.tables.VehicleCheckData;
import communication.vehicle.tables.VehicleCheckReportGroupData;
import communication.vehicle.tables.VehicleCheckResumeData;
import communication.vehicle.tables.VehicleData;
import communication.vehicle.tables.VehicleDriverListData;
import communication.vehicle.tables.VehicleMaintenanceData;
import communication.vehicle.tables.VehiclePursuitRouteData;

/**
 * @ejb.bean name = "VehicleReportFacade" jndi-name = "ejb/VehicleReportFacade"
 *           type = "Stateless" transaction-type = "Container"
 * 
 * @ejb.ejb-ref ejb-name = "VehicleReport" ref-name = "ejb/VehicleReport"
 *              view-type = "remote" link = "VehicleReport"
 * 
 * @ejb.ejb-ref ejb-name = "VehicleReport" ref-name = "ejb/VehicleReportLocal"
 *              view-type = "local" link = "VehicleReport"
 * 
 * @ejb.ejb-ref ejb-name = "DriverReport" 
 *              ref-name = "ejb/DriverReport" 
 *              view-type = "remote" 
 *               
 *               
 * @ejb.util generate="physical"
 */
public abstract class VehicleReportFacadeBean implements SessionBean {

	private ExceptionHandler handler = new ExceptionHandler(
			VehicleReportFacadeBean.class);

	/**
	 * Logger for this class
	 */
	private Logger logger = Logger.getLogger(VehicleReportFacade.class);

	private LoggerFormatting logFormatting = new LoggerFormatting();

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
	 * @ejb.permission role-name =
	 *                 "administrator,vehiclesearch,eventcreate,finecreate,driveredit,vehicleview,vehicleedit,fleetupdate,fleetedit,fleetinsertdata,driverviewowndata,drivercreateevent,drivercreatefine,drivervehicleview,drivercreatevehicle,vehicleedit,drivervehicleedit,vehiclechecklist,driverodometer,jjreporter,dpachecklist"
	 */
	public void ejbCreate() throws CreateException {
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "administrator,vehiclesearch,vehicleview,drivervehicleview,driverodometer,dpachecklist"
	 * 
	 * Valid values for attribute name: firstName, lastName, nick, mail
	 * 
	 * @param attrName
	 * @param attrValue
	 * @return Collection of UserData
	 * @throws InvalidDataException
	 * @throws CommunicationProblemException
	 * @throws DeslogedException
	 */
	public ListResult findVehicleList(String attribute, String value,
			int first, int count, int status, UserSessionData userSessionData)
			throws InvalidDataException, CommunicationProblemException,
			DeslogedException {

		// Check data

		try {

			if (attribute == null) {
				throw new InvalidDataException(
						"bussines.vehicle.findvehiclelist", "attrName = null");
			}
			// UserReport access
			VehicleReportLocalHome VehicleHome = VehicleReportUtil.getLocalHome();
			VehicleReportLocal vehicleReport = VehicleHome.create();
			return vehicleReport.findList(value, userSessionData, getAttribute(
					attribute, userSessionData), first, count, status);

		} catch (CreateException create) {
			logger.fatal(logFormatting.formattingText(create,
					"findVehicleList(String, String, int, int)",
					"CreateException", userSessionData.getFilter().getLoginName()),
					create);
			throw new CommunicationProblemException(
					"Has ocurred error in business.vehicle.VehicleReportFacade(findDriverByLikeAtribute), can not access VehicleReport, CreateException",
					"business.vehicle.vehiclereportfacade.notaccess.vehiclereport");
		} catch (NamingException naming) {
			logger.fatal(logFormatting.formattingText(naming,
					"findVehicleList(String, String, int, int)",
					"NamingException", userSessionData.getFilter().getLoginName()),
					naming);
			throw new CommunicationProblemException(
					"Has ocurred error in business.vehicle.VehicleReportFacade(findVehicleByLikeAtribute), can not access VehicleReport, NamingException",
					"business.vehicle.vehiclereportfacade.notaccess.vehiclereport");
		} catch (Exception e) {
			logger.error(logFormatting.formattingText(e,
					"findVehicleByLikeAttribute(String, String, int, int)",
					"Exception", userSessionData.getFilter().getLoginName()),e);
			throw new DCException(e);
		}

	}

	 /**
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws CommunicationProblemException
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccessr" 
     */
    public boolean validateDeleteChecklist(Integer idChecklist, UserSessionData userSessionData) throws Exception {
      try{
    	  VehicleReportLocalHome VehicleHome = VehicleReportUtil.getLocalHome();
    	  VehicleReportLocal vehicleReport = VehicleHome.create();
    	  return vehicleReport.validateDeleteChecklist(idChecklist, userSessionData);
      }catch(Exception e){
    	Object[] p = {userSessionData};
      	handler.handleException("findVehicleCheckDPA",e,userSessionData,p);
      }
      return false;
    }
    
	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator" Valid values for attribute
	 *                 name: firstName, lastName, nick, mail
	 * 
	 * @param attrName
	 * @param attrValue
	 * @return Collection of UserData
	 * @throws InvalidDataException
	 * @throws CommunicationProblemException
	 * @throws DeslogedException
	 */
	public List findVehicleByLike(VehicleData vehicle,
			UserSessionData userSessionData) throws InvalidDataException,
			CommunicationProblemException, DeslogedException {

		// Check data

		try {
			// UserReport access

			// VehicleReportHome VehicleHome = VehicleReportUtil.getHome();
			VehicleReportLocalHome VehicleHome = VehicleReportUtil
					.getLocalHome();
			// VehicleReport vehicleReport = VehicleHome.create();
			VehicleReportLocal vehicleReport = VehicleHome.create();
			return vehicleReport.findVehicleByLike(userSessionData, vehicle, 0, 10);

		} /*
			 * catch (RemoteException remote) { remote.printStackTrace(); throw
			 * new CommunicationProblemException("Has ocurred error in
			 * business.driver.DriverReportFacade(findDriverByLikeAtribute), can
			 * not access DriverReport,
			 * RemoteException","business.driver.driverreportfacade.notaccess.driverreport");
			 *  }
			 */catch (CreateException create) {
			throw new CommunicationProblemException(
					"Has ocurred error in business.vehicle.VehicleReportFacade(findVehicleByLikeAtribute), can not access VehicleReport, CreateException",
					"business.vehicle.vehiclereportfacade.notaccess.vehiclereport");
		} catch (NamingException naming) {
			throw new CommunicationProblemException(
					"Has ocurred error in business.vehicle.VehicleReportFacade(findVehicleByLikeAtribute), can not access VehicleReport, NamingException",
					"business.vehicle.vehiclereportfacade.notaccess.vehiclereport");
		}

	}
	
	/**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,dpachecklist,drivervehicleedit,drivercreateevent,vehiclechecklist" 
     */ 
    public Collection<VehicleCheckData> findVehicleCheckDPA(Integer id, UserSessionData userSessionData) throws Exception {
    	Collection<VehicleCheckData> res = new ArrayList<VehicleCheckData>();
        try {
        	VehicleReportLocalHome VehicleHome = VehicleReportUtil.getLocalHome();
			// VehicleReport vehicleReport = VehicleHome.create();
			VehicleReportLocal vehicleReport = VehicleHome.create();
			
        	res=vehicleReport.findVehicleCheckDPA(id,userSessionData);
        }catch(Exception e){
        	Object[] p = {userSessionData};
        	handler.handleException("findVehicleCheckDPA",e,userSessionData,p);
        }
        
        return res;
    }

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator" Valid values for attribute
	 *                 name: firstName, lastName, nick, mail
	 * 
	 * @param attrName
	 * @param attrValue
	 * @return Collection of UserData
	 * @throws InvalidDataException
	 * @throws CommunicationProblemException
	 * @throws DeslogedException
	 */
	public List findVehicleByLike(String matricula, Integer chasisNumber,
			String firstName, String lastName, int status,
			UserSessionData userSessionData) throws Exception	{

		// Check data

		try {
			// UserReport access

			// remote case
			// VehicleReportHome VehicleHome = VehicleReportUtil.getHome();

			// local case
			VehicleReportLocalHome VehicleHome = VehicleReportUtil
					.getLocalHome();

			// remote case
			// VehicleReport vehicleReport = VehicleHome.create();

			// local case
			VehicleReportLocal vehicleReport = VehicleHome.create();

			return vehicleReport.findVehicleByLike(userSessionData, matricula,
					chasisNumber, firstName, lastName, 0, 10, status);

		} catch (Exception e) {
			Object[]p = new Object[1];
			 p[0] = matricula;
			 p[1] = chasisNumber;
			 p[2] = firstName;
			 p[3] = lastName;
			 p[4] = status;
			handler.handleException("vehicleWithoutPursuitRoute",e,userSessionData,p);
		}
		return null;

	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "administrator,eventcreate,finecreate,drivercreateevent,vehicleupdate,vehicleedit,dpachecklist"
	 *                 Valid values for attribute name: firstName, lastName,
	 *                 nick, mail
	 * 
	 * @param attrName
	 * @param attrValue
	 * @return Collection of UserData
	 * @throws InvalidDataException
	 * @throws CommunicationProblemException
	 * @throws DeslogedException
	 */
	public VehicleData findVehicleById(Integer id, UserSessionData userSessionData)
			throws Exception {

		// Check data

		try {
			// UserReport access

			VehicleReportHome VehicleHome = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = VehicleHome.create();
			return vehicleReport.findVehicleByPrimaryKey(id, userSessionData);

		} catch (Exception e) {
			Object[]p = new Object[1];
			 p[0] = id;
			handler.handleException("vehicleWithoutPursuitRoute",e,userSessionData,p);
		}
		return null;

	}

	private int getAttribute(String attrName, UserSessionData userSessionData)
			throws InvalidDataException, DCException {
		try {
			if (attrName.equals("matricula")) {
				return VehicleReportBean.MATRICULA;
			}
			if (attrName.equals("prefix")) {
				return VehicleReportBean.PREFIX;
			}
			/*
			 * if (attrName.equals("lastName")) { return
			 * DriverReportBean.LAST_NAME; } if (attrName.equals("division")) {
			 * return DriverReportBean.DIVISION; } if
			 * (attrName.equals("subdivision")) { return
			 * DriverReportBean.SUBDIVISION; } if (attrName.equals("country")) {
			 * return DriverReportBean.COUNTRY; } if (attrName.equals("city")) {
			 * return DriverReportBean.CITY; }
			 */
		} catch (Exception e) {
			
			logger.error(logFormatting.formattingText(e,
					"getAttribute(String attrName)", "Exception", userSessionData
							.getFilter().getLoginName()),e);
			throw new DCException(e);
		}
		throw new InvalidDataException(
				"Class business.vehicle.VehicleReportFacade(findVehicleByLikeAtribute), not suport this attribute",
				"business.vehicle.vehiclereportfacade.findvehiclebylikeattribute.attributeunsuported");

	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "administrator,eventcreate,finecreate,vehicleedit,drivervehicleedit,vehiclechecklist,driverodometer,dpachecklist,jjreporter"
	 * 
	 * @param userPK
	 * @return UserData
	 * @throws InvalidDataException
	 * @throws CommunicationProblemException
	 * @throws DeslogedException
	 */
	public VehicleData findVehicleByPrimaryKey(Integer id,
			UserSessionData userSessionData) throws Exception {
		try {
			// Check data

			if (id == null) {
				throw new InvalidDataException(
						"Class business.vehicle.VehicleReportFacade(findDriverByPrimaryKey), primary Key is empty",
						"generic.vehicle.vehicledata.id.empty");
			}

			VehicleData data = null;

			// UserReport access
			VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			data = vehicleReport.findVehicleByPrimaryKey(id, userSessionData);

			return data;
		} catch (Exception e) {
			Object[] p = new Object[1];
			p[0] =id;
			handler.handleException(" findVehicleByPrimaryKey",e,userSessionData,p);
		}
		return null;

	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,vehicleview,drivervehicleview"
	 *                 Advanced search of drivers, includes the most important
	 *                 parameters to find
	 * @param registrationNumber -
	 *            registrationNumber of vehicle
	 * @param chasisNumber -
	 *            chasisNumber of vehicle
	 * @param firstNameDriver -
	 *            first name of driver
	 * @param lastNameDriver -
	 *            last name of driver
	 * @param divOrgs -
	 *            collection of div org that the vehicle is associated
	 * @param divGeo -
	 *            country and geographic division that the vehicle is associated
	 * @param first -
	 *            first index of the search to return
	 * @param count -
	 *            number of elements to return
	 * @throws InvalidDataException
	 * @throws CommunicationProblemException
	 * @throws DeslogedException
	 */
	public ListResult advancedSearch(String registrationNumber,
			String costCenter, String chasisNumber, String permissionNumber, String firstNameDriver,
			String lastNameDriver, Integer[][] divOrgs, Integer[][] divGeos,
			int first, int count, UserSessionData userSessionData, int status,
			String prefix) throws Exception {
		try {
			VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = vehicleHome.create();
			return vehicleReport.advancedSearch(userSessionData,
					registrationNumber, costCenter, chasisNumber, permissionNumber,
					firstNameDriver, lastNameDriver, divOrgs, divGeos, first,
					count, status, prefix);
		} catch (Exception e) {
			Object[]p = new Object[11];
			p[0] = registrationNumber;
			p[1] = costCenter;
			p[2] = chasisNumber;
			p[3] = firstNameDriver;
			p[4] = lastNameDriver;
			p[5] = divOrgs;
			p[6] = divGeos;
			p[7] = first;
			p[8 ] = count;
			p[9] = status;
			p[10] = prefix;
			handler.handleException("advancedSearch",e,userSessionData,p);
		}
		return null;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "administrator,eventcreate,driveredit,driverviewowndata,drivercreateevent,drivercreatefine,driverodometer,jjreporter"
	 * @param driverId
	 * @return
	 * @throws InvalidDataException
	 * @throws DeslogedException
	 * @throws CommunicationProblemException
	 */
	public Collection findDriversVehicles(Integer driverId,
			UserSessionData userSessionData) throws Exception {

		try {
			VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = vehicleHome.create();
			if (userSessionData.isDriver()) {
				return vehicleReport.findDriversVehicles(userSessionData,
						userSessionData.getDriverData().getId());
			}
			return vehicleReport.findDriversVehicles(userSessionData, driverId);
		} catch (Exception e) {
			Object[]p = new Object[1];
			p[0] = driverId;
			handler.handleException("findDriversVehicles",e,userSessionData,p);
		}
		return null;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "vehicleview,administrator"
	 */
	public Collection getObservations(Integer vehicleId,
			UserSessionData userSessionData) throws Exception {

		try {

			// UserReport access
			VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			return vehicleReport.getObservations(vehicleId, userSessionData);

		} catch (Exception e) {
			Object[] p = new Object[1];
			p[0] = vehicleId;
			handler.handleException("getObservations",e,userSessionData,p);
		}
		return null;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "vehicleupdate,administrator"
	 */
	public List getVehicles(String vehicles, UserSessionData userSessionData)
			throws Exception {

		try {

			// UserReport access
			VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			List list = vehicleReport.getVehicles(vehicles, userSessionData);
			return list;

		} catch (Exception e) {
			Object[]p = new Object[1];
			p[0] = vehicles;
			handler.handleException("getVehicles",e,userSessionData,p);
		}
		return null;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "administrator,vehiclecreate,drivercreatevehicle,vehicleupdate"
	 *                 Advanced search of drivers, includes the most important
	 *                 parameters to find
	 * @param registrationNumber -
	 *            registrationNumber of vehicle
	 * @param country -
	 *            country of vehicle
	 * @return boolean
	 * @throws CommunicationProblemException
	 * @throws DeslogedException
	 */
	public boolean vehicleExist(String registrationNumber, Integer country, UserSessionData userSessionData)
			throws Exception {
		try {
			VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = vehicleHome.create();
			return vehicleReport.vehicleExist(registrationNumber, country, userSessionData);
		} catch (Exception e) {
			Object[] p = new Object[2];
			p[0] = registrationNumber;
			p[1] = country;
			handler.handleException("vehicleExist",e,null,p);
		}
		return false;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "administrator,vehicleedit,drivervehicleedit,vehiclecreate,vehicleupdate"
	 *                 Advanced search of drivers, includes the most important
	 *                 parameters to find
	 * @param registrationNumber -
	 *            registrationNumber of vehicle
	 * @param country -
	 *            country of vehicle
	 * @return boolean
	 * @throws CommunicationProblemException
	 * @throws DeslogedException
	 */
	public boolean existOther(Integer id, String registrationNumber,
			Integer country) throws Exception {
		try {
			VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = vehicleHome.create();
			return vehicleReport.existOther(id, registrationNumber, country);
		} catch (Exception e) {
			Object[] p = new Object[3];
			p[0] = id;
			p[1] = registrationNumber;
			p[2] = country;
			handler.handleException("existOther",e,null,p);
		}
		return false;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,query,jjreporter"
	 */
	public Collection<VehicleData> getReportVehicles(String plate, String trade, String model, String year, UserSessionData userSessionData) throws Exception {
		try {
			VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			return vehicleReport.getReportVehicles(plate, trade, model, year, userSessionData);
		} catch (Exception e) {
			Object[]p = {plate,trade,model,year};
			handler.handleException("getReportVehicles",e,userSessionData,p);
		}
		return null;
	}
	
	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,query,jjreporter"
	 */
	public ListResult getReportVehicles(String type, String trade, String year,String use, String fuel, String centerCost, Integer[][] divOrgs,
			Integer[][] divGeos, int first, int count,UserSessionData userSessionData, String plate, String model, int status) throws Exception {
		try {
			VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			return vehicleReport.getReportVehicles(type, trade, year, use, fuel, centerCost, divOrgs, divGeos, first, count, userSessionData, plate, model, status);
		} catch (Exception e) {
			Object[]p = {type,trade,year,use,fuel,centerCost,divOrgs,divGeos,plate,model,status};
			handler.handleException("getReportVehicles",e,userSessionData,p);
		}
		return null;
	}
	
	
	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,query"
	 */
	public Collection findVehicleDriversLogList(Integer idVehicle, UserSessionData userSessionData) throws Exception{
	
		try{
			Collection result = null;
			Date fromDate = null;
			Date toDate = null;
			Date date = null;
			Integer idDriver;
			DriverMainAttributes driver = null;
			VehicleDriverListData vehicleDriverData = null;
			
			VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			DriverReportHome driverHome = DriverReportUtil.getHome();
			DriverReport driverReport = driverHome.create();
			
			// Obtengo la coleccion (date, driver) del idVehicle:
			Collection vehicleLog = vehicleReport.findVehicleDriversLogList(idVehicle, userSessionData);
			Integer lastDriver = null;
			boolean firstDriver = true;
			if (vehicleLog != null && !vehicleLog.isEmpty()){
				result = new LinkedList();
				Iterator it = vehicleLog.iterator();
				
				while (it.hasNext()){
					
					Object[] log = (Object[]) it.next();
					date = (Date) log[0];
					idDriver = (Integer) log[1];
					
					if (firstDriver){
						// Primer driver de la lista:
						fromDate = date;
						lastDriver = idDriver;
						firstDriver = false;
						
					}else if (idDriver != lastDriver ){
						// Si el idDriver es diferente que el anterior:
						toDate = date;
						// Guardo el registro del driver anterior
						if (lastDriver != null){
							
							driver = driverReport.getDriverMainAttributes(lastDriver, userSessionData);
						}else{
							driver = null;
							
						}
						
						if ((driver == null && 
							!this.formatDate(fromDate).equalsIgnoreCase(this.formatDate(toDate))) ||
							(driver != null)){
							/*
							Agrego un registro de VehicleDriver cuando:
								1. Driver es null, pero hubo unos dias en que el vehiculo no tuvo
								conductor asociado
								2. Hay un driver != null.
							*/
							vehicleDriverData = new VehicleDriverListData(driver,fromDate,toDate);
							result.add(vehicleDriverData);	
						}
																	
						fromDate = date;
						toDate = null;
						lastDriver = idDriver;
					}
					
				} // fin while
				
				if (!firstDriver){
					if (lastDriver != null){
						driver = driverReport.getDriverMainAttributes(lastDriver, userSessionData);
					}else{
						driver = null;
					}
					
					if ((driver == null && 
						!this.formatDate(fromDate).equalsIgnoreCase(this.formatDate(toDate))) ||
						(driver != null)){
						/*
						Agrego un registro de VehicleDriver cuando:
							1. Driver es null, pero hubo unos dias en que el vehiculo no tuvo
							conductor asociado
							2. Hay un driver != null.
						*/
						vehicleDriverData = new VehicleDriverListData(driver,fromDate,toDate);
						result.add(vehicleDriverData);	
					}
					
				}
		
			} // fin if vehicleLog != null
			return result;
		}catch (Exception e){
			Object[] parameters = {idVehicle};
    		handler.handleException("findVehicleDriversLogList",e, userSessionData, parameters);
		}
		return null;
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	private String formatDate(Date date) {
		if(date == null) {
            return "";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);
    }
	    
	
	 /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     */
    public ListResult getReportTrailers(String type, String trade, String year,
    		String use, String fuel, String centerCost, Integer[][] divOrgs,
    		Integer[][] divGeos, int first, int count,
    		UserSessionData userSessionData, String plate, String model, 
    		int status) throws Exception {
    	try {
			VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			return vehicleReport.getReportTrailers(type, trade, year, use,
					fuel, centerCost, divOrgs, divGeos, first, count,
					userSessionData, plate, model, status);
		} catch (Exception e) {
			Object[] p = new Object[13];
			p[0] = type;
			p[1] = trade;
			p[2] = year;
			p[3] = use;
			p[4] = fuel;
			p[5] = centerCost;
			p[6] = divOrgs;
			p[7] = divGeos;
			p[8] = first;
			p[9] = count;
			p[10] = plate;
			p[11] = model;
			p[12] = status;
			handler.handleException("getReportVehicles",e,userSessionData,p);
		}
		return null;
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     */
    public ListResult getReportVehiclesAndTrailers(String type, String trade, String year,
    		String use, String fuel, String centerCost, Integer[][] divOrgs, 
    		Integer[][] divGeos, int first, int count,UserSessionData userSessionData, 
            String plate, String model, int status) throws Exception {
    	try{
	    	VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			return vehicleReport.getReportVehiclesAndTrailers(type, trade, year, use,
					fuel, centerCost, divOrgs, divGeos, first, count,
					userSessionData, plate, model, status);
		} catch (Exception e) {
			Object[] p = new Object[13];
			p[0] = type;
			p[1] = trade;
			p[2] = year;
			p[3] = use;
			p[4] = fuel;
			p[5] = centerCost;
			p[6] = divOrgs;
			p[7] = divGeos;
			p[8] = first;
			p[9] = count;
			p[10] = plate;
			p[11] = model;
			p[12] = status;
			handler.handleException(".getReportVehiclesAndTrailers",e,userSessionData,p);
		}
		return null;
	}
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     */
    public ListResult getExtendedReportVehiclesAndTrailers(String type, String trade, String year,
    		String use, String fuel, String centerCost, Integer[][] divOrgs, 
    		Integer[][] divGeos, int first, int count,UserSessionData userSessionData, 
            String plate, String model, int status) throws Exception {
    	try{
	    	VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			return vehicleReport.getReportVehiclesAndTrailers(type, trade, year, use,
					fuel, centerCost, divOrgs, divGeos, first, count,
					userSessionData, plate, model, status);
		} catch (Exception e) {
			Object[] p = new Object[13];
			p[0] = type;
			p[1] = trade;
			p[2] = year;
			p[3] = use;
			p[4] = fuel;
			p[5] = centerCost;
			p[6] = divOrgs;
			p[7] = divGeos;
			p[8] = first;
			p[9] = count;
			p[10] = plate;
			p[11] = model;
			p[12] = status;
			handler.handleException("getExtendedReportVehiclesAndTrailers",e,userSessionData,p);
		}
		return null;
	}
    
    

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,query"
	 */
	public ListResult getExtendedReportVehicles(String type, String trade,
			String year, String use, String fuel, String centerCost,
			Integer[][] divOrgs, Integer[][] divGeos, int first, int count,
			UserSessionData userSessionData, String plate, String model,
			int status) throws Exception {
		try {
			VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			return vehicleReport.getReportVehicles(type, trade, year, use,
					fuel, centerCost, divOrgs, divGeos, first, count,
					userSessionData, plate, model, status);
		} catch (Exception e) {
			Object[] p = new Object[13];
			p[0] = type;
			p[1] = trade;
			p[2] = year;
			p[3] = use;
			p[4] = fuel;
			p[5] = centerCost;
			p[6] = divOrgs;
			p[7] = divGeos;
			p[8] = first;
			p[9] = count;
			p[10] = plate;
			p[11] = model;
			p[12] = status;
			handler.handleException("getExtendedReportVehicles",e,userSessionData,p);
		}
		return null;
	}
	
	
	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,query"
	 */
	public ListResult getExtendedReportTrailers(String type, String trade,
			String year, String use, String fuel, String centerCost,
			Integer[][] divOrgs, Integer[][] divGeos, int first, int count,
			UserSessionData userSessionData, String plate, String model,
			int status) throws Exception {
		try {
			VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			return vehicleReport.getReportTrailers(type, trade, year, use,
					fuel, centerCost, divOrgs, divGeos, first, count,
					userSessionData, plate, model, status);
		} catch (Exception e) {
			Object[] p = new Object[13];
			p[0] = type;
			p[1] = trade;
			p[2] = year;
			p[3] = use;
			p[4] = fuel;
			p[5] = centerCost;
			p[6] = divOrgs;
			p[7] = divGeos;
			p[8] = first;
			p[9] = count;
			p[10] = plate;
			p[11] = model;
			p[12] = status;
			handler.handleException("getExtendedReportTrailers",e,userSessionData,p);
		}
		return null;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,fleetupdate,fleetedit,fleetinsertdata"
	 */
	public Collection findVehiclesByFleet(Integer fleetId,UserSessionData userSessionData) throws Exception {
		try {
			VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			return vehicleReport.findVehiclesByFleet(fleetId, userSessionData);
		} catch (Exception e) {
			Object[] p = new Object[1];
			p[0] = fleetId;
			handler.handleException("findVehiclesByFleet",e,userSessionData,p);
		}
		return null;
	}

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,fleetupdate,fleetedit,fleetinsertdata"
     */
    public Collection findVehiclesByFleetNew(Integer fleetId,UserSessionData userSessionData) throws Exception {
        try {
            VehicleReportHome home = VehicleReportUtil.getHome();
            VehicleReport vehicleReport = home.create();
            return vehicleReport.findVehiclesByFleetNew(fleetId, userSessionData);
        } catch (Exception e) {
        	Object[] p = new Object[1];
        	p[0] = fleetId;
            handler.handleException("findVehiclesByFleet",e,userSessionData,p);
        }
        return null;
    }

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "administrator,eventcreate,finecreate,vehicleedit,drivervehicleedit,vehiclechecklist,jjreporter"
	 * 
	 * @param userPK
	 * @return UserData
	 * @throws InvalidDataException
	 * @throws CommunicationProblemException
	 * @throws DeslogedException
	 */
	public CheckListData findVehicleCheckList(UserSessionData userSessionData) throws Exception {
		try {
			CheckListData checkList = null;

			// UserReport access
			VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			checkList = vehicleReport.findVehicleCheckList(userSessionData);

			return checkList;
		} catch (Exception e) {
			handler.handleException("findVehicleCheckList",e,userSessionData,null);
		}
		return null;
	}
	
    /**
     * Contratos de Alquiler de Vehiculos Allergan
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query,vehicleview,vehicleupdate,vehicleedit"
     * @return
     */
	public Collection findVehicleContracts(Integer id, UserSessionData userSessionData) throws Exception {
		Collection contracts = null;
		try {
			
			// UserReport access
			VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			contracts = vehicleReport.findVehicleContracts(id,userSessionData);

		} catch (Exception e) {
			handler.handleException("findVehicleCheckList",e,userSessionData,null);
		}
		return contracts;
	}

	
	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "administrator,eventcreate,finecreate,vehicleedit,drivervehicleedit,vehiclechecklist"
	 * 
	 * @param userPK
	 * @return UserData
	 * @throws InvalidDataException
	 * @throws CommunicationProblemException
	 * @throws DeslogedException
	 */
	public CheckListData findVehicleCheckListNalco(UserSessionData userSessionData) throws Exception {
		try {
			CheckListData checkList = null;

			// UserReport access
			VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			checkList = vehicleReport.findVehicleCheckListNalco(userSessionData);

			return checkList;
		} catch (Exception e) {
			handler.handleException("findVehicleCheckListNalco",e,userSessionData,null);
		}
		return null;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "administrator,eventcreate,finecreate,vehicleedit,drivervehicleedit"
	 * 
	 * @param userPK
	 * @return UserData
	 * @throws InvalidDataException
	 * @throws CommunicationProblemException
	 * @throws DeslogedException
	 */
	public VehicleData findVehicleByPlate(String plate,
			UserSessionData userSessionData) throws Exception {
		try {
			// Check data

			VehicleData data = null;

			// UserReport access
			VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			data = vehicleReport.findVehicleByPlate(plate, userSessionData);

			return data;

		} catch (Exception e) {
			Object[] p = new Object[1];p[0] = plate;
			handler.handleException("findVehicleByPlate",e,userSessionData,p);
		}
		return null;
	}
	
	 /**
     * Devuelve un listado de vehiculos cuya matricula sea "plate"
     * y que pertenezcan a la corporaraciÃ²n indicada en el userSessionData.
     * 
     * "With Repeats" significa que devuelve todos los que encuentre con esa placa, no solo el primero.
     * 
     * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,eventcreate,finecreate,vehicleedit,drivervehicleedit,eventupload"
	 * 
	 * @param plate
	 * @return ListResult
	 * @throws Exception   
	 */
    public ListResult findVehicleByPlateWithRepeats(String plate, UserSessionData userSessionData) throws Exception {
    	try {
			// Check data

			ListResult data = null;

			// UserReport access
			VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			data = vehicleReport.findVehicleByPlateWithRepeats(plate, userSessionData);

			return data;

		} catch (Exception e) {
			Object[] p = new Object[1];p[0] = plate;
			handler.handleException("findVehicleByPlateWithRepeats",e,userSessionData,p);
		}
		return null;
    }

	 /**
     * Devuelve un listado de vehiculos cuya matricula sea "plate"
     * y que pertenezcan a la corporaraciÃ²n indicada en el userSessionData.
     * 
     * "With Repeats" significa que devuelve todos los que encuentre con esa placa, no solo el primero.
     * 
     * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,eventcreate,finecreate,vehicleedit,drivervehicleedit,eventupload"
	 * 
	 * @param plate
	 * @return ListResult
	 * @throws Exception   
	 */
    public ListResult findVehiclesByPlate(String plate, UserSessionData userSessionData) throws Exception {
    	try {
			return  VehicleReportUtil.getHome().create().findVehiclesByPlate(plate, userSessionData);
		} catch (Exception e) {
			Object[] p = {plate};
			handler.handleException("findVehiclesByPlate",e,userSessionData,p);
		}
		return null;
    }

    /**
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "administrator,eventcreate,finecreate,vehicleedit,drivervehicleedit"
	 * 
	 * @param userPK
	 * @return UserData
	 * @throws InvalidDataException
	 * @throws CommunicationProblemException
	 * @throws DeslogedException
	 */
	public VehicleCheckData findLastVehicleCheck(Integer id,
			UserSessionData userSessionData) throws Exception {
		try {
			// Check data

			if (id == null) {
				throw new InvalidDataException(
						"Class business.vehicle.VehicleReportFacade(findDriverByPrimaryKey), primary Key is empty",
						"generic.vehicle.vehicledata.id.empty");
			}

			VehicleCheckData data = null;

			// UserReport access
			VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			data = vehicleReport.findLastVehicleCheck(id, userSessionData);

			return data;

		} catch (Exception e) {
			Object[] p = new Object[1];
			p[0] = id;
			handler.handleException("findLastVehicleCheck",e,userSessionData,p);
		}
		return null;
	}
	
	
	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "administrator,eventcreate,finecreate,vehicleedit,drivervehicleedit"
	 * 
	 * @param userPK
	 * @return UserData
	 * @throws InvalidDataException
	 * @throws CommunicationProblemException
	 * @throws DeslogedException
	 */
	public Date findLastVehicleCheckDate(Integer id,
			UserSessionData userSessionData) throws Exception {
		try {
			if (id == null) {
				throw new InvalidDataException(
						"Class business.vehicle.VehicleReportFacade(findDriverByPrimaryKey), primary Key is empty",
						"generic.vehicle.vehicledata.id.empty");
			}

			Date checkListDate = null;

			// UserReport access
			VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			checkListDate = vehicleReport.findLastVehicleCheckDate(id, userSessionData);

			return checkListDate;

		} catch (Exception e) {
			 Object[] p = new Object[1];
         	 p[0] = id;
			handler.handleException("findLastVehicleCheckId",e,userSessionData,p);
		}
		return null;
	}
	
	
	
	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,eventcreate,finecreate,vehicleedit,drivervehicleedit,vehiclechecklist"
	 */
	public VehicleCheckData findVehicleCheck(Integer id,
			UserSessionData userSessionData) throws Exception {
		VehicleCheckData data = null;
		try {
			if (userSessionData == null) {
				throw new DeslogedException("findVehicleCheck",
						"userSessionData = null");
			}
			if (id == null) {
				throw new InvalidDataException("findVehicleCheck", "id = null");
			}

			VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			data = vehicleReport.getVehicleCheck(id, userSessionData);

		} catch (Exception e) {
			Object[] p = new Object[1];
			p[0] = id;
			handler.handleException("findVehicleCheck",e,userSessionData,p);
		}
		return data;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,eventcreate,finecreate,vehicleedit,drivervehicleedit,vehiclechecklist,systemaccess"
	 */
	public VehicleCheckData findVehicleCheckById(Integer id, UserSessionData userSessionData) throws Exception {
		VehicleCheckData data = null;
		try {
			if (userSessionData == null) {
				throw new DeslogedException("findVehicleCheckById", "userSessionData = null");
			}
			if (id == null) {
				throw new InvalidDataException("findVehicleCheckById", "id = null");
			}

			VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			data = vehicleReport.findVehicleCheckById(id, userSessionData);

		} catch (Exception e) {
			Object[] p = new Object[1];
			p[0] = id;
			handler.handleException("findVehicleCheck",e,userSessionData,p);
		}
		return data;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "administrator,eventcreate,finecreate,vehicleedit,drivervehicleedit"
	 */
	public Collection findVehicleChecks(Integer id,
			UserSessionData userSessionData) throws Exception {
		try {
			if (userSessionData == null) {
				throw new DeslogedException("findVehicleChecks",
						"userSessionData = null");
			}
			if (id == null) {
				throw new InvalidDataException("findVehicleChecks", "id = null");
			}

			VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			return vehicleReport.findVehicleChecks(id, userSessionData);

		} catch (Exception e) {
			Object[] p = new Object[1];
			p[0] = id;
			handler.handleException("findVehicleChecks",e,userSessionData,p);
		}
		return null;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "administrator,eventcreate,finecreate,vehicleedit,drivervehicleedit"
	 */
	public VehiclePursuitRouteData findVehiclePursuitRoute(Integer id,
			UserSessionData userSessionData) throws Exception {
		try {
			if (userSessionData == null) {
				throw new DeslogedException("findVehiclePursuitRoute",
						"userSessionData = null");
			}
			if (id == null) {
				throw new InvalidDataException("findVehiclePursuitRoute",
						"id = null");
			}

			VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			return vehicleReport.findVehiclePursuitRoute(id, userSessionData);

		} catch (Exception e) {
			Object[] p = new Object[1];
			p[0] = id;
			handler.handleException("findVehiclePursuitRoute",e,userSessionData,p);
		}
		return null;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "administrator,eventcreate,finecreate,vehicleedit,drivervehicleedit,drivercreateevent,vehiclechecklist"
	 */
	public Collection findVehiclePursuitRoutes(Integer id,
			UserSessionData userSessionData) throws Exception {
		try {
			if (userSessionData == null) {
				throw new DeslogedException("findVehiclePursuitRoutes",
						"userSessionData = null");
			}
			if (id == null) {
				throw new InvalidDataException("findVehiclePursuitRoutes",
						"id = null");
			}

			VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			return vehicleReport.findVehiclePursuitRoutes(id, userSessionData);

		} catch (Exception e) {
			Object[] p = new Object[1];
			p[0] = id;
			handler.handleException("findVehiclePursuitRoutes",e,userSessionData,p);
		}
		return null;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "administrator,vehiclecreate,drivervehicleedit,vehicleedit,vehicleupdate"
	 *                 Advanced search of drivers, includes the most important
	 *                 parameters to find
	 * @param registrationNumber -
	 *            registrationNumber of vehicle
	 * @param country -
	 *            country of vehicle
	 * @return boolean
	 * @throws CommunicationProblemException
	 * @throws DeslogedException
	 */
	public boolean vehicleCheckExist(Integer vehicleId) throws Exception {
		try {
			VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = vehicleHome.create();
			return vehicleReport.vehicleCheckExist(vehicleId);
		} catch (Exception e) {
			Object[] p = new Object[1];
			p[0] = vehicleId;
			handler.handleException("vehicleCheckExist",e,null,p);
		}
		return false;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,query"
	 */
	public ReportCheckListData getChecksCount(Integer fromMonth,
			Integer fromYear, Integer toMonth, Integer toYear,
			Integer[][] divOrgs, Integer[] divGeos, Text messages, int order,
			UserSessionData userSessionData) throws Exception {
		try {
			VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = vehicleHome.create();
			Hashtable hash = vehicleReport.getChecksCount(fromMonth, fromYear,
					toMonth, toYear, divOrgs, divGeos, messages, order,
					userSessionData);
			VehicleCheckReportDataType[] data = new VehicleCheckReportDataType[hash
					.size()];
			Enumeration enData = hash.elements();
			int i = 0;
			while (enData.hasMoreElements()) {
				data[i] = (VehicleCheckReportDataType) enData.nextElement();
				i++;
			}
			return new ReportCheckListData(data, messages);
		} catch (Exception e) {
			Object[] p = new Object[8];
			p[0] = fromMonth;
			p[1] = fromYear;
			p[2] = toMonth;
			p[3] = toYear;
			p[4] = divOrgs;
			p[5] = divGeos;
			p[6]  = messages;
			p[7] = order;
			
			handler.handleException("getChecksCount",e,userSessionData,p);
		}
		return null;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,query"
	 */
	public ReportVehiclePursuitRouteData getVehiclePursuitRouteCount(
			Integer fromMonth, Integer fromYear, Integer toMonth,
			Integer toYear, Integer[][] divOrgs, Integer[] divGeos,
			Text messages, int order, UserSessionData userSessionData)
			throws Exception {

		try {
			VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = vehicleHome.create();

			Hashtable hash = vehicleReport.getVehiclePursuitRouteCount(
					fromMonth, fromYear, toMonth, toYear, divOrgs, divGeos,
					messages, order, userSessionData);

			VehiclePursuitReportDataType[] data = new VehiclePursuitReportDataType[hash
					.size()];
			Enumeration enData = hash.elements();
			int i = 0;
			while (enData.hasMoreElements()) {
				data[i] = (VehiclePursuitReportDataType) enData.nextElement();
				i++;
			}
			return new ReportVehiclePursuitRouteData(data, messages);
		} catch (Exception e) {
			Object[] p = new Object[8];
			p[0] = fromMonth;
			p[1] = fromYear;
			p[2] = toMonth;
			p[3] = toYear;
			p[4] = divOrgs;
			p[5] = divGeos;
			p[6] = messages;
			p[7] = order;
			handler.handleException("getVehiclePursuitRouteCount",e,userSessionData,p);
		}
		return null;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,query"
	 */
	public ReportVehiclePursuitRouteData getVehiclePursuitRouteList(
			Integer fromMonth, Integer fromYear, Integer toMonth,
			Integer toYear, Integer[][] divOrgs, Integer[] divGeos,
			Text messages, int order, UserSessionData userSessionData)
			throws Exception {

		try {
			VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = vehicleHome.create();

			Hashtable hash = vehicleReport.getVehiclePursuitRouteList(
					fromMonth, fromYear, toMonth, toYear, divOrgs, divGeos,
					messages, order, userSessionData);

			VehiclePursuitReportDataType[] data = new VehiclePursuitReportDataType[hash.size()];
			Enumeration enData = hash.elements();
			int i = 0;
			while (enData.hasMoreElements()) {
				data[i] = (VehiclePursuitReportDataType) enData.nextElement();
				i++;
			}
			return new ReportVehiclePursuitRouteData(data, messages);
		} catch (Exception e) {
			Object[] p = new Object[8];
			p[0] = fromMonth;
			p[1] = fromYear;
			p[2] = toMonth;
			p[3] = toYear;
			p[4] = divOrgs;
			p[5] = divGeos;
			p[6] = messages;
			p[7] = order;
			handler.handleException("getVehiclePursuitRouteCount",e,userSessionData,p);
		}
		return null;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate,vehicleview,vehicleedit,dpachecklist"
	 */
	public ListResult getTrailers(String plate,int first,int count,
			int status,UserSessionData userSessionData) throws Exception {
		try {
			VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = vehicleHome.create();
			return vehicleReport.getTrailers(plate,first,count,status,userSessionData);
		} catch (Exception e) {
			Object[] p = new Object[4];
			p[0] = plate;
			p[1] = first;
			p[2] = count;
			p[3] = status;
			handler.handleException("getTrailers",e,userSessionData,p);
		}
		return null;
	}
	
	 /**
     * @ejb.interface-method
     * @ejb.permission role-name =
     *                 "administrator,eventcreate,finecreate,vehicleedit,drivervehicleedit,vehiclechecklist,dpachecklist"
     */
     public ArrayList<VehicleData> getVehiclesNonIn(ArrayList<String> plates,UserSessionData userSessionData) throws Exception {
    		 
    			try {
    				VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
    				VehicleReport vehicleReport = vehicleHome.create();
    				return vehicleReport.getVehiclesNonIn(plates,userSessionData);
    			} catch (Exception e) {
    				Object[] p = new Object[4];
    				p[0] = plates;
    				handler.handleException("getVehiclesNonIn",e,userSessionData,p);
    			}
    			return null;
     }
     /**
      * @ejb.interface-method
      * @ejb.permission role-name =
      *                 "administrator,eventcreate,finecreate,vehicleedit,drivervehicleedit,vehiclechecklist,dpachecklist"
      */
      public ArrayList<TrailerData> getTrailersNonIn(ArrayList<String> plates,UserSessionData userSessionData) throws Exception {
    	  try {
				VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
				VehicleReport vehicleReport = vehicleHome.create();
				return vehicleReport.getTrailersNonIn(plates,userSessionData);
			} catch (Exception e) {
				Object[] p = new Object[4];
				p[0] = plates;
				handler.handleException("getTrailersNonIn",e,userSessionData,p);
			}
			return null;
      }
      

	/**
	 * Retorna los Drivers que no hicieron la Evaluacion en Cabina. Se usa
	 * principalmente para Forestales.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,query"
	 */
	public ListResult vehicleWithoutPursuitRoute(
			UserSessionData userSessionData, Date initialDate, Date lastDate,
			Integer[][] divOrgs, Integer[][] divGeos, int status, int first,
			int count, String plate) throws Exception {
		try {
			VehicleReportHome home = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = home.create();
			return vehicleReport.vehicleWithoutPursuitRoute(userSessionData,
					initialDate, lastDate, divOrgs, divGeos, status, first,
					count, plate);
		} catch (Exception e) {
			Object[] p = new Object[8];
			p[0] = initialDate;
			p[1] = lastDate;
			p[2] = divOrgs;
			p[3] = divGeos;
			p[4] = status;
			p[5] = first;
			p[6] = count;
			p[7] = plate;
			handler.handleException("vehicleWithoutPursuitRoute",e,userSessionData,p);
		}
		return null;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "administrator,vehicleupdate,drivervehicleupdate,vehicleedit,dpachecklist"
	 */
	public TrailerData findTrailer(Integer id, UserSessionData userSessionData)
			throws Exception {
		try {
			VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = vehicleHome.create();
			return vehicleReport.findTrailer(id, userSessionData);
		} catch (Exception e) {
			Object[] p = new Object[1];
			p[0] = id;
			handler.handleException("findTrailer",e,userSessionData,p);
		}
		return null;
	}
	
	 /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate,vehicleedit,dpachecklist"
     */
    public ArrayList<TrailerData> findTrailer(String plate, UserSessionData userSessionData) throws Exception {
    	try {
			VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = vehicleHome.create();
			return vehicleReport.findTrailer(plate, userSessionData);
		} catch (Exception e) {
			Object[] p = new Object[1];
			p[0] = plate;
			handler.handleException("findTrailer",e,userSessionData,p);
		}
		return null;
    }

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate,vehicleedit"
	 */
	public ListResult findTrailerVehicles(Integer id,
			UserSessionData userSessionData) throws Exception {
		try {
			VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = vehicleHome.create();
			return vehicleReport.findTrailerVehicles(id, userSessionData);
		} catch (Exception e) {
			Object[] p = new Object[1];
			p[0] = id;
			handler.handleException("findTrailerVehicles",e,userSessionData,p);
		}
		return null;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "administrator,eventcreate,finecreate,drivervehicleedit,drivercreateevent,
	 *                 vehiclechecklist,dpachecklist"
	 */
	public CheckListData getCheckList(int checklist,
			UserSessionData userSessionData) throws Exception {
		try {
			VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = vehicleHome.create();
			return vehicleReport.getCheckList(checklist, userSessionData);
		} catch (Exception e) {
			Object[] p = new Object[1];
			p[0] = checklist;
			handler.handleException("getCheckList",e,userSessionData,p);
		}
		return null;
	}

    /**
     * @ejb.interface-method
     * @ejb.permission role-name =
     *                 "administrator,eventcreate,finecreate,drivervehicleedit,drivercreateevent,
     *                 vehiclechecklist"
     */
    public CheckListData getTruckCheckList(UserSessionData userSessionData, int type) throws Exception {
        try {
            VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
            VehicleReport vehicleReport = vehicleHome.create();
            return vehicleReport.getTruckCheckList(userSessionData,type);
        } catch (Exception e) {
        	Object[] p = new Object[1];
			p[0] = type;
            handler.handleException("getTruckCheckList",e,userSessionData,p);
        }
        return null;
    }

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,query" Forestales: Consulta de
	 *                 Vencimientos de Sucta y MTOP de Vehiculos y Plataformas.
	 */
	public ListResult getSuctaMTOPQuery(String plate, String suctaormtop,
			String status, String vehicleorplataform, String fromMonth,
			String fromYear, String toMonth, String toYear,
			Integer[][] divOrgs, Integer[][] divGeos, int first, int count,
			UserSessionData userSessionData) throws Exception {
		try {
			VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = vehicleHome.create();
			return vehicleReport.getSuctaMTOPQuery(plate, suctaormtop, status,
					vehicleorplataform, fromMonth, fromYear, toMonth, toYear,
					divOrgs, divGeos, first, count, userSessionData);
		} catch (Exception e) {
			Object[] p = new Object[11];
			p[0] = plate;
			p[1] = suctaormtop;
			p[2] = status;
			p[3] = vehicleorplataform;
			p[4] = fromMonth;
			p[5] = fromYear;
			p[6] = toMonth;
			p[7] = toYear;
			p[8] = divOrgs;
			p[9] = divGeos;
			p[10] = first;
			p[11] = count;
			
			handler.handleException("getSuctaMTOPQuery",e,userSessionData,p);
		}
		return null;
	}

	/**
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "administrator,fleetupdate,fleetedit,fleetinsertdata"
	 * 
	 * Devuelve el odometro que tiene ingresado un vehiculo para una flota dada
	 * en un periodo y aÃ±o dados y en el historial del odometerData trae el
	 * odometro De no haber datos devuelve NULL.
	 * 
	 */
	public OdometerData getVehicleOdometer(Integer fleetId, Integer vehicleId,
			Integer year, Integer period, UserSessionData userSessionData)
			throws Exception {

		try {
			VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = vehicleHome.create();
			return vehicleReport.getVehicleOdometer(fleetId, vehicleId, year,
					period, userSessionData);
		} catch (Exception e) {
			Object[] p = new Object[4];
			p[0] = fleetId;
			p[1] = vehicleId;
			p[2] = year;
			p[3] = period;
			handler.handleException("getVehicleOdometer",e,userSessionData,p);
		}
		return null;
	}

	/**
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "administrator,fleetupdate,fleetedit,fleetinsertdata"
	 */
	public Collection getVehicleFleetObservations(Integer fleetId,
			Integer vehicleId, UserSessionData userSessionData) throws Exception {
		try {
			VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = vehicleHome.create();
			return vehicleReport.getVehicleFleetObservations(fleetId,
					vehicleId, userSessionData);
		} catch (Exception e) {
			Object[] p = new Object[2];
			p[0] = fleetId;
			p[1] = vehicleId;
			handler.handleException("getVehicleOdometer",e,userSessionData,p);
		}
		return null;
	}
	
	 /**
     * @ejb.permission role-name = "administrator,vehicleview,vehicledelete,vehicleedit,vehicleupdate,vehiclecreate,vehiclechecklist,vehicleupload"
     * @ejb.interface-method	 
	 * @param userSessionData
	 * @param firstName
	 * @param lastName
	 * @param birthday
	 * 
	 * Devuelve true si existe un vehiculo con esa matricula y en ese pais.
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean existVehicle(UserSessionData userSessionData,String plate, Integer country) throws Exception
	{
		try {
			VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = vehicleHome.create();
			return vehicleReport.existVehicle(userSessionData,plate,country);
		} catch (Exception e) {
			Object[] p = new Object[2];
			p[0] = plate;
			p[1] = country;
			handler.handleException("getVehicleOdometer",e,userSessionData,p);
		}
		return false;
	}
	
	 /**   
     * @ejb.permission role-name = "administrator,query"
     * @ejb.interface-method
     * 
     * Si el parametro getAll es true trae todos los items, si es false solo trae
	 * los items del checklist que esten mal.
     */ 
	public ReportCheckListDetailedData getChecksCountDetailed(Integer fromMonth, Integer fromYear, 
			Integer toMonth, Integer toYear, Integer[][] divOrgs, Integer[] divGeos, 
			Text messages, int order, UserSessionData userSessionData, 
			boolean getAll, String type, int count, int first,String typeCheck) throws Exception {
		
		try {
			VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = vehicleHome.create();
			Object[] data = vehicleReport.getChecksCountDetailed(fromMonth,fromYear, 
					toMonth,toYear, divOrgs, divGeos, messages, 
					order,userSessionData, getAll, type, count, first,typeCheck);
			
			if (data != null){
				// Genero un hastable con las parejas item, text, para
				// poder mostrar una descripcion de cada item del checklist
				Hashtable itemsText = vehicleReport.getCheckListItemsText(
						userSessionData,(LinkedList)data[1]);
					
				VehicleCheckReportDetailedDataType[] checkData = 
					(VehicleCheckReportDetailedDataType[])data[0];
				int total = ((Integer)data[2]).intValue();
				LinkedList list = (LinkedList)data[1];
				
				ReportCheckListDetailedData reportData = 
					new ReportCheckListDetailedData(checkData, messages, 
						list, itemsText,total, userSessionData.getFilter().getCorporationId());
				
				return reportData;
			}
			
		} catch (Exception e) {
			Object[] p = new Object[12];
			p[0] = fromMonth;
			p[1] = fromYear;
			p[2] = toMonth;
			p[3] = toYear;
			p[4] = divOrgs;
			p[5] = divGeos;
			p[6] = messages;
			p[7] = order;
			p[8] = getAll;
			p[9] = type;
			p[10] = count;
			p[11] = first;
			
			handler.handleException("getChecksCountDetailed",e,userSessionData,p);
		}
		return null;
	}     
	

     /**
     * @ejb.permission role-name = "administrator,vehicleview,vehicledelete,vehicleedit,vehicleupdate,vehiclecreate,vehiclechecklist,vehicleupload"
     * @ejb.interface-method
     */     
    public Collection getTrailerPlates(String trailers,UserSessionData userSessionData) throws Exception {
        try {
            VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
            VehicleReport vehicleReport = vehicleHome.create();
            return vehicleReport.getTrailerPlates(trailers,userSessionData);
        } catch (Exception e) {
        	Object[] p = new Object[1];
        	p[0] = trailers;
            handler.handleException("getTrailerPlates",e,userSessionData,p);
        }
        return null;
    }

    /**
     * @ejb.permission role-name = "administrator,query"
     * @ejb.interface-method
     */     
    public TrailerWithoutCheckListData getTrailerWithoutCheckList(Date start, Date end, 
    		int status, Integer[][] divOrgs, Integer[] divGeos, Text messages,
    		UserSessionData userSessionData) throws Exception {
        try {
            VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
            VehicleReport vehicleReport = vehicleHome.create();
            
            Collection trailers = vehicleReport.getTrailerWithoutCheckList(start, end, status, divOrgs, divGeos,userSessionData);
            String ids = "0";
            Iterator it = trailers.iterator();
            while(it.hasNext()) {
                ids += "," + String.valueOf(((Object[])it.next())[0]);
            }
            Hashtable lastTrailers = vehicleReport.getTrailerLastCheckList(ids, userSessionData);

            Collection vehicles = vehicleReport.getVehicleWithoutCheckList(start, end, status, divOrgs, divGeos,userSessionData);
            ids = "0";
            it = vehicles.iterator();
            while(it.hasNext()) {
                ids += "," + String.valueOf(((Object[])it.next())[0]);
            }
            Hashtable lastVehicles = vehicleReport.getVehicleLastCheckList(ids, userSessionData);
            
            return new TrailerWithoutCheckListData(vehicles,lastVehicles,trailers,lastTrailers,messages);
        } catch (Exception e) {
        	Object [] p = new Object[6];
         	p[0] = start;
         	p[1] = end;
         	p[2] = status;
         	p[3] = divOrgs;
         	p[4] = divGeos;
         	p[5] = messages;
            handler.handleException("getTrailerWithoutCheckList",e,userSessionData,p);
        }
        return null;
    }

    /**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,fleetupdate,fleetedit"
     */
     public Collection getPlates(Collection vehicles,UserSessionData userSessionData) throws Exception {
         MemoryLog.getInstance().log(MemoryLog.START,"getPlates",getClass());
         Collection plates = new LinkedList();
         try {
             plates = VehicleReportUtil.getHome().create().getPlates(vehicles,userSessionData);
         } catch (Exception e) {
        	 Object[] p = new Object[1];
         	 p[0] = vehicles;
             handler.handleException("getPlates",e,userSessionData,p);
         }
         MemoryLog.getInstance().log(MemoryLog.END,"getPlates",getClass());
         return plates;
     }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name =
     *                 "administrator,eventcreate,finecreate,vehicleedit,drivervehicleedit,vehiclechecklist,dpachecklist"
     */
     public Collection getVehicleTrailers(Integer vehicle,UserSessionData userSessionData) throws Exception {
         MemoryLog.getInstance().log(MemoryLog.START,"getVehicleTrailers",getClass());
         Collection trailers = new LinkedList();
         try {
             trailers = VehicleReportUtil.getHome().create().getVehicleTrailers(vehicle,userSessionData);
         } catch (Exception e) {
        	 Object[] p = new Object[1];
         	 p[0] = vehicle;
             handler.handleException("getVehicleTrailers",e,userSessionData,p);
         }
         MemoryLog.getInstance().log(MemoryLog.END,"getVehicleTrailers",getClass());
         return trailers;
     }

     /**
      * @ejb.interface-method
      * @ejb.permission role-name = "administrator,eventcreate,finecreate,drivervehicleedit,drivercreateevent,vehiclechecklist" 
      */
     public Collection getCheckListTrailers(Integer vehicleCheckList,UserSessionData userSessionData) throws Exception {
        try {
            if (userSessionData == null) {
                throw new DeslogedException("getCheckListTrailers","userSessionData = null");
            }
            if (vehicleCheckList == null) {
                throw new InvalidDataException("getCheckListTrailers", "id = null");
            }
            return VehicleReportUtil.getHome().create().getCheckListTrailers(vehicleCheckList,userSessionData);
        } catch (Exception e) {
        	 Object[] p = new Object[1];
         	 p[0] = vehicleCheckList;
            handler.handleException("getCheckListTrailers",e,userSessionData,p);
        }
        return new LinkedList();
     }

     /**
      * @ejb.interface-method
      * @ejb.permission role-name = "administrator,query" 
      */
     public Collection getUncoformities(Integer fromMonth,Integer fromYear,Integer toMonth,Integer toYear,String[] divOrgs,boolean detailed,UserSessionData userSessionData) throws Exception {
        try {
            if (userSessionData == null) {
                throw new DeslogedException("getUncoformities","userSessionData = null");
            }
            VehicleReport report = VehicleReportUtil.getHome().create();
            if(detailed) {
            	return report.getDetailedUncoformities(fromMonth,fromYear,toMonth,toYear,divOrgs,userSessionData);
            }else {
            	return report.getUncoformities(fromMonth,fromYear,toMonth,toYear,divOrgs,userSessionData);
            }
        } catch (Exception e) {
        	 Object[] p = {fromMonth,fromYear,toMonth,toYear,divOrgs};
            handler.handleException("getUncoformities",e,userSessionData,p);
        }
        return new LinkedList();
     }

     /**
      * @throws Exception
      * @ejb.interface-method
      * @ejb.permission role-name = "vehicleedit,vehicleupdate,vehiclechecklist" 
      */
    public String[] findVehicleCheckListData(Integer vehicle,UserSessionData userSessionData) throws Exception {
    	try {
    		return VehicleReportUtil.getHome().create().findVehicleCheckListData(vehicle,userSessionData);
    	}catch (Exception e) {
       	 	Object[] p = {vehicle};
       	 	handler.handleException("getUncoformities",e,userSessionData,p);
		}
    	return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     */ 
   public Collection getCheckLists(Integer monthFrom, Integer yearFrom,Integer monthTo, Integer yearTo, int[] order, Integer[][] divOrgs, Integer[] divGeos, ItemData[] items, UserSessionData userSessionData) throws Exception {
	   Collection checkLists = new LinkedList();
	   try {
/*		   Collection list = VehicleReportUtil.getHome().create().getCheckLists(monthFrom,yearFrom,monthTo,yearTo,divOrgs,divGeos,userSessionData);
	       if(list != null && list.size() > 0) {
	    	   Iterator it = list.iterator();
	    	   while(it.hasNext()) {
	    		   checkLists.add(new CimsaCheckListData((VehicleCheck)it.next(),order,items));
	    	   }
	       }*/
		   checkLists = VehicleReportUtil.getHome().create().getCheckLists(monthFrom,yearFrom,monthTo,yearTo,divOrgs,divGeos,order,userSessionData);
	   }catch (Exception e) {
        	Object[] parameters = {monthFrom,yearFrom,monthTo,yearTo,divOrgs,divGeos};
        	handler.handleException("getCheckLists",e,userSessionData,parameters);
		}
    	return checkLists;
   }

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,query"
	 */
	public ReportVehiclePursuitRouteData getVehiclePursuitRouteCountByItem(
			Integer fromMonth, Integer fromYear, Integer toMonth,
			Integer toYear, Integer[][] divOrgs, Integer[] divGeos,
			Text messages, int order, UserSessionData userSessionData)
			throws Exception {

		try {
			VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = vehicleHome.create();

			Hashtable hash = vehicleReport.getVehiclePursuitRouteCountByItem(
					fromMonth, fromYear, toMonth, toYear, divOrgs, divGeos,
					messages, order, userSessionData);

			VehiclePursuitReportDataType[] data = new VehiclePursuitReportDataType[hash
					.size()];
			Enumeration enData = hash.elements();
			int i = 0;
			while (enData.hasMoreElements()) {
				data[i] = (VehiclePursuitReportDataType) enData.nextElement();
				i++;
			}
			return new ReportVehiclePursuitRouteData(data, messages);
		} catch (Exception e) {
			Object[] p = new Object[8];
			p[0] = fromMonth;
			p[1] = fromYear;
			p[2] = toMonth;
			p[3] = toYear;
			p[4] = divOrgs;
			p[5] = divGeos;
			p[6] = messages;
			p[7] = order;
			handler.handleException("getVehiclePursuitRouteCount",e,userSessionData,p);
		}
		return null;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,query"
	 */
	public ReportVehiclePursuitRouteData getVehiclePursuitRouteListByItem(
			Integer fromMonth, Integer fromYear, Integer toMonth,
			Integer toYear, Integer[][] divOrgs, Integer[] divGeos,
			Text messages, int order, UserSessionData userSessionData)
			throws Exception {

		try {
			VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = vehicleHome.create();

			Hashtable hash = vehicleReport.getVehiclePursuitRouteListByItem(
					fromMonth, fromYear, toMonth, toYear, divOrgs, divGeos,
					messages, order, userSessionData);

			VehiclePursuitReportDataType[] data = new VehiclePursuitReportDataType[hash
					.size()];
			Enumeration enData = hash.elements();
			int i = 0;
			while (enData.hasMoreElements()) {
				data[i] = (VehiclePursuitReportDataType) enData.nextElement();
				i++;
			}
			return new ReportVehiclePursuitRouteData(data, messages);
		} catch (Exception e) {
			Object[] p = new Object[8];
			p[0] = fromMonth;
			p[1] = fromYear;
			p[2] = toMonth;
			p[3] = toYear;
			p[4] = divOrgs;
			p[5] = divGeos;
			p[6] = messages;
			p[7] = order;
			handler.handleException("getVehiclePursuitRouteCount",e,userSessionData,p);
		}
		return null;
	}

    /**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "vehicleedit,vehicleupdate,administration" 
     */
	public Collection getVehicleMaintenances(Integer vehicle, UserSessionData userSessionData) throws Exception {
		Collection maintenances = null;
		try {
			maintenances = VehicleReportUtil.getHome().create().getVehicleMaintenances(vehicle,userSessionData);
		} catch (Exception e) {
			Object[] p = {vehicle};
			handler.handleException("getVehicleMaintenances",e,userSessionData,p);
		}
		return maintenances;
	}

	/**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "vehicleedit,vehicleupdate,administration" 
     */
	public VehicleMaintenanceData getVehicleMaintenance(Integer id,UserSessionData userSessionData) throws Exception {
		try {
			return VehicleReportUtil.getHome().create().getVehicleMaintenance(id,userSessionData);
		} catch (Exception e) {
			Object[] p = {id};
			handler.handleException("getVehicleMaintenance",e,userSessionData,p);
		}
		return null;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,vehicleedit"
	 */
	public Collection getVehiclesWithDriver(String type,int status, Integer[][] divOrgs, Integer[][] divGeos,UserSessionData userSessionData) throws Exception {
		try {
			VehicleReportHome vehicleHome = VehicleReportUtil.getHome();
			VehicleReport vehicleReport = vehicleHome.create();
			return vehicleReport.getVehiclesWithDriver(type,status,divOrgs,divGeos,userSessionData);
		} catch (Exception e) {
			Object[]p = {type,status,divOrgs,divGeos};
			handler.handleException("getVehiclesWithDriver",e,userSessionData,p);
		}
		return null;
	}

    /**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "vehicleedit,vehicleupdate,administration" 
     */
	public Collection getVehicleComplaints(Integer vehicle, UserSessionData userSessionData) throws Exception {
		Collection maintenances = null;
		try {
			maintenances = VehicleReportUtil.getHome().create().
				getVehicleComplaints(vehicle,userSessionData);
		} catch (Exception e) {
			Object[] p = {vehicle};
			handler.handleException("getVehicleComplaints",e,userSessionData,p);
		}
		return maintenances;
	}

	/**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "vehicleedit,vehicleupdate,administration" 
     */
	public ComplaintData getVehicleComplaint(Integer id,UserSessionData userSessionData) throws Exception {
		try {
			return VehicleReportUtil.getHome().create().getVehicleComplaint(id,userSessionData);
		} catch (Exception e) {
			Object[] p = {id};
			handler.handleException("getVehicleComplaint",e,userSessionData,p);
		}
		return null;
	}

	/**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "vehicleview,query,administration" 
     */
	public ListResult getComplaintsReport(String vehicle, Date from, Date to,Integer[][] divOrgs,Integer[][] divGeos,int first,int count, int status,UserSessionData userSessionData) throws Exception {
		Collection result = new LinkedList();
		try {
			return VehicleReportUtil.getHome().create().getComplaintsReport(vehicle,from,to,divOrgs,divGeos,first,count,status,userSessionData);
		} catch (Exception e) {
			Object[] p = {vehicle, from, to,divOrgs,divGeos,status};
			handler.handleException("getComplaintsReport",e,userSessionData,p);
		}
		return null;
	}
	
	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,vehicleedit,query"
	 */
	public ListResult getVehiclesWithDriverAndContracts(
			String type,int status, Integer[][] divOrgs, Integer[][] divGeos,
			String vehicleType, Date from, Date to,int first, int count,
			UserSessionData userSessionData) throws Exception {
		Collection result = new LinkedList();
		try {
			return VehicleReportUtil.getHome().create().getVehiclesWithDriverAndContracts
				(type,status,divOrgs,divGeos,vehicleType,from,to,first,count,userSessionData);
		} catch (Exception e) {
			Object[] p = {};
			handler.handleException("getVehiclesWithDriverAndContracts",e,userSessionData,p);
		}
		return null;
	}

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,driverview,driverviewowndata,drivercreateevent,drivercreatefine,driverodometer,jjreporter" 
     * @param userRequest
     * @param driverId
     * @return
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws CommunicationProblemException
     */
    public Collection findDriversVehicleHistory(UserSessionData userSessionData,Integer driverId) throws Exception {
        Collection result = new LinkedList();
		try {
			Collection values = VehicleReportUtil.getHome().create().findDriversVehicleHistory(userSessionData,driverId);
			if(values != null && values.size() > 0) {
				Iterator it = values.iterator();
				String[] last = {"--","--","--","--"};
				while(it.hasNext()) {
					Object[] data = (Object[])it.next();
					if(!String.valueOf(data[0]).equals(last[0])) {
						if(!last[0].equals("--")) {
							result.add(last);
							last = new String[4];
							for(int i = 0; i < 4; i++)
								last[i] = "--";
						}
						if(data[2] != null && data[2].equals(driverId)) {
							last[0] = String.valueOf(data[0]);
							last[1] = String.valueOf(data[1]);
							last[2] = Util.formatDate((Date)data[3]);
						}
					}else {
						if(data[2] == null || !data[2].equals(driverId)) {
							last[3] = Util.formatDate((Date)data[3]);
							result.add(last);
							last = new String[4];
							for(int i = 0; i < 4; i++)
								last[i] = "--";
						}
					}
				}
				if(!last[0].equals("--")) {
					result.add(last);
				}
			}
		} catch (Exception e) {
			Object[] p = {};
			handler.handleException("findDriversVehicleHistory",e,userSessionData,p);
		}
        return result;
    }
    
    /**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "vehicleedit,vehicleupdate,administration" 
     */
	public void dpaExportCheckListToXLS(Text messages , UserSessionData userSessionData,DPACheckListXLSFilter filter,OutputStream output) throws Exception {
		try {
			VehicleReportUtil.getHome().create().dpaExportCheckListToXLS(messages, userSessionData, filter, output);
		} catch (Exception e) {
			Object[] p = {filter};
			handler.handleException("dpaExportCheckListToXLS",e,userSessionData,p);
		}
	}
	
    /**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "vehicleedit,vehicleupdate,administration,query" 
     */
	public void checkListReportToXLS(boolean comment,int type,Date start,Date end, Integer vehicleType, Integer chargeType, Integer checkPlace, OutputStream output,Text messages,UserSessionData userSessionData) throws Exception {
		try {
			VehicleReportUtil.getHome().create().exportCheckListForestales(comment,type, start, end, vehicleType, chargeType, checkPlace, output, messages, userSessionData);
		} catch (Exception e) {
			Object[] p = {type,start,end};
			handler.handleException("checkListReportToXLS",e,userSessionData,p);
		}
	}
	
    /**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "vehicleedit,vehicleupdate,administration,query" 
     */
	public void vehiclesWOCheckListReportToXLS(Date start,Date end,int status,OutputStream output,Text messages,UserSessionData userSessionData) throws Exception {
		try {
			VehicleReportUtil.getHome().create().vehiclesWOCheckListReportToXLS(start, end, status, output, messages, userSessionData);
		} catch (Exception e) {
			Object[] p = {start,end};
			handler.handleException("checkListReportToXLS",e,userSessionData,p);
		}
	}
	
	/**
	 * @ejb.permission role-name ="administrator,driveredit,driverviewowndata"
	 * @ejb.interface-method
	 */
	public Collection<TelematicControlData> findTelematicControls(
			Integer vehicle, UserSessionData userSessionData) throws Exception {
		try {
			return VehicleReportUtil.getHome().create().findTelematicControls(vehicle, userSessionData);
		} catch (Exception e) {
			Object[] p = {vehicle};
			handler.handleException("findTelematicControls",e,userSessionData,p);
		}
		return new ArrayList<TelematicControlData>();
	}
	
	/**
	 * @throws Exception 
	 * @ejb.permission role-name ="administrator,driveredit,driverviewowndata"
	 * @ejb.interface-method
	 */
	public Collection<TelematicControlData> findTelematicControlsDPA(UserSessionData userSessionData) throws Exception{
			try{
				return VehicleReportUtil.getHome().create().findTelematicControlsDPA( userSessionData);
			}catch(Exception e){ 
				Object[] p = {};
				handler.handleException("findTelematicControls",e,userSessionData,p);
			}
			
			return new ArrayList<TelematicControlData>();
	}
	
	/**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "vehicleedit,vehicleupdate,administration" 
     */
	public void exportVehicleCheckListResumeToXLS(UserSessionData userSessionData,DPACheckListXLSFilter filter,OutputStream output) throws Exception {
		try {
			VehicleReportUtil.getHome().create().exportVehicleCheckListResumeToXLS(userSessionData, filter, output);
		} catch (Exception e) {
			Object[] p = {filter};
			handler.handleException("exportVehicleCheckListResumeToXLS",e,userSessionData,p);
		}
	}
	
	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,vehicleview"
	 */
	public void exportTelematicControlDPA(Text messages, UserSessionData userSessionData,DPATelematicControlFilter filter,OutputStream output) throws Exception {
		try {
			VehicleReportUtil.getHome().create().exportTelematicControlDPA(messages,userSessionData, filter, output);
		} catch (Exception e) {
			Object[] p = {filter};
			handler.handleException("exportTelematicControlDPA",e,userSessionData,p);
		}
	}
	
	/**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,dpachecklist,drivervehicleedit,drivercreateevent,vehiclechecklist,vehicleview" 
     */
    public ArrayList<VehicleCheckResumeData> findVehicleCheckReportResume(Integer startYear,Integer endYear,Integer startMonth,Integer endMonth,UserSessionData userSessionData) throws Exception {
    	try {
			return VehicleReportUtil.getHome().create().findVehicleCheckReportResume(startYear,endYear,startMonth,endMonth,userSessionData);
		} catch (Exception e) {
			Object[] p = {startMonth,endMonth,userSessionData};
			handler.handleException("findVehicleCheckReportResume",e,userSessionData,p);
		}
    	return new ArrayList<VehicleCheckResumeData>();
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,dpachecklist,drivervehicleedit,drivercreateevent,vehiclechecklist,vehicleview" 
     */
    public ArrayList<VehicleCheckReportGroupData> findVehicleCheckReportResumeGroupItems(Integer startYear, Integer endYear,Integer startMonth,Integer endMonth,UserSessionData userSessionData) throws Exception {
    	try {
			return VehicleReportUtil.getHome().create().findVehicleCheckReportResumeGroupItems(startYear,endYear,startMonth,endMonth,userSessionData);
		} catch (Exception e) {
			Object[] p = {startMonth,endMonth,userSessionData};
			handler.handleException("findVehicleCheckReportResumeGroupItems",e,userSessionData,p);
		}
    	return new ArrayList<VehicleCheckReportGroupData>();
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,dpachecklist,drivervehicleedit,drivercreateevent,vehiclechecklist,vehicleview" 
     */
    public ArrayList<VehicleCheckReportGroupData> findVehicleCheckReportResumeGroupChecks(Integer startYear, Integer endYear,Integer startMonth,Integer endMonth,UserSessionData userSessionData) throws Exception {
    	try {
			return VehicleReportUtil.getHome().create().findVehicleCheckReportResumeGroupChecks(startYear,endYear,startMonth,endMonth,userSessionData);
		} catch (Exception e) {
			Object[] p = {startMonth,endMonth,userSessionData};
			handler.handleException("findVehicleCheckReportResumeGroupItems",e,userSessionData,p);
		}
    	return new ArrayList<VehicleCheckReportGroupData>();
    }
    	

	/**
	 * @ejb.interface-method view-type = "remote"
	 * @ejb.permission role-name = "administrator,systemaccess,vehicleview"
	 * @param data
	 * @throws Exception 
	 */
	public ArrayList<DPAGoalData> getDPAGoals(Integer year,UserSessionData userSession) throws Exception {
		ArrayList<DPAGoalData> res=new ArrayList<DPAGoalData>();
		try {
			return VehicleReportUtil.getHome().create().getDPAGoals(year, userSession);
		} catch (Exception e) {
			Object[] p = {year,userSession};
			handler.handleException("getDPAGoals",e,userSession,p);
		}
    	return res;
	}
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @ejb.permission role-name = "administrator,systemaccess,vehicleview"
	 * @param data
	 * @throws Exception 
	 */
	public ArrayList<DPAGoalData> getDPAGoals(Integer year,Integer month,UserSessionData userSession) throws Exception {
		ArrayList<DPAGoalData> res=new ArrayList<DPAGoalData>();
		try {
			return VehicleReportUtil.getHome().create().getDPAGoals(year,month, userSession);
		} catch (Exception e) {
			Object[] p = {year,userSession};
			handler.handleException("getDPAGoals",e,userSession,p);
		}
    	return res;
	}
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @ejb.permission role-name = "administrator,systemaccess,vehicleview"
	 * @param data
	 * @throws Exception 
	 */
	public ArrayList<DPAVehicleActiveData> getDPAVehicleActive(Integer year,UserSessionData userSession) throws Exception {
		ArrayList<DPAVehicleActiveData> res=new ArrayList<DPAVehicleActiveData>();
		try {
			return VehicleReportUtil.getHome().create().getDPAVehicleActive(year,userSession);
		} catch (Exception e) {
			Object[] p = {year,userSession};
			handler.handleException("getDPAVehicleActive",e,userSession,p);
		}
    	return res;
	}
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @ejb.permission role-name = "administrator,systemaccess,vehicleview"
	 * @param data
	 * @throws Exception 
	 */
	public ArrayList<DPAVehicleActiveData> getDPAVehicleActive(Integer year,Integer month,UserSessionData userSession) throws Exception {
		ArrayList<DPAVehicleActiveData> res=new ArrayList<DPAVehicleActiveData>();
		try {
			return VehicleReportUtil.getHome().create().getDPAVehicleActive(year,month,userSession);
		} catch (Exception e) {
			Object[] p = {year,userSession};
			handler.handleException("getDPAVehicleActive",e,userSession,p);
		}
    	return res;
	}
	
	/**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,dpachecklist,drivervehicleedit,drivercreateevent,vehiclechecklist,vehicleview" 
     */
    public ArrayList<ChecklistReportHead> findCheckReportHead(Integer startDay, Integer startMonth , Integer startYear, Integer endDay,Integer endMonth,Integer endYear,Integer corporation,String type,UserSessionData userSessionData) throws Exception {
		ArrayList<ChecklistReportHead> res=new ArrayList<ChecklistReportHead>();
		try {
			return VehicleReportUtil.getHome().create().findCheckReportHead(startDay, startMonth , startYear, endDay,endMonth,endYear,corporation,type,userSessionData);
		} catch (Exception e) {
			Object[] p = {};
			handler.handleException("findCheckReportHead",e,userSessionData,p);
		}
    	return res;
	}
	
    /**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehiclecreate,drivercreatevehicle,vehiclechecklist,dpachecklist,systemaccess"
	 * @param data
	 * @throws Exception
	 */	
	public String reportChecklistPDF(String fileName,CheckListData  checkList, VehicleCheckData data,  UserSessionData userSys,Text messages) throws Exception{
		try{
			 VehicleReport vehicleReport=VehicleReportUtil.getHome().create();	
			 boolean internos = data.getName().equals("84");
		      boolean pesados = (data.getName().equals("80")) || (data.getName().equals("123")) || (internos);
			 boolean seguimiento = data.getName().equals("79");
			
			 boolean negativePoints=false;
			 boolean war=false;
			 Iterator<PointData> points = checkList.getSortPoints();
			 while(points.hasNext()) {
				PointData point = (PointData)points.next();
				Iterator items = point.getSortItems();
				if(items.hasNext()) {
					ItemData item = (ItemData)items.next();
					int value = -1;
					String pointComment = null;
					if(data != null) {
						String checkValue = data.getValueFromItem(item.getId()).trim();
						value = (checkValue == null || !checkValue.equals("2")) ? -1 : 2;
					}//
					if(value==2){
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
							negativePoints=true;
							if(tag!=null && Integer.valueOf(tag)==0){
								war=true;
							}
						//}
						if(seguimiento){
							negativePoints=true;//Si es Seguimiento en ruta, no hay parada y va siempre la notificacion.
							war=false;
						}
			
					}
				}
			 }
		
			 HashMap<String,Object> parameters = new HashMap<String,Object>();
		     parameters.put("header", AlertConstants.TEMP_DIRECTORY_DPA+"images/header.jpg");
		     parameters.put("footer", AlertConstants.TEMP_DIRECTORY_DPA+"images/footer.jpg");
		     parameters.put("logo_cepa", AlertConstants.TEMP_DIRECTORY_DPA+"images/logo_cepa.png");
		     
		     parameters.put("date",""/*"Envío: "+ communication.administration.corporation.util.Util.formatDateHour(Calendar.getInstance().getTime())*/);
		     parameters.put("header1", AlertConstants.TEMP_DIRECTORY_DPA+"images/check_header1.jpg");
		     parameters.put("header2", AlertConstants.TEMP_DIRECTORY_DPA+"images/check_header2.jpg");
		     parameters.put("header3", AlertConstants.TEMP_DIRECTORY_DPA+"images/check_header3.jpg");
		     parameters.put("header4", AlertConstants.TEMP_DIRECTORY_DPA+"images/check_header4.jpg");
		     parameters.put("checkdata", "Fecha: "+communication.administration.corporation.util.Util.formatDateHour(data.getDate()));
		     Iterator<PointData> it = checkList.getSortPoints();
		     String[][] values = null;
		     String transportista=".";
		     Iterator iter= data.getVehicle().getDivOrgsIterator();
			 
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
						
						transportista=item.getName();
					}
				}
			 }
				
		     if(pesados) {
		         String title = "Checklist Vehículos Pesados";
		         if (internos)
		           title = "Check List Interno";
		         if (data.getName().equals("123")) {
		           title = "Checklist Preventivo";
		         }
		     	 parameters.put("title", title);
		         parameters.put("data", AlertConstants.TEMP_DIRECTORY_DPA+"images/datosCheckListForestales"+(CorporationData.FORESTALORIENTAL==userSys.getCorporationId()?"3":"2")+".png");
		         parameters.put("table", AlertConstants.TEMP_DIRECTORY_DPA+"images/tablaCheckListT1.jpg");
		         parameters.put("plate","Placa: "+data.getVehicle().getMatricula());
		         if(CorporationData.FORESTALORIENTAL==userSys.getCorporationId()){
		        	 parameters.put("abs","ABS camión:"+(data.getVehicle().getAbs()?"SI":"NO"));
		        	 parameters.put("abs1","ABS semi. 1:"+(data.getVehicle().getAirBag()?"SI":"NO"));
		        	 parameters.put("abs2","ABS semi. 2:"+(data.getVehicle().getAireAcondicionado()?"SI":"NO"));
		        	 parameters.put("susp","Susp. Neum.:"+(data.getVehicle().getSuspension()?"SI":"NO"));
		         }
		         
		         Iterator<ItemData> items = it.next().getSortItems();
		         for(int count=0; count< 6 ; ) {
		         	if(!items.hasNext()){
		         		 items = it.next().getSortItems();
		         	}
				        ItemData item = items.next();
				        String text =  messages.getText(item.getText()) + ": " + data.getValueFromItem(item);
				        if(item.getText().equals("forestales.check2.h1") || item.getText().equals("forestales.checkinternal2.h1")){
				        	//Conductor
				        	count++;
				        	parameters.put("driver", text);
				        }else if(item.getText().equals("forestales.check2.h3") || item.getText().equals("forestales.checkinternal2.h3")){
				        	//Transportista
				        	parameters.put("transporter", text);
				        	count++;
				        	 

				        }else if(item.getText().equals("forestales.check2.h7")|| item.getText().equals("forestales.checkinternal2.h7") ){
				        	//Primer Semi Remolque
				        	parameters.put("plate1", text);
					        count++;
				        }else if(item.getText().equals("forestales.check2.h8") || item.getText().equals("forestales.checkinternal2.h8")){
				        	//Segundo Semi Remolque
				        	parameters.put("plate2", text);
				        	count++;
				        }else if(item.getText().equals("forestales.check2.h9") || item.getText().equals("forestales.checkinternal2.h9") ){
				        	//Camión/Unidad
				        	parameters.put("plate3",text);
				        	count++;
				        }else if(item.getText().equals("forestales.check2.h10") || item.getText().equals("forestales.checkinternal2.h10") ){
				        	//Zorra
				        	parameters.put("plate4", text);
				        	count++;
				        }
			        }
		         
		         Collection<CheckListImageData> images = data.getPointImageSet();
		         String[] imgSources = new String[images.size()];
		         String[] imgTexts = new String[images.size()];
		         String[] imgPointTexts = new String[images.size()];
			     if(images.size() > 0) {
			        Iterator<CheckListImageData> imgIt = images.iterator();
			        int index = 0;
			        while(imgIt.hasNext()) {
			        	CheckListImageData imageData = imgIt.next();
			        	imgSources[index] = imageData.getFileName();
			        	PointData point = checkList.getPoint(imageData.getPointId());
		        		imgTexts[index] = point.getSortItems().next().getText();
		        		imgPointTexts[index]=data.getValueFromCommentPoint(point.getId());
			        	index++;
			        }
			        PictureDPADataSource sub = new PictureDPADataSource(imgSources,imgTexts,imgPointTexts,messages);
			        sub.setCorporationId(userSys.getCorporationId());
			        //JasperCompileManager.compileReportToFile(AlertConstants.getTEMP(userSys.getCorporationId())+"jasper/PictureSubreportPesados.jrxml", AlertConstants.getTEMP(userSys.getCorporationId())+"jasper/PictureSubreport.jasper");
			        FileInputStream input = new FileInputStream(new File(AlertConstants.getTEMP(userSys.getCorporationId())+"jasper/PictureSubreport.jasper"));
			        JasperReport subReport = (JasperReport)JRLoader.loadObject(input);
			        parameters.put("sub", sub);
			        parameters.put("subreport", subReport);
		         }else {
			        PictureDPADataSource sub = new PictureDPADataSource(imgSources,imgTexts,imgPointTexts,messages);
			        sub.setCorporationId(userSys.getCorporationId());
			      //JasperCompileManager.compileReportToFile(AlertConstants.TEMP_DIRECTORY_GENERAL+"jasper/PictureSubreport2.jrxml", AlertConstants.TEMP_DIRECTORY_GENERAL+"jasper/PictureSubreport2.jasper");
			        FileInputStream input = new FileInputStream(new File(AlertConstants.TEMP_DIRECTORY_GENERAL+"jasper/PictureSubreport2.jasper"));
			        JasperReport subReport = (JasperReport)JRLoader.loadObject(input);
			        parameters.put("sub", sub);
			        parameters.put("subreport", subReport);
			     }
		     
		     }else if(seguimiento){
		     		parameters.put("title", "Seguimiento en ruta.");
		     	 	parameters.put("data", AlertConstants.TEMP_DIRECTORY_DPA+"images/datosCheckListForestales.jpg");
			            parameters.put("table", AlertConstants.TEMP_DIRECTORY_DPA+"images/tablaCheckListT1.jpg");
			            parameters.put("plate","Placa: "+data.getVehicle().getMatricula());
			            
			            parameters.put("driver", "");
			            parameters.put("transporter", "Transportista: "+transportista);
		     	 
		     }
		     
		     parameters.put("aprouved", "S/F");
		     parameters.put("reprouved", "C/F");
		     parameters.put("plates", "PLACAS");
		     parameters.put("obs", messages.getText("generic.messages.observations"));
		     
		     String result = messages.getText("generic.activity.result") +": ";
		     result += (data.getResult().intValue() == 1) ? "SIN FALTA/FALLAS" : "CON FALTA/FALLAS"; 
		     parameters.put("pictures", messages.getText("generic.checklist.pictures"));
		     parameters.put("finaldata", "Conclusiones");
		     parameters.put("result", result);
		     parameters.put("inspected", "Técnico/Evaluador"+": "+data.getInspectedby());
		     parameters.put("obsText", messages.getText("generic.messages.observations"));
		     parameters.put("stop", "P");
		     parameters.put("obsValue", (data.getObs() == null || data.getObs().contains("dpa")) ? "--------" :  data.getObs());
		     String city= (data.getSerie() != null) ?data.getSerie():"...";
				
			if(war){
				parameters.put("warning", AlertConstants.TEMP_DIRECTORY_DPA+"images/warning.jpg");
			 
				parameters.put("warningB",true);
			}else{
				parameters.put("warningB",false);
			}
			
			CheckListUPMDataSource dataSource = new CheckListUPMDataSource(true, data, checkList, messages);
			parameters.put("place","Lugar: "+city);
			if(pesados){
				parameters.put("city","");
			}else{
				parameters.put("city","");
			}
			
			parameters.put("redflag", AlertConstants.TEMP_DIRECTORY_DPA+"images/redFlag.png");
			
		     String type = pesados? "Pesados":"Seguimiento";
		     String corp="DEF";
		   //JasperCompileManager.compileReportToFile(AlertConstants.TEMP_DIRECTORY_DPA+"jasper/CheckList"+corp+type+".jrxml", AlertConstants.TEMP_DIRECTORY_DPA+"jasper/CheckList"+corp+type+".jasper");
		     FileInputStream input2 = new FileInputStream(new File(AlertConstants.TEMP_DIRECTORY_DPA+"jasper/CheckList"+corp+type+".jasper"));
	
		     JasperReport jasperReport = (JasperReport)JRLoader.loadObject(input2);
		     VehicleData vehicleData=vehicleReport.findVehicleByPrimaryKey(data.getVehicle().getId(), userSys);
		     
		     String file = AlertConstants.TEMP_DIRECTORY_DPA+"out/"+fileName;
		     
		     OutputStream out = new FileOutputStream(new File(file));
		     JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		     
		     JRPdfExporter exporter = new JRPdfExporter();
		     exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		     exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
		     exporter.exportReport();       
		     out.flush();
		     out.close();
		     
		     return file;
		     
		}catch(Exception ex){
			ex.printStackTrace();
			Object[] p = new Object[2];
			p[0] = data;
			p[1] = checkList;
			handler.handleException("reportChecklistPDF", ex, userSys,p);
		}
		
		return null;
	}
	
	
	 /**
		 * @ejb.interface-method
	     * @ejb.permission role-name = "administrator,vehiclecreate,drivercreatevehicle,vehiclechecklist,dpachecklist"
		 * @param data
		 * @throws Exception
		 */	
		public String reportChecklistPDFACU(String fileName,CheckListData  checkList, VehicleCheckData data,  UserSessionData userSys,Text messages) throws Exception{
			try{
				 VehicleReport vehicleReport=VehicleReportUtil.getHome().create();	
				 
				 boolean negativePoints=false;
				 boolean war=false;
				
			
				 HashMap<String,Object> parameters = new HashMap<String,Object>();
			     parameters.put("header", AlertConstants.TEMP_DIRECTORY_DPA+"images/header.jpg");
			     parameters.put("footer", AlertConstants.TEMP_DIRECTORY_DPA+"images/footer.jpg");
			     parameters.put("logo_cepa", AlertConstants.TEMP_DIRECTORY_DPA+"images/logo_cepa.png");
			     
			     parameters.put("date",""/*"Envío: "+ communication.administration.corporation.util.Util.formatDateHour(Calendar.getInstance().getTime())*/);
			     parameters.put("header1", AlertConstants.TEMP_DIRECTORY_DPA+"images/check_header1.jpg");
			     parameters.put("header2", AlertConstants.TEMP_DIRECTORY_DPA+"images/check_header2.jpg");
			     parameters.put("header3", AlertConstants.TEMP_DIRECTORY_DPA+"images/check_header3.jpg");
			     parameters.put("header4", AlertConstants.TEMP_DIRECTORY_DPA+"images/check_header4.jpg");
			     parameters.put("checkdata", "Fecha: "+communication.administration.corporation.util.Util.formatDateHour(data.getDate()));
			     Iterator<PointData> it = checkList.getSortPoints();
			     String[][] values = null;
			     String transportista=".";
			     Iterator iter= data.getVehicle().getDivOrgsIterator();
				 
				
		 	 parameters.put("title", "Checklist Vehículos ACU");
	         parameters.put("data", AlertConstants.TEMP_DIRECTORY_DPA+"images/datosCheckListForestales2.png");
	         parameters.put("table", AlertConstants.TEMP_DIRECTORY_DPA+"images/tablaCheckListT1.jpg");
	         parameters.put("plate","Placa: "+data.getVehicle().getMatricula());
	         
	         Iterator<ItemData> items = it.next().getSortItems();
	         for(int count=0; count<3; ) {
		         	if(!items.hasNext()){
		         		 items = it.next().getSortItems();
		         	}
			        ItemData item = items.next();
			        String text =  messages.getText(item.getText()) + ": " + data.getValueFromItem(item);
			        if(item.getText().equals("acu.check2.h5")){
			        	//Conductor
			        	count++;
			        	parameters.put("driver", text);
			        }else if(item.getText().equals("acu.check2.h6")){
			        	//Transportista
			        	parameters.put("licence", text);
			        	count++;
			        }else if(item.getText().equals("acu.check2.h4") ){
			        	switch (Integer.valueOf( data.getValueFromItem(item)).intValue()) {
						case 1:
							text="Servicio: Serv. Auxilio";
							break;
						case 2:
							text="Servicio: Esc. Conducción";
							break;
						case 3:
							text="Servicio: Otros";
							break;

						default:
							text="Servicio: ----";
							break;
						}
			        	parameters.put("service", text);
				        count++;
			        } 
		        }
			     
	        
				
			     parameters.put("aprouved", "S/O");
			     parameters.put("reprouved", "R/A");
			     parameters.put("plates", "PLACAS");
			     parameters.put("obs", messages.getText("generic.messages.observations"));
			     
			     parameters.put("pictures", messages.getText("generic.checklist.pictures"));
			     parameters.put("finaldata", "Conclusiones");
			     parameters.put("inspected", "Técnico/Evaluador"+": "+data.getInspectedby());
			     parameters.put("obsText", messages.getText("generic.messages.observations"));
			     parameters.put("stop", "P");
			     parameters.put("obsValue", (data.getObs() == null || data.getObs().contains("dpa")) ? "--------" :  data.getObs());
			     String city= (data.getSerie() != null) ?data.getSerie():"...";
			   
			     switch (Integer.valueOf(data.getSerie()).intValue()) {
					case 1:
						city="Estación Colonia y Yì";
						break;
					case 2:
						city="Local Ayala";
						break;
					case 3:
						city="Sucursal Maldonado";
						break;
					case 4:
						city="Ciudad de la Costa";
						break;
					default:
						city="----";
						break;
				}	
			     parameters.put("place","Sede: "+city);
						
				
			     CheckListACUDataSource dataSource = new CheckListACUDataSource( data, checkList, messages);
				
					
			   //JasperCompileManager.compileReportToFile(AlertConstants.TEMP_DIRECTORY_DPA+"jasper/CheckListACU.jrxml", AlertConstants.TEMP_DIRECTORY_DPA+"jasper/CheckListACU.jasper");
			     FileInputStream input2 = new FileInputStream(new File(AlertConstants.TEMP_DIRECTORY_DPA+"jasper/CheckListACU.jasper"));
		
			     JasperReport jasperReport = (JasperReport)JRLoader.loadObject(input2);
			     VehicleData vehicleData=vehicleReport.findVehicleByPrimaryKey(data.getVehicle().getId(), userSys);
			     
			     String file = AlertConstants.TEMP_DIRECTORY_DPA+"out/"+fileName;
			     
			     OutputStream out = new FileOutputStream(new File(file));
			     JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			     
			     JRPdfExporter exporter = new JRPdfExporter();
			     exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			     exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
			     exporter.exportReport();       
			     out.flush();
			     out.close();
			     
			     return file;
			     
			}catch(Exception ex){
				Object[] p = new Object[2];
				p[0] = data;
				p[1] = checkList;
				handler.handleException("reportChecklistPDF", ex, userSys,p);
			}
			
			return null;
		}
		
		
		 /**
		 * @ejb.interface-method
	     * @ejb.permission role-name = "administrator,vehiclecreate,drivercreatevehicle,vehiclechecklist,dpachecklist"
		 * @param data
		 * @throws Exception
		 */	
		public String reportChecklistPDFSyngentaBR(String fileName,CheckListData  checkList, VehicleCheckData data,  UserSessionData userSys,Text messages) throws Exception{
			try{
				 VehicleReport vehicleReport=VehicleReportUtil.getHome().create();	
				 
				 boolean negativePoints=false;
				 boolean war=false;
				
			
				 HashMap<String,Object> parameters = new HashMap<String,Object>();
			     parameters.put("header", AlertConstants.TEMP_DIRECTORY_DPA+"images/header.jpg");
			     parameters.put("footer", AlertConstants.TEMP_DIRECTORY_DPA+"images/footer.jpg");
			     parameters.put("logo_cepa", AlertConstants.TEMP_DIRECTORY_DPA+"images/logo_cepa.png");
			     
			     parameters.put("date",""/*"Envío: "+ communication.administration.corporation.util.Util.formatDateHour(Calendar.getInstance().getTime())*/);
			     parameters.put("header1", AlertConstants.TEMP_DIRECTORY_DPA+"images/check_header1.jpg");
			     parameters.put("header2", AlertConstants.TEMP_DIRECTORY_DPA+"images/check_header2.jpg");
			     parameters.put("header3", AlertConstants.TEMP_DIRECTORY_DPA+"images/check_header3.jpg");
			     parameters.put("header4", AlertConstants.TEMP_DIRECTORY_GENERAL+"images/check_header4_4.jpg");
			     parameters.put("checkdata", "Fecha: "+communication.administration.corporation.util.Util.formatDateHour(data.getDate()));
			     parameters.put("pictures", "Fotos");
			     Iterator<PointData> it = checkList.getSortPoints();
			     String[][] values = null;
			     
			 	 parameters.put("title", messages.getText("checklist.syngentabr.title"));
		         parameters.put("data", AlertConstants.TEMP_DIRECTORY_GENERAL+"images/datosCheckListSyngenta.jpg");
		         parameters.put("table", AlertConstants.TEMP_DIRECTORY_DPA+"images/tablaCheckListT1.jpg");
		         parameters.put("plate","Placa: "+data.getVehicle().getMatricula());
		         Iterator<ItemData> items = it.next().getSortItems();
		         for(int count=0; count<17;) {
			         	if(!items.hasNext()){
			         		 items = it.next().getSortItems();
			         	}
				        ItemData item = items.next();
				        String text =  messages.getText(item.getText()) + ": " + data.getValueFromItem(item);
				        if(item.getText().equals("syngentabr95.checklist.i1")){
				        	count++;
				        	parameters.put("item1", text);
				        }else if(item.getText().equals("syngentabr95.checklist.i2")){
				        	parameters.put("item2", text);
				        	count++;
				        }else if(item.getText().equals("syngentabr95.checklist.i3")){
					    	parameters.put("item3", text);
				        	count++;
				        }else if(item.getText().equals("syngentabr95.checklist.i4")){
					    	parameters.put("item4", text);
				        	count++;
				        }else if(item.getText().equals("syngentabr95.checklist.i5")){
				        	//'Marca/modelo tractor - camión'
				        	parameters.put("item5", text);
				        	count++;
				        }else if(item.getText().equals("syngentabr95.checklist.i6")){
				        	//'Marca/modelo tractor - camión'
				        	parameters.put("item6", text);
				        	count++;
				        }else if(item.getText().equals("syngentabr95.checklist.i7")){
				        	//'Marca/modelo tractor - camión'
				        	parameters.put("item7", text);
				        	count++;
				        }else if(item.getText().equals("syngentabr95.checklist.i8")){
				        	//'Marca/modelo tractor - camión'
				        	parameters.put("item8", text);
				        	count++;
				        }else if(item.getText().equals("syngentabr95.checklist.i9")){
				        	parameters.put("item9", text);
				        	count++;
				        }else if(item.getText().equals("syngentabr95.checklist.i10")){
				        	parameters.put("item10", text);
				        	count++;
				        }else if(item.getText().equals("syngentabr95.checklist.i11")){
				        	parameters.put("item11", text);
				        	count++;
				        }else if(item.getText().equals("syngentabr95.checklist.i12")){
				        	parameters.put("item12", text);
				        	count++;
				        }else if(item.getText().equals("syngentabr95.checklist.i13")){
				        	parameters.put("item13", text);
				        	count++;
				        }else if(item.getText().equals("syngentabr95.checklist.i14")){
				        	parameters.put("item14", text);
				        	count++;
				        }else if(item.getText().equals("syngentabr95.checklist.i15")){
				        	parameters.put("item15", text);
				        	count++;
				        }else if(item.getText().equals("syngentabr95.checklist.i16")){
				        	parameters.put("item16", text);
				        	count++;
				        }else if(item.getText().equals("syngentabr95.checklist.i17")){
				        	parameters.put("item17", text);
				        	count++;
				        }
			     }
	      
		         parameters.put("warning",  AlertConstants.TEMP_DIRECTORY_DPA+"images/redFlag.png");
		         parameters.put("time", "P");
			     parameters.put("reprouved", "R");
			     parameters.put("aprouved", "A");
			     parameters.put("plates", "PLACAS");
			     parameters.put("obs", messages.getText("generic.messages.observations"));
			     
			     parameters.put("pictures", "Imagens");
			     parameters.put("finaldata", "Conclusões");
			     parameters.put("inspected", "Técnico/Avaliador"+": "+data.getInspectedby());
			     parameters.put("obsText", "Observação");
			     parameters.put("stop", "P");
			     parameters.put("obsValue", (data.getObs() == null || data.getObs().contains("dpa")) ? "--------" :  data.getObs());
			     String inspector= (data.getSerie() != null) ?data.getSerie():"...";
			   
			     
			     Collection<CheckListImageData> images = data.getPointImageSet();
		         String[] imgSources = new String[images.size()];
		         String[] imgTexts = new String[images.size()];
		         String[] imgPointTexts = new String[images.size()];
			     if(images.size() > 0) {
			        Iterator<CheckListImageData> imgIt = images.iterator();
			        int index = 0;
			        while(imgIt.hasNext()) {
			        	CheckListImageData imageData = imgIt.next();
			        	imgSources[index] = imageData.getFileName();
			        	PointData point = checkList.getPoint(imageData.getPointId());
		        		imgTexts[index] = point.getSortItems().next().getText();
		        		imgPointTexts[index]=data.getValueFromCommentPoint(point.getId());
			        	index++;
			        }
			        PictureDPADataSource sub = new PictureDPADataSource(imgSources,imgTexts,imgPointTexts,messages);
			        sub.setCorporationId(userSys.getCorporationId());
			      //JasperCompileManager.compileReportToFile(AlertConstants.getTEMP(userSys.getCorporationId())+"jasper/PictureSubreport.jrxml", AlertConstants.getTEMP(userSys.getCorporationId())+"jasper/PictureSubreport.jasper");
			        FileInputStream input = new FileInputStream(new File(AlertConstants.getTEMP(userSys.getCorporationId())+"jasper/PictureSubreport.jasper"));
			        JasperReport subReport = (JasperReport)JRLoader.loadObject(input);
			        parameters.put("sub", sub);
			        parameters.put("subreport", subReport);
		         }else {
			        PictureDPADataSource sub = new PictureDPADataSource(imgSources,imgTexts,imgPointTexts,messages);
			        sub.setCorporationId(userSys.getCorporationId());
			      //JasperCompileManager.compileReportToFile(AlertConstants.TEMP_DIRECTORY_GENERAL+"jasper/PictureSubreport2.jrxml", AlertConstants.TEMP_DIRECTORY_GENERAL+"jasper/PictureSubreport2.jasper");
			        FileInputStream input = new FileInputStream(new File(AlertConstants.TEMP_DIRECTORY_GENERAL+"jasper/PictureSubreport2.jasper"));
			        JasperReport subReport = (JasperReport)JRLoader.loadObject(input);
			        parameters.put("sub", sub);
			        parameters.put("subreport", subReport);
			     }
			   
			     parameters.put("place","Email Condutor: "+inspector);
				
			     CheckListACUDataSource dataSource = new CheckListACUDataSource( data, checkList, messages);
					
			   //JasperCompileManager.compileReportToFile(AlertConstants.TEMP_DIRECTORY_GENERAL+"jasper/CheckListSyngentaBR.jrxml", AlertConstants.TEMP_DIRECTORY_GENERAL+"jasper/CheckListSyngentaBR.jasper");
			     FileInputStream input2 = new FileInputStream(new File(AlertConstants.TEMP_DIRECTORY_GENERAL+"jasper/CheckListSyngentaBR.jasper"));
		
			     JasperReport jasperReport = (JasperReport)JRLoader.loadObject(input2);
			     VehicleData vehicleData=vehicleReport.findVehicleByPrimaryKey(data.getVehicle().getId(), userSys);
			     
			     String file = AlertConstants.TEMP_DIRECTORY_GENERAL+"out/"+fileName;
			     
			     OutputStream out = new FileOutputStream(new File(file));
			     JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			     
			     JRPdfExporter exporter = new JRPdfExporter();
			     exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			     exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
			     exporter.exportReport();       
			     out.flush();
			     out.close();
			     
			     return file;
			     
			}catch(Exception ex){
				ex.printStackTrace();
				Object[] p = new Object[2];
				p[0] = data;
				p[1] = checkList;
				handler.handleException("reportChecklistPDFSyngentaBR", ex, userSys,p);
			}
			
			return null;
		}
		
		 /**
		 * @ejb.interface-method
	     * @ejb.permission role-name = "administrator,vehiclecreate,drivercreatevehicle,vehiclechecklist,dpachecklist"
		 * @param data
		 * @throws Exception
		 */	
		public String reportChecklistPDFMP(String fileName,CheckListData  checkList, VehicleCheckData data,  UserSessionData userSys,Text messages) throws Exception{
			try{
				 VehicleReport vehicleReport=VehicleReportUtil.getHome().create();	
				 
				 boolean negativePoints=false;
				 boolean war=false;
				
			
				 HashMap<String,Object> parameters = new HashMap<String,Object>();
			     parameters.put("header", AlertConstants.TEMP_DIRECTORY_DPA+"images/header.jpg");
			     parameters.put("footer", AlertConstants.TEMP_DIRECTORY_DPA+"images/footer.jpg");
			     parameters.put("logo_cepa", AlertConstants.TEMP_DIRECTORY_DPA+"images/logo_cepa.png");
			     
			     parameters.put("date",""/*"Envío: "+ communication.administration.corporation.util.Util.formatDateHour(Calendar.getInstance().getTime())*/);
			     parameters.put("header1", AlertConstants.TEMP_DIRECTORY_DPA+"images/check_header1.jpg");
			     parameters.put("header2", AlertConstants.TEMP_DIRECTORY_DPA+"images/check_header2.jpg");
			     parameters.put("header3", AlertConstants.TEMP_DIRECTORY_DPA+"images/check_header3.jpg");
			     parameters.put("header4", AlertConstants.TEMP_DIRECTORY_GENERAL+"images/check_header4_4.jpg");
			     parameters.put("checkdata", "Fecha: "+communication.administration.corporation.util.Util.formatDateHour(data.getDate()));
			     parameters.put("pictures", "Fotos");
			     Iterator<PointData> it = checkList.getSortPoints();
			     String[][] values = null;
			     String transportista=".";
			     Iterator iter= data.getVehicle().getDivOrgsIterator();
				
			 	 parameters.put("title", messages.getText("checklist.mp.title"));
		         parameters.put("data", AlertConstants.TEMP_DIRECTORY_GENERAL+"images/"+(checkList.getId().equals(CheckListData.MDP_CHECKLIS_MERCANCIASPELIGROSAS)?"datosCheckListMP.png":"datosCheckListMP2.png"));
		         parameters.put("table", AlertConstants.TEMP_DIRECTORY_DPA+"images/tablaCheckListT1.jpg");
		         parameters.put("plate","Placa: "+data.getVehicle().getMatricula());
		         
		         Iterator<ItemData> items = it.next().getSortItems();
		         for(int count=0; count<(checkList.getId().equals(91)?14:12); ) {
			         	if(!items.hasNext()){
			         		 items = it.next().getSortItems();
			         	}
				        ItemData item = items.next();
				        String text =  messages.getText(item.getText()) + ": " + data.getValueFromItem(item);
				        if(item.getText().equals("mdp91.checklist.i1")||item.getText().equals("mdp92.checklist.i1")){
				        	//Conductor
				        	count++;
				        	parameters.put("driver", text);
				        }else if(item.getText().equals("mdp91.checklist.i2")||item.getText().equals("mdp92.checklist.i2")){
				        	//Licencia
				        	parameters.put("licence", text);
				        	count++;
				        }else if(item.getText().equals("mdp91.checklist.i3")||item.getText().equals("mdp92.checklist.i3")){
				        	//Transportista
				        	parameters.put("service", text);
				        	count++;
				        } else if(item.getText().equals("mdp91.checklist.i4")||item.getText().equals("mdp92.checklist.i4")){
				        	//Empresa
				        	parameters.put("empresa", text);
				        	count++;
				        }  else if(item.getText().equals("mdp91.checklist.i5")||item.getText().equals("mdp92.checklist.i5")){
				        	//'Marca/modelo tractor - camión'
				        	parameters.put("mmtractor", text);
				        	count++;
				        } else if(item.getText().equals("mdp91.checklist.i6")||item.getText().equals("mdp92.checklist.i6")){
				        	//'Marca/modelo tractor - camión'
				        	parameters.put("atractor", text);
				        	count++;
				        }  else if(item.getText().equals("mdp91.checklist.i7")||item.getText().equals("mdp92.checklist.i7")){
				        	//'Marca/modelo tractor - camión'
				        	parameters.put("mmsemi", text);
				        	count++;
				        } else if(item.getText().equals("mdp91.checklist.i8")||item.getText().equals("mdp92.checklist.i8")){
				        	//'Marca/modelo tractor - camión'
				        	parameters.put("asemi", text);
				        	count++;
				        } else if(item.getText().equals("mdp91.checklist.i11")||item.getText().equals("mdp92.checklist.i11")){
				        	parameters.put("psemiremolque", text);
				        	count++;
				        } else if(item.getText().equals("mdp91.checklist.i12")||item.getText().equals("mdp92.checklist.i12")){
				        	parameters.put("ssemiremolque", text);
				        	count++;
				        } else if(item.getText().equals("mdp91.checklist.i13")||item.getText().equals("mdp92.checklist.i13")){
				        	parameters.put("cancap", text);
				        	count++;
				        } else if(item.getText().equals("mdp91.checklist.i14")||item.getText().equals("mdp92.checklist.i14") ){
				        	parameters.put("cpetrobras", text);
				        	count++;
				        } else if(item.getText().equals("mdp91.checklist.i15") ){
				        	parameters.put("zorra", text);
				        	count++;
				        } else if(item.getText().equals("mdp91.checklist.i16") ){
				        	parameters.put("kilometros", text);
				        	count++;
				        } 
			     }
			     
	      
		         parameters.put("warning",  AlertConstants.TEMP_DIRECTORY_DPA+"images/redFlag.png");
		         parameters.put("time", "P");
			     parameters.put("reprouved", "R/A");
			     parameters.put("aprouved", "S/O");
			     parameters.put("plates", "PLACAS");
			     parameters.put("obs", messages.getText("generic.messages.observations"));
			     
			     parameters.put("pictures", "Fotos");
			     parameters.put("finaldata", "Conclusiones");
			     parameters.put("inspected", "Técnico/Evaluador"+": "+data.getInspectedby());
			     parameters.put("obsText", "Observaciones");
			     parameters.put("stop", "P");
			     parameters.put("obsValue", (data.getObs() == null || data.getObs().contains("dpa")) ? "--------" :  data.getObs());
			     String inspector= (data.getSerie() != null) ?data.getSerie():"...";
			   
			     
			     Collection<CheckListImageData> images = data.getPointImageSet();
		         String[] imgSources = new String[images.size()];
		         String[] imgTexts = new String[images.size()];
		         String[] imgPointTexts = new String[images.size()];
			     if(images.size() > 0) {
			        Iterator<CheckListImageData> imgIt = images.iterator();
			        int index = 0;
			        while(imgIt.hasNext()) {
			        	CheckListImageData imageData = imgIt.next();
			        	imgSources[index] = imageData.getFileName();
			        	PointData point = checkList.getPoint(imageData.getPointId());
		        		imgTexts[index] = point.getSortItems().next().getText();
		        		imgPointTexts[index]=data.getValueFromCommentPoint(point.getId());
			        	index++;
			        }
			        PictureDPADataSource sub = new PictureDPADataSource(imgSources,imgTexts,imgPointTexts,messages);
			        sub.setCorporationId(userSys.getCorporationId());
			      //JasperCompileManager.compileReportToFile(AlertConstants.getTEMP(userSys.getCorporationId())+"jasper/PictureSubreport.jrxml", AlertConstants.getTEMP(userSys.getCorporationId())+"jasper/PictureSubreport.jasper");
			        FileInputStream input = new FileInputStream(new File(AlertConstants.getTEMP(userSys.getCorporationId())+"jasper/PictureSubreport.jasper"));
			        JasperReport subReport = (JasperReport)JRLoader.loadObject(input);
			        parameters.put("sub", sub);
			        parameters.put("subreport", subReport);
		         }else {
			        PictureDPADataSource sub = new PictureDPADataSource(imgSources,imgTexts,imgPointTexts,messages);
			        sub.setCorporationId(userSys.getCorporationId());
			      //JasperCompileManager.compileReportToFile(AlertConstants.TEMP_DIRECTORY_GENERAL+"jasper/PictureSubreport2.jrxml", AlertConstants.TEMP_DIRECTORY_GENERAL+"jasper/PictureSubreport2.jasper");
			        FileInputStream input = new FileInputStream(new File(AlertConstants.TEMP_DIRECTORY_GENERAL+"jasper/PictureSubreport2.jasper"));
			        JasperReport subReport = (JasperReport)JRLoader.loadObject(input);
			        parameters.put("sub", sub);
			        parameters.put("subreport", subReport);
			     }
			   
			     parameters.put("place","Lugar: "+inspector);
				
			     CheckListACUDataSource dataSource = new CheckListACUDataSource( data, checkList, messages);
					
			   //JasperCompileManager.compileReportToFile(AlertConstants.TEMP_DIRECTORY_GENERAL+"jasper/CheckListMP"+(checkList.getId().equals(92)?"2":"")+".jrxml", AlertConstants.TEMP_DIRECTORY_GENERAL+"jasper/CheckListMP.jasper");
			     FileInputStream input2 = new FileInputStream(new File(AlertConstants.TEMP_DIRECTORY_GENERAL+"jasper/CheckListMP.jasper"));
		
			     JasperReport jasperReport = (JasperReport)JRLoader.loadObject(input2);
			     VehicleData vehicleData=vehicleReport.findVehicleByPrimaryKey(data.getVehicle().getId(), userSys);
			     
			     String file = AlertConstants.TEMP_DIRECTORY_GENERAL+"out/"+fileName;
			     
			     OutputStream out = new FileOutputStream(new File(file));
			     JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			     
			     JRPdfExporter exporter = new JRPdfExporter();
			     exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			     exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
			     exporter.exportReport();       
			     out.flush();
			     out.close();
			     
			     return file;
			     
			}catch(Exception ex){
				Object[] p = new Object[2];
				p[0] = data;
				p[1] = checkList;
				handler.handleException("reportChecklistPDFMP", ex, userSys,p);
			}
			
			return null;
		}

		/**
		 * @ejb.interface-method
		 * @ejb.permission role-name = "fleetinsertdata,administrator"
		 */
		public HashMap getActiveVehicleCount(UserSessionData userSessionData) throws Exception {
			HashMap values = new HashMap();
			try {
				values = VehicleReportUtil.getHome().create().getActiveVehicleCount(userSessionData);
			} catch (Exception e) {
				handler.handleException("getActiveVehicleCount", e, userSessionData,new Object[0]);				
			}
			return values;
		}

		/**
		 * @ejb.interface-method
		 * @ejb.permission role-name = "fleetinsertdata,administrator"
		 */
		public HashMap getActiveVehicleCount(Integer fleet, Integer month, Integer year, UserSessionData userSessionData) throws Exception {
			HashMap values = new HashMap();
			try {
				values = VehicleReportUtil.getHome().create().getActiveVehicleCount(fleet, month, year, userSessionData);
			} catch (Exception e) {
				handler.handleException("getActiveVehicleCount", e, userSessionData,new Object[0]);				
			}
			return values;
		}
}

