/*
  * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.vehicle;

import java.util.Date;

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

/**
 * @author fcarriquiry
 */
public class CheckListForestalesXLSAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		 HttpSession session = request.getSession();
         SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        
         DynaActionForm filterForm = (DynaActionForm) form;
         response.setHeader("Content-Type", "application/vnd.ms-excel");
    	 response.setHeader("Content-Disposition", "inline; filename=\"CheckList.xls" + "\"");

    	 Date start = Util.getDate(filterForm.getString("startDay"), filterForm.getString("startMonth"), filterForm.getString("startYear"));
    	 Date end = Util.getDate(filterForm.getString("endDay"), filterForm.getString("endMonth"), filterForm.getString("endYear"));
    	 boolean comment=false;
    	 if(filterForm.get("comment")!=null){
    		 comment=(Boolean)filterForm.get("comment");
    	 }
    	 Integer vehicleType = (filterForm.getString("vehicletype").equals("") || filterForm.getString("vehicletype").equals("0")) ? null : Integer.parseInt(filterForm.getString("vehicletype"));
    	 Integer chargeType = (filterForm.getString("chargetype").equals("") || filterForm.getString("chargetype").equals("0")) ? null : Integer.parseInt(filterForm.getString("chargetype"));
    	 Integer checkPlace = (filterForm.getString("checkplace").equals("") || filterForm.getString("checkplace").equals("0")) ? null : Integer.parseInt(filterForm.getString("checkplace"));
         sys.getVehicleReportFacade().checkListReportToXLS(comment,Integer.parseInt(filterForm.getString("type")), start, end, vehicleType, chargeType, checkPlace, response.getOutputStream(), sys.getText(), sys.getUserSessionData());
         
	     return null;
	}

	@Override
	public ActionForward cancel(HttpSession session, ActionMapping mapping, ActionForm form) {
		return null;
	}
    
}
