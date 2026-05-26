/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.driver.report;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import communication.activity.tables.CEPAActivityConstants;
import communication.administration.corporation.util.Util;
import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.util.ListResult;

public class FOSADriverReportData extends AbstractTableModel 
    implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total;
    private FOSADriverReportDataType[] attributes;
    private boolean color = true;
    private Text messages;
			    

    
    public FOSADriverReportData() {
        total = 0;
        attributes = new FOSADriverReportDataType[0]; 

    }
    
    public FOSADriverReportData(ListResult reportDrivers) {
        total = reportDrivers.getTotal();
        Collection elements = reportDrivers.getElements();
        Collections.sort((List) elements);
        attributes = (FOSADriverReportDataType[])elements.toArray(new FOSADriverReportDataType[0]);        
    }


    public int getRowCount() {
        return attributes.length;
    }

    public int getColumnCount() {
        return 36;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return String.valueOf(attributes[rowIndex].getId());
            case 1:
                return attributes[rowIndex].getLastname()+" "+attributes[rowIndex].getFirstname();  
            case 2:
            	String s = attributes[rowIndex].getCorporationId().trim();
            	if(s.length() == 8) {
            		s = s.substring(0,1)+"."+s.substring(1,4)+"."+s.substring(4,7)+"-"+s.substring(7,8);
            	}
                return s;  
            case 3:
                color = !color;
                return new Boolean(color);
            case 4:
                return messages.getText(attributes[rowIndex].getCountry());
            case 5:
                return Util.formatDate(attributes[rowIndex].getBirthdate(),messages);
            case 6:
                return Util.formatDate(attributes[rowIndex].getExclusiondate(),messages);
            case 7:
                return messages.getText(attributes[rowIndex].getContract());
            case 8:
                return attributes[rowIndex].getDivorg11();
            case 9:
                return attributes[rowIndex].getDivorg12();
            case 10:
                return attributes[rowIndex].getDivorg13();
            case 11:
                return attributes[rowIndex].getDivorg21();
            case 12:
                return attributes[rowIndex].getDivorg22();
            case 13:
                return attributes[rowIndex].getDivorg23();
            case 14:
                return attributes[rowIndex].getDivorg31();
            case 15:
                return attributes[rowIndex].getDivorg32();
            case 16:
                return attributes[rowIndex].getDivorg33();
            case 17:
                return attributes[rowIndex].getDivgeo1();
            case 18:
                return attributes[rowIndex].getDivgeo2();
            case 19:
                return attributes[rowIndex].getDivgeo3();
            case 20:
                return new Integer(total);
            case 21:
            	return messages.getText(attributes[rowIndex].getCountry());
            case 22:
                return Util.formatDate(attributes[rowIndex].getTestDate(),messages);
            case 23:
            	if(attributes[rowIndex].isPending()){
            		return messages.getText("generic.messages.no")+" - "+
            			messages.getText("generic.messages.pending");
            	}else if(attributes[rowIndex].isApt()) {
                    return messages.getText("generic.messages.yes");
                }else {
                    return messages.getText("generic.messages.no");
                }
            case 24:
                return Util.formatDate(attributes[rowIndex].getExpiry(),messages);
            case 25:
                return Util.formatDate(attributes[rowIndex].getLast(),messages);
            case 26:
                return Util.formatDate(attributes[rowIndex].getLastControl(),messages);
            case 27:
                return Util.formatDate(attributes[rowIndex].getLast(),messages);
            case 28:
                switch(attributes[rowIndex].getStatus()) {
                    case AlcoholControlReportDataType.NEVER:
                        return messages.getText("driver.alcoholcontrol.neverdone");
                    case AlcoholControlReportDataType.EXPIRED:
                        return messages.getText("driver.alcoholcontrol.expired");
                    default:
                        return messages.getText("driver.alcoholcontrol.effective");
                }
            case 29:
            	if(attributes[rowIndex].getLast() != null && attributes[rowIndex].getLastExpiry() == null) {
            		return messages.getText(CEPAActivityConstants.NOTAPRUVED);
            	}else {
            		return Util.formatDate(attributes[rowIndex].getLastExpiry(),messages);
            	}
            case 30:
                return attributes[rowIndex].getCount();
            case 31:
                return Util.formatDate(attributes[rowIndex].getLast(),messages);
            case 32:
                return Util.formatDate(attributes[rowIndex].getLastEC(),messages);
            case 33:
            	return Util.formatDate(attributes[rowIndex].getLastRadar(),messages);
            case 34:
            	return Util.formatDate(attributes[rowIndex].getLastInduction(),messages);
            case 35:
            	return messages.getText(attributes[rowIndex].getRadarNote());
                   }
        return null;
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "id";
            case 1:
                return "name";
            case 2:
                return "corporationId";
            case 3:
                return "color";
            case 4:
                return "country";
            case 5:
                return "birthdate";
            case 6:
                return "exclusiondate";
            case 7:
                return "contract";
            case 8:
                return "divorg11";
            case 9:
                return "divorg12";
            case 10:
                return "divorg13";
            case 11:
                return "divorg21";
            case 12:
                return "divorg22";
            case 13:
                return "divorg23";
            case 14:
                return "divorg31";
            case 15:
                return "divorg32";
            case 16:
                return "divorg33";
            case 17:
                return "divgeo1";
            case 18:
                return "divgeo2";
            case 19:
                return "divgeo3";
            case 20:
                return "total";
            case 21:
            	return "country";
            case 22:
                return "test";
            case 23:
                return "apt";
            case 24:
                return "expiry";
            case 25:
                return "bps";
            case 26:
                return "lastControl";
            case 27:
                return "lastBTW";
            case 28:
                return "status";
            case 29:
                return "expiryBTW";
            case 30:
                return "count";
            case 31:
                return "last";
            case 32:
                return "lastec";
            case 33:
                return "lastRadar";
            case 34:
                return "lastInduction";
            case 35:
                return "noteRadar";
        }
        return null;
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }

    public int getTotal() {
        return total;
    }
}
