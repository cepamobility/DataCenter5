/**
 * Created on 24-jul-2006
 * CEPA
 * DataCenter 5
 */
package persistence.fleet.tables;

import org.hibernate.Session;

import communication.fleet.tables.FleetDivisionDetailData;


public class FleetDivisionDetail {

    private FleetDivisionDetailPK pk;

    private Integer vehicles;
    private Integer miles;
    
    private Integer accidents;
    private Integer incidents;
    private Integer nearmiss;
    
    private Double APMM;
    
    public FleetDivisionDetail() {
    }

    public FleetDivisionDetail(FleetDivisionDetailData detail, Session session) {
        
        FleetDivision division = (FleetDivision)session.load(FleetDivision.class,detail.getFleetDivision().getId());
        pk = new FleetDivisionDetailPK(division,detail.getPeriod(),detail.getYear());

        vehicles = detail.getVehicles();
        miles = detail.getMiles();
        
        accidents = (detail.getAccidents() != null)?detail.getAccidents():0;
        incidents = (detail.getIncidents() != null)?detail.getIncidents() :0;
        nearmiss = (detail.getNearmiss() != null)?detail.getNearmiss():0;
        
        APMM = detail.getAPMM();
    }

    public FleetDivisionDetail(FleetDivisionDetailPK pk,FleetDivisionDetailData detail) {
        this.pk = pk;

        vehicles = detail.getVehicles();
        miles = detail.getMiles();
        
        accidents = detail.getAccidents();
        incidents = detail.getIncidents();
        nearmiss = (detail.getNearmiss() != null)?detail.getNearmiss():0;
    }

    public Integer getAccidents() {
        return accidents;
    }

    public Double getAPMM() {
        return APMM;
    }

    public Integer getIncidents() {
        return incidents;
    }

    public Integer getMiles() {
        return miles;
    }

    public FleetDivisionDetailPK getPk() {
        return pk;
    }

    public Integer getVehicles() {
        return vehicles;
    }

    public void setAccidents(Integer accidents) {
        this.accidents = accidents;
    }

    public void setAPMM(Double apmm) {
        APMM = apmm;
    }

    public void setIncidents(Integer incidents) {
        this.incidents = incidents;
    }

    public void setMiles(Integer miles) {
        this.miles = miles;
    }

    public void setPk(FleetDivisionDetailPK pk) {
        this.pk = pk;
    }

    public void setVehicles(Integer vehicles) {
        this.vehicles = vehicles;
    }

    public FleetDivisionDetailData getData() {
        FleetDivisionDetailData data = new FleetDivisionDetailData();
        data.setFleetDivision(pk.getFleet().getData());
        data.setPeriod(pk.getPeriod());
        data.setYear(pk.getYear());
        data.setAccidents(accidents);
        data.setAPMM(APMM);
        data.setIncidents(incidents);
        data.setNearmiss(nearmiss);
        data.setMiles(miles);
        data.setVehicles(vehicles);
        return data;
    }

    public boolean equalData(FleetDivisionDetail divisionDetail) {
        if(vehicles.intValue() != divisionDetail.vehicles.intValue()) {
            return false;
        }
        if(miles.intValue() != divisionDetail.miles.intValue()) {
            return false;
        }
        if(accidents.intValue() != divisionDetail.accidents.intValue()) {
            return false;
        }
        if(incidents.intValue() != divisionDetail.incidents.intValue()) {
            return false;
        }
        if(nearmiss != null && divisionDetail.nearmiss != null && nearmiss.intValue() != divisionDetail.nearmiss.intValue()) {
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
