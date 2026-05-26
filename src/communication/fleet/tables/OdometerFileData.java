package communication.fleet.tables;

import communication.driver.tables.DriverData;

public class OdometerFileData {

	public static final int NOT_EXISTS = -1; 
	public static final int OK = 0; 
	public static final int WARNING = 1; 
	public static final int ERROR_PREV = 2; 
	public static final int ERROR_POST = 3;
	
	private String fileIdDallas;
	private String filePlate;
	private int fileOdometer;
	
	private int error = OK;
	
	private int lastOdometer;
	private int lastPeriod;
	private int lastYear;

	private int insertedOdometer;
	private int insertedPeriod;
	private int insertedYear;

	private String driverName;
	private Integer newDriverId;
	private String newDdriverName;

	public OdometerFileData(String fileIdDallas, String filePlate,int fileOdometer) {
		this.fileIdDallas = fileIdDallas;
		this.filePlate = filePlate;
		this.fileOdometer = fileOdometer;
	}

	public String getFileIdDallas() {
		return fileIdDallas;
	}

	public void setFileIdDallas(String fileIdDallas) {
		this.fileIdDallas = fileIdDallas;
	}

	public String getFilePlate() {
		return filePlate;
	}

	public void setFilePlate(String filePlate) {
		this.filePlate = filePlate;
	}

	public int getFileOdometer() {
		return fileOdometer;
	}

	public void setFileOdometer(int fileOdometer) {
		this.fileOdometer = fileOdometer;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public void setInsertedData(int insertedOdometer, int insertedPeriod, int insertedYear) {
		this.insertedOdometer = insertedOdometer;
		this.insertedPeriod = insertedPeriod;
		this.insertedYear = insertedYear;
		
	}

	public int getInsertedOdometer() {
		return insertedOdometer;
	}

	public void setInsertedOdometer(int insertedOdometer) {
		this.insertedOdometer = insertedOdometer;
	}

	public int getInsertedPeriod() {
		return insertedPeriod;
	}

	public void setInsertedPeriod(int insertedPeriod) {
		this.insertedPeriod = insertedPeriod;
	}

	public int getInsertedYear() {
		return insertedYear;
	}

	public void setInsertedYear(int insertedYear) {
		this.insertedYear = insertedYear;
	}

	public void setDriverData(Object[] driver) {
		driverName = driver[2] + " " + driver[3] + "(" + driver[4] + ")";
	}

	public void setNewDriverId(Integer id) {
		newDriverId = id;
	}

	public void setNewDriverName(DriverData driver) {
		newDdriverName = driver.getFirstName()+" "+driver.getLastName() + "(" + driver.getIdDallas() + ")";
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getNewDdriverName() {
		return newDdriverName;
	}

	public void setNewDdriverName(String newDdriverName) {
		this.newDdriverName = newDdriverName;
	}

	public Integer getNewDriverId() {
		return newDriverId;
	}

	public void setLastData(int lastOdometer, int lastPeriod, int lastYear) {
		this.lastOdometer = lastOdometer;
		this.lastPeriod = lastPeriod;
		this.lastYear = lastYear;
		
	}

	public int getLastOdometer() {
		return lastOdometer;
	}

	public void setLastOdometer(int lastOdometer) {
		this.lastOdometer = lastOdometer;
	}

	public int getLastPeriod() {
		return lastPeriod;
	}

	public void setLastPeriod(int lastPeriod) {
		this.lastPeriod = lastPeriod;
	}

	public int getLastYear() {
		return lastYear;
	}

	public void setLastYear(int lastYear) {
		this.lastYear = lastYear;
	}

}

