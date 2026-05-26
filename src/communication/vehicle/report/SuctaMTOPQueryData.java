package communication.vehicle.report;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import communication.language.Text;
import communication.vehicle.tables.SuctaMTOPData;

public class SuctaMTOPQueryData extends AbstractTableModel 
	implements Serializable {

    static final long serialVersionUID = 1L;
    
    int total;
    SuctaMTOPData [] attributes;    
    boolean color = true;   
    Text messages;
    
    public SuctaMTOPQueryData() {
        attributes = new SuctaMTOPData[0]; 
        total = attributes.length;
    }
    
    public SuctaMTOPQueryData(Collection reportDrivers, int total) {
        this.total = total; //reportDrivers.size();
        attributes = (SuctaMTOPData[])reportDrivers.toArray(new SuctaMTOPData[0]);       
    }

    public int getRowCount() {
        return attributes.length;
    }

    public int getColumnCount() {
        return 17;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
    	Calendar calendar;
        switch(columnIndex) {
            case 0:
                return attributes[rowIndex].getPlate();
            case 1:
                color = !color;
                return new Boolean(color);
            case 2:
                return messages.getText(attributes[rowIndex].getCountryName());
            case 3:
            	calendar = Calendar.getInstance();
                calendar.setTime(attributes[rowIndex].getMtopExpiry());
                return calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);           
            case 4:
            	calendar = Calendar.getInstance();
                calendar.setTime(attributes[rowIndex].getSuctaExpiry());
                return calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);           
            case 5:
            	calendar = Calendar.getInstance();
                calendar.setTime(attributes[rowIndex].getExclusionDate());
                return calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);           
            case 6:
                return attributes[rowIndex].getData(1,1);
            case 7:
                return attributes[rowIndex].getData(1,2);
            case 8:
                return attributes[rowIndex].getData(1,3);
            case 9:
                return attributes[rowIndex].getData(2,1);
            case 10:
                return attributes[rowIndex].getData(2,2);
            case 11:
                return attributes[rowIndex].getData(2,3);
            case 12:
                return attributes[rowIndex].getData(3,1);
            case 13:
                return attributes[rowIndex].getData(3,2);
            case 14:
                return attributes[rowIndex].getData(3,3);           
            case 15:
            	return new Integer(total);   
            case 16:
            	return String.valueOf(attributes[rowIndex].getId());
        }
        return null;
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "plate";
            case 1:
                return "color";
            case 2:
                return "country";
            case 3:
                return "mtop";
            case 4:
                return "sucta";
            case 5:
                return "exclusiondate";           
            case 6:
                return "divorg11";
            case 7:
                return "divorg12";
            case 8:
                return "divorg13";
            case 9:
                return "divorg21";
            case 10:
                return "divorg22";
            case 11:
                return "divorg23";
            case 12:
                return "divorg31";
            case 13:
                return "divorg32";
            case 14:
                return "divorg33";
            case 15:
            	return "total";
            case 16:
            	return "id";
        }
        return null;
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }
}
