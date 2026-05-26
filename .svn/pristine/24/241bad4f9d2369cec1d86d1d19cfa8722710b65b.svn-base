<%@ page language="java"
    errorPage="exception.jsp"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	String text = "Type in your corporate e-mail";
	if(request.getParameter("system") != null) {
	    if(request.getParameter("system").equals("DC5")) {
		    response.sendRedirect("https://www.cepadc.com");
	    }else {
		    response.sendRedirect("http://datacenter.cepasafedrive.com");
	    }
	}else {
	    if(request.getParameter("lan").equals("es")) {
	        text = "Ingrese su e-mail corporativo";
	    }else if(request.getParameter("lan").equals("pt")) {
	        text = "Ingresse seu e-mail corporativo";
	    }
%>
<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<title>CEPA</title>
		<style type="text/css">
		<!--
		body {
			background-color: #dddddd;
		}
		.style1 {
			font-size: 11px;
			font-family: Verdana, Arial, Helvetica, sans-serif;
		}
		.style2 {
			font-family: Verdana, Arial, Helvetica, sans-serif;
			font-size: 12px;
			font-weight: bold;
			color: #53688A;
		}
		-->
		</style>
	</head>

	<body>
		<center>
			<html:form action="/CEPARedirect">
				<table width="653" height="350" border="0" align="center" cellpadding="0" cellspacing="0" background="/datacenter/util/imgs/fundo01.jpg">
		  			<tr>
		    			<td align="center" valign="top"><br />
							<br/>
							<br/>
							<br/>
							<br/>
		                  	<table width="409" height="32" border="0" cellpadding="0" cellspacing="2">
		      					<tr>
		        					<td align="center"><span class="style2"><%=text%></span></td>
		      					</tr>
		      					<tr>
		        					<td width="418" align="center">
		        						<html:text property="mail" style="width: 200;" styleClass="style1" />
		          						<img src="/datacenter/util/imgs/bt01.gif" width="66" height="24" align="absmiddle" onclick="javascript:document.forms['CEPARedirect'].submit();"/>
		          					</td>
		      					</tr>
		    				</table>
		    			</td>
		  			</tr>
				</table>
			</html:form>
		</center>
	</body>
</html:html>
<%	}
%>
