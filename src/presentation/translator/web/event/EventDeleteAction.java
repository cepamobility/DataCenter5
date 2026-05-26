/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.event;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.jboss.logging.Logger;

import persistence.log.LoggerFormatting;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.event.EventABMFacade;

import communication.exception.AlreadyExistsException;
import communication.exception.CommunicationProblemException;
import communication.exception.DCSecurityException;
import communication.exception.DeslogedException;
import communication.language.Text;

/**
 * @author jrodriguez
 */
public class EventDeleteAction extends Action {
	/**
	 * Log this class
	 */
	private Logger logger=Logger.getLogger(EventDeleteAction.class);
	private LoggerFormatting logFormatting=new LoggerFormatting();
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Text messages=null;

		HttpSession session = request.getSession();

		SystemAccess sys = (SystemAccess) session.getAttribute("SystemAccess");
		try {
				
			String target=(String)session.getAttribute("target");
			session.removeAttribute("target");

			if(isCancelled(request)) {
			    return mapping.findForward("cancel");
			}
				
			if (sys == null) {
				session.setAttribute("Msg",Text.getMessage("generic.error.deslogued"));
				return mapping.findForward("logout");
			}

			messages=sys.getText();
            DynaActionForm eventDeleteForm = (DynaActionForm) form;
            Collection events = Util.stringToCollection((String)eventDeleteForm.get("event"));
				
            try {
                EventABMFacade eventABM = sys.getEventABMFacade();
                eventABM.deleteEvent(events,sys.getUserSessionData(),"delete");
                return mapping.findForward("deleted");
                
			} catch (DeslogedException deslogued) {
				session.setAttribute("Msg",messages.getText("generic.error.delogued"));
				return mapping.findForward("exception");
			} catch (AlreadyExistsException already) {
				session.setAttribute("target",target);
				session.setAttribute("Msg", messages.getText("generic.error.deslogued"));
				return mapping.findForward("exception");
			}
            
		} catch (CommunicationProblemException comm) {
			request.setAttribute("exception",comm);
			return mapping.findForward("exception");
		} catch(DCSecurityException security){
			return mapping.findForward("insufficientpermissions");
		} catch(RemoteException remote){
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
				request.setAttribute("exception",remote);
				logger.fatal(logFormatting.formattingText(remote,"ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse","RemoteException",sys.getUserSessionData().getFilter().getLoginName()),remote);
				return mapping.findForward("exception");
			}else{
				logger.fatal(logFormatting.formattingText(remote,"ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse","SecurityException",sys.getUserSessionData().getFilter().getLoginName()),remote);
				return mapping.findForward("insufficientpermissions");
			}
		}catch (Exception e) {
			request.setAttribute("exception",e);
			logger.error(logFormatting.formattingText(e,"ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse","Exception",sys.getUserSessionData().getFilter().getLoginName()),e);
			return mapping.findForward("exception");
		}
	}
}
