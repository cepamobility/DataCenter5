package communication.administration.corporation;

import java.io.Serializable;

public class WSResultData implements Serializable {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;//0 ok, 1-Eror 1, 2--Error2...etc;
	private String description;
	
	public WSResultData(){}
	
	public WSResultData(int code, String description){
		this.code=code;
		this.description=description;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	

}
