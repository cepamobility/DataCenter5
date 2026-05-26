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
	<title>Bayer Driver Data</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- *************************JCalendar***********************************-->
 <!-- calendar stylesheet -->
  	<link rel="stylesheet" type="text/css" media="all" href="/datacenter/component/jscalendar-1.0/skins/aqua/theme.css" title="win2k-cold-1" />

  <!-- main calendar program -->
  	<script type="text/javascript" src="/datacenter/component/jscalendar-1.0/calendar.js"></script>
  	<script type="text/javascript" src='/datacenter/js/showHide.js' ></script>
	<script type="text/javascript" src="/datacenter/component/jscalendar-1.0/lang/calendar-pt.js"></script>
  <!-- the following script defines the Calendar.setup helper function, which makes
       adding a calendar a matter of 1 or 2 lines of code. -->
  	<script type="text/javascript" src="/datacenter/component/jscalendar-1.0/calendar-setup.js"></script>
  
  <!-- **********************end JCalendar ********************************* -->
  <!-- language for the calendar-->
	<script type="text/javascript">
		function doSubmit() {
			var form = document.forms['DriverBayerUpdateForm'];
			if(form.date.value == '' || form.month.value == '' || form.year.value == ''
					|| form.email.value == '' || form.city.value == '' || form.state.selectedIndex == 0 || form.phone.value == '') {
				alert('Digite todos os dados');
			}else {
				form.submit();
			}
		}
	</script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
	<div align="center">
		<table id="Table_01" width="660" height="523" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td colspan="2">
					<img src="../menu/imgs/web_bayer_01.jpg" width="165" height="164" alt="">
				</td>
				<td>
					<img src="../menu/imgs/web_bayer_02.jpg" width="165" height="164" alt="">
				</td>
				<td colspan="2">
					<img src="../menu/imgs/web_bayer_03.jpg" width="165" height="164" alt="">
				</td>
				<td>
					<img src="../menu/imgs/web_bayer_04.jpg" width="165" height="164" alt="">
				</td>
			</tr>
			<tr>
				<td>
					<img src="../menu/imgs/web_bayer_05.jpg" width="17" height="179" alt="">
				</td>
        		<td colspan="3" rowspan="2">
        			<table width="443" height="358" background="../menu/imgs/web_bayer_06.jpg" align=center>
        				<tr>
        					<td width="30"></td>
        					<td>
						         <html:form action="/Driverbayerupdatedata" >
							        <br>
					    	     	<p> 
									 	<label><font face="Calibri" color="#000000">Condutor:</font></label>
								  		<%=data.getFullName() %> 
								  		<html:hidden property="id" value="<%=data.getId().toString()%>"/>
								    </p> 
									<p>
									 	<label><font face="Calibri" color="#000000">Data vencimento CNH::</font></label>
								        <jsp:include  page='<%="../component/dateComponent.jsp?index=1&day=date&month=month&year=year&form=DriverBayerUpdateForm" %>' />
									</p>
								 	<p> 
								  		<label> 
								  			<font face="Calibri" color="#000000">E-mail Gerente Direto:</font>
								  			<html:text property="email" size="30" maxlength="30" styleClass="input"/>
								  		</label>
								 	</p>
								 	<p> 
								  		<label> 
								  			<font face="Calibri" color="#000000">Cidade onde atua:</font>
								  			<html:text property="city" size="30" maxlength="30" styleClass="input"/>
								  		</label>
								  	</p> 
								 	<p> 
								  		<label><font face="Calibri" color="#000000">Estado onde atua:</font></label>
								  		<html:select property="state" styleClass="input" style="width:120;">
								    		<html:options collection="stateColl" property="value" labelProperty="label" styleClass="input"/>
								     	</html:select>
								 	</p> 
								 	<p>
										<font face="Calibri" color="#000000">Telefone com DDD:
								   			<html:text property="ddd" size="3" maxlength="3" ></html:text>-
								 			<html:text property="phone" size="21" maxlength="30" ></html:text> <br/>
								  		</font>  
								 	</p> 
								 	<div class="linha" align="center"> 
								 		<label>  
								  			<input type="button" class="input" style="width:150;height:30" value="Enviar" onclick="doSubmit();"/>				 
								  		</label> 
									</div> 
					  			</html:form> 
        					</td>
        				</tr>
        			</table>
        		</td> 
				<td colspan="2">
					<img src="../menu/imgs/web_bayer_07.jpg" width="200" height="179" alt="">
				</td>
			</tr>
			<tr>
				<td>
					<img src="../menu/imgs/web_bayer_08.jpg" width="17" height="179" alt="">
				</td>
				<td colspan="2">
					<img src="../menu/imgs/web_bayer_09.jpg" width="200" height="179" alt="">
				</td>
			</tr>
			<tr>
				<td>
					<img src="../menu/imgs/spacer.gif" width="17" height="1" alt="">
				</td>
				<td>
					<img src="../menu/imgs/spacer.gif" width="148" height="1" alt="">
				</td>
				<td>
					<img src="../menu/imgs/spacer.gif" width="165" height="1" alt="">
				</td>
				<td>
					<img src="../menu/imgs/spacer.gif" width="130" height="1" alt="">
				</td>
				<td>
					<img src="../menu/imgs/spacer.gif" width="35" height="1" alt="">
				</td>
				<td>
					<img src="../menu/imgs/spacer.gif" width="165" height="1" alt="">
				</td>
			</tr>
		</table>
	</div>
<!-- End Save for Web Slices -->
</body>


<% }  %>

</html:html>	   