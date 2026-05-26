package communication.vehicle.report;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import communication.fleet.tables.KmByTradeDataType;
import communication.language.Text;

public class KmByTradeData extends AbstractTableModel 
    implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total = 0;
    private KmByTradeDataType[] attributes;
    private Text messages;
    private boolean color = true;

	private Integer toYear;
	private Integer toPeriod;
    
    public KmByTradeData() {
        total = 0;
        attributes = new KmByTradeDataType[0];
    }
    
    public KmByTradeData(Collection<KmByTradeDataType> data,Text messages, Integer toPeriod, Integer toYear) {
    	this.toPeriod = toPeriod;
    	this.toYear = toYear;
        if (data != null){
        	this.total = data.size();
        	Collections.sort((List)data);
        	attributes = (KmByTradeDataType[])data.toArray(new KmByTradeDataType[0]);
        }
        this.messages = messages;
    }

    public int getRowCount() {
        return total;
    }

    public int getColumnCount() {
        return 22;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return attributes[rowIndex].getPlate();
            case 1:
                return attributes[rowIndex].getDriverFirstName();
            case 2:
                return attributes[rowIndex].getKms();               
            case 3:
                return attributes[rowIndex].getAccidents();               
            case 4:
                return attributes[rowIndex].getIncidents();               
            case 5:                
            	return attributes[rowIndex].getLitres();               
            case 6:
                return messages.getText(attributes[rowIndex].getFuel());               
            case 7:
                return attributes[rowIndex].getSuperviser();
            case 8:
                return attributes[rowIndex].getManager();               
            case 9:
            	// vehicleid
            	if (attributes[rowIndex].getVehicleid() != null){
            		return attributes[rowIndex].getVehicleid().toString();
            	}
            	return "";
            case 10:
            	// driverid
            	if (attributes[rowIndex].getDriverid() != null){
            		return attributes[rowIndex].getDriverid().toString();
            	}
            	return "";
            case 11:
            	// country
            	if (attributes[rowIndex].getCountry() != null){
            		return messages.getText(attributes[rowIndex].getCountry());
            	}else{
            		return "";
            	}            
            case 12:
            	return attributes[rowIndex].getDivorg1();
            case 13: 
            	return attributes[rowIndex].getDivorg2();
            case 14: 
            	return attributes[rowIndex].getDivorg3();
            case 15:
            	return attributes[rowIndex].getDivorg4();
            case 16:
                color = !color;
                return new Boolean(color);
            case 17:
            	return new Integer(total);
            case 18:
                return attributes[rowIndex].getDriverLastName();
            case 19:
            	return (attributes[rowIndex].getLastPeriod() == toPeriod && attributes[rowIndex].getLastYear() == toYear) ?  messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 20:
            	return (attributes[rowIndex].isDirector()) ?  messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 21:
            	if(attributes[rowIndex].getKms() != 0) {
            		return "---";
            	}else {
            		if(attributes[rowIndex].getLastYear() < toYear.intValue() || (attributes[rowIndex].getLastYear() == toYear.intValue() && attributes[rowIndex].getLastPeriod() < toPeriod.intValue())) {
            			return messages.getText("query.kmbytrade.comment1");
            		} else if(attributes[rowIndex].getOdometerValue() == 0) {
            			return messages.getText("query.kmbytrade.comment2");
            		} else  if(attributes[rowIndex].getOdometerValue() == attributes[rowIndex].getLastOdometer()) {
            			return messages.getText("query.kmbytrade.comment3");
            		}
            	}
            }
        return "";
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "plate";
            case 1:
                return "driverFirstName";
            case 2:
                return "kms";
            case 3:
                return "accidents";
            case 4:
                return "incidents";
            case 5:
                return "litres";
            case 6:
                return "fuel";
            case 7:
            	return "superviser";
            case 8:
            	return "manager";
            
            case 9:
            	return "vehicleid";
            case 10:
            	return "driverid";
            case 11:
            	return "country";
            case 12:
                return "divorg1";
            case 13:
                return "divorg2";
            case 14:
                return "divorg3";
            case 15:
                return "divorg4";
            case 16:
            	return "color";
            case 17:
            	return "total";
            case 18:
                return "driverLastName";
            case 19:
                return "insertLast";
            case 20:
                return "director";
            case 21:
                return "comment";
        }
        return "";
    }

    /**
	 * @return Returns the total.
	 */
	public int getTotal() {
		return total;
	}
}


