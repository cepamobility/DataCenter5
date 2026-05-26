package communication.datatype.tables;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;

public class EcolabVehiceData {

	private String plate;
	private HashMap<Integer, Integer> odometers;
	private boolean reportOdometers;
	private boolean instaled;
	
	public EcolabVehiceData(String plate, Integer month, Integer odometer) {
		this.plate = plate;
		odometers = new HashMap<Integer, Integer>();
		odometers.put(month, odometer);
		reportOdometers = true;
		instaled = false;
	}

	public EcolabVehiceData(String plate) {
		this.plate = plate;
		reportOdometers = false; 
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public void addOdometer(Integer month, Integer odometer) {
		odometers.put(month, odometer);
		
	}

	public String getOdometers(Integer month) {
		NumberFormat formatter = NumberFormat.getInstance(new Locale("pt_BR"));
		String line = "";
		int total = 0;
		int last = 0;
		if(reportOdometers) {
			last = (odometers.containsKey(0)) ? odometers.get(0).intValue() : 0;
		}
		for(int i = 1; i <= 12; i++) {
			if(reportOdometers) {
				if(i <= month+1) {
					String column = String.valueOf(i+1);
					String bgColor = "#00CC00";
					int value = 0;
					if(odometers.containsKey(new Integer(i))) {
						if(last == -1) {
							line += "&nbsp;<div id='columnaPM'>-</div>";
						}else {
							if(odometers.get(new Integer(i)).intValue() == last) {
								bgColor = "red";
							} else if(odometers.get(new Integer(i)).intValue() == 0) {
								bgColor = "white";
							} else {
								value = odometers.get(new Integer(i)) - last;
								total += value;
								if(value < 0 || value > 10000) {
									bgColor = "orange";
								}
							}
							line += "&nbsp;<div id='columnaPM' style='background-color:"+bgColor+";'>"+formatter.format(new Integer(value).longValue()).replaceAll(",",".")+"</div>";
												}
						last = odometers.get(new Integer(i)).intValue();
					} else {
						line += "&nbsp;<div id='columnaPM'>-</div>";
						last = -1;
					}
				}else {
					line += "&nbsp;<div id='columnaPM'>-</div>";
					last = -1;
				}
			}else {
				line += "&nbsp;<div id='columnaPM'>-</div>";
				last = -1;
			}
		}
		line += "&nbsp;<div id='columnaPM'>"+formatter.format(new Integer(total).longValue()).replaceAll(",",".")+"</div>";
		return line;
	}

	public Integer getOdometerValue(Integer month) {
		Integer last = new Integer(month.intValue()-1);
		if(odometers.containsKey(month)) {
			int lastValue = (odometers.containsKey(last)) ? odometers.get(last).intValue() : 0;
			return new Integer(odometers.get(month).intValue() - lastValue);
		}
		return null;
	}

	public boolean isReportOdometers() {
		return reportOdometers;
	}

	public void setReportOdometers(boolean reportOdometers) {
		this.reportOdometers = reportOdometers;
	}

	public boolean isInstaled() {
		return instaled;
	}

	public void setInstaled(boolean instaled) {
		this.instaled = instaled;
	}
	
	
}
