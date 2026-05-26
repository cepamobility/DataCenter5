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
<%! RequestDispatcher dispatcher; String pathMsg;String pathLanguage; Text messages;
	UserAttributes data; UserReportFacade userReport;
	CorporationReportFacade corporationReport;
    SystemAccess sys; String uid; String target;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.MODIFY_USER);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		pathMsg="/util/jsp/message.jsp";

		uid=(String)request.getParameter("uid");
		if(uid==null){
			uid=(String)session.getAttribute("uid");
			session.removeAttribute("uid");
		}
		session.setAttribute("url","user/UserEdit.jsp?uid="+uid);

		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);
	
		dispatcher=request.getRequestDispatcher("/language/findalllanguage.jsp");
		dispatcher.include(request,response);	

		

		if(session.getAttribute("target")==null){
			target=(String)request.getParameter("target"); 
			session.setAttribute("target",target);		
		}
		
		if(uid!=null){
			messages=sys.getText();
			data=sys.getUserReportFacade().findUserAttributes(uid,sys.getUserSessionData());
			if(data!=null){
				CreateForm createForm=new CreateForm(data);
				session.setAttribute("UserCreateForm",createForm);
%>
	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

	<head/>
	<body>
		<html:form  action="/Userupdate" >
			<table width="450" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("web.user.useredit.edituser")%>' />
	  			</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
	  			<tr>
	    			<td width="100%" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("web.user.userview.systemuserdata")%>' />
    					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
		                 	<tr class="linetwo">
		   						<th width="329" height="20" align="right">
		   					 		<div align="left"><%=messages.getText("generic.user.userdata.nickname")%><span class="required">*</span></div>
		   						</th>
	    			  	    	<td width="146" align="right">
   					          		<div align="right">
   					          			<html:text property="loginname"	size="25" readonly="true" styleClass="input"/>
   					          		</div>
   					        	</td>
		                 	</tr>
					      	<tr  class="space">
					        	<th align="right">&nbsp;</th>
					        	<td align="right">&nbsp;</td>
					      	</tr>
		 				 	<tr class="linetwo">
		 				   		<th align="right">
		 				   			<div align="left"><%=messages.getText("generic.user.userdata.firstname")%><span class="required">*</span></div>
		 				   		</th>
							    <td align="right">
						        	<div align="right">
						        		<html:text property="firstName" size="25" styleClass="input"/>
						        	</div>
						        </td>
					        </tr>
					      	<tr  class="space">
					        	<th align="right">&nbsp;</th>
					        	<td align="right">&nbsp;</td>
					      	</tr>
							<tr class="linetwo">
						  		<th align="right">
						  			<div align="left"><%=messages.getText("generic.user.userdata.lastname")%><span class="required">*</span></div>
						  		</th>
		 				   		<td align="right" class="linetwo">
						      		<div align="right">
						      			<html:text property="lastName" size="25" styleClass="input" />
						      		</div>
								</td>
				        	</tr>
					      	<tr  class="space">
					        	<th align="right">&nbsp;</th>
					        	<td align="right">&nbsp;</td>
					      	</tr>
							<tr class="linetwo">
						  		<th align="right">
						  			<div align="left"><%=messages.getText("generic.user.userdata.mail")%></div>
						  		</th>
								<td align="right">
				    	          	<div align="right">
				    	          		<html:text property="email" size="25" styleClass="input"/>
				    	          	</div>
								</td>
 				        	</tr>
					      	<tr  class="space">
					        	<th align="right">&nbsp;</th>
					        	<td align="right">&nbsp;</td>
					      	</tr>
					    	<tr class="linetwo">
					      		<th align="right">
					      			<div align="left"><%=messages.getText("generic.user.userdata.language")%><span class="required">*</span></div>
					      		</th>
								<td align="right">
    					      		<div align="right">
										<html:select property="language" styleClass="input" style="width:150;">
											<html:options collection="languageOptionList" property="value" labelProperty="label"/>
										</html:select>
	  		                    	</div>
	  		                	</td>
	                  		</tr>
					      	<tr  class="space">
					        	<th align="right">&nbsp;</th>
					        	<td align="right">&nbsp;</td>
					      	</tr>
		 				 	<tr class="linetwo">
		 				   		<td height="19"></td>
		 				   		<td align="right"> 
		 				   			<div align="right">
		 				   				<html:submit styleClass="input">
	 				  		 				<%=messages.getText("generic.messages.save")%>
			 				  		    </html:submit >
							 			<html:cancel styleClass="input">
							 				<%=messages.getText("generic.messages.cancel")%>
								 		</html:cancel >
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
	}
%>
</html:html>
