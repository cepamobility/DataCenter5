<%@page import="communication.driver.tables.DriverPointList"%>
<%@page language="java" 
	errorPage="../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="business.fleet.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.fleet.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
%>

<%
	Text messages; 
	SystemAccess sys;
	
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	if(sys.isCorporation(CorporationData.BAYER_MX)) {
		messages = sys.getText();
		String check = Util.checkPermission(sys,SecurityConstants.QUERY);
		if(check != null) {
	response.sendRedirect(request.getContextPath()+check);
		}else {
	DriverPointList resume = sys.getDriverReportFacade().getDriverPointResume(sys.getUserSessionData());
%>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<style type="text/css">	</style>
	<head>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<META http-equiv="Expires" content="-1">		
	</head>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> 
	<script language="javascript" type="text/javascript">
	function openDiv(val) {
		document.getElementById('black').style.display = 'none';
		document.getElementById('red').style.display = 'none';
		document.getElementById('yellow').style.display = 'none';
		document.getElementById('green').style.display = 'none';
		document.getElementById(val).style.display = 'block';
	}
	function openDriver(id) {
		var form = document.forms['driver'];
		form.id.value = id;
		form.submit();
	}
	</script>
	<div id="contentBayerReport">
		<div class="titleBayer"><%=messages.getText("query.points.driverresume")%></div>
		<form action="layout.jsp" name="driver">
			<input type="hidden" name="refer" value="/driver/DriverView.jsp"/>
			<input type="hidden" name="id" />
		</form>
		<form name="values">
			<table width="900" cellspacing="0" cellpadding="0" align="center">
<%
	int[] values = resume.getPercentages();
%>				<tr height="60">
<%
	if(values[0] > 0) {
%>					<th width="<%=values[0]+"%"%>" class="backBlack" align="center" valign="middle">
						<a href="javascript:openDiv('black');" style="color: #FFFFFF; font-family: Verdana; font-size: 10px; padding-left: 5px; padding-right: 5px" align="center">
							<%=values[0]+"%"%>
						</a>
					</th>
<%
	}
		if(values[1] > 0) {
%>					<th width="<%=values[1]+"%"%>" class="backRed" align="center" valign="middle">
						<a href="javascript:openDiv('red');" style="color: #FFFFFF; font-family: Verdana; font-size: 10px; padding-left: 5px; padding-right: 5px">
							<%=values[1]+"%"%>
						</a>
					</th>
<%
	}
		if(values[2] > 0) {
%>					<th width="<%=values[2]+"%"%>" class="backYellow" align="center" valign="middle">
						<a href="javascript:openDiv('yellow');" style="color: #000000; font-family: Verdana; font-size: 10px; padding-left: 5px; padding-right: 5px">
							<%=values[2]+"%"%>
						</a>
					</th>
<%
	}
		if(values[3] > 0) {
%>					<th width="<%=values[3]+"%"%>" class="backGreen" align="center" valign="middle">
						<a href="javascript:openDiv('green');" style="color: #000000; font-family: Verdana; font-size: 10px; padding-left: 5px; padding-right: 5px">
							<%=values[3]+"%"%>
						</a>
					</th>
<%
	}
%>				</tr>
			</table>
			<div id="black" style="display: none;">
				<table width="900" cellspacing="0" cellpadding="0" align="center">
					<tr>
						<jsp:include  page="../component/spaceline.jsp" />
      				</tr>
					<tr>
						<th colspan="2" class="titleReport" align="left">Riesgo Muy Alto</th>
					</tr>
					<tr>
						<jsp:include  page="../component/spaceline.jsp" />
      				</tr>
      				<tr height="25">
	               	 	<td width='50%' class="guide2" align="center">Conductor</td>
	               	 	<td width='50%' class="guide2" align="center">Plan de Mitigación</td>
					</tr>
<%
	Iterator<Object[]> it = resume.getBlack().iterator();
		boolean lineone = true;
		while(it.hasNext()) {
	Object[] data = it.next();
	String advance = (data[2] == null) ? "No tiene acciones asociadas" : data[2] +"% de acciones realizadas (venc. "+Util.formatDate((Date)data[3])+")";
%>					<tr class='<%=(lineone) ? "lineone" : "linetwo"%>' height="25">
						<td width="50%">
							<a href="javascript:openDriver(<%=String.valueOf(data[0])%>);"><%=data[1]%></a>
						</td>
						<td>
							<%=advance%>
						</td>
					</tr>
<%
	lineone = !lineone;
		}
%>				</table>
			</div>
			<div id="red" style="display: none;">
				<table width="900" cellspacing="0" cellpadding="0" align="center">
					<tr>
						<jsp:include  page="../component/spaceline.jsp" />
      				</tr>
					<tr>
						<th colspan="2" class="titleReport" align="left">Riesgo Alto</th>
					</tr>
					<tr>
						<jsp:include  page="../component/spaceline.jsp" />
      				</tr>
      				<tr height="25">
	               	 	<td width='50%' class="guide2" align="center">Conductor</td>
	               	 	<td width='50%' class="guide2" align="center">Plan de Mitigación</td>
					</tr>
<%
	it = resume.getRed().iterator();
		lineone = true;
		while(it.hasNext()) {
	Object[] data = it.next();
	String advance = (data[2] == null) ? "No tiene acciones asociadas" : data[2] +"% de acciones realizadas (venc. "+Util.formatDate((Date)data[3])+")";
%>					<tr class='<%=(lineone) ? "lineone" : "linetwo"%>' height="25">
						<td width="50%">
							<a href="javascript:openDriver(<%=String.valueOf(data[0])%>);"><%=data[1]%></a>
						</td>
						<td>
							<%=advance%>
						</td>
					</tr>
<%
	lineone = !lineone;
		}
%>				</table>
			</div>
			<div id="yellow" style="display: none;">
				<table width="900" cellspacing="0" cellpadding="0" align="center">
					<tr>
						<jsp:include  page="../component/spaceline.jsp" />
      				</tr>
					<tr>
						<th colspan="2" class="titleReport" align="left">Riesgo Medio</th>
					</tr>
					<tr>
						<jsp:include  page="../component/spaceline.jsp" />
      				</tr>
      				<tr height="25">
	               	 	<td width='50%' class="guide2" align="center">Conductor</td>
	               	 	<td width='50%' class="guide2" align="center">Plan de Mitigación</td>
					</tr>
<%
	it = resume.getYellow().iterator();
		lineone = true;
		while(it.hasNext()) {
	Object[] data = it.next();
	String advance = (data[2] == null) ? "No tiene acciones asociadas" : data[2] +"% de acciones realizadas (venc. "+Util.formatDate((Date)data[3])+")";
%>					<tr class='<%=(lineone) ? "lineone" : "linetwo"%>' height="25">
						<td width="50%">
							<a href="javascript:openDriver(<%=String.valueOf(data[0])%>);"><%=data[1]%></a>
						</td>
						<td>
							<%=advance%>
						</td>
					</tr>
<%
	lineone = !lineone;
		}
%>				</table>
			</div>
			<div id="green" style="display: none;">
				<table width="900" cellspacing="0" cellpadding="0" align="center">
					<tr>
						<jsp:include  page="../component/spaceline.jsp" />
      				</tr>
					<tr>
						<th colspan="2" class="titleReport" align="left">Bajo Riesgo</th>
					</tr>
					<tr>
						<jsp:include  page="../component/spaceline.jsp" />
      				</tr>
      				<tr height="25">
	               	 	<td width='50%' class="guide2" align="center">Conductor</td>
	               	 	<td width='50%' class="guide2" align="center">Plan de Mitigación</td>
					</tr>
<%
	it = resume.getGreen().iterator();
		lineone = true;
		while(it.hasNext()) {
	Object[] data = it.next();
	String advance = (data[2] == null) ? "No tiene acciones asociadas" : data[2] +"% de acciones realizadas (venc. "+Util.formatDate((Date)data[3])+")";
%>					<tr class='<%=(lineone) ? "lineone" : "linetwo"%>' height="25">
						<td width="50%">
							<a href="javascript:openDriver(<%=String.valueOf(data[0])%>);"><%=data[1]%></a>
						</td>
						<td>
							<%=advance%>
						</td>
					</tr>
<%			lineone = !lineone;
		}
%>				</table>
			</div>
		</form>
	</div>
<%		}
	}else {
		response.sendRedirect(request.getContextPath()+"/error.jsp");		
	}
%>
