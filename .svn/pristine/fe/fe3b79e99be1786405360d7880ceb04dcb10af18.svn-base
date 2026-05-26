package presentation.translator.web.activity.schedule;

import java.util.ArrayList;
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
import business.activity.ActivityReportFacade;

import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.activity.tables.CheckListData;
import communication.activity.tables.ItemData;
import communication.activity.tables.ItemValueData;
import communication.activity.tables.PointCommentData;
import communication.activity.tables.PointData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverData;


public class CDSRegistryCreateAction extends AbstractAction { 


    public ActionForward action(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
        
        DynaActionForm activityRegistryForm = (DynaActionForm) form;
        
        String note = (String) activityRegistryForm.get("note");
        String obs = (String) activityRegistryForm.get("obs");
            
        String instructor = (String) activityRegistryForm.get("instructor");        
        String idActivity=(String)activityRegistryForm.get("idActivity");
        String idDriver=(String)activityRegistryForm.get("idDriver");   

        int checkListId = Integer.parseInt((String)activityRegistryForm.get("classNumber"));
        ActivityABMFacade activityABM = sys.getActivityABMFacade(); 
    
        
        Collection<ItemValueData> itemsValues = new LinkedList<ItemValueData>();
        Collection<PointCommentData> commentValues = new LinkedList<PointCommentData>();

        CheckListData checkList = sys.getActivityReportFacade().findCheckList(new Integer(checkListId),userSessionData);	
		Collection points = checkList.getPoints();
        Collections.sort((List)points);

        Iterator pointIt = points.iterator();
        while(pointIt.hasNext()) {
            PointData point = (PointData)pointIt.next();
            Collection<ItemData> items = point.getItems();
            Collections.sort((List)items);
            Iterator itemIt = items.iterator();
            if(itemIt.hasNext()) {
                ItemData item = (ItemData)itemIt.next();
                int valueItem = Integer.parseInt(request.getParameter("item"+String.valueOf(item.getId())));
                itemsValues.add(new ItemValueData(item,new Integer(valueItem)));
                if(valueItem == 2) {
                    commentValues.add(new PointCommentData(point,request.getParameter("pointComment"+String.valueOf(point.getId()))));
                }
            }
        }

        CEPAActivityScheduleRegistryData registryData= new 
        CEPAActivityScheduleRegistryData(
        		new CEPAActivityScheduleData(new Integer(idActivity)),
        		new DriverData(	new Integer(idDriver)),
        		note,null,obs,instructor,commentValues,itemsValues,null,null);
        registryData.setHasResult(true);
        
        
        boolean pasarADictada = true;
        /*
         * Obtengo la actividad, para luego preguntar el tipo.
         * Si es COURSE1 no la paso a dictada
         * si es COURSE2 la paso a dictada.
         */
        ActivityReportFacade activityReport = sys.getActivityReportFacade();
        List activities = activityReport.getActivities(idActivity, userSessionData);
        
        if (activities != null && activities.size() > 0){
        	
        	Iterator it = activities.iterator();
        	// Asumimos que con ese id existe solo una actividad.
        	if (it.hasNext()){
        		CEPAActivityScheduleData activity = (CEPAActivityScheduleData)it.next();
        		String activityType = activity.getType().getResourceBundleId();
        		
        		if (activityType.equalsIgnoreCase(CEPAActivityConstants.CEPA_SYSTEM_COURSE_1_PESADOS) ||

        			// Si es Pepsico no paso ninguna actividad a dictada automaticamente
        			(activity.getCorpooration().getId().equals(CorporationData.PEPSICO)) 
        		){        			
        			pasarADictada = false;
        		}
        	}
        }
        
        
        
        activityABM.createCEPAScheduleActivityRegistry(registryData,userSessionData,pasarADictada);
        
        //  Clean session
        session.removeAttribute("ActivityRegistryForm");        
        session.removeAttribute("activityData");
        session.removeAttribute("registryData");
        session.removeAttribute("from"); // variable utilizada en VehicleCheckCreate para determinar desde donde se llamò el formulario
        
        userSessionData.setUrl("/activity/schedule/CEPAActivityScheduleView.jsp?idActivity="+idActivity);
        ActionForward forward = mapping.findForward("layout");
        
        return forward;
        
    }
    
    public boolean contains(Collection items, ItemData item){
        
        Iterator iter = items.iterator();
        boolean contain = false;
        while(iter.hasNext() && !contain){
            ItemData itemData = (ItemData) iter.next();
            contain = (item.getId().intValue() == itemData.getId().intValue());
        }
        return contain;
    }
    
    public Collection removeItem(Collection items, ItemData item){
        
        Collection itemsNew = new ArrayList();
        Iterator iter = items.iterator();
        while(iter.hasNext()){
            ItemData itemData = (ItemData) iter.next();
            if (!(item.getId().intValue() == itemData.getId().intValue())) {
                itemsNew.add(itemData);
            }
        }
        return itemsNew;
    }
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
    	 SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
         UserSessionData userSessionData = sys.getUserSessionData();
         DynaActionForm activityRegistryForm = (DynaActionForm) form;
         String idActivity=(String)activityRegistryForm.get("idActivity");
         userSessionData.setUrl("/activity/schedule/CEPAActivityScheduleView.jsp?idActivity="+idActivity);
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

    
}
