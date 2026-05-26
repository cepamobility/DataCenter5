/**
 * Created on 09-mar-2007
 * CEPA
 * DataCenter 5
 */
package persistence.fleet.tables;

import org.hibernate.classic.Session;

import persistence.administration.corporation.tables.Corporation;
import persistence.administration.corporation.tables.Country;
import persistence.administration.corporation.tables.DivOrgItemLevel1;
import persistence.datatype.tables.TypeFuel;
import persistence.datatype.tables.TypeVehicle;

import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.user.UserSessionData;
import communication.datatype.tables.TypeFuelDataType;
import communication.datatype.tables.TypeVehicleDataType;
import communication.fleet.tables.ConsumedFuelData;

public class ConsumedFuel {

    private Integer id;

    private ColectiveFleet fleet;

    private Integer period;
    private Integer year;

    private Corporation corporation;
    private Country country;
    private DivOrgItemLevel1 divOrg1;
    private DivOrgItemLevel1 divOrg2;
    private DivOrgItemLevel1 divOrg3;

    private TypeVehicle typeVehicle;
    private Integer vehicles;
    private Integer miles;

    private TypeFuel fuel;                        
    private Integer liters;

    public ConsumedFuel() {
    }

    public ConsumedFuel(ConsumedFuelData data, Integer fleetId, Session session, UserSessionData userSessionData) {
        id = data.getId();
        fleet = (ColectiveFleet)session.load(ColectiveFleet.class,fleetId);
        period = data.getPeriod();
        year = data.getYear();
        corporation = (Corporation)session.load(Corporation.class,userSessionData.getFilter().getCorporationId());
        country = (Country)session.load(Country.class,data.getCountry().getId());
        if(data.getDivOrg1() != null) {
            divOrg1 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,data.getDivOrg1().getId());
        }
        if(data.getDivOrg2() != null) {
            divOrg2 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,data.getDivOrg2().getId());
        }
        if(data.getDivOrg3() != null) {
            divOrg3 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,data.getDivOrg3().getId());
        }
        
        typeVehicle = (TypeVehicle)session.load(TypeVehicle.class,data.getTypeVehicle().getResourceBundleId());
        vehicles = data.getVehicles();
        miles = data.getMiles();

        fuel = (TypeFuel)session.load(TypeFuel.class,data.getFuel().getResourceBundleId());
        liters = data.getLiters();
        
    }
    
    public Corporation getCorporation() {
        return corporation;
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

    public TypeFuel getFuel() {
        return fuel;
    }

    public Integer getId() {
        return id;
    }

    public Integer getLiters() {
        return liters;
    }

    public Integer getMiles() {
        return miles;
    }

    public Integer getPeriod() {
        return period;
    }

    public TypeVehicle getTypeVehicle() {
        return typeVehicle;
    }

    public Integer getVehicles() {
        return vehicles;
    }

    public Integer getYear() {
        return year;
    }

    public void setCorporation(Corporation corporation) {
        this.corporation = corporation;
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

    public void setFuel(TypeFuel fuel) {
        this.fuel = fuel;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLiters(Integer liters) {
        this.liters = liters;
    }

    public void setMiles(Integer miles) {
        this.miles = miles;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public void setTypeVehicle(TypeVehicle typeVehicle) {
        this.typeVehicle = typeVehicle;
    }

    public void setVehicles(Integer vehicles) {
        this.vehicles = vehicles;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public ConsumedFuelData getData() {
        ConsumedFuelData data = new ConsumedFuelData();
        data.setId(id);

        data.setPeriod(period);
        data.setYear(year);

        if(country != null) {
            data.setCountry(new GeoItemData(country.getId(),country.getResourceKey(),true,null));
        }
        
        String[][] s = new String[3][3];
        if(divOrg1 != null) {
            data.setDivOrg1(new ItemData(divOrg1.getId(),divOrg1.getResourceKey(),true,null));
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
            data.setDivOrg2(new ItemData(divOrg2.getId(),divOrg2.getResourceKey(),true,null));
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
            data.setDivOrg3(new ItemData(divOrg3.getId(),divOrg3.getResourceKey(),true,null));
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
        data.setData(s);

        if(typeVehicle != null) {
            data.setTypeVehicle((TypeVehicleDataType) typeVehicle.getData());
        }
        data.setVehicles(vehicles);
        data.setMiles(miles);

        if(fuel != null) {
            data.setFuel((TypeFuelDataType)fuel.getData());
        }
        data.setLiters(liters);
        return data;
    }
}
