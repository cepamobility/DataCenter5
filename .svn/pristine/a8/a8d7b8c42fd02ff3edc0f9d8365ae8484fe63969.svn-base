/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.driver.report;

import java.io.Serializable;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverData;
import communication.language.Text;

public class DriverLicenseExpiryData extends AbstractTableModel 
    implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total = 0;
    private int cantReg = 0;
    private DriverData[] attributes;
    private boolean color = true;
    private Text messages;
    private UserSessionData userSessionData;

    private int[][] principal;
    
    public DriverLicenseExpiryData() {
        total = 0;
        attributes = new DriverData[0];
        principal = new int[3][2];
    }
    
    public DriverLicenseExpiryData(Collection reportDrivers,int total, UserSessionData usr) {
        this.cantReg = reportDrivers.size();
        attributes = (DriverData[])reportDrivers.toArray(new DriverData[0]);
        userSessionData = usr;
        principal = userSessionData.getFilter().getPrincipalDivOrgs();
        this.total = total;
    }
    
    public DriverLicenseExpiryData(Collection reportDrivers,int[][] principal) {
        total = reportDrivers.size();
        attributes = (DriverData[])reportDrivers.toArray(new DriverData[0]);
        this.principal = principal;
    }

    public int getRowCount() {
        return cantReg;
    }
    
    /**
	 * @return Returns the total.
	 */
	public int getTotal() {
		return total;
	}

    public int getColumnCount() {
        return 13;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return String.valueOf(attributes[rowIndex].getId());
            case 1:
                return attributes[rowIndex].getFirstName();
            case 2:
                return attributes[rowIndex].getData(DriverData.DIV_ORG,principal[0][0],principal[0][1]);
            case 3:
                return attributes[rowIndex].getData(DriverData.DIV_ORG,principal[1][0],principal[1][1]);
            case 4:
                return attributes[rowIndex].getData(DriverData.DIV_ORG,principal[2][0],principal[2][1]);
            case 5:
                return attributes[rowIndex].getLicenseExpiry();
            case 6:
                color = !color;
                return new Boolean(color);
            case 7:
            	return new Integer(this.total);
            case 8:
                return attributes[rowIndex].getData(DriverData.DIV_ORG,3,1);
            case 9:
                return attributes[rowIndex].getData(DriverData.DIV_ORG,principal[3][0],principal[3][1]);
            case 10:
            	return attributes[rowIndex].getLastName();
            case 11:
            	return (attributes[rowIndex].isDirector()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 12:
            	return attributes[rowIndex].getCorporationId();
        }
        return null;
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
                return "expiry";
            case 6:
                return "color";
            case 7:
            	return "total";
            case 8:
            	return "divorgExtra1";
            case 9:
                return "divorg4";
            case 10:
                return "lastname";
            case 11:
                return "director";
            case 12:
            	return "corporationId";
        }
        return null;
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }
}
