/**
 * Created on 10-nov-2006
 * CEPA
 * DataCenter 5
 */
package communication.fleet.util;

import java.io.Serializable;

import communication.administration.corporation.tables.CorporationData;
import communication.language.Text;



public abstract class TypeData implements Comparable , Serializable {

    protected Integer divOrg1Id;
    
    private String divOrg1Name;

    protected Integer divOrg2Id;
    private String divOrg2Name;
    
    protected Integer divOrg3Id;
    private String divOrg3Name;
    
    protected Integer divOrg4Id;
    private String divOrg4Name;

    private String eventType; 
    
    protected int cost;
    private int count;
    
    private String type;
    
    public TypeData() {
    }

    public TypeData(Object[] data,String type, int corporation, Text messages, int order1, int order2, int order3, int order4) {
        count = 1;
        cost = (data[0] == null) ? 0 : ((Integer)data[0]).intValue();
        eventType = (String) data[1];
        if(corporation != CorporationData.MONSANTO) {
            if(eventType.equals("datatype.accidentclasification.other")) {
                eventType = (String) data[2]+"_"+eventType;
            }
        }
        if(data.length > 9 && data[9] instanceof Integer) {
            divOrg1Id = (Integer) data[9];
            divOrg1Name = (order1 == 3) ? messages.getText((String) data[10]) : (String) data[10];
        }
        if(data.length > 11 && data[11] instanceof Integer) {
            divOrg2Id = (Integer) data[11];
            divOrg2Name = (order2 == 3) ? messages.getText((String) data[12]) : (String) data[12];
        }
        if(data.length > 13 && data[13] instanceof Integer) {
            divOrg3Id = (Integer) data[13];
            divOrg3Name = (order3 == 3) ? messages.getText((String) data[14]) : (String) data[14];
        }
        if(data.length > 15 && data[15] instanceof Integer) {
            divOrg4Id = (Integer) data[15];
            divOrg4Name = (order4 == 3) ? messages.getText((String) data[16]) : (String) data[16];
        }
        this.type = type;
    }

    public TypeData(int count, int cost, String eventType, Integer divOrg1Id, String divOrg1Name, Integer divOrg2Id, String divOrg2Name, Integer divOrg3Id, String divOrg3Name, Integer divOrg4Id, String divOrg4Name,String type) {
        this.count = count;
        this.cost = cost;
        this.eventType = eventType;
        this.divOrg1Id = divOrg1Id;
        this.divOrg1Name = divOrg1Name;
        this.divOrg2Id = divOrg2Id;
        this.divOrg2Name = divOrg2Name;
        this.divOrg3Id = divOrg3Id;
        this.divOrg3Name = divOrg3Name;
        this.divOrg4Id = divOrg4Id;
        this.divOrg4Name = divOrg4Name;
        this.type = type;
    }

    public TypeData(int count, int cost, String eventType, String consequence, Integer divOrg1Id, 
    		String divOrg1Name, Integer divOrg2Id, String divOrg2Name, Integer divOrg3Id, 
    		String divOrg3Name, Integer divOrg4Id, String divOrg4Name,String type) {
        this.count = count;
        this.cost = cost;
        this.eventType = eventType;
        this.divOrg1Id = divOrg1Id;
        this.divOrg1Name = divOrg1Name;
        this.divOrg2Id = divOrg2Id;
        this.divOrg2Name = divOrg2Name;
        this.divOrg3Id = divOrg3Id;
        this.divOrg3Name = divOrg3Name;
        this.divOrg4Id = divOrg4Id;
        this.divOrg4Name = divOrg4Name;
        this.type = type;
    }

    public int getCost() {
        return cost;
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

    public String getEventType() {
        return eventType;
    }

    public void setCost(int cost) {
        this.cost = cost;
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

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    
    public int compareTo(Object arg0) {
        TypeData data = (TypeData)arg0;
        if(divOrg1Name != null) {
            if(!divOrg1Name.equals(data.divOrg1Name)) {
                return divOrg1Name.compareTo(data.divOrg1Name);
            }else{
                if(!divOrg1Id.equals(data.divOrg1Id)) {
                    return divOrg1Id.compareTo(data.divOrg1Id);
                }
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
        if(!type.equals(data.type)) {
            if(type.equals("accident")) {
                return 0;
            }else if(data.type.equals("accident")) {
                return 1;
            }else if(type.equals("incident")) {
                return 0;
            }else {
                return 1;
            }
        }
        return eventType.compareTo(data.eventType);
    }

    public boolean equalData(TypeData data) {
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
        return eventType.equals(data.eventType);
    }

    public void addData(TypeData data) {
        
        this.cost += data.cost;
        this.count += data.count;
    }

    public int getCount() {
        return count;
    }

    public String getType() {
        return type;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setType(String type) {
        this.type = type;
    }

    
}
