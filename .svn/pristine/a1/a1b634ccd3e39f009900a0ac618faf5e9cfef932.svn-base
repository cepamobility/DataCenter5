package communication.vehicle.report;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import communication.administration.corporation.tables.CorporationData;
import communication.language.Text;

public class ReportCheckListDetailedData extends AbstractTableModel implements Serializable  {


	private static final long serialVersionUID = 1L;
	private VehicleCheckReportDetailedDataType[] attributes;
	private boolean color = true;
	private Text messages;
	private int totalItems;
	private int totalChecks; // Cantidad de checklist total
	private LinkedList items;
	private Hashtable itemsText;
	private int corporationId;


	public ReportCheckListDetailedData() {
		attributes = new VehicleCheckReportDetailedDataType[0];
		this.items = new LinkedList();
		totalItems = 0;
	}

	public ReportCheckListDetailedData(VehicleCheckReportDetailedDataType[] data, 
			Text messages, LinkedList items, Hashtable itemsText, int totalChecks, int corporationId) {
		attributes = data;
		this.totalChecks = totalChecks;
		this.messages = messages;
		this.items = items;
		this.totalItems = items.size();		
		this.itemsText = itemsText;
		this.corporationId=corporationId;
	}

	public int getRowCount() {
		return attributes.length;
	}

	public int getColumnCount() {
		return (corporationId==CorporationData.DPA?2:0)+11 + (2*this.totalItems);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		if(corporationId==CorporationData.DPA){
			return getValueAtDPA(rowIndex, columnIndex); 
		}
		
	    switch(columnIndex) {
	        case 0:
	            return  attributes[rowIndex].getContractName();
	        case 1:
	            return  attributes[rowIndex].getPlate();
	        case 2:
	            return  attributes[rowIndex].getType();
	        case 3:
	            return  attributes[rowIndex].getStringDate();
	        case 4:
	            color = !color;
	            return new Boolean(color);
	        case 5:
	            return  messages.getText("generic.messages.total") + " " + attributes[rowIndex].getContractName();
	        case 6:
	            return  messages.getText("generic.messages.total") + " " + attributes[rowIndex].getType();
	        case 7:
	        	// Total de items a mostrar:
	        	return new Integer(this.totalItems).toString();
	        case 8:
	        	// Total checks
	        	return new Integer(this.totalChecks);
	        case 9:
	            return  attributes[rowIndex].getObservations();
	        case 10:
	            return  String.valueOf(attributes[rowIndex].getVehicleId());
	        default:
	        	 if (columnIndex >= this.totalItems+11){
	        		// Muestro el valor 
	 	        	if (attributes[rowIndex].getItems().containsKey(
	 	        			items.get(columnIndex-11-this.totalItems))){
	 	        		// Como el valor de los items en la base es:
	 	        		// 1 correecto y 2 incorrecto, para el reporte
	 	        		// colocamos los valores 0 correcto, 1 incorrecto.
	 	        		return new Integer(((Integer)attributes[rowIndex].getItems().
	 	        				get(items.get(columnIndex-11-this.totalItems))).intValue() - 1);
	 	        	}else{
	 	        		return new Integer(0);
	 	        	}
		         }else{
		        	if (attributes[rowIndex].getItems().containsKey(items.get(columnIndex-11)) &&
		        			((Integer)attributes[rowIndex].getItems().get(items.get(columnIndex-11))).intValue() == 2){		 	        		
		 	        		return new String("X");
	 	        	}else{
		 	        		return new String(" ");
	 	        	}
		         }
	    }
	    
	}
	
