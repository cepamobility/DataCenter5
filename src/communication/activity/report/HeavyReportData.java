/**
 * Created on 21-abr-2008
 * CEPA
 * DataCenter 5
 */
package communication.activity.report;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.language.Text;

public class HeavyReportData extends AbstractTableModel implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String activity;
    private String driver;
    private String instructor;
    private Object[][] values;
    private String note;
    private String observations;
    boolean color = false;
    private Text messages;
    private String city;
    private String country;
    private String date;

    public HeavyReportData(CEPAActivityScheduleData activityData, CEPAActivityScheduleRegistryData registryData, Object[][] values, Text messages) {
        this.activity = messages.getText(activityData.getDefinition().getResourceKeyName());
        this.driver = registryData.getDriver().getFirstName()+" "+registryData.getDriver().getLastName();
        this.instructor = registryData.getInstructor();
        this.values = values;
        this.note = messages.getText(registryData.getNote());
        this.observations = registryData.getObs();
        this.messages = messages;
        this.city = activityData.getCity();
        this.country = messages.getText(activityData.getCountryName());
        this.date = formatDate(activityData.getDate());
    }

    public int getRowCount() {
        return values.length;
    }

    public int getColumnCount() {
        return 14;
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "activity";
            case 1:
                return "driver";
            case 2:
                return "instructor";
            case 3:
                return "point";
            case 4:
                return "item";
            case 5:
                return "yes";
            case 6:
                return "no";
            case 7:
                return "must";
            case 8:
                return "note";
            case 9:
                return "observations";
            case 10:
                return "color";
            case 11:
                return "city";
            case 12:
                return "country";
            case 13:
                return "date";
        }
        return null;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return messages.getText("generic.activity.activitydata.activitytype")+": "+activity;
            case 1:
                return messages.getText("generic.activity.form.evaluacionencabinapesado.driver") +": "+ driver;
            case 2:
                return messages.getText("generic.activity.form.evaluacionencabinapesado.instructor")+ ": " + instructor;
            case 3:case 4:
                return values[rowIndex][columnIndex-3];
            case 5:
                return (((Integer)values[rowIndex][2]).intValue() == 1) ? "X" : "--";
            case 6:
                return (((Integer)values[rowIndex][2]).intValue() == 2) ? "X" : "--";
            case 7:
                return (((Integer)values[rowIndex][2]).intValue() == 3) ? "X" : "--";
            case 8:
                return messages.getText("generic.activity.note")+": "+note;
            case 9:
                return observations;
            case 10:
                color = !color;
                return new Boolean(color);
            case 11:
                return messages.getText("generic.activity.activitydata.city")+": "+city;
            case 12:
                return messages.getText("generic.activity.activitydata.country")+": "+country;
            case 13:
                return messages.getText("generic.activity.activitydata.date")+": "+date;
        }
        return null;
    }

    public static String formatDate(Date date) {
        if(date == null) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);
    }
}
