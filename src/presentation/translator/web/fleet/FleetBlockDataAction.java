package presentation.translator.web.fleet;
import java.util.Calendar;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.fleet.FleetABMFacade;
import business.fleet.FleetReportFacade;

import communication.fleet.util.RegionBlockData;
import communication.language.Text;
public class FleetBlockDataAction  extends AbstractAction{
	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        DynaActionForm fleetBlockDataForm;
		FleetABMFacade fleetABMFacade;
		FleetReportFacade fleetReportFacade;
		Integer regionId;
		Collection fleets;
		Integer month, year;
		String regionName;
		String comment;
		RegionBlockData regionBlockData;
		
		fleetBlockDataForm = (DynaActionForm)form;
		
		regionId = Integer.valueOf(fleetBlockDataForm.getString("regionId"));
		regionName = sys.getUserSessionData().getFilter().getDivGeoItem(1,regionId).getName();
		month = new Integer(fleetBlockDataForm.getString("frommonth"));
		year = new Integer(fleetBlockDataForm.getString("fromyear"));
		comment = fleetBlockDataForm.getString("comment");
		
		// Find all Fleets that match this regionId
		// Precondition: All Fleet's Divisions must have this regionId otherwise
		// that fleet will not be blocked.
		fleetABMFacade = sys.getFleetABMFacade();
		fleetReportFacade = sys.getFleetReportFacade();
		fleets = fleetReportFacade.findFleetsByRegion(regionId,sys.getUserSessionData());
		
		// Creo el regionBlockData
		regionBlockData = new RegionBlockData();
		regionBlockData.setBlockMonth(month);
		regionBlockData.setBlockYear(year);
		regionBlockData.setCorporationId(sys.getUserSessionData().getFilter().getCorporationId());
		regionBlockData.setRegionId(regionId);
		regionBlockData.setUserId(sys.getUserSessionData().getFilter().getLoginName());
		regionBlockData.setComment(comment);
		regionBlockData.setRegionName(regionName);
		Calendar cal = Calendar.getInstance();
		regionBlockData.setDate(cal.getTime());
		
		// En este metodo se deberia validar las fechas, si son menores
		// a las que estan debe saltar un mensaje de confirmacion:
		// En principio no voy a hacer este control.
		fleetABMFacade.fleetsUpdateBlockPeriod(fleets,sys.getUserSessionData(), regionBlockData);
		
	   		
	
		session.setAttribute("Msg", messages.getText("generic.fleet.region")+ ": " + regionName +
				" - " + messages.getText("generic.fleet.blockedPeriod")
				+ ": "+ messages.getText("generic.month."+String.valueOf(month)) +
				" " + year.toString());
		return mapping.findForward("back");		
	}

	public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
		return null;
    }

}
