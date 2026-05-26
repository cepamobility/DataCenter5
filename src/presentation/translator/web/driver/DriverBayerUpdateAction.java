package presentation.translator.web.driver;

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
import business.driver.DriverABMFacade;

import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverBayerData;
import communication.language.Text;

public class DriverBayerUpdateAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Throwable {
        
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
        Text messages = sys.getText();

        DynaActionForm updateData = (DynaActionForm) form;
        String id = (String) updateData.get("id");
        String date = (String) updateData.get("date");
        String month = (String) updateData.get("month");
        String year = (String) updateData.get("year");
        String state = (String) updateData.get("state");
        String city = (String) updateData.get("city");
        String email = (String) updateData.get("email");
        String ddd = (String) updateData.get("ddd");
        String phone = (String) updateData.get("phone");
        
        DriverBayerData data = sys.getDriverReportFacade().findDriverBayerByPrimaryKey(new Integer(id), userSessionData);
        data.setStateOrRegion(messages.getText(state));
        data.setCity(city);
        data.setLicenseExpiry(Util.getDate(date, month, year));
        data.setEmail(email);
        
        if (Util.empty(ddd)) 
        	data.setPhone(phone);
        else 
        	data.setPhone(ddd + " - " + phone);
        
        DriverABMFacade driverABM = sys.getDriverABMFacade();
        driverABM.driverBayerUpdate(data, "Atualização realizada pelo condutor.", userSessionData);
        
        String url = "?id="+data.getId();
	    userSessionData.setUrl(url);
	    return mapping.findForward("success");          
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
    	return null;
    }    
	
}
