package communication.event.util;

import java.io.Serializable;

import communication.language.Text;


public class EventSeverityData implements Comparable, Serializable{

    private Integer eventCount[];

    private Integer divOrg1Id;
    private String divOrg1Name;

    private Integer divOrg2Id;
    private String divOrg2Name;
    
    private Integer divOrg3Id;
    private String divOrg3Name;
    
    private Integer divOrg4Id;
    private String divOrg4Name;
    
    
	public EventSeverityData(Object[] data,int order1, int order2, int order3, int order4, Text messages){
		eventCount = new Integer[8];
        int index = (data[0] == null) ? 7 : ((Integer)data[0]).intValue();  
        eventCount[index] = (Integer)data[1];        
        if(data.length > 2) {
            divOrg1Id = (Integer)data[3];
            divOrg1Name = (order1 == 3) ? messages.getText((String)data[4]) : (String)data[4];
        }
        if(data.length > 4) {
            divOrg2Id = (Integer)data[5];
            divOrg2Name = (order2 == 3) ? messages.getText((String)data[6]) : (String)data[6];
        }
        if(data.length > 6) {
            divOrg3Id = (Integer)data[7];
            divOrg3Name = (order3 == 3) ? messages.getText((String)data[8]) : (String)data[8];
        }
        if(data.length > 8) {
            divOrg4Id = (Integer)data[9];
            divOrg4Name = (order4 == 3) ? messages.getText((String)data[10]) : (String)data[10];
        }
	}
	
	public Integer getDivOrg1Id() {
        return divOrg1Id;
    }

    public String getDivOrg1Name() {
        return divOrg1Name;
    }

    public Integer getDivOrg2Id() {
        return divOrg2Id;
    }

    public String getDivOrg2Name() {
        return divOrg2Name;
    }

    public Integer getDivOrg3Id() {
        return divOrg3Id;
    }

    public String getDivOrg3Name() {
        return divOrg3Name;
    }

    public Integer getDivOrg4Id() {
        return divOrg4Id;
    }

    public String getDivOrg4Name() {
        return divOrg4Name;
    }

    public void setDivOrg1Id(Integer divOrg1Id) {
        this.divOrg1Id = divOrg1Id;
    }

    public void setDivOrg1Name(String divOrg1Name) {
        this.divOrg1Name = divOrg1Name;
    }

    public void setDivOrg2Id(Integer divOrg2Id) {
        this.divOrg2Id = divOrg2Id;
    }

    public void setDivOrg2Name(String divOrg2Name) {
        this.divOrg2Name = divOrg2Name;
    }

    public void setDivOrg3Id(Integer divOrg3Id) {
        this.divOrg3Id = divOrg3Id;
    }

    public void setDivOrg3Name(String divOrg3Name) {
        this.divOrg3Name = divOrg3Name;
    }

    public void setDivOrg4Id(Integer divOrg4Id) {
        this.divOrg4Id = divOrg4Id;
    }

    public void setDivOrg4Name(String divOrg4Name) {
        this.divOrg4Name = divOrg4Name;
    }

    /**
	 * 
	 * @param arg0
	 * @return
	 */
	public int compareTo(Object arg0) {
        EventSeverityData data = (EventSeverityData)arg0;
        if(!divOrg1Name.equals(data.divOrg1Name)) {
            return divOrg1Name.compareTo(data.divOrg1Name);
        }else{
            if(!divOrg1Id.equals(data.divOrg1Id)) {
                return divOrg1Id.compareTo(data.divOrg1Id);
            }
        }
        if(divOrg2Name != null) {
            if(!divOrg2Name.equals(data.divOrg2Name)) {
                return divOrg2Name.compareTo(data.divOrg2Name);
            }else{
                if(!divOrg2Id.equals(data.divOrg2Id)) {
                    return divOrg2Id.compareTo(data.divOrg2Id);
                }
            }
        }
        if(divOrg3Name != null) {
            if(!divOrg3Name.equals(data.divOrg3Name)) {
                return divOrg3Name.compareTo(data.divOrg3Name);
            }else{
                if(!divOrg3Id.equals(data.divOrg3Id)) {
                    return divOrg3Id.compareTo(data.divOrg3Id);
                }
            }
        }
        if(divOrg4Name != null) {
            if(!divOrg4Name.equals(data.divOrg4Name)) {
                return divOrg4Name.compareTo(data.divOrg4Name);
            }else{
                if(!divOrg4Id.equals(data.divOrg4Id)) {
                    return divOrg4Id.compareTo(data.divOrg4Id);
                }
            }
        }
        return 0;
    }
	
    public boolean equalData(EventSeverityData data) {
        if(divOrg1Id != null && !divOrg1Id.equals(data.divOrg1Id)) {
            return false;
        }
        if(divOrg2Id != null && !divOrg2Id.equals(data.divOrg2Id)) {
            return false;
        }
        if(divOrg3Id != null && !divOrg3Id.equals(data.divOrg3Id)) {
            return false;
        }
        if(divOrg4Id != null && !divOrg4Id.equals(data.divOrg4Id)) {
            return false;
        }
        return true;
    }

	public void addData(Object[] data) {
        int index = (data[0] == null) ? 7 : ((Integer)data[0]).intValue();  
        eventCount[index] = (Integer)data[1];        
	}

	public Integer getSeverity(int index) {
		return new Integer(eventCount[index]);
	}

}

