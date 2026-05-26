package communication.driver.report;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import communication.driver.tables.DriverData;
import communication.language.Text;

public class DriverPHRDQueryData extends AbstractTableModel 
    implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total;
    private int cant = 0;
    private DriverReportDataType[] attributes;
    private boolean color = true;
    private boolean heavy = false;
    private Text messages;
    Format dateFormat = new SimpleDateFormat("dd-MM-yyyy");	

    private int[][] principal;
    
    public DriverPHRDQueryData() {
        total = 0;
        cant = 0;
        attributes = new DriverReportDataType[0];
        principal = new int[4][2];
        heavy = false;
    }
    
    public DriverPHRDQueryData(Collection reportDrivers,int total, int[][] principal,boolean heavy) {
        cant = reportDrivers.size();
        attributes = (DriverReportDataType[])reportDrivers.toArray(new DriverReportDataType[0]);
        this.principal = principal;
        this.total = total;
        this.heavy = heavy;
    }

    public int getRowCount() {
        return cant;
    }

    public int getColumnCount() {
        return 20;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
    	String result;
        switch(columnIndex) {
            case 0:
                return String.valueOf(attributes[rowIndex].getId());
            case 1:
                result = attributes[rowIndex].getFirstname();
                return (result != null)?result:"";
            case 2:
            	result = attributes[rowIndex].getData(DriverData.DIV_ORG,principal[0][0],principal[0][1]);
                return (result != null)?result:"";
            case 3:
                result =  attributes[rowIndex].getData(DriverData.DIV_ORG,principal[1][0],principal[1][1]);
                return (result != null)?result:"";
            case 4:
                result = attributes[rowIndex].getData(DriverData.DIV_ORG,principal[2][0],principal[2][1]);
                return (result != null)?result:"";
            case 5:            	
            	// Condicion 1
            	result = attributes[rowIndex].getPhrdCondition(1);
                return (result != null)?result:"";
            case 6:           
            	// Condicion 2
            	result = attributes[rowIndex].getPhrdCondition(2);
                return (result != null)?result:"";
            case 7:            	
            	// Condicion 3
            	result = attributes[rowIndex].getPhrdCondition(3);
                return (result != null)?result:"";
            case 8:
            	result = messages.getText(attributes[rowIndex].getCountry());
            	return (result != null)?result:"";
            case 9:
                color = !color;
                return new Boolean(color);
            case 10:
            	return new Integer(total);
            case 11:
            	// Condicion 3
            	result = attributes[rowIndex].getPhrdCondition(4);
                return (result != null)?result:"";
            case 12:
            	// Condicion 5
            	result = attributes[rowIndex].getPhrdCondition(5);
                return (result != null)?result:"";
            case 13:
            	// Condicion 6
            	result = attributes[rowIndex].getPhrdCondition(6);
                return (result != null)?result:"";
            case 14:
            	// Score
            	result = (heavy) ? new Integer(attributes[rowIndex].getPhrdHeavyScore()).toString() : new Integer(attributes[rowIndex].getPhrdScore()).toString();
                return (result != null)?result:"";
            case 15:
            	result = dateFormat.format(attributes[rowIndex].getLastUpdate());
            	return (result != null)?result:"";
            case 16:
            	// Condicion 7 : Phrd por curso
            	result = attributes[rowIndex].getPhrdCondition(7);
                return (result != null)?result:"";
            case 17:
                result = attributes[rowIndex].getData(DriverData.DIV_ORG,principal[3][0],principal[3][1]);
                return (result != null)?result:"";
            case 18:
                return attributes[rowIndex].getLastHRD();
            case 19:
                result = attributes[rowIndex].getLastname();
                return (result != null)?result:"";
        }
        return "";
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "id";
            case 1:
                return "firstname";
            case 2:
                return "divorg1";
            case 3:
                return "divorg2";
            case 4:
                return "divorg3";
            case 5:
            	return "phrdConditions1";
            case 6:
            	return "phrdConditions2";
            case 7:
            	return "phrdConditions3";
            case 8:
            	return "country";
            case 9:
            	return "color";
            case 10:
            	return "total";
            case 11:
            	return "phrdConditions4";
            case 12:
            	return "phrdConditions5";
            case 13:
            	return "phrdConditions6";
            case 14:
            	return "score";
            case 15:
            	return "lastUpdate";
            case 16:
            	return "phrdConditions7";
            case 17:
                return "divorg4";
            case 18:
                return "lastHRD";
            case 19:
                return "lastname";
        }
        return "";
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }
    
    public Text getMessages(){
    	return this.messages;
    }

	/**
	 * @return Returns the total.
	 */
	public int getTotal() {
		return total;
	}
	
    
    
}
