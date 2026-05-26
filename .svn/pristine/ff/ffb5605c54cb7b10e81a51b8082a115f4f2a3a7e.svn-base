package presentation.translator.web.activity.schedule;

import java.util.Collection;
import java.util.Date;
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
import presentation.report.activity.DriversWithoutBTWFactory;
import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.activity.report.DriversWithoutBTWData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.language.Text;

public class DriversWithoutBTWAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        UserSessionData userSessionData = sys.getUserSessionData();
        
        DriversWithoutBTWForm reportForm = (DriversWithoutBTWForm)form;
        
        // Restriccion de 2 años  y de campos nulos para las fechas:
        /*Integer fromYear = (Util.empty(reportForm.getFromyear()) || reportForm.getFromyear().equals("0")) ? null : new Integer(reportForm.getFromyear());
        Integer toYear = (Util.empty(reportForm.getToyear()) || reportForm.getToyear().equals("0")) ? null : new Integer(reportForm.getToyear());
        Integer fromMonth = (Util.empty(reportForm.getFrommonth()) || reportForm.getFrommonth().equals("0")) ? null : new Integer(reportForm.getFrommonth());
        Integer toMonth = (Util.empty(reportForm.getTomonth()) || reportForm.getTomonth().equals("0")) ? null : new Integer(reportForm.getTomonth());
        Integer toDay = (Util.empty(reportForm.getToday()) || reportForm.getToday().equals("0")) ? null : new Integer(reportForm.getToday());
        Integer fromDay = (Util.empty(reportForm.getFromday()) || reportForm.getFromday().equals("0")) ? null : new Integer(reportForm.getFromday());
        if ( fromYear == null ||  toYear == null || fromMonth == null || toMonth == null ||
        		toDay == null || fromDay == null || !Util.dateRange(fromYear.toString(),
        		toYear.toString(),fromMonth.toString(),toMonth.toString(),2)){
            	session.setAttribute("Msg",messages.getText("report.event.range2"));
                return mapping.findForward("back");
        }
        */

   		String message = reportForm.validate(sys.getUserSessionData());
   		
		if(message != null) {				
		    //Some attribute data is invalid	
			//Si en el struts config indico que el form es de sesion la línea de abajo es inneseraria
			// session.setAttribute("Msg", messages.getText(message));
			userSessionData.setUrl("/activity/schedule/driversWithoutBTWQuery.jsp");
			userSessionData.setMsg(messages.getText(message));			
			return mapping.findForward("back");    			
		}   		
            
		JFreeReportBoot jFreeReportBoot = JFreeReportBoot.getInstance();
		try{
			jFreeReportBoot.start();
		}catch(Throwable e){
			e.printStackTrace();
			jFreeReportBoot.start();
		}
        		
		
        JFreeReport report = getReport(sys,reportForm,1);
        session.setAttribute("Bookmark",reportForm.getBookmark());
        ActionForward fwd = new ReportUtil().executeReport(mapping,
        		response,report,reportForm.getOutput(),session,"DriversWithoutBTW",true);
        return fwd;
        
    }
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
    public JFreeReport getReport(SystemAccess sys,DriversWithoutBTWForm reportForm,int page) throws Exception {
        Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
        Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);        

        JFreeReport report = null;
        Text messages = sys.getText();
        int first = (reportForm.getOutput().equals("HTML")) ? (page-1)*50 : 0;
        int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;
        
        Date initialDate = reportForm.getInitialDate();
        Date lastDate = reportForm.getLastDate();
        
        String status = reportForm.getStatus();
        String[] vehicleAllowed = reportForm.getVehicleAllowed();
