package presentation.translator.web.vehicle;

import java.util.Calendar;

import org.apache.struts.action.ActionForm;

import communication.language.Text;
import communication.vehicle.tables.VehicleData;
import communication.vehicle.tables.VehiclePursuitRouteData;

public class VehiclePursuitRouteListForm extends ActionForm{

    private static final long serialVersionUID = 1L;	
    private String id;	
	private String divorg1;
	private String day;
	private String month;
	private String year;
	private String evaluationhour;
	private String evaluationminute;	
	private String instructor;
	private String area;
	private String obs;
	private String plate;
	
	private String list;
	
	
	public VehiclePursuitRouteListForm() {
		super();		
	}
	
   
    public VehiclePursuitRouteListForm(VehiclePursuitRouteData vehicleCheck, Text messages) {
        id = String.valueOf(vehicleCheck.getPursuitRouteId());
        Calendar c = Calendar.getInstance();
        c.setTime(vehicleCheck.getDate());
        day = String.valueOf(c.get(Calendar.DATE));
        month = String.valueOf(c.get(Calendar.MONTH)+1);
        year = String.valueOf(c.get(Calendar.YEAR));       
        obs = vehicleCheck.getObs();
        area = vehicleCheck.getArea();
        instructor = vehicleCheck.getInstructor();
        divorg1 = vehicleCheck.getDivorg1();  
        
        c.setTime(vehicleCheck.getEvaluationHour());
    	evaluationhour = String.valueOf(c.get(Calendar.HOUR));
    	evaluationminute = String.valueOf(c.get(Calendar.MINUTE));
        
    }
    
    public Integer getPursuitId() {
        if(id != null && id.length() > 0) {
            return new Integer(id);
        }
        return null;
    }
    
    
    /*
    public VehiclePursuitRouteListForm(VehicleData vehicle) {
        this.model = vehicle.getModel();
        this.trade = vehicle.getTrade().getResourceBundleId();
        this.plate = vehicle.getMatricula();        
    }
    */


	/**
	 * @return Returns the area.
	 */
	public String getArea() {
		return area;
	}


	/**
	 * @param area The area to set.
	 */
	public void setArea(String area) {
		this.area = area;
	}


	/**
	 * @return Returns the day.
	 */
	public String getDay() {
		return day;
	}


	/**
	 * @param day The day to set.
	 */
	public void setDay(String day) {
		this.day = day;
	}


	/**
	 * @return Returns the divorg1.
	 */
	public String getDivorg1() {
		return divorg1;
	}


	/**
	 * @param divorg1 The divorg1 to set.
	 */
	public void setDivorg1(String divorg1) {
		this.divorg1 = divorg1;
	}

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return Returns the instructor.
	 */
	public String getInstructor() {
		return instructor;
	}


	/**
	 * @param instructor The instructor to set.
	 */
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}


	/**
	 * @return Returns the month.
	 */
	public String getMonth() {
		return month;
	}


	/**
	 * @param month The month to set.
	 */
	public void setMonth(String month) {
		this.month = month;
	}


	/**
	 * @return Returns the obs.
	 */
	public String getObs() {
		return obs;
	}


	/**
	 * @param obs The obs to set.
	 */
	public void setObs(String obs) {
		this.obs = obs;
	}


	/**
	 * @return Returns the year.
	 */
	public String getYear() {
		return year;
	}


	/**
	 * @param year The year to set.
	 */
	public void setYear(String year) {
		this.year = year;
	}

	public String getList() {
        return list;
    }


    public void setList(String list) {
        this.list = list;
    }
    
    public VehiclePursuitRouteListForm(VehicleData vehicle) {
        // this.model = vehicle.getModel();
        //this.trade = vehicle.getTrade().getResourceBundleId();
        this.plate = vehicle.getMatricula();
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


	public String getEvaluationhour() {
		return evaluationhour;
	}


	public void setEvaluationhour(String evaluationhour) {
		this.evaluationhour = evaluationhour;
	}


	public String getEvaluationminute() {
		return evaluationminute;
	}


	public void setEvaluationminute(String evaluationminute) {
		this.evaluationminute = evaluationminute;
	}
    
	
    

}
