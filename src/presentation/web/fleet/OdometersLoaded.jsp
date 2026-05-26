<%@page import="communication.driver.tables.DriverData"%>
<%@page language="java" 
	errorPage="../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="business.fleet.*"
	import="business.vehicle.*"
	import="business.datatype.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.fleet.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.datatype.tables.*"
	import="communication.datatype.*"
	import="communication.vehicle.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"

%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	 	<title> CEPA International</title>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<style type="text/css">
<!--	body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
		}
		.contain {
			width: 100%; 
			height: 100%;
		}
 -->
		</style>
	</head>
<%!
	RequestDispatcher dispatcher; String context;
	Text messages; SystemAccess sys;
	String period;String year;String vehicle;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.DRIVER_INSERT_ODOMETER);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		int corporationId = sys.getCorporationId();
		DriverData driver = sys.getUserSessionData().getDriverData();

		vehicle = (Util.empty(request.getParameter("vehicle"))) ? (String)session.getAttribute("vehicle") : request.getParameter("vehicle");
	    period = (Util.empty(request.getParameter("period"))) ? (String)session.getAttribute("period") : request.getParameter("period");
	    year = (Util.empty(request.getParameter("year"))) ? (String)session.getAttribute("year") : request.getParameter("year");
		session.setAttribute("url","fleet/OdometersLoaded.jsp?vehicle="+vehicle+"&period="+period+"&year="+year);
		
	    VehicleReportFacade vehicleReport = sys.getVehicleReportFacade();
	    FleetReportFacade fleetReport = sys.getFleetReportFacade();

	    VehicleData data = vehicleReport.findVehicleByPrimaryKey(new Integer(vehicle),sys.getUserSessionData());
		messages = sys.getText();
		String[] odometerValues = fleetReport.findVehicleOdometerValues(data,period,year,sys.getUserSessionData());

		boolean isMonsantoBrasil = (data.getCountryId().intValue() == CountryData.BRASIL_ID.intValue()) && sys.isCorporation(CorporationData.MONSANTO);
		boolean monsantoLAdriver = corporationId == CorporationData.MONSANTO && 
		(driver.getCountryName().equals(CountryData.ARGENTINA) || 
		driver.getCountryName().equals(CountryData.PARAGUAY) || 
		driver.getCountryName().equals(CountryData.BOLIVIA) || 
		driver.getCountryName().equals(CountryData.URUGUAY));
%>
	<body>
	 	<table width="100%" height="90" border="0" cellpadding="0" cellspacing="0" class="default">
		 	<form name="logout" action="../login.jsp" method="post">
	 		</form>
			<form name="home" action="./layout.jsp" method="post">
			</form>		
  			<tr>
    			<td colspan="2">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" valign="top">
      					<tr>
        					<td><jsp:include  page='<%="../topMenu.jsp?home=true"%>'/></td>
      					</tr>
    				</table>	
    			</td><!--end the top�s table -->
  			</tr>
  		</table>
  		<br>
  		<br>	
		<form name="edit" method="post" action="OwnOdometerValue.jsp">
			<input name="vehicle" type="hidden" value='<%=vehicle%>' />
			<input name="period" type="hidden" value='<%=period%>' />
			<input name="year" type="hidden" value='<%=year%>' />
		</form>
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1">
				<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.fleet.odometers")+" "+messages.getText("generic.month."+String.valueOf(period))+" - "+year%>' />
	  		</tr>
	  		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
			</tr>
			<tr>
				<td colspan="3" width="600">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.vehicledata")%>' />
						<table>
							<tr>
								<td width="290" class="linetwo">
									<table width="100%">
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.plate")+":"%> </th>
				         					<td align="right" class="linetwo"><%=data.getMatricula()%></td>
			         					</tr>
		      							<tr  class="space">
		        							<th align="right">&nbsp;</th>
		        							<td align="right">&nbsp;</td>
		      							</tr>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.vehicle.vehicledata.trade")+":"%> </th>
				         					<td align="right" class="linetwo"><%=messages.getText(data.getTrade().getResourceBundleId())%></td>
										</tr>
									</table>
								</td>
								<td width="20"></td>
								<td width="290">
									<table width="100%">
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.model")+":"%> </th>
				         					<td align="right" class="linetwo"><%=data.getModel()%></td>
										</tr>
		      							<tr  class="space">
		        							<th align="right">&nbsp;</th>
		        							<td align="right">&nbsp;</td>
		      							</tr>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.vehicle.vehicledata.year")+":"%> </th>
				         					<td align="right" class="linetwo"><%=data.getYear()%></td>
										</tr>				  
									</table>
								</td>
							</tr>
						</table>    
					<jsp:include page="../component/utilityboxbottom.jsp"/>	
				</td>
			</tr>
	  		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
			</tr>
			<tr>
				<td colspan="3" width="600">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("fleet.odometer.data")%>' />
						<table width="100%">
