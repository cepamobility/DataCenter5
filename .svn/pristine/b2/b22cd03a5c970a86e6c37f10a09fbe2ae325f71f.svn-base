package persistence.driver.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.hibernate.Session;

import persistence.administration.corporation.tables.Corporation;
import persistence.administration.corporation.tables.CorporationCountry;
import persistence.administration.corporation.tables.CorporationCountryPK;
import persistence.administration.corporation.tables.Country;
import persistence.administration.corporation.tables.DivOrgItemLevel1;
import persistence.administration.corporation.tables.GeoLevel1;
import persistence.datatype.tables.AssignedVehicle;
import persistence.datatype.tables.Contract;
import persistence.datatype.tables.EducationLevel;
import persistence.datatype.tables.MaritalStatus;
import persistence.datatype.tables.PuestoPepsico;
import persistence.datatype.tables.Sex;
import persistence.datatype.tables.TypeVehicle;
import persistence.hibernate.HibernateUtil;
import persistence.util.Util;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.CountryData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelAttributes;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemAttribute;
import communication.administration.corporation.tables.LabelItemData;
import communication.administration.user.UserSessionData;
import communication.datatype.tables.AssignedVehicleDataType;
import communication.datatype.tables.ContractDataType;
import communication.datatype.tables.EducationLevelDataType;
import communication.datatype.tables.MaritalStatusDataType;
import communication.datatype.tables.PuestoPepsicoDataType;
import communication.datatype.tables.SexDataType;
import communication.datatype.tables.TypeVehicleDataType;
import communication.driver.report.DetailedDriverReportDataType;
import communication.driver.report.DetailedDriverReportWithVehiclesDataType;
import communication.driver.report.DetailedFOSADriverReportDataType;
import communication.driver.report.DriverReportDataType;
import communication.driver.report.FOSADriverReportDataType;
import communication.driver.tables.AlcoholControlData;
import communication.driver.tables.AptitudTestData;
import communication.driver.tables.DriverData;
import communication.driver.tables.DriverLogisticData;
import communication.driver.tables.DriverMainAttributes;
import communication.driver.tables.DriverPrizeData;

/**
 * @author fcarriquiry
 */
public class DriverLogistic implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private DriverLogisticPK pk;
	private Date dateLastSchedule;
    private String city;
    private String observations;
    private Integer status;
    
    public DriverLogistic() {
	}

    public DriverLogistic(DriverLogisticData data) {
		pk = new DriverLogisticPK(data.getDriverId(),data.getActivity());
		this.dateLastSchedule=data.getDateLastSchedule();
		this.city=data.getCity();
		this.status=data.getStatus();
		this.observations= data.getObservations();
   	}
    
    public DriverLogisticData getData(){
    	DriverLogisticData data=new DriverLogisticData();
    	data.setCity(city);
    	data.setDateLastSchedule(dateLastSchedule);
    	data.setDriverId(pk.getDriver().getId());
    	data.setActivity(pk.getActivity().getResourceBundleId());
    	data.setObservations(observations);
    	data.setStatus(status);
    	
    	return data;
    }
	public Date getDateLastSchedule() {
		return dateLastSchedule;
	}

	public void setDateLastSchedule(Date dateLastSchedule) {
		this.dateLastSchedule = dateLastSchedule;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public DriverLogisticPK getPk() {
		return pk;
	}

	public void setPk(DriverLogisticPK pk) {
		this.pk = pk;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void updateData(DriverLogisticData data) {
		pk = new DriverLogisticPK(data.getDriverId(),data.getActivity());
		this.dateLastSchedule=data.getDateLastSchedule();
		this.city=data.getCity();
		this.status=data.getStatus();
		this.observations= data.getObservations();
	}
    
}
