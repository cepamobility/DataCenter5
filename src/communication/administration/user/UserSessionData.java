package communication.administration.user;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import business.SystemAccess;

import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.administration.RoleData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.CountryData;
import communication.administration.corporation.tables.LabelItemData;
import communication.driver.tables.DriverData;
import communication.security.SecurityConstants;
import communication.util.Filter;
import communication.util.ListResult;
import communication.vehicle.tables.VehicleCheckData;
import communication.vehicle.tables.VehicleData;
import communication.vehicle.tables.VehicleMaintenanceData;

/**
 * <p>
 * </p>
 */
public class UserSessionData implements Serializable{
	
    private static final long serialVersionUID = 1L;

    private Filter filter;
	private String lenguage;
    private Hashtable permissions;
    private RoleData role;
	private int divOrgCount;
	private DriverData driver;
    private boolean multipleCorporation;
    private boolean horizontalAccess;
    
    private String url;
    private String msg;

	private Object value;

	private VehicleCheckData vehicleCheck;
    
    
    
	public boolean isHorizontalAccess() {
		return horizontalAccess;
	}

	public void setHorizontalAccess(boolean horizontalAccess) {
		this.horizontalAccess = horizontalAccess;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Constructor por defecto;
	 */   
	public UserSessionData() {
		
	}
	
	/**
	 * @return Returns the divOrgCount.
	 */
	public int getDivOrgCount() {
		return filter.getDivOrgCount();
	}

	/**
	 * @param divOrgCount The divOrgCount to set.
	 */
	public void setDivOrgCount(int divOrgCount) {
		this.divOrgCount = divOrgCount;
	}

	public Filter getFilter() {
		return filter;
	}
	
	public void setFilter(Filter filter) {
		this.filter = filter;
	}
	
	public String getLenguage() {
		return lenguage;
	}
	
	public void setLenguage(String lenguage) {
		this.lenguage = lenguage;
	}

    /**
     * @param permissions
     */
    public void setPermissions(Hashtable permissions) {
        this.permissions = permissions;
    }

    /**
     * @return true if the logged user has permissions for action.
     */
    public boolean checkPermission(String action) {
        return (permissions.containsKey(action) || permissions.containsKey(SecurityConstants.ADMINISTRATOR));
    }
    
    /**
     * @return true if the logged user has permissions for action.
     */
    public boolean hasPermission(String action) {
        return permissions.containsKey(action);
    }
    
    public Set getPermissions() {
        return permissions.keySet();
    }

    public Collection getPermissionsData() {
        Enumeration en = permissions.elements();
        Collection elements = new LinkedList();
        while(en.hasMoreElements()) {
            elements.add(en.nextElement());
        }
        return elements;
    }

    public boolean isCepaUser() {
        return role.isCepaUser();
    }
        
    public boolean isAccountManager()
    {
    	return role.isAccountManager();
    }


    public boolean isAdministrator()
    {
    	return role.isAdministrator();
    }

    public boolean isDPACheckList() {
        return role.isDPACheckList();
    }

    public boolean isMobileCheckList() {
        return role.isMobileCheckList();
    }

    public boolean isInternalCheckList() {
        return role.isInternalCheckList();
    }
    
    public boolean isContractor() {
        return role.isContractor();
    }

    /*
     * Conductores
     */
    public boolean isDriver() {
        return role.isDriver() || role.isFleetDriver();
    }
    
    public boolean isFleetUser() {
        return role.isFleetDriver();
    }

    /* 
     * Administrador de Monsantos:
     * 	Tiene todos los permisos de actividades pero solo para Commentary Drive. 		
     */    
    public boolean isMonsantoAdministrator()
    {
    	return role.isMonsantoAdministrator();
    }
    
    /* 
     * Instructor de Cepa     
     */
    public boolean isInstructor()
    {
    	return role.isInstructor();
    }
   
    public boolean isInstructorDPA()
    {
    	return role.isInstructorDPA();
    }
   
    public boolean isInstructorActivity()
    {
    	return role.isInstructorActivity();
    }
    
    /* 
     * Gerente     
     */
    public boolean isGerente()
    {
    	return role.isGerente();
    }
       
   
    /* 
     * Gerente     
     */
    public boolean isConsulting()
    {
    	return role.isConsulting();
    }
       
   

    /**
     * @return Returns the role.
     */
    public RoleData getRole() {
        return role;
    }

    /**
     * @param role The role to set.
     */
    public void setRole(RoleData role) {
        this.role = role;
    }

    public DriverData getDriverData() {
        return driver;
    }

    public void setDriver(DriverData data) {
        driver = data;
    }
    
    public boolean isForest() {
        return filter.isForest();
    }

    public LabelItemData getLabelItemData(int divOrg,Integer lastLevel) {
        return filter.getLabelItemData(divOrg,lastLevel);
    }

    public boolean isMultipleCorporation() {
        return multipleCorporation;
    }

    public void setMultipleCorporation(boolean multipleCorporation) {
        this.multipleCorporation = multipleCorporation;
    }

    public boolean hasSecondEventSupervision(Integer country) {
    	 /*if(filter.getCorporationId().intValue() == CorporationData.MONSANTO) {
    		 return country.equals(CountryData.BRASIL_ID) || country.equals(CountryData.ARGENTINA_ID) || 
    		 country.equals(CountryData.MEXICO_ID) || country.equals(CountryData.COLOMBIA_ID) || 
    		 country.equals(CountryData.COSTA_RICA_ID) || country.equals(CountryData.GUATEMALA_ID) || 
    		 country.equals(CountryData.NICARAGUA_ID) || country.equals(CountryData.CHILE_ID) || 
    		 country.equals(CountryData.VENEZUELA_ID) || country.equals(CountryData.PERU_ID) || country.equals(CountryData.ECUADOR_ID) || 
    		 country.equals(CountryData.EL_SALVADOR_ID) || country.equals(CountryData.HONDURAS_ID);
    	 }*/
    	 return false;
    }

	public boolean isCepaSystemUser() {
		return role.isCepaSystemUser();
	}

	public String getFullName() {
		return filter.getFirstName()+" "+filter.getLastName();
	}

	public boolean[] checkDriverAlerts(SystemAccess sys) { 	
	    boolean licenseExpiry = false;
	    boolean activityExpiry = true;
	    boolean maintenance = true;
		try {
		    if(driver.getLicenseExpiry() != null) {
		    	Calendar expiry = Calendar.getInstance();
		    	expiry.setTime(driver.getLicenseExpiry());
		    	expiry.add(Calendar.MONTH,2);
		    	licenseExpiry = Calendar.getInstance().before(expiry);
		    }
		    Collection list = sys.getActivityReportFacade().findActivityByDriver(driver.getId(),this,true);
		    Iterator it = list.iterator();
		    while(it.hasNext()) {
		    	CEPAActivityScheduleRegistryData activity = (CEPAActivityScheduleRegistryData)it.next();
		    	String type = activity.getActivity().getType().getResourceBundleId();
		    	if(type.equals(CEPAActivityConstants.BTW1) || type.equals(CEPAActivityConstants.BTW2) || type.equals(CEPAActivityConstants.OUT_TRAINING)) {
		    		if(activity.getActivity().getDateExpiry() != null) {
				    	Calendar expiry = Calendar.getInstance();
				    	expiry.setTime(activity.getActivity().getDateExpiry());
				    	expiry.add(Calendar.MONTH,2);
				    	if(expiry.before(Calendar.getInstance())){
				    		activityExpiry = false;
				    	}
		    		}
		    	}
		    }
			ListResult result = sys.getVehicleReportFacade().findVehicleList("matricula","",0,-1,VehicleData.ACTIVE,this);
			if(result.getTotal() > 0) {
	    		it = result.getElements().iterator();
	    		while(it.hasNext()) {
	    		    VehicleData vehicle = (VehicleData)it.next();
	    		    Collection maintenances = sys.getVehicleReportFacade().getVehicleMaintenances(vehicle.getId(), this);
	    		    if(maintenances.size() > 0) {
	    		    	VehicleMaintenanceData lastMaintenance = (VehicleMaintenanceData)maintenances.iterator().next();
				    	Calendar lastMaintenanceDate = Calendar.getInstance();
				    	lastMaintenanceDate.setTime(lastMaintenance.getDate());
	    		    	Collection odometers = sys.getFleetReportFacade().findVehicleOdometers(vehicle.getId(),this);
	    		    	Iterator odometersIt = odometers.iterator();
	    		    	int maintenanceOdometer = -1;
	    		    	int lastOdometer = -1;
	    		    	if(odometersIt.hasNext()) {
	    		    		Object[] data = (Object[])odometersIt.next(); 
	    		    		lastOdometer = ((Integer)data[0]).intValue();
	    		    		if((((Integer)data[2]).intValue() < lastMaintenanceDate.get(Calendar.YEAR)) ||
	    		    		(((Integer)data[2]).intValue() == lastMaintenanceDate.get(Calendar.YEAR) && ((Integer)data[1]).intValue() <= lastMaintenanceDate.get(Calendar.MONTH+1))) {
	    		    			maintenanceOdometer = ((Integer)data[0]).intValue();
	    		    		}
	    		    	}
	    		    	while(maintenanceOdometer == -1 && odometersIt.hasNext()) {
	    		    		Object[] data = (Object[])odometersIt.next(); 
	    		    		if((((Integer)data[2]).intValue() < lastMaintenanceDate.get(Calendar.YEAR)) ||
	    	    		    	(((Integer)data[2]).intValue() == lastMaintenanceDate.get(Calendar.YEAR) && ((Integer)data[1]).intValue() <= lastMaintenanceDate.get(Calendar.MONTH+1))) {
	    	    		    	maintenanceOdometer = ((Integer)data[0]).intValue();
	    	    		    }
	    		    	}
	    		    }
	    		}
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		boolean[] values = {licenseExpiry,activityExpiry,maintenance};
		return values;
	}

	public boolean isRedirectOdometer() {
		if(role.isDriver()) {
			int corporation = getFilter().getCorporationId().intValue();
			switch(corporation) {
				case CorporationData.MONSANTO:
					String country = driver.getCountryName();
					return (country.equals(CountryData.ARGENTINA) || country.equals(CountryData.PARAGUAY) || country.equals(CountryData.BOLIVIA));
			}
		}
		return false;
	}

	public int getCorporationId() {
		return filter.getCorporationId().intValue();
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public boolean isReporterJJ() {
        return role.isReporterJJ();
	}

	public boolean isForestalUnified() {
		int id = filter.getCorporationId().intValue();
		return (id == CorporationData.FORESTALORIENTAL || id == CorporationData.WEYERHAEUSER || id == CorporationData.MONTES_DE_PLATA);
	}

	public boolean canModifyActivityRegitry(String activity) {
		return !(isForestalUnified() && CEPAActivityConstants.isForestalCommonActivity(activity));
	}

	public boolean isCorporation(int[] corporations) {
		for(int i = 0; i < corporations.length; i++) {
			if(corporations[i] == CorporationData.JJ) {
				if(CorporationData.JJ == filter.getCorporationId().intValue() || CorporationData.KENVUE == filter.getCorporationId().intValue()) {
					return true;
				}
			}else if(corporations[i] == CorporationData.CONTRACTOR_FORESTAL) {
				return (CorporationData.FRAYLOG == filter.getCorporationId().intValue() || CorporationData.SAN_NICOLAS == filter.getCorporationId().intValue()
						 || CorporationData.SOCTMA == filter.getCorporationId().intValue());
			}else {
				if(corporations[i] == filter.getCorporationId().intValue()) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isCorporation(int corporation) {
		if(corporation == CorporationData.JJ) {
			return (CorporationData.JJ == filter.getCorporationId().intValue() || CorporationData.KENVUE == filter.getCorporationId().intValue());
		}else if(corporation == CorporationData.CONTRACTOR_FORESTAL) {
			return (CorporationData.FRAYLOG == filter.getCorporationId().intValue() || CorporationData.SAN_NICOLAS == filter.getCorporationId().intValue()
					 || CorporationData.SOCTMA == filter.getCorporationId().intValue());
		}else {
			return corporation == filter.getCorporationId().intValue();
		}
	}

	public boolean isBrasilCorporation() {
		return isCorporation(new int[]{CorporationData.ASTRAZENECA,CorporationData.ALLERGAN,CorporationData.BAYER,CorporationData.COVESTRO,CorporationData.CEPA,CorporationData.CHEMTURA,CorporationData.DOW,CorporationData.MONSANTO,CorporationData.SYNGENTA,CorporationData.SYNGENTA_BR,CorporationData.ECOLAB});
	}

	public boolean isPointCorporation() {
		return isCorporation(new int[]{CorporationData.BAYER_MX,CorporationData.MONTES_DE_PLATA});
	}

	public void setVehicleCheck(VehicleCheckData vehicleCheck) {
		this.vehicleCheck = vehicleCheck;
	}

	public VehicleCheckData getVehicleCheck() {
		return vehicleCheck;
	}
}

