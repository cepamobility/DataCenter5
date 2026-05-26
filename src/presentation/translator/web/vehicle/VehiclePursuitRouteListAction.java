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
import communication.activity.tables.ItemData;
import communication.activity.tables.ItemValueStrData;
import communication.activity.tables.PointData;
import communication.administration.user.UserSessionData;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;
import communication.vehicle.tables.VehiclePursuitRouteData;

public class VehiclePursuitRouteListAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		    HttpSession session = request.getSession();
	        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
	        Text messages = sys.getText();
			UserSessionData userSessionData = sys.getUserSessionData();
			
			VehiclePursuitRouteListForm formData = (VehiclePursuitRouteListForm) form;		
			
			String idDriver = (String) session.getAttribute("idDriver");
			String map="";

			String idActivity = (String) session.getAttribute("idActivity");
			
	        Collection itemCollection = new LinkedList();
	        Hashtable items = new Hashtable();
	        Iterator it = Util.stringToCollection(formData.getList()).iterator(); 

			while(it.hasNext()) {			
				String[] values = ((String)it.next()).split("-");
	            if(values.length == 2) {
	                items.put(values[0],values[1]);
	            }
			}
					
	        CheckListData checkList = sys.getVehicleReportFacade().
	        	getCheckList(CheckListData.PURSUIT_ROUTE_CHECKLIST,sys.getUserSessionData());
	        Iterator points = checkList.getSortPoints();
	        while(points.hasNext()) {
	            PointData point = (PointData)points.next();
	            Iterator itemsIt = point.getSortItems();
	            while(itemsIt.hasNext()) {
	                ItemData item = (ItemData)itemsIt.next();
	                if(!items.containsKey(String.valueOf(item.getId()))) {
	                    if(formData.getId()!= null) {
	                        session.setAttribute("check",formData.getId());
	                    }
	                    session.setAttribute("Msg", messages.getText("checklist.error.missingdata") + " - " + messages.getText(item.getText()));         
	                    return mapping.findForward("back");       
	                }else {
	                    ItemValueStrData itemValueAux = new ItemValueStrData(new ItemData(item.getId()),(String)items.get(String.valueOf(item.getId())));
	                    itemCollection.add(itemValueAux);
	                }
	            }
	        }
	        
	        Integer vehicleId = new Integer ((String) session.getAttribute("id"));
			VehicleData vehicle = sys.getVehicleReportFacade().
				findVehicleByPrimaryKey(vehicleId,userSessionData);
			
			// Si vehicle es null entonces desplegar un mensaje de error			
			String message = validar(vehicle,formData.getDay(),formData.getMonth(),
					formData.getYear(),formData.getEvaluationhour(),formData.getEvaluationminute());
			
			if(message != null) {				
				session.setAttribute("Msg", messages.getText(message));		    
	            if(formData.getId() != null) {
	                session.setAttribute("check",formData.getId());
	            }
				return mapping.findForward("back");		
			}
			
			if (Util.empty(formData.getInstructor())){
				session.setAttribute("Msg", messages.getText("generic.messages.noinstructor"));		    
	            if(formData.getId() != null) {
	                session.setAttribute("check",formData.getId());
	            }
				return mapping.findForward("back");	
			}
			
			if (Util.empty(formData.getArea())){
				session.setAttribute("Msg", messages.getText("generic.messages.noarea"));		    
	            if(formData.getId() != null) {
	                session.setAttribute("check",formData.getId());
	            }
				return mapping.findForward("back");	
			}
			
				
			Date date = Util.getDate(formData.getDay().trim(),formData.getMonth().trim(),formData.getYear().trim());
			Date dateEvaluation = Util.getDate(formData.getDay().trim(),formData.getMonth().trim(),formData.getYear().trim());
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.HOUR_OF_DAY,new Integer(formData.getEvaluationhour().trim()).intValue());
			c.set(Calendar.MINUTE, new Integer(formData.getEvaluationminute().trim()).intValue());
			dateEvaluation = c.getTime();


			VehiclePursuitRouteData vehicleCheck = new VehiclePursuitRouteData(
					formData.getPursuitId(),itemCollection,date,formData.getDivorg1(),
					formData.getObs().trim(),vehicle, formData.getInstructor(),dateEvaluation,
					formData.getArea());
	        
	        Integer id = sys.getVehicleABMFacade().createOrUpdateVehiclePursuitRoute(
	        		vehicleCheck,"creacion",userSessionData);					
	        session.setAttribute("check",id);
	        
			// 	Clean session
			session.removeAttribute("VehiclePursuitRouteListForm");
			session.removeAttribute("idActivity");
			session.removeAttribute("form");
						
			return mapping.findForward("success");
	}
	
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean isEmpty(String value) {
        return ((value == null) || (value.trim().length()==0));
    }	    
	
	
	/**
	 * 
	 * @param vehicle
	 * @param day
	 * @param month
	 * @param year
	 * @param dayLicenseExpiry
	 * @param monthLicenseExpiry
	 * @param yearLicenseExpiry
	 * @return
	 */
	private String validar(VehicleData vehicle,String day,
			String month, String year,
			String evaluationhour,String evaluationminute) { 

		String message = null;

		if (vehicle == null) {
			message = "generic.vehicle.invalidVehicle";
			return message;
		}				
		
	    // Fecha válida
	    if (anyEmpty(day,month,year)) 
	    	return "generic.activity.activitydata.dateInvalid";
	    else {
		    if((! isEmpty(day) && notNumber(day.trim())) || 
		    		(! isEmpty(month) && notNumber(month.trim()) ||
		    		(! isEmpty(year) && notNumber(year.trim())))) {
		    	return "generic.activity.activitydata.dateInvalid";
		    }
	    }
	    if (!Util.checkDate(Integer.parseInt(day.trim()),Integer.parseInt(month.trim()),Integer.parseInt(year.trim()))) {
	    	return "generic.activity.activitydata.dateInvalid";
	    }
	    
	    // Fecha de expiraciòn de la licencia válida
	    if (anyEmpty(evaluationhour, evaluationminute, evaluationminute)) 
	    	return "generic.activity.activitydata.dateInvalid";
	    else {
		    if((! isEmpty(day) && notNumber(day.trim())) || 
		    		(! isEmpty(evaluationhour) && notNumber(evaluationhour.trim()) ||		    						    				
		    		(! isEmpty(evaluationminute) && notNumber(evaluationminute.trim())))) {
		    	return "generic.activity.activitydata.hourInvalid";
		    }
	    }
	    if (!Util.checkHour(Integer.parseInt(evaluationhour.trim()),
	    		Integer.parseInt(evaluationminute.trim()))) {
	    	return "generic.activity.activitydata.hourInvalid";
	    }			    	    
		return message;
    } 
	   
	
	/**
	 * 
	 * @param value1
	 * @param value2
	 * @param value3
	 * @return
	 */
	private boolean anyEmpty(String value1,String value2,String value3) {
	    	int any = 0;
	    	if ((value1 == null) || (value1.trim().length()==0))
	    		any ++;
	    	if ((value2 == null) || (value2.trim().length()==0))
	    		any ++;
	    	if ((value3 == null) || (value3.trim().length()==0))
	    		any ++;
		    //return ((any > 0 && any < 3));
	    	return (any > 0);
		}	

	/**
	 * 
	 * @param value
	 * @return
	 */
    private boolean notNumber(String value) {
    	
	    try {
	    	new Integer(value.trim());
	    }
	    catch (Exception e) {
	    	return true;
	    }	
	    return false;
	}	   
    
    /**
     * 
     * @param items
     * @param item
     * @return
     */
    private String getValue(Collection items,ItemData item) {
    	
    	Iterator iter = items.iterator();
    	boolean encontre = false;
    	int idItem;
    	String value = null;
    	ItemValueStrData itemValue= null;
    	while (iter.hasNext() && !encontre) {
    		itemValue= (ItemValueStrData) (iter.next());
    		encontre = (item.getId().intValue() == itemValue.getItem().getId().intValue());	    		
    	}
    	if (encontre) {
    		value = itemValue.getItemValue();
    	}
    	return value;
    }

    /**
     * 
     * @param items
     * @param item
     * @return
     */
    private ItemValueStrData getItemValueStr(Collection items,ItemData item) {
    	
    	Iterator iter = items.iterator();
    	boolean encontre = false;
    	ItemValueStrData itemValue= null;
    	while (iter.hasNext() && !encontre) {
    		itemValue= (ItemValueStrData) (iter.next());
    		encontre = (item.getId().intValue() == itemValue.getItem().getId().intValue());	    		
    	}
    	if (encontre) {
    		return itemValue;
    	}
    	return null;
    }

	
	public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    } 
		

}
