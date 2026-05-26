package presentation.translator.web.fleet;

import java.util.Collection;
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
import business.fleet.FleetABMFacade;

import communication.datatype.tables.TypeFuelDataType;
import communication.fleet.tables.FleetData;
import communication.fleet.tables.OdometerData;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;

/**
 * @author jrodriguez
 */
public class OdometerFuelAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        DynaActionForm fleetForm = (DynaActionForm)form;
        
        String fleet = (String)fleetForm.get("fleet");
        if(Util.empty(fleet)) {
            session.setAttribute("Msg",messages.getText("fleet.error.empty.division"));
            return mapping.findForward("back");
        }
        Integer period = (Util.empty((String)fleetForm.get("period"))) ? null : new Integer((String)fleetForm.get("period"));
        if(period == null) {
            session.setAttribute("Msg",messages.getText("fleet.error.empty.period"));
            return mapping.findForward("back");
        }
        String year = (String)fleetForm.get("year");
        if(Util.empty(year)) {
            session.setAttribute("Msg",messages.getText("fleet.error.empty.year"));
            return mapping.findForward("back");
        }

        session.setAttribute("fleet",fleet);
        session.setAttribute("period",period);
        session.setAttribute("year",year);

        FleetData fleetData = sys.getFleetReportFacade().findFleetByPrimaryKey(new Integer(fleet),sys.getUserSessionData());
        Iterator vehicles = fleetData.getVehicles().iterator();
        Collection odometers = new LinkedList();
        while(vehicles.hasNext()) {
            VehicleData vehicle = (VehicleData)vehicles.next();

            String odometerValue = request.getParameter("odometer"+String.valueOf(vehicle.getId()));
            String accidents = request.getParameter("accidents"+String.valueOf(vehicle.getId()));
            String incidents = request.getParameter("incidents"+String.valueOf(vehicle.getId()));
            String fuel = request.getParameter("fuel"+String.valueOf(vehicle.getId()));
            String fuelType = request.getParameter("fueltype"+String.valueOf(vehicle.getId()));

            if(!(Util.empty(odometerValue) && Util.empty(accidents) && Util.empty(incidents) && Util.empty(fuel))) {
                if(!Util.isNumber(odometerValue) || Util.empty(odometerValue)) {
                    session.setAttribute("Msg",messages.getText("fleet.error.invalid.odometer")+" "+messages.getText("generic.data.vehicle")+" "+vehicle.getMatricula());
                    return mapping.findForward("back");
                }
                if(!Util.isNumber(accidents) || Util.empty(accidents)) {
                    session.setAttribute("Msg",messages.getText("fleet.error.invalid.accidents")+" "+messages.getText("generic.data.vehicle")+" "+vehicle.getMatricula());
                    return mapping.findForward("back");
                }
                if(!Util.isNumber(incidents) || Util.empty(incidents)) {
                    session.setAttribute("Msg",messages.getText("fleet.error.invalid.incidents")+" "+messages.getText("generic.data.vehicle")+" "+vehicle.getMatricula());
                    return mapping.findForward("back");
                }
                if(!Util.isNumber(fuel) || Util.empty(fuel)) {
                    session.setAttribute("Msg",messages.getText("fleet.error.invalid.fuel")+" "+messages.getText("generic.data.vehicle")+" "+vehicle.getMatricula());
                    return mapping.findForward("back");
                }                
                if(Util.empty(fuelType)) {
                    session.setAttribute("Msg",messages.getText("fleet.error.invalid.fueltype")+" "+messages.getText("generic.data.vehicle")+" "+vehicle.getMatricula());
                    return mapping.findForward("back");
                }
                if(!odometerValue.equals(request.getParameter("lastodometer"+String.valueOf(vehicle.getId())))
                        || !accidents.equals(request.getParameter("lastaccidents"+String.valueOf(vehicle.getId()))) 
                        || !incidents.equals(request.getParameter("lastincidents"+String.valueOf(vehicle.getId())))
                        || !fuel.equals(request.getParameter("lastfuel"+String.valueOf(vehicle.getId())))
                        || !fuelType.equals(request.getParameter("lastfueltype"+String.valueOf(vehicle.getId())))) { 

                    OdometerData odometer = new OdometerData();
                    odometer.setFleet(fleetData);
                    odometer.setVehicle(vehicle);
                    odometer.setPeriod(period);
                    odometer.setYear(new Integer(year));
                    odometer.setOdometer(new Integer(odometerValue));
                    odometer.setAccidents(new Integer(accidents));
                    odometer.setIncidents(new Integer(incidents));
                    odometer.setFuel(new Integer(fuel));
                    odometer.setFuelType(new TypeFuelDataType(fuelType));
                    odometers.add(odometer);
                }
            }
        }
        
        if(odometers.size() > 0) {
            FleetABMFacade fleetABM = sys.getFleetABMFacade();
            fleetABM.updateOdometer(odometers,sys.getUserSessionData());
        }
        return mapping.findForward("back");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("cancel");
    }
}
