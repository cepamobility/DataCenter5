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
	String idVehicle = (String)request.getParameter("idVehicle");
	String plate = (String)request.getParameter("plate");
	Collection col = vehicleReportFacade.findVehicleDriversLogList(new Integer(idVehicle), sys.getUserSessionData());

%>
		<table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<jsp:include  page='<%="../component/titlecomponent.jsp?colspan=5&title="+messages.getText("web.vehicle.view")+" "+plate%>' />	
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
%>					    <td width='10%'	class="guide2"><%=messages.getText(principalLabels[z])%></td>
<% 		} 
%>					    
						<td width='10%'	class="guide2">
						   	<%=messages.getText("generic.driver.driverdata.fromdate")%>
 						    <div class="texts">
 						    <%=messages.getText("generic.messages.dateformat")%>
 						    </div>
 						</td>
 						<td width='10%'	class="guide2">
						   	<%=messages.getText("generic.driver.driverdata.todate")%>
 						    <div class="texts">
 						    <%=messages.getText("generic.messages.dateformat")%>
 						    </div>
 						</td>
 					</tr>
	              		
<%		

		if(col == null || col.size() == 0){
%>			    	<tr>
      					<td class="linetwo" colspan='<%= (5 + principalLabels.length) %>'>
	      					<%=messages.getText("generic.messages.notresult")%>
     					</td>
     				</tr>
<%		}else {
			Iterator iter = col.iterator();
			int count = 1;
%>
<%			while(iter.hasNext()){
			    VehicleDriverListData vehicleLog = (VehicleDriverListData)iter.next();
			    DriverMainAttributes driver = vehicleLog.getDriver();
%>        			<tr align=center align="right" class='<%=(count % 2 == 0) ? "lineone" : "linetwo"%>'>              
               		
<%              if (driver == null){ 
						int colspan = 3 + principalLabels.length;
						%>
						<td align="left" colspan='<%=colspan%>'>
                			<%=messages.getText("generic.messages.widthoutdriverassigned")%>
                		</td>
<%				}else{ %>
					
						<td width='15%' align="left">
<%					if(sys.getUserSessionData().checkPermission(SecurityConstants.EDIT_DRIVER)) {
%>	              			<a href='layout.jsp?refer=/driver/DriverView.jsp?id=<%=driver.getId()%>' > <%=driver.getFirstName()%> </a>
<%					}else {		
%>                  		<%=driver.getFirstName()%>
<%					}				
%>              		</td>
              			<td width='15%' align="left">
	<%				if(sys.getUserSessionData().checkPermission(SecurityConstants.EDIT_DRIVER)) {
	%>	              			<a href='layout.jsp?refer=/driver/DriverView.jsp?id=<%=driver.getId()%>' > <%=driver.getLastName()%> </a>
	<%				}else {		
	%>		                  	<%=driver.getLastName()%>
	<%				}			
	%>              		</td>
							<td width='10%' align="right">
					      		<%=(driver.getCountryName() != null)?messages.getText(driver.getCountryName()):"---"%> 
			               	</td>
	<% 				for(int z = 1; z <= principalLabels.length; z ++){ 
	%>		               	<td width='10%' align="right">
								<%=(driver.getDivOrgItem(z) != null)?driver.getDivOrgItem(z):"---"%> 
							</td>
	<% 				}
				}
%>		               	<td width='10%' align="right">
							<%=(vehicleLog.getFromDate() == null) ? "-" : Util.formatDate(vehicleLog.getFromDate())%> 
						</td>
		               	<td width='10%' align="right">
		               		<%=(vehicleLog.getToDate() == null) ? "-" : Util.formatDate(vehicleLog.getToDate())%> 
		               	</td>
        			</tr>
<%			count++;
			}
		}
%>		      					
				</table>
				<jsp:include page="../component/utilitybox3bottom.jsp"/>
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
                	<input name="button" type="button" onClick="javascript:history.back(1);" value='<%=messages.getText("generic.messages.back")%>' class="input"/>
                </td>
			</tr>
		</table>  
		
		  	