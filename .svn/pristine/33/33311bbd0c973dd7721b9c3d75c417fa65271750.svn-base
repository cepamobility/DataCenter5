/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.queries.report;

import java.io.Serializable;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import org.hibernate.Hibernate;

import communication.language.Text;

public class MissingOdometerData extends AbstractTableModel implements Serializable {

    static final long serialVersionUID = 1L;
    
    private Object[][] attributes;
    private boolean color = true;
    private Text messages;

    public MissingOdometerData() {
        attributes = new Object[0][0]; 
    }
    
    public MissingOdometerData(Collection vehicles,Text messages) {
        this.attributes = (Object[][])vehicles.toArray(new Object[0][0]);
        this.messages = messages;
    }

    public int getRowCount() {
        return attributes.length;
    }

    public int getColumnCount() {
        return 14;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
        	case 0:
        		return attributes[rowIndex][0];
        	case 1:
        		return String.valueOf(attributes[rowIndex][1]);
            case 2:
                return attributes[rowIndex][3];
            case 3:
                return messages.getText((String)attributes[rowIndex][4]);
            case 4:
            	if(attributes[rowIndex].length > 9) {
            		return attributes[rowIndex][9];
            	}
                return null;
            case 5:
            	if(attributes[rowIndex].length > 10) {
            		return attributes[rowIndex][10];
            	}
                return null;
            case 6:
            	if(attributes[rowIndex].length > 11) {
            		return attributes[rowIndex][11];
            	}
                return null;
            case 7:
                color = !color;
                return new Boolean(color);
            case 8:
                return String.valueOf(attributes[rowIndex][5]);
            case 9:
                return String.valueOf(attributes[rowIndex][6]);
            case 10:
                return attributes[rowIndex][7];
            case 11:
            	if(attributes[rowIndex].length > 12) {
            		return attributes[rowIndex][12];
            	}
                return null;
        	case 12:
        		return String.valueOf(attributes[rowIndex][2]);
        	case 13:
            	return (((Boolean)attributes[rowIndex][8]).booleanValue()) ?  messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
        }
        return null;
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "fleet";            
            case 1:
                return "driverFirstName";            
            case 2:
                return "plate";            
            case 3:
                return "country";            
            case 4:
                return "divorg1";            
            case 5:
                return "divorg2";            
            case 6:
                return "divorg3";            
            case 7:
                return "color";
            case 8:
                return "idDriver";
            case 9:
                return "idVehicle";
            case 10:
                return "email";
            case 11:
                return "divorg4";            
            case 12:
                return "driverLastName";            
        	case 13:
                return "director";            
        }
        return null;
    }
}
