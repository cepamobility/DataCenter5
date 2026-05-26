<%@page language="java"
    errorPage="../exception.jsp"
	import="java.util.ArrayList"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>
<%	
	session.setAttribute("url","bookmark.jsp");
	byte[] b = (byte[])session.getAttribute("resp");

	for(int i = 0; i < b.length; i++) {
	    response.getWriter().write(b[i]);
	} 
	
	
	String image = null;
	ArrayList arrayGraphics = (ArrayList)session.getAttribute("arrayGraficas");
	session.removeAttribute("arrayGraficas");
	if (arrayGraphics != null && arrayGraphics.size() > 0)
	{
		for (int i = 0; i < arrayGraphics.size(); i++)
		{
			image = "/images/" + (String)arrayGraphics.get(i);			
%>	
		<div align="center">
			<br>
			<img src='<%=image%>' />
			<br>
		</div>
		
<%		}

	}
%>

