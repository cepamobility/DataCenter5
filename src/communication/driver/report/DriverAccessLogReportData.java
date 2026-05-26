package communication.driver.report;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.util.DriverAccessLogData;

public class DriverAccessLogReportData extends AbstractTableModel 
    implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private DriverAccessLogData[] attributes;
    private boolean color = true;
    Format dateFormat = new SimpleDateFormat("dd-MM-yyyy");	

    private int[][] principal;

	private Text messages;
    
    public DriverAccessLogReportData(Collection logs,Text messages) {
        Collections.sort((List)logs);
        attributes = (DriverAccessLogData[])logs.toArray(new DriverAccessLogData[0]);
        this.messages = messages;
    }

    public int getRowCount() {
        return attributes.length;
    }

    public int getColumnCount() {
        return 7;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return attributes[rowIndex].getCorporationId();
            case 1:
                return attributes[rowIndex].getFirstName()+" "+attributes[rowIndex].getLastName();
            case 2:
                return attributes[rowIndex].getContratist();
            case 3:
                if(attributes[rowIndex].getAccess() == 0) {
                	return messages.getText("doorcontrol.access.success");
                }else {
                	return messages.getText("doorcontrol.access.forbidden");
                }
            case 4:
                return dateFormat.format(attributes[rowIndex].getDate());
            case 5:            	
                return attributes[rowIndex].getUser();
            case 6:           
                color = !color;
                return new Boolean(color);
        }
        return "";
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "id";
            case 1:
                return "name";
            case 2:
                return "contratist";
            case 3:
                return "access";
            case 4:
                return "date";
            case 5:
            	return "user";
            case 6:
            	return "color";
        }
        return "";
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }
    
    public Text getMessages(){
    	return this.messages;
    }
}
