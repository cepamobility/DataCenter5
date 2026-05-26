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

	<body>
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
				<td align="left" class="texts" colspan="3">
					<jsp:include  page="../component/utilitybox4top.jsp" />
					<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
						<tr class="lineone">
							<td align="left" class="lineone"><%=messages.getText("generic.messages.drivermessage3")%></td>
						</tr>
						<tr class="lineone">
							<td align="right" class="lineone">
								<input type="button" value='<%=messages.getText("generic.messages.back")%>' onclick="javascript:document.forms['vehicles'].submit();">
							</td>
						</tr>
					</table>			
     					<jsp:include page="../component/utilitybox4bottom.jsp"/>
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