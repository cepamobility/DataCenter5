/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.cenapra.report;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.table.AbstractTableModel;

import communication.administration.corporation.util.Util;
import communication.cenapra.CENAPRAParticipantListData;
import communication.cenapra.CENAPRAParticipantListFullData;
import communication.language.Text;
import communication.util.ListResult;

public class CheckListResultReportData extends AbstractTableModel 
    implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total;
    private CENAPRAParticipantListData[] attributes;
    private boolean color = true;
    private Text messages;

    private Collection answers;
    
    public CheckListResultReportData() {
        total = 0;
        attributes = new CENAPRAParticipantListData[0]; 
    }
    
    public CheckListResultReportData(ListResult participants,Collection answers, Text messages) {
        this.answers = answers;
	    Iterator it = answers.iterator();
        total = participants.getTotal();
        attributes = (CENAPRAParticipantListData[])participants.getElements().toArray(new CENAPRAParticipantListData[0]);
        this.messages = messages;
    }


    public int getRowCount() {
        return attributes.length;
    }

    public int getColumnCount() {
        return 69;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return String.valueOf(attributes[rowIndex].getId());
            case 1:
                return attributes[rowIndex].getFirstName() +" "+attributes[rowIndex].getLastName();  
            case 2:
                return attributes[rowIndex].getLastName();  
            case 3:
                return messages.getText(attributes[rowIndex].getActivity());
            case 4:
                return Util.formatDate(attributes[rowIndex].getDate(),messages);
            case 5:
                return attributes[rowIndex].getCity();
            case 6:
                return attributes[rowIndex].getInstructor();
            case 7:
                color = !color;
                return new Boolean(color);
            case 8:
                return new Integer(total);
            case 9:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getBirthdate();
            case 10:
                return messages.getText(((CENAPRAParticipantListFullData)attributes[rowIndex]).getPopulation());
            case 11:
                return messages.getText(((CENAPRAParticipantListFullData)attributes[rowIndex]).getOrganization());
            case 12:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getState();
            case 13:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getCity();
            case 14:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getDelegation();
            case 15:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getAddress();
            case 16:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getPostalcode();
            case 17:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getPhone();
            case 18:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getMovile();
            case 19:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getEmail();
            case 20:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isMedical()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 21:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isParamedical()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 22:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isNurse()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 23:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isPolice()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 24:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isVialinspector()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 25:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isTeacher()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 26:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isPrevent()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 27:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getOtherprofession();
            case 28:
                return messages.getText(((CENAPRAParticipantListFullData)attributes[rowIndex]).getEducation());
            case 29:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getOthereducation();
            case 30:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isSecurity()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 31:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isSecuritytrainning()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 32:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getSecuritydesc();
            case 33:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isTeachexperience()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 34:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isFirst()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 35:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isSecond()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 36:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isThird()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 37:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getOtherteach();
            case 38:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getObservations();
            case 39:
                return attributes[rowIndex].getDivOrg2();
            case 40:
                return attributes[rowIndex].getDivOrg1();
            default:
            	int index = columnIndex-41;
            	if(attributes[rowIndex].getActivity().equals("datatype.scheduledactivitytype.cenapramultiplicadores") ||
            			attributes[rowIndex].getActivity().equals("datatype.scheduledactivitytype.cenaprareplicadores") ||
            			attributes[rowIndex].getActivity().equals("datatype.scheduledactivitytype.cenapramultiplicadoresnew")) {
            		if(index > 8) {
            			if(index < 14) {
            				return "-";
            			}else if(index < 23) {
            				index -= 5;
            			}else{
            				return "-";
            			}
            		}
            	}else if(attributes[rowIndex].getActivity().equals("datatype.scheduledactivitytype.cenaprapoblacionmeta2")) {
            		if(index > 11) {
            			if(index < 14) {
            				return "-";
            			}else if(index < 26) {
            				index -= 2;
            			}else{
            				return "-";
            			}
            		}
            	}else if(attributes[rowIndex].getActivity().equals("datatype.scheduledactivitytype.cenaprapoblacionmeta")) {
            		if(index > 5) {
            			if(index < 14) {
            				return "-";
            			}else if(index < 23) {
            				index -= 8;
            			}else{
            				return "-";
            			}
            		}
            	}
            	int val = attributes[rowIndex].hasResult(new Integer(index),answers);
            	switch(val) {
	            	case -1:
	        			return "-";
	        		default:
	        			return String.valueOf(val);
            	}
        }
    }

    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "id";
            case 1:
                return "name";
            case 2:
                return "lastname";
            case 3:
                return "activity";
            case 4:
                return "date";
            case 5:
                return "city";
            case 6:
                return "instructor";
            case 7:
                return "color";
            case 8:
                return "total";
            case 9:
                return "birthdate";
            case 10:
                return "population";
            case 11:
                return "organization";
            case 12:
                return "state";
            case 13:
                return "city";
            case 14:
                return "delegation";
            case 15:
                return "address";
            case 16:
                return "postalcode";
            case 17:
                return "phone";
            case 18:
                return "movile";
            case 19:
                return "email";
            case 20:
                return "medical";
            case 21:
                return "paramedical";
            case 22:
                return "nurse";
            case 23:
                return "police";
            case 24:
                return "vialinspector";
            case 25:
                return "teacher";
            case 26:
                return "prevent";
            case 27:
                return "otherprofession";
            case 28:
                return "education";
            case 29:
                return "othereducation";
            case 30:
                return "security";
            case 31:
                return "securitytrainning";
            case 32:
                return "securitydesc";
            case 33:
                return "teachexperience";
            case 34:
                return "first";
            case 35:
                return "second";
            case 36:
                return "third";
            case 37:
                return "otherteach";
            case 38:
                return "observations";
            case 39:
                return "division";
            case 40:
                return "municipaly";
            default:
                return "check"+String.valueOf(column-41);
        }
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }

    public int getTotal() {
        return total;
    }
}
