<%@page import="communication.administration.corporation.tables.CorporationData"%>
<%@page import="communication.administration.dpa.UnconformityItemDPAData"%>
<%@page import="communication.administration.dpa.CorrectiveActionDPAData"%>
<%@page import="communication.administration.dpa.UnconformityDPAData"%>
<%@page import="communication.administration.dpa.UnconformityItemTypeDPADataType"%>
<%@page import="communication.datatype.DataTypeConstants"%>
<%@page import="communication.driver.tables.TelematicControlData"%>
<%@page import="persistence.driver.tables.TelematicControl"%>
<%@page import="communication.administration.dpa.UnconformityTypeDPADataType"%>
<%@page import="presentation.translator.web.dpa.DPAUnconformityForm"%>
<%@page language="java" 
	errorPage="../exception.jsp" 
	import="business.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="presentation.translator.web.vehicle.*"
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.ActivityTypeDataType"
	import="business.vehicle.VehicleReportFacade"
	import="java.util.*"	
	import="org.apache.struts.action.DynaActionForm"
	import="communication.activity.tables.*"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%!
	RequestDispatcher dispatcher; 
	Text messages; SystemAccess sys;
	UserSessionData userSessionData; 
	VehicleCheckData vehicleCheck;
	TelematicControlData telematicControl;
	VehicleData vehicleData;
	String idReferido;
	String mode;
	String id;
	UnconformityDPAData data;
	Collection correctiveActions;
	
