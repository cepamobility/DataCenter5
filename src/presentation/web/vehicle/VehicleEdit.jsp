<%@page import="persistence.administration.corporation.tables.Corporation"%>
<%@page language="java"
    errorPage="../exception.jsp"
	import="business.*"
	import="business.administration.user.*"
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.datatype.tables.*"
	import="communication.datatype.*"
	import="communication.administration.user.tables.*"
	import="communication.administration.corporation.tables.*"
	import="presentation.translator.web.util.*"
	import="java.util.*"
	import="business.vehicle.VehicleReportFacade"
	import="communication.vehicle.tables.VehicleData"	
	import="presentation.translator.web.vehicle.*"	 
	import="communication.security.*"	
	import="communication.driver.tables.*"		
	import="communication.administration.user.UserSessionData"			
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>

<%  RequestDispatcher dispatcher; 
	Text messages; 
	SystemAccess sys; 
	UserSessionData userSessionData;		
%>

<html:html>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();
	String check = null;
	if(userSessionData.isDriver()) {
		check = Util.checkPermission(sys,SecurityConstants.DRIVER_MODIFY_VEHICLE);
	}else {
		check = Util.checkPermission(sys,SecurityConstants.EDIT_VEHICLE);
	}
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
		
		messages=sys.getText();		
		
		if(session.getAttribute("target")==null){
			session.setAttribute("target",request.getParameter("target"));
		}

		String idVehicle = request.getParameter("id");	
		if (idVehicle == null) {
			idVehicle = (String)session.getAttribute("vehicleId");
			session.removeAttribute("vehicleId");
		}
		session.setAttribute("url","vehicle/VehicleEdit.jsp?id="+idVehicle);
		
		VehicleReportFacade vehicleReportFacade = sys.getVehicleReportFacade();
		VehicleData vehicleData = vehicleReportFacade.findVehicleByPrimaryKey(new Integer(idVehicle),userSessionData);
	
		if(vehicleData!=null){
			DataTypeReportFacade dtReportFacade = sys.getDataTypeReportFacade();		

			VehicleForm vehicleForm = VehicleUtil.createVehicleForm(vehicleData,VehicleUtil.EDIT,messages);
			session.setAttribute("VehicleCreateForm",vehicleForm);
			session.setAttribute("vehicleId",vehicleData.getId());
			
			DriverData driverData = (userSessionData.isDriver()) ? userSessionData.getDriverData() : vehicleData.getDriver();
			
			int corporationId = userSessionData.getFilter().getCorporationId().intValue();
			//	Datos Opcionales de Vehiculos (Camiones Brasil), en particular para KLABIN
			boolean hasOptionalData = false;
			if (corporationId == CorporationData.KLABIN || 
					corporationId == CorporationData.YOLI ||
			        corporationId == CorporationData.CIMSA ||
			        corporationId == CorporationData.PEPSICO || corporationId == CorporationData.FORESTALORIENTAL){
				// Cimsa y Pepsico usan la matricula como numero economico
				// y el campo prefijo de optionaInfo como matricula
				
				hasOptionalData = true;
			}
			if(corporationId == CorporationData.MONTES_DE_PLATA) {
				DataTypeUtil dataTypeUtil = new DataTypeUtil(sys);
				session.setAttribute("yesno",dataTypeUtil.getYesNo(true, false));
			}
%>  

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<script language="javascript" src='/datacenter/menu/js/driverFunctions.js' type='text/javascript' >
</script>
<script>
function onSelect1(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayId1");
  		var input_month = document.getElementById("monthId1");
  		var input_year = document.getElementById("yearId1");
  		var date3 = new Date(time);
  		input_day.value = date3.print("%d");
  		input_month.value = date3.print("%m");
  		input_year.value= parseInt(date3.print("%Y"),10);
  }
function onSelect2(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayId2");
  		var input_month = document.getElementById("monthId2");
  		var input_year = document.getElementById("yearId2");
  		var date3 = new Date(time);
  		input_day.value = date3.print("%d");
  		input_month.value = date3.print("%m");
  		input_year.value= parseInt(date3.print("%Y"),10);
  }
function onSelect3(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayId3");
  		var input_month = document.getElementById("monthId3");
  		var input_year = document.getElementById("yearId3");
  		var date3 = new Date(time);
  		input_day.value = date3.print("%d");
  		input_month.value = date3.print("%m");
  		input_year.value= parseInt(date3.print("%Y"),10);
  }
  
  function onSelect4(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayId4");
  		var input_month = document.getElementById("monthId4");
  		var input_year = document.getElementById("yearId4");
  		var date3 = new Date(time);
  		input_day.value = date3.print("%d");
  		input_month.value = date3.print("%m");
  		input_year.value= parseInt(date3.print("%Y"),10);
  }
  
function onSelect5(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayId5");
  		var input_month = document.getElementById("monthId5");
  		var input_year = document.getElementById("yearId5");
  		var date3 = new Date(time);
  		input_day.value = date3.print("%d");
  		input_month.value = date3.print("%m");
  		input_year.value= parseInt(date3.print("%Y"),10);
  }

function onSelect6(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayId6");
  		var input_month = document.getElementById("monthId6");
  		var input_year = document.getElementById("yearId6");
  		var date3 = new Date(time);
  		input_day.value = date3.print("%d");
  		input_month.value = date3.print("%m");
  		input_year.value= parseInt(date3.print("%Y"),10);
  }
  
function onSelect7(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayId7");
  		var input_month = document.getElementById("monthId7");
  		var input_year = document.getElementById("yearId7");
  		var date3 = new Date(time);
  		input_day.value = date3.print("%d");
  		input_month.value = date3.print("%m");
  		input_year.value= parseInt(date3.print("%Y"),10);
  }
  
function onSelect8(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayId8");
  		var input_month = document.getElementById("monthId8");
  		var input_year = document.getElementById("yearId8");
  		var date3 = new Date(time);
  		input_day.value = date3.print("%d");
  		input_month.value = date3.print("%m");
  		input_year.value= parseInt(date3.print("%Y"),10);
  }

function onSelect9(calendar, date) {
	var date2 = calendar.date;
	var time = date2.getTime();
		var input_day = document.getElementById("dayId9");
		var input_month = document.getElementById("monthId9");
		var input_year = document.getElementById("yearId9");
		var date3 = new Date(time);
		input_day.value = date3.print("%d");
		input_month.value = date3.print("%m");
		input_year.value= parseInt(date3.print("%Y"),10);
}

</script>
<head/>

<body bgcolor="#FFFFFF">
	<html:errors/>
	<html:form action="update">
	
	<p></p>
		<table width="600" border="0" cellspacing="0" cellpadding="0" align="center">
 			<tr class="guide1">
				<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("web.vehicle.editing")%>' />
	  		</tr>
			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
	  		</tr>
  			<tr valign="top">
    			<td width="49%" valign="top">
					<table width="100%" border="0" align='left' cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.vehicle.driverpersonaldata")%>' />
<%  	if (driverData != null) {   
%>								<table width="100%" border="0" align='left' cellpadding="0" cellspacing="0">
									<tr>
							    		<td class="default">
											<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
												<tr  class="linetwo">
													<th align="left"> 
														<%=messages.getText("generic.driver.driverdata.firstname")%> 
													</th>
													<td align="right">
														<%=driverData.getFirstName() %>
													</td>
												</tr>
												<tr class="space">
													<th align="right">&nbsp;</th>
													<td align="right">&nbsp;</td>
												</tr>
												<tr  class="linetwo">
													<th align="left">
														<%=messages.getText("generic.driver.driverdata.lastname")%>
													</th>
													<td align="right">
														<%=driverData.getLastName() %>
													</td>
												</tr>
												<tr class="space">
													<th align="right">&nbsp;</th>
													<td align="right">&nbsp;</td>
												</tr>
												<tr  class="linetwo">
													<th align="left">
														<%=messages.getText("generic.vehicle.driverdata.birthdate")%>
													</th>
													<td align="right">
														<%=Util.formatDate(driverData.getBirthDate())%>
													</td>
												</tr>
												<tr>
													<th colspan="2" align="left">
														<%=messages.getText("generic.messages.dateformat")%>
													</th>
												</tr>
												<tr  class="space">
													<th align="right">&nbsp;</th>
													<td align="right">&nbsp;</td>
												</tr>
												<tr  class="linetwo">
													<th align="left" > <%=messages.getText("generic.driver.driverdata.corporationid")%></th>
													<td align="right"><%=driverData.getCorporationId()%></td>
												</tr>						
<% 			if(driverData.getExclusionDate() != null) {
%>												<tr class="space">
													<th align="right">&nbsp;</th>
													<td align="right">&nbsp;</td>
												</tr>
												<tr>
													<td align="left" class="texts">
														<%=messages.getText("generic.driver.driverdata.exclusiondate")%>
													</td>
													<td align="right" class="linetwo">
														<%=Util.formatDate(driverData.getExclusionDate())%>
													</td>
												</tr>								
<% 			} 
 			if(sys.getCorporationId() == CorporationData.ALLERGAN) {
%>												<tr class="space">
													<th align="right">&nbsp;</th>
													<td align="right">&nbsp;</td>
												</tr>
												<tr>
													<td align="left" class="texts">
														<%=messages.getText("generic.driver.driverdata.officeaddress")%>
													</td>
													<td align="right" class="linetwo">
														<%=(driverData.getOfficeAddress()!=null)? driverData.getOfficeAddress() : "----"%>
													</td>
												</tr>								
												<tr class="space">
													<th align="right">&nbsp;</th>
													<td align="right">&nbsp;</td>
												</tr>
												<tr>
													<td align="left" class="texts">
														<%=messages.getText("generic.driver.driverdata.superviser")%>
													</td>
													<td align="right" class="linetwo">
														<%=(driverData.getSuperviser() == null) ? "----" : driverData.getSuperviser()%>
													</td>
												</tr>								
												<tr class="space">
													<th align="right">&nbsp;</th>
													<td align="right">&nbsp;</td>
												</tr>
												<tr>
													<td align="left" class="texts">
														<%=messages.getText("generic.driver.driverdata.manager")%>
													</td>
													<td align="right" class="linetwo">
														<%=(driverData.getManager() == null) ? "----" : driverData.getManager()%>
													</td>
												</tr>								
												<tr class="space">
													<th align="right">&nbsp;</th>
													<td align="right">&nbsp;</td>
												</tr>
												<tr>
													<td align="left" class="texts">
														<%=messages.getText("generic.driver.driverdata.iddallas")%>
													</td>
													<td align="right" class="linetwo">
														<%=(Util.empty(driverData.getIdDallas())) ? "----" : messages.getText(driverData.getIdDallas())%>
													</td>
												</tr>								
												<tr class="space">
													<th align="right">&nbsp;</th>
													<td align="right">&nbsp;</td>
												</tr>
												<tr class="linetwo">
													<td align="left" class="texts">
														<%=messages.getText("generic.vehicledata.ownership")%>
													</td>
													<td align="right" class="linetwo">
						                				<html:text property="costCenter" style="width:155" styleClass="input"/>
													</td>
												</tr>
<%			}
%>											</table>		      	
								      	</td>
									</tr>		      	
								</table>
<% 		} else {
%>								<table width="100%" border="0" align='left' cellpadding="0" cellspacing="0">
									<tr>
								    	<td class="default">
											<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
												<tr  class="linetwo">
													<td align="right">
														<%=messages.getText("generic.vehicle.institutionalvehicle")%>
													</td>
												</tr>
<% 			if(sys.getCorporationId() == CorporationData.ALLERGAN) {
%>												<tr class="space">
												<tr class="space">
													<th align="right">&nbsp;</th>
													<td align="right">&nbsp;</td>
												</tr>
												<tr class="linetwo">
													<td align="left" class="texts">
														<%=messages.getText("generic.vehicledata.ownership")%>
													</td>
													<td align="right" class="linetwo">
						                				<html:text property="costCenter" style="width:155" styleClass="input"/>
													</td>
												</tr>
<% 			} 
%>												<tr class="space">
											</table>		      	
										</td>
									</tr>		      	
								</table>	
<% 		} 
%>								<jsp:include page="../component/utilityboxbottom.jsp"/>	
							</td>
						</tr>
				  		<tr>
							<jsp:include  page="../component/spaceline.jsp" />
						</tr>
						<tr>
					    	<td valign="top">
								<table width="100%" cellpadding="0" cellspacing="0" border="0">				
<%  //Obtengo el Unit de la region/pais que tiene el vechiculo
	// para mostrarla por defecto en la ventana:
			String preExistingUnit = null;
			if (vehicleData.getCountry() != null){
				GeoItemData geoItem1 = (GeoItemData) vehicleData.getCountry().getItemIterator().next();
				if (geoItem1.getSubItems() != null) {
					GeoItemData geoItem2 = (GeoItemData) geoItem1.getSubItems().getItemIterator().next();
					if (geoItem2.getSubItems() != null) {
						GeoItemData geoItem3 = (GeoItemData) geoItem2.getSubItems().getItemIterator().next();
					}
				}
			}
			preExistingUnit = "hola";
			String geoUrl = "/util/divgeo"+userSessionData.getFilter().getGeoLevelCount()+".jsp?form=VehicleCreateForm&consult=false";
			if(userSessionData.isDriver()) {
			    geoUrl += "&allElements=true";
			}
%>									<jsp:include page='<%=geoUrl%>' />				
								</table>
							</td>
						</tr>
					</table>
				</td>
	    		<td width="2%">&nbsp;</td>
    			<td width="49%" valign="top">
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
<%	String orgUrl = (userSessionData.isDriver()) ? "/util/divorg.jsp?form=VehicleCreateForm&consult=false&allElements=true" : "/util/divorg.jsp?form=VehicleCreateForm&consult=false";
%>            			<jsp:include page='<%=orgUrl%>' />    
					</table>
				</td>
		  	</tr>
<%		if(corporationId != CorporationData.ALLERGAN){
%>  		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
			</tr>
  			<tr valign="top">
		    	<td colspan="3" valign="top">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.administrativedata")%>' />
        			<table width="100%" cellpadding="0" cellspacing="0">
          				<tr class="texts">
            				<th align="left" >
            					<%=messages.getText("generic.vehicle.vehicledata.vehicletype")%>
            					<span class="required">*</span>
            				</th>
            				<td align="right">
<%			switch(sys.getCorporationId()) {
				case CorporationData.CIMSA:
%>		        				<html:select property="type" styleClass="input" style="width:155;">
									<html:option value=""><%=messages.getText("generic.messages.select")%></html:option>
									<html:option value="datatype.vehicletype.truck"><%=messages.getText("datatype.vehicletype.truck")%></html:option>
									<html:option value="datatype.vehicletype.van"><%=messages.getText("datatype.vehicletype.van")%></html:option>
									<html:option value="datatype.vehicletype.minivan"><%=messages.getText("datatype.vehicletype.minivan")%></html:option>
								</html:select>
<%					break;	
				case CorporationData.DPA:
%>		        				<html:select property="type" styleClass="input" style="width:155;">
									<html:option value=""><%=messages.getText("generic.messages.select")%></html:option>
									<html:option value="datatype.vehicletype.trator"><%=messages.getText("datatype.vehicletype.trator")%></html:option>
									<html:option value="datatype.vehicletype.van"><%=messages.getText("datatype.vehicletype.van")%></html:option>
									<html:option value="datatype.vehicletype.minivan"><%=messages.getText("datatype.vehicletype.minivan")%></html:option>
									<html:option value="datatype.vehicletype.semireboque"><%=messages.getText("datatype.vehicletype.semireboque")%></html:option>
								</html:select>
<%					break;	
				default:
					session.setAttribute("vt",new DataTypeUtil(sys).getVehicleType(false,true,false));
%>		        				<html:select property="type" styleClass="input" style="width:155;">
                  					<html:options collection="vt" property="value" labelProperty="label" styleClass="input"/>
                				</html:select>
<%			}
%>            				</td>
          				</tr>
						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
          				<tr class="texts" >
            				<th align="left"> 
            					<%=messages.getText("generic.vehicle.vehicledata.registrationNumber")%>
            					<span class="required">*</span>
            				</th>
            				<td align="right">
                				<html:text property="matricula" style="width:155" styleClass="input" maxlength="30"/>
            				</td>
          				</tr>
<%		if(sys.getCorporationId() == CorporationData.MONSANTO) {
			Collection assignedVehicles = new LinkedList();
			Iterator iterAV = dtReportFacade.findAll(DataTypeConstants.ASSIGNED_VEHICLE, sys.getUserSessionData()).iterator();
			while(iterAV.hasNext()) {
				String value = ((AssignedVehicleDataType)iterAV.next()).getResourceBundleId();
				assignedVehicles.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
			}
			request.setAttribute("assignedVehicles",assignedVehicles);
%>						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left"> <%=messages.getText("corporation.monsanto.assignedvehicles")%></th>
							<td align="left">
		      					<html:select property="assignedVehicle" styleClass="input" style="width:120;" >
									<html:options collection="assignedVehicles" property="value" labelProperty="label"/>
			  					</html:select>   
							</td>
						</tr>
<%		}
%>						<tr  class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
<%		if(!sys.isCorporation(new int[]{CorporationData.CIMSA,CorporationData.PEPSICO,CorporationData.DOW})) {
%>          				<tr class="texts" >
            				<th align="left"> 
            					<%=messages.getText("generic.vehicle.vehicledata.cost")%>
            				</th>
            				<td align="right">
                				<html:text property="cost" style="width:75" styleClass="input" maxlength="10"/>
            				</td>
          				</tr>
          				<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
<%					    // Klabin: Prefijo ------------------
		}
		if (hasOptionalData && !sys.isCorporation(CorporationData.FORESTALORIENTAL)){ 
%>						   
							
							<tr class="texts" >
            				<th align="left"> 
            					<%=messages.getText("generic.vehicle.vehicledata.prefix")%>            					
            				</th>
            				<td align="right">
                				<html:text property="prefix" style="width:155" styleClass="input" maxlength="30"/>
            				</td>
          				</tr>
						<tr  class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
<%			}
				
			
				if(sys.getCorporationId() == CorporationData.CIMSA) {
%>		  				<tr class="texts" >
            				<th align="left">
            					<%=messages.getText("generic.vehicledata.ensurancepolicy")%>
            				</th>
            				<td align="right">
                				<html:text property="costCenter" style="width:155" styleClass="input" maxlength="15"/>
            				</td>
          				</tr>
						<tr  class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
		  				<tr class="texts" >
            				<th align="left">
            					<%=messages.getText("generic.vehicledata.ensurancepoint")%>
            				</th>
            				<td align="right">
                				<html:text property="ensurance" style="width:155" styleClass="input" maxlength="15"/>
            				</td>
          				</tr>
						<tr  class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
<%				}else {
						// Klabin no tiene centro de costo:
						// Pepsico no tiene centro de costo
					if(!sys.isCorporation(new int[]{CorporationData.KLABIN,CorporationData.PEPSICO,CorporationData.DOW})) {
%>	      				<tr class="texts" >
            				<th align="left">
            					<%=messages.getText("generic.vehicle.vehicledata.costCenter")%>
            				</th>
            				<td align="right">
<%		if(sys.isCorporation(CorporationData.MONTES_DE_PLATA)) {
%>              				<html:select property="costCenter" style="width:155" styleClass="input">
									<html:option value="SPOT">SPOT</html:option>
									<html:option value="LARGO PLAZO">LARGO PLAZO</html:option>
								</html:select>
<%		}else {
%>              				<html:text property="costCenter" style="width:155" styleClass="input"/>
<%		}
%>          				</td>
            				</td>
          				</tr>
<% 						} 
%>						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
          				<tr class="texts">
            				<th align="left"> 
            					<%=messages.getText("generic.vehicle.vehicledata.permissionNumber")%> 
            				</th>          
            				<td align="right">
            					<html:text property="permissionNumber" style="width:155" styleClass="input" value = '<%=vehicleData.getPermissionNumber() %>' maxlength="30"/>
            				</td>
          				</tr>
						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
<%			if(!sys.isCorporation(CorporationData.DOW)) {						
%>          				<tr class="texts">
            				<th align="left"> <%=messages.getText("generic.vehicle.vehicledata.permissionExpiryDate")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%></th>
            				<td align="right">
            					<html:text property="permissionExpiryDay" style="width:20;" styleClass="input" maxlength="2" styleId="dayId1"/>
              					&nbsp;/&nbsp;
              					<html:text property="permissionExpiryMonth" style="width:20;" styleClass="input" maxlength="2" styleId="monthId1"/>
              					&nbsp;/&nbsp;
              					<html:text property="permissionExpiryYear" style="width:40;" styleClass="input" maxlength="4" styleId="yearId1"/>
              					<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button1" style="cursor: pointer; border: 1px solid red;"
     							onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>
			          		</td>
			          		<script type="text/javascript">
								Calendar.setup({
        							inputArea     :    "yearId1",
        							ifFormat       :    "%d/%m/%Y",
        							showsTime      :    true,
        							timeFormat     :    "24",
        							button         :    "calendar_button1",
        							step           :    1,
        							onSelect       :    onSelect1,
        							singleClick    :    true
    							});
							</script>
          				</tr>
						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
<%			}						
%>
		  				<tr class="texts">
            				<th align="left"> 
            					<%=messages.getText("generic.vehicle.vehicledata.purchaseDate")%><%=" ( "+messages.getText("generic.messages.dateformat")+" )"%>
            				</th>
            				<td align="right">
					            <html:text property="purchaseDay" style="width:20;" styleClass="input" maxlength="2" styleId="dayId2"/>
					            &nbsp;/&nbsp;
					            <html:text property="purchaseMonth" style="width:20;" styleClass="input" maxlength="2" styleId="monthId2"/>
					            &nbsp;/&nbsp;
					            <html:text property="purchaseYear" style="width:40;" styleClass="input" maxlength="4" styleId="yearId2"/>
            					<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button2" style="cursor: pointer; border: 1px solid red;"
     							onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>
			          		</td>
			          			<script type="text/javascript">
									Calendar.setup({
        									inputArea     :    "yearId2",
        									ifFormat       :    "%d/%m/%Y",
        									showsTime      :    true,
        									timeFormat     :    "24",
        									button         :    "calendar_button2",
        									step           :    1,
        									onSelect       :    onSelect2,
        									singleClick    :    true
    									});
								</script>
          				</tr>
						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
<%		if(!sys.isCorporation(CorporationData.DOW)) {						
%>          				<tr class="texts">
            				<th align="left" > 
            					<%=messages.getText("generic.vehicle.vehicledata.ownership")%>
            				</th>
            				<td align="right">
<%			Collection ownership = new LinkedList();
			ownership.add(new OptionItem(messages.getText("generic.messages.select"),""));
			if(sys.isCorporation(CorporationData.SYNGENTA)) {
				ownership.add(new OptionItem(messages.getText("datatype.ownership.contract"),"datatype.ownership.contract"));
				ownership.add(new OptionItem(messages.getText("datatype.ownership.owner"),"datatype.ownership.owner"));				
				ownership.add(new OptionItem(messages.getText("datatype.ownership.leasing"),"datatype.ownership.leasing"));
			}else{
				Iterator iter1 = dtReportFacade.findAll(DataTypeConstants.TYPECENTERCOST,userSessionData).iterator();
				while(iter1.hasNext()) {
					String value = ((TypeCenterCostDataType)iter1.next()).getResourceBundleId();
					ownership.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
				}
			}
			session.setAttribute("cc",ownership);
%>		        				<html:select property="ownership" styleClass="input" style="width:155;" >
                  					<html:options collection="cc" property="value" labelProperty="label" styleClass="input"/>
                				</html:select>
            				</td>
          				</tr>
          				
<% 		}
		if (userSessionData.getFilter().getCorporationId().equals(new Integer(CorporationData.MONSANTO)))
          				{ %>
          				<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
          				<tr class="texts">
            				<th align="left"> 
            					<%=messages.getText("vehicle.data.greencard")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%>
            				</th>
            				<td align="right">
            					<html:text property="greenCardExpiryDate" style="width:20;" styleClass="input" maxlength="2" styleId="dayId3"/>
	                			&nbsp;/&nbsp;
      	        				<html:text property="greenCardExpiryMonth" style="width:20;" styleClass="input" maxlength="2" styleId="monthId3"/>
			            		&nbsp;/&nbsp;
	            				<html:text property="greenCardExpiryYear" style="width:40;" styleClass="input" maxlength="4" styleId="yearId3" />
	            				<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button3" style="cursor: pointer; border: 1px solid red;"
     							onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>
			          		</td>
			          			<script type="text/javascript">
									Calendar.setup({
        									inputArea     :    "yearId2",
        									ifFormat       :    "%d/%m/%Y",
        									showsTime      :    true,
        									timeFormat     :    "24",
        									button         :    "calendar_button3",
        									step           :    1,
        									onSelect       :    onSelect3,
        									singleClick    :    true
    									});
								</script>
            				
          				</tr>
          				<% } %>
          				
          				
<%			if(sys.getUserSessionData().isForest()) {
	 			if (hasOptionalData && !sys.isCorporation(CorporationData.FORESTALORIENTAL)){	        	
	               	if(sys.getCorporationId() != CorporationData.YOLI && sys.getCorporationId() != CorporationData.CIMSA) {
%>
		 						<tr class="space">
		 							<th align="right">&nbsp;</th>
		 							<td align="right">&nbsp;</td>
		 						</tr>
		 		  				<tr class="texts">
		             				<th align="left"> 
		             					<%=messages.getText("vehicle.data.antt")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%>
		             				</th>
		             				<td align="right">
		             					<html:text property="anttExpiryDate" style="width:20;" styleClass="input" maxlength="2" styleId="dayId6" />
		 	                			&nbsp;/&nbsp;
		       	        				<html:text property="anttExpiryMonth" style="width:20;" styleClass="input" maxlength="2" styleId="monthId6" />
		 			            		&nbsp;/&nbsp;
		 	            				<html:text property="anttExpiryYear" style="width:40;" styleClass="input" maxlength="4"  styleId="yearId6"/>
		 	            				<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button6" style="cursor: pointer; border: 1px solid red;"
		      							onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>			          		
		             				</td>
		             				<script type="text/javascript">
		 									Calendar.setup({
		         									inputArea     :    "yearId6",
		         									ifFormat       :    "%d/%m/%Y",
		         									showsTime      :    true,
		         									timeFormat     :    "24",
		         									button         :    "calendar_button6",
		         									step           :    1,
		         									onSelect       :    onSelect6,
		         									singleClick    :    true
		     									});
		 								</script>
		           				</tr>
		 						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		 		  				<tr class="texts">
		             				<th align="left"> 
		             					<%=messages.getText("vehicle.data.citv")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%>
		             				</th>
		             				<td align="right">
		             					<html:text property="citvExpiryDate" style="width:20;" styleClass="input" maxlength="2" styleId="dayId7" />
		 	                			&nbsp;/&nbsp;
		       	        				<html:text property="citvExpiryMonth" style="width:20;" styleClass="input" maxlength="2" styleId="monthId7"/>
		 			            		&nbsp;/&nbsp;
		 	            				<html:text property="citvExpiryYear" style="width:40;" styleClass="input" maxlength="4"  styleId="yearId7"/>
		 	            				<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button7" style="cursor: pointer; border: 1px solid red;"
		      							onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>			          		
		             				
		             				</td>
		             				<script type="text/javascript">
		 									Calendar.setup({
		         									inputArea     :    "yearId7",
		         									ifFormat       :    "%d/%m/%Y",
		         									showsTime      :    true,
		         									timeFormat     :    "24",
		         									button         :    "calendar_button7",
		         									step           :    1,
		         									onSelect       :    onSelect7,
		         									singleClick    :    true
		     									});
		 								</script>
		           				</tr>
<%					}   
				}
	           	if (!hasOptionalData || userSessionData.getFilter().getCorporationId().equals(new Integer(CorporationData.YOLI))){
%>
					<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
		  				<tr class="texts">
            				<th align="left"> 
            					<%=messages.getText("vehicle.data.sucta")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%>
            				</th>
            				<td align="right">
            					<html:text property="suctaExpiryDate" style="width:35;" styleClass="input" maxlength="2"/>
	                			&nbsp;/&nbsp;
      	        				<html:text property="suctaExpiryMonth" style="width:35;" styleClass="input" maxlength="2"/>
			            		&nbsp;/&nbsp;
	            				<html:text property="suctaExpiryYear" style="width:55;" styleClass="input" maxlength="4"/>
            				</td>
          				</tr>
						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
		  				<tr class="texts">
            				<th align="left"> 
            					<%=messages.getText("vehicle.data.mtop")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%>
            				</th>
            				<td align="right">
            					<html:text property="mtopExpiryDate" style="width:35;" styleClass="input" maxlength="2"/>
	                			&nbsp;/&nbsp;
      	        				<html:text property="mtopExpiryMonth" style="width:35;" styleClass="input" maxlength="2"/>
			            		&nbsp;/&nbsp;
	            				<html:text property="mtopExpiryYear" style="width:55;" styleClass="input" maxlength="4"/>
            				</td>
          				</tr>
<%				} 
%>
          				<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
          				<tr class="texts">
            				<th align="left"> 
            					<%=messages.getText("vehicle.data.ownercard")%> 
            				</th>          
            				<td align="right">
            					<html:radio property="ownerCard" value="1"/><%=messages.getText("generic.vehiclecheck.form.correct")%>
            					&nbsp;
            					<html:radio property="ownerCard" value="2"/><%=messages.getText("generic.vehiclecheck.form.incorrect")%>
            				</td>
          				</tr>
          				
<% 				//Kilometraje Actual y fecha de medicion.
          		if (hasOptionalData && !sys.isCorporation(CorporationData.FORESTALORIENTAL)){ 
%>		  				<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
          				<tr class="texts">
            				<th align="left"> 
            					<%=messages.getText("vehicle.data.currentkm")%> 
            				</th>          
            				<td align="right">
            					<html:text property="currentKm" style="width:155;" styleClass="input" />
	                		</td>
          				</tr>
          				<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		  				<tr class="texts">
            				<th align="left"> 
            					<%=messages.getText("vehicle.data.datecurrentkm")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%>
            				</th>
            				<td align="right">
            					<html:text property="dateCurrentKmDate" style="width:20;" styleClass="input" maxlength="2" styleId="dayId8" />
	                			&nbsp;/&nbsp;
      	        				<html:text property="dateCurrentKmMonth" style="width:20;" styleClass="input" maxlength="2" styleId="monthId8"/>
			            		&nbsp;/&nbsp;
	            				<html:text property="dateCurrentKmYear" style="width:40;" styleClass="input" maxlength="4"  styleId="yearId8"/>
	            				<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button8" style="cursor: pointer; border: 1px solid red;"
     							onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>			          		
            				
            				</td>
            				<script type="text/javascript">
									Calendar.setup({
        									inputArea     :    "yearId8",
        									ifFormat       :    "%d/%m/%Y",
        									showsTime      :    true,
        									timeFormat     :    "24",
        									button         :    "calendar_button8",
        									step           :    1,
        									onSelect       :    onSelect8,
        									singleClick    :    true
    									});
								</script>
          				</tr>
          		
<% 				} 
%>						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
          				<tr class="texts">
            				<th align="left"> 
            					<%=messages.getText("vehicle.data.ensurancetype")%> 
            				</th>          
            				<td align="right">
<%			Collection ensurance = new LinkedList();
			ensurance.add(new OptionItem(messages.getText("generic.messages.select"),""));
			Iterator iter2 = dtReportFacade.findAll(DataTypeConstants.ENSURANCE_TYPE,userSessionData).iterator();
			while(iter2.hasNext()) {
				String value = ((EnsuranceDataType)iter2.next()).getResourceBundleId();
				ensurance.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
			}
			session.setAttribute("ensurance",ensurance);
%>		        				<html:select property="ensuranceType" styleClass="input" style="width:155;">
                  					<html:options collection="ensurance" property="value" labelProperty="label" styleClass="input"/>
                				</html:select>
            				</td>
          				</tr>
						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
          				<tr class="texts">
            				<th align="left"> 
            					<%=messages.getText("vehicle.data.ensurance")%> 
            				</th>          
            				<td align="right">
            					<html:text property="ensurance" style="width:155" styleClass="input" value = '<%=vehicleData.getEnsurance() %>' maxlength="30"/>
            				</td>
          				</tr>
          				
          				
<%				if(sys.isCorporation(CorporationData.MONTES_DE_PLATA)) {
%>	
						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr class="texts">
							<th align="left" >
								<%=messages.getText("generic.vehicle.vehicledata.neumaticsuspension")%>
							</th>
							<td align="right">
								<html:select property="prefix" styleClass="input" style="width:155;">
                  					<html:options collection="yesno" property="value" labelProperty="label" styleClass="input"/>
								</html:select>
							</td>
						</tr>
						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left"> 
								<%=messages.getText("generic.vehicle.vehicledata.weightcontrol")%>
							</th>
							<td align="right">
								<html:select property="monitoringSystem" styleClass="input" style="width:155;">
                  					<html:options collection="yesno" property="value" labelProperty="label" styleClass="input"/>
								</html:select>
							</td>
						</tr>
						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr class="texts" >
							<th align="left"> 
								<%=messages.getText("generic.vehicle.vehicledata.euromotor")%>
							</th>
							<td align="right">
								<html:select property="renavan" styleClass="input" style="width:155;">
                  					<html:options collection="yesno" property="value" labelProperty="label" styleClass="input"/>
								</html:select>
							</td>
						</tr>
						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr class="texts" >
							<th align="left"> 
								<%=messages.getText("generic.vehicle.vehicledata.mode")%>
							</th>
							<td align="right">
								<html:select property="tachograph" styleClass="input" style="width:155;">
                  					<html:option value="DOBLE"  styleClass="input">DOBLE</html:option>
                  					<html:option value="SIMPLE"  styleClass="input">SIMPLE</html:option>
								</html:select>
							</td>
						</tr>
<%				}
				if (hasOptionalData && !userSessionData.getFilter().getCorporationId().equals(new Integer(CorporationData.FORESTALORIENTAL))){ 
					if(!userSessionData.getFilter().getCorporationId().equals(new Integer(CorporationData.YOLI)) ) {
%>
						<!--  Peso Bruto Total -->
						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
          				<tr class="texts">
            				<th align="left"> 
            					<%=messages.getText("vehicle.data.totalgrossweight")%> 
            				</th>          
            				<td align="right">
            					<html:text property="totalGrossWeight" style="width:155" styleClass="input" maxlength="30"/>
            				</td>
          				</tr>
          				
          				<!--  Peso Bruto Total Combinado -->
          				<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
          				<tr class="texts">
            				<th align="left"> 
            					<%=messages.getText("vehicle.data.totalcombinedgrossweight")%> 
            				</th>          
            				<td align="right">
            					<html:text property="totalCombinedGrossWeight" style="width:155" styleClass="input" maxlength="30"/>
            				</td>
          				</tr>
<%					}
%>          			<!-- Sistema de Seguimiento -->
          				<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
          				<tr class="texts">
            				<th width="224" align="left"> 
            					<%=messages.getText("vehicle.data.monitoringsystem")%> 
            				</th>
            				<td align="right">
<%			Collection monitoring = new LinkedList();
			monitoring.add(new OptionItem(messages.getText("generic.messages.select"),""));
			iter2 = dtReportFacade.findAll(DataTypeConstants.MONITORING_SYSTEM,userSessionData).iterator();
			while(iter2.hasNext()) {
				String value = ((EnsuranceDataType)iter2.next()).getResourceBundleId();
				monitoring.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
			}
			session.setAttribute("monitoring",monitoring);
%>	                			<html:select property="monitoringSystem" styleClass="input" style="width:155">
                  					<html:options collection="monitoring" property="value" labelProperty="label" styleClass="input"/>
                				</html:select>
            				</td>
          				</tr>
<%					if(!userSessionData.getFilter().getCorporationId().equals(new Integer(CorporationData.YOLI))) {
%>          				
          				<!-- Tachograph -->
          				<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
          				<tr class="texts">
            				<th width="224" align="left"> 
            					<%=messages.getText("vehicle.data.tachograph")%> 
            				</th>
            				<td align="right">
	                			<html:select property="tachograph" styleClass="input" style="width:155" 
	                			value='<%= (vehicleData.getOptionalInfo() != null && 
	                						vehicleData.getOptionalInfo().getTachograph() != null)?
	                								vehicleData.getOptionalInfo().getTachograph():"" %>' >
	                				<html:option value=""><%=messages.getText("generic.messages.select")%></html:option>
                  					<html:option value="vehicle.data.mechanic"><%=messages.getText("vehicle.data.mechanic")%></html:option>
                  					<html:option value="vehicle.data.electronic"><%=messages.getText("vehicle.data.electronic")%></html:option>
                				</html:select>
            				</td>
          				</tr>
          		
<%					}
				}
			} //fin forestal
		}
%>        			</table>
      				<jsp:include page="../component/utilityboxbottom.jsp"/>
				</td>
  			</tr>
<%		}
%>  			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
<%		if(sys.getCorporationId() == CorporationData.MONSANTO) {
			Collection company = new LinkedList();
			company.add(new OptionItem("MONSANTO","MONSANTO"));
			company.add(new OptionItem("MONSOY","MONSOY"));
			company.add(new OptionItem("SEMINIS","SEMINIS"));
			company.add(new OptionItem("DP&L","DP&L"));
			company.add(new OptionItem("NOVOZYMES","NOVOZYMES"));
			company.add(new OptionItem("CANAVIALIS","CANAVIALIS"));
			session.setAttribute("company",company);
%>
  			<tr>
    			<td colspan="3" valign="top">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.extradata")%>' />
        			<table width="100%" cellpadding="0" cellspacing="0">
          				<tr class="texts">
            				<th align="left" >
            					<%=messages.getText("generic.vehicle.vehicledata.active")%>
            				</th>
            				<td align="right">
                				<html:text property="prefix" style="width:155" styleClass="input" maxlength="30"/>
            				</td>
          				</tr>
						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left"> 
            					<%=messages.getText("generic.vehicle.vehicledata.responsible")%>
							</th>
							<td align="right">
                				<html:text property="monitoringSystem" style="width:155" styleClass="input" maxlength="30"/>
							</td>
						</tr>
						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
          				<tr class="texts">
            				<th align="left" > 
            					<%=messages.getText("generic.vehicle.vehicledata.company")%>
            				</th>
            				<td align="right">
		        				<html:select property="tachograph" styleClass="input" style="width:155;">
                  					<html:options collection="company" property="value" labelProperty="label" styleClass="input"/>
                				</html:select>
            				</td>
          				</tr>
						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		  				<tr class="texts" >
            				<th align="left"> 
            					<%=messages.getText("generic.vehicle.vehicledata.plate")%>
            				</th>
            				<td align="right">
                				<html:text property="renavan" style="width:155" styleClass="input" maxlength="10"/>
            				</td>
          				</tr>
        			</table>
      				<jsp:include page="../component/utilityboxbottom.jsp"/>
				</td>
  			</tr>
  			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>

<%		}
%>
			<tr>
				<td width="100%" colspan="3">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.observations")%>' />
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<html:textarea property="observations" cols="70" />
							</td>
						</tr>
					</table>
					<jsp:include  page="../component/utilityboxbottom.jsp" />
				</td>
			</tr>
  			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
			</tr>
