package persistence.vehicle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.SessionBean;
import javax.imageio.ImageIO;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.jboss.logging.Logger;

import persistence.activity.tables.Point;
import persistence.administration.corporation.tables.DivOrgItemLevel1;
import persistence.administration.corporation.tables.PointMove;
import persistence.administration.tables.LevelOfServiceVehicleDPA;
import persistence.hibernate.HibernateUtil;
import persistence.log.LoggerFormatting;
import persistence.log.vehicle.VehicleCheckLog;
import persistence.log.vehicle.VehicleLog;
import persistence.log.vehicle.VehicleOptionalInfoLog;
import persistence.log.vehicle.VehicleSMFLog;
import persistence.util.ExceptionHandler;
import persistence.util.Util;
import persistence.util.UtilBase64;
import persistence.vehicle.tables.CheckListTransporterLink;
import persistence.vehicle.tables.Complaint;
import persistence.vehicle.tables.DPAGoal;
import persistence.vehicle.tables.DPAVehicleActive;
import persistence.vehicle.tables.InstructorActivity;
import persistence.vehicle.tables.Trailer;
import persistence.vehicle.tables.TrailerCheck;
import persistence.vehicle.tables.Unconformity;
import persistence.vehicle.tables.UnconformityPK;
import persistence.vehicle.tables.Vehicle;
import persistence.vehicle.tables.VehicleCheck;
import persistence.vehicle.tables.VehicleCheckListImage;
import persistence.vehicle.tables.VehicleContract;
import persistence.vehicle.tables.VehicleContractPK;
import persistence.vehicle.tables.VehicleMaintenance;
import persistence.vehicle.tables.VehicleOptionalInfo;
import persistence.vehicle.tables.VehiclePursuitRoute;
import persistence.vehicle.tables.VehicleSMF;
import persistence.vehicle.tables.VehicleTrailer;
import persistence.vehicle.tables.VehicleTrailerCheck;
import persistence.vehicle.tables.VehicleTransporterLink;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.dpa.LevelOfServiceVehicleDPAData;
import communication.administration.user.UserSessionData;
import communication.alert.AlertConstants;
import communication.exception.CommunicationProblemException;
import communication.exception.DCException;
import communication.exception.DeslogedException;
import communication.exception.InvalidDataException;
import communication.util.LogActions;
import communication.util.Validate;
import communication.vehicle.report.CheckListImageData;
import communication.vehicle.tables.ComplaintData;
import communication.vehicle.tables.DPAGoalData;
import communication.vehicle.tables.DPAVehicleActiveData;
import communication.vehicle.tables.InstructorActivityData;
import communication.vehicle.tables.TrailerCheckData;
import communication.vehicle.tables.TrailerData;
import communication.vehicle.tables.UnconformityData;
import communication.vehicle.tables.VehicleCheckData;
import communication.vehicle.tables.VehicleContractData;
import communication.vehicle.tables.VehicleData;
import communication.vehicle.tables.VehicleMaintenanceData;
import communication.vehicle.tables.VehiclePursuitRouteData;
/**
 * @ejb.bean name="VehicleABM"
 *           display-name="Name for VehicleABM"
 *           description="Description for VehicleABM"
 *           jndi-name="ejb/VehicleABM"
 *           type="Stateless"
 */

public abstract class VehicleABMBean implements SessionBean {	
	
	 private ExceptionHandler handler = new ExceptionHandler(VehicleABMBean.class);
	    	
	
	/**
	 * Logger for this class
	 */
	private Logger logger=Logger.getLogger(VehicleABMBean.class);
	private LoggerFormatting logFormatting=new LoggerFormatting();	
	
    /**
     * Init transaction
     * @ejb.create-method
     * @ejb.permission role-name = "administrator,vehicleupdate,vehicledelete,vehiclecreate,drivercreatevehicle,drivervehicleupdate,vehiclechecklist,dpachecklist,jjreporter,systemaccess"
     */
    public void ejbCreate()
    throws javax.ejb.CreateException {
    }
	
	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,vehiclecreate,drivercreatevehicle,dpachecklist"
	 */
	public Integer vehicleCreate(VehicleData data,UserSessionData userSessionData,String comment) throws Exception{		
		
		try {

			if (userSessionData == null) {
				throw new DeslogedException("vehicleCreate","userSessionData = null");
			}
			if (data == null) {
				throw new InvalidDataException("vehicleCreate","data = null");
			}
            if(userSessionData.isDriver() && !data.getDriver().getId().equals(userSessionData.getDriverData().getId())) {
                throw new InvalidDataException("vehicleCreate","Invalid driver");
            }

			// Validate data
			String validVehicle = validate(data, userSessionData);
			if (validVehicle != null) {
				throw new InvalidDataException("vehicleCreate",validVehicle);
			}

			Vehicle vehicle = new Vehicle(data);

			// Al crear guardo la info de la fecha de inclusion en inclusiondate
			if (vehicle.getInclusionDate() == null){
				vehicle.setInclusionDate(new Date());
			}
			
            if(userSessionData.isForest()) {
                vehicle.setForestalData(data);
            }
            Session session = HibernateUtil.currentSession();

            // Si tiene información opcional, por ejemplo Klabin, Cimsa, Yoli , Pepsico
            if (vehicle.getOptionalInfo() != null){
            	// Guardo la información opcional del vehiculo:
            	VehicleOptionalInfo optionalInfo = vehicle.getOptionalInfo();
            	Integer idOptionalInfo = (Integer)session.save(optionalInfo);
            	optionalInfo.setId(idOptionalInfo);
            	vehicle.setOptionalInfo(optionalInfo);
            	
            	// Guardo el LOG
            	VehicleOptionalInfoLog logOptional = new VehicleOptionalInfoLog(
            				optionalInfo, userSessionData.getFilter().getLoginName(), new Date(),
            				LogActions.CREATE,comment);
            	session.save(logOptional);
            }
            
            // Si tiene vehicleSMF - Monsanto
            if (vehicle.getVehicleSMF() != null){            	
            	VehicleSMF vehicleSMF = vehicle.getVehicleSMF();
            	Integer idVehicleSMF = (Integer)session.save(vehicleSMF);
            	vehicleSMF.setVehicleSMFid(idVehicleSMF);
            	vehicle.setVehicleSMF(vehicleSMF);
            	
            	// Guardo el LOG
            	VehicleSMFLog logOptional = new VehicleSMFLog(
            				vehicleSMF, userSessionData.getFilter().getLoginName(), new Date(),
            				LogActions.CREATE,comment);
            	session.save(logOptional);
            }

			
            Integer id = (Integer)session.save(vehicle);
			vehicle.setId(id);
			
			VehicleLog log = new VehicleLog(vehicle,userSessionData.getFilter().getLoginName(),
					new Date(),LogActions.CREATE,comment);
			session.save(log);		
			
			if(vehicle.getCorporation().getId().equals(CorporationData.DPA)){
				LevelOfServiceVehicleDPAData dataLevel=new LevelOfServiceVehicleDPAData();
				dataLevel.setInspector(userSessionData.getFullName());
				dataLevel.setVehicleId(id);
				dataLevel.setMatricula(data.getMatricula());
				dataLevel.setDate(new Date());
				dataLevel.setStatus("A");
				dataLevel.setChassi(data.getChasisNumber());
				dataLevel.setYearModel(data.getYear()!=null?data.getYear().toString():"");
				dataLevel.setModel(data.getModel());
				dataLevel.setRenavan(data.getPermissionNumber());
				
				Iterator it = data.getDivOrgsIterator();
			    if(it.hasNext()) {
			        DivOrgData divOrg = (DivOrgData)it.next();
			        ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
			        while(item1.getSubItems() != null) {
			            item1 = (ItemData)item1.getSubItems().getItemIterator().next();
			        }
			        dataLevel.setFleet(item1.getId());
			    }
				    
				LevelOfServiceVehicleDPA level=new LevelOfServiceVehicleDPA(dataLevel);
				session.save(level);
			}
			
			return id;

		} catch (Exception e) {
			Object[] parameters = {data,comment};
		    handler.getException(e,"vehicleCreate",userSessionData,parameters);
		}
		
		return null;
	}
	
