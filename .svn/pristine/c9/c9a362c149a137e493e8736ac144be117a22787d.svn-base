/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.vehicle.report;

import java.io.Serializable;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import communication.language.Text;
import communication.vehicle.tables.VehicleDataText;

public class VehicleReportCombinedData extends AbstractTableModel 
	implements Serializable {

    static final long serialVersionUID = 1L;
    
    int total;
    VehicleDataText[] attributes;
    boolean color = true;
    Text messages;
    
    public VehicleReportCombinedData() {
        attributes = new VehicleDataText[0]; 
        total = attributes.length;
    }
    
    public VehicleReportCombinedData(Collection reportDrivers, int total) {
        this.total = total; //reportDrivers.size();
        attributes = (VehicleDataText[])reportDrivers.toArray(new VehicleDataText[0]);
    }

    public int getRowCount() {
        return attributes.length;
    }

    public int getColumnCount() {
        return 19;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return attributes[rowIndex].getPlate();
            case 1:
                return messages.getText(attributes[rowIndex].getTrade());
            case 2:
                color = !color;
                return new Boolean(color);
            case 3:
            	if (attributes[rowIndex].getType() == null){
            		// Trailer:
            		return messages.getText("vehicle.data.trailer");
            	}else {
            		// Vehicles:
            		return messages.getText(attributes[rowIndex].getType());
            	}
            case 4:
                return attributes[rowIndex].getModel();
            case 5:
                return attributes[rowIndex].getYear();
            case 6:
                return messages.getText(attributes[rowIndex].getCountry());
            case 7:
                return attributes[rowIndex].getDivOrg1();
            case 8:
                return attributes[rowIndex].getDivOrg2();
            case 9:
                return attributes[rowIndex].getDivOrg3();
            case 10:
                return attributes[rowIndex].getDivOrg3();
            case 11:
                return attributes[rowIndex].getDivOrg3();
            case 12:
                return attributes[rowIndex].getDivOrg3();
            case 13:
                return attributes[rowIndex].getDivOrg3();
            case 14:
                return attributes[rowIndex].getDivOrg3();
            case 15:
                return attributes[rowIndex].getDivOrg3();
            case 16:
                return attributes[rowIndex].getId().toString();
            case 17:
            	return new Integer(total);  
            case 18:
            	if (attributes[rowIndex].getType() == null)
            		// Trailer:
            		return "true";
            	else
            		// Vehicle:
            		return "false";            			
            	
        }
        return null;
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "registrationNumber";
            case 1:
                return "trade";
            case 2:
                return "color";
            case 3:
                return "type";
            case 4:
                return "model";
            case 5:
                return "year";
            case 6:
                return "country";
            case 7:
                return "divorg11";
            case 8:
                return "divorg12";
            case 9:
                return "divorg13";
            case 10:
                return "divorg21";
            case 11:
                return "divorg22";
            case 12:
                return "divorg23";
            case 13:
                return "divorg31";
            case 14:
                return "divorg32";
            case 15:
                return "divorg33";
            case 16:
                return "id";
            case 17:
            	return "total";
            case 18:
            	return "isTrailer";
        }
        return null;
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }
}
