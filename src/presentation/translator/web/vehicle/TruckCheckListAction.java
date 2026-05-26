/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.vehicle;

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
import communication.vehicle.tables.TrailerCheckData;
import communication.vehicle.tables.TrailerData;
import communication.vehicle.tables.TrailerMainData;
import communication.vehicle.tables.VehicleCheckData;
import communication.vehicle.tables.VehicleData;

/**
 * @author adocarmo
 */
public class TruckCheckListAction extends AbstractAction {

		
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
		UserSessionData userSessionData = sys.getUserSessionData();
		
		TruckCheckListForm formData = (TruckCheckListForm) form;	
		
		String idDriver = (String) session.getAttribute("idDriver");
		String map="";
		String idActivity = (String) session.getAttribute("idActivity");
		boolean flag=false;
        Collection itemCollection = new LinkedList();

        Hashtable hashValues = new Hashtable();
        
        Iterator it = Util.stringToCollection(formData.getList()).iterator(); 
		while(it.hasNext()) {			
			String[] values = ((String)it.next()).split("-");
            if(values.length == 3) {
                if(hashValues.containsKey(values[0])) {
                    ((Hashtable)hashValues.get(values[0])).put(values[1],values[2]);
                }else {
                    Hashtable otherVehicle = new Hashtable();
                    otherVehicle.put(values[1],values[2]);
                    hashValues.put(values[0],otherVehicle);
                }
            }
		}
				
        Integer vehicleId = new Integer ((String) session.getAttribute("id"));

        CheckListData checkList = sys.getVehicleReportFacade().getCheckList(CheckListData.TRUCK_CHECKLIST,sys.getUserSessionData());
        Iterator points = checkList.getSortPoints();
        while(points.hasNext()) {
        	PointData point = (PointData)points.next();
        	Iterator itemsIt = point.getSortItems();
        	Hashtable items = (Hashtable)hashValues.get(session.getAttribute("id"));
            while(itemsIt.hasNext()) {
                ItemData item = (ItemData)itemsIt.next();
                if(point.getId().equals(new Integer(137)) && formData.getVehicleType().equals("datatype.vehicletype.trator")){
                	ItemValueStrData itemValueAux = new ItemValueStrData(new ItemData(item.getId()),"1");
                	itemCollection.add(itemValueAux);
                } else if(item.getType() != null && item.getType().intValue() == 1 && formData.getVehicleType().equals("datatype.vehicletype.trator")) {
                    ItemValueStrData itemValueAux = new ItemValueStrData(new ItemData(item.getId()),"1");
                    itemCollection.add(itemValueAux);
                }else {
                    if(items == null || !items.containsKey(String.valueOf(item.getId()))) {
                        if(formData.getCheckId() != null) {
                            session.setAttribute("check",formData.getCheckId());
                        }
                        session.setAttribute("Msg", messages.getText("checklist.error.missingdata") + " - " + messages.getText(item.getText()));         
                        return mapping.findForward("back");       
                    }else {
                    	String value=(String)items.get(String.valueOf(item.getId()));
                        ItemValueStrData itemValueAux = new ItemValueStrData(new ItemData(item.getId()),value);
                        itemCollection.add(itemValueAux);
                        if (value.equals("2")){
                        	flag=true;
                        }
                    }
                }
            }
        }
        
		VehicleData vehicle = sys.getVehicleReportFacade().findVehicleByPrimaryKey(vehicleId,userSessionData);
		
		// Si vehicle es null entonces desplegar un mensaje de error
		Integer vKm= null;
		String message = validar(formData.getVehicleKm(),vehicle,formData.getDay(),formData.getMonth(),formData.getYear(),formData.getDayLicenseExpiry(),formData.getMonthLicenseExpiry(),formData.getYearLicenseExpiry());
		
		if(message != null) {				
			session.setAttribute("Msg", messages.getText(message));		    
            if(formData.getCheckId() != null) {
                session.setAttribute("check",formData.getCheckId());
            }
			return mapping.findForward("back");		
		}
		
		if (!isEmpty(formData.getVehicleKm())) {
			vKm = new Integer(formData.getVehicleKm());
		}	
		Date date = Util.getDate(formData.getDay(),formData.getMonth(),formData.getYear());
		Date dateLicenseExpiry = Util.getDate(formData.getDayLicenseExpiry(),formData.getMonthLicenseExpiry(),formData.getYearLicenseExpiry());

		VehicleCheckData vehicleCheck = new VehicleCheckData(formData.getCheckId(),vehicle,date,formData.getName(),vKm,formData.getObs(),formData.getSerie(),itemCollection,formData.getLicenseNumber(),dateLicenseExpiry,null);
		Collection trailers = sys.getVehicleReportFacade().getVehicleTrailers(vehicleId,sys.getUserSessionData());
        
        Collection trailerChecks = new LinkedList();
        
