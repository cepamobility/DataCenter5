/*
 * Created on 28-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.activity.schedule;

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
public class ActivitySearchAction extends AbstractAction { 

	public ActionForward action(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
			
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        /* Definición de Variables */
			int first,count, pageNumber;
			String type, country, state, city;
			Date initialDate, lastDate;
			ActivityReportFacade activityReportFacade;
			ActivitySearchForm activitySearchForm;
			UserSessionData userSessionData;
			
			userSessionData = sys.getUserSessionData();		
			activitySearchForm = (ActivitySearchForm) form;
            activityReportFacade = sys.getActivityReportFacade();    

    		String message = activitySearchForm.validate(userSessionData);

    		if(message != null) {				
    		    //Some attribute data is invalid	
    			//Si en el struts config indico que el form es de sesion la línea de abajo es inneseraria
    			session.setAttribute("Msg", messages.getText(message));
            	activitySearchForm.setPageNum("1");
            	activitySearchForm.setResetPN("false");    			
                session.setAttribute("activityForm", activitySearchForm);
    			session.setAttribute("activitySearchList",new ListResult(new LinkedList(),0));
    			return mapping.findForward("ActivityList");    			
    		}
    		
            initialDate = activitySearchForm.getInitialDate();
            lastDate = activitySearchForm.getLastDate();
            type = (!Util.empty(activitySearchForm.getType())) ? activitySearchForm.getType() : null;
            country = (!Util.empty(activitySearchForm.getCountry())) ? activitySearchForm.getCountry() : null;
            city = (!Util.empty(activitySearchForm.getCity())) ? activitySearchForm.getCity() : null;
            
            state = (!Util.empty(activitySearchForm.getState())) ? activitySearchForm.getState() : null;
            
            // Los gerentes solo ven actividades dictadas
            /*if (userSessionData.isGerente()) {
            	state = CEPAActivityConstants.DICTADA;
            }
            */
            if (activitySearchForm.getResetPN().equals("true")) {
            	activitySearchForm.setPageNum("1");
            	activitySearchForm.setResetPN("false");
            }            
                		
            pageNumber = (!Util.empty(activitySearchForm.getPageNum()))? Integer.parseInt(activitySearchForm.getPageNum()):1;
            first =  (!Util.empty(activitySearchForm.getPageNum())) ? (pageNumber -1)*10: 0;
            count = (Boolean.parseBoolean(activitySearchForm.getAll())) ? -1 : 10;
            
            ListResult result = activityReportFacade.simpleSearch(
            		initialDate,lastDate,type,country,city,state,first,count,userSessionData);

			session.setAttribute("activitySearchList", result);          
            session.setAttribute("activityForm", activitySearchForm);
            
            return mapping.findForward("ActivityList");
			//return mapping.findForward((String)session.getAttribute("target"));
	}
	
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }    

}
