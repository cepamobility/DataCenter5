<%@page language="java"
	errorPage="../../exception.jsp"
	import="java.util.*"
	import="communication.language.*"
	import="business.SystemAccess"	
	import="communication.administration.user.*"
%>

<%!	UserSessionData userSessionData; Integer crpName; String lngName; Text messages; SystemAccess sys; %>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	
	if(sys==null){
		response.sendRedirect("/login.jsp");	
	}
	else{
//		userSessionData=(UserSessionData)session.getAttribute("UserSessionData");
		userSessionData=sys.getUserSessionData();
		crpName=userSessionData.getFilter().getCorporationId();
		lngName=userSessionData.getLenguage();
//		messages=(Text)session.getAttribute("Text");
		messages=sys.getText();
	}
		
	
%>