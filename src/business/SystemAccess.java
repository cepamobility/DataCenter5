package business;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import business.activity.ActivityABMFacade;
import business.activity.ActivityABMFacadeHome;
import business.activity.ActivityABMFacadeUtil;
import business.activity.ActivityReportFacade;
import business.activity.ActivityReportFacadeHome;
import business.activity.ActivityReportFacadeUtil;
import business.administration.RoleReportFacade;
import business.administration.RoleReportFacadeHome;
import business.administration.RoleReportFacadeUtil;
import business.administration.corporation.CorporationABMFacade;
import business.administration.corporation.CorporationABMFacadeHome;
import business.administration.corporation.CorporationABMFacadeUtil;
import business.administration.corporation.CorporationReportFacade;
import business.administration.corporation.CorporationReportFacadeHome;
import business.administration.corporation.CorporationReportFacadeUtil;
import business.administration.group.GroupABMFacade;
import business.administration.group.GroupABMFacadeHome;
import business.administration.group.GroupABMFacadeUtil;
import business.administration.group.GroupReportFacade;
import business.administration.group.GroupReportFacadeHome;
import business.administration.group.GroupReportFacadeUtil;
import business.administration.user.UserABMFacade;
import business.administration.user.UserABMFacadeHome;
import business.administration.user.UserABMFacadeUtil;
import business.administration.user.UserReportFacade;
import business.administration.user.UserReportFacadeHome;
import business.administration.user.UserReportFacadeUtil;
import business.alert.AlertABMFacade;
import business.alert.AlertABMFacadeHome;
import business.alert.AlertABMFacadeUtil;
import business.alert.AlertReportFacade;
import business.alert.AlertReportFacadeHome;
import business.alert.AlertReportFacadeUtil;
import business.cenapra.CENAPRAABMFacade;
import business.cenapra.CENAPRAABMFacadeHome;
import business.cenapra.CENAPRAABMFacadeUtil;
import business.cenapra.CENAPRAReportFacade;
import business.cenapra.CENAPRAReportFacadeHome;
import business.cenapra.CENAPRAReportFacadeUtil;
import business.datatype.DataTypeReportFacade;
import business.datatype.DataTypeReportFacadeHome;
import business.datatype.DataTypeReportFacadeUtil;
import business.driver.DriverABMFacade;
import business.driver.DriverABMFacadeHome;
import business.driver.DriverABMFacadeUtil;
import business.driver.DriverReportFacade;
import business.driver.DriverReportFacadeHome;
import business.driver.DriverReportFacadeUtil;
import business.event.EventABMFacade;
import business.event.EventABMFacadeHome;
import business.event.EventABMFacadeUtil;
import business.event.EventReportFacade;
import business.event.EventReportFacadeHome;
import business.event.EventReportFacadeUtil;
import business.external.ExternalFacade;
import business.external.ExternalFacadeHome;
import business.external.ExternalFacadeUtil;
import business.fleet.FleetABMFacade;
import business.fleet.FleetABMFacadeHome;
import business.fleet.FleetABMFacadeUtil;
import business.fleet.FleetReportFacade;
import business.fleet.FleetReportFacadeHome;
import business.fleet.FleetReportFacadeUtil;
import business.language.LanguageReportFacade;
import business.language.LanguageReportFacadeHome;
import business.language.LanguageReportFacadeUtil;
import business.report.BIReportFacade;
import business.report.BIReportFacadeHome;
import business.report.BIReportFacadeUtil;
import business.util.ExceptionHandler;
import business.vehicle.VehicleABMFacade;
import business.vehicle.VehicleABMFacadeHome;
import business.vehicle.VehicleABMFacadeUtil;
import business.vehicle.VehicleReportFacade;
import business.vehicle.VehicleReportFacadeHome;
import business.vehicle.VehicleReportFacadeUtil;
import communication.administration.RoleData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgAttribute;
import communication.administration.corporation.tables.GeoLabelAttributes;
import communication.administration.corporation.tables.LabelItemAttribute;
import communication.administration.corporation.tables.MobilTrackEventData;
import communication.administration.user.UserSessionData;
import communication.administration.user.tables.UserData;
import communication.exception.CommunicationProblemException;
import communication.exception.DCSecurityException;
import communication.exception.DeslogedException;
import communication.fleet.ContractorPointData;
import communication.language.Text;
import communication.language.TextContainer;
import communication.util.Filter;
import persistence.log.LoggerFormatting;


public class SystemAccess implements Reload { 

	private static final long serialVersionUID = 1L;
	/**
	 * Logger for this class
	 */
	private Logger logger = Logger.getLogger(SystemAccess.class);
	private LoggerFormatting logFormatting=new LoggerFormatting();
	
	private ExceptionHandler handler = new ExceptionHandler(SystemAccess.class);
    
	private UserSessionData userSessionData;
	
	//private DataTypeAccess dataTypeAccess;
	
	private Text text;
	private boolean cepaPoints = false;
	
