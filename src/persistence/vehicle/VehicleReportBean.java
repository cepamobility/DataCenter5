
package persistence.vehicle;

import java.io.OutputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.jboss.logging.Logger;

import persistence.activity.tables.CheckList;
import persistence.administration.corporation.tables.Corporation;
import persistence.administration.corporation.tables.DivOrgItemLevel1;
import persistence.alert.tables.ContractorData;
import persistence.driver.tables.TelematicControl;
import persistence.fleet.tables.FleetDivision;
import persistence.fleet.tables.IndividualFleet;
import persistence.fleet.tables.Odometer;
import persistence.hibernate.HibernateUtil;
import persistence.log.LoggerFormatting;
import persistence.log.fleet.VehicleFleetLog;
import persistence.log.vehicle.VehicleLog;
import persistence.util.ExcelGenerator;
import persistence.util.ExceptionHandler;
import persistence.util.Util;
import persistence.util.UtilityQuery;
import persistence.vehicle.tables.Complaint;
import persistence.vehicle.tables.DPAGoal;
import persistence.vehicle.tables.DPAVehicleActive;
import persistence.vehicle.tables.InstructorActivity;
import persistence.vehicle.tables.Trailer;
import persistence.vehicle.tables.TrailerCheck;
import persistence.vehicle.tables.Vehicle;
import persistence.vehicle.tables.VehicleCheck;
import persistence.vehicle.tables.VehicleContract;
import persistence.vehicle.tables.VehicleMaintenance;
import persistence.vehicle.tables.VehiclePursuitRoute;
import business.util.MemoryLog;

import communication.activity.tables.CheckListData;
import communication.activity.tables.ItemData;
import communication.activity.tables.ItemValueStrData;
import communication.activity.tables.PointCommentData;
import communication.activity.tables.PointData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.util.DPACheckListXLSFilter;
import communication.administration.user.UserSessionData;
import communication.datatype.tables.VehicleContractDataType;
import communication.driver.report.DPATelematicControlFilter;
import communication.driver.tables.TelematicControlData;
import communication.exception.CommunicationProblemException;
import communication.exception.DCException;
import communication.exception.DeslogedException;
import communication.exception.InvalidDataException;
import communication.fleet.tables.OdometerData;
import communication.language.Text;
import communication.util.ListResult;
import communication.util.tables.ResumeItem;
import communication.vehicle.report.CheckListImageData;
import communication.vehicle.report.ChecklistReportHead;
import communication.vehicle.report.VehicleCheckReportDataType;
import communication.vehicle.report.VehicleCheckReportDetailedDataType;
import communication.vehicle.report.VehicleContractReportDataType;
import communication.vehicle.report.VehiclePursuitReportDataType;
import communication.vehicle.tables.CimsaCheckListData;
import communication.vehicle.tables.ComplaintData;
import communication.vehicle.tables.DPAGoalData;
import communication.vehicle.tables.DPAVehicleActiveData;
import communication.vehicle.tables.ForestalCheckListData;
import communication.vehicle.tables.ForestalResumeCheckListData;
import communication.vehicle.tables.InstructorActivityData;
import communication.vehicle.tables.SuctaMTOPData;
import communication.vehicle.tables.TrailerData;
import communication.vehicle.tables.TrailerMainData;
import communication.vehicle.tables.VehicleCheckData;
import communication.vehicle.tables.VehicleCheckReportGroupData;
import communication.vehicle.tables.VehicleCheckResumeData;
import communication.vehicle.tables.VehicleContractData;
import communication.vehicle.tables.VehicleData;
import communication.vehicle.tables.VehicleDataText;
import communication.vehicle.tables.VehicleMaintenanceData;
import communication.vehicle.tables.VehiclePursuitRouteData;

/**
 * @ejb.bean name="VehicleReport" display-name="Name for VehicleReport"
 *          description="Description for VehicleReport"
 *          jndi-name="ejb/VehicleReport"
 *          local-jndi-name="ejb/VehicleReportLocal"
 *          type="Stateless"
 *
 */

public abstract class VehicleReportBean implements SessionBean {

    private ExceptionHandler handler = new ExceptionHandler(VehicleReportBean.class);


    /**
     * Logger for this class
     */
    private Logger logger=Logger.getLogger(VehicleReportBean.class);
    private LoggerFormatting logFormatting=new LoggerFormatting();

    public static final int FIRST_NAME_DRIVER = 0;
    public static final int LAST_NAME_DRIVER = 1;
    public static final int COUNTRY = 4;
    public static final int CHASIS = 5;
    public static final int MATRICULA = 6;
    public static final int PREFIX = 7;

    /**
     * @ejb.create-method
     * @ejb.permission role-name = "administrator,vehiclesearch,vehicleview,vehicleedit,eventcreate,finecreate,driverviewowndata,drivercreateevent,drivercreatefine,drivervehicleview,drivercreatevehicle,vehicleedit,drivervehicleedit,vehiclechecklist,fleetinsertdata,driverodometer,jjreporter,dpachecklist"
     * @throws CreateException
     */
    public void ejbCreate() throws CreateException{

    }

    /**
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws CommunicationProblemException
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,vehicleedit,drivervehicleedit,drivercreateevent,vehiclechecklist,driverodometer,dpachecklist,jjreporter"
     */
    public VehicleData findVehicleByPrimaryKey(Integer id, UserSessionData userSessionData) throws Exception {
        VehicleData data = null;
        try {
            if (id == null) {
                throw new InvalidDataException("findVehicleByPrimaryKey","id = null");
            }
            if (userSessionData == null) {
                throw new DeslogedException("findVehicleByPrimaryKey","session = null");
            }
            Session session = HibernateUtil.currentSession();
            String sql = "SELECT vehicle FROM Vehicle AS vehicle ";
			if (userSessionData.isDriver() || userSessionData.isReporterJJ()) {
                sql += " WHERE vehicle.corpCountry.pk.corporation.id = "+userSessionData.getFilter().getCorporationId();
            }else {
                sql += UtilityQuery.corporationDivisionsRestictions("vehicle",userSessionData);
            }
            sql += "AND vehicle.id = :id ";
            Query query = session.createQuery(sql);
            query.setParameter("id", id);

            Vehicle vehicle = (Vehicle)query.uniqueResult();
            if(vehicle != null) {
                data = vehicle.getVehicleData();
            }
        } catch (Exception e) {
			Object[] parameters = {id};
            handler.getException(e,"findVehicleByPrimaryKey",userSessionData,parameters);
        }

        return data;
    }

    /**
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws CommunicationProblemException
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccessr"
     */
    public boolean validateDeleteChecklist(Integer idChecklist, UserSessionData userSessionData) throws Exception {

        try {
            if (idChecklist == null) {
                throw new InvalidDataException("validateDeleteChecklist","idChecklist=null");
            }
            if (userSessionData == null) {
                throw new DeslogedException("findVehicleByPrimaryKey","session = null");
            }
            Session session = HibernateUtil.currentSession();

            Query query=session.createQuery("select c from UnconformityDPA as c where c.check="+idChecklist);

            return query.list().size()==0;

        } catch (Exception e) {
			Object[] parameters = {idChecklist};
            handler.getException(e,"validateDeleteChecklist",userSessionData,parameters);
        }

        return false;
    }
    
    
    /**
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws CommunicationProblemException
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,vehicleview,drivervehicleview,driverodometer,dpachecklist"
     */
    public List<InstructorActivityData> findInstructorActivityListByInstructor(String instructor, UserSessionData userSessionData) throws Exception{
    	List<InstructorActivityData> result = new ArrayList<InstructorActivityData>();
    	try {
    		if (instructor == null) {
                throw new InvalidDataException("findInstructorActivityListByInstructor","instructor = null");
            }
    		if (userSessionData == null) {
                throw new DeslogedException("findInstructorActivityListByInstructor","session = null");
            }
    		Session session = HibernateUtil.currentSession();
    		
    		Query query = session.createQuery("SELECT i FROM InstructorActivity i WHERE i.user.loginName = :instructor");
    		query.setParameter("instructor", instructor);
    		
    		Iterator<InstructorActivity> it = query.list().iterator();
    		
    		while(it.hasNext()) {
    			InstructorActivityData data = new InstructorActivityData(it.next());
    			data.setUser(userSessionData);
    			result.add(data);
    		}
    		
    	} catch (Exception e) {
    		Object[] parameters = {instructor};
            handler.getException(e,"findInstructorActivityListByInstructor",userSessionData,parameters);
    	}
    	
    	return result;
    }
    

    /**
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws CommunicationProblemException
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,vehicleview,drivervehicleview,driverodometer,dpachecklist"
     */
    public ListResult findList(String value, UserSessionData userRequest,int attribute,int first,int count, int status) throws Exception {
        ListResult listResult=null;
        try{
            Session session = HibernateUtil.currentSession();

            String queryStr = "SELECT COUNT(*) FROM Vehicle AS vehicle ";

            if(userRequest.isDriver()) {
                queryStr += " WHERE vehicle.corpCountry.pk.corporation.id = "+userRequest.getFilter().getCorporationId();
                queryStr += " AND vehicle.driver IS NOT null AND vehicle.driver.id = "+String.valueOf(userRequest.getDriverData().getId());
            }else {
                queryStr += UtilityQuery.corporationDivisionsRestictions("vehicle",userRequest);
            }

            Query query = getQuery(session,attribute,queryStr,value,userRequest, status);

            List vehicleColl = query.list();
            Iterator iter = vehicleColl.iterator();
            Integer total = new Integer(0);
            while (iter.hasNext()) {
                total = new Integer(((Long) iter.next()).intValue());
            }

            queryStr = " SELECT vehicle FROM Vehicle AS vehicle ";

            if(userRequest.isDriver()) {
                queryStr += " WHERE vehicle.corpCountry.pk.corporation.id = "+userRequest.getFilter().getCorporationId();
                queryStr += " AND vehicle.driver IS NOT null AND vehicle.driver.id = "+String.valueOf(userRequest.getDriverData().getId());
            }else {
                queryStr += UtilityQuery.corporationDivisionsRestictions("vehicle",userRequest);
            }

            query = getQueryWithOrderBy(session,attribute,queryStr,value,status, userRequest);

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

            vehicleColl = query.list();
            Collection result = new LinkedList();
            if (vehicleColl != null){
            	 iter = vehicleColl.iterator();
                 Vehicle vehicle;
                 while (iter.hasNext()) {
                	 vehicle = (Vehicle) iter.next();
                	 if (vehicle != null && vehicle.getVehicleData()!= null){
                		 result.add(vehicle.getVehicleData());
                	 }
                 }
            }

            listResult = new ListResult(result, total.intValue());

        } catch (Exception e) {
			Object[] parameters = {value,attribute,first,count,status};
             handler.getException(e,"findList",userRequest,parameters);

        }

        return listResult;

    }

    /**
     *
     * @param session
     * @param attribute
     * @param queryStr
     * @param value
     * @param userRequest
     * @param status
     * @return
     * @throws Exception
     */
    public Query getQuery(Session session, int attribute,String queryStr, String value, UserSessionData userRequest, int status) throws Exception {
    	try{
            //Check data integrity
    		if (value == null) {
                throw new InvalidDataException("getQuery","value is null");
            }
            if (userRequest == null) {
                throw new DeslogedException("getQuery","session is null");
            }

            switch (attribute) {
                case MATRICULA:
                    queryStr += " and  lower(matricula) like lower(:value)";
                    value+="%";
                    break;
                case PREFIX:
                    queryStr += " and  lower(vehicle.optionalInfo.prefix) like lower(:value)";
                    value+="%";
                    break;
                default:
                    throw new InvalidDataException("getQuery","invalid attribute");
            }
            switch (status) {
               case VehicleData.ACTIVE:
                   queryStr += " and exclusionDate is null";
                   break;
               case VehicleData.EXCLUDED:
                   queryStr += " and exclusionDate is not null";
                   break;
            }

            Query query = session.createQuery(queryStr);
            if(value.endsWith("%"))
                query.setParameter("value", "%" + value);
            else
                query.setParameter("value", new Integer(value));

            return query;

    	}catch (Exception e) {
    		Object[] parameters = {session,attribute,queryStr,value,status};
   			handler.getException(e, "getQuery", userRequest, parameters);
    	}
    	return null;
    }


    /**
     * @param session
     * @param attribute
     * @param queryStr
     * @param value
     * @param status
     * @param userRequest
     * @return
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws DCException
     */
    public Query getQueryWithOrderBy(Session session, int attribute,String queryStr, String value, int status ,UserSessionData userRequest) throws Exception {
    	try{
            //Check data integrity
            if (value == null) {
                throw new InvalidDataException("Class persistence.vehicle.VehicleReport(findVehicleByLike), value name is empty",   "generic.vehicle.vehicledata.valueempty");
            }
            if (userRequest == null) {
                throw new DeslogedException(
                        "Class persistence.vehicle.VehicleReport(findUserByLike), session data is empty","generic.error.deslogued");
            }


            switch (attribute) {
                case MATRICULA:
                    queryStr += " and  lower(matricula) like lower(:value)";
                    value+="%";
                    break;
                case PREFIX:
                    queryStr += " and  lower(vehicle.optionalInfo.prefix) like lower(:value)";
                    value+="%";
                    break;
                default:
                    throw new InvalidDataException(
                        "Class persistence.vehicle.VehicleReport(findVehicleByLike), attribute name is mot right",
                        "generic.vehicle.vehicledata.invalidattribute");
            }

            switch (status) {
               case VehicleData.ACTIVE:
                   queryStr += " and exclusionDate is null";
                   break;
               case VehicleData.EXCLUDED:
                   queryStr += " and exclusionDate is not null";
                   break;
            }
            String orderBy = " order by vehicle.matricula";
            Query query = session.createQuery(queryStr+orderBy);

            //Query query = session.createQuery(queryStr);

            if(value.endsWith("%"))
                query.setParameter("value", "%" + value);
            else
                query.setParameter("value", new Integer(value));

            return query;

    	}catch (Exception e) {
       		Object[] parameters = {session,attribute,queryStr,value,status};
   			handler.getException(e, "getQueryWithOrderBy", userRequest, parameters);
    	}
    	return null;
    }


    /**
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws CommunicationProblemException
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator"
     */
    public List findVehicleByLike(UserSessionData userRequest, int attribute,String value,int first,int count) throws Exception {
    	try {
	        List vehiclesData = new ArrayList();
	        List list = find(userRequest,attribute,value,first,count);
	        Iterator iter = list.iterator();
	        while (iter.hasNext()) {
	            Vehicle vehicle = (Vehicle) iter.next();
	            vehiclesData.add(vehicle.getVehicleData());
	        }
	        return (vehiclesData);
    	}catch (Exception e) {
       		Object[] parameters = {attribute,value,first,count};
   			handler.getException(e, "findVehicleByLike", userRequest, parameters);
    	}
    	return null;
    }

    /**
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws CommunicationProblemException
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator"
     */
    public List findVehicleByLike(UserSessionData userRequest,
            VehicleData vehicleData,int first,int count) throws InvalidDataException, DeslogedException,
            CommunicationProblemException {

        List list = find(userRequest,vehicleData,first,count);
        Iterator iter = list.iterator();
        List vehiclesData = new ArrayList();
        while (iter.hasNext()) {
            Vehicle vehicle = (Vehicle) iter.next();
            vehiclesData.add(vehicle.getVehicleData());
        }
        return (vehiclesData);
    }

    private List find(UserSessionData userRequest,int attribute,String value,int first,int count) throws Exception {

    	try {
    		if (userRequest == null) {
    			throw new DeslogedException("find","session is null");
    		}

    		Session session = HibernateUtil.currentSession();

    		Corporation corporation = new Corporation();
    		Integer corporationId = userRequest.getFilter().getCorporationId();
    		session.load(corporation,corporationId);

    		Criteria c = session.createCriteria(Vehicle.class);
    		//c.setFirstResult(first);
    		//c.setMaxResults(count);
    		c.add(Expression.eq("corporation",corporation));

        //	Check data integrity
    		if (value != null) {
    			switch (attribute) {
                	case CHASIS:
                		c.add(Expression.eq("chasisNumber",new Integer(value)));
                		break;
                	case MATRICULA:
                		c.add(Expression.eq("matricula",value));
                		break;
                	default:
                		throw new InvalidDataException("find","invalid attribute");
    			}
    		}
    		return c.list();
    	}catch (Exception e) {
       		Object[] parameters = {attribute,value,first,count};
   			handler.getException(e, "find", userRequest, parameters);
    	}
    	return null;
    }

    private List find(UserSessionData userRequest,VehicleData vehicle,int first,int count) throws InvalidDataException, DeslogedException {

        if (userRequest == null) {
            throw new DeslogedException(
                    "Class persistence.vehicle.VehicleReport(findUserByLike), session data is empty",
                    "generic.error.deslogued");
        }

        Session session = HibernateUtil.currentSession();

        // Set codition on corporation
        Corporation corporation = new Corporation();
        Integer corporationId = userRequest.getFilter().getCorporationId();
        session.load(corporation,corporationId);

        Criteria c = session.createCriteria(Vehicle.class)
        .add(Expression.eq("matricula",vehicle.getMatricula()))
        .add(Expression.eq("chasisNumber",vehicle.getChasisNumber()))
        .add(Expression.eq("corporation",corporation))
        .createCriteria("driver")
                .add(Expression.ilike("firstName",vehicle.getDriver().getFirstName(),MatchMode.START))
                .add(Expression.ilike("lastName",vehicle.getDriver().getLastName(),MatchMode.START));

        return c.list();
    }


    public boolean haveMilles(UserSessionData userRequest, VehicleData vehicle)
    {
            Session session = HibernateUtil.currentSession();

            String sql = "select count(distinct odometer) from Odometer as o  "+
                    "where o.pk.vehicle = :id ";
            Query query = session.createQuery(sql);
            query.setParameter("id", vehicle.getId());
            Object result = query.uniqueResult();
            if((result != null) && ((Integer)result).intValue() > 1)
                return true; // Tiene millas
            else
                return false; // No tiene millas.

    }

    /**
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws CommunicationProblemException
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator"
     */

    public List findVehicleByLike(UserSessionData userRequest,String matricula,Integer chasisNumber,String firstName, String lastName,int first,int count, int status) throws Exception {
        List list = find(userRequest,matricula,chasisNumber,firstName,lastName,first,count, status);
        Iterator iter = list.iterator();
        List vehiclesData = new ArrayList();
        while (iter.hasNext()) {
            Vehicle vehicle = (Vehicle) iter.next();
            vehiclesData.add(vehicle.getVehicleData());
        }
        return (vehiclesData);
    }

