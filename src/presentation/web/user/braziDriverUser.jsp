<%@page language="java"
	errorPage="../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="business.driver.*"
	import="communication.driver.tables.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.*"
	import="presentation.translator.web.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>

<html:html>

<%! RequestDispatcher dispatcher; 
	Text messages; 
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.ADMINISTRATOR);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		Text messages = sys.getText();
%>
	<head>
		<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	</head>
	<body bgcolor="#FFFFFF">
		<html:form action="/BrazilFleetDriverUserCreate">
			<table>
      			<tr class="linetwo">
        			<td align="right"> </td>
        			<td align="right">
						<html:submit styleClass="input">
							<%=messages.getText("generic.messages.save")%>
						</html:submit>
						<html:cancel styleClass="input">
							<%=messages.getText("generic.messages.cancel")%>
						</html:cancel>
					</td>
      			</tr>
			</table>
		</html:form>
	</body>
<%	}	
%>
</html:html>
