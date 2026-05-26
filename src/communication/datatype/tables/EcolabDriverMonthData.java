package communication.datatype.tables;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

public class EcolabDriverMonthData {

	private Integer month;
	
	private Integer speeds;
	private Integer night;
	private Collection<EcolabFineData> fines;
	
	private boolean tolerance;
	private boolean extserious;
	private Integer accident;

	public EcolabDriverMonthData(Integer month) {
		this.month = month;
		speeds = 0;
		night = 0;
		fines = new LinkedList<EcolabFineData>();
		accident = 0;
		
		tolerance = false;
		extserious = false;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getSpeeds() {
		if(speeds == null)
			speeds = new Integer(0);
		return speeds;
	}

	public void setSpeeds(Integer speeds) {
		this.speeds = speeds;
	}

	public Integer getNight() {
		if(night == null)
			night = new Integer(0);
		return night;
	}

	public void setNight(Integer night) {
		this.night = night;
	}

	public Integer getFines() {
		if(fines == null)
			fines = new LinkedList<EcolabFineData>();
		return fines.size();
	}

	public boolean isTolerance() {
		return tolerance;
	}

	public void setTolerance(boolean tolerance) {
		this.tolerance = tolerance;
	}

	public void addFine(String name,String type, String qualify, Date date, Time time, String plate) {
		if(type.equals("datatype.finetype.extserious")) extserious = true;
		if(type.equals("datatype.finetype.tolerance")) tolerance = true;
		fines.add(new EcolabFineData(name,type, qualify, date, time, plate));
	}

	public void setEvitableAccident(Integer b) {
		accident = b;		
	}

	public double getPoints() {
		double points = 100;
		if(tolerance)
			points -= 45;
		if(extserious)
			points -= 25;
		if(accident != null && accident > 0)
			points -= 30;
		int sum = fines.size() + speeds.intValue();
		if(sum <= 5) {
			points -= sum * 5;
		} else if(sum >= 17) {
			points = 10;
		} else {
			points -= sum * 50.0 / 9.0;
		}
		if(points < 10)
			points = 10;
		return  Math.round(points*100.0)/100.0;
	}

	public void addSpeeds(Integer count) {
		speeds += count;
	}

	public double getDiscountPoints() {
		int sum = fines.size() + speeds.intValue();
		double points = 0;
		if(sum <= 5) {
			points = sum * 5;
		} else if(sum >= 17) {
			points = 90;
		} else {
			points = sum * 50.0 / 9.0;
		}
		if(tolerance)
			points += 45;
		if(extserious)
			points += 25;
		if(accident != null && accident > 0)
			points += 30;
		if(points > 90)
			points = 90;
		return  Math.round(points*100.0)/100.0;
	}

	public Integer getAccidents() {
		return accident;
	}

	public void addEvitableAccidents(Integer count) {
		accident += count;
		
	}

	public Collection<EcolabFineData> getFineCollection() {
		return fines;
	}
}
