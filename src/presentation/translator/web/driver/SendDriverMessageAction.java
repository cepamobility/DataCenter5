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

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.CountryData;
import communication.administration.user.UserSessionData;
import communication.alert.AlertConstants;
import communication.util.MailSender;


public class SendDriverMessageAction extends AbstractAction {

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
        
    	MailSender mailSender = new MailSender();
        DynaActionForm forms = (DynaActionForm)form;
        
        HttpSession session = request.getSession();
        SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
        UserSessionData userSessionData = sys.getUserSessionData();
        String driverName = userSessionData.getDriverData().getFirstName()+" "+userSessionData.getDriverData().getLastName();
        
        String body = "<p>Conductor: "+driverName+"</p>";
        body += "<p>--------------------------------------------------------</p>";
        body += "<b>Mensaje:</b>";
        body += "<br>";
        body += forms.getString("body");
        body += "<br>";
        body += "--------------------------------------------------------";
        body += "<br>";
        
        String email = null;
        String title = "Mensaje de Usuario Conductor ";
        if(sys.getCorporationId() == CorporationData.LILLY) {
        	email = AlertConstants.EMAIL_LILLY;
        	title += "Lilly";
        }else if(sys.getCorporationId() == CorporationData.MONSANTO && userSessionData.getDriverData().getCountryName().equals(CountryData.ARGENTINA)) {
        	email = AlertConstants.EMAIL_MONSANTO_AR;
        	title += "Monsanto";
        }
        /*mailSender.send(
        		email,
        		AlertConstants.MAILSERVER,
        		AlertConstants.FROMNAME_DC,
        		AlertConstants.FROM_DC,
        		AlertConstants.FROMPASSWORD_DC,
        		title,body);*/
            
        return mapping.findForward("send"); 
    }
}
