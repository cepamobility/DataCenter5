/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
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
import presentation.report.activity.ActivityDeliveredFactory;
import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.activity.report.ActivityDeliveredData;
import communication.activity.tables.CEPAActivityConstants;
import communication.administration.bookmark.tables.BookmarkData;
import communication.administration.corporation.tables.CorporationData;
import communication.language.Text;


/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActivityDeliveredAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        ActivityExpiryForm reportForm = (ActivityExpiryForm)form;
        
        String message = reportForm.validate(sys.getUserSessionData());
        
        if(message != null) {               
            //Some attribute data is invalid    
            //Si en el struts config indico que el form es de sesion la línea de abajo es inneseraria
            session.setAttribute("Msg", messages.getText(message));
            return mapping.findForward("back");             
        }           
     
        JFreeReportBoot.getInstance().start();
        JFreeReport report = getReport(sys,reportForm,1);
        session.setAttribute("Bookmark",reportForm.getBookmark(BookmarkData.ACTIVITY_DELIVERED_REPORT));
        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"ActivityExpiry",true);
        return fwd;
        
    }
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
    public JFreeReport getReport(SystemAccess sys,ActivityExpiryForm reportForm,int page) throws Exception {
        Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
        Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);        

        JFreeReport report = null;
        Text messages = sys.getText();
        int first = (reportForm.getOutput().equals("HTML")) ? (page-1)*50 : 0;
        int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;
        
        Date initialDate = reportForm.getInitialDate();
        Date lastDate = reportForm.getLastDate();
        
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
        
        String status = reportForm.getStatus();
        
        String[] vehicleAllowed = reportForm.getVehicleAllowed();
        /*Collection<String> vehicles = new LinkedList<String>();
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
        ActivityDeliveredData result = sys.getActivityReportFacade().driverWithDeliveredActivities(sys.getUserSessionData(),initialDate,lastDate,type,divOrgs,divGeos,status,vehicleAllowed,first,count);        
        
        result.setMessages(messages);
        int pages = (int)(result.getTotal() / 50);
        if(pages*50 < result.getTotal()) {
            pages++;
        }
        
        //ActivityExpiryFactory factory = new ActivityExpiryFactory(messages.getText("query.activitydelivered.title"),new StaticTableModelProvider(result).getModel(),reportForm,sys,messages,pages,page);
        
        ActivityDeliveredFactory factory = new ActivityDeliveredFactory(messages.getText("query.activitydelivered.title"),result,reportForm,sys,messages,pages,page);
        
        report = factory.createReport();

        return report;
    }    

} 
    