	/**
	 * @ejb.permission role-name = "administrator,cepascheduleactivitycreate,cdactivitycreate,vehiclechecklist,dpachecklist,jjreporter" 
	 * @ejb.interface-method view-type = "both"
	 * @param data
	 * @param userRequest
	 */
	public Integer instructorActivityCreate( InstructorActivityData data, UserSessionData userSessionData ) throws Exception {
		try {
			if (userSessionData == null) {
				throw new DeslogedException("instructorActivityCreate","userSessionData = null");
			}
			if (data == null) {
				throw new InvalidDataException("instructorActivityCreate","data = null");
			}
			
			
			InstructorActivity instructorActivity = new InstructorActivity(data);
			Session session = HibernateUtil.currentSession();
			
			Integer id = (Integer)session.save(instructorActivity);
			
			instructorActivity.setId(id);
			
			return id;
		} catch(Exception e) {
			Object[] parameters = {data};
		    handler.getException(e,"instructorActivityCreate",userSessionData,parameters);
		}
		
		return null;
	}

	/**
	 * @ejb.interface-method
	 * 
	 * @param data
	 * @param userRequest
	 * @throws InvalidDataException
	 * @throws CommunicationProblemException
	 * @throws DeslogedException
	 * @ejb.permission role-name = "administrator,vehicledelete"
	 */
	public void vehicleDelete(VehicleData data, UserSessionData userRequest, String comment) 
			throws InvalidDataException, CommunicationProblemException,
			DeslogedException,DCException {
		try {
			// Check integrity data
			if (data == null) {
				throw new InvalidDataException(
						"Class persistence.vehicle.VehicleABM(vehicleDelete), vehicle data is empty",
						"generic.vehicle.vehicledata.isempty");
			}
			if (data.getId() == null) {
				throw new InvalidDataException(
						"Class persistence.vehicle.VehicleABM(vehicleDelete), primary key is empty",
						"persistence.vehicle.vehicledata.primarykey.isempty");
			}
			if (userRequest == null) {
				throw new DeslogedException(
						"Class persistence.vehicle.VehicleABM(vehicleDelete), session data is empty",
						"generic.error.deslogued");
			}
			Vehicle vehicle = null;

			Session session = HibernateUtil.currentSession();

			vehicle = (Vehicle) session.load(Vehicle.class, data.getId());

			VehicleLog log = new VehicleLog(vehicle,userRequest.getFilter().getLoginName(),
					new Date(),LogActions.DELETE,comment);
			session.save(log);			
			
			// Si tiene información opcional, por ejemplo Klabin
            if (vehicle.getOptionalInfo() != null){
            	// Borro la información opcional del vehiculo:
            	VehicleOptionalInfo optionalInfo = (VehicleOptionalInfo)
            		session.load(VehicleOptionalInfo.class,vehicle.getOptionalInfo().getId());
            	
            	// Guardar un LOG 
            	VehicleOptionalInfoLog logOptional = new VehicleOptionalInfoLog(optionalInfo,
            			userRequest.getFilter().getLoginName(),new Date(),LogActions.DELETE,comment);
            	session.save(logOptional);
            	
            	session.delete(optionalInfo);            	
            }
            
            //Vehicle SMF MONSANTO	
            if (vehicle.getVehicleSMF() != null){
            	// Borro la información opcional del vehiculo:
            	VehicleSMF vehicleSMF = (VehicleSMF)session.load(VehicleSMF.class,vehicle.getVehicleSMF().getVehicleSMFid());
            	
            	// Guardar un LOG 
            	VehicleSMFLog vehicleSMFlog = new VehicleSMFLog(vehicleSMF,
            			userRequest.getFilter().getLoginName(),new Date(),LogActions.DELETE,comment);
            	session.save(vehicleSMFlog);
            	
            	session.delete(vehicleSMF);            	
            }
			session.delete(vehicle);

		} catch (HibernateException he) {
			 logger.fatal(logFormatting.formattingText(he,"vehicleDelete(VehicleData, UserSessionData)","HibernateException",userRequest.getFilter().getLoginName()), he);
			throw new CommunicationProblemException(
					"Has ocurred error in persistence.vehicle.VehicleABM(vehicleDelete), HibernateException",
					"generic.error.hibernate");
		} catch (Exception e) {
			 logger.error(logFormatting.formattingText(e,"vehicleDelete(VehicleData,UserSessionData)","Exception",userRequest.getFilter().getLoginName()),e);
			throw new DCException(e);
		}		
		
	}
	
	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate,dpachecklist"
	 * @param data
	 * @param userRequest
	 */
	public void vehicleUpdate(VehicleData data, UserSessionData userRequest, String comment, int action) throws Exception {
	    try{
	        Session session=HibernateUtil.currentSession();
	        validate(data,userRequest);

			Vehicle vehicle = (Vehicle)session.load(Vehicle.class,data.getId());
			
			vehicle.updateData(data, session);
            if(userRequest.isForest()) {
                vehicle.setForestalData(data);
            }
            
            System.out.println("VehicleABMBean, Vehicle Use:"+vehicle.getUse().getResourceBundleId()+" -> "+vehicle.getExtradata1());
            
            // Si tiene información opcional, por ejemplo Klabin, Yoli, CIMSA, PEPSICO
            if (data.getOptionalInfo() != null){
            	// Borro la información opcional del vehiculo:
            	VehicleOptionalInfo optionalInfo = 
            			(data.getOptionalInfo().getId() != null) ?
            				(VehicleOptionalInfo)
            				session.load(VehicleOptionalInfo.class,data.getOptionalInfo().getId()): 
            				null;
            	
            	if (optionalInfo != null){
            		optionalInfo.updateData(data.getOptionalInfo(),session);
            		session.update(optionalInfo);
            	}else{
            		optionalInfo = new VehicleOptionalInfo(data.getOptionalInfo());
            		session.save(optionalInfo);            		
            	}
            	vehicle.setOptionalInfo(optionalInfo);    	  
            	
            	//Guardar un LOG 
            	VehicleOptionalInfoLog logOptional = new VehicleOptionalInfoLog(optionalInfo,
            			userRequest.getFilter().getLoginName(),new Date(),LogActions.UPDATE,comment);
            	session.save(logOptional);
            	
            }  
            // FIN Informacion Opcional
            
            // Si tiene información SMF - Safe Monitoring Fleet, Monsanto
            if(vehicle.getVehicleSMF() != null){
    	    	VehicleSMF vehicleSMF =  (VehicleSMF)session.load(VehicleSMF.class,vehicle.getVehicleSMF().getVehicleSMFid());    	    	    	    	
	    		vehicleSMF.updateData(data.getVehicleSMFData(),session);
	    		session.update(vehicleSMF);            	
	    		vehicle.setVehicleSMF(vehicleSMF);        	
	    		
	    		//Guarda el Log de VehicleSMF
	    		VehicleSMFLog log = new VehicleSMFLog(vehicleSMF,userRequest.getFilter().getLoginName(),
	    				new Date(),action,comment);
	    		session.save(log);
	    		
            }else if (data.getVehicleSMFData() != null){
            	// Aca vehicle.getVehicleSMF() = null, entonces creo el vehicleSMF en la base de datos xq no existia previamente
            	VehicleSMF vehicleSMF = new VehicleSMF(data.getVehicleSMFData());
            	Integer vehicleSMFid = (Integer)session.save(vehicleSMF);
            	vehicleSMF.setVehicleSMFid(vehicleSMFid);
            	vehicle.setVehicleSMF(vehicleSMF);     
            	
            	//Guarda el Log de VehicleSMF
	    		VehicleSMFLog log = new VehicleSMFLog(vehicleSMF,userRequest.getFilter().getLoginName(),
	    				new Date(),LogActions.CREATE,comment);
	    		session.save(log);
            }
            // FIN Si tiene información SMF - Safe Monitoring Fleet, Monsanto
            
            
            session.update(vehicle);    		
            VehicleLog log = new VehicleLog(vehicle,userRequest.getFilter().getLoginName(),
            		new Date(),action,comment);
            session.save(log);
            if(userRequest.getCorporationId()==CorporationData.DPA){ 
            	Query query=session.createQuery("select c from LevelOfServiceVehicleDPA as c, Vehicle as d where c.vehicleId="+vehicle.getId()+" and c.vehicleId=d.id and  c.date in(select max(c2.date)  from LevelOfServiceVehicleDPA as c2 where c2.vehicleId=c.vehicleId)");
            	List list=query.list();
            	if(list.size()>0){
            		LevelOfServiceVehicleDPA levelOfService=(LevelOfServiceVehicleDPA)list.iterator().next();
            		levelOfService.setMatricula(vehicle.getMatricula());
            		levelOfService.setChassi(vehicle.getChasisNumber());
            		levelOfService.setYearModel(vehicle.getYear()!=null?vehicle.getYear().toString():null);
            		levelOfService.setModel(data.getModel());
            		levelOfService.setRenavan(data.getPermissionNumber());
    				
            		Iterator it = data.getDivOrgsIterator();
    			    if(it.hasNext()) {
    			        DivOrgData divOrg = (DivOrgData)it.next();
    			        ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
    			        while(item1.getSubItems() != null) {
    			            item1 = (ItemData)item1.getSubItems().getItemIterator().next();
    			        }
    			        levelOfService.setFleet(item1.getId());
    			    }
    			    
    	    		if(vehicle.getExclusionDate()==null && levelOfService.getStatus().equalsIgnoreCase("I")){
    	    			levelOfService.setStatus("A");
    	    		}else if(vehicle.getExclusionDate()!=null && levelOfService.getStatus().equalsIgnoreCase("A") ){
    	    			levelOfService.setStatus("I");
    	    		}
    	    		
    	    		session.save(levelOfService);
            	}
            }
        } catch (Exception e) {
			Object[] parameters = {data,comment,action};
	        handler.getException(e,"update",userRequest,parameters);
        }
    }
	
