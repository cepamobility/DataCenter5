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

public class ReportCimsaCheckListData extends AbstractTableModel 
    implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CimsaCheckListData[] attributes;
	private ItemData[] items;
    private boolean color = true;
    private Text messages;
    
    public ReportCimsaCheckListData() {
        attributes = new CimsaCheckListData[0];
    }

    public ReportCimsaCheckListData(Collection data, ItemData[] items, Text messages) {
    	Collections.sort((List)data);
        attributes = (CimsaCheckListData[])data.toArray(new CimsaCheckListData[0]);
        this.messages = messages;
        this.items = items;
    }

    public int getRowCount() {
        return attributes.length;
    }

    public int getColumnCount() {
        return items.length+5;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return  attributes[rowIndex].getContractor();
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
            default:
            	switch(items[columnIndex-5].getId()) {
            		case 4802: case 4803: case 4804: case 4805: case 4806: case 4795:
            			return  attributes[rowIndex].getValue(columnIndex-5);
					case 4770:	
						switch(Integer.parseInt(attributes[rowIndex].getValue(columnIndex-5))) {
							case 1:
								return "Refresco";
							case 2:
								return "Agua";
							case 3:
								return "JDV";
							case 4:
								return "CFDLA";
							case 5:
								return "No tiene";
							default:
								return "---";
						}
            		default:
            			if(Integer.parseInt(attributes[rowIndex].getValue(columnIndex-5)) == 1) {
            				return "O";
            			}else {
            				return "X";
            			}
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
            default:
                return "check"+String.valueOf(column-5);
        }
    }

    public int getCheckCount() {
        return 14;
    }
}
