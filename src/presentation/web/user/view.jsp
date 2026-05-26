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

<html:html>	   

<%! RequestDispatcher dispatcher; String pathMsg;
	Text messages; String pathLanguage;
	UserAttributes data; String uid;
	SystemAccess sys;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.EDIT_USER);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		pathLanguage="/util/jsp/language.jsp";
	
		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
	
		pathMsg="/util/jsp/message.jsp";
	
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);

		uid=(String)request.getParameter("uid");
		if(uid!=null){
		
			messages=sys.getText();
			data=sys.getUserReportFacade().findUserAttributes(uid,sys.getUserSessionData());
			if(data!=null){
				CreateForm createForm=new CreateForm(data);				
				session.setAttribute("UserCreateForm",createForm);

%>
<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">


<head/>
	   
				<html:form  action="/Userupdate" >
					<table width='100%' border="0" align='left' cellpadding="0" cellspacing="0">
						<tr class="linetwo">
		   					<th align="right">
								<div align="left"><%=messages.getText("generic.user.userdata.nickname")%></div>
							</th>
		    			  	<td align="left">
		     					<div align="right"><%=createForm.getLoginname()%></div>
		    		 		</td>
		  	         	</tr>
		 				<tr class="linetwo">
							<th align="right">
								<div align="left"><%=messages.getText("generic.user.userdata.firstname")%></div>
							</th>
		    			  	<td align="left">
		     					<div align="right"><%=createForm.getFirstName()%></div>
		    		 		</td>
						</tr>
						<tr class="linetwo">
							<th align="right">
								<div align="left"><%=messages.getText("generic.user.userdata.lastname")%></div>
							</th>
		    			  	<td align="left">
		     					<div align="right"><%=createForm.getLastName()%></div>
		    		 		</td>
						</tr>
						<tr class="linetwo">
						    <th align="right">
								<div align="left"><%=messages.getText("generic.user.userdata.mail")%></div>
							</th>
							<td align="left">
		     					<div align="right"><%=createForm.getEmail()%></div>
		    		 		</td>
		 				</tr>
					    <tr class="linetwo">
						    <th align="right">
								<div align="left"><%=messages.getText("generic.user.userdata.language")%></div>
							</th>
		    			  	<td align="left">
		     					<div align="right"><%=messages.getText(createForm.getLanguage())%></div>
		    		 		</td>
					  </tr>
					  <tr class="linetwo">
							<th align="right">
								<div align="left"><%=messages.getText("generic.user.userdata.role")%></div>
							</th>
		    			  	<td align="left">
		     					<div align="right"><%=messages.getText("role."+createForm.getRole()+".name")%></div>
		    		 		</td>
					  	</tr>
					</table>
				</html:form>
<%
			}
		}		
	}
%>

</html:html>