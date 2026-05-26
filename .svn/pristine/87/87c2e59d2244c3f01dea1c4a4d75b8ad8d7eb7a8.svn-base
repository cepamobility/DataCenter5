<%@page import="communication.administration.corporation.tables.ItemData"%>
<%@page import="communication.administration.user.UserSessionData"%>
<%@page import="communication.driver.tables.DriverData"%>
<%@ page language="java"
    errorPage="exception.jsp"
	import="business.*"
	import="presentation.translator.web.util.*"
	import="communication.language.*"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData = sys.getUserSessionData();
	Text messages = sys.getText();

	
%>

<html>
	<head>
		<LINK REL=StyleSheet HREF="./util/css/estilo.css" TYPE="text/css">
	</head>
	<script type="text/javascript">
	 
	</script>
 
	<body>
		<center>
			<html:form action="/UploadUpdateDriver"  enctype="multipart/form-data">
			<html:hidden property="step" value="1"/>
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
							<%=messages.getText("driver.upload.update.title")%>								
			    		</span>
						<br>
		    			<br>
						<table width="600" border="0" cellpadding="0" cellspacing="0" class="guideSearch">
					  		<tr>
					    		<td width="19" height="30"><img src="./util/imgs/left_top_search.jpg" width="19" height="30" /></td>
					    		<td width="562" height="30"><%=messages.getText("driver.upload.update.selectfile")%></td>
					    		<td width="19" height="30"><img src="./util/imgs/right_top_search.jpg" width="19" height="30" /></td>
					  		</tr>
					  		<tr>
					    		<td background="./util/imgs/left_line_search.jpg">&nbsp;</td>
				    		  	<td width="562" align="center" class="lineone">
				    		  		<html:file property="file"></html:file>
				    		  	</td>
					    		<td background="./util/imgs/right_line_search.jpg">&nbsp;</td>
					  		</tr>
					  		<tr>
					    		<td width="19" height="11"><img src="./util/imgs/left_base_search.jpg" width="19" height="11" /></td>
					    		<td width="562" height="11" background="./util/imgs/center_base_search.jpg"><img src="x" width="1" height="1" /></td>
					    		<td width="19" height="11"><img src="./util/imgs/right_base_search.jpg" width="19" height="11" /></td>
						  	</tr>
						</table>
						<html:submit value='<%=messages.getText("generic.messages.accept")%>' />
					</td>
		    		<td background="./util/imgs/right_line_search.jpg">&nbsp;</td>
		  		</tr>
		  		<tr>
		    		<td width="19" height="11"><img src="./util/imgs/left_base_search.jpg" width="19" height="11" /></td>
		    		<td width="562" height="11" background="./util/imgs/center_base_search.jpg"><img src="x" width="1" height="1" /></td>
		    		<td width="19" height="11"><img src="./util/imgs/right_base_search.jpg" width="19" height="11" /></td>
			  	</tr>
			</table>
			</html:form>
		</center>
		<br/><br/>
	</body>
</html>
