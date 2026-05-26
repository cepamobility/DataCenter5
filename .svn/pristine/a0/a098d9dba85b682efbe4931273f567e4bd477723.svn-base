/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.queries;

import java.awt.Color;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.report.JFreeReport;

import presentation.report.ReportUtil;
import presentation.report.queries.ReportAccidentCountFactory;
import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.language.Text;
import communication.queries.report.ReportAccidentCountData;


/**
 * @author adocarmo
 */
public class ReportAccidentCountAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        ReportAccidentCountForm reportForm = (ReportAccidentCountForm)form;
        
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
        if ( fromYear == null ||  toYear == null || fromMonth == null || toMonth == null ||
                !Util.dateRange(fromYear.toString(),toYear.toString(),fromMonth.toString(),toMonth.toString(),2)){
            session.setAttribute("Msg",messages.getText("report.event.range2"));
            return mapping.findForward("back");
        }

        JFreeReport report = getReport(sys,reportForm);
        ArrayList arrayGraphics = new ArrayList();
        session.removeAttribute("ReportAccidentCountForm");
        session.setAttribute("Bookmark",reportForm.getBookmark());
        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"AccidentCount",arrayGraphics,true);
        return fwd;
        
    }
    
    private JFreeChart createFirstLevelChart(Text messages,final CategoryDataset dataset, String name) {
    	final JFreeChart chart = ChartFactory.createBarChart(messages.getText("query.accidentcount.title")+" / "+name,name,"",dataset, PlotOrientation.VERTICAL,true,true,false);
        chart.setBackgroundPaint(Color.WHITE);
        return chart;
    }

    private JFreeChart createSecondLevelChart(Text messages, final CategoryDataset dataset, String name) {
        final JFreeChart chart = ChartFactory.createBarChart(messages.getText("query.accidentcount.title")+" / "+name,name,"",dataset, PlotOrientation.VERTICAL,true,true,false);
        chart.setBackgroundPaint(Color.WHITE);
        return chart;
    }

    private JFreeChart createThirdLevelChart(Text messages, final CategoryDataset dataset, String name) {
        final JFreeChart chart = ChartFactory.createBarChart(messages.getText("query.accidentcount.title")+" / "+name,name,"",dataset, PlotOrientation.VERTICAL,true,true,false);
        chart.setBackgroundPaint(Color.WHITE);
        return chart;
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
    public JFreeReport getReport(SystemAccess sys,ReportAccidentCountForm reportForm) throws Exception {
        Text messages = sys.getText();

        String fromMonth = reportForm.getFrommonth();
        String toMonth = reportForm.getTomonth();
        String fromYear = reportForm.getFromyear();
        String toYear = reportForm.getToyear();
        
        Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
        Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);

        int order1 = 4;
        int order2 = 4;
        int order3 = 4;
        int order4 = 4;
        order1 = Integer.parseInt(reportForm.getOrder1());
        if(order1 != 4) {
            order2 = Integer.parseInt(reportForm.getOrder2());
        }
        if(order2 != 4) {
            try {
                order3 = Integer.parseInt(reportForm.getOrder3());
            }catch(Exception e) {
                order3 = 4;
            }
        }
        if(order3 != 4) {
            try {
                order4 = Integer.parseInt(reportForm.getOrder4());
            }catch(Exception e) {
                order4 = 4;
            }
        }
        
        String vehicleType = (reportForm.getVehicletype().equals("datatype.vehicletype.all")) ? "" : reportForm.getVehicletype();
        ReportAccidentCountData result = sys.getEventReportFacade().
            getReportEventCount(fromMonth,new Integer(fromYear),toMonth,new Integer(toYear),divOrgs,divGeos,
                    vehicleType,0,-1,sys.getUserSessionData(),order1,order2,order3,order4,messages);
        
        ReportAccidentCountFactory factory = new ReportAccidentCountFactory(messages.getText("query.accidentcount.title"),result ,reportForm,sys,messages,reportForm.getOutput());

        return factory.createReport();
    }

} 
    


