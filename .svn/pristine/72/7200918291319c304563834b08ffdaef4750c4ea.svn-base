package presentation.translator.web.event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.event.EventABMFacade;

import communication.alert.AlertConstants;
import communication.event.tables.EventMainAttributes;
import communication.language.Text;
import communication.util.MailSender;

/**
 * @author fcarriquiry
 */
public class NewEventSuperviseAction extends AbstractAction {
	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
				
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        NewEventSuperviseForm createData = (NewEventSuperviseForm) form;
        String message = createData.validate(sys.getUserSessionData());
        if (message != null) {
            session.setAttribute("NewEventForm", createData);
            session.setAttribute("eventId", new Integer(createData.getId()));
            session.setAttribute("Msg", messages.getText(message));
            return mapping.findForward("back");
        }

        int status = EventMainAttributes.SUPERVISED;
        if(createData.isChecked(createData.getRecomendationCheck())) {
            MailSender sender = new MailSender();
            String title = messages.getText("email.recomendation.title") + " - " +
            	messages.getText("generic.data.event")+" "+createData.getId();
            String body = messages.getText("email.recomendation.body"); 
            body += "&nbsp;<a href=\"https://" + AlertConstants.DC_DOMAIN +"/datacenter/home.jsp?refer=/event/EventView.jsp?eventId="+
            createData.getId()+"\">"+messages.getText("generic.data.event")+"</a>";
            
           /* sender.send(createData.getEmailrecomendation(),
            		AlertConstants.MAILSERVER,
            		AlertConstants.FROMNAME_DC,
            		AlertConstants.FROM_DC,
            		AlertConstants.FROMPASSWORD_DC,
            		title,Util.emailTranslation(body),AlertConstants.BCC);*/
            status = EventMainAttributes.PENDING_RECOMENDATION;
        }

        EventABMFacade eventABM = sys.getEventABMFacade();
        eventABM.eventUpdate(createData.getEventDataType(sys),status,sys.getUserSessionData(),createData.getComment());

        return mapping.findForward("cancel");
        
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("cancel");
    }
}
