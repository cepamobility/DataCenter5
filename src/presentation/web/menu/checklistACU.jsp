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

	
	VehicleData data = sys.getVehicleReportFacade().findVehicleByPrimaryKey(new Integer(request.getParameter("id")),userSessionData); 
	//Id=79/80	
	String checklist = request.getParameter("checklist");
	
	String next = "layout.jsp?refer=vehicle/VehicleView.jsp?id="+request.getParameter("id");
	String success = "layout.jsp";
	String path="";
	if(userSessionData.isDPACheckList()) {
		next = "vehicle/selectCheckListFOSA.jsp?vehicle="+request.getParameter("id");
		if(request.getParameter("fu")!=null){
			success = "../vehicle/successUPM.jsp";
			path="../";
		}else{
			success = "vehicle/successUPM.jsp";	
		}
		
	}
	
	String checkId=checklist;
	
	String version=String.valueOf( new Date().getTime());
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
		<title>Checklist</title>
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
				<h1 class="customer_logo" style="background-image: url('../menu/<%=Util.corporationLogo(sys.getUserSessionData()) %>');">DC</h1>
				<div class="toolbar">
					<div class="cepa_logo">CEPA Safe Drive</div>
					<span class="username"><%=sys.getUserSessionData().getFilter().getFirstName()+" "+sys.getUserSessionData().getFilter().getLastName() %></span>
					<span class="exit"><a href='<%=next%>'><%=messages.getText("generic.messages.exit") %></a></span>
				</div>
			</section>
		</header>


		<section id="content">
			<section class="grid_container">
				<form id="assessment" name="assessment" class="checklist" action="resultCheckListACU.jsp"  data-module="<%=checklist%>" data-success-redirect="<%=success%>">
					<fieldset class="title">
						<h4>
							<%=messages.getText("checklist.dpa.title"+checklist) %></h4>
						
						
					</fieldset>

					<fieldset>
						<div class="narrow" id="qw1" data-type="2">
							<label for="q1"><%=messages.getText("generic.vehicle.vehicledata.matricula") %></label>
							<input id="q1" name="q1" type="hidden" value="<%=String.valueOf(data.getId())%>"><%=data.getMatricula()%>
						</div>
						<div class="narrow" id="qw3" data-type="2">
							<label for="q3"><%=messages.getText("generic.messages.date") %></label>
							<input id="q3" name="q3" type="date"  >
						</div>
						<div class="narrow" id="qw33" data-type="22">
							<label for="q33"><%="Hora" %></label>
							<select id="q33" name="q33" type="text"  style="width:80px;" style="float: left;">
								<% for(int i=0;i<24;i++){ %>
									<option value='<%=Integer.valueOf(i).toString()%>'><%=Integer.valueOf(i).toString()%></option>
								<%}%>
							</select> 
							:
							<select style="width:80px;" id="q34" name="q34" type="text"  >
								<% for(int i=0;i<60;i++){ %>
									<option value='<%=Integer.valueOf(i).toString()%>'><%=Integer.valueOf(i).toString()%></option>
								<%}%>
							</select>
						</div>
						
					</fieldset>


<%
			CheckListData checkList = sys.getActivityReportFacade().findCheckList(new Integer(checkId),userSessionData);	
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
						String preText=".";
						
						if(item.getText().equalsIgnoreCase("acu.check.h1")){
							Iterator iterDiv=data.getDivOrgsIterator();
							if(iterDiv.hasNext()){
								DivOrgData divOrg = (DivOrgData)iterDiv.next();
								if(sys.isCorporation(CorporationData.WEYERHAEUSER)){
									if(iterDiv.hasNext()){
										divOrg = (DivOrgData)iterDiv.next();
										if(iterDiv.hasNext()){
											 divOrg = (DivOrgData)iterDiv.next();	
										}
										
									}
								}
								communication.administration.corporation.tables.ItemData item1 = (communication.administration.corporation.tables.ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
						        while(item1.getSubItems() != null) {
						            item1 = (communication.administration.corporation.tables.ItemData)item1.getSubItems().getItemIterator().next();
						        }
								preText= item1.getName();
								
							}
						}
						if(item.getText().equalsIgnoreCase("acu.check.h2") ){
							preText=data.getMatricula();
						}
						if(item.getText().equalsIgnoreCase("acu.check.h4")){
							preText=sys.getUserSessionData().getFullName();
						}
						
												
%>						<fieldset>
							<div id="<%=divName%>" data-type="1">
								<% if( !item.getText().equals("acu.check2.h4")){ %>
									<label for="<%=qName%>"><%=messages.getText(item.getText()) %></label>
									<label class="inline"><input id="<%=qName%>" name="<%=qName%>" value='<%=preText%>' type="text"></label>
								<%}else{%>
									<label for="<%=qName%>"><%=messages.getText(item.getText()) %></label>
									<select id="<%=qName%>" name="<%=qName%>" class="select">
										<option value="" selected="selected">Seleccionar Servicio</option>
										<option value="1">Serv. Auxilio</option>
										<option value="2">Esc. Conducción</option>
										<option value="3">Otros</option>
									</select>
								<%}%>
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
						<h5 style="cursor:pointer;" onclick="toggleVisibleGroup(<%=item.getId()%>)"><%= messages.getText(point.getText()) %></h5>
					</fieldset>
<%					idVG=item.getId();
					}
					
	
