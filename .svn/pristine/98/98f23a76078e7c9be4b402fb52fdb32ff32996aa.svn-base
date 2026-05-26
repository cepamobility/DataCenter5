package persistence.alert.tables;

import java.io.Serializable;

import communication.alert.AlertToData;

public class AlertTo implements Serializable {
	
	Integer id;
	Integer corporation;
	String email;
	
	public AlertTo() {

	}
	
	
	
	public AlertTo(Integer id, Integer corporation, String email) {
		super();
		this.id = id;
		this.corporation = corporation;
		this.email = email;
	}



	public AlertTo(AlertToData data) {
		email = data.getEmail();
		corporation = data.getCorporation();
		id=data.getId();
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

	public void setEmail(String to) {
		this.email = to;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public AlertToData getAlertToData() {
		return new AlertToData(id,corporation,email); 
	}		
	
	   public boolean equals(Object obj) {
	    	
		boolean equalCorp = false;
		boolean equalEmail = false;
	    if(corporation!=null && obj!=null){
    		AlertTo i=(AlertTo)obj;
    		if(i.getCorporation()!=null){
    			equalCorp =  i.getCorporation().equals(this.getCorporation());
    		}
	    }
	    if(email!=null && obj!=null){
    		AlertTo i=(AlertTo)obj;
    		if(i.getEmail()!=null){
    			equalEmail =  i.getEmail().equals(this.getEmail());
    		}
	    }
	    
	    return (equalCorp && equalEmail);
	    }
	   
	public void UpdateAlertTo(AlertToData data) {
			email = data.getEmail();
			corporation = data.getCorporation();
			id=data.getId();
	}
	
	

}
