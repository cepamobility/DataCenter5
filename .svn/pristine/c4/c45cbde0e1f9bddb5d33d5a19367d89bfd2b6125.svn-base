/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.driver.report;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import communication.administration.corporation.util.Util;
import communication.driver.tables.DriverData;
import communication.language.Text;

public class DriverInsertedLogData extends AbstractTableModel 
    implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total = 0;
    private Object[][] attributes;
    private boolean color = true;
    private Text messages;

    public DriverInsertedLogData() {
        total = 0;
        attributes = new Object[0][0];
    }
    
    public DriverInsertedLogData(Collection reportDrivers, Text messages) {
        total = reportDrivers.size();
        attributes = (Object[][])reportDrivers.toArray(new Object[0][0]);
        this.messages = messages;
    }

    public int getRowCount() {
        return total;
    }
    
    /**
	 * @return Returns the total.
	 */
	public int getTotal() {
		return total;
	}

    public int getColumnCount() {
        return 13;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0: case 1: case 2: case 5: case 7: case 8: case 9: case 10: 
                return String.valueOf(attributes[rowIndex][columnIndex]);
            case 3:
                return (((Boolean)attributes[rowIndex][columnIndex]).booleanValue()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 4:
            	return (Date)attributes[rowIndex][columnIndex];
            case 6:
            	return messages.getText(String.valueOf(attributes[rowIndex][columnIndex]));
            case 11:
                color = !color;
                return new Boolean(color);
            case 12:
                return new Integer(total);
        }
        return null;
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
                return "director";
            case 4:
                return "date";
            case 5:
                return "user";
            case 6:
                return "country";
            case 7:
                return "divorg1";
            case 8:
                return "divorg2";
            case 9:
                return "divorg3";
            case 10:
                return "divorg4";
            case 11:
                return "color";
            case 12:
            	return "total";
        }
        return null;
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }
}
