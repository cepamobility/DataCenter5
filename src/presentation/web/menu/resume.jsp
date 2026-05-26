<%@page import="communication.util.tables.ResumeData"%>
<%@page import="communication.language.Text"%>
<%@page import="communication.administration.user.UserSessionData"%>
<%@page import="communication.util.tables.ResumeItem"%>
<%@page language="java"
	errorPage="../exception.jsp"
	import="business.*"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
 	UserSessionData userSessionData = sys.getUserSessionData();
	Text messages = sys.getText(); 
	ResumeData resume = sys.getCorporationReportFacade().getResume(userSessionData);
%>
<html>
	<script type="text/javascript" src="./menu/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">
        google.load('visualization', '1.0',  {'packages':['corechart']});
        google.setOnLoadCallback(startCharts);
      	function startCharts() {
			var data = google.visualization.arrayToDataTable([
			  ['Mes', 'APMM'],
			  ['Jan',  1000],
			  ['Fev',  1170],
			  ['Mar',  660],
			  ['Abr',  1030],
			  ['Mai',  1130],
			  ['Jun',  1040],
			  ['Jul',  1010],
			  ['Ago',  930],
			  ['Set',  580],
			  ['Oct',  110],
			  ['Nov',  130],
			  ['Dez',  1030]
			]);

			var options = {
					  title: '',
					  backgroundColor:'#F2F2F2',
					  series: {
					    0:{color: 'orange', visibleInLegend: true, pointSize:10}
						},

					  hAxis: {title: 'Mes',  titleTextStyle: {color: '#636363'}, textStyle: {color: '#939393'}},
					  vAxis: {title: 'APMM',  titleTextStyle: {color: '#636363'}, textStyle: {color: '#939393'}, minValue:0},
					  legend: {position: 'none'},
					  pointsVisible: 'true'
					};

			var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
			chart.draw(data, options);
		}
		function openClose(id,img,cant,prefix) {
			var imgValue = document.getElementById(img).src;
			if(imgValue.endsWith('mas.png')) {
				for(i = 1; i <= cant; i++) {
					$('#'+id+'_'+i).show();
				}
				$('#'+img).attr('src','./menu/imgs/'+prefix+'menos.png');
			}else {
				var allDivs = "div[id^="+id+"_]";
				$(allDivs).hide();
				var allImgs = "img[id^="+img+"]";
				$(allImgs).attr('src','./menu/imgs/'+prefix+'mas.png');
			}
		}
	</script>
	<style>
	@font-face {
		font-family: 'Roboto';
		src: url('./menu/font/Roboto-Regular.ttf');
	}
	body {
		background-color:#F2F2F2;
		margin-left: 40px;
		margin-top: 10px;
		margin-right: 20px;
		margin-bottom: 20px;
	}
	.title {
		color: #3F4647;
    	font-family: Roboto, Verdana, Times, serif;
    	font-size:22px;
    	padding: 5px;
    }
	.texto1 {
		color: white;
    	font-family: Roboto, Verdana, Times, serif;
    	font-size:14px;
    	padding: 5px;
    	height:20px;
    }
	.texto2 {
		color: #333333;
    	font-family: Roboto, Verdana, Times, serif;
    	font-size:12px;
    	padding: 5px;
    	height:20px;
    }
	.line {
    	height:10px;
    }
	.style1 {
    	background-image:url(./menu/imgs/barra-menu-1.png);
    	font-family: Verdana, Times, serif;
    	padding: 5px;
    	height:20px;
    }
	.style2 {
		display: none;
    	background-image:url(./menu/imgs/barra-menu-2.png);
    	font-family: Verdana, Times, serif;
    	padding: 5px;
    	height:20px;
    }
	.style3 {
		display: none;
    	background-image:url(./menu/imgs/barra-menu-3.png);
    	font-family: Verdana, Times, serif;
    	padding: 5px;
    	height:20px;
    }
	.style4 {
		display: none;
    	background-image:url(./menu/imgs/barra-menu-4.png);
    	font-family: Verdana, Times, serif;
    	padding: 5px;
    	height:20px;
    }
	.style5 {
		display: none;
    	background-image:url(./menu/imgs/barra-menu-5.png);
    	font-family: Verdana, Times, serif;
    	padding: 5px;
    	height:20px;
    }
	</style>
	<form name="driverForm" action="layout.jsp">
		<input type="hidden" name="refer" value="driver/DriverList.jsp" />
	</form>
	<form name="vehicleForm" action="layout.jsp">
		<input type="hidden" name="refer" value="vehicle/VehicleList.jsp" />
	</form>
	<form name="eventForm" action="layout.jsp">
		<input type="hidden" name="refer" value="event/EventList.jsp" />
	</form>
	<form name="activityForm" action="layout.jsp">
		<input type="hidden" name="refer" value="activity/ActivityList.jsp" />
	</form>
	<body style="bgcolor=">
		<div class="title">
			Resumen de Datos
		</div>
		<div class="line"></div>
		<div>		
			<%=resume.printDrivers(messages)%>		
		</div>
		<div class="line"></div>
		<div>		
			<%=resume.printVehicles(messages)%>		
		</div>
		<div class="line"></div>
		<div>		
			<%=resume.printEvents(messages)%>		
		</div>
		<div class="line"></div>
		<div>		
			<%=resume.printActivities(messages)%>		
		</div>
		<div class="line"></div>
		<div class="title">
			Gráfico de APMM
		</div>
		<div class="line"></div>
		<div id="chart_div">
		</div>
	</body>
</html>
