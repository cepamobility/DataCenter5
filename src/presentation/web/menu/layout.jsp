<%@ page language="java"
	errorPage="../exception.jsp"
	import="business.SystemAccess"
	import="communication.language.Text"
	import="presentation.translator.web.util.Util"
	import="java.util.Collection"
	import="java.util.Iterator"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.administration.user.tables.*"
	import="presentation.translator.web.user.*"
	import="communication.language.tables.*"
	import="communication.administration.user.*"
	import="communication.administration.corporation.tables.*"
	import="java.util.Calendar"
	
		
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%!
	String reference;	
%>
<%

	if(session == null){
		response.sendRedirect("../datacenter/home.jsp?session=1");
	}

	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	if(sys==null){
		response.sendRedirect("home.jsp?session=1");
	}else {	
	
		Text messages = sys.getText();
		UserSessionData userSession = null;
		boolean redirect = false;
		try{
			userSession=sys.getUserSessionData();
		}catch(NullPointerException e){		
			redirect = true;
			response.sendRedirect("../datacenter/home.jsp?session=1");
			System.out.println(Util.formatDate(Calendar.getInstance().getTime())+				
					": layout.jsp -> Session Time Out ");		
			// throw e;
		}
			
		if(userSession.isDPACheckList()) {
			redirect = true;
			if(sys.isCorporation(CorporationData.FORESTALORIENTAL)){
	    		response.sendRedirect("./vehicle/vehicleSearchFOSA.jsp");
			}else{
				response.sendRedirect("./vehicle/vehicleSearchDPA.jsp");
			}
		}

		if(sys.isCorporation(CorporationData.DTCP) && !sys.getUserSessionData().isAdministrator()) {
 			redirect = true;
			response.sendRedirect("survey.jsp");
		}

		if(!redirect) {
			reference = request.getParameter("refer");
			if (reference == null && userSession.getUrl() != null){
				// Refactoring, refer url en userSessionData.
				reference = userSession.getUrl();
				// Limpio la URL
				userSession.setUrl(null);
			}
			
			
			if(reference == null) {
			    reference = (String)session.getAttribute("url");  
			}
			if((reference!=null)&&reference.equals("./home.jsp")) {
			    if(sys.getCorporationId() == CorporationData.CENAPRA || sys.getCorporationId() == CorporationData.IMESEVI_UY) {
					reference="./cenapra/start.jsp";
				}else if(sys.getUserSessionData().isInstructorActivity() && sys.getActivityReportFacade().isInstructorActivities(sys.getUserSessionData())){
					reference="./activity/schedule/log.jsp?user="+request.getUserPrincipal().getName();
			    }else {
					if (userSession.getLenguage().equals("es"))
						reference="./es_home.htm";
					else if(userSession.getLenguage().equals("en"))
						reference="./en_home.htm";
					else	
						reference="./pt_home.htm";
			    }
			}
			String check = Util.checkPermission(sys,communication.security.SecurityConstants.ACCESS_TO_SYSTEM);
			if(check!=null) {
				response.sendRedirect(request.getContextPath()+check);
			}else { 		
%>
<html>
<head>
<title>CEPA - Data Center</title>
<LINK REL="StyleSheet" HREF="/datacenter/util/css/estilo.css" TYPE="text/css">
<!--The drop down  my info list -->
<script type="text/javascript" src="./menu/js/dropdown.js"></script>
<script type="text/javascript" src="./menu/js/functionPreferences.js"></script>


	<META http-equiv="Cache-Control" content="no-cache">
	<META http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<META http-equiv="Expires" content="-1">
	<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.contain {
width: 100%; 
height: 100%;
}
.style1 {
	font-size: 4px;
	color: #FFFFFF;
}
-->
	</style>
	
<!-- *************************JCalendar***********************************-->
 <!-- calendar stylesheet -->
  <link rel="stylesheet" type="text/css" media="all" href="/datacenter/component/jscalendar-1.0/skins/aqua/theme.css" title="win2k-cold-1" />

  <!-- main calendar program -->
  <script type="text/javascript" src="/datacenter/component/jscalendar-1.0/calendar.js"></script>
  <script type="text/javascript" src='/datacenter/js/showHide.js' ></script>

  <!-- language for the calendar-->
<% if (userSession.getLenguage().equals("es")){
%><script type="text/javascript" src="/datacenter/component/jscalendar-1.0/lang/calendar-es.js"></script>
<% }else if(userSession.getLenguage().equals("en")){
%><script type="text/javascript" src="/datacenter/component/jscalendar-1.0/lang/calendar-en.js"></script>
<% }else{
%><script type="text/javascript" src="/datacenter/component/jscalendar-1.0/lang/calendar-pt.js"></script>
<%	} %>
  <!-- the following script defines the Calendar.setup helper function, which makes
       adding a calendar a matter of 1 or 2 lines of code. -->
  <script type="text/javascript" src="/datacenter/component/jscalendar-1.0/calendar-setup.js"></script>
  
  <!-- **********************end JCalendar ********************************* -->
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
		
		<!-- JQuery Pablo -->
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> 
		<!-- script type="text/javascript" src="http://jquery-blog-js.googlecode.com/files/SetCase.js"></script-->
	 	
	</head>
	<body onLoad="javascript:Onload()">
		<form name="logout" action="./login.jsp" method="post"></form>
		<form name="home" action="./layout.jsp" method="post"></form>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
    			<td colspan="2">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
      					<tr>
<%			if(sys.isCorporation(CorporationData.BAYER_MX)) {
%>        					<td><jsp:include  page='<%="topoBayerMX.jsp?home=true" %>'/></td>
<%			}else {
%>        					<td><jsp:include  page='<%="topo.jsp?home=true" %>'/></td>
<%			}
%>     					</tr>
    				</table>	
    			</td><!--end the top�s table -->
  			</tr>
  			<tr>
<% 			if(!(userSession.getRole().getName().equals("Gerente") && sys.isCorporation(CorporationData.HALLIBURTON))) {
%>  				<td width="150" rowspan="3" valign="top"><!--Start the table that will have the include of Menu inside -->
					<table width="100%" border="0" cellspacing="0" cellpadding="0" valign="top">
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
				</td><!--end the menu�s table -->
<% 			}
%>    			<td width="100%" height="10" valign="top">
    				<span class="style1"> </span>
      				<!--Start the table that will have the content of the page inside -->
      				<!--end content�s table -->
      			</td>
  			</tr>
<%		if(sys.getCorporationId() == CorporationData.IMESEVI_UY) {
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
    				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" valign="top">
      					<tr>
        					<td>
<%					if(!Util.empty(reference)) {
%>        						<jsp:include page='<%=reference%>'/>
<%					}
%>        					</td>
      					</tr>
    				</table>
    			</td>
  			</tr>
		</table>
		<jsp:include  page='<%="footer.jsp"%>'/>
	</body>
</html>
<%			}
		}
	}
%>