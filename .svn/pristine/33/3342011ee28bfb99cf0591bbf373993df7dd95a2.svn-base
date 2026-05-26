/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.queries;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.queries.ReportAPMMFactory;
import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.DivOrgForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.RoleData;
import communication.administration.bookmark.tables.BookmarkData;
import communication.administration.corporation.tables.CorporationData;
import communication.language.Text;
import communication.queries.report.ReportAPMMData;
import communication.security.SecurityConstants;
import communication.util.Filter;


/**
 * @author adocarmo
 *
 */
public class ReportAPMMAction extends AbstractAction {

	// Constants
	private final String PATH = SecurityConstants.IMAGES_WAR_DIRECTORY;  // "/tmp/datacenter/images/";
	
	
    public ActionForward action(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        APMMReport reportForm = (APMMReport)form;
        
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
        
        
        Integer[][] divOrgs = null;
        Integer[] divGeos = null;

        // Los usuarios de Rol Contratista solo pueden ver divisiones de sus contratistas:
        if (sys.getUserSessionData().getRole().getName().equals(RoleData.CONTRACTOR)){
        	divOrgs = (reportForm instanceof DriverDivOrgForm) ? Util.getDivOrgs(sys,(DriverDivOrgForm)reportForm,Filter.USER) : Util.getDivOrgs(sys,(DivOrgForm)reportForm,Filter.USER);
        	divGeos = (reportForm instanceof DriverDivOrgForm) ? Util.getDivGeos(sys,(DriverDivOrgForm)reportForm,Filter.USER) : Util.getDivGeos(sys,(DivOrgForm)reportForm,Filter.USER);
        }else{
        	divOrgs = (reportForm instanceof DriverDivOrgForm) ? 
        			Util.getDivOrgs(sys,(DriverDivOrgForm)reportForm,Filter.CORPORATION) : 
        				Util.getDivOrgs(sys,(DivOrgForm)reportForm,Filter.CORPORATION);
        	divGeos = (reportForm instanceof DriverDivOrgForm) ? 
        			Util.getDivGeos(sys,(DriverDivOrgForm)reportForm,Filter.CORPORATION) : 
        				Util.getDivGeos(sys,(DivOrgForm)reportForm,Filter.CORPORATION);
        }
        
        int order1 = Integer.parseInt(reportForm.getOrder1());
        int order2 = Integer.parseInt(reportForm.getOrder2());
        int order3 = 4;
        try {
            order3 = Integer.parseInt(reportForm.getOrder3());
        }catch(Exception e) {}
        int order4 = 4;
        try {
            order4 = Integer.parseInt(reportForm.getOrder4());
        }catch(Exception e) {}
        String[] levelNames = (sys.isCorporation(CorporationData.BAYER)) ? sys.getUserSessionData().getFilter().getDivOrgLabels() : sys.getUserSessionData().getFilter().getLevelNames(messages);
        
        if (!Util.dateRange(fromYear,toYear,fromMonth,toMonth,2)){
            	session.setAttribute("Msg",messages.getText("report.event.range2"));
                return mapping.findForward("back");
        }

        boolean showDecimals = Util.isChecked(reportForm.getDecimals());
        
        ReportAPMMData result = sys.getFleetReportFacade().getReportAPMM(fromMonth,new Integer(fromYear),toMonth,
        		new Integer(toYear),divOrgs,divGeos,order1,order2,order3,order4,
        		Integer.parseInt(reportForm.getFleetTypes()),sys.getUserSessionData(),messages);
    	
        // Creo el reporte:    	
        int monthCount = (12 - Integer.parseInt(fromMonth) + 1) + ((Integer.parseInt(toYear) - Integer.parseInt(fromYear) - 1) * 12) + Integer.parseInt(toMonth);
        ReportAPMMFactory factory = new ReportAPMMFactory("APMM",result,form,sys,messages, 
        		reportForm.getOutput(),monthCount,showDecimals);
        JFreeReport report = factory.createReport();
       
        ArrayList arrayGraficas = new ArrayList();
        
        if (result != null){
	        for(int i = 1; i <= levelNames.length; i++) {
	            if(factory.getOrder(i) != 4) {
	                
	                final CategoryDataset dataset = (result != null)?result.getChartDataset(i):null;
	                if(!sys.isCorporation(CorporationData.BAYER)) {
	                	String s = (factory.getOrder(i) == 3) ? "corporation.country" : levelNames[factory.getOrder(i)];
		                final JFreeChart chart = ChartFactory.createBarChart("APMM / "+messages.getText(s),messages.getText(s),"",dataset, PlotOrientation.VERTICAL,true,true,false);
		
		                chart.setBackgroundPaint(Color.WHITE);
		                report.setProperty("chart"+i, chart);
		                report.setPropertyMarked("chart"+i, true);      
		
		                arrayGraficas.add(CreateJPEGFromChart(sys,chart, "chart"+i,"apmm"));
	                }
	            }
	        }
        }
        
        // Inicio el JFreeReport:
        JFreeReportBoot jfree = JFreeReportBoot.getInstance();
        try{
        	jfree.start();
        }catch(Throwable e){
        	jfree.start();
        }
        
        session.setAttribute("Bookmark",reportForm.getBookmark(BookmarkData.KM_BYDIVISION_REPORT));
        session.removeAttribute("ReportAPMMForm");
        session.removeAttribute("ReportAPMMFormJJ");
        // Muestro el reporte:
        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"apmm",arrayGraficas,true);
        return fwd;
        
    }
 
    /*
     * @param chart : JFreeChart 
     * @param chartName : Name of the JFreeChart
     * @param reportName : Name of the report which contains the Chart
     * @return String : Name of the image generated. 
     */
    private String CreateJPEGFromChart(SystemAccess sys,JFreeChart chart, String chartName, String reportName)
    {
    	// Genero imagenes para mostrar en el html:    	
    	String loginActual = null;
    	File imagen = null;
    	Date now = new Date();
    	long nowLong;
    	try{
    		// Segundos de la fecha de generacion de la grafica
            nowLong = now.getTime();
            // Login del usuario que solicito el reporte
        	loginActual = sys.getUserSessionData().getFilter().getLoginName().toString();
        	imagen = new File(PATH + loginActual + "-" +
        			reportName + "-" + chartName + "-" + String.valueOf(nowLong)+".png");        	
        	ChartUtilities.saveChartAsPNG(imagen,chart,600,600);        	
        }catch (IOException e){
        	String message = e.getMessage();
        	System.out.print(message);
        }
        return imagen.getName();
        
    } 
    
    public JFreeReport getReport(SystemAccess sys,ReportAPMMFormJJ reportForm) throws Exception {
        Text messages = sys.getText();

        String fromMonth = reportForm.getFrommonth();
        String toMonth = reportForm.getTomonth();
        String fromYear = reportForm.getFromyear();
        String toYear = reportForm.getToyear();
        if(fromMonth.startsWith("Q")) {
            fromMonth = String.valueOf(Integer.parseInt(fromMonth.substring(1)) * 3 - 2);
        }
        if(toMonth.startsWith("Q")) {
            toMonth = String.valueOf(Integer.parseInt(toMonth.substring(1)) * 3);
        }
        
        Integer[][] divOrgs = null;
        Integer[] divGeos = null;

        // Los usuarios de Rol Contratista solo pueden ver divisiones de sus contratistas:
        if (sys.getUserSessionData().getRole().getName().equals(RoleData.CONTRACTOR)){
        	divOrgs =  Util.getDivOrgs(sys,(DivOrgForm)reportForm,Filter.USER);
        	divGeos =  Util.getDivGeos(sys,(DivOrgForm)reportForm,Filter.USER);
        }else{
        	divOrgs =  Util.getDivOrgs(sys,(DivOrgForm)reportForm,Filter.CORPORATION);
        	divGeos =  Util.getDivGeos(sys,(DivOrgForm)reportForm,Filter.CORPORATION);
        }
        //Integer[][] divOrgs = Util.getDivOrgs(sys,(DivOrgForm)reportForm,Filter.CORPORATION);
        //Integer[] divGeos = Util.getDivGeos(sys,(DivOrgForm)reportForm,Filter.CORPORATION);
        
        int order1 = Integer.parseInt(reportForm.getOrder1());
        int order2 = Integer.parseInt(reportForm.getOrder2());
        int order3 = 4;
        try {
            order3 = Integer.parseInt(reportForm.getOrder3());
        }catch(Exception e) {}
        int order4 = 4;
        try {
            order4 = Integer.parseInt(reportForm.getOrder4());
        }catch(Exception e) {}

        boolean showDecimals = Util.isChecked(reportForm.getDecimals());
        ReportAPMMData result = sys.getFleetReportFacade().getReportAPMM(fromMonth,new Integer(fromYear),toMonth,new Integer(toYear),divOrgs,divGeos,order1,order2,order3,order4,Integer.parseInt(reportForm.getFleetTypes()),sys.getUserSessionData(),messages);
        
        // Creo el reporte:     
        int monthCount = (12 - Integer.parseInt(fromMonth) + 1) + ((Integer.parseInt(toYear) - Integer.parseInt(fromYear) - 1) * 12) + Integer.parseInt(toMonth);
        ReportAPMMFactory factory = new ReportAPMMFactory("APMM",result,reportForm,sys,messages,reportForm.getOutput(),monthCount,showDecimals);
        return factory.createReport();
    }
    
    private void deleteOldImages()
    {
    	// TODO
    	//Date now = new Date();
    	//long nowLong = now.getTime();
    	
        //boolean success = (new File("filename")).delete();
        //if (!success) {
            // Deletion failed
        //}

     
    }
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

} 
    


