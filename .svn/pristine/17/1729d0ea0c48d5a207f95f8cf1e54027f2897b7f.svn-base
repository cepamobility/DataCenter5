package communication.vehicle.report;

import java.io.Serializable;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;

public class VehicleWithoutPursuitRouteData extends AbstractTableModel 
    implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total = 0;
    private int cantReg = 0;
    private VehicleData[] attributes;
    private boolean color = true;
    private Text messages;
    private UserSessionData userSessionData;

    private int[][] principal;
    
    public VehicleWithoutPursuitRouteData() {
        total = 0;
        attributes = new VehicleData[0];
        principal = new int[3][2];
        cantReg = 0;
    }
    
    public VehicleWithoutPursuitRouteData(Collection reportDrivers,int total, UserSessionData usr) {
        this.cantReg = reportDrivers.size();
        attributes = (VehicleData[])reportDrivers.toArray(new VehicleData[0]);
        userSessionData = usr;
       /* int[][] pdo = userSessionData.getFilter().getPrincipalDivOrgs();
    	for (int i= 0; i<3; i++) {
    		principal[i][0]= pdo[i][0];
    		principal[i][1]= getNivel(i);
    	}*/
        principal = userSessionData.getFilter().getPrincipalDivOrgs();
        this.total = total;
    }

    public int getRowCount() {
        return cantReg;
    }

    public int getColumnCount() {
        return 8;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
    	String result;
        switch(columnIndex) {
            case 0:
                return String.valueOf(attributes[rowIndex].getId());
            case 1:
                result = attributes[rowIndex].getMatricula();
                return (result != null)?result:"";
            case 2:
            	result = attributes[rowIndex].getData(DriverData.DIV_ORG,principal[0][0],principal[0][1]);
                return (result != null)?result:"";
            case 3:
                result =  attributes[rowIndex].getData(DriverData.DIV_ORG,principal[1][0],principal[1][1]);
                return (result != null)?result:"";
            case 4:
                result = attributes[rowIndex].getData(DriverData.DIV_ORG,principal[2][0],principal[2][1]);
                return (result != null)?result:"";
            case 5:
            	result = messages.getText(attributes[rowIndex].getCountryName());
            	return (result != null)?result:"";
            case 6:
                color = !color;
                return new Boolean(color);
            case 7:
            	return new Integer(total);
        }
        return "";
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "id";
            case 1:
                return "plate";
            case 2:
                return "divorg1";
            case 3:
                return "divorg2";
            case 4:
                return "divorg3";
            case 5:
            	return "country";
            case 6:
            	return "color";
            case 7:
            	return "total";
        }
        return "";
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }
    
    public Text getMessages(){
    	return this.messages;
    }

	/**
	 * @return Returns the total.
	 */
	public int getTotal() {
		return total;
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


