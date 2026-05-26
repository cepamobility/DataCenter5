/**
 * Created on 24-jul-2006
 * CEPA
 * DataCenter 5
 */
package persistence.log.fleet;

import java.util.Date;

import persistence.fleet.tables.FleetAccident;


public class FleetAccidentLog {

    private FleetAccidentLogPK pk;
    
    private Integer fleet;

    private Integer period;
    private Integer year;

    private Integer corporation;
    
    private Integer country;
    
    private Integer divOrg1;
    private Integer divOrg2;
    private Integer divOrg3;
    
    private String clasification;
    private String collisionClasification;

    private String consequence;

    private Integer number;
    private Integer cost;

    private int action;

    public FleetAccidentLog() {
    }

    public FleetAccidentLog(FleetAccident accident,String user,Date date,int action) {
        pk = new FleetAccidentLogPK(accident.getId(),user,date);
        this.fleet = accident.getFleet().getId();
        this.period = accident.getPeriod();
        this.year = accident.getYear();
        this.action = action;
        this.corporation = accident.getCorporation().getId();
        this.country = accident.getCountry().getId();
        if(accident.getDivOrg1() != null) {
            this.divOrg1 = accident.getDivOrg1().getId();
        }        
        if(accident.getDivOrg2() != null) {
            this.divOrg2 = accident.getDivOrg2().getId();
        }        
        if(accident.getDivOrg3() != null) {
            this.divOrg3 = accident.getDivOrg3().getId();
        }        
        if(accident.getClasification() != null) {
            this.clasification = accident.getClasification().getResourceBundleId();
        }        
        if(accident.getCollisionClasification() != null) {
            this.collisionClasification = accident.getCollisionClasification().getResourceBundleId();
        }        
        if(accident.getConsequence() != null) {
            this.consequence = accident.getConsequence().getResourceBundleId();
        }        
        this.number = accident.getNumber();
        this.cost = accident.getCost();
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

    public String getCollisionClasification() {
        return collisionClasification;
    }

    public String getConsequence() {
        return consequence;
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

    public FleetAccidentLogPK getPk() {
        return pk;
    }

    public Integer getYear() {
        return year;
    }

    public void setClasification(String clasification) {
        this.clasification = clasification;
    }

    public void setCollisionClasification(String collisionClasification) {
        this.collisionClasification = collisionClasification;
    }

    public void setConsequence(String consequence) {
        this.consequence = consequence;
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

    public void setPk(FleetAccidentLogPK pk) {
        this.pk = pk;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}
