package communication.driver.report;

import java.io.Serializable;

public class DriverHistoryDataType implements Comparable, Serializable {

	private String date;
	private String type;
	private String clasification;	
	private String result;
	
	public DriverHistoryDataType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * @param date
	 * @param type
	 * @param clasification
	 * @param result
	 */
	public DriverHistoryDataType(String date, String type, String clasification, String result) {
		super();
		// TODO Auto-generated constructor stub
		this.date = date;
		this.type = type;
		this.clasification = clasification;
		this.result = result;
	}



	public String getClasification() {
		return clasification;
	}

	public void setClasification(String clasification) {
		this.clasification = clasification;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	public int compareTo(Object arg0) {
		DriverHistoryDataType dhd = (DriverHistoryDataType)arg0;
		if(date == null || dhd.date == null)
			return (date == null) ? -1 : 1;
		int dateCompare = date.toLowerCase().compareTo(dhd.date.toLowerCase());
        if (dateCompare == 0){
        	dateCompare = type.toLowerCase().compareTo(((DriverHistoryDataType)arg0).type.toLowerCase());
        }
	    return dateCompare;
    }
}
