/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.queries.report;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import communication.fleet.util.KmLtrFuelData;
import communication.language.Text;

public class ReportKmLtrData extends AbstractTableModel 
	implements Serializable {

    static final long serialVersionUID = 1L;
    
    int total;
    KmLtrFuelData[] attributes;
    boolean color = true;
    Text messages;

    int accidents = 0;
    int incidents = 0;
    int miles = 0;

    private Integer monthCount;

    private int order1;
    private int order2;
    private int order3;
    private int order4;

    public ReportKmLtrData() {
        total = 0;
        attributes = new KmLtrFuelData[0];
        monthCount = new Integer(0);
    }

    public ReportKmLtrData(Collection data,Text messages) {
        total = data.size();
        Collections.sort((List) data);
        attributes = (KmLtrFuelData[])data.toArray(new KmLtrFuelData[0]);
        this.messages = messages;
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
                return  attributes[rowIndex].getDivOrg1Id();
            case 1:
                if(order1 == 3) {
                    return  messages.getText(attributes[rowIndex].getDivOrg1Name());
                }else {
                    return  attributes[rowIndex].getDivOrg1Name();
                }
            case 2:
                return  attributes[rowIndex].getDivOrg2Id();
            case 3:
                if(order2 == 3) {
                    return  messages.getText(attributes[rowIndex].getDivOrg2Name());
                }else {
                    return  attributes[rowIndex].getDivOrg2Name();
                }
            case 4:
                return  attributes[rowIndex].getDivOrg3Id();
            case 5:
                if(order3 == 3) {
                    return  messages.getText(attributes[rowIndex].getDivOrg3Name());
                }else {
                    return  attributes[rowIndex].getDivOrg3Name();
                }
            case 6:
                return  attributes[rowIndex].getDivOrg4Id();
            case 7:
                if(order4 == 3) {
                    return  messages.getText(attributes[rowIndex].getDivOrg4Name());
                }else {
                    return  attributes[rowIndex].getDivOrg4Name();
                }                
            case 8:
                return messages.getText(attributes[rowIndex].getVehicleType());
            case 9:
                return attributes[rowIndex].getPlate();
            case 10:
                return messages.getText(attributes[rowIndex].getTrade());
            case 11:
                return attributes[rowIndex].getModel();
            case 12:
                return attributes[rowIndex].getMiles();
            case 13:
                return attributes[rowIndex].getLiters();
            case 14:
                return messages.getText(attributes[rowIndex].getFuel());
            case 15:
                return new Double(attributes[rowIndex].getMiles().doubleValue() / attributes[rowIndex].getLiters().doubleValue());
            case 16:
                color = !color;
                return new Boolean(color);
        }
        return null;
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "divOrg1Id";
            case 1:
                return "divOrg1Name";
            case 2:
                return "divOrg2Id";
            case 3:
                return "divOrg2Name";
            case 4:
                return "divOrg3Id";
            case 5:
                return "divOrg3Name";
            case 6:
                return "divOrg4Id";
            case 7:
                return "divOrg4Name";
            case 8:
                return "vehicleType";
            case 9:
                return "plate";
            case 10:
                return "trade";
            case 11:
                return "model";
            case 12:
                return "km";
            case 13:
                return "liters";
            case 14:
                return "fuel";
            case 15:
                return "average";
            case 16:
                return "color";
        }
        return null;
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }
    
}
