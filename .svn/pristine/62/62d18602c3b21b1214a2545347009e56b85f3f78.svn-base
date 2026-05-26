/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.vehicle.report;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import business.SystemAccess;

import communication.language.Text;
import communication.vehicle.tables.VehicleDataText;

public class ContractReportData extends AbstractTableModel 
    implements Serializable {

        static final long serialVersionUID = 1L;
	
    private String[][] attributes;
    private Text messages;
    
    
	public ContractReportData() {
        attributes = new String[0][0]; 
    }
	
    public int getRowCount() {
        return attributes.length;
    }    
    
    public ContractReportData(SystemAccess sys,String[][] data) {
        messages = sys.getText();
        attributes = data;	
    }

    public int getColumnCount() {
        return 1;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0) {
        	return attributes[rowIndex][0];
        }
        return null;
    }

    public String getColumnName(int column) {
        if(column == 0) {
        	return "item";
        }
        return null;
    }

}
