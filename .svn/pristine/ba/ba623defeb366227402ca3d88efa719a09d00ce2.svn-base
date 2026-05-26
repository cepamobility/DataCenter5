/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.alert.AlertConstants;
import communication.util.MailSender;

/**
 * @author jrodriguez
 */
public abstract class AbstractAction extends Action {

	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            request.setAttribute("className",getClass().getName());
            if(isCancelled(request)) {
                return cancel(request.getSession(),mapping,form);
            }
            ActionForward forward = action(mapping,form,request,response);        
            request.removeAttribute("className");
            return forward;
        }catch(Throwable e) {
        	e.printStackTrace();
            throw new Exception(e);
        }
	}

    public abstract ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form);

    public abstract ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable;

	public void sendEmail(String subject, Collection addressTo, MailSender sender, Collection bcc, String body, Collection<String> files, Collection<String> names, SystemAccess sys) {
		sendEmail(subject, addressTo, sender, new LinkedList(), bcc, body, files, names, sys);
	}
	
	public void sendEmail(String subject, Collection addressTo, MailSender sender, Collection cc, Collection bcc, String body, Collection<String> files, Collection<String> names, SystemAccess sys) {
		Iterator toIt = addressTo.iterator();
		while(toIt.hasNext()) {
			Collection to = new LinkedList();
			to.add(toIt.next());
			boolean sended = false;
			int count = 0;
			while(!sended && count < 10) {
				try {
					sended = sender.sendImageMandrill(to,
							"CEPA DataCenter", 
							AlertConstants.EMAIL_SUPPORT, 
							subject, body, files,
							names, 
							"", "", bcc,null);
				}catch (Exception e) {
					e.printStackTrace();
				}
				count++;
			}
		}
	}
 

}
