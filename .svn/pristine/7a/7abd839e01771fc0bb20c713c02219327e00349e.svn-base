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
		FleetBayerData data = sys.getCorporationReportFacade().getPointRanges(sys.getUserSessionData());
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
	function mostrar(val) {
		var element = document.forms['values'].elements['val'+val];
		var disp = "none";
		if(element.value == 0) {
			disp = "block";
			element.value = 1;
		}else {
			element.value = 0;
		}
		var nivel = 'nivel'+val+'_0';
		$( "div[id^='"+nivel+"']" ).css("display", disp);
	}
	</script>
	<div id="contentBayerReport">
		<div class="titleBayer"><%=messages.getText("query.points.rangereport") %></div>
		<div class="titleBayer">
			<img src="util/imgs/leyenda_bayer_mx.png">
		</div>
		<form name="values">
			<div>
				<div class="emptyValue">&nbsp;</div>
				<div class='headerBayerReport0'>
					<img src="menu/imgs/header.png">
				</div>
			</div>
<% 		Collection<String> lines = data.getRangeLines("","");
		String[] lineStr = (String[])lines.toArray(new String[0]);
		for(int j = 1; j < lineStr.length; j++) {
%>			<%=lineStr[j]%>			
<%		}
%>			<%=lineStr[0]%>			
		</form>
	</div>
<%		}
	}else {
		response.sendRedirect(request.getContextPath()+"/error.jsp");		
	}
%>
