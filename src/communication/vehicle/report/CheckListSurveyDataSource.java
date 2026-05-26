package communication.vehicle.report;


import java.util.Iterator;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import communication.activity.tables.CheckListData;
import communication.activity.tables.ItemData;
import communication.activity.tables.PointData;
import communication.alert.AlertConstants;
import communication.language.Text;
import communication.vehicle.tables.VehicleCheckData;

public class CheckListSurveyDataSource implements JRDataSource {

    private VehicleCheckData value;
    private String[][] values;
    private int index = -1;
	private Text messages;
    
    public CheckListSurveyDataSource(VehicleCheckData value, CheckListData checkList, Text messages) {
    	this.value = value;
    	this.messages = messages;
    	values = new String[69][3];
    	Iterator<PointData> it = checkList.getSortPoints();
   		int in = 0;
    	while(it.hasNext()) {
   			PointData point = (PointData)it.next();
    		Iterator it2 = point.getSortItems();
        	while(it2.hasNext()) {
       			ItemData item = (ItemData)it2.next();
        		values[in][0] = messages.getText(point.getText());
        		values[in][1] = messages.getText(item.getText());
        		String itemValue = value.getValueFromItem(item.getId());
        		if(item.getType().intValue() == 3) {
        			values[in][2] = itemValue;
        		}else {
        			switch(Integer.parseInt(itemValue)) {
	        			case 1:
	        				values[in][2] = messages.getText("generic.messages.yes");
	        				break;
	        			case 2:
	        				values[in][2] = messages.getText("generic.messages.partially");
	        				break;
	        			default:
	        				values[in][2] = messages.getText("generic.messages.yes");
	        				break;
        			}
        		}
       			in++;
        	}
    	}
   	}
    
    public boolean next() throws JRException {
        if(index+1 < values.length) {
        	index++;
        	return true;
        }
        return false;
    }

    public Object getFieldValue(JRField field) throws JRException {
    	if(field.getName().equals("point")) {
      		return values[index][0];
    	}
    	if(field.getName().equals("item")) {
      		return values[index][1];
    	}
    	if(field.getName().equals("value")) {
      		return values[index][2];
    	}
    	if(field.getName().equals("table1")) {
    		if(index % 2 == 0) {
    			return AlertConstants.TEMP_DIRECTORY_JASPERREPORT+"images/checkline1_"+field.getName()+".jpg";
    		}else {
    			return AlertConstants.TEMP_DIRECTORY_JASPERREPORT+"images/checkline2_"+field.getName()+".jpg";
    		}
    	}
    	if(field.getName().startsWith("table")) {
    		if(index % 2 == 0) {
    			return AlertConstants.TEMP_DIRECTORY_JASPERREPORT+"images/checkline1_"+field.getName()+".jpg";
    		}else {
    			return AlertConstants.TEMP_DIRECTORY_JASPERREPORT+"images/checkline2_"+field.getName()+".jpg";
    		}
    	}
    	return null;
    }

}
