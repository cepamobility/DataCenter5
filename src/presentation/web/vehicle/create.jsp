<%@page language="java"
    errorPage="../exception.jsp"
	import="business.*"
	import="business.administration.user.*"
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.datatype.tables.*"
	import="communication.datatype.*"
	import="communication.administration.user.tables.*"
	import="java.util.*"
 	import="communication.administration.user.*" 		
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>

<%! RequestDispatcher dispatcher; String context; String pathMsg;
	Text messages; String pathLanguage;
	SystemAccess sys; UserData user; UserReportFacade userReport;
	UserSessionData userSessionData;	
%>

<%
	pathLanguage="/util/jsp/language.jsp";
	
	session.setAttribute("url","vehicle/create.jsp");

	dispatcher=request.getRequestDispatcher(pathLanguage);
	dispatcher.include(request,response);

	context=request.getContextPath();
	pathMsg="/util/jsp/message.jsp";
	
	dispatcher=request.getRequestDispatcher(pathMsg);
	dispatcher.include(request,response);
%>


<html:html>

<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	if(sys==null){
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}
	
	userSessionData=sys.getUserSessionData();	
	messages=sys.getText();
	
	if(session.getAttribute("target")==null){
		session.setAttribute("target",request.getParameter("target"));
	}			
	
	session.removeAttribute("VehicleCreateFormNew");			
	session.removeAttribute("driverId");					
	session.removeAttribute("useType");
		
%>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head></head>

<body bgcolor="#FFFFFF">
<html:form action="create" focus="nick">
<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
  		<td align="center" valign="midlle">
   			<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.selectvehicleusetype")%>' />
				<table width="100%">
					<tr class="text">
  						<td width="45%" align="center" class="texts">
							<a href="layout.jsp?refer=/driver/DriverSearch.jsp?link=layout.jsp?refer=/vehicle/create_vehicle_data.jsp" >
								 <%=messages.getText("persistence.datatype.typeuse.individual")%> </a></td>			
  						<td  width="10%">&nbsp;</td> 
						<td width="45%" align="center" class="texts">
							<a href="layout.jsp?refer=/vehicle/create_vehicle_data.jsp?useType=persistence.datatype.typeuse.institutional" >
								 <%=messages.getText("persistence.datatype.typeuse.institutional")%> </a>							</td>			
				  </tr>
  				</table>
    		<jsp:include page="../component/utilityboxbottom.jsp"/>
		</td>
	</tr>
</table>
</html:form>

</body>
</html:html>
