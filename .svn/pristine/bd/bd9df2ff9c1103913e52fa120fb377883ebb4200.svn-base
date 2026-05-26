/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.vehicle.report;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.util.Util;
import communication.language.Text;
import communication.vehicle.tables.VehicleDataText;

public class VehicleReportCombinedExtendedData extends AbstractTableModel 
    implements Serializable {

        static final long serialVersionUID = 1L;
	
    private int total;
    private VehicleDataText[] attributes;
    private boolean color = true;
    private Text messages;

	private int corporation;
    
    
	public VehicleReportCombinedExtendedData() {
        total = 0;
        attributes = new VehicleDataText[0]; 
        corporation = 0;
    }
	
    public int getRowCount() {
        return attributes.length;
    }    
    
    public VehicleReportCombinedExtendedData(Collection reportDrivers, int total) {
        this.total = total; //reportDrivers.size();
        attributes = (VehicleDataText[])reportDrivers.toArray(new VehicleDataText[0]);	
        corporation = 0;
    }

    public int getColumnCount() {
        return 54;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
    	Format dateFormat;	
    	dateFormat = new SimpleDateFormat("d-MMM-yyyy");	
        switch(columnIndex) {
            case 0:
                return attributes[rowIndex].getPlate();
            case 1:
            	if (attributes[rowIndex].getTrade() != null){
                    return (attributes[rowIndex].getTrade().equals("generic.messages.nodata")) ? messages.getText(attributes[rowIndex].getTrade()) : attributes[rowIndex].getTrade();
            	}else
            		return "----";
            case 2:
                color = !color;
                return new Boolean(color);
            case 3:
            	if (attributes[rowIndex].getType() == null){
            		// Trailer:
            		return messages.getText("vehicle.data.trailer");
            	}else{
            		//Vehicles:
            		return messages.getText(attributes[rowIndex].getType());
            	}            		
            case 4:
            	if (attributes[rowIndex].getModel() != null){
            		return attributes[rowIndex].getModel();
            	}else 
            		return "----";
                
            case 5:
            	if (attributes[rowIndex].getYear() != null &&
            			!attributes[rowIndex].getYear().equals("1000"))
            		return attributes[rowIndex].getYear();
            	else
            		return "----";
            case 6:
            	if (attributes[rowIndex].getCountry() != null){
            		return messages.getText(attributes[rowIndex].getCountry());
            	}else
            		return "----";            	
            case 7:
                return attributes[rowIndex].getDivOrg1();
            case 8:
                return attributes[rowIndex].getDivOrg2();
            case 9:
                return attributes[rowIndex].getDivOrg3();
            case 10:
                return attributes[rowIndex].getDivOrg3();
            case 11:
                return attributes[rowIndex].getDivOrg3();
            case 12:
                return attributes[rowIndex].getDivOrg3();
            case 13:
                return attributes[rowIndex].getDivOrg3();
            case 14:
                return attributes[rowIndex].getDivOrg3();
            case 15:
                return attributes[rowIndex].getDivOrg3();
            case 16:
                return attributes[rowIndex].getId().toString();
            case 17:
            	if (attributes[rowIndex].getColour() != null && 
            		attributes[rowIndex].getColour().trim() != "") 
            		return attributes[rowIndex].getColour();
            	else 
            		return "----";
            case 18:
            	if (attributes[rowIndex].getPermissionNumber()!= null &&
            		attributes[rowIndex].getPermissionNumber().trim() != "")            	
            		return attributes[rowIndex].getPermissionNumber();
            	else return "----";            	
            case 19:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
                return messages.getText(attributes[rowIndex].getUse());
            case 20:
            	if (attributes[rowIndex].getPermissionExpiry()!=null)            	
            		return dateFormat.format(attributes[rowIndex].getPermissionExpiry());
            	else 
            		return "----";
            case 21:
            	/*
            	if (attributes[rowIndex].getOwnerShip()!=null)
            		return messages.getText(attributes[rowIndex].getOwnerShip());
            	else 
            	*/
            		return "----";
            case 22:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
            	if (attributes[rowIndex].getFuel()!=null)
            		return messages.getText(attributes[rowIndex].getFuel());
            	else
            		return "----";
            case 23:
            	if (attributes[rowIndex].getPurchaseDate()!=null)
            		return dateFormat.format(attributes[rowIndex].getPurchaseDate());
            	else
            		return "----";
            case 24:
            	if (attributes[rowIndex].getChasisNumber() != null &&
            			attributes[rowIndex].getChasisNumber().trim() != ""	)
            		return attributes[rowIndex].getChasisNumber();
            	else
            		return "----";
            case 25:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
            	if (attributes[rowIndex].getEngine()!=null)
            		return messages.getText(attributes[rowIndex].getEngine());
            	else
            		return "----";
            case 26:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
            	if (attributes[rowIndex].getTankCapacity()!=null)
            		return attributes[rowIndex].getTankCapacity();
            	else
            		return "----";
            case 27:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
            	if (attributes[rowIndex].getTankUnit()!=null)
            		return messages.getText(attributes[rowIndex].getTankUnit());
            	else
            		return "----";
            case 28:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
                if(attributes[rowIndex].getAbs().equalsIgnoreCase("true")) {
                    return messages.getText("generic.messages.yes");
                }else {
                    return messages.getText("generic.messages.no");
                }
            case 29:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
                if(attributes[rowIndex].getAirBag().equalsIgnoreCase("true")) {
                    return messages.getText("generic.messages.yes");
                }else {
                    return messages.getText("generic.messages.no");
                }
            case 30:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
                if(attributes[rowIndex].getAireAcondicionado().equalsIgnoreCase("true")) {
                    return messages.getText("generic.messages.yes");
                }else {
                    return messages.getText("generic.messages.no");
                }
            case 31:
                if(attributes[rowIndex].getCover() == null) {
                    return "----";
                }
                if(attributes[rowIndex].getCover().equals("generic.messages.nodata")) {
                    return messages.getText(attributes[rowIndex].getCover());
                }
                return attributes[rowIndex].getCover();
            case 32:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
                if(attributes[rowIndex].getOdometerUnit() != null) {
                    return messages.getText(attributes[rowIndex].getOdometerUnit());
                }
                return "----";
            case 33:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
                return (attributes[rowIndex].getDriverFirstName() != null )
                		?attributes[rowIndex].getDriverFirstName():"----";                              
            case 34:
            	return new Integer(total);
            case 35:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
            	if (attributes[rowIndex].getObservations() != null &&
            			attributes[rowIndex].getObservations().trim() != ""){
            		return attributes[rowIndex].getObservations();
            	}else
            		return "----";
            case 36:
            	if (attributes[rowIndex].getSuctaExpiry()!=null)
                    return attributes[rowIndex].getSuctaExpiry();
                else 
                	return "----";
            case 37:
                if (attributes[rowIndex].getMtopExpiry()!=null)
                    return attributes[rowIndex].getMtopExpiry();
                else
                	return "----";
            case 38:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
                if(attributes[rowIndex].getOwnerCard().equalsIgnoreCase("true")) {
                    return messages.getText("generic.vehiclecheck.form.correct");
                }else {
                    return messages.getText("generic.vehiclecheck.form.incorrect");
                }
            case 39:
            	if (attributes[rowIndex].getEnsurance() != null &&
            		attributes[rowIndex].getEnsurance().trim() != ""){
            		return attributes[rowIndex].getEnsurance();
            	}else{
            		return "----";
            	}
            case 40:
                if(attributes[rowIndex].getEnsuranceType() != null) {
                    return messages.getText(attributes[rowIndex].getEnsuranceType());
                }
                return "----";
            case 41:
                if(attributes[rowIndex].getAxis() != null) {
                    return attributes[rowIndex].getAxis();
                }
                return "----";
            case 42:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
                if(attributes[rowIndex].getHp() != null &&
                		attributes[rowIndex].getHp().trim() != "") {
                    return attributes[rowIndex].getHp();
                }
                return "----";
                
            // Campos Opcionales:
            case 43:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
            	/*
            	if (attributes[rowIndex].getOptionalInfo() != null &&
            			attributes[rowIndex].getOptionalInfo().getAnttExpiry() != null){
            		return dateFormat.format(attributes[rowIndex].getOptionalInfo().getAnttExpiry());
            	}else 
            	*/
            		return "----";
            case 44:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
            	/*
            	if (attributes[rowIndex].getOptionalInfo() != null &&
            			attributes[rowIndex].getOptionalInfo().getCitvExpiry() != null){
            		return dateFormat.format(attributes[rowIndex].getOptionalInfo().getCitvExpiry());
            	}else 
            	*/
            		return "----";
            case 45:
            	if (isTrailer(rowIndex))
            		return "true";
            	else
            		return "false";   
            case 46:
                if (attributes[rowIndex].getPrefix() != null){
                    return (corporation == CorporationData.MONTES_DE_PLATA) ? messages.getText(attributes[rowIndex].getPrefix()) : attributes[rowIndex].getPrefix();
                }else 
                    return "----";
            case 47:
            	return attributes[rowIndex].getOdometer();
            case 48:
            	return attributes[rowIndex].getCenterCost();
            case 49:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
                return (attributes[rowIndex].getDriverLastName() != null )
                		?attributes[rowIndex].getDriverLastName():"----"; 
            case 50:
                if (attributes[rowIndex].getMonitoringSystem() != null){
                    return (corporation == CorporationData.MONTES_DE_PLATA) ? messages.getText(attributes[rowIndex].getMonitoringSystem()) : attributes[rowIndex].getMonitoringSystem();
                }else 
                    return "----";
            case 51:
                if (attributes[rowIndex].getRenavan() != null){
                    return (corporation == CorporationData.MONTES_DE_PLATA) ? messages.getText(attributes[rowIndex].getRenavan()) : attributes[rowIndex].getRenavan();
                }else 
                    return "----";
            case 52:
            	if (isTrailer(rowIndex)){
            		return messages.getText("generic.messages.notapply");
            	}
                if(attributes[rowIndex].getSuspension().equalsIgnoreCase("true")) {
                    return messages.getText("generic.messages.yes");
                }else {
                    return messages.getText("generic.messages.no");
                }
            case 53:
            	if (attributes[rowIndex].getExclusionDate()!=null)
            		return messages.getText(Util.formatDate(attributes[rowIndex].getExclusionDate()));
            	else 
            		return "---";

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
            case 43:
            	return "antt";
            case 44:
            	return "citv";
            case 45:
                return "isTrailer";
            case 46:
                return "prefix";
            case 47:
                return "odometer";
            case 48:
                return "cost_center";
            case 49:
                return "driverLastName";  
            case 50:
                return "monitoringsystem";
            case 51:
                return "renavan";
            case 52:
                return "suspension";
            case 53:
                return "exclusionDate";
            
        }
        return null;
    }



    
    /**
	 * @return Returns the attributes.
	 */
	public VehicleDataText[] getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes The attributes to set.
	 */
	public void setAttributes(VehicleDataText[] attributes) {
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
