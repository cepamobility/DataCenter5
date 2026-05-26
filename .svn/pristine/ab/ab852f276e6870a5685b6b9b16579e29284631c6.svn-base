<%@page language="java" 
	errorPage="../../exception.jsp"
	import="java.util.ArrayList"
	import="business.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.activity.schedule.*"
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
	int pageNumber = Integer.parseInt(request.getParameter("page"));
	
	ReportCommentaryDriveForm reportForm = new ReportCommentaryDriveForm(
			request.getParameter("divOrg11"),request.getParameter("divOrg12"),request.getParameter("divOrg13"),
	        request.getParameter("divOrg21"),request.getParameter("divOrg22"),request.getParameter("divOrg23"),
	        request.getParameter("divOrg31"),request.getParameter("divOrg32"),request.getParameter("divOrg33"),
	        request.getParameter("country1"),request.getParameter("country2"),request.getParameter("country3"));
	reportForm.setFromday(request.getParameter("fd"));
	reportForm.setFrommonth(request.getParameter("fm"));
	reportForm.setFromyear(request.getParameter("fy"));
	reportForm.setToday(request.getParameter("td"));
	reportForm.setTomonth(request.getParameter("tm"));
	reportForm.setToyear(request.getParameter("ty"));
	reportForm.setOutput(request.getParameter("o"));
	reportForm.setState(request.getParameter("s"));
	reportForm.setVehicleAllowed(request.getParameter("vt"));
	reportForm.setType(request.getParameter("type"));
	reportForm.setCommentaryDriveType(request.getParameter("cdt"));
	reportForm.setCommentaryDriveKind(request.getParameter("cdk"));
	reportForm.setOneCommentaryDrive(request.getParameter("ocd"));
	reportForm.setTwoCommentaryDrive(request.getParameter("tcd"));
	reportForm.setMoreThanTwoCommentaryDrive(request.getParameter("mttcd"));
	reportForm.setDriverState(request.getParameter("ds"));
	reportForm.setWithCommentaryDrive(request.getParameter("wcd"));
	reportForm.setOutput("HTML");
	
	JFreeReportBoot.getInstance().start();
	JFreeReport report = new ReportCommentaryDriveAction().getReport(sys,reportForm,pageNumber);

	/* Genero el reporte */
	// HTML Report            	
    final HtmlProcessor processor = new HtmlProcessor(report);
    ByteArrayOutputStream ous = new ByteArrayOutputStream(); 
    processor.setFilesystem(new StreamHtmlFilesystem(ous));
    processor.processReport();                
    session.setAttribute("resp",ous.toByteArray());
    session.setAttribute("arrayGraficas", null);   

    /* Muestro Reporte */
	
	/* Titulo con Logo */
	boolean conLogo = true;
    String title = (reportForm.getCommentaryDriveKind().equals("datatype.scheduledactivitytype.commentarydriverisk")) ? "query.activity.commentarydriverisk.result" : "query.activity.commentarydrive.result";
	String titulo = presentation.report.ReportUtil.textToHtml(messages.getText(title));
	String subtitulo = "";
	StringBuffer logoTitulo;
	if (conLogo && (titulo != null))
	{
		// Si tiene logo de CEPA y titulo (y tal vez logo de la compañía lo muestro 
		// Para el logo del cliente se puede parametrizar recibiendo el idCorp
		// y mostrando la imagen que corresponda segun el idCorp.jpg
		logoTitulo = new StringBuffer(
			"<div align='left'>	" +
			"	<table >" +
			"		<tr>" +
			"			<td align='right' width='90' >" +
			"				<!-- Logo CEPA -->" +
			"				<img src='/datacenter/util/imgs/logoCEPA.png' width='70px' height='60' alt='CEPA International'/>" +
			"			</td>" +
			"			<td align='left' valign='center' width='500'>" +
			"				<table> <tr><td align='left'><!--  Titulo Reporte --> <h3>" + 
			 				titulo +
			((subtitulo != null && !subtitulo.equals(""))? "</h3></td></tr><tr><td align='right'><h3>"+subtitulo :"") +
			"			</h3></td></tr></table><td align='left' >" +
			"				<!-- Logo Cliente --> ");
			if (sys.getUserSessionData().getFilter().getCorporationId().equals(new Integer(1))){
				logoTitulo.append("<img src='/datacenter/util/imgs/monsanto_logo.gif' width='100px' height='60' alt='CEPA International'/>");
			}
			logoTitulo.append(
			"				<img src='/datacenter/util/imgs/monsanto_logo.gif' width='100px' height='60' alt='CEPA International'/>" +
			"			</td>" +
			"		</tr><tr><td colspan='3'>") ;
			byte[] mostrarTitulo = logoTitulo.toString().getBytes();
			for(int i = 0; i < mostrarTitulo.length; i++) {
			    response.getWriter().write(mostrarTitulo[i]);			
			}
	}				
	/* Contenido del Reporte */
	byte[] b = (byte[])session.getAttribute("resp");
	for(int i = 0; i < b.length; i++) {
	    response.getWriter().write(b[i]);
	}
	
	if (conLogo && (titulo != null))
	{
		logoTitulo = null;
		logoTitulo = new StringBuffer();
		logoTitulo.append("	</td></tr></table></div>");
		byte[] mostrarTitulo = logoTitulo.toString().getBytes();
		for(int i = 0; i < mostrarTitulo.length; i++) {
		    response.getWriter().write(mostrarTitulo[i]);			
		}
	}


	if (session.getAttribute("output").equals("HTML")) 
	{
		session.removeAttribute("output");	
%>
	<html:form action="/ActivityReportNew" >	
			<html:hidden property="output" value="PDF" />
			<html:hidden property="idActivity" value='<%=request.getParameter("idActivity") %>' />			
			<html:hidden property="idDriver" value='<%=request.getParameter("idDriver") %>' />						
			<table>
			<tr>
			<td align="right">			
			<html:submit styleClass="input"> <%=messages.getText("generic.messages.PDF")%> </html:submit>
			</td>
			</tr>
			</table>
	</html:form>

<% 
	} 
%>
</body>	
</html:html>
