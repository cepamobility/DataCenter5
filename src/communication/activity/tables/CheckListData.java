package communication.activity.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CheckListData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public static final int TRUCK_CHECKLIST = 12;
    public static final int TRAILER_CHECKLIST = 13;
    public static final int PURSUIT_ROUTE_CHECKLIST = 15;
    public static final int COMMENTARY_DRIVE_ARGENTINA = 16;
    public static final int CENAPRA_PREVIOUS_CHECKLIST = 20;
    public static final int CENAPRA_POST_CHECKLIST = 21;
    public static final int CENAPRA_SATISFACTION_CHECKLIST = 22;
    public static final int CIMSA_CHECKLIST = 37;
    public static final int COMMENTARY_DRIVE_NALCO = 41;
    public static final int SYNGENTA_CHECKLIST = 42;
    public static final int EBTW = 50;
    public static final int COMMENTARY_DRIVE_ARGENTINA_NUEVO = 53;
    public static final int REPORTERJJ_SELF_EVALUATION = 62;
    public static final int REPORTERJJ_COMMENTARYDRIVE = 64;
    
    public static final int COMMENTARY_DRIVE_JJ2 = 66;
    public static final int COMMENTARY_DRIVE_NOVARTIS = 67;

    public static final int DPA_CHECKLIST_T1 = 75;
    public static final int DPA_CHECKLIST_T2 = 78;
    
    public static final int UPM_CHECKLIST_SEGRUTA = 79;
    public static final int UPM_CHECKLIST_PESADOS = 80;
    public static final int UPM_CHECKLIST_INTERNO = 84;
    public static final int MDP_CHECKLIS_MERCANCIASPELIGROSAS = 91;
    public static final int MDP_CHECKLIS_MERCANCIASPELIGROSAS_2 = 92;
    
    public static final int SURVEY = 89;
    
    public static final int SURVEY_DEFAULT_VEHICLE = 504924;
    
    public static final int TRUCK = 0;
    public static final int TRAILER = 1;
    
    //CHECK LIST FORMULARIO ACU 
    public static final int CHECK_LIST_FORMULARIO_ACU = 86;
    
    public static final int CHECK_LIST_SYNGENTA_BR = 95;
    public static final int FEEDBACK_LINE_MANAGER = 98;
    
    
	private Integer id;
	
	private String goals;
	private String scope; 
	
    private Collection<PointData> points;
	
    public CheckListData() {
        super();
    }

    /**
     * @param id
     * @param goals
     * @param scope
     * @param points
     */
    public CheckListData(Integer id, String goals, String scope, Collection<PointData> points) {
        this.id = id;
        this.goals = goals;
        this.scope = scope;
        this.points = points;
    }

    /**
	 * @return Returns the goals.
	 */
	public String getGoals() {
		return goals;
	}
	/**
	 * @param goals The goals to set.
	 */
	public void setGoals(String goals) {
		this.goals = goals;
	}
	/**
	 * @return Returns the points.
	 */
	public Collection<PointData> getPoints() {
		return points;
	}
	/**
	 * @param points The points to set.
	 */
	public void setPoints(Collection<PointData> points) {
		this.points = points;
	}
	/**
	 * @return Returns the scope.
	 */
	public String getScope() {
		return scope;
	}
	/**
	 * @param scope The scope to set.
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * @return Returns the id.
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	} 
	
    public Iterator<PointData> getSortPoints() {
        Collections.sort((List<PointData>) points);
        return points.iterator();
    }

    public int getItemCount() {
        int count = 0;
        Iterator<PointData> it = points.iterator();
        while(it.hasNext()) {
            count += ((PointData)it.next()).getItems().size();
        }
        return count;
    }

	public PointData getPoint(Integer pointId) {
        Iterator<PointData> it = points.iterator();
        while(it.hasNext()) {
        	PointData point = it.next();
        	if(point.getId().intValue() == pointId.intValue()) {
        		return point;
        	}
        }
		return null;
	}
}
