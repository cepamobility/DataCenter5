package persistence.util.tables;

import java.util.Date;

public class DriverAccessLog {

	public DriverAccessLogPK pk;
	
	public Integer contratist;
	public Integer access;
	
	public Integer active;
	public Integer license;
	public Integer health;
	public Integer aptitud;
	public Integer radar;
	public Integer induction;

	public DriverAccessLog() {
	}

	public DriverAccessLog(String user,Integer driver, Integer contratist, Object[] errors) {
		pk = new DriverAccessLogPK(driver,user,new Date());
		this.contratist = contratist;
		access = new Integer(0);
		if(errors[0] != null) {
			active = new Integer(1);
			access = new Integer(1);
		}else {
			active = new Integer(0);
		}
		if(errors[1] != null) {
			if(errors[1] instanceof Integer) {
				license = new Integer(1);
			}else {
				license = new Integer(2);
			}
			access = new Integer(1);
		}else {
			license = new Integer(0);
		}
		if(errors[2] != null) {
			if(errors[2] instanceof Integer) {
				health = new Integer(1);
			}else {
				health = new Integer(2);
			}
			access = new Integer(1);
		}else {
			health = new Integer(0);
		}
		if(errors[3] != null) {
			if(errors[3] instanceof Integer) {
				aptitud = (Integer)errors[3];
			}else {
				aptitud = new Integer(3);
			}
			access = new Integer(1);
		}else {
			aptitud = new Integer(0);
		}
		if(errors[4] != null) {
			radar = new Integer(1);
			access = new Integer(1);
		}else {
			radar = new Integer(0);
		}
		if(errors[5] != null) {
			induction = new Integer(1);
			access = new Integer(1);
		}else {
			induction = new Integer(0);
		}
	}

	public DriverAccessLogPK getPk() {
		return pk;
	}

	public void setPk(DriverAccessLogPK pk) {
		this.pk = pk;
	}

	public Integer getContratist() {
		return contratist;
	}

	public void setContratist(Integer contratist) {
		this.contratist = contratist;
	}

	public Integer getAccess() {
		return access;
	}

	public void setAccess(Integer access) {
		this.access = access;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getLicense() {
		return license;
	}

	public void setLicense(Integer license) {
		this.license = license;
	}

	public Integer getHealth() {
		return health;
	}

	public void setHealth(Integer health) {
		this.health = health;
	}

	public Integer getAptitud() {
		return aptitud;
	}

	public void setAptitud(Integer aptitud) {
		this.aptitud = aptitud;
	}

	public Integer getRadar() {
		return radar;
	}

	public void setRadar(Integer radar) {
		this.radar = radar;
	}

	public Integer getInduction() {
		return induction;
	}

	public void setInduction(Integer induction) {
		this.induction = induction;
	}
	
}