    public SystemAccess(String user) throws Exception {
        userSessionData = new UserSessionData();
        if(!user.equals("resetpassword") && getUserReportFacade().isFirstAccess(user)) {
            Hashtable table = new Hashtable();
            table.put("firstaccess","firstaccess");
            table.put("systemaccess","systemaccess");
            userSessionData.setPermissions(table);
            userSessionData.setRole(new RoleData("firstAccess",false,null));
            userSessionData.setFilter(new Filter(user,new Integer(CorporationData.JJ),new Integer(CorporationData.DATACENTER)));
            SystemAccessController.getInstance().suscribe (this,user);          
        }else {
        	try {
        		loadUser(user);
        		loadText();
        		SystemAccessController.getInstance().suscribe (this,user);    		
        	}catch(Exception e) {
        		Object[] parameters = new Object[1];
        		parameters[0] = user;
        	    handler.handleException("SystemAccess()",e, userSessionData, parameters);
            }
        }
/*        
        Calendar c1 = Calendar.getInstance();

		MobilTrackEventData data=new MobilTrackEventData();
		data.setdEvento("Exceso velocidad en zona (Tramo Las Palmas_30Km/h) [Tramo Las Palmas]");
		data.setEmpresa("SOCTMA SRL");
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		data.setFechaHora(format.parse("23/09/2024 10:46:29"));
		data.setMatricula("OTP3714");
		data.setVelGps(100);
		
		getCorporationABMFacade().saveMobilTrackEvent(data, userSessionData);

		data=new MobilTrackEventData();
		data.setdEvento("Exceso velocidad en zona (Tramo Las Palmas_30Km/h) [Tramo Las Palmas]");
		data.setEmpresa("SILVERA");
		data.setFechaHora(format.parse("13/06/2023 20:46:29"));
		data.setMatricula("RTP2937");
		data.setVelGps(100);
		
		getCorporationABMFacade().saveMobilTrackEvent(data, userSessionData);
		

		data=new MobilTrackEventData();
		data.setdEvento("Exceso velocidad 1");
		data.setEmpresa("SILVERA");
		data.setFechaHora(format.parse("13/06/2023 20:46:29"));
		data.setMatricula("RTP2937");
		data.setVelGps(100);
		
		getCorporationABMFacade().saveMobilTrackEvent(data, userSessionData);

		data=new MobilTrackEventData();
		data.setdEvento("Exceso velocidad 2");
		data.setEmpresa("SILVERA");
		data.setFechaHora(format.parse("13/06/2023 20:46:29"));
		data.setMatricula("RTP2937");
		data.setVelGps(100);
		
		getCorporationABMFacade().saveMobilTrackEvent(data, userSessionData);

		data=new MobilTrackEventData();
		data.setdEvento("Exceso velocidad 3");
		data.setEmpresa("SILVERA");
		data.setFechaHora(format.parse("13/06/2023 20:46:29"));
		data.setMatricula("RTP2937");
		data.setVelGps(100);
		
		getCorporationABMFacade().saveMobilTrackEvent(data, userSessionData);

		data=new MobilTrackEventData();
		data.setdEvento("Conduccion sin cinturon de seguridad colocado");
		data.setEmpresa("SILVERA");
		data.setFechaHora(format.parse("13/06/2023 20:46:29"));
		data.setMatricula("RTP2937");
		data.setVelGps(100);
		
		getCorporationABMFacade().saveMobilTrackEvent(data, userSessionData);

		data=new MobilTrackEventData();
		data.setdEvento("Alerta por persistencia en no respetar distancia de seguridad");
		data.setEmpresa("SILVERA");
		data.setFechaHora(format.parse("13/06/2023 20:46:29"));
		data.setMatricula("RTP2937");
		data.setVelGps(100);
		
		getCorporationABMFacade().saveMobilTrackEvent(data, userSessionData);

        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.YEAR, 2022);
        c2.set(Calendar.MONTH, 2);
        c2.set(Calendar.DATE, 30);
        
        BIReportFacade bi = getBIReportFacade();
        long time = System.currentTimeMillis();
        System.out.println("Time 1 : "+time);
        
        Collection col1 = bi.findContractorsPointData(c1.getTime(), c2.getTime(), 0, userSessionData);
        long time2 = System.currentTimeMillis();
        time = time2 - time;
        System.out.println("Time 2 : "+time);

        Collection col2 = bi.findContractorsPointDataOld(c1.getTime(), c2.getTime(), 0, userSessionData);
        time = System.currentTimeMillis() - time2;
        System.out.println("Time 3 : "+time);
        
        System.out.println("------------------------------");
        Iterator it1 = col1.iterator();
        while(it1.hasNext())
            System.out.println(((ContractorPointData)it1.next()).toString());
        System.out.println("------------------------------");
        Iterator it2 = col2.iterator();
        while(it2.hasNext())
            System.out.println(((ContractorPointData)it2.next()).toString());
        System.out.println("------------------------------");
        if(user.equals("jrodriguez")) {
	        VehicleABMFacade vehicleF = getVehicleABMFacade();
	        if(userSessionData.getCorporationId() == 50) {
	        	//vehicleF.procesarCheckList(346577, text, userSessionData);
	        }
	        if(userSessionData.getCorporationId() == 9) {
	        	vehicleF.procesarCheckList(342391, text, userSessionData);
	        	vehicleF.procesarCheckList(341382, text, userSessionData);
	        	vehicleF.procesarCheckList(343267, text, userSessionData);
	        /*	vehicleF.procesarCheckList(345875, text, userSessionData);
	        	vehicleF.procesarCheckList(345877, text, userSessionData);
	        	vehicleF.procesarCheckList(345879, text, userSessionData);
	        	vehicleF.procesarCheckList(345880, text, userSessionData);
	        	vehicleF.procesarCheckList(345881, text, userSessionData);
	        	vehicleF.procesarCheckList(345882, text, userSessionData);
	        	vehicleF.procesarCheckList(345883, text, userSessionData);
	        	vehicleF.procesarCheckList(345884, text, userSessionData);
	        	vehicleF.procesarCheckList(345886, text, userSessionData);
	        	vehicleF.procesarCheckList(345984, text, userSessionData);
	        }
        }*/
        
    }
	
	public void unSuscribe() throws Exception{};
	
	public Object getReloadKey(){
		Object returnObject = userSessionData.getFilter().getLoginName();
		return returnObject;
	}
	
	public void loadUser(String login) throws Exception {
	
		userSessionData = new UserSessionData();
        long time = System.currentTimeMillis();

        UserReportFacade userReportFacade = this.getUserReportFacade();
		UserData data = userReportFacade.findUserByPrimaryKey(login,userSessionData);

		CorporationReportFacade crpReportFacade=this.getCorporationReportFacade();
		CorporationData crpData=crpReportFacade.findCorporationByPrimaryKey(data.getCorporationId(),userSessionData);
		
		cepaPoints  = crpReportFacade.isCepaPoints(data.getCorporationId(),userSessionData);
		
        int divOrgCount=crpData.getDivOrgDataSet().size();
        if(crpData.getId().intValue() == CorporationData.BAYER_MX) {
        	if(data.getRole().getName().equals("Gerente")) {
        		data.setGroupLines(crpData.getAllGroupLines(divOrgCount, data.getFirstName()+" "+data.getLastName()));
        	}else {
        		data.setGroupLines(crpData.getAllGroupLines(divOrgCount));
        	}
		}

        DivOrgAttribute[] divOrgs = crpData.getDivOrgs();

        LabelItemAttribute[][] divOrgLabels = crpData.getDivOrgLabels();
        GeoLabelAttributes[] divGeoLabels = crpData.getGeoLabels();
        Filter filter = new Filter(login,data.getCorporationId(),divOrgCount,divOrgs,divOrgLabels,divGeoLabels,data.getGroupLines(),data.getFirstName(),data.getLastName());
        filter.setCorporationType(crpData.getType());
        filter.setCorporationName(crpData.getName());
        filter.setCorporationGroupLines(crpData.getAllGroupLines(divOrgCount));
        System.out.println("TIEMPO 4 --> "+String.valueOf(System.currentTimeMillis() - time));

        userSessionData.setFilter(filter);
		userSessionData.setLenguage(data.getLanguage());
        userSessionData.setRole(data.getRole());
        userSessionData.setPermissions(data.getPermissions());
        userSessionData.setDriver(data.getDriverData());
        userSessionData.getFilter().setEncriptPassword(data.getPassword());
        
        System.out.println("TIEMPO 5 --> "+String.valueOf(System.currentTimeMillis() - time));

        userSessionData.setMultipleCorporation(userReportFacade.isMultipleCorporation(login));
	}	
	
