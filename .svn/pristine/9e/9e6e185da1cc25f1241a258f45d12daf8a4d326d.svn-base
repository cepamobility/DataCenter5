package presentation.translator.web.alert;

import java.util.Date;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.activity.ActivityReportFacade;

import communication.administration.user.UserSessionData;
import communication.language.Text;
import communication.util.ListResult;

/**
 * @author adocarmo
 */
public class AlertSearchAction extends AbstractAction { 

	public ActionForward action(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
			
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        /* Definición de Variables */
			int first,count, pageNumber;
			String type, country, state, city;
			Date initialDate, lastDate;
			ActivityReportFacade activityReportFacade;
			AlertSearchForm alertSearchForm;
			UserSessionData userSessionData;
			
			userSessionData = sys.getUserSessionData();		
			alertSearchForm = (AlertSearchForm) form;
            activityReportFacade = sys.getActivityReportFacade();    

    		String message = alertSearchForm.validate(userSessionData);

    		if(message != null) {				
    		    //Some attribute data is invalid	
    			//Si en el struts config indico que el form es de sesion la línea de abajo es inneseraria
    			session.setAttribute("Msg", messages.getText(message));
            	alertSearchForm.setPageNum("1");
            	alertSearchForm.setResetPN("false");    			
                session.setAttribute("alertForm", alertSearchForm);
    			session.setAttribute("alertSearchList",new ListResult(new LinkedList(),0));
    			session.setAttribute("url", "alert/alertList.jsp");
    			return mapping.findForward("layout");    			
    		}
    		
            initialDate = alertSearchForm.getInitialDate();
            lastDate = alertSearchForm.getLastDate();
            type = (!Util.empty(alertSearchForm.getType())) ? alertSearchForm.getType() : null;
            country = (!Util.empty(alertSearchForm.getCountry())) ? alertSearchForm.getCountry() : null;
            city = (!Util.empty(alertSearchForm.getCity())) ? alertSearchForm.getCity() : null;
            
            state = (!Util.empty(alertSearchForm.getState())) ? alertSearchForm.getState() : null;

            if (alertSearchForm.getResetPN().equals("true")) {
            	alertSearchForm.setPageNum("1");
            	alertSearchForm.setResetPN("false");
            }            
                		
            pageNumber = Integer.parseInt(alertSearchForm.getPageNum());
            first =  (!Util.empty(alertSearchForm.getPageNum())) ? (pageNumber -1)*10: 0;
            count = (Boolean.parseBoolean(alertSearchForm.getAll())) ? -1 : 10;
            
            ListResult result = activityReportFacade.simpleSearch(
            		initialDate,lastDate,type,country,city,state,first,count,userSessionData);

			session.setAttribute("alertSearchList", result);          
            session.setAttribute("alertForm", alertSearchForm);
            session.setAttribute("url","alert/alertList.jsp");
            return mapping.findForward("layout");
			
	}
	
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }    

}
