<%@ page language="java"
	isErrorPage="true"
	import="business.*"
	import="communication.language.*"
	import="communication.exception.*"
	import=" presentation.translator.web.util.*"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%!
	Text messages;
	SystemAccess sys;
	String msg;	String key;
	Exception systemException;
%>

<html>
	<head>
		<title> CEPA </title>
		<link href="./util/css/estilo.css" rel="stylesheet" type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<style type="text/css">
		</style>
	</head>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	if(sys != null) {
		messages = sys.getText();

%>


	<script type="text/javascript" src='<%="./util/js/uiframework.js"%>'>
	</script>

	<body onload="ui.loading();">

		<table width="100%" border="0" cellspacing="0" cellpadding="0">
  			<tr>
    			<td valign="top">
    				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" valign="top">
      					<tr>
        					<td>
								<html:form method="post" action="/ReportError" >
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
  													<tr class="success">
    													<th width="100%"><%=messages.getText("generic.reporterror.message1")%></th>
    												</tr>
    												<tr>	
    													<td width="100%" class="lineone">&nbsp;</td>
    												</tr>
    												<tr height="70px">
    													<th width="100%" class="lineone" align="left">
    														<%=messages.getText("generic.reporterror.message2")%>
    													</th>
    												</tr>
    												<tr>	
    													<td width="100%" align="left" class="lineone">
   															<html:textarea property="body" cols="120" rows="3" styleClass="input"> </html:textarea>
    													</td>
    												</tr>
    												<tr>	
    													<td width="100%" align="right" class="lineone">
   															<html:submit  value='<%=messages.getText("generic.exception.send")%>' styleClass="input"/>
   															<html:cancel  value='<%=messages.getText("generic.messages.cancel")%>'  styleClass="input"/>
    													</td>
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
    							</html:form>	
        					</td>
      					</tr>
    				</table>
    			</td>
  			</tr>
		</table>
	</body>
<%
	}
%>
</html>
