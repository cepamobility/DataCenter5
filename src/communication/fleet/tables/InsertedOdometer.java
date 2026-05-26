package communication.fleet.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import persistence.util.Util;

import communication.language.Text;

public class InsertedOdometer {

	public Integer vehicleId;
	public String plate;
	public String driver;
	
	public String div1;
	public String div2;
	public String div3;
	public String div4;

	public HashMap<String, Integer> odometers = new HashMap<String, Integer>();
	
	public InsertedOdometer(Object[] data) {
		vehicleId = (Integer) data[0];
		plate = (String) data[1];
		driver = data[2] + " " + data[3];
		putOdometer((Integer) data[4], (Integer) data[5], (Integer) data[6]);
		div1 = (String) data[7];
		if(data.length > 8) 
			div2 = (String) data[8];
		if(data.length > 9) 
			div3 = (String) data[9];
		if(data.length > 10) 
			div4 = (String) data[10];
	}
	
	public void putOdometer(Integer period, Integer year, Integer odometer) {
		odometers.put(period+"-"+year, odometer);
	}

	public ArrayList<Object> getLine(Text messages, HashMap<Integer, String> months, HashMap<String, Object[]> logs) {
		ArrayList<Object> line = new ArrayList<Object>();
		line.add(plate);
		line.add(driver);
		line.add(div1);
		if(div2 != null)
			line.add(div2);
		if(div3 != null)
			line.add(div3);
		if(div4 != null)
			line.add(div4);
		Integer[] kms = new Integer[months.size()-1];
		Integer last = null;
		for(int i = 1; i <= months.size(); i++) {
			String key = months.get(i);
			Integer current = (odometers.containsKey(key)) ? odometers.get(key) : null;
			if(current == null) {
				line.add("---");
				line.add("---");
				line.add("---");
			}else {
				line.add(String.valueOf(current));
				String key2 = vehicleId + "-" + key;
				if(logs != null && logs.containsKey(key2)) {
					Object[] value = logs.get(key2);
					line.add(Util.formatDate((Date)value[0]));
					if(value[1] == null) {
						line.add("---");
					}else {
						if(((String)value[1]).startsWith("query.insertedodometers.")) {
							line.add(messages.getText((String)value[1]));
						}else {
							line.add((String)value[1]);
						}
					}
				}else {
					line.add("---");
					line.add("---");
				}
			}
			if(current != null && last != null) {
				kms[i-2] = current.intValue() - last.intValue();
			}
			last = current;
		}
		for(int i = 0; i < kms.length; i++) {
			if(kms[i] == null) {
				line.add("---");
			}else {
				line.add(String.valueOf(kms[i]));
			}
		}
		return line;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	
}
