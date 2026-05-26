<%@page language="java" 
    errorPage="../exception.jsp"
	import="business.*"
	import="business.datatype.*"
	import="business.vehicle.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.event.*"
	import="communication.travel.tables.*"
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

<%!RequestDispatcher dispatcher; SystemAccess sys;	String context;
	Text messages; Integer id; 
	TravelDataType travelDataType;
	UserSessionData userSessionData;
	VehicleData vehicle = null;%>
<%

	try{

		sys = (SystemAccess)session.getAttribute("SystemAccess");
		if(sys == null){
			response.sendRedirect("/exception.jsp");
		}
	
		context=request.getContextPath();
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
	
		userSessionData = sys.getUserSessionData();
		messages=sys.getText();
		id = new Integer(request.getParameter("id"));
		String check = Util.checkPermission(sys,SecurityConstants.MODIFY_EVENT);
		if(check!=null) {
			response.sendRedirect(request.getContextPath()+check);
		}else {
			String accidentCollision="";
			context=request.getContextPath();
			dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
			dispatcher.include(request,response);
		
			messages=sys.getText();
			travelDataType = sys.getEventReportFacade().findTravelByPrimaryKey(id,sys.getUserSessionData());
	
			if(travelDataType != null) {
				session.setAttribute("url","/travel/TravelView.jsp?id="+String.valueOf(id));
%>
			<script language="JavaScript" type="text/javascript">
				function confirmDelete(form,msg){
					if(confirm(msg)){
						form.submit();
					}
				}
			</script>
			<form action='layout.jsp?refer=/travel/TravelCreate.jsp?type=edit&id=<%=String.valueOf(id)%>' name='edit' method="post">
			</form>	
			<form action='layout.jsp?refer=/travel/TravelList.jsp' name='back' method="post">
			</form>	
			<form action='layout.jsp?refer=/travel/TravelCheckPoints.jsp?id=<%=String.valueOf(id)%>' name='checkpoints' method="post">
			</form>	
			<form action='layout.jsp?refer=/travel/TravelClose.jsp?id=<%=String.valueOf(id)%>' name='close' method="post">
			</form>	
			<html:form action="/Traveldelete" >
				<html:hidden property="travel" value='<%=String.valueOf(id)%>'/>
			</html:form>
			<table align="center">
				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
 				<tr class="guide1">
  					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.data.travel")%>' />
			  	</tr>
				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
			  	<tr>			  		
			  		<jsp:include page='TravelViewOptions.jsp?travelId=<%=String.valueOf(id)%>' />
			  	</tr>
  				<tr>
					<jsp:include  page='<%="../component/spaceline.jsp?colspan=3" %>' />
			  	</tr>
				<tr>
					<td width="49%" valign="top">
                    	<table width="100%">
                    		<tr>
                     			<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.driverdata")%>' />
		        					<table width='100%' border="0" cellpadding="0" cellspacing="0">
		            					<tr class="linetwo">
	              							<th align="left" valign="middle"><%=messages.getText("travel.driver.name")%></th>
	    									<td align="right" valign="middle" >			
                       							<%=travelDataType.getDriver().getFirstName()+" "+travelDataType.getDriver().getLastName()%>
											</td>
	                         			</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            					<tr class="linetwo">
	              							<th align="left" valign="middle"><%=messages.getText("travel.driver.mobile")%></th>
	                            			<td align="right">
                       							<%=(travelDataType.getMobile() == null) ? "---" : travelDataType.getMobile()%>
							    			</td>
	                         			</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            					<tr class="linetwo">
	              							<th align="left" valign="middle"><%=messages.getText("travel.driver.radio")%></th>
	                            			<td align="right">
	                            				<%=(Util.empty(travelDataType.getRadio())) ? "---" : travelDataType.getRadio()%>
							    			</td>
	                         			</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            					<tr class="linetwo">
	              							<th align="left" valign="middle"><%=messages.getText("travel.driver.charge")%></th>
	    									<td align="right" valign="middle" >			
	    										<%=(travelDataType.getCharge() == null) ? "---" : travelDataType.getCharge()%>
											</td>
	                         			</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            					<tr class="linetwo">
	              							<th align="left" valign="middle"><%=messages.getText("travel.driver.other")%></th>
	                            			<td align="right">
	                            				<%=(Util.empty(travelDataType.getOther())) ? "---" : travelDataType.getOther()%>
							    			</td>
	                         			</tr>
	                     			</table>
									<jsp:include  page="../component/utilityboxbottom.jsp" />
								</td>
							</tr>
						</table>
					</td>
					<td width="2%" valign="top">&nbsp;</td>
					<td width="49%" valign="top">
						<table width="100%" height="100%" cellpadding="0" cellspacing="0">
<%			
				Iterator it = travelDataType.getDivOrgsIterator();
				while(it.hasNext()) {		
					DivOrgData divOrgData = (DivOrgData)it.next();
					if(divOrgData.getDivOrgLevels() != null) {
						ItemData item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
%>							<tr>
								<td valign="top" width="50%">	
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText(divOrgData.getName())%>' />
										<table width="100%"  cellpadding="0" cellspacing="0">
											<tr class="linetwo">
												<th align="left"> <%=messages.getText(divOrgData.getDivOrgLevels().getName())%> </th>
												<td align="right"><%=item1.getName()%></td>
											</tr>
<%	    				if(item1.getSubItems() != null) {
							ItemData item2 = (ItemData)item1.getSubItems().getItemIterator().next();
%>											<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
											<tr class="linetwo">
												<th align="left"> <%=messages.getText(item1.getSubItems().getName())%> </th>
												<td align="right"><%=item2.getName()%></td>
											</tr>
<%				    		if(item2.getSubItems() != null) {
								ItemData item3 = (ItemData)item2.getSubItems().getItemIterator().next();
%>											<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
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
	   						<tr><td height="5"><img src="x" width="1" height="1"></tr>
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
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title=VEÍCULO" %>' />
<%			if(travelDataType.getVehicle() == null) { 
%>						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="270" class="linetwo">
									<table width="100%">
										<tr>
										   	<th align="left" class="linetwo"><%=messages.getText("travel.vehicle.plate")%></th>
				         					<td align="right" class="linetwo">
				         						<%=(Util.empty(travelDataType.getPlate())) ? "---" : travelDataType.getPlate()%>
				         					</td>
			         					</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo"><%=messages.getText("travel.vehicle.trade")%> </th>
				         					<td align="right" class="linetwo">
	                            				<%=(Util.empty(travelDataType.getTrade())) ? "---" : messages.getText(travelDataType.getTrade())%>
				         					</td>
										</tr>
									</table>
								</td>
								<td width="16"></td>
								<td width="270">
									<table width="100%">
										<tr>
										   	<th align="left" class="linetwo"><%=messages.getText("travel.vehicle.model")%></th>
				         					<td align="right" class="linetwo">
	                            				<%=(Util.empty(travelDataType.getModel())) ? "---" : travelDataType.getModel()%>
				         					</td>
										</tr>
		      							<tr  class="space">
		        							<th align="right">&nbsp;</th>
		        							<td align="right">&nbsp;</td>
		      							</tr>
										<tr>
										   	<th align="left" class="linetwo"><%=messages.getText("travel.vehicle.color")%></th>
				         					<td align="right" class="linetwo">
	                            				<%=(Util.empty(travelDataType.getColor())) ? "---" : travelDataType.getColor()%>
                							</td>
										</tr>				  
									</table>
								</td>
							</tr>
       					</table>   

 
<%			}else {
%>						<table>
							<tr>
								<td width="270" class="linetwo">
									<table width="100%">
										<tr>
										   	<th align="left" class="linetwo"><%=messages.getText("travel.vehicle.plate")%></th>
				         					<td align="right" class="linetwo"><%=travelDataType.getVehicle().getMatricula()%></td>
			         					</tr>
		      							<tr  class="space">
		        							<th align="right">&nbsp;</th>
		        							<td align="right">&nbsp;</td>
		      							</tr>
										<tr>
										   	<th align="left" class="linetwo"><%=messages.getText("travel.vehicle.trade")%></th>
				         					<td align="right" class="linetwo"><%=messages.getText(travelDataType.getVehicle().getTrade().getResourceBundleId())%></td>
										</tr>
									</table>
								</td>
								<td width="16"></td>
								<td width="270">
									<table width="100%">
										<tr>
										   	<th align="left" class="linetwo"><%=messages.getText("travel.vehicle.model")%></th>
				         					<td align="right" class="linetwo"><%=travelDataType.getVehicle().getModel()%></td>
										</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo"><%=messages.getText("travel.vehicle.color")%></th>
				         					<td align="right" class="linetwo"><%=travelDataType.getVehicle().getColour()%></td>
										</tr>				  
									</table>
								</td>
							</tr>
						</table>    
<%				}
%>						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
	  			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
				<tr>
					<td width="600" colspan="4">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title=ITINERÁRIO PREVISTO" %>' />
		        		<table width='100%' border="0" cellpadding="0" cellspacing="0">
		            		<tr class="linetwo">
	              				<th align="left" valign="middle"><%=messages.getText("travel.from.place")%></th>
	    						<td align="right" valign="middle" >
	                            	<%=(Util.empty(travelDataType.getLocalLeave())) ? "---" : travelDataType.getLocalLeave()%>
								</td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	              				<th align="left" valign="middle"><%=messages.getText("travel.to.place")%></th>
	                            <td align="right">
	                            	<%=(Util.empty(travelDataType.getLocalArrive())) ? "---" : travelDataType.getLocalArrive()%>
							    </td>
	                      	</tr>
							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            		<tr class="linetwo">
	              				<th align="left" valign="middle"><%=messages.getText("travel.leave.date") %></th>
	       						<td align="right" class="linetwo" width="25%">
	       							<%=(travelDataType.getDateLeave() != null) ? Util.formatDate(travelDataType.getDateLeave()) : "---" %>
			          			</td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	              				<th align="left" valign="middle"><%=messages.getText("travel.stop.schedule")%></th>
	                            <td align="right">
	                            	<%=(travelDataType.getStops() == null) ? "---" : String.valueOf(travelDataType.getStops())%>
							    </td>
								<td>&nbsp;</td>
	                      	</tr>
							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            		<tr class="linetwo">
	              				<th align="left" valign="middle"><%=messages.getText("travel.leave.date") %></th>
	    						<td align="right" valign="middle" >
	       							<%=(travelDataType.getHourLeave() != null) ? Util.formatHour(travelDataType.getHourLeave()) : "---" %>
								</td>
	                      	</tr>
							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            		<tr class="linetwo">
	              				<th align="left" valign="middle"><%=messages.getText("travel.distance.total")%></th>
	    						<td align="right" valign="middle" >
	                            	<%=(travelDataType.getDistance() == null) ? "---" : travelDataType.getDistance()%>
								</td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	              				<th align="left" valign="middle"><%=messages.getText("travel.arrive.hour")%></th>
	                            <td align="right">
	       							<%=(travelDataType.getHourArrive() != null) ? Util.formatHour(travelDataType.getHourArrive()) : "---" %>
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
					<td width="600" colspan="4">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("travel.rotogram")%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr class="linetwo">
								<th width="45%" align="left">
									<%=messages.getText("travel.rotogram.description")%>
								</th>
								<td>&nbsp;</td>
								<th width="45%" align="left">
									<%=messages.getText("travel.rotogram.denomination")%>
								</th>
							</tr>
							<tr class="linetwo">
								<td width="45%" align="left">
	                            	<%=(Util.empty(travelDataType.getRotogram())) ? "---" : travelDataType.getRotogram()%>
								</td>
								<td>&nbsp;</td>
								<td width="45%" align="left">
	                            	<%=(Util.empty(travelDataType.getLocalNames())) ? "---" : travelDataType.getLocalNames()%>
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
					<td width="600" colspan="4">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("travel.additionalcomment")%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr class="linetwo">
								<td width="85%" align="left">
	                            	<%=(Util.empty(travelDataType.getComment())) ? "---" : travelDataType.getComment()%>
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
<%				if(travelDataType.getConfirmedLeave() != null) {
%>				<tr>
					<td colspan="4" width="600">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("travel.checkpoint.confirmation")%>' />
		        		<table width='100%' border="0" cellpadding="0" cellspacing="0">
		            		<tr class="linetwo">
	              				<th align="left" valign="middle"><%=messages.getText("travel.checkpoint.date") %></th>
	       						<td align="left" class="linetwo">
	       							<%=Util.formatDate(travelDataType.getConfirmedLeave())%>
	       						</td>
								<td>&nbsp;</td>
	              				<th align="left" valign="middle"><%=messages.getText("travel.checkpoint.hour")%></th>
	                            <td align="left">
	       							<%=Util.formatHour(travelDataType.getConfirmedLeaveHour()) %>
							    </td>
							</tr>
   							<tr  class="space">
   								<th align="right">&nbsp;</th><td align="right">&nbsp;</td>
   							</tr>
		            		<tr class="linetwo">
	              				<th align="left" valign="middle" colspan="2"><%=messages.getText("travel.checkpoint.comments") %></th>
								<td colspan="3" align="right"><%=travelDataType.getLeaveComment()%></td>
							</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
	  			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
<%				Collection list = sys.getEventReportFacade().getTravelCheckPoints(travelDataType.getId(), sys.getUserSessionData());
				if(list.size() > 0) {
%>	  			<tr>
					<td colspan="3" width="600">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("travel.checkpoint.verification")%>' />
    					<table width="100%" border="0" id="drivers">
        					<tr align="center">
								<td width='20%'	class="guide2"><%=messages.getText("travel.checkpoint.date")%></td>
							    <td width='20%' class="guide2"><%=messages.getText("travel.checkpoint.resultincidents")%></td>
							    <td width='60%' class="guide2"><%=messages.getText("travel.checkpoint.comments")%></td>
							</tr>
<%					Collections.sort((List)list);
					it = list.iterator();
					while(it.hasNext()) {
						CheckPointData checkPoint = (CheckPointData)it.next();							
%>							<tr align="center">
								<td width='20%'	class="linetwo"><%=Util.formatDate(checkPoint.getDate())+" "+Util.formatHour(checkPoint.getHour())%></td>
							    <td width='20%' class="linetwo"><%=(checkPoint.isIncidents()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no")%></td>
							    <td width='60%' class="linetwo"><%=checkPoint.getComment()%></td>
							</tr>
<%					}
%>						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
	  			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
<%				}
			}
			if(travelDataType.getCloseDate() != null) {
%>				<tr>
					<td colspan="4" width="600">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("travel.close.destiny")%>' />
		        		<table width='100%' border="0" cellpadding="0" cellspacing="0">
		            		<tr class="linetwo">
	              				<th align="left" valign="middle"><%=messages.getText("travel.checkpoint.date") %></th>
	       						<td align="left" class="linetwo">
	       							<%=Util.formatDate(travelDataType.getCloseDate())%>
	       						</td>
								<td>&nbsp;</td>
	              				<th align="left" valign="middle"><%=messages.getText("travel.checkpoint.hour")%></th>
	                            <td align="right">
	       							<%=Util.formatHour(travelDataType.getCloseHour()) %>
							    </td>
							</tr>
   							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
   							<tr  class="linetwo">
   								<th align="left" colspan="5">
   									<%=(travelDataType.isTravelResult()) ? messages.getText("travel.checkpoint.resultincidents") : messages.getText("travel.checkpoint.resultok") %>
   								</th>
   							</tr>
   							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            		<tr class="linetwo">
	              				<th align="left" valign="middle" colspan="2"><%=messages.getText("travel.checkpoint.comments") %></th>
								<td colspan="3"><%=travelDataType.getCloseComment()%></td>
							</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
	  			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
				<tr>
					<td colspan="4" width="600">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("travel.close.registry")%>' />
		        		<table width='100%' border="0" cellpadding="0" cellspacing="0">
		            		<tr class="linetwo">
	              				<th align="left" valign="middle"><%=messages.getText("travel.checkpoint.date") %></th>
	       						<td align="right" class="linetwo">
	       							<%=Util.formatDate(travelDataType.getRegistryDate())%>
	       						</td>
								<td>&nbsp;</td>
	              				<th align="left" valign="middle"><%=messages.getText("travel.checkpoint.hour")%></th>
	                            <td align="right">
	       							<%=Util.formatHour(travelDataType.getRegistryHour()) %>
							    </td>
							</tr>
   							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            		<tr class="linetwo">
	              				<th align="left" valign="middle" colspan="2"><%=messages.getText("travel.checkpoint.comments") %></th>
								<td colspan="3"><%=travelDataType.getRegistryComment()%></td>
							</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
		 		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
<%			}
%>	  			<tr>			  		
			  		<jsp:include page='TravelViewOptions.jsp?travelId=<%=String.valueOf(id)%>' />
			  	</tr>
  				<tr>
					<jsp:include  page='<%="../component/spaceline.jsp?colspan=3" %>' />
			  	</tr>
			</table>
<%			}
		}	
	}catch(Exception e){
		e.printStackTrace();
	}
%>	
