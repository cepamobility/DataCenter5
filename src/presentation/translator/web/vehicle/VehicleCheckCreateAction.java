package presentation.translator.web.vehicle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

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
import business.vehicle.VehicleABMFacade;
import business.vehicle.VehicleReportFacade;

import communication.activity.tables.ItemData;
import communication.activity.tables.ItemValueStrData;
import communication.administration.user.UserSessionData;
import communication.language.Text;
import communication.vehicle.tables.VehicleCheckData;
import communication.vehicle.tables.VehicleData;


public class VehicleCheckCreateAction extends AbstractAction {

		
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
		UserSessionData userSessionData = sys.getUserSessionData();
		
		DynaActionForm createData = (DynaActionForm) form;		
		
		String stringList = (String) createData.get("checkboxParamList");
		String plate = ((String)createData.get("plate")).trim();
		String name = ((String)createData.get("name")).trim();
		
		String map="";

		String idActivity = (String) session.getAttribute("idActivity");
		String from = (String) session.getAttribute("from");
		
		if ((from != null) && from.equals("commentaryAdd") )  { // Vengo de la pàgina de CommentaryDrive		
			session.setAttribute("id",idActivity);
			//map = "ActivityRegistryAdd";
			map = "CommentaryAdd";
		}	
		else {
			if ((from != null) && from.equals("commentaryView") ) {
				session.setAttribute("id",idActivity);
				map = "CommentaryView";
			}
			else { // Vengo de la pàgina VehicleView
				map = "VehicleView";
			}
		}	
		
		Collection items = new ArrayList();
		Collection colUid;
		
		if (!stringList.equals(""))
		{			
			colUid= stringToCollection(stringList);
			Iterator iterColUid=colUid.iterator();
			ItemValueStrData itemValueAux = null;
			ItemValueStrData itemValue = null;
			while(iterColUid.hasNext())
			{	
				String idItem=(String) iterColUid.next();
				String[] arr = idItem.split("-");
				ItemData item=new ItemData(new Integer(arr[0]));
				String value = arr[1];
				itemValueAux = new ItemValueStrData(item,value);
				if (items.contains(itemValueAux)) {
					itemValue = getItemValueStr(items,item);
					itemValue.setItemValue(itemValue.getItemValue() + value);
				}
				else {
					items.add(itemValueAux);
				}	
			}			
		}
				
		Integer vehicleId = null;
		VehicleReportFacade vehicleReport = null;
		vehicleReport = sys.getVehicleReportFacade();
		VehicleData vehicle = null;		
		if (map.equals("VehicleView")) {
			if ((String) session.getAttribute("id") != null) {
				vehicleId = new Integer ((String) session.getAttribute("id"));
				vehicle = vehicleReport.findVehicleByPrimaryKey(vehicleId,userSessionData);
			}	
			else {
				vehicle = vehicleReport.findVehicleByPlate(((String)createData.get("plate")).trim(),userSessionData);
				if (vehicle == null){
					System.out.println("Error al buscar el vehiculo con placa:"+(String)createData.get("plate"));
				}else{
					session.setAttribute("id",vehicle.getId().toString());
				}
			}
		}
		else {
			vehicle = vehicleReport.findVehicleByPlate(((String)createData.get("plate")).trim(),userSessionData);
		}
		
		// Si vehicle es null entonces desplegar un mensaje de error
		Integer vKm= null;
		String vehicleKm = (String)(createData.get("vehicleKm"));
		String message = validar(vehicleKm,vehicle,(String)createData.get("day"),(String)createData.get("month"),(String)createData.get("year"),(String)createData.get("dayLicenseExpiry"),(String)createData.get("monthLicenseExpiry"),(String)createData.get("yearLicenseExpiry"));
		
		if(message != null) {
			userSessionData.setMsg(messages.getText(message));
			session.setAttribute("VehicleCheckForm",createData);
			userSessionData.setUrl("/vehicle/VehicleCheckForm.jsp?plate="+plate+"&name="+name);
			return mapping.findForward("layout");		
		}
		
		if (!isEmpty(vehicleKm)) {
			vKm = new Integer(vehicleKm);
		}	
		Date date = getDate((String)createData.get("day"),(String)createData.get("month"),(String)createData.get("year"));
		
		Date dateLicenseExpiry = getDate((String)createData.get("dayLicenseExpiry"),(String)createData.get("monthLicenseExpiry"),(String)createData.get("yearLicenseExpiry"));

		VehicleCheckData vehicleCheck = new VehicleCheckData(null,vehicle,date,(String)(createData.get("name")),vKm,(String)(createData.get("obs")),(String)(createData.get("serie")),items,(String)(createData.get("licenseNumber")),dateLicenseExpiry,null);
		
		VehicleABMFacade vehicleABM = null;
		
		//Access ActivityABMFacade
		vehicleABM = sys.getVehicleABMFacade();				
		String comment = "creacion";
		vehicleABM.createOrUpdateVehicleCheck(vehicleCheck,comment,userSessionData);					
				
		// 	Clean session
		session.setAttribute("view",new Boolean(true));
		//session.setAttribute("id",vehicleId);
		session.removeAttribute("VehicleCheckForm");
		session.removeAttribute("idActivity");
		session.removeAttribute("from");
					
		session.setAttribute("idVehicleAlert",vehicleId);
		if(sys.getUserSessionData().isDPACheckList()) {
			return mapping.findForward("dpa");
		}else if(sys.getUserSessionData().isReporterJJ()) {
			return mapping.findForward("reporterjj");
		}else {
			userSessionData.setUrl("vehicle/VehicleView.jsp?id="+vehicleId);
			return mapping.findForward("layout");
		}
				
	}
	
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
		
        return null;
    }    
    
	public Collection stringToCollection(String stringList){
		String[] stringArray=stringList.split("<");
		Collection col = new LinkedList();
		
		for(int i=0; i < stringArray.length; i++){
			col.add(stringArray[i]);
		}
		
		return col;
	}
	    
	public Date getDate(String day,String month,String year) {
	    	if (isEmpty(day) || isEmpty(month) || isEmpty(year)) {
	    		return null;
	    	} else {
	        	Calendar calendar = Calendar.getInstance();
	    		calendar.set(Calendar.DATE,Integer.parseInt(day.trim()));
	    		//calendar.set(Calendar.MONTH,Integer.parseInt(month.trim()));
	            calendar.set(Calendar.MONTH,Integer.parseInt(month.trim())-1);    		
	    		calendar.set(Calendar.YEAR,Integer.parseInt(year.trim()));
	            return calendar.getTime();
	    	}    
	  }	
	    	
	  private boolean isEmpty(String value) {
	     	System.out.println(value);
	   	    return ((value == null) || (value.trim().length()==0));
	  }	    
	        	    	
	  public String validar(String vehiclekm,VehicleData vehicle,String day,String month, String year,String dayLicenseExpiry,String monthLicenseExpiry, String yearLicenseExpiry) { 
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
	    
	    String getValue(Collection items,ItemData item) {
	    	
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

	    ItemValueStrData getItemValueStr(Collection items,ItemData item) {
	    	
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

}
