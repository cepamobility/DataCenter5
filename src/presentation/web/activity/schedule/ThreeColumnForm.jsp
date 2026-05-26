<%@page language="java" 
	errorPage="../../exception.jsp"
	import="business.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.activity.tables.*"
	import="communication.administration.corporation.tables.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.*"
	import="communication.datatype.*"	
	import="business.activity.ActivityReportFacade"
	import="java.util.*"
	import="java.lang.*"
	import="communication.activity.tables.*"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	String idActivity;
	// Obtengo el formulario de esa actividad para dibujarlo en el HTML:
	idActivity =(String)request.getParameter("idActivity");
	if(idActivity==null){
		idActivity=(String)session.getAttribute("id");
	}

	
	CEPAActivityScheduleData activityData = (CEPAActivityScheduleData) session.getAttribute("activityData");

	CheckListData checkList = activityData.getDefinition().getCheckList();
	Collection points = checkList.getPoints();

	int primerPoint, ultimoPoint, totalPoints;
	
	totalPoints = points.size();
	
    int cantCol = Integer.parseInt(request.getParameter("cantCol"));
	int pointsByCol = Math.round(points.size() / cantCol);
	
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

%>
        <td width="200" valign="top"> 		
		<jsp:include  page='<%="ImprimoCol.jsp?primerPoint="+ primerPoint + "&ultimoPoint=" + ultimoPoint%>' />        				
		</td>
<%		if (col < cantCol) {
%>		
        	<td valign="top">&nbsp;</td>

 <%		
		}
		primerPoint = col*pointsByCol + 1;
		
	}	
%>		

</tr>
</table>





