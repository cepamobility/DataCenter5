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
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.queries.ReportFineCountFactory;
import presentation.report.queries.ReportFineTypeFactory;
import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.language.Text;
import communication.queries.report.ReportFineCountData;
import communication.queries.report.ReportFineTypeData;


/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReportFineAction extends AbstractAction {
		
	
    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        ReportFineForm reportForm = (ReportFineForm)form;
        Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
        Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
        
        if(Util.empty(reportForm.getCountry2())) {
            session.setAttribute("Msg",messages.getText("reportapmm.error.emptycountry"));
            return mapping.findForward("back");
        }
        
        JFreeReport report = null;
        ArrayList arrayGraphics = new ArrayList();
        
        if(Integer.parseInt(reportForm.getType()) == 1) {
            ReportFineCountData result = sys.getEventReportFacade().getReportFineCount(reportForm.getFrommonth(),new Integer(reportForm.getFromyear()),reportForm.getTomonth(),new Integer(reportForm.getToyear()),divOrgs,divGeos,sys.getUserSessionData());     
            ReportFineCountFactory factory = new ReportFineCountFactory(messages.getText("query.fine.title"),result,form,sys,messages);
            factory.setUnitsChart(result.isUnit());
            factory.setDivisionsChart(result.isDivision());
            factory.setSubdivisionsChart(result.isSubdivision());
            report = factory.createReport();
            if(result.isUnit()) {
                JFreeChart chart =createChart(messages,report, "Units","query.accidentcount.state", result.getDataset(0));
                String charts = Util.createJPEGFromChart(sys,chart,"state", "finesCountType");
                arrayGraphics.add(charts);
            }
            if(result.isDivision()) {
            	JFreeChart chart = createChart(messages,report, "Divisions","query.accidentcount.division", result.getDataset(1));
                String charts = Util.createJPEGFromChart(sys,chart,"division", "finesCountType");
                arrayGraphics.add(charts);
            }
            if(result.isSubdivision()) {
            	JFreeChart chart = createChart(messages,report, "Subdivisions","query.accidentcount.subdivision", result.getDataset(2));
            	String charts = Util.createJPEGFromChart(sys,chart,"subdivision", "finesCountType");
                arrayGraphics.add(charts);
            }
        }else {
            ReportFineTypeData result = sys.getEventReportFacade().getReportFineType(reportForm.getFrommonth(),new Integer(reportForm.getFromyear()),reportForm.getTomonth(),new Integer(reportForm.getToyear()),divOrgs,divGeos,sys.getUserSessionData());     
            result.setMessages(messages);
            ReportFineTypeFactory factory = new ReportFineTypeFactory(messages.getText("query.fine.title"),result,form,sys,messages);
            factory.setUnitsChart(result.isUnit());
            factory.setDivisionsChart(result.isDivision());
            factory.setSubdivisionsChart(result.isSubdivision());
            report = factory.createReport();
            if(result.isUnit()) {
            	JFreeChart chart = createChart(messages,report, "Units","query.accidentcount.state", result.getDataset(0));
            	String charts = Util.createJPEGFromChart(sys,chart,"state", "finesCountType");
                arrayGraphics.add(charts);
            }
            if(result.isDivision()) {
            	JFreeChart chart = createChart(messages,report, "Divisions","query.accidentcount.division", result.getDataset(1));
            	String charts = Util.createJPEGFromChart(sys,chart,"division", "finesCountType");
                arrayGraphics.add(charts);
            }
            if(result.isSubdivision()) {
            	JFreeChart chart = createChart(messages,report, "Subdivisions","query.accidentcount.subdivision", result.getDataset(2));
            	String charts = Util.createJPEGFromChart(sys,chart,"subdivision", "finesCountType");
                arrayGraphics.add(charts);
            }
        }
       

        JFreeReportBoot.getInstance().start();

        session.removeAttribute("ReportAccidentCountForm");
        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"finesCountType",arrayGraphics,true);
        arrayGraphics=null;
        return fwd;
        
    }

    private JFreeChart createChart(Text messages,JFreeReport report, String field, String name,CategoryDataset dataset) {
        final JFreeChart chart = ChartFactory.createBarChart(messages.getText("query.accidentcount.title")+" / "+messages.getText(name),messages.getText(name),"",dataset, PlotOrientation.VERTICAL,true,true,false);
        chart.setBackgroundPaint(Color.WHITE);
        report.setProperty(field, chart);
        report.setPropertyMarked(field, true);
        
        return chart;
    }
    

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

} 
    


