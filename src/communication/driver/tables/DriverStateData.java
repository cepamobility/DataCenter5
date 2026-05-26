package communication.driver.tables;

import java.util.ArrayList;
import java.util.Date;


public class DriverStateData  {

	public static String ACTIVE="ACTIVE";
	
	private Date stateDate;
    private String state;
	private String reasonDescription;
    private ArrayList<DriverStateHistoryData> history;
    
	public Date getStateDate() {
		return stateDate;
	}
	public void setStateDate(Date stateDate) {
		this.stateDate = stateDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getReasonDescription() {
		return reasonDescription;
	}
	public void setReasonDescription(String reasonDescription) {
		this.reasonDescription = reasonDescription;
	}
	public ArrayList<DriverStateHistoryData> getHistory() {
		return history;
	}
	public void setHistory(ArrayList<DriverStateHistoryData> history) {
		this.history = history;
	}
    
    
    
    
	
}
