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
				<div id="videocontainer" style="display: none;">
					<div style="float: left;">
					<!--  <img src='menu/images/Shot_Camera-icon.png' style="height:100px;width:100px;" onclick="takepicture();"/>-->	
						<!-- <div id="videocontainerh"> -->
					</div>
					<video id="video" style="position:fixed;top:0;left:0;width: 100%;height: 100%;" autoplay onclick="takepicture();"></video>
		 		</div>
			</section>
		</header>


		<section id="content">
			<section class="grid_container">
				<form id="assessment" name="assessment" class="checklist" action="resultCheckList2.jsp"  data-module="<%=checklist%>" data-success-redirect="<%=success%>">
					<fieldset class="title">
						<h4>
							<%=messages.getText("checklist.dpa.title"+checklist) %>
						</h4>
						<!-- h5>Data Efetivação: 07/02/2011 • Revisão: 00</h5 -->
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
<% 					for(int i=0;i<24;i++){ 
%>								<option value='<%=Integer.valueOf(i).toString()%>'><%=Integer.valueOf(i).toString()%></option>
<%					}
%>							</select> 
							:
							<select style="width:80px;" id="q34" name="q34" type="text"  >
<% 					for(int i=0;i<60;i++){ 
%>								<option value='<%=Integer.valueOf(i).toString()%>'><%=Integer.valueOf(i).toString()%></option>
<%					}
%>							</select>
						</div>
