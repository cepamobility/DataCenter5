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
	Integer pageNum; boolean all; int pageSize; 
	String id;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.VIEW_DRIVER_LIST);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
	
		dispatcher=request.getRequestDispatcher("/util/jsp/language.jsp");
		dispatcher.include(request,response);

		messages=sys.getText();
	
		// session.setAttribute("target","DriverExclude");

		id = request.getParameter("id");
		// session.setAttribute("url","driver/DriverExclusion.jsp?checkboxParamList="+checkboxParamList+"&action="+action);
		
		DriverMainAttributes data = sys.getDriverReportFacade().findDriverMainData(new Integer(id), sys.getUserSessionData());
		if(data != null) {
	sys.getUserSessionData().setUrl(null);
%>


<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
<head>
	<META http-equiv="Cache-Control" content="no-cache">
	<META http-equiv="Pragma" content="no-cache">
	<META http-equiv="Expires" content="-1">		
</head>

<body bgcolor="#FFFFFF">
	<html:form action="/DriverAssociateAnualPlan">
		<html:hidden property="id" value='<%=id%>'/>
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1">
				<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("driver.anualplan.associateplan")%>' />
	  		</tr>
    	  	<tr>
				<jsp:include  page="../component/spaceline.jsp" />
	      	</tr>
        	<tr>
    	    	<td colspan="3">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.data")%>' />
					<table width='100%' border="0" align='center' cellpadding="0" cellspacing="0">
      					<tr>
        					<td width="100%">
		    					<table width="100%" border="0">
              						<tr class="linetwo">
					    	            <td align="left"><%=messages.getText("generic.driver.driverdata.firstname")%></td>
    	      							<td align="right">
        	          						<%=data.getFirstName()+" "+data.getLastName()%>
            	  						</td>
               						</tr>
						    	  	<tr>
										<jsp:include  page="../component/spaceline.jsp" />
							      	</tr>
              						<tr class="linetwo">
					    	            <td align="left"><%=messages.getText("generic.driver.anualplan")%></td>
    	      							<td align="right">
        	          						<html:select property="plan" styleClass="input">
        	          							<html:option value="1">Conductores Antiguos</html:option>
        	          							<html:option value="5">Managers Antiguos</html:option>
        	          							<html:option value="2">Nuevos Conductores</html:option>
        	          							<html:option value="6">Managers Nuevos</html:option>
        	          							<html:option value="3">Monitores Antiguos</html:option>
        	          							<html:option value="4">Nuevos Monitores</html:option>
        	          						</html:select>
            	  						</td>
               						</tr>
              						<tr class="linetwo">
										<td align="right" colspan="2">
											<html:submit styleClass="input"><%=messages.getText("generic.messages.save")%></html:submit>
								 			<html:cancel styleClass="input"><%=messages.getText("generic.messages.cancel")%></html:cancel >
										</td>
									</tr>
		   						</table>
          					</td>
	      				</tr>
					</table>
   					<jsp:include page="../component/utilityboxbottom.jsp"/>	
				</td>
	      	</tr>
    	  	<tr>
				<jsp:include  page="../component/spaceline.jsp" />
	      	</tr>
	    </table>
	</html:form>
</body>
<%		}else {
%>		<jsp:include  page='<%="/util/allowedData.jsp?message=driver.error.access.driver" %>'/>
<%		}
	}	
%></html:html>
