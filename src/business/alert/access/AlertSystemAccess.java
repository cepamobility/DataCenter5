package business.alert.access;

import java.io.Serializable;

import business.util.ExceptionHandler;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgAttribute;
import communication.administration.corporation.tables.GeoLabelAttributes;
import communication.administration.corporation.tables.LabelItemAttribute;
import communication.administration.user.UserSessionData;
import communication.administration.user.tables.UserData;
import communication.language.Text;
import communication.util.Filter;


public class AlertSystemAccess implements Serializable{ 

	private static final long serialVersionUID = 1L;
	private UserHandler userHandler= new UserHandler();
	/**
	 * Logger for this class
	 */
//	private Logger logger = Logger.getLogger(AlertSystemAccess.class);
//	private LoggerFormatting logFormatting=new LoggerFormatting();
	
	private ExceptionHandler handler = new ExceptionHandler(AlertSystemAccess.class);
    
	private UserSessionData userSessionData;
	
	//private DataTypeAccess dataTypeAccess;
	
	private Text text;
	
    public AlertSystemAccess(String user) throws Exception {
        // long time = System.currentTimeMillis();
        userSessionData = new UserSessionData();
        try {
        	if (user != null){
        		loadUser(user);
        		loadText();	
        	}
    	}catch(Exception e) {
    		Object[] parameters = new Object[1];
    		parameters[0] = user;
    	    handler.handleException("AlertSystemAccess()",e,null,parameters);
        }
    }
	
	public void unSuscribe() throws Exception{};
	
	public Object getReloadKey(){
		Object returnObject = userSessionData.getFilter().getLoginName();
		return returnObject;
	}
	
	public void loadUser(String login) throws Exception {
	
		try{
			userSessionData = new UserSessionData();
	       // long time = System.currentTimeMillis();
			UserData data = userHandler.findUserByPrimaryKey(login,userSessionData);
	        
			CorporationData crpData=userHandler.findCorporationByPrimaryKey(data.getCorporationId(),userSessionData);
			
	        int divOrgCount=crpData.getDivOrgDataSet().size();
	        DivOrgAttribute[] divOrgs = crpData.getDivOrgs();
	
	        LabelItemAttribute[][] divOrgLabels = crpData.getDivOrgLabels();
	        GeoLabelAttributes[] divGeoLabels = crpData.getGeoLabels();
	        Filter filter = new Filter(login,data.getCorporationId(),divOrgCount,divOrgs,divOrgLabels,divGeoLabels,data.getGroupLines(),data.getFirstName(),data.getLastName());
	        filter.setCorporationName(crpData.getName());
	        filter.setCorporationGroupLines(crpData.getAllGroupLines(divOrgCount));
	        userSessionData.setFilter(filter);
			userSessionData.setLenguage(data.getLanguage());
	        userSessionData.setRole(data.getRole());
	        userSessionData.setPermissions(data.getPermissions());
	        userSessionData.setDriver(data.getDriverData());
	        userSessionData.setMultipleCorporation(true);
		}catch(Exception e){
			Object[] parameters = new Object[1];
			parameters[0] = login;
			handler.handleException("loadUser",e,null,parameters);
		}
	}	
	
   /* public void reloadFilter(int corporationId) throws Exception {
        UserData data= userReportFacade.findUserByPrimaryKey(userSessionData.getFilter().getLoginName(),userSessionData);
        CorporationReportFacade crpReportFacade=this.getCorporationReportFacade();
        CorporationData crpData=crpReportFacade.findCorporationByPrimaryKey(new Integer(corporationId),userSessionData);
        int divOrgCount=crpData.getDivOrgDataSet().size();
        DivOrgAttribute[] divOrgs = crpData.getDivOrgs();
        LabelItemAttribute[][] divOrgLabels = crpData.getDivOrgLabels();
        GeoLabelAttributes[] divGeoLabels = crpData.getGeoLabels();
        Filter filter = new Filter(userSessionData.getFilter().getLoginName(),new Integer(corporationId),divOrgCount,divOrgs,divOrgLabels,divGeoLabels,data.getGroupLines(),userSessionData.getFilter().getFirstName(),userSessionData.getFilter().getLastName());
        filter.setCorporationGroupLines(crpData.getAllGroupLines(divOrgCount));
        userSessionData.setFilter(filter);
    }   */
	
