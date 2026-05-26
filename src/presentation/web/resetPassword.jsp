<%@ page language="java"
    errorPage="exception.jsp"
	import="presentation.translator.web.user.*"	
	import="org.apache.struts.action.*"	
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>
<%! RequestDispatcher dispatcher; String context; String pathMsg; String pathGarbagecolector;%>
<%
	context=request.getContextPath();
	pathMsg="/util/jsp/message.jsp";
	
	dispatcher=request.getRequestDispatcher(pathMsg);
	dispatcher.include(request,response);
	ResetPasswordForm form = new ResetPasswordForm();
	session.setAttribute("ResetPasswordForm",form);
	String message1 = "";
	if(!presentation.translator.web.util.Util.empty(request.getParameter("message"))) {
	    if(Integer.parseInt(request.getParameter("message")) == 1) {
	        message1 = "E-mail no pode ser vazio - The e-mail can't be empty";
	    }else {
	        message1 = "E-mail nao cadastrado - E-mail not founded";
	    }
	}
	
	// Idioma:
	String language = (String)request.getAttribute("language");
	if (language == null) language = "en";
	String message2 = "Insert e-mail&nbsp;";
	String message3 = "";
	if (language == "es"){
		// Español
		message2 = "Introduzca su e-mail";
		message3 = "(o ID de la corporación: ID@corporacion.com)";				
	}else if (language == "pt"){
		// Portugues
		message2 = "Insira seu e-mail&nbsp;";
		message3 = "(ou ID da Corporação: ID@corporação.com)";
	}else{
		// Ingles;
		message2 = "Enter your e-mail&nbsp;";
		message3 = "(or corporation ID: ID@corporation.com)";
	}
%>

	<head>
		<title> CEPA </title>

		
		<link href='<%=context+"/util/css/estilo.css"%>' rel="stylesheet" type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"><style type="text/css">
		
		<!--
		body {
			background-color: #DDDDDD;
		}
		-->
		</style>

	</head>

	<body>
		<html:form action="/Resetpassword" method="post">
			<table width="700" height="386" border="0" align="center" cellpadding="0" cellspacing="0"  class="tablelogin">
			  	<tr>
    				<td height="120"  class="red" align="center" valign="bottom"><%=message1%></td>
	  			</tr>
				<tr>
    				<td height="20" class="txtlogin" align="center">    				
    					<%=message2%>
    					<html:text property="email" styleClass="input"/>
    				</td>
  				</tr>
  				<tr>
    				<td height="20" class="txtlogin" align="center">    				
    					<%=message3%>
    				</td>
  				</tr>
			  	<tr>
    				<td height="50" align="center" valign="bottom">&nbsp;</td>
	  			</tr>
				<tr>
    				<td height="35" class="txtlogin" align="center">
    					<html:radio property="language" styleClass="input" value="es"/>Spanish&nbsp;
    					<html:radio property="language" styleClass="input" value="en" />English&nbsp;
    					<html:radio property="language" styleClass="input" value="pt"/>Portuguese
    				</td>
  				</tr>
				<tr>
    				<td align="center" valign="top">
            			<html:submit styleClass="input">Send</html:submit>
            			<html:cancel styleClass="input">Cancel</html:cancel>
           			</td>
  				</tr>
			</table>
		</html:form>
	</body>
</html:html>