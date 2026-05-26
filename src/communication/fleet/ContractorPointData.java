package communication.fleet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import communication.administration.corporation.tables.PointMoveData;


public class ContractorPointData  {

	private String name;
	private ArrayList<ResumePointData> resume=new ArrayList<ResumePointData>();
	private ArrayList<VehiclePointData> vehicles=new ArrayList<VehiclePointData>();
	
	HashMap<String, ResumePointData> resumeMap=new HashMap<String, ResumePointData>();
	
	private Integer checks = 0;
	private Integer seguimientos = 0;
	private Integer preventivos = 0;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<ResumePointData> getResume() {
		return resume;
	}
	
	public void setResume(ArrayList<ResumePointData> resume) {
		this.resume = resume;
	}
	
	public ArrayList<VehiclePointData> getVehicles() {
		return vehicles;
	}
	
	public void setVehicles(ArrayList<VehiclePointData> vehicles) {
		this.vehicles = vehicles;
	}
	
	public void addMoveResume(PointMoveData move) {
		if(!resumeMap.containsKey(move.getDescription())){
			resumeMap.put(move.getDescription(), new ResumePointData(move.getDescription()));
		}
		resumeMap.get(move.getDescription()).addPoints(move.getAmount().intValue());
	}
	
	public void saveResume() {
		Collection<String> moves = new LinkedList<String>();
		moves.addAll(resumeMap.keySet());
		Collections.sort((List)moves);
		Iterator<String> moveIt = moves.iterator();
		while(moveIt.hasNext()) {
			resume.add(resumeMap.get(moveIt.next()));
		}
		
		Iterator<VehiclePointData> vehicleIt = vehicles.iterator();
		while(vehicleIt.hasNext()) {
			VehiclePointData vehiclePointData = vehicleIt.next();
			checks += vehiclePointData.getChecks();
			seguimientos += vehiclePointData.getSeguimientos();
			preventivos += vehiclePointData.getPreventivos();
		}
	}
    
	public String toString() {
		String s = name+"\n";
		Iterator<VehiclePointData> it = vehicles.iterator();
		while(it.hasNext()) 
			s += "---"+it.next().toString()+"\n";
		return s;
	}

	public Integer getChecks() {
		return checks;
	}

	public void setChecks(Integer checks) {
		this.checks = checks;
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

	
}
