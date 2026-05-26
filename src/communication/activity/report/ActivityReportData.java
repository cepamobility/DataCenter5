package communication.activity.report;

import java.io.Serializable;

import javax.swing.table.AbstractTableModel;

import communication.activity.tables.PointCommentData;
import communication.activity.tables.PointItemData;
import communication.language.Text;

public class ActivityReportData extends AbstractTableModel 
	implements Serializable {

    static final long serialVersionUID = 1L;
    
    int total;
    PointItemData[] pointItem;
    PointCommentData[] pointComment;
    
    boolean color = true;
    Text messages;
    
    public ActivityReportData() {
        total = 0;
        pointItem = new PointItemData[0]; 
        pointComment = new PointCommentData[0];
    }
    
    /**
     * 
     * @param reportDrivers
     */
    public ActivityReportData(ActivityReportDataType reportDrivers) {
        total = reportDrivers.getPointItem().size();
        pointItem = (PointItemData[])reportDrivers.getPointItem().toArray(new PointItemData[0]);
        if (reportDrivers.getPointComment() != null) {
        	pointComment = (PointCommentData[])reportDrivers.getPointComment().toArray(new PointCommentData[0]);        
        }else{
        	pointComment = null;
        }        
    }

    public int getRowCount() {
        return total;
    }

    public int getColumnCount() {
        return 4;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
            	return messages.getText(pointItem[rowIndex].getPoint().getTextReport());
            case 1:
                return "* " + messages.getText(pointItem[rowIndex].getItem().getTextReport());                                
            case 2:
                color = !color;
                return new Boolean(color);
            case 3:
            	if (pointComment == null) return "";
            	int row = 0;
            	Integer id = pointItem[rowIndex].getPoint().getId();
            	while (!id.equals(pointComment[row].getPoint().getId()) && row < pointComment.length ) {
            		row ++;
            	}
            	return (row < pointComment.length && pointComment[row].getComment() != null &&
            			! pointComment[row].getComment().equals("") && !pointComment[row].getComment().equals("_") ) ?
            			messages.getText("generic.activity.observations")+ ": " +pointComment[row].getComment() : "";
            
            
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
                return "color";
            case 3:
            	return "pointComment";
        }
        return null;
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }
}
