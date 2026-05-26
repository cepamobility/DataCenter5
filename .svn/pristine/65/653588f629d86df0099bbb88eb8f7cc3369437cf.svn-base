<%@page language="java" 
	errorPage="../../exception.jsp"
	import="communication.activity.tables.*"
	import="business.activity.ActivityReportFacade"
	import="business.SystemAccess"
	import="presentation.translator.web.util.Util"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
		String idActivity=(String)request.getParameter("idActivity");
		if(idActivity==null){
			idActivity=(String)session.getAttribute("id");			
		}
		// session.setAttribute("id",idActivity);
		
		String idDriver=(String)request.getParameter("idDriver");
		if(idDriver==null){
			idDriver=(String)session.getAttribute("idDriver");			
		}
		// session.setAttribute("idDriver",idDriver);

		// Para Commentary Drive de J&J
		String enabled = (String) request.getParameter("enabled");
		if (Util.empty(enabled)){
			enabled = "true";
		}

		// Agregado por el commentary drive
		SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
		CEPAActivityScheduleData activityData = sys.getActivityReportFacade().findCEPAActivityScheduleByPrimaryKey(new Integer(Integer.parseInt(idActivity)),false,sys.getUserSessionData());
		session.setAttribute("activityData",activityData);
		
		boolean isEdit = false;
		String back = null; 
		String pageToRedirect = CEPAActivityConstants.getPageToRedirect(
				activityData.getDefinition(),idDriver,idActivity,enabled,isEdit, back);
%>	
		<jsp:include  page='<%=pageToRedirect%>' />     
		   	
