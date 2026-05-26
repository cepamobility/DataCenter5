<%@page language="java"
    errorPage="../../exception.jsp"
	import="business.*"    
	import="communication.language.*"	
	import="java.util.ArrayList"
	import="business.activity.ActivityReportFacade"	
	import="communication.activity.tables.*"
	import="communication.administration.user.tables.*"
 	import="communication.administration.user.*" 
	import="presentation.translator.web.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>

<html:html>
<body>
 
<%	
	Text messages; SystemAccess sys;
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	messages = sys.getText();
	UserSessionData userSessionData; 
	userSessionData=sys.getUserSessionData();	
	
	String idActivity = request.getParameter("idActivity");
	String idDriver = request.getParameter("idDriver");
	
	ActivityReportFacade activityReport = sys.getActivityReportFacade();
	CEPAActivityScheduleData  activityData = null;
	if (!Util.empty(idActivity)){
		activityData = activityReport.findCEPAActivityScheduleByPrimaryKey(
			new Integer(Integer.parseInt(idActivity)),userSessionData);			
	}
	//System.out.println("reportResultOld.jsp: "+activityData.getDefinition().getResourceKeyName());
%>
	<table width="100%">
	<% 
		String guide = (String) session.getAttribute("guide");
		if (!Util.empty(guide) && !Util.empty(idActivity)){	
			System.out.println("Guides");
	%>
		<tr>
			<td width="100%" align="left">
				<jsp:include  page='<%="CurseGuideInclude.jsp?activity=" + activityData.getDefinition().getResourceKeyName()%>' />
			</td>
		</tr>
	<%
		}else{
			System.out.println("NO Guides");
		}
	%>
		<tr>
			<td width="100%" align="left">
				<jsp:include  page='<%="reportResultOldReport.jsp?idDriver="+idDriver+"&idActivity"+idActivity%>' />
			</td>
		</tr>
	</table>
	
</body>	
</html:html>
