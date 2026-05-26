package persistence.activity.tables;

import java.util.Collection;
import java.util.LinkedList;


public class ActivityLink {
	
	private ActivityLinkPK pk;
	private Integer fosa;
	private Integer weyerhauser;
	private Integer mdp;
	
	public ActivityLink() {
	}

	public ActivityLink(Integer[] data) {
		pk = new ActivityLinkPK(data);
		fosa = data[2];
		weyerhauser = data[3];
		mdp = data[4];
	}


	public ActivityLinkPK getPk() {
		return pk;
	}

	public void setPk(ActivityLinkPK pk) {
		this.pk = pk;
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
