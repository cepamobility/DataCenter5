/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.queries.report;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.jfree.data.general.DefaultPieDataset;

import communication.event.tables.EventDataType;
import communication.fleet.util.EventTypeData;
import communication.fleet.util.FineTypeData;
import communication.fleet.util.TypeData;
import communication.language.Text;

public class ReportAccidentTypeData extends AbstractTableModel 
	implements Serializable {

    static final long serialVersionUID = 1L;
    
    private int total;
    private TypeData[] attributes;
    private boolean color = true;
    private Text messages;

    private DefaultPieDataset accidents = new DefaultPieDataset();
    private DefaultPieDataset incidents = new DefaultPieDataset();
    private DefaultPieDataset nearmiss = new DefaultPieDataset();
   	private DefaultPieDataset fines = new DefaultPieDataset();

    private int order1;
    private int order2;
    private int order3;
    private int order4;

    private boolean accidentsChart = false;
    private boolean incidentsChart = false;
    private boolean nearmissChart = false;
    private boolean finesChart = false;

    public ReportAccidentTypeData() {
        total = 0;
    }
    
    public ReportAccidentTypeData(Collection details, Text messages,int order1,int order2,int order3,int order4) {
        total = details.size();
        Collections.sort((List) details);
        attributes = (TypeData[])details.toArray(new TypeData[0]);
        this.messages = messages;
        this.order1 = order1;
        this.order2 = order2;
        this.order3 = order3;
        this.order4 = order4;
        loadReports();
    }

    private void loadReports() {
        for(int rowIndex = 0; rowIndex < attributes.length; rowIndex++) {
            if(attributes[rowIndex].getType().equals("fine")) {
                fines.setValue(messages.getText(attributes[rowIndex].getEventType()),attributes[rowIndex].getCount());
                finesChart = true;
            }else if(attributes[rowIndex].getType().equals("accident")) {
                accidents.setValue(messages.getText(attributes[rowIndex].getEventType()),attributes[rowIndex].getCount());
                accidentsChart = true;
            }else if(attributes[rowIndex].getType().equals("nearmiss")) {
                nearmiss.setValue(messages.getText(attributes[rowIndex].getEventType()),attributes[rowIndex].getCount());
                nearmissChart = true;
            }else {
                incidents.setValue(messages.getText(attributes[rowIndex].getEventType()),attributes[rowIndex].getCount());
                incidentsChart = true;
            }
        }
    }

    public int getRowCount() {
        return total;
    }

    public int getColumnCount() {
        return 35;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return attributes[rowIndex].getType();
            case 1:
                return attributes[rowIndex].getDivOrg1Id();
            case 2:
                return (order1 == 3) ? messages.getText(attributes[rowIndex].getDivOrg1Name()) : attributes[rowIndex].getDivOrg1Name();
            case 3:
                return attributes[rowIndex].getDivOrg2Id();
            case 4:
                return (order2 == 3) ? messages.getText(attributes[rowIndex].getDivOrg2Name()) : attributes[rowIndex].getDivOrg2Name();
            case 5:
                return attributes[rowIndex].getDivOrg3Id();
            case 6:
                return (order3 == 3) ? messages.getText(attributes[rowIndex].getDivOrg3Name()) : attributes[rowIndex].getDivOrg3Name();
            case 7:
                return attributes[rowIndex].getDivOrg4Id();
            case 8:
                return (order4 == 3) ? messages.getText(attributes[rowIndex].getDivOrg4Name()) : attributes[rowIndex].getDivOrg4Name();
            case 9:
                if(attributes[rowIndex].getType().equals("fine")) {
                    return new Integer(((FineTypeData)attributes[rowIndex]).getExtserious());
                }else {
                    return new Integer(((EventTypeData)attributes[rowIndex]).getDamage());
                }
            case 10:
                if(attributes[rowIndex].getType().equals("fine")) {
                    return new Integer(((FineTypeData)attributes[rowIndex]).getSerious());
                }else {
                    return new Integer(((EventTypeData)attributes[rowIndex]).getFuncInjured());
                }
            case 11:
                if(attributes[rowIndex].getType().equals("fine")) {
                    return new Integer(((FineTypeData)attributes[rowIndex]).getHalf());
                }else {
                    return new Integer(((EventTypeData)attributes[rowIndex]).getOtherInjured());
                }
            case 12:
                if(attributes[rowIndex].getType().equals("fine")) {
                    return new Integer(((FineTypeData)attributes[rowIndex]).getSoft());
                }else {
                    return new Integer(((EventTypeData)attributes[rowIndex]).getFuncOtherInjured());
                }
            case 13:
                if(attributes[rowIndex].getType().equals("fine")) {
                    return new Integer(((FineTypeData)attributes[rowIndex]).getOthers());
                }else {
                    return new Integer(((EventTypeData)attributes[rowIndex]).getFuncFatal());
                }
            case 14:
                if(attributes[rowIndex].getType().equals("fine")) {
                    return new Integer(((FineTypeData)attributes[rowIndex]).getNodata());
                }else {
                    return new Integer(((EventTypeData)attributes[rowIndex]).getOtherFatal());
                }
            case 15:
                if(!attributes[rowIndex].getType().equals("fine")) {
                    return new Integer(((EventTypeData)attributes[rowIndex]).getFuncInjuredOtherFatal());
                }
                return new Integer(attributes[rowIndex].getCount());
            case 16:
                if(!attributes[rowIndex].getType().equals("fine")) {
                    return new Integer(((EventTypeData)attributes[rowIndex]).getFuncFatalOtherInjured());
                }
                return new Integer(attributes[rowIndex].getCost());
            case 17:
                if(!attributes[rowIndex].getType().equals("fine")) {
                    return new Integer(((EventTypeData)attributes[rowIndex]).getFuncOtherFatal());
                }
                return null;
            case 18:
                if(attributes[rowIndex].getType().equals("fine")) {
                    return messages.getText("query.fine.type.extserious");
                }
                return messages.getText("query.accidenttype.damage");
            case 19:
                if(attributes[rowIndex].getType().equals("fine")) {
                    return messages.getText("query.fine.type.serious");
                }
                return messages.getText("query.accidenttype.driver.injured");
            case 20:
                if(attributes[rowIndex].getType().equals("fine")) {
                    return messages.getText("query.fine.type.half");
                }
                return messages.getText("query.accidenttype.other.injured");
            case 21:
                if(attributes[rowIndex].getType().equals("fine")) {
                    return messages.getText("query.fine.type.notserious");
                }
                return messages.getText("query.accidenttype.func.other.injured");
            case 22:
                if(attributes[rowIndex].getType().equals("fine")) {
                    return messages.getText("query.fine.type.other");
                }
                return messages.getText("query.accidenttype.driver.fatal");
            case 23:
                if(attributes[rowIndex].getType().equals("fine")) {
                    return messages.getText("query.fine.type.nodata");
                }
                return messages.getText("query.accidenttype.other.fatal");
            case 24:
                if(!attributes[rowIndex].getType().equals("fine")) {
                    return messages.getText("query.accidenttype.funcinjured.otherfatal");
                }
                return messages.getText("query.fine.type.count");
            case 25:
                if(!attributes[rowIndex].getType().equals("fine")) {
                    return messages.getText("query.accidenttype.funcfatal.otherinjured");
                }
                return messages.getText("query.fine.type.cost");
            case 26:
                if(!attributes[rowIndex].getType().equals("fine")) {
                    return messages.getText("query.accidenttype.func.other.fatal");
                }
                return "";
            case 27:
                return messages.getText("query.accidenttype."+attributes[rowIndex].getType()+".pl");
            case 28:
                if(!attributes[rowIndex].getType().equals("fine")) {
                    return new Integer(attributes[rowIndex].getCount());
                }
                return null;
            case 29:
                if(!attributes[rowIndex].getType().equals("fine")) {
                    return new Integer(attributes[rowIndex].getCost());
                }
                return null;
            case 30:
                color = !color;
                return new Boolean(color);
            case 31:
                return messages.getText(attributes[rowIndex].getEventType());
            case 32:
                if(!attributes[rowIndex].getType().equals("fine")) {
                    return messages.getText("query.fine.type.count");
                }
                return "";
            case 33:
                if(!attributes[rowIndex].getType().equals("fine")) {
                    return messages.getText("query.fine.type.cost");
                }
                return "";
            case 34:
                return new Boolean(!attributes[rowIndex].getType().equals("fine"));
        }
        return null;
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "accident";
            case 1:
                return "div1Id";
            case 2:
                return "div1Name";
            case 3:
                return "div2Id";
            case 4:
                return "div2Name";
            case 5:
                return "div3Id";
            case 6:
                return "div3Name";
            case 7:
                return "div4Id";
            case 8:
                return "div4Name";
            case 9:
                return "field1";
            case 10:
                return "field2";
            case 11:
                return "field3";
            case 12:
                return "field4";
            case 13:
                return "field5";
            case 14:
                return "field6";
            case 15:
                return "field7";
            case 16:
                return "field8";
            case 17:
                return "field9";
            case 18:
                return "title1";
            case 19:
                return "title2";
            case 20:
                return "title3";
            case 21:
                return "title4";
            case 22:
                return "title5";
            case 23:
                return "title6";
            case 24:
                return "title7";
            case 25:
                return "title8";
            case 26:
                return "title9";
            case 27:
                return "type";
            case 28:
                return "field10";
            case 29:
                return "field11";
            case 30:
                return "color";
            case 31:
                return "eventType";
            case 32:
                return "title10";
            case 33:
                return "title11";
            case 34:
                return "color2";
        }
        return "unit";
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }
    
    
    public DefaultPieDataset getDataset(int event) {
        switch(event) {
            case EventDataType.ACCIDENT:
                return accidents;
            case EventDataType.INCIDENT:
                return incidents;
            case EventDataType.NEARMISS:
                return nearmiss;
            case EventDataType.FINE:
                return fines;
        }
        return null;
    }

    public boolean accidentsChart() {
        return accidentsChart;
    }

    public boolean incidentsChart() {
        return incidentsChart;
    }

    public boolean nearmissChart() {
        return nearmissChart;
    }

    public boolean fineChart() {
        return finesChart;
    }
}
