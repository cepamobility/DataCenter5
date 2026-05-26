<%@page language="java" 
	errorPage="exception.jsp" 
	import="java.util.*"
	import="business.*"
	import="business.vehicle.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.fleet.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.vehicle.*" 	
 	import="communication.administration.user.*" 	
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%@page import="communication.administration.corporation.tables.CorporationData"%>
<%@page import="communication.administration.corporation.tables.CountryData"%>
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
	Text messages; 
	SystemAccess sys;
	String attribute; 
	String value;
	UserSessionData userSessionData; 
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	int corporationId = sys.getCorporationId();
	userSessionData=sys.getUserSessionData();
	String check = Util.checkPermission(sys,SecurityConstants.JJ_REPORTER);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
	userSessionData.setDriver(null);

	String next = "VehicleCheckForm.jsp";

	session.setAttribute("url","vehicle.jsp");
	messages=sys.getText();
	
	String trade = null;
	String model = null;
	String plate = null;
	String year = null;
	Hashtable parameters = new Hashtable();
	if(!Util.empty(request.getParameter("trade"))) {
		trade = request.getParameter("trade");
	}
	if(!Util.empty(request.getParameter("model"))) {
		model = request.getParameter("model");
	}
	if(!Util.empty(request.getParameter("plate"))) {
		plate = request.getParameter("plate");
	}
	if(!Util.empty(request.getParameter("year"))) {
		year = request.getParameter("year");
	}
	
	if(plate == null && trade == null && model == null && year == null) {
		response.sendRedirect("vehicle.jsp?error=1");
	}else {
		Collection<VehicleData> list = sys.getVehicleReportFacade().getReportVehicles(plate, trade, model, year, userSessionData);
		if(list != null) {
	switch(list.size()) {
		case 0:
			response.sendRedirect("vehicle.jsp?error=2");
			break;
		case 1:
			VehicleData vehicle = (VehicleData)list.iterator().next();
			response.sendRedirect(next+"?id="+vehicle.getId());
			break;
		default:
%>	
	<body>
	 	<table width="100%" height="90" border="0" cellpadding="0" cellspacing="0" class="default">
		 	<form name="logout" action="../login.jsp" method="post">
	 		</form>
		 	<form name="vehicle" action="vehicle.jsp" method="post">
	 		</form>
  			<tr>
    			<td colspan="2">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" valign="top">
      					<tr>
        					<td><jsp:include  page='<%="../topMenu.jsp?home=true&reporter=true" %>'/></td>
      					</tr>
    				</table>	
    			</td><!--end the top�s table -->
  			</tr>
  		</table>
  		<br>
  		<br>	
		<table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1"> 
		    	<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("reporerjj.step1")%>' />
			</tr>
		  	<tr> 
	  			<jsp:include  page="../component/spaceline.jsp" />
		  	</tr>
			<tr> 
	  			<td colspan="3" class="red" align="left">
	  				<%=messages.getText("reporterjj.vehicle.notfound1") %>
	  			</td> 
  			</tr> 
		  	<tr> 
	  			<jsp:include  page="../component/spaceline.jsp" />
		  	</tr>
			<tr> 
	  			<td colspan="3"> 
		    		<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("reporerjj.identifyvehicle")%>' /> 
					<table width="100%" border="0" align='center' cellpadding="0" cellspacing="0">
   						<tr  class="linetwo" height="22">
				          	<td width='20%' class="guide2"><%=messages.getText("generic.vehicle.vehicledata.registrationNumber")%></td>
				            <td width='30%'	class="guide2"><%=messages.getText("generic.vehicle.vehicledata.trade")%></td>
				            <td width='30%'	class="guide2"><%=messages.getText("generic.vehicle.vehicledata.model")%></td>
				            <td width='20%'	class="guide2"><%=messages.getText("generic.vehicle.vehicledata.year")%></td>
						</tr>		
<%		Iterator it = list.iterator();
		String style = "linetwo";
		while(it.hasNext()) {
			vehicle = (VehicleData)it.next();
			String link = next+"?id="+vehicle.getId();
%>
   						<tr  class="<%=style%>" height="22">
				          	<td>
				          		<a href='<%=link%>'>
				          			<%=vehicle.getMatricula()%>
				          		</a>
				          	</td>
				          	<td>
			          			<%=messages.getText(vehicle.getTrade().getResourceBundleId())%>
				          	</td>
				            <td><%=vehicle.getModel()%></td>
				            <td><%=String.valueOf(vehicle.getYear())%></td>
						</tr>		
			
<%			style = (style.equals("linetwo")) ? "lineone" : "linetwo"; 
		}
%>
					</table>			
	    			<jsp:include page="../component/utilityboxbottom.jsp"/>
		      	</td>	
		  	</tr>
		  	<tr> 
		    	<jsp:include  page="../component/spaceline.jsp" />
		  	</tr>
			<tr>
				<td colspan="3" align="right">
	    			<jsp:include  page='../component/utilitybox2top.jsp' />
	    			<table width="100%" cellpadding="0" cellspacing="0">
   						<tr  class="lineone">
							<td width="100%" colspan="2" align="right" class="lineone">
								<input type="button" value='<%=messages.getText("generic.messages.back")%>' onclick="document.forms['vehicle'].submit();" />
		    				</td>   					
		    			</tr>
					</table>			
	    			<jsp:include page="../component/utilitybox2bottom.jsp"/>
		      	</td>	
		  	</tr>
		  	<tr> 
		    	<jsp:include  page="../component/spaceline.jsp" />
		  	</tr>
		</table>		
	</body>
<%					}	
				}
			}
	}
%>		
</html>
