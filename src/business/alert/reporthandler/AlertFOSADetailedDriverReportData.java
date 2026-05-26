package business.alert.reporthandler;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import communication.administration.corporation.util.Util;
import communication.driver.report.DetailedFOSADriverReportDataType;
import communication.driver.report.FOSADetailedDriverReportData;
import communication.language.Text;
import communication.util.ListResult;

public class AlertFOSADetailedDriverReportData extends FOSADetailedDriverReportData implements Serializable {

	    private static final long serialVersionUID = 1L;
	    
	    private int total;
	    private DetailedFOSADriverReportDataType[] attributes;
	    private boolean[] expired;
	    private boolean color = true;
	    private Text messages;
	    
	    public AlertFOSADetailedDriverReportData() {
	        total = 0;
	        attributes = new DetailedFOSADriverReportDataType[0];	        
	    }
	    
	    public AlertFOSADetailedDriverReportData(ListResult reportDrivers) {
	        total = reportDrivers.getTotal();
	        Collection elements = reportDrivers.getElements();
	        Collections.sort((List) elements);
	        attributes = (DetailedFOSADriverReportDataType[])elements.toArray(new DetailedFOSADriverReportDataType[0]);
	        expired = new boolean[attributes.length];
	        hasExpired();
	    }
	    
	    /*
	     * Marco con true los items que tienen:
	     * 1. tienen el gabinete vencido 
	     * 2. aquellos conductores que no tienen hecho ningun BTW 
	     * 3. tienen el BTW vencido (duran 2 años).
	     */
	    private void hasExpired(){
	    	int i = 0;
	    	
	    	Calendar today = Calendar.getInstance();
	    	today.add(Calendar.MONTH, 1);
	    	today.set(Calendar.DATE, 31);
	    	for (i = 0; i <= attributes.length-1; i++){
	    		
	    		// 1. Gabinete Vencido?
	    		Calendar expiry = Calendar.getInstance();
	    		if (attributes[i].getExpiry()==null){
	    			
	    			expired[i] = false;
	    			System.out.println("Expired false1");
	    			
	    		}else if(attributes[i].getExpiry()!=null){
	    			
	    			expiry.setTime(attributes[i].getExpiry());	
	    		
	    			if (today.compareTo(expiry) >= 1){
	    				expired[i] = true;
	    				System.out.println("Expired true");
	    			}else{
	    			
	    				// 2. No tiene BTW?		    		
	    				if (attributes[i].getCount() == null || attributes[i].getCount() == 0 
	    						|| attributes[i].getLast() == null){
		    			
	    					expired[i] = true;
	    					System.out.println("Expired true");
		    			
	    				}else{
		    			// 3. BTW vencido ?
		    			
	    					expiry.setTime(attributes[i].getLast());
	    					expiry.add(Calendar.YEAR, 2); // vence en 2 años	    			
	    					if (today.compareTo(expiry) >= 1){
	    						expired[i] = true;
	    						System.out.println("Expired true");
	    					}else{
	    						expired[i] = false;
	    						System.out.println("Expired FALSE");
	    					}
	    				}
	    			}
	    		}
	    		
	    		} // end for
	    	}
	    

	    public int getRowCount() {
	        return attributes.length;
	    }

	    public int getColumnCount() {
	        return 25;
	    }

