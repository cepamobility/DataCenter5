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

import presentation.translator.web.queries.MitigationPlansReportForm;
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
public class MitigationPlansReportAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	// Date  ho1= new Date();
    	
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        
        MitigationPlansReportForm reportForm = (MitigationPlansReportForm)form;

        Integer[] divGeos = Util.getDivGeos(sys, reportForm, Filter.USER);
        Integer[][] divOrgs = Util.getDivOrgs(sys, reportForm, Filter.USER);
        Collection<Object[]> values = sys.getDriverReportFacade().getMitigationPlans(reportForm.getFirstName(), reportForm.getLastName(), reportForm.getCorporationId(), Integer.parseInt(reportForm.getStatus()), divOrgs, divGeos, sys.getUserSessionData());
        if(reportForm.getOutput().equals("XLS")) {
			response.setHeader("Content-Type", "application/vnd.ms-excel");
	        response.setHeader("Content-Disposition", "inline; filename=\"MitigationPlan.xls" + "\"");
	        ArrayList sheetResume = new ArrayList();
	        
	        ArrayList header = new ArrayList();
	        header.add(messages.getText("generic.driver.driverdata.corporationid"));
	        header.add(messages.getText("generic.driver.driverdata.firstname"));
	        header.add(messages.getText("generic.driver.driverdata.lastname"));
	        /*String[] labels = sys.getUserSessionData().getFilter().getDivOrgLabels();
	        for(int i = 0; i < labels.length; i++)
		        header.add(messages.getText(labels[i]));*/
	        header.add(messages.getText("generic.activity.activitydata.type"));
	        header.add(messages.getText("driver.query.advance"));
	        header.add(messages.getText("generic.messages.start"));
	        header.add(messages.getText("generic.activity.activitydata.activityexpiry"));
	        header.add("CRC");
	        sheetResume.add(header);
	        
	        Iterator<Object[]> it = values.iterator();
	        Calendar now = Calendar.getInstance();
	        while(it.hasNext()) {
	        	Object[] data = it.next();
	        	ArrayList line = new ArrayList();
	        	for(int i = 1; i < 9; i++) {
	        		switch(i) {
	        			case 4:
	        				int type = ((Integer)data[i]).intValue();
        					Object[] dataValues = new Object[3];
        					dataValues[0] = messages.getText("driver.mitigation.plan"+type);
	        				switch(type) {
	        					case 0:
		    	        			dataValues[1] = new Short(new HSSFColor.GREEN().getIndex());
		    	        			dataValues[2] = new Short(new HSSFColor.WHITE().getIndex());
		    	        			break;
	        					case 1:
		    	        			dataValues[1] = new Short(new HSSFColor.YELLOW().getIndex());
		    	        			dataValues[2] = new Short(new HSSFColor.BLACK().getIndex());
		    	        			break;
	        					case 2:
		    	        			dataValues[1] = new Short(new HSSFColor.RED().getIndex());
		    	        			dataValues[2] = new Short(new HSSFColor.WHITE().getIndex());
		    	        			break;
	        					case 3:
		    	        			dataValues[1] = new Short(new HSSFColor.BLACK().getIndex());
		    	        			dataValues[2] = new Short(new HSSFColor.WHITE().getIndex());
		    	        			break;
	        				}
    	        			line.add(dataValues);
	    	        		break;
	        			case 5:
	    	        		line.add(data[i]+"%");
	    	        		break;
	        			case 6: case 7:
	        				Calendar date = Calendar.getInstance();
	        				date.setTime((Date)data[i]);
	    	        		line.add(data[i]);
	    	        		break;
	    	        	default:
	    	        		line.add(data[i]);
	        		}
	        	}
	        	sheetResume.add(line);
	        }
			ExcelGenerator.generatorObjectXLS(sheetResume,"PlanesMitigacion.xls",response.getOutputStream());
        }else {
        	sys.getUserSessionData().setValue(values);
        	sys.getUserSessionData().setUrl("reports/mitigationPlansOut.jsp");
        	return mapping.findForward("layout");
        }

        return null;
        
    }
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
}