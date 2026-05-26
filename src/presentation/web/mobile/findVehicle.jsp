<!doctype html>

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
<%@page import="presentation.translator.web.util.*"%>
<%@page language="java" 
	errorPage="../exception.jsp" 
	import="business.*"
%>
<%@page import="communication.administration.user.UserSessionData"%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData = sys.getUserSessionData();
	Text messages = sys.getText();
	
	String plate = request.getParameter("q1");
	ListResult result = sys.getVehicleReportFacade().findVehicleList("matricula", plate, 0, -1, VehicleData.ALL, userSessionData);

%>

<html lang="en">
	<head>
		<meta charset="iso-8859-1">
		<meta name="MobileOptimized" content="width" />
		<meta name="HandheldFriendly" content="true" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="content-language" content="es" />
		<title>Checklist</title>
		<link rel="shortcut icon" type="../image/ico" href="../images/favicon.ico">
		<link rel="stylesheet" href="../styles/mbase.css">
		<link rel="stylesheet" href="../styles/mchecklist.css">
		<link rel="stylesheet" href="../styles/jquery-ui-1.10.3.custom.min.css">
		<script type="text/javascript">
			function doCheckList(type,vehicle) {
				var form = document.forms['checklistForm'];
				form.checklist.value = type;
				form.id.value = vehicle;
				form.submit();
			}
			function doCheckList2(type,vehicle) {
	            var form = document.getElementById('checklist2');
	            form.checklist.value = type;
	            form.vehicle.value = vehicle;
	            form.submit();
	        }
			function doCheckList3(type,vehicle) {
				var form = document.getElementById('checklist1');
				form.checklist.value = type;
				form.id.value = vehicle;
				form.submit();
			}
			function doCheckListMP(type,vehicle) {
	            var form = document.getElementById('checklistMP');
	            form.checklist.value = type;
	            form.id.value = vehicle;
	            form.submit();
	        }
			function doCheckListSyngetaBR(type,vehicle) {
	            var form = document.getElementById('checklistSyngenta');
	            form.checklist.value = type;
	            form.id.value = vehicle;
	            form.submit();
	         }
		</script>
	</head>
	<body>
		<section class="grid_container">
			<h1 class="customer_logo"><img src="../menu/images/main_logo.png"/></h1>
			<br>
			<div class="toolbar">
				<span class="username"><%=sys.getUserSessionData().getFilter().getFirstName()+" "+sys.getUserSessionData().getFilter().getLastName() %></span>
				<span class="exit"><a href='../login.jsp'><%=messages.getText("generic.messages.exit") %></a></span>
			</div>
		</section>
		<form name="repeat" action="vehicleSearch.jsp"></form>
		<form name="create" action="checklistAddVehicle.jsp"></form>
		<form name="checklistForm" action="checklist.jsp">
			<input type="hidden" name="checklist" />
			<input type="hidden" name="id"  />
		</form>
		<form name="checklistForm2" id='checklist2' action="vehicleFastUpdate.jsp">
			<input type="hidden" name="checklist" />
			<input type="hidden" name="vehicle" />
		</form>
		<form name="checklistSyngentaForm" id='checklistSyngenta' action="checklistSyngentaBR.jsp">
			<input type="hidden" name="checklist" />
			<input type="hidden" name="id" />
		</form>
		<section id="content">
			<section class="grid_container">
				<form action="findVehicleDPA.jsp" >
					<fieldset class="title">
						<h4><%=messages.getText("checklist.dpa.search") %></h4>
					</fieldset>
<%		switch(result.getTotal()) {
			case 0:
%>					<fieldset>
						<div class="narrow">
							<label>
								<%=messages.getText("vehicle.notfound") %>
							</label>
						</div>
					</fieldset>
					<fieldset>
						<div class="narrow">
							<label>
								<input type="button" class="button" value='<%=messages.getText("checklistdpa.search.repeat")%>' onClick="javascript:document.forms['repeat'].submit()" />
							</label>
						</div>
						<div class="narrow">
							<label>
								<input type="button" class="button" value='<%=messages.getText("permission.vehiclecreate.name")%>' onClick="javascript:document.forms['create'].submit()" />
							</label>
						</div>
					</fieldset>
<%				break;	
			case 1:
				VehicleData data = (VehicleData)result.getElements().iterator().next();
%>					<fieldset>
						<div class="narrow" id="qw1" data-type="2">
							<label for="q1">
								<%=messages.getText("vehicle.found") %>
							</label>
						</div>
					</fieldset>
					<fieldset>
						<div class="narrow">
							<label for="q1">
								<%=messages.getText("generic.vehicle.vehicledata.matricula")+": "+data.getMatricula()%>
							</label>
							<label for="q2">
								<%=messages.getText("generic.vehicle.vehicledata.trade")+": "+messages.getText(data.getTrade().getResourceBundleId())%>
							</label>
							<label for="q3">
								<%=messages.getText("generic.vehicle.vehicledata.model")+": "+data.getModel()%>
							</label>
							<label for="q4">
								<%=messages.getText("generic.vehicle.vehicledata.type")+": "+messages.getText(data.getType().getResourceBundleId())%>
							</label>
						</div>
					</fieldset>
					<fieldset>
						<div class="narrow">
							<label for="q5">
								<%=messages.getText("checklistdpa.vehicle.select")%>
							</label>
						</div>
					</fieldset>
					<fieldset>
						<div class="narrow">						
							<label for="q5">
<% 							if(sys.isCorporation(CorporationData.SYNGENTA_BR)){ 
%>								<input type="button" class="button"  value="Checklist" onClick="javascript:doCheckListSyngetaBR('95',<%=String.valueOf(data.getId())%>)"/>	
								
<%							}else{ 
%>								<input type="button" class="button"  value="Seguimiento en Ruta" onClick="javascript:doCheckList('79',<%=String.valueOf(data.getId())%>)"/>	
<% 								if(!sys.isCorporation(new int[]{CorporationData.FORESTALORIENTAL, CorporationData.MONTES_DE_PLATA, CorporationData.WEYERHAEUSER})){ 
%>								<input type="button" class="button"  value="Check List" onClick="javascript:doCheckList('80',<%=String.valueOf(data.getId())%>)"/>
<%								}else {
%>								<input type="button" class="button"  value="Chequeo Vehicular" onClick="javascript:doCheckList('80',<%=String.valueOf(data.getId())%>)"/>	
								<input type="button" class="button" style="background-color: #E74405 !important; background: linear-gradient(to bottom, #E74405 0%, #E74405 100%)  !important; color: #FFFFFF !important;" value="Nuevo Chequeo Preventivo" onClick="javascript:doCheckList('123',<%=String.valueOf(data.getId())%>)"/>	
<%								}
							}
%>	
							</label>
						</div>
					</fieldset>
<%				break;	
			default:
				Iterator it = result.getElements().iterator();
				while(it.hasNext()) {
					data = (VehicleData)it.next();
%>					<fieldset>
						<div class="narrow" id="qw1" data-type="2">
							<label>
								<a href='selectCheckList.jsp?vehicle=<%=String.valueOf(data.getId())%>'><%=data.getMatricula()%></a><%=" - " + messages.getText(data.getType().getResourceBundleId())%>
							</label>
						</div>
						<div class="narrow">
							<label>
								<%=messages.getText(data.getTrade().getResourceBundleId()) + " - " + data.getModel()%>
							</label>
						</div>
					</fieldset>
<%				}
				break;	
		}

%>
				</form>
			</section>
		</section>
	</body>
</html>
