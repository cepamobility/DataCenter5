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

public class ReportAPMMByPeriodData extends AbstractTableModel 
	implements Serializable {

    static final long serialVersionUID = 1L;
    
    private int total;
    private Object[][] attributes;
    private boolean color = true;
    private Text messages;

    private Hashtable divisions;
    private Hashtable subdivisions;
    private Hashtable units;

    public ReportAPMMByPeriodData() {
        total = 0;
        attributes = new Object[0][0]; 
    }
    
    public ReportAPMMByPeriodData(Object[][] details,Hashtable units,Hashtable divisions,Hashtable subdivisions) {
        total = details.length;
        attributes = details;
        this.units = units;
        this.divisions = divisions;
        this.subdivisions = subdivisions;
    }

    public int getRowCount() {
        return total;
    }

    public int getColumnCount() {
        return 7;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:case 1:case 2:
                return attributes[rowIndex][columnIndex];
            case 3:
                return new Double(((Integer)attributes[rowIndex][4]).doubleValue() * 1000000 / ((Integer)attributes[rowIndex][3]).doubleValue());
            case 4:
                color = !color;
                return new Boolean(color);
            case 5:
                return attributes[rowIndex][3];
            case 6:
                return attributes[rowIndex][4];
        }
        return null;
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
                return "apmm";
            case 4:
                return "color";
            case 5:
                return "miles";
            case 6:
                return "accidents";
        }
        return null;
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }
    
    public CategoryDataset getUnitsDataset() {
        final DefaultCategoryDataset result = new DefaultCategoryDataset();
        Enumeration enumeration = units.elements();
        while(enumeration.hasMoreElements()) {
            Object[] data = (Object[])enumeration.nextElement();
            if(((Integer)data[1]).intValue() != 0) {
                result.setValue(((Integer)data[2]).intValue() * 1000000 / ((Integer)data[1]).intValue(),"APMM", (String)data[0]);
            }else {
                result.setValue(0,"APMM", (String)data[0]);
            }
        }
        return result;
    }

    public CategoryDataset getDivisionsDataset() {
        final DefaultCategoryDataset result = new DefaultCategoryDataset();
        Enumeration enumeration = divisions.elements();
        while(enumeration.hasMoreElements()) {
            Object[] data = (Object[])enumeration.nextElement();
            if(((Integer)data[1]).intValue() != 0) {
                result.setValue(((Integer)data[2]).intValue() * 1000000 / ((Integer)data[1]).intValue(),"APMM", (String)data[0]);
            }else {
                result.setValue(0,"APMM", (String)data[0]);
            }
        }
        return result;
    }

    public CategoryDataset getSubdivisionsDataset() {
        final DefaultCategoryDataset result = new DefaultCategoryDataset();
        Enumeration enumeration = subdivisions.elements();
        while(enumeration.hasMoreElements()) {
            Object[] data = (Object[])enumeration.nextElement();
            if(((Integer)data[1]).intValue() != 0) {
                result.setValue(((Integer)data[2]).intValue() * 1000000 / ((Integer)data[1]).intValue(),"APMM", (String)data[0]);
            }else {
                result.setValue(0,"APMM", (String)data[0]);
            }
        }
        return result;
    }
    
    public boolean isUnits() {
        return (units.size() > 1);
    }

    public boolean isDivisions() {
        return (divisions.size() > 1);
    }

    public boolean isSubdivisions() {
        return (subdivisions.size() > 1);
    }
}
