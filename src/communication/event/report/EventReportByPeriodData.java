/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.event.report;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import communication.event.tables.EventDataType;
import communication.event.util.EventByPeriodData;
import communication.language.Text;

public class EventReportByPeriodData extends AbstractTableModel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total;
    private int columns;
    private EventByPeriodData[] attributes;
    private boolean color = true;

    private Text messages;
    
    public EventReportByPeriodData() {
        total = 0;
        attributes = new EventByPeriodData[0]; 
        columns = 0;
    }
    
    public EventReportByPeriodData(Collection data,int columns) {
        total = data.size();
        Collections.sort((List) data);
        attributes = (EventByPeriodData[])data.toArray(new EventByPeriodData[0]);
        this.columns = columns;
    }

    public int getRowCount() {
        return total;
    }

    public int getColumnCount() {
        return columns+5;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return "color";
            case 1:
                switch(Integer.parseInt(attributes[rowIndex].getTypeEvent())) {
                    case EventDataType.ACCIDENT:
                        return messages.getText("generic.messages.accident");
                    case EventDataType.INCIDENT:
                        return messages.getText("generic.messages.incident");
                    case EventDataType.NEARMISS:
                        return messages.getText("generic.event.nearmiss");
                    default:
                        return messages.getText("generic.messages.fine");
                }
            case 2:
                return attributes[rowIndex].getDivOrg1Id();
            case 3:
                return attributes[rowIndex].getDivOrg1Name();
            case 4:
                return attributes[rowIndex].getDivOrg2Id();
            case 5:
                return attributes[rowIndex].getDivOrg2Name();
            case 6:
                return attributes[rowIndex].getDivOrg3Id();
            case 7:
                return attributes[rowIndex].getDivOrg3Name();
            case 8:
                return attributes[rowIndex].getDivOrg4Id();
            case 9:
                return attributes[rowIndex].getDivOrg4Name();
            default:
                if(columnIndex == columns+4) {
                    return attributes[rowIndex].getTotal();   
                }
                return attributes[rowIndex].getMonths(columnIndex-10);
        }
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "color";
            case 1:
                return "type";
            case 2:
                return "divOrg1Id";
            case 3:
                return "divOrg1Name";
            case 4:
                return "divOrg2Id";
            case 5:
                return "divOrg2Name";
            case 6:
                return "divOrg3Id";
            case 7:
                return "divOrg3Name";
            case 8:
                return "divOrg4Id";
            case 9:
            	return "divOrg4Name";
            default:
                return "column"+(column-10);
        }
    }

    public void setMessages(Text messages) {
        this.messages = messages;
    }
    
    
}
