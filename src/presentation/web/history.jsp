<%@ page language="java"
    errorPage="exception.jsp"
	import="business.SystemAccess"
	import="communication.language.Text"
	import="communication.administration.corporation.util.Util"
	import="communication.util.*"
	import="java.util.Collection"
	import="java.util.Iterator"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%	Text messages = ((SystemAccess)session.getAttribute("SystemAccess")).getText();
%>
<html>
	<head>
		<title>CEPA - Data Center</title>
		<LINK REL=StyleSheet HREF="./util/css/estilo.css" TYPE="text/css">
	</head>
	<body>
		<table width="600" cellpadding="0" cellspacing="0" border="0" align="center">
			<tr class="guide1">
				<jsp:include  page='<%="./component/titlecomponent.jsp?title="+messages.getText("generic.user.fullhistory")%>' />
			</tr>
      		<tr>
				<jsp:include  page="./component/spaceline.jsp" />
   			</tr>
 			<tr>
    			<td valign="top" align="left" height="20" class="linetwo">
					<jsp:include  page='<%="./component/utilityboxtop.jsp?title="+messages.getText("generic.messages.detail")%>' />
					<table width="600" cellpadding="0" cellspacing="0" border="0">
    					<tr class="guide2" height="20">
							<td width="24%"><%=messages.getText("generic.messages.type")%></td>
							<td width="50%"><%=messages.getText("generic.message.description")%></td>
							<td width="24%"><%=messages.getText("generic.message.accesshour")%></td>
						</tr>
<%	if(session.getAttribute("Breadcrumb") != null) {			
    	Breadcrumb breadcrumb = (Breadcrumb)session.getAttribute("Breadcrumb");
		Iterator it = breadcrumb.getSortedIterator();    		
		boolean linetwo = true;
		while(it.hasNext()) {
			BreadcrumbItem item = (BreadcrumbItem)it.next();   
%>    					<tr class='<%=(linetwo) ? "linetwo" : "lineone" %>' height="20">
							<td><%=item.getType(messages)%></td>
							<td>
								<a href='<%=item.getLink()%>'>
									<%=(String)item.getString()%>
								</a>
							</td>
							<td><%=Util.formatHour(item.getDate())%></td>
						</tr>
<%			linetwo = !linetwo;
		}
	}
%>					</table>
					<jsp:include  page="./component/utilityboxbottom.jsp" />
    			</td>
    		</tr>
    	</table>
	</body>
</html>
