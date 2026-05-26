<%@page language="java" 
	errorPage="../exception.jsp" 
	import="business.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.activity.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.ActivityTypeDataType"
	import="business.vehicle.VehicleReportFacade"
	import="java.util.*"
	import="java.lang.*"
	import="communication.activity.tables.*"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");

	// Obtengo el formulario de esa actividad para dibujarlo en el HTML:
	String idVehicle =(String)request.getParameter("idVehicle");
	if(idVehicle==null){
		idVehicle=(String)session.getAttribute("id");
	}
	
	VehicleReportFacade vehicleReport = sys.getVehicleReportFacade(); 
	CheckListData checkList = vehicleReport.findVehicleCheckListNalco(sys.getUserSessionData());
	session.setAttribute("checkList",checkList);	
	
	Collection points = checkList.getPoints();
	
	int primerPoint, ultimoPoint, totalPoints;
	
	totalPoints = points.size();
	
    int cantCol = (request.getParameter("cantCol") != null) ? 
    		Integer.parseInt(request.getParameter("cantCol")) : 1;
	int pointsByCol = Math.round(points.size() / cantCol);
	String alertType = request.getParameter("type");
	primerPoint = 1;
	
%>

    <table width="100%" border="0" cellspacing="0" cellpadding="0">	
    <tr> 
<%
	for (int col=1; col <= cantCol;col++) { 
		if (col == cantCol) {
			ultimoPoint = totalPoints;
		}
		else {
			ultimoPoint = primerPoint+pointsByCol-1;
		}
		
%>		<td width="600" valign="top">
		<jsp:include  page='<%="ImprimoColViewNalco.jsp?primerPoint="+ primerPoint + "&ultimoPoint=" + ultimoPoint%>' />
		</td>        				
<%
		if (col < cantCol) {
%>		
        	<td valign="top">&nbsp;</td>
 <%		
		}
		primerPoint = col*pointsByCol + 1;
		
	}	
%>		
</tr>
</table>
