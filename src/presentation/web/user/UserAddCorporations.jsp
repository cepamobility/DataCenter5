<%@page 
	language="java" 
	errorPage="../exception.jsp" 
	import="business.*"
	import="business.administration.user.*"
	import="business.administration.corporation.*"
	import="communication.language.*" 
	import="communication.security.*"
	import="communication.administration.RoleData"
	import="communication.administration.user.tables.*"
	import="communication.administration.corporation.tables.*"
	import="presentation.translator.web.user.*"
	import="presentation.translator.web.util.*" 
	import="java.util.*"
	import="org.apache.struts.action.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<html:html>
<%!RequestDispatcher dispatcher;
	String pathMsg;
	String pathLanguage;
	Text messages;
	UserAttributes data;
	UserReportFacade userReport;
	CorporationReportFacade corporationReport;
	SystemAccess sys;
	String uid;
	String target;
%>
<%
	sys = (SystemAccess) session.getAttribute("SystemAccess");
		String check = Util.checkPermission(sys,
				SecurityConstants.MODIFY_USER);

		if (check != null) {
			response.sendRedirect(request.getContextPath() + check);
		} else {
			pathMsg = "/util/jsp/message.jsp";

			uid = (String) request.getParameter("uid");
			if (uid == null) {
				uid = (String) session.getAttribute("uid");
				session.removeAttribute("uid");
			}
			messages=sys.getText();
			
			session.setAttribute("url","user/UserAddCorporations.jsp?uid=" + uid);
			System.out.println("loginname :"+ uid);
			
			dispatcher = request.getRequestDispatcher(pathMsg);
			dispatcher.include(request, response);

			dispatcher = request.getRequestDispatcher("/language/findalllanguage.jsp");
			dispatcher.include(request, response);
		
		//Cargo todas las corporations
			Integer currentCorporation = sys.getUserSessionData().getFilter().getCorporationId();
			Collection<CorporationAttribute> corporations = sys.getCorporationReportFacade().findCorporationAttributesByUser(sys.getUserSessionData().getFilter().getLoginName(),sys.getUserSessionData());
			Collection<UserCorporationData> userCorporation = sys.getCorporationReportFacade().findCorporationRoleByUser(uid,sys.getUserSessionData());
			Collection<Integer> values = new LinkedList<Integer>();
			Iterator<UserCorporationData> it = userCorporation.iterator();
			while(it.hasNext()) {
				values.add(it.next().getId());
			}
			Iterator corpIt = corporations.iterator();
			Collection optionCorporations = new LinkedList();
			
			while (corpIt.hasNext()) {
				CorporationAttribute corp = (CorporationAttribute) corpIt.next();
				if(!values.contains(corp.getId())) {
					optionCorporations.add(new OptionItem(corp.getName(), String.valueOf(corp.getId())));
				}
			}
			Collections.sort((List) optionCorporations);
			session.setAttribute("optionCorporations",optionCorporations);

		//Cargo todos los Roles 	
			RoleData role = sys.getUserReportFacade().findUserRole(uid,sys.getUserSessionData());
			RoleForm roleForm = new RoleForm(uid, role.getName());

			Collection notAvailable = new LinkedList();

			notAvailable.add("resetpassword");
			notAvailable.add("recomendation");
			notAvailable.add("fleetdriver");
			notAvailable.add("cds");

			Collection roleList = new LinkedList();
			
			Collection roles = sys.getRoleReportFacade().findAll(sys.getUserSessionData());//Iterator
			
			Iterator rolesIt = roles.iterator();
			
			while (rolesIt.hasNext()) {
				RoleData roleData = (RoleData) rolesIt.next();
				if (roleData != null && roleData.getName() != null) {
					/*
					if (roleData.getName().equals(data.getRoleName())) {
						currentCorporationName = data.getRoleName();
					} else {*/
						OptionItem o = new OptionItem(messages.getText("role."+ roleData.getName() + ".name"), roleData.getName());
						roleList.add(o);
					//}
				}
			}
			Collections.sort((List) roleList);
			session.setAttribute("roleList", roleList);
			
			String corporation = sys.getUserSessionData().getFilter().getCorporationName();
			session.setAttribute("corporation",corporation);
			 	
			if (session.getAttribute("target") == null) {
				target = (String) request.getParameter("target");
				session.setAttribute("target", target);
			}
			
			data = sys.getUserReportFacade().findUserAttributes(uid, sys.getUserSessionData());
	
			if (uid != null) {
				messages = sys.getText();
				if (data != null) {
%>
<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head />

<body>

<html:form action="/UserAddCorporations">
		
		<input type="hidden" name="uid" value='<%=uid%>' />
		<input type="hidden" name="target" value="UserView"/>
		<input type="hidden" name="loginname" value= '<%=data.getLoginName()%>' />
		<input type="hidden" name="firstname" value= '<%=data.getFirstName()%>' />
		<input type="hidden" name="lastname" value= '<%=data.getLastName()%>' />
		
		<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1">
				<jsp:include page='<%="../component/titlecomponent.jsp?title="+ messages.getText("generic.user.userdata.userAddCorporation")%>' />
			</tr>
			<tr>
				<jsp:include page="../component/spaceline.jsp" />
			</tr>
			<tr>
				<td width="100%" valign="top">
					<jsp:include page='<%="../component/utilityboxtop.jsp?title="+ messages.getText("web.user.userview.systemuserdata")%>' />
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
						<tr class="linetwo">
							<td width="50%" height="20" align="left">
								<b><%=messages.getText("generic.user.userdata.nickname")%>:</b>&nbsp;<%=data.getLoginName()%>
							</td>
							<td align="left">
								<b><%=messages.getText("generic.user.userdata.firstname")%>:</b>&nbsp;<%=data.getFirstName()+" "+data.getLastName()%>
							</td>
						</tr>
					</table> 
					<jsp:include page="../component/utilityboxbottom.jsp" />
				</td>
			</tr>
			<tr>
				<jsp:include page="../component/spaceline.jsp" />
			</tr>
			<tr>
				<td width="100%" valign="top">
					<jsp:include page='<%="../component/utilityboxtop.jsp?title="+ messages.getText("web.user.corporations")%>' />
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
						<tr>
			                <td class="guide2" width="50%" align="left"><%=messages.getText("generic.corporation")%></td>
			                <td class="guide2" width="50%" align="left"><%=messages.getText("user.data.role")%></td>
						</tr>
<%			Collections.sort((List)userCorporation);
			Iterator<UserCorporationData> userCorpIt = userCorporation.iterator();
			String line = "linetwo";
			while (userCorpIt.hasNext()) {
				UserCorporationData corp = userCorpIt.next();
%>
						<tr class="<%=line%>">
							<td width="50%" align="left">
								<%=corp.getCorporationName()%>
							</td>
							<td width="50%" align="left">
								<%=messages.getText("role."+ corp.getRole() + ".name")%>
							</td>
						</tr>
<%				line = (line.equals("linetwo")) ? "lineone" : "linetwo"; 
			}
%>					</table> 
					<jsp:include page="../component/utilityboxbottom.jsp" />
				</td>
			</tr>
			<tr>
				<jsp:include page="../component/spaceline.jsp" />
			</tr>
			<tr>
				<td width="100%" valign="top">
					<jsp:include page='<%="../component/utilityboxtop.jsp?title="+ messages.getText("web.user.newcorporation")%>' />
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
						<tr class="linetwo">
							<td align="left">
								<b><%=messages.getText("generic.driver.driverdata.corporation")%>:</b>
								&nbsp;
								<html:select  property="corporationId" styleClass="input"> 
									<html:options collection="optionCorporations" property="value" labelProperty="label" styleClass="input" />
		     					</html:select> 
							</td>
							<td align="left">
								<b><%=messages.getText("generic.user.userdata.role")%></b>
								&nbsp;
								<html:select  property ="newrole" styleClass="input">
									<html:options  collection="roleList" property="value" labelProperty="label" styleClass="input" />
								</html:select>
							</td>
						</tr>
						<tr class="space">
							<th align="right">&nbsp;</th>
							<td align="right">&nbsp;</td>
						</tr>
						<tr class="linetwo">
							<td height="19"></td>
							<td align="right">
								<div align="right">
									<html:submit styleClass="input">
										<%=messages.getText("generic.messages.save")%>
									</html:submit>
									<html:cancel styleClass="input">
										<%=messages.getText("generic.messages.cancel")%>
									</html:cancel>
								</div>
							</td>
						</tr>
					</table> 
					<jsp:include page="../component/utilityboxbottom.jsp" />
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
