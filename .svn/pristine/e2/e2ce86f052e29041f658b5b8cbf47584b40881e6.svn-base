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
<html:html>
<%!
	Text messages; SystemAccess sys;
	UserSessionData userSessionData; 
	VehicleData  vehicle;
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();	
	
	String check = Util.checkPermission(sys,SecurityConstants.EDIT_VEHICLE);
	String check1 = Util.checkPermission(sys,SecurityConstants.MODIFY_VEHICLE);	
	String check2 = Util.checkPermission(sys,SecurityConstants.CHECKLIST_VEHICLE);	
	
	if ((check!=null) && (check1!=null) && (check2!=null))  {
		response.sendRedirect(request.getContextPath()+check);	
	}else {
		messages=sys.getText();
	
		String idCheck = request.getParameter("check");
		if(Util.empty(idCheck)) {
		    idCheck = String.valueOf(session.getAttribute("check"));
		}

		session.setAttribute("url","vehicle/CimsaCheckView.jsp?check="+idCheck);
		
		VehicleCheckData vehicleCheck = sys.getVehicleReportFacade().findVehicleCheck(new Integer(idCheck),userSessionData);
		session.setAttribute("id",String.valueOf(vehicleCheck.getVehicle().getId()));
%>	
<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<script>
	function deleteIfConfirm(form,msg){
		if(confirm(msg)) {
			form.submit();
		}
	} 
</script>

<html:form action="/TruckCheckListDelete">
	<html:hidden property="check" value='<%=idCheck%>'/>
</html:form>	

<form action='<%="layout.jsp?refer=/vehicle/CimsaCheckForm.jsp?type=edit&check="+vehicleCheck.getVehicleCheckId().toString()%>' name='edit' method="post">
</form>	

<body>
	<center>
	<table width="768" border="0" cellspacing="0" cellpadding="0">  
		<tr class="guide1">
			<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.activity.form.truckchecklist")%>' />
  		</tr>
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
		<tr> 
			<td height="40" colspan="5" valign="middle">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.activity.form.tranningonthejob.proofdata")%>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
   	 				<tr> 
       					<td width="50%" align="left" class="linetwo">        
   							<%=messages.getText("generic.activity.date")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<%=Util.formatDate(vehicleCheck.getDate())%>
						</td>
   					</tr>
			      	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
     				<tr> 
        				<td width="50%" align="left" class="linetwo">        
       						<%= messages.getText("generic.driver.name")%>
						</td>
						<td width="50%" align="right" class="linetwo">		
							<%=vehicleCheck.getName()%>
        				</td>
     				</tr>
			      	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
     				<tr class="linetwo"> 
        				<td width="50%" align="left">        
       						<%=messages.getText("generic.activity.form.tranningonthejob.trade")%>
						</td>
						<td width="50%" align="right">		
							<%=(vehicleCheck.getVehicle().getTrade().equals("generic.messages.nodata")) ? messages.getText(vehicleCheck.getVehicle().getTrade().getResourceBundleId()) : vehicleCheck.getVehicle().getTrade().getResourceBundleId()%>
        				</td>
     				</tr>
			      	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
      				<tr class="linetwo">
        				<td width="50%" align="left">        
       						<%= messages.getText("generic.vehicle.model")%>
						</td>
						<td width="50%" align="right">		
							<%=vehicleCheck.getVehicle().getModel()%>                                                     
        				</td>
     				</tr>     
			      	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
      				<tr class="linetwo">  
        				<td width="50%" align="left">        
					        <%= messages.getText("generic.activity.form.vehiclecheck.plate")%>
						</td>
						<td width="50%" align="right">		
							<%=vehicleCheck.getVehicle().getMatricula()%>                                                     
        				</td>
     				</tr>
				</table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>  
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
<%		CheckListData checkList = sys.getVehicleReportFacade().getCheckList(CheckListData.CIMSA_CHECKLIST,sys.getUserSessionData());
		Iterator points = checkList.getSortPoints();
%>		<tr>
			<td valign="top">
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
					<tr>
<%		for(int i = 0; i < 3; i++) {
    		if(i == 1) {
%>    		    		<td width="2%"></td>
<%    		}else {
%>						<td width="49%" valign="top">
							<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
<%				for(int j = 0; j < 5; j++) {
				    PointData point = (PointData)points.next();
%>								<tr>
									<td valign="top">
										<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText(point.getText())%>' />
										<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
<%					Iterator items = point.getSortItems();
					while(items.hasNext()) {
						ItemData item = (ItemData)items.next();
						int value = -1;
						if(vehicleCheck != null) {
							String checkValue = vehicleCheck.getValueFromItem(item.getId());
							value = (checkValue == null) ? -1 : Integer.parseInt(checkValue.trim());
						}
%>											<tr class="linetwo">
												<td width="60%">
		   											<%=messages.getText(item.getText())%>
												</td>	
												<td width="40%" align="right">
<%						switch(item.getId().intValue()) {
							case 4802: case 4803: case 4804: case 4805: case 4806: case 4795:	
%>								<%=value%>
<%								break;
							case 4770:	
								switch(value) {
									case 1:
%>											Refresco
<%										break;	
									case 2:
%>											Agua
<%										break;	
									case 3:
%>											JDV
<%										break;	
									case 4:
%>											CFDLA
<%										break;	
									case 5:
%>											No tiene
<%										break;	
								}
								break;
							default:
								switch(value) {
									case 1:
%>											<%=messages.getText("generic.messages.rigth")%>
<%										break;	
									case 2:
%>											<%=messages.getText("generic.messages.wrong")%>
<%										break;	
								}
								break;
						}
					}
				    
%>										</table>
										<jsp:include  page="../component/utilityboxbottom.jsp" />
									</td>	
								</tr>	
<%				    if(j < 4) {	
%>     							<tr>
									<jsp:include  page="../component/spaceline.jsp" />
						  		</tr>
<%					}
				}
%>							</table>
						</td>
<%			}
		}
%>					</tr>
				</table>
			</td>
		</tr>	
	  	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
       	<tr>   		
	   		<td align="right">
	   			<a href='<%="layout.jsp?refer=/vehicle/VehicleView.jsp?id="+vehicleCheck.getVehicle().getId().toString()%>'>
		   			<input name="button" type="button" value='<%=messages.getText("generic.messages.accept")%>' class="input"/>	 		                             
				</a>
				<% if(userSessionData.checkPermission(SecurityConstants.MODIFY_VEHICLE) &&
						userSessionData.checkPermission(SecurityConstants.CHECKLIST_VEHICLE)){
						%>	   			
	   			<input name="button" type="button" value='<%=messages.getText("generic.messages.edit")%>' class="input" onclick="javascript:document.forms['edit'].submit();"/>	 		                             
	   			<input name="button2" type="button" value='<%=messages.getText("generic.messages.delete")%>' onClick="deleteIfConfirm(document.forms['TruckCheckListDeleteForm'],'<%=messages.getText("trailer.checklist.delete.confirm")%>');" class="input"/>
	   			<% } %>	 		                             		   			
           	</td>                 							
   	  	</tr>              
	</table>
	</center>
</body>
<%
	}	
%>	

</html:html>
