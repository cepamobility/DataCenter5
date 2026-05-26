<%@page language="java"
    errorPage="../exception.jsp"
    import="java.util.*"
	import="business.*"
	import="business.vehicle.*"
	import="communication.vehicle.tables.*"
	import="communication.driver.tables.*"
	import="communication.language.*"
	import="business.administration.user.*"
	import="communication.security.*"
	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.vehicle.*"
 	import="communication.administration.user.UserSessionData" 	
	
%>	


<%!
	SystemAccess sys;
	UserSessionData userSessionData;
	Text messages; 
%>
<%
	sys = (SystemAccess) session.getAttribute("SystemAccess");
	VehicleReportFacade vehicleReportFacade = sys.getVehicleReportFacade();
	messages = sys.getText();
	userSessionData = sys.getUserSessionData();
	String idVehicle = (String)request.getParameter("vehicle");
	VehicleData vehicle = vehicleReportFacade.findVehicleByPrimaryKey(new Integer(idVehicle), userSessionData);
	Collection col = vehicleReportFacade.findVehicleDriversLogList(new Integer(idVehicle), sys.getUserSessionData());

%>
		<form name="otherdriver" action="layout.jsp">
			<input type="hidden" name="refer" value="/driver/fineDriverSearch.jsp" />
			<input type="hidden" name="vehicle" value="<%=idVehicle%>" />
		</form>
		<form name="cancel" action="layout.jsp">
			<input type="hidden" name="refer" value="/event/EventList.jsp" />
		</form>
		<table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<jsp:include  page='<%="../component/titlecomponent.jsp?colspan=5&title="+messages.getText("event.selection.vehicle")%>' />	
		    </tr>
			<tr>    
				<jsp:include  page="../component/spaceline.jsp" />	
		    </tr>
			<tr>
				<td align="right" valign="bottom" class="texts" >
					<jsp:include  page='../component/utilitybox2top.jsp' />
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr class="lineone">
							<td width="48%">
								<table width="100%">
									<tr>
									   	<th align="left" class="lineone"> <%=messages.getText("generic.event.eventdata.plate")+":"%> </th>
			         					<td align="right" class="lineone"><%=vehicle.getMatricula()%></td>
			       					</tr>
									<tr>
									   	<th align="left" class="lineone"> <%=messages.getText("generic.vehicle.vehicledata.trade")+":"%> </th>
			         					<td align="right" class="lineone"><%=messages.getText(vehicle.getTrade().getResourceBundleId())%></td>
									</tr>
								</table>
							</td>
							<td width="2%"></td>
							<td width="48%">
								<table width="100%">
									<tr>
									   	<th align="left" class="lineone"> <%=messages.getText("generic.event.eventdata.model")+":"%> </th>
			         					<td align="right" class="lineone"><%=vehicle.getModel()%></td>
									</tr>
									<tr>
									   	<th align="left" class="lineone"> <%=messages.getText("generic.vehicle.vehicledata.year")+":"%> </th>
			         					<td align="right" class="lineone"><%=vehicle.getYear()%></td>
									</tr>				  
								</table>
							</td>
						</tr>
                	</table>
					<jsp:include page="../component/utilitybox2bottom.jsp"/>
				</td>
			</tr>
			<tr>    
				<jsp:include  page="../component/spaceline.jsp" />	
		    </tr>
		    <tr>
		    	<td>
		    	<!-- Muestro Listado: -->
		    	<table width="100%" cellpadding="0" cellspacing="0">
		    		<tr>
						<td valign="top">
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.vehicledriverlist")%>' />
							<table width="100%" cellpadding="0" cellspacing="0" border="0">
								<tr align="center">
							   	 	<td width='15%' class="guide2"><%=messages.getText("generic.driver.driverdata.firstname")%></td>
								    <td width='15%'	class="guide2"><%=messages.getText("generic.driver.driverdata.lastname")%></td>
								    <td width='10%'	class="guide2"><%=messages.getText("generic.driver.driverdata.country")%></td>
<% 		String[] principalLabels = sys.getUserSessionData().getFilter().getPrincipalLabels();
		for (int z = 0 ; z < principalLabels.length ; z++){ 
%>								    <td width='10%'	class="guide2"><%=messages.getText(principalLabels[z])%></td>
<% 		} 
%>					    
									<td width='10%'	class="guide2">
									   	<%=messages.getText("generic.driver.driverdata.fromdate")%>
			 						</td>
			 						<td width='10%'	class="guide2">
									   	<%=messages.getText("generic.driver.driverdata.todate")%>
			 						</td>
			 					</tr>
