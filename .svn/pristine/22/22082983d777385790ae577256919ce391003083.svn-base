<%@ page language="java"
	isErrorPage="true"
	import="business.*"
	import="communication.language.*"
	import="communication.exception.*"
	import="presentation.translator.web.util.*"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%!
	Text messages;
	SystemAccess sys;
	String msg;	String key;
	Throwable systemException;
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	if(sys != null) {
		messages = sys.getText();

		systemException = (request.getAttribute("exception") != null) ? 
				(Throwable)request.getAttribute("exception") : null;
				
		if(systemException != null) {
			if((systemException.getCause()!=null && systemException.getCause().toString().indexOf("SecurityException") > 0) ||
 	           (systemException.getClass()!=null && systemException.getClass().getName().toString().indexOf("SecurityException") > 0)) {
				response.sendRedirect(request.getContextPath()+"/insufficientpermissions.jsp");
			}
		}
		
		systemException.printStackTrace();
		/*
		else {
			
		    if(exception!=null) {
				if((exception.getCause()!=null && exception.getCause().toString().indexOf("SecurityException") > 0) ||
				   (exception.getClass()!=null && exception.getClass().getName().toString().indexOf("SecurityException")>0)) {
					response.sendRedirect(request.getContextPath()+"/insufficientpermissions.jsp");
				}
			}
		}
		
		Throwable thw = exception;
		if(exception != null) {
		    while(thw.getCause() != null) {
			    thw = exception.getCause();
		    }
		}
		
		if(systemException == null && thw instanceof Exception) {
		    systemException = (Exception)thw;
		}
		*/
		if(systemException != null) {		
			session.setAttribute("exception",systemException);
		}
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

	<body onload="ui.loading();">

    				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" valign="top">
      					<tr>
        					<td>
								<html:form method="post" action="/SendErrorReport" >
									<table width="600"  border="0" align="center" cellpadding="0" cellspacing="0">
		  								<tr>
		    								<td width="19" height="30"><img src="./util/imgs/left_top_search.jpg" width="19" height="30" /></td>
		    								<td width="562" height="30" class="guideSearch">&nbsp;</td>
		    								<td width="19" height="30"><img src="./util/imgs/right_top_search.jpg" width="19" height="30" /></td>
		  								</tr>
		  								<tr>
			    							<td background="./util/imgs/left_line_search.jpg">&nbsp;</td>
		   		  							<td width="562" align="center" class="success">
		   		  								<table width="100%">		    			
  													<tr>
    													<th width="100%"><%=messages.getText("generic.exception.message1")%></th>
    												</tr>
    												<tr>	
    													<td width="100%" class="lineone">&nbsp;</td>
    												</tr>
    												<tr height="70px">
    													<th width="100%" class="lineone" align="left">
    														<%=messages.getText("generic.exception.message2")%>
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
	</body>
</html>
<%
	}
%>