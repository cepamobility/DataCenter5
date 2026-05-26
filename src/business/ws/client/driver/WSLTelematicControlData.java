package business.ws.client.driver;

import java.io.Serializable;
import java.util.Date;

import business.ws.client.vehicle.WSLVehicleData;



public class WSLTelematicControlData implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final Integer TYPE_EMPTY = 0; // driver.telematiccontrol.emptytype
	public static final int TYPE_HARD_START = 1; // driver.telematiccontrol.hardstart
	public static final int TYPE_HARD_STOP = 2; // driver.telematiccontrol.hardstop
	public static final Integer TYPE_EXCESIVE_ROTATION = 3; //  driver.telematiccontrol.excesiverotation
	public static final Integer TYPE_BAD_DIRECTION = 4; // driver.telematiccontrol.baddirection	
	public static final Integer TYPE_OPERATIONAL = 5; // driver.telematiccontrol.operational 
	public static final Integer TYPE_RAIN = 8;  // driver.telematiccontrol.rain 
	public static final int TYPE_EXCESSIVE_SPEED = 7; 
	public static final Integer TYPE_HRD = 6;  // driver.telematiccontrol.hrd
	public static final int TYPE_WORK_HOURS = 9; 
	public static final int TYPE_FREE_TIME = 10; 
	public static final int TYPE_INSERTICIAL_REPORT = 11;
	public static final int TYPE_24HOOUR_WITHOUT_REPORT = 12;
	public static final int TYPE_WEEK_WORK = 13;

	public static final String COLOR_RED = "driver.telematiccontrol.red";
	public static final String COLOR_YELLOW = "driver.telematiccontrol.yellow";
	public static final String COLOR_GREEN = "driver.telematiccontrol.green";
	
	public static final String REPORT_TYPE_DETAILED = "detailed";
	public static final String REPORT_TYPE_RESUMED = "resumed";
	
	public static final String DPA_POINT_Gravissima_LABEL = "7 - Gravissima";
	public static final String DPA_POINT_Grave_LABEL = "5 - Grave";
	public static final String DPA_POINT_Media_LABEL = "3 - Media";
	public static final String DPA_POINT_Leve_LABEL = "1 - Leve";

	public static final String DPA_POINT_Gravissima_VALUE = "7";
	public static final String DPA_POINT_Grave_VALUE = "5";
	public static final String DPA_POINT_Media_VALUE = "3";
	public static final String DPA_POINT_Leve_VALUE = "1";
	
	public static final Integer RECOMENDATION_EMPTY = 0; // "driver.telematiccontrol.emptyrecomendation"
	public static final Integer RECOMENDATION_IPG = 1;  // driver.telematiccontrol.ipg
	public static final Integer RECOMENDATION_DCT = 2; // driver.telematiccontrol.dct
	public static final Integer RECOMENDATION_DCC = 3; // driver.telematiccontrol.dcc
	public static final Integer RECOMENDATION_ATSV = 4; //  driver.telematiccontrol.atsv
	
	
	
	
	 // Campos para Excesos de Velocidad Monsanto (Walter):
    // 17/07/2012
    private Integer occurrences;
    private Integer speed; // Velocidad maxima a la que llego el conductor en km/h
    private Integer limitExceeded; // velocidad maxima permitida
    private Integer limitExceededRain; // velocidad maxima permitida bajo lluvia
    private String time; // Formato: hh:mm:ss
    private String duration; // Duracion en hh:mm:ss
    private String totalDuration; // Duracion en hh:mm:ss
    private Float distance; // Distancia total recorrida en exceso de velocidad
    private Float averageVelocity;
    private String garage;
    private String point;//PUNTOS PARA DPA
    private String status;//Estado PARA DPA, Ativo/Inativo
    private String statusComment;//Estado comentario PARA DPA, Ativo/Inativo
    /* Atributos */
	private Integer id;
	private WSLDriverData driver;
	private WSLVehicleData vehicle;
	private String dateStr;
	
	
	private Integer type;
    private Integer recomendation;
    private String observations;
    private String color;
	private String date2Str;
	private String date3Str;
	
    	 

	public WSLTelematicControlData(){
		
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public static String getDPAPointLabel(String value){
		String label="Sim dados";
		if(value==null || value.trim().length()==0){
			return label;
		}
	
		int val=Integer.valueOf(value);
		switch(val){
			case 7 :
				label=DPA_POINT_Gravissima_LABEL;
				break;
			case 5:
				label=DPA_POINT_Grave_LABEL;
				break;
			case 3:
				label=DPA_POINT_Media_LABEL;
				break;
			case 1:
				label=DPA_POINT_Leve_LABEL;
				break;
		}

		return label;
	}
	
	/**
	 * 
	 * @param rec
	 * @return
	 */
	public static String getRecomendationString(int rec){
		switch (rec) {
			case 0: return "driver.telematiccontrol.emptyrecomendation";
			case 1: return "driver.telematiccontrol.ipg";
			case 2: return "driver.telematiccontrol.dct";
			case 3: return "driver.telematiccontrol.dcc";
			case 4: return "driver.telematiccontrol.atsv";			
		}
		return null;
	}

	public WSLDriverData getDriver() {
		return driver;
	}

	public void setDriver(WSLDriverData driver) {
		this.driver = driver;
	}

	public WSLVehicleData getVehicle() {
		return vehicle;
	}

	public void setVehicle(WSLVehicleData vehicle) {
		this.vehicle = vehicle;
	}

	public Integer getOccurrences() {
		return occurrences;
	}

	public void setOccurrences(Integer occurrences) {
		this.occurrences = occurrences;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Integer getLimitExceeded() {
		return limitExceeded;
	}

	public void setLimitExceeded(Integer limitExceeded) {
		this.limitExceeded = limitExceeded;
	}

	public Integer getLimitExceededRain() {
		return limitExceededRain;
	}

	public void setLimitExceededRain(Integer limitExceededRain) {
		this.limitExceededRain = limitExceededRain;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(String totalDuration) {
		this.totalDuration = totalDuration;
	}

	public Float getDistance() {
		return distance;
	}

	public void setDistance(Float distance) {
		this.distance = distance;
	}

	public Float getAverageVelocity() {
		return averageVelocity;
	}

	public void setAverageVelocity(Float averageVelocity) {
		this.averageVelocity = averageVelocity;
	}

	public String getGarage() {
		return garage;
	}

	public void setGarage(String garage) {
		this.garage = garage;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusComment() {
		return statusComment;
	}

	public void setStatusComment(String statusComment) {
		this.statusComment = statusComment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

 
 
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getRecomendation() {
		return recomendation;
	}

	public void setRecomendation(Integer recomendation) {
		this.recomendation = recomendation;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDate2Str() {
		return date2Str;
	}

	public void setDate2Str(String date2Str) {
		this.date2Str = date2Str;
	}

	public String getDate3Str() {
		return date3Str;
	}

	public void setDate3Str(String date3Str) {
		this.date3Str = date3Str;
	}

	 

	 
	
	 
	
	
}
