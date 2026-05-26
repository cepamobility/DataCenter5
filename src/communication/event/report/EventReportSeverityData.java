/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.event.report;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import communication.event.util.EventSeverityData;
import communication.language.Text;

public class EventReportSeverityData extends AbstractTableModel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total;
    private EventSeverityData[] attributes;
    
    private Hashtable<String,int[]> divOrgValues = new Hashtable<String,int[]>();
    private int totalValue = 0;
    
    private boolean color = true;

    public EventReportSeverityData() {
        total = 0;
        attributes = new EventSeverityData[0]; 
    }
    
    public EventReportSeverityData(Collection data) {
        total = data.size();
        Collections.sort((List) data);
        attributes = (EventSeverityData[])data.toArray(new EventSeverityData[0]);
        for(int i = 0; i < attributes.length; i++) {
        	EventSeverityData severityData = attributes[i];
        	addData("total",severityData);
    		for(int j = 0; j < 8; j++) {
    			totalValue += severityData.getSeverity(j);				
    		}
        	if(severityData.getDivOrg1Id() != null) {
        		String key = String.valueOf(severityData.getDivOrg1Id());
            	addData(key,severityData);
            	if(severityData.getDivOrg2Id() != null) {
            		key += "-"+String.valueOf(severityData.getDivOrg2Id());
                	addData(key,severityData);
                	if(severityData.getDivOrg3Id() != null) {
                		key += "-"+String.valueOf(severityData.getDivOrg3Id());
                    	addData(key,severityData);
                    	if(severityData.getDivOrg4Id() != null) {
                    		key += "-"+String.valueOf(severityData.getDivOrg4Id());
                        	addData(key,severityData);
                    	}
                	}
            	}
            }
        }
    }

    private void addData(String key, EventSeverityData severityData) {
		if(divOrgValues.containsKey(key)) {
			int[] values = divOrgValues.get(key);
			for(int i = 0; i < values.length; i++) {
				values[i] += severityData.getSeverity(i);				
			}
		}else {
			int[] values = new int[8];
			for(int i = 0; i < values.length; i++) {
				values[i] += severityData.getSeverity(i);				
			}
			divOrgValues.put(key, values);
		}
		
	}

	public int getRowCount() {
        return total;
    }

    public int getColumnCount() {
        return 49;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return "color";
            case 1:
                return attributes[rowIndex].getDivOrg1Id();
            case 2:
                return attributes[rowIndex].getDivOrg1Name();
            case 3:
                return attributes[rowIndex].getDivOrg2Id();
            case 4:
                return attributes[rowIndex].getDivOrg2Name();
            case 5:
                return attributes[rowIndex].getDivOrg3Id();
            case 6:
                return attributes[rowIndex].getDivOrg3Name();
            case 7:
                return attributes[rowIndex].getDivOrg4Id();
            case 8:
                return attributes[rowIndex].getDivOrg4Name();
            default:
            	if(columnIndex <= 16) {
            		int[] data = divOrgValues.get("total");
            		return new Integer(data[columnIndex-9]);
            	}else if(columnIndex <= 24) {
            		String key = String.valueOf(attributes[rowIndex].getDivOrg1Id());
            		int[] data = divOrgValues.get(key);
            		return new Integer(data[columnIndex-17]);
            	}else if(columnIndex <= 32) {
            		String key = String.valueOf(attributes[rowIndex].getDivOrg1Id())+"-"+String.valueOf(attributes[rowIndex].getDivOrg2Id());
            		int[] data = divOrgValues.get(key);
            		return new Integer(data[columnIndex-25]);
            	}else if(columnIndex <= 40) {
            		String key = String.valueOf(attributes[rowIndex].getDivOrg1Id())+"-"+String.valueOf(attributes[rowIndex].getDivOrg2Id())
            		+"-"+String.valueOf(attributes[rowIndex].getDivOrg3Id());
            		int[] data = divOrgValues.get(key);
            		return new Integer(data[columnIndex-33]);
            	}else {
            		String key = String.valueOf(attributes[rowIndex].getDivOrg1Id())+"-"+String.valueOf(attributes[rowIndex].getDivOrg2Id())
            		+"-"+String.valueOf(attributes[rowIndex].getDivOrg3Id())+"-"+String.valueOf(attributes[rowIndex].getDivOrg4Id());
            		int[] data = divOrgValues.get(key);
            		return new Integer(data[columnIndex-41]);
            	}
        }
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "color";
            case 1:
                return "divOrg1Id";
            case 2:
                return "divOrg1Name";
            case 3:
                return "divOrg2Id";
            case 4:
                return "divOrg2Name";
            case 5:
                return "divOrg3Id";
            case 6:
                return "divOrg3Name";
            case 7:
                return "divOrg4Id";
            case 8:
            	return "divOrg4Name";
            default:
            	if(column <= 16) {
            		return "total_"+(column-9);
            	}else if(column <= 24) {
            		return "divOrg1_"+(column-17);
            	}else if(column <= 32) {
            		return "divOrg2_"+(column-25);
            	}else if(column <= 40) {
            		return "divOrg3_"+(column-33);
            	}else {
            		return "divOrg4_"+(column-41);
            	}
        }
    }

    
}
