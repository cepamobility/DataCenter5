<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@ page language="java"
    errorPage="exception.jsp"
	import="business.*"
	import="communication.language.*"
%>

<%
	Runtime r = Runtime.getRuntime();
	try {
    	String line;
     	Process p = r.exec("/bin/sh /usr/local/apache/conf/ar2.sh");
//    	BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
//     	while ((line = input.readLine()) != null) {
//       		System.out.println(line);
//     	}
//     	input.close();
		response.sendRedirect("home.jsp");
   	} catch (Exception err) {
     	err.printStackTrace();
   	}
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
		<form name="back" method="post" action="home.jsp">
			<table width="709" height="386" border="0" align="center" cellpadding="0" cellspacing="0"  class="tablelogin">
			  	<tr>
    				<td height="100" colspan="3">&nbsp;</td>
	  			</tr>
  				<tr>
    				<td width="352"height="24" align="right" class="txtlogin">Matenimiento</td>
    				<td width="5"></td>
    				<td width="352">
    				</td>
  				</tr>
			</table>
		</form>
	</body>
</html>
