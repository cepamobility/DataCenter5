/*
 * Created on 31-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.driver;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.driver.DriverPointReportFactory;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.administration.corporation.CorporationReportFacade;
import business.alert.handler.Util;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.driver.report.DriverPointReportData;
import communication.driver.tables.DriverPointData;
import communication.language.Text;
/**
 * @author jrodriguez
 */
public class DriverPointReportAction extends AbstractAction {
	
	public ActionForward action(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Throwable {

		HttpSession session = request.getSession();
	    SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

	    DriverPointReportForm reportForm = (DriverPointReportForm)form;
   		if(Integer.parseInt(reportForm.getOperator()) > 0 && !Util.isNumber(reportForm.getValue())) {
			sys.getUserSessionData().setMsg("pointreport.error.value");
			sys.getUserSessionData().setUrl("/driver/driverPointQuery.jsp");
			return mapping.findForward("layout");
		}

        JFreeReportBoot.getInstance().start();
        JFreeReport report = getReport(sys,reportForm);

        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"DriverPoints",true);
        return fwd;
        
    }
    
    public JFreeReport getReport(SystemAccess sys,DriverPointReportForm reportForm) throws Exception {
       	try{
       		Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
       		Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
       		Text messages = sys.getText();
       		
       		String operation = null;
       		int operator = Integer.parseInt(reportForm.getOperator());
       		if(operator > 0) {
       			switch(operator) {
	       			case 1:
	       				operation = " <= "+reportForm.getValue();
	       				break;
	       			case 2:
	       				operation = " = "+reportForm.getValue();
	       				break;
	       			case 3:
	       				operation = " >= "+reportForm.getValue();
	       				break;
       			}
       		}
       		boolean resumed = reportForm.isResumed();
       		CorporationReportFacade corporationReport = sys.getCorporationReportFacade();
       		Collection<DriverPointData> values = corporationReport.getDriverPoints(reportForm.getFirstName(), reportForm.getLastName(), Integer.parseInt(reportForm.getStatus()), divOrgs, divGeos, operation, resumed, sys.getUserSessionData());
            
            DriverPointReportData reportData = new DriverPointReportData(values,sys.getUserSessionData(),resumed);
            reportData.setMessages(messages);
            if(resumed && sys.isCorporation(CorporationData.BAYER_MX)) {
            	reportData.setElectronics(corporationReport.getElectroincs(sys.getUserSessionData()));
            	reportData.setMitigationPlans(sys.getDriverReportFacade().getMitigationPlans(sys.getUserSessionData()));
            }
            DriverPointReportFactory factory = new DriverPointReportFactory(messages.getText("query.pointquery.title"),reportData,reportForm,sys,messages,resumed);
            
            return factory.createReport();
      	}catch(Exception e){
       		e.printStackTrace();
    		throw e;
       	}       	    	
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();

        DynaActionForm driverForm = (DynaActionForm) form;
        String driver = (String) driverForm.get("driver");
        userSessionData.setUrl("/driver/DriverView.jsp?id="+driver);
        return mapping.findForward("next");
    }
}
