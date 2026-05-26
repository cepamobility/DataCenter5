package communication.driver.report;

import java.io.Serializable;
import java.util.Date;

public class HistoryDataType implements Comparable, Serializable {
	
    protected Integer id;
    protected String firstname;
    protected String lastname;
    protected String country;
    protected Date birthdate;
    protected Date exclusiondate;
    protected String contract;
    protected String corporationId;
    protected String divorg1;
    protected String divorg2;
    protected String divorg3;
    protected String divorg4;
    
    protected Date lastUpdate = null;
    protected int status;
    
    protected String date;
    protected String type;
    protected String clasification;   
    protected String result;
	private Integer reference;

    public HistoryDataType(Date update) {
        lastUpdate = update;        
    }

    public HistoryDataType(Object[] data,Date update) {
        id = (Integer)data[0];
        firstname = (String)data[1];
        lastname = (String)data[2];
        birthdate = (Date)data[3];
        exclusiondate = (Date)data[4];
        contract = (String)data[5];
        corporationId = (String)data[6];
        date = (String)data[7];
        reference = (Integer)data[8];
        type = (String)data[9];
        clasification = (String)data[10];   
        result = (String)data[11];
        country = (String)data[12];
        if(data.length > 13) {
            divorg1 = (String)data[13];
        }
        if(data.length > 14) {
            divorg2 = (String)data[14];
        }
        if(data.length > 15) {
            divorg3 = (String)data[15];
        }
        if(data.length > 16) {
            divorg4 = (String)data[16];
        }
        lastUpdate = update;        
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getClasification() {
        return clasification;
    }

    public String getContract() {
        return contract;
    }

    public String getCountry() {
        return country;
    }

    public String getDate() {
        return date;
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

    public Date getExclusiondate() {
        return exclusiondate;
    }

    public String getFirstname() {
        return firstname;
    }

    public Integer getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public String getResult() {
        return result;
    }

    public int getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setClasification(String clasification) {
        this.clasification = clasification;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDivorg1(String divorg1) {
        this.divorg1 = divorg1;
    }

    public void setDivorg2(String divorg2) {
        this.divorg2 = divorg2;
    }

    public void setDivorg3(String divorg3) {
        this.divorg3 = divorg3;
    }

    public void setExclusiondate(Date exclusiondate) {
        this.exclusiondate = exclusiondate;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int compareTo(Object o) {
        HistoryDataType data = (HistoryDataType)o;
        if(!id.equals(data.id)) {
            return id.compareTo(data.id);
        }
        if(!date.equals(data.date)) {
            return date.compareTo(data.date);
        }
        if(!type.equals(data.type)) {
            return date.compareTo(data.type);
        }
        return 0;
    }
    
    public boolean equals(Object obj) {
    	try {
    		if (obj instanceof HistoryDataType) {
    			HistoryDataType last = (HistoryDataType)obj;
    			if(last.reference == null && reference != null)
    				return false;
    			if(last.reference != null && reference == null)
    				return false;
    			if(last.reference != null && reference != null && !last.reference.equals(reference))
    				return false;
    			return id.equals(last.id) && date.equals(last.date) && type.equals(last.type) && clasification.equals(last.clasification); 
    		}
    	}catch (Exception e) {
			e.printStackTrace();
		}
        return false;
    }

	public String getDivorg4() {
		return divorg4;
	}

	public void setDivorg4(String divorg4) {
		this.divorg4 = divorg4;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

}

