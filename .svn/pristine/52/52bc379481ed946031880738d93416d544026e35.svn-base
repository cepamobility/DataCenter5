<%@page language="java"
    errorPage="exception.jsp"
    import="business.*"
    import="communication.language.*"
    import="communication.util.*"
	import="java.io.File"
	import="java.util.ArrayList"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>
<%	if(session.getAttribute("lastQuery") == null) {
    	Text messages = ((SystemAccess)session.getAttribute("SystemAccess")).getText();
%>	
<html>
	<head>
		<title>CEPA - Data Center</title>
		<LINK REL=StyleSheet HREF="/datacenter/util/css/estilo.css" TYPE="text/css">
	</head>	
	<body>
		<span class="success">&nbsp;&nbsp;&nbsp;<%=messages.getText("generic.messages.nosearchs")%></span>
	</body>
</html>
<% 	}else {
    	LastSearch search = (LastSearch)session.getAttribute("lastQuery");
		byte[] b = search.getData();

		for(int i = 0; i < b.length; i++) {
	    	response.getWriter().write(b[i]);
		} 
	
	
		ArrayList arrayGraphics = search.getImages();
		if (arrayGraphics != null && arrayGraphics.size() > 0) {
			for (int i = 0; i < arrayGraphics.size(); i++) {
			    String image = "/images/" + (String)arrayGraphics.get(i);			
%>	
		<div align="center">
			<br>
			<img src='<%=image%>' />
			<br>
		</div>
		
<%			}
		}
	}
%>

