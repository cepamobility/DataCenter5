<%@page import="communication.alert.AlertConstants"%>
<%@ page language="java"
    errorPage="exception.jsp"
	import="presentation.translator.web.user.*"	
	import="communication.security.*"
	import="java.io.File"
	import="java.net.*"
	import="java.io.FileReader"
	import="java.io.IOException"
	import="java.io.BufferedReader"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>


<html>
<%! RequestDispatcher dispatcher; String context; String pathMsg; String pathGarbagecolector;%>
<%
	new LogoutAction().logout(request);
	if(AlertConstants.isProductionServer()) {
	    response.sendRedirect("http://www.cepadc.com/datacenter/home.jsp?reporter=2d61b90cc4f60d3494de7cfa343cf1a7");
	}else {
	    response.sendRedirect(request.getContextPath()+"/home.jsp?reporter=2d61b90cc4f60d3494de7cfa343cf1a7");
	}
	

%>
