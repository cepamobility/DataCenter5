/*
  * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

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
import business.vehicle.VehicleABMFacade;

import communication.language.Text;
import communication.vehicle.report.CheckListImageData;
import communication.vehicle.tables.UnconformityData;
import communication.vehicle.tables.VehicleCheckData;

/**
 * @author fcarriquiry
 */
public class CopyAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

			HttpSession session = request.getSession();
	       
	        String img=(String)request.getParameter("contents");
	        if(img!=null && !img.contains("undefined")){
	        	String Id=(String)request.getParameter("id");
		        session.setAttribute("img", img);
		        System.out.println("Image:"+img);
		        if(session.getAttribute("imagesCheckList")==null){
		        	session.setAttribute("imagesCheckList",new HashMap<String,ArrayList<String>>());
		        }
		        HashMap<String,ArrayList<String>> h=(HashMap)session.getAttribute("imagesCheckList");
		        if(!h.containsKey(Id)){
		        	h.put(Id, new ArrayList<String>());
		        }
		        h.get(Id).add(img);
		        
	        }else if(request.getParameter("fileToUpload")!=null){
	        	String g="";
	        }
	        return mapping.findForward("success");
        	
	}



	@Override
	public ActionForward cancel(HttpSession session, ActionMapping mapping,
			ActionForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	
    
}
