/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.activity.schedule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.table.AbstractTableModel;

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
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportFactory;
import presentation.report.ReportUtil;
import presentation.report.activity.ActivityReportFactory;
import presentation.report.activity.ActivityReportTabletFactory;
import presentation.report.activity.EBtwReportFactory;
import presentation.report.reporterjj.CommentaryDriveDataSource;
import presentation.report.reporterjj.SelfEvaluationData;
import presentation.report.reporterjj.SelfEvaluationReportFactory;
import presentation.report.vehicle.ReportCimsaCheckListFactory;
import presentation.report.vehicle.ReportSyngentaCheckListFactory;
import presentation.translator.web.activity.report.ActivityReportBTW;
import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.activity.ActivityReportFacade;

import communication.activity.report.ActivityReportData;
import communication.activity.report.ActivityReportTabletData;
import communication.activity.report.ActivityReportTabletDataType;
import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.activity.tables.CheckListData;
import communication.activity.tables.ItemData;
import communication.activity.tables.PointData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.CountryData;
import communication.alert.AlertConstants;
import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.util.EmptyDataSource;
import communication.util.Filter;
import communication.util.ListResult;
import communication.util.MailSender;
import communication.vehicle.report.ReportCimsaCheckListData;
import communication.vehicle.report.ReportSyngentaCheckListData;
import communication.vehicle.tables.VehicleCheckData;


/**
 * @author adocarmo
 *
 */
