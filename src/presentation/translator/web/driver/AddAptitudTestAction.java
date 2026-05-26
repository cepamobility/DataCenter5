/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.driver;

import java.util.Calendar;
import java.util.Date;

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

import communication.driver.tables.AptitudTestData;
import communication.language.Text;

/**
 * @author jrodriguez
 */
public class AddAptitudTestAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        DynaActionForm testData = (DynaActionForm)form;
        
        Integer driverId = new Integer(testData.getString("driver"));
        
		String message = validate(testData);
		if(message != null) {
		    session.setAttribute("Msg", messages.getText(message));
            sys.getUserSessionData().setUrl("driver/aptitudTest.jsp?id="+testData.getString("driver"));
			return mapping.findForward("next");
		}else {
            AptitudTestData data = new AptitudTestData();
            Date date = Util.getDate(testData.getString("testDate"),testData.getString("testMonth"),testData.getString("testYear"));
            data.setDate(date);
            
            if (testData.getString("apt").equals("pending")){
            	data.setApt(false); // No apto
            	data.setPending(true); // Pendinete de Resultado
            }else{
            	data.setApt(testData.getString("apt").equals("yes"));
            	data.setPending(false);
            }
            
            data.setExpiry(Util.getDate(testData.getString("expiryDate"),testData.getString("expiryMonth"),testData.getString("expiryYear")));
        	data.setExternal(testData.getString("external").equals("1"));

            if(sys.getDriverReportFacade().existsAptitudTest(driverId, String.valueOf(date.getTime()), sys.getUserSessionData())) {
    		    session.setAttribute("Msg", messages.getText("driver.aptitudtest.alreadyexists"));
                sys.getUserSessionData().setUrl("driver/aptitudTest.jsp?id="+testData.getString("driver"));
    			return mapping.findForward("next");
            }else {
	            session.removeAttribute("AddAptitudTestForm");
	            DriverABMFacade driverABM = sys.getDriverABMFacade();
	            driverABM.createAptitudTest(driverId,data,sys.getUserSessionData());
	
	            sys.getUserSessionData().setUrl("driver/DriverView.jsp?id="+testData.getString("driver"));
				return mapping.findForward("next");
            }
        }	
	}

    private String validate(DynaActionForm testData) {
    	if(Util.getDate(testData.getString("testDate"),testData.getString("testMonth"),testData.getString("testYear")) == null) {
    		return "driver.aptitudtest.errordate";
    	}
    	if(Util.getDate(testData.getString("expiryDate"),testData.getString("expiryMonth"),testData.getString("expiryYear")) == null) {
    		return "driver.aptitudtest.errorexpiry";
    	}
        return null;
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {

    	session.removeAttribute("AddAptitudTestForm");
        DynaActionForm testData = (DynaActionForm)form;

        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        sys.getUserSessionData().setUrl("driver/DriverView.jsp?id="+testData.getString("driver"));
        return mapping.findForward("next");
    }
}
