package business;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;

import persistence.report.BIReportBean;
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
import communication.administration.user.UserSessionData;
import communication.administration.user.tables.UserData;
import communication.exception.CommunicationProblemException;
import communication.exception.DCSecurityException;
import communication.exception.DeslogedException;
import communication.language.Text;
import communication.language.TextContainer;
import communication.util.Filter;


public class SystemAccessRemote { 

	private static final long serialVersionUID = 1L;
	private UserSessionData userSessionData;
	Hashtable environment = new Hashtable();
	
	//private DataTypeAccess dataTypeAccess;
	
	private Text text;
	private boolean horizontalAccess=false;
	 
	 //
	
	  
    public SystemAccessRemote(String user, Hashtable ev) throws Exception {
      	 
    	this.environment=ev;
    	this.environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.security.jndi.LoginInitialContextFactory");  
    	this.environment.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");  
    	this.environment.put(Context.SECURITY_PROTOCOL, "datacenter");  

    	 /* LoginContext loginContext = null;
		  loginContext = new LoginContext("datacenter",  new MyHandler());
		  loginContext.login();
		 System.out.println("LOGIN!!!!!!!!"); 
		 */  
        long time = System.currentTimeMillis();
        userSessionData = new UserSessionData();
  
    	try {
    		loadUser(user);
    		loadText();
    	}catch(Exception e) {
    		e.printStackTrace();
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Time 2 : "+time);
    }
	
	public void unSuscribe() throws Exception{};
	
	public boolean isHorizontalAccess() {
		return horizontalAccess;
	}

	public void setHorizontalAccess(boolean horizontalAccess) {
		this.horizontalAccess = horizontalAccess;
	}

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
		
        int divOrgCount=crpData.getDivOrgDataSet().size();
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
    }

    public UserSessionData getUserSessionData() {
    	userSessionData.setHorizontalAccess(horizontalAccess);
		return userSessionData;
	}

	


	public Text getText() {
		return text;
	}
	
	

