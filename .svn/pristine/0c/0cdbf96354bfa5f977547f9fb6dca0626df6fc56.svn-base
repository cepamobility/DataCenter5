<%@page language="java" 
	import="business.*"
	import="business.administration.corporation.*" 
	import="communication.language.*"
	import="communication.administration.corporation.tables.*"
	import="communication.security.*"
	import="presentation.translator.web.administration.corporation.*"
	import="presentation.translator.web.util.*"
	import="java.util.*"
	import="org.apache.struts.action.*"
	errorPage="../exception.jsp"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>	   

<%! Text messages; 
	String unitName;
%>
<%
	SystemAccess sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.MODIFY_CORPORATION);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		messages=sys.getText();

		session.setAttribute("url","corporation/RenameUnit.jsp");
		unitName = request.getParameter("unitName");
%>
	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>
	<body>
		<html:form action="Corporationrenameunit">
			<html:hidden property="level" value='<%=request.getParameter("level")%>' />
			<html:hidden property="level1" value='<%=request.getParameter("level1")%>' />
			<html:hidden property="level2" value='<%=request.getParameter("level2")%>' />
			<html:hidden property="level3" value='<%=request.getParameter("level3")%>' />
			<html:hidden property="unitId" value='<%=request.getParameter("unitId")%>' />
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.corporation.unit.edit")+"      "+unitName%>' />
  				</tr>
      			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
  				</tr>
				<tr width='100%' class="linetwo">
    				<td colspan="3" valign="top">
						<%=messages.getText("generic.corporation.unit.newname")%>&nbsp;
						<input type="text" name="unitName" size="30" class="input"/>
					</td>
				</tr>	
		      	<tr>
					<jsp:include  page="../component/spaceline.jsp" />
		  		</tr>
				<tr>
					<td class="linetwo" align="right">
					   	<html:submit value='<%=messages.getText("generic.messages.accept")%>' styleClass="input"/>
					   	<html:cancel value='<%=messages.getText("generic.messages.cancel")%>' styleClass="input"/>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
<%	}
%>
</html:html>
