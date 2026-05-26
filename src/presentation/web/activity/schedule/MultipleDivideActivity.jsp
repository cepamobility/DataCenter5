<%@page language="java"
	errorPage="../../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="business.activity.*" 
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.corporation.tables.*"
	import="communication.administration.corporation.tables.ItemData"
	import="communication.administration.user.tables.*"
	import="communication.activity.tables.*"
	import="communication.activity.tables.CEPAActivityConstants"
	import="communication.util.*"	
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*"
	import="communication.administration.user.UserSessionData" 	
	import="communication.driver.tables.DriverData" 		
	import="communication.driver.tables.DriverData" 		
	import="presentation.translator.web.util.Util"
	import="communication.activity.tables.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%! RequestDispatcher dispatcher; 
	
	Text messages; 
	SystemAccess sys; 
	CEPAActivityScheduleData data; 
	ActivityReportFacade activityReport;
	UserSessionData userSessionData;		
	Collection drivers = new LinkedList();
	
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	
	String idActivity = "";
	
	userSessionData =sys.getUserSessionData();
	
	String check = Util.checkPermission(sys,SecurityConstants.VIEW_CEPASCHEDULE_ACTIVITY);
	String check1 = Util.checkPermission(sys,SecurityConstants.MODIFY_CD_ACTIVITY);
	
	if ((check!=null) && (check1!=null))  {	
		response.sendRedirect(request.getContextPath()+check);
	}else {
		messages=sys.getText();

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

%>
	<body>
		<html:form action="/ActivityMultipleDivide">
		
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  			<tr>
   				<jsp:include  page='<%="../../component/titlecomponent.jsp?colspan=3&title="+messages.getText("web.activity.divide")%>'/>			
    		</tr>
  			<tr>
    			<jsp:include  page="../../component/spaceline.jsp" />
			</tr>
	    	<tr>
	    		<td valign="top">
					<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.administrativedata")%>' />
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td align="left" class="texts">
								<html:textarea property="id"></html:textarea>
							</td>
						</tr>										
					</table>
					<jsp:include page="../../component/utilityboxbottom.jsp"/>
				</td>
			</tr>
	  		<tr>
				<jsp:include  page="../../component/spaceline.jsp" />
	  		</tr>   
       		<tr>
             	<td height="18" colspan="3" valign="top" align="right">
           			<html:submit styleClass="input"> <%=messages.getText("generic.messages.confirm")%> </html:submit>                                                  
           			<html:cancel styleClass="input"> <%=messages.getText("generic.messages.cancel")%> </html:cancel>                                                  
 		        </td>
     		</tr>                                         
		</table>
		</html:form>
	</body>
<%
	}
%>	
