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
 	import="communication.administration.corporation.tables.*"
 	import="communication.administration.user.*" 
	import="communication.util.Filter"
	import="presentation.translator.web.vehicle.*"
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
	check = Util.checkPermission(sys,SecurityConstants.UPLOAD_VEHICLE);	
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
	
	
		// Link al Archivo con el Template para subir Vehiculos:
	
		String directory = "../datacenter5/cepa/uploads/";
		String language = "es";
		// Filtro por idioma
		if (sys.getUserSessionData().getLenguage().equals("es")){
	language = "es";
		}else if(sys.getUserSessionData().getLenguage().equals("en")){
	language = "en";
		}else if(sys.getUserSessionData().getLenguage().equals("pt")){
	language = "pt";
		}
		String file = "vehicles.txt";
		if (sys.getUserSessionData().getFilter().getCorporationId().equals(new Integer(CorporationData.KLABIN))){
	file = "vehicles-Klabin.txt";
		}else if (sys.getUserSessionData().getFilter().getCorporationId().equals(new Integer(CorporationData.YOLI))){
	file = "vehicles-Yoli.txt";			
		}
		String link = directory + language+"/"+file;
%>

<html:html>
	<head>
		<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	</head>
	<body>
		<table width="700"  border="0" cellspacing="0" cellpadding="0" align="center"> 
			<tr>
				<td>
					<html:form action="InsertVehicles" method="post" enctype="multipart/form-data" >	
						<table width="700"  border="0" align="center" cellpadding="0" cellspacing="0">
	      					<tr>
	        					<td width="19"><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
	          					<td width="672" background="/datacenter/util/imgs/center.jpg" class='guide1'>
	          						<%=messages.getText("vehicles.messages.upload.addvehicles") %>
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
			      								<%=messages.getText("generic.supportmaterial.reports.file")%>:
			      							</th>
			      							<td align="left">
			      								<html:file property="file" />
			      							</td>			      					      					      		
			      						</tr>
							  			<tr>
											<jsp:include  page="../component/spaceline.jsp" />
							  			</tr>
			      						<tr>
			      							<th align="left">
			      								<%=messages.getText("generic.driver.comment")%>:
			      							</th>
			      							<td align="left"> 
												<html:textarea property="notes" cols="50" />
											</td>											
										</tr>
							  			<tr>
											<jsp:include  page="../component/spaceline.jsp" />
							  			</tr>
			      						<tr>
			      							<td>
				      								<html:submit value='<%=messages.getText("vehicles.messages.uploadvehicles")%>' />
			      							</td>
			      							<br/>
			      							<td align="right">
			      								<a href='<%=link%>'><%=messages.getText("vehicle.messages.upload.file")%></a>
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

