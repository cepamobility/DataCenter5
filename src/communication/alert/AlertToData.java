package communication.alert;

import java.io.Serializable;

public class AlertToData implements Serializable  {
	
	private Integer corporation;
	private String email;
	private Integer id;

	public AlertToData(Integer id,Integer corporation,String email) {
		this.corporation =corporation;
		this.email=email;
		this.id=id;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCorporation() {
		return corporation;
	}

	public void setCorporation(Integer corporation) {
		this.corporation = corporation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AlertToData() {

	}

}
