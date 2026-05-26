package communication.fleet.tables;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import communication.administration.corporation.util.Util;

public class FleetBayerData {
	
	private Integer id;
	private String key;
	
	private int level;
	
	private int vehicles;
	private int miles;
	private int accidents;
	
	private int[][] ranges;
	
	private HashMap<Integer, FleetBayerData> values;

	public FleetBayerData() {
		values = new HashMap<Integer, FleetBayerData>();
		vehicles = new Integer(0);
		miles = new Integer(0);
		accidents = new Integer(0);
		ranges = new int[4][3];
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 3; j++)
				ranges[i][j] = 0; 
	}
	
	public void addData(Object[] data) {
		level = 0;
		vehicles += ((Integer)data[5]).intValue(); 
		miles += ((Integer)data[6]).intValue(); 
		accidents += ((Integer)data[7]).intValue();
		Integer subId = (Integer)data[8];
		if(!values.containsKey(subId)) {
			values.put(subId, new FleetBayerData());
		}
		values.get(subId).addData(data, 8, level+1);
	}

	public void addRangeData(Object[] data) {
		level = 0;
		int index = ((Integer)data[6]).intValue(); 
		ranges[index][0] += ((Integer)data[7]).intValue(); 
		Integer subId = (Integer)data[8];
		if(!values.containsKey(subId)) {
			values.put(subId, new FleetBayerData());
		}
		values.get(subId).addRangeData(data, 8, level+1);
	}

	public void addData(Object[] data, int index, int level) {
		this.level = level;
		id = (Integer)data[index];
		key = (String)data[index-8];
		vehicles += ((Integer)data[6]).intValue(); 
		miles += ((Integer)data[7]).intValue(); 
		accidents += ((Integer)data[8]).intValue();
		index++;
		if(index < data.length && !data[index-8].equals("N/A")) {
			Integer subId = (Integer)data[index];
			if(!values.containsKey(subId)) {
				values.put(subId, new FleetBayerData());
			}
			values.get(subId).addData(data, index, level+1);
		}
	}

	public void addRangeData(Object[] data, int index, int level) {
		this.level = level;
		id = (Integer)data[index];
		key = (String)data[index-8];
		int indexRange = ((Integer)data[6]).intValue(); 
		ranges[indexRange][0] += ((Integer)data[7]).intValue(); 
		index++;
		if(index < data.length && !data[index-8].equals("N/A")) {
			Integer subId = (Integer)data[index];
			if(!values.containsKey(subId)) {
				values.put(subId, new FleetBayerData());
			}
			values.get(subId).addRangeData(data, index, level+1);
		}
	}

	public void addMitigationPlanData(Object[] data, int index, int level) {
		this.level = level;
		id = (Integer)data[index];
		int indexRange = ((Integer)data[6]).intValue(); 
		if(((Boolean)data[8]).booleanValue())
			ranges[indexRange][1]+= ((Integer)data[7]).intValue(); 
		ranges[indexRange][2] += ((Integer)data[7]).intValue(); 
		index++;
		if(index < 6) {
			if(values.containsKey(id)) {
				values.get(id).addMitigationPlanData(data, index, level+1);
			}
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getVehicles() {
		return vehicles;
	}

	public void setVehicles(int vehicles) {
		this.vehicles = vehicles;
	}

	public int getMiles() {
		return miles;
	}

	public void setMiles(int miles) {
		this.miles = miles;
	}

	public int getAccidents() {
		return accidents;
	}

	public void setAccidents(int accidents) {
		this.accidents = accidents;
	}

	public Collection<FleetBayerData> getValues() {
		return values.values();
	}

	public void setValues(HashMap<Integer, FleetBayerData> values) {
		this.values = values;
	}
	
	public String getLine(String lastId, String lastKey) {
		double kms = Math.round(new Integer(miles).doubleValue() * 1.609344);
		String display = (level > 1) ? "style='display:none;'" : "style='display:block;'";
		String s = "";
		if(level < 2) {
			s += "<div class='bayerLine'></div>";
		}
		if(id == null) {
			s += "<div class='bayerReport"+level+"' id='nivel_0' "+display+">";
		}else {
			s += "<div class='bayerReport"+level+"' id='nivel"+lastId+"_"+id+"' "+display+">";
		}
		if(key == null) {
			s += "	<div class='colName'>Total</div>";			
		} else { 
			if(getValues().size() > 0) {
				s += "	<div class='colName'>" +
					"		<a href='javascript:mostrar(\""+lastId+"_"+id+"\");'>" + level + " - " + lastKey+"</a>"+
					"	</div>";
			}else {
				s += "	<div class='colName'>" + level + " - " + lastKey + "</div>";
			}
		}
		String s1 = (miles == 0) ? "0" : Util.formatDouble(new Double(new Integer(accidents).doubleValue() * 1000000 / new Integer(miles).doubleValue()),2);
		String s2 = (vehicles == 0) ? "0" : Util.formatDouble(new Double(new Integer(accidents).doubleValue() * 100 / new Integer(vehicles).doubleValue()),2);
		String s3 = (miles == 0) ? "0" : Util.formatDouble(new Double(Math.round(kms / new Integer(miles).doubleValue())),2);
		s += "	<div class='colValue'>"+vehicles+"</div>"+
				"	<div class='colValue2'>"+miles+"</div>"+
				"	<div class='colValue'>"+accidents+"</div>"+
				"	<div class='colValue3'>"+s1+"</div>"+
				"	<div class='colValue3'>"+s2+"</div>"+
				"	<div class='colValue3'>"+s3+"</div>"+
				"</div>";
		if(id == null) {
			s += "<input type='hidden' name='val_0' value='0' />";
		}else {
			s += "<input type='hidden' name='val"+lastId+"_"+id+"' value='0' />";
		}
		return s;
	}
	
	public String getRangeLine(String lastId, String lastKey) {
		String display = (level > 1) ? "style='display:none;'" : "style='display:block;'";
		String s = "";
		if(level < 2) {
			s += "<div class='bayerLine'></div>";
		}
		if(id == null) {
			s += "<div class='bayerReport"+level+"' id='nivel_0' "+display+">";
		}else {
			s += "<div class='bayerReport"+level+"' id='nivel"+lastId+"_0' "+display+">";
		}
		if(key == null) {
			s += "	<div class='colName'>Total</div>";			
		} else { 
			if(getValues().size() > 0) {
				s += "	<div class='colName'>" +
					"		<a href='javascript:mostrar(\""+lastId+"_"+id+"\");'>" + level + " - " + lastKey+"</a>"+
					"	</div>";
			}else {
				s += "	<div class='colName'>" + level + " - " + lastKey + "</div>";
			}
		}
		int totalLine1 = ranges[0][0]+ranges[1][0]+ranges[2][0]+ranges[3][0];
		int totalLine2 = ranges[0][1]+ranges[1][1]+ranges[2][1]+ranges[3][1];
		int totalLine3 = ranges[0][2]+ranges[1][2]+ranges[2][2]+ranges[3][2];
		for(int i = 0; i < 4; i++) {
			s += "	<div class='colValue3'>"+ranges[i][0]+" - "+getAvg(ranges[i][0], totalLine1)+" ("+getAvg(ranges[i][1],ranges[i][2])+")</div>";			
		}
		s += "	<div class='colValue3'>"+String.valueOf(totalLine1)+"  ("+getAvg(totalLine2,totalLine3)+")</div>"+
				"</div>";
		if(id == null) {
			s += "<input type='hidden' name='val_0' value='0' />";
		}else {
			s += "<input type='hidden' name='val"+lastId+"_"+id+"' value='0' />";
		}
		return s;
	}

	private String getAvg(int value, int total) {
		int avg = 0;
		if(total > 0) {
			avg = new Integer(String.valueOf(Math.round(new Integer(value).doubleValue() * 100.0 / new Integer(total).doubleValue()))).intValue();
		}
		return avg+"%";
	}

	public Collection<String> getLines(String lastId, String lastKey) {
		Collection<String> lines = new LinkedList<String>();
		if(key != null) {
			if(lastKey.length() > 0) 
				lastKey += " - ";
			lastKey += key;
		}
		lines.add(getLine(lastId,lastKey));
		Iterator<FleetBayerData> it = getValues().iterator();
		while(it.hasNext()) {
			if(id == null) {
				lines.addAll(it.next().getLines("",lastKey));
			}else {
				lines.addAll(it.next().getLines(lastId+"_"+id,lastKey));
			}
		}
		return lines;
	}
	
	public Collection<String> getRangeLines(String lastId, String lastKey) {
		Collection<String> lines = new LinkedList<String>();
		if(key != null) {
			if(lastKey.length() > 0) 
				lastKey += " - ";
			lastKey += key;
		}
		lines.add(getRangeLine(lastId,lastKey));
		Iterator<FleetBayerData> it = getValues().iterator();
		while(it.hasNext()) {
			if(id == null) {
				lines.addAll(it.next().getRangeLines("",lastKey));
			}else {
				lines.addAll(it.next().getRangeLines(lastId+"_"+id,lastKey));
			}
		}
		return lines;
	}
}
