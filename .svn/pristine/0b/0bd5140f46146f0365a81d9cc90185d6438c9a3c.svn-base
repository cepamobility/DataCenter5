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

import business.SystemAccess;
import business.event.EventABMFacade;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.datatype.tables.FineQualifyDataType;
import communication.datatype.tables.FineTypeDataType;
import communication.driver.tables.DriverData;
import communication.event.tables.EventDataType;
import communication.event.tables.FineDataType;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;

public class FineConfirmationAction extends AbstractAction{
	
	public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
		
		session.removeAttribute("FineDataType");					
		session.removeAttribute("FineForm");
        
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
	       
	      //Guarda el Fine si confirma
	        
	        //eventABM.fineCreate(fine,sys.getUserSessionData());
	        EventABMFacade eventABM = sys.getEventABMFacade();
	        FineDataType data = (FineDataType)session.getAttribute("FineDataType");
	        eventABM.fineCreate(data,sys.getUserSessionData());
	       
	        session.removeAttribute("FineDataType");					
			session.removeAttribute("FineForm");
	        
			return mapping.findForward("success");
	       
	    }
	   
}
