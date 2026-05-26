package presentation.translator.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import business.SystemAccess;

import communication.administration.user.UserSessionData;
import communication.alert.AlertConstants;
import communication.exception.DCException;
import communication.util.MailSender;


public class SendErrorReportAction extends AbstractAction {

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("cancel");
    }

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
        MailSender mailSender=new MailSender();
        DynaActionForm forms=(DynaActionForm)form;
        
        HttpSession session = request.getSession();
        SystemAccess sys=(SystemAccess)session.getAttribute("SystemAccess");
        UserSessionData userRequest=sys.getUserSessionData();
        String userLoginName=userRequest.getFilter().getLoginName();
        
        String body="";
        
        body += "--------------------------------------------------------";
        body += "<br>";
        if(session.getAttribute("exception") != null && session.getAttribute("exception") instanceof DCException) {
            DCException dc = (DCException)session.getAttribute("exception");
            if(dc.getUser() != null) {
            	body += "<b>User:</b> "+dc.getUser();
                body += "<br>";
            }
            if(dc.getCorp() != null) {
            	body += "<b>Corp:</b> "+dc.getCorp();
                body += "<br>";
            }
            body += "<b>Class:</b> "+dc.getClassName();
            body += "<br>";
            body += "<b>Func:</b> "+dc.getFunc();
            body += "<br>";
            body += "<b>Parameters</b>";
            body += "<br>";
            for(int i = 0; i < dc.getParameters().length; i++) {
            	body += "|----"+String.valueOf(dc.getParameters()[i]);
                body += "<br>";
            }
            Throwable thw = (Throwable) dc;
            body += "<b>Exc:</b> "+ 
            	((thw.getMessage() != null)?thw.getMessage():"");
            body += "<br>";
            body += "<b>Exc msg:</b> "+
            	((thw.getLocalizedMessage() != null) ? thw.getLocalizedMessage():"");
            body += "<br>";
            if (dc != null && dc.getStackTrace() != null){
            	StackTraceElement[] elements = dc.getStackTrace();
            	for(int i = 0; i < elements.length; i++) {
            		body +="- "+elements[i].toString();
            		body += "<br>";
            	}
            }
            
            if (dc != null && dc.getMessage() != null) System.out.println(dc.getMessage());
        }else {
        	body +="<b>User:</b> ["+userLoginName+"]";
            body += "<br>";
        	body +="<b>Message : </b>["+session.getAttribute("exception")+" ]";
            body += "<br>";
            body += "<br>";
        }
        body += "--------------------------------------------------------";
        body += "<br>";
        body += "<b>User inserted message</b>";
        body += "<br>";
        body += forms.getString("body");
        body += "<br>";
        body += "--------------------------------------------------------";
        body += "<br>";
        
        
        mailSender.send(
        		AlertConstants.EMAIL_JUAN,
        		AlertConstants.FROMNAME_DC,
        		AlertConstants.FROM_DC,
        		"FEEDBACK REPORT",body);
            
        return mapping.findForward("sendreportsuccessful"); 
    }
}
