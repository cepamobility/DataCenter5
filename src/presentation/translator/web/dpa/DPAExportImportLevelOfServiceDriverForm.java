package presentation.translator.web.dpa;

import java.io.Serializable;

import java.util.Calendar;
import java.util.Date;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import persistence.util.Util;


public class DPAExportImportLevelOfServiceDriverForm extends ActionForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String exportImport="e";
	
	private FormFile file;
	private String day;
	private String month;
	private String year;
	private String divOrg1;
	private String divOrg2;
	private String divOrg3;
	private String exportVehicleOrDriver="V";
	private String telematicActive="Ativo";
	
	public DPAExportImportLevelOfServiceDriverForm(){
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());
		day=String.valueOf(cal.get(Calendar.DATE));
		month=String.valueOf(cal.get(Calendar.MONTH)+1);
		year=String.valueOf(cal.get(Calendar.YEAR));
	}

 
	public String getTelematicActive() {
		return telematicActive;
	}


	public void setTelematicActive(String telematicActive) {
		this.telematicActive = telematicActive;
	}


	public String getExportVehicleOrDriver() {
		return exportVehicleOrDriver;
	}


	public void setExportVehicleOrDriver(String exportVehicleOrDriver) {
		this.exportVehicleOrDriver = exportVehicleOrDriver;
	}


	public FormFile getFile() {
		return file;
	}


	public void setFile(FormFile file) {
		this.file = file;
	}


	public String getExportImport() {
		return exportImport;
	}


	public void setExportImport(String exportImport) {
		this.exportImport = exportImport;
	}


	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDivOrg1() {
		return divOrg1;
	}

	public void setDivOrg1(String divOrg1) {
		this.divOrg1 = divOrg1;
	}

	public String getDivOrg2() {
		return divOrg2;
	}

	public void setDivOrg2(String divOrg2) {
		this.divOrg2 = divOrg2;
	}

	public String getDivOrg3() {
		return divOrg3;
	}

	public void setDivOrg3(String divOrg3) {
		this.divOrg3 = divOrg3;
	}

	 
	 
	public Date makeDate(){
		return Util.getDate(year, month, day);
	}
	

}
