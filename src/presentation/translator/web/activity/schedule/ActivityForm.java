/*
 * Created on 24-feb-2006
 */
package presentation.translator.web.activity.schedule;

import java.util.Calendar;
import java.util.Date;

import org.apache.struts.upload.FormFile;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;
import communication.activity.tables.CEPAActivityConstants;
import communication.administration.user.UserSessionData;
/**
 * @author adocarmo
 */
public class ActivityForm extends DriverDivOrgForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
    
	private String type;
	private String day;
	private String month;
	private String year;	
	private String state;
	//private String country;	
	private String city;		
	private String observations; // observaciones de la actividad
	private String comment;	 // comentarios generales
	
	private String isOld = "false";

	private String participants;

	private String theme;	//tema de charlas de seguridad
	private String durHour;
	private String durMinute;
		
	private FormFile fileUpload;
	private String file;
	

    /**
	 * @return the isOld
	 */
	public String getIsOld() {
		return isOld;
	}

	/**
	 * @param isOld the isOld to set
	 */
	public void setIsOld(String isOld) {
		this.isOld = isOld;
	}

	/**
	 * @return Returns the comment.
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment The comment to set.
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
     * @param name
     * @param i
     * @param j
     */
    public void setDivOrg(String name, int i, int j) {
        switch(i) {
            case 1:
                switch(j) {
                    case 1:
                        divOrg11 = name;
                        break;
                    case 2:
                        divOrg12 = name;
                        break;
                    case 3:
                        divOrg13 = name;
                        break;
                }
                break;
            case 2:
                switch(j) {
                    case 1:
                        divOrg21 = name;
                        break;
                    case 2:
                        divOrg22 = name;
                        break;
                    case 3:
                        divOrg23 = name;
                        break;
                }
                break;
            case 3:
                switch(j) {
                    case 1:
                        divOrg31 = name;
                        break;
                    case 2:
                        divOrg32 = name;
                        break;
                    case 3:
                        divOrg33 = name;
                        break;
                }
                break;
        }
    }

    public String getDivOrg(int i, int j) {
        switch(i) {
            case 1:
                switch(j) {
                    case 1:
                        return divOrg11;
                    case 2:
                        return divOrg12;
                    case 3:
                        return divOrg13;
                }
            case 2:
                switch(j) {
                    case 1:
                        return divOrg21;
                    case 2:
                        return divOrg22;
                    case 3:
                        return divOrg23;
                }
            case 3:
                switch(j) {
                    case 1:
                        return divOrg31;
                    case 2:
                        return divOrg32;
                    case 3:
                        return divOrg33;
                }
        }
        return null;
    }
    
    public Date getDate() {
    	if (isEmpty(day) || isEmpty(month) || isEmpty(year)) {
    		return null;
    	} else {
        	Calendar calendar = Calendar.getInstance();
    		calendar.set(Calendar.DATE,Integer.parseInt(day.trim()));
    		//calendar.set(Calendar.MONTH,Integer.parseInt(month.trim()));
            calendar.set(Calendar.MONTH,Integer.parseInt(month.trim())-1);    		
    		calendar.set(Calendar.YEAR,Integer.parseInt(year.trim()));
            return calendar.getTime();
    	}
    }
    
	public FormFile getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(FormFile fileUpload) {
		this.fileUpload = fileUpload;
	}

	/*
    public String validate(UserSessionData userRequest) {

	    if(isEmpty(getMatricula())) {
	        return "generic.vehicle.vehicledata.registrationNumberInvalid";
	    }
	    if(isEmpty(getUse())) {
	        return "generic.vehicle.vehicledata.useInvalid";
	    }
	    if(isEmpty(getType())) {
	        return "generic.vehicle.vehicledata.typeInvalid";
	    }	    
	    if(isEmpty(getTrade())) {
	        return "generic.vehicle.vehicledata.tradeInvalid";
	    }	    
	    if(isEmpty(getModel())) {
	        return "generic.vehicle.vehicledata.modelInvalid";
	    }	    	    
  	    
	    if(isEmpty(getYear()) || notNumber(getYear().trim())) {
	        return "generic.vehicle.vehicledata.yearInvalid";
	    }
	    if(! isEmpty(getPermissionNumber()) && notNumber(getPermissionNumber().trim())) {
	        return "generic.vehicle.vehicledata.permissionNumberInvalid";
	    }
	    if(! isEmpty(getChasisNumber()) && notNumber(getChasisNumber().trim())) {
	        return "generic.vehicle.vehicledata.chasisNumberInvalid";
	    }	    
	    if(! isEmpty(getTankCapacity()) && notNumber(getTankCapacity().trim())) {
	        return "generic.vehicle.vehicledata.tankCapacityInvalid";
	    }	    
	    
	    if (anyEmpty(purchaseDay,purchaseMonth,purchaseYear)) 
	    	return "generic.vehicle.vehicledata.purchaseDateInvalid";
	    else {
		    if((! isEmpty(purchaseDay) && notNumber(purchaseDay.trim())) || 
		    		(! isEmpty(purchaseMonth) && notNumber(purchaseMonth.trim()) ||
		    		(! isEmpty(purchaseYear) && notNumber(purchaseYear.trim())))) {
		    	return "generic.vehicle.vehicledata.purchaseDateInvalid";
		    }
	    }

	    if (anyEmpty(permissionExpiryDay,permissionExpiryMonth,permissionExpiryYear))
	    	return "generic.vehicle.vehicledata.permissionExpiryDateInvalid";
	    else {
	    	if((! isEmpty(permissionExpiryDay) && notNumber(permissionExpiryDay.trim())) || 
	    			(! isEmpty(permissionExpiryMonth) && notNumber(permissionExpiryMonth.trim()) ||
	    			(! isEmpty(permissionExpiryYear) && notNumber(permissionExpiryYear.trim())))) {
	    		return "generic.vehicle.vehicledata.permissionExpiryDateInvalid";
	    	}	
	    }	    
	    
	    Collections.sort((List)userRequest.getFilter().getUserDivOrgDataSet());
        Iterator it = userRequest.getFilter().getUserDivOrgDataSet().iterator();
        int index = 1;
        while(it.hasNext()) {
            UserDivOrgData userDivOrgData = (UserDivOrgData)it.next();
            System.out.println("DIV VALIDATOR:" + userDivOrgData.getDivOrgAttribute().getName());
            for(int i = 1; i <= userDivOrgData.getNroLevel(); i++) {
                if(Util.empty(getDivOrg(index,i))) {
                    return "generic.vehicle.vehicledata.emptyDivOrg";
                }
            }
            index++;
        }

        if(isEmpty(getCountry1())) {
            return "generic.vehicle.vehicledata.emptyDivGeo";
        }
        if(userRequest.getFilter().getUserGeoLabelData().getLevelCount()>1 && isEmpty(getCountry2())) {
            return "generic.vehicle.vehicledata.emptyDivGeo";
        }
        if(userRequest.getFilter().getUserGeoLabelData().getLevelCount()>2 && isEmpty(getCountry3())) {
            return "generic.vehicle.vehicledata.emptyDivGeo";
        }
  
	    return null;
	}   
*/	
    private boolean isEmpty(String value) {
    	System.out.println(value);
	    return ((value == null) || (value.trim().length()==0));
	}	    
    
    private boolean notNumber(String value) {
    	
	    try {
	    	new Integer(value.trim());
	    }
	    catch (Exception e) {
	    	return true;
	    }	
	    return false;
	    }
    
    private boolean anyEmpty(String value1,String value2,String value3) {
    	int any = 0;
    	if ((value1 == null) || (value1.trim().length()==0))
    		any ++;
    	if ((value2 == null) || (value2.trim().length()==0))
    		any ++;
    	if ((value3 == null) || (value3.trim().length()==0))
    		any ++;
	    //return ((any > 0 && any < 3));
    	return (any > 0);
	}

	/**
	 * @return Returns the country.
	 */
	/*public String getCountry() {
		return country;
	}*/

	/**
	 * @param country The country to set.
	 */
	/*public void setCountry(String country) {
		this.country = country;
	}*/

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id The idActivity to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Returns the permissionExpiryDay.
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param permissionExpiryDay The permissionExpiryDay to set.
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * @return Returns the permissionExpiryMonth.
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param permissionExpiryMonth The permissionExpiryMonth to set.
	 */
	public void setMonth(String permissionExpiryMonth) {
		this.month = permissionExpiryMonth;
	}

	/**
	 * @return Returns the permissionExpiryYear.
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param permissionExpiryYear The permissionExpiryYear to set.
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Obtiene el tipo de Actividad
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}	
	
	public String validate(UserSessionData userRequest) {

	    if(isEmpty(getType())) {
	        return "generic.activity.activitydata.typeInvalid";
	    }	    	    
		if(isEmpty(getState())) {
	        return "generic.activity.activitydata.stateInvalid";
	    }
	    if(isEmpty(getCity())) {
	        return "generic.activity.activitydata.cityInvalid";
	    }	    	    
	    
	    if(!isEmpty(durMinute) && (!Util.isNumber(durMinute) || Integer.parseInt(durMinute) > 59)) {
	        return "generic.activity.activitydata.durationInvalid";
	    }	    	    

	    if(!isEmpty(durHour) && !Util.isNumber(durHour)) {
	        return "generic.activity.activitydata.durationInvalid";
	    }	    	    

	    // Fecha válida
	    if (anyEmpty(day,month,year)) 
	    	return "generic.activity.activitydata.dateInvalid";
	    else {
		    if((! isEmpty(day) && notNumber(day.trim())) || 
		    		(! isEmpty(month) && notNumber(month.trim()) ||
		    		(! isEmpty(year) && notNumber(year.trim())))) {
		    	return "generic.activity.activitydata.dateInvalid";
		    }
	    }
	    if (!Util.checkDate(Integer.parseInt(day),Integer.parseInt(month),Integer.parseInt(year))) {
	    	return "generic.activity.activitydata.dateInvalid";
	    }	    
	    
	    if (! getType().equals(CEPAActivityConstants.COMMENTARYDRIVE) &&
	    		! getType().equals(CEPAActivityConstants.COMMENTARYDRIVEARGENTINA) &&
	    		! getType().equals(CEPAActivityConstants.COMMENTARYDRIVEHIGHRISK)
	    		) {
	    	for(int j = 0; j < userRequest.getFilter().getDivOrgCount(); j++) {
	    		for(int i = 1; i <= userRequest.getFilter().getDivOrgLevel(j); i++) {
	    			if(Util.empty(getDivOrg(j+1,i))) {
	    				return "generic.vehicle.vehicledata.emptyDivOrg";
	    			}
	    		}
	    	}

	    	if(isEmpty(getCountry1())) {
	    		return "generic.vehicle.vehicledata.emptyDivGeo";
	    	}
	    	if(userRequest.getFilter().getGeoLevelCount() > 1 && isEmpty(getCountry2())) {
	    		return "generic.vehicle.vehicledata.emptyDivGeo";
	    	}
	    	if(userRequest.getFilter().getGeoLevelCount() > 2 && isEmpty(getCountry3())) {
	    		return "generic.vehicle.vehicledata.emptyDivGeo";
	    	}
	    }
	    if(isEmpty(getComment())) {
	    	// return "generic.activity.activitydata.commentInvalid";
	    }
	    	
	    return null;
	}

	/**
	 * @return the observations
	 */
	public String getObservations() {
		return observations;
	}

	/**
	 * @param observations the observations to set
	 */
	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getParticipants() {
		return participants;
	}

	public void setParticipants(String participants) {
		this.participants = participants;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getDurHour() {
		return durHour;
	}

	public void setDurHour(String durHour) {
		this.durHour = durHour;
	}

	public String getDurMinute() {
		return durMinute;
	}

	public void setDurMinute(String durMinute) {
		this.durMinute = durMinute;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}   
	
	
	
}	    
