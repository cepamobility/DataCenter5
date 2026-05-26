/*
  * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.fleet;


import java.text.SimpleDateFormat;
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
import communication.administration.corporation.tables.PointMoveData;
import communication.administration.dpa.UnconformityTypeDPADataType;
import communication.fleet.ContractorPointData;
import communication.fleet.VehiclePointData;
import communication.language.Text;
import communication.util.ExcelGenerator;

/**
 * @author fcarriquiry
 */
public class UPMScoringReportAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

		 HttpSession session = request.getSession();
         SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
         Text messages = sys.getText();
        
         UPMScoringReportForm formUPM=(UPMScoringReportForm) form;
         response.setHeader("Content-Type", "application/vnd.ms-excel");
    	 response.setHeader("Content-Disposition", "inline; filename=\"UPMScoringReport.xls");
    	
    	 
    	 ArrayList<ContractorPointData> list=sys.getBIReportFacade().findContractorsPointData(Util.getDate(formUPM.getStartDay(), formUPM.getStartMonth(),formUPM.getStartYear() ), Util.getDate(formUPM.getEndDay(), formUPM.getEndMonth(), formUPM.getEndYear()), formUPM.getContractorId(), sys.getUserSessionData());
    	
    	 ArrayList<ArrayList<String>> xls=new ArrayList<ArrayList<String>>();
		 ArrayList<String> header=new ArrayList<String>();
		 header.add("Contratista");
		 header.add("Placa");
		 header.add("Movimiento");
		 header.add("Fecha");
		 header.add("Puntos");
		 header.add("Total de puntos en la cuenta al momento del movimiento");
		 xls.add(header);
		 SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy HH:mm");
    	 for(ContractorPointData contractor:list){
    		 for(VehiclePointData vehicle:contractor.getVehicles()){
    			 for(PointMoveData point:vehicle.getMoves()){
    				 ArrayList<String> row=new ArrayList<String>();
    				 row.add(contractor.getName());
    				 row.add(vehicle.getPlate());
    				 row.add(point.getDescription());
    				 row.add(format.format(point.getDate().getTime()));
    				 row.add(point.getAmount().toString());
    				 row.add(point.getActualTotal().toString());
    				 xls.add(row);
    			 }
    		 }
    	 }
    	  
    	 
    	 ExcelGenerator.generatorXLS(xls, "UPMScoringReport", response.getOutputStream());
    	 
         request.setAttribute("UPMScoringReportForm", formUPM);
         
	     return null;
        	
	}
	
	

	@Override
	public ActionForward cancel(HttpSession session, ActionMapping mapping,
			ActionForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	
    
}
