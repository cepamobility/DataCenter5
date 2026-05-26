package persistence.vehicle.tables;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import persistence.activity.tables.Item;
import persistence.activity.tables.ItemValueStr;
import persistence.hibernate.HibernateUtil;

import communication.activity.tables.ItemValueStrData;
import communication.vehicle.tables.VehiclePursuitRouteData;

public class VehiclePursuitRoute implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer pursuitRouteId;	
	// Contiene el Set de pares (item,valor):
	protected Set itemValueSet = null;	
	protected Date date;	
	protected String divorg1;	
	protected String obs;
	protected Vehicle vehicle;	
	protected String instructor;	
	protected Date evaluationHour;
	protected String area;
  
    
	public void updateData(VehiclePursuitRouteData data){
		this.pursuitRouteId = data.getPursuitRouteId();
		
		Session session = HibernateUtil.currentSession();
		// itemValueSet
		itemValueSet = new HashSet();
		if (data.getItemValueSet() != null) {
			Iterator it = data.getItemValueSet().iterator();
	    	while(it.hasNext()){
	    		ItemValueStrData itemValueData = (ItemValueStrData)it.next();    		
	    		Item item = (Item)session.load(Item.class,itemValueData.getItem().getId());
	    		String value = itemValueData.getItemValue();
	    		ItemValueStr itemValue = new ItemValueStr(item,value);
	    		itemValueSet.add(itemValue);	    	
	    	}
		}
		this.date = data.getDate();
		this.obs = data.getObs();
		this.vehicle = new Vehicle(data.getVehicle());
		this.divorg1 = data.getDivorg1();
		this.instructor = data.getInstructor();
		this.evaluationHour = data.getEvaluationHour();
		this.area = data.getArea();
		
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
	public Set getItemValueSet() {
		return itemValueSet;
	}


	/**
	 * @param itemValueSet The itemValueSet to set.
	 */
	public void setItemValueSet(Set itemValueSet) {
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
	public Vehicle getVehicle() {
		return vehicle;
	}


	/**
	 * @param vehicle The vehicle to set.
	 */
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}


	public VehiclePursuitRoute() {
		super();
	}
	
	
	
	// Devuelve una CEPAActivityData con los items sin cargar
	public VehiclePursuitRouteData getVehiclePursuitRouteData() { 
		VehiclePursuitRouteData vcd = new VehiclePursuitRouteData();
		vcd.setPursuitRouteId(this.pursuitRouteId);
		vcd.setVehicle(this.vehicle.getVehicleData());
		vcd.setDate(this.date);
		vcd.setObs(this.obs);
		vcd.setItemValueSet(itemValueSet);
		vcd.setDivorg1(this.divorg1);
		vcd.setInstructor (this.instructor);	
		vcd.setEvaluationHour(this.evaluationHour);
		vcd.setArea(this.area);
		
		return vcd;
	}	
	
	public VehiclePursuitRoute(VehiclePursuitRouteData data)
		throws HibernateException,Exception {
		
		this.date = data.getDate();			
		this.obs = data.getObs();
		this.vehicle = new Vehicle(data.getVehicle());
		this.divorg1 = data.getDivorg1();
		this.instructor = data.getInstructor();
		this.evaluationHour = data.getEvaluationHour();
		this.area = data.getArea();

		Session session = HibernateUtil.currentSession();
				
		// itemValueSet
		itemValueSet = new HashSet();
		if (data.getItemValueSet() != null) {
			Iterator it = data.getItemValueSet().iterator();
	    	while(it.hasNext()){
	    		ItemValueStrData itemValueData = (ItemValueStrData)it.next();    		
	    		Item item = (Item)session.load(Item.class,itemValueData.getItem().getId());
	    		String value = itemValueData.getItemValue();
	    		ItemValueStr itemValue = new ItemValueStr(item,value);
	    		itemValueSet.add(itemValue);	    	
	    	}
		}
		else
			itemValueSet = null;      
       
	}

	
	
	public String getValue(int idItem) {
		String value = "";
		Iterator it = itemValueSet.iterator();
		boolean encontre = false;
		ItemValueStr itemValue = null;
		while (it.hasNext() && !encontre) {
			itemValue = (ItemValueStr) it.next();
			encontre = (itemValue.getItem().getId().intValue() == idItem);
			if (encontre) {
				value = itemValue.getItemValue();
			}
		}
		return value;
	}

	
}
