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
import communication.driver.tables.DriverMainAttributes;
import communication.driver.tables.DriverPrizeData;

/**
 * @author jrodriguez
 */
public class ClosedDriverPK implements Serializable {

	private Integer id;
	private Integer period;
	private Integer year;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getPeriod() {
		return period;
	}
	
	public void setPeriod(Integer period) {
		this.period = period;
	}
	
	public Integer getYear() {
		return year;
	}
	
	public void setYear(Integer year) {
		this.year = year;
	}

}
