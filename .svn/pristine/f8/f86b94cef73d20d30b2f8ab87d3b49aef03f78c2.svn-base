<%@page language="java" 
	errorPage="../exception.jsp"
	import="business.*"
	import="business.driver.*" 
	import="business.datatype.*" 
	import="communication.datatype.*"
	import="communication.datatype.tables.*"
	import="communication.language.*"
	import="communication.driver.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.security.*"
	import="communication.administration.user.*"
	import="presentation.translator.web.driver.*"
	import="presentation.translator.web.util.*"
	import="java.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>	   

<%! RequestDispatcher dispatcher; String pathMsg;
	Text messages; String pathLanguage;
	DriverData data; Integer id;
	SystemAccess sys;
%>
<%
	// Pagina para que los conductores actualicen sus datos b[asicos de ubicacion geogr[afica.
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData = sys.getUserSessionData();
	String check = null;
	check = Util.checkPermission(sys,SecurityConstants.DRIVER_BAYER_DATA);
	
	DriverData data = null;
	String idDriver = (String)request.getParameter("id");
	if(idDriver != null && Util.isNumber(idDriver)){
		data = sys.getDriverReportFacade().getDriver(new Integer(idDriver), userSessionData);
	}
	
	if(check!=null || idDriver == null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		messages=sys.getText();
		pathLanguage="/util/jsp/language.jsp";
		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
		pathMsg="/util/jsp/message.jsp";
	
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);
		
		Collection stateColl = new LinkedList();
		DataTypeReportFacade dtReportFacade = sys.getDataTypeReportFacade();			
		Iterator iterC = dtReportFacade.findAll(DataTypeConstants.BRASIL_STATE, userSessionData).iterator();
		// stateColl.add(new OptionItem(messages.getText("generic.messages.nodata"),""));
		while(iterC.hasNext()) {
			String value = ((BrasilStateDataType)iterC.next()).getResourceBundleId();
			stateColl.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
		}
		request.setAttribute("stateColl",stateColl);
		
		
%>

	<head>
		<title>Motorista Monsanto</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	</head>
	<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
		<html:form action="/DriverMonsantoUpdateData" >
	    	<br>
         	<p> 
				<label>
			  		<font face="Calibri" color="#000000">Condutor:</font>
			  	</label>
			  	<%=data.getFirstName() + " " + data.getLastName()%> 
			  	<html:hidden property="id" value="<%=data.getId().toString()%>"/>
			</p> 
			<p> 
			  	<label>
			  		<font face="Calibri" color="#000000">Cidade de resid&ecirc;ncia:&nbsp;&nbsp;</font>
			  		<html:text property="cityhome" size="30" maxlength="30" styleClass="input"/>
			  	</label>
			</p>
			<p>
			  	<font face="Calibri" color="#000000">Telefone fixo:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			   	<html:text property="ddd" size="3" maxlength="3" ></html:text>-
			 	<html:text property="phone" size="21" maxlength="30" ></html:text> <br/>
				  		   
			  	Telefone celular:&nbsp;
			  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			   	<html:text property="dddcelular" size="3" maxlength="3" ></html:text>-
			 	<html:text property="phonecelular" size="21" maxlength="30" ></html:text>
			  </font>  
			 </p> 
			 
			<p>
			  <label><font face="Calibri" color="#000000">
			  			Utiliza Ve&iacute;culo da Companhia a Trabalho?</font>
			  </label>
			  
			  <!-- Work Vehicle: 
			  			SIM -> Vehiculo Asginado: JOB CAR
			  			NO  -> Vehiculo Asginado: No Data
			   -->
			  <html:radio property="workVehicle" value="true">
			  	<font face="Calibri" color="#000000"><%=messages.getText("generic.messages.yes")%></font>
			  </html:radio> 
			  <html:radio property="workVehicle" value="false">
			   	<font face="Calibri" color="#000000"><%=messages.getText("generic.messages.no")%></font>
			  </html:radio>			  
			 </p>    
			 
			 <div class="linha" align="center"> 
			  <label>  
			  	<html:submit styleClass="input" style="width:150;height:30">Enviar</html:submit>				 
			  </label> 
			 </div> 
  
</html:form> 

        </td>
        </tr>
        </table>
        </td> 
		<td colspan="2">
			<img src="../menu/imgs/web_bayer_07.jpg" width="200" height="179" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="../menu/imgs/web_bayer_08.jpg" width="17" height="179" alt=""></td>
		<td colspan="2">
			<img src="../menu/imgs/web_bayer_09.jpg" width="200" height="179" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="../menu/imgs/spacer.gif" width="17" height="1" alt=""></td>
		<td>
			<img src="../menu/imgs/spacer.gif" width="148" height="1" alt=""></td>
		<td>
			<img src="../menu/imgs/spacer.gif" width="165" height="1" alt=""></td>
		<td>
			<img src="../menu/imgs/spacer.gif" width="130" height="1" alt=""></td>
		<td>
			<img src="../menu/imgs/spacer.gif" width="35" height="1" alt=""></td>
		<td>
			<img src="../menu/imgs/spacer.gif" width="165" height="1" alt=""></td>
	</tr>
</table>
</div>
<!-- End Save for Web Slices -->
</body>


<% }  %>

</html:html>	   