package presentation.translator.web.event;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import communication.alert.AlertConstants;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

public class DeleteFileAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
       
    	HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        DynaActionForm fileForm = (DynaActionForm)form;
        String id = fileForm.getString("id");
        File f = new File(AlertConstants.EVENT_DIRECTORY+"event_" + id + ".pdf");
        f.delete();
        
   		sys.getEventABMFacade().deleteFile(new Integer(id), sys.getUserSessionData());
   		sys.getUserSessionData().setUrl("event/files.jsp?event="+fileForm.getString("event")+"&type="+request.getParameter("type"));
        return mapping.findForward("layout"); 		
    }

	public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("layout"); 		
    }

}
