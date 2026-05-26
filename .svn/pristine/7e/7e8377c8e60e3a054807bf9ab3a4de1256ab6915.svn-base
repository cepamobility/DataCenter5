package presentation.translator.web.documents;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.sun.org.apache.xml.internal.utils.StringToIntTable;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.driver.DriverABMFacade;

public class DeleteAppFileAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
       
    	HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
         
    //  Obtengo los id de los checkbox seleccionados  
    	StringTokenizer deleteTkn = new StringTokenizer(((DynaActionForm)form).getString("list"),"<");
    	String[] deleteIds = new String[deleteTkn.countTokens()];
    	int i = 0;
    	while(deleteTkn.hasMoreTokens()) {
    		deleteIds[i] = deleteTkn.nextToken();
    		i++;
    	}
		if(deleteIds.length  > 0){
	        sys.getCorporationABMFacade().deleteAppFile(deleteIds, sys.getUserSessionData());
		}
    	
 		sys.getUserSessionData().setUrl("/appfiles/files.jsp");
        return mapping.findForward("next");
 		
    }

	
	public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
 		sys.getUserSessionData().setUrl("/appfiles/files.jsp");
        return mapping.findForward("next");
    }

}
