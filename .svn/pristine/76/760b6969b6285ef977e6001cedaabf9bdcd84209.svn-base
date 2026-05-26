<%@page import="communication.driver.tables.DriverData"%>
<%@page import="communication.driver.tables.DriverMainAttributes"%>
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
		DriverData main = sys.getDriverReportFacade().findDriverByPrimaryKey(driverId, sys.getUserSessionData());
		drivers = sys.getVehicleReportFacade().findDriversVehicles(driverId,sys.getUserSessionData());
		String form=request.getParameter("form");
%>	
	<!-- SelectVehicle.jsp -->
	<link REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>CEPA</title>
	</head>
	<body>
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  			<tr class="guide1">
  				<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.event.selectvehicle")%>' />
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
						if(form==null || form.trim().length()==0){
%>							<a href='layout.jsp?refer=/event/EventCreate.jsp?type=create&vehicle=<%=vehicle.getId()%>' > <%=vehicle.getMatricula()%> </a>							
<%						}else if(form.equals("new")){
							if(sys.isCorporation(CorporationData.JJ)){
%>								<a href='layout.jsp?refer=/event/EventCreate3.jsp?type=create&vehicle=<%=vehicle.getId()%>' > <%=vehicle.getMatricula()%> </a>								
<%							}else{
%>								<a href='layout.jsp?refer=/event/NewEventCreate.jsp?type=create&vehicle=<%=vehicle.getId()%>' > <%=vehicle.getMatricula()%> </a>
<%							}
						}else if(form.equals("old")){
%>								<a href='layout.jsp?refer=/event/EventCreate2.jsp?type=create&vehicle=<%=vehicle.getId()%>' > <%=vehicle.getMatricula()%> </a>								
<%						}
%>
<%				}else {
						if(form==null || form.trim().length()==0){
%>							<a href='layout.jsp?refer=/event/EventCreate.jsp?type=create&id=<%=driverId%>&vehicle=<%=vehicle.getId()%>' > <%=vehicle.getMatricula()%> </a>							
<%						}else if(sys.isCorporation(CorporationData.MONSANTO) && main.isMercosur()) {
%>								<a href='layout.jsp?refer=/event/EventCreate2.jsp?type=create&id=<%=driverId%>&vehicle=<%=vehicle.getId()%>' > <%=vehicle.getMatricula()%> </a>								
<%						}else if(form.equals("new")){
							if(sys.isCorporation(CorporationData.JJ)){
%>								<a href='layout.jsp?refer=/event/EventCreate3.jsp?type=create&id=<%=driverId%>&vehicle=<%=vehicle.getId()%>' > <%=vehicle.getMatricula()%> </a>								
<%							}else{
%>								<a href='layout.jsp?refer=/event/NewEventCreate.jsp?type=create&id=<%=driverId%>&vehicle=<%=vehicle.getId()%>' > <%=vehicle.getMatricula()%> </a>
<%							}
						}else if(form.equals("old")){
%>								<a href='layout.jsp?refer=/event/EventCreate2.jsp?type=create&id=<%=driverId%>&vehicle=<%=vehicle.getId()%>' > <%=vehicle.getMatricula()%> </a>								
<%						}
%>	
	              		
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
<%											if((form==null || form.length()==0) || sys.isCorporation(CorporationData.BAYER_MX)){ 											
%>	              								<a href="layout.jsp?refer=/vehicle/VehicleSearch.jsp?link=layout.jsp?refer=/event/EventCreate.jsp?type=create&id=<%=driverId%>&title=generic.event.selectvehicle"><span class="linetwo"><%=" "+messages.getText("generic.event.options.selectexistingvehicle")%></span></a>
<%											}else if(sys.isCorporation(CorporationData.MONSANTO) && main.isMercosur()) {
%>	              									<a href="layout.jsp?refer=/vehicle/VehicleSearch.jsp?link=layout.jsp?refer=/event/EventCreate2.jsp?type=create&id=<%=driverId%>&title=generic.event.selectvehicle"><span class="linetwo"><%=" "+messages.getText("generic.event.options.selectexistingvehicle")%></span></a>
<%											}else if(form.equals("new")){
												if(sys.isCorporation(CorporationData.JJ)){
%>		              								<a href="layout.jsp?refer=/vehicle/VehicleSearch.jsp?link=layout.jsp?refer=/event/EventCreate3.jsp?type=create&id=<%=driverId%>&title=generic.event.selectvehicle"><span class="linetwo"><%=" "+messages.getText("generic.event.options.selectexistingvehicle")%></span></a>
<%												}else{
%>	              									<a href="layout.jsp?refer=/vehicle/VehicleSearch.jsp?link=layout.jsp?refer=/event/NewEventCreate.jsp?type=create&id=<%=driverId%>&title=generic.event.selectvehicle"><span class="linetwo"><%=" "+messages.getText("generic.event.options.selectexistingvehicle")%></span></a>
<%												}
											}else if(form.equals("old")){
%>	              									<a href="layout.jsp?refer=/vehicle/VehicleSearch.jsp?link=layout.jsp?refer=/event/EventCreate2.jsp?type=create&id=<%=driverId%>&title=generic.event.selectvehicle"><span class="linetwo"><%=" "+messages.getText("generic.event.options.selectexistingvehicle")%></span></a>
<%											}
%>
										</td>
									</tr>
<%				}
%>									<tr>
										<td>
											<img align="middle" border="0" src="/datacenter/util/imgs/icon1.jpg" width="12">