    /**
     * @param userRequest
     * @param matricula
     * @param chasisNumber
     * @param firstName
     * @param lastName
     * @param first
     * @param count
     * @param status
     * @return
     * @throws InvalidDataException
     * @throws DeslogedException
     */
    private List find(UserSessionData userRequest,String matricula,Integer chasisNumber,String firstName, String lastName, int first, int count, int status) throws Exception {
    	try {
    		if (userRequest == null) {
    			throw new DeslogedException("find","session is null");
    		}

    		Session session = HibernateUtil.currentSession();

    		// Set codition on corporation
    		Corporation corporation = new Corporation();
    		Integer corporationId = userRequest.getFilter().getCorporationId();
    		session.load(corporation,corporationId);

    		Criteria c;
    		switch (status) {
    			case VehicleData.ACTIVE:
    				// Activo: Exclusion Date = NULL
    				c = session.createCriteria(Vehicle.class)
		            .add(Expression.eq("matricula",matricula))
		            .add(Expression.eq("chasisNumber",chasisNumber))
		            .add(Expression.eq("corporation",corporation))
		            .add(Expression.isNull("exclusionDate"))
		            .createCriteria("driver")
                    .add(Expression.ilike("firstName",firstName,MatchMode.START))
                    .add(Expression.ilike("lastName",lastName,MatchMode.START));
    				break;
    			case VehicleData.EXCLUDED:
    				// Excluido: ExclusionDate != null
    				c = session.createCriteria(Vehicle.class)
		            .add(Expression.eq("matricula",matricula))
		            .add(Expression.eq("chasisNumber",chasisNumber))
		            .add(Expression.eq("corporation",corporation))
		            .add(Expression.isNotNull("exclusionDate"))
		            .createCriteria("driver")
                    .add(Expression.ilike("firstName",firstName,MatchMode.START))
                    .add(Expression.ilike("lastName",lastName,MatchMode.START));
    				break;
    			default:
    				// 	TODOS:
    				c = session.createCriteria(Vehicle.class)
		            .add(Expression.eq("matricula",matricula))
		            .add(Expression.eq("chasisNumber",chasisNumber))
		            .add(Expression.eq("corporation",corporation))
		            .createCriteria("driver")
                    .add(Expression.ilike("firstName",firstName,MatchMode.START))
                    .add(Expression.ilike("lastName",lastName,MatchMode.START));
    				break;
    		}

    		return c.list();
    	}catch (Exception e) {
			Object[] parameters = {matricula,chasisNumber,firstName,lastName,first,count,status};
			handler.getException(e, "find", userRequest, parameters);
		}
    	return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,vehicleview,drivervehicleview"
     * @param userRequest - logged user
     * @param registrationNumber - registrationNumber of vehicle
     * @param chasisNumber - chasisNumber of vehicle
     * @param firstNameDriver - first name of driver
     * @param lastNameDriver - last name of driver
     * @param divOrgs - collection of div org that the vehicle is associated
     * @param divGeo - country and geographic division that the vehicle is associated
     * @param first - first index of the search to return
     * @param count - number of elements to return
     * @return result of the search
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws CommunicationProblemException
     */
    public ListResult advancedSearch(UserSessionData userRequest,String registrationNumber,String costCenter,String chasisNumber,String permissionNumber,String firstNameDriver,String lastNameDriver,
            Integer[][] divOrgs,Integer[][] divGeos,int first,int count, int status, String prefix)throws Exception {

    	try {
	        Session session = HibernateUtil.currentSession();

	        Hashtable parameters = new Hashtable();
	        String queryStr = UtilityQuery.corporationDivisionsRestictions("vehicle",userRequest);

	        if(chasisNumber != null) {
	            queryStr +=" and vehicle.chasisNumber = (:chasisNumber)";
	            parameters.put("chasisNumber",chasisNumber);
	        }

	        // Si es Klabin, me puedo fijar el prefijo de los datos opcionales del vehiculo.
	        if (userRequest.getFilter().getCorporationId().equals(new Integer(CorporationData.KLABIN))) {
	            if (prefix != null){
	                queryStr += " and vehicle.optionalInfo.prefix = (:prefix) ";
	                parameters.put("prefix",prefix);
	            }
	        }


	        if(costCenter != null) {
	            queryStr +=" and lower(vehicle.costCenter) like lower(:costCenter)";
	            parameters.put("costCenter","%"+costCenter+"%");
	        }
	        if(registrationNumber != null) {
	            queryStr +=" and lower(vehicle.matricula) like lower(:registrationNumber)";
	            parameters.put("registrationNumber","%"+registrationNumber+"%");
	        }
	        if(permissionNumber != null) {
	            queryStr +=" and lower(vehicle.permissionNumber) like lower(:permissionNumber)";
	            parameters.put("permissionNumber","%"+permissionNumber+"%");
	        }
	        if(firstNameDriver != null) {
	            queryStr +=" and lower(vehicle.driver.firstName) like lower(:firstNameDriver)";
	            parameters.put("firstNameDriver","%"+firstNameDriver+"%");
	        }
	        if(lastNameDriver != null) {
	            queryStr +=" and lower(vehicle.driver.lastName) like lower(:lastNameDriver)";
	            parameters.put("lastNameDriver","%"+lastNameDriver+"%");
	        }
	        if(userRequest.isDriver()) {
	            queryStr += " and vehicle.driver is not null and vehicle.driver.id = "+String.valueOf(userRequest.getDriverData().getId());
	        }

	        if (status == VehicleData.ACTIVE){
	        	// Si buscamos vehiculos activos:
	        	queryStr += " and vehicle.exclusionDate is null ";
	        }else if (status == VehicleData.EXCLUDED){
	        	// Si buscamos vehiculos excluidos:
	        	queryStr += " and vehicle.exclusionDate is not null ";

	        }

	        if(divOrgs != null && divOrgs.length > 0) {
	            for(int j = 0; j < divOrgs.length; j++) {
	                if(divOrgs[j] != null && divOrgs[j].length > 0) {
	                    queryStr += " and vehicle.divOrg"+String.valueOf(j+1)+" in (";
	                    for(int i = 0; i < divOrgs[j].length; i++) {
	                        queryStr += divOrgs[j][i];
	                        queryStr += (i == divOrgs[j].length-1) ? ") " : ",";
	                    }
	                }
	            }
	        }
	        if(divGeos != null) {
	            if(divGeos[0] != null && divGeos[0].length > 0) {
	                queryStr += "and vehicle.corpCountry.pk.country in (";
	                for(int i = 0; i < divGeos[0].length; i++) {
	                    queryStr += divGeos[0][i];
	                    queryStr += (i == divGeos[0].length-1) ? ")" : ",";
	                }
	            }
	            if(divGeos[1] != null && divGeos[1].length > 0) {
	                queryStr += "and vehicle.geoLevel1 in (";
	                for(int i = 0; i < divGeos[1].length; i++) {
	                    queryStr += divGeos[1][i];
	                    queryStr += (i == divGeos[1].length-1) ? ")" : ",";
	                }
	            }
	        }

	        Query query = session.createQuery("select count(distinct vehicle.id)  from Vehicle as vehicle "+queryStr);
	        Enumeration keys = parameters.keys();
	        while(keys.hasMoreElements()) {
	            String parameter = (String)keys.nextElement();
	            query.setParameter(parameter,parameters.get(parameter));
	        }
	        List userColl = query.list();
	        Iterator iter = userColl.iterator();
	        Integer total = new Integer(0);
	        while (iter.hasNext()) {
	            total = new Integer(((Long) iter.next()).intValue());
	        }

	        String  orderBy = " order by vehicle.matricula";
	        query = session.createQuery("select distinct vehicle from Vehicle as vehicle "+queryStr+orderBy);
	        keys = parameters.keys();
	        while(keys.hasMoreElements()) {
	            String parameter = (String)keys.nextElement();
	            query.setParameter(parameter,parameters.get(parameter));
	        }
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
	        userColl = query.list();
	        Collection result = new LinkedList();
	        iter = userColl.iterator();
	        while (iter.hasNext()) {
	            Vehicle vehicle = (Vehicle) iter.next();
	            result.add(vehicle.getPrincipalAttributes());
	        }
	        session.close();

	        return new ListResult(result,total.intValue());
		}catch (Exception e) {
			Object[] parameters = {registrationNumber,costCenter,chasisNumber,firstNameDriver,lastNameDriver,divOrgs,divGeos,first,count,status,prefix};
			handler.getException(e, "advancedSearch", userRequest, parameters);
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
    public Collection findDriversVehicles(UserSessionData userSessionData,Integer driverId) throws Exception {
        Collection result = new LinkedList();
        try {
            Session session = HibernateUtil.currentSession();
            String queryStr = "SELECT vehicle FROM Vehicle AS vehicle";
            if(userSessionData.isDriver() || userSessionData.isReporterJJ()) {
                queryStr += " WHERE vehicle.corpCountry.pk.corporation.id = "+userSessionData.getFilter().getCorporationId();
            }else {
                queryStr += UtilityQuery.corporationDivisionsRestictions("vehicle",userSessionData);
            }
            queryStr += " AND driver.id = (:driver)";

            Query query = session.createQuery(queryStr);
            if(userSessionData.isDriver()) {
                query.setParameter("driver",userSessionData.getDriverData().getId());
            }else {
                query.setParameter("driver",driverId);
            }

            List list = query.list();
            if (list != null){
            	Iterator iter = list.iterator();
            	while (iter.hasNext()) {
            		result.add(((Vehicle)iter.next()).getPrincipalAttributes());
            	}
            }
        } catch (Exception e) {
        	Object[] parameters = {driverId};
            handler.getException(e,"findDriversVehicles",userSessionData,parameters);
        }
        return result;
    }

    /**
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws CommunicationProblemException
     * @ejb.interface-method
     * @ejb.permission role-name = "vehicleview,administrator"
     */
    public Collection getObservations(Integer vehicleId, UserSessionData userRequest) throws Exception {
        try{
            Session session = HibernateUtil.currentSession();

            String queryStr = "select log from VehicleLog as log "+
                " where log.pk.vehicle = (:vehicle)";

            Query query = session.createQuery(queryStr);
            query.setParameter("vehicle",vehicleId);

            List userColl = query.list();
            List result = new LinkedList();
            Iterator iter = userColl.iterator();
            while (iter.hasNext()) {
                VehicleLog log = (VehicleLog) iter.next();
                result.add(log.getVehicleObservations());
            }

            return result;

        }catch (Exception e) {
        	Object[] parameters = {vehicleId};
        	handler.getException(e, "getObservations", userRequest, parameters);
        }
        return null;
    }

    /**
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws CommunicationProblemException
     * @ejb.interface-method
     * @ejb.permission role-name = "vehicleupdate,administrator"
     */
    public List getVehicles(String vehicles, UserSessionData userRequest) throws Exception {
        try{
            Session session = HibernateUtil.currentSession();

            String queryStr = "select vehicle from  Vehicle as vehicle "+
                UtilityQuery.corporationDivisionsRestictions("vehicle",userRequest)+
                " and vehicle.id in ("+vehicles+")";

            Query query = session.createQuery(queryStr);
            List userColl = query.list();
            List result = new LinkedList();
            Iterator iter = userColl.iterator();
            while (iter.hasNext()) {
                Vehicle vehicle = (Vehicle) iter.next();
                result.add(vehicle.getPrincipalAttributes());
            }

            return result;

        }catch (Exception e) {
        	Object[] parameters = {vehicles};
        	handler.getException(e, "getVehicle", userRequest, parameters);
        }
        return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,drivercreatevehicle,vehiclecreate,vehicleupdate"
     * @param registrationNumber - registrationNumber of vehicle
     * @param country - country of vehicle
     * @return boolean
     * @throws DeslogedException
     * @throws CommunicationProblemException
     */
    public boolean vehicleExist(String registrationNumber,Integer country, UserSessionData userSessionData) throws Exception {

    	try {
	        Session session = HibernateUtil.currentSession();

	        Hashtable parameters = new Hashtable();

	        String condition = "";
	        condition +=" where lower(vehicle.matricula) like lower(:registrationNumber)";
	        parameters.put("registrationNumber",registrationNumber);
            condition += " and vehicle.corpCountry.pk.country.id = :country";
	        parameters.put("country",country);
	        if(userSessionData.isForestalUnified() || userSessionData.isCorporation(new int[]{CorporationData.SYNGENTA,CorporationData.SYNGENTA_BR})) {
		        condition += " and vehicle.corpCountry.pk.corporation.id = :corporation";
		        parameters.put("corporation",new Integer(userSessionData.getCorporationId()));
	        }

	        Query query = session.createQuery("select count(distinct vehicle.id)  from Vehicle as vehicle "+condition);
	        Enumeration keys = parameters.keys();
	        while(keys.hasMoreElements()) {
	            String parameter = (String)keys.nextElement();
	            query.setParameter(parameter,parameters.get(parameter));
	        }

	        List userColl = query.list();
	        Iterator iter = userColl.iterator();
	        Integer total = new Integer(0);
	        while (iter.hasNext()) {
	            total = new Integer(((Long) iter.next()).intValue());
	        }
	        return(total.intValue() > 0);
    	}catch (Exception e) {
        	Object[] parameters = {registrationNumber};
        	handler.getException(e, "vehicleExist", null, parameters);
		}
    	return true;
   }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleedit,drivervehicleedit,vehiclecreate,vehicleupdate"
     * @param id - a vehicle id
     * @param registrationNumber - registrationNumber of vehicle
     * @param country - country of vehicle
     * @return boolean
     * @throws DeslogedException
     * @throws CommunicationProblemException
     */
    public boolean existOther(Integer id,String registrationNumber,Integer country) throws Exception {
    // Devuelve true si existe en la base de datos un vehiculo con igual matricula e igual pais pero distinto id
    // que el vehiculo pasado como parámetro (dado por un id)

        try {
        	Session session = HibernateUtil.currentSession();

        	Hashtable parameters = new Hashtable();

        	String condition = "";
        	condition +=" where lower(vehicle.matricula) like lower(:registrationNumber)";
        	parameters.put("registrationNumber",registrationNumber);
        	condition += " and vehicle.corpCountry.pk.country.id = :country";
        	parameters.put("country",country);

        	Query query = session.createQuery("select distinct vehicle.id  from Vehicle as vehicle "+condition);
        	Enumeration keys = parameters.keys();
        	while(keys.hasMoreElements()) {
        		String parameter = (String)keys.nextElement();
        		query.setParameter(parameter,parameters.get(parameter));
        	}

        	Object unirqueResult = query.uniqueResult();
        	if(unirqueResult != null) {
        		Integer idVehicle = (Integer)unirqueResult;
        		return(!idVehicle.equals(id));
        	}
        }catch (Exception e) {
        	Object[] parameters = {registrationNumber};
        	handler.getException(e, "vehicleExist", null, parameters);
		}
		return false;
   }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query,jjreporter"
     */
    public ListResult getReportVehicles(String type, String trade, String year,String use, String fuel, String centerCost,
            Integer[][] divOrgs, Integer[][] divGeos, int first, int count,UserSessionData userSessionData,
            String plate, String model, int status) throws Exception {
    	try {
	        Session session = HibernateUtil.currentSession();

	        StringBuffer queryStr = new StringBuffer();
	        queryStr.append(UtilityQuery.corporationDivisionsRestictions("vehicle",userSessionData));


	        HashSet set = new HashSet();
	        String divOrgStr = "";
	        if(divOrgs != null && divOrgs.length > 0) {
	            for(int j = 0; j < divOrgs.length; j++) {
	                if(divOrgs[j] != null && divOrgs[j].length > 0) {
	                    divOrgStr = "";
	                    divOrgStr = " and vehicle.divOrg"+String.valueOf(j+1)+" in (";
	                    for(int i = 0; i < divOrgs[j].length; i++) {
	                        set.add(divOrgs[j][i]); // Para evitar repetidos
	                    }
	                    Iterator it = set.iterator();
	                    int i = 0;
	                    while (it.hasNext()){
	                        String div = ((Integer)it.next()).toString();
	                        divOrgStr += div;
	                        divOrgStr += (i == set.size()-1) ? ") " : ",";
	                        i++;
	                    }
	                    //System.out.println(divOrgStr);
	                    queryStr.append(divOrgStr);
	                }
	            }
	        }

	        if(divGeos != null) {
	            if(divGeos[0] != null && divGeos[0].length > 0) {
	                queryStr.append("and vehicle.corpCountry.pk.country in (");
	                for(int i = 0; i < divGeos[0].length; i++) {
	                    queryStr.append(divGeos[0][i]);
	                    queryStr.append((i == divGeos[0].length-1) ? ")" : ",");
	                }
	            }
	        }
	        if(!Util.empty(type)) {
	            queryStr.append("and vehicle.type.resourceBundleId = '"+type+"' ");
	        }
	        if(!Util.empty(trade)) {
	            queryStr.append("and vehicle.trade.resourceBundleId = '"+trade+"' ");
	        }
	        if(!Util.empty(year)) {
	            queryStr.append("and vehicle.year = "+year+" ");
	        }
	        if(!Util.empty(use)) {
	            queryStr.append("and vehicle.use.resourceBundleId = '"+use+"' ");
	        }
	        if(!Util.empty(fuel)) {
	            queryStr.append("and vehicle.fuel.resourceBundleId = '"+fuel+"' ");
	        }

	        if(!Util.empty(centerCost)) {
	            if (userSessionData.getFilter().getCorporationId().equals(new Integer(CorporationData.KLABIN))){
	                queryStr.append(" and lower(vehicle.optionalData.prefix) like lower(:costCenter)");
	            }else {
	                queryStr.append(" and lower(vehicle.costCenter) like lower(:costCenter)");
	            }
	        }


	        if (!Util.empty(plate)){
	            queryStr.append(" and vehicle.matricula = '"+plate+"' ");
	        }
	        if (!Util.empty(model)){
	            queryStr.append(" and vehicle.model = '"+model+"' ");
	        }
	        switch(status) {
	            case VehicleData.ACTIVE:
	                queryStr.append(" and vehicle.exclusionDate is null");
	                break;
	            case VehicleData.EXCLUDED:
	                queryStr.append(" and vehicle.exclusionDate is not null");
	                break;
	        }
	        // Creo la consulta
	        Query query = session.createQuery("select count(distinct vehicle) from Vehicle as vehicle "+queryStr.toString());
	        // Seteo los parametros
	        if(!Util.empty(centerCost)) {
	            query.setParameter("costCenter","%"+centerCost+"%");
	        }

	        // Realizo la consulta:
	        List userColl = query.list();
	        Iterator iter = userColl.iterator();
	        Integer total = new Integer(0);
	        // Calculo el total de registros:
	        while (iter.hasNext()) {
	            total = new Integer(((Long) iter.next()).intValue());
	        }


	        // Realizo la consulta:
	        query = session.createQuery("select distinct vehicle from Vehicle as vehicle " +
	                queryStr.toString() + " order by vehicle.matricula ");
	        // Seteo los parametros
	        if(!Util.empty(centerCost)) {
	            query.setParameter("costCenter","%"+centerCost+"%");
	        }
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

	        // Realizo la consulta:
	        userColl = query.list();
	        Collection result = new LinkedList();
	        iter = userColl.iterator();

	        if(userSessionData.getFilter().getCorporationId().intValue() == CorporationData.MONSANTO) {
		        String ids = "0";
		        // Armo la coleccion del resultado:
		        Hashtable hash = new Hashtable();
		        while (iter.hasNext()) {
		            Vehicle v = (Vehicle) iter.next();
		            hash.put(v.getId(),v.getVehicleData());
		            ids += ","+v.getId();
		        }

		        String sql2 = "SELECT vehicle,odometer FROM odometers WHERE vehicle IN ("+ids+") ORDER BY year DESC,period DESC";
	        	Query q2 = session.createSQLQuery(sql2).addScalar("vehicle", Hibernate.INTEGER).addScalar("odometer", Hibernate.INTEGER);
	        	Iterator it = q2.list().iterator();
	        	Collection added = new LinkedList();
	        	while(it.hasNext()) {
	        		Object[] data = (Object[])it.next();
	        		if(!added.contains(data[0])) {
	        			added.add(data[0]);
	        			((VehicleData)hash.get(data[0])).setOdometer((Integer)data[1]);
	        		}
	        	}

		        String sql3 = "SELECT vehicle,userid,date,action,comment FROM vehiclelog WHERE vehicle IN ("+ids+") ORDER BY vehicle,date";
	        	Query q3 = session.createSQLQuery(sql3).addScalar("vehicle", Hibernate.INTEGER).addScalar("userid", Hibernate.STRING).addScalar("date", Hibernate.DATE).addScalar("action", Hibernate.INTEGER).addScalar("comment", Hibernate.STRING);
	        	it = q3.list().iterator();
	        	while(it.hasNext()) {
	        		Object[] data = (Object[])it.next();
	        		if(hash.containsKey(data[0])) {
	        			((VehicleData)hash.get(data[0])).addComment(data);
	        		}
	        	}

	        	Enumeration en = hash.elements();
	        	while(en.hasMoreElements()) {
	        		result.add(en.nextElement());
	        	}
	        }else {
		        // Armo la coleccion del resultado:
		        while (iter.hasNext()) {
		            result.add(((Vehicle) iter.next()).getVehicleData());
		        }
	        }
	        // Armo la coleccion del resultado:
	        session.close();

	        return new ListResult(result,total.intValue());
        }catch (Exception e) {
        	Object[] parameters = {type,trade,year,use,fuel,centerCost,divOrgs,divGeos,first,count,plate,model,status};
        	handler.getException(e, "getReportVehicles", userSessionData, parameters);
		}
        return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query,jjreporter"
     */
    public Collection<VehicleData> getReportVehicles(String plate, String trade, String model, String year, UserSessionData userSessionData) throws Exception {
    	try {
	        Session session = HibernateUtil.currentSession();

	        // Realizo la consulta:
	        String sql = "SELECT id,matricula,trade,model,year " +
	        		"FROM vehicles WHERE corporation = " + userSessionData.getCorporationId();
	        if (!Util.empty(plate)){
	        	sql += " AND LOWER(matricula) LIKE '%"+plate.toLowerCase()+"%' ";
	        }
	        if (!Util.empty(model)){
	        	sql += " AND lower(trim(model)) LIKE '%"+model.toLowerCase()+"%' ";
	        }
	        if (!Util.empty(trade)){
	        	sql += " AND trade LIKE '"+trade+"' ";
	        }
	        if (!Util.empty(year)){
	        	sql += " AND year = '"+year+"' ";
	        }
	    	sql += " ORDER BY matricula ";
	        Query query = session.createSQLQuery(sql).addScalar("id",Hibernate.INTEGER).addScalar("matricula",Hibernate.STRING).addScalar("trade",Hibernate.STRING).addScalar("model",Hibernate.STRING).addScalar("year",Hibernate.INTEGER);

	        Collection<VehicleData> result = new LinkedList<VehicleData>();

	        // Realizo la consulta:
	        Collection userColl = query.list();
	        Iterator iter = userColl.iterator();

	        while (iter.hasNext()) {
	            Object[] v = (Object[]) iter.next();
	            VehicleData vehicle = new VehicleData((Integer)v[0], (String)v[1], (String)v[3], (String)v[2]);
	            vehicle.setYear((Integer)v[4]);
	            result.add(vehicle);
	        }

	        return result;
        }catch (Exception e) {
        	Object[] parameters = {plate,model,trade,year};
        	handler.getException(e, "getReportVehicles", userSessionData, parameters);
		}
        return null;
    }

    /**
     * Devuelve una coleccion de objetos[] (date, driver) del log de vehìculos
     * segun el id del vehiculo.
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     * @param idVehicle
     * @return
     */
    public Collection findVehicleDriversLogList(Integer idVehicle, UserSessionData userSessionData) throws Exception{
    	try {
	        Collection result = null;
    		Session session = HibernateUtil.currentSession();

	        // Realizo la consulta:
	        Query query = session.createQuery("select vehiclelog.pk.date, vehiclelog.driver from VehicleLog as vehiclelog " +
	        		" where vehiclelog.pk.vehicle = " + idVehicle.toString()+" order by vehiclelog.pk.date ASC ");

	        List vehicleLog = query.list();
	        if (vehicleLog != null && !vehicleLog.isEmpty()){
	        	result = new LinkedList();
	        	Iterator iter = vehicleLog.iterator();
	        	while (iter.hasNext()){
	        	    Object[] dateDriver = (Object[])iter.next();
        			result.add(dateDriver);
        		}
        	}
        	return result;

    	}catch (Exception e) {
    		Object[] parameters = {idVehicle};
    		handler.getException(e, "findVehicleDriversLogList", userSessionData, parameters);
    	}
    	return null;
    }




    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     */
    public ListResult getReportTrailers(String type, String trade, String year,String use, String fuel, String centerCost,
            Integer[][] divOrgs, Integer[][] divGeos, int first, int count,UserSessionData userSessionData,
            String plate, String model, int status) throws Exception {

    	try{
	        Session session = HibernateUtil.currentSession();

	        StringBuffer queryStr = new StringBuffer();
	        queryStr.append(UtilityQuery.corporationDivisionsRestictions("trailer",userSessionData));


	        HashSet set = new HashSet();
	        String divOrgStr = "";
	        if(divOrgs != null && divOrgs.length > 0) {
	            for(int j = 0; j < divOrgs.length; j++) {
	                if(divOrgs[j] != null && divOrgs[j].length > 0) {
	                	divOrgStr = "";
	                    divOrgStr = " and trailer.divOrg"+String.valueOf(j+1)+" in (";
	                    for(int i = 0; i < divOrgs[j].length; i++) {
	                    	set.add(divOrgs[j][i]); // Para evitar repetidos

	                        // divOrgStr += divOrgs[j][i];
	                        // divOrgStr += (i == divOrgs[j].length-1) ? ") " : ",";
	                    }
	                    Iterator it = set.iterator();
	                    int i = 0;
	                    while (it.hasNext()){
	                    	String div = ((Integer)it.next()).toString();
	                    	divOrgStr += div;
	                        divOrgStr += (i == set.size()-1) ? ") " : ",";
	                        i++;
	                    }
	                    //System.out.println(divOrgStr);
	                    queryStr.append(divOrgStr);
	                }
	            }
	        }

	        if(divGeos != null) {
	            if(divGeos[0] != null && divGeos[0].length > 0) {
	                queryStr.append("and trailer.corpCountry.pk.country in (");
	                for(int i = 0; i < divGeos[0].length; i++) {
	                    queryStr.append(divGeos[0][i]);
	                    queryStr.append((i == divGeos[0].length-1) ? ")" : ",");
	                }
	            }
	            if(divGeos[1] != null && divGeos[1].length > 0) {
	                queryStr.append("and trailer.geoLevel1 in (");
	                for(int i = 0; i < divGeos[1].length; i++) {
	                    queryStr.append(divGeos[1][i]);
	                    queryStr.append((i == divGeos[1].length-1) ? ")" : ",");
	                }
	            }
	        }
	        if(!Util.empty(trade)) {
	            queryStr.append("and trailer.trade.resourceBundleId = '"+trade+"' ");
	        }
	        if(!Util.empty(year)) {
	            queryStr.append("and trailer.year = "+year+" ");
	        }


	        if(!Util.empty(centerCost)) {
	       		queryStr.append(" and lower(trailer.costCenter) like lower(:costCenter)");
	        }


	        if (!Util.empty(plate)){
	        	queryStr.append(" and trailer.plate = '"+plate+"' ");
	        }
	        if (!Util.empty(model)){
	        	queryStr.append(" and trailer.model = '"+model+"' ");
	        }
	        switch(status) {
	            case VehicleData.ACTIVE:
	                queryStr.append(" and trailer.exclusionDate is null");
	                break;
	            case VehicleData.EXCLUDED:
	                queryStr.append(" and trailer.exclusionDate is not null");
	                break;
	        }
	        // Creo la consulta
	        Query query = session.createQuery("select count(distinct trailer) from Trailer as trailer "+queryStr.toString());
	        // Seteo los parametros
	        if(!Util.empty(centerCost)) {
	            query.setParameter("costCenter","%"+centerCost+"%");
	        }

	        // Realizo la consulta:
	        List userColl = query.list();
	        Iterator iter = userColl.iterator();
	        Integer total = new Integer(0);
	        // Calculo el total de registros:
	        while (iter.hasNext()) {
	            total = new Integer(((Long) iter.next()).intValue());
	        }


	        // Realizo la consulta:
	        query = session.createQuery("select distinct trailer from Trailer as trailer " +
	        		queryStr.toString() + " order by trailer.plate ");
	        // Seteo los parametros
	        if(!Util.empty(centerCost)) {
	            query.setParameter("costCenter","%"+centerCost+"%");
	        }
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

	        // Realizo la consulta:
	        userColl = query.list();
	        Collection result = new LinkedList();
	        iter = userColl.iterator();

	        // Armo la coleccion del resultado:
	        while (iter.hasNext()) {
	            Trailer trailer = (Trailer) iter.next();
	            result.add(trailer.getVehicleData());
	        }
	        session.close();

	        return new ListResult(result,total.intValue());
    	}catch(Exception e){
    		Object[] parameters = {type,trade,year,use,fuel,centerCost,divOrgs,divGeos,first,count,plate,model,status};
    	    handler.getException(e,"getReportTrailers",userSessionData,parameters);
    	}
    	return null;
    }


    /**
     *
     * @param type
     * @param trade
     * @param year
     * @param use
     * @param fuel
     * @param centerCost
     * @param divOrgs
     * @param divGeos
     * @param first
     * @param count
     * @param userSessionData
     * @param plate
     * @param model
     * @param status
     * @return
     */
    private String getReportVehicleQuerySQL(String type, String trade, String year,String use, String fuel, String centerCost,
            Integer[][] divOrgs, Integer[][] divGeos, int first, int count,UserSessionData userSessionData,
            String plate, String model, int status) throws Exception {

        StringBuffer queryStr = new StringBuffer();

        String divisionsRestictions = UtilityQuery.corporationDivisionsRestictionsSQL("vehicle",userSessionData);
        queryStr.append(divisionsRestictions);


        HashSet set = new HashSet();
        String divOrgStr = "";
        if(divOrgs != null && divOrgs.length > 0) {
            for(int j = 0; j < divOrgs.length; j++) {
                if(divOrgs[j] != null && divOrgs[j].length > 0) {
                	divOrgStr = "";
                    divOrgStr = " and vehicle.divOrg"+String.valueOf(j+1)+" in (";
                    for(int i = 0; i < divOrgs[j].length; i++) {
                    	set.add(divOrgs[j][i]); // Para evitar repetidos
                    }
                    Iterator it = set.iterator();
                    int i = 0;
                    while (it.hasNext()){
                    	String div = ((Integer)it.next()).toString();
                    	divOrgStr += div;
                        divOrgStr += (i == set.size()-1) ? ") " : ",";
                        i++;
                    }
                    //System.out.println(divOrgStr);
                    queryStr.append(divOrgStr);
                }
            }
        }

        if(divGeos != null) {
            if(divGeos[0] != null && divGeos[0].length > 0) {
                queryStr.append(" and vehicle.country in (");
                for(int i = 0; i < divGeos[0].length; i++) {
                    queryStr.append(divGeos[0][i]);
                    queryStr.append((i == divGeos[0].length-1) ? ")" : ",");
                }
            }
            if(divGeos[1] != null && divGeos[1].length > 0) {
                queryStr.append(" and vehicle.geoLevel1 in (");
                for(int i = 0; i < divGeos[1].length; i++) {
                    queryStr.append(divGeos[1][i]);
                    queryStr.append((i == divGeos[1].length-1) ? ")" : ",");
                }
            }
        }
        if(!Util.empty(type)) {
            queryStr.append("and vehicle.type = '"+type+"' ");
        }
        if(!Util.empty(trade)) {
            queryStr.append("and vehicle.trade = '"+trade+"' ");
        }
        if(!Util.empty(year)) {
            queryStr.append("and vehicle.year = "+year+" ");
        }
        if(!Util.empty(use)) {
            queryStr.append("and vehicle.use = '"+use+"' ");
        }
        if(!Util.empty(fuel)) {
            queryStr.append("and vehicle.fuel = '"+fuel+"' ");
        }

        if(!Util.empty(centerCost)) {
        	if (userSessionData.getFilter().getCorporationId().equals(new Integer(CorporationData.KLABIN))){
        		// queryStr.append(" and lower(vehicle.optionalData.prefix) like lower('%"+centerCost+"%')");
        	}else {
        		queryStr.append(" and lower(vehicle.costCenter) like lower('%"+centerCost+"%')");
        	}
        }


        if (!Util.empty(plate)){
        	queryStr.append(" and vehicle.matricula = '"+plate+"' ");
        }
        if (!Util.empty(model)){
        	queryStr.append(" and vehicle.model = '"+model+"' ");
        }
        switch(status) {
            case VehicleData.ACTIVE:
                queryStr.append(" and vehicle.exclusionDate is null");
                break;
            case VehicleData.EXCLUDED:
                queryStr.append(" and vehicle.exclusionDate is not null");
                break;
        }

        return queryStr.toString();

    }

    /**
     *
     * @param type
     * @param trade
     * @param year
     * @param use
     * @param fuel
     * @param centerCost
     * @param divOrgs
     * @param divGeos
     * @param first
     * @param count
     * @param userSessionData
     * @param plate
     * @param model
     * @param status
     * @return
     */
    private String getReportTrailerQuerySQL(String type, String trade, String year,String use, String fuel, String centerCost,
            Integer[][] divOrgs, Integer[][] divGeos, int first, int count,UserSessionData userSessionData,
            String plate, String model, int status) throws Exception {

    	StringBuffer queryStr = new StringBuffer();
        queryStr.append(UtilityQuery.corporationDivisionsRestictionsSQL("trailer",userSessionData));

        HashSet set = new HashSet();
        String divOrgStr = "";
        if(divOrgs != null && divOrgs.length > 0) {
            for(int j = 0; j < divOrgs.length; j++) {
                if(divOrgs[j] != null && divOrgs[j].length > 0) {
                	divOrgStr = "";
                    divOrgStr = " and trailer.divOrg"+String.valueOf(j+1)+" in (";
                    set = new HashSet();
                    for(int i = 0; i < divOrgs[j].length; i++) {
                    	set.add(divOrgs[j][i]); // Para evitar repetidos
                    }
                    Iterator it = set.iterator();
                    int i = 0;
                    while (it.hasNext()){
                    	String div = ((Integer)it.next()).toString();
                    	divOrgStr += div;
                        divOrgStr += (i == set.size()-1) ? ") " : ",";
                        i++;
                    }
                    //System.out.println(divOrgStr);
                    queryStr.append(divOrgStr);
                }
            }
        }

        if(divGeos != null) {
            if(divGeos[0] != null && divGeos[0].length > 0) {
                queryStr.append("and trailer.country in (");
                for(int i = 0; i < divGeos[0].length; i++) {
                    queryStr.append(divGeos[0][i]);
                    queryStr.append((i == divGeos[0].length-1) ? ")" : ",");
                }
            }
            if(divGeos[1] != null && divGeos[1].length > 0) {
                queryStr.append("and trailer.geoLevel1 in (");
                for(int i = 0; i < divGeos[1].length; i++) {
                    queryStr.append(divGeos[1][i]);
                    queryStr.append((i == divGeos[1].length-1) ? ")" : ",");
                }
            }
        }
        if(!Util.empty(trade)) {
            queryStr.append("and trailer.trade = '"+trade+"' ");
        }
        if(!Util.empty(year)) {
            queryStr.append("and trailer.year = "+year+" ");
        }


        if(!Util.empty(centerCost)) {
       		queryStr.append(" and lower(trailer.costCenter) like lower('%"+centerCost+"%')");
        }


        if (!Util.empty(plate)){
        	queryStr.append(" and trailer.plate = '"+plate+"' ");
        }
        if (!Util.empty(model)){
        	queryStr.append(" and trailer.model = '"+model+"' ");
        }
        switch(status) {
            case VehicleData.ACTIVE:
                queryStr.append(" and trailer.exclusionDate is null");
                break;
            case VehicleData.EXCLUDED:
                queryStr.append(" and trailer.exclusionDate is not null");
                break;
        }

        return queryStr.toString();
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     */
    public ListResult getReportVehiclesAndTrailers(String type, String trade, String year,String use, String fuel, String centerCost, Integer[][] divOrgs,
    		    Integer[][] divGeos, int first, int count,UserSessionData userSessionData,String plate, String model, int status) throws Exception {

    	try{
	        Session session = HibernateUtil.currentSession();

	        String queryVehicleSQL = getReportVehicleQuerySQL(type,trade,year,use,fuel,centerCost, divOrgs,divGeos,first,count,userSessionData,plate,model,status);
	        String queryTrailerSQL = getReportTrailerQuerySQL(type,trade,year,use,fuel,centerCost, divOrgs,divGeos,first,count,userSessionData,plate,model,status);

            StringBuffer querySql = new StringBuffer("SELECT SUM(c) AS total FROM (SELECT COUNT(*) AS c FROM vehicles AS vehicle ");
            querySql.append(queryVehicleSQL);
            querySql.append(" UNION (SELECT COUNT(*) AS c FROM trailers AS trailer ");
            querySql.append(queryTrailerSQL);
            querySql.append(")) AS p");

	        // Creo la consulta
	        SQLQuery query = session.createSQLQuery(querySql.toString()).addScalar("total",Hibernate.INTEGER);

	        // Realizo la consulta:
	        List userColl = query.list();
	        Iterator iter = userColl.iterator();
	        Integer total = new Integer(0);
	        // Calculo el total de registros:
	        while (iter.hasNext()) {
	            total = (Integer) iter.next();
	        }

	        String[] fields =  userSessionData.getFilter().getFields("vehicle");

	        // Realizo la consulta:
	        querySql = new StringBuffer("SELECT ");
            querySql.append("vehicle.id AS id, ");
            querySql.append("vehicle.matricula AS plate, ");
            querySql.append("country1.resourcekey AS country, ");
            querySql.append("vehicle.type AS type, ");
            querySql.append("vehicle.trade AS trade, ");
            querySql.append("vehicle.model AS model,");
            querySql.append("vehicle.year AS year, ");
            querySql.append("vehicle.colour AS colour,");
            querySql.append("vehicle.permissionNumber AS permissionNumber,");
            querySql.append("vehicle.use as use, ");
            querySql.append("vehicle.permissionExpiry as permissionExpiry,");
            querySql.append("vehicle.suctaExpiry as suctaExpiry, ");
            querySql.append("vehicle.mtopExpiry as mtopExpiry,");
            querySql.append("vehicle.ownerCard as ownerCard,");
            querySql.append("vehicle.ensuranceType as ensuranceType,");
            querySql.append("vehicle.ensurance as ensurance, ");
            querySql.append("vehicle.observations as observations, ");
            querySql.append("vehicle.fuel as fuel, ");
            querySql.append("vehicle.purchaseDate as purchaseDate, ");
            querySql.append("vehicle.chasisNumber as chasisNumber,");
            querySql.append("vehicle.axis as axis, ");
            querySql.append("vehicle.engine as engine, ");
            querySql.append("vehicle.hp as hp, ");
            querySql.append("vehicle.tankCapacity as tankCapacity,");
            querySql.append("vehicle.tankUnit as tankUnit, ");
            querySql.append("vehicle.abs as abs,");
            querySql.append("vehicle.airBag as airBag, ");
            querySql.append("vehicle.aireAcondicionado as aireAcondicionado,");
            querySql.append("vehicle.cover as cover,");
            querySql.append("vehicle.odometerUnit as odometerUnit,");
            querySql.append("driver.firstName as driverFirstName, ");
            querySql.append("driver.lastName as driverLastName, ");
            querySql.append("info.prefix as prefix, ");
            querySql.append("info.monitoringSystem as monitoringSystem, ");
            querySql.append("info.renavan as renavan, ");
            querySql.append("vehicle.costcenter as costcenter, ");
            querySql.append("driver.corporationId as wwwid, ");
            querySql.append("vehicle.suspension as suspension, ");
            querySql.append("vehicle.exclusiondate as exclusiondate ");
            querySql.append(fields[0]);

            querySql.append(" FROM vehicles as vehicle ");
            querySql.append("LEFT JOIN drivers as driver ON vehicle.driver = driver.id ");
            querySql.append("LEFT JOIN vehiclesoptionalinfo as info ON vehicle.optionalInfo = info.id");
            querySql.append(", country as country1 ");
            querySql.append(fields[1]);

            // Where
            querySql.append(queryVehicleSQL).append(fields[2]).append(" AND country1.id = vehicle.country ");

            String[] fieldsTrailer =  userSessionData.getFilter().getFields("trailer");
            // Union Trailers:
            querySql.append(" UNION (SELECT ");
            querySql.append("trailer.id as id, ");
            querySql.append("trailer.plate as plate, ");
            querySql.append("country2.resourcekey as country, ");
            querySql.append("null as type, ");
            querySql.append("trailer.trade as trade, ");
            querySql.append("trailer.model as model,");
            querySql.append("trailer.year as year, ");
            querySql.append("trailer.colour as colour,");
            querySql.append("trailer.permissionNumber as permissionNumber,");
            querySql.append("null as use, ");
            querySql.append("trailer.permissionExpiry as permissionExpiry,");
            querySql.append("trailer.suctaExpiry as suctaExpiry, ");
            querySql.append("trailer.mtopExpiry as mtopExpiry,");
            querySql.append("null as ownerCard,");
            querySql.append("trailer.ensuranceType as ensuranceType,");
            querySql.append("trailer.ensurance as ensurance, ");
            querySql.append("null as observations, ");
            querySql.append("null as fuel, ");
            querySql.append("trailer.purchaseDate as purchaseDate, ");
            querySql.append("trailer.chasisNumber as chasisNumber,");
            querySql.append("trailer.axis as axis, ");
            querySql.append("null as engine, ");
            querySql.append("null as hp, ");
            querySql.append("null as tankCapacity,");
            querySql.append("null as tankUnit, ");
            querySql.append("null as abs,");
            querySql.append("null as airBag, ");
            querySql.append("null as aireAcondicionado,");
            querySql.append("trailer.cover as cover,");
            querySql.append("null as odometerUnit,");
            querySql.append("'' as driverFirstName, ");
            querySql.append("'' as driverLastName, ");
            querySql.append("null as prefix, ");
            querySql.append("null as monitoringSystem, ");
            querySql.append("null as renavan, ");
            querySql.append("null as costcenter, ");
            querySql.append("null as wwwid, ");
            querySql.append("null as suspension, ");
            querySql.append("trailer.exclusiondate as exclusiondate ");

            querySql.append(fieldsTrailer[0]);

            querySql.append(" FROM trailers as trailer , country as country2 ");
            querySql.append(fieldsTrailer[1]);

            // Where
            querySql.append(queryTrailerSQL).append(fieldsTrailer[2]).append(" and country2.id = trailer.country ");

            querySql.append(") ORDER BY plate ");



            // Creo la consulta con los tipos de cada campo a traer:
	        query = session.createSQLQuery(querySql.toString()).
	        	addScalar("id",Hibernate.INTEGER).
	        	addScalar("plate",Hibernate.STRING).
	        	addScalar("country",Hibernate.STRING).
                addScalar("type",Hibernate.STRING).
            	addScalar("trade",Hibernate.STRING).
            	addScalar("model",Hibernate.STRING).
            	addScalar("year",Hibernate.INTEGER).
            	addScalar("colour",Hibernate.STRING).
            	addScalar("permissionNumber",Hibernate.STRING).
            	addScalar("use",Hibernate.STRING).
            	addScalar("permissionExpiry",Hibernate.DATE).
            	addScalar("suctaExpiry",Hibernate.DATE).
            	addScalar("mtopExpiry",Hibernate.DATE).
            	addScalar("ownerCard",Hibernate.STRING).
            	addScalar("ensuranceType",Hibernate.STRING).
            	addScalar("ensurance",Hibernate.STRING).
            	addScalar("observations",Hibernate.STRING).
            	addScalar("fuel",Hibernate.STRING).
            	addScalar("purchaseDate",Hibernate.DATE).
            	addScalar("chasisNumber",Hibernate.STRING).
            	addScalar("axis",Hibernate.INTEGER).
            	addScalar("engine",Hibernate.STRING).
            	addScalar("hp",Hibernate.STRING).
            	addScalar("tankCapacity",Hibernate.INTEGER).
            	addScalar("tankUnit",Hibernate.STRING).
            	addScalar("abs",Hibernate.BOOLEAN).
            	addScalar("airBag",Hibernate.BOOLEAN).
            	addScalar("aireAcondicionado",Hibernate.BOOLEAN).
            	addScalar("cover",Hibernate.STRING).
            	addScalar("odometerUnit",Hibernate.STRING).
            	addScalar("driverFirstName",Hibernate.STRING).
                addScalar("driverLastName",Hibernate.STRING).
                addScalar("prefix",Hibernate.STRING).
                addScalar("monitoringSystem",Hibernate.STRING).
                addScalar("renavan",Hibernate.STRING).
                addScalar("costcenter",Hibernate.STRING).
                addScalar("wwwid",Hibernate.STRING).
            	addScalar("suspension",Hibernate.BOOLEAN).
        		addScalar("exclusiondate",Hibernate.DATE);

            StringTokenizer divorgs = new StringTokenizer(fields[3],",");
            while (divorgs.hasMoreTokens()){
                query.addScalar(divorgs.nextToken(),Hibernate.STRING);
            }

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

	        // Realizo la consulta:
	        userColl = query.list();
	        Collection result = new LinkedList();
	        iter = userColl.iterator();


	        if(userSessionData.getFilter().getCorporationId().intValue() == CorporationData.MONSANTO) {
		        String ids = "0";
		        // Armo la coleccion del resultado:
		        Hashtable hash = new Hashtable();
		        while (iter.hasNext()) {
		        	VehicleDataText v = new VehicleDataText((Object []) iter.next());
		            if(v.getType() == null) {
			            hash.put("v_"+v.getId(),v);
		            	ids += ","+v.getId();
		            }else {
			            hash.put("t_"+v.getId(),v);
		            }
		        }

		        String sql2 = "SELECT vehicle,odometer FROM odometers WHERE vehicle IN ("+ids+") ORDER BY year DESC,period DESC";
	        	Query q2 = session.createSQLQuery(sql2).addScalar("vehicle", Hibernate.INTEGER).addScalar("odometer", Hibernate.INTEGER);
	        	Iterator it = q2.list().iterator();
	        	while(it.hasNext()) {
	        		Object[] data = (Object[])it.next();
	        		VehicleDataText v = (VehicleDataText)hash.get("v_"+String.valueOf(data[0]));
	        		if(v.getOdometer() == null) {
	        			v.setOdometer((Integer)data[1]);
	        		}
	        	}

	        	Enumeration en = hash.elements();
	        	while(en.hasMoreElements()) {
	        		result.add(en.nextElement());
	        	}
	        }else {
		        // Armo la coleccion del resultado:
		        while (iter.hasNext()) {
		            result.add(new VehicleDataText((Object []) iter.next()));
		        }
	        }
	        session.close();

	        return new ListResult(result,total.intValue());
    	}catch(Exception e){
    		Object[] parameters = {type,trade,year,use,fuel,centerCost,divOrgs,divGeos,first,count,plate,model,status};
    		handler.getException(e,"getReportVehiclesAndTrailers",userSessionData,parameters);
    	}
    	return null;
    }

    /**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,fleetupdate,fleetedit,fleetinsertdata"
     *
     * Devuelve el odometro que tiene ingresado un vehiculo
     * para una flota dada en un periodo y año dados y en el historial
     * del odometerData trae el odometro
     * De no haber datos devuelve NULL.
     *
     */
    public OdometerData getVehicleOdometer(Integer fleetId, Integer vehicleId, Integer year,
        Integer period, UserSessionData userRequest) throws Exception {

        try{
            Session session = HibernateUtil.currentSession();
            OdometerData odometerData;


            Integer lastperiod, lastyear;
            if (period.equals(new Integer(1))){
                // Si el periodo requerido es enero, traigo tambien en el historico el
                // odometro de diciembre del año anterior
                lastperiod = new Integer(12);
                lastyear = new Integer(year.intValue()-1);
            }else{
                // si el periodo es de 2 a 12:
                lastperiod = new Integer(period.intValue()-1);
                lastyear = year;
            }


            String sql = "select o from Odometer as o  "+
                    "where o.pk.vehicle = :vehicleId and o.pk.fleet = :fleetId "+
                    "and ((o.pk.period = :period and o.pk.year = :year ) or " +
                    "(o.pk.period = :lastperiod and o.pk.year = :lastyear )) " +
                    "ORDER BY o.pk.year, o.pk.period DESC ";

            Query query = session.createQuery(sql);
            query.setParameter("vehicleId", vehicleId);
            query.setParameter("fleetId", fleetId);
            query.setParameter("period", period);
            query.setParameter("year", year);
            query.setParameter("lastyear", lastyear);
            query.setParameter("lastperiod", lastperiod);

            List result = query.list();
            if (result.isEmpty()){
                return null;
            }else{
                Iterator it = result.iterator();
                odometerData = new OdometerData();
                while (it.hasNext()){
                    Odometer odometer =((Odometer)it.next());
                    if (odometer.getPk().getPeriod().equals(period)
                            && odometer.getPk().getYear().equals(year)){
                        odometerData = odometer.getData();
                    }else{
                        odometerData.addPrevious(odometer.getPk().getPeriod(),
                                odometer.getPk().getYear(), odometer.getOdometer());
                    }
                }
                if (odometerData.getOdometer() != null && odometerData.getOdometer().intValue() != 0){
                    return odometerData;
                }else{
                    // Si solo hay odometro para el periodo anterior y no para este periodo
                    // el odometerData va a estar vacio por el while anterior.
                    return null;
                }

            }
        }
        catch(Exception e){
        	Object[] parameters = {fleetId,vehicleId,year,period};
        	handler.getException(e,"getVehicleOdometer",userRequest,parameters);
        }
        return null;

    }


    /**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,fleetupdate,fleetedit,fleetinsertdata"
     */
    public Collection getVehicleFleetObservations(Integer fleetId,
            Integer vehicleId, UserSessionData userRequest) throws Exception {
         try{

             Session session = HibernateUtil.currentSession();

             String queryStr = "select log from VehicleFleetLog as log "+
                 " where log.pk.fleetId = (:fleetId) and log.vehicleId = (:vehicleId) " +
                 " order by log.pk.date desc ";

             Query query = session.createQuery(queryStr);
             query.setParameter("fleetId",fleetId);
             query.setParameter("vehicleId",vehicleId);
             query.setFirstResult(0);
             query.setMaxResults(1);

             List userColl = query.list();
             List result = new LinkedList();
             Iterator iter = userColl.iterator();
             while (iter.hasNext()) {
                VehicleFleetLog log = (VehicleFleetLog) iter.next();
                 result.add(log.getVehicleFleetObservations());
             }

             return result;

        }catch (Exception e) {
        	Object[] parameters = {fleetId,vehicleId};
            handler.getException(e,"getVehicleFleetObservations",userRequest,parameters);
        }
        return null;
    }

    /**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,fleetupdate,fleetedit,fleetinsertdata"
     *
     * Devuelve los vehiculos que pertenecen a las divisiones de una Flota (fleetid)
     * pero que no pertenecen actualmente a la Flota.
     */
    public Collection findVehiclesByFleet(Integer fleetId, UserSessionData userSessionData)throws Exception {
        Collection list = new LinkedList();
        try{
            Session session = HibernateUtil.currentSession();
            String queryStr = "select vehicle from Vehicle as vehicle" +
                       UtilityQuery.corporationDivisionsRestictions("vehicle",userSessionData);

            IndividualFleet fleet = (IndividualFleet)session.load(IndividualFleet.class,fleetId);
            if(fleet.getDivisionSet().size() > 0) {
                queryStr += " and (";
                Iterator it = fleet.getDivisionSet().iterator();
                boolean first = true;
                while(it.hasNext()) {
                    FleetDivision fleetDivision = (FleetDivision)it.next();
                    if(first) {
                        first = false;
                        queryStr += "(";
                    }else {
                        queryStr += " or (";
                    }
                    queryStr += " vehicle.corpCountry.pk.country in ("+fleetDivision.getDivGeoString(userSessionData)+") ";
                    for(int i = 0; i < userSessionData.getFilter().getDivOrgCount(); i++) {
                        String label = fleetDivision.getDivOrgString(i+1,userSessionData);
                        if(!Util.empty(label)) {
                            queryStr += " and vehicle.divOrg"+(i+1)+" in ("+label+")";
                        }
                    }
                    queryStr += ")";
                }
                queryStr += " )";

                if(((IndividualFleet)fleet).getVehicleSet().size() > 0) {
                    String notin = "";
                    it = ((IndividualFleet)fleet).getVehicleSet().iterator();
                    while(it.hasNext()) {
                        if(Util.empty(notin)) {
                            notin += String.valueOf(((Vehicle)it.next()).getId());
                        }else {
                            notin += ","+String.valueOf(((Vehicle)it.next()).getId());
                        }
                    }
                    queryStr += " and vehicle.id not in ("+notin+")";
                }

                Query query = session.createQuery(queryStr);
                List vehicleColl = query.list();
                it = vehicleColl.iterator();
                while(it.hasNext()) {
                    Vehicle vehicle = (Vehicle) it.next();
                    list.add(vehicle.getPrincipalAttributes());
                }
            }

        } catch (Exception e) {
        	Object[] parameters = {fleetId};
            handler.getException(e, "findVehiclesByFleet", userSessionData, parameters);
        }
        return list;
  }

    /**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,fleetupdate,fleetedit,fleetinsertdata"
     *
     * Devuelve los vehiculos que pertenecen a las divisiones de una Flota (fleetid)
     * pero que no pertenecen actualmente a la Flota.
     */
    public Collection findVehiclesByFleetNew(Integer fleetId, UserSessionData userSessionData)throws Exception {
        try{
            Session session = HibernateUtil.currentSession();
            String queryStr = "SELECT id,matricula FROM vehicles AS v " +
                            " WHERE v.corporation = "+userSessionData.getFilter().getCorporationId();

            Collection<Integer> countries = new LinkedList<Integer>();
            Collection<Integer> divOrg1s = new LinkedList<Integer>();
            Collection<Integer> divOrg2s = new LinkedList<Integer>();
            Collection<Integer> divOrg3s = new LinkedList<Integer>();

            Collection<Integer> countryIds = userSessionData.getFilter().getDivGeoLeavesCollection(null, null);
            Collection<Integer>[] ids = userSessionData.getFilter().getDivOrgLeaveCollection();
            Collection<Integer> divOrg1Ids = ids[0];
            Collection<Integer> divOrg2Ids = ids[1];
            Collection<Integer> divOrg3Ids = ids[2];

            Query q = session.createSQLQuery("SELECT DISTINCT country FROM fleetdivisions WHERE fleet = "+String.valueOf(fleetId)).addScalar("country",Hibernate.INTEGER);
            Iterator it = q.list().iterator();
            while(it.hasNext()) {
                Integer countryId = (Integer)it.next();
                if(countryIds.contains(countryId)) {
                    countries.add(countryId);
                }
            }
            if(countries.size() == 0) {
            	return new LinkedList();
            }
            queryStr += " AND v.country IN ("+getString(countries)+") ";

            if(userSessionData.getFilter().getDivOrgCount() >= 1) {
                q = session.createSQLQuery("SELECT DISTINCT divorg11 AS id FROM fleetdivisions WHERE fleet = "+String.valueOf(fleetId)).addScalar("id",Hibernate.INTEGER);
                it = q.list().iterator();
                while(it.hasNext()) {
                    Integer divOrgId = (Integer)it.next();
                    if(divOrg1Ids.contains(divOrgId)) {
                        divOrg1s.add(divOrgId);
                    }
                }
                queryStr += " AND v.divorg1 IN ("+getString(divOrg1s)+") ";
            }
            if(userSessionData.getFilter().getDivOrgCount() >= 2) {
                q = session.createSQLQuery("SELECT DISTINCT divorg21 AS id FROM fleetdivisions WHERE fleet = "+String.valueOf(fleetId)).addScalar("id",Hibernate.INTEGER);
                it = q.list().iterator();
                while(it.hasNext()) {
                    Integer divOrgId = (Integer)it.next();
                    if(divOrg2Ids.contains(divOrgId)) {
                        divOrg2s.add(divOrgId);
                    }
                }
                queryStr += " AND v.divorg2 IN ("+getString(divOrg2s)+") ";
            }
            if(userSessionData.getFilter().getDivOrgCount() >= 3) {
                q = session.createSQLQuery("SELECT DISTINCT divorg31 AS id FROM fleetdivisions WHERE fleet = "+String.valueOf(fleetId)).addScalar("id",Hibernate.INTEGER);
                it = q.list().iterator();
                while(it.hasNext()) {
                    Integer divOrgId = (Integer)it.next();
                    if(divOrg3Ids.contains(divOrgId)) {
                        divOrg3s.add(divOrgId);
                    }
                }
                queryStr += " AND v.divorg3 IN ("+getString(divOrg3s)+") ";
            }

            q = session.createSQLQuery("SELECT vehicle FROM vehiclefleets WHERE id = "+String.valueOf(fleetId)).addScalar("vehicle",Hibernate.INTEGER);
            it = q.list().iterator();
            if(it.hasNext()) {
                queryStr += " AND v.id NOT IN ("+String.valueOf(it.next());
                while(it.hasNext()) {
                    queryStr += ","+String.valueOf(it.next());
                }
                queryStr += ") ";
            }
            queryStr += " AND v.exclusiondate IS null";

             Query query = session.createSQLQuery(queryStr).addScalar("id",Hibernate.INTEGER).addScalar("matricula",Hibernate.STRING);
             return query.list();

        } catch (Exception e) {
        	Object[] parameters = {fleetId};
            handler.getException(e, "findVehiclesByFleetNew", userSessionData, parameters);
        }
        return null;
  }


    /**
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws CommunicationProblemException
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,dpachecklist,drivervehicleedit,drivercreateevent,vehiclechecklist,jjreporter"
     */
    public CheckListData findVehicleCheckList(UserSessionData userRequest) throws Exception {
        try {
            if (userRequest == null) {
                throw new DeslogedException("findVehicleCheckList","session is null");
            }

            Session session = HibernateUtil.currentSession();
            CheckList checkList = null;
            String sql = "select checklist from CheckList as checklist where goals = '"
                    + "Vehicle Checklist'";

            Query query = session.createQuery(sql);
            Object result = query.uniqueResult();
            if(result != null) {
                checkList = (CheckList)result;
                return checkList.getCheckListData(userRequest.getCorporationId());
            }

        } catch (Exception e) {
        	Object[] parameters = {};
            handler.getException(e, "findVehicleCheckList", userRequest, parameters);
        }

        return null;
    }

    /**
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws CommunicationProblemException
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,drivervehicleedit,drivercreateevent,vehiclechecklist"
     */
    public CheckListData findVehicleCheckListNalco(UserSessionData userRequest) throws Exception {
        try {
            if (userRequest == null) {
                throw new DeslogedException("findVehicleCheckListNalco","session is null");
            }

            Session session = HibernateUtil.currentSession();
            CheckList checkList = null;
            String sql = "select checklist from CheckList as checklist where goals = "
                    + " 'Vehicle Checklist Nalco' ";

            Query query = session.createQuery(sql);
            Object result = query.uniqueResult();
            if(result != null) {
                checkList = (CheckList)result;
                return checkList.getCheckListData(userRequest.getCorporationId());
            }

        } catch (Exception e) {
        	Object[] parameters = {};
            handler.getException(e, "findVehicleCheckListNalco", userRequest, parameters);
        }

        return null;
    }

    /**
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws CommunicationProblemException
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,drivervehicleedit,drivercreateevent"
     */
    public VehicleData findVehicleByPlate(String plate,UserSessionData userRequest) throws Exception {
        VehicleData data = null;
        try {

        	if (userRequest == null) {
        		throw new DeslogedException("findVehicleByPlate","session is null");
        	}

        	Session session = HibernateUtil.currentSession();

            String sql = "select vehicle from Vehicle as vehicle  "+
                    UtilityQuery.corporationDivisionsRestictions("vehicle",userRequest)+
                    "and vehicle.matricula = '" + plate + "'";
/*            if(userRequest.isDriver()) {
                sql += " and vehicle.driver is not null and vehicle.driver.id = "+String.valueOf(userRequest.getDriverData().getId());
            }
*/
            Query query = session.createQuery(sql);

            //query.setParameter("plate", plate);
            List vehicleColl = query.list();
            //Object result = query.uniqueResult();
            //if(result != null) {
            if (vehicleColl.iterator().hasNext()) {
                Vehicle vehicle = (Vehicle)vehicleColl.iterator().next();
                data = vehicle.getVehicleData();
            }

        } catch (Exception e) {
        	Object[] parameters = {plate};
            handler.getException(e, "findVehicleByPlate", userRequest, parameters);
        }

        return data;
    }

    /**
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws CommunicationProblemException
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,drivervehicleedit,drivercreateevent, systemaccess"
     */
    public VehicleData findVehicleByPlateInCorporation(String plate,UserSessionData userRequest) throws Exception {
        VehicleData data = null;
        try {

        	if (userRequest == null) {
        		throw new DeslogedException("findVehicleByPlate","session is null");
        	}

        	Session session = HibernateUtil.currentSession();

            String sql = "select vehicle from Vehicle as vehicle  "+
            		"where vehicle.corpCountry.pk.corporation.id = "+userRequest.getFilter().getCorporationId()+
                    "and vehicle.matricula = '" + plate + "'";
/*            if(userRequest.isDriver()) {
                sql += " and vehicle.driver is not null and vehicle.driver.id = "+String.valueOf(userRequest.getDriverData().getId());
            }
*/
            Query query = session.createQuery(sql);

            //query.setParameter("plate", plate);
            List vehicleColl = query.list();
            //Object result = query.uniqueResult();
            //if(result != null) {
            if (vehicleColl.iterator().hasNext()) {
                Vehicle vehicle = (Vehicle)vehicleColl.iterator().next();
                data = vehicle.getVehicleData();
            }

        } catch (Exception e) {
        	Object[] parameters = {plate};
            handler.getException(e, "findVehicleByPlate", userRequest, parameters);
        }

        return data;
    }

    /**
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws CommunicationProblemException
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,drivervehicleedit,drivercreateevent, systemaccess"
     */
    public VehicleData[] findVehiclesByPlateInCorporation(String plate,UserSessionData userSessionData) throws Exception {
        Collection<VehicleData> data = new LinkedList<VehicleData>();
        try {

        	if (userSessionData == null) {
        		throw new DeslogedException("findVehicleByPlate","session is null");
        	}

        	Session session = HibernateUtil.currentSession();

        	String corporation = String.valueOf(userSessionData.getFilter().getCorporationId());
        	if(userSessionData.isCorporation(CorporationData.FORESTALORIENTAL)) {
        		corporation += ", "+CorporationData.UPM_CELULOSA+", "+CorporationData.SOCTMA;
        	}
            String sql = "SELECT vehicle FROM Vehicle AS vehicle  "+
            		"WHERE vehicle.corpCountry.pk.corporation.id IN ("+corporation+") AND vehicle.matricula = '" + plate + "'";
        	if(userSessionData.isCorporation(CorporationData.FORESTALORIENTAL)) {
        		sql += " AND vehicle.exclusionDate IS NULL";
        	}
            Query query = session.createQuery(sql);
            Iterator it = query.list().iterator();
            while (it.hasNext()) {
                data.add(((Vehicle)it.next()).getVehicleData());
            }

        } catch (Exception e) {
        	Object[] parameters = {plate};
            handler.getException(e, "findVehiclesByPlateInCorporation", userSessionData, parameters);
        }

        return (VehicleData[])data.toArray(new VehicleData[0]);
    }

    /**
     * Devuelve un listado de vehiculos cuya matricula sea "plate"
     * y que pertenezcan a la corporaraciòn indicada en el userRequest.
     *
     * "With Repeats" significa que devuelve todos los que encuentre con esa placa, no solo el primero.
     *
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,drivervehicleedit,drivercreateevent,eventupload"
     */
    public ListResult findVehicleByPlateWithRepeats(String plate, UserSessionData userRequest) throws Exception {

    	ListResult result = null;
    	Collection vehicles = null;
    	try {

        	Session session = HibernateUtil.currentSession();

            String sql = "select vehicle from Vehicle as vehicle  "+
                    UtilityQuery.corporationDivisionsRestictions("vehicle",userRequest)+
                    "and vehicle.matricula = '" + plate + "'";

            Query query = session.createQuery(sql);
            List vehicleColl = query.list();

            if (vehicleColl != null && vehicleColl.size() > 0){
            	Iterator it = vehicleColl.iterator();
            	vehicles = new LinkedList<VehicleData>();
            	while (it.hasNext()){
            		Vehicle v = (Vehicle)it.next();
            		vehicles.add(v.getVehicleData());
            	}
            	result = new ListResult(vehicles,vehicles.size());
            }

        } catch (Exception e) {
        	Object[] parameters = {plate};
            handler.getException(e, "findVehicleByPlateWithRepeats", userRequest, parameters);
        }

        return result;
    }

    /**
     * Devuelve un listado de vehiculos cuya matricula sea "plate"
     * y que pertenezcan a la corporaraciòn indicada en el userRequest.
     *
     * "With Repeats" significa que devuelve todos los que encuentre con esa placa, no solo el primero.
     *
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,drivervehicleedit,drivercreateevent,eventupload"
     */
    public ListResult findVehiclesByPlate(String plate, UserSessionData userRequest) throws Exception {

    	ListResult result = null;
    	Collection vehicles = new LinkedList<VehicleData>();;
    	try {

        	Session session = HibernateUtil.currentSession();

            String sql = "SELECT id FROM vehicles v "+UtilityQuery.corporationDivisionsRestictionsSQL("v",userRequest)+" AND matricula = '" + plate + "'";

            Query query = session.createSQLQuery(sql).addScalar("id", Hibernate.INTEGER);
            List vehicleColl = query.list();

            if (vehicleColl != null && vehicleColl.size() > 0){
            	Iterator it = vehicleColl.iterator();
            	while (it.hasNext()){
            		vehicles.add(new VehicleData((Integer)it.next()));
            	}
            	result = new ListResult(vehicles,vehicles.size());
            }

        } catch (Exception e) {
        	Object[] parameters = {plate};
            handler.getException(e, "findVehiclesByPlate", userRequest, parameters);
        }

        return result;
    }


    /**
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws CommunicationProblemException
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,dpachecklist,finecreate,drivervehicleedit,drivercreateevent"
     */
    public VehicleCheckData findLastVehicleCheck(Integer id,UserSessionData userRequest) throws Exception {
    	VehicleCheckData data = null;

        try {
        	if (id == null) {
        		throw new InvalidDataException("findLastVehicleCheck","id is null");
        	}
        	if (userRequest == null) {
        		throw new DeslogedException("findLastVehicleCheck","session is null");
        	}

        	Session session = HibernateUtil.currentSession();

            String sql = "select vehicleCheck from VehicleCheck as vehicleCheck  "+
                    UtilityQuery.corporationDivisionsRestictions("vehicleCheck.vehicle",userRequest)+
                    "and vehicleCheck.vehicle.id = :id " +
                    " order by vehicleCheck.date desc";
/*            if(userRequest.isDriver()) {
                sql += " and vehicle.driver is not null and vehicle.driver.id = "+String.valueOf(userRequest.getDriverData().getId());
            }
*/
            Query query = session.createQuery(sql);

            query.setParameter("id", id);
            List vehicleColl = query.list();
            //Object result = query.uniqueResult();
            //if(result != null) {
            if (vehicleColl.iterator().hasNext()) {
                VehicleCheck vehicleCheck = (VehicleCheck)vehicleColl.iterator().next();

                // accedo al conjunto de items para que la inicializaciòn lazy no falle
                Iterator it = vehicleCheck.getItemValueSet().iterator();
                while (it.hasNext()) {
                    it.next();
                }
                data = vehicleCheck.getVehicleCheckData();
            }

        } catch (Exception e) {
        	Object[] parameters = {id};
            handler.getException(e, "findLastVehicleCheck", userRequest, parameters);
        }

        return data;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,drivervehicleedit,drivercreateevent"
     */
    public Date findLastVehicleCheckDate(Integer id,UserSessionData userRequest) throws Exception {
        Date vehicleCheckDate = null;

        try {
        	if (id == null) {
        		throw new InvalidDataException("findLastVehicleCheckDate","id is null");
        	}
        	if (userRequest == null) {
        		throw new DeslogedException("findLastVehicleCheckDate","session is null");
        	}

        	Session session = HibernateUtil.currentSession();

            String sql = "select vehicleCheck.date from VehicleCheck as vehicleCheck  "+
                    UtilityQuery.corporationDivisionsRestictions("vehicleCheck.vehicle",userRequest)+
                    "and vehicleCheck.vehicle.id = :id " +
                    " order by vehicleCheck.date desc";

            Query query = session.createQuery(sql);

            query.setParameter("id", id);
            List vehicleColl = query.list();
            if (vehicleColl.iterator().hasNext()) {
                vehicleCheckDate = (Date)vehicleColl.iterator().next();
            }

        } catch (Exception e) {
        	Object[] parameters = {id};
            handler.getException(e, "findLastVehicleCheckDate", userRequest, parameters);
        }

        return vehicleCheckDate;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,dpachecklist,drivervehicleedit,drivercreateevent,vehiclechecklist,vehicleview"
     */
    public ArrayList<VehicleCheckResumeData> findVehicleCheckReportResume(Integer startYear, Integer endYear,Integer startMonth,Integer endMonth,UserSessionData userSessionData) throws Exception {
    	ArrayList<VehicleCheckResumeData> res = new ArrayList<VehicleCheckResumeData>();
        try {
        	if (userSessionData == null) {
        		throw new DeslogedException("findVehicleCheckReportResume","userSessionData = null");
        	}
        	Session session = HibernateUtil.currentSession();
            String sql = " select vehicleCheck.vehiclecheckid, vehicleCheck.date,vehicleCheck.result,vehicle.divOrg1 from vehiclecheck as vehicleCheck " +
            		" inner join vehicles as vehicle on( vehicle.id = vehicleCheck.vehicle )  "+
                    UtilityQuery.corporationDivisionsRestictionsSQL("vehicle",userSessionData) +
                    " and vehicleCheck.date >=(:sd) and vehicleCheck.date <(:ed) order by date desc ";

            Query query = session.createSQLQuery(sql);//

            query.setParameter("sd", Util.floorDateMonthDPA(startYear, startMonth));
            query.setParameter("ed", Util.topDateMonthDPA(endYear, endMonth));

            Iterator iter = query.list().iterator();
            while(iter.hasNext()){
            	Object[] row=(Object[])iter.next();
            	Integer id=(Integer) row[0];
            	Date date=(Date) row[1];
            	Integer result=(Integer) row[2];
            	Integer divOrg1=(Integer) row[3];

            	VehicleCheckResumeData vc=new VehicleCheckResumeData();
            	vc.setResult(result);

            	vc.setId(id);

            	if(divOrg1!=null && divOrg1>0){
            		DivOrgItemLevel1 divOrg=(DivOrgItemLevel1)session.load(DivOrgItemLevel1.class, divOrg1);
            		if(divOrg!=null){
            			if(divOrg.getDivOrgItemLevel2()==null){
            				vc.setDivOrgItem1(divOrg.getResourceKey());
            			}else if(divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3()==null){
            				vc.setDivOrgItem1(divOrg.getResourceKey());
                			vc.setDivOrgItem2(divOrg.getDivOrgItemLevel2().getResourceKey());
                		}else{
                			vc.setDivOrgItem1(divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey());
                			vc.setDivOrgItem2(divOrg.getDivOrgItemLevel2().getResourceKey());
                			vc.setDivOrgItem3(divOrg.getResourceKey());
                		}
            		}
        		 }

            	Calendar cal=Calendar.getInstance();
            	if(vc.getDivOrgItem1().equals("T1")){
	            	cal.setTime(date);
	            	boolean pasa=false;


	            	/*Junho: 26/05/2014 a 25/06/2014.

	            	Julho: 26/06/2014  a 24/07/2014.

	            	Agosto : 25/07/2014 a 24/08/2014.
	            	*/

	            	int day=Util.dpaDayBreak(cal);

	            	if(cal.get(Calendar.DATE)>=day){
	            		cal.add(Calendar.MONTH, 1);
	            		cal.set(Calendar.DATE, 1);
	            		pasa=true;
	            	}
	            	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM");
	            	vc.setDate(Util.formatDate(cal.getTime()));
	            	if(!(pasa && cal.get(Calendar.MONTH)>(endMonth-1))){
	            		if( (cal.get(Calendar.MONTH)<=(endMonth-1) && cal.get(Calendar.YEAR)==endYear) || (cal.get(Calendar.MONTH)>(endMonth-1) && cal.get(Calendar.YEAR)<endYear)){
	            			res.add(vc);
	            		}
	            	}//Si no esta pasado del 26 del mes en cuestion
	            }else if(vc.getDivOrgItem1().equals("T2")){
	            	cal.setTime(date);
	            	boolean pasa=false;
            		if(((cal.get(Calendar.MONTH)<= (endMonth-1) && cal.get(Calendar.YEAR)==endYear) || (cal.get(Calendar.MONTH)>(startMonth-1) && cal.get(Calendar.YEAR)<endYear))){
		            	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM");
		            	vc.setDate(Util.formatDate(cal.getTime()));
	            		res.add(vc);
            		}

	            }
            }

            return res;
        } catch (Exception e) {
        	Object[] parameters = {userSessionData,startMonth,endMonth};
            handler.getException(e,"findVehicleCheckReportResume",userSessionData,parameters);
        }
        return new ArrayList<VehicleCheckResumeData>();
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,dpachecklist,drivervehicleedit,drivercreateevent,vehiclechecklist,vehicleview"
     */
    public ArrayList<VehicleCheckReportGroupData> findVehicleCheckReportResumeGroupItems(Integer startYear, Integer endYear,Integer startMonth,Integer endMonth,UserSessionData userSessionData) throws Exception {
    	ArrayList<VehicleCheckReportGroupData> res = new ArrayList<VehicleCheckReportGroupData>();
        try {
        	if (userSessionData == null) {
        		throw new DeslogedException("findVehicleCheckReportResume","userSessionData = null");
        	}
        	Session session = HibernateUtil.currentSession();
            String sql = " select to_date(to_char(vehicleCheck.date, 'YYYY/MM/DD'), 'YYYY/MM/DD')  as dateStr,vehicle.divOrg1,m.text,item.itemValue, count(*) as count "+
            		" from vehiclecheck as vehicleCheck "+
            		" inner join vehicles as vehicle on( vehicle.id = vehicleCheck.vehicle ) "+
            		" inner join vehiclecheckitemsvalues as item on (item.vehiclecheck=vehicleCheck.vehiclecheckid) "+
            		" inner join items as i on (i.id=item.item) "+
            		" inner join generalmessages as m on (m.labelKey=i.text and m.language='pt') "+
            		"  " +UtilityQuery.corporationDivisionsRestictionsSQL("vehicle",userSessionData)+
            		" and item.itemValue in ('1','2')  and  to_date(to_char(vehicleCheck.date, 'YYYY/MM/DD'), 'YYYY/MM/DD') >=(:sd) and to_date(to_char(vehicleCheck.date, 'YYYY/MM/DD'), 'YYYY/MM/DD') <(:ed) " +
            		" group by dateStr,vehicle.divOrg1,m.text,item.itemValue "+
            		" order by dateStr asc ,count desc  ";

            Query query = session.createSQLQuery(sql);//

            query.setParameter("sd", Util.floorDateMonthDPA(startYear, startMonth));
            query.setParameter("ed", Util.topDateMonthDPA(endYear, endMonth));

            Iterator iter = query.list().iterator();
            while(iter.hasNext()){
            	Object[] row=(Object[])iter.next();
        		Date date=(Date) row[0];
        		Integer divOrg1=(Integer) row[1];
        		String text=(String) row[2];
            	String result=(String) row[3];
            	BigInteger count=(BigInteger) row[4];
            	VehicleCheckReportGroupData vc=new VehicleCheckReportGroupData();
            	vc.setResult(result);
            	//Los informes son mensuales y van de 26 a 26
            	vc.setCount(count.intValue());
            	vc.setText(text);

            	if(divOrg1!=null && divOrg1>0){
            		DivOrgItemLevel1 divOrg=(DivOrgItemLevel1)session.load(DivOrgItemLevel1.class, divOrg1);
            		if(divOrg!=null){
            			if(divOrg.getDivOrgItemLevel2()==null){
            				vc.setDivOrgItem1(divOrg.getResourceKey());
            			}else if(divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3()==null){
            				vc.setDivOrgItem1(divOrg.getResourceKey());
                			vc.setDivOrgItem2(divOrg.getDivOrgItemLevel2().getResourceKey());
                		}else{
                			vc.setDivOrgItem1(divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey());
                			vc.setDivOrgItem2(divOrg.getDivOrgItemLevel2().getResourceKey());
                			vc.setDivOrgItem3(divOrg.getResourceKey());
                		}
            		}
        		 }

            	Calendar cal=Calendar.getInstance();
            	if(vc.getDivOrgItem1().equals("T1")){
	            	cal.setTime(date);
	            	boolean pasa=false;

	            	int day=Util.dpaDayBreak(cal);


	            	if(cal.get(Calendar.DATE)>=day){
	            		cal.add(Calendar.MONTH, 1);
	            		cal.set(Calendar.DATE, 1);
	            		pasa=true;
	            	}
	            	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM");
	            	vc.setMonth(format.format(cal.getTime()));
	            	if(!(pasa && cal.get(Calendar.MONTH)>(endMonth-1))){
	            		if( (cal.get(Calendar.MONTH)<=(endMonth-1) && cal.get(Calendar.YEAR)==endYear) || (cal.get(Calendar.MONTH)>(endMonth-1) && cal.get(Calendar.YEAR)<endYear)){
	            			res.add(vc);
	            		}
	            	}//Si no esta pasado del 25 del mes en cuestion
	            }else if(vc.getDivOrgItem1().equals("T2")){
	            	cal.setTime(date);
	            	boolean pasa=false;
            		if(((cal.get(Calendar.MONTH)<= (endMonth-1) && cal.get(Calendar.YEAR)==endYear) || (cal.get(Calendar.MONTH)>(startMonth-1) && cal.get(Calendar.YEAR)<endYear))){
		            	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM");
		            	vc.setMonth(format.format(cal.getTime()));
	            		res.add(vc);
            		}

	            }

            }

            return res;
        } catch (Exception e) {
        	Object[] parameters = {userSessionData,startMonth,endMonth};
            handler.getException(e,"findVehicleCheckReportResumeGroupItems",userSessionData,parameters);
        }
        return new ArrayList<VehicleCheckReportGroupData>();
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,dpachecklist,drivervehicleedit,drivercreateevent,vehiclechecklist,vehicleview"
     */
    public ArrayList<VehicleCheckReportGroupData> findVehicleCheckReportResumeGroupChecks(Integer startYear, Integer endYear,Integer startMonth,Integer endMonth,UserSessionData userSessionData) throws Exception {
    	ArrayList<VehicleCheckReportGroupData> res = new ArrayList<VehicleCheckReportGroupData>();
        try {
        	if (userSessionData == null) {
        		throw new DeslogedException("findVehicleCheckReportResume","userSessionData = null");
        	}
        	Session session = HibernateUtil.currentSession();
            String sql = "  select to_date(to_char(vehicleCheck.date, 'YYYY/MM/DD'), 'YYYY/MM/DD')  as dateStr,vehicle.divOrg1, vehicleCheck.result, count(*) as count "+
            			 " from vehiclecheck as vehicleCheck "+
            			 " inner join vehicles as vehicle on( vehicle.id = vehicleCheck.vehicle )"+
            			 "  " +UtilityQuery.corporationDivisionsRestictionsSQL("vehicle",userSessionData)+
            			 " and  vehicleCheck.date >=(:sd) and vehicleCheck.date <(:ed) "+
            			 " group by dateStr,vehicle.divOrg1,vehicleCheck.result"+
            			 " order by dateStr asc ,count desc  ";

            Query query = session.createSQLQuery(sql);//

            query.setParameter("sd", Util.floorDateMonthDPA(startYear, startMonth));
            query.setParameter("ed", Util.topDateMonthDPA(endYear, endMonth));

            Iterator iter = query.list().iterator();
            while(iter.hasNext()){
            	Object[] row=(Object[])iter.next();
        		Date date=(Date) row[0];
        		Integer divOrg1=(Integer) row[1];
        		Integer result=(Integer) row[2];
            	BigInteger count=(BigInteger) row[3];
            	VehicleCheckReportGroupData vc=new VehicleCheckReportGroupData();
            	vc.setResult(result.toString());
            	//Los informes son mensuales y van de 26 a 26

            	vc.setCount(count.intValue());

            	if(divOrg1!=null && divOrg1>0){
            		DivOrgItemLevel1 divOrg=(DivOrgItemLevel1)session.load(DivOrgItemLevel1.class, divOrg1);
            		if(divOrg!=null){
            			if(divOrg.getDivOrgItemLevel2()==null){
            				vc.setDivOrgItem1(divOrg.getResourceKey());
            			}else if(divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3()==null){
            				vc.setDivOrgItem1(divOrg.getResourceKey());
                			vc.setDivOrgItem2(divOrg.getDivOrgItemLevel2().getResourceKey());
                		}else{
                			vc.setDivOrgItem1(divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey());
                			vc.setDivOrgItem2(divOrg.getDivOrgItemLevel2().getResourceKey());
                			vc.setDivOrgItem3(divOrg.getResourceKey());
                		}
            		}
        		 }
            	Calendar cal=Calendar.getInstance();
            	if(vc.getDivOrgItem1()!=null && vc.getDivOrgItem1().equals("T1")){
	            	cal.setTime(date);
	            	boolean pasa=false;

	            	int day=Util.dpaDayBreak(cal);

	            	if(cal.get(Calendar.DATE)>=day){
	            		cal.add(Calendar.MONTH, 1);
	            		cal.set(Calendar.DATE, 1);
	            		pasa=true;
	            	}
	            	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM");
	            	vc.setMonth(format.format(cal.getTime()));
	            	if(!(pasa && cal.get(Calendar.MONTH)>(endMonth-1))){
	            		if( (cal.get(Calendar.MONTH)<=(endMonth-1) && cal.get(Calendar.YEAR)==endYear) || (cal.get(Calendar.MONTH)>(endMonth-1) && cal.get(Calendar.YEAR)<endYear)){
	            			res.add(vc);
	            		}
	            	}//Si no esta pasado del 26 del mes en cuestion
	            }else if(vc.getDivOrgItem1()!=null && vc.getDivOrgItem1().equals("T2")){
	            	cal.setTime(date);

            		if(((cal.get(Calendar.MONTH)<= (endMonth-1) && cal.get(Calendar.YEAR)==endYear) || (cal.get(Calendar.MONTH)>(startMonth-1) && cal.get(Calendar.YEAR)<endYear))){
		            	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM");
		            	vc.setMonth(format.format(cal.getTime()));
		            	res.add(vc);
            		}
	            }


            }

            return res;
        } catch (Exception e) {
        	Object[] parameters = {userSessionData,startMonth,endMonth};
            handler.getException(e,"findVehicleCheckReportResumeGroupItems",userSessionData,parameters);
        }
        return new ArrayList<VehicleCheckReportGroupData>();
    }



    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,dpachecklist,drivervehicleedit,drivercreateevent,vehiclechecklist,vehicleview"
     */
    public ArrayList<ChecklistReportHead> findCheckReportHead(Integer startDay, Integer startMonth , Integer startYear, Integer endDay,Integer endMonth,Integer endYear,Integer corporation,String type,UserSessionData userSessionData) throws Exception {
    	ArrayList<ChecklistReportHead> res = new ArrayList<ChecklistReportHead>();
        try {
        	if (userSessionData == null) {
        		throw new DeslogedException("findCheckReportHead","userSessionData = null");
        	}
        	Session session = HibernateUtil.currentSession();
        	String checks = (type.equalsIgnoreCase("s")) ? "'79'" : "'80','123'";
            String sql = "  select to_char(vehicleCheck.date, 'YYYY/MM/DD')  as dateStr, vehicle.divOrg1, vehicleCheck.result,vehicle.divOrg3, vehicle.matricula, vehicleCheck.inspectedBy,vehicleCheck.vehicleCheckId,vehicle.id, un.id as unid "+
            			 " from vehiclecheck as vehicleCheck "+
            			 " inner join vehicles as vehicle on( vehicle.id = vehicleCheck.vehicle )"+
            			 " left outer join unconformitydpa as un on( un.vehicleCheckId = vehicleCheck.vehicleCheckId ) "+
            			 " where vehicle.corporation= "+corporation+" "+
            			 " and  vehicleCheck.date >=(:sd) and vehicleCheck.date <=(:ed) and"+
     			 		 " vehicleCheck.name IN ("+checks +") "+ 
            			 " order by dateStr desc";

            Query query = session.createSQLQuery(sql);//

            query.setParameter("sd", Util.getDateFloor(startYear, startMonth, startDay));
            query.setParameter("ed", Util.getDateTop(endYear,endMonth,endDay));

            Iterator iter = query.list().iterator();
            while(iter.hasNext()){
            	Object[] row=(Object[])iter.next();
        		String date=(String) row[0];
        		Integer divOrg1=(Integer) row[1];
        		Integer result=(Integer) row[2];
        		Integer divOrg3=(Integer) row[3];
        		String matricula=(String) row[4];
        		String inspectedBy=(String) row[5];
        		Integer checkId=(Integer) row[6];
        		Integer vehicleId=(Integer) row[7];
        		Integer unId=(Integer) row[8];
        		ChecklistReportHead vc=new ChecklistReportHead();
            	vc.setResult(result.toString());
            	vc.setPlate(matricula);
            	vc.setDate(date);
            	vc.setInspectedBy(inspectedBy);
            	vc.setVehicleCheckId(checkId);
            	vc.setVehicleId(vehicleId);
            	vc.setUnconformityId(unId);

            	if(corporation.equals(CorporationData.COFUSA) || corporation.equals(9) || corporation.equals(50)){
            		if(divOrg1!=null && divOrg1>0){
                		DivOrgItemLevel1 divOrg=(DivOrgItemLevel1)session.load(DivOrgItemLevel1.class, divOrg1);
                		if(divOrg!=null){
                			vc.setTransporter(divOrg.getResourceKey());
                    	}
            		}
            	}else if(corporation.equals(19) )
	            	if(divOrg3!=null && divOrg3>0){
	            		DivOrgItemLevel1 divOrg=(DivOrgItemLevel1)session.load(DivOrgItemLevel1.class, divOrg3);
	            		if(divOrg!=null){
	            			vc.setTransporter(divOrg.getResourceKey());
	            		}
	            	}

            	res.add(vc);
            }


            return res;
        } catch (Exception e) {
        	Object[] parameters = {userSessionData,startMonth,endMonth};
            handler.getException(e,"ChecklistReportHead",userSessionData,parameters);
        }
        return new ArrayList<ChecklistReportHead>();
    }


    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,dpachecklist,drivervehicleedit,drivercreateevent,vehiclechecklist"
     */
    public VehicleCheckData getVehicleCheck(Integer id,UserSessionData userSessionData) throws Exception {
        VehicleCheckData data = null;
        try {
        	if (id == null) {
        		throw new InvalidDataException("findVehicleCheck","id = null");
        	}
        	if (userSessionData == null) {
        		throw new DeslogedException("findVehicleCheck","userSessionData = null");
        	}
        	Session session = HibernateUtil.currentSession();
            String sql = "SELECT date, name, licensenumber, datelicenseexpiry, obs, serie, vehiclekm, result, trailercheck, inspectedby, distrito, grupo, vehicletype, chargetype, checkplace , vehicle " +
            		"FROM vehiclecheck vc " +
            		"JOIN vehicles v ON v.id = vc.vehicle";
            if(userSessionData.isCorporation(CorporationData.CONTRACTOR_FORESTAL)) {
            	sql += " WHERE vehicleCheckId = "+id;
            }else {
                sql += UtilityQuery.corporationDivisionsRestictionsSQL("v",userSessionData);
            	sql += " AND vehicleCheckId = "+id;
            }
            SQLQuery query = session.createSQLQuery(sql);
            query.addScalar("date", Hibernate.TIMESTAMP);
            query.addScalar("name", Hibernate.STRING);
            query.addScalar("licensenumber", Hibernate.STRING);
            query.addScalar("datelicenseexpiry", Hibernate.DATE);
            query.addScalar("obs", Hibernate.STRING);
            query.addScalar("serie", Hibernate.STRING);
            query.addScalar("vehiclekm", Hibernate.INTEGER);
            query.addScalar("result", Hibernate.INTEGER);
            query.addScalar("trailercheck", Hibernate.INTEGER);
            query.addScalar("inspectedby", Hibernate.STRING);
            query.addScalar("distrito", Hibernate.STRING);
            query.addScalar("grupo", Hibernate.STRING);
            query.addScalar("vehicletype", Hibernate.INTEGER);
            query.addScalar("chargetype", Hibernate.INTEGER);
            query.addScalar("checkplace", Hibernate.INTEGER);
            query.addScalar("vehicle", Hibernate.INTEGER);
            Iterator it = query.list().iterator();
            if(it.hasNext()) {
                Object[] dataValues = (Object[])it.next();
                data = new VehicleCheckData(dataValues);
                Vehicle v = (Vehicle)session.load(Vehicle.class, (Integer)dataValues[15]);
                data.setVehicle(v.getVehicleData());
                
                SQLQuery qItems = session.createSQLQuery("SELECT itemvalue, item, itemtag FROM vehiclecheckitemsvalues WHERE vehiclecheck = "+id);
                qItems.addScalar("itemvalue", Hibernate.STRING);
                qItems.addScalar("item", Hibernate.INTEGER);
                qItems.addScalar("itemtag", Hibernate.STRING);
                Iterator qIt = qItems.list().iterator();
                while(qIt.hasNext()) {
                    Object[] dataItem = (Object[])qIt.next();
                	data.getItemValueSet().add(new ItemValueStrData(new ItemData((Integer)dataItem[1]),(String)dataItem[0],(String)dataItem[2]));
                }
                
                SQLQuery qPointComments = session.createSQLQuery("SELECT point, comment FROM vehiclecheckpointscomment WHERE comment != '30' AND comment != '' AND vehiclecheck = "+id).addScalar("point", Hibernate.INTEGER).addScalar("comment", Hibernate.STRING);
                qIt = qPointComments.list().iterator();
                while(qIt.hasNext()) {
                    Object[] dataPointComment = (Object[]) qIt.next();
                    data.getPointCommentSet().add(new PointCommentData(new PointData((Integer)dataPointComment[0]), (String)dataPointComment[1]));
                }

                SQLQuery qPointImages = session.createSQLQuery("SELECT point, imagename, text FROM vehiclecheckpointsimage WHERE vehiclecheck = "+id).addScalar("point", Hibernate.INTEGER).addScalar("imagename", Hibernate.STRING).addScalar("text", Hibernate.STRING);
                qIt = qPointImages.list().iterator();
                while(qIt.hasNext()) {
                    Object[] dataPointImages = (Object[]) qIt.next();
                    data.getPointImageSet().add(new CheckListImageData((Integer)dataPointImages[0], (String)dataPointImages[1], (String)dataPointImages[2]));
                }
            }
        } catch (Exception e) {
        	Object[] parameters = {id};
            handler.getException(e,"findVehicleCheck",userSessionData,parameters);
        }
        return data;
    }


    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,dpachecklist,drivervehicleedit,drivercreateevent,vehiclechecklist,vehicleedit"
     */
    public VehicleCheckData findVehicleCheck(Integer id,UserSessionData userSessionData) throws Exception {
        VehicleCheckData data = null;
        try {
        	if (id == null) {
        		throw new InvalidDataException("findVehicleCheck","id = null");
        	}
        	if (userSessionData == null) {
        		throw new DeslogedException("findVehicleCheck","userSessionData = null");
        	}
        	Session session = HibernateUtil.currentSession();
            String sql = "select vehicleCheck from VehicleCheck as vehicleCheck  "+
                    UtilityQuery.corporationDivisionsRestictions("vehicleCheck.vehicle",userSessionData)+
                    "and vehicleCheck.vehicleCheckId = :id ";
            Query query = session.createQuery(sql);
            query.setParameter("id", id);
            Object result = query.uniqueResult();
            if(result != null) {
                VehicleCheck vehicleCheck = (VehicleCheck)result;
                data = vehicleCheck.getVehicleCheckData();
            }
        } catch (Exception e) {
        	Object[] parameters = {id};
            handler.getException(e,"findVehicleCheck",userSessionData,parameters);
        }
        return data;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,dpachecklist,drivervehicleedit,drivercreateevent,vehiclechecklist"
     */
    public VehicleCheckData findVehicleCheckById(Integer id,UserSessionData userSessionData) throws Exception {
        VehicleCheckData data = null;
        try {
        	if (id == null) {
        		throw new InvalidDataException("findVehicleCheck","id = null");
        	}
        	if (userSessionData == null) {
        		throw new DeslogedException("findVehicleCheck","userSessionData = null");
        	}
        	Session session = HibernateUtil.currentSession();
            String sql = "SELECT date, name, licensenumber, datelicenseexpiry, obs, serie, vehiclekm, result, trailercheck, inspectedby, distrito, grupo, vehicletype, chargetype, checkplace , vehicle " +
            		"FROM vehiclecheck vc " +
            		"JOIN vehicles v ON v.id = vc.vehicle";
            sql += " WHERE vehicleCheckId = "+id;
            SQLQuery query = session.createSQLQuery(sql);
            query.addScalar("date", Hibernate.TIMESTAMP);
            query.addScalar("name", Hibernate.STRING);
            query.addScalar("licensenumber", Hibernate.STRING);
            query.addScalar("datelicenseexpiry", Hibernate.DATE);
            query.addScalar("obs", Hibernate.STRING);
            query.addScalar("serie", Hibernate.STRING);
            query.addScalar("vehiclekm", Hibernate.INTEGER);
            query.addScalar("result", Hibernate.INTEGER);
            query.addScalar("trailercheck", Hibernate.INTEGER);
            query.addScalar("inspectedby", Hibernate.STRING);
            query.addScalar("distrito", Hibernate.STRING);
            query.addScalar("grupo", Hibernate.STRING);
            query.addScalar("vehicletype", Hibernate.INTEGER);
            query.addScalar("chargetype", Hibernate.INTEGER);
            query.addScalar("checkplace", Hibernate.INTEGER);
            query.addScalar("vehicle", Hibernate.INTEGER);
            Iterator it = query.list().iterator();
            if(it.hasNext()) {
                Object[] dataValues = (Object[])it.next();
                data = new VehicleCheckData(dataValues);
                Vehicle v = (Vehicle)session.load(Vehicle.class, (Integer)dataValues[15]);
                data.setVehicle(v.getVehicleData());
                
                SQLQuery qItems = session.createSQLQuery("SELECT itemvalue, item, itemtag FROM vehiclecheckitemsvalues WHERE vehiclecheck = "+id);
                qItems.addScalar("itemvalue", Hibernate.STRING);
                qItems.addScalar("item", Hibernate.INTEGER);
                qItems.addScalar("itemtag", Hibernate.STRING);
                Iterator qIt = qItems.list().iterator();
                while(qIt.hasNext()) {
                    Object[] dataItem = (Object[])qIt.next();
                	data.getItemValueSet().add(new ItemValueStrData(new ItemData((Integer)dataItem[1]),(String)dataItem[0],(String)dataItem[2]));
                }
                
                SQLQuery qPointComments = session.createSQLQuery("SELECT point, comment FROM vehiclecheckpointscomment WHERE comment != '30' AND comment != '' AND vehiclecheck = "+id).addScalar("point", Hibernate.INTEGER).addScalar("comment", Hibernate.STRING);
                qIt = qPointComments.list().iterator();
                while(qIt.hasNext()) {
                    Object[] dataPointComment = (Object[]) qIt.next();
                    data.getPointCommentSet().add(new PointCommentData(new PointData((Integer)dataPointComment[0]), (String)dataPointComment[1]));
                }

                SQLQuery qPointImages = session.createSQLQuery("SELECT point, imagename, text FROM vehiclecheckpointsimage WHERE vehiclecheck = "+id).addScalar("point", Hibernate.INTEGER).addScalar("imagename", Hibernate.STRING).addScalar("text", Hibernate.STRING);
                qIt = qPointImages.list().iterator();
                while(qIt.hasNext()) {
                    Object[] dataPointImages = (Object[]) qIt.next();
                    data.getPointImageSet().add(new CheckListImageData((Integer)dataPointImages[0], (String)dataPointImages[1], (String)dataPointImages[2]));
                }
            }
        } catch (Exception e) {
        	Object[] parameters = {id};
            handler.getException(e,"findVehicleCheck",userSessionData,parameters);
        }
        return data;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,dpachecklist,drivervehicleedit,drivercreateevent,vehiclechecklist"
     */
    public HashMap<Integer, String> findVehicleCheckPointComments(Integer id,UserSessionData userSessionData) throws Exception {
    	HashMap<Integer, String> comments = new HashMap<Integer, String>();
        try {
        	if (id == null) {
        		throw new InvalidDataException("findVehicleCheck","id = null");
        	}
        	if (userSessionData == null) {
        		throw new DeslogedException("findVehicleCheck","userSessionData = null");
        	}
        	Session session = HibernateUtil.currentSession();
            String sql = "SELECT point, comment FROM vehiclecheckpointscomment WHERE comment != '30' AND comment != '' AND vehiclecheck = "+id;
            Query query = session.createSQLQuery(sql).addScalar("point", Hibernate.INTEGER).addScalar("comment", Hibernate.STRING);
            Iterator it = query.list().iterator();
            while(it.hasNext()) {
                Object[] data = (Object[]) it.next();
                comments.put((Integer)data[0], (String)data[1]);
            }
        } catch (Exception e) {
        	Object[] parameters = {id};
            handler.getException(e,"findVehicleCheckPointComments",userSessionData,parameters);
        }
        return comments;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,dpachecklist,drivervehicleedit,drivercreateevent,vehiclechecklist"
     */
    public Collection<VehicleCheckData> findVehicleCheckDPA(Integer id,UserSessionData userSessionData) throws Exception {
    	Collection<VehicleCheckData> data = new ArrayList<VehicleCheckData>();
        try {

        	if (userSessionData == null) {
        		throw new DeslogedException("findVehicleCheckDPA","userSessionData = null");
        	}
        	Session session = HibernateUtil.currentSession();
            String sql = "select vehicleCheck from VehicleCheck as vehicleCheck  "+
                    "  WHERE vehicleCheck.vehicle.corpCountry.pk.corporation=62 and vehicleCheck.vehicleCheckId >"+id+" order by vehicleCheck.vehicleCheckId asc ";
            Query query = session.createQuery(sql);
            query.setMaxResults(50);

            Iterator<VehicleCheck> iter=query.list().iterator();
            while(iter.hasNext()){
            	VehicleCheck vc=(VehicleCheck)iter.next();
            	data.add(vc.getVehicleCheckData());
            }
        } catch (Exception e) {
        	Object[] parameters = {};
            handler.getException(e,"findVehicleCheckDPA",userSessionData,parameters);
        }
        return data;
    }




    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,vehicleedit,drivervehicleedit,drivercreateevent,vehiclechecklist"
     */
    public Collection findVehicleChecks(Integer id,UserSessionData userSessionData) throws Exception {
    	try {
    		if (id == null) {
    			throw new InvalidDataException("findVehicleChecks","id = null");
    		}
    		if (userSessionData == null) {
    			throw new DeslogedException("findVehicleChecks","userSessionData = null");
    		}
            Session session = HibernateUtil.currentSession();
            Query query = session.createSQLQuery("SELECT vehiclecheckid,date,name,obs,result,inspectedby FROM vehiclecheck WHERE vehicle = "+id+" ORDER BY date")
            .addScalar("vehiclecheckid",Hibernate.INTEGER)
            .addScalar("date",Hibernate.DATE)
            .addScalar("name",Hibernate.STRING)
            .addScalar("obs",Hibernate.STRING)
            .addScalar("result",Hibernate.INTEGER)
            .addScalar("inspectedby",Hibernate.STRING);
            if(userSessionData.isCorporation(new int[]{CorporationData.SYNGENTA,CorporationData.SYNGENTA_BR})) {
            	Collection result = new LinkedList();
            	Iterator it = query.list().iterator();
            	while(it.hasNext()) {
            		Object[] oldData = (Object[])it.next();
            		Object[] newData = new Object[oldData.length+1];
            		for(int i = 0; i < oldData.length; i++) {
            			newData[i] = oldData[i];
            		}
            		Query q = session.createSQLQuery("SELECT DISTINCT checklist FROM point WHERE ID in (SELECT point FROM items WHERE id IN (SELECT item FROM vehiclecheckitemsvalues WHERE vehiclecheck = "+String.valueOf(oldData[0])+"))").addScalar("checklist",Hibernate.INTEGER);
            		Iterator it2 = q.list().iterator();
            		if(it2.hasNext())
            			newData[oldData.length] = new Boolean(((Integer)it2.next()).intValue() == CheckListData.SYNGENTA_CHECKLIST);
            		result.add(newData);
            	}
            	return result;
            }else {
                return query.list();
            }
        } catch (Exception e) {
        	Object[] parameters = {id};
            handler.getException(e,"findVehicleChecks",userSessionData,parameters);
        }
        return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,vehicleedit,drivervehicleedit,drivercreateevent,vehiclechecklist"
     */
    public VehiclePursuitRouteData findVehiclePursuitRoute(Integer id,UserSessionData userSessionData) throws Exception {
        VehiclePursuitRouteData result = null;

        try {
	        if (id == null) {
	            throw new InvalidDataException("findVehiclePursuitRoute","id = null");
	        }
	        if (userSessionData == null) {
	            throw new DeslogedException("findVehiclePursuitRoute","userSessionData = null");
	        }

	        Session session = HibernateUtil.currentSession();
            String sql = "select vehicleCheck from VehiclePursuitRoute as vehicleCheck  "+
                UtilityQuery.corporationDivisionsRestictions("vehicleCheck.vehicle",userSessionData)+
                "and vehicleCheck.pursuitRouteId = :id ";

            Query query = session.createQuery(sql);
            query.setParameter("id", id);

            Object results = query.uniqueResult();
            if(results != null) {
                VehiclePursuitRoute vehicleCheck = (VehiclePursuitRoute)results;
                // accedo al conjunto de items para que la inicializaciòn lazy no falle
                Iterator items = vehicleCheck.getItemValueSet().iterator();
                while (items.hasNext()) {
                    items.next();
                }
                result = vehicleCheck.getVehiclePursuitRouteData();
            }
        } catch (Exception e) {
        	Object[] parameters = {id};
            handler.getException(e,"findVehiclePursuitRoute",userSessionData,parameters);
        }

        return result;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,vehicleedit,drivervehicleedit,drivercreateevent,vehiclechecklist"
     */
    public Collection findVehiclePursuitRoutes(Integer id,UserSessionData userSessionData) throws Exception {
        try {
            if (id == null) {
                throw new InvalidDataException("findVehiclePursuitRoute","id = null");
            }
            if (userSessionData == null) {
                throw new DeslogedException("findVehiclePursuitRoute","userSessionData = null");
            }
            Session session = HibernateUtil.currentSession();
            Query query = session.createSQLQuery("SELECT pursuitrouteid,date,instructor,obs FROM vehiclepursuitroute WHERE vehicle = "+id+" ORDER BY date").addScalar("pursuitrouteid",Hibernate.INTEGER).addScalar("date",Hibernate.DATE).addScalar("instructor",Hibernate.STRING).addScalar("obs",Hibernate.STRING);

            return query.list();
        } catch (Exception e) {
        	Object[] parameters = {id};
            handler.getException(e,"findVehiclePursuitRoute", userSessionData, parameters);
        }
        return null;
    }


    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,drivervehicleedit,vehiclecreate,vehicleupdate,vehicleedit"
     * @param registrationNumber - registrationNumber of vehicle
     * @param country - country of vehicle
     * @return boolean
     * @throws DeslogedException
     * @throws CommunicationProblemException
     */
    public boolean vehicleCheckExist(Integer vehicleId) throws Exception {

    	try {
	        Session session = HibernateUtil.currentSession();

	        Query query = session.createQuery("select " +
	                "count(*)  from VehicleCheck " +
	                "as vehicleCheck where vehicleCheck.vehicle.id = :vehicleId");


	        query.setParameter("vehicleId", vehicleId);

	        List userColl = query.list();
	        Iterator iter = userColl.iterator();
	        Integer total = new Integer(0);
	        while (iter.hasNext()) {
	            total = new Integer(((Long) iter.next()).intValue());
	        }
	        return(total.intValue() > 0);
        } catch (Exception e) {
        	Object[] parameters = {vehicleId};
            handler.getException(e,"findVehiclePursuitRoute", null, parameters);
        }
        return false;
   }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     */
    public Hashtable getChecksCount(Integer fromMonth, Integer fromYear, Integer toMonth, Integer toYear, Integer[][] divOrgs, Integer[] divGeos, Text messages, int order, UserSessionData userSessionData) throws Exception {
        Hashtable result = new Hashtable();
        String divOrgSelected = getSelectedDivOrg(userSessionData);
        try {
            Session session = HibernateUtil.currentSession();

            StringBuffer queryStr = new StringBuffer("SELECT vc.vehicleCheckId,vc.vehicle.id,vc.vehicle."+divOrgSelected+".resourceKey, vc.vehicle.matricula, vc.vehicle.type.resourceBundleId ");
            queryStr.append("FROM VehicleCheck vc ");
            queryStr.append("WHERE (YEAR(vc.date) = ").append(String.valueOf(fromYear)).append(" AND MONTH(vc.date) >= ").append(fromMonth).append(" OR YEAR(vc.date) > ").append(String.valueOf(fromYear)+") ");
            queryStr.append(" AND (YEAR(vc.date) = ").append(String.valueOf(toYear)).append(" AND MONTH(vc.date) <= ").append(toMonth).append(" OR YEAR(vc.date) < ").append(String.valueOf(toYear)+") ");
            queryStr.append(" AND vc.vehicle.corpCountry.pk.corporation.id = ").append(String.valueOf(userSessionData.getFilter().getCorporationId())).append(" ");
            queryStr.append(" AND vc.name NOT IN ('79','80','84') ");


            HashSet set = new HashSet();
            String divOrgStr = "";
            if(divOrgs != null && divOrgs.length > 0) {
                for(int j = 0; j < divOrgs.length; j++) {
                    if(divOrgs[j] != null && divOrgs[j].length > 0) {
                        divOrgStr = "";
                        divOrgStr = " and vc.vehicle.divOrg"+String.valueOf(j+1)+" in (";
                        set = new HashSet();
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
                        queryStr.append(divOrgStr);
                    }
                }
            }
            if(divGeos != null && divGeos.length > 0) {
                queryStr.append(" AND vc.vehicle.corpCountry.pk.country in (");
                for(int i = 0; i < divGeos.length; i++) {
                    queryStr.append(divGeos[i]);
                    queryStr.append((i == divGeos.length-1) ? ")" : ",");
                }
            }

            StringBuffer ids = new StringBuffer("0");
            Query query = session.createQuery(queryStr.toString());
            Iterator it = query.list().iterator();
            while(it.hasNext()) {
                Object[] s = (Object[])it.next();
                if(result.containsKey(s[1])) {
                    ((VehicleCheckReportDataType)result.get(s[1])).sumCount();
                }else {
                    result.put(s[1],new VehicleCheckReportDataType(s, messages, order));
                }
                ids.append(",").append(s[0]);
            }

            int checklist = CheckListData.TRUCK_CHECKLIST;

            String sql1 = "SELECT pk.checkList FROM CorporationCheckList WHERE corporation = (:corporation) AND type = (:type)";

            query = session.createQuery(sql1);
            query.setParameter("corporation",userSessionData.getFilter().getCorporationId());
            query.setParameter("type",new Integer(CheckListData.TRUCK));
            List list = query.list();
            if(list.size() > 0) {
                checklist = ((CheckList)list.iterator().next()).getId().intValue();
            }

            for(int i = 0; i < 13; i++) {
                Iterator itCount = getColumnCheckListReport(String.valueOf(checklist),i,ids.toString(),userSessionData);
                while(itCount.hasNext()) {
                    Object[] counts = (Object[])itCount.next();
                    if(result.containsKey(counts[0])) {
                        ((VehicleCheckReportDataType)result.get(counts[0])).setCount(((Integer)counts[1]).intValue(),i);
                    }
                }
            }
            Iterator itCount = getNoObservationsColumnCheckListReport(ids.toString(),userSessionData);
            while(itCount.hasNext()) {
                Object[] counts = (Object[])itCount.next();
                if(result.containsKey(counts[0])) {
                    ((VehicleCheckReportDataType)result.get(counts[0])).setCount(((Integer)counts[1]).intValue(),13);
                }
            }

            queryStr = new StringBuffer("SELECT tc.trailerCheckId,tc.trailer.id, ");

            //Colonvade muestra el Transportista, que esta en el dviorg3
            if (userSessionData.getFilter().getCorporationId().equals(
                new Integer(CorporationData.COLONVADE))){
                queryStr.append(" tc.trailer.divOrg3.resourceKey, ");
            } else{
                queryStr.append(" tc.trailer.divOrg1.resourceKey, ");
            }

            queryStr.append(" tc.trailer.plate ");
            queryStr = new StringBuffer("SELECT tc.trailerCheckId,tc.trailer.id,tc.trailer."+divOrgSelected+".resourceKey, tc.trailer.plate ");
            queryStr.append("FROM TrailerCheck tc ");
            queryStr.append("WHERE (YEAR(tc.date) = ").append(String.valueOf(fromYear)).append(" AND MONTH(tc.date) >= ").append(fromMonth).append(" OR YEAR(tc.date) > ").append(String.valueOf(fromYear)+") ");
            queryStr.append(" AND (YEAR(tc.date) = ").append(String.valueOf(toYear)).append(" AND MONTH(tc.date) <= ").append(toMonth).append(" OR YEAR(tc.date) < ").append(String.valueOf(toYear)+") ");
            queryStr.append(" AND tc.trailer.corpCountry.pk.corporation.id = ").append(String.valueOf(userSessionData.getFilter().getCorporationId())).append(" ");
            if(divOrgs != null && divOrgs.length > 0) {
                for(int j = 0; j < divOrgs.length; j++) {
                    if(divOrgs[j] != null && divOrgs[j].length > 0) {
                        queryStr.append(" AND tc.trailer.divOrg"+String.valueOf(j+1)+" IN (");
                        for(int i = 0; i < divOrgs[j].length; i++) {
                            queryStr.append(divOrgs[j][i]);
                            queryStr.append((i == divOrgs[j].length-1) ? ") " : ",");
                        }
                    }
                }
            }
            if(divGeos != null && divGeos.length > 0) {
                queryStr.append(" AND tc.trailer.corpCountry.pk.country in (");
                for(int i = 0; i < divGeos.length; i++) {
                    queryStr.append(divGeos[i]);
                    queryStr.append((i == divGeos.length-1) ? ")" : ",");
                }
            }

            ids = new StringBuffer("0");
            query = session.createQuery(queryStr.toString());
            it = query.list().iterator();
            while(it.hasNext()) {
                Object[] s = (Object[])it.next();
                if(result.containsKey(s[1])) {
                    ((VehicleCheckReportDataType)result.get(s[1])).sumCount();
                }else {
                    result.put(s[1],new VehicleCheckReportDataType((String)s[2], (String)s[3], messages.getText("vehicle.data.trailer"), order));
                }
                ids.append(",").append(s[0]);
            }

            checklist = CheckListData.TRAILER_CHECKLIST;

            sql1 = "SELECT pk.checkList FROM CorporationCheckList WHERE corporation = (:corporation) AND type = (:type)";

            query = session.createQuery(sql1);
            query.setParameter("corporation",userSessionData.getFilter().getCorporationId());
            query.setParameter("type",new Integer(CheckListData.TRAILER));
            list = query.list();
            if(list.size() > 0) {
                checklist = ((CheckList)list.iterator().next()).getId().intValue();
            }

            for(int i = 0; i < 13; i++) {
                itCount = getColumnTrailerCheckListReport(String.valueOf(checklist),i,ids.toString(),userSessionData);
                while(itCount.hasNext()) {
                    Object[] counts = (Object[])itCount.next();
                    if (result.containsKey(counts[0])){
                        ((VehicleCheckReportDataType)result.get(counts[0])).setCount(((Integer)counts[1]).intValue(),i);
                    }
                }
            }
            itCount = getNoObservationsColumnTrailerCheckListReport(ids.toString(),userSessionData);
            while(itCount.hasNext()) {
                Object[] counts = (Object[])itCount.next();
                if (result.containsKey(counts[0])){
                    ((VehicleCheckReportDataType)result.get(counts[0])).setCount(((Integer)counts[1]).intValue(),13);
                }
            }

        }catch(Exception e) {
        	Object[] parameters = {fromMonth,fromYear,toMonth,toYear,divOrgs,divGeos,messages,order};
        	handler.getException(e,"getChecksCount",userSessionData,parameters);
        }
        return result;
    }



    private String getSelectedDivOrg(UserSessionData userSessionData) {
        switch(userSessionData.getFilter().getCorporationId().intValue()) {
            case CorporationData.COLONVADE: case CorporationData.LOSPIQUES:
                return "divOrg3";
            default:
                return "divOrg1";
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     */
    public Hashtable getVehiclePursuitRouteCount(Integer fromMonth, Integer fromYear,
            Integer toMonth, Integer toYear, Integer[][] divOrgs, Integer[] divGeos,
            Text messages, int order, UserSessionData userSessionData) throws Exception {

        Hashtable result = new Hashtable();
        try {
            Session session = HibernateUtil.currentSession();

            StringBuffer queryStr = new StringBuffer("SELECT vc.pursuitRouteId,vc.vehicle.id, ");

            int corporationId = userSessionData.getFilter().getCorporationId().intValue();
            // Colonvade muestra el Transportista, que esta en el dviorg3
            if (corporationId == CorporationData.COLONVADE || corporationId == CorporationData.LOSPIQUES){
                queryStr.append(" vc.vehicle.divOrg3.resourceKey, ");
            } else{
                queryStr.append(" vc.vehicle.divOrg1.resourceKey, ");
            }

            queryStr.append(" vc.vehicle.matricula, vc.vehicle.type.resourceBundleId ");
            queryStr.append(" FROM VehiclePursuitRoute vc ");
            queryStr.append(" WHERE (YEAR(vc.date) = ").append(String.valueOf(fromYear)).append(" AND MONTH(vc.date) >= ").append(fromMonth).append(" OR YEAR(vc.date) > ").append(String.valueOf(fromYear)+") ");
            queryStr.append(" AND (YEAR(vc.date) = ").append(String.valueOf(toYear)).append(" AND MONTH(vc.date) <= ").append(toMonth).append(" OR YEAR(vc.date) < ").append(String.valueOf(toYear)+") ");
            queryStr.append(" AND vc.vehicle.corpCountry.pk.corporation.id = ").append(String.valueOf(userSessionData.getFilter().getCorporationId())).append(" ");

            HashSet set = new HashSet();
            String divOrgStr = "";
            if(divOrgs != null && divOrgs.length > 0) {
                for(int j = 0; j < divOrgs.length; j++) {
                    if(divOrgs[j] != null && divOrgs[j].length > 0) {
                        divOrgStr = "";
                        divOrgStr = " and vc.vehicle.divOrg"+String.valueOf(j+1)+" in (";
                        set = new HashSet();
                        for(int i = 0; i < divOrgs[j].length; i++) {
                            set.add(divOrgs[j][i]); // Para evitar repetidos

                            // divOrgStr += divOrgs[j][i];
                            // divOrgStr += (i == divOrgs[j].length-1) ? ") " : ",";
                        }
                        Iterator it = set.iterator();
                        int i = 0;
                        while (it.hasNext()){
                            String div = ((Integer)it.next()).toString();
                            divOrgStr += div;
                            divOrgStr += (i == set.size()-1) ? ") " : ",";
                            i++;
                        }
                        //System.out.println(divOrgStr);
                        queryStr.append(divOrgStr);
                    }
                }
            }
            if(divGeos != null && divGeos.length > 0) {
                queryStr.append(" AND vc.vehicle.corpCountry.pk.country in (");
                for(int i = 0; i < divGeos.length; i++) {
                    queryStr.append(divGeos[i]);
                    queryStr.append((i == divGeos.length-1) ? ")" : ",");
                }
            }

            StringBuffer ids = new StringBuffer("0");
            Query query = session.createQuery(queryStr.toString());
            Iterator it = query.list().iterator();
            while(it.hasNext()) {
                Object[] s = (Object[])it.next();
                if(result.containsKey(s[1])) {
                    ((VehiclePursuitReportDataType)result.get(s[1])).sumCount();
                }else {
                    result.put(s[1],new VehiclePursuitReportDataType(s, messages, order));
                }
                ids.append(",").append(s[0]);
            }

//          Puntos 1 y 2
            String[] items = {"870,871,872,873,874,875,876,877,878,879," +
                    "880,881,882,883,884,885,886,887,888," +
                    "889,890,891,892,893,894,895,896","897,898,899,900,901"};

            for(int i = 0; i < 2; i++) {
                Iterator itCount = getColumnVehiclePursuitReport(items[i],ids.toString(),
                        userSessionData);
                while(itCount.hasNext()) {
                    Object[] counts = (Object[])itCount.next();
                    if (result.containsKey(counts[0])){
                        ((VehiclePursuitReportDataType)result.get(counts[0])).
                            setCount(((Integer)counts[1]).intValue(),i);
                    }
                }
            }

            // Punto 3
            String [] items3 = {"902,903,904,905,906"};
            Iterator itCount = getColumnThreeVehiclePursuitReport(items3[0],ids.toString(),
                        userSessionData);
            while(itCount.hasNext()) {
                Object[] counts = (Object[])itCount.next();
                if (result.containsKey(counts[0])){
                    ((VehiclePursuitReportDataType)result.get(counts[0])).
                        setCount(((Integer)counts[1]).intValue(),2);
                }
            }


            // Observaciones:
            itCount = getObservationsColumnVehiclePursuitReport(ids.toString(),
                    userSessionData,fromYear,fromMonth,toYear,toMonth);
            while(itCount.hasNext()) {
                Object[] counts = (Object[])itCount.next();
                if (result.containsKey(counts[0])){
                    ((VehiclePursuitReportDataType)result.get(counts[0])).
                        setCount(((Integer)counts[1]).intValue(),3);
                }
            }


        }catch(Exception e) {
        	Object[] parameters = {fromMonth,fromYear,toMonth,toYear,divOrgs,divGeos,messages,order};
            handler.getException(e,"getVehiclePursuitRouteCount",userSessionData,parameters);
        }
        return result;
    }

    /**
     * Devuelve los conductores que no hicieron la Evaluación en Cabina
     * para Vehiculos Pesados (Forestales)
     * @param userRequest - logged user
     * @param type - type of activity
     * @param first - first index of the search to return
     * @param count - number of elements to return
     * @return result of the search
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws CommunicationProblemException
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
    */
    public ListResult vehicleWithoutPursuitRoute(UserSessionData userRequest,Date initialDate,Date lastDate,Integer[][] divOrgs,Integer[][] divGeos, int status,int first, int count, String plate) throws Exception {
    	try {
	        Session session = HibernateUtil.currentSession();
	        Hashtable parameters = new Hashtable();
	        Collection result = new LinkedList();
	        String queryStr = UtilityQuery.corporationDivisionsRestictions("vehicle",userRequest);

	        HashSet set = new HashSet();
	        String divOrgStr = "";
	        if(divOrgs != null && divOrgs.length > 0) {
	            for(int j = 0; j < divOrgs.length; j++) {
	                if(divOrgs[j] != null && divOrgs[j].length > 0) {
	                    divOrgStr = "";
	                    divOrgStr = " and vehicle.divOrg"+String.valueOf(j+1)+" in (";
	                    set = new HashSet();
	                    for(int i = 0; i < divOrgs[j].length; i++) {
	                        set.add(divOrgs[j][i]); // Para evitar repetidos

	                        // divOrgStr += divOrgs[j][i];
	                        // divOrgStr += (i == divOrgs[j].length-1) ? ") " : ",";
	                    }
	                    Iterator it = set.iterator();
	                    int i = 0;
	                    while (it.hasNext()){
	                        String div = ((Integer)it.next()).toString();
	                        divOrgStr += div;
	                        divOrgStr += (i == set.size()-1) ? ") " : ",";
	                        i++;
	                    }
	                    // System.out.println(divOrgStr);
	                    queryStr += divOrgStr;
	                }
	            }
	        }

	        if(divGeos != null) {
	            if(divGeos[0] != null && divGeos[0].length > 0) {
	                queryStr += "and vehicle.corpCountry.pk.country in (";
	                for(int i = 0; i < divGeos[0].length; i++) {
	                    queryStr += divGeos[0][i];
	                    queryStr += (i == divGeos[0].length-1) ? ")" : ",";
	                }
	            }
	        }

	//      Driver State: Activo
	        if (status == VehicleData.ACTIVE)
	        {
	            queryStr +=" and vehicle.exclusionDate is null ";
	        }
	        // Driver State: Excluido
	        if (status == VehicleData.EXCLUDED)
	        {
	            queryStr +=" and vehicle.exclusionDate is not null ";
	        }
	        // Plate:
	        if (!Util.empty(plate)){
	            queryStr += " and TO_UPPER(vehicle.matricula) like TO_UPPER('%"+plate+"%') ";
	        }

	        // String subqueryStr ="";

	        /*
	        if(initialDate != null) {
	            subqueryStr +=" and (registry.activity.date >= (:date1)";
	            Calendar calendar1 = Calendar.getInstance();
	            calendar1.setTime(initialDate);
	            calendar1.set(Calendar.HOUR_OF_DAY,0);
	            calendar1.set(Calendar.MINUTE,0);
	            calendar1.set(Calendar.SECOND,0);
	            calendar1.set(Calendar.MILLISECOND,0);
	            parameters.put("date1",calendar1.getTime());
	        }

	        if(lastDate != null) {
	            subqueryStr +=" and registry.activity.date <= (:date2))";
	            Calendar calendar2 = Calendar.getInstance();
	            calendar2.setTime(lastDate);
	            calendar2.set(Calendar.HOUR_OF_DAY,23);
	            calendar2.set(Calendar.MINUTE,59);
	            calendar2.set(Calendar.SECOND,59);
	            parameters.put("date2",calendar2.getTime());
	        }
	          */


	        Query queryAux = session.createQuery("select count(*) " +
	                " from Vehicle as vehicle "
	                + queryStr +
	                " and vehicle.id not in " +
	                    " ( select vc.vehicle.id " +
	                    "   from VehiclePursuitRoute vc )");

	        Enumeration keys = parameters.keys();
	        while(keys.hasMoreElements()) {
	            String parameter = (String)keys.nextElement();
	            queryAux.setParameter(parameter,parameters.get(parameter));
	        }

	        List activityColl = queryAux.list();
	        Iterator iter = activityColl.iterator();
	        Integer total = new Integer(0);
	        while (iter.hasNext()) {
	            total = new Integer(((Long) iter.next()).intValue());
	        }

	        // System.out.println("TOTAL Vehicles WO Pursuit Routes:"+total);

	        Query query = session.createQuery("select vehicle " +
	                " from Vehicle as vehicle "
	                + queryStr +
	                " and vehicle.id not in " +
	                    " ( select vc.vehicle.id " +
	                    "   from VehiclePursuitRoute vc )" +
	                " order by vehicle.divOrg1.resourceKey ");

	        keys = parameters.keys();
	        while(keys.hasMoreElements()) {
	            String parameter = (String)keys.nextElement();
	            query.setParameter(parameter,parameters.get(parameter));
	        }

	        if(count != -1) {
	            query.setFirstResult(first);
	            query.setMaxResults(count);
	        }

	        List userColl = query.list();
	        iter = userColl.iterator();
	        userColl=null;
	        while (iter.hasNext()) {
	            Vehicle vehicle = (Vehicle) iter.next();
	            result.add(vehicle.getVehicleData());
	        }
	        session.close();

	        return new ListResult(result,total.intValue());
    	}catch (Exception e) {
    		Object[] parameters = {initialDate,lastDate,divOrgs,divGeos,status,first,count,plate};
    		handler.getException(e, "vehicleWithoutPursuitRoute", userRequest, parameters);
        }
    	return null;
    }

    private Iterator getColumnCheckListReport(String checklist,int index,String ids,UserSessionData userSessionData) throws Exception {
    	try {
	        Session session = HibernateUtil.currentSession();

	        StringBuffer queryStr = new StringBuffer("SELECT v.id as id, count(*) as count ");
	        queryStr.append("FROM vehiclecheck vc ");
	        queryStr.append("JOIN vehicles v ON vc.vehicle = v.id ");
	        queryStr.append("WHERE v.corporation = ").append(String.valueOf(userSessionData.getFilter().getCorporationId()));
	        queryStr.append(" AND vc.vehiclecheckid in (");
	        queryStr.append("SELECT DISTINCT vehiclecheck FROM vehiclecheckitemsvalues WHERE ");
	        queryStr.append("itemvalue = '2' AND item in (SELECT item FROM checklistitems WHERE checklist = ").append(checklist).append(" AND index = ").append(index);
	        queryStr.append(") AND vehiclecheck in (").append(ids).append(")) ");
	        queryStr.append("GROUP BY v.id ");

	        Query query = session.createSQLQuery(queryStr.toString()).addScalar("id", Hibernate.INTEGER).addScalar("count", Hibernate.INTEGER);
	        return query.list().iterator();
		}catch (Exception e) {
			Object[] parameters = {checklist,index,ids};
			handler.getException(e, "getColumnCheckListReport", userSessionData, parameters);
	    }
		return null;
    }

    private Iterator getColumnVehiclePursuitReport(String items,String ids, UserSessionData userSessionData) throws Exception {
    	try {
	        Session session = HibernateUtil.currentSession();

	        StringBuffer queryStr = new StringBuffer("SELECT v.id as id, count(*) as count ");
	        queryStr.append("FROM VehiclePursuitRoute vc ");
	        queryStr.append("JOIN vehicles v ON vc.vehicle = v.id ");
	        queryStr.append("WHERE v.corporation = ").append(String.valueOf(userSessionData.getFilter()
	                .getCorporationId()));
	        queryStr.append(" AND vc.pursuitRouteId in (");
	        queryStr.append("SELECT DISTINCT vehiclepursuitroute FROM vehiclepursuitrouteitemsvalues WHERE ");
	        queryStr.append("itemvalue = '2' AND item in (").append(items);
	        queryStr.append(") AND vehiclepursuitroute in (").append(ids).append(")) ");
	        queryStr.append("GROUP BY v.id ");

	        Query query = session.createSQLQuery(queryStr.toString()).
	            addScalar("id", Hibernate.INTEGER).
	            addScalar("count", Hibernate.INTEGER);
	        return query.list().iterator();
		}catch (Exception e) {
			Object[] parameters = {items,ids};
			handler.getException(e, "getColumnVehiclePursuitReport", userSessionData, parameters);
	    }
		return null;
    }

    private Iterator getColumnVehiclePursuitReportList(String items,String ids, UserSessionData userSessionData) throws Exception {
    	try {
	        Session session = HibernateUtil.currentSession();

	        StringBuffer queryStr = new StringBuffer("SELECT vc.pursuitrouteid as id, count(*) as count ");
	        queryStr.append("FROM VehiclePursuitRoute vc ");
	        queryStr.append("JOIN vehicles v ON vc.vehicle = v.id ");
	        queryStr.append("WHERE v.corporation = ").append(String.valueOf(userSessionData.getFilter()
	                .getCorporationId()));
	        queryStr.append(" AND vc.pursuitRouteId in (");
	        queryStr.append("SELECT DISTINCT vehiclepursuitroute FROM vehiclepursuitrouteitemsvalues WHERE ");
	        queryStr.append("itemvalue = '2' AND item in (").append(items);
	        queryStr.append(") AND vehiclepursuitroute in (").append(ids).append(")) ");
	        queryStr.append("GROUP BY vc.pursuitrouteid ");

	        Query query = session.createSQLQuery(queryStr.toString()).
	            addScalar("id", Hibernate.INTEGER).
	            addScalar("count", Hibernate.INTEGER);
	        return query.list().iterator();
		}catch (Exception e) {
			Object[] parameters = {items,ids};
			handler.getException(e, "getColumnVehiclePursuitReport", userSessionData, parameters);
	    }
		return null;
    }

    private Iterator getColumnThreeVehiclePursuitReport(String items,String ids, UserSessionData userSessionData) throws Exception {
    	try {
	        Session session = HibernateUtil.currentSession();

	        StringBuffer queryStr = new StringBuffer("SELECT v.id as id, count(*) as count ");
	        queryStr.append("FROM VehiclePursuitRoute vc ");
	        queryStr.append("JOIN vehicles v ON vc.vehicle = v.id ");
	        queryStr.append("WHERE v.corporation = ").append(String.valueOf(userSessionData.getFilter()
	                .getCorporationId()));
	        queryStr.append(" AND vc.pursuitRouteId in (");
	        queryStr.append("SELECT DISTINCT vehiclepursuitroute FROM vehiclepursuitrouteitemsvalues WHERE ");
	        queryStr.append("itemvalue = '1' AND item in (").append(items);
	        queryStr.append(") AND vehiclepursuitroute in (").append(ids).append(")) ");
	        queryStr.append("GROUP BY v.id ");

	        Query query = session.createSQLQuery(queryStr.toString()).
	            addScalar("id", Hibernate.INTEGER).
	            addScalar("count", Hibernate.INTEGER);
	        return query.list().iterator();
		}catch (Exception e) {
			Object[] parameters = {items,ids};
			handler.getException(e, "getColumnThreeVehiclePursuitReport", userSessionData, parameters);
	    }
		return null;
    }

    private Iterator getColumnThreeVehiclePursuitReportList(String items,String ids, UserSessionData userSessionData) throws Exception {
    	try {
	        Session session = HibernateUtil.currentSession();

	        StringBuffer queryStr = new StringBuffer("SELECT vc.pursuitrouteid as id, count(*) as count ");
	        queryStr.append("FROM VehiclePursuitRoute vc ");
	        queryStr.append("JOIN vehicles v ON vc.vehicle = v.id ");
	        queryStr.append("WHERE v.corporation = ").append(String.valueOf(userSessionData.getFilter()
	                .getCorporationId()));
	        queryStr.append(" AND vc.pursuitRouteId in (");
	        queryStr.append("SELECT DISTINCT vehiclepursuitroute FROM vehiclepursuitrouteitemsvalues WHERE ");
	        queryStr.append("itemvalue = '1' AND item in (").append(items);
	        queryStr.append(") AND vehiclepursuitroute in (").append(ids).append(")) ");
	        queryStr.append("GROUP BY vc.pursuitrouteid ");

	        Query query = session.createSQLQuery(queryStr.toString()).
	            addScalar("id", Hibernate.INTEGER).
	            addScalar("count", Hibernate.INTEGER);
	        return query.list().iterator();
		}catch (Exception e) {
			Object[] parameters = {items,ids};
			handler.getException(e, "getColumnThreeVehiclePursuitReport", userSessionData, parameters);
	    }
		return null;
    }

    private Iterator getColumnTrailerCheckListReport(String checklist,int index,String ids,UserSessionData userSessionData) throws Exception {
    	try {
	        Session session = HibernateUtil.currentSession();

	        StringBuffer queryStr = new StringBuffer("SELECT t.id as id, count(*) as count ");
	        queryStr.append("FROM trailercheck tc ");
	        queryStr.append("JOIN trailers t ON tc.trailer = t.id ");
	        queryStr.append("WHERE t.corporation = ").append(String.valueOf(userSessionData.getFilter().getCorporationId()));
	        queryStr.append(" AND tc.trailercheckid in (");
	        queryStr.append("SELECT DISTINCT tailercheck FROM trailercheckitemsvalues WHERE ");
	        queryStr.append("itemvalue = '2' AND item in (SELECT item FROM checklistitems WHERE checklist = ").append(checklist).append(" AND index = ").append(index);
	        queryStr.append(") AND tailercheck in (").append(ids).append(")) ");
	        queryStr.append("GROUP BY t.id ");

	        Query query = session.createSQLQuery(queryStr.toString()).addScalar("id", Hibernate.INTEGER).addScalar("count", Hibernate.INTEGER);
	        return query.list().iterator();
		}catch (Exception e) {
			Object[] parameters = {checklist,index,ids};
			handler.getException(e, "getColumnTrailerCheckListReport", userSessionData, parameters);
	    }
		return null;
    }

    private Iterator getNoObservationsColumnCheckListReport(String ids,UserSessionData userSessionData) throws Exception {
    	try {
	        Session session = HibernateUtil.currentSession();

	        StringBuffer queryStr = new StringBuffer("SELECT v.id as id, count(*) as count ");
	        queryStr.append("FROM vehiclecheck vc ");
	        queryStr.append("JOIN vehicles v ON vc.vehicle = v.id ");
	        queryStr.append("WHERE v.corporation = ").append(String.valueOf(userSessionData.getFilter().getCorporationId()));
	        queryStr.append(" AND vc.vehiclecheckid IN (").append(ids).append(") ");
	        queryStr.append(" AND vc.vehiclecheckid NOT IN (");
	        queryStr.append("SELECT DISTINCT vehiclecheck FROM vehiclecheckitemsvalues WHERE ");
	        queryStr.append("itemvalue = '2' AND vehiclecheck in (").append(ids).append(")) ");
	        queryStr.append("GROUP BY v.id ");

	        Query query = session.createSQLQuery(queryStr.toString()).addScalar("id", Hibernate.INTEGER).addScalar("count", Hibernate.INTEGER);
	        return query.list().iterator();
		}catch (Exception e) {
			Object[] parameters = {ids};
			handler.getException(e, "getNoObservationsColumnCheckListReport", userSessionData, parameters);
	    }
		return null;
    }

    private Iterator getObservationsColumnVehiclePursuitReport(String ids,UserSessionData userSessionData,
            Integer fromYear, Integer fromMonth, Integer toYear, Integer toMonth) throws Exception {
    	try {
	        Session session = HibernateUtil.currentSession();

	        StringBuffer queryStr = new StringBuffer("SELECT v.id as id, count(*) as count ");
	        queryStr.append("FROM VehiclePursuitRoute vc ");
	        queryStr.append("JOIN vehicles v ON vc.vehicle = v.id ");
	        queryStr.append("WHERE v.corporation = ").
	            append(String.valueOf(userSessionData.getFilter().getCorporationId()));
	        queryStr.append(" AND vc.obs is not null AND vc.obs != '' ");

	        queryStr.append(" AND (extract(year from vc.date) = ").append(String.valueOf(fromYear)).
	        append(" AND extract(month from vc.date) >= ").append(fromMonth).
	        append(" OR extract(year from vc.date) > ").
	        append(String.valueOf(fromYear)+") ");
	        queryStr.append(" AND (extract(year from vc.date) = ").append(String.valueOf(toYear)).
	        append(" AND extract(month from vc.date) <= ").append(toMonth).
	        append(" OR extract(year from vc.date) < ").
	        append(String.valueOf(toYear)+") ");

	        queryStr.append("GROUP BY v.id ");

	        Query query = session.createSQLQuery(queryStr.toString()).
	            addScalar("id", Hibernate.INTEGER).addScalar("count", Hibernate.INTEGER);
	        return query.list().iterator();
		}catch (Exception e) {
			Object[] parameters = {ids,fromYear,fromMonth,toYear,toMonth};
			handler.getException(e, "getObservationsColumnVehiclePursuitReport", userSessionData, parameters);
	    }
		return null;
    }

    private Iterator getObservationsColumnVehiclePursuitReportList(String ids,UserSessionData userSessionData,
            Integer fromYear, Integer fromMonth, Integer toYear, Integer toMonth) throws Exception {
    	try {
	        Session session = HibernateUtil.currentSession();

	        StringBuffer queryStr = new StringBuffer("SELECT vc.pursuitrouteid as id, count(*) as count ");
	        queryStr.append("FROM VehiclePursuitRoute vc ");
	        queryStr.append("JOIN vehicles v ON vc.vehicle = v.id ");
	        queryStr.append("WHERE v.corporation = ").
	            append(String.valueOf(userSessionData.getFilter().getCorporationId()));
	        queryStr.append(" AND vc.obs is not null AND vc.obs != '' ");

	        queryStr.append(" AND (extract(year from vc.date) = ").append(String.valueOf(fromYear)).
	        append(" AND extract(month from vc.date) >= ").append(fromMonth).
	        append(" OR extract(year from vc.date) > ").
	        append(String.valueOf(fromYear)+") ");
	        queryStr.append(" AND (extract(year from vc.date) = ").append(String.valueOf(toYear)).
	        append(" AND extract(month from vc.date) <= ").append(toMonth).
	        append(" OR extract(year from vc.date) < ").
	        append(String.valueOf(toYear)+") ");

	        queryStr.append("GROUP BY vc.pursuitrouteid ");

	        Query query = session.createSQLQuery(queryStr.toString()).
	            addScalar("id", Hibernate.INTEGER).addScalar("count", Hibernate.INTEGER);
	        return query.list().iterator();
		}catch (Exception e) {
			Object[] parameters = {ids,fromYear,fromMonth,toYear,toMonth};
			handler.getException(e, "getObservationsColumnVehiclePursuitReport", userSessionData, parameters);
	    }
		return null;
    }

    private Iterator getNoObservationsColumnTrailerCheckListReport(String ids,UserSessionData userSessionData) throws Exception {
    	try {
	        Session session = HibernateUtil.currentSession();

	        StringBuffer queryStr = new StringBuffer("SELECT t.id as id, count(*) as count ");
	        queryStr.append("FROM trailercheck tc ");
	        queryStr.append("JOIN trailers t ON tc.trailer = t.id ");
	        queryStr.append("WHERE t.corporation = ").append(String.valueOf(userSessionData.getFilter().getCorporationId()));
	        queryStr.append(" AND tc.trailercheckid IN (").append(ids).append(") ");
	        queryStr.append(" AND tc.trailercheckid NOT IN (");
	        queryStr.append("SELECT DISTINCT tailercheck FROM trailercheckitemsvalues WHERE ");
	        queryStr.append("itemvalue = '2' AND tailercheck in (").append(ids).append(")) ");
	        queryStr.append("GROUP BY t.id ");

	        Query query = session.createSQLQuery(queryStr.toString()).addScalar("id", Hibernate.INTEGER).addScalar("count", Hibernate.INTEGER);
	        return query.list().iterator();
		}catch (Exception e) {
			Object[] parameters = {ids};
			handler.getException(e, "NoObservationsColumnTrailerCheckListReport", userSessionData, parameters);
	    }
		return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate,vehicleview,vehicleedit,dpachecklist"
     */
    public ListResult getTrailers(String plate,int first,int count,int status,UserSessionData userSessionData) throws Exception {

        ListResult listResult=null;
        try{
            Session session = HibernateUtil.currentSession();
            StringBuffer queryStr = new StringBuffer();
            queryStr.append("SELECT COUNT(*) FROM Trailer as trailer ");
            queryStr.append(UtilityQuery.corporationDivisionsRestictions("trailer",userSessionData));
            queryStr.append(" and corporation = "+String.valueOf(userSessionData.getFilter().getCorporationId()));
            if(!Util.empty(plate)) {
                queryStr.append(" AND lower(plate) like lower(:value) ");
                plate += "%";
            }
            switch(status) {
                case VehicleData.ACTIVE:
                    queryStr.append(" AND exclusionDate is null ");
                    break;
                case VehicleData.EXCLUDED:
                    queryStr.append(" AND exclusionDate is not null ");
                    break;
            }


            Query query = session.createQuery(queryStr.toString());
            if(!Util.empty(plate)) {
                query.setParameter("value",plate);
            }

            Integer total = new Integer( ((Long)query.uniqueResult()).intValue());

            queryStr = new StringBuffer("SELECT trailer FROM Trailer as trailer ");
            queryStr.append(UtilityQuery.corporationDivisionsRestictions("trailer",userSessionData));
            queryStr.append(" and corporation = "+String.valueOf(userSessionData.getFilter().getCorporationId()));
            if(!Util.empty(plate)) {
                queryStr.append(" AND lower(plate) like lower(:value)");
                plate = "%"+plate + "%";
            }
            switch(status) {
                case VehicleData.ACTIVE:
                    queryStr.append(" AND exclusionDate is null ");
                    break;
                case VehicleData.EXCLUDED:
                    queryStr.append(" AND exclusionDate is not null ");
                    break;
            }


            queryStr.append(" ORDER BY trailer.plate ");

            query = session.createQuery(queryStr.toString());
            if(!Util.empty(plate)) {
                query.setParameter("value",plate);
            }

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

            Collection result = new LinkedList();
            Iterator it = query.list().iterator();
            while (it.hasNext()) {
                result.add(((Trailer)it.next()).getTrailerData());
            }

            listResult = new ListResult(result, total.intValue());

        } catch (Exception e) {
        	Object[] parameters = {plate,first,count,status};
            handler.getException(e,"getTrailers",userSessionData,parameters);
        }
        return listResult;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate,vehicleedit,dpachecklist"
     */
    public TrailerData findTrailer(Integer id, UserSessionData userSessionData) throws Exception {
        if (id == null) {
            throw new InvalidDataException("findTrailer","id = null");
        }
        if (userSessionData == null) {
            throw new DeslogedException("findTrailer","userSessionData = null");
        }
        Session session = null;
        TrailerData data = null;
        try {
            session = HibernateUtil.currentSession();
            Trailer trailer = (Trailer)session.load(Trailer.class,id);
            data = trailer.getTrailerData();

        } catch (Exception e) {
        	Object[] parameters = {id};
            handler.getException(e,"findTrailer",userSessionData,parameters);
        }
        return data;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate,vehicleedit,dpachecklist"
     */
    public ArrayList<TrailerData> findTrailer(String plate, UserSessionData userSessionData) throws Exception {
        if (plate == null) {
            throw new InvalidDataException("findTrailer","plate = null");
        }
        if (userSessionData == null) {
            throw new DeslogedException("findTrailer","userSessionData = null");
        }
        Session session = null;
        ArrayList<TrailerData>  data = new ArrayList<TrailerData>();
        try {
            session = HibernateUtil.currentSession();
            Query query=session.createQuery("select p from Trailer as p where p.plate=(:plate) and p.corpCountry.pk.corporation.id =(:corp) ");
            query.setParameter("plate",plate);
            query.setParameter("corp",userSessionData.getCorporationId());
            Iterator iter= query.list().iterator();
            while(iter.hasNext()){
            	Trailer trailer=(Trailer) iter.next();
            	data.add(trailer.getTrailerData());
            }

        } catch (Exception e) {
        	Object[] parameters = {plate};
            handler.getException(e,"findTrailer",userSessionData,parameters);
        }
        return data;
    }


    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate,vehicleedit"
     */
    public ListResult findTrailerVehicles(Integer id, UserSessionData userSessionData) throws Exception {
        try {
	        if (id == null) {
	            throw new InvalidDataException("findTrailerVehicles","id = null");
	        }
	        if (userSessionData == null) {
	            throw new DeslogedException("findTrailerVehicles","userSessionData = null");
	        }
	        Session session = HibernateUtil.currentSession();

            String queryStr = "SELECT id,matricula,model,trade FROM vehicles WHERE " +
                            " id IN (SELECT vehicle FROM vehicletrailers WHERE trailer = "+id.toString() +") "+
                            " AND corporation = "+String.valueOf(userSessionData.getFilter().getCorporationId());
            Query query = session.createSQLQuery(queryStr).addScalar("id",Hibernate.INTEGER).addScalar("matricula",Hibernate.STRING).addScalar("model",Hibernate.STRING).addScalar("trade",Hibernate.STRING);

            LinkedList vehicles = new LinkedList();

            Iterator it = query.list().iterator();
            while (it.hasNext()){
                Object[] data = (Object[])it.next();
                vehicles.add(new VehicleData((Integer)data[0],(String)data[1],(String)data[2],(String)data[3]));
            }

            return new ListResult(vehicles, vehicles.size());
        } catch (Exception e) {
            Object[] parameters = {id};
            handler.getException(e,"findTrailerVehicles",userSessionData,parameters);
        }
        return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,drivervehicleedit,drivercreateevent,vehiclechecklist,dpachecklist,systemaccess"
     */
    public CheckListData getCheckList(int checklist, UserSessionData userSessionData) throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.currentSession();

            String sql = "SELECT checklist FROM CheckList as checklist WHERE id = "+checklist;

            Query query = session.createQuery(sql);
            Object result = query.uniqueResult();
            if(result != null) {
                CheckList checkList = (CheckList)result;
                return checkList.getCheckListData(userSessionData.getCorporationId());
            }
        }catch (Exception e) {
            Object[] parameters = {checklist};
            handler.getException(e,"getCheckList",userSessionData,parameters);
        }
        return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,drivervehicleedit,drivercreateevent,vehiclechecklist"
     */
    public CheckListData getTruckCheckList(UserSessionData userSessionData,int type) throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.currentSession();

            int checklist = (type == CheckListData.TRUCK) ? CheckListData.TRUCK_CHECKLIST : CheckListData.TRAILER_CHECKLIST;

            String sql1 = "SELECT pk.checkList FROM CorporationCheckList WHERE corporation = (:corporation) AND type = (:type)";

            Query query = session.createQuery(sql1);
            query.setParameter("corporation",userSessionData.getCorporationId());
            query.setParameter("type",new Integer(type));
            List list = query.list();
            if(list.size() > 0) {
                return ((CheckList)list.iterator().next()).getCheckListData(userSessionData.getCorporationId());
            }


            String sql2 = "SELECT checklist FROM CheckList as checklist WHERE id = "+checklist;
            query = session.createQuery(sql2);
            Object result = query.uniqueResult();
            if(result != null) {
                CheckList checkList = (CheckList)result;
                return checkList.getCheckListData(userSessionData.getCorporationId());
            }
        }catch (Exception e) {
            Object[] parameters = {type};
            handler.getException(e,"getTruckCheckList",userSessionData,parameters);
        }
        return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     * Forestales: Consulta de Vencimientos de Sucta  y MTOP de Vehiculos y Plataformas.
     */
    public ListResult getSuctaMTOPQuery(String plate, String suctaormtop, String status,
            String vehicleorplataform, String fromMonth, String fromYear,
            String toMonth, String toYear, Integer [][] divOrgs, Integer[][] divGeos,
            int first, int count, UserSessionData userSessionData)throws Exception{
        try{

                Session session = HibernateUtil.currentSession();
                Hashtable parameters = new Hashtable();
                Calendar calendar1, calendar2;
                Collection result = new LinkedList();
                String queryStr = UtilityQuery.corporationDivisionsRestictions(vehicleorplataform,userSessionData);

                if (Util.empty(vehicleorplataform)){
                    vehicleorplataform = "vehicle";
                }
                if (Util.empty(suctaormtop)){
                    suctaormtop = "sucta";
                }

                HashSet set = new HashSet();
                String divOrgStr = "";
                if(divOrgs != null && divOrgs.length > 0) {
                    for(int j = 0; j < divOrgs.length; j++) {
                        if(divOrgs[j] != null && divOrgs[j].length > 0) {
                            divOrgStr = "";
                            divOrgStr = " and "+vehicleorplataform+".divOrg"+String.valueOf(j+1)+" in (";
                            for(int i = 0; i < divOrgs[j].length; i++) {
                                set.add(divOrgs[j][i]); // Para evitar repetidos

                                // divOrgStr += divOrgs[j][i];
                                // divOrgStr += (i == divOrgs[j].length-1) ? ") " : ",";
                            }
                            Iterator it = set.iterator();
                            int i = 0;
                            while (it.hasNext()){
                                String div = ((Integer)it.next()).toString();
                                divOrgStr += div;
                                divOrgStr += (i == set.size()-1) ? ") " : ",";
                                i++;
                            }
                            queryStr += divOrgStr;
                        }
                    }
                }

                if(divGeos != null) {
                    if(divGeos[0] != null && divGeos[0].length > 0) {
                        queryStr += "and "+vehicleorplataform+".corpCountry.pk.country in (";
                        for(int i = 0; i < divGeos[0].length; i++) {
                            queryStr += divGeos[0][i];
                            queryStr += (i == divGeos[0].length-1) ? ")" : ",";
                        }
                    }
                }

//            Driver State: Activo
                if (status.equals(String.valueOf(VehicleData.ACTIVE)))
                {
                    queryStr +=" and "+vehicleorplataform+".exclusionDate is null ";
                }
                // Driver State: Excluido
                if (status.equals(String.valueOf(VehicleData.EXCLUDED)))
                {
                    queryStr +=" and "+vehicleorplataform+".exclusionDate is not null ";
                }
                // Plate:
                if (!Util.empty(plate) && vehicleorplataform.equalsIgnoreCase("vehicle")){
                    queryStr += " and TO_UPPER(vehicle.matricula) like TO_UPPER('%"+plate+"%') ";
                }else if (!Util.empty(plate) && vehicleorplataform.equalsIgnoreCase("trailer")){
                    queryStr += " and TO_UPPER(trailer.plate) like TO_UPPER('%"+plate+"%') ";
                }


                //String subqueryStr ="";

                if (!Util.empty(fromMonth) && !Util.empty(fromYear))
                {
                    queryStr +=" and ("+vehicleorplataform+"."+suctaormtop.toLowerCase()+"Expiry >= (:date1)";
                    calendar1 = Calendar.getInstance();
                    calendar1.set(Calendar.YEAR,new Integer(fromYear).intValue());
                    calendar1.set(Calendar.MONTH,new Integer(fromMonth).intValue()-1);
                    calendar1.set(Calendar.DAY_OF_MONTH,1);
                    calendar1.set(Calendar.HOUR_OF_DAY,0);
                    calendar1.set(Calendar.MINUTE,0);
                    calendar1.set(Calendar.SECOND,0);
                    calendar1.set(Calendar.MILLISECOND,0);
                    parameters.put("date1",calendar1.getTime());
                }

                if(!Util.empty(toMonth) && !Util.empty(toYear))
                {
                    queryStr +=" and "+vehicleorplataform+"."+suctaormtop.toLowerCase()+"Expiry < (:date2))";
                    calendar2 = Calendar.getInstance();
                    calendar2.set(Calendar.YEAR, new Integer(toYear).intValue());
                    calendar2.set(Calendar.MONTH,new Integer(toMonth).intValue());
                    calendar2.set(Calendar.DAY_OF_MONTH,1);
                    calendar2.set(Calendar.HOUR_OF_DAY,0);
                    calendar2.set(Calendar.MINUTE,0);
                    calendar2.set(Calendar.SECOND,0);
                    parameters.put("date2",calendar2.getTime());
                }


                String table = (vehicleorplataform.trim().equalsIgnoreCase("vehicle")?"Vehicle":"Trailer");
                Query queryAux = session.createQuery("select count(*) " +
                        " from "+table+" as "+vehicleorplataform+" "
                        + queryStr);

                Enumeration keys = parameters.keys();
                while(keys.hasMoreElements()) {
                    String parameter = (String)keys.nextElement();
                    queryAux.setParameter(parameter,parameters.get(parameter));
                }

                List activityColl = queryAux.list();
                Iterator iter = activityColl.iterator();
                Integer total = new Integer(0);
                while (iter.hasNext()) {
                    total = new Integer (( (Long) iter.next()).intValue());
                }

                Query query = session.createQuery("select "+vehicleorplataform+" " +
                        " from "+table+" as "+vehicleorplataform+"  "
                        + queryStr +
                        " order by "+vehicleorplataform+".divOrg1.resourceKey ");

                keys = parameters.keys();
                while(keys.hasMoreElements()) {
                    String parameter = (String)keys.nextElement();
                    query.setParameter(parameter,parameters.get(parameter));
                }

                if(count != -1) {
                    query.setFirstResult(first);
                    query.setMaxResults(count);
                }

                List userColl = query.list();
                iter = userColl.iterator();
                userColl=null;
                while (iter.hasNext()) {
                    if (vehicleorplataform.equalsIgnoreCase("vehicle")){
                        Vehicle vehicle = (Vehicle) iter.next();
                        VehicleData data = vehicle.getVehicleData();
                        result.add(new SuctaMTOPData(data));
                    }else{
                        Trailer trailer = (Trailer)iter.next();
                        TrailerData data = trailer.getTrailerData();
                        result.add(new SuctaMTOPData(data));
                    }
                }

                session.close();
                return new ListResult(result,total.intValue());

        }catch (Exception e){
            Object[] parameters = {plate,suctaormtop,status,vehicleorplataform,fromMonth,fromYear,toMonth,toYear,divOrgs,divGeos,first,count};
            handler.getException(e,"getSuctaMTOPQuery",userSessionData,parameters);
        }
        return null;
    }


    /**
     * @ejb.permission role-name = "administrator,vehicleview,vehicledelete,vehicleedit,vehicleupdate,vehiclecreate,vehiclechecklist,vehicleupload"
     * @ejb.interface-method
     * @param userRequest
     * @param firstName
     * @param lastName
     * @param birthday
     *
     * Devuelve true si existe un vehiculo con esa matricula y en ese pais.
     *
     * @return
     * @throws Exception
     */
    public boolean existVehicle(UserSessionData userRequest,String plate, Integer country) throws Exception {
        Session session = null;
        try{
            // Format dateFormat;
            // dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            session = HibernateUtil.currentSession();
             Query query = session.createQuery("select vehicle from Vehicle as vehicle  "+
                   UtilityQuery.corporationDivisionsRestictions(
                        "vehicle",userRequest)+
             // ver si alcanza con restringir solo la corporacion id
                        " and vehicle.matricula = '" + plate + "' "+
                        " and vehicle.corpCountry.pk.country = " + country + " ");
                List result = query.list();
                if(result != null && result.size() > 0) {
                    return true;
                }
                return false;
        }catch(Exception e){
            Object[] parameters = {plate,country};
            handler.getException(e,"existVehicle",userRequest,parameters);
        }
        return false;

    }
     /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     *
     * Si el parametro getAll es true trae todos los items, si es false solo trae
     * los items del checklist que esten mal.
     *
     * Object[0] = Array de VehicleCheckReportDetailedDataType
     * Object[1] = Lista ordenada de ids (Integer) de items
     * Object[2] = Numero total de Checklists (Integer).
     */
    public Object[] getChecksCountDetailed(Integer fromMonth, Integer fromYear,
            Integer toMonth, Integer toYear, Integer[][] divOrgs, Integer[] divGeos,
            Text messages, int order, UserSessionData userSessionData, boolean getAll,
            String type, int count, int first, String typeCheck) throws Exception {

        LinkedList result = new LinkedList();
        List checklistColl;
        LinkedList items = new LinkedList();
        Integer total = new Integer(0);
        Object[] data;
        String divOrgSelected = getSelectedDivOrg(userSessionData);
        try {
            Session session = HibernateUtil.currentSession();

            // Matricula o plate (segun sea vehiculo o plataforma de carga)
            String plate = "matricula";
            if (type.equalsIgnoreCase("trailer")){
                plate = "plate";
            }

            String select = "SELECT DISTINCT vc."+type+"CheckId , "+
                " vc."+type+".id, vc."+type+"."+divOrgSelected+".resourceKey , " +
                "vc."+type+"."+plate+", ";

            String vehicleType = "";
            if (type.equalsIgnoreCase("trailer")){
                vehicleType += " 'generic.checklist.type.trailer' ";
            }else{
                vehicleType += " vc."+type+".type.resourceBundleId  ";
            }
            select += vehicleType;
            select += ", vc.date, vc.obs ";

            if(type.equalsIgnoreCase("vehicle")){
            	select+=", vc.inspectedby, vc.result";
            }

            StringBuffer queryStr = new StringBuffer();
            queryStr.append(" FROM "+
                    type.substring(0,1).toUpperCase()+type.substring(1)+"Check vc ");
            queryStr.append("WHERE (YEAR(vc.date) = ").
                append(String.valueOf(fromYear)).
                append(" AND MONTH(vc.date) >= ").
                append(fromMonth).
                append(" OR YEAR(vc.date) > ").
                append(String.valueOf(fromYear)+") ");
            queryStr.append(" AND (YEAR(vc.date) = ").
                append(String.valueOf(toYear)).
                append(" AND MONTH(vc.date) <= ").
                append(toMonth).append(" OR YEAR(vc.date) < ").
                append(String.valueOf(toYear)+") ");
            queryStr.append(" AND vc."+type+".corpCountry.pk.corporation.id = ").
                append(String.valueOf(userSessionData.getFilter().getCorporationId())).append(" ");
            queryStr.append(" AND vc.name NOT IN ('79','80','84') ");

            if(typeCheck!=null && typeCheck.trim().length()>0){
            	 queryStr.append(" AND name = ").
                 append("'"+typeCheck+"'").append(" ");
            }

            HashSet set = new HashSet();
            String divOrgStr = "";
            if(divOrgs != null && divOrgs.length > 0) {
                for(int j = 0; j < divOrgs.length; j++) {
                    if(divOrgs[j] != null && divOrgs[j].length > 0) {
                        divOrgStr = "";
                        divOrgStr = " and vc."+type+".divOrg"+String.valueOf(j+1)+".id in (";
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
                        queryStr.append(divOrgStr);
                    }
                }
            }
            if(divGeos != null && divGeos.length > 0) {
                queryStr.append(" AND vc."+type+".corpCountry.pk.country.id in (");
                for(int i = 0; i < divGeos.length; i++) {
                    queryStr.append(divGeos[i]);
                    queryStr.append((i == divGeos.length-1) ? ")" : ",");
                }
            }

            String orderStr = "";
            switch (order){
                case VehicleCheckReportDetailedDataType.PLATE:
                    orderStr = " ORDER BY vc."+type+"."+plate; break;
                case VehicleCheckReportDetailedDataType.CONTRACT:
                    orderStr = " ORDER BY vc."+type+"."+divOrgSelected+".resourceKey ";break;
                case VehicleCheckReportDetailedDataType.CONTRACT_TYPE:
                    orderStr = " ORDER BY vc."+type+"."+divOrgSelected+".resourceKey , type "; break;
                case VehicleCheckReportDetailedDataType.TYPE:
                    if (type.equalsIgnoreCase("trailer")){
                        orderStr = " ORDER BY vc."+type+"."+plate;
                    }else{
                        orderStr = " ORDER BY vc."+type+".type.resourceBundleId  ";
                    }
                    break;
                case VehicleCheckReportDetailedDataType.TYPE_CONTRACT:
                    if (type.equalsIgnoreCase("trailer")){
                        orderStr = " ORDER BY vc."+type+"."+divOrgSelected+".resourceKey  ";
                    }else{
                        orderStr = " ORDER BY vc."+type+".type.resourceBundleId  , vc."
                            +type+"."+divOrgSelected+".resourceKey  ";
                    }
                    break;
                default:
                    orderStr = " ORDER BY vc."+type+"."+plate; break;
            }


            if (getAll){
                // Si quiero traer todos los checklist calculo el total
                // y pagino.
                // si solo traigo los checklist con errores pagino recien
                // cuando sepa que checklist tomo
                Query queryAux = session.createQuery("select count(*) " +
                    queryStr.toString());
                checklistColl = queryAux.list();
                Iterator iter = checklistColl.iterator();
                total = new Integer(0);
                while (iter.hasNext()) {
                    total = new Integer(((Long) iter.next()).intValue());
                }
            }

            Query query = session.createQuery(select+queryStr.toString()+orderStr);

            if (getAll){
                // Si quiero traer todos los checklist calculo el total
                // y pagino la consulta,
                // si solo traigo los checklist con errores pagino recien
                // cuando sepa que checklist tomo
                if(count != -1) {
                    query.setFirstResult(first);
                    query.setMaxResults(count);
                }
            }

            checklistColl = query.list();
            StringBuffer ids = new StringBuffer("0");
            if (checklistColl != null && checklistColl.size() > 0){
                Iterator it = checklistColl.iterator();
                int i = 0;
                LinkedList aux = new LinkedList();
                while(it.hasNext()) {
                    Object[] s = (Object[])it.next();
                    // Quiero todos los checklist de los conductores en ese periodo, incluso
                    // si un conductor tuvo mas de un checklist.
                    aux.add(i,new VehicleCheckReportDetailedDataType(s,messages,order));
                    ids.append(",").append(((Integer)s[0]).toString());
                    i++;
                }

                // Traigo para cada checklist la lista de items erroneos:
                StringTokenizer stok = new StringTokenizer(ids.toString().trim(),",");
                i = 0;
                int indice = 0;
                // Como cada checklist puede tener los mismos items que otro
                // checklist, me guardo los ids de los items en un set para no
                // tener repetidos y los ordeno por su id.
                // Esto es para poder mostrarlos ordenados en el reporte.
                // Para el caso en que getAll = false, o sea solo muestro los erroneos
                // quiero mostrar todos los items que haya algun erroneo
                // (incluso cuando pagino el html)
                HashSet hashAux = new HashSet();
                while (stok.hasMoreTokens()){
                    String idCheckList = stok.nextToken().trim();
                    if (!idCheckList.equals("0")){
                        Hashtable checkListItems = getColumnsCheckListDetailed(
                                userSessionData,new Integer(idCheckList),getAll,type);
                        // Actualizo los datos del checklist para agregarle los items erroneos:
                        if (checkListItems != null){
                            VehicleCheckReportDetailedDataType  checklist =
                                (VehicleCheckReportDetailedDataType)aux.get(i);
                            if (checklist != null){
                                checklist.setItems(checkListItems);
                                result.add(indice,checklist);
                                indice++;
                                hashAux.addAll(checkListItems.keySet());
                            }
                        }
                        i++;
                    }
                }


                // genero una lista ordenada de los ids de los items:
                it = hashAux.iterator();
                String idsItems="0";
                while (it.hasNext()){
                    Integer item = (Integer)it.next();
                    idsItems+=","+item;
                    		items.add(item);
                }
                Collections.sort(items);

                items.clear();
                query = session.createSQLQuery("select i.id from point as p,items as i where i.id in("+idsItems+") and i.point = p.id order by p.pointOrder, i.itemOrder");
                Iterator iterListItemIds=query.list().iterator();
                int iAux=0;
                while(iterListItemIds.hasNext()){
                	Integer itemAux=(Integer)iterListItemIds.next();
                	iAux++;
                	if(userSessionData.getFilter().getCorporationId()==CorporationData.DPA){
                		if(typeCheck.equals("78")){
	                		if(iAux>9){//Salteo los primeros tres items
	                			items.add(itemAux);
	                		}
                		}else if(typeCheck.equals("75")){
	                		if(iAux>7){//Salteo los primeros tres items
	                			items.add(itemAux);
	                		}
                		}
                	}else{
                		items.add(itemAux);
                	}
                }

                if (!getAll){
                    // Si solo muestro los checklist erroneos,
                    // tomo count para la paginacion
                    total = new Integer(result.size());
                    if (count != -1){
                        LinkedList subResult = new LinkedList();
                        // Solo para evitar errores de indexoutofbound de la lista:
                        int last = first + count;
                        int n = 0;
                        if (first == 0) first = 1;
                        if (last > result.size()) last = result.size();
                        for (int j = first - 1; j < last - 1; j++){
                            subResult.add(n,result.get(j));
                            n++;
                        }

                        data = new Object[3];
                        VehicleCheckReportDetailedDataType[] checkData = new VehicleCheckReportDetailedDataType[result.size()];
                        for (i = 0; i < result.size(); i++){
                            checkData[i] = (VehicleCheckReportDetailedDataType)result.get(i);
                        }

                        data[0] = checkData; // Array de VehicleCheckReportDetailedDataType
                        data[1] = items; // Lista ordenada de items
                        data[2] = total; // Numero total de Checklist
                        return data;

                    }
                }
            }



            data = new Object[3];
            VehicleCheckReportDetailedDataType[] checkData = new VehicleCheckReportDetailedDataType[result.size()];
            for (int i = 0; i < result.size(); i++){
                checkData[i] = (VehicleCheckReportDetailedDataType)result.get(i);
            }

            data[0] = checkData; // Array de VehicleCheckReportDetailedDataType
            data[1] = items; // Lista ordenada de items
            data[2] = total; // Numero total de Checklist
            return data;

        }catch(Exception e) {
        	Object[] parameters = {fromMonth,fromYear,toMonth,toYear,divOrgs,divGeos,messages,order,getAll,type,count,first};
            handler.getException(e,"getChecksCountDetailed",userSessionData,parameters);
        }
        return null;
    }

    /**
     * Devuelve un hashtable con los items,values del checklist.
     * Si el parametro getAll es true trae todos los items, si es false solo trae
     * los items del checklist que esten mal.
     * @param userSessionData
     * @param idCheckList
     * @return
     */
    private Hashtable getColumnsCheckListDetailed(UserSessionData userSessionData,Integer idCheckList, boolean getAll, String type) throws Exception{
        Hashtable items = new Hashtable();
        try{
                // Valido type:
            if (!type.equalsIgnoreCase("trailer") && !type.equalsIgnoreCase("vehicle")){
                return null;
            }

            Session session = HibernateUtil.currentSession();
            StringBuffer queryStr = new StringBuffer();

            queryStr.append("SELECT item, itemvalue FROM "+type+"checkitemsvalues vc WHERE ");

            if (!getAll){
                // traigo solo los errores del checklist
                queryStr.append(" vc.itemvalue = '2' and ");
            }

            if (type.equalsIgnoreCase("trailer")){
                queryStr.append(" vc.tailercheck = "+idCheckList);
            }else{
                queryStr.append(" vc."+type+"check = "+idCheckList);
            }

            Query query = session.createSQLQuery(queryStr.toString()).addScalar("item", Hibernate.INTEGER).addScalar("itemvalue", Hibernate.STRING); //1 o 2

            List list = query.list();
            if (list != null && list.size() > 0){
                Iterator it = list.iterator();
                while (it.hasNext()){
                    Object[] itemData = (Object[])it.next();
                    try {
                    	items.put((Integer)itemData[0],new Integer((String)itemData[1]));
                    }catch (Exception e) {
                    	items.put((Integer)itemData[0],new Integer(0));
					}
                }
            }else {
                return null;
            }

        }catch (Exception e){
            Object[] parameters = {idCheckList,getAll,type};
            handler.getException(e,"getColumnsCheckListDetailed",userSessionData,parameters);
        }
        return items;
    }


    /**
     * Devuelve un hashtable con los (items, textos) de los items pasados
     * por parametro.
     * Si items = null, devuelve un hastable null.
     *
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     *
     * @param userSessionData
     * @param items
     * @return hashtable con los (items, textos) de los items.
     */
    public Hashtable getCheckListItemsText(UserSessionData userSessionData,
            LinkedList items) throws Exception{

        if (items == null) return null;
        try{
            Session session = HibernateUtil.currentSession();

            // Genero un string con la lista de items separados por coma
            String strItems = "0";
            Iterator it = items.iterator();
            while (it.hasNext()){
                Integer item = (Integer)it.next();
                strItems += ","+item.toString();
            }

            StringBuffer queryStr = new StringBuffer();
            queryStr.append(
                    "SELECT id as item, text "+
                    "FROM items "+
                    "WHERE id IN ("+strItems+")");

            Query query = session.createSQLQuery(queryStr.toString())
                .addScalar("item", Hibernate.INTEGER)
                .addScalar("text", Hibernate.STRING);

            List list = query.list();
            if (list != null && list.size() > 0){
                it = list.iterator();
                Hashtable result = new Hashtable();
                while (it.hasNext()){
                    Object[] itemData = (Object[])it.next();
                    result.put((Integer)itemData[0],(String)itemData[1]);
                }
                return result;
            }
            return null;

        }catch (Exception e){
            Object[] parameters = {items};
            handler.getException(e,"getCheckListItemsText",userSessionData,parameters);
        }
        return null;
    }


    /**
     * @ejb.permission role-name = "administrator,vehicleview,vehicledelete,vehicleedit,vehicleupdate,vehiclecreate,vehiclechecklist,vehicleupload"
     * @ejb.interface-method
     */
    public Collection getTrailerPlates(String trailers, UserSessionData userSessionData) throws Exception {
        try{
            Query query = HibernateUtil.currentSession().createSQLQuery("SELECT plate FROM trailers WHERE id IN ("+trailers+")").addScalar("plate",Hibernate.STRING);
            return query.list();
        }catch(Exception e){
            Object[] parameters = {trailers};
            handler.getException(e,"getTrailerPlates",userSessionData,parameters);
        }
        return null;
    }

    /**
     * @ejb.permission role-name = "administrator,query"
     * @ejb.interface-method
     */
    public Collection getTrailerWithoutCheckList(Date start, Date end, int status, Integer[][] divOrgs, Integer[] divGeos, UserSessionData userSessionData) throws Exception {
        try {
            String[] s = userSessionData.getFilter().getFields("trailer");
            String queryStr = "SELECT trailer.id,plate,model,trade,c.resourcekey AS country" +s[0];
            queryStr += " FROM trailers trailer,country c "+s[1]+" ";
            queryStr += UtilityQuery.corporationDivisionsRestictionsSQL("trailer",userSessionData);
            queryStr +=" AND trailer.country = c.id ";
            if(divOrgs != null && divOrgs.length > 0) {
                for(int j = 0; j < divOrgs.length; j++) {
                    if(divOrgs[j] != null && divOrgs[j].length > 0) {
                        queryStr += " AND trailer.divOrg"+String.valueOf(j+1)+" in (";
                        for(int i = 0; i < divOrgs[j].length; i++) {
                            queryStr += divOrgs[j][i];
                            queryStr += (i == divOrgs[j].length-1) ? ") " : ",";
                        }
                    }
                }
            }
            if(divGeos != null) {
                if(divGeos[0] != null && divGeos.length > 0) {
                    queryStr += " AND trailer.country in (";
                    for(int i = 0; i < divGeos.length; i++) {
                        queryStr += divGeos[i];
                        queryStr += (i == divGeos.length-1) ? ")" : ",";
                    }
                }
            }
            if(status == VehicleData.ACTIVE) {
                queryStr +=" AND trailer.exclusionDate is null ";
            }
            // Driver State: Excluido
            if(status == VehicleData.EXCLUDED) {
                queryStr +=" AND trailer.exclusionDate is not null ";
            }
            queryStr += s[2];
            queryStr += " AND trailer.id NOT IN (SELECT trailer FROM trailercheck WHERE date >= "+Util.formatSQL(start)+" AND date >= "+Util.formatSQL(start)+" ) ";

            SQLQuery query = HibernateUtil.currentSession().createSQLQuery(queryStr);
            query.addScalar("id",Hibernate.INTEGER);
            query.addScalar("plate",Hibernate.STRING);
            query.addScalar("trade",Hibernate.STRING);
            query.addScalar("model",Hibernate.STRING);
            query.addScalar("country",Hibernate.STRING);
            StringTokenizer token = new StringTokenizer(s[3],",");
            while(token.hasMoreTokens()) {
                query.addScalar(token.nextToken(),Hibernate.STRING);
            }
            return query.list();
        } catch (HibernateException e) {
            Object[] parameters = {start,end,status,divOrgs,divGeos};
            handler.getException(e,"getTrailerWithoutCheckList",userSessionData,parameters);
        }
        return new LinkedList();
    }

    /**
     * @ejb.permission role-name = "administrator,query"
     * @ejb.interface-method
     */
    public Hashtable getTrailerLastCheckList(String ids, UserSessionData userSessionData) throws Exception {
        Hashtable result = new Hashtable();
        try {
            String queryStr = "SELECT trailer, max(date) AS date FROM trailercheck WHERE trailer IN ("+ids+") GROUP BY trailer";
            SQLQuery query = HibernateUtil.currentSession().createSQLQuery(queryStr);
            query.addScalar("trailer",Hibernate.INTEGER);
            query.addScalar("date",Hibernate.DATE);
            Iterator it = query.list().iterator();
            while(it.hasNext()) {
                Object[] data = (Object[])it.next();
                result.put(data[0],data[1]);
            }
        } catch (HibernateException e) {
            Object[] parameters = {ids};
            handler.getException(e,"getTrailerLastCheckList",userSessionData,parameters);
        }
        return result;
    }

    /**
     * @ejb.permission role-name = "administrator,query"
     * @ejb.interface-method
     */
    public Collection getVehicleWithoutCheckList(Date start, Date end, int status, Integer[][] divOrgs, Integer[] divGeos, UserSessionData userSessionData) throws Exception {
        try {
            String[] s = userSessionData.getFilter().getFields("vehicle");
            String queryStr = "SELECT vehicle.id,matricula,model,trade,c.resourcekey AS country" +s[0];
            queryStr += " FROM vehicles vehicle,country c "+s[1]+" ";
            queryStr += UtilityQuery.corporationDivisionsRestictionsSQL("vehicle",userSessionData);
            queryStr +=" AND vehicle.country = c.id ";
            if(divOrgs != null && divOrgs.length > 0) {
                for(int j = 0; j < divOrgs.length; j++) {
                    if(divOrgs[j] != null && divOrgs[j].length > 0) {
                        queryStr += " AND vehicle.divOrg"+String.valueOf(j+1)+" in (";
                        for(int i = 0; i < divOrgs[j].length; i++) {
                            queryStr += divOrgs[j][i];
                            queryStr += (i == divOrgs[j].length-1) ? ") " : ",";
                        }
                    }
                }
            }
            if(divGeos != null) {
                if(divGeos[0] != null && divGeos.length > 0) {
                    queryStr += " AND vehicle.country in (";
                    for(int i = 0; i < divGeos.length; i++) {
                        queryStr += divGeos[i];
                        queryStr += (i == divGeos.length-1) ? ")" : ",";
                    }
                }
            }
            if(status == VehicleData.ACTIVE) {
                queryStr +=" AND vehicle.exclusionDate is null ";
            }
            // Driver State: Excluido
            if(status == VehicleData.EXCLUDED) {
                queryStr +=" AND vehicle.exclusionDate is not null ";
            }
            queryStr += s[2];
            queryStr += " AND vehicle.id NOT IN (SELECT vehicle FROM vehiclecheck WHERE date >= "+Util.formatSQL(start)+" AND date >= "+Util.formatSQL(start)+" ) ";

            SQLQuery query = HibernateUtil.currentSession().createSQLQuery(queryStr);
            query.addScalar("id",Hibernate.INTEGER);
            query.addScalar("matricula",Hibernate.STRING);
            query.addScalar("trade",Hibernate.STRING);
            query.addScalar("model",Hibernate.STRING);
            query.addScalar("country",Hibernate.STRING);
            StringTokenizer token = new StringTokenizer(s[3],",");
            while(token.hasMoreTokens()) {
                query.addScalar(token.nextToken(),Hibernate.STRING);
            }
            return query.list();
        } catch (HibernateException e) {
        	Object[] parameters = {start,end,status,divOrgs,divGeos};
        	handler.getException(e, "getVehicleWithoutCheckList", userSessionData, parameters);
        }
        return new LinkedList();
    }

    /**
     * @ejb.permission role-name = "administrator,query"
     * @ejb.interface-method
     */
    public Hashtable getVehicleLastCheckList(String ids, UserSessionData userSessionData) throws Exception {
        Hashtable result = new Hashtable();
        try {
            String queryStr = "SELECT vehicle, max(date) AS date FROM vehiclecheck WHERE vehicle IN ("+ids+") GROUP BY vehicle";
            SQLQuery query = HibernateUtil.currentSession().createSQLQuery(queryStr);
            query.addScalar("vehicle",Hibernate.INTEGER);
            query.addScalar("date",Hibernate.DATE);
            Iterator it = query.list().iterator();
            while(it.hasNext()) {
                Object[] data = (Object[])it.next();
                result.put(data[0],data[1]);
            }
        } catch (HibernateException e) {
        	Object[] parameters = {ids};
        	handler.getException(e, "getVehicleLastCheckList", userSessionData, parameters);
        }
        return result;
    }

    private String getString(Collection<Integer> collection) {
        String result = "";
        Iterator<Integer> it =  collection.iterator();
        if(it.hasNext()) {
            result += String.valueOf(it.next());
        }
        while(it.hasNext()) {
            result += ","+String.valueOf(it.next());
        }
        return result;
    }

   /**
    * @throws Exception
    * @ejb.interface-method
    * @ejb.permission role-name = "administrator,fleetupdate,fleetedit,eventcreate,drivercreateevent"
    */
    public Collection getPlates(Collection vehicles,UserSessionData userSessionData) throws Exception {
        MemoryLog.getInstance().log(MemoryLog.START,"getPlates",getClass());
        Collection plates = new LinkedList();
        try{
            if(vehicles.size() > 0) {
                Session session = HibernateUtil.currentSession();
                String sql = "SELECT matricula FROM vehicles WHERE id IN (";
                Iterator it = vehicles.iterator();
                if(it.hasNext()) {
                    sql += String.valueOf(it.next());
                    while(it.hasNext()) {
                        sql += ","+String.valueOf(it.next());
                    }
                }
                sql += ")";
                Query q = session.createSQLQuery(sql).addScalar("matricula",Hibernate.STRING);
                plates = q.list();
            }
        } catch (Exception e) {
        	Object[] parameters = {vehicles};
            handler.getException(e,"getPlates",userSessionData,parameters);
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
             Session session = HibernateUtil.currentSession();
             SQLQuery query = session.createSQLQuery("SELECT id,plate,model,trade FROM trailers t JOIN vehicletrailers vt ON vt.trailer = t.id WHERE vt.vehicle = "+String.valueOf(vehicle));
             query.addScalar("id",Hibernate.INTEGER);
             query.addScalar("plate",Hibernate.STRING);
             query.addScalar("model",Hibernate.STRING);
             query.addScalar("trade",Hibernate.STRING);
             Iterator it = query.list().iterator();
             while(it.hasNext()) {
                 trailers.add(new TrailerMainData((Object[])it.next()));
             }
         } catch (Exception e) {
         	Object[] parameters = {vehicle};
             handler.getException(e,"getVehicleTrailers",userSessionData,parameters);
         }
         MemoryLog.getInstance().log(MemoryLog.END,"getVehicleTrailers",getClass());
         return trailers;
     }

     /**
      * @ejb.interface-method
      * @ejb.permission role-name =
      *                 "administrator,eventcreate,finecreate,vehicleedit,drivervehicleedit,vehiclechecklist,dpachecklist"
      */
      public ArrayList<VehicleData> getVehiclesNonIn(ArrayList<String> plates,UserSessionData userSessionData) throws Exception {
          MemoryLog.getInstance().log(MemoryLog.START,"getVehicleTrailers",getClass());
          ArrayList<VehicleData> vehicles = new  ArrayList<VehicleData>();
          try {
              Session session = HibernateUtil.currentSession();
              String platesStr="'zzzzz'";
              Iterator<String> iterPlate=plates.iterator();
              while(iterPlate.hasNext()){
            	  if(platesStr.length()>0){
            		  platesStr+=",";
            	  }
            	  platesStr+="'"+iterPlate.next().trim()+"'";
              }

              Query query = session.createQuery("SELECT v FROM Vehicle as v "+UtilityQuery.corporationDivisionsRestictions("v",userSessionData)+" and  v.matricula not in("+platesStr+")  ");

              Iterator it = query.list().iterator();
              while(it.hasNext()) {
            	  vehicles.add(((Vehicle)it.next()).getVehicleData());
              }
          } catch (Exception e) {
          	Object[] parameters = {plates};
              handler.getException(e,"getVehiclesNonIn",userSessionData,parameters);
          }
          MemoryLog.getInstance().log(MemoryLog.END,"getVehiclesNonIn",getClass());
          return vehicles;
      }
      /**
       * @ejb.interface-method
       * @ejb.permission role-name =
       *                 "administrator,eventcreate,finecreate,vehicleedit,drivervehicleedit,vehiclechecklist,dpachecklist"
       */
       public ArrayList<TrailerData> getTrailersNonIn(ArrayList<String> plates,UserSessionData userSessionData) throws Exception {
           MemoryLog.getInstance().log(MemoryLog.START,"getVehicleTrailers",getClass());
           ArrayList<TrailerData> trailers = new  ArrayList<TrailerData>();
           try {
               Session session = HibernateUtil.currentSession();
               String platesStr="'zzzzz'";
               Iterator<String> iterPlate=plates.iterator();
               while(iterPlate.hasNext()){
             	  if(platesStr.length()>0){
             		  platesStr+=",";
             	  }
             	  platesStr+="'"+iterPlate.next().trim()+"'";
               }

               Query query = session.createQuery("SELECT t FROM Trailer as t "+UtilityQuery.corporationDivisionsRestictions("t",userSessionData)+" and t.plate not in("+platesStr+")  ");

               Iterator it = query.list().iterator();
               while(it.hasNext()) {
            	   trailers.add(((Trailer)it.next()).getTrailerData());
               }
           } catch (Exception e) {
           	Object[] parameters = {plates};
               handler.getException(e,"getTrailersNonIn",userSessionData,parameters);
           }
           MemoryLog.getInstance().log(MemoryLog.END,"getTrailersNonIn",getClass());
           return trailers;
       }

     /**
      * @ejb.interface-method
      * @ejb.permission role-name = "administrator,eventcreate,finecreate,drivervehicleedit,drivercreateevent,vehiclechecklist"
      */
     public Collection getCheckListTrailers(Integer vehicleCheckList,UserSessionData userSessionData) throws Exception {
         MemoryLog.getInstance().log(MemoryLog.START,"getCheckListTrailers",getClass());
         Collection trailerChecks = new LinkedList();
         try {
             Session session = HibernateUtil.currentSession();
             Query q = session.createQuery("SELECT vtc.pk.trailer FROM VehicleTrailerCheck vtc WHERE vtc.pk.vehicle = "+String.valueOf(vehicleCheckList));
             Iterator it = q.list().iterator();
             while(it.hasNext()) {
                 trailerChecks.add(((TrailerCheck)it.next()).getTrailerCheckData());
             }
         } catch (Exception e) {
         	Object[] parameters = {vehicleCheckList};
             handler.getException(e,"getCheckListTrailers",userSessionData,parameters);
         }
         MemoryLog.getInstance().log(MemoryLog.END,"getCheckListTrailers",getClass());
         return trailerChecks;
     }

     /**
      * @ejb.interface-method
      * @ejb.permission role-name = "administrator,query"
      */
     public Collection getUncoformities(Integer fromMonth,Integer fromYear,Integer toMonth,Integer toYear,String[] divOrgs,UserSessionData userSessionData) throws Exception {
        try {
            Session session = HibernateUtil.currentSession();
        	String sql = "SELECT d.id,d.resourcekey,COUNT(*) AS count " +
        			"FROM unconformities u " +
        			"JOIN divorgitemlevel1s d ON d.id = u.contractor " +
        			"JOIN vehicles v ON v.id = u.id ";
        	sql += "WHERE v.corporation = "+String.valueOf(userSessionData.getFilter().getCorporationId());
            if(divOrgs == null || divOrgs.length == 0 || (divOrgs.length == 1 && divOrgs[0].equals("0"))) {
                String[] st = userSessionData.getFilter().getDivOrgLeaves();
                sql += " AND u.contractor in ("+st[2]+") ";
            }else {
            	sql += " AND u.contractor in (";
                for(int i = 0; i < divOrgs.length; i++) {
                	sql += divOrgs[i];
                    sql += (i == divOrgs.length-1) ? ")" : ",";
                }
            }
            if(fromMonth != null && fromYear != null) {
            	sql += " AND (EXTRACT(YEAR FROM u.date) = '"+String.valueOf(fromYear)+"' AND EXTRACT(MONTH FROM u.date) >= '"+fromMonth+"' OR EXTRACT(YEAR FROM u.date) > '"+String.valueOf(fromYear)+"') ";
            }
            if(toMonth != null && toYear != null) {
            	sql += " AND (EXTRACT(YEAR FROM u.date) = '"+String.valueOf(toYear)+"' AND EXTRACT(MONTH FROM u.date) <= '"+toMonth+"' OR EXTRACT(YEAR FROM u.date) < '"+String.valueOf(toYear)+"') ";
            }
            sql += " GROUP BY d.id,d.resourcekey";

            Query q = session.createSQLQuery(sql).addScalar("id",Hibernate.INTEGER).addScalar("resourcekey",Hibernate.STRING).addScalar("count",Hibernate.INTEGER);
            return q.list();
       } catch (Exception e) {
        	 Object[] p = {fromMonth,fromYear,toMonth,toYear,divOrgs};
            handler.getException(e,"getUncoformities",userSessionData,p);
        }
        return new LinkedList();
     }

     /**
      * @ejb.interface-method
      * @ejb.permission role-name = "administrator,query"
      */
     public Collection getDetailedUncoformities(Integer fromMonth,Integer fromYear,Integer toMonth,Integer toYear,String[] divOrgs,UserSessionData userSessionData) throws Exception {
        try {
            Session session = HibernateUtil.currentSession();
        	String sql = "SELECT v.matricula,u.date,u.origin,u.department,u.charge,u.observations,d.id,d.resourcekey " +
        			"FROM unconformities u " +
        			"JOIN divorgitemlevel1s d ON d.id = u.contractor " +
        			"JOIN vehicles v ON v.id = u.id ";
        	sql += "WHERE v.corporation = "+String.valueOf(userSessionData.getFilter().getCorporationId());
            if(divOrgs == null || divOrgs.length == 0 || (divOrgs.length == 1 && divOrgs[0].equals("0"))) {
                String[] st = userSessionData.getFilter().getDivOrgLeaves();
                sql += " AND u.contractor in ("+st[2]+") ";
            }else {
            	sql += " AND u.contractor in (";
                for(int i = 0; i < divOrgs.length; i++) {
                	sql += divOrgs[i];
                    sql += (i == divOrgs.length-1) ? ")" : ",";
                }
            }
            if(fromMonth != null && fromYear != null) {
            	sql += " AND (EXTRACT(YEAR FROM u.date) = '"+String.valueOf(fromYear)+"' AND EXTRACT(MONTH FROM u.date) >= '"+fromMonth+"' OR EXTRACT(YEAR FROM u.date) > '"+String.valueOf(fromYear)+"') ";
            }
            if(toMonth != null && toYear != null) {
            	sql += " AND (EXTRACT(YEAR FROM u.date) = '"+String.valueOf(toYear)+"' AND EXTRACT(MONTH FROM u.date) <= '"+toMonth+"' OR EXTRACT(YEAR FROM u.date) < '"+String.valueOf(toYear)+"') ";
            }
            sql += " ORDER BY d.resourcekey,d.id,u.date";

            Query q = session.createSQLQuery(sql).addScalar("matricula",Hibernate.STRING).addScalar("date",Hibernate.DATE).addScalar("origin",Hibernate.STRING).addScalar("department",Hibernate.STRING)
            				.addScalar("charge",Hibernate.INTEGER).addScalar("observations",Hibernate.STRING).addScalar("id",Hibernate.INTEGER).addScalar("resourcekey",Hibernate.STRING);
            return q.list();
       } catch (Exception e) {
        	 Object[] p = {fromMonth,fromYear,toMonth,toYear,divOrgs};
            handler.getException(e,"getUncoformities",userSessionData,p);
        }
        return new LinkedList();
     }


     /**
      * @ejb.interface-method
      * @ejb.permission role-name = "administrator,query"
      */
     public Hashtable getVehiclePursuitRouteList(Integer fromMonth, Integer fromYear,
             Integer toMonth, Integer toYear, Integer[][] divOrgs, Integer[] divGeos,
             Text messages, int order, UserSessionData userSessionData) throws Exception {

    	 Hashtable result = new Hashtable();
         try {
             Session session = HibernateUtil.currentSession();

             StringBuffer queryStr = new StringBuffer("SELECT vc.pursuitRouteId,vc.vehicle.id, ");

             int corporationId = userSessionData.getFilter().getCorporationId().intValue();
             // Colonvade muestra el Transportista, que esta en el dviorg3
             if (corporationId == CorporationData.COLONVADE || corporationId == CorporationData.LOSPIQUES){
                 queryStr.append(" vc.vehicle.divOrg3.resourceKey, ");
             } else{
                 queryStr.append(" vc.vehicle.divOrg1.resourceKey, ");
             }

             queryStr.append(" vc.vehicle.matricula, vc.vehicle.type.resourceBundleId, vc.date ");
             queryStr.append(" FROM VehiclePursuitRoute vc ");
             queryStr.append(" WHERE (YEAR(vc.date) = ").append(String.valueOf(fromYear)).append(" AND MONTH(vc.date) >= ").append(fromMonth).append(" OR YEAR(vc.date) > ").append(String.valueOf(fromYear)+") ");
             queryStr.append(" AND (YEAR(vc.date) = ").append(String.valueOf(toYear)).append(" AND MONTH(vc.date) <= ").append(toMonth).append(" OR YEAR(vc.date) < ").append(String.valueOf(toYear)+") ");
             queryStr.append(" AND vc.vehicle.corpCountry.pk.corporation.id = ").append(String.valueOf(userSessionData.getFilter().getCorporationId())).append(" ");

             HashSet set = new HashSet();
             String divOrgStr = "";
             if(divOrgs != null && divOrgs.length > 0) {
                 for(int j = 0; j < divOrgs.length; j++) {
                     if(divOrgs[j] != null && divOrgs[j].length > 0) {
                         divOrgStr = "";
                         divOrgStr = " and vc.vehicle.divOrg"+String.valueOf(j+1)+" in (";
                         set = new HashSet();
                         for(int i = 0; i < divOrgs[j].length; i++) {
                             set.add(divOrgs[j][i]); // Para evitar repetidos

                             // divOrgStr += divOrgs[j][i];
                             // divOrgStr += (i == divOrgs[j].length-1) ? ") " : ",";
                         }
                         Iterator it = set.iterator();
                         int i = 0;
                         while (it.hasNext()){
                             String div = ((Integer)it.next()).toString();
                             divOrgStr += div;
                             divOrgStr += (i == set.size()-1) ? ") " : ",";
                             i++;
                         }
                         //System.out.println(divOrgStr);
                         queryStr.append(divOrgStr);
                     }
                 }
             }
             if(divGeos != null && divGeos.length > 0) {
                 queryStr.append(" AND vc.vehicle.corpCountry.pk.country in (");
                 for(int i = 0; i < divGeos.length; i++) {
                     queryStr.append(divGeos[i]);
                     queryStr.append((i == divGeos.length-1) ? ")" : ",");
                 }
             }

             StringBuffer ids = new StringBuffer("0");
             Query query = session.createQuery(queryStr.toString());
             Iterator it = query.list().iterator();
             while(it.hasNext()) {
                 Object[] s = (Object[])it.next();
                 VehiclePursuitReportDataType v = new VehiclePursuitReportDataType(s, messages, order);
                 v.setDate((Date)s[5]);
                 result.put(s[0],v);
                 ids.append(",").append(s[0]);
             }

//           Puntos 1 y 2
             String[] items = {"870,871,872,873,874,875,876,877,878,879," +
                     "880,881,882,883,884,885,886,887,888," +
                     "889,890,891,892,893,894,895,896","897,898,899,900,901"};

             for(int i = 0; i < 2; i++) {
                 Iterator itCount = getColumnVehiclePursuitReportList(items[i],ids.toString(),
                         userSessionData);
                 while(itCount.hasNext()) {
                     Object[] counts = (Object[])itCount.next();
                     if (result.containsKey(counts[0])){
                         ((VehiclePursuitReportDataType)result.get(counts[0])).
                             setCount(((Integer)counts[1]).intValue(),i);
                     }
                 }
             }

             // Punto 3
             String [] items3 = {"902,903,904,905,906"};
             Iterator itCount = getColumnThreeVehiclePursuitReportList(items3[0],ids.toString(),
                         userSessionData);
             while(itCount.hasNext()) {
                 Object[] counts = (Object[])itCount.next();
                 if (result.containsKey(counts[0])){
                     ((VehiclePursuitReportDataType)result.get(counts[0])).
                         setCount(((Integer)counts[1]).intValue(),2);
                 }
             }


             // Observaciones:
             itCount = getObservationsColumnVehiclePursuitReportList(ids.toString(),
                     userSessionData,fromYear,fromMonth,toYear,toMonth);
             while(itCount.hasNext()) {
                 Object[] counts = (Object[])itCount.next();
                 if (result.containsKey(counts[0])){
                     ((VehiclePursuitReportDataType)result.get(counts[0])).
                         setCount(((Integer)counts[1]).intValue(),3);
                 }
             }


         }catch(Exception e) {
         	Object[] parameters = {fromMonth,fromYear,toMonth,toYear,divOrgs,divGeos,messages,order};
             handler.getException(e,"getVehiclePursuitRouteCount",userSessionData,parameters);
         }
         return result;
     }

     /**
      * @throws Exception
      * @ejb.interface-method
      * @ejb.permission role-name = "vehicleedit,vehicleupdate,vehiclechecklist"
      */
    public String[] findVehicleCheckListData(Integer vehicle,UserSessionData userSessionData) throws Exception {
    	String[] values = {"","",""};
    	try {
    		Session session = HibernateUtil.currentSession();
    		Query query = session.createSQLQuery("SELECT matricula,trade,model FROM vehicles WHERE id = "+String.valueOf(vehicle)).addScalar("matricula", Hibernate.STRING).addScalar("trade", Hibernate.STRING).addScalar("model", Hibernate.STRING);
    		Iterator it = query.list().iterator();
    		if(it.hasNext()) {
    			Object[] data = (Object[])it.next();
    			for(int i = 0; i < 3; i++) {
    				if(data[i] != null) {
    					values[i] = (String)data[i];
    				}
    			}
    		}
    	}catch (Exception e) {
         	Object[] parameters = {vehicle};
            handler.getException(e,"findVehicleCheckListData",userSessionData,parameters);
		}
    	return values;
    }

    /**
     * Contratos de Alquiler de Vehiculos Allergan
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query,vehicleview,vehicleupdate,vehicleedit"
     * @return
     */
	public Collection findVehicleContracts(Integer id, UserSessionData userSessionData) throws Exception {
		Collection contracts = new LinkedList();
		try{
			Session session = HibernateUtil.currentSession();
    		Query query = session.createQuery(
    				"SELECT DISTINCT v FROM VehicleContract v WHERE v.saved = 't' AND v.pk.vehicle.id = " +String.valueOf(id)+" ORDER BY date DESC");
    		List list  = query.list();
    		if (list != null){
    			Iterator it = list.iterator();
    			while (it.hasNext()){
    				VehicleContract v = (VehicleContract) it.next();
    				VehicleContractData data = v.getData();
    				contracts.add(data);
    			}
    		}
		}catch (Exception e){
			Object[] parameters = {id};
            handler.getException(e,"findVehicleContracts",userSessionData,parameters);
		}
		return contracts;
	}

   /**
    * @ejb.interface-method
    * @ejb.permission role-name = "administrator,query"
    */
  public Collection getCheckLists(Integer monthFrom, Integer yearFrom,Integer monthTo, Integer yearTo, Integer[][] divOrgs, Integer[] divGeos, int[] order, UserSessionData userSessionData) throws Exception {
   	Collection checkLists = new LinkedList();
   	try {
   		Session session = HibernateUtil.currentSession();
   		String sql = "SELECT vc.vehicleCheckId,vc.date,v.type,v.matricula,d.resourcekey,vcv.item,vcv.itemvalue,vc.obs,vc.name "+
   			"from vehiclecheck vc "+
   			"join vehicles v on v.id = vc.vehicle "+
   			"join vehiclecheckitemsvalues vcv on vcv.vehiclecheck = vc.vehicleCheckId "+
   			"join items i on i.id = vcv.item "+
   			"join point p on p.id = i.point "+
   			"join divorgitemlevel1s d on d.id = v.divorg1";
   		sql += UtilityQuery.corporationDivisionsRestictionsSQL("v", userSessionData);

   		HashSet set = new HashSet();
	        if(divOrgs != null && divOrgs.length > 0) {
	            for(int j = 0; j < divOrgs.length; j++) {
	                if(divOrgs[j] != null && divOrgs[j].length > 0) {
	                    sql += " AND v.divOrg"+String.valueOf(j+1)+" IN (";
	                    for(int i = 0; i < divOrgs[j].length; i++) {
	                        set.add(divOrgs[j][i]); // Para evitar repetidos
	                    }
	                    Iterator it = set.iterator();
	                    int i = 0;
	                    while (it.hasNext()){
	                        sql += ((Integer)it.next()).toString();
	                        sql += (i == set.size()-1) ? ") " : ",";
	                        i++;
	                    }
	                }
	            }
	        }

	        if(divGeos != null) {
	            if(divGeos != null && divGeos.length > 0) {
	            	sql += " AND v.country IN (";
	                for(int i = 0; i < divGeos.length; i++) {
	                	sql += divGeos[i];
	                    sql += (i == divGeos.length-1) ? ")" : ",";
	                }
	            }
	        }

	        sql += "AND (Extract(year from vc.date) = "+yearFrom+" AND Extract(month from vc.date) >= "+monthFrom+" OR Extract(year from vc.date) > "+yearFrom+") ";
	        sql += "AND (Extract(year from vc.date) = "+yearTo+" AND Extract(month from vc.date) <= "+monthTo+" OR Extract(year from vc.date) < "+yearTo+") ";
	        sql += "ORDER BY vc.date,vc.vehicleCheckId,p.pointorder,i.itemorder";

	        SQLQuery q = session.createSQLQuery(sql);
	        q.addScalar("vehicleCheckId", Hibernate.INTEGER);
	        q.addScalar("date", Hibernate.DATE);
	        q.addScalar("type", Hibernate.STRING);
	        q.addScalar("matricula", Hibernate.STRING);
	        q.addScalar("resourcekey", Hibernate.STRING);
	        q.addScalar("item", Hibernate.INTEGER);
	        q.addScalar("itemvalue", Hibernate.STRING);
	        q.addScalar("obs", Hibernate.STRING);
	        q.addScalar("name", Hibernate.STRING);
	        Iterator it  = q.list().iterator();

	        int lastId = -1;
	        int index = 0;
	        CimsaCheckListData checkList = null;
	        while(it.hasNext()) {
	        	Object[] data = (Object[])it.next();
	        	int id = ((Integer)data[0]).intValue();
	        	if(id != lastId) {
	        		lastId = id;
	        		if(checkList != null) {
	        			checkLists.add(checkList);
	        		}
	        		checkList = new CimsaCheckListData();
	        		checkList.setContractor((String)data[4]);
	        		checkList.setDate((Date)data[1]);
	        		checkList.setOrder(order);
	        		checkList.setPlate((String)data[3]);
	        		checkList.setType((String)data[2]);
	        		checkList.setItemValue(0,(String)data[6]);
	        		checkList.setObs((String)data[7]);
	        		checkList.setName((String)data[8]);
	        		index = 1;
	        	}else {
	        		checkList.setItemValue(index,String.valueOf(data[6]));
	        		index++;
	        	}
	        }
   	}catch (Exception e) {
        	Object[] parameters = {monthFrom,yearFrom,monthTo,yearTo,divOrgs,divGeos};
           handler.getException(e,"findVehicleCheckListData",userSessionData,parameters);
		}
   	return checkLists;
   }

   /**
    * @ejb.interface-method
    * @ejb.permission role-name = "administrator,query"
    */
   public Hashtable getVehiclePursuitRouteCountByItem(Integer fromMonth, Integer fromYear,
           Integer toMonth, Integer toYear, Integer[][] divOrgs, Integer[] divGeos,
           Text messages, int order, UserSessionData userSessionData) throws Exception {

       Hashtable result = new Hashtable();
       try {
           Session session = HibernateUtil.currentSession();

           StringBuffer queryStr = new StringBuffer("SELECT vc.pursuitRouteId,vc.vehicle.id, ");

           int corporationId = userSessionData.getFilter().getCorporationId().intValue();
           // Colonvade muestra el Transportista, que esta en el dviorg3
           if (corporationId == CorporationData.COLONVADE || corporationId == CorporationData.LOSPIQUES){
               queryStr.append(" vc.vehicle.divOrg3.resourceKey, ");
           } else{
               queryStr.append(" vc.vehicle.divOrg1.resourceKey, ");
           }

           queryStr.append(" vc.vehicle.matricula, vc.vehicle.type.resourceBundleId ");
           queryStr.append(" FROM VehiclePursuitRoute vc ");
           queryStr.append(" WHERE (YEAR(vc.date) = ").append(String.valueOf(fromYear)).append(" AND MONTH(vc.date) >= ").append(fromMonth).append(" OR YEAR(vc.date) > ").append(String.valueOf(fromYear)+") ");
           queryStr.append(" AND (YEAR(vc.date) = ").append(String.valueOf(toYear)).append(" AND MONTH(vc.date) <= ").append(toMonth).append(" OR YEAR(vc.date) < ").append(String.valueOf(toYear)+") ");
           queryStr.append(" AND vc.vehicle.corpCountry.pk.corporation.id = ").append(String.valueOf(userSessionData.getFilter().getCorporationId())).append(" ");

           HashSet set = new HashSet();
           String divOrgStr = "";
           if(divOrgs != null && divOrgs.length > 0) {
               for(int j = 0; j < divOrgs.length; j++) {
                   if(divOrgs[j] != null && divOrgs[j].length > 0) {
                       divOrgStr = "";
                       divOrgStr = " and vc.vehicle.divOrg"+String.valueOf(j+1)+" in (";
                       set = new HashSet();
                       for(int i = 0; i < divOrgs[j].length; i++) {
                           set.add(divOrgs[j][i]); // Para evitar repetidos

                           // divOrgStr += divOrgs[j][i];
                           // divOrgStr += (i == divOrgs[j].length-1) ? ") " : ",";
                       }
                       Iterator it = set.iterator();
                       int i = 0;
                       while (it.hasNext()){
                           String div = ((Integer)it.next()).toString();
                           divOrgStr += div;
                           divOrgStr += (i == set.size()-1) ? ") " : ",";
                           i++;
                       }
                       //System.out.println(divOrgStr);
                       queryStr.append(divOrgStr);
                   }
               }
           }
           if(divGeos != null && divGeos.length > 0) {
               queryStr.append(" AND vc.vehicle.corpCountry.pk.country in (");
               for(int i = 0; i < divGeos.length; i++) {
                   queryStr.append(divGeos[i]);
                   queryStr.append((i == divGeos.length-1) ? ")" : ",");
               }
           }

           StringBuffer ids = new StringBuffer("0");
           Query query = session.createQuery(queryStr.toString());
           Iterator it = query.list().iterator();
           while(it.hasNext()) {
               Object[] s = (Object[])it.next();
               if(result.containsKey(s[1])) {
                   ((VehiclePursuitReportDataType)result.get(s[1])).sumCount();
               }else {
                   result.put(s[1],new VehiclePursuitReportDataType(s, messages, order,50));
               }
               ids.append(",").append(s[0]);
           }

//         Puntos 1 y 2
           String items = "870,871,872,873,874,875,876,877,878,879,880,881,882,883,884,885,886,887,888,889,890,891,892,893,894,895,896,897,898,899,900,901";
           Iterator itCount = getColumnVehiclePursuitReportByItem(items,ids.toString(),userSessionData);
           while(itCount.hasNext()) {
        	   Object[] counts = (Object[])itCount.next();
               if (result.containsKey(counts[0])){
            	   ((VehiclePursuitReportDataType)result.get(counts[0])).setCount(((Integer)counts[2]).intValue(),((Integer)counts[1]).intValue()-870);
               }
           }

           // Punto 3
           items = "902,903,904,905,906";
           itCount = getColumnThreeVehiclePursuitReportByItem(items,ids.toString(),userSessionData);
           while(itCount.hasNext()) {
               Object[] counts = (Object[])itCount.next();
               if (result.containsKey(counts[0])){
            	   ((VehiclePursuitReportDataType)result.get(counts[0])).setCount(((Integer)counts[2]).intValue(),((Integer)counts[1]).intValue()-870);
               }
           }


           // Observaciones:
           itCount = getObservationsColumnVehiclePursuitReport(ids.toString(),userSessionData,fromYear,fromMonth,toYear,toMonth);
           while(itCount.hasNext()) {
               Object[] counts = (Object[])itCount.next();
               if (result.containsKey(counts[0])){
            	   ((VehiclePursuitReportDataType)result.get(counts[0])).setCount(((Integer)counts[1]).intValue(),37);
               }
           }


       }catch(Exception e) {
       	Object[] parameters = {fromMonth,fromYear,toMonth,toYear,divOrgs,divGeos,messages,order};
           handler.getException(e,"getVehiclePursuitRouteCount",userSessionData,parameters);
       }
       return result;
   }

   private Iterator getColumnVehiclePursuitReportByItem(String items,String ids, UserSessionData userSessionData) throws Exception {
   	try {
	        Session session = HibernateUtil.currentSession();

	        StringBuffer queryStr = new StringBuffer("SELECT v.id as id, item, count(*) as count ");
	        queryStr.append("FROM VehiclePursuitRoute vc ");
	        queryStr.append("JOIN vehicles v ON vc.vehicle = v.id ");
	        queryStr.append("JOIN vehiclepursuitrouteitemsvalues vpr ON vc.pursuitRouteId = vpr.vehiclepursuitroute ");
	        queryStr.append(" WHERE v.corporation = ").append(String.valueOf(userSessionData.getFilter().getCorporationId()));
	        queryStr.append(" AND vpr.itemvalue = '2' ");
	        queryStr.append(" AND vpr.item in (").append(items).append(") ");
	        queryStr.append(" AND vpr.vehiclepursuitroute in (").append(ids).append(") ");
	        queryStr.append("GROUP BY v.id,item ");

	        Query query = session.createSQLQuery(queryStr.toString()).
	        	addScalar("id", Hibernate.INTEGER).
	        	addScalar("item", Hibernate.INTEGER).
	            addScalar("count", Hibernate.INTEGER);
	        return query.list().iterator();
		}catch (Exception e) {
			Object[] parameters = {items,ids};
			handler.getException(e, "getColumnVehiclePursuitReport", userSessionData, parameters);
	    }
		return null;
   }

   private Iterator getColumnThreeVehiclePursuitReportByItem(String items,String ids, UserSessionData userSessionData) throws Exception {
   	try {
	        Session session = HibernateUtil.currentSession();

	        StringBuffer queryStr = new StringBuffer("SELECT v.id as id, item, count(*) as count ");
	        queryStr.append(" FROM VehiclePursuitRoute vc ");
	        queryStr.append(" JOIN vehicles v ON vc.vehicle = v.id ");
	        queryStr.append(" JOIN vehiclepursuitrouteitemsvalues vpr ON vc.pursuitRouteId = vpr.vehiclepursuitroute ");
	        queryStr.append(" WHERE v.corporation = ").append(String.valueOf(userSessionData.getFilter().getCorporationId()));
	        queryStr.append(" AND vpr.itemvalue = '1'");
	        queryStr.append(" AND vpr.item in (").append(items).append(") ");
	        queryStr.append(" AND vpr.vehiclepursuitroute in (").append(ids).append(") ");
	        queryStr.append("GROUP BY v.id,item ");

	        Query query = session.createSQLQuery(queryStr.toString()).
            	addScalar("id", Hibernate.INTEGER).
            	addScalar("item", Hibernate.INTEGER).
	            addScalar("count", Hibernate.INTEGER);
	        return query.list().iterator();
		}catch (Exception e) {
			Object[] parameters = {items,ids};
			handler.getException(e, "getColumnThreeVehiclePursuitReport", userSessionData, parameters);
	    }
		return null;
   }

   /**
    * @ejb.interface-method
    * @ejb.permission role-name = "administrator,query"
    */
   public Hashtable getVehiclePursuitRouteListByItem(Integer fromMonth, Integer fromYear,
           Integer toMonth, Integer toYear, Integer[][] divOrgs, Integer[] divGeos,
           Text messages, int order, UserSessionData userSessionData) throws Exception {

  	 Hashtable result = new Hashtable();
       try {
           Session session = HibernateUtil.currentSession();

           StringBuffer queryStr = new StringBuffer("SELECT vc.pursuitRouteId,vc.vehicle.id, ");

           int corporationId = userSessionData.getFilter().getCorporationId().intValue();
           // Colonvade muestra el Transportista, que esta en el dviorg3
           if (corporationId == CorporationData.COLONVADE || corporationId == CorporationData.LOSPIQUES){
               queryStr.append(" vc.vehicle.divOrg3.resourceKey, ");
           } else{
               queryStr.append(" vc.vehicle.divOrg1.resourceKey, ");
           }

           queryStr.append(" vc.vehicle.matricula, vc.vehicle.type.resourceBundleId, vc.date ");
           queryStr.append(" FROM VehiclePursuitRoute vc ");
           queryStr.append(" WHERE (YEAR(vc.date) = ").append(String.valueOf(fromYear)).append(" AND MONTH(vc.date) >= ").append(fromMonth).append(" OR YEAR(vc.date) > ").append(String.valueOf(fromYear)+") ");
           queryStr.append(" AND (YEAR(vc.date) = ").append(String.valueOf(toYear)).append(" AND MONTH(vc.date) <= ").append(toMonth).append(" OR YEAR(vc.date) < ").append(String.valueOf(toYear)+") ");
           queryStr.append(" AND vc.vehicle.corpCountry.pk.corporation.id = ").append(String.valueOf(userSessionData.getFilter().getCorporationId())).append(" ");

           HashSet set = new HashSet();
           String divOrgStr = "";
           if(divOrgs != null && divOrgs.length > 0) {
               for(int j = 0; j < divOrgs.length; j++) {
                   if(divOrgs[j] != null && divOrgs[j].length > 0) {
                       divOrgStr = "";
                       divOrgStr = " and vc.vehicle.divOrg"+String.valueOf(j+1)+" in (";
                       set = new HashSet();
                       for(int i = 0; i < divOrgs[j].length; i++) {
                           set.add(divOrgs[j][i]); // Para evitar repetidos

                           // divOrgStr += divOrgs[j][i];
                           // divOrgStr += (i == divOrgs[j].length-1) ? ") " : ",";
                       }
                       Iterator it = set.iterator();
                       int i = 0;
                       while (it.hasNext()){
                           String div = ((Integer)it.next()).toString();
                           divOrgStr += div;
                           divOrgStr += (i == set.size()-1) ? ") " : ",";
                           i++;
                       }
                       //System.out.println(divOrgStr);
                       queryStr.append(divOrgStr);
                   }
               }
           }
           if(divGeos != null && divGeos.length > 0) {
               queryStr.append(" AND vc.vehicle.corpCountry.pk.country in (");
               for(int i = 0; i < divGeos.length; i++) {
                   queryStr.append(divGeos[i]);
                   queryStr.append((i == divGeos.length-1) ? ")" : ",");
               }
           }

           StringBuffer ids = new StringBuffer("0");
           Query query = session.createQuery(queryStr.toString());
           Iterator it = query.list().iterator();
           while(it.hasNext()) {
               Object[] s = (Object[])it.next();
               VehiclePursuitReportDataType v = new VehiclePursuitReportDataType(s, messages, order, 50);
               v.setDate((Date)s[5]);
               result.put(s[0],v);
               ids.append(",").append(s[0]);
           }

           String items = "870,871,872,873,874,875,876,877,878,879,880,881,882,883,884,885,886,887,888,889,890,891,892,893,894,895,896,897,898,899,900,901";
           Iterator itCount = getColumnVehiclePursuitReportListByItem(items,ids.toString(),userSessionData);
           while(itCount.hasNext()) {
        	   Object[] counts = (Object[])itCount.next();
               if (result.containsKey(counts[0])){
            	   ((VehiclePursuitReportDataType)result.get(counts[0])).setCount(((Integer)counts[2]).intValue(),((Integer)counts[1]).intValue()-870);
               }
           }

           // Punto 3
           items = "902,903,904,905,906";
           itCount = getColumnThreeVehiclePursuitReportListByItem(items,ids.toString(),userSessionData);
           while(itCount.hasNext()) {
               Object[] counts = (Object[])itCount.next();
               if (result.containsKey(counts[0])){
            	   ((VehiclePursuitReportDataType)result.get(counts[0])).setCount(((Integer)counts[2]).intValue(),((Integer)counts[1]).intValue()-870);
               }
           }


           // Observaciones:
           itCount = getObservationsColumnVehiclePursuitReportList(ids.toString(),userSessionData,fromYear,fromMonth,toYear,toMonth);
           while(itCount.hasNext()) {
               Object[] counts = (Object[])itCount.next();
               if (result.containsKey(counts[0])){
            	   ((VehiclePursuitReportDataType)result.get(counts[0])).setCount(((Integer)counts[1]).intValue(),37);
               }
           }


       }catch(Exception e) {
       	Object[] parameters = {fromMonth,fromYear,toMonth,toYear,divOrgs,divGeos,messages,order};
           handler.getException(e,"getVehiclePursuitRouteCount",userSessionData,parameters);
       }
       return result;
   }

   private Iterator getColumnVehiclePursuitReportListByItem(String items,String ids, UserSessionData userSessionData) throws Exception {
	   	try {
		        Session session = HibernateUtil.currentSession();

		        StringBuffer queryStr = new StringBuffer("SELECT vc.pursuitrouteid as id, item, count(*) as count ");
		        queryStr.append("FROM VehiclePursuitRoute vc ");
		        queryStr.append("JOIN vehicles v ON vc.vehicle = v.id ");
		        queryStr.append("JOIN vehiclepursuitrouteitemsvalues vpr ON vc.pursuitRouteId = vpr.vehiclepursuitroute ");
		        queryStr.append(" WHERE v.corporation = ").append(String.valueOf(userSessionData.getFilter().getCorporationId()));
		        queryStr.append(" AND vpr.itemvalue = '2' ");
		        queryStr.append(" AND vpr.item in (").append(items).append(") ");
		        queryStr.append(" AND vpr.vehiclepursuitroute in (").append(ids).append(") ");
		        queryStr.append("GROUP BY vc.pursuitrouteid,item ");

		        Query query = session.createSQLQuery(queryStr.toString()).
		        	addScalar("id", Hibernate.INTEGER).
		        	addScalar("item", Hibernate.INTEGER).
		            addScalar("count", Hibernate.INTEGER);
		        return query.list().iterator();
			}catch (Exception e) {
				Object[] parameters = {items,ids};
				handler.getException(e, "getColumnVehiclePursuitReport", userSessionData, parameters);
		    }
			return null;
	   }

	   private Iterator getColumnThreeVehiclePursuitReportListByItem(String items,String ids, UserSessionData userSessionData) throws Exception {
	   	try {
		        Session session = HibernateUtil.currentSession();

		        StringBuffer queryStr = new StringBuffer("SELECT vc.pursuitrouteid as id, item, count(*) as count ");
		        queryStr.append(" FROM VehiclePursuitRoute vc ");
		        queryStr.append(" JOIN vehicles v ON vc.vehicle = v.id ");
		        queryStr.append(" JOIN vehiclepursuitrouteitemsvalues vpr ON vc.pursuitRouteId = vpr.vehiclepursuitroute ");
		        queryStr.append(" WHERE v.corporation = ").append(String.valueOf(userSessionData.getFilter().getCorporationId()));
		        queryStr.append(" AND vpr.itemvalue = '1'");
		        queryStr.append(" AND vpr.item in (").append(items).append(") ");
		        queryStr.append(" AND vpr.vehiclepursuitroute in (").append(ids).append(") ");
		        queryStr.append("GROUP BY vc.pursuitrouteid,item ");

		        Query query = session.createSQLQuery(queryStr.toString()).
	            	addScalar("id", Hibernate.INTEGER).
	            	addScalar("item", Hibernate.INTEGER).
		            addScalar("count", Hibernate.INTEGER);
		        return query.list().iterator();
			}catch (Exception e) {
				Object[] parameters = {items,ids};
				handler.getException(e, "getColumnThreeVehiclePursuitReport", userSessionData, parameters);
		    }
			return null;
	   }

   /**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "vehicleedit,vehicleupdate,administration"
     */
	public Collection getVehicleMaintenances(Integer vehicle, UserSessionData userSessionData) throws Exception {
		Collection maintenances = new LinkedList();
		try {
	        Session session = HibernateUtil.currentSession();

	        String queryStr = "SELECT vm FROM VehicleMaintenance vm WHERE vm.vehicle.id = "+String.valueOf(vehicle);
	        Query query = session.createQuery(queryStr);

	        Iterator it = query.list().iterator();
	        while(it.hasNext()) {
	        	maintenances.add(((VehicleMaintenance)it.next()).getData());
	        }
		} catch (Exception e) {
			Object[] p = {vehicle};
			handler.getException(e, "getVehicleMaintenances", userSessionData, p);
		}
		return maintenances;
	}

	/**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "vehicleedit,vehicleupdate,administration"
     */
	public VehicleMaintenanceData getVehicleMaintenance(Integer id,UserSessionData userSessionData) throws Exception {
		VehicleMaintenanceData maintenance = null;
		try {
	        Session session = HibernateUtil.currentSession();
	        VehicleMaintenance vehicleMaintenance = (VehicleMaintenance)session.load(VehicleMaintenance.class, id);
	        maintenance = vehicleMaintenance.getData();
		} catch (Exception e) {
			Object[] p = {id};
			handler.getException(e, "getVehicleMaintenance", userSessionData, p);
		}
		return maintenance;
	}

	public Collection getVehicleMaintenances(String plate, String vehicleType, Integer[][] divOrgs, Integer[] divGeos, int status, Integer first, Integer count, UserSessionData userSessionData) throws Exception {
		Collection result = new LinkedList();
		return result;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,vehicleedit,query"
	 */
	public Collection getVehiclesWithDriver(String type,int status, Integer[][] divOrgs, Integer[][] divGeos,UserSessionData userSessionData) throws Exception {
		Collection list = new LinkedList();
		try {
	        Session session = HibernateUtil.currentSession();
	        StringBuffer query1 = new StringBuffer();
			query1.append("SELECT v.id,v.matricula,d.firstname,d.lastname " +
					"FROM vehicles v " +
					"JOIN drivers d ON v.driver = d.id");
			query1.append(UtilityQuery.corporationRestrictionsSQL(divOrgs,divGeos[0],"v",userSessionData));
	        if(!Util.empty(type)) {
	            query1.append(" AND v.type = '"+type+"' ");
	        }
            switch (status) {
            	case VehicleData.ACTIVE:
            		query1.append(" AND v.exclusionDate IS NULL");
            		break;
            	case VehicleData.EXCLUDED:
            		query1.append(" AND v.exclusionDate IS NOT NULL");
            		break;
            }
            query1.append(" ORDER BY firstname,lastname");
            Query q = session.createSQLQuery(query1.toString())
            .addScalar("id",Hibernate.INTEGER)
            .addScalar("matricula",Hibernate.STRING)
            .addScalar("firstname",Hibernate.STRING)
            .addScalar("lastname",Hibernate.STRING);
            list = q.list();
		} catch (Exception e) {
			Object[] p = {type,status,divOrgs,divGeos};
			handler.getException(e, "getVehicleMaintenance", userSessionData, p);
		}
		return list;
	}

	/**
	 * Devuelve un listado de Vehiculos con sus drivers y contratos
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,vehicleedit,query"
	 */
	public ListResult getVehiclesWithDriverAndContracts(
			String type,int status, Integer[][] divOrgs, Integer[][] divGeos,
			String vehicleType, Date from, Date to, int first, int count,
			UserSessionData userSessionData) throws Exception {

		Collection result = new LinkedList();
		Collection resultVehicles = new LinkedList();
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			String[] s = userSessionData.getFilter().getFields("vehicle");
            String queryStr = " FROM Vehicle vehicle " +
            		UtilityQuery.corporationDivisionsRestictions("vehicle",userSessionData);
            /*
            if(divOrgs != null && divOrgs.length > 0) {
                for(int j = 0; j < divOrgs.length; j++) {
                    if(divOrgs[j] != null && divOrgs[j].length > 0) {
                        queryStr += " AND vehicle.divOrg"+String.valueOf(j+1)+" in (";
                        for(int i = 0; i < divOrgs[j].length; i++) {
                            queryStr += divOrgs[j][i];
                            queryStr += (i == divOrgs[j].length-1) ? ") " : ",";
                        }
                    }
                }
            }
            if(divGeos != null) {
                if(divGeos[0] != null && divGeos.length > 0) {
                    queryStr += " AND vehicle.country in (";
                    for(int i = 0; i < divGeos.length; i++) {
                        queryStr += divGeos[i];
                        queryStr += (i == divGeos.length-1) ? ")" : ",";
                    }
                }
            }
            */
            if(status == VehicleData.ACTIVE) {
                queryStr +=" AND vehicle.exclusionDate is null ";
            }
            // Driver State: Excluido
            if(status == VehicleData.EXCLUDED) {
                queryStr +=" AND vehicle.exclusionDate is not null ";
            }

            // vehicletype
            if (vehicleType != null && vehicleType.trim() != ""){
            	queryStr +=" AND vehicle.type = '" + vehicleType + "' ";
            }

            // tipo de contrato
            if (type.equalsIgnoreCase(VehicleContractDataType.WITHOUT_CONTRACT)){
            	// Sin contratos:
            	queryStr +=
					"and vehicle.id NOT IN (" +
					" SELECT contract.pk.vehicle.id FROM VehicleContract as contract " +
					" WHERE contract.pk.vehicle.id = vehicle.id " +
					" AND contract.pk.date >= '" + dateFormat.format(from)+ "' " +
					" AND contract.pk.date <= '" + dateFormat.format(to) + "' "+
					" )  ";

            }else{
            	// Con contratos de tipo "type"
            	queryStr += " AND  " +
					" ( SELECT count(*) FROM VehicleContract as contract " +
					" WHERE contract.pk.vehicle.id = vehicle.id " +
					" AND contract.type.resourceBundleId = '" + type + "' " +
					" AND contract.pk.date >= '" + dateFormat.format(from)+ "' " +
					" AND contract.pk.date <= '" + dateFormat.format(to) + "' "+
					" ) >= 1 ";
            }


            Query q = HibernateUtil.currentSession().
            		createQuery("SELECT COUNT(*) AS count " + queryStr + "  ");
	        Long total = (Long)q.uniqueResult();


	        // TODO Gus
            Query query = HibernateUtil.currentSession().createQuery("SELECT vehicle " + queryStr + " order by vehicle.matricula ");
            if(count != -1) {
	            query.setFirstResult(first);
	            query.setMaxResults(count);
	        }
            List list = query.list();

            if (list != null){
            	Iterator it = list.iterator();
            	String idsVehicles = "";
            	VehicleContractReportDataType vContract = null;
            	Hashtable<Integer,VehicleContractReportDataType> vehiclesHash =
            			new Hashtable<Integer,VehicleContractReportDataType>();

            	// Itero sobre los vehiculos para obtener sus ids para luego buscale sus contratos:
            	while (it.hasNext()){
            		Vehicle vehicle = (Vehicle)it.next();
            		if (vehicle != null){
            			// Obtengo el data del vehiculo
            			VehicleData vehicleData = vehicle.getVehicleData();
            			if (vehicleData != null){
            				vContract = new VehicleContractReportDataType(vehicleData,null,null);

            				if (idsVehicles.equalsIgnoreCase("")){
            					idsVehicles = vehicle.getId().toString();
            				}else{
            					idsVehicles += ", " + vehicle.getId().toString();
            				}
            				vehiclesHash.put(vehicleData.getId(),vContract);
                			resultVehicles.add(vContract);
            			}
            		}
            	}

            	if (!type.equalsIgnoreCase(VehicleContractDataType.WITHOUT_CONTRACT)){
            		Query query2  = HibernateUtil.currentSession().createQuery(
            			" SELECT contract FROM VehicleContract as contract " +
    					" WHERE contract.pk.vehicle.id IN ( " + idsVehicles + ") " +
    					" AND contract.type.resourceBundleId = '" + type + "' " +
    					" AND contract.pk.date >= '" + dateFormat.format(from)+ "' " +
    					" AND contract.pk.date <= '" + dateFormat.format(to) + "' ");
            		 List list2 = query2.list();
            		 if (list2 != null && list2.size() > 0){
            			Iterator it2 = list2.iterator();
             			while (it2.hasNext()){
             				VehicleContract contract = (VehicleContract) it2.next();
             				if (contract != null){
             					Integer vehicleId = contract.getPk().getVehicle().getId();
             					if (vehiclesHash.containsKey(vehicleId)){
             						vContract = null;
             						vContract = vehiclesHash.get(vehicleId);
             						VehicleContractReportDataType vContractNew = new VehicleContractReportDataType(vContract);
             						vContractNew.setContractDate(contract.getPk().getDate());
             						vContractNew.setContractType(contract.getType().getResourceBundleId());
             						result.add(vContractNew); // agrego vehiculo con 1 contrato asociado.
        							resultVehicles.remove(vContract); // saco el driver que tiene vehiculo asociado
             					}
             				}
             			}
            		 }
            	}
            	result.addAll(resultVehicles); // agrego al resultado de los vehiculos los vehiculos que no tienen contrato (si aplica)

            } // if list!= null

            // MemoryLog.getInstance().log(MemoryLog.END,"getVehiclesWithDriverAndContracts", getClass());
            ListResult listResult = new ListResult(result,total.intValue());
	        return listResult;

		} catch (Exception e) {
			Object[] p = {type,status, divOrgs, divGeos, vehicleType };
			handler.getException(e, "getVehiclesWithDriverAndContracts", userSessionData, p);
		}
		return null;
	}

	/**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "vehicleedit,vehicleupdate,administration"
     */
	public ComplaintData getVehicleComplaint(Integer id,UserSessionData userSessionData) throws Exception {
		ComplaintData complaintData = null;
		try {
	        Session session = HibernateUtil.currentSession();
	        Complaint complaint = (Complaint)session.load(Complaint.class, id);
	        complaintData = complaint.getData();
		} catch (Exception e) {
			Object[] p = {id};
			handler.getException(e, "getVehicleComplaint", userSessionData, p);
		}
		return complaintData;
	}

	/**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "vehicleedit,vehicleupdate,administration"
     */
	public Collection getVehicleComplaints(Integer vehicle, UserSessionData userSessionData) throws Exception {
		Collection result = new LinkedList();
		try {
	        Session session = HibernateUtil.currentSession();
	        Query q = session.createSQLQuery("SELECT complaintid,date,calltype,location " +
	        		"FROM complaints " +
	        		"WHERE vehicle = "+String.valueOf(vehicle))
	        		.addScalar("complaintid", Hibernate.INTEGER)
	        		.addScalar("date", Hibernate.DATE)
	        		.addScalar("calltype", Hibernate.STRING)
	        		.addScalar("location", Hibernate.STRING);
	        return q.list();
		} catch (Exception e) {
			Object[] p = {vehicle};
			handler.getException(e, "getVehicleComplaint", userSessionData, p);
		}
		return result;
	}

	/**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "vehicleview,query,administration"
     */
	public ListResult getComplaintsReport(String vehicle, Date from, Date to,Integer[][] divOrgs,Integer[][] divGeos,int first,int count, int status,UserSessionData userSessionData) throws Exception {
		Collection result = new LinkedList();
		try {

	        Session session = HibernateUtil.currentSession();
	        String where = "FROM complaints c JOIN vehicles v ON c.vehicle = v.id JOIN vehiclesoptionalinfo voi ON voi.id = v.optionalinfo " + UtilityQuery.corporationRestrictionsSQL(divOrgs, divGeos[0], "v", userSessionData);
            if(!Util.empty(vehicle)) {
            	where += " AND lower(v.matricula) like '%"+vehicle.toLowerCase()+"%'";
            }
            switch(status) {
                case VehicleData.ACTIVE:
                	where += " AND exclusionDate is null ";
                    break;
                case VehicleData.EXCLUDED:
                	where += " AND exclusionDate is not null ";
                    break;
            }
            if(from != null) {
            	where += " AND c.date >= "+Util.formatSQLHour(from,0);
            }
            if(to != null) {
            	where += " AND c.date < "+Util.formatSQLHour(to,1);
            }

            String query = "SELECT COUNT(*) AS count " +where;
	        SQLQuery q = session.createSQLQuery(query);
	        q.addScalar("count", Hibernate.INTEGER);
	        Integer total = (Integer)q.uniqueResult();

            query = "SELECT c.complaintId,c.date,c.time,c.vehicle,v.matricula,c.location,c.description,c.callType,c.specification,c.reportDate,c.ticket,c.phoneId," +
    		"c.agent,c.reporterName,c.reporterPhone,c.reporterMobile,c.reporterEmail,c.options,c.actions,voi.prefix " + where + " ORDER BY date";
	        q = session.createSQLQuery(query);
	        q.addScalar("complaintId", Hibernate.INTEGER);
	        q.addScalar("date", Hibernate.DATE);
	        q.addScalar("time", Hibernate.DATE);
	        q.addScalar("vehicle", Hibernate.INTEGER);
	        q.addScalar("matricula", Hibernate.STRING);
	        q.addScalar("location", Hibernate.STRING);
	        q.addScalar("description", Hibernate.STRING);
	        q.addScalar("callType", Hibernate.STRING);
	        q.addScalar("specification", Hibernate.STRING);
	        q.addScalar("reportDate", Hibernate.DATE);
	        q.addScalar("ticket", Hibernate.STRING);
	        q.addScalar("phoneId", Hibernate.STRING);
	        q.addScalar("agent", Hibernate.STRING);
	        q.addScalar("reporterName", Hibernate.STRING);
	        q.addScalar("reporterPhone", Hibernate.STRING);
	        q.addScalar("reporterMobile", Hibernate.STRING);
	        q.addScalar("reporterEmail", Hibernate.STRING);
	        q.addScalar("options", Hibernate.STRING);
	        q.addScalar("actions", Hibernate.STRING);
	        q.addScalar("prefix", Hibernate.STRING);
	        if(count != -1) {
	            q.setFirstResult(first);
	            q.setMaxResults(count);
	        }

	        ListResult list = new ListResult(q.list(),total);
	        return list;

		} catch (Exception e) {
			Object[] p = {vehicle};
			handler.getException(e, "getComplaintsReport", userSessionData, p);
		}
		return new ListResult(new LinkedList(),0);
	}

	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,driverupdate,vehicleupdate"
	 * @param data
	 * @throws Exception
	 */
	public boolean hasChangedDivisions(Integer vehicleId, Integer[] divOrgs, UserSessionData userSessionData) throws Exception {
		boolean result = false;
		try {
			String sql = "SELECT COUNT(*) AS count FROM vehicles WHERE id = "+vehicleId;
			for(int i = 0; i < 3; i++) {
				if(divOrgs[i] != 0) {
					sql += " AND divorg" + String.valueOf(i+1) + " = " + divOrgs[i];
				}
			}
	        Session session = HibernateUtil.currentSession();
	        Query q = session.createSQLQuery(sql).addScalar("count",Hibernate.INTEGER);
	        Integer count = (Integer)q.uniqueResult();
	        result = count == 0;
		} catch (Exception e) {
			Object[] p = {vehicleId,divOrgs};
			handler.getException(e, "hasChangedDivisions", userSessionData, p);
		}
		return result;
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
            Session session = HibernateUtil.currentSession();
            String queryStr = "SELECT vehicle,matricula,driver,date FROM vehiclelog WHERE vehicle IN (SELECT DISTINCT vehicle FROM vehiclelog WHERE driver = "+driverId+") ORDER BY matricula,vehicle,date";

            SQLQuery query = session.createSQLQuery(queryStr);
            query.addScalar("vehicle",Hibernate.INTEGER);
            query.addScalar("matricula",Hibernate.STRING);
            query.addScalar("driver",Hibernate.INTEGER);
            query.addScalar("date",Hibernate.DATE);
            result = query.list();
        } catch (Exception e) {
        	Object[] parameters = {driverId};
            handler.getException(e,"findDriversVehicles",userSessionData,parameters);
        }
        return result;
    }

    /**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "vehicleedit,vehicleupdate,administration"
     */
	public void dpaExportCheckListToXLS(Text messages, UserSessionData userSessionData,DPACheckListXLSFilter filter,OutputStream output) throws Exception {

		try {
	        Session session = HibernateUtil.currentSession();
	        Query query= session.createSQLQuery("select vc.vehiclecheckid,date_trunc('day',vc.date) as data, v.matricula,case when vc.name = '75' then 'T1' else 'T2' end, vc.obs, vc.inspectedby,v.divorg1,v.type,vc.serie "+
									" from vehiclecheck vc "+
									" join vehicles v on v.id = vc.vehicle "+
									" where name in ('75','78') "+(filter.getStart()!=null?"and vc.date >= (:startDate) ":"")+(filter.getEnd()!=null?" and vc.date <= (:endDate)":"")+" and lower(v.matricula) like (:matricula) "+
									" and vc.result = 1 "+(filter.getInspectedByMy()!=null && filter.getInspectedByMy()?"and lower(vc.inspectedby) like (:inspected) ":"")+
									" order by vehiclecheckid, data, matricula");
	        if(filter.getStart()!=null){
	        	query.setParameter("startDate", filter.getStart());
	        }
	        if(filter.getEnd()!=null){
	        	query.setParameter("endDate", filter.getEnd());
	        }
	        if(filter.getInspectedByMy()!=null && filter.getInspectedByMy()){
	        	query.setParameter("inspected", "%"+userSessionData.getFullName().toLowerCase()+"%");
	        }

	        query.setParameter("matricula", filter.getText()!=null?"%"+filter.getText().toLowerCase()+"%":"");

	        List result = query.list();
	        Iterator iter= result.iterator();
	        ArrayList<ArrayList<String>> aprovedPage=new ArrayList<ArrayList<String>>();
	        ArrayList<String> header=new ArrayList<String>();
	        header.add("TRECHO");
	        header.add("TRANSPORTADORA");
	        header.add("REGIAO");
	        header.add("CIUDADE");
	        header.add("DATA");
	        header.add("VEHICULO PLACA");
	        header.add(messages.getText("dpat1.point02.item1"));
	        header.add(messages.getText("dpat1.point02.item2"));
	        header.add("VEHICULO TIPO");
	        header.add("TIPO");
	        header.add("OBSERVACIONES");
	        header.add("INGRESADO POR");
	        aprovedPage.add(header);
	        while(iter.hasNext()){
	        	Object[] row=(Object[]) iter.next();
	        	Integer vcId=(Integer)row[0];
	        	Date date=(Date)row[1];
	        	String matricula=(String)row[2];
	        	String name=(String)row[3];
	        	String obs=(String)row[4];
	        	String inspectedBy=(String)row[5];
	        	Integer divOrg1=(Integer)row[6];
	        	String typeV=(String)row[7];
	        	String city=(String)row[8];

	        	ArrayList<String> rowStr=new ArrayList<String>();
	        	Query query2=session.createQuery("select d from DivOrgItemLevel1 as d where d.id="+divOrg1);

	    		DivOrgItemLevel1 divOrg=(DivOrgItemLevel1) query2.uniqueResult();

	    		if(divOrg!=null && divOrg.getDivOrgItemLevel2()!=null && divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3()!=null){
		    		rowStr.add(divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey());

		    		rowStr.add(divOrg.getDivOrgItemLevel2().getResourceKey());

		    		rowStr.add(divOrg.getResourceKey());
	    		}else{
	    			rowStr.add("Sim Dados.");
	    			rowStr.add("Sim Dados.");
	    			rowStr.add("Sim Dados.");
	    		}
	    		rowStr.add(city);
	        	rowStr.add(Util.formatDate(date));
	        	rowStr.add(matricula);
	        	Query query3=session.createSQLQuery("select v.itemValue from vehiclecheckitemsvalues as v inner join items as i on( i.id=v.item and i.text in('dpat1.point02.item1','dpat1.point02.item2')) where v.vehiclecheck="+vcId+" order by i.text");

	    		Iterator iter4=query3.list().iterator();

	    		int count=0;
	    		while(iter4.hasNext()){
	    			String v=(String) iter4.next();
	    			rowStr.add(v.toString());
	    			count++;
	    		}
	    		while(count<2){
	    			rowStr.add("");
	    			count++;
	    		}

	        	rowStr.add(messages.getText(typeV));
	        	rowStr.add(name);
	        	rowStr.add(obs);
	        	rowStr.add(inspectedBy);
	        	aprovedPage.add(rowStr);
	        }

	        query= session.createSQLQuery("select vc.vehiclecheckid,date_trunc('day',vc.date) as data, v.matricula,case when vc.name = '75' then 'T1' else 'T2' end, vc.obs, vc.inspectedby, case when vc.result = 1 then 'Aprovado' else 'Reprovado' end, gm.text, vpc.comment, v.divorg1,v.type, vc.serie "+
	        		" from vehiclecheck vc "+
	        		" join vehicles v on v.id = vc.vehicle "+
	        		" join vehiclecheckitemsvalues viv on viv.vehiclecheck = vc.vehiclecheckid "+
	        		" join items i on i.id = viv.item "+
	        		" join generalmessages gm on gm.labelkey = i.text "+
	        		" left join vehiclecheckpointscomment vpc on vpc.vehiclecheck = vc.vehiclecheckid and vpc.point = i.point "+
	        		" where name in ('75','78') "+(filter.getStart()!=null?"and vc.date >= (:startDate) ":"")+(filter.getEnd()!=null?" and vc.date <= (:endDate)":"")+"  and lower(v.matricula) like (:matricula) "+
	        		" and vc.result = 2 "+
	        		" and viv.itemvalue = '2' "+(filter.getInspectedByMy()!=null && filter.getInspectedByMy()?"and lower(vc.inspectedby) like (:inspected) ":"")+
	        		" order by vehiclecheckid, data, matricula ");

	        if(filter.getStart()!=null){
	        	query.setParameter("startDate", filter.getStart());
	        }
	        if(filter.getEnd()!=null){
	        	query.setParameter("endDate", filter.getEnd());
	        }

	        if(filter.getInspectedByMy()!=null && filter.getInspectedByMy()){
	        	query.setParameter("inspected", "%"+userSessionData.getFullName().toLowerCase()+"%");
	        }


			query.setParameter("matricula", filter.getText()!=null?"%"+filter.getText().toLowerCase()+"%":"");

			result = query.list();
			iter= result.iterator();
			ArrayList<ArrayList<String>> reprovedPage=new ArrayList<ArrayList<String>>();
			ArrayList<String> header2=new ArrayList<String>();
			header2.add("TRECHO");
			header2.add("TRANSPORTADORA");
			header2.add("REGIAO");
			header2.add("CIUDADE");
			header2.add("DATA");
			header2.add("VEICULO PLACA");
			header2.add(messages.getText("dpat1.point02.item1"));
			header2.add(messages.getText("dpat1.point02.item2"));
			header2.add("VEICULO TIPO");
			header2.add("TIPO");
			header2.add("OBSERVACIONES");
			header2.add("INGRESADO POR");
			header2.add("PUNTOS REPROVADO");
			header2.add("COMENTARIO");
			reprovedPage.add(header2);
			Integer vcId=0;
			Integer maxReporovados=0;
			Integer countReprovados=0;
			ArrayList<String> rowStrBack=new ArrayList<String>();
			while(iter.hasNext()){
				Object[] row=(Object[]) iter.next();
				Integer id=(Integer)row[0];
				Date date=(Date)row[1];
				String matricula=(String)row[2];
				String name=(String)row[3];
				String obs=(String)row[4];
				String inspectedBy=(String)row[5];
				String text=(String)row[7];
				String comment=(String)row[8];
				Integer divOrg1=(Integer)row[9];
				String typeV=(String)row[10];
				String city=(String)row[11];

				boolean onlypoint=false;
				ArrayList<String> rowStr=new ArrayList<String>();
				if( vcId.equals(id)){
					//Mismo checklist
					rowStr=rowStrBack;
					onlypoint=true;
					countReprovados++;
					if(countReprovados>maxReporovados){
						maxReporovados=countReprovados;
						header2.add("PUNTOS REPROVADO");
						header2.add("COMENTARIO");
					}
				}
				if(!onlypoint){
					Query query2=session.createQuery("select d from DivOrgItemLevel1 as d where d.id="+divOrg1);

		    		DivOrgItemLevel1 divOrg=(DivOrgItemLevel1) query2.uniqueResult();

		    		if(divOrg!=null && divOrg.getDivOrgItemLevel2()!=null && divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3()!=null){
			    		rowStr.add(divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey());

			    		rowStr.add(divOrg.getDivOrgItemLevel2().getResourceKey());

			    		rowStr.add(divOrg.getResourceKey());
		    		}else{
		    			rowStr.add("Sim Dados.");
		    			rowStr.add("Sim Dados.");
		    			rowStr.add("Sim Dados.");
		    		}



		    		rowStr.add(city);
					rowStr.add(Util.formatDate(date));
					rowStr.add(matricula);
					Query query3=session.createSQLQuery("select v.itemValue from vehiclecheckitemsvalues as v inner join items as i on( i.id=v.item and i.text in('dpat1.point02.item1','dpat1.point02.item2')) where v.vehiclecheck="+vcId+" order by i.text");

		    		Iterator iter4=query3.list().iterator();
		    		int count=0;
		    		while(iter4.hasNext()){
		    			String v=(String) iter4.next();
		    			rowStr.add(v.toString());
		    			count++;
		    		}
		    		while(count<2){
		    			rowStr.add("");
		    			count++;
		    		}

					rowStr.add(messages.getText(typeV));
					rowStr.add(name);
					rowStr.add(obs);
					rowStr.add(inspectedBy);
				}

				rowStr.add(text);

				rowStr.add(comment);
				if( !onlypoint ){
					reprovedPage.add(rowStr);
				}

				if(!id.equals(vcId)){
					vcId=id;
					rowStrBack=rowStr;
					countReprovados=0;
				}
			}

			ExcelGenerator.generatorXLS(aprovedPage, "Aprovados",reprovedPage,"Reprovados", output);

		} catch (Exception e) {
			Object[] p = {filter};
			handler.getException(e, "dpaExportCheckListToXLS", userSessionData, p);
		}

	}

	  /**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "vehicleedit,vehicleupdate,administration"
     */
	public void exportVehicleCheckListResumeToXLS(UserSessionData userSessionData,DPACheckListXLSFilter filter,OutputStream output) throws Exception {

		try {
	        Session session = HibernateUtil.currentSession();

	        Query query= session.createSQLQuery("select v.id,v.divorg1, v.matricula,vc.date ,vc.result ,vc.serie" +
	        " from vehicles as v "+
	        " left outer join vehiclecheck as vc  on( vc.vehicle=v.id "+(filter.getStart()!=null?" and vc.date >= (:startDate) ":"")+(filter.getEnd()!=null?" and vc.date <= (:endDate)":"")+ ") where v.divorg1 in("+filter.getText()+") "+
	        " order by v.matricula , vc.date ");

	        if(filter.getStart()!=null){
	        	query.setParameter("startDate", filter.getStart());
	        }
	        if(filter.getEnd()!=null){
	        	query.setParameter("endDate", filter.getEnd());
	        }

	        List result = query.list();
	        Iterator iter= result.iterator();
	        ArrayList<ArrayList<String>> page=new ArrayList<ArrayList<String>>();
	        ArrayList<String> header=new ArrayList<String>();
	        header.add("TRECHO");
	        header.add("TRANSPORTADORA");
	        header.add("REGIAO");
	        header.add("CIUDADE");
	        header.add("VEICULO");
	        header.add("CHECK LIST");
	        header.add("DATA");
	        header.add("RESULTADO");
	        page.add(header);
	        while(iter.hasNext()){
	        	Object[] row=(Object[]) iter.next();
	        	Date date=row[3]==null?null:(Date)row[3];
	        	String matricula=(String)row[2];
	        	Integer vehicle=(Integer)row[0];
	        	Integer divOrg1=(Integer)row[1];
	        	Integer resultC=row[4]==null?null:(Integer)row[4];
	        	String city=(String)row[5];

	        	ArrayList<String> rowStr=new ArrayList<String>();
	        	Query query2=session.createQuery("select d from DivOrgItemLevel1 as d where d.id="+divOrg1);

	    		DivOrgItemLevel1 divOrg=(DivOrgItemLevel1) query2.uniqueResult();

	    		rowStr.add(divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey());

	    		rowStr.add(divOrg.getDivOrgItemLevel2().getResourceKey());

	    		rowStr.add(divOrg.getResourceKey());

	    		rowStr.add(city);


	        	rowStr.add(matricula);
	        	rowStr.add(date!=null?"S":"N");
	        	rowStr.add(Util.formatDate(date));
	        	rowStr.add(resultC==null?"":(resultC==1)?"Aprovado":"Reprovado");
	        	page.add(rowStr);
	        }

	        ExcelGenerator.generatorXLS(page, "CheckList", output);

		} catch (Exception e) {
			Object[] p = {filter};
			handler.getException(e, "dpaExportCheckListToXLS", userSessionData, p);
		}

	}

	/**
	 * @ejb.permission role-name ="administrator,driveredit,driverviewowndata"
	 * @ejb.interface-method
	 */
	public Collection<TelematicControlData> findTelematicControlsDPA(UserSessionData userSessionData) throws Exception {
		Collection<TelematicControlData> list = new LinkedList<TelematicControlData>();

		if (userSessionData == null) {
			throw new DeslogedException("findTelematicControls",
					"session is null");
		}
		try {
			String sql = "SELECT control FROM TelematicControl AS control WHERE( (vehicle is not null and vehicle.corpCountry.pk.corporation= 62) or (driver is not null and driver.corpCountry.pk.corporation=62) )";
			Query query = HibernateUtil.currentSession().createQuery(sql);
			Iterator it = query.list().iterator();
			while (it.hasNext()) {
				list.add(((TelematicControl) it.next())
						.getData(userSessionData));
			}
		} catch (Exception e) {
			Object[] parameters = {  };
			handler.getException(e, "findTelematicControls", userSessionData,
					parameters);
		}
		return list;
	}


	/**
	 * @ejb.permission role-name ="administrator,driveredit,driverviewowndata"
	 * @ejb.interface-method
	 */
	public Collection<TelematicControlData> findTelematicControls(
			Integer vehicle, UserSessionData userSessionData) throws Exception {
		Collection<TelematicControlData> list = new LinkedList<TelematicControlData>();
		if (vehicle == null) {
			throw new InvalidDataException("findTelematicControls", "id = null");
		}
		if (userSessionData == null) {
			throw new DeslogedException("findTelematicControls",
					"session is null");
		}
		try {
			String sql = "SELECT control FROM TelematicControl AS control WHERE vehicle.id = "
					+ String.valueOf(vehicle)+" and driver is null ORDER by date desc";
			Query query = HibernateUtil.currentSession().createQuery(sql);
			Iterator it = query.list().iterator();
			while (it.hasNext()) {
				list.add(((TelematicControl) it.next())
						.getData(userSessionData));
			}
		} catch (Exception e) {
			Object[] parameters = { vehicle };
			handler.getException(e, "findTelematicControls", userSessionData,
					parameters);
		}
		return list;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,vehicleview"
	 */
	public void exportTelematicControlDPA(Text messages, UserSessionData userSessionData,DPATelematicControlFilter filter,OutputStream output) throws Exception {
		try{

			Session session = HibernateUtil.currentSession();
			String select=" select t from TelematicControl as t ";
			String where=" where t.driver is null and t.vehicle.corpCountry.pk.corporation.id="+userSessionData.getFilter().getCorporationId()+" and ";
			if(filter.getStart()!=null){
				where+=" t.date >= (:start)";
			}else{
				where+=" 1=1 ";
			}
			where+=" and ";
			if(filter.getEnd()!=null){
				where+=" t.date <= (:end)";
			}else{
				where+=" 1=1 ";
			}
			where+=" and ";
			if(filter.getText()!=null && filter.getText().trim().length()>0){
				where+=" ( lower(t.vehicle.matricula) like (:text) )";
			}else{
				where+=" 1=1 ";
			}

			Query query=session.createQuery(select+where);
			if(filter.getStart()!=null){
				query.setParameter("start", filter.getStart());
			}
			if(filter.getEnd()!=null){
				query.setParameter("end", filter.getEnd());
			}
			if(filter.getText()!=null && filter.getText().trim().length()>0){
				query.setParameter("text", "%"+filter.getText().trim().toLowerCase()+"%");
			}

			ArrayList<ArrayList<String>> sheet24Hour=new ArrayList<ArrayList<String>>();



			ArrayList<String> sheet24HourHeader=new ArrayList<String>();
			sheet24HourHeader.add("ID");
			sheet24HourHeader.add(messages.getText("generic.dpa.divorglevel1"));
			sheet24HourHeader.add(messages.getText("generic.dpa.divorglevel2"));
			sheet24HourHeader.add(messages.getText("generic.dpa.divorglevel3"));
			sheet24HourHeader.add(messages.getText("driver.telematiccontrol.associatedVehicle"));
			sheet24HourHeader.add(messages.getText("driver.telematic.point"));
			sheet24HourHeader.add(messages.getText("driver.telematic.status"));
			sheet24HourHeader.add(messages.getText("driver.telematic.statuscomment"));

			sheet24HourHeader.add(messages.getText("driver.telematic.date"));
			sheet24HourHeader.add(messages.getText("driver.telematiccontrol.lastreport"));
			sheet24HourHeader.add(messages.getText("driver.telematic.garage"));
			sheet24HourHeader.add(messages.getText("driver.telematic.occurrences"));

			sheet24Hour.add(sheet24HourHeader);




			Iterator iter=query.list().iterator();
			while(iter.hasNext()){
				TelematicControl telematicControl=(TelematicControl) iter.next();

				ArrayList<String> xlsRow=new ArrayList<String>();

				Query query2=session.createQuery("select d from DivOrgItemLevel1 as d where d.id="+telematicControl.getVehicle().getDivOrg1().getId());

				DivOrgItemLevel1 divOrg=(DivOrgItemLevel1) query2.uniqueResult();
				xlsRow.add(telematicControl.getId().toString());
				xlsRow.add(divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey());

				xlsRow.add(divOrg.getDivOrgItemLevel2().getResourceKey());

				xlsRow.add(divOrg.getResourceKey());
				//xlsRow.add(telematicControl.getDriver().getFirstName()+" "+telematicControl.getDriver().getLastName());
				xlsRow.add(telematicControl.getVehicle()!=null?telematicControl.getVehicle().getMatricula():"");

				switch(telematicControl.getType()){
					case TelematicControlData.TYPE_24HOOUR_WITHOUT_REPORT:

						xlsRow.add(TelematicControlData.getDPAPointLabel(telematicControl.getPoint()));

						xlsRow.add(telematicControl.getStatus());
						xlsRow.add(telematicControl.getStatusComment());

						xlsRow.add(telematicControl.getDate()!=null?Util.formatDate(telematicControl.getDate()):"");
						xlsRow.add(telematicControl.getDate2()!=null?Util.formatDate(telematicControl.getDate2()):"");
						xlsRow.add(telematicControl.getGarage()!=null?telematicControl.getGarage():"");
						xlsRow.add(telematicControl.getOccurrences()!=null?telematicControl.getOccurrences().toString():"");

						sheet24Hour.add(xlsRow);
						break;

				}
			}


			ExcelGenerator.generatorXLS(
					sheet24Hour,messages.getText("driver.telematiccontrol.24hourworeport"),
					output);

		} catch (Exception e) {
			handler.getException(e, "exportTelematicControlDPA", userSessionData, null);
		}
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,vehicleview"
	 */
	public void exportCheckListForestales(boolean comment, int checkType, Date from, Date to, Integer vehicleType, Integer chargeType, Integer checkPlace, OutputStream output, Text messages, UserSessionData userSession) throws Exception {
		try {
			boolean segInternos = false;
			if(checkType == 791) {
				checkType = 79;
				segInternos = true;
			}
			Session session = HibernateUtil.currentSession();
			String sql = "SELECT vc.vehiclecheckid,vc.date,vc.name,v.matricula,d1.resourcekey,vc.result,vciv.item,vciv.itemvalue,i.text,i.type, v.divorg1, vc.serie, v.abs, v.airBag, v.aireAcondicionado, v.suspension, " +
					"vc.inspectedby, vop.renavan, vop.prefix, vop.monitoringsystem, vc.vehicletype, vc.chargetype, vc.checkplace " +
					" FROM vehiclecheck vc " +
					" JOIN vehicles v ON vc.vehicle = v.id " +
					" LEFT OUTER JOIN vehiclesOptionalInfo vop ON vop.id = v.optionalinfo " +
					" JOIN divorgitemlevel1s d1 ON ( ( v.divorg1 = d1.id and not(v.corporation=19) ) or ( v.divorg3 = d1.id and v.corporation=19) ) " +
					" JOIN vehiclecheckitemsvalues vciv ON vciv.vehiclecheck = vc.vehiclecheckid " +
					" JOIN items i ON i.id = vciv.item " + UtilityQuery.corporationDivisionsRestictionsSQL("v", userSession) +
					" AND ";//i.type IN (1,2)
			sql += (checkType == 0) ? "vc.name IN ('79','80','84','91','92') " : "vc.name IN ('"+checkType+"') ";
			if(vehicleType != null) {
				sql += " AND vc.vehicletype = "+vehicleType+" ";
			}
			if(chargeType != null) {
				sql += " AND vc.chargetype = "+chargeType+" ";
			}
			if(checkPlace != null) {
				sql += " AND vc.checkplace = "+checkPlace+" ";
			}
			if(from != null) {
				sql += "AND  to_date(to_char(date, 'YYYY-MM-DD'), 'YYYY-MM-DD') >= "+Util.formatSQL(from,0)+" ";
			}
			if(to != null) {
				sql += "AND  to_date(to_char(date, 'YYYY-MM-DD'), 'YYYY-MM-DD')  < "+Util.formatSQL(to,1)+" ";
			}
			if(userSession.isCorporation(CorporationData.MONTES_DE_PLATA) && checkType == 79) {
				sql += (segInternos) ? " AND vc.inspectedby IN ('Gonzalo Ruiz','Madelin Rinaldi','Pablo Aguiar') " : " AND vc.inspectedby NOT IN ('Gonzalo Ruiz','Madelin Rinaldi','Pablo Aguiar') ";
			}
			sql += "ORDER BY vehiclecheckid";
			Query query = session.createSQLQuery(sql).addScalar("vehiclecheckid", Hibernate.INTEGER)
					.addScalar("date", Hibernate.TIMESTAMP)
					.addScalar("name", Hibernate.STRING)
					.addScalar("matricula", Hibernate.STRING)
					.addScalar("resourcekey", Hibernate.STRING)
					.addScalar("result", Hibernate.INTEGER)
					.addScalar("item", Hibernate.INTEGER)
					.addScalar("itemvalue", Hibernate.STRING)
					.addScalar("text", Hibernate.STRING)
					.addScalar("type", Hibernate.INTEGER)
					.addScalar("serie", Hibernate.STRING)
					.addScalar("abs", Hibernate.BOOLEAN)
					.addScalar("airBag", Hibernate.BOOLEAN)
					.addScalar("aireAcondicionado", Hibernate.BOOLEAN)
					.addScalar("suspension", Hibernate.BOOLEAN)
					.addScalar("inspectedby", Hibernate.STRING)
					.addScalar("renavan", Hibernate.STRING)
					.addScalar("prefix", Hibernate.STRING)
					.addScalar("monitoringsystem", Hibernate.STRING)
					.addScalar("vehicletype", Hibernate.INTEGER)
					.addScalar("chargetype", Hibernate.INTEGER)
					.addScalar("checkplace", Hibernate.INTEGER);

			Iterator it = query.list().iterator();


			ForestalCheckListData checkList = null;
			List<ForestalCheckListData> checks = new LinkedList<ForestalCheckListData>();
			List<ForestalCheckListData> internos = new LinkedList<ForestalCheckListData>();
			List<ForestalCheckListData> seguimientosinternos = new LinkedList<ForestalCheckListData>();
			List<ForestalCheckListData> mercancias = new LinkedList<ForestalCheckListData>();
			List<ForestalCheckListData> seguimientos = new LinkedList<ForestalCheckListData>();
			HashMap<String,ForestalResumeCheckListData> resumeMap = new HashMap<String,ForestalResumeCheckListData>();
			while(it.hasNext()) {
				Object[] data = (Object[])it.next();
				if(checkList == null) {
					HashMap<Integer, String> vcData=null;
					if(comment){
						vcData=findVehicleCheckPointComments((Integer)data[0],userSession);
					}
					checkList = new ForestalCheckListData(data,vcData);
				}else {
					if(checkList.getId().intValue() == ((Integer)data[0]).intValue()) {
						checkList.addItem((Integer)data[6], (String)data[7],(String)data[8],(Integer)data[9]);
					}else {
						if(checkList.getType() == CheckListData.MDP_CHECKLIS_MERCANCIASPELIGROSAS_2) {
							mercancias.add(checkList);
						} else  if (checkList.getType() == CheckListData.UPM_CHECKLIST_INTERNO) {
							internos.add(checkList);
						} else  if (checkList.getType() == CheckListData.MDP_CHECKLIS_MERCANCIASPELIGROSAS || checkList.getType() == CheckListData.CHECK_LIST_FORMULARIO_ACU || checkList.getType() == CheckListData.UPM_CHECKLIST_PESADOS || checkList.getType() == CheckListData.UPM_CHECKLIST_INTERNO|| checkList.getType() == CheckListData.CHECK_LIST_SYNGENTA_BR) {
							checks.add(checkList);
						}else {
							if(checkList.isMDPInternal()) {
								seguimientosinternos.add(checkList);
							} else {
								seguimientos.add(checkList);
							}
						}
						ForestalResumeCheckListData resume = checkList.getResume();
						String key = resume.getKey();
						if(resumeMap.containsKey(key)) {
							resumeMap.get(key).addData(resume);
						}else {
							resumeMap.put(key, resume);
						}
						HashMap<Integer, String> vcData=null;
						if(comment){
							vcData=findVehicleCheckPointComments((Integer)data[0],userSession);
						}
						checkList = new ForestalCheckListData(data,vcData);
					}
				}
			}
			if(checkList != null) {
				if(checkList.getType() == CheckListData.MDP_CHECKLIS_MERCANCIASPELIGROSAS_2) {
					mercancias.add(checkList);
				} else  if (checkList.getType() == CheckListData.UPM_CHECKLIST_INTERNO) {
					internos.add(checkList);
				} else  if (checkList.getType() == CheckListData.MDP_CHECKLIS_MERCANCIASPELIGROSAS || checkList.getType() == CheckListData.CHECK_LIST_FORMULARIO_ACU || checkList.getType() == CheckListData.UPM_CHECKLIST_PESADOS || checkList.getType() == CheckListData.UPM_CHECKLIST_INTERNO || checkList.getType() == CheckListData.CHECK_LIST_SYNGENTA_BR) {
					checks.add(checkList);
				}else {
					if(checkList.isMDPInternal()) {
						seguimientosinternos.add(checkList);
					} else {
						seguimientos.add(checkList);
					}
				}
				ForestalResumeCheckListData resume = checkList.getResume();
				String key = resume.getKey();
				if(resumeMap.containsKey(key)) {
					resumeMap.get(key).addData(resume);
				}else {
					resumeMap.put(key, resume);
				}
			}
			ArrayList<ArrayList<String>> sheetResume = new ArrayList<ArrayList<String>>();
			ArrayList<String> headerResume = new ArrayList<String>();
			headerResume.add(messages.getText("role.contractors.name"));
			headerResume.add(messages.getText("generic.messages.type"));
			headerResume.add(messages.getText("generic.messages.count"));
			headerResume.add(messages.getText("report.checklist.withobservations"));
			headerResume.add(messages.getText("report.checklist.observationscount"));
			headerResume.add(messages.getText("report.checklist.approved"));
			headerResume.add(messages.getText("report.checklist.notapproved"));
			headerResume.add(messages.getText("report.checklist.approvedwithobservations"));
			sheetResume.add(headerResume);
			List<ForestalResumeCheckListData> values = new LinkedList<ForestalResumeCheckListData>(resumeMap.values());
			Collections.sort(values);
			Iterator<ForestalResumeCheckListData> resumeIt = values.iterator();
			while(resumeIt.hasNext()) {
				sheetResume.add(resumeIt.next().getLine(messages));
			}
			switch(checkType) {
			case 0:
				ArrayList<ArrayList<String>> sheetSeguimientos = getForestalSheet(messages, userSession, 79, seguimientos);
				ArrayList<ArrayList<String>> sheetCheckList = getForestalSheet(messages, userSession, 80, checks);
				if(userSession.isCorporation(CorporationData.MONTES_DE_PLATA)) {
					ArrayList<ArrayList<String>> sheetSeguimientosInternos = getForestalSheet(messages, userSession, 79, seguimientosinternos);
					ArrayList<ArrayList<String>> sheetMP = getForestalSheet(messages, userSession, 92, mercancias);
					ArrayList<ArrayList<String>> sheetCheckListInternos = getForestalSheet(messages, userSession, 84, internos);
					ExcelGenerator.generatorXLS(sheetResume,messages.getText("generic.messages.resume"),sheetCheckList,messages.getText("generic.messages.addvehiclecheckupm2"),sheetSeguimientos,messages.getText("generic.messages.addvehiclecheckupm1"),sheetSeguimientosInternos,messages.getText("generic.messages.seguimientosinternos"),sheetCheckListInternos,messages.getText("generic.messages.checkinternal"),sheetMP,messages.getText("datatype.scheduledactivitytype.dangerousmaterials"),output);
				}else if(userSession.isCorporation(CorporationData.WEYERHAEUSER)) {
					ArrayList<ArrayList<String>> sheetCheckListInternos = getForestalSheet(messages, userSession, 84, internos);
					ExcelGenerator.generatorXLS(sheetResume,messages.getText("generic.messages.resume"),sheetCheckList,messages.getText("generic.messages.addvehiclecheckupm2"),sheetSeguimientos,messages.getText("generic.messages.addvehiclecheckupm1"),sheetCheckListInternos,messages.getText("generic.messages.checkinternal"),output);
				}else {
					ExcelGenerator.generatorXLS(sheetResume,messages.getText("generic.messages.resume"),sheetCheckList,messages.getText("generic.messages.addvehiclecheckupm2"),sheetSeguimientos,messages.getText("generic.messages.addvehiclecheckupm1"),output);
				}
				break;
			case 79:
				if(segInternos) {
					sheetCheckList = getForestalSheet(messages, userSession, 79, seguimientosinternos);
					ExcelGenerator.generatorXLS(sheetResume,messages.getText("generic.messages.resume"),sheetCheckList,messages.getText("generic.messages.seguimientosinternos"),output);
				}else {
					sheetSeguimientos = getForestalSheet(messages, userSession, 79, seguimientos);
					ExcelGenerator.generatorXLS(sheetResume,messages.getText("generic.messages.resume"),sheetSeguimientos,messages.getText("generic.messages.addvehiclecheckupm1"),output);
				}
				break;
			case 80:
				sheetCheckList = getForestalSheet(messages, userSession, 80, checks);
				ExcelGenerator.generatorXLS(sheetResume,messages.getText("generic.messages.resume"),sheetCheckList,messages.getText("generic.messages.addvehiclecheckupm2"),output);
				break;
			case 84:
				sheetCheckList = getForestalSheet(messages, userSession, 84, internos);
				ExcelGenerator.generatorXLS(sheetResume,messages.getText("generic.messages.resume"),sheetCheckList,messages.getText("generic.messages.addvehiclecheckupm2"),output);
				break;
			case 86:
				sheetCheckList = getForestalSheet(messages, userSession, 86, checks);
				ExcelGenerator.generatorXLS(sheetResume,messages.getText("generic.messages.resume"),sheetCheckList,messages.getText("Checklist Formulario ACU"),output);
				break;
			case 91:
				sheetCheckList = getForestalSheet(messages, userSession, 91, checks);
				ExcelGenerator.generatorXLS(sheetResume,messages.getText("generic.messages.resume"),sheetCheckList,messages.getText("generic.messages.addvehiclecheckupm2"),output);
				break;
			case 92:
				ArrayList<ArrayList<String>> sheetMP = getForestalSheet(messages, userSession, 92, mercancias);
				ExcelGenerator.generatorXLS(sheetResume,messages.getText("generic.messages.resume"),sheetMP,messages.getText("datatype.scheduledactivitytype.dangerousmaterials"),output);
				break;
			case 95:
				sheetCheckList = getForestalSheet(messages, userSession, 95, checks);
				ExcelGenerator.generatorXLS(sheetResume,messages.getText("generic.messages.resume"),sheetCheckList,messages.getText("generic.messages.addvehiclecheckupm2"),output);
				break;
			}
		} catch (Exception e) {
			handler.getException(e, "exportCheckListForestales", userSession, null);
		}
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,vehicleview"
	 */
	public void vehiclesWOCheckListReportToXLS(Date from, Date to, int status, OutputStream output, Text messages, UserSessionData userSession) throws Exception {
		try {
			Session session = HibernateUtil.currentSession();

			Object[] sheets = new Object[2];
			String[] checks = {"80","79"};
			for(int i = 0; i < checks.length; i++) {
				ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
				ArrayList<String> headerResume = new ArrayList<String>();
				headerResume.add(messages.getText("generic.vehicle.vehicledata.registrationNumber"));
				headerResume.add(messages.getText("generic.vehicle.vehicledata.trade"));
				headerResume.add(messages.getText("generic.vehicle.vehicledata.model"));
				headerResume.add(messages.getText("generic.vehicle.vehicledata.year"));
				headerResume.add(messages.getText("generic.messages.contractor"));
				result.add(headerResume);
				String sql = "SELECT v.matricula,v.trade,v.model,v.year,d1.resourcekey " +
						" FROM vehicles v" +
						" JOIN divorgitemlevel1s d1 ON d1.id = v.divorg1 " +
						" WHERE v.corporation = "+userSession.getCorporationId();
				switch(status) {
					case VehicleData.ACTIVE:
						sql += " AND v.exclusiondate IS NULL";
						break;
					case VehicleData.EXCLUDED:
						sql += " AND v.exclusiondate IS NOT NULL";
						break;
				}
				sql += " AND v.id NOT IN (SELECT vehicle FROM vehiclecheck WHERE name = '"+checks[i]+"' ";
				if(from != null) {
					sql += "AND  to_date(to_char(date, 'YYYY-MM-DD'), 'YYYY-MM-DD') >= "+Util.formatSQL(from,0)+" ";
				}
				if(to != null) {
					sql += "AND  to_date(to_char(date, 'YYYY-MM-DD'), 'YYYY-MM-DD')  < "+Util.formatSQL(to,1)+" ";
				}
				if(userSession.isCorporation(CorporationData.MONTES_DE_PLATA)) {
					sql += " AND vc.inspectedby NOT IN ('Gonzalo Ruiz','Madelin Rinaldi','Pablo Aguiar') ";
				}
				sql += ") ORDER BY matricula";
				Query query = session.createSQLQuery(sql).addScalar("matricula", Hibernate.STRING)
						.addScalar("trade", Hibernate.STRING)
						.addScalar("model", Hibernate.STRING)
						.addScalar("year", Hibernate.INTEGER)
						.addScalar("resourcekey", Hibernate.STRING);

				Iterator it = query.list().iterator();
				while(it.hasNext()) {
					Object[] data = (Object[]) it.next();
					ArrayList<String> line = new ArrayList<String>();
					for(int j = 0; j < data.length; j++) {
						if(j == 1)
							line.add(messages.getText((String)data[j]));
						else
							line.add(String.valueOf(data[j]));
					}
					result.add(line);
				}
				sheets[i] = result;
			}
			ExcelGenerator.generatorXLS((ArrayList<ArrayList<String>>)sheets[0],"Check List",(ArrayList<ArrayList<String>>)sheets[1],"Seguimientos en Ruta",output);
		} catch (Exception e) {
			handler.getException(e, "exportCheckListForestales", userSession, null);
		}
	}

	private ArrayList<ArrayList<String>> getForestalSheet(Text messages, UserSessionData userSession, int checkId, List<ForestalCheckListData> values) throws Exception {
		ArrayList<ArrayList<String>> sheet = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> texts = new ArrayList<ArrayList<String>>();
		CheckListData check = getCheckList(checkId, userSession);
		ArrayList<String> header = new ArrayList<String>();
		header.add("Tipo");
		header.add(messages.getText("generic.vehicle.vehicledata.matricula"));
		if(userSession.isCorporation(CorporationData.FORESTALORIENTAL)) {
			header.add(messages.getText("generic.vehicle.vehicledata.abs"));
			header.add(messages.getText("generic.vehicle.vehicledata.neumaticsuspension"));
		}
		header.add(messages.getText("forestales.check2.h7"));
		if(userSession.isCorporation(CorporationData.FORESTALORIENTAL)) {
			header.add(messages.getText("generic.vehicle.vehicledata.airBag"));
		}
		header.add(messages.getText("forestales.check2.h8"));
		if(userSession.isCorporation(CorporationData.FORESTALORIENTAL)) {
			header.add(messages.getText("generic.vehicle.vehicledata.aireAcondicionado"));
		}
		header.add(messages.getText("forestales.check2.h9"));
		header.add(messages.getText("forestales.check2.h10"));
		header.add(messages.getText("generic.activity.date"));
		if(userSession.isCorporation(new int[]{CorporationData.FORESTALORIENTAL,CorporationData.CONTRACTOR_FORESTAL,CorporationData.MONTES_DE_PLATA})) {
			header.add(messages.getText("checklist.vehicletype"));
			header.add(messages.getText("checklist.chargetype"));
		}
		header.add(messages.getText("role.contractors.name"));
		if(userSession.isCorporation(CorporationData.SYNGENTA_BR)){
			header.add(messages.getText("Email Condutor"));
		}else{
			header.add(messages.getText("Lugar"));
		}
		if(userSession.isCorporation(new int[]{CorporationData.FORESTALORIENTAL,CorporationData.CONTRACTOR_FORESTAL,CorporationData.MONTES_DE_PLATA}) && checkId == CheckListData.UPM_CHECKLIST_PESADOS) {
			header.add(messages.getText("checklist.checkplace"));
		}
		header.add(messages.getText("Inspector"));
		if(userSession.isCorporation(CorporationData.FORESTALORIENTAL)) {
			header.add(messages.getText("generic.vehicle.optionalinfo.renavan"));
			header.add(messages.getText("generic.vehicle.optionalinfo.prefix"));
			header.add(messages.getText("generic.vehicle.optionalinfo.monitoringsystem"));
		}

		header.add(messages.getText("generic.activity.result"));
		int i = 1;
		Iterator<PointData> pointIt = check.getSortPoints();
		while(pointIt.hasNext()) {
			PointData pointData = pointIt.next();
			Iterator<ItemData> itemIt = pointData.getSortItems();
			while(itemIt.hasNext()) {
				ItemData item = itemIt.next();
				if(item.getType().intValue() == 2 || item.getType().intValue() == 1) {
					header.add(String.valueOf(i));
					ArrayList<String> textLine = new ArrayList<String>();
					textLine.add(String.valueOf(i));
					textLine.add(messages.getText(item.getText()));
					texts.add(textLine);
					i++;
				}
			}
		}
		sheet.add(header);
		Collections.sort( values);
		Iterator<ForestalCheckListData> itSeguimientos = values.iterator();
		while(itSeguimientos.hasNext()) {
			sheet.add(itSeguimientos.next().getLine(check,messages,userSession));
		}
		sheet.add(new ArrayList<String>());
		Iterator<ArrayList<String>> it = texts.iterator();
		while(it.hasNext())
			sheet.add(it.next());
		return sheet;
	}

	/**
	 * @ejb.interface-method view-type = "remote"
	 * @ejb.permission role-name = "administrator,systemaccess,vehicleview"
	 * @param data
	 * @throws Exception
	 */
	public ArrayList<DPAGoalData> getDPAGoals(Integer year,UserSessionData userSession) throws Exception {
		ArrayList<DPAGoalData> res=new ArrayList<DPAGoalData>();
		try{
			Session session=HibernateUtil.currentSession();
			Query query=session.createQuery("select d from DPAGoal as d where year="+year);
			Iterator iter=query.list().iterator();
			while(iter.hasNext()){
				DPAGoal goal=(DPAGoal) iter.next();
				res.add(goal.getData(userSession));
			}
    	}catch (Exception e) {
			Object[] parameters = {year};
    		handler.getException(e,"getDPAGoals",userSession,parameters);
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
		try{
			Session session=HibernateUtil.currentSession();
			Query query=session.createQuery("select d from DPAGoal as d where year="+year+" and month="+month);
			Iterator iter=query.list().iterator();
			while(iter.hasNext()){
				DPAGoal goal=(DPAGoal) iter.next();
				res.add(goal.getData(userSession));
			}
    	}catch (Exception e) {
			Object[] parameters = {year};
    		handler.getException(e,"getDPAGoals",userSession,parameters);
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
		try{
			Session session=HibernateUtil.currentSession();
			Query query=session.createQuery("select d from DPAVehicleActive as d where year="+year);
			Iterator iter=query.list().iterator();
			while(iter.hasNext()){
				DPAVehicleActive va=(DPAVehicleActive) iter.next();
				res.add(va.getData(userSession));
			}
    	}catch (Exception e) {
			Object[] parameters = {year};
    		handler.getException(e,"getDPAVehicleActive",userSession,parameters);
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
		try{
			Session session=HibernateUtil.currentSession();
			Query query=session.createQuery("select d from DPAVehicleActive as d where year="+year+" and month="+month);
			Iterator iter=query.list().iterator();
			while(iter.hasNext()){
				DPAVehicleActive va=(DPAVehicleActive) iter.next();
				res.add(va.getData(userSession));
			}
    	}catch (Exception e) {
			Object[] parameters = {year};
    		handler.getException(e,"getDPAVehicleActive",userSession,parameters);
    	}
		return res;
	}


	/**
	 * @ejb.interface-method view-type = "remote"
	 * @ejb.permission role-name = "administrator,systemaccess,dpachecklist,driverview"
	 * @param data
	 * @throws Exception
	 */
	public String getContractorDataMail(Integer divOrg1,UserSessionData userSession) throws Exception {
		try{
			String queryStr = " select contractorData from ContractorData as contractorData " +
					"where contractorData.state = 1 and contractorData.id="+divOrg1;
			//" and contractorData.id in (330219)";
			Session session = HibernateUtil.currentSession();
			Query query = session.createQuery(queryStr);
			List listAux=query.list();
			ContractorData c =new ContractorData();
			c.setMail("vluongo@cepasafedrive.com");
			if(listAux.size()>0){
				c=(ContractorData)listAux.iterator().next();
			}

			return c!=null ? c.getMail():"";
    	}catch (Exception e) {
			Object[] parameters = {divOrg1};
    		handler.getException(e,"getContractorDataMail",userSession,parameters);
    	}
		return "";
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "fleetinsertdata,administrator"
	 */
	public HashMap getActiveVehicleCount(UserSessionData userSessionData) throws Exception {
		HashMap map = new HashMap();
		try{
			Session session = HibernateUtil.currentSession();
			int corporationId = userSessionData.getCorporationId();
			String[] keys = UtilityQuery.getCorporationKey("v",corporationId);
			String queryStr = "SELECT "+keys[0].replaceAll(","," || ';' || ")+" AS key,COUNT(*) AS count " +
					"FROM vehicles v "+keys[1]+" WHERE corporation = "+corporationId+" AND exclusiondate IS NULL ";
			switch(corporationId) {
				case CorporationData.MONSANTO:
					queryStr += "AND v.id IN (SELECT vehicle FROM vehiclefleets WHERE id = 9508) ";
					break;
				case CorporationData.JJ:
					queryStr += "AND v.id IN (SELECT vehicle FROM vehiclefleets WHERE id IN (9496,9500,9518,9528) ";
					break;
			}
			queryStr += "GROUP BY "+keys[0];
			Query query = session.createSQLQuery(queryStr).addScalar("key",Hibernate.STRING).addScalar("count",Hibernate.INTEGER);
			Iterator it = query.list().iterator();
			while(it.hasNext()) {
				Object[] data = (Object[])it.next();
				map.put(data[0], data[1]);
			}
    	}catch (Exception e) {
			Object[] parameters = {};
    		handler.getException(e,"getActiveVehicleCount",userSessionData,parameters);
    	}
		return map;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "fleetinsertdata,administrator"
	 */
	public HashMap getActiveVehicleCount(Integer fleet, Integer month, Integer year, UserSessionData userSessionData) throws Exception {
		HashMap map = new HashMap();
		try{
			Session session = HibernateUtil.currentSession();
			int corporationId = userSessionData.getCorporationId();
			String[] keys = UtilityQuery.getCorporationKey("v",corporationId);
			String queryStr = "SELECT "+keys[0].replaceAll(","," || ';' || ")+" AS key,COUNT(*) AS count " +
					"FROM vehicles v "+keys[1]+" WHERE corporation = "+corporationId + " AND v.exclusiondate IS NULL";
			queryStr += " AND v.id IN (SELECT vehicle FROM vehiclefleets WHERE id = "+fleet+") ";
			//queryStr += " AND v.id IN (SELECT vehicle FROM odometers WHERE fleet = "+fleet+" AND period = "+month+" AND year = "+year+" AND odometer > 0) ";
			queryStr += " GROUP BY "+keys[0];
			Query query = session.createSQLQuery(queryStr).addScalar("key",Hibernate.STRING).addScalar("count",Hibernate.INTEGER);
			Iterator it = query.list().iterator();
			while(it.hasNext()) {
				Object[] data = (Object[])it.next();
				map.put(data[0], data[1]);
			}
    	}catch (Exception e) {
			Object[] parameters = {};
    		handler.getException(e,"getActiveVehicleCount",userSessionData,parameters);
    	}
		return map;
	}

	public Collection getPointsUPM(Calendar from, Calendar to, Integer transporter, UserSessionData userSessionData) throws Exception {
		String sql = "SELECT v.matricula,d1.resourcekey,pm.date,description,pm.amount,pm.actualtotal,pa.total " +
				"FROM pointmoves pm " +
				"JOIN pointaccount pa ON pm.account = pa.id " +
				"JOIN vehicles v ON pa.reference = v.id " +
				"JOIN divorgitemlevel1s d1 ON d1.id = v.divorg1 " +
				"WHERE v.corporation = "+userSessionData+" " +
				"AND pm.date >= '2015-11-01'" +
				"ORDER BY matricula,date,actualtotal desc";
		return null;
	}


  	/**
   	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     * @throws Exception
   	 */
	public ResumeItem getVehicleResume(UserSessionData userSessionData)  throws Exception {
		ResumeItem item = new ResumeItem("query.resume.vehicles");
		try {
			String[] s = userSessionData.getFilter().getPrincipalFields("v",userSessionData.getFilter().getPrincipalCount());
			String sql = "SELECT c.resourcekey AS c "+s[0]+", count(*) AS count" +
						" FROM vehicles v, country c"+s[1]+
						" WHERE corporation = "+userSessionData.getCorporationId()+" AND exclusiondate IS NULL AND c.id = v.country "+s[2]+
						" GROUP BY c,"+s[3];
	        Session session = HibernateUtil.currentSession();

	        SQLQuery q = session.createSQLQuery(sql);
	        q.addScalar("c", Hibernate.STRING);
			StringTokenizer token = new StringTokenizer(s[3], ",");
			while (token.hasMoreTokens()) {
				q.addScalar(token.nextToken(), Hibernate.STRING);
			}
	        q.addScalar("count", Hibernate.INTEGER);
	        Iterator it = q.list().iterator();
	        while(it.hasNext()) {
	        	item.addData(0,(Object[]) it.next());
	        }
        } catch(Exception e){
			Object[] p = {};
			handler.getException(e, "getVehicleResume", userSessionData, p);
        }
		return item;
	}

	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate,dpachecklist"
	 * @param data
	 * @throws Exception 
	 */
	public int[] getVehicleDataLinks(String plate, int corporation, UserSessionData userSessionData) throws Exception {
		int[] values = new int[4];
		try {
			Session session = HibernateUtil.currentSession();
			String sql = "SELECT id, divorg1, corporation FROM vehicles WHERE corporation IN (9, 50, 19) AND matricula = '"+plate+"'";
			Query q = session.createSQLQuery(sql).addScalar("id", Hibernate.INTEGER).addScalar("divorg1", Hibernate.INTEGER).addScalar("corporation", Hibernate.INTEGER);
			Iterator it = q.list().iterator();
			String sqlLinks = "WHERE corporation = "+corporation+" ";
			boolean exist = false;
			if(it.hasNext()) {
				exist = true;
				Object[] data = (Object[])it.next(); 
				switch(((Integer)data[2]).intValue()) {
					case CorporationData.WEYERHAEUSER:
						sqlLinks += "AND lumin = "+data[0];
						break;
					case CorporationData.FORESTALORIENTAL:
						sqlLinks += "AND fosa = "+data[0];
						break;
					case CorporationData.MONTES_DE_PLATA:
						sqlLinks += "AND mdp = "+data[0];
						break;
				}
			}
			if(exist) {
				sqlLinks = "SELECT id, fosa, mdp, lumin FROM vehicletransporterlinks "+sqlLinks;
				q = session.createSQLQuery(sqlLinks).addScalar("id", Hibernate.INTEGER).addScalar("fosa", Hibernate.INTEGER).addScalar("mdp", Hibernate.INTEGER).addScalar("lumin", Hibernate.INTEGER);
				it = q.list().iterator();
				while(it.hasNext()) {
					Object[] data = (Object[])it.next();
					for(int i = 0; i < 4; i++) {
						if(data[i] != null) {
							values[i] = ((Integer)data[i]).intValue();
						}
					}
				}
			}
    	}catch (Exception e) {
			Object[] p = {plate};
			handler.getException(e, "getVehicleTransporterLinks", userSessionData, p);
    	} 
		return values;
	}

	/**
	 * @ejb.interface-method view-type = "remote"
	 * @ejb.transaction type="Required"
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate,vehiclecreate,drivercreatevehicle,vehiclechecklist,dpachecklist"
	 * @param data
	 * @throws Exception
	 */
	public Integer[] getVehicleLinks(Integer vehicle, UserSessionData userSessionData) throws Exception {
		try{
            Session session = HibernateUtil.currentSession();
            String sql = "SELECT id, fosa, mdp, lumin FROM vehicletransporterlinks WHERE ";
			switch(userSessionData.getCorporationId()) {
				case CorporationData.FRAYLOG: case CorporationData.SAN_NICOLAS: case CorporationData.SOCTMA:
					sql += " id = "+vehicle;
					break;
				case CorporationData.FORESTALORIENTAL:
					sql += " fosa = "+vehicle;
					break;
				case CorporationData.MONTES_DE_PLATA:
					sql += " mdp = "+vehicle;
					break;
				case CorporationData.WEYERHAEUSER:
					sql += " lumin = "+vehicle;
					break;
			}

           	Query q = session.createSQLQuery(sql).addScalar("id", Hibernate.INTEGER).addScalar("fosa", Hibernate.INTEGER).addScalar("mdp", Hibernate.INTEGER).addScalar("lumin", Hibernate.INTEGER);
        	Iterator it = q.list().iterator();
        	if(it.hasNext()) {
        		Object[] data = (Object[])it.next();
        		return new Integer[]{(Integer)data[0],(Integer)data[1],(Integer)data[2],(Integer)data[3]};
        	}
    	}catch (Exception e) {
    	}
		return null;

	}
	
    /**
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws CommunicationProblemException
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,vehicleedit,drivervehicleedit,drivercreateevent,vehiclechecklist,driverodometer,dpachecklist,jjreporter"
     */
	public String[][] getVehicleAsociation(Integer vehicleId, UserSessionData userSessionData) throws Exception {
		String[][] s = new String[2][4];
		try{
            Session session = HibernateUtil.currentSession();
            String sql = "SELECT fosa, mdp FROM vehicletransporterlinks WHERE id = "+vehicleId;
           	Query q = session.createSQLQuery(sql).addScalar("fosa", Hibernate.INTEGER).addScalar("mdp", Hibernate.INTEGER);
        	Iterator it = q.list().iterator();
        	if(it.hasNext()) {
        		Object[] data = (Object[])it.next();
        		for(int i = 0; i < 2; i++) {
        			if(data[i] != null) {
        				sql = "SELECT matricula, firstname, lastname, v.exclusiondate, d1.resourcekey " +
        						"FROM vehicles v " +
        						"LEFT JOIN drivers d ON v.driver = d.id " +
        						"JOIN divorgitemlevel1s d1 on d1.id = v.divorg1 WHERE v.id = "+data[i];
        				Query q2 = session.createSQLQuery(sql).addScalar("matricula", Hibernate.STRING).addScalar("firstname", Hibernate.STRING).addScalar("lastname", Hibernate.STRING).addScalar("exclusiondate", Hibernate.DATE).addScalar("resourcekey", Hibernate.STRING);
        				Iterator it2 = q2.list().iterator();
        				if(it2.hasNext()) {
        					Object[] data2 = (Object[]) it2.next();
        					s[i][0] = String.valueOf(data2[0]);
        					if(data2[1] != null)
        						s[i][1] = data2[1]+" "+data2[2];
        					s[i][2] = String.valueOf(data2[3]);
        					s[i][3] = String.valueOf(data2[4]);
        				}
        			}
        		}
        	}
    	}catch (Exception e) {
			Object[] p = {vehicleId};
			handler.getException(e, "getVehicleResume", userSessionData, p);
    	} 
		return s;
	}


    /**
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws CommunicationProblemException
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,finecreate,vehicleedit,drivervehicleedit,drivercreateevent,vehiclechecklist,driverodometer,dpachecklist,jjreporter"
     */
    public VehicleData findVehicleTransporter(Integer id, UserSessionData userSessionData) throws Exception {
        VehicleData data = null;
        try {
            if (id == null) {
                throw new InvalidDataException("findVehicleTransporter","id = null");
            }
            if (userSessionData == null) {
                throw new DeslogedException("findVehicleTransporter","session = null");
            }
            Session session = HibernateUtil.currentSession();
            String sql = "SELECT vehicle FROM Vehicle AS vehicle ";
            sql += "WHERE vehicle.id = :id ";
            Query query = session.createQuery(sql);
            query.setParameter("id", id);

            Vehicle vehicle = (Vehicle)query.uniqueResult();
            if(vehicle != null) {
                data = vehicle.getVehicleData();
            }
        } catch (Exception e) {
			Object[] parameters = {id};
            handler.getException(e,"findVehicleTransporter",userSessionData,parameters);
        }

        return data;
    }

	/**
	 * @ejb.interface-method view-type = "remote"
	 * @ejb.transaction type="Required"
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate,vehiclecreate,drivercreatevehicle,vehiclechecklist,dpachecklist"
	 * @param data
	 * @throws Exception
	 */
	public Integer[] getVehicleLinks(Integer vehicle,int corporation,int tCorporation,UserSessionData userSessionData) throws Exception {
		try{
            Session session = HibernateUtil.currentSession();
            String sql = "SELECT id, fosa, mdp, lumin FROM vehicletransporterlinks WHERE corporation = "+tCorporation;
			switch(corporation) {
				case CorporationData.FRAYLOG: case CorporationData.SAN_NICOLAS: case CorporationData.SOCTMA:
					sql += " AND id = "+vehicle;
					break;
				case CorporationData.FORESTALORIENTAL:
					sql += " AND fosa = "+vehicle;
					break;
				case CorporationData.MONTES_DE_PLATA:
					sql += " AND mdp = "+vehicle;
					break;
				case CorporationData.WEYERHAEUSER:
					sql += " AND lumin = "+vehicle;
					break;
			}

           	Query q = session.createSQLQuery(sql).addScalar("id", Hibernate.INTEGER).addScalar("fosa", Hibernate.INTEGER).addScalar("mdp", Hibernate.INTEGER).addScalar("lumin", Hibernate.INTEGER);
        	Iterator it = q.list().iterator();
        	if(it.hasNext()) {
        		Object[] data = (Object[])it.next();
        		return new Integer[]{(Integer)data[0],(Integer)data[1],(Integer)data[2],(Integer)data[3]};
        	}
    	}catch (Exception e) {
    	}
		return null;

	}
}
