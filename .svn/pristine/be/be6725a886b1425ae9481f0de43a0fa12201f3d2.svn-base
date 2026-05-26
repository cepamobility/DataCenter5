package presentation.report.reporterjj;

import java.awt.Color;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;

import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.activity.tables.CheckListData;
import communication.activity.tables.ItemData;
import communication.activity.tables.PointData;
import communication.language.Text;

public class CommentaryDriveData extends AbstractTableModel 
    implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Object[][] values = new Object[122][5];
    private String obs1 = "";
    private String obs2 = "";
    private int resultIndex = 0;
    
    public CommentaryDriveData(CEPAActivityScheduleRegistryData registryData,CheckListData checkList,Text messages) {
    
		Iterator pointIt = checkList.getSortPoints();		
		int index = 0;
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
				if(index == 0) {
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

    public Object getValueAt(int rowIndex, int columnIndex) {
    	switch(columnIndex) {
	    	case 0:
	    	case 1:
	    	case 2:
	            return values[rowIndex][columnIndex];
	    	case 3:
	            return values[rowIndex][columnIndex];
	    	case 4:
	            return obs2;
	    	case 5:
	    		return new Boolean(Integer.parseInt((String) values[rowIndex][4]) == 1);
		}
		return null;
    }

    public String getColumnName(int column) {
    	switch(column) {
	    	case 0:
	            return "point";
	    	case 1:
	            return "item";
	    	case 2:
	            return "result";
	    	case 3:
	            return "obs1";
	    	case 4:
	            return "obs2";
	    	case 5:
	            return "color";
    	}
    	return null;
    }

}

