package presentation.translator.web.travel;


import java.util.Calendar;
import java.util.Collection;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverData;
import communication.travel.tables.TravelDataType;
import communication.vehicle.tables.VehicleData;

/**
 * @author jrodriguez
 */
public class TravelForm extends DriverDivOrgForm {

    private static final long serialVersionUID = 1L;

    private String id;
    private String status;
	private String driverid;
	private String mobile;
    private String city;
    private String hitrun;
    private String state;
    private String collisionangle;

    private String vehicleId;

    private String vehicleplate;
    private String vehicletrade;
    private String vehiclemodel;
    private String vehicleyear;

    private String localLeave;
    private String localArrive;
    private String stops;
    private String distance;
    
    private String date;
	private String month;
	private String year;

    private String hour;  
    private String minute;

    private String hour2;  
    private String minute2;
	
    private String othervehicles;
    private String supervisionObservations;
	
    private String description;
   	
   	public TravelForm() {
    }
    
    /**
     * @param driverid
     */
    public TravelForm(DriverData driver) {
        if(driver != null) {
            this.driverid = String.valueOf(driver.getId());
        }
    }

    /**
     * @param driverid
     */
    public TravelForm(TravelDataType travel,UserSessionData userSessionData) {
        this.id = String.valueOf(travel.getId());
        this.driverid = String.valueOf(travel.getDriver().getId());
        this.mobile = travel.getMobile();
       
        this.hitrun = travel.getRadio();
        this.state = travel.getCharge();
        this.collisionangle = travel.getOther();
        
        if(travel.getVehicle() != null) {
            vehicleId = String.valueOf(travel.getVehicle().getId());            
        }else {
            vehicleplate = travel.getPlate();
            vehicletrade = travel.getTrade();
            if(vehicletrade.equals("Sin Datos")) {
                vehicletrade = "generic.messages.nodata";
            }
            vehiclemodel = travel.getModel();
            vehicleyear = travel.getColor();
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(travel.getDateLeave());
        date = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        year = String.valueOf(calendar.get(Calendar.YEAR));
        
        localArrive = travel.getLocalArrive(); 
        localLeave = travel.getLocalLeave(); 
        if(travel.getStops() != null) {
        	stops = String.valueOf(travel.getStops());
        }
        if(travel.getDistance() != null) {
        	distance = String.valueOf(travel.getDistance());
        }

        if(travel.getHourLeave() != null) {
            calendar.setTime(travel.getHourLeave());
            hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));  
            minute = String.valueOf(calendar.get(Calendar.MINUTE));
        }
        if(travel.getHourArrive() != null) {
            calendar.setTime(travel.getHourArrive());
            hour2 = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));  
            minute2 = String.valueOf(calendar.get(Calendar.MINUTE));
        }
        
        this.othervehicles = travel.getRotogram();
        this.supervisionObservations = travel.getLocalNames();
        this.description = travel.getComment();
    }

    public String validate() {
        if (vehicleId.equals("-1")) {
        	if(Util.empty(vehicleplate)) {
        		return "travel.error.invalidplate";
        	}
        	if(Util.empty(vehicletrade)) {
        		return "travel.error.invalidtrade";
        	}
        	if(Util.empty(vehiclemodel)) {
        		return "travel.error.invalidmodel";
        	}
        }
    	if(!Util.checkDate(date.trim(), month.trim(), year.trim())) {
    		return "travel.error.invaliddate";
    	}
    	if(Util.empty(hour) || Util.empty(minute) || !Util.checkHour(hour, minute)) {
    		return "travel.error.invalidleavehour";
    	}
    	if(Util.empty(hour2) || Util.empty(minute2) || !Util.checkHour(hour2, minute2)) {
    		return "travel.error.invalidarrivehour";
    	}
    	if(Util.empty(stops)) {
    		stops = "0";
    	}
    	if(!Util.isNumber(stops)) {
    		return "travel.error.invalidstops";
    	}
    	return null;	
    }
    
    public TravelDataType getTravelData(SystemAccess sys) {
    	TravelDataType travel = new TravelDataType();
    	
        if(!Util.empty(id)) {
            travel.setId(new Integer(id.trim()));
        }
        
        travel.setMobile(mobile);
        travel.setCorporation(new CorporationData(sys.getUserSessionData().getFilter().getCorporationId(), null, null, null, null, null));
        travel.setDriver(new DriverData(new Integer(driverid.trim())));
        
        if (!vehicleId.equals("-1")) {
        	travel.setVehicle(new VehicleData(new Integer(vehicleId.trim())));
        } else {
        	travel.setPlate(vehicleplate);
        	travel.setTrade(vehicletrade);
        	travel.setModel(vehiclemodel);
        	travel.setColor(vehicleyear.trim());
        }
        
        Collection list = Util.getDivOrgs(this,sys.getUserSessionData());
        travel.setDivOrgs(list);
        
        travel.setRadio(hitrun);
        travel.setCharge(state);
        travel.setOther(collisionangle);

        
        travel.setDateLeave(Util.getDate(date.trim(),month.trim(),year.trim()));
        travel.setHourLeave(Util.getTime(hour.trim(),minute.trim()));
        travel.setHourArrive(Util.getTime(hour2.trim(),minute2.trim()));

        
        travel.setLocalArrive(localArrive); 
        travel.setLocalLeave(localLeave); 
        if(!Util.empty(stops)) {
        	travel.setStops(new Integer(stops));
        }
       	travel.setDistance(distance);
        
        travel.setRotogram(othervehicles);
        travel.setLocalNames(supervisionObservations);
        travel.setComment(description);
        
        return travel;
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDriverid() {
		return driverid;
	}

	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHitrun() {
		return hitrun;
	}

	public void setHitrun(String hitrun) {
		this.hitrun = hitrun;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCollisionangle() {
		return collisionangle;
	}

	public void setCollisionangle(String collisionangle) {
		this.collisionangle = collisionangle;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleplate() {
		return vehicleplate;
	}

	public void setVehicleplate(String vehicleplate) {
		this.vehicleplate = vehicleplate;
	}

	public String getVehicletrade() {
		return vehicletrade;
	}

	public void setVehicletrade(String vehicletrade) {
		this.vehicletrade = vehicletrade;
	}

	public String getVehiclemodel() {
		return vehiclemodel;
	}

	public void setVehiclemodel(String vehiclemodel) {
		this.vehiclemodel = vehiclemodel;
	}

	public String getVehicleyear() {
		return vehicleyear;
	}

	public void setVehicleyear(String vehicleyear) {
		this.vehicleyear = vehicleyear;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getHour2() {
		return hour2;
	}

	public void setHour2(String hour2) {
		this.hour2 = hour2;
	}

	public String getMinute2() {
		return minute2;
	}

	public void setMinute2(String minute2) {
		this.minute2 = minute2;
	}

	public String getOthervehicles() {
		return othervehicles;
	}

	public void setOthervehicles(String othervehicles) {
		this.othervehicles = othervehicles;
	}

	public String getSupervisionObservations() {
		return supervisionObservations;
	}

	public void setSupervisionObservations(String supervisionObservations) {
		this.supervisionObservations = supervisionObservations;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocalLeave() {
		return localLeave;
	}

	public void setLocalLeave(String localLeave) {
		this.localLeave = localLeave;
	}

	public String getLocalArrive() {
		return localArrive;
	}

	public void setLocalArrive(String localArrive) {
		this.localArrive = localArrive;
	}

	public String getStops() {
		return stops;
	}

	public void setStops(String stops) {
		this.stops = stops;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
    
}
