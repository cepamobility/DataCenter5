package communication.driver.report;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import communication.language.Text;
import communication.util.ListResult;

public class CombinedReportData extends AbstractTableModel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total = 0;
    private int cantReg = 0;
    private Object[][] attributes;
    private Text messages;
    private boolean color = true;
    
    // private Integer lastId = new Integer(0);

    public CombinedReportData() {
        total = 0;
        attributes = new Object[0][0];
        cantReg = 0;
    }
    
    public CombinedReportData(ListResult result, Text messages) {
    	Iterator it = result.getElements().iterator();
    	while(it.hasNext()) {
    		cantReg += ((CombinedHistoryDataType)it.next()).getEventCount();
    	}
        attributes = new Object[cantReg][15];
        it = result.getElements().iterator();
        int id = -1;
        int index = 0;
        while(it.hasNext()) {
    		CombinedHistoryDataType dt = (CombinedHistoryDataType)it.next();
    		if(dt.getId().intValue() != id) {
    			id = dt.getId().intValue();
    			attributes[index][0] = dt.getId();
    			attributes[index][1] = dt.getFirstname();
    			attributes[index][2] = dt.getLastname();
    			attributes[index][3] = dt.getCountry();
    			attributes[index][4] = dt.getDivorg1();
    			attributes[index][5] = dt.getDivorg2();
    			attributes[index][6] = dt.getDivorg3();
    		}
    		Collection events = dt.getEvents();
    		Collections.sort((List)events);
    		Iterator it2 = events.iterator();
    		if(!it2.hasNext()) {
	    		index++;
    		}else {
	    		while(it2.hasNext()) {
	    			EventCombinedDataType event = (EventCombinedDataType)it2.next();
					attributes[index][7] = event.getDate();
					attributes[index][8] = event.getType();
					attributes[index][9] = event.getClasification();
					attributes[index][10] = event.getResult();
					attributes[index][11] = event.getStory();
					attributes[index][12] = event.getObservations();
					attributes[index][13] = event.getRecomendation();
					attributes[index][14] = event.getEvitability();
		    		index++;
	    		}
    		}
    	}
        this.total = result.getTotal();
        this.messages = messages;
    }

    public int getRowCount() {
        return cantReg;
    }

    public int getColumnCount() {
        return 17;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
    	switch(columnIndex) {
    		case 15:
    			if(attributes[rowIndex][0] != null) {
    				color = !color;
    			}
    			return new Boolean(color);
    		case 16:
    			return total;
    		case 3: case 8: case 9: case 10: case 14:
    			return (attributes[rowIndex][columnIndex] == null) ? null : messages.getText((String)attributes[rowIndex][columnIndex]);
    		default:
    			return attributes[rowIndex][columnIndex];
    	}
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "id";
            case 1:
                return "firstName";
            case 2:
                return "lastName";
            case 3:
            	return "country";
            case 4:
                return "divorg1";
            case 5:
                return "divorg2";
            case 6:
                return "divorg3";
            case 7:
            	return "date";
            case 8:
            	return "type";
            case 9:
            	return "clasification";
            case 10:
            	return "result";
            case 11:
            	return "story";
            case 12:
            	return "observations";
            case 13:
            	return "recomendation";
            case 14:
            	return "evitability";
            case 15:
            	return "color";
            case 16:
            	return "total";
        }
        return "";
    }
    
	public int getTotal() {
		return total;
	}
}


