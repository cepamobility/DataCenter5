/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.vehicle;

import java.util.Collection;
import java.util.Date;
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

/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TrailerDeleteAction extends AbstractAction {
	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        
        DynaActionForm trailerForm = (DynaActionForm) form;
        String stringList = (String) trailerForm.get("checkboxParamList");
        String action = (String) trailerForm.get("action");
 
        VehicleABMFacade vehicleABM = sys.getVehicleABMFacade();
        Collection ids = Util.stringToCollection(stringList);

        if (action.equals("delete")) {
            vehicleABM.deleteTrailer(ids, sys.getUserSessionData());
            return mapping.findForward("list");
        }   
                        
        if (action.equals("exclude")) {
            if(!Util.checkDate((String) trailerForm.get("exclusionDate"),(String) trailerForm.get("exclusionMonth"),(String) trailerForm.get("exclusionYear"))) {
                 session.setAttribute("Msg", messages.getText("driver.error.invalidexclusiondate"));                 
                 return mapping.findForward("back");
            }
            Date exclusionDate = Util.getDate((String)trailerForm.get("exclusionDate"), (String)trailerForm.get("exclusionMonth"), (String)trailerForm.get("exclusionYear"));
            vehicleABM.trailerExclude(ids,exclusionDate,(String)trailerForm.get("comment"),sys.getUserSessionData());
         }
            
         if (action.equals("include")) {
             vehicleABM.trailerInclude(ids,(String) trailerForm.get("comment"),sys.getUserSessionData());
         }

         return mapping.findForward("list");      
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
        return mapping.findForward("list");
    }   

}
