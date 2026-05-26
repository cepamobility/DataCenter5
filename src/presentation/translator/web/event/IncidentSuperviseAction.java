/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.event;

import java.rmi.RemoteException;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jboss.logging.Logger;

import persistence.log.LoggerFormatting;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.event.EventABMFacade;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.datatype.tables.ZoneDataType;
import communication.driver.tables.DriverData;
import communication.event.tables.EventDataType;
import communication.event.tables.EventMainAttributes;
import communication.exception.AlreadyExistsException;
import communication.exception.CommunicationProblemException;
import communication.exception.DCSecurityException;
import communication.exception.DeslogedException;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;

/**
 * @author jrodriguez
 */
public class IncidentSuperviseAction extends Action {
	/**
	 * Log this class
	 */
	private Logger logger=Logger.getLogger(IncidentSuperviseAction.class);
	private LoggerFormatting logFormatting=new LoggerFormatting();
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Text messages=null;

		HttpSession session = request.getSession();

		SystemAccess sys = (SystemAccess) session.getAttribute("SystemAccess");
		try {
				
			String target=(String)session.getAttribute("target");
			session.removeAttribute("target");

            EventForm createData = (EventForm)form;

            if(isCancelled(request)) {
                session.setAttribute("event",createData.getId());
			    return mapping.findForward("cancel");
			}
				
			if (sys == null) {
				session.setAttribute("Msg",Text.getMessage("generic.error.deslogued"));
				return mapping.findForward("logout");
			}

			messages=sys.getText();
				
            String message = createData.validate(sys.getUserSessionData());
            if(message != null) {
                session.setAttribute("EventForm",createData);
                session.setAttribute("Msg", messages.getText(message));
                return mapping.findForward("supervise");
            }
                
			CorporationData corporation = new CorporationData(sys.getUserSessionData().getFilter().getCorporationId(),null,null,null,null,null);
			DriverData driver = new DriverData(new Integer(createData.getDriverid().trim()));
                
            GeoLabelData country = Util.getGeoLabelData(createData);
            Collection list = Util.getDivOrgs(createData,sys.getUserSessionData());
            
            VehicleData vehicle = null;
            String plate = null;
            String trade = null;
            String model = null;
            Integer year = null;
            if(!Util.empty(createData.getVehicleId())) {
                vehicle = new VehicleData(new Integer(createData.getVehicleId().trim()));
            }else {
                plate = createData.getVehicleplate();
                trade = createData.getVehicletrade();
                model = createData.getVehiclemodel();
                year = new Integer(createData.getVehicleyear().trim());
            }

            boolean selfbelt = !Util.empty(createData.getSelfbelt()) && createData.getSelfbelt().toLowerCase().equals("on");
            boolean alcohol = !Util.empty(createData.getAlcohol()) && createData.getAlcohol().toLowerCase().equals("on");
            Integer cellular = new Integer(EventDataType.NO_CELLULAR); 
            if(!Util.empty(createData.getCellular()) && createData.getCellular().toLowerCase().equals("on")) {
                cellular = new Integer(EventDataType.NORMAL_CELLULAR);
            }
            Integer speed = new Integer(0);//createData.getSpeed().trim());
            boolean otherVehicles = !Util.empty(createData.getOthervehicles()) && createData.getOthervehicles().toLowerCase().equals("on");

            boolean damage = !Util.empty(createData.getDamage()) && createData.getDamage().toLowerCase().equals("on");
            Date date = Util.getDate(createData.getDate(),createData.getMonth(),createData.getYear());
            Time hour = Util.getTime(createData.getHour(),createData.getMinute());

            ZoneDataType zone = new ZoneDataType(createData.getZone());

            Integer collision = new Integer(0);//createData.getCollision().trim());
            boolean pedestrian = true;//!Util.empty(createData.getPedestrian()) && createData.getPedestrian().toLowerCase().equals("on");
            boolean animal = true;//!Util.empty(createData.getAnimal()) && createData.getAnimal().toLowerCase().equals("on");
            EventDataType event = new EventDataType();

            try {
                EventABMFacade eventABM = sys.getEventABMFacade();
                eventABM.eventUpdate(event,EventMainAttributes.PENDING_SUPERVISION,sys.getUserSessionData(),createData.getComment());
                return mapping.findForward("supervised");
			} catch (DeslogedException deslogued) {
				session.setAttribute("Msg",messages.getText("generic.error.delogued"));
				return mapping.findForward("exception");
			} catch (AlreadyExistsException already) {
				session.setAttribute("target",target);
				session.setAttribute("Msg", messages.getText("generic.error.deslogued"));
				return mapping.findForward("exception");
			}
            
		} catch (CommunicationProblemException comm) {
			request.setAttribute("exception",comm);
			return mapping.findForward("exception");
		} catch(DCSecurityException security){
			return mapping.findForward("insufficientpermissions");
		} catch(RemoteException remote){
			if(remote.getCause().toString().indexOf("SecurityException" )<=0){
				request.setAttribute("exception",remote);
				logger.fatal(logFormatting.formattingText(remote,"ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse","RemoteException",sys.getUserSessionData().getFilter().getLoginName()),remote);
				return mapping.findForward("exception");
			}else{
				logger.fatal(logFormatting.formattingText(remote,"ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse","SecurityException",sys.getUserSessionData().getFilter().getLoginName()),remote);
				return mapping.findForward("insufficientpermissions");
			}
		}catch (Exception e) {
			request.setAttribute("exception",e);
			logger.error(logFormatting.formattingText(e,"ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse","Exception",sys.getUserSessionData().getFilter().getLoginName()),e);
			return mapping.findForward("exception");
		}
	}
}
