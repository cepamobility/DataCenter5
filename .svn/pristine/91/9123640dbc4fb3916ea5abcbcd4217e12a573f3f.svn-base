/**
 * Created on 27-jul-2007
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.vehicle;

import java.util.Date;

import org.apache.struts.action.ActionForm;

import presentation.translator.web.util.Util;

public class VehicleCheckListForm  extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String id;
    
    private String vehicle;
    private String name;
    private String date;
    private String month;
    private String year;
    private String obs;


    public VehicleCheckListForm() {
    }


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getVehicle() {
		return vehicle;
	}


	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
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

	public Date getCheckDate() {
		return Util.getDate(date, month, year);
	}


	public String getObs() {
		return obs;
	}


	public void setObs(String obs) {
		this.obs = obs;
	}
}
