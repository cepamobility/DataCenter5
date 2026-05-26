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
		
		CheckListData checkList = (registryData == null) ? activityReport.findCheckList(new Integer(CheckListData.COMMENTARY_DRIVE_NOVARTIS),userSessionData) : registryData.getActivity().getDefinition().getCheckList();	
		Collection points = checkList.getPoints();
		Collections.sort((List)points);
		Iterator pointIt = points.iterator();
%>

	<html:form action = "/CDNovartisRegistryCreate" method="post">
		
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
								<td class="linetwo" align="left"><%=driver.getFirstName()+" "+driver.getLastName()%></td>
							</tr>
							<tr>
					        	<td height="5"><img src="x" width="1" height="1"></td>
							</tr>
							<tr>
								<th class="linetwo" align="left"><%=messages.getText("generic.activity.instructor")+": "%></th>
								<td class="linetwo" align="left">
									<html:text property="instructor" value='<%=instructor%>' style="width:250;" styleClass="input"/>	
								</td>
							</tr>
	  					</table>
					</td>
				</tr>
				<tr>
		        	<td height="10"><img src="x" width="1" height="1"></td>
				</tr>
<%			while(pointIt.hasNext()) {
				PointData point = (PointData) pointIt.next();
				String comment = registryData.getPointComment(point);
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
											<td align="left" class="guide2" width="74%">
												&nbsp;
											</td>
											<td align="left" class="guide2" width="13%">
												<%=messages.getText("activity.commentarydrive.option1")%>
											</td>
											<td align="left" class="guide2" width="13%">
												<%=messages.getText("activity.commentarydrive.option2")%>
											</td>
										</tr>
<%				Collection items = point.getItems();
				Collections.sort((List)items);
				Iterator itemIt = items.iterator();
				String line = "linetwo";
				while(itemIt.hasNext()) {
					ItemData item = (ItemData)itemIt.next();
					Integer valueItem = registryData.getValueFromItem(item);
					int value = (valueItem == null) ? 0 : valueItem.intValue();
%>										<tr class='<%=line%>'>
											<td align="left" >
												<%=messages.getText(item.getText()) %>
											</td>
											<td align="center">
												<input type="radio" name='<%="item"+String.valueOf(item.getId())%>' value="1" <%=(value == 1) ? "checked" : "" %>/>
											</td>
											<td align="center">
												<input type="radio" name='<%="item"+String.valueOf(item.getId())%>' value="2" <%=(value == 2) ? "checked" : "" %>/>
											</td>
										</tr>
<%					line = (line.equals("linetwo")) ? "lineone" : "linetwo";
				}
%>										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td align="left" class="linetwo" width="60%" colspan="4">
												<table width="100%" border="0">
													<tr>
														<th class="linetwo" align="left" valign="top">
															<%=messages.getText("generic.activity.form.commentarydrive.observations") %>
														</th>
														<td class="linetwo">&nbsp;</td>
														<td class="linetwo" align="left" width="100%">
															<textarea name='<%="pointComment"+String.valueOf(point.getId())%>' style="width:80%" rows="2"><%=(comment == null) ? "" : comment%></textarea>
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
<%			}
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
					       					<td valign="top" align="left" style="width:85%" class="linetwo">
					       						<font size="1" face="Arial, Helvetica, sans-serif"> 
													<html:textarea property="obs" style="width:80%" rows="3"  value='<%=(registryData != null && registryData.getObs() != null) ? registryData.getObs() : "" %>'></html:textarea>
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

