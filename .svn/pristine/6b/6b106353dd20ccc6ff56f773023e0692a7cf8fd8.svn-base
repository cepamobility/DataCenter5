<%@page language="java" 
	errorPage="../exception.jsp"
	import="business.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.administration.user.tables.*"
	import="communication.security.*"
	import="presentation.translator.web.user.*"
	import="presentation.translator.web.util.*"
	import="java.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>
<%! RequestDispatcher dispatcher; String pathMsg;
	Text messages; String pathLanguage;
	String uid;
	SystemAccess sys;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.CHANGE_PASSWORD_USER);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		pathLanguage="/util/jsp/language.jsp";
	
		uid=(String)request.getParameter("uid");
		if(uid==null){
			uid=(String)session.getAttribute("uid");
		}
		session.setAttribute("url","user/resetPassword.jsp?uid="+uid);

		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
		pathMsg="/util/jsp/message.jsp";
	
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);
	
		messages=sys.getText();
		
		if(request.getParameter("target")!=null){
			session.setAttribute("target",request.getParameter("target"));
		}
		
		if(session.getAttribute("target")==null){
			session.setAttribute("target",request.getParameter("target"));
		}
				
		if(uid!=null){
			session.setAttribute("uid",uid); 
%>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

	<head/>
	<body>
	    <html:form  action="/UserresetPassword" >
			<table width="450" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("web.user.resetpassword.resetpasswordforuser")+" "+uid%>' />
	  			</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
	  			<tr>
	    			<td width="100%" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("web.user.resetpassword.resetpassword")%>' />
    					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
		                 	<tr class="linetwo">
   								<th align="right">
   									<div align="left"><%=messages.getText("generic.user.userdata.pass")%> </div>
   								</th>
   			  	        		<td align="left" class="linetwo">
     					     		<html:password property="password"	size="25" styleClass="input"/>
   		 		        		</td>
	    					</tr>
					      	<tr  class="space">
					        	<th align="right">&nbsp;</th>
					        	<td align="right">&nbsp;</td>
					      	</tr>
	    					<tr>
	 				       		<th align="right" class="linetwo"><div align="left"><%=messages.getText("generic.messages.confirm")%></div></th>
					        	<td align="left" class="linetwo">
					             	<html:password property="rePassword" size="25" styleClass="input"/>
				            	</td>
	    					</tr>
					      	<tr  class="space">
					        	<th align="right">.</th>
					        	<td align="right">.</td>
					      	</tr>
					     	<tr>
					       		<td colspan="2" class="linetwo"> 
					       			<div align="right">
								     	<html:submit styleClass="input" >
									         <%=messages.getText("generic.messages.save")%>
							         	</html:submit>
						     			<html:cancel styleClass="input" >
								        	<%=messages.getText("generic.messages.cancel")%>
					         			</html:cancel>
							 		</div>
								</td>
			          		</tr>
	    				</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
			 		</td>
                </tr>
			</table>
		</html:form>
	</body>
<%
		}
	}
%>
</html:html>
