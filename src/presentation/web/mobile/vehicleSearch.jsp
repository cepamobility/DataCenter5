<!doctype html>

<%@page import="communication.administration.corporation.tables.CorporationData"%>
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
		<section id="content">
				<section class="grid_container">
				<form action="findVehicle.jsp" >
					<fieldset class="title">
						<h4><%=messages.getText("checklist.dpa.search") %></h4>
						<!-- h5>Data Efetivação: 07/02/2011 • Revisão: 00</h5 -->
					</fieldset>

					<fieldset>
						<div class="narrow" id="qw1" data-type="2">
							<label for="q1">
								<%=messages.getText("generic.vehicle.vehicledata.matricula") %>&nbsp;
								<input id="q1" name="q1" >&nbsp;
								<input type="submit" class="button" value='<%=messages.getText("generic.messages.search")%>'>
							</label>
						</div>
					</fieldset>
				</form>
			</section>
		</section>
	</body>
</html>
