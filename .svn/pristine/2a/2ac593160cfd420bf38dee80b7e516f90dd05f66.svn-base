<%@page import="java.util.Date"%>
<%@page import="presentation.translator.web.util.Util"%>
<%@page import="communication.administration.corporation.tables.CorporationData"%>
<%@page import="communication.fleet.tables.FleetDivisionData"%>
<%@page language="java" 
	errorPage="../exception.jsp"
	import="java.util.HashMap"
	import="communication.fleet.tables.FleetData"
	import="business.fleet.FleetReportFacade"
	import="communication.administration.user.UserSessionData"
	import="business.SystemAccess"
	import="org.json.JSONObject"
	import="org.json.JSONArray"
	import="java.util.Iterator"
%>

<%!
	SystemAccess sys;
	UserSessionData userSessionData;
%>
<%
	
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	userSessionData = sys.getUserSessionData();
			
	Integer id = new Integer(request.getParameter("id"));
	Integer driver = new Integer(request.getParameter("driver"));
	Integer vehicle = (request.getParameter("vehicle").equals("0")) ? null : new Integer(request.getParameter("vehicle"));
	String plate = (request.getParameter("plate").equals("")) ? null : request.getParameter("plate");
	Date date = Util.getDate(request.getParameter("date"),request.getParameter("month"),request.getParameter("year"));
	String type = request.getParameter("type");
	int eventType = Integer.parseInt(request.getParameter("eventtype"));
	JSONObject obj = new JSONObject();
	boolean b = sys.getEventReportFacade().existEvent(id,driver,vehicle,plate,date,type,eventType,userSessionData);
	System.out.println("b "+b);
	obj.put("result", b);
%>
<%=obj.toString()%>
