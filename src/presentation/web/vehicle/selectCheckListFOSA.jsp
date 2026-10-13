<!doctype html>


<%@page language="java" 
	errorPage="../exception.jsp" 
	import="business.*"
%>
<%@page import="communication.administration.user.UserSessionData"%>
<%@page import="communication.datatype.tables.TypeTradeDataType"%>
<%@page import="communication.datatype.DataTypeConstants"%>
<%@page import="java.util.LinkedList"%>
<%@page import="communication.vehicle.tables.TrailerMainData"%>
<%@page import="communication.administration.corporation.tables.CorporationData"%>
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
<%@page import="communication.security.SecurityConstants"%>
<%@page import="presentation.translator.web.util.*"%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData = sys.getUserSessionData();
	Text messages = sys.getText();
	
	String id = request.getParameter("vehicle");
	String title = "vehicle.found";
	if(id == null) {
		id = (String)userSessionData.getValue();
		title = (String) session.getAttribute("Msg");
	}
	VehicleData data = sys.getVehicleReportFacade().findVehicleByPrimaryKey(new Integer(id), userSessionData);
	
	boolean hasPendingCheck = sys.getVehicleReportFacade().hasPendingCheck(new Integer(id), 80, userSessionData);
	
	Collection trade = new LinkedList();
	trade.add(new OptionItem(messages.getText("generic.messages.select"),""));                
	Iterator iter = sys.getDataTypeReportFacade().findAll(DataTypeConstants.TYPETRADE,
			userSessionData).iterator();
	while(iter.hasNext()) {
		String value = ((TypeTradeDataType)iter.next()).getResourceBundleId();
		trade.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
	}
	session.setAttribute("trade",trade);