	    public Object getValueAt(int rowIndex, int columnIndex) {
	        switch(columnIndex) {
	            case 0:
	            	if (attributes[rowIndex].getId() == null){
	            		return "";
	            	}
	                return String.valueOf(attributes[rowIndex].getId());
	            case 1:
	            	if (attributes[rowIndex].getFirstname() == null){
	            		return "";
	            	}
	                return attributes[rowIndex].getFirstname();  
	            case 2:
	            	if (attributes[rowIndex].getLastname() == null){
	            		return "";
	            	}
	                return attributes[rowIndex].getLastname();  
	            case 3:
	                color = !color;
	                return new Boolean(color);
	            case 4:
	            	if (attributes[rowIndex].getBirthdate() == null){
	            		return "";
	            	}
	                return Util.formatDate(attributes[rowIndex].getBirthdate(),messages);	            
	            case 7:
	                if (attributes[rowIndex].getDivorg11() == null){
	                	return "";
	                }
	            	return attributes[rowIndex].getDivorg11();
	            case 8:
	            	if (attributes[rowIndex].getDivorg12() == null){
	                	return "";
	                }
	                return attributes[rowIndex].getDivorg12();
	            case 9:
	            	if (attributes[rowIndex].getDivorg13() == null){
	                	return "";
	                }
	                return attributes[rowIndex].getDivorg13();
	            case 10:
	            	if (attributes[rowIndex].getDivorg21() == null){
	                	return "";
	                }
	                return attributes[rowIndex].getDivorg21();
	            case 11:
	            	if (attributes[rowIndex].getDivorg22() == null){
	                	return "";
	                }
	                return attributes[rowIndex].getDivorg22();
	            case 12:
	            	if (attributes[rowIndex].getDivorg23() == null){
	                	return "";
	                }
	                return attributes[rowIndex].getDivorg23();
	            case 13:
	            	if (attributes[rowIndex].getDivorg31() == null){
	                	return "";
	                }
	                return attributes[rowIndex].getDivorg31();
	            case 14:
	            	if (attributes[rowIndex].getDivorg32() == null){
	                	return "";
	                }
	                return attributes[rowIndex].getDivorg32();
	            case 15:
	            	if (attributes[rowIndex].getDivorg33() == null){
	                	return "";
	                }
	                return attributes[rowIndex].getDivorg33();	            
	            case 16:
	            	if (attributes[rowIndex].getLicenseExpiry() == null){
	            		return "";
	            	}
	                return Util.formatDate(attributes[rowIndex].getLicenseExpiry(),messages);
	            case 17:
	                if (attributes[rowIndex].getTestDate() == null){
	                	return "";
	                }
	            	return Util.formatDate(attributes[rowIndex].getTestDate(),messages);
	            case 18:
	                if(attributes[rowIndex].isApt()) {
	                    return messages.getText("generic.messages.yes");
	                }else {
	                    return messages.getText("generic.messages.no");
	                }
	            case 19:
	                if (attributes[rowIndex].getExpiry() == null){
	                	return "";
	                }
	            	return Util.formatDate(attributes[rowIndex].getExpiry(),messages);	           
	            case 20:
	                if (attributes[rowIndex].getCount() == null){
	                	return "";
	                }
	            	return attributes[rowIndex].getCount();
	            case 21:
	                if (attributes[rowIndex].getLast() == null){
	                	return "";
	                }
	            	return Util.formatDate(attributes[rowIndex].getLastExpiry(),messages);
	            case 22:
	            	if (attributes[rowIndex].getLastEC() == null){
	            		return "";
	            	}
	                return Util.formatDate(attributes[rowIndex].getLastEC(),messages);
	            case 23:
	            	String corpId= attributes[rowIndex].getCorporationId();
	            	
	            	if(corpId!=null && !corpId.equals("") && !corpId.equals("null")){
	            		
	            		return corpId;
	            	}
	            	return "";
	            case 24:
	                return new Boolean(expired[rowIndex]);
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
	                return "licenseExpiry";
	            case 17:
	                return "test";
	            case 18:
	                return "apt";
	            case 19:
	                return "expiry";
	            case 20:
	                return "count";
	            case 21:
	                return "last";
	            case 22:
	                return "lastec";
	            case 23:
	            	return "corporationid";
	            case 24:
	            	return "expired";
	        }
	        return null;
	    }
	    
	    public void setMessages(Text messages) {
	        this.messages = messages;
	    }

	    public int getTotal() {
	        return total;
	    }

		/**
		 * @return the expired
		 */
		public boolean[] getExpired() {
			return expired;
		}

		/**
		 * @param expired the expired to set
		 */
		public void setExpired(boolean[] expired) {
			this.expired = expired;
		}
	    
	    
	}
