<!doctype html>


<%@page language="java" 
	errorPage="../exception.jsp" 
	import="business.*"
%>
<%@page import="communication.administration.user.UserSessionData"%>
<%@page import="communication.datatype.tables.TypeTradeDataType"%>
<%@page import="communication.datatype.DataTypeConstants"%>
<%@page import="java.util.LinkedList"%>
<%@page import="communication.vehicle.tables.TrailerMainData"%>
<%@page import="communication.administration.corporation.tables.CorporationData"%>
<%@page import="communication.util.ListResult"%>
<%@page import="communication.vehicle.tables.VehicleData"%>
<%@page import="java.util.Collections"%>
<%@page import="communication.activity.tables.ItemData"%>
<%@page import="communication.language.Text"%>
<%@page import="communication.activity.tables.PointData"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@page import="communication.activity.tables.CheckListData"%>
<%@page import="communication.vehicle.tables.VehicleCheckData"%>
<%@page import="presentation.translator.web.util.*"%>
<%@page import="java.util.Date"%>
<%@page import="communication.activity.tables.ItemValueStrData" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="communication.administration.corporation.util.DPAUnconformityItemFilter" %>
<%@page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData = sys.getUserSessionData();
	Text messages = sys.getText();
	
	String checklist = request.getParameter("checklist");
	Integer id = new Integer(request.getParameter("id"));
	
	String version=String.valueOf( new Date().getTime());
	
	String next = "layout.jsp?refer=vehicle/VehicleView.jsp?id="+id;
	
	VehicleCheckData data = sys.getVehicleReportFacade().findLastVehicleCheckByCheckId(id, 80, userSessionData);
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
%>
<style>
<!--
.button-red {
	background-color: red !important;
}
-->
</style>
<html lang="en">
	<head>
		<meta charset="iso-8859-1">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=EDGE" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<link rel="apple-touch-icon-precomposed" sizes="57x57" href="images/apple-touch-icon-57x57-precomposed.png" />
		<link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/apple-touch-icon-72x72-precomposed.png" />
		<link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/apple-touch-icon-114x114-precomposed.png" />
		<link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/apple-touch-icon-144x144-precomposed.png" />
		<meta name="viewport" content="user-scalable=no, width=980" />
		<meta name="description" content="">
		<meta name="keywords" content="">
		<title>Checklist Pendiente</title>
		<link rel="shortcut icon" type="image/ico" href="images/favicon.ico">
		<link rel="stylesheet" href="styles/fonts/pontano_sans.css">
		<link rel="stylesheet" href="styles/base.css">
		<link rel="stylesheet" href="styles/checklist.css">
		<link rel="stylesheet" href="styles/jquery-ui-1.10.3.custom.min.css">
		<style>
			.pendingContainer { padding-top: 50px; position: relative; overflow: hidden; border-radius: 15px; width: 100%; max-width: 1024px; background: white; margin: 100px auto; padding: 70px 50px 25px 50px; box-sizing: border-box; }
			.pendingHeader { 
					display: flex; 
					padding: 8px 16px; 
					align-items: center; 
					background: grey; 
					min-height: fit-content; 
					margin-bottom: 10px; 
					top: 0;
				    left: 0;
				    right: 0;
				    position: absolute;
				}
			.pendingHeader h1 { margin: 0; color: white; font-size: 22px; font-weight: 500; }
			.narrow { display: flex; flex-direction: column; gap: 15px; margin-inline: 15px; width: 100%; box-sizing: border-box; min-width: 0; }
			.content { padding-inline: 20px }
			table {
				font-family: 'Roboto Light';
    			font-size: 12px;
    			color: black !important;
    			border: 1px solid #ddd;
    			width: 100%;
			    max-width: 100%;
			    margin-bottom: 20px;
			    background-color: transparent;
			    border-spacing: 0;
    			border-collapse: collapse;
			}
			thead {
				background-color: #F9BD6A;
			    font-family: 'Roboto Medium';
			    font-size: 14px;
			}
			thead tr {
				background: transparent;
			}
			tr {
				background-color: #f9f9f9;
			}
			tr td:first-child {
				border-left: 0px; /* !important; */;
			}
			td{
				border-left-width: unset;
				white-space: nowrap;
			    border: 1px solid #ddd;
		        padding: 5px;
			}
			table>tbody>tr>td {
			    padding: 8px;
			    line-height: 1.42857143;
			    vertical-align: top;
			    border-top: 1px solid #ddd;
			}
			.cellRed {
			    background-color: red !important;
			    color: white !important;
			}
			fieldset { border: none; padding: 0; margin: 0; min-width: 0; }
			form label { font-size: 1.4rem; font-weight: 500; color: rgb(36, 36, 36); }
			input[type="text"], input[type="date"], select, textarea {
			  width: 100%; box-sizing: border-box; min-width: 0;
			  border: 1px solid grey;
			  padding: 8px 10px; border: 0.5px solid grey;
			  border-radius: 10px; background: white;
			  color: rgb(36, 36, 36); font-size: 15px;
			}
			.table-custom a {
			    color: black !important;
			    cursor: pointer;
			}
			ul {
				display: flex;
				flex-direction: column;
				gap: 15px;
				list-style-type: none;
				margin-left: 10px;
    			padding: 0;
			}
			a{background-color: transparent;}
			a:link {text-decoration: none;}
			button {
				padding: 10px;
				font-size: 1.2rem;
				border: 1px solid grey;
				border-radius: 5px;
				background-color: #ffcd00;
			}
			label {
				font-size: 1.3rem;
				margin-top: 10px
			}
			@media (max-width: 1024px) { 
				form label {font-size: 2rem;}
				.pendingContainer { flex-direction: column; max-width: 750px; }
				.pendingHeader h1 {
					font-size: 2.2rem;
				}
			}
		</style>
		<script type="text/javascript">
			function doCheckList(type, checkId, vehicle) {
	            var form = document.getElementById('checklist');
	            form.checklist.value = type;
	            form.id.value = vehicle;
	            form.submit();
	         }

			function doDPA(uId, vehicleId) {
	            var form = document.getElementById('dpaform');
	            form.unconformitydpaid.value = uId;
	            form.vehicle.value = vehicleId;
	            form.submit();
	         }
		</script>
	</head>
	<body>
		<form name="checklistForm" id='checklist' action="./checklistUPM.jsp">
			<input type="hidden" name="checklist" />
			<input type="hidden" name="id" />
		</form>
		<form name="dpaForm" id='dpaform' action="./pendingDPA.jsp">
			<input type="hidden" name="unconformitydpaid" />
			<input type="hidden" name="vehicle" />
		</form>
		<header>
			<section class="grid_container">
				<h1 class="customer_logo" style="background-image: url('../menu/<%=Util.corporationLogo(sys.getUserSessionData()) %>');">DC</h1>
				<div class="toolbar">
					<div class="cepa_logo">CEPA Safe Drive</div>
					<span class="username"><%=sys.getUserSessionData().getFilter().getFirstName()+" "+sys.getUserSessionData().getFilter().getLastName() %></span>
					<span class="exit"><a href='<%=next%>'><%=messages.getText("generic.messages.exit") %></a></span>
				</div>
			</section>
		</header>
	
		<div class="pendingContainer">
		  <div class="pendingHeader"><h1>No Conformidades Pendientes</h1></div>
		  <div class="col-xs-12">
			<fieldset>
				<div class="table-responsive">
					<table class="table table-bordered table-hover table-condensed table-striped table-custom">
						<thead>
							<tr>
								<td><%=messages.getText("generic.driver.observation.date")%></td>
								<td><%=messages.getText("generic.dpa.unconformity.termdate")%></td>
								<td><%=messages.getText("generic.dpa.unconformity.items")%></td>
								<td><%=messages.getText("generic.dpa.uncnformity.comment")%></td>
							</tr>
						</thead>
						<tbody>