<%					if((checkId.equals("79") || checkId.equals("80") || checkId.equals("123")) && sys.isCorporation(new int[]{CorporationData.FORESTALORIENTAL, CorporationData.MONTES_DE_PLATA, CorporationData.FRAYLOG})) {
%>						<div id="qw7" data-type="1"  >
							<label for="q7"><%=messages.getText("checklist.vehicletype")%></label>
							<label class="inline">
								<select id="q7" name="q7">
<%						for(int i = 1; i <= 3; i++) {
%>									<option value='<%=String.valueOf(i)%>'><%=messages.getText("checklist.vehicletype.type"+i)%></option>
<%						}
%>								</select>
							</label>
						</div>
						<div id="qw6" data-type="1"  >
							<label for="q6"><%=messages.getText("checklist.chargetype")%></label>
							<label class="inline">
								<select id="q6" name="q6">
<%						for(int i = 1; i <= 3; i++) {
%>									<option value='<%=String.valueOf(i)%>'><%=messages.getText("checklist.chargetype.type"+i)%></option>
<%						}
%>								</select>
							</label>
						</div>
<%					}
%>					</fieldset>
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
						if(checklist.equals("79")){
							if(item.getText().equalsIgnoreCase("forestales.check.h1")){
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
							if(item.getText().equalsIgnoreCase("forestales.check.h2") ){
								preText=data.getMatricula();
							}
							if(item.getText().equalsIgnoreCase("forestales.check.h4")){
								preText=sys.getUserSessionData().getFullName();
							}
						}else
						if(checklist.equals("80") || checkId.equals("123") || checklist.equals("84")){
							if(item.getText().equalsIgnoreCase("forestales.check2.h3") || item.getText().equalsIgnoreCase("forestales.checkinternal2.h3")){
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
							}else
							if(item.getText().equalsIgnoreCase("forestales.check2.h6") || item.getText().equalsIgnoreCase("forestales.checkinternal2.h6")){
								preText=data.getMatricula();
							}
						}
												
%>						<fieldset>
							<div id="<%=divName%>" data-type="1">
								<label for="<%=qName%>"><%=messages.getText(item.getText()) %></label>
								<label class="inline"><input id="<%=qName%>" name="<%=qName%>" value='<%=preText%>' type="text"></label>
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
					if(sys.isCorporation(new int[]{CorporationData.FORESTALORIENTAL, CorporationData.FRAYLOG, CorporationData.MONTES_DE_PLATA}) && checklist.equals("80")) {
%>						<fieldset tagVG="<%=idVG%>" tagVGm="n">
							<div id="<%=divName%>" data-type="16"  data-foto="true">
<%						int itemId = item.getId().intValue();
						int options = -1;							
						String v = data.getExtradata(point.getId().intValue()-4000);
						if(v == null || v.equals("-1"))
							v = "0";
						if(point.isProcesable(data, sys.getCorporationId())) {
							if((itemId >= 17000 && itemId < 17003) || itemId == 17009 || itemId == 17010 || itemId == 17012) {
								options = 1;
							} else if(itemId == 17005 || itemId == 17006 || itemId == 17015 || itemId == 17016) {
								options = 3;
							} else {
								if(point.getId().intValue() >= 4000 && point.getId().intValue() < 4022) {
									options = 2;
								}
							} 
						} else {
							if(point.getId().intValue() >= 4000 && point.getId().intValue() < 4022) {
								options = 2;
							}
						}
						if(v.equals("0") && point.getId().intValue() >= 4000 && point.getId().intValue() < 4022) {
%>								<label style="cursor:pointer;" for="<%=qName%>" onclick="toggleVisible(this);"><%=messages.getText(item.getText()) %>
								</label>
								<label class="radio" tagV="n" style="float:left;">
									<input type="radio" idaux='<%="ids"+qName%>' tagErr="n" nameSelect="<%="s"+qName%>" name="<%=qName%>" value="1" checked='checked'>TIENE S/O
								</label>
								<label class="radio" tagV="n" style="float:left;">
									<input type="radio" idaux='<%="ids"+qName%>' tagErr="s" nameSelect="<%="s"+qName%>" name="<%=qName%>" value="2" >TIENE C/O
								</label>
								<label class="radio" tagV="n">
									<input type="radio" idaux='<%="ids"+qName%>' tagErr="n" nameSelect="<%="s"+qName%>" name="<%=qName%>" value="3">NO TIENE
								</label>
<%						} else {
 %>								<label style="cursor:pointer;" for="<%=qName%>" onclick="toggleVisible(this);">
 									<%=messages.getText(item.getText()) %>
 								</label>
								<label class="radio" tagV="n" style="float:left;">
									<input idaux='<%="ids"+qName%>' tagErr="n" type="radio" name="<%=qName%>" value="1" checked='checked'>S/O
								</label>
								<label class="radio" tagV="n"  >
									<input type="radio" idaux='<%="ids"+qName%>' tagErr="s" nameSelect="<%="s"+qName%>" name="<%=qName%>" value="2">C/O
								</label>
<%						}
%>								<label class="inline" tagV='n'><%=messages.getText("generic.messages.observations") %><input id="<%=comName%>" name="<%=comName%>" type="text"></label>
								<label class="inline" tagV="no" id='<%="ids"+qName%>' ><%="PLAZO REPARACIÓN"%>
									<select p='t' name="<%="s"+qName%>" id="<%="s"+qName%>" class="select">
<%						if(options == 1) {
%>										<option value="2">48 hs</option>
<%						} else if(options == 2) {
%>										<option value="30">s/v</option>
<%						} else if(options == 3) {
%>										<option value="0">Detención inmediata</option>
<%						} else if(options == 4) {
%>										<option value="3">72 hs</option>
<%						} else {
%>										<option value="30">30 Días</option>
										<option value="0">Detención inmediata</option>
										<option value="1">24 hs</option>
										<option value="2">48 hs</option>
										<option value="3">72 hs</option>
<%						}
%>									</select>
								</label>
								<img tagV="n" src="menu/images/Photo-Video-Slr-camera-icon.png" style="cursor:pointer;right:47px;top:20px;position: absolute;" onclick="init('<%=qName%>');"/>
								<canvas tagV="n" id="canvas<%=qName%>" style="display: none;"></canvas>
								<div tagV="n" id="foto<%=qName%>"></div>
								<input type="hidden" id="fotoSrc<%=qName%>" tag='fotoSrc' src="" foto="false" />
							</div>
						</fieldset>
<%					} else {
%> 
					<fieldset tagVG="<%=idVG%>" tagVGm="n">
						<div id="<%=divName%>" data-type="16"  data-foto="true">
							
							<label style="cursor:pointer;" for="<%=qName%>" onclick="toggleVisible(this);"><%=messages.getText(item.getText()) %></label>
							<label class="radio" tagV="n" style="float:left;"><input idaux='<%="ids"+qName%>' tagErr="n" type="radio" name="<%=qName%>" value="1" checked="checked"><%=checklist.equals("80")|| checklist.equals("84")? "S/O":"SI"%></label>
							<label class="radio" tagV="n"  ><input type="radio" idaux='<%="ids"+qName%>' tagErr="s" nameSelect="<%="s"+qName%>" name="<%=qName%>" value="2"><%=checklist.equals("80") || checklist.equals("84")? "C/O":"NO" %></label>
							<label class="inline" tagV="n"><%=messages.getText("generic.messages.observations") %><input id="<%=comName%>" name="<%=comName%>" type="text"></label>
<%						if(!checklist.equals("123")) {
%>							<label class="inline" tagV='no' id='<%="ids"+qName%>' ><%="PLAZO REPARACIÓN"%>
								<select p='t' name="<%="s"+qName%>" id="<%="s"+qName%>" class="select">
									<option value="30">30 Días</option>
									<option value="0">Detención inmediata</option>
									<option value="1">24 hs</option>
									<option value="2">48 hs</option>
									<option value="3">72 hs</option>
								</select>
							</label>
							<img tagV="n" src="menu/images/Photo-Video-Slr-camera-icon.png" style="cursor:pointer;right:47px;top:10px;position: absolute;" onclick="init('<%=qName%>');"/>
							<canvas tagV="n" id="canvas<%=qName%>" style="display: none;"></canvas>
							<div tagV="n" id="foto<%=qName%>"></div>
							<input type="hidden" id="fotoSrc<%=qName%>" tag='fotoSrc' src="" foto="false" />
<%						}
%>						</div>
					</fieldset>
<%					}
				}
			}
