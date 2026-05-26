/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.vehicle;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.vehicle.ReportCimsaCheckListFactory;
import presentation.report.vehicle.ReportSyngentaCheckListFactory;
import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.activity.tables.CheckListData;
import communication.activity.tables.ItemData;
import communication.activity.tables.PointData;
import communication.language.Text;
import communication.util.Filter;
import communication.util.ListResult;
import communication.vehicle.report.ReportCimsaCheckListData;
import communication.vehicle.report.ReportSyngentaCheckListData;
import communication.vehicle.tables.VehicleCheckData;


/**
 * @author adocarmo
 *
 */
public class ReportSyngentaCheckListAction extends AbstractAction {

	
    public ActionForward action(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response) throws Exception {

    	try{
	        HttpSession session = request.getSession();
	        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
	        Text messages = sys.getText();
	
	        DynaActionForm reportForm = (DynaActionForm)form;
	        
			VehicleCheckData vehicleCheck = sys.getVehicleReportFacade().findVehicleCheck(new Integer(reportForm.getString("check")),sys.getUserSessionData());

			
	        
	        // Inicio el JFreeReport:
	        try{
	        	JFreeReportBoot.getInstance().start();
	        }catch(Exception e){
	        	// La primera vez que se corre puede dar error de cache de Pentahoo.
	        	// asi que lo corremos de nuevo...jeje
	        	JFreeReportBoot.getInstance().start();
	        }
	        
	        
	        session.removeAttribute("ReportCheckListForm");   
	        
	        JFreeReport report = getReport(sys,reportForm,vehicleCheck);
	        // Muestro el reporte:
	        return new ReportUtil().executeReport(mapping,response, report,"PDF",session,"CheckList",true);	  
    	}catch(Exception e){
    		e.printStackTrace();
    		throw e;
    	}
    }
 
    
    public JFreeReport getReport(SystemAccess sys,DynaActionForm reportForm, VehicleCheckData vehicleCheck) throws Exception {

       	try{
	        Text messages = sys.getText();
        	CheckListData checkList = sys.getVehicleReportFacade().getCheckList(CheckListData.SYNGENTA_CHECKLIST,sys.getUserSessionData());
    		Iterator points = checkList.getSortPoints();
    		Collection itemList = new LinkedList();
    		int x = 0;
    		int y = 0;
			String[][] pneusValues = new String[4][2]; 
			String[][] pneusTexts = new String[4][2]; 
    		while(points.hasNext()) {
    			PointData point = (PointData)points.next();
    			Iterator items = point.getSortItems();
    			while(items.hasNext()) {
    				ItemData item = (ItemData)items.next();
    				if(item.getType().intValue() == 1) {
        				int value = -1;
        				String pointComment = null;
        				if(vehicleCheck != null) {
        					String checkValue = vehicleCheck.getValueFromItem(item.getId()).trim();
        					value = (checkValue == null) ? -1 : Integer.parseInt(checkValue);
        				}
	    				if(item.getType().intValue() == 1) {
	    					pointComment = vehicleCheck.getValueFromCommentPoint(point.getId()).trim();
	    				}
	    				String[] s = new String[3];
	    				s[0] = messages.getText(item.getText());
	    				s[1] = (value == 1) ? messages.getText("syngenta.checklist.atention") : messages.getText("syngenta.checklist.good");
	    				s[2] = Util.empty(pointComment) ? "----" : pointComment;
	    				itemList.add(s);
    				}else {
    					pneusValues[x][y] = vehicleCheck.getValueFromItem(item.getId()).trim();
    					pneusTexts [x][y] = messages.getText(item.getText());
    					if(y == 0) {
    						y++;
    					}else {
    						y = 0;
    						x++;
    					}
    				}
    			}
    		}
    		
	        ReportSyngentaCheckListData data = new ReportSyngentaCheckListData((String[][])itemList.toArray(new String[0][0]));
	        // Creo el reporte:   
	        ReportSyngentaCheckListFactory factory = new ReportSyngentaCheckListFactory("CheckList",data,reportForm,vehicleCheck,pneusTexts,pneusValues,sys);
	        return factory.createReport();
	   	    
	    }catch(Exception e){
       		e.printStackTrace();
       		throw e;
       	}
    }
       
    
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

} 
    


