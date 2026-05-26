<%@page import="org.apache.struts.action.DynaActionForm"%>
<%@page language="java" 
errorPage="../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="business.alert.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.alert.*"
	import="communication.administration.user.tables.*"
	import="communication.alert.tables.*"
	import="communication.administration.corporation.tables.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.alert.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<script language="JavaScript" type="text/javascript">
function submitPagination(page,all) {
	document.forms[2].all.value = all;
	document.forms[2].pageNum.value = page;
	document.forms[2].submit();
}
</script>
<html:html>
<%!
	RequestDispatcher dispatcher; 
	String pathLanguage; Text messages; SystemAccess sys;
	Integer pageNum; boolean all; int pageSize; 
	String checkboxParamList;String action;
%>
<%
	
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.ALERT_VIEW);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
	
		dispatcher=request.getRequestDispatcher("/util/jsp/language.jsp");
		dispatcher.include(request,response);

		messages=sys.getText();
	
		session.setAttribute("target","AlertExclusion");

		if(request.getParameter("checkboxParamList") != null) {
			checkboxParamList = request.getParameter("checkboxParamList");
		}else {
		    org.apache.struts.action.DynaActionForm form = (org.apache.struts.action.DynaActionForm)session.getAttribute("AlertDeleteForm");
			checkboxParamList = (String)form.get("checkboxParamList");
		}
		session.setAttribute("url","alert/AlertExclusion.jsp?checkboxParamList="+checkboxParamList);
		List list = sys.getAlertReportFacade().getAlerts(checkboxParamList.replaceAll("<",","),sys.getUserSessionData());
		if(list.size() > 0) {
%>


<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
<head>
	<META http-equiv="Cache-Control" content="no-cache">
	<META http-equiv="Pragma" content="no-cache">
	<META http-equiv="Expires" content="-1">		
</head>

<body bgcolor="#FFFFFF">
	<html:form action="Alertdelete">
		<html:hidden property="checkboxParamList" value='<%=checkboxParamList%>'/>
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1">
				<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("web.alert.delete")%>' />
	  		</tr>
    	  	<tr>
				<jsp:include  page="../component/spaceline.jsp" />
	      	</tr>
        	<tr>
    	    	<td colspan="3">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("web.alert.alertList.systemalert")%>' />
					<table width='100%' border="0" align='center' cellpadding="0" cellspacing="0">
      					<tr>
        					<td width="100%">
<%!		Iterator iter; AlertData alert; int i;
		String uid; int pageMax; int contIter; int total;
%>		    					<table width="100%" border="0">
              						<tr align="center">
					    	            <td class="guide2"><%=messages.getText("generic.alert.search.lastdate")%></td>
					    	            <td class="guide2"><%=messages.getText("generic.alert.search.type")%></td>
					    	            <td class="guide2"><%=messages.getText("generic.alert.search.subject")%></td>					    	            					    	            
              						</tr>
<%		iter=list.iterator();
		contIter=0;
		while(iter.hasNext()){
			contIter++;
			alert=(AlertData)iter.next();
%>	         						<tr align=center align="right" class='<%=((contIter % 2)==0) ? "lineone" : "linetwo"%>'>               
    	      							<td align="right">
        	          						<%=Util.formatDate(alert.getLastdate())%>
            	  						</td>
    	      							<td align="right">
											<%=messages.getText(alert.getAlertType().getResourceBundleId())%>        	          						
            	  						</td>            	  	
    	      							<td align="right">
        	          						<%=messages.getText(alert.getSubject())%>
            	  						</td>            	  											
               						</tr>
<%		}
%>		   						</table>
          					</td>
	      				</tr>
					</table>
   					<jsp:include page="../component/utilityboxbottom.jsp"/>	
				</td>
	      	</tr>
    	  	<tr>
				<jsp:include  page="../component/spaceline.jsp" />
	      	</tr>
			<tr>
				<td width="100%" colspan="3">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.comment")+"*"%>' />
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<html:textarea property="comment" cols="70" />
							</td>
						</tr>
					</table>
					<jsp:include  page="../component/utilityboxbottom.jsp" />
				</td>
			</tr>
			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
 			</tr>
			<tr  class="linetwo">
				<td colspan="2">&nbsp;</td>
				<td align="right">
					<html:submit styleClass="input"><%=messages.getText("generic.messages.finalize")%></html:submit>
		 			<html:cancel styleClass="input"><%=messages.getText("generic.messages.cancel")%></html:cancel >
				</td>
			</tr>
	    </table>
	</html:form>
</body>
<%		}else {
%>	<jsp:include  page='<%="/util/allowedData.jsp?message=alert.error.access.alert" %>'/>
<%		}
	}	
%></html:html>
