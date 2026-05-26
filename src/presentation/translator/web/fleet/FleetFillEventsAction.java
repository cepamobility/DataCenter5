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

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.fleet.FleetABMFacade;
import business.fleet.FleetReportFacade;

import communication.administration.corporation.tables.CorporationData;
import communication.datatype.tables.TypeFuelDataType;
import communication.fleet.tables.FleetData;
import communication.fleet.tables.FleetDivisionData;
import communication.fleet.tables.OdometerData;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;

/**
 * @author jrodriguez
 */
public class FleetFillEventsAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
        
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        DynaActionForm fleetForm = (DynaActionForm)form;
        
        Integer fleet = new Integer((String)fleetForm.get("fleet"));
        Integer period = new Integer((String)fleetForm.get("period"));
        Integer year = new Integer((String)fleetForm.get("year"));

        FleetReportFacade fleetReport = sys.getFleetReportFacade();
        FleetData fleetData = fleetReport.findFleetByPrimaryKey(fleet,sys.getUserSessionData());
        Iterator it = fleetData.getDivisionsIterator();
        Collection values = new LinkedList();
        while(it.hasNext()) {
        	Integer divisionId = ((FleetDivisionData)it.next()).getId();
        	values.add(new Integer[]{divisionId,new Integer(request.getParameter("accident_"+divisionId)),new Integer(request.getParameter("incident_"+divisionId))});
        }

        sys.getFleetABMFacade().fillEvents(period,year,values,sys.getUserSessionData());      
        sys.getUserSessionData().setUrl("fleet/InsertColective.jsp?fleet="+fleet+"&period="+period+"&year="+year);
        return mapping.findForward("back");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("cancel");
    }
}
