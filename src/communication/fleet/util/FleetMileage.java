/**
 * Created on 26-sep-2006
 * CEPA
 * DataCenter 5
 */
package communication.fleet.util;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.StringTokenizer;

import presentation.translator.web.util.Util;

import communication.administration.user.UserSessionData;
import communication.vehicle.tables.VehicleData;

public class FleetMileage extends DivisionData 
        implements Comparable, Serializable {

	private static final long serialVersionUID = 1L;
	protected Integer period;
    protected Integer year;

    protected String country;
    
    protected Integer divOrg4Id;
    protected Integer divOrg5Id;
    protected Integer divOrg6Id;
    protected Integer divOrg7Id;
    protected Integer divOrg8Id;
    protected Integer divOrg9Id;

    protected String divOrg1Name;
    protected String divOrg2Name;
    protected String divOrg3Name;
    protected String divOrg4Name;
    protected String divOrg5Name;
    protected String divOrg6Name;
    protected String divOrg7Name;
    protected String divOrg8Name;
    protected String divOrg9Name;

    protected String vehicleType;

    protected Integer vehicles;
    protected Integer miles;
    protected Integer accidents;
    protected Integer incidents;
    protected Integer nearmiss;
    
    private Integer accidentCost;
    private Integer incidentCost;
    private Integer nearmissCost;
    
    
    public FleetMileage() {
        accidentCost = new Integer(0);
        incidentCost = new Integer(0);
        nearmissCost = new Integer(0);
    }

    public FleetMileage(Object[] mileage) {

        this.vehicles = new Integer(((Long)mileage[0]).intValue());
        this.miles = new Integer (((Long) mileage[1]).intValue());
        this.accidents = new Integer (((Long) mileage[2]).intValue());
        this.incidents = new Integer (((Long) mileage[3]).intValue());
        this.nearmiss = new Integer (((Long) mileage[4]).intValue());

        this.period = (Integer) mileage[5];
        this.year = (Integer) mileage[6];
        
        this.country = (String) mileage[7];
        this.vehicleType = (String) mileage[8];

        if(mileage.length > 9) {
            this.divOrg1Id = (Integer) mileage[9];
            this.divOrg1Name = (String) mileage[10];
        }
        if(mileage.length > 11) {
            this.divOrg2Id = (Integer) mileage[11];
            this.divOrg2Name = (String) mileage[12];
        }
        if(mileage.length > 13) {
            this.divOrg3Id = (Integer) mileage[13];
            this.divOrg3Name = (String) mileage[14];
        }
        if(mileage.length > 15) {
            this.divOrg4Id = (Integer) mileage[15];
            this.divOrg4Name = (String) mileage[16];
        }
        if(mileage.length > 17) {
            this.divOrg5Id = (Integer) mileage[17];
            this.divOrg5Name = (String) mileage[18];
        }
        if(mileage.length > 19) {
            this.divOrg6Id = (Integer) mileage[19];
            this.divOrg6Name = (String) mileage[20];
        }
        if(mileage.length > 21) {
            this.divOrg7Id = (Integer) mileage[21];
            this.divOrg7Name = (String) mileage[22];
        }
        if(mileage.length > 23) {
            this.divOrg8Id = (Integer) mileage[23];
            this.divOrg8Name = (String) mileage[24];
        }
        if(mileage.length > 25) {
            this.divOrg9Id = (Integer) mileage[25];
            this.divOrg9Name = (String) mileage[26];
        }
        
        accidentCost = new Integer(0);
        incidentCost = new Integer(0);
        nearmissCost = new Integer(0);
    }

    public FleetMileage(String periodStr,VehicleData vehicle,Integer lastOdometer,Integer odometer,Integer accidents,Integer incidents, Integer nearmiss, Hashtable fleetDivisions, Integer fleet, UserSessionData userSessionData) {
        StringTokenizer tokenizer = new StringTokenizer(periodStr,"/");
        this.period = new Integer(tokenizer.nextToken());
        this.year = new Integer(tokenizer.nextToken());
        this.country = vehicle.getCountryName();
    
        int[][] divOrgs = userSessionData.getFilter().getAllDivOrgs();
        
        if(divOrgs.length > 0) {
            this.divOrg1Id = vehicle.getIdData(divOrgs[0][0],divOrgs[0][1]);
            this.divOrg1Name = vehicle.getData(divOrgs[0][0],divOrgs[0][1]);
        }
        if(divOrgs.length > 1) {
            this.divOrg2Id = vehicle.getIdData(divOrgs[1][0],divOrgs[1][1]);
            this.divOrg2Name = vehicle.getData(divOrgs[1][0],divOrgs[1][1]);
        }
        if(divOrgs.length > 2) {
            this.divOrg3Id = vehicle.getIdData(divOrgs[2][0],divOrgs[2][1]);
            this.divOrg3Name = vehicle.getData(divOrgs[2][0],divOrgs[2][1]);
        }
        if(divOrgs.length > 3) {
            this.divOrg4Id = vehicle.getIdData(divOrgs[3][0],divOrgs[3][1]);
            this.divOrg4Name = vehicle.getData(divOrgs[3][0],divOrgs[3][1]);
        }
        if(divOrgs.length > 4) {
            this.divOrg5Id = vehicle.getIdData(divOrgs[4][0],divOrgs[4][1]);
            this.divOrg6Name = vehicle.getData(divOrgs[4][0],divOrgs[4][1]);
        }
        if(divOrgs.length > 5) {
            this.divOrg6Id = vehicle.getIdData(divOrgs[5][0],divOrgs[5][1]);
            this.divOrg6Name = vehicle.getData(divOrgs[5][0],divOrgs[5][1]);
        }
        if(divOrgs.length > 6) {
            this.divOrg7Id = vehicle.getIdData(divOrgs[6][0],divOrgs[6][1]);
            this.divOrg7Name = vehicle.getData(divOrgs[6][0],divOrgs[6][1]);
        }
        if(divOrgs.length > 7) {
            this.divOrg8Id = vehicle.getIdData(divOrgs[7][0],divOrgs[7][1]);
            this.divOrg8Name = vehicle.getData(divOrgs[7][0],divOrgs[7][1]);
        }
        if(divOrgs.length > 8) {
            this.divOrg9Id = vehicle.getIdData(divOrgs[8][0],divOrgs[8][1]);
            this.divOrg9Name = vehicle.getData(divOrgs[8][0],divOrgs[8][1]);
        }

        this.vehicleType = vehicle.getType().getResourceBundleId();
        this.vehicles = new Integer(1);
        if(lastOdometer.intValue() >= 0) {
	        this.miles = (odometer.intValue() - lastOdometer.intValue() < 0 )
	        	? new Integer(0) : new Integer(odometer.intValue() - lastOdometer.intValue());
	        if(fleetDivisions.containsKey(fleet)) {
	        	if(fleetDivisions.get(fleet).equals("datatype.distanceunit.kilometers")) {
	        		this.miles = new Integer(String.valueOf(Math.round(this.miles.doubleValue() / Util.KMS_TO_MILES)));
	        	}
	        }
        }else {
        	miles = new Integer(0);
        }
        this.accidents = accidents;
        this.incidents = incidents;
        this.nearmiss = nearmiss;
        accidentCost = new Integer(0);
        incidentCost = new Integer(0);
        nearmissCost = new Integer(0);
    }

    public void addData(FleetMileage other) {
        vehicles = new Integer(vehicles.intValue() + 1);
        miles = new Integer(miles.intValue() + other.miles.intValue());
        accidents = new Integer(accidents.intValue() + other.accidents.intValue());
        incidents = new Integer(incidents.intValue() + other.incidents.intValue());
        nearmiss = new Integer(nearmiss.intValue() + other.nearmiss.intValue());
    }
    
    public Integer getAccidents() {
        return accidents;
    }

    public String getCountry() {
        return country;
    }

    public Integer getIncidents() {
        return incidents;
    }

    public Integer getMiles() {
        return miles;
    }

    public Integer getPeriod() {
        return period;
    }

    public Float getVehicles() {
        return new Float(vehicles.floatValue());
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public Integer getYear() {
        return year;
    }

    public void setAccidents(Integer accidents) {
        this.accidents = accidents;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setIncidents(Integer incidents) {
        this.incidents = incidents;
    }

    public void setMiles(Integer miles) {
        this.miles = miles;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public void setVehicles(Integer vehicles) {
        this.vehicles = vehicles;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public int compareTo(Object arg0) {
        FleetMileage other = (FleetMileage)arg0;
        if(!year.equals(other.year)) {
            return year.compareTo(other.year);
        }
        if(!period.equals(other.period)) {
            return period.compareTo(other.period);
        }
        if(!country.equals(other.country)) {
            return country.compareTo(other.country);
        }
        if(divOrg3Name != null && !divOrg3Name.equals(other.divOrg3Name)) {
            return divOrg3Name.compareTo(other.divOrg3Name);
        }
        if(divOrg1Name != null && !divOrg1Name.equals(other.divOrg1Name)) {
            return divOrg1Name.compareTo(other.divOrg1Name);
        }
        if(divOrg2Name != null && !divOrg2Name.equals(other.divOrg2Name)) {
            return divOrg2Name.compareTo(other.divOrg2Name);
        }
        if(divOrg4Name != null && !divOrg4Name.equals(other.divOrg4Name)) {
            return divOrg4Name.compareTo(other.divOrg4Name);
        }
        if(divOrg5Name != null && !divOrg5Name.equals(other.divOrg5Name)) {
            return divOrg5Name.compareTo(other.divOrg5Name);
        }
        if(divOrg6Name != null && !divOrg6Name.equals(other.divOrg6Name)) {
            return divOrg6Name.compareTo(other.divOrg6Name);
        }
        if(divOrg7Name != null && !divOrg7Name.equals(other.divOrg7Name)) {
            return divOrg7Name.compareTo(other.divOrg7Name);
        }
        if(divOrg8Name != null && !divOrg8Name.equals(other.divOrg8Name)) {
            return divOrg8Name.compareTo(other.divOrg8Name);
        }
        if(divOrg9Name != null && !divOrg9Name.equals(other.divOrg9Name)) {
            return divOrg9Name.compareTo(other.divOrg9Name);
        }
        return 0;
    }
    
    public boolean equalsData(FleetMileage other) {
        if(!equalDivisions(other)) {
            return false;
        }
        if(!year.equals(other.year)) {
            return false;
        }
        if(!period.equals(other.period)) {
            return false;
        }
        if(!country.equals(other.country)) {
            return false;
        }
        if(divOrg4Id != null && !divOrg4Id.equals(other.divOrg4Id)) {
            return false;
        }
        return true;
    }

    public Integer getAccidentCost() {
        return accidentCost;
    }

    public Integer getIncidentCost() {
        return incidentCost;
    }

    public String getDivOrg1Name() {
        return divOrg1Name;
    }

    public String getDivOrg2Name() {
        return divOrg2Name;
    }

    public String getDivOrg3Name() {
        return divOrg3Name;
    }

    public void setAccidentCost(Integer accidentCost) {
        this.accidentCost = accidentCost;
    }

    public void setDivOrg1Name(String divOrg1Name) {
        this.divOrg1Name = divOrg1Name;
    }

    public void setDivOrg2Name(String divOrg2Name) {
        this.divOrg2Name = divOrg2Name;
    }

    public void setDivOrg3Name(String divOrg3Name) {
        this.divOrg3Name = divOrg3Name;
    }

    public void setIncidentCost(Integer incidentCost) {
        this.incidentCost = incidentCost;
    }
    
    public boolean equalDivisions(DivisionData data) {
        if(!super.equalDivisions(data)) {
            return false;
        }
        if(data instanceof EventCostData) {
            if(((EventCostData)data).getPeriod().intValue() != 0) {
                if(!period.equals(((EventCostData)data).getPeriod())) {
                    return false;
                }
                if(!year.equals(((EventCostData)data).getYear())) {
                    return false;
                }
            }
        }
        return true;
    }

    public Integer getDivOrg4Id() {
        return divOrg4Id;
    }

    public String getDivOrg4Name() {
        return divOrg4Name;
    }

    public Integer getDivOrg5Id() {
        return divOrg5Id;
    }

    public String getDivOrg5Name() {
        return divOrg5Name;
    }

    public Integer getDivOrg6Id() {
        return divOrg6Id;
    }

    public String getDivOrg6Name() {
        return divOrg6Name;
    }

    public Integer getDivOrg7Id() {
        return divOrg7Id;
    }

    public String getDivOrg7Name() {
        return divOrg7Name;
    }

    public Integer getDivOrg8Id() {
        return divOrg8Id;
    }

    public String getDivOrg8Name() {
        return divOrg8Name;
    }

    public Integer getDivOrg9Id() {
        return divOrg9Id;
    }

    public String getDivOrg9Name() {
        return divOrg9Name;
    }

    public void setDivOrg4Id(Integer divOrg4Id) {
        this.divOrg4Id = divOrg4Id;
    }

    public void setDivOrg4Name(String divOrg4Name) {
        this.divOrg4Name = divOrg4Name;
    }

    public void setDivOrg5Id(Integer divOrg5Id) {
        this.divOrg5Id = divOrg5Id;
    }

    public void setDivOrg5Name(String divOrg5Name) {
        this.divOrg5Name = divOrg5Name;
    }

    public void setDivOrg6Id(Integer divOrg6Id) {
        this.divOrg6Id = divOrg6Id;
    }

    public void setDivOrg6Name(String divOrg6Name) {
        this.divOrg6Name = divOrg6Name;
    }

    public void setDivOrg7Id(Integer divOrg7Id) {
        this.divOrg7Id = divOrg7Id;
    }

    public void setDivOrg7Name(String divOrg7Name) {
        this.divOrg7Name = divOrg7Name;
    }

    public void setDivOrg8Id(Integer divOrg8Id) {
        this.divOrg8Id = divOrg8Id;
    }

    public void setDivOrg8Name(String divOrg8Name) {
        this.divOrg8Name = divOrg8Name;
    }

    public void setDivOrg9Id(Integer divOrg9Id) {
        this.divOrg9Id = divOrg9Id;
    }

    public void setDivOrg9Name(String divOrg9Name) {
        this.divOrg9Name = divOrg9Name;
    }

	public Integer getNearmiss() {
		return nearmiss;
	}

	public void setNearmiss(Integer nearmiss) {
		this.nearmiss = nearmiss;
	}

	public Integer getNearmissCost() {
		return nearmissCost;
	}

	public void setNearmissCost(Integer nearmissCost) {
		this.nearmissCost = nearmissCost;
	}
    
}
