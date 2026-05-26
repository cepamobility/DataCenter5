package communication.driver.tables;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import communication.administration.corporation.tables.PointAccountData;
import communication.administration.corporation.tables.PointMoveData;

public class DriverPointList {

	private Integer total;
	
	private Collection<Object[]> black;
	private Collection<Object[]> red;
	private Collection<Object[]> yellow;
	private Collection<Object[]> green;
	
	public DriverPointList() {
		total = 0;
		black = new LinkedList<Object[]>();
		red = new LinkedList<Object[]>();
		yellow = new LinkedList<Object[]>();
		green = new LinkedList<Object[]>();
	}

	public Integer getTotal() {
		return total;
	}

	public Collection<Object[]> getBlack() {
		return black;
	}

	public Collection<Object[]> getRed() {
		return red;
	}

	public Collection<Object[]> getYellow() {
		return yellow;
	}

	public Collection<Object[]> getGreen() {
		return green;
	}

	public void addData(Object[] data, double amount) {
		if(amount >= 50) {
			black.add(data);
		} else if(amount >= 30) {
			red.add(data);
		} else if(amount >= 20) {
			yellow.add(data);
		} else {
			green.add(data);
		} 
		total++;
	}
	
	public int[] getPercentages() {
		int[] values = new int[4];
		values[0] = (int) Math.round(new Integer(black.size()).doubleValue() * 100.0 / total.doubleValue());
		values[1] = (int) Math.round(new Integer(red.size()).doubleValue() * 100.0 / total.doubleValue());
		values[2] = (int) Math.round(new Integer(yellow.size()).doubleValue() * 100.0 / total.doubleValue());
		values[3] = 100 - (values[0] + values[1] + values[2]);
		return values;
	}
	
}
