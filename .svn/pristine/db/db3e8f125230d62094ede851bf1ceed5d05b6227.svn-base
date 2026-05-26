/*
  * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.dpa;

import java.io.PrintWriter;
import java.util.ArrayList;
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
import communication.security.SecurityConstants;
import communication.vehicle.report.CheckListImageData;
import communication.vehicle.tables.DPAGoalData;
import communication.vehicle.tables.UnconformityData;
import communication.vehicle.tables.VehicleCheckData;

/**
 * @author fcarriquiry
 */
public class DPAGoalsAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

			
		 HttpSession session = request.getSession();
         SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
         Text messages = sys.getText();
         if(request.getParameter("save")!=null){
        	 String yearStr=(String) request.getParameter("year");
        	 Integer year=Integer.valueOf(yearStr);
        	 ArrayList<DPAGoalData> goals=new ArrayList<DPAGoalData>();
        	 for(int j=1;j<8;j++){
	        	 for(int i=1;i<13 && j!=4;i++){
	        		 String valStr	=(String) request.getParameter("t2m"+i+"_"+j);
	        		 Integer val=Integer.valueOf(valStr);
	        		 String valRegStr=(String) request.getParameter("rt2m"+i+"_"+j);
	        		 
	        		 DPAGoalData data=new DPAGoalData();
	        		 data.setRegiao(valRegStr);
	        		 data.setValue(val);
	        		 data.setYear(year);
	        		 data.setMonth(i);
	        		 
	        		 if(request.getParameter("idt2m"+i+"_"+j)!=null){
	        			 Integer id=Integer.valueOf(request.getParameter("idt2m"+i+"_"+j));
	        			 data.setId(id);
	        		 }
	        		 goals.add(data);
	        		 
	        	 }
        	 }
        	 
        	 sys.getVehicleABMFacade().createOrUpdateDPAGoals(goals, sys.getUserSessionData());
         }else if(request.getParameter("get")!=null){
        	 String year=(String)request.getParameter("year");
        	 StringBuilder res=new StringBuilder("{ \"values\": [  ");
        	 boolean coma=false;
        	 for(Object g1: sys.getVehicleReportFacade().getDPAGoals(Integer.valueOf(year), sys.getUserSessionData())){
        		 DPAGoalData g=(DPAGoalData)g1;
        		 if(coma) res.append(" , ");
        		 res.append("{ \"id\":\""+g.getId()+"\",\"reg\":\""+g.getRegiao()+"\",\"month\":\""+g.getMonth()+"\",\"value\":\""+g.getValue()+"\" } ");
        		 coma=true;
        	 }
        	 		 
        	 res.append("  ]  }");
        	 PrintWriter writer=new PrintWriter(response.getOutputStream());
        	 writer.write(res.toString());
        	 writer.close();
        	 
        	 coma=true;
         }
         
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
			sys.getCorporationABMFacade().deleteCorrectiveActionDPA((String) request.getParameter("ids"), sys.getUserSessionData());
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
