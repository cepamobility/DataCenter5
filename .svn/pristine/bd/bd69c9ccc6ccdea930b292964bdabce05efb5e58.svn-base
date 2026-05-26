<%@page import="communication.activity.tables.ItemData"%>
<%@page import="org.apache.struts.action.DynaActionForm"%>
<%@page language="java" 
	errorPage="../../exception.jsp"
	import="business.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.activity.tables.*"
	import="communication.administration.corporation.tables.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.ActivityTypeDataType"
	import="business.activity.ActivityReportFacade"
	import="communication.datatype.tables.*"
	import="communication.datatype.*"
	import="java.util.*"	
	import="org.apache.struts.action.DynaActionForm"
	import="business.datatype.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>


<html:html>
<%!
	RequestDispatcher dispatcher; 
	String context;String pathView; String pathMsg; 
	String pathSearch;
	String pathLanguage; Text messages; SystemAccess sys;
	Integer pageNum; boolean all; int pageSize; 
	String attribute; String value;
	UserSessionData userSessionData; 
	ActivityReportFacade activityReport;
	String str1, str2, str3,col, idActivity;
	CEPAActivityScheduleData  activityData;
	int columns;
	String url;
	DynaActionForm activityRegistryForm; 
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();	
	String check = Util.checkPermission(sys,SecurityConstants.CREATE_CEPASCHEDULEACTIVITYREGISTRY);	
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		messages=sys.getText();
		pathLanguage="/util/jsp/language.jsp";	
		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
	
		context=request.getContextPath();
		pathMsg="/util/jsp/message.jsp";
	
		//session.setAttribute("url","activity/schedule/TranningOnTheJobForm.jsp");
		url = "activity/schedule/TranningOnTheJobForm.jsp";
		userSessionData.setUrl(url);
		
		
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);

		idActivity =(String)request.getParameter("idActivity");
		if(idActivity==null){
			idActivity=(String)session.getAttribute("id");			
		}
		//session.setAttribute("id",idActivity);
		// Obtengo el formulario de esa actividad para dibujarlo en el HTML:
		activityReport = sys.getActivityReportFacade();
		
		// Obtengo la cantidad de columnas a dibujar en el formulario:
		col = request.getParameter("columns");		
		if (!Util.empty(col))
			columns	 = (Integer.parseInt(col));
		else
			columns = 1;
		
		CEPAActivityScheduleData activityData = (CEPAActivityScheduleData) session.getAttribute("activityData");
		//activityData = activityReport.findCEPAActivityScheduleByPrimaryKey(new Integer(Integer.parseInt(idActivity)),userSessionData);
		
		
		// Me fijo si ya tiene un registro de esta actividad ese conductor:
		String idDriver=(String)request.getParameter("idDriver");
		if(idDriver==null){
			idDriver=(String)session.getAttribute("idDriver");			
		}		
		//session.setAttribute("idDriver",idDriver);	
		
		String initialHrStr = "";
		String initialMinuteStr = "";
		
		String noteStr = "";
		String obsStr = "";
		String instructorStr = "";	
		String plateStr = "";
		String tradeStr = "";
		String initialKmStr = "";
		String finalKmStr = "";
		String finalHrStr = "";	
		String finalMinuteStr = "";				
		String selNota1 ="";
		String selNota2="";
		String selNota3="";	
		String checkArea1="";
		String checkArea2= "";
		String checkArea3= "";
		String checkArea4= "";
		String checkVehicle1= "";
		String checkVehicle2= "";
		String checkVehicle3= "";
		String checkVehicle4 = "";
		String checkVel1 = "";
		String checkVel2 = "";
		String checkLight1 = "";
		String checkLight2 = "";		
		String phrd = "";		
		
		// Si hubo problemas con la validación se recargan los datos ya ingresados
		activityRegistryForm = (DynaActionForm)session.getAttribute("ActivityRegistryForm");
		if(activityRegistryForm !=null) {
			initialHrStr = (String)activityRegistryForm.get("initialHr");
			initialMinuteStr = (String)activityRegistryForm.get("initialMinute");
			finalHrStr = (String)activityRegistryForm.get("finalHr");
			finalMinuteStr = (String)activityRegistryForm.get("finalMinute");			
			plateStr = (String)activityRegistryForm.get("plate");
			tradeStr = (String)activityRegistryForm.get("trade");
			initialKmStr = (String)activityRegistryForm.get("initialKm");
			finalKmStr = (String)activityRegistryForm.get("finalKm");			
			noteStr = (String) activityRegistryForm.get("note");
			obsStr = (String) activityRegistryForm.get("obs");
			instructorStr = (String) activityRegistryForm.get("instructor");			
			
			if (noteStr.equals("generic.activity.note.notapruved")) {
				selNota1 = "selected";
			}
			if (noteStr.equals("generic.activity.note.apruved")) {
				selNota2 = "selected";
			}
			if (noteStr.equals("generic.activity.note.apruvedwithobservations")) {
				selNota3 = "selected";
			}
			
			// Area de Circulacion     			
			if (((String)activityRegistryForm.get("circulationAreaUrban")).equals("true")) {
				checkArea1 = "checked";
			}
			
			if (((String)activityRegistryForm.get("circulationAreaSubUrban")).equals("true")) {
				checkArea2 = "checked";
			}			
			
			if (((String)activityRegistryForm.get("circulationAreaHighWay")).equals("true")) {
				checkArea3 = "checked";
			}
			
			if (((String)activityRegistryForm.get("circulationAreaRoad")).equals("true")) {
				checkArea4 = "checked";
			}
			
			// Tipo de Vehiculo
			if (((String)activityRegistryForm.get("vehicleType")).equals("generic.activity.form.tranningonthejob.vehicletype.sedan")) {
				checkVehicle1 = "checked";
			}
			
			if (((String)activityRegistryForm.get("vehicleType")).equals("generic.activity.form.tranningonthejob.vehicletype.pickup")) {
				checkVehicle2 = "checked";
			}			
			
			if (((String)activityRegistryForm.get("vehicleType")).equals("generic.activity.form.tranningonthejob.vehicletype.SUV")) {
				checkVehicle3 = "checked";
			}
			
			if (((String)activityRegistryForm.get("vehicleType")).equals("generic.activity.form.tranningonthejob.vehicletype.4x4")) {
				checkVehicle4 = "checked";
			}

			// Caja de Velocidades
			if (((String)activityRegistryForm.get("velocityBox")).equals("generic.activity.form.tranningonthejob.velocitybox.manual")) {
				checkVel1 = "checked";
			}
			
			if (((String)activityRegistryForm.get("velocityBox")).equals("generic.activity.form.tranningonthejob.velocitybox.automatic")) {
				checkVel2 = "checked";
			}			
			
			// Luz
			if (((String)activityRegistryForm.get("light")).equals("generic.activity.form.tranningonthejob.light.diurno")) {
				checkLight1 = "checked";
			}
			
			if (((String)activityRegistryForm.get("light")).equals("generic.activity.form.tranningonthejob.light.nocturno")) {
				checkLight2 = "checked";
			}						
			if (((String)activityRegistryForm.get("light")).equals("generic.activity.form.tranningonthejob.light.diurnoynocturno")) {
				checkLight2 = "checked";
				checkLight1 = "checked";
			}	
		}

		CEPAActivityScheduleRegistryData registryData;
		
		// Borra cualquier resultado guardado en la sesión
		session.removeAttribute("registryData");
		if (activityReport.hasResult(new Integer(idActivity),new Integer(idDriver),sys.getUserSessionData())) {
			registryData = activityReport.getCompleteResult(
				new Integer(idActivity),new Integer(idDriver),sys.getUserSessionData());
			
			DynaActionForm form = new DynaActionForm();
			form.set("obs", registryData.getObs());
			
			session.setAttribute("registryData",registryData);
					
			noteStr = registryData.getNote();
			obsStr = registryData.getObs();		
			instructorStr = registryData.getInstructor();	
			
			plateStr = registryData.getPlate();	
			tradeStr = registryData.getTrade();
			
			if (registryData.getInitialKm()!=null) {
				initialKmStr = registryData.getInitialKm().toString();	
			}
			if (registryData.getFinalKm()!=null) {
				finalKmStr = registryData.getFinalKm().toString();	
			}	
			
			Calendar calendar = null;
			if (registryData.getInitialHr()!=null) {
				calendar = Calendar.getInstance();
		        calendar.setTime(registryData.getInitialHr());
		        
	    	    initialHrStr = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));  
	        	initialMinuteStr = String.valueOf(calendar.get(Calendar.MINUTE));
			}	
	        
			if (registryData.getFinalHr()!=null) {
		        calendar.setTime(registryData.getFinalHr());
		        finalHrStr = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));  
	    	    finalMinuteStr = String.valueOf(calendar.get(Calendar.MINUTE));	        
			}    
			
			if (registryData.getNote() != null && registryData.getNote().equals("generic.activity.note.notapruved")) {
				selNota1 = "selected";
			}
			if (registryData.getNote() != null && registryData.getNote().equals("generic.activity.note.apruved")) {
				selNota2 = "selected";
			}
			if (registryData.getNote() != null && registryData.getNote().equals("generic.activity.note.apruvedwithobservations")) {
				selNota3 = "selected";
			}
			
			// Area de Circulacion     			
			if (registryData.isCirculationAreaUrban()) {
				checkArea1 = "checked";
			}
			
			if (registryData.isCirculationAreaSubUrban()) {
				checkArea2 = "checked";
			}			
			
			if (registryData.isCirculationAreaHighWay()) {
				checkArea3 = "checked";
			}
			
			if (registryData.isCirculationAreaRoad()) {
				checkArea4 = "checked";
			}
			
			// Tipo de Vehiculo
			if (registryData.getVehicleType() != null && registryData.getVehicleType().equals("generic.activity.form.tranningonthejob.vehicletype.sedan")) {
				checkVehicle1 = "checked";
			}
			
			if (registryData.getVehicleType() != null && registryData.getVehicleType().equals("generic.activity.form.tranningonthejob.vehicletype.pickup")) {
				checkVehicle2 = "checked";
			}			
			
			if (registryData.getVehicleType() != null && registryData.getVehicleType().equals("generic.activity.form.tranningonthejob.vehicletype.SUV")) {
				checkVehicle3 = "checked";
			}
			
			if (registryData.getVehicleType() != null && registryData.getVehicleType().equals("generic.activity.form.tranningonthejob.vehicletype.4x4")) {
				checkVehicle4 = "checked";
			}

			// Caja de Velocidades
			if (registryData.getVelocityBox() != null && registryData.getVelocityBox().equals("generic.activity.form.tranningonthejob.velocitybox.manual")) {
				checkVel1 = "checked";
			}
			
			if (registryData.getVelocityBox() != null && registryData.getVelocityBox().equals("generic.activity.form.tranningonthejob.velocitybox.automatic")) {
				checkVel2 = "checked";
			}			
			
			// Luz
			if (registryData.getLight() != null && registryData.getLight().equals("generic.activity.form.tranningonthejob.light.diurno")) {
				checkLight1 = "checked";
			}
			
			if (registryData.getLight() != null && registryData.getLight().equals("generic.activity.form.tranningonthejob.light.nocturno")) {
				checkLight2 = "checked";
			}
			if (registryData.getLight() != null && registryData.getLight().equals("generic.activity.form.tranningonthejob.light.diurnoynocturno")) {
				checkLight1 = "checked";
				checkLight2 = "checked";
			}
		
			if(registryData.getPhrd()) {
			    phrd = "checked";
			}
		}
