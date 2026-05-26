<%@page language="java" 
	errorPage="../../exception.jsp"
	import="business.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="communication.datatype.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.activity.tables.*"
	import="communication.util.*"
	import="communication.driver.tables.*"
	import="communication.datatype.tables.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.ActivityTypeDataType"
	import="business.activity.ActivityReportFacade"
	import="business.datatype.*"
	import="java.util.*"	
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%!
	RequestDispatcher dispatcher; 
	Text messages; SystemAccess sys;
	UserSessionData userSessionData; 
	ActivityReportFacade activityReport;
	String idActivity, idDriver;
	String enabled;
	int columns;
	DriverMainAttributes driver;
	CEPAActivityScheduleRegistryData registryData;
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();	
	String check = (userSessionData.isDriver()) ? Util.checkPermission(sys,SecurityConstants.DRIVER_VIEW_OWN_DATA) : Util.checkPermission(sys,SecurityConstants.VIEW_CEPASCHEDULE_ACTIVITY);		
	if (check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		messages=sys.getText();
	
	
		// session.setAttribute("url","activity/schedule/CEPAActivityScheduleView.jsp");
		userSessionData.setUrl("activity/schedule/CEPAActivityScheduleView.jsp");
		
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		idActivity =(String)request.getParameter("idActivity");
		if(idActivity==null){
	idActivity=(String)session.getAttribute("id");			
		}
		// session.setAttribute("id",idActivity);
		idDriver = (String)request.getParameter("idDriver");
		if(idDriver==null){
		    idDriver=(String)session.getAttribute("idDriver");			
		}
		if(!Util.empty(idDriver)){
		    driver = (userSessionData.isDriver()) ? userSessionData.getDriverData() : sys.getDriverReportFacade().findDriverMainData(Integer.valueOf(idDriver),sys.getUserSessionData());
		}
		
		registryData = sys.getActivityReportFacade().getCompleteResult(new Integer(idActivity),new Integer(idDriver),sys.getUserSessionData());		
		
		Collection points = registryData.getActivity().getDefinition().getCheckList().getPoints();
		Collections.sort((List)points);
		Iterator pointIt = points.iterator();
%>

		<center>
	  		<table width="700" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
	    					<tr> 
	      						<td width="9">
							      	<img src="/datacenter/util/imgs/titulo_left.gif" width="9" height="40">
								</td>
	      						<th width="700" background="/datacenter/util/imgs/bg2.gif" align="center">
									<font color="#FFFFFF" size="4" face="Arial, Helvetica, sans-serif">
										<%=messages.getText("generic.activity.form.formulario")%>
	            					</font>
	      						</th>
	      						<td width="9">
							      	<img src="/datacenter/util/imgs/titulo_right.gif" width="9" height="40">
	      						</td>
	    					</tr>
	  					</table>
					</td>
				</tr>
				<tr>
		        	<td height="10"><img src="x" width="1" height="1"></td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<th class="linetwo" align="left"><%=messages.getText("generic.data.driver")+": "%></th>
								<td class="linetwo" align="left"><%=driver.getFirstName()+" "+driver.getLastName()%></td>
							</tr>
							<tr>
					        	<td height="5"><img src="x" width="1" height="1"></td>
							</tr>
							<tr>
								<th class="linetwo" align="left"><%=messages.getText("generic.activity.instructor")+": "%></th>
								<td class="linetwo" align="left"><%=registryData.getInstructor()%></td>
							</tr>
							<tr>
					        	<td height="5"><img src="x" width="1" height="1"></td>
							</tr>
							<tr>
								<th class="linetwo" align="left"><%=messages.getText("generic.vehicle.vehicledata.vehicletype")+": "%></th>
								<td class="linetwo" align="left"><%=messages.getText(registryData.getVehicleType())%></td>
							</tr>
							<tr>
					        	<td height="5"><img src="x" width="1" height="1"></td>
							</tr>
							<tr>
								<td colspan="2">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<th class="linetwo" align="left"><%=messages.getText("generic.vehicle.vehicledata.matricula")+": "%></th>
											<td class="linetwo" align="left"><%=registryData.getPlate()%></td>
								        	<td>&nbsp;</td>
											<th class="linetwo" align="left"><%=messages.getText("generic.vehicle.vehicledata.trade")+": "%></th>
											<td class="linetwo" align="left"><%=registryData.getTrade()%></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
					        	<td height="5"><img src="x" width="1" height="1"></td>
							</tr>
							<tr>
								<td colspan="2">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
								        	<td height="5"><img src="x" width="1" height="1"></td>
										</tr>
										<tr>
											<th class="linetwo" align="left"><%=messages.getText("generic.activity.form.tranningonthejob.initialHr")+": "%></th>
											<td class="linetwo" align="left"><%=Util.formatHour(registryData.getInitialHr())%></td>
								        	<td>&nbsp;</td>
											<th class="linetwo" align="left"><%=messages.getText("generic.activity.form.tranningonthejob.initialKm")+": "%></th>
											<td class="linetwo" align="left"><%=String.valueOf(registryData.getInitialKm())%></td>
										</tr>
										<tr>
								        	<td height="5"><img src="x" width="1" height="1"></td>
										</tr>
										<tr>
											<th class="linetwo" align="left"><%=messages.getText("generic.activity.form.tranningonthejob.finalHr")+": "%></th>
											<td class="linetwo" align="left"><%=Util.formatHour(registryData.getFinalHr())%></td>
								        	<td>&nbsp;</td>
											<th class="linetwo" align="left"><%=messages.getText("generic.activity.form.tranningonthejob.finalKm")+": "%></th>
											<td class="linetwo" align="left"><%=String.valueOf(registryData.getFinalKm())%></td>
										</tr>
									</table>
								</td>
							</tr>
	  					</table>
					</td>
				</tr>
				<tr>
		        	<td height="10"><img src="x" width="1" height="1"></td>
				</tr>
				<tr>
					<td align="center">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
<%
	for(int i = 0; i < 2; i++) {	
		PointData point = (PointData) pointIt.next();
%>								<td width="49%">
									<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
										<tr class="guide1">
											<td width="19" height="30" ><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
					   		  		  		<td height="30" class="guide1"><div align="left"><%=messages.getText(point.getText())%></div></td>
											<td width="19" height="30" ><img src="/datacenter/util/imgs/right.jpg" width="19" height="30"></td>
					  					</tr>
  										<tr>
											<td class="leftLine">&nbsp;</td>
        									<td height="6"><img src="x" width="1" height="1"></td>
        									<td class="rightLine">&nbsp;</td>
  										</tr>  					  					
    									<tr>
    										<td class="leftLine">&nbsp;</td>
    										<td>
												<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
													<tr>
														<td align="left" class="guide2" width="60%">
															&nbsp;
														</td>
														<td align="left" class="guide2" width="13%">
															<%=messages.getText("activity.commentarydrive.option1")%>
														</td>
														<td align="left" class="guide2" width="13%">
															<%=messages.getText("activity.commentarydrive.option2")%>
														</td>
														<td align="left" class="guide2" width="13%">
															<%=messages.getText("activity.commentarydrive.option3")%>
														</td>
													</tr>
<%
	Collection items = point.getItems();
		Collections.sort((List)items);
		Iterator itemIt = items.iterator();
		String line = "linetwo";
		while(itemIt.hasNext()) {
	ItemData item = (ItemData)itemIt.next();
	int value = registryData.getValueFromItem(item).intValue();
%>													<tr class='<%=line%>'>
														<td align="left" >
															<%=messages.getText(item.getText())%>
														</td>
														<td align="center">
															<%=(value == 1) ? "x" : ""%>
														</td>
														<td align="center">
															<%=(value == 2) ? "x" : ""%>
														</td>
														<td align="center">
															<%=(value == 3) ? "x" : ""%>
														</td>
													</tr>
<%
	line = (line.equals("linetwo")) ? "lineone" : "linetwo";
		}
%> 	   											</table>	
											</td>    									
    										<td class="rightLine">&nbsp;</td>
										</tr>	
						  				<tr>
						                  	<td height="11" valign="top"><img src="/datacenter/util/imgs/left_base.gif" width="19" height="11"></td>
						  				  	<td class="centerBase"></td>
						  				  	<td valign="top" height="11"><img src="/datacenter/util/imgs/right_base.gif" width="19" height="11"></td>
									 	</tr>
                      				</table>
    							</td>
<%
	if(i == 0) {
%>    							<td width="2%">&nbsp;</td>
<%
	}
	}
%>							</tr>
						</table>
					</td>
				</tr>
				<tr>
		        	<td height="10"><img src="x" width="1" height="1"></td>
				</tr>
<%
	while(pointIt.hasNext()) {
		PointData point = (PointData) pointIt.next();
%>				<tr>
					<td>
						<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
							<tr class="guide1">
								<td width="19" height="30" ><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
		   		  		  		<td height="30" class="guide1"><div align="left"><%=messages.getText(point.getText())%></div></td>
								<td width="19" height="30" ><img src="/datacenter/util/imgs/right.jpg" width="19" height="30"></td>
		  					</tr>
							<tr>
								<td class="leftLine">&nbsp;</td>
     							<td height="6"><img src="x" width="1" height="1"></td>
     							<td class="rightLine">&nbsp;</td>
							</tr>  					  					
 							<tr>
 								<td class="leftLine">&nbsp;</td>
 								<td>
									<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
										<tr>
											<td align="left" class="guide2" width="60%">
												&nbsp;
											</td>
											<td align="left" class="guide2" width="13%">
												<%=messages.getText("activity.commentarydrive.option1")%>
											</td>
											<td align="left" class="guide2" width="13%">
												<%=messages.getText("activity.commentarydrive.option2")%>
											</td>
											<td align="left" class="guide2" width="13%">
												<%=messages.getText("activity.commentarydrive.option3")%>
											</td>
										</tr>
<%
	Collection items = point.getItems();
		Collections.sort((List)items);
		Iterator itemIt = items.iterator();
		String line = "linetwo";
		while(itemIt.hasNext()) {
	ItemData item = (ItemData)itemIt.next();
	int value = registryData.getValueFromItem(item).intValue();
%>										<tr class='<%=line%>'>
											<td align="left" >
												<%=messages.getText(item.getText())%>
											</td>
											<td align="center">
												<%=(value == 1) ? "x" : ""%>
											</td>
											<td align="center">
												<%=(value == 2) ? "x" : ""%>
											</td>
											<td align="center">
												<%=(value == 3) ? "x" : ""%>
											</td>
										</tr>
<%
	line = (line.equals("linetwo")) ? "lineone" : "linetwo";
		}
%>										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td align="left" class="linetwo" width="60%" colspan="4">
												<table width="100%" border="0">
													<tr>
														<th class="linetwo" align="left">
															<%=messages.getText("generic.activity.form.commentarydrive.comments")%>
														</th>
														<td>&nbsp;</td>
														<td class="linetwo" align="left" width="100%">
															<%=registryData.getPointComment(point)%>
														</td>
													</tr>
												</table>
											</td>
										</tr>
  									</table>	
								</td>    									
								<td class="rightLine">&nbsp;</td>
							</tr>	
			  				<tr>
			                  	<td height="11" valign="top"><img src="/datacenter/util/imgs/left_base.gif" width="19" height="11"></td>
			  				  	<td class="centerBase"></td>
			  				  	<td valign="top" height="11"><img src="/datacenter/util/imgs/right_base.gif" width="19" height="11"></td>
						 	</tr>
           				</table>
					</td>
				</tr>	
				<tr>
		        	<td height="10"><img src="x" width="1" height="1"></td>
				</tr>
<%
	}
%>				<tr>
					<td align="center">
						<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
							<tr class="guide1">
								<td width="19" height="30" ><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
		   		  		  		<td height="30" class="guide1"><div align="left"><%=messages.getText("generic.messages.observations")%></div></td>
								<td width="19" height="30" ><img src="/datacenter/util/imgs/right.jpg" width="19" height="30"></td>
		  					</tr>
							<tr>
								<td class="leftLine">&nbsp;</td>
     							<td height="6"><img src="x" width="1" height="1"></td>
     							<td class="rightLine">&nbsp;</td>
							</tr>  					  					
 							<tr>
 								<td class="leftLine">&nbsp;</td>
 								<td>
			    					<table width="100%" border="0" cellpadding="0" cellspacing="0">
			    						<tr>
					       					<td valign="top" align="left" style="width:85%" >
					       						<font size="1" face="Arial, Helvetica, sans-serif"> 
													<%=(registryData != null && registryData.getObs() != null) ? registryData.getObs() : ""%>
												</font>	        	
					        				</td> 		             
					    				</tr>
					    			</table>
			        			</td>        
								<td class="rightLine">&nbsp;</td>
							</tr>	
			  				<tr>
			                  	<td height="11" valign="top"><img src="/datacenter/util/imgs/left_base.gif" width="19" height="11"></td>
			  				  	<td class="centerBase"></td>
			  				  	<td valign="top" height="11"><img src="/datacenter/util/imgs/right_base.gif" width="19" height="11"></td>
						 	</tr>
           				</table>
			    	 </td>
			   	</tr>
				<tr>
		        	<td height="10"><img src="x" width="1" height="1"></td>
				</tr>
				<tr>
		        	<td height="10" align="right">
<%
	if(Util.checkPermission(sys,SecurityConstants.CREATE_CEPASCHEDULEACTIVITYREGISTRY) == null) {
%>		        		<input type="button" value='<%=messages.getText("generic.messages.edit")%>' class="input" onclick="javascript:document.forms['edit'].submit();" />
<%			}
%>		        		<input type="button" value='<%=messages.getText("generic.messages.cancel")%>' class="input" onclick="javascript:document.forms['view'].submit();" />
					</td>
				</tr>
			</table>
		</center>

<%			if(userSessionData.isDriver()) {
%>      <form name="view" method="post" action="layout.jsp?refer=/driver/DriverView.jsp"> </form>
<%			}else {        
%>  	<form name="view" method="post" action='layout.jsp?refer=/activity/schedule/CEPAActivityScheduleView.jsp?idActivity=<%=idActivity %>'> </form>
<%			}
%>		<form name="edit" method="post" action='<%="layout.jsp?refer=/activity/schedule/AddCEPAActivityScheduleRegistry.jsp?idActivity="+idActivity+"&idDriver="+idDriver%>'> </form>
<%	}
%>

