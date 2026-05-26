/**
 * Created on 22-mar-2007
 * CEPA
 * DataCenter 5
 */
package communication.event.util;

import java.io.Serializable;

import communication.event.tables.EventDataType;
import communication.language.Text;

public class EventCountData  implements Comparable, Serializable {

    private Integer div1Id;
    private String div1Name;

    private Integer div2Id;
    private String div2Name;
    
    private Integer div3Id;
    private String div3Name;
    
    private Integer div4Id;
    private String div4Name;

    private Integer accidentCount;
    private Integer accidentCost;

    private Integer incidentCount;
    private Integer incidentCost;
    
    private Integer nearmissCount;
    private Integer nearmissCost;

    private Integer fineCount;
    private Integer fineCost;
    
    public EventCountData(Object[] data, int type, int order1, int order2, int order3, int order4, Text messages) {
        switch(type) {
            case EventDataType.ACCIDENT:
                accidentCount = new Integer(((Long)data[0]).intValue());
                accidentCost = (data[1] == null) ? new Integer(0) : new Integer(((Long)data[1]).intValue());
                incidentCount = new Integer(0);
                incidentCost = new Integer(0);
                fineCount = new Integer(0);
                fineCost = new Integer(0);
                nearmissCount = new Integer(0);
                nearmissCost = new Integer(0);
                break;
            case EventDataType.INCIDENT:
                accidentCount = new Integer(0);
                accidentCost = new Integer(0);
                incidentCount = new Integer(((Long)data[0]).intValue());
                incidentCost = (data[1] == null) ? new Integer(0) : new Integer(((Long)data[1]).intValue());
                fineCount = new Integer(0);
                fineCost = new Integer(0);
                nearmissCount = new Integer(0);
                nearmissCost = new Integer(0);
                break;
            case EventDataType.NEARMISS:
                accidentCount = new Integer(0);
                accidentCost = new Integer(0);
                incidentCount = new Integer(0);
                incidentCost = new Integer(0);
                fineCount = new Integer(0);
                fineCost = new Integer(0);
                nearmissCount = new Integer(((Long)data[0]).intValue());
                nearmissCost = (data[1] == null) ? new Integer(0) : new Integer(((Long)data[1]).intValue());
                break;
            case EventDataType.FINE:
                accidentCount = new Integer(0);
                accidentCost = new Integer(0);
                incidentCount = new Integer(0);
                incidentCost = new Integer(0);
                fineCount = new Integer(((Long)data[0]).intValue());
                fineCost = (data[1] == null) ? new Integer(0) : new Integer(((Long)data[1]).intValue());
                nearmissCount = new Integer(0);
                nearmissCost = new Integer(0);
                break;
        }
        if(data.length > 2) {
            div1Id = (Integer)data[2];
            div1Name = (order1 == 3) ? messages.getText((String)data[3]) : (String)data[3];
        }
        if(data.length > 4) {
            div2Id = (Integer)data[4];
            div2Name = (order2 == 3) ? messages.getText((String)data[5]) : (String)data[5];
        }
        if(data.length > 6) {
            div3Id = (Integer)data[6];
            div3Name = (order3 == 3) ? messages.getText((String)data[7]) : (String)data[7];
        }
        if(data.length > 8) {
            div4Id = (Integer)data[8];
            div4Name = (order4 == 3) ? messages.getText((String)data[9]) : (String)data[9];
        }
    }
    
    
    public Integer getAccidentCost() {
        return accidentCost;
    }


    public Integer getAccidentCount() {
        return accidentCount;
    }


    public Integer getDiv1Id() {
        return div1Id;
    }


    public String getDiv1Name() {
        return div1Name;
    }


    public Integer getDiv2Id() {
        return div2Id;
    }


    public String getDiv2Name() {
        return div2Name;
    }


    public Integer getDiv3Id() {
        return div3Id;
    }


    public String getDiv3Name() {
        return div3Name;
    }


    public Integer getDiv4Id() {
        return div4Id;
    }


    public String getDiv4Name() {
        return div4Name;
    }


    public Integer getFineCost() {
        return fineCost;
    }


    public Integer getFineCount() {
        return fineCount;
    }


    public Integer getIncidentCost() {
        return incidentCost;
    }


    public Integer getIncidentCount() {
        return incidentCount;
    }


    public void setAccidentCost(Integer accidentCost) {
        this.accidentCost = accidentCost;
    }


    public void setAccidentCount(Integer accidentCount) {
        this.accidentCount = accidentCount;
    }


    public void setFineCost(Integer fineCost) {
        this.fineCost = fineCost;
    }


    public void setFineCount(Integer fineCount) {
        this.fineCount = fineCount;
    }


    public void setIncidentCost(Integer incidentCost) {
        this.incidentCost = incidentCost;
    }


    public void setIncidentCount(Integer incidentCount) {
        this.incidentCount = incidentCount;
    }


    public int compareTo(Object arg0) {
        EventCountData data = (EventCountData)arg0;
        if(div1Name != null && !div1Name.equals(data.div1Name)) {
            return div1Name.compareTo(data.div1Name);
        }
        if(div2Name != null && !div2Name.equals(data.div2Name)) {
            return div2Name.compareTo(data.div2Name);
        }
        if(div3Name != null && !div3Name.equals(data.div3Name)) {
            return div3Name.compareTo(data.div3Name);
        }
        if(div4Name != null && !div4Name.equals(data.div4Name)) {
            return div4Name.compareTo(data.div4Name);
        }
        return 0;
    }

    public boolean equalsData(EventCountData data) {
        if(div1Id != null && !div1Id.equals(data.div1Id)) {
            return false;
        }
        if(div2Id != null && !div2Id.equals(data.div2Id)) {
            return false;
        }
        if(div3Id != null && !div3Id.equals(data.div3Id)) {
            return false;
        }
        if(div4Id != null && !div4Id.equals(data.div4Id)) {
            return false;
        }
        return true;
    }


	public Integer getNearmissCount() {
		return nearmissCount;
	}


	public void setNearmissCount(Integer nearmissCount) {
		this.nearmissCount = nearmissCount;
	}


	public Integer getNearmissCost() {
		return nearmissCost;
	}


	public void setNearmissCost(Integer nearmissCost) {
		this.nearmissCost = nearmissCost;
	}

}
