<%@page language="java" 
	errorPage="../exception.jsp" 
	import="java.util.*"
	import="business.*"
	import="business.vehicle.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.fleet.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.vehicle.*" 	
 	import="communication.administration.user.*" 	
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	 	<title> CEPA International</title>
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

<%!
	Text messages; 
	SystemAccess sys;
	String attribute; 
	String value;
	UserSessionData userSessionData; 
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();
	messages = sys.getText();
	session.setAttribute("showAlert","1");
		
%>		
	<body>
	 	<table width="100%" height="90" border="0" cellpadding="0" cellspacing="0" class="default">
			<form name="logout" action="../login.jsp" method="post">
	 		</form>
			<form name="home" action="../layout.jsp" method="post">
			</form>		
  			<tr>
    			<td colspan="2">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" valign="top">
      					<tr>
        					<td><jsp:include  page='<%="../topMenu.jsp?home=true" %>' /></td>
      					</tr>
    				</table>	
    			</td><!--end the top�s table -->
  			</tr>
  		</table>
  		<br>
  		<br>	
		<table width="768" border="0" align="center" cellpadding="0" cellspacing="0">
		  	<tr>
		    	<td width="19" height="30"><img src="../util/imgs/left_top_search.jpg" width="19" height="30" /></td>
		    	<td width="562" height="30">&nbsp;</td>
		    	<td width="19" height="30"><img src="../util/imgs/right_top_search.jpg" width="19" height="30" /></td>
		  	</tr>
		  	<tr>
		    	<td background="../util/imgs/left_line_search.jpg">&nbsp;</td>
		    	<td align="center" class="lineone">
		    		<table width="100%" height="500" border="0" cellpadding="0" cellspacing="0">
		    			<tr>
		    				<td class="required"><h1><%=messages.getText("generic.driver.alert")%></h1></td>
		    			</tr>
<%		if(Boolean.parseBoolean(request.getParameter("expiry"))) {
%>		 				<tr>
							<td height="50"></td>
						</tr>
						<tr>
							<td align="left" class="input"><%=messages.getText("driver.alert.licenseexpiry")+": "+Util.formatDate(userSessionData.getDriverData().getLicenseExpiry())%></td>
						</tr>   			
<%		}
%>		 				<tr>
							<td height="50">
								<input type="button" value="<%=messages.getText("generic.messages.accept")%>" />
							</td>
						</tr>
		    		</table>
	    		<td background="../util/imgs/right_line_search.jpg">&nbsp;</td>
	  		</tr>
	  		<tr>
	    		<td width="19" height="11"><img src="../util/imgs/left_base_search.jpg" width="19" height="11" /></td>
	    		<td width="562" height="11" background="../util/imgs/center_base_search.jpg"><img src="x" width="1" height="1" /></td>
	    		<td width="19" height="11"><img src="../util/imgs/right_base_search.jpg" width="19" height="11" /></td>
		  	</tr>
		</table>
	</body>
</html>
