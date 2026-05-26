/**
 * Created on 28-sep-2006
 * CEPA
 * DataCenter 5
 */
package communication.driver.report;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import persistence.driver.tables.AlcoholControl;
import persistence.driver.tables.AptitudTest;
import persistence.driver.tables.Driver;

import communication.driver.tables.DriverData;

public class DriverReportDataType implements Comparable, Serializable {

    public static final int EXPIRED = 1;
    public static final int NOT_EXPIRED = 2;
    public static final int NEVER = 3;
    
    public static final String COND1 = "CD1";
	public static final String COND2 = "CD2";
	public static final String COND3 = "CD3";
	public static final String COND4 = "CD4";
	public static final String COND5 = "CD5";
	public static final String COND6 = "CD6";
	public static final String COND7 = "CD7";
    
    protected Integer id;
    protected String firstname;
    protected String lastname;
    protected String country;
    protected Date birthdate;
    protected Date exclusiondate;
    protected String contract;
    protected String divorg11;
    protected String divorg12;
    protected String divorg13;
    protected String divorg21;
    protected String divorg22;
    protected String divorg23;
    protected String divorg31;
    protected String divorg32;
    protected String divorg33;
    protected String divgeo1;
    protected String divgeo2;
    protected String divgeo3;
    
    protected String corporationId; // WWID
    protected String email;
    
    protected Integer driverPoints; // puntos del conductor CIMSA
        
    /**
     * Para el hrd, condiciones por el que el driver es HRD.
     */
    protected String hrdCondition;
    /**
     * Para el hrd, condiciones por el que el driver es HRD.
     */
    protected String phrdCondition;
    protected Date lastUpdate = null;

    protected String bpsRegistry;
    
    protected Date testDate;
    protected boolean apt;
    protected boolean pending;
    protected Date expiry;
    
    protected Date lastControl;
    protected Integer result;
    protected int status;

    protected boolean anyPositive;

    protected boolean conditional;
    
    protected Driver driver;
    protected String assignedVehicle;

	private Integer vehicleId;
	private String vehiclePlate;
	private Date lastHRD;
    
    protected String city;
    protected String phonenumber;
	protected String manager;
	protected String superviser;
	
	//Bayer
	protected boolean noHrd;
	protected String noHrdComment;
	
	protected Integer evitability;
	private String licenseObs;
	protected boolean director;

	protected boolean temporal;
	
	public DriverReportDataType() {
    }