	public void loadFilter(Integer corporationid) throws Exception {
		try{
		   if(userSessionData.getFilter().getCorporationId().equals(corporationid)){
		   		loadUser(userSessionData.getFilter().getLoginName());
		   }
		}catch(Exception e){
			Object[] parameters = new Object[1];
			parameters[0] = corporationid;
			handler.handleException("loadFilter",e,null,parameters);
		}
	}

	public void loadText() throws Exception {
		try{
			text = new Text();
			text.setContents(userHandler.getTexts(userSessionData.getLenguage(),userSessionData.getFilter().getCorporationId()));
		}catch(Exception e){
			handler.handleException("loadText",e,null,null);
			
		}
	}
	
	/*public void reloadText() throws Exception {
		text.setContents(userHandler.reloadTexts(userSessionData.getLenguage(),userSessionData.getFilter().getCorporationId(),this,userSessionData));
	}	*/
	
	/*public void updateLanguage(Text text) throws Exception {  	
	    text = new Text();
		text.setContents(TextContainer.getInstance().getTexts(userSessionData.getLenguage(),userSessionData.getFilter().getCorporationId(),this,userSessionData));			
	}*/

	public void updatePermissions() throws Exception {
		try{
			UserData data= userHandler.findUserByPrimaryKey(userSessionData.getFilter().getLoginName(),userSessionData);
	        userSessionData.setPermissions(data.getPermissions());
	        userSessionData.setRole(data.getRole());
		}catch(Exception e){
			handler.handleException("updatePermissions",e,null,null);			
		}
	}
	   
    public void updatePermissions(UserData data) throws Exception {
    	try{
	        userSessionData.setPermissions(data.getPermissions());
	        userSessionData.setRole(data.getRole());
    	}catch(Exception e){
    		Object[] parameters = new Object[1];
    		parameters[0] = data;
			handler.handleException("updatePermissions",e,null,parameters);			
		}
    	
    }

    public void updateFilter() throws Exception {
    	try{
	        UserData data= userHandler.findUserByPrimaryKey(userSessionData.getFilter().getLoginName(),userSessionData);
			CorporationData crpData=userHandler.findCorporationByPrimaryKey(data.getCorporationId(),userSessionData);
			int divOrgCount=crpData.getDivOrgDataSet().size();
	        DivOrgAttribute[] divOrgs = crpData.getDivOrgs();
	        LabelItemAttribute[][] divOrgLabels = crpData.getDivOrgLabels();
	        GeoLabelAttributes[] divGeoLabels = crpData.getGeoLabels();
	
	        Filter filter = new Filter(data.getLoginName(),data.getCorporationId(),divOrgCount,divOrgs,divOrgLabels,divGeoLabels,data.getGroupLines(),data.getFirstName(),data.getLastName());
	        filter.setCorporationName(crpData.getName());
	        filter.setCorporationGroupLines(crpData.getAllGroupLines(divOrgCount));
	        userSessionData.setFilter(filter);
    	}catch(Exception e){
    		handler.handleException("updateFilter",e,null,null);			
		}
    }

    public void updateFilter(UserData data) throws Exception {
    	try{
	        CorporationData crpData=userHandler.findCorporationByPrimaryKey(data.getCorporationId(),userSessionData);
	        int divOrgCount=crpData.getDivOrgDataSet().size();
	        DivOrgAttribute[] divOrgs = crpData.getDivOrgs();
	        LabelItemAttribute[][] divOrgLabels = crpData.getDivOrgLabels();
	        GeoLabelAttributes[] divGeoLabels = crpData.getGeoLabels();
	
	        Filter filter = new Filter(data.getLoginName(),data.getCorporationId(),divOrgCount,divOrgs,divOrgLabels,divGeoLabels,data.getGroupLines(),data.getFirstName(),data.getLastName());
	        filter.setCorporationName(crpData.getName());
	        filter.setCorporationGroupLines(crpData.getAllGroupLines(divOrgCount));
	        userSessionData.setFilter(filter);
    	}catch(Exception e){
    		Object[] parameters = new Object[1];
    		parameters[0] = data;
    		handler.handleException("updateFilter",e,null,parameters);			
		}
    }

    public UserSessionData getUserSessionData() {
		return userSessionData;
	}

	


	public Text getText() {
		return text;
	}
	

	
}