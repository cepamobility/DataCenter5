/**
 * Created on 26-jul-2007
 * CEPA
 * DataCenter 5
 */
package communication.vehicle.report;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import presentation.translator.web.util.Util;

import communication.activity.tables.ItemData;
import communication.language.Text;
import communication.vehicle.tables.CimsaCheckListData;

public class ReportSyngentaCLData extends AbstractTableModel 
    implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CimsaCheckListData[] attributes;
	private ItemData[] items;
    private boolean color = true;
    private Text messages;
    
    public ReportSyngentaCLData() {
        attributes = new CimsaCheckListData[0];
    }

    public ReportSyngentaCLData(Collection data, ItemData[] items, Text messages) {
    	Collections.sort((List)data);
        attributes = (CimsaCheckListData[])data.toArray(new CimsaCheckListData[0]);
        this.messages = messages;
        this.items = items;
    }

    public int getRowCount() {
        return attributes.length;
    }

    public int getColumnCount() {
        return items.length+9;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return  attributes[rowIndex].getName();
            case 1:
                return  attributes[rowIndex].getPlate();
            case 2:
                return  messages.getText(attributes[rowIndex].getType());
            case 3:
                Date date = attributes[rowIndex].getDate();
                if(date == null) {
                	return "---";
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                return calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);
            case 4:
                color = !color;
                return new Boolean(color);
            case 5:
                return  attributes[rowIndex].getObs();
            case 6:
                return  attributes[rowIndex].getValues(2,items.length-8);
            case 7:
                return  attributes[rowIndex].getValues(1,items.length-8);
            case 8:
                return  attributes.length;
            default:
    			if(Integer.parseInt(attributes[rowIndex].getValue(columnIndex-9)) == 1) {
    				return "X";
    			}else {
    				return "-";
    			}
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
                return "date";
            case 4:
                return "color";
            case 5:
                return "obs";
            case 6:
                return "correct";
            case 7:
                return "incorrect";
            case 8:
                return "totalchecks";
            default:
                return "check"+String.valueOf(column-9);
        }
    }

    public int getCheckCount() {
        return 14;
    }
}
