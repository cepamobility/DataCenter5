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

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.ItemData;
import communication.datatype.tables.AccidentClasificationDataType;
import communication.datatype.tables.CollisionClasificationDataType;
import communication.datatype.tables.FleetConsequenceDataType;
import communication.fleet.tables.FleetAccidentData;
import communication.fleet.tables.FleetData;
import communication.language.Text;
import communication.util.Filter;

/**
 * @author jrodriguez
 */
public class FleetAddAccidentAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        
        DynaActionForm fleetForm = (DynaActionForm)form;
        
        FleetAccidentData fleetData = new FleetAccidentData();
        
        String fleet = (String)fleetForm.get("fleet");
        if(Util.empty(fleet)) {
            session.setAttribute("Msg",messages.getText("fleet.error.empty.fleet"));
            return mapping.findForward("back");
        }else {
            fleetData.setFleet(new FleetData(new Integer(fleet)));
        }
        if(Util.empty((String)fleetForm.get("period"))) {
            session.setAttribute("Msg",messages.getText("fleet.error.empty.period"));
            return mapping.findForward("back");
        }else {
            fleetData.setPeriod(new Integer((String)fleetForm.get("period")));
        }
        String year = (String)fleetForm.get("year");
        if(Util.empty(year)) {
            session.setAttribute("Msg",messages.getText("fleet.error.empty.year"));
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
                session.setAttribute("Msg",messages.getText("fleet.error.empty.divorg"));
                return mapping.findForward("back");
            }
        }
        if(divOrgCount >= 2) {
            String divOrg2 = (String)fleetForm.get("divOrg2"+filter.getDivOrgLevel(1));
            if(!Util.empty(divOrg2)) {
                fleetData.setDivOrg2(new ItemData(new Integer(divOrg2)));
            }else {
                session.setAttribute("Msg",messages.getText("fleet.error.empty.divorg"));
                return mapping.findForward("back");
            }
        }
        if(divOrgCount >= 3) {
            String divOrg3 = (String)fleetForm.get("divOrg3"+filter.getDivOrgLevel(2));
            if(!Util.empty(divOrg3)) {
                fleetData.setDivOrg3(new ItemData(new Integer(divOrg3)));
            }else {
                session.setAttribute("Msg",messages.getText("fleet.error.empty.divorg"));
                return mapping.findForward("back");
            }
        }
        String country = (String)fleetForm.get("country");
        if(Util.empty(country)) {
            session.setAttribute("Msg",messages.getText("fleet.error.empty.country"));
            return mapping.findForward("back");
        }else {
            fleetData.setCountry(new GeoItemData(new Integer(country)));
        }
        
        // Clasificacion:
        String clasification = (String)fleetForm.get("clasification");
        if(Util.empty(clasification)) {
            session.setAttribute("Msg",messages.getText("fleet.error.empty.clasification"));
            return mapping.findForward("back");
        }else {
            fleetData.setClasification(new AccidentClasificationDataType(clasification));
        }
        
        if((sys.getUserSessionData().getFilter().getCorporationId().intValue() == CorporationData.JJ)||(sys.getUserSessionData().getFilter().getCorporationId().intValue() == CorporationData.ABBOTT)) {
            // Clasificacion de Colision:
            String colision = (String)fleetForm.get("colisionclasification");
            if (Util.empty(colision)){
            	session.setAttribute("Msg",messages.getText("generic.event.eventdata.colisionclasification"));
            	return mapping.findForward("back");
            }else{
            	fleetData.setCollisionClasification(new CollisionClasificationDataType(colision));
            }
        }
        
        String consequence = (String)fleetForm.get("consequence");
        if(Util.empty(consequence)) {
            session.setAttribute("Msg",messages.getText("fleet.error.empty.consequence"));
            return mapping.findForward("back");
        }else {
            fleetData.setConsequence(new FleetConsequenceDataType(consequence));
        }
        String number = (String)fleetForm.get("number");
        if(Util.empty(number)) {
            session.setAttribute("Msg",messages.getText("fleet.error.empty.number"));
            return mapping.findForward("back");
        }else {
        	if(!Util.isNumber(number)) {
                session.setAttribute("Msg","fleet.error.invalid.accidents");
                return mapping.findForward("back");
       	 	}else{
       	 		try{
       	 			fleetData.setNumber(new Integer(number));
       	 		}catch(NumberFormatException e){
        			session.setAttribute("Msg","fleet.error.invalid.accidents");
                	return mapping.findForward("back");
        		}
        	}
        }
        String cost = (String)fleetForm.get("cost");
        if(Util.empty(cost)) {
            session.setAttribute("Msg",messages.getText("fleet.error.empty.cost"));
            return mapping.findForward("back");
        }else {
        	if(!Util.isNumber(number)) {
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
        fleetABM.addAccident(fleetData,sys.getUserSessionData());
        session.removeAttribute("FleetAddAccidentForm");
        return mapping.findForward("back");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
}
