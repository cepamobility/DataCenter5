/**
 * Created on 06-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.vehicle;

import java.util.Hashtable;

import presentation.translator.web.util.DivOrgForm;
import presentation.translator.web.util.Util;

import communication.administration.bookmark.tables.BookmarkData;
import communication.vehicle.tables.VehicleData;

public class VehicleMultiReportForm extends DivOrgForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
    private String trade;
    private String year;
    private String use;
    private String fuel;
    private String centerCost;
    private String plate;
    private String model;
    private String status;
    private String output;    
    private String reportType;
   
    // Si es forestal:
    private String plataformCheck;
    private String vehicleCheck;
    
    /**
	 * @return Returns the reportType.
	 */
	public String getReportType() {
		return reportType;
	}


	/**
	 * @param reportType The reportType to set.
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}


	public VehicleMultiReportForm() {
        status = String.valueOf(VehicleData.ALL);
    }


    /**
     * @return Returns the fuel.
     */
    public String getFuel() {
        return fuel;
    }


    /**
     * @return Returns the output.
     */
    public String getOutput() {
        return output;
    }


    /**
     * @return Returns the trade.
     */
    public String getTrade() {
        return trade;
    }


    /**
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }


    /**
     * @return Returns the use.
     */
    public String getUse() {
        return use;
    }


    /**
     * @return Returns the year.
     */
    public String getYear() {
        return year;
    }


    /**
     * @param fuel The fuel to set.
     */
    public void setFuel(String fuel) {
        this.fuel = fuel;
    }


    /**
     * @param output The output to set.
     */
    public void setOutput(String output) {
        this.output = output;
    }


    /**
     * @param trade The trade to set.
     */
    public void setTrade(String trade) {
        this.trade = trade;
    }


    /**
     * @param type The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }


    /**
     * @param use The use to set.
     */
    public void setUse(String use) {
        this.use = use;
    }


    /**
     * @param year The year to set.
     */
    public void setYear(String year) {
        this.year = year;
    }


    public String getCenterCost() {
        return centerCost;
    }


    public void setCenterCost(String centerCost) {
        this.centerCost = centerCost;
    }


	/**
	 * @return Returns the model.
	 */
	public String getModel() {
		return model;
	}


	/**
	 * @param model The model to set.
	 */
	public void setModel(String model) {
		this.model = model;
	}


	/**
	 * @return Returns the plate.
	 */
	public String getPlate() {
		return plate;
	}


	/**
	 * @param plate The plate to set.
	 */
	public void setPlate(String plate) {
		this.plate = plate;
	}
	
	
	   public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }
    
    


    /**
	 * @return Returns the plataformCheck.
	 */
	public String getPlataformCheck() {
		return plataformCheck;
	}


	/**
	 * @param plataformCheck The plataformCheck to set.
	 */
	public void setPlataformCheck(String plataformCheck) {
		this.plataformCheck = plataformCheck;
	}


	/**
	 * @return Returns the vehicleCheck.
	 */
	public String getVehicleCheck() {
		return vehicleCheck;
	}


	/**
	 * @param vehicleCheck The vehicleCheck to set.
	 */
	public void setVehicleCheck(String vehicleCheck) {
		this.vehicleCheck = vehicleCheck;
	}


	/*
	 *  (non-Javadoc)
	 * @see presentation.translator.web.driver.DriverDivOrgForm#getLink()
	 * Genera un link con los parametros del form
	 */
	public String getLink() {
	        StringBuffer link = new StringBuffer("");
	        link.append((!Util.empty(type)) ? "&type="+type : "");
	        link.append((!Util.empty(trade)) ? "&tr="+trade : "");
	        link.append((!Util.empty(year)) ? "&y="+year : "");
	        link.append((!Util.empty(use)) ? "&u="+use : "");
	        link.append((!Util.empty(fuel)) ? "&f="+fuel : "");
	        link.append((!Util.empty(centerCost)) ? "&cC="+centerCost : "");
	        link.append((!Util.empty(plate)) ? "&pl="+plate : "");
	        link.append((!Util.empty(model)) ? "&m="+model : "");
	        link.append((!Util.empty(output)) ? "&o="+output : "");
            link.append((!Util.empty(reportType)) ? "&rT="+reportType : "");
            link.append((!Util.empty(status)) ? "&status="+status : "");
            // Para forestales:
            link.append((!Util.empty(plataformCheck))?"&pc="+plataformCheck:"");
            link.append((!Util.empty(vehicleCheck))?"&vc="+vehicleCheck:"");
            // fin para forestales
	        link.append(super.getLink());
	        return link.toString();
	    }
	
    /**
     * @param divOrg11
     * @param divOrg12
     * @param divOrg13
     * @param divOrg21
     * @param divOrg22
     * @param divOrg23
     * @param divOrg31
     * @param divOrg32
     * @param divOrg33
     * @param country1
     * @param country2
     * @param country3
     */
    public VehicleMultiReportForm(String[] divOrg11, String[] divOrg12, String[] divOrg13, String[] divOrg21, String[] divOrg22, String[] divOrg23, String[] divOrg31, String[] divOrg32, String[] divOrg33, String[] country1, String[] country2, String[] country3) {
        super(divOrg11, divOrg12, divOrg13, divOrg21, divOrg22, divOrg23, divOrg31, divOrg32, divOrg33, country1, country2, country3);
    }

    public VehicleMultiReportForm(BookmarkData bookmark) {
        super(bookmark);
        Hashtable parameters = bookmark.getParameters();
        if(parameters.containsKey("type")) {
            type = (String)parameters.get("type"); 
        }
        if(parameters.containsKey("trade")) {
            trade = (String)parameters.get("trade"); 
        }
        if(parameters.containsKey("year")) {
            year = (String)parameters.get("year"); 
        }
        if(parameters.containsKey("use")) {
            use = (String)parameters.get("use"); 
        }
        if(parameters.containsKey("fuel")) {
            fuel = (String)parameters.get("fuel"); 
        }
        if(parameters.containsKey("centerCost")) {
            centerCost = (String)parameters.get("centerCost"); 
        }
        if(parameters.containsKey("plate")) {
            plate = (String)parameters.get("plate"); 
        }
        if(parameters.containsKey("model")) {
            model = (String)parameters.get("model"); 
        }
        if(parameters.containsKey("status")) {
            status = (String)parameters.get("status"); 
        }
        if(parameters.containsKey("output")) {
            output = (String)parameters.get("output"); 
        }
        if(parameters.containsKey("reportType")) {
            reportType = (String)parameters.get("reportType"); 
        }
        if(parameters.containsKey("plataformCheck")) {
            plataformCheck = (String)parameters.get("plataformCheck"); 
        }
        if(parameters.containsKey("vehicleCheck")) {
            vehicleCheck = (String)parameters.get("vehicleCheck"); 
        }
    }
    
    public BookmarkData getBookmark() {
        BookmarkData data = super.getBookmark(BookmarkData.VEHICLE_REPORT);
        if(!Util.empty(type)) {
            data.addParameter("type",type);
        }
        if(!Util.empty(trade)) {
            data.addParameter("trade",trade);
        }
        if(!Util.empty(year)) {
            data.addParameter("year",year);
        }
        if(!Util.empty(use)) {
            data.addParameter("use",use);
        }
        if(!Util.empty(fuel)) {
            data.addParameter("fuel",fuel);
        }
        if(!Util.empty(centerCost)) {
            data.addParameter("centerCost",centerCost);
        }
        if(!Util.empty(plate)) {
            data.addParameter("plate",plate);
        }
        if(!Util.empty(model)) {
            data.addParameter("model",model);
        }
        if(!Util.empty(status)) {
            data.addParameter("status",status);
        }
        if(!Util.empty(output)) {
            data.addParameter("output",output);
        }
        if(!Util.empty(reportType)) {
            data.addParameter("reportType",reportType);
        }
        if(!Util.empty(plataformCheck)) {
            data.addParameter("plataformCheck",plataformCheck);
        }
        if(!Util.empty(vehicleCheck)) {
            data.addParameter("vehicleCheck",vehicleCheck);
        }
        return data;
    }
      

    
}
