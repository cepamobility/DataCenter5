<%@page language="java" 
	errorPage="../exception.jsp"
	import="business.*"
	import="business.datatype.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.event.*"
	import="communication.event.tables.*"
	import="communication.driver.tables.*"
	import="communication.language.*"
	import="communication.datatype.*"
	import="communication.datatype.tables.*"
	import="communication.driver.tables.*"
	import="communication.vehicle.tables.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>
<%!
	RequestDispatcher dispatcher; SystemAccess sys;	String context;
	String pathLanguage; Text messages; Integer driverId; String driverName; String driverLastName;
	EventForm eventForm;EventDataType eventDataType;
	String action;Integer eventId;
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	if(sys == null){
		response.sendRedirect("/exception.jsp");
	}

	context=request.getContextPath();
	dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
	dispatcher.include(request,response);
	
	messages=sys.getText();

	eventId = new Integer(request.getParameter("eventId"));
	EventDataType eventDataType = sys.getEventReportFacade().findEventByPrimaryKey(eventId,sys.getUserSessionData());
%>
	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>
	
	<html:form action="/Eventsendrecomendation" >
		<html:hidden property="id" value='<%=String.valueOf(eventId)%>' />
		<html:hidden property="drivermail" value='<%=eventDataType.getDriver().getEmail()%>' />
		<body bgcolor="#FFFFFF">
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  				<tr class="guide1">
  					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.event.sendrecomendation")%>' />
			  	</tr>
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
				<tr>
					<td width="100%" colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.recomendation")+"*"%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="500">
									<html:textarea property="recomendation" cols="80" rows="15" />
								</td>
							</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
	  			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
	  			<tr>
	  				<td colspan="3">
						<table width="100%">
							<tr>
				        		<td align="right">
				          			<div align="right">
				            			<html:submit styleClass="input"><%=messages.getText("generic.messages.save")%></html:submit>
						 				<html:cancel styleClass="input"><%=messages.getText("generic.messages.cancel")%></html:cancel >
				          			</div>
				          		</td>
				  			</tr>
						</table>
	  				</td>
	  			</tr>
			</table>
		</body>
	</html:form>
</html:html>
