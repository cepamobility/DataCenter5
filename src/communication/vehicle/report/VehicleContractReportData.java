package communication.vehicle.report;

import java.io.Serializable;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import communication.administration.corporation.util.Util;
import communication.language.Text;

/**
 * Data para el reporte de contratos que muestra la lista de
 * vehiculos con su conductores y contratos asociados.
 * 
 * @author gbenaderet
 *
 */
public class VehicleContractReportData extends AbstractTableModel 
	implements Serializable {

    static final long serialVersionUID = 1L;
    
    int total;
    VehicleContractReportDataType[] attributes;
    boolean color = true;
    Text messages;
    
    public VehicleContractReportData() {
        attributes = new VehicleContractReportDataType[0]; 
        total = attributes.length;
    }
    
    public VehicleContractReportData(Collection reportDrivers, int total) {
        this.total = total; 
        attributes = (VehicleContractReportDataType[])reportDrivers.toArray(new VehicleContractReportDataType[0]);
    }

    public int getRowCount() {
        return attributes.length;
    }

    public int getColumnCount() {
        return 24;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return attributes[rowIndex].getVehicle().getMatricula();
            case 1:
                return messages.getText(attributes[rowIndex].getVehicle().getTrade().getResourceBundleId());
            case 2:
                color = !color;
                return new Boolean(color);
            case 3:
            	if (attributes[rowIndex].getVehicle().getType() == null){
            		// Trailer:
            		return messages.getText("vehicle.data.trailer");
            	}else {
            		// Vehicles:
            		return messages.getText(attributes[rowIndex].getVehicle().getType().getResourceBundleId());
            	}
            case 4:
                return attributes[rowIndex].getVehicle().getModel();
            case 5:
                return attributes[rowIndex].getVehicle().getYear();
            case 6:
                return messages.getText(attributes[rowIndex].getVehicle().getCountryName());
            case 7:
                return attributes[rowIndex].getVehicle().getData(1,1);
            case 8:
                return attributes[rowIndex].getVehicle().getData(1,2);
            case 9:
                return attributes[rowIndex].getVehicle().getData(1,3);
            case 10:
                return attributes[rowIndex].getVehicle().getData(2,1);
            case 11:
                return attributes[rowIndex].getVehicle().getData(2,2);
            case 12:
                return attributes[rowIndex].getVehicle().getData(2,3);
            case 13:
                return attributes[rowIndex].getVehicle().getData(3,1);
            case 14:
                return attributes[rowIndex].getVehicle().getData(3,2);
            case 15:
                return attributes[rowIndex].getVehicle().getData(3,3);
            case 16:
                return attributes[rowIndex].getVehicle().getId().toString();
            case 17:
            	return new Integer(total);  
            case 18:
            	if (attributes[rowIndex].getVehicle().getType() == null)
            		// Trailer:
            		return "true";
            	else
            		// Vehicle:
            		return "false";           
            case 19:
            	if (attributes[rowIndex].getVehicle().getDriver() != null &&
            			attributes[rowIndex].getVehicle().getDriver().getFirstName() != null){
            		return  attributes[rowIndex].getVehicle().getDriver().getFirstName();
            	}
            	return "---";
            case 20:
            	if (attributes[rowIndex].getVehicle().getDriver() != null &&
            			attributes[rowIndex].getVehicle().getDriver().getLastName() != null){
            		return  attributes[rowIndex].getVehicle().getDriver().getLastName();
            	}
            	return "---";
            case 21:
            	if (attributes[rowIndex].getContractDate() != null){
            		return Util.formatDate(attributes[rowIndex].getContractDate());
            	}
            	return "---";
            case 22: 
            	if (attributes[rowIndex].getContractType() != null){
            		return messages.getText(attributes[rowIndex].getContractType());
            	}
            	return "---";
            case 23:
            	// Numero de RENAVAM
            	if (attributes[rowIndex].getVehicle().getPermissionNumber()!= null &&
                		attributes[rowIndex].getVehicle().getPermissionNumber().trim() != "")            	
                	return attributes[rowIndex].getVehicle().getPermissionNumber();
                else return "---";   
            	
        }
        return null;
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "plate"; // matricula
            case 1:
                return "trade"; // marca
            case 2:
                return "color";
            case 3:
                return "type"; // tipo de vehiculo
            case 4:
                return "model";
            case 5:
                return "year";
            case 6:
                return "country";
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
                return "id";
            case 17:
            	return "total";
            case 18:
            	return "isTrailer"; // si es trailer o vehiculo
            
            case 19:
            	return "driverName";
            case 20:
            	return "driverLastName";
            case 21:
            	return "contractDate";
            case 22: 
            	return "contractType";
            case 23:
            	return "renavam";
            	
           
        }
        return null;
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }
}
