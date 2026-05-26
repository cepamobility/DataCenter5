/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.fleet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.fleet.tables.FleetData;
import communication.language.Text;
import communication.util.LogActions;

/**
 * @author jrodriguez
 */
public class FleetUpdateAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        FleetDataForm createData = (FleetDataForm)form;
		
		// TODO GUS: El commentario debe venir como parametro de la jsp.
        String comment = null;
       
		String message = createData.validate(createData.getType());
		if(message != null) {
		    session.setAttribute("FleetUpdateForm",createData);
		    session.setAttribute("Msg", messages.getText(message));
            session.setAttribute("id",createData.getId());
            return mapping.findForward("back");                
		}else {
            FleetData fleet = sys.getFleetReportFacade().findFleetByPrimaryKey(new Integer(createData.getId()),sys.getUserSessionData()); 
            fleet.setName(createData.getName());    
            fleet.setDescription(createData.getDescription());
            fleet.setPeriod(new Integer(createData.getPeriod()));
            sys.getFleetABMFacade().fleetUpdate(fleet,sys.getUserSessionData(), comment, LogActions.UPDATE);
            session.removeAttribute("FleetEditForm");
            session.setAttribute("id",createData.getId());
			return mapping.findForward("next");
        }	
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("cancel");
    }
}
