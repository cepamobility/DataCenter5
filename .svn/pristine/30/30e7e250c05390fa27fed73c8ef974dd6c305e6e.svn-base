package presentation.report.reporterjj;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.activity.tables.CheckListData;
import communication.activity.tables.ItemData;
import communication.activity.tables.PointData;
import communication.language.Text;

public class SelfEvaluationData extends AbstractTableModel 
    implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Object[][] values = new Object[53][7];
    private String[] results = new String[8];
    
    public SelfEvaluationData(CEPAActivityScheduleRegistryData registryData,CheckListData checkList,Text messages) {
    	Collection points = checkList.getPoints();
		Iterator pointIt = points.iterator();

		Collection items = ((PointData)pointIt.next()).getItems();
		Collections.sort((List)items);
		Iterator itemIt = items.iterator();
		double count = 0;
		double total = 0;
		
		int next = 0;
		int resultIndex = 0;
		while(itemIt.hasNext()) {
			count++;
			ItemData item = (ItemData)itemIt.next();
			Integer valueItem = registryData.getValueFromItem(item);
			double value = 0;
			int i = item.getItemOrder().intValue();
			switch(i){
				case 1: case 2: case 8: case 9: case 14: case 15: case 16:
				case 19: case 24: case 27: case 28: case 31: case 38:
				case 39: case 48: case 49:
					value = valueItem.floatValue() * 10.0 / 4.0;						
					break;
				case 3: case 29: case 30: case 40: case 50:
					value = (5.0 - valueItem.floatValue()) * 10.0 / 4.0;						
					break;
				case 4: case 5: case 7: case 18: case 20: case 21: case 32:
				case 41:
					value = (5.0 - valueItem.floatValue()) * 9.0 / 4.0;						
					break;
				case 6: case 10: case 11: case 12: case 17: case 25: case 26:
				case 33: case 34: case 35: case 36: case 37: case 42: case 43:
				case 44: case 45: case 46: case 47: case 51:
					value = valueItem.floatValue() * 9.0 / 4.0;						
					break;
				case 13: case 22: case 23: case 52: case 53:
					value = valueItem.floatValue() * 8.0 / 4.0;						
					break;
			}
			total += value;
			values[i-1][5] = Boolean.TRUE;
			values[i-1][6] = Boolean.TRUE;
			if(i == 1 || i == 8 || i == 14 || i == 19 || i == 24 || i == 27 || i == 38 || i == 48) {
				values[i-1][4] = messages.getText("reportjj.selfevaluation.item"+i);
				values[i][4] = messages.getText("Pontuação");
				next = i + 1;
				values[i-1][5] = Boolean.FALSE;
			}

			values[i-1][0] = String.valueOf(i);
			values[i-1][1] = valueItem;
			values[i-1][2] = String.valueOf(value);
			values[i-1][3] = messages.getText(item.getText());

			if(i == 7  || i == 13 || i == 18 || i == 23 || i == 26 || i == 37 || i == 47 || i == 53 ) {
				double avg = total / count;
				avg = Math.round(avg * Math.pow(10,2)) / Math.pow(10,2);
				total = 0;
				count = 0;
				values[i-1][6] = Boolean.FALSE;
				values[next][4] = String.valueOf(avg);
				results[resultIndex] = String.valueOf(avg);
				resultIndex++;
			}
		}
        total = 0;
    }
    
    public int getRowCount() {
        return 53;
    }

    public int getColumnCount() {
        return 7;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
         
        return values[rowIndex][columnIndex];
    }

    public String getColumnName(int column) {
    	switch(column) {
	    	case 0:
	            return "number";
	    	case 1:
	            return "point";
	    	case 2:
	            return "io";
	    	case 3:
	            return "item";
	    	case 4:
	            return "extra";
	    	case 5:
	            return "color";
	    	case 6:
	            return "color2";
    	}
    	return null;
    }

	public String[] getResults() {
		return results;
	}
	
}