%>
	<head>
		<LINK REL=StyleSheet HREF="../../util/css/estilo.css" TYPE="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	</head>
	<script language="JavaScript" type="text/javascript">

		function preparedCheckboxRadioParameters(form){
		
				var elements=form.elements;
				var length=elements.length;
				var i;
				var valueCheckboxParamList="";
				var separator="<";
				
				for(i=0;i<length;i++){
					var element=elements[i];
					if((element.type.toLowerCase()=="checkbox") || (element.type.toLowerCase()=="radio")) {
						if ( (element.checked) 
							&& (element.name != "circulationAreaHighWay") 
							&& (element.name != "circulationAreaRoad") 
							&& (element.name != "circulationAreaSubUrban")
							&& (element.name != "circulationAreaUrban")						 
							&& (element.name != "vehicleType")
							&& (element.name != "phrd")
							&& (element.name != "velocityBox")
							&& (element.name != "lightNocturno")
							&& (element.name != "lightDiurno")				    
						    ){	
		
							if(valueCheckboxParamList==""){
								valueCheckboxParamList=element.value;
							}else{
								valueCheckboxParamList=element.value+"<"+valueCheckboxParamList;
							}	
						}	
					} 
				
				}
				form.checkboxParamList.value=valueCheckboxParamList;
				return true;
		
		}
		
		function setAction(form1,form2){
			if(preparedCheckboxRadioParameters(form1)) {
				form2.checkboxParamList.value = form1.checkboxParamList.value;
				form2.note.value = form1.note.value;
				form2.obs.value = form1.obs.value;		
				form2.instructor.value = form1.instructor.value;	
				
				form2.plate.value = form1.plate.value;	
				form2.trade.value = form1.trade.value;	
				form2.initialKm.value = form1.initialKm.value;	
				form2.finalKm.value = form1.finalKm.value;	
				form2.initialHr.value = form1.initialHr.value;	
				form2.initialMinute.value = form1.initialMinute.value;		
				form2.finalHr.value = form1.finalHr.value;	
				form2.finalMinute.value = form1.finalMinute.value;		
						
				for (i=0;i<form1.vehicleType.length;i++){
			       if (form1.vehicleType[i].checked == true ) {
		    	      form2.vehicleType.value = form1.vehicleType[i].value;
		        	  break;
		        	}  
				}
				
				form2.circulationAreaHighWay.value = (form1.circulationAreaHighWay.checked == true)?"true":"false";
				form2.circulationAreaRoad.value = (form1.circulationAreaRoad.checked == true)?"true":"false";
				form2.circulationAreaSubUrban.value = (form1.circulationAreaSubUrban.checked == true)?"true":"false";
				form2.circulationAreaUrban.value = (form1.circulationAreaUrban.checked == true)?"true":"false";
				
				
				for (i=0;i<form1.velocityBox.length;i++){
			       if (form1.velocityBox[i].checked == true) {
		    	      form2.velocityBox.value = form1.velocityBox[i].value;
		        	  break;
		        	}  
				}				
		
				// Luces	
				if (form1.lightDiurno.checked == true &&
					form1.lightNocturno.checked == true){
					form2.light.value = "generic.activity.form.tranningonthejob.light.diurnoynocturno";
		    	}else if (form1.lightDiurno.checked == true && form1.lightNocturno.checked == false){
		    		form2.light.value = "generic.activity.form.tranningonthejob.light.diurno";
		    	}else if (form1.lightDiurno.checked == false && form1.lightNocturno.checked == true){
		    		form2.light.value = "generic.activity.form.tranningonthejob.light.nocturno";
		    	}else{
		    		form2.light.value = "";
		    	}
				// Fin Luces
			
				form2.phrd.value = form1.phrd.checked;
				// Refactoring	
				form2.idDriver.value = '<%=idDriver%>';	
				form2.idActivity.value = '<%=idActivity%>';
				form2.url.value = '<%=url%>';
				// Fin Refactoring
				form2.submit();
			}
		}
		
	</script>
	<body>
		<div align="center">
			<html:form action = "ActivityRegistrycreate">
				<html:hidden property="idDriver" value='<%=idDriver%>' />
				<html:hidden property="idActivity" value='<%=idActivity%>' />
				<html:hidden property="url" value='<%=url%>' />

 				<!--  Variables Ocultas: -->
				<html:hidden property="cepaCourse" value="true" />				
				<html:hidden property="partner" value="" />				
				<html:hidden property="conductionType" value="" />																										    
	
				<html:hidden property="checkboxParamList" value="checkboxParamList" />
				<html:hidden property="light" value="" />	
				
 				<table width="900" border="0" cellspacing="2" cellpadding="2">  
