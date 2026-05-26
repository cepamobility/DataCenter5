<%@ page language="java"
    errorPage="exception.jsp"
	import="business.*"
	import="communication.language.*"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>


<html>
	<head>
		<title> CEPA </title>
		<link href='<%=request.getContextPath()+"/util/css/estilo.css"%>' rel="stylesheet" type="text/css">
		<link rel="STYLESHEET" type="text/css" href="./css/menu.css"> 
	</head>

	<body>
		<form name="error" method="post" action="./login.jsp" >
			<table width="499"  border="0" align="center" cellpadding="0" cellspacing="0" class="table">
	  			<tr>
    				<td><img src='<%=request.getContextPath()+"/util/imgs/topo_login.jpg"%>' height="90" width="499"></td>
			  	</tr>
  				<tr>
		        	<td height="10" ><img src="x" width="1" height="1"></td>
  				</tr>
  				<tr>
    				<th><div align="left" class="style2" ><%=Text.getMessage("generic.error.login")%></div></th>
  				</tr>
  				<tr>
   					<th><div align="left" class="style2" ><%=Text.getMessage("generic.error.login.retry")%><a onClick="javascript:document.forms[0].submit()" class="cursor"><%=Text.getMessage("generic.messages.here")%></a></div></th>
  				</tr>
			</table>
		</form>
	</body>
</html>
