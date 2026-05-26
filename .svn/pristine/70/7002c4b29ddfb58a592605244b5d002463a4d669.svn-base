package presentation.translator.web.activity.schedule;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import persistence.datatype.DataTypeReportUtil;
import persistence.util.Util;
import presentation.report.ReportFactory;
import presentation.report.ReportUtil;
import presentation.report.activity.ActivityReportFactory;
import presentation.report.activity.ActivityReportTabletFactory;
import presentation.report.activity.EBtwReportFactory;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.activity.ActivityABMFacade;
import business.activity.ActivityReportFacade;
import communication.activity.report.ActivityReportData;
import communication.activity.report.ActivityReportTabletData;
import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.CountryData;
import communication.administration.user.UserSessionData;
import communication.alert.AlertConstants;
import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.util.MailSender;

public class ActivityUpdateAction extends AbstractAction { 

	// This action is executed from all services that involve updating of 
	// vehicles (edit vehicle, change driver assigned, assign a driver to vehicle, and 
	// change to institutional
	
	public ActionForward action(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
	
		
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        UserSessionData userSessionData = sys.getUserSessionData();		
			
		String comment = "";
		ActivityForm updateData = (ActivityForm) form;
		Integer activityId = new Integer(updateData.getId());
		
		String msg = null;
		msg = updateData.validate(userSessionData);
	
		if (msg != null) {				
			// session.setAttribute("Msg",messages.getText(msg));
			
			userSessionData.setMsg(messages.getText(msg));
			// ojo, cuando hago forward a la pag ActivityView el id del activity se guarda en un atributo llamado activityId
			//session.setAttribute("activityId",updateData.getId());
			
			// session.setAttribute("id",updateData.getId());
			
			userSessionData.setUrl("/activity/schedule/CEPAActivityScheduleEdit.jsp?idActivity="+updateData.getId());
			return mapping.findForward("layout");
		} 
		
		boolean isOld = Boolean.parseBoolean(updateData.getIsOld()); 
		// isOld = ((Boolean)session.getAttribute("isOld")).booleanValue();
		
		//Access ActivityReportFacade
		ActivityReportFacade activityReport = sys.getActivityReportFacade();
		//if (updateData.getState().equals(CEPAActivityConstants.DICTADA)) {
		// Para las actividades nuevas controlo que se haya ingresado resultado para todos los participantes
		if (updateData.getState().equals(CEPAActivityConstants.DICTADA) && !isOld) {
			if (!updateData.getType().equals("datatype.scheduledactivitytype.consulting") && !updateData.getType().equals("datatype.scheduledactivitytype.securitytalk") && activityReport.existsAnyDriverWithOutResultForActivity(activityId,userSessionData)) {
				msg = "generic.activity.activitydata.driverWithOutResult";
				
				/*
				session.setAttribute("Msg",messages.getText(msg));
				session.setAttribute("id",updateData.getId());
				*/
				
				userSessionData.setMsg(messages.getText(msg));
				userSessionData.setUrl("/activity/schedule/CEPAActivityScheduleEdit.jsp?idActivity="+updateData.getId());
				return mapping.findForward("layout");
			}
			if (!sys.isCorporation(new int[] {CorporationData.CEPA,CorporationData.ORICA}) && activityReport.existsAnyTemporalDriverForActivity(activityId,userSessionData)) {
				msg = "generic.activity.activitydata.temporaldriverforresult";
				CEPAActivityScheduleData activityData = activityReport.findCEPAActivityScheduleByPrimaryKey(new Integer(updateData.getId()),userSessionData);
				if(!activityData.getCountryName().equals(CountryData.BRASIL)){
					/*
					session.setAttribute("Msg",messages.getText(msg));
					session.setAttribute("id",updateData.getId());
					*/
					
					userSessionData.setMsg(messages.getText(msg));
					userSessionData.setUrl("/activity/schedule/CEPAActivityScheduleEdit.jsp?idActivity="+updateData.getId());
					return mapping.findForward("layout");
				}
			}
		}
		
		CEPAActivityScheduleData activityOld = activityReport.findCEPAActivityScheduleByPrimaryKey(new Integer(updateData.getId()), userSessionData);
		//boolean isOld = ((Boolean)session.getAttribute("isOld")).booleanValue();
		CEPAActivityScheduleData activityUpdated = ActivityUtil.
			createCEPAActivityScheduleData(userSessionData,updateData,isOld,sys);

		//Access UserABMFacade
		ActivityABMFacade activityABM = sys.getActivityABMFacade();
		if (updateData.getComment() != null){
			comment = updateData.getComment();
		}
		FormFile fileUpload=updateData.getFileUpload();
		if(fileUpload.getFileSize()>0 && fileUpload.getFileName()!=null && fileUpload.getFileName().trim().length()>0){
			String fileName=activityUpdated.getActivityId()+"_"+fileUpload.getFileName().replaceAll(" ", "_");
			InputStream reportStream = fileUpload.getInputStream();
			FileOutputStream fout;
			File file2;
			fout = new FileOutputStream(AlertConstants.FILE_DIRECTORY_ACTIVITY+fileName);
			int c = 0;
			while ( (c=reportStream.read()) != -1){
				fout.write(c);
			}			
			fout.flush();
			fout.close();			
			activityUpdated.setFile(AlertConstants.FILE_DIRECTORY_ACTIVITY+fileName);
		}
		
		activityABM.updateCEPAScheduleActivity(activityUpdated,comment,userSessionData);
	
		
		//=====ACA VA EL ENVIO DE MAILS=====================
		if(hasPDFReport(activityOld) && (activityOld.getState().getResourceBundleId().equals(CEPAActivityScheduleData.EN_SUPERVISION) && 
				activityUpdated.getState().getResourceBundleId().equals(CEPAActivityScheduleData.DICTADA))) {
				
			Collection emails = sys.getCorporationReportFacade().getActivityMails(activityOld,sys.getUserSessionData());

			if(emails.size() > 0) {
				Collection drivers = activityReport.getParticipants(activityId,userSessionData);
				Iterator it = drivers.iterator();
				
				Collection<String> files = new LinkedList<String>();
				Collection<String> names = new LinkedList<String>();
				while(it.hasNext()) {
					DriverData driver = (DriverData)it.next();
					CEPAActivityScheduleRegistryData completeResult = activityReport.getCompleteResult(activityId,driver.getId(),messages,sys.getUserSessionData());        
	
			        String type = activityOld.getType().getResourceBundleId();
			        ReportFactory factory = null;
			        if(type != null && (type.equals(CEPAActivityConstants.EBTW) || type.equals(CEPAActivityConstants.EBTW_PISTA))) {
			        	factory = new EBtwReportFactory(completeResult,activityOld,driver,"Activity",null,form,sys,messages,"PDF");
			        }else if (type != null && type.contains("tablet")){
			        	// Si la actividad tiene formulario de Tablet llamo a otro Factory para mostrar
			        	// el reporte de otra manera.
			        	 ActivityReportTabletData result = sys.getActivityReportFacade().getReportTabletActivity(activityId,driver.getId(),1,1,messages, sys.getUserSessionData());
			        	 result.setMessages(messages);
			        	 factory = new ActivityReportTabletFactory(completeResult,activityOld,driver,"Activity", result,form,sys,messages,"PDF");
			        	 
			        }else {
			            ActivityReportData result = sys.getActivityReportFacade().getReportActivity(activityId,driver.getId(),1,1,sys.getUserSessionData());
			              
			            result.setMessages(messages);
			        	factory = new ActivityReportFactory(completeResult,activityOld,driver,"Activity",
			        			result,form,sys,messages,"PDF");
			        }
			        
			        try{
			        	JFreeReportBoot.getInstance().start();
			        }catch(Throwable e){
			        	e.printStackTrace();
			        	// Intento de nuevo, porque el primer reporte salta exception al buscar las fuentes (tipos de letras)
			        	JFreeReportBoot.getInstance().start();
			        }
			        
			        String fileName = "Activity_"+activityId+"_"+driver.getFirstName()+" "+driver.getLastName()+".pdf";
			 		String file = AlertConstants.TEMP_DIRECTORY_ACTIVITIES+fileName;
			    	ReportUtil reportUtil = new ReportUtil();
			    	reportUtil.executeReport(mapping,response,factory.createReport(),"PDFFile",session,file,true);
			    	
			    	files.add(file);
			    	names.add(fileName);
				}
				
				String body = "";
				MailSender sender = new MailSender();
				int count = 0;
				boolean send = false;
				String subject = messages.getText("generic.messages.report")+" - "+messages.getText(activityOld.getType().getResourceBundleId())+" - "+Util.formatDate(activityOld.getDate());
				while(!send && count < 10) {
					try {
						sender.sendImageMandrill(emails,
								"CEPA DataCenter", 
								AlertConstants.EMAIL_SUPPORT,
								subject, body, files,names,
								new LinkedList(),null);
							send = true;
					}catch (Exception e) {
						e.printStackTrace();
					}
					count++;
				}
			}
		}
		//==================================================
				
		// Clean session			
		session.removeAttribute("ActivityForm");
		session.removeAttribute("isOld");
			
		// ojo, cuando hago forward a la pag ActivityView el id del vehiculo
		// se guarda en un atributo llamado id				
		// request.setAttribute("id",updateData.getId());
		userSessionData.setUrl("/activity/schedule/CEPAActivityScheduleView.jsp?idActivity="+updateData.getId());
		return mapping.findForward("layout");
	}