<%		String title = "../../component/titlecomponent.jsp?colspan=6&title="+messages.getText("generic.activity.form.formulario")+": "+messages.getText(activityData.getDefinition().getResourceKeyName());
%> 
					<tr>
						<jsp:include  page='<%=title%>' />
			  		</tr>
					<tr>
		        		<td valign="top" width="100%" colspan="6">
							<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.activity.form.tranningonthejob.proofdata")%>' />
							<!--  Datos Básicos de la Prueba --> 
							<table width="100%" border="0" cellpadding="2" cellspacing="2">
      							<tr>  
	      							<td width="50%" align="left" class="texts">        
								        <%= messages.getText("generic.activity.form.tranningonthejob.initialHr")%>
									</td>
									<td width="50%" align="right">
										<html:text property="initialHr" style="width:30;" styleClass="input" value='<%= initialHrStr%>'/>&nbsp;:&nbsp;                
										<html:text property="initialMinute" style="width:30;" styleClass="input" value='<%= initialMinuteStr%>'/>                		
									</td>
     							</tr>
      							<tr>  
        							<td width="50%" align="left" class="texts">        
								        <%= messages.getText("generic.activity.form.tranningonthejob.finalHr")%>
									</td>
									<td width="50%" align="right">		
										<html:text property="finalHr" style="width:30;" styleClass="input" value = '<%= finalHrStr%>'/>&nbsp;:&nbsp;                
										<html:text property="finalMinute" style="width:30;" styleClass="input" value = '<%= finalMinuteStr%>'/>                				
        							</td>
     							</tr>
     							<tr> 
        							<td width="50%" align="left" class="texts">        
								        <%= messages.getText("generic.activity.form.tranningonthejob.trade")%>
									</td>
									<td width="50%" align="right">		
										<html:text property="trade" styleClass="input" value = '<%= tradeStr%>'/>                                     
							        </td>
							     </tr>
      							<tr>  
        							<td width="50%" align="left" class="texts">        
								        <%= messages.getText("generic.activity.form.tranningonthejob.plate")%>
									</td>
									<td width="50%" align="right">		
										<html:text property="plate" styleClass="input" value = '<%= plateStr%>'/>                                             
							        </td>
							     </tr>       
      							<tr>
        							<td width="50%" align="left" class="texts">        
								        <%= messages.getText("generic.activity.form.tranningonthejob.initialKm")%>
									</td>
									<td width="50%" align="right">		
										<html:text property="initialKm" styleClass="input" value = '<%= initialKmStr%>'/>                                                     
        							</td>
     							</tr>
              					<tr>  
        							<td width="50%" align="left" class="texts">        
								        <%= messages.getText("generic.activity.form.tranningonthejob.finalKm")%>        
									</td>
									<td width="50%" align="right">		
										<html:text property="finalKm" styleClass="input" value = '<%= finalKmStr%>'/>                                                             
							        </td>
     							</tr>
							</table>
							<jsp:include  page='../../component/utilityboxbottom.jsp' />
							<!-- Fin de Datos Básicos de la Prueba --> 
						</td>
					</tr>
					<tr>
						<td colspan="6">  
							<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.activity.phrdbycourse")%>' />
              				<html:checkbox property="phrd">
              					<span class="texts"><%=messages.getText("generic.activity.phrdbycourse.isphrd")%></span>
              				</html:checkbox> 
							<jsp:include  page='../../component/utilityboxbottom.jsp' />
						</td>	
					</tr>
					<!-- Otros Datos --> 
					<tr>
						<td colspan="2">
							<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.activity.form.tranningonthejob.circulationarea")%>' />
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr> 
									<td class="texts">        
								        <html:checkbox property="circulationAreaUrban" ></html:checkbox>	    				
								        <%= messages.getText("generic.activity.form.tranningonthejob.circulationarea.urbana")%>
									</td>
								</tr>
								<tr> 
									<td class="texts">        
								        <html:checkbox property="circulationAreaSubUrban" ></html:checkbox>	    				
								        <%= messages.getText("generic.activity.form.tranningonthejob.circulationarea.suburban")%>
									</td>
								</tr>
								<tr> 
									<td class="texts">        
								        <html:checkbox property="circulationAreaHighWay" ></html:checkbox>	    				
								        <%= messages.getText("generic.activity.form.tranningonthejob.circulationarea.autopista")%>
									</td>
								</tr>
								<tr> 
									<td class="texts">        
								        <html:checkbox property="circulationAreaRoad" ></html:checkbox>	    				
								        <%= messages.getText("generic.activity.form.tranningonthejob.circulationarea.road")%>
									</td>
								</tr>
							</table>
							<jsp:include  page='../../component/utilityboxbottom.jsp' />
						</td>	
						<td colspan="4">
							<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.activity.form.tranningonthejob.vehicletype")%>' />
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr> 
									<td class="texts">        
										<html:radio property="vehicleType" value="generic.activity.form.tranningonthejob.vehicletype.sedan" />	    				
										<%= messages.getText("generic.activity.form.tranningonthejob.vehicletype.sedan")%>
									</td>
								</tr>
								<tr>  
									<td class="texts">        
										<html:radio property="vehicleType" value="generic.activity.form.tranningonthejob.vehicletype.pickup" />	    				
										<%= messages.getText("generic.activity.form.tranningonthejob.vehicletype.pickup")%>
									</td>
								</tr>
								<tr>
									<td class="texts">        
										<html:radio property="vehicleType" value="generic.activity.form.tranningonthejob.vehicletype.SUV"  />	    				
										<%= messages.getText("generic.activity.form.tranningonthejob.vehicletype.SUV")%>
									</td>
								</tr>
								<tr>  
									<td class="texts">        	
										<html:radio property="vehicleType" value="generic.activity.form.tranningonthejob.vehicletype.4x4" />	    				    
										<%= messages.getText("generic.activity.form.tranningonthejob.vehicletype.4x4")%>
									</td>
								</tr>
							</table>
							<jsp:include  page='../../component/utilityboxbottom.jsp' />
						</td>	
 					</tr>
					<tr>
						<td colspan="3">
							<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.activity.form.tranningonthejob.velocitybox")%>' />
							<table width="100%" border="0" cellpadding="2" cellspacing="2">
							     <tr> 
							        <td class="texts">        
								        <html:radio property="velocityBox" value="generic.activity.form.tranningonthejob.velocitybox.manual" />	    				
								        <%= messages.getText("generic.activity.form.tranningonthejob.velocitybox.manual")%>
							        </td>
							        <td class="texts">        
							            <html:radio property="velocityBox" value="generic.activity.form.tranningonthejob.velocitybox.automatic" />	    				
								        <%= messages.getText("generic.activity.form.tranningonthejob.velocitybox.automatic")%>
							        </td>
							     </tr>      
							</table>
							<jsp:include  page='../../component/utilityboxbottom.jsp' />
						</td>	
						<td colspan="3">
							<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.activity.form.tranningonthejob.light")%>' />
							<table width="100%" border="0" cellpadding="2" cellspacing="2">
							     <tr> 
							        <td class="texts">        
							        	<html:checkbox property="light" value="generic.activity.form.tranningonthejob.light.diurno" ></html:checkbox>	    				
						        		<%= messages.getText("generic.activity.form.tranningonthejob.light.diurno")%>
							        </td>
							        <td class="texts">        
							         	<html:checkbox property="light" value="generic.activity.form.tranningonthejob.light.nocturno" ></html:checkbox>	    				
							        	<%= messages.getText("generic.activity.form.tranningonthejob.light.nocturno")%>
							        </td>
							     </tr>      
							</table>
							<jsp:include  page='../../component/utilityboxbottom.jsp' />
						</td>
					</tr>
					<tr>
