/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.activity.report;

import java.io.Serializable;

import javax.swing.table.AbstractTableModel;

import communication.language.Text;

public class ActivityGeneralReportData extends AbstractTableModel 
	implements Serializable {

    static final long serialVersionUID = 1L;
    
    private Object[][] attributes;
    private boolean color = true;
    private Text messages;
    
    public ActivityGeneralReportData() {
        attributes = new Object[0][0]; 
    }
    
    public ActivityGeneralReportData(Object[][] attributes,Text messages) {
        this.attributes = attributes;
        this.messages = messages;
    }

    public int getRowCount() {
        return attributes.length;
    }

    public int getColumnCount() {
        //return 6;
    	return 7;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:case 1:case 2:
                return attributes[rowIndex][columnIndex];
            case 3:
                return messages.getText((String) attributes[rowIndex][columnIndex]);
            case 4:                
                return String.valueOf(attributes[rowIndex][columnIndex]);
            case 5:                
                return String.valueOf(attributes[rowIndex][columnIndex]);                
            case 6:    
                color = !color;
                return new Boolean(color);
        }
        return null;
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "firstname";
            case 1:
                return "lastname";
            case 2:
                return "birthdate";
            case 3:
                return "note";
            case 4:
                return "id";
            case 5:
                return "idActivity";                
            case 6:
                return "color";                
        }
        return null;
    }
}
