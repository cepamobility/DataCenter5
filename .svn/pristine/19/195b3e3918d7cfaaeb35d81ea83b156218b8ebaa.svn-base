<%@page language="java" 
	errorPage="../exception.jsp"
	import="business.*"
	import="business.administration.user.*" 
	import="business.administration.corporation.*" 
	import="communication.language.*"
	import="communication.language.tables.*"
	import="communication.administration.user.tables.*"
	import="communication.administration.corporation.tables.*"
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
	int level;String currentCorporationName; 
%>
<%
	
	pathMsg="/util/jsp/message.jsp";
	
	dispatcher=request.getRequestDispatcher(pathMsg);
	dispatcher.include(request,response);
	
	SystemAccess sys=(SystemAccess)session.getAttribute("SystemAccess");
	messages=sys.getText();

	level=Integer.parseInt((String)request.getParameter("level"));

	Integer currentCorporation = sys.getUserSessionData().getFilter().getCorporationId();
	Collection corporations = sys.getCorporationReportFacade().findCorporationAttributesByUser(sys.getUserSessionData().getFilter().getLoginName(),sys.getUserSessionData());
	Iterator corpIt = corporations.iterator();
	Collection optionCorporations = new LinkedList();
	while(corpIt.hasNext()) {
		CorporationAttribute corp = (CorporationAttribute)corpIt.next();
		if(corp.getId().equals(currentCorporation)) {
		    currentCorporationName=corp.getName();
		}else{
			optionCorporations.add(new OptionItem(corp.getName(),String.valueOf(corp.getId())));
		}
	}
	Collections.sort((List)optionCorporations);
	session.setAttribute("optionCorporations",optionCorporations);
%>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

	<style type="text/css">
<!--
body {
	background-color: #F7F7F7;
}
-->
</style><head/>
	<title><%=messages.getText("corporation.messages.change")%></title>

<script>
	function init(level) {
		if(level == -1){
			self.opener.document.forms[1].submit();
			window.close();
		}
	}
</script>

	<html:form action="/Switchcorporationuser" >
  <body onLoad="init(<%=level%>)">
	  <table width='310' height="200" align='center' cellpadding="0" cellspacing="0" bgcolor="#F7F7F7">
		  <tr><td height="10">&nbsp;</td><td height="10">&nbsp;</td></tr>
				<tr class="lineone">
					<td height="30" align="left"><span ><b><%=messages.getText("generic.user.currentcorporation")%></b></span></td>
					<td height="30" align="left"><span ><%=currentCorporationName%></span></td>
				</tr>
				<tr><td height="10">&nbsp;</td><td height="10">&nbsp;</td></tr>
<%		if(optionCorporations.size() > 0) { %>				
				<tr class="lineone">
					<td height="30" align="left"><b><%=messages.getText("generic.messages.changeto")%></b></td>
					<td height="30" align="left">
						<html:select property="corporation" styleClass="input">
			          		<html:options collection="optionCorporations" property="value" labelProperty="label" />
						</html:select>
					</td>
				</tr>
				<tr><td height="10">&nbsp;</td><td height="10">&nbsp;</td></tr>
				<tr>	
					<td colspan="2">
						<div align="center">
							<html:submit value='<%=messages.getText("generic.messages.accept")%>' styleClass="input" />				    		  		
							<input type="button" value='<%=messages.getText("generic.messages.close")%>' onClick="javascript:window.close()" class="input"/>				    		  		
						</div>
					</td>
				</tr>
<%		}else {
%>				<tr class="lineone">
					<td height="30" align="left"><span ><b><%=messages.getText("user.changecorporation.nocorporations")%></b></span></td>
				</tr>
				<tr><td height="10">&nbsp;</td><td height="10">&nbsp;</td></tr>
				<tr>	
					<td colspan="2">
						<div align="center">
							<input type="button" value='<%=messages.getText("generic.messages.close")%>' onClick="javascript:window.close()" class="input"/>				    		  		
						</div>
					</td>
				</tr>
<%		}
%>				
  </table>		
  </body>
	</html:form>	
</html:html>