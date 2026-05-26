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
	import ="communication.administration.corporation.tables.CorporationData"
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

	function changeCheck(form){
		var option = form.elements['trailerCheck'].options[form.elements['trailerCheck'].selectedIndex].value;
		if(option == 1) {
			document.getElementById('trailer').style.display = 'block';
		}else {
			document.getElementById('trailer').style.display = 'none';
		}
	}
</script>
<html:html>
<%!
	RequestDispatcher dispatcher; 
	Text messages; SystemAccess sys;
	UserSessionData userSessionData; 
	VehiclePursuitRouteData vehicleCheck;
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	int corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();
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
		VehicleData vehicle;
		VehiclePursuitRouteListForm form = null;
		boolean edit = !Util.empty(request.getParameter("type")) && 
	request.getParameter("type").equals("edit"); 
		String formName = (edit) ? "VehiclePursuitRouteListEditForm" : "VehiclePursuitRouteListForm";
		
		String idVehicle =(String)request.getParameter("id");
		if(Util.empty(idVehicle)) {
		    idVehicle =(String)session.getAttribute("id");
		}else {
	session.setAttribute("id",idVehicle);
		}
		vehicle = sys.getVehicleReportFacade().
	findVehicleByPrimaryKey(new Integer(idVehicle),userSessionData);	
		
		if(edit) {
		    String checkId = request.getParameter("check");
		    if(Util.empty(checkId)) {
		        checkId = String.valueOf(session.getAttribute("check"));
		    }
	vehicleCheck = sys.getVehicleReportFacade().
		findVehiclePursuitRoute(new Integer(checkId),userSessionData);
	if(session.getAttribute(formName) == null) {
	    form = new VehiclePursuitRouteListForm(vehicleCheck,messages);
		session.setAttribute(formName,form);
	}else {
	    form = (VehiclePursuitRouteListForm)session.getAttribute(formName);
	}
		}else {			
	session.setAttribute("url","vehicle/VehiclePursuitRouteForm.jsp?id="+idVehicle);
	if(session.getAttribute(formName) == null) {
	    form = new VehiclePursuitRouteListForm(vehicle);
		session.setAttribute(formName,form);
	}else {
	    form = (VehiclePursuitRouteListForm)session.getAttribute(formName);
	}
		}
%>	
<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
	<center>
	<html:form action='<%=(edit) ? "/VehiclePursuitRouteListEdit" : "/VehiclePursuitRouteList"%>'>
	<html:hidden property="list" />
	<table width="768" border="0" cellspacing="0" cellpadding="0">  
		<tr class="guide1">
			<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.activity.form.vehiclepursuitroutelist")%>' />
  		</tr>
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
		<tr> 
			<td height="40" colspan="3" valign="middle">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.activity.form.tranningonthejob.proofdata")%>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
					<tr> 
        				<td width="50%" align="left" class="linetwo">        
       						<%=messages.getText("generic.activity.form.seguimientoenruta.corporation")%>
						</td>
						<td width="50%" align="right" class="linetwo">		
							<%
										Hashtable[] elements = communication.administration.corporation.util.Util.getDivOrgMap(
																								sys.getUserSessionData().getFilter().getGroupLines(),
																								sys.getUserSessionData().getFilter().getDivOrgCount());							
																					    Hashtable itemsDiv;
																					    if(corporationId == CorporationData.COLONVADE || corporationId == CorporationData.LOSPIQUES){
																						    itemsDiv = elements[6]; //7
																					    }else{
																					    	itemsDiv = elements[0];
																					    }
																						Collection items1 = new LinkedList();
																						Collection added = new LinkedList();
																						if(itemsDiv != null) {			
																							items1.add(new OptionItem(messages.getText("generic.messages.select"),""));
																					        Enumeration keys = itemsDiv.keys();
																						    while(keys.hasMoreElements()) {
																				        	    Integer countryId = (Integer)keys.nextElement();
																				        	    Iterator it1 = ((Collection)itemsDiv.get(countryId)).iterator();
																				        	    while(it1.hasNext()) {
																				        	      	ElementData element = (ElementData)it1.next();
																				        	      	if(!added.contains(element.getName())) {
																				        	      		added.add(element.getName());
																				        	     		items1.add(new OptionItem(element.getName(),element.getName())) ;
																				        	      	}
																				        	    }
																						    }
																						}	
																						Collections.sort((List)items1);
																						session.setAttribute("divorgs", items1);
									%>							
							<html:select property="divorg1" styleClass="input"  style="width:200;" >                                     
								<html:options collection="divorgs" property="value" labelProperty="label"/>
							</html:select>
        				</td>
     				</tr>					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
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
       						<%=messages.getText("generic.activity.form.seguimientoenruta.plate")%>
						</td>
						<td width="50%" align="right" class="linetwo">		
							<%=vehicle.getMatricula()%>
        				</td>
     				</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
     				<tr class="linetwo"> 
        				<td width="50%" align="left">        
       						<%=messages.getText("generic.activity.form.seguimientoenruta.evaluationtime")+" (hh:mm)"%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:text style="width:30;" property="evaluationhour" styleClass="input" />&nbsp;:&nbsp;                
							<html:text style="width:30;" property="evaluationminute" styleClass="input" />
						</td>
     				</tr>     
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
     				<tr> 
        				<td width="50%" align="left" class="linetwo">        
       						<%=messages.getText("generic.activity.form.seguimientoenruta.instructor")%>
						</td>
						<td width="50%" align="right" class="linetwo">		
							<html:text property="instructor" styleClass="input"  style="width:200;"/>                                   
        				</td>
     				</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>     				
     				<tr> 
        				<td width="50%" align="left" class="linetwo">        
       						<%=messages.getText("generic.activity.form.seguimientoenruta.evaluationarea")%>
						</td>
						<td width="50%" align="right" class="linetwo">		
							<html:text property="area" styleClass="input"  style="width:200;"/>                                   
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
	CheckListData checkList = sys.getVehicleReportFacade().
	getCheckList(CheckListData.PURSUIT_ROUTE_CHECKLIST,sys.getUserSessionData());
		Iterator points = checkList.getSortPoints();