	/**
	 * @param data
	 * @return null if is valid, String with message if is invalid
	 */
	private String validate(VehicleData data,UserSessionData userSession) throws Exception {	
	
		if (data.getUse() == null || data.getUse().getResourceBundleId() == null) {
			return "generic.vehicle.vehicledata.vehicleUseInvalid";
		}		
		if (data.getType() == null || data.getType().getResourceBundleId() == null) {
			return "generic.vehicle.vehicledata.vehicleTypeInvalid";
		}
		if (data.getTrade() == null || data.getTrade().getResourceBundleId() == null) {
			return "generic.vehicle.vehicledata.tradeInvalid";
		}		
/*		if (data.getFuel() == null || data.getFuel().getResourceBundleId() == null) {
			return "generic.vehicle.vehicledata.fuelInvalid";
		}
*/						
/*		if (data.getOdometerUnit() == null || data.getOdometerUnit().getResourceBundleId() == null) {
			return "generic.vehicle.vehicledata.distanceUnitInvalid";
		}
*/		
/*		if (data.getOwnerShip() == null || data.getOwnerShip().getResourceBundleId() == null) {
			return "generic.vehicle.vehicledata.ownershipInvalid";
		}
*/						
		if (data.getYear() == null) {
			return "generic.vehicle.vehicledata.yearInvalid";
		}						
		
/*		if (data.getChasisNumber() == null) {
			return "generic.vehicle.vehicledata.chasisNumberInvalid";
		}
*/		
/*		if (data.getColour() == null || data.getColour().trim().length() == 0) {
			return "generic.vehicle.vehicledata.colourInvalid";
		}
*/		
/*		if (data.getPermissionExpiry() == null) {
			return "generic.vehicle.vehicledata.permissionExpiryDateInvalid";
		}
*/		
/*		if (data.getPurchaseDate() == null) {
			return "generic.vehicle.vehicledata.purchaseDateInvalid";
		}
*/				
/*		if (data.getPermissionNumber() == null) {
			return "generic.vehicle.vehicledata.permissionNumberInvalid";
		}
*/				
		//if (data.getMatricula() == null) {
		if (data.getMatricula() == null || data.getMatricula().trim().length() == 0) {
			return "generic.vehicle.vehicledata.registrationNumberInvalid";
		}
		if (data.getModel() == null || data.getModel().trim().length() == 0) {
			return "generic.vehicle.vehicledata.modelInvalid";
		}		
		if (data.getCountry() == null) {
			return "generic.vehicle.vehicledata.countryInvalid";
		} 
		
		int vehicleCorporation = data.getCorporation().getId();
		if(vehicleCorporation != CorporationData.FORESTALORIENTAL &&
				vehicleCorporation != CorporationData.FRAYLOG &&
				vehicleCorporation != CorporationData.WEYERHAEUSER &&
				vehicleCorporation != CorporationData.SAN_NICOLAS &&
				vehicleCorporation != CorporationData.SOCTMA &&
				vehicleCorporation != CorporationData.MONTES_DE_PLATA) {
	        Iterator divOrgsIterator = data.getDivOrgsIterator();
	        if(divOrgsIterator == null) {
	            return "generic.vehicle.vehicledata.divorgInvalid";
	        }else {
	    		String message = Validate.validateDivOrg(data.getDivOrgsIterator(),userSession);
	    		if(message != null) {
	    		    return message;
	    		}
	        }
			if(!Validate.validateDivGeo(data.getCountry(),userSession)) {
		        return "generic.vehicle.vehicledata.divgeoInvalid";
			}
		}
		return null;
	}
	
