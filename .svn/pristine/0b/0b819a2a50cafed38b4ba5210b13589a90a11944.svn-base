<%@ page language="java"
	errorPage="../exception.jsp"
	import="business.SystemAccess"
	import="communication.language.Text"
	import="communication.driver.tables.DriverData"
	import="communication.administration.user.UserSessionData"		
	import="presentation.translator.web.util.Util"
	import="business.driver.DriverReportFacade"
	import="java.util.*"		
	import="java.io.File"
	import="java.io.FileInputStream"
	import="java.net.URI"
	import="communication.security.SecurityConstants"
	import="java.io.FileReader"
	import="java.io.BufferedReader"
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
		String id = request.getParameter("ci");
		System.out.println("id --> "+id);
		if(id.startsWith("ñ") && id.endsWith("_")) {
			id = id.substring(1,id.length()-1);
		}
		if(id.startsWith("_") && id.endsWith("?")) {
			id = id.substring(1,id.length()-1);
		}
		if(!Util.empty(id)) {
			DriverReportFacade driverReport = sys.getDriverReportFacade();
			Collection drivers = driverReport.findDriverByCorporationId(id,userSession);
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
	<form name="logout" action="./login.jsp" method="post"></form>
	<form name="back" action="./accesscontrol.jsp" method="post"></form>
	<body bgcolor="#cccccc">
		<center>
			<table width="450" border="0" cellspacing="0" cellpadding="0">
				<tr><td height="50"></td></tr>
				<tr height="150">
	      			<td colspan="2">
    					<img src="./imgs/logoW.gif" width="447" height="155" >
	      			</td>
	      		</tr>
				<tr><td height="50"></td></tr>
<%		switch(drivers.size()) {
			case 0:
%>				<tr>
					<td class="accessTitle"><%=messages.getText("doorcontrol.error.missingdriver")%></td>
				</tr>
<%				break;
			case 1:
				DriverData driver = (DriverData)drivers.iterator().next();
%>
				<tr>
					<td class="accessDriver"><%="<b>"+messages.getText("generic.data.driver") +":</b> "+driver.getFirstName()+" "+driver.getLastName()%></td>
<%				try{
					File file = new File(SecurityConstants.DATACENTER5_WEYERHAUSER_IMAGE_DIRECTORY+driver.getCorporationId()+".jpg");
					FileReader input = new FileReader(file); 				
		        	BufferedReader br = new BufferedReader(input);
					String fileName = "../pictures/"+driver.getCorporationId()+".jpg"; 
%>					<td rowspan="2" align="right"><img src='<%=fileName%>' border="0" width="50" height="80" /></td>
<%				}catch(Exception e) {
					e.printStackTrace();
				}
%>
				</tr>
				<tr><td height="15"></td></tr>
				<tr>
					<td class="accessDriver" colspan="2" valign="middle"><%="<b>"+messages.getText("role.contractors.name") +":</b> "+driver.getDivOrgName(3,1)%></td>
				</tr>
				<tr><td height="15"></td></tr>
<%				String[][] access = driverReport.verifyAccess(driver.getId(), messages,userSession);
				if(Integer.parseInt(access[0][0]) == DriverData.AVAILABLE) {
%>				<tr>
					<td class="accessGreen"  colspan="2" align="center"><%=messages.getText("doorcontrol.access.success")%></td>
				</tr>
<%					for(int i = 0; i < access[1].length; i++) {
%>				<tr height="20">
					<td class="accessText" colspan="2"><span class="red">WARNING</span> - <%=access[1][i]%></td>
				</tr>
<%					}
				}else {
%>				<tr>
					<td class="accessRed"  colspan="2" align="center"><%=messages.getText("doorcontrol.access.forbidden")%></td>
				</tr>
<%					for(int i = 0; i < access[1].length; i++) {
%>				<tr height="20">
					<td class="accessText" colspan="2"><%=" - " + access[1][i]%></td>
				</tr>
<%					}
				}
				break;
			default:
%>				<tr>
					<td class="accessTitle" colspan="2"><%=messages.getText("doorcontrol.error.duplicatedriverid")%></td>
				</tr>
<%		}
%>				<tr><td height="50"></td></tr>
	        	<tr>
	      			<td align="left">
	      				<img src="./imgs/logo_cepa.png" width="100" height="32" >
	      			</td>
	        		<td align="right">
	        			<input type="button" onclick="javascript:document.forms['back'].submit();" value="<%=messages.getText("generic.messages.back")%>" width="50" /> 
	        			<input type="button" onclick="javascript:document.forms['logout'].submit();" value="<%=messages.getText("generic.messages.logout")%>" width="50" /> 
	        		</td>
	      		</tr>
			</table>
		</center>
	</body>
<%		}
	}
%>
</html>
