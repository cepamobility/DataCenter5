<%@page language="java" 
	   errorPage="../../exception.jsp"
	import="communication.activity.tables.*"
	import="business.activity.ActivityReportFacade"
	import="business.*"
	import="business.administration.user.*"
	import="communication.administration.user.*"
	import="presentation.translator.web.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<!-- ViewCEPAActivityScheduleRegistry -->
<%
		
		String idActivity=(String)request.getParameter("idActivity");
		if(idActivity==null){
			idActivity=(String)session.getAttribute("id");			
		}
		// session.setAttribute("id",idActivity);
		
		String idDriver=(String)request.getParameter("idDriver");
		if (Util.empty(idDriver)){
			idDriver = (String)session.getAttribute("idDriver");
		}
		// session.setAttribute("idDriver",idDriver);	
		
		String enabled = (String)request.getParameter("enabled");
		if (Util.empty(enabled)){
			enabled = (String)session.getAttribute("enabled");
			session.removeAttribute("enabled");
			System.out.println("ViewCEPAActivityScheduleRegistry.jsp: enabled="+enabled);
			if (Util.empty(enabled)) enabled = "true";
		}
		
		String back = (String)request.getParameter("page");
		if (Util.empty(back)){
			back = (String)session.getAttribute("page");
			session.removeAttribute("page");
			System.out.println("ViewCEPAActivityScheduleRegistry.jsp: page="+back);
			if (Util.empty(back)) back = "";
		}

		// Agregado por el commentary drive
		SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
		UserSessionData userSessionData=sys.getUserSessionData();	
		ActivityReportFacade  activityReport = sys.getActivityReportFacade();		
		CEPAActivityScheduleData activityData = activityReport.findCEPAActivityScheduleByPrimaryKey(
				new Integer(idActivity),userSessionData);
		
		session.setAttribute("activityData",activityData);

		boolean isEdit = true;		 
		
		String pageToRedirect = CEPAActivityConstants.getPageToRedirect(
				activityData.getDefinition(),idDriver,idActivity,enabled,isEdit, back);
%>	
		 
		<jsp:include  page='<%=pageToRedirect%>' />     

