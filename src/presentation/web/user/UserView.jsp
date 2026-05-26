<%@page language="java"
	errorPage="../exception.jsp"
	import="business.*"
	import="business.administration.user.*" 
	import="business.administration.corporation.*" 
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.administration.user.*"
	import="communication.administration.corporation.tables.*"
	import="communication.administration.group.tables.*"
	import="presentation.translator.web.user.*"
	import="presentation.translator.web.util.*"
	import="java.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>
<%! RequestDispatcher dispatcher; String context;String pathView; 
	String pathMsg; String pathSearch; String uid;
	Text messages; String pathLanguage;
	CorporationReportFacade corporationReport;
	SystemAccess sys;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.EDIT_USER);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		uid=(String)request.getParameter("uid");

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		if(uid==null){
			uid=(String)session.getAttribute("uid");
			session.removeAttribute("uid");
		}
	
		pathLanguage="/util/jsp/language.jsp";
		session.setAttribute("url","user/UserView.jsp?uid="+uid);

		if(uid!=null){	
			pathView="./view.jsp?uid="+uid;
			session.setAttribute("target","UserView");
	
			messages=sys.getText();

			boolean linetwo = false;
			Collection groups = sys.getUserReportFacade().findGroupAttributes(uid,sys.getUserSessionData());	
			Iterator it = groups.iterator();
			String groupIds = "";
			while(it.hasNext()) {
				GroupMainAttributes group = (GroupMainAttributes)it.next();
				if(!Util.empty(groupIds)) {
					groupIds += ",";
				}
				groupIds += String.valueOf(group.getId());
			}
%>

<LINK REL=StyleSheet HREF="./util/css/estilo.css" TYPE="text/css">

<script language="javascript" src='./util/js/Prepared_Parameters.js' type='text/javascript' >
</script>
<script>
	
	function doSubmitConfirm(form,id,name,msg) {
		form.elements[name].value=id;
		if(confirm(msg)){
			form.submit();
		}
	}
	
	function doSubmit(form,id,name) {
		form.elements[name].value=id;
		form.submit();
	}

	function addAllCountry(form) {
		form.submit();
	}
	
	function deleteItem(form,formCheck,id,msg,labelId){

		var elements=formCheck.elements;
		var length=elements.length;
		var i;
		var valueCheckboxParamList="";
		var separator="<";
		
		for(i=0;i<length;i++){
			var element=elements[i];
			if(element.type.toLowerCase()=="checkbox"){
				
				if(element.checked){
					if(valueCheckboxParamList==""){
						valueCheckboxParamList=element.value;
					}
					else{
						valueCheckboxParamList=element.value+"<"+valueCheckboxParamList;
					}	
				}	
			} 
		
		}
		form.elements[labelId].value=valueCheckboxParamList;
		if(id != -1) {
			form.elements['id'].value=id;
		}
		form.elements['level'].value="1";
		if(valueCheckboxParamList.length>0){
			if(confirm(msg)){
				form.submit();
			}
		}
	}
	function deleteIFConfirm(form,msg){
		var elements=form.elements;
		var length=elements.length;
		var i;
		var valueCheckboxParamList="";
		var separator="<";
			
		for(i=0;i<length;i++){
			var element=elements[i];
			if(element.type.toLowerCase()=="checkbox"){
				if(element.checked){
					if(valueCheckboxParamList==""){
						valueCheckboxParamList=element.value;
					}else{
						valueCheckboxParamList=element.value+"<"+valueCheckboxParamList;
					}	
				}	
			} 
		}
		form.list.value=valueCheckboxParamList;
		if(valueCheckboxParamList.length>0){
			if(confirm(msg)){
				form.submit();
			}
		}
	}

</script>

<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style><head/>

<body bgcolor="#FFFFFF">
	
	<html:form action="/Userdelete" >
		<input type="checkbox" name='<%=uid%>' value='<%=uid%>' style="display:none" checked/>
		<html:hidden property="checkboxParamList" />		
	</html:form>
	
	<form action="layout.jsp?refer=/user/UserEdit.jsp" name='edit' method="post">
		<input type="hidden" name="uid" value='<%=uid%>' />
		<input type="hidden" name="target" value="UserView" />
	</form>	

	<form action="layout.jsp?refer=/user/resetPassword.jsp" name='resetPassword' method="post">
		<input type="hidden" name="uid" value='<%=uid%>' />
		<input type="hidden" name="target" value="UserView" />
	</form>	
	
	<form action="layout.jsp?refer=/user/DivOrgEdit.jsp" name='divorgedit' method="post">
		<input type="hidden" name="uid" value='<%=uid%>' />
		<input type="hidden" name="target" value="UserView" />
	</form>	
<!-- Add corporation Pablo -->
	<form action="layout.jsp?refer=/user/UserAddCorporations.jsp" name='userAddCorporations' method="post">
		<input type="hidden" name="uid" value='<%=uid%>' />
		<input type="hidden" name="target" value="UserView" />
	</form>
		
	<body>
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1">
				<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.data.user")+" "+uid%>' />
  			</tr>
	      	<tr>
				<jsp:include  page="../component/spaceline.jsp" />
   			</tr>
			<tr>
				<td width="100%" valign="top" colspan="3">
					<jsp:include  page='<%="../component/utilitybox3top.jsp?title="+messages.getText("generic.messages.options")%>' />
						<table width="100%">
							<tr>
								<td width="50%">
									<table width="100%">
<%			if(sys.getUserSessionData().checkPermission(SecurityConstants.MODIFY_USER)) {	%>
										<tr>
			          						<td width="3%" class="texts1">
			          							<img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">
			          						</td>
											<td align="left"  class="texts">
												<a href="javascript:document.forms[3].submit();">
													<%=messages.getText("generic.messages.modify")%>
												</a>
											</td>
										</tr>
<%			}	
			if(sys.getUserSessionData().checkPermission(SecurityConstants.CHANGE_PASSWORD_USER)) {	
%>										<tr>
			          						<td width="3%" class="texts1">
			          							<img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">
			          						</td>
								      		<td align="left" class="texts">
												<a href="javascript:document.forms[4].submit();">
													<%=messages.getText("web.user.resetpassword.resetpassword")%>
												</a>
											</td>
										</tr>
<%			}	
			if(sys.getUserSessionData().checkPermission(SecurityConstants.CHANGE_PERMISSIONS) && !uid.equals(sys.getUserSessionData().getFilter().getLoginName())) {	%>
										<tr>
			          						<td width="3%" class="texts1">
			          							<img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">
			          						</td>
									      	<td align="left" class="texts">
												<a href='<%="layout.jsp?refer=/user/permissions.jsp?user="+uid%>' >
													<%=messages.getText("web.user.changepermissions")%>										
												</a>
											</td>
										</tr>
<%			}	
%>									</table>
								</td>
								<td width="50%">
									<table width="100%">
<%			if(sys.getUserSessionData().checkPermission(SecurityConstants.DELETE_USER)) {	%>
										<tr>
			          						<td width="3%" class="texts1">
			          							<img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">
			          						</td>
									      	<td align="left" class="texts">
												<a href="javascript:doSubmitConfirm(document.forms[2],'<%=uid%>','checkboxParamList','<%=messages.getText("generic.user.userdata.confirmdelete")%>');">
													<%=messages.getText("generic.messages.delete")%>										
												</a>
											</td>
										</tr>
<%			}	
%>										<tr>
			          						<td width="3%" class="texts1">
			          							<img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">
			          						</td>
									      	<td align="left" class="texts">
			          							<a href='<%="layout.jsp?refer=/groups/groupSearch.jsp?id="+uid+"&action=UserAddGroup&groups="+groupIds%>'> 
			            							<%=messages.getText("group.add.group")%> 
			            						</a> 
											</td>
										</tr>
										<tr>
			          						<td width="3%" class="texts1">
			          							<img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">
			          						</td>
									      	<td align="left" class="texts">
			          							<a href='<%="layout.jsp?refer=/user/UserNEDList.jsp"%>'> 
			            							<%=messages.getText("generic.messages.cancel")%> 
			            						</a> 
											</td>
										</tr>
<% 			if (sys.getUserSessionData().isAdministrator() || sys.getUserSessionData().isAccountManager()){
%>										<tr>	<!-- Agregar usuario a corporation -->
			          						<td width="3%" class="texts1">
			          							<img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">
			          						</td>
									      	<td align="left" class="texts">												
			          							 <a href='<%="layout.jsp?refer=/user/UserAddCorporations.jsp?uid=" + uid %>'>
			            							<%=messages.getText("generic.user.userdata.userAddCorporation")%> 
			            						</a> 
											</td>
										</tr>
<%			}
%>										
									</table>
								</td>
							</tr>
						</table>
					<jsp:include  page="../component/utilitybox3bottom.jsp" />
				</td>
			</tr>
      		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
  			<tr>
    			<td width="100%" valign="top" colspan="3">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("web.user.userview.systemuserdata")%>' />
					<table width="100%">
						<tr>
            				<td valign="middle"><jsp:include page='<%=pathView%>' /></td> 
						</tr>
					</table>
					<jsp:include  page="../component/utilityboxbottom.jsp" />
				</td>
          	</tr>
      		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
  			<tr>
    			<td width="100%" valign="top" colspan="3">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("web.user.groups")%>' />
    				<html:form action="/UserDeleteGroup"  >
						<html:hidden property="user" value='<%=uid%>' />
						<html:hidden property="list" />
	    				<table width="100%" border="0">
   		  					<tr align="center">
    	  						<td width='5%' class="guide2"></td>
				                <td width='24%' class="guide2"><%=messages.getText("group.data.name")%></td>
				                <td width='48%'	class="guide2"><%=messages.getText("group.data.description")%></td>
		    		            <td width='24%'	class="guide2"><%=messages.getText("group.data.type")%></td>
   							</tr>
<%			if(groups.size() == 0) {
%>             				<tr>
           	  					<td colspan="4" class="linetwo">
          							<%=messages.getText("generic.messages.notresult")%>
           	  					</td>
           	  				</tr>
<%			}else {
				it = groups.iterator();
				linetwo = true;
				while(it.hasNext()) {
					GroupMainAttributes group = (GroupMainAttributes)it.next();
%>	            			<tr align=center align="right" class='<%=(linetwo) ? "linetwo" : "lineone"%>'>
	   	        				<td width='5%' align="center"><input type='checkbox' name='<%=String.valueOf(group.getId())%>' value='<%=String.valueOf(group.getId())%>' ></td>
			        	        <td width='24%' align="right">
<%					if(Util.checkPermission(sys,SecurityConstants.EDIT_GROUP) == null) { 
%>									<a href='layout.jsp?refer=/groups/groupView.jsp?id=<%=group.getId()%>'>
										<%=group.getName()%> 
									</a>
<%					}else { %>			        	        
			        	        	<%=group.getName()%>
<%					} %>			        	        
			        	        </td>
    							<td width='48%' align="right">
        							 <%=group.getDescription()%> 
        						</td>
				                <td width='24%' align="right">
				                	<%=(group.getType().equals(GroupMainAttributes.INDIVIDUAL)) ? messages.getText("group.type.individual") : messages.getText("group.type.composed")%>
								</td>
   	      					</tr>
<%
					linetwo = !linetwo;
				}
%>							<tr>
								<td class="linetwo" align="right" colspan="4">
									<input name="button2" type="button" onClick="javascript:deleteIFConfirm(document.forms['UserDeleteGroupForm'],'<%=messages.getText("group.user.confirmdelete")%>');" value='<%=messages.getText("generic.messages.delete")%>' class="input"/>
								</td>
							</tr>
<%			}
%>						</table>
					</html:form>       							
					<jsp:include  page="../component/utilityboxbottom.jsp" />
				</td>
          	</tr>
      		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
        </table>
	</body>
<%		}else{
			dispatcher=request.getRequestDispatcher("layout.jsp?refer=/user/UserNEDList.jsp");
			dispatcher.forward(request,response);
		}	
	}
%>
</html:html>

