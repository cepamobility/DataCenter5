<%@page language="java" 
    errorPage="../exception.jsp"
	import="business.*"
	import="business.datatype.*"
	import="business.vehicle.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.event.*"
	import="communication.event.tables.*"
	import="communication.driver.tables.*"
	import="communication.language.*"
	import="communication.datatype.*"
	import="communication.datatype.tables.*"
	import="communication.driver.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.administration.user.*"
	import="communication.vehicle.tables.*"
	import="communication.security.*"
	import="communication.activity.tables.CEPAActivityScheduleRegistryData"
	import="communication.activity.tables.CEPAActivityConstants"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%!
	RequestDispatcher dispatcher; SystemAccess sys;	String context;
	String pathLanguage; Text messages; Integer driverId; String driverName; String driverLastName;
	EventForm eventForm;EventDataType eventDataType;
	UserSessionData userSessionData;
	String action;Integer eventId;
	int corporationId;
	VehicleData vehicle = null;
%>
<%

	

	sys = (SystemAccess)session.getAttribute("SystemAccess");
	if(sys == null){
		response.sendRedirect("/exception.jsp");
	}


	userSessionData = sys.getUserSessionData();
	messages = sys.getText();
	corporationId = userSessionData.getFilter().getCorporationId().intValue();
	DriverData driver = userSessionData.getDriverData();
	
	if(userSessionData.isFleetUser() && corporationId == CorporationData.MONSANTO && driver.isArgPyUy()) {
		
		String accidentCollision="";
		context = request.getContextPath();
		dispatcher = request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
	
		messages = sys.getText();
	
		OptionItem emptyObject = new OptionItem(messages.getText("generic.messages.select"),"");
		OptionItem noDataObject = new OptionItem(messages.getText("generic.messages.nodata"),"generic.messages.nodata");
		OptionItem noApplyObject = new OptionItem(messages.getText("generic.messages.notapply"),"generic.messages.notapply");

		if((session.getAttribute("EventForm") != null)&&
		    	((request.getParameter("vehicle")==null) || (!request.getParameter("vehicle").equals("-1")))) {
		    	
			eventForm = (EventForm)session.getAttribute("EventForm");
		}else {
		    eventForm = new EventForm(driver);
			session.setAttribute("EventForm",eventForm);
		}	    
		if((request.getParameter("vehicle") != null)&&(!request.getParameter("vehicle").equals("-1"))) {
		    eventForm.setVehicleId(request.getParameter("vehicle"));
		}
		if(eventForm.getClasification()==null){
	    	accidentCollision="disabled";
		}
		session.setAttribute("url","/event/reportEvent.jsp?vehicle="+String.valueOf(eventForm.getVehicleId()));
	
		DataTypeUtil dataTypeUtil = new DataTypeUtil(sys);

		session.setAttribute("years",dataTypeUtil.getYears(1990,Calendar.getInstance().get(Calendar.YEAR)+1));
		session.setAttribute("generic",dataTypeUtil.getYesNo(true,true));
		session.setAttribute("noandnodata",dataTypeUtil.getNoAndNoData());
		session.setAttribute("vehicleType",dataTypeUtil.getVehicleType(false,false,false));
		session.setAttribute("zones",dataTypeUtil.getDataType(DataTypeConstants.ZONE,true,false,false));
		session.setAttribute("factorType",dataTypeUtil.getDataType(DataTypeConstants.ACCIDENT_FACTORS,true,false,false));
		session.setAttribute("behaviourType",dataTypeUtil.getDataType(DataTypeConstants.VEHICLE_BEHAVIOUR,true,false,false));
		session.setAttribute("climates",dataTypeUtil.getDataType(DataTypeConstants.CLIMATE,true,true,true));
		session.setAttribute("lightings",dataTypeUtil.getDataType(DataTypeConstants.LIGHTING,true,true,true));
		session.setAttribute("ways",dataTypeUtil.getDataType(DataTypeConstants.WAY,true,true,true));
		session.setAttribute("directions",dataTypeUtil.getDataType(DataTypeConstants.CIRCULATIONDIRECTION,true,true,true));
		
		session.setAttribute("controls",dataTypeUtil.getDataType(DataTypeConstants.TRAFFIC_CONTROLS,true,true,true));
		
		session.setAttribute("pavements",dataTypeUtil.getDataType(DataTypeConstants.PAVEMENT,true,true,true));
		session.setAttribute("conditions",dataTypeUtil.getDataType(DataTypeConstants.CONDITIONROUT,true,true,true));
		session.setAttribute("trades",dataTypeUtil.getTradeDataType(true));
	
		Collection pedestrianActions = new LinkedList();
		pedestrianActions.add(emptyObject);				
		pedestrianActions.add(new OptionItem(messages.getText("driver.pedestrian.cross.intersection"),"driver.pedestrian.cross.intersection"));				
		pedestrianActions.add(new OptionItem(messages.getText("driver.pedestrian.cross.otherplace"),"driver.pedestrian.cross.otherplace"));				
		pedestrianActions.add(new OptionItem(messages.getText("driver.pedestrian.cross.out"),"driver.pedestrian.out"));				
		pedestrianActions.add(new OptionItem(messages.getText("driver.pedestrian.cross.other"),"driver.pedestrian.other"));				
		if (sys.getCorporationId() == CorporationData.PEPSICO){
			pedestrianActions.add(new OptionItem(messages.getText("driver.pedestrian.cross.stayon"),"driver.pedestrian.cross.stayon"));
		}
		session.setAttribute("pedestrianActions",pedestrianActions);
			
		Collection hitrunOthers = new LinkedList();
		hitrunOthers.add(emptyObject);                
		hitrunOthers.add(noDataObject);                
		hitrunOthers.add(new OptionItem(messages.getText("driver.hitrun.no"),"driver.hitrun.no"));
		hitrunOthers.add(new OptionItem(messages.getText("driver.hitrun.yes.known"),"driver.hitrun.yes.known"));
		hitrunOthers.add(new OptionItem(messages.getText("driver.hitrun.yes.unknown"),"driver.hitrun.yes.unknown"));
		session.setAttribute("hitrunOther",hitrunOthers);
			
		Collection cellular = new LinkedList();
		cellular.add(emptyObject);				
		cellular.add(noDataObject);
		cellular.add(noApplyObject);
		cellular.add(new OptionItem(messages.getText("driver.cellular.no"),"driver.cellular.no"));
		cellular.add(new OptionItem(messages.getText("driver.cellular.yes.freehand"),"driver.cellular.yes.freehand"));
		cellular.add(new OptionItem(messages.getText("driver.cellular.yes.normal"),"driver.cellular.yes.normal"));
		session.setAttribute("cellulars",cellular);
		
		Collection selfbelt = new LinkedList();
		selfbelt.add(emptyObject);
		selfbelt.add(noDataObject);
		selfbelt.add(noApplyObject);
		selfbelt.add(new OptionItem(messages.getText("generic.messages.yes"),"generic.messages.yes"));
		selfbelt.add(new OptionItem(messages.getText("generic.messages.no"),"generic.messages.no"));
		if(sys.getCorporationId() == CorporationData.CIMSA) {
			selfbelt.add(new OptionItem(messages.getText("generic.messages.missing"),"generic.messages.missing"));
		}
		session.setAttribute("selfbelt",selfbelt);
		
		Collection speedunits = new LinkedList();
		speedunits.add(emptyObject);				
		speedunits.add(new OptionItem(messages.getText("driver.speedunits.metres"),"1"));
		speedunits.add(new OptionItem(messages.getText("driver.speedunits.milles"),"2"));
		session.setAttribute("speedunits",speedunits);

		Collection drives = new LinkedList();
		drives.add(emptyObject);				
		drives.add(new OptionItem(messages.getText("event.whodrives.driver"),"event.whodrives.driver"));
		drives.add(new OptionItem(messages.getText("event.whodrives.auxiliar"),"event.whodrives.auxiliar"));
		session.setAttribute("drives",drives);
	
%>

<html>
	<head>
		<title> CEPA </title>
		<link href="../util/css/estilo.css" rel="stylesheet" type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<style type="text/css">
		</style>
	</head>

<!-- *************************JCalendar***********************************-->
 <!-- calendar stylesheet -->
  <link rel="stylesheet" type="text/css" media="all" href="/datacenter/component/jscalendar-1.0/skins/aqua/theme.css" title="win2k-cold-1" />

  <!-- main calendar program -->
  <script type="text/javascript" src="/datacenter/component/jscalendar-1.0/calendar.js"></script>
  <script type="text/javascript" src='/datacenter/js/showHide.js' ></script>

  <!-- language for the calendar-->
<% if (userSessionData.getLenguage().equals("es")){
%><script type="text/javascript" src="/datacenter/component/jscalendar-1.0/lang/calendar-es.js"></script>
<% }else if(userSessionData.getLenguage().equals("en")){
%><script type="text/javascript" src="/datacenter/component/jscalendar-1.0/lang/calendar-en.js"></script>
<% }else{
%><script type="text/javascript" src="/datacenter/component/jscalendar-1.0/lang/calendar-pt.js"></script>
<%	} %>
  <!-- the following script defines the Calendar.setup helper function, which makes
       adding a calendar a matter of 1 or 2 lines of code. -->
  <script type="text/javascript" src="/datacenter/component/jscalendar-1.0/calendar-setup.js"></script>
  
  <!-- **********************end JCalendar ********************************* -->

	<script type="text/javascript" src='<%="./util/js/uiframework.js"%>'>
	</script>

	<!-- Para deshabilitar y habilitar todos los checks en CIMSA. Librería JQuery -->
	<script type="text/javascript" src="../menu/js/jquery-latest.js">
	</script>
	   
	<script language="javascript" src='../menu/js/driverFunctions.js' type='text/javascript' >
	</script>
	
	<script language="javascript" src='../menu/js/refactors.js' type='text/javascript' >
	</script>
	
	<script language="javascript" src='../menu/js/eventFunctions.js' type='text/javascript' >
	</script>
	
	<body>		
	<html:form action='/event/EventDriverCreate' >
		<html:hidden property="corpId" value='<%=String.valueOf(corporationId)%>' />
	 	<table width="100%" height="90" border="0" cellpadding="0" cellspacing="0" class="default">
		 	<form name="logout" action="../login.jsp" method="post">
	 		</form>
			<form name="vehicles" action="../vehicle/AssociatedVehicles.jsp" method="post"></form>		
  			<tr>
    			<td colspan="2">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" valign="top">
      					<tr>
        					<td><jsp:include  page='<%="../topMenu.jsp?home=true" %>'/></td>
      					</tr>
    				</table>	
    			</td><!--end the topï¿½s table -->
  			</tr>
  		</table>
  		<br>
  		<br>
		<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1">
				<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.messages.addevent")%>' />
		  	</tr>
			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
		  	</tr>
			<tr>
				<td width="49%" valign="top">
                   	<table width="100%">
                   		<tr>
                   			<td>
								<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.driverdata")%>' />
	        					<table width='100%' border="0" cellpadding="0" cellspacing="0">
	            					<tr class="linetwo">
              							<th align="left" valign="middle"><%=messages.getText("generic.event.driver.name")%></th>
    									<td align="right" valign="middle" >			
                    						<html:hidden property="driverid"/>
                       						<%=driver.getFirstName()+" "+driver.getLastName()%>
										</td>
									</tr>
		      						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            				<tr class="linetwo">
	              						<th align="left" valign="middle"><%=messages.getText("generic.event.driver.birthdate")%></th>
	                            		<td align="right">
                       						<%=(driver.getBirthDate() == null) ? "---" : Util.formatDate(driver.getBirthDate())+" ( "+messages.getText("generic.messages.dateformat")+" )"%>
							    		</td>
	                         		</tr>
	      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
	            					<tr class="linetwo">
              							<th align="left" valign="middle"><%=messages.getText("generic.event.driver.startdate")%></th>
                            			<td align="right">
                   							<%=(driver.getInclusionDate() == null) ? "---" : Util.formatDate(driver.getInclusionDate())+" ( "+messages.getText("generic.messages.dateformat")+" )"%>
						    			</td>
                         			</tr>
	      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
	            					<tr class="linetwo">
              							<th align="left" valign="middle"><%=messages.getText("generic.event.driver.licensecategory")%></th>
                            			<td align="right">
                   							<%=(driver.getLicenseCategory() == null) ? "---" : driver.getLicenseCategory()%>
						    			</td>
                         			</tr>
	      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
	            					<tr class="linetwo">
              							<th align="left" valign="middle"><%=messages.getText("generic.event.driver.licenseexpiry")%></th>
                            			<td align="right">
                   							<%=(driver.getLicenseExpiry() == null) ? "---" : Util.formatDate(driver.getLicenseExpiry())+" ( "+messages.getText("generic.messages.dateformat")+" )"%>
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
							<td valign="top">
								<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.divgeo")%>' />
<%				if(driver.getCountry() != null) {
	    			GeoItemData geoItem1 = (GeoItemData)driver.getCountry().getItemIterator().next();
%>
									<table width="100%" height="100%" cellpadding="0" cellspacing="0">
										<tr class="linetwo">
											<th align="left"> <%=messages.getText(driver.getCountry().getResourceKey())%> </th>
											<td align="right">
												<%=(geoItem1.getSubItems() != null) ? geoItem1.getName() : messages.getText(geoItem1.getName()) %>
											</td>
										</tr>
<%			    	if(geoItem1.getSubItems() != null) {
				    	GeoItemData geoItem2 = (GeoItemData)geoItem1.getSubItems().getItemIterator().next();
%>									
										<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
										<tr class="linetwo">
											<th align="left"> <%=messages.getText(geoItem1.getSubItems().getResourceKey())%> </th>
											<td align="right">
												<%=(geoItem2.getSubItems() != null) ? geoItem2.getName() : messages.getText(geoItem2.getName()) %>
											</td>
										</tr>
<%				    	if(geoItem2.getSubItems() != null) {
					    	GeoItemData geoItem3 = (GeoItemData)geoItem2.getSubItems().getItemIterator().next();
%>										<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
										<tr class="linetwo">
											<th align="left"> <%=messages.getText(geoItem2.getSubItems().getResourceKey())%> </th>
											<td align="right">
												<%=messages.getText(geoItem3.getName())%>
											</td>
										</tr>
<%						}
					}
				}
%>										<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
										<tr class="linetwo">
											<th align="left"> <%=messages.getText("web.event.state")%> </th>
											<td align="right">
													<html:text property="state" size="25" styleClass="input"/>
											</td>
										</tr>
										<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
										<tr class="linetwo">
											<th align="left"> <%=messages.getText("web.event.city")%> </th>
											<td align="right">
													<html:text property="city" size="25" styleClass="input"/>
											</td>
										</tr>
									</table>	
	   							<jsp:include page="../component/utilityboxbottom.jsp"/>
	   						</td>
	   					</tr>
	   				</table>	
				</td>
	            <td width="2%">&nbsp;</td>
                <td width="49%" valign="top">
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">					
<%			
				Iterator it = driver.getDivOrgsIterator();
				while(it.hasNext()) {		
					DivOrgData divOrgData = (DivOrgData)it.next();
					if(divOrgData.getDivOrgLevels() != null) {
						ItemData item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
%>					<tr>
						<td valign="top" width="50%">	
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText(divOrgData.getName())%>' />
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr class="linetwo">
									<th align="left"> <%=messages.getText(divOrgData.getDivOrgLevels().getName())%> </th>
									<td align="right"><%=item1.getName()%></td>
								</tr>
<%	    		
			    		if(item1.getSubItems() != null) {
							ItemData item2 = (ItemData)item1.getSubItems().getItemIterator().next();
%>								<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
								<tr class="linetwo">
									<th align="left"> <%=messages.getText(item1.getSubItems().getName())%> </th>
									<td align="right"><%=item2.getName()%></td>
								</tr>
<%				    
				    		if(item2.getSubItems() != null) {
								ItemData item3 = (ItemData)item2.getSubItems().getItemIterator().next();
%>								<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
								<tr class="linetwo">
									<th align="left"> <%=messages.getText(item2.getSubItems().getName())%> </th>
									<td align="right"><%=item3.getName()%></td>
								</tr>
<%							}
						}
%>
							</table>
   							<jsp:include page="../component/utilityboxbottom.jsp"/>	
	   					</td>
	   				</tr>
	   				<tr><td height="5"><img src="x" width="1" height="1"></td></tr>
<%					}
				}
%>
                     </table>
   	             </td>
			</tr>
			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
		  	</tr>
			<tr>
				<td colspan="3" width="600">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.vehicledata")%>' />
<%			if(Util.empty(eventForm.getVehicleId())) { 
%>					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td width="49%" class="linetwo">
								<table width="100%">
									<tr>
									   	<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.plate")%> <span class="required">*</span></th>
				       					<td align="right"><html:text property="vehicleplate" size="25" styleClass="input"/></td>
			        				</tr>
		      						<tr  class="space">
		      							<th align="right">&nbsp;</th>
		      							<td align="right">&nbsp;</td>
		      						</tr>
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
							<td width="2%"></td>
							<td width="49%">
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
								</table>
							</td>
						</tr>
      				</table>   
<%			}else {
    			vehicle = sys.getVehicleReportFacade().findVehicleById(new Integer(eventForm.getVehicleId()),sys.getUserSessionData());
%>					<html:hidden property="vehicleId" value='<%=request.getParameter("vehicle")%>' />
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td width="49%" class="linetwo">
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
   									<html:hidden property="vehiclecompanycar" value='<%=(vehicle.getOwnerShip() != null && vehicle.getOwnerShip().getResourceBundleId().equals("datatype.ownership.companycar")) ? "on" : "off"%>' />
								</table>
							</td>
							<td width="2%"></td>
							<td width="49%">
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
		      						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
									<tr>
										<th class="linetwo" align="left"><%=messages.getText("event.data.vehiclecost")%></th>
				         				<td align="right" class="linetwo"><%=(vehicle == null || vehicle.getCost() == null) ? "---" : String.valueOf(vehicle.getCost())%></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>    
<%				}
%>					<jsp:include  page="../component/utilityboxbottom.jsp" />
				</td>
			</tr>
	  		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
	  		</tr>
			<tr>
				<td width="600" colspan="4">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.eventdata")%>' />
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td width="50%" class="linetwo" colspan="2">
					  			<table>
									<tr>
		       							<th align="left" class="linetwo" width="22%"> <%=messages.getText("generic.event.eventdata.date")%><span class="required">*</span></th>
	       								<td align="right" class="linetwo" width="25%">
											<html:text property="date" style="width:20;" styleClass="input" styleId="dayIdEvent" maxlength="2" />/
		            						<html:text property="month" style="width:20;" styleClass="input" styleId="monthIdEvent" maxlength="2"/>/
		            						<html:text property="year" style="width:40;" styleClass="input" styleId="yearIdEvent"  maxlength="4"/>
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
			          					<td align="right" class="linetwo" width="3%"></td>
			          				</tr>
			      					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
									<tr>
				        				<th align="left" class="linetwo" width="22%"><%=messages.getText("generic.event.eventdata.hour")%><span class="required">*</span></th>
				        				<td align="right" class="linetwo" width="25%">
											<html:text property="hour" style="width:23;" styleClass="input" maxlength="2"/>&nbsp;:&nbsp;
		            						<html:text property="minute" style="width:23;" styleClass="input" maxlength="2"/>
												&nbsp;&nbsp;
											<html:hidden property="nodatahour" value="off"/>			       
				   						</td>
		        						<td align="right" class="linetwo" width="3%"></td>
			          				</tr>
			          				<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			          				<tr>
			        					<th align="left" class="linetwo" width="22%"> <%=messages.getText("generic.event.eventdata.working")%><span class="required">*</span></th>
			        					<td align="right" class="linetwo" width="25%">
											<html:select property="working" styleClass="input" >
	               								<html:options collection="generic" property="value" labelProperty="label" styleClass="input"/>
	           								</html:select>
			          					</td>
			          					<td align="right" class="linetwo" width="3%"></td>
			          				</tr>
				       	   		</table>
			          	   	</td>
			          	   	<td width="50%" class="linetwo" colspan="2">
			          	   		<table> 
			          	   	  		<tr class="texts" >
										<td class="linetwo" width="3%"></td>
										<td class="linetwo" width="22%" align="left"><%=messages.getText("datatype.consequence.damage")%></td>
										<td class="linetwo" width="25%" align="center">
											<html:checkbox property="damage" ></html:checkbox>
	   									</td>
	   								</tr>	      				
		      						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
									<tr>
			        					<td class="linetwo" width="3%"></td>
			        					<th align="left" class="linetwo" width="22%"><%=messages.getText("generic.event.eventdata.zone")%><span class="required">*</span></th>
			        					<td align="right" class="linetwo" width="25%">
				      						<html:select property="zone" styleClass="input" >
				      							<html:options collection="zones" property="value" labelProperty="label" styleClass="input"/>
					  						</html:select>   
			          					</td>
			          				</tr>
			          				<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			          				<tr>
										<td class="linetwo" width="3%"></td>
										<th align="left" class="linetwo" width="22%"><%=messages.getText("generic.event.eventdata.policereport")%><span></span></th>
				         				<td class="linetwo" width="25%" align="right">
											<html:select property="policeReport" styleClass="input" >
	           			    					<html:options collection="generic" property="value" labelProperty="label" styleClass="input"/>
	           								</html:select>
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
				<td width="600" valign="top" colspan="4">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.consequences")%>' />
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr class="texts"  >
							<td colspan="1" align="left" width="25%"></td>
							<td colspan="1" align="left" width="25%"><%=messages.getText("generic.event.eventdata.driver")%></td>
							<td colspan="1" align="left" width="25%"><%=messages.getText("event.eventdata.functionaries.state")%></td>
							<td colspan="1" align="left" width="25%"><%=messages.getText("event.eventdata.otherparty.state")%></td>
						</tr>
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr class="texts" colspan="4" width="100%">
   							<td colspan="1" align="left"><%=messages.getText("datatype.consequence.safe")%></td>
   							<td colspan="1" align="left"><html:radio property="driverstate" value="datatype.driverstate.safe" /></td>
   							<td class="linetwo" colspan="1" align="left">
        						<html:select property="safefunccount" styleClass="input">
<%									for(int i = 0; i < 6; i++) {
%>				         							<html:option value='<%=String.valueOf(i)%>'><%=String.valueOf(i)%></html:option>
<%									}
%>     							</html:select>
							</td>
							<td class="linetwo" colspan="1" align="left">
	       						<html:select property="safeothercount" styleClass="input">
<%									for(int i = 0; i < 6; i++) {
%>				         							<html:option value='<%=String.valueOf(i)%>'><%=String.valueOf(i)%></html:option>
<%									}
%>			         			</html:select>
							</td>
    					</tr>
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr class="texts" >
   							<td colspan="1" align="left"><%=messages.getText("datatype.consequence.injured")%></td>
   							<td colspan="1" align="left"><html:radio property="driverstate" value="datatype.driverstate.injured"/></td>
   							<td class="linetwo" colspan="1" align="left">
         						<html:select property="injuredfunccount" styleClass="input">
<%									for(int i = 0; i < 6; i++) {
%>        							<html:option value='<%=String.valueOf(i)%>'><%=String.valueOf(i)%></html:option>
<%									}
%>     							</html:select>
							</td>
							<td class="linetwo" colspan="1" align="left">
	       						<html:select property="injuredothercount" styleClass="input">
<%									for(int i = 0; i < 6; i++) {
%>	       							<html:option value='<%=String.valueOf(i)%>'><%=String.valueOf(i)%></html:option>
<%									}
%>	   							</html:select>
							</td>
    					</tr>
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr class="texts" >
    						<td colspan="1" align="left"><%=messages.getText("datatype.consequence.fatalities")%></td>
    						<td colspan="1" align="left"><html:radio property="driverstate" value="datatype.driverstate.dead"/></td>
    						<td class="linetwo" colspan="1" align="left">
	       						<html:select property="fatalitiesfunccount" styleClass="input">
<%									for(int i = 0; i < 6; i++) {
%>	       							<html:option value='<%=String.valueOf(i)%>'><%=String.valueOf(i)%></html:option>
<%									}
%>	   							</html:select>
							</td>
							<td class="linetwo" colspan="1" align="left">
	       						<html:select property="fatalitiesothercount" styleClass="input">
<%									for(int i = 0; i < 6; i++) {
%>	       							<html:option value='<%=String.valueOf(i)%>'><%=String.valueOf(i)%></html:option>
<%									}
%>	   							</html:select>
							</td>
    					</tr>
	      				<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr class="texts" >
						   	<th align="left" class="linetwo" colspan="1"> <%=messages.getText("event.eventdata.lostdays")%> </th>
						   	<td align="left" colspan="1">
			      				<html:text property="dayslost" styleClass="input" size="5" />
				  			</td>
						   	<td align="left" colspan="1">
			      				<html:text property="otherpartydayslost" styleClass="input" size="5" />
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
				<td width="600" colspan="3">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.eventdata.story")+"*"%>' />
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td width="85%" align="center">
								<html:textarea property="description" cols="80" rows="5" />
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
				<td width="100%" valign="top" colspan="3">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.eventdata.clasification")%>' />
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td width="292">
								<table>
<%				Collection accidents = dataTypeUtil.getDataTypeReport().findAll(DataTypeConstants.ACCIDENT_CLASIFICATION,sys.getUserSessionData());
				if(accidents != null && accidents.size() > 0) {
				    it = accidents.iterator();
				    while(it.hasNext()) {
				    	AccidentClasificationDataType accident = (AccidentClasificationDataType)it.next();
%>										<tr>
											<td width="100%" class="linetwo">
												<html:radio property="clasification" value='<%=accident.getResourceBundleId()%>' onclick="changeClasification(document.forms['EventSuperviseForm'],1,'collision');"><%=messages.getText(accident.getResourceBundleId())%></html:radio>
											</td>
										</tr>
<%					}
				}
%>									</table>
								</td>
								<td width="16"></td>
								<td width="292" valign="top">
									<table width="100%" cellpadding="0" cellspacing="0">
<%				Collection incidents = dataTypeUtil.getDataTypeReport().findAll(DataTypeConstants.INCIDENT_CLASIFICATION,sys.getUserSessionData());
				if(incidents != null && incidents.size() > 0) {
				    it = incidents.iterator();
				    while(it.hasNext()) {
				        IncidentClasificationDataType incident = (IncidentClasificationDataType)it.next();
				        if(!incident.getResourceBundleId().equals("datatype.incidentclasification.nonrecordable")) {
%>										<tr>
											<td width="100%" class="linetwo">
												<html:radio property="clasification" value='<%=incident.getResourceBundleId()%>' onclick="changeClasification(document.forms['EventSuperviseForm'],2,'collision');"><%=messages.getText(incident.getResourceBundleId())%></html:radio>
											</td>
										</tr>
<%					  	}
				    }
				}
%>									</table>
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
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.eventdata.clasification")%>' />
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr class="linetwo">
							<td width="33%" align="center">
								<html:radio value="0" property="clasificationType"  ><%=messages.getText("generic.event.accident")%></html:radio>
							</td>
							<td width="33%" align="center">
								<html:radio value="1" property="clasificationType" ><%=messages.getText("generic.event.incident")%></html:radio>
							</td>
							<td width="33%" align="center">
								<html:radio value="2" property="clasificationType" ><%=messages.getText("generic.event.nearmiss")%></html:radio>
							</td>
						</tr>
					</table>
					<jsp:include  page="../component/utilityboxbottom.jsp" />
				</td>
			</tr>
 			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
		  	</tr>
		  	<!-- Clasificacion de Colisiones: -->
			<tr>
				<td valign="top" colspan="3" width="600">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.eventdata.colisionclasification")+"*"%>' />
					<table width="100%" cellpadding="0" cellspacing="0">
<%				Collection collisions = dataTypeUtil.getDataTypeReport().findAll(DataTypeConstants.COLLISION_CLASIFICATION,sys.getUserSessionData());
				if(collisions != null && collisions.size() > 0) {
				    it = collisions.iterator();
				    for(int i = 0; i < ((int)Math.floor(collisions.size()/2)); i++) {
%>							<tr>
<%						for(int j = 0; j < 2; j++) {
							
							if (it.hasNext()){	
								CollisionClasificationDataType accident = (CollisionClasificationDataType)it.next();
%>								<td width="50%" class="linetwo">
									<html:radio property="collision"  value='<%=accident.getResourceBundleId()%>' onclick="changeEvents(document.forms['EventForm'],2,'collision','clasification')"><%=messages.getText(accident.getResourceBundleId())%></html:radio>
								</td>
<%							}
					   }
%>							</tr>
<%				   }
				    if(it.hasNext()&&corporationId!=4){
				    	CollisionClasificationDataType accident = (CollisionClasificationDataType)it.next();
%>							<tr>
								<td width="50%" class="linetwo">&nbsp;</td>	
								<td width="50%" class="linetwo">
									<html:radio property="collision" value='<%=accident.getResourceBundleId()%>' disabled="true"><%=messages.getText(accident.getResourceBundleId())%></html:radio>
								</td>
							</tr>
<%					}
				}
%>					</table>
					<jsp:include  page="../component/utilityboxbottom.jsp" />
				</td>
			</tr>	
			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
			</tr>
       		<tr>
       			<td colspan="3" width="600">
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
               						<html:options collection="generic" property="value" labelProperty="label" styleClass="input"/>
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
						<tr>
							<td colspan="5" width="100%">
								<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.event.eventdata.factors")%>' />
								<table width="100%"  cellpadding="0" cellspacing="0">
									<tr>
										<td width="49%" class="lineone">
											<html:checkbox property="factorovertaking"><%=messages.getText("datatype.accidentfactors.overtaking")%></html:checkbox>
										</td>
										<td width="2%" class="lineone">&nbsp;</td>
										<td width="49%" class="lineone">
											<html:checkbox property="factoralcohol"><%=messages.getText("datatype.accidentfactors.drugs")%></html:checkbox>
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
									<tr>
										<td width="49%" class="lineone">
											<html:checkbox property="factorConcience"><%=messages.getText("datatype.accidentfactors.conscience")%></html:checkbox>
										</td>
										<td width="2%" class="lineone">&nbsp;</td>
										<td width="49%" class="lineone">
											<html:checkbox property="factorOtherParking"><%=messages.getText("datatype.accident.factors.other.parking.baking")%></html:checkbox>
										</td>
									</tr>
									<tr>
										<td width="49%" class="lineone">
											<html:checkbox property="factorOtherCrossing"><%=messages.getText("datatype.accident.factors.other.crossing")%></html:checkbox>
										</td>
										<td width="2%" class="lineone">&nbsp;</td>
										<td width="49%" class="lineone">
  												<html:checkbox property="factorOtherStanding"><%=messages.getText("datatype.accident.factors.other.parking.standing")%></html:checkbox>
										</td>
									</tr>
									<tr>
										<td width="49%" class="lineone">
											<html:checkbox property="factorMecanicFail"><%=messages.getText("datatype.accident.factors.mecanicfail")%></html:checkbox>
										</td>
										<td width="2%" class="lineone">&nbsp;</td>
										<td width="49%" class="lineone">&nbsp;</td>
									</tr>										
								</table>
								<jsp:include  page='<%= "../component/utilitybox2bottom.jsp?" %>'/>
							</td>
						</tr>	
						<tr  class="linetwo">
							<td colspan="5" width="100%">
								<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.event.eventdata.collisionangle")%>' />
								<table width="100%"  cellpadding="0" cellspacing="0">
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
								<jsp:include  page='<%= "../component/utilitybox2bottom.jsp?" %>'/>
							</td>
						</tr>
						<tr>
						   	<td width="270" colspan="2">
								<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.event.eventdata.speed")%>' />
									<table  width="100%"  cellpadding="0" cellspacing="0">
										<tr>
										   	<th align="left" class="lineone"> 
										   		<%=messages.getText("generic.event.eventdata.maxspeed")%>
				         					</th>
				         					<td align="right" class="lineone">
				         						<html:text property="maxspeed" size="10" styleClass="input"/>
			         						</td>
										</tr>
		      							<tr  class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="lineone"> 
										   		<%=messages.getText("generic.event.eventdata.collisionspeed")%>
				         					</th>
				         					<td align="right" class="lineone">
												<html:text property="collisionspeed" size="10" styleClass="input"/>
			         						</td>
										</tr>
		      							<tr  class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="lineone"> 
										   		<%=messages.getText("generic.event.eventdata.vehiclespeed")%>
				         					</th>
				         					<td align="right" class="lineone">
				         						<html:text property="vehiclespeed" size="10" styleClass="input"/>
			         						</td>
										</tr>
		      							<tr  class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="lineone"> 
										   		<%=messages.getText("generic.event.eventdata.reportedspeed")%>
				         					</th>
				         					<td align="right" class="lineone">
				         						<html:text property="reportedspeed" size="10" styleClass="input"/>
			         						</td>
										</tr>
		      							<tr  class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
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
								<td width="270" colspan="2">
									<table>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo" ><%=messages.getText("generic.event.eventdata.selfbelt")%></th>
				         					<td class="linetwo">
												<html:select property="selfbelt" styleClass="input" >
            			    						<html:options collection="selfbelt" property="value" labelProperty="label" styleClass="input"/>
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
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td>
		      							</tr>
										
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
							         	<tr  class="space">
							         				<th align="right">&nbsp;</th><td align="right">&nbsp;</td>
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
                 	<td width="600" colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.othervehicles")%>' />
		        		<table width='100%' border="0" cellpadding="0" cellspacing="0">
		            		<tr class="linetwo">
	              				<td align="left" valign="middle"><strong><%=messages.getText("generic.event.vehicle.count")%></strong></td>
				    			<td align="left">
				    				<html:select property="vehiclecount" styleClass="input" onchange="javascript:changeVehicleCount('EventForm');">
<%									for(int i = 0; i < 6; i++) {
%>				         							<html:option value='<%=String.valueOf(i)%>'><%=String.valueOf(i)%></html:option>
<%									}
%>			        				</html:select>
								</td>
							</tr>
		      				<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%									for(int i = 1; i < 6; i++) {
%>							<tr>
								<td colspan="2" >
									<div id=<%="vehicle"+String.valueOf(i)%> style='<%= (i <= Integer.parseInt(eventForm.getVehiclecount())) ? "display:block" : "display:none" %>' >
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
															<td colspan="3" >
																<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.event.eventdata.factors")%>' />
																<table width="100%" border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		<td width="49%" class="lineone">
																			<html:checkbox property='<%="factorovertaking"+i%>'><%=messages.getText("datatype.accidentfactors.overtaking")%></html:checkbox>
																		</td>
																		<td width="2%" class="lineone">&nbsp;</td>
																		<td width="49%" class="lineone">
																			<html:checkbox property='<%="factoralcohol"+i%>'><%=messages.getText("datatype.accidentfactors.drugs")%></html:checkbox>
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
																	<tr>
									                                	<td width="49%" class="lineone">
																			<html:checkbox property='<%="factorConcience"+i%>'><%=messages.getText("datatype.accidentfactors.conscience")%></html:checkbox>
																		</td>
																		<td width="2%" class="lineone">&nbsp;</td>
																		<td width="49%" class="lineone">
																			<html:checkbox property='<%="factorOtherParking"+i%>'><%=messages.getText("datatype.accident.factors.other.parking.baking")%></html:checkbox>
																		</td>
																	</tr>
																	<tr>
																		<td width="49%" class="lineone">
																			<html:checkbox property='<%="factorOtherCrossing"+i%>'><%=messages.getText("datatype.accident.factors.other.crossing")%></html:checkbox>
																		</td>
																		<td width="2%" class="lineone">&nbsp;</td>
																		<td width="49%" class="lineone">
								   											<html:checkbox property='<%="factorOtherStanding"+i%>'><%=messages.getText("datatype.accident.factors.other.parking.standing")%></html:checkbox>
																		</td>
																	</tr>
																</table>
																<jsp:include  page='<%="../component/utilitybox2bottom.jsp" %>'/>
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
													<jsp:include  page='<%="../component/utilitybox2bottom.jsp" %>'/>
												</td>
											</tr>
							  				<tr>
												<jsp:include  page="../component/spaceline.jsp" />
										  	</tr>
										</table>	
										</div>
									</td>
								</tr>
<%							}
%>
	               			</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
				<tr>
					<td colspan="3" width="600">
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
					      							</tr>
													<tr>
						        						<th align="left" class="lineone"> <%=messages.getText("generic.event.eventdata.circulationdirection")%><span class="required">*</span></th>
				    	    							<td align="right" class="lineone">
					    	  								<html:select property="direction" styleClass="input" >
						      									<html:options collection="directions" property="value" labelProperty="label" styleClass="input"/>
						  									</html:select>   
					          							</td>
						          					</tr>
						          					
						          					<%if (sys.getCorporationId() == CorporationData.PEPSICO){ 
						          						// Si es PEPSCIO permito que los controles de transito "Señalamientos existentes"
						          						// sean multiple select
						          					%>
						          					<tr  class="lineone">
					      								<th align="right">&nbsp;</th><td align="right">&nbsp;</td>
					      							</tr>
													<tr>
						        						<th align="left" class="lineone" valign="top"> <%=messages.getText("generic.event.eventdata.trafficcontrol")%><span class="required">*</span></th>
				    		    						<td align="right" class="lineone">
						    		    						<!-- TODO: Elegir una o varias opciones -->
																<table width="100%">
		<%														String checks2 = eventForm.getControlMultiple();
																	if (Util.empty(checks2)) { checks2 = "0000000"; }
																	for(int j = 1; j <= 7; j++) {				
		%>														<tr>
																		<td>
																			<input type="checkbox" name='<%="controlMultipleOption"+j%>' <%=(checks2.charAt(j-1) == '1') ? "checked" : "" %> />
																			<span class="input"><%=messages.getText("driver.controlmultiple.option"+j) %></span>
																		</td>		
																	</tr>
		<%														}
		%>													</table>					      									
						          						</td>
						          					</tr>
						          					<%}else{ %>
					      							<tr  class="lineone">
					      								<th align="right">&nbsp;</th><td align="right">&nbsp;</td>
													</tr>
													<tr>
						        						<th align="left" class="lineone"> <%=messages.getText("generic.event.eventdata.trafficcontrol")%><span class="required">*</span></th>
				    		    						<td align="right" class="lineone">
					      									<html:select property="control" styleClass="input" >
					      										<html:options collection="controls" property="value" labelProperty="label" styleClass="input"/>
						  									</html:select>   
						          						</td>
						          					</tr>
						          					<%} %>
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
										<div id=<%="pedestrian"+String.valueOf(i)%> style='<%= (i <= Integer.parseInt(eventForm.getPedestriancount())) ? "display:block" : "display:none" %>' >
											<table width="100%">
												<tr>
													<td width="100%">
														<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.event.pedestrian")+" "+i%>' />
														<table width="100%" border="0" cellpadding="0" cellspacing="0">
															<tr  class="lineone">
		      													<th align="left"> <%=messages.getText("generic.event.eventdata.pedestrianaction")%><span class="required">*</span></th>
		      													<td align="right">
								 									<html:select property='<%="pedestrian"+String.valueOf(i)%>' styleClass="input" >
																		<html:options collection="pedestrianActions" property="value" labelProperty="label" styleClass="input"/>
																	</html:select>   
			  													</td>
			  												</tr>
															<tr  class="lineone">
		      													<th align="left"> <%=messages.getText("generic.event.pedestrian.comment")%></th>
		      													<td align="right" colspan="2">
								 									<html:text property='<%="pedestrianother"+String.valueOf(i)%>'  size="70" styleClass="input"	 />
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
			  	
			  	<!--  Esquema de Daños, con dibujo de vehiculo -->
			  	<tr>
					<td width="600" valign="top" colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.damageschema")%>' />
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<!-- Dibujo del auto -->
									<div id="carImage" >
										<table width="300px" border="0" cellpadding="0" cellspacing="0" background="/datacenter/util/imgs/carro.jpg">
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
												<td align="right" valign="middle" height="48" width="10">
													<html:checkbox property="damage4"/>
												</td>
												<td align="center" valign="middle" height="48" width="280">
													<html:checkbox property="damage5"/>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<html:checkbox property="damage10"/>
													&nbsp;&nbsp;&nbsp;&nbsp;
												</td>
												<td align="left" valign="middle" height="48" width="10">
													<html:checkbox property="damage6"/>
												</td>
											</tr>
											<tr>
												<td align="right" valign="bottom" height="33" width="10">
													<html:checkbox property="damage7"/>
												</td>
												<td align="center" valign="bottom" height="33" width="280">
													<html:checkbox property="damage8"/>
												</td>
												<td align="left" valign="bottom" height="33" width="10">
													<html:checkbox property="damage9"/>
												</td>
												   
											</tr>
											<tr>
												<td align="left"height="5"></td>
											</tr>
										</table>
									</div>
								</td>
							   	<td class="linetwo" colspan="1" align="left" valign="middle">   
							   		<table width="100%" border="0">
							   			<tr class="linetwo">
							   				<td>
										   		<span align="left">
											   			<%=messages.getText("generic.event.finedata.cost")%>
										   		</span>
										   	</td>
										   	<td align="right">
							      				<html:text property="cost" styleClass="input"  style="width:55;"/>
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
					<td width="600" colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.eventdata.comment")%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="85%" align="center">
									<html:textarea property="comment" cols="80" />
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
	  				<td colspan="3">
						<table width="100%">
							<tr>
				        		<td align="right">
				          			<div align="right">
				            			<html:submit styleClass="input"><%=messages.getText("generic.messages.save")%></html:submit>
						 				<input type="button" value='<%=messages.getText("generic.messages.cancel")%>' onclick="document.forms['vehicles'].submit();" class="input"/>
				          			</div>
				          		</td>
				  			</tr>
						</table>
	  				</td>
	  			</tr>
			</table>
		</html:form>
	</body>
</html>
<%
	}	
%>	
