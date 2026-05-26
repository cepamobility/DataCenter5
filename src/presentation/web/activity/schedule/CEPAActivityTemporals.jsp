<%@page language="java"
	errorPage="../../exception.jsp"
	import="business.*"
	import="business.administration.user.*"
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.datatype.tables.ActivityTypeDataType" 
	import="communication.datatype.*"
	import="communication.datatype.tables.*"	
	import="communication.administration.user.tables.*"
	import="presentation.translator.web.util.*"
	import="java.util.*"
	import="communication.activity.tables.CEPAActivityConstants"
	import="business.activity.ActivityReportFacade"
	import="communication.activity.tables.CEPAActivityScheduleData"	
	import="presentation.translator.web.activity.schedule.*"	 
	import="communication.security.*"	
	import="communication.administration.user.UserSessionData"	
	import="communication.activity.tables.*"	
	import="communication.administration.corporation.tables.CorporationData"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>

<%! Text messages;
	SystemAccess sys; 
	UserSessionData userSessionData;
	String idActivity;
%>
<html:html>

<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();
	messages=sys.getText();		

	String check = Util.checkPermission(sys,SecurityConstants.MODIFY_CEPASCHEDULEACTIVITY);
	if (check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		idActivity = (String)request.getParameter("idActivity"); // Refactoring
		userSessionData.setUrl("activity/schedule/CEPAActivityTemporals.jsp?id="+idActivity);	
		
		ActivityReportFacade activityReportFacade = sys.getActivityReportFacade();
		CEPAActivityScheduleData activityData = activityReportFacade.findCEPAActivityScheduleByPrimaryKey(new Integer(idActivity),userSessionData);
	
		if(activityData!=null) {
			List totalList = sys.getDriverReportFacade().getAllDriverCorporation(userSessionData);
%>  

		<script language="javascript" src='/datacenter/menu/js/driverFunctions.js' type='text/javascript' ></script> 
     	<script type="text/javascript" language="javascript" src="/datacenter/util/table/js/jquery.js"></script>
     	<script type="text/javascript" language="javascript" src="/datacenter/util/table/js/jquery.dataTables.js"></script>
     	<script type="text/javascript" language="javascript" src="/datacenter/util/table/resources/syntax/shCore.js"></script>
     	<script type="text/javascript" language="javascript" src="/datacenter/util/table/resources/demo.js"></script>
    	<script type="text/javascript" class="init">
			$(document).ready(function() {
   				$('#example').dataTable();
			});
   		</script>
		<head>
      		<link rel="stylesheet" type="text/css" href="/datacenter/util/table/css/jquery.dataTables.css">
      		<link rel="stylesheet" type="text/css" href="/datacenter/util/table/resources/syntax/shCore.css">
      		<link rel="stylesheet" type="text/css" href="/datacenter/util/table/resources/demo.css">
		</head>
	<body class="dt-example" bgcolor="#FFFFFF">
		<html:form action="ActivityResolveTemporals">
			<html:hidden property="idActivity" value="<%=idActivity%>"/>
			<table width="750" border="0" cellspacing="0" cellpadding="0" align="center">
 				<tr class="guide1">
					<jsp:include  page='<%="../../component/titlecomponent.jsp?title="+messages.getText("web.activity.temporals")%>' />
  				</tr>
				<tr>
					<jsp:include  page="../../component/spaceline.jsp" />
  				</tr>
				<tr>
					<td>
			            <div class="container">
			               <section>
			                  <br>
			                  <table id="example" class="display" cellspacing="0" width="100%">
<%			int len = ((Object[])totalList.get(0)).length;		
%>		  	  					<thead>
									<tr>
<%			for(int i = 0; i < len; i++) {
%>		    							<tH width="100%"><%=String.valueOf(i) %></tH>
<%			}
%>									</tr>
								</thead>	
		  	  					<tfoot>
									<tr>
<%			for(int i = 0; i < len; i++) {
%>		    							<tH width="100%"><%=String.valueOf(i) %></tH>
<%			}
%>									</tr>
								</tfoot>	
								<tbody>
<%			Iterator it = totalList.iterator();
			while(it.hasNext()) {
				Object[] data = (Object[]) it.next();
%>		  	  						<tr>
<%				for(int i = 0; i < data.length; i++) {
%>		    							<td width="100%"><%=String.valueOf(data[i]) %></td>
<%				}
%>									</tr>
<%			}
%> 					  			</tbody>
			                  </table>
			               </section>
			            </div>
			    	</td>
				</tr>
				<tr>
		  			<td height="18" colspan="3" valign="top">
						<div align="right">
			 				<html:submit styleClass="input"> <%=messages.getText("generic.messages.save")%> </html:submit>
		     				<html:reset styleClass="input"> <%=messages.getText("generic.messages.reset")%> </html:reset>
						</div>    
					</td>
				</tr>
			</table>
		</html:form>   
	</body>
<%		}else { %>	
			<jsp:include  page='<%="/util/allowedData.jsp?message=activity.error.access.activity" %>'/>
<%		}
	}
%>
</html:html>
