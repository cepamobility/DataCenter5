/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.fleet;

import java.util.Hashtable;
import java.util.Iterator;

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
import business.fleet.FleetABMFacade;

import communication.fleet.tables.FleetData;
import communication.util.LogActions;
import communication.vehicle.tables.VehicleData;

/**
 * @author jrodriguez
 */
public class FleetDeleteVehicleAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        DynaActionForm fleetForm = (DynaActionForm)form;
        String stringList = (String)fleetForm.get("list");

        // TODO GUS: El commentario debe venir como parametro de la jsp.
        String comment = null;
        boolean putPlates = false;
        // Si el comentario es nulo pongo la lista de matriculas separadas
        // por comas como commentario del borrado de vehiculos.
        if (comment == null || comment == "")
        {
        	putPlates= true;       
        	comment = "";
        }
       
        FleetABMFacade fleetABM = sys.getFleetABMFacade();

        FleetData fleet = sys.getFleetReportFacade().findFleetByPrimaryKey(new Integer((String)fleetForm.get("fleet")),sys.getUserSessionData());
        Hashtable deletes = Util.stringToHash(stringList);
        Iterator it  = fleet.getVehicles().iterator();
        while(it.hasNext()) 
        {
        	VehicleData vehicleData = (VehicleData)it.next();
            if(deletes.containsKey(String.valueOf((vehicleData.getId()))))
            {
                it.remove();
                if (putPlates)
                {
                	comment += vehicleData.getMatricula()+ ", ";
                }
            }
           
        }
        fleetABM.fleetUpdate(fleet,sys.getUserSessionData(),comment,LogActions.DELETEVEHICLE);
        session.setAttribute("id",(String)fleetForm.get("fleet"));
        return mapping.findForward("back");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
}
