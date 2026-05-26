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
	import ="org.apache.struts.action.DynaActionForm"
	import ="communication.activity.tables.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<!-- VehicleCheckForm -->
<script language="JavaScript" type="text/javascript">
	function preparedCheckboxRadioParameters(form){

		var elements=form.elements;
		var length=elements.length;
		var i;
		var valueCheckboxParamList="";
		var separator="<";
		
		for(i=0;i<length;i++){
			var element=elements[i];
			if(element.type.toLowerCase()=="radio") {
				if (element.checked) {	
					if(valueCheckboxParamList==""){
						valueCheckboxParamList=element.value;
					}
					else{
						valueCheckboxParamList=element.value+"<"+valueCheckboxParamList;
					}	
				}	
			} 
		}
		form.list.value=valueCheckboxParamList;
		return true;
	}

	function setAction(form){
		if(preparedCheckboxRadioParameters(form)) {
			form.submit();
		}
	}

	function changeCheck(form,id){
		var option = form.elements['trailerCheck'+id].options[form.elements['trailerCheck'+id].selectedIndex].value;
		if(option == 1) {
			document.getElementById('trailer'+id).style.display = 'block';
		}else {
			document.getElementById('trailer'+id).style.display = 'none';
		}
	}
</script>
<html:html>
<%!
	RequestDispatcher dispatcher; 
	Text messages; SystemAccess sys;
	UserSessionData userSessionData; 
	VehicleCheckData vehicleCheck;
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();	
	
	String check = Util.checkPermission(sys,SecurityConstants.EDIT_VEHICLE);
	String check1 = Util.checkPermission(sys,SecurityConstants.MODIFY_VEHICLE);	
	// String check2 = Util.checkPermission(sys,SecurityConstants.CREATE_CD_ACTIVITY);
	String check2 = Util.checkPermission(sys,SecurityConstants.CHECKLIST_VEHICLE);	
	
	if (((check!=null) && (check1!=null)) && (check2 != null)){ 
		response.sendRedirect(request.getContextPath()+check);	
	}else {
		messages=sys.getText();
	
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		TruckCheckListForm form = null;
		String idMissing = "0";
		boolean edit = !Util.empty(request.getParameter("type")) && request.getParameter("type").equals("edit"); 
		String formName = (edit) ? "TruckCheckListEditForm" : "TruckCheckListForm";
		String idVehicle = null;
		Collection trailerChecks = new LinkedList();
		if(edit) {
		    String checkId = request.getParameter("check");
		    if(Util.empty(checkId)) {
		        checkId = String.valueOf(session.getAttribute("check"));
		    }
	trailerChecks = sys.getVehicleReportFacade().getCheckListTrailers(new Integer(checkId),sys.getUserSessionData());
	vehicleCheck = sys.getVehicleReportFacade().findVehicleCheck(new Integer(checkId),userSessionData);
	idVehicle = String.valueOf(vehicleCheck.getVehicle().getId());
	if(session.getAttribute(formName) == null) {
	    form = new TruckCheckListForm(vehicleCheck,messages);
		session.setAttribute(formName,form);
	}else {
	    form = (TruckCheckListForm)session.getAttribute(formName);
	}
		}else {
	idVehicle = (String)request.getParameter("id");
	if(Util.empty(idVehicle)) {
	    idVehicle =(String)session.getAttribute("id");
	}else {
		session.setAttribute("id",idVehicle);
	}
	session.setAttribute("url","vehicle/TruckCheckForm.jsp?id="+idVehicle);
	if(session.getAttribute(formName) == null) {
		VehicleData vehicle = sys.getVehicleReportFacade().findVehicleByPrimaryKey(new Integer(idVehicle),userSessionData);			
	    form = new TruckCheckListForm(vehicle,messages);
		session.setAttribute(formName,form);
	}else {
	    form = (TruckCheckListForm)session.getAttribute(formName);
	}
		}
%>	
<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
	<center>
	<html:form action='<%=(edit) ? "/TruckCheckListEdit" : "/TruckCheckList"%>'>
	<html:hidden property="list" />
	<table width="768" border="0" cellspacing="0" cellpadding="0">  
		<tr class="guide1">
			<%
				String page1 = "../component/titlecomponent.jsp?title="+messages.getText("generic.activity.form.truckchecklist");
			%>
			<jsp:include  page='<%=page1%>' />
  		</tr>
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
		<tr> 
			<td height="40" colspan="3" valign="middle">
				<%
					String page2 = "../component/utilityboxtop.jsp?title="+messages.getText("generic.activity.form.tranningonthejob.proofdata");
				%>
				<jsp:include  page='<%=page2%>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
   	 				<tr> 
       					<td width="50%" align="left" class="linetwo">        
   							<%=messages.getText("generic.activity.date")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:text style="width:30;" property="day" styleClass="input" />&nbsp;/&nbsp;                
							<html:text style="width:30;" property="month" styleClass="input" />&nbsp;/&nbsp; 
							<html:text style="width:60;" property="year" styleClass="input" />&nbsp;           		    		
						</td>
   					</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
     				<tr> 
        				<td width="50%" align="left" class="linetwo">        
       						<%=messages.getText("generic.driver.name")%>
						</td>
						<td width="50%" align="right" class="linetwo">		
							<html:text property="name" styleClass="input"  style="width:200;"/>                                     
        				</td>
     				</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
     				<tr class="linetwo"> 
        				<td width="50%" align="left">        
       						<%=messages.getText("generic.driver.licenseNumber")%>
						</td>
						<td width="50%" align="right">		
							<html:text property="licenseNumber" styleClass="input" style="width:200;" /> 
        				</td>
     				</tr>     
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
     				<tr class="linetwo">        
        				<td width="50%" align="left">        
       						<%=messages.getText("generic.activity.dateLicenseExpiry")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
						</td>
						<td width="50%" align="right">
							<html:text style="width:30;" property="dayLicenseExpiry" styleClass="input" />&nbsp;/&nbsp;                
							<html:text style="width:30;" property="monthLicenseExpiry" styleClass="input" />&nbsp;/&nbsp; 
							<html:text style="width:60;" property="yearLicenseExpiry" styleClass="input" />&nbsp;           		    		
						</td>
     				</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
     				<tr class="linetwo"> 
        				<td width="50%" align="left">        
       						<%=messages.getText("generic.activity.form.tranningonthejob.trade")%>
						</td>
						<td width="50%" align="right">		
							<html:text property="trade" styleClass="input" disabled="true" style="width:200;"/> 
        				</td>
     				</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      				<tr class="linetwo">
        				<td width="50%" align="left">        
       						<%=messages.getText("generic.vehicle.model")%>
						</td>
						<td width="50%" align="right">		
							<html:text property="model" styleClass="input" disabled="true" style="width:200;" /> 
        				</td>
     				</tr>     
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      				<tr class="linetwo">  
        				<td width="50%" align="left">        
					        <%=messages.getText("generic.activity.form.vehiclecheck.plate")%>
						</td>
						<td width="50%" align="right">		
							<html:text property="plate" styleClass="input" disabled="true" style="width:200;" />
        				</td>
     				</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      				<tr class="linetwo">
        				<td width="50%" align="left">        
					        <font face="Arial, Helvetica, sans-serif"> 
						        <%=messages.getText("generic.vehicle.serie")%>
							</td>
						<td width="50%" align="right">		
							<html:text property="serie" styleClass="input"  style="width:200;" />
				        </td>
				     </tr>     
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      				<tr class="linetwo">  
				        <td width="50%" align="left">        
					        <%=messages.getText("generic.vehiclecheck.form.vehicleKm")%>        
						</td>
						<td width="50%" align="right">		
							<html:text property="vehicleKm" styleClass="input" />                                                             
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
	CheckListData checkList = sys.getVehicleReportFacade().getTruckCheckList(sys.getUserSessionData(),CheckListData.TRUCK);
		Iterator points = checkList.getSortPoints();
%>		<tr>
			<td valign="top" width="100%">
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
					<tr>
<%
	for(int i = 0; i < 3; i++) {
    		if(i == 1) {
%>    		    		<td width="2%"></td>
<%
	}else {
%>						<td width="49%" valign="top">
							<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
<%
	int count = (i == 0) ? 4 : 4;
		for(int j = 0; j < count; j++) {
		    PointData point = (PointData)points.next();
		    if(!(point.getId().equals(new Integer(137))  && form.getVehicleType().equals("datatype.vehicletype.trator"))) {
%>								<tr>
									<td valign="top">
										<%
											String page3 = "../component/utilityboxtop.jsp?title="+messages.getText(point.getText());
										%>
										<jsp:include  page='<%=page3%>' />
										<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
<%
	Iterator items = point.getSortItems();
	while(items.hasNext()) {
		ItemData item = (ItemData)items.next();
		boolean show = true;
		if(item.getType() != null && form.getVehicleType().equals("datatype.vehicletype.trator") && item.getType().intValue() == 1) {
	        show = false;
		}
		String id1 =  String.valueOf(item.getId())+"-1";
		String id2 =  String.valueOf(item.getId())+"-2";
		int checked = 0;
		if(edit) {
		    String value = vehicleCheck.getValueFromItem(item.getId());
		    if(value == null) {
		        checked = 1;
		    }else {
				checked = Integer.parseInt(value);
		    }
		}else{
		    if(!Util.empty(form.getList())) {
		        if(form.getList().contains(id1)){
		            checked = 1;
		        }else {
		        	// Modificado:
			        if(form.getList().contains(id2)){
			            checked = 2;
			        }
		        }
		    }
		}
		if(show) {
%>											<tr class="linetwo">
												<td width="33%">
		   											<%=messages.getText(item.getText())%>
												</td>	
												<td width="33%">
				        							<input type="radio" name='<%=String.valueOf(idVehicle)+"-"+String.valueOf(item.getId())%>' value='<%=String.valueOf(idVehicle)+"-"+String.valueOf(item.getId())+"-1"%>' <%=(checked == 1) ? "checked" : ""%>/>	    				
													<%=messages.getText("generic.vehiclecheck.form.correct")%>
												</td>
												<td width="33%">
						        					<input type="radio" name='<%=String.valueOf(idVehicle)+"-"+String.valueOf(item.getId())%>' value='<%=String.valueOf(idVehicle)+"-"+String.valueOf(item.getId())+"-2"%>' <%=(checked == 2) ? "checked" : ""%> />	    				
													<%=messages.getText("generic.vehiclecheck.form.incorrect")%>
												</td>
											</tr>
<%
	if(items.hasNext()) {
%>			      							<tr>
												<jsp:include  page="../component/spaceline.jsp" />
									  		</tr>
<%
	}
		}else {
%>    
				        					<input type="radio" name='<%=String.valueOf(idVehicle)+"-"+String.valueOf(item.getId())%>' value='<%=String.valueOf(idVehicle)+"-"+String.valueOf(item.getId())+"-1"%>' style="display: none;"/>	    				
<%
	    					}
	    					    							}
	    				%>										</table>
										<jsp:include  page="../component/utilityboxbottom.jsp" />
									</td>	
								</tr>	
<%
		if(j < count-1) {
	%>     							<tr>
									<jsp:include  page="../component/spaceline.jsp" />
						  		</tr>
<%
	}
	}
		}
%>							</table>
						</td>
<%
	}
		}
%>					</tr>
				</table>
			</td>
		</tr>	
	  	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
<%
	Collection trailers = sys.getVehicleReportFacade().getVehicleTrailers(new Integer(idVehicle),userSessionData);
		Iterator it = trailers.iterator();
		while(it.hasNext()) {
		    TrailerMainData trailer = (TrailerMainData)it.next();
	TrailerCheckData trailerCheck = null;
	Iterator trailerIt = trailerChecks.iterator();
	while(trailerIt.hasNext() && trailerCheck == null) {
	    TrailerCheckData trailerCheckAux = (TrailerCheckData)trailerIt.next();
	    if(trailerCheckAux.getTrailer().getId().equals(trailer.getId())) {
	        trailerCheck = trailerCheckAux;
	    }
	}
    		String formNameLink = (edit) ?  "javascript:changeCheck(document.forms['TruckCheckListEditForm'],"+String.valueOf(trailer.getId())+");" : "javascript:changeCheck(document.forms['TruckCheckListForm'],"+String.valueOf(trailer.getId())+");";
%>		<tr> 
			<td width="100%" colspan="3" valign="middle">
				<%
					String page4 = "../component/utilityboxtop.jsp?title="+messages.getText("activity.trailer.checklist")+" "+trailer.getPlate();
				%>
				<jsp:include  page='<%=page4%>' />
				<span class="input"><%=messages.getText("truck.checklist.trailer")%>&nbsp;</span>
<%
	if(trailerCheck != null) {
%>				<input type="hidden" name='<%="trailerCheckId"+String.valueOf(trailer.getId())%>' value='<%=String.valueOf(trailerCheck.getTrailerCheckId())%>' />
<%
	}
%>				<select name='<%="trailerCheck"+String.valueOf(trailer.getId())%>' class="input" onchange="<%=formNameLink%>">
					<option value="0"><%=messages.getText("generic.messages.no")%></option>
					<option value="1" <%=(trailerCheck != null) ? "selected" : ""%>><%=messages.getText("generic.messages.yes")%></option>
				</select>
				<div id='<%="trailer"+String.valueOf(trailer.getId())%>' style='<%=((edit && (vehicleCheck != null) && (/*vehicleCheck.getTrailerCheckData()*/ "1" != null)) || ((form != null) && (form.getTrailerCheck() != null) && (form.getTrailerCheck().trim().equals("1"))))? "display: block;" : "display: none;"%>'>
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
      				<tr>
						<jsp:include  page="../component/spaceline.jsp" />
		  			</tr>
					<tr> 
						<td height="40" colspan="3" valign="middle">
							<%
								String page5 = "../component/utilitybox2top.jsp?title="+messages.getText("generic.activity.form.tranningonthejob.proofdata");
							%>
							<jsp:include  page='<%=page5%>' />
								<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
				     				<tr class="lineone"> 
				        				<td width="50%" align="left">        
				       						<%=messages.getText("generic.activity.form.tranningonthejob.trade")%>
										</td>
										<td width="50%" align="right">		
											<html:text property="tradeTrailer" styleClass="input" disabled="true" style="width:200;" value='<%=(trailer.getTrade() == null) ? "" : messages.getText(trailer.getTrade().getResourceBundleId())%>' /> 
				        				</td>
				     				</tr>
									<tr class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
				      				<tr class="lineone">
				        				<td width="50%" align="left">        
				       						<%=messages.getText("generic.vehicle.model")%>
										</td>
										<td width="50%" align="right">		
											<html:text property="modelTrailer" styleClass="input" disabled="true" style="width:200;" value='<%=(trailer.getModel() == null) ? "" : trailer.getModel()%>' /> 
				        				</td>
				     				</tr>     
									<tr class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
				      				<tr class="lineone">  
				        				<td width="50%" align="left">        
									        <%=messages.getText("generic.activity.form.vehiclecheck.plate")%>
										</td>
										<td width="50%" align="right">		
											<html:text property="plateTrailer" styleClass="input" disabled="true" style="width:200;" value='<%=(trailer.getPlate() == null) ? "" : trailer.getPlate()%>'/>
				        				</td>
				     				</tr>
									<tr class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
				      				<tr class="lineone">
				        				<td width="50%" align="left">        
									        <font face="Arial, Helvetica, sans-serif"> 
										        <%=messages.getText("generic.vehicle.serie")%>
											</td>
										<td width="50%" align="right">		
											<html:text property="serieTrailer" styleClass="input"  style="width:200;" value='<%=(trailerCheck == null) ? "" : trailerCheck.getSerie()%>' />
								        </td>
								     </tr>     
								</table>
							<jsp:include  page="../component/utilitybox2bottom.jsp" />
						</td>
					</tr>  
      				<tr>
						<jsp:include  page="../component/spaceline.jsp" />
		  			</tr>
<%
	checkList = sys.getVehicleReportFacade().getTruckCheckList(sys.getUserSessionData(),CheckListData.TRAILER);
	points = checkList.getSortPoints();
%>					<tr>
						<td valign="top">
							<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
								<tr>
<%
	for(int i = 0; i < 3; i++) {
    		if(i == 1) {
%>    		    					<td width="2%"></td>
<%
	}else {
%>									<td width="49%" valign="top">
										<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
<%
	int count = (i == 0) ? 3 : 2;
		for(int j = 0; j < count; j++) {
		    PointData point = (PointData)points.next();
%>											<tr>
												<td valign="top">
												<%
													String page6 = "../component/utilitybox2top.jsp?title="+messages.getText(point.getText());
												%>
													<jsp:include  page='<%=page6%>' />
													<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
<%
	Iterator items = point.getSortItems();
	int checked = 0;
	while(items.hasNext()) {
		ItemData item = (ItemData)items.next();
		String id1 =  String.valueOf(item.getId())+"-1";
		String id2 =  String.valueOf(item.getId())+"-2";
		checked = 0;
		if(edit && vehicleCheck != null && trailerCheck != null) {
		    String valueItem = trailerCheck.getValueFromItem(item.getId());
			checked = (valueItem == null) ? 1 : Integer.parseInt(valueItem);							
		}else {
		    if(!Util.empty(form.getList())) {
		        if(form.getList().contains(id1)){
		            checked = 1;
		        }else {
		        	// Modificado:
			        if(form.getList().contains(id2)){
			            checked = 2;
			        }
		        }
		    }
		}
%>														<tr class="lineone">
															<td width="33%">
		   														<%=messages.getText(item.getText())%>
															</td>	
															<td width="33%">
				        										<input type="radio" name='<%=String.valueOf(trailer.getId())+"-"+String.valueOf(item.getId())%>' value='<%=String.valueOf(trailer.getId())+"-"+String.valueOf(item.getId())+"-1"%>' <%=(checked == 1) ? "checked" : "" %>/>	    				
																<%=messages.getText("generic.vehiclecheck.form.correct")%>
															</td>
															<td width="33%">
						        								<input type="radio" name='<%=String.valueOf(trailer.getId())+"-"+String.valueOf(item.getId())%>' value='<%=String.valueOf(trailer.getId())+"-"+String.valueOf(item.getId())+"-2"%>' <%=(checked == 2) ? "checked" : "" %>/>	    				
																<%=messages.getText("generic.vehiclecheck.form.incorrect")%>
															</td>
														</tr>
<%						if(items.hasNext()) {	
%>			      										<tr class="lineone" ><td align="right" colspan="3">&nbsp;</td></tr>
<%						}
					}
%>													</table>
													<jsp:include  page="../component/utilitybox2bottom.jsp" />
												</td>	
											</tr>	
<%					if(j < count-1) {	
%>     										<tr>
												<jsp:include  page="../component/spaceline.jsp" />
						  					</tr>
<%					}
				}
%>										</table>
									</td>
<%			}
		}
%>								</tr>
							</table>
						</td>
					</tr>	
				</table>
				</div>
				<jsp:include page="../component/utilityboxbottom.jsp"/>  		
			</td>
		</tr>
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
<%		}
%>		<tr> 
			<td height="40" colspan="3" valign="middle">
			<%
				String page7 = "../component/utilitybox2top.jsp?title="+messages.getText("generic.activity.observations");
			%>
				<jsp:include  page='<%=page7%>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
		            <tr> 
              			<td bgcolor="#f9f9f9">
              				<strong>
              					<font color="#FF0000" size="1" face="Arial, Helvetica, sans-serif"> 
				                	<html:textarea property="obs" cols="100" rows="5" />
                				</font>
                			</strong>
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
	   		<td align="right">
	   		    <input type="hidden" name="checkboxParamList" />   
	   			<button value="acept" onClick="javascript:setAction(document.forms['<%=formName%>']);" class="input"><%=messages.getText("generic.messages.accept")%></button>	 		                             
	 			<html:cancel styleClass="input"><%=messages.getText("generic.messages.cancel")%></html:cancel >
           	</td>                 							
   	  	</tr>              
	</table>
	</html:form>
	</center>
</body>
<%
	}	
%>	

</html:html>
