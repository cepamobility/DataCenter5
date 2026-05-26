<%@page import="communication.administration.corporation.tables.CorporationData"%>
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
	String check = Util.checkPermission(sys,SecurityConstants.MODIFY_DRIVER);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		session.setAttribute("url","event/SelectVehicleAddControlTelematic.jsp?id="+request.getParameter("id"));
	
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
		Boolean isDPA=sys.isCorporation(CorporationData.DPA);
		String next = (isDPA) ? "/driver/telematicControlDPA.jsp" : "/driver/telematicControl.jsp";
%>	
	<link REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>CEPA</title>
	</head>
	<body>
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  			<tr class="guide1">
  				<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("driver.telematiccontrol.selectVehicleTelematiControl")%>' />
			</tr>
  			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
			</tr>
			<tr>
				<td width="292" valign="top">
                  	<table width="100%">
                  		<tr>
                   			<td>
								<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.driversvehicles")%>' />
								<table width="100%">
<%		if(drivers == null || drivers.size() == 0) {
%>									<tr>
										<td>
											<span class="linetwo"><%=messages.getText("generic.event.driverwithoutvehicles")%></span>
										</td>
									</tr>
<%		}else {
%>									<tr>
										<td width='32%' class="guide2">
											<%=messages.getText("generic.vehicle.vehicledata.registrationNumber")%>
										</td>
    									<td width='32%'	class="guide2">
    										<%=messages.getText("generic.vehicle.vehicledata.year")%>
    									</td>
					    				<td width='32%'	class="guide2">
					    					<%=messages.getText("generic.vehicle.vehicledata.country")%>
					    				</td>
									</tr>
<%  		Iterator it = drivers.iterator();
			boolean lineone = false;
    		while(it.hasNext()) {
				VehicleMainAttributes vehicle = (VehicleMainAttributes)it.next();   		    	
%>									<tr align=center align="right" class='<%=(lineone) ? "lineone" : "linetwo"%>'>
	          							<td width='32%' align="right">
<%				if(sys.getUserSessionData().isDriver()) {
%>											<a href='layout.jsp?refer=/event/EventCreate.jsp?type=create&vehicle=<%=vehicle.getId()%>' > <%=vehicle.getMatricula()%> </a>
<%				}else {
%>	              							<a href='layout.jsp?refer=<%=next%>?driver=<%=driverId%>&vehicle=<%=vehicle.getId()%>'> <%=vehicle.getMatricula()%> </a>
<%				}
%>			  							</td>
						                <td width='32%' align="right"><%=vehicle.getYear()%> </td>
						                <td width='32%' align="right"><%=messages.getText(vehicle.getCountryName())%> </td>						                
									</tr>
<%    			lineone = !lineone;
			}
		}
%>								</table>
								<jsp:include  page="../component/utilityboxbottom.jsp" />
							</td>
						</tr>
					</table>
				</td>
                <td width="16">&nbsp;</td>
				<td width="292" valign="top">
                  	<table width="100%">
                  		<tr>
                   			<td>
								<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.options")%>' />
								<table>
<%				if(!sys.getUserSessionData().isDriver()) {
%>									<tr>
										<td>
											<img align="middle" border="0" src="/datacenter/util/imgs/icon1.jpg" width="12">
	              							<a href="layout.jsp?refer=/vehicle/VehicleSearch.jsp?link=layout.jsp?refer=<%=next%>?driver=<%=driverId%>&title=<%=messages.getText("driver.telematiccontrol.selectVehicleTelematiControl")%>">
	              							<span class="linetwo"><%=" "+messages.getText("generic.event.options.selectexistingvehicle")%></span></a>
										</td>
									</tr>
<%				}
%>
<%			  if(isDPA){%>
									<tr>
										<td>
											<img align="middle" border="0" src="/datacenter/util/imgs/icon1.jpg" width="12">
	              							<a href="layout.jsp?refer=<%=next%>?driver=<%=driverId%>">
	              							<span class="linetwo"><%=" "+messages.getText("generic.event.options.noselectvehicle")%></span></a>
										</td>
									</tr>
<%			}%>

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