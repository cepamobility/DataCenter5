package presentation.translator.web.event;

import java.sql.Time;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.event.EventABMFacade;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.datatype.tables.FineQualifyDataType;
import communication.datatype.tables.FineTypeDataType;
import communication.driver.tables.DriverData;
import communication.event.tables.EventDataType;
import communication.event.tables.EventMainAttributes;
import communication.event.tables.FineDataType;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;

/**
 * @author jrodriguez
 */
public class FineUpdateAction extends AbstractAction {

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        FineForm createData = (FineForm)form;
        session.setAttribute("fine",new Integer(createData.getId()));
        return mapping.findForward("view");
    }

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
        
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        FineForm createData = (FineForm)form;
        String message = createData.validate(sys.getUserSessionData());
        if(message != null) {
            session.setAttribute("FineForm",createData);
            session.setAttribute("Msg", messages.getText(message));
            return mapping.findForward("error");
        }
            
        CorporationData corporation = new CorporationData(sys.getUserSessionData().getFilter().getCorporationId(),null,null,null,null,null);
        DriverData driver = new DriverData(new Integer(createData.getDriverid().trim()));
            
        GeoLabelData country = Util.getGeoLabelData(createData);
        Collection list = Util.getDivOrgs(createData,sys.getUserSessionData());
        
        VehicleData vehicle = null;
        String plate = null;
        String trade = null;
        String model = null;
        String year = null;
        if(!Util.empty(createData.getVehicleId())) {
            vehicle = new VehicleData(new Integer(createData.getVehicleId().trim()));
        }else {
            plate = createData.getVehicleplate();
            trade = createData.getVehicletrade();
            model = createData.getVehiclemodel();
            year = createData.getVehicleyear().trim();
        }

        Integer status = new Integer(createData.getStatus());
        if(createData.getType().equals("supervise")) {
            status = new Integer(EventDataType.SUPERVISED);
        }
        Time hour = null;
        if(!Util.isChecked(createData.getNodatahour())) {
            hour = Util.getTime(createData.getHour(),createData.getMinute());
        }
        

        String infringementNotice = createData.getInfringementNotice().trim();
        
        Date lote = null;
        if (Util.isNumber(createData.getLoteMonth()) && 
        		Util.isNumber(createData.getLoteYear())){
        	Calendar c = Calendar.getInstance();
        	c.set(Calendar.DATE, 1);
        	c.set(Calendar.MONTH, (new Integer(createData.getLoteMonth())).intValue() - 1);
        	c.set(Calendar.YEAR, (new Integer(createData.getLoteYear())).intValue());
        	lote = c.getTime(); 
        }
        
        Integer points = 0;
        if (Util.isNumber(createData.getPoints())){
        	points = new Integer(createData.getPoints());
        }
        
        String economicNumber = createData.getVehicleeconomicnumber();
        
        FineDataType fine = new FineDataType(new Integer(createData.getId()),driver,
                status,corporation,country,list,createData.getCity(),
                createData.getState(),vehicle,plate,trade,model,year,
                Util.getDate(createData.getDate(),createData.getMonth(),createData.getYear()),
                hour,new FineTypeDataType(createData.getFineType()),points,
                new FineQualifyDataType(createData.getQualify()),new Integer(createData.getCost()),
                createData.getArticle(),createData.getObservations(),createData.getWorking(),
                createData.getSupervisionObservations(), infringementNotice, lote, economicNumber,
                Util.getDate(createData.getExpiryDate(),createData.getExpiryMonth(),createData.getExpiryYear()),createData.getSpeed(),createData.getSpeedPermitted());
        
        //va la jps pasando el fine dentro de la session esperando la conf sino volver a como estaba
        
        EventABMFacade eventABM = sys.getEventABMFacade();
        eventABM.fineUpdate(fine,sys.getUserSessionData());
        session.removeAttribute("FineForm");
        
        if(createData.getType().equals("supervise")) {
            String[] messageLinks = {
            		"layout.jsp?refer=/event/EventList.jsp?type=3&status="+EventMainAttributes.PENDING_SUPERVISION,//cambiar por supervision
            		"layout.jsp?refer=/event/FineView.jsp?fine="+createData.getId(),//cambiar por fineConfirmation
            		"layout.jsp?refer=/event/EventList.jsp"};
            session.setAttribute("links",messageLinks);
            return mapping.findForward("supervised");
        }else {
            session.setAttribute("fine",new Integer(createData.getId()));
            return mapping.findForward("view");
        }
    }
}


