package persistence.vehicle.tables;


public class VehicleTransporterLink {
	
	private Integer id;
	private Integer corporation;
	private Integer fosa;
	private Integer mdp;
	private Integer lumin;

	public VehicleTransporterLink() {
	}

	public VehicleTransporterLink(Integer[] data) {
		id = data[0];
		corporation = data[1];
		fosa = data[2];
		mdp = data[3];
		if(data.length > 4)
			lumin = data[4];
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFosa() {
		return fosa;
	}

	public void setFosa(Integer fosa) {
		this.fosa = fosa;
	}

	public Integer getMdp() {
		return mdp;
	}

	public void setMdp(Integer mdp) {
		this.mdp = mdp;
	}

	public Integer[] getIds() {
		Integer[] ids = {fosa,mdp};
		return ids;
	}

	public Integer getCorporation() {
		return corporation;
	}

	public void setCorporation(Integer corporation) {
		this.corporation = corporation;
	}

	public Integer getLumin() {
		return lumin;
	}

	public void setLumin(Integer lumin) {
		this.lumin = lumin;
	}


}
