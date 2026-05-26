package presentation.translator.web.fleet;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.fleet.tables.FleetData;
import communication.fleet.tables.OdometerData;
import communication.language.Text;
import communication.security.SecurityConstants;
import communication.util.ListResult;
import communication.vehicle.tables.VehicleData;

public class UploadOdometerConfirmAction extends AbstractAction {


	static final int EOF = 65535;
	private String errorMessage = null;
	private SystemAccess sys;
	
	/**
	 * 
	 */	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session = request.getSession();
        sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        
		    
		try {
		
			UploadOdometerForm uploadForm = (UploadOdometerForm) form;
        	String period = uploadForm.getPeriod();
        	String year = uploadForm.getYear();
        	String fleet = uploadForm.getFleet();
        	sys.getUserSessionData().setUrl("fleet/success.jsp");
        	
    		Hashtable odometers = (Hashtable)session.getAttribute("odometerData");
    		Enumeration vehicles = odometers.keys();
    		Collection newValues = new LinkedList();
    		while(vehicles.hasMoreElements()) {
    			String vehicle = (String)vehicles.nextElement();
    			String odometerValue = (String)odometers.get(vehicle);
    			int action = Integer.parseInt(request.getParameter("action"+vehicle));
    			if(action == 1) {
    				OdometerData odometerData = new OdometerData();
    				odometerData.setFleet(new FleetData(new Integer(fleet)));
    				odometerData.setVehicle(new VehicleData(new Integer(request.getParameter("id"+vehicle))));
    				odometerData.setPeriod(new Integer(period));
    				odometerData.setYear(new Integer(year));
    				odometerData.setOdometer(new Integer(odometerValue));
    				odometerData.setAccidents(new Integer(request.getParameter("incidents"+vehicle)));
    				odometerData.setIncidents(new Integer(request.getParameter("accidents"+vehicle)));
    				newValues.add(odometerData);
    			}
    		}
    		
    		sys.getFleetABMFacade().updateOdometer(newValues,sys.getUserSessionData());
    		
    		session.removeAttribute("fleet");
    		session.removeAttribute("period");
    		session.removeAttribute("year");
    		session.removeAttribute("odometerData");
    		session.removeAttribute("OdometerUploadForm");

    		sys.getUserSessionData().setUrl("success.jsp?entity=odometer.upload");
			return mapping.findForward("next");
		}
	    catch(Exception e) {
	    	session.setAttribute("Msg", messages.getText("generic.supportmaterial.cepatips.error"));
	    	e.printStackTrace();
	  	    return mapping.findForward("error");	
	    }
       
	}
	

	@Override
	public ActionForward cancel(HttpSession session, ActionMapping mapping, ActionForm form) {
        sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        session.removeAttribute("fleet");
		session.removeAttribute("period");
		session.removeAttribute("year");
		session.removeAttribute("odometerData");
		session.removeAttribute("OdometerUploadForm");

		sys.getUserSessionData().setUrl("fleet/uploadOdometers.jsp");
		return mapping.findForward("next");
	}
}

