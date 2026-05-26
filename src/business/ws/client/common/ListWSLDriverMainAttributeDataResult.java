package business.ws.client.common;

import java.io.Serializable;

import business.ws.client.driver.WSLDriverData;
import business.ws.client.driver.WSLDriverMainAttributesData;

import communication.driver.tables.DriverData;
import communication.vehicle.tables.VehicleData;

public class ListWSLDriverMainAttributeDataResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private WSLDriverMainAttributesData[] elements;
	private int total;
	
	public ListWSLDriverMainAttributeDataResult(){
		
	}

	public WSLDriverMainAttributesData[] getElements() {
		return elements;
	}

	public void setElements(WSLDriverMainAttributesData[] elements) {
		this.elements = elements;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
	

}
