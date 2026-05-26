
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
	String line = "linetwo";
%>

	<html:form action = "/ActivityRenovation" method="post">
		
		<html:hidden property="idDriver" value='<%=idDriver%>' />
		<html:hidden property="idActivity" value='<%=idActivity%>' />
		<html:hidden property="url" value='<%=url%>' />		
		<html:hidden property="checkboxParamList" value="checkboxParamList" />
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
	  		<table width="850" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center" colspan="3">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
	    					<tr> 
	      						<td width="9">
							      	<img src="/datacenter/util/imgs/titulo_left.gif" width="9" height="40">
								</td>
	      						<th width="832" background="/datacenter/util/imgs/bg2.gif" align="center">
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
					<td colspan="3">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<th class="linetwo" align="left"><%=messages.getText("generic.data.driver")+": "%></th>
								<td class="linetwo" align="left"><%=driver.getFirstName()+" "+driver.getLastName()%></td>
							</tr>
							<tr>
					        	<td height="5"><img src="x" width="1" height="1"></td>
							</tr>
							<tr>
								<th class="linetwo" align="left"><%=(registryData.getActivity().getType().getResourceBundleId().equals(CEPAActivityConstants.INITIAL_FOREIGN_EVALUATION)) ? messages.getText("generic.activity.multiplier")+": " : messages.getText("generic.activity.instructor")+": "%></th>
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
				
				
<%	//RECONOCIMIENTO PREVIO DEL VEHICULO PRIMER POINT
%>
			<tr>
				<td align="center" colspan="3">
					<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0"> 
						<tr>
<%			if(pointIt.hasNext()) {
				PointData point = (PointData) pointIt.next();
				String comment = registryData.getPointComment(point);
				String pointTxt = (point.getText().equals("commentaryrenovation.jj.point1") && registryData.getActivity().getType().getResourceBundleId().equals(CEPAActivityConstants.INITIAL_FOREIGN_EVALUATION)) ? "initialforeignevaluation.point1" : point.getText(); 
%>									
							<tr class="guide1">
								<td width="19" height="30" ><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
		   		  		  		<td height="30" class="guide1"><div align="left"><%=messages.getText(pointTxt)%></div></td>
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
<%				Collection items = point.getItems();
				Collections.sort((List)items);
				Iterator itemIt = items.iterator();
				while(itemIt.hasNext()) {
					ItemData item = (ItemData)itemIt.next();
					Integer valueItem = registryData.getValueFromItem(item);
					int value = (valueItem == null) ? 0 : valueItem.intValue();//0  ""
					String itemTxt = (item.getText().equals("commentaryrenovation.jj.point1.item6") && registryData.getActivity().getType().getResourceBundleId().equals(CEPAActivityConstants.INITIAL_FOREIGN_EVALUATION)) ? "initialforeignevaluation.point1.item6" : item.getText(); 
%>											<td>
												<table cellpadding="0" cellspacing="0">
													<tr class="<%=line%>">
														<td align="right">
															<input type="radio" name='<%="item"+String.valueOf(item.getId())%>' value="1" <%=(value == 1) ? "checked" : "" %>/><%=messages.getText("generic.messages.yes") %>
														</td>
														<td align="left">
															<input type="radio" name='<%="item"+String.valueOf(item.getId())%>' value="2" <%=(value == 2) ? "checked" : "" %>/><%=messages.getText("generic.messages.no") %>
														</td>
													</tr>
													<tr class="<%=line%>">
														<td align="center" colspan="2">
															<%=messages.getText(itemTxt) %>
														</td>
													</tr>
												</table>
											</td>
<%					line = (line.equals("linetwo")) ? "lineone" : "linetwo";
				}
%>										</tr>
										<tr>
											<td align="left" class="linetwo" width="60%" colspan="6">
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
<%			}
			
%>
			</tr>	
			<tr>
		        	<td height="10"><img src="x" width="1" height="1"></td>
			</tr>
			
			<tr>
