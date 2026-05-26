<!doctype html>

<%@page import="business.administration.corporation.CorporationReportFacade"%>
<%@page import="persistence.administration.corporation.CorporationReportBean"%>
<%@page import="org.jfree.report.modules.output.table.html.StreamHtmlFilesystem"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="org.jfree.report.modules.output.table.html.HtmlProcessor"%>
<%@page import="org.jfree.report.modules.output.pageable.base.PageableReportProcessor"%>
<%@page import="org.jfree.report.modules.output.pageable.pdf.PDFOutputTarget"%>
<%@page import="org.jfree.report.JFreeReportBoot"%>
<%@page import="org.jfree.report.JFreeReport"%>
<%@page import="presentation.report.driver.UserDriverPointReportFactory"%>
<%@page import="communication.driver.report.DriverPointReportData"%>
<%@page import="communication.driver.tables.DriverData"%>
<%@page import="communication.driver.tables.DriverPointData"%>
<%@page import="communication.language.Text"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Collections"%>
<%@page import="communication.activity.tables.ItemData"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@page import="presentation.translator.web.util.*"%>
<%@page import="communication.activity.tables.CheckListData"%>
<%@page language="java" 
	errorPage="../exception.jsp" 
	import="business.*"
%>
<%@page import="communication.administration.user.UserSessionData"%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData = sys.getUserSessionData();
	Text messages = sys.getText();
		
	Integer[][] divOrgs = new Integer[3][0];
	Integer[][] divGeos = new Integer[2][0];
	
	CorporationReportFacade corporationReport = sys.getCorporationReportFacade();
	Collection<DriverPointData> values = corporationReport.getDriverPoints("", "", DriverData.ACTIVE, divOrgs, divGeos, null, true, sys.getUserSessionData());
        
   	DriverPointReportData reportData = new DriverPointReportData(values,sys.getUserSessionData(),true);
	reportData.setElectronics(corporationReport.getElectroincs(sys.getUserSessionData()));
	reportData.setMitigationPlans(sys.getDriverReportFacade().getMitigationPlans(sys.getUserSessionData()));
   	reportData.setMessages(messages);
    UserDriverPointReportFactory factory = new UserDriverPointReportFactory(messages.getText("query.pointquery.title"),reportData,sys,messages,true);
        
    JFreeReportBoot.getInstance().start();

    JFreeReport jFreeReport = factory.createReport();

    final HtmlProcessor processor = new HtmlProcessor(jFreeReport);
    ByteArrayOutputStream ous = new ByteArrayOutputStream(); 
    processor.setFilesystem(new StreamHtmlFilesystem(ous));
    processor.processReport();  
    
    String html = ous.toString();
    byte[] bytes = html.getBytes();
    	            
    ous.flush();
    ous.close();
    
	for(int i = 0; i < bytes.length; i++) {
	    response.getWriter().write(bytes[i]);
	} 


%>