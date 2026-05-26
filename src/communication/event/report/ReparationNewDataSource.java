package communication.event.report;


import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import communication.administration.corporation.util.Util;
import communication.alert.AlertConstants;
import communication.event.tables.ReparationData;
import communication.event.tables.ReparationItemData;
import communication.language.Text;

public class ReparationNewDataSource implements JRDataSource {

    private String[][] items;
    private int index = -1;
	private Text messages;
	private int total;
    
    public ReparationNewDataSource(ReparationData reparation, int type, Text messages) {
    	this.messages = messages;
    	int min = 0;
    	int max = 100;
    	switch(type) {
	    	case ReparationData.TALLER:
	    		min = 20;
	    		max = 30;
	    		break;
	    	case ReparationData.GOMERIA:
	    		min = 30;
	    		max = 40;
	    		break;
    	}
    	Collection<ReparationItemData> itemList = reparation.getItems();
    	Collections.sort((List)itemList);
    	Iterator<ReparationItemData> it = itemList.iterator();
    	int i = 0;
    	items = new String[reparation.getItems().size()][5];
    	while(it.hasNext()) {
    		ReparationItemData itemData = it.next();
    		int v = itemData.getMaintenance1();
    		if(v > min && v < max) {
	    		items[i][0] = String.valueOf(v / 10); 
	    		items[i][1] = String.valueOf(v % 10);
	    		items[i][2] = String.valueOf(itemData.getMaintenance2());
				items[i][3] = messages.getText("reparation.item.status"+itemData.getStatus().intValue());
	    		if(itemData.getStatus().intValue() != ReparationItemData.PENDING) {
	    			items[i][3] += " - "+Util.formatDate(itemData.getDate())+" - "+itemData.getObs(); 
	    		}
	    		items[i][4] = reparation.getComment(Integer.parseInt(items[i][0]));
	    		i++;
    		}
    	}
    	total = i;
    }
    
    public boolean next() throws JRException {
        if(index+1 < total) {
        	index++;
        	return true;
        }
        return false;
    }

    public Object getFieldValue(JRField field) throws JRException {
    	if(field.getName().equals("item")) {
    		return "- "+messages.getText("generic.reparation.maintenance_"+items[index][0]+"_"+items[index][1]+"_"+items[index][2]);
    	}
    	if(field.getName().equals("category1")) {
    		return messages.getText("generic.reparation.maintenancegroup"+items[index][0]);
    	}
    	if(field.getName().equals("category2")) {
    		return messages.getText("generic.reparation.maintenance_"+items[index][0]+"_"+items[index][1]);
    	}
    	if(field.getName().equals("comment")) {
    		return (items[index][4] == null) ? "" : items[index][4];
    	}
    	if(field.getName().equals("itemState")) {
    		return items[index][3];
    	}
    	if(field.getName().startsWith("table")) {
    		if(index % 2 == 0) {
    			return AlertConstants.TEMP_DIRECTORY_REPARATIONS_FRAYLOG+"back1.jpg";
    		}else {
    			return AlertConstants.TEMP_DIRECTORY_REPARATIONS_FRAYLOG+"back2.jpg";
    		}
    	}
    	return null;
    }


    private String formatDate(Date date) {
        if(date == null) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);
    }
}
