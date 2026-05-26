package communication.administration.dpa;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import presentation.translator.web.util.Util;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import communication.activity.tables.CheckListData;
import communication.activity.tables.PointData;
import communication.administration.dpa.UnconformityItemDPAData;
import communication.alert.AlertConstants;
import communication.language.Text;
import communication.vehicle.tables.VehicleCheckData;

public class UnconformityReportDPADataSource implements JRDataSource {

    private CorrectiveActionDPAData[] items;
    private int index = -1;
	private Text messages;
    
    public UnconformityReportDPADataSource(Collection values, Text messages) {
    	this.messages = messages;
    	items = new CorrectiveActionDPAData[values.size()];
    	Iterator it = values.iterator();
    	int in = 0;
    	while(it.hasNext()) {
   			items[in] = (CorrectiveActionDPAData)it.next();
    		in++;
    	}
    }
    
    public boolean next() throws JRException {
        if(index+1 < items.length) {
        	index++;
        	return true;
        }
        return false;
    }

    public Object getFieldValue(JRField field) throws JRException {
    	if(field.getName().equals("actioncomment")) {
    		return items[index].getComment();
    	}
    	if(field.getName().equals("actionday")) {
    		return messages.getText("generic.messages.date") +" "+messages.getText("generic.dpa.correctiveaction.title")+ ": " + formatDate(items[index].getDate());
    	}
    	if(field.getName().equals("pictures")) {
    		return new  UnconformityReportPictureDPADataSource(items[index].getImages(), messages);
    	}
    	if(field.getName().equals("items")) {
    		return new  UnconformityReportItemDPADataSource(items[index].getItems(), messages,items[index].getDate());
    	}
    	if(field.getName().startsWith("tabla1")) {
    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla1.jpg";
    	}
    	if(field.getName().startsWith("tabla2")) {
   			return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla3.jpg";
    	}
    	if(field.getName().startsWith("tabla3")) {
    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla5.jpg";
    	}
    	return null;
    }


    private String formatDate(Date date) {
        if(date == null) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);
    }
}
