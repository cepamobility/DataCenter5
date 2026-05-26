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

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html>
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
<%	sys=(SystemAccess)session.getAttribute("SystemAccess");
	messages = sys.getText();
%>	<script>
		function change(doConfirm,msg) {
			var form = document.forms['FleetOwnOdometerForm'];
			if(doConfirm == 1) {
				if(confirm(msg)) {
					form.submit();
				}
			}else {
				form.submit();
			}
		}
		function verifyOdometer(last,msg) {
			var form = document.forms['newOdometer'];
			var newValue = validateInteger(form.odometer.value);
			if(newValue == "") {
				alert('<%=messages.getText("fleet.error.invalid.odometer")%>');
				return;
			}
			var newAccidents = validateInteger(form.accidents.value);
			if(newValue == "") {
				alert('<%=messages.getText("fleet.error.invalid.accidents")%>');
				return;
			}
			var newIncidents = validateInteger(form.incidents.value);
			if(newValue == "") {
				alert('<%=messages.getText("fleet.error.invalid.incidents")%>');
				return;
			}
			if(newValue < last) {
				alert(msg);
			}else {
				form.submit();
			}
		}
		function validateInteger(value){
			value = parseInt(value);
		    if (isNaN(value)) {
		       return "";
		    }else{
		       return value;
		    }
		} 	
		</script>
	
