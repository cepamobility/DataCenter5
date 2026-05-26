/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.fleet;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

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
import business.fleet.FleetReportFacade;

import communication.language.Text;

/**
 * @author jrodriguez
 */
public class FleetDeleteDivisionAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

		Iterator it;
		Integer id;
		DynaActionForm fleetForm = (DynaActionForm)form;
        String stringList = (String) fleetForm.get("checkboxParamList");
        String action = (String) fleetForm.get("action");
        String comment = (String)fleetForm.get("comment");
        Date exclusionDate;
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        FleetABMFacade fleetABM = sys.getFleetABMFacade();
        FleetReportFacade fleetReport = sys.getFleetReportFacade();
        
        Collection colIds = Util.stringToCollection(stringList);
        colIds.remove(null);
        
        /* Action: DELETE */
        if (action.equals("delete")) {
            if (Util.empty(comment)) {
                session.setAttribute("Msg", messages.getText("driver.error.invalidcomment"));
                return mapping.findForward("exclude");
            }
           
            /* Resticcion de Integridad para Delete de Flotas 
             * No permitimos el borrado de una division si esta tiene detalles.
             */
            it = colIds.iterator();
            while (it.hasNext())
            {
            	id = Integer.valueOf((String)it.next());
            	if (id != null && fleetReport.fleetDivisionHasDetails(id,sys.getUserSessionData())) 	
            	{
            		 session.setAttribute("Msg", messages.getText("fleet.error.divisionwithdetail"));	                
            		 /* Agregarle una marquita a los que tienen detalles */
                     return mapping.findForward("exclude");
            	}
            }
            if (colIds.size() > 0)
            {
            	fleetABM.fleetDeleteDivisions(colIds,sys.getUserSessionData(),comment);
            }
            session.setAttribute("id",(String)fleetForm.get("fleet"));
        	return mapping.findForward("back");
        }
        
        /* Action: EXCLUDE */
        if (action.equals("exclude")) {
            if (Util.empty((String) fleetForm.get("exclusionDate"))
                    || !Util.isNumber((String) fleetForm.get("exclusionDate"))
                    || Util.empty((String) fleetForm.get("exclusionMonth"))
                    || !Util.isNumber((String) fleetForm.get("exclusionMonth"))
                    || Util.empty((String) fleetForm.get("exclusionYear"))
                    || !Util.isNumber((String) fleetForm.get("exclusionYear"))) {
                session.setAttribute("Msg", messages.getText("driver.error.invalidexclusiondate"));	                
                return mapping.findForward("exclude");
            }
            if (Util.empty(comment)) {
                session.setAttribute("Msg", messages.getText("driver.error.invalidcomment"));
                return mapping.findForward("exclude");
            }
            
            exclusionDate = Util.getDate((String) fleetForm
                    .get("exclusionDate"), (String) fleetForm
                    .get("exclusionMonth"), (String) fleetForm
                    .get("exclusionYear"));
            fleetABM.fleetExcludeDivisions(colIds,exclusionDate, comment,sys.getUserSessionData());
        	session.setAttribute("id",(String)fleetForm.get("fleet"));
            return mapping.findForward("back");		
        }
        
        // Action: INCLUDE
        if (action.equals("include")) {
            if (Util.empty(comment)) {
                session.setAttribute("Msg", messages.getText("driver.error.invalidcomment"));
                return mapping.findForward("exclude");
            }
            fleetABM.fleetIncludeDivisions(colIds, comment,sys.getUserSessionData());
        }
        
    	session.setAttribute("id",(String)fleetForm.get("fleet"));
        return mapping.findForward("back");		
        
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
}
