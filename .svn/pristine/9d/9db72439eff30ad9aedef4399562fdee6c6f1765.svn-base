package presentation.translator.web.driver;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.administration.corporation.CorporationABMFacade;
import business.driver.DriverABMFacade;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverData;
import communication.language.Text;

public class LoadPointsAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Throwable {
        
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
        Text messages = sys.getText();

        DriverForm updateData = (DriverForm) form;
        Integer id = new Integer(updateData.getId());
        
        CorporationABMFacade corporationABM = sys.getCorporationABMFacade();
        
        DriverData driver = sys.getDriverReportFacade().getDriver(id, userSessionData);
        
        corporationABM.addDriver(driver, userSessionData);
        /*
        Collection events = sys.getEventReportFacade().findEventList(name, type, first, count, status, country, folioNumber, userSessionData)(id, userSessionData);
        whil
        
        String msg = updateData.validate(userSessionData);
        if (msg != null) {
            
            
		    if(userSessionData.getFilter().isCEPASystem()) {
		    	// CEPA System
		    	// session.setAttribute("Msg", messages.getText(msg));
	            // session.setAttribute("id", updateData.getId());	   
	            
	            userSessionData.setMsg(messages.getText(msg));
	            userSessionData.setUrl("/cepasystem/DriverEdit.jsp?id="+updateData.getId());
	            
			    // return mapping.findForward("editCepaSystem");
	            return mapping.findForward("layout");
		    }
		 
		    
		    String url = "/driver/DriverEdit.jsp?id="+updateData.getId();
            userSessionData.setUrl(url);
            userSessionData.setMsg(msg);
            
            return mapping.findForward("layout");
            
            
        } else {
        	
        	String idDallas = updateData.getIdDallas();
        	
        	// Si no es vacio iddallas no puede ser igual a uno ya existente
		    if (!Util.empty(idDallas) && sys.getCorporationId() != CorporationData.ALLERGAN  && sys.getCorporationId() != CorporationData.DPA && sys.getCorporationId() != CorporationData.BAYER
		    		 && sys.getCorporationId() != CorporationData.JJ && sys.getCorporationId() != CorporationData.DOW){
	    	   msg = DriverUtil.idDallasExist(sys, updateData, userSessionData);
	    	   if (msg != null){
	    		   String url = "/driver/DriverEdit.jsp?id="+updateData.getId();
	               userSessionData.setUrl(url);
	               userSessionData.setMsg(msg);
	               return mapping.findForward("layout");
	    	   }		    	   
		    }
        	
            DriverData driver = DriverUtil.createDriverData(userSessionData, updateData);
            DriverABMFacade driverABM = sys.getDriverABMFacade();
            if(sys.isCorporation(CorporationData.FORESTALES)) {
            	driverABM.updateForstalDrivers(driver, updateData.getComment(), userSessionData);
            }else {
            	driverABM.driverUpdate(driver, updateData.getComment(), userSessionData);
            }
            
                      
            session.removeAttribute("DriverCreateForm");
            
		    if(userSessionData.getFilter().isCEPASystem()) {
		    	// CEPA System
		    	//session.setAttribute("id", updateData.getId()); 
		    	userSessionData.setUrl("/cepasystem/DriverView.jsp?id="+updateData.getId());
			    // return mapping.findForward("viewCepaSystem");
		    	return mapping.findForward("layout");
		    }
		    
		    
            userSessionData.setUrl("/driver/DriverList.jsp");
        }*/
        return mapping.findForward("layout");
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        
		SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
	    UserSessionData userSessionData = sys.getUserSessionData();
    	        
	    if(((SystemAccess)session.getAttribute("SystemAccess")).getUserSessionData().getFilter().isCEPASystem()) {
	    	// CEPA System
	    	//session.setAttribute("id", ((DriverForm) form).getId());
	    	userSessionData.setUrl("/cepasystem/DriverView.jsp?id="+((DriverForm) form).getId());
		    // return mapping.findForward("viewCepaSystem");
	    	return mapping.findForward("layout");
	    }
	    
	    String url = "/driver/DriverView.jsp?id="+((DriverForm) form).getId();
        userSessionData.setUrl(url);
        return mapping.findForward("layout");
    }
}
