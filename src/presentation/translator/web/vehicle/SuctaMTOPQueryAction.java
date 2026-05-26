package presentation.translator.web.vehicle;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;


import presentation.report.ReportUtil;
import presentation.report.vehicle.SuctaMTOPQueryFactory;
import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.language.Text;
import communication.util.ListResult;
import communication.vehicle.report.SuctaMTOPQueryData;

public class SuctaMTOPQueryAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	try{
    	    
            HttpSession session = request.getSession();
            SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
            Text messages = sys.getText();
            SuctaMTOPQueryForm reportForm = (SuctaMTOPQueryForm)form;
        
            if (reportForm.getFrommonth() == null || reportForm.getFromyear() == null || 
                    reportForm.getTomonth() == null ||  reportForm.getToyear() == null ||
                    !Util.dateRange(reportForm.getFromyear(),reportForm.getToyear(),reportForm.getFrommonth(),reportForm.getTomonth(),2)){
            	session.setAttribute("Msg",messages.getText("report.event.range2"));
                return mapping.findForward("back");
            }
        
//      Valido que fecha from sea menor que fecha to:
            try {
                Date initialDate = reportForm.getInitialDate();
                Date lastDate = reportForm.getLastDate();
                if (initialDate.compareTo(lastDate) > 0) {
                    session.setAttribute("Msg",messages.getText("query.activity.commentarydrive.initialdatelastdate"));
                    return mapping.findForward("back");
                }
            }catch( Exception nfe) {
                session.setAttribute("Msg",messages.getText("query.activity.commentarydrive.initialdatelastdate"));
                return mapping.findForward("back");    		
            }
        
            JFreeReportBoot.getInstance().start();
            JFreeReport report = getReport(sys,reportForm,1);
        
            session.setAttribute("Bookmark",reportForm.getBookmark());
            return new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"SuctaMTOP",true);
    	}catch(Exception e){
    		e.printStackTrace();
    		throw e;
    	}
    	
    }

    public JFreeReport getReport(SystemAccess sys,SuctaMTOPQueryForm reportForm, int pageNumber) throws Exception {

    	try{
    	SuctaMTOPQueryData result;
    	
     	Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
     	Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
        
	    JFreeReport report = null;
	    ListResult listResult;
	    Text messages = sys.getText();
	    int total = 0;
	    int first = (reportForm.getOutput().equals("HTML")) ? (pageNumber - 1) * 50 : 0;
	    int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;        
	
	    String vehicleorplataform = (Util.empty(reportForm.getVehicleorplataform())?
	    		"vehicle":reportForm.getVehicleorplataform());
	   	listResult = sys.getVehicleReportFacade().
	     		getSuctaMTOPQuery(reportForm.getPlate(), 
	     				reportForm.getSuctaormtop(),
	     				reportForm.getStatus(),
	     				vehicleorplataform,
	     				reportForm.getFrommonth(), 
	     				reportForm.getFromyear(),
	     				reportForm.getTomonth(),
	     				reportForm.getToyear(),
	     				divOrgs,divGeos,first,count,
	     				sys.getUserSessionData());

	   	result = new SuctaMTOPQueryData(listResult.getElements(),listResult.getTotal());
	
       	total = listResult.getTotal();
        result.setMessages(messages);
        int pages = (int)(total / 50);
        if (pages * 50 < total) {
            pages++;
        }     	   	
     	
     	SuctaMTOPQueryFactory factory = new SuctaMTOPQueryFactory("SuctaMTOP",
     			result,	reportForm,sys,messages,count,pages,pageNumber,reportForm.getSuctaormtop(),vehicleorplataform);
     	report = factory.createReport();
        return report;
    	}catch(Exception e){
    		e.printStackTrace();
    		throw e;
    	}
    }
    
    
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

} 
    


