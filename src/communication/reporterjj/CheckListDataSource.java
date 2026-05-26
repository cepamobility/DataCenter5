package communication.reporterjj;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import business.SystemAccess;

import communication.activity.tables.CheckListData;
import communication.activity.tables.ItemData;
import communication.activity.tables.PointData;
import communication.alert.AlertConstants;
import communication.language.Text;
import communication.vehicle.tables.VehicleCheckData;

public class CheckListDataSource implements JRDataSource {

    private VehicleCheckData value;
    private Object[][] points;
    private int index = -1;
	private Text messages;
	private int corporationId;
    
    public CheckListDataSource(VehicleCheckData value, CheckListData checkList, SystemAccess sys) {
    	this.value = value;
    	this.messages = sys.getText();
    	this.corporationId = sys.getCorporationId();
    	Collection<Object[]> list = new LinkedList<Object[]>(); 
    	Iterator<PointData> it = checkList.getSortPoints();
    	while(it.hasNext()) {
			PointData point = (PointData)it.next();
			Iterator items = point.getSortItems();
			while(items.hasNext()) {
				ItemData item = (ItemData)items.next();
				list.add(new Object[]{point.getId(),point.getText(),item});
			}
		}
    	Iterator<Object[]> it2 = list.iterator();
    	points = new Object[list.size()][3];
    	int in = 0;
    	while(it2.hasNext()) {
    		points[in] = it2.next();
    		in++;
    	}
    }
    
    public boolean next() throws JRException {
        if(index+1 < points.length) {
        	index++;
        	return true;
        }
        return false;
    }

    public Object getFieldValue(JRField field) throws JRException {
    	if(field.getName().equals("table1")) {
    		if(index % 2 == 0) 
    			return AlertConstants.getTEMP(corporationId)+"images/tabla1.jpg";
    		else
    			return AlertConstants.getTEMP(corporationId)+"images/tabla3.jpg";
    	}
    	if(field.getName().equals("table2")) {
    		if(index % 2 == 0) 
    			return AlertConstants.getTEMP(corporationId)+"images/tabla2.jpg";
    		else
    			return AlertConstants.getTEMP(corporationId)+"images/tabla4.jpg";
    	}
    	if(field.getName().equals("point")) {
    		return points[index][0];
    	}
    	if(field.getName().equals("pointText")) {
    		return messages.getText((String) points[index][1]);
    	}
    	if(field.getName().equals("item")) {
    		ItemData item = (ItemData) points[index][2];
    		return messages.getText(item.getText());
    	}
    	if(field.getName().equals("value")) {
    		ItemData item = (ItemData) points[index][2];
			String text="";	
			String valueStr = value.getValueFromItem(item.getId());
			if (valueStr  != null) {
				if (valueStr.contains("1")) {
					text += messages.getText("generic.vehiclecheck.form.correct");
				}
				if (valueStr.contains("2")) {
					if (text.equals("")) {
						text += messages.getText("generic.vehiclecheck.form.withoutclean");
					}
					else {
						text += "; " + messages.getText("generic.vehiclecheck.form.withoutclean");
					}
				}
				if (valueStr.contains("3")) {
					if (text.equals("")) {
						text += messages.getText("generic.vehiclecheck.form.conmarcas");
					}
					else {
						text += "; " + messages.getText("generic.vehiclecheck.form.conmarcas");
					}	
				}			
				if (valueStr.contains("4")) {
					if (text.equals("")) {
						text += messages.getText("generic.vehiclecheck.form.noposee");
					}
					else {
						text += "; " + messages.getText("generic.vehiclecheck.form.noposee");
					}	
				}							
			}	
			if (text.equals("")) {
				text = messages.getText("generic.vehiclecheck.form.nodata");
			}
	    	return text;
    	}
    	return null;
    }
}
