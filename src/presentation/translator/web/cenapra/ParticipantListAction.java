package presentation.translator.web.cenapra;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.cenapra.ParticipantListFactory;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.cenapra.CENAPRAReportFacade;

import communication.cenapra.report.ParticipantListReportData;
import communication.language.Text;
import communication.util.ListResult;


/**
 * @author adocarmo
 */
public class ParticipantListAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        ReportForm reportForm = (ReportForm)form;

        JFreeReportBoot.getInstance().start();
        JFreeReport report = getReport(sys,reportForm,1);
        
        session.setAttribute("Bookmark",reportForm.getBookmark());
        return new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"driver",true);
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
    public JFreeReport getReport(SystemAccess sys,ReportForm reportForm,int page) throws Exception {
        Text messages = sys.getText();
        String output = reportForm.getOutput();
        int first = (output.equals("HTML")) ? (page-1)*50 : 0;
        int count = (output.equals("HTML")) ? 50 : -1;
        
        boolean all = Boolean.parseBoolean(reportForm.getAll());
        CENAPRAReportFacade reportFacade = sys.getCENAPRAReportFacade();
        ListResult result = reportFacade.getParticipantList(reportForm.getFirstName(),reportForm.getLastName(),reportForm.getMultiplicadores(),reportForm.getDivOrg11(),all,first,count,sys.getUserSessionData());
        int pages = (int)(result.getTotal() / 50);
        if(pages*50 < result.getTotal()) {
            pages++;
        }

        ParticipantListReportData data = new ParticipantListReportData(result,messages,all); 
        ParticipantListFactory factory = new ParticipantListFactory("ParticipantList",data,reportForm,sys,messages,pages,page,all);
        return factory.createReport();
    }
} 
    


