package presentation.translator.web.event;

import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import persistence.util.Util;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.event.EventABMFacade;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.CountryData;
import communication.administration.user.UserSessionData;
import communication.alert.AlertConstants;
import communication.driver.tables.DriverMainAttributes;
import communication.event.tables.EventDataType;
import communication.language.Text;
import communication.security.SecurityConstants;
import communication.util.MailSender;

/**
 * @author jrodriguez
 */
public class EventCreateAction extends AbstractAction {
	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
				
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
        Text messages = sys.getText();

        EventForm createData = (EventForm) form;
        
        System.out.println("Entre en el EventCreateAction con el usuario: "+userSessionData.getFilter().getLoginName());

        String message = createData.validate(userSessionData);
        
        System.out.println("EventCreateAction: valide todos los campos del evento");
        
        if (message != null) {
            session.setAttribute("EventForm", createData);
            session.setAttribute("Msg", messages.getText(message));            
            userSessionData.setUrl("/event/EventCreate.jsp?type=create");
            System.out.println("EventCreateAction: encontre un error de validacion de campos: "+message);
            return mapping.findForward("layout");
        }
        
        System.out.println("EventCreateAction: no hubo errores de validacion de campos, voy a crear el evento en la BD");
        
        EventABMFacade eventABM = sys.getEventABMFacade();
        EventDataType eventData = createData.getEventDataType(sys);
        eventABM.eventCreate(eventData,userSessionData,createData.getComment());
        
        userSessionData.setUrl("/success.jsp?entity=event.create");
        return mapping.findForward("layout");
        
	}

	public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        ((SystemAccess)session.getAttribute("SystemAccess")).getUserSessionData().setUrl("/event/EventList.jsp");
        return mapping.findForward("layout");
    }
}
