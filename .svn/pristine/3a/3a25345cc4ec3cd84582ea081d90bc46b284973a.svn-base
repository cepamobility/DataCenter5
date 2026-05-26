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
import business.SystemAccess;

import communication.administration.corporation.tables.ItemData;
import communication.language.Text;
import communication.util.Filter;


/**
 * @author fcarriquiry
 */
public class DPAExportImportLevelOfServiceDriverAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

			
		 HttpSession session = request.getSession();
         SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
         Text messages = sys.getText();
         DPAExportImportLevelOfServiceDriverForm formDPA=(DPAExportImportLevelOfServiceDriverForm)form;
         if(request.getParameter("getItems")!=null){
         	return getItems(mapping, form, request, response);
         }
         if(formDPA.getExportImport().equalsIgnoreCase("e")){
        	 response.setHeader("Content-Type", "application/vnd.ms-excel");
        	 response.setHeader("Content-Disposition", "inline; filename=\"DPALevelOfService"+(formDPA.getExportVehicleOrDriver().equalsIgnoreCase("V")?"Vehicle":"Driver")+".xls" + "\"");
        	 Integer divOrg1=formDPA.getDivOrg1()!=null && !formDPA.getDivOrg1().equalsIgnoreCase("0")?Integer.valueOf(formDPA.getDivOrg1()):null;
        	 Integer divOrg2=formDPA.getDivOrg2()!=null && !formDPA.getDivOrg2().equalsIgnoreCase("0")?Integer.valueOf(formDPA.getDivOrg2()):null;
        	 Integer divOrg3=formDPA.getDivOrg3()!=null && !formDPA.getDivOrg3().equalsIgnoreCase("0")?Integer.valueOf(formDPA.getDivOrg3()):null;
        	 String divOrgs="";
        	 if(divOrg3==null){
        		 //divOrgs=sys.getUserSessionData().getFilter().getDivOrgLeaves(0, divOrg2, divOrg1);
    		    Iterator iter=sys.getUserSessionData().getFilter().getLevelItems(3, 0, divOrg1==null?null:divOrg1.toString(), divOrg2==null?null:divOrg2.toString(), Filter.USER);
    	        while(iter.hasNext()){
    	        	if(!divOrgs.equals("")){
    	        		divOrgs+=",";
    	        	}
    	        	ItemData item=(ItemData)iter.next();
    	        	divOrgs+=item.getId();
    	        }
    	        
        	 }else{
        		 divOrgs=divOrg3.toString();
        	 }
        	 
        	 
        	 if(formDPA.getExportVehicleOrDriver().equalsIgnoreCase("V")){
        		 sys.getCorporationReportFacade().exportLevelOfServiceVehiclesDPA(sys.getUserSessionData(),sys.getText(), response.getOutputStream(),divOrgs,formDPA.makeDate());
        	 }else{
        		 sys.getCorporationReportFacade().exportLevelOfServiceDriversDPA(sys.getUserSessionData(),sys.getText(), response.getOutputStream(),divOrgs,formDPA.makeDate(),formDPA.getTelematicActive());
        	 }
        	 return null;
         }else if(formDPA.getExportImport().equalsIgnoreCase("i")){
        	 //De momento no esta implementada
         }
       
         
	     return null;
        	
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
	

	@Override
	public ActionForward cancel(HttpSession session, ActionMapping mapping,
			ActionForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	
    
}
