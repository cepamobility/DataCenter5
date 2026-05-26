package communication.driver.report;

import java.io.Serializable;
import java.util.StringTokenizer;

import javax.swing.table.AbstractTableModel;

import communication.language.Text;
import communication.util.ListResult;

public class HistoryQueryData extends AbstractTableModel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total = 0;
    private int cantReg = 0;
    private HistoryDataType[] attributes;
    private boolean color = true;
    private Text messages;
    private Integer lastId = new Integer(0);
	private boolean excel;

    public HistoryQueryData() {
        total = 0;
        attributes = new HistoryDataType[0];
        cantReg = 0;
    }
    
    public HistoryQueryData(ListResult result, Text messages, boolean excel) {
        attributes = (HistoryDataType[])result.getElements().toArray(new HistoryDataType[0]);
        this.cantReg = attributes.length;
        this.total = result.getTotal();
        this.messages = messages;
        this.excel = excel;
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
            	if (!lastId.equals(attributes[rowIndex].getId())){	            		
            		result = attributes[rowIndex].getFirstname();
            	}else{
            		result = "";
            	}	            		
                return (result != null)?result:"";
            case 2:
            	if (lastId.equals(attributes[rowIndex].getId()))
            		result = "";
            	else
            		result = attributes[rowIndex].getDivorg1();
                return (result != null) ? result : "";
            case 3:
            	if (lastId.equals(attributes[rowIndex].getId()))
            	 	result = "";
            	else
                    result = attributes[rowIndex].getDivorg2();
                return (result != null)?result:"";
            case 4:
            	if (lastId.equals(attributes[rowIndex].getId()))
            		result = "";
            	else
                    result = attributes[rowIndex].getDivorg3();
                return (result != null)?result:"";
            case 5:            	
            	if (lastId.equals(attributes[rowIndex].getId()))
            		result = "";
            	else
            		result = messages.getText(attributes[rowIndex].getCountry());
            	return (result != null) ? result:"";
            case 6:   
	            if (!lastId.equals(attributes[rowIndex].getId())){
	            	color = !color;
	            }
                return new Boolean(color);
            case 7:
            	return new Integer(total);
            case 8:
                if (!excel && !lastId.equals(attributes[rowIndex].getId())){
                    lastId = attributes[rowIndex].getId();
                }
            	result = transformDate(attributes[rowIndex].getDate().substring(0,10));
            	return (result != null)?result:"";
            case 9:
            	result = messages.getText(attributes[rowIndex].getType());
            	return (result != null)?result:"";
            case 10:
            	result = messages.getText(attributes[rowIndex].getClasification());
            	return (result != null)?result:"";
            case 11:
            	result = messages.getText(attributes[rowIndex].getResult());	            	            	
            	return (result != null)?result:"";
            case 12:
            	if (lastId.equals(attributes[rowIndex].getId()))
            		result = "";
            	else
                    result = attributes[rowIndex].getDivorg4();
                return (result != null)?result:"";
            case 13:	            	
            	if (!lastId.equals(attributes[rowIndex].getId())){	            		
            		result = attributes[rowIndex].getLastname();
            	}else{
            		result = "";
            	}	            		
                return (result != null)?result:"";
            case 14:	            	
            	if (!lastId.equals(attributes[rowIndex].getId())){	            		
            		result = attributes[rowIndex].getCorporationId();
            	}else{
            		result = "";
            	}	            		
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
            	return "country";
            case 6:
            	return "color";
            case 7:
            	return "total";
            case 8:
            	return "date";
            case 9:
            	return "type";
            case 10:
            	return "clasification";
            case 11:
            	return "result";
            case 12:
                return "divorg4";
            case 13:
                return "lastname";
            case 14:
                return "corporationId";
        }
        return "";
    }
    
	public int getTotal() {
		return total;
	}

    private String transformDate(String date) {
        String result = "";
        StringTokenizer tokenizer = new StringTokenizer(date,"-");
        if(tokenizer.hasMoreTokens()) {
            result = tokenizer.nextToken();
            while(tokenizer.hasMoreTokens()) {
                result = tokenizer.nextToken() + "/" + result;
            }
        }
        return result;
    }
}


