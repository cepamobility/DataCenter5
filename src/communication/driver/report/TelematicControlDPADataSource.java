package communication.driver.report;


import persistence.util.Util;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import communication.alert.AlertConstants;
import communication.driver.tables.TelematicControlData;
import communication.language.Text;

public class TelematicControlDPADataSource implements JRDataSource {

    private TelematicControlData value;
    private int index = -1;
    private int count = 0;
	private Text messages;
    
    public TelematicControlDPADataSource(TelematicControlData value, Text messages) {
    	this.value = value;
    	this.messages = messages;
    	switch(value.getType().intValue()) {
			case TelematicControlData.TYPE_EXCESSIVE_SPEED:
				count = 9;
				break;
			case TelematicControlData.TYPE_FREE_TIME:
				count = 9;
				break;
			case TelematicControlData.TYPE_WORK_HOURS:
				count = 7;
				break;
			case TelematicControlData.TYPE_INSERTICIAL_REPORT:
				count = 8;
				break;
			case TelematicControlData.TYPE_24HOOUR_WITHOUT_REPORT:
				count = 5;
				break;
			case TelematicControlData.TYPE_WEEK_WORK:
				count = 8;
				break;
			case TelematicControlData.TYPE_HARD_START:
			case TelematicControlData.TYPE_HARD_STOP:
				count = 7;
				break;
    	}
    }
    
    public boolean next() throws JRException {
        if(index < count) {
        	index++;
        	return true;
        }
        return false;
    }

