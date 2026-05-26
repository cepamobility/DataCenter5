package business.ws.client.common;

import java.io.Serializable;

import business.ws.client.driver.WSLDriverData;

import communication.driver.tables.DriverData;
import communication.vehicle.tables.VehicleData;

public class ListWSLDriverDataResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private WSLDriverData[] elements;
	private int total;
	
	public ListWSLDriverDataResult(){
		
	}

	public WSLDriverData[] getElements() {
		return elements;
	}

	public void setElements(WSLDriverData[] elements) {
		this.elements = elements;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
	

}
