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

<html:html>
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
	Text messages; SystemAccess sys;
	String period;String year;String vehicle;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.DRIVER_INSERT_ODOMETER);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		
		
		messages = sys.getText();
		String vehicle = request.getParameter("vehicle");
		String period = request.getParameter("period");
		String year = request.getParameter("year");
		String odometer = request.getParameter("odometer");
		String fuelType = null;
		String fuel = null;
		if(!Util.empty(request.getParameter("fueltype"))) {
	fuelType = request.getParameter("fueltype");
		}
		if(Util.isNumber(request.getParameter("fuel"))) {
	fuel = request.getParameter("fuel");
		}
		boolean valid = true;
        if(Util.empty(odometer) || !Util.isNumber(odometer)) {
            session.setAttribute("Msg",messages.getText("fleet.error.invalid.odometer"));
            response.sendRedirect("OwnOdometerValue.jsp?vehicle="+vehicle+"&period="+period+"&year="+year);
            valid = false;
        }
        if(sys.isCorporation(CorporationData.ALLERGAN) && valid) {
        	if( fuel == null) {
	            session.setAttribute("Msg",messages.getText("fleet.error.invalid.fuelvalue"));
	            response.sendRedirect("OwnOdometerValue.jsp?vehicle="+vehicle+"&period="+period+"&year="+year);
	            valid = false;
        	} else if (Integer.parseInt(fuel) >= 1000 || Integer.parseInt(fuel) < 0) {
	            session.setAttribute("Msg",messages.getText("fleet.error.invalid.fuelrange"));
	            response.sendRedirect("OwnOdometerValue.jsp?vehicle="+vehicle+"&period="+period+"&year="+year);
	            valid = false;
        	}
        }
        if(sys.isCorporation(CorporationData.ALLERGAN) && valid && fuel == null) {
            session.setAttribute("Msg",messages.getText("fleet.error.invalid.fleetvalue"));
            response.sendRedirect("OwnOdometerValue.jsp?vehicle="+vehicle+"&period="+period+"&year="+year);
            valid = false;
        }
		String accidents = request.getParameter("accidents");
        if(valid && (Util.empty(accidents) || !Util.isNumber(accidents))) {
            session.setAttribute("Msg",messages.getText("fleet.error.invalid.accidents"));
            response.sendRedirect("OwnOdometerValue.jsp?vehicle="+vehicle+"&period="+period+"&year="+year);
            valid = false;
        }
		String incidents = request.getParameter("incidents");
        if(valid && (Util.empty(incidents) || !Util.isNumber(incidents))) {
            session.setAttribute("Msg",messages.getText("fleet.error.invalid.incidents"));
            response.sendRedirect("OwnOdometerValue.jsp?vehicle="+vehicle+"&period="+period+"&year="+year);
            valid = false;
        }
        
        if(valid) {
	session.setAttribute("url","fleet/odometerConfirmation.jsp?vehicle="+vehicle+"&period="+period+"&odometer="+odometer+"&accidents="+accidents+"&incidents="+incidents+"&year="+year);
		
		    VehicleReportFacade vehicleReport = sys.getVehicleReportFacade();
		    FleetReportFacade fleetReport = sys.getFleetReportFacade();
	
		    String distance = fleetReport.getVehicleOdometerType(new Integer(vehicle), sys.getUserSessionData());
	
	Integer[][] odometerValues = fleetReport.getLastTwoOdometerValues(new Integer(vehicle),new Integer(period),new Integer(year),sys.getUserSessionData());
	int difference = Integer.parseInt(odometer);
	int lastDifference = -1;
	double percent = 0.0;
	Calendar current = Calendar.getInstance();
	current.set(Calendar.MONTH,Integer.parseInt(period));
	current.set(Calendar.YEAR,Integer.parseInt(year));
	if(odometerValues.length > 0 && odometerValues[0][0] != null) {
		Calendar last = Calendar.getInstance();
		last.set(Calendar.MONTH,odometerValues[0][1].intValue()-1);
		last.set(Calendar.YEAR,odometerValues[0][2].intValue());
		last.add(Calendar.MONTH,1);
		difference = Integer.parseInt(odometer) - odometerValues[0][0].intValue(); 
		if(odometerValues.length > 1 && odometerValues[1][0] != null) {
	Calendar prelast = Calendar.getInstance();
	prelast.set(Calendar.MONTH,odometerValues[1][1].intValue()-1);
	prelast.set(Calendar.YEAR,odometerValues[1][2].intValue());
	prelast.add(Calendar.MONTH,2);
	if(prelast.get(Calendar.MONTH) == last.get(Calendar.MONTH) && prelast.get(Calendar.YEAR) == last.get(Calendar.YEAR)
			&& current.get(Calendar.MONTH) == last.get(Calendar.MONTH) && current.get(Calendar.YEAR) == last.get(Calendar.YEAR)) {
		lastDifference = odometerValues[0][0].intValue() - odometerValues[1][0].intValue();
		percent = new Double(Math.abs(lastDifference - difference)).doubleValue() * 100.0 / new Double(lastDifference).doubleValue();
	}
		}
	}
%>	
	<body>
	<form name="logout" action="./login.jsp" method="post">
	 		</form>
			<form name="home" action="./layout.jsp" method="post">
			</form>	
	 	<table width="100%" height="90" border="0" cellpadding="0" cellspacing="0" class="default">
		 		
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
		<form name="back" method="post" action="OwnOdometerValue.jsp">
			<input name="vehicle" type="hidden" value='<%=vehicle%>' />
			<input name="period" type="hidden" value='<%=String.valueOf(period)%>' />
			<input name="year" type="hidden" value='<%=year%>' />
		</form>
	<html:form action="/fleet/FleetOwnodometer">
		<html:hidden property="vehicle" value='<%=vehicle%>' />
		<html:hidden property="period" value='<%=String.valueOf(period)%>' />
		<html:hidden property="year" value='<%=year%>' />
		<html:hidden property="odometer" value='<%=odometer%>' />
		<html:hidden property="accidents" value='<%=accidents%>' />
		<html:hidden property="incidents" value='<%=incidents%>' />
		<html:hidden property="fuel" value='<%=fuel%>' />
		<html:hidden property="fuelType" value='<%=fuelType%>' />
		<table width="450" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1">
				<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.fleet.odometers")+" "+messages.getText("generic.month."+String.valueOf(period))+" - "+year%>' />
	  		</tr>
	  		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
			</tr>
			<tr>
				<td colspan="3" width="450">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("fleet.odometer.insertedvalues")%>' />
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th class="blue" align="left" colspan="2"><%=messages.getText("generic.fleet.pressconfirm")%></th> 
						</tr>
						<tr><td>&nbsp;</td></tr>
<%
	if(sys.isCorporation(CorporationData.ALLERGAN)) {
%>						<tr>
							<th align="left" class="redBig" colspan="2"> <%=messages.getText("fleet.odometer.value")+" "+odometer+"."%></th> 
						</tr>
<%
	}else {
%>						<tr>
							<th align="left" class="redBig" colspan="2"> <%=messages.getText("generic.fleet.roaded")+" "+String.valueOf(difference)+ " " + messages.getText(distance)+"."%></th> 
						</tr>
<%
	if(lastDifference > -1) {
%>						<tr>
							<th align="left" class="redBig" colspan="2"> <%=messages.getText("generic.fleet.thisrepresents") + " " + String.valueOf(Math.round(percent))%> % <%=(difference > lastDifference) ? messages.getText("generic.messages.more") : messages.getText("generic.messages.less")%><%=messages.getText("generic.fleet.thanlastperiod")+"."%></th> 
						</tr>
<%
	}
		}
		if(sys.isCorporation(CorporationData.ALLERGAN)) {
%>						<tr><td>&nbsp;</td></tr>
						<tr>
							<td colspan="2">
								<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("fleet.odometer.fuel")%>' />
								<table width="100%" cellpadding="0" cellspacing="0" border="0">
									<tr class="lineone">
										<th align="left"><%=(Util.empty(fuelType)) ? messages.getText("fleet.odometer.fueltype")+": ---" : messages.getText("fleet.odometer.fueltype")+": " + messages.getText(fuelType)%></th> 
									</tr>
									<tr class="lineone"><td>&nbsp;</td></tr>
									<tr class="lineone">
										<th align="left"><%=(!Util.isNumber(fuel)) ? messages.getText("datatype.volumeunit.liters")+": ---" : messages.getText("datatype.volumeunit.liters")+": " + fuel%></th>
									</tr>
								</table>
								<jsp:include  page='../component/utilitybox2bottom.jsp' />
							</td>
						</tr>
<%		} else if (sys.isCorporation(CorporationData.MONSANTO)) {
			if(!sys.getUserSessionData().getDriverData().getCountryName().equals(CountryData.BRASIL)) {
%>						<tr><td>&nbsp;</td></tr>						
						<tr>
							<th class="linetwo" align="left"><%=messages.getText("generic.fleet.eventcount")+": "+accidents%></th> 
						</tr>
<%			}
		} else {
%>						<tr><td>&nbsp;</td></tr>						
						<tr>
							<th class="linetwo" align="left"><%=messages.getText("generic.fleet.accidentcount")+": "+accidents%></th> 
						</tr>
						<tr><td>&nbsp;</td></tr>						
						<tr>
							<th class="linetwo" align="left"><%=messages.getText("generic.fleet.incidentcount")+": "+incidents%></th> 
						</tr>
<%		}
%>						<tr><td>&nbsp;</td></tr>
						<tr>
							<th class="linetwo" align="center" colspan="2">
								<html:submit styleClass="input" value='<%=messages.getText("generic.messages.confirm")%>' />
								<input type="button" class="input" value='<%=messages.getText("generic.messages.cancel")%>' onclick="document.forms['back'].submit();"/>
							</th> 
						</tr>
					</table>
					<jsp:include page="../component/utilityboxbottom.jsp"/>	
				</td>
			</tr>
	      	<tr>
				<jsp:include  page="../component/spaceline.jsp" />
      		</tr>
	    </table>
	</html:form>	
<%		}
	}
%>	
</html:html>



