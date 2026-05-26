package presentation.translator.web.driver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.driver.TelematicControlQueryFactory;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.driver.report.TelematicControlQueryData;
import communication.driver.tables.TelematicControlData;
import communication.language.Text;
import communication.util.ListResult;

public class TelematicControlQueryAction extends AbstractAction {

	
	/*
	 
	 Consultas para hacer directamente sobre la BD para obtener los listados necesarios para MailChimp>
	 
		-- --- ROJOS
		select email, firstname, lastname, d.id as iddriver, 'red' as color, iddallas, 6 as mes
		from telematiccontrols t, drivers d
		where d.id = t.driver and t.type = 7 and
		d.corporation = 1 and
		t.date >= '2012-06-01' and t.date < '2012-07-01'
		and t.occurrences > 3
		and d.country = 32 -- solo brasil
		and iddallas is not null and iddallas != ''
		order by firstname, lastname;
		
		
		-- AMARILLOS
		select email, firstname, lastname, d.id as iddriver, 'yellow' as color, iddallas, 6 as mes
		from telematiccontrols t
		inner join drivers d on d.id = t.driver
		where t.type = 7 
		and d.corporation = 1  
		and (t.date >= '2012-06-01' and t.date < '2012-07-01')
		and t.occurrences between 1 and  3
		and d.country = 32 -- solo brasil
		and d.iddallas is not null and d.iddallas != ''
		order by d.firstname, d.lastname;
		
		-- VERDES 
		select  email, firstname, lastname, d.id as iddriver, 'green' as color, iddallas, 6 as mes
		from drivers d
		where 
		d.corporation = 1 and 
		 d.country = 32 -- solo brasil
		and iddallas is not null and iddallas != '' and
		d.id NOT IN ( 
				select t.driver from telematiccontrols as t 
				where t.driver = d.id 
				and t.type = 7 and t.occurrences > 0 and 
				t.date >= '2012-06-01' and t.date < '2012-07-01'
		)
		order by firstname, lastname;
	 
	 */
	
	
	/*
	 * (non-Javadoc)
	 * @see presentation.translator.web.util.AbstractAction#action(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        TelematicControlQueryForm reportForm = (TelematicControlQueryForm)form;
        session.setAttribute("Bookmark",reportForm.getBookmark());

        JFreeReportBoot jfree;
        jfree = JFreeReportBoot.getInstance();
        try{        	
        	jfree.start();
        }catch(Throwable e){
        	jfree.start();
        }
        
        JFreeReport report = getReport(sys,reportForm,1);

        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"TelematicControl",true);
        return fwd;
        
    }
    
    /**
     * 
     * @param sys
     * @param reportForm
     * @param pageNumber
     * @return
     * @throws Exception
     */
    public JFreeReport getReport(SystemAccess sys,TelematicControlQueryForm reportForm,
   		 int pageNumber) throws Exception {
       	try{
      
       		JFreeReport report = null;
       		Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
       		Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
       		Text messages = sys.getText();
       		
       		int rows = 50;
          
       		int first = (reportForm.getOutput().equals("HTML")) ? (pageNumber-1)* rows : 0;
       		int count = (reportForm.getOutput().equals("HTML")) ? rows : -1;

       		Integer fromYear = (Util.empty(reportForm.getFromyear())) ? null : 
       			new Integer(reportForm.getFromyear()); 
       		Integer fromMonth = (Util.empty(reportForm.getFrommonth())) ? null : 
       			new Integer(reportForm.getFrommonth()); 
       		Integer fromDay = (Util.empty(reportForm.getFromday())) ? null : 
       			new Integer(reportForm.getFromday()); 
       		
       		Integer toYear = (Util.empty(reportForm.getToyear())) ? null : 
       			new Integer(reportForm.getToyear()); 
       		Integer toMonth = (Util.empty(reportForm.getTomonth())) ? null : 
       			new Integer(reportForm.getTomonth()); 
       		Integer toDay = (Util.empty(reportForm.getToday())) ? null : 
       			new Integer(reportForm.getToday()); 
       		
       		Integer type = (Util.empty(reportForm.getType())) ? null : 
       			new Integer(reportForm.getType());
       		Integer recomendation = (Util.empty(reportForm.getRecomendation())) ? null : 
       			new Integer(reportForm.getRecomendation());
       		String idDallas = (Util.empty(reportForm.getIdDallas())) ? null : 
       			reportForm.getIdDallas().trim(); 
       		String firstName = (Util.empty(reportForm.getFirstname())) ? null : 
       			reportForm.getFirstname().trim();
       		String lastName = (Util.empty(reportForm.getLastname())) ? null : 
       			reportForm.getLastname().trim(); 
       	
//       		String reportType = (Util.empty(reportForm.getReportType())) ? 
//       				TelematicControlData.REPORT_TYPE_DETAILED:
//       				reportForm.getReportType();
       		String color = (Util.empty(reportForm.getColor())) ? 
       				TelematicControlData.COLOR_RED : 
       				reportForm.getColor();
       		String colorOrType = (Util.empty(reportForm.getColorOrType())) ? 
       				"color" : reportForm.getColorOrType();
       		
       		String reportType = TelematicControlData.REPORT_TYPE_DETAILED;
       		
       		if (color.equalsIgnoreCase(TelematicControlData.COLOR_GREEN)){
       			// Cuando son verdes entonces no tenemos eventos de velocidad, por lo que el detallado no es necesario.
       			reportType = TelematicControlData.REPORT_TYPE_RESUMED;
       		}
       		
       		ListResult result = sys.getDriverReportFacade().
       			getTelematicControlQuery(fromDay,fromMonth,fromYear,
       					toDay, toMonth, toYear, divOrgs, divGeos,
       					firstName, lastName, idDallas, type, recomendation, 
       					reportForm.getStatus(),first, count,colorOrType,reportType,color,
       					sys.getUserSessionData());
       		
       		TelematicControlQueryData query = null;
       		int pages = 0;
       		if (result != null){
       			query = new TelematicControlQueryData(
       				result.getElements(), result.getTotal(), sys.getUserSessionData());
       		
       			query.setMessages(messages);
       			pages = (int)(result.getTotal() / rows);
       			if (pages*rows < result.getTotal()) {
       				pages++;
       			}
       		}      		
         
            TelematicControlQueryFactory factory = new TelematicControlQueryFactory(
            		messages.getText("query.telematiccontrolquery.title"),
            		query, reportForm, sys, messages, pages, pageNumber,
            		colorOrType, reportType);
       		
       		
       		report = factory.createReport();
       		return report;
        
       		
       	}catch(Exception e){
       		e.printStackTrace();
    		throw e;
       	}       	    	
    }
       	
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

} 
    


