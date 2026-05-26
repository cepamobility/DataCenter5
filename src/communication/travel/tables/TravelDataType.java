/**
 * Created on 26-abr-2006
 * CEPA
 * DataCenter 5
 */
package communication.travel.tables;

import java.io.Serializable;
import java.sql.Time;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import communication.administration.corporation.tables.CorporationData;
import communication.driver.tables.DriverData;
import communication.vehicle.tables.VehicleData;

public class TravelDataType implements Serializable, Comparable {

	public static final int PENDING = 1;
	public static final int STARTED = 2;
	public static final int FINALIZED = 3;
	
    protected Integer id;
    protected DriverData driver;
	private String mobile;

	private CorporationData corporation;
    protected Integer status;
    
    protected VehicleData vehicle;
    protected String plate;
    protected String trade;
    protected String model;
    protected String color;

    private Collection divOrgs;
   	private String charge;
   	private String radio;
   	private String other;

    private String localLeave;
    private String localArrive;
	private Date dateLeave;
	private Time hourLeave;
	private Integer stops;
	private String distance;
	private Time hourArrive;

	private String rotogram;
	private String localNames;
	private String comment;
	
	private Date confirmedLeave;
	private Time confirmedLeaveHour;
	private String communicationLeave;
	private String monitor;
	private String leaveComment;
    
	private Date closeDate;
	private Time closeHour;
	private String closeLocal;
	private String closeWay;
	private boolean travelResult;
	private String closeCommunication;
	private String closeComment;

	private Date registryDate;
	private Time registryHour;
	private String registryLocal;
	private String registryMonitor;
	private String registryComment;

	public TravelDataType() {
    }

    public TravelDataType(Integer id, DriverData driver, String mobile, Integer status,
			VehicleData vehicle, String plate, String trade, String model,
			String color, Collection divOrgs, String charge, String radio,
			String other, String localLeave, String localArrive,
			Date dateLeave, Time hourLeave, Integer stops, String distance,
			Time hourArrive, String rotogram, String localNames, String comment) {
		super();
		this.id = id;
		this.driver = driver;
		this.mobile = mobile;
		this.status = status;
		this.vehicle = vehicle;
		this.plate = plate;
		this.trade = trade;
		this.model = model;
		this.color = color;
		this.divOrgs = divOrgs;
		this.charge = charge;
		this.radio = radio;
		this.other = other;
		this.localLeave = localLeave;
		this.localArrive = localArrive;
		this.dateLeave = dateLeave;
		this.hourLeave = hourLeave;
		this.stops = stops;
		this.distance = distance;
		this.hourArrive = hourArrive;
		this.rotogram = rotogram;
		this.localNames = localNames;
		this.comment = comment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DriverData getDriver() {
		return driver;
	}

	public void setDriver(DriverData driver) {
		this.driver = driver;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public VehicleData getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleData vehicle) {
		this.vehicle = vehicle;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getTrade() {
		return trade;
	}

	public void setTrade(String trade) {
		this.trade = trade;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Collection getDivOrgs() {
		return divOrgs;
	}

	public void setDivOrgs(Collection divOrgs) {
		this.divOrgs = divOrgs;
	}

    public Iterator getDivOrgsIterator() {
        if(divOrgs == null) {
            return null;
        }
        Collections.sort((List)divOrgs);
        return divOrgs.iterator();
    }

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
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

	public Date getDateLeave() {
		return dateLeave;
	}

	public void setDateLeave(Date dateLeave) {
		this.dateLeave = dateLeave;
	}

	public Time getHourLeave() {
		return hourLeave;
	}

	public void setHourLeave(Time hourLeave) {
		this.hourLeave = hourLeave;
	}

	public Integer getStops() {
		return stops;
	}

	public void setStops(Integer stops) {
		this.stops = stops;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public Time getHourArrive() {
		return hourArrive;
	}

	public void setHourArrive(Time hourArrive) {
		this.hourArrive = hourArrive;
	}

	public String getRotogram() {
		return rotogram;
	}

	public void setRotogram(String rotogram) {
		this.rotogram = rotogram;
	}

	public String getLocalNames() {
		return localNames;
	}

	public void setLocalNames(String localNames) {
		this.localNames = localNames;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public CorporationData getCorporation() {
		return corporation;
	}

	public void setCorporation(CorporationData corporation) {
		this.corporation = corporation;
	}

	public int compareTo(Object arg0) {
		DriverData d = ((TravelDataType)arg0).getDriver();
		String name1 = d.getFirstName()+" "+d.getLastName();
		String name2 = driver.getFirstName()+" "+driver.getLastName();
		return name2.compareTo(name1);
	}

	public String getChargeValue() {
		return charge;
	}

	public void setConfirmedLeave(Date confirmedLeave) {
		this.confirmedLeave = confirmedLeave;
	}

	public void setCommunicationLeave(String communicationLeave) {
		this.communicationLeave = communicationLeave;
	}

	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}

	public void setLeaveComments(String leaveComment) {
		this.leaveComment = leaveComment;
	}

	public String getLeaveComment() {
		return leaveComment;
	}

	public void setLeaveComment(String leaveComment) {
		this.leaveComment = leaveComment;
	}

	public Date getConfirmedLeave() {
		return confirmedLeave;
	}

	public String getCommunicationLeave() {
		return communicationLeave;
	}

	public String getMonitor() {
		return monitor;
	}

	public Time getConfirmedLeaveHour() {
		return confirmedLeaveHour;
	}

	public void setConfirmedLeaveHour(Time confirmedLeaveHour) {
		this.confirmedLeaveHour = confirmedLeaveHour;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public Time getCloseHour() {
		return closeHour;
	}

	public void setCloseHour(Time closeHour) {
		this.closeHour = closeHour;
	}

	public String getCloseLocal() {
		return closeLocal;
	}

	public void setCloseLocal(String closeLocal) {
		this.closeLocal = closeLocal;
	}

	public String getCloseWay() {
		return closeWay;
	}

	public void setCloseWay(String closeWay) {
		this.closeWay = closeWay;
	}

	public boolean isTravelResult() {
		return travelResult;
	}

	public void setTravelResult(boolean travelResult) {
		this.travelResult = travelResult;
	}

	public String getCloseCommunication() {
		return closeCommunication;
	}

	public void setCloseCommunication(String closeCommunication) {
		this.closeCommunication = closeCommunication;
	}

	public String getCloseComment() {
		return closeComment;
	}

	public void setCloseComment(String closeComment) {
		this.closeComment = closeComment;
	}

	public Date getRegistryDate() {
		return registryDate;
	}

	public void setRegistryDate(Date registryDate) {
		this.registryDate = registryDate;
	}

	public Time getRegistryHour() {
		return registryHour;
	}

	public void setRegistryHour(Time registryHour) {
		this.registryHour = registryHour;
	}

	public String getRegistryLocal() {
		return registryLocal;
	}

	public void setRegistryLocal(String registryLocal) {
		this.registryLocal = registryLocal;
	}

	public String getRegistryMonitor() {
		return registryMonitor;
	}

	public void setRegistryMonitor(String registryMonitor) {
		this.registryMonitor = registryMonitor;
	}

	public String getRegistryComment() {
		return registryComment;
	}

	public void setRegistryComment(String registryComment) {
		this.registryComment = registryComment;
	}

	public String getState() {
		String s = null;
		switch(status.intValue()) {
			case PENDING:
				s = "travel.status.pending";
				break;
			case STARTED:
				s = "travel.status.started";
				break;
			case FINALIZED:
				s = "travel.status.finalized";
				break;
		}
		return s;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}
}
