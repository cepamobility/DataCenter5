<%@page language="java"
 import="java.util.*"
 errorPage="../exception.jsp"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>


<html:html>




<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
<%
	if(session.getAttribute("SystemAccess")==null){
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}
	
%>

<head/>

<%! LinkedList list;String target;%>
<%
	//Param target in scope request 
	//is a name of reply result.   
	target=(String)request.getAttribute("target");
%>

<body bgcolor="#FFFFFF">
<html:form action="Corporationsearch">
<table width="350" border="0" cellspacing="0" cellpadding="0">
    <tr>
    <td width="5%" align="right" class="default" cellspacing="0">
      <html:hidden property="target" value='<%=target%>' styleClass="input" />    </td>
  	<td width="35%" align="right" class="default" cellspacing="0">
      <html:text property="value" size="16"   styleClass="input"/>    </td>
  	<td width="25%" align="right">
      <html:submit styleClass="input">
			Search
      </html:submit>    </td>
  </tr>
</table>
</html:form>

</body>

</html:html>
