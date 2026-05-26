<%@page language="java" 
     errorPage="../exception.jsp"
	import="business.*"
	import="business.datatype.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.event.*"
	import="communication.driver.tables.*"
	import="communication.language.*"
	import="communication.datatype.*"
	import="communication.event.tables.*"
	import="communication.datatype.tables.*"
	import="communication.driver.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.administration.corporation.tables.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>
<%!
	RequestDispatcher dispatcher; SystemAccess sys;	String context;
	String pathLanguage; Text messages; Integer driverId; String driverName; String driverLastName;
	EventForm form;
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	if(sys == null){
		response.sendRedirect("/exception.jsp");
	}
	int corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();
	Integer eventId = new Integer(request.getParameter("eventId"));
	String url = "event/EventEdit.jsp?eventId="+eventId.toString();
	if(Util.empty(request.getParameter("vehicle"))) {
	    url += "&vehicle="+request.getParameter("vehicle");
	}
	session.setAttribute("url",url);
	EventDataType eventDataType = sys.getEventReportFacade().findEventByPrimaryKey(eventId,sys.getUserSessionData());
	if(eventDataType != null) {
		form = new EventForm(eventDataType,sys.getUserSessionData());
		session.setAttribute("EventForm",form);
	    driverId = new Integer(form.getDriverid());
	    DriverData driver = sys.getDriverReportFacade().findDriverByPrimaryKey(driverId,sys.getUserSessionData());
	//	DriverData driver = null;
	//	if(session.getAttribute("EventForm") != null) {
	//	    eventForm = (EventForm)session.getAttribute("EventForm");
	//	    driverId = new Integer(eventForm.getDriverid());
	//	    driver = sys.getDriverReportFacade().findDriverByPrimaryKey(driverId,sys.getUserSessionData());
	//	}else {
	//	    driverId = new Integer(request.getParameter("id"));
	//	    driver = sys.getDriverReportFacade().findDriverByPrimaryKey(driverId,sys.getUserSessionData());
	//	    eventForm = new EventForm(driver);
	//		session.setAttribute("EventForm",eventForm);
	//	}	    
	
	//	if(request.getParameter("vehicle") != null) {
	//	    eventForm.setVehicleId(request.getParameter("vehicle"));
	//	}
	
		context=request.getContextPath();
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
		
		messages=sys.getText();
		session.setAttribute("target","EventSelectDriverList");
	
		OptionItem emptyObject = new OptionItem(messages.getText("generic.messages.select"),"");
		OptionItem noDataObject = new OptionItem(messages.getText("generic.messages.nodata"),"generic.messages.nodata");
		
		Collection years = new LinkedList();
		years.add(emptyObject);
		years.add(noDataObject);
		years.add(new OptionItem(messages.getText("generic.year.beforeninety"),"generic.year.beforeninety"));
		Calendar instance = Calendar.getInstance();
		for(int i = 1990; i <= instance.get(Calendar.YEAR); i++) {
			years.add(new OptionItem(String.valueOf(i),String.valueOf(i)));
		}
		session.setAttribute("years",years);
		
		Collection generic = new LinkedList();
		generic.add(emptyObject);
		generic.add(noDataObject);
		generic.add(new OptionItem(messages.getText("generic.messages.yes"),"generic.messages.yes"));
		generic.add(new OptionItem(messages.getText("generic.messages.no"),"generic.messages.no"));
		session.setAttribute("generic",generic);
		
		Collection noandnodata = new LinkedList();
		noandnodata.add(emptyObject);
		noandnodata.add(noDataObject);
		noandnodata.add(new OptionItem(messages.getText("generic.messages.no"),"generic.messages.no"));
		session.setAttribute("noandnodata",noandnodata);
	
		session.setAttribute("vehicleType",new DataTypeUtil(sys).getVehicleType(true,false,true));
	
		Collection zonesDT = new LinkedList();
		Collection zones = sys.getDataTypeReportFacade().findAll(DataTypeConstants.ZONE,sys.getUserSessionData());
		Iterator zonesIt = zones.iterator();
		zonesDT.add(emptyObject);				
		while(zonesIt.hasNext()) {
		    ZoneDataType zone = (ZoneDataType)zonesIt.next();
		    zonesDT.add(new OptionItem(messages.getText(zone.getResourceBundleId()),zone.getResourceBundleId()));
		}
		session.setAttribute("zones",zonesDT);
	
		DataTypeReportFacade dataTypeReport = sys.getDataTypeReportFacade();
		Collection factorsType = new LinkedList();
		Iterator iterF = dataTypeReport.findAll(DataTypeConstants.ACCIDENT_FACTORS,sys.getUserSessionData()).iterator();
		factorsType.add(emptyObject);				
		while(iterF.hasNext()) {
			String value = ((AccidentFactorsDataType)iterF.next()).getResourceBundleId();
			factorsType.add(new OptionItem(messages.getText(value),value));				
		}
		session.setAttribute("factorType",factorsType);
	
		Collection behaType = new LinkedList();
		Iterator iterV = dataTypeReport.findAll(DataTypeConstants.VEHICLE_BEHAVIOUR,sys.getUserSessionData()).iterator();
		behaType.add(emptyObject);				
		while(iterV.hasNext()) {
			String value = ((VehicleBehaviourDataType)iterV.next()).getResourceBundleId();
			behaType.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
		}
		request.setAttribute("behaviourType",behaType);
		
		Collection climateDT = new LinkedList();
		Collection climates = sys.getDataTypeReportFacade().findAll(DataTypeConstants.CLIMATE,sys.getUserSessionData());
		Iterator climateIt = climates.iterator();
		climateDT.add(emptyObject);				
		climateDT.add(noDataObject);				
		while(climateIt.hasNext()) {
		    ClimateDataType climate = (ClimateDataType)climateIt.next();
			if(!climate.getResourceBundleId().equals("generic.messages.nodata")) {
			    climateDT.add(new OptionItem(messages.getText(climate.getResourceBundleId()),climate.getResourceBundleId()));
			}
		}
		request.setAttribute("climates",climateDT);
	
		Collection lightingDT = new LinkedList();
		Collection lightings = sys.getDataTypeReportFacade().findAll(DataTypeConstants.LIGHTING,sys.getUserSessionData());
		Iterator lightingIt = lightings.iterator();
		lightingDT.add(emptyObject);				
		lightingDT.add(noDataObject);				
		while(lightingIt.hasNext()) {
		    LightingDataType lighting = (LightingDataType)lightingIt.next();
			if(!lighting.getResourceBundleId().equals("generic.messages.nodata")) {
			    lightingDT.add(new OptionItem(messages.getText(lighting.getResourceBundleId()),lighting.getResourceBundleId()));
			}
		}
		session.setAttribute("lightings",lightingDT);
	
		Collection wayDT = new LinkedList();
		Collection ways = sys.getDataTypeReportFacade().findAll(DataTypeConstants.WAY,sys.getUserSessionData());
		Iterator wayIt = ways.iterator();
		wayDT.add(emptyObject);				
		wayDT.add(noDataObject);				
		while(wayIt.hasNext()) {
		    WayDataType way = (WayDataType)wayIt.next();
			if(!way.getResourceBundleId().equals("generic.messages.nodata")) {
			    wayDT.add(new OptionItem(messages.getText(way.getResourceBundleId()),way.getResourceBundleId()));
			}
		}
		session.setAttribute("ways",wayDT);
	
		Collection directionDT = new LinkedList();
		Collection directions = sys.getDataTypeReportFacade().findAll(DataTypeConstants.CIRCULATIONDIRECTION,sys.getUserSessionData());
		Iterator directionIt = directions.iterator();
		directionDT.add(emptyObject);				
		directionDT.add(noDataObject);				
		while(directionIt.hasNext()) {
		    CirculationDirectionDataType direction = (CirculationDirectionDataType)directionIt.next();
			if(!direction.getResourceBundleId().equals("generic.messages.nodata")) {
			    directionDT.add(new OptionItem(messages.getText(direction.getResourceBundleId()),direction.getResourceBundleId()));
			}
		}
		session.setAttribute("directions",directionDT);
	
		Collection controlDT = new LinkedList();
		Collection controls = sys.getDataTypeReportFacade().findAll(DataTypeConstants.TRAFFIC_CONTROLS,sys.getUserSessionData());
		Iterator controlIt = controls.iterator();
		controlDT.add(emptyObject);				
		controlDT.add(noDataObject);				
		while(controlIt.hasNext()) {
		    TrafficControlDataType control = (TrafficControlDataType)controlIt.next();
			if(!control.getResourceBundleId().equals("generic.messages.nodata")) {
			    controlDT.add(new OptionItem(messages.getText(control.getResourceBundleId()),control.getResourceBundleId()));
			}
		}
		session.setAttribute("controls",controlDT);
	
		Collection pavementDT = new LinkedList();
		Collection pavements = sys.getDataTypeReportFacade().findAll(DataTypeConstants.PAVEMENT,sys.getUserSessionData());
		Iterator pavementIt = pavements.iterator();
		pavementDT.add(emptyObject);				
		pavementDT.add(noDataObject);				
		while(pavementIt.hasNext()) {
		    PavementDataType pavement = (PavementDataType)pavementIt.next();
			if(!pavement.getResourceBundleId().equals("generic.messages.nodata")) {
			    pavementDT.add(new OptionItem(messages.getText(pavement.getResourceBundleId()),pavement.getResourceBundleId()));
			}
		}
		session.setAttribute("pavements",pavementDT);
	
		Collection conditionDT = new LinkedList();
		Collection conditions = sys.getDataTypeReportFacade().findAll(DataTypeConstants.CONDITIONROUT,sys.getUserSessionData());
		Iterator conditionIt = conditions.iterator();
		conditionDT.add(emptyObject);				
		conditionDT.add(noDataObject);				
		while(conditionIt.hasNext()) {
		    ConditionRoutDataType condition = (ConditionRoutDataType)conditionIt.next();
			if(!condition.getResourceBundleId().equals("generic.messages.nodata")) {
			    conditionDT.add(new OptionItem(messages.getText(condition.getResourceBundleId()),condition.getResourceBundleId()));
			}    
		}
		session.setAttribute("conditions",conditionDT);
	
		Collection pedestrianActions = new LinkedList();
		pedestrianActions.add(emptyObject);				
		pedestrianActions.add(new OptionItem(messages.getText("driver.pedestrian.cross.intersection"),"driver.pedestrian.cross.intersection"));				
		pedestrianActions.add(new OptionItem(messages.getText("driver.pedestrian.cross.otherplace"),"driver.pedestrian.cross.otherplace"));				
		pedestrianActions.add(new OptionItem(messages.getText("driver.pedestrian.cross.out"),"driver.pedestrian.out"));				
		pedestrianActions.add(new OptionItem(messages.getText("driver.pedestrian.cross.other"),"driver.pedestrian.other"));				
		session.setAttribute("pedestrianActions",pedestrianActions);
	
		Collection driverState = new LinkedList();
		driverState.add(emptyObject);				
		driverState.add(new OptionItem(messages.getText("datatype.driverstate.safe"),"datatype.driverstate.safe"));				
		driverState.add(new OptionItem(messages.getText("datatype.driverstate.injured"),"datatype.driverstate.injured"));				
		driverState.add(new OptionItem(messages.getText("datatype.driverstate.dead"),"datatype.driverstate.dead"));				
		request.setAttribute("driverState",driverState);
		
		Collection trade = new LinkedList();
		trade.add(emptyObject);                
		Iterator iter = dataTypeReport.findAll(DataTypeConstants.TYPETRADE,sys.getUserSessionData()).iterator();
		while(iter.hasNext()) {
			String value = ((TypeTradeDataType)iter.next()).getResourceBundleId();
			trade.add(new OptionItem(messages.getText(value),value));				
		}
		request.setAttribute("trades",trade);
	
		Collection hitrunOthers = new LinkedList();
		hitrunOthers.add(emptyObject);                
		hitrunOthers.add(noDataObject);                
		hitrunOthers.add(new OptionItem(messages.getText("driver.hitrun.no"),"driver.hitrun.no"));
		hitrunOthers.add(new OptionItem(messages.getText("driver.hitrun.yes.known"),"driver.hitrun.yes.known"));
		hitrunOthers.add(new OptionItem(messages.getText("driver.hitrun.yes.unknown"),"driver.hitrun.yes.unknown"));
		request.setAttribute("hitrunOther",hitrunOthers);
	
		Collection cellular = new LinkedList();
		cellular.add(emptyObject);				
		cellular.add(noDataObject);
		cellular.add(new OptionItem(messages.getText("driver.cellular.no"),"driver.cellular.no"));
		cellular.add(new OptionItem(messages.getText("driver.cellular.yes.freehand"),"driver.cellular.yes.freehand"));
		cellular.add(new OptionItem(messages.getText("driver.cellular.yes.normal"),"driver.cellular.yes.normal"));
		request.setAttribute("cellulars",cellular);
		
		Collection speedunits = new LinkedList();
		speedunits.add(emptyObject);				
		speedunits.add(new OptionItem(messages.getText("driver.speedunits.metres"),"1"));
		speedunits.add(new OptionItem(messages.getText("driver.speedunits.milles"),"2"));
		request.setAttribute("speedunits",speedunits);

	
%>

<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
</script>

<script language="javascript" src='./menu/js/refactors.js' type='text/javascript' >
</script>

<script language="javascript" type='text/javascript' >
	
	function onSelectEvent(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayIdEvent");
  		var input_month = document.getElementById("monthIdEvent");
  		var input_year = document.getElementById("yearIdEvent");
  		var date3 = new Date(time);
  		input_day.value = date3.print("%d");
  		input_month.value = date3.print("%m");
  		input_year.value= parseInt(date3.print("%Y"),10);
  	}

	function onSelectLote(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
			// var input_day = document.getElementById("dayIdLote");
			var input_month = document.getElementById("monthIdLote");
			var input_year = document.getElementById("yearIdLote");
			var date3 = new Date(time);
			//input_day.value = date3.print("%d");
			input_month.value = date3.print("%m");
			input_year.value= parseInt(date3.print("%Y"),10);
		}

	function changeVehicleCount(){
		value = document.forms[2].vehiclecount.value;
		for(i = 1; i <= value; i++) {
			document.getElementById('vehicle'+new String(i)).style.display = 'block';
		}
		for(i = new Number(value)+1; i < 6; i++) {
			document.getElementById('vehicle'+new String(i)).style.display = 'none';
		}		
		refactor.refactorResto(300);		
	}
	function changePedestrianCount(formName){
		value = document.forms[formName].pedestriancount.value;
		for(i = 1; i <= value; i++) {
			document.getElementById('pedestrian'+new String(i)).style.display = 'block';
		}
		for(i = new Number(value)+1; i < 6; i++) {
			document.getElementById('pedestrian'+new String(i)).style.display = 'none';
		}
		refactor.refactorResto(300);		
	}
	function nodata(form) {
		form.elements['hour'].disabled = form.elements['nodatahour'].checked;
		form.elements['minute'].disabled = form.elements['nodatahour'].checked;
	}
	function setnodata(form) {
		form.elements['way'].options.selectedIndex = 1;
		form.elements['direction'].options.selectedIndex = 1;
		form.elements['control'].options.selectedIndex = 1;
		form.elements['climate'].options.selectedIndex = 1;
		form.elements['pavement'].options.selectedIndex = 1;
		form.elements['condition'].options.selectedIndex = 1;
		form.elements['lighting'].options.selectedIndex = 1;
	}
</script>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>
	
	<html:form action="/Eventupdate" >
		<body bgcolor="#FFFFFF">
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  				<tr class="guide1">
  					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.messages.addevent")%>' />
			  	</tr>
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
				<tr>
					<td width="292" valign="top">
                    	<table width="100%">
                    		<tr>
                     			<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.driverdata")%>' />
		        					<table width='100%' border="0" cellpadding="0" cellspacing="0">
		            					<tr class="linetwo">
	              							<td align="left" valign="middle"><strong><%=messages.getText("generic.event.driver.name")%></strong></td>
	    									<td align="left" valign="middle" >			
                       							<html:hidden property="driverid"/>
                       							<span class="linetwo"><%=driver.getFirstName()+" "+driver.getLastName()%></span>
											</td>
										</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            					<tr class="linetwo">
	              							<td align="left" valign="middle"><strong><%=messages.getText("generic.event.driver.birthdate")%></strong></td>
	                            			<td>
                       							<span class="linetwo"><%=Util.formatDate(driver.getBirthDate())%></span>
							    			</td>
	                         			</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            					<tr class="linetwo">
	              							<td align="left" valign="middle"><strong><%=messages.getText("generic.event.driver.startdate")%></strong></td>
	                            			<td>
                       							<span class="linetwo"><%=Util.formatDate(driver.getInclusionDate())%></span>
							    			</td>
	                         			</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            					<tr class="linetwo">
	              							<td align="left" valign="middle"><strong><%=messages.getText("generic.event.driver.licensecategory")%></strong></td>
	                            			<td>
                       							<span class="linetwo"><%=driver.getLicenseCategory()%></span>
							    			</td>
	                         			</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            					<tr class="linetwo">
	              							<td align="left" valign="middle"><strong><%=messages.getText("generic.event.driver.licenseexpiry")%></strong></td>
	                            			<td>
                       							<span class="linetwo"><%=Util.formatDate(driver.getLicenseExpiry())%></span>
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
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
                       					<tr>
                         					<td valign="top"> 
<%			String divgeo = "../util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=EventForm&consult=false";
			divgeo += "&city=both";
			//switch(corporationId) {
			 //   case CorporationData.MONSANTO:
			 //   	divgeo += "&city=city";
		//	}	
%> 			             						<jsp:include page='<%=divgeo%>' />
											</td>
                       					</tr>
	                     			</table>					  
								</td>
							</tr>		
						</table>
					</td>
	                <td width="16">&nbsp;</td>
                	<td width="292" valign="top">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
                    		<jsp:include page='<%="../util/divorg.jsp?form=EventForm&consult=false" %>'/>
                    		
<%		if(sys.getCorporationId() == CorporationData.CIMSA) { 

										// Muestro Numero de Folio y Numero de SAP
%>
                    		<tr  class="linetwo">
        						<td align="left" colspan="3">
    								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
    								  	<tr class="guide1">
											<td width="19" height="30" ><img src="../util/imgs/left.jpg" width="19" height="30"></td>
   		  		  							<td height="30" class="guide1"><div align="left">&nbsp;</div></td>
											<td width="19" height="30" ><img src="../util/imgs/right.jpg" width="19" height="30"></td>
  										</tr>  					
  										<tr>
    										<td class="leftLine">&nbsp;</td>
				       						<td height="10" colspan="1"><img src="x" width="1" height="1"></td>
    										<td class="rightLine">&nbsp;</td>
  										</tr>  					  					
	 									<tr>
    										<td class="leftLine">&nbsp;</td>
    										<td>
												<table width="100%" cellpadding="0" cellspacing="0">
													<tr class="linetwo">
			    										<th align="left"><%=messages.getText("generic.events.folioNumber")%></th>
														<td align="right"><html:text property="folioNumber" size="25" styleClass="input"/></td>
													</tr>
													<tr class="linetwo">
			    										<th align="left"><%=messages.getText("generic.events.sapNumber")%></th>
														<td align="right"><html:text property="sapNumber" size="25" styleClass="input"/></td>
													</tr>
												</table>
											</td>
											<td class="rightLine">&nbsp;</td>
										</tr>
					  					<tr>
					            	      	<td height="11" valign="top"><img src="../util/imgs/left_base.gif" width="19" height="11"></td>
					  					  	<td class="centerBase"></td>
					  				  		<td valign="top"><img src="../util/imgs/right_base.gif" width="19" height="11"></td>
									 	</tr> 
									</table>
								</td>
							</tr>
<% 		} %>
                    		
	                     </table>
    	             </td>
				</tr>
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
				<tr>
					<td colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.vehicledata")%>' />
<%					if(Util.empty(form.getVehicleId())) { 
%>						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="270" class="linetwo">
									<table width="100%">
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.plate")%> <span class="required">*</span></th>
				         					<td align="right"><html:text property="vehicleplate" size="25" styleClass="input"/></td>
			         					</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.vehicle.vehicledata.trade")%><span class="required">*</span> </th>
				         					<td align="right">
                								<html:select property="vehicletrade" styleClass="input" style="width:155;">
                  									<html:options collection="trades" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
				         					</td>
										</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
											<th width="100%" class="linetwo" colspan="2" align="left">
												<html:checkbox property="vehiclecompanycar"><%=messages.getText("generic.event.vehicle.companycar")%></html:checkbox>
											</th>
										</tr>
										
										
			         					
									</table>
								</td>
								<td width="16"></td>
								<td width="270">
									<table width="100%">
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.model")%> <span class="required">*</span></th>
				         					<td align="right"><html:text property="vehiclemodel" size="25" styleClass="input"/></td>
										</tr>
		      							<tr  class="space">
		        							<th align="right">&nbsp;</th>
		        							<td align="right">&nbsp;</td>
		      							</tr>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.vehicle.vehicledata.year")%> <span class="required">*</span></th>
				         					<td align="right">
                								<html:select property="vehicleyear" styleClass="input" >
                  									<html:options collection="years" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
                							</td>
										</tr>		
										<% if (sys.getCorporationId() == CorporationData.PEPSICO) { %>
										<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo"> 
										   			<%=messages.getText("generic.event.eventdata.economicnumber")%>
										   	</th>
				         					<td align="right"><html:text property="vehicleeconomicnumber" size="25" styleClass="input"/></td>
			         					</tr>
			         					<% } %>		  
									</table>
								</td>
							</tr>
						</table>
<%					}else {
    					VehicleData vehicle = sys.getVehicleReportFacade().findVehicleById(new Integer(form.getVehicleId()),sys.getUserSessionData());
%>						<html:hidden property="vehicleId" value='<%=request.getParameter("vehicle")%>' />
						<table>
							<tr>
								<td width="270" class="linetwo">
									<table width="100%">
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.plate")+":"%> </th>
				         					<td align="right" class="linetwo"><%=vehicle.getMatricula()%></td>
			         					</tr>
		      							<tr  class="space">
		        							<th align="right">&nbsp;</th>
		        							<td align="right">&nbsp;</td>
		      							</tr>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.vehicle.vehicledata.trade")+":"%> </th>
				         					<td align="right" class="linetwo"><%=messages.getText(vehicle.getTrade().getResourceBundleId())%></td>
										</tr>
									</table>
								</td>
								<td width="16"></td>
								<td width="270">
									<table width="100%">
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.model")+":"%> </th>
				         					<td align="right" class="linetwo"><%=vehicle.getModel()%></td>
										</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.vehicle.vehicledata.year")+":"%> </th>
				         					<td align="right" class="linetwo"><%=vehicle.getYear()%></td>
										</tr>				  
									</table>
								</td>
							</tr>
						</table>    
<%					}
%>						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
	  			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
				<tr>
					<td width="270">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.eventdata")%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
	       						<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.date")%><span class="required">*</span></th>
	       						<td align="right" class="linetwo">
									<html:text property="date" style="width:20;" styleClass="input" styleId="dayIdEvent" maxlength="2"/>/
		            				<html:text property="month" style="width:20;" styleClass="input" styleId="monthIdEvent" maxlength="2"/>/
		            				<html:text property="year" style="width:40;" styleClass="input" styleId="yearIdEvent" maxlength="4"/>
		            				<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_buttonEvent" style="cursor: pointer; border: 1px solid red;"
     								onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>
			          			</td>
			          			<script type="text/javascript">
									Calendar.setup({
        								inputArea     :    "yearIdEvent",
        								ifFormat       :    "%d/%m/%Y",
        								showsTime      :    true,
        								timeFormat     :    "24",
        								button         :    "calendar_buttonEvent",
        								step           :    1,
        								onSelect       :    onSelectEvent,
        								singleClick    :    true
    								});
								</script>
			          		</tr>
		      				<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr>
			        			<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.hour")%><span class="required">*</span></th>
			        			<td align="right" class="linetwo">
<%			if(sys.getUserSessionData().getFilter().getCorporationId().intValue() != communication.administration.corporation.tables.CorporationData.MONSANTO) {
%>									<html:checkbox property="nodatahour" onchange="javascript:nodata(document.forms['EventForm']);"><b><%=messages.getText("generic.messages.nodata")%></b></html:checkbox>		            				
<%			}else {
%>									<html:hidden property="nodatahour" value="off"/>			       
<%			}
%>		            				<html:text property="hour" style="width:20;" styleClass="input" maxlength="2"/>&nbsp;:&nbsp;
		            				<html:text property="minute" style="width:20;" styleClass="input" maxlength="2"/>
			          			</td>
			          		</tr>
		      				<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr>
			        			<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.working")%><span class="required">*</span></th>
			        			<td align="right" class="linetwo">
									<html:select property="working" styleClass="input" >
                						<html:options collection="generic" property="value" labelProperty="label" styleClass="input"/>
              						</html:select>
			          			</td>
			          		</tr>
			          		
			          		<% if (sys.getUserSessionData().getFilter().getCorporationId().intValue() == CorporationData.ALLERGAN){ 
										// ALLERGAN, tiene un campo de uso interno de la compania, llamado LOTE: Mes/Año
								%>
							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr>
								<th align="left" class="linetwo"><%=messages.getText("generic.event.eventdata.lote")%>&nbsp;<%=messages.getText("generic.event.eventdata.loteformat")%></th>
				      				<td align="right" class="linetwo">
				     					<html:text property="loteMonth" style="width:20;" styleClass="input" styleId="monthIdLote" maxlength="2"/>/
				     					<html:text property="loteYear" style="width:40;" styleClass="input" styleId="yearIdLote" maxlength="4"/>
				     					<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_buttonLote" style="cursor: pointer; border: 1px solid red;"
										onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>
				       				</td>
				       				<script type="text/javascript">
									Calendar.setup({
				  										inputArea     :    "yearIdLote",
				  										ifFormat       :    "%m/%Y",
				  										showsTime      :    true,
				  										timeFormat     :    "24",
				  										button         :    "calendar_buttonLote",
				  										step           :    1,
				  										onSelect       :    onSelectLote,
				  										singleClick    :    true
												});
									</script>					    			
							</tr>
							<%	
								}
							%>
			          		<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			          		
			          		<%		if (corporationId == CorporationData.CIMSA) {
%>
								<tr>
			        				<th align="left" class="linetwo"><%=messages.getText("generic.event.finedata.points")%></th>
										<td class="linetwo"  align="right">
											<html:text property="points" size="10" styleClass="input"/>
    									</td>
    								</tr>	      				
		      						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>

<% 		} %>	
			          		
		      				
							<tr>
			        			<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.zone")%><span class="required">*</span></th>
			        			<td align="right" class="linetwo">
				      				<html:select property="zone" styleClass="input" >
				      					<html:options collection="zones" property="value" labelProperty="label" styleClass="input"/>
					  				</html:select>   
			          			</td>
			          		</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
					<td width="16">&nbsp;</td>
					
					<td width="270" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.consequences")%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr class="texts" >
								<td>
									<html:checkbox property="damage" ><%=messages.getText("datatype.consequence.damage")%></html:checkbox>
    							</td>
    						</tr>
							<tr class="texts" >
    							<td>
			    					<html:checkbox property="injured" ><%=messages.getText("datatype.consequence.injured")%></html:checkbox>
    							</td>
    						</tr>
							<tr class="texts" >
    							<td>
			    					<html:checkbox property="fatalities" ><%=messages.getText("datatype.consequence.fatalities")%></html:checkbox>
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
					<td width="100%" colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.eventdata.story")+"*"%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="270">
									<html:textarea property="description" cols="70" />
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
					<td valign="top" colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.eventdata.colisionclasification")+"*"%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
<%				Collection collisions = dataTypeReport.findAll(DataTypeConstants.COLLISION_CLASIFICATION,sys.getUserSessionData());
				if(collisions != null && collisions.size() > 0) {
				    Iterator it = collisions.iterator();
				    for(int i = 0; i < 3; i++) {	
%>							<tr>
<%						for(int j = 0; j < 2; j++) {	
							CollisionClasificationDataType accident = (CollisionClasificationDataType)it.next();
%>								<td width="30%" class="linetwo">
									<html:radio property="collision" value='<%=accident.getResourceBundleId()%>'><%=messages.getText(accident.getResourceBundleId())%></html:radio>
								</td>
<%						}
%>							</tr>
<%					}
				}
%>						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>	
	  			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
				<tr>
					<td width="292" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.eventdata.clasification")%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
<%				Collection accidents = dataTypeReport.findAll(DataTypeConstants.ACCIDENT_CLASIFICATION,sys.getUserSessionData());
				if(accidents != null && accidents.size() > 0) {
				    Iterator it = accidents.iterator();
				    while(it.hasNext()) {
				        AccidentClasificationDataType accident = (AccidentClasificationDataType)it.next();
%>							<tr>
								<td width="100%" class="linetwo">
									<html:radio property="clasification" value='<%=accident.getResourceBundleId()%>'><%=messages.getText(accident.getResourceBundleId())%></html:radio>
								</td>
							</tr>
<%					}
				}
%>						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
					<td width="16"></td>
					<td width="292" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.eventdata.incidents")%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
<%				Collection incidents = dataTypeReport.findAll(DataTypeConstants.INCIDENT_CLASIFICATION,sys.getUserSessionData());
				if(incidents != null && incidents.size() > 0) {
				    Iterator it = incidents.iterator();
				    while(it.hasNext()) {
				        IncidentClasificationDataType incident = (IncidentClasificationDataType)it.next();
%>							<tr>
								<td width="100%" class="linetwo">
									<html:radio property="clasification" value='<%=incident.getResourceBundleId()%>'><%=messages.getText(incident.getResourceBundleId())%></html:radio>
								</td>
							</tr>
<%					}
				}
%>						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
          		<tr>
           			<td colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.companycar")%>' />
						<table width="100%">
							<tr  class="linetwo">
								<th align="left"> <%=messages.getText("generic.event.eventdata.handle")%><span class="required">*</span></th>
								<td align="right">
 									<html:select property="handle" styleClass="input" style="width:180;" >
										<html:options collection="behaviourType" property="value" labelProperty="label" styleClass="input"/>
									</html:select>   
								</td>
								<td width="16"></td>
								<th align="left"> <%=messages.getText("generic.event.eventdata.hitrun")%></th>
        						<td align="right">
									<html:select property="hitrun" styleClass="input" >
									<% if (corporationId == CorporationData.CIMSA){ %>
										<html:options collection="noandnodata" property="value" labelProperty="label" styleClass="input"/>
									<% }else{ %>
                						<html:options collection="generic" property="value" labelProperty="label" styleClass="input"/>
                					<% } %>
              						</html:select>
								</td>
							</tr>
							<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr  class="linetwo">
								<th align="left"> <%=messages.getText("generic.event.eventdata.principalfactor")%><span class="required">*</span></th>
								<td align="right">
 									<html:select property="principalFactor" styleClass="input" style="width:180;" >
										<html:options collection="factorType" property="value" labelProperty="label" styleClass="input"/>
									</html:select>   
								</td>
							</tr>
							<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr  class="linetwo">
								<td colspan="5" width="100%">
									<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.event.eventdata.factors")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="49%" class="lineone">
												<html:checkbox property="factorovertaking"><%=messages.getText("datatype.accidentfactors.overtaking")%></html:checkbox>
											</td>
											<td width="2%" class="lineone">&nbsp;</td>
											<td width="49%" class="lineone">
												<html:checkbox property="factoralcohol"><%=messages.getText("datatype.accidentfactors.alcohol")%></html:checkbox>
											</td>
										</tr>
										<tr>
											<td width="49%" class="lineone">
												<html:checkbox property="factorchangelane"><%=messages.getText("datatype.accidentfactors.changelane")%></html:checkbox>
											</td>
											<td width="2%" class="lineone">&nbsp;</td>
											<td width="49%" class="lineone">
												<html:checkbox property="factorsleep"><%=messages.getText("datatype.accidentfactors.sleep")%></html:checkbox>
											</td>
										</tr>
										<tr>
											<td width="49%" class="lineone">
												<html:checkbox property="factorturn"><%=messages.getText("datatype.accidentfactors.turn")%></html:checkbox>
											</td>
											<td width="2%" class="lineone">&nbsp;</td>
											<td width="49%" class="lineone">
												<html:checkbox property="factortooclose"><%=messages.getText("datatype.accidentfactors.tooclose")%></html:checkbox>
											</td>
										</tr>
										<tr>
											<td width="49%" class="lineone">
												<html:checkbox property="factorother"><%=messages.getText("datatype.accidentfactors.others")%></html:checkbox>
											</td>
											<td width="2%" class="lineone">&nbsp;</td>
											<td width="49%" class="lineone">
												<html:checkbox property="factorlight"><%=messages.getText("datatype.accidentfactors.lights")%></html:checkbox>
											</td>
										</tr>
										<tr>
											<td width="49%" class="lineone">
												<html:checkbox property="factorhighspeed"><%=messages.getText("datatype.accidentfactors.speed")%></html:checkbox>
											</td>
											<td width="2%" class="lineone">&nbsp;</td>
											<td width="49%" class="lineone">
												<html:checkbox property="factorsigns"><%=messages.getText("datatype.accidentfactors.ignoresigns")%></html:checkbox>
											</td>
										</tr>
										<tr>
											<td width="49%" class="lineone">
												<html:checkbox property="factorstop"><%=messages.getText("datatype.accidentfactors.stop")%></html:checkbox>
											</td>
											<td width="2%" class="lineone">&nbsp;</td>
											<td width="49%" class="lineone">
												<html:checkbox property="factordistraction"><%=messages.getText("datatype.accidentfactors.distraction")%></html:checkbox>
											</td>
										</tr>
									</table>
									<jsp:include  page='<%="../component/utilitybox2bottom.jsp" %>'/>
								</td>
							</tr>	
							<tr  class="linetwo">
								<td colspan="5" width="100%">
									<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.event.eventdata.collisionangle")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="49%" class="lineone">
												<html:radio property="collisionangle" value="datatype.collisionangle.hitrear"><%=messages.getText("datatype.collisionangle.hitrear")%></html:radio>
											</td>
											<td width="2%" class="lineone">&nbsp;</td>
											<td width="49%" class="lineone">
												<html:radio property="collisionangle" value="datatype.collisionangle.otherpartyhitrear"><%=messages.getText("datatype.collisionangle.otherpartyhitrear")%></html:radio>
											</td>
										</tr>
										<tr>
											<td width="49%" class="lineone">
												<html:radio property="collisionangle" value="datatype.collisionangle.rectangle"><%=messages.getText("datatype.collisionangle.rectangle")%></html:radio>
											</td>
											<td width="2%" class="lineone">&nbsp;</td>
											<td width="49%" class="lineone">
												<html:radio property="collisionangle" value="datatype.collisionangle.leftturn"><%=messages.getText("datatype.collisionangle.leftturn")%></html:radio>
											</td>
										</tr>
										<tr>
											<td width="49%" class="lineone">
												<html:radio property="collisionangle" value="datatype.collisionangle.front"><%=messages.getText("datatype.collisionangle.front")%></html:radio>
											</td>
											<td width="2%" class="lineone">&nbsp;</td>
											<td width="49%" class="lineone">
												<html:radio property="collisionangle" value="datatype.collisionangle.lateral"><%=messages.getText("datatype.collisionangle.lateral")%></html:radio>
											</td>
										</tr>
										<tr>
											<td width="49%" class="lineone">
												<html:radio property="collisionangle" value="datatype.collisionangle.rightturn"><%=messages.getText("datatype.collisionangle.rightturn")%></html:radio>
											</td>
											<td width="2%" class="lineone">&nbsp;</td>
											<td width="49%" class="lineone">
												<html:radio property="collisionangle" value="datatype.collisionangle.other"><%=messages.getText("datatype.collisionangle.other")%></html:radio>
											</td>
										</tr>
									</table>
									<jsp:include  page='<%="../component/utilitybox2bottom.jsp" %>'/>
								</td>
							</tr>
							<tr>
							   	<td width="270" colspan="2">
									<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.event.eventdata.speed")%>' />
										<table width="100%" border="0" cellpadding="0" cellspacing="0">
											<tr>
											   	<th align="left" class="lineone"> 
											   		<%=messages.getText("generic.event.eventdata.maxspeed")%>
					         					</th>
					         					<td align="right" class="lineone">
					         						<html:text property="maxspeed" size="10" styleClass="input"/>
				         						</td>
											</tr>
											<tr>
											   	<th align="left" class="lineone"> 
											   		<%=messages.getText("generic.event.eventdata.collisionspeed")%>
					         					</th>
					         					<td align="right" class="lineone">
													<html:text property="collisionspeed" size="10" styleClass="input"/>
				         						</td>
											</tr>
											<tr>
											   	<th align="left" class="lineone"> 
											   		<%=messages.getText("generic.event.eventdata.vehiclespeed")%>
					         					</th>
					         					<td align="right" class="lineone">
					         						<html:text property="vehiclespeed" size="10" styleClass="input"/>
				         						</td>
											</tr>
											<tr>
											   	<th align="left" class="lineone"> 
											   		<%=messages.getText("generic.event.eventdata.reportedspeed")%>
					         					</th>
					         					<td align="right" class="lineone">
					         						<html:text property="reportedspeed" size="10" styleClass="input"/>
				         						</td>
											</tr>
											<tr>
											   	<th align="left" class="lineone"> 
											   		<%=messages.getText("generic.event.eventdata.speedunit")%>
											   	</th>
					         					<td align="right" class="lineone">
	                								<html:select property="speedunit" styleClass="input" >
       				  									<html:options collection="speedunits" property="value" labelProperty="label" styleClass="input"/>
       												</html:select>
				         						</td>
				         					</tr>
										</table>
									<jsp:include  page="../component/utilitybox2bottom.jsp" />
								</td>
							   	<td width="16">&nbsp;</td>
								<td width="260" colspan="2">
									<table>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo" ><%=messages.getText("generic.event.eventdata.selfbelt")%></th>
				         					<td class="linetwo">
												<html:select property="selfbelt" styleClass="input" >
            			    						<html:options collection="generic" property="value" labelProperty="label" styleClass="input"/>
              									</html:select>
              								</td>
			         					</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo"> 
										   		<%=messages.getText("generic.event.eventdata.alcohollevel")%>
										   	</th>
				         					<td class="linetwo">
				         						<html:text property="alcohol" size="10" styleClass="input" value="0" />
							         		</td>
										</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo" ><%=messages.getText("generic.event.eventdata.othervehicles")%></th>
				         					<td class="linetwo">
												<html:select property="othervehicles" styleClass="input" >
            			    						<html:options collection="generic" property="value" labelProperty="label" styleClass="input"/>
              									</html:select>
              								</td>
										</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo" ><%=messages.getText("generic.event.eventdata.drugs")%></th>
				         					<td class="linetwo">
												<html:select property="drugs" styleClass="input" >
            			    						<html:options collection="generic" property="value" labelProperty="label" styleClass="input"/>
              									</html:select>
              								</td>
										</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		      							<tr>
											<th align="left" class="linetwo"> 
												<%=messages.getText("generic.event.eventdata.cellular")%>
										   	</th>
				         					<td class="linetwo">
                								<html:select property="cellular" styleClass="input" >
             				  						<html:options collection="cellulars" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
							         		</td>
							         	</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		      							   <% if (sys.getCorporationId() == CorporationData.PEPSICO){  %>
											<!--  Distractores -->
											<tr>
											<th align="left" class="linetwo"> 
												<%=messages.getText("generic.event.eventdata.distractors")%>
										   	</th>
				         					<td class="linetwo">
												<html:select property="distractor" styleClass="input" >
			                						<html:options collection="distractorsOptions" property="value" labelProperty="label" styleClass="input"/>
			              						</html:select>
											</td>
											</tr>
											<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
									<% } %>
									</table>
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
					<td width="600" valign="top" colspan="3">
                    	<table width="100%">
                    		<tr>
                     			<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.othervehicles")%>' />
		        					<table width='100%' border="0" cellpadding="0" cellspacing="0">
		            					<tr class="linetwo">
	              							<td align="left" valign="middle"><strong><%=messages.getText("generic.event.vehicle.count")%></strong></td>
				         					<td align="left">
				         						<html:select property="vehiclecount" styleClass="input" onchange="javascript:changeVehicleCount('EventForm');">
<%									for(int i = 0; i < 6; i++) {
%>				         							<html:option value='<%=String.valueOf(i)%>'><%=String.valueOf(i)%></html:option>
<%									}
%>			         							</html:select>
											</td>
										</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%									for(int i = 1; i < 6; i++) {
%>										
										<tr>
											<td colspan="2">
												<div id=<%="vehicle"+String.valueOf(i)%> style='<%= (i <= Integer.parseInt(form.getVehiclecount())) ? "display:block" : "display:none" %>' >
												<table width="100%">
													<tr>
														<td width="100%">
															<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.event.vehicle")+" "+i%>' />
															<table width="100%" border="0" cellpadding="0" cellspacing="0">
																<tr  class="lineone">
			      													<th align="left"> <%=messages.getText("generic.event.eventdata.vehicletype")%><span class="required">*</span></th>
			      													<td align="right" colspan="2">
			 	     													<html:select property='<%="vehicleType"+i%>' styleClass="input" style="width:180;" >
			      															<html:options collection="vehicleType" property="value" labelProperty="label" styleClass="input"/>
				  														</html:select>   
				  													</td>
																</tr>
			      												<tr class="lineone"><th align="right">&nbsp;</th><td align="right" colspan="2">&nbsp;</td></tr>
																<tr  class="lineone">
			      													<th align="left"> <%=messages.getText("generic.event.eventdata.previous")%><span class="required">*</span></th>
			      													<td align="right" colspan="2">
			 	     													<html:select property='<%="behaviourType"+i%>' styleClass="input" style="width:180;" >
			      															<html:options collection="behaviourType" property="value" labelProperty="label" styleClass="input"/>
				  														</html:select>   
				  													</td>
																</tr>
																<tr class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
																<tr  class="lineone">
																	<th align="left"> <%=messages.getText("generic.event.eventdata.principalfactor")%><span class="required">*</span></th>
																	<td align="right">
									 									<html:select property='<%="principalFactor"+i%>' styleClass="input" style="width:180;" >
																			<html:options collection="factorType" property="value" labelProperty="label" styleClass="input"/>
																		</html:select>   
																	</td>
																</tr>
			      												<tr class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
																<tr  class="lineone">
																	<td colspan="3">
																		<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.event.eventdata.factors")%>' />
																		<table width="100%" border="0" cellpadding="0" cellspacing="0">
																			<tr>
																				<td width="49%" class="lineone">
																					<html:checkbox property='<%="factorovertaking"+i%>'><%=messages.getText("datatype.accidentfactors.overtaking")%></html:checkbox>
																				</td>
																				<td width="2%" class="lineone">&nbsp;</td>
																				<td width="49%" class="lineone">
																					<html:checkbox property='<%="factoralcohol"+i%>'><%=messages.getText("datatype.accidentfactors.alcohol")%></html:checkbox>
																				</td>
																			</tr>
																			<tr>
																				<td width="49%" class="lineone">
																					<html:checkbox property='<%="factorchangelane"+i%>'><%=messages.getText("datatype.accidentfactors.changelane")%></html:checkbox>
																				</td>
																				<td width="2%" class="lineone">&nbsp;</td>
																				<td width="49%" class="lineone">
																					<html:checkbox property='<%="factorsleep"+i%>'><%=messages.getText("datatype.accidentfactors.sleep")%></html:checkbox>
																				</td>
																			</tr>
																			<tr>
																				<td width="49%" class="lineone">
																					<html:checkbox property='<%="factorturn"+i%>'><%=messages.getText("datatype.accidentfactors.turn")%></html:checkbox>
																				</td>
																				<td width="2%" class="lineone">&nbsp;</td>
																				<td width="49%" class="lineone">
																					<html:checkbox property='<%="factortooclose"+i%>'><%=messages.getText("datatype.accidentfactors.tooclose")%></html:checkbox>
																				</td>
																			</tr>
																			<tr>
																				<td width="49%" class="lineone">
																					<html:checkbox property='<%="factorother"+i%>'><%=messages.getText("datatype.accidentfactors.others")%></html:checkbox>
																				</td>
																				<td width="2%" class="lineone">&nbsp;</td>
																				<td width="49%" class="lineone">
																					<html:checkbox property='<%="factorlight"+i%>'><%=messages.getText("datatype.accidentfactors.lights")%></html:checkbox>
																				</td>
																			</tr>
																			<tr>
																				<td width="49%" class="lineone">
																					<html:checkbox property='<%="factorhighspeed"+i%>'><%=messages.getText("datatype.accidentfactors.speed")%></html:checkbox>
																				</td>
																				<td width="2%" class="lineone">&nbsp;</td>
																				<td width="49%" class="lineone">
																					<html:checkbox property='<%="factorsigns"+i%>'><%=messages.getText("datatype.accidentfactors.ignoresigns")%></html:checkbox>
																				</td>
																			</tr>
																			<tr>
																				<td width="49%" class="lineone">
																					<html:checkbox property='<%="factorstop"+i%>'><%=messages.getText("datatype.accidentfactors.stop")%></html:checkbox>
																				</td>
																				<td width="2%" class="lineone">&nbsp;</td>
																				<td width="49%" class="lineone">
																					<html:checkbox property='<%="factordistraction"+i%>'><%=messages.getText("datatype.accidentfactors.distraction")%></html:checkbox>
																				</td>
																			</tr>
																		</table>
																		<jsp:include  page='<%="../component/utilitybox2bottom.jsp?" %>' />
																	</td>
																</tr>
			      												<tr class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
																<tr  class="lineone">
			      													<th align="left"> <%=messages.getText("generic.event.eventdata.hitrun")%></th>
			      													<td align="right">
 	     																<html:select property='<%="hitrun"+i%>' styleClass="input" style="width:180;" >
      																		<html:options collection="hitrunOther" property="value" labelProperty="label" styleClass="input"/>
	  																	</html:select>   
	  																</td>
																</tr>																						      												
			      												<tr class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
																<tr  class="lineone">
			      													<th align="left"> <%=messages.getText("driver.othervehicle.contact")%></th>
			      													<td align="right">
 	     																<html:select property='<%="contact"+i%>' styleClass="input" style="width:180;" >
      																		<html:options collection="generic" property="value" labelProperty="label" styleClass="input"/>
	  																	</html:select>   
	  																</td>
																</tr>
															</table>
															<jsp:include  page="../component/utilitybox2bottom.jsp" />
														</td>
													</tr>
									  				<tr>
														<jsp:include  page="../component/spaceline.jsp" />
												  	</tr>
												</table>	
												</div>
											</td>
										</tr>
<%									}
%>
	                     			</table>
									<jsp:include  page="../component/utilityboxbottom.jsp" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
				<tr>
					<td colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.environment")%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="270" valign="top">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr class="guideSearch">
											<td width="19" >
												<img src="/datacenter/util/imgs/left_top_search.jpg" width="19" height="30" align="bottom">
											</td>
											<td width="180" align="left" class="guideSearch">
												<%=messages.getText("generic.event.way")%>							
											</td>
											<td width="19" >
												<img src="/datacenter/util/imgs/right_top_search.jpg" width="19" height="30" align="bottom">
											</td>
										</tr>
										<tr>
											<td class="leftLineSearch">&nbsp;</td>
											<td>
	 											<table width="100%" border="0" cellpadding="0" cellspacing="0">
													<tr>
						        						<th align="left" class="lineone"> <%=messages.getText("generic.event.eventdata.way")%><span class="required">*</span></th>
						        						<td align="right" class="lineone">
							      							<html:select property="way" styleClass="input" >
							      								<html:options collection="ways" property="value" labelProperty="label" styleClass="input"/>
								  							</html:select>   
						          						</td>
						          					</tr>
					      							<tr  class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td>
													<tr>
						        						<th align="left" class="lineone"> <%=messages.getText("generic.event.eventdata.circulationdirection")%><span class="required">*</span></th>
				    	    							<td align="right" class="lineone">
					    	  								<html:select property="direction" styleClass="input" >
						      									<html:options collection="directions" property="value" labelProperty="label" styleClass="input"/>
						  									</html:select>   
					          							</td>
						          					</tr>
					      							<tr  class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td>
													<tr>
						        						<th align="left" class="lineone"> <%=messages.getText("generic.event.eventdata.trafficcontrol")%><span class="required">*</span></th>
				    		    						<td align="right" class="lineone">
					      									<html:select property="control" styleClass="input" >
					      										<html:options collection="controls" property="value" labelProperty="label" styleClass="input"/>
						  									</html:select>   
						          						</td>
						          					</tr>
	 											</table>
											</td>
	  										<td class="rightLineSearch">&nbsp;</td>
										</tr>	
	  									<tr>
	                 						<td height="11" valign="top"><img src="/datacenter/util/imgs/left_base_search.jpg" width="19" height="11"></td>
	 				  						<td class="centerBaseSearch"></td>
	 				  						<td valign="top" height="11"><img src="/datacenter/util/imgs/right_base_search.jpg" width="19" height="11"></td>
			 							</tr>
						  				<tr>
											<jsp:include  page="../component/spaceline.jsp" />
									  	</tr>
										<tr>
											<td colspan="3">
												<table width="100%">
													<tr>
						        						<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.climate")%><span class="required">*</span></th>
						        						<td align="right" class="linetwo">
							      							<html:select property="climate" styleClass="input" style="width:155;">
							      								<html:options collection="climates" property="value" labelProperty="label" styleClass="input"/>
								  							</html:select>   
						          						</td>
													</tr>
												</table>
											</td>
			          					</tr>
									</table>
								</td>
								<td width="16">&nbsp;</td>
								<td width="270" valign="top">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr class="guideSearch">
											<td width="19" >
												<img src="/datacenter/util/imgs/left_top_search.jpg" width="19" height="30" align="bottom">
											</td>
											<td width="180" align="left" class="guideSearch">
												<%=messages.getText("generic.event.eventdata.pavement")%>							
											</td>
											<td width="19" >
												<img src="/datacenter/util/imgs/right_top_search.jpg" width="19" height="30" align="bottom">
											</td>
										</tr>
										<tr>
											<td class="leftLineSearch">&nbsp;</td>
											<td>
	 											<table width="100%" border="0" cellpadding="0" cellspacing="0">
													<tr>
						        						<th align="left" class="lineone"> <%=messages.getText("generic.event.eventdata.pavement.state")%><span class="required">*</span></th>
						        						<td align="right" class="lineone">
							      							<html:select property="pavement" styleClass="input" >
							      								<html:options collection="pavements" property="value" labelProperty="label" styleClass="input"/>
								  							</html:select>   
						          						</td>
						          					</tr>
					      							<tr  class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td>
													<tr>
						        						<th align="left" class="lineone"> <%=messages.getText("generic.event.eventdata.conditionrout")%><span class="required">*</span></th>
						        						<td align="right" class="lineone">
							      							<html:select property="condition" styleClass="input" >
							      								<html:options collection="conditions" property="value" labelProperty="label" styleClass="input"/>
								  							</html:select>   
						          						</td>
						          					</tr>
	 											</table>
											</td>
	  										<td class="rightLineSearch">&nbsp;</td>
										</tr>	
	  									<tr>
	                 						<td height="11" valign="top"><img src="/datacenter/util/imgs/left_base_search.jpg" width="19" height="11"></td>
	 				  						<td class="centerBaseSearch"></td>
	 				  						<td valign="top" height="11"><img src="/datacenter/util/imgs/right_base_search.jpg" width="19" height="11"></td>
			 							</tr>
						  				<tr>
											<jsp:include  page="../component/spaceline.jsp" />
									  	</tr>
										<tr>
											<td colspan="3">
												<table width="100%">
													<tr>
						        						<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.lighting")%><span class="required">*</span></th>
						        						<td align="right" class="linetwo">
							      							<html:select property="lighting" styleClass="input" style="width:155;">
							      								<html:options collection="lightings" property="value" labelProperty="label" styleClass="input"/>
								  							</html:select>  
						          						</td>
						          					</tr>
												</table>
											</td>
			          					</tr>
						  				<tr>
											<jsp:include  page="../component/spaceline.jsp" />
										</tr>
									  	<tr>
											<td align="right" colspan="3">
												<input type="button" value='<%=messages.getText("generic.messages.nodata")%>' onclick="javascript:setnodata(document.forms['EventForm'])" class="input"/>
											</td>
										</tr>
									</table>
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
					<td width="600" valign="top" colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.otherpedestrian")%>' />
       						<table width='100%' border="0" cellpadding="0" cellspacing="0">
	            				<tr class="linetwo">
              						<td align="left" valign="middle"><strong><%=messages.getText("generic.event.pedestrian.count")%></strong></td>
		         					<td align="left">
		         						<html:select property="pedestriancount" styleClass="input" onchange="javascript:changePedestrianCount('EventForm')">
<%								for(int i = 0; i < 4; i++) {
%>			         						<html:option value='<%=String.valueOf(i)%>'><%=String.valueOf(i)%></html:option>
<%								}
%>			         					</html:select>
									</td>
								</tr>
	      						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%								for(int i = 1; i < 4; i++) {
%>								<tr>
									<td colspan="2">
										<div id=<%="pedestrian"+String.valueOf(i)%> style='<%= (i <= Integer.parseInt(form.getPedestriancount())) ? "display:block" : "display:none" %>' >
											<table width="100%">
												<tr>
													<td width="100%">
														<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.event.pedestrian")+" "+i%>' />
														<table width="100%" border="0" cellpadding="0" cellspacing="0">
															<tr  class="linetwo">
		      													<th align="left"> <%=messages.getText("generic.event.eventdata.pedestrianaction")%><span class="required">*</span></th>
		      													<td align="right">
								 									<html:select property='<%="pedestrian"+String.valueOf(i)%>' styleClass="input" >
																		<html:options collection="pedestrianActions" property="value" labelProperty="label" styleClass="input"/>
																	</html:select>   
			  													</td>
			  												</tr>
															<tr  class="linetwo">
		      													<th align="left"> <%=messages.getText("generic.event.pedestrian.comment")%></th>
		      													<td align="right" colspan="2">
								 									<html:text property='<%="pedestrianother"+String.valueOf(i)%>' styleClass="input" />
			  													</td>
															</tr>
														</table>
														<jsp:include  page="../component/utilitybox2bottom.jsp" />
													</td>
												</tr>
								  				<tr>
													<jsp:include  page="../component/spaceline.jsp" />
											  	</tr>
											</table>	
										</div>
									</td>
								</tr>
<%								}		
%>							</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
			  	<tr>
					<td width="270" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("Esquema de daños del vehículo")%>' />
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<table class="SearchBack" width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td width="100%" valign="top" >
												<table width="300" border="0" cellpadding="0" cellspacing="0" background="/datacenter/util/imgs/carro.jpg">
													<tr>
														<td align="right" valign="middle" height="40" width="10">
															<html:checkbox property="damage1"/>
														</td>
														<td align="center" valign="middle" height="40" width="280">
															<html:checkbox property="damage2"/>
														</td>
														<td align="left" valign="middle" height="40" width="10">
															<html:checkbox property="damage3"/>
														</td>
													</tr>
													<tr>
														<td align="right" valign="middle" height="40" width="10">
															<html:checkbox property="damage4"/>
														</td>
														<td align="center" valign="middle" height="40" width="280">
															<html:checkbox property="damage5"/>
															&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															<html:checkbox property="damage10"/>
														</td>
														<td align="left" valign="middle" height="40" width="10">
															<html:checkbox property="damage6"/>
														</td>
													</tr>
													<tr>
														<td align="right" valign="middle" height="45" width="10">
															<html:checkbox property="damage7"/>
														</td>
														<td align="center" valign="middle" height="45" width="280">
															<html:checkbox property="damage8"/>
														</td>
														<td align="left" valign="middle" height="45" width="10">
															<html:checkbox property="damage9"/>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
					 		</tr>
		      				<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr>
							   	<td class="linetwo">   
							   		<span align="left"><%=messages.getText("event.eventdata.cost")%></span>
				      				<span align="right"><html:text property="cost" styleClass="input" style="width:55;"/></span>
					  			</td>
							</tr>
					 	</table>	
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
					<td width="16">&nbsp;</td>
					<td width="270">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.involvedpeople")%>' />
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="3">
									<table width="100%">
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("event.eventdata.driverstate")%> </th>
										   	<td align="right">
							      				<html:select property="driverstate" styleClass="input" >
				      								<html:options collection="driverState" property="value" labelProperty="label" styleClass="input"/>
					  							</html:select>   
								  			</td>
										</tr>
					      				<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("event.eventdata.lostdays")%> </th>
										   	<td align="right">
							      				<html:text property="dayslost" styleClass="input" />
								  			</td>
										</tr>
					      				<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					      			</table>	
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr class="guideSearch">
											<td width="19" >
												<img src="/datacenter/util/imgs/left_top_search.jpg" width="19" height="30" align="bottom">
											</td>
											<td width="180" align="left" class="guideSearch">
												<%=messages.getText("event.eventdata.functionaries.state")%>							
											</td>
											<td width="19" >
												<img src="/datacenter/util/imgs/right_top_search.jpg" width="19" height="30" align="bottom">
											</td>
										</tr>
										<tr>
											<td class="leftLineSearch">&nbsp;</td>
										   	<td align="left" class="lineone">
											   	<%=messages.getText("event.eventdata.functionaries.state.dead")%>
				         						<html:select property="fatalitiesfunccount" styleClass="input">
<%									for(int i = 0; i < 6; i++) {
%>				         							<html:option value='<%=String.valueOf(i)%>'><%=String.valueOf(i)%></html:option>
<%									}
%>			         							</html:select>
											   	<%=messages.getText("event.eventdata.functionaries.state.injured")%>
				         						<html:select property="injuredfunccount" styleClass="input">
<%									for(int i = 0; i < 6; i++) {
%>				         							<html:option value='<%=String.valueOf(i)%>'><%=String.valueOf(i)%></html:option>
<%									}
%>			         							</html:select>
								  			</td>
	  										<td class="rightLineSearch">&nbsp;</td>
										</tr>	
	  									<tr>
	                 						<td height="11" valign="top"><img src="/datacenter/util/imgs/left_base_search.jpg" width="19" height="11"></td>
	 				  						<td class="centerBaseSearch"></td>
	 				  						<td valign="top" height="11"><img src="/datacenter/util/imgs/right_base_search.jpg" width="19" height="11"></td>
			 							</tr>
						  				<tr>
											<jsp:include  page="../component/spaceline.jsp" />
									  	</tr>
										<tr class="guideSearch">
											<td width="19" >
												<img src="/datacenter/util/imgs/left_top_search.jpg" width="19" height="30" align="bottom">
											</td>
											<td width="180" align="left" class="guideSearch">
												<%=messages.getText("event.eventdata.otherparty.state")%>							
											</td>
											<td width="19" >
												<img src="/datacenter/util/imgs/right_top_search.jpg" width="19" height="30" align="bottom">
											</td>
										</tr>
										<tr>
											<td class="leftLineSearch">&nbsp;</td>
										   	<td align="right" class="lineone">
											   	<%=messages.getText("event.eventdata.otherparty.state.dead")%>
				         						<html:select property="fatalitiesothercount" styleClass="input">
<%									for(int i = 0; i < 6; i++) {
%>				         							<html:option value='<%=String.valueOf(i)%>'><%=String.valueOf(i)%></html:option>
<%									}
%>			         							</html:select>
											   	<%=messages.getText("event.eventdata.otherparty.state.injured")%>
				         						<html:select property="injuredothercount" styleClass="input">
<%									for(int i = 0; i < 6; i++) {
%>				         							<html:option value='<%=String.valueOf(i)%>'><%=String.valueOf(i)%></html:option>
<%									}
%>			         							</html:select>
								  			</td>
	  										<td class="rightLineSearch">&nbsp;</td>
										</tr>	
	  									<tr>
	                 						<td height="11" valign="top"><img src="/datacenter/util/imgs/left_base_search.jpg" width="19" height="11"></td>
	 				  						<td class="centerBaseSearch"></td>
	 				  						<td valign="top" height="11"><img src="/datacenter/util/imgs/right_base_search.jpg" width="19" height="11"></td>
			 							</tr>
					      			</table>	
								</td>
							</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
  				<tr>
					<jsp:include  page='<%="../component/spaceline.jsp" %>'/>
			  	</tr>
				<tr>
					<td width="100%" colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.eventdata.comment")+"*"%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="270">
									<html:textarea property="comment" cols="70" />
								</td>
							</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
	  			<tr>
					<jsp:include  page='<%="../component/spaceline.jsp" %>'/>
	  			</tr>
	  			<tr>
	  				<td colspan="3">
						<table width="100%">
							<tr>
				        		<td align="right">
				          			<div align="right">
				            			<html:submit styleClass="input"><%=messages.getText("generic.messages.save")%></html:submit>
						 				<html:cancel styleClass="input"><%=messages.getText("generic.messages.cancel")%></html:cancel >
				          			</div>
				          		</td>
				  			</tr>
						</table>
	  				</td>
	  			</tr>
			</table>
		</body>
	</html:form>
<%	}else {
%>		<jsp:include  page='<%="/util/allowedData.jsp?message=event.error.access.event" %>'/>
<%	}
%>		
</html:html>
