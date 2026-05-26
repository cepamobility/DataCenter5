package presentation.translator.web.vehicle;

import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;

import communication.administration.bookmark.tables.BookmarkData;
import communication.vehicle.tables.VehicleData;

public class SuctaMTOPQueryForm extends DriverDivOrgForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String plate;
    private String status;
    private String suctaormtop;
    private String output;    
    private String vehicleorplataform;
    
    private String frommonth;
    private String fromyear;

    private String tomonth;
    private String toyear;
    
   
	/**
	 * @param plate
	 * @param status
	 * @param suctaormtop
	 * @param output
	 * @param vehicleorplataform
	 * @param frommonth
	 * @param fromyear
	 * @param tomonth
	 * @param toyear
	 */
	public SuctaMTOPQueryForm(String plate, String status, String suctaormtop, 
			String output, String vehicleorplataform, String frommonth, 
			String fromyear, String tomonth, String toyear) {
		super();
		// TODO Auto-generated constructor stub
		this.plate = plate;
		this.status = status;
		this.suctaormtop = suctaormtop;
		this.output = output;
		this.vehicleorplataform = vehicleorplataform;
		this.frommonth = frommonth;
		this.fromyear = fromyear;
		this.tomonth = tomonth;
		this.toyear = toyear;
	}

    public SuctaMTOPQueryForm(BookmarkData data) {
        super(data);
        Hashtable parameters = data.getParameters();
        if(parameters.containsKey("plate")) {
            plate = (String)parameters.get("plate");
        }
        if(parameters.containsKey("status")) {
            status = (String)parameters.get("status");
        }
        if(parameters.containsKey("suctaormtop")) {
            suctaormtop = (String)parameters.get("suctaormtop");
        }
        if(parameters.containsKey("output")) {
            output = (String)parameters.get("output");
        }
        if(parameters.containsKey("vehicleorplataform")) {
            vehicleorplataform = (String)parameters.get("vehicleorplataform");
        }
        if(parameters.containsKey("frommonth")) {
            frommonth = (String)parameters.get("frommonth");
        }      
        if(parameters.containsKey("fromyear")) {
            fromyear = (String)parameters.get("fromyear");
        }
        if(parameters.containsKey("tomonth")) {
            tomonth = (String)parameters.get("tomonth");
        }
        if(parameters.containsKey("toyear")) {
            toyear = (String)parameters.get("toyear");
        }
    }

	public String getFrommonth() {
		return frommonth;
	}




	public void setFrommonth(String frommonth) {
		this.frommonth = frommonth;
	}




	public String getFromyear() {
		return fromyear;
	}




	public void setFromyear(String fromyear) {
		this.fromyear = fromyear;
	}




	public String getTomonth() {
		return tomonth;
	}




	public void setTomonth(String tomonth) {
		this.tomonth = tomonth;
	}




	public String getToyear() {
		return toyear;
	}




	public void setToyear(String toyear) {
		this.toyear = toyear;
	}




	public SuctaMTOPQueryForm() {
        status = String.valueOf(VehicleData.ALL);
        vehicleorplataform = "vehicle";
        suctaormtop = "sucta";
    }


   

    /*
	 *  (non-Javadoc)
	 * @see presentation.translator.web.driver.DriverDivOrgForm#getLink()
	 * Genera un link con los parametros del form
	 */
	public String getLink() {
	        StringBuffer link = new StringBuffer("");
	        link.append((!Util.empty(plate)) ? "&pl="+plate : "");
	        link.append((!Util.empty(suctaormtop)) ? "&sotm="+suctaormtop : "");
	        link.append((!Util.empty(output)) ? "&o="+output : "");
            link.append((!Util.empty(vehicleorplataform)) ? "&vp="+vehicleorplataform : "");
            link.append((!Util.empty(status)) ? "&status="+status : "");
            link.append((!Util.empty(this.tomonth)) ? "&tm="+tomonth : "");
            link.append((!Util.empty(this.toyear)) ? "&ty="+toyear : "");
            link.append((!Util.empty(this.frommonth)) ? "&fm="+frommonth : "");
            link.append((!Util.empty(this.fromyear)) ? "&fy="+fromyear : "");
            
            
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
    public SuctaMTOPQueryForm(String divOrg11, String divOrg12, String divOrg13, String divOrg21, String divOrg22, String divOrg23, String divOrg31, String divOrg32, String divOrg33, String country1, String country2, String country3) {
        super(divOrg11, divOrg12, divOrg13, divOrg21, divOrg22, divOrg23, divOrg31, divOrg32, divOrg33, country1, country2, country3);
    }


    /**
     * 
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
     * @param plate
     * @param frommonth
     * @param fromyear
     * @param tomonth
     * @param toyear
     * @param status
     * @param output
     * @param vehicleorplataform
     * @param suctaormtop
     */
    public SuctaMTOPQueryForm(String divOrg11, String divOrg12, String divOrg13,
    		String divOrg21, String divOrg22, String divOrg23, 
    		String divOrg31, String divOrg32, String divOrg33, 
    		String country1, String country2, String country3,
    		String plate, String frommonth,
    		String fromyear, String tomonth, String toyear, 
    		String status, String output, String vehicleorplataform, String suctaormtop) {
        super(divOrg11, divOrg12, divOrg13, divOrg21, divOrg22, divOrg23,
        		divOrg31, divOrg32, divOrg33, country1, country2, country3);
        this.plate = plate;
		this.frommonth = frommonth;
		this.fromyear = fromyear;
		this.tomonth = tomonth;
		this.toyear = toyear;
		this.status = status;
		this.output = output;
		this.vehicleorplataform = vehicleorplataform;
		this.suctaormtop = suctaormtop;
    }


	public String getOutput() {
		return output;
	}




	public void setOutput(String output) {
		this.output = output;
	}




	public String getPlate() {
		return plate;
	}




	public void setPlate(String plate) {
		this.plate = plate;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public String getSuctaormtop() {
		return suctaormtop;
	}




	public void setSuctaormtop(String suctaormtop) {
		this.suctaormtop = suctaormtop;
	}




	public String getVehicleorplataform() {
		return vehicleorplataform;
	}




	public void setVehicleorplataform(String vehicleorplataform) {
		this.vehicleorplataform = vehicleorplataform;
	}
      
	
	public String getStatusLabel(){
		if (status.equals(Integer.toString(VehicleData.ACTIVE))){
			return "generic.messages.active";
		}else if (status.equals(Integer.toString(VehicleData.ALL))){
			return "generic.messages.all.pl";
		}else{
			return "generic.messages.excluded";
		}
			
	}
    
    public Date getInitialDate() {
    	if (isEmpty(frommonth) || isEmpty(fromyear)) {
    		return null;
    	} else {
        	Calendar calendar = Calendar.getInstance();
    		calendar.set(Calendar.DATE,Integer.parseInt("1"));
    		calendar.set(Calendar.MONTH,Integer.parseInt(frommonth.trim())-1);
    		calendar.set(Calendar.YEAR,Integer.parseInt(fromyear.trim()));
            return calendar.getTime();
    	}
   }
    
    private boolean isEmpty(String value) {
	    return ((value == null) || (value.trim().length()==0));
	}
    
    public Date getLastDate() 
    {		
    	int year, month;
    	if (isEmpty(tomonth) || isEmpty(toyear)) 
    	{
    		return null;
    	} 
    	else
    	{	    		
    		year= Integer.parseInt(toyear.trim());
	 		month=Integer.parseInt(tomonth.trim())-1;
	 		
	 		if (month==12)
	 		{		 			
	 			month=1;
	 			year=year+1;
	 		}
	 		
	 		
        	Calendar calendar = Calendar.getInstance();
    		calendar.set(Calendar.MONTH,month);
    		calendar.set(Calendar.YEAR,year);
            return calendar.getTime();
    	}	    	
	}

    public BookmarkData getBookmark() {
        BookmarkData data = super.getBookmark(BookmarkData.SUCTA_MTOP_REPORT);
        if(!Util.empty(plate)) {
            data.addParameter("plate",plate);
        }
        if(!Util.empty(status)) {
            data.addParameter("status",status);
        }
        if(!Util.empty(suctaormtop)) {
            data.addParameter("suctaormtop",suctaormtop);
        }
        if(!Util.empty(output)) {
            data.addParameter("output",output);
        }
        if(!Util.empty(vehicleorplataform)) {
            data.addParameter("vehicleorplataform",vehicleorplataform);
        }
        if(!Util.empty(frommonth)) {
            data.addParameter("frommonth",frommonth);
        }      
        if(!Util.empty(fromyear)) {
            data.addParameter("fromyear",fromyear);
        }
        if(!Util.empty(tomonth)) {
            data.addParameter("tomonth",tomonth);
        }
        if(!Util.empty(toyear)) {
            data.addParameter("toyear",toyear);
        }
        return data;
    }
}