public class SendActivityReportAction extends AbstractAction {

	
    public ActionForward action(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response) throws Exception {

    	try{
	        HttpSession session = request.getSession();
	        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
	        Text messages = sys.getText();
	
			DynaActionForm reportForm = (DynaActionForm)form;
			Integer activityId = new Integer(reportForm.getString("activity"));
			
			StringTokenizer tokenizer = new StringTokenizer(reportForm.getString("address"),",");
			Collection addressTo = new LinkedList();
			while(tokenizer.hasMoreTokens()) {
				String address = tokenizer.nextToken().trim();
				if(!Util.checkEmail(address)) {
			    	session.setAttribute("Msg", messages.getText("generic.sendemail.wrongaddress")+": "+address);
			    	sys.getUserSessionData().setUrl("/activity/schedule/sendReports.jsp?activity="+activityId);
					return mapping.findForward("success");
				}else {
					addressTo.add(address);
				}
			}

			
			Collection drivers = Util.stringToCollection(reportForm.getString("list"));
			
	        // Inicio el JFreeReport:
	        try{
	        	JFreeReportBoot.getInstance().start();
	        }catch(Exception e){
	        	// La primera vez que se corre puede dar error de cache de Pentahoo.
	        	// asi que lo corremos de nuevo...jeje
	        	JFreeReportBoot.getInstance().start();
	        }
	        
	    	MailSender sender = new MailSender();
			Collection bcc = new LinkedList();
			bcc.add("cepareport@gmail.com");	
			String body = Util.toHTMLString(reportForm.getString("body"));
			int data = Integer.parseInt(reportForm.getString("data"));
			String subject = reportForm.getString("subject");
			int sendType = Integer.parseInt(reportForm.getString("type"));

			Collection<String> files = new LinkedList<String>();
			Collection<String> names = new LinkedList<String>();
	        
	        session.removeAttribute("ReportCheckListForm");   
	        
	        ActivityReportFacade facade = sys.getActivityReportFacade();
	        CEPAActivityScheduleData activitySchedule = facade.findCEPAActivityScheduleByPrimaryKey(new Integer(activityId)	, false, sys.getUserSessionData());

	        String type = activitySchedule.getType().getResourceBundleId();
	        
	        Iterator it = drivers.iterator();

	        HashMap<String,Integer> media = null;
	        if(activitySchedule.hasNewReports()) {
	        	media = facade.getMediaActivity(activitySchedule.getType().getResourceBundleId(), new Integer(sys.getCorporationId()), new Integer(activitySchedule.getCountryId()), sys.getUserSessionData());
	        }

	        while(it.hasNext()) {
	        	Integer driverId = new Integer((String)it.next());
	        	CEPAActivityScheduleRegistryData completeResult = facade.getCompleteResult(activityId, driverId, sys.getUserSessionData());
		        DriverData driver = completeResult.getDriver();
		        if(data == 1 || data == 3) {
			        String fileName = null;
			        String file = null;
			        if(activitySchedule.hasNewReports()) {
			     		fileName = "Report_"+Util.formatFile(driver.getFirstName()+" "+driver.getLastName())+".pdf";
			     		file = AlertConstants.TEMP_DIRECTORY_REPORTER_JJ+fileName;
			            ActivityReportTabletDataType dataType = sys.getActivityReportFacade().getReportTabletActivity(activityId, driverId, messages, sys.getUserSessionData());
			       	    ActivityReportBTW reportBTW = new ActivityReportBTW(completeResult,dataType,sys,media);
			       	    FileOutputStream out = new FileOutputStream(new File(file));
			       	    reportBTW.executeReport(out, Integer.parseInt(request.getParameter("type")));
			        } else if(type.equals(CEPAActivityConstants.REPORTERJJ_COMMENTARYDRIVE)) {
			     		fileName = (type.equals(CEPAActivityConstants.REPORTERJJ_SELFEVALUATION)) ? "ReporterJJ_"+messages.getText("reporterjj.selfevaluation.point")+"_"+driver.getCorporationId()+".pdf" : "ReporterJJ_"+messages.getText("scheduledactivitytype.reporterjj.commentarydrive")+"_"+driver.getCorporationId()+".pdf";
			     		file = AlertConstants.TEMP_DIRECTORY_REPORTER_JJ+fileName;
			        	reporterJJReport(type, sys, completeResult,fileName);
			        }else {
		
				        ReportFactory factory = null;
				        if(type.equals(CEPAActivityConstants.EBTW) || type.equals(CEPAActivityConstants.EBTW_PISTA)) {
				        	factory = new EBtwReportFactory(completeResult,activitySchedule,driver,"Activity", null,form,sys,messages,"PDF");
				        }else if (type.contains("tablet")){
				        	ActivityReportTabletData result = facade.getReportTabletActivity(activityId,driverId,1,1,messages, sys.getUserSessionData());
			       	        result.setMessages(messages);
			       	        factory = new ActivityReportTabletFactory(completeResult,activitySchedule,driver,"Activity",result,form,sys,messages,"PDF");
			        	}else {
			        		ActivityReportData result = sys.getActivityReportFacade().getReportActivity(activityId,driverId,1,1,sys.getUserSessionData());
			        		result.setMessages(messages);
			        		factory = new ActivityReportFactory(completeResult,activitySchedule,driver,"Activity",result,form,sys,messages,"PDF");
			        	}
			        
				        JFreeReport report = factory.createReport();
			        
				        // Muestro el reporte:
				        fileName = Util.formatFile(messages.getText(type)+"_"+driver.getFirstName()+"_"+driver.getLastName()+".pdf");
				        file = AlertConstants.TEMP_DIRECTORY_ACTIVITIES+fileName;
		
				        new ReportUtil().executeReport(mapping,response, report,"PDFFile",session,file,true);
			        }
			        files.add(file);
			        names.add(fileName);
			        
		        }
		        
		        if((data == 2 || data == 3) && completeResult.isApprouved()) {
			        String fileName = null;
			        String file = null;

			        HashMap<String,Object> parameters = new HashMap<String,Object>();
			    	boolean sabMillerH = sys.isCorporation(CorporationData.SABMILLER) && activitySchedule.getCountryId().equals(String.valueOf(CountryData.HONDURAS_ID));
			    	if(sabMillerH) {
				        if(!Util.empty(driver.getCorporationId())) {
				        	parameters.put("ci",  driver.getCorporationId());
				        }else {
				        	parameters.put("ci",  "----");
				        }
			    		parameters.put("fondo", AlertConstants.TEMP_DIRECTORY_CERTIFICADOS+"fondo_sabmillerH.jpg");
			    	}else {
			    		parameters.put("fondo", AlertConstants.TEMP_DIRECTORY_CERTIFICADOS+"fondo.jpg");
			    	}
			    	
		            String driverName = driver.getFirstName() + " " + driver.getLastName();
		            if(!Util.empty(driver.getCorporationId()) && (sys.isForestalUnified() || sys.isCorporation(CorporationData.FORESTALES))) {
		            	driverName += " (C.I. " + driver.getCorporationId() + ")";
		            }
		            parameters.put("conductor",  driverName);
		            parameters.put("actividad", messages.getText(completeResult.getActivity().getType().getResourceBundleId()));
			        if(sabMillerH) {
			        	Calendar c = Calendar.getInstance();
			        	c.setTime(completeResult.getActivity().getDate());
			        	String s = c.get(Calendar.DATE)+" días del mes de "+messages.getText("generic.month."+String.valueOf(c.get(Calendar.MONTH)+1))+" de "+c.get(Calendar.YEAR);
			        	parameters.put("fecha", s);
			        }else {
			        	parameters.put("fecha", Util.formatDate(completeResult.getActivity().getDate()));
			        }
		            String vigencia = (completeResult.getActivity().getDefinition().getTimeExpiry() == 0) ? "---" : completeResult.getActivity().getDefinition().getTimeExpiry() + " "  + messages.getText("generic.messages.years");
		            parameters.put("vigencia", vigencia);

			    	boolean withContent = (completeResult.getActivity().getType().getResourceBundleId().equals(CEPAActivityConstants.BTW1) 
			    			&& sys.getUserSessionData().getLenguage().equals("pt") 
			    			&& sys.isBrasilCorporation() 
			    			&& !sys.isCorporation(new int[]{CorporationData.MONSANTO,CorporationData.DOW})); 
			
			    	String activityKey = completeResult.getActivity().getType().getResourceBundleId();

			    	String fileNameJasper = (withContent) ? "certificados_br" : "certificados";
			        if(activityKey.equals(CEPAActivityConstants.BTW3SEGUNDARENOVACION) || activityKey.equals(CEPAActivityConstants.BTW3TERCERARENOVACION) || activityKey.equals(CEPAActivityConstants.CLEAT))
			        	fileNameJasper = "certificados_largo";
			    	if(sys.isCorporation(CorporationData.FEMSA_BR)) {
			    		//JasperCompileManager.compileReportToFile(AlertConstants.TEMP_DIRECTORY_CERTIFICADOS+"certificados69.jrxml", AlertConstants.TEMP_DIRECTORY_CERTIFICADOS+"certificados69.jasper");
				        fileNameJasper += String.valueOf(CorporationData.FEMSA_BR);
			    	}
			    	if(sys.isCorporation(CorporationData.SABMILLER) && completeResult.getActivity().getCountryId().equals(String.valueOf(CountryData.HONDURAS_ID))) {
				        fileNameJasper = "certificado_sabmillerH";	    		
			    	}
		            FileInputStream input2 = new FileInputStream(new File(AlertConstants.TEMP_DIRECTORY_CERTIFICADOS+fileNameJasper+".jasper"));


		            JasperReport jasperReport = (JasperReport)JRLoader.loadObject(input2);
		            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new EmptyDataSource());
		            
		     		fileName = "Certificado_" + driver.getFirstName() + "_" + driver.getLastName() + ".pdf";
		            file = AlertConstants.TEMP_DIRECTORY_CERTIFICADOS+"out/"+fileName;
		            FileOutputStream out = new FileOutputStream(new File(file));

		     		JRPdfExporter exporter = new JRPdfExporter();
		            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
		            exporter.exportReport();
		            
		            files.add(file);
			        names.add(fileName);

		        }

		        if(sendType == 2) {
					sendEmail(subject, addressTo, sender, bcc, body, files, names, sys);
			        files = new LinkedList<String>();
			        names = new LinkedList<String>();
		        }
	        }
	        
	        if(sendType == 1) {
	        	sendEmail(subject, addressTo, sender, bcc, body, files, names, sys);
	        }
			
	    	session.setAttribute("Msg", messages.getText("generic.sendemail.reportsended"));
	    	sys.getUserSessionData().setUrl("/activity/schedule/CEPAActivityScheduleView.jsp?id="+activityId);
			return mapping.findForward("success");
    	}catch(Exception e){
    		e.printStackTrace();
    		throw e;
    	}
    }



    
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

    private void reporterJJReport(String activityType, SystemAccess sys,CEPAActivityScheduleRegistryData registryData, String fileName) throws Exception {
    	Text messages = sys.getText();
    	
    	DriverData driver = registryData.getDriver();
 		String file = AlertConstants.TEMP_DIRECTORY_REPORTER_JJ+fileName;
        
 		int checkListId = (activityType.equals(CEPAActivityConstants.REPORTERJJ_SELFEVALUATION)) ? CheckListData.REPORTERJJ_SELF_EVALUATION : CheckListData.COMMENTARY_DRIVE_JJ2;
		CheckListData checkList = sys.getActivityReportFacade().findCheckList(checkListId, sys.getUserSessionData());

		AbstractTableModel data = null;

		CommentaryDriveDataSource dataSource = new CommentaryDriveDataSource(registryData,checkList,sys);

        HashMap<String,Object> parameters2 = new HashMap<String,Object>();
        parameters2.put("logos", AlertConstants.TEMP_DIRECTORY_REPORTER_JJ+"images/logos.jpg");
        parameters2.put("header", AlertConstants.TEMP_DIRECTORY_REPORTER_JJ+"images/header.jpg");
        parameters2.put("footer", AlertConstants.TEMP_DIRECTORY_REPORTER_JJ+"images/footer.jpg");
        parameters2.put("title", messages.getText("scheduledactivitytype.reporterjj.commentarydrive"));
        parameters2.put("tabla1",AlertConstants.TEMP_DIRECTORY_REPORTER_JJ+"images/tabla1.jpg");
        parameters2.put("tabla2",AlertConstants.TEMP_DIRECTORY_REPORTER_JJ+"images/tabla3.jpg");
        parameters2.put("tabla3",AlertConstants.TEMP_DIRECTORY_REPORTER_JJ+"images/tabla2.jpg");
        parameters2.put("tabla4",AlertConstants.TEMP_DIRECTORY_REPORTER_JJ+"images/tabla4.jpg");
        parameters2.put("tabla5",AlertConstants.TEMP_DIRECTORY_REPORTER_JJ+"images/group_header.jpg");
        parameters2.put("tabla6",AlertConstants.TEMP_DIRECTORY_REPORTER_JJ+"images/tabla5.jpg");
        parameters2.put("date", Util.formatDate(registryData.getActivity().getDate()));
        parameters2.put("drivertxt",messages.getText("generic.driver.driverdata.firstname"));
        parameters2.put("wwidtxt",messages.getText("generic.driver.driverdata.wwid").toUpperCase());
    	parameters2.put("managertxt",messages.getText("generic.activity.cc.gerente"));
    	
        parameters2.put("ccdatetxt",messages.getText("generic.activity.date"));
        parameters2.put("driver",driver.getFirstName()+" "+driver.getLastName());
        parameters2.put("wwid",driver.getCorporationId());
        parameters2.put("manager",registryData.getInstructor());
        parameters2.put("ccdate",Util.formatDate(Calendar.getInstance().getTime()));
        parameters2.put("obstext", messages.getText("generic.messages.observations"));
        parameters2.put("obsvalue", registryData.getObs());

        //JasperCompileManager.compileReportToFile(AlertConstants.TEMP_DIRECTORY_REPORTER_JJ+"jasper/ReporterJJ_CC.jrxml", AlertConstants.TEMP_DIRECTORY_REPORTER_JJ+"jasper/ReporterJJ_CC.jasper");
        FileInputStream input2 = new FileInputStream(new File(AlertConstants.TEMP_DIRECTORY_REPORTER_JJ+"jasper/ReporterJJ_CC.jasper"));

        JasperReport jasperReport = (JasperReport)JRLoader.loadObject(input2);
        FileOutputStream out2 = new FileOutputStream(new File(file));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters2, dataSource);
       
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out2);
        exporter.exportReport();       
        out2.flush();
        out2.close();
	        

    }
} 
    


