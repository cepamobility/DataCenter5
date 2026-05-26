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

public class ReportSyngentaCheckListData extends AbstractTableModel 
    implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[][] attributes;
    private boolean color = true;
    
    public ReportSyngentaCheckListData() {
        attributes = new String[0][0];
    }

    public ReportSyngentaCheckListData(String[][] data) {
        attributes = data;
    }

    public int getRowCount() {
        return attributes.length;
    }

    public int getColumnCount() {
        return 4;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0: case 1: case 2:
                return  attributes[rowIndex][columnIndex];
            case 3:
                color = !color;
                return new Boolean(color);
        }
        return null;
    }

    public String getColumnName(int column) {
        switch(column) {
	        case 0:
	            return  "data";
	        case 1:
	            return  "result";
	        case 2:
	            return  "observations";
	        case 3:
                return "color";
        }
        return null;
    }

}
