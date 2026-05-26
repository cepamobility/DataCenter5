package communication.util;


import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import communication.language.Text;

public class EmptyDataSource implements JRDataSource {

    private String[][] values;
    private int index = -1;
	
    
    public EmptyDataSource() {
    	values = new String[][]{};
    }
    
    public boolean next() throws JRException {
        if(index+1 <= values.length) {
        	index++;
        	return true;
        }
        return false;
    }

    public Object getFieldValue(JRField field) throws JRException {
    	 
    	return null;
    }


}
