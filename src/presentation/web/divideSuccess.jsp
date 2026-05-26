<%@page import="communication.driver.tables.DriverData"%>
<%@page import="communication.administration.corporation.tables.ItemData"%>
<%@page import="communication.administration.corporation.tables.DivOrgData"%>
<%@page import="communication.activity.tables.CEPAActivityScheduleData"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="communication.administration.user.UserSessionData"%>
<%@ page language="java"
    errorPage="exception.jsp"
	import="business.*"
	import="presentation.translator.web.util.*"
	import="communication.language.*"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	Text messages = sys.getText();
	UserSessionData userSessionData = sys.getUserSessionData();
%>

<html>
	<head>
		<LINK REL=StyleSheet HREF="./util/css/estilo.css" TYPE="text/css">
	</head>
 	<script type="text/javascript">
 		function openActivity(activity) {
 			var form = document.forms['activity'];
 			form.id.value = activity;
 			form.submit();
 		}
 	</script>
	<body>
		<form action="layout.jsp" name="activity">
			<input type="hidden" name="refer" value="/activity/schedule/CEPAActivityScheduleView.jsp" />
			<input type="hidden" name="id"/>
		</form>
		<center>
			<table width="600" border="0" cellpadding="0" cellspacing="0">
	    		<tr>
   					<jsp:include  page='<%="component/titlecomponent.jsp?colspan=3&title="+messages.getText("web.activity.divided")%>' />			
    			</tr>
  				<tr>
    				<jsp:include  page="component/spaceline.jsp" />
				</tr>
		  		<tr>
					<td class="texts"><%=messages.getText("activity.divide.messagepost") %></td>
		  		</tr>   
		  		<tr>
					<jsp:include  page="component/spaceline.jsp" />
		  		</tr>   
<%			Collection newActivities = (Collection)userSessionData.getValue();
			int index = 0;
			if(newActivities != null && newActivities.size() > 0) {
				Iterator itAct = newActivities.iterator();
				while(itAct.hasNext()) {
					index++;
					CEPAActivityScheduleData newActivity = (CEPAActivityScheduleData)itAct.next();
					Iterator it = newActivity.getDivOrgsIterator();
%>			
				<tr>
	    			<td valign="top">
						<jsp:include  page='<%="component/utilityboxtop.jsp?title="+messages.getText("module.activity.name")+" "+index%>' />
						<table width="100%" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td align="left" class="texts"><%=messages.getText("generic.activity.activitydata.date")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%></td>
								<td align="right" class="linetwo"><%=Util.formatDate(newActivity.getDate()) %></td>			
							</tr>										
							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr>
								<td align="left" class="texts"><%=messages.getText("generic.activity.activitydata.type")%></td>
								<td align="right" class="linetwo"><%=messages.getText(newActivity.getType().getResourceBundleId())%></td>
							</tr>
							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr>
								<td align="left" class="texts"><%=messages.getText("generic.activity.activitydata.city")%></td>
								<td align="right" class="linetwo"><%=newActivity.getCity()%></td>
							</tr>				
							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr>
								<td align="left" class="texts"><%=messages.getText("generic.activity.activitydata.state")%></td>
								<td align="right" class="linetwo"><%=messages.getText(newActivity.getState().getResourceBundleId())%></td>
							</tr>
<%					while(it.hasNext()) {		
						DivOrgData divOrgData = (DivOrgData)it.next();
						if(divOrgData.getDivOrgLevels() != null) {
							ItemData item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
%>
							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr>
								<td align="left" class="texts"><%=messages.getText(divOrgData.getName())%></td>
<%							String pageTitulo = item1.getName();
		    				if(item1.getSubItems() != null) {
								ItemData item2 = (ItemData)item1.getSubItems().getItemIterator().next();
								pageTitulo += " / " + item2.getName();
								if(item2.getSubItems() != null) {
									ItemData item3 = (ItemData)item2.getSubItems().getItemIterator().next();
									pageTitulo += " / " + item3.getName();
								} // end if(item2.getSubItems() != null)
							} // end if(item1.getSubItems() != null) 
%>			
								<td align="right" class="linetwo"><%=pageTitulo%></td>
							</tr>
<%						}
					}
%>
							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr>
								<td valign="top" colspan="2">
									<jsp:include  page='<%="component/utilitybox2top.jsp?title="+messages.getText("generic.data.driver.pl")%>' />
									<table width="100%" cellpadding="0" cellspacing="0" border="0" class="guideSearch">
<%					Iterator drivers = newActivity.getDrivers().iterator();
					while(drivers.hasNext()) {
						DriverData driver = (DriverData)drivers.next();
%>										<tr>
											<td class="lineone"><%=driver.getFirstName()+" "+driver.getLastName()%></td>
										</tr>   
<%					}

%>									</table>
									<jsp:include page="component/utilitybox2bottom.jsp"/>
								</td>
							</tr>
							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr  class="space">
								<td align="right" colspan="2">
									<input type="button" value="Ver Actividad" onclick="openActivity(<%=newActivity.getActivityId().toString()%>)"/>
								</td>
							</tr>
						</table>
						<jsp:include page="component/utilityboxbottom.jsp"/>
					</td>
				</tr>
				<tr>
					<jsp:include  page="component/spaceline.jsp" />
	  			</tr>   
<%		}
	
	}
%>	  		
			</table>
		</center>
		<br/><br/>
	</body>
</html>
