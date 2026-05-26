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
import org.apache.struts.action.DynaActionForm;
import org.jfree.report.JFreeReport;

import presentation.report.ReportUtil;
import presentation.report.activity.HeavyReportFactory;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.activity.report.HeavyReportData;
import communication.language.Text;


/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HeavyReportAction extends AbstractAction {

    public ActionForward cancel(HttpSession session, ActionMapping mapping, ActionForm form) {
        return null;
    }

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        String activity = ((DynaActionForm)form).getString("activity");
        String driver = ((DynaActionForm)form).getString("driver");
        HeavyReportData data = sys.getActivityReportFacade().getReportHeavy(new Integer(activity),new Integer(driver),messages,sys.getUserSessionData());
    
        HeavyReportFactory factory = new HeavyReportFactory(data ,form,sys,messages);
        JFreeReport report = factory.createReport();

        new ReportUtil().executeReport(mapping,response,report,"PDF",session,"heavy",null,false);
        session.setAttribute("id",activity);
        session.setAttribute("idDriver",driver);
        return null;
    }    

} 
    


