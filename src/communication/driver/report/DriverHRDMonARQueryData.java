package communication.driver.report;

import java.io.Serializable;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import communication.driver.tables.DriverData;
import communication.language.Text;

public class DriverHRDMonARQueryData extends AbstractTableModel 
    implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total = 0;
    private int cantReg = 0;
    private DriverReportDataType[] attributes;
    private boolean color = true;
    private Text messages;

    private int[][] principal;
    
    public DriverHRDMonARQueryData() {
        total = 0;
        attributes = new DriverReportDataType[0];
        principal = new int[3][2];
        cantReg = 0;
    }
    
    public DriverHRDMonARQueryData(Collection reportDrivers,int total, int[][] principal) {
//       this.cantReg = reportDrivers.size();
        attributes = (DriverReportDataType[])reportDrivers.toArray(new DriverReportDataType[0]);
        this.cantReg = attributes.length;
        this.principal = principal;
        this.total = total;
    }

    public int getRowCount() {
        return cantReg;
    }

    public int getColumnCount() {
        return 15;
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
            	result = attributes[rowIndex].getHrdCondition(1);
                return (result != null)?result:"";
            case 6:           
            	// Condicion 2
            	result = attributes[rowIndex].getHrdCondition(2);
                return (result != null)?result:"";
            case 7:            	
            	// Condicion 3
            	result = attributes[rowIndex].getHrdCondition(3);
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
            	// Condicion 4
            	// result = attributes[rowIndex].getHrdCondition(4);
                // return (result != null)?result:"";
            	return "";
            case 12:
            	// Condicional
                return (attributes[rowIndex].isConditional()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");            
            case 13:
                result = attributes[rowIndex].getData(DriverData.DIV_ORG,1,1);
                return (result != null)?result:"";
            case 14:
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
            	return "hrdConditions1";
            case 6:
            	return "hrdConditions2";
            case 7:
            	return "hrdConditions3";
            case 8:
            	return "country";
            case 9:
            	return "color";
            case 10:
            	return "total";
            case 11:
            	return "hrdConditions4";            	
            case 12:
            	return "conditional";
            case 13:
                return "divorg11";
            case 14:
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
