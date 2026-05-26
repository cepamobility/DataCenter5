package communication.external.instructors;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChecklistResultsRestData {
	
	private String user;
	private String password;
	
	ArrayList<ChecklistResultRestData> data;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<ChecklistResultRestData> getData() {
		return data;
	}

	public void setData(ArrayList<ChecklistResultRestData> data) {
		this.data = data;
	}
	
	
	
	

}
