/*
 * Created on 31-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.driver;

import java.util.Calendar;
import java.util.Date;

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
import business.administration.corporation.CorporationABMFacade;

import communication.administration.corporation.tables.PointMoveData;
import communication.administration.user.UserSessionData;
/**
 * @author jrodriguez
 */
public class DriverModifyAssignPointsAction extends AbstractAction {
	
	public ActionForward action(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();

        DynaActionForm driverForm = (DynaActionForm) form;
        String driver = (String) driverForm.get("driver");
        PointMoveData move = sys.getCorporationReportFacade().getPointMove(new Integer((String) driverForm.get("move")), userSessionData);
        
        Date assignDate = Util.getDate(driverForm.getString("date"), driverForm.getString("month"), driverForm.getString("year"));
        if(assignDate != null) {
            Calendar c = Calendar.getInstance();
        	c.setTime(assignDate);
        	move.setDate(c);
        }
        Integer account = new Integer((String) driverForm.get("account"));
        int points = Integer.parseInt((String) driverForm.get("points"));
        if(points == 0) {
            if(!Util.isNumber((String)driverForm.get("otherPoint"))) {
    			userSessionData.setMsg(sys.getText().getText("driver.error.invalidpoints"));
    	        userSessionData.setUrl("/driver/DriverPoints.jsp?move="+ driverForm.get("move"));
    	        return mapping.findForward("next");
            }
        	points = Integer.parseInt((String) driverForm.get("otherPoint"));
        }
        if(Integer.parseInt((String) driverForm.get("type")) == 0) {
        	points = points * -1;
        }
        String comment = (String) driverForm.get("comment");

        int diff = points - move.getAmount().intValue();
        move.setAmount(new Integer(points).doubleValue());
        move.setReference(comment);
        
        CorporationABMFacade facade = sys.getCorporationABMFacade();
        facade.updateMove(account, diff, move, userSessionData);
        facade.updatePointAccount(account, userSessionData);
        userSessionData.setUrl("/driver/DriverView.jsp?id="+driver);
        session.removeAttribute("DriverAssignPointsForm");
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