    /**
     * @param id
     * @param name
     * @param country
     * @param birthdate
     * @param exclusiondate
     * @param contract
     * @param divorg11
     * @param divorg12
     * @param divorg13
     * @param divorg21
     * @param divorg22
     * @param divorg23
     * @param divorg31
     * @param divorg32
     * @param divorg33
     * @param unit
     * @param unitName 
     * @param divgeo3 
     */
    public DriverReportDataType(Integer id, String firstname, String lastname, String country, 
    		Date birthdate, Date exclusiondate, String contract, String divorg11, 
    		String divorg12, String divorg13, String divorg21, String divorg22, 
    		String divorg23, String divorg31, String divorg32, String divorg33, 
    		String divgeo2, String divgeo3, Driver driver) {
        super();
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.country = country;
        this.birthdate = birthdate;
        this.exclusiondate = exclusiondate;
        this.contract = contract;
        this.divorg11 = divorg11;
        this.divorg12 = divorg12;
        this.divorg13 = divorg13;
        this.divorg21 = divorg21;
        this.divorg22 = divorg22;
        this.divorg23 = divorg23;
        this.divorg31 = divorg31;
        this.divorg32 = divorg32;
        this.divorg33 = divorg33;
        this.divgeo1 = country;
        this.divgeo2 = divgeo2;
        this.divgeo3 = divgeo3;
        this.driver = driver;
        this.assignedVehicle = null;
        if(driver != null) {
        	
        	this.assignedVehicle = (driver.getAssignedVehicle() != null && 
        			driver.getAssignedVehicle().getResourceBundleId() != null)
        			?driver.getAssignedVehicle().getResourceBundleId():null;
        	
            this.bpsRegistry = driver.getBpsregistry();
            
            this.corporationId = driver.getCorporationId();
            this.licenseObs = driver.getLicenseObs();
            this.director = driver.isDirector();
            this.temporal = driver.getTemporal();

            if(driver.getAptitudTestSet() != null) {
                Iterator it = driver.getAptitudTestSet().iterator();
                Calendar lastTest  = null;
                while(it.hasNext()) {
                    AptitudTest test = (AptitudTest)it.next();
                    if(lastTest == null) {
                        lastTest = Calendar.getInstance();
                        lastTest.setTime(test.getPk().getDate());
                        apt = test.isApt();
                        pending = test.isPending();
                        if(!test.isPending()) {
                        	expiry = test.getExpiry();
                        }
                    }else {
                        Calendar newTest = Calendar.getInstance();
                        newTest.setTime(test.getPk().getDate());
                        if(lastTest.get(Calendar.DATE) == newTest.get
(Calendar.DATE)
                                && lastTest.get(Calendar.MONTH) ==
newTest.get(Calendar.MONTH)
                                && lastTest.get(Calendar.YEAR) ==
newTest.get(Calendar.YEAR)) {
                            if(!apt) {
                                apt = test.isApt();
                            }
                            pending = test.isPending();
                            if(expiry == null || expiry.before(test.getExpiry())) {
                                if(!test.isPending()) {
                                	expiry = test.getExpiry();
                                }
                            }
                        }else {
                            if(lastTest.before(newTest)) {
                                lastTest = newTest;
                                apt = test.isApt();
                                pending = test.isPending();
                                if(!test.isPending()) {
                                	expiry = test.getExpiry();
                                }
                            }
                        }                           
                        if(expiry == null) {
                            if(!test.isPending()) {
                            	expiry = test.getExpiry();
                            }
                        }	                            	
                    }
                }
                if(lastTest != null) {
                    testDate = lastTest.getTime();
                }          
            }
            if(driver.getAlcoholControlSet() != null && !driver.getAlcoholControlSet().isEmpty()) {
                Iterator it = driver.getAlcoholControlSet().iterator();
                Calendar c = Calendar.getInstance();
                c.add(Calendar.YEAR,-1);
                Date yearAgo = c.getTime();
                anyPositive = false;
                while(it.hasNext()) {
                    AlcoholControl control = (AlcoholControl)it.next();
                    if(lastControl == null || lastControl.before(control.getPk().getDate())) {
                        lastControl = control.getPk().getDate();
                        result = control.getResult();
                    }
                    if(control.getResult().intValue() == DriverData.POSITIVE) {
                        anyPositive = true;
                    }
                }
                if(lastControl.before(yearAgo)) {
                    status = EXPIRED;
                }else {
                    status = NOT_EXPIRED;
                }
            }else {
                status = NEVER;
            }
        }
        
      
    }
    
    public boolean isNoHrd() {
		return noHrd;
	}

	public void setNoHrd(boolean noHrd) {
		this.noHrd = noHrd;
	}

	public String getNoHrdComment() {
		return noHrdComment;
	}

	public void setNoHrdComment(String noHrdComment) {
		this.noHrdComment = noHrdComment;
	}

