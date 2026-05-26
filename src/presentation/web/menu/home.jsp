 <%@page import="communication.vehicle.tables.VehicleCheckData"%>
<%@page import="communication.security.SecurityConstants"%>
<%@page language="java"
	errorPage="../exception.jsp"
	import="business.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.administration.user.tables.*"
	import="presentation.translator.web.user.*"
	import="communication.language.tables.*"
	import="presentation.translator.web.util.Util"
	import="communication.administration.corporation.tables.*"
	import="java.util.*"
 	import="communication.administration.user.*" 		
 	import="communication.datatype.*" 	
 	import="communication.util.*"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	Text messages = null; 
	SystemAccess sys = null;
	String area = null;

		
	if(session.getAttribute("SystemAccess") == null) {
		sys = new SystemAccess(request.getUserPrincipal().getName()); 
		if(sys==null){
			response.sendRedirect("../datacenter/home.jsp?session=1");
		}
		session.setAttribute("SystemAccess", sys);
	}else {
		sys = (SystemAccess)session.getAttribute("SystemAccess");
	}
	
	/*Calendar c1 = Calendar.getInstance();
	c1.set(Calendar.MONTH, 3);
	Calendar c2 = Calendar.getInstance();
	sys.getBIReportFacade().findContractorsPointData(c1.getTime(), c2.getTime(), 0, sys.getUserSessionData());*/
	if(session.getAttribute("Breadcrumb") == null) {
	    Breadcrumb breadcrumb = new Breadcrumb();
		session.setAttribute("Breadcrumb", breadcrumb); 
	}
	
 	String check = presentation.translator.web.util.Util.checkPermission(sys,communication.security.SecurityConstants.ACCESS_TO_SYSTEM);
 	UserSessionData userSession=sys.getUserSessionData();
 	
 	boolean redirect = false;
 	if(request.getUserPrincipal().getName().equals("resetpassword")) {
 		redirect = true;
 		
 		// Paso el idioma para mostrar el resetPassword en el idioma elegido. 
 		if (request.getAttribute("language") != null){
 			String language = (String)request.getAttribute("language");
 			response.sendRedirect("./resetPassword.jsp?language="+language);
 		} 		
	    response.sendRedirect("./resetPassword.jsp");	    
	    
	    
	} else if(sys.isCorporation(CorporationData.ECOLAB) && sys.getUserSessionData().isGerente()) {
	 	redirect = true;
		response.sendRedirect("./ecolabParameters.jsp");
 	}else if(request.getUserPrincipal().getName().equals(new MD5().encriptar("recomendation"))) {
	 	redirect = true;
		response.sendRedirect("./event/recomendation.jsp?event="+request.getParameter("recomendation")+"&check="+request.getParameter("check"));
 	}else if(request.getUserPrincipal().getName().equals(new MD5().encriptar("jjodometer"))) {
	 	redirect = true;
		response.sendRedirect("./fleet/saveOdometer.jsp?vehicle="+request.getParameter("vehicle")+"&odometer="+request.getParameter("odometer")+"&month="+request.getParameter("month")+"&year="+request.getParameter("year"));
	}else if(userSession.getRole().getName().equals("firstAccess")) {
 		redirect = true;
	    response.sendRedirect("./firstaccess.jsp");
	}else if(userSession.getRole().getName().equals("accesscontrol")) {
 		redirect = true;
	    response.sendRedirect("./accesscontrol.jsp");
	    
	// Fecha: 14/06/2012
	// Actualizacion de datos de conductores de bayer
	}else if (userSession.getRole().getName().equals("driverbayer")){
	   	System.out.println("Soy usuario driverBayer...");
		redirect = true;
	    String idDriver = request.getParameter("id");
	    if (!Util.empty(idDriver) && Util.isNumber(idDriver)){
	    	response.sendRedirect("./driver/web_bayer.jsp?id="+idDriver);
	    }else{
	    	response.sendRedirect("/login.jsp");
	    }
	   
	    
	// Fin 14/06/2012
	
	}else if(userSession.getRole().getName().equals("fleetdriver")) {
 		redirect = true;
	    response.sendRedirect("./vehicle/AssociatedVehicles.jsp");
	}else if(userSession.isMobileCheckList()) {
 		redirect = true;
    	response.sendRedirect("./mobile/vehicleSearch.jsp");
	}else if(userSession.isDPACheckList()) {
 		redirect = true;
 		if(sys.isCorporation(CorporationData.SYNGENTA) || sys.isCorporation(CorporationData.FORESTALORIENTAL) || sys.isCorporation(CorporationData.ACU)){
	    	response.sendRedirect("./vehicle/vehicleSearchFOSA.jsp");
 		}else if(sys.isCorporation(CorporationData.MONTES_DE_PLATA) || sys.isCorporation(CorporationData.WEYERHAEUSER)|| sys.isCorporation(CorporationData.COFUSA)|| sys.isCorporation(CorporationData.CEPA)){
 			response.sendRedirect("./vehicle/vehicleSearchFOSA.jsp");
 		}else{
 			response.sendRedirect("./vehicle/vehicleSearchDPA.jsp");
 		}
	}else if(userSession.getRole().getName().equals("jjreporter")) {
 		redirect = true;
 		if(userSession.getLenguage().equals("es")) {
 			if(sys.isCorporation(CorporationData.BAYER_MX)) {
 				response.sendRedirect("./reporterjj/driver.jsp?formType=3");
 			}else {
	 			if(userSession.getFilter().getLoginName().equals(SecurityConstants.LOGIN_REPORTERJJ_MX)) {
		 			if(userSession.getVehicleCheck() == null) {
		 				response.sendRedirect("./reporterjj/vehicle.jsp");	
		 			}else {
		 				response.sendRedirect("./reporterjj/driver.jsp?formType=1");
		 			}
	 			}else if(userSession.getFilter().getLoginName().equals(SecurityConstants.LOGIN_REPORTERJJ_ARG)) {
	 				response.sendRedirect("./reporterjj/driver.jsp?formType=1");
	 			}else {
		 			response.sendRedirect("./reporterjj/form_es.jsp");
	 			}
 			}
 		}else {
	    	response.sendRedirect("./reporterjj/forms.jsp");
 		}
//	}else if(sys.getCorporationId() == CorporationData.LILLY && userSession.getRole().isDriver()) {
//		DriverData driver = userSession.getDriverData();
//		VehicleReportFacade vehicleReportFacade = sys.getVehicleReportFacade();
//		ListResult result = vehicleReportFacade.findVehicleList("matricula","",0,-1,Integer.parseInt(String.valueOf(VehicleData.ACTIVE)),userSession);
//		switch(result.getTotal()) {
//			case 0:
//				response.sendRedirect("layout.jsp?refer=/event/EventCreate.jsp?type=create&id="+String.valueOf(driver.getId())+"&vehicle=-1");
//				break;
//			case 1:
//				response.sendRedirect("layout.jsp?refer=/event/EventCreate.jsp?type=create&id="+String.valueOf(driver.getId())+"&vehicle="+String.valueOf(((VehicleData)result.getElements().iterator().next()).getId()));
//				break;
//			case 2:
//				response.sendRedirect("layout.jsp?refer=/event/EventList.jsp");
//				break;
//		}
	}else if(userSession.isRedirectOdometer()) {
		VehicleReportFacade vehicleReportFacade = sys.getVehicleReportFacade();
		ListResult result = vehicleReportFacade.findVehicleList("matricula","",0,-1,Integer.parseInt(String.valueOf(VehicleData.ACTIVE)),userSession);
		switch(result.getTotal()) {
			case 0:
				redirect = false;
				break;
			case 1:
	    		Iterator it = result.getElements().iterator();
	    		VehicleData vehicle = (VehicleData)it.next();
	        	int periodType = sys.getFleetReportFacade().findFleetPeriodType(vehicle.getId(),sys.getUserSessionData());
	        	if(periodType != -1) {
		    		Calendar instance = Calendar.getInstance();
		    		Calendar lastInstance = sys.getFleetReportFacade().findLastOdometer(vehicle.getId(),sys.getUserSessionData());
		    		String action = null;
		    		if(instance.get(Calendar.DATE) >= 28) {
		    			if(instance.get(Calendar.DATE) <= 31) {
		    				instance.add(Calendar.MONTH,1);
		    			}
		    		}
		    		int month = instance.get(Calendar.MONTH);
		    		int year = instance.get(Calendar.YEAR);
		    		if(lastInstance == null) {
	    		        if (month == 0){
    		        		year = year -1;
    		        	}
	    		        action = "layout.jsp?refer=fleet/OwnOdometers.jsp";
		    		}else { 
		    	    	int previousMonth;
	    	    		if (month == 0) 	
	    	    			previousMonth = 11;
	    	    		else 
	    	    			previousMonth = month - 1;
    		    		if (previousMonth == lastInstance.get(Calendar.MONTH)){    		    			
    		    			year = lastInstance.get(Calendar.YEAR);
	    		    		action = "layout.jsp?refer=fleet/InsertedOdometers.jsp" ; 
    		    		}else{    		    			
    		    			action = "layout.jsp?refer=fleet/OwnOdometers.jsp";	
    		    		}
    		    		
	    		    	if(lastInstance.get(Calendar.MONTH) < 11){
		    		    	year = lastInstance.get(Calendar.YEAR);
		    		    }
		    		}
		    		if(month == 0) {
		    		  	month = 12;
		    		}
		    		response.sendRedirect(action+"&vehicle="+String.valueOf(vehicle.getId())+"&period="+String.valueOf(month)+"&year="+String.valueOf(year));
	        	}else {
	        		redirect = false;
	        	}
				break;
			default:
				response.sendRedirect("layout.jsp?refer=/vehicle/VehicleOdometerList.jsp");
				break;
		}
	}else if(check != null) {
 		redirect = true;
		response.sendRedirect(request.getContextPath()+check);
	}else if(sys.isCorporation(CorporationData.DTCP) && !sys.getUserSessionData().isAdministrator()) {
 		redirect = true;
		response.sendRedirect("survey.jsp");
	}
	
 	Integer defaultBookmark = null;
 	
	if(!redirect) { 		
		if(request.getParameter("refer") != null) {
			area = request.getParameter("refer");
		}else if(sys.getUserSessionData().isInstructorActivity() && sys.getActivityReportFacade().isInstructorActivities(sys.getUserSessionData())){
			area="./activity/schedule/log.jsp?user="+request.getUserPrincipal().getName();
		} else if(sys.isCorporation(CorporationData.BAYER_MX) && sys.getUserSessionData().isGerente()) {
			area = "./driver/driversbymx.jsp";
		} else if(sys.isCorporation(CorporationData.BAYER_MX) && sys.getUserSessionData().isDriver()) {
			area = "./driver/DriverView.jsp";
		}else {
			
		    switch(sys.getCorporationId()) {
		        case CorporationData.FORESTALORIENTAL: case CorporationData.LOSPIQUES: case CorporationData.MONTES_DE_PLATA:
					area="./driver/report.jsp";
					break;
		        case CorporationData.CENAPRA: case CorporationData.IMESEVI_UY:
					area="./cenapra/start.jsp";
					break;
		        case CorporationData.MONSANTO:
		        	if (userSession.getLenguage().equals("es"))
						area="./es_home.htm";
					else if(userSession.getLenguage().equals("en"))
						area="./en_home.htm";
					else	
						area="./monsanto.htm";
					break;
				default:
				    defaultBookmark = sys.getUserReportFacade().getDefaultBookmark(sys.getUserSessionData());
				    if(defaultBookmark == null) {
						if (userSession.getLenguage().equals("es"))
							area="./es_home.htm";
						else if(userSession.getLenguage().equals("en"))
							area="./en_home.htm";
						else	
							area="./pt_home.htm";
				    }else {
				        area = "./bookmark.jsp?id="+String.valueOf(defaultBookmark);
				    }
				    break;
		    }
		}
		if(userSession.getRole().getName().equals("Gerente") && sys.isCorporation(CorporationData.HALLIBURTON)) {
		    area = "./driver/DriverList.jsp";
		}
		long date = System.currentTimeMillis();
		try {
			String password = request.getParameter("password"); 
			if(!Util.empty(password)) {
		        if(password.length() > 13) {
		            date = Long.parseLong(password.substring(password.length()-13));
		        }
			}
		}catch(NumberFormatException e) {
		}
		sys.getUserABMFacade().saveLogin(date,sys.getUserSessionData());
		
		session.setAttribute("url","./home.jsp");
		if(sys==null){
			response.sendRedirect("/login.jsp");
		}
		
		messages=sys.getText();
%>

<%@page import="communication.vehicle.tables.VehicleData"%>
<%@page import="business.vehicle.VehicleReportFacade"%>
<%@page import="communication.driver.tables.DriverData"%><html>
	<head>
		<LINK REL=StyleSheet HREF="util/css/estilo.css" TYPE="text/css">
		<link rel="STYLESHEET" type="text/css" href="./css/menu.css"> 
		<script language="JavaScript" src="./menu/js/refactor.js" type="text/javascript">
 		</script>
 		<script type="text/javascript" src='./menu/js/showHide.js' ></script>
	 	<title> CEPA International</title>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">

		<script>
		function refactors(){
			refactor.refactorMenu(200);
			refactor.refactorResto(300);
		}
		</script>
<!-- The drop down  my info list -->
		<script type="text/javascript" src="./menu/js/dropdown.js"></script>
		<script type="text/javascript" src="./menu/js/functionPreferences.js"></script>
		<script type="text/javascript">
		if (window.addEventListener)
			window.addEventListener("load", do_onload, false)
		else if (window.attachEvent)
			window.attachEvent("onload", do_onload)
		else if (document.getElementById)
			window.onload=do_onload
		if (enablepersist=="on" && document.getElementById)
			window.onunload=saveswitchstate
		</script>
	</head>
	<body onLoad="setTimeout('refactor.loading()',4000);return true" >
		<form name="logout" action="./login.jsp" method="post">
	 	</form>
		<form name="home" action="./layout.jsp" method="post">
		</form>	
	 	<table width="100%" height="90px" border="0" cellpadding="0" cellspacing="0" class="default">
		 		
  			<tr>
    			<td colspan="2" valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" valign="top" >
      					<tr>
<%			if(sys.isCorporation(CorporationData.BAYER_MX)) {
%>        					<td><jsp:include  page='<%="topoBayerMX.jsp?home=true" %>'/></td>
<%			}else {
%>        					<td><jsp:include  page='<%="topo.jsp?home=true" %>'/></td>
<%			}
%>     					</tr>
    				</table>	
    			</td><!--end the topo table -->
  			</tr>
  			<tr>
<% 			if(!(userSession.getRole().getName().equals("Gerente") && sys.isCorporation(CorporationData.HALLIBURTON))) {
%> 				<td width="150" rowspan="3" valign="top"><!--Start the table that will have the include of Menu inside -->
					<table width="100%" border="0" cellspacing="0" cellpadding="0" valign="top" >
  						<tr>
    						<td id='td_menu' valign="top">
								<h3 onClick="expandcontent(this, 'sc1')" style="cursor:pointer">
									<div id="sc1" class="switchcontent">
    									<iframe width="150" height="700" frameborder="0" marginheight="0" scrolling="no" src="./menu.jsp"></iframe>
									</div>
								</h3>
							</td>
  						</tr>
					</table>
				</td><!--end the menus table -->
<% 			}
%>    			<td width="100%" height="10" valign="top">
    				<span class="style1"> </span>
      				<!--Start the table that will have the content of the page inside -->
      				<!--end contents table -->
      			</td>
  			</tr>
<%		
		
		if(sys.getCorporationId() == CorporationData.IMESEVI_UY) {
%>			<tr>
    			<td valign="top" align="left" height="30" class="linetwo">
    				<table cellspacing="5" border="0">
    					<tr>
    						<td><img src="./imgs/rotary.jpg" height="60"></td>
    						<td><img src="./imgs/acu_unitran.jpg" height="60"></td>
    						<td><img src="./imgs/ops.jpg" height="60"></td>
    						<td><img src="./imgs/unasev.jpg" height="60"></td>
    						<td><img src="./imgs/presidencia.jpg" height="60"></td>
    						<td><img src="./imgs/decada.jpg" height="60"></td>
						</tr>
					</table>
				</td>
    		</tr>
<%		}else { 
%>			<tr>
    			<td valign="top" align="left" height="20" class="linetwo">
    				<table cellspacing="5" border="0">
    					<tr>
<%			if(session.getAttribute("Breadcrumb") != null) {
%>    				<%=((communication.util.Breadcrumb)session.getAttribute("Breadcrumb")).getList(messages)%>
<%			}
%>    						
						</tr>
					</table>
				</td>
    		</tr>
<%		}
%>  			<tr>
    			<td valign="top">
    				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
      					
<%					if(defaultBookmark == null && userSession.getLenguage().equals("es") &&
							sys.getCorporationId() == CorporationData.PEPSICO){
							// Mensaje de duración de session para pepsico
%>
						<tr>
        					<td style="color:red;text-align:center" class="linetwo">
        						<%=messages.getText("generic.messages.sessionexpiredtime") %>
        					</td>
        				</tr>
<%					}
%>
					   <tr>
        					<td>
<%
						// Area, dibujos del home o bookmarks
						if(!Util.empty(area)) {
%>        						<jsp:include page='<%= area %>'/>
<%					}
	%>        			</td>
      					</tr>
    				</table>
    			</td>
  			</tr>
		</table>
<jsp:include  page='<%="footer.jsp"%>'/>		
<%@ include  file = '../util/jsp/googleanalytics.jsp' %>		
</body>
<%	}
%>
</html>
