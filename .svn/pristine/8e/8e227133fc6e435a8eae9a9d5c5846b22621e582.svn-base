<%@page language="java"
	errorPage="../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="business.administration.group.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.group.tables.*"
	import="communication.administration.user.tables.*"
	import="communication.administration.corporation.tables.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.administration.groups.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>

<html:html>

<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.EDIT_GROUP);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		Text messages = sys.getText();
		
		Integer groupId = (Util.empty(request.getParameter("id"))) ? new Integer((String)session.getAttribute("id")) : new Integer(request.getParameter("id"));
		session.setAttribute("url","groups/groupView.jsp?id="+String.valueOf(groupId));
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
		
		GroupReportFacade groupReport = sys.getGroupReportFacade();
		GroupData groupData = groupReport.findGroup(groupId,sys.getUserSessionData());		
		if(groupData != null) {
			boolean individual = groupData.getType().equals(GroupMainAttributes.INDIVIDUAL);
			String groups = Util.getGroups(groupData);
%>
	<script language="JavaScript" type="text/javascript">
		function confirmDelete(form,msg){
			if(confirm(msg)){
				form.submit();
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
	
	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>

	<html:form action="/Groupdelete"  >
		<html:hidden property="list" value='<%=String.valueOf(groupId)%>' />
	</html:form>	
	<body bgcolor="#FFFFFF">
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("groups.create.title")%>' />
		  		</tr>
		      	<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
				<tr>
					<td width="100%" valign="top" colspan="5">
						<jsp:include  page='<%="../component/utilitybox3top.jsp?title="+messages.getText("generic.messages.options")%>' />
							<table>
								<tr>
									<td width="50%">
										<table>
											<tr valign="top">
				          						<td width="3%" class="texts1">
				          							<img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">
				          						</td>
				          						<td width="97%" align="left">
				          							<a href='layout.jsp?refer=/groups/groupData.jsp?type=update&groupId=<%=String.valueOf(groupId)%>' class="texts"> 
				            							<%=messages.getText("generic.messages.edit")%> 
				            						</a> 
				            					</td>
											</tr>
<%		if(individual) {										
%>											<tr valign="top">
				          						<td width="3%" class="texts1">
				          							<img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">
				          						</td>
				          						<td width="97%" align="left">
				          							<a href='layout.jsp?refer=/groups/groupLinesEdit.jsp?id=<%=String.valueOf(groupId)%>' class="texts"> 
				            							<%=messages.getText("group.edit.lines")%> 
				            						</a> 
				            					</td>
											</tr>
<%		}else {
%>											<tr valign="top">
				          						<td width="3%" class="texts1">
				          							<img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">
				          						</td>
				          						<td width="97%" align="left">
				          							<a href='<%="layout.jsp?refer=/groups/groupSearch.jsp?id="+String.valueOf(groupId)+"&action=AddGroup&groups="+groups%>' class="texts"> 
				            							<%=messages.getText("group.add.group")%> 
				            						</a> 
				            					</td>
											</tr>											
<%		}
%>											<tr valign="top">
				          						<td width="3%" class="texts1">
				          							<img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">
				          						</td>
				          						<td width="97%" align="left">
				          							<a href='layout.jsp?refer=/user/UserSearch.jsp?id=<%=String.valueOf(groupId)%>' class="texts"> 
				            							<%=messages.getText("group.edit.users")%> 
				            						</a> 
				            					</td>
											</tr>
										</table>
									</td>
									<td width="50%">
										<table>
											<tr valign="top">
				          						<td width="3%" class="texts1">
				          							<img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">
				          						</td>
				          						<td width="97%" align="left">
				          							<a href="javascript:confirmDelete(document.forms['GroupDeleteForm'],'<%=messages.getText("web.group.confirmdelete.one")%>');" class="texts"> 
				            							<%=messages.getText("generic.messages.delete")%> 
				            						</a> 
				            					</td>
											</tr>
											<tr valign="top">
				          						<td width="3%" class="texts1">
				          							<img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">
				          						</td>
				          						<td width="97%" align="left">
				          							<a href="layout.jsp?refer=/groups/create.jsp" class="texts"> 
				            							<%=messages.getText("group.message.activate")%> 
				            						</a> 
				            					</td>
											</tr>
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
		    		<td valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.group.data")%>' />
						<table width="100%">
					      	<tr  class="linetwo">
					        	<th align="left"> <%=messages.getText("group.data.name")%></th>
					         	<td align="right">
					         		<%=groupData.getName() %>
					          	</td>
					      	</tr>	
					      	<tr  class="space"><th align="right"></th><td align="right"></td></tr>
      						<tr  class="linetwo">
        						<th align="left" valign="top"><%=messages.getText("group.data.description")%></th>
					         	<td align="right">
					         		<%=groupData.getDescription() %>
					          	</td>
      						</tr>
					      	<tr  class="space"><th align="right"></th><td align="right"></td></tr>
      						<tr  class="linetwo">
        						<th align="left"> <%=messages.getText("group.data.period")%></th>
        						<td align="right">
					         		<%=(groupData.getPeriod().equals(GroupMainAttributes.MONTHLY)) ? messages.getText("group.data.monthly") : messages.getText("group.data.byquarter")%>
          						</td>
      						</tr>
					      	<tr  class="space"><th align="right"></th><td align="right"></td></tr>
      						<tr  class="linetwo">
        						<th align="left"> <%=messages.getText("group.data.type")%></th>
        						<td align="right">
					         		<%=(individual) ? messages.getText("group.type.individual") : messages.getText("group.type.composed")%>
          						</td>
      						</tr>
      					</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
    				</td>
  				</tr>
		      	<tr>
					<jsp:include  page="../component/spaceline.jsp" />
		  		</tr>
<%		if(individual) {
%>			  	<tr>
		    		<td valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.group.lines")%>' />
						<table width="100%">
<%			Iterator it = groupData.getLinesIterator();
			if(!it.hasNext()) {
%>		    				<tr>	
								<td width="100%"class="linetwo">
   									<%=messages.getText("generic.messages.notresult")%>
								</td>
							</tr>
<%			}else {
%>							<tr>
								<td class="guide2"><%=messages.getText("corporation.divgeo.countries")%></td>				            				
<%				DivOrgAttribute[] attrs = sys.getUserSessionData().getFilter().getDivOrgsAttrs();
				int count = 0;
				for(int i = 0; i < attrs.length; i++) {
			    	if(attrs[i] != null) {
				    	count++;
%>								<td class="guide2"><%=messages.getText(attrs[i].getName())%></td>				    
<%					}
				}
%>							</tr>	
<%				boolean linetwo = true;
				while(it.hasNext()) {
					GroupLineData line = (GroupLineData)it.next();
%>							<tr>
								<td class='<%=(linetwo) ? "linetwo" : "lineone" %>' width="25%">
									<%=line.getGeoLabel(messages)%>
								</td>
<%					for(int i = 0; i < count; i++) { 
						String label = line.getLabel(i);
%>								<td class='<%=(linetwo) ? "linetwo" : "lineone" %>' width="25%">
									<%=(!Util.empty(label)) ? label : ""%>
								</td>
<%					}	
%>							</tr>
<%					linetwo = !linetwo;
		    	}
			}
%>	  					</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
    				</td>
  				</tr>
<%		}else {
%>			  	<tr>
		    		<td valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.group.includedgroups")%>' />
<%			if(groupData.getGroups().size() == 0) {
%>  	    			<%=messages.getText("generic.messages.notresult")%>
<%			}else {
%>		    			<html:form action="/DeleteGroup"  >
							<html:hidden property="group" value='<%=String.valueOf(groupId)%>' />
							<html:hidden property="list" />
							<table width="100%" border="0">
								<tr align="center">
     	    	  					<td width='5%' class="guide2"></td>
						    		<td width='24%' class="guide2"><%=messages.getText("generic.fleet.fleetdata.name")%></td>
            						<td width='48%'	class="guide2"><%=messages.getText("generic.fleet.fleetdata.description")%></td>
	        						<td width='24%'	class="guide2"><%=messages.getText("generic.fleet.fleetdata.type")%></td>
								</tr>
<%				Iterator it = groupData.getGroups().iterator();
				boolean linetwo = true;
				while(it.hasNext()) {
					GroupMainAttributes group = (GroupMainAttributes)it.next();
%>		           				<tr align=center align="right" class='<%=(linetwo) ? "linetwo" : "lineone"%>'>
	            					<td width='5%' align="center"><input type='checkbox' name='<%=String.valueOf(group.getId())%>' value='<%=String.valueOf(group.getId())%>' ></td>
						            <td width='24%' align="right">
										<a href='layout.jsp?refer=/groups/groupView.jsp?id=<%=group.getId()%>'>
											<%=group.getName()%> 
										</a>
									</td>
	       							<td width='48%' align="right">
										<%=group.getDescription()%> 
									</td>
						            <td width='24%' align="right"><%=(group.getType().equals(GroupMainAttributes.COMPOSED)) ? messages.getText("generic.fleet.colective") : messages.getText("generic.fleet.individual")%> </td>
								</tr>
<%					linetwo = !linetwo;
				}
%>		    				</table>
						</html:form>
						<table width='100%'  border="0" cellpadding="0" cellspacing="0" class="default">
							<tr>
								<td class="linetwo" align="right">
									<input name="button2" type="button" onClick="javascript:deleteIFConfirm(document.forms['DeleteGroupForm'],'<%=messages.getText("web.group.confirmdelete")%>');" value='<%=messages.getText("generic.messages.delete")%>' class="input"/>
								</td>
							</tr>
						</table>
<%			}
%>						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
<%		}
%>		      	<tr>
					<jsp:include  page="../component/spaceline.jsp" />
		  		</tr>
			  	<tr>
		    		<td valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.group.users")%>' />
						<html:form action="/DeleteUserGroup">
							<html:hidden property="group" value='<%=String.valueOf(groupId)%>'/>
							<html:hidden property="list"/>
							<table width="100%">
<%		Iterator it = groupReport.findUsersGroup(groupId,sys.getUserSessionData()).iterator();
		if(!it.hasNext()) {
%>				   				<tr>	
									<td width="100%"class="linetwo">
   										<%=messages.getText("generic.messages.notresult")%>
									</td>
								</tr>
<%		}else {
%>								<tr>
									<td class="guide2" width="3%">&nbsp;</td>
					                <td class="guide2" width="26%"><%=messages.getText("generic.user.userdata.firstname")%></td>
					                <td class="guide2" width="36%"><%=messages.getText("generic.user.userdata.lastname")%></td>
					                <td class="guide2" width="36%"><%=messages.getText("generic.user.userdata.nickname")%></td>
								</tr>	
<%			boolean linetwo = true;
			while(it.hasNext()) {
				UserAttributes user = (UserAttributes)it.next();
%>								<tr>
		           					<td width="5%" align="center"><input type='checkbox' name='<%=user.getLoginName()%>' value='<%=user.getLoginName()%>' ></td>
									<td class='<%=(linetwo) ? "linetwo" : "lineone" %>' width="26%">
										<%=user.getFirstName()%>
									</td>
									<td class='<%=(linetwo) ? "linetwo" : "lineone" %>' width="36%">
										<%=user.getLastName()%>
									</td>
									<td class='<%=(linetwo) ? "linetwo" : "lineone" %>' width="36%">
										<%=user.getLoginName()%>
									</td>
								</tr>
<%					linetwo = !linetwo;
		    }
%>								<tr>
									<td class="linetwo" align="right" colspan="4">
										<input name="button2" type="button" onClick="javascript:deleteIFConfirm(document.forms['DeleteUserGroupForm'],'<%=messages.getText("group.user.confirmdelete")%>');" value='<%=messages.getText("generic.messages.delete")%>' class="input"/>
									</td>
								</tr>
<%		}
%>							</table>
						</html:form>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
    				</td>
  				</tr>
		      	<tr>
					<jsp:include  page="../component/spaceline.jsp" />
		  		</tr>
			</table>
	</body>
<%		}else {
%>	<jsp:include  page='<%="/util/allowedData.jsp?message=group.error.access.group" %>'/>
<%		}
	}	%>
</html:html>
