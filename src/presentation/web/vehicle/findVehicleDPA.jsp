<!doctype html>

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
	ListResult result = sys.getVehicleReportFacade().findVehicleList("matricula", plate, 0, -1, VehicleData.ACTIVE, userSessionData);

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
		<title>DPA - Checklist</title>
		<link rel="shortcut icon" type="../image/ico" href="../images/favicon.ico">
		<link rel="stylesheet" href="../styles/fonts/pontano_sans.css">
		<link rel="stylesheet" href="../styles/base.css">
		<link rel="stylesheet" href="../styles/checklist.css">
		<link rel="stylesheet" href="../styles/jquery-ui-1.10.3.custom.min.css">
		<script type="text/javascript">
			function doCheckList(type,vehicle) {
				var form = document.forms['checklistForm'];
				form.checklist.value = type;
				form.id.value = vehicle;
				form.submit();
			}

			function doCheckList2(type,vehicle) {
				document.forms['checklistForm'].action="../checklistDPATest2.jsp";
				var form = document.forms['checklistForm'];
				form.checklist.value = type;
				form.id.value = vehicle;
				form.submit();
			}
			
		</script>
	</head>
	<body>
		<header>
			<section class="grid_container">
				<h1 class="customer_logo" style="background-image: url('images/main_logo_large');">DC</h1>
				<div class="toolbar">
					<div class="cepa_logo">CEPA Safe Drive</div>
					<span class="username"><%=sys.getUserSessionData().getFilter().getFirstName()+" "+sys.getUserSessionData().getFilter().getLastName() %></span>
					<span class="exit"><a href='vehicleSearchDPA.jsp'><%=messages.getText("generic.messages.exit") %></a></span>
				</div>
			</section>
		</header>

		<form name="repeat" action="vehicleSearchDPA.jsp"></form>
		<form name="create" action="checklistAddVehicleDPA.jsp"></form>
		<form name="checklistForm" action="../checklistDPA.jsp">
			<input type="hidden" name="checklist" />
			<input type="hidden" name="id" />
		</form>
		<section id="content">
			<section class="grid_container">
				<form action="findVehicleDPA.jsp" >
					<fieldset class="title">
						<h4><%=messages.getText("checklist.dpa.search") %></h4>
						<!-- h5>Data Efetivação: 07/02/2011 • Revisão: 00</h5 -->
					</fieldset>
<%		switch(result.getTotal()) {
			case 0:
%>				<fieldset>
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
%>				<fieldset>
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
							<input type="button" class="button"  value="T1" onClick="javascript:doCheckList('75',<%=String.valueOf(data.getId())%>)" style="width: 150px; "/>
						</label>
					</div>
					<div class="narrow">
						<label for="q5">
							<input type="button" class="button"  value="T2" onClick="javascript:doCheckList('78',<%=String.valueOf(data.getId())%>)" style="width: 150px; " />
						</label>
					</div>
				</fieldset>
				<fieldset>
					<div class="narrow">
						<label for="q5">
							<input type="button" class="button"  value="T1 + Camera" onClick="javascript:doCheckList2('75',<%=String.valueOf(data.getId())%>)" style="width: 150px; "/>
						</label>
					</div>
					<div class="narrow">
						<label for="q5">
							<input type="button" class="button"  value="T2 + Camera" onClick="javascript:doCheckList2('78',<%=String.valueOf(data.getId())%>)" style="width: 150px; " />
						</label>
					</div>
				</fieldset>
<%				break;	
			default:
				Iterator it = result.getElements().iterator();
				while(it.hasNext()) {
					data = (VehicleData)it.next();
%>				<fieldset>
					<div class="narrow" id="qw1" data-type="2">
						<label>
							<a href='selectCheckListDPA.jsp?vehicle=<%=String.valueOf(data.getId())%>'><%=data.getMatricula()%></a><%=" - " + messages.getText(data.getType().getResourceBundleId())%>
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
