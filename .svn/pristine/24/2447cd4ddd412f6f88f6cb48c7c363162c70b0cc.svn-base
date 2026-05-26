package business.external;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.SessionBean;

import org.hibernate.Query;

import persistence.driver.DriverReportUtil;
import persistence.driver.tables.Driver;
import persistence.event.EventABM;
import persistence.event.EventABMUtil;
import persistence.external.ExternalUtil;
import persistence.hibernate.HibernateUtil;
import persistence.util.Util;
import persistence.util.UtilityQuery;
import persistence.vehicle.VehicleReportUtil;
import business.administration.corporation.CorporationABMFacadeUtil;
import business.util.ExceptionHandler;
import business.util.MemoryLog;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.CountryData;
import communication.administration.user.UserSessionData;
import communication.alert.AlertConstants;
import communication.driver.tables.DriverData;
import communication.driver.tables.DriverMainAttributes;
import communication.event.tables.EventDataType;
import communication.event.tables.FineDataType;
import communication.exception.CreateBeanException;
import communication.exception.DeslogedException;
import communication.exception.InvalidDataException;
import communication.external.instructors.ChecklistRestData;
import communication.external.instructors.ChecklistResultRestData;
import communication.external.instructors.CorporationRestData;
import communication.language.Text;
import communication.security.SecurityConstants;
import communication.travel.tables.CheckPointData;
import communication.travel.tables.TravelDataType;
import communication.util.MailSender;

/**
 * @author fcarriquiry
 * 
 * @ejb.bean name = "ExternalFacade" jndi-name = "ejb/ExternalFacade" type =
 *           "Stateless" transaction-type = "Container"  
 * 
 * @ejb.ejb-ref
 * 			 ejb-name = "External"
 * 			 ref-name = "ejb/External"
 * 			 view-type ="remote"
 * 
 
 * 
 * @ejb.ejb-ref ejb-name = "VehicleReport" ref-name = "ejb/VehicleReport" view-type =
 *              "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/VehicleReport" ref-name = "VehicleReport"
 * 
 * @ejb.ejb-ref ejb-name = "DriverReport" ref-name = "ejb/DriverReport" view-type =
 *              "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/DriverReport" ref-name = "DriverReport"

 * @ejb.util generate="physical"
 */
public abstract class ExternalFacadeBean implements SessionBean {

    private ExceptionHandler handler = new ExceptionHandler(ExternalFacadeBean.class);
	
    /**
     * Create method
     * @ejb.permission role-name = "systemaccess"
     * @ejb.create-method
     */
	public void ejbCreate() throws CreateBeanException {
	}
	
	/**
	 * @ejb.permission role-name ="systemaccess"
	 * @ejb.interface-method
	 */
	public ArrayList<DriverData> test() throws Exception {
	
		try {
			 return ExternalUtil.getHome().create().test();
			
		} catch (Exception e) {
				e.printStackTrace();
		}
		 
		return new ArrayList<DriverData>();
	}
	

	 /**
	 * @ejb.permission role-name ="systemaccess"
	 * @ejb.interface-method
	 */
	public ArrayList<CorporationRestData> sync(Integer lastId, String activitiesIds, Date  lastUpdateDate, Text messages,UserSessionData userRequest) throws Exception {
		 
		try {
			 return ExternalUtil.getHome().create().sync(lastId,activitiesIds,lastUpdateDate,messages,userRequest);
		} catch (Exception e) {
				e.printStackTrace();
		}
		 
		return new ArrayList<CorporationRestData>();
	}
	
	 /**
		 * @ejb.permission role-name ="systemaccess"
		 * @ejb.interface-method
		 */
		public ChecklistRestData getChecklist(Integer id, Text messages, UserSessionData userRequest){
			try {
				 return ExternalUtil.getHome().create().getChecklist(id,messages,userRequest);
			} catch (Exception e) {
					e.printStackTrace();
			}
			 
			return new ChecklistRestData();
		}
		
		/**
		 * @ejb.permission role-name ="systemaccess"
		 * @ejb.interface-method
		 */
		public boolean saveResults(ArrayList<ChecklistResultRestData> results,Text messages,UserSessionData userRequest) throws Exception {
			return ExternalUtil.getHome().create().saveResults(results,messages,userRequest);
		}
	
}