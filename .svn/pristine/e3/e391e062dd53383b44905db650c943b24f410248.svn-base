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
		<style>
			.novisible{
				display:none;
				visibility:hidden;
			}
		</style>	
	</head>
	<body>
		<header>
			<section class="grid_container">
				<h1 class="customer_logo" style="background-image: url('../menu/images/main_logo.png');">DC</h1>
				<div class="toolbar">
					<span class="username"><%=sys.getUserSessionData().getFilter().getFirstName()+" "+sys.getUserSessionData().getFilter().getLastName() %></span>
					<span class="exit"><a href='../login.jsp'><%=messages.getText("generic.messages.exit") %></a></span>
				</div>
			</section>
		</header>
		<form name="repeat" action="vehicleSearch.jsp"></form>
		<form name="logout" action="../login.jsp"></form>
		<section id="content">
			<section class="grid_container">
				<form action="vehicleSearch.jsp" >
					<fieldset class="title">
						<h4><%=messages.getText("generic.activity.form.vehiclechecklist") %></h4>
						<!-- h5>Data Efetivação: 07/02/2011 • Revisão: 00</h5 -->
					</fieldset>
					<fieldset>
						<div class="narrow">
							<label>
								<%=messages.getText("vehicle.checklistdpa.inserted") %>
							</label>
						</div>
					</fieldset>
					<fieldset>
						<div class="narrow">
							<label>
								<input type="button" class="button" value='<%=messages.getText("checklistdpa.insert.other")%>' onClick="javascript:document.forms['repeat'].submit()" />
							</label>
						</div>
						<div class="narrow">
							<label>
								<input type="button" class="button" value='<%=messages.getText("generic.messages.logout")%>' onClick="javascript:document.forms['logout'].submit()" />
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
