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
	boolean quarter = Boolean.parseBoolean(request.getParameter("q"));

	HashMap<String, String> fleetKeys = sys.getFleetReportFacade().findFleetKeys(new Integer(id), userSessionData);		
	System.out.println(id+"-"+period+"-"+year);
	HashMap<String,int[]> vehicleValues = sys.getEventReportFacade().getInsertedEvents(new Integer(id), new Integer(period), new Integer(year), quarter, userSessionData);
	Iterator it = fleetKeys.keySet().iterator();
	JSONObject obj = new JSONObject();
	while(it.hasNext()) {
		String divisionId = (String)it.next();
		String key = (String)fleetKeys.get(divisionId);
		JSONArray array = new JSONArray();
		int[] events = (vehicleValues.containsKey(key)) ? vehicleValues.get(key) : new int[]{0,0,0};
		array.put(events[0]);
		if(sys.isCorporation(CorporationData.MONSANTO)) {
			array.put(events[0]+events[1]);
		}else {
			array.put(events[1]);
		}
		if(sys.isCorporation(CorporationData.MONSANTO))
			array.put(events[2]);
		obj.put(divisionId,array);
	}
%>
<%=obj.toString()%>
