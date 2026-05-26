package communication.activity.report;

import java.io.Serializable;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import communication.administration.user.UserSessionData;
import communication.language.Text;
import communication.util.ListResult;

public class AssessmentResultData extends AbstractTableModel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total;
    private AssessmentResultDataType[] attributes;
    private boolean color = true;
    private Text messages;
    
    //private UserSessionData userSessionData;
    
    //private int[][] ppalDivOrgs = new int[3][2];
    
    public AssessmentResultData() {
        total = 0;
        attributes = new AssessmentResultDataType[0]; 
    }
    
    public AssessmentResultData(ListResult reportDrivers,UserSessionData usr) {   
        
    	if (reportDrivers == null){
        	total = 0;
        	attributes = new AssessmentResultDataType[0];
        }else{
        	total = reportDrivers.getTotal();
        	Collection elements = reportDrivers.getElements();
        	attributes = (AssessmentResultDataType[])elements.toArray(new AssessmentResultDataType[0]);
        }
        // userSessionData = usr;
        //ppalDivOrgs = userSessionData.getFilter().getPrincipalDivOrgs();
    }
    
    public AssessmentResultData(AssessmentResultDataType[] elements, int total, UserSessionData usr){
    	if (elements == null){
        	total = 0;
        	attributes = new AssessmentResultDataType[0];
        }else{
        	this.total = total;
        	attributes = elements;
        }
        //userSessionData = usr;
        //ppalDivOrgs = userSessionData.getFilter().getPrincipalDivOrgs();
    }

    public int getRowCount() {
    	return attributes.length;
    }

    public int getColumnCount() {
        return 12;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
    	
    	
        switch(columnIndex) {
            case 0:
            	if (attributes[rowIndex].getDriver().getId() == null){
            		return new Integer(0);
            	}
                return String.valueOf(attributes[rowIndex].getDriver().getId());
            case 1:
            	if (attributes[rowIndex].getDriver().getFirstName() == null || 
            			attributes[rowIndex].getDriver().getLastName() == null){
            		return "";
            	}
                return attributes[rowIndex].getDriver().getFirstName()+" "+attributes[rowIndex].getDriver().getLastName();
            case 2:
            	// Unidad
				return attributes[rowIndex].getDriver().getDivOrgItem3(); // getDivOrg(ppalDivOrgs[2][0],ppalDivOrgs[2][1]);
            case 3:
            	// Division            	
				return attributes[rowIndex].getDriver().getDivOrgItem1(); //.getDivOrg(ppalDivOrgs[0][0],ppalDivOrgs[0][1]);
            case 4:
				return attributes[rowIndex].getDriver().getDivOrgItem2(); //getDivOrg(ppalDivOrgs[1][0],ppalDivOrgs[1][1]);
//            case 10:
//				return attributes[rowIndex].getDriver().getDivOrg(ppalDivOrgs[3][0],ppalDivOrgs[3][1]);
            case 5: 
                if (attributes[rowIndex].getDriver().getCountryName() == null){
                	return "";
                }
            	return messages.getText(attributes[rowIndex].getDriver().getCountryName());
            case 6:
                color = !color;
                return new Boolean(color);
            case 7:
                return new Integer(total);
            case 8:
            	if (attributes[rowIndex].getPsiResult() != null){
            		return attributes[rowIndex].getPsiResult();
            	}else{
            		return "---";
            	}
            case 9:
            	if (attributes[rowIndex].getPsiNote() != null){
            		return messages.getText("generic.messages.".concat(
            				attributes[rowIndex].getPsiNote()));
            	}else{
            		return "---";
            	}
            case 10:
            	if (attributes[rowIndex].getTestResult() != null){
            		return attributes[rowIndex].getTestResult();
            	}else{
            		return "---";
            	}
            case 11:
            	if (attributes[rowIndex].getTestNote() != null){
            		return messages.getText("generic.messages.".concat(
            				attributes[rowIndex].getTestNote()));
            	}else{
            		return "---";
            	}
            	
            
        }
        return null;
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "idDriver";
            case 1:
                return "name";
            case 2:
                return "unit";
            case 3:
                return "division";
            case 4:
                return "subdivision";
            case 5:
                return "country";                
            case 6:
                return "color";
            case 7:
                return "total";             
            case 8:
            	return "psiResult";
            case 9:
            	return "psiNote";
            case 10:
            	return "testResult";
            case 11:
            	return "testNote";
            	
        }
        return null;
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
