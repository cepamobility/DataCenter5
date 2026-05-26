<%@page language="java" 
	errorPage="../exception.jsp"
	import="business.*"
	import="business.administration.user.*"
	import="business.administration.corporation.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.administration.corporation.tables.*"
	import="presentation.translator.web.user.*"
	import="presentation.translator.web.util.*"
	import="java.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%! RequestDispatcher dispatcher; String pathMsg;String pathLanguage; Text messages;
	UserData data; UserReportFacade userReport;
	CorporationReportFacade corporationReport;
    SystemAccess sys; String uid; String target;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.EDIT_CORPORATION);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		pathMsg="/util/jsp/message.jsp";

		session.setAttribute("url","user/ReloadText.jsp");

		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);
	
		dispatcher=request.getRequestDispatcher("/language/findalllanguage.jsp");
		dispatcher.include(request,response);	

		
		if(session.getAttribute("target")==null){
			target=(String)request.getParameter("target"); 
			session.setAttribute("target",target);		
		}
		
		sys.reloadText();
//		dispatcher=request.getRequestDispatcher(target);
//		System.out.println("TARGET:" + target);
//		dispatcher.forward(request,response);

		Text messages = sys.getText();
		
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
						<%=messages.getText("generic.messages.correctreloadtext")%>						
			    		</span>
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
	</body>
<%
		}	
	
%>
</html>
