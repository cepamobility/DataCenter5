/**
 * Created on 10-nov-2006
 * CEPA
 * DataCenter 5
 */
package communication.fleet.util;

import java.io.Serializable;

import communication.event.tables.EventDataType;



public class EventCostData extends DivisionData implements Serializable {

    private Integer period;
    private Integer year;
    
    protected Integer accidentCost;
    protected Integer incidentCost;
    protected Integer nearmissCost;

    public EventCostData(Object[] data, int type) {
        switch(type) {
        	case EventDataType.ACCIDENT:
        		accidentCost = (data[0] == null) ? new Integer(0) : new Integer( ((Long)data[0]).intValue());
        		incidentCost = new Integer(0);
        		nearmissCost = new Integer(0);
        		break;
        	case EventDataType.INCIDENT:
        		accidentCost = new Integer(0);
        		incidentCost = (data[0] == null) ? new Integer(0) : new Integer( ((Long)data[0]).intValue());
        		nearmissCost = new Integer(0);
        		break;
        	case EventDataType.NEARMISS:
        		accidentCost = new Integer(0);
        		incidentCost = new Integer(0);
        		nearmissCost = (data[0] == null) ? new Integer(0) : new Integer( ((Long)data[0]).intValue());
        		break;
        }
        divOrg1Id = (Integer) data[1];
        divOrg2Id = (Integer) data[2];
        divOrg3Id = (Integer) data[3];
        if(data.length == 4) {
            period = new Integer(0);
            year = new Integer(0);
        }else {
            period = (Integer) data[4];
            year = (Integer) data[5];
        }
    }

    public Integer getAccidentCost() {
        return accidentCost;
    }

    public Integer getIncidentCost() {
        return incidentCost;
    }

    public void setAccidentCost(Integer accidentCost) {
        this.accidentCost = accidentCost;
    }

    public void setIncidentCost(Integer incidentCost) {
        this.incidentCost = incidentCost;
    }

    public Integer getPeriod() {
        return period;
    }

    public Integer getYear() {
        return year;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
    
    public boolean equalDivisions(DivisionData data) {
        if(!super.equalDivisions(data)) {
            return false;
        }
        if(!period.equals(((EventCostData)data).period)) {
            return false;
        }
        if(!year.equals(((EventCostData)data).year)) {
            return false;
        }
        return true;
    }

	public Integer getNearmissCost() {
		return nearmissCost;
	}

	public void setNearmissCost(Integer nearmissCost) {
		this.nearmissCost = nearmissCost;
	}
}
