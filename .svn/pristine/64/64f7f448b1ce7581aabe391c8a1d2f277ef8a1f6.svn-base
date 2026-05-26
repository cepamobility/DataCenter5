/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.vehicle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.language.Text;

/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
//public class VehicleCreateIndorColAction extends Action {
public class VehicleCreateIndorColAction extends AbstractAction {
			
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {		
			
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        String target=(String)session.getAttribute("target");
        session.removeAttribute("target");

				if(isCancelled(request)) {
				    //return mapping.findForward("VehicleList");
					return mapping.findForward("home");
				}
				
				if (sys == null) {
					//User not logon
					
					session.setAttribute("Msg",Text.getMessage("generic.error.deslogued"));
					
					return mapping.findForward("login");
				}
	
			
				VehicleForm1 createData = (VehicleForm1)form;
				String message = createData.validate();
				
				if(message != null) {
					
				    //Some attribute data is invalid				
					session.setAttribute("target",target);				
					System.out.println("ATR INVALIDO");
					session.setAttribute("Msg", messages.getText(message));
					return mapping.findForward("create");
					
				}else {
				    
					System.out.println("****************USO:****************" + createData.getUse());
					String useType = createData.getUse();
					session.setAttribute("useType",useType);					
					String forward = "create";
					if (useType != null) {
						//if (createData.getUse().equals("persistence.datatype.typeuse.individual")) {
						if (useType.equals("persistence.datatype.typeuse.individual")) {
							forward = "find_driver";
						}
					}
					return mapping.findForward(forward);
				}

	}

    private boolean isEmpty(String value) {
	    return ((value == null) || (value.trim().length()==0));
	}
	
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("VehicleList");
    }       

}
