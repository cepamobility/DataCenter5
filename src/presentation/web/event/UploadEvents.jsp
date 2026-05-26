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
	import="presentation.translator.web.event.*"
 	import="presentation.translator.web.util.*"
 	import="communication.administration.corporation.tables.*"	
 			
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>
<%
	//hace el include de la página que despliega el box que indica el resultado de la operación
// el texto que se despliega en el box es el guardado en la variable de sesión bajo la key Msg

	RequestDispatcher dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
	dispatcher.include(request,response);
	// Fin manejo de mensajes
 	
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	Text messages = sys.getText();
	UserSessionData userSessionData = sys.getUserSessionData();

	String check = null;
	check = Util.checkPermission(sys,SecurityConstants.UPLOAD_EVENT);	
	if(check != null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
	
		// Link al Archivo con el Template para subir Eventos:		
		String directory, linkDirectory;
		String corporation = ""; // Nombre de la carpeta donde estan los Archivos:

		Integer corporationId = sys.getUserSessionData().getFilter().getCorporationId();
		
		/* Si todas las empresas tuvieran un archivo para subir eventos diferentes
		usaria este codigo:
		CorporationDirectoryData cdd = sys.getCorporationReportFacade().
	findCorporationDirectoryByPrimaryKey(corporationId,sys.getUserSessionData());
	
		if (cdd == null || Util.empty(cdd.getDirectory())){
	corporation = "cepa";
		}else{
	corporation = cdd.getDirectory();
		}
		*/
		
		/*
	Al momento solo se difernecian 2 archivos para subir eventos:
		Bayer (esta creado solo en portugues)
		Cepa (y cepa no esta creado)
		*/
		if (corporationId == CorporationData.BAYER){
	corporation = "bayer";
		}else{
	corporation = "cepa";
		}
		
		
		// directory = "/home/datacenter5/" + corporation + "/accinv/" + country ;	
		directory = SecurityConstants.DATACENTER5_WAR_DIRECTORY + corporation + "/uploads/";
		linkDirectory = SecurityConstants.DATACENTER5_LINK_DIRECTORY  + corporation + "/uploads/";
		
		String language = "es";
		// Filtro por idioma
		if (sys.getUserSessionData().getLenguage().equals("es")){
	language = "es";
		}else if(sys.getUserSessionData().getLenguage().equals("en")){
	language = "en";
		}else if(sys.getUserSessionData().getLenguage().equals("pt")){
	language = "pt";
		}
		String file = (sys.isCorporation(CorporationData.BAYER_MX)) ? "eventsBYMX.xlsx" : "events.xlsx";
		String link = linkDirectory + language+"/"+file;
%>

<html:html>
	<head>
		<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	</head>
	<body>
		<table width="700"  border="0" cellspacing="0" cellpadding="0" align="center"> 
			<tr>
				<td>
					<html:form action="/UploadEvents" method="post" enctype="multipart/form-data" >	
						<table width="700"  border="0" align="center" cellpadding="0" cellspacing="0">
	      					<tr>
	        					<td width="19"><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
	          					<td width="672" background="/datacenter/util/imgs/center.jpg" class='guide1'>
	          						<%=messages.getText("events.messages.upload.addevents") %>
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
			      							<td>
				      								<html:submit value='<%=messages.getText("events.messages.uploadevents")%>' />
			      									<br/>
			      							</td>
			      							

			      							<td align="right">
			      								<a href='<%=link%>'><%=messages.getText("events.messages.upload.file")%></a>
			      							</td>
			      						</tr>
			      						<tr>
										
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