%>
<style>
<!--
.button-red {
	background-color: red !important;
}
-->
</style>
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
		<title>Checklist</title>
		<link rel="shortcut icon" type="../image/ico" href="../images/favicon.ico">
		<link rel="stylesheet" href="../styles/fonts/pontano_sans.css">
		<link rel="stylesheet" href="../styles/base.css">
		<link rel="stylesheet" href="../styles/checklist.css">
		<link rel="stylesheet" href="../styles/jquery-ui-1.10.3.custom.min.css">
		<script type="text/javascript">
		
		function doCheckList(type,vehicle) {
            var form = document.getElementById('checklist');
            form.checklist.value = type;
            form.id.value = vehicle;
            form.submit();

         }

		function doCheckList2(type,vehicle) {
            var form = document.getElementById('checklist2');
            form.checklist.value = type;
            form.vehicle.value = vehicle;
            form.submit();
        }
		function doCheckListMP(type,vehicle) {
            var form = document.getElementById('checklistMP');
            form.checklist.value = type;
            form.id.value = vehicle;
            form.submit();
        }
		
		function doCheckList3(type,vehicle) {
            var form = document.getElementById('checklist3');
            form.checklist.value = type;
            form.id.value = vehicle;
            form.submit();
        }
		function doCheckList4(type,vehicle) {
            var form = document.getElementById('checklistSyngenta');
            form.checklist.value = type;
            form.id.value = vehicle;
            form.submit();
        }
		function deleteTrailer(vehicle,trailer){
			$.ajax(
					{
						async:true,
						url:'../TrailersTablet.do?op=delete&vehicle='+vehicle+'&trailer='+trailer,
						success:function(){
								$('#t'+trailer).hide('slow',function(){$(this).remove();});
							}
					}
				);	
		 }
		 
		function newTrailer(vehicle){
				var plateValue=plate.value;
				var tradeValue=trade.value;
				var tradeText="";
				$('#trade').find("option[value='"+tradeValue+"']").each(
						function(){
							tradeText=$(this)[0].innerHTML;	
						}	
				);
				var modelValue= model.value;
				var yearValue=year.value;
				var typeValue= type.value;
				var controlValue= $("#weightControl").is(":checked");
				var absValue= $("#abs").is(":checked");
				var suspensionValue= $("#suspension").is(":checked");
				if(plateValue==""){
					alert('Indique Placa');
				}else
				if(tradeValue==""){
					alert('Indique Marca');
				}else	
				if(modelValue==""){
					alert('Indique Modelo');
				}else
				if(yearValue==""){
					alert('Indique año');
				}else
				if(typeValue==""){
					alert('Indique tipo');
				}	
				else{

					$.ajax(
							{
								async:true,
								url:'../TrailersTablet.do?op=create&vehicle='+vehicle+
																   '&plate='+plateValue+
																   '&trade='+tradeValue+
																   "&model="+modelValue+
																   "&year="+yearValue+
																   "&type="+typeValue+
																   "&control="+controlValue+
																   "&abs="+absValue+
																   "&suspension="+suspensionValue,
								dataType:'JSON',
								success:function(trailer){

										var trailerId=trailer.trailerId;
										
										$('#grid').append(
												'<tr align=center align="right" class="linetwo" id="t'+trailerId+'">' +
													'<td width="10%" align="right">'+
														'<input type="button"  class="button"  value="Quitar" onClick="deleteTrailer('+vehicle+','+trailerId+');"/>'+
								            		'</td>'+						                
													'<td width="30%" align="right">'+
														plateValue+
							           				'</td>'+
									            	'<td width="30%" align="right">'+
									            		tradeText+ 
										            '</td>'+						                
										            '<td width="30%" align="right">'+
										            	modelValue+
										            '</td>'+						                
												'</tr>'
										);

										plate.value='';
										model.value='';
										trade.value="";
										year.value="";
										type.value="";
										$("#weightControl").prop('checked', false);
										$("#abs").prop('checked', false);
										$("#suspension").prop('checked', false);
									}
							}
						);	
					
					
				}	
		
		 }

		function checkPending(type, vehicle) {    
	    	var form = document.getElementById('pendingCheck');
            form.checklist.value = type;
            form.id.value = vehicle;
            form.submit();
		}


		function updateTrailer(vehicle){
			var plateValue=plate.value;
			var tradeValue=trade.value;
			 
			var modelValue= model.value;
			var yearValue=year.value;
			var typeValue= type.value;
			var controlValue= $("#weightControl").is(":checked");
			var absValue= $("#abs").is(":checked");
			var suspensionValue= $("#suspension").is(":checked");
			var trailerId=$("#idTrailer").val();
			if(!(trailerId>0)){
				alert('Operación invalida, primero seleccione un trailer.');
			}else
			if(plateValue==""){
				alert('Indique Placa');
			}else
			if(tradeValue==""){
				alert('Indique Marca');
			}else	
			if(modelValue==""){
				alert('Indique Modelo');
			}else
			if(yearValue==""){
				alert('Indique año');
			}else
			if(typeValue==""){
				alert('Indique tipo');
			}	
			else{

				$.ajax(
						{
							async:true,
							url:'../TrailersTablet.do?op=update&trailerId='+trailerId+
															   '&plate='+plateValue+
															   '&trade='+tradeValue+
															   "&model="+modelValue+
															   "&year="+yearValue+
															   "&type="+typeValue+
															   "&control="+controlValue+
															   "&abs="+absValue+
															   "&suspension="+suspensionValue,
							success:function(){

									$("#idTrailer").val(0);

									$('#trade').find("option[value='"+tradeValue+"']").each(
											function(){
												$('#tTrade'+trailerId)[0].innerHTML=$(this)[0].innerHTML;	
											}	     
									);
									$('#tPlate'+trailerId)[0].innerHTML=plate.value;
									$('#tModel'+trailerId)[0].innerHTML=model.value;
									
									
									
									plate.value='';
									model.value='';
									trade.value="";
									year.value="";
									type.value="";
									$("#weightControl").prop('checked', false);
									$("#abs").prop('checked', false);
									$("#suspension").prop('checked', false);

									window.scroll(0,findPos(document.getElementById("t"+trailerId)));
								}
						}
					);	
				
				
			}	
	
	 }

		function searchTrailer(vehicle){
			$('#gridsearch').find('tr[tag="search"]').each(function(){$(this).remove();});
			
			$.ajax(
					{
						async:true,
						url:'../TrailersTablet.do?op=search&plate='+plate.value,
						success:function(data){
								var trailers=$.parseJSON(data);
								
								for(var i=0; i< trailers.trailers.length;i++){
									var plateValue=trailers.trailers[i].plate;
									var modelValue=trailers.trailers[i].model;
									var yearValue=trailers.trailers[i].year;
									var tradeValue=trailers.trailers[i].trade;
									var trailerId=trailers.trailers[i].id;
									
									$('#gridsearch').append(
											'<tr tag="search" align=center align="right" class="linetwo" id="b'+trailerId+'">' +
												'<td width="10%" align="right">'+
													'<input type="button"  class="button"  value="Asociar" onClick="associateTrailer('+vehicle+','+trailerId+',\''+plateValue+'\',\''+modelValue+'\',\''+tradeValue+'\');"/>'+
							            		'</td>'+						                
												'<td width="30%" align="right">'+
													plateValue+
						           				'</td>'+
						           				'<td width="30%" align="right">'+
													tradeValue+
					           					'</td>'+
								            	'<td width="30%" align="right">'+
									            	modelValue+
									            '</td>'+						                
											'</tr>'
									);
								}

							}
					}
				);	
			}

		function associateTrailer(vehicle,trailerId,plateValue,modelValue,tradeText){
			$.ajax(
					{
						async:true,
						url:'../TrailersTablet.do?op=associate&vehicle='+vehicle+'&trailerId='+trailerId,
						success:function(){
								
								$('#grid').append(
										'<tr align=center align="right" class="linetwo" id="t'+trailerId+'">' +
											'<td width="10%" align="right">'+
												'<input type="button"  class="button"  value="Quitar" onClick="deleteTrailer('+vehicle+','+trailerId+');"/>'+
						            		'</td>'+						                
											'<td width="30%" align="right">'+
												plateValue+
					           				'</td>'+
							            	'<td width="30%" align="right">'+
							            		tradeText+ 
								            '</td>'+						                
								            '<td width="30%" align="right">'+
								            	modelValue+
								            '</td>'+						                
										'</tr>'
								);

								$("#b"+trailerId).hide('slow');
								window.scroll(0,findPos(document.getElementById("t"+trailerId))-$("t"+trailerId).outerHeight());
								 
							}
					}
				);	
			
		}

		function editTrailer(vehicle,trailerId){
			$.ajax(
					{
						async:true,
						url:'../TrailersTablet.do?op=getTrailer&trailerId='+trailerId,
						dataType:'JSON',
						success:function(data){
							
							plate.value=data.plate;
							type.value=data.type;
							model.value=data.model;
							year.value=data.year;
							trade.value=data.trade.resourceBundleId;
							if(data.abs==true || data.abs=='true'){
								$("#abs").prop("checked",true);
							}else{
								$("#abs").prop("checked",false);
							}	 
							if(data.suspension==true || data.suspension=='true'){
								$("#suspension").prop("checked",true);
							}else{
								$("#suspension").prop("checked",false);
							}	 
							if(data.weightControl==true || data.weightControl=='true'){
								$("#weightControl").prop("checked",true);
							}else{
								$("#weightControl").prop("checked",false);
							}

							$('#idTrailer').val(trailerId);	

							window.scroll(0,findPos(document.getElementById("edit"))); 
						}
					}
				);	
			
		}

		function findPos(obj) {
		    var curtop = 0;
		    if (obj.offsetParent) {
		        do {
		            curtop += obj.offsetTop;
		        } while (obj = obj.offsetParent);
		    return [curtop];
		    }
		}

		function doTrailerChecks(idVehicle){
			var form = document.getElementById('trailerCheck');
            form.vehicle.value = idVehicle;
            form.submit();
		}
		
		</script>
		 
		
	</head>
	<body>
		<header>
			<section class="grid_container">
				<h1 class="customer_logo" style="background-image: url('../menu/<%=Util.corporationLogo(sys.getUserSessionData()) %>');">DC</h1>
				<div class="toolbar">
					<div class="cepa_logo">CEPA Safe Drive</div>
					<span class="username"><%=sys.getUserSessionData().getFilter().getFirstName()+" "+sys.getUserSessionData().getFilter().getLastName() %></span>
					<span class="exit"><a href='vehicleSearchFOSA.jsp'><%=messages.getText("generic.messages.exit") %></a></span>
				</div>
			</section>
		</header>
		<form name="pendingForm" id="pendingCheck" action="../pendingChecklist.jsp">
			<input type="hidden" name="checklist" />
			<input type="hidden" name="id" />
		</form>
		<form name="checklistForm" id='checklist3' action="../checklistACU.jsp">
			<input type="hidden" name="checklist" />
			<input type="hidden" name="id" />
		</form>
		<form name="checklistForm" id='checklist' action="../checklistUPM.jsp">
			<input type="hidden" name="checklist" />
			<input type="hidden" name="id" />
		</form>
		<form name="checklistForm" id='checklistSyngenta' action="../checklistSyngenta.jsp">
			<input type="hidden" name="checklist" />
			<input type="hidden" name="id" />
			
			
		</form>
		
		<form name="checklistForm" id='checklist2' action="vehicleFastUpdate.jsp">
			<input type="hidden" name="checklist" />
			<input type="hidden" name="vehicle" />
		</form>
		<form name="checklistForm" id='trailerCheck' action="selectTrailerChecksFOSA.jsp">
			<input type="hidden" name="vehicle" />
		</form>
		
		<form name="checklistForm" id='checklistMP' action="../checklistMP.jsp">
			<input type="hidden" name="checklist" />
			<input type="hidden" name="id" />
			
			
		</form>
		<section id="content">
			<section class="grid_container">
				<form name="repeat" action="vehicleSearchFOSA.jsp">
				<fieldset class="title">
					<h4><%=messages.getText("checklist.dpa.search") %></h4>
					<!-- h5>Data Efetivação: 07/02/2011 • Revisão: 00</h5 -->
				</fieldset>
				<fieldset>
					<div class="narrow" id="qw1" data-type="2">
						<label for="q1">
							<b><%=messages.getText(title) %></b>
						</label>
					</div>
				</fieldset>
				<fieldset>
					<div class="narrow">
						<label>
							<%=messages.getText("generic.vehicle.vehicledata.matricula")+": "+data.getMatricula()%>
						</label>
						<label>
							<%=messages.getText("generic.vehicle.vehicledata.trade")+": "+messages.getText(data.getTrade().getResourceBundleId())%>
						</label>
						<label>
							<%=messages.getText("generic.vehicle.vehicledata.model")+": "+data.getModel()%>
						</label>
						<label>
							<%=messages.getText("generic.vehicle.vehicledata.type")+": "+messages.getText(data.getType().getResourceBundleId())%>
						</label>
					</div>
				</fieldset>
