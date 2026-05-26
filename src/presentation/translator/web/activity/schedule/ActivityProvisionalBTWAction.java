/*
 * Created on 24-feb-2006
 */
package presentation.translator.web.activity.schedule;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportFactory;
import presentation.report.ReportUtil;
import presentation.report.activity.ActivityProvisionalBTWTabletFactory;
import presentation.report.activity.ActivityReportFactory;
import presentation.report.activity.ActivityReportTabletFactory;
import presentation.report.activity.EBtwReportFactory;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.activity.ActivityReportFacade;

import communication.activity.report.ActivityReportData;
import communication.activity.report.ActivityReportTabletData;
import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.activity.tables.ItemData;
import communication.activity.tables.PointData;
import communication.driver.tables.DriverData;
import communication.language.Text;

/**
 * @author adocarmo
 *
 */
public class ActivityProvisionalBTWAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	 
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        String date=request.getParameter("date");
        String result=request.getParameter("result");
    	String idDriver=request.getParameter("idDriver");
     	String formatOutput ="PDF" ;
     	request.setAttribute("PBTW", "true");
     	
    	session.setAttribute("output",formatOutput);
    	if (request.getParameter("output")==null || !request.getParameter("output").equalsIgnoreCase("PDF")) formatOutput="";    	
           	
        JFreeReport report;
        DriverData driver = sys.getDriverReportFacade().findDriverByPrimaryKey(Integer.valueOf(idDriver), sys.getUserSessionData());

        ReportFactory factory = null;
        
       
        
        
        factory = new ActivityProvisionalBTWTabletFactory(driver,"ActivityProvisionalBTW",
        			 		null,form,sys,messages,formatOutput,date,result);
        
        report = factory.createReport();	
        
        try{
        	JFreeReportBoot.getInstance().start();
        }catch(Throwable e){
        	e.printStackTrace();
        	// Intento de nuevo, porque el primer reporte salta exception al buscar las fuentes (tipos de letras)
        	JFreeReportBoot.getInstance().start();
        }
        
        session.setAttribute("guide","guide");
        return new ReportUtil().executeReport(mapping,response,report,formatOutput,session,"activity",
        		messages.getText("query.activity.generalreport.reporttitle"),
        		messages.getText("query.activity.generalreport.reportsubtitle"),
        		true);
               
    }


    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
    /*
    ActivityReportData getReportActivity(CEPAActivityScheduleRegistryData registry) {
    	
        Collection items =  registry.getItemSet(); 
        Collection result = new LinkedList();
        Iterator iter2 = items.iterator();           
        while (iter2.hasNext()) {
        	Item item = (Item) iter2.next();
        	Point point = getPoint(registry.getActivity().getDefinition().getCheckList().getPoints(),item);
        	PointItem ip = new PointItem(point,item);
            result.add(ip);
        }       
        return new ActivityReportData(result);        
    }
    */
    
     
} 
    


