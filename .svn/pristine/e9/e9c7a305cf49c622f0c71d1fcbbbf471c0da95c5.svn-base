package communication.driver.tables;

import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import business.SystemAccess;

import communication.administration.corporation.util.Util;
import communication.vehicle.tables.VehicleData;

public class TelematicControlData implements Serializable {

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
	public static final int TYPE_NIGTH_DRIVE = 14;

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
    
    private String fileName;
    private InputStream fileInputStream;
    
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public static String getTypeString(int type){
		switch (type) {
			case 0: return "driver.telematiccontrol.emptytype";
			case 1: return "driver.telematiccontrol.hardstart";
			case 2: return "driver.telematiccontrol.hardstop";
			case 3: return "driver.telematiccontrol.excesiverotation";
			case 4: return "driver.telematiccontrol.baddirection";
			case 5: return "driver.telematiccontrol.operational";
			case 6: return "driver.telematiccontrol.rain";
			case TYPE_EXCESSIVE_SPEED: return "driver.telematiccontrol.excessivespeed";
			case TYPE_FREE_TIME: return "driver.telematiccontrol.freetime";
			case TYPE_WORK_HOURS: return "driver.telematiccontrol.workhours";
			case TYPE_24HOOUR_WITHOUT_REPORT: return "driver.telematiccontrol.24hourworeport";
			case TYPE_NIGTH_DRIVE: return "driver.telematiccontrol.nightdrive";
		}
		return null;
	}
	
	public static final Integer RECOMENDATION_EMPTY = 0; // "driver.telematiccontrol.emptyrecomendation"
	public static final Integer RECOMENDATION_IPG = 1;  // driver.telematiccontrol.ipg
	public static final Integer RECOMENDATION_DCT = 2; // driver.telematiccontrol.dct
	public static final Integer RECOMENDATION_DCC = 3; // driver.telematiccontrol.dcc
	public static final Integer RECOMENDATION_ATSV = 4; //  driver.telematiccontrol.atsv
	
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
	
	/* Atributos */
	private Integer id;
	private DriverData driver;
	private VehicleData vehicle;
	private Date date;
	private Integer type;
    private Integer recomendation;
    private String observations;
    private String color;
	private Date date2;
	private Date date3;
	
	public TelematicControlData() {
	}

	public TelematicControlData(DriverData  driver, VehicleData vehicle, Date date) {
		this.driver = driver;
		this.vehicle = vehicle;
		this.date = date;
		color = COLOR_GREEN;
		type = TYPE_EMPTY;
		recomendation = RECOMENDATION_EMPTY;
	}
	
	public TelematicControlData(Integer id, DriverData  driver, VehicleData vehicle,  Date date,
			Integer type, Integer recomendation, String observations, String color) {
		this.id = id;
		this.driver = driver;
		this.vehicle = vehicle;
		
		if (date != null){
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.HOUR,0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND,0);
			this.date = c.getTime();
		}else{
			this.date = null;
		}
			
