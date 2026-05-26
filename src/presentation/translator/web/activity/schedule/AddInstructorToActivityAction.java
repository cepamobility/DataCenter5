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
import org.apache.struts.action.DynaActionForm;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.activity.ActivityABMFacade;
import business.activity.ActivityReportFacade;
import business.driver.DriverReportFacade;

import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.CEPAActivityData;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.activity.tables.CEPAScheduleActivityInstructorData;
import communication.administration.user.UserSessionData;
import communication.administration.user.tables.UserData;
import communication.driver.tables.DriverData;

public class AddInstructorToActivityAction extends AbstractAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
        
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
		UserSessionData userSessionData = sys.getUserSessionData();		
		
        DynaActionForm activityRegistryForm = (DynaActionForm) form;			
		String stringList = activityRegistryForm.getString("checkboxParamList");		
        String idActivity = (activityRegistryForm.getString("activity") != null &&
        		activityRegistryForm.getString("activity").trim() != "")?
        				activityRegistryForm.getString("activity"):"0";           

        String note = "";
		boolean hasResult = false;		
		String obs = "";
		
		Collection<String> colUid= stringToCollection(stringList);
			
		ActivityReportFacade activityReport = sys.getActivityReportFacade();
		//CEPAActivityScheduleData activityData = new CEPAActivityScheduleData(new Integer(idActivity));		
		CEPAActivityScheduleData activityData = activityReport.findCEPAActivityScheduleByPrimaryKey(new Integer(idActivity),userSessionData);

		ActivityABMFacade activityABM = sys.getActivityABMFacade();
				
		if (activityData != null &&
			activityData.getDefinition() != null && colUid != null && colUid.size() > 0 ){
			
			Iterator<String> iterColUid=colUid.iterator();
			LinkedList<CEPAScheduleActivityInstructorData> instructors = activityData.getInstructors();
			while(iterColUid.hasNext()){
				String loginName =(String) iterColUid.next(); // loginname
				UserData userData = new UserData(loginName);
				
				if (instructors == null) instructors = new LinkedList();
				CEPAScheduleActivityInstructorData instructor = new CEPAScheduleActivityInstructorData(activityData, userData);
				instructors.add(instructor);		
			}
			activityData.setInstructors(instructors);
			activityABM.updateCEPAScheduleActivity(activityData, "Instructor Added.", userSessionData);
		}
		
        session.removeAttribute("AddInstructorToActivityForm");
        session.setAttribute("id",idActivity);
		return mapping.findForward("ActivityView");
	}	

	public Collection<String> stringToCollection(String stringList){
		String[] stringArray=stringList.split("<");
		Collection<String> col=new LinkedList<String>();
		for(int i=0; i < stringArray.length; i++){
			col.add(stringArray[i]);
		}
		return col;
	}
	
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("ActivityView");
    }	

}
