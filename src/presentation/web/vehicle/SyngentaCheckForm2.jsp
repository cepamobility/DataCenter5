<%@page import="communication.datatype.tables.TypeTradeDataType"%>
<%@page import="communication.datatype.DataTypeConstants"%>
<%@page language="java" 
	errorPage="../exception.jsp" 
	import="business.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="presentation.translator.web.vehicle.*"
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.ActivityTypeDataType"
	import="business.vehicle.VehicleReportFacade"
	import="java.util.*"	
	import="org.apache.struts.action.DynaActionForm"
	import="communication.activity.tables.*"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%!
	RequestDispatcher dispatcher; 
	Text messages; SystemAccess sys;
	UserSessionData userSessionData; 
	VehicleCheckData vehicleCheck;
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();	
	vehicleCheck = null;
	HashMap<String, String> values = new HashMap<String, String>();
	
	String check = Util.checkPermission(sys,SecurityConstants.EDIT_VEHICLE);
	String check1 = Util.checkPermission(sys,SecurityConstants.MODIFY_VEHICLE);	
	String check2 = Util.checkPermission(sys,SecurityConstants.CHECKLIST_VEHICLE);	
	
	if (check!=null) { 
		response.sendRedirect(request.getContextPath()+check);	
	}else if (check1!=null) { 
		response.sendRedirect(request.getContextPath()+check1);	
	} else if (check2!=null) { 
		response.sendRedirect(request.getContextPath()+check2);	
	}else {
		messages=sys.getText();
	
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		Iterator iter = sys.getDataTypeReportFacade().findAll(DataTypeConstants.TYPETRADE,userSessionData).iterator();
		Collection trade = new LinkedList();
		while(iter.hasNext()) {
	String value = ((TypeTradeDataType)iter.next()).getResourceBundleId();
	if(value.equals("generic.messages.nodata")) {
		trade.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
	}else {
		trade.add(new OptionItem(value,value));				
	}
		}
		session.setAttribute("trade",trade);
		
		session.setAttribute("vt",new DataTypeUtil(sys).getVehicleType(false,true,false));

		if(request.getParameter("type") != null && request.getParameter("type").equals("error")) {
	values = (HashMap<String, String>)userSessionData.getValue();
		}
%>	
<html:html>
<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
	<center>
	<html:form action="/SyngentaCheckList2">
		<table width="768" border="0" cellspacing="0" cellpadding="0">  
			<tr class="guide1">
			<%
				String page14 = "../component/titlecomponent.jsp?title="+messages.getText("generic.activity.form.truckchecklist");
			%>
				<jsp:include  page='<%=page14%>' />
  			</tr>
      		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
		<tr> 
			<td height="40" colspan="3" valign="middle">
				<%
					String page13 = "../component/utilityboxtop.jsp?title="+messages.getText("generic.activity.form.tranningonthejob.proofdata");
				%>
				<jsp:include  page='<%=page13%>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
   	 				<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.activity.date")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
						</td>
						<td width="50%" align="right" class="linetwo">
						<%
							String date = "";
														String month = "";
														String year = "";
														String name = "";
														String serie = "";
														if (vehicleCheck != null && vehicleCheck.getDate() != null){
															Calendar c = Calendar.getInstance();
															c.setTime(vehicleCheck.getDate());
															date = String.valueOf(c.get(Calendar.DATE));
															month = String.valueOf(c.get(Calendar.MONTH)+1);
															year = String.valueOf(c.get(Calendar.YEAR));
															name = vehicleCheck.getName();
															serie = vehicleCheck.getSerie();
														}else if(values.containsKey("date")) {
															date = values.get("date");
															month = values.get("month");
															year = values.get("year");		
															name = values.get("name");
															serie = values.get("serie");
														}
						%>
							<html:text style="width:30;" property="date" styleClass="input" value='<%=date%>' />&nbsp;/&nbsp;                
							<html:text style="width:30;" property="month" styleClass="input" value='<%=month%>' />&nbsp;/&nbsp; 
							<html:text style="width:60;" property="year" styleClass="input" value='<%=year%>' />&nbsp;           		    		
						</td>
   					</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
     				<tr> 
        				<td width="50%" align="left" class="texts">        
       						<%=messages.getText("generic.driver.name")%>
						</td>
						<td width="50%" align="right" class="linetwo">		
							<html:text property="name" styleClass="input"  style="width:200;"  
							value='<%=name%>'/>                                     
        				</td>
     				</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
     				<tr> 
        				<td width="50%" align="left" class="texts">        
       						<%=messages.getText("generic.driver.charge")%>
						</td>
						<td width="50%" align="right" class="linetwo">		
							<html:text property="charge" styleClass="input"  style="width:200;"  
							value='<%=serie%>'/>                                     
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
			<td height="40" colspan="3" valign="middle">
<%
	page13 = "../component/utilityboxtop.jsp?title="+messages.getText("generic.vehicle.vehicledata");
%>
				<jsp:include  page='<%=page13%>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
     				<tr> 
        				<td width="50%" align="left" class="texts">        
       						<%=messages.getText("generic.vehicle.vehicledata.plate")%>
           					<span class="required">*</span>
						</td>
						<td width="50%" align="right" class="linetwo">		
                			<html:text property="plate" style="width:155" styleClass="input" maxlength="30"/>
        				</td>
     				</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
       				<tr class="texts">
           				<th align="left" >
           					<%=messages.getText("generic.vehicle.vehicledata.vehicletype")%>
           					<span class="required">*</span>
           				</th>
           				<td align="right">
<%
	session.setAttribute("vt",new DataTypeUtil(sys).getVehicleType(false,true,false));
%>	        				<html:select property="type" styleClass="input" style="width:155;">
               					<html:options collection="vt" property="value" labelProperty="label" styleClass="input"/>
               				</html:select>
           				</td>
       				</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
     				<tr> 
        				<td width="50%" align="left" class="texts">        
       						<%=messages.getText("generic.vehicle.vehicledata.trade")%>
           					<span class="required">*</span>
						</td>
						<td width="50%" align="right" class="linetwo">		
                			<html:select property="trade" styleClass="input" style="width:155;">
               					<html:options collection="trade" property="value" labelProperty="label" styleClass="input"/>
               				</html:select>
        				</td>
     				</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
     				<tr> 
        				<td width="50%" align="left" class="texts">        
       						<%=messages.getText("generic.vehicle.vehicledata.model")%>
           					<span class="required">*</span>
						</td>
						<td width="50%" align="right" class="linetwo">		
							<html:text property="model" style="width:155" styleClass="input"/>                                    
        				</td>
     				</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
       				<tr class="texts">
           				<th align="left"> 
           					<%=messages.getText("generic.vehicle.vehicledata.year")%>
           					<span class="required">*</span>
           				</th>
           				<td align="right">
               				<html:text property="vehicleYear" style="width:155" styleClass="input" maxlength="4"/>
           				</td>
       				</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
     				<tr> 
        				<td width="50%" align="left" class="texts">        
       						<%=messages.getText("generic.vehicle.vehicledata.renavan")%>
						</td>
						<td width="50%" align="right" class="linetwo">		
							<html:text property="renavam" styleClass="input"  style="width:200;"  
							value='<%=(vehicleCheck == null) ? "" : vehicleCheck.getLicenseNumber()%>'/>                                     
        				</td>
     				</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
     				<tr> 
        				<td width="50%" align="left" class="texts">        
       						<%=messages.getText("generic.vehiclecheck.form.vehicleKm")%>
						</td>
						<td width="50%" align="right" class="linetwo">		
							<html:text property="vehicleKm" styleClass="input"  style="width:200;"  
							value='<%=(vehicleCheck == null || vehicleCheck.getVehicleKm() != null) ? "" : String.valueOf(vehicleCheck.getVehicleKm())%>'/>                                     
        				</td>
     				</tr>
				</table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>  
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
<%
	CheckListData checkList = sys.getVehicleReportFacade().getCheckList(CheckListData.SYNGENTA_CHECKLIST,sys.getUserSessionData());
		Iterator points = checkList.getSortPoints();
		boolean header = false;
		int pneusIndex = 0;
%>		<tr>
			<td valign="top" width="100%">
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top">
<%
	String className = "linetwo";
		while(points.hasNext()) {
	PointData point = (PointData)points.next();
	Iterator items = point.getSortItems();
	while(items.hasNext()) {
		ItemData item = (ItemData)items.next();
		if(item.getType().intValue() == 1) {
	int value = -1;
	if(vehicleCheck != null) {
		String checkValue = vehicleCheck.getValueFromItem(item.getId()).trim();
		value = (checkValue == null) ? -1 : Integer.parseInt(checkValue);
	}else {
		value = (values.containsKey("item-"+String.valueOf(item.getId()))) ? Integer.parseInt(values.get("item-"+String.valueOf(item.getId()))) : -1; 
	}
	String pointComment = "";
	if(vehicleCheck != null) {
		pointComment = vehicleCheck.getValueFromCommentPoint(point.getId()).trim();
	}else {
		pointComment = 	(values.containsKey("point-"+String.valueOf(point.getId()))) ? values.get("point-"+String.valueOf(point.getId())) : "";
	}
	if(!header) {
		header = true;
		String page12 = "../component/utilityboxtop.jsp?title="+messages.getText("Datos");
%>							<jsp:include  page='<%=page12%>' />
							<table width="100%" height="60" border="0" cellpadding="2" cellspacing="2">
								<tr>
									<td align="left" class="guide2" width="40%"><%=messages.getText("syngenta.checklist.condition")%></td>
									<td align="center" class="guide2" width="10%"><%=messages.getText("syngenta.checklist.atention")%></td>
									<td align="center" class="guide2" width="10%"><%=messages.getText("syngenta.checklist.good")%></td>
									<td align="left" class="guide2" width="40%"><%=messages.getText("syngenta.checklist.observations")%></td>
								</tr>				
<%
					}
				%>				
								<tr>
									<td align="left" class="<%=className%>">
		   								<%=messages.getText(item.getText())%>
									</td>	
									<td align="center" class="<%=className%>">
										<input type="radio" name="<%="item-"+String.valueOf(item.getId())%>" value="1" <%=(value == 1) ? "checked" : ""%>></input>
									</td>	
									<td align="center" class="<%=className%>">
										<input type="radio" name="<%="item-"+String.valueOf(item.getId())%>" value="2" <%=(value == 2) ? "checked" : ""%>></input>
									</td>	
									<td align="left" class="<%=className%>">
										<input type="text" name="<%="point-"+String.valueOf(point.getId())%>" size="40" value="<%=(Util.empty(pointComment)) ? "" : pointComment%>"></input>
									</td>
								</tr>
<%				}else if(item.getType().intValue() == 2) {
					String value = "";
					if(vehicleCheck != null) {
						value = vehicleCheck.getValueFromItem(item.getId()).trim();
					}else {
						value = (values.containsKey("item-"+String.valueOf(item.getId()))) ? values.get("item-"+String.valueOf(item.getId())) : ""; 
					}
					switch(pneusIndex) {
						case 0:
%>	
							</table>
							<jsp:include  page='../component/utilityboxbottom.jsp' />
						</td>
					</tr>
				  	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
					<tr>
						<td valign="top">
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.activity.observations") %>' />
							<table align="center" width="300" cellpadding="2" cellspacing="2" border="0">								
								<tr>
									<td>
										<html:textarea property="obs" cols="100" rows="5" value='<%=(vehicleCheck == null) ? "" : vehicleCheck.getObs()%>'></html:textarea>
									</td>
								</tr>
							</table>
							<jsp:include  page='../component/utilityboxbottom.jsp' />
						</td>
					</tr>
				  	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
					<tr>
						<td valign="top">
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("syngenta.checklist.cover")%>' />
							<table align="center" width="300" cellpadding="2" cellspacing="2" border="0">								
								<tr>
									<td>
										<table>
											<tr>
												<td align="center" class="linetwo">
					   								<%=messages.getText(item.getText())%>
					   								&nbsp;
													<input type="text" name="<%="item-"+String.valueOf(item.getId())%>" size="10" value="<%=value%>"></input>
												</td>
											</tr>	
<%							break;
						case 1:case 3:case 5: case 7:
%>											<tr>
												<td align="center" class="linetwo">
					   								<%=messages.getText(item.getText())%>
					   								&nbsp;
													<input type="text" name="<%="item-"+String.valueOf(item.getId())%>" size="10" value="<%=value%>"></input>
												</td>
											</tr>
										</table>
									</td>	
<%							break;
						case 2:case 6:
%>
									<td>
										<table>
											<tr>
												<td align="center" class="linetwo">
					   								<%=messages.getText(item.getText())%>
					   								&nbsp;
													<input type="text" name="<%="item-"+String.valueOf(item.getId())%>" size="10" value="<%=value%>"></input>
												</td>
											</tr>	
<%							break;
						case 4:
%>								</tr>	
								<tr>
									<td colspan="2" align="center">
										<table width="300" height="130" border="0" cellpadding="0" cellspacing="0" background="../datacenter/util/imgs/van.png">
											<tr><td>&nbsp;</td></tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table>
											<tr>
												<td align="center" class="linetwo">
					   								<%=messages.getText(item.getText())%>
					   								&nbsp;
													<input type="text" name="<%="item-"+String.valueOf(item.getId())%>" size="10" value="<%=value%>"></input>
												</td>
											</tr>	
<%					}
					pneusIndex++;
				}
			}
		}
%>											
								</tr>	
							</table>
							<jsp:include  page='../component/utilityboxbottom.jsp' />
						</td>
					</tr>
				</table>
			</td>
		</tr>	
	  	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
	   	<tr>   		
	   		<td align="right">
	   			<html:submit styleClass="input"><%=messages.getText("generic.messages.accept")%></html:submit>	 		                             
	 			<html:cancel styleClass="input"><%=messages.getText("generic.messages.cancel")%></html:cancel >
           	</td>                 							
   	  	</tr>              
	</table>
	</html:form>
	</center>
</body>
</html:html>
<%
	}
%>
