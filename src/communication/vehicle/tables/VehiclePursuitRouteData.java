package communication.vehicle.tables;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import communication.util.DateValue;

import persistence.activity.tables.Item;
import persistence.activity.tables.ItemValueStr;


public class VehiclePursuitRouteData implements Serializable,DateValue,Comparable {

	private static final long serialVersionUID = 1L;

	private Integer pursuitRouteId;	
	// Contiene el Set de pares (item,valor):
	protected Collection itemValueSet = null;	
	protected Date date;	
	protected String divorg1;	//name
	protected String obs;
	protected VehicleData vehicle;	
	protected String instructor;	
	protected Date evaluationHour;
	protected String area;
   
	public VehiclePursuitRouteData(){
		super();
	}				
	
	/**
	 * @param pursuitRouteId
	 * @param itemValueSet
	 * @param date
	 * @param divorg1
	 * @param obs
	 * @param vehicle
	 * @param instructor
	 * @param evaluationHour
	 * @param area
	 */
	public VehiclePursuitRouteData(Integer pursuitRouteId, Collection itemValueSet, Date date, String divorg1, String obs, VehicleData vehicle, String instructor, Date evaluationHour, String area) {
		super();		
		this.pursuitRouteId = pursuitRouteId;
		this.itemValueSet = itemValueSet;
		this.date = date;
		this.divorg1 = divorg1;
		this.obs = obs;
		this.vehicle = vehicle;
		this.instructor = instructor;
		this.evaluationHour = evaluationHour;
		this.area = area;
	}
	
	

	public int compareTo(Object anotherobject){
		DateValue another = (DateValue)anotherobject; 
    	Date element1=another.getDate();
    	Date element2=this.getDate();
    	if (element1 instanceof Timestamp)
    		element1= new Date(element1.getTime());
    	if (element2 instanceof Timestamp)
    		element2= new Date(element2.getTime());
        return element1.compareTo(element2);    
      }

	/**
	 * Redefine el metodo equals. Dos checklist son iguales
	 * si y solo si tienen el mismo vehiculo (id del vehiculo)
	 * y tienen la misma fecha.
	 * @param o - Objeto
	 * @return boolean - True si son iguales, false en caso contrario.
	 * @author gbenaderet
	 */
	public boolean equals(Object o){
		VehiclePursuitRouteData vcd;
		if (o instanceof VehiclePursuitRouteData){
			vcd = (VehiclePursuitRouteData)o;
			// Asumo que es la misma checklist si esta asociada
			// al mismo vehiculo para la misma fecha.
			// Si las fechas son diferentes son checklist diferentes
			if (	
				(vcd.pursuitRouteId != null && this.pursuitRouteId != null
				&& vcd.pursuitRouteId.equals(this.pursuitRouteId) ) 
				||
				(vcd.getVehicle().getId().equals(this.getVehicle().getId())
				&& vcd.getDate().equals(this.getDate()))
				){
				return true;
			}else{
				return false;
			}
		}else
			return false;
	}
	
	
	/* Devuelve el valor (1,2,3 o null) de un item.
	 * Esto se utiliza para ls Commentary Drive donde los items
	 * pueden tomar valores 1 2 o 3 (segun el radiobuttom 
	 * que se indique: SI, NO, Necesita Mejorar.
	 * Para los demás casos devuelve null; 
	 */
	public String getValueFromItem(Item item)
	{
		if (this.itemValueSet != null)
		{
			Iterator it = this.itemValueSet.iterator();
			while (it.hasNext())
			{
				ItemValueStr itemValue = (ItemValueStr)it.next();
				if (itemValue.getItem().equals(item))
					return itemValue.getItemValue();
			}
		}
		return null;
		
	}

    public String getValueFromItem(Integer item) {
        if (itemValueSet != null) {
            Iterator it = itemValueSet.iterator();
            while (it.hasNext()) {
                ItemValueStr itemValue = (ItemValueStr)it.next();
                if(itemValue.getItem().getId().equals(item)) {
                    return itemValue.getItemValue();
                }
            }
        }
        return null;
    }

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
	 * @return Returns the date.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date The date to set.
	 */
	public void setDate(Date date) {
		this.date = date;
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
	 * @return Returns the evaluationHour.
	 */
	public Date getEvaluationHour() {
		return evaluationHour;
	}
	
	public int getEvaluationTimeHour(){
		Calendar c = Calendar.getInstance();
		c.setTime(this.evaluationHour);		
		return c.get(Calendar.HOUR_OF_DAY);
	}
	
	public int getEvaluationTimeMinute(){
		Calendar c = Calendar.getInstance();
		c.setTime(this.evaluationHour);		
		return c.get(Calendar.MINUTE);
	}


	/**
	 * @param evaluationHour The evaluationHour to set.
	 */
	public void setEvaluationHour(Date evaluationHour) {
		this.evaluationHour = evaluationHour;
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
	 * @return Returns the itemValueSet.
	 */
	public Collection getItemValueSet() {
		return itemValueSet;
	}

	/**
	 * @param itemValueSet The itemValueSet to set.
	 */
	public void setItemValueSet(Collection itemValueSet) {
		this.itemValueSet = itemValueSet;
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
	 * @return Returns the pursuitRouteId.
	 */
	public Integer getPursuitRouteId() {
		return pursuitRouteId;
	}

	/**
	 * @param pursuitRouteId The pursuitRouteId to set.
	 */
	public void setPursuitRouteId(Integer pursuitRouteId) {
		this.pursuitRouteId = pursuitRouteId;
	}

	/**
	 * @return Returns the vehicle.
	 */
	public VehicleData getVehicle() {
		return vehicle;
	}

	/**
	 * @param vehicle The vehicle to set.
	 */
	public void setVehicle(VehicleData vehicle) {
		this.vehicle = vehicle;
	}

    


	
	
	
}