<%		if(sys.isCorporation(CorporationData.DOW)) {
%>
  			<tr>
    			<td width="100%" valign="top" colspan="3">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.technicaldata")%>' />
        			<table width="100%" cellpadding="0" cellspacing="0">
          				<tr class="texts">
            				<th align="left" > 
            					<%=messages.getText("generic.vehicle.vehicledata.trade")%>
            					<span class="required">*</span>
            				</th>
            				<td align="right">
<%			Collection trade = new LinkedList();
   			trade.add(new OptionItem(messages.getText("generic.messages.select"),""));
   			Iterator iter = dtReportFacade.findAll(DataTypeConstants.TYPETRADE,userSessionData).iterator();
			while(iter.hasNext()) {
				String value = ((TypeTradeDataType)iter.next()).getResourceBundleId();
				if(value.equals("generic.messages.nodata")) {
					trade.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
				}else {
					trade.add(new OptionItem(value,value));				
				}
			}
			session.setAttribute("trade",trade);
%>	                			<html:select property="trade" styleClass="input" style="width:155;">
                  					<html:options collection="trade" property="value" labelProperty="label" styleClass="input"/>
                				</html:select>
            				</td>
          				</tr>
						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
          				<tr class="texts">
            				<th width="224" align="left"> <%=messages.getText("generic.vehicle.vehicledata.model")%><span class="required">*</span></th>
            				<td align="right">
	              				<html:text property="model" style="width:155" styleClass="input"/>
	          				</td>
          				</tr>
						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
          				<tr class="texts">
            				<th align="left"> 
            					<%=messages.getText("generic.vehicle.vehicledata.year")%>
            					<span class="required">*</span>
            				</th>
            				<td align="right">
                				<html:text property="year" style="width:155" styleClass="input" maxlength="4"/>
            				</td>
          				</tr>
						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
          				<tr class="texts">
            				<th align="left"> 
            					<%=messages.getText("generic.vehicle.vehicledata.costCenter")%>
            				</th>
            				<td align="right">
<%		if(sys.isCorporation(CorporationData.MONTES_DE_PLATA)) {
%>              				<html:select property="costCenter" style="width:155" styleClass="input">
									<html:option value="SPOT">SPOT</html:option>
									<html:option value="LARGO PLAZO">LARGO PLAZO</html:option>
								</html:select>
<%		}else {
%>              				<html:text property="costCenter" style="width:155" styleClass="input" />
<%		}
%>          				</td>
          				</tr>
						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
          				<tr class="texts">
            				<th align="left" > 
            					<%=messages.getText("generic.vehicle.vehicledata.colour")%> 
            				</th>
            				<td align="right">
                				<html:text property="colour" style="width:155" styleClass="input" maxlength="30"/>
            				</td>
          				</tr>
	      				<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
          				<tr class="texts">
            				<th width="224" align="left"> 
            					<%=messages.getText("generic.vehicle.vehicledata.chasisNumber")%> 
            				</th>
            				<td align="right">
                				<html:text property="chasisNumber" style="width:155" styleClass="input" maxlength="30"/>
            				</td>
          				</tr>
						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
          				<tr class="texts">
            				<th align="left" > 
            					<%=messages.getText("generic.vehicle.vehicledata.fuel")%>
            				</th>
            				<td align="right">
<%			Collection fuel = new LinkedList();
			fuel.add(new OptionItem(messages.getText("datatype.fuel.diesel"),"datatype.fuel.diesel"));
   			fuel.add(new OptionItem(messages.getText("datatype.fuel.gasoline"),"datatype.fuel.gasoline"));
   			fuel.add(new OptionItem(messages.getText("datatype.fuel.glp"),"datatype.fuel.glp"));
   			fuel.add(new OptionItem(messages.getText("datatype.fuel.nodata"),"datatype.fuel.nodata"));
			session.setAttribute("f",fuel);
%>
                				<html:select property="fuel" styleClass="input" style="width:155;">
                  					<html:options collection="f" property="value" labelProperty="label" styleClass="input"/>
                				</html:select>
            				</td>
          				</tr>
        			</table>
					<jsp:include  page="../component/utilityboxbottom.jsp" />
				</td>
			</tr>
			
<%		} else {
%>
  			<tr>
    			<td width="49%" valign="top">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.technicaldata")%>' />
        			<table width="100%" cellpadding="0" cellspacing="0">
<%			if(sys.getCorporationId() == CorporationData.ALLERGAN) {
%>
						<tr>
							<!--  Matricula o Numero Economico para algunas empresas -->
							<td align="left" class="texts"><%=messages.getText("generic.vehicle.vehicledata.registrationNumber")%></td>
            				<td align="right">
                				<html:text property="matricula" style="width:155" styleClass="input" maxlength="30" value='<%= vehicleData.getMatricula() %>'/>
            				</td>
						</tr>
						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
          				<tr class="texts">
            				<th align="left" >
            					<%=messages.getText("generic.vehicle.vehicledata.vehicletype")%>
            					<span class="required">*</span>
            				</th>
            				<td align="right">
<%				session.setAttribute("vt",new DataTypeUtil(sys).getVehicleType(false,true,false));
%>		        				<html:select property="type" styleClass="input" style="width:155;">
                  					<html:options collection="vt" property="value" labelProperty="label" styleClass="input"/>
                				</html:select>
            				</td>
          				</tr>
						<tr  class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
<%		}
%>
          				<tr class="texts">
            				<th align="left" >
            	 				<%=messages.getText("generic.vehicle.vehicledata.trade")%>
            	 				<span class="required">*</span>
            				</th>
            				<td align="left">
            					<div align="right">
<%			Collection trade = new LinkedList();
   			trade.add(new OptionItem(messages.getText("generic.messages.select"),""));
			if(sys.getCorporationId() == CorporationData.CIMSA) {
				trade.add(new OptionItem("Ford","Ford"));				
				trade.add(new OptionItem("Mercedes-Benz","Mercedes-Benz"));				
				trade.add(new OptionItem("Freightliner","Freightliner"));				
				trade.add(new OptionItem("Hino","Hino"));				
				trade.add(new OptionItem("Kenworth","Kenworth"));				
				trade.add(new OptionItem("Murrel","Murrel"));	
				trade.add(new OptionItem("Chevrolet","Chevrolet"));
				trade.add(new OptionItem("Honda","Honda"));
				trade.add(new OptionItem("Nissan","Nissan"));
				trade.add(new OptionItem("Vectio","Vectio"));
				trade.add(new OptionItem("Volkswagen","Volkswagen"));
				trade.add(new OptionItem(messages.getText("generic.messages.nodata"),"generic.messages.nodata"));
			}else {
				Iterator iter = dtReportFacade.findAll(DataTypeConstants.TYPETRADE,userSessionData).iterator();
				while(iter.hasNext()) {
					String value = ((TypeTradeDataType)iter.next()).getResourceBundleId();
					if(value.equals("generic.messages.nodata")) {
						trade.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
					}else {
						trade.add(new OptionItem(value,value));				
					}
				}
			}
			session.setAttribute("trade",trade);
%>		        					<html:select property="trade" styleClass="input" style="width:155;">
                  						<html:options collection="trade" property="value" labelProperty="label" styleClass="input"/>
	                				</html:select>
    					        </div>
							</td>
          				</tr>
						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
          				<tr class="texts">
            				<th align="left"> 
            					<%=messages.getText("generic.vehicle.vehicledata.model")%>
            					<span class="required">*</span>
            				</th>
            				<td align="right">
<%			if(sys.getCorporationId() == CorporationData.CIMSA) {
				Collection model = new LinkedList();
				model.add(new OptionItem(messages.getText("generic.messages.select"),""));
				model.add(new OptionItem("716","716"));
				model.add(new OptionItem("F-350","F-350"));
				model.add(new OptionItem("F-450","F-450"));
				model.add(new OptionItem("FL70","FL70"));
				model.add(new OptionItem("ECOPOWER","ECOPOWER"));
				model.add(new OptionItem("1417","1417"));
				model.add(new OptionItem("1418","1418"));
				model.add(new OptionItem("1619","1619"));
				model.add(new OptionItem("1217","1217"));
				model.add(new OptionItem("1518","1518"));
				model.add(new OptionItem("1617","1617"));
				model.add(new OptionItem("2400","2400"));
				model.add(new OptionItem("ELECTRICO","ELECTRICO"));
				model.add(new OptionItem("414","414"));
				model.add(new OptionItem("CG125","CG125"));
				model.add(new OptionItem("CHEVY","CHEVY"));
				model.add(new OptionItem("E-150","E-150"));
				model.add(new OptionItem("F-550","F-550"));
				model.add(new OptionItem("F-250","F-250"));
				model.add(new OptionItem("HIBRIDO","HIBRIDO"));
				model.add(new OptionItem("M2","M2"));
				model.add(new OptionItem("PICK UP","PICK UP"));
				model.add(new OptionItem("SEDAN","SEDAN"));
				model.add(new OptionItem(messages.getText("generic.messages.nodata"),"generic.messages.nodata"));

				session.setAttribute("model",model);
%>	                			<html:select property="model" styleClass="input" style="width:155;">
									<html:options collection="model" property="value" labelProperty="label" styleClass="input"/>
								</html:select>
<%			}else {
%>              				<html:text property="model" style="width:155" styleClass="input"/>
<%			}
%>          				</td>
          				</tr>
						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
          				<tr class="texts">
            				<th align="left"> 
				            	<%=messages.getText("generic.vehicle.vehicledata.year")%>
				            	<span class="required">*</span>
            				</th>
            				<td align="left">
            					<div align="right">
                					<html:text property="year" style="width:155" styleClass="input" value='<%= vehicleData.getYear().toString() %>' maxlength="4" />
            					</div>
            				</td>
          				</tr>
<%		if(sys.getCorporationId() != CorporationData.CIMSA) {
%>						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
          				<tr class="texts">
            				<th align="left" > 
            					<%=messages.getText("generic.vehicle.vehicledata.colour")%> 
            				</th>
            				<td align="left">
            					<div align="right">
                					<html:text property="colour" style="width:155" styleClass="input" value='<%= vehicleData.getColour() %>' maxlength="30"/>
            					</div>
            				</td>
          				</tr>
<%		}
%>						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
          				<tr class="texts">
            				<th align="left">
            					<%=messages.getText("generic.vehicle.vehicledata.chasisNumber")%> 
            				</th>
            				<td align="left">
            					<div align="right">           
                					<html:text property="chasisNumber" style="width:155" styleClass="input" value='<%=vehicleData.getChasisNumber()%>' maxlength="30"/>
            					</div>
            				</td>
          				</tr>
<%			if(sys.getCorporationId() == CorporationData.ALLERGAN) {
%>
						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
	       				<tr class="texts">
	           				<th align="left">
	           					<%=messages.getText("generic.vehicle.vehicledata.permissionNumber")%> 
	           				</th>
	           				<td align="right">
            					<html:text property="permissionNumber" style="width:155" styleClass="input" value = '<%=vehicleData.getPermissionNumber() %>' maxlength="30"/>
	           				</td>
	       				</tr>
						<tr  class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
          				<tr class="texts">
            				<th align="left"> 
            					<%=messages.getText("generic.vehicle.vehicledata.purchaseDate")%><%=" ( "+messages.getText("generic.messages.dateformat")+" )"%>
            				</th>
            				<td align="right">
					            <html:text property="purchaseDay" style="width:20;" styleClass="input" maxlength="2" styleId="dayId2"/>
					            &nbsp;/&nbsp;
					            <html:text property="purchaseMonth" style="width:20;" styleClass="input" maxlength="2" styleId="monthId2"/>
					            &nbsp;/&nbsp;
					            <html:text property="purchaseYear" style="width:40;" styleClass="input" maxlength="4" styleId="yearId2"/>
            					<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button2" style="cursor: pointer; border: 1px solid red;"
     							onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>
			          		</td>
			          			<script type="text/javascript">
									Calendar.setup({
        									inputArea     :    "yearId2",
        									ifFormat       :    "%d/%m/%Y",
        									showsTime      :    true,
        									timeFormat     :    "24",
        									button         :    "calendar_button2",
        									step           :    1,
        									onSelect       :    onSelect2,
        									singleClick    :    true
    									});
								</script>
						</tr>
<%			}
		  	if (sys.getCorporationId() == CorporationData.JJ){ 
%>		  				<tr class="texts">
            				<th align="left">
            					<%=messages.getText("generic.vehicle.vehicledata.co2emission")%> 
            				</th>
            				<td align="left">
            					<div align="right">           
                					<html:text property="co2emission" style="width:155" styleClass="input" 
                					value='<%= (vehicleData.getCo2emission() != null) ? String.valueOf(vehicleData.getCo2emission()): "" %>' maxlength="30"/>
            					</div>
            				</td>
          				</tr>
<% 	      	} 
			if(sys.getUserSessionData().isForest()) {
%>						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
          				<tr class="texts">
            				<th width="224" align="left"> 
            					<%=messages.getText("vehicle.data.axiscount")%> 
            				</th>
            				<td align="right">
	                			<html:select property="axisCount" styleClass="input" >
                  					<html:option value="1">1</html:option>
                  					<html:option value="2">2</html:option>
                  					<html:option value="3">3</html:option>
                  					<html:option value="4">4</html:option>
                				</html:select>
            				</td>
          				</tr>
<%			}
			// Limit Load 
			if (hasOptionalData  && !sys.isCorporation(CorporationData.FORESTALORIENTAL)){
%>

							<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
	          				<tr class="texts">
	            				<th width="224" align="left"> 
	            					<%=messages.getText("generic.vehicle.vehicledata.limitload")%> 
	            				</th>
	            				<td align="right">
	                				<html:text property="limitLoad" style="width:155" styleClass="input"
	                				 maxlength="30"/>
	            				</td>
	          				</tr>	

<% 			} 
%>		      			</table>
      				<jsp:include page="../component/utilityboxbottom.jsp"/>
				</td>
    			<td width="2%" valign="top">&nbsp;</td>
    			<td width="49%" valign="top">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.enginefuel")%>' />
        			<table width="100%" cellpadding="0" cellspacing="0">
<%		if(corporationId != CorporationData.ALLERGAN) {
%>          				<tr class="texts">
            				<th align="left">
            					<%=messages.getText("generic.vehicle.vehicledata.engine")%>
            				</th>
            				<td align="left">
            					<div align="right">
<%			Collection engine = new LinkedList();
	   		engine.add(new OptionItem(messages.getText("generic.messages.select"),""));                                
	  			Iterator iter3 = dtReportFacade.findAll(DataTypeConstants.TYPEENGINE,userSessionData).iterator();
			while(iter3.hasNext()) {
				String value = ((TypeEngineDataType)iter3.next()).getResourceBundleId();
				engine.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
			}
			session.setAttribute("e",engine);
%>	            					<html:select property="engine" styleClass="input" style="width:155;">
                  						<html:options collection="e" property="value" labelProperty="label" styleClass="input"/>
                					</html:select>
            					</div>
            				</td>
          				</tr>
<%		}
		if(sys.getUserSessionData().isForest() || corporationId == CorporationData.ALLERGAN) {
 		  	String hp = "";
	        if (vehicleData.getHp() != null)
   	      		hp = vehicleData.getHp().toString();		
%>						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
          				<tr class="texts">
            				<th width="224" align="left"> 
            					<%=messages.getText("vehicle.data.hp")%> 
            				</th>
            				<td align="right">
                				<html:text property="hp" style="width:155" styleClass="input" maxlength="4" value="<%=hp%>"/>
            				</td>
          				</tr>
<%			}
%>						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
          				<tr class="texts">
            				<th align="left" >
            					<%=messages.getText("generic.vehicle.vehicledata.fuel")%>
            				</th>
            				<td align="left">
            					<div align="right">
<%			Collection fuel = new LinkedList();
            fuel.add(new OptionItem(messages.getText("generic.messages.select"),""));    
       		if(sys.getCorporationId() != CorporationData.CIMSA) {
    			Iterator iter4 = dtReportFacade.findAll(DataTypeConstants.TYPEFUEL,userSessionData).iterator();
    			while(iter4.hasNext()) {
    				String value = ((TypeFuelDataType)iter4.next()).getResourceBundleId();
    				fuel.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
    			}
       		}else {
       			fuel.add(new OptionItem(messages.getText("datatype.fuel.glp"),"datatype.fuel.glp"));
       			fuel.add(new OptionItem(messages.getText("datatype.fuel.diesel"),"datatype.fuel.diesel"));
       			fuel.add(new OptionItem(messages.getText("datatype.fuel.other"),"datatype.fuel.other"));
       			fuel.add(new OptionItem(messages.getText("datatype.fuel.nodata"),"datatype.fuel.nodata"));
       		}
			session.setAttribute("f",fuel);
%>		        					<html:select property="fuel" styleClass="input" style="width:155;">
					                	<html:options collection="f" property="value" labelProperty="label" styleClass="input"/>
                					</html:select>
            					</div>
            				</td>
          				</tr>
<%		if(corporationId != CorporationData.ALLERGAN) {
%>						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
          				<tr class="texts">
            				<th align="left">
            					<%=messages.getText("generic.vehicle.vehicledata.tankCapacity")%> 
            				</th>
            				<td align="left">
            					<div align="right">
<% 		  		String tc = (vehicleData.getTankCapacity() != null) ? vehicleData.getTankCapacity().toString() : "";
%>		     						<html:text property="tankCapacity" style="width:155;" styleClass="input" value='<%=tc %>' maxlength="4"/>
            					</div>
            				</td>
          				</tr>
<%			if (sys.getCorporationId() == CorporationData.JJ){ 
%>						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
		  				<tr class="texts">
            				<th align="left">
            					<%=messages.getText("generic.vehicle.vehicledata.consume")%> 
            				</th>
            				<td align="left">
            					<div align="right">           
                					<html:text property="consume" style="width:155" styleClass="input" 
                					value='<%= (vehicleData.getConsume() != null) ? String.valueOf(vehicleData.getConsume()): "" %>' maxlength="30"/>
            					</div>
            				</td>
          				</tr>
<% 	      	} 
%>						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
          				<tr class="texts">
            				<th align="left" > 
            					<%=messages.getText("generic.vehicle.vehicledata.volumeunit")%>
            				</th>
            				<td align="left">
            					<div align="right">
<%			Collection volumeUnit = new LinkedList();
            volumeUnit.add(new OptionItem(messages.getText("generic.messages.select"),""));        
			Iterator iter5 = dtReportFacade.findAll(DataTypeConstants.TYPEVOLUMEUNIT,userSessionData).iterator();
			while(iter5.hasNext()) {
				String value = ((TypeVolumeUnitDataType)iter5.next()).getResourceBundleId();
				volumeUnit.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
			}
			session.setAttribute("vu",volumeUnit);
%>		        					<html:select property="tankUnit" styleClass="input" style="width:155;">
                  						<html:options collection="vu" property="value" labelProperty="label" styleClass="input"/>
                					</html:select>
            					</div>
            				</td>
          				</tr>
<%		} 					
%>        			</table>
      				<jsp:include page="../component/utilityboxbottom.jsp"/>  
				</td>
  			</tr>
  			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
			</tr>
<%		if(corporationId != CorporationData.ALLERGAN) {
%>  			<tr>
<%			if(sys.getCorporationId() != CorporationData.CIMSA) {
%>	    		<td valign="top">
		 			<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.coverTypes")%>' />
	        		<table width="100%" cellpadding="0" cellspacing="0">
	          			<tr class="texts">
	            			<th align="left" > 
	            				<%=messages.getText("generic.vehicle.vehicledata.cover")%>
	            			</th>
	            			<td align="left">
	            				<div align="right">
<%			Collection cover = new LinkedList();
            cover.add(new OptionItem(messages.getText("generic.messages.select"),""));        
			Iterator iter10 = dtReportFacade.findAll(DataTypeConstants.TYPECOVER,userSessionData).iterator();
			while(iter10.hasNext()) {
				String value = ((TypeCoverDataType)iter10.next()).getResourceBundleId();
				cover.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
			}
			session.setAttribute("cub",cover);
%>			        				<html:select property="cover" styleClass="input" style="width:155;">
	                  					<html:options collection="cub" property="value" labelProperty="label" styleClass="input"/>
	                				</html:select>
	            				</div>
	            			</td>
	          			</tr>
	        		</table>
	      			<jsp:include page="../component/utilityboxbottom.jsp"/>  
				</td>
    			<td valign="top">&nbsp;</td>
    			<td valign="top" rowspan="3">
<%		}else {
%>				<td width="49%" valign="top">&nbsp;</td>
    			<td width="2%" valign="top">&nbsp;</td>
    			<td width="49%" valign="top" rowspan="2">
<%		}
%>    			
<%		if(sys.getCorporationId() == CorporationData.MONSANTO){	
%>
		<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.accesories")%>' />
   			<table width="100%" border="0" align='right' cellpadding="0" cellspacing="0">
 		
<% 		}else {
%>
 		<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.accesories")%>' />
	       			<table width="100%" cellpadding="0" cellspacing="0">
<%} %>         						
						<tr class="texts">
	           				<td align="left" class="texts">
	           					<html:checkbox property="abs" > <%=messages.getText("generic.vehicle.vehicledata.abs")%></html:checkbox>
	           				</td>
         				</tr>
						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
         				<tr class="texts">
	           				<td align="left" class="texts">
	           					<html:checkbox property="airBag"> <%=messages.getText("generic.vehicle.vehicledata.airBag")%></html:checkbox>
	           				</td>
         				</tr>
						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
         				<tr class="texts">
	           				<td align="left" class="texts">
	           					<html:checkbox property="aireAcondicionado"> <%=messages.getText("generic.vehicle.vehicledata.aireAcondicionado")%></html:checkbox>
		           			</td>
	         			</tr>
<%						if(sys.isCorporation(CorporationData.FORESTALORIENTAL)) {
%>	
						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
          				<tr class="texts">
            				<td align="left" class="texts">
            					<html:checkbox property="suspension"> <%=messages.getText("generic.vehicle.vehicledata.neumaticsuspension")%></html:checkbox>
            				</td>
          				</tr>
          				<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
          				<tr class="texts">
            				<td align="left" class="texts">
            					 <%=messages.getText("generic.vehicle.optionalinfo.renavan")%> 
            				</td>
            				<td align="left" class="texts">
            					 <html:select property="renavan">
            					   <html:option value='NE'  >No Indicado</html:option>
								   <html:option value='SI'  >SI</html:option>
								   <html:option value='NO' >NO</html:option>
								 </html:select> 
            				</td>
          				</tr>  
          				<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
          				<tr class="texts">
            				<td align="left" class="texts">
            					 <%=messages.getText("generic.vehicle.optionalinfo.prefix")%> 
            				</td>
            				<td align="left" class="texts">
            					 <html:select property="prefix">
            					   <html:option value='NE'  >No Indicado</html:option>
								   <html:option value='SI'  >SI</html:option>
								   <html:option value='NO' >NO</html:option>
								 </html:select> 
						</select> 	
            				</td>
          				</tr>  
          				<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
          				<tr class="texts">
            				<td align="left" class="texts">
            					 <%=messages.getText("generic.vehicle.optionalinfo.monitoringsystem")%> 
            				</td>
            				<td align="left" class="texts">
            					  <html:select property="monitoringSystem">
            					   <html:option value='NE'  >No Indicado</html:option>
								   <html:option value='SI'  >SI</html:option>
								   <html:option value='NO' >NO</html:option>
								 </html:select> 
            				</td>
          				</tr>      				
<%						}
		  				if (hasOptionalData  && !userSessionData.getFilter().getCorporationId().equals(new Integer(CorporationData.YOLI)) && !userSessionData.getFilter().getCorporationId().equals(new Integer(CorporationData.FORESTALORIENTAL))){ 
%>		  					<tr class="space">
								<th align="right">&nbsp;</th>
								<td align="right">&nbsp;</td>
							</tr>
    	      				<tr class="texts">
        	    				<td align="left" class="texts">
            						<html:checkbox property="asr"> <%=messages.getText("generic.vehicle.vehicledata.asr")%></html:checkbox>
            					</td>
          					</tr>
<% 						} 
%>			   		</table>
	      			<jsp:include page="../component/utilityboxbottom.jsp"/>
				</td>	
	    	</tr>
  			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
			</tr>
<%		}
		if (corporationId == CorporationData.MONSANTO) {   
%>
			<tr>
<%//DATOS VEHICLE SMF MONSANTO %>
				<td>
			<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.vehicle.vehicleSMF")%>' />
			<table width="100%" border="0" align='left' cellpadding="0" cellspacing="0">
				<tr>
					<td class="default">
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
						<tr  class="texts">
						<th align="left"> 
						<%=messages.getText("generic.vehicle.vehicleSMF.ordernumber")%> 
						</th>
						<td align="left">
            					<div align="right">
            						
            						<html:text property='orderNumber' style='width:155' styleClass='input' 
            						value='<%=(vehicleData.getVehicleSMFData()!= null && 
            								vehicleData.getVehicleSMFData().getOrderNumber() != null)? 
            								vehicleData.getVehicleSMFData().getOrderNumber().toString(): "" %>' maxlength='30'/>
            					</div>
            			</td>
          				</tr>
							<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
							</tr>
          				<tr  class="texts">
						<th align="left"> 
						<%= messages.getText("generic.vehicle.vehicleSMF.corporationSMF")%> 
						</th>
						<td align="left">
            					<div align="right">
            						<html:text property='corporationSMF' style='width:155' styleClass='input' 
            						value='<%=(vehicleData.getVehicleSMFData()!= null && 
            								vehicleData.getVehicleSMFData().getCorporationSMF() != null)? 
            								vehicleData.getVehicleSMFData().getCorporationSMF(): "" %>' maxlength='30'/>
            					</div>
            			</td>
          				</tr>
							<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
							</tr>	
						<tr class="texts">
            				<th align="left"> <%=messages.getText("generic.vehicle.vehicleSMF.instalationDateSMF")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%></th>
            				<td align="right">
            					<html:text property="daySMF" style="width:20;" styleClass="input" maxlength="2" styleId="dayId9"/>
              					&nbsp;/&nbsp;
              					<html:text property="monthSMF" style="width:20;" styleClass="input" maxlength="2" styleId="monthId9"/>
              					&nbsp;/&nbsp;
              					<html:text property="yearSMF" style="width:40;" styleClass="input" maxlength="4" styleId="yearId9"/>
              					<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button9" style="cursor: pointer; border: 1px solid red;"
     							onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>
			          		</td>
			          		<script type="text/javascript">
								Calendar.setup({
        							inputArea     :    "yearId9",
        							ifFormat       :    "%d/%m/%Y",
        							showsTime      :    true,
        							timeFormat     :    "24",
        							button         :    "calendar_button9",
        							step           :    1,
        							onSelect       :    onSelect9,
        							singleClick    :    true
    							});
							</script>
							
          				</tr>	
          				<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>	
						
						<tr  class="texts">
						<th align="left"> 
						<%=messages.getText("generic.vehicle.vehicleSMF.localInstallation")%> 
						</th>
						<td align="left">
            					<div align="right">
            						<html:text property='localInstallation' style='width:155' styleClass='input' 
            						value='<%=(vehicleData.getVehicleSMFData()!= null && 
            								vehicleData.getVehicleSMFData().getLocalInstallation() != null)? 
            								vehicleData.getVehicleSMFData().getLocalInstallation(): "" %>' maxlength='30'/>
            					</div>
            			</td>
          				</tr>
							<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
							</tr>
						<tr  class="texts">
						<th align="left"> 
						<%=messages.getText("generic.vehicle.vehicleSMF.responsibleTechnical")%> 
						</th>
						<td align="left">
            					<div align="right">
            						<html:text property='responsibleTechnical' style='width:155' styleClass='input' 
            						value='<%=(vehicleData.getVehicleSMFData()!= null && 
            								vehicleData.getVehicleSMFData().getResponsibleTechnical() != null)? 
            								vehicleData.getVehicleSMFData().getResponsibleTechnical(): "" %>' maxlength='30'/>
            					</div>
            			</td>
          				</tr>
							<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
							</tr>
						<tr  class="texts">
						<th align="left"> 
						<%=messages.getText("generic.vehicle.vehicleSMF.observationsSMF")%> 
						</th>
						<td align="left">
            					<div align="right">
            						<html:text property='observationsSMF' style='width:155' styleClass='input' 
            						value='<%=(vehicleData.getVehicleSMFData()!= null && 
            								vehicleData.getVehicleSMFData().getObservations() != null)? 
            								vehicleData.getVehicleSMFData().getObservations(): "" %>' maxlength='30'/>
            					</div>
            			</td>
          				</tr>
							<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
							</tr>
						<tr  class="texts">
						<th align="left"> 
						<%=messages.getText("generic.vehicle.vehicleSMF.ustContact")%> 
						</th>
						<td align="left">
            					<div align="right">
            						<html:text property='ustContact' style='width:155' styleClass='input' 
            						value='<%=(vehicleData.getVehicleSMFData()!= null && 
            								vehicleData.getVehicleSMFData().getUstContact() != null)? 
            								vehicleData.getVehicleSMFData().getUstContact(): "" %>' maxlength='30'/>
            					</div>
            			</td>
          				</tr>
							<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
							</tr>
										
					</table>	
					</td>						
					</tr>
					
					</table>
					<jsp:include page="../component/utilityboxbottom.jsp"/>
					</td>
				</tr>
			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
			</tr>
