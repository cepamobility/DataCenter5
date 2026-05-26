/*
 * Created on 31-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.vehicle;

import java.util.Collection;
import java.util.Date;
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
import business.vehicle.VehicleABMFacade;

import communication.language.Text;
import communication.vehicle.tables.VehicleCheckData;
import communication.vehicle.tables.VehicleData;

/**
 * @author adocarmo
 */
public class VehicleDeleteAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
		DynaActionForm vehicleForm = (DynaActionForm) form;
        String stringList = (String) vehicleForm.get("checkboxParamList");
        String action = (String) vehicleForm.get("action");
 
	        VehicleABMFacade vehicleABM = sys.getVehicleABMFacade();

	        if (action.equals("delete")) {
	            if (Util.empty((String) vehicleForm.get("comment"))) {
	                session.setAttribute("Msg", messages.getText("driver.error.invalidcomment"));
	                return mapping.findForward("exclude");
	            }
	            Collection colUid = Util.stringToCollection(stringList);
	            Iterator iterColUid = colUid.iterator();
	            String message="";
	            int count=0;
	            //VehicleMainAttributes vehicle;
	            while (iterColUid.hasNext()) {
	                String id = (String) iterColUid.next();
	                VehicleData vehicleData = sys.getVehicleReportFacade().findVehicleById(new Integer(id),sys.getUserSessionData());
	                Collection events=sys.getEventReportFacade().getEventsByVehicle(new Integer(id),sys.getUserSessionData());
	                Integer numberFleets= sys.getFleetReportFacade().countFleetsWithVehicle(new Integer(id),sys.getUserSessionData());
	                Iterator itevents=events.iterator();
	                if ((vehicleData.getDriver() != null)||(itevents.hasNext())||((numberFleets!=null)&&(numberFleets.intValue()!=0))){
	                	if (count==0)
	                		message+= messages.getText("generic.vehicle.error.deleteini")+" "+"   - "+vehicleData.getMatricula();	                		
	                	else if(count>0)
	                		message+=" "+"   - "+vehicleData.getMatricula();
	                	count+=1;
	                }
                }   
	            if (count>0){
	            	message+=" "+messages.getText("generic.vehicle.error.deleteend");
	            	session.setAttribute("Msg", message);

	            }else{
	            	VehicleCheckData vcd =null;
	            	Iterator iterCol =colUid.iterator();
	            	while (iterCol.hasNext()){
	            		String id = (String) iterCol.next();
	            		VehicleData vehicleData = sys.getVehicleReportFacade().findVehicleById(new Integer(id),sys.getUserSessionData());
	            		
	            		while (sys.getVehicleReportFacade().vehicleCheckExist(vehicleData.getId())) {
	            			vcd = sys.getVehicleReportFacade().findLastVehicleCheck(vehicleData.getId(),sys.getUserSessionData());
	            		   	vehicleABM.deleteVehicleCheck(vcd.getVehicleCheckId(),"delete",sys.getUserSessionData());
	            	    }

	            		vehicleABM.vehicleDelete(vehicleData, (String) vehicleForm.get("comment"), sys.getUserSessionData());
	            	}
	            }   
	            
	            return mapping.findForward("VehicleList");
	        }
	        	        
	        // EXCLUDE:
	        if (action.equals("exclude")) {
	            if (Util.empty((String) vehicleForm.get("exclusionDate"))
	                    || !Util.isNumber((String) vehicleForm.get("exclusionDate"))
	                    || Util.empty((String) vehicleForm.get("exclusionMonth"))
	                    || !Util.isNumber((String) vehicleForm.get("exclusionMonth"))
	                    || Util.empty((String) vehicleForm.get("exclusionYear"))
	                    || !Util.isNumber((String) vehicleForm.get("exclusionYear"))) {
	                session.setAttribute("Msg", messages.getText("driver.error.invalidexclusiondate"));	                
	                return mapping.findForward("exclude");
	            }
	            if (Util.empty((String) vehicleForm.get("comment"))) {
	                session.setAttribute("Msg", messages.getText("driver.error.invalidcomment"));
	                return mapping.findForward("exclude");
	            }
	            Date exclusionDate = Util.getDate((String) vehicleForm
	                    .get("exclusionDate"), (String) vehicleForm
	                    .get("exclusionMonth"), (String) vehicleForm
	                    .get("exclusionYear"));
	            vehicleABM.vehicleExclude(stringList.replaceAll("<", ","),exclusionDate, (String) vehicleForm.get("comment"),sys.getUserSessionData());
	        }
	        
	        // INCLUDE:
	        if (action.equals("include")) {
	            if (Util.empty((String) vehicleForm.get("comment"))) {
	                session.setAttribute("Msg", messages.getText("driver.error.invalidcomment"));
	                return mapping.findForward("exclude");
	            }
	            vehicleABM.vehicleInclude(stringList.replaceAll("<", ","),(String) vehicleForm.get("comment"),sys.getUserSessionData());
	        }

	        return mapping.findForward("VehicleList");		
	}
	

	public Collection stringToCollection(String stringList){
		String[] stringArray=stringList.split("<");
		Collection col=new LinkedList();
		
		for(int i=0; i < stringArray.length; i++){
			col.add(stringArray[i]);
		}
		
		return col;
	}
	
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("VehicleList");
    }	

}
