/**
 * Created on 22-jun-2007
 * CEPA
 * DataCenter 5
 */
package persistence.driver.tables;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import communication.driver.tables.MitigationPlanActivityData;

public class MitigationPlanActivity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MitigationPlanActivityPK pk;
    private boolean done;

    public MitigationPlanActivity() {
    }

    public MitigationPlanActivity(boolean done, MitigationPlanActivityPK pk) {
        this.pk = pk;
        this.done = done;
    }

	public MitigationPlanActivityPK getPk() {
		return pk;
	}

	public void setPk(MitigationPlanActivityPK pk) {
		this.pk = pk;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public MitigationPlanActivityData getData() {
		return new MitigationPlanActivityData(pk.getActivity(),done);
	}

}
