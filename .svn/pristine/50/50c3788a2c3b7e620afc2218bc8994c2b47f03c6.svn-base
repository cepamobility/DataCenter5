package presentation.translator.web.event;

import java.sql.Time;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.LabelItemData;
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
public class FineCreateAction extends AbstractAction {

   

	public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
    	
        return mapping.findForward("cancel");
    }

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
        
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        FineForm createData = (FineForm)form;
        String message = createData.validate(sys.getUserSessionData());
        if(message != null) {
            session.setAttribute("EventForm",createData);
            session.setAttribute("Msg", messages.getText(message));
            return mapping.findForward("fine");
        }
                     
        CorporationData corporation = new CorporationData(sys.getUserSessionData().getFilter().getCorporationId(),null,null,null,null,null);
        DriverData driver = new DriverData(new Integer(createData.getDriverid().trim()));
        
        //Traigo el Driver completo para mostrar los datos
        driver = sys.getDriverReportFacade().findDriverByPrimaryKey(new Integer(createData.getDriverid().trim()), sys.getUserSessionData());
        
        
        GeoLabelData country = Util.getGeoLabelData(createData);
        
        //DivOrgs
        Collection list = Util.getDivOrgs(createData,sys.getUserSessionData());
        
        	Iterator itDiv = list.iterator();
            DivOrgData di = (DivOrgData)itDiv.next();
            LabelItemData lid = (LabelItemData) di.getDivOrgLevels();
            String nom1 = lid.getName();
            String nom = di.getDivOrgAttribute().getName();
			//sys.getEventReportFacade().findEventByPrimaryKey(id, sys.getUserSessionData());
        		
        VehicleData vehicle = null;
        String plate = null;
        String trade = null;
        String model = null;
        String year = null;
        if(!Util.empty(createData.getVehicleId())) {
            vehicle = new VehicleData(new Integer(createData.getVehicleId().trim()));           
        // Traigo el Vehicle para mostrar los datos
        vehicle = sys.getVehicleReportFacade().findVehicleById(new Integer(createData.getVehicleId().trim()), sys.getUserSessionData());
            }else {
            plate = createData.getVehicleplate();
            trade = createData.getVehicletrade();
            model = createData.getVehiclemodel();
            year = createData.getVehicleyear().trim();
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
         
        
        FineDataType fine = new FineDataType(null, driver,new Integer(EventDataType.FINE),
                corporation,country,list,createData.getCity(),createData.getState(),vehicle,
                plate,trade,model,year,
                Util.getDate(createData.getDate(),createData.getMonth(),createData.getYear()),
                hour,new FineTypeDataType(createData.getFineType()),points,
                new FineQualifyDataType(createData.getQualify()),new Integer(createData.getCost()),
                createData.getArticle(),createData.getObservations(),createData.getWorking(),null,
                infringementNotice, lote, economicNumber,
                Util.getDate(createData.getExpiryDate(),createData.getExpiryMonth(),createData.getExpiryYear()),createData.getSpeed(),createData.getSpeedPermitted());
       
        //EventABMFacade eventABM = sys.getEventABMFacade();
        //eventABM.fineCreate(fine,sys.getUserSessionData());
        String state1 = fine.getState();
        System.out.println("state del fine es : " + state1);
        
        int state = EventMainAttributes.SUPERVISED;
        System.out.println("state es : " + state);
        
        int status = fine.getStatus();
        System.out.println("state es : " + status);
        session.setAttribute("Divs", list);
        session.setAttribute("FineForm",createData);
       
        session.setAttribute("FineDataType", fine);
        
        return mapping.findForward("confirmation");//"sucess"
                
    	}
    
    }
   
   