<%		} %>	
	<%//DATOS VEHICLE SMF MONSANTO %>
<%		if(!sys.isCorporation(new int[]{CorporationData.CIMSA,CorporationData.ALLERGAN,CorporationData.SYNGENTA_BR})) {
%>    		<tr>	
    			<td valign="top">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.odometer")%>' />
        			<table width="100%" cellpadding="0" cellspacing="0">
          				<tr class="texts">
            				<th align="left">
            					<%=messages.getText("generic.vehicle.vehicledata.distanceunit")%>
            				</th>
            				<td align="left">
            					<div align="right">
<%			Collection distanceUnit = new LinkedList();
            distanceUnit.add(new OptionItem(messages.getText("generic.messages.select"),""));                        
			Iterator iter2 = dtReportFacade.findAll(DataTypeConstants.TYPEDISTANCEUNIT,userSessionData).iterator();
			while(iter2.hasNext()) {
				String value = ((TypeDistanceUnitDataType)iter2.next()).getResourceBundleId();
				System.out.println("LABEL DISTANCEUNIT:" + messages.getText(value));
				System.out.println("VALOR DISTANCEUNIT:" + value);				
				distanceUnit.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
			}
			session.setAttribute("du",distanceUnit);
%>		        					<html:select property="distanceUnit" styleClass="input" style="width:155;" >
                  						<html:options collection="du" property="value" labelProperty="label" styleClass="input"/>
                					</html:select>
            					</div>
            				</td>
          				</tr>
        			</table>
      				<jsp:include page="../component/utilityboxbottom.jsp"/>
				</td>
  			</tr>
    		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
			</tr>
<%			}else {
%>		  	<tr> 
				<jsp:include  page='<%="../component/spaceline.jsp?colspan=5"%>'/>
  			</tr>
<%			}
		}
%>			<tr>
				<td width="100%" colspan="3">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.comment")%>' />
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<html:textarea property="comment" cols="70"/>
							</td>
						</tr>
					</table>
					<jsp:include  page="../component/utilityboxbottom.jsp" />
				</td>
			</tr>
			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
		  	</tr>	
			

				
			<tr>
	  			<td height="18" colspan="3" valign="top">
					<div align="right">
						 <html:submit styleClass="input"> <%=messages.getText("generic.messages.save")%> </html:submit>
					     <html:cancel styleClass="input"> <%=messages.getText("generic.messages.cancel")%> </html:cancel >
			  		</div>    
				</td>
			</tr>
				
		</table>
		
	</html:form>   
</body>
<%		}else {
%>	<jsp:include  page='<%="/util/allowedData.jsp?message=vehicle.error.access.vehicle" %>'/>
<%		}
	}	
%>
</html:html>
