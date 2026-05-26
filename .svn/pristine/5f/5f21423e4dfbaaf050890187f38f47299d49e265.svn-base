package communication.activity.report;

import java.io.Serializable;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import communication.administration.corporation.util.Util;
import communication.administration.user.UserSessionData;
import communication.driver.report.DetailedDriverReportDataType;
import communication.driver.report.DriverReportDataType;
import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.util.ListResult;

public class DriversWithoutBTWData extends AbstractTableModel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total;
    private DriverReportDataType[] attributes;
    private boolean color = true;
    private Text messages;
    
    private UserSessionData userSessionData;
    
    private int[][] ppalDivOrgs = new int[3][2];
    
    public DriversWithoutBTWData() {
        total = 0;
        attributes = new DriverReportDataType[0]; 
    }
    
    public DriversWithoutBTWData(ListResult reportDrivers,UserSessionData usr) {   
        
    	if (reportDrivers == null){
        	total = 0;
        	attributes = new DriverReportDataType[0];
        }else{
        	total = reportDrivers.getTotal();
        	Collection elements = reportDrivers.getElements();
        	attributes = (DriverReportDataType[])elements.toArray(new DriverReportDataType[0]);
        }
        userSessionData = usr;
        ppalDivOrgs = userSessionData.getFilter().getPrincipalDivOrgs();
    }

    public int getRowCount() {
    	return attributes.length;
    }

    public int getColumnCount() {
        return 21;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
    	
    	
        switch(columnIndex) {
            case 0:
            	if (attributes[rowIndex].getId() == null){
            		return new Integer(0);
            	}
                return String.valueOf(attributes[rowIndex].getId());
            case 1:
            	if (attributes[rowIndex].getFirstname() == null || attributes[rowIndex].getLastname() == null){
            		return "";
            	}
                return attributes[rowIndex].getFirstname();
            case 2:
            	// Unidad
				return attributes[rowIndex].getDivOrg(ppalDivOrgs[2][0],ppalDivOrgs[2][1]);
            case 3:
            	// Division            	
				return attributes[rowIndex].getDivOrg(ppalDivOrgs[0][0],ppalDivOrgs[0][1]);
            case 4:
				return attributes[rowIndex].getDivOrg(ppalDivOrgs[1][0],ppalDivOrgs[1][1]);
            case 10:
				return attributes[rowIndex].getDivOrg(ppalDivOrgs[3][0],ppalDivOrgs[3][1]);
            case 5: 
                if (attributes[rowIndex].getCountry() == null){
                	return "";
                }
            	return messages.getText(attributes[rowIndex].getCountry());
            case 6:
                color = !color;
                return new Boolean(color);
            case 7:
                return new Integer(total);
            case 8:
                if ( ((DetailedDriverReportDataType)attributes[rowIndex]).getVehicleallowed() == null){
                	return "";
                }
            	String allowed = ((DetailedDriverReportDataType)attributes[rowIndex]).getVehicleallowed(); 
                return (allowed == null || allowed.length() == 0) ? "---" : messages.getText(allowed);
            
            case 9:
            	// WWID
            	if (attributes[rowIndex].getCorporationId() == null){
            		return "";
            	}
            	String corporationId = attributes[rowIndex].getCorporationId();
            	return (corporationId == null)?"":corporationId;
            case 11:
            	// WWID
            	if (((DetailedDriverReportDataType)attributes[rowIndex]).getEmail() == null){
            		return "";
            	}
            	return ((DetailedDriverReportDataType)attributes[rowIndex]).getEmail();
            case 12:
            	// INCLUSION DATE
            	return Util.formatDate(((DetailedDriverReportDataType)attributes[rowIndex]).getInclusiondate());
            case 13:
            	// SUPERVISER
            	if (((DetailedDriverReportDataType)attributes[rowIndex]).getSuperviser() == null){
            		return "";
            	}
            	return ((DetailedDriverReportDataType)attributes[rowIndex]).getSuperviser();
            case 14:
            	// MANAGER
            	if (((DetailedDriverReportDataType)attributes[rowIndex]).getManager() == null){
            		return "";
            	}
            	return ((DetailedDriverReportDataType)attributes[rowIndex]).getManager();
            case 15:
            	// CITY
            	if (((DetailedDriverReportDataType)attributes[rowIndex]).getCity() == null){
            		return "";
            	}
            	return Util.toHTMLString(((DetailedDriverReportDataType)attributes[rowIndex]).getCity());
            case 16:
            	// ADDRESS
            	if (((DetailedDriverReportDataType)attributes[rowIndex]).getPersonaladdress() == null){
            		return "";
            	}
            	return Util.toHTMLString(((DetailedDriverReportDataType)attributes[rowIndex]).getPersonaladdress().trim());
            case 17:
            	// ADDRESS
            	if (((DetailedDriverReportDataType)attributes[rowIndex]).getPhonenumber() == null){
            		return "";
            	}
            	return Util.toHTMLString(((DetailedDriverReportDataType)attributes[rowIndex]).getPhonenumber().trim());
            case 18:
            	// OBSERVACIONES
            	if (((DetailedDriverReportDataType)attributes[rowIndex]).getLicenseObs() == null){
            		return "";
            	}
            	return Util.toHTMLString(((DetailedDriverReportDataType)attributes[rowIndex]).getLicenseObs().trim());
            case 19:
            	return (((DetailedDriverReportDataType)attributes[rowIndex]).isDirector()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 20:
            	if (attributes[rowIndex].getFirstname() == null || attributes[rowIndex].getLastname() == null){
            		return "";
            	}
                return attributes[rowIndex].getLastname();
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
                return "unit";
            case 3:
                return "division";
            case 4:
                return "subdivision";
            case 5:
                return "country";                
            case 6:
                return "color";
            case 7:
                return "total";             
            case 8:
                return "allowed";      
            case 9:
            	return "corporationId";
            case 10:
            	return "safety";
            case 11:
            	return "email";
            case 12:
            	return "inclusiondate";
            case 13:
            	return "superviser";
            case 14:
            	return "manager";
            case 15:
            	return "city";
            case 16:
            	return "personaladdress";
            case 17:
            	return "phonenumber";
            case 18:
            	return "licenseObs";
            case 19:
            	return "director";
            case 20:
                return "lastname";
        }
        return null;
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
