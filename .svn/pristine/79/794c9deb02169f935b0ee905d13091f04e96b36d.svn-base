<%@page import="communication.vehicle.report.CheckListImageData"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="communication.activity.tables.PointCommentData"%>
<%@page import="communication.activity.tables.ItemValueStrData"%>
<%@page import="communication.activity.tables.ItemData"%>
<%@page import="communication.activity.tables.PointData"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.Collection"%>
<%@page import="communication.activity.tables.CheckListData"%>
<%@page import="communication.vehicle.tables.VehicleData"%>
<%@page import="communication.vehicle.tables.VehicleCheckData"%>
<%@ page contentType="application/json" %>

<%@page import="java.util.HashMap"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONTokener"%>
<%@page import="org.json.JSONStringer"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.json.JSONArray"%>
<%@page import="java.util.Enumeration"%>
<%@page import="communication.language.Text"%>
<%@page import="business.SystemAccess"%>

<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	Text messages = sys.getText();
	CheckListImageData idata=new CheckListImageData();
	idata.setImageBase64(request.getParameter("data"));
%>