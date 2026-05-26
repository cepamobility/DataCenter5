<%@page
	import="communication.administration.group.tables.GroupMainAttributes"%>
<%@page language="java" errorPage="../exception.jsp"
	import="java.util.*" import="business.*"
	import="communication.language.*" import="communication.security.*"
	import="communication.administration.*"
	import="presentation.translator.web.util.*"
	import="business.administration.group.*" import="communication.util.*"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!-- Script de groups -->
<script language="JavaScript" type="text/javascript">
	function createUsr(){
		document.forms['UserCreateForm'].action.value = "create";
		document.forms['UserCreateForm'].submit();
	}

	function submitPagination(page,all) {
		document.forms['UserCreateForm'].all.value = all;
		document.forms['UserCreateForm'].pageNum.value = page;
		document.forms['UserCreateForm'].submit();
	}
	function doSubmit() {
		document.forms['search'].submit();
	}
	function addIFConfirm(form,msg){
	
		var elements=form.elements;
		var length=elements.length;
		var i;
		var valueCheckboxParamList="";
			
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

<!-- Fin Script groupList -->

<script language="javascript">
	function createWithPassword() {
		document.forms['UserCreateForm'].elements['type'].value = 'password';
		document.forms['UserCreateForm'].submit();
	}
</script>
<html:html>

<%! RequestDispatcher dispatcher; String context; String pathMsg;
	Text messages; String pathLanguage; Integer pageNum;String value;int pageSize;boolean all;
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.CREATE_USER);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		Text messages = sys.getText();

		pathLanguage="/util/jsp/language.jsp";
	
		session.setAttribute("url","user/create.jsp");
		
//------------------Comienza Groups------------------------

		String groupId = request.getParameter("id");
	    String action = request.getParameter("action");
	    String groups = request.getParameter("groups");
	    String link = "groups/groupSearch.jsp?action="+action;
	    if(!Util.empty(groupId)) {
	        link += "&id="+groupId;
	    }
	    if(!Util.empty(groups)) {
	        link += "&groups="+groups;
	    }
		session.setAttribute("url",link);
		
		context=request.getContextPath();
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
	
		messages=sys.getText();
	
		if(request.getParameter("pageNum")!=null){
			pageNum=new Integer(request.getParameter("pageNum"));
		}else{
			pageNum=new Integer(1);
		}

		value = "";
		if(request.getParameter("value")!=null){
			value = request.getParameter("value");
		}		
		pageSize = 10;
		if(request.getParameter("pageSize")!=null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}		
		int first = 0;
		int count = pageSize;
		all=!Util.empty(request.getParameter("all")) && request.getParameter("all").equals("true");
		if(all) {
		    count=-1;
		}else{
		    first=(pageNum.intValue()-1)*pageSize;
		}
		GroupReportFacade groupReportFacade = sys.getGroupReportFacade();
		String link2 = request.getContextPath()+link;
		ListResult groupList = groupReportFacade.findSearchList(value,first,count,sys.getUserSessionData(),groups);
		
		//------------------Termina Groups------------------------
		
		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
	
		/*context=request.getContextPath();
		pathMsg="/util/jsp/message.jsp";*/
	
		/*dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);*/

		Collection notAvailable = new LinkedList();
		notAvailable.add("resetpassword");
		notAvailable.add("recomendation");
		notAvailable.add("fleetdriver");
		notAvailable.add("cds");

		Collection roleList = new LinkedList();
		Iterator roles = sys.getRoleReportFacade().findAll(sys.getUserSessionData()).iterator();
		while(roles.hasNext()) {
			RoleData role = (RoleData)roles.next();	
			if(!notAvailable.contains(role.getName())) {
			    if(sys.getCorporationId() == communication.administration.corporation.tables.CorporationData.CENAPRA
			    		|| sys.getCorporationId() == communication.administration.corporation.tables.CorporationData.IMESEVI_UY) {
					if(role.getName().equals("administrator") || role.getName().equals("imeseviview") || role.getName().equals("imeseviupdate")) {
						roleList.add(new OptionItem(messages.getText("role."+role.getName()+".name"),role.getName()));
					}
			    }else {
					if(!role.getName().equals("imeseviview") && !role.getName().equals("imeseviupdate") && !role.getName().equals("contractor")) {
						if(role.getName().equals("accesscontrol")) {
							if(sys.getCorporationId() == communication.administration.corporation.tables.CorporationData.LOSPIQUES) {
								roleList.add(new OptionItem(messages.getText("role."+role.getName()+".name"),role.getName()));
							}
						}else {
							roleList.add(new OptionItem(messages.getText("role."+role.getName()+".name"),role.getName()));
						}
					}
			    }
			}
			session.setAttribute("roleList",roleList);
		}
