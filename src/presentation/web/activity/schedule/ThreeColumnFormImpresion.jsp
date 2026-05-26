<%@page language="java" 
	   errorPage="../../exception.jsp"
	import="business.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.activity.tables.*"
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
	UserSessionData userSessionData; 
	userSessionData=sys.getUserSessionData();	
			
	// Obtengo el formulario de esa actividad para dibujarlo en el HTML:
	String idActivity =(String)request.getParameter("idActivity");
	if(idActivity==null){
		idActivity=(String)session.getAttribute("id");			
	}
	ActivityReportFacade activityReport = sys.getActivityReportFacade();
	CEPAActivityScheduleData  activityData;
	activityData = activityReport.findCEPAActivityScheduleByPrimaryKey(
			new Integer(Integer.parseInt(idActivity)),userSessionData);
	CheckListData checkList = activityData.getDefinition().getCheckList();
	Collection points = checkList.getPoints();

	PointData point = null;
	ItemData item = null;
	Text messages=sys.getText();
	boolean encontrePoint = false;
	boolean encontreItem = false;
	String checkedStr = "";
	String peer = "";
	String superior = "";
	int primerPoint, ultimoPoint, totalPoints;
	boolean isCommentaryDrive = false;
	
	totalPoints = points.size();
	
	System.out.println("CANT PUNTOS" + totalPoints);

    int cantCol = Integer.parseInt(request.getParameter("cantCol"));
	int pointsByCol = Math.round(points.size() / cantCol);
	
	System.out.println("CANT PUNTOS POR COL" + pointsByCol);
	
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
		<jsp:include  page='<%="ImprimoColImpresion.jsp?primerPoint="+ primerPoint + "&ultimoPoint=" + ultimoPoint%>' />        				
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





