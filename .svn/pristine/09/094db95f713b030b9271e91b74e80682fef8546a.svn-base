<%@page language="java"
    errorPage="../../exception.jsp"
	import="business.*"    
	import="communication.language.*"	
	import="java.util.ArrayList"
	import="business.activity.ActivityReportFacade"	
	import="communication.activity.tables.*"
	import="communication.administration.user.tables.*"
 	import="communication.administration.user.*" 
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>

<html:html>

<head>
<SCRIPT TYPE="text/javascript">
function popupform(href){	
	var newWindow = window.open(href, '', 'height=400,width=600,scrollbars=yes,left=(screen.width  - 600)/2;');	
	return true;
}
</SCRIPT>


</head>

<%
	Text messages; SystemAccess sys;
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	messages = sys.getText();
	
	String idActivity = request.getParameter("idActivity");
	String idDriver = request.getParameter("idDriver");
%>

<body>

<form name="PrintForm">
<div id="print">

<%
	boolean isPrint = false;
	//Si es para imprimir:
	String printParameter = request.getParameter("print");	
	isPrint = (printParameter != null &&
		Boolean.parseBoolean(printParameter.trim()))?true:false;

	/* Contenido del Reporte */
	byte[] b = (byte[])session.getAttribute("resp");
	for(int i = 0; i < b.length; i++) {
	    response.getWriter().write(b[i]);
	}
	/* Fin contenido del Reporte */
	
	
	// Invoco impresion si corresponde:	
	if (isPrint){
		StringBuffer printForm = null;
		printForm = new StringBuffer();
		printForm.append("<script>window.print();</script>");
		byte[] cierroHtml = printForm.toString().getBytes();
		for(int i = 0; i < cierroHtml.length; i++) {
		    response.getWriter().write(cierroHtml[i]);			
		}
	}

	
%>

</div>	
</form>

<%
	if (session.getAttribute("output").equals("HTML") && 
		!(request.getParameter("print") != null && 
		 Boolean.getBoolean(request.getParameter("print"))) ) {
	
		session.removeAttribute("output");	
%>

	<table>
	
		<tr>
	
			<td align="right"><font size="2" face="Arial, Helvetica, sans-serif">			
				<input type="hidden" name="output" value="PDF" />
				<input styleClass="input" type="button" value='<%=messages.getText("generic.messages.printversion")%>' 
					onclick="popupform('<%="/datacenter/activity/schedule/reportResultOldReport.jsp?idDriver="+idDriver+"&idActivity="+idActivity+"&print=true"%>');" />
				</font>
			</td>
	
			<html:form action="/ActivityReportOld" >	
					<html:hidden property="output" value="PDF" />
					<html:hidden property="idActivity" value='<%=idActivity %>' />			
					<html:hidden property="idDriver" value='<%=idDriver %>' />						
					<td align="left">			
						<input type="submit" styleClass="input" value='<%=messages.getText("generic.messages.PDF")%>' />
					</td>					
			</html:form>

		</tr>
	</table>
	

<% 
	} 
%>


<%@ include file = '../../util/jsp/googleanalytics.jsp' %>	


</body>	
</html:html>
