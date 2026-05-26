/*
 * Created on 31-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
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

/**
 * @author adocarmo
 */
public class CDRiskRegistryCreateAction extends AbstractAction { 

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @SuppressWarnings("unchecked")
	public ActionForward action(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        UserSessionData userSessionData = sys.getUserSessionData();
        
        DynaActionForm activityRegistryForm = (DynaActionForm) form;
        String message = null;
        // String idActivity=(String)session.getAttribute("id");
        String idActivity=(String)activityRegistryForm.get("idActivity");
        // String idDriver=(String)session.getAttribute("idDriver"); 
        String idDriver=(String)activityRegistryForm.get("idDriver");
        // session.setAttribute("id",idActivity);
        // session.setAttribute("idDriver",idDriver);
        session.setAttribute("ActivityRegistryForm",activityRegistryForm);  
        
        String note = "";
        String obs = (String) activityRegistryForm.get("obs");
        String instructor = (String) activityRegistryForm.get("instructor");
        if (instructor == null || instructor.trim() == ""){
        	// Error el instructor no puede ser nulo:
        	message = "generic.messages.activity.emptyinstructor";
        	// session.setAttribute("Msg", messages.getText(message));
        	userSessionData.setMsg(messages.getText(message));
        	userSessionData.setUrl("/activity/schedule/CommentaryDriveRiskForm.jsp?idActivity="+idActivity+"&idDriver="+idDriver);
        	return mapping.findForward("layout");       
        }

       
        ActivityABMFacade activityABM = sys.getActivityABMFacade(); 
    
        CEPAActivityScheduleData activity = (CEPAActivityScheduleData) session.getAttribute("activityData");
        if (activity == null) {
            activity = sys.getActivityReportFacade().
            	findCEPAActivityScheduleByPrimaryKey(new Integer(idActivity),sys.getUserSessionData());
        }

        Collection<ItemValueData> itemsValues = new LinkedList<ItemValueData>();               
        Collection<PointCommentData> commentValues = new LinkedList<PointCommentData>();
        Collection points = activity.getDefinition().getCheckList().getPoints();
        Collections.sort((List)points);
        Iterator pointIt = points.iterator();
        while(pointIt.hasNext()) {
            PointData point = (PointData)pointIt.next();
            Collection items = point.getItems();
            Collections.sort((List)items);
            Iterator itemIt = items.iterator();
            while(itemIt.hasNext()) {
                ItemData item = (ItemData)itemIt.next();
                
                /*
                Integer itemValue = (!Util.isNumber(request.getParameter("item"+String.valueOf(item.getId()))))?
                		0:new Integer(request.getParameter("item"+String.valueOf(item.getId())));
                */
                // Valido que el item tenga algun valor seleccionado: 1,2 o 3, sino error:
                String itemValueStr = request.getParameter("item"+String.valueOf(item.getId()));
                if (!Util.isNumber(itemValueStr) || new Integer(itemValueStr).intValue() == 0){
                	
                	message = "generic.messages.activity.itemnotcomplete";
                	// session.setAttribute("Msg", messages.getText(message)
                	//		+" "+messages.getText(item.getText()));
                	
                	userSessionData.setMsg(messages.getText(message)+" "+messages.getText(item.getText()));
                	userSessionData.setUrl("/activity/schedule/CommentaryDriveRiskForm.jsp?idActivity="+idActivity+"&idDriver="+idDriver);
                	return mapping.findForward("layout");  
                }
                
                if (item != null && item.getId() != null ){
                	itemsValues.add(new ItemValueData(item, new Integer(itemValueStr)));
                }
            }
            if (point != null && point.getId() != null){
            	commentValues.add(new PointCommentData(point,
            		request.getParameter("pointComment"+String.valueOf(point.getId()))));
            }
        }

        
        /* Otros campos que no se usan: */
        String partner = (String)activityRegistryForm.get("partner");
        boolean cepaCourse = Boolean.parseBoolean((String)activityRegistryForm.get("cepaCourse"));
        String plate = (String)activityRegistryForm.get("plate");
        String trade = (String)activityRegistryForm.get("trade");
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

        CEPAActivityScheduleRegistryData registryData= new CEPAActivityScheduleRegistryData(activity,driverData,new LinkedList(),note,null,obs,instructor, cepaCourse, partner, null,
                        plate, commentValues, trade, null, null, null, null, 
                        null, circulationAreaHighWay,circulationAreaRoad,
                        circulationAreaSubUrban,circulationAreaUrban,
                        velocityBox, light, false,true, true,true,false,false, itemsValues,
                        0,false,0,null,null);

        activityABM.createCEPAScheduleActivityRegistry(registryData,userSessionData,true);
        
        //  Clean session
        session.removeAttribute("ActivityRegistryForm");        
        session.removeAttribute("activityData");
        session.removeAttribute("registryData");
        session.removeAttribute("from"); // variable utilizada en VehicleCheckCreate para determinar desde donde se llamò el formulario
        
        /*
        session.setAttribute("id",idActivity);
        session.setAttribute("idDriver",idDriver);
        */
        
        userSessionData.setUrl("/activity/schedule/CommentaryDriveRiskView.jsp?idActivity="+idActivity+"&idDriver="+idDriver);
    	return mapping.findForward("layout"); 
    	
        
        
    }   
   
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
    	SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
        DynaActionForm activityRegistryForm = (DynaActionForm) form;
        String idActivity = (String)activityRegistryForm.get("idActivity");
        String idDriver = (String)activityRegistryForm.get("idDriver");
        userSessionData.setUrl("/activity/schedule/CommentaryDriveRiskView.jsp?idActivity="+idActivity+"&idDriver="+idDriver);
       	return mapping.findForward("layout"); 
    }    
   
    
}
