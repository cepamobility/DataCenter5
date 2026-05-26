/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
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

import communication.language.Text;
import communication.datatype.tables.UnconformityDataType;
import communication.driver.tables.UnconformityData;

/**
 * @author jrodriguez
 */
public class AddDriverUnconformityAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        DynaActionForm formData = (DynaActionForm)form;
        
        Integer driverId = new Integer(formData.getString("driver"));
        
		String message = validate(formData);
		if(message != null) {
			sys.getUserSessionData().setMsg(messages.getText(message));
		    return mapping.findForward("next");
		}else {
            UnconformityData data = new UnconformityData();
            data.setDate(Util.getDate(formData.getString("date"),formData.getString("month"),formData.getString("year")));
            data.setType(new UnconformityDataType(formData.getString("type")));
            data.setObservations(formData.getString("observations"));

            sys.getUserSessionData().setUrl("driver/DriverView.jsp?id="+formData.getString("driver"));
            sys.getDriverABMFacade().createUnconformity(driverId,data,sys.getUserSessionData());
			return mapping.findForward("next");
        }	
	}

    private String validate(DynaActionForm formData) {
    	if(Util.getDate(formData.getString("date"),formData.getString("month"),formData.getString("year")) == null) {
    		return "driver.unconformities.dateerror";
    	}
    	if(Util.empty(formData.getString("type"))) {
    		return "driver.unconformities.typeerror";
    	}
		return null;
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        DynaActionForm formData = (DynaActionForm)form;
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        sys.getUserSessionData().setUrl("driver/DriverView.jsp?id="+formData.getString("id"));
        return mapping.findForward("next");
    }
}
