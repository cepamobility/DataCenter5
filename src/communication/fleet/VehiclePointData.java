package communication.fleet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import communication.administration.corporation.tables.PointMoveData;
import communication.report.UnconformityAction;
import communication.vehicle.tables.VehicleStatusData;


public class VehiclePointData  {

	private String plate;
	private ArrayList<ResumePointData> resume=new ArrayList<ResumePointData>();
    private ArrayList<PointMoveData> moves=new ArrayList<PointMoveData>();
    
	HashMap<String, ResumePointData> resumeMap=new HashMap<String, ResumePointData>();
	
	private Integer checks = 0;
	private Integer seguimientos = 0;
	private Integer preventivos = 0;
    
	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}
	
	public ArrayList<ResumePointData> getResume() {
		return resume;
	}
	public void setResume(ArrayList<ResumePointData> resume) {
		this.resume = resume;
	}
	public ArrayList<PointMoveData> getMoves() {
		return moves;
	}
	public void setMoves(ArrayList<PointMoveData> moves) {
		this.moves = moves;
	}

	
	public void addMoveResume(PointMoveData move) {
		if(!resumeMap.containsKey(move.getDescription())){
			resumeMap.put(move.getDescription(), new ResumePointData(move.getDescription()));
		}
		resumeMap.get(move.getDescription()).addPoints(move.getAmount().intValue());
	}
	
	public void saveResume() {
		Collection<String> moveList = new LinkedList<String>();
		moveList.addAll(resumeMap.keySet());
		Collections.sort((List)moveList);
		Iterator<String> moveIt = moveList.iterator();
		while(moveIt.hasNext()) {
			resume.add(resumeMap.get(moveIt.next()));
		}
	}

	public String toString() {
		String s = plate+"\n";
		Iterator<PointMoveData> it = moves.iterator();
		while(it.hasNext()) 
			s += "--------"+it.next().toString()+"\n";
		return s;
	}

	public Integer getChecks() {
		return checks;
	}

	public Integer getSeguimientos() {
		return seguimientos;
	}

	public void setSeguimientos(Integer seguimientos) {
		this.seguimientos = seguimientos;
	}

	public Integer getPreventivos() {
		return preventivos;
	}

	public void setPreventivos(Integer preventivos) {
		this.preventivos = preventivos;
	}

	public void setChecks(Integer checks) {
		this.checks = checks;
	}

	
}
