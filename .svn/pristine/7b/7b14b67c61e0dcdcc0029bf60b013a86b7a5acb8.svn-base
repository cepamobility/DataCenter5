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
import org.apache.struts.action.DynaActionForm;

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
public class DeleteVehicleCheckAction extends AbstractAction {

		
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
		UserSessionData userSessionData = sys.getUserSessionData();
		
		DynaActionForm formData = (DynaActionForm) form;	
		Collection checks = Util.stringToCollection(formData.getString("list"));
        
        sys.getVehicleABMFacade().deleteSimpleVehicleCheck(checks, userSessionData);					
		// 	Clean session
		session.removeAttribute("DeleteSimpleVehicleCheckForm");
		userSessionData.setUrl("/vehicle/VehicleView.jsp?id="+formData.getString("vehicle"));
		return mapping.findForward("next");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
		DynaActionForm formData = (DynaActionForm) form;	
		session.removeAttribute("DeleteSimpleVehicleCheckForm");
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
		sys.getUserSessionData().setUrl("/vehicle/VehicleView.jsp?id="+formData.getString("vehicle"));
		return mapping.findForward("next");
    }    
    
}
