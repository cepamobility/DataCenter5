package communication.datatype.tables;

import java.sql.Time;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import communication.driver.tables.DriverMainAttributes;
import communication.driver.tables.TelematicControlData;
import communication.language.Text;

public class EcolabReportData {

	private Integer dpt;
	private String dptName;
	
	private HashMap<Integer,EcolabDriverData> drivers;
	private Collection<TelematicControlData> speeds;
	private Collection<TelematicControlData> nigths;
	
	private Integer[] fines;

	private int monthDriverFines = 0;
	private int yearDriverFines = 0;
	
	private int[] fineQualifies = {0,0,0,0,0};
	
	public EcolabReportData(Integer dpt) {
		this.dpt = dpt;
		drivers = new HashMap<Integer,EcolabDriverData>();
		speeds = new LinkedList<TelematicControlData>();
		nigths = new LinkedList<TelematicControlData>();
		fines = new Integer[12];
		for(int i = 0; i < 12; i++)
			fines[i] = 0;
	}

	public void setDrivers(Collection<DriverMainAttributes> findDriversByDivisions) {
		Iterator<DriverMainAttributes> it = findDriversByDivisions.iterator();
		while(it.hasNext()) {
			DriverMainAttributes driver = it.next();
			drivers.put(driver.getId(), new EcolabDriverData(driver));
		}
	}

	public Integer getDpt() {
		return dpt;
	}

	public void setDpt(Integer dpt) {
		this.dpt = dpt;
	}

	public String getDptName() {
		return dptName;
	}

	public void setDptName(String dptName) {
		this.dptName = dptName;
	}

	public HashMap<Integer, EcolabDriverData> getDrivers() {
		return drivers;
	}

	public void setDrivers(HashMap<Integer, EcolabDriverData> drivers) {
		this.drivers = drivers;
	}

	public void setTelematicControls(Collection<TelematicControlData> telematicControls) {
		Iterator<TelematicControlData> it = telematicControls.iterator();
		while(it.hasNext()) {
			TelematicControlData tc = it.next();
			if(tc.getType() == TelematicControlData.TYPE_EXCESSIVE_SPEED) {
				speeds.add(tc);
			}else {
				nigths.add(tc);
			}
		}
	}

	public Collection<TelematicControlData> getSpeeds() {
		return speeds;
	}

	public void setSpeeds(Collection<TelematicControlData> speeds) {
		this.speeds = speeds;
	}

	public Collection<TelematicControlData> getNigths() {
		return nigths;
	}

	public void setNigths(Collection<TelematicControlData> nigths) {
		this.nigths = nigths;
	}

	public void setOdometers(Collection odometers, int year) {
		Iterator it = odometers.iterator();
		while(it.hasNext()) {
			Object[] data = (Object[]) it.next();
			if(drivers.containsKey(data[0]))
				drivers.get(data[0]).addOdometer(data,year);
		}
	}

	public String getOdometers(Integer month) {
		String line = "";
		Iterator<EcolabDriverData> it = getDriverIterator();
		while(it.hasNext()) {
			EcolabDriverData driverData = it.next();
			Iterator<EcolabVehiceData> it2 = driverData.getVehicleIterator();
			while(it2.hasNext()) {
				line += "<br><div id='contenidos'>";
				EcolabVehiceData vehicleData = it2.next();
				line += "<div id='columnaPM'>"+vehicleData.getPlate()+"</div>&nbsp;";
				line += "<div id='columnaPM'>"+driverData.getName()+"</div>&nbsp;";
				line += vehicleData.getOdometers(month);
				line += "</div>";
			}
		}
		return line;
	}
	
