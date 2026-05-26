/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.driver;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.BindingProvider;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import presentation.translator.web.util.AbstractAction;
import ws.assessment.datacenter.AssessmentWSEJB;
import ws.assessment.datacenter.AssessmentWSEJBService;
import business.SystemAccess;

import communication.security.SecurityConstants;


public class AssessmentReportAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        DynaActionForm reportForm = (DynaActionForm)form;

        byte[] out = getReport(sys,reportForm.getString("user"),reportForm.getString("assessment"),reportForm.getString("report"),reportForm.getString("format"));
        if(Integer.parseInt(reportForm.getString("format")) == 1) {
	        response.setHeader("Content-Type", "application/pdf");
	        response.setHeader("Content-Disposition","inline; filename=\"assessment.pdf\"");
	        ServletOutputStream outStream = response.getOutputStream();
	        outStream.write(out);
        }else {
            session.setAttribute("resp",new String(out));
            return mapping.findForward("report");
        }
        return null;
    }

    private byte[] getReport(SystemAccess sys, String user,String assessment, String report, String format) {
    	try {
	    	AssessmentWSEJBService service = new AssessmentWSEJBService();
			AssessmentWSEJB port = service.getAssessmentWSEJBPort();
	
			// Seteamos la URL del WebService Remoto:
			String endpointURL = SecurityConstants.WS_ASSESSMENT_DC5;
			BindingProvider bp = (BindingProvider)port;
			bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);
			
			return port.getUserResultReport(new Integer(assessment),user,sys.getUserSessionData().getLenguage(),new Integer(format));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
		return null;
	}

	public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
} 
    