<%//COMIENZA ANTES DE SALIR Y USO DE LOS COMANDOS 				
			for(int i = 0; i < 2; i++) {
				PointData point = (PointData) pointIt.next();
				String comment = registryData.getPointComment(point);
%>					
			
				<td width="49%" valign="top">
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
											<td align="left" class="guide2" width="70%">
												&nbsp;
											</td>
											<td align="center" class="guide2" width="15%">
												<%=messages.getText("generic.messages.yes")%>
											</td>
											<td align="center" class="guide2" width="15%">
												<%=messages.getText("generic.messages.no")%>
											</td>
										</tr>
<%				Collection items = point.getItems();
				Collections.sort((List)items);
				Iterator itemIt = items.iterator();
				while(itemIt.hasNext()) {
					ItemData item = (ItemData)itemIt.next();
					Integer valueItem = registryData.getValueFromItem(item);
					int value = (valueItem == null) ? 0 : valueItem.intValue();
%>										<tr class='<%=line%>'>
											<td align="left" >
												<%=messages.getText(item.getText()) %>
											</td>
											<td align="center">
												<input type="radio" name='<%="item"+String.valueOf(item.getId())%>' value="1"  <%=(value == 1) ? "checked" : "" %>/>
											</td>
											<td align="center">
												<input type="radio" name='<%="item"+String.valueOf(item.getId())%>' value="2"  <%=(value == 2) ? "checked" : "" %>/>
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
<%				if(i == 0) {
%>					<td width="2%"></td>		
<%				}
			}
%>
				</tr>
				<tr>
		        	<td height="10"><img src="x" width="1" height="1"></td>
				</tr>
<%//COMIENZA ANTES DE SALIR Y USO DE LOS COMANDOS 				
			PointData point = (PointData) pointIt.next();
			String comment = registryData.getPointComment(point);
%>
	
				<tr>
					<td width="100%" valign="top" colspan="3">
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
								<td width="100%">
									<table width="100%">
										<tr>
<%//COMIENZA ANTES DE SALIR Y USO DE LOS COMANDOS 				
			int index = 0;
			Collection items = point.getItems();
			Collections.sort((List)items);
			Iterator itemIt = items.iterator();			
			for(int i = 0; i < 2; i++) {
%>											<td width="49%" valign="top">
												<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
													<tr>
														<td align="left" class="guide2" width="70%">
															&nbsp;
														</td>
														<td align="center" class="guide2" width="15%">
															<%=messages.getText("generic.messages.yes")%>
														</td>
														<td align="center" class="guide2" width="15%">
															<%=messages.getText("generic.messages.no")%>
														</td>
													</tr>
<%				while(itemIt.hasNext() && index < (9 * (i+1))) {
					ItemData item = (ItemData)itemIt.next();
					Integer valueItem = registryData.getValueFromItem(item);
					int value = (valueItem == null) ? 0 : valueItem.intValue();
%>
													<tr class='<%=line%>'>
														<td align="left" >
															<%=messages.getText(item.getText()) %>
														</td>
														<td align="center">
															<input type="radio" name='<%="item"+String.valueOf(item.getId())%>' value="1"  <%=(value == 1) ? "checked" : "" %>/>
														</td>
														<td align="center">
															<input type="radio" name='<%="item"+String.valueOf(item.getId())%>' value="2"  <%=(value == 2) ? "checked" : "" %>/>
														</td>
													</tr>										
<%					line = (line.equals("linetwo")) ? "lineone" : "linetwo";
					index++;
				}
%>												</table>
											</td>	
<%				if(i == 0) {
%>											<td width="2%"></td>		
<%				}
			}
%>						   				</tr>
									</table>
								</td>
								<td class="rightLine">&nbsp;</td>
							</tr>		
							<tr>
								<td class="leftLine">&nbsp;</td>
   								<td height="6"><img src="x" width="1" height="1"></td>
   								<td class="rightLine">&nbsp;</td>
							</tr>  					  					
							<tr>
								<td class="leftLine">&nbsp;</td>
								<td>
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

<%
// FIN DE MANIOBRAS EN ESPACIO REDUCIDO Y ACTITUD  
%>
				<tr>
					<td colspan="3">
						<table width="100%">
							<tr>
					
<% //COMIENZA DICTAMEN  Y ACTITUD

		for(int i = 0; i < 2; i++) {
				point = (PointData) pointIt.next();
				comment = registryData.getPointComment(point);
				String width = (i == 0) ? "61%" : "38%";
%>					
				<td width='<%=width%>' valign="top">
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
<%				items = point.getItems();
				Collections.sort((List)items);
				itemIt = items.iterator();
				index = 0;
				while(itemIt.hasNext()) {
					ItemData item = (ItemData)itemIt.next();
					Integer valueItem = registryData.getValueFromItem(item);
					int value = (valueItem == null) ? 0 : valueItem.intValue();//0
					if(i == 1 || (index == 0)) {
%>									<tr class="<%=line%>">
<%					}
%>										<td align="left" >
											<table width="100%" cellpadding="0" cellspacing="0">
												<tr class="<%=line%>">
													<td align="right" width="20%">
														<input type="radio" name='<%="item"+String.valueOf(item.getId())%>' value="1" <%=(value == 1) ? "checked" : "" %>/>
														<%=messages.getText("generic.messages.no") %>
													</td>
													<td align="left" width="20%">
														<input type="radio" name='<%="item"+String.valueOf(item.getId())%>' value="2" <%=(value == 2) ? "checked" : "" %>/>
														<%=messages.getText("generic.messages.yes") %>
													</td>
													<td width="60%" align="left">
														<%=messages.getText(item.getText()) %>
													</td>
												</tr>
											</table>
										</td>
<%					if(i == 1 || (index == 1)) {
%>									</tr>
<%						line = (line.equals("linetwo")) ? "lineone" : "linetwo";
					}
					index = 1 - index;
				}
%>									<tr>
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
<%			if(i == 0) {
%>				<td width="1%"></td>		
<%			}
		}
%>				
						</tr>
					</table>
				</td>
			</tr>
			<tr>
		        	<td height="10"><img src="x" width="1" height="1"></td>
			</tr>
			<tr>
				<td align="center" colspan="3">
					<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0"> 
						<tr>
<%			if(pointIt.hasNext()) {
				point = (PointData) pointIt.next();
				comment = registryData.getPointComment(point);
%>									
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
										<tr class="linetwo">
<%				items = point.getItems();
				Collections.sort((List)items);
				itemIt = items.iterator();
				while(itemIt.hasNext()) {
					ItemData item = (ItemData)itemIt.next();
					Integer valueItem = registryData.getValueFromItem(item);
					int value = (valueItem == null) ? 0 : valueItem.intValue();//0
%>											<td align="right">
												<input type="checkbox" name='<%="item"+String.valueOf(item.getId())%>' <%=(value == 1) ? "checked" : "" %>/>
												<%=messages.getText(item.getText()) %>
											</td>
<%				}
%>										</tr>
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
<%			}
%>
				</tr>	
<%
// FIN DICTAMEN  Y ACTITUD
%>		
			
<%
// COMIENZA OBSERVACIONES
%>		
				<tr>
		        	<td height="10"><img src="x" width="1" height="1"></td>
				</tr>
				<tr>
					<td align="center" colspan="3">
						<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
							<tr class="guide1">
								<td width="19" height="30" ><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
		   		  		  		<td height="30" class="guide1"><div align="left"><%=messages.getText("generic.activity.result")%></div></td>
								<td width="19" height="30" ><img src="/datacenter/util/imgs/right.jpg" width="19" height="30"></td>
		  					</tr>
							<tr>
								<td class="leftLine">&nbsp;</td>
     							<td height="6"><img src="x" width="1" height="1"></td>
     							<td class="rightLine">&nbsp;</td>
							</tr>  					  					
            				<tr> 
 								<td class="leftLine">&nbsp;</td>
              					<td align="left" class="linetwo">
              						<%= messages.getText("generic.activity.note") %>
              						&nbsp;
<%		      		DataTypeReportFacade dataTypeReportFacade = sys.getDataTypeReportFacade();		
        			Collection notes = dataTypeReportFacade.findAll(DataTypeConstants.TYPECEPAACTIVITYNOTE,userSessionData);
              		if (notes != null && notes.size() > 0){ 
%>	              					<html:select property="note" styleClass="input">
              		
<%             			Iterator it = notes.iterator();
              			while (it.hasNext()){
              				String activityNote = ((CEPAActivityNoteDataType) it.next()).getResourceBundleId();
%>		       			  				<html:option value='<%= activityNote %>' styleClass="input"><%= messages.getText(activityNote) %></html:option>
<%               		}
%>                					</html:select> 
<%                	}
%>	              				</td>
								<td class="rightLine">&nbsp;</td>
						    </tr>
							<tr>
								<td class="leftLine">&nbsp;</td>
     							<td  class="linetwo"><%=messages.getText("generic.messages.observations") %></td>
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
<%
// FIN DE OBSERVACIONES
%>					   	
				<tr>
		        	<td height="10"><img src="x" width="1" height="1"></td>
				</tr>
				<tr>
		        	<td height="10" align="right" colspan="3">
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