    public void reloadFilter(int corporationId) throws Exception {
        UserReportFacade userReportFacade=this.getUserReportFacade();
        UserData data= userReportFacade.findUserByPrimaryKey(userSessionData.getFilter().getLoginName(),userSessionData);
        CorporationReportFacade crpReportFacade=this.getCorporationReportFacade();
        CorporationData crpData=crpReportFacade.findCorporationByPrimaryKey(new Integer(corporationId),userSessionData);
        int divOrgCount=crpData.getDivOrgDataSet().size();
        DivOrgAttribute[] divOrgs = crpData.getDivOrgs();
        LabelItemAttribute[][] divOrgLabels = crpData.getDivOrgLabels();
        GeoLabelAttributes[] divGeoLabels = crpData.getGeoLabels();
        Filter filter = new Filter(userSessionData.getFilter().getLoginName(),new Integer(corporationId),divOrgCount,divOrgs,divOrgLabels,divGeoLabels,data.getGroupLines(),userSessionData.getFilter().getFirstName(),userSessionData.getFilter().getLastName());
        filter.setCorporationType(crpData.getType());
        filter.setCorporationName(crpData.getName());
        filter.setCorporationGroupLines(crpData.getAllGroupLines(divOrgCount));
        userSessionData.setFilter(filter);
    }   
	
	public void loadFilter(Integer corporationid) throws Exception {
	   if(userSessionData.getFilter().getCorporationId().equals(corporationid)){
	   		loadUser(userSessionData.getFilter().getLoginName());
	   }
	}

	public void loadText() throws Exception {
	    text = new Text();
		text.setContents(TextContainer.getInstance().getTexts(userSessionData.getLenguage(),userSessionData.getFilter().getCorporationId(),this,userSessionData));			
	}
	
	/**
	 * Reload las traducciones
	 * @throws Exception
	 */
	public void reloadText() throws Exception {
	
		text.setContents(TextContainer.getInstance().
				reloadTexts(userSessionData.getLenguage(),
				userSessionData.getFilter().getCorporationId(),this,userSessionData));
				
	}	
	
	public void updateLanguage(Text text) throws Exception {  	
	    text = new Text();
		text.setContents(TextContainer.getInstance().getTexts(userSessionData.getLenguage(),userSessionData.getFilter().getCorporationId(),this,userSessionData));			
	}

	public void updatePermissions() 	
	throws Exception {
		UserReportFacade userReportFacade=this.getUserReportFacade();
		UserData data= userReportFacade.findUserByPrimaryKey(userSessionData.getFilter().getLoginName(),userSessionData);
        userSessionData.setPermissions(data.getPermissions());
        userSessionData.setRole(data.getRole());
	}
	   
    public void updatePermissions(UserData data) throws Exception {
        if (data != null){
        	userSessionData.setPermissions(data.getPermissions());
        	userSessionData.setRole(data.getRole());
        }
    }

    public void updateFilter() throws Exception {
        UserReportFacade userReportFacade=this.getUserReportFacade();
        UserData data= userReportFacade.findUserByPrimaryKey(userSessionData.getFilter().getLoginName(),userSessionData);
		CorporationReportFacade crpReportFacade=this.getCorporationReportFacade();
		CorporationData crpData=crpReportFacade.findCorporationByPrimaryKey(data.getCorporationId(),userSessionData);
		int divOrgCount=crpData.getDivOrgDataSet().size();
        DivOrgAttribute[] divOrgs = crpData.getDivOrgs();
        LabelItemAttribute[][] divOrgLabels = crpData.getDivOrgLabels();
        GeoLabelAttributes[] divGeoLabels = crpData.getGeoLabels();

        Filter filter = new Filter(data.getLoginName(),data.getCorporationId(),divOrgCount,divOrgs,divOrgLabels,divGeoLabels,data.getGroupLines(),data.getFirstName(),data.getLastName());
        filter.setCorporationType(crpData.getType());
        filter.setCorporationName(crpData.getName());
        filter.setCorporationGroupLines(crpData.getAllGroupLines(divOrgCount));
        userSessionData.setFilter(filter);
    }

    public void updateFilter(UserData data) throws Exception {
    	if (data != null){
	        CorporationReportFacade crpReportFacade=this.getCorporationReportFacade();
	        CorporationData crpData=crpReportFacade.findCorporationByPrimaryKey(data.getCorporationId(),userSessionData);
	        cepaPoints = crpReportFacade.isCepaPoints(data.getCorporationId(), userSessionData);
	        int divOrgCount=crpData.getDivOrgDataSet().size();
	        DivOrgAttribute[] divOrgs = crpData.getDivOrgs();
	        LabelItemAttribute[][] divOrgLabels = crpData.getDivOrgLabels();
	        GeoLabelAttributes[] divGeoLabels = crpData.getGeoLabels();
	
	        Filter filter = new Filter(data.getLoginName(),data.getCorporationId(),divOrgCount,divOrgs,divOrgLabels,divGeoLabels,data.getGroupLines(),data.getFirstName(),data.getLastName());
	        filter.setCorporationType(crpData.getType());
	        filter.setCorporationName(crpData.getName());
	        filter.setCorporationGroupLines(crpData.getAllGroupLines(divOrgCount));
	        filter.setEncriptPassword(data.getPassword());
	        userSessionData.setFilter(filter);
    	}
    }

