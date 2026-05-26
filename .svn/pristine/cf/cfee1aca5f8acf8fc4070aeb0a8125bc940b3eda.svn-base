package communication.alert;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class TravelAlertData implements Serializable{

	public static final String STATE_1_PT = "Pendente"; // Pendiente
	public static final String STATE_2_PT = "Iniciado"; // Iniciado

	public static final String STATE_1_ES = "Pendiente"; // Pendiente
	public static final String STATE_2_ES = "Iniciado"; // Iniciado
	
	public static final String STATE_1_EN = "Pending"; // Pendiente
	public static final String STATE_2_EN = "Started"; // Iniciado
	
	
	
	private String firstName;
	private String lastName;
	private Date dateleave;
	private Time hourleave;
	private Integer state;
	
		
	public String getStateText(String language){
		switch(state){
			case 1: if (language.equalsIgnoreCase("pt")){
						return STATE_1_PT;
					}else if (language.equalsIgnoreCase("es")){
						return STATE_1_ES;						
					}else
						return STATE_1_EN;
			
			case 2: if (language.equalsIgnoreCase("pt")){
						return STATE_2_PT;
					}else if (language.equalsIgnoreCase("es")){
						return STATE_2_ES;						
					}else
						return STATE_2_EN;
		}
		return "";
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the dateleave
	 */
	public Date getDateleave() {
		return dateleave;
	}
	/**
	 * @param dateleave the dateleave to set
	 */
	public void setDateleave(Date dateleave) {
		this.dateleave = dateleave;
	}
	/**
	 * @return the hourleave
	 */
	public Time getHourleave() {
		return hourleave;
	}
	/**
	 * @param hourleave the hourleave to set
	 */
	public void setHourleave(Time hourleave) {
		this.hourleave = hourleave;
	}
	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	public TravelAlertData(String firstName, String lastName, Date dateleave,
			Time hourleave, Integer state) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateleave = dateleave;
		this.hourleave = hourleave;
		this.state = state;
	}
	public TravelAlertData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateleave == null) ? 0 : dateleave.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((hourleave == null) ? 0 : hourleave.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TravelAlertData other = (TravelAlertData) obj;
		if (dateleave == null) {
			if (other.dateleave != null)
				return false;
		} else if (!dateleave.equals(other.dateleave))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (hourleave == null) {
			if (other.hourleave != null)
				return false;
		} else if (!hourleave.equals(other.hourleave))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	
	
	
}