	/**
	 * @ejb.permission role-name = "administrator,cepascheduleactivitycreate,cdactivitycreate,vehiclechecklist,dpachecklist,jjreporter" 
	 * @ejb.interface-method view-type = "both"
	 * @param data
	 * @param userRequest
	 */
	public Integer createOrUpdateVehicleCheck(VehicleCheckData data, String comment, UserSessionData userRequest) throws Exception {

		try{
			Session session=HibernateUtil.currentSession();
			Integer id = data.getVehicleCheckId();
            
            VehicleCheck vehicleCheck= null;
            VehicleCheckLog log = null;
            System.out.println("DEBUG::::: "+data.getVehicleCheckId());
            if(data.getVehicleCheckId() != null) {
                Query query = session.createQuery("SELECT vc from VehicleCheck vc WHERE vc.vehicleCheckId = "+String.valueOf(data.getVehicleCheckId()));
                Object result = query.uniqueResult();
            
                vehicleCheck = (VehicleCheck)result; 
                vehicleCheck.updateData(data);
                session.update(vehicleCheck);
                
                log = new VehicleCheckLog(vehicleCheck, userRequest.getFilter().getLoginName(), Calendar.getInstance().getTime(), LogActions.UPDATE);
            }else   {
                vehicleCheck= new VehicleCheck(data);
    			id = (Integer)session.save(vehicleCheck);
    			vehicleCheck.setVehicleCheckId(id);
            	
                log = new VehicleCheckLog(vehicleCheck, userRequest.getFilter().getLoginName(), Calendar.getInstance().getTime(), LogActions.CREATE);
            }	
            session.save(log);
			
			//ActivityLog log = new ActivityLog(activity,userRequest.getFilter().getLoginName(),new Date(),LogActions.CREATE,comment);
			//session.save(log);			

			return vehicleCheck.getVehicleCheckId();
			
		} catch (Exception e) {
			Object[] parameters = {data,comment};
            handler.getException(e,"createOrUpdateVehicleCheck",userRequest,parameters);
        }	
		return null;
	}
	
	/**
	 * @ejb.permission role-name = "administrator,cepascheduleactivitycreate,cdactivitycreate,vehiclechecklist" 
	 * @ejb.interface-method view-type = "both"
	 * @param data
	 * @param userRequest
	 */
	public void uploadCheckListImages(List images,Integer checkId, UserSessionData userRequest) throws Exception {

		try{
			if(images!=null && images.size()>0){
			Session session=HibernateUtil.currentSession();
			
            VehicleCheck vehicleCheck= null;
            Query query = session.createQuery("SELECT vc from VehicleCheck vc WHERE vc.vehicleCheckId = "+String.valueOf(checkId));
            Object result = query.uniqueResult();
        
            vehicleCheck = (VehicleCheck)result; 
            Iterator iter=images.iterator();
            while(iter.hasNext()){
            	CheckListImageData data=(CheckListImageData) iter.next();
            	Query query2 = session.createQuery("SELECT p from Point p WHERE p.id = "+String.valueOf(data.getPointId()));
                Object result2 = query2.uniqueResult();
                String fileName=checkId+"_"+data.getPointId()+"_"+data.getFileName();
                
                File fileDelete=new File(AlertConstants.getTEMPImage(userRequest.getCorporationId())+fileName);
    	        if(fileDelete.exists()){
    	        	fileDelete.delete();
    	        }
    	        
                FileOutputStream fout;
    			
    			InputStream reportStream = data.getInputStream();
    			fout = new FileOutputStream(AlertConstants.getTEMPImage(userRequest.getCorporationId())+fileName);
    			
    			int c = 0;
    			while ( (c=reportStream.read()) != -1){
    				fout.write(c);
    			}			
    			fout.flush();
    			fout.close();	
                
                VehicleCheckListImage image=new VehicleCheckListImage((Point)result2,fileName,data.getText());
                vehicleCheck.getPointImageSet().add(image);
            }
            
            session.update(vehicleCheck);                
			
			//ActivityLog log = new ActivityLog(activity,userRequest.getFilter().getLoginName(),new Date(),LogActions.CREATE,comment);
			//session.save(log);			

			
			}
		} catch (Exception e) {
			Object[] parameters = {};
            handler.getException(e,"uploadDPACheckListImages",userRequest,parameters);
        }	
		
	}
	
