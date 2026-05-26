/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.event.report;


import java.io.Serializable;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import communication.administration.corporation.tables.CorporationData;
import communication.event.tables.DetailedReportEventAttributes;
import communication.event.tables.EventDataType;
import communication.event.tables.SimpleReportEventAttributes;
import communication.language.Text;
import communication.util.ListResult;
import communication.util.ReportDataUtil;

public class DetailedEventReportData extends AbstractTableModel 
    implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total;
    private DetailedReportEventAttributes[] attributes;
    private boolean color = true;
    private Text messages;
    private Integer corporationId;

	private boolean onlyFine;
    
    public DetailedEventReportData() {
        total = 0;
        attributes = new DetailedReportEventAttributes[0];
        corporationId=0;
    }
    
    public DetailedEventReportData(ListResult reportEvents, boolean onlyFine, Integer corporationId) {
        total = reportEvents.getTotal();
        Collection elements = reportEvents.getElements();
        attributes = (DetailedReportEventAttributes[])elements.toArray(new DetailedReportEventAttributes[0]);
        this.onlyFine = onlyFine;
        this.corporationId=corporationId;
    }

    public int getRowCount() {
        return attributes.length;
    }
    
    public int getTotal() {
        return total;
    }

    public int getColumnCount() {
        return 107;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return String.valueOf(attributes[rowIndex].getId());
            case 1:
            	return attributes[rowIndex].getDriverName();
            case 2:
                color = !color;
                return new Boolean(color);
            case 3:
                return attributes[rowIndex].getEventdate();
            case 4:
                switch(attributes[rowIndex].getType()) {
                	case EventDataType.NEARMISS:
                		return messages.getText("generic.event.nearmiss");
                    case EventDataType.INCIDENT:
                        return messages.getText("generic.event.incident");
                    case EventDataType.FINE:
                    	String fine = messages.getText(attributes[rowIndex].getFineType());
                    	if(!onlyFine) {
                    		fine = messages.getText("generic.event.fine")+": "+ fine;
                    	}
                        return fine;
                    default:
                        if(attributes[rowIndex].getRecordable()) {
                            return messages.getText("generic.event.incident");
                        }
                        return messages.getText("generic.event.accident");
                }
            case 5:
                return messages.getText(attributes[rowIndex].getCountry());
            case 6:
                return attributes[rowIndex].getDivOrg1();
            case 7:
                return attributes[rowIndex].getDivOrg2();
            case 8:
                return attributes[rowIndex].getDivOrg3();
            case 9:
                return attributes[rowIndex].getDivOrg4();
            case 10:            	
            	return attributes[rowIndex].getDivOrg5();
            case 11:
                return String.valueOf(attributes[rowIndex].getDriverId());
            case 12:
            	switch(attributes[rowIndex].getType()) {
                case SimpleReportEventAttributes.INCIDENT:
                	return ReportDataUtil.toHTMLString(attributes[rowIndex].getStory());
                case SimpleReportEventAttributes.FINE:
                	return ReportDataUtil.toHTMLString(attributes[rowIndex].getObservations());
                default:
                    return ReportDataUtil.toHTMLString(attributes[rowIndex].getStory());
            }
            case 13:
            	return getClasificationResult(rowIndex,columnIndex);
            case 14:{
            	if (attributes[rowIndex].getHour()!=null)
            		return attributes[rowIndex].getHour().toString().substring(0,5);
            	else
            		return "-";
            }
            case 15:{
            	if(corporationId==CorporationData.BAYER){
            		if (attributes[rowIndex].getSapNumber()!=null && attributes[rowIndex].getSapNumber()==1)
            			return messages.getText("datatype.bayer.homework"); 
	            	else if ( attributes[rowIndex].getSapNumber()!=null && attributes[rowIndex].getSapNumber()==2){
	            		return messages.getText("datatype.bayer.workhome");
	            	}else{
	            		return messages.getText("generic.messages.nodata");
	            	}
            	}
            	else{
            		if (attributes[rowIndex].isDamage()==true)
            			return messages.getText("generic.messages.yes");
	            	else
	            		return messages.getText("generic.messages.no");
	            }
            }
            case 16:
            	return messages.getText(attributes[rowIndex].getZone());
            case 17:{
            	if (attributes[rowIndex].getWorking()==null)
            		return "-";
            	else if (attributes[rowIndex].getWorking().equals("t"))
            		return messages.getText("generic.messages.yes");
            	else if(attributes[rowIndex].getWorking().equals("f"))
            		return messages.getText("generic.messages.no");
            	else
            		return messages.getText(attributes[rowIndex].getWorking());
            	
            		
            }
            case 18:
            	return (attributes[rowIndex].getPoliceReport()!=null) ? messages.getText(attributes[rowIndex].getPoliceReport()):"-";
            case 19:
            	return messages.getText(attributes[rowIndex].getCollisionclasification());
            case 20:
            	return messages.getText(attributes[rowIndex].getHandle());
            case 21:
            	return messages.getText(attributes[rowIndex].getHitrun());
            case 22:
            	return messages.getText(attributes[rowIndex].getPrincipalFactor());
            case 23:
            	return (attributes[rowIndex].getCollisionAngle()!=null) ? messages.getText(attributes[rowIndex].getCollisionAngle()):"-";
            case 24:
            	return (attributes[rowIndex].getMaxSpeed()!=null) ? attributes[rowIndex].getMaxSpeed().toString():"-";
            case 25:
            	return (attributes[rowIndex].getCollisionSpeed()!=null) ? attributes[rowIndex].getCollisionSpeed().toString():"-";
            case 26:
            	return (attributes[rowIndex].getVehicleSpeed()!=null) ? attributes[rowIndex].getVehicleSpeed().toString():"-";
            case 27:
            	return (attributes[rowIndex].getReportedSpeed()!=null) ? attributes[rowIndex].getReportedSpeed().toString():"-";
            case 28:{
            	String speed=attributes[rowIndex].getSpeedUnit();
            	if(speed!=null) {
            		if(speed.equals("1"))
            			return messages.getText("driver.speedunits.metres");
            		else if(speed.equals("2"))	
            			return messages.getText("driver.speedunits.milles");
            		else
            			return "-";
            	}else
            		return "-";
            }
            case 29:
            	return (attributes[rowIndex].getSelfbelt()!=null) ? messages.getText(attributes[rowIndex].getSelfbelt()) : "-";
            case 30:
            	return attributes[rowIndex].getAlcohol();
            case 31:
            	return (attributes[rowIndex].getOtherCompanyVehicles()!=null) ? messages.getText(attributes[rowIndex].getOtherCompanyVehicles()):"-";
            case 32:
            	return (attributes[rowIndex].getCellular()!=null) ?  messages.getText(attributes[rowIndex].getCellular()):"-";
            case 33:
            	return messages.getText(attributes[rowIndex].getWay());
            case 34:
            	return messages.getText(attributes[rowIndex].getDirection());
            case 35:
            	return messages.getText(attributes[rowIndex].getControl());
            case 36:
            	return  (attributes[rowIndex].getState()!=null) ? attributes[rowIndex].getState():"-";
            case 37:
            	return  messages.getText(attributes[rowIndex].getCondition());
            case 38:
            	return  messages.getText(attributes[rowIndex].getClimate());
            case 39:
            	return  messages.getText(attributes[rowIndex].getLighting());
            case 40:{
            	if(attributes[rowIndex].getType()!=SimpleReportEventAttributes.FINE) {
                    return  (attributes[rowIndex].getCost()!=null) ? attributes[rowIndex].getCost():new Integer(0);
                }else {
            		return  (attributes[rowIndex].getFcost()!=null) ? attributes[rowIndex].getFcost():new Integer(0);
                }
            }
            case 41:
            	return  messages.getText(attributes[rowIndex].getDriverstate());
            case 42:
            	return (attributes[rowIndex].getDayslost()!= null) ? attributes[rowIndex].getDayslost() : new Integer(0);
            case 43:
            	return (attributes[rowIndex].getOtherpartydayslost()!= null) ? attributes[rowIndex].getOtherpartydayslost() : new Integer(0);
            case 44:
            	return (attributes[rowIndex].getSafefunccount()!= null) ? attributes[rowIndex].getSafefunccount() : new Integer(0);
            case 45:
            	return (attributes[rowIndex].getSafeothercount()!= null) ?attributes[rowIndex].getSafeothercount() : new Integer(0);
            case 46:
            	return (attributes[rowIndex].getInjuredfunccount()!= null) ? attributes[rowIndex].getInjuredfunccount() : new Integer(0);
            case 47:
            	return (attributes[rowIndex].getInjuredothercount()!= null) ? attributes[rowIndex].getInjuredothercount() : new Integer(0);
            case 48:
            	return (attributes[rowIndex].getFatalitiesfunccount()!= null) ? attributes[rowIndex].getFatalitiesfunccount() : new Integer(0);
            case 49:
            	return (attributes[rowIndex].getFatalitiesothercount()!= null) ? attributes[rowIndex].getFatalitiesothercount() : new Integer(0);
            case 50:{
            	if (attributes[rowIndex].getPlate()!=null)
            		return attributes[rowIndex].getPlate();
            	else
            		return attributes[rowIndex].getVehiclePlate();
            }
            case 51:{
            	if (attributes[rowIndex].getModel()!=null)
            		return attributes[rowIndex].getModel();
            	else
            		return attributes[rowIndex].getVehicleModel();
            }
            case 52:{
            	if (attributes[rowIndex].getTrade()!=null)
            		return (attributes[rowIndex].getTrade().equals("generic.messages.nodata")) ? messages.getText(attributes[rowIndex].getTrade()) : attributes[rowIndex].getTrade();
            	else
                    return (attributes[rowIndex].getVehicleTrade().equals("generic.messages.nodata")) ? messages.getText(attributes[rowIndex].getVehicleTrade()) : attributes[rowIndex].getVehicleTrade();
            }
            case 53:{
            	if (attributes[rowIndex].getYear()!=null)
                    if(!isNumber(attributes[rowIndex].getYear())) {
                        return messages.getText(attributes[rowIndex].getYear());
                    }else {
                        return attributes[rowIndex].getYear();
                    }
            	else
            		return attributes[rowIndex].getVehicleYear();
            }
            case 54:
            	return attributes[rowIndex].getBirthDate();
            case 55:
            	return attributes[rowIndex].getInclusionDate();
            case 56:
            	return attributes[rowIndex].getLicenseCategory();
            case 57:
            	return attributes[rowIndex].getLicenseExpiry();
            case 58:{
            	if (attributes[rowIndex].isCompanyCar()==true)
            		return messages.getText("generic.messages.yes");
            	else
            		return messages.getText("generic.messages.no");
            
            }
            case 59:
            	switch(attributes[rowIndex].getType()) {
                case EventDataType.INCIDENT:
                    return "1";
                case EventDataType.FINE:
                    return "3";
                default:
                    return "2";
            	}
            case 60:
            	if(attributes[rowIndex].getRecomendation() == null) {
            		return null;
            	}
            	String s = ReportDataUtil.toHTMLString(attributes[rowIndex].getRecomendation());
            	return s;
            case 61:
            	if(attributes[rowIndex].getType()==3){
            		return messages.getText(attributes[rowIndex].getFineType());
            	}else return "-"; 
            
            case 62:
            	return new Integer(total);
            case 63:
            	return attributes[rowIndex].getVehicleId();
            case 64:
                return attributes[rowIndex].getDivOrg6();
            case 65:
                return attributes[rowIndex].getDivOrg7();
            case 66:
                return attributes[rowIndex].getDivOrg8();
            case 67:
                return attributes[rowIndex].getDivOrg9();
            case 68:
                if(attributes[rowIndex].getSupervisionObservations() == null) {
                    return "---";
                }
                return ReportDataUtil.toHTMLString(attributes[rowIndex].getSupervisionObservations());
            case 69:
                return  (attributes[rowIndex].getCity()!=null) ? ReportDataUtil.toHTMLString(attributes[rowIndex].getCity()):"-";    
            case 70:
                return  (attributes[rowIndex].getEvitability()!=null) ? messages.getText(attributes[rowIndex].getEvitability()):"-";
            case 71:
            	return attributes[rowIndex].getWorkingHours();
            case 72:
            	// Severity:
            	Integer i = attributes[rowIndex].getSeverity();
            	if (i != null){
            		return messages.getText("event.severityindex." + i);
            	}else{
            		return messages.getText("generic.messages.nodata");
            	}
            case 73:
            	// CorporationId, WWID
            	String corporationId = attributes[rowIndex].getCorporationId();
            	return (corporationId == null)?"":corporationId;
            	
            case 74:
            	// RealCost?
            	if (attributes[rowIndex].isRealCost() == true)
            		return messages.getText("generic.messages.yes");
            	else
            		return messages.getText("generic.messages.no");
            	
            case 75:
            	// companyAmount;
            	
            	if (attributes[rowIndex].getCompanyAmount() != null){
            		return attributes[rowIndex].getCompanyAmount().toString();
            	}else{
            		return "---";
            	}
            case 76: 
            	// driverAmount;
            	if (attributes[rowIndex].getDriverAmount() != null){
            		return attributes[rowIndex].getDriverAmount().toString();
            	}else{
            		return "---";
            	}
            	
            case 77:
               	// thirdAmount;
            	if (attributes[rowIndex].getThirdAmount() != null){
            		return attributes[rowIndex].getThirdAmount().toString();
            	}else{
            		return "---";
            	}
            case 78:
               	// thirdAmount;
            	if (attributes[rowIndex].getWhoDrives() != null){
            		return messages.getText(attributes[rowIndex].getWhoDrives());
            	}else{
            		return "---";
            	}
            case 79:
            	// tipo de vehiculo
            	if (attributes[rowIndex].getVehicleType() != null){
            		return messages.getText(attributes[rowIndex].getVehicleType());
            	}else
            		return "---";
            case 80:
            	// assignedVehicle
            	if (attributes[rowIndex].getAssignedVehicle() != null){
            		return messages.getText(attributes[rowIndex].getAssignedVehicle());
            	}else
            		return "---";
            case 81:
            	return attributes[rowIndex].getDriverLastName();

            case 82: 
            	// InfringementNotice;
            	if (attributes[rowIndex].getInfringementNotice() != null){
            		return attributes[rowIndex].getInfringementNotice().toString();
            	}else{
            		return "---";
            	}
            case 83: 
            	// points;
            	if (attributes[rowIndex].getPoints() != null){
            		return attributes[rowIndex].getPoints().toString();
            	}else{
            		return "---";
            	}
            case 84: 
            	// article;
            	return attributes[rowIndex].getArticle();
            case 85:
                return attributes[rowIndex].getExpiry();
            case 86:
                return  (attributes[rowIndex].getSupervisedAccident() != null) ? messages.getText(attributes[rowIndex].getSupervisedAccident()) : messages.getText(attributes[rowIndex].getSupervisedIncident());
            case 87:
                return  (attributes[rowIndex].getSupervisedCollision() != null) ? messages.getText(attributes[rowIndex].getSupervisedCollision()):"-";
            case 88:
                return  (attributes[rowIndex].isDoubleCabin()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 89:
                return  (attributes[rowIndex].getClasification1()!=null) ? messages.getText(attributes[rowIndex].getClasification1()):"";
            case 90:
                return  (attributes[rowIndex].getClasification2()!=null) ? messages.getText(attributes[rowIndex].getClasification2()):"";
            case 91:
                return  (attributes[rowIndex].getClasification3()!=null) ? messages.getText(attributes[rowIndex].getClasification3()):"";
            case 92:
                return  (attributes[rowIndex].getClasificationText()!=null) ?messages.getText(attributes[rowIndex].getClasificationText()):"";
            case 93:
                return  (attributes[rowIndex].getClasification4()!=null) ? messages.getText(attributes[rowIndex].getClasification4()):"";
            case 94:
                return  (attributes[rowIndex].getClasificationcepa1()!=null) ? messages.getText(attributes[rowIndex].getClasificationcepa1()):"";
            case 95:
                return  (attributes[rowIndex].getClasificationcepa2()!=null) ? messages.getText(attributes[rowIndex].getClasificationcepa2()):"";
            case 96:
                return  (attributes[rowIndex].getClasificationcepa3()!=null) ? messages.getText(attributes[rowIndex].getClasificationcepa3()):"";
            case 97:
                return  (attributes[rowIndex].getClasificationcepaText()!=null) ?messages.getText(attributes[rowIndex].getClasificationcepaText()):"";
            case 98:
                return  (attributes[rowIndex].getClasificationcepa4()!=null) ? messages.getText(attributes[rowIndex].getClasificationcepa4()):"";   
            case 99:
                return  (attributes[rowIndex].getCharged()!=null) ? messages.getText(attributes[rowIndex].getCharged()):"";
            case 100:
                return  (attributes[rowIndex].getOtherCompanyVehicles()!=null) ? messages.getText(attributes[rowIndex].getOtherCompanyVehicles()):"";
            case 101:
            	if(attributes[rowIndex].getExtracost1() != null) {
            		return (attributes[rowIndex].getExtracost1().intValue() == 1) ? messages.getText("datatype.syngentaseverity.high") : messages.getText("datatype.syngentaseverity.low");
            	}
            case 102:
            	return attributes[rowIndex].getAlcoholbreath();
            case 103:
            	if(attributes[rowIndex].getExtracost2() != null) {
            		return (attributes[rowIndex].getExtracost2().intValue() == 1) ? messages.getText("event.syngentaclasification.evitable") : messages.getText("event.syngentaclasification.unevitable");
            	}
            case 104:
            	return attributes[rowIndex].getStoppedvehicle();
            case 105:
            	if(attributes[rowIndex].getExtracost1() != null) {
            		return (attributes[rowIndex].getExtracost3().intValue() == 1) ? messages.getText("generic.messages.reportable") : messages.getText("generic.messages.unreportable");
            	}
            case 106:
            	return (attributes[rowIndex].getRecordable()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
        }
        return null;
    }

    private String getClasificationResult(int rowIndex, int columnIndex){
    	if (attributes[rowIndex].getType()==SimpleReportEventAttributes.ACCIDENT)
    		return messages.getText(attributes[rowIndex].getAccident());
    	else if (attributes[rowIndex].getType()==SimpleReportEventAttributes.INCIDENT)
    		return messages.getText(attributes[rowIndex].getIncident());
   		else if (attributes[rowIndex].getType()==SimpleReportEventAttributes.NEARMISS)
    		return messages.getText(attributes[rowIndex].getNearmiss());
    	else return messages.getText(attributes[rowIndex].getQualify());
    }
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "id";
            case 1:
                return "driverFirstName";
            case 2:
                return "color";
            case 3:
                return "eventdate";
            case 4:
                return "type";
            case 5:
                return "country";
            case 6:
                return "divOrg1";
            case 7:
                return "divOrg2";
            case 8:
                return "divOrg3";
            case 9:
                return "divOrg4";
            case 10:
                return "divOrg5";
            case 11:
                return "driverId";
            case 12:
                return "story";
            case 13:
            	return "clasification";
            case 14:
            	return "hour";
            case 15:
            	return "damage";
            case 16:
            	return "zone";
            case 17:
            	return "working";
            case 18:
            	return "policereport";
            case 19:
            	return "collisionclasification";
            case 20:
            	return "handle";
            case 21:
            	return "hitrun";
            case 22:
            	return "principalfactor";
            case 23:
            	return "collisionangle";
            case 24:
            	return "maxspeed";
            case 25:
            	return "collisionspeed";
            case 26:
            	return "getVehicleSpeed";
            case 27:
            	return "reportedSpeed";
            case 28:
            	return "speedunit";
            case 29:
            	return "selfbelt";
            case 30:
            	return "alcohol";
            case 31:
            	return "othercompanyvehicles";
            case 32:
            	return "cellular";
            case 33:
            	return "way";
            case 34:
            	return "direction";
            case 35:
            	return "control";
            case 36:
            	return "stateevent";
            case 37:
            	return  "condition";
            case 38:
            	return  "climate";
            case 39:
            	return  "lighting";
            case 40:
            	return  "cost";
            case 41:
            	return  "driverstate";
            case 42:
            	return "dayslost";
            case 43:
            	return "otherpartydayslost";
            case 44:
            	return "safefunccount";
            case 45:
            	return "safeothercount";
            case 46:
            	return "injuredfunccount";
            case 47:
            	return "injuredothercount";
            case 48:
            	return "fatalitiesfunccount";
            case 49:
            	return "fatalitiesothercount";
            case 50:
            	return "plate";
            case 51:
            	return "model";
            case 52:
            	return "trade";
            case 53:
            	return "year";
            case 54:
            	return "birthdate";
            case 55:
            	return "inclusiondate";
            case 56:
            	return "licensecategory";
            case 57:
            	return "licenseexpiry";
            case 58:
            	return "companycar";
            case 59: 
            	return "typenumber";
            case 60:
            	return "recomendation";
            case 61:
            	return"finetype";
            case 62: 
            	return "total";
            case 63:
            	return "vehicleid";
            case 64:
                return "divOrg6";
            case 65:
                return "divOrg7";
            case 66:
                return "divOrg8";
            case 67:
                return "divOrg9";
            case 68:
                return "supervisionObservations";
            case 69:
                return "city";
            case 70:
                return "evitability";
            case 71:
            	return "workinghours";
            case 72:
            	return "severity";
            case 73:
            	return "corporationId";
            case 74:
            	return "realCost";
            case 75:
                return "companyAmount";
            case 76:
                return "driverAmount";
            case 77:
               	return "thirdAmount";
            case 78:
               	return "whoDrives";
            case 79:
            	return "vehicleType";
            case 80:
            	return "assignedVehicle";
            case 81:
                return "driverLastName";
            case 82:
                return "infringementNotice";
            case 83: 
                return "points";
            case 84: 
                return "article";
            case 85: 
                return "expiry";
            case 86:
                return "supervisionClasification";
            case 87:
                return "supervisionCollision";
            case 88:
                return "doubleCabin";
            case 89:
                return "clasification1";
            case 90:
                return "clasification2";
            case 91:
                return "clasification3";
            case 92:
                return "clasificationText";
            case 93:
                return "clasification4";
            case 94:
                return "clasificationcepa1";
            case 95:
                return "clasificationcepa2";
            case 96:
                return "clasificationcepa3";
            case 97:
                return "clasificationcepaText";
            case 98:
                return "clasificationcepa4";
            case 99:
                return "consequences";
            case 100:
                return "gold";
            case 101:
                return "severity2";
            case 102:
                return "severityJustification";
            case 103:
                return "evitability2";
            case 104:
                return "evitabilityJustification";
            case 105:
                return "responsability";
            case 106:
                return "recordable";
        }
        return null;
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }

    private boolean isNumber(String string) {
        try {
            Integer.parseInt(string);
        }catch(Exception e) {
            return false;
        }
        return true;
    }
    
}
