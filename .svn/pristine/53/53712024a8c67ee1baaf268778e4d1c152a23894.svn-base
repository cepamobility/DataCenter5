package presentation.translator.web.activity.schedule;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
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
import business.activity.ActivityABMFacade;

import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.activity.tables.ItemData;
import communication.activity.tables.ItemValueData;
import communication.activity.tables.PointCommentData;
import communication.activity.tables.PointData;
import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverData;
import communication.language.Text;


public class EBTWRegistryCreateAction extends AbstractAction { 

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward action(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        UserSessionData userSessionData = sys.getUserSessionData();
        
        DynaActionForm activityRegistryForm = (DynaActionForm) form;
        
        String stringList = (String)activityRegistryForm.get("checkboxParamList");
        String obs = (String) activityRegistryForm.get("obs");
            
        String idActivity = (String)activityRegistryForm.get("idActivity");
        String idDriver   = (String)activityRegistryForm.get("idDriver");   

        String instructor = (String)activityRegistryForm.get("instructor");      
        if(Util.empty(instructor)) {
            session.setAttribute("Msg",messages.getText("cenapra.dataerror.instructor"));
            session.setAttribute("ActivityRegistryForm",activityRegistryForm);
            session.setAttribute("driver",idDriver);
            userSessionData.setUrl("/activity/schedule/ebtw.jsp?idActivity="+idActivity+"&idDriver="+idDriver);
            return mapping.findForward("layout");       
        }

        ActivityABMFacade activityABM = sys.getActivityABMFacade(); 
        
    
        CEPAActivityScheduleData activity = (CEPAActivityScheduleData) session.getAttribute("activityData");
        if (activity == null) {
            activity = sys.getActivityReportFacade().findCEPAActivityScheduleByPrimaryKey(new Integer(idActivity),sys.getUserSessionData());
        }

        Collection<ItemValueData> itemsValues = new LinkedList<ItemValueData>();
        
        Collection<PointCommentData> commentValues = new LinkedList<PointCommentData>();
        Collection points = activity.getDefinition().getCheckList().getPoints();
        Collections.sort((List)points);
        Iterator pointIt = points.iterator();
        while(pointIt.hasNext()) {
            PointData point = (PointData)pointIt.next();
            if(point.getPointOrderReport() == 1) {
                if(Util.empty(request.getParameter("point"+String.valueOf(point.getId())))) {
                	// Si no se marco algun punto, muestro mensaje de error:
                    session.setAttribute("Msg",messages.getText("activity.cdargentina.emptyitem"));
                    session.setAttribute("ActivityRegistryForm",activityRegistryForm);
                    
                    session.setAttribute("driver",idDriver);
                    userSessionData.setUrl("/activity/schedule/ebtw.jsp?idActivity="+idActivity+"&idDriver="+idDriver);
                    return mapping.findForward("layout");       
                }else {
                	Integer value = new Integer(request.getParameter("point"+String.valueOf(point.getId())));
    	            Collection<ItemData> items = point.getItems();
    	            Collections.sort((List)items);
    	            Iterator itemIt = items.iterator();
    	            while(itemIt.hasNext()) {
    	                ItemData item = (ItemData)itemIt.next();
    	                int valueItem = (item.getId().equals(value)) ? 1 : 0;
    	                itemsValues.add(new ItemValueData(item,new Integer(valueItem)));
    	            }
                }
            }else {
	            Collection<ItemData> items = point.getItems();
	            Collections.sort((List)items);
	            Iterator itemIt = items.iterator();
	            while(itemIt.hasNext()) {
	                ItemData item = (ItemData)itemIt.next();
	                String itemValue = request.getParameter("item"+String.valueOf(item.getId()));
	                
	                if (Util.empty(itemValue)){
	                    session.setAttribute("Msg",messages.getText("activity.cdargentina.emptyitem"));
	                    session.setAttribute("ActivityRegistryForm",activityRegistryForm);
	                    
	                    session.setAttribute("driver",idDriver);
	                    userSessionData.setUrl("/activity/schedule/ebtw.jsp?idActivity="+idActivity+"&idDriver="+idDriver);
	                    return mapping.findForward("layout");       
	                }
	           
	                /*
	                ItemData itemData = new ItemData(item.getId());
	                ItemValueData itemValueData = new ItemValueData(itemData,new Integer(itemValue));
	                itemsValuesForm.contains(itemValueData);
	                */
	                
	                itemsValues.add(new ItemValueData(item,new Integer(itemValue)));
	            }
            }
            commentValues.add(new PointCommentData(point,request.getParameter("pointComment"+String.valueOf(point.getId()))));
        }

        
        
        String message = null;
        
        // session.setAttribute("id",idActivity);
        // session.setAttribute("idDriver",idDriver);
        
        if(message != null) {               
            session.setAttribute("ActivityRegistryForm",activityRegistryForm);
            
            session.setAttribute("driver",idDriver);         
            userSessionData.setUrl("/activity/schedule/ebtw.jsp?idActivity="+idActivity+"&idDriver="+idDriver);
            userSessionData.setMsg(messages.getText(message));
            return mapping.findForward("layout");       
        }


        DriverData driverData=new DriverData(new Integer(idDriver));

        CEPAActivityScheduleRegistryData registryData= new CEPAActivityScheduleRegistryData();
        registryData.setActivity(activity);
        registryData.setDriver(driverData);
        registryData.setInstructor(instructor);
        registryData.setObs(obs);
        registryData.setItemValueSet(itemsValues);
        registryData.setPointCommentSet(commentValues);
        registryData.setHasResult(true);

		String ih = (String)activityRegistryForm.get("initialHr");;
		String im = (String)activityRegistryForm.get("initialMinute");;
		if ( (ih != null) && (!ih.equals(""))
				&&	(im != null) && (!im.equals(""))) {
			registryData.setInitialHr(Util.getTime(ih,im));
		}
		String fh = (String)activityRegistryForm.get("finalHr");;
		String fm = (String)activityRegistryForm.get("finalMinute");;
		if ( (fh != null) && (!fh.equals(""))
				&&	(fm != null) && (!fm.equals(""))) {
			registryData.setFinalHr(Util.getTime(fh,fm));
		}	
		registryData.setVehicleType((String)activityRegistryForm.get("vehicleType"));
		registryData.setNote((String)activityRegistryForm.get("note"));

		activityABM.createCEPAScheduleActivityRegistry(registryData,userSessionData,false);
        
        //  Clean session
        session.removeAttribute("ActivityRegistryForm");        
        session.removeAttribute("activityData");
        session.removeAttribute("registryData");
   
        session.removeAttribute("from"); // variable utilizada en VehicleCheckCreate para determinar desde donde se llamò el formulario
        
        // session.setAttribute("id",idActivity);
        // session.setAttribute("idDriver",idDriver);
        userSessionData.setUrl("/activity/schedule/CEPAActivityScheduleView.jsp?id="+idActivity);
        return mapping.findForward("layout");
	}

	
	/**
	 * 
	 */
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
    	SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
        DynaActionForm activityRegistryForm = (DynaActionForm) form;
    	String idActivity = (String)activityRegistryForm.get("idActivity");
    	if (idActivity != null){
    		userSessionData.setUrl("/activity/schedule/CEPAActivityScheduleView.jsp?idActivity="+idActivity);
    	}else{
    		userSessionData.setUrl("/activity/schedule/ActivityList.jsp");
    	}
        return mapping.findForward("layout");
    }

    public String validar(String ikm,String fkm,String ih,String im,String fh,String fm) { 
    	String message = null;

		try {

			if ( (ikm != null) && (!ikm.equals(""))) {
				new Integer(ikm);
			}
						
			if ( (fkm != null) && (!fkm.equals(""))) {
				new Integer(fkm);
			}
		
		} catch (Exception e) {
			message = "generic.activity.trainingonthejob.invalidKm";
			return message;
		}
			
		if ((ih != null) && (im != null)
				&& (!(ih.equals(""))) && (!(im.equals("")))) {
				try {
					new Integer(ih.trim());
					new Integer(im.trim());
					if ( (ih != null) && (im != null) 
							&& (!(ih.equals(""))) && (!(im.equals("")))
							&& (Util.checkHour(Integer.parseInt(ih),Integer.parseInt(im)))
						) {
						message = null;
					}
					else {
						return message = "generic.activity.trainingonthejob.invalidHour";
					}
				} catch (Exception e) {
					message = "generic.activity.trainingonthejob.invalidHour";
					return message;
				}
			}
		else {
			if ((ih == null) || (ih.equals(""))
					&& (im == null) || (im.equals(""))) {
				message = null;
			}	
			else 
				return "generic.activity.trainingonthejob.invalidHour";
		}	
		if ((fh != null) && (fm != null)
				&& (!(fh.equals(""))) && (!(fm.equals("")))) {
				try {
					new Integer(fh.trim());
					new Integer(fm.trim());
					if ( (fh != null) && (fm != null) 
							&& (!(fh.equals(""))) && (!(fm.equals("")))
							&& (Util.checkHour(Integer.parseInt(fh),Integer.parseInt(fm)))
						) {
						message = null;
					}
					else {
						return "generic.activity.trainingonthejob.invalidHour";
					}
				} catch (Exception e) {
					message = "generic.activity.trainingonthejob.invalidHour";
					return message;
				}
			}
		else {
			if ((fh == null) || (fh.equals(""))
					&& (fm == null) || (fm.equals(""))) {
				message = null;
			}	
			else 
				return message = "generic.activity.trainingonthejob.invalidHour";
		}	
		
	
		return message;
    }
    
    /**
	 * Recibe un stringList que tiene items y valores con el formato:
	 * Item1-ValorItem1<Item2-ValorItem2<Item3-ValorItem3 ....
	 * y devuelve una coleccion de elementos ItemValue (Item,Value)
	 */
	private Collection<ItemValueData> stringToItemValueCollection(String stringList)
	{
		String[] stringItemValue;
		String element;
		ItemValueData itemValue;
		String[] stringArray=stringList.split("<");
		Collection<ItemValueData> col = new LinkedList<ItemValueData>();
		
		for(int i=0; i < stringArray.length; i++){
			element  = (String) stringArray[i];
			stringItemValue = (element).split("-");
			itemValue = new ItemValueData(new ItemData(new Integer(stringItemValue[0])),
					new Integer(stringItemValue[1]));	
			col.add(itemValue);
		}
		
		return col;
	}
}
