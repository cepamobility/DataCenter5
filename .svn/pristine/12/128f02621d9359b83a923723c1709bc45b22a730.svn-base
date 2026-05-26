/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.driver.report;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import communication.administration.corporation.util.Util;
import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.util.ListResult;

public class FOSADetailedDriverReportData extends AbstractTableModel 
    implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total;
    private DetailedFOSADriverReportDataType[] attributes;
    private boolean color = true;
    private Text messages;
    
    public FOSADetailedDriverReportData() {
        total = 0;
        attributes = new DetailedFOSADriverReportDataType[0]; 
    }
    
    public FOSADetailedDriverReportData(ListResult reportDrivers) {
        total = reportDrivers.getTotal();
        Collection elements = reportDrivers.getElements();
        Collections.sort((List) elements);
        attributes = (DetailedFOSADriverReportDataType[])elements.toArray(new DetailedFOSADriverReportDataType[0]);
    }

    public int getRowCount() {
        return attributes.length;
    }

    public int getColumnCount() {
        return 52;
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
                return attributes[rowIndex].getCount();
            case 46:
                return Util.formatDate(attributes[rowIndex].getLast(),messages);
            case 47:
                return Util.formatDate(attributes[rowIndex].getLastEC(),messages);
            case 48:
            	return Util.formatDate(attributes[rowIndex].getLastRadar(),messages);
            case 49:
            	return Util.formatDate(attributes[rowIndex].getLastInduction(),messages);
            case 50:
            	return messages.getText(attributes[rowIndex].getRadarNote());
            case 51:
            	return Util.formatDate(attributes[rowIndex].getHealth(),messages);
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
                return "count";
            case 46:
                return "last";
            case 47:
                return "lastec";
            case 48:
                return "lastRadar";
            case 49:
                return "lastInduction";
            case 50:
                return "noteRadar";
            case 51:
                return "health";
        }
        return null;
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }

    public int getTotal() {
        return total;
    }
}
