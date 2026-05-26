/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.activity.schedule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.language.Text;


/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class ActivityQueryAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        ActivityExpiryForm reportForm = (ActivityExpiryForm)form;
        
   		String message = reportForm.validate(sys.getUserSessionData());
   		
		if(message != null) {				
		    //Some attribute data is invalid	
			//Si en el struts config indico que el form es de sesion la línea de abajo es inneseraria
			session.setAttribute("Msg", messages.getText(message));
			return mapping.findForward("back");    			
		}   		
     
        JFreeReportBoot.getInstance().start();
        JFreeReport report = getReport(sys,reportForm,1);
        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"ActivityExpiry",true);
        return fwd;
        
    }
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
    public abstract JFreeReport getReport(SystemAccess sys,ActivityExpiryForm reportForm,int page) throws Exception;

} 
    