    public Object getFieldValue(JRField field) throws JRException {
    	if(value.getType().intValue()==TelematicControlData.TYPE_24HOOUR_WITHOUT_REPORT){
    		return getFieldValue24(field);
    	}
    	switch(index) {
			case 0:
    	    	if(field.getName().equals("text")) {
    	    		return value.getDriver()!=null?messages.getText("driver.telematic.driver"):"Placa";
    	    	}
    	    	if(field.getName().equals("value")) {
    	    		return value.getDriver()!=null?value.getDriver().getFirstName() + " " + value.getDriver().getLastName():value.getVehicle().getMatricula();
    	    	}
    	    	if(field.getName().equals("tabla1")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla1.jpg";
    	    	}
    	    	if(field.getName().equals("tabla2")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla3.jpg";
    	    	}

			case 1:
    	    	if(field.getName().equals("text")) {
    	    		return "Veiculo associado";
    	    	}
    	    	if(field.getName().equals("value")) {
    	    		return value.getVehicle()!=null?value.getVehicle().getMatricula():"---";
    	    	}
    	    	if(field.getName().equals("tabla1")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla2.jpg";
    	    	}
    	    	if(field.getName().equals("tabla2")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla4.jpg";
    	    	}
			case 2:
    	    	if(field.getName().equals("text")) {
    	    		return "Pontos";
    	    	}
    	    	if(field.getName().equals("value")) {
    	    		return getDPAPointLabel(value.getPoint());
    	    	}
    	    	if(field.getName().equals("tabla1")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla1.jpg";
    	    	}
    	    	if(field.getName().equals("tabla2")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla3.jpg";
    	    	}
			case 3:
    	    	if(field.getName().equals("text")) {
    	    		switch(value.getType().intValue()) {
    				case TelematicControlData.TYPE_EXCESSIVE_SPEED:
        	    		return messages.getText("driver.telematic.speed");
    				case TelematicControlData.TYPE_HARD_START:
    				case TelematicControlData.TYPE_HARD_STOP:
        	    		return messages.getText("driver.telematic.speed2");
	    				default:
	           	    		return messages.getText("driver.telematic.date");
    	    		}
    	    	}
    	    	if(field.getName().equals("value")) {
    	    		switch(value.getType().intValue()) {
	    				case TelematicControlData.TYPE_EXCESSIVE_SPEED:
	    				case TelematicControlData.TYPE_HARD_START:
	    				case TelematicControlData.TYPE_HARD_STOP:
	        	    		return (value.getSpeed() == null) ? "---" : value.getSpeed().toString();
	    				default:
	        	    		return Util.formatDate(value.getDate());
    	    		}
    	    	}
    	    	if(field.getName().equals("tabla1")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla2.jpg";
    	    	}
    	    	if(field.getName().equals("tabla2")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla4.jpg";
    	    	}
    	    	break;
    		case 4:
    	    	if(field.getName().equals("text")) {
    	    		switch(value.getType().intValue()) {
	    				case TelematicControlData.TYPE_HARD_START:
	    				case TelematicControlData.TYPE_HARD_STOP:
	    				case TelematicControlData.TYPE_EXCESSIVE_SPEED:
	           	    		return messages.getText("driver.telematic.date");
	    				case TelematicControlData.TYPE_FREE_TIME:
	        	    		return messages.getText("driver.telematiccontrol.drivetime");
	    				case TelematicControlData.TYPE_WORK_HOURS:
	        	    		return messages.getText("driver.telematiccontrol.out");
	    				case TelematicControlData.TYPE_INSERTICIAL_REPORT:
	        	    		return messages.getText("driver.telematiccontrol.endhourworkday");
	    				case TelematicControlData.TYPE_24HOOUR_WITHOUT_REPORT:
	        	    		return messages.getText("driver.telematiccontrol.lastreport");
	    				case TelematicControlData.TYPE_WEEK_WORK:
	        	    		return messages.getText("driver.telematiccontrol.firstday");
    	    		}
    	    	}
    	    	if(field.getName().equals("value")) {
    	    		switch(value.getType().intValue()) {
	    				case TelematicControlData.TYPE_HARD_START:
	    				case TelematicControlData.TYPE_HARD_STOP:
	    				case TelematicControlData.TYPE_EXCESSIVE_SPEED:
	        	    		return Util.formatDate(value.getDate());
	    				case TelematicControlData.TYPE_FREE_TIME:
	    				case TelematicControlData.TYPE_WORK_HOURS:
	    				case TelematicControlData.TYPE_INSERTICIAL_REPORT:
	        	    		return (value.getTime() == null) ? "---" : value.getTime();
	    				case TelematicControlData.TYPE_24HOOUR_WITHOUT_REPORT:
	    				case TelematicControlData.TYPE_WEEK_WORK:
	        	    		return Util.formatDate(value.getDate2());
    	    		}
    	    	}
    	    	if(field.getName().equals("tabla1")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla1.jpg";
    	    	}
    	    	if(field.getName().equals("tabla2")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla3.jpg";
    	    	}
    	    	break;
    		case 5:
    	    	if(field.getName().equals("text")) {
    	    		switch(value.getType().intValue()) {
	    				case TelematicControlData.TYPE_HARD_START:
	    				case TelematicControlData.TYPE_HARD_STOP:
	    				case TelematicControlData.TYPE_EXCESSIVE_SPEED:
	        	    		return messages.getText("driver.telematic.time");
	    				case TelematicControlData.TYPE_FREE_TIME:
	        	    		return messages.getText("driver.telematiccontrol.tolerancetime");
	    				case TelematicControlData.TYPE_WORK_HOURS:
	        	    		return messages.getText("driver.telematiccontrol.in");
	    				case TelematicControlData.TYPE_INSERTICIAL_REPORT:
	        	    		return messages.getText("driver.telematiccontrol.starthourworkday");
	    				case TelematicControlData.TYPE_24HOOUR_WITHOUT_REPORT:
	        	    		return messages.getText("driver.telematic.garage");
	    				case TelematicControlData.TYPE_WEEK_WORK:
	        	    		return messages.getText("driver.telematiccontrol.lastday");
    	    		}
    	    	}
    	    	if(field.getName().equals("value")) {
    	    		switch(value.getType().intValue()) {
	    				case TelematicControlData.TYPE_HARD_START:
	    				case TelematicControlData.TYPE_HARD_STOP:
	    				case TelematicControlData.TYPE_EXCESSIVE_SPEED:
	        	    		return (value.getTime() == null) ? "---" : value.getTime();
	    				case TelematicControlData.TYPE_FREE_TIME:
	    				case TelematicControlData.TYPE_WORK_HOURS:
	    				case TelematicControlData.TYPE_INSERTICIAL_REPORT:
	        	    		return (value.getDuration() == null) ? "---" : value.getDuration();
	    				case TelematicControlData.TYPE_24HOOUR_WITHOUT_REPORT:
	        	    		return (value.getGarage() == null) ? "---" : value.getGarage();
	    				case TelematicControlData.TYPE_WEEK_WORK:
	        	    		return Util.formatDate(value.getDate3());
    	    		}
    	    	}
    	    	if(field.getName().equals("tabla1")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla2.jpg";
    	    	}
    	    	if(field.getName().equals("tabla2")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla4.jpg";
    	    	}
    	    	break;
    		case 6:
    	    	if(field.getName().equals("text")) {
    	    		switch(value.getType().intValue()) {
	    				case TelematicControlData.TYPE_HARD_START:
	    				case TelematicControlData.TYPE_HARD_STOP:
	        	    		return messages.getText("driver.telematic.garage");
	    				case TelematicControlData.TYPE_EXCESSIVE_SPEED:
	        	    		return messages.getText("driver.telematic.duration");
	    				case TelematicControlData.TYPE_FREE_TIME:
	        	    		return messages.getText("driver.telematiccontrol.freetimevalue");
	    				case TelematicControlData.TYPE_WORK_HOURS:
	        	    		return messages.getText("driver.telematic.exceedtime");
	    				case TelematicControlData.TYPE_INSERTICIAL_REPORT:
	        	    		return messages.getText("driver.telematiccontrol.interstitialtime");
	    				case TelematicControlData.TYPE_24HOOUR_WITHOUT_REPORT:
	        	    		return messages.getText("driver.telematic.occurrences");
	    				case TelematicControlData.TYPE_WEEK_WORK:
	        	    		return messages.getText("driver.telematiccontrol.exceeddays");
    	    		}
    	    	}
    	    	if(field.getName().equals("value")) {
    	    		switch(value.getType().intValue()) {
	    				case TelematicControlData.TYPE_HARD_START:
	    				case TelematicControlData.TYPE_HARD_STOP:
	        	    		return (value.getGarage() == null) ? "---" : value.getGarage();
	    				case TelematicControlData.TYPE_EXCESSIVE_SPEED:
	        	    		return (value.getDuration() == null) ? "---" : value.getDuration();
	    				case TelematicControlData.TYPE_FREE_TIME:
	    				case TelematicControlData.TYPE_WORK_HOURS:
	    				case TelematicControlData.TYPE_INSERTICIAL_REPORT:
	        	    		return (value.getTotalDuration() == null) ? "---" : value.getTotalDuration();
	    				case TelematicControlData.TYPE_24HOOUR_WITHOUT_REPORT:
	        	    		return (value.getOccurrences() == null) ? "---" : value.getOccurrences().toString();
	    				case TelematicControlData.TYPE_WEEK_WORK:
	        	    		return (value.getLimitExceededRain() == null) ? "---" : value.getLimitExceededRain().toString();
    	    		}
    	    	}
    	    	if(field.getName().equals("tabla1")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla1.jpg";
    	    	}
    	    	if(field.getName().equals("tabla2")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla3.jpg";
    	    	}
    	    	break;
    		case 7:
    	    	if(field.getName().equals("text")) {
    	    		switch(value.getType().intValue()) {
	    				case TelematicControlData.TYPE_HARD_START:
	    				case TelematicControlData.TYPE_HARD_STOP:
	        	    		return messages.getText("driver.telematic.occurrences");
	    				case TelematicControlData.TYPE_FREE_TIME:
	        	    		return messages.getText("driver.telematic.stops");
	    				case TelematicControlData.TYPE_EXCESSIVE_SPEED:
	    				case TelematicControlData.TYPE_WORK_HOURS:
	    				case TelematicControlData.TYPE_INSERTICIAL_REPORT:
	    				case TelematicControlData.TYPE_WEEK_WORK:
	        	    		return messages.getText("driver.telematic.garage");
    	    		}
    	    	}
    	    	if(field.getName().equals("value")) {
    	    		switch(value.getType().intValue()) {
	    				case TelematicControlData.TYPE_HARD_START:
	    				case TelematicControlData.TYPE_HARD_STOP:
	        	    		return (value.getOccurrences() == null) ? "---" : value.getOccurrences().toString();
	    				case TelematicControlData.TYPE_FREE_TIME:
	        	    		return (value.getLimitExceededRain() == null) ? "---" : value.getLimitExceededRain().toString();
	    				case TelematicControlData.TYPE_EXCESSIVE_SPEED:
	    				case TelematicControlData.TYPE_WORK_HOURS:
	    				case TelematicControlData.TYPE_INSERTICIAL_REPORT:
	    				case TelematicControlData.TYPE_WEEK_WORK:
	        	    		return (value.getGarage() == null) ? "---" : value.getGarage();
    	    		}
    	    	}
    	    	if(field.getName().equals("tabla1")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla2.jpg";
    	    	}
    	    	if(field.getName().equals("tabla2")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla4.jpg";
    	    	}
    	    	break;
    		case 8:
    	    	if(field.getName().equals("text")) {
    	    		switch(value.getType().intValue()) {
	    				case TelematicControlData.TYPE_EXCESSIVE_SPEED:
	    				case TelematicControlData.TYPE_INSERTICIAL_REPORT:
	    				case TelematicControlData.TYPE_WEEK_WORK:
	        	    		return messages.getText("driver.telematic.occurrences");
	    				case TelematicControlData.TYPE_FREE_TIME:
	        	    		return messages.getText("driver.telematic.garage");
    	    		}
    	    	}
    	    	if(field.getName().equals("value")) {
    	    		switch(value.getType().intValue()) {
	    				case TelematicControlData.TYPE_EXCESSIVE_SPEED:
	    				case TelematicControlData.TYPE_INSERTICIAL_REPORT:
	    				case TelematicControlData.TYPE_WEEK_WORK:
	        	    		return (value.getOccurrences() == null) ? "---" : value.getOccurrences().toString();
	    				case TelematicControlData.TYPE_FREE_TIME:
	        	    		return (value.getGarage() == null) ? "---" : value.getGarage();
    	    		}
    	    	}
    	    	if(field.getName().equals("tabla1")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla1.jpg";
    	    	}
    	    	if(field.getName().equals("tabla2")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla3.jpg";
    	    	}
    	    	break;
    		case 9:
    			
    			if(value.getType().intValue()==TelematicControlData.TYPE_EXCESSIVE_SPEED){
    				if(field.getName().equals("text")) {
           	    		return "Chuva";
        	    	}	
    				if(field.getName().equals("value")) {
        	    		return (value.getLimitExceededRain() == null) ? "" : value.getLimitExceededRain().equals(new Integer(1))?messages.getText("generic.messges.yes"):messages.getText("generic.messges.no");
        	    	}
    			}
    			
    	    	if(field.getName().equals("text")) {
       	    		return messages.getText("driver.telematic.occurrences");
    	    	}
    	    	if(field.getName().equals("value")) {
    	    		return (value.getOccurrences() == null) ? "" : value.getOccurrences().toString();
    	    	}
    	    	if(field.getName().equals("tabla1")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla2.jpg";
    	    	}
    	    	if(field.getName().equals("tabla2")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla4.jpg";
    	    	}
    	    	break;
    	}
    	return null;
    }
    