		this.type = type;
		this.recomendation = recomendation;
		this.observations = observations;
		this.color = color;
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
	
	
	public String getPDFFileName(SystemAccess sys){
		DriverData driverData=this.driver;
		if(driverData!=null && driverData.getId()!=null && driverData.getFirstName()!=null && driverData.getLastName()!=null){
			String f=driverData.getFirstName();
			String l=driverData.getLastName();
			String d="";
			Date dd=getDate();
			if(dd!=null){
				Calendar calendar = Calendar.getInstance();
		        calendar.setTime(date);
		        d=calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);
			}
		        
			return sys.getText().getText("TelematicControl_DPA")+"_"+f.trim().replaceAll(" ", "_")+"_"+l.trim().replaceAll(" ", "_")+"_"+d+".pdf";
		}else{
			return sys.getText().getText("TelematicControl_DPA")+"_"+ vehicle.getMatricula()+".pdf";
		}
	}		
			
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
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

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DriverData getDriver() {
		return driver;
	}

	public void setDriver(DriverData driver) {
		this.driver = driver;
	}

	public Date getDate() {
		return date;
	}
	
	public String getDateString(){
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		if (this.date != null) return format.format(this.date);
		else return null;
	}

	public void setDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR,0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND,0);
		this.date = c.getTime();
		
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

	
	public TelematicControlData(Integer occurrences, Integer speed,
			Integer limitExceeded, String time, String duration,
			String totalDuration, Float distance, Float averageVelocity,
			Integer id, DriverData driver, Date date, Integer type,
			Integer recomendation, String observations, String color, Integer limitExceededRain, String garage, 
			Date date2, Date date3, VehicleData vehicle) {
		super();
		this.occurrences = occurrences;
		this.speed = speed;
		this.limitExceeded = limitExceeded;
		this.limitExceededRain = limitExceededRain;
		this.point=point;
		this.time = time;
		this.duration = duration;
		this.totalDuration = totalDuration;
		this.distance = distance;
		this.averageVelocity = averageVelocity;
		this.id = id;
		this.driver = driver;
		this.vehicle = vehicle;
		this.date = date;
		this.type = type;
		this.recomendation = recomendation;
		this.observations = observations;
		this.color = color;
		this.garage = garage;
		this.date2 = date2;
		this.date3 = date3;
	}

	public TelematicControlData(Object[] data) {
		DriverData driverData = new DriverData((Integer)data[0]);
		driverData.setFirstName((String)data[1]);
		driverData.setLastName((String)data[2]);
		driverData.setDivOrgItem1((String)data[3]);
		this.driver = driverData;
		this.date = (Date) data[4];
		this.time = (String) data[5];
		this.type = (Integer) data[6];
		this.observations = (String) data[7];
		this.speed = (Integer) data[8];
		this.duration = (String) data[9];
		this.garage = (String) data[10];
		this.statusComment = (String) data[11];
	}

	public Integer getLimitExceededRain() {
		return limitExceededRain;
	}

	public void setLimitExceededRain(Integer limitExceededRain) {
		this.limitExceededRain = limitExceededRain;
	}

	public VehicleData getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleData vehicle) {
		this.vehicle = vehicle;
	}

	public String getGarage() {
		return garage;
	}

	public void setGarage(String garage) {
		this.garage = garage;
	}

	public String getStringType() {
		switch(type.intValue()) {
			case TYPE_EXCESSIVE_SPEED:
				return "driver.telematiccontrol.excessivespeed";
			case TYPE_FREE_TIME:
				return "driver.telematiccontrol.freetime";
			case TYPE_WORK_HOURS:
				return "driver.telematiccontrol.workhours";
			case TYPE_INSERTICIAL_REPORT:
				return "driver.telematiccontrol.interstitialreport";
			case TYPE_WEEK_WORK:
				return "driver.telematiccontrol.weekwork";
			case TYPE_24HOOUR_WITHOUT_REPORT:
				return "driver.telematiccontrol.24hourworeport";
			case TYPE_HARD_START:
				return "driver.telematiccontrol.hardstart";
			case TYPE_HARD_STOP:
				return "driver.telematiccontrol.hardstop";
		}
		return null;
	}

	public Date getDate2() {
		return date2;
	}

	public Date getDate3() {
		return date3;
	}

	public void setDate3(Date date3) {
		this.date3 = date3;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public String getEcolabLine(int index, int type) {
		String line = "data.setCell("+index+", 0, '"+Util.cleanString(driver.getFirstName())+" "+Util.cleanString(driver.getLastName())+"', '"+driver.getFirstName()+" "+driver.getLastName()+"', {style: 'color:black;'});"; 
		line += "data.setCell("+index+", 1, '"+garage+"', '"+garage+"', {style: 'color:black;'});";
		if(date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			line += "data.setCell("+index+", 2, new Date("+c.get(Calendar.YEAR)+","+String.valueOf(c.get(Calendar.MONTH)+1)+","+c.get(Calendar.DATE)+"), '"+c.get(Calendar.DATE)+"/"+String.valueOf(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR)+"', {style: 'color:black;'});";
		}
		if(time != null) {
			StringTokenizer tokenizer = new StringTokenizer(time,":");
			if(tokenizer.countTokens() == 2) {
				line += "data.setCell("+index+", 3, ["+tokenizer.nextToken()+","+tokenizer.nextToken()+",0,0], '"+time+"', {style: 'color:black;'});";				
			}
		}
		int column = 4;
		if(type == TYPE_NIGTH_DRIVE) {
			if(duration != null)
				line += "data.setCell("+index+", "+column+", '"+duration+"', '"+duration+"', {style: 'color:black;'});";
			column++;
		}
		line += "data.setCell("+index+", "+column+", '"+observations.replaceAll("'", " ")+"', '"+observations.replaceAll("'", " ")+"', {style: 'color:black;'});";
		column++;
		if(type == TYPE_NIGTH_DRIVE) {
			line += "data.setCell("+index+", "+column+", "+speed+", '"+speed+" km/h', {style: 'color:black;'});";
		}else {
			line += "data.setCell("+index+", "+column+", "+speed+", '"+speed+"', {style: 'color:black;'});";
		}
		return line;
	}
	
}
