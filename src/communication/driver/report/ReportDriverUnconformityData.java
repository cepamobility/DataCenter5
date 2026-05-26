package communication.driver.report;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import presentation.translator.web.util.Util;

import communication.language.Text;

public class ReportDriverUnconformityData extends AbstractTableModel 
    implements Serializable {

	private static final long serialVersionUID = 1L;
	private Object[][] attributes;
    private boolean color = true;
    private Text messages;
    
    public ReportDriverUnconformityData() {
        attributes = new Object[0][0];
    }

    public ReportDriverUnconformityData(Object[][] data, Text messages) {
    	attributes = data;
        this.messages = messages;
    }

    public int getRowCount() {
        return attributes.length;
    }

    public int getColumnCount() {
        return 6;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
    		case 0:
    			return  attributes[rowIndex][columnIndex];
    		case 1:
    			return  attributes[rowIndex][columnIndex]+" "+attributes[rowIndex][columnIndex+1];
    		case 2:
                return  formatDate((Date)attributes[rowIndex][columnIndex+1]);
    		case 3: 
                return  messages.getText((String)attributes[rowIndex][columnIndex+1]);
    		case 4:
                return  attributes[rowIndex][columnIndex+1];
        	case 5:
                color = !color;
                return new Boolean(color);
        }
        return null;
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
            	return "id";
            case 1:
                return "driver";
            case 2:
                return "date";
            case 3:
                return "type";
            case 4:
                return "observations";
            case 5:
                return "color";
        }
        return null;
    }

    public String formatDate(Date date) {
        if(date == null) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);
    }
}
