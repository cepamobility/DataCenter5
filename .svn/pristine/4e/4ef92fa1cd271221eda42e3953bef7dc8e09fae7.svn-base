package communication.vehicle.report;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.table.AbstractTableModel;

import communication.language.Text;

public class ReportDetailedUnconformityData extends AbstractTableModel 
    implements Serializable {

	private static final long serialVersionUID = 1L;
	private Object[][] attributes;
    private boolean color = true;
    private Text messages;
    
    public ReportDetailedUnconformityData() {
        attributes = new Object[0][0];
    }

    public ReportDetailedUnconformityData(Collection data, Text messages) {
    	attributes = new Object[data.size()][8];
    	Iterator it = data.iterator();
    	int i = 0;
    	while(it.hasNext()) {
    		attributes[i] = (Object[])it.next();
    		i++;
    	}
        this.messages = messages;
    }

    public int getRowCount() {
        return attributes.length;
    }

    public int getColumnCount() {
        return 9;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 8:
                color = !color;
                return new Boolean(color);
            default:
                return  attributes[rowIndex][columnIndex];
        }
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "matricula";
            case 1:
                return "date";
            case 2:
                return "origin";
            case 3:
                return "department";
            case 4:
                return "charge";
            case 5:
                return "observations";
            case 6:
            	return "id";
            case 7:
                return "resourcekey";
            case 8:
                return "color";
        }
        return null;
    }
}