/*        Collection<String> vehicles = new LinkedList<String>();
        if(vehicleAllowed != null) {
	        for(int i = 0; i < vehicleAllowed.length; i++) {
	        	if(sys.getCorporationId() == CorporationData.MONSANTO) {
	        		if(vehicleAllowed[i].equals("datatype.vehicletype.automobile")) {
	        			if(!vehicles.contains("datatype.vehicletypecomb.carpickups")) {
	        				vehicles.add("datatype.vehicletypecomb.carpickups");
	        			}
	        			if(!vehicles.contains("datatype.vehicletypecomb.carpickupstruck")) {
	        				vehicles.add("datatype.vehicletypecomb.carpickupstruck");
	        			}
	        			if(!vehicles.contains("datatype.vehicletypecomb.carpickupspickupm")) {
	        				vehicles.add("datatype.vehicletypecomb.carpickupspickupm");
	        			}
	        			if(!vehicles.contains("datatype.vehicletypecomb.carpickupm")) {
	        				vehicles.add("datatype.vehicletypecomb.carpickupm");
	        			}
	        		}
	        		if(vehicleAllowed[i].equals("datatype.vehicletype.pickuplow")) {
	        			if(!vehicles.contains("datatype.vehicletypecomb.carpickups")) {
	        				vehicles.add("datatype.vehicletypecomb.carpickups");
	        			}
	        			if(!vehicles.contains("datatype.vehicletypecomb.carpickupstruck")) {
	        				vehicles.add("datatype.vehicletypecomb.carpickupstruck");
	        			}
	        			if(!vehicles.contains("datatype.vehicletypecomb.carpickupspickupm")) {
	        				vehicles.add("datatype.vehicletypecomb.carpickupspickupm");
	        			}
	        			if(!vehicles.contains("datatype.vehicletypecomb.pickupmpickups")) {
	        				vehicles.add("datatype.vehicletypecomb.pickupmpickups");
	        			}
	        		}
	        		if(vehicleAllowed[i].equals("datatype.vehicletype.middle")) {
	        			if(!vehicles.contains("datatype.vehicletypecomb.carpickupspickupm")) {
	        				vehicles.add("datatype.vehicletypecomb.carpickupspickupm");
	        			}
	        			if(vehicleAllowed[i].equals("datatype.vehicletypecomb.carpickupm")) {
	        				vehicles.add("datatype.vehicletypecomb.carpickupm");
	        			}
	        			if(!vehicles.contains("datatype.vehicletypecomb.pickupmvan")) {
	        				vehicles.add("datatype.vehicletypecomb.pickupmvan");
	        			}
	        			if(!vehicles.contains("datatype.vehicletypecomb.pickupmpickups")) {
	        				vehicles.add("datatype.vehicletypecomb.pickupmpickups");
	        			}
	        		}
	        		if(vehicleAllowed[i].equals("datatype.vehicletype.truck")) {
	        			if(!vehicles.contains("datatype.vehicletypecomb.carpickupstruck")) {
	        				vehicles.add("datatype.vehicletypecomb.carpickupstruck");
	        			}
	        			if(!vehicles.contains("datatype.vehicletypecomb.vanemergency")) {
	        				vehicles.add("datatype.vehicletypecomb.vanemergency");
	        			}
	        			if(!vehicles.contains("datatype.vehicletypecomb.pickupmvan")) {
	        				vehicles.add("datatype.vehicletypecomb.pickupmvan");
	        			}
	        		}
	        		if(vehicleAllowed[i].equals("datatype.vehicletype.van.emergency")) {
	        			if(!vehicles.contains("datatype.vehicletypecomb.vanemergency")) {
	        				vehicles.add("datatype.vehicletypecomb.vanemergency");
	        			}
	        		}
	        		
	        	}
	        	vehicles.add(vehicleAllowed[i]);
	        }
        }        */
        DriversWithoutBTWData result = sys.getActivityReportFacade().driversWithoutBTW(sys.getUserSessionData(),initialDate,lastDate,divOrgs, divGeos,status,vehicleAllowed,first,count);        
        
        int total = 0;
        int pages = 1;
        if (result != null){
        	result.setMessages(messages);
        	total = (int)result.getTotal();
        	pages = total / 50;
        	if(pages*50 < total) {
                  pages++;
            }
        }
      
        
        DriversWithoutBTWFactory factory = new DriversWithoutBTWFactory(
        			messages.getText("query.driverswithoutbtw.title"),result,
        			reportForm,sys,messages,pages,page);        
        
        report = factory.createReport();

        return report;
    }    
}