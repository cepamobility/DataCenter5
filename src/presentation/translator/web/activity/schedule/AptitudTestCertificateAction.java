/*
 * Created on 24-feb-2006
 */
package presentation.translator.web.activity.schedule;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;

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

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.alert.AlertConstants;
import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.util.EmptyDataSource;

/**
 * @author fcarriquiry
 *
 */
public class AptitudTestCertificateAction extends AbstractAction {

    private static final String type = null;


	public ActionForward action(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	 
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        String date=request.getParameter("date");
        String expirity=request.getParameter("expirity");
        String result=request.getParameter("result");
    	String idDriver=request.getParameter("driver");

        DriverData driver = sys.getDriverReportFacade().findDriverByPrimaryKey(Integer.valueOf(idDriver), sys.getUserSessionData());
        String driverName = driver.getFirstName() + " " + driver.getLastName();
/*
    	String formatOutput ="PDF" ;

     	request.setAttribute("PBTW", "true");
     	
    	session.setAttribute("output",formatOutput);
    	//if (request.getParameter("output")==null || !request.getParameter("output").equalsIgnoreCase("PDF")) formatOutput="";    	
           	
        JFreeReport report;

        ReportFactory factory = null;
        
       
        
        
        factory = new AptitudTestCertificateTabletFactory(driver,"AptitudTestCertificate",
        			 		null,form,sys,messages,formatOutput,date,result,expirity);
        
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
        		messages.getText("generic.driver.aptitudtest"),
        		messages.getText("query.activity.generalreport.reportsubtitle"),
        		true);
               */
        
        HashMap<String,Object> parameters = new HashMap<String,Object>();
        parameters.put("fondo", AlertConstants.TEMP_DIRECTORY_GABINETES+"gabinetes.jpg");
        parameters.put("titulo", messages.getText("generic.driver.aptitudtest").toUpperCase());
        parameters.put("subtitulo", messages.getText("query.activity.generalreport.reportsubtitle"));
        parameters.put("actividad", messages.getText("generic.activity.activitydata.activitytype").toUpperCase()+": "+messages.getText("generic.driver.aptitudtest"));
        parameters.put("pais", messages.getText("corporation.country").toUpperCase()+": "+messages.getText(driver.getCountryName()));
        parameters.put("fecha", messages.getText("generic.activity.activitydata.date").toUpperCase() + ": "+ date);
        parameters.put("vencimiento", messages.getText("generic.activity.activitydata.activityexpiry").toUpperCase() + ": "+ expirity);
        parameters.put("conductor", messages.getText("generic.activity.nameandlastnameofparticipant").toUpperCase() + ": "+ driverName);
        parameters.put("ci", messages.getText("driver.data.ci").toUpperCase() + ": "+ driver.getCorporationId());
        parameters.put("contratista", messages.getText("datatype.ownership.contract").toUpperCase() + ": "+ driver.getDivOrgName(1, 1));
        parameters.put("resultado", messages.getText("generic.activity.result").toUpperCase() + ": "+ messages.getText(result));
        


      //JasperCompileManager.compileReportToFile(AlertConstants.TEMP_DIRECTORY_GABINETES+"gabinetes.jrxml", AlertConstants.TEMP_DIRECTORY_GABINETES+"gabinetes.jasper");
        FileInputStream input2 = new FileInputStream(new File(AlertConstants.TEMP_DIRECTORY_GABINETES+"gabinetes.jasper"));


        JasperReport jasperReport = (JasperReport)JRLoader.loadObject(input2);
        response.setHeader("Content-Type", "application/pdf");
   	 	response.setHeader("Content-Disposition", "inline; filename=\"Gabinete_"+Util.stringToSlug(driverName)+".pdf");
        OutputStream out = response.getOutputStream();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new EmptyDataSource());
        
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
    


