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
public class DPACheckListXLSAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

		
		 HttpSession session = request.getSession();
         SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
         Text messages = sys.getText();
        
         DPACheckListXLSForm formDPA=(DPACheckListXLSForm) form;
         response.setHeader("Content-Type", "application/vnd.ms-excel");
    	 response.setHeader("Content-Disposition", "inline; filename=\"DPACheckList.xls" + "\"");
    
         sys.getVehicleReportFacade().dpaExportCheckListToXLS(sys.getText(), sys.getUserSessionData(), formDPA.getFilter(), response.getOutputStream());
         
         
         
	     return null;
        	
	}
	

	@Override
	public ActionForward cancel(HttpSession session, ActionMapping mapping,
			ActionForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	
    
}
