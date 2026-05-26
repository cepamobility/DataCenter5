<%@page language="java"
    errorPage="../exception.jsp"
	import="java.io.File"
	import="java.util.ArrayList"
	import="presentation.report.ReportUtil"
	import="org.jfree.report.modules.output.table.html.HtmlProcessor"
	import="java.io.ByteArrayOutputStream"
	import="org.jfree.report.modules.output.table.html.StreamHtmlFilesystem"
	import="java.util.ArrayList"
	import="business.*"
	import="presentation.translator.web.util.*"
	import="presentation.report.vehicle.*" 
	import="presentation.translator.web.vehicle.*"
	import="communication.language.*"
	import="org.jfree.report.JFreeReport"
	import="org.jfree.report.JFreeReportBoot" 
	import="org.jfree.report.ext.servletdemo.*"
	import="communication.vehicle.report.*" 
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
	Text messages = sys.getText();
	int pageNumber = 1;
	if (Util.isNumber(request.getParameter("page"))){
		pageNumber = Integer.parseInt(request.getParameter("page"));
	}


	
	if (!Util.empty((String)request.getParameter("rt")) &&
			!((String)request.getParameter("rt")).equalsIgnoreCase("resumed")){
		// Si el reporte de checklist es detallado:
		
		ReportCheckListForm reportForm = new ReportCheckListForm(
			request.getParameter("divOrg11"),request.getParameter("divOrg12"),request.getParameter("divOrg13"),
	        request.getParameter("divOrg21"),request.getParameter("divOrg22"),request.getParameter("divOrg23"),
	        request.getParameter("divOrg31"),request.getParameter("divOrg32"),request.getParameter("divOrg33"),
	        request.getParameter("country1"),request.getParameter("country2"),request.getParameter("country3"));

		reportForm.setFrommonth(request.getParameter("fm"));
		reportForm.setFromyear(request.getParameter("fy"));
		reportForm.setOutput(request.getParameter("o"));
		reportForm.setTomonth(request.getParameter("tm"));
		reportForm.setToyear(request.getParameter("ty"));
		reportForm.setReportType(request.getParameter("rt"));
		reportForm.setType(request.getParameter("type"));
		reportForm.setOrder1(request.getParameter("o1"));
		reportForm.setOrder2(request.getParameter("o2"));
		reportForm.setOrder3(request.getParameter("o3"));
		
		if (reportForm.getOutput() == null)	reportForm.setOutput("HTML");		
		JFreeReportBoot.getInstance().start();
		JFreeReport report = new ReportCheckListAction().getReport(sys,reportForm,pageNumber);

		/* Genero el reporte */
		// HTML Report            	
	    final HtmlProcessor processor = new HtmlProcessor(report);
    	ByteArrayOutputStream ous = new ByteArrayOutputStream(); 
	    processor.setFilesystem(new StreamHtmlFilesystem(ous));
	    processor.processReport();                
    	session.setAttribute("resp",ous.toByteArray());
	    session.setAttribute("arrayGraficas", null);   
	}


	byte[] b = (byte[])session.getAttribute("resp");

	for(int i = 0; i < b.length; i++) {
	    response.getWriter().write(b[i]);
	} 
	
	
	String image = null;
	ArrayList arrayGraphics = (ArrayList)session.getAttribute("arrayGraficas");
	session.removeAttribute("arrayGraficas");
	if (arrayGraphics != null && arrayGraphics.size() > 0)
	{
		for (int i = 0; i < arrayGraphics.size(); i++)
		{
			image = "/images/" + (String)arrayGraphics.get(i);			
%>	
		<div align="center">
			<br>
			<img src='<%=image%>' />
			<br>
		</div>
		
<%		}

	}
%>