	public String getParametricMonth(int month) {
		String line = "";

		NumberFormat formatter = NumberFormat.getInstance(new Locale("pt_BR"));
		Iterator<EcolabDriverData> it = getDriverIterator();
		while(it.hasNext()) {
			EcolabDriverData driverData = it.next();
			Iterator<EcolabVehiceData> it2 = driverData.getVehicleIterator();
			while(it2.hasNext()) {
				line += "<br><div id='contenidos'>";
				EcolabVehiceData vehicleData = it2.next();
				EcolabDriverMonthData monthData = driverData.getMonth(month);
				line += "<div id='columnaPM'>"+driverData.getName()+"</div>&nbsp;";
				line += "<div id='columnaPM'>"+vehicleData.getPlate()+"</div>&nbsp;";
				if(vehicleData.isReportOdometers()) {
					Integer odometer = vehicleData.getOdometerValue(new Integer(month));
					if(odometer != null)
						line += "<div id='columnaPM'>"+formatter.format(odometer.longValue()).replaceAll(",",".")+"</div>&nbsp;";
					else{
						line += "<div id='columnaPM'>0</div>&nbsp;";
					}
				}else {
					line += "<div id='columnaPM'>-</div>&nbsp;";
				}
				double points = driverData.getPoint(new Integer(month));
				if(monthData != null) {
					Integer speeds = monthData.getSpeeds();
					if(speeds != null && speeds.intValue() > 0)
						line += "<div id='columnaPM'>"+speeds+"</div>&nbsp;";
					else
						line += "<div id='columnaPM'></div>&nbsp;";
					Integer fines = monthData.getFines();
					if(fines != null && fines.intValue() > 0)
						line += "<div id='columnaPM'>"+fines+"</div>&nbsp;";
					else
						line += "<div id='columnaPM'></div>&nbsp;";
					int sumSF = monthData.getSpeeds()+monthData.getFines();
					if(sumSF > 0)
						line += "<div id='columnaPM'>"+String.valueOf(sumSF) +"</div>&nbsp;";
					else
						line += "<div id='columnaPM'></div>&nbsp;";
					int accidents = monthData.getAccidents();
					if(accidents > 0)
						line += "<div id='columnaPM'>"+String.valueOf(accidents) +"</div>&nbsp;";
					else 
						line += "<div id='columnaPM'></div>&nbsp;";
					line += "<div id='columnaPM'>"+String.valueOf(points) +"</div>&nbsp;";
				} else {
					line += "<div id='columnaPM'></div>&nbsp;";
					line += "<div id='columnaPM'></div>&nbsp;";
					line += "<div id='columnaPM'></div>&nbsp;";
					line += "<div id='columnaPM'></div>&nbsp;";
					line += "<div id='columnaPM'></div>&nbsp;";
					
				}
				line += "<div id='columnaPM'>"+String.valueOf(points) +"</div>&nbsp;";
				line += "<div id='columnaPM' style='background-color:"+driverData.getColor(100-points)+";'>"+String.valueOf(100-points)+"</div>";
				line += "</div>";
			}
		}
		return line;
	}

	public String getParametricYear(int month) {
		String line = "";
		Iterator<EcolabDriverData> it = getDriverIterator();
		while(it.hasNext()) {
			EcolabDriverData driverData = it.next();
			line += "<br><div id='contenidos'>";
			line += driverData.getPointLine(month);
			line += "</div>";
		}
		return line;
	}
	
	private Iterator<EcolabDriverData> getDriverIterator() {
		List<EcolabDriverData> list = new LinkedList<EcolabDriverData>();
		list.addAll(drivers.values());
		Collections.sort(list);		
		return list.iterator();
	}

	public String[][] getFineSpeedLine(Text messages) {
		String[][] values = new String[12][6];
		for(int index = 0; index < 12; index++) {
/*Cambio ecolab	dic*/		int month = (index == 0) ? 12 :index;
/*Cambio ecolab	dic*/		values[index][0] = messages.getText("generic.month."+String.valueOf(month));
/*cambio ecolab ene			values[index][0] = messages.getText("generic.month."+String.valueOf(index+1));*/
			int totalSpeeds = 0;
			int totalFines = 0;
			int totalDriverSpeeds = 0;
			int totalDriverFines = 0;
			Iterator<EcolabDriverData> it = drivers.values().iterator();
			while(it.hasNext()) {
				EcolabDriverData driverData = it.next();
				EcolabDriverMonthData monthData = driverData.getMonth(index);
				if(monthData != null) {
					totalSpeeds += monthData.getSpeeds().intValue();
					totalFines += monthData.getFines().intValue();
					if(monthData.getSpeeds().intValue() > 0)
						totalDriverSpeeds++;
					if(monthData.getFines().intValue() > 0)
						totalDriverFines++;
				}
			}
			values[index][1] = String.valueOf(totalFines);
			values[index][2] = String.valueOf(totalSpeeds);
			values[index][3] = String.valueOf(totalFines+totalSpeeds);
			values[index][4] = String.valueOf(totalDriverFines);
			values[index][5] = String.valueOf(totalDriverSpeeds);
		}
		return values;
	}
	
