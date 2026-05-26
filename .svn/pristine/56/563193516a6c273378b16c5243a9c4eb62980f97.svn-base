/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.activity.report;


import java.io.Serializable;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import communication.activity.tables.OldResultData;
import communication.language.Text;

public class ActivityReportOldData extends AbstractTableModel	implements Serializable {

    static final long serialVersionUID = 1L;
    
    int total;
    OldResultData[] attributes;
    boolean color = true;
    
    Text messages;
    
    public ActivityReportOldData() {
        total = 0;
        attributes = new OldResultData[0]; 
    }
    
    public ActivityReportOldData(Collection reportDrivers) {
        total = reportDrivers.size();
        attributes = (OldResultData[])reportDrivers.toArray(new OldResultData[0]);
    }

    public int getRowCount() {
        return total;
    }

    public int getColumnCount() {
        //return 2;
    	return 1;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                //return messages.getText(attributes[rowIndex].getPoint().getText());
            	return (attributes[rowIndex].getTextoinformetexto() != null &&
            			attributes[rowIndex].getTextoinformetexto().trim() != "null")?
            			messages.getText(attributes[rowIndex].getTextoinformetexto()):"";                               
            case 1:
                color = !color;
                return new Boolean(color);
               
        }
        return null;
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "item";
         case 1:
                return "color";
             
        }
        return null;
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }
}