	public Object getValueAtDPA(int rowIndex, int columnIndex) {
	    switch(columnIndex) {
	        case 0:
	            return  attributes[rowIndex].getContractName();
	        case 1:
	            return  attributes[rowIndex].getPlate();
	        case 2:
	            return  attributes[rowIndex].getType();
	        case 3:
	            return  attributes[rowIndex].getStringDate();
	        case 4:
	            color = !color;
	            return new Boolean(color);
	        case 5:
	            return  messages.getText("generic.messages.total") + " " + attributes[rowIndex].getContractName();
	        case 6:
	            return  messages.getText("generic.messages.total") + " " + attributes[rowIndex].getType();
	        case 7:
	        	// Total de items a mostrar:
	        	return new Integer(this.totalItems).toString();
	        case 8:
	        	// Total checks
	        	return new Integer(this.totalChecks);
	        case 9:
	            return  attributes[rowIndex].getObservations();
	        case 10:
	            return  String.valueOf(attributes[rowIndex].getVehicleId());
	        case 11:
	            return  attributes[rowIndex].getInspectedBy();
	        case 12:
	            return  (attributes[rowIndex].getResult()!=null && attributes[rowIndex].getResult().equals("1")?messages.getText("generic.messages.approved").toUpperCase():(attributes[rowIndex].getResult()!=null && attributes[rowIndex].getResult().equals("2")?messages.getText("generic.messages.notapproved").toUpperCase():""));    
	        default:
	        	 if (columnIndex >= this.totalItems+13){
	        		// Muestro el valor 
	 	        	if (attributes[rowIndex].getItems().containsKey(items.get(columnIndex-13-this.totalItems))){
	 	        		// Como el valor de los items en la base es:
	 	        		// 1 correecto y 2 incorrecto, para el reporte
	 	        		// colocamos los valores 0 correcto, 1 incorrecto.
	 	        		return new Integer(((Integer)attributes[rowIndex].getItems().get(items.get(columnIndex-13-this.totalItems))).intValue() - 1);
	 	        	}else{
	 	        		return new Integer(0);
	 	        	}
		         }else{
		        	if (attributes[rowIndex].getItems().containsKey(items.get(columnIndex-13)) &&
		        			((Integer)attributes[rowIndex].getItems().get(items.get(columnIndex-13))).intValue() == 2){		 	        		
		 	        		return new String("X");
	 	        	}else{
		 	        		return new String(" ");
	 	        	}
		         }
	    }
	    
	}
	
	public String getColumnName(int column) {
		if(corporationId==CorporationData.DPA){
			return getColumnNameDPA(column); 
		}
		
	    switch(column) {
	        case 0:
	            return "name";
	        case 1:
	            return "plate";
	        case 2:
	            return "type";
	        case 3:
	            return "date";
	        case 4:
	            return "color";
	        case 5:
	            return "totalname";
	        case 6:
	            return "totaltype";
	        case 7:
	        	return "totalitems";
	        case 8:
	        	return "totalchecks";
	        case 9:
	        	return "observations";
	        case 10:
	        	return "vehicleId";
	        default:
	            if (column >= this.totalItems+11){
	            	return "checknum"+String.valueOf(column-11-this.totalItems);
	            }else{
	            	return "check"+String.valueOf(column-11);
	            }
	        
	    }
	}
	
	public String getColumnNameDPA(int column) {
	    switch(column) {
	        case 0:
	            return "name";
	        case 1:
	            return "plate";
	        case 2:
	            return "type";
	        case 3:
	            return "date";
	        case 4:
	            return "color";
	        case 5:
	            return "totalname";
	        case 6:
	            return "totaltype";
	        case 7:
	        	return "totalitems";
	        case 8:
	        	return "totalchecks";
	        case 9:
	        	return "observations";
	        case 10:
	        	return "vehicleId";
	        case 11:
	        	return "inspectedby";
	        case 12:
	        	return "result";
	        default:
	            if (column >= this.totalItems+13){
	            	return "checknum"+String.valueOf(column-13-this.totalItems);
	            }else{
	            	return "check"+String.valueOf(column-13);
	            }
	        
	    }
	}
	
	public int getTotalItems() {
	    return totalItems;
	}
	
	public LinkedList getItems(){
		return items;
	}
	
	public Hashtable getItemsText(){
		return this.itemsText;
	}

	/**
	 * @return Returns the totalChecks.
	 */
	public int getTotalChecks() {
		return totalChecks;
	}

	/**
	 * @param totalChecks The totalChecks to set.
	 */
	public void setTotalChecks(int totalChecks) {
		this.totalChecks = totalChecks;
	}
	
	
}
