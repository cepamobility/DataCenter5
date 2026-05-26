/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.driver.report;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.util.ListResult;

public class AlcoholControlReportData extends AbstractTableModel 
    implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total;
    private AlcoholControlReportDataType[] attributes;
    private boolean color = true;
    private Text messages;
    
    public AlcoholControlReportData() {
        total = 0;
        attributes = new AlcoholControlReportDataType[0]; 
    }
    
    public AlcoholControlReportData(ListResult reportDrivers) {
        total = reportDrivers.getTotal();
        Collection elements = reportDrivers.getElements();
        Collections.sort((List) elements);
        attributes = (AlcoholControlReportDataType[])elements.toArray(new AlcoholControlReportDataType[0]);
    }


    public int getRowCount() {
        return attributes.length;
    }

    public int getColumnCount() {
        return 25;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return String.valueOf(attributes[rowIndex].getId());
            case 1:
                return attributes[rowIndex].getFirstname();  
            case 2:
                return attributes[rowIndex].getLastname();  
            case 3:
                color = !color;
                return new Boolean(color);
            case 4:
                return messages.getText(attributes[rowIndex].getCountry());
            case 5:
                return attributes[rowIndex].getBirthdate();
            case 6:
                return attributes[rowIndex].getExclusiondate();
            case 7:
                return messages.getText(attributes[rowIndex].getContract());
            case 8:
                return attributes[rowIndex].getDivorg11();
            case 9:
                return attributes[rowIndex].getDivorg12();
            case 10:
                return attributes[rowIndex].getDivorg13();
            case 11:
                return attributes[rowIndex].getDivorg21();
            case 12:
                return attributes[rowIndex].getDivorg22();
            case 13:
                return attributes[rowIndex].getDivorg23();
            case 14:
                return attributes[rowIndex].getDivorg31();
            case 15:
                return attributes[rowIndex].getDivorg32();
            case 16:
                return attributes[rowIndex].getDivorg33();
            case 17:
                return attributes[rowIndex].getDivgeo1();
            case 18:
                return attributes[rowIndex].getDivgeo2();
            case 19:
                return attributes[rowIndex].getDivgeo3();
            case 20:
                return new Integer(total);
            case 21:
            	return messages.getText(attributes[rowIndex].getCountry());
            case 22:
                return attributes[rowIndex].getLastControl();
            case 23:
                if(attributes[rowIndex].getStatus() != AlcoholControlReportDataType.NEVER) {
                    if(attributes[rowIndex].getResult().intValue() == DriverData.POSITIVE) {
                        return messages.getText("generic.messages.positive");
                    }else {
                        return messages.getText("generic.messages.negative");
                    }
                }
                return null;
            case 24:
                switch(attributes[rowIndex].getStatus()) {
                    case AlcoholControlReportDataType.NEVER:
                        return messages.getText("driver.alcoholcontrol.neverdone");
                    case AlcoholControlReportDataType.EXPIRED:
                        return messages.getText("driver.alcoholcontrol.expired");
                    default:
                        return messages.getText("driver.alcoholcontrol.effective");
                }
        }
        return null;
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "id";
            case 1:
                return "name";
            case 2:
                return "lastname";
            case 3:
                return "color";
            case 4:
                return "country";
            case 5:
                return "birthdate";
            case 6:
                return "exclusiondate";
            case 7:
                return "contract";
            case 8:
                return "divorg11";
            case 9:
                return "divorg12";
            case 10:
                return "divorg13";
            case 11:
                return "divorg21";
            case 12:
                return "divorg22";
            case 13:
                return "divorg23";
            case 14:
                return "divorg31";
            case 15:
                return "divorg32";
            case 16:
                return "divorg33";
            case 17:
                return "divgeo1";
            case 18:
                return "divgeo2";
            case 19:
                return "divgeo3";
            case 20:
                return "total";
            case 21:
            	return "country";
            case 22:
                return "lastControl";
            case 23:
                return "result";
            case 24:
                return "status";
        }
        return null;
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }

    public int getTotal() {
        return total;
    }
}
