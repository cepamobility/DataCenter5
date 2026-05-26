<%@page language="java"
    errorPage="../exception.jsp"
	import="java.util.ArrayList"
	import="business.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.event.*"
	import="presentation.report.driver.*"
	import="communication.language.*"
	import="org.jfree.report.JFreeReport"
	import="org.jfree.report.JFreeReportBoot"
	import="org.jfree.report.ext.servletdemo.*"
	import="communication.event.report.*"
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
	
	EventReportForm reportForm = new EventReportForm(request.getParameter("divOrg11"),request.getParameter("divOrg12"),request.getParameter("divOrg13"),
	        request.getParameter("divOrg21"),request.getParameter("divOrg22"),request.getParameter("divOrg23"),
	        request.getParameter("divOrg31"),request.getParameter("divOrg32"),request.getParameter("divOrg33"),
	        request.getParameter("country1"),request.getParameter("country2"),request.getParameter("country3"));
	reportForm.setMonth1(request.getParameter("month1"));
	reportForm.setMonth2(request.getParameter("month2"));
	reportForm.setReportType(request.getParameter("reportType"));
	reportForm.setYear1(request.getParameter("year1"));
	reportForm.setYear2(request.getParameter("year2"));
	reportForm.setIncidents(request.getParameter("incident"));
	reportForm.setAccidents(request.getParameter("accident"));
	reportForm.setFines(request.getParameter("fine"));
	reportForm.setType(request.getParameter("type"));
	reportForm.setOutput(request.getParameter("output"));
	reportForm.setDriverStatus(request.getParameter("driverStatus"));
	JFreeReportBoot.getInstance().start();
	JFreeReport report = new EventReportAction().getReport(sys,reportForm,session,pageNumber);
	ReportUtil reportUtil = new ReportUtil();
	byte[] b = reportUtil.executeReport(response,report,"HTML",session,"event",null);

	if(b != null) {
		for(int i = 0; i < b.length; i++) {
		    response.getWriter().write(b[i]);
		} 
		
		String image = null;
		ArrayList arrayGraphics = (ArrayList)session.getAttribute("arrayGraficas");
		if (arrayGraphics != null && arrayGraphics.size() > 0)
		{
			for (int i = 0; i < arrayGraphics.size(); i++)
			{
				image = "/images/" + (String)arrayGraphics.get(i);			
%>			<div align="center">
				<br>
				<img src='<%=image%>' />
				<br>
			</div>
<%			}
		}
	}
%>

