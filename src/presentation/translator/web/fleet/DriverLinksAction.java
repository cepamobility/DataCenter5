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

import communication.fleet.tables.InsertedOdometer;
import communication.language.Text;


/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DriverLinksAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	// Date  ho1= new Date();
    	
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        InsertedOdometerForm reportForm = (InsertedOdometerForm)form;
		     
        if(Util.empty(reportForm.getFleet())) {
            session.setAttribute("Msg",messages.getText("query.mileage.selectfleet"));
        	return mapping.findForward("next");
        }
        String fleet = reportForm.getFleet();
        Collection<String[]> lines = sys.getFleetReportFacade().getDriverLinks(fleet, sys.getUserSessionData());

		ArrayList<ArrayList<Object>> sheetResume = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> headerResume = new ArrayList<Object>();
		headerResume.add(messages.getText("generic.data.driver").toUpperCase());
		headerResume.add("EMAIL");
		headerResume.add(messages.getText("generic.data.fleet").toUpperCase());
		headerResume.add("LINK");
		sheetResume.add(headerResume);

		Iterator<String[]> itLines = lines.iterator();
		while(itLines.hasNext()) {
			ArrayList<Object> lineArray = new ArrayList<Object>();
			String[] line = itLines.next();
			for(int i = 0; i < line.length; i++)
				lineArray.add(line[i]);
			sheetResume.add(lineArray);
		}

		response.setHeader("Content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "inline; filename=\"Links.xls" + "\"");

		ExcelGenerator.generatorObjectXLS(sheetResume,messages.getText("generic.messages.resume"),response.getOutputStream());

        return null;
        
    }
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
}