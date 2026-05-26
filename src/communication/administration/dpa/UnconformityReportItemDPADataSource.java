package communication.administration.dpa;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

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

/**
 * 
 * @author fcarriquiry
 *
 */
public class UnconformityReportItemDPADataSource implements JRDataSource {

    private UnconformityItemDPAData[] items;
    private int index = -1;
	private Text messages;
	private Date acDate;
    
    public UnconformityReportItemDPADataSource(Set values, Text messages,Date acDate) {
    	this.messages = messages;
    	this.acDate=acDate;
    	items = new UnconformityItemDPAData[values.size()];
    	Iterator it = values.iterator();
    	int in = 0;
    	while(it.hasNext()) {
   			items[in] = (UnconformityItemDPAData)it.next();
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
    	if(field.getName().equals("item")) {
    		return messages.getText(items[index].getType());
    	}
    	if(field.getName().equals("expirity")) {
    		return messages.getText("generic.dpa.unconformity.termdate")+"  "+formatDate(items[index].getExpirity());
    	}
    	if(field.getName().startsWith("checkImage")) {
    		if(acDate.compareTo(items[index].getExpirity())>0){
    			return AlertConstants.TEMP_DIRECTORY_DPA+"images/unchecked.jpg";
    		}else{
    			return AlertConstants.TEMP_DIRECTORY_DPA+"images/checked.jpg";
    		}
    	}
    	if(field.getName().startsWith("tabla1")) {
    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla1.jpg";
    	}
    	if(field.getName().startsWith("tabla2")) {
   			return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla3.jpg";
    	}
    	if(field.getName().startsWith("tabla3")) {
    		return AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla4.jpg";
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
