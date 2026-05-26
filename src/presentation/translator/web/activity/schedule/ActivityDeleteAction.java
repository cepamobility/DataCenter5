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
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.activity.ActivityABMFacade;
import business.activity.ActivityReportFacade;

import communication.activity.tables.CEPAActivityScheduleData;
import communication.administration.user.UserSessionData;
import communication.language.Text;

public class ActivityDeleteAction extends AbstractAction { 


	public ActionForward action(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        UserSessionData userSessionData = sys.getUserSessionData();
		
		DynaActionForm activityDeleteForm = (DynaActionForm) form;
		String stringList = (String) activityDeleteForm.get("checkboxParamList");
		
		String comment = (String) activityDeleteForm.get("comment");
		if ((comment == null) || (comment.trim().length()==0)) {
			String message = "generic.activity.activitydata.commentInvalid";
			// session.setAttribute("Msg", messages.getText(message));
			
			userSessionData.setMsg(messages.getText(message));
			userSessionData.setUrl("/activity/schedule/CEPAActivityScheduleExclusion.jsp?checkboxParamList="+stringList);
			return mapping.findForward("layout");
		}
		
		
			
		Collection colUid= stringToCollection(stringList);
						
		ActivityABMFacade activityABM = sys.getActivityABMFacade();
		ActivityReportFacade activityReport = sys.getActivityReportFacade();
		Iterator iterColUid=colUid.iterator();
		String message="";
		int count=0;		
		while(iterColUid.hasNext()){
			String id=(String) iterColUid.next();
			//CEPAActivityScheduleData activityData=new CEPAActivityScheduleData(new Integer(id));
			CEPAActivityScheduleData activityData=activityReport.findCEPAActivityScheduleByPrimaryKey(new Integer(id),userSessionData);
			
			if ((!activityData.getState().equals("datatype.scheduledactivitystate.dictada")
					&& activityReport.getParticipants(activityData.getActivityId(),userSessionData).isEmpty())) {
			
					activityABM.deleteCEPAScheduleActivity(activityData,comment,userSessionData);
			}
			else {
            	if (count==0)
            		message+= messages.getText("activity.error.remove_ini")+" "+Util.formatDate(activityData.getDate())+messages.getText(activityData.getType().getResourceBundleId())+messages.getText(activityData.getCountryName());
            	else if(count>0)
    				message+= ", "+Util.formatDate(activityData.getDate())+messages.getText(activityData.getType().getResourceBundleId())+messages.getText(activityData.getCountryName());
            	count+=1;					
			}
		}	
				
        if (count>0){
        	message+=" "+messages.getText("activity.error.remove_end");
        	// session.setAttribute("Msg", message);
        	userSessionData.setMsg(message);
        }		
        
        userSessionData.setUrl("/activity/schedule/ActivityList.jsp");
		return mapping.findForward("layout");
	}
	
	/**
	 * 
	 * @param stringList
	 * @return
	 */
	public Collection stringToCollection(String stringList){
		String[] stringArray=stringList.split("<");
		Collection col=new LinkedList();
		
		for(int i=0; i < stringArray.length; i++){
			col.add(stringArray[i]);
		}
		
		return col;
	}
	
	/**
	 * 
	 */
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
    	
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
    	userSessionData.setUrl("/activity/schedule/ActivityList.jsp");
    	
    	return mapping.findForward("layout");
    }	

}
