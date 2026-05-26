package communication.activity.report;

import java.io.Serializable;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import communication.administration.user.UserSessionData;
import communication.driver.report.DetailedDriverReportDataType;
import communication.language.Text;
/**
 * @author gbenaderet
 *
 */
public class ActivityReportCommentaryDriveData extends AbstractTableModel 
	implements Serializable {

    static final long serialVersionUID = 1L;
    
    private ActivityReportCommentaryDriveDataType[] attributes;
    private boolean color = true;
    private Text messages;   
    private int total;
    
    private int[][] ppalDivOrgs = new int[3][2];    
    
    private UserSessionData userSessionData;    
    
    public ActivityReportCommentaryDriveData(UserSessionData usr) {    	
        attributes = new ActivityReportCommentaryDriveDataType[0]; 
        total = attributes.length;
        userSessionData = usr;
        ppalDivOrgs = userSessionData.getFilter().getPrincipalDivOrgs();
    }
       
    
    public ActivityReportCommentaryDriveData(Collection details, int total,UserSessionData usr) {
        attributes = (ActivityReportCommentaryDriveDataType[])details.toArray(new ActivityReportCommentaryDriveDataType[0]);
        this.total = total;
        userSessionData = usr;        
        ppalDivOrgs = userSessionData.getFilter().getPrincipalDivOrgs();
    }
    
    public ActivityReportCommentaryDriveData(ActivityReportCommentaryDriveDataType[] attributes,Text messages,UserSessionData usr) {
        this.attributes = attributes;        
        this.messages = messages;
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
                return attributes[rowIndex].getDriver().getFirstname();
            case 1:
            	return attributes[rowIndex].getDriver().getLastname();
            case 2:
            	return messages.getText(attributes[rowIndex].getDriver().getCountry());
            case 3:
				return attributes[rowIndex].getDivOrg(ppalDivOrgs[0][0],ppalDivOrgs[0][1]);
            case 4:
				return attributes[rowIndex].getDivOrg(ppalDivOrgs[1][0],ppalDivOrgs[1][1]);
            	// Division            	
            case 5:
				return attributes[rowIndex].getDivOrg(ppalDivOrgs[2][0],ppalDivOrgs[2][1]);
            case 12:
				return attributes[rowIndex].getDivOrg(ppalDivOrgs[3][0],ppalDivOrgs[3][1]);
            case 6:
            	return attributes[rowIndex].getCount();
            case 7:
            	return attributes[rowIndex].getDriver().getId().toString();
            case 8:    
                color = !color;
                return new Boolean(color);
            case 9:
            	return attributes[rowIndex].getLastName() + " " + attributes[rowIndex].getFirstName();
            case 10:
            	return new Integer(total);          	
            case 11:
                String allowed = ((DetailedDriverReportDataType)attributes[rowIndex].getDriver()).getVehicleallowed();
                return (allowed == null || allowed.length() == 0) ? "---" : messages.getText(allowed);                          	
            case 13:
                return ((DetailedDriverReportDataType)attributes[rowIndex].getDriver()).getLicenseObs();
        }
        return null;
    }

    public String getColumnName(int column) {
    	switch(column) {
            case 0:
                return "firstname"; // Nombre
            case 1:
                return "lastname"; // Apellido
            case 2:
                return "country"; // Pais
            case 3:
                return "division"; // Division
            case 4:
                return "subdivision"; // SubDivision
            case 5:
            	return "unit"; // Unidad
            case 6:
            	return "count"; // Cantidad de Commentary Drives
            case 7:
            	return "id"; // Id del Conductor
            case 8:
            	return "color"; // Color para los renglones
            case 9:
            	return "completename"; // Apellido y nombre.
            case 10:
                return "total";
            case 11:
                return "allowed";
            case 12:
                return "safety";
            case 13:
                return "licenseObs";
        }
        return "id";
    }

    public int getTotal() {
        return total;
    }

	/**
	 * @return Returns the attributes.
	 */
	public ActivityReportCommentaryDriveDataType[] getAttributes() {
		return attributes;
	}



	/**
	 * @param attributes The attributes to set.
	 */
	public void setAttributes(ActivityReportCommentaryDriveDataType[] attributes) {
		this.attributes = attributes;
	}



	/**
	 * @return Returns the color.
	 */
	public boolean isColor() {
		return color;
	}



	/**
	 * @param color The color to set.
	 */
	public void setColor(boolean color) {
		this.color = color;
	}



	/**
	 * @return Returns the messages.
	 */
	public Text getMessages() {
		return messages;
	}



	/**
	 * @param messages The messages to set.
	 */
	public void setMessages(Text messages) {
		this.messages = messages;
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
