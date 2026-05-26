package communication.driver.report;


import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.table.AbstractTableModel;

import communication.driver.tables.DriverData;
import communication.language.Text;

	public class DetailedDriverHRDQueryData extends AbstractTableModel 
	    implements Serializable {

	    private static final long serialVersionUID = 1L;
	    
	    private int total = 0;
	    private int cantReg = 0;
	    private DetailedDriverHRDDataType[] attributes;
	    private boolean color = true;
	    private Text messages;
        private int count = 0;	    
	    
	    private String lastId = "0";
	    private String thisId = "0";
	    
	    

	    private int[][] principal;
	    
	    public DetailedDriverHRDQueryData() {
	        total = 0;
	        attributes = new DetailedDriverHRDDataType[0];
	        principal = new int[3][2];
	        cantReg = 0;
	    }
	    
	    public DetailedDriverHRDQueryData(Collection reportDrivers,int total, int[][] principal) {
	    	Collections.sort((List)reportDrivers);
	        attributes = (DetailedDriverHRDDataType[])reportDrivers.toArray(new DetailedDriverHRDDataType[0]);
	        this.cantReg = attributes.length;
	        this.principal = principal;
	        this.total = total;
	    }

	    public int getRowCount() {
	        return cantReg;
	    }

	    public int getColumnCount() {
	        return 23;
	    }

	    public Object getValueAt(int rowIndex, int columnIndex) {
	    	String result;
	    		    	
	    	
	        switch(columnIndex) {
	            case 0:
	            	thisId = String.valueOf(attributes[rowIndex].getId());
	                return thisId;
	            case 1:	            	
	            	thisId = String.valueOf(attributes[rowIndex].getId());
	            	if (!lastId.equals(thisId)){	            		
	            		result = attributes[rowIndex].getFirstname();
	            	}else{
	            		result = "";
	            	}	            		
	                return (result != null)?result:"";
	            case 2:
	            	if (lastId.equals(thisId))
	            		result = "";
 	            	else
	            		result = attributes[rowIndex].getData(DriverData.DIV_ORG,principal[0][0],principal[0][1]);
	                return (result != null)?result:"";
	            case 3:
	            	if (lastId.equals(thisId))
	            	 	result = "";
	            	else
	            		result =  attributes[rowIndex].getData(DriverData.DIV_ORG,principal[1][0],principal[1][1]);
	                return (result != null)?result:"";
	            case 4:
	            	if (lastId.equals(thisId))
	            		result = "";
	            	else
	            		result = attributes[rowIndex].getData(DriverData.DIV_ORG,principal[2][0],principal[2][1]);
	                return (result != null)?result:"";
	            case 5:            	
	            	// Condiciones HRD
	            	if (lastId.equals(thisId))
	            		result = "";
	            	else
	            		result = attributes[rowIndex].getHrdConditions();
                    if (!lastId.equals(thisId)){
                        if(total == 1) {
                            count++;
                            if(count > cantReg) {
                                lastId = thisId;
                            }
                        }else {
                            lastId = thisId;
                        }
                    }
	                return (result != null)?result:"";	           
	            case 6:
	            	if (lastId.equals(thisId))
	            		result = "";
	            	else
	            		result = messages.getText(attributes[rowIndex].getCountry());
	            	return (result != null)?result:"";
	            case 7:   
	            	thisId = String.valueOf(attributes[rowIndex].getId());
		            if (lastId.equals(thisId)){
		            	;
		            }else{
                        if(total != 1) {
                            color = !color;
                        }
		            }
	                return new Boolean(color);
	            case 8:
	            	return new Integer(total);
	            case 9:
	            	result = transformDate(attributes[rowIndex].getHistory().getDate().substring(0,10));
	            	return (result != null) ? result : "";
	            case 10:
	            	result = messages.getText(attributes[rowIndex].getHistory().getType());
	            	return (result != null)?result:"";
	            case 11:
	            	result = messages.getText(attributes[rowIndex].getHistory().getClasification());
	            	return (result != null)?result:"";
	            case 12:
	            	result = messages.getText(attributes[rowIndex].getHistory().getResult());	            	            	
	            	return (result != null)?result:"";
                case 13:
                    String value = "";
                    // Condicional
                    if (!lastId.equals(thisId)){                        
                        value = (attributes[rowIndex].isConditional()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");            
                    }                       
                    thisId = String.valueOf(attributes[rowIndex].getId());
                    return value;
                case 14:
                    if (lastId.equals(thisId))
                        result = "";
                    else
                        result = attributes[rowIndex].getData(DriverData.DIV_ORG,1,1);
                    return (result != null)?result:"";
                case 15:
                	// Puntos del conductor / CIMSA
                	if (attributes[rowIndex].getDriverPoints() != null){
                		return attributes[rowIndex].getDriverPoints();
                	}else{
                		return "";
                	}
	            case 16:
	            	if (lastId.equals(thisId))
	            		result = "";
	            	else
	            		result = attributes[rowIndex].getData(DriverData.DIV_ORG,principal[3][0],principal[3][1]);
	                return (result != null)?result:"";
	            case 17:
	            	return attributes[rowIndex].getVehicleId();
	            case 18:
	            	if (lastId.equals(thisId))
	            		result = "";
	            	else
	            		return attributes[rowIndex].getVehiclePlate();
	            case 19:
	            	if (lastId.equals(thisId))
	            		result = "";
	            	else
	            		return attributes[rowIndex].getLastHRD();
	            case 20:
	            	if (lastId.equals(thisId))
	            		result = "";
	            	else
	            		return (attributes[rowIndex].getLastHRD() != null) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
	            case 21:
	            	if (lastId.equals(thisId))
	            		result = "";
	            	else
	            		return attributes[rowIndex].getLastTOJ();
	            case 22:	            	
	            	thisId = String.valueOf(attributes[rowIndex].getId());
	            	if (!lastId.equals(thisId)){	            		
	            		result = attributes[rowIndex].getLastname();
	            	}else{
	            		result = "";
	            	}	            		
	                return (result != null)?result:"";
	        }
	        return "";
	    }

	    private String transformDate(String date) {
            String result = "";
            StringTokenizer tokenizer = new StringTokenizer(date,"-");
            if(tokenizer.hasMoreTokens()) {
                result = tokenizer.nextToken();
                while(tokenizer.hasMoreTokens()) {
                    result = tokenizer.nextToken() + "/" + result;
                }
            }
            return result;
        }

        public String getColumnName(int column) {
	        switch(column) {
	            case 0:
	                return "id";
	            case 1:
	                return "firstname";
	            case 2:
	                return "divorg1";
	            case 3:
	                return "divorg2";
	            case 4:
	                return "divorg3";
	            case 5:
	            	return "hrdConditions";
	            case 6:
	            	return "country";
	            case 7:
	            	return "color";
	            case 8:
	            	return "total";
	            case 9:
	            	return "date";
	            case 10:
	            	return "type";
	            case 11:
	            	return "clasification";
	            case 12:
	            	return "result";
                case 13:
                    return "conditional";
                case 14:
                    return "divorg11";
                case 15:
                	return "points";
	            case 16:
	                return "divorg4";
	            case 17:
	                return "vehicleId";
	            case 18:
	                return "vehiclePlate";
	            case 19:
	                return "lastHRD";
	            case 20:
	                return "hasHRD";
	            case 21:
	                return "lastTOJ";
	            case 22:
	                return "lastname";
	        }
	        return "";
	    }
	    
	    public void setMessages(Text messages) {
	        this.messages = messages;
	    }
	    
	    public Text getMessages(){
	    	return this.messages;
	    }

		/** private int historyCount = 0;
		 * @return Returns the total.
		 */
		public int getTotal() {
			return total;
		}
		
	    
	    
	}