	public int getVehicleCount() {
		int count = 0;
		Iterator<EcolabDriverData> it = drivers.values().iterator();
		while(it.hasNext()) {
			count += it.next().getVehicleCount();
		}
		return count;
	}

	public String getSpeedChart() {
		String chart = "[[\"Nome\", \"Total\", { role: \"style\" } ],";
		HashMap<String, Integer> values = new HashMap<String, Integer>();
		Iterator<TelematicControlData> it = speeds.iterator();
		while(it.hasNext()) {
			TelematicControlData tc = it.next();
			String name = tc.getDriver().getFirstName()+ " " + tc.getDriver().getLastName();
			if(values.containsKey(name)) {
				values.put(name, values.get(name) + 1); 
			}else {
				values.put(name, 1); 
			}
		}
		if(values.size() == 0) {
			return null;
		}
		Iterator<String> keys = values.keySet().iterator();
		while(keys.hasNext()) {
			String name = keys.next();
			chart += "[\""+name+"\", "+String.valueOf(values.get(name))+", \"#4572A7\"]";
			if(keys.hasNext())
				chart += ",";
		}
		chart += "]";
		return chart;
	}

	public String getParametricMonthChart(int month) {
		String chart = "[[\".\", \"Pontuação\", { role: \"style\" } ],";
		Iterator<EcolabDriverData> it = getDriverIterator();
		while(it.hasNext()) {
			EcolabDriverData driver = it.next();
			double points = driver.getPoints(month);
			String name = driver.getName();
			chart += "[\""+name+"\", "+String.valueOf(points)+", \""+driver.getColor(points)+"\"]";
			if(it.hasNext())
				chart += ",";
		}
		chart += "]";
		return chart;
	}
	
	public String getParametricYearChart() {
		String chart = "[[\"rrr\", \"Pontuação\", { role: \"style\" } ],";
		Iterator<EcolabDriverData> it = getDriverIterator();
		while(it.hasNext()) {
			EcolabDriverData driver = it.next();
			double points = driver.getTotal();
			String name = driver.getName();
			chart += "[\""+name+"\", "+String.valueOf(Math.round((points) * 100.0) / 100.0)+", \""+driver.getColor(points)+"\"]";
			if(it.hasNext())
				chart += ",";
		}
		chart += "]";
		return chart;
	}

	public int[] getDriverMonthPoints(int month) {
		int[] values = {0,0,0};
		Iterator<EcolabDriverData> it = drivers.values().iterator();
		while(it.hasNext()) {
			EcolabDriverData driver = it.next();
			double points = driver.getPoints(month);
			if(points < 50) {
				values[2]++;
			} else if(points < 75) {
				values[1]++;
			}else {
				values[0]++;
			}
		}
		return values;
	}

	public double[] getAveragePoints(int month) {
		double totalFull = 0.0;
		double ponderFull = 0.0;
		double[] values = new double[13];
		for(int i = 0;i < 12; i++) {
			if(i <= month) {
				double total = 0.0;
				double ponder = 0.0;
				Iterator<EcolabDriverData> it = drivers.values().iterator();
				while(it.hasNext()) {
					EcolabDriverData driver = it.next();
					double points = driver.getPoints(i);
					if(points < 50) {
						total += points * 0.3;
						ponder += 0.3;
					} else if(points < 75) {
						total += points * 0.2;
						ponder += 0.2;
					}else {
						total += points * 0.1;
						ponder += 0.1;
					}
				}
				values[i] = Math.round((total / ponder) * 100.0) / 100.0;
				totalFull += total;
				ponderFull += ponder;
			}else {
				values[i] = 0;
			}
		}
		values[12] = Math.round((totalFull / ponderFull) * 100.0) / 100.0;
		return values;
	}

