package presentation.translator.web.fleet;

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

import communication.administration.RoleData;
import communication.datatype.tables.TypeFuelDataType;
import communication.fleet.tables.OdometerData;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;

/**
 * @author jrodriguez
 */
public class FleetOwnOdometerAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        DynaActionForm fleetForm = (DynaActionForm)form;
        
        Integer vehicle = new Integer((String)fleetForm.get("vehicle"));
        Integer period = (Util.empty((String)fleetForm.get("period"))) ? null : 
        	new Integer((String)fleetForm.get("period"));
        String year = (String)fleetForm.get("year");

        session.setAttribute("vehicle",String.valueOf(vehicle));
        session.setAttribute("period",String.valueOf(period));
        session.setAttribute("year",year);

        OdometerData odometer = new OdometerData();
        odometer.setVehicle(new VehicleData(vehicle));
        odometer.setPeriod(period);
        odometer.setYear(new Integer(year));

        String country = (sys.getUserSessionData().getRole().getName().equals(RoleData.FLEET_DRIVER)) ? "Fleet" : ""; 
            
        String odometerValue = request.getParameter("odometer");
        String lastOdometerValue = request.getParameter("lastodometer");
        if(Util.empty(odometerValue) || !Util.isNumber(odometerValue)) {
            session.setAttribute("Msg",messages.getText("fleet.error.invalid.odometer"));
            return mapping.findForward("back"+country);
        }else {
            odometer.setOdometer(new Integer(odometerValue.trim()));
        }
            
        String accidents = request.getParameter("accidents");
        String lastAccidents = request.getParameter("lastaccidents");
        if(Util.empty(accidents) || !Util.isNumber(accidents)) {
            session.setAttribute("Msg",messages.getText("fleet.error.invalid.accidents"));
            return mapping.findForward("back"+country);
        }else {
            odometer.setAccidents(new Integer(accidents.trim()));
        }

        String incidents = request.getParameter("incidents");
        String lastIncidents = request.getParameter("lastincidents");
        if(Util.empty(incidents) || !Util.isNumber(incidents)) {
            session.setAttribute("Msg",messages.getText("fleet.error.invalid.incidents"));
            return mapping.findForward("back"+country);
        }else {
            odometer.setIncidents(new Integer(incidents.trim()));
        }

        String fuelType = request.getParameter("fuelType");
        if(!Util.empty(fuelType)) {
        	odometer.setFuelType(new TypeFuelDataType(fuelType));
        }

        String fuel = request.getParameter("fuel");
        if(Util.isNumber(fuel)) {
        	odometer.setFuel(new Integer(fuel.trim()));
        }

        if(!odometerValue.equals(lastOdometerValue) || !accidents.equals(lastAccidents) || !incidents.equals(lastIncidents)) {
            FleetABMFacade fleetABM = sys.getFleetABMFacade();
            fleetABM.insertOwnOdometer(odometer,sys.getUserSessionData());
        }
        
        session.setAttribute("Msg",messages.getText("fleet.odometer.success"));
        return mapping.findForward("success"+country);
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        String country = (sys.getUserSessionData().getRole().getName().equals(RoleData.FLEET_DRIVER)) ? "Fleet" : ""; 
        return mapping.findForward("cancel"+country);
    }
}