%>
<%
	//PARA EDITAR id=488552&mode=edit, donde 488552 es el id de la inconformidad
	//PARA NUEVO  TELEMATICCONTROL telec=111&mode=new&type=UnconformityTypeDPADataType.TYPE_TELEMATIC_CONTROL donde 111 es el id del control telematico
	//PARA NUEVO  CHECK check=111&mode=new&type=UnconformityTypeDPADataType.TYPE_VEHICLE_CHECK donde 111 es el id del vehiclecheck

	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();	
	
	
	
	String check = Util.checkPermission(sys,SecurityConstants.EDIT_VEHICLE);
	String check1 = Util.checkPermission(sys,SecurityConstants.MODIFY_VEHICLE);	
	String check2 = Util.checkPermission(sys,SecurityConstants.CHECKLIST_VEHICLE);	
	String check0 = Util.checkPermission(sys,SecurityConstants.VIEW_VEHICLE_LIST);	
	
	if (check!=null && check0!=null) { 
		response.sendRedirect(request.getContextPath()+check);	
	}else if (check1!=null && check0!=null) { 
		response.sendRedirect(request.getContextPath()+check1);	
	} else if (check2!=null && check0!=null) { 
		response.sendRedirect(request.getContextPath()+check2);	
	}else {
		messages=sys.getText();
	
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
%>	

<%@page import="business.vehicle.VehicleReportFacadeBean"%>

<html:html>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<script type="text/javascript" src="./menu/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript">
		$(document).ready(
			function(){
				$('input[type="text"]').val('0');

				$('#year').change(
					function(){

						var data=null;
						$.ajax({	
							 async:false,
							 dataType:"text",
							 url:'DPAVehicleActive.do?get=true&year='+$(this).val(),
							 success:function(d){
									data=$.parseJSON(d);
								 }
							});
					 	
						$('input[type="text"]').each(
								function(){
									$(this).attr('dbid',"undefined");
									$(this).val('0');
								}
						);
						for(var i=0;i<data.values.length;i++){
							$('input[ref="'+data.values[i].reg+'"]').each(
									function(){
										if($(this).attr('id').indexOf('m'+data.values[i].month+'_')>0){
											$(this).val(data.values[i].value);
											$(this).attr('dbid',data.values[i].id);
										}			
									}
							);
						}
							
					}
				);
			}

			
		);

		function save(){
			var data="";
			var coma=false;
			$('input[type="text"]').each(
					function(){
						if(coma) data+="&";
						data+=$(this).attr('id')+"="+$(this).val()+"&r"+$(this).attr('id')+"="+$(this).attr("ref");
						if($(this).attr('dbid')!=null && $(this).attr('dbid')!="undefined"){
							data+="&id"+$(this).attr('id')+"="+$(this).attr("dbid");
						}
						coma=true;
					}
			);
			$.ajax({	
				 async:false,
				 dataType:"text",
				 url:'DPAVehicleActive.do?save=true&year='+$('#year').val()+"&"+data
				 
				}).done(
						function( ){ 
							alert('Guardado!');
				 	});

			
		}
	</script>
</head>

<body>
	 
	<center>
	<html:form action="/DPAVehicleActive" styleId="DPAVehicleActive" >
		<table width="768" border="0" cellspacing="0" cellpadding="0">  
			<tr class="guide1">
			<%
				String page14 = "../component/titlecomponent.jsp?title=Web Report Tools DPA";
			%>
				<jsp:include  page='<%=page14 %>' />
  			</tr>
      		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
  				<tr> 
			<td height="40" colspan="3" valign="middle">
<%
		String page13 = "../component/utilityboxtop.jsp?title=Veiculos Ativos";
				%>
				<jsp:include  page='<%=page13 %>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
   	 				<tr> 
       					<td width="50%" align="left" class="texts">        
   							Ano
						</td>
						<td width="50%" align="left" class="linetwo">
								<select id="year">
									<option id="selNo" value='0'>Seleccione</option>
									<%
										Calendar cal=Calendar.getInstance();
									for(int i=cal.get(Calendar.YEAR)-10; i<cal.get(Calendar.YEAR)+2;i++){
										%>
										<option value='<%= "" +i %>'><%= ""+i %></option>
									<%}%>
								</select>
						</td>
   					</tr>
   					<tr> 
       					<td colspan="2" width="100%" align="center" class="linetwo">
							<table>
								<tr>
									<td>	
											Mes
									</td> 	
									<td>	
											ITABUNA
									</td> 	
									<td>	
											PALMEIRA DAS MISSOES
									</td> 	
									<td>	
											ITUIUTABA
									</td> 
									<td>	
											ARACATUBA
									</td>
									<td>	
											GOIANIA
									</td>   
									<td>	
											MONTES CLAROS
									</td>   	
								</tr>
								<tr>
									<td>	
										<%= messages.getText("generic.month.1") %>
									</td> 	
									<td>	
										<input ref="ITABUNA" type="text" id="t2m1_1"/>
									</td>
									<td>
										<input ref="PALMEIRA DAS MISSOES" type="text" id="t2m1_2"/>	
									</td> 	
									<td>	
										<input ref="ITUIUTABA" type="text" id="t2m1_3"/>	
									</td>
									<td>	
										<input ref="ARACATUBA" type="text" id="t2m1_5"/>	
									</td>
									<td>	
										<input ref="GOIANIA" type="text" id="t2m1_6"/>	
									</td>   
									<td>	
										<input ref="MONTES CLAROS" type="text" id="t2m1_7"/>	
									</td>  	
								</tr>
								<tr>
									<td>	
										<%= messages.getText("generic.month.2") %>
									</td> 	
									<td>	
										<input ref="ITABUNA" type="text" id="t2m2_1"/>
									</td>
									<td>
										<input ref="PALMEIRA DAS MISSOES" type="text" id="t2m2_2"/>	
									</td> 	
									<td>	
										<input ref="ITUIUTABA" type="text" id="t2m2_3"/>	
									</td>
									<td>	
										<input ref="ARACATUBA" type="text" id="t2m2_5"/>	
									</td>
									<td>	
										<input ref="GOIANIA" type="text" id="t2m2_6"/>	
									</td>   
									<td>	
										<input ref="MONTES CLAROS" type="text" id="t2m2_7"/>	
									</td>  	
								</tr>
								<tr>
									<td>	
										<%= messages.getText("generic.month.3") %>
									</td> 	
									<td>	
										<input ref="ITABUNA" type="text" id="t2m3_1"/>
									</td>
									<td>
										<input ref="PALMEIRA DAS MISSOES" type="text" id="t2m3_2"/>	
									</td> 	
									<td>	
										<input ref="ITUIUTABA" type="text" id="t2m3_3"/>	
									</td>
									<td>	
										<input ref="ARACATUBA" type="text" id="t2m3_5"/>	
									</td>
									<td>	
										<input ref="GOIANIA" type="text" id="t2m3_6"/>	
									</td>   
									<td>	
										<input ref="MONTES CLAROS" type="text" id="t2m3_7"/>	
									</td>  		
								</tr>
								<tr>
									<td>	
										<%= messages.getText("generic.month.4") %>
									</td> 	
									<td>	
										<input ref="ITABUNA" type="text" id="t2m4_1"/>
									</td>
									<td>
										<input ref="PALMEIRA DAS MISSOES" type="text" id="t2m4_2"/>	
									</td> 	
									<td>	
										<input ref="ITUIUTABA" type="text" id="t2m4_3"/>	
									</td>
									<td>	
										<input ref="ARACATUBA" type="text" id="t2m4_5"/>	
									</td>
									<td>	
										<input ref="GOIANIA" type="text" id="t2m4_6"/>	
									</td>   
									<td>	
										<input ref="MONTES CLAROS" type="text" id="t2m4_7"/>	
									</td>  	 	
								</tr>
								<tr>
									<td>	
										<%= messages.getText("generic.month.5") %>
									</td> 	
									<td>	
										<input ref="ITABUNA" type="text" id="t2m5_1"/>
									</td>
									<td>
										<input ref="PALMEIRA DAS MISSOES" type="text" id="t2m5_2"/>	
									</td> 	
									<td>	
										<input ref="ITUIUTABA" type="text" id="t2m5_3"/>	
									</td>
									<td>	
										<input ref="ARACATUBA" type="text" id="t2m5_5"/>	
									</td>
									<td>	
										<input ref="GOIANIA" type="text" id="t2m5_6"/>	
									</td>   
									<td>	
										<input ref="MONTES CLAROS" type="text" id="t2m5_7"/>	
									</td>  	 	
								</tr>
								<tr>
									<td>	
										<%= messages.getText("generic.month.6") %>
									</td> 	
									<td>	
										<input ref="ITABUNA" type="text" id="t2m6_1"/>
									</td>
									<td>
										<input ref="PALMEIRA DAS MISSOES" type="text" id="t2m6_2"/>	
									</td> 	
									<td>	
										<input ref="ITUIUTABA" type="text" id="t2m6_3"/>	
									</td>
									<td>	
										<input ref="ARACATUBA" type="text" id="t2m6_5"/>	
									</td>
									<td>	
										<input ref="GOIANIA" type="text" id="t2m6_6"/>	
									</td>   
									<td>	
										<input ref="MONTES CLAROS" type="text" id="t2m6_7"/>	
									</td>  	 	
								</tr>
								<tr>
									<td>	
										<%= messages.getText("generic.month.7") %>
									</td> 	
									<td>	
										<input ref="ITABUNA" type="text" id="t2m7_1"/>
									</td>
									<td>
										<input ref="PALMEIRA DAS MISSOES" type="text" id="t2m7_2"/>	
									</td> 	
									<td>	
										<input ref="ITUIUTABA" type="text" id="t2m7_3"/>	
									</td>
									<td>	
										<input ref="ARACATUBA" type="text" id="t2m7_5"/>	
									</td>
									<td>	
										<input ref="GOIANIA" type="text" id="t2m7_6"/>	
									</td>   
									<td>	
										<input ref="MONTES CLAROS" type="text" id="t2m7_7"/>	
									</td>  	 								</tr>
								<tr>
									<td>	
										<%= messages.getText("generic.month.8") %>
									</td> 	
									<td>	
										<input ref="ITABUNA" type="text" id="t2m8_1"/>
									</td>
									<td>
										<input ref="PALMEIRA DAS MISSOES" type="text" id="t2m8_2"/>	
									</td> 	
									<td>	
										<input ref="ITUIUTABA" type="text" id="t2m8_3"/>	
									</td>
									<td>	
										<input ref="ARACATUBA" type="text" id="t2m8_5"/>	
									</td>
									<td>	
										<input ref="GOIANIA" type="text" id="t2m8_6"/>	
									</td>   
									<td>	
										<input ref="MONTES CLAROS" type="text" id="t2m8_7"/>	
									</td>  	 								</tr>
								<tr>
									<td>	
										<%= messages.getText("generic.month.9") %>
									</td> 	
									<td>	
										<input ref="ITABUNA" type="text" id="t2m9_1"/>
									</td>
									<td>
										<input ref="PALMEIRA DAS MISSOES" type="text" id="t2m9_2"/>	
									</td> 	
									<td>	
										<input ref="ITUIUTABA" type="text" id="t2m9_3"/>	
									</td>
									<td>	
										<input ref="ARACATUBA" type="text" id="t2m9_5"/>	
									</td>
									<td>	
										<input ref="GOIANIA" type="text" id="t2m9_6"/>	
									</td>   
									<td>	
										<input ref="MONTES CLAROS" type="text" id="t2m9_7"/>	
									</td>								</tr>
								<tr>
									<td>	
										<%= messages.getText("generic.month.10") %>
									</td> 	
									<td>	
										<input ref="ITABUNA" type="text" id="t2m10_1"/>
									</td>
									<td>
										<input ref="PALMEIRA DAS MISSOES" type="text" id="t2m10_2"/>	
									</td> 	
									<td>	
										<input ref="ITUIUTABA" type="text" id="t2m10_3"/>	
									</td>
									<td>	
										<input ref="ARACATUBA" type="text" id="t2m10_5"/>	
									</td>
									<td>	
										<input ref="GOIANIA" type="text" id="t2m10_6"/>	
									</td>   
									<td>	
										<input ref="MONTES CLAROS" type="text" id="t2m10_7"/>	
									</td>				
								</tr>
								<tr>
									<td>	
										<%= messages.getText("generic.month.11") %>
									</td> 	
									<td>	
										<input ref="ITABUNA" type="text" id="t2m11_1"/>
									</td>
									<td>
										<input ref="PALMEIRA DAS MISSOES" type="text" id="t2m11_2"/>	
									</td> 	
									<td>	
										<input ref="ITUIUTABA" type="text" id="t2m11_3"/>	
									</td>
									<td>	
										<input ref="ARACATUBA" type="text" id="t2m11_5"/>	
									</td>
									<td>	
										<input ref="GOIANIA" type="text" id="t2m11_6"/>	
									</td>   
									<td>	
										<input ref="MONTES CLAROS" type="text" id="t2m11_7"/>	
									</td>				
								</tr>
								<tr>
									<td>	
										<%= messages.getText("generic.month.12") %>
									</td> 	
									<td>	
										<input ref="ITABUNA" type="text" id="t2m12_1"/>
									</td>
									<td>
										<input ref="PALMEIRA DAS MISSOES" type="text" id="t2m12_2"/>	
									</td> 	
									<td>	
										<input ref="ITUIUTABA" type="text" id="t2m12_3"/>	
									</td>
									<td>	
										<input ref="ARACATUBA" type="text" id="t2m12_5"/>	
									</td>
									<td>	
										<input ref="GOIANIA" type="text" id="t2m12_6"/>	
									</td>   
									<td>	
										<input ref="MONTES CLAROS" type="text" id="t2m12_7"/>	
									</td>				
								</tr>
							</table>	
						</td>
   					</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					
				</table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>  
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
	
       	<tr>   		
	   		<td align="right">
	   		<input name="button2" type="button" onclick="save();" value=Salvar' class="input"/>
	   	   	</td>             
   	  	</tr>              
	</table>
	</html:form>
	</center>
	</body>
	
</html:html>
<%
	}
%>