    private boolean hasPDFReport(CEPAActivityScheduleData activity) {
    	Collection<String> noPDF = new LinkedList<String>();
    	noPDF.add(CEPAActivityConstants.COMMENTARYDRIVEARGENTINA);
    	noPDF.add(CEPAActivityConstants.COMMENTARYDRIVEARGENTINA_NUEVO);
    	noPDF.add(CEPAActivityConstants.COMMENTARYDRIVEHIGHRISK);
    	noPDF.add(CEPAActivityConstants.COMMENTARYDRIVENALCO);
    	noPDF.add(CEPAActivityConstants.COMMENTARYDRIVE_NOVARTIS);
    	noPDF.add(CEPAActivityConstants.COMMENTARYDRIVE_JJ2);
    	noPDF.add(CEPAActivityConstants.COMMENTARYDRIVE_RENOVATION);
    	noPDF.add(CEPAActivityConstants.INITIAL_FOREIGN_EVALUATION);
		if(!activity.getIsOld()) {
			if(activity.getDefinition().isHaveIndividualReport()) {
				return true;
			}else {
				if(!noPDF.contains(activity.getType().getResourceBundleId())) {
					return true;
				}
			}
		}
		return false;
	}

	public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
		// ojo, cuando hago forward a la pag VehicleView el id del vehiculo se guarda en un atributo llamado id        	
         // session.setAttribute("id", ((ActivityForm) form).getId());
    	
         SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
         UserSessionData userSessionData = sys.getUserSessionData();
         userSessionData.setUrl("/activity/schedule/CEPAActivityScheduleView.jsp?idActivity="+((ActivityForm) form).getId());
         return mapping.findForward("layout");
    }            
}