	/**
	 * @ejb.permission role-name = "administrator,dpachecklist,cepascheduleactivitycreate,cdactivitycreate,vehiclechecklist" 
	 * @ejb.interface-method view-type = "both"
	 * @param data
	 * @param userRequest
	 */
	public void uploadCheckListImagesBase64(List images,Integer checkId, UserSessionData userRequest) throws Exception {

		try{
			if(images!=null && images.size()>0){
				Session session=HibernateUtil.currentSession();
				
	            VehicleCheck vehicleCheck= null;
	            Query query = session.createQuery("SELECT vc from VehicleCheck vc WHERE vc.vehicleCheckId = "+String.valueOf(checkId));
	            Object result = query.uniqueResult();
	        
	            vehicleCheck = (VehicleCheck)result; 
	            Iterator iter=images.iterator();
	            while(iter.hasNext()){
	            	CheckListImageData data=(CheckListImageData) iter.next();
	            	Query query2 = session.createQuery("SELECT p from Point p WHERE p.id = "+String.valueOf(data.getPointId()));
	                Object result2 = query2.uniqueResult();
	                String fileName="checklist_"+checkId+"_"+data.getPointId()+"_"+data.getFileName();
	                
	                File fileDelete=new File(AlertConstants.getTEMPImage(userRequest.getCorporationId())+fileName);
	    	        if(fileDelete.exists()){
	    	        	fileDelete.delete();
	    	        }
	    	        
	                FileOutputStream fout;
	    			
	    			InputStream reportStream = data.getInputStream();
	    			
	    			ImageIO.write(UtilBase64.decodeToImage(data.getImageBase64()), "png", new File(AlertConstants.getTEMPImage(userRequest.getCorporationId())+fileName));
	    	        
	                VehicleCheckListImage image=new VehicleCheckListImage((Point)result2,fileName,data.getText());
	                vehicleCheck.getPointImageSet().add(image);
	            }
	            
	            session.update(vehicleCheck);                
				
				//ActivityLog log = new ActivityLog(activity,userRequest.getFilter().getLoginName(),new Date(),LogActions.CREATE,comment);
				//session.save(log);			

			
			}
		} catch (Exception e) {
			Object[] parameters = {};
            handler.getException(e,"uploadDPACheckListImages",userRequest,parameters);
        }	
		
	}
	
	/**
	 * @ejb.permission role-name = "administrator,cepascheduleactivitycreate,cdactivitycreate,vehiclechecklist" 
	 * @ejb.interface-method view-type = "both"
	 * @param data
	 * @param userRequest
	 */
	public void deleteCheckListImages(List images,Integer checkId, UserSessionData userRequest) throws Exception {

		try{
			if(images!=null && images.size()>0){
			Session session=HibernateUtil.currentSession();
			
            VehicleCheck vehicleCheck= null;
            Query query = session.createQuery("SELECT vc from VehicleCheck vc WHERE vc.vehicleCheckId = "+String.valueOf(checkId));
            Object result = query.uniqueResult();
        
            vehicleCheck = (VehicleCheck)result; 
            
            Set<VehicleCheckListImage> pointImagesSet=new HashSet<VehicleCheckListImage>();
            
        	Iterator it=vehicleCheck.getPointImageSet().iterator();
        	while(it.hasNext()){
        		VehicleCheckListImage data=(VehicleCheckListImage)it.next();
        		boolean delete=false;
        		Iterator iter=images.iterator();
        		while(iter.hasNext() && !delete){
        			String file=(String) iter.next();
        			delete=file.equalsIgnoreCase(data.getImageName());
        		}
        		
        		if(!delete){
        			pointImagesSet.add(data);
        		}else{
        			File file=new File(AlertConstants.TEMP_DIRECTORY_IMAGE_DPA+data.getImageName());
        			if(file.exists()){
        				file.delete();
        			}
        		}
        		
        	}
        
            vehicleCheck.setPointImageSet(pointImagesSet);
            
            session.update(vehicleCheck);                
			
			//ActivityLog log = new ActivityLog(activity,userRequest.getFilter().getLoginName(),new Date(),LogActions.CREATE,comment);
			//session.save(log);			

			
			}
		} catch (Exception e) {
			Object[] parameters = {};
            handler.getException(e,"deleteCheckListImages",userRequest,parameters);
        }	
		
	}
    
    /**
     * @ejb.permission role-name = "administrator,cepascheduleactivitycreate,cdactivitycreate, vehiclechecklist" 
     * @ejb.interface-method view-type = "both"
     * @param data
     * @param userRequest
     */
    public Integer createOrUpdateTrailerCheck(TrailerCheckData data,Integer vehicleCheckId, String comment,UserSessionData userRequest) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            Integer id = data.getTrailerCheckId();
            TrailerCheck trailerCheck= new TrailerCheck(data);

