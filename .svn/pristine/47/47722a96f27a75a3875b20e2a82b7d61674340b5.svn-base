package presentation.translator.web.vehicle;

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
import presentation.report.vehicle.ReportVehiclePursuitRouteFactory;
import presentation.report.vehicle.ReportVehiclePursuitRouteListFactory;
import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.language.Text;
import communication.util.Filter;
import communication.vehicle.report.ReportVehiclePursuitRouteData;
import communication.vehicle.report.VehicleCheckReportDataType;

public class ReportVehiclePursuitRouteAction extends AbstractAction {

	
    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        ReportVehiclePursuitRouteForm reportForm = (ReportVehiclePursuitRouteForm)form;
        
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
        
        Integer[][] divOrgs = Util.getDivOrgs(sys,(DriverDivOrgForm)reportForm,Filter.USER);
        Integer[] divGeos = Util.getDivGeos(sys,(DriverDivOrgForm)reportForm,Filter.USER);
        
        int order = 0;
        switch(Integer.parseInt(reportForm.getOrder1())) {
            case 1:
                if(reportForm.getOrder2() != null && Integer.parseInt(reportForm.getOrder2()) == 2) {
                    order = VehicleCheckReportDataType.CONTRACT_TYPE;
                }else {
                    order = VehicleCheckReportDataType.CONTRACT;
                }
                break;
            case 2:
                if(reportForm.getOrder2() != null && 
                		Integer.parseInt(reportForm.getOrder2()) == 1) {
                    order = VehicleCheckReportDataType.TYPE_CONTRACT;
                }else {
                    order = VehicleCheckReportDataType.TYPE;
                }
        }
        
        ReportVehiclePursuitRouteData result;
        ReportFactory factory;
        	// Reporte Con Seguimientos en Ruta
        	
        if(Integer.parseInt(reportForm.getOutputType()) == 1) {
        	if(!Util.isChecked(reportForm.getAcumulated())) {
        		result = sys.getVehicleReportFacade().getVehiclePursuitRouteListByItem(new Integer(fromMonth),new Integer(fromYear),
                		new Integer(toMonth),new Integer(toYear),divOrgs,divGeos,messages,order,sys.getUserSessionData());        	
                	// Creo el reporte:    	
                factory = new ReportVehiclePursuitRouteListFactory("SeguimientoEnRuta",result,form,sys,messages,order,38);
        	}else {
        		result = sys.getVehicleReportFacade().getVehiclePursuitRouteList(new Integer(fromMonth),new Integer(fromYear),
            		new Integer(toMonth),new Integer(toYear),divOrgs,divGeos,messages,order,sys.getUserSessionData());        	
            	// Creo el reporte:    	
            	factory = new ReportVehiclePursuitRouteListFactory("SeguimientoEnRuta",result,form,sys,messages,order,4);
        	}
        }else {
        	if(!Util.isChecked(reportForm.getAcumulated())) {
        		result = sys.getVehicleReportFacade().getVehiclePursuitRouteCountByItem(new Integer(fromMonth),new Integer(fromYear),
        				new Integer(toMonth),new Integer(toYear),divOrgs,divGeos,messages,order,sys.getUserSessionData());    	
        	// Creo el reporte:    	
        		factory = new ReportVehiclePursuitRouteFactory("SeguimientoEnRuta",result,form,sys,messages,order,38);
        	}else {
        		result = sys.getVehicleReportFacade().getVehiclePursuitRouteCount(new Integer(fromMonth),new Integer(fromYear),
        				new Integer(toMonth),new Integer(toYear),divOrgs,divGeos,messages,order,sys.getUserSessionData());
        	// Creo el reporte:    	
        		factory = new ReportVehiclePursuitRouteFactory("SeguimientoEnRuta",result,form,sys,messages,order,4);
        	}
        }
        	
        
        
        JFreeReport report = factory.createReport();
    
        // Inicio el JFreeReport:
        JFreeReportBoot.getInstance().start();
        
        session.removeAttribute("ReportVehiclePursuitRouteForm");
        session.setAttribute("Bookmark",reportForm.getBookmark());
        // Muestro el reporte:
        ActionForward fwd = new ReportUtil().executeReport(mapping,response,
        		report,reportForm.getOutput(),session,"checkList",true);
        return fwd;
        
    }
 
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

    public JFreeReport getReport(SystemAccess sys,ReportVehiclePursuitRouteForm reportForm) throws Exception {
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
        
        Integer[][] divOrgs = Util.getDivOrgs(sys,(DriverDivOrgForm)reportForm,Filter.USER);
        Integer[] divGeos = Util.getDivGeos(sys,(DriverDivOrgForm)reportForm,Filter.USER);
        
        int order = 0;
        switch(Integer.parseInt(reportForm.getOrder1())) {
            case 1:
                if(reportForm.getOrder2() != null && Integer.parseInt(reportForm.getOrder2()) == 2) {
                    order = VehicleCheckReportDataType.CONTRACT_TYPE;
                }else {
                    order = VehicleCheckReportDataType.CONTRACT;
                }
                break;
            case 2:
                if(reportForm.getOrder2() != null && 
                        Integer.parseInt(reportForm.getOrder2()) == 1) {
                    order = VehicleCheckReportDataType.TYPE_CONTRACT;
                }else {
                    order = VehicleCheckReportDataType.TYPE;
                }
        }
        
        ReportVehiclePursuitRouteData result = sys.getVehicleReportFacade().getVehiclePursuitRouteList(new Integer(fromMonth),new Integer(fromYear),
                new Integer(toMonth),new Integer(toYear),divOrgs,divGeos,messages,order,sys.getUserSessionData());
        
        ReportVehiclePursuitRouteFactory factory = new ReportVehiclePursuitRouteFactory("SeguimientoEnRuta",result,reportForm,sys,messages,order,4);
        return factory.createReport();
    }
} 
    


