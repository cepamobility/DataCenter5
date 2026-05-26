package presentation.translator.web.alert;

import java.io.Serializable;
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

import persistence.util.Util;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.alert.AlertABMFacade;

import communication.administration.user.UserSessionData;
import communication.language.Text;

/**
 * @author jrodriguez
 *

 */
public class AlertDeleteAction extends AbstractAction implements Serializable{ 

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

        UserSessionData userSessionData = sys.getUserSessionData();
		
		DynaActionForm alertDeleteForm = (DynaActionForm) form;
		
		/*String comment = (String) alertDeleteForm.get("comment");
		if ((comment == null) || (comment.trim().length()==0)) {
			String message = "generic.activity.activitydata.commentInvalid";
			session.setAttribute("Msg", messages.getText(message));			
			return mapping.findForward("exclude");
		}*/
		
		String stringList = (String) alertDeleteForm.get("checkboxParamList");
			
		Collection colUid= stringToCollection(stringList);
						
		AlertABMFacade alertABM = sys.getAlertABMFacade();
		Iterator iterColUid=colUid.iterator();
		// String message="";
		while(iterColUid.hasNext()){
			String id=(String) iterColUid.next();
			if(!Util.empty(id)){
				alertABM.finishAlert(new Integer(id),userSessionData);
			}
		}
		return mapping.findForward("AlertList");
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
        return mapping.findForward("AlertList");
    }	

}
