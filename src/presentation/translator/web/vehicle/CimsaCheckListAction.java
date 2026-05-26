/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.vehicle;

import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.activity.tables.CheckListData;
import communication.activity.tables.ItemData;
import communication.activity.tables.ItemValueStrData;
import communication.activity.tables.PointData;
import communication.administration.user.UserSessionData;
import communication.language.Text;
import communication.vehicle.tables.TrailerCheckData;
import communication.vehicle.tables.TrailerData;
import communication.vehicle.tables.TrailerMainData;
import communication.vehicle.tables.VehicleCheckData;
import communication.vehicle.tables.VehicleData;

/**
 * @author adocarmo
 */
public class CimsaCheckListAction extends AbstractAction {

		
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
		UserSessionData userSessionData = sys.getUserSessionData();
		Text messages = sys.getText();
		
		VehicleCheckListForm formData = (VehicleCheckListForm) form;	
		
		VehicleCheckData vehicleCheck = new VehicleCheckData();
		if(Util.empty(formData.getName())) {
			userSessionData.setMsg("Debe ingresar nombre");
			userSessionData.setUrl("/vehicle/CimsaCheckForm.jsp?id="+formData.getVehicle());
			return mapping.findForward("success");
		}
		if(formData.getCheckDate() == null) {
			userSessionData.setMsg("Debe ingresar fecha");
			userSessionData.setUrl("/vehicle/CimsaCheckForm.jsp?id="+formData.getVehicle());
			return mapping.findForward("success");
		}
		vehicleCheck.setName(formData.getName());
		vehicleCheck.setDate(formData.getCheckDate());		
		vehicleCheck.setVehicle(sys.getVehicleReportFacade().findVehicleByPrimaryKey(new Integer(formData.getVehicle()),sys.getUserSessionData()));
		
        CheckListData checkList = sys.getVehicleReportFacade().getCheckList(CheckListData.CIMSA_CHECKLIST,sys.getUserSessionData());
        Collection itemValueSet = new LinkedList();
        Iterator points = checkList.getSortPoints();
        while(points.hasNext()) {
        	PointData point = (PointData)points.next();
        	Iterator itemsIt = point.getSortItems();
            while(itemsIt.hasNext()) {
                ItemData item = (ItemData)itemsIt.next();
                itemValueSet.add(new ItemValueStrData(new ItemData(item.getId()),request.getParameter("item-"+String.valueOf(item.getId()))));
                if(request.getParameter("item-"+String.valueOf(item.getId())).equals("-1")) {
        			userSessionData.setMsg("Debe ingresar "+messages.getText(item.getText()));
        			userSessionData.setUrl("/vehicle/CimsaCheckForm.jsp?id="+formData.getVehicle());
        			return mapping.findForward("success");
                }
            }
        }
		vehicleCheck.setItemValueSet(itemValueSet);
        
        sys.getVehicleABMFacade().createOrUpdateVehicleCheck(vehicleCheck, "", userSessionData);					
		userSessionData.setUrl("/vehicle/VehicleView.jsp?id="+formData.getVehicle());
		return mapping.findForward("success");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
    	((SystemAccess)session.getAttribute("SystemAccess")).getUserSessionData().setUrl("layout.jsp?refer=/vehicle/VehicleView.jsp?id="+((VehicleCheckListForm) form).getVehicle());
		return mapping.findForward("success");
    }    
    
}
