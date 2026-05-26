<%@ page language="java"
	errorPage="../exception.jsp"
	import="business.SystemAccess"
	import="communication.language.Text"
	import="presentation.translator.web.util.Util"
	import="java.util.Collection"
	import="java.util.Iterator"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.administration.user.tables.*"
	import="presentation.translator.web.user.*"
	import="communication.language.tables.*"
	import="communication.administration.user.*"
	import="communication.administration.corporation.tables.*"
	import="java.util.Calendar"
	
		
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%!
	String Reference;	
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	if(sys==null){
		response.sendRedirect("../datacenter/home.jsp?session=1");
	}	
	
	Text messages = sys.getText();
	UserSessionData userSession = null;
	try{
		userSession=sys.getUserSessionData();
	}catch(NullPointerException e){		
		response.sendRedirect("../datacenter/home.jsp?session=1");
		System.out.println(Util.formatDate(Calendar.getInstance().getTime())+				
				": layout.jsp -> Session Time Out ");		
		// throw e;
	}
		
	String check = Util.checkPermission(sys,communication.security.SecurityConstants.ACCESS_TO_SYSTEM);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else { 		
%>
<html>
	<head>
		<title>CEPA - Data Center</title>
		<LINK REL=StyleSheet HREF="/datacenter/util/css/estilo.css" TYPE="text/css">
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.contain {
width: 100%; 
height: 100%;
}
.style1 {
	font-size: 4px;
	color: #CCCCCC;
}
 -->
	</style>
	
	</head>
	<script>
	function doSubmit() {
		form = document.forms['door'];
		if(form.ci.value == '') {
			alert('<%=messages.getText("doorcontrol.error.missingid")%>');
		}else{
			form.submit();
		}
	}
	function putFocus() {
		document.getElementById('ci').focus();
	}
	</script>
	<form name="logout" action="./login.jsp" method="post"></form>
	<body bgcolor="#cccccc" onload="putFocus()">
		<center>
			<form name="door" action="control.jsp" method="post">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  				<tr>
	    				<td valign="top">
	    					<table width="450" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr><td height="50"></td></tr>
	      						<tr>
	      							<td colspan="2">
	      								<img src="./imgs/logoW.gif" width="447" height="155" >
	      							</td>
	      						</tr>
								<tr><td height="50"></td></tr>
	      						<tr>
	        						<td class="accessTitle" colspan="2">
	        							<%=messages.getText("doorcontrol.access.title")%>
	        						</td>
	      						</tr>
								<tr><td height="30"></td></tr>
	      						<tr>
	        						<td align="left" class="accessText" height="20" colspan="2">
	        							<%=messages.getText("doorcontrol.access.ci")%>
	        						</td>
	        					</tr>
								<tr><td height="5"></td></tr>
	        					<tr>
	        						<td colspan="2">
	        							<input type="text" id="ci" name="ci" size="30" height="15" style="font-size: 14;">
	        							<input type="button" onclick="doSubmit();" value='<%=messages.getText("doorcontrol.error.submit")%>' /> 
	        						</td>
	      						</tr>
								<tr><td height="50"></td></tr>
	        					<tr>
	      							<td align="left">
	      								<img src="./imgs/logo_cepa.png" width="100" height="32" >
	      							</td>
	        						<td align="right">
	        							<input type="button" onclick="javascript:document.forms['logout'].submit();" value='<%=messages.getText("generic.messages.logout")%>' width="50" /> 
	        						</td>
	      						</tr>
	    					</table>
	    				</td>
	    			</tr>
				</table>
	  		</form>
		</center>
	</body>
<%	}
%>
</html>
