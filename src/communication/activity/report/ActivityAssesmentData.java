package communication.activity.report;

public class ActivityAssesmentData {

	protected Integer id;
    protected String name;
    protected String date;
    protected boolean testComplete;
    protected String user;
    
    
    
    
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isTestComplete() {
		return testComplete;
	}
	public void setTestComplete(boolean testComplete) {
		this.testComplete = testComplete;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
    
    
}
