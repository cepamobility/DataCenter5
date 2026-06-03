<!doctype html>


<%@page language="java" 
	errorPage="../exception.jsp" 
	import="business.*"
%>
<%@page import="communication.administration.user.UserSessionData"%>
<%@page import="communication.language.Text"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="communication.vehicle.tables.InstructorActivityData"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="business.vehicle.VehicleReportFacadeUtil"%>
<%@page import="business.vehicle.VehicleReportFacade"%>
<%@page import="presentation.translator.web.util.*"%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData = sys.getUserSessionData();
	Text messages = sys.getText();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	VehicleReportFacade vehicleReportFacade = VehicleReportFacadeUtil.getHome().create();
	List<InstructorActivityData> dataList = vehicleReportFacade.findInstructorActivityListByInstructor(userSessionData.getFilter().getLoginName(), userSessionData);
	Iterator it = dataList.iterator();
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
		<title>Actividades</title>
		<link rel="shortcut icon" type="../image/ico" href="../images/favicon.ico">
		<link rel="stylesheet" href="../styles/fonts/pontano_sans.css">
		<link rel="stylesheet" href="../styles/base.css">
		<style>
			.content { overflow: hidden; border-radius: 15px; width: 100%; max-width: 1024px; background: white; margin: 100px auto; padding-bottom: 20px; box-sizing: border-box;}
			.activitiesHeader { display: flex; padding: 8px 16px; align-items: center; background: grey; min-height: fit-content; margin-bottom: 10px; }
			.activitiesHeader h1 { margin: 0; color: white; font-size: 22px; font-weight: 500; }
			.contentContainer { display: grid; grid-template-columns: repeat(2, 1fr); grid-template-rows: repeat(4, 80px) auto; border-inline: none; border-top: none}
			.item {
				display: flex;
				gap: 10px;
				align-items: center;
				padding-left: 20px;
			}
			.item label {font-size: 1.4rem; font-weight: 700} .item span {font-size: 1.2rem}
			.observation { grid-column: span 2; flex-direction: column; align-items: start; }
		</style>
	</head>
	<body>
		<header>
			<section class="grid_container">
				<h1 class="customer_logo" style="background-image: url('../menu/<%=Util.corporationLogo(sys.getUserSessionData()) %>');">DC</h1>
				<div class="toolbar">
					<div class="cepa_logo">CEPA Safe Drive</div>
					<span class="username"><%=sys.getUserSessionData().getFilter().getFirstName()+" "+sys.getUserSessionData().getFilter().getLastName() %></span>
					<span class="exit"><a href='../login.jsp'><%=messages.getText("generic.messages.exit") %></a></span>
				</div>
			</section>
		</header>
		<section class="content">
			<div class="activitiesHeader"><h1>Actividades</h1></div>
			<% if(dataList.isEmpty()) { %>
			<span>No hay actividades ingresadas</span>
			<% } else { 
					while(it.hasNext()) {
						InstructorActivityData data = (InstructorActivityData)it.next();
						String[] categorias = data.getCategoryName();
			%>
			
			<fieldset class="contentContainer">
				<div class="item">
					<label>Instructor: </label>
					<span> <%= data.getUser().getFullName() %> </span>
				</div>
				<div class="item">
					<label>Empresa: </label>
					<span> <%= data.getCorporationName() %> </span>
				</div>
				<div class="item">
					<label>Categoría: </label>
					<span> <%= categorias[0] %> </span>
				</div>
				<div class="item">
					<label>Sub Categoría: </label>
					<span> <%= categorias[1] %> </span>
				</div>
				<div class="item">
					<label>Ubicación: </label>
					<span> <%= data.getLocation() %> </span>
				</div>
				<div class="item">
					<label>Fecha: </label>
					<span> <%= sdf.format(data.getDate()) %> </span>
				</div>
				<div class="item">
					<label>Canal de Comunicación: </label>
					<span> <%= data.getChannelName() %> </span>
				</div>
				<div class="item">
					<label>Referente: </label>
					<span> <%= data.getReferent() %> </span>
				</div>
				<div class="item observation">
					<label>Observación: </label>
					<span> <%= data.getObservation() %> </span>
				</div>
			</fieldset>
			<% 		} 
				}	
%>
		</section>
	</body>
</html>