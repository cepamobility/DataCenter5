package presentation.translator.web.reports;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.queries.PendingActivitiesForm;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.language.Text;
import communication.util.ExcelGenerator;
import communication.util.Filter;


/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PendingActivitiesReportAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	// Date  ho1= new Date();
    	
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        
        PendingActivitiesForm reportForm = (PendingActivitiesForm)form;

        Integer[] divGeos = Util.getDivGeos(sys, reportForm, Filter.USER);
        Integer[][] divOrgs = Util.getDivOrgs(sys, reportForm, Filter.USER);
        Collection<Object[]> values = sys.getDriverReportFacade().getPendingActivities(reportForm.getMitigation(), reportForm.getAnual(), Integer.parseInt(reportForm.getStatus()), Util.getCalendar(reportForm.getFromday(),reportForm.getFrommonth(),reportForm.getFromyear()), Util.getCalendar(reportForm.getToday(),reportForm.getTomonth(),reportForm.getToyear()), divOrgs, divGeos, sys.getUserSessionData());
        if(reportForm.getOutput().equals("XLS")) {
			response.setHeader("Content-Type", "application/vnd.ms-excel");
	        response.setHeader("Content-Disposition", "inline; filename=\"ActividadesPendientes.xls" + "\"");
	        ArrayList sheetResume = new ArrayList();
	        
	        ArrayList header = new ArrayList();
	        header.add(messages.getText("generic.driver.driverdata.firstname"));
	        header.add(messages.getText("generic.driver.driverdata.lastname"));
	        String[] labels = sys.getUserSessionData().getFilter().getDivOrgLabels();
	        for(int i = 0; i < labels.length; i++)
		        header.add(messages.getText(labels[i]));
	        header.add(messages.getText("module.activity.name"));
	        header.add(messages.getText("generic.activity.activitydata.type"));
	        header.add(messages.getText("generic.activity.activitydata.activityexpiry"));
	        sheetResume.add(header);
	        
	        Iterator<Object[]> it = values.iterator();
	        Calendar now = Calendar.getInstance();
	        while(it.hasNext()) {
	        	Object[] data = it.next();
	        	ArrayList line = new ArrayList();
	        	for(int i = 1; i < data.length; i++) {
	        		switch(i) {
	        			case 12:
	        				line.add(messages.getText("driver."+((String)data[13]).toLowerCase()+"plan.activity"+data[i]));
	    	        		break;
	        			case 13:
	    	        		line.add(messages.getText("generic.driver."+((String)data[i]).toLowerCase()+"plan"));
	    	        		break;
	        			case 14:
	        				Calendar date = Calendar.getInstance();
	        				date.setTime((Date)data[i]);
	        				if(date.before(now)) {
	        					Object[] dataValues = new Object[3];
	        					dataValues[0] = data[i];
	    	        			dataValues[1] = new Short(new HSSFColor.RED().getIndex());
	    	        			dataValues[2] = new Short(new HSSFColor.WHITE().getIndex());
	    	        			line.add(dataValues);
	        				}else {
		    	        		line.add(data[i]);
	        				}
	    	        		break;
	    	        	default:
	    	        		line.add(data[i]);
	        		}
	        	}
	        	sheetResume.add(line);
	        }
			ExcelGenerator.generatorObjectXLS(sheetResume,"ActividadesPendientes.xls",response.getOutputStream());
        }else {
        	sys.getUserSessionData().setValue(values);
        	sys.getUserSessionData().setUrl("reports/pendingActivitiesReport.jsp");
        	return mapping.findForward("layout");
        }

        return null;
        
    }
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
}