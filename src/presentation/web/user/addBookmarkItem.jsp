<%@page language="java" 
	errorPage="../exception.jsp"
	import="business.*"
	import="communication.administration.bookmark.tables.*" 
	import="communication.language.*"
	import="java.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>	   
	<head>
		<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
		<style type="text/css">
<!--	body {
			background-color: #F7F7F7;
		}
-->
		</style>
	</head>
<%! RequestDispatcher dispatcher; String pathMsg;
	Text messages; 
%>
<%
	pathMsg="/util/jsp/message.jsp";
	
	dispatcher=request.getRequestDispatcher(pathMsg);
	dispatcher.include(request,response);
	
	SystemAccess sys=(SystemAccess)session.getAttribute("SystemAccess");
	messages=sys.getText();

	String level = request.getParameter("level");
%>
	<script>
		function init(level) {
			if(level == -1){
				self.opener.document.forms[1].submit();
				window.close();
			}
		}
	</script>
 	<body onLoad="init(<%=level%>)">
<%	if(session.getAttribute("Bookmark") != null) {
		BookmarkData bookmark = (BookmarkData)session.getAttribute("Bookmark"); 
%>		
		<html:form action="/BookmarkAddItem" >
			<table width='350' height="200" align='center' cellpadding="0" cellspacing="0" bgcolor="#F7F7F7">
			 	<tr>
			 		<td height="10">&nbsp;</td><td height="10">&nbsp;</td>
			 	</tr>
				<tr class="lineone">
					<td height="30" align="left" colspan="2" class="title">
						<%=messages.getText("generic.bookmark.additem")%>
					</td>
				</tr>
				<tr><td height="10">&nbsp;</td><td height="10">&nbsp;</td></tr>
				<tr class="lineone">
					<td height="30" align="left"><b><%=messages.getText("generic.messages.name")%></b></td>
					<td height="30" align="left">
						<html:text property="name" styleClass="input" value='<%=messages.getText(bookmark.getActionName())%>' style="width: 250;"/>
					</td>
				</tr>
				<tr><td height="10">&nbsp;</td><td height="10">&nbsp;</td></tr>
				<tr class="lineone">
					<td height="30" align="left" colspan="2">
						<html:checkbox property="start"/>
						<%=messages.getText("generic.bookmark.setasstart")%>
					</td>
				</tr>
				<tr><td height="10">&nbsp;</td><td height="10">&nbsp;</td></tr>
				<tr>	
					<td colspan="2">
						<div align="center">
							<html:submit value='<%=messages.getText("generic.messages.accept")%>' styleClass="input" />				    		  		
							<input type="button" value='<%=messages.getText("generic.messages.cancel")%>' onClick="javascript:window.close()" class="input"/>				    		  		
						</div>
					</td>
				</tr>
	  		</table>		
		</html:form>	
<%	}
%>	</body>
</html:html>