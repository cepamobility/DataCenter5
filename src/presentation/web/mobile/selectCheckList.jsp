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
		<script type="text/javascript">
		
		function doCheckList(type,vehicle) {
            var form = document.getElementById('checklist');
            form.checklist.value = type;
            form.id.value = vehicle;
            form.submit();

         }

		function doCheckListSyngetaBR(type,vehicle) {
            var form = document.getElementById('checklistSyngenta');
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
		</script>
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
		<form name="checklistForm" id='checklist' action="checklist.jsp">
			<input type="hidden" name="checklist" />
			<input type="hidden" name="id" />
		</form>
		<form name="checklistSyngentaForm" id='checklistSyngenta' action="checklistSyngentaBR.jsp">
			<input type="hidden" name="checklist" />
			<input type="hidden" name="id" />
		</form>
		<section id="content">
			<section class="grid_container">
				<form name="repeat">
					<fieldset class="title">
						<h4><%=messages.getText(title)%></h4>
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
					<fieldset>
						<div class="narrow">
							<label for="q5">
								<b>Seleccione</b>
							</label>
							<label for="q5"> 
<% 							if(sys.isCorporation(CorporationData.SYNGENTA_BR)){ 
%>								<input type="button" class="button"  value="Checklist" onClick="javascript:doCheckListSyngetaBR('95',<%=String.valueOf(data.getId())%>)"/>	
								
<%							}else{ 
%>								<input type="button" class="button"  value="Seguimiento en Ruta" onClick="javascript:doCheckList('79',<%=String.valueOf(data.getId())%>)"/>	
<% 								if(!sys.isCorporation(new int[]{CorporationData.FORESTALORIENTAL, CorporationData.MONTES_DE_PLATA, CorporationData.WEYERHAEUSER})){ 
%>								<input type="button" class="button"  value="Check List" onClick="javascript:doCheckList('80',<%=String.valueOf(data.getId())%>)"/>
<%								}else {
%>								<input type="button" class="button"  value="Chequeo Vehicular" onClick="javascript:doCheckList('80',<%=String.valueOf(data.getId())%>)"/>	
								<input type="button" class="button" style="background-color: #E74405 !important; background: linear-gradient(to bottom, #E74405 0%, #E74405 100%)  !important; color: #FFFFFF !important;" value="Nuevo Chequeo Preventivo" onClick="javascript:doCheckList('123',<%=String.valueOf(data.getId())%>)"/>	
<%								}
							}
%>	
							</label>
						</div>
					</fieldset>
				</form>
			</section>
		</section>
	</body>
</html>