<% 					
	CheckListData checkList = (activityData != null && activityData.getDefinition()  != null) ? activityData.getDefinition().getCheckList() : null;
	LinkedList points = (checkList != null && checkList.getPoints() != null)? (LinkedList)checkList.getPoints() : null;
	Collections.sort(points);
	int index = 0;
	Iterator it = points.iterator();
	int rows = points.size() / 3;
	if(points.size() % 3 > 0) {
		rows++;
	}
	while(it.hasNext()) {
		PointData point = (PointData)it.next();
		if(index == 0) {
%>						<td colspan="2" valign="top" width="33%">
							<table width="100%" border="0" cellpadding="2" cellspacing="2">
<%		}
		index++;
%>							
							     <tr> 
							        <td class="texts">        
										<jsp:include  page='<%="../../component/utilitybox6top.jsp?font=8&title="+messages.getText(point.getText())%>' />
										<table width="100%" border="0" cellpadding="1" cellspacing="1">
<% 					
		Iterator items = point.getSortItems();
		while(items.hasNext()) {
			ItemData item = (ItemData)items.next();
%>											<tr>
												<td colspan="2" class="linetwo">
													<input type="checkbox" name='<%=item.getId().toString()%>'  id="<%=item.getId().toString()%>" class="check" /> 
													<%=messages.getText(item.getText())%>
												</td>
							     			</tr>      
<% 					
		}
%>
										</table>
										<jsp:include  page='../../component/utilityboxbottom.jsp' />
							        </td>
							     </tr>      
<% 					
		if(index == rows) {
%>							</table>
						</td>
<%			index = 0;
		}
	}
