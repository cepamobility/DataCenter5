/*
 * Created on 24-feb-2006
 */
package presentation.translator.web.activity.schedule;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.activity.InductionActivityReportFactory;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.activity.ActivityReportFacade;

import communication.activity.report.ActivityReportData;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.driver.tables.DriverData;
import communication.language.Text;

public class InductionTrainingReportAllAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	 
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        String idActivity=request.getParameter("idActivity");
    	String idDrivers=request.getParameter("idDrivers");
     	String formatOutput = "PDF";
    	Collection drivers = stringToCollection(idDrivers);
         
        JFreeReport report;
        ActivityReportFacade facade = sys.getActivityReportFacade();
        CEPAActivityScheduleRegistryData completeResult;
        String idDriver ="";
        DriverData driver;
        CEPAActivityScheduleData activitySchedule;
        InductionActivityReportFactory factory;
        Collection reports = new LinkedList();
        
        Iterator it = drivers.iterator();
        activitySchedule = facade.findCEPAActivityScheduleByPrimaryKey(new Integer(idActivity),sys.getUserSessionData());
        while (it.hasNext()){
        	idDriver = (String)it.next();
        	completeResult = facade.getCompleteResult(new Integer(idActivity),new Integer(idDriver),sys.getUserSessionData());        
        	driver = completeResult.getDriver();
            factory = new InductionActivityReportFactory(completeResult.getObs(),activitySchedule,driver,"InductionTraining",new ActivityReportData(),form,sys,messages,formatOutput);
        	report = factory.createReport();	
        	reports.add(report);
        }
        
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
    public Collection stringToCollection(String stringList){
		String[] stringArray=stringList.trim().split("<",0);
		Collection col = new LinkedList();
		
		for(int i=0; i < stringArray.length; i++){
			col.add(stringArray[i]);
		}
		
		return col;
	}
	

} 
    


