package communication.driver.report;

import java.io.Serializable;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverData;
import communication.driver.tables.TelematicControlData;
import communication.language.Text;

public class TelematicControlReportData extends AbstractTableModel 
    implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total = 0;
    private TelematicControlData[] attributes;
    private boolean color = true;
    private Text messages;
    private UserSessionData userSessionData;

    
    public TelematicControlReportData() {
        total = 0;
        attributes = new TelematicControlData[0];
    }
    
    public TelematicControlReportData(Collection<TelematicControlData> reportDrivers,UserSessionData usr) {
        this.total = reportDrivers.size();
        attributes = (TelematicControlData[])reportDrivers.toArray(new TelematicControlData[0]);
        userSessionData = usr;
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
            case 0:
                return String.valueOf(attributes[rowIndex].getDriver().getId());
            case 1:
                return attributes[rowIndex].getDriver().getFirstName()+" "+attributes[rowIndex].getDriver().getLastName();
            case 2:
                return attributes[rowIndex].getDriver().getDivOrgItem1();
            case 3:
            	return (attributes[rowIndex].getDate() != null) ? attributes[rowIndex].getDate() : "---";
            case 4:
            	return (attributes[rowIndex].getTime() != null) ? attributes[rowIndex].getTime() : "---";
            case 5:
            	return (attributes[rowIndex].getType() != null) ? messages.getText(TelematicControlData.getTypeString(attributes[rowIndex].getType())) : "---";
            case 6:
            	return attributes[rowIndex].getObservations();
            case 7:
            	return attributes[rowIndex].getSpeed();
            case 8:
            	return attributes[rowIndex].getDuration();
            case 9:
            	return attributes[rowIndex].getGarage();
            case 10:
            	return attributes[rowIndex].getStatusComment();
            case 11:
                color = !color;
                return new Boolean(color);
            case 12:
            	return new Integer(this.total);
        }
        return null;
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "id";
            case 1:
                return "driver";
            case 2:
                return "divorg1";
            case 3:
                return "date";
            case 4:
                return "time";
            case 5:
                return "type";
            case 6:
            	return "location";
            case 7:
            	return "speed";
            case 8:
            	return "duration";
            case 9:
            	return "vehicle";
            case 10:
            	return "comment";
            case 11:
                return "color"; // color de la linea del registro
            case 12:
            	return "total";
        }
        return null;
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }
}
