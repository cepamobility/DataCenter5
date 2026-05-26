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
	import="business.vehicle.VehicleReportFacadeBean"
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
	
	String check = Util.checkPermission(sys,SecurityConstants.CHECKLIST_VEHICLE);	
	
	if (check!=null) { 
		response.sendRedirect(request.getContextPath()+check);	
	}else {
		messages=sys.getText();
	
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		String checkId = (String)request.getParameter("check");

		VehicleReportFacade vehicleReport = sys.getVehicleReportFacade(); 
		vehicleCheck = vehicleReport.findVehicleCheck(new Integer(checkId),userSessionData);
		VehicleData vehicleData = vehicleCheck.getVehicle();
		
	 	String fileName = messages.getText("syngenta.checklist.titlehtml")+"_"+vehicleCheck.getVehicle().getMatricula().trim().replaceAll(" ", "_")+"_"+Util.formatDate(vehicleCheck.getDate())+".pdf";
%>	


<html:html>
	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	</head>

	<body>
		<center>
			<html:form action="/SyngentaSendReport" method="post" >
				<html:hidden property="check" value='<%=checkId%>' />	
				<html:hidden property="fileName" value='<%=fileName%>' />	
				<table width="700" border="0" cellspacing="2" cellpadding="2">  
					<tr>
						<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("syngenta.checklist.sendreport")%>' />
		  			</tr>
					<tr> 
						<td height="40" colspan="3" valign="middle">
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("syngenta.checklist.title")%>' />
							<table width="100%" height="60" border="0" cellpadding="2" cellspacing="2">
			   	 				<tr> 
			       					<td width="50%" align="left" class="texts">        
			   							<%=messages.getText("generic.activity.date")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
									</td>
									<td width="50%" align="right" class="linetwo">
										<%=(vehicleCheck != null && vehicleCheck.getDate() != null) ? Util.formatDate(vehicleCheck.getDate()) : ""%>	
									</td>
			   					</tr>
			     				<tr> 
			        				<td width="50%" align="left" class="texts">        
			       						<%= messages.getText("generic.driver.name")%>
									</td>
									<td width="50%" align="right" class="linetwo">		
										<%=(vehicleCheck == null) ? "" : vehicleCheck.getName() %>                                     
			        				</td>
			     				</tr>
			     				<tr> 
			        				<td width="50%" align="left" class="texts">        
			       						<%= messages.getText("generic.vehicle.vehicledata.plate")%>
									</td>
									<td width="50%" align="right" class="linetwo">		
										<%=(vehicleData == null) ? "---" : vehicleData.getMatricula() %>                                     
			        				</td>
			     				</tr>
			     				<tr> 
			        				<td width="50%" align="left" class="texts">        
			       						<%= messages.getText("generic.vehicle.vehicledata.trade")%>
									</td>
									<td width="50%" align="right" class="linetwo">		
										<%=(vehicleData == null || vehicleData.getTrade() == null) ? "---" : vehicleData.getTrade().getResourceBundleId() %>                                     
			        				</td>
			     				</tr>
			     				<tr> 
			        				<td width="50%" align="left" class="texts">        
			       						<%= messages.getText("generic.vehicle.vehicledata.model")%>
									</td>
									<td width="50%" align="right" class="linetwo">		
										<%=(vehicleData == null || vehicleData.getModel() == null) ? "---" : vehicleData.getModel() %>                                     
			        				</td>
			     				</tr>
							</table>
							<jsp:include  page="../component/utilityboxbottom.jsp" />
						</td>
					</tr>  
					<tr> 
						<td height="40" colspan="3" valign="middle">
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("syngenta.checklist.title")%>' />
							<table width="100%" height="60" border="0" cellpadding="2" cellspacing="2">
			   	 				<tr> 
			       					<td width="50%" align="left" class="texts">        
			   							<%= messages.getText("generic.sendemail.address")%>
									</td>
									<td width="50%" align="right" class="linetwo">
										<html:text property="address" styleClass="input" size="50"></html:text>                                     
									</td>
			   					</tr>
			   	 				<tr> 
			       					<td width="50%" align="left" class="texts">        
			   							<%= messages.getText("generic.sendemail.subject")%>
									</td>
									<td width="50%" align="right" class="linetwo">
										<html:text property="subject" styleClass="input" size="50"></html:text>                                     
									</td>
			   					</tr>
			   	 				<tr> 
			       					<td width="50%" align="left" class="texts">        
			   							<%= messages.getText("generic.sendemail.attachment")%>
									</td>
									<td width="50%" align="right" class="linetwo">
										<%=fileName%>                                     
									</td>
			   					</tr>
			   	 				<tr> 
			       					<td width="50%" align="left" class="texts">        
			   							<%= messages.getText("generic.sendemail.body")%>
									</td>
								</tr>
			   	 				<tr> 
									<td colspan="2" align="left" class="linetwo">
										<html:textarea property="body" styleClass="input" cols="120" rows="5"></html:textarea>                                     
									</td>
			   					</tr>
							</table>
							<jsp:include  page='../component/utilityboxbottom.jsp' />
						</td>
					</tr>
			       	<tr>   		
				   		<td align="right">
				   			<html:submit value='<%=messages.getText("generic.messages.send") %>' styleClass="input"/>			   			
				   			<html:cancel value='<%=messages.getText("generic.messages.cancel") %>' styleClass="input"/>			   			
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