<%
	if(!isMonsantoBrasil)  {
%>							<tr class="lineone">
								<td colspan="2">
									<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("fleet.odometer.lastdata")%>' />
										<table width="100%" border="0">
											<tr>
												<th align="left" class="lineone"><%=messages.getText("fleet.odometer.lastvalue")%></th>
												<td align="right" class="lineone"><%=(Util.empty(odometerValues[3])) ? "---" :  odometerValues[3]%></td>
											</tr>
			      							<tr  class="space">
			        							<th align="right">&nbsp;</th>
			        							<td align="right">&nbsp;</td>
			      							</tr>
											<tr>
												<th align="left" class="lineone"><%=messages.getText("fleet.odometer.lastdate")%></th>
												<td align="right" class="lineone"><%=(Util.empty(odometerValues[4]) || Util.empty(odometerValues[5]))? "---" :  messages.getText("generic.month."+odometerValues[4]) + " / " +odometerValues[5]%></td>
											</tr>
										</table>
									<jsp:include  page="../component/utilitybox2bottom.jsp" />
								</td>
							</tr>
					  		<tr>
								<jsp:include  page="../component/spaceline.jsp" />
							</tr>
<%				}
%>							<tr class="lineone">
								<td colspan="2" >
									<jsp:include  page='<%="../component/utilitybox5top.jsp?title="+messages.getText("fleet.odometer.insertedvalues")+" "+messages.getText("generic.month."+String.valueOf(period))+" - "+year%>' />
									<table width="100%" border="0">
										<tr>
											<th align="left" class="lineone"><%=messages.getText("fleet.odometer.value")%></th>
											<td align="right" class="lineone">
												<%=odometerValues[0]%>
											</td>
										</tr>
<%				if(!isMonsantoBrasil) {
%>		      							<tr  class="space">
		        							<th align="right">&nbsp;</th>
		        							<td align="right">&nbsp;</td>
		      							</tr>
										<tr>
											<th align="left" class="lineone"><%=messages.getText("fleet.odometer.road.miles")%></th>
											<td align="right" class="lineone"><%=odometerValues[6]%></td>
										</tr>
		      							<tr  class="space">
		        							<th align="right">&nbsp;</th>
		        							<td align="right">&nbsp;</td>
		      							</tr>
										<tr>
											<th align="left" class="lineone"><%=messages.getText("fleet.odometer.road.km")%></th>
											<td align="right" class="lineone"><%=odometerValues[7] %></td>
										</tr>	
		      							<tr  class="space">
		        							<th align="right">&nbsp;</th>
		        							<td align="right">&nbsp;</td>
		      							</tr>
<%				}
				if(sys.isCorporation(CorporationData.ALLERGAN)) {
%>		
										<tr class="lineone">
											<td colspan="2" >
												<jsp:include  page='<%="../component/utilitybox5top.jsp?title="+messages.getText("fleet.odometer.fuel")%>' />
												<table width="100%" border="0">
													<tr>
														<th align="left" class="lineone"><%=messages.getText("fleet.odometer.fueltype")%></th>
														<td align="right" class="lineone">
															<%=(odometerValues[8] == null) ? "---" : messages.getText(odometerValues[8])%>
														</td>
													</tr>
														<tr  class="space">
														<th align="right">&nbsp;</th>
														<td align="right">&nbsp;</td>
														</tr>
													<tr>
														<th align="left" class="lineone"><%=messages.getText("datatype.volumeunit.liters")%></th>
														<td align="right" class="lineone">
															<%=(odometerValues[9] == null) ? "---" : odometerValues[9]%>
														</td>
													</tr>
												</table>
												<jsp:include  page="../component/utilitybox2bottom.jsp" />
											</td>
										</tr>
<%			} else if (sys.isCorporation(CorporationData.MONSANTO)) {
				if(!sys.getUserSessionData().getDriverData().getCountryName().equals(CountryData.BRASIL)) {
%>										<tr>
											<th align="left" class="lineone"><%=messages.getText("fleet.odometer.events")%></th>
											<td align="right" class="lineone">
												<%=odometerValues[1]%>
											</td>
										</tr>
<%				}
			} else {
%>										<tr>
											<th align="left" class="lineone"><%=messages.getText("fleet.odometer.accidents")%></th>
											<td align="right" class="lineone">
												<%=odometerValues[1]%>
											</td>
										</tr>
		      							<tr  class="space">
		        							<th align="right">&nbsp;</th>
		        							<td align="right">&nbsp;</td>
		      							</tr>
										<tr>
											<th align="left" class="lineone"><%=messages.getText("fleet.odometer.incidents")%></th>
											<td align="right" class="lineone">
												<%=odometerValues[2]%>
											</td>
										</tr>
<%			}
%>									</table>
									<jsp:include  page="../component/utilitybox2bottom.jsp" />
								</td>
							</tr>
						</table>
					<jsp:include page="../component/utilityboxbottom.jsp"/>	
				</td>
			</tr>
	  		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
			</tr>
	      	<tr>
				<td colspan="3" align="right">
<%			Calendar c = Calendar.getInstance();
			boolean edit = false;
			switch(sys.getCorporationId()) {
				case CorporationData.JJ:
					edit = c.get(Calendar.DATE) <= OdometerData.END_ODOMETER_JJ;
					break;
				case CorporationData.ALLERGAN:
					edit = c.get(Calendar.DATE) < OdometerData.END_ODOMETER_ALLERGAN;
					break;
				case CorporationData.MONSANTO:
					if(sys.getUserSessionData().getDriverData().getCountryName().equals(CountryData.BRASIL)) {
						edit = c.get(Calendar.DATE) >= OdometerData.START_ODOMETER;
					}else {
						edit = c.get(Calendar.DATE) < OdometerData.END_ODOMETER_MONSANTO_ARG;
					}
			}
			System.out.println(c.get(Calendar.DATE)+" "+edit);
			if(edit) { %>				
					<input type="button" onclick="javascript:document.forms['edit'].submit();" value='<%=messages.getText("generic.messages.modifydata")%>' class="input"/>
<%			} %>				
					<a href="../vehicle/AssociatedVehicles.jsp">
						<input type="button" value='<%=messages.getText("generic.messages.exit")%>' class="input"/>
					</a>
				</td>
      		</tr>
	      	<tr>
				<jsp:include  page="../component/spaceline.jsp" />
      		</tr>
	    </table>
	</body>
<%	}
%>	
</html>
