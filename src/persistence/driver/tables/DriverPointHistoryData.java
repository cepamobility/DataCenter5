package persistence.driver.tables;

import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.poi.hssf.util.HSSFColor;


public class DriverPointHistoryData {

	private String corporationId;
	private String firstName;
	private String lastName;
	
	private Integer[][] values;

	public DriverPointHistoryData(Object[] data, Hashtable<String, Integer> index) {
		corporationId = (String) data[1];
		firstName = (String) data[2];
		lastName = (String) data[3];
		
		values = new Integer[index.size()][2];
		addValue(data,index);
	}

	public void addValue(Object[] data, Hashtable<String, Integer> index) {
		String key = String.valueOf(data[4])+"-"+String.valueOf(data[5]); 
		if(index.containsKey(key)){
			int indexKey = index.get(key).intValue();
			values[indexKey][0] = new Integer(((Double)data[6]).intValue());
			values[indexKey][1] = (Integer)data[7];
		}
	}

	public ArrayList<Object> getLine() {
        ArrayList<Object> line=new ArrayList<Object>();
        line.add(corporationId);
        line.add(firstName);
        line.add(lastName);
        for(int i = 0; i < values.length; i++) {
        	if(values[i][0] == null) {
        		line.add("---");
        	}else {
        		Object[] dataValues = new Object[3];
        		dataValues[0] = String.valueOf(values[i][0]);
        		switch(values[i][1].intValue()) {
	        		case 0:
	        			dataValues[1] = new Short(new HSSFColor.GREEN().getIndex());
	        			dataValues[2] = new Short(new HSSFColor.WHITE().getIndex());
	        			break;
	        		case 1:
	        			dataValues[1] = new Short(new HSSFColor.YELLOW().getIndex());
	        			dataValues[2] = new Short(new HSSFColor.BLACK().getIndex());
	        			break;
	        		case 2:
	        			dataValues[1] = new Short(new HSSFColor.RED().getIndex());
	        			dataValues[2] = new Short(new HSSFColor.WHITE().getIndex());
	        			break;
	        		case 3:
	        			dataValues[1] = new Short(new HSSFColor.BLACK().getIndex());
	        			dataValues[2] = new Short(new HSSFColor.WHITE().getIndex());
	        			break;
        		}
        		line.add(dataValues);
        	}
        }
		return line;
	}
	
	
}
