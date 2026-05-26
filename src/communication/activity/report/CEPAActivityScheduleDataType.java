/**
 * Created on 28-sep-2006
 * CEPA
 * DataCenter 5
 */
package communication.activity.report;

import java.io.Serializable;
import java.util.Date;

import communication.driver.tables.DriverData;

public class CEPAActivityScheduleDataType implements Comparable, Serializable {

    private Integer id;
    private Date date;    
    private String type;
    private String country;
    private Integer participantsCount;

    private String divorg11;
    private String divorg12;
    private String divorg13;
    private String divorg21;
    private String divorg22;
    private String divorg23;
    private String divorg31;
    private String divorg32;
    private String divorg33;
    private String divgeo1;
    private String divgeo2;
    private String divgeo3;
    
    private String state;
    
    
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
    public CEPAActivityScheduleDataType(Integer id, String type, Date date, String country,Integer participantsCount,String divorg11, String divorg12, String divorg13, String divorg21, String divorg22, String divorg23, String divorg31, String divorg32, String divorg33, String divgeo2, String divgeo3, String state) {
        super();
        this.id = id;
        this.type = type;
        this.date = date;
        this.country = country;
        this.participantsCount = participantsCount;        

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
        this.state = state;
    }
    
    public CEPAActivityScheduleDataType(Object[] data,int[][] principalDivOrgs) {
        this.id = (Integer) data[0];
        this.date = (Date) data[1];
        this.type = (String) data[2];
        this.country = (String) data[3];
        this.state = (String) data[4];
        for(int i = 5; i < data.length; i++) {
        	putDivOrg((String)data[i],principalDivOrgs[i-5]);
        }
	}

	private void putDivOrg(String value, int[] index) {
		switch(index[0]) {
			case 1:
				switch(index[1]) {
					case 1:
						divorg11 = value;
						break;
					case 2:
						divorg12 = value;
						break;
					case 3:
						divorg13 = value;
						break;
				}
				break;
			case 2:
				switch(index[1]) {
					case 1:
						divorg21 = value;
						break;
					case 2:
						divorg22 = value;
						break;
					case 3:
						divorg23 = value;
						break;
				}
				break;
			case 3:
				switch(index[1]) {
				case 1:
					divorg31 = value;
					break;
				case 2:
					divorg32 = value;
					break;
				case 3:
					divorg33 = value;
					break;
			}
			break;
		}
	}

	public String getDO(int index0, int index1) {
		switch(index0) {
			case 1:
				switch(index1) {
					case 1:
						return divorg11;
					case 2:
						return divorg12;
					case 3:
						return divorg13;
				}
				break;
			case 2:
				switch(index1) {
					case 1:
						return divorg21;
					case 2:
						return divorg22;
					case 3:
						return divorg23;
				}
				break;
			case 3:
				switch(index1) {
					case 1:
						return divorg31;
					case 2:
						return divorg32;
					case 3:
						return divorg33;
				}
			break;
		}
		return null;
	}

	public Date getDate() {
        return date;
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

    public Integer getId() {
        return id;
    }


    public void setDate(Date date) {
        this.date = date;
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

    public void setId(Integer id) {
        this.id = id;
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

    public int compareTo(Object arg0) {
        //return lastname.toLowerCase().compareTo(((CEPAActivityScheduleDataType)arg0).lastname.toLowerCase());
    	
    	return 0;
    }

	public Integer getParticipantsCount() {
		return participantsCount;
	}

	public void setParticipantsCount(Integer participantsCount) {
		this.participantsCount = participantsCount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}
