package presentation.report.activity;

import java.awt.Color;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;

import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.activity.tables.CheckListData;
import communication.activity.tables.ItemData;
import communication.activity.tables.PointData;
import communication.alert.AlertConstants;
import communication.language.Text;

public class ActivityDataSource  implements JRDataSource,Serializable {

    private static final long serialVersionUID = 1L;
    
    private Object[][] values = new Object[8][4];
    private String obs2 = "";
    private int resultIndex = 8;
    private int index = -1;
    
    public ActivityDataSource(CEPAActivityScheduleRegistryData registryData,Text messages) {
    	values[0] = new Object[]{new Integer(1),"Postura",new Integer(45),new Integer(70)};
    	values[1] = new Object[]{new Integer(2),"Mecanica de brazos",new Integer(75),new Integer(80)};
    	values[2] = new Object[]{new Integer(3),"Uso de caja de cambios",new Integer(45),new Integer(30)};
    	values[3] = new Object[]{new Integer(4),"Físca aplicada al conductor",new Integer(15),new Integer(24)};
    	values[4] = new Object[]{new Integer(5),"Tiempo de reacción",new Integer(55),new Integer(91)};
    	values[5] = new Object[]{new Integer(6),"Aceleración",new Integer(65),new Integer(15)};
    	values[6] = new Object[]{new Integer(7),"Ruta de escape",new Integer(45),new Integer(20)};
    	values[7] = new Object[]{new Integer(8),"Skidcar",new Integer(85),new Integer(50)};
    }
    
    public int getRowCount() {
        return resultIndex;
    }

    public int getColumnCount() {
        return 6;
    }

	@Override
	public Object getFieldValue(JRField field) throws JRException {
    	if(field.getName().equals("point")) {
            return values[index][0];    		
    	}
    	if(field.getName().equals("pointname")) {
            return values[index][1];    		
    	}
    	if(field.getName().equals("media")) {
            return values[index][2];    		
    	}
    	if(field.getName().equals("value")) {
            return values[index][3];    		
    	}
    	if(field.getName().equals("obs2")) {
    		return obs2;
    	}
    	if(field.getName().equals("color")) {
    		return values[index][4];
    	}
    	if(field.getName().equals("tabla2")) {
    		if(index % 2 == 0) {
    			return AlertConstants.TEMP_DIRECTORY_REPORTER_JJ+"images/tabla1.jpg";
    		}else {
    			return AlertConstants.TEMP_DIRECTORY_REPORTER_JJ+"images/tabla3.jpg";
    		}
    	}
    	if(field.getName().equals("tabla1")) {
    		if(index % 2 == 0) {
    			return AlertConstants.TEMP_DIRECTORY_REPORTER_JJ+"images/tabla2.jpg";
    		}else {
    			return AlertConstants.TEMP_DIRECTORY_REPORTER_JJ+"images/tabla4.jpg";
    		}
    	}
		return null;
	}

	@Override
	public boolean next() throws JRException {
		if(index < resultIndex-1) {
			index++;
			return true;
		}
		return false;
	}

}

