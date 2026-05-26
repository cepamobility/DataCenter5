package business.ws.client.vehicle;

import java.io.Serializable;
import java.util.ArrayList;

import communication.administration.corporation.tables.PointAccountData;
import communication.report.UnconformityAction;
import communication.vehicle.tables.VehicleStateData;

public class WSLVehicleStatusData implements Serializable{

	private WSLVehicleData vehicle;
	private VehicleStateData state;
    private ArrayList<UnconformityAction> unconformities;
    private PointAccountData pointAccount;
    
	public WSLVehicleData getVehicle() {
		return vehicle;
	}
	public void setVehicle(WSLVehicleData vehicle) {
		this.vehicle = vehicle;
	}
	public VehicleStateData getState() {
		return state;
	}
	public void setState(VehicleStateData state) {
		this.state = state;
	}
	public ArrayList<UnconformityAction> getUnconformities() {
		return unconformities;
	}
	public void setUnconformities(ArrayList<UnconformityAction> unconformities) {
		this.unconformities = unconformities;
	}
	public PointAccountData getPointAccount() {
		return pointAccount;
	}
	public void setPointAccount(PointAccountData pointAccount) {
		this.pointAccount = pointAccount;
	}
    
		
		
}
