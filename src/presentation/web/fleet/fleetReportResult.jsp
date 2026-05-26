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
	String check = Util.checkPermission(sys,SecurityConstants.QUERY);
	if(check != null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		Calendar c1 = Calendar.getInstance();
		c1.add(Calendar.YEAR, -1);
		Calendar c2 = Calendar.getInstance();
		
		FleetBayerData data = sys.getFleetReportFacade().getFleetBayerReport(c1, c2, sys.getUserSessionData());
%>
	<form name="values">
		<div>
			<div class='emptyValue'>&nbsp;</div>
			<div class='headerBayerReport'>Tamaño Flota</div>
			<div class='headerBayerReport'>Total Millas</div>
			<div class='headerBayerReport'>Colisiones</div>
			<div class='headerBayerReport'>APMM</div>
			<div class='headerBayerReport'>% Frecuencia</div>
			<div class='headerBayerReport'>% Kms/Mes</div>
		</div>
		
<% 		Collection<String> lines = data.getLines("","");
		String[] lineStr = (String[])lines.toArray(new String[0]);
		for(int j = 1; j < lineStr.length; j++) {
%>			<%=lineStr[j]%>			
<%		}
%>			<%=lineStr[0]%>			
<%	}
%>
	</form>
