package communication.activity.report;

import java.io.Serializable;

import javax.swing.table.AbstractTableModel;

import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.PointItemData;
import communication.administration.user.UserSessionData;
import communication.language.Text;

public class ActivityReportTabletData extends AbstractTableModel 
	implements Serializable {

    static final long serialVersionUID = 1L;
    
    int total;
    PointItemData[] pointItem;    
    
    boolean color = true;
    Text messages;

	private String type;

    public ActivityReportTabletData() {
        total = 0;
        pointItem = new PointItemData[0];    
    }
    
    /**
     * 
     * @param reportDrivers
     */
    public ActivityReportTabletData(ActivityReportTabletDataType reportDrivers) {
        total = reportDrivers.getPointItem().size();
        pointItem = (PointItemData[])reportDrivers.getPointItem().toArray(new PointItemData[0]);     
    }

    public int getRowCount() {
        return total;
    }

    public int getColumnCount() {
        return 6;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
            	String textReport = pointItem[rowIndex].getPoint().getTextReport();
            	if((textReport.equals("tablet.evaluacionencabina.pointreport6")
            			|| textReport.equals("tablet.toj.pointreport6") 
            			|| textReport.equals("tablet.toj4X4.pointreport6") 
            			|| textReport.equals("tablet.phrd.pointreport6") 
            			|| textReport.equals("tablet.btw1onboard.pointreport6") 
            			|| textReport.equals("tablet.btw2onboard.pointreport6") 
            			|| textReport.equals("tablet.specialdayoftraining.pointreport6"))
            			&& pointItem[rowIndex].getItem().getItemOrder() > 5) {
            		textReport += "1";
            	}
            	return messages.getText(textReport);
            case 1:
            	if (pointItem[rowIndex].getItem() != null &&
            			pointItem[rowIndex].getItem().getTextReport() != null){
            		
            			String text = messages.getText(pointItem[rowIndex].getItem().getTextReport()); 
            			if  (pointItem[rowIndex].getItem().getItemOrder() != null &&
            					pointItem[rowIndex].getItem().getItemOrder() < 100){
            				// los items dentro de los puntos tienen itemorder > 100
            				text = "* ".concat(text);
            			}
            			/*
            			else if (pointItem[rowIndex].getItem().getItemOrder() != null && 
            					pointItem[rowIndex].getItem().getItemOrder() > 100){
            				// Las observaciones tienen itemorder > 100
            				text = "   - ".concat(text);
            			}
            			*/
            			return text;       
            	}else
            		return "";
            case 2:
                color = !color;
                return new Boolean(color);           
            case 3:
            	textReport = pointItem[rowIndex].getPoint().getText();
            	if((textReport.equals("tablet.evaluacionencabina.point6")
            			|| textReport.equals("tablet.toj.point6") 
            			|| textReport.equals("tablet.toj4X4.pointreport6") 
            			|| textReport.equals("tablet.btw1onboard.point6") 
            			|| textReport.equals("tablet.btw2onboard.point6") 
            			|| textReport.equals("tablet.phrd.point6") 
            			|| textReport.equals("tablet.specialdayoftraining.point6") )
            			&& pointItem[rowIndex].getItem().getItemOrder() > 5) {
            		return "";
            	}
            	return messages.getText(textReport);
            case 4:
            	textReport = pointItem[rowIndex].getPoint().getTextReport()+".concept";
            	if((textReport.equals("tablet.report.evaluacionencabina.point6.concept")
            			|| textReport.equals("tablet.report.toj.point6.concept") 
            			|| textReport.equals("tablet.report.toj.point64X4.concept") 
            			|| textReport.equals("tablet.btw1onboard.point6") 
            			|| textReport.equals("tablet.btw2onboard.point6") 
            			|| textReport.equals("tablet.report.phrd.point6.concept") 
            			|| textReport.equals("tablet.report.specialdayoftraining.point6.concept") )
            			&& pointItem[rowIndex].getItem().getItemOrder() > 5) {
            		return "";
            	}
            	return messages.getText("generic.messages.concept")+" :"+messages.getText(textReport);
            case 5:
            	textReport = pointItem[rowIndex].getPoint().getTextReport()+".recomendation";
            	if((textReport.equals("tablet.report.evaluacionencabina.point6.recomendation")
            			|| textReport.equals("tablet.report.toj.point6.recomendation") 
            			|| textReport.equals("tablet.report.toj4X4.point6.recomendation") 
            			|| textReport.equals("tablet.btw1onboard.point6") 
            			|| textReport.equals("tablet.btw2onboard.point6") 
            			|| textReport.equals("tablet.report.phrd.point6.recomendation") 
            			|| textReport.equals("tablet.report.specialdayoftraining.point6.recomendation") )
            			&& pointItem[rowIndex].getItem().getItemOrder() > 5) {
            		return "";
            	}
            	return messages.getText(textReport);
            
        }
        return null;
    }

    private boolean isModified() {
    	if(type==null)
    		return false;
		return (type.equals(CEPAActivityConstants.BTW1_NEW_FORM_TABLET) || 
				type.equals(CEPAActivityConstants.BTW2_NEW_FORM_TABLET) || 
				type.equals(CEPAActivityConstants.PHRD_TABLET) || 
				type.equals(CEPAActivityConstants.BTW4X4_ONBOARD_TABLET) || 
				type.equals(CEPAActivityConstants.SPECIALDAYOFTRAINING_TABLET) || 
				type.equals(CEPAActivityConstants.TRAINING_ON_THE_JOB_TABLET) || 
				type.equals(CEPAActivityConstants.TRAINING_ON_THE_JOB_4X4_TABLET) || 
				type.equals(CEPAActivityConstants.EVALUACION_EN_CABINA_TABLET));
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
                return "pointtitle";        
            case 4:
                return "pointconcept";        
            case 5:
                return "pointrecomendation";
        }
        return null;
    }
    
    public void setMessages(Text messages) {
        this.messages = messages;
    }

	public void setType(String type) {
		this.type = type;
	}
}