	public void setFines(Collection ecolabFines, Integer period) {
		Iterator it = ecolabFines.iterator();
		while(it.hasNext()) {
			Object[] data = (Object[]) it.next();
/*Cambio ecolab	dic*/		fines[((Integer)data[3]).intValue() % 12] += 1;
/*Cambio ecolab ene			fines[((Integer)data[3]).intValue()-1] += 1;*/
			String plate = (data[6] != null && ((String)data[6]).length() > 0) ? (String) data[6] : (String) data[7];
			if(drivers.containsKey(data[0])) {
				drivers.get(data[0]).addFine((String)data[1],(String)data[2],(Integer)data[3],(Date)data[4],(Time)data[5],plate);
				if(data[1].equals("datatype.finetype.soft"))
					fineQualifies[0]++;
				else if(data[1].equals("datatype.finetype.half"))
					fineQualifies[1]++;
				else if(data[1].equals("datatype.finetype.serious"))
					fineQualifies[2]++;
				else if(data[1].equals("datatype.finetype.extserious"))
					fineQualifies[3]++;
				else if(data[1].equals("datatype.finetype.tolerance"))
					fineQualifies[4]++;
			}
		}
	}

	public void setDriverSpeeds(Collection ecolabSpeeds) {
		Iterator it = ecolabSpeeds.iterator();
		while(it.hasNext()) {
			Object[] data = (Object[]) it.next();
			if(drivers.containsKey(data[0])) {
				drivers.get(data[0]).addSpeeds((Integer)data[1],(Integer)data[2]);
			}
		}
	}

	public void setVehiclesWO_Odometers(Collection vehicleWO_Odometers) {
		Iterator it = vehicleWO_Odometers.iterator();
		while(it.hasNext()) {
			Object[] data = (Object[]) it.next();
			if(drivers.containsKey(data[0]))
				drivers.get(data[0]).addVehicleWO_Odometer(data);
		}
	}

	public Object[] getGPSVehicles(int month) {
		Object[] values = new Object[4];
		values[0] = new Integer(0);
		for(int i = 1; i < 4; i++) {
			values[i] = new LinkedList<String[]>();
		}
		Iterator<EcolabDriverData> it = getDriverIterator();
		while(it.hasNext()) {
			EcolabDriverData driverData = it.next();
			Iterator<EcolabVehiceData> it2 = driverData.getVehicleIterator();
			while(it2.hasNext()) {
				EcolabVehiceData vehicleData = it2.next();
				if(vehicleData.isReportOdometers()) {
					values[0] = new Integer(((Integer)values[0]).intValue()+1);
					Integer currentOdometer = vehicleData.getOdometerValue(month);
					Integer lastOdometer = vehicleData.getOdometerValue(month-1);
					if(lastOdometer == null && vehicleData.isInstaled()) {
						((Collection<String[]>)values[2]).add(new String[]{driverData.getName(),vehicleData.getPlate()});
					}else if(currentOdometer != null && currentOdometer.intValue() < 0) {
						((Collection<String[]>)values[3]).add(new String[]{driverData.getName(),vehicleData.getPlate()});
					}
				}else {
					((Collection<String[]>)values[1]).add(new String[]{driverData.getName(),vehicleData.getPlate()});
				}
			}
		}
		return values;
	}
	
	public String getInstaledList(int month) {
		String s = "0";
		Iterator<EcolabDriverData> it = getDriverIterator();
		while(it.hasNext()) {
			EcolabDriverData driverData = it.next();
			Iterator<Integer> it2 = driverData.getVehicleIdsIterator();
			while(it2.hasNext()) {
				Integer vehicleId = it2.next();
				EcolabVehiceData vehicleData = driverData.getVehicle(vehicleId);
				if(vehicleData.isReportOdometers()) {
					Integer lastOdometer = vehicleData.getOdometerValue(month-1);
					if(lastOdometer == null) {
						s += ","+vehicleId;
					}
				}
			}
		}
		return s;
	}
	
