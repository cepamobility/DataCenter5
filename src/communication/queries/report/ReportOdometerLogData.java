package communication.queries.report;

import java.io.Serializable;

import javax.swing.table.AbstractTableModel;

import communication.language.Text;

public class ReportOdometerLogData extends AbstractTableModel implements Serializable {

    static final long serialVersionUID = 1L;
    
    private Object[][] attributes;
    private boolean color = true;
    private Text messages;

    public ReportOdometerLogData() {
        attributes = new Object[0][0]; 
    }
    
    public ReportOdometerLogData(Object[][] attributes,Text messages) {
        this.attributes = attributes;
        this.messages = messages;
    }

    public int getRowCount() {
        return attributes.length;
    }

    public int getColumnCount() {
        return 20;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return messages.getText("generic.month."+String.valueOf(attributes[rowIndex][0]))+" / "+
                	String.valueOf(attributes[rowIndex][1]);
            case 1: // user
            	return attributes[rowIndex][columnIndex+1];
            case 6:
                return String.valueOf(attributes[rowIndex][columnIndex+1]);
            case 2:case 3:case 4:case 5:case 7:case 9:
                return attributes[rowIndex][columnIndex+1];
            case 8: // trade (marca)
                return messages.getText((String)attributes[rowIndex][9]);
            case 10: // flota
            	return attributes[rowIndex][columnIndex+1];
            case 11:
                color = !color;
                return new Boolean(color);
            case 12: // conductor
                return attributes[rowIndex][13];
            case 13: case 14: case 15: case 16:
                return attributes[rowIndex][columnIndex+3];
            case 17:
            	return String.valueOf(attributes[rowIndex][12]);
            case 18: // conductor
                return attributes[rowIndex][14];
        	case 19:
            	return (((Boolean)attributes[rowIndex][15]).booleanValue()) ?  messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
        }
        return null;
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "period";            
            case 1:
                return "user";            
            case 2:
                return "date";            
            case 3:
                return "odometer";            
            case 4:
                return "accidents";            
            case 5:
                return "incidents";            
            case 6:
                return "vehicle";            
            case 7:
                return "plate";            
            case 8:
                return "trade";            
            case 9:
                return "model";
            case 10:
                return "fleet";
            case 11:
                return "color";
            case 12:
                return "driverFirstName";
            case 13:
                return "divorg1";
            case 14:
                return "divorg2";
            case 15:
                return "divorg3";
            case 16:
                return "divorg4";
            case 17:
                return "driverId";
            case 18:
                return "driverLastName";
            case 19:
                return "director";
        }
        return null;
    }
}
