<%@page language="java" 
	errorPage="../exception.jsp"
	import="java.util.ArrayList"
	import="business.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.vehicle.*"
	import="presentation.report.driver.*" 
	import="communication.language.*"
	import="org.jfree.report.JFreeReport"
	import="org.jfree.report.JFreeReportBoot" 
	import="org.jfree.report.ext.servletdemo.*"
	import="communication.driver.report.*" 
	import="presentation.report.*"
	import="org.jfree.report.modules.output.table.html.HtmlProcessor"
	import="java.io.ByteArrayOutputStream"
	import="org.jfree.report.modules.output.table.html.StreamHtmlFilesystem"
	import="presentation.report.ReportUtil"

%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html:html>
<body>

<%	
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	Text messages = sys.getText();
	int pageNumber = 1;
	if (Util.isNumber(request.getParameter("page"))){
		pageNumber = Integer.parseInt(request.getParameter("page"));
	}

	SuctaMTOPQueryForm reportForm = new SuctaMTOPQueryForm(
			request.getParameter("divOrg11"),request.getParameter("divOrg12"),request.getParameter("divOrg13"),
	        request.getParameter("divOrg21"),request.getParameter("divOrg22"),request.getParameter("divOrg23"),
	        request.getParameter("divOrg31"),request.getParameter("divOrg32"),request.getParameter("divOrg33"),
	        request.getParameter("country1"),request.getParameter("country2"),request.getParameter("country3"));

	reportForm.setStatus(request.getParameter("status"));
	reportForm.setPlate(request.getParameter("plate"));
	reportForm.setSuctaormtop(request.getParameter("sotm"));
	reportForm.setOutput(request.getParameter("o"));
    reportForm.setVehicleorplataform(request.getParameter("vp"));
    reportForm.setTomonth(request.getParameter("tm"));
    reportForm.setToyear(request.getParameter("ty"));
    reportForm.setFrommonth(request.getParameter("fm"));
    reportForm.setFromyear(request.getParameter("fy"));    
	
	if (reportForm.getOutput() == null)	reportForm.setOutput("HTML");
		
	JFreeReportBoot.getInstance().start();
	JFreeReport report = new SuctaMTOPQueryAction().getReport(sys,reportForm,pageNumber);

	/* Genero el reporte */
	// HTML Report            	
    final HtmlProcessor processor = new HtmlProcessor(report);
    ByteArrayOutputStream ous = new ByteArrayOutputStream(); 
    processor.setFilesystem(new StreamHtmlFilesystem(ous));
    processor.processReport();                
    session.setAttribute("resp",ous.toByteArray());
    session.setAttribute("arrayGraficas", null);   

    /* Muestro Reporte */
		
			/* Contenido del Reporte */
			byte[] b = (byte[])session.getAttribute("resp");
			for(int i = 0; i < b.length; i++) {
			    response.getWriter().write(b[i]);
			}	
		%>
</body>	
</html:html>
