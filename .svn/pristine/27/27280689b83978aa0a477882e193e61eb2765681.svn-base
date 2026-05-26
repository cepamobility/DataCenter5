<%@ page language="java"
	isErrorPage="true"
	import="business.*"
	import="communication.language.*"
	import="communication.exception.*"
	import="presentation.translator.web.util.*"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%!
	Text messages;
	SystemAccess sys;
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	if(sys != null) {
		messages = sys.getText();

%>

<html>
	<head>
		<title> CEPA </title>
		<link href="../util/css/estilo.css" rel="stylesheet" type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<style type="text/css">
		</style>
	</head>

	<script type="text/javascript" src='<%="./util/js/uiframework.js"%>'>
	</script>

	<body bgcolor="#FFFFFF">	
	 	<table width="100%" height="90" border="0" cellpadding="0" cellspacing="0" class="default">
		 	<form name="logout" action="../login.jsp" method="post">
	 		</form>
			<form name="vehicles" action="../vehicle/AssociatedVehicles.jsp" method="post"></form>		
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
		<table width="700" align='center' cellpadding="0" cellspacing="0">
			<tr class="guide1">
				<jsp:include  page='<%="../component/titlecomponent.jsp?colspan=5&title="+messages.getText("generic.messages.drivermessage1")%>' />
			</tr>
	   		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
	  		</tr>
      		<tr>
        		<td>
        			<html:form action="/driver/DriverSendMessage">
						<jsp:include  page="../component/utilitybox4top.jsp" />
	 					<table width="100%">		    			
							<tr height="70px">
								<td width="100%" class="lineone" align="left">
									<%=messages.getText("generic.messages.drivermessage2")%>
								</td>
							</tr>
							<tr>	
								<td width="100%" align="left" class="lineone">
									<html:textarea property="body" cols="180" rows="10" styleClass="input"> 
									</html:textarea>
								</td>
							</tr>
							<tr class="lineone">
								<td align="right" class="lineone" width="100%">
									<input type="button" value='<%=messages.getText("generic.messages.send")%>' onclick="javascript:document.forms['ErrorReportForm'].submit();">
									<input type="button" value='<%=messages.getText("generic.messages.back")%>' onclick="javascript:document.forms['vehicles'].submit();">
								</td>
							</tr>
						</table>
						<jsp:include  page="../component/utilitybox4bottom.jsp" />
					</html:form>
				</td>
			</tr>
		  	<tr> 
		    	<jsp:include  page="../component/spaceline.jsp" />
		  	</tr>
		</table>
		<br>
		<br>
	</body>
</html>
<%
	}
%>