%>


<jsp:include page='<%="/language/findalllanguage.jsp" %>' />
<%	
	if(session.getAttribute("target")==null){
		session.setAttribute("target",request.getParameter("target"));
	}
%>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head />

<body bgcolor="#FFFFFF">
	<html:form action="/Usercreate" focus="loginname">
		<html:hidden property="type" value="normal" />
		<table width="450" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr class="guide1">
				<jsp:include
					page='<%="../component/titlecomponent.jsp?title="+messages.getText("web.user.usernedlist.newuser")%>' />
			</tr>
			<tr>
				<jsp:include page="../component/spaceline.jsp" />
			</tr>
			<tr>
				<td valign="top"><jsp:include
						page='<%="../component/utilityboxtop.jsp?title="+messages.getText("web.user.userview.systemuserdata")%>' />
					<table>
						<tr class="linetwo">
							<th width="224" align="left"><%=messages.getText("generic.user.userdata.nickname")%><span
								class="required">*</span></th>
							<td align="left">
								<div align="right">
									<html:text property="loginname" size="25" styleClass="input" />
								</div>
							</td>
						</tr>
						<tr class="space">
							<th align="right"></th>
							<td align="right"></td>
						</tr>
						<tr class="linetwo">
							<th align="left"><%=messages.getText("generic.user.userdata.pass")%><span
								class="required">*</span></th>
							<td align="left">
								<div align="right">
									<html:password property="password" size="25" styleClass="input" />
								</div>
							</td>
						</tr>
						<tr class="space">
							<th align="right"></th>
							<td align="right"></td>
						</tr>
						<tr class="linetwo">
							<th align="left"><%=messages.getText("web.user.create.confirmpassword")%><span
								class="required">*</span></th>
							<td align="left">
								<div align="right">
									<html:password property="rePassword" size="25"
										styleClass="input" />
								</div>
							</td>
						</tr>
						<tr class="space">
							<th align="right"></th>
							<td align="right"></td>
						</tr>
						<tr class="linetwo">
							<th align="left"><%=messages.getText("generic.user.userdata.firstname")%><span
								class="required">*</span></th>
							<td align="left">
								<div align="right">
									<html:text property="firstName" size="25" styleClass="input" />
								</div>
							</td>
						</tr>
						<tr class="space">
							<th align="right"></th>
							<td align="right"></td>
						</tr>
						<tr class="linetwo">
							<th align="left"><%=messages.getText("generic.user.userdata.lastname")%><span
								class="required">*</span></th>
							<td align="left">
								<div align="right">
									<html:text property="lastName" size="25" styleClass="input" />
								</div>
							</td>
						</tr>
						<tr class="space">
							<th align="right"></th>
							<td align="right"></td>
						</tr>
						<tr class="linetwo">
							<th align="left"><%=messages.getText("generic.user.userdata.mail")%></th>
							<td align="left">
								<div align="right">
									<html:text property="email" size="25" styleClass="input" />
								</div>
							</td>
						</tr>
						<tr class="space">
							<th align="right"></th>
							<td align="right"></td>
						</tr>
						<tr class="linetwo">
							<th align="left"><%=messages.getText("generic.user.userdata.language")%><span
								class="required">*</span></th>
							<td align="left">
								<div align="right">
									<html:select property="language" styleClass="input"
										style="width:160; ">
										<html:options collection="languageOptionList" property="value"
											labelProperty="label" />
									</html:select>
								</div>
							</td>
						</tr>
						<tr class="space">
							<th align="right"></th>
							<td align="right"></td>
						</tr>
						<tr class="linetwo">
							<th align="left"><%=messages.getText("generic.user.userdata.role")%><span
								class="required">*</span></th>
							<td align="left">
								<div align="right">
									<html:select property="role" styleClass="input"
										style="width:160; ">
										<html:options collection="roleList" property="value"
											labelProperty="label" />
									</html:select>
								</div>
							</td>
						</tr>
						<tr class="space">
							<th align="right"></th>
							<td align="right"></td>
						</tr>

						<!--Comienza GroupsList -->
						<% 				Collection groupselements = groupList.getElements();
				Collections.sort((List)groupselements);
				Iterator itgroups = groupselements.iterator();
