/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.queries.report;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.table.AbstractTableModel;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import communication.language.Text;

public class ReportFineTypeData extends AbstractTableModel 
	implements Serializable {

    static final long serialVersionUID = 1L;
    
    private int total;
    private Object[][] attributes;
    private boolean color = true;

    private Hashtable divisions;
    private Hashtable subdivisions;
    private Hashtable units;

    private Text messages;

    public ReportFineTypeData() {
        total = 0;
    }
    
    public ReportFineTypeData(Object[][] details, Hashtable unitChart, Hashtable divisionChart, Hashtable subdivisionChart) {
        total = details.length;
        attributes = details;
        units = unitChart;
        divisions = divisionChart;
        subdivisions = subdivisionChart;
    }

    public int getRowCount() {
        return total;
    }

    public int getColumnCount() {
        return 8;
    }

    public void setMessages(Text messages) {
        this.messages = messages;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 3:
                return messages.getText((String)attributes[rowIndex][columnIndex]);
            case 6:
                color = !color;
                return new Boolean(color);
            case 7:
                return new Integer(1);
            default:
                return attributes[rowIndex][columnIndex];
        }
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "unit";
            case 1:
                return "division";
            case 2:
                return "subdivision";
            case 3:
                return "tipo";
            case 4:
                return "cantidad";
            case 5:
                return "costo";
            case 6:
                return "color";
            case 7:
                return "id";
        }
        return "unit";
    }
    
    public CategoryDataset getDataset(int type) {
        final DefaultCategoryDataset result = new DefaultCategoryDataset();
        Enumeration enumeration = null;
        switch(type) {
            case 0:
                if(units.size() == 0) {
                    return null;
                }
                enumeration = units.elements();
                break;
            case 1:
                enumeration = divisions.elements();
                break;
            case 2:
                enumeration = subdivisions.elements();
                break;
        }
        while(enumeration.hasMoreElements()) {
            Object[] data = (Object[])enumeration.nextElement();
            result.setValue((Integer)data[1],(String)data[0], (String)data[2]);
        }
        return result;
    }

    public boolean isUnit() {
        return (units.size() > 1);
    }

    public boolean isDivision() {
        return (divisions.size() > 1);
    }

    public boolean isSubdivision() {
        return (subdivisions.size() > 1);
    }
}
