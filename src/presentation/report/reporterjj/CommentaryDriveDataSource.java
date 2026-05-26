package presentation.report.reporterjj;

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
import communication.language.Text;
import communication.security.SecurityConstants;

public class CommentaryDriveDataSource  implements JRDataSource,Serializable {

    private static final long serialVersionUID = 1L;
    
    private Object[][] values = new Object[122][5];
    private String obs2 = "";
    private int resultIndex = 0;
    private int index = -1;
    
    boolean reporterjjmx = false;
    
    public CommentaryDriveDataSource(CEPAActivityScheduleRegistryData registryData,CheckListData checkList,SystemAccess sys) {
    
    	Text messages = sys.getText();
		Iterator pointIt = checkList.getSortPoints();		
		int index = 0;
		reporterjjmx = sys.getUserSessionData().getFilter().getLoginName().equals(SecurityConstants.LOGIN_REPORTERJJ_MX);
		if(reporterjjmx) {
			pointIt.next();
		}
		while(pointIt.hasNext()) {
			PointData point = (PointData)pointIt.next();
			Collection items = point.getItems();
			Collections.sort((List)items);
			Iterator itemIt = items.iterator();
			
			while(itemIt.hasNext()) {
				ItemData item = (ItemData)itemIt.next();
				Integer valueItem = registryData.getValueFromItem(item);
				
				values[resultIndex][0] = messages.getText(point.getText());
				values[resultIndex][1] = messages.getText(item.getText());
				if(index < 4 && !reporterjjmx) {
					values[resultIndex][2] = (valueItem == 1) ? messages.getText("generic.messages.correct") : messages.getText("generic.messages.incorrect");
				}else {
					values[resultIndex][2] = (valueItem == 1) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
				}
				values[resultIndex][3] = registryData.getPointComment(point);
				if(values[resultIndex][3] == null) 
					values[resultIndex][3] = "---";
				values[resultIndex][4] = String.valueOf(valueItem);
						
				resultIndex++;
				index++;
			}
		}
        obs2 = (registryData.getObs() == null) ? "---" : registryData.getObs();
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
    	if(field.getName().equals("itemtext")) {
            return values[index][1];    		
    	}
    	if(field.getName().equals("itemvalue")) {
            return values[index][2];    		
    	}
    	if(field.getName().equals("pointobs")) {
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

