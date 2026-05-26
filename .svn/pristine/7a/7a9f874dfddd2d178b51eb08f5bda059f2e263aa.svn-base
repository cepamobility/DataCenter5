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
import java.util.Hashtable;

import javax.swing.table.AbstractTableModel;

import communication.language.Text;


public class TrailerWithoutCheckListData extends AbstractTableModel 
	implements Serializable {

    static final long serialVersionUID = 1L;
    
    int total;
    
    Object[][] attributeTrailers;
    Object[][] attributeVehicles;

    boolean color = true;
    Text messages;

    private Hashtable lastVehicles;
    private Hashtable lastTrailers;

    public TrailerWithoutCheckListData() {
        total = 0;
        attributeVehicles = new Object[0][0];
        lastVehicles = new Hashtable();
        attributeTrailers = new Object[0][0];
        lastTrailers = new Hashtable();
    }

    public TrailerWithoutCheckListData(Collection vehicles,Hashtable lastVehicles,Collection trailers,Hashtable lastTrailers,Text messages) {
        total = vehicles.size() + trailers.size();
        attributeVehicles = (Object[][])vehicles.toArray(new Object[0][0]);
        this.lastVehicles = lastVehicles;
        attributeTrailers = (Object[][])trailers.toArray(new Object[0][0]);
        this.lastTrailers = lastTrailers;
        this.messages = messages;
    }

    public int getRowCount() {
        return total;
    }

    public int getColumnCount() {
        return 12;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                if(rowIndex < attributeVehicles.length) {
                    return  attributeVehicles[rowIndex][0];
                }else {
                    return  attributeTrailers[rowIndex-attributeVehicles.length][0];                    
                }
            case 1:
                if(rowIndex < attributeVehicles.length) {
                    return  messages.getText((String) attributeVehicles[rowIndex][4]);
                }else {
                    return  messages.getText((String) attributeTrailers[rowIndex-attributeVehicles.length][4]);                    
                }
            case 2:
                if(rowIndex < attributeVehicles.length) {
                    if(attributeVehicles[rowIndex].length > 5) {
                        return  attributeVehicles[rowIndex][5];
                    }
                }else {
                    if(attributeTrailers[rowIndex-attributeVehicles.length].length > 5) {
                        return  attributeTrailers[rowIndex-attributeVehicles.length][5];                    
                    }
                }
            case 3:
                if(attributeVehicles[rowIndex].length > 6) {
                    if(rowIndex < attributeVehicles.length) {
                        return  attributeVehicles[rowIndex][6];
                    }else {
                        return  attributeTrailers[rowIndex-attributeVehicles.length][6];                    
                    }
                }
            case 4:
                if(attributeVehicles[rowIndex].length > 7) {
                    if(rowIndex < attributeVehicles.length) {
                        return  attributeVehicles[rowIndex][7];
                    }else {
                        return  attributeTrailers[rowIndex-attributeVehicles.length][7];                    
                    }
                }
            case 5:
                if(rowIndex < attributeVehicles.length) {
                    return  attributeVehicles[rowIndex][1];
                }else {
                    return  attributeTrailers[rowIndex-attributeVehicles.length][1];                    
                }
            case 6:
                if(rowIndex < attributeVehicles.length) {
                    if(attributeVehicles[rowIndex][2].equals("generic.messages.nodata")) {
                        return messages.getText("generic.messages.nodata");
                    }
                    return  attributeVehicles[rowIndex][2];
                }else {
                    if(attributeTrailers[rowIndex-attributeVehicles.length][2].equals("generic.messages.nodata")) {
                        return messages.getText("generic.messages.nodata");
                    }
                    return  attributeTrailers[rowIndex-attributeVehicles.length][2];
                }
            case 7:
                if(rowIndex < attributeVehicles.length) {
                    return  attributeVehicles[rowIndex][3];
                }else {
                    return  attributeTrailers[rowIndex-attributeVehicles.length][3];                    
                }
            case 8:
                color = !color;
                return new Boolean(color);
            case 9:
                if(rowIndex < attributeVehicles.length) {
                    if(lastVehicles.containsKey(attributeVehicles[rowIndex][0])) {
                        return formatDate((Date) lastVehicles.get(attributeVehicles[rowIndex][0]));
                    }else {
                        return "---";
                    }
                }else {
                    if(lastTrailers.containsKey(attributeTrailers[rowIndex-attributeVehicles.length][0])) {
                        return formatDate((Date) lastTrailers.get(attributeTrailers[rowIndex-attributeVehicles.length][0]));
                    }else {
                        return "---";
                    }
                }
            case 10:
                if(rowIndex < attributeVehicles.length) {
                    return  new Integer(attributeVehicles.length);
                }else {
                    return new Integer(attributeTrailers.length);                    
                }
                
            case 11:
                if(rowIndex < attributeVehicles.length) {
                    return  messages.getText("datatype.vehicletype.truck");
                }else {
                    return  messages.getText("vehicle.data.trailer");                    
                }
        }
        return null;
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "id";
            case 1:
                return "country";
            case 2:
                return "divOrg1Name";
            case 3:
                return "divOrg2Name";
            case 4:
                return "divOrg3Name";
            case 5:
                return "plate";
            case 6:
                return "trade";
            case 7:
                return "model";
            case 8:
                return "color";
            case 9:
                return "last";
            case 10:
                return "total";
            case 11:
                return "type";
        }
        return null;
    }

    private String formatDate(Date date) {
        if(date == null) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);
    }
}