    public UserSessionData getUserSessionData() {
		return userSessionData;
	}

	


	public Text getText() {
		return text;
	}
	
	
	public BIReportFacade getBIReportFacade() throws DCSecurityException, DeslogedException, CommunicationProblemException {

		BIReportFacade biReportFacade = null;

		if (userSessionData == null) {
			throw new DeslogedException("Class business.SystemAccess(getBIReportFacade), session data is empty","generic.error.deslogued");
		}

		try {
			
			BIReportFacadeHome home = BIReportFacadeUtil.getHome();
			biReportFacade = home.create();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return biReportFacade;
	}
	

	public UserABMFacade getUserABMFacade() throws DCSecurityException, DeslogedException, CommunicationProblemException {
		UserABMFacade userABMFacade = null;
	
		if (userSessionData == null) {
			DeslogedException de=new DeslogedException("Class business.SystemAccess(getUserABMFacade) , session data is empty","generic.error.deslogued");
			throw de;
		}

		try {
			UserABMFacadeHome home = UserABMFacadeUtil.getHome();
			userABMFacade = home.create();
		
		} catch (NamingException naming) {
			logger.fatal(logFormatting.formattingText(naming,"getUserABMFacade()","NamingException",userSessionData.getFilter().getLoginName()), naming);
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getUserABMFacade) , can not access UserABM, NamingException","business.systemaccess.notaccess.userabmfacade");
		} catch (CreateException create) {
			logger.fatal(logFormatting.formattingText(create,"getUserABMFacade()","CreateException",userSessionData.getFilter().getLoginName()), create);
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getUserABMFacade), can not access UserABM, CreateException","business.systemaccess.notaccess.userabmfacade");
		} catch (RemoteException remote) {
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
				
				logger.fatal(logFormatting.formattingText(remote,"getUserABMFacade()","RemoteException",userSessionData.getFilter().getLoginName()), remote);
				throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getUserABMFacade), can not access UserABM, RemoteException","business.systemaccess.notaccess.userabmfacade");
			}
			else{
				
				logger.fatal(logFormatting.formattingText(remote,"getUserABMFacade()","SecutityException",userSessionData.getFilter().getLoginName()), remote);
				throw new DCSecurityException("Class business.SystemAccess(getUserABMFacade()), can not access getUserABMFacadeHome , SecurityException","general.messages.securityexception");
			}

		}

		return userABMFacade;
	}


	public UserReportFacade getUserReportFacade() throws DCSecurityException, DeslogedException, CommunicationProblemException {

		UserReportFacade userReportFacade = null;

		if (userSessionData == null) {
			throw new DeslogedException("Class business.SystemAccess(getUserReportFacade), session data is empty","generic.error.deslogued");
		}

		try {
			
			UserReportFacadeHome home = UserReportFacadeUtil.getHome();
			userReportFacade = home.create();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userReportFacade;
	}

	public ExternalFacade getExternalFacade() throws DCSecurityException, DeslogedException, CommunicationProblemException {

		ExternalFacade exFacade = null;

		 

		try {
			
			ExternalFacadeHome home = ExternalFacadeUtil.getHome();
			exFacade = home.create();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return exFacade;
	}




	public CorporationABMFacade getCorporationABMFacade()
		throws DeslogedException, CommunicationProblemException, DCSecurityException {

		CorporationABMFacade corporationABMFacade = null;

		if (userSessionData == null) {
			DeslogedException de=new DeslogedException("Class business.SystemAccess(getCorporationABMFacade), session data is empty","generic.error.deslogued"); 
			throw de;
		}

		try {
			CorporationABMFacadeHome home = CorporationABMFacadeUtil.getHome();
			corporationABMFacade = home.create();

		} catch (NamingException naming) {
			logger.fatal(logFormatting.formattingText(naming,"getCorporationABMFacade()","NamingException",userSessionData.getFilter().getLoginName()), naming);
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getCorporationABMFacade), can not access CorporationABMFacadeHome, NamingException","business.systemaccess.notaccess.corporationabmfacade");
		} catch (CreateException create) {
			logger.fatal(logFormatting.formattingText(create,"getCorporationABMFacade()","CreateException",userSessionData.getFilter().getLoginName()), create);
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getCorporationABMFacade), can not access CorporationABMFacadeFacade, CreateException","business.systemaccess.notaccess.corporationabmfacade");
		} catch (RemoteException remote) {
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
				
				logger.fatal(logFormatting.formattingText(remote,"getCorporationABMFacade()","RemoteException",userSessionData.getFilter().getLoginName()), remote);
				throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getCorporationABMFacade), can not access CorporationABMFacadeHome , RemoteException","business.systemaccessnotaccess.corporationabmfacade");
			}
			else{
				
				logger.fatal(logFormatting.formattingText(remote,"getCorporationABMFacade()","SecutityException",userSessionData.getFilter().getLoginName()), remote);
				throw new DCSecurityException("Class business.SystemAccess(getCorporationABMFacade()), can not access CorporationABMFacadeHome , SecurityException","general.messages.securityexception");
			}

		}
		
		return corporationABMFacade;
	}


	public CorporationReportFacade getCorporationReportFacade()
			throws DCSecurityException, DeslogedException, CommunicationProblemException {

		CorporationReportFacade corporationReportFacade = null;

		if (userSessionData == null) {
			DeslogedException de=new DeslogedException("Class business.SystemAccess(getCorporationRepoortFacade), session data is empty","generic.error.deslogued");
			throw de;
		}

		try {
			CorporationReportFacadeHome home = CorporationReportFacadeUtil.getHome();
			corporationReportFacade = home.create();

		} catch (NamingException naming) {
			logger.fatal(logFormatting.formattingText(naming,"getCorporationReportFacade()","NamingException",userSessionData.getFilter().getLoginName()), naming);
			throw new CommunicationProblemException("Class business.SystemAccess(getCorporationReportFacde), can not access CorporationReportFacadeHome, NamingException","business.systemacess.notaccess.corporationreportfacde");
		} catch (CreateException create) {
			logger.fatal(logFormatting.formattingText(create,"getCorporationReportFacade()","CreateException",userSessionData.getFilter().getLoginName()), create);
			throw new CommunicationProblemException("Class business.SystemAccess(getCorporationReportFacde), can not access CorporationReportFacadeFacade, CreateException","business.systemacess.notaccess.corporationreportfacde");
		} catch (RemoteException remote) {
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
				
				logger.fatal(logFormatting.formattingText(remote,"getCorporationReportFacade()","RemoteException",userSessionData.getFilter().getLoginName()), remote);
				throw new CommunicationProblemException("Class business.SystemAccess(getCorporationReportFacde), can not access CorporationReportFacadeHome , RemoteException","business.systemacess.notaccess.corporationreportfacde");
			}
			else{
				
				logger.fatal(logFormatting.formattingText(remote,"getCorporationReportFacde()","SecutityException",userSessionData.getFilter().getLoginName()), remote);
				throw new DCSecurityException("Class business.SystemAccess(getCorporationReportFacde), can not access CorporationReportFacadeHome , SecurityException","general.messages.securityexception");
			}
		}

		return corporationReportFacade;
		
	}
	

	public DriverABMFacade getDriverABMFacade() throws DCSecurityException, DeslogedException, CommunicationProblemException {

		DriverABMFacade driverABMFacade = null;

		if (userSessionData == null) {
			throw new DeslogedException("Class business.SystemAccess(getDriverABMFacade) , session data is empty","generic.error.deslogued");
		}

		try {
			DriverABMFacadeHome home = DriverABMFacadeUtil.getHome();
			driverABMFacade = home.create();

		} catch (NamingException naming) {
			logger.fatal(logFormatting.formattingText(naming,"getDriverABMFacade()","NamingException",userSessionData.getFilter().getLoginName()), naming);
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDriverABMFacade) , can not access DriverABM, NamingException","business.systemaccess.notaccess.driverabmfacade");
		} catch (CreateException create) {
			logger.fatal(logFormatting.formattingText(create,"getDriverABMFacade()","CreateException",userSessionData.getFilter().getLoginName()), create);
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDriverABMFacade), can not access DriverABM, CreateException","business.systemaccess.notaccess.driverabmfacade");
		} catch (RemoteException remote) {
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
				
				logger.error(logFormatting.formattingText(remote,"getDriverABMFacade()","RemoteException",userSessionData.getFilter().getLoginName()), remote);
				throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDriverABMFacade), can not access DriverABM, RemoteException","business.systemaccess.notaccess.driverabmfacade");
			}
			else{
				
				logger.fatal(logFormatting.formattingText(remote,"getDriverABMFacade()","SecutityException",userSessionData.getFilter().getLoginName()), remote);
				throw new DCSecurityException("Has ocurred error in business.SystemAccess(getDriverABMFacade()), can not access DriverABM, SecurityException","general.error.securityexception");
			}

		}

		return driverABMFacade;
	}
	
	
	 public VehicleABMFacade getVehicleABMFacade() throws DeslogedException, CommunicationProblemException {
		VehicleABMFacade vehicleABMFacade = null;
		if (userSessionData == null) {
			throw new DeslogedException("Class business.SystemAccess(getDriverABMFacade) , session data is empty","generic.error.deslogued");
		}

		try {
			VehicleABMFacadeHome home = VehicleABMFacadeUtil.getHome();
			vehicleABMFacade = home.create();

		} catch (NamingException naming) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getVehicleABMFacade) , can not access VehicleABM, NamingException","business.systemaccess.notaccess.vehicleabmfacade");
		} catch (CreateException create) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getVehicleABMFacade), can not access VehicleABM, CreateException","business.systemaccess.notaccess.vehicleabmfacade");
		} catch (RemoteException remote) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getVehicleABMFacade), can not access VehicleABM, RemoteException","business.systemaccess.notaccess.vehicleabmfacade");

		}

		return vehicleABMFacade;
	}	
	

	public LanguageReportFacade getLanguageReportFacade() throws DCSecurityException, DeslogedException, CommunicationProblemException {

		LanguageReportFacade languageReportFacade = null;

		if (userSessionData == null) {
			throw new DeslogedException("Class business.SystemAccess(getLanguageReportFacade), session data is empty","generic.error.deslogued");
		}

		try {
			LanguageReportFacadeHome home = LanguageReportFacadeUtil.getHome();
			languageReportFacade = home.create();

		} catch (NamingException naming) {
			logger.fatal(logFormatting.formattingText(naming,"getLanguageReportFacade()","NamingException",userSessionData.getFilter().getLoginName()), naming);
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getLanguageReportFacade) , can not access LanguageReportFacade, NamingException","business.systemaccess.notaccess.languagereportfacade");
		} catch (CreateException create) {
			logger.fatal(logFormatting.formattingText(create,"getLanguageReportFacade()","CreateException",userSessionData.getFilter().getLoginName()), create);
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getLanguageReportFacade), can not access LanguageReportFacade, CreateException","business.systemaccess.notaccess.languagereportfacade");
		} catch (RemoteException remote) {
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
				
				logger.fatal(logFormatting.formattingText(remote,"getLanguageReportFacade()","RemoteException",userSessionData.getFilter().getLoginName()), remote);
				throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getLanguageReportFacade), can not access LanguageReportFacade, RemoteException","business.systemaccess.notaccess.languagereportfacade");
			}
			else{
				
				logger.fatal(logFormatting.formattingText(remote,"getLanguageReportFacade()","SecutityException",userSessionData.getFilter().getLoginName()), remote);
				throw new DCSecurityException("Has ocurred error in business.SystemAccess(getLanguageReportFacade()), can not access LanguageReportFacade, SecurityException","general.error.securityexception");
			}

		}

		return languageReportFacade;
	}



	public DriverReportFacade getDriverReportFacade() throws DCSecurityException,DeslogedException, CommunicationProblemException {

		DriverReportFacade driverReportFacade = null;
		if (userSessionData == null) {
			throw new DeslogedException("Class business.SystemAccess(getDriverABMFacade) , session data is empty","generic.error.deslogued");
		}

		try {
			DriverReportFacadeHome home = DriverReportFacadeUtil.getHome();
			driverReportFacade = home.create();

		} catch (NamingException naming) {
			logger.fatal(logFormatting.formattingText(naming,"getDriverReportFacade()","NamingException",userSessionData.getFilter().getLoginName()), naming);
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDriverReportFacade) , can not access DriverReport, NamingException","business.systemaccess.notaccess.driverreportfacade");
		} catch (CreateException create) {
			logger.fatal(logFormatting.formattingText(create,"getDriverReportFacade()" ,"CreateException",userSessionData.getFilter().getLoginName()), create);
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDriverReportFacade), can not access DriverReport, CreateException","business.systemaccess.notaccess.driverreportfacade");
		} catch (RemoteException remote) {
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
				
				logger.fatal(logFormatting.formattingText(remote,"getDriverReportFacade()","RemoteException",userSessionData.getFilter().getLoginName()), remote);
				throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDriverReportFacade), can not access DriverReport, RemoteException","business.systemaccess.notaccess.driverreportfacade");
			}
			else{
				
				logger.fatal(logFormatting.formattingText(remote,"getDriverReportFacade()","SecutityException",userSessionData.getFilter().getLoginName()), remote);
				throw new DCSecurityException("Has ocurred error in business.SystemAccess(getDriverReportFacade()), can not access DriverReport, SecurityException","general.error.securityexception");
			}
			
			
		}

		return driverReportFacade;
	}
	

	public DataTypeReportFacade getDataTypeReportFacade() throws DCSecurityException,DeslogedException, CommunicationProblemException {

		DataTypeReportFacade dtReportFacade = null;
		if (userSessionData == null) {
			throw new DeslogedException("Class business.SystemAccess(getDataTypeABMFacade) , session data is empty","generic.error.deslogued");
		}

		try {
			DataTypeReportFacadeHome home = DataTypeReportFacadeUtil.getHome();
			dtReportFacade = home.create();

		} catch (NamingException naming) {
			logger.fatal(logFormatting.formattingText(naming,"getDataTypeReportFacade()","NamingException",userSessionData.getFilter().getLoginName()), naming);
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDataTypeReportFacade) , can not access DriverReport, NamingException","business.systemaccess.notaccess.datatypereportfacade");
		} catch (CreateException create) {
			logger.fatal(logFormatting.formattingText(create,"getDataTypeReportFacade()","CreateException",userSessionData.getFilter().getLoginName()), create);
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDataTypeReportFacade), can not access DriverReport, CreateException","business.systemaccess.notaccess.datatypereportfacade");
		} catch (RemoteException remote) {
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
				
				logger.fatal(logFormatting.formattingText(remote,"getDataTypeReportFacade()","RemoteException",userSessionData.getFilter().getLoginName()), remote);
				throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDataTypeReportFacade), can not access DriverReport, RemoteException","business.systemaccess.notaccess.datatypereportfacade");
			}
			else{
				
				logger.fatal(logFormatting.formattingText(remote,"getDataTypeReportFacade()","SecutityException",userSessionData.getFilter().getLoginName()), remote);
				throw new DCSecurityException("Has ocurred error in business.SystemAccess(getDataTypeReportFacade()), can not access DriverReport, SecurityException","general.error.securityexception");
			}
		}

		return dtReportFacade;
	}


	/**
	 * 
	 * @return
	 * @throws DCSecurityException
	 * @throws DeslogedException
	 * @throws CommunicationProblemException
	 */
	public EventABMFacade getEventABMFacade() throws DCSecurityException, DeslogedException, CommunicationProblemException {

		EventABMFacade eventABMFacade = null;

		if (userSessionData == null) {
			throw new DeslogedException("Class business.SystemAccess(getEventABMFacade) , session data is empty","generic.error.deslogued");
		}

		try {
			EventABMFacadeHome home = EventABMFacadeUtil.getHome();
			eventABMFacade = home.create();

		} catch (NamingException naming) {
			logger.fatal(logFormatting.formattingText(naming,"getEventABMFacade()","NamingException",userSessionData.getFilter().getLoginName()), naming);
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDataTypeReportFacade) , can not access DriverReport, NamingException","business.systemaccess.notaccess.eventabmfacade");
		} catch (CreateException create) {
			logger.error(logFormatting.formattingText(create,"getEventABMFacade()","CreateException",userSessionData.getFilter().getLoginName()), create);
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDataTypeReportFacade), can not access DriverReport, CreateException","business.systemaccess.notaccess.eventabmfacade");
		} catch (RemoteException remote) {
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
				
				logger.error(logFormatting.formattingText(remote,"getEventABMFacade()","RemoteException",userSessionData.getFilter().getLoginName()), remote);
				throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDataTypeReportFacade), can not access DriverReport, RemoteException","business.systemaccess.notaccess.eventabmfacade");
			}
			else{
				
				logger.fatal(logFormatting.formattingText(remote,"getEventABMFacade()","SecutityException",userSessionData.getFilter().getLoginName()), remote);
				throw new DCSecurityException("Has ocurred error in business.SystemAccess(getEventABMFacade()), can not access DriverReport, SecurityException","general.error.securityexception");
			}

		}

		return eventABMFacade;
	}
	

	/**
	 * 
	 * @return
	 * @throws DCSecurityException
	 * @throws DeslogedException
	 * @throws CommunicationProblemException
	 */
    public EventReportFacade getEventReportFacade() throws DCSecurityException,DeslogedException, CommunicationProblemException {

        EventReportFacade eventReportFacade = null;
        if (userSessionData == null) {
            throw new DeslogedException("Class business.SystemAccess(getEventReportFacade) , session data is empty","generic.error.deslogued");
        }

        try {
            EventReportFacadeHome home = EventReportFacadeUtil.getHome();
            eventReportFacade = home.create();

        } catch (NamingException naming) {
            logger.fatal(logFormatting.formattingText(naming,"getDriverReportFacade()","NamingException",userSessionData.getFilter().getLoginName()), naming);
            throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDriverReportFacade) , can not access DriverReport, NamingException","business.systemaccess.notaccess.driverreportfacade");
        } catch (CreateException create) {
            logger.fatal(logFormatting.formattingText(create,"getDriverReportFacade()" ,"CreateException",userSessionData.getFilter().getLoginName()), create);
            throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDriverReportFacade), can not access DriverReport, CreateException","business.systemaccess.notaccess.driverreportfacade");
        } catch (RemoteException remote) {
            if(remote.getCause().toString().indexOf("SecurityException" )<=0){
                
                logger.fatal(logFormatting.formattingText(remote,"getDriverReportFacade()","RemoteException",userSessionData.getFilter().getLoginName()), remote);
                throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDriverReportFacade), can not access DriverReport, RemoteException","business.systemaccess.notaccess.driverreportfacade");
            }
            else{
                
                logger.fatal(logFormatting.formattingText(remote,"getDriverReportFacade()","SecutityException",userSessionData.getFilter().getLoginName()), remote);
                throw new DCSecurityException("Has ocurred error in business.SystemAccess(getDriverReportFacade()), can not access DriverReport, SecurityException","general.error.securityexception");
            }
            
            
        }

        return eventReportFacade;
    }
    
    public FleetReportFacade getFleetReportFacade() throws Exception {
        FleetReportFacade fleetReportFacade = null;
        if (userSessionData == null) {
            throw new DeslogedException("getFleetReportFacade","userSessionData = null");
        }
        try {
            FleetReportFacadeHome home = FleetReportFacadeUtil.getHome();
            fleetReportFacade = home.create();
        } catch (Exception e) {
            handler.handleException("getFleetReportFacade",e, userSessionData,null);
        }
        return fleetReportFacade;
    }

    public FleetABMFacade getFleetABMFacade() throws Exception {
        FleetABMFacade fleetABMFacade = null;
        if (userSessionData == null) {
            throw new DeslogedException("getFleetABMFacade","userSessionData = null");
        }
        try {
            FleetABMFacadeHome home = FleetABMFacadeUtil.getHome();
            fleetABMFacade = home.create();
        } catch (Exception e) {
            handler.handleException("getFleetABMFacade",e, userSessionData,null);
        }
        return fleetABMFacade;
    }

    public VehicleReportFacade getVehicleReportFacade() throws DeslogedException, CommunicationProblemException {
		VehicleReportFacade vehicleReportFacade = null;
		if (userSessionData == null) {
			throw new DeslogedException("Class business.SystemAccess(getVehicleReportFacade) , session data is empty","generic.error.deslogued");
		}

		try {
			VehicleReportFacadeHome home = VehicleReportFacadeUtil.getHome();
			vehicleReportFacade = home.create();

		} catch (NamingException naming) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getVehicleReportFacade) , can not access VehicleReport, NamingException","business.systemaccess.notaccess.vehiclereportfacade");
		} catch (CreateException create) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getVehicleReportFacade), can not access VehicleReport, CreateException","business.systemaccess.notaccess.vehiclereportfacade");
		} catch (RemoteException remote) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getVehicleReportFacade), can not access VehicleReport, RemoteException","business.systemaccess.notaccess.vehiclereportfacade");

		}

		return vehicleReportFacade;
	}
	
	

	public RoleReportFacade getRoleReportFacade() throws DCSecurityException, DeslogedException, CommunicationProblemException {

		RoleReportFacade roleReportFacade = null;

		if (userSessionData == null) {
			throw new DeslogedException("Class business.SystemAccess(getRoleReportFacade) , session data is empty","generic.error.deslogued");
		}

		try {
			RoleReportFacadeHome home = RoleReportFacadeUtil.getHome();
			roleReportFacade = home.create();

		} catch (NamingException naming) {
			logger.fatal(logFormatting.formattingText(naming,"getRoleReportFacade()","NamingException",userSessionData.getFilter().getLoginName()), naming);
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getRoleReportFacade) , can not access DriverReport, NamingException","business.systemaccess.notaccess.rolereportfacade");
		} catch (CreateException create) {
			logger.fatal(logFormatting.formattingText(create,"getRoleReportFacade()","CreateException",userSessionData.getFilter().getLoginName()), create);
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getRoleReportFacade), can not access DriverReport, CreateException","business.systemaccess.notaccess.rolereportfacade");
		} catch (RemoteException remote) {
			
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
		
				logger.fatal(logFormatting.formattingText(remote,"getRoleReportFacade()","RemoteException",userSessionData.getFilter().getLoginName()), remote);
				throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getRoleReportFacade), can not access DriverReport, RemoteException","business.systemaccess.notaccess.rolereportfacade");
			}
			else{
				
				logger.fatal(logFormatting.formattingText(remote,"getRoleReportFacade()","SecutityException",userSessionData.getFilter().getLoginName()), remote);
				throw new DCSecurityException("Has ocurred error in business.SystemAccess(getRoleReportFacade), can not access DriverReport, SecurityException","general.error.securityexception");
			}
		} 

		return roleReportFacade;
	}
	


	public ActivityReportFacade getActivityReportFacade() throws DCSecurityException, DeslogedException, CommunicationProblemException {

		ActivityReportFacade activityReportFacade = null;

		if (userSessionData == null) {
			throw new DeslogedException("Class business.SystemAccess(getActivityReportFacade) , session data is empty","generic.error.deslogued");
		}

		try {
			ActivityReportFacadeHome home = ActivityReportFacadeUtil.getHome();
			activityReportFacade = home.create(); 

		} catch (NamingException naming) {
			logger.fatal(logFormatting.formattingText(naming,"getActivityReportFacade()","NamingException",userSessionData.getFilter().getLoginName()), naming);
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getActivityReportFacade) , can not access ActivityReportFacade, NamingException","business.activity.notaccess.activityreportfacade");
		} catch (CreateException create) {
			logger.fatal(logFormatting.formattingText(create,"getActivityReportFacade()","CreateException",userSessionData.getFilter().getLoginName()), create);
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getActivityReportFacade), can not access ActivityReportFacade, CreateException","business.activity.notaccess.activityreportfacade");
		} catch (RemoteException remote) {
			
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
		
				logger.fatal(logFormatting.formattingText(remote,"getActivityReportFacade()","RemoteException",userSessionData.getFilter().getLoginName()), remote);
				throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getActivityReportFacade), can not access ActivityReportFacade, RemoteException","business.activity.notaccess.activityreportfacade");
			}
			else{
				
				logger.fatal(logFormatting.formattingText(remote,"getActivityReportFacade()","SecutityException",userSessionData.getFilter().getLoginName()), remote);
				throw new DCSecurityException("Has ocurred error in business.SystemAccess(getActivityReportFacade), can not access ActivityReportFacade, SecurityException","general.error.securityexception");
			}
		} 

		return activityReportFacade;
	}


	public ActivityABMFacade getActivityABMFacade() throws DCSecurityException, DeslogedException, CommunicationProblemException {

		ActivityABMFacade activityABMFacade = null;

		if (userSessionData == null) {
			throw new DeslogedException("Class business.SystemAccess(getActivityABMFacade) , session data is empty","generic.error.deslogued");
		}

		try {
			ActivityABMFacadeHome home = ActivityABMFacadeUtil.getHome();
			activityABMFacade = home.create();

		} catch (NamingException naming) {
			logger.fatal(logFormatting.formattingText(naming,"getActivityABMFacade()","NamingException",userSessionData.getFilter().getLoginName()), naming);
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getActivityABMFacade) , can not access ActivityABMFacade, NamingException","business.activity.notaccess.activityabmfacade");
		} catch (CreateException create) {
			logger.fatal(logFormatting.formattingText(create,"getActivityABMFacade()","CreateException",userSessionData.getFilter().getLoginName()), create);
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getActivityABMFacade), can not access ActivityABMFacade, CreateException","business.activity.notaccess.activityabmfacade");
		} catch (RemoteException remote) {
			
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
		
				logger.fatal(logFormatting.formattingText(remote,"getActivityABMFacade()","RemoteException",userSessionData.getFilter().getLoginName()), remote);
				throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getActivityABMFacade), can not access ActivityABMFacade, RemoteException","business.activity.notaccess.activityabmfacade");
			}
			else{
				
				logger.fatal(logFormatting.formattingText(remote,"getActivityABMFacade()","SecutityException",userSessionData.getFilter().getLoginName()), remote);
				throw new DCSecurityException("Has ocurred error in business.SystemAccess(getActivityABMFacade), can not access ActivityABMFacade, SecurityException","general.error.securityexception");
			}
		} 

		return activityABMFacade;
	}

    public GroupABMFacade getGroupABMFacade() throws Exception {
        
        GroupABMFacade groupABMFacade = null;
        if (userSessionData == null) {
            throw new DeslogedException("getGroupABMFacade");
        }
        try {
            GroupABMFacadeHome home = GroupABMFacadeUtil.getHome();
            groupABMFacade = home.create();
        } catch(Exception e) {
            handler.handleException("getGroupABMFacade",e, userSessionData, null);  
        }
        return groupABMFacade;
    }

    public GroupReportFacade getGroupReportFacade() throws Exception {
        
        GroupReportFacade groupReportFacade = null;
        if (userSessionData == null) {
            throw new DeslogedException("getGroupReportFacade");
        }
        try {
            GroupReportFacadeHome home = GroupReportFacadeUtil.getHome();
            groupReportFacade = home.create();
        } catch(Exception e) {
            handler.handleException("getGroupReportFacade",e, userSessionData, null);  
        }
        return groupReportFacade;
    }
    
    public AlertABMFacade getAlertABMFacade() throws Exception {
        AlertABMFacade alertABMFacade = null;
        if (userSessionData == null) {
            throw new DeslogedException("getAlertABMFacade","userSessionData = null");
        }
        try {
            AlertABMFacadeHome home = AlertABMFacadeUtil.getHome();
            alertABMFacade = home.create();
        } catch (Exception e) {
            handler.handleException("getAlertABMFacade",e, userSessionData,null);
        }
        return alertABMFacade;
    }
    
    public AlertReportFacade getAlertReportFacade() throws Exception {
        AlertReportFacade alertReportFacade = null;
        if (userSessionData == null) {
            throw new DeslogedException("getAlertReportFacade","userSessionData = null");
        }
        try {
            AlertReportFacadeHome home = AlertReportFacadeUtil.getHome();
            alertReportFacade = home.create();
        } catch (Exception e) {
            handler.handleException("getAlertReportFacade",e, userSessionData,null);
        }
        return alertReportFacade;
    }

    public CENAPRAReportFacade getCENAPRAReportFacade() throws Exception {
        CENAPRAReportFacade cenapraReportFacade = null;
        if (userSessionData == null) {
            throw new DeslogedException("getCENAPRAReportFacade");
        }
        try {
            CENAPRAReportFacadeHome home = CENAPRAReportFacadeUtil.getHome();
            cenapraReportFacade = home.create();
        } catch(Exception e) {
            handler.handleException("getCENAPRAReportFacade",e, userSessionData, null);  
        }
        return cenapraReportFacade;
    }

    public CENAPRAABMFacade getCENAPRAABMFacade() throws Exception {
        CENAPRAABMFacade cenapraABMFacade = null;
        if (userSessionData == null) {
            throw new DeslogedException("getCENAPRAABMFacade");
        }
        try {
            CENAPRAABMFacadeHome home = CENAPRAABMFacadeUtil.getHome();
            cenapraABMFacade = home.create();
        } catch(Exception e) {
            handler.handleException("getCENAPRAABMFacade",e, userSessionData, null);  
        }
        return cenapraABMFacade;
    }

	public int getCorporationId() {
		return userSessionData.getFilter().getCorporationId().intValue();
	}
	
	public boolean isCorporation(int[] corporations) {
		for(int i = 0; i < corporations.length; i++) {
			if(corporations[i] == userSessionData.getFilter().getCorporationId().intValue()) {
				return true;
			}
		}
		return false;
	}

	public boolean isBrasilCorporation() {
		return isCorporation(new int[]{CorporationData.ASTRAZENECA,CorporationData.ALLERGAN,CorporationData.BAYER,CorporationData.CEPA,CorporationData.CHEMTURA,CorporationData.DOW,CorporationData.MONSANTO,CorporationData.SYNGENTA,CorporationData.SYNGENTA_BR,CorporationData.COVESTRO,CorporationData.ECOLAB});
	}
	

	public boolean isCorporation(int corporation) {
		if(corporation == userSessionData.getFilter().getCorporationId().intValue()) {
			return true;
		}
		return false;
	}

	public boolean isCountry(String country) {
		if(userSessionData.getDriverData() != null && userSessionData.getDriverData().getCountryName().equals(country)) {
			return true;
		}
		return false;
	}

	public boolean isForestalUnified() {
		return userSessionData.isForestalUnified();
	}

	public boolean isPointCorporation() {
		return userSessionData.isPointCorporation();
	}
	
	public boolean hasStandarPoints() {
		return cepaPoints;
	}
}
