/**
 * Created on 19-may-2008
 * CEPA
 * DataCenter 5
 */
package communication.activity.report;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;


public class CommentaryDriveDataType implements Comparable, Serializable {

    private String firstName;
    private String lastName;
    private String vehicleAllowed;
    private Date date;
    private Integer arId;
    private String conductiontype;
    private String obs;
    private String licenseObs;
    
    private String country;
    private String divOrg1;
    private String divOrg2;
    private String divOrg3;
    private String divOrg4;
    
    private Collection items;

    public CommentaryDriveDataType() {
    }

    public CommentaryDriveDataType(Object[] data) {
        this.firstName = (String)data[0];
        this.lastName = (String)data[1];
        this.vehicleAllowed = (String)data[2];
        this.date = (Date)data[3];
        this.arId = (Integer)data[4];
        this.conductiontype = (String)data[5];
        this.obs = (String)data[6];
        this.country = (String)data[7];
        this.licenseObs = (String)data[8];
        if(data.length > 9) {
            this.divOrg1 = (String)data[9];
        }
        if(data.length > 10) {
            this.divOrg2 = (String)data[10];
        }
        if(data.length > 11) {
            this.divOrg3 = (String)data[11];
        }
        if(data.length > 12) {
            this.divOrg4 = (String)data[12];
        }
        items = new LinkedList();
    }

    public CommentaryDriveDataType(Object[] data, String type) {
        this.firstName = (String)data[0];
        this.lastName = (String)data[1];
        this.vehicleAllowed = (String)data[2];
        this.date = (Date)data[3];
        this.arId = (Integer)data[4];
        this.conductiontype = type;
        this.obs = (String)data[5];
        this.country = (String)data[6];
        this.licenseObs = (String)data[7];
        if(data.length > 8) {
            this.divOrg1 = (String)data[8];
        }
        if(data.length > 9) {
            this.divOrg2 = (String)data[9];
        }
        if(data.length > 10) {
            this.divOrg3 = (String)data[10];
        }
        if(data.length > 11) {
            this.divOrg4 = (String)data[11];
        }
        items = new LinkedList();
    }

    public Integer getArId() {
        return arId;
    }

    public String getConductiontype() {
        return conductiontype;
    }

    public String getCountry() {
        return country;
    }

    public Date getDate() {
        return date;
    }

    public String getDivOrg1() {
        return divOrg1;
    }

    public String getDivOrg2() {
        return divOrg2;
    }

    public String getDivOrg3() {
        return divOrg3;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getObs() {
        return obs;
    }

    public void setArId(Integer arId) {
        this.arId = arId;
    }

    public void setConductiontype(String conductiontype) {
        this.conductiontype = conductiontype;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDivOrg1(String divOrg1) {
        this.divOrg1 = divOrg1;
    }

    public void setDivOrg2(String divOrg2) {
        this.divOrg2 = divOrg2;
    }

    public void setDivOrg3(String divOrg3) {
        this.divOrg3 = divOrg3;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public void addItem(Integer item) {
        items.add(item);
    }

    public void addItem(Object[] item) {
        items.add(item);
    }

    public String containItem(Integer item) {
        return (items.contains(item)) ? "1" : "";
    }

    public int containData(Integer item) {
        Iterator it = items.iterator();
        while(it.hasNext()) {
            Object[] data = (Object[])it.next();
            if(((Integer)data[1]).intValue() == item.intValue()) {
                return ((Integer)data[2]).intValue();
            }
        }
        return 0;
    }

    public int compareTo(Object o) {
        return date.compareTo(((CommentaryDriveDataType)o).date);
    }

    public String getVehicleAllowed() {
        return vehicleAllowed;
    }

    public void setVehicleAllowed(String vehicleAllowed) {
        this.vehicleAllowed = vehicleAllowed;
    }

	public String getDivOrg4() {
		return divOrg4;
	}

	public void setDivOrg4(String divOrg4) {
		this.divOrg4 = divOrg4;
	}

	public String getLicenseObs() {
		return licenseObs;
	}

	public void setLicenseObs(String licenseObs) {
		this.licenseObs = licenseObs;
	}
    
    
}
