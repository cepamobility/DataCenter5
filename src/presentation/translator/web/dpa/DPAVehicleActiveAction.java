/*
  * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.dpa;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.language.Text;
import communication.security.SecurityConstants;
import communication.vehicle.tables.DPAGoalData;
import communication.vehicle.tables.DPAVehicleActiveData;

/**
 * @author fcarriquiry
 */
public class DPAVehicleActiveAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

			
		 HttpSession session = request.getSession();
         SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
         Text messages = sys.getText();
         if(request.getParameter("save")!=null){
        	 String yearStr=(String) request.getParameter("year");
        	 Integer year=Integer.valueOf(yearStr);
        	 ArrayList<DPAVehicleActiveData> vas=new ArrayList<DPAVehicleActiveData>();
        	 for(int j=1;j<8;j++){
	        	 for(int i=1;i<13 && j!=4;i++){
	        		 String valStr	=(String) request.getParameter("t2m"+i+"_"+j);
	        		 Integer val=Integer.valueOf(valStr);
	        		 String valRegStr=(String) request.getParameter("rt2m"+i+"_"+j);
	        		 
	        		 DPAVehicleActiveData data=new DPAVehicleActiveData();
	        		 data.setRegiao(valRegStr);
	        		 data.setValue(val);
	        		 data.setYear(year);
	        		 data.setMonth(i);
	        		 
	        		 if(request.getParameter("idt2m"+i+"_"+j)!=null){
	        			 Integer id=Integer.valueOf(request.getParameter("idt2m"+i+"_"+j));
	        			 data.setId(id);
	        		 }
	        		 vas.add(data);
	        		 
	        	 }
        	 }
        	 
        	 sys.getVehicleABMFacade().createOrUpdateDPAVehicleActive(vas, sys.getUserSessionData());
         }else if(request.getParameter("get")!=null){
        	 String year=(String)request.getParameter("year");
        	 StringBuilder res=new StringBuilder("{ \"values\": [  ");
        	 boolean coma=false;
        	 for(Object g1: sys.getVehicleReportFacade().getDPAVehicleActive(Integer.valueOf(year), sys.getUserSessionData())){
        		 DPAVehicleActiveData g=(DPAVehicleActiveData)g1;
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
	
	 

	@Override
	public ActionForward cancel(HttpSession session, ActionMapping mapping,
			ActionForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	
    
}
