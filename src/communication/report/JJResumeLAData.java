package communication.report;
//
public class JJResumeLAData {
	
	private Double cpmmGoal;
	private Double ipmmGoal;
	private Double cpmm;
	private Double ipmm;
	private Double totalMiles;
	private Double avMilesPerVehicle;
	private Integer totalAccident;
	private Integer totalIncident;
	private Integer totalVehicles;
	private Double percentageVehicleInCrashesAP;
	private Integer numberCrashesWithInjuries;
	
	public JJResumeLAData(){
		cpmmGoal=0d;
		ipmmGoal=0d;
		cpmm=0d;
		ipmm=0d;
		totalMiles=0d;
		totalVehicles=0;
		avMilesPerVehicle=0d;
		totalAccident=0;
		totalIncident=0;
		percentageVehicleInCrashesAP=0d;
		numberCrashesWithInjuries=0;
			
	}
	
	
	public Integer getTotalVehicles() {
		return totalVehicles;
	}


	public void setTotalVehicles(Integer totalVehicles) {
		this.totalVehicles = totalVehicles;
	}


	public Double getCpmmGoal() {
		return cpmmGoal;
	}
	public void setCpmmGoal(Double cpmmGoal) {
		this.cpmmGoal = cpmmGoal;
	}
	public Double getIpmmGoal() {
		return ipmmGoal;
	}
	public void setIpmmGoal(Double ipmmGoal) {
		this.ipmmGoal = ipmmGoal;
	}
	public Double getCpmm() {
		return cpmm;
	}
	public void setCpmm(Double cpmm) {
		this.cpmm = cpmm;
	}
	public Double getIpmm() {
		return ipmm;
	}
	public void setIpmm(Double ipmm) {
		this.ipmm = ipmm;
	}
	public Double getTotalMiles() {
		return totalMiles;
	}
	public void setTotalMiles(Double totalMiles) {
		this.totalMiles = totalMiles;
	}
	
	public Double getAvMilesPerVehicle() {
		return avMilesPerVehicle;
	}
	public void setAvMilesPerVehicle(Double avMilesPerVehicle) {
		this.avMilesPerVehicle = avMilesPerVehicle;
	}
	public Integer getTotalAccident() {
		return totalAccident;
	}
	public void setTotalAccident(Integer totalAccident) {
		this.totalAccident = totalAccident;
	}
	public Integer getTotalIncident() {
		return totalIncident;
	}
	public void setTotalIncident(Integer totalIncident) {
		this.totalIncident = totalIncident;
	}
	public Double getPercentageVehicleInCrashesAP() {
		return percentageVehicleInCrashesAP;
	}
	public void setPercentageVehicleInCrashesAP(Double percentageVehicleInCrashesAP) {
		this.percentageVehicleInCrashesAP = percentageVehicleInCrashesAP;
	}
	public Integer getNumberCrashesWithInjuries() {
		return numberCrashesWithInjuries;
	}
	public void setNumberCrashesWithInjuries(Integer numberCrashesWithInjuries) {
		this.numberCrashesWithInjuries = numberCrashesWithInjuries;
	}
	
	
	
}
