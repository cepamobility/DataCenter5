package presentation.translator.web.driver;

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
import business.driver.DriverABMFacade;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverData;
import communication.language.Text;

/**
 * @author jrodriguez
 */
public class ReportJJDriverCreateAction extends AbstractAction {

	/**
	 * 
	 */
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
        Text messages = sys.getText();

        DriverForm createData = (DriverForm)form;

		String message = validate(createData);
		if(message != null) {
		    session.setAttribute("DriverCreateForm",createData);
		    
		    if(userSessionData.getFilter().isCEPASystem()) {
		    	session.setAttribute("Msg", messages.getText(message));
			    return mapping.findForward("createCepaSystem");
		    }
		    
		    userSessionData.setMsg(messages.getText(message));
		    userSessionData.setUrl("/driver/new.jsp");
		    return mapping.findForward("create");
		}else {
			
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
            driverABM.driverCreate(driver,createData.getComment(),userSessionData);
		    if(userSessionData.getFilter().isCEPASystem()) {
			    return mapping.findForward("successCepaSystem");
		    }
			
		    userSessionData.setUrl("/success.jsp?entity=driver.create");
		    return mapping.findForward("success");
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

    public String validate(DriverForm form) {

	    if(isEmpty(form.getFirstName())) {
	        return "generic.driver.driverdata.firstNameInvalid";
	    }
	    if(isEmpty(form.getLastName())) {
	        return "generic.driver.driverdata.lastNameInvalid";
	    }
        try{
            Date birth = form.getBirth();
             if(birth == null) {
                return "generic.driver.driverdata.birthDateInvalid";
            }
        }catch(Exception e) {
            return "generic.driver.driverdata.birthDateInvalid";    
        }
        try{
            int day = Integer.parseInt(form.getBirthDate());
            int month= Integer.parseInt(form.getBirthMonth());
            int year =Integer.parseInt(form.getBirthYear());
            boolean checkBirth = Util.checkDate(day,month,year);
            if(checkBirth == false) {
                return "generic.driver.driverdata.birthDateInvalid";
            }
        }catch(Exception e) {
            return "generic.driver.driverdata.birthDateInvalid";    
        }
            
	    if(isEmpty(form.getSex())) {
	        return "generic.driver.driverdata.sexInvalid";
	    }
        if(isEmpty(form.getCountry1())) {
            return "generic.driver.driverdata.emptyDivGeo";
        }
       if(!isEmpty(form.getEmail())) {
	        try{
	           
	           boolean checkEmail= Util.checkEmail(form.getEmail());
	           if(checkEmail == false){
	        	   return "generic.driver.driverdata.invalidEmail"; 
	           }
	          }catch(Exception e) {
	        	return "generic.driver.driverdata.invalidEmail";
	        }
      
        }
       
      
	    return null;
	}

    private boolean isEmpty(String value) {
	    return ((value == null) || (value.trim().length()==0));
	}
}
