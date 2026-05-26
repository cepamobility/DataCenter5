package presentation.translator.web.activity.schedule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletOutputStream;
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
import presentation.report.activity.ActivityReportFactory;
import presentation.report.activity.ActivityReportTabletFactory;
import presentation.report.activity.EBtwReportFactory;
import presentation.report.activity.HeavyReportFactory;
import presentation.translator.web.activity.report.ActivityReportBTW;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.activity.ActivityReportFacade;

import communication.activity.report.ActivityReportData;
import communication.activity.report.ActivityReportTabletData;
import communication.activity.report.ActivityReportTabletDataType;
import communication.activity.report.HeavyReportData;
import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.driver.tables.DriverData;
import communication.language.Text;

public class ActivityReportActionAllDrivers extends AbstractAction {

	/**
	 * 
	 */
    public ActionForward action(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    		
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        String idActivity = request.getParameter("idActivity");
    	String idDrivers = request.getParameter("idDrivers");
    	String activityType = request.getParameter("activityType");
    	String checklist = request.getParameter("checklist"); // id del tipo de formulario de actividad
    	
     	String formatOutput = "PDF";
    	Collection<String> drivers = stringToCollection(idDrivers);
         
        JFreeReport report;
        ActivityReportFacade facade = sys.getActivityReportFacade();
        CEPAActivityScheduleRegistryData completeResult;
        String idDriver ="";
        DriverData driver;
        ActivityReportData result;
        ReportFactory factory;
        Collection<JFreeReport> reports = new LinkedList<JFreeReport>();
        
        // Para Evaluacion en Cabina Pesados:
        HeavyReportFactory heavyFactory;
        HeavyReportData heavyData;
        
        CEPAActivityScheduleData activitySchedule = facade.findCEPAActivityScheduleByPrimaryKey(new Integer(idActivity),sys.getUserSessionData());
        HashMap<String,Integer> media = null;
   	    ServletOutputStream out = null;

        // Genero los reportes para cada driver de la actividad:
        Iterator<String> it = drivers.iterator();
        while (it.hasNext()){
        	idDriver = (String)it.next();
        	
        	if (activityType != null && activityType.equalsIgnoreCase(CEPAActivityConstants.EVALUACIONENCABINAPESADO) ||
        		activityType.equalsIgnoreCase(CEPAActivityConstants.CURSOPESADOS) ||
        		activityType.equalsIgnoreCase(CEPAActivityConstants.BTWPESADOSCONEVALUACIONENCABINA) ||
        		(checklist != null && checklist.equalsIgnoreCase("14"))
        		){
        		
        		// Reporte Total para el Formulario de Evaluacion en Cabina Pesados:
        		// - EVALUACIONENCABINAPESADO
        		// - CURSOPESADOS
        		// - BTWPESADOSCONEVALUACIONENCABINA
        		// Checklist = 14 (Formulario del tipo Evaluacion en Cabina Pesados)
        		
        		heavyData = sys.getActivityReportFacade().getReportHeavy(
        	       		new Integer(idActivity),new Integer(idDriver),messages,sys.getUserSessionData());
        	    
        	    heavyFactory = new HeavyReportFactory(heavyData ,form,sys,messages);
        	    report = heavyFactory.createReport();
        	    
        	}else{
        		
        		completeResult = facade.getCompleteResult(new Integer(idActivity),
            			new Integer(idDriver),messages,sys.getUserSessionData());        
            	driver = completeResult.getDriver();
            	
                if(activitySchedule.getType().getResourceBundleId().equals(CEPAActivityConstants.EBTW)
                		|| activitySchedule.getType().getResourceBundleId().equals(CEPAActivityConstants.EBTW_PISTA)) {
                	// Reporte eBTW
                	factory = new EBtwReportFactory(completeResult,activitySchedule,driver,
                			"Activity",null,form,sys,messages,formatOutput);
                	
                }else if (activitySchedule.getType().getResourceBundleId().contains("tablet")){
                	// Reporte Total para las actividades con formularios para TABLET 
                	 ActivityReportTabletData result2 = sys.getActivityReportFacade().
                			 getReportTabletActivity(new Integer(idActivity),
   							new Integer(idDriver),1,1,messages,sys.getUserSessionData());
                			 
                			 
        
                	 result2.setMessages(messages);
                	 factory = new ActivityReportTabletFactory(completeResult,activitySchedule,driver,"Activity",
         			 		result2,form,sys,messages,formatOutput);
                }else {
	        		// Reporte Total para todas las demas actividades
	        		result = sys.getActivityReportFacade().
	        			getReportActivity(new Integer(idActivity),
	        				new Integer(idDriver),1,1,sys.getUserSessionData());
	              
	        		result.setMessages(messages);
	        
	
	        		factory = new ActivityReportFactory(completeResult,
	        				activitySchedule,driver,"Activity",result,form,sys,messages,formatOutput);
                }
        		report = factory.createReport();
        	}
        	reports.add(report);
        }
        // Genero el reporte TOTAL:

        JFreeReportBoot.getInstance().start();   
        return new ReportUtil().executeReport(mapping,response,reports,formatOutput,session,"activity");
    	
    }


    /**
     * 
     */
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    

    /**
     * 
     * @param stringList
     * @return
     */
    public Collection<String> stringToCollection(String stringList){
		String[] stringArray=stringList.trim().split(";",0);
		Collection<String> col = new LinkedList<String>();
		
		for(int i=0; i < stringArray.length; i++){
			col.add(stringArray[i]);
		}
		
		return col;
	}
	

} 
    


