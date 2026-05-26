/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.activity.report;

import java.io.Serializable;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.DriverWithDeliveredActivities;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.driver.report.DetailedDriverReportDataType;
import communication.driver.report.DriverReportDataType;
import communication.language.Text;
import communication.util.ListResult;

public class ActivityDeliveredData extends AbstractTableModel 
    implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total;
    private DriverWithDeliveredActivities[] attributes;
    private boolean color = true;
    private Text messages;
    
    private UserSessionData userSessionData;
    
    private int[][] ppalDivOrgs = new int[4][2];
    
    public ActivityDeliveredData() {
        total = 0;
        attributes = new DriverWithDeliveredActivities[0]; 
    }
    
    public ActivityDeliveredData(ListResult reportDrivers,UserSessionData usr) {   
        total = reportDrivers.getTotal();
        Collection elements = reportDrivers.getElements();
        attributes = (DriverWithDeliveredActivities[])elements.toArray(new DriverWithDeliveredActivities[0]);
        userSessionData = usr;
        ppalDivOrgs = userSessionData.getFilter().getPrincipalDivOrgs();
    }

    public int getRowCount() {
    	return attributes.length;
    }

    public int getColumnCount() {
        return 28;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return String.valueOf(attributes[rowIndex].getDriver().getId());
            case 1:
                return attributes[rowIndex].getDriver().getFirstname();
            case 2:
            	return attributes[rowIndex].getDivOrg(ppalDivOrgs[2][0],ppalDivOrgs[2][1]);
            case 3:
            	return attributes[rowIndex].getDivOrg(ppalDivOrgs[0][0],ppalDivOrgs[0][1]);
            case 4:
            	return attributes[rowIndex].getDivOrg(ppalDivOrgs[1][0],ppalDivOrgs[1][1]);            	
            case 13:
            	return attributes[rowIndex].getDivOrg(ppalDivOrgs[3][0],ppalDivOrgs[3][1]);            	
            case 5: 
            	return attributes[rowIndex].getDate();
            case 6: 
                return messages.getText(attributes[rowIndex].getDriver().getCountry());
            case 7:
                color = !color;
                return new Boolean(color);
            case 8:
                return new Integer(total);
            case 9: 
            	int corporationId = userSessionData.getCorporationId();
                if(corporationId == CorporationData.FORESTALORIENTAL && attributes[rowIndex].getType().equals(CEPAActivityConstants.PESADOS_1)) {
                    return messages.getText(CEPAActivityConstants.BTW3);                
                }
                if((corporationId == CorporationData.FORESTALORIENTAL || corporationId == CorporationData.MONTES_DE_PLATA) && attributes[rowIndex].getType().equals(CEPAActivityConstants.SECURITY_TALK)) {
                    return messages.getText(CEPAActivityConstants.SECURITY_TALK) + " - " + attributes[rowIndex].getTheme();                
                }
                return messages.getText(attributes[rowIndex].getType());
            case 10:
                return messages.getText(attributes[rowIndex].getNote());
            case 11:
                String allowed = ((DetailedDriverReportDataType)attributes[rowIndex].getDriver()).getVehicleallowed();
                return (allowed == null || allowed.length() == 0) ? "---" : messages.getText(allowed);
            case 12:
            	// WWID
            	String corporationStr = attributes[rowIndex].getDriver().getCorporationId();
            	return (corporationStr != null) ? corporationStr : "";
            case 14:
            	return attributes[rowIndex].getDivOrg(1,1);            	
            case 15:
            	return attributes[rowIndex].getDivOrg(1,2);            	
            case 16:
            	return attributes[rowIndex].getDivOrg(1,3);            	
            case 17:
            	return attributes[rowIndex].getDivOrg(2,1);            	
            case 18:
            	return attributes[rowIndex].getDivOrg(2,2);            	
            case 19:
            	return attributes[rowIndex].getDivOrg(2,3);            	
            case 20:
            	return attributes[rowIndex].getDivOrg(3,1);            	
            case 21:
            	return attributes[rowIndex].getDivOrg(3,2);            	
            case 22:
            	return attributes[rowIndex].getDivOrg(3,3);            	
            case 23:
            	return attributes[rowIndex].getCity();            	
            case 24:
            	return attributes[rowIndex].getInstructor();            	
            case 25:
            	return ((DetailedDriverReportDataType)attributes[rowIndex].getDriver()).getLicenseObs();            	
            case 26:
                return attributes[rowIndex].getDriver().getLastname();
            case 27:
                return (attributes[rowIndex].getDriver().isDirector()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
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
                return "activityDate";
            case 6:
                return "country";                
            case 7:
                return "color";
            case 8:
            	return "total";
            case 9:
            	return "type";
            case 10:
                return "note";              
            case 11:
                return "allowed";   
            case 12:
            	return "corporationId";
            case 13:
            	return "safety";
            case 14:
            	return "divorg11";
            case 15:
            	return "divorg12";
            case 16:
            	return "divorg13";
            case 17:
            	return "divorg21";
            case 18:
            	return "divorg22";
            case 19:
            	return "divorg23";
            case 20:
            	return "divorg31";
            case 21:
            	return "divorg32";
            case 22:
            	return "divorg33";
            case 23:
            	return "city";
            case 24:
            	return "instructor";
            case 25:
            	return "licenseObs";
            case 26:
            	return "lastname";
            case 27:
            	return "director";
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
	
    public int getNivel(int index) {
        int nivel = 0;
        int[][] ppalDivOrg = userSessionData.getFilter().getPrincipalDivOrgs();
        int[] data = ppalDivOrg[index];
        int divOrg = data[0];
        int level = data[1];
        if(divOrg > 0) {
            int length = userSessionData.getFilter().getDivOrgLevel(divOrg-1);
            switch(length) {
                case 1:
                	nivel = 3;
                    break;
                case 2:
                    if(level == 1) {
                    	nivel= 2; 
                    }else {
                        nivel = 3; 
                    }
                    break;
                case 3:
                    if(level == 1) {
                    	nivel = 1; 
                    }else if(level == 2) {
                    	nivel = 2; 
                    }else {
                    	nivel = 3; 
                    }
                    break;
            }

        }
        return nivel;
    }
}