%>					
					</tr>
					<tr>
						<td colspan="6">
							<jsp:include  page='../../component/utilitybox4top.jsp' />
							<table width="100%" border="0" cellpadding="2" cellspacing="2">
					            <tr> 
					              	<td class="texts" align="left" width="10%">
										<%= messages.getText("generic.activity.note") + ": " %>
									</td>
					              	<td class="texts" align="left" width="90%">
<%
             		DataTypeReportFacade dataTypeReportFacade = sys.getDataTypeReportFacade();		
     				Collection notes = dataTypeReportFacade.findAll(DataTypeConstants.TYPECEPAACTIVITYNOTE,userSessionData);
             		if (notes != null){ 
%>					             		<html:select property="note" styleClass="input" >
             		
<%             			Iterator itNotes = notes.iterator();
             			while (itNotes.hasNext()){
             				String activityNote =  ((CEPAActivityNoteDataType) itNotes.next()).getResourceBundleId();
%>              			  				<html:option value='<%= activityNote %>' styleClass="input" ><%= messages.getText(activityNote) %></html:option>
<%              		}
%>               						</html:select> 
<%
              		}
%>
                             		</td>
            					</tr>
					            <tr> 
					              	<td class="texts" align="left" colspan="2">
										<%= messages.getText("generic.activity.observations")+": "  %>
									</td>
					            </tr>
					            <tr> 
					              	<td colspan="2">
					                	<html:textarea property="obs" cols="150" rows="5" styleClass="input"></html:textarea>
					                </td>
					            </tr>
					            <tr> 
					              	<td class="texts" align="left" width="10%">
										<%= messages.getText("generic.activity.instructor")+": " %>
									</td>
					              	<td class="texts" align="left" width="90%">
					                	<html:text property="instructor" value = '<%= instructorStr%>' styleClass="input" style="width: 200px;"/> 
					                </td>
					            </tr>
       						</table>
							<jsp:include  page='../../component/utilitybox4bottom.jsp' />
						</td>
					</tr>
		        	<tr>   		
				   		<td align="right" colspan="6">
				   			<html:submit value='<%=messages.getText("generic.messages.save") %>' styleClass="input"/>	 		                             
		            	</td>                 							
		    	  	</tr>              
			    </table>
			</html:form>
		</div>
	</body>
<%	}	
%>	

</html:html>
