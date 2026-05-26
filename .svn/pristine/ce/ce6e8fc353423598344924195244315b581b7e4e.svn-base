/*
 * Created on 31-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.driver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.administration.corporation.CorporationABMFacade;

import communication.administration.corporation.tables.PointAccountData;
import communication.administration.corporation.tables.PointMoveData;
import communication.administration.user.UserSessionData;
/**
 * @author jrodriguez
 */
public class DriverDeleteAssignPointsAction extends AbstractAction {
	
	public ActionForward action(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();

        DynaActionForm driverForm = (DynaActionForm) form;
        Integer move = new Integer((String) driverForm.get("move"));
        Integer accountId = new Integer((String) driverForm.get("account"));
		PointAccountData account = sys.getCorporationReportFacade().getPointAccount(accountId, sys.getUserSessionData());
		PointMoveData moveData = new PointMoveData(move);
		moveData.setAmount(Double.parseDouble((String) driverForm.get("amount")));
		CorporationABMFacade facade = sys.getCorporationABMFacade();
		facade.removeMove(account, moveData, userSessionData);
		facade.updatePointAccount(account.getId(), userSessionData);
        userSessionData.setUrl("/driver/DriverView.jsp?id="+account.getReference());
        return mapping.findForward("next");
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();

        DynaActionForm driverForm = (DynaActionForm) form;
        String driver = (String) driverForm.get("driver");
        userSessionData.setUrl("/driver/DriverView.jsp?id="+driver);
        return mapping.findForward("next");
    }
}
