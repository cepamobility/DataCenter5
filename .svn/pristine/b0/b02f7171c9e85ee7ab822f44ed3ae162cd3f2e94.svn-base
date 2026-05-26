package presentation.translator.web.activity.schedule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import persistence.util.ExcelGenerator;
import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.activity.tables.ActivityList;
import communication.activity.tables.DriverMaxExpiry;
import communication.administration.corporation.tables.CorporationData;
import communication.language.Text;


/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActivityMaxExpiryAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	// Date  ho1= new Date();
    	
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        ActivityMaxExpiryForm reportForm = (ActivityMaxExpiryForm)form;
		     
        Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
        Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);        
        
        int status = Integer.parseInt(reportForm.getStatus());
        String firstName = reportForm.getFirstName();
        String lastName = reportForm.getLastName();
        String vehicleAllowed = reportForm.getVehicleAllowed();
        Date from = reportForm.getFrom();
        Date to = reportForm.getTo();

        Collection activities = sys.getDataTypeReportFacade().getActivityWithExpiry(sys.getUserSessionData(),divOrgs,divGeos[0],from, to, status, firstName, lastName, vehicleAllowed);
        ActivityList list = new ActivityList(activities, messages);
        String[] types = list.getArray();

        Collection<DriverMaxExpiry> lines = sys.getActivityReportFacade().getMaxExpiry(sys.getUserSessionData(),types,divOrgs,divGeos[0],from,to,status,firstName,lastName,vehicleAllowed);        

		ArrayList<ArrayList<Object>> sheetResume = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> headerResume = new ArrayList<Object>();
		headerResume.add(messages.getText("generic.driver.driverdata.firstname"));
		if(sys.isCorporation(CorporationData.JJ)) {
			headerResume.add(messages.getText("generic.driver.driverdata.country"));
			headerResume.add(messages.getText("generic.driver.driverdata.corporationid"));
		}else {
			headerResume.add(messages.getText("generic.driver.driverdata.director"));
			headerResume.add(messages.getText("generic.driver.driverdata.email"));
			headerResume.add(messages.getText("generic.driver.driverdata.vehicleallowed"));
			headerResume.add(messages.getText("generic.driver.driverdata.licenseobs"));
			headerResume.add(messages.getText("generic.driver.driverdata.country"));
			headerResume.add(messages.getText("generic.driver.driverdata.city"));
			headerResume.add(messages.getText("generic.driver.driverdata.phonenumber"));
			headerResume.add(messages.getText("generic.dridverlogistic.title")+" - "+messages.getText("generic.activity.type"));
			headerResume.add(messages.getText("generic.dridverlogistic.title")+" - "+messages.getText("generic.activity.date"));
			headerResume.add(messages.getText("generic.dridverlogistic.title")+" - "+messages.getText("generic.driverlogistic.city"));
			headerResume.add(messages.getText("generic.dridverlogistic.title")+" - "+messages.getText("generic.driverlogistic.status"));
			headerResume.add(messages.getText("generic.dridverlogistic.title")+" - "+messages.getText("generic.driverlogistic.observations"));
		}
		
		String[] labels = sys.getUserSessionData().getFilter().getPrincipalLabels();
		int len = 0;
		for(int i = 0; i < labels.length; i++) {
			if(!Util.empty(labels[i])) {
				headerResume.add(messages.getText(labels[i]));
				len++;
			}
		}
		for(int i = 0; i < types.length; i++) {
			headerResume.add(messages.getText(types[i]));
		}
		sheetResume.add(headerResume);

		Collections.sort((List)lines);
		Iterator<DriverMaxExpiry> itLines = lines.iterator();
		while(itLines.hasNext()) {
			if(sys.isCorporation(CorporationData.JJ)) {
				sheetResume.addAll(itLines.next().getLineJJ(messages,len));
			}else {
				sheetResume.addAll(itLines.next().getLine(messages,len));
			}
		}

		response.setHeader("Content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "inline; filename=\"MaxExpiryActivity.xls" + "\"");

		ExcelGenerator.generatorObjectXLS(sheetResume,messages.getText("generic.messages.resume"),response.getOutputStream());

        return null;
        
    }
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
}