/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.activity.report;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import communication.language.Text;

public class CommentaryDriveReportData extends AbstractTableModel implements Serializable {

    static final long serialVersionUID = 1L;
    
    private Integer[] items;
    private CommentaryDriveDataType[] attributes;
    
    boolean color = true;
    Text messages;
    
    public CommentaryDriveReportData() {
        items = new Integer[0]; 
        attributes = new CommentaryDriveDataType[0];
    }
    
    public CommentaryDriveReportData(Collection data,Integer[] items,Text messages) {
        Collections.sort((List)data);
        this.attributes = (CommentaryDriveDataType[])data.toArray(new CommentaryDriveDataType[0]);
        this.items = items;
        this.messages = messages;
    }

    public int getRowCount() {
        return attributes.length;
    }

    public int getColumnCount() {
        return items.length + 13;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return attributes[rowIndex].getDate();
            case 1:
                return messages.getText(attributes[rowIndex].getConductiontype());
            case 2:
                return attributes[rowIndex].getFirstName();
            case 3:
                return attributes[rowIndex].getLastName();
            case 4:
                return messages.getText(attributes[rowIndex].getCountry());
            case 5:
                return attributes[rowIndex].getDivOrg1();
            case 6:
                return attributes[rowIndex].getDivOrg2();
            case 7:
                return attributes[rowIndex].getDivOrg3();
            case 11:
                return attributes[rowIndex].getDivOrg4();
            case 8:
                return attributes[rowIndex].getObs();
            case 9:
                color = !color;
                return color;
            case 10:
                return (attributes[rowIndex].getVehicleAllowed() == null || attributes[rowIndex].getVehicleAllowed().length() == 0) ? "---" : messages.getText(attributes[rowIndex].getVehicleAllowed());
            case 12:
                return attributes[rowIndex].getLicenseObs();
            default:
                return attributes[rowIndex].containItem(items[columnIndex-13]);
        }
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "activityDate";
            case 1:
                return "type";
            case 2:
                return "firstname";
            case 3:
                return "lastname";
            case 4:
                return "country";
            case 5:
                return "divorg1";
            case 6:
                return "divorg2";
            case 7:
                return "divorg3";
            case 8:
                return "observations";
            case 9:
                return "color";
            case 10:
                return "allowed";
            case 11:
                return "divorg4";
            case 12:
                return "licenseObs";
            default:
                return String.valueOf(items[column-13]);
        }
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }
}
