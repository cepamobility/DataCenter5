/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.event.report;

import java.io.Serializable;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import communication.event.tables.SimpleReportEventAttributes;
import communication.language.Text;
import communication.util.ListResult;
import communication.util.ReportDataUtil;

public class EventReportData extends AbstractTableModel 
    implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total;
    private SimpleReportEventAttributes[] attributes;
    private boolean color = true;
    private Text messages;
    
    public EventReportData() {
        total = 0;
        attributes = new SimpleReportEventAttributes[0];
         
    }
    
    public EventReportData(ListResult reportEvents) {
        total = reportEvents.getTotal();
        Collection elements = reportEvents.getElements();
        attributes = (SimpleReportEventAttributes[])elements.toArray(new SimpleReportEventAttributes[0]);
        
    }

    public int getRowCount() {
        return attributes.length;
    }
    public int getTotal() {
        return total;
    }

    public int getColumnCount() {
        return 40;
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
                    case SimpleReportEventAttributes.INCIDENT:
                        return messages.getText("generic.event.incident");
                    case SimpleReportEventAttributes.NEARMISS:
                        return messages.getText("generic.event.nearmiss");
                    case SimpleReportEventAttributes.FINE:
                    	return messages.getText("generic.event.fine")+": "+messages.getText(attributes[rowIndex].getFineType());
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
                	return attributes[rowIndex].getObservations();
                default:
                    return ReportDataUtil.toHTMLString(attributes[rowIndex].getStory());
            }
            case 13:
            	return getClasificationResult(rowIndex,columnIndex);
            case 14:
            	switch(attributes[rowIndex].getType()) {
                case SimpleReportEventAttributes.INCIDENT:
                    return "1";
                case SimpleReportEventAttributes.FINE:
                    return "3";
                default:
                    return "2";
            	}
            case 15:
            	return ReportDataUtil.toHTMLString(attributes[rowIndex].getRecomendation());
            case 16:
            	if(attributes[rowIndex].getType()==3){
            		return messages.getText(attributes[rowIndex].getFineType());
            	}else return null; 
            case 17:
            	return  new Integer(total);
            case 18:
                return attributes[rowIndex].getDivOrg6();
            case 19:
                return attributes[rowIndex].getDivOrg7();
            case 20:
                return attributes[rowIndex].getDivOrg8();
            case 21:
                return attributes[rowIndex].getDivOrg9();
            case 22:
                return ReportDataUtil.toHTMLString(attributes[rowIndex].getSupervisionObservations());
            case 23:
            	return attributes[rowIndex].getDriverLastName();
            case 24:
            	return attributes[rowIndex].getExtraCost1();
            case 25:
            	return attributes[rowIndex].getExtraCost2();
            case 26:
            	return attributes[rowIndex].getExtraCost3();
            case 27:
                return  (attributes[rowIndex].getEvitability()!=null) ? messages.getText(attributes[rowIndex].getEvitability()):"-";
            case 28:
                return  (attributes[rowIndex].getSupervisedAccident() != null) ? messages.getText(attributes[rowIndex].getSupervisedAccident()) : messages.getText(attributes[rowIndex].getSupervisedIncident());
            case 29:
                return  (attributes[rowIndex].getSupervisedCollision() != null) ? messages.getText(attributes[rowIndex].getSupervisedCollision()):"-";
            case 30:
                return  (attributes[rowIndex].getClasification1()!= null) ? messages.getText(attributes[rowIndex].getClasification1()):"-";
            case 31:
                return  (attributes[rowIndex].getClasification2()!= null) ? messages.getText(attributes[rowIndex].getClasification2()):"-";
            case 32:
                return  (attributes[rowIndex].getClasification3()!= null) ? messages.getText(attributes[rowIndex].getClasification3()):"-";
            case 33:
                return  (attributes[rowIndex].getClasification4()!= null) ? messages.getText(attributes[rowIndex].getClasification4()):"-";
            case 34:
                return  (attributes[rowIndex].getClasificationText()!= null) ? attributes[rowIndex].getClasificationText():"-";
            case 35:
                return  (attributes[rowIndex].getClasificationcepa1()!= null) ? messages.getText(attributes[rowIndex].getClasificationcepa1()):"-";
            case 36:
                return  (attributes[rowIndex].getClasificationcepa2()!= null) ? messages.getText(attributes[rowIndex].getClasificationcepa2()):"-";
            case 37:
                return  (attributes[rowIndex].getClasificationcepa3()!= null) ? messages.getText(attributes[rowIndex].getClasificationcepa3()):"-";
            case 38:
                return  (attributes[rowIndex].getClasificationcepa4()!= null) ? messages.getText(attributes[rowIndex].getClasificationcepa4()):"-";
            case 39:
                return  (attributes[rowIndex].getClasificationcepaText()!= null) ? attributes[rowIndex].getClasificationcepaText():"-";
        
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
            	return "typenumber";
            case 15:
            	return "recomendation";
            case 16:
            	return"finetype";
            case 17: 
            	return "total";
            case 18:
                return "divOrg6";
            case 19:
                return "divOrg7";
            case 20:
                return "divOrg8";
            case 21:
                return "divOrg9";
            case 22:
                return "supervisionobs";
            case 23:
                return "driverLastName";
            case 24:
                return "extraCost1";
            case 25:
                return "extraCost2";
            case 26:
                return "extraCost3";
            case 27:
                return "evitability";
            case 28:
                return "supervisionClasification";
            case 29:
                return "supervisionCollision";
            case 30:
                return "clasification1";
            case 31:
                return "clasification2";
            case 32:
                return "clasification3";
            case 33:
                return "clasification4";
            case 34:
                return "clasificationText";
            case 35:
                return "clasificationcepa1";
            case 36:
                return "clasificationcepa2";
            case 37:
                return "clasificationcepa3";
            case 38:
                return "clasificationcepa4";
            case 39:
                return "clasificationcepaText";
        }
        return null;
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }
}
