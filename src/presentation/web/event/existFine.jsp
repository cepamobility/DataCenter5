<%@page import="communication.driver.tables.DriverData"%>
<%@page import="communication.datatype.tables.FineQualifyDataType"%>
<%@page import="communication.event.tables.FineDataType"%>
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
	boolean b = false;	
	if(userSessionData.isBrasilCorporation()) {
		Integer driver = new Integer(request.getParameter("driver"));
		Integer hour = (request.getParameter("hour").equals("")) ? new Integer(0) : new Integer(request.getParameter("hour"));
		Integer minute = (request.getParameter("minute").equals("")) ? new Integer(0) : new Integer(request.getParameter("minute"));
		Date date = Util.getDate(request.getParameter("date"),request.getParameter("month"),request.getParameter("year"),hour,minute);
		String type = request.getParameter("type");
		FineDataType fine = new FineDataType();
		fine.setDriver(new DriverData(driver));
		fine.setDate(date);
		fine.setHour(Util.getTime(hour.toString(),minute.toString()));
		fine.setQualify(new FineQualifyDataType(type));
		b = sys.getEventReportFacade().existFine(fine,userSessionData);
		System.out.println("b "+b);
	}
	JSONObject obj = new JSONObject();
	obj.put("result", b);
%>
<%=obj.toString()%>
