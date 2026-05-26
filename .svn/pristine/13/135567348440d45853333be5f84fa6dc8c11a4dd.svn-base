package presentation.translator.web.activity.schedule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.activity.ActivityABMFacade;

import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.administration.user.UserSessionData;
import communication.language.Text;


/**
 * @author adocarmo
 */
public class ActivityCreateAction extends AbstractAction {

	/**
	 * 
	 */	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        
		UserSessionData userSessionData = sys.getUserSessionData();
		
		ActivityForm createData = (ActivityForm)form;
		
		String message = null;
		
		message = createData.validate(userSessionData);
		if(message != null && 
				!message.equals("generic.activity.activitydata.commentInvalid")) {				
			
			//	Some attribute data is invalid	
			//	Si en el struts config indico que el form es de sesion la línea de abajo es inneseraria
			session.setAttribute("ActivityForm",createData);
			
			// session.setAttribute("Msg", messages.getText(message));
			userSessionData.setUrl("/activity/schedule/CEPAActivityScheduleCreate.jsp");
			userSessionData.setMsg(messages.getText(message));
			return mapping.findForward("layout");		
		}

		// Si la actividad es commentarydrive de monsanto cargo la divorg y la divgeo con valores 
		// por defecto. Se comporta igual para comentarydriverisk. 
		if (createData.getType().equals(CEPAActivityConstants.COMMENTARYDRIVE) ||
				createData.getType().equals(CEPAActivityConstants.COMMENTARYDRIVEHIGHRISK) ||
				createData.getType().equals(CEPAActivityConstants.COMMENTARYDRIVEARGENTINA_NUEVO) ||
				createData.getType().equals(CEPAActivityConstants.COMMENTARYDRIVEARGENTINA)) {
			createData.setCountry1("5523");
			createData.setCountry2("32");
			createData.setDivOrg11("5860");
			createData.setDivOrg21("5742");
			createData.setDivOrg22("5777");
			createData.setDivOrg23("5420");
			createData.setDivOrg31("82704");
			createData.setDivOrg32("212245");
		}
		
		
		CEPAActivityScheduleData activity = ActivityUtil.
			createCEPAActivityScheduleData(userSessionData,createData,false,sys);
		ActivityABMFacade activityABM = null;
			
		//Access ActivityABMFacade
		activityABM = sys.getActivityABMFacade();				
		Integer activityId = activityABM.createCEPAScheduleActivity(activity,createData.getComment(),userSessionData);					
		
		session.setAttribute("activityview",new Boolean(true));
		
		String activityIdStr = activityId.toString();
		session.setAttribute("id",activityIdStr);
		//session.setAttribute("id",activityId);
		
		// 	Clean session
		session.removeAttribute("ActivityForm");
		session.removeAttribute("cityJavascript");
		session.removeAttribute("typeJavascript");
		session.removeAttribute("diaJavascript");
		session.removeAttribute("mesJavascript");
		session.removeAttribute("anioJavascript");		
		
		
		if (activity.getType().getResourceBundleId().equals(CEPAActivityConstants.COMMENTARYDRIVE) ||
				activity.getType().getResourceBundleId().equals(CEPAActivityConstants.COMMENTARYDRIVEOTHER) ||	
                activity.getType().getResourceBundleId().equals(CEPAActivityConstants.COMMENTARYDRIVEARGENTINA) ||
                activity.getType().getResourceBundleId().equals(CEPAActivityConstants.COMMENTARYDRIVEARGENTINA_NUEVO) ||
                activity.getType().getResourceBundleId().equals(CEPAActivityConstants.COMMENTARYDRIVE_NOVARTIS) ||
                activity.getType().getResourceBundleId().equals(CEPAActivityConstants.COMMENTARYDRIVEHIGHRISK) ||
                activity.getType().getResourceBundleId().equals(CEPAActivityConstants.COMMENTARYDRIVECAYACO) ||
                activity.getType().getResourceBundleId().equals(CEPAActivityConstants.COMMENTARYDRIVE_JJ2) ||
                activity.getType().getResourceBundleId().equals(CEPAActivityConstants.COMMENTARYDRIVE_RENOVATION) ||
                activity.getType().getResourceBundleId().equals(CEPAActivityConstants.INITIAL_FOREIGN_EVALUATION) ||
                // CEPA SYSTEM
                activity.getType().getResourceBundleId().equals(CEPAActivityConstants.CEPA_SYSTEM_COURSE_1_LIVIANOS) ||
                activity.getType().getResourceBundleId().equals(CEPAActivityConstants.CEPA_SYSTEM_COURSE_2_ENTRENADORES)		
			) {
		
			/*
			 * Actividades que solo pueden elegir un ùnico conductor: 
			 */
			
			userSessionData.setUrl("/driver/DriverSearchForActivity.jsp?pageNum=1&idActivity="+activityIdStr+"&onlyOne=true");
			return mapping.findForward("layout");
		
		}else {
			
			userSessionData.setUrl("/success.jsp?entity=activity.create");
			return mapping.findForward("layout");				
		}		
	}
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
    	
    	SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
     	userSessionData.setUrl("/activity/schedule/ActivityList.jsp");
     	
     	return mapping.findForward("layout");
    }    
	    
	

}
