package communication.vehicle.report;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.table.AbstractTableModel;

import communication.language.Text;

public class ReportUnconformityData extends AbstractTableModel 
    implements Serializable {

	private static final long serialVersionUID = 1L;
	private Object[][] attributes;
    private boolean color = true;
    private Text messages;
    
    public ReportUnconformityData() {
        attributes = new Object[0][0];
    }

    public ReportUnconformityData(Collection data, Text messages) {
    	attributes = new Object[data.size()][4];
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
        return 4;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 3:
                color = !color;
                return new Boolean(color);
            default:
                return  attributes[rowIndex][columnIndex];
        }
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
            	return "id";
            case 1:
                return "resourcekey";
            case 2:
                return "count";
            case 3:
                return "color";
        }
        return null;
    }
}