	public Date getBirthdate() {
        return birthdate;
    }
    public String getContract() {
        return contract;
    }
    public String getCountry() {
        return country;
    }
    public String getDivorg11() {
        return divorg11;
    }
    public String getDivorg12() {
        return divorg12;
    }
    public String getDivorg13() {
        return divorg13;
    }
    public String getDivorg21() {
        return divorg21;
    }
    public String getDivorg22() {
        return divorg22;
    }
    public String getDivorg23() {
        return divorg23;
    }
    public String getDivorg31() {
        return divorg31;
    }
    public String getDivorg32() {
        return divorg32;
    }
    public String getDivorg33() {
        return divorg33;
    }
    public Date getExclusiondate() {
        return exclusiondate;
    }
    public Integer getId() {
        return id;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    public void setContract(String contract) {
        this.contract = contract;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setDivorg11(String divorg11) {
        this.divorg11 = divorg11;
    }
    public void setDivorg12(String divorg12) {
        this.divorg12 = divorg12;
    }
    public void setDivorg13(String divorg13) {
        this.divorg13 = divorg13;
    }
    public void setDivorg21(String divorg21) {
        this.divorg21 = divorg21;
    }
    public void setDivorg22(String divorg22) {
        this.divorg22 = divorg22;
    }
    public void setDivorg23(String divorg23) {
        this.divorg23 = divorg23;
    }
    public void setDivorg31(String divorg31) {
        this.divorg31 = divorg31;
    }
    public void setDivorg32(String divorg32) {
        this.divorg32 = divorg32;
    }
    public void setDivorg33(String divorg33) {
        this.divorg33 = divorg33;
    }
    public void setExclusiondate(Date exclusiondate) {
        this.exclusiondate = exclusiondate;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDivgeo1() {
        return divgeo1;
    }

    public String getDivgeo2() {
        return divgeo2;
    }

    public String getDivgeo3() {
        return divgeo3;
    }

    public void setDivgeo1(String divg1) {
        this.divgeo1 = divg1;
    }

    public void setDivgeo2(String divg2) {
        this.divgeo2 = divg2;
    }

    public void setDivgeo3(String divg3) {
        this.divgeo3 = divg3;
    }

    public boolean isApt() {
        return apt;
    }
    
    public boolean isPending(){
    	return pending;
    }

    public String getBpsRegistry() {
        return bpsRegistry;
    }

    public Date getExpiry() {
        return expiry;
    }

    public Date getLastControl() {
        return lastControl;
    }

    public Integer getResult() {
        return result;
    }

    public int getStatus() {
        return status;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setApt(boolean apt) {
        this.apt = apt;
    }
    
    public void setPending(boolean pending){
    	this.pending = pending;
    }

    public void setBpsRegistry(String bpsRegistry) {
        this.bpsRegistry = bpsRegistry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public void setLastControl(Date lastControl) {
        this.lastControl = lastControl;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public int compareTo(Object arg0) {
        return lastname.toLowerCase().compareTo(((DriverReportDataType)arg0).lastname.toLowerCase());
    }

    public boolean isAnyPositive() {
        return anyPositive;
    }

    public void setAnyPositive(boolean anyPositive) {
        this.anyPositive = anyPositive;
    }


	/**
	 * @return Returns the hrdCondition.
	 */
	public String getHrdCondition() {
		return hrdCondition;
	}

	/**
	 * @param hrdCondition The hrdCondition to set.
	 */
	public void setHrdCondition(String hrdCondition) {
		if (hrdCondition == null || hrdCondition.equals("")){
			this.hrdCondition = "";
		}else
			/*if (hrdCondition.equals(DriverReportDataType.COND1) || 
					hrdCondition.equals(DriverReportDataType.COND2) ||
					hrdCondition.equals(DriverReportDataType.COND3)
				){
				if (this.hrdCondition == null || this.hrdCondition == ""){*/
					this.hrdCondition = hrdCondition;
				/*}else{
					this.hrdCondition += " "+hrdCondition;
				}
		}*/
	}
	
	/**
	 * Retorna una X si el driver tiene la condicion "number"
	 * como condicion HRD. Si no tiene dicha condicion devuelve 
	 * un string vacio.
	 * @param number
	 * @return
	 */
	public String getHrdCondition(int number){
		switch (number){
			case 1: if (this.hrdCondition.contains(DriverReportDataType.COND1))
						return "X";
					else
						return "";
			case 2: if (this.hrdCondition.contains(DriverReportDataType.COND2))
						return "X";
					else
						return "";
			case 3:if (this.hrdCondition.contains(DriverReportDataType.COND3))
						return "X";
					else
						return "";
			// Para HRD Monsanto:
			case 4: if (this.hrdCondition.contains(DriverReportDataType.COND4))
						return "X";
					else
						return "";
			case 5:if (this.hrdCondition.contains(DriverReportDataType.COND5))
						return "X";
					else
						return "";
			default: return "";
		}			
	}
	/**
	 * Devuelve la etiqueta de la division geografica u organizacional
	 * segul sea el index y el j dentro de la matriz para geografica
	 * y el index para organizacional.
	 * @param type
	 * @param index
	 * @param j
	 * @return
	 */
	public String getData(int type, int index, int j) {
	        if(type == DriverData.DIV_ORG) {	       
	            if (index == 1){
	            	switch (j) {
	            		case 1:return this.divorg11;
	            		case 2:return this.divorg12;
	            		case 3:return this.divorg13;
	            	}
	            }else if (index == 2){
	            	switch (j) {
	            		case 1:return this.divorg21;
            			case 2:return this.divorg22;
            			case 3:return this.divorg23;
	            	}
            	}else if (index == 3){
            		switch (j) {
            			case 1:return this.divorg31;
            			case 2:return this.divorg32;
            			case 3:return this.divorg33;
            		}
            	}
            }else{	       
            	switch (index) {
            		case 1:return this.divgeo1;
            		case 2:return this.divgeo2;
            		case 3:return this.divgeo3;
            	}  	          
	        }
	        return null;
	    }

	/**
	 * @return Returns the phrdCondition.
	 */
	public String getPhrdCondition() {
		return phrdCondition;
	}

	/**
	 * @param phrdCondition The phrdCondition to set.
	 */
	public void setPhrdCondition(String phrdCondition) {
		if (phrdCondition == null || phrdCondition.equals("")){
			this.phrdCondition = "";
		}else{
			this.phrdCondition = phrdCondition;
		}
	}
	
	/**
	 * Retorna una X si el driver tiene la condicion "number"
	 * como condicion PHRD. Si no tiene dicha condicion devuelve 
	 * un string vacio.
	 * @param number
	 * @return
	 */
	public String getPhrdCondition(int number){
		switch (number){
			case 1: if (this.phrdCondition.contains(DriverReportDataType.COND1))
						return "X";
					else
						return "";
			case 2: if (this.phrdCondition.contains(DriverReportDataType.COND2))
						return "X";
					else
						return "";
			case 3:if (this.phrdCondition.contains(DriverReportDataType.COND3))
						return "X";
					else
						return "";
			case 4:if (this.phrdCondition.contains(DriverReportDataType.COND4))
						return "X";
					else
						return "";
			case 5:if (this.phrdCondition.contains(DriverReportDataType.COND5))
						return "X";
					else
						return "";
			case 6:if (this.phrdCondition.contains(DriverReportDataType.COND6))
						return "X";
					else
						return "";			
			case 7:if (this.phrdCondition.contains(DriverReportDataType.COND7))
				return "X";
			else
				return "";	
			default: return "";
		}			
	}

	/**
	 * @return Returns the phrdScore.
	 */
	public int getPhrdScore() {
		int score = 0;
		if (this.phrdCondition.contains(DriverReportDataType.COND1))
			score += 3;
		if (this.phrdCondition.contains(DriverReportDataType.COND2))
			score += 2;
		if (this.phrdCondition.contains(DriverReportDataType.COND3))
			score += 3;
		if (this.phrdCondition.contains(DriverReportDataType.COND4))
			score += 2;
		if (this.phrdCondition.contains(DriverReportDataType.COND5))
			score += 4;
		if (this.phrdCondition.contains(DriverReportDataType.COND6))
			score += 2;
		if (this.phrdCondition.contains(DriverReportDataType.COND7))
			score += 5;
		return score;
	}

    /**
     * @return Returns the phrdScore.
     */
    public int getPhrdHeavyScore() {
        int score = 0;
        if (this.phrdCondition.contains(DriverReportDataType.COND1))
            score += 3;
        if (this.phrdCondition.contains(DriverReportDataType.COND2))
            score += 2;
        if (this.phrdCondition.contains(DriverReportDataType.COND3))
            score += 4;
        if (this.phrdCondition.contains(DriverReportDataType.COND4))
            score += 2;
        if (this.phrdCondition.contains(DriverReportDataType.COND5))
            score += 5;
        return score;
    }

    /**
	 * @return Returns the lastUpdate.
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate The lastUpdate to set.
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

    public boolean isConditional() {
        return conditional;
    }

    public void setConditional(boolean conditional) {
        this.conditional = conditional;
    }

    public void addCond(String newCondition) {
        if(hrdCondition == null || hrdCondition.length() == 0) {
            hrdCondition = newCondition;
        }else {
            if(!hrdCondition.contains(newCondition)) {
                hrdCondition += ","+newCondition;
            }
        }
    }

	/**
	 * @return the corporationId
	 */
	public String getCorporationId() {
		return corporationId;
	}

	/**
	 * @param corporationId the corporationId to set
	 */
	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	/**
	 * @return the driverPoints
	 */
	public Integer getDriverPoints() {
		return driverPoints;
	}

	/**
	 * @param driverPoints the driverPoints to set
	 */
	public void setDriverPoints(Integer driverPoints) {
		this.driverPoints = driverPoints;
	}
    
	public String getDivOrg(int index, int j) {
        if (index == 1){
        	switch (j) {
    			case 1:
        			if(divorg11 != null) {
            			return this.divorg11;
        			}else {
            			if(divorg12 != null) {
                			return this.divorg12;
            			}else {
            				return this.divorg13;
            			}
        			}
        		case 2:
        			if(divorg11 == null) {
        				return this.divorg13;
        			}else {
        				return this.divorg12;
        			}
        		case 3:
        			return this.divorg13;
        	}
        }else if (index == 2){
        	switch (j) {
				case 1:
        			if(divorg21 != null) {
            			return this.divorg21;
        			}else {
            			if(divorg22 != null) {
                			return this.divorg22;
            			}else {
            				return this.divorg23;
            			}
        			}
	    		case 2:
	    			if(divorg21 == null) {
	    				return this.divorg23;
	    			}else {
	    				return this.divorg22;
	    			}
	    		case 3:
	    			return this.divorg23;
        	}
    	}else if (index == 3){
    		switch (j) {
				case 1:
        			if(divorg31 != null) {
            			return this.divorg31;
        			}else {
            			if(divorg32 != null) {
                			return this.divorg32;
            			}else {
            				return this.divorg33;
            			}
        			}
	    		case 2:
	    			if(divorg31 == null) {
	    				return this.divorg33;
	    			}else {
	    				return this.divorg32;
	    			}
	    		case 3:
	    			return this.divorg33;
    		}
    	}
        return null;
	}

	/**
	 * @return the driver
	 */
	public Driver getDriver() {
		return driver;
	}

	/**
	 * @param driver the driver to set
	 */
	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	/**
	 * @return the assignedVehicle
	 */
	public String getAssignedVehicle() {
		return assignedVehicle;
	}

	/**
	 * @param assignedVehicle the assignedVehicle to set
	 */
	public void setAssignedVehicle(String assignedVehicle) {
		this.assignedVehicle = assignedVehicle;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}

	public void setLastHRD(Date lastHRD) {
		this.lastHRD = lastHRD;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public String getVehiclePlate() {
		return vehiclePlate;
	}

	public Date getLastHRD() {
		return lastHRD;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public void setSuperviser(String superviser) {
		this.superviser = superviser;
	}

	public String getCity() {
		return city;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public String getManager() {
		return manager;
	}

	public String getSuperviser() {
		return superviser;
	}

	public void setEvitability(Integer evitability) {
		this.evitability = evitability;
	}

	public Integer getEvitability() {
		return evitability;
	}

	public String getLicenseObs() {
		return licenseObs;
	}

	public void setLicenseObs(String licenseObs) {
		this.licenseObs = licenseObs;
	}

	public boolean isDirector() {
		return director;
	}

	public void setDirector(boolean director) {
		this.director = director;
	}

	public boolean isTemporal() {
		return temporal;
	}

	public void setTemporal(boolean temporal) {
		this.temporal = temporal;
	}

}
