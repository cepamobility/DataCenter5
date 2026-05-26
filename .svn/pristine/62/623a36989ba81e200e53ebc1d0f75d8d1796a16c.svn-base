/**
 * Created on 28-sep-2006
 * CEPA
 * DataCenter 5
 */
package communication.activity.report;

import java.io.Serializable;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import communication.activity.tables.CEPAActivityConstants;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.language.Text;
import communication.util.ListResult;


public class AllActivitiesGeneralReportData extends AbstractTableModel 
    implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total;
    private int participants;
    private CEPAActivityScheduleDataType[] attributes;
    private boolean color = true;
    private Text messages;	
 
    private UserSessionData userSessionData;
    private int[][] ppalDivOrgs = new int[4][2];    
    
    public AllActivitiesGeneralReportData(ListResult reportDrivers,UserSessionData usr) {
        total = reportDrivers.getTotal();
        participants = reportDrivers.getSubtotal();
        Collection elements = reportDrivers.getElements();
        attributes = (CEPAActivityScheduleDataType[])elements.toArray(new CEPAActivityScheduleDataType[0]);
        
        userSessionData = usr;
        ppalDivOrgs = userSessionData.getFilter().getPrincipalDivOrgs();
    }    
    

    public int getRowCount() {
    	return attributes.length;
    }

    public int getColumnCount() {
        return 14;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
    	
        switch(columnIndex) {
        	case 0:
        		return String.valueOf(attributes[rowIndex].getId());        
            case 1:
                if(userSessionData.getFilter().getCorporationId().intValue() == CorporationData.FORESTALORIENTAL
                        && attributes[rowIndex].getType().equals(CEPAActivityConstants.PESADOS_1)) {
                    return messages.getText(CEPAActivityConstants.BTW3);                
                }
                return messages.getText(attributes[rowIndex].getType());                
            case 2:
            	return attributes[rowIndex].getDO(ppalDivOrgs[2][0],ppalDivOrgs[2][1]);
            case 3:
            	return attributes[rowIndex].getDO(ppalDivOrgs[0][0],ppalDivOrgs[0][1]);
            case 4:
            	return attributes[rowIndex].getDO(ppalDivOrgs[1][0],ppalDivOrgs[1][1]);            	
            case 5:
            	return attributes[rowIndex].getDate();            	
            case 6:
                return messages.getText(attributes[rowIndex].getCountry());            	
            case 7: {
            	return attributes[rowIndex].getParticipantsCount();
            	//return new Integer(0);
            }        
            case 8:
                color = !color;
                return new Boolean(color);
            case 9:
                return new Integer(total);
            case 10:
            	return attributes[rowIndex].getDO(ppalDivOrgs[3][0],ppalDivOrgs[3][1]);            	
            case 11:
            	return messages.getText(attributes[rowIndex].getState());
            case 12:
            	if(attributes[rowIndex].getState().equals(CEPAActivityConstants.DICTADA)) {
            		return "./layout.jsp?refer=/activity/schedule/OneActivityGeneralReport.jsp?activityId="+attributes[rowIndex].getId();
            	}else {
            		return "./layout.jsp?refer=/activity/schedule/CEPAActivityScheduleView.jsp?id="+attributes[rowIndex].getId();
            	}
            case 13:
                return new Integer(participants);
        }
        return null;
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "id";
            case 1:
                return "type";
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
                return "participants";
            case 8:
                return "color";                
            case 9:
            	return "total";
            case 10:
            	return "security";
            case 11:
            	return "state";
            case 12:
            	return "link";
            case 13:
                return "totalparticipants";
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
