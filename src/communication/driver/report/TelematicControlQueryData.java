package communication.driver.report;

import java.io.Serializable;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverData;
import communication.driver.tables.TelematicControlData;
import communication.language.Text;

public class TelematicControlQueryData extends AbstractTableModel 
    implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total = 0;
    private int cantReg = 0;
    private TelematicControlData[] attributes;
    private boolean color = true;
    private Text messages;
    private UserSessionData userSessionData;

    private int[][] principal;
    
    public TelematicControlQueryData() {
        total = 0;
        attributes = new TelematicControlData[0];
        principal = new int[3][2];
    }
    
    public TelematicControlQueryData(Collection reportDrivers,int total, UserSessionData usr) {
        this.cantReg = reportDrivers.size();
        attributes = (TelematicControlData[])reportDrivers.toArray(new TelematicControlData[0]);
        userSessionData = usr;
        principal = userSessionData.getFilter().getPrincipalDivOrgs();
        this.total = total;
    }
    
    public TelematicControlQueryData(Collection reportDrivers,int[][] principal) {
        total = reportDrivers.size();
        attributes = (TelematicControlData[])reportDrivers.toArray(new TelematicControlData[0]);
        this.principal = principal;
    }

    public int getRowCount() {
        return cantReg;
    }
    
    /**
	 * @return Returns the total.
	 */
	public int getTotal() {
		return total;
	}

    public int getColumnCount() {
        return 28;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return String.valueOf(attributes[rowIndex].getDriver().getId());
            case 1:
                return attributes[rowIndex].getDriver().getFirstName();
            case 8:
            	return attributes[rowIndex].getDriver().getLastName();
            case 2:
                return attributes[rowIndex].getDriver().getData(
                		DriverData.DIV_ORG,principal[0][0],principal[0][1]);
            case 3:
                return attributes[rowIndex].getDriver().getData(
                		DriverData.DIV_ORG,principal[1][0],principal[1][1]);
            case 4:
                return attributes[rowIndex].getDriver().getData(
                		DriverData.DIV_ORG,principal[2][0],principal[2][1]);
            case 5:
                return (attributes[rowIndex].getDriver().getCorporationId() != null) ?
                		attributes[rowIndex].getDriver().getCorporationId(): "---";
            case 6:
                color = !color;
                return new Boolean(color);
            case 7:
            	return new Integer(this.total);
            case 9:
            	return (attributes[rowIndex].getDate() != null) ? attributes[rowIndex].getDate(): "---";
            case 10:
            	return (attributes[rowIndex].getType() != null) ? 
            			messages.getText(TelematicControlData.
            					getTypeString(attributes[rowIndex].getType()) ): "---";
            case 11:
            	return (attributes[rowIndex].getRecomendation() != null) ? 
            			messages.getText(TelematicControlData.
            					getRecomendationString(attributes[rowIndex].getRecomendation()) ): "---";
            case 12:
                return (attributes[rowIndex].getObservations() != null) ?
                		attributes[rowIndex].getObservations(): "---";	       
            case 13:
            	 return attributes[rowIndex].getDriver().getData(DriverData.DIV_ORG,3,1);
            case 14:
            	 return attributes[rowIndex].getDriver().getData(
            			 DriverData.DIV_ORG,principal[3][0],principal[3][1]);
            case 15:
            	return (attributes[rowIndex].getColor() != null) ? 
            			messages.getText(attributes[rowIndex].getColor()):"---";
            case 16:
            	return (attributes[rowIndex].getDriver().getCountryName() != null) ? 
            			messages.getText(attributes[rowIndex].getDriver().getCountryName()): "---";
            case 17:
            	return  (attributes[rowIndex].getDriver().getEmail() != null ) ?
            			attributes[rowIndex].getDriver().getEmail() : "---";
            case 18:
            	return (attributes[rowIndex].getOccurrences() != null) ? 
            			attributes[rowIndex].getOccurrences(): "---";
            case 19:
            	return (attributes[rowIndex].getSpeed() != null) ? attributes[rowIndex].getSpeed().toString(): "---";
            case 20:
            	return (attributes[rowIndex].getLimitExceeded() != null && attributes[rowIndex].getLimitExceeded() != 0) ? 
            			attributes[rowIndex].getLimitExceeded().toString(): "---";
            case 21:
            	return (attributes[rowIndex].getTime() != null)?attributes[rowIndex].getTime():"---";
            case 22:
            	return (attributes[rowIndex].getDuration() != null) ? attributes[rowIndex].getDuration(): "---";
            case 23:
            	return (attributes[rowIndex].getTotalDuration() != null) ? 
            			attributes[rowIndex].getTotalDuration():"---";
            case 24:
            	return (attributes[rowIndex].getDistance() != null) ? attributes[rowIndex].getDistance().toString(): "---";
            case 25:
            	return (attributes[rowIndex].getAverageVelocity() != null ) ? attributes[rowIndex].getAverageVelocity(): "---";
            case 26:
            	return (attributes[rowIndex].getLimitExceededRain() != null && attributes[rowIndex].getLimitExceededRain() != 0) ?
            			attributes[rowIndex].getLimitExceededRain().toString(): "---";
            case 27:
            	return (attributes[rowIndex].getVehicle() != null) ?
            			attributes[rowIndex].getVehicle().getMatricula() : "---";
        }
        return null;
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
                return "iddallas";
            case 6:
                return "color"; // color de la linea del registro
            case 7:
            	return "total";
            case 8:
                return "lastname";
            case 9:
            	return "date";
            case 10:
            	return "type";
            case 11:
            	return "recomendation";
            case 12:
            	return "observation";
            case 13:
            	return "divorgExtra1";
            case 14:
                return "divorg4";
            case 15:
            	return "colorFilter"; // color del conductor: rojo, amarilllo o verde
            case 16:
            	return "country";
            case 17:
            	return "email";
            case 18:
            	return "occurrences";
            case 19:
            	return "speed";
            case 20:
            	return "limitExceeded";
            case 21:
            	return "time";
            case 22:
            	return "duration";
            case 23:
            	return "totalDuration";
            case 24:
            	return "distance";
            case 25:
            	return "averageVelocity";
            case 26:
            	return "limitExceededRain";
            case 27:
            	return "vehicle";
        }
        return null;
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }
}
