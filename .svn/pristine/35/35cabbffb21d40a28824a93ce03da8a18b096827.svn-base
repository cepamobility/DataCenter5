<!doctype html>

<%@page import="communication.alert.AlertConstants"%>
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

	VehicleData data = sys.getVehicleReportFacade().findVehicleByPrimaryKey(new Integer(request.getParameter("id")),userSessionData); 
	String checklist = request.getParameter("checklist");
	
	String next = "layout.jsp?refer=vehicle/VehicleView.jsp?id="+request.getParameter("id");
	String success = "layout.jsp";
	if(userSessionData.isDPACheckList()) {
		next = "vehicle/selectCheckListDPA.jsp?vehicle="+request.getParameter("id");
		success = "vehicle/successDPA.jsp";
	}
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
		<title>DPA - Checklist</title>
		<link rel="shortcut icon" type="image/ico" href="images/favicon.ico">
		<link rel="stylesheet" href="styles/fonts/pontano_sans.css">
		<link rel="stylesheet" href="styles/base.css">
		<link rel="stylesheet" href="styles/checklist.css">
		<link rel="stylesheet" href="styles/jquery-ui-1.10.3.custom.min.css">
	</head>
	<body>
		<header>
			<section class="grid_container">
				<h1 class="customer_logo" style="background-image: url('images/main_logo_large');">DC</h1>
				<div class="toolbar">
					<div class="cepa_logo">CEPA Safe Drive</div>
					<span class="username"><%=sys.getUserSessionData().getFilter().getFirstName()+" "+sys.getUserSessionData().getFilter().getLastName() %></span>
					<span class="exit"><a href='<%=next%>'><%=messages.getText("generic.messages.exit") %></a></span>
				</div>
			</section>
		</header>


		<section id="content">
			<section class="grid_container">
				<form id="assessment" name="assessment" class="checklist" action="resultCheckList.jsp"  data-module="<%=checklist%>" data-success-redirect="<%=success%>">
					<fieldset class="title">
						<h4><%=messages.getText("checklist.dpa.title"+checklist) %></h4>
						<!-- h5>Data Efetivação: 07/02/2011 • Revisão: 00</h5 -->
					</fieldset>

					<fieldset>
						<div class="narrow" id="qw1" data-type="2">
							<label for="q1"><%=messages.getText("generic.vehicle.vehicledata.matricula") %></label>
							<input id="q1" name="q1" type="hidden" value="<%=String.valueOf(data.getId())%>"><%=data.getMatricula()%>
						</div>
						<div class="narrow" id="qw3" data-type="2">
							<label for="q3"><%=messages.getText("generic.messages.date") %></label>
							<input id="q3" name="q3" type="date" >
						</div>
					</fieldset>


<%
			CheckListData checkList = sys.getActivityReportFacade().findCheckList(new Integer(checklist),userSessionData);	
			Collection points = checkList.getPoints();
			Collections.sort((List)points);
			Iterator pointIt = points.iterator();
			Integer idVG=null;
			while(pointIt.hasNext()) {
				PointData point = (PointData) pointIt.next();
				Iterator<ItemData> itemsIt = point.getSortItems();
				ItemData item = itemsIt.next();
				if(item.getType().intValue() > 2) {
%>						<fieldset class="title">
							<h5><%=messages.getText(point.getText()) %></h5>
						</fieldset>
<%					do {
						String divName = "qw"+String.valueOf(item.getId());
						String qName = "q"+String.valueOf(item.getId());
%>						<fieldset>
							<div id="<%=divName%>" data-type="1">
								<label for="<%=qName%>"><%=messages.getText(item.getText()) %></label>
								<label class="inline"><input id="<%=qName%>" name="<%=qName%>" type="text"></label>
							</div>
						</fieldset>
<% 						if(itemsIt.hasNext()) {
							item = itemsIt.next();
						}else {
							item = null;
						}
					} while (item != null);
				}else {
					String divName = "qw"+String.valueOf(item.getId());
					String qName = "q"+String.valueOf(item.getId());
					String comName = "q"+String.valueOf(item.getId())+"comment";
					if(item.getType().intValue() == 1) {
%>					<fieldset class="title">
						<h5 style="cursor:pointer;" onclick="toggleVisibleGroup(<%=item.getId()%>)"><%=messages.getText(point.getText()) %></h5>
					</fieldset>
<%					idVG=item.getId();
					}
%>
					<fieldset tagVG="<%=idVG%>" tagVGm="n">
						<div id="<%=divName%>" data-type="15">
							<label style="cursor:pointer;" for="<%=qName%>" onclick="toggleVisible(this);" ><%=messages.getText(item.getText()) %></label>
							<label class="radio" tagV="n" style="float:left;" ><input type="radio" name="<%=qName%>" value="1" checked="checked"><%=messages.getText("checklist.dpa.yes") %></label>
							<label class="radio" tagV="n" ><input type="radio" r="t" name="<%=qName%>" value="2"><%=messages.getText("checklist.dpa.no") %></label>
							<label class="inline" tagV="n"><%=messages.getText("generic.messages.observations") %><input id="<%=comName%>" name="<%=comName%>" type="text"></label>
							<label class="checkbox" tagV="n" ><input type="checkbox" p="t" rname="<%=qName%>" onclick="stopSelected(this);" name="<%="c"+qName%>" ><%="Parar Vehiculo"%></label>
						</div>
					</fieldset>
<%				}
			}
