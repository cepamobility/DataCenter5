/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.activity.report;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import communication.activity.tables.DriversBTWData;
import communication.administration.user.UserSessionData;
import communication.driver.report.DetailedDriverReportDataType;
import communication.language.Text;
import communication.util.ListResult;

public class DriversBTWAssistData extends AbstractTableModel 
    implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total;
    private DriversBTWData[] attributes;
    private boolean color = true;
    private Text messages;
    
    private UserSessionData userSessionData;
    
    private int[][] ppalDivOrgs = new int[4][2];
    
    public DriversBTWAssistData() {
        total = 0;
        attributes = new DriversBTWData[0]; 
    }
    
    public DriversBTWAssistData(ListResult reportDrivers,UserSessionData usr) {   
        total = reportDrivers.getTotal();
        Collection elements = reportDrivers.getElements();
        attributes = (DriversBTWData[])elements.toArray(new DriversBTWData[0]);
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
                return String.valueOf(attributes[rowIndex].getDriverReport().getId());
            case 1:
                return attributes[rowIndex].getDriverReport().getFirstname()
                	+" "+attributes[rowIndex].getDriverReport().getLastname();
            case 2:
            	// Unidad
				return attributes[rowIndex].getDivOrg(ppalDivOrgs[2][0],ppalDivOrgs[2][1]);
            case 3:
            	// Division            	
				return attributes[rowIndex].getDivOrg(ppalDivOrgs[0][0],ppalDivOrgs[0][1]);
            case 4:
				return attributes[rowIndex].getDivOrg(ppalDivOrgs[1][0],ppalDivOrgs[1][1]);
            case 12:
				return attributes[rowIndex].getDivOrg(ppalDivOrgs[3][0],ppalDivOrgs[3][1]);
            case 5: {
            	// Country
                return messages.getText(attributes[rowIndex].getDriverReport().getCountry());
            }        
            
            case 6:
                color = !color;
                return new Boolean(color);
                
            case 7:
                return new Integer(total);
            case 8:
            	// Tipo de Actividad (Nombre del Curso)
            	return (attributes[rowIndex].getType()!=null)?
            			messages.getText(attributes[rowIndex].getType()):" ";
            case 9:
            	Calendar calendar = Calendar.getInstance();
                calendar.setTime(attributes[rowIndex].getLastDate());
                return calendar.get(Calendar.DAY_OF_MONTH)+"-"
                	+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);           
            case 10:
                String allowed = ((DetailedDriverReportDataType)attributes[rowIndex].getDriverReport()).getVehicleallowed();
                return (allowed == null || allowed.length() == 0) ? "---" : messages.getText(allowed);
            case 11:
            	// WWID
            	String corporationId = attributes[rowIndex].getDriverReport().getCorporationId();
            	return (corporationId == null)?"":corporationId;
            case 13:
                return ((DetailedDriverReportDataType)attributes[rowIndex].getDriverReport()).getLicenseObs();
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
            	return "activitytype";
            case 9:
                return "lastdate";
            case 10:
                return "allowed";
            case 11: 
            	return "corporationId";
            case 12:
                return "safety";
            case 13:
                return "licenseObs";
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
