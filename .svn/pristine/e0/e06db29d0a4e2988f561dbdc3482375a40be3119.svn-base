/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.vehicle;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.vehicle.ReportSyngentaCLFactory;
import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.activity.tables.CheckListData;
import communication.activity.tables.ItemData;
import communication.activity.tables.PointData;
import communication.language.Text;
import communication.util.Filter;
import communication.util.ListResult;
import communication.vehicle.report.ReportSyngentaCLData;


/**
 * @author adocarmo
 *
 */
public class ReportSyngentaCLAction extends AbstractAction {

	
    public ActionForward action(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response) throws Exception {

    	try{
	        HttpSession session = request.getSession();
	        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
	        Text messages = sys.getText();
	
	        ReportCheckListForm reportForm = (ReportCheckListForm)form;
	        
	        String fromMonth = reportForm.getFrommonth();
	        String toMonth = reportForm.getTomonth();
	        String fromYear = reportForm.getFromyear();
	        String toYear = reportForm.getToyear();
	        if(fromMonth.length() == 0 || toMonth.length() == 0 || fromYear.length() == 0 || toYear.length() == 0) {
	            session.setAttribute("Msg",messages.getText("generic.error.emptyperiod"));
	            return mapping.findForward("back");
	        }
	        if((fromMonth.startsWith("Q") && !toMonth.startsWith("Q")) || (!fromMonth.startsWith("Q") && toMonth.startsWith("Q"))) {
	            session.setAttribute("Msg",messages.getText("generic.error.invalidperiod"));
	            return mapping.findForward("back");
	        }
	        if(fromMonth.startsWith("Q")) {
	            fromMonth = String.valueOf(Integer.parseInt(fromMonth.substring(1)) * 3 - 2);
	        }
	        if(toMonth.startsWith("Q")) {
	            toMonth = String.valueOf(Integer.parseInt(toMonth.substring(1)) * 3);
	        }
	         
	        
	        
	        // Inicio el JFreeReport:
	        try{
	        	JFreeReportBoot.getInstance().start();
	        }catch(Exception e){
	        	// La primera vez que se corre puede dar error de cache de Pentahoo.
	        	// asi que lo corremos de nuevo...jeje
	        	JFreeReportBoot.getInstance().start();
	        }
	        
	        
	        session.removeAttribute("ReportCheckListForm");   
	        
	        JFreeReport report = getReport(sys,reportForm,1);
            session.setAttribute("Bookmark",reportForm.getBookmark());
	        // Muestro el reporte:
	        return new ReportUtil().executeReport(mapping,response, report,reportForm.getOutput(),session,"CheckList",true);	  
    	}catch(Exception e){
    		e.printStackTrace();
    		throw e;
    	}
    }
 
    
    public JFreeReport getReport(SystemAccess sys,ReportCheckListForm reportForm, int pageNumber) throws Exception {

       	try{
	       	Integer[][] divOrgs = Util.getDivOrgs(sys,(DriverDivOrgForm)reportForm,Filter.USER);
	        Integer[] divGeos = Util.getDivGeos(sys,(DriverDivOrgForm)reportForm,Filter.USER);
	        
	        String fromMonth = reportForm.getFrommonth();
	        String toMonth = reportForm.getTomonth();
	        String fromYear = reportForm.getFromyear();
	        String toYear = reportForm.getToyear();
	        
	        ListResult listResult;
	   	    Text messages = sys.getText();
	   	    int total = 0;
	   	    int first = (reportForm.getOutput().equals("HTML")) ? (pageNumber - 1) * 50 : 0;
	   	    int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;        
	   	
	   	    
	   	    ActionForward fwd = null;        
	        JFreeReport report = null;
	        boolean getAll = false;

            // Vehicle or Trailer
        	String type = "vehicle"; // Camion
        	if (reportForm.getType().trim().equalsIgnoreCase("trailer")){
        		type = "trailer";
        	}
	        
        	CheckListData checkList = sys.getVehicleReportFacade().getCheckList(CheckListData.SYNGENTA_CHECKLIST,sys.getUserSessionData());
    		Collection itemList = new LinkedList();
        	Iterator points = checkList.getSortPoints();
    		while(points.hasNext()) {
			    PointData point = (PointData)points.next();
				Iterator items = point.getSortItems();
				while(items.hasNext()) {
					itemList.add(items.next());
				}
    		}
    		ItemData[] items = (ItemData[])itemList.toArray(new ItemData[0]);
    		
	        int[] order = {0,0,0};
	        Collection result = sys.getVehicleReportFacade().getCheckLists(new Integer(fromMonth),new Integer(fromYear),
	        	 	new Integer(toMonth),new Integer(toYear),order,divOrgs,divGeos,items,sys.getUserSessionData());
	 	    	
	        ReportSyngentaCLData data = new ReportSyngentaCLData(result,items,sys.getText());
	        // Creo el reporte:   
	        ReportSyngentaCLFactory factory = new ReportSyngentaCLFactory("CheckList",data,reportForm,items,sys,reportForm.getReportType().equals("resumed"));
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
    