%>
						<tr class="space">
							<td align="right" colspan="2"><jsp:include
									page='<%="../component/utilitybox2top.jsp?title="+messages.getText("web.group.grouplist")%>' />
								<table width='100%' border="0" align='center' cellpadding="0"
									cellspacing="0">
									<tr align="center">

										<%				int total = groupList.getTotal();
				boolean delete = sys.getUserSessionData().checkPermission(SecurityConstants.DELETE_GROUP);
				boolean edit = sys.getUserSessionData().checkPermission(SecurityConstants.EDIT_GROUP);
				if(total == 0) {
%>
										<%=messages.getText("generic.messages.notresult")%>
<%				}else {	
%>										<td width='5%' class="guide2"></td>
										<td width="15%" class="guide2" align="center"><%=messages.getText("generic.fleet.fleetdata.name") %></td>
										<td width="50%" class="guide2" align="center"><%=messages.getText("generic.fleet.fleetdata.description") %></td>
										<td width="15%" class="guide2" align="center"><%=messages.getText("generic.fleet.fleetdata.type") %></td>
									</tr>
<%				
					Collection elements = groupList.getElements();
					Collections.sort((List)elements);
					Iterator it = elements.iterator();
					count = 0;
					boolean linetwo = true;
					while(it.hasNext() && (count < pageSize || all)) {
					GroupMainAttributes group = (GroupMainAttributes)it.next();				
%>
									<!-- Defino el combo para la lista de grupos -->
									<tr align=center align="right"
										class='<%=(linetwo) ? "linetwo" : "lineone"%>'>
										<td width='5%' align="center"><html:checkbox
												property="groupIds"
												value="<%= String.valueOf(group.getId().toString())%>">
											</html:checkbox></td>
										<td width="24%" align="right"><a
											href='layout.jsp?refer=/groups/groupView.jsp?id=<%=group.getId()%>'>
												<%= (group.getName() != null) ? group.getName() : "---" %>
										</a></td>
										<td width="48%" align="right"><%=  (group.getDescription() != null) ? group.getDescription() :"---" %>
										</td>
										<td width='24%' align="right"><%=(group.getType().equals(GroupMainAttributes.COMPOSED)) ? messages.getText("generic.fleet.colective") : messages.getText("generic.fleet.individual")%>
										</td>
									</tr>
<%	    				count++;
						linetwo = !linetwo;
	   				}
				}
%>
								</table> <jsp:include page="../component/utilitybox2bottom.jsp" /></td>
						</tr>
						<tr class="space">
							<th align="right"></th>
							<td align="right"></td>
						</tr>
						<tr class="linetwo">
							<td align="right"></td>
							<td align="right">
								<div align="right">
									<input type="button" class="input"
										onclick="createWithPassword()"
										value='<%=messages.getText("user.create.with.password")%>' />
									<!--html:submit styleClass="input"-->
									<input type="button" class="input" onClick="createUsr(true)"
										value='<%= messages.getText("generic.messages.save")%>' />
									<!-- poner el button que vaya al javascript y que cargue un campo mas para poder filtrar -->
									<!-- /html:submit -->
									<html:cancel styleClass="input">
										<%=messages.getText("generic.messages.cancel")%>
									</html:cancel>
								</div>
							</td>
						</tr>
					</table> <jsp:include page="../component/utilityboxbottom.jsp" /></td>
			</tr>
		</table>

	</html:form>
</body>
<%	}	%>
</html:html>