%> 
					<fieldset tagVG="<%=idVG%>" tagVGm="n">
						<div id="<%=divName%>" data-type="16">
							<label style="cursor:pointer;" for="<%=qName%>" onclick="toggleVisible(this);"><%=messages.getText(item.getText()) %></label>
							<label class="radio" tagV="n" style="float:left;"><input idaux='<%="ids"+qName%>' tagErr="n" type="radio" name="<%=qName%>" value="1" checked="checked">S/O</label>
							<label class="radio" tagV="n" style="float:left;" ><input type="radio" idaux='<%="ids"+qName%>' tagErr="s" name="<%=qName%>" value="2">R/A</label>
							<label class="radio" tagV="n"  ><input type="radio" idaux='<%="ids"+qName%>' tagErr="s" name="<%=qName%>" value="3">N/A</label>
							<label class="inline" tagV="n"><%=messages.getText("generic.messages.observations") %><input id="<%=comName%>" name="<%=comName%>" type="text"></label>
						</div>
					</fieldset>
<%				}
			}
%> 
					<fieldset>
						<div id="qw4" data-type="1"  >
							<label class="inline" ><%="SEDE"%>
								<select id="q4" name="q4" class="select">
									<option value="" selected="selected">Seleccionar SEDE</option>
									<option value="1">Estación Colonia y Yì</option>
									<option value="2">Local Ayala</option>
									<option value="3">Sucursal Maldonado</option>
									<option value="4">Ciudad de la Costa</option>
								</select>
							</label>
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
					<fieldset class="title" style='display: none;'>
						<h5><%=messages.getText("generic.activity.result") %></h5>
					</fieldset>
					<fieldset>
						<div id="qw0" data-type="3" style='display: none;'>
							<label class="radio"><input type="radio" name="q0" id="resultap" value="1" checked><%=messages.getText("generic.messages.approved").toUpperCase() %></label>
							<label class="radio"><input type="radio" name="q0" id="resultapco" value="3" ><%=messages.getText("generic.activity.note.apruvedwithobservations").toUpperCase() %></label>
							<label class="radio"><input type="radio" name="q0" id="resultnoap" value="2" ><%=messages.getText("generic.messages.notapproved").toUpperCase() %></label>
						</div>
					</fieldset>
					<input type="submit">
				</form>
			</section>
		</section>
		<div id="confirm_message" style="display:none;">
			Con Observaciones:
			__questions__
			Esta seguro?
			<br>
			<img style='display:none;' width="225px" class='loader' src='<%=path%>menu/images/ajax-loader.gif'/>
			<br>
			<button class="button confirm_answers_ok"><%=messages.getText("generic.messages.confirm") %></button>
			<button class="button confirm_answers_no"><%=messages.getText("generic.messages.cancel") %></button>
		</div>
		<script type="text/javascript">
			var corp='UPM';
			var srcImgWarning='<%=path%>menu/images/redFlag.png';
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
			}
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
		<script type="text/javascript">
			$(document).ready(
					function(){
						$('label[tagV="n"]').each(
								function(){
									$(this).css("display","none");
								}	
						);
						$('label[tagV="no"]').each(
								function(){
									$(this).css("display","none");
								}	
						);

						$('fieldset[tagVGm="n"]').each(
								function(){
									$(this).css("display","none");
								}
						);
 
					
						var date=new Date();

						var fDate = date.toISOString().split('T')[0];

						document.getElementById('q3').value = fDate;);
						$('#q33').val(date.getHours());
						$('#q34').val(date.getMinutes());

								
						<%if(!checklist.equals("79")){%>
							$('input[tagErr="s"]').each(
									function(){
										$(this).change(
											function(){
												if( $(this).is(":checked") ){
													$("#"+$(this).attr('idaux')).css('display','block');
												}else{
													$("#"+$(this).attr('idaux')).css('display','none');
												}	
											}
										);
									}	
							);
						<%}%>

						$('#resultnoap').prop('disabled',true);
						$('#resultapco').prop('disabled',true);
						$('#resultap').prop('disabled',true);
						
						$('input[tagErr="s"]').each(
								function(){
									$(this).change(
											generateResult
									);
								}
						);	

						$('input[tagErr="n"]').each(
								function(){
									$(this).change(
											generateResult
									);
								}
						);	

						$("select[p='t']").each(
								function(){
									$(this).change(
											generateResult
									);
								}
						);	
						
						function generateResult(){
							var result="1";//Aprobado
							var hayObs=false;
							$('input[tagErr="s"]').each(
								function(){
									hayObs =hayObs || $(this).is(":checked") ;	
								}
							);
							var hayDetencionInmediata=false;
							$("select[p='t']").each(
									function(){
										if($(this).val()=='0'){
											hayDetencionInmediata = true ;
									    }
									}
								);	

							if(hayDetencionInmediata){
								$('#resultnoap').prop('checked','checked');
							}else if(hayObs){
								$('#resultapco').prop('checked','checked');
							}else{
								$('#resultap').prop('checked','checked');
							}	
							
						}

						
						$('input[tagErr="n"]').each(
								function(){
									$(this).change(
										function(){
											if( $(this).is(":checked") ){
												$("#"+$(this).attr('idaux')).css('display','none');
											}else{
												$("#"+$(this).attr('idaux')).css('display','block');
											}	
										}
									);
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

			session();
			
			function session(){
				setTimeout(checkSession,1000*60*10);
			}
			
			function checkSession(){
				$.ajax({async:true,url:'/datacenter/index2.jsp',success:function(){session();}});
			}
		</script>
	</body>
</html>
