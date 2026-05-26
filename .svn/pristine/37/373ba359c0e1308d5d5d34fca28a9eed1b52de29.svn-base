/**
 * Created on 27-jul-2007
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.vehicle;

import java.util.Calendar;

import org.apache.struts.action.ActionForm;

import presentation.translator.web.util.Util;

import communication.vehicle.tables.VehicleData;
import communication.vehicle.tables.VehicleMaintenanceData;

public class VehicleMaintenanceForm  extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String id;
	private String vehicle;
    
    private String date;
    private String month;
    private String year;

    private String cost;
    private String type;

    private String item1;
    private String item2;
    private String item3;
    private String item4;
    private String item5;
    private String item6;
    private String item7;
    private String item8;
    private String item9;

    private String kms;

    public VehicleMaintenanceForm() {
    }

    public VehicleMaintenanceForm(VehicleMaintenanceData data) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(data.getDate());
    	date = String.valueOf(c.get(Calendar.DATE));
    	month = String.valueOf(c.get(Calendar.MONTH)+1);
    	year = String.valueOf(c.get(Calendar.YEAR));
    	cost = String.valueOf(data.getCost());
    	type = String.valueOf(data.getType());
    	kms = (data.getKms() != null) ? String.valueOf(data.getKms()) : "";
    	item1 = String.valueOf(data.isItem1());
    	item2 = String.valueOf(data.isItem2());
    	item3 = String.valueOf(data.isItem3());
    	item4 = String.valueOf(data.isItem4());
    	item5 = String.valueOf(data.isItem5());
    	item6 = String.valueOf(data.isItem6());
    	item7 = String.valueOf(data.isItem7());
    	item8 = String.valueOf(data.isItem8());
    	item9 = String.valueOf(data.isItem9());
    }
    
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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


	public String getCost() {
		return cost;
	}


	public void setCost(String cost) {
		this.cost = cost;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getItem1() {
		return item1;
	}


	public void setItem1(String item1) {
		this.item1 = item1;
	}


	public String getItem2() {
		return item2;
	}


	public void setItem2(String item2) {
		this.item2 = item2;
	}


	public String getItem3() {
		return item3;
	}


	public void setItem3(String item3) {
		this.item3 = item3;
	}


	public String getItem4() {
		return item4;
	}


	public void setItem4(String item4) {
		this.item4 = item4;
	}


	public String getItem5() {
		return item5;
	}


	public void setItem5(String item5) {
		this.item5 = item5;
	}


	public String getItem6() {
		return item6;
	}


	public void setItem6(String item6) {
		this.item6 = item6;
	}


	public String getItem7() {
		return item7;
	}


	public void setItem7(String item7) {
		this.item7 = item7;
	}


	public String getItem8() {
		return item8;
	}


	public void setItem8(String item8) {
		this.item8 = item8;
	}

	public VehicleMaintenanceData getCreateData() {
		VehicleMaintenanceData data = new VehicleMaintenanceData();
		data.setVehicle(new VehicleData(new Integer(id)));
		data.setDate(Util.getDate(date, month, year));
		data.setCost(new Integer(cost));
		data.setType(new Integer(type));
		data.setItem1(Util.isChecked(item1));
		data.setItem2(Util.isChecked(item2));
		data.setItem3(Util.isChecked(item3));
		data.setItem4(Util.isChecked(item4));
		data.setItem5(Util.isChecked(item5));
		data.setItem6(Util.isChecked(item6));
		data.setItem7(Util.isChecked(item7));
		data.setItem8(Util.isChecked(item8));
		data.setItem9(Util.isChecked(item9));
		if(Util.isNumber(kms)) {
			data.setKms(new Integer(kms));
		}
		return data;
	}

	public VehicleMaintenanceData getUpdateData() {
		VehicleMaintenanceData data = new VehicleMaintenanceData();
		data.setId(new Integer(id));
		data.setVehicle(new VehicleData(new Integer(vehicle)));
		data.setDate(Util.getDate(date, month, year));
		data.setCost(new Integer(cost));
		data.setType(new Integer(type));
		if(Integer.parseInt(type) == VehicleMaintenanceData.RUTINA) {
			data.setItem1(false);
			data.setItem2(false);
			data.setItem3(false);
			data.setItem4(false);
			data.setItem5(false);
			data.setItem6(false);
			data.setItem7(false);
			data.setItem8(false);
			data.setItem9(false);
			if(Util.isNumber(kms)) {
				data.setKms(new Integer(kms));
			}
		}else {
			data.setItem1(Util.isChecked(item1));
			data.setItem2(Util.isChecked(item2));
			data.setItem3(Util.isChecked(item3));
			data.setItem4(Util.isChecked(item4));
			data.setItem5(Util.isChecked(item5));
			data.setItem6(Util.isChecked(item6));
			data.setItem7(Util.isChecked(item7));
			data.setItem8(Util.isChecked(item8));
			data.setItem9(Util.isChecked(item9));
			data.setKms(null);
		}
		return data;
	}

	public String validate() {
		return null;
	}


	public String getItem9() {
		return item9;
	}


	public void setItem9(String item9) {
		this.item9 = item9;
	}


	public String getKms() {
		return kms;
	}


	public void setKms(String kms) {
		this.kms = kms;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

}
