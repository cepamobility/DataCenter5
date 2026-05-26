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
	Object[] result = sys.getCorporationReportFacade().getPointDetail(sys.getUserSessionData());
	Collection[] values = (Collection[]) result[0];
	PointDetailBayerData[] totals = (PointDetailBayerData[]) result[1];
%>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<style type="text/css">	</style>
	<head>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<META http-equiv="Expires" content="-1">		
	</head>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> 
	<div id="contentBayerReport">
		<div class="titleBayer"><%=messages.getText("query.points.detailreport") %></div>
		<form name="values">
			<table cellpadding="0" cellspacing="0" align="center">
				<tr>
					<td colspan="3">&nbsp;</td>
					<td class='headerBayerReport'>Very High Risk</td>
					<td class='headerBayerReport'>High Risk</td>
					<td class='headerBayerReport'>Medium Risk</td>
					<td class='headerBayerReport'>Low Risk</td>
					<td rowspan="2" class='headerBayerReport'>Total</td>
				</tr>
				<tr>
					<td colspan="3">&nbsp;</td>
					<td class='blackBayerReport'>&nbsp;</td>
					<td class='redBayerReport'>&nbsp;</td>
					<td class='yellowBayerReport'>&nbsp;</td>
					<td class='greenBayerReport'>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
					<td class='headerBayerReport'>Puntos</td>
<%		PointDetailBayerData totalValues = totals[totals.length-1];
		for(int i = 0; i < 4; i++) {
%>					<td class='headerBayerReport'><%=String.valueOf(totalValues.getValues()[i]) %></td>
<%		}
%>					<td class='headerBayerReport'><%=String.valueOf(totalValues.getSum()) %></td>
				</tr>
<% 		for(int i = 0; i < values.length; i++) {
			Collection data = values[i];
%>				<tr>
					<td rowspan="<%=String.valueOf(data.size()+1)%>" class="bayerCategory1"><%=messages.getText(totals[i].getKey())%></td>
					<th class='bayerDetailCell1'><%=messages.getText(totals[i].getKey())%></th>
					<td class='bayerDetailCell2'></td>
<% 			for(int j = 0; j < 4; j++) {
%>					<td class='bayerDetailCellTotal'><%=String.valueOf(totals[i].getValues()[j])%></td>
<%			}
%>
					<td class='bayerDetailCellTotal'><%=String.valueOf(totals[i].getSum())%></td>
				</tr>
<%			Iterator it = data.iterator();
			while(it.hasNext()) {
%>				<%=((PointDetailBayerData)it.next()).getLine(i,messages)%>			
<%			}
%>						
<%		}
%>		</form>
	</div>
<%		}	
	}else {
		response.sendRedirect(request.getContextPath()+"/error.jsp");		
	}
%>
