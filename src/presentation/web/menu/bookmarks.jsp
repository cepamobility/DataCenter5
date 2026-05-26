<%@page language="java"
	import="business.*"
 	import="communication.util.*" 	
 	import="communication.language.*"
 	import="communication.administration.bookmark.tables.*"
 	import="java.util.*"
	errorPage="../exception.jsp"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	if(sys != null) {
	    Text messages = sys.getText();
%>	    
<html:html>
	<head>
		<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	</head>
	<body>
		<html:form action="/BookmarkDeleteItem">
		<table width="600" border="0" cellpadding="0" cellspacing="0" align="center">
			<tr class="guide1">
				<jsp:include  page='<%="./component/titlecomponent.jsp?colspan=5&title="+messages.getText("generic.messages.bookmark")%>' />
			</tr>
	   		<tr>
				<jsp:include  page='<%="./component/spaceline.jsp" %>'/>
  			</tr>
  			<tr>
    			<td colspan="3">
					<jsp:include  page='<%="./component/utilityboxtop.jsp?title="+messages.getText("generic.bookmark.list")%>' />
    				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      					<tr>
			                <td class="guide2" width="2%"></td>
			                <td class="guide2" width="37%"><%=messages.getText("bookmark.data.name")%></td>
			                <td class="guide2" width="25%"><%=messages.getText("bookmark.data.default")%></td>
			                <td class="guide2" width="37%"><%=messages.getText("bookmark.data.query")%></td>
			            </tr>    
<%
	    Collection bookmarks = sys.getUserReportFacade().getCompleteBookmarks(sys.getUserSessionData());
	    Iterator bIt = bookmarks.iterator();
	    while(bIt.hasNext()) {
		    BookmarkData bookmark = (BookmarkData)bIt.next();
%>						<tr class="lineone">
			                <td><input type="checkbox" name='<%="bookmark"+String.valueOf(bookmark.getId())%>'/></td>
			                <td>
			                	<a href='<%="layout.jsp?refer=/user/editBookmark.jsp?id="+String.valueOf(bookmark.getId())%>'>
				                	<%=bookmark.getName()%>
			                	</a>
			                </td>
			                <td><%=(bookmark.isStart()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no")%></td>
			                <td><%=messages.getText(bookmark.getActionName())%></td>
			            </tr>    
<%	    }
%>
      					<tr>
			                <td class="linetwo" colspan="4" align="right">&nbsp;</td>
			            </tr>
      					<tr>
			                <td class="linetwo" colspan="4" align="right">
			                	<html:submit value='<%=messages.getText("generic.messages.delete")%>' styleClass="input"></html:submit>
			                </td>
			            </tr>
					</table>
					<jsp:include  page='<%="./component/utilityboxbottom.jsp" %>'/>
				</td>
			</tr>
		</table>	
		</html:form>
	</body>
</html:html>
<%	}
%>

