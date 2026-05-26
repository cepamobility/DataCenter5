/*
 * Created on 24-feb-2006
 */
package presentation.translator.web.activity.schedule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.activity.ActivityReportFacade;

import communication.activity.report.SmallCertificateDataSource;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.administration.corporation.tables.CorporationData;
import communication.alert.AlertConstants;
import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.util.EmptyDataSource;

/**
 * @author fcarriquiry
 *
 */
public class DriverActivitySmallCertificateAction extends AbstractAction {


	public ActionForward action(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	 
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        
    	Integer activityId = new Integer(((DynaActionForm)form).getString("activity"));
    	Integer driverId = new Integer(((DynaActionForm)form).getString("driver"));
    	ActivityReportFacade activityReport = sys.getActivityReportFacade();
    	CEPAActivityScheduleData activity = activityReport.findCEPAActivityScheduleByPrimaryKey(activityId, sys.getUserSessionData());

    	DriverData driver = sys.getDriverReportFacade().findDriverByPrimaryKey(driverId, sys.getUserSessionData());
		String name = driver.getFirstName() + " " + driver.getLastName();
		SmallCertificateDataSource dataSource = new SmallCertificateDataSource(driver, activity, messages);
	
	
    	HashMap<String,Object> parameters = new HashMap<String,Object>();
    	String folder = "";
    	String file = "";
    	switch(sys.getCorporationId()) {
    		case CorporationData.DOW:
    			folder = AlertConstants.TEMP_DIRECTORY_CARTERINHA;
    			file = "Carterinha";
    			break;
    		case CorporationData.FORESTALORIENTAL:
    			folder = AlertConstants.UPM_DIRECTORY_CARNET;
    			file = "Certificado";
    			break;
    		case CorporationData.MONTES_DE_PLATA:
    			folder = AlertConstants.MDP_DIRECTORY_CARNET;
    			file = "Certificado";
    			break;
    	}
    	
        parameters.put("back", folder+"carterinha.jpg");

        //JasperCompileManager.compileReportToFile(folder+"report.jrxml", folder+"report.jasper");
        FileInputStream input2 = new FileInputStream(new File(folder+"report.jasper"));

        JasperReport jasperReport = (JasperReport)JRLoader.loadObject(input2);
        String link = file+"_"+name+"_"+activityId;
        response.setHeader("Content-Type", "application/pdf");
        link = Util.stringToSlug(link.replaceAll(" ", "_"));
        link += ".pdf";
   	 	response.setHeader("Content-Disposition", "inline; filename=\""+link);
        OutputStream out = response.getOutputStream();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
        exporter.exportReport();       
        out.flush();
        out.close();

        return null;
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
    