        it = trailers.iterator();
        while(it.hasNext()) {
            TrailerMainData trailer = (TrailerMainData)it.next();
            if(request.getParameter("trailerCheck"+String.valueOf(trailer.getId())).equals("1")) {
            
                Hashtable items = (Hashtable)hashValues.get(String.valueOf(trailer.getId()));
                Date trailerDate = Util.getDate(formData.getDay(),formData.getMonth(),formData.getYear());
                Date trailerDateLicenseExpiry = Util.getDate(formData.getDayLicenseExpiry(),formData.getMonthLicenseExpiry(),formData.getYearLicenseExpiry());
    
                checkList = sys.getVehicleReportFacade().getCheckList(CheckListData.TRAILER_CHECKLIST,sys.getUserSessionData());
                points = checkList.getSortPoints();
                Collection trailerIitemCollection = new LinkedList();
                while(points.hasNext()) {
                    PointData point = (PointData)points.next();
                    Iterator itemsIt = point.getSortItems();
                    while(itemsIt.hasNext()) {
                        ItemData item = (ItemData)itemsIt.next();
                        if(items == null || !items.containsKey(String.valueOf(item.getId()))) {
                            if(formData.getCheckId() != null) {
                                session.setAttribute("check",formData.getCheckId());
                            }
                            session.setAttribute("Msg", messages.getText("checklist.error.missingdata") + " - " + messages.getText(item.getText()));         
                            //session.setAttribute("CheckIdMissing",item.getId().toString());
                            return mapping.findForward("back");       
                        }else {
                        	String value = (String)items.get(String.valueOf(item.getId()));
                            ItemValueStrData itemValueAux = new ItemValueStrData(new ItemData(item.getId()),value);
                            trailerIitemCollection.add(itemValueAux);
                            if (value.equals("2")){
                            	flag=true;
                            }
                        }
                    } 
                }
                Integer trailerCheckId = (!Util.empty(request.getParameter("trailerCheckId"+String.valueOf(trailer.getId())))) ? new Integer(request.getParameter("trailerCheckId"+String.valueOf(trailer.getId()))) : null;
                trailerChecks.add(new TrailerCheckData(trailerCheckId,new TrailerData(trailer),trailerDate,formData.getName(),formData.getObs(),formData.getSerieTrailer(),trailerIitemCollection,formData.getLicenseNumber(),trailerDateLicenseExpiry));            
            }
        }
        
        Integer id = sys.getVehicleABMFacade().createOrUpdateTruckCheck(vehicleCheck,trailerChecks,"creacion",userSessionData);					
        session.setAttribute("check",id);
		// 	Clean session
		session.removeAttribute("TruckCheckListForm");
		session.removeAttribute("idActivity");
		session.removeAttribute("from");
		//session.removeAttribute("CheckIdMissing");
		if ((flag)&&(userSessionData.getFilter().getCorporationId().intValue()==2 || userSessionData.getFilter().getCorporationId().intValue()==31)){
			Date lastChecklistDate=sys.getVehicleReportFacade().findLastVehicleCheckDate(vehicleCheck.getVehicle().getId(),sys.getUserSessionData());
			Date thisChecklistDate=vehicleCheck.getDate();
			if (thisChecklistDate.equals(lastChecklistDate)||(thisChecklistDate.after(lastChecklistDate)))
				return mapping.findForward("alert");
			else
				return mapping.findForward("success");
				
		}else
			return mapping.findForward("success");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }    
    
    private boolean isEmpty(String value) {
        return ((value == null) || (value.trim().length()==0));
    }	    
	        	    	
	  private String validar(String vehiclekm,VehicleData vehicle,String day,String month, String year,String dayLicenseExpiry,String monthLicenseExpiry, String yearLicenseExpiry) { 
	    	String message = null;

			try {

				if ( (vehiclekm != null) && (!vehiclekm.equals(""))) {
					new Integer(vehiclekm);
				}
				
				if (vehicle == null) {
					message = "generic.vehicle.invalidVehicle";
					return message;
					
					
				}
				
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
			    if (!Util.checkDate(Integer.parseInt(day),Integer.parseInt(month),Integer.parseInt(year))) {
			    	return "generic.activity.activitydata.dateInvalid";
			    }
			    
			    // Fecha de expiraciòn de la licencia válida
			    if (anyEmpty(dayLicenseExpiry,monthLicenseExpiry,yearLicenseExpiry)) 
			    	return "generic.activity.activitydata.dateInvalid";
			    else {
				    if((! isEmpty(day) && notNumber(day.trim())) || 
				    		(! isEmpty(monthLicenseExpiry) && notNumber(monthLicenseExpiry.trim()) ||
				    		(! isEmpty(yearLicenseExpiry) && notNumber(yearLicenseExpiry.trim())))) {
				    	return "generic.activity.activitydata.dateInvalid";
				    }
			    }
			    if (!Util.checkDate(Integer.parseInt(dayLicenseExpiry),Integer.parseInt(monthLicenseExpiry),Integer.parseInt(yearLicenseExpiry))) {
			    	return "generic.activity.activitydata.dateInvalid";
			    }			    
			    				
							
			} catch (Exception e) {
				message = "generic.vehicle.invalidVehicleKm";
				return message;
			}
			return message;
	    } 
	    
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

	    private boolean notNumber(String value) {
	    	
		    try {
		    	new Integer(value.trim());
		    }
		    catch (Exception e) {
		    	return true;
		    }	
		    return false;
		}	   
	      

}
