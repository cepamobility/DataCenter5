package presentation.translator.web.cenapra;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportFactory;
import presentation.report.ReportUtil;
import presentation.report.cenapra.CheckListReportFactory;
import presentation.report.cenapra.SatisfactionReportFactory;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.cenapra.CENAPRAReportFacade;

import communication.activity.tables.CheckListData;
import communication.activity.tables.PointData;
import communication.administration.corporation.tables.CorporationData;
import communication.cenapra.report.CheckListResultReportData;
import communication.cenapra.report.SatisfactionReportData;
import communication.language.Text;
import communication.util.ListResult;


/**
 * @author adocarmo
 */
public class CheckListReportAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        ReportForm reportForm = (ReportForm)form;

        JFreeReportBoot.getInstance().start();
        JFreeReport report = getReport(sys,reportForm,1);
        
        session.setAttribute("Bookmark",reportForm.getBookmark());
        session.removeAttribute("CENAPRACheckListReportForm");
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
        int checkListType = Integer.parseInt(reportForm.getChecklist()); 
        
        Collection activities = new LinkedList();
        String s = "0";
        if(!Util.isChecked(reportForm.getMultiplicadores()) && !Util.isChecked(reportForm.getReplicadores()) && !Util.isChecked(reportForm.getMultiplicadoresNew())
            && !Util.isChecked(reportForm.getMultiplicadores2()) && !Util.isChecked(reportForm.getFormadores()) && !Util.isChecked(reportForm.getPoblacionmeta())) {
            s += ",20,21,23,24,25,26,30,31,32,33";
        }else {
            if(Util.isChecked(reportForm.getMultiplicadores())) {
                activities.add("datatype.scheduledactivitytype.cenapramultiplicadores");
                s += ",20,21";
            }
            if(Util.isChecked(reportForm.getReplicadores())) {
                activities.add("datatype.scheduledactivitytype.cenaprareplicadores");
            }
            if(Util.isChecked(reportForm.getMultiplicadoresNew())) {
                activities.add("datatype.scheduledactivitytype.cenapramultiplicadoresnew");
                s += ",25,26";
            }
            if(Util.isChecked(reportForm.getMultiplicadores2())) {
                activities.add("datatype.scheduledactivitytype.cenapramultiplicadores2");
                s += ",32,33";
            }
            if(Util.isChecked(reportForm.getFormadores())) {
                activities.add("datatype.scheduledactivitytype.cenapraformadores");
                activities.add("datatype.scheduledactivitytype.cenapraformadoresviejo");
                s += ",32,33";
            }
            if(Util.isChecked(reportForm.getPoblacionmeta())) {
                activities.add("datatype.scheduledactivitytype.cenaprapoblacionmeta2");
                activities.add("datatype.scheduledactivitytype.cenaprapoblacionmeta");
                s += ",23,24,30,31";
            }
        }
        ListResult result = reportFacade.getParticipantResult(reportForm.getFirstName(),reportForm.getLastName(),activities,reportForm.getFrom(),reportForm.getTo(),all,checkListType,first,count,sys.getUserSessionData());
        int pages = (int)(result.getTotal() / 50);
        if(pages*50 < result.getTotal()) {
            pages++;
        }

        ReportFactory factory = null;
        if(checkListType == 1) {
            CheckListResultReportData data = new CheckListResultReportData(result,reportFacade.getCorrectAnswers(s,sys.getUserSessionData()),messages); 
            factory = new CheckListReportFactory("CheckList",data,reportForm,sys,messages,Integer.parseInt(reportForm.getOrder()),pages,page,all);
        }else {
        	int id = (sys.getCorporationId() == CorporationData.CENAPRA) ? 22 : 52;
            CheckListData checkList = sys.getActivityReportFacade().findCheckList(new Integer(id),sys.getUserSessionData());
            Collection points = checkList.getPoints();
            Collections.sort((List)points);
            Iterator it = points.iterator();
            Integer[] pointIds = new Integer[points.size()];
            String[] questions = new String[points.size()];
            int i = 0;
            while(it.hasNext()) {
                PointData point = ((PointData)it.next());
                pointIds[i] = point.getId();
                questions[i] = messages.getText(point.getText());
                i++;
            }
            SatisfactionReportData data = new SatisfactionReportData(result,pointIds,messages); 
            factory = new SatisfactionReportFactory("Satisfaction",data,reportForm,sys,messages,1,pages,page,questions,all);
        }
        return factory.createReport();
    }
} 
    


