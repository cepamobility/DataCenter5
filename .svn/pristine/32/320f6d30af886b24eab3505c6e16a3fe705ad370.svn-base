/*
  * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.vehicle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.driver.DriverABMFacade;
import business.vehicle.VehicleABMFacade;

import communication.administration.corporation.tables.CorporationData;
import communication.language.Text;
import communication.vehicle.report.CheckListImageData;
import communication.vehicle.tables.UnconformityData;
import communication.vehicle.tables.VehicleCheckData;

/**
 * @author fcarriquiry
 */
public class DPAVehicleCheckListEditAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

		 HttpSession session = request.getSession();
	        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
	        Text messages = sys.getText();
	        String checkId=(String)request.getParameter("check");
	        String vehicleId=(String)request.getParameter("vehicleId");
	       
	        VehicleCheckData vehicleCheck = sys.getVehicleReportFacade().findVehicleCheck(new Integer(checkId),sys.getUserSessionData());
	        DPAVehicleCheckListEditForm formDPA=(DPAVehicleCheckListEditForm)form;
	      
	        if(formDPA.validate()!=null){
	        	request.getSession().setAttribute("Msg",messages.getText(formDPA.validate()));
	        	request.setAttribute("DPAVehicleCheckListEditForm", formDPA);
	        	if(sys.isCorporation(CorporationData.DPA)){
		        	return mapping.findForward("next");	        		
	        	}else{
	        		return mapping.findForward("next2");
	        	}

	        }
	        vehicleCheck=formDPA.toVehicleCheckData(vehicleCheck);
	        
	        sys.getVehicleABMFacade().createOrUpdateVehicleCheck(vehicleCheck, "", sys.getUserSessionData());
	        
	        request.getSession().setAttribute("check", checkId);
	        request.getSession().setAttribute("vehicleId", vehicleId);
	        request.getSession().setAttribute("vehicle", vehicleId);
	        if(sys.isCorporation(CorporationData.DPA)){
	        	return mapping.findForward("list");
	        }else{
	        	return mapping.findForward("list2");
	        }
        	
	}



	@Override
	public ActionForward cancel(HttpSession session, ActionMapping mapping,
			ActionForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	
    
}
