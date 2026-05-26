package persistence.event.tables;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import org.hibernate.Session;

import persistence.administration.corporation.tables.Corporation;
import persistence.administration.corporation.tables.DivOrgItemLevel1;
import persistence.driver.tables.Driver;
import persistence.vehicle.tables.Vehicle;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemAttribute;
import communication.administration.corporation.tables.LabelItemData;
import communication.travel.tables.TravelDataType;
import communication.vehicle.tables.VehicleData;

/**
 */
public class Travel {
	
    protected Integer id;

    protected Driver driver;
    private String mobile;

    protected Integer state;
    protected Corporation corporation;  
    
    private DivOrgItemLevel1 divOrg1;
    private DivOrgItemLevel1 divOrg2;
    private DivOrgItemLevel1 divOrg3;

    private String charge;
   	private String radio;
   	private String other;

    protected Vehicle vehicle;
    protected String plate;
    protected String trade;
    protected String model;
    protected String color;
    
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
	
	public Travel() {
	}
	
    public Travel(TravelDataType travelData, Session session) {
        
        this.id = travelData.getId();
        
        this.driver = (Driver)session.load(Driver.class,travelData.getDriver().getId());
        mobile = travelData.getMobile();

	    Iterator it = travelData.getDivOrgsIterator();
	    if(it.hasNext()) {
	        DivOrgData divOrg = (DivOrgData)it.next();
	        ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
	        while(item1.getSubItems() != null) {
	            item1 = (ItemData)item1.getSubItems().getItemIterator().next();
	        }
	        divOrg1 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,item1.getId());
	    }
	    if(it.hasNext()) {
	        DivOrgData divOrg = (DivOrgData)it.next();
	        ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
	        while(item1.getSubItems() != null) {
	            item1 = (ItemData)item1.getSubItems().getItemIterator().next();
	        }
	        divOrg2 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,item1.getId());
	    }
	    if(it.hasNext()) {
	        DivOrgData divOrg = (DivOrgData)it.next();
	        ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
	        while(item1.getSubItems() != null) {
	            item1 = (ItemData)item1.getSubItems().getItemIterator().next();
	        }
	        divOrg3 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,item1.getId());
	    }

	    corporation = (Corporation)session.load(Corporation.class,travelData.getCorporation().getId());
        state = travelData.getStatus();
        
        charge = travelData.getCharge();
        radio = travelData.getRadio();
        other = travelData.getOther();
        
        if(travelData.getVehicle() != null) {
            vehicle = (Vehicle)session.load(Vehicle.class,travelData.getVehicle().getId());
        }else {
            plate = travelData.getPlate();
            trade = travelData.getTrade();
            model = travelData.getModel();
            color = travelData.getColor();
        }

        localLeave = travelData.getLocalLeave();
        localArrive = travelData.getLocalArrive();
    	dateLeave = travelData.getDateLeave();
    	hourLeave = travelData.getHourLeave();
    	stops = travelData.getStops();
    	distance = travelData.getDistance();
    	hourArrive = travelData.getHourArrive();

    	rotogram = travelData.getRotogram();
    	localNames = travelData.getLocalNames();
    	comment = travelData.getComment();
    }

    public void setLeaveData(TravelDataType data) {
        this.state = data.getStatus();
    	this.confirmedLeave = data.getConfirmedLeave();
    	this.confirmedLeaveHour = data.getConfirmedLeaveHour();
    	this.communicationLeave = data.getCommunicationLeave();
    	this.monitor = data.getMonitor();
    	this.leaveComment = data.getLeaveComment();
    }
    
    public void setCloseData(TravelDataType data) {
    	state = data.getStatus();
    	
    	closeDate = data.getCloseDate();
    	closeHour = data.getCloseHour();
    	closeLocal = data.getCloseLocal();
    	closeWay = data.getCloseWay();
    	closeCommunication = data.getCloseCommunication();
    	travelResult = data.isTravelResult();
    	closeComment = data.getCloseComment();

    	registryDate = data.getRegistryDate();
    	registryHour = data.getRegistryHour();
    	registryLocal = data.getRegistryLocal();
    	registryMonitor = data.getRegistryMonitor();
    	registryComment = data.getRegistryComment();
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Driver getDriver() {
		return driver;
	}
	
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object arg) {
		if(arg==null){
			return false;
		}
		else{
			Travel ag=(Travel)arg;
	
			if(ag.getDateLeave().equals(this.getDateLeave()) && ag.getHourLeave().equals(this.getHourLeave()))
				{
				return true;
			}
			else{
				return false; 
			}
		}
	}

    /**
     * @return Returns the model.
     */
    public String getModel() {
        return model;
    }

    /**
     * @return Returns the plate.
     */
    public String getPlate() {
        return plate;
    }

    /**
     * @return Returns the trade.
     */
    public String getTrade() {
        return trade;
    }

    /**
     * @return Returns the vehicle.
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * @param model The model to set.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @param plate The plate to set.
     */
    public void setPlate(String plate) {
        this.plate = plate;
    }

    /**
     * @param trade The trade to set.
     */
    public void setTrade(String trade) {
        this.trade = trade;
    }

    /**
     * @param vehicle The vehicle to set.
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public TravelDataType getData() {
        VehicleData vehicleData = null;
        if(vehicle != null) {
            vehicleData = vehicle.getVehicleData(); 
        }
	    CorporationData corporationData = corporation.getData();
	    Collection<DivOrgData> divOrgs = new LinkedList<DivOrgData>();
	    if(divOrg1 != null) {
	        DivOrgData divOrgData = getDivOrgData(divOrg1,corporationData);
	        if(divOrgData != null) {
	            divOrgs.add(divOrgData);
	        }
	    }
	    if(divOrg2 != null) {
	        DivOrgData divOrgData = getDivOrgData(divOrg2,corporationData);
	        if(divOrgData != null) {
	            divOrgs.add(divOrgData);
	        }
	    }
	    if(divOrg3 != null) {
	        DivOrgData divOrgData = getDivOrgData(divOrg3,corporationData);
	        if(divOrgData != null) {
	            divOrgs.add(divOrgData);
	        }
	    }
        TravelDataType travel = new TravelDataType(id,driver.getDriverData(),mobile,state,vehicleData,plate,trade,model,color,divOrgs,
        		charge,radio,other,localLeave,localArrive,dateLeave,hourLeave,stops,distance,hourArrive,rotogram,localNames,comment);
        if(confirmedLeave != null) {
        	travel.setConfirmedLeave(confirmedLeave);
        	travel.setConfirmedLeaveHour(confirmedLeaveHour);
        	travel.setCommunicationLeave(communicationLeave);
        	travel.setMonitor(monitor);
        	travel.setLeaveComment(leaveComment);
        }
        if(closeDate != null) {
        	travel.setCloseDate(closeDate);
        	travel.setCloseHour(closeHour);
        	travel.setCloseLocal(closeLocal);
        	travel.setCloseWay(closeWay);
        	travel.setCloseCommunication(closeCommunication);
        	travel.setTravelResult(travelResult);
        	travel.setCloseComment(closeComment);

        	travel.setRegistryDate(registryDate);
        	travel.setRegistryHour(registryHour);
        	travel.setRegistryLocal(registryLocal);
        	travel.setRegistryMonitor(registryMonitor);
        	travel.setRegistryComment(registryComment);
        }
        return travel;
    }


    public void setData(TravelDataType travelData, Session session) {
        
    	mobile = travelData.getMobile();
        corporation = (Corporation)session.load(Corporation.class,travelData.getCorporation().getId());
        

        charge = travelData.getCharge();
        radio = travelData.getRadio();
        other = travelData.getOther();
        
        if(travelData.getVehicle() == null) {
            plate = travelData.getPlate();
            trade = travelData.getTrade();
            model = travelData.getModel();
            color = travelData.getColor();
        }

        localLeave = travelData.getLocalLeave();
        localArrive = travelData.getLocalArrive();
    	dateLeave = travelData.getDateLeave();
    	hourLeave = travelData.getHourLeave();
    	stops = travelData.getStops();
    	distance = travelData.getDistance();
    	hourArrive = travelData.getHourArrive();

    	rotogram = travelData.getRotogram();
    	localNames = travelData.getLocalNames();
    	comment = travelData.getComment();
    }

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Corporation getCorporation() {
		return corporation;
	}

	public void setCorporation(Corporation corporation) {
		this.corporation = corporation;
	}

    /**
     * @return Returns the divOrg1.
     */
    public DivOrgItemLevel1 getDivOrg1() {
        return divOrg1;
    }

    /**
     * @return Returns the divOrg2.
     */
    public DivOrgItemLevel1 getDivOrg2() {
        return divOrg2;
    }

    /**
     * @return Returns the divOrg3.
     */
    public DivOrgItemLevel1 getDivOrg3() {
        return divOrg3;
    }


    /**
     * @param divOrg1 The divOrg1 to set.
     */
    public void setDivOrg1(DivOrgItemLevel1 divOrg1) {
        this.divOrg1 = divOrg1;
    }

    /**
     * @param divOrg2 The divOrg2 to set.
     */
    public void setDivOrg2(DivOrgItemLevel1 divOrg2) {
        this.divOrg2 = divOrg2;
    }

    /**
     * @param divOrg3 The divOrg3 to set.
     */
    public void setDivOrg3(DivOrgItemLevel1 divOrg3) {
        this.divOrg3 = divOrg3;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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

	public Date getConfirmedLeave() {
		return confirmedLeave;
	}

	public void setConfirmedLeave(Date confirmedLeave) {
		this.confirmedLeave = confirmedLeave;
	}

	public String getCommunicationLeave() {
		return communicationLeave;
	}

	public void setCommunicationLeave(String communicationLeave) {
		this.communicationLeave = communicationLeave;
	}

	public String getMonitor() {
		return monitor;
	}

	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}

	public String getLeaveComment() {
		return leaveComment;
	}

	public void setLeaveComment(String leaveComment) {
		this.leaveComment = leaveComment;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

    private DivOrgData getDivOrgData(DivOrgItemLevel1 level1,CorporationData corporationData) {
        DivOrgData divOrgData = corporationData.getDivOrgData(level1.getDivOrg().getId().intValue());
	    LabelItemAttribute[] labelAttrs = divOrgData.getLabels();
	    ItemData itemData = new ItemData(level1.getAttributes());
	    Collection<ItemData> items1 = new LinkedList<ItemData>();
	    items1.add(itemData);
	    LabelItemData labelData = null; 
	    if(level1.getDivOrgItemLevel2() != null) {
	        ItemData item2 = new ItemData(level1.getDivOrgItemLevel2().getAttributes());
		    Collection<ItemData> items2 = new LinkedList<ItemData>();
		    items2.add(item2);
	        if(level1.getDivOrgItemLevel2().getDivOrgItemLevel3() == null) {
		        item2.setSubItems(new LabelItemData(labelAttrs[1].getId(),labelAttrs[1].getName(),items1,labelAttrs[1].getShowLevel()));
		        labelData = new LabelItemData(labelAttrs[0].getId(),labelAttrs[0].getName(),items2,labelAttrs[0].getShowLevel());
	        }else {
	            
		        item2.setSubItems(new LabelItemData(labelAttrs[2].getId(),labelAttrs[2].getName(),items1,labelAttrs[2].getShowLevel()));
	            
		        ItemData item3 = new ItemData(level1.getDivOrgItemLevel2().getDivOrgItemLevel3().getAttributes());
		        item3.setSubItems(new LabelItemData(labelAttrs[1].getId(),labelAttrs[1].getName(),items2,labelAttrs[1].getShowLevel()));

		        Collection<ItemData> items3 = new LinkedList<ItemData>();
			    items3.add(item3);
			    
		        labelData = new LabelItemData(labelAttrs[0].getId(),labelAttrs[0].getName(),items3,labelAttrs[0].getShowLevel());
	        }
	    }else {
	        labelData = new LabelItemData(labelAttrs[0].getId(),labelAttrs[0].getName(),items1,labelAttrs[0].getShowLevel());
	    }
	    divOrgData.setDivOrgLevels(labelData);
	    return divOrgData;
    }
}
