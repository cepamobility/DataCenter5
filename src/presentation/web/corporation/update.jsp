<%@page language="java" 
	import="java.util.*"
	import="communication.administration.corporation.tables.*"
	import="presentation.translator.web.user.*"
	errorPage="../exception.jsp"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>


<%!String cid; RequestDispatcher dispatcher; String context;String pathView; String pathMsg; String pathSearch;%>
<%
	context=request.getContextPath();
	pathMsg="/util/jsp/message.jsp";
	pathView="/corporation/view.jsp";
	
	dispatcher=request.getRequestDispatcher(pathMsg);
	dispatcher.include(request,response);

	request.setAttribute("target","update");
	pathSearch="/corporation/search.jsp";

	dispatcher=request.getRequestDispatcher(pathSearch);
	dispatcher.include(request,response);
%>


<html:html>

<%
	if(session.getAttribute("SystemAccess")==null){
		response.sendRedirect("/login.jsp");
	}
	
%>


<head/>

<body bgcolor="#FFFFFF">


<%! Collection col; Iterator iter; CorporationData crpData; CreateForm createForm; String uid;%>
<%
	col=(Collection)session.getAttribute("corporationSearchList");
	session.removeAttribute("corporationSearchList");
	
	if(col!=null && col.isEmpty()){%>
		<table border="0">
		<tr align="center">
			<td style='width:16'></td>
			<td width='50%'>Corporation name</td>
		</tr>
</table>
	
		No Result

<%	}
	
	else
	if(col!=null)
	{
%>
 		<table width="100%" border="0">
			<tr align="center">
				<td style='width:16'></td>
				<td width='50%'>Corporation name</td>
			</tr>
		</table>
<%
		iter=col.iterator();
%>	
			<table name="corporations" width="50%" >
<%	
				while(iter.hasNext()){
					crpData=(CorporationData)iter.next();
%>
					<tr align=center>
						<td style='width:16'>
							<a href='layout.jsp?refer=/corporation/view.jsp?cid=<%=crpData.getName()%>' style="border:0">
								<img src="/datacenter/util/imgs/stock_edit-16.png" border='0'>
							</a>
								
						</td>
						<td width='50%'>
							<%=crpData.getName()%>
						</td>
					</tr>
<%
				}

%>	
			</table>
			
			<table border="0">
				<tr>
					<td>
						<input type="hidden" name="trash1" />
					</td>
					<td>
						<input type="hidden" name="trash2" />
					</td>
				</tr>
			</table>
			
<%
		}
		
%>

</html:html>

