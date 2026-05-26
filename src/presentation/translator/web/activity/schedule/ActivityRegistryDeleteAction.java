/*
 * Created on 31-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.activity.schedule;

import java.util.Collection;
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

import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;

/**
 * @author jrodriguez
 */
public class ActivityRegistryDeleteAction extends AbstractAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward action(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        UserSessionData userSessionData = sys.getUserSessionData();		
		DynaActionForm activityRegistryForm = (DynaActionForm) form;			
		String stringList = (String) activityRegistryForm.get("checkboxParamList");
		
		// Se esta migrando los activity (id) a idActivity..pero puede ser que falten algunos...
		String idActivity = activityRegistryForm.getString("activity");
		
        if(idActivity == null || idActivity.length() == 0) {
            idActivity=(String)session.getAttribute("id");
        }
        
		Collection<String> collection = stringToCollection(stringList);
		
		ActivityABMFacade activityABM = sys.getActivityABMFacade();
        activityABM.deleteRegistries(new Integer(idActivity),collection,activityRegistryForm.getString("comment"),userSessionData);
        if(sys.isCorporation(CorporationData.FORESTALES)) {
        	activityABM.deleteRegistryLink(new Integer(idActivity), collection, userSessionData);
        }
		// session.setAttribute("id",idActivity);
		userSessionData.setUrl("/activity/schedule/CEPAActivityScheduleView.jsp?idActivity="+idActivity);
		return mapping.findForward("layout");
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
    	SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();		
 		DynaActionForm activityRegistryForm = (DynaActionForm) form;
 		String idActivity = (activityRegistryForm.getString("idActivity") != null)?
				activityRegistryForm.getString("idActivity"):
				activityRegistryForm.getString("activity");    
    	userSessionData.setUrl("/activity/schedule/CEPAActivityScheduleView.jsp?idActivity="+idActivity);
		return mapping.findForward("layout");
    }	

}
