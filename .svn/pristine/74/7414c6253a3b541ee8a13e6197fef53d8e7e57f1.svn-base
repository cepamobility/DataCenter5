package communication.activity.report;

import java.io.Serializable;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import communication.administration.user.UserSessionData;
import communication.driver.report.DetailedDriverReportDataType;
import communication.driver.report.DriverReportDataType;
import communication.language.Text;

public class DriversWithoutTOJData extends AbstractTableModel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total;
    private DriverReportDataType[] attributes;
    private boolean color = true;
    private Text messages;
    
    private UserSessionData userSessionData;
    
    private int[][] ppalDivOrgs = new int[3][2];
    
    public DriversWithoutTOJData() {
        total = 0;
        attributes = new DriverReportDataType[0]; 
    }
    
    public DriversWithoutTOJData(Collection drivers,UserSessionData usr) {   
        
    	if (drivers == null){
        	total = 0;
        	attributes = new DriverReportDataType[0];
        }else{
        	total = drivers.size();
        	attributes = (DriverReportDataType[])drivers.toArray(new DriverReportDataType[0]);
        }
        userSessionData = usr;
        ppalDivOrgs = userSessionData.getFilter().getPrincipalDivOrgs();
    }

    public int getRowCount() {
    	return attributes.length;
    }

    public int getColumnCount() {
        return 17;
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
                return attributes[rowIndex].getFirstname()+" "+attributes[rowIndex].getLastname();
            case 2:
            	// Unidad
				return attributes[rowIndex].getDivOrg(3,1);
            case 3:
            	// Division            	
				return attributes[rowIndex].getDivOrg(2,2);
            case 4:
				return attributes[rowIndex].getDivOrg(2,1);
            case 10:
				return attributes[rowIndex].getDivOrg(1,1);
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
                if (attributes[rowIndex].getAssignedVehicle() == null){
                	return "";
                }
            	String allowed = attributes[rowIndex].getAssignedVehicle(); 
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
                if (attributes[rowIndex].getLastHRD() == null){
                	return "";
                }
                return attributes[rowIndex].getLastHRD();
            case 13:
                if (attributes[rowIndex].getExpiry() == null){
                	return "";
                }
                return attributes[rowIndex].getExpiry();
            case 14:
            	return ((DriverReportDataType)attributes[rowIndex]).getLicenseObs();
            case 15:
            	return (((DriverReportDataType)attributes[rowIndex]).isDirector()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 16:
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
            	return "lastTOJ";
            case 13:
            	return "expiryTOJ";
            case 14:
            	return "licenseObs";
            case 15:
            	return "director";
            case 16:
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
