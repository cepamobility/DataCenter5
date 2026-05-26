<!doctype html>

<%@page import="communication.vehicle.tables.VehicleOptionalInfoData"%>
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
	
	String id = request.getParameter("vehicle");
	String title = "vehicle.found";
	if(id == null) {
		id = (String)userSessionData.getValue();
		title = (String) session.getAttribute("Msg");
	}
	VehicleData data = sys.getVehicleReportFacade().findVehicleByPrimaryKey(new Integer(id), userSessionData);
    String up=(String)request.getParameter("update");
	if(up!=null && up.equals("s")){
		String air=(String) request.getParameter("air");
		data.setAireAcondicionado(air.equals("s"));
		String airBag=(String) request.getParameter("airBag");
		data.setAirBag(airBag.equals("s"));
		String abs=(String) request.getParameter("abs");
		data.setAbs(abs.equals("s"));
		String susp=(String) request.getParameter("susp");
		data.setSuspension(susp.equals("s"));
		String renavan=(String) request.getParameter("renavan");
		String prefix=(String) request.getParameter("prefix");
		String monitoringsystem=(String) request.getParameter("monitoringsystem");
		
		if(data.getOptionalInfo()==null){
			data.setOptionalInfo(new VehicleOptionalInfoData());
		}
		data.getOptionalInfo().setRenavan(renavan);
		data.getOptionalInfo().setPrefix(prefix);
		data.getOptionalInfo().setMonitoringSystem(monitoringsystem);
		
		
		sys.getVehicleABMFacade().vehicleUpdate(data, sys.getUserSessionData().getFullName(), false, sys.getUserSessionData());
		  
		String redirectURL = "../menu/checklistUPM.jsp?fu=true&id="+id+"&checklist="+request.getParameter("checklist");
		response.sendRedirect(redirectURL); 
		
	}else{
	
	//"../checklistUPM.jsp"
%>

<html lang="en">
	<head>
		<meta charset="iso-8859-1">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=EDGE" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<link rel="apple-touch-icon-precomposed" sizes="57x57" href="../images/apple-touch-icon-57x57-precomposed.png" />
		<link rel="apple-touch-icon-precomposed" sizes="72x72" href="../images/apple-touch-icon-72x72-precomposed.png" />
		<link rel="apple-touch-icon-precomposed" sizes="114x114" href="../images/apple-touch-icon-114x114-precomposed.png" />
		<link rel="apple-touch-icon-precomposed" sizes="144x144" href="../images/apple-touch-icon-144x144-precomposed.png" />
		<meta name="viewport" content="user-scalable=no, width=980" />
		<meta name="description" content="">
		<meta name="keywords" content="">
		<title>Checklist</title>
		<link rel="shortcut icon" type="../image/ico" href="../images/favicon.ico">
		<link rel="stylesheet" href="../styles/fonts/pontano_sans.css">
		<link rel="stylesheet" href="../styles/base.css">
		<link rel="stylesheet" href="../styles/checklist.css">
		<link rel="stylesheet" href="../styles/jquery-ui-1.10.3.custom.min.css">
		<script type="text/javascript">
			function doCheckList(type,vehicle) {
				var form = document.forms['checklistForm'];
				form.checklist.value = type;
				form.vehicle.value = vehicle;
				form.abs.value = document.getElementById('abs').value;
				form.airBag.value = document.getElementById('airBag').value;
				form.air.value = document.getElementById('air').value;
				form.susp.value = document.getElementById('susp').value;
				form.update.value='s';
				form.renavan.value = document.getElementById('renavan').value; 
				form.prefix.value = document.getElementById('prefix').value;
				form.monitoringsystem.value = document.getElementById('monitoringsystem').value;
				
				
				form.submit();
			}

			 
		</script>
	</head>
	<body>
		<header>
			<section class="grid_container">
				<h1 class="customer_logo" style="background-image: url('../menu/<%=Util.corporationLogo(sys.getUserSessionData()) %>');">DC</h1>
				<div class="toolbar">
					<div class="cepa_logo">CEPA Safe Drive</div>
					<span class="username"><%=sys.getUserSessionData().getFilter().getFirstName()+" "+sys.getUserSessionData().getFilter().getLastName() %></span>
					<span class="exit"><a href='vehicleSearchDPA.jsp'><%=messages.getText("generic.messages.exit") %></a></span>
				</div>
			</section>
		</header>		

		<form name="checklistForm" action="vehicleFastUpdate.jsp">
			<input type="hidden" name="checklist" />
			<input type="hidden" name="vehicle" />
			<input type="hidden" name="abs" />
			<input type="hidden" name="airBag" />
			<input type="hidden" name="air" />
			<input type="hidden" name="susp" />
			<input type="hidden" name="update" />
			<input type="hidden" name="prefix" />
			<input type="hidden" name="renavan" />
			<input type="hidden" name="monitoringsystem" />
		</form>
		
		<section id="content">
			<section class="grid_container">
				<form name="repeat">
				<fieldset class="title">
					<h4><%="Actualizar Vehículo"%></h4>
					<!-- h5>Data Efetivação: 07/02/2011 • Revisão: 00</h5 -->
				</fieldset>
				
				<fieldset>
					<div class="narrow">
						<label for='abs'>
							<%=messages.getText("generic.vehicle.vehicledata.abs")%>
						</label>
						<select id="abs"   type="text"  style="width:80px;" style="float: left;">
								 <% if(data.getAbs()){ %>
									<option value='s' selected="selected" >SI</option>
									<option value='n'>NO</option>
								  <% }else{ %>
									<option value='s' >SI</option>
									<option value='n' selected="selected" >NO</option>
								  <% } %>
								  
						</select> 
						<label for='susp'>
							<%="Suspensión neumática" %>
						</label>
						<select id="susp"   type="text"  style="width:80px;" style="float: left;">
								  <% if(data.getSuspension()){ %>
									<option value='s' selected="selected" >SI</option>
									<option value='n'>NO</option>
								  <% }else{ %>
									<option value='s' >SI</option>
									<option value='n' selected="selected" >NO</option>
								  <% } %>
						</select> 
						<label for='renavan'>
							<%=messages.getText("generic.vehicle.optionalinfo.renavan")%>
						</label>
						<select id="renavan"   type="text"  style="width:80px;" style="float: left;">
								  <% if(data.getOptionalInfo()!=null && data.getOptionalInfo().getRenavan().equals("SI")){ %>
									<option value='SI' selected="selected" >SI</option>
									<option value='NO'>NO</option>
								  <% }else{ %>
									<option value='SI' >SI</option>
									<option value='NO' selected="selected" >NO</option>
								  <% } %>
						</select> 	
						<label for='airBag'>
							<%=messages.getText("generic.vehicle.vehicledata.airBag")%>
						</label>
						<select id="airBag"   type="text"  style="width:80px;" style="float: left;">
								  <% if(data.getAirBag()){ %>
									<option value='s' selected="selected" >SI</option>
									<option value='n'>NO</option>
								  <% }else{ %>
									<option value='s' >SI</option>
									<option value='n' selected="selected" >NO</option>
								  <% } %>
								 
						</select> 
						<label for='prefix'>
							<%=messages.getText("generic.vehicle.optionalinfo.prefix")%>
						</label>
						<select id="prefix"   type="text"  style="width:80px;" style="float: left;">
								  <% if(data.getOptionalInfo()!=null && data.getOptionalInfo().getPrefix().equals("SI")){ %>
									<option value='SI' selected="selected" >SI</option>
									<option value='NO'>NO</option>
								  <% }else{ %>
									<option value='SI' >SI</option>
									<option value='NO' selected="selected" >NO</option>
								  <% } %>
								 
						</select> 
						<label for='aire'>
							<%=messages.getText("generic.vehicle.vehicledata.aireAcondicionado")%>
						</label>
						<select id="air"   type="text"  style="width:80px;" style="float: left;">
								  <% if(data.getAireAcondicionado()){ %>
									<option value='SI' selected="selected" >SI</option>
									<option value='n'>NO</option>
								  <% }else{ %>
									<option value='s' >SI</option>
									<option value='n' selected="selected" >NO</option>
								  <% } %>
						</select>
						<label for='monitoringsystem'>
							<%=messages.getText("generic.vehicle.optionalinfo.monitoringsystem")%>
						</label>
						<select id="monitoringsystem"   type="text"  style="width:80px;" style="float: left;">
								  <% if(data.getOptionalInfo()!=null && data.getOptionalInfo().getMonitoringSystem().equals("SI")){ %>
									<option value='SI' selected="selected" >SI</option>
									<option value='NO'>NO</option>
								  <% }else{ %>
									<option value='SI' >SI</option>
									<option value='NO' selected="selected" >NO</option>
								  <% } %>
								 
						</select>  
						
						
					</div>
				</fieldset>
				<fieldset>
					<div class="narrow">
						<label for="q5">
							<input type="button" class="button"  value="siguiente" onClick="javascript:doCheckList('<%=String.valueOf(request.getParameter("checklist"))%>',<%=String.valueOf(data.getId())%>)"/>
						</label>
					</div>
				</fieldset>
		</form>
			</section>
		</section>
		<script type="text/javascript" src="../scripts/json2.min.js"></script>
		<script type="text/javascript" src="../scripts/jquery-2.0.2.min.js"></script>
		<script type="text/javascript" src="../scripts/jquery-ui-1.10.3.custom.min.js"></script>
		<script type="text/javascript" src="../scripts/jquery.ui.datepicker-pt.js"></script>
		<script type="text/javascript" src="../scripts/jquery.ui.datepicker-es.js"></script>
		<script type="text/javascript" src="../scripts/jquery.tadeouy.loggable.js"></script>
		<script type="text/javascript" src="../scripts/jquery.tadeouy.assessmentController.js"></script>
		<script type="text/javascript" src="../scripts/jquery.tadeouy.question.js"></script>
		<script type="text/javascript" src="../scripts/jquery.tadeouy.card.js"></script>
		<script type="text/javascript" src="../scripts/jquery.tadeouy.assessment.js"></script>
		<script type="text/javascript" src="../scripts/assessment.js"></script>
	</body>
</html>
 <% } %>
