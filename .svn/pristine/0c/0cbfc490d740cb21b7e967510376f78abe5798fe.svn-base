package presentation.translator.web.activity.schedule;

import java.sql.Time;
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

import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.activity.tables.ItemData;
import communication.activity.tables.ItemValueData;
import communication.activity.tables.PointCommentData;
import communication.activity.tables.PointData;
import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverData;
import communication.language.Text;


public class CDArgentinaNewRegistryCreateAction extends AbstractAction { 

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
        
        String note = (String) activityRegistryForm.get("note");
        String obs = (String) activityRegistryForm.get("obs");
            
        String instructor = (String) activityRegistryForm.get("instructor");        
        // String idActivity=(String)session.getAttribute("id");
        String idActivity = (String) activityRegistryForm.get("idActivity");
        // String idDriver=(String)session.getAttribute("idDriver");
        String idDriver=(String)activityRegistryForm.get("idDriver");   

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
            Collection<ItemData> items = point.getItems();
            Collections.sort((List)items);
            Iterator itemIt = items.iterator();
            while(itemIt.hasNext()) {
                ItemData item = (ItemData)itemIt.next();
                if(Util.isChecked(request.getParameter("item"+String.valueOf(item.getId())))) {
                    itemsValues.add(new ItemValueData(item,new Integer(1)));
                }else {
                    itemsValues.add(new ItemValueData(item,new Integer(0)));
                }
            }
            commentValues.add(new PointCommentData(point,request.getParameter("pointComment"+String.valueOf(point.getId()))));
        }

        
        /* Commentary Drive: */
        String partner = (String)activityRegistryForm.get("partner");
        boolean cepaCourse = Boolean.parseBoolean((String)activityRegistryForm.get("cepaCourse"));
        String conductionType = (String)activityRegistryForm.get("conductionType");
        String plate = (String)activityRegistryForm.get("plate");
        /* Fin Commentary Drive  */
        
        
        // Tranning on the Job
        String trade = (String)activityRegistryForm.get("trade");
        
        String message = null;
        
        Integer initialKm = new Integer(0);
        if(Util.isNumber((String)activityRegistryForm.get("initialKm"))) {
            initialKm = new Integer((String)activityRegistryForm.get("initialKm"));
        }
        Integer finalKm = new Integer(0);
        if(Util.isNumber((String)activityRegistryForm.get("finalKm"))) {
            finalKm = new Integer((String)activityRegistryForm.get("finalKm"));
        }
        
        Time initialHr = Util.getTime((String)activityRegistryForm.get("initialHr"),(String)activityRegistryForm.get("initialMinute"));
        Time finalHr = Util.getTime((String)activityRegistryForm.get("finalHr"),(String)activityRegistryForm.get("finalMinute"));
        
        // session.setAttribute("id",idActivity);
        // session.setAttribute("idDriver",idDriver);
        
        if(message != null) {               
            //Some attribute data is invalid    
            //Si en el struts config indico que el form es de sesion la línea de abajo es inneseraria       
            // session.setAttribute("Msg", messages.getText(message));
            //session.setAttribute("id",idActivity);
            //session.setAttribute("idDriver",idDriver);
            
            session.setAttribute("ActivityRegistryForm",activityRegistryForm);
         
            userSessionData.setMsg(messages.getText(message));
            userSessionData.setUrl("/activity/schedule/CommentaryDriveArgentinaForm.jsp?idActivity="+idActivity+"&idDriver="+idDriver);
            return mapping.findForward("layout");       
        }

        String vehicleType = (String)activityRegistryForm.get("vehicleType");
        /* Circulation Area */
		
		// String circulationArea = (String)activityRegistryForm.get("circulationArea");
		String ca = (String)activityRegistryForm.get("circulationAreaHighWay");
		boolean circulationAreaHighWay = (ca != null && ca.trim() != "")?Boolean.parseBoolean(ca.trim()):false;
		ca = (String)activityRegistryForm.get("circulationAreaRoad");
		boolean circulationAreaRoad = (ca != null && ca.trim() != "")?Boolean.parseBoolean(ca.trim()):false;
		ca = (String)activityRegistryForm.get("circulationAreaSubUrban");
		boolean circulationAreaSubUrban = (ca != null && ca.trim() != "")?Boolean.parseBoolean(ca.trim()):false;
		ca = (String)activityRegistryForm.get("circulationAreaUrban");
		boolean circulationAreaUrban = (ca != null && ca.trim() != "")?Boolean.parseBoolean(ca.trim()):false;
		
		/* Fin Circulation Area */
        String velocityBox = (String)activityRegistryForm.get("velocityBox");
        String light = (String)activityRegistryForm.get("light");   


        DriverData driverData=new DriverData(new Integer(idDriver));

        CEPAActivityScheduleRegistryData registryData= new CEPAActivityScheduleRegistryData(activity,driverData,new LinkedList(),note,null,obs,instructor, cepaCourse, partner, conductionType,
                        plate, commentValues, trade, initialKm, finalKm,
                        initialHr,finalHr, vehicleType, circulationAreaHighWay,circulationAreaRoad,circulationAreaSubUrban,circulationAreaUrban, velocityBox, light, false,true, true,true, false, false, itemsValues,0,false,0,null,null);

        activityABM.createCEPAScheduleActivityRegistry(registryData,userSessionData,true);
        
        //  Clean session
        session.removeAttribute("ActivityRegistryForm");        
        session.removeAttribute("activityData");
        session.removeAttribute("registryData");
        session.removeAttribute("from"); // variable utilizada en VehicleCheckCreate para determinar desde donde se llamò el formulario
        
        // session.setAttribute("id",idActivity);
        // session.setAttribute("idDriver",idDriver);
        userSessionData.setUrl("/activity/schedule/CDArgentinaView.jsp?idActivity="+idActivity+"&idDriver="+idDriver);
        return mapping.findForward("layout");
        
    }
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
    	SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
    	UserSessionData userSessionData = sys.getUserSessionData();
        DynaActionForm activityRegistryForm = (DynaActionForm) form;
        String idActivity = (String) activityRegistryForm.get("idActivity");
        String idDriver=(String)activityRegistryForm.get("idDriver");   
        userSessionData.setUrl("/activity/schedule/CDArgentinaView.jsp?idActivity="+idActivity+"&idDriver="+idDriver);
        return mapping.findForward("layout");
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
