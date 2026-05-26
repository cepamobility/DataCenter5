<%@page language="java"
 errorPage="../exception.jsp"	
 import="java.util.*"
 import="presentation.translator.web.util.*"
 import="presentation.translator.web.user.*"
 import="communication.language.*"
 import="communication.security.*"
 import="business.*" 
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>


<html:html>

<%! LinkedList list;String target;
	
	String pathLanguage;
	Text messages; RequestDispatcher dispatcher; 
	SystemAccess sys;
%>
<%
	// UserSearchAction searchAction;
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.VIEW_USER_LIST);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		messages=sys.getText();
		pathLanguage="/util/jsp/language.jsp";
	
		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
	
		target=(String)request.getParameter("target");
		if(session.getAttribute("optionSearchList")==null){
			list =new LinkedList();
			list.add(new OptionItem(messages.getText("generic.user.userdata.firstname"),"firstName"));
			list.add(new OptionItem(messages.getText("generic.user.userdata.lastname"),"lastName"));
			list.add(new OptionItem(messages.getText("generic.user.userdata.nickname"),"nick"));
			list.add(new OptionItem(messages.getText("generic.user.userdata.mail"),"mail"));
			session.setAttribute("optionSearchList", list);
		
		}
	
		/*
		if(session.getAttribute("userSearchList")==null){
			searchAction=new UserSearchAction();
			session.setAttribute("userSearchList",searchAction.getSearchList(sys,"firstName",""));
		}
		*/
%>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
<head/>
<body bgcolor="#FFFFFF">
<html:form action="/Usersearch">
<table width="350" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr valign="middle">
    <td width="5%" align="right" class="default" cellspacing="0">
      <html:hidden property="target" value='<%=target%>' />    </td>
    <td width="35%" align="right" class="default" cellspacing="0">
      <html:select property="option" styleClass="input">
          <html:options collection="optionSearchList" property="value" labelProperty="label" styleClass="input" />
	  </html:select>    </td>
    <td width="35%" align="right" class="default" cellspacing="0">
      <html:text property="value" size="16" styleClass="input"/>    </td>
  <td width="25%" align="right">
      <html:submit styleClass="input">
			<%=messages.getText("generic.messages.search")%>
      </html:submit>  </td>
  </tr>
</table>
</html:form>

<%
	session.removeAttribute("optionSearchList");
%>
<div align="center"></div>
<div align="center"></div>
</body>
<%	}	%>
</html:html>
