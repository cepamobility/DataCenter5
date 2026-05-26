package presentation.translator.web.user;

import javax.servlet.ServletException;
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
import business.driver.DriverReportFacade;

import communication.driver.tables.DriverData;
import communication.language.Text;

public class CDSUserCreateAction extends AbstractAction {

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("cancel");
    }

    public ActionForward action(ActionMapping mapping, ActionForm createForm, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        DynaActionForm createData = (DynaActionForm) createForm;
        String driverId = createData.getString("driver");
        String password = null;
        String language = createData.getString("language");
        String loginname = createData.getString("loginname");

        try {
            password = createData.getString("password");
            String confirm = createData.getString("confirm");
            if(!Util.empty(password) && !Util.empty(confirm)) {
                if(!password.equals(confirm)) {
                    session.setAttribute("Msg",messages.getText("generic user.userdata.passconfirm"));
                    session.setAttribute("driver",driverId);
                    session.removeAttribute("DriverUserCreateForm");
                    return mapping.findForward("error");
                }
            } else {
                session.setAttribute("Msg",messages.getText("generic.user.userdata.pass.isempty"));
                session.setAttribute("driver",driverId);
                session.removeAttribute("DriverUserCreateForm");
                return mapping.findForward("error");
            }

            DriverReportFacade driverReport = sys.getDriverReportFacade();
            DriverData driver = driverReport.findDriverByPrimaryKey(new Integer(driverId),sys.getUserSessionData());
            password = sys.getUserABMFacade().userCDSCreate(driver,loginname,password,language,sys.getUserSessionData());
            
            
            return mapping.findForward("next");
                
        }catch(Exception e) {
            Throwable thw = (e instanceof ServletException) ? ((ServletException)e).getRootCause() : e;
            while (thw.getCause() != null) {
                thw = thw.getCause();
            }
            if (thw.getClass().toString().indexOf("AlreadyExistsException") > 0) {
                session.setAttribute("Msg",messages.getText("user.error.alreadyexist"));
                return mapping.findForward("error");
            }else {
                throw e;
            }
        }
    }

}