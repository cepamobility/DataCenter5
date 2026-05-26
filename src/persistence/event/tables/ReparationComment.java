
package persistence.event.tables;

import java.util.Date;

import communication.event.tables.ReparationItemData;

public class ReparationComment {
	
    protected Integer id;

    protected Integer reparation;

    protected Integer maintenance1;
    protected String comment;
    
	public ReparationComment() {
	}

	public ReparationComment(Integer reparation, int maintenance, String comment) {
		this.reparation = reparation;
		this.maintenance1 = maintenance;
		this.comment = comment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getReparation() {
		return reparation;
	}

	public void setReparation(Integer reparation) {
		this.reparation = reparation;
	}

	public Integer getMaintenance1() {
		return maintenance1;
	}

	public void setMaintenance1(Integer maintenance1) {
		this.maintenance1 = maintenance1;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
}
