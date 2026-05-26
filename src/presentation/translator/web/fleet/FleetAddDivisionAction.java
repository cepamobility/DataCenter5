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
import communication.fleet.tables.FleetDivisionData;
import communication.language.Text;

/**
 * @author jrodriguez
 */
public class FleetAddDivisionAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        FleetDivisionData fleetDivisionData;
		FleetAddDivisionForm fleetForm = (FleetAddDivisionForm)form;
        FleetData fleet = sys.getFleetReportFacade().findFleetByPrimaryKey(new Integer(fleetForm.getId()),sys.getUserSessionData());
    
        String comment = null;
        
		String message = fleetForm.validate(fleet.getType().intValue(),sys.getUserSessionData());
		if(message != null) 
		{
		    session.setAttribute("FleetAddDivisionForm",fleetForm);
		    session.setAttribute("Msg", messages.getText(message));
            return mapping.findForward("back");
		}
		else 
		{
			// Obtengo los datos de la FleetDivision
			fleetDivisionData = fleetForm.getFleetDivision(fleet.getType().intValue());
			
			
			// Creo la FleetDivision en el Sistema
			fleetDivisionData = sys.getFleetABMFacade().fleetDivisionAdd(fleet,sys.getUserSessionData(),fleetDivisionData, comment);
			
			// Si la FleetDivision ya esta en el sistema no la agrego de nuevo
			// Para eso verifico que la fleetDivisionData tenga id <> null
			// if (fleetDivisionData.getId() == null)
			// {
				// Aca puedo mostrar de que ya existe esa Division en la Flota
			// }
			
            session.removeAttribute("FleetAddDivisionForm");
            session.setAttribute("id",fleetForm.getId());
			return mapping.findForward("back");
        }	
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        FleetAddDivisionForm fleetForm = (FleetAddDivisionForm)form;
        session.removeAttribute("FleetDataForm");
        session.setAttribute("id",fleetForm.getId());
        return mapping.findForward("cancel");
    }
}
