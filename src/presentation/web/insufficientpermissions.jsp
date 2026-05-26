<%@ page language="java"
    errorPage="exception.jsp"
	import="business.*"
	import="communication.language.*"
	
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%!
Text message; SystemAccess sys;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	message=(Text)session.getAttribute("Text");;
%>

<html>
	<head>
		<title> CEPA </title>
		<link href='<%=request.getContextPath()+"/util/css/estilo.css"%>' rel="stylesheet" type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<style type="text/css">
		</style>
	</head>

	<body>
			<table width="499"  border="0" align="center" cellpadding="0" cellspacing="0" class="table">
  				<tr>
    				<td width="417" class="guide1"><%=message.getText("generic.error.permissions.insufficientexception")%></td>
    			</tr>
    	    </table>
		
	</body>
</html>
