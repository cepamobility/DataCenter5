package presentation.translator.web.driver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.driver.DriverABMFacade;
import business.points.PointManager;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverData;
import communication.language.Text;

/**
 * @author jrodriguez
 */
public class DriverCreateAction extends AbstractAction {

	/**
	 * 
	 */
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
        Text messages = sys.getText();

        if(sys.isForestalUnified()) {
			userSessionData.setMsg(messages.getText("driver.error.unifiedforestales"));
		    userSessionData.setUrl("/driver/DriverList.jsp");
		    return mapping.findForward("success");
        }else {
	        DriverForm createData = (DriverForm)form;
	
			String message = createData.validate(userSessionData);
			if(message != null) {
			    session.setAttribute("DriverCreateForm",createData);
			    
			    if(userSessionData.getFilter().isCEPASystem()) {
			    	session.setAttribute("Msg", messages.getText(message));
				    return mapping.findForward("createCepaSystem");
			    }
			    
			    userSessionData.setMsg(messages.getText(message));
			    userSessionData.setUrl("/driver/create.jsp");
			    return mapping.findForward("create");
			}else {
				
				// Si no es vacio iddallas no puede ser igual a uno ya existente
			    if (sys.isCorporation(CorporationData.JJ)){
			    	message = DriverUtil.idCorporationExist(sys, createData);
			    	if(message != null){
		    		   	userSessionData.setMsg(messages.getText(message));
		   		    	userSessionData.setUrl("/driver/create.jsp");
		   		    	return mapping.findForward("create");
			    	}		    	   
			    }
				
			    DriverData driver = DriverUtil.createDriverData(userSessionData,createData);
				DriverABMFacade driverABM = null;
				driverABM = sys.getDriverABMFacade();
			    if(sys.isCorporation(CorporationData.FORESTALES)) {
			    	Integer[] links = sys.getCorporationReportFacade().getTransporterLink(new Integer(createData.getDivOrg11()),sys.getUserSessionData());
			    	int count = 0;
			    	boolean[] replicate = new boolean[3];
			    	for(int i = 0; i < 3; i++) {
			    		if(replicate[i] = links[i] != null) {
			    			count++;
			    		}
			    	}
		    		if(count < 2) {
			            driverABM.driverCreate(driver,createData.getComment(),replicate,userSessionData);
		    		}else {
		    			driver.setDivOrgItem1(createData.getDivOrg11());
		    			userSessionData.setValue(driver);
		    			userSessionData.setMsg(createData.getComment());
		    		    userSessionData.setUrl("/driver/selectCorporations.jsp");
		    		    return mapping.findForward("success");
		    		}
			    }else {
		            driverABM.driverCreate(driver,createData.getComment(),userSessionData);
			    }
			    if(userSessionData.getFilter().isCEPASystem()) {
				    return mapping.findForward("successCepaSystem");
			    }
			    session.removeAttribute("activityview");
			    userSessionData.setUrl("/success.jsp?entity=driver.create");
			    return mapping.findForward("success");
	        }
        }
	}

	/**
	 * 
	 */
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
    	SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
    	
    	if(((SystemAccess)session.getAttribute("SystemAccess")).getUserSessionData().getFilter().isCEPASystem()) {
		    return mapping.findForward("DriverListCepaSystem");
	    }
	    
	    
	    userSessionData.setUrl("/driver/DriverList.jsp");
	    return mapping.findForward("DriverList");
    }
}
