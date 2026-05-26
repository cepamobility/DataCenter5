<%@page language="java"
	errorPage="../exception.jsp"
	import="business.*"
	import="business.fleet.*" 
	import="communication.language.*"
	import="communication.security.*"
	import="communication.fleet.tables.*"
	import="communication.administration.user.*"
	import="communication.administration.corporation.tables.*"
	import="communication.vehicle.tables.*"
	import="presentation.translator.web.user.*"
	import="presentation.translator.web.util.*"
	import="java.util.*"
	import="communication.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>
<%! Text messages; String pathLanguage;
	FleetReportFacade fleetReport; 
	SystemAccess sys;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData=sys.getUserSessionData();
	String check = Util.checkPermission(sys,SecurityConstants.EDIT_FLEET);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		String id=(String)request.getParameter("id");
		if(id==null){
			id=(String)session.getAttribute("id");
			session.removeAttribute("id");
		}
		session.setAttribute("url","fleet/FleetView.jsp?id="+id);
		if(id!=null) 	{	
			messages=sys.getText();
			fleetReport = sys.getFleetReportFacade();
			Object[] data = fleetReport.findFleetData(new Integer(id),sys.getUserSessionData());
			if(data != null) {
				boolean linetwo = false;
				int type = fleetReport.getFleetType(new Integer(id),sys.getUserSessionData());
				int principalCount =  sys.getUserSessionData().getFilter().getPrincipalCount();
				String title = (type == FleetData.COLECTIVE) ? "generic.fleet.colectivefleet" : "generic.fleet.individualfleet";
%>
	<LINK REL=StyleSheet HREF="./util/css/estilo.css" TYPE="text/css">
	<head>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<META http-equiv="Expires" content="-1">		
	</head>
	<script language="javascript" src='./util/js/Prepared_Parameters.js' type='text/javascript' >
	</script>
	<script>
		function setAction(form,action){
			form.action.value=action;
		}
	</script>
	<body bgcolor="#FFFFFF">
		<html:form action="/Fleetdelete" >
			<html:hidden property="id" value='<%=id%>' />
			<html:hidden property="checkboxParamList" />		
		</html:form>
		<form action="layout.jsp?refer=/fleet/FleetExclusion.jsp" name="exclusion" method="post">
			<input type="hidden" name="checkboxParamList" value='<%=id%>' />		
			<input type="hidden" name="action" />								
		</form>	
		<form action="layout.jsp?refer=/fleet/FleetEdit.jsp" name='edit' method="post">
			<input type="hidden" name="id" value='<%=id%>' />
			<input type="hidden" name="target" value="FleetView" />
		</form>	
		<form action="layout.jsp?refer=/fleet/FleetDeleteDivisions.jsp" name='deletedetail' method="post">
			<input type="hidden" name="id" value='<%=id%>' />
		</form>	
		<table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1">
				<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText(title)%>' />
  			</tr>
      		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
  			<tr>
				<jsp:include  page='<%="fleetOptions.jsp?id="+id+"&excluded="+String.valueOf(data[3] != null)+"&type="+type%>' />
			</tr>
      		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
<%				// ExclusionDate
				if(data[3] != null) {
%>  		<tr>
				<td colspan="5">
					<span class="red">&nbsp;&nbsp;<%=messages.getText("generic.fleet.inactivefleet") %></span>
				</td>
	  		</tr>
			<tr>
				<td colspan="5">
					<span class="red">&nbsp;&nbsp;<%=messages.getText("generic.fleet.exclusiondate") +": "+Util.formatDate((Date)data[3])%>
					</span>				
				</td>
	  		</tr>
<% 				} 
%>			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
	  		</tr>
  			<tr>
    			<td>
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.fleet.fleetdata")%>' />
						<table width="100%">
					      	<tr  class="linetwo">
					        	<th align="left"> <%=messages.getText("generic.fleet.fleetdata.name")%> </th>
					         	<td align="right"><%=data[0]%></td>
					      	</tr>
					      	<tr  class="space"><th align="right"></th><td align="right"></td></tr>
      						<tr  class="linetwo">
        						<th align="left" valign="top"><%=messages.getText("generic.fleet.fleetdata.description")%></th>
					         	<td align="right"><%=data[1]%></td>
      						</tr>
					      	<tr  class="space"><th align="right"></th><td align="right"></td></tr>
      						<tr  class="linetwo">
        						<th align="left"> <%=messages.getText("generic.fleet.fleetdata.period")%></th>
    				         	<td align="right"><%=data[2].equals(FleetData.MONTHLY) ? messages.getText("generic.fleet.fleetdata.monthly") : messages.getText("generic.fleet.fleetdata.byquarter")%></td>
      						</tr>
						</table>
					<jsp:include  page="../component/utilityboxbottom.jsp" />
        		</td>        			
          	</tr>
      		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
			<!--  Division Section -->
	  		<tr>
        		<td colspan="3">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.fleet.divisions")%>' />
        			<table width="100%" border="0" cellpadding="0" cellspacing="0">
<!--  Dibujo las Columnas de la Seccion de Divisiones -->
        				<tr>
	        				<td class="guide2"><%=messages.getText("corporation.divgeo.countries")%></td>	        				
<%				int[][] divOrgs = sys.getUserSessionData().getFilter().getPrincipalDivOrgs();			
				String[] attrs = sys.getUserSessionData().getFilter().getDivOrgLabels();
				for(int i = 0; i < principalCount; i++) {
				    int[] index = divOrgs[i];
				    int c = (principalCount > 3) ? 3 : principalCount;
				    if(!(sys.getCorporationId() == CorporationData.MONSANTO && i > 3)) {
%>							<td class="guide2">
								<%=messages.getText(attrs[(index[0]-1)*c+(index[1]-1)])%>
							</td>				    
<%					}
				}
%>							<!--  Exclusion Date -->
							<td class="guide2" align="center"><%=messages.getText("generic.driver.driverdata.exclusiondate")%><%=" "+messages.getText("generic.messages.dateformat")%></td>								            				
						</tr>	
		<!--  Coloco los registros de la Seccion de Divisiones -->
<%				Iterator it = fleetReport.getFleetDivisionData(new Integer(id),sys.getUserSessionData()).iterator();
				linetwo = false;
				if(it.hasNext()) {
					while(it.hasNext()) {
						Object[] divisionData = (Object[])it.next();
%>						<tr class='<%=(linetwo) ? "linetwo" : "lineone" %>'>
							<td><%=messages.getText((String)divisionData[0])%></td>								
<%						for(int i = 1; i <= principalCount; i++) {
%>							<td><%=divisionData[i]%></td>				    
<%						}
%>							<td align="center">
								<%=(divisionData[principalCount+1]== null) ? "-" : Util.formatDate((Date)divisionData[principalCount+1])%>							
							</td>					
<%					}
%>						</tr>
<%					linetwo = !linetwo;
				}else {
					// Assigned Vehicle - No Data
%>						<tr>
							<td class="linetwo" colspan="4">
								<%=messages.getText("generic.messages.notresult")%>
							</td>
						</tr>
<%				}			
%>					</table>
					<jsp:include  page="../component/utilityboxbottom.jsp" />
				</td>
      		</tr>
      		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
<%				if(type == FleetData.INDIVIDUAL) {		
							
						Collection individualFleet = fleetReport.getVehiclesByFleet(new Integer(id),sys.getUserSessionData());
						
	
%>	  		<tr>
        		<td colspan="3">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("web.fleet.vehicles")%>' />
        			<table width="100%" border="0" cellpadding="0" cellspacing="0">
        				
<%					it = individualFleet.iterator();
					linetwo = false;
					if(it.hasNext()) { %>
						
						<tr>
				   			<td class='<%=(linetwo)?"linetwo":"lineone"%>'  width='100%' colspan="4">
				   				<%=new Integer(individualFleet.size()).toString() + "  "+ messages.getText("web.fleet.vehicles") %>
				   				<br/>
				   			</td>
				   		</tr>
				   		
				   		<tr>
							<td class="guide2" width="25%"><%=messages.getText("generic.vehicle.vehicledata.registrationNumber")%></td>				            				
							<td class="guide2" width="25%"><%=messages.getText("generic.vehicle.vehicledata.trade")%></td>				            				
							<td class="guide2" width="25%"><%=messages.getText("generic.vehicle.vehicledata.model")%></td>				            				
							<td class="guide2" width="25%"><%=messages.getText("generic.vehicle.vehicledata.year")%></td>				            				
						</tr>	
				   		
						<%
					    while(it.hasNext()) {
							Object[] vehicle = (Object[])it.next();				    
%>  	  				<tr>
							<td class='<%=(linetwo)?"linetwo":"lineone"%>' width="25%" >
								<a href='layout.jsp?refer=/vehicle/VehicleView.jsp?id=<%=vehicle[0]%>'>
									<%=vehicle[1]%>
								</a>
							</td>				            				
							<td class='<%=(linetwo)?"linetwo":"lineone"%>' width="25%"><%=messages.getText((String)vehicle[9])%></td>				            				
							<td class='<%=(linetwo)?"linetwo":"lineone"%>' width="25%"><%=vehicle[9]%></td>				            				
							<td class='<%=(linetwo)?"linetwo":"lineone"%>' width="25%"><%=String.valueOf(vehicle[10])%></td>				            				
						</tr>	
<%							linetwo = !linetwo;
						}
					   %> 
					   
					    <%
					}else {
%>      				<tr>
							<td class="linetwo" colspan="4"><%=messages.getText("generic.messages.notresult")%></td>				            				
						</tr>				    
<%					}
%>					</table>
					<jsp:include  page="../component/utilityboxbottom.jsp" />
				</td>
      		</tr>
<%				}
%>    		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
  			<tr>
  			<!--  Historial -->
<%				if(userSessionData.isCepaUser()) {	
%>			    <td>
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.observations")%>' />
					<table width="100%">
						<tr>
							<td width='24%' class="guide2" align="center">
								<%=messages.getText("generic.driver.observation.date")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%>
							</td>
							<td width='24%' class="guide2" align="center">
								<%=messages.getText("generic.driver.observation.action")%>
							</td>
   							<td width='24%'	class="guide2" align="center">
   								<%=messages.getText("generic.driver.observation.user")%>
   							</td>
							<td width='24%'	class="guide2" align="center">
								<%=messages.getText("generic.driver.observation.comment")%>
							</td>
						</tr>
<%					Collection observations = sys.getFleetReportFacade().getObservations(new Integer(id),userSessionData);
					if(observations.size() == 0) {					
%>						<tr>
							<td colspan="3">
								<span class="linetwo"><%=messages.getText("generic.event.noobservations")%></span>
							</td>								
						</tr>
<%					}else {
    					Iterator itObservations = observations.iterator();
    					boolean lineone = false;
	    				while(itObservations.hasNext()) {
    					    FleetObservationData observation = (FleetObservationData)itObservations.next();
%>						<tr align=center align="right" class='<%=(lineone) ? "lineone" : "linetwo"%>'>
	          				<td width='24%' align="right">
	              				<%=Util.formatDate(observation.getDate())%>
              				</td>
<%							if (observation.getAction() == null) {
								observation.setAction(new Integer(LogActions.UPDATE));
							}
							switch(observation.getAction().intValue()) {
								case LogActions.CREATE: %>	
						    <td width='24%' align="right">
						    	<%=messages.getText("generic.log.create")%>
						    </td>
<%									break;
								case LogActions.UPDATE: %>		
						    <td width='24%' align="right">
						    	<%=messages.getText("generic.log.update")%>
						    </td>
<%									break;
								case LogActions.DELETE: %>	
						    <td width='24%' align="right">
						    	<%=messages.getText("generic.log.delete")%>
						    </td>
<%									break;
								case LogActions.INCLUDE:%>
						    <td width='24%' align="right">
						    	<%=messages.getText("generic.log.include")%> 
						    </td>
<%									break;
								case LogActions.EXCLUDE:
%>						    <td width='24%' align="right">
								<%=messages.getText("generic.log.exclude")%>
							</td>
<%									break;
								case LogActions.ADDVEHICLE: 	%>
						    <td width='24%' align="right">
								<%=messages.getText("generic.log.addvehicle")%>
							</td>
<%									break;
								case LogActions.DELETEVEHICLE: 	%>
						    <td width='24%' align="right">
								<%=messages.getText("generic.log.deletevehicle")%>
							</td>							
<%									break;
								case LogActions.ADDDIVISION: 	%>
						    <td width='24%' align="right">
								<%=messages.getText("generic.log.adddivision")%>
							</td>	
<%									break;
								case LogActions.DELETEDIVISION: 	%>
						    <td width='24%' align="right">
								<%=messages.getText("generic.log.deletedivision")%>
							</td>								
<% 									break; 
								case LogActions.EXCLUDEDIVISION: 	%>
					    	<td width='24%' align="right">
								<%=messages.getText("generic.log.excludedivision")%>
							</td>								
<% 									break; 
								case LogActions.INCLUDEDIVISION: 	%>
						    <td width='24%' align="right">
								<%=messages.getText("generic.log.includedivision")%>
							</td>								
<% 									break; 
								case LogActions.EXCLUDEVEHICLE: 	%>
						    <td width='24%' align="right">
								<%=messages.getText("generic.log.excludevehicle")%>
							</td>								
<% 									break;
								case LogActions.INCLUDEVEHICLE: 	%>
						    <td width='24%' align="right">
								<%=messages.getText("generic.log.includevehicle")%>
							</td>								
<% 									break; 
								default: %>
								 <td width='24%' align="right">
							    	<%=messages.getText("generic.log.update")%>
							     </td>
<%									break;						
							}
%>						    <td width='24%' align="right"><%=observation.getUser()%> </td>	
<%							String comment = (observation.getComment()==null) ? "----" : observation.getComment();
							if(comment.length() > 100) {
							    comment =  comment.substring(0,30)+"...";
							}
%>					        <td width='24%' align="right"><%=comment%> </td>						                
						</tr>
<%    					lineone = !lineone;
						}
					}
%>					</table>
					<jsp:include page="../component/utilityboxbottom.jsp"/>	
				</td>
<%				}
%>		  	</tr>
  			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
  			<tr>
				<jsp:include  page='<%="fleetOptions.jsp?id="+id+"&excluded="+String.valueOf(data[3] != null)+"&type="+type%>' />
			</tr>
      		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
        </table>
	</body>
<%			}
		}else {
%>	<jsp:include  page='<%="/util/allowedData.jsp?message=fleet.error.divisions" %>'/>
<%		}
	}
%>
</html:html>

