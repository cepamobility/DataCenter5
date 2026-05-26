<%@page import="java.util.Calendar"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collections"%>
<%@page import="communication.administration.corporation.tables.ItemData"%>
<%@page import="communication.datatype.tables.EcolabDriverData"%>
<%@page import="communication.driver.tables.TelematicControlData"%>
<%@page import="communication.datatype.tables.EcolabReportData"%>
<%@page import="communication.security.SecurityConstants"%>
<%@page import="communication.language.Text"%>
<%@page import="communication.administration.user.UserSessionData"%>
<%@page language="java"	import="business.SystemAccess"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.LinkedList"%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<html:html>

<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData = sys.getUserSessionData();
	Text messages = sys.getText();

	if(userSessionData.isGerente() || userSessionData.isAdministrator()) {
		Collection<ItemData> leavesDpt = userSessionData.getFilter().getDivOrgLeaves(1);
		Collection<ItemData> leavesDistrit = userSessionData.getFilter().getDivOrgLeaves(2);

		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		int month = c.get(Calendar.MONTH) + 1;
		int year = c.get(Calendar.YEAR);
%>
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
		<title>Ecolab - Report DC</title>
		<link rel="shortcut icon" type="image/ico" href="images/favicon.ico">
		<link rel="stylesheet" href="styles/fonts/pontano_sans.css">
		<link rel="stylesheet" href="styles/base.css">
		<link rel="stylesheet" href="styles/jquery-ui-1.10.3.custom.min.css">
		<!--[if lt IE 9]>
			<script type="text/javascript" src="scripts/html5shiv.min.js"></script>
		<![endif]-->

		<style type="text/css">
			#speeds {
			}
		</style>
		<script type="text/javascript" src="./js/jquery.min.js"></script>
		<script type="text/javascript" src="./js/cepaglobal.js"></script>
		<script type="text/javascript" src="./js/angular.min.js"></script>
		<script type="text/javascript" src="./js/angular-route.js"></script>
		<script type="text/javascript" src="./js/angular-sanitize.min.js"></script>
		<script type="text/javascript" src="./js/plugins.js"></script>
		<script type="text/javascript" src="./js/jsapi.js"></script>
		<script type="text/javascript" src="./js/controllers.js"></script>	

	</head>
	<body>
      	<header id="header">
         	<section class="grid_container">
				<h1 class="customer_logo" style="background-image: url('images/main_logo_large.png');">Ecolab</h1>
            	<div class="toolbar">
               		<span class="username"><%=userSessionData.getFullName()%></span>
               		<span class="exit">
               			<a href="login.jsp"><%=messages.getText("generic.messages.logout")%></a>
               		</span>
            	</div>
         	</section>
      	</header>
		<form name="logout" action="./logout.jsp" method="post"></form>
    	<section id="content" ng-app="app" >
			<section class="grid_container">
				<form name="ecolab" action="./ecolab.jsp" method="post">
					<fieldset id="username_block" class="active">
						 <div class="titleReport" align="left">
						 	<div id="name"><h1 class="customer_logo" >Ecolab - Reporte General</h1></div>
						 </div>
<%		if(request.getParameter("error") != null) {
%>
	    				<div id="general">
	    					<div id="contenidos">
		    					<div id="columna">
		    						<span class="ecolabError">Selecione Departamento ou Distrito</span>
		    					</div>
							 </div>
						 </div>
<%		}
		if(leavesDistrit.size() == 1) {
%>						<input type="hidden" name="dpto" />
						<input type="hidden" name="distrit" value="<%=leavesDistrit.iterator().next().getId().toString()%>" />	
<%		}else {
%>
	    				<div id="general">
	    					<div id="contenidos">
		    					<div id="columna">
		    						<span class="ecolabParameter">Departamento:</span>
		    					</div>
<%			Collections.sort((List)leavesDpt);
			Iterator<ItemData> it = leavesDpt.iterator();
%>								<div id="columna">
			    					<select name="dpto" >
<%			if(leavesDpt.size() > 1) {
%>			    						<option value=''><%=messages.getText("generic.messages.all.pl") %></option>
<%			}
			while(it.hasNext()) {
				ItemData item = it.next();	
%>										<option value='<%=String.valueOf(item.getId())%>' <%=(leavesDpt.size() == 1) ? "selected" : ""%>><%=item.getName()%></option>
<%			}	
%>		    						</select>
								</div>
		    				</div>
	    					<div id="contenidos">
		    					<div id="columna">
		    						<span class="ecolabParameter">Distrito:</span>
		    					</div>
<%
			Collections.sort((List)leavesDistrit);
			it = leavesDistrit.iterator();
%>								<div id="columna">
			    					<select name="distrit" >
<%			if(leavesDistrit.size() > 1) {
%>			    						<option value=''><%=messages.getText("generic.messages.all.pl") %></option>
<%			}
			while(it.hasNext()) {
				ItemData item = it.next();	
%>										<option value='<%=String.valueOf(item.getId())%>' <%=(leavesDistrit.size() == 1) ? "selected" : ""%>><%=item.getName()%></option>
<%			}	
%>			    					</select>
								</div>
		    				</div>
<%		}
%>	    					<div id="contenidos">
		    					<div id="columna">
		    						<span class="ecolabParameter">MÊS:</span>
		    					</div>
		    					<div id="columna">
		    						<select name="month">
<%							for(int i = 1; i < 13; i++) {
%>									<option value='<%=String.valueOf(i)%>' <%=(month == i) ? "selected" : ""%>><%=messages.getText("generic.month."+i)%></option>
<%							}	
%>			    					</select>
								</div>
							</div>
	    					<div id="contenidos">
		    					<div id="columna">
		    						<span class="ecolabParameter">ANO:</span>
		    					</div>
		    					<div id="columna">
		    						<select name="year">
<%							for(int i = 2014; i < 2020; i++) {
%>									<option value='<%=String.valueOf(i)%>' <%=(year == i) ? "selected" : ""%>><%=String.valueOf(i)%></option>
<%							}	
%>			    					</select>
								</div>
		    				</div>
	    				</div>
						 <div class="titleReport" align="right">
						 	<div id="name">
						 		<input class="button" type="submit" value="Abrir" onclick="openReport();">
						 	</div>
						 </div>
	    			</fieldset>
	    		</form>
			</section>
		</section>
		<script type="text/javascript" src="scripts/json2.min.js"></script>
		<!--[if lt IE 9]>
			<script type="text/javascript" src="scripts/jquery-1.11.1.min.js"></script>
		<![endif]-->
		<!--[if gte IE 9]><!-->
			<script type="text/javascript" src="scripts/jquery-2.0.2.min.js"></script>
		<!--<![endif]-->
		<script type="text/javascript" src="scripts/jquery-ui-1.10.3.custom.min.js"></script>
		<script type="text/javascript" src="scripts/jquery.ui.datepicker-pt.js"></script>
		<script type="text/javascript" src="scripts/jquery.ui.datepicker-es.js"></script>
		<script type="text/javascript" src="scripts/jquery.tadeouy.loggable.js"></script>
		<script type="text/javascript" src="scripts/jquery.tadeouy.assessmentController.js"></script>
		<script type="text/javascript" src="scripts/jquery.tadeouy.question.js"></script>
		<script type="text/javascript" src="scripts/jquery.tadeouy.card.js"></script>
		<script type="text/javascript" src="scripts/jquery.tadeouy.assessment.js"></script>
		<script type="text/javascript" src="scripts/assessment.js"></script>
	</body>
<%		}
%>
</html:html>