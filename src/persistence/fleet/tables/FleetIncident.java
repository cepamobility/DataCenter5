/**
 * Created on 24-jul-2006
 * CEPA
 * DataCenter 5
 */
package persistence.fleet.tables;

import org.hibernate.classic.Session;

import persistence.administration.corporation.tables.Corporation;
import persistence.administration.corporation.tables.Country;
import persistence.administration.corporation.tables.DivOrgItemLevel1;
import persistence.datatype.tables.IncidentClasification;

import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.user.UserSessionData;
import communication.datatype.tables.IncidentClasificationDataType;
import communication.fleet.tables.FleetIncidentData;

public class FleetIncident {

    private Integer id;
    
    private ColectiveFleet fleet;

    private Integer period;
    private Integer year;

    private Corporation corporation;
    
    private Country country;
    
    private DivOrgItemLevel1 divOrg1;
    private DivOrgItemLevel1 divOrg2;
    private DivOrgItemLevel1 divOrg3;
    
    private IncidentClasification clasification;

    private Integer number;
    private Integer cost;

    public FleetIncident() {
    }


    public FleetIncident(FleetIncidentData incident, Session session, UserSessionData userSessionData) {
        id = incident.getId();
        fleet = (ColectiveFleet)session.load(ColectiveFleet.class,incident.getFleet().getId());
        period = incident.getPeriod();
        year = incident.getYear();
        corporation = (Corporation)session.load(Corporation.class,userSessionData.getFilter().getCorporationId());
        country = (Country)session.load(Country.class,incident.getCountry().getId());
        if(incident.getDivOrg1() != null) {
            divOrg1 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,incident.getDivOrg1().getId());
        }
        if(incident.getDivOrg2() != null) {
            divOrg2 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,incident.getDivOrg2().getId());
        }
        if(incident.getDivOrg3() != null) {
            divOrg3 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,incident.getDivOrg3().getId());
        }
        clasification = (IncidentClasification)session.load(IncidentClasification.class,incident.getClasification().getResourceBundleId());
        number = incident.getNumber();
        cost = incident.getCost();
    }


    public IncidentClasification getClasification() {
        return clasification;
    }

    public Corporation getCorporation() {
        return corporation;
    }

    public Integer getCost() {
        return cost;
    }

    public Country getCountry() {
        return country;
    }


    public DivOrgItemLevel1 getDivOrg1() {
        return divOrg1;
    }


    public DivOrgItemLevel1 getDivOrg2() {
        return divOrg2;
    }

    public DivOrgItemLevel1 getDivOrg3() {
        return divOrg3;
    }


    public ColectiveFleet getFleet() {
        return fleet;
    }


    public Integer getId() {
        return id;
    }


    public Integer getNumber() {
        return number;
    }


    public Integer getPeriod() {
        return period;
    }


    public Integer getYear() {
        return year;
    }

    public void setClasification(IncidentClasification clasification) {
        this.clasification = clasification;
    }

    public void setCorporation(Corporation corporation) {
        this.corporation = corporation;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }


    public void setCountry(Country country) {
        this.country = country;
    }


    public void setDivOrg1(DivOrgItemLevel1 divOrg1) {
        this.divOrg1 = divOrg1;
    }

    public void setDivOrg2(DivOrgItemLevel1 divOrg2) {
        this.divOrg2 = divOrg2;
    }

    public void setDivOrg3(DivOrgItemLevel1 divOrg3) {
        this.divOrg3 = divOrg3;
    }


    public void setFleet(ColectiveFleet fleet) {
        this.fleet = fleet;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public void setNumber(Integer number) {
        this.number = number;
    }


    public void setPeriod(Integer period) {
        this.period = period;
    }


    public void setYear(Integer year) {
        this.year = year;
    }

    public FleetIncidentData getFleetIncidentData() {
        FleetIncidentData incident = new FleetIncidentData();

        incident.setId(id);
        incident.setFleet(fleet.getFleetData());
        
        if(country != null) {
            incident.setCountry(new GeoItemData(country.getId(),country.getResourceKey(),true,null));
        }
        
        String[][] s = new String[3][3];
        if(divOrg1 != null) {
            incident.setDivOrg1(new ItemData(divOrg1.getId(),divOrg1.getResourceKey(),true,null));
            if(divOrg1.getDivOrgItemLevel2() == null) {
                s[0][0] = divOrg1.getResourceKey();
            }else {
                if(divOrg1.getDivOrgItemLevel2().getDivOrgItemLevel3() == null) {
                    s[0][0] = divOrg1.getDivOrgItemLevel2().getResourceKey();
                    s[0][1] = divOrg1.getResourceKey();
                }else {
                    s[0][0] = divOrg1.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();
                    s[0][1] = divOrg1.getDivOrgItemLevel2().getResourceKey();
                    s[0][2] = divOrg1.getResourceKey();
                }
            }
        }
        if(divOrg2 != null) {
            incident.setDivOrg2(new ItemData(divOrg2.getId(),divOrg2.getResourceKey(),true,null));
            if(divOrg2.getDivOrgItemLevel2() == null) {
                s[1][0] = divOrg2.getResourceKey();
            }else {
                if(divOrg2.getDivOrgItemLevel2().getDivOrgItemLevel3() == null) {
                    s[1][0] = divOrg2.getDivOrgItemLevel2().getResourceKey();
                    s[1][1] = divOrg2.getResourceKey();
                }else {
                    s[1][0] = divOrg2.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();
                    s[1][1] = divOrg2.getDivOrgItemLevel2().getResourceKey();
                    s[1][2] = divOrg2.getResourceKey();
                }
            }
        }
        if(divOrg3 != null) {
            incident.setDivOrg3(new ItemData(divOrg3.getId(),divOrg3.getResourceKey(),true,null));
            if(divOrg3.getDivOrgItemLevel2() == null) {
                s[2][0] = divOrg3.getResourceKey();
            }else {
                if(divOrg3.getDivOrgItemLevel2().getDivOrgItemLevel3() == null) {
                    s[2][0] = divOrg3.getDivOrgItemLevel2().getResourceKey();
                    s[2][1] = divOrg3.getResourceKey();
                }else {
                    s[2][0] = divOrg3.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();
                    s[2][1] = divOrg3.getDivOrgItemLevel2().getResourceKey();
                    s[2][2] = divOrg3.getResourceKey();
                }
            }
        }
        incident.setData(s);
        
        incident.setClasification((IncidentClasificationDataType) clasification.getData());
        
        incident.setCost(cost);
        incident.setNumber(number);
        
        incident.setPeriod(period);
        incident.setYear(year);
        
        return incident;
    }
    
}
