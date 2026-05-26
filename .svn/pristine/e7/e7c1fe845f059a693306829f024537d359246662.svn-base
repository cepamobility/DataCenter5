<!doctype html>

<%@page import="java.util.Calendar"%>
<%@page import="communication.administration.corporation.tables.CorporationData"%>
<%@page import="communication.administration.corporation.tables.DivOrgData"%>
<%@page import="java.util.Date"%>
<%@page import="presentation.translator.web.util.Util"%>
<%@page import="communication.vehicle.tables.VehicleData"%>
<%@page import="java.util.Collections"%>
<%@page import="communication.activity.tables.ItemData"%>
<%@page import="communication.language.Text"%>
<%@page import="communication.activity.tables.PointData"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@page import="presentation.translator.web.util.*"%>
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

	
	String success = "surveySuccess.jsp";
	
	String version="1";//String.valueOf( new Date().getTime());
%>

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
		<title>Survey</title>
		<link rel="shortcut icon" type="image/ico" href="images/favicon.ico">
		<link rel="stylesheet" href="styles/fonts/pontano_sans.css">
		<link rel="stylesheet" href="styles/base.css">
		<link rel="stylesheet" href="styles/checklist.css">
		<link rel="stylesheet" href="styles/jquery-ui-1.10.3.custom.min.css">
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
				<div class="toolbar">
					<div class="cepa_logo">CEPA Safe Drive</div>
					<span class="username"><%=sys.getUserSessionData().getFilter().getFirstName()+" "+sys.getUserSessionData().getFilter().getLastName() %></span>
					<span class="exit"><a href='login.jsp'><%=messages.getText("generic.messages.exit") %></a></span>
				</div>
			</section>
		</header>
		<form name="logout" action="./login.jsp" method="post"></form>
		<section id="content">
			<section class="grid_container">
				<form>
					<fieldset id="username_block" class="active">
						<div>
							<br>
							<br>
							<label>
								<%=messages.getText("survey.messages.text1")%>
							</label>
							<br>
							<label>
								<%=messages.getText("survey.messages.text2")%>
							</label>
							<br>
							<br>
							<div>
					  			<input class="button" value="<%=messages.getText("generic.messages.logout")%>" onClick="javascript:document.forms['logout'].submit()" />
							</div>
						</div>
					</fieldset>  
				</form>
			</section>
		</section>
		<style type="text/css">
			.error_text{
				color:red;
			} 
		</style>
		
		<script type="text/javascript">
			var corp="survey";
		</script>
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
