/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.queries;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.queries.ReportAccidentTypeFactory;
import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.DivOrgForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.datatype.DataTypeReportFacade;

import communication.administration.bookmark.tables.BookmarkData;
import communication.datatype.tables.AccidentClasificationDataType;
import communication.datatype.tables.FineQualifyDataType;
import communication.datatype.tables.IncidentClasificationDataType;
import communication.event.tables.EventDataType;
import communication.fleet.util.EventTypeData;
import communication.language.Text;
import communication.queries.report.ReportAccidentTypeData;
import communication.util.Filter;


/**
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReportEventTypeAction extends AbstractAction {
	
    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        ReportEventTypeForm reportForm = (ReportEventTypeForm)form;
        BookmarkData bookmark = reportForm.getBookmark();
        
        String fromMonth = reportForm.getFrommonth();
        String toMonth = reportForm.getTomonth();
        String fromYear = reportForm.getFromyear();
        String toYear = reportForm.getToyear();
        if(fromMonth.length() == 0 || toMonth.length() == 0 || fromYear.length() == 0 || toYear.length() == 0) {
            session.setAttribute("Msg",messages.getText("generic.error.emptyperiod"));
            sys.getUserSessionData().setUrl("queries/reportEventType.jsp?type=individual");
            return mapping.findForward("layout");
        }
        if((fromMonth.startsWith("Q") && !toMonth.startsWith("Q")) || (!fromMonth.startsWith("Q") && toMonth.startsWith("Q"))) {
            session.setAttribute("Msg",messages.getText("generic.error.invalidperiod"));
            sys.getUserSessionData().setUrl("queries/reportEventType.jsp?type=individual");
            return mapping.findForward("layout");
        }
        if(fromMonth.startsWith("Q")) {
            fromMonth = String.valueOf(Integer.parseInt(fromMonth.substring(1)) * 3 - 2);
        }
        if(toMonth.startsWith("Q")) {
            toMonth = String.valueOf(Integer.parseInt(toMonth.substring(1)) * 3);
        }
        
        Integer[][] divOrgs = Util.getDivOrgs(sys,(DriverDivOrgForm)reportForm,Filter.CORPORATION);
        Integer[] divGeos = Util.getDivGeos(sys,(DriverDivOrgForm)reportForm,Filter.CORPORATION);
        
        ArrayList arrayGraphics = new ArrayList();
        DataTypeReportFacade dataTypeReport = sys.getDataTypeReportFacade();        

        String record = "all";
        String finesIn = "";
        Iterator datatypeIt = dataTypeReport.findAll(communication.datatype.DataTypeConstants.OLD_FINE_QUALIFY,sys.getUserSessionData()).iterator();
        while(datatypeIt.hasNext()) {
            FineQualifyDataType data = (FineQualifyDataType)datatypeIt.next();
            String fine = data.getResourceBundleId();
            if(Util.isChecked(request.getParameter(fine))) {
                finesIn += (finesIn.length() == 0) ? "'"+fine+"'" : ",'"+fine+"'";   
            }
        }
        if(finesIn.length() > 0) {
            bookmark.addParameter("finesIn",finesIn);
        }

        String accidentIn = "";
        datatypeIt = dataTypeReport.findAll(communication.datatype.DataTypeConstants.OLD_ACCIDENT_CLASIFICATION,sys.getUserSessionData()).iterator();
        while(datatypeIt.hasNext()) {
            AccidentClasificationDataType data = (AccidentClasificationDataType)datatypeIt.next();
            String accident = data.getResourceBundleId();
            if(Util.isChecked(request.getParameter("a"+accident))) {
                accidentIn += (accidentIn.length() == 0) ? "'"+accident+"'" : ",'"+accident+"'";   
            }
            
        }
        datatypeIt = dataTypeReport.findAll(communication.datatype.DataTypeConstants.OLD_INCIDENT_CLASIFICATION,sys.getUserSessionData()).iterator();
        while(datatypeIt.hasNext()) {
            IncidentClasificationDataType data = (IncidentClasificationDataType)datatypeIt.next();
            String incident = data.getResourceBundleId();
            if(Util.isChecked(request.getParameter("a"+incident))) {
                accidentIn += (accidentIn.length() == 0) ? "'"+incident+"'" : ",'"+incident+"'";   
            }
        }
        if(accidentIn.length() > 0) {
            bookmark.addParameter("accidentsIn",accidentIn);
        }


        int order1 = Integer.parseInt(reportForm.getOrder1());
        int order2 = Integer.parseInt(reportForm.getOrder2());
        int order3 = (sys.getUserSessionData().getFilter().getPrincipalCount() > 1) ? Integer.parseInt(reportForm.getOrder3()) : 4;
        int order4 = (sys.getUserSessionData().getFilter().getPrincipalCount() > 2) ? Integer.parseInt(reportForm.getOrder4()) : 4;
        
        Collection elements = new LinkedList();        

        if(Util.isChecked(reportForm.getIncidents()) && accidentIn.length() >  0) {
            Collection incidents = new LinkedList();        
            if(Util.isChecked(reportForm.getIndividual())) {
                incidents = sys.getEventReportFacade().getReportEventType(fromMonth,new Integer(fromYear),toMonth,new Integer(toYear),divOrgs,divGeos,accidentIn,sys.getUserSessionData(),EventDataType.INCIDENT,record,order1,order2,order3,order4,messages);
            }
            if(Util.isChecked(reportForm.getColective())) {
                Iterator colective = sys.getFleetReportFacade().getReportIncidentType(fromMonth,new Integer(fromYear),toMonth,new Integer(toYear),divOrgs,divGeos,accidentIn,sys.getUserSessionData(),order1,order2,order3,order4,messages).iterator();
                while(colective.hasNext()) {
                    EventTypeData eventType = (EventTypeData)colective.next();
                    Iterator elementIt = incidents.iterator();
                    boolean find = false;
                    while(elementIt.hasNext() && !find) {
                        EventTypeData element = (EventTypeData)elementIt.next();
                        if(element.equalData(eventType)) {
                            find = true;
                            element.addData(eventType);
                        }
                    }
                    if(!find) {
                        incidents.add(eventType);
                    }
                }
            }
            elements.addAll(incidents);
        }
        
        if(Util.isChecked(reportForm.getAccidents()) && accidentIn.length() >  0) {
            Collection accidents = new LinkedList();        
            if(Util.isChecked(reportForm.getIndividual())) {
                accidents = sys.getEventReportFacade().getReportEventType(fromMonth,new Integer(fromYear),toMonth,new Integer(toYear),divOrgs,divGeos,accidentIn,sys.getUserSessionData(),EventDataType.ACCIDENT,record,order1,order2,order3,order4,messages);
            }
            if(Util.isChecked(reportForm.getColective())) {
                Iterator colective = sys.getFleetReportFacade().getReportAccidentType(fromMonth,new Integer(fromYear),toMonth,new Integer(toYear),divOrgs,divGeos,accidentIn,sys.getUserSessionData(),order1,order2,order3,order4,messages).iterator();
                while(colective.hasNext()) {
                    EventTypeData eventType = (EventTypeData)colective.next();
                    Iterator elementIt = accidents.iterator();
                    boolean find = false;
                    while(elementIt.hasNext() && !find) {
                        EventTypeData element = (EventTypeData)elementIt.next();
                        if(element.equalData(eventType)) {
                            find = true;
                            element.addData(eventType);
                        }
                    }
                    if(!find) {
                        accidents.add(eventType);
                    }
                }
            }
            elements.addAll(accidents);
        }
        
        if(Util.isChecked(reportForm.getNearmiss()) && accidentIn.length() >  0) {
            Collection nearmiss = new LinkedList();        
            if(Util.isChecked(reportForm.getIndividual())) {
            	nearmiss = sys.getEventReportFacade().getReportEventType(fromMonth,new Integer(fromYear),toMonth,new Integer(toYear),divOrgs,divGeos,accidentIn,sys.getUserSessionData(),EventDataType.NEARMISS,record,order1,order2,order3,order4,messages);
            }
            elements.addAll(nearmiss);
        }

        if(Util.isChecked(reportForm.getIndividual()) && finesIn.length() >  0) {
            if (!record.equals("nonrecordable")) {
            	Collection reportFine = sys.getEventReportFacade().getReportFineByType(
            			fromMonth,new Integer(fromYear),toMonth,new Integer(toYear),divOrgs,divGeos,finesIn,sys.getUserSessionData(),order1,order2,order3,order4,messages);
            	if (reportFine != null){
            		elements.addAll(reportFine);
            	}
            }
        }

        ReportAccidentTypeData result = new ReportAccidentTypeData(elements,messages,order1,order2,order3,order4);
    	   	
        ReportAccidentTypeFactory factory = new ReportAccidentTypeFactory(messages.getText("query.accidenttype.title"),result,form,sys,messages,reportForm.getOutput(),order1,order2,order3,order4);
        JFreeReport report = factory.createReport();
        
        if(order1 == 4) {
            if(result.accidentsChart()) {
                final JFreeChart chart = ChartFactory.createPieChart(messages.getText("query.accidenttype.accidentcount"),result.getDataset(EventDataType.ACCIDENT),true,true,false);
                chart.setBackgroundPaint(Color.WHITE);
                report.setProperty("Accidents",chart);
                report.setPropertyMarked("Accidents", true);
                arrayGraphics.add(Util.createJPEGFromChart(sys,chart,"accidents", "AccidentType"));
            }
            
             
            if(result.incidentsChart()) {
                final JFreeChart chart = ChartFactory.createPieChart(messages.getText("query.accidenttype.incidentcount"),result.getDataset(EventDataType.INCIDENT),true,true,false);
                chart.setBackgroundPaint(Color.WHITE);
                report.setProperty("Incidents", chart);
                report.setPropertyMarked("Incidents", true);
                arrayGraphics.add(Util.createJPEGFromChart(sys,chart,"incidents", "AccidentType"));
            }
            
            if(result.nearmissChart()) {
                final JFreeChart chart = ChartFactory.createPieChart(messages.getText("query.accidenttype.nearmisscount"),result.getDataset(EventDataType.NEARMISS),true,true,false);
                chart.setBackgroundPaint(Color.WHITE);
                report.setProperty("NearMiss", chart);
                report.setPropertyMarked("NearMiss", true);
                arrayGraphics.add(Util.createJPEGFromChart(sys,chart,"nearmiss", "AccidentType"));
            }
            
            if(result.fineChart()) {
                final JFreeChart chart = ChartFactory.createPieChart(messages.getText("query.accidenttype.finecount"),result.getDataset(EventDataType.FINE),true,true,false);
                chart.setBackgroundPaint(Color.WHITE);
                report.setProperty("Fines", chart);
                report.setPropertyMarked("Fines", true);
                arrayGraphics.add(Util.createJPEGFromChart(sys,chart,"fines", "AccidentType"));
            }
        }
        
        JFreeReportBoot.getInstance().start();
        
        session.setAttribute("Bookmark",bookmark);
        
        session.removeAttribute("ReportEventTypeForm");
        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"AccidentType",arrayGraphics,true);
        
        return fwd;
        
    }
    
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

    public JFreeReport getReport(SystemAccess sys,ReportAccidentTypeAllForm reportForm) {
        try {
            Text messages = sys.getText(); 
            
            Integer[][] divOrgs = Util.getDivOrgs(sys,(DivOrgForm)reportForm,Filter.CORPORATION);
            Integer[] divGeos = Util.getDivGeos(sys,(DivOrgForm)reportForm,Filter.CORPORATION);
            String record = "all";

            String fromMonth = reportForm.getFrommonth();
            String toMonth = reportForm.getTomonth();
            String fromYear = reportForm.getFromyear();
            String toYear = reportForm.getToyear();

            String finesIn = reportForm.getFinesIn();
            String accidentIn = reportForm.getAccidentsIn();
            String incidentIn = reportForm.getIncidentsIn();

            int order1 = Integer.parseInt(reportForm.getOrder1());
            int order2 = Integer.parseInt(reportForm.getOrder2());
            int order3 = (sys.getUserSessionData().getFilter().getPrincipalCount() > 1) ? Integer.parseInt(reportForm.getOrder3()) : 4;
            int order4 = (sys.getUserSessionData().getFilter().getPrincipalCount() > 2) ? Integer.parseInt(reportForm.getOrder4()) : 4;
            
            Collection elements = new LinkedList();        

            if(incidentIn.length() >  0) {
                Collection incidents = new LinkedList();        
                if(Util.isChecked(reportForm.getIndividual())) {
                    incidents = sys.getEventReportFacade().getReportEventType(fromMonth,new Integer(fromYear),toMonth,new Integer(toYear),divOrgs,divGeos,incidentIn,sys.getUserSessionData(),EventDataType.INCIDENT,record,order1,order2,order3,order4,messages);
                }
                if(Util.isChecked(reportForm.getColective())) {
                    Iterator colective = sys.getFleetReportFacade().getReportIncidentType(fromMonth,new Integer(fromYear),toMonth,new Integer(toYear),divOrgs,divGeos,incidentIn,sys.getUserSessionData(),order1,order2,order3,order4,messages).iterator();
                    while(colective.hasNext()) {
                        EventTypeData eventType = (EventTypeData)colective.next();
                        Iterator elementIt = incidents.iterator();
                        boolean find = false;
                        while(elementIt.hasNext() && !find) {
                            EventTypeData element = (EventTypeData)elementIt.next();
                            if(element.equalData(eventType)) {
                                find = true;
                                element.addData(eventType);
                            }
                        }
                        if(!find) {
                            incidents.add(eventType);
                        }
                    }
                }
                elements.addAll(incidents);
            }
            
            if(accidentIn.length() >  0) {
                Collection accidents = new LinkedList();        
                if(Util.isChecked(reportForm.getIndividual())) {
                    accidents = sys.getEventReportFacade().getReportEventType(fromMonth,new Integer(fromYear),toMonth,new Integer(toYear),divOrgs,divGeos,accidentIn,sys.getUserSessionData(),EventDataType.ACCIDENT,record,order1,order2,order3,order4,messages);
                }
                if(Util.isChecked(reportForm.getColective())) {
                    Iterator colective = sys.getFleetReportFacade().getReportAccidentType(fromMonth,new Integer(fromYear),toMonth,new Integer(toYear),divOrgs,divGeos,accidentIn,sys.getUserSessionData(),order1,order2,order3,order4,messages).iterator();
                    while(colective.hasNext()) {
                        EventTypeData eventType = (EventTypeData)colective.next();
                        Iterator elementIt = accidents.iterator();
                        boolean find = false;
                        while(elementIt.hasNext() && !find) {
                            EventTypeData element = (EventTypeData)elementIt.next();
                            if(element.equalData(eventType)) {
                                find = true;
                                element.addData(eventType);
                            }
                        }
                        if(!find) {
                            accidents.add(eventType);
                        }
                    }
                }
                elements.addAll(accidents);
            }
            
            if(Util.isChecked(reportForm.getIndividual()) && finesIn.length() >  0) {
                if (!record.equals("nonrecordable")) {
                    elements.addAll(sys.getEventReportFacade().getReportFineByType(fromMonth,new Integer(fromYear),toMonth,new Integer(toYear),divOrgs,divGeos,finesIn,sys.getUserSessionData(),order1,order2,order3,order4,messages));
                }
            }

            ReportAccidentTypeData result = new ReportAccidentTypeData(elements,messages,order1,order2,order3,order4);
                
            ReportAccidentTypeFactory factory = new ReportAccidentTypeFactory(messages.getText("query.accidenttype.title"),result,reportForm,sys,messages,reportForm.getOutput(),order1,order2,order3,order4);
            return factory.createReport();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
} 
    


