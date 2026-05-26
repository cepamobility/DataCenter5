<%@page language="java"
    errorPage="../exception.jsp"
	import="java.util.ArrayList"
	import="business.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.cenapra.*"
	import="presentation.report.driver.*"
	import="communication.language.*"
	import="org.jfree.report.JFreeReport"
	import="org.jfree.report.JFreeReportBoot"
	import="org.jfree.report.ext.servletdemo.*"
	import="communication.driver.report.*"
	import="communication.administration.corporation.tables.*"
	import="presentation.report.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>
<%	

	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	int pageNumber = Integer.parseInt(request.getParameter("page"));
	JFreeReport report = null;
	
	if(request.getParameter("report").equals("list")){
		ReportForm reportForm = new ReportForm(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("all"), request.getParameter("fromDay"), request.getParameter("fromMonth"), request.getParameter("fromYear"), request.getParameter("toDay"), request.getParameter("toMonth"), request.getParameter("toYear"), request.getParameter("multiplicadores"), request.getParameter("replicadores"), request.getParameter("multiplicadoresNew"), request.getParameter("multiplicadores2"), request.getParameter("formadores"), request.getParameter("poblacionmeta"), request.getParameter("checklist"), request.getParameter("order"));
		reportForm.setOutput("HTML");
		JFreeReportBoot.getInstance().start();
		report = new ParticipantListAction().getReport(sys,reportForm,pageNumber);
    }else if(request.getParameter("report").equals("report")){
		ReportForm reportForm = new ReportForm(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("all"), request.getParameter("fromDay"), request.getParameter("fromMonth"), request.getParameter("fromYear"), request.getParameter("toDay"), request.getParameter("toMonth"), request.getParameter("toYear"), request.getParameter("multiplicadores"), request.getParameter("replicadores"), request.getParameter("multiplicadoresNew"), request.getParameter("multiplicadores2"), request.getParameter("formadores"), request.getParameter("poblacionmeta"), request.getParameter("checklist"), request.getParameter("order"));
		reportForm.setOutput("HTML");
		JFreeReportBoot.getInstance().start();
		report = new CheckListReportAction().getReport(sys,reportForm,pageNumber);
    }
	ReportUtil reportUtil= new ReportUtil();
	byte[] b = reportUtil.executeReport(response,report,"HTML",session,"driver",null);

	if(b != null) {
		for(int i = 0; i < b.length; i++) {
		    response.getWriter().write(b[i]);
		} 
	}
%>

