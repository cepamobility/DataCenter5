/**
 * Created on 26-jul-2007
 * CEPA
 * DataCenter 5
 */
package communication.vehicle.report;

import java.io.Serializable;

import javax.swing.table.AbstractTableModel;

import communication.language.Text;

public class ReportCheckListData extends AbstractTableModel 
    implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VehicleCheckReportDataType[] attributes;
    private boolean color = true;
    private Text messages;
    
    public ReportCheckListData() {
        attributes = new VehicleCheckReportDataType[0];
    }

    public ReportCheckListData(VehicleCheckReportDataType[] data, Text messages) {
        attributes = data;
        this.messages = messages;
    }

    public int getRowCount() {
        return attributes.length;
    }

    public int getColumnCount() {
        return 22;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return  attributes[rowIndex].getName();
            case 1:
                return  attributes[rowIndex].getPlate();
            case 2:
                return  attributes[rowIndex].getType();
            case 3:
                return  new Integer(attributes[rowIndex].getCheckCount());
            case 4:
                color = !color;
                return new Boolean(color);
            case 5:
                return  messages.getText("generic.messages.total") + " " + attributes[rowIndex].getName();
            case 6:
                return  messages.getText("generic.messages.total") + " " + attributes[rowIndex].getType();
            case 7:
                return  String.valueOf(attributes[rowIndex].getVehicleId());
            default:
                return new Integer(attributes[rowIndex].getCount((columnIndex-8)));
        }
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "name";
            case 1:
                return "plate";
            case 2:
                return "type";
            case 3:
                return "count";
            case 4:
                return "color";
            case 5:
                return "totalname";
            case 6:
                return "totaltype";
            case 7:
                return "vehicleId";
            default:
                return "check"+String.valueOf(column-8);
        }
    }

    public int getCheckCount() {
        return 14;
    }
}
