package communication.activity.tables;

import java.util.Date;

public class CepaSystemDataType {

	private Integer activity;
	private String type;
	private Date date;
	private String firstname;
	private String lastname;
	private String note;
	private String obs;
	private Integer[] values;

	private String divOrg1;
	private String divOrg2;
	private String divOrg3;
	private String divOrg4;

	public CepaSystemDataType(Object[] data) {
		activity = (Integer) data[0];
		type = (String) data[1];
		date = (Date) data[2];
		firstname = (String) data[3];
		lastname = (String) data[4];
		note = (String) data[5];
		obs = (String) data[6];
		values = new Integer[7];
		values[((Integer) data[9]).intValue()-1] = (Integer) data[8];
		if(data.length > 10) {
			divOrg1 = (String) data[10];
		}
		if(data.length > 11) {
			divOrg2 = (String) data[11];
		}
		if(data.length > 12) {
			divOrg3 = (String) data[12];
		}
		if(data.length > 13) {
			divOrg4 = (String) data[13];
		}
	}

	public void addValue(Object[] data) {
		values[((Integer) data[9]).intValue()-1] = (Integer) data[8];
	}

	public Integer getActivity() {
		return activity;
	}

	public String getType() {
		return type;
	}

	public Date getDate() {
		return date;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getNote() {
		return note;
	}

	public String getObs() {
		return obs;
	}

	public Integer[] getValues() {
		return values;
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

	public String getDivOrg4() {
		return divOrg4;
	}

	
}
