/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.activity.schedule;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

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
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.activity.ActivityGeneralReportFactory;
import presentation.report.activity.AllActivitiesGeneralReportFactory;
import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.activity.report.ActivityGeneralReportData;
import communication.activity.report.AllActivitiesGeneralReportData;
import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.administration.corporation.tables.CorporationData;
import communication.language.Text;
import communication.security.SecurityConstants;

/**
 * @author adocarmo
 */
public class ActivityGeneralReportAction extends AbstractAction {

		
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

		ActivityGeneralReportForm data = (ActivityGeneralReportForm)form;
        
        if(data.getReportAction().equals("doSearch")) {
        	
        	String message = data.validate(sys.getUserSessionData());
        	
    		if(message != null) {				
    		    //Some attribute data is invalid	
    			//Si en el struts config indico que el form es de sesion la línea de abajo es inneseraria
    			session.setAttribute("Msg", messages.getText(message));
    			return mapping.findForward("back");    			
    		}   		

            JFreeReportBoot.getInstance().start();
            JFreeReport report = getReport(sys,data,1);
            session.setAttribute("Bookmark",data.getBookmark());
            return new ReportUtil().executeReport(mapping,response,report,data.getOutput(),session,"driver",true);

        }else {
            CEPAActivityScheduleData activity = sys.getActivityReportFacade().findCEPAActivityScheduleByPrimaryKey(new Integer(data.getId()),sys.getUserSessionData());
            Object[][] participants = sys.getActivityReportFacade().getParticipantsRegistry(activity.getActivityId(),sys.getUserSessionData());
            
            int[][] clasifications;
            if (!activity.getIsOld()) {
            	
            		
            	clasifications = new int[6][2];
            	
	            for(int i = 0; i < participants.length; i++) {
	                if(participants[i][3].equals(CEPAActivityConstants.APRUVED)) {
	                    clasifications[0][0]++; 
	                }else if(participants[i][3].equals(CEPAActivityConstants.APRUVEDWITHOBSERVATIONS)) {
	                    clasifications[1][0]++; 
	                }else if(participants[i][3].equals(CEPAActivityConstants.NOTAPRUVED)) {
	                    clasifications[2][0]++; 
	                }else if(participants[i][3].equals(CEPAActivityConstants.THEORETICALPENDING)){
	                	clasifications[3][0]++;
	                }else if(participants[i][3].equals(CEPAActivityConstants.PRACTICEPENDING)){
	                	clasifications[4][0]++;
	                }else{
	                    clasifications[5][0]++; 
	                }
	            }
	            for(int i = 0; i < 6; i++) {
	                clasifications[i][1] = (int)(clasifications[i][0] * 100 / participants.length); 
	            }
            	
	            /* Commented by Gus:
            	clasifications = new int[4][2];
	            for(int i = 0; i < participants.length; i++) {
	                if(participants[i][3].equals("generic.activity.note.apruved")) {
	                    clasifications[0][0]++; 
	                }else if(participants[i][3].equals("generic.activity.note.apruvedwithobservations")) {
	                    clasifications[1][0]++; 
	                }else if(participants[i][3].equals("generic.activity.note.notapruved")) {
	                    clasifications[2][0]++; 
	                }else {
	                    clasifications[3][0]++; 
	                }
	            }
	            for(int i = 0; i < 4; i++) {
	                clasifications[i][1] = (int)(clasifications[i][0] * 100 / participants.length); 
	            }
	            */
            }
            else {
            	clasifications = new int[12][2];
	            for(int i = 0; i < participants.length; i++) {
	                if(participants[i][3].equals("dc4.notasdescripcion.nddesccorta1")) {
	                    clasifications[0][0]++; 
	                }else if(participants[i][3].equals("dc4.notasdescripcion.nddesccorta2")) {
	                    clasifications[1][0]++; 
	                }else if(participants[i][3].equals("dc4.notasdescripcion.nddesccorta3")) {
	                    clasifications[2][0]++;
	                }else if (participants[i][3].equals("dc4.notasdescripcion.nddesccorta4")) {
	                    clasifications[3][0]++;	                	
	                }else if (participants[i][3].equals("dc4.notasdescripcion.nddesccorta5")) {
	                    clasifications[4][0]++;	                	
	                }else if (participants[i][3].equals("dc4.notasdescripcion.nddesccorta6")) {
	                    clasifications[5][0]++;	                	
	                }else if (participants[i][3].equals("dc4.notasdescripcion.nddesccorta7")) {
	                    clasifications[6][0]++;	                	
	                }else if (participants[i][3].equals("dc4.notasdescripcion.nddesccorta8")) {
	                    clasifications[7][0]++;	                	
	                }else if (participants[i][3].equals("dc4.notasdescripcion.nddesccorta9")) {
	                    clasifications[8][0]++;	                	
	                }else if (participants[i][3].equals("dc4.notasdescripcion.nddesccorta10")) {
	                    clasifications[9][0]++;	                	
	                }else if (participants[i][3].equals("dc4.notasdescripcion.nddesccorta11")) {	                	
	                    clasifications[10][0]++;	                
	                }else {
	                    clasifications[11][0]++; 
	                }
	            }
	            for(int i = 0; i < 12; i++) {
	                clasifications[i][1] = (int)(clasifications[i][0] * 100 / participants.length); 
	            }
            }
            ActivityGeneralReportData reportData = new ActivityGeneralReportData(participants,sys.getText());
            
            JFreeReportBoot.getInstance().start();
            ActivityGeneralReportFactory factory = new ActivityGeneralReportFactory("Activity",reportData,sys,activity,clasifications,data.getOutput());
            JFreeReport report = factory.createReport();        

            final DefaultCategoryDataset result = new DefaultCategoryDataset();
            if (!activity.getIsOld()) {
            	result.setValue(clasifications[0][0], messages.getText(CEPAActivityConstants.APRUVED),"");
            	result.setValue(clasifications[1][0], messages.getText(CEPAActivityConstants.APRUVEDWITHOBSERVATIONS),"");
            	result.setValue(clasifications[2][0], messages.getText(CEPAActivityConstants.NOTAPRUVED),"");
            	result.setValue(clasifications[3][0], messages.getText(CEPAActivityConstants.THEORETICALPENDING),"");
            	result.setValue(clasifications[4][0], messages.getText(CEPAActivityConstants.PRACTICEPENDING),"");
            }
            else {
            	result.setValue(clasifications[0][0], messages.getText("dc4.notasdescripcion.nddesccorta1"),"");
            	result.setValue(clasifications[1][0], messages.getText("dc4.notasdescripcion.nddesccorta2"),"");
            	result.setValue(clasifications[2][0], messages.getText("dc4.notasdescripcion.nddesccorta3"),"");
            	result.setValue(clasifications[3][0], messages.getText("dc4.notasdescripcion.nddesccorta4"),"");
            	result.setValue(clasifications[4][0], messages.getText("dc4.notasdescripcion.nddesccorta5"),"");
            	result.setValue(clasifications[5][0], messages.getText("dc4.notasdescripcion.nddesccorta6"),"");
            	result.setValue(clasifications[6][0], messages.getText("dc4.notasdescripcion.nddesccorta7"),"");
            	result.setValue(clasifications[7][0], messages.getText("dc4.notasdescripcion.nddesccorta8"),"");
            	result.setValue(clasifications[8][0], messages.getText("dc4.notasdescripcion.nddesccorta9"),"");
            	result.setValue(clasifications[9][0], messages.getText("dc4.notasdescripcion.nddesccorta10"),"");
            	result.setValue(clasifications[10][0], messages.getText("dc4.notasdescripcion.nddesccorta11"),"");            	
            }

            final JFreeChart chart = ChartFactory.createBarChart("Resultados","Resultados","",result, PlotOrientation.VERTICAL,true,true,false);
            chart.setBackgroundPaint(Color.WHITE);
            report.setProperty("Resultados", chart);
            report.setPropertyMarked("Resultados", true);      
            File imagen = new File(SecurityConstants.IMAGES_WAR_DIRECTORY + 
            		sys.getUserSessionData().getFilter().getLoginName() + 
            		"-activity-" + String.valueOf(System.currentTimeMillis()) + ".png");           
            ChartUtilities.saveChartAsPNG(imagen,chart,600,400);            

            ArrayList arrayGraficas = new ArrayList();
            arrayGraficas.add(imagen.getName());
            
            return new ReportUtil().executeReport(mapping,response,report,request.getParameter("output"),session,"activities",arrayGraficas,true);
        }
				
	}
	
    public JFreeReport getReport(SystemAccess sys,ActivityGeneralReportForm reportForm,int page) throws Exception {
        Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
        Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
        
        Integer monthFrom = (Util.empty(reportForm.getMonth1())) ? null : new Integer(reportForm.getMonth1());
        Integer yearFrom = (Util.empty(reportForm.getYear1())) ? null : new Integer(reportForm.getYear1());

        Integer monthTo = (Util.empty(reportForm.getMonth2())) ? null : new Integer(reportForm.getMonth2());
        Integer yearTo = (Util.empty(reportForm.getYear2())) ? null : new Integer(reportForm.getYear2());
        
        JFreeReport report = null;
        Text messages = sys.getText();
        int first = (reportForm.getOutput().equals("HTML")) ? (page-1)*50 : 0;
        int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;
       
        String[] type = reportForm.getType();
        if(sys.getUserSessionData().getFilter().getCorporationId().intValue() == CorporationData.FORESTALORIENTAL) {
            boolean isBTW = false;
            for(int i = 0; i < type.length && !isBTW; i++) {
                isBTW = type[i].equals(CEPAActivityConstants.BTW3);
            }
            if(isBTW) {
                String[] newTypes = new String[type.length+1];
                newTypes[0] = CEPAActivityConstants.PESADOS_1;
                for(int i = 0; i < type.length; i++) {
                    newTypes[i+1] = type[i];
                }
                type = newTypes;
            }
        }
        Collection<String> state = new LinkedList<String>();
        if(Util.isChecked(reportForm.getPrevista()))
        	state.add(CEPAActivityConstants.PREVISTA);
        if(Util.isChecked(reportForm.getEnSupervision()))
        	state.add(CEPAActivityConstants.ENSUPERVISION);
        if(Util.isChecked(reportForm.getDictada()))
        	state.add(CEPAActivityConstants.DICTADA);
        AllActivitiesGeneralReportData result = sys.getActivityReportFacade().findReportList(type,monthFrom,yearFrom,monthTo,yearTo,divOrgs,divGeos,(String[])state.toArray(new String[0]),first,count,sys.getUserSessionData());
        
        int pages = 1;
        int total = 0;
        if (result != null){
        	result.setMessages(messages);
        	total = result.getTotal();
        }
        
        pages = (int)(total / 50);
        if(pages*50 < total) {
             pages++;
        }
        AllActivitiesGeneralReportFactory factory = new AllActivitiesGeneralReportFactory(messages.getText("generic.data.activity.pl"),result,reportForm,sys,messages,1,pages,page);
        report = factory.createReport();

        return report;
    }	
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }    
	    
	

}
