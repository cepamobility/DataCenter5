<%@page language="java"
    errorPage="../exception.jsp"
	import="business.*"
	import="java.io.*"
	import="java.text.*"
	import="java.util.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.util.*"
 	import="communication.administration.user.*" 
	import="communication.util.Filter"
	import="presentation.translator.web.driver.*"
 	import="presentation.translator.web.util.*"
 			
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>
<%
	// hace el include de la página que despliega el box que indica el resultado de la operación
	// el texto que se despliega en el box es el guardado en la variable de sesión bajo la key Msg
	RequestDispatcher dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
	dispatcher.include(request,response);
	// Fin manejo de mensajes
 	
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	Text messages = sys.getText();
	UserSessionData userSessionData = sys.getUserSessionData();

	String check = null;
	check = Util.checkPermission(sys,SecurityConstants.ADMINISTRATOR);	
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
//		 Link al Archivo con el Template para subir Conductores:
		String link = "../datacenter5/bayermx/uploads/mantenimientos.csv";
%>

<html:html>
	<head>
		<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	</head>
	<body>
		<table width="700"  border="0" cellspacing="0" cellpadding="0" align="center"> 
			<tr>
				<td>
					<html:form action="/UploadMaintenance" method="post" enctype="multipart/form-data" >	
						<table width="700"  border="0" align="center" cellpadding="0" cellspacing="0">
	      					<tr>
	        					<td width="19"><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
	          					<td width="672" background="/datacenter/util/imgs/center.jpg" class='guide1'>
	          						<%=messages.getText("vehicle.maintenance.upload") %>
	          					</td>
	        					<td width="19"><img src="/datacenter/util/imgs/right.jpg" width="19" height="30"></td>
	      					</tr>
	      					<tr>
	          					<td width="19" background="/datacenter/util/imgs/left_line.gif">&nbsp;</td>
		      					<td width="672">
		      						<table width="672" cellpadding="0" cellspacing="0" class="texts">
			      						<tr>
			      							<td>&nbsp;</td>
			      						</tr>
			      						<tr>
			      							<th align="left">
			      								<%=messages.getText("generic.uploadmaintenance.file")%>:
			      							</th>
			      							<td align="left">
			      								<html:file property="file" />
			      							</td>			      					      					      		
			      						</tr>
							  			<tr>
											<jsp:include  page="../component/spaceline.jsp" />
							  			</tr>
			      						<tr>
			      							<td>
				      							<html:submit value='<%=messages.getText("module.action.moveup")%>' />
			      								<br/>
			      							</td>
			      							<td align="right">
			      								<a href='<%=link%>'><%=messages.getText("generic.uploadmaintenance.file")%></a>
			      							</td>
			      						</tr>
		      						</table>              
		      					</td>
		      					<td width="19" background="/datacenter/util/imgs/right_line.gif">&nbsp;</td>  	
	      					</tr>
	      					<tr>
	        					<td width="19"><img src="/datacenter/util/imgs/left_base.gif" width="19" height="11"></td>
        						<td width="672" background="/datacenter/util/imgs/center_base.gif"><img src="x" height="1" width="1"></td>
    	    					<td width="19"><img src="/datacenter/util/imgs/right_base.gif" width="19" height="11"></td>
	      					</tr>
						</table>
					</html:form>
				</td>
			</tr>
		</table>
	</body>
</html:html>
<% }
%>

