/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.queries.report;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import communication.event.util.EventCountData;


public class ReportAccidentCountData extends AbstractTableModel 
	implements Serializable {

    static final long serialVersionUID = 1L;
    
    private int total;
    private EventCountData[] attributes;
    private boolean color = true;
    private Hashtable secondlevel;
    private Hashtable thirdlevel;
    private Hashtable firstlevel;

    public ReportAccidentCountData() {
        total = 0;
    }
    
    public ReportAccidentCountData(Collection details, Hashtable firstLevelChart, Hashtable secondLevelChart, Hashtable thirdLevelChart) {
        total = details.size();
        Collections.sort((List) details);
        attributes = (EventCountData[])details.toArray(new EventCountData[0]);
        firstlevel = firstLevelChart;
        secondlevel = secondLevelChart;
        thirdlevel = thirdLevelChart;
    }

    public int getRowCount() {
        return total;
    }

    public int getColumnCount() {
        return 20;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
    	
        switch(columnIndex) {
            case 0:
                return attributes[rowIndex].getDiv1Id();
            case 1:
                return attributes[rowIndex].getDiv1Name();
            case 2:
                return attributes[rowIndex].getDiv2Id();
            case 3:
                return attributes[rowIndex].getDiv2Name();
            case 4:
                return attributes[rowIndex].getDiv3Id();
            case 5:
                return attributes[rowIndex].getDiv3Name();
            case 6:
                return attributes[rowIndex].getDiv4Id();
            case 7:
                return attributes[rowIndex].getDiv4Name();
            case 9:
                return attributes[rowIndex].getAccidentCount();
            case 10:
                return attributes[rowIndex].getAccidentCost();
            case 11:
                return attributes[rowIndex].getIncidentCount();
            case 12:
                return attributes[rowIndex].getIncidentCost();
            case 13:
                return attributes[rowIndex].getFineCount();
            case 14:
                return attributes[rowIndex].getFineCost();
            case 15:
                color = !color;
                return new Boolean(color);
            case 16:
                return new Integer(1);
            case 17:
                int count = (attributes[rowIndex].getAccidentCost() == null) ? 0 : attributes[rowIndex].getAccidentCost().intValue();
                count += (attributes[rowIndex].getIncidentCost() == null) ? 0 : attributes[rowIndex].getIncidentCost().intValue();
                count += (attributes[rowIndex].getFineCost() == null) ? 0 : attributes[rowIndex].getNearmissCost().intValue();
                count += (attributes[rowIndex].getFineCost() == null) ? 0 : attributes[rowIndex].getFineCost().intValue();
                return new Integer(count);
            case 18:
                return attributes[rowIndex].getNearmissCount();
            case 19:
                return attributes[rowIndex].getNearmissCost();
        }
        return null;
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "div1Id";
            case 1:
                return "div1Name";
            case 2:
                return "div2Id";
            case 3:
                return "div2Name";
            case 4:
                return "div3Id";
            case 5:
                return "div3Name";
            case 6:
                return "div4Id";
            case 7:
                return "div4Name";
            case 8:
                return "thirdLevel";
            case 9:
                return "accidentCount";
            case 10:
                return "accidentCost";
            case 11:
                return "incidentCount";
            case 12:
                return "incidentCost";
            case 13:
                return "fineCount";
            case 14:
                return "fineCost";
            case 15:
                return "color";
            case 16:
                return "id";
            case 17:
                return "totalCost";
            case 18:
                return "nearmissCount";
            case 19:
                return "nearmissCost";
        }
        return null;
    }
    
    public CategoryDataset getSecondLevelDataset() {
        final DefaultCategoryDataset result = new DefaultCategoryDataset();
        Enumeration enumeration = secondlevel.elements();
        while(enumeration.hasMoreElements()) {
            Object[] data = (Object[])enumeration.nextElement();
            result.setValue((Integer)data[1],(String)data[0], "");
        }
        return result;
    }

    public CategoryDataset getThirdLevelDataset() {
        final DefaultCategoryDataset result = new DefaultCategoryDataset();
        Enumeration enumeration = thirdlevel.elements();
        while(enumeration.hasMoreElements()) {
            Object[] data = (Object[])enumeration.nextElement();
            result.setValue((Integer)data[1], (String)data[0], "");
        }
        return result;
    }

    public CategoryDataset getFirstLevelDataset() {
        final DefaultCategoryDataset result = new DefaultCategoryDataset();
        Enumeration enumeration = firstlevel.elements();
        while(enumeration.hasMoreElements()) {
            Object[] data = (Object[])enumeration.nextElement();
            result.setValue((Integer)data[1],(String)data[0], "");
        }
        return result;
    }
}
