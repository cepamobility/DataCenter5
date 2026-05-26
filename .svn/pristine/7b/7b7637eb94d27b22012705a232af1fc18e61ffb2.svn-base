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
	import="presentation.translator.web.user.*"	
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
	
	DriverBayerData data = null;
	String idDriver = (String)request.getParameter("id");
	if(idDriver != null && Util.isNumber(idDriver)){
		data = sys.getDriverReportFacade().findDriverBayerByPrimaryKey(new Integer(idDriver), userSessionData);
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
		
%>

<head>
<title>web</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<form name="back" action="./home.jsp">
	<input type="hidden" name="id" value="<%=idDriver%>">
</form>
<form name="logout" action="./login.jsp">
</form>
<!-- Save for Web Slices (web.psd) -->
<div align="center">
<table id="Table_01" width="660" height="523" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="2">
			<img src="./menu/imgs/web_bayer_01.jpg" width="165" height="164" alt=""></td>
		<td>
			<img src="./menu/imgs/web_bayer_02.jpg" width="165" height="164" alt=""></td>
		<td colspan="2">
			<img src="./menu/imgs/web_bayer_03.jpg" width="165" height="164" alt=""></td>
		<td>
			<img src="./menu/imgs/web_bayer_04.jpg" width="165" height="164" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="./menu/imgs/web_bayer_05.jpg" width="17" height="179" alt=""></td>
        <td colspan="3" rowspan="2">
        <table width="443" height="358" background="./menu/imgs/web_bayer_06.jpg" align=center>
        <tr>
        <td width="30">
        </td>
        <td>  
        <br>
        <br>
         	<div class="linha"> 
			  <label><font face="Calibri" color="#000000">Condutor:</font></label>&nbsp;&nbsp;&nbsp;&nbsp;
			  		<%=data.getFullName() %> 			  		
			    <br><br/>
			 </div> 
			 
			 <div class="linha"> 
			  <label><font face="Calibri" color="#000000">
			  				Dados atualizados corretamente.<br/>
							Muito obrigado pela sua colaboração.
			  </font></label>
			  		   
			    <br>
			 </div> 
			    <br>
			    <br>
			    <br>
			 <div class="linha2" align="center"> 
	  			<input type="button" class="input" style="width:150;height:30" value="Volver a modificar" onclick="document.forms['back'].submit();" />				 
	  			<input type="button" class="input" style="width:150;height:30" value="Salir" onclick="document.forms['logout'].submit();" />				 
			 </div> 

        </td>
        </tr>
        </table>
        </td> 
		<td colspan="2">
			<img src="./menu/imgs/web_bayer_07.jpg" width="200" height="179" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="./menu/imgs/web_bayer_08.jpg" width="17" height="179" alt=""></td>
		<td colspan="2">
			<img src="./menu/imgs/web_bayer_09.jpg" width="200" height="179" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="./menu/imgs/spacer.gif" width="17" height="1" alt=""></td>
		<td>
			<img src="./menu/imgs/spacer.gif" width="148" height="1" alt=""></td>
		<td>
			<img src="./menu/imgs/spacer.gif" width="165" height="1" alt=""></td>
		<td>
			<img src="./menu/imgs/spacer.gif" width="130" height="1" alt=""></td>
		<td>
			<img src="./menu/imgs/spacer.gif" width="35" height="1" alt=""></td>
		<td>
			<img src="./menu/imgs/spacer.gif" width="165" height="1" alt=""></td>
	</tr>
</table>
</div>
<!-- End Save for Web Slices -->
</body>


<% }  %>

</html:html>	   