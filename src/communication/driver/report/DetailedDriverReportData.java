/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.driver.report;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import communication.administration.corporation.util.Util;
import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.util.ListResult;

public class DetailedDriverReportData extends AbstractTableModel 
    implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total;
    private DetailedDriverReportDataType[] attributes;
    private boolean color = true;
    private Text messages;

	private Hashtable points;
    
    public DetailedDriverReportData() {
        total = 0;
        attributes = new DetailedDriverReportDataType[0]; 
    }
    
    public DetailedDriverReportData(ListResult reportDrivers) {
        total = reportDrivers.getTotal();
        Collection elements = reportDrivers.getElements();
        Collections.sort((List) elements);
        attributes = (DetailedDriverReportDataType[])elements.toArray(new DetailedDriverReportDataType[0]);
    }

    public int getRowCount() {
        return attributes.length;
    }

    public int getColumnCount() {
        return 56;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return String.valueOf(attributes[rowIndex].getId());
            case 1:
                return attributes[rowIndex].getFirstname();  
            case 2:
                return attributes[rowIndex].getLastname();  
            case 3:
                color = !color;
                return new Boolean(color);
            case 4:
                return Util.formatDate(attributes[rowIndex].getBirthdate(),messages);
            case 5:
                return messages.getText(attributes[rowIndex].getSex());
            case 6:
                return messages.getText(attributes[rowIndex].getNationality());    
            case 7:
                return attributes[rowIndex].getDivorg11();
            case 8:
                return attributes[rowIndex].getDivorg12();
            case 9:
                return attributes[rowIndex].getDivorg13();
            case 10:
                return attributes[rowIndex].getDivorg21();
            case 11:
                return attributes[rowIndex].getDivorg22();
            case 12:
                return attributes[rowIndex].getDivorg23();
            case 13:
                return attributes[rowIndex].getDivorg31();
            case 14:
                return attributes[rowIndex].getDivorg32();
            case 15:
                return attributes[rowIndex].getDivorg33();
            case 16:
                return attributes[rowIndex].getDivgeo1();
            case 17:
                return attributes[rowIndex].getDivgeo2();
            case 18:
                return attributes[rowIndex].getDivgeo3();
            case 19:
                return attributes[rowIndex].getCity();        
            case 20:
            	String licenseExpiry = Util.formatDate(attributes[rowIndex].getLicenseExpiry(),messages);
            	if (attributes[rowIndex].isLicenseNotExpiry()){
            		licenseExpiry = messages.getText("generic.driver.driverdata.licensenotexpiry");
            	}
            	return licenseExpiry;
            case 21:{
            	String licenseCry=attributes[rowIndex].getLicenseCategory();
                if (licenseCry!=null && !licenseCry.equals("null"))
                	return licenseCry;
                else
                	return "-";
            }
            case 22:
                return Util.formatDate(attributes[rowIndex].getLicenseGet(),messages);        
            case 23:
                return messages.getText(attributes[rowIndex].getVehicleallowed());        
            case 24:
                return attributes[rowIndex].getOfficeaddress();        
            case 25:
                return attributes[rowIndex].getEmail();        
            case 26:
                return attributes[rowIndex].getPersonaladdress();
            case 27:{
            	String licenseNer=attributes[rowIndex].getLicensenumber();
                if (licenseNer!=null && !licenseNer.equals("null"))
                	return licenseNer;
                else
                	return "-";
            }
            case 28:
                return attributes[rowIndex].getPhonenumber();
            case 29:
                return Util.formatDate(attributes[rowIndex].getInclusiondate(),messages);
            case 30:
                return Util.formatDate(attributes[rowIndex].getExclusiondate(),messages);
            case 31:{
            	if (attributes[rowIndex].getDrives()==true)
            		return messages.getText("generic.messages.yes");
            	else
            		return messages.getText("generic.messages.no");
            }
            case 32:{
            	if (attributes[rowIndex].getEmployee()==true)
            		return messages.getText("generic.messages.yes");
            	else
            		return messages.getText("generic.messages.no");
            }
            case 33:
                return messages.getText(attributes[rowIndex].getContract());
            case 34:
                return new Integer(total);
            case 35:
            	return messages.getText(attributes[rowIndex].getCountry());
            case 36:
                return Util.formatDate(attributes[rowIndex].getTestDate(),messages);
            case 37:
                if(attributes[rowIndex].isApt()) {
                    return messages.getText("generic.messages.yes");
                }else {
                    return messages.getText("generic.messages.no");
                }
            case 38:
                return Util.formatDate(attributes[rowIndex].getExpiry(),messages);
            case 39:
                return attributes[rowIndex].getBpsRegistry();
            case 40:
                return Util.formatDate(attributes[rowIndex].getLastControl(),messages);
            case 41:
                if(attributes[rowIndex].getStatus() != AlcoholControlReportDataType.NEVER) {
                    if(attributes[rowIndex].getResult().intValue() == DriverData.POSITIVE) {
                        return messages.getText("generic.messages.positive");
                    }else {
                        return messages.getText("generic.messages.negative");
                    }
                }
                return null;
            case 42:
                switch(attributes[rowIndex].getStatus()) {
                    case AlcoholControlReportDataType.NEVER:
                        return messages.getText("driver.alcoholcontrol.neverdone");
                    case AlcoholControlReportDataType.EXPIRED:
                        return messages.getText("driver.alcoholcontrol.expired");
                    default:
                        return messages.getText("driver.alcoholcontrol.effective");
                }
            case 43:
                if(attributes[rowIndex].isAnyPositive()) {
                    return messages.getText("generic.messages.yes");
                }else {
                    return messages.getText("generic.messages.no");
                }
            case 44:{
            	String corpId= attributes[rowIndex].getCorporationId();
            	
            	if(corpId!=null && !corpId.equals("") && !corpId.equals("null")){
            		
            		return corpId;
            	}
            	return "-";
            }
            case 45:
                if(points != null) {
                	if(points.containsKey(attributes[rowIndex].getId())) {
                		return String.valueOf(points.get(attributes[rowIndex].getId())); 
                	}
                }
                return "25";
            case 46:
            	// Astrazeneca y Allergan:
            	// CPF
            	if (attributes[rowIndex].getLicenseCoverage() != null){
            		return attributes[rowIndex].getLicenseCoverage();
            	}else{
            		return "---";
            	}
            case 47:
            	// Astrazeneca y Allergan:
            	// CPF
            	if (attributes[rowIndex].getFederativeEntity() != null){
            		return attributes[rowIndex].getFederativeEntity();
            	}else{
            		return "---";
            	}
            case 48:
            	if (attributes[rowIndex].getAssignedVehicle() != null){
            		return messages.getText(attributes[rowIndex].getAssignedVehicle());
            	}else{
            		return "---";
            	}
            case 49:
            	if (attributes[rowIndex].getSuperviser() != null){
            		return messages.getText(attributes[rowIndex].getSuperviser());
            	}else{
            		return "---";
            	}
            case 50:
            	if (attributes[rowIndex].getManager() != null){
            		return messages.getText(attributes[rowIndex].getManager());
            	}else{
            		return "---";
            	}
            case 51:
            	if (attributes[rowIndex].getIddallas() != null){
            		return messages.getText(attributes[rowIndex].getIddallas());
            	}else{
            		return "---";
            	}
            case 52:
            	if (attributes[rowIndex].getAuthorizateWho() != null){
            		return attributes[rowIndex].getAuthorizateWho();
            	}else{
            		return "---";
            	}
            case 53:
            	return attributes[rowIndex].getLicenseObs();
            case 54:
    			return (attributes[rowIndex].isDirector()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 55:
    			return (attributes[rowIndex].isTemporal()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
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
                return "lastname";
            case 3:
                return "color";
            case 4:
                return "birthdate";
            case 5:
                return "sex";
            case 6:
                return "nationality";
            case 7:
                return "divorg11";
            case 8:
                return "divorg12";
            case 9:
                return "divorg13";
            case 10:
                return "divorg21";
            case 11:
                return "divorg22";
            case 12:
                return "divorg23";
            case 13:
                return "divorg31";
            case 14:
                return "divorg32";
            case 15:
                return "divorg33";
            case 16:
                return "divgeo1";
            case 17:
                return "divgeo2";
            case 18:
                return "divgeo3";
            case 19:
                return "city";
            case 20:
                return "licenseExpiry";
            case 21:
                return "licenseCategory";
            case 22:
                return "licenseGet";
            case 23:
                return "vehicleallowed";
            case 24:
                return "officeaddress";
            case 25:
                return "email";
            case 26:
                return "personaladdress";
            case 27:
                return "licensenumber";
            case 28:
                return "phonenumber";
            case 29:
                return "inclusiondate";
            case 30:
                return "exclusiondate";
            case 31:
                return "drives";
            case 32:
                return "employee";
            case 33:
                return "contract";
            case 34:
            	return "total";
            case 35: 
            	return "country";
            case 36:
                return "test";
            case 37:
                return "apt";
            case 38:
                return "expiry";
            case 39:
                return "bps";
            case 40:
                return "lastControl";
            case 41:
                return "result";
            case 42:
                return "status";
            case 43:
                return "anypositive";
            case 44:
                return "corporationid";
            case 45:
                return "points";
            case 46:
            	return "licenseCoverage"; // CPF para allergan y Astrazeneca
            case 47:
            	return "federativeEntity"; // RG para Allergan y Astrazeneca
            case 48:
            	return "assignedVehicle";
            case 49:
            	return "superviser";
            case 50:
            	return "manager";
            case 51:
            	return "iddallas";
            case 52:
            	return "authorizatewho";
            case 53:
            	return "licenseobs";
            case 54:
    			return "director";
            case 55:
    			return "temporal";
        }
        return null;
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }

    public int getTotal() {
        return total;
    }

	public void setPoints(Hashtable driverPoints) {
		points = driverPoints;
	}

	public Hashtable getPoints() {
		return points;
	}
}
