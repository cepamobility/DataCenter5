package presentation.translator.web.vehicle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.vehicle.VehicleABMFacade;
import business.vehicle.VehicleReportFacade;

import communication.administration.user.UserSessionData;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;

/**
 * @author jrodriguez
 */
public class VehicleUpdateAction extends AbstractAction {

	// This action is executed from all services that involve updating of vehicles (edit vehicle, change driver assigned, assign a driver to vehicle, and 
	// change to institutional
	
	public ActionForward action(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        UserSessionData userSessionData = sys.getUserSessionData();		
			
		String comment = "";
		VehicleForm updateData = (VehicleForm) form;
		
			//Control de matricula (elimina los caracteres especiales y los espacios) 
		String matricula = VehicleUtil.getCleanMatricula(updateData.getMatricula());
		updateData.setMatricula(matricula);
			
			// Caso de modificación del driver asignado
		if (session.getAttribute("driverId") !=null) {
			updateData.setDriver((String) session.getAttribute("driverId"));
			//session.removeAttribute("driverId");
		}	
		boolean asignDriver = false;
		    
		String msg = null;
		// caso de update de datos básicos
		if (session.getAttribute("updateDriverOrTypeUse") == null ) {
			msg = updateData.validate(userSessionData);
				
			if (msg != null) {				
					session.setAttribute("Msg",messages.getText(msg));
					userSessionData.setUrl("/vehicle/VehicleEdit.jsp?id="+updateData.getId());
					return mapping.findForward("layout");
				} 
			
				// OJO en el update la función vehicleExist no sirve para detectar si estoy 
				// cambiándole la matricula para una matricula ya existente.
				// En este caso tengo que invocar a otra función.
				msg = VehicleUtil.existOther(sys,updateData,userSessionData);
				
				if (msg != null) {				
					session.setAttribute("Msg",messages.getText(msg));
					userSessionData.setUrl("/vehicle/VehicleEdit.jsp?id="+updateData.getId());
					return mapping.findForward("layout");
				} 
			}
			//caso en el que se está asignando un driver a un vehículo que era institucional o
			// se desasocia un driver o se modifica el driver asociado (en este caso no hay form a validar)
			else {  
				if (((String)session.getAttribute("updateDriverOrTypeUse")).equals("changeToInstitutional"))
					comment = messages.getText("generic.messages.changeToInstitutional") + ": " +
							(String) updateData.getComment();				
				if (((String)session.getAttribute("updateDriverOrTypeUse")).equals("assignDriver"))
					comment = messages.getText("generic.messages.assignDriver");
					asignDriver = true;
				if (((String)session.getAttribute("updateDriverOrTypeUse")).equals("changeDriver"))
					comment = messages.getText("generic.messages.changeDriver");				
				updateData.setComment(comment);
				session.removeAttribute("updateDriverOrTypeUse");
			}
			
			VehicleData vehicle = VehicleUtil.createVehicleData(userSessionData,updateData);
				
			
			
			//Access UserABMFacade
			VehicleABMFacade vehicleABM = sys.getVehicleABMFacade();
			
			if (updateData.getComment() != null){
				comment = updateData.getComment();
			}
			
			vehicleABM.vehicleUpdate(vehicle,comment,asignDriver,userSessionData);
		
			// Clean session
			session.removeAttribute("driverId");				
			session.removeAttribute("VehicleCreateForm");
				
			userSessionData.setUrl("/vehicle/VehicleList.jsp");
			return mapping.findForward("layout");
		}	
	
        public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
            ((SystemAccess)session.getAttribute("SystemAccess")).getUserSessionData().setUrl("/vehicle/VehicleEdit.jsp?id="+((VehicleForm)form).getId());
			return mapping.findForward("layout");
        }

}
