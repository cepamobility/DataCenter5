/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.vehicle.report;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.swing.table.AbstractTableModel;

import communication.language.Text;

public class ComplaintReportData extends AbstractTableModel 
	implements Serializable {

    static final long serialVersionUID = 1L;
    
    int total;
    Object[][] attributes;
    boolean color = true;
    Text messages;
    
    public ComplaintReportData() {
        attributes = new Object[0][0]; 
        total = attributes.length;
    }
    
    public ComplaintReportData(Collection complaints, int total, Text messages) {
        this.total = total; //reportDrivers.size();
        this.messages = messages;
        Iterator it = complaints.iterator();
        attributes = new Object[complaints.size()][19];
        int i = 0;
        while(it.hasNext()) {
        	attributes[i] = (Object[])it.next();
        	i++;
        }
    }

    public int getRowCount() {
        return attributes.length;
    }

    public int getColumnCount() {
        return 22;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 1:case 9:
                return formatDate(((Date)attributes[rowIndex][columnIndex]));
            case 2:
                return formatHour(((Date)attributes[rowIndex][columnIndex]));
            case 20:
                color = !color;
                return new Boolean(color);
            case 7:
            	return messages.getText((String)attributes[rowIndex][columnIndex]);
            case 21:
            	return new Integer(total);
            default:
                return String.valueOf(attributes[rowIndex][columnIndex]);
        }
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "id";
            case 1:
                return "date";
            case 2:
                return "hour";
            case 3:
                return "vehicle";
            case 4:
                return "matricula";
            case 5:
                return "location";
            case 6:
                return "description";
            case 7:
                return "callType";
            case 8:
                return "specification";
            case 9:
                return "reportDate";
            case 10:
                return "ticket";
            case 11:
                return "phoneId";
            case 12:
                return "agent";
            case 13:
                return "reporterName";
            case 14:
                return "reporterPhone";
            case 15:
                return "reporterMobile";
            case 16:
                return "reporterEmail";
            case 17:
                return "options";
            case 18:
                return "actions";
            case 19:
                return "prefijo";
            case 20:
                return "color";
            case 21:
                return "total";
        }
        return null;
    }
    
    public static String formatDate(Date date) {
        if(date == null) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);
    }

    public static String formatHour(Date time) {
        if(time == null) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        String minute = (calendar.get(Calendar.MINUTE) < 10) ? "0"+String.valueOf(calendar.get(Calendar.MINUTE)) : String.valueOf(calendar.get(Calendar.MINUTE)); 
        return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY))+":"+minute;
    }
}