            if(data.getTrailerCheckId() != null) {
                Query query = session.createQuery("SELECT tc FROM TrailerCheck tc WHERE tc.trailerCheckId = "+String.valueOf(data.getTrailerCheckId()));
                Object result = query.uniqueResult();
            
                trailerCheck = (TrailerCheck)result; 
                trailerCheck.updateData(data);
                session.update(trailerCheck);                
            }else {
                id = (Integer)session.save(trailerCheck);
                trailerCheck.setTrailerCheckId(id);
                
                VehicleTrailerCheck vt = new VehicleTrailerCheck(vehicleCheckId,id,session);
                session.save(vt);
                
                return id;
            }           
        } catch (Exception e) {
			Object[] parameters = {data,vehicleCheckId,comment};
            handler.getException(e,"createOrUpdateTrailerCheck",userRequest,parameters);
        }   
        return data.getTrailerCheckId();
    }
    
    /**
     * @ejb.permission role-name = "administrator,cepascheduleactivitycreate,cdactivitycreate, vehiclechecklist" 
     * @ejb.interface-method view-type = "both"
     * @param data
     * @param userRequest
     */
    public Integer createOrUpdateVehiclePursuitRoute(VehiclePursuitRouteData data, 
    		String comment,UserSessionData userRequest) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            Integer id = data.getPursuitRouteId();
            VehiclePursuitRoute trailerCheck= new VehiclePursuitRoute(data);

            if(id != null) {          
                trailerCheck = (VehiclePursuitRoute)session.load(VehiclePursuitRoute.class,id);               
                trailerCheck.updateData(data);
                session.update(trailerCheck);  
                
            }else {
                id = (Integer)session.save(trailerCheck);
                trailerCheck.setPursuitRouteId(id);
                return id;
            }           
        } catch (Exception e) {
			Object[] parameters = {data,comment};
            handler.getException(e,"createOrUpdateVehiclePursuitRoute",userRequest,parameters);
        }   
        return data.getPursuitRouteId();
    }
	
	/**
	 * @ejb.permission role-name = "administrator,cepascheduleactivitycreate,cdactivitycreate,vehiclechecklist" 
	 * @ejb.interface-method view-type = "both"
	 * @param data
	 * @param userRequest
	 */
	public void deleteVehicleCheck(Integer id, String comment,UserSessionData userRequest) throws Exception {

		try{
			Session session=HibernateUtil.currentSession();
			
            VehicleCheck v = null;
            Query q = session.createQuery("SELECT vt FROM VehicleTrailerCheck vt WHERE vt.pk.vehicle = "+String.valueOf(id));
            Iterator it = q.list().iterator();
            while(it.hasNext()) {
                VehicleTrailerCheck vt = (VehicleTrailerCheck)it.next();
                TrailerCheck t = vt.getPk().getTrailer();
                if(v == null) {
                    v = vt.getPk().getVehicle();
                }
                session.delete(vt);
                t.getItemValueSet().removeAll(t.getItemValueSet());
                session.delete(t);
            }
            
            if(v == null) {
                v = (VehicleCheck) session.load(VehicleCheck.class, id);
                VehicleCheckLog log = new VehicleCheckLog(v,userRequest.getFilter().getLoginName(),Calendar.getInstance().getTime(),LogActions.DELETE);
                session.save(log);
            }
            
            v.getItemValueSet().removeAll(v.getItemValueSet());
            session.delete(v); 
			
		} catch (Exception e) {
			Object[] parameters = {id,comment};
            handler.getException(e,"deleteVehicleCheck",userRequest,parameters);
        }	

	}
	
	/**
	 * @ejb.permission role-name = "administrator,cepascheduleactivitycreate,cdactivitycreate,vehiclechecklist" 
	 * @ejb.interface-method view-type = "both"
	 * @param data
	 * @param userRequest
	 */
	public void deleteVehiclePursuitRoute(Integer id, String comment,
			UserSessionData userRequest) throws Exception {

		try{
			Session session=HibernateUtil.currentSession();
			
			VehiclePursuitRoute vehicleCheck = (VehiclePursuitRoute) 
				session.load(VehiclePursuitRoute.class, id);
			
			vehicleCheck.getItemValueSet().removeAll(vehicleCheck.getItemValueSet());

			session.delete(vehicleCheck); 
			
		} catch (Exception e) {
			Object[] parameters = {id,comment};
            handler.getException(e,"deleteVehiclePursuitRoute",userRequest,parameters);
        }	

	}
 
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate,dpachecklist"
     */
    public void associateTrailer(Integer vehicleId, Integer trailerId, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            VehicleTrailer vehicleTrailer = new VehicleTrailer(vehicleId,trailerId,session);
            session.save(vehicleTrailer);
        } catch (Exception e) {
			Object[] parameters = {vehicleId,trailerId};
            handler.getException(e,"associateTrailer",userSessionData,parameters);
        }   
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate,dpachecklist"
     */
    public void desassociateTrailer(Integer vehicleId, Integer trailerId, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            Query q = session.createQuery("DELETE FROM VehicleTrailer vt WHERE vt.pk.vehicle = "+String.valueOf(vehicleId)+" AND vt.pk.trailer = "+String.valueOf(trailerId));
            q.executeUpdate();
        } catch (Exception e) {
			Object[] parameters = {vehicleId,trailerId};
            handler.getException(e,"desassociateTrailer",userSessionData,parameters);
        }   
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate"
     */
    public void desassociateTrailers(Integer vehicleId, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            Query q = session.createQuery("DELETE FROM VehicleTrailer vt WHERE vt.pk.vehicle = "+String.valueOf(vehicleId));
            q.executeUpdate();
        } catch (Exception e) {
			Object[] parameters = {vehicleId};
            handler.getException(e,"desassociateTrailer",userSessionData,parameters);
        }   
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate,dpachecklist"
     */
	public Integer createTrailer(TrailerData trailerData, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            Trailer trailer = new Trailer(trailerData,userSessionData);
            return (Integer)session.save(trailer); 
        } catch (Exception e) {
			Object[] parameters = {trailerData};
            handler.getException(e,"createTrailer",userSessionData,parameters);
        }   
        return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate,dpachecklist"
     */
    public void updateTrailer(TrailerData trailerData, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            Trailer trailer = (Trailer)session.load(Trailer.class,trailerData.getId());
            trailer.setData(trailerData);
            session.update(trailer); 
        } catch (Exception e) {
			Object[] parameters = {trailerData};
            handler.getException(e,"updateTrailer",userSessionData,parameters);
        }   
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate"
     */
    public void trailerExclude(Integer trailerId, Date exclusionDate, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            Trailer trailer = (Trailer) session.load(Trailer.class, trailerId);
            trailer.setExclusionDate(exclusionDate);
            session.update(trailer);
        } catch (Exception e) {
			Object[] parameters = {trailerId,exclusionDate};
            handler.getException(e,"trailerExclude",userSessionData,parameters);
        }   
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate"
     */
    public Integer deleteTrailer(Integer trailerId, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            Trailer trailer = (Trailer) session.load(Trailer.class, trailerId);
            session.delete(trailer);
        } catch (Exception e) {
			Object[] parameters = {trailerId};
            handler.getException(e,"deleteTrailer",userSessionData,parameters);
        }   
        return null;
    }


    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate"
     */
    public void trailerInclude(Integer trailerId, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            Trailer trailer = (Trailer) session.load(Trailer.class, trailerId);
            trailer.setExclusionDate(null);
            session.update(trailer);
        } catch (Exception e) {
			Object[] parameters = {trailerId};
            handler.getException(e,"trailerInclude",userSessionData,parameters);
        }   
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     */
    public void createUnconformity(Integer vehicleId, UnconformityData data, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            Unconformity unconformity = new Unconformity(vehicleId,data,session);
            session.save(unconformity);
        }catch(Exception e) {
			Object[] parameters = {vehicleId,data};
            handler.getException(e,"createUnconformity",userSessionData,parameters);
        }
    }
    
  

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     */
    public void deleteUnconformity(Integer vehicleId, String date, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            Unconformity unconformity = (Unconformity)session.load(Unconformity.class, new UnconformityPK(vehicleId,date));
            session.delete(unconformity);
        }catch(Exception e) {
			Object[] parameters = {vehicleId,date};
            handler.getException(e,"deleteUnconformity",userSessionData,parameters);
        }
    }
    
    /**
     * Contratos de Alquiler de los Vehiculos (Allergan)
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     */
    public void createVehicleContract(Integer vehicleId, VehicleContractData data, UserSessionData userSessionData) throws Exception{
    	try{
            Session session = HibernateUtil.currentSession();
            VehicleContract contract = new VehicleContract(vehicleId,data,session);
            session.saveOrUpdate(contract);
        }catch(Exception e) {
			Object[] parameters = {vehicleId,data};
            handler.getException(e,"createVehicleContract",userSessionData,parameters);
        }
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     */
    public void deleteVehicleContract(Integer vehicleId, String date, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            VehicleContract contract = (VehicleContract)session.load(VehicleContract.class, new VehicleContractPK(vehicleId,date));
            session.delete(contract);
        }catch(Exception e) {
			Object[] parameters = {vehicleId,date};
            handler.getException(e,"deleteVehicleContract",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     */
    public void deleteSimpleVehicleCheck(Integer checkId, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            
            VehicleCheck vehicleCheck = (VehicleCheck)session.load(VehicleCheck.class, checkId);
            VehicleCheckLog log = new VehicleCheckLog(vehicleCheck, userSessionData.getFilter().getLoginName(), Calendar.getInstance().getTime(), LogActions.DELETE);

            session.save(log);
            session.delete(vehicleCheck);
        }catch(Exception e) {
			Object[] parameters = {checkId};
            handler.getException(e,"deleteUnconformity",userSessionData,parameters);
        }
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     */
    public Integer createVehicleMaintenance(VehicleMaintenanceData data, UserSessionData userSessionData) throws Exception {
        Integer id = null;
    	try{
            Session session=HibernateUtil.currentSession();
            VehicleMaintenance vehicleMaintenance = new VehicleMaintenance();
            vehicleMaintenance.setData(data,session);
            id = (Integer)session.save(vehicleMaintenance);
        }catch(Exception e) {
			Object[] parameters = {data};
            handler.getException(e,"createVehicleMaintenance",userSessionData,parameters);
        }
    	return id;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     */
    public void updateVehicleMaintenance(VehicleMaintenanceData data, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            VehicleMaintenance vehicleMaintenance = (VehicleMaintenance)session.load(VehicleMaintenance.class, data.getId());
            vehicleMaintenance.setData(data,session);
            session.update(vehicleMaintenance);
        }catch(Exception e) {
			Object[] parameters = {data};
            handler.getException(e,"updateVehicleMaintenance",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     */
    public PointMove deleteVehicleMaintenance(Integer vehicleMaintenanceId, UserSessionData userSessionData) throws Exception {
    	try{
            Session session=HibernateUtil.currentSession();
            VehicleMaintenance vehicleMaintenance = (VehicleMaintenance)session.load(VehicleMaintenance.class, vehicleMaintenanceId);
            String matricula = vehicleMaintenance.getVehicle().getMatricula();
            Date date = vehicleMaintenance.getDate();
            Integer driver = vehicleMaintenance.getDriver().getId();
            session.delete(vehicleMaintenance);
            
            if(userSessionData.isCorporation(CorporationData.BAYER_MX) && driver != null) {
            	Query q = session.createSQLQuery("SELECT id FROM pointaccount WHERE type = 1 AND reference = "+driver).addScalar("id", Hibernate.INTEGER);
            	Iterator it = q.list().iterator();
            	if(it.hasNext()) {
            		Integer account = (Integer)it.next();
                	q = session.createQuery("SELECT pm FROM PointMove pm WHERE pm.account = "+account+" AND date = "+Util.formatSQL(date)+" AND definition = 60864 AND reference = '"+matricula+"'");
                	it = q.iterate();
                	if(it.hasNext())
                		return (PointMove)it.next();
            	}
            }
        }catch(Exception e) {
			Object[] parameters = {vehicleMaintenanceId};
            handler.getException(e,"deleteVehicleMaintenance",userSessionData,parameters);
        }
    	return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     */
    public void createComplaint(ComplaintData data, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            Complaint complaint = new Complaint(data,session);
            session.save(complaint);
        }catch(Exception e) {
			Object[] parameters = {data};
            handler.getException(e,"createComplaint",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     */
    public void updateComplaint(ComplaintData data, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            Complaint complaint = (Complaint)session.load(Complaint.class, data.getComplaintId());
            complaint.setData(data,session);
            session.update(complaint);
        }catch(Exception e) {
			Object[] parameters = {data};
            handler.getException(e,"updateComplaint",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     */
    public void deleteComplaint(Integer complaintId, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            Complaint complaint = (Complaint)session.load(Complaint.class, complaintId);
            session.delete(complaint);
        }catch(Exception e) {
			Object[] parameters = {complaintId};
            handler.getException(e,"deleteComplaint",userSessionData,parameters);
        }
    }

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,driverupdate,vehicleupdate"
	 * @param data
	 * @param userRequest
	 */        			
    public void updateDivisions(Integer vehicleId, Integer[] divOrgs, UserSessionData userSessionData) throws Exception {
        try{
            Session session = HibernateUtil.currentSession();
            Vehicle vehicle = (Vehicle)session.load(Vehicle.class, vehicleId);
            vehicle.updateDivisions(divOrgs,session);
            session.update(vehicle);
        }catch(Exception e) {
			Object[] parameters = {vehicleId,divOrgs};
            handler.getException(e,"updateDivisions",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,vehicleupdate"
     */
    public void updateVehicleContract(VehicleContractData contract, int action, UserSessionData userSessionData) throws Exception {
        try{
            Session session = HibernateUtil.currentSession();
        	VehicleContract vehicleContract = (VehicleContract)session.load(VehicleContract.class, new VehicleContractPK(contract.getId(), contract.getDate(), session));
        	if(vehicleContract != null) {
	        	if(action == 1) {
	        		vehicleContract.setSaved(true);
	        		session.update(vehicleContract);
	        	}else {
	        		session.delete(vehicleContract);
	        	}
        	}
        }catch(Exception e) {
			Object[] parameters = {contract,action};
            handler.getException(e,"updateVehicleContract",userSessionData, parameters);
        }
    }
    
    /**
	 * @ejb.interface-method view-type = "remote"
	 * @ejb.permission role-name = "administrator,systemaccess,vehicleview"
	 * @param data
	 * @throws Exception 
	 */
	public void createOrUpdateDPAGoals(DPAGoalData data,UserSessionData userSession) throws Exception {
		
		try{
			Session session=HibernateUtil.currentSession();
			
			if(data.getId()==null){
				data.setId((Integer)session.save(new DPAGoal(data)));	
			}else{
				session.update(new DPAGoal(data));
			}
			
			
    	}catch (Exception e) {
			Object[] p = {data};
			handler.getException(e,"createOrUpdateDPAGoals",userSession, p);
    	}
	
	}
	
	 /**
		 * @ejb.interface-method view-type = "remote"
		 * @ejb.permission role-name = "administrator,systemaccess,vehicleview"
		 * @param data
		 * @throws Exception 
		 */
		public void createOrUpdateDPAVehicleActive(DPAVehicleActiveData data,UserSessionData userSession) throws Exception {
			
			try{
				Session session=HibernateUtil.currentSession();
				
				if(data.getId()==null){
					data.setId((Integer)session.save(new DPAVehicleActive(data)));	
				}else{
					session.update(new DPAVehicleActive(data));
				}
				
				
	    	}catch (Exception e) {
				Object[] p = {data};
				handler.getException(e,"createOrUpdateDPAVehicleActive",userSession, p);
	    	}
		
		}

		/**
		 * @ejb.interface-method
		 * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate,dpachecklist"
		 * @param data
		 * @param userRequest
		 */
		public void vehicleUpdateContractor(Integer vehicleId, Integer contractor, UserSessionData userSessionData) throws Exception {
		    try{
		        Session session=HibernateUtil.currentSession();
				Vehicle vehicle = (Vehicle)session.load(Vehicle.class,vehicleId);
				
				vehicle.setDivOrg1((DivOrgItemLevel1)session.load(DivOrgItemLevel1.class, contractor));
	    		session.update(vehicle);
	    		
				VehicleLog log = new VehicleLog(vehicle,userSessionData.getFilter().getLoginName(),
						new Date(),LogActions.UPDATE,"UPDATE CONTRACTOR");
				session.save(log);		

			} catch (Exception e) {
				Object[] parameters = {vehicleId, contractor};
			    handler.getException(e,"vehicleUpdateContractor",userSessionData,parameters);
			}
		}
		
		/**
		 * @ejb.interface-method
		 * @ejb.permission role-name = "administrator,drivercreate,dpachecklist"
		 * @param data Driver that will be created 
		 * @param userRequest User logged
		 */
		public void saveVehicleLinks(Integer id, Integer fosa, Integer mdp, Integer lumin, UserSessionData userSessionData) throws Exception {
			try{
	            Session session=HibernateUtil.currentSession();			
	            VehicleTransporterLink link = new VehicleTransporterLink();
	            link.setId(id);
	            link.setFosa(fosa);
	            link.setMdp(mdp);
	            link.setLumin(lumin);
	            link.setCorporation(userSessionData.getCorporationId());
	            session.save(link);
	        }catch(Exception e) {
				Object[] parameters = {id,fosa,mdp};
				handler.getException(e,"saveVehicleLinks",userSessionData,parameters);
	        }
	    }
		
		/**
		 * @ejb.interface-method
		 * @ejb.permission role-name = "administrator,drivercreate,vehicleupdate,dpachecklist"
		 * @param data Driver that will be created 
		 * @param userRequest User logged
		 */
		public void saveVehicleLinks(Integer id, Integer fosa, Integer mdp, Integer lumin, Integer corporation, UserSessionData userSessionData) throws Exception {
			try{
	            Session session=HibernateUtil.currentSession();			
	            VehicleTransporterLink link = new VehicleTransporterLink();
	            link.setId(id);
	            link.setFosa(fosa);
	            link.setMdp(mdp);
	            link.setLumin(lumin);
	            link.setCorporation(corporation);
	            session.save(link);
	        }catch(Exception e) {
				Object[] parameters = {id,fosa,mdp};
				handler.getException(e,"saveVehicleLinks",userSessionData,parameters);
	        }
	    }

		/**
		 * @ejb.interface-method
		 * @ejb.permission role-name = "administrator,drivercreate,vehicleupdate,dpachecklist"
		 * @param data Driver that will be created 
		 * @param userRequest User logged
		 */
		public void saveVehicleLinks(Integer existId, Integer newId, Integer corporation, UserSessionData userSessionData) throws Exception {
			try{
	            Session session=HibernateUtil.currentSession();			
	            VehicleTransporterLink link = new VehicleTransporterLink();
	            link.setId(newId);
	            switch(userSessionData.getCorporationId()) {
		            case CorporationData.FORESTALORIENTAL:
		            	link.setFosa(existId);
		            	break;
		            case CorporationData.WEYERHAEUSER:
		            	link.setLumin(existId);
		            	break;
		            case CorporationData.MONTES_DE_PLATA:
		            	link.setMdp(existId);
		            	break;
	            }
	            link.setCorporation(corporation);
	            session.save(link);
			}catch(Exception e) {
				Object[] parameters = {existId, newId, corporation};
				handler.getException(e,"saveVehicleLinks",userSessionData,parameters);
	        }
	    }

		/**
		 * @ejb.interface-method
	     * @ejb.permission role-name = "administrator,vehiclecreate,drivercreatevehicle,vehiclechecklist,dpachecklist"
		 * @param data
		 * @throws Exception
		 */	
		public void saveCheckListLinks(Integer id, Integer fosa, Integer mdp, UserSessionData userSessionData) throws Exception {
			try{
	            Session session=HibernateUtil.currentSession();			
	            CheckListTransporterLink link = new CheckListTransporterLink();
	            link.setId(id);
	            link.setFosa(fosa);
	            link.setMdp(mdp);
	            link.setCorporation(userSessionData.getCorporationId());
	            session.save(link);
	        }catch(Exception e) {
				Object[] parameters = {id,fosa,mdp};
				handler.getException(e,"saveCheckListLinks",userSessionData,parameters);
	        }
	    }

		/**
		 * @ejb.interface-method
	     * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate,dpachecklist"
		 * @param data
		 * @throws Exception 
		 */
		public Integer[] updateVehicleTransporterLinks(Integer[] links, UserSessionData userSessionData) throws Exception {
			try{
	            Session session=HibernateUtil.currentSession();			
				VehicleTransporterLink v = (VehicleTransporterLink) session.load(VehicleTransporterLink.class, links[0]);
				v.setFosa(links[1]);			
				v.setMdp(links[2]);
				session.save(v);
	        }catch(Exception e) {
				Object[] parameters = {links};
				handler.getException(e,"updateVehicleTransporterLinks",userSessionData,parameters);
	        }
			return null;
		}
		/**
		 * @ejb.interface-method
		 * @ejb.permission role-name = "administrator,vehicleupdate,drivervehicleupdate,dpachecklist"
		 * @param data
		 * @param userRequest
		 */
		public void vehicleSimpleUpdate(VehicleData data, UserSessionData userRequest, String comment, int action) throws Exception {
		    try{
		        Session session=HibernateUtil.currentSession();
		        validate(data,userRequest);

				Vehicle vehicle = (Vehicle)session.load(Vehicle.class,data.getId());
				vehicle.updateSimpleData(data, session);
	            if(userRequest.isForest()) {
	                vehicle.setForestalData(data);
	            }
	            session.update(vehicle);    		
			} catch (Exception e) {
				Object[] parameters = {data,comment,action};
			    handler.getException(e,"vehicleSimpleUpdate",userRequest,parameters);
			}
		}
}