<%		if(sys.isCorporation(new int[]{CorporationData.FORESTALORIENTAL, CorporationData.MONTES_DE_PLATA, CorporationData.WEYERHAEUSER})) {	
%>				<fieldset>
					<div class="narrow">
						<label for="q5">
							<b>Seguimientos en Ruta</b>
						</label>
						<label for="q5">
							<input type="button" class="button"  value="Seguimiento en Ruta" onClick="javascript:doCheckList('79',<%=String.valueOf(data.getId())%>)"/>	
						</label>
					</div>
				</fieldset>
				<fieldset>
					<div class="narrow">
						<label for="q5">
							<b>Chequeos vehiculares</b>
						</label>
						<label for="q5">
							<%if (hasPendingCheck && (userSessionData.getFullName().contains("Test") || !SecurityConstants.isProductionServer())){%>
							<input type="button" class="button"  value="Chequeo Vehicular" onClick="javascript:checkPending('80',<%=String.valueOf(data.getId())%>)"/>	
								
							<%}	else {	
							%>
							<input type="button" class="button"  value="Chequeo Vehicular" onclick="javascript:doCheckList('80',<%=String.valueOf(data.getId())%>)"/>
							<%} %>
							<input type="button" class="button" style="background-color: #E74405 !important; background: linear-gradient(to bottom, #E74405 0%, #E74405 100%)  !important; color: #FFFFFF !important;" value="Chequeo Preventivo" onClick="javascript:doCheckList('123',<%=String.valueOf(data.getId())%>)"/>	
						</label>
					</div>
				</fieldset>
<%		} else{
%>				<fieldset>
					<div class="narrow">
						<label for="q5">
							<b><%=messages.getText("checklistdpa.vehicle.select")%></b>
						</label>
					</div>
				</fieldset>
				<fieldset>
					<div class="narrow">
						<label for="q5">
					<% if(sys.isCorporation(CorporationData.SYNGENTA)) { %>
					
						<input type="button" class="button"  value="<%=messages.getText("generic.activity.form.truckchecklist") %>" onClick="javascript:doCheckList4('42',<%=String.valueOf(data.getId())%>)"/>	
						<br>
						
					<% } else if(sys.getUserSessionData().isInternalCheckList()) {%>

					
						<input type="button" class="button"  value="<%=messages.getText("checklist.weyer.internal") %>" onClick="javascript:doCheckList('84',<%=String.valueOf(data.getId())%>)"/>
					<%
						}else{
					
						if(sys.isCorporation(CorporationData.CEPA)){
					%>	
							<input type="button" class="button"  value="<%=messages.getText("checklist.mp.title") %>" onClick="javascript:doCheckListMP('91',<%=String.valueOf(data.getId())%>)"/>
					<%	
						}else{
						
							if(!sys.isCorporation(CorporationData.ACU)){	
						%>
							<input type="button" class="button"  value="<%=messages.getText("generic.messages.addvehiclecheckupm1") %>" onClick="javascript:doCheckList('79',<%=String.valueOf(data.getId())%>)"/>	
							<br>
							<%}
							
							if(sys.isCorporation(CorporationData.FORESTALORIENTAL)) {%>
								<input type="button" class="button"  value="<%=messages.getText("generic.messages.addvehiclecheckupm2") %>" onClick="javascript:doCheckList2('80',<%=String.valueOf(data.getId())%>)"/>
							<%}else{
								if(sys.isCorporation(CorporationData.ACU)){
								%>
									<input type="button" class="button"  value="FORMULARIO ACU" onClick="javascript:doCheckList3('86',<%=String.valueOf(data.getId())%>)"/>
								<%}else{ 
									if(sys.isCorporation(CorporationData.MONTES_DE_PLATA)){
								%>
								
									<br/>
									<input type="button" class="button"  value="<%=messages.getText("checklist.mp.title") %>" onClick="javascript:doCheckListMP('92',<%=String.valueOf(data.getId())%>)"/>
									<br/>
									<br/>
									<input type="button" class="button"  value="<%=messages.getText("generic.messages.addvehiclecheckupm2") %>" onClick="javascript:doTrailerChecks(<%=String.valueOf(data.getId())%>);"/>
									
								<%	}else{%>
									
									<input type="button" class="button"  value="<%=messages.getText("generic.messages.addvehiclecheckupm2") %>" onClick="javascript:checkPending('80',<%=String.valueOf(data.getId())%>);"/>
							<%		}
								}%>
							<%} %>
						<%} %>		
					<%} %>	
					</label>
				</div>
			</fieldset>
<%		}
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
