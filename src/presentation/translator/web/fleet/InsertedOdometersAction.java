package presentation.translator.web.fleet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import persistence.util.ExcelGenerator;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.fleet.tables.InsertedOdometer;
import communication.language.Text;


/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InsertedOdometersAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	// Date  ho1= new Date();
    	
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        InsertedOdometerForm reportForm = (InsertedOdometerForm)form;
		     
        if(reportForm.getFromMonth().equals("0") || reportForm.getFromYear().equals("0") || reportForm.getToMonth().equals("0") || reportForm.getToYear().equals("0")) {
            session.setAttribute("Msg",messages.getText("generic.error.invalidperiod"));
        	return mapping.findForward("next");
        }
        Integer periodFrom = new Integer(reportForm.getFromMonth());
        Integer yearFrom = new Integer(reportForm.getFromYear());
        Integer periodTo = new Integer(reportForm.getToMonth());
        Integer yearTo = new Integer(reportForm.getToYear());

        if(Util.empty(reportForm.getFleet())) {
            session.setAttribute("Msg",messages.getText("query.mileage.selectfleet"));
        	return mapping.findForward("next");
        }
        Integer fleet = new Integer(reportForm.getFleet());
        Collection<InsertedOdometer> lines = sys.getFleetReportFacade().getInsertedOdometers(fleet, periodFrom, periodTo, yearFrom, yearTo, sys.getUserSessionData());
        HashMap<String, Object[]> logs = sys.getFleetReportFacade().getInsertedOdometerLogs(fleet, periodFrom, periodTo, yearFrom, yearTo, sys.getUserSessionData());
        
		ArrayList<ArrayList<Object>> sheetResume = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> headerResume = new ArrayList<Object>();
		headerResume.add(messages.getText("generic.vehicle.vehicledata.matricula"));
		headerResume.add(messages.getText("generic.data.driver"));
		
		String[] labels = (sys.isCorporation(CorporationData.ALLERGAN)) ? sys.getUserSessionData().getFilter().getDivOrgLabels() : sys.getUserSessionData().getFilter().getPrincipalLabels();
		for(int i = 0; i < labels.length; i++) {
			if(!Util.empty(labels[i])) {
				headerResume.add(messages.getText(labels[i]));
			}
		}
		HashMap months = new HashMap();
		int index = 1;
		Collection<String> values = new LinkedList<String>();
		for(int i = yearFrom; i <= yearTo; i++) {
			int start = (i == yearFrom) ? periodFrom : 1;
			int end = (i == yearTo) ? periodTo : 12;
			for(int j = start; j <= end; j++) {
				String monthStr = messages.getText("generic.shortmonth."+j)+"/"+i;
				headerResume.add(monthStr);
				headerResume.add(messages.getText("generic.messages.date")+" "+monthStr);
				headerResume.add(messages.getText("generic.data.insertedby")+" "+monthStr);
				months.put(index, j+"-"+i);
				if(index > 1)
					values.add("Kms "+messages.getText("generic.shortmonth."+j)+"/"+i);
				index++;
			}
		}
		Iterator<String> it = values.iterator();
		while(it.hasNext()) {
			headerResume.add(it.next());
		}
		sheetResume.add(headerResume);

		Iterator<InsertedOdometer> itLines = lines.iterator();
		while(itLines.hasNext()) {
			sheetResume.add(itLines.next().getLine(messages,months,logs));
		}

		response.setHeader("Content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "inline; filename=\"Odometers.xls" + "\"");

		ExcelGenerator.generatorObjectXLS(sheetResume,messages.getText("generic.messages.resume"),response.getOutputStream());

        return null;
        
    }
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
}