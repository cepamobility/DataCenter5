/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.activity.report;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import communication.language.Text;

public class ReportCommentaryDriveLogData extends AbstractTableModel 
	implements Serializable {

    static final long serialVersionUID = 1L;
    
    private int total;
    private CommentaryDriveLogData[] attributes;
    private boolean color = true;
    private Text messages;

    private int order1;
    private int order2;
    private int order3;
    private int order4;

    public ReportCommentaryDriveLogData() {
        total = 0;
    }
    
    public ReportCommentaryDriveLogData(Collection details, Text messages,int order1,int order2,int order3,int order4) {
        total = details.size();
        Collections.sort((List) details);
        attributes = (CommentaryDriveLogData[])details.toArray(new CommentaryDriveLogData[0]);
        this.messages = messages;
        this.order1 = order1;
        this.order2 = order2;
        this.order3 = order3;
        this.order4 = order4;
    }

    public int getRowCount() {
        return total;
    }

    public int getColumnCount() {
        return 15;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                color = !color;
                return new Boolean(color);
            case 1:
            	if (attributes[rowIndex].getDivOrg1Id() == null){
            		return new Integer(0);
            	}
                return attributes[rowIndex].getDivOrg1Id();
                
            case 2:
            	if (attributes[rowIndex].getDivOrg1Name() == null){
            		return "";
            	}
                return (order1 == 3) ? messages.getText(attributes[rowIndex].getDivOrg1Name()) : 
                	attributes[rowIndex].getDivOrg1Name();
                
            case 3:
            	if (attributes[rowIndex].getDivOrg2Id() == null){
            		return new Integer(0);
            	}
                return attributes[rowIndex].getDivOrg2Id();
                
            case 4:
            	if (attributes[rowIndex].getDivOrg2Name() == null){
            		return "";
            	}
                return (order2 == 3) ? messages.getText(attributes[rowIndex].getDivOrg2Name()) : 
                	attributes[rowIndex].getDivOrg2Name();
            case 5:
            	if (attributes[rowIndex].getDivOrg3Id() == null){
            		return new Integer(0);
            	}
                return attributes[rowIndex].getDivOrg3Id();
            case 6:
            	if (attributes[rowIndex].getDivOrg3Name() == null){
            		return "";
            	}
                return (order3 == 3) ? messages.getText(attributes[rowIndex].getDivOrg3Name()) : attributes[rowIndex].getDivOrg3Name();
            case 7:
            	if (attributes[rowIndex].getDivOrg4Id() == null){
            		return new Integer(0);
            	}
                return attributes[rowIndex].getDivOrg4Id();
            case 8:
            	if (attributes[rowIndex].getDivOrg4Name() == null){
            		return "";
            	}
                return (order4 == 3) ? messages.getText(attributes[rowIndex].getDivOrg4Name()) : attributes[rowIndex].getDivOrg4Name();
            case 9:
            	if (attributes[rowIndex].getActivityDate() == null){
            		return "";
            	}
                return attributes[rowIndex].getActivityDate();
                
            case 10:
            	if (attributes[rowIndex].getDriverFirstName() == null) {
            		return "";
            	}
                return attributes[rowIndex].getDriverFirstName();
            case 11:
            	if (attributes[rowIndex].getDriverLastName() == null){
            		return "";
            	}
                return attributes[rowIndex].getDriverLastName();
            case 12:
            	if (attributes[rowIndex].getDriverBirth() == null){
            		return "";
            	}
                return attributes[rowIndex].getDriverBirth();
            case 13:
            	if (attributes[rowIndex].getDriverDate() == null){
            		return "";
            	}
                return attributes[rowIndex].getDriverDate();
            case 14:
            	if (attributes[rowIndex].getDriverUser() == null){
            		return "";
            	}
                return attributes[rowIndex].getDriverUser();
        }
        return null;
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "color";
            case 1:
                return "div1Id";
            case 2:
                return "div1Name";
            case 3:
                return "div2Id";
            case 4:
                return "div2Name";
            case 5:
                return "div3Id";
            case 6:
                return "div3Name";
            case 7:
                return "div4Id";
            case 8:
                return "div4Name";
            case 9:
                return "activityDate";
            case 10:
                return "driverfirstname";
            case 11:
                return "driverlastname";
            case 12:
                return "driverBirth";
            case 13:
                return "driverDate";
            case 14:
                return "driverUser";
        }
        return null;
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }
    
    
}
