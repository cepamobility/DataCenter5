package presentation.translator.web.activity.schedule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.activity.ActivityReportFacade;

import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.activity.tables.CheckListData;
import communication.administration.user.UserSessionData;
import communication.language.Text;

public class CDCayacoRegistryCreateAction extends AbstractAction { 

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
	
			ActivityReportFacade activityReport = sys.getActivityReportFacade();
			ActivityCDCayacoRegistryForm activityRegistryForm = (ActivityCDCayacoRegistryForm) form;
			CheckListData checkList = activityReport.findCheckList(new Integer(34), userSessionData);
	
			String validate = activityRegistryForm.validate(checkList.getPoints(), request, messages);
			if(validate != null) {
				// session.setAttribute("Msg", validate);
				userSessionData.setMsg(validate);
				session.setAttribute("ErrorActivityCDCayacoRegistryForm", activityRegistryForm);
				userSessionData.setUrl("/activity/schedule/AddCEPAActivityScheduleRegistry.jsp?idDriver="+activityRegistryForm.getDriver()+"&idActivity="+activityRegistryForm.getActivity()+"&enabled=false");
				
				return mapping.findForward("layout");
			}
	 		CEPAActivityScheduleRegistryData registryData = activityRegistryForm.getRegistryData(checkList.getPoints(), request);
	
	 		// Si ya fueron ingresados los resultados de todos los participantes (o todos excepto el del participante actual) y la actividad es commentary entonces al ingresar este resultado la actividad se pasa a dictada
	 		boolean existeOtroDriverSinResultado = activityReport.existsCeroOrOneDriverWithOutResultForActivity(new Integer(activityRegistryForm.getActivity()),userSessionData,new Integer(activityRegistryForm.getDriver()));
	
	 		sys.getActivityABMFacade().createCEPAScheduleActivityRegistry(registryData,userSessionData,!existeOtroDriverSinResultado);
			
			// 	Clean session
			session.removeAttribute("ActivityCDCayacoRegistryForm");		
			// session.setAttribute("idActivity", activityRegistryForm.getActivity());
			// session.setAttribute("idDriver", activityRegistryForm.getDriver());
			
			userSessionData.setUrl("/activity/schedule/CEPAActivityScheduleView.jsp?idActivity="+
					activityRegistryForm.getActivity()+"&idDriver="+activityRegistryForm.getDriver());
			return mapping.findForward("layout");
        
	}

	@Override
	public ActionForward cancel(HttpSession session, ActionMapping mapping, ActionForm form) {
		ActivityCDCayacoRegistryForm activityRegistryForm = (ActivityCDCayacoRegistryForm) form;
		
		//session.setAttribute("idActivity", activityRegistryForm.getActivity());
		//session.setAttribute("idDriver", activityRegistryForm.getDriver());
		
		SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
		UserSessionData userSessionData = sys.getUserSessionData();
		userSessionData.setUrl("/activity/schedule/CEPAActivityScheduleView.jsp?idActivity="+
				activityRegistryForm.getActivity()+"&idDriver="+activityRegistryForm.getDriver());
		return mapping.findForward("layout");
	}
	
}