	public Object getFieldValue24(JRField field) throws JRException {
    	switch(index) {
			case 0:
    	    	if(field.getName().equals("text")) {
    	    		return "Veiculo associado";
    	    	}
    	    	if(field.getName().equals("value")) {
    	    		return value.getVehicle()!=null?value.getVehicle().getMatricula():"---";
    	    	}
    	    	if(field.getName().equals("tabla1")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla2.jpg";
    	    	}
    	    	if(field.getName().equals("tabla2")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla4.jpg";
    	    	}
			case 1:
    	    	if(field.getName().equals("text")) {
    	    		return "Pontos";
    	    	}
    	    	if(field.getName().equals("value")) {
    	    		return getDPAPointLabel(value.getPoint());
    	    	}
    	    	if(field.getName().equals("tabla1")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla1.jpg";
    	    	}
    	    	if(field.getName().equals("tabla2")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla3.jpg";
    	    	}
			case 2:
    	    	if(field.getName().equals("text")) {
    	    		switch(value.getType().intValue()) {
	    				case TelematicControlData.TYPE_EXCESSIVE_SPEED:
	        	    		return messages.getText("driver.telematic.speed");
	    				default:
	           	    		return messages.getText("driver.telematic.date");
    	    		}
    	    	}
    	    	if(field.getName().equals("value")) {
    	    		switch(value.getType().intValue()) {
	    				case TelematicControlData.TYPE_EXCESSIVE_SPEED:
	        	    		return (value.getSpeed() == null) ? "---" : value.getSpeed().toString();
	    				default:
	        	    		return Util.formatDate(value.getDate());
    	    		}
    	    	}
    	    	if(field.getName().equals("tabla1")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla2.jpg";
    	    	}
    	    	if(field.getName().equals("tabla2")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla4.jpg";
    	    	}
    	    	break;
    		case 3:
    	    	if(field.getName().equals("text")) {
    	    		switch(value.getType().intValue()) {
	    				case TelematicControlData.TYPE_EXCESSIVE_SPEED:
	           	    		return messages.getText("driver.telematic.date");
	    				case TelematicControlData.TYPE_FREE_TIME:
	        	    		return messages.getText("driver.telematiccontrol.drivetime");
	    				case TelematicControlData.TYPE_WORK_HOURS:
	        	    		return messages.getText("driver.telematiccontrol.out");
	    				case TelematicControlData.TYPE_INSERTICIAL_REPORT:
	        	    		return messages.getText("driver.telematiccontrol.endhourworkday");
	    				case TelematicControlData.TYPE_24HOOUR_WITHOUT_REPORT:
	        	    		return messages.getText("driver.telematiccontrol.lastreport");
	    				case TelematicControlData.TYPE_WEEK_WORK:
	        	    		return messages.getText("driver.telematiccontrol.firstday");
    	    		}
    	    	}
    	    	if(field.getName().equals("value")) {
    	    		switch(value.getType().intValue()) {
	    				case TelematicControlData.TYPE_EXCESSIVE_SPEED:
	        	    		return Util.formatDate(value.getDate());
	    				case TelematicControlData.TYPE_FREE_TIME:
	    				case TelematicControlData.TYPE_WORK_HOURS:
	    				case TelematicControlData.TYPE_INSERTICIAL_REPORT:
	        	    		return (value.getTime() == null) ? "---" : value.getTime();
	    				case TelematicControlData.TYPE_24HOOUR_WITHOUT_REPORT:
	    				case TelematicControlData.TYPE_WEEK_WORK:
	        	    		return Util.formatDate(value.getDate2());
    	    		}
    	    	}
    	    	if(field.getName().equals("tabla1")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla1.jpg";
    	    	}
    	    	if(field.getName().equals("tabla2")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla3.jpg";
    	    	}
    	    	break;
    		case 4:
    	    	if(field.getName().equals("text")) {
    	    		switch(value.getType().intValue()) {
	    				case TelematicControlData.TYPE_EXCESSIVE_SPEED:
	        	    		return messages.getText("driver.telematic.time");
	    				case TelematicControlData.TYPE_FREE_TIME:
	        	    		return messages.getText("driver.telematiccontrol.tolerancetime");
	    				case TelematicControlData.TYPE_WORK_HOURS:
	        	    		return messages.getText("driver.telematiccontrol.in");
	    				case TelematicControlData.TYPE_INSERTICIAL_REPORT:
	        	    		return messages.getText("driver.telematiccontrol.starthourworkday");
	    				case TelematicControlData.TYPE_24HOOUR_WITHOUT_REPORT:
	        	    		return messages.getText("driver.telematic.garage");
	    				case TelematicControlData.TYPE_WEEK_WORK:
	        	    		return messages.getText("driver.telematiccontrol.lastday");
    	    		}
    	    	}
    	    	if(field.getName().equals("value")) {
    	    		switch(value.getType().intValue()) {
	    				case TelematicControlData.TYPE_EXCESSIVE_SPEED:
	        	    		return (value.getTime() == null) ? "---" : value.getTime();
	    				case TelematicControlData.TYPE_FREE_TIME:
	    				case TelematicControlData.TYPE_WORK_HOURS:
	    				case TelematicControlData.TYPE_INSERTICIAL_REPORT:
	        	    		return (value.getDuration() == null) ? "---" : value.getDuration();
	    				case TelematicControlData.TYPE_24HOOUR_WITHOUT_REPORT:
	        	    		return (value.getGarage() == null) ? "---" : value.getGarage();
	    				case TelematicControlData.TYPE_WEEK_WORK:
	        	    		return Util.formatDate(value.getDate3());
    	    		}
    	    	}
    	    	if(field.getName().equals("tabla1")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla2.jpg";
    	    	}
    	    	if(field.getName().equals("tabla2")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla4.jpg";
    	    	}
    	    	break;
    		case 5:
    	    	if(field.getName().equals("text")) {
    	    		switch(value.getType().intValue()) {
	    				case TelematicControlData.TYPE_EXCESSIVE_SPEED:
	        	    		return messages.getText("driver.telematic.duration");
	    				case TelematicControlData.TYPE_FREE_TIME:
	        	    		return messages.getText("driver.telematiccontrol.freetimevalue");
	    				case TelematicControlData.TYPE_WORK_HOURS:
	        	    		return messages.getText("driver.telematic.exceedtime");
	    				case TelematicControlData.TYPE_INSERTICIAL_REPORT:
	        	    		return messages.getText("driver.telematiccontrol.interstitialtime");
	    				case TelematicControlData.TYPE_24HOOUR_WITHOUT_REPORT:
	        	    		return messages.getText("driver.telematic.occurrences");
	    				case TelematicControlData.TYPE_WEEK_WORK:
	        	    		return messages.getText("driver.telematiccontrol.exceeddays");
    	    		}
    	    	}
    	    	if(field.getName().equals("value")) {
    	    		switch(value.getType().intValue()) {
	    				case TelematicControlData.TYPE_EXCESSIVE_SPEED:
	        	    		return (value.getDuration() == null) ? "---" : value.getDuration();
	    				case TelematicControlData.TYPE_FREE_TIME:
	    				case TelematicControlData.TYPE_WORK_HOURS:
	    				case TelematicControlData.TYPE_INSERTICIAL_REPORT:
	        	    		return (value.getTotalDuration() == null) ? "---" : value.getTotalDuration();
	    				case TelematicControlData.TYPE_24HOOUR_WITHOUT_REPORT:
	        	    		return (value.getOccurrences() == null) ? "---" : value.getOccurrences().toString();
	    				case TelematicControlData.TYPE_WEEK_WORK:
	        	    		return (value.getLimitExceededRain() == null) ? "---" : value.getLimitExceededRain().toString();
    	    		}
    	    	}
    	    	if(field.getName().equals("tabla1")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla1.jpg";
    	    	}
    	    	if(field.getName().equals("tabla2")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla3.jpg";
    	    	}
    	    	break;
    		case 6:
    	    	if(field.getName().equals("text")) {
    	    		switch(value.getType().intValue()) {
	    				case TelematicControlData.TYPE_FREE_TIME:
	        	    		return messages.getText("driver.telematic.stops");
	    				case TelematicControlData.TYPE_EXCESSIVE_SPEED:
	    				case TelematicControlData.TYPE_WORK_HOURS:
	    				case TelematicControlData.TYPE_INSERTICIAL_REPORT:
	    				case TelematicControlData.TYPE_WEEK_WORK:
	        	    		return messages.getText("driver.telematic.garage");
    	    		}
    	    	}
    	    	if(field.getName().equals("value")) {
    	    		switch(value.getType().intValue()) {
	    				case TelematicControlData.TYPE_FREE_TIME:
	        	    		return (value.getLimitExceededRain() == null) ? "---" : value.getLimitExceededRain().toString();
	    				case TelematicControlData.TYPE_EXCESSIVE_SPEED:
	    				case TelematicControlData.TYPE_WORK_HOURS:
	    				case TelematicControlData.TYPE_INSERTICIAL_REPORT:
	    				case TelematicControlData.TYPE_WEEK_WORK:
	        	    		return (value.getGarage() == null) ? "---" : value.getGarage();
    	    		}
    	    	}
    	    	if(field.getName().equals("tabla1")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla2.jpg";
    	    	}
    	    	if(field.getName().equals("tabla2")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla4.jpg";
    	    	}
    	    	break;
    		case 7:
    	    	if(field.getName().equals("text")) {
    	    		switch(value.getType().intValue()) {
	    				case TelematicControlData.TYPE_EXCESSIVE_SPEED:
	    				case TelematicControlData.TYPE_INSERTICIAL_REPORT:
	    				case TelematicControlData.TYPE_WEEK_WORK:
	        	    		return messages.getText("driver.telematic.occurrences");
	    				case TelematicControlData.TYPE_FREE_TIME:
	        	    		return messages.getText("driver.telematic.garage");
    	    		}
    	    	}
    	    	if(field.getName().equals("value")) {
    	    		switch(value.getType().intValue()) {
	    				case TelematicControlData.TYPE_EXCESSIVE_SPEED:
	    				case TelematicControlData.TYPE_INSERTICIAL_REPORT:
	    				case TelematicControlData.TYPE_WEEK_WORK:
	        	    		return (value.getOccurrences() == null) ? "---" : value.getOccurrences().toString();
	    				case TelematicControlData.TYPE_FREE_TIME:
	        	    		return (value.getGarage() == null) ? "---" : value.getGarage();
    	    		}
    	    	}
    	    	if(field.getName().equals("tabla1")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla1.jpg";
    	    	}
    	    	if(field.getName().equals("tabla2")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla3.jpg";
    	    	}
    	    	break;
    		case 8:
    	    	if(field.getName().equals("text")) {
       	    		return messages.getText("driver.telematic.occurrences");
    	    	}
    	    	if(field.getName().equals("value")) {
    	    		return (value.getOccurrences() == null) ? "" : value.getOccurrences().toString();
    	    	}
    	    	if(field.getName().equals("tabla1")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla2.jpg";
    	    	}
    	    	if(field.getName().equals("tabla2")) {
    	    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla4.jpg";
    	    	}
    	    	break;
    	}
    	return null;
    }

	public static final String DPA_POINT_Gravissima_LABEL = "7 - Gravissima";
	public static final String DPA_POINT_Grave_LABEL = "5 - Grave";
	public static final String DPA_POINT_Media_LABEL = "3 - Media";
	public static final String DPA_POINT_Leve_LABEL = "1 - Leve";

	public static final String DPA_POINT_Gravissima_VALUE = "7";
	public static final String DPA_POINT_Grave_VALUE = "5";
	public static final String DPA_POINT_Media_VALUE = "3";
	public static final String DPA_POINT_Leve_VALUE = "1";

    private String getDPAPointLabel(String value){
		String label="Sim dados";
		if(value==null || value.trim().length()==0){
			return label;
		}
	
		int val=Integer.valueOf(value);
		switch(val){
			case 7 :
				label=DPA_POINT_Gravissima_LABEL;
				break;
			case 5:
				label=DPA_POINT_Grave_LABEL;
				break;
			case 3:
				label=DPA_POINT_Media_LABEL;
				break;
			case 1:
				label=DPA_POINT_Leve_LABEL;
				break;
		}

		return label;
	}

}
