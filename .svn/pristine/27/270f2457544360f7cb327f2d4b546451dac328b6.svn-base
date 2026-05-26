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
import presentation.report.activity.ActivityExpiryFactory;
import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.activity.report.ActivityExpiryData;
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
public class ActivityExpiryAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	// Date  ho1= new Date();
    	
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        ActivityExpiryForm reportForm;
        reportForm = (ActivityExpiryForm)form;
        
        // validación fechas:
        Integer fromYear = (Util.empty(reportForm.getFromyear()) || reportForm.getFromyear().equals("0")) ? null : new Integer(reportForm.getFromyear());
        Integer toYear = (Util.empty(reportForm.getToyear()) || reportForm.getToyear().equals("0")) ? null : new Integer(reportForm.getToyear());
        Integer fromMonth = (Util.empty(reportForm.getFrommonth()) || reportForm.getFrommonth().equals("0")) ? null : new Integer(reportForm.getFrommonth());
        Integer toMonth = (Util.empty(reportForm.getTomonth()) || reportForm.getTomonth().equals("0")) ? null : new Integer(reportForm.getTomonth());
        Integer toDay = (Util.empty(reportForm.getToday()) || reportForm.getToday().equals("0")) ? null : new Integer(reportForm.getToday());
        Integer fromDay = (Util.empty(reportForm.getFromday()) || reportForm.getFromday().equals("0")) ? null : new Integer(reportForm.getFromday());
        if (fromDay==null&&fromMonth==null&&fromYear==null){
        	fromDay=new Integer("1");
        	fromMonth=new Integer("1");
        	fromYear=new Integer("1997");	
        	
        }
        if ( fromYear == null ||  toYear == null || fromMonth == null || toMonth == null ||
        		toDay == null || fromDay == null){
            	session.setAttribute("Msg",messages.getText("generic.supportmaterial.cepatips.baddate"));
            	
            		return mapping.findForward("back");
        }     
        reportForm.setFromday(fromDay.toString());
        reportForm.setFrommonth(fromMonth.toString());
        reportForm.setFromyear(fromYear.toString());
   		String message = reportForm.validate(sys.getUserSessionData());
   		
		if(message != null) {				
		    //Some attribute data is invalid	
			//Si en el struts config indico que el form es de sesion la línea de abajo es inneseraria
			session.setAttribute("Msg", messages.getText(message));
			
        		return mapping.findForward("back");			
		}   		
        
		     
        JFreeReportBoot.getInstance().start();
        JFreeReport report = getReport(sys,reportForm,1,session);
        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"ActivityExpiry",true);
        BookmarkData data = reportForm.getBookmark(BookmarkData.ACTIVITY_EXPIRY_REPORT);
        session.setAttribute("Bookmark",data);
        
        /*
        Date  hof= new Date();        
        System.out.println("\n");
        System.out.print("Usuario:" + sys.getUserSessionData().getFilter().getLoginName());
        System.out.print("Tiempo Reporte Venicmientos Optimizada:");
        System.out.println(hof.getTime() - ho1.getTime());
        System.out.println("\n");        
        */
        return fwd;
        
    }
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
    public JFreeReport getReport(SystemAccess sys,ActivityExpiryForm reportForm,int page,HttpSession session) throws Exception {
        Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
        Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);        
        String isBTW=(String)session.getAttribute("isBTW");
        JFreeReport report = null;
        Text messages = sys.getText();
        int first = (reportForm.getOutput().equals("HTML")) ? (page-1)*50 : 0;
        int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;
        
        Date initialDate = reportForm.getInitialDate();
        Date lastDate = reportForm.getLastDate();       
        String[] type = reportForm.getType();
        if(sys.getUserSessionData().getFilter().getCorporationId().intValue() == CorporationData.FORESTALORIENTAL) {
            boolean isBTW3 = false;
            for(int i = 0; i < type.length && !isBTW3; i++) {
                isBTW3 = type[i].equals(CEPAActivityConstants.BTW3);
            }
            if(isBTW3) {
                String[] newTypes = new String[type.length+1];
                newTypes[0] = CEPAActivityConstants.PESADOS_1;
                for(int i = 0; i < type.length; i++) {
                    newTypes[i+1] = type[i];
                }
                type = newTypes;
            }
        }else if(sys.getCorporationId() == CorporationData.MONSANTO) {
        	if(contains(type,"datatype.scheduledactivitytype.btw1") && !contains(type,"datatype.scheduledactivitytype.btw2")) {
                String[] newTypes = new String[type.length+1];
                newTypes[0] = "datatype.scheduledactivitytype.btw2";
                for(int i = 0; i < type.length; i++) {
                    newTypes[i+1] = type[i];
                }
                type = newTypes;
        	}
        }
        
        String status = reportForm.getStatus();
        
        int third = (Util.empty(reportForm.getThird())) ? 0 :Integer.parseInt(reportForm.getThird());
        
        String[] vehicleAllowed = reportForm.getVehicleAllowed();
     /*   Collection<String> vehicles = new LinkedList<String>();
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
        ActivityExpiryData result = sys.getActivityReportFacade().driverWithExpiredActivities(sys.getUserSessionData(),initialDate,lastDate,type,divOrgs,divGeos,status,vehicleAllowed,third,first,count);        
        int pages = 1;
        if (result != null){
	        result.setMessages(messages);
	        pages = (int)(result.getTotal() / 50);
	        if(pages*50 < result.getTotal()) {
	            pages++;
	        }
        }

        String text;
        if(sys.getUserSessionData().getFilter().getCorporationId().intValue() == CorporationData.MONSANTO && isBTW!=null)
        	text=messages.getText("query.activityexpiry.titlebtw2");
        else if (isBTW!=null)
        	text=messages.getText("query.activityexpiry.titlebtw");
        else
        	text= messages.getText("query.activityexpiry.title");
        
        ActivityExpiryFactory factory= new ActivityExpiryFactory(text,result,reportForm,sys,messages,pages,page,isBTW);        
            
        report = factory.createReport();

        return report;
    }

	private boolean contains(String[] type, String string) {
		for(int i = 0; i < type.length; i++) {
			if(type[i].equals(string)) {
				return true;
			}
		}
		return false;
	}    
}