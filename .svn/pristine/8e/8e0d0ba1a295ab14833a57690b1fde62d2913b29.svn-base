/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.alert;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
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
import business.alert.AlertABMFacade;
import business.datatype.DataTypeReportFacade;

import communication.administration.user.UserSessionData;
import communication.datatype.tables.AccidentClasificationDataType;
import communication.datatype.tables.FineQualifyDataType;
import communication.datatype.tables.IncidentClasificationDataType;
import communication.language.Text;

public class ContractorAddMailsAction extends AbstractAction {
	
    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        
    	HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
		UserSessionData userSessionData = sys.getUserSessionData();
		
		DynaActionForm formData = (DynaActionForm) form;	
		Collection contractorsMails = Util.stringToCollection(formData.getString("list"));
		String divOrgId=formData.getString("addmail");
		session.setAttribute("addmail",divOrgId);
		/*String message = validate(contractorsMails);
		if(message != null) {				
			session.setAttribute("Msg", messages.getText(message));		    
			return mapping.findForward("back");		
		}*/
    	
    	AlertABMFacade alertABM = sys.getAlertABMFacade();
		alertABM.createUpdateContractorsMails(contractorsMails,userSessionData);
		
		
        return mapping.findForward("success");
        
    }
    
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
    
    private String validate(Collection contractorsMails) {     
    	String message = null;
    	try {
			Iterator it = contractorsMails.iterator(); 
			String emailValue;
			while (it.hasNext()){
				String[] values = ((String)it.next()).split("	");
				if ((values.length==2)&&(!values[1].equals("mailna"))){
					if(!Util.checkEmail(values[1].trim()))
						return message = "generic.alert.invalidemail";
				}
			}
			
    	} catch (Exception e) {
    		message = "generic.alert.invaliddata";
    		return message;
    	}
	
		return message;
    } 

} 
    


