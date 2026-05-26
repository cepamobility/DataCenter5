package presentation.translator.web.driver;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.user.UserSessionData;

/**
 * @author fcarriquiry
 */
public class UploadUpdateDriverAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        DynaActionForm formDyna=(DynaActionForm) form;
        
        if(formDyna.get("step").equals("1")){
        	
        	return step1( mapping,  formDyna,  request,  response);
        }
                
        
	    return null;
	}

	private ActionForward step1(ActionMapping mapping, DynaActionForm form,	HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
        
		FormFile file=(FormFile)form.get("file");
		System.out.println("FILE:"+file.getFileName()+" SIZE:"+file.getFileSize());
		
		try {
			session.setAttribute("rows", Util.completeRows(Util.xlsInputStreamToArray(file.getInputStream())));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return mapping.findForward("step2");
	}

	@Override
	public ActionForward cancel(HttpSession session, ActionMapping mapping,ActionForm form) {
		return null;
	}

	 
}
