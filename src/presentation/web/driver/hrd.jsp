<%@page import="communication.administration.user.UserSessionData"%>
<%@page import="org.apache.struts.action.DynaActionForm"%>
<%@page language="java" 
	errorPage="../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="business.driver.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.driver.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.driver.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.driver.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>
<%!
	RequestDispatcher dispatcher; 
	String pathLanguage; Text messages; SystemAccess sys;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.EDIT_DRIVER);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
	
		dispatcher=request.getRequestDispatcher("/util/jsp/language.jsp");
		dispatcher.include(request,response);

		messages=sys.getText();
	
		String driverId = request.getParameter("driver");
		DriverData driver = sys.getDriverReportFacade().findDriverByPrimaryKey(new Integer(driverId), sys.getUserSessionData());
		if(driver != null) {
	sys.getUserSessionData().setUrl("layout.jsp?refer=driver/hrd.jsp&driver="+driverId);
	boolean isHRD = (driver.getIdDallas()!=null && driver.getIdDallas().toLowerCase().startsWith("driver.hrd.type"));
	String title = (isHRD) ? "driver.hrd.out" : "driver.hrd.in";
	
	Calendar c = Calendar.getInstance();
	// session.setAttribute("List_drivers",list);
%>


<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
<head>
	<META http-equiv="Cache-Control" content="no-cache">
	<META http-equiv="Pragma" content="no-cache">
	<META http-equiv="Expires" content="-1">		
</head>

<body bgcolor="#FFFFFF">
	<html:form action="/DriverHRD">
		<html:hidden property="driver" value='<%=driverId%>'/>
		<html:hidden property="action" value='<%=(isHRD) ? String.valueOf(LogActions.UNMARK_HRD) : String.valueOf(LogActions.MARK_HRD)%>'/>
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1">
				<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText(title)%>' />
	  		</tr>
    	  	<tr>
				<jsp:include  page="../component/spaceline.jsp" />
	      	</tr>
        	<tr>
    	    	<td colspan="3">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.data.driver")%>' />
					<table width='100%' border="0" align='center' cellpadding="0" cellspacing="0">
   						<tr align=center align="right" class='linetwo'>               
    	      				<td align="right">
        	          			<%=driver.getFirstName()+" "+driver.getLastName()%>
         					</td>
	      				</tr>
					</table>
   					<jsp:include page="../component/utilityboxbottom.jsp"/>	
				</td>
	      	</tr>
    	  	<tr>
				<jsp:include  page="../component/spaceline.jsp" />
	      	</tr>
<%
	if(isHRD) {
%>	
        	<tr>
    	    	<td colspan="3">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title=HRD"%>' />
					<table width='100%' border="0" align='center' cellpadding="0" cellspacing="0">
   						<tr align=center align="left" class='linetwo'>               
    	      				<th align="left">
        	          			<%=messages.getText("driver.hrd.date")%>
         					</th>
    	      				<td align="right">
        	          			<%=Util.formatDate(driver.getAuthorizateDate())%>
         					</td>
	      				</tr>
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
   						<tr align=center align="left" class='linetwo'>               
    	      				<th align="left">
        	          			<%=messages.getText("driver.hrd.type")%>
         					</th>
    	      				<td align="right">
        	          			<%=messages.getText(driver.getIdDallas())%>
         					</td>
	      				</tr>
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr class="linetwo">
							<th align="left"> <%=messages.getText("driver.hrd.user")%></th>
    	      				<td align="right">
        	          			<%=driver.getAuthorizateWho()%>
         					</td>
						</tr>
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr class="linetwo">
							<th align="left"> <%=messages.getText("generic.messages.observations")%></th>
    	      				<td align="right">
        	          			<%=driver.getFederativeEntity()%>
         					</td>
						</tr>
					</table>
   					<jsp:include page="../component/utilityboxbottom.jsp"/>	
				</td>
	      	</tr>
    	  	<tr>
				<jsp:include  page="../component/spaceline.jsp" />
	      	</tr>
<%			}	
%>	
			<tr>
				<td width="100%" colspan="3">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("driver.hrd.data")+"*"%>' />
					<table width="100%" cellpadding="0" cellspacing="0">
<%			if(!isHRD) {	
%>	
      					<tr  class="linetwo">
							<th align="left"> <%=messages.getText("driver.hrd.date")%></th>
							<td align="left">
				       			<jsp:include  page='<%="../component/dateComponent.jsp?index=1&day=date&month=month&year=year&form=DriverHRDForm"%>' />
						    </td>
						</tr>
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      					<tr  class="linetwo">
							<th align="left"> <%=messages.getText("driver.hrd.type")%></th>
							<td align="left">
				       			<html:select property="type" styleClass="input">
<%				for(int i = 1; i < 5; i++) {
%>				       				<html:option value='<%="driver.hrd.type"+i%>'><%=messages.getText("driver.hrd.type"+i) %></html:option>
<%				}
%>				       			</html:select>
						    </td>
						</tr>
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%			}
%>						<tr class="linetwo">
							<th align="left"> <%=messages.getText("generic.messages.observations")%></th>
						</tr>
						<tr class="linetwo">
							<td colspan="2">
								<html:textarea property="comment" cols="70" />
							</td>
						</tr>
					</table>
					<jsp:include  page="../component/utilityboxbottom.jsp" />
				</td>
			</tr>
			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
 			</tr>
			<tr  class="linetwo">
				<td colspan="2">&nbsp;</td>
				<td align="right">
					<html:submit styleClass="input"><%=messages.getText("generic.messages.confirm")%></html:submit>
		 			<html:cancel styleClass="input"><%=messages.getText("generic.messages.cancel")%></html:cancel >
				</td>
			</tr>
	    </table>
	</html:form>
</body>
<%		}else {
%>		<jsp:include  page='<%="/util/allowedData.jsp?message=driver.error.access.driver" %>'/>
<%		}
	}	
%></html:html>
