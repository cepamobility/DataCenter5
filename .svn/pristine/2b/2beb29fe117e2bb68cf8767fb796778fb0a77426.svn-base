/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.fleet;

import java.util.Collection;

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
import business.fleet.FleetABMFacade;

/**
 * @author jrodriguez
 */
public class FleetDeleteAccidentIncidentAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        DynaActionForm fleetForm = (DynaActionForm)form;
        String stringList = (String)fleetForm.get("list");

        FleetABMFacade fleetABM = sys.getFleetABMFacade();
        Collection colIds = Util.stringToCollection(stringList);
        if(fleetForm.getString("type").equals("accident")) {
            fleetABM.deleteAccident(colIds,sys.getUserSessionData());
        }else if(fleetForm.getString("type").equals("incident")) {
            fleetABM.deleteIncident(colIds,sys.getUserSessionData());
        }else {
            fleetABM.deleteConsumedFuel(colIds,sys.getUserSessionData());
        }
        session.setAttribute("fleet",fleetForm.getString("fleet"));
        session.setAttribute("period",fleetForm.getString("period"));
        session.setAttribute("year",fleetForm.getString("year"));
        return mapping.findForward("back");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
}
