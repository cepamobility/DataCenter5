<%@page language="java"
	import="business.*"
	import="communication.security.SecurityConstants"
	import="java.util.*"
	errorPage="../exception.jsp"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	if(sys.getUserSessionData().checkPermission(SecurityConstants.ADMINISTRATOR)) {
		Collection sended = sys.getFleetReportFacade().getFleetDrivers(new Integer(9405),sys.getUserSessionData());
%>
<html>
	<head>
		<LINK REL=StyleSheet HREF="util/css/estilo.css" TYPE="text/css">
		<link rel="STYLESHEET" type="text/css" href="./css/menu.css"> 
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<style type="text/css">
<!--	body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
		}
		.contain {
			width: 100%; 
			height: 100%;
		}
 -->
		</style>
	</head>
	<body>
	 	<table width="100%" height="90" border="0" cellpadding="0" cellspacing="0" class="default">
<%		Iterator it = sended.iterator();
		int i = 1;
		while(it.hasNext()) {
%>			<tr>
  				<td><%="Enviado "+i+": "+it.next()%></td>
      		</tr>
<%			i++;
		}
%>      					
		</table>
	</body>
<%	}
%>
</html>

