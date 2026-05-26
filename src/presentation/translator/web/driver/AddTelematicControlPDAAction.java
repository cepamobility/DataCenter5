/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.driver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.driver.DriverABMFacade;

import communication.alert.AlertConstants;
import communication.driver.tables.DriverData;
import communication.driver.tables.TelematicControlData;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;

/**
 * @author jrodriguez
 */
public class AddTelematicControlPDAAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        DynaActionForm telematicControlData = (DynaActionForm)form;
        boolean isDriver=telematicControlData.getString("driver")!=null && telematicControlData.getString("driver").trim().length()>0;
        Integer driverId = isDriver?new Integer(telematicControlData.getString("driver")):null;
        
		String message = validate(telematicControlData);
		if(message != null) {
		    session.setAttribute("Msg", messages.getText(message));
            String url = "/driver/telematicContrPDA.jsp";
            if(!Util.empty(telematicControlData.getString("id"))) {
            	url += "?id="+telematicControlData.getString("id");
            }else {
            	if(isDriver){
            		url += "?driver="+telematicControlData.getString("driver");	
            	}else {
            		url = "/vehicle/telematicContrVehiclePDA.jsp?vehicle="+telematicControlData.getString("vehicle");
            	}	            	
            }
            
            session.setAttribute("TelematicControlForm", telematicControlData); // recargo lo que ya haya puesto
            sys.getUserSessionData().setUrl(url);
            
            return mapping.findForward("next");
		}else {
			session.removeAttribute("TelematicControlForm");
			
            TelematicControlData data = new TelematicControlData();
            
            int type = Integer.parseInt(telematicControlData.getString("type"));
        	data.setType(new Integer(type));
    		data.setDate(Util.getDate(telematicControlData.getString("date"),telematicControlData.getString("month"),telematicControlData.getString("year")));
    		data.setTime(Util.getTimeString(telematicControlData.getString("timeHour"),telematicControlData.getString("timeMin"),telematicControlData.getString("timeSec")));
    		data.setDuration(Util.getTimeString(telematicControlData.getString("durationHour"),telematicControlData.getString("durationMin"),telematicControlData.getString("durationSec")));
            data.setRecomendation(new Integer(0));
            FormFile file=(FormFile)telematicControlData.get("file");
            if(file!=null && file.getFileSize()>0){
            	data.setFileName(file.getFileName());
            	data.setFileInputStream(file.getInputStream());
            }
            data.setGarage(telematicControlData.getString("garage"));
            /**
             *CHEQUEO SI ES VERDE, AMARILLO Y ROJO EL COLOR DEL CONTROL PARA SABER QUE DATOS ALMACENO
             */
            String speed = telematicControlData.getString("speed");
            String occurrences = telematicControlData.getString("occurrences");
            String limitExceededRain = telematicControlData.getString("limitExceededRain");
            switch(type) {
            	case TelematicControlData.TYPE_EXCESSIVE_SPEED:
            		//Lluvia 
            		 data.setLimitExceededRain((!Util.empty(limitExceededRain) && limitExceededRain.equals("on")) ? 1 : 0);
            	case TelematicControlData.TYPE_HARD_START:
            	case TelematicControlData.TYPE_HARD_STOP:
    	            data.setSpeed((!Util.empty(speed) && Util.isNumber(speed)) ? new Integer(speed) : 0);
    	            data.setOccurrences((!Util.empty(occurrences) && Util.isNumber(occurrences)) ? new Integer(occurrences) : 0);
    	            break;
            	case TelematicControlData.TYPE_WORK_HOURS:
            		data.setTotalDuration(Util.getTimeString(telematicControlData.getString("totalDurationHour"),telematicControlData.getString("totalDurationMin"),telematicControlData.getString("totalDurationSec")));
    	            break;
            	case TelematicControlData.TYPE_FREE_TIME:
            		data.setTotalDuration(Util.getTimeString(telematicControlData.getString("totalDurationHour"),telematicControlData.getString("totalDurationMin"),telematicControlData.getString("totalDurationSec")));
    	            data.setLimitExceededRain((!Util.empty(limitExceededRain) && Util.isNumber(limitExceededRain)) ? new Integer(limitExceededRain) : 0);
    	            data.setOccurrences((!Util.empty(occurrences) && Util.isNumber(occurrences)) ? new Integer(occurrences) : 0);
    	            break;
            	case TelematicControlData.TYPE_INSERTICIAL_REPORT:
            		data.setTotalDuration(Util.getTimeString(telematicControlData.getString("totalDurationHour"),telematicControlData.getString("totalDurationMin"),telematicControlData.getString("totalDurationSec")));
    	            data.setOccurrences((!Util.empty(occurrences) && Util.isNumber(occurrences)) ? new Integer(occurrences) : 0);
    	            break;
            	case TelematicControlData.TYPE_24HOOUR_WITHOUT_REPORT:
            		data.setDate2(Util.getDate(telematicControlData.getString("date2"),telematicControlData.getString("month2"),telematicControlData.getString("year2")));
    	            data.setOccurrences((!Util.empty(occurrences) && Util.isNumber(occurrences)) ? new Integer(occurrences) : 0);
    	            break;
            	case TelematicControlData.TYPE_WEEK_WORK:
            		data.setDate2(Util.getDate(telematicControlData.getString("date2"),telematicControlData.getString("month2"),telematicControlData.getString("year2")));
            		data.setDate3(Util.getDate(telematicControlData.getString("date3"),telematicControlData.getString("month3"),telematicControlData.getString("year3")));
    	            data.setLimitExceededRain((!Util.empty(limitExceededRain) && Util.isNumber(limitExceededRain)) ? new Integer(limitExceededRain) : 0);
    	            data.setOccurrences((!Util.empty(occurrences) && Util.isNumber(occurrences)) ? new Integer(occurrences) : 0);
    	            break;
            }
	            
            data.setDriver(new DriverData(driverId));
            if(telematicControlData.getMap().containsKey("vehicle") && telematicControlData.getString("vehicle").trim().length()>0){
            	data.setVehicle(new VehicleData(new Integer (telematicControlData.getString("vehicle"))));
            }
            data.setPoint(telematicControlData.getString("point"));
            data.setStatus(telematicControlData.getString("status"));
            data.setStatusComment(telematicControlData.getString("statusComment"));
            
            DriverABMFacade driverABM = sys.getDriverABMFacade();            
            if(Util.empty(telematicControlData.getString("id"))) {
            	driverABM.addTelematicControl(data,sys.getUserSessionData());
            	
            }else {
            	data.setId(new Integer(telematicControlData.getString("id")));
            	driverABM.updateTelematicControl(data,sys.getUserSessionData());
            }
            if(isDriver){
            	sys.getUserSessionData().setUrl("/driver/DriverView.jsp?id="+driverId);
            }else {
            	sys.getUserSessionData().setUrl("/vehicle/VehicleView.jsp?id="+telematicControlData.getString("vehicle"));
            }
            session.removeAttribute("TelematicControlForm");
			return mapping.findForward("next");
        }	
	}

    private String validate(DynaActionForm controlData) {
        
    	//fecha
        Date date = Util.getDate(controlData.getString("date"),controlData.getString("month"),controlData.getString("year"));
        if(date == null) {
        	return "driver.telematiccontrol.emptydate";
        }

        return null;
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        DynaActionForm telematicControlData = (DynaActionForm)form;
        boolean isDriver=telematicControlData.getString("driver")!=null && telematicControlData.getString("driver").trim().length()>0;
        session.removeAttribute("TelematicControlForm");
        if(isDriver){
        	sys.getUserSessionData().setUrl("/driver/DriverView.jsp?id="+telematicControlData.getString("driver"));
        }else{
        	sys.getUserSessionData().setUrl("/vehicle/VehiceView.jsp?id="+telematicControlData.getString("vehicle"));
        }		
        return mapping.findForward("next");
    }
}
