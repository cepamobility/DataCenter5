<%@page language="java"
	errorPage="../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.group.tables.*"
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

	String id = "";
	String type = request.getParameter("type"); 
	String permission = (type.equals("create")) ? SecurityConstants.CREATE_GROUP : SecurityConstants.MODIFY_GROUP;
	
	String check = Util.checkPermission(sys,permission);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		Text messages = sys.getText();

		boolean valid = true;		
		if(type.equals("create")) {
			if(session.getAttribute("GroupCreateForm") == null) {
			    GroupDataForm form = new GroupDataForm();
			    session.setAttribute("GroupCreateForm",form);
			}
			session.setAttribute("url","groups/groupData.jsp?type=create");
		}else {
			if(session.getAttribute("GroupUpdateForm") == null) {
			    id = request.getParameter("groupId");
			    GroupMainAttributes data = sys.getGroupReportFacade().findGroupAttributes(new Integer(id),sys.getUserSessionData());
			    if(data == null) {
			        valid = false;;
			    }else {
				    GroupDataForm form = new GroupDataForm(data);
				    session.setAttribute("GroupUpdateForm",form);
			    }
			}else {
			    id = ((GroupDataForm)session.getAttribute("GroupUpdateForm")).getId();
			    GroupMainAttributes data = sys.getGroupReportFacade().findGroupAttributes(new Integer(id),sys.getUserSessionData());
			    if(data == null) {
			        valid = false;
			    }
			}
			session.setAttribute("url","groups/groupData.jsp?type="+type+"&groupId="+id);
		}

		if(valid) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
			dispatcher.include(request,response);
			
			Collection periodList = new LinkedList();
			periodList.add(new OptionItem(messages.getText("generic.messages.select"),""));                			
			periodList.add(new OptionItem(messages.getText("group.data.monthly"),String.valueOf(GroupMainAttributes.MONTHLY)));                			
			periodList.add(new OptionItem(messages.getText("group.data.byquarter"),String.valueOf(GroupMainAttributes.BY_QUARTER)));                			
			session.setAttribute("periodList",periodList);
			
			String title = (type.equals("create")) ? messages.getText("groups.create.title") : messages.getText("groups.update.title");
%>
	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>

	<body bgcolor="#FFFFFF">
		<html:form action='<%=(type.equals("create")) ? "/Groupcreate" : "/Groupupdate"%>' focus="name">
			<html:hidden property="id" value='<%=id%>'/>
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+title%>' />
		  		</tr>
		      	<tr>
					<jsp:include  page="../component/spaceline.jsp" />
		  		</tr>
			  	<tr>
		    		<td valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.group.data")%>' />
						<table width="100%">
					      	<tr  class="linetwo">
					        	<th align="left"> <%=messages.getText("group.data.name")%><span class="required">*</span></th>
					         	<td align="right">
					            	<html:text property="name" styleClass="input" style="width:250;" maxlength="150"/>
					          	</td>
					      	</tr>	
					      	<tr  class="space"><th align="right"></th><td align="right"></td></tr>
      						<tr  class="linetwo">
        						<th align="left" valign="top"><%=messages.getText("group.data.description")%><span class="required">*</span></th>
        						<td align="right">
        							<html:textarea property="description" styleClass="input" rows="3" style="width:250" cols="50"/>
          						</td>
      						</tr>
					      	<tr  class="space"><th align="right"></th><td align="right"></td></tr>
      						<tr  class="linetwo">
        						<th align="left"> <%=messages.getText("group.data.period")%><span class="required">*</span></th>
        						<td align="right">
									<html:select property="period" styleClass="input" style="width:250;" >
										<html:options collection="periodList" property="value" labelProperty="label" />
									</html:select>
          						</td>
      						</tr>
					      	<tr  class="space"><th align="right"></th><td align="right"></td></tr>
      						<tr  class="linetwo">
        						<th align="left"> <%=messages.getText("group.data.type")%><span class="required">*</span></th>
        						<td align="right">
									<html:radio property="type" styleClass="input" value='<%=String.valueOf(GroupMainAttributes.INDIVIDUAL) %>' ><%=messages.getText("group.type.individual")%></html:radio>
									<html:radio property="type" styleClass="input" value='<%=String.valueOf(GroupMainAttributes.COMPOSED) %>' ><%=messages.getText("group.type.composed")%></html:radio>
          						</td>
      						</tr>
	      			      	<tr  class="space"><th align="right"></th><td align="right"></td></tr>
      						<tr  class="linetwo">
        						<td colspan="2" align="right">
									<html:submit value='<%=messages.getText("generic.messages.accept")%>' styleClass="input" />
									<html:cancel value='<%=messages.getText("generic.messages.cancel")%>' styleClass="input" />
          						</td>
      						</tr>
      					</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
    				</td>
  				</tr>
			</table>
		</html:form>
	</body>
<%		}else {
%>	<jsp:include  page='<%="/util/allowedData.jsp?message=group.error.access.group" %>'/>
<%		}
	}	%>
</html:html>
