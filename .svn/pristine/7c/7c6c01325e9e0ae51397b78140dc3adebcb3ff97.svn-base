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


public class CDNovartisRegistryCreateAction extends AbstractAction { 

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
                if(Util.empty(request.getParameter("item"+String.valueOf(item.getId())))) {
                    session.setAttribute("Msg",messages.getText("activity.cdargentina.emptyitem"));
                    session.setAttribute("ActivityRegistryForm",activityRegistryForm);
                    session.setAttribute("driver",idDriver);
                    userSessionData.setUrl("/activity/schedule/CommentaryDriveNalcoForm.jsp?idActivity="+idActivity+"&idDriver="+idDriver);
                    return mapping.findForward("layout");       
                }
                itemsValues.add(new ItemValueData(item,new Integer(request.getParameter("item"+String.valueOf(item.getId())))));
            }
            commentValues.add(new PointCommentData(point,request.getParameter("pointComment"+String.valueOf(point.getId()))));
        }

        
        
        String message = null;
        
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
            userSessionData.setUrl("/activity/schedule/AddCEPAActivityScheduleRegistry.jsp?idActivity="+idActivity+"&idDriver="+idDriver);
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

        activityABM.createCEPAScheduleActivityRegistry(registryData,userSessionData,true);
        
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
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
    	SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
    	UserSessionData userSessionData = sys.getUserSessionData();
        DynaActionForm activityRegistryForm = (DynaActionForm) form;
        String idActivity = (String) activityRegistryForm.get("idActivity");
        String idDriver=(String)activityRegistryForm.get("idDriver");   
        userSessionData.setUrl("/activity/schedule/CEPAActivityScheduleView.jsp?id="+idActivity);
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
    
}
