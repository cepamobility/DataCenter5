package communication.datatype.tables;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import communication.driver.tables.DriverMainAttributes;

public class EcolabDriverData implements Comparable {
	
	private String name;
	
	private EcolabDriverMonthData[] months;
	private HashMap<Integer,EcolabVehiceData> vehicles;

	private double total = 0;
	
	public EcolabDriverData(DriverMainAttributes driver) {
		name = driver.getFirstName()+" "+driver.getLastName();
		months = new EcolabDriverMonthData[12];
		vehicles = new HashMap<Integer,EcolabVehiceData>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addOdometer(Object[] data, int year) {
/*Cambio ecolab	dic */	Integer month = (((Integer)data[4]).intValue() == year) ? ((Integer)data[3]).intValue()+1 : ((Integer)data[3]).intValue()-11;  
/*Cambio ecolab ene		Integer month = (((Integer)data[4]).intValue() == year) ? ((Integer)data[3]).intValue() : ((Integer)data[3]).intValue()-12;*/  
		if(vehicles.containsKey(data[1])) {
			vehicles.get(data[1]).addOdometer(month, (Integer)data[5]);
		} else {
			vehicles.put((Integer)data[1], new EcolabVehiceData((String)data[2], month, (Integer)data[5]));
		}
		
	}

	public Iterator<EcolabVehiceData> getVehicleIterator() {
		return vehicles.values().iterator();
	}

	public Iterator<Integer> getVehicleIdsIterator() {
		return vehicles.keySet().iterator();
	}

	public void setVehicles(HashMap<Integer, EcolabVehiceData> vehicles) {
		this.vehicles = vehicles;
	}

	public int getVehicleCount() {
		return vehicles.size();
	}

	public void addFine(String type, String qualify, Integer month, Date date, Time time,  String plate) {
/*Cambio ecolab	dic*/	int index = month.intValue() % 12;
/*cambio ecolab ene		int index = month.intValue()-1; */
		if(months[index] == null)
			months[index] = new EcolabDriverMonthData(month);
		months[index].addFine(name,type,qualify,date,time,plate);
	}

	public String getPointLine(int month) {
		String line = "<div id='columnaPM' style='color:black; '>"+name+"</div>";
		for(int i = 0; i < 12; i++) {
			if(i <= month) {
				double points = (months[i] == null) ? 100 : months[i].getPoints();
				total += points;
				line += "&nbsp;<div id='columnaPM'  style='color:black; background-color:"+getColor(points)+";'>"+points+"</div>";
			}else {
				line += "&nbsp;<div id='columnaPM'></div>";
			}
		}
		total = total / (month+1);
		String txt = (total == 0) ? "" : String.valueOf(Math.round(total*100.0)/100.0); 
		line += "&nbsp;<div id='columnaPM' style='color:black; background-color:"+getColor(total)+";'>"+txt+"</div>";
		return line;
	}

	public double getPoint(Integer month) {
		return (months[month] == null) ? 0.0 : months[month].getDiscountPoints();
	}
	public String getColor(double points) {
		if(points < 50)
			return "red";
		else if(points < 75)
			return "yellow";
		else 
			return "#00CC00";
	}
	
	public void addSpeeds(Integer month, Integer count) {
/*Cambio ecolab dic*/					int index = month.intValue() % 12;
/*cambio ecolab ene		int index = month.intValue()-1; */
		if(months[index] == null)
			months[index] = new EcolabDriverMonthData(month);
		months[index].addSpeeds(count);
	}

	public double getPoints(int month) {
		return (months[month] == null) ? 100.0 : months[month].getPoints();
	}

	public EcolabDriverMonthData getMonth(int month) {
		return months[month];
	}

	public void addVehicleWO_Odometer(Object[] data) {
		vehicles.put((Integer)data[1], new EcolabVehiceData((String)data[2]));
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public int compareTo(Object o) {
		return name.compareTo(((EcolabDriverData)o).name);
	}

	public void addEvitableAccidents(Integer month, Integer count) {
		int index = month.intValue() % 12;
		if(months[index] == null)
			months[index] = new EcolabDriverMonthData(month);
		months[index].addEvitableAccidents(count);
	}

	public Collection<EcolabFineData> getFines(int month) {
		if(months[month] != null) 
			return months[month].getFineCollection(); 
		return new LinkedList<EcolabFineData>();
	}

	public EcolabVehiceData getVehicle(Integer vehicleId) {
		return vehicles.get(vehicleId);
	}

	
}
