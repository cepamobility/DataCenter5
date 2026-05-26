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
	import="communication.administration.corporation.tables.CorporationData"
	import="communication.administration.RoleData"
	import="javax.servlet.http.HttpServletRequest"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%!
	RequestDispatcher dispatcher; 
	Text messages; SystemAccess sys;
	UserSessionData userSessionData; 
	ActivityReportFacade activityReport;
	String enabled, url, idDriver, idActivity;
	int columns;
	DriverMainAttributes driver;
	CEPAActivityScheduleRegistryData registryData;
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();	
	String check = Util.checkPermission(sys,SecurityConstants.CREATE_CEPASCHEDULEACTIVITYREGISTRY);	
	if (check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		messages=sys.getText();
	
	
		//session.setAttribute("url","activity/schedule/CEPAActivityScheduleView.jsp");
		url = "activity/schedule/ebtw.jsp";
		userSessionData.setUrl("activity/schedule/ebtw.jsp");
		
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		idActivity =(String)request.getParameter("idActivity");
		idDriver = (String)request.getParameter("idDriver");
		if(!Util.empty(idDriver)){
	driver = sys.getDriverReportFacade().findDriverMainData(Integer.valueOf(idDriver),sys.getUserSessionData());
		}
		
		ActivityReportFacade activityReport = sys.getActivityReportFacade();
		registryData = activityReport.getCompleteResult(new Integer(idActivity),new Integer(idDriver),sys.getUserSessionData());		
		String instructor = "";
		String initialHr = "";
		String initialMinute = "";
		String finalHr = "";
		String finalMinute = "";
		String stringList = null;
		DynaActionForm form = null;
		Collection itemsValues = null;
		Collection pointsValues = null;
		
		if(session.getAttribute("ActivityRegistryForm") != null) {
		    form = (DynaActionForm)session.getAttribute("ActivityRegistryForm");
	stringList = form.getString("checkboxParamList");
	if(stringList != null && stringList.length() > 0) {
	    instructor = form.getString("instructor");
		initialHr = form.getString("initialHr");		
		initialMinute = form.getString("initialMinute");
		finalHr = form.getString("finalHr");
		finalMinute = form.getString("finalMinute");
		
		itemsValues = Util.stringToItemValueCollection(stringList,"item");
		pointsValues = Util.stringToItemValueCollection(stringList,"point");
	} else {
	    instructor = registryData.getInstructor();
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
		}else {
		    instructor = registryData.getInstructor();
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
		
		CheckListData checkList = activityReport.findCheckList(new Integer(CheckListData.EBTW),userSessionData);	
		Collection points = checkList.getPoints();
		Collections.sort((List)points);
		Iterator pointIt = points.iterator();
%>
<!-- ebtw.jsp -->
	
	<script language="JavaScript" type="text/javascript">

	function preparedCheckboxRadioParameters(){

			var form = document.forms['ActivityRegistryForm'];
			var elements=form.elements;
			var length=elements.length;
			var i;
			var valueCheckboxParamList="";
			var separator="<";
			
			for(i=0;i<length;i++){
				var element=elements[i];
			
				if((element.type.toLowerCase()=="checkbox") || (element.type.toLowerCase()=="radio")) {
					if(element.checked){
						if(valueCheckboxParamList==""){
							valueCheckboxParamList = element.name + "-" + element.value;
						}else{
							valueCheckboxParamList =  element.name + "-" + element.value + "<"+valueCheckboxParamList;
						}	
					}	
				}
			}
			form.checkboxParamList.value=valueCheckboxParamList;
			return true;
	}

	
		function showObservations(result,observations) {
			var form = document.forms['ActivityRegistryForm'];
			if(form.elements[result].selectedIndex == 0) {
				document.getElementById(observations).style.display = 'none'; 
			}else {
				document.getElementById(observations).style.display = 'block'; 
			}
		}
	</script>
	
	<html:form action = "/EBTWRegistryCreate" method="post"  >
		<html:hidden property="idActivity" value='<%=idActivity%>' />
		<html:hidden property="idDriver" value='<%=idDriver%>' />
		<html:hidden property="url" value='<%=url%>' />
		<html:hidden property="checkboxParamList" value="" />
		<center>
	  		<table width="750" border="0" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../../component/titlecomponent.jsp?title="+messages.getText(registryData.getActivity().getType().getResourceBundleId())%>' />
		  		</tr>
		      	<tr>
					<jsp:include  page="../../component/spaceline.jsp" />
		  		</tr>
				<tr>
					<td align="center">
						<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.personaldata")%>' />
						<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
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
							<tr>
								<th class="linetwo" align="left"><%=messages.getText("generic.activity.form.tranningonthejob.initialHr")+": "%></th>
								<td class="linetwo" align="right">
<%
	Calendar c = Calendar.getInstance();
	if(registryData.getInitialHr() != null) {
	    c.setTime(registryData.getInitialHr());
	}
%>									<html:text property="initialHr" style="width:23;" styleClass="input" value='<%=initialHr%>' maxlength="2" />&nbsp;:&nbsp;
			            			<html:text property="initialMinute" style="width:23;" styleClass="input" value='<%=initialMinute%>' maxlength="2"/>
								</td>
							</tr>
							<tr>
					        	<td height="5"><img src="x" width="1" height="1"></td>
							</tr>
							<tr>
								<th class="linetwo" align="left"><%=messages.getText("generic.activity.form.tranningonthejob.finalHr")+": "%></th>
								<td class="linetwo" align="right">
<%
	if(registryData.getFinalHr() != null) {
	    c.setTime(registryData.getFinalHr());
	}
%>									<html:text property="finalHr" style="width:23;" styleClass="input" value='<%=finalHr%>'  maxlength="2" />&nbsp;:&nbsp;
	           						<html:text property="finalMinute" style="width:23;" styleClass="input" value='<%=finalMinute%>'   maxlength="2" />
								</td>
							</tr>
							<tr>
					        	<td height="5"><img src="x" width="1" height="1"></td>
							</tr>
            				<tr> 
								<th class="linetwo" align="left"><%=messages.getText("activity.ebtw.noteteorico")+": "%></th>
           						<td align="right" class="linetwo"> 
             						<select name="vehicleType" class="input">              		
         			  					<option value='<%=CEPAActivityNoteDataType.APRUVED%>' class="input" <%=(registryData != null && registryData.getVehicleType().equals(CEPAActivityNoteDataType.APRUVED)) ? "selected" : ""%>><%=messages.getText(CEPAActivityNoteDataType.APRUVED)%></option>
         			  					<option value='<%=CEPAActivityNoteDataType.NOT_APRUVED%>' class="input" <%=(registryData != null && registryData.getVehicleType().equals(CEPAActivityNoteDataType.NOT_APRUVED)) ? "selected" : ""%>><%=messages.getText(CEPAActivityNoteDataType.NOT_APRUVED)%></option>
       								</select> 
     							</td>
				            </tr>           
							<tr>
					        	<td height="5"><img src="x" width="1" height="1"></td>
							</tr>
            				<tr> 
								<th class="linetwo" align="left"><%=messages.getText("activity.ebtw.notepractico")+": "%></th>
           						<td align="right" class="linetwo"> 
             						<select name="note" class="input">              		
         			  					<option value='<%=CEPAActivityNoteDataType.APRUVED%>' class="input" <%=(registryData != null && registryData.getVehicleType() != null && registryData.getNote().equals(CEPAActivityNoteDataType.APRUVED)) ? "selected" : ""%>><%=messages.getText(CEPAActivityNoteDataType.APRUVED)%></option>
         			  					<option value='<%=CEPAActivityNoteDataType.NOT_APRUVED%>' class="input" <%=(registryData != null && registryData.getVehicleType() != null && registryData.getNote().equals(CEPAActivityNoteDataType.NOT_APRUVED)) ? "selected" : ""%>><%=messages.getText(CEPAActivityNoteDataType.NOT_APRUVED)%></option>
       								</select> 
     							</td>
            				</tr>            
	  					</table>
						<jsp:include  page='../../component/utilityboxbottom.jsp' />
					</td>
				</tr>
				<tr>
		        	<td height="10"><img src="x" width="1" height="1"></td>
				</tr>
				<tr>
					<td align="center">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
<%
	boolean line1 = true;
	boolean line2 = true;
	boolean header = false;
	while(pointIt.hasNext()) {	
		PointData point = (PointData) pointIt.next();
		int type = point.getPointOrderReport().intValue();
		if(type == 1) {
	if(line1) {
%>							<tr>
<%
	}
%>								<td width="49%">
									<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText(point.getText())%>' />
									<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
<%
  					Collection items = point.getItems();
  				  							Collections.sort((List)items);
  				  							Iterator itemIt = items.iterator();
  				  							boolean itemLine = true;
  				  							int indexValue = 1;
  				  							while(itemIt.hasNext()) {
  				  								ItemData item = (ItemData)itemIt.next();
  				  								int value = 0;
  				  								if(registryData != null) {
  				  									Integer valueItem = registryData.getValueFromItem(item);
  				  									
  				  									if (valueItem == null){
  				  										if (form != null && item != null && item.getId() != null && 
  				  												point != null && point.getId() != null) {
  				  											
  				  											// Formulario vacio, se precargan los datos del form
  				  											ItemData itemData = new ItemData(point.getId());
  				  											Integer valueFromItem = Util.getValueFromItem(pointsValues,itemData);
  				  											if ( valueFromItem != null && valueFromItem.equals(item.getId()) ){
  				  												value = 1;
  				  								            }else
  				  								             	value = 0;
  				  										}else{
  				  											// Formulario vacio, se precargan los datos por defecto (items)
  				  											value = 0;
  				  										}			
  				  									}else
  				  										value = valueItem.intValue();						
  				  								}						
  				  								if(itemLine) {
  				%>										<tr class="linetwo">
<%
	}
%>											<td align="left" width="49%">
												<table width="100%">
													<tr class="linetwo">
														<td align="right" width="70%">
															<%=messages.getText(item.getText())%>
														</td>
														<td align="center" width="30%">
															<input type="radio" id="<%="point"+point.getId()%>"  name="<%="point"+point.getId()%>" value="<%=String.valueOf(item.getId())%>"  <%=(value == 1) ? "checked" : ""%>>
														</td>
													</tr>
    											</table>	
											</td>    									
<%
    										if(!itemLine) {
    									    														itemLine = true;
    									%>									 	</tr>
<%
	}else{
			itemLine = false;
%>	        					<td width="2%"></td>
<%
	}
		indexValue++;
	}
%>                      			</table>
									<jsp:include  page='../../component/utilityboxbottom.jsp' />
    							</td>
<%
	if(!line1) {
		line1 = true;
%>							</tr>
							<tr>
		        				<td height="10"><img src="x" width="1" height="1"></td>
							</tr>
<%
	}else {
%>	        					<td width="2%"></td>
<%
	line1 = false;
	}
		}else {
	String component = "utilitybox2top.jsp";
	String component2 = "utilitybox2bottom.jsp";
	String classname = "lineone";
	switch(type){
		case 2:
			if(line2) {
%>							<tr>
								<td colspan="3">
									<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("ebtw.point.family2")%>' />
									<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  
<%
  	line2 = false;
    					}
    					break;
    				case 3:
    					if(!line2) {
  %>									</table>
									<jsp:include  page='../../component/utilityboxbottom.jsp' />
    							</td>
							</tr>
							<tr>
								<td colspan="3">
									<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("ebtw.point.family3")%>' />
									<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  
<%
  	line2 = true;
    					}
    					break;
    				case 4:
    					classname = "linetwo";
    					component = "utilityboxtop.jsp";
    					component2 = "utilityboxbottom.jsp";
    					if(line2) {
  %>									</table>
									<jsp:include  page='../../component/utilityboxbottom.jsp' />
								</td>
							</tr>
<%
	line2 = false;
			}
	}
%>							<tr>
								<td colspan="3">
									<jsp:include  page='<%="../../component/"+component+"?title="+messages.getText(point.getText())%>' />
									<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  
										<tr class="<%=classname%>">
											<td align="left" width="100%">
												<table width="100%">
													<tr class="guide2">
														<td align="left" width="40%">
															<%=messages.getText("ebtw.point.item")%>
														</td>
														<td align="left" width="20%" >
															<%=messages.getText("ebtw.item.result1")%>
														</td>
														<td align="left"  width="20%">
															<%=messages.getText("ebtw.item.result2")%>
														</td>
														<td align="left"  width="20%">
															<%=messages.getText("ebtw.item.result3")%>
														</td>
													</tr>
<%
	Collection items = point.getItems();
	Collections.sort((List)items);
	Iterator itemIt = items.iterator();
	
	while(itemIt.hasNext()) {
		ItemData item = (ItemData)itemIt.next();
		int value = 0;
		if(registryData != null) {
			Integer valueItem = registryData.getValueFromItem(item);
			
			if (valueItem == null){
				// valueItem == null, cargo por defecto todo en correcto
				// o si recargue el formulario por algun error de validacion tengo
				// que cargar los datos previamente elegidos.
				// Si hubo problemas con la validación se recargan los datos ya ingresados
				if (form != null && item != null && item.getId() != null) {
					// Formulario vacio, se precargan los datos del form
					ItemData itemData = new ItemData(item.getId());
					Integer valueFromItem = Util.getValueFromItem(itemsValues,itemData);
					if ( valueFromItem != null){
						value = valueFromItem.intValue();
		            }else
		             	value = 1;
				}else{
					// Formulario vacio, se precargan los datos por defecto (items)
					value = 1;
				}							
				// 	value = (valueItem == null) ? 0 : valueItem.intValue();
			}else{
				value = valueItem.intValue();
			}
		}
%>													<tr class="<%=classname%>">
														<td align="left" >
															<%=messages.getText(item.getText()) %>
														</td>
														<td align="center">
															<input type="radio" id="<%="item"+item.getId()%>"  name="<%="item"+item.getId()%>" value="1" <%=(value == 1) ? "checked" : ""%>>
														</td>
														<td align="center">
															<input type="radio" id="<%="item"+item.getId()%>"  name="<%="item"+item.getId()%>" value="2" <%=(value == 2) ? "checked" : ""%>>
														</td>
														<td align="center">
															<input type="radio" id="<%="item"+item.getId()%>"  name="<%="item"+item.getId()%>" value="3" <%=(value == 3) ? "checked" : ""%>>
														</td>
													</tr>
<%					}
%>												</table>	
											</td>    									
										</table>
										<jsp:include  page='<%="../../component/"+component2%>' />
					    			</td>
								</tr>
								<tr>
			        				<td height="10"><img src="x" width="1" height="1"></td>
								</tr>
<%				}
			}
%>						</table>
					</td>
				</tr>
				<tr>
		        	<td height="10"><img src="x" width="1" height="1"></td>
				</tr>
				<tr>
					<td align="center">
						<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.observations")%>' />
    					<table width="100%" border="0" cellpadding="0" cellspacing="0">
    						<tr>
		       					<td valign="top" align="left" colspan="2">
		       						<font size="1" face="Arial, Helvetica, sans-serif"> 
										<html:textarea property="obs" style="width:80%" cols="60" rows="3" styleClass="input" value='<%=(registryData != null && registryData.getObs() != null) ? registryData.getObs() : "" %>'></html:textarea>
									</font>	        	
		        				</td> 		             
		    				</tr>
		    			</table>
						<jsp:include  page='../../component/utilityboxbottom.jsp' />
			    	 </td>
			   	</tr>
				<tr>
		        	<td height="10"><img src="x" width="1" height="1"></td>
				</tr>
				<tr>
		        	<td height="10" align="right">
		        		<html:submit value='<%=messages.getText("generic.messages.save")%>' styleClass="input" onclick="preparedCheckboxRadioParameters();" />
		        		<html:cancel value='<%=messages.getText("generic.messages.cancel")%>' styleClass="input"/>
					</td>
				</tr>
			</table>
		</center>
	</html:form>

  
  <form name="FormCD" method="post" action='layout.jsp?refer=/activity/schedule/CEPAActivityScheduleView.jsp?id=<%=idActivity %>' >
  </form>
  
<%	}
%>

