<%@page import="org.apache.struts.action.DynaActionForm"%>
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
	import="org.apache.struts.action.DynaActionForm"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%!
	RequestDispatcher dispatcher; 
	Text messages; SystemAccess sys;
	UserSessionData userSessionData; 
	ActivityReportFacade activityReport;
	String idActivity, idDriver, url;
	String enabled;
	int columns;
	DriverMainAttributes driver;
	CEPAActivityScheduleRegistryData registryData;
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();	
	String check = Util.checkPermission(sys,SecurityConstants.CREATE_CEPASCHEDULEACTIVITYREGISTRY);	
	String check1 = Util.checkPermission(sys,SecurityConstants.CREATE_CD_ACTIVITY);		
	if ((check!=null) && (check1!=null)) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		messages=sys.getText();
	
	
		// session.setAttribute("url","activity/schedule/CEPAActivityScheduleView.jsp");
		url = "activity/schedule/CEPAActivityScheduleView.jsp";
		userSessionData.setUrl(url);
		
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		idActivity =(String)request.getParameter("idActivity");
		if(idActivity==null){
	idActivity=(String)session.getAttribute("id");			
		}
		// session.setAttribute("id",idActivity);
		
		idDriver = (String)request.getParameter("idDriver");
		if(idDriver==null){
		    idDriver=(String)session.getAttribute("driver");			
		}
		if(!Util.empty(idDriver)){
	driver = sys.getDriverReportFacade().findDriverMainData(Integer.valueOf(idDriver),sys.getUserSessionData());
		}
		
		ActivityReportFacade activityReport = sys.getActivityReportFacade();
		registryData = activityReport.getCompleteResult(new Integer(idActivity),new Integer(idDriver),sys.getUserSessionData());		
		String instructor = "";
		String vehicleType = "";
		String plate = "";
		String trade = "";
		String initialHr = "";
		String initialMinute = "";
		String finalHr = "";
		String finalMinute = "";
		String initialKm = "";
		String finalKm = "";
		if(session.getAttribute("ActivityRegistryForm") != null) {
		    DynaActionForm form = (DynaActionForm)session.getAttribute("ActivityRegistryForm");
		    instructor = form.getString("instructor");
		    vehicleType = form.getString("vehicleType");
		    plate = form.getString("plate");
		    trade = form.getString("trade");
	initialHr = form.getString("initialHr");
	
	initialMinute = form.getString("initialMinute");
	finalHr = form.getString("finalHr");
	finalMinute = form.getString("finalMinute");
		}else {
		    instructor = registryData.getInstructor();
		    vehicleType = (registryData.getVehicleType() != null) ? registryData.getVehicleType() : "";
		    plate = (registryData.getPlate() != null) ? registryData.getPlate() : "";
		    trade = registryData.getTrade();
		    Calendar c = Calendar.getInstance();
	if(registryData.getInitialHr() != null) {
	    c.setTime(registryData.getInitialHr());
	    initialHr = (registryData.getInitialHr() != null) ? String.valueOf(c.get(Calendar.HOUR_OF_DAY)) : "";
	    initialMinute = (registryData.getInitialHr() != null) ? String.valueOf(c.get(Calendar.MINUTE)) : "";
	}
	if(registryData.getFinalHr() != null) {
	    c.setTime(registryData.getFinalHr());
	    finalHr = (registryData.getFinalHr() != null) ? String.valueOf(c.get(Calendar.HOUR_OF_DAY)) : "";
	    finalMinute = (registryData.getFinalHr() != null) ? String.valueOf(c.get(Calendar.MINUTE)) : "";
	}
		}
		
		DataTypeReportFacade reportDataType = sys.getDataTypeReportFacade();
		
		CheckListData checkList = (registryData == null) ? activityReport.findCheckList(new Integer(CheckListData.COMMENTARY_DRIVE_NALCO),userSessionData) : registryData.getActivity().getDefinition().getCheckList();	
		Collection points = checkList.getPoints();
		Collections.sort((List)points);
		Iterator pointIt = points.iterator();
