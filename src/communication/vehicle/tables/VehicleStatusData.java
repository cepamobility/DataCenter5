package communication.vehicle.tables;

import java.util.ArrayList;

import communication.administration.corporation.tables.PointAccountData;
import communication.report.UnconformityAction;


public class VehicleStatusData  {

	private VehicleData vehicle;
	private VehicleStateData state;
    private ArrayList<UnconformityAction> unconformities;
    private PointAccountData pointAccount;
    
    
	public VehicleData getVehicle() {
		return vehicle;
	}
	public void setVehicle(VehicleData vehicle) {
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
