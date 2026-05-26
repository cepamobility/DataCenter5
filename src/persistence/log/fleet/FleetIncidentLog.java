/**
 * Created on 24-jul-2006
 * CEPA
 * DataCenter 5
 */
package persistence.log.fleet;

import java.util.Date;

import persistence.fleet.tables.FleetIncident;


public class FleetIncidentLog {

    private FleetIncidentLogPK pk;
    
    private Integer fleet;

    private Integer period;
    private Integer year;

    private Integer corporation;
    
    private Integer country;
    
    private Integer divOrg1;
    private Integer divOrg2;
    private Integer divOrg3;
    
    private String clasification;

    private Integer number;
    private Integer cost;

    private int action;

    public FleetIncidentLog() {
    }

    public FleetIncidentLog(FleetIncident incident,String user,Date date,int action) {
        pk = new FleetIncidentLogPK(incident.getId(),user,date);
        this.fleet = incident.getFleet().getId();
        this.period = incident.getPeriod();
        this.year = incident.getYear();
        this.action = action;
        this.corporation = incident.getCorporation().getId();
        this.country = incident.getCountry().getId();
        if(incident.getDivOrg1() != null) {
            this.divOrg1 = incident.getDivOrg1().getId();
        }        
        if(incident.getDivOrg2() != null) {
            this.divOrg2 = incident.getDivOrg2().getId();
        }        
        if(incident.getDivOrg3() != null) {
            this.divOrg3 = incident.getDivOrg3().getId();
        }        
        if(incident.getClasification() != null) {
            this.clasification = incident.getClasification().getResourceBundleId();
        }        
        this.number = incident.getNumber();
        this.cost = incident.getCost();
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getClasification() {
        return clasification;
    }

    public Integer getCorporation() {
        return corporation;
    }

    public Integer getCost() {
        return cost;
    }

    public Integer getCountry() {
        return country;
    }

    public Integer getDivOrg1() {
        return divOrg1;
    }

    public Integer getDivOrg2() {
        return divOrg2;
    }

    public Integer getDivOrg3() {
        return divOrg3;
    }

    public Integer getFleet() {
        return fleet;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getPeriod() {
        return period;
    }

    public FleetIncidentLogPK getPk() {
        return pk;
    }

    public Integer getYear() {
        return year;
    }

    public void setClasification(String clasification) {
        this.clasification = clasification;
    }

    public void setCorporation(Integer corporation) {
        this.corporation = corporation;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public void setDivOrg1(Integer divOrg1) {
        this.divOrg1 = divOrg1;
    }

    public void setDivOrg2(Integer divOrg2) {
        this.divOrg2 = divOrg2;
    }

    public void setDivOrg3(Integer divOrg3) {
        this.divOrg3 = divOrg3;
    }

    public void setFleet(Integer fleet) {
        this.fleet = fleet;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public void setPk(FleetIncidentLogPK pk) {
        this.pk = pk;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}
