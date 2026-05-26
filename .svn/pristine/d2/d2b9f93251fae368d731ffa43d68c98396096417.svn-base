package presentation.translator.web.activity.schedule;

import java.rmi.RemoteException;
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
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.activity.CommentaryDriveReportFactory;
import presentation.report.activity.CommentaryDriveRiskReportFactory;
import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.activity.report.CommentaryDriveReportData;
import communication.activity.report.CommentaryDriveRiskReportData;
import communication.activity.tables.CheckListData;
import communication.activity.tables.ItemData;
import communication.activity.tables.PointData;
import communication.exception.CommunicationProblemException;
import communication.exception.DCSecurityException;
import communication.exception.DeslogedException;
import communication.language.Text;

/**
 * @author jrodriguez
*/

public class CepaSystemResultReportAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        CommentaryDriveReportForm reportForm = (CommentaryDriveReportForm)form;
        
        JFreeReportBoot.getInstance().start();
        JFreeReport report = getReport(sys,reportForm);
        session.setAttribute("Bookmark",reportForm.getBookmark());

        return new ReportUtil().executeReport(mapping,response,report, reportForm.getOutput(),session,"CommentaryDriveQuery",true);
    }
    	
    public JFreeReport getReport(SystemAccess sys, CommentaryDriveReportForm reportForm) throws Exception {
        Text messages = sys.getText();

        String firstName = reportForm.getFirstName();
        String lastName = reportForm.getLastName();
        int status = Integer.parseInt(reportForm.getStatus());
        
            
        Date initialDate = reportForm.getInitialDate();
        Date lastDate = reportForm.getLastDate();

        Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
        Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);

        JFreeReport report = null;
        if(Integer.parseInt(reportForm.getCommentaryDrive()) == 0) {
            CheckListData checkListData = sys.getActivityReportFacade().findCheckList(new Integer(2),sys.getUserSessionData());
            Integer[] items = getItems(checkListData,sys);
            String type = reportForm.getConductionType(); 
            Collection data = sys.getActivityReportFacade().findCommentaryDriveResult(firstName,lastName,status,type,initialDate,lastDate,divOrgs,divGeos,reportForm.getVehicleAllowed(),sys.getUserSessionData());
            CommentaryDriveReportData cdData = new CommentaryDriveReportData(data,items,messages);
            CommentaryDriveReportFactory factory = new CommentaryDriveReportFactory("commentaryDrive",cdData,reportForm,checkListData.getPoints(),sys,items.length);
            report = factory.createReport();
        }else {
            CheckListData checkListData = sys.getActivityReportFacade().findCheckList(new Integer(17),sys.getUserSessionData());
            Integer[] items = getItems(checkListData,sys);
            Collection data = sys.getActivityReportFacade().findCommentaryDriveRiskResult(firstName,lastName,status,initialDate,lastDate,divOrgs,divGeos,reportForm.getVehicleAllowed(),sys.getUserSessionData());
            CommentaryDriveRiskReportData cdData = new CommentaryDriveRiskReportData(data,items,messages);
            CommentaryDriveRiskReportFactory factory = new CommentaryDriveRiskReportFactory("commentaryDriveRisk",cdData,reportForm,checkListData.getPoints(),sys,items.length);
            report = factory.createReport();
        }
        return report;
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

    private Integer[] getItems(CheckListData checkListData,SystemAccess sys) throws DeslogedException, CommunicationProblemException, DCSecurityException, RemoteException, Exception {
        Collection points = checkListData.getPoints();
        Integer[] items = new Integer[checkListData.getItemCount()];
        int i = 0;
        Collections.sort((List)points);
        Iterator it = points.iterator();
        while(it.hasNext()) {
            PointData point = (PointData)it.next();
            Collection pointItems = point.getItems();
            Collections.sort((List)pointItems);
            Iterator itemIt = pointItems.iterator();
            while(itemIt.hasNext()) {
                items[i] = ((ItemData)itemIt.next()).getId();
                i++;
            }
        }
        return items;
    }
} 
    


