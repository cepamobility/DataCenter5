<%@page language="java" 
	errorPage="../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.vehicle.tables.*"
	import="presentation.translator.web.util.*"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<html>
<%!
	RequestDispatcher dispatcher; String context;
	SystemAccess sys; Text messages;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	messages=sys.getText();
		
%>	
	<!-- SelectVehicle.jsp -->
	<link REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>CEPA</title>
	</head>
	<body>
	 	<table width="100%" height="90" border="0" cellpadding="0" cellspacing="0" class="default">
		 	<form name="logout" action="../login.jsp" method="post">
	 		</form>
			<form name="Vehicles" action="../vehicle/AssociatedVehicles.jsp"></form>
  			<tr>
    			<td colspan="2">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" valign="top">
      					<tr>
        					<td><jsp:include  page='<%="../topMenu.jsp?home=true" %>'/></td>
      					</tr>
    				</table>	
    			</td><!--end the top�s table -->
  			</tr>
  		</table>
  		<br>
  		<br>	
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  			<tr class="guide1">
      			<td>
	  				<jsp:include  page='../component/utilitybox4top.jsp' />
					<table cellpadding="0" cellspacing="0" align="center" width="560" height="90">
						<tr class="lineone">
							<td align="center">
								<span class="success"><%=messages.getText("event.create.message")%></span>
							</td>
						</tr>
						<tr height="10" class="lineone"><td></td></tr>	
						<tr>
							<td colspan="3" align="right" class="lineone">
								<input type="button" value='<%=messages.getText("generic.messages.backvehicle")%>' onclick="document.forms['Vehicles'].submit()"/>
								<input type="button" value='<%=messages.getText("generic.messages.logout")%>' onclick="document.forms['logout'].submit()"/>
							</td>
						</tr>	
					</table>
					<jsp:include  page="../component/utilitybox4bottom.jsp" />
				</td>
			</tr>
		</table>
	</body>
</html>