package communication.fleet.tables;

import org.hibernate.Hibernate;

public class KmByTradeDataType implements Comparable {
	
	private String plate; 
    private String driverFirstName;
    private String driverLastName;
    private Integer kms;
    private Integer accidents;
    private Integer incidents;
    private Integer litres;
    private String fuel;
    private String superviser;
    private String manager;
    private Integer vehicleid;
    private Integer driverid;
    private String country;
    private String divorg1;
    private String divorg2;
    private String divorg3;
    private String divorg4;

    private int odometerValue = 0;
    private int lastOdometer = 0;
    private int lastPeriod = 0;
    private int lastYear = 0;

    private boolean director;
    private String comment;
    
    public KmByTradeDataType(Object[] data, int period, int year) {
    	plate = (String)data[0];
    	driverFirstName = (String) data[1];
    	driverLastName = (String) data[2];
    	kms = 0;
    	int currentPeriod = ((Integer)data[3]).intValue();
    	int currentYear = ((Integer)data[4]).intValue();
    	if(currentYear > lastYear || (currentYear == lastYear && currentPeriod > lastPeriod)) {
    		lastYear = currentYear;
    		lastPeriod = currentPeriod;
    	}
    	if(currentYear <= year || (currentYear == year && currentPeriod <= period)) {
    		accidents = 0;
    		incidents = 0;
    		litres = 0;
    	}else {
    		accidents = (Integer)data[6];
    		incidents = (Integer)data[7];
    		litres = (Integer)data[8];
    	}
    	lastOdometer = ((Integer)data[5]).intValue();
    	fuel = (String)data[9];
    	superviser = (String)data[10];
    	manager = (String)data[11];
    	
    	vehicleid = (Integer)data[12];
    	driverid = (Integer)data[13];

    	country = (String)data[14];
    	director = ((Boolean)data[15]).booleanValue();
    	
    	int start = 16;
    	if(data.length > start) {
    		divorg1 = (String)data[start];
    	}
    	start++;
    	if(data.length > start) {
    		divorg2 = (String)data[start];
    	}
    	start++;
    	if(data.length > start) {
    		divorg3 = (String)data[start];
    	}
    	start++;
    	if(data.length > start) {
    		divorg4 = (String)data[start];
    	}
    }

    public KmByTradeDataType(int period, int year, Object[] data) {
    	plate = (String)data[0];
    	kms = 0;
    	int currentPeriod = ((Integer)data[1]).intValue();
    	int currentYear = ((Integer)data[2]).intValue();
    	if(currentYear > lastYear || (currentYear == lastYear && currentPeriod > lastPeriod)) {
    		lastYear = currentYear;
    		lastPeriod = currentPeriod;
    	}
    	if(currentYear <= year || (currentYear == year && currentPeriod <= period)) {
    		accidents = 0;
    		incidents = 0;
    		litres = 0;
    	}else {
    		accidents = (Integer)data[4];
    		incidents = (Integer)data[5];
    		litres = (Integer)data[6];
    	}
    	lastOdometer = ((Integer)data[3]).intValue();
    	fuel = (String)data[7];
    	
    	vehicleid = (Integer)data[8];

    	country = (String)data[9];
    	director = false;
    	
    	int start = 10;
    	if(data.length > start) {
    		divorg1 = (String)data[start];
    	}
    	start++;
    	if(data.length > start) {
    		divorg2 = (String)data[start];
    	}
    	start++;
    	if(data.length > start) {
    		divorg3 = (String)data[start];
    	}
    	start++;
    	if(data.length > start) {
    		divorg4 = (String)data[start];
    	}
	}

	public void sumData(Object[] data) {
    	odometerValue = ((Integer)data[5]).intValue(); 
    	int currentPeriod = ((Integer)data[3]).intValue();
    	int currentYear = ((Integer)data[4]).intValue();
    	if(currentYear > lastYear || (currentYear == lastYear && currentPeriod > lastPeriod)) {
    		lastYear = currentYear;
    		lastPeriod = currentPeriod;
    	}
    	if(odometerValue > 0) {
    		if(odometerValue < lastOdometer) {
    			kms = -1;
    			System.out.println(plate+" "+data[3]+" "+data[4]);
    		}else {
    			if(kms != -1) {
    				kms += (odometerValue - lastOdometer);
    				lastOdometer = odometerValue;
    			}
    		}
    	}
    	if(data[6] != null)
    		accidents += ((Integer)data[6]);
    	if(data[7] != null)
    		incidents += ((Integer)data[7]);
    	if(data[8] != null) {
    		if(litres == null) {
        		litres = ((Integer)data[8]);
    		}else {
        		litres += ((Integer)data[8]);
    		}
    	}
    }
    
    public void sumData2(Object[] data) {
    	odometerValue = ((Integer)data[3]).intValue(); 
    	if(odometerValue > 0) {
    		if(odometerValue < lastOdometer) {
    			kms = -1;
    		}else {
    			if(kms != -1) {
    				kms += (odometerValue - lastOdometer);
    				lastOdometer = odometerValue;
    			}
    		}
    	}
    	if(data[4] != null)
    		accidents += ((Integer)data[4]);
    	if(data[5] != null)
    		incidents += ((Integer)data[5]);
    	if(data[6] != null)
    		litres += ((Integer)data[6]);
    }
    
	public String getPlate() {
		return plate;
	}

	public String getDriverFirstName() {
		return driverFirstName;
	}

	public String getDriverLastName() {
		return driverLastName;
	}

	public Integer getKms() {
		return kms;
	}

	public Integer getAccidents() {
		return accidents;
	}

	public Integer getIncidents() {
		return incidents;
	}

	public Integer getLitres() {
		return litres;
	}

	public String getFuel() {
		return fuel;
	}

	public String getSuperviser() {
		return superviser;
	}

	public String getManager() {
		return manager;
	}

	public Integer getVehicleid() {
		return vehicleid;
	}

	public Integer getDriverid() {
		return driverid;
	}

	public String getCountry() {
		return country;
	}

	public String getDivorg1() {
		return divorg1;
	}

	public String getDivorg2() {
		return divorg2;
	}

	public String getDivorg3() {
		return divorg3;
	}

	public String getDivorg4() {
		return divorg4;
	}

	public int getLastOdometer() {
		return lastOdometer;
	}

	public int compareTo(Object o) {
		return plate.compareTo(((KmByTradeDataType)o).plate);
	}

	public int getLastPeriod() {
		return lastPeriod;
	}

	public int getLastYear() {
		return lastYear;
	}

	public boolean isDirector() {
		return director;
	}

	public int getOdometerValue() {
		return odometerValue;
	}

	
}
