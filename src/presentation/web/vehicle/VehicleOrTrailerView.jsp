<%@page language="java" 
	errorPage="../exception.jsp" 
	import="communication.vehicle.tables.*"
	import="business.vehicle.VehicleReportFacade"
	import="business.*"
	import="business.administration.user.*"
	import="communication.administration.user.*"
	import="presentation.translator.web.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
		String idVehicleOrTrailer=(String)request.getParameter("id");
		if(idVehicleOrTrailer==null){
			idVehicleOrTrailer=(String)session.getAttribute("id");			
		}	
		
		String type=(String)request.getParameter("isTrailer");
		if(type.equalsIgnoreCase("true")){
			// Si es trailer lo redirijo a la pagina de Trailers:
			session.setAttribute("trailer",idVehicleOrTrailer);
			String pageToRedirect= "TrailerView.jsp?trailer=" + idVehicleOrTrailer;	
			%>
			<!-- Trailer -->
			<jsp:include  page='<%=pageToRedirect%>' />        	
			<%
		}else{
			// Si es vehiculo lo redirijo a la pagina de Vehiculos:
			session.setAttribute("id",idVehicleOrTrailer);
			String pageToRedirect= "VehicleView.jsp?id=" + idVehicleOrTrailer;	
			%>
			<!-- Vehicle -->
			<jsp:include  page='<%=pageToRedirect%>' />        	
			<%
		}

%>