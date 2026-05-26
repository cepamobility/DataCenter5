<%@page language="java" 
	errorPage="../exception.jsp"
	import="business.*"
	import="communication.administration.bookmark.tables.*" 
	import="presentation.translator.web.administration.bookmark.*"
	import="communication.language.*"
	import="java.util.*"
	import="org.apache.struts.action.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>	   
	<head>
		<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
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

	BookmarkData bookmark = sys.getUserReportFacade().getBookmarkData(new Integer(request.getParameter("id")),sys.getUserSessionData());
	BookmarkUpdateForm form = new BookmarkUpdateForm(bookmark);
	session.setAttribute("BookmarkUpdateForm",form);
%>
 	<body>
		<html:form action="/BookmarkUpdateItem" >
			<html:hidden property="id" />
			<table align='center' cellpadding="0" cellspacing="0" width="500">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.bookmark.updateitem")%>' />
				</tr>
		   		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
	  			<tr>
	    			<td colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.bookmark.data")%>' />
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr class="linetwo">
									<td align="left"><b><%=messages.getText("generic.messages.name")%></b></td>
									<td align="right">
										<html:text property="name" styleClass="input" style="width: 350;"/>
									</td>
								</tr>
								<tr><td height="5">&nbsp;</td><td height="5">&nbsp;</td></tr>
								<tr class="linetwo">
									<td align="left" colspan="2">
										<html:checkbox property="start"></html:checkbox>
										<%=messages.getText("generic.bookmark.setasstart")%>
									</td>
								</tr>
								<tr><td height="5">&nbsp;</td><td height="5">&nbsp;</td></tr>
								<tr>	
									<td colspan="2">
										<div align="right">
											<html:submit value='<%=messages.getText("generic.messages.accept")%>' styleClass="input" />				    		  		
											<html:cancel value='<%=messages.getText("generic.messages.cancel")%>' styleClass="input" />				    		  					    		  		
										</div>
									</td>
								</tr>
							</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
	  		</table>		
		</html:form>	
	</body>
</html:html>