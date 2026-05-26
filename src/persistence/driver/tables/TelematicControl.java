/**
 * Created on 22-jun-2007
 * CEPA
 * DataCenter 5
 */
package persistence.driver.tables;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.Session;

import persistence.vehicle.tables.Vehicle;

import communication.administration.user.UserSessionData;
import communication.driver.tables.TelematicControlData;
import communication.vehicle.tables.VehicleData;

public class TelematicControl implements Serializable{


	private static final long serialVersionUID = 8235161610226719402L;
	
	private Integer id;
	
	private String image;
	
    private Driver driver;
    private Vehicle vehicle;
    private Date date;
    private Integer type;
    private Integer recomendation;
    private String observations;
    
    private String color; 
    
    // Campos para Excesos de Velocidad Monsanto (Walter):
    // 17/07/2012
    private Integer occurrences;
    private Integer speed; // Velocidad maxima a la que llego el conductor en km/h
    private Integer limitExceeded; // velocidad maxima permitida
    
    private Integer limitExceededRain; // velocidad maxima permmitida bajo lluvia
    
    private String time; // Formato: hh:mm:ss
    private String duration; // Duracion en hh:mm:ss
    private String totalDuration; // Duracion en hh:mm:ss
    private Float distance; // Distancia total recorrida en exceso de velocidad
    private Float averageVelocity; // media de velocidad excedida

	private String garage;
	
	private String point;//DPA
	private String status;//Estado PARA DPA, Ativo/Inativo
    private String statusComment;//Estado comentario PARA DPA, Ativo/Inativo
	    
    
    private Date date2;
    private Date date3;

    
    
    public TelematicControl() {
    }

