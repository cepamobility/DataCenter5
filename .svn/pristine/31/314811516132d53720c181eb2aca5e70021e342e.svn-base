/*
 * Created on 24-feb-2006
 */
package presentation.translator.web.vehicle;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportFactory;
import presentation.report.ReportUtil;
import presentation.report.vehicle.ContractComodatoReportFactory;
import presentation.report.vehicle.ContractDevolutionReportFactory;
import presentation.report.vehicle.ContractDistratoReportFactory;
import presentation.report.vehicle.ContractResponsabilityReportFactory;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.datatype.tables.VehicleContractDataType;
import communication.security.SecurityConstants;
import communication.vehicle.report.ContractReportData;
import communication.vehicle.tables.VehicleContractData;
import communication.vehicle.tables.VehicleData;


public class VehicleSaveContractAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        
        DynaActionForm reportForm = (DynaActionForm)form;
        
        // TODO: Validar Fecha
        String day = reportForm.getString("day");
        String month = reportForm.getString("month");
        String year = reportForm.getString("year");
        Date date = Util.getDate(day, month, year);
	    String vehicleId = reportForm.getString("vehicle");
	    String type = (String)reportForm.getString("type");
	    int action = Integer.parseInt((String)reportForm.getString("action"));
	
	    VehicleData vehicle = sys.getVehicleReportFacade().findVehicleById(
	    		new Integer(vehicleId), sys.getUserSessionData());
	    

	    VehicleContractData contract = new VehicleContractData(new Integer(vehicleId),date,new VehicleContractDataType(type),true);
	    sys.getVehicleABMFacade().updateVehicleContract(contract,action,sys.getUserSessionData());
	    
	    return mapping.findForward("next");
    }

    /**
     * 
     */
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
} 
    


