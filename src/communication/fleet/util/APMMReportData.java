/**
 * Created on 10-nov-2006
 * CEPA
 * DataCenter 5
 */
package communication.fleet.util;

import java.io.Serializable;



public class APMMReportData implements Comparable , Serializable  {

    protected Integer divOrg1Id;
    private String divOrg1Name;

    protected Integer divOrg2Id;
    private String divOrg2Name;
    
    protected Integer divOrg3Id;
    private String divOrg3Name;
    
    protected Integer divOrg4Id;
    private String divOrg4Name;

    protected Integer miles;
    protected Integer accidents;
    protected Integer incidents;
    protected Integer nearmiss;
    
    public APMMReportData() {
    }
    
    
    public APMMReportData(Integer divOrg1Id, String divOrg1Name, Integer divOrg2Id, String divOrg2Name, Integer divOrg3Id, String divOrg3Name, Integer divOrg4Id, String divOrg4Name, Integer miles, Integer accidents, Integer incidents, Integer nearmiss) {
        this.divOrg1Id = divOrg1Id;
        this.divOrg1Name = divOrg1Name;
        this.divOrg2Id = divOrg2Id;
        this.divOrg2Name = divOrg2Name;
        this.divOrg3Id = divOrg3Id;
        this.divOrg3Name = divOrg3Name;
        this.divOrg4Id = divOrg4Id;
        this.divOrg4Name = divOrg4Name;
        this.miles = miles;
        this.accidents = accidents;
        this.incidents = incidents;
        this.nearmiss = nearmiss;
    }

    /**
     * @param divOrg1Id
     * @param divOrg1Name
     * @param divOrg2Id
     * @param divOrg2Name
     * @param divOrg3Id
     * @param divOrg3Name
     * @param miles
     * @param accidents
     * @param incidents
     */
    public APMMReportData(Integer divOrg1Id, String divOrg1Name, Integer divOrg2Id, String divOrg2Name, Integer divOrg3Id, String divOrg3Name, Integer miles, Integer accidents, Integer incidents, Integer nearmiss) {
        this(divOrg1Id,divOrg1Name,divOrg2Id,divOrg2Name,divOrg3Id,divOrg3Name,null,null,miles,accidents,incidents,nearmiss);
    }

    
    public Integer getAccidents() {
        return accidents;
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


    public void setDivOrg4Id(Integer divOrg4Id) {
        this.divOrg4Id = divOrg4Id;
    }


    public void setDivOrg4Name(String divOrg4Name) {
        this.divOrg4Name = divOrg4Name;
    }


    public Integer getIncidents() {
        return incidents;
    }

    public Integer getMiles() {
        return miles;
    }

    public void setAccidents(Integer accidents) {
        this.accidents = accidents;
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

    public void setIncidents(Integer incidents) {
        this.incidents = incidents;
    }

    public void setMiles(Integer miles) {
        this.miles = miles;
    }

    public int compareTo(Object arg0) {
        APMMReportData apmm = (APMMReportData)arg0;
        if(divOrg1Name != null && !divOrg1Name.equals(apmm.divOrg1Name)) {
            return divOrg1Name.compareTo(apmm.divOrg1Name);
        }
        if(divOrg2Name != null && !divOrg2Name.equals(apmm.divOrg2Name)) {
            return divOrg2Name.compareTo(apmm.divOrg2Name);
        }
        if(divOrg3Name != null && !divOrg3Name.equals(apmm.divOrg3Name)) {
            return divOrg3Name.compareTo(apmm.divOrg3Name);
        }
        if(divOrg4Name != null && !divOrg4Name.equals(apmm.divOrg4Name)) {
            return divOrg4Name.compareTo(apmm.divOrg4Name);
        }
        return 0;
    }
    
    public boolean equalDivisions(DivisionData data) {
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

	public Integer getNearmiss() {
		return nearmiss;
	}

	public void setNearmiss(Integer nearmiss) {
		this.nearmiss = nearmiss;
	}
}
