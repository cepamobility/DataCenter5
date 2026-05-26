/*
 * Created on 20-May-2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.vehicle;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.activity.tables.CheckListData;
import communication.alert.AlertData;
import communication.alert.AlertToData;
import communication.administration.user.UserSessionData;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;

/**
 * @author fperez
 */
public class CheckFormAlertAction extends AbstractAction {

		
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
		UserSessionData userSessionData = sys.getUserSessionData();
		
		CheckFormAlert formData = (CheckFormAlert) form;	
		Collection emailCollection = new LinkedList();
        Collection itemCollection = new LinkedList();
        
        emailCollection = Util.stringToCollection(formData.getEmaillist());
        if ((formData.getTruckdatelist()!="")&&(formData.getVehiclecheck()!=null)){
        	itemCollection = Util.stringToCollection(formData.getTruckdatelist());
        	String message = validate(itemCollection,emailCollection);
    		if(message != null) {				
    			session.setAttribute("Msg", messages.getText(message));		    
                if(formData.getVehiclecheck() != null) {
                    session.setAttribute("check",formData.getVehiclecheck());
                }
    			return mapping.findForward("back");		
    		}
        	// sys.getAlertABMFacade().vehicleChecklistAlertGenerate(AlertData.ALERT_TRUCK,itemCollection,emailCollection,formData.getVehiclecheck(),formData.getVehicleplate(),null,null,messages,sys.getUserSessionData());
        }
        
        if ((formData.getTrailersdatelist()!="")&&(formData.getTrailerschecks()!=null)){
        	itemCollection = Util.stringToCollection(formData.getTrailersdatelist(),">");
        	Iterator it =itemCollection.iterator();
        	Collection trailer = new LinkedList();
        	Iterator itIds=formData.getTrailerschecks().iterator();
        	Iterator itPlates=formData.getTrailersplates().iterator();
        	Integer idTrailer;
        	String plateTrailer;
        	String text="";
        	while(it.hasNext()){
        		text=(String)it.next();
        		if(!text.equals("")){
            		idTrailer=(Integer)itIds.next();
            		plateTrailer=(String)itPlates.next();
        			trailer = Util.stringToCollection(text);
        			String message = validate(itemCollection,emailCollection);
        			if(message != null) {				
        				session.setAttribute("Msg", messages.getText(message));		    
        				return mapping.findForward("back");		
        			}
        			// sys.getAlertABMFacade().vehicleChecklistAlertGenerate(AlertData.ALERT_TRAILER,trailer,emailCollection,idTrailer,formData.getVehicleplate(),plateTrailer,null,messages,sys.getUserSessionData());
        		}
        	}
         }    
        
         return mapping.findForward("success");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }    
    
    private boolean isEmpty(String value) {
        return ((value == null) || (value.trim().length()==0));
    }	    
	        	    	
	private String validate(Collection itemCollection, Collection emailDate) { 
	    	String message = null;

			try {
					Iterator itdate = itemCollection.iterator();
					while(itdate.hasNext()) {			
						String[] values = ((String)itdate.next()).split("	");
						if(values.length == 5) {
							Date lastdate= new Date(values[1]);
							Calendar calendar = Calendar.getInstance();
					        calendar.setTime(lastdate);
							if (!Util.checkDate(calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.YEAR)))
								return "generic.activity.activitydata.dateInvalid";
						}
					}
					
					Iterator emaildate = emailDate.iterator(); 
					String emailValue;
					while(emaildate.hasNext()) {
						emailValue=(String)emaildate.next();
						if(!Util.checkEmail(emailValue))
							return message = "generic.alert.invalidemail";
					}
					
			} catch (Exception e) {
				message = "generic.alert.invaliddata";
				return message;
			}
			return message;
	 } 
	    
	   

}