<%		if(col == null || col.size() == 0){
%>						    	<tr>
      								<td class="linetwo" colspan='<%= (5 + principalLabels.length) %>'>
				      					<%=messages.getText("generic.messages.notresult")%>
			     					</td>
			     				</tr>
<%		}else {
			Iterator iter = col.iterator();
			int count = 1;
			VehicleDriverListData lastDriverLog = null;
			boolean lineone = false;
			while(iter.hasNext()){
			    VehicleDriverListData vehicleLog = (VehicleDriverListData)iter.next();
              	if (lastDriverLog != null && lastDriverLog.getDriver() != null && (vehicleLog.getDriver() == null || !vehicleLog.getDriver().getId().equals(lastDriverLog.getDriver().getId()))) {
%>								<tr class='<%=(lineone) ? "lineone" : "linetwo"%>'>
									<td width='15%' align="left">
				              			<a href='<%="layout.jsp?refer=/event/FineCreate.jsp?vehicle="+vehicle.getId()+"&id="+lastDriverLog.getDriver().getId()%>' > <%=lastDriverLog.getDriver().getFirstName()%> </a>
				              		</td>
									<td width='15%' align="left">
				              			<a href='<%="layout.jsp?refer=/event/FineCreate.jsp?vehicle="+vehicle.getId()+"&id="+lastDriverLog.getDriver().getId()%>' > <%=lastDriverLog.getDriver().getLastName()%> </a>
				              		</td>
									<td width='10%' align="right">
							      		<%=(lastDriverLog.getDriver().getCountryName() != null)?messages.getText(lastDriverLog.getDriver().getCountryName()):"---"%> 
					               	</td>
<%	 				for(int z = 1; z <= principalLabels.length; z ++){ 
%>					               	<td width='10%' align="right">
										<%=(lastDriverLog.getDriver().getDivOrgItem(z) != null) ? lastDriverLog.getDriver().getDivOrgItem(z):"---"%> 
									</td>
<%					}
%>					               	<td width='10%' align="right">
										<%=(lastDriverLog.getFromDate() == null) ? "-" : Util.formatDate(lastDriverLog.getFromDate())%> 
									</td>
					               	<td width='10%' align="right">
					               		<%=(vehicleLog.getToDate() == null) ? "-" : Util.formatDate(vehicleLog.getToDate())%> 
					               	</td>
								</tr>	              			
<%             		lineone = !lineone;
				}
          		lastDriverLog = vehicleLog;
          	}              		
            if (lastDriverLog != null && lastDriverLog.getDriver() != null) {
%>								<tr class='<%=(lineone) ? "lineone" : "linetwo"%>'>
									<td width='15%' align="left">
				              			<a href='<%="layout.jsp?refer=/event/FineCreate.jsp?vehicle="+vehicle.getId()+"&id="+lastDriverLog.getDriver().getId()%>' > <%=lastDriverLog.getDriver().getFirstName()%> </a>
				              		</td>
									<td width='15%' align="left">
				              			<a href='<%="layout.jsp?refer=/event/FineCreate.jsp?vehicle="+vehicle.getId()+"&id="+lastDriverLog.getDriver().getId()%>' > <%=lastDriverLog.getDriver().getLastName()%> </a>
				              		</td>
									<td width='10%' align="right">
							      		<%=(lastDriverLog.getDriver().getCountryName() != null)?messages.getText(lastDriverLog.getDriver().getCountryName()):"---"%> 
					               	</td>
<%	 				for(int z = 1; z <= principalLabels.length; z ++) { 
%>					               	<td width='10%' align="right">
										<%=(lastDriverLog.getDriver().getDivOrgItem(z) != null)? lastDriverLog.getDriver().getDivOrgItem(z):"---"%> 
									</td>
<%					}
%>		               				<td width='10%' align="right">
										<%=(lastDriverLog.getFromDate() == null) ? "-" : Util.formatDate(lastDriverLog.getFromDate())%> 
									</td>
					               	<td width='10%' align="right">
					               		---
					               	</td>
								</tr>	              			
<%         	}
		}
%>							</table>
							<jsp:include page="../component/utilityboxbottom.jsp"/>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>    
				<jsp:include  page="../component/spaceline.jsp" />	
		    </tr>
			<tr>
				<td align="right" valign="bottom" class="texts" >
					<jsp:include  page='../component/utilitybox4top.jsp' />
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr align="right">
					   	 	<td class="right">
                				<input name="button" type="button" onClick="javascript:document.forms['otherdriver'].submit();" value='<%=messages.getText("event.driver.selectother")%>' class="input"/>
                				<input name="button" type="button" onClick="javascript:document.forms['cancel'].submit();" value='<%=messages.getText("generic.messages.cancel")%>' class="input"/>
                			</td>
                		</tr>
                	</table>
					<jsp:include page="../component/utilitybox4bottom.jsp"/>
                </td>
			</tr>
		</table>  
		
		  	