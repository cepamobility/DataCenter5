/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.fleet;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.fleet.FleetABMFacade;

import communication.fleet.tables.FleetData;
import communication.language.Text;

/**
 * @author jrodriguez
 */
public class FleetCreateAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        FleetDataForm createData = (FleetDataForm)form;

		String message = createData.validate(createData.getType());
		if(message != null) {
		    session.setAttribute("FleetCreateForm",createData);
		    session.setAttribute("Msg", messages.getText(message));
            if(Integer.parseInt(createData.getType()) == FleetData.COLECTIVE) {
                return mapping.findForward("createColective");
            }else {
                return mapping.findForward("createIndividual");                
            }
		}else {
       		FleetData fleet = new FleetData(null,createData.getName(),
            		createData.getDescription(),true,
            		new Integer(createData.getPeriod()),
            		new Integer(createData.getType()),
            		new LinkedList(),null,null,null);
			FleetABMFacade fleetABM = sys.getFleetABMFacade();
            fleetABM.fleetCreate(fleet,createData.getComment(),sys.getUserSessionData());
            session.removeAttribute("FleetCreateForm");
			return mapping.findForward("success");
        }	
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("cancel");
    }
}
