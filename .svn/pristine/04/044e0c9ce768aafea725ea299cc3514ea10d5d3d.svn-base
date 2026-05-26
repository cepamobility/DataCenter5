package presentation.translator.web.activity.schedule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.activity.ActivityABMFacade;

import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;

public class SimpleActivityRegistryAction extends AbstractAction { 

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
        UserSessionData userSessionData = sys.getUserSessionData();
        
        DynaActionForm activityRegistryForm = (DynaActionForm) form;
        
        String observations = (String)activityRegistryForm.get("observations");
        String idActivity=(String)activityRegistryForm.get("activity");
        String idDriver=(String)activityRegistryForm.get("driver");   

        ActivityABMFacade activityABM = sys.getActivityABMFacade(); 


        CEPAActivityScheduleRegistryData registryData= new CEPAActivityScheduleRegistryData(idActivity,idDriver,observations);
        if(sys.getCorporationId() == CorporationData.LOSPIQUES) {
        	// registryData.setNote((activityRegistryForm.get("note") != null)?(String)activityRegistryForm.get("note"):null);
        	registryData.setNote((String)activityRegistryForm.get("note"));
        	
        }
        
        activityABM.createCEPAScheduleActivityRegistry(registryData,userSessionData,true);
        
        //  Clean session
        session.removeAttribute("SimpleActivityRegistryForm");        
        
        session.setAttribute("id",idActivity);
        session.setAttribute("idDriver",idDriver);
        return mapping.findForward("activity");
        
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        DynaActionForm activityRegistryForm = (DynaActionForm) form;
        session.setAttribute("id",activityRegistryForm.get("activity"));
        session.setAttribute("idDriver",activityRegistryForm.get("driver"));   
        return mapping.findForward("activity");
    }
}
