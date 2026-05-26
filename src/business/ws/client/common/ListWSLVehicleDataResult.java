package business.ws.client.common;

import java.io.Serializable;


import business.ws.client.vehicle.WSLVehicleData;


public class ListWSLVehicleDataResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private WSLVehicleData[] elements;
	private int total;
	
	public ListWSLVehicleDataResult(){
		
	}

	public WSLVehicleData[] getElements() {
		return elements;
	}

	public void setElements(WSLVehicleData[] elements) {
		this.elements = elements;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
	

}
