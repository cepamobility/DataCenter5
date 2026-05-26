package presentation.translator.web.event;

import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;

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
import business.event.EventABMFacade;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.CountryData;
import communication.alert.AlertConstants;
import communication.event.tables.EventDataType;
import communication.event.tables.EventMainAttributes;
import communication.language.Text;
import communication.util.MD5;
import communication.util.MailSender;

/**
 * @author jrodriguez
 */
public class EventSendRecomendationAction extends AbstractAction {
	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
				
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        DynaActionForm data = (DynaActionForm) form;
        if (Util.empty(data.getString("recomendation"))) {
            session.setAttribute("Msg", messages.getText("event.error.emptyrecomendation"));
            
            return mapping.findForward("back");
        }

        EventDataType event = sys.getEventReportFacade().findEventByPrimaryKey(
        		new Integer(data.getString("id")),sys.getUserSessionData());
        event.setRecomendation(data.getString("recomendation"));
        if(!Util.empty(data.getString("drivermail")) && !sys.isCorporation(CorporationData.JJ)) {
            try {
                MailSender sender = new MailSender();
                String title = messages.getText("email.recomendation.recived.title");
                String body = messages.getText("email.recomendation.recived.body1")+" "+
                	event.getDriver().getFirstName()+" "+event.getDriver().getLastName();
                body += "<br><br>";
                
                body += messages.getText("email.recomendation.recived.body2")+" "+
            	Util.formatDate(event.getDate())+" "+
            	messages.getText("email.recomendation.recived.body3");
                body += "<br><br>";
                body += event.getRecomendation();
                body += "<br><br>";
                body += messages.getText("email.recomendation.recived.body4")+": ";
                body += (Util.empty(event.getEvitability())) ? messages.getText("generic.messages.nodata") :messages.getText(event.getEvitability());
                body += "<br><br>";
                body += messages.getText("email.recomendation.recived.body5");
                body += "<br><br><br><br>";
                body += messages.getText("email.recomendation.recived.body6");
                body += "<br>";
                body += messages.getText("email.recomendation.recived.body7");
                body += "<br>";
                body += "<br>";
               
               
    			Collection to = new LinkedList();
    			to.add(data.getString("drivermail"));
    			Collection cc = new LinkedList();
    			Collection bcc = new LinkedList();
    			bcc.add(AlertConstants.BCC);
    			boolean sended = false;
    			int count = 0;
    			while(!sended && count < 10) {
    				try {
    					sended = sender.sendImageMandrill(to,
    							"CEPA DataCenter", 
    							AlertConstants.EMAIL_SUPPORT, 
    							title, Util.emailTranslation(body),new LinkedList<String>(),
    							new LinkedList<String>(), 
    							"", "", bcc,null);
    				}catch (Exception e) {
    					e.printStackTrace();
    				}
    				count++;
    			}

            }catch(Exception e) {
            	e.printStackTrace();
            }
        }
        
        EventABMFacade eventABM = sys.getEventABMFacade();
        eventABM.eventUpdate(event,EventMainAttributes.SUPERVISED,sys.getUserSessionData(),"recomendation");
        
        session.removeAttribute("EventRecomendationForm");
        session.setAttribute("eventId",event.getId());
        
        return mapping.findForward("success");
        
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        session.removeAttribute("EventRecomendationForm");
        session.setAttribute("eventId",new Integer(((DynaActionForm) form).getString("id")));
        return mapping.findForward("cancel");
    }
}
