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
		String check = Util.checkPermission(sys,SecurityConstants.QUERY);
		if(check != null) {
	response.sendRedirect(request.getContextPath()+check);
		}else {
	messages = sys.getText();
	Calendar c = Calendar.getInstance();
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
	function runReport() {
		var form = document.forms['parameters'];
         var link1 = "fleet/fleetReportResult.jsp?fM="+form.fromMonth.value+"&fY="+form.fromYear.value+"&tM="+form.toMonth.value+"&tY="+form.toYear.value;
        $.get(link1,
           function(data) {
				$("#contentBayerReport").html(data);
	  	});
	}
	</script>
	<div id="filter">
		<div class="titleBayer">Reporte de Flotas</div>
		<form name="parameters" action="#">
			<div class="parameterBayer">
				Desde:&nbsp;
				<select name="fromMonth" class="input">
<%			for(int i = 1; i < 13; i++) { %>
					<option value="<%=String.valueOf(i)%>" <%=(i == c.get(Calendar.MONTH)+1) ? "selected" : "" %>><%=messages.getText("generic.month."+i)%></option>
<%			}
%>				</select>
				<select name="fromYear" class="input">
<%			for(int i = 2013; i < 2020; i++) { %>
					<option value="<%=String.valueOf(i)%>" <%=(i == c.get(Calendar.YEAR)-1) ? "selected" : "" %>><%=String.valueOf(i)%></option>
<%			}
%>				</select>
			</div>
			<div class="parameterBayer">
				Hasta:&nbsp;
				<select name="toMonth" class="input">
<%			for(int i = 1; i < 13; i++) { %>
					<option value="<%=String.valueOf(i)%>" <%=(i == c.get(Calendar.MONTH)+1) ? "selected" : "" %>><%=messages.getText("generic.month."+i)%></option>
<%			}
%>				</select>
				<select name="toYear" class="input">
<%			for(int i = 2013; i < 2020; i++) { %>
					<option value="<%=String.valueOf(i)%>" <%=(i == c.get(Calendar.YEAR)) ? "selected" : "" %>><%=String.valueOf(i)%></option>
<%			}
%>				</select>

			</div>
			<div align="right" style="width: 250px;">
				<input type="button" value="Abrir" class="input" onclick="runReport();"/>
			</div>
		</form>
	</div>
	<div id="contentBayerReport">
	</div>
<%		}
	}else {
		response.sendRedirect(request.getContextPath()+"/error.jsp");		
	}
%>
