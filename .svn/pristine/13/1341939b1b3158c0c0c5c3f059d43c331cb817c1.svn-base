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

public class DetailedDriverReportWithVehiclesData extends AbstractTableModel 
    implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total;
    private DetailedDriverReportWithVehiclesDataType[] attributes;
    private boolean color = true;
    private Text messages;
    
    private Integer lastId = new Integer(0);

	private Hashtable points;
    
    public DetailedDriverReportWithVehiclesData() {
        total = 0;
        attributes = new DetailedDriverReportWithVehiclesDataType[0]; 
    }
    
    public DetailedDriverReportWithVehiclesData(ListResult reportDrivers) {
        total = reportDrivers.getTotal();
        Collection elements = reportDrivers.getElements();
        Collections.sort((List) elements);
        attributes = (DetailedDriverReportWithVehiclesDataType[])elements.toArray(new DetailedDriverReportWithVehiclesDataType[0]);
    }

    public int getRowCount() {
        return attributes.length;
    }

    public int getColumnCount() {
        return 74;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return String.valueOf(attributes[rowIndex].getId());
            case 1:
            	if (lastId.equals(attributes[rowIndex].getId()))
            		return "";
            	else 
            		return attributes[rowIndex].getFirstname();  
            case 2:
            	if (lastId.equals(attributes[rowIndex].getId()))
            		return "";
            	else 
            		return attributes[rowIndex].getLastname();  
            case 3:
            	 if (!lastId.equals(attributes[rowIndex].getId())){
 	            	color = !color;
 	             }
                 return new Boolean(color);
               
            case 4:
            	if (lastId.equals(attributes[rowIndex].getId())) 
            		return "";
            	else
            		return Util.formatDate(attributes[rowIndex].getBirthdate(),messages);
            case 5:
            	if (lastId.equals(attributes[rowIndex].getId()))
            		return "";
            	else
            		return messages.getText(attributes[rowIndex].getSex());
            case 6:
            	if (lastId.equals(attributes[rowIndex].getId())) 
            		return "";
            	else
            		return messages.getText(attributes[rowIndex].getNationality());    
            case 7:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return attributes[rowIndex].getDivorg11();
            case 8:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return attributes[rowIndex].getDivorg12();
            case 9:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return attributes[rowIndex].getDivorg13();
            case 10:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return attributes[rowIndex].getDivorg21();
            case 11:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return attributes[rowIndex].getDivorg22();
            case 12:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return attributes[rowIndex].getDivorg23();
            case 13:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return attributes[rowIndex].getDivorg31();
            case 14:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return attributes[rowIndex].getDivorg32();
            case 15:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return attributes[rowIndex].getDivorg33();
            case 16:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return attributes[rowIndex].getDivgeo1();
            case 17:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return attributes[rowIndex].getDivgeo2();
            case 18:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return attributes[rowIndex].getDivgeo3();
            case 19:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return attributes[rowIndex].getCity();        
            case 20:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
            	String licenseExpiry = Util.formatDate(attributes[rowIndex].getLicenseExpiry(),messages);
            	if (attributes[rowIndex].isLicenseNotExpiry()){
            		licenseExpiry = messages.getText("generic.driver.driverdata.licensenotexpiry");
            	}
            	return licenseExpiry;
            case 21:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
            	String licenseCry=attributes[rowIndex].getLicenseCategory();
                if (licenseCry!=null && !licenseCry.equals("null"))
                	return licenseCry;
                else
                	return "-";
            
            case 22:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return Util.formatDate(attributes[rowIndex].getLicenseGet(),messages);        
            case 23:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return messages.getText(attributes[rowIndex].getVehicleallowed());        
            case 24:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return attributes[rowIndex].getOfficeaddress();        
            case 25:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return attributes[rowIndex].getEmail();        
            case 26:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return attributes[rowIndex].getPersonaladdress();
            case 27:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
            	String licenseNer=attributes[rowIndex].getLicensenumber();
                if (licenseNer!=null && !licenseNer.equals("null"))
                	return licenseNer;
                else
                	return "-";
            
            case 28:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return attributes[rowIndex].getPhonenumber();
            case 29:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return Util.formatDate(attributes[rowIndex].getInclusiondate(),messages);
            case 30:
            	if (lastId.equals(attributes[rowIndex].getId())) 
            		return "";
                return Util.formatDate(attributes[rowIndex].getExclusiondate(),messages);
            case 31:
            	if (lastId.equals(attributes[rowIndex].getId())) 
            		return "";
            	
            	if (attributes[rowIndex].getDrives()==true)
            		return messages.getText("generic.messages.yes");
            	else
            		return messages.getText("generic.messages.no");            	
            case 32:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
            	if (attributes[rowIndex].getEmployee()==true)
            		return messages.getText("generic.messages.yes");
            	else
            		return messages.getText("generic.messages.no");            	
            case 33:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return messages.getText(attributes[rowIndex].getContract());
            case 34:
                return new Integer(total);
            case 35:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
            	return messages.getText(attributes[rowIndex].getCountry());
            case 36:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return Util.formatDate(attributes[rowIndex].getTestDate(),messages);
            case 37:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                if(attributes[rowIndex].isApt()) {
                    return messages.getText("generic.messages.yes");
                }else {
                    return messages.getText("generic.messages.no");
                }
            case 38:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return Util.formatDate(attributes[rowIndex].getExpiry(),messages);
            case 39:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return attributes[rowIndex].getBpsRegistry();
            case 40:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                return Util.formatDate(attributes[rowIndex].getLastControl(),messages);
                
            case 41:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                if(attributes[rowIndex].getStatus() != AlcoholControlReportDataType.NEVER) {
                    if(attributes[rowIndex].getResult().intValue() == DriverData.POSITIVE) {
                        return messages.getText("generic.messages.positive");
                    }else {
                        return messages.getText("generic.messages.negative");
                    }
                }
                return null;
            case 42:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                switch(attributes[rowIndex].getStatus()) {
                    case AlcoholControlReportDataType.NEVER:
                        return messages.getText("driver.alcoholcontrol.neverdone");
                    case AlcoholControlReportDataType.EXPIRED:
                        return messages.getText("driver.alcoholcontrol.expired");
                    default:
                        return messages.getText("driver.alcoholcontrol.effective");
                }
            case 43:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                if(attributes[rowIndex].isAnyPositive()) {
                    return messages.getText("generic.messages.yes");
                }else {
                    return messages.getText("generic.messages.no");
                }
            case 44:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
            	String corpId= attributes[rowIndex].getCorporationId();
            	
            	if(corpId!=null && !corpId.equals("") && !corpId.equals("null")){
            		
            		return corpId;
            	}
            	return "---";
            
            case 45:
            	if (lastId.equals(attributes[rowIndex].getId())) return "";
                if(points != null) {
                	if(points.containsKey(attributes[rowIndex].getId())) {
                		return String.valueOf(points.get(attributes[rowIndex].getId())); 
                	}
                }
                return "25";
                
            case 46:
            	// Datos Vehiculo del Driver
            	// Matricula
            	if (attributes[rowIndex].getVehicleData() != null &&
            			attributes[rowIndex].getVehicleData().getMatricula() != null ) {
            		return attributes[rowIndex].getVehicleData().getMatricula();
            	}else{
            		return "---";
            	}
            case 47:
            	// Datos Vehiculo del Driver
            	// Tipo de Vehiculo
            	if (attributes[rowIndex].getVehicleData() != null &&
            			attributes[rowIndex].getVehicleData().getType() != null ) {
            		return messages.getText(attributes[rowIndex].getVehicleData().getType().getResourceBundleId());
            	}else{
            		return "---";
            	}
            case 48:
            	// Datos Vehiculo del Driver
            	// Marca
            	if (attributes[rowIndex].getVehicleData() != null &&
            			attributes[rowIndex].getVehicleData().getTrade() != null ) {
            		return messages.getText(attributes[rowIndex].getVehicleData().getTrade().getResourceBundleId());
            	}else{
            		return "---";
            	}
            case 49:
            	// Datos Vehiculo del Driver
            	// Modelo
            	if (attributes[rowIndex].getVehicleData() != null &&
            			attributes[rowIndex].getVehicleData().getModel() != null ) {
            		return attributes[rowIndex].getVehicleData().getModel();
            	}else{
            		return "---";
            	}
            case 50:
            	// Datos Vehiculo del Driver
            	// Año
            	if (attributes[rowIndex].getVehicleData() != null &&
            			attributes[rowIndex].getVehicleData().getYear() != null ) {
            		return attributes[rowIndex].getVehicleData().getYear().toString();
            	}else{
            		return "---";
            	}
            case 51:
            	// Datos Vehiculo del Driver
            	// Fecha de Inclusion del vehiculo
            	if (attributes[rowIndex].getVehicleData() != null &&
            			attributes[rowIndex].getVehicleData().getInclusionDate() != null ) {
            		return Util.formatDate(attributes[rowIndex].getVehicleData().getInclusionDate(),messages);
            	}else{
            		return "---";
            	}
            case 52:
            	// Datos Vehiculo del Driver
            	// Vehiculo Designado: Job Car, etc...
            	
            	 //if (!lastId.equals(attributes[rowIndex].getId())){
            		 // Por ser el ultimo campo mostrado cambio el lastId
                     lastId = attributes[rowIndex].getId();
                //  }
            	
            	if (attributes[rowIndex].getVehicleData() != null &&
            			attributes[rowIndex].getVehicleData().getAssignedVehicle() != null ) {
            		return messages.getText(attributes[rowIndex].getVehicleData().getAssignedVehicle().getResourceBundleId());
            	}else{
            		return "---";
            	}
            case 53:
            	// Datos Vehiculo del Driver
            	// Cost Center (Centro do Costo, Vehiculos)
            	if (attributes[rowIndex].getVehicleData() != null &&
            			attributes[rowIndex].getVehicleData().getCostCenter() != null ) {
            		return attributes[rowIndex].getVehicleData().getCostCenter();
            	}else{
            		return "---";
            	}
            case 54:
            	// ID Dallas, campo de Driver
            	if (lastId.equals(attributes[rowIndex].getId())) 
            		return "";
            	else if (attributes[rowIndex].getIdDallas() != null){
            		return attributes[rowIndex].getIdDallas();
            	}else{
            		return "---";
            	}
            	
            // Nuevos Campos Solicitados el 8/11/2011:
            	
            case 55:
            	// Datos Vehiculo del Driver
            	// Numero de RENAVAM
            	if (attributes[rowIndex].getVehicleData().getPermissionNumber()!= null &&
                		attributes[rowIndex].getVehicleData().getPermissionNumber().trim() != "")            	
                		return attributes[rowIndex].getVehicleData().getPermissionNumber();
                	else return "---";   
            	
            case 56:
            	// Datos Vehiculo del Driver
            	// Fuel
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
            	if (attributes[rowIndex].getVehicleData().getFuel()!=null)
            		return messages.getText(attributes[rowIndex].getVehicleData().
            				getFuel().getResourceBundleId());
            	else
            		return "---";
            
            case 57:
            	// Datos Vehiculo del Driver
            	// Fecha de Compra
            	if (attributes[rowIndex].getVehicleData() != null && attributes[rowIndex].getVehicleData().getPurchaseDate()!=null)
            		return Util.formatDate(attributes[rowIndex].getVehicleData().
            				getPurchaseDate(),messages);
            	else
            		return "---";
            
            case 58:
            	// Datos Vehiculo del Driver
            	// Numero de Chasis
            	if (attributes[rowIndex].getVehicleData() != null && attributes[rowIndex].getVehicleData().getChasisNumber() != null &&
            			attributes[rowIndex].getVehicleData().getChasisNumber().trim() != ""	)
            		return attributes[rowIndex].getVehicleData().getChasisNumber();
            	else
            		return "---";
            	
            case 59:
            	// Datos Vehiculo del Driver
            	// Motor
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
            	if (attributes[rowIndex].getVehicleData() != null && attributes[rowIndex].getVehicleData().getEngine()!=null)
            		return messages.getText(attributes[rowIndex].getVehicleData().getEngine().getResourceBundleId());
            	else
            		return "---";
            	
            case 60:
            	// Datos Vehiculo del Driver
            	// Capacidad del Tanque
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
            	if (attributes[rowIndex].getVehicleData() != null && attributes[rowIndex].getVehicleData().getTankCapacity()!=null)
            		return attributes[rowIndex].getVehicleData().getTankCapacity();
            	else
            		return "---";
            	
            case 61:
            	// Datos Vehiculo del Driver
            	// Unidad de volumen del tanque
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
            	if (attributes[rowIndex].getVehicleData() != null && attributes[rowIndex].getVehicleData().getTankUnit()!=null)
            		return messages.getText(attributes[rowIndex].getVehicleData().getTankUnit().getResourceBundleId());
            	else
            		return "---";
            
            case 62:
            	// Datos Vehiculo del Driver
            	// ABS
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
                if(attributes[rowIndex].getVehicleData() != null && attributes[rowIndex].getVehicleData().getAbs()) {
                    return messages.getText("generic.messages.yes");
                }else {
                    return messages.getText("generic.messages.no");
                }
                
            case 63:
            	// Datos Vehiculo del Driver
            	// AirBag
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
                if(attributes[rowIndex].getVehicleData() != null && attributes[rowIndex].getVehicleData().getAirBag()) {
                    return messages.getText("generic.messages.yes");
                }else {
                    return messages.getText("generic.messages.no");
                }
                
            case 64:
            	// Datos Vehiculo del Driver
            	// Aire Acondicionado
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
                if(attributes[rowIndex].getVehicleData() != null && attributes[rowIndex].getVehicleData().getAireAcondicionado()) {
                    return messages.getText("generic.messages.yes");
                }else {
                    return messages.getText("generic.messages.no");
                }
            
            case 65:
            	// Datos Vehiculo del Driver
            	// Cubiertas
            	  if(attributes[rowIndex].getVehicleData() == null || attributes[rowIndex].getVehicleData().getCover() == null) {
                      return "---";
                  }
                  if(attributes[rowIndex].getVehicleData().getCover().equals("generic.messages.nodata")) {
                      return messages.getText(attributes[rowIndex].getVehicleData().getCover().getResourceBundleId());
                  }
                  return attributes[rowIndex].getVehicleData().getCover().getResourceBundleId();
            	
            case 66:
            	// Datos Vehiculo del Driver
            	// Unidad de distancia
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
                if(attributes[rowIndex].getVehicleData() != null && attributes[rowIndex].getVehicleData().getOdometerUnit() != null) {
                    return messages.getText(attributes[rowIndex].getVehicleData().getOdometerUnit().getResourceBundleId());
                }
                return "---";
                
            case 67: 
            	// Datos Vehiculo del Driver
            	// Ultimo Odometro
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
                if(attributes[rowIndex].getLastOdometer() != null) {
                    return attributes[rowIndex].getLastOdometer();
                }
                return "---";
            	
            case 68:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
                if(attributes[rowIndex].getVehicleData().getColour() != null) {
                    return messages.getText(attributes[rowIndex].getVehicleData().getColour());
                }
                return "---";
            case 69:
    			return (attributes[rowIndex].isDirector()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 70:
            	if (attributes[rowIndex].getSuperviser() != null){
            		return messages.getText(attributes[rowIndex].getSuperviser());
            	}else{
            		return "---";
            	}
            case 71:
            	if (attributes[rowIndex].getManager() != null){
            		return messages.getText(attributes[rowIndex].getManager());
            	}else{
            		return "---";
            	}
            case 72:
    			return (attributes[rowIndex].isDirector()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 73:
    			return (attributes[rowIndex].isTemporal()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
        }
        
        return "";
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
            	return "plate";
            case 47:
            	return "vehicleType";
            case 48:
            	return "trade";
            case 49:
            	return "model";
            case 50:
            	return "year";
            case 51: 
            	return "vehicleInclusionDate";
            case 52:
            	return "assignedVehicle";
            case 53:
            	return "costCenter";
            case 54:
            	return "idDallas";
            case 55:
            	 return "permissionNumber";
            case 56:
            	 return "fuel";
            case 57:
            	 return "purchaseDate";
            case 58:
            	 return "chasisNumber";                
            case 59:
                return "engine";
            case 60:
                return "tankCapacity";
            case 61:
                return "tankUnit";
            case 62:
                return "abs";
            case 63:
                return "airBag";
            case 64:
                return "aireAcondicionado";
            case 65:
                return "cover";
            case 66:
                return "odometerUnit";
            case 67:
            	return "odometer";
            case 68:
            	return "colour";
            case 69:
            	return "director";
            case 70:
            	return "superviser";
            case 71:
            	return "manager";
            case 72:
            	return "director";
            case 73:
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

	/**
	 * @return the lastId
	 */
	public Integer getLastId() {
		return lastId;
	}

	/**
	 * @param lastId the lastId to set
	 */
	public void setLastId(Integer lastId) {
		this.lastId = lastId;
	}
	
    /**
     * 
     * @param rowIndex
     * @return
     */
    private boolean isTrailer(int rowIndex){
    	if (attributes[rowIndex].getVehicleData().getType() == null)
    		// Trailer:
    		return true;
    	else
    		// Vehicle:
    		// Para los vehiculos siempre existe un Type.
    		return false;   
    }
	
}
