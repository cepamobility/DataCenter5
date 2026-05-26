<%@page language="java" 
    errorPage="../exception.jsp"
	import="java.util.*"
	import="business.SystemAccess"
	import="communication.administration.corporation.tables.*"
	import="communication.administration.user.UserSessionData"
	import="communication.language.Text"
	import="presentation.translator.web.util.Util"
	import="communication.security.SecurityConstants"
	import="communication.driver.tables.DriverData"
	import="communication.event.tables.EventDataType"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%@page import="java.util.Date"%>
<%@page import="communication.util.MD5"%>
<%@page import="communication.vehicle.tables.VehicleData"%><html>
	<head>
		<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	 	<title> CEPA International</title>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
	</head>
	<body>
		<form name="logout" action="../login.jsp" method="post"></form>
<%

	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	if(sys == null){
		response.sendRedirect("/exception.jsp");
	}

	RequestDispatcher dispatcher = request.getRequestDispatcher("/util/jsp/message.jsp");
	dispatcher.include(request,response);

	UserSessionData userSessionData = sys.getUserSessionData();
	Text messages = sys.getText();

	Integer eventId = new Integer(request.getParameter("event"));
	String checkParameter = String.valueOf(request.getParameter("check"));
	
	String check = Util.checkPermission(sys,SecurityConstants.VIEW_RECOMENDATION);
	
	if(check != null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {

	    EventDataType eventDataType = sys.getEventReportFacade().getEventRecomendation(eventId,userSessionData);
	    DriverData driver = eventDataType.getDriver();
	    if(checkParameter == null || eventId == null || !checkParameter.equals(new MD5().encriptar(String.valueOf(eventId)))) {
%>	    	<%=messages.getText("user.permission.notadded")%>	
<%	    }else {
%>
			<table width="760" border="0" align="center" cellpadding="0" cellspacing="0">
  				<tr  class="guide1">
  					<jsp:include  page='<%="../component/titlecomponent.jsp?colspan=5&title="+messages.getText("generic.data.event")%>' />
			  	</tr>
  				<tr>
					<jsp:include  page='<%="../component/spaceline.jsp?colspan=3" %>' />
			  	</tr>
				<tr>
					<td width="45%" valign="top">
                    	<table width="100%">
                    		<tr>
                     			<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.driverdata")%>' />
		        					<table width='100%' border="0" cellpadding="0" cellspacing="0">
		            					<tr class="linetwo">
	              							<td align="left" valign="middle"><strong><%=messages.getText("generic.event.driver.name")%></strong></td>
	    									<td align="right" valign="middle" >			
                       							<span class="linetwo"><%=driver.getFirstName()+" "+driver.getLastName()%></span>
                       						</td>
										</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            					<tr class="linetwo">
	              							<td align="left" valign="middle"><strong><%=messages.getText("generic.event.driver.birthdate")%></strong></td>
	                            			<td align="right" valign="middle" >
                       							<%=Util.formatDate(driver.getBirthDate())%><%=" ( "+messages.getText("generic.messages.dateformat")+" )"%>
                       						</td>
	                         			</tr>	                         		
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            					<tr class="linetwo">
	              							<td align="left" valign="middle"><strong><%=messages.getText("generic.event.driver.startdate")%></strong></td>
	                            			<td align="right" valign="middle" >
                       							<%=Util.formatDate(driver.getInclusionDate())%><%=" ( "+messages.getText("generic.messages.dateformat")+" )"%>
							    			</td>
	                         			</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            					<tr class="linetwo">
	              							<td align="left" valign="middle"><strong><%=messages.getText("generic.event.driver.licensecategory")%></strong></td>
	                            			<td align="right" valign="middle">
                       							<%=(driver.getLicenseCategory() == null) ? "---" : driver.getLicenseCategory()%>
							    			</td>
	                         			</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            					<tr class="linetwo">
	              							<td align="left" valign="middle"><strong><%=messages.getText("generic.event.driver.licenseexpiry")%></strong></td>
	                            			<td align="right" valign="middle" >
                       							<%=Util.formatDate(driver.getLicenseExpiry())%><%=" ( "+messages.getText("generic.messages.dateformat")+" )"%>
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
<%				if(eventDataType.getCountry() != null) {
	    			GeoItemData geoItem1 = (GeoItemData)eventDataType.getCountry().getItemIterator().next();
%>
									<table width="100%" height="100%" cellpadding="0" cellspacing="0">
										<tr class="linetwo">
											<th align="left"> <%=messages.getText(eventDataType.getCountry().getResourceKey())%> </th>
											<td align="right">
												<%=(geoItem1.getSubItems() != null) ? geoItem1.getName() : messages.getText(geoItem1.getName()) %>
											</td>
										</tr>
<%			    	if(geoItem1.getSubItems() != null) {
				    	GeoItemData geoItem2 = (GeoItemData)geoItem1.getSubItems().getItemIterator().next();
%>										<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
										<tr class="linetwo">
											<th align="left"> <%=messages.getText(geoItem1.getSubItems().getResourceKey())%> </th>
											<td align="right">
												<%=(geoItem2.getSubItems() != null) ? geoItem2.getName() : messages.getText(geoItem2.getName()) %>
											</td>
										</tr>
						
<%
				    	if(geoItem2.getSubItems() != null) {
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
												<%=(Util.empty(eventDataType.getState())) ? "" : eventDataType.getState()%>
											</td>
										</tr>
										<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
										<tr class="linetwo">
											<th align="left"> <%=messages.getText("web.event.city")%> </th>
											<td align="right">
												<%=(Util.empty(eventDataType.getCity())) ? "" : eventDataType.getCity()%>
											</td>
										</tr>
									</table>	
			   						<jsp:include page="../component/utilityboxbottom.jsp"/>	
			   					</td>
		   					</tr>
		   					<tr>
		   						<td height="5"><img src="x" width="1" height="1"></td>
		   					</tr>
			   			</table>
					</td>
					<td width="5%" >&nbsp;</td>
					<td width="50%" valign="top">
						<table width="100%" height="100%" cellpadding="0" cellspacing="0">
<%				Iterator it = eventDataType.getDivOrgsIterator();
				while(it.hasNext()) {		
					DivOrgData divOrgData = (DivOrgData)it.next();
					if(divOrgData.getDivOrgLevels() != null) {
						ItemData item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
%>							<tr>
								<td valign="top" width="50%">	
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText(divOrgData.getName())%>' />
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr class="linetwo">
											<th align="left"> <%=messages.getText(divOrgData.getDivOrgLevels().getName())%> </th>
											<td align="right"><%=item1.getName()%></td>
										</tr>
<%	    				if(item1.getSubItems() != null) {
							ItemData item2 = (ItemData)item1.getSubItems().getItemIterator().next();
%>										<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr class="linetwo">
											<th align="left"> <%=messages.getText(item1.getSubItems().getName())%> </th>
											<td align="right"><%=item2.getName()%></td>
										</tr>
<%				    		if(item2.getSubItems() != null) {
								ItemData item3 = (ItemData)item2.getSubItems().getItemIterator().next();
%>										<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
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
<%					if(eventDataType.getVehicle() == null) { 
%>						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="270" class="linetwo">
									<table width="100%">
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.plate")%></th>
				         					<td align="right" class="linetwo"><%=eventDataType.getPlate()%></td>
			         					</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.vehicle.vehicledata.trade")%></th>
				         					<td align="right" class="linetwo"><%=(eventDataType.getTrade().equals("generic.messages.nodata")) ? messages.getText(eventDataType.getTrade()) : eventDataType.getTrade()%></td>
										</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.event.vehicle.companycar")%></th>
				         					<td align="right" class="linetwo"><%=(eventDataType.getCompanyCar()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no")%></td>
										</tr>
									</table>
								</td>
								<td width="16"></td>
								<td width="270">
									<table width="100%">
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.model")%></th>
				         					<td align="right" class="linetwo"><%=eventDataType.getModel()%></td>
										</tr>
		      							<tr  class="space">
		        							<th align="right" class="linetwo">&nbsp;</th>
		        							<td align="right" class="linetwo">&nbsp;</td>
		      							</tr>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.vehicle.vehicledata.year")%></th>
				         					<td align="right" class="linetwo"><%=(eventDataType.getYear().equals("generic.messages.nodata")) ? messages.getText(eventDataType.getYear()) : eventDataType.getYear()%></td>
										</tr>				  
<%					if(sys.getCorporationId() != CorporationData.CIMSA) {
%>		      							<tr  class="space">
		        							<th align="right">&nbsp;</th>
		        							<td align="right">&nbsp;</td>
		      							</tr>
										<tr>
							   				<th class="linetwo" align="left"><%=messages.getText("event.data.vehiclecost")%></th>
				         					<td align="right" class="linetwo"><%=(eventDataType.getCarCost() == null) ? "---" : String.valueOf(eventDataType.getCarCost())%></td>
										</tr>
<%					}
%>									</table>
								</td>
							</tr>
      					</table>    
<%			}else {
    			VehicleData vehicle = eventDataType.getVehicle();
%>						<table>
							<tr>
								<td width="270" class="linetwo">
									<table width="100%">
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.plate")+":"%> </th>
				         					<td align="right" class="linetwo"><%=(vehicle != null) ? vehicle.getMatricula() : "----"%></td>
			         					</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.vehicle.vehicledata.trade")+":"%> </th>
				         					<td align="right" class="linetwo"><%=(vehicle != null) ? messages.getText(vehicle.getTrade().getResourceBundleId()) : "----"%></td>
										</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.event.vehicle.companycar")%></th>
				         					<td align="right" class="linetwo"><%=(eventDataType.getCompanyCar()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no")%></td>
										</tr>
									</table>
								</td>
								<td width="16"></td>
								<td width="270">
									<table width="100%">
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.model")+":"%> </th>
				         					<td align="right" class="linetwo"><%=(vehicle != null) ? vehicle.getModel() : "----"%></td>
										</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.vehicle.vehicledata.year")+":"%> </th>
				         					<td align="right" class="linetwo"><%=(vehicle != null) ? vehicle.getModel() : "----"%></td>
										</tr>				  
		      							<tr  class="space">
		        							<th align="right">&nbsp;</th>
		        							<td align="right">&nbsp;</td>
		      							</tr>
										<tr>
							   				<th class="linetwo" align="left"><%=messages.getText("event.data.vehiclecost")%></th>
				         					<td align="right" class="linetwo"><%=(vehicle == null || vehicle.getCost() == null) ? "---" : String.valueOf(vehicle.getCost())%></td>
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
					<td width="600" colspan="4">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.eventdata")%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
						  <tr>
							 <td width="50%" class="linetwo" colspan="2">
						  		<table>
									<tr>
	       								<th align="left" class="linetwo" width="22%"> <%=messages.getText("generic.event.eventdata.date")+":"%> </th>
	         							<td align="right" class="linetwo" width="25%"><%=Util.formatDate(eventDataType.getDate())%></td>
			          					<td align="right" class="linetwo" width="3%"></td>
			          				</tr>
		      						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
									<tr>
			        					<th align="left" class="linetwo" width="22%"> <%=messages.getText("generic.event.eventdata.hour")+":"%> </th>
	         							<td align="right" class="linetwo" width="25%"><%=(eventDataType.getHour() == null) ? messages.getText("generic.messages.nodata") : Util.formatHour(eventDataType.getHour())%></td>
			        					<td align="right" class="linetwo" width="3%"></td>
			          				</tr>
			          				<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			          				<tr>
			        					<th align="left" class="linetwo" width="22%"> <%=messages.getText("generic.event.eventdata.working")+":"%> </th>
	         							<td align="right" class="linetwo" width="25%"><%=messages.getText(eventDataType.getWorking())%></td>
			          					<td align="right" class="linetwo" width="3%"></td>
			          				</tr>
		          	   			</table>
			          	   	</td>
			          	   	<td width="50%" class="linetwo" colspan="2">
			          	   		<table> 
			          	   	  		<tr>
										<td class="linetwo" width="3%"></td>
										<th class="linetwo" width="22%" align="left"><%=messages.getText("datatype.consequence.damage")+":"%></th>
										<td align="right" class="linetwo" width="25%"><%=(!eventDataType.isDamage()) ? messages.getText("generic.messages.no") : messages.getText("generic.messages.yes")%></td>
    								</tr>	      				
		      						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
									<tr>
			        					<td class="linetwo" width="3%"></td>
			        					<th align="left" class="linetwo" width="22%"> <%=messages.getText("generic.event.eventdata.zone")%></th>
	         							<td align="right" class="linetwo" width="25%"><%=messages.getText(eventDataType.getZone().getResourceBundleId())%></td>
			          				</tr>
			          				<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			          				<tr>
										<td class="linetwo" width="3%"></td>
										<th align="left" class="linetwo" width="22%"> <%=messages.getText("generic.event.eventdata.policereport")%></th>
	         							<td align="right" class="linetwo" width="25%"><%=messages.getText(eventDataType.getPoliceReport())%></td>
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
							<tr class="texts" >
								<td colspan="1" align="left" width="25%"></td>
								<td colspan="1" align="left" width="25%"><%=messages.getText("generic.event.eventdata.driver")%></td>
								<td colspan="1" align="left" width="25%"><%=messages.getText("event.eventdata.functionaries.state")%></td>
								<td colspan="1" align="left" width="25%"><%=messages.getText("event.eventdata.otherparty.state")%></td>
							</tr>
							
							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr class="texts">
    							<td colspan="1" align="left"><%=messages.getText("datatype.consequence.safe")+":"%></td>
    							<td align="left" colspan="1" class="linetwo"><%=(eventDataType.getDriverstate()!=null&&!eventDataType.getDriverstate().equals("datatype.driverstate.safe")) ? messages.getText("generic.messages.no") : messages.getText("generic.messages.yes")%></td>
    							<td class="linetwo" align="left" colspan="1"><%=String.valueOf(eventDataType.getSafefunccount()) %></td>
								<td class="linetwo" colspan="1" align="left"><%=String.valueOf(eventDataType.getSafeothercount()) %></td>
    						</tr>
    						
    						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr class="texts" >
    							<td colspan="1" align="left"><%=messages.getText("datatype.consequence.injured")+":"%></td>
    							<td align="left" colspan="1" class="linetwo"><%=(eventDataType.getDriverstate()!=null&&eventDataType.getDriverstate().equals("datatype.driverstate.injured")) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no")%></td>
    							<td class="linetwo" align="left" colspan="1"><%=String.valueOf(eventDataType.getInjuredfunccount()) %></td>
								<td class="linetwo" colspan="1" align="left"><%=String.valueOf(eventDataType.getInjuredothercount()) %></td>
    						</tr>
							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr class="texts" >
    							<td colspan="1" align="left"><%=messages.getText("datatype.consequence.fatalities")+":"%></td>
    							<td align="left" colspan="1" class="linetwo"><%=(eventDataType.getDriverstate()!=null&&eventDataType.getDriverstate().equals("datatype.driverstate.dead")) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no")%></td>
    							<td class="linetwo" align="left" colspan="1"><%=String.valueOf(eventDataType.getFatalitiesfunccount()) %></td>
								<td class="linetwo" colspan="1" align="left"><%=String.valueOf(eventDataType.getFatalitiesothercount()) %></td>
    						</tr>
		      				<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr class="texts">
							   	<th align="left" class="linetwo" colspan="1"> <%=messages.getText("event.eventdata.lostdays")+":"%> </th>
							   	<td align="left" colspan="1" class="linetwo">	
									<%=(eventDataType.getDayslost() == null) ? "0" : String.valueOf(eventDataType.getDayslost())%>
					  			</td>
							   	<td align="left" colspan="1" class="linetwo">
				      				<%=(eventDataType.getOtherpartydayslost() == null) ? "0": String.valueOf(eventDataType.getOtherpartydayslost())%>
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
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.eventdata.story")%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="85%" align="justify" colspan="3" class="linetwo">
										<%=eventDataType.getStory()%>
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
					<td valign="top" colspan="3" width="600">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.eventdata.clasification")%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
			        			<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.type")%></th>
	         					<td align="right" class="linetwo"><%=(eventDataType.getAccident() != null) ? messages.getText("generic.event.accident") : messages.getText("generic.event.incident")%></td>
							</tr>
							<tr>
								<jsp:include  page="../component/spaceline.jsp" />
				  			</tr>
							<tr>
			        			<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.clasification")%></th>
	         					<td align="right" class="linetwo"><%=(eventDataType.getAccident() != null) ? messages.getText(eventDataType.getAccident().getResourceBundleId()) : messages.getText(eventDataType.getIncident().getResourceBundleId())%></td>
							</tr>
							<tr>
								<jsp:include  page="../component/spaceline.jsp" />
				  			</tr>
							<tr>
			        			<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.colisionclasification")%></th>
	         					<td align="right" class="linetwo"><%=(eventDataType.getAccident() != null) ? messages.getText(eventDataType.getCollision().getResourceBundleId()):messages.getText("generic.messages.notapply")%></td>
							</tr>
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
							  		<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.event.eventdata.way")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
			        						<th align="left" class="lineone"> <%=messages.getText("generic.event.eventdata.way")%></th>
		    	    						<td align="right" class="lineone">
												<%=messages.getText(eventDataType.getWay().getResourceBundleId())%>
		          							</td>
		          						</tr>
	      								<tr  class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td>
										<tr>
		        							<th align="left" class="lineone"> <%=messages.getText("generic.event.eventdata.circulationdirection")%></th>
	    	    							<td align="right" class="lineone">
												<%=messages.getText(eventDataType.getDirection().getResourceBundleId())%>
			          						</td>
				          				</tr>
			      						<tr  class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td>
										<tr>
			    	    					<th align="left" class="lineone"> <%=messages.getText("generic.event.eventdata.trafficcontrol")%></th>
	    		    						<td align="right" class="lineone">
												<%=messages.getText(eventDataType.getControl().getResourceBundleId())%>
			          						</td>
			          					</tr>
									</table>
									<jsp:include  page="../component/utilitybox2bottom.jsp" />
									<br>
									<table width='100%'border="0" cellpadding="0" cellspacing="0">
										<tr>
						        			<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.climate")%></th>
							        		<td align="right" class="linetwo">
												<%=messages.getText(eventDataType.getClimate().getResourceBundleId())%>
							          		</td>
										</tr>
									</table>
							  	</td>
								<td width="16">&nbsp;</td>
								<td width="270" valign="top">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr  class="lineone">
											<td colspan="5" width="100%">
												<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.event.eventdata.pavement")%>' />
												<table width="100%" border="0" cellpadding="0" cellspacing="0">
													<tr>
						        						<th align="left" class="lineone"> <%=messages.getText("generic.event.eventdata.pavement.state")%></th>
						        						<td align="right" class="lineone">
															<%=messages.getText(eventDataType.getPavement().getResourceBundleId())%>
						          						</td>
						          					</tr>
					      							<tr  class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td>
													<tr>
						        						<th align="left" class="lineone"> <%=messages.getText("generic.event.eventdata.conditionrout")%></th>
						        						<td align="right" class="lineone">
															<%=messages.getText(eventDataType.getCondition().getResourceBundleId())%>
						          						</td>
						          					</tr>
												</table>
												<jsp:include  page="../component/utilitybox2bottom.jsp" />
											</td>
										</tr>
						  				<tr>
											<jsp:include  page="../component/spaceline.jsp" />
									  	</tr>
										<tr>
											<td colspan="3">
												<table width="100%" border="0" cellpadding="0" cellspacing="0">
													<tr>
						        						<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.lighting")%></th>
						        						<td align="right" class="linetwo">
															<%=messages.getText(eventDataType.getLighting().getResourceBundleId())%>
						          						</td>
						          					</tr>
											  </table>
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
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.recomendation")%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>   	
								<th align="left" class="linetwo">
									<%=String.valueOf(eventDataType.getRecomendation()).replace("\n","<br>")%>
								</th>
							</tr>							
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>						
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
				</tr>
				<tr>
					<td width="600" colspan="3" align="right">
						<input type="button" value="<%=messages.getText("generic.messages.logout")%>" onclick="javascript:document.forms['logout'].submit();" /> 
					</td>
				</tr>
			</table>
<%		}
	}
%>	</body>
</html>