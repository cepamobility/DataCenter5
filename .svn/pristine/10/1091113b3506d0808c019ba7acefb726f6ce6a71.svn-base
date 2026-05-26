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
import communication.activity.tables.DriverWithExpiredActivities;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.util.Util;
import communication.administration.user.UserSessionData;
import communication.language.Text;
import communication.util.ListResult;

public class ActivityExpiryData extends AbstractTableModel 
    implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total;
    private DriverWithExpiredActivities[] attributes;
    private boolean color = true;
    private Text messages;
    
    private UserSessionData userSessionData;
    
    private int[][] ppalDivOrgs = new int[3][2];    
    
    public ActivityExpiryData() {
        total = 0;
        attributes = new DriverWithExpiredActivities[0]; 
    }
    
    public ActivityExpiryData(ListResult reportDrivers,UserSessionData usr) {
        total = reportDrivers.getTotal();
        Collection elements = reportDrivers.getElements();
        //Collections.sort((List) elements);
        attributes = (DriverWithExpiredActivities[])elements.toArray(new DriverWithExpiredActivities[0]);
        
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
            	return attributes[rowIndex].getDivOrg3();
            case 3:
            	return attributes[rowIndex].getDivOrg1();
            case 4:
            	return attributes[rowIndex].getDivOrg2();            	
            case 14:
            	return attributes[rowIndex].getDivOrg4();            	
            	
            case 5: {
            	return attributes[rowIndex].getDateExpiry();
            }
            
            case 6: {
            	return attributes[rowIndex].getDate();
            }        
            
            case 7: {
                return messages.getText(attributes[rowIndex].getDriver().getCountry());
            }        
            
            case 8:
                color = !color;
                return new Boolean(color);
                
            case 9:
                return new Integer(total);
                
            case 10: 
                if(userSessionData.getFilter().getCorporationId().intValue() == CorporationData.FORESTALORIENTAL
                        && attributes[rowIndex].getType().equals(CEPAActivityConstants.PESADOS_1)) {
                    return messages.getText(CEPAActivityConstants.BTW3);                
                }
                return messages.getText(attributes[rowIndex].getType());
            case 11: 
                return messages.getText(attributes[rowIndex].getNote());
            case 12: 
                String allowed = attributes[rowIndex].getDriver().getContract();
                return (allowed == null || allowed.length() == 0) ? "---" : messages.getText(allowed);
            case 13:
            	// WWID
            	String corporationId = attributes[rowIndex].getDriver().getCorporationId();
            	return (corporationId == null)?"":corporationId;
            case 15:
            	return (attributes[rowIndex].isHasBTW2()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 16:
            	return (attributes[rowIndex].isHasBTW3()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 17: 
                return (attributes[rowIndex].getLastThird() == null) ? "---" : messages.getText(attributes[rowIndex].getLastThird());
            case 18: 
            	return Util.formatDate(attributes[rowIndex].getThirdExpiry(),messages);
            case 19:
            	return (attributes[rowIndex].isEBTW()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 20:
            	return attributes[rowIndex].getDriver().getCity();
            case 21:
            	return attributes[rowIndex].getDriver().getPhonenumber();
            case 22:
            	return attributes[rowIndex].getDriver().getManager();
            case 23:
            	return attributes[rowIndex].getDriver().getSuperviser();
            case 24:
            	return attributes[rowIndex].getDriver().getEmail();
            case 25:
            	return attributes[rowIndex].getDriver().getLicenseObs();
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
                return "expiry";
            case 6:
                return "activityDate";
            case 7:
                return "country";                
            case 8:
                return "color";
            case 9:
            	return "total";
            case 10:
            	return "type";
            case 11:
                return "note";              
            case 12:
                return "allowed"; 
            case 13:
            	return "corporationId";
            case 14:
            	return "safety";
            case 15:
            	return "btw2";
            case 16:
            	return "btw3";
            case 17:
            	return "lastthird";
            case 18:
            	return "expirythird";
            case 19:
            	return "ebtw";
            case 20:
            	return "address";
            case 21:
            	return "phone";
            case 22:
            	return "manager";
            case 23:
            	return "superviser";
            case 24:
            	return "email";
            case 25:
            	return "licenseobs";
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
