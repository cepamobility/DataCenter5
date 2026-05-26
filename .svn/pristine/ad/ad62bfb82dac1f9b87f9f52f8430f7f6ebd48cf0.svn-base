/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.driver;

import java.rmi.RemoteException;
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

import communication.exception.CommunicationProblemException;
import communication.exception.DCSecurityException;
import communication.exception.DeslogedException;
import communication.language.Text;

/**
 * @author jrodriguez
 */
public class AddProvisionalBTWAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		//Desde la vista del conductor en su historial si tiene un BTW provisional se puede eliminar
		//para ello se llama a este action pasando el parametro deleteBTW
		if(request.getParameter("deleteBTW")!=null){
			return deleteProvisionalBTW(mapping,  request,  response);
		}
		
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        DynaActionForm btwData = (DynaActionForm)form;
        
        Integer driverId = new Integer(btwData.getString("driver"));
        Date btwDate = Util.getDate(btwData.getString("btwDate"),btwData.getString("btwMonth"),btwData.getString("btwYear"));
        if(btwDate == null) {
        	sys.getUserSessionData().setMsg(messages.getText("driver.provisionaldate.errordate"));
        	sys.getUserSessionData().setUrl("driver/addProvisionalBTW.jsp?id="+btwData.getString("driver"));
        }
        String result = btwData.getString("result");

        sys.getDriverABMFacade().addProvisionalBTW(driverId, result, btwDate, sys.getUserSessionData());
    	sys.getUserSessionData().setUrl("driver/DriverView.jsp?id="+btwData.getString("driver"));
		return mapping.findForward("next");
	}

    private ActionForward deleteProvisionalBTW(ActionMapping mapping,
			HttpServletRequest request, HttpServletResponse response) throws DeslogedException, CommunicationProblemException, DCSecurityException, RemoteException, Exception {
		// TODO Auto-generated method stub
    	Integer idDriver=Integer.valueOf(request.getParameter("idDriver"));
    	Date expiry=Util.getDate((String)request.getParameter("expiry")).getTime();
    	HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        
        sys.getDriverABMFacade().deleteProvisionalBTW(idDriver, expiry, sys.getUserSessionData());
        
		return null;
	}

	public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        DynaActionForm btwData = (DynaActionForm)form;
    	sys.getUserSessionData().setUrl("driver/DriverView.jsp?id="+btwData.getString("driver"));
		return mapping.findForward("next");
    }
}
