package communication.util;

public class ContratistDataType {

	public Integer contratist;
	public Integer type;
	public String emails;

	public ContratistDataType() {
	}

	public ContratistDataType(Integer contratist,Integer type, String emails) {
		this.contratist = contratist; 
		this.type = type;
		this.emails = emails;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public Integer getContratist() {
		return contratist;
	}

	public void setContratist(Integer contratist) {
		this.contratist = contratist;
	}
	
	
}
