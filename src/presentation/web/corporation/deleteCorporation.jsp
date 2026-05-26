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
	
	session.setAttribute("url","corporation/deleteCorporation.jsp");

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
		
%>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head/>

<body bgcolor="#FFFFFF">
<html:form action="Corporationdelete" focus="nick">
<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
  		<td align="center" valign="midlle">
   			<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.deletecorporation")%>' />
				<table width="100%">
		          <tr class="texts">
        		    <th width="224" align="left"> <%=messages.getText("generic.corporation.corporationdata.corporationId")%> </th>
		            <td align="left"><div align="right">
        	        <html:text property="id" style="width:35;" styleClass="input"/>
            		</div></td>
		          </tr>
  				</table>
    		<jsp:include page="../component/utilityboxbottom.jsp"/>
		</td>
	</tr>
	
	 				<tr>
  				  	<td height="18" colspan="3" valign="top">
          				<div align="right">
						 <html:submit styleClass="input"> <%=messages.getText("generic.messages.accept")%> </html:submit>
				  		</div>    
					</td>
				</tr>
</table>
</html:form>

</body>
</html:html>
