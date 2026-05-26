package communication.vehicle.report;

import java.io.Serializable;

import javax.swing.table.AbstractTableModel;

import communication.language.Text;

public class ReportVehiclePursuitRouteData extends AbstractTableModel 
    implements Serializable {

	private static final long serialVersionUID = 1L;
	private VehiclePursuitReportDataType[] attributes;
    private boolean color = true;
    private Text messages;
    
    public ReportVehiclePursuitRouteData() {
        attributes = new VehiclePursuitReportDataType[0];
    }

    public ReportVehiclePursuitRouteData(VehiclePursuitReportDataType[] data, Text messages) {
        attributes = data;
        this.messages = messages;
    }

    public int getRowCount() {
        return attributes.length;
    }

    public int getColumnCount() {
        return 47;
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
            	return attributes[rowIndex].getId();
            case 8:
            	return attributes[rowIndex].getDate();
            default:
                return new Integer(attributes[rowIndex].getCount((columnIndex-9)));
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
            	return "id";
            case 8:
            	return "date";
            default:
                return "check"+String.valueOf(column-9);
        }
    }

    public int getCheckCount() {
        return 4;
    }
}