%>

	<html:form action = "/CDNalcoRegistryCreate" method="post">
		
		<html:hidden property="idDriver" value='<%=idDriver%>' />
		<html:hidden property="idActivity" value='<%=idActivity%>' />
		<html:hidden property="url" value='<%=url%>' />		
		<html:hidden property="checkboxParamList" value="checkboxParamList" />
		<html:hidden property="note" value="note" />	
		<html:hidden property="partner" value="partner" />	
		<html:hidden property="cepaCourse" value="cepaCourse" />	
		<html:hidden property="conductionType" value="conductionType" />
		<html:hidden property="skidcar" value="skidcar" />	
		<html:hidden property="pointCommentList" value="pointCommentList" />
		<html:hidden property="circulationAreaHighWay" value="false" />
		<html:hidden property="circulationAreaRoad" value="false" />	
		<html:hidden property="circulationAreaSubUrban" value="false" />	
		<html:hidden property="circulationAreaUrban" value="false" />											
		<html:hidden property="velocityBox" value="" />											
		<html:hidden property="light" value="" />
		<html:hidden property="commentaryDriveOther" value="commentaryDriveOther" />					
		<html:hidden property="frenaje"/>		
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
								<td class="linetwo" align="right"><%=driver.getFirstName()+" "+driver.getLastName()%></td>
							</tr>
							<tr>
					        	<td height="5"><img src="x" width="1" height="1"></td>
							</tr>
							<tr>
								<th class="linetwo" align="left"><%=messages.getText("generic.activity.instructor")+": "%></th>
								<td class="linetwo" align="right">
									<html:text property="instructor" value='<%=instructor%>' style="width:250;" styleClass="input"/>	
								</td>
							</tr>
							<tr>
					        	<td height="5"><img src="x" width="1" height="1"></td>
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
							
