/*
 * Created on 24-feb-2006
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
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.activity.CepaSystemReportFactory;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.activity.ActivityReportFacade;

import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.activity.tables.CheckListData;
import communication.language.Text;

public class CepaSystemReportAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	 
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        DynaActionForm reportForm = (DynaActionForm)form;
        Text messages = sys.getText();

        String idActivity=reportForm.getString("activity");
    	String idDriver=reportForm.getString("driver");
           	
        ActivityReportFacade facade = sys.getActivityReportFacade();
        CEPAActivityScheduleRegistryData registryData = facade.getCompleteResult(new Integer(idActivity),new Integer(idDriver),sys.getUserSessionData());
        
		CheckListData checkList = null;
		if(registryData.getActivity().getDefinition().getResourceKeyName().equals(CEPAActivityConstants.CEPA_SYSTEM_COURSE_1_LIVIANOS)) {
			checkList = facade.findCheckList(new Integer(40),sys.getUserSessionData());	
		}else if(registryData.getActivity().getDefinition().getResourceKeyName().equals(CEPAActivityConstants.CEPA_SYSTEM_COURSE_1_PESADOS)) {
			checkList = facade.findCheckList(new Integer(35),sys.getUserSessionData());	
		}else {
			checkList = facade.findCheckList(new Integer(36),sys.getUserSessionData());	
		}
		
		CEPAActivityScheduleData activitySchedule = facade.findCEPAActivityScheduleByPrimaryKey(
        		new Integer(idActivity)	,sys.getUserSessionData());

		CepaSystemReportFactory factory = new CepaSystemReportFactory(registryData,activitySchedule,"CepaSystem",form,checkList,sys);

		JFreeReport report = factory.createReport();	
        JFreeReportBoot.getInstance().start();
        return new ReportUtil().executeReport(mapping,response,report,"PDF",session,"activity",
        		messages.getText("query.activity.generalreport.reporttitle"),
        		messages.getText("query.activity.generalreport.reportsubtitle"),
        		true);
               
    }


    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
} 
    