	public int[] totalPerformanceValues() {
		int[] values = {0,0,0};
		Iterator<EcolabDriverData> it = drivers.values().iterator();
		while(it.hasNext()) {
			int total = (int)it.next().getTotal();
			if(total < 50) {
				values[2]++;
			} else if(total < 75) {
				values[1]++;
			}else {
				values[0]++;
			}
		}		
		return values;
	}

	public void setEvitableAccidents(Collection ecolabEvitableAccidents, Integer month) {
		Iterator it = ecolabEvitableAccidents.iterator();
		while(it.hasNext()) {
			Object[] data = (Object[]) it.next();
			if(drivers.containsKey(data[0])) {
				drivers.get(data[0]).addEvitableAccidents((Integer)data[1],(Integer)data[2]);
			}
		}
	}

	public Collection<EcolabFineData> getFines(int month, boolean acumulated) {
		Collection<EcolabFineData> fines = new LinkedList<EcolabFineData>();
		Iterator it = getDriverIterator();
		while(it.hasNext()) {
			EcolabDriverData driver = (EcolabDriverData)it.next();
			for(int i = (acumulated) ? 0 : month; i <= month; i++) {
				fines.addAll(driver.getFines(i));
			}
		}
		return fines;
	}

	public String getFineChart(int month, boolean acumulated) {
		String chart = "[[\"rrr\", \"Multas\", { role: \"style\" } ],";
		Iterator it = getDriverIterator();
		while(it.hasNext()) {
			EcolabDriverData driver = (EcolabDriverData)it.next();
			int count = 0;
			for(int i = (acumulated) ? 0 : month; i <= month; i++) {
				count += driver.getFines(i).size();
			}
			if(count > 0) {
				if(acumulated) {
					yearDriverFines++;
				}else {
					monthDriverFines++;
				}
				chart += "[\""+driver.getName()+"\", "+String.valueOf(count)+", \"red\"]";
				if(it.hasNext())
					chart += ",";
			}
		}
		chart += "]";
		return chart;
	}

	public int getMonthDriverFines() {
		return monthDriverFines;
	}

	public void setMonthDriverFines(int monthDriverFines) {
		this.monthDriverFines = monthDriverFines;
	}

	public int getYearDriverFines() {
		return yearDriverFines;
	}

	public void setYearDriverFines(int yearDriverFines) {
		this.yearDriverFines = yearDriverFines;
	}

	public String getFineGravity(Text messages) {
		String chart = "[[\"rrr\", \"Multas\", { role: \"style\" } ],";
		chart += "[\""+messages.getText("datatype.finetype.soft")+"\", "+String.valueOf(fineQualifies[0])+", \"red\"],";
		chart += "[\""+messages.getText("datatype.finetype.half")+"\", "+String.valueOf(fineQualifies[1])+", \"red\"],";
		chart += "[\""+messages.getText("datatype.finetype.serious")+"\", "+String.valueOf(fineQualifies[2])+", \"red\"],";
		chart += "[\""+messages.getText("datatype.finetype.extserious")+"\", "+String.valueOf(fineQualifies[3])+", \"red\"],";
		chart += "[\""+messages.getText("datatype.finetype.tolerance")+"\", "+String.valueOf(fineQualifies[4])+", \"red\"]";
		chart += "]";
		return chart;
	}

	public void setInstaled(Collection installed) {
		Iterator it = installed.iterator();
		while(it.hasNext()) {
			Object[] data = (Object[]) it.next();
			if(drivers.containsKey(data[0])) {
				drivers.get(data[0]).getVehicle((Integer)data[1]).setInstaled(true);
			}
		}
	}

	public int getMultivehicles() {
		Iterator it = getDriverIterator();
		int multivehicles = 0;
		while(it.hasNext()) {
			EcolabDriverData driver = (EcolabDriverData)it.next();
			if(driver.getVehicleCount() > 1)
				multivehicles++;
		}
		return multivehicles;
	}
}