%> 
					<fieldset>
						<div id="qw4" data-type="1"  >
							<label for="q4"><%="LUGAR" %></label>
							<label class="inline"><input id="q4" name="q4" type="text" values="."></label>
						</div>
					</fieldset>
<%				if((checkId.equals("80") || checkId.equals("123")) && sys.isCorporation(new int[]{CorporationData.FORESTALORIENTAL, CorporationData.MONTES_DE_PLATA, CorporationData.FRAYLOG})) {
%>					<fieldset>
						<div id="qw5" data-type="1"  >
							<label for="q5"><%=messages.getText("checklist.checkplace").toUpperCase()%></label>
							<label class="inline">
								<select id="q5" name="q5">
<%					for(int i = 1; i <= 5; i++) {
%>									<option value='<%=String.valueOf(i)%>'><%=messages.getText("checklist.checkplace.place"+i)%></option>
<%					}
%>								</select>
							</label>
						</div>
<%				}
%>					</fieldset>
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


	 		var currentFacing = 'environment';
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


			
			 function stopSelected(cb){
				 if($(cb).is(':checked')){
					 $('input[name="'+$(cb).attr('rname')+'"]').prop('checked',true);
				 }
			 }
				 
				var initiated=false;
			  	var qNameBuff="";
			  	var h,w,topBack;
			  	var sourcesIds=null;

			 function off(){
				 $('#videocontainer').hide();
				 $('#videocontainerh').hide();
				 
			 }
			 function on(){
				 $('#videocontainer').show();
				 $('#videocontainerh').show();
				 
			 } 	
		 	 function init(qName) {
			 	 console.log('PRUEBA, ACCEDI AL BOTON AL MENOS');
		 		 topBack=$(window).scrollTop();
		 		 qNameBuff=qName;	
			 	  video        = document.querySelector('#video');
			   	  canvas       = document.querySelector('#canvas'+qName);
			   
				  ctx    = canvas.getContext('2d');
				  streaming    = false;
				  width  = 600;
				  height = 450;

				  
				  img = new Image(),
			      imgwidth = 172,
			      imgheight = 50;
			  	  img.src = 'menu/images/logo_cepa_agua.png';
					

				if(!initiated){
					if(navigator.userAgent.match(/Chrome/i)){
						if(sourcesIds==null){
							
							//MediaStreamTrack.getSources(
							navigator.mediaDevices.enumerateDevices().then(
								function(sourceInfos){
									$('#videocontainerh').append("<select id='video_select' style='height:100px;width:100px;'/>");
									var video_select=$('#video_select');
									var video_count=0;
									 for (var i = 0; i < sourceInfos.length; i++) {
										var option = document.createElement("option");
										    option.value = sourceInfos[i].deviceId;
										    option.text = sourceInfos[i].label;
										    if (sourceInfos[i].kind === 'audio') {
										      /*audio_count++;
										      if (option.text === '') {
										        option.text = 'Audio ' + audio_count;
										      }
										      audio_select.appendChild(option);*/
										    } else {
										      video_count++;
										      if (option.text === '') {
										        option.text = 'Video ' + video_count;
										      }
										      
										      video_select.append(option);
										      sourcesIds=sourceInfos[i].id;
										      video_select.val(sourcesIds);								      
										    }
									 }
									 if(video_count<2){
										 sourcesIds="N";
									 }
									 video_select.change(function(){sourcesIds=$('#video_select').val();initiated=false;init2();});
									 init2();
								}
								
							);
						}else{
							init2();
						}
					}else{
						init2();
					}
				   
				}else{
					on();
					 canvas.width = w;
			          canvas.height = h;
			          ctx.drawImage(img, 590 - imgwidth, 440 - imgheight, imgwidth, imgheight);
			          streaming = true;
				}
				
			  }

		 	let currentFacingMode = "environment"; // por defecto trasera

		 	function init2() {
		 	    let constraint = { video: { facingMode: { ideal: currentFacingMode } }, audio: false };

		 	    navigator.mediaDevices.getUserMedia(constraint)
		 	        .then(stream => {
		 	            video.srcObject = stream;
		 	            video.play();
		 	            initiated = true;

		 	           	if (!/Android/i.test(navigator.userAgent)) {
		 	                if (video.requestFullscreen) video.requestFullscreen();
		 	                else if (video.mozRequestFullScreen) video.mozRequestFullScreen();
		 	                else if (video.webkitRequestFullscreen) video.webkitRequestFullscreen();
		 	            }

		 	            video.addEventListener('loadedmetadata', () => {
		 	                w = video.videoWidth;
		 	                h = video.videoHeight;
		 	                canvas.width = w;
		 	                canvas.height = h;
		 	                ctx.drawImage(img, 590 - imgwidth, 440 - imgheight, imgwidth, imgheight);
		 	                streaming = true;
		 	            });

		 	            on();
		 	        })
		 	        .catch(err => {
		 	            console.error("No se pudo acceder a la cámara:", err);
		 	            alert("Error al acceder a la cámara: " + err.name);
		 	        });
		 	}

	 		
		 	 function takepicture(qName) {
		 	 	 if(qName=="undefined" || !qName){
		 	 		qName=qNameBuff;
		 	 	  }
		 	    //ctx.save();
		 	    //ctx.translate(width, 0);
		 	    //ctx.scale(-1, 1);
		 	    canvas=document.createElement("canvas");
		 	    ctx    = canvas.getContext('2d');
		 	   	h=video.videoHeight;
				w=video.videoWidth;
				canvas.width = w;
				canvas.height = h;
				 
				//ctx.drawImage(img, w - imgwidth, h - imgheight, imgwidth, imgheight);
		 	    ctx.drawImage(video, 0, 0, w, h);
		 	    //ctx.restore();
		 	    //ctx.scale(1, 1);
		 	    //ctx.drawImage(img, 590 - imgwidth, 440 - imgheight, imgwidth, imgheight);
		 	    
		 	    

		 	    upload(qName);

		 	   if (document.fullscreenElement) {
		 	       document.exitFullscreen();
		 	   } else if (document.webkitFullscreenElement) {
		 	       document.webkitExitFullscreen();
		 	   } else if (document.mozFullScreenElement) {
		 	       document.mozCancelFullScreen();
		 	   }
		 	    
		 	    off();
		 	  }

		 	function upload(qName) {
		 	    var head = /^data:image\/(png|jpeg);base64,/,
		 	        data = '',
		 	        fd = new FormData(),
		 	        xhr = new XMLHttpRequest();

		 	    data = canvas.toDataURL('image/jpeg', 0.9).replace(head, '');
				$('#fotoSrc'+qName).attr('foto','true');
				$('#fotoSrc'+qName).attr('fotoSrc',data);
				$('#foto'+qName).empty();
				$('#foto'+qName).append("<img src='data:image/jpeg;base64,"+data+"' style='max-width:"+($($('fieldset[class="title2"]')[0]).width()-20)+"px;'/>");
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
						$('img[tagV="n"]').each(
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

						//document.getElementById('q3').value = fDate;
						
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
							var hayDetencionInmediata=false;
							$('input[tagErr="s"]').each(
								function(){
									hayObs =hayObs || $(this).is(":checked") ;	
									if($(this).is(":checked") ){
										$("#"+$(this).attr('nameSelect')).each(
												function(){
													if($(this).val()=='0'){
														hayDetencionInmediata = true ;
												    }
												}
											);	
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
