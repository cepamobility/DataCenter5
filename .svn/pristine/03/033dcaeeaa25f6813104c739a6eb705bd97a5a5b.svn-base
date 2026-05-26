<%@page import="org.apache.struts.action.DynaActionForm"%>
<%@page language="java" 
	errorPage="../../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.activity.tables.*"
	import="communication.driver.tables.*"	
	import="communication.administration.corporation.tables.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*"
 	import="communication.administration.user.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>


<html:html>
<%!
	RequestDispatcher dispatcher; 
	String pathLanguage; Text messages; SystemAccess sys;
	Integer pageNum; boolean all; int pageSize; 
	String checkboxParamList;String action;
	String id; UserSessionData userSessionData;
%>
<%
	
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	userSessionData = sys.getUserSessionData();
	String check = Util.checkPermission(sys,SecurityConstants.VIEW_CEPASCHEDULE_ACTIVITY_LIST);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
	
		dispatcher=request.getRequestDispatcher("/util/jsp/language.jsp");
		dispatcher.include(request,response);

		messages=sys.getText();
	
		// session.setAttribute("target","ActivityRegistryExclusion");

		if(request.getParameter("checkboxParamList") != null) {
			checkboxParamList = request.getParameter("checkboxParamList");
			System.out.println("CHECKPARAMLIST:" + checkboxParamList);			
		}else {
		    org.apache.struts.action.DynaActionForm form = (org.apache.struts.action.DynaActionForm)session.getAttribute("ActivityDeleteForm");
			checkboxParamList = (String)form.get("checkboxParamList");
			System.out.println("CHECKPARAMLIST:" + checkboxParamList);
		}
		// session.setAttribute("url","activity/schedule/CEPAActivityScheduleRegistryExclusion.jsp?checkboxParamList="+checkboxParamList);
		sys.getUserSessionData().setUrl("activity/schedule/CEPAActivityScheduleRegistryExclusion.jsp?checkboxParamList="+checkboxParamList);
		
		List list = sys.getActivityReportFacade().getDrivers(checkboxParamList.replaceAll("<",","),sys.getUserSessionData()); 
		if(list.size() > 0) {		
		    id = request.getParameter("activity");
		    if(Util.empty(id)) {
				id=(String)session.getAttribute("id");
		    }
%>
<LINK REL=StyleSheet HREF="../../util/css/estilo.css" TYPE="text/css">
<head>
	<META http-equiv="Cache-Control" content="no-cache">
	<META http-equiv="Pragma" content="no-cache">
	<META http-equiv="Expires" content="-1">		
</head>

<script language="JavaScript" type="text/javascript">
function submitPagination(page,all) {
	document.forms[2].all.value = all;
	document.forms[2].pageNum.value = page;
	document.forms[2].submit();
}
</script>
<body bgcolor="#FFFFFF">
	<html:form action="ActivityRegistrydelete">
		<html:hidden property="activity" value='<%=id%>'/>
		<html:hidden property="checkboxParamList" value='<%=checkboxParamList%>'/>
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1">
				<jsp:include  page='<%="../../component/titlecomponent.jsp?title="+messages.getText("web.activity.delete")%>' />
	  		</tr>
    	  	<tr>
				<jsp:include  page="../../component/spaceline.jsp" />
	      	</tr>
        	<tr>
    	    	<td colspan="3">
					<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("web.activity.activitylist.systemactivities")%>' />
					<table width='100%' border="0" align='center' cellpadding="0" cellspacing="0">
      					<tr>
        					<td width="100%">
<%!		Iterator iter; DriverData driver; int i;
		String uid; int pageMax; int contIter; int total;
%>		    					<table width="100%" border="0">
              						<tr align="center">
					    	            <td class="guide2"><%=messages.getText("generic.driver.driverdata.firstname")%></td>
					    	            <td class="guide2"><%=messages.getText("generic.driver.driverdata.lastname")%></td>
					    	            <td class="guide2"><%=messages.getText("generic.activityregistry.hasresult")%></td>					    	            
              						</tr>
<%		iter=list.iterator();
		contIter=0;
		while(iter.hasNext()){
			contIter++;
			driver=(DriverData)iter.next();
			String hasResult = messages.getText("generic.activityregistry.hasresult.no");
			if (sys.getActivityReportFacade().hasResult(new Integer(id),driver.getId(),sys.getUserSessionData())) {
				hasResult = messages.getText("generic.activityregistry.hasresult.yes");				
			}
%>	         						<tr align=center align="right" class='<%=((contIter % 2)==0) ? "lineone" : "linetwo"%>'>               
    	      							<td align="right">
											<%=driver.getFirstName()%>        	          						
            	  						</td>            	  	
    	      							<td align="right">
        	          						<%=driver.getLastName()%>
            	  						</td>            
    	      							<td align="right">
        	          						<%=hasResult%>
            	  						</td>                        	  							  											
               						</tr>
<%		}
%>		   						</table>
          					</td>
	      				</tr>
					</table>
   					<jsp:include page="../../component/utilityboxbottom.jsp"/>	
				</td>
	      	</tr>
    	  	<tr>
				<jsp:include  page="../../component/spaceline.jsp" />
	      	</tr>
			<tr>
				<td width="100%" colspan="3">
					<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.comment")+"*"%>' />
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<html:textarea property="comment" cols="70" />
							</td>
						</tr>
					</table>
					<jsp:include  page="../../component/utilityboxbottom.jsp" />
				</td>
			</tr>
			<tr>
				<jsp:include  page="../../component/spaceline.jsp" />
 			</tr>
			<tr  class="linetwo">
				<td colspan="2">&nbsp;</td>
				<td align="right">
					<html:submit styleClass="input"><%=messages.getText("web.driver.message.delete")%></html:submit>
		 			<html:cancel styleClass="input"><%=messages.getText("generic.messages.cancel")%></html:cancel >
				</td>
			</tr>
	    </table>
	</html:form>
</body>
<%		}else {
%>	<jsp:include  page='<%="/util/allowedData.jsp?message=activity.error.access.activity" %>' />
<%		}
	}	
%></html:html>
