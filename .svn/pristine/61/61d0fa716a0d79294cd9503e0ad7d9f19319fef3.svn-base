package presentation.translator.web.vehicle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.vehicle.ReportVehicleWithoutPursuitRouteFactory;
import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.language.Text;
import communication.util.ListResult;
import communication.vehicle.report.VehicleWithoutPursuitRouteData;

/**
 * DriverWithOutECAction
 * Lleva a cabo la generacion del reporte de los Conductores
 * que no hicieron la Evaluación en Cabina en Vehiculos Pesados.
 * 
 * @author gbenaderet
 *
 */
public class VehicleWithoutPursuitRouteAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        VehicleWithoutPursuitRouteForm reportForm = (VehicleWithoutPursuitRouteForm)form;        
       
        JFreeReportBoot.getInstance().start();
        JFreeReport report = getReport(sys,reportForm,1);
        session.setAttribute("Bookmark",reportForm.getBookmark());
        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report, reportForm.getOutput(),session,"VehicleWithoutPursuitRoute",true);
        return fwd;

        
    }
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
    public JFreeReport getReport(SystemAccess sys,VehicleWithoutPursuitRouteForm reportForm,int page)
    	throws Exception {
    	
        Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
        Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);        

        JFreeReport report = null;
        Text messages = sys.getText();
        
        int rows = 50;
        
        int first = (reportForm.getOutput().equals("HTML")) ? (page-1)* rows : 0;
        int count = (reportForm.getOutput().equals("HTML")) ? rows : -1;
        
       
        int status = reportForm.getStatus();
        
        ListResult result = sys.getVehicleReportFacade().vehicleWithoutPursuitRoute(
        		sys.getUserSessionData(),null,null,divOrgs,divGeos,
        		status,first,count,reportForm.getPlate());        
        
        VehicleWithoutPursuitRouteData query = new VehicleWithoutPursuitRouteData(result.getElements(),
        		result.getTotal(), sys.getUserSessionData());
        query.setMessages(messages);
        

        int pages = (int)(result.getTotal() / rows);
        if(pages*rows < result.getTotal()) {
            pages++;
        }     
        
        ReportVehicleWithoutPursuitRouteFactory factory = new ReportVehicleWithoutPursuitRouteFactory(
        		messages.getText("vehicle.seguimientoenruta.query.notpursuit.query"),query,
        		reportForm,sys,messages,pages,page);        
        
        
        report = factory.createReport();

        return report;
    }    
}
