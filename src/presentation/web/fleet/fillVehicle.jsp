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
	
	String id = request.getParameter("fleet");

	HashMap<String, String> fleetKeys = sys.getFleetReportFacade().findFleetKeys(new Integer(id), userSessionData);		
	HashMap<String, Integer> vehicleValues = sys.getVehicleReportFacade().getActiveVehicleCount(userSessionData);
	Iterator it = fleetKeys.keySet().iterator();
	JSONObject obj = new JSONObject();
	while(it.hasNext()) {
		String divisionId = (String)it.next();
		String key = (String)fleetKeys.get(divisionId);
		if(vehicleValues.containsKey(key)) {
			obj.put(divisionId,vehicleValues.get(key));
		}else {
			obj.put(divisionId,0);
		}
	}
%>
<%=obj.toString()%>