	public UserABMFacade getUserABMFacade() throws DCSecurityException, DeslogedException, CommunicationProblemException {
		UserABMFacade userABMFacade = null;
	
		if (userSessionData == null) {
			DeslogedException de=new DeslogedException("Class business.SystemAccess(getUserABMFacade) , session data is empty","generic.error.deslogued");
			throw de;
		}

		try {
			UserABMFacadeHome home = UserABMFacadeUtil.getHome(environment);
			userABMFacade = home.create();
		
		} catch (NamingException naming) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getUserABMFacade) , can not access UserABM, NamingException","business.systemaccess.notaccess.userabmfacade");
		} catch (CreateException create) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getUserABMFacade), can not access UserABM, CreateException","business.systemaccess.notaccess.userabmfacade");
		} catch (RemoteException remote) {
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
				throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getUserABMFacade), can not access UserABM, RemoteException","business.systemaccess.notaccess.userabmfacade");
			}
			else{
				throw new DCSecurityException("Class business.SystemAccess(getUserABMFacade()), can not access getUserABMFacadeHome , SecurityException","general.messages.securityexception");
			}

		}

		return userABMFacade;
	}
	
	
	public ExternalFacade getExternalFacade() throws DCSecurityException, DeslogedException, CommunicationProblemException {
		ExternalFacade exFacade = null;
	
		if (userSessionData == null) {
			DeslogedException de=new DeslogedException("Class business.SystemAccess(getExternalFacade) , session data is empty","generic.error.deslogued");
			throw de;
		}

		try {
			ExternalFacadeHome home = ExternalFacadeUtil.getHome(environment);
			exFacade = home.create();
		
		} catch (NamingException naming) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getUserABMFacade) , can not access UserABM, NamingException","business.systemaccess.notaccess.userabmfacade");
		} catch (CreateException create) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getUserABMFacade), can not access UserABM, CreateException","business.systemaccess.notaccess.userabmfacade");
		} catch (RemoteException remote) {
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
				throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getUserABMFacade), can not access UserABM, RemoteException","business.systemaccess.notaccess.userabmfacade");
			}
			else{
				throw new DCSecurityException("Class business.SystemAccess(getExternalFacade()), can not access getUserABMFacadeHome , SecurityException","general.messages.securityexception");
			}

		}

		return exFacade;
	}


	public UserReportFacade getUserReportFacade() throws DCSecurityException, DeslogedException, CommunicationProblemException {

		UserReportFacade userReportFacade = null;

		if (userSessionData == null) {
			throw new DeslogedException("Class business.SystemAccess(getUserReportFacade), session data is empty","generic.error.deslogued");
		}

		try {
			
			UserReportFacadeHome home = UserReportFacadeUtil.getHome(environment);
			userReportFacade = home.create();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userReportFacade;
	}


	public BIReportFacade getBIReportFacade() throws DCSecurityException, DeslogedException, CommunicationProblemException {

		BIReportFacade biReportFacade = null;

		if (userSessionData == null) {
			throw new DeslogedException("Class business.SystemAccess(getBIReportFacade), session data is empty","generic.error.deslogued");
		}

		try {
			
			BIReportFacadeHome home = BIReportFacadeUtil.getHome(environment);
			biReportFacade = home.create();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return biReportFacade;
	}

	public CorporationABMFacade getCorporationABMFacade()
		throws DeslogedException, CommunicationProblemException, DCSecurityException {

		CorporationABMFacade corporationABMFacade = null;

		if (userSessionData == null) {
			DeslogedException de=new DeslogedException("Class business.SystemAccess(getCorporationABMFacade), session data is empty","generic.error.deslogued"); 
			throw de;
		}

		try {
			CorporationABMFacadeHome home = CorporationABMFacadeUtil.getHome(environment);
			corporationABMFacade = home.create();

		} catch (NamingException naming) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getCorporationABMFacade), can not access CorporationABMFacadeHome, NamingException","business.systemaccess.notaccess.corporationabmfacade");
		} catch (CreateException create) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getCorporationABMFacade), can not access CorporationABMFacadeFacade, CreateException","business.systemaccess.notaccess.corporationabmfacade");
		} catch (RemoteException remote) {
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
				throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getCorporationABMFacade), can not access CorporationABMFacadeHome , RemoteException","business.systemaccessnotaccess.corporationabmfacade");
			}
			else{
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
			CorporationReportFacadeHome home = CorporationReportFacadeUtil.getHome(environment);
			corporationReportFacade = home.create();

		} catch (NamingException naming) {
			throw new CommunicationProblemException("Class business.SystemAccess(getCorporationReportFacde), can not access CorporationReportFacadeHome, NamingException","business.systemacess.notaccess.corporationreportfacde");
		} catch (CreateException create) {
			throw new CommunicationProblemException("Class business.SystemAccess(getCorporationReportFacde), can not access CorporationReportFacadeFacade, CreateException","business.systemacess.notaccess.corporationreportfacde");
		} catch (RemoteException remote) {
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
				
				throw new CommunicationProblemException("Class business.SystemAccess(getCorporationReportFacde), can not access CorporationReportFacadeHome , RemoteException","business.systemacess.notaccess.corporationreportfacde");
			}
			else{
				
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
			DriverABMFacadeHome home = DriverABMFacadeUtil.getHome(environment);
			driverABMFacade = home.create();

		} catch (NamingException naming) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDriverABMFacade) , can not access DriverABM, NamingException","business.systemaccess.notaccess.driverabmfacade");
		} catch (CreateException create) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDriverABMFacade), can not access DriverABM, CreateException","business.systemaccess.notaccess.driverabmfacade");
		} catch (RemoteException remote) {
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
				throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDriverABMFacade), can not access DriverABM, RemoteException","business.systemaccess.notaccess.driverabmfacade");
			}
			else{
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
			VehicleABMFacadeHome home = VehicleABMFacadeUtil.getHome(environment);
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
			LanguageReportFacadeHome home = LanguageReportFacadeUtil.getHome(environment);
			languageReportFacade = home.create();

		} catch (NamingException naming) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getLanguageReportFacade) , can not access LanguageReportFacade, NamingException","business.systemaccess.notaccess.languagereportfacade");
		} catch (CreateException create) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getLanguageReportFacade), can not access LanguageReportFacade, CreateException","business.systemaccess.notaccess.languagereportfacade");
		} catch (RemoteException remote) {
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
				
				throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getLanguageReportFacade), can not access LanguageReportFacade, RemoteException","business.systemaccess.notaccess.languagereportfacade");
			}
			else{
				
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
			DriverReportFacadeHome home = DriverReportFacadeUtil.getHome(environment);
			driverReportFacade = home.create();

		} catch (NamingException naming) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDriverReportFacade) , can not access DriverReport, NamingException","business.systemaccess.notaccess.driverreportfacade");
		} catch (CreateException create) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDriverReportFacade), can not access DriverReport, CreateException","business.systemaccess.notaccess.driverreportfacade");
		} catch (RemoteException remote) {
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
				
				throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDriverReportFacade), can not access DriverReport, RemoteException","business.systemaccess.notaccess.driverreportfacade");
			}
			else{
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
			DataTypeReportFacadeHome home = DataTypeReportFacadeUtil.getHome(environment);
			dtReportFacade = home.create();

		} catch (NamingException naming) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDataTypeReportFacade) , can not access DriverReport, NamingException","business.systemaccess.notaccess.datatypereportfacade");
		} catch (CreateException create) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDataTypeReportFacade), can not access DriverReport, CreateException","business.systemaccess.notaccess.datatypereportfacade");
		} catch (RemoteException remote) {
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
				
				throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDataTypeReportFacade), can not access DriverReport, RemoteException","business.systemaccess.notaccess.datatypereportfacade");
			}
			else{
				
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
			EventABMFacadeHome home = EventABMFacadeUtil.getHome(environment);
			eventABMFacade = home.create();

		} catch (NamingException naming) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDataTypeReportFacade) , can not access DriverReport, NamingException","business.systemaccess.notaccess.eventabmfacade");
		} catch (CreateException create) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDataTypeReportFacade), can not access DriverReport, CreateException","business.systemaccess.notaccess.eventabmfacade");
		} catch (RemoteException remote) {
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
				
				throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDataTypeReportFacade), can not access DriverReport, RemoteException","business.systemaccess.notaccess.eventabmfacade");
			}
			else{
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
            EventReportFacadeHome home = EventReportFacadeUtil.getHome(environment);
            eventReportFacade = home.create();

        } catch (NamingException naming) {
            
            throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDriverReportFacade) , can not access DriverReport, NamingException","business.systemaccess.notaccess.driverreportfacade");
        } catch (CreateException create) {
            
            throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDriverReportFacade), can not access DriverReport, CreateException","business.systemaccess.notaccess.driverreportfacade");
        } catch (RemoteException remote) {
            if(remote.getCause().toString().indexOf("SecurityException" )<=0){
                
                
                throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getDriverReportFacade), can not access DriverReport, RemoteException","business.systemaccess.notaccess.driverreportfacade");
            }
            else{
                
                
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
            FleetReportFacadeHome home = FleetReportFacadeUtil.getHome(environment);
            fleetReportFacade = home.create();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return fleetReportFacade;
    }

    public FleetABMFacade getFleetABMFacade() throws Exception {
        FleetABMFacade fleetABMFacade = null;
        if (userSessionData == null) {
            throw new DeslogedException("getFleetABMFacade","userSessionData = null");
        }
        try {
            FleetABMFacadeHome home = FleetABMFacadeUtil.getHome(environment);
            fleetABMFacade = home.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fleetABMFacade;
    }

    public VehicleReportFacade getVehicleReportFacade() throws DeslogedException, CommunicationProblemException {
		VehicleReportFacade vehicleReportFacade = null;
		if (userSessionData == null) {
			throw new DeslogedException("Class business.SystemAccess(getVehicleReportFacade) , session data is empty","generic.error.deslogued");
		}

		try {
			VehicleReportFacadeHome home = VehicleReportFacadeUtil.getHome(environment);
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
			RoleReportFacadeHome home = RoleReportFacadeUtil.getHome(environment);
			roleReportFacade = home.create();

		} catch (NamingException naming) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getRoleReportFacade) , can not access DriverReport, NamingException","business.systemaccess.notaccess.rolereportfacade");
		} catch (CreateException create) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getRoleReportFacade), can not access DriverReport, CreateException","business.systemaccess.notaccess.rolereportfacade");
		} catch (RemoteException remote) {
			
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
				throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getRoleReportFacade), can not access DriverReport, RemoteException","business.systemaccess.notaccess.rolereportfacade");
			}
			else{
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
			ActivityReportFacadeHome home = ActivityReportFacadeUtil.getHome(environment);
			activityReportFacade = home.create(); 

		} catch (NamingException naming) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getActivityReportFacade) , can not access ActivityReportFacade, NamingException","business.activity.notaccess.activityreportfacade");
		} catch (CreateException create) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getActivityReportFacade), can not access ActivityReportFacade, CreateException","business.activity.notaccess.activityreportfacade");
		} catch (RemoteException remote) {
			
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
		
				throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getActivityReportFacade), can not access ActivityReportFacade, RemoteException","business.activity.notaccess.activityreportfacade");
			}
			else{
				
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
			ActivityABMFacadeHome home = ActivityABMFacadeUtil.getHome(environment);
			activityABMFacade = home.create();

		} catch (NamingException naming) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getActivityABMFacade) , can not access ActivityABMFacade, NamingException","business.activity.notaccess.activityabmfacade");
		} catch (CreateException create) {
			throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getActivityABMFacade), can not access ActivityABMFacade, CreateException","business.activity.notaccess.activityabmfacade");
		} catch (RemoteException remote) {
			
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
		
				throw new CommunicationProblemException("Has ocurred error in business.SystemAccess(getActivityABMFacade), can not access ActivityABMFacade, RemoteException","business.activity.notaccess.activityabmfacade");
			}
			else{
				
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
            GroupABMFacadeHome home = GroupABMFacadeUtil.getHome(environment);
            groupABMFacade = home.create();
        } catch(Exception e) {
        	 e.printStackTrace();
        }
        return groupABMFacade;
    }

    public GroupReportFacade getGroupReportFacade() throws Exception {
        
        GroupReportFacade groupReportFacade = null;
        if (userSessionData == null) {
            throw new DeslogedException("getGroupReportFacade");
        }
        try {
            GroupReportFacadeHome home = GroupReportFacadeUtil.getHome(environment);
            groupReportFacade = home.create();
        } catch(Exception e) {
        	 e.printStackTrace();
        }
        return groupReportFacade;
    }
    
    public AlertABMFacade getAlertABMFacade() throws Exception {
        AlertABMFacade alertABMFacade = null;
        if (userSessionData == null) {
            throw new DeslogedException("getAlertABMFacade","userSessionData = null");
        }
        try {
            AlertABMFacadeHome home = AlertABMFacadeUtil.getHome(environment);
            alertABMFacade = home.create();
        } catch (Exception e) {
        	 e.printStackTrace();
        }
        return alertABMFacade;
    }
    
    public AlertReportFacade getAlertReportFacade() throws Exception {
        AlertReportFacade alertReportFacade = null;
        if (userSessionData == null) {
            throw new DeslogedException("getAlertReportFacade","userSessionData = null");
        }
        try {
            AlertReportFacadeHome home = AlertReportFacadeUtil.getHome(environment);
            alertReportFacade = home.create();
        } catch (Exception e) {
        	 e.printStackTrace();
        }
        return alertReportFacade;
    }

    public CENAPRAReportFacade getCENAPRAReportFacade() throws Exception {
        CENAPRAReportFacade cenapraReportFacade = null;
        if (userSessionData == null) {
            throw new DeslogedException("getCENAPRAReportFacade");
        }
        try {
            CENAPRAReportFacadeHome home = CENAPRAReportFacadeUtil.getHome(environment);
            cenapraReportFacade = home.create();
        } catch(Exception e) {
        	 e.printStackTrace();
        }
        return cenapraReportFacade;
    }

    public CENAPRAABMFacade getCENAPRAABMFacade() throws Exception {
        CENAPRAABMFacade cenapraABMFacade = null;
        if (userSessionData == null) {
            throw new DeslogedException("getCENAPRAABMFacade");
        }
        try {
            CENAPRAABMFacadeHome home = CENAPRAABMFacadeUtil.getHome(environment);
            cenapraABMFacade = home.create();
        } catch(Exception e) {
           e.printStackTrace();
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
}
