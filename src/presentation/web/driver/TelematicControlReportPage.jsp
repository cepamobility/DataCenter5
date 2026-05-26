<%@page language="java"
    errorPage="../exception.jsp"
	import="java.io.File"
	import="java.util.ArrayList"
	import="business.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.activity.*"
	import="presentation.report.driver.*"
	import="communication.language.*"
	import="org.jfree.report.JFreeReport"
	import="org.jfree.report.JFreeReportBoot"
	import="org.jfree.report.ext.servletdemo.*"
	import="communication.activity.schedule.report.*"
	import="presentation.report.*"
	import="presentation.report.driver.*"		
	import="presentation.translator.web.driver.*"		
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>
<%	

	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	String pageN = (String) request.getParameter("page")	;
	int pageNumber = (Util.isNumber(pageN)) ? Integer.parseInt(pageN):1; 
		
	TelematicControlQueryForm reportForm = new TelematicControlQueryForm(
			request.getParameter("divOrg11"),request.getParameter("divOrg12"),request.getParameter("divOrg13"),
	        request.getParameter("divOrg21"),request.getParameter("divOrg22"),request.getParameter("divOrg23"),
	        request.getParameter("divOrg31"),request.getParameter("divOrg32"),request.getParameter("divOrg33"),
	        request.getParameter("country1"),request.getParameter("country2"),request.getParameter("country3"));

		reportForm.setFirstname(request.getParameter("fn"));
		reportForm.setLastname(request.getParameter("ln"));
		
		reportForm.setFromday(request.getParameter("fd"));
		reportForm.setFrommonth(request.getParameter("fm"));
		reportForm.setFromyear(request.getParameter("fy"));
		
		reportForm.setToday(request.getParameter("td"));
		reportForm.setTomonth(request.getParameter("tm"));
		reportForm.setToyear(request.getParameter("ty"));
		
		reportForm.setIdDallas(request.getParameter("idd"));
		reportForm.setStatus(request.getParameter("status"));	
		reportForm.setOutput(request.getParameter("output"));
		
		reportForm.setType(request.getParameter("type"));
		reportForm.setRecomendation(request.getParameter("r"));
		
		reportForm.setReportType(request.getParameter("rt"));
		reportForm.setColor(request.getParameter("color"));
		reportForm.setColorOrType(request.getParameter("cort"));
		
		
		JFreeReportBoot.getInstance().start();
		JFreeReport report = new TelematicControlQueryAction().getReport(sys,reportForm,pageNumber);
			
		byte[] b = new ReportUtil().executeReport(response,report,"HTML",session,"driver",null);
	
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

