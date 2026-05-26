package presentation.translator.web.activity.schedule;

import java.util.Collection;
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
import presentation.report.activity.DriversWithoutTOJFactory;
import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.activity.report.DriversWithoutTOJData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.language.Text;

public class DriversWithoutTOJAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        UserSessionData userSessionData = sys.getUserSessionData();
        
        DriversWithoutBTWForm reportForm = (DriversWithoutBTWForm)form;
        
   		String message = reportForm.validate(sys.getUserSessionData());
   		
		if(message != null) {				
		    //Some attribute data is invalid	
			//Si en el struts config indico que el form es de sesion la línea de abajo es inneseraria
			// session.setAttribute("Msg", messages.getText(message));
			userSessionData.setUrl("/activity/schedule/driversWithoutBTWQuery.jsp");
			userSessionData.setMsg(messages.getText(message));			
			return mapping.findForward("back");    			
		}   		
            
        JFreeReportBoot.getInstance().start();
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
	    }*/
        
        
        DriversWithoutTOJData result = 
        	sys.getActivityReportFacade().
        	driversWithoutTOJ(sys.getUserSessionData(),divOrgs,divGeos,status,vehicleAllowed);        
        result.setMessages(messages);
        DriversWithoutTOJFactory factory = new DriversWithoutTOJFactory(
        			messages.getText("query.driverswithouttoj.title"),result,
        			reportForm,sys,messages);        
        
        report = factory.createReport();

        return report;
    }    
}