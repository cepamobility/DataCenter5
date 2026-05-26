<%@page language="java" 
	errorPage="../exception.jsp"
	import="business.*"
	import="business.administration.user.*" 
	import="business.administration.corporation.*" 
	import="communication.language.*"
	import="communication.administration.*"
	import="communication.administration.user.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.administration.user.*"
	import="communication.security.*"
	import="presentation.translator.web.user.*"
	import="presentation.translator.web.util.*"
	import="java.util.*"
	import="org.apache.struts.action.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>	   
	
<%! RequestDispatcher dispatcher; String pathMsg;
	Text messages; String pathLanguage;
%>
<%
	SystemAccess sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.CHANGE_PERMISSIONS);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		pathLanguage="/util/jsp/language.jsp";
	
		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
	
		pathMsg="/util/jsp/message.jsp";
	
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);
	
		session.removeAttribute("target");

		messages=sys.getText();

		String userName = request.getParameter("user");
		if(Util.empty(userName)) {
		    userName = (String)session.getAttribute("user");
		}
		session.setAttribute("url","user/permissions.jsp?user="+userName);
		
		if(userName.equals(sys.getUserSessionData().getFilter().getLoginName())) {
			response.sendRedirect(request.getContextPath()+"/home.jsp");
		}else {
		    RoleData role = sys.getUserReportFacade().findUserRole(userName,sys.getUserSessionData());
			RoleForm roleForm = new RoleForm(userName,role.getName());
			Collection userPermissions = sys.getUserReportFacade().findUserPermissions(userName,sys.getUserSessionData());
			session.setAttribute("UserModifyRoleForm",roleForm);

			Collection notAvailable = new LinkedList();
			notAvailable.add("resetpassword");
			notAvailable.add("recomendation");
			notAvailable.add("fleetdriver");
			notAvailable.add("cds");
			
			Collection roleList = new LinkedList();
			Iterator roles = sys.getRoleReportFacade().findAll(sys.getUserSessionData()).iterator();
			while(roles.hasNext()) {
				RoleData roleData = (RoleData)roles.next();	
				if(!notAvailable.contains(roleData.getName())) {
				    if(sys.getCorporationId() == CorporationData.CENAPRA || sys.getCorporationId() == CorporationData.IMESEVI_UY) {
						if(role.getName().equals("administrator") || role.getName().equals("imeseviview") || role.getName().equals("imeseviupdate")) {
							roleList.add(new OptionItem(messages.getText("role."+roleData.getName()+".name"),roleData.getName()));
						}
				    }else {
						if(roleData.getName().equals("accesscontrol")) {
							if(sys.getCorporationId() == CorporationData.LOSPIQUES) {
								roleList.add(new OptionItem(messages.getText("role."+roleData.getName()+".name"),roleData.getName()));
							}
						}else {
							roleList.add(new OptionItem(messages.getText("role."+roleData.getName()+".name"),roleData.getName()));
						}
				    }
				}
				Collections.sort((List)roleList);
				session.setAttribute("roleList",roleList);
			}
%>
	<LINK REL=StyleSheet HREF="./util/css/estilo.css" TYPE="text/css">
	<head/>
	<script>
		function modifyPermissions(label){
			var elements=document.forms[label].elements;
			var length=elements.length;
			var i;
			var list="";
			var separator="<";
		
			for(i=0;i<length;i++){
				var element=elements[i];
				if(element.type.toLowerCase()=="checkbox"){
					if(element.checked){
						if(list==""){
							list=element.value;
						}else{
							list=element.value+"<"+list;
						}	
					}	
				}	
			} 		
			document.forms[5].elements[label].value=list;
			if(list.length>0){
				document.forms[5].submit();
			}
		}
	</script>
	<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr class="guide1">
			<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.messages.modify.permissions")%>' />
		</tr>
     	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
 		</tr>
      	<tr>
        	<td align="left" >
				<table width='292' cellpadding="0" cellspacing="0">
					<tr class="linetwo">
			        	<th align="left" > <%=messages.getText("generic.user.userdata.role")%></th>
						<td>
							<div align="right">
								<html:form action="/Userroles">
									<html:hidden property="oldrole"/>
									<html:select property="newrole" styleClass="input">
										<html:options collection="roleList" property="value" labelProperty="label"/>
									</html:select>
									<html:submit value='<%=messages.getText("generic.messages.update") %>' styleClass="input"/>
								</html:form>	
        			  		</div>
						</td>	
					</tr>
				</table>
      		</td>
		</tr>
     	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
 		</tr>
 		<tr>
   			<td width="292" valign="top">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("user.permission.added")%>' />
				<form name="remove">
					<table align="center" width="100%">	
<%					Collections.sort((List)userPermissions);
					Iterator it = userPermissions.iterator();
					boolean elements = false;
					String module = "";
					Collection perms = new LinkedList();
	    			while(it.hasNext()) {
	        			PermissionData permission = (PermissionData)it.next();
	        			if(!permission.getModule().equals(module)) {
	        			    module = permission.getModule();
	        			    if(!elements) {
	    						elements = true;
	        			    }else {	
%>        
									</table>
								<jsp:include  page="../component/utilitybox3bottom.jsp" />
							</td>
						</tr>
				      	<tr>
							<jsp:include  page="../component/spaceline.jsp" />
		  				</tr>
<%							}
%>						<tr>
	    					<td width="100%" valign="top" colspan="5">
	    					<%
	    					String page1 = "../component/utilitybox3top.jsp?title="+messages.getText("module."+permission.getModule()+".name");
	    					%>
	    						<jsp:include  page='<%=page1%>' />
	    							<table>
<%	        			}    
%>										<tr align='left' class="texts">
											<td>
												<input type='checkbox' name='<%=permission.getName()%>' value='<%=permission.getName()%>' >
											</td>
											<td><%=messages.getText("permission."+permission.getName()+".name")%></td>
										</tr>
<%						perms.add(permission.getName());
					}	
					if(!elements) {					
%>						<tr align='left' class="linetwo">
							<td colspan="2"><%=messages.getText("user.permission.notadded")%></td>
						</tr>
<%				    }else {			
%>									</table>
								<jsp:include  page="../component/utilitybox3bottom.jsp" />
							</td>
						</tr>
				      	<tr>
							<jsp:include  page="../component/spaceline.jsp" />
		  				</tr>
						<tr>	
							<td colspan="2" align="right">
								<input name="button" type="button" value='<%=messages.getText("user.permission.removepermission")%>' onClick="javascript:modifyPermissions('remove')" class="input"/>
							</td>
						</tr>			
<%					}     
%>					
					</table>
				</form>
				<jsp:include page="../component/utilityboxbottom.jsp" />
			</td>
        	<td width="16">&nbsp;</td>
		    <td width="292" valign="top"><table align='left' cellpadding="0" cellspacing="0">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("user.permission.notadded")%>' />
				<form name="add">
					<table align="center" width="100%">	
<%					Collection permissions = sys.getUserSessionData().getPermissionsData();
					Collections.sort((List)permissions);
					it = permissions.iterator();
					elements = false;
					module = "";
	    			while(it.hasNext()) {
	        			PermissionData permission = (PermissionData)it.next();
						if(!perms.contains(permission.getName())) {
		        			if(!permission.getModule().equals(module)) {
		        			    module = permission.getModule();
	    	    			    if(!elements) {
	    							elements = true;
	        				    }else {	
%>        							</table>
								<jsp:include  page="../component/utilitybox3bottom.jsp" />
							</td>
						</tr>
				      	<tr>
							<jsp:include  page="../component/spaceline.jsp" />
		  				</tr>
<%								}
%>						<tr>
	    					<td width="100%" valign="top" colspan="5">
	    						<jsp:include  page='<%="../component/utilitybox3top.jsp?title="+messages.getText("module."+permission.getModule()+".name")%>' />
	    							<table>
<%	        				}    
%>										<tr align='left' class="texts">
											<td align='left'>
												<input type='checkbox' name='<%=permission.getName()%>' value='<%=permission.getName()%>' >
											</td>
											<td><%=messages.getText("permission."+permission.getName()+".name")%></td>
										</tr>
<%						}
					}	
					if(!elements) {					
%>						<tr align='left' class="linetwo">
							<td><%=messages.getText("user.permission.notadded")%></td>
						</tr>
<%				    }else {			
%>									</table>
								<jsp:include  page="../component/utilitybox3bottom.jsp" />
							</td>
						</tr>
				      	<tr>
							<jsp:include  page="../component/spaceline.jsp" />
		  				</tr>
						<tr>	
							<td colspan="2" align="right">
								<input name="button" type="button" value='<%=messages.getText("user.permission.addpermission")%>' onClick="javascript:modifyPermissions('add')" class="input"/>
							</td>
						</tr>			
<%					}     
%>             		</table>
				</form> 
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>
     	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
 		</tr>
		<tr>
			<td colspan="3" class="linetwo" align="right">
				<html:form action="/Userpermissions">
					<html:hidden property="user" value='<%=userName%>'/>
					<html:hidden property="add"/>
					<html:hidden property="remove"/>
					<html:cancel styleClass="input" value='<%=messages.getText("generic.messages.end")%>'/>
				</html:form>
			</td>
		</tr>
	</table>	
<%		}
	}	%>
</html:html>

