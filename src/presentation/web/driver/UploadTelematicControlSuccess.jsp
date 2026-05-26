<%@ page language="java"
    errorPage="../exception.jsp"
	import="business.*"
	import="presentation.translator.web.util.*"
	import="communication.language.*"
	import="communication.util.*"
	import="java.util.*"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	Text messages = sys.getText();

	String total = request.getParameter("total");
	
	
	

//	Boolean view = (Boolean) (session.getAttribute("view"));
//	Boolean view = (Boolean) (session.getAttribute("activityview"));
%>

<html>
	<head>
		<LINK REL=StyleSheet HREF="./util/css/estilo.css" TYPE="text/css">
	</head>
 
	<body>
		<center>
			<table width="600" border="0" cellpadding="0" cellspacing="0" class="guideSearch">
		  		<tr>
		    		<td width="19" height="30"><img src="./util/imgs/left_top_search.jpg" width="19" height="30" /></td>
		    		<td width="562" height="30">&nbsp;</td>
		    		<td width="19" height="30"><img src="./util/imgs/right_top_search.jpg" width="19" height="30" /></td>
		  		</tr>
		  		<tr>
		    		<td background="./util/imgs/left_line_search.jpg">&nbsp;</td>
	    		  	<td width="562" align="center" class="lineone">
		    			<span class="success">		    			
							<%=messages.getText("driver.messages.upload.telematiccontrol.message")%>								
			    		</span>
						<br>
		    			<br>
		    			<br>
						<%=messages.getText("driver.messages.upload.telematiccontrol.inserted")+" "+total%>
						<br>
						<br>
						<br>
						
					</td>
		    		<td background="./util/imgs/right_line_search.jpg">&nbsp;</td>
		  		</tr>
		  		<tr>
		    		<td width="19" height="11"><img src="./util/imgs/left_base_search.jpg" width="19" height="11" /></td>
		    		<td width="562" height="11" background="./util/imgs/center_base_search.jpg"><img src="x" width="1" height="1" /></td>
		    		<td width="19" height="11"><img src="./util/imgs/right_base_search.jpg" width="19" height="11" /></td>
			  	</tr>
			</table>
		</center>
		<br/><br/>
	</body>
</html>
