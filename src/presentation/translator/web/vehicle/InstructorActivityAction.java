package presentation.translator.web.vehicle;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import business.SystemAccess;
import business.vehicle.VehicleABMFacade;
import business.vehicle.VehicleABMFacadeUtil;
import communication.administration.user.UserSessionData;
import communication.vehicle.tables.InstructorActivityData;
import persistence.administration.user.tables.User;
import presentation.translator.web.util.AbstractAction;

public class InstructorActivityAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		HttpSession session = request.getSession();
		DynaActionForm activityForm = (DynaActionForm) form;
		SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
		UserSessionData userSessionData = sys.getUserSessionData();
		
		Integer corporation = (Integer)activityForm.get("corporation");
		String user = activityForm.getString("user");
		Integer category = (Integer)activityForm.get("category");
		Integer subcat = (Integer)activityForm.get("subcat");
		String location = activityForm.getString("location");
		String date = activityForm.getString("date");
		Integer channel = (Integer)activityForm.get("channel");
		String referent = activityForm.getString("referent");
		String observation = activityForm.getString("observation");
		
		System.out.println("EL USUARIO ES: " + user);
		
		InstructorActivityData data = new InstructorActivityData();
		data.setChannel(channel);
		data.setCorporation(corporation);
		data.setLocation(location);
		data.setReferent(referent);
		data.setCategory(category);
		data.setSubCategory(subcat);
		data.setUser(userSessionData);
		if(!observation.isEmpty()) data.setObservation(observation);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		data.setDate(sdf.parse(date));
		
		VehicleABMFacade vehicleReport = VehicleABMFacadeUtil.getHome().create();

		vehicleReport.instructorActivityCreate(data, userSessionData);
		
		return mapping.findForward("back");
	}
	
	public ActionForward cancel(HttpSession session, ActionMapping mapping, ActionForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
