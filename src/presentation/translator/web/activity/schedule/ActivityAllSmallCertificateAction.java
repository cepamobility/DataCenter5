/*
 * Created on 24-feb-2006
 */
package presentation.translator.web.activity.schedule;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.administration.corporation.tables.CorporationData;
import communication.alert.AlertConstants;
import communication.driver.tables.DriverData;
import communication.language.Text;

/**
 * @author fcarriquiry
 *
 */
public class ActivityAllSmallCertificateAction extends AbstractAction {


	public ActionForward action(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	 
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        
    	Integer activityId = new Integer(((DynaActionForm)form).getString("activity"));
    	ActivityReportFacade activityReport = sys.getActivityReportFacade();
    	CEPAActivityScheduleData activity = activityReport.findCEPAActivityScheduleByPrimaryKey(activityId, sys.getUserSessionData());
		Collection drivers = activityReport.getParticipants(activityId,sys.getUserSessionData());
		
    	Iterator itDrivers = drivers.iterator();
    	String[][] links = new String[drivers.size()][2];
    	int index = 0;
    	
    	File f = new File(AlertConstants.TEMP_DIRECTORY_CARTERINHA+"out/Carterinha.zip");
        OutputStream outZip = new FileOutputStream(f);
    	ZipOutputStream zip = new ZipOutputStream(outZip);
		while(itDrivers.hasNext()) {
			DriverData driver = (DriverData)itDrivers.next();
			String name = Util.formatFile(driver.getFirstName().trim() + " " + driver.getLastName().trim());
			SmallCertificateDataSource dataSource = new SmallCertificateDataSource(driver, activity, messages);
    	
	    	HashMap<String,Object> parameters = new HashMap<String,Object>();
	    	String folder = "";
	    	String file = "";
	    	String fileName = "";
	    	switch(sys.getCorporationId()) {
	    		case CorporationData.DOW:
	    			folder = AlertConstants.TEMP_DIRECTORY_CARTERINHA;
	    			file = AlertConstants.TEMP_DIRECTORY_CARTERINHA_OUT;
	    			fileName = "Carterinha";
	    			break;
	    		case CorporationData.FORESTALORIENTAL:
	    			folder = AlertConstants.UPM_DIRECTORY_CARNET;
	    			file = AlertConstants.UPM_DIRECTORY_CARNET_OUT;
	    			fileName = "Certificado";
	    			break;
	    		case CorporationData.MONTES_DE_PLATA:
	    			folder = AlertConstants.MDP_DIRECTORY_CARNET;
	    			file = AlertConstants.MDP_DIRECTORY_CARNET_OUT;
	    			fileName = "Certificado";
	    			break;
	    	}
	    	fileName += "_"+name+"_"+activityId+".pdf";
	    	if(sys.isCorporation(CorporationData.DOW) && (activity.getType().getResourceBundleId().equals(CEPAActivityConstants.TRANINGONTHEJOB) || activity.getType().getResourceBundleId().equals(CEPAActivityConstants.TRAINING_ON_THE_JOB_TABLET))) {
	    		parameters.put("back", folder+"carterinha_toj.jpg");
	    	}else {
	    		parameters.put("back", folder+"carterinha.jpg");
	    	}
	
	        FileInputStream input2 = new FileInputStream(new File(folder+"report.jasper"));
	
	        JasperReport jasperReport = (JasperReport)JRLoader.loadObject(input2);
	        String link = file+fileName;
	        links[index][0] = name;
	        links[index][1] = link;
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	        
	        JRPdfExporter exporter = new JRPdfExporter();
	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
	        exporter.exportReport();
	        index++;
	        
	        zip.putNextEntry(new ZipEntry(fileName));
	        zip.write(out.toByteArray());
	        zip.closeEntry();


		}
    	zip.close();

		response.setContentLength((int)f.length());
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=\"carterinha.zip\"");
		
        InputStream input = new FileInputStream(f);
        copy(input, response.getOutputStream());

		return null;
    }


    private void copy(InputStream input, OutputStream output) throws Exception {
    	byte[] buffer = new byte[4096];
    	int read;
    	while ((read = input.read(buffer)) != -1) {
    	    output.write(buffer, 0, read);
    	}
    	input.close();

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
    