<%			DPAUnconformityItemFilter filter=new DPAUnconformityItemFilter();
			filter.setId(Integer.valueOf(id));
			Collection unconformities=sys.getCorporationReportFacade().exportUnconformityItemsCheckList(sys.getUserSessionData(), sys.getText(),null, filter);
			if(unconformities.size() == 0) {
%>									<tr>
								<td colspan="4"><%="No tiene."%></td>								
							</tr>
<%			} else {
			Iterator itLOS = unconformities.iterator();
	 		boolean lineone = false;
	 		int i = 0;
	 		while(itLOS.hasNext()) {
 				ArrayList<String> level = (ArrayList<String>)itLOS.next();
%>
								<tr>
          							<td <%=(level.get(6).equalsIgnoreCase("V")) ? "class='cellRed'" : ""%>><a onClick="javascript:doDPA(<%=level.get(0)%>, <%=id%>)">  <%=level.get(2)%></a></td>
              						<td <%=(level.get(6).equalsIgnoreCase("V")) ? "class='cellRed'" : ""%>><%=level.get(4)%></td>
              						<td <%=(level.get(6).equalsIgnoreCase("V")) ? "class='cellRed'" : ""%>><%=level.get(3)%></td>
              						<td <%=(level.get(6).equalsIgnoreCase("V")) ? "class='cellRed'" : ""%>><%=level.get(5)%></td>
					      		</tr>
<%			}
	}
%>							</tbody>					
						</table>
					</div>
				</fieldset>
			</div>
		  	<button onClick="javascript:doCheckList('80',<%=String.valueOf(data.getVehicleCheckId())%>,<%=String.valueOf(id)%>)">Continuar de igual manera</button>
		  	<button onClick="history.back()">Cancelar</button>
		</div>
		<script type="text/javascript" src="scripts/json2.min.js?<%=version%>"></script>
		<script type="text/javascript" src="scripts/jquery-2.0.2.min.js?<%=version%>"></script>
		<script type="text/javascript" src="scripts/jquery-ui-1.10.3.custom.min.js?<%=version%>"></script>
		<script type="text/javascript" src="scripts/jquery.ui.datepicker-pt.js?<%=version%>"></script>
		<script type="text/javascript" src="scripts/jquery.ui.datepicker-es.js?<%=version%>"></script>
		<script type="text/javascript" src="scripts/jquery.tadeouy.loggable.js?<%=version%>"></script>
		<script type="text/javascript" src="scripts/jquery.tadeouy.assessmentController.js?<%=version%>"></script>
		<script type="text/javascript" src="scripts/jquery.tadeouy.question.js?<%=version%>"></script>
		<script type="text/javascript" src="scripts/jquery.tadeouy.card.js?<%=version%>"></script>
		<script type="text/javascript" src="scripts/jquery.tadeouy.assessment.js?<%=version%>"></script>
		<script type="text/javascript" src="scripts/assessment.js?<%=version%>"></script>
	</body>
</html>
