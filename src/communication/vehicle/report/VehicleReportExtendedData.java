/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.vehicle.report;

import java.io.Serializable;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.util.Util;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;

public class VehicleReportExtendedData extends AbstractTableModel 
    implements Serializable {

    static final long serialVersionUID = 1L;
	
    private int total;
    private VehicleData[] attributes;
    private boolean color = true;
    private Text messages;
    private int corporation;
    
    
	public VehicleReportExtendedData() {
        total = 0;
        attributes = new VehicleData[0];
        corporation = 0;
    }
	
    public int getRowCount() {
        return attributes.length;
    }    
    
    public VehicleReportExtendedData(Collection reportDrivers, int total) {
        this.total = total; //reportDrivers.size();
        attributes = (VehicleData[])reportDrivers.toArray(new VehicleData[0]);	
        corporation = 0;
    }

    public int getColumnCount() {
    	return 64;//63
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return attributes[rowIndex].getMatricula();
            case 1:
            	if (attributes[rowIndex].getTrade() != null){
            		return (attributes[rowIndex].getTrade().getResourceBundleId().equals("generic.messages.nodata")) ? messages.getText(attributes[rowIndex].getTrade().getResourceBundleId()) : attributes[rowIndex].getTrade().getResourceBundleId();
            	}else
            		return "---";
            case 2:
                color = !color;
                return new Boolean(color);
            case 3:
            	if (attributes[rowIndex].getType() == null){
            		// Trailer:
            		return messages.getText("vehicle.data.trailer");
            	}else{
            		//Vehicles:
            		return messages.getText(attributes[rowIndex].getType().getResourceBundleId());
            	}            		
            case 4:
            	if (attributes[rowIndex].getModel() != null){
            		return attributes[rowIndex].getModel();
            	}else 
            		return "---";
                
            case 5:
            	if (attributes[rowIndex].getYear() != null &&
            			!attributes[rowIndex].getYear().equals("1000"))
            		return attributes[rowIndex].getYear();
            	else
            		return "---";
            case 6:
            	if (attributes[rowIndex].getCountryName() != null){
            		return messages.getText(attributes[rowIndex].getCountryName());
            	}else
            		return "---";            	
            case 7:
                return attributes[rowIndex].getData(1,1);
            case 8:
                return attributes[rowIndex].getData(1,2);
            case 9:
                return attributes[rowIndex].getData(1,3);
            case 10:
                return attributes[rowIndex].getData(2,1);
            case 11:
                return attributes[rowIndex].getData(2,2);
            case 12:
                return attributes[rowIndex].getData(2,3);
            case 13:
                return attributes[rowIndex].getData(3,1);
            case 14:
                return attributes[rowIndex].getData(3,2);
            case 15:
                return attributes[rowIndex].getData(3,3);
            case 16:
                return attributes[rowIndex].getId().toString();
            case 17:
            	if (attributes[rowIndex].getColour() != null && 
            		attributes[rowIndex].getColour().trim() != "") 
            		return attributes[rowIndex].getColour();
            	else 
            		return "---";
            case 18:
            	if (attributes[rowIndex].getPermissionNumber()!= null &&
            		attributes[rowIndex].getPermissionNumber().trim() != "")            	
            		return attributes[rowIndex].getPermissionNumber();
            	else return "---";            	
            case 19:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
                return messages.getText(attributes[rowIndex].getUse().getResourceBundleId());
            case 20:
            	if (attributes[rowIndex].getPermissionExpiry()!=null)            	
            		return Util.formatDate(attributes[rowIndex].getPermissionExpiry(),messages);
            	else 
            		return "----";
            case 21:
            	if (attributes[rowIndex].getOwnerShip()!=null)
            		return messages.getText(attributes[rowIndex].getOwnerShip().getResourceBundleId());
            	else 
            		return "---";
            case 22:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
            	if (attributes[rowIndex].getFuel()!=null)
            		return messages.getText(attributes[rowIndex].getFuel().getResourceBundleId());
            	else
            		return "---";
            case 23:
            	if (attributes[rowIndex].getPurchaseDate()!=null)
            		return Util.formatDate(attributes[rowIndex].getPurchaseDate(),messages);
            	else
            		return "---";
            case 24:
            	if (attributes[rowIndex].getChasisNumber() != null &&
            			attributes[rowIndex].getChasisNumber().trim() != ""	)
            		return attributes[rowIndex].getChasisNumber();
            	else
            		return "---";
            case 25:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
            	if (attributes[rowIndex].getEngine()!=null)
            		return messages.getText(attributes[rowIndex].getEngine().getResourceBundleId());
            	else
            		return "---";
            case 26:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
            	if (attributes[rowIndex].getTankCapacity()!=null)
            		return attributes[rowIndex].getTankCapacity();
            	else
            		return "---";
            case 27:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
            	if (attributes[rowIndex].getTankUnit()!=null)
            		return messages.getText(attributes[rowIndex].getTankUnit().getResourceBundleId());
            	else
            		return "---";
            case 28:
            	if(corporation==CorporationData.MONTES_DE_PLATA){
            		if (isTrailer(rowIndex)){
                		return attributes[rowIndex].getReportMoreInfo().get("abs");
            		}
            	}
            	
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
                if(attributes[rowIndex].getAbs()) {
                    return messages.getText("generic.messages.yes");
                }else {
                    return messages.getText("generic.messages.no");
                }
            case 29:
            	if (isTrailer(rowIndex)){
            		
            		return messages.getText("generic.messages.notapply");
            	}
                if(attributes[rowIndex].getAirBag()) {
                    return messages.getText("generic.messages.yes");
                }else {
                    return messages.getText("generic.messages.no");
                }
            case 30:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
                if(attributes[rowIndex].getAireAcondicionado()) {
                    return messages.getText("generic.messages.yes");
                }else {
                    return messages.getText("generic.messages.no");
                }
            case 31:
                if(attributes[rowIndex].getCover() == null) {
                    return "---";
                }
                if(attributes[rowIndex].getCover().equals("generic.messages.nodata")) {
                    return messages.getText(attributes[rowIndex].getCover().getResourceBundleId());
                }
                return attributes[rowIndex].getCover().getResourceBundleId();
            case 32:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
                if(attributes[rowIndex].getOdometerUnit() != null) {
                    return messages.getText(attributes[rowIndex].getOdometerUnit().getResourceBundleId());
                }
                return "---";
            case 33:
                return (attributes[rowIndex].getDriver() != null) ? attributes[rowIndex].getDriver().getFirstName() : "----";                              
            case 34:
            	return new Integer(total);
            case 35:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
            	if (attributes[rowIndex].getObservations() != null){
            		return attributes[rowIndex].getObservations();
            	}else
            		return "---";
            case 36:
            	if (attributes[rowIndex].getSuctaExpiry()!=null)
                    return Util.formatDate(attributes[rowIndex].getSuctaExpiry(),messages);
                else 
                	return "---";
            case 37:
                if (attributes[rowIndex].getMtopExpiry()!=null)
                    return Util.formatDate(attributes[rowIndex].getMtopExpiry(),messages);
                else
                	return "---";
            case 38:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
                if(attributes[rowIndex].isOwnerCard()) {
                    return messages.getText("generic.vehiclecheck.form.correct");
                }else {
                    return messages.getText("generic.vehiclecheck.form.incorrect");
                }
            case 39:
            	if (attributes[rowIndex].getEnsurance() != null &&
            		attributes[rowIndex].getEnsurance().trim() != ""){
            		return attributes[rowIndex].getEnsurance();
            	}else{
            		return "---";
            	}
            case 40:
                if(attributes[rowIndex].getEnsuranceType() != null) {
                    return messages.getText(attributes[rowIndex].getEnsuranceType().getResourceBundleId());
                }
                return "---";
            case 41:
                if(attributes[rowIndex].getAxisCount() != null) {
                    return attributes[rowIndex].getAxisCount();
                }
                return "---";
            case 42:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
                if(attributes[rowIndex].getHp() != null) {
                    return attributes[rowIndex].getHp().toString();
                }
                return "---";
                
            // Campos Opcionales:
            case 43:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
            	if (attributes[rowIndex].getOptionalInfo() != null &&
            			attributes[rowIndex].getOptionalInfo().getAnttExpiry() != null){
            		return Util.formatDate(attributes[rowIndex].getOptionalInfo().getAnttExpiry(),messages);
            	}else 
            		return "----";
            case 44:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
            	if (attributes[rowIndex].getOptionalInfo() != null &&
            			attributes[rowIndex].getOptionalInfo().getCitvExpiry() != null){
            		return Util.formatDate(attributes[rowIndex].getOptionalInfo().getCitvExpiry(),messages);
            	}else 
            		return "---";
            case 45:
            	if (isTrailer(rowIndex))
            		return "true";
            	else
            		return "false";   
            case 46:
            	if (isTrailer(rowIndex) && corporation==CorporationData.MONTES_DE_PLATA){
           			return attributes[rowIndex].getReportMoreInfo().get("suspension");
            	}else
                if (attributes[rowIndex].getOptionalInfo() != null && attributes[rowIndex].getOptionalInfo().getPrefix() != null){
                    return (corporation == CorporationData.MONTES_DE_PLATA) ? messages.getText(attributes[rowIndex].getOptionalInfo().getPrefix()) : attributes[rowIndex].getOptionalInfo().getPrefix();
                }else 
                    return "---";
            case 47:
            	if(isTrailer(rowIndex) && corporation == CorporationData.MONTES_DE_PLATA){
            		return attributes[rowIndex].getReportMoreInfo().get("weightControl");
            	}else
                if (attributes[rowIndex].getOptionalInfo() != null && attributes[rowIndex].getOptionalInfo().getMonitoringSystem()!= null){
                    return (corporation == CorporationData.MONTES_DE_PLATA) ? messages.getText(attributes[rowIndex].getOptionalInfo().getMonitoringSystem()) : attributes[rowIndex].getOptionalInfo().getMonitoringSystem();
                }else 
                    return "---";
            case 48:
                if (attributes[rowIndex].getOptionalInfo() != null && attributes[rowIndex].getOptionalInfo().getTachograph()!= null){
                    return attributes[rowIndex].getOptionalInfo().getTachograph();
                }else 
                    return "---";
            case 49:
            	
                if (attributes[rowIndex].getOptionalInfo() != null && attributes[rowIndex].getOptionalInfo().getRenavan() != null){
                    return (corporation == CorporationData.MONTES_DE_PLATA) ? messages.getText(attributes[rowIndex].getOptionalInfo().getRenavan()) : attributes[rowIndex].getOptionalInfo().getRenavan();
                }else 
                    return "---";
            //FIN Campos Opcionales
            case 50:
                return attributes[rowIndex].getOdometer();
                
            case 51:
            	if (attributes[rowIndex].getDriver() != null && 
            		attributes[rowIndex].getDriver().getCorporationId() != null &&
            		!attributes[rowIndex].getDriver().getCorporationId().trim().equals("")){
            		
            		return attributes[rowIndex].getDriver().getCorporationId();
            	}else 
                    return "---";
            case 52:
                return attributes[rowIndex].getCostCenter();
            case 53:
            	// CO2 Emission - J&J 
            	if (attributes[rowIndex].getCo2emission() == null){
            		return "---";
            	}else{
            		return attributes[rowIndex].getCo2emission().toString();
            	}
            case 54:
                return (attributes[rowIndex].getDriver() != null) ? attributes[rowIndex].getDriver().getLastName() : "----";                              
            case 55:
            	// CO2 Emission - J&J 
            	if (attributes[rowIndex].getConsume() == null){
            		return "---";
            	}else{
            		return attributes[rowIndex].getConsume().toString();
            	}
            //EXCLUSIONDATE
            case 56:
            	if (attributes[rowIndex].getExclusionDate()!=null)
            		return messages.getText(Util.formatDate(attributes[rowIndex].getExclusionDate()));
            	else 
            		return "---";
            case 57:
            	if (attributes[rowIndex].getComment()!=null)
            		return attributes[rowIndex].getComment();
            	else 
            		return "";
            //DATOS MONSANTO
            case 58:
                return (attributes[rowIndex].getDriver() != null) ? attributes[rowIndex].getDriver().getPersonalAddress(): "----";
                
            case 59:
                return (attributes[rowIndex].getDriver() != null) ? attributes[rowIndex].getDriver().getCity() : "----";
                
            case 60:
                return (attributes[rowIndex].getDriver() != null) ? attributes[rowIndex].getDriver().getPhoneNumber() : "----";                              
            case 61:
                return (attributes[rowIndex].getCost() != null) ? String.valueOf(attributes[rowIndex].getCost()) : "----";                              
            case 62:
            	if(attributes[rowIndex].getSuspension()) {
                    return messages.getText("generic.messages.yes");
                }else {
                    return messages.getText("generic.messages.no");
                }
            case 63:
            	if(corporation==CorporationData.MONTES_DE_PLATA){
            		if (isTrailer(rowIndex)){
                		return attributes[rowIndex].getReportMoreInfo().get("type");
            		}
            		return messages.getText("generic.messages.notapply");
            	}else{
            		return messages.getText("generic.messages.notapply");
            	}
            case 64:
            	if(corporation==CorporationData.FORESTALORIENTAL){
					if(attributes[rowIndex].getOptionalInfo()!=null && attributes[rowIndex].getOptionalInfo().getRenavan()!=null){
						return attributes[rowIndex].getOptionalInfo().getRenavan();
					}
					return "Sin indicar";
            	}
            	
            	return messages.getText("generic.messages.notapply");
            	
            case 65:
            	if(corporation==CorporationData.FORESTALORIENTAL){
					if(attributes[rowIndex].getOptionalInfo()!=null && attributes[rowIndex].getOptionalInfo().getPrefix()!=null){
						return attributes[rowIndex].getOptionalInfo().getPrefix();
					}
					return "Sin indicar";
            	}
            	
            	return messages.getText("generic.messages.notapply");
            case 66:
            	if(corporation==CorporationData.FORESTALORIENTAL){
					if(attributes[rowIndex].getOptionalInfo()!=null && attributes[rowIndex].getOptionalInfo().getMonitoringSystem()!=null){
						return attributes[rowIndex].getOptionalInfo().getMonitoringSystem();
					}
					return "Sin indicar";
            	}
            	
            	return messages.getText("generic.messages.notapply");
            	
                
   
        }
                
        return null;
    }
    
    /**
     * 
     * @param rowIndex
     * @return
     */
    private boolean isTrailer(int rowIndex){
    	if (attributes[rowIndex].getType() == null)
    		// Trailer:
    		return true;
    	else
    		// Vehicle:
    		// Para los vehiculos siempre existe un Type.
    		return false;   
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "registrationNumber";
            case 1:
                return "trade";
            case 2:
                return "color";
            case 3:
                return "type";
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
                return "colour";
            case 18:
                return "permissionNumber";
            case 19:
                return "use";
            case 20:
                return "permissionExpiry";
            case 21:
                return "ownerShip";
            case 22:
                return "fuel";
            case 23:
                return "purchaseDate";
            case 24:
                return "chasisNumber";                
            case 25:
                return "engine";
            case 26:
                return "tankCapacity";
            case 27:
                return "tankUnit";
            case 28:
                return "abs";
            case 29:
                return "airBag";
            case 30:
                return "aireAcondicionado";
            case 31:
                return "cover";
            case 32:
                return "odometerUnit";
            case 33:
                return "driverFirstName";  
            case 34:
                return "total";
            case 35:
                return "observations";
            case 36:
                return "sucta";
            case 37:
                return "mtop";
            case 38:
                return "ownercard";
            case 39:
                return "ensurance";
            case 40:
                return "ensurance_comp";
            case 41:
                return "axis_count";
            case 42:
                return "hp";
            //CAMPOS OPCIONALES    
            case 43:
            	return "antt";
            case 44:
            	return "citv";
            case 45:
                return "isTrailer";
            case 46:
                return "prefix";
            case 47:
                return "monitoringsystem";
            case 48:
                return "tachograph";
            case 49:
                return "renavan";
            //FIN CAMPOS OPCIONALES
            case 50:
                return "odometer";
            case 51:
            	return "wwwid";
            case 52:
            	return "cost_center";
            case 53:
            	return "co2emission";
            case 54:
                return "driverLastName";  
            case 55:
            	return "consume";
            case 56:
            	return "exclusionDate";
            case 57:
            	return "comments";
            case 58:
            	return "personaladdress";
            case 59:
            	return "city";
            case 60:
            	return "phonenumber";
            case 61:
            	return "cost";
            case 62:
            	return "suspension";
            case 63:
            	return "semiZorra";
            case 64:
            	return "renavan";
            case 65:
            	return "prefix";
            case 66:
            	return "monitoringsystem";
            	
        }
        return null;
    }



    
    /**
	 * @return Returns the attributes.
	 */
	public VehicleData[] getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes The attributes to set.
	 */
	public void setAttributes(VehicleData[] attributes) {
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
	 * @return Returns the total.
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total The total to set.
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return Returns the messages.
	 */
	public Text getMessages() {
		return messages;
	}

	public void setMessages(Text messages) {
        this.messages = messages;
    }

	public void setCorporation(int corporation) {
		this.corporation = corporation;
	}
    
}
