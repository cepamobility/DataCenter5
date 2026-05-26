package communication.queries.report;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import persistence.util.Util;

import communication.fleet.util.APMMReportData;
import communication.language.Text;

public class ReportAPMMByDivisionData extends AbstractTableModel 
	implements Serializable {

    static final long serialVersionUID = 1L;
    
    int total;
    APMMReportData[] attributes;
    boolean color = true;
    Text messages;

    private Hashtable divisions;
    private Hashtable subdivisions;

    int accidents = 0;
    int incidents = 0;
    int miles = 0;

    public ReportAPMMByDivisionData() {
        total = 0;
        attributes = new APMMReportData[0];
    }
    
    public ReportAPMMByDivisionData(Collection details) {
        total = details.size();
        Collections.sort((List) details);
        attributes = (APMMReportData[])details.toArray(new APMMReportData[0]);
    }

    public int getRowCount() {
        return total;
    }

    public int getColumnCount() {
        return 18;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return null;
            case 1:
            	if (attributes[rowIndex].getDivOrg1Id() == null){
            		return new Integer(0);
            	}
                return attributes[rowIndex].getDivOrg1Id();
                
            case 2:
            	if (attributes[rowIndex].getDivOrg1Name() == null){
            		return "---";
            	}
                return attributes[rowIndex].getDivOrg1Name();
                
            case 3:
            	if (attributes[rowIndex].getDivOrg2Id() == null){
            		return new Integer(0);
            	}
                return attributes[rowIndex].getDivOrg2Id();
                
            case 4:
            	if (attributes[rowIndex].getDivOrg2Name() == null){
            		return "---";
            	}
                return attributes[rowIndex].getDivOrg2Name();
                
            case 5:
            	if (attributes[rowIndex].getDivOrg3Id() == null){
            		return new Integer(0);
            	}
                return attributes[rowIndex].getDivOrg3Id();
                
            case 6:
            	if (attributes[rowIndex].getDivOrg3Name() == null){
            		return "---";
            	}
                return attributes[rowIndex].getDivOrg3Name();
                
            case 7:
            	if (attributes[rowIndex].getMiles() == null){
            		return new Integer(0);
            	}
                return attributes[rowIndex].getMiles();
                
            case 8:
            	if (attributes[rowIndex].getAccidents() == null){
            		return new Integer(0);
            	}
                return attributes[rowIndex].getAccidents();
                
            case 9:
            	if (attributes[rowIndex].getIncidents() == null){
            		return new Integer(0);
            	}
                return attributes[rowIndex].getIncidents();
            case 10:
            	
                if(attributes[rowIndex].getMiles() == null ||
                	attributes[rowIndex].getMiles().doubleValue() == 0 ||
                	attributes[rowIndex].getAccidents() == null) {
                    return new Float(0);
                }
                return new Float(attributes[rowIndex].getAccidents().floatValue() * 1000000 / 
                		attributes[rowIndex].getMiles().floatValue());
            case 11:
                if(attributes[rowIndex].getMiles() == null || 
                	attributes[rowIndex].getMiles().doubleValue() == 0 ||
                	attributes[rowIndex].getIncidents() == null) {
                    return new Float(0);
                }
                return new Float(attributes[rowIndex].getIncidents().floatValue() * 1000000 / 
                		attributes[rowIndex].getMiles().floatValue());
                
            case 12:
                color = !color;
                return new Boolean(color);
                
            case 13:
            	if (attributes[rowIndex].getDivOrg4Id() == null){
            		return new Integer(0);
            	}
                return attributes[rowIndex].getDivOrg4Id();
                
            case 14:
            	if (attributes[rowIndex].getDivOrg4Name() == null){
            		return "---";
            	}
                return attributes[rowIndex].getDivOrg4Name();
            case 15:
            	if (attributes[rowIndex].getMiles() == null){
            		return new Integer(0);
            	}
                return new Integer(String.valueOf(Math.round(attributes[rowIndex].getMiles() * Util.KMS_TO_MILES)));
            case 16:
            	
                if(attributes[rowIndex].getMiles() == null ||
                	attributes[rowIndex].getMiles().doubleValue() == 0 ||
                	attributes[rowIndex].getAccidents() == null) {
                    return new Float(0);
                }
                return new Float(attributes[rowIndex].getAccidents().floatValue() * 1000000 / 
                		(attributes[rowIndex].getMiles().floatValue() * Util.KMS_TO_MILES));
            case 17:
                if(attributes[rowIndex].getMiles() == null || 
                	attributes[rowIndex].getMiles().doubleValue() == 0 ||
                	attributes[rowIndex].getIncidents() == null) {
                    return new Float(0);
                }
                return new Float(attributes[rowIndex].getIncidents().floatValue() * 1000000 / 
                		(attributes[rowIndex].getMiles().floatValue() * Util.KMS_TO_MILES));
                
        }
        return null;
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "country";
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
                return "miles";
            case 8:
                return "accidents";
            case 9:
                return "incidents";
            case 10:
                return "apmm";
            case 11:
                return "ipmm";
            case 12:
                return "color";
            case 13:
                return "divOrg4Id";
            case 14:
                return "divOrg4Name";
            case 15:
                return "kms";
            case 16:
                return "apmk";
            case 17:
                return "ipmk";
        }
        return null;
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }
    


}
