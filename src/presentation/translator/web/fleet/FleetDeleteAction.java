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
public class FleetDeleteAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        DynaActionForm fleetForm = (DynaActionForm) form;
        String stringList = (String) fleetForm.get("checkboxParamList");
        String action = (String) fleetForm.get("action");
        FleetABMFacade fleetABM = sys.getFleetABMFacade();
        FleetReportFacade fleetReport;
        Collection colIds;
        Iterator it;
        Date exclusionDate;

        /* Action: DELETE */
        if (action.equals("delete")) {
            if (Util.empty((String) fleetForm.get("comment"))) {
                session.setAttribute("Msg", messages.getText("driver.error.invalidcomment"));
                return mapping.findForward("exclude");
            }
            fleetReport = sys.getFleetReportFacade();
            colIds = Util.stringToCollection(stringList);
            it = colIds.iterator();
            while(it.hasNext()) {
                if(!fleetReport.validateDelete(new Integer((String)it.next()),sys.getUserSessionData())) {
                    session.setAttribute("Msg", messages.getText("fleet.invalid.delete"));
                    return mapping.findForward("FleetList");
                }
            }
            fleetABM = sys.getFleetABMFacade();
            fleetABM.fleetDelete(colIds,sys.getUserSessionData(),(String) fleetForm.get("comment"));
    		return mapping.findForward("FleetList");         
        }
        	        
        // Action: EXCLUDE
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
            if (Util.empty((String) fleetForm.get("comment"))) {
                session.setAttribute("Msg", messages.getText("driver.error.invalidcomment"));
                return mapping.findForward("exclude");
            }
            
            exclusionDate = Util.getDate((String) fleetForm
                    .get("exclusionDate"), (String) fleetForm
                    .get("exclusionMonth"), (String) fleetForm
                    .get("exclusionYear"));
            fleetABM.fleetExclude(stringList.replaceAll("<", ","),exclusionDate,
            		(String) fleetForm.get("comment"),sys.getUserSessionData());
        }
        
        // Action: INCLUDE
        if (action.equals("include")) {
            if (Util.empty((String) fleetForm.get("comment"))) {
                session.setAttribute("Msg", messages.getText("driver.error.invalidcomment"));
                return mapping.findForward("exclude");
            }
            fleetABM.fleetInclude(stringList.replaceAll("<", ","),
            		(String)fleetForm.get("comment"),sys.getUserSessionData());
        }

        return mapping.findForward("FleetList");		
	}
		
		

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
}
