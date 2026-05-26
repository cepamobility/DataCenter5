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

import communication.language.Text;
import communication.vehicle.report.CheckListImageData;
import communication.vehicle.tables.UnconformityData;

/**
 * @author fcarriquiry
 */
public class VehicleDPAImageUploadAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        if(request.getParameter("deleteImages")!=null){
        	return  actionDelete(mapping, form, request, response);
        }else{
        	return actionUpload(mapping, form, request, response);
        }
        	
	}


	public ActionForward actionDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        String checkId=(String)request.getParameter("check");
        String vehicleId=(String)request.getParameter("vehicleId");
        String listStr=(String) request.getParameter("dImages");
        
        
        List list=new ArrayList();
        
        String[] arrStr=listStr.split(";");
        for(int i=0; i<arrStr.length;i++){
        	list.add(arrStr[i]);
        }
        
        if(list.size()>0){
        	sys.getVehicleABMFacade().deleteCheckListImages(list, Integer.valueOf(checkId), sys.getUserSessionData());
        }
        
        request.getSession().setAttribute("check", checkId);
        request.getSession().setAttribute("vehicleId", vehicleId);
        
        return mapping.findForward("next");
        	
	}

	public ActionForward actionUpload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        String checkId=(String)request.getParameter("check");
        String vehicleId=(String)request.getParameter("vehicleId");
        
        DPAImageUploadForm formDPA=(DPAImageUploadForm)form;
        
        List list=new ArrayList();
        Iterator<Integer> iter=formDPA.getItems().keySet().iterator();
        String msg=messages.getText("gerneric.messages.uploadfiles")+":";
        while(iter.hasNext()){
        	Integer key=iter.next();
        	FormFile file=(FormFile)formDPA.getItems().get(key).getFile();
        	if(file!=null && file.getFileName()!=null && file.getFileName().trim().length()>0){
	        	Integer point=Integer.valueOf((String)formDPA.getItems().get(key).getPoint());
	        	String text=(String)formDPA.getItems().get(key).getText();
	        	System.out.println("Archivos recividos: Point="+point+" - File:"+file.getFileName()+"-Text:"+text);
	        	CheckListImageData data=new CheckListImageData();
	        	data.setCheckId(Integer.valueOf(checkId));
	        	data.setFileName(file.getFileName());
	        	data.setInputStream(file.getInputStream());
	        	data.setPointId(point);
	        	data.setText(text);
	        	
	        	msg+=file.getFileName()+"|";
	        	
	        	list.add(data);
        	}
        }
        
        if(list.size()>0){
        	sys.getVehicleABMFacade().uploadCheckListImages(list, Integer.valueOf(checkId), sys.getUserSessionData());
        	session.setAttribute("Msg", msg);
        }
        
        request.getSession().setAttribute("check", checkId);
        request.getSession().setAttribute("vehicleId", vehicleId);
        return mapping.findForward("next");
        	
	}


    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        DynaActionForm formData = (DynaActionForm)form;
        session.setAttribute("id",formData.getString("id"));
        return mapping.findForward("cancel");
    }
}
