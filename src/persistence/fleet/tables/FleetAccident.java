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
import persistence.datatype.tables.AccidentClasification;
import persistence.datatype.tables.CollisionClasification;
import persistence.datatype.tables.FleetConsequence;

import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.user.UserSessionData;
import communication.datatype.tables.AccidentClasificationDataType;
import communication.datatype.tables.FleetConsequenceDataType;
import communication.fleet.tables.FleetAccidentData;

public class FleetAccident {

    private Integer id;
    
    private ColectiveFleet fleet;

    private Integer period;
    private Integer year;

    private Corporation corporation;
    
    private Country country;
    
    private DivOrgItemLevel1 divOrg1;
    private DivOrgItemLevel1 divOrg2;
    private DivOrgItemLevel1 divOrg3;
    
    private AccidentClasification clasification;
    private CollisionClasification collisionClasification;

    private FleetConsequence consequence;

    private Integer number;
    private Integer cost;

    public FleetAccident() {
    }


    public FleetAccident(FleetAccidentData accident, Session session, UserSessionData userSessionData) {
        id = accident.getId();
        fleet = (ColectiveFleet)session.load(ColectiveFleet.class,accident.getFleet().getId());
        period = accident.getPeriod();
        year = accident.getYear();
        corporation = (Corporation)session.load(Corporation.class,userSessionData.getFilter().getCorporationId());
        country = (Country)session.load(Country.class,accident.getCountry().getId());
        if(accident.getDivOrg1() != null) {
            divOrg1 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,accident.getDivOrg1().getId());
        }
        if(accident.getDivOrg2() != null) {
            divOrg2 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,accident.getDivOrg2().getId());
        }
        if(accident.getDivOrg3() != null) {
            divOrg3 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,accident.getDivOrg3().getId());
        }
        clasification = (AccidentClasification)session.load(AccidentClasification.class,accident.getClasification().getResourceBundleId());
        if(accident.getCollisionClasification() != null) {
            collisionClasification = (CollisionClasification)session.load(CollisionClasification.class,accident.getCollisionClasification().getResourceBundleId());
        }
        consequence = (FleetConsequence)session.load(FleetConsequence.class,accident.getConsequence().getResourceBundleId());
        number = accident.getNumber();
        cost = accident.getCost();
    }


    public AccidentClasification getClasification() {
        return clasification;
    }


    public FleetConsequence getConsequence() {
        return consequence;
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


    public void setClasification(AccidentClasification clasification) {
        this.clasification = clasification;
    }


    public void setConsequence(FleetConsequence consequence) {
        this.consequence = consequence;
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


    public FleetAccidentData getFleetAccidentData() {
        FleetAccidentData accident = new FleetAccidentData();

        accident.setId(id);
        accident.setFleet(fleet.getFleetData());
        
        if(country != null) {
            accident.setCountry(new GeoItemData(country.getId(),country.getResourceKey(),true,null));
        }
        
        String[][] s = new String[3][3];
        if(divOrg1 != null) {
            accident.setDivOrg1(new ItemData(divOrg1.getId(),divOrg1.getResourceKey(),true,null));
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
            accident.setDivOrg2(new ItemData(divOrg2.getId(),divOrg2.getResourceKey(),true,null));
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
            accident.setDivOrg3(new ItemData(divOrg3.getId(),divOrg3.getResourceKey(),true,null));
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
        accident.setData(s);
        
        accident.setClasification((AccidentClasificationDataType) clasification.getData());
        accident.setConsequence((FleetConsequenceDataType) consequence.getData());
        
        accident.setCost(cost);
        accident.setNumber(number);
        
        accident.setPeriod(period);
        accident.setYear(year);
        
        return accident;
    }


	/**
	 * @return Returns the collisionClasification.
	 */
	public CollisionClasification getCollisionClasification() {
		return collisionClasification;
	}


	/**
	 * @param collisionClasification The collisionClasification to set.
	 */
	public void setCollisionClasification(
			CollisionClasification collisionClasification) {
		this.collisionClasification = collisionClasification;
	}
    
}
