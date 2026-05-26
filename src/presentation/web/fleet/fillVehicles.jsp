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
	
	String id = request.getParameter("f");
	String period = request.getParameter("p");
	String year = request.getParameter("y");

	HashMap<String, String> fleetKeys = sys.getFleetReportFacade().findFleetKeys(new Integer(id), userSessionData);		
	System.out.println(id+"-"+period+"-"+year);
	int fleetId = 0;
	switch(sys.getCorporationId()) {
		case CorporationData.MONSANTO:
			fleetId = 9581;
			break;
		case CorporationData.ALLERGAN:
			fleetId = 9557;
			break;
		case CorporationData.DOW:
			fleetId = 9569;
			break;
	}
	HashMap<String, Integer> vehicleValues = sys.getVehicleReportFacade().getActiveVehicleCount(new Integer(fleetId), new Integer(period), new Integer(year), userSessionData);
	Iterator it = fleetKeys.keySet().iterator();
	JSONObject obj = new JSONObject();
	while(it.hasNext()) {
		String divisionId = (String)it.next();
		String key = (String)fleetKeys.get(divisionId);
		JSONArray array = new JSONArray();
		Integer miles = 0;
		if(vehicleValues.containsKey(key)) {
			array.put(vehicleValues.get(key));	
		}else {
			array.put(0);
		}
		array.put(miles);
		array.put(Util.toMiles(miles));
		obj.put(divisionId,array);
	}
%>
<%=obj.toString()%>
