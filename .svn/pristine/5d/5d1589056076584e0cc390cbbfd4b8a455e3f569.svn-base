/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
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

import persistence.administration.corporation.tables.Corporation;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.fleet.FleetABMFacade;

import communication.administration.corporation.tables.CorporationData;
import communication.datatype.tables.TypeFuelDataType;
import communication.fleet.tables.FleetData;
import communication.fleet.tables.OdometerData;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;

/**
 * @author jrodriguez
 */
public class FleetOdometerAction extends AbstractAction {

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

        Iterator vehicles = sys.getFleetReportFacade().getVehiclesByFleet(new Integer(fleet),sys.getUserSessionData()).iterator();         
        Collection odometers = new LinkedList();
        while(vehicles.hasNext()) {
            Object[] vehicle = (Object[])vehicles.next();
            Integer vehicleId = (Integer)vehicle[0];
            String plate = (String)vehicle[1];

            String odometerValue = request.getParameter("odometer"+String.valueOf(vehicleId));
            String accidents = (sys.isCorporation(CorporationData.ALLERGAN)) ? "0" : request.getParameter("accidents"+String.valueOf(vehicleId));
            String incidents = (sys.isCorporation(CorporationData.ALLERGAN)) ? "0" : request.getParameter("incidents"+String.valueOf(vehicleId));
            String nearmiss = (sys.isCorporation(CorporationData.ALLERGAN)) ? "0" : request.getParameter("nearmiss"+String.valueOf(vehicleId));
            String fuel = (!sys.isCorporation(CorporationData.ALLERGAN)) ? "0" : request.getParameter("fuel"+String.valueOf(vehicleId));
            String fuelType = (!sys.isCorporation(CorporationData.ALLERGAN)) ? "" : request.getParameter("fueltype"+String.valueOf(vehicleId));
            String lastaccidents = (sys.isCorporation(CorporationData.ALLERGAN)) ? "0" : request.getParameter("lastaccidents"+String.valueOf(vehicleId));
            String lastincidents = (sys.isCorporation(CorporationData.ALLERGAN)) ? "0" : request.getParameter("lastincidents"+String.valueOf(vehicleId));
            String lastnearmiss = (!sys.isCorporation(CorporationData.MONSANTO)) ? "0" : request.getParameter("lastnearmiss"+String.valueOf(vehicleId));
            String lastfuel = (!sys.isCorporation(CorporationData.ALLERGAN)) ? "0" : request.getParameter("lastfuel"+String.valueOf(vehicleId));
            String lastfuelType = (!sys.isCorporation(CorporationData.ALLERGAN)) ? "0" : request.getParameter("lastfueltype"+String.valueOf(vehicleId));
            
            if(!Util.empty(odometerValue)
            		|| ((!Util.empty(accidents) || !Util.empty(incidents)) && !sys.isCorporation(CorporationData.ALLERGAN))
            		|| ((!Util.empty(fuel) || !Util.empty(fuelType)) && sys.isCorporation(CorporationData.ALLERGAN))) {
                if(!Util.isNumber(odometerValue) || Util.empty(odometerValue)) {
                    session.setAttribute("Msg",messages.getText("fleet.error.invalid.odometer")+" "+messages.getText("generic.data.vehicle")+" "+plate);
                    return mapping.findForward("back");
                }
                if(!Util.isNumber(accidents) || Util.empty(accidents)) {
                    session.setAttribute("Msg",messages.getText("fleet.error.invalid.accidents")+" "+messages.getText("generic.data.vehicle")+" "+plate);
                    return mapping.findForward("back");
                }
                if(!Util.isNumber(incidents) || Util.empty(incidents)) {
                    session.setAttribute("Msg",messages.getText("fleet.error.invalid.incidents")+" "+messages.getText("generic.data.vehicle")+" "+plate);
                    return mapping.findForward("back");
                }
                if(sys.getCorporationId() == CorporationData.MONSANTO) {
                    if(!Util.isNumber(nearmiss) || Util.empty(nearmiss)) {
                        session.setAttribute("Msg",messages.getText("fleet.error.invalid.nearmiss")+" "+messages.getText("generic.data.vehicle")+" "+plate);
                        return mapping.findForward("back");
                    }
                }
                if(sys.isCorporation(CorporationData.ALLERGAN)) {
                    if(Util.empty(fuelType)) {
                        session.setAttribute("Msg",messages.getText("fleet.error.invalid.fueltype")+" "+messages.getText("generic.data.vehicle")+" "+plate);
                        return mapping.findForward("back");
                    }
                    if(!Util.isNumber(fuel) || Util.empty(fuel)) {
                        session.setAttribute("Msg",messages.getText("fleet.error.invalid.fuel")+" "+messages.getText("generic.data.vehicle")+" "+plate);
                        return mapping.findForward("back");
                    }
                }
                if(!odometerValue.equals(request.getParameter("lastodometer"+String.valueOf(vehicleId)))
                        || !accidents.equals(lastaccidents) 
                        || !incidents.equals(lastincidents)
                        || (sys.isCorporation(CorporationData.MONSANTO) && !nearmiss.equals(lastnearmiss))
                        || (sys.isCorporation(CorporationData.ALLERGAN) && !fuel.equals(lastfuel))
                        || (sys.isCorporation(CorporationData.ALLERGAN) && !fuelType.equals(lastfuelType))) { 
                    OdometerData odometer = new OdometerData();
                    odometer.setFleet(new FleetData(new Integer(fleet)));
                    odometer.setVehicle(new VehicleData(new Integer(vehicleId)));
                    odometer.setPeriod(period);
                    odometer.setYear(new Integer(year));
                    odometer.setOdometer(new Integer(odometerValue.trim()));
                    odometer.setAccidents(new Integer(accidents.trim()));
                    odometer.setIncidents(new Integer(incidents.trim()));
                    if(sys.isCorporation(CorporationData.MONSANTO)) {
                        odometer.setNearmiss(new Integer(nearmiss));
                    }                    
                    if(sys.isCorporation(CorporationData.ALLERGAN)) {
                        odometer.setFuel(new Integer(fuel));
                        odometer.setFuelType(new TypeFuelDataType(fuelType));
                    }                    
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
