package presentation.translator.web.activity.schedule;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.activity.ActivityReportFacade;

import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.activity.tables.ItemData;
import communication.activity.tables.PointData;
import communication.driver.tables.DriverData;
import communication.language.Text;

/**
 * @author jrodriguez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActivityReportAction extends AbstractAction { 

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward action(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
	
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        String idActivity="86";

		ActivityReportFacade activityReport = sys.getActivityReportFacade();		
		CEPAActivityScheduleData activityData = activityReport.findCEPAActivityScheduleByPrimaryKey(new Integer(idActivity),sys.getUserSessionData());
		
		String textResultPoint ="";
		String textInforme = "";
		
		Collection drivers = activityReport.getParticipants(new Integer(idActivity),sys.getUserSessionData());
		Iterator iter = drivers.iterator();
		String textDriver = "";
		while(iter.hasNext()){
			DriverData driverData =(DriverData) iter.next();			
			CEPAActivityScheduleRegistryData completeResult = activityReport.getCompleteResult(new Integer(idActivity),driverData.getId(),sys.getUserSessionData());			 
			Iterator iterPoints = activityData.getDefinition().getCheckList().getPoints().iterator();
			String textPoints = "";			
			while(iterPoints.hasNext()) {
				PointData point = (PointData)iterPoints.next();
				String labelPoint = messages.getText(point.getText());
				textResultPoint = textResultPoint + labelPoint + "\n";				
				Iterator iterItemsPoint = point.getItems().iterator();
				String textItems = "\n" + labelPoint;
				while(iterItemsPoint.hasNext()) {
					ItemData item = (ItemData) iterItemsPoint.next();
					if (completeResult.getItemSet().contains(item)) {
//						textResultPoint = textResultPoint + "\n" + item.getTextReport();
						textItems = textItems + "\n" + item.getTextReport();
					}
				}
				textPoints = textPoints + textItems; 
			}		
			textDriver = driverData.getFirstName() + " " + driverData.getLastName() + textPoints;
			textInforme = textInforme + "\n\n" + textDriver;
		}
		
		return mapping.findForward("ActivityView");
		
	}
	

	public Collection stringToCollection(String stringList){
		String[] stringArray=stringList.split("<");
		Collection col=new LinkedList();
		
		for(int i=0; i < stringArray.length; i++){
			col.add(stringArray[i]);
		}
		
		return col;
	}
	
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("ActivityList");
    }	

    private String headReport(CEPAActivityScheduleData activityData) {
    	String text = "";
		Iterator iterPoints = activityData.getDefinition().getCheckList().getPoints().iterator();
		while(iterPoints.hasNext()){
			PointData point = (PointData)iterPoints.next();
			String labelPoint = point.getText();
			String descriptionPoint = point.getTextReport();
			text = text + "\n\n" + labelPoint + "\n" + descriptionPoint;  
		}
		return text;
    }
}
