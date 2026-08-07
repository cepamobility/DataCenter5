package communication.vehicle.tables;

import java.util.Date;
import java.util.List;
import java.util.Map;

import communication.administration.user.UserSessionData;
import communication.administration.user.tables.UserData;
import persistence.vehicle.tables.InstructorActivity;

public class InstructorActivityData {
	
	//CONTRACTORS
	public static final int CONTRACTOR_MDP = 3;
	public static final int CONTRACTOR_FOSA = 4;
	
	//CATEGORIES
	public static final int CONSULTA_TECNICA = 1;
	public static final int CONSULTA_CAMINERA = 2;
	public static final int CONSULTAS_VARIAS = 3;
	
	//Sub Categories
	public static final String[] SUBCONSULTAS_1 = {"Falla Computadora", "Plataforma de Carga", "Sistema de Seguridad", "Sistema de frenos", "Ejes", "Otros"};
	public static final String[] SUBCONSULTAS_2 = {"Consulta de caminería/ingreso/salida"};
	public static final String[] SUBCONSULTAS_3 = {"Consultas varias"};
	
	
	// Contact Channel
	public static final String[] CHANNELS = {"WhatsApp", "Llamada Telefónica", "Visita Presencial", "Email", "Reunion Virtual"};
	
	private Integer id;
	private UserSessionData user;
	private Integer corporation;
	private Integer category;
	private Integer subCategory;
	private String location;
	private Date date;
	private Integer channel;
	private String referent;
	private String observation;
	
	
	
	public InstructorActivityData() {
	}
	
	public InstructorActivityData(InstructorActivity instructorActivity) {
		this.id = instructorActivity.getId();
		this.corporation = instructorActivity.getCorporation();
		this.category = instructorActivity.getCategory();
		this.subCategory = instructorActivity.getSubCategory();
		this.location = instructorActivity.getLocation();
		this.date = instructorActivity.getDate();
		this.channel = instructorActivity.getChannel();
		this.referent = instructorActivity.getReferent();
		this.observation = instructorActivity.getObservation();
	}

	public InstructorActivityData(Integer id, UserSessionData user, Integer category, Integer corporation, Integer subCategory, String location,
			Date date, Integer channel, String referent, String observation) {
		super();
		this.id = id;
		this.user = user;
		this.corporation = corporation;
		this.category = category;
		this.subCategory = subCategory;
		this.location = location;
		this.date = date;
		this.channel = channel;
		this.referent = referent;
		this.observation = observation;
	}
	
	public String getChannelName() {
		return CHANNELS[channel-1];
	}
	
	public String[] getCategoryName () {
		String[] categoria = new String[2];
		switch (category) {
			case CONSULTA_TECNICA: 
				categoria[0] = "Consulta técnica";
				categoria[1] = SUBCONSULTAS_1[subCategory-1];
				break;
			case CONSULTA_CAMINERA: 
				categoria[0] = "Consulta de caminería/ingreso/salida";
				categoria[1] = SUBCONSULTAS_2[subCategory-1];
				break;
			case CONSULTAS_VARIAS: 
				categoria[0] = "Consultas varias";
				categoria[1] = SUBCONSULTAS_3[subCategory-1];
				break;
		}
		
		return categoria;
	}
	
	public String getCorporationName() {
		switch (corporation) {
			case 50: return "Montes del Plata";
			case 9: return "Forestal Oriental";
			case CONTRACTOR_MDP: return "Contratista Montes del Plata";
			case CONTRACTOR_FOSA: return "Contratista Forestal Oriental";
			default: return "";
		}
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public UserSessionData getUser() {
		return user;
	}
	
	public void setUser(UserSessionData user) {
		this.user = user;
	}
	
	public Integer getCorporation() {
		return corporation;
	}
	
	public void setCorporation(Integer corporation) {
		this.corporation = corporation;
	}
	
	public Integer getSubCategory() {
		return subCategory;
	}
	
	public void setSubCategory(Integer subCategory) {
		this.subCategory = subCategory;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Integer getChannel() {
		return channel;
	}
	
	public void setChannel(Integer channel) {
		this.channel = channel;
	}
	
	public String getReferent() {
		return referent;
	}
	
	public void setReferent(String referent) {
		this.referent = referent;
	}
	
	public String getObservation() {
		return observation;
	}
	
	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}
}