    /**
     * 
     * @param telematicControlData
     * @param session
     */
	public TelematicControl(TelematicControlData telematicControlData, Session session) {
		if(telematicControlData.getDriver()!=null && telematicControlData.getDriver().getId()!=null){
			driver = (Driver)session.load(Driver.class, telematicControlData.getDriver().getId());
		}
		if(telematicControlData.getVehicle() != null) {
			vehicle = (Vehicle)session.load(Vehicle.class, telematicControlData.getVehicle().getId());
		}
		date = telematicControlData.getDate();
		type = telematicControlData.getType();
		recomendation = telematicControlData.getRecomendation();
		observations = telematicControlData.getObservations();
		color = telematicControlData.getColor();
		this.point=telematicControlData.getPoint();
		this.status=telematicControlData.getStatus();
		this.statusComment=telematicControlData.getStatusComment();
		occurrences = telematicControlData.getOccurrences();
		speed = telematicControlData.getSpeed(); // Velocidad maxima a la que llego el conductor en km/h
		limitExceeded = telematicControlData.getLimitExceeded(); // velocidad maxima permitida
		limitExceededRain = telematicControlData.getLimitExceededRain(); // velocidad maxima permitida
		time = telematicControlData.getTime(); // Formato: hh:mm:ss
		duration = telematicControlData.getDuration(); // Duracion en hh:mm:ss
		totalDuration = telematicControlData.getTotalDuration(); // Duracion en hh:mm:ss
		distance = telematicControlData.getDistance(); // Distancia total recorrida en exceso de velocidad
		averageVelocity = telematicControlData.getAverageVelocity();
		garage = telematicControlData.getGarage();
		date2 = telematicControlData.getDate2();
		date3 = telematicControlData.getDate3();
		this.image=telematicControlData.getFileName();
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}


	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getStatus() {
		if(status==null) status="Ativo";
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

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 
	 * @param userSessionData
	 * @return
	 */
	public TelematicControlData getData(UserSessionData userSessionData) {
		VehicleData vehicleData = (vehicle == null) ? null : vehicle.getVehicleData();
		TelematicControlData aux= new TelematicControlData(occurrences,  speed,
							 limitExceeded, time,  duration,
							 totalDuration,  distance,  averageVelocity,
							 id,  driver!=null?driver.getDriverData():null,  date, type,
							recomendation,  observations,  color, limitExceededRain, garage, date2, date3, vehicleData);
		aux.setPoint(point);
		aux.setStatus(status);
		aux.setStatusComment(statusComment);
		aux.setFileName(this.image);
		
		return aux;
		
//		return new TelematicControlData(id,driver.getDriverData(),date,type,
//				recomendation,observations,color);
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

	/**
	 * 
	 * @param telematicControlData
	 */
	public void updateData(TelematicControlData telematicControlData) {
		date = telematicControlData.getDate();
		type = telematicControlData.getType();
		recomendation = telematicControlData.getRecomendation();
		observations = telematicControlData.getObservations();
		color = telematicControlData.getColor();
		this.point=telematicControlData.getPoint();
		this.status=telematicControlData.getStatus();
		this.statusComment=telematicControlData.getStatusComment();
		// Velocidad, Monsanto 17/07/2012
		occurrences = telematicControlData.getOccurrences();
	    speed = telematicControlData.getSpeed(); // Velocidad maxima a la que llego el conductor en km/h
	    limitExceeded = telematicControlData.getLimitExceeded(); // velocidad maxima permitida
	    limitExceededRain = telematicControlData.getLimitExceededRain(); // velocidad maxima permitida
	    time = telematicControlData.getTime(); // Formato: hh:mm:ss
	    duration = telematicControlData.getDuration(); // Duracion en hh:mm:ss
	    totalDuration = telematicControlData.getTotalDuration(); // Duracion en hh:mm:ss
	    distance = telematicControlData.getDistance(); // Distancia total recorrida en exceso de velocidad
	    averageVelocity = telematicControlData.getAverageVelocity();
		garage = telematicControlData.getGarage();
		date2 = telematicControlData.getDate2();
		date3 = telematicControlData.getDate3();
		if(telematicControlData.getFileName()!=null){
			this.image=telematicControlData.getFileName();
		}
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the occurrences
	 */
	public Integer getOccurrences() {
		return occurrences;
	}

	/**
	 * @param occurrences the occurrences to set
	 */
	public void setOccurrences(Integer occurrences) {
		this.occurrences = occurrences;
	}

	/**
	 * @return the speed
	 */
	public Integer getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	/**
	 * @return the limitExceeded
	 */
	public Integer getLimitExceeded() {
		return limitExceeded;
	}

	/**
	 * @param limitExceeded the limitExceeded to set
	 */
	public void setLimitExceeded(Integer limitExceeded) {
		this.limitExceeded = limitExceeded;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @return the totalDuration
	 */
	public String getTotalDuration() {
		return totalDuration;
	}

	/**
	 * @param totalDuration the totalDuration to set
	 */
	public void setTotalDuration(String totalDuration) {
		this.totalDuration = totalDuration;
	}

	/**
	 * @return the distance
	 */
	public Float getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(Float distance) {
		this.distance = distance;
	}

	/**
	 * @return the averageVelocity
	 */
	public Float getAverageVelocity() {
		return averageVelocity;
	}

	/**
	 * @param averageVelocity the averageVelocity to set
	 */
	public void setAverageVelocity(Float averageVelocity) {
		this.averageVelocity = averageVelocity;
	}

	public TelematicControl(Integer id, Driver driver, Vehicle vehicle, Date date, Integer type,
			Integer recomendation, String observations, String color,
			Integer occurrences, Integer speed, Integer limitExceeded,
			String time, String duration, String totalDuration, Float distance,
			Float averageVelocity, Integer limitExceededRain, String garage, Date date2, Date date3) {
		super();
		this.id = id;
		this.driver = driver;
		this.vehicle = vehicle;
		this.date = date;
		this.type = type;
		this.recomendation = recomendation;
		this.observations = observations;
		this.color = color;
		this.occurrences = occurrences;
		this.speed = speed;
		this.limitExceeded = limitExceeded;
		this.limitExceededRain = limitExceededRain;
		this.time = time;
		this.duration = duration;
		this.totalDuration = totalDuration;
		this.distance = distance;
		this.averageVelocity = averageVelocity;
		this.garage = garage;
		this.date2 = date2;
		this.date3 = date3;
	}

	public Integer getLimitExceededRain() {
		return limitExceededRain;
	}

	public void setLimitExceededRain(Integer limitExceededRain) {
		this.limitExceededRain = limitExceededRain;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getGarage() {
		return garage;
	}

	public void setGarage(String garage) {
		this.garage = garage;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public Date getDate3() {
		return date3;
	}

	public void setDate3(Date date3) {
		this.date3 = date3;
	}

	
	
	
	

}
