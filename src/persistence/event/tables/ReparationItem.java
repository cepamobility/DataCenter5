
package persistence.event.tables;

import java.util.Date;

import communication.event.tables.ReparationItemData;

public class ReparationItem {
	
    protected Integer id;

    protected Integer reparation;

    protected Integer maintenance1;
    protected Integer maintenance2;
    
    protected Integer status;
    protected Date date;
    protected String login; 
    protected String obs; 

	public ReparationItem() {
	}

	public ReparationItem(ReparationItemData next) {
		this.id = next.getId();
		this.maintenance1 = next.getMaintenance1();
		this.maintenance2 = next.getMaintenance2();
		this.status = next.getStatus();
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

	public ReparationItemData getData() {
		ReparationItemData data = new ReparationItemData();
		data.setId(id);
		data.setDate(date);
		data.setMaintenance1(maintenance1);
		data.setMaintenance2(maintenance2);
		data.setStatus(status);
		data.setLogin(login);
		data.setDate(date);
		data.setObs(obs);
		return data;
	}

	public Integer getReparation() {
		return reparation;
	}

	public void setReparation(Integer reparation) {
		this.reparation = reparation;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}


	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

}
