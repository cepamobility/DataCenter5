/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.driver.report;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.text.StyledEditorKit.BoldAction;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverPointData;
import communication.language.Text;

public class DriverPointReportData extends AbstractTableModel 
    implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total = 0;
    private DriverPointData[] attributes;
    private boolean color = true;
    private Text messages;
    private UserSessionData userSessionData;

    private int[][] principal;

	private HashMap<Integer,Integer[]> electronics;
	private HashMap<Integer,String> plans;
    
    public DriverPointReportData() {
        total = 0;
        attributes = new DriverPointData[0];
    }
    
    public DriverPointReportData(Collection<DriverPointData> reportDrivers,UserSessionData usr, boolean resumed) {
        if(resumed) {
            total = reportDrivers.size();
            Collections.sort((List<DriverPointData>) reportDrivers);
        	attributes = (DriverPointData[])reportDrivers.toArray(new DriverPointData[0]);
        }else {
        	Collection<DriverPointData> values = new LinkedList<DriverPointData>();
        	Iterator<DriverPointData> it = reportDrivers.iterator();
        	while(it.hasNext()) {
        		values.addAll(it.next().getCollection());
        	}
            total = values.size();
            Collections.sort((List<DriverPointData>) values);
        	attributes = (DriverPointData[])values.toArray(new DriverPointData[0]);
        }
        userSessionData = usr;
        principal = userSessionData.getFilter().getPrincipalDivOrgs();
    }
    
    public int getRowCount() {
        return total;
    }
    
    public int getColumnCount() {
        return 32;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
	        case 0:
	            return attributes[rowIndex].getId().toString();
	        case 1:
	            return attributes[rowIndex].getFirstName();
	        case 2:
	            return attributes[rowIndex].getLastName();
			case 6:
				color = !color;
				return new Boolean(color);
	        case 3:
	            return (attributes[rowIndex].isDirector()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
	        case 4:
	            return messages.getText(attributes[rowIndex].getCountry());
	        case 5:
	            return attributes[rowIndex].getTotal();
	        case 7: case 8: case 9: case 11:  
	            return attributes[rowIndex].getCategories()[columnIndex-7];
	        case 10:  
        		return attributes[rowIndex].getCategories()[columnIndex-7];
	        case 12:
	        	return attributes[rowIndex].getMove().getDate().getTime();
	        case 13:
	        	return attributes[rowIndex].getMove().getTxt(messages);
	        case 14:
	        	return attributes[rowIndex].getMove().getAmount();
	        case 15:
	        	return attributes[rowIndex].getDivOrg1();
	        case 16:
	        	return attributes[rowIndex].getDivOrg2();
	        case 17:
	        	return attributes[rowIndex].getDivOrg3();
	        case 18:
	        	return attributes[rowIndex].getDivOrg4();
	        case 19:
	        	if(userSessionData.isCorporation(CorporationData.BAYER_MX)) {
	        		int amount = attributes[rowIndex].getTotal().intValue();
	        		if(amount >= 50) {
	        			return "Muy Alto";
	        		} else if(amount >= 30) {
	        			return "Alto";
	        		} else if(amount >= 20) {
	        			return "Medio";
	        		} else {
	        			return "Bajo";
	        		} 
	        	}
	        	return null;
	        case 20:
	        	return new Boolean(userSessionData.isCorporation(CorporationData.BAYER_MX) && attributes[rowIndex].getTotal().intValue() >= 50);
	        case 21:
	        	return new Boolean(userSessionData.isCorporation(CorporationData.BAYER_MX) && attributes[rowIndex].getTotal().intValue() >= 30 && attributes[rowIndex].getTotal().intValue() < 50);
	        case 22:
	        	return new Boolean(userSessionData.isCorporation(CorporationData.BAYER_MX) && attributes[rowIndex].getTotal().intValue() >= 20 && attributes[rowIndex].getTotal().intValue() < 30);
	        case 23:
	        	return new Boolean(userSessionData.isCorporation(CorporationData.BAYER_MX) && attributes[rowIndex].getTotal().intValue() < 20);
	        case 24:
	        	return new Boolean(userSessionData.isCorporation(CorporationData.BAYER_MX) && attributes[rowIndex].getTotal().intValue() < 30);
            case 25:
            	if(userSessionData.isCorporation(CorporationData.BAYER_MX)) {
	        		if(electronics.containsKey(attributes[rowIndex].getId())) {
	        			Integer value = electronics.get(attributes[rowIndex].getId())[0];
	        			return (value == 0) ? null : value;
	        		}
            	}
                return null;
            case 26:
            	if(userSessionData.isCorporation(CorporationData.BAYER_MX)) {
	        		if(electronics.containsKey(attributes[rowIndex].getId())) {
	        			Integer value = electronics.get(attributes[rowIndex].getId())[1];
	        			return (value == 0) ? null : value;
	        		}
            	}
                return null;
            case 27:
	        	return attributes[rowIndex].getCorporationId();
            case 28:
	        	return attributes[rowIndex].getManager();
            case 29:
            	if(userSessionData.isCorporation(CorporationData.BAYER_MX)) {
	        		if(plans.containsKey(attributes[rowIndex].getId())) {
	        			return plans.get(attributes[rowIndex].getId());
	        		}
            	}
	        case 30:  
        		return attributes[rowIndex].getCategories()[5];
	        case 31:  
        		return attributes[rowIndex].getCategories()[7];
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
                return "lastname";
            case 3:
                return "director";
            case 4:
                return "country";
            case 5:
                return "points";
            case 6:
                return "color";
            case 7: case 8: case 9: case 10: case 11:  
                return "category" + String.valueOf(column-7);
            case 12:
                return "date";
            case 13:
                return "move";
            case 14:
                return "amount";
            case 15:case 16:case 17:case 18:
                return "divorg"+String.valueOf(column-14);
            case 19:
                return "risk";
            case 20:
                return "veryhighRisk";
            case 21:
                return "highRisk";
            case 22:
                return "medRisk";
            case 23:
                return "lowRisk";
            case 24:
                return "colorRisk";
            case 25:
                return "ebtw";
            case 26:
                return "da";
            case 27:
                return "corporationId";
            case 28:
                return "manager";
            case 29:
                return "mitigationplan";
            case 30:
                return "category5";
            case 31:
                return "category7";
        }
        return null;
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }

	public void setElectronics(HashMap<Integer,Integer[]> electronics) {
		this.electronics = electronics;
	}

	public void setMitigationPlans(HashMap<Integer,String> plans) {
		this.plans = plans;
	}
}
