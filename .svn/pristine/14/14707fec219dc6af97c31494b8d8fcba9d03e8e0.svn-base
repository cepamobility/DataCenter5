package presentation.translator.web.activity.schedule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.table.TableModel;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportFactory;
import presentation.report.ReportUtil;
import presentation.report.activity.CepaSystemCertificateFactory;
import presentation.report.activity.CepaSystemReportFactory;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.activity.ActivityReportFacade;

import communication.activity.tables.CEPAActivityScheduleRegistryData;

public class CepaSystemCertificateAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	 
        HttpSession session = request.getSession();
        DynaActionForm reportForm = (DynaActionForm)form;

        String date=reportForm.getString("date");
    	String driver=reportForm.getString("driver");
           	
		ReportFactory factory = new CepaSystemCertificateFactory(form);

		JFreeReport report = factory.createCertificate(driver, date);	
        JFreeReportBoot.getInstance().start();
        return new ReportUtil().executeReport(mapping,response,report,"PDF",session,"activity",true);
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
} 
    


