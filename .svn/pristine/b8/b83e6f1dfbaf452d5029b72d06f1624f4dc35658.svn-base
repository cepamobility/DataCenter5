/*
 * Created on 24-feb-2006
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
import org.apache.struts.action.DynaActionForm;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.language.Text;

public class DeleteDetailDivisionAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        DynaActionForm reportForm = (DynaActionForm)form;
        
        String fleet = reportForm.getString("fleet");
        
        if(!Util.isNumber(reportForm.getString("fromMonth")) || !Util.isNumber(reportForm.getString("fromYear")) || 
        		!Util.isNumber(reportForm.getString("toMonth")) || !Util.isNumber(reportForm.getString("toYear"))) {
            session.setAttribute("Msg",messages.getText("generic.error.emptyperiod"));
            sys.getUserSessionData().setUrl("fleet/FleetDeleteDivisions.jsp?id="+fleet);
            return mapping.findForward("next");
        }
        Integer fromMonth = new Integer(reportForm.getString("fromMonth"));
        Integer toMonth = new Integer(reportForm.getString("toMonth"));
        Integer fromYear = new Integer(reportForm.getString("fromYear"));
        Integer toYear = new Integer(reportForm.getString("toYear"));
        if(!Util.afterOrEqual(fromMonth,fromYear,toMonth,toYear)) {
            session.setAttribute("Msg",messages.getText("generic.error.wrongperiod"));
            sys.getUserSessionData().setUrl("fleet/FleetDeleteDivisions.jsp?id="+fleet);
            return mapping.findForward("next");
        }
        
        sys.getFleetABMFacade().deleteCollectiveData(new Integer(fleet), new Integer(fromMonth), new Integer(fromYear), new Integer(toMonth), new Integer(toYear), sys.getUserSessionData());
        session.setAttribute("Msg",messages.getText("web.fleet.divisiondeleted"));
        sys.getUserSessionData().setUrl("fleet/FleetView.jsp?id="+fleet);
        session.removeAttribute("DeleteFleetDivisionDetailForm");
        return mapping.findForward("next");
    }
     
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        sys.getUserSessionData().setUrl("fleet/FleetView.jsp?id="+((DynaActionForm)form).getString("fleet"));
        return mapping.findForward("next");
    }

} 
    


