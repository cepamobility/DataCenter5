package presentation.report.queries;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import business.SystemAccess;

import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.activity.tables.CheckListData;
import communication.activity.tables.ItemData;
import communication.activity.tables.PointData;
import communication.alert.AlertConstants;
import communication.fleet.ApmkData;
import communication.language.Text;
import communication.security.SecurityConstants;

public class ReportAPMKDataSource  implements JRDataSource,Serializable {

    private static final long serialVersionUID = 1L;
    
    private int length = 0;
    private int index = -1;
    
    private String[][] values = null;
    private Text messages;
    private String[] divisions;
    
    public ReportAPMKDataSource(ApmkData data, SystemAccess sys) {
    	messages = sys.getText();
    	values = data.getJasperValues();
    	length = (values.length == 0) ? 0 : values[0].length;
    	divisions = new String[]{"País","Division", "Subdivision"};
	}

	public int getRowCount() {
        return values.length;
    }

	@Override
	public Object getFieldValue(JRField field) throws JRException {
		String name = field.getName();
		if(name.endsWith("Total1") || name.equals("value1") || name.equals("total1")) {
			return getValue(name,3);
		} else if(name.endsWith("Total2") || name.equals("value2") || name.equals("total2")) {
			return getValue(name,2);
		} if(name.endsWith("Total3") || name.equals("value3") || name.equals("total3")) {
			return getValue(name,1);
		} 
		
    	if(name.equals("field")) {
            return values[index][length-3];    		
    	}
    	if(name.equals("vehicles")) {
            return values[index][length-1];    		
    	}
    	if(name.equals("distance")) {
            return values[index][length-2];    		
    	}
    	if(name.equals("color")) {
            return new Boolean(index % 2 == 0);    		
    	}
		return "0";
	}

	private Object getValue(String name, int i) {
		int column = length-(i*3);
		String prefix = "";
    	if(name.startsWith("value") || name.startsWith("total")) {
    		prefix = divisions[3-i]+" ";
    		column -= 3;
    	} else if(name.startsWith("vehicles")) {
    		column -= 1;
    	} else if(name.startsWith("distance")) {
    		column -= 2;
    	} else {
    		return "0";
    	}
        String value = (index >= 0) ? values[index][column] : null;
        value = (value.startsWith("datatype.country")) ? prefix+messages.getText(value) : prefix+value;
        if(name.startsWith("total")) {
        	value += " (total)";
        }
        return value;
	}

	@Override
	public boolean next() throws JRException {
		if(index < values.length-1) {
			index++;
			return true;
		}
		return false;
	}

}

