/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.vehicle;

import java.util.Collection;
import java.util.HashMap;
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
import communication.activity.tables.PointCommentData;
import communication.activity.tables.PointData;
import communication.administration.user.UserSessionData;
import communication.datatype.tables.TypeTradeDataType;
import communication.datatype.tables.TypeVehicleDataType;
import communication.language.Text;
import communication.vehicle.tables.VehicleCheckData;
import communication.vehicle.tables.VehicleData;

/**
 * @author adocarmo
 */
public class SyngentaCheckListAction extends AbstractAction {

		
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
		UserSessionData userSessionData = sys.getUserSessionData();
		Text messages = sys.getText();
		
		SyngentaCheckListForm formData = (SyngentaCheckListForm) form;	
		
		HashMap<String, String> values = new HashMap<String, String>();
		String msg = null;
		
		VehicleCheckData vehicleCheck = new VehicleCheckData();
		if(Util.empty(formData.getName())) {
			msg = messages.getText("checklist.error.name");
		}else {
			values.put("name", formData.getName());
			vehicleCheck.setName(formData.getName());
		}
		if(formData.getCheckDate() == null) {
			if(Util.empty(msg)) 
				msg = messages.getText("checklist.error.data");
		}else {
			values.put("date", formData.getDate());
			values.put("month", formData.getMonth());
			values.put("year", formData.getYear());
			vehicleCheck.setDate(formData.getCheckDate());		
		}
		if(!Util.empty(formData.getCharge())) {
			vehicleCheck.setSerie(formData.getCharge());
			values.put("charge", formData.getCharge());
		}
		Integer vehicleKm = null;
		if(!Util.empty(formData.getVehicleKm())) {
			if(Util.isNumber(formData.getVehicleKm().trim())) {
				vehicleKm = new Integer(formData.getVehicleKm().trim());
				values.put("vehicleKm", formData.getVehicleKm().trim());
			}
		}
		if(vehicleKm == null) {
			msg = messages.getText("checklist.error.kms");
		}
		vehicleCheck.setVehicleKm(vehicleKm);
		if(!Util.empty(formData.getRenavam())) {
			vehicleCheck.setLicenseNumber(formData.getRenavam());
			values.put("renavam", formData.getRenavam());
		}
		vehicleCheck.setObs(formData.getObs());
		values.put("obs", formData.getObs());
		vehicleCheck.setVehicle(sys.getVehicleReportFacade().findVehicleByPrimaryKey(new Integer(formData.getVehicle()),sys.getUserSessionData()));
		if(!Util.empty(formData.getId())) {
			vehicleCheck.setVehicleCheckId(new Integer(formData.getId()));
		}
        CheckListData checkList = sys.getVehicleReportFacade().getCheckList(CheckListData.SYNGENTA_CHECKLIST,sys.getUserSessionData());
        Collection itemValueSet = new LinkedList();
        Collection pointCommentValueSet = new LinkedList();
        Iterator points = checkList.getSortPoints();
        while(points.hasNext()) {
        	PointData point = (PointData)points.next();
        	Iterator itemsIt = point.getSortItems();
            while(itemsIt.hasNext()) {
                ItemData item = (ItemData)itemsIt.next();
                String insertedValue = request.getParameter("item-"+String.valueOf(item.getId()));
                itemValueSet.add(new ItemValueStrData(new ItemData(item.getId()),insertedValue));
                values.put("item-"+String.valueOf(item.getId()), insertedValue);
                if(item.getType() == 1) {
                	pointCommentValueSet.add(new PointCommentData(new PointData(point.getId()),request.getParameter("point-"+String.valueOf(point.getId()))));
                    values.put("point-"+String.valueOf(point.getId()), request.getParameter("point-"+String.valueOf(point.getId())));
                }
                if(item.getType().intValue() == 1) {
	                if(!Util.isNumber(insertedValue) || insertedValue.equals("-1")) {
	                	if(Util.empty(msg)) 
	                		msg = "Debe ingresar "+messages.getText(item.getText());
	                }
                }else {
                	if(Util.empty(insertedValue)) {
                		insertedValue = "0";
                	}
	                if(!Util.isFloat(insertedValue)) {
	                	if(Util.empty(msg)) 
	                		msg = "Debe ingresar "+messages.getText(item.getText());
	                }
                }
            }
        }

        if(!Util.empty(msg)) {
	        userSessionData.setMsg(msg);
	        userSessionData.setValue(values);
			userSessionData.setUrl("/vehicle/SyngentaCheckForm.jsp?id="+formData.getVehicle()+"&type=error");
        }else {
			vehicleCheck.setItemValueSet(itemValueSet);
			vehicleCheck.setPointCommentSet(pointCommentValueSet);
		    
		    sys.getVehicleABMFacade().createOrUpdateVehicleCheck(vehicleCheck, "", userSessionData);
			userSessionData.setUrl("/vehicle/VehicleView.jsp?id="+formData.getVehicle());
        }
        
		return mapping.findForward("success");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
    	((SystemAccess)session.getAttribute("SystemAccess")).getUserSessionData().setUrl("layout.jsp?refer=/vehicle/VehicleView.jsp?id="+((VehicleCheckListForm) form).getVehicle());
		return mapping.findForward("success");
    }    
    
}
