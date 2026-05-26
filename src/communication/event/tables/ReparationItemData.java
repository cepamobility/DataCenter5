package communication.event.tables;

import java.util.Date;

import org.hibernate.classic.Session;

import communication.security.SecurityConstants;

public class ReparationItemData implements Comparable<ReparationItemData> {

    public static final int PENDING = 0;
    public static final int REPAIRED = 1;
    public static final int NOT_NEED = 2;

	protected Integer id;

    protected Integer maintenance1;
    protected Integer maintenance2;
    
    protected Integer status;
    protected Date date;
    protected String login; 
    protected String obs; 

	public ReparationItemData() {
	}

	public ReparationItemData(int maintenance1, int maintenance2) {
		this.maintenance1 = maintenance1;
		this.maintenance2 = maintenance2;
		status = PENDING;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getMaintenance1() {
		return maintenance1;
	}

	public void setMaintenance1(Integer maintenance1) {
		this.maintenance1 = maintenance1;
	}

	public Integer getMaintenance2() {
		return maintenance2;
	}

	public void setMaintenance2(Integer maintenance2) {
		this.maintenance2 = maintenance2;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
/*
	public ReparationItemData getData() {
		ReparationData data = new ReparationData(id);
		data.setVehicle(vehicle.getPrincipalAttributes());
		data.setDriver(driver.getDriverData());
		data.setDate(date);
		data.setMaintenance1(maintenance1);
		data.setMaintenance2(maintenance2);
		data.setStatus(status);
		data.setObservation(observation);
		data.setKms(kms);
		data.setItems(items);
		return data;
	}
*/

	public String getLogin() {
		String[] users = ReparationData.LOGIN_REPARATION_USERS;
		String[] names = ReparationData.LOGIN_REPARATION_NAMES;
		for(int i = 0; i < users.length; i++) {
			if(login.equals(users[i])) 
				return names[i];
		}
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public int compareTo(ReparationItemData arg0) {
		if(maintenance1 != null && arg0.maintenance1 != null && !maintenance1.equals(arg0.maintenance1))
			return maintenance1.compareTo(arg0.maintenance1);
		if(maintenance2 != null && arg0.maintenance2 != null && !maintenance2.equals(arg0.maintenance2))
			return maintenance2.compareTo(arg0.maintenance2);
		if(id != null && arg0.id != null && !id.equals(arg0.id))
			return id.compareTo(arg0.id);
		return 0;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

}
