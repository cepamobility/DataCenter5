<%@page language="java" 
    errorPage="../exception.jsp"
	import="business.*"
	import="business.datatype.*"
	import="business.vehicle.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.travel.*"
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
	String pathLanguage; Text messages; Integer driverId; String driverName; String driverLastName;
	TravelForm travelForm;TravelDataType travelDataType;
	UserSessionData userSessionData;
	String action;Integer eventId;
	int corporationId;
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
	corporationId=sys.getUserSessionData().getFilter().getCorporationId().intValue();
	String check = null;
	String type = request.getParameter("type");
	
	if(sys.getUserSessionData().isDriver()) {
		if(type.equals("supervise")) {
	check = "/error.jsp";
		}else if(type.equals("edit")) {
		    check = Util.checkPermission(sys,SecurityConstants.DRIVER_MODIFY_EVENT);
		}else {
		    check = Util.checkPermission(sys,SecurityConstants.DRIVER_CREATE_EVENT);
		}
	}else {
		if(type.equals("supervise")) {
	check = Util.checkPermission(sys,SecurityConstants.SUPERVISE_EVENT);
		}else if(type.equals("edit")) {
	check = Util.checkPermission(sys,SecurityConstants.MODIFY_EVENT);
		}else {
		    check = Util.checkPermission(sys,SecurityConstants.CREATE_EVENT);
		}
	}
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		String accidentCollision="";
		context=request.getContextPath();
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
	
		messages=sys.getText();
	
		OptionItem emptyObject = new OptionItem(messages.getText("generic.messages.select"),"");
		OptionItem noDataObject = new OptionItem(messages.getText("generic.messages.nodata"),"generic.messages.nodata");
		OptionItem noApplyObject = new OptionItem(messages.getText("generic.messages.notapply"),"generic.messages.notapply");
		DriverData driver = null;
		
		String form = "TravelForm";
		if(type.equals("edit")) {
			Integer id = null;
		    action = "/Traveledit";
		    form = "TravelEditForm";
		    if(Util.empty(request.getParameter("id"))) {
	   		 	id = (Integer)session.getAttribute("id");
		    }else {
		    	id = new Integer(request.getParameter("id"));
		    }
		    travelDataType = sys.getEventReportFacade().findTravelByPrimaryKey(id,userSessionData);
		    driver = travelDataType.getDriver();
		    travelForm = new TravelForm(travelDataType,sys.getUserSessionData());
		    session.setAttribute("TravelEditForm",travelForm);
		}else {
		    action = "/Travelcreate";
		    if((session.getAttribute("TravelForm") != null)&&((request.getParameter("vehicle")==null)||(!request.getParameter("vehicle").equals("-1")))) {
		    	travelForm = (TravelForm)session.getAttribute("TravelForm");
				if(sys.getUserSessionData().isDriver()) {
				    driver = sys.getUserSessionData().getDriverData();
				    driverId = driver.getId();
				}else {
				    driverId = new Integer(travelForm.getDriverid());
				    driver = sys.getDriverReportFacade().findDriverByPrimaryKey(driverId,sys.getUserSessionData());
				}
			}else {
				if(sys.getUserSessionData().isDriver()) {
				    driver = sys.getUserSessionData().getDriverData();
				    driverId = driver.getId();
				}else {
				    driverId = new Integer(request.getParameter("id"));
				    driver = sys.getDriverReportFacade().findDriverByPrimaryKey(driverId,sys.getUserSessionData());
				}
				travelForm = new TravelForm(driver);
				session.setAttribute("EventForm",travelForm);
			}	    
			if((request.getParameter("vehicle") != null)&&(!request.getParameter("vehicle").equals("-1"))) {
				travelForm.setVehicleId(request.getParameter("vehicle"));
			}
		}
		if(!Util.empty(travelForm.getDriverid())) {
	
	session.setAttribute("url","/event/TravelCreate.jsp?id="+String.valueOf(driverId)+"&vehicle="+String.valueOf(travelForm.getVehicleId()));
	
	DataTypeUtil dataTypeUtil = new DataTypeUtil(sys);
	session.setAttribute("trades",dataTypeUtil.getTradeDataType(true));
%>




<%@page import="communication.util.Filter"%><script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
</script>

<script language="javascript" src='./menu/js/refactors.js' type='text/javascript' >
</script>

	<html:form action='<%=action%>' >
<%			if(type.equals("edit")) {
%>		<html:hidden property="id" value='<%=String.valueOf(travelDataType.getId())%>' />
<%			}
%>			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
<%      	if(type.equals("edit") || type.equals("supervise")) { 
%> 				<tr class="guide1">
  					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.data.travel")%>' />
			  	</tr>
<%      	}else{
%>				<tr class="guide1">
  					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.messages.addtravel")%>' />
			  	</tr>
<%			} 
%>				<tr>
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
	              							<th align="left" valign="middle"><%=messages.getText("travel.driver.name")%></th>
	    									<td align="right" valign="middle" >			
                       							<html:hidden property="driverid" value="<%=String.valueOf(driver.getId())%>"/>
                       							<%=driver.getFirstName()+" "+driver.getLastName()%>
											</td>
	                         			</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            					<tr class="linetwo">
	              							<th align="left" valign="middle"><%=messages.getText("travel.driver.mobile")%></th>
	                            			<td align="right">
                       							<html:text property="mobile" styleClass="input" />
							    			</td>
	                         			</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            					<tr class="linetwo">
	              							<th align="left" valign="middle"><%=messages.getText("travel.driver.charge")%></th>
	    									<td align="right" valign="middle" >			
	    										<html:text property="state" styleClass="input" />
											</td>
	                         			</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            					<tr class="linetwo">
	              							<th align="left" valign="middle"><%=messages.getText("travel.driver.other")%></th>
	                            			<td align="right">
	                            				<html:text property="collisionangle" styleClass="input"></html:text>
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
                    	<table width="100%">
                    		<tr>
                     			<td>
			                    	<jsp:include page='<%="/util/divorg.jsp?form="+form+"&consult=false"%>' />
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
				<tr>
					<td colspan="3" width="600">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title=Veículo" %>'/>
<%			if(Util.empty(travelForm.getVehicleId())) { 
%>						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="270" class="linetwo">
									<table width="100%">
										<tr>
										   	<th align="left" class="linetwo"><%=messages.getText("travel.vehicle.plate")%><span class="required">*</span></th>
				         					<td align="right"><html:text property="vehicleplate" size="25" styleClass="input"/></td>
			         					</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo"><%=messages.getText("travel.vehicle.trade")%><span class="required">*</span> </th>
				         					<td align="right">
                								<html:select property="vehicletrade" styleClass="input" style="width:155;">
                  									<html:options collection="trades" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
				         					</td>
										</tr>
									</table>
								</td>
								<td width="16"></td>
								<td width="270">
									<table width="100%">
										<tr>
										   	<th align="left" class="linetwo"><%=messages.getText("travel.vehicle.model")%><span class="required">*</span></th>
				         					<td align="right"><html:text property="vehiclemodel" size="25" styleClass="input"/></td>
										</tr>
		      							<tr  class="space">
		        							<th align="right">&nbsp;</th>
		        							<td align="right">&nbsp;</td>
		      							</tr>
										<tr>
										   	<th align="left" class="linetwo"><%=messages.getText("travel.vehicle.color")%></th>
				         					<td align="right">
                								<html:text property="vehicleyear" styleClass="input" />
                							</td>
										</tr>				  
									</table>
								</td>
							</tr>
       					</table>   

 
<%			}else {
    			vehicle = sys.getVehicleReportFacade().findVehicleById(new Integer(travelForm.getVehicleId()),sys.getUserSessionData());
%>						<html:hidden property="vehicleId" value='<%=request.getParameter("vehicle")%>' />
						<table>
							<tr>
								<td width="270" class="linetwo">
									<table width="100%">
										<tr>
										   	<th align="left" class="linetwo"><%=messages.getText("travel.vehicle.plate")%></th>
				         					<td align="right" class="linetwo"><%=vehicle.getMatricula()%></td>
			         					</tr>
		      							<tr  class="space">
		        							<th align="right">&nbsp;</th>
		        							<td align="right">&nbsp;</td>
		      							</tr>
										<tr>
										   	<th align="left" class="linetwo"><%=messages.getText("travel.vehicle.trade")%></th>
				         					<td align="right" class="linetwo"><%=messages.getText(vehicle.getTrade().getResourceBundleId())%></td>
										</tr>
									</table>
								</td>
								<td width="16"></td>
								<td width="270">
									<table width="100%">
										<tr>
										   	<th align="left" class="linetwo"><%=messages.getText("travel.vehicle.model")%></th>
				         					<td align="right" class="linetwo"><%=vehicle.getModel()%></td>
										</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo"><%=messages.getText("travel.vehicle.color")%></th>
				         					<td align="right" class="linetwo"><%=vehicle.getColour()%></td>
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
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title=Itinerário Previsto" %>' />
		        		<table width='100%' border="0" cellpadding="0" cellspacing="0">
		            		<tr class="linetwo">
	              				<th align="left" valign="middle"><%=messages.getText("travel.from.place")%></th>
	    						<td align="right" valign="middle" >
                       				<html:text property="localLeave"  styleClass="input"/>
								</td>
								<td>&nbsp;</td>
	              				<th align="left" valign="middle"><%=messages.getText("travel.to.place")%></th>
	                            <td align="right">
                       				<html:text property="localArrive"  styleClass="input"/>
							    </td>
	                      	</tr>
							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            		<tr class="linetwo">
	              				<th align="left" valign="middle"><%=messages.getText("travel.leave.date") %></th>
	       						<td align="right" class="linetwo" width="25%">
	       							<jsp:include  page='<%="../component/dateComponent.jsp?index=1&day=date&month=month&year=year&form="+form%>' />
			          			</td>
								<td>&nbsp;</td>
	              				<th align="left" valign="middle"><%=messages.getText("travel.stop.schedule")%></th>
	                            <td align="right">
                       				<html:text property="stops"  styleClass="input"/>
							    </td>
								<td>&nbsp;</td>
	                      	</tr>
							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            		<tr class="linetwo">
	              				<th align="left" valign="middle"><%=messages.getText("travel.leave.hour") %></th>
	    						<td align="right" valign="middle" >
									<html:text property="hour" style="width:23;" styleClass="input"/>&nbsp;:&nbsp;
		        					<html:text property="minute" style="width:23;" styleClass="input"/>
								</td>
	                      	</tr>
							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            		<tr class="linetwo">
	              				<th align="left" valign="middle"><%=messages.getText("travel.distance.total")%></th>
	    						<td align="right" valign="middle" >
									<html:text property="distance" style="width:50;" styleClass="input"/>
								</td>
								<td>&nbsp;</td>
	              				<th align="left" valign="middle"><%=messages.getText("travel.arrive.hour")%></th>
	                            <td align="right">
									<html:text property="hour2" style="width:23;" styleClass="input"/>&nbsp;:&nbsp;
		        					<html:text property="minute2" style="width:23;" styleClass="input"/>
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
							<tr>
								<td width="45%" align="center">
									<html:textarea property="othervehicles" cols="55" rows="3"  styleClass="input"/>
								</td>
								<td>&nbsp;</td>
								<td width="45%" align="center">
									<html:textarea property="supervisionObservations" cols="55" rows="3"  styleClass="input"/>
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
							<tr>
								<td width="85%" align="center">
									<html:textarea property="description" cols="100" rows="5"  styleClass="input"/>
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
						 				<html:cancel styleClass="input"><%=messages.getText("generic.messages.cancel")%></html:cancel >
				          			</div>
				          		</td>
				  			</tr>
						</table>
	  				</td>
	  			</tr>
			</table>
		</html:form>
<%			}
		}	
	}catch(Exception e){
		e.printStackTrace();
	}
%>	
