/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.cenapra.report;

import java.io.Serializable;

import javax.swing.table.AbstractTableModel;

import communication.administration.corporation.util.Util;
import communication.cenapra.CENAPRAParticipantListData;
import communication.cenapra.CENAPRAParticipantListFullData;
import communication.language.Text;
import communication.util.ListResult;

public class SatisfactionReportData extends AbstractTableModel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total;
    private CENAPRAParticipantListData[] attributes;
    private boolean color = true;
    private Text messages;

    private Integer[] points;
    
    public SatisfactionReportData() {
        total = 0;
        attributes = new CENAPRAParticipantListData[0]; 
    }
    
    public SatisfactionReportData(ListResult participants,Integer[] points, Text messages) {
        this.points = points;
        total = participants.getTotal();
        attributes = (CENAPRAParticipantListData[])participants.getElements().toArray(new CENAPRAParticipantListData[0]);
        this.messages = messages;
    }


    public int getRowCount() {
        return attributes.length;
    }

    public int getColumnCount() {
        return 48;
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
            case 18:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getBirthdate();
            case 19:
                return messages.getText(((CENAPRAParticipantListFullData)attributes[rowIndex]).getPopulation());
            case 20:
                return messages.getText(((CENAPRAParticipantListFullData)attributes[rowIndex]).getOrganization());
            case 21:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getState();
            case 22:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getCity();
            case 23:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getDelegation();
            case 24:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getAddress();
            case 25:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getPostalcode();
            case 26:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getPhone();
            case 27:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getMovile();
            case 28:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getEmail();
            case 29:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isMedical()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 30:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isParamedical()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 31:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isNurse()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 32:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isPolice()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 33:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isVialinspector()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 34:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isTeacher()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 35:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isPrevent()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 36:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getOtherprofession();
            case 37:
                return messages.getText(((CENAPRAParticipantListFullData)attributes[rowIndex]).getEducation());
            case 38:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getOthereducation();
            case 39:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isSecurity()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 40:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isSecuritytrainning()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 41:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getSecuritydesc();
            case 42:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isTeachexperience()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 43:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isFirst()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 44:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isSecond()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 45:
                return (((CENAPRAParticipantListFullData)attributes[rowIndex]).isThird()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
            case 46:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getOtherteach();
            case 47:
                return ((CENAPRAParticipantListFullData)attributes[rowIndex]).getObservations();
            default:
                return attributes[rowIndex].getValue(messages,points[columnIndex-9]);
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
            case 18:
                return "birthdate";
            case 19:
                return "population";
            case 20:
                return "organization";
            case 21:
                return "state";
            case 22:
                return "city";
            case 23:
                return "delegation";
            case 24:
                return "address";
            case 25:
                return "postalcode";
            case 26:
                return "phone";
            case 27:
                return "movile";
            case 28:
                return "email";
            case 29:
                return "medical";
            case 30:
                return "paramedical";
            case 31:
                return "nurse";
            case 32:
                return "police";
            case 33:
                return "vialinspector";
            case 34:
                return "teacher";
            case 35:
                return "prevent";
            case 36:
                return "otherprofession";
            case 37:
                return "education";
            case 38:
                return "othereducation";
            case 39:
                return "security";
            case 40:
                return "securitytrainning";
            case 41:
                return "securitydesc";
            case 42:
                return "teachexperience";
            case 43:
                return "first";
            case 44:
                return "second";
            case 45:
                return "third";
            case 46:
                return "otherteach";
            case 47:
                return "observations";
            default:
                return "value"+String.valueOf(column-9);
        }
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }

    public int getTotal() {
        return total;
    }
}
