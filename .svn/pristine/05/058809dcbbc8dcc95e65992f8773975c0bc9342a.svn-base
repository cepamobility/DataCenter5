/*
  * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.dpa;

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

import communication.language.Text;
import communication.security.SecurityConstants;
import communication.vehicle.report.CheckListImageData;
import communication.vehicle.tables.UnconformityData;
import communication.vehicle.tables.VehicleCheckData;

/**
 * @author fcarriquiry
 */
public class DPAUnconformityListAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

		if(request.getParameter("delete")!=null){
       	 return deleteAction(mapping, form, request, response);
        }	
		 HttpSession session = request.getSession();
         SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
         Text messages = sys.getText();
        
         DPAUnconformityListForm formDPA=(DPAUnconformityListForm) form;
         
         formDPA.setResult(sys.getCorporationReportFacade().findUnconformityDPAByFilter(sys.getUserSessionData(), formDPA.getFilter()));
         request.setAttribute("DPAUnconformityListForm", formDPA);
         
	     return mapping.findForward("next");
        	
	}
	
	public ActionForward deleteAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
         
        String check = Util.checkPermission(sys,SecurityConstants.EDIT_VEHICLE);
 		String check1 = Util.checkPermission(sys,SecurityConstants.MODIFY_VEHICLE);	
 		String check2 = Util.checkPermission(sys,SecurityConstants.CHECKLIST_VEHICLE);	
 		
 		
 		if (check!=null) { 
 			response.sendRedirect(request.getContextPath()+check);	
 		}else if (check1!=null) { 
 			response.sendRedirect(request.getContextPath()+check1);	
 		} else if (check2!=null) { 
 			response.sendRedirect(request.getContextPath()+check2);	
 		}else {
 			sys.getCorporationABMFacade().deleteUnconformityDPA((String) request.getParameter("ids"), sys.getUserSessionData());
 		}
 		
        return null;
	}


	@Override
	public ActionForward cancel(HttpSession session, ActionMapping mapping,
			ActionForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	
    
}