<%
		String check = Util.checkPermission(sys,SecurityConstants.DRIVER_INSERT_ODOMETER);
			if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
			}else {
		
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		vehicle = (Util.empty(request.getParameter("vehicle"))) ? (String)session.getAttribute("vehicle") : request.getParameter("vehicle");
			    period = (Util.empty(request.getParameter("period"))) ? (String)session.getAttribute("period") : request.getParameter("period");
			    year = (Util.empty(request.getParameter("year"))) ? (String)session.getAttribute("year") : request.getParameter("year");
		session.setAttribute("url","fleet/OwnOdometers.jsp?vehicle="+vehicle+"&period="+period+"&year="+year);
		
			    VehicleReportFacade vehicleReport = sys.getVehicleReportFacade();
			    FleetReportFacade fleetReport = sys.getFleetReportFacade();

			    VehicleData data = vehicleReport.findVehicleByPrimaryKey(new Integer(vehicle),sys.getUserSessionData());
		if(data != null) {
			String[] odometerValues = fleetReport.findVehicleOdometerValues(data,period,year,sys.getUserSessionData());
			int type = (Util.empty(odometerValues[6])) ? 0 : 1;
			boolean isMonsantoBrasil = (data.getCountryId().intValue() == CountryData.BRASIL_ID.intValue()) && sys.isCorporation(CorporationData.MONSANTO);
	%>	

	<body>
	 	<table width="100%" height="90" border="0" cellpadding="0" cellspacing="0" class="default">
		 	<form name="logout" action="./login.jsp" method="post">
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
		<form name="cancel" action="OdometerValue.jsp" method="post">
			<input type="hidden" name="vehicle" value='<%=vehicle%>' />
			<input type="hidden" name="period" value='<%=String.valueOf(period)%>' />
			<input type="hidden" name="year" value='<%=year%>' />
		</form>
		<form name="newOdometer" action="OwnOdometerConfirmation.jsp" method="post">
		<input type="hidden" name="vehicle" value='<%=vehicle%>' />
		<input type="hidden" name="period" value='<%=String.valueOf(period)%>' />
		<input type="hidden" name="year" value='<%=year%>' />
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
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
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
	if(!isMonsantoBrasil) {
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
<%
	}
%>							<tr class="lineone">
								<td colspan="2">
									<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("fleet.odometer.currentdata")+" "+messages.getText("generic.month."+String.valueOf(period))+" - "+year%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr class="lineone">
											<th align="left" class="lineone"><%=messages.getText("fleet.odometer.value")%></th>
											<td align="right" class="lineone">
												<input type="hidden" name="lastodometer"  value='<%=(odometerValues[0] != null && odometerValues[0].length() > 0) ? odometerValues[0] : "-1"%>'/>
												<input type="text" name="odometer" size="10" class="input" value='<%=odometerValues[0]%>'/>
											</td>
										</tr>
		      							<tr  class="space">
		        							<th align="right">&nbsp;</th>
		        							<td align="right">&nbsp;</td>
		      							</tr>
<%
	if(!Util.empty(odometerValues[6]) && !isMonsantoBrasil) {
%>										<tr class="lineone">
											<th align="left" class="lineone"><%=messages.getText("fleet.odometer.road.miles")%></th>
											<td align="right" class="lineone"><%=odometerValues[6]%></td>
										</tr>
		      							<tr  class="space">
		        							<th align="right">&nbsp;</th>
		        							<td align="right">&nbsp;</td>
		      							</tr>
										<tr class="lineone">
											<th align="left" class="lineone"><%=messages.getText("fleet.odometer.road.km")%></th>
											<td align="right" class="lineone"><%=odometerValues[7]%></td>
										</tr>	
		      							<tr  class="space">
		        							<th align="right">&nbsp;</th>
		        							<td align="right">&nbsp;</td>
		      							</tr>
<%
	}
	if(sys.isCorporation(CorporationData.MONSANTO)) {
		if(sys.getUserSessionData().getDriverData().getCountryName().equals(CountryData.BRASIL)) {
%>										<input type="hidden" name="lastaccidents"  value='0'/>
										<input type="hidden" name="accidents"  value='0'/>
										<input type="hidden" name="lastincidents"  value='0'/>
										<input type="hidden" name="incidents"  value='0'/>
<%
	}else {
%>										<tr class="lineone">
											<th align="left" class="lineone"><%=messages.getText("fleet.odometer.events")%></th>
											<td align="right" class="lineone">
												<input type="hidden" name="lastaccidents"  value='<%=(odometerValues[1] != null && odometerValues[1].length() > 0) ? odometerValues[1] : "-1"%>'/>
												<input type="text" name="accidents" size="10" class="input" value='<%=odometerValues[1]%>'/>
											</td>
										</tr>
										<input type="hidden" name="lastincidents"  value='0'/>
										<input type="hidden" name="incidents"  value='0'/>
<%
	}
	}else if(sys.isCorporation(CorporationData.ALLERGAN)) {
		Collection fuelTypes = sys.getDataTypeReportFacade().findAll(DataTypeConstants.TYPEFUEL,sys.getUserSessionData());
%>											<input type="hidden" name="accidents"  value='0'/>
											<input type="hidden" name="incidents"  value='0'/>
										<tr><td>&nbsp;</td></tr>
										<tr class="lineone">
											<td colspan="2" >
												<jsp:include  page='<%="../component/utilitybox5top.jsp?title="+messages.getText("fleet.odometer.fuel")%>' />
												<table width="100%" border="0">
													<tr class="lineone">
														<th align="left" class="lineone"><%=messages.getText("fleet.odometer.fueltype")%></th>
														<td align="right" class="lineone">
															<select name='fueltype' class="input">
																<option value=""></option>
<%
	Iterator fuelIt = fuelTypes.iterator();
	    			while(fuelIt.hasNext()) {
	        			String name = ((TypeFuelDataType)fuelIt.next()).getResourceBundleId();
%>																<option value='<%=name%>' <%=(odometerValues[8] != null && odometerValues[8].equals(name)) ? "selected" : ""%>><%=messages.getText(name)%></option>
<%
	}
%>															</select>
														</td>
													</tr>
				      								<tr  class="space">
				        								<th align="right">&nbsp;</th>
				        								<td align="right">&nbsp;</td>
					      							</tr>
													<tr class="lineone">
														<th align="left" class="lineone"><%=messages.getText("datatype.volumeunit.liters")%></th>
														<td align="right" class="lineone">
															<input type="text" name="fuel" size="10" class="input" value='<%=(odometerValues[9] != null) ? odometerValues[9] : ""%>'/>
														</td>
													</tr>
												</table>
												<jsp:include  page="../component/utilitybox2bottom.jsp" />
											</td>
										</tr>
<%
	} else {
%>										<tr class="lineone">
											<th align="left" class="lineone"><%=messages.getText("fleet.odometer.accidents")%></th>
											<td align="right" class="lineone">
												<input type="hidden" name="lastaccidents"  value='<%=(odometerValues[1] != null && odometerValues[1].length() > 0) ? odometerValues[1] : "-1"%>'/>
												<input type="text" name="accidents" size="10" class="input" value='<%=odometerValues[1]%>'/>
											</td>
										</tr>
		      							<tr  class="space">
		        							<th align="right">&nbsp;</th>
		        							<td align="right">&nbsp;</td>
		      							</tr>
										<tr class="lineone">
											<th align="left" class="lineone"><%=messages.getText("fleet.odometer.incidents")%></th>
											<td align="right" class="lineone">
												<input type="hidden" name="lastincidents"  value='<%=(odometerValues[2] != null && odometerValues[2].length() > 0) ? odometerValues[2] : "-1"%>'/>
												<input type="text" name="incidents" size="10" class="input" value='<%=odometerValues[2]%>'/>
											</td>
										</tr>
<%
	}
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
					<input type="button" class="input" value='<%=messages.getText("generic.messages.save")%>' onclick="javascript:verifyOdometer(<%=(Util.empty(odometerValues[3]) || isMonsantoBrasil) ? "0" :  odometerValues[3]%>,'<%=messages.getText("odometer.error.lessthanlast")%>');"/>
					<input type="button" class="input" value='<%=messages.getText("generic.messages.cancel")%>' onclick="javascript:document.forms['cancel'].submit();"/>
				</td>
      		</tr>
	      	<tr>
				<jsp:include  page="../component/spaceline.jsp" />
      		</tr>
	    </table>
	</form>	
<%		}else {
%>	<jsp:include  page='<%="/util/allowedData.jsp?message=fleet.error.access.fleet" %>'/>
<%		}
	}	
%>	
</html>
