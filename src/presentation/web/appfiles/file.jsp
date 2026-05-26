<%@page import="presentation.translator.web.documents.AppFileForm"%>
<%@page import="communication.util.tables.AppFileData"%>
<%@page language="java"
	errorPage="../exception.jsp"
	import="business.*"
	import="java.io.*"
	import="java.text.*"
	import="java.util.*"
	import="business.administration.user.*"
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.vehicle.tables.*"
	import="communication.datatype.tables.*"
	import="communication.datatype.*"
	import="communication.corporation.tables.*"
	import="communication.geografic.tables.*"
	import="communication.administration.user.tables.*"
	import="communication.administration.user.tables.geographicdivisions.*"
	import="communication.security.*"
	import="communication.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.vehicle.*"
	import="presentation.translator.web.activity.schedule.*"	
	import="java.util.*"
 	import="communication.administration.user.*" 
 	import="communication.administration.corporation.tables.*"	
	import="communication.util.Filter"
	import="communication.security.*"
 			
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	Text messages = sys.getText();
	UserSessionData userSessionData;
	// Fin manejo de mensajes
 	
	//	Me fijo si tiene permisos para agregar reportes o cepa tips
	String addReportPermission = Util.checkPermission(sys,SecurityConstants.ADD_REPORT);
	if (addReportPermission == null) {
		
		sys = (SystemAccess)session.getAttribute("SystemAccess");
		userSessionData = sys.getUserSessionData();
		
		request.getRequestDispatcher("/util/jsp/message.jsp").include(request,response);
		
		if(request.getParameter("id") != null) {
	AppFileData file = sys.getCorporationReportFacade().getAppFile(new Integer(request.getParameter("id")),sys.getUserSessionData());
	AppFileForm form = new AppFileForm(file);
	session.setAttribute("AppFileForm", form);
		}else {
	AppFileForm form = new AppFileForm();
	session.setAttribute("AppFileForm", form);
		}
%>


<html:html>
	<head>
		<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	</head>
	<body>
		<html:form action="/AppFile" enctype="multipart/form-data">	
		<html:hidden property="language" value="<%= LanguageConstants.PT%>"/>
		<html:hidden property="language" value="<%= LanguageConstants.PT%>"/>
			<table width="850"  border="0" cellspacing="0" cellpadding="0" align="center"> 
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?colspan=5&title="+messages.getText("generic.messages.appfiles")%>' />
				</tr>
		   		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
		  		</tr>
		    	<tr>
		      		<td>
		      			<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.data")%>' />
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" >
      						<tr  class="linetwo">
        						<th align="left"> <%=messages.getText("generic.appfiles.title")%><span class="required">*</span></th>
        						<td></td>
         						<td align="right"><html:text property="title" size="55" styleClass="input"/></td>
      						</tr>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr  class="linetwo">
        						<th align="left"> <%=messages.getText("generic.appfiles.subtitle")%><span class="required">*</span></th>
        						<td></td>
         						<td align="right"><html:text property="subtitle" size="55" styleClass="input"/></td>
      						</tr>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr  class="linetwo">
        						<th align="left"> <%=messages.getText("generic.appfiles.description")%><span class="required">*</span></th>
        						<td></td>
         						<td align="right"><html:text property="description" size="55" styleClass="input"/></td>
      						</tr>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr  class="linetwo">
        						<th align="left"> <%=messages.getText("generic.appfiles.category")%><span class="required">*</span></th>
        						<td></td>
         						<td align="right">
         							<html:select property="category" styleClass="input">
         								<html:option value="1">Politics</html:option>
         								<html:option value="2">Tips</html:option>
         								<html:option value="3">News</html:option>
         							</html:select>
         						</td>
      						</tr>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr  class="linetwo">
        						<th align="left"> <%=messages.getText("generic.appfiles.doctype")%><span class="required">*</span></th>
        						<td></td>
         						<td align="right">
         							<html:select property="doctype" styleClass="input">
         								<html:option value="1">PDF</html:option>
         								<html:option value="2">PPS</html:option>
         								<html:option value="3">HTML</html:option>
         							</html:select>
         						</td>
      						</tr>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr  class="linetwo">
        						<th align="left"> <%=messages.getText("generic.appfiles.order")%><span class="required">*</span></th>
        						<td></td>
         						<td align="right">
         							<html:select property="order" styleClass="input">
<%							for(int i = 1; i <= 15; i++) {					
%>										<html:option value="<%=String.valueOf(i)%>"><%=String.valueOf(i)%></html:option>
<%							}					
%>         							</html:select>
         						</td>
      						</tr>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr  class="linetwo">
        						<th align="left"> <%=messages.getText("generic.appfiles.documentname")%><span class="required">*</span></th>
        						<td align="right"><html:file property="file"   styleClass="input"/></td>
         						<td align="right"><html:text property="documentname" size="55" styleClass="input"/></td>
      						</tr>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr  class="linetwo">
        						<th align="left"> <%=messages.getText("generic.appfiles.imagename")%><span class="required">*</span></th>
        						<td align="right"><html:file property="fileImage"   styleClass="input"/></td>
         						<td align="right"><html:text property="imagename" size="55" styleClass="input"/></td>
      						</tr>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr  class="linetwo">
        						<td colspan="3" align="right">
        							<html:submit value='<%=messages.getText("generic.messages.save")%>' styleClass="input"/>
        							<html:cancel value='<%=messages.getText("generic.messages.cancel")%>' styleClass="input"/>
        						</td>
      						</tr>
      					</table>
						<jsp:include  page='../component/utilityboxbottom.jsp' />
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html:html>

<%
  	}
 %>
