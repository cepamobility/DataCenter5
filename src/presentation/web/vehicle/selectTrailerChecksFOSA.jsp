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

		
		function doCheckList3(type,vehicle) {
            var form = document.getElementById('checklist3');
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
		<header>
			<section class="grid_container">
				<h1 class="customer_logo" style="background-image: url('../menu/<%=Util.corporationLogo(sys.getUserSessionData()) %>');">DC</h1>
				<div class="toolbar">
					<div class="cepa_logo">CEPA Safe Drive</div>
					<span class="username"><%=sys.getUserSessionData().getFilter().getFirstName()+" "+sys.getUserSessionData().getFilter().getLastName() %></span>
					<span class="exit"><a href='vehicleSearchDPA.jsp'><%=messages.getText("generic.messages.exit") %></a></span>
				</div>
			</section>
		</header>

		<form name="checklistForm" id='checklist3' action="../checklistACU.jsp">
			<input type="hidden" name="checklist" />
			<input type="hidden" name="id" />
		</form>
		<form name="checklistForm" id='checklist' action="../checklistUPM.jsp">
			<input type="hidden" name="checklist" />
			<input type="hidden" name="id" />
			
			
		</form>
		<form name="checklistForm" id='checklist2' action="vehicleFastUpdate.jsp">
			<input type="hidden" name="checklist" />
			<input type="hidden" name="vehicle" />
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
				
				<%if(sys.isCorporation(CorporationData.MONTES_DE_PLATA)){ %>
				
				<fieldset class="title">
					<h4><%="PLATAFORMAS DE CARGA"%></h4>
					<!-- h5>Data Efetivação: 07/02/2011 • Revisão: 00</h5 -->
				</fieldset>
				<fieldset>
					<div class="narrow">
						<h4><%="PLATAFORMAS DE CARGA ASOCIADAS"%></h4>
						<br/>
						<table id="grid">	
						<tr>
							<td width='3%' class="guide2" align="center">
							</td>
							<td width='32%' class="guide2" align="center">
								<%=messages.getText("vehicle.trailer.plate")%>
							</td>
		    				<td width='32%'	class="guide2" align="center">
	    						<%=messages.getText("vehicle.trailer.trade")%>
	    					</td>
							<td width='32%' class="guide2" align="center">
								<%=messages.getText("vehicle.trailer.model")%>
							</td>
						</tr>
<%
		
	Collection trailers = sys.getVehicleReportFacade().getVehicleTrailers(data.getId(),userSessionData);
	if(trailers.size() == 0) {
%>						
<%
	}else {
    			Iterator itTrailers = trailers.iterator();
    			while(itTrailers.hasNext()) {
    			    TrailerMainData trailer = (TrailerMainData)itTrailers.next();
%>						<tr align=center align="right" class="linetwo" id="t<%=trailer.getId()%>">
			            	<td width='10%' align="right">
			            		<input type="button"  class="button"  value='Quitar' onClick="deleteTrailer(<%=id%>,<%=trailer.getId()%>);"/>
			            		<input type="button"  class="button"  value='Editar' onClick="editTrailer(<%=id%>,<%=trailer.getId()%>);"/>
				            </td>						                
   							<td width='30%' align="right" id="tPlate<%=trailer.getId()%>">
									<%=trailer.getPlate()%>
	           				</td>
			            	<td width='30%' align="right" id="tTrade<%=trailer.getId()%>">
			            		<%=messages.getText(trailer.getTrade().getResourceBundleId())%> 
				            </td>						                
				            <td width='30%' align="right" id="tModel<%=trailer.getId()%>">
				            	<%=trailer.getModel()%> 
				            </td>						                
						</tr>
<%
		}
	}
%>
					</table>
				</div>
			</fieldset>
			<fieldset class="title" id="edit">
					<h4><%="CREAR, BUSCAR Y ASOCIAR PLATAFORMAS DE CARGA"%></h4>
					<!-- h5>Data Efetivação: 07/02/2011 • Revisão: 00</h5 -->
				</fieldset>
				<fieldset>
					<input type="hidden" value="0" id="idTrailer"/>
					<div class="narrow">
						<label for="plate"><%=messages.getText("generic.vehicle.vehicledata.registrationNumber")%></label>
						<input type="text" id="plate" style="width:155" class="input" />
					</div>
					<div class="narrow">
						<label for="trade"><%=messages.getText("generic.vehicle.vehicledata.trade")%></label>
						<select id="trade" class="input" style="width:155;">
						<%
							Iterator iterOp=trade.iterator();
							while(iterOp.hasNext()){
								OptionItem item=(OptionItem) iterOp.next();
								%>
									<option value="<%=item.getValue()%>" styleClass="input">
										<%=item.getLabel() %>
									</option>
								<%
							}
						%>
						</select>
					</div>
					<div class="narrow">
						<label for="model"><%=messages.getText("generic.vehicle.vehicledata.model")%></label>
						<input type="text" id="model" style="width:155" class="input" />				
					</div>
			    	<div class="narrow">
						<label for="year"><%=messages.getText("generic.vehicle.vehicledata.year")%></label>
						<input type="text" id="year" style="width:155" class="input" />			
					</div>
					<div class="narrow">
						<label for="type"><%=messages.getText("generic.vehicle.vehicledata.type")%></label>
						<select id="type" class="input" style="width:155;">
							<option value="" styleClass="input">Seleccionar</option>
							<option value="Zorra" styleClass="input">Zorra</option>
							<option value="Semi" styleClass="input">Semirremolque</option>
						</select>
					</div>
					<div class="narrow">
						<label for=abs><%=messages.getText("generic.vehicle.vehicledata.abs")%></label>
						<input type="checkbox" id="abs" class="input" />			
					</div>
					<div class="narrow">
						<label for="suspension"><%=messages.getText("generic.vehicle.vehicledata.suspension")%></label>
						<input type="checkbox" id="suspension" class="input" />			
					</div>
					<div class="narrow">
						<label for="weightControl"><%=messages.getText("generic.vehicle.vehicledata.weightcontrol")%></label>
						<input type="checkbox" id="weightControl" class="input" />			
					</div>
					<div class="narrow">
						<input type="button"  class="button"  value='Agregar Nueva' onClick="newTrailer(<%=id%>);"/>
						<input type="button"  class="button"  value='Buscar Existente' onClick="searchTrailer(<%=id%>);"/>
						<input type="button"  class="button"  value='Actualizar' onClick="updateTrailer(<%=id%>);"/>
						<br/>
						<h4 style="margin-top: 40px;"><%="PLATAFORMAS DE CARGA BUSCADAS"%></h4>
						<table id="gridsearch">	
						<tr>
							<td width='3%' class="guide2" align="center">
							</td>
							<td width='32%' class="guide2" align="center">
								<%=messages.getText("vehicle.trailer.plate")%>
							</td>
		    				<td width='32%'	class="guide2" align="center">
	    						<%=messages.getText("vehicle.trailer.trade")%>
	    					</td>
							<td width='32%' class="guide2" align="center">
								<%=messages.getText("vehicle.trailer.model")%>
							</td>
						</tr>
						</table>
					</div>
				</fieldset>
				
				
<%
	} //EN IS CORPORATION MONTES DEL PLATA
%>			
			<fieldset>
				<div class="narrow">
					<label for="q5">
					
						<input type="button" class="button"  value='<%="Continuar" %>' onClick="javascript:doCheckList('80',<%=String.valueOf(data.getId())%>)"/>
						
					</label>
				</div>
			</fieldset>
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
