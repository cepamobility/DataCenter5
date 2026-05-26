<%@ page language="java"
    errorPage="exception.jsp"
	import="business.*"
	import="communication.language.*"
	
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%
	SystemAccess sys=(SystemAccess)session.getAttribute("SystemAccess");
	Text messages = sys.getText();

%>

<html>
	<head>
		<title> CEPA </title>
		<link href="./util/css/estilo.css" rel="stylesheet" type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<style type="text/css">
		</style>
	</head>

	<script type="text/javascript" src='<%="./util/js/uiframework.js"%>'>
	</script>

	<body >
		<center>
		<table>
			<tr height="100"><td>&nbsp;</td></tr>
  			<tr>
    			<td valign="top">
    				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" valign="top">
      					<tr>
        					<td>
								<table width="600"  border="0" align="center" cellpadding="0" cellspacing="0">
	  								<tr>
	    								<td width="19" height="30"><img src="./util/imgs/left_top_search.jpg" width="19" height="30" /></td>
	    								<td width="562" height="30" class="guideSearch">&nbsp;</td>
	    								<td width="19" height="30"><img src="./util/imgs/right_top_search.jpg" width="19" height="30" /></td>
	  								</tr>
	  								<tr>
		    							<td background="./util/imgs/left_line_search.jpg">&nbsp;</td>
	   		  							<td width="562" align="center"  class="lineone">
	   		  								<table width="100%">		    			
												<tr>
   													<th width="100%" class="success"><%=messages.getText("generic.exception.thanks")%></th>
    											</tr>
    											<tr>	
    												<td width="100%" class="lineone">&nbsp;</td>
    											</tr>
   											</table>
										</td>
	    								<td background="./util/imgs/right_line_search.jpg">&nbsp;</td>
	  								</tr>
	  								<tr>
	    								<td width="19" height="11"><img src="./util/imgs/left_base_search.jpg" width="19" height="11" /></td>
	    								<td width="562" height="11" background="./util/imgs/center_base_search.jpg"><img src="x" width="1" height="1" /></td>
	    								<td width="19" height="11"><img src="./util/imgs/right_base_search.jpg" width="19" height="11" /></td>
		  							</tr>
   								</table>
        					</td>
      					</tr>
    				</table>
    			</td>
  			</tr>
		</table>
		</center>
	</body>
</html>
