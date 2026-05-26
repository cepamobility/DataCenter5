/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.activity.report;

import java.io.Serializable;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import communication.activity.tables.CEPAActivityData;
import communication.driver.tables.DriverData;
import communication.language.Text;
public class ActivityReportGTData extends AbstractTableModel 
	implements Serializable {

    static final long serialVersionUID = 1L;
    
    int total;
    CEPAActivityData[] attributes;
    boolean color = true;
    Text messages;
    
    public ActivityReportGTData() {
        total = 0;
        attributes = new CEPAActivityData[0]; 
    }
    
    public ActivityReportGTData(Collection details) {
        total = details.size();
        attributes = (CEPAActivityData[])details.toArray(new CEPAActivityData[0]);
    }

    public int getRowCount() {
        return total;
    }

    public int getColumnCount() {
        return 17;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return messages.getText(attributes[rowIndex].getDMY());
            case 1:
                return attributes[rowIndex].getData(DriverData.DIV_ORG,3,2);
            case 2:
                return attributes[rowIndex].getData(DriverData.DIV_ORG,1,1);
            case 3:
                return attributes[rowIndex].getData(DriverData.DIV_ORG,1,2);
            case 4:
                return attributes[rowIndex].getData(DriverData.DIV_ORG,1,3);
            case 5:
                return attributes[rowIndex].getData(DriverData.DIV_ORG,2,1);
            case 6:
                return attributes[rowIndex].getData(DriverData.DIV_ORG,2,2);
            case 7:
                return attributes[rowIndex].getData(DriverData.DIV_ORG,2,3);
            case 8:
                return attributes[rowIndex].getData(DriverData.DIV_ORG,3,1);
            case 9:
                return attributes[rowIndex].getData(DriverData.DIV_ORG,3,2);
            case 10:
                return attributes[rowIndex].getData(DriverData.DIV_ORG,3,3);
            case 11:
                return messages.getText(attributes[rowIndex].getData(DriverData.DIV_GEO,1,1));
            case 12:
                return messages.getText(attributes[rowIndex].getData(DriverData.DIV_GEO,1,2));
            case 13:
                return messages.getText(attributes[rowIndex].getData(DriverData.DIV_GEO,1,3));
            case 14:
            	return messages.getText(attributes[rowIndex].getCity());
            case 15:
                return messages.getText(attributes[rowIndex].getType().getResourceBundleId());
            case 16:
                return "32";
            
            
        }
        return null;
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "date";
            case 1:
                return "unit";
            case 2:
                return "divorg11";
            case 3:
                return "divorg12";
            case 4:
                return "divorg13";
            case 5:
                return "divorg21";
            case 6:
                return "divorg22";
            case 7:
                return "divorg23";
            case 8:
                return "divorg31";
            case 9:
                return "divorg32";
            case 10:
                return "divorg33";
            case 11:
                return "divgeo1";
            case 12:
                return "divgeo2";
            case 13:
                return "divgeo3";
            case 14:
                return "city";
            case 15:
                return "type";
            case 16:
                return "participants";
        }
        return null;
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }
}