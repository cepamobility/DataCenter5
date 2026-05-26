/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.vehicle;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.activity.tables.CheckListData;
import communication.administration.user.UserSessionData;
import communication.vehicle.tables.VehicleCheckData;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DownloadChecklistPDFAction extends AbstractAction {
	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
		
		DynaActionForm formData = (DynaActionForm)form;
								
		Integer id=(Integer) formData.get("id");
		if(id!=null){
			VehicleCheckData vehicleCheckListData=sys.getVehicleReportFacade().findVehicleCheck(id, userSessionData);
			File file=null;
			CheckListData checklistData=sys.getVehicleReportFacade().getCheckList(new Integer(vehicleCheckListData.getName()), userSessionData);
			if(checklistData.getId()==CheckListData.CHECK_LIST_FORMULARIO_ACU ){
				String path=sys.getVehicleReportFacade().reportChecklistPDFACU("checklist.pdf", checklistData, vehicleCheckListData, userSessionData, sys.getText());
				response.setHeader("Content-Type", "application/pdf");
		   	 	response.setHeader("Content-Disposition", "inline; filename=\"CheckList_"+vehicleCheckListData.getVehicle().getMatricula().replace(" ", "_")+".pdf");
		   	 	
		   	 	file=new File(path);
				
			}else if(checklistData.getId()==CheckListData.MDP_CHECKLIS_MERCANCIASPELIGROSAS || checklistData.getId()==CheckListData.MDP_CHECKLIS_MERCANCIASPELIGROSAS_2  ){
				String path=sys.getVehicleReportFacade().reportChecklistPDFMP("checklist.pdf", checklistData, vehicleCheckListData, userSessionData, sys.getText());
				response.setHeader("Content-Type", "application/pdf");
		   	 	response.setHeader("Content-Disposition", "inline; filename=\"CheckList_"+vehicleCheckListData.getVehicle().getMatricula().replace(" ", "_")+".pdf");
		   	 	file=new File(path);
				
			}else if(checklistData.getId()==CheckListData.UPM_CHECKLIST_PESADOS || checklistData.getId()==CheckListData.UPM_CHECKLIST_SEGRUTA || checklistData.getId()==CheckListData.UPM_CHECKLIST_INTERNO){
			 	String idVehicle = (String)request.getParameter("vehicle");
				String checkId = (String)request.getParameter("check");				 
		       
		        String fileName=vehicleCheckListData.getVehicle().getMatricula().replace(" ", "_")+".pdf";
		        String path = sys.getVehicleReportFacade().reportChecklistPDF(fileName, checklistData, vehicleCheckListData, userSessionData, sys.getText());
		        response.setHeader("Content-Type", "application/pdf");
		   	 	response.setHeader("Content-Disposition", "inline; filename=\"CheckList_"+vehicleCheckListData.getVehicle().getMatricula().replace(" ", "_")+".pdf");
		   	 	
		   	 	file=new File(path);
			} else if(checklistData.getId()==CheckListData.CHECK_LIST_SYNGENTA_BR){
			 	String idVehicle = (String)request.getParameter("vehicle");
				String checkId = (String)request.getParameter("check");				 
		       
		        String fileName=vehicleCheckListData.getVehicle().getMatricula().replace(" ", "_")+".pdf";
		        String path = sys.getVehicleReportFacade().reportChecklistPDFSyngentaBR(fileName, checklistData, vehicleCheckListData, userSessionData, sys.getText());
		        response.setHeader("Content-Type", "application/pdf");
		   	 	response.setHeader("Content-Disposition", "inline; filename=\"CheckList_"+vehicleCheckListData.getVehicle().getMatricula().replace(" ", "_")+".pdf");
		   	 	
		   	 	file=new File(path);
			} 
			
			OutputStream oos = response.getOutputStream();

	        byte[] buf = new byte[8192];

	        InputStream is = new FileInputStream(file);

	        int c = 0;

	        while ((c = is.read(buf, 0, buf.length)) > 0) {
	            oos.write(buf, 0, c);
	            oos.flush();
	        }

	        oos.close();
	        is.close();
		}
		
		return null;
	}
			
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("cancel");
    }    
	

}
