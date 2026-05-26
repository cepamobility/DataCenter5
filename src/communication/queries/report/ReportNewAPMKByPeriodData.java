/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.queries.report;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import communication.fleet.util.APMMData;

public class ReportNewAPMKByPeriodData extends AbstractTableModel 
	implements Serializable {

    static final long serialVersionUID = 1L;
    
    private APMMData[] attributes;
    private boolean color = true;

    private int periods;

    public ReportNewAPMKByPeriodData() {
        attributes = new APMMData[0]; 
    }
    
    public ReportNewAPMKByPeriodData(Collection collection, int periods) {
        Collections.sort((List) collection);
        this.attributes = (APMMData[])collection.toArray(new APMMData[0]);
        this.periods = periods;
    }

    public int getRowCount() {
        return attributes.length;
    }

    public int getColumnCount() {
        return periods * 3 + 9;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return attributes[rowIndex].getDivOrg1Id();
            case 1:
                return attributes[rowIndex].getDivOrg1Name();
            case 2:
                return attributes[rowIndex].getDivOrg2Id();
            case 3:
                return attributes[rowIndex].getDivOrg2Name();
            case 4:
                return attributes[rowIndex].getDivOrg3Id();
            case 5:
                return attributes[rowIndex].getDivOrg3Name();
            case 6:
                return "color";
            case 7:
                return attributes[rowIndex].getDivOrg4Id();
            case 8:
                return attributes[rowIndex].getDivOrg4Name();
            default:
                if(columnIndex < periods + 9) {
                    return attributes[rowIndex].getAPMK(columnIndex-9);
                }
                if(columnIndex < periods * 2 + 9) {
                    return attributes[rowIndex].getAccidents(columnIndex-(periods+9));
                }else {
                    return attributes[rowIndex].getKms(columnIndex-(periods*2+9));
                }
        }
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "divOrg1Id";
            case 1:
                return "divOrg1Name";
            case 2:
                return "divOrg2Id";
            case 3:
                return "divOrg2Name";
            case 4:
                return "divOrg3Id";
            case 5:
                return "divOrg3Name";
            case 6:
                return "color";
            case 7:
                return "divOrg4Id";
            case 8:
                return "divOrg4Name";
            default:
                if(column < periods + 9) {
                    return "apmm"+String.valueOf(column-9);
                }
                if(column < periods * 2 + 9) {
                    return "accidents"+String.valueOf(column-(periods+9));
                }else {
                    return "miles"+String.valueOf(column-(periods*2+9));
                }
        }
    }

    public APMMData[] getData() {
        return attributes;
    }
}