%>
					<fieldset>
						<div id="qw4" data-type="1">
							<label for="q4"><%=messages.getText("checklist.dpa.city") %></label>
							<label class="inline"><input id="q4" name="q4" type="text"></label>
						</div>
					</fieldset>
					<fieldset class="title">
						<h5><%=messages.getText("generic.messages.observations") %></h5>
					</fieldset>
					<fieldset>
						<div id="qw2" data-type="11">
							<textarea id="q2" name="q2" cols="50" rows="4"><%=messages.getText("checklist.dpa.noobs") %></textarea>
						</div>
					</fieldset>
					<fieldset class="title">
						<h5><%=messages.getText("generic.activity.result") %></h5>
					</fieldset>
					<fieldset>
						<div id="qw0" data-type="3">
							<label class="radio"><input type="radio" name="q0" value="1"><%=messages.getText("generic.messages.approved").toUpperCase() %></label>
							<label class="radio"><input type="radio" name="q0" value="2"><%=messages.getText("generic.messages.notapproved").toUpperCase() %></label>
						</div>
					</fieldset>
					<input type="submit">
				</form>
			</section>
		</section>
		<div id="confirm_message" style="display:none;">
			Componente(s) RECUSADO(S):
			__questions__
			Tem certeza?
			<br>
			<img style='display:none;' width="225px" class='loader' src='menu/images/ajax-loader.gif'/>
			<br>
			
			<button class="button confirm_answers_ok"><%=messages.getText("generic.messages.confirm") %></button>
			<button class="button confirm_answers_no"><%=messages.getText("generic.messages.cancel") %></button>
		</div>
		<script type="text/javascript" src="scripts/json2.min.js"></script>
		<script type="text/javascript" src="scripts/jquery-2.0.2.min.js"></script>
		<script type="text/javascript" src="scripts/jquery-ui-1.10.3.custom.min.js"></script>
		<script type="text/javascript" src="scripts/jquery.ui.datepicker-pt.js"></script>
		<script type="text/javascript" src="scripts/jquery.ui.datepicker-es.js"></script>
		<script type="text/javascript" >
		 var srcImgWarning='menu/images/redFlag.png';
		 var corp="DPA";
		 function stopSelected(cb){
			 if($(cb).is(':checked')){
				 $('input[name="'+$(cb).attr('rname')+'"]').prop('checked',true);
			 }
		 }

		</script>
		<script type="text/javascript" src="scripts/jquery.tadeouy.loggable.js"></script>
		<script type="text/javascript" src="scripts/jquery.tadeouy.assessmentController.js"></script>
		<script type="text/javascript" src="scripts/jquery.tadeouy.question.js"></script>
		<script type="text/javascript" src="scripts/jquery.tadeouy.card.js"></script>
		<script type="text/javascript" src="scripts/jquery.tadeouy.assessment.js"></script>
		<script type="text/javascript" src="scripts/assessment.js"></script>
		
		<script type="text/javascript">
		function toggleVisible(t){
			$(t).parent().find('label').each(
					function(){
						if($(this).attr('tagV')=='n' || $(this).attr('tagV')=='s'){
							if($(this).attr('tagV')=='n'){
								$(this).css("display","block");
								$(this).attr('tagV','s');
							}else{
								$(this).css("display","none");
								$(this).attr('tagV','n');
							}
						}	
					}
			);
			$(t).parent().find('img').each(
					function(){
						if($(this).attr('tagV')=='n' || $(this).attr('tagV')=='s'){
							if($(this).attr('tagV')=='n'){
								$(this).css("display","block");
								$(this).attr('tagV','s');
							}else{
								$(this).css("display","none");
								$(this).attr('tagV','n');
							}
						}	
					}
			);
		}
		$(document).ready(
				function(){
					$('label[tagV="n"]').each(
							function(){
								$(this).css("display","none");
							}	
					);

					$('img[tagV="n"]').each(
							function(){
								$(this).css("display","none");
							}	
					);


					$('fieldset[tagVGm="n"]').each(
							function(){
								$(this).css("display","none");
							}
					);
						
				}

		);
	  	
		function toggleVisibleGroup(n){
			$(document).find('fieldset').each(
					function(){
						if($(this).attr('tagVG')==n ){
							if($(this).attr('tagVGm')=='n'){
								$(this).css("display","block");
								$(this).attr('tagVGm','s');
							}else{
								$(this).css("display","none");
								$(this).attr('tagVGm','n');
							}
						}	
					}
			);
			
		}
	
		</script>
		
	</body>
</html>