<%				if(sys.getUserSessionData().isDriver()) {
											if((form==null || form.length()==0)){ 											
%>	              								<a href="layout.jsp?refer=/event/EventCreate.jsp?type=create"><span class="linetwo"><%=" "+messages.getText("generic.event.options.insertvehicledata")%></span></a>
<%											}else if(sys.isCorporation(CorporationData.MONSANTO) && main.isMercosur()) {
%>												<a href='layout.jsp?refer=/event/EventCreate2.jsp?type=create' ><span class="linetwo"><%=" "+messages.getText("generic.event.options.insertvehicledata")%></span></a>								
<%											}else if(form.equals("new")){
												if(sys.isCorporation(CorporationData.JJ)){
%>		              								<a href="layout.jsp?refer=/event/EventCreate3.jsp?type=create"><span class="linetwo"><%=" "+messages.getText("generic.event.options.insertvehicledata")%></span></a>
<%												}else{
%>													<a href="layout.jsp?refer=/event/NewEventCreate.jsp?type=create"><span class="linetwo"><%=" "+messages.getText("generic.event.options.insertvehicledata")%></span></a>
<%												}
											}else if(form.equals("old")  || sys.isCorporation(new int[]{CorporationData.BAYER_MX,CorporationData.WEYERHAEUSER})){
%>												<a href="layout.jsp?refer=/event/EventCreate2.jsp?type=create"><span class="linetwo"><%=" "+messages.getText("generic.event.options.insertvehicledata")%></span></a>
<%											}
				}else {
					session.removeAttribute("EventForm");
					String href2 = "layout.jsp?refer=/event/EventCreate.jsp?type=create&id="+driverId+"&vehicle=-1";
					if((form==null || form.length()==0)){ 											
		              	href2 = "layout.jsp?refer=/event/EventCreate.jsp?type=create&id="+driverId+"&vehicle=-1";
					}else if(sys.isCorporation(CorporationData.MONSANTO) && main.isMercosur()) {
		              	href2 = "layout.jsp?refer=/event/EventCreate2.jsp?type=create&id="+driverId+"&vehicle=-1";
					}else if(form.equals("new")){
						if(sys.isCorporation(CorporationData.JJ)){
			              	href2 = "layout.jsp?refer=/event/EventCreate3.jsp?type=create&id="+driverId+"&vehicle=-1";
						}else{
			              	href2 = "layout.jsp?refer=/event/NewEventCreate.jsp?type=create&id="+driverId+"&vehicle=-1";
						}
					}else if(form.equals("old") || sys.isCorporation(new int[]{CorporationData.BAYER_MX,CorporationData.WEYERHAEUSER})){
		              	href2 = "layout.jsp?refer=/event/EventCreate2.jsp?type=create&id="+driverId+"&vehicle=-1";
					}

%>	              							<a href='<%=href2 %>'><span class="linetwo"><%=" "+messages.getText("generic.event.options.insertvehicledata")%></span></a>
<%				}
%>										</td>
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