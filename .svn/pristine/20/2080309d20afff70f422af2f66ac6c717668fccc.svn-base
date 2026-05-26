/*
 * Created on 24-feb-2006
 */
package presentation.translator.web.vehicle;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.administration.user.UserSessionData;
import communication.language.Text;


public class VehicleContractList1Action extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
        Text messages = sys.getText();
        
        VehicleReportForm reportForm = (VehicleReportForm)form;
        
   		Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
     	Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
     	String type = reportForm.getType();
   	    int status = Integer.parseInt(reportForm.getStatus());

     	Collection vehicles = sys.getVehicleReportFacade().
     		getVehiclesWithDriver(type,status,divOrgs,divGeos,userSessionData);
	    userSessionData.setUrl("vehicle/contractListResult.jsp");
	    userSessionData.setValue(vehicles);
	    
	    return mapping.findForward("next");
    }
    
    /**
     * 
     */
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

} 
    


