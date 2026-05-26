package communication.administration.corporation;



public class CalculateRetroactiveImpactData  {

    //Cantidad de horas que se pierden en penalizaciones
    Integer sumStopedHour;
    //Cantidad total de vehiculos evaluados
    Integer sumTotalVehicles;
    //Cantidad total suspensiones
    Integer sumTotalStopedVehicles;
    //Cantidad total de puntos restados
    Double sumTotalLessPointss;
    //Se usa para guardar por horas cuantos vehiculos se suspendieron
    Integer[][] suspendedXHours;
    
	public Integer getSumStopedHour() {
		return sumStopedHour;
	}
	public void setSumStopedHour(Integer sumStopedHour) {
		this.sumStopedHour = sumStopedHour;
	}
	public Integer getSumTotalVehicles() {
		return sumTotalVehicles;
	}
	public void setSumTotalVehicles(Integer sumTotalVehicles) {
		this.sumTotalVehicles = sumTotalVehicles;
	}
	public Integer getSumTotalStopedVehicles() {
		return sumTotalStopedVehicles;
	}
	public void setSumTotalStopedVehicles(Integer sumTotalStopedVehicles) {
		this.sumTotalStopedVehicles = sumTotalStopedVehicles;
	}
	public Double getSumTotalLessPointss() {
		return sumTotalLessPointss;
	}
	public void setSumTotalLessPointss(Double sumTotalLessPointss) {
		this.sumTotalLessPointss = sumTotalLessPointss;
	}
	public Integer[][] getSuspendedXHours() {
		return suspendedXHours;
	}
	public void setSuspendedXHours(Integer[][] suspendedXHours) {
		this.suspendedXHours = suspendedXHours;
	}

    
    
    
  
}
