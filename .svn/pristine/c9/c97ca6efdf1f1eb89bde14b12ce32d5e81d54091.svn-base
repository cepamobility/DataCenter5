/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
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

import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.ItemData;
import communication.datatype.tables.IncidentClasificationDataType;
import communication.fleet.tables.FleetData;
import communication.fleet.tables.FleetIncidentData;
import communication.util.Filter;

/**
 * @author jrodriguez
 */
public class FleetAddIncidentAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        DynaActionForm fleetForm = (DynaActionForm)form;
        
        FleetIncidentData fleetData = new FleetIncidentData();
        
        String fleet = (String)fleetForm.get("fleet");
        if(Util.empty(fleet)) {
            session.setAttribute("Msg","fleet.error.empty.fleet");
            return mapping.findForward("back");
        }else {
            fleetData.setFleet(new FleetData(new Integer(fleet)));
        }
        if(Util.empty((String)fleetForm.get("period"))) {
            session.setAttribute("Msg","fleet.error.empty.period");
            return mapping.findForward("back");
        }else {
            fleetData.setPeriod(new Integer((String)fleetForm.get("period")));
        }
        String year = (String)fleetForm.get("year");
        if(Util.empty(year)) {
            session.setAttribute("Msg","fleet.error.empty.year");
            return mapping.findForward("back");
        }else {
            fleetData.setYear(new Integer(year));
        }
        session.setAttribute("fleet",fleet);
        session.setAttribute("period",fleetForm.get("period"));
        session.setAttribute("year",year);
        Filter filter = sys.getUserSessionData().getFilter();
        int divOrgCount = filter.getDivOrgCount(); 
        if(divOrgCount >= 1) {
            String divOrg1 = (String)fleetForm.get("divOrg1"+filter.getDivOrgLevel(0));
            if(!Util.empty(divOrg1)) {
                fleetData.setDivOrg1(new ItemData(new Integer(divOrg1)));
            }else {
                session.setAttribute("Msg","fleet.error.empty.divorg");
                return mapping.findForward("back");
            }
        }
        if(divOrgCount >= 2) {
            String divOrg2 = (String)fleetForm.get("divOrg2"+filter.getDivOrgLevel(1));
            if(!Util.empty(divOrg2)) {
                fleetData.setDivOrg2(new ItemData(new Integer(divOrg2)));
            }else {
                session.setAttribute("Msg","fleet.error.empty.divorg");
                return mapping.findForward("back");
            }
        }
        if(divOrgCount >= 3) {
            String divOrg3 = (String)fleetForm.get("divOrg3"+filter.getDivOrgLevel(2));
            if(!Util.empty(divOrg3)) {
                fleetData.setDivOrg3(new ItemData(new Integer(divOrg3)));
            }else {
                session.setAttribute("Msg","fleet.error.empty.divorg");
                return mapping.findForward("back");
            }
        }
        String country = (String)fleetForm.get("country");
        if(Util.empty(country)) {
            session.setAttribute("Msg","fleet.error.empty.country");
            return mapping.findForward("back");
        }else {
            fleetData.setCountry(new GeoItemData(new Integer(country)));
        }
        String clasification = (String)fleetForm.get("clasification");
        if(Util.empty(clasification)) {
            session.setAttribute("Msg","fleet.error.empty.clasification");
            return mapping.findForward("back");
        }else {
            fleetData.setClasification(new IncidentClasificationDataType(clasification));
        }
        String number = (String)fleetForm.get("number");
        if(Util.empty(number)) {
            session.setAttribute("Msg","fleet.error.empty.number");
            return mapping.findForward("back");
        }else {
        	if(!Util.isNumber(number)) {
                session.setAttribute("Msg","fleet.error.invalid.incidents");
                return mapping.findForward("back");
       	 	}else{
       	 		try{
       	 			fleetData.setNumber(new Integer(number));
       	 		}catch(NumberFormatException e){
       	 			session.setAttribute("Msg","fleet.error.invalid.incidents");
       	 			return mapping.findForward("back");
       	 		}
        	}
        }
        String cost = (String)fleetForm.get("cost");
        if(Util.empty(cost)) {
            session.setAttribute("Msg","fleet.error.empty.cost");
            return mapping.findForward("back");
        }else {
        	 if(!Util.isNumber(cost)) {
                 session.setAttribute("Msg","fleet.error.invalid.costs");
                 return mapping.findForward("back");
        	 }else{
        		 try{
        			 fleetData.setCost(new Integer(cost));
        		 }catch(NumberFormatException e){
        			 session.setAttribute("Msg","fleet.error.invalid.costs");
                     return mapping.findForward("back");
        		 }
        	}
        }

        FleetABMFacade fleetABM = sys.getFleetABMFacade();
        fleetABM.addIncident(fleetData,sys.getUserSessionData());
        session.removeAttribute("FleetAddIncidentForm");
		return mapping.findForward("back");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
}
