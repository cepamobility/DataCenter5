package persistence.driver.tables;


public class DriverLink {
	
	private Integer id;
	private Integer fosa;
	private Integer weyerhauser;
	private Integer mdp;
	
	public DriverLink() {
	}

	public DriverLink(Integer[] data) {
		id = data[0];
		fosa = data[1];
		weyerhauser = data[2];
		mdp = data[3];
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

	public Integer getWeyerhauser() {
		return weyerhauser;
	}

	public void setWeyerhauser(Integer weyerhauser) {
		this.weyerhauser = weyerhauser;
	}

	public Integer getMdp() {
		return mdp;
	}

	public void setMdp(Integer mdp) {
		this.mdp = mdp;
	}

	public Integer[] getIds() {
		Integer[] ids = {fosa,weyerhauser,mdp};
		return ids;
	}


}
