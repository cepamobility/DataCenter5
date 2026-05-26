package communication.vehicle.tables;


import java.io.Serializable;


public class VehicleCheckReportGroupData implements Serializable {

	private String month;
	private String result;
	private String text;
	private Integer count;
	private String divOrgItem1;
	private String divOrgItem2;
	private String divOrgItem3;
	
	public VehicleCheckReportGroupData(){
		
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getDivOrgItem1() {
		return divOrgItem1;
	}

	public void setDivOrgItem1(String divOrgItem1) {
		this.divOrgItem1 = divOrgItem1;
	}

	public String getDivOrgItem2() {
		return divOrgItem2;
	}

	public void setDivOrgItem2(String divOrgItem2) {
		this.divOrgItem2 = divOrgItem2;
	}

	public String getDivOrgItem3() {
		return divOrgItem3;
	}

	public void setDivOrgItem3(String divOrgItem3) {
		this.divOrgItem3 = divOrgItem3;
	}

		
		
	

}
