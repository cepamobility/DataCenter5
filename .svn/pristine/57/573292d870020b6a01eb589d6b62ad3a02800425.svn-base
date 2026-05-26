package presentation.translator.web.vehicle;


import org.apache.struts.action.ActionForm;


public class DPACheckListAddVehicleForm extends ActionForm {

	 
	private static final long serialVersionUID = 1L;
	
	private String plate;
	private String model;
	private String type;
	private String year;
	private String brand;
	private String divOrg1;
	private String divOrg2;
	private String divOrg3;
		

    public DPACheckListAddVehicleForm() {
    	 
    }


	public String getPlate() {
		return plate;
	}


	public void setPlate(String plate) {
		this.plate = plate;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
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
	  
    
	public String validate(){
		if(plate==null || plate.trim().length()==0) return "generic.vehicle.vehicledata.emptyplate";
		if(model==null || model.trim().length()==0) return "generic.vehicle.vehicledata.emptymodel";
		if(type==null || type.trim().length()==0 || type.equals("0")) return "generic.vehicle.vehicledata.emptytype";
		if(year==null || year.trim().length()==0) return "generic.vehicle.vehicledata.emptyyear";
		if(brand==null || brand.trim().length()==0 || brand.equals("0")) return "generic.vehicle.vehicledata.emptybrand";
		if(divOrg1==null || divOrg1.trim().length()==0 || divOrg1.equals("0")) return "generic.vehicle.vehicledata.emptydivorg";
		
		try{
			Integer.valueOf(year);
		}catch(Exception ex){
			return "generic.vehicle.vehicledata.invalidyear";
		}
		
		return null;
	}


}
