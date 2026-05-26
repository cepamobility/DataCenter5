package communication.driver.report;

import java.io.Serializable;
import java.util.Date;

import communication.driver.tables.DriverData;

public class DetailedDriverHRDDataType implements Comparable,Serializable  {
	
    
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
    
    /**
     * Para el hrd, condiciones por el que el driver es HRD.
     */
    private String hrdCondition;
    private Date lastUpdate = null;
    private int status;
    private boolean anyPositive;
    private DriverHistoryDataType history;

    private boolean conditional;
    
    private Integer driverPoints; // puntos del conductor cimsa
	private Integer vehicleId;
	private String vehiclePlate;
	private Date lastHRD;
	private Date lastTOJ;
    /**
     * Constructor por Defecto
     *
     */
    public DetailedDriverHRDDataType() {
    }
    
    public DetailedDriverHRDDataType(DetailedDriverHRDDataType drdt, DriverHistoryDataType history) {
    	super();
        this.id = drdt.getId();
        this.firstname = drdt.getFirstname();
        this.lastname = drdt.getLastname();
        this.country = drdt.getCountry();
        this.birthdate = drdt.getBirthdate();
        this.exclusiondate = drdt.getExclusiondate();
        this.contract = drdt.getContract();
        this.divorg11 = drdt.getDivorg11();
        this.divorg12 = drdt.getDivorg12();
        this.divorg13 = drdt.getDivorg13();
        this.divorg21 = drdt.getDivorg21();
        this.divorg22 = drdt.getDivorg22();
        this.divorg23 = drdt.getDivorg23();
        this.divorg31 = drdt.getDivorg31();
        this.divorg32 = drdt.getDivorg32();
        this.divorg33 = drdt.getDivorg33();
        this.divgeo1 = drdt.getDivgeo1();
        this.divgeo2 = drdt.getDivgeo2();
        this.divgeo3 = drdt.getDivgeo3();
        this.hrdCondition = drdt.getHrdCondition();
        this.lastUpdate = drdt.getLastUpdate();
        this.history = history;
        this.conditional = drdt.isConditional();
        this.driverPoints = drdt.getDriverPoints();
        this.vehicleId = drdt.getVehicleId();
        this.vehiclePlate = drdt.getVehiclePlate();
        this.lastHRD = drdt.getLastHRD();
        this.lastTOJ = drdt.getLastTOJ();
    }
    
    /**
     * Constructor por Copia
     * @param drdt
     * @param history
     */
    public DetailedDriverHRDDataType(DetailedDriverHRDDataType drdt) {
    	
    	super();
        this.id = drdt.getId();
        this.firstname = drdt.getFirstname();
        this.lastname = drdt.getLastname();
        this.country = drdt.getCountry();
        this.birthdate = drdt.getBirthdate();
        this.exclusiondate = drdt.getExclusiondate();
        this.contract = drdt.getContract();
        this.divorg11 = drdt.getDivorg11();
        this.divorg12 = drdt.getDivorg12();
        this.divorg13 = drdt.getDivorg13();
        this.divorg21 = drdt.getDivorg21();
        this.divorg22 = drdt.getDivorg22();
        this.divorg23 = drdt.getDivorg23();
        this.divorg31 = drdt.getDivorg31();
        this.divorg32 = drdt.getDivorg32();
        this.divorg33 = drdt.getDivorg33();
        this.divgeo1 = drdt.getDivgeo1();
        this.divgeo2 = drdt.getDivgeo2();
        this.divgeo3 = drdt.getDivgeo3();
        this.hrdCondition = drdt.getHrdCondition();
        this.lastUpdate = drdt.getLastUpdate();
        this.history =drdt.getHistory();
        this.driverPoints = drdt.getDriverPoints();
    }
    
    
    /**
     * 
     * @param drdt DriverReportDataType
     */
    public DetailedDriverHRDDataType(DriverReportDataType drdt){
    	super();
        this.id = drdt.getId();
        this.firstname = drdt.getFirstname();
        this.lastname = drdt.getLastname();
        this.country = drdt.getCountry();
        this.birthdate = drdt.getBirthdate();
        this.exclusiondate = drdt.getExclusiondate();
        this.contract = drdt.getContract();
        this.divorg11 = drdt.getDivorg11();
        this.divorg12 = drdt.getDivorg12();
        this.divorg13 = drdt.getDivorg13();
        this.divorg21 = drdt.getDivorg21();
        this.divorg22 = drdt.getDivorg22();
        this.divorg23 = drdt.getDivorg23();
        this.divorg31 = drdt.getDivorg31();
        this.divorg32 = drdt.getDivorg32();
        this.divorg33 = drdt.getDivorg33();
        this.divgeo1 = drdt.getDivgeo1();
        this.divgeo2 = drdt.getDivgeo2();
        this.divgeo3 = drdt.getDivgeo3();
        this.hrdCondition = drdt.getHrdCondition();
        this.lastUpdate = drdt.getLastUpdate();
        this.history = new DriverHistoryDataType(null,null,null,null);
        this.driverPoints = drdt.getDriverPoints();
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
    public DetailedDriverHRDDataType(Integer id, String firstname, String lastname,
    		String country, Date birthdate, Date exclusiondate, String contract,
    		String divorg11, String divorg12, String divorg13, String divorg21, 
    		String divorg22, String divorg23, String divorg31, String divorg32, 
    		String divorg33, String divgeo2, String divgeo3, DriverHistoryDataType history,
    		Integer driverPoints) {
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
        this.history = history;
        this.driverPoints = driverPoints;
        
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




    public DriverHistoryDataType getHistory() {
		return history;
	}
    
       
    
    
    


	public void setHistory(DriverHistoryDataType history) {
		this.history = history;
	}

	public int getStatus() {
        return status;
    }



    public void setStatus(int status) {
        this.status = status;
    }


    public int compareTo(Object arg0) {
        DetailedDriverHRDDataType dtd = (DetailedDriverHRDDataType)arg0;
        if(lastname.equalsIgnoreCase(dtd.lastname)) {
            return history.compareTo(dtd.history);
        }
        return lastname.toLowerCase().compareTo(dtd.lastname.toLowerCase());
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
		}else{
			this.hrdCondition = hrdCondition;
		}			
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
	 * Retorna una CDX si el driver tiene la condicion "X"
	 * como condicion HRD. Si no tiene dicha condicion devuelve 
	 * un string vacio.
	 * @param number
	 * @return
	 */
	public String getHrdConditions(){
		
		String result = "";
		if (this.hrdCondition.contains(DriverReportDataType.COND1))
			result += "CD1 - ";
		if (this.hrdCondition.contains(DriverReportDataType.COND2))
			result += "CD2 - ";
		if (this.hrdCondition.contains(DriverReportDataType.COND3))
			result += "CD3 - ";
		// Para HRD Monsanto:
		if (this.hrdCondition.contains(DriverReportDataType.COND4))
			result += "CD4 - ";
		if (this.hrdCondition.contains(DriverReportDataType.COND5))
			result += "CD5 - ";				
		return result.substring(0,result.length()-3);
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
	
	public boolean isConditional() {
        return conditional;
    }

    public void setConditional(boolean conditional) {
        this.conditional = conditional;
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

	public void setLastTOJ(Date lastTOJ) {
		this.lastTOJ = lastTOJ;
	}

	public Date getLastTOJ() {
		return lastTOJ;
	}
	
}

