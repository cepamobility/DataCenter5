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
<%@page import="communication.driver.tables.DriverData"%>
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
		if(request.getParameter("formType") == null && Integer.parseInt(request.getParameter("change")) == 1) {
	response.sendRedirect(request.getContextPath()+"/reporterjj/forms.jsp");
		}else {

	int type = Integer.parseInt(request.getParameter("formType"));
	userSessionData.setDriver(null);

	String next = "form.jsp";
	switch(type) {
		case 1:
	next = "cd.jsp";
	break;
		case 3:
	next = "activity.jsp";
	break;
	}

	session.setAttribute("url","driver.jsp?formType="+type);
	messages=sys.getText();
	
	Hashtable parameters = new Hashtable();
	if(!Util.empty(request.getParameter("firstName"))) {
		parameters.put("firstName",request.getParameter("firstName"));
	}
	if(!Util.empty(request.getParameter("lastName"))) {
		parameters.put("lastName",request.getParameter("lastName"));
	}
	if(!Util.empty(request.getParameter("email"))) {
		parameters.put("email",request.getParameter("email"));
	}
	if(!Util.empty(request.getParameter("corporationId"))) {
		parameters.put("corporationId",request.getParameter("corporationId"));
	}
	
	if(parameters.size() == 0) {
		response.sendRedirect("driver.jsp?formType="+type+"&error=1");
	}else {
		Collection drivers = sys.getDriverReportFacade().findDriverReport(parameters,sys.getUserSessionData());
		switch(drivers.size()) {
	case 0:
		response.sendRedirect("driver.jsp?formType="+type+"&error=2");
		break;
	case 1:
		DriverData driver = (DriverData)drivers.iterator().next();
		sys.getUserSessionData().setDriver(driver);
		response.sendRedirect(next);
		break;
	default:
%>	
	<body>
	 	<table width="100%" height="90" border="0" cellpadding="0" cellspacing="0" class="default">
		 	<form name="logout" action="../login.jsp" method="post">
	 		</form>
		 	<form name="driver" action="driver.jsp" method="post">
		 		<input type="hidden" name="formType" value="<%=type%>" />
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
	  				<%=messages.getText("reporterjj.driver.notfound") %>
	  			</td> 
  			</tr> 
		  	<tr> 
	  			<jsp:include  page="../component/spaceline.jsp" />
		  	</tr>
			<tr> 
	  			<td colspan="3"> 
		    		<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("reporerjj.identifydriver")%>' /> 
					<table width="100%" border="0" align='center' cellpadding="0" cellspacing="0">
   						<tr  class="linetwo" height="22">
				          	<td width='40%' class="guide2"><%=messages.getText("generic.driver.driverdata.firstname")%></td>
				            <td width='40%'	class="guide2"><%=messages.getText("generic.driver.driverdata.lastname")%></td>
<%		if(sys.isCorporation(CorporationData.JJ)) {
%>				            <td width='20%'	class="guide2"><%=messages.getText("generic.driver.driverdata.wwid")%></td>
<%		}
%>						</tr>		
<%		Iterator it = drivers.iterator();
		String style = "linetwo";
		while(it.hasNext()) {
			driver = (DriverData)it.next();
			String link = next+"?id="+driver.getId();
%>
   						<tr  class="<%=style%>" height="22">
				          	<td>
				          		<a href='<%=link%>'>
				          			<%=driver.getFirstName()%>
				          		</a>
				          	</td>
				          	<td>
				          		<a href='<%=link%>'>
				          			<%=driver.getLastName()%>
				          		</a>
				          	</td>
<%		if(sys.isCorporation(CorporationData.JJ)) {
%>				            <td><%=driver.getCorporationId()%></td>
<%		}
%>						</tr>		
			
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
								<input type="button" value='<%=messages.getText("generic.messages.back")%>' onclick="document.forms['driver'].submit();" />
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
<%				}	
			}
		}
	}
%>		
</html>
