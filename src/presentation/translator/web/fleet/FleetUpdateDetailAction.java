package presentation.translator.web.fleet;

import java.util.Collection;
import java.util.Hashtable;
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

import communication.administration.corporation.tables.CorporationData;
import communication.fleet.tables.FleetDivisionData;
import communication.fleet.tables.FleetDivisionDetailData;
import communication.language.Text;

/**
 * @author jrodriguez
 */
public class FleetUpdateDetailAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

		try{
			
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

        Iterator divisions = sys.getFleetReportFacade().findFleetByPrimaryKey(new Integer(fleet),sys.getUserSessionData()).getDivisions().iterator();
        Collection details = new LinkedList();
        
        /* Para controlar excluidos */
        Hashtable hasDetails = sys.getFleetReportFacade().findFleetDetail(new Integer(fleet),period,
        		new Integer(year),sys.getUserSessionData());
        /* Fin para controlar excluidos */
        
        while(divisions.hasNext()) {
            FleetDivisionData division = (FleetDivisionData)divisions.next();
            
            if (division != null){
	            /* Para controlar excluidos */
	        	FleetDivisionDetailData hasDetail = ( hasDetails != null && hasDetails.containsKey(division.getId()) ) ? 
	        			(FleetDivisionDetailData) hasDetails.get(division.getId()) : null;
	        			
	   			if(!(hasDetail == null && division.getExclusionDate() != null)) 
	   			{
	        	
	                String vehicles = request.getParameter("vehicles"+String.valueOf(division.getId()));
	                boolean emptyVehicles = Util.empty(vehicles);
	                String miles = request.getParameter("miles"+String.valueOf(division.getId()));
	                boolean emptyMiles = Util.empty(miles);
	                String accidents = request.getParameter("accidents"+String.valueOf(division.getId()));
	                boolean emptyAccidents = Util.empty(accidents);
	                String incidents = request.getParameter("incidents"+String.valueOf(division.getId()));
	                boolean emptyIncidents = Util.empty(incidents);
	                String nearmiss = request.getParameter("nearmiss"+String.valueOf(division.getId()));
	                boolean emptyNearmiss = Util.empty(nearmiss);
	                
	                if(!((emptyVehicles && emptyMiles && emptyAccidents && emptyIncidents) || 
	                        (!emptyVehicles && !emptyMiles && !emptyAccidents && !emptyIncidents))) {
	                    session.setAttribute("Msg",messages.getText("fleet.error.empty.data"));
	                    return mapping.findForward("back");
	                }
	                if(!emptyVehicles && !Util.isNumber(vehicles)) {
	                    session.setAttribute("Msg",messages.getText("fleet.error.invalid.vehicles"));
	                    return mapping.findForward("back");
	                }                
	                if(!emptyMiles && !Util.isNumber(miles)) {
	                    session.setAttribute("Msg",messages.getText("fleet.error.invalid.miles"));
	                    return mapping.findForward("back");
	                }
	                if(!emptyAccidents && !Util.isNumber(accidents)) {
	                    session.setAttribute("Msg",messages.getText("fleet.error.invalid.accidents"));
	                    return mapping.findForward("back");
	                }
	                if(!emptyIncidents && !Util.isNumber(incidents)) {
	                    session.setAttribute("Msg",messages.getText("fleet.error.invalid.incidents"));
	                    return mapping.findForward("back");
	                }
	                if(sys.getCorporationId() == CorporationData.MONSANTO) {
		                if(!emptyNearmiss && !Util.isNumber(nearmiss)) {
		                    session.setAttribute("Msg",messages.getText("fleet.error.invalid.nearmiss"));
		                    return mapping.findForward("back");
		                }	                	
	                }
	                if (!emptyVehicles && !emptyMiles && !emptyAccidents && !emptyIncidents) {
	    	            FleetDivisionDetailData fleetData = new FleetDivisionDetailData();
	    	            
	    	            fleetData.setFleetDivision(division);
	    	            fleetData.setPeriod(period);
	    	            fleetData.setYear(new Integer(year.trim()));
	                    fleetData.setVehicles(new Integer(vehicles.trim()));
	                    fleetData.setMiles(new Integer(miles.trim()));
	                    fleetData.setAccidents(new Integer(accidents.trim()));
	                    fleetData.setIncidents(new Integer(incidents.trim()));
	                    if(sys.isCorporation(new int[]{CorporationData.MONSANTO,CorporationData.DPA})) {
		                	fleetData.setNearmiss(new Integer(nearmiss.trim()));
		                }
	    	            fleetData.setAPMM(new Double(0));
	    	            
	    	            details.add(fleetData);
	                }
	   			}
            }
        }
        

        
        FleetABMFacade fleetABM = sys.getFleetABMFacade();
        fleetABM.updateDetail(details,sys.getUserSessionData(),sys.getAlertABMFacade());
        
        return mapping.findForward("back");
        
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
}