<%			while(pointIt.hasNext()) {	
				PointData point = (PointData) pointIt.next();				
%>							<tr>
								<td width="100%">
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
<%				Collection items = point.getItems();
				Collections.sort((List)items);
				Iterator itemIt = items.iterator();
				String line = "linetwo";
				boolean header1 = false;
				boolean header2 = false;
				while(itemIt.hasNext()) {
					ItemData item = (ItemData)itemIt.next();
					Integer valueItem = registryData.getValueFromItem(item);
					int value = (valueItem == null) ? 0 : valueItem.intValue();
					switch(item.getType()) {
						case 1:
%>										<tr>
    										<td class="leftLine">&nbsp;</td>
    										<td>
												<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
													<tr class='<%=line%>'>
														<th align="left" width="50%">
															<%=messages.getText(item.getText()) %>
														</th>
														<td align="center" width="25%">
															<input type="radio" name='<%="item"+String.valueOf(item.getId())%>' value="1" <%=(value == 1) ? "checked" : "" %>/><%=messages.getText("generic.messages.yes")%>
														</td>
														<td align="center" width="25%">
															<input type="radio" name='<%="item"+String.valueOf(item.getId())%>' value="2" <%=(value == 2) ? "checked" : "" %>/><%=messages.getText("generic.messages.no")%>
														</td>
													</tr>
													<tr height="15"><td></td></tr>
												</table>
											</td>
    										<td class="rightLine">&nbsp;</td>
										</tr>	
<%							break;
						case 2:
%>										<tr>
											<td class="leftLine">&nbsp;</td>
											<td>
												<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
<%							if(!header1) {
								header1 = true;
%>													<tr class='<%=line%>'>
														<th align="left" colspan="3">
															<%=messages.getText("Qué se revisó ?") %>
														</th>
													</tr>
<%							}
%>												  				
													<tr class='<%=line%>'>
														<td align="left"  width="50%">
															<%=messages.getText(item.getText()) %>
														</td>
														<td align="center" width="25%">
															<input type="radio" name='<%="item"+String.valueOf(item.getId())%>' value="1" <%=(value == 1) ? "checked" : "" %>/><%=messages.getText("generic.messages.yes")%>
														</td>
														<td align="center" width="25%">
															<input type="radio" name='<%="item"+String.valueOf(item.getId())%>' value="2" <%=(value == 2) ? "checked" : "" %>/><%=messages.getText("generic.messages.no")%>
														</td>
													</tr>
												</table>
											</td>
											<td class="rightLine">&nbsp;</td>
										</tr>	
<%							break;
						case 3:
							if(!header2) {
								header2 = true;
%>										<tr>
											<td class="leftLine">&nbsp;</td>
											<td>
												<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
													<tr>
														<td align="left" class="guide2" width="60%">
															<%=messages.getText("activity.commentarydrivenalco.option0")%>
														</td>
														<td align="left" class="guide2" width="20%">
															<%=messages.getText("activity.commentarydrivenalco.option1")%>
														</td>
														<td align="left" class="guide2" width="20%">
															<%=messages.getText("activity.commentarydrivenalco.option2")%>
														</td>
													</tr>
<%							}
%>
													<tr class='<%=line%>'>
														<td align="left"  width="60%">
															<%=messages.getText(item.getText()) %>
														</td>
														<td align="center" width="20%">
															<input type="radio" name='<%="item"+String.valueOf(item.getId())%>' value="1" <%=(value == 1) ? "checked" : "" %>/>
														</td>
														<td align="center" width="20%">
															<input type="radio" name='<%="item"+String.valueOf(item.getId())%>' value="2" <%=(value == 2) ? "checked" : "" %>/>
														</td>
													</tr>
													<tr height="15"><td></td></tr>
<%							break;
						case 4:
%>
												</table>
											</td>
											<td class="rightLine">&nbsp;</td>
										</tr>	
										<tr>
											<td class="leftLine">&nbsp;</td>
											<td>
												<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
												    <tr class='<%=line%>'>
														<td align="left"  width="60%">
															<%=messages.getText(item.getText()) %>
														</td>
<%							for(int index = 1; index < 5; index++) {
%>														<td align="center" width="10%">
															<input type="radio" name='<%="item"+String.valueOf(item.getId())%>' value="<%=String.valueOf(index)%>" <%=(value == index) ? "checked" : "" %>><%=String.valueOf(index)%></input>
														</td>
<%							}
%>													</tr>
												</table>
											<td class="rightLine">&nbsp;</td>
										</tr>	
	
<%							break;
					}
					line = (line.equals("linetwo")) ? "lineone" : "linetwo"; 
				}
%>
						  				<tr>
						                  	<td height="11" valign="top"><img src="/datacenter/util/imgs/left_base.gif" width="19" height="11"></td>
						  				  	<td class="centerBase"></td>
						  				  	<td valign="top" height="11"><img src="/datacenter/util/imgs/right_base.gif" width="19" height="11"></td>
									 	</tr>
                      				</table>
    							</td>
    						</tr>
<%			}
%>							
						</table>
					</td>
				</tr>
				<tr>
		        	<td height="10"><img src="x" width="1" height="1"></td>
				</tr>
				<tr>
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
													<html:textarea property="obs" style="width:80%" rows="3" styleClass="input" value='<%=(registryData != null && registryData.getObs() != null) ? registryData.getObs() : "" %>'></html:textarea>
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
		        		<html:submit value='<%=messages.getText("generic.messages.save")%>' styleClass="input"/>
		        		<html:cancel value='<%=messages.getText("generic.messages.cancel")%>' styleClass="input"/>
					</td>
				</tr>
			</table>
		</center>
	</html:form>

  
  <form name="FormCD" method="post" action='layout.jsp?refer=/activity/schedule/CEPAActivityScheduleView.jsp?idActivity=<%=idActivity %>' />
<%	}
%>

