package communication.external.instructors;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChecklistResultRestData {
	
	private Integer activityId;
	private Integer driverId;
	private String firstName;
	private String lastName;
	private String email;
	private String sexo;
	private String corporationId;
	private Integer corpId;
	private boolean temp;
	private String result;
	public ArrayList<PointRestData> points;
	public ArrayList<ChecklistObservationData> observations;
	
	
 
	public ArrayList<ChecklistObservationData> getObservations() {
		return observations;
	}
	public void setObservations(ArrayList<ChecklistObservationData> observations) {
		this.observations = observations;
	}
	public ArrayList<PointRestData> getPoints() {
		return points;
	}
	public void setPoints(ArrayList<PointRestData> points) {
		this.points = points;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Integer getCorpId() {
		return corpId;
	}
	public void setCorpId(Integer corpId) {
		this.corpId = corpId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCorporationId() {
		return corporationId;
	}
	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}
	public boolean isTemp() {
		return temp;
	}
	public void setTemp(boolean temp) {
		this.temp = temp;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Integer getDriverId() {
		return driverId;
	}
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
	 

	public String sexoDT() {
		if(sexo!=null){
			if(sexo.equalsIgnoreCase("m")){
				return "datatype.sex.male";
			}else{
				return "datatype.sex.female";
			}
		}

		return "datatype.sex.male";
	}

}