%>		<tr>
			<td valign="top" width="100%">
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
					<tr>
		
						<td width="100%" valign="top">
							<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
<%
	// int count = (i == 0) ? 3 : 3;
		while (points.hasNext()){
		// for(int j = 0; j < count; j++) {
		    PointData point = (PointData)points.next();
%>								<tr>
									<td valign="top">
										<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText(point.getText())%>' />
										<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
<%
	Iterator items = point.getSortItems();
	while(items.hasNext()) {
		ItemData item = (ItemData)items.next();
		String id1 =  String.valueOf(item.getId())+"-1";
		String id2 =  String.valueOf(item.getId())+"-2";
		int checked = 0;
		if(edit) {
			checked = Integer.parseInt(vehicleCheck.getValueFromItem(item.getId()));
		}else{
		    if(!Util.empty(form.getList())) {
		        if(form.getList().contains(id1)){
		            checked = 1;
		        }else {
			        if(form.getList().contains(id1)){
			            checked = 1;
			        }
		        }
		    }
		}
%>											<tr class="linetwo">
												<td width="60%">
		   											<%=messages.getText(item.getText())%>
												</td>	
												<td width="20%">
				        							<input type="radio" name='<%=String.valueOf(item.getId())%>' value='<%=String.valueOf(item.getId())+"-1"%>' <%=(checked == 1) ? "checked" : "" %>/>	    				
													<%=messages.getText("generic.activity.form.seguimientoenruta.yes")%>
												</td>
												<td width="20%">
						        					<input type="radio" name='<%=String.valueOf(item.getId())%>' value='<%=String.valueOf(item.getId())+"-2"%>' <%=(checked == 2) ? "checked" : "" %> />	    				
													<%=messages.getText("generic.activity.form.seguimientoenruta.no")%>
												</td>
											</tr>
<%						if(items.hasNext()) {	
%>			      							<tr>
												<jsp:include  page="../component/spaceline.jsp" />
									  		</tr>
<%						}
					}
%>										</table>
										<jsp:include  page="../component/utilityboxbottom.jsp" />
									</td>	
								</tr>
								<tr>
									<jsp:include  page="../component/spaceline.jsp" />
						  		</tr>		     							
<%			
		}
%>				
											
						</table>
						</td>

					</tr>
				</table>
			</td>
		</tr>		  	
		<tr> 
			<td height="40" valign="middle">
				<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.activity.observations")%>' />
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
