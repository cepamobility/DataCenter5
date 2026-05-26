/*
 * Created on 31-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.driver;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.driver.DriverABMFacade;

import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverData;
import communication.driver.tables.DriverMainAttributes;
import communication.language.Text;
/**
 * @author jrodriguez
 */
public class DriverDeleteAction extends AbstractAction {
	
	public ActionForward action(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
        String url = null;
        String msg = null;
        Text messages = sys.getText();

        DynaActionForm driverForm = (DynaActionForm) form;
        String stringList = (String) driverForm.get("checkboxParamList");
        String action = (String) driverForm.get("action");

        DriverABMFacade driverABM = sys.getDriverABMFacade();

        if (action.equals("delete")) {
            if (Util.empty((String) driverForm.get("comment"))) {
            	
                // session.setAttribute("Msg", messages.getText("driver.error.invalidcomment"));
            	url = "/driver/DriverExclusion.jsp?checkboxParamList="+stringList+"&action="+action;
                msg = messages.getText("driver.error.invalidcomment");
                userSessionData.setUrl(url);
                userSessionData.setMsg(msg);
                return mapping.findForward("exclude");
            }
            
            Collection colUid = Util.stringToCollection(stringList);            
            Iterator iterColUid = colUid.iterator();
            
            // List list = (List)session.getAttribute("List_drivers");
            List list = sys.getDriverReportFacade().getDrivers(stringList.replaceAll("<",","), sys.getUserSessionData());
            Iterator iter = null;
            
            if (list != null){
            	iter = list.iterator();
            }
            String message="";
            int count=0;
            DriverMainAttributes driver;
             while (iterColUid.hasNext()) {
                String id = (String) iterColUid.next();
                DriverData driverData = new DriverData(new Integer(id.trim()));
                Collection events = sys.getEventReportFacade().findEventByDriver(driverData.getId(),sys.getUserSessionData());
                Collection vehicle = sys.getVehicleReportFacade().findDriversVehicles(driverData.getId(),sys.getUserSessionData());
                Collection activities =sys.getActivityReportFacade().findActivityByDriver(driverData.getId(),sys.getUserSessionData(),false);
                Iterator itEvent = events.iterator();
                Iterator itVehicle=vehicle.iterator();
                Iterator itActivity=activities.iterator();
                driver=(DriverMainAttributes)iter.next();
                if((!itEvent.hasNext()) && (!itVehicle.hasNext()) &&(!itActivity.hasNext())){
                    driverABM.driverDelete(driverData, (String) driverForm.get("comment"), sys.getUserSessionData());
                }else{
                	if (count==0)
                		message+= messages.getText("driver.error.remove_ini")+" "+driver.getFirstName()+" "+driver.getLastName();
                	else if(count>0)
                		message+=", "+driver.getFirstName()+" "+driver.getLastName();
                	count += 1;	
                }   
            }
            if (count>0){
            	message+=" "+messages.getText("driver.error.remove_end");
            	// session.setAttribute("Msg", message);
            	userSessionData.setMsg(message);
            }
            

            if (userSessionData.getUrl() == null){
            	// Si no viene de una pagina en particular lo mando a DriverList
            	url = "/driver/DriverList.jsp";
            	userSessionData.setUrl(url);
            }
            return mapping.findForward("DriverList");
        }
        
        
        if (action.equals("exclude")) {
            if (Util.empty((String) driverForm.get("exclusionDate"))
                    || !Util.isNumber((String) driverForm.get("exclusionDate"))
                    || Util.empty((String) driverForm.get("exclusionMonth"))
                    || !Util.isNumber((String) driverForm.get("exclusionMonth"))
                    || Util.empty((String) driverForm.get("exclusionYear"))
                    || !Util.isNumber((String) driverForm.get("exclusionYear"))) {
            	
                //session.setAttribute("Msg", messages.getText("driver.error.invalidexclusiondate"));
            	url = "/driver/DriverExclusion.jsp?checkboxParamList="+stringList+"&action="+action;
                msg = messages.getText("driver.error.invalidexclusiondate");
                userSessionData.setUrl(url);
                userSessionData.setMsg(msg);
                return mapping.findForward("exclude");

            }
            if (Util.empty((String) driverForm.get("comment"))) {
                // session.setAttribute("Msg", messages.getText("driver.error.invalidcomment"));
                
            	url = "/driver/DriverExclusion.jsp?checkboxParamList="+stringList+"&action="+action;
                msg = messages.getText("driver.error.invalidcomment");
                userSessionData.setUrl(url);
                userSessionData.setMsg(msg);
                return mapping.findForward("exclude");
            }
            Date exclusionDate = Util.getDate((String) driverForm
                    .get("exclusionDate"), (String) driverForm
                    .get("exclusionMonth"), (String) driverForm
                    .get("exclusionYear"));
            driverABM.driverExclude(stringList.replaceAll("<", ","),exclusionDate, (String) driverForm.get("comment"),sys.getUserSessionData());
        }
        if (action.equals("include")) {
            if (Util.empty((String) driverForm.get("comment"))) {
            	
                // session.setAttribute("Msg", messages.getText("driver.error.invalidcomment"));
            	url = "/driver/DriverExclusion.jsp?checkboxParamList="+stringList+"&action="+action;
                msg = messages.getText("driver.error.invalidcomment");
                userSessionData.setUrl(url);
                userSessionData.setMsg(msg);
                return mapping.findForward("exclude");
            }
            driverABM.driverInclude(stringList.replaceAll("<", ","),(String) driverForm.get("comment"),sys.getUserSessionData());
        }

        
        if (userSessionData.getUrl() == null){
        	url = "/driver/DriverList.jsp";
        	userSessionData.setUrl(url);
        }
        return mapping.findForward("DriverList");
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
    	
    	String url = null;
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
        
        if (userSessionData.getUrl() == null){
        	url = "/driver/DriverList.jsp";
        	userSessionData.setUrl(url);
        }       
        
        return mapping.findForward("DriverList");
    }
}
