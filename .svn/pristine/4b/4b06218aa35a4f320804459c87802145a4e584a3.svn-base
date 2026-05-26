package communication.driver.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.CountryData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemData;
import communication.administration.corporation.util.Util;
import communication.administration.group.tables.GroupLineData;
import communication.administration.user.UserSessionData;
import communication.datatype.tables.AssignedVehicleDataType;
import communication.datatype.tables.ContractDataType;
import communication.datatype.tables.EducationLevelDataType;
import communication.datatype.tables.MaritalStatusDataType;
import communication.datatype.tables.PuestoPepsicoDataType;
import communication.datatype.tables.SexDataType;
import communication.datatype.tables.TypeVehicleDataType;
import communication.util.Validate;

public class DriverLogisticData  implements Serializable{

    private static final long serialVersionUID = 1L;

	public static final Integer ASSIGNED = 0;
	public static final Integer MISSING = 1;
	public static final Integer EMPTY = 2;
	public static final Integer WAITING = 3;

	private Integer driverId;
	private String activity;
	private Date dateLastSchedule;
    private String city;
    private String observations;
    private Integer status;
    
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getDriverId() {
		return driverId;
	}
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
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
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
    
	
		
}
