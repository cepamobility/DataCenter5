<%@page language="java" 
	errorPage="../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.vehicle.tables.*"
	import="presentation.translator.web.util.*"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<html>
<%!
	RequestDispatcher dispatcher; String context;
	SystemAccess sys; Text messages;
	Integer driverId; Collection drivers;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = null;
	if(sys.getUserSessionData().isDriver()) {
	    check = Util.checkPermission(sys,SecurityConstants.DRIVER_CREATE_EVENT);
	}else {
	    check = Util.checkPermission(sys,SecurityConstants.CREATE_EVENT);
	}
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		session.setAttribute("url","event/SelectVehicle.jsp?id="+request.getParameter("id"));
	
		context=request.getContextPath();
	
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
	
		dispatcher=request.getRequestDispatcher("/util/jsp/language.jsp");
		dispatcher.include(request,response);

		messages=sys.getText();
		
		if(sys.getUserSessionData().isDriver()) {
		    driverId = sys.getUserSessionData().getDriverData().getId();
		}else {
			driverId = new Integer(request.getParameter("id"));
		}	
		drivers = sys.getVehicleReportFacade().findDriversVehicles(driverId,sys.getUserSessionData());
%>	
	<!-- SelectVehicle.jsp -->
	<link REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>CEPA</title>
	</head>
	<body>
	 	<table width="100%" height="90" border="0" cellpadding="0" cellspacing="0" class="default">
		 	<form name="logout" action="../login.jsp" method="post">
	 		</form>
			<form name="data" action="../driver/data.jsp" method="post">
			</form>		
			<form name="Vehicles" action="../vehicle/AssociatedVehicles.jsp"></form>
			<form name="ReportEvent" action="../event/reportEvent.jsp"></form>
  			<tr>
    			<td colspan="2">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" valign="top">
      					<tr>
        					<td><jsp:include  page='<%="../topMenu.jsp?home=true" %>'/></td>
      					</tr>
    				</table>	
    			</td><!--end the top�s table -->
  			</tr>
  		</table>
  		<br>
  		<br>	
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  			<tr class="guide1">
  				<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.event.selectvehicle")%>' />
			</tr>
  			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
			</tr>
			<tr>
				<td width="100%" valign="top">
                  	<table width="100%">
                  		<tr>
                   			<td>
								<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.driversvehicles")%>' />
								<table width="100%">
<%		if(drivers == null || drivers.size() == 0) {
%>									<tr>
										<td align="left">
											<span class="linetwo"><%=messages.getText("generic.event.driverwithoutvehicles")%></span>
										</td>
									</tr>
<%		}else {
%>									<tr>
										<td width='32%' class="guide2" align="center">
											<%=messages.getText("generic.vehicle.vehicledata.registrationNumber")%>
										</td>
    									<td width='32%'	class="guide2" align="center">
    										<%=messages.getText("generic.vehicle.vehicledata.year")%>
    									</td>
					    				<td width='32%'	class="guide2" align="center">
					    					<%=messages.getText("generic.vehicle.vehicledata.country")%>
					    				</td>
									</tr>
<%  		Iterator it = drivers.iterator();
			boolean lineone = false;
    		while(it.hasNext()) {
				VehicleMainAttributes vehicle = (VehicleMainAttributes)it.next();   		    	
%>									<tr align=center align="right" class='<%=(lineone) ? "lineone" : "linetwo"%>'>
	          							<td width='32%'  align="center">
											<a href='reportEvent.jsp?vehicle=<%=vehicle.getId()%>' > <%=vehicle.getMatricula()%> </a>
			  							</td>
						                <td width='32%'  align="center"><%=vehicle.getYear()%> </td>
						                <td width='32%'  align="center"><%=messages.getText(vehicle.getCountryName())%> </td>						                
									</tr>
<%    			lineone = !lineone;
			}
		}
%>									<tr height="10"><td></td></tr>	
									<tr>
										<td colspan="3" align="right">
											<input type="button" value='<%=messages.getText("generic.messages.othervehicle")%>' onclick="document.forms['ReportEvent'].submit()""/>
											<input type="button" value='<%=messages.getText("generic.messages.cancel")%>' onclick="document.forms['Vehicles'].submit()""/>
										</td>
									</tr>	
								</table>
								<jsp:include  page="../component/utilityboxbottom.jsp" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
<%	}
%>
</html>