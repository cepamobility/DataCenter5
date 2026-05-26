package presentation.translator.web.driver;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.driver.DriverReportFacade;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.user.UserSessionData;
import communication.datatype.tables.ContractDataType;
import communication.datatype.tables.EducationLevelDataType;
import communication.datatype.tables.MaritalStatusDataType;
import communication.datatype.tables.PuestoPepsicoDataType;
import communication.datatype.tables.SexDataType;
import communication.datatype.tables.TypeVehicleDataType;
import communication.driver.tables.DriverData;
import communication.language.Text;

/**
 * @author jrodriguez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DriverUtil {

	public static final int VIEW = 0;
	public static final int EDIT = 1;

	/**
	 * @param createData
	 * @return
	 */
	public static DriverData createDriverData(UserSessionData userSession, DriverForm createData) {
	    
        GeoLabelData country = Util.getGeoLabelData(createData);
        Collection list = Util.getDivOrgs(createData,userSession);
        Integer id = (createData.getId() != null) ? new Integer(createData.getId()) : null;
        
        if (id == null){
        	System.out.println("Update DriverData, id = null");
        }
        
        TypeVehicleDataType typeVehicle = (Util.empty(createData.getVehicleAllowed())) ? null : new TypeVehicleDataType(createData.getVehicleAllowed());
        ContractDataType contract = (Util.empty(createData.getContract())) ? null : new ContractDataType(createData.getContract());
        Date licenseExpiry = createData.getLicenseExpiry();
        
        Integer points = null;
        if(!Util.empty(createData.getPoints()) && Util.isNumber(createData.getPoints())) {
        	points = new Integer(createData.getPoints());
        }
        String licenseCategory = "";
        if(userSession.getFilter().getCorporationId().intValue() == CorporationData.CIMSA) {
        	licenseCategory += (Util.isChecked(createData.getOption1())) ? "1" : "0";
        	licenseCategory += (Util.isChecked(createData.getOption2())) ? "1" : "0";
        	licenseCategory += (Util.isChecked(createData.getOption3())) ? "1" : "0";
        	licenseCategory += (Util.isChecked(createData.getOption4())) ? "1" : "0";
        	licenseCategory += (Util.isChecked(createData.getOption5())) ? "1" : "0";
        	licenseCategory += (Util.isChecked(createData.getOption6())) ? "1" : "0";
        	licenseCategory += (Util.isChecked(createData.getOption7())) ? "1" : "0";
        }else {
        	licenseCategory = createData.getLC();
        }
        
        Date authorizateDate = createData.getAuthorizateDate();
        
        return new DriverData(id,createData.getFirstName(), createData.getLastName(),country,
				createData.getBirth(), createData.getCity(),new SexDataType(createData.getSex()),createData.getNationality(),
                 new CorporationData(userSession.getFilter().getCorporationId(),"",null,null,null,null),list,
				 licenseExpiry,licenseCategory,createData.getLicenseObs(), createData.getLicenseGet(), typeVehicle,
				createData.getOfficeAddress(), createData.getEmail(),createData.getPersonalAddress(),
                createData.getLicenseNumber(),createData.getCorporationId(),createData.getPhoneNumber(),
                createData.getInclusionDate(),null,Boolean.parseBoolean(createData.getDrives()),
                Boolean.parseBoolean(createData.getEmployee()),contract,createData.getBpsregistry(),createData.getHealth(), 
                createData.getLicenseNotExpiry().equals("0"),points,createData.getAssignedVehicleData(),
                createData.getSuperviser(),createData.getManager(),
               new MaritalStatusDataType(createData.getMaritalStatus()),
        		new EducationLevelDataType(createData.getEducationLevel()),
        		new PuestoPepsicoDataType(createData.getPuestoPepsico()),
        		createData.getLicenseCoverage(),
        		createData.getFederativeEntity(), 
        		createData.getAuthorizateWho(),authorizateDate, 
        		createData.getIdDallas(),Boolean.parseBoolean(createData.getTemporal()), 
        		createData.getStateOrRegion(), createData.getNoHrd()!=null && createData.getNoHrd().equalsIgnoreCase("on")?true:false,
        		createData.getNoHrdComment(),Boolean.parseBoolean(createData.getDirector())); 
	}

	/**
	 * 
	 * @param data
	 * @param function
	 * @param messages
	 * @return
	 */
	public static DriverForm createDriverForm(DriverData data, int function, Text messages) {
		DriverForm driverForm = new DriverForm();
		driverForm.setId(String.valueOf(data.getId()));
		driverForm.setFirstName(data.getFirstName());
		driverForm.setLastName(data.getLastName());
		if(data.getBirthDate() != null) {
			Calendar birthDate = Calendar.getInstance();
			birthDate.setTime(data.getBirthDate());
			driverForm.setBirthDate(String.valueOf(birthDate.get(Calendar.DATE)));
			driverForm.setBirthMonth(String.valueOf(birthDate.get(Calendar.MONTH)+1));
			driverForm.setBirthYear(String.valueOf(birthDate.get(Calendar.YEAR)));
		}
		//driverForm.setSex(data.getSex().getResourceBundleId());
		driverForm.setNationality(data.getNationality());
		driverForm.setStateOrRegion(data.getStateOrRegion());
		driverForm.setCorporation(String.valueOf(data.getCorporation().getId()));
		
	    GeoItemData geoItem1 = (GeoItemData)data.getCountry().getItemIterator().next();
	    driverForm.setCountry1(String.valueOf(geoItem1.getId()));
	    if(geoItem1.getSubItems() != null) {
		    GeoItemData geoItem2 = (GeoItemData)geoItem1.getSubItems().getItemIterator().next();
		    driverForm.setCountry2(String.valueOf(geoItem2.getId()));
		    if(geoItem2.getSubItems() != null) {
			    GeoItemData geoItem3 = (GeoItemData)geoItem2.getSubItems().getItemIterator().next();
			    driverForm.setCountry3(String.valueOf(geoItem3.getId()));
            }
	    }

	    Iterator it = data.getDivOrgsIterator();
		int i = 1;
		while(it.hasNext()) {
		    DivOrgData divOrgData = (DivOrgData)it.next();
		    ItemData item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
		    driverForm.setDivOrg(String.valueOf(item1.getId()),i,1);
		    if(item1.getSubItems() != null) {
			    ItemData item2 = (ItemData)item1.getSubItems().getItemIterator().next();
			    driverForm.setDivOrg(String.valueOf(item2.getId()),i,2);
			    if(item2.getSubItems() != null) {
				    ItemData item3 = (ItemData)item2.getSubItems().getItemIterator().next();
				    driverForm.setDivOrg(String.valueOf(item3.getId()),i,3);
			    }
		    }
		    i++;
		}
		
		/*if(function == EDIT) {
			driverForm.setSex(data.getSex().getResourceBundleId());
		}else {
			driverForm.setSex(messages.getText(data.getSex().getResourceBundleId()));
		}*/
		driverForm.setSex(data.getSex().getResourceBundleId());
		driverForm.setEducationLevel((data.getEducationLevel() != null)?data.getEducationLevel().getResourceBundleId():null);
		driverForm.setMaritalStatus((data.getMaritalStatus() != null)?data.getMaritalStatus().getResourceBundleId():null);
		driverForm.setPuestoPepsico((data.getPuesto() != null)?data.getPuesto().getResourceBundleId():null);
		
		driverForm.setCity(data.getCity());
		if(data.getLicenseExpiry() != null) {
			Calendar licenseExpiry = Calendar.getInstance();
			licenseExpiry.setTime(data.getLicenseExpiry());
			driverForm.setLicenseExpiryDate(String.valueOf(licenseExpiry.get(Calendar.DATE)));
			driverForm.setLicenseExpiryMonth(String.valueOf(licenseExpiry.get(Calendar.MONTH)+1));
			driverForm.setLicenseExpiryYear(String.valueOf(licenseExpiry.get(Calendar.YEAR)));
		}
		if(data.getLicenseCategory() != null) {
			driverForm.setLicenseCategory(String.valueOf(data.getLicenseCategory()));
		}
		if(data.getLicenseObs() != null) {
			driverForm.setLicenseObs(data.getLicenseObs());
		}
		if(data.getLicenseGet() != null) {
			Calendar licenseGet = Calendar.getInstance();
			licenseGet.setTime(data.getLicenseGet());
			driverForm.setLicenseGetDate(String.valueOf(licenseGet.get(Calendar.DATE)));
			driverForm.setLicenseGetMonth(String.valueOf(licenseGet.get(Calendar.MONTH)+1));
			driverForm.setLicenseGetYear(String.valueOf(licenseGet.get(Calendar.YEAR)));
		}
		if(data.getVehicleAllowed() != null) {
			driverForm.setVehicleAllowed(data.getVehicleAllowed().getResourceBundleId());
		}
        if(data.getInclusionDate() != null) {
            Calendar licenseExpiry = Calendar.getInstance();
            licenseExpiry.setTime(data.getInclusionDate());
            driverForm.setStartDate(String.valueOf(licenseExpiry.get(Calendar.DATE)));
            driverForm.setStartMonth(String.valueOf(licenseExpiry.get(Calendar.MONTH)+1));
            driverForm.setStartYear(String.valueOf(licenseExpiry.get(Calendar.YEAR)));
        }
		driverForm.setOfficeAddress(data.getOfficeAddress());
		driverForm.setEmail(data.getEmail());
		driverForm.setPersonalAddress(data.getPersonalAddress());
        if(data.getLicenseNumber() != null) {
            driverForm.setLicenseNumber(String.valueOf(data.getLicenseNumber()));
        }
        driverForm.setCorporationId(String.valueOf(data.getCorporationId()));
        driverForm.setPhoneNumber(data.getPhoneNumber());
        driverForm.setDrives(String.valueOf(data.isDrives()));
        driverForm.setEmployee(String.valueOf(data.isEmployee()));
        if(data.getContract() != null) {
            driverForm.setContract(data.getContract().getResourceBundleId());
        }
        driverForm.setBpsregistry(data.getBpsregistry());
        
        if(data.isLicenseNotExpiry()) {
        	driverForm.setLicenseNotExpiry("0");
        	driverForm.setLicenseExpiryDate(null);
        	driverForm.setLicenseExpiryMonth(null);
        	driverForm.setLicenseExpiryYear(null);
        }else {
        	driverForm.setLicenseNotExpiry("1");
        }
        driverForm.setHealth(data.getHealthExpiry());
        
        if(data.getAssignedVehicle() != null) {
        	driverForm.setAssignedVehicle(data.getAssignedVehicle().getResourceBundleId());
        }
        driverForm.setSuperviser(data.getSuperviser());
        driverForm.setManager(data.getManager());
        
        driverForm.setLicenseCoverage(data.getLicenseCoverage());
        driverForm.setFederativeEntity(data.getFederativeEntity());
        driverForm.setAuthorizateWho(data.getAuthorizateWho());
        
        if(data.getAuthorizateDate() != null) {
            Calendar authorizate = Calendar.getInstance();
            authorizate.setTime(data.getAuthorizateDate());
            driverForm.setAuthorizateDateDay(String.valueOf(authorizate.get(Calendar.DATE)));
            driverForm.setAuthorizateDateMonth(String.valueOf(authorizate.get(Calendar.MONTH)+1));
            driverForm.setAuthorizateDateYear(String.valueOf(authorizate.get(Calendar.YEAR)));
        }
      
        driverForm.setIdDallas(data.getIdDallas());
        driverForm.setNoHrd(data.isNoHrd()?"on":"off");
        driverForm.setNoHrdComment(data.getNoHrdComment());
        driverForm.setTemporal(String.valueOf(data.isTemporal()));
        driverForm.setDirector(String.valueOf(data.isDirector()));
		return driverForm;
	}
	
	/**
	 * @param driverForm
	 * @return
	 */
	public static Date getDateRepresentation(String date,String month,String year) {
		if(notEmpty(date) && notEmpty(month) && notEmpty(year)) {
			Calendar instance = Calendar.getInstance();
			instance.set(Calendar.DATE,Integer.parseInt(date));
			instance.set(Calendar.MONTH,Integer.parseInt(month));
			instance.set(Calendar.YEAR,Integer.parseInt(year));
			return instance.getTime();
		}
		return null;
	}


	/**
	 * @param licenseExpiryMonth
	 * @return
	 */
	public static boolean notEmpty(String value) {
		return (value != null && !value.trim().equals(""));
	}

    public Hashtable<String,Object> getParameters(HttpSession session,HttpServletRequest request) {
        Hashtable<String,Object> parameters = new Hashtable<String,Object>();

        boolean advanced = false;
        if(!Util.empty(request.getParameter("advanced"))) {
            advanced = Boolean.parseBoolean(request.getParameter("advanced"));
        }
        parameters.put("advanced",new Boolean(advanced));
            
        parameters.put("status",getValue(request,"status",String.valueOf(DriverData.ACTIVE)));
        if(advanced && (session.getAttribute("driverForm") != null)) {
            parameters.put("pageNum",((DriverAdvancedSearchForm)
            		session.getAttribute("driverForm")).getPageNum());
        }else {
            parameters.put("pageNum",getValue(request,"pageNum","1"));
        }
        parameters.put("attribute",getValue(request,"attribute","firstorlastName"));
        parameters.put("temp",getValue(request,"temp","0"));
        parameters.put("value",getValue(request,"value",""));
        
        String pageSize = "10";
        if(advanced && session.getAttribute("driverForm") != null){
        	pageSize = ((DriverAdvancedSearchForm)session.getAttribute("driverForm")).getCount();
        	if (!Util.isNumber(pageSize) || (new Integer(pageSize)) < 1 ){
        		pageSize = "10";
        	}
        }
        parameters.put("pageSize",getValue(request,"pageSize",pageSize));
        
        if(Util.empty(request.getParameter("all"))) {
            parameters.put("all",new Boolean(false));
        }else {
            parameters.put("all",new Boolean(request.getParameter("all")));
        }
        
        if (Util.empty(request.getParameter("temporal"))){
        	parameters.put("temporal", new Boolean(false));
        }else{
        	parameters.put("temporal",new Boolean(request.getParameter("temporal")));
        }
       	parameters.put("corporationid", getValue(request, "corporationid", ""));
        return parameters;
    }
    
    private String getValue(HttpServletRequest request,String parameter,String defaultValue) {
        if(!Util.empty(request.getParameter(parameter))){
            return request.getParameter(parameter);
        }else {
            return defaultValue;
        }
    }

    public String isChecked(Hashtable<String,Object> parameters,int value) {
        if(parameters.containsKey("status") && Integer.parseInt((String)parameters.get("status")) == value) {
            return "checked";
        }
        return "";
    }
    
    public String isSelected(Hashtable<String,Object> parameters,int value, String field) {
        if(parameters.containsKey(field) && Integer.parseInt((String)parameters.get(field)) == value) {
            return "selected";
        }
        return "";
    }

    public String getAdvancedLink(Hashtable<String,Object> parameters) {
        String link = "layout.jsp?refer=/driver/DriverList.jsp?advanced=true";
        Enumeration<String> keys = parameters.keys();
        while(keys.hasMoreElements()) {
            String key = (String)keys.nextElement();
            if(!key.equals("advanced")) {
                link += "&"+key+"="+String.valueOf(parameters.get(key));
            }
        }
        return link;
    }
    
    /**
     * Devuelve la busqueda avanzada para vincular conductores temporales 
     * a conductores pre-existentes en el Datacenter.
     * @param parameters
     * @return
     */
    public String getTemporalAdvancedLink(Hashtable<String,Object> parameters) {
        String link = "layout.jsp?refer=/driver/TemporalDriverList.jsp?advanced=true";
        Enumeration<String> keys = parameters.keys();
        while(keys.hasMoreElements()) {
            String key = (String)keys.nextElement();
            if(!key.equals("advanced")) {
                link += "&"+key+"="+String.valueOf(parameters.get(key));
            }
        }
        return link;
    }

    /**
	 * Valida que no exista el idDallas.
	 * El idDallas es un identificador unico para mapear DataCenter/Telematic
	 * @param sys
	 * @param data
	 * @param userSessionData
	 * @return
	 * @throws Exception
	 */
	 public static String idCorporationExist(SystemAccess sys,DriverForm data) throws Exception {
        if (data.getCorporationId() != null){
        	if (sys.getDriverReportFacade().idCorporationExist(data.getId(),data.getCorporationId(),sys.getUserSessionData()))
        		return "driver.error.idcorporationalreadyexist"; 
        }
        return null;
	}
}
