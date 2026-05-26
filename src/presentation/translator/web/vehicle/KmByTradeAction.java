package presentation.translator.web.vehicle;

import java.util.Calendar;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.vehicle.KmByTradeFactory;
import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.language.Text;
import communication.vehicle.report.KmByTradeData;

/**
 * DriverWithOutECAction
 * Lleva a cabo la generacion del reporte de los Conductores
 * que no hicieron la Evaluación en Cabina en Vehiculos Pesados.
 * 
 * @author gbenaderet
 *
 */
public class KmByTradeAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        KmByTradeForm reportForm = (KmByTradeForm)form;        
       
        JFreeReportBoot jfree = JFreeReportBoot.getInstance();
        try{
        	jfree.start();
        }catch (Throwable e){
        	jfree.start(); // tiro de nuevo el reporte por si da errores de truetypefonts el jfree
        }
        JFreeReport report = getReport(sys,reportForm);
        session.setAttribute("Bookmark",reportForm.getBookmark());
        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report, reportForm.getOutput(),session,"KmByTrade",true);
        return fwd;
    }
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
    public JFreeReport getReport(SystemAccess sys,KmByTradeForm reportForm) throws Exception {
    	
        Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
        Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);        

        JFreeReport report = null;
        Text messages = sys.getText();
        
        Calendar c = Calendar.getInstance();
        
        
        int fromMonth = Util.isNumber(reportForm.getFrommonth())?Integer.parseInt(reportForm.getFrommonth())-1:c.get(Calendar.MONTH);
        
        // int fromMonth = Util.isNumber(reportForm.getFrommonth())?Integer.parseInt(reportForm.getFrommonth()):c.get(Calendar.MONTH);
        
        int fromYear = Util.isNumber(reportForm.getFromyear())?Integer.parseInt(reportForm.getFromyear()):c.get(Calendar.YEAR);
        if(fromMonth == 0) {
        	fromMonth = 12;
        	fromYear--;
        }
        Collection result = sys.getFleetReportFacade().kmByTrade(String.valueOf(fromMonth),String.valueOf(fromYear),reportForm.getTomonth(),reportForm.getToyear(),reportForm.getPlate(),Integer.parseInt(reportForm.getStatus()),divOrgs,divGeos,sys.getUserSessionData());        
        KmByTradeData query = new KmByTradeData(result,messages,Integer.parseInt(reportForm.getTomonth()),Integer.parseInt(reportForm.getToyear()));
        KmByTradeFactory factory = new KmByTradeFactory(messages.getText("query.kmbytrade.title"),query,reportForm,sys,messages,0);        
        
        report = factory.createReport();
        return report;
    }    
}
