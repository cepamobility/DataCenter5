/*
  * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.dpa;

import java.io.PrintWriter;
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

import communication.administration.corporation.tables.ItemData;
import communication.language.Text;
import communication.security.SecurityConstants;
import communication.util.Filter;

/**
 * @author fcarriquiry
 */
public class DPALevelOfServiceDriverAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

			
		 HttpSession session = request.getSession();
         SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
         Text messages = sys.getText();
         if(request.getParameter("getItems")!=null){
          	return getItems(mapping, form, request, response);
          }
         DPALevelOfServiceDriverForm formDPA=(DPALevelOfServiceDriverForm)form;
         if(formDPA.getId()==null || formDPA.getId()==0){
        	 sys.getCorporationABMFacade().createLevelOfServiceDriverDPA(formDPA.makeLevelOfServiceDriverDPAData(sys), sys.getUserSessionData());
         }else{
        	 sys.getCorporationABMFacade().updateLevelOfServiceDriverDPA(formDPA.makeLevelOfServiceDriverDPAData(sys), sys.getUserSessionData());
         }
         
         session.setAttribute("id", formDPA.getDriverId().toString());
	     return mapping.findForward("driver");
        	
	}
	
	public ActionForward getItems(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Integer index= Integer.valueOf(request.getParameter("index"));
        String level1=request.getParameter("level1");
        String level2=request.getParameter("level2");
        String salida="[";
        Iterator iter=sys.getUserSessionData().getFilter().getLevelItems(index, 0, level1, level2, Filter.USER);
        boolean first=true;
        while(iter.hasNext()){
        	if(!first){
        		salida+=",";
        	}
        	ItemData item=(ItemData)iter.next();
        	salida+="{\"id\":\""+item.getId()+"\",\"name\":\""+item.getName()+"\"}";
        		
        	first=false;
        }
        
        salida+="]";
        
        response.setContentType("application/json");
	    PrintWriter out = response.getWriter();
	    out.print(salida);
	    out.flush();
	       
        return null;
	}
	
	public ActionForward deleteAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		 HttpSession session = request.getSession();
         SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
         Text messages = sys.getText();
         
		String check = Util.checkPermission(sys,SecurityConstants.EDIT_VEHICLE);
		String check1 = Util.checkPermission(sys,SecurityConstants.MODIFY_VEHICLE);	
		String check2 = Util.checkPermission(sys,SecurityConstants.CHECKLIST_VEHICLE);	
		
		
		if (check!=null) { 
			response.sendRedirect(request.getContextPath()+check);	
		}else if (check1!=null) { 
			response.sendRedirect(request.getContextPath()+check1);	
		} else if (check2!=null) { 
			response.sendRedirect(request.getContextPath()+check2);	
		}else {
			//sys.getCorporationABMFacade().deleteCorrectiveActionDPA((String) request.getParameter("ids"), sys.getUserSessionData());
		}
		
		return null;
	}



	@Override
	public ActionForward cancel(HttpSession session, ActionMapping mapping,
			ActionForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	
    
}
