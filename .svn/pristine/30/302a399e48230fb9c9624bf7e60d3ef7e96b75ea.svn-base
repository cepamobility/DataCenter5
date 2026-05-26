/**
 * Created on 22-jun-2007
 * CEPA
 * DataCenter 5
 */
package persistence.driver.tables;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.classic.Session;

public class MitigationPlanActivityPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private MitigationPlan plan;
    private Integer activity;

    public MitigationPlanActivityPK() {
    }

    public MitigationPlanActivityPK(Integer activity, MitigationPlan plan) {
        this.plan = plan;
        this.activity = activity;
    }

	public MitigationPlanActivityPK(Integer activity, Integer planId, Session session) {
		plan = (MitigationPlan) session.load(MitigationPlan.class, planId);
        this.activity = activity;
	}

	public MitigationPlan getPlan() {
		return plan;
	}

	public void setPlan(MitigationPlan plan) {
		this.plan = plan;
	}

	public Integer getActivity() {
		return activity;
	}

	public void setActivity(Integer activity) {
		this.activity = activity;
	}
}
