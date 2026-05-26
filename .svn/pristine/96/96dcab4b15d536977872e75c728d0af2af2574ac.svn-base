 <%@page import="communication.activity.tables.DriverActivity"%>
<%@page import="communication.activity.tables.InstructorActivity"%>
<%@page language="java"
	errorPage="../exception.jsp"
	import="business.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.administration.user.tables.*"
	import="presentation.translator.web.user.*"
	import="communication.language.tables.*"
	import="presentation.translator.web.util.Util"
	import="communication.administration.corporation.tables.*"
	import="java.util.*"
 	import="communication.administration.user.*" 		
 	import="communication.datatype.*" 	
 	import="communication.util.*"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData = sys.getUserSessionData();
	Text messages = sys.getText(); 
	String user = request.getParameter("user");
	if((user == null || user.length() == 0) && (sys.getUserSessionData().isInstructorActivity() && sys.getActivityReportFacade().isInstructorActivities(sys.getUserSessionData()))) {
		user = userSessionData.getFilter().getLoginName();
	}
	if(user == null || user.length() == 0) {
		Collection<UserData> instructors = sys.getActivityReportFacade().getActivityInstructors(userSessionData);
		if(instructors.size() == 1) {
			user = instructors.iterator().next().getLoginName();
		}
	}
	if(user.contains(",")) {
		user = user.replaceAll(",", "','");
	}
	
%>
		<script type="text/javascript">
		function showDrivers(id) {
			document.getElementById("activity_"+id).style.display = 'block';
			document.getElementById("drivers_"+id).style.display = 'none';
			document.getElementById("show_"+id).style.display = 'none';
			document.getElementById("hide_"+id).style.display = 'block';
		}
		function hideDrivers(id) {
			document.getElementById("activity_"+id).style.display = 'none';
			document.getElementById("drivers_"+id).style.display = 'block';
			document.getElementById("show_"+id).style.display = 'block';
			document.getElementById("hide_"+id).style.display = 'none';
		}
		</script>
		<table width="768" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1">
				<jsp:include  page='<%="../../component/titlecomponent.jsp?title="+messages.getText("web.activity.activitylist.systemactivities")%>' />
	  		</tr>
<%	Collection<InstructorActivity> activities = sys.getActivityReportFacade().getInstructorActivities(user, 0, userSessionData);
	if(activities == null || activities.size() == 0) {
%>
    	  	<tr>
				<jsp:include  page="../../component/spaceline.jsp" /> 
	      	</tr>
	      	<tr>
				<td class="texts"><%=messages.getText("activity.tablet.noactivity")%></td>	
	      	</tr>
		
<%	}else {
		Iterator<InstructorActivity> itActivities = activities.iterator();
		while(itActivities.hasNext()) {
		InstructorActivity activity = itActivities.next();
%>
    	  	<tr>
				<jsp:include  page="../../component/spaceline.jsp" /> 
	      	</tr>
    	  	<tr>
        		<td valign="top" class="lineone">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr class="guideSearch">
							<td width="19" ><img src="/datacenter/util/imgs/left_top_search.jpg" width="19" height="30" align="bottom"></td>
							<td align="left" class="guideSearch"><%=activity.getTitle(messages)%></td>
							<td width="19" ><img src="/datacenter/util/imgs/right_top_search.jpg" width="19" height="30" align="bottom"></td>
						</tr>
		   				<tr>
							<td class="leftLineSearch">&nbsp;</td>
    				<td>
					<table width="100%" cellspacing="0" cellpadding="0">
						<tr class="lineone">
							<th width="10%" align="left"><%=messages.getText("generic.activity.activitydata.state")%>:</th>
							<td width="35%" align="left"><%=messages.getText(activity.getState())%></td>
							<th width="10%" align="left"><%=messages.getText("generic.activity.activitydata.city")%>:</th>
							<td width="35%" align="left"><%=activity.getCity()%></td>
							<td width="10%" rowspan="2">
<%			if(activity.hasDrivers()) {	
%>								<div id='<%="show_"+activity.getId()%>'><a href='javascript:showDrivers(<%=String.valueOf(activity.getId())%>);' class="abutton">+</a></div>
								<div id='<%="hide_"+activity.getId()%>' style="display: none;"><a href='javascript:hideDrivers(<%=String.valueOf(activity.getId())%>);' class="abutton">-</a></div>
<%			}
%>							</td>
						</tr>
				      	<%=activity.getDivOrgs(userSessionData, messages)%>
			    	  	<tr>
							<jsp:include  page="../../component/spaceline.jsp" /> 
				      	</tr>
						<tr class="lineone">
							<td colspan="5">
<%			if(activity.hasDrivers()) {	
%>
								<div id='<%="drivers_"+activity.getId()%>'>
									<span class="texts"><%="<b>"+messages.getText("generic.activity.activitydata.drivercount")+":</b>&nbsp;"+activity.getDriverCount()%></span>	
								</div>
								<div id='<%="activity_"+activity.getId()%>' style="display: none;">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
						                	<td width="45%"	class="guide2"><%=messages.getText("generic.data.driver")%></td>
						                	<td width="25%"	class="guide2"><%=messages.getText("generic.activity.instructor")%></td>
						                	<td width="15%"	class="guide2"><%=messages.getText("generic.activity.synchronized")%></td>
						                	<td width="15%"	class="guide2"><%=messages.getText("generic.activity.date")%></td>
						           		</tr>
<%				Iterator<DriverActivity> itDrivers = activity.getDriversIterator();
				boolean lineone = false;
				while(itDrivers.hasNext()) {
					DriverActivity driver = itDrivers.next();
%>										<%=driver.getTableLine(lineone)%>
<%					lineone = !lineone;
				}
%>									</table>
								</div>
<%			}else {
%>									<span class="texts"><%=messages.getText("generic.activity.nodrivers")%></span>	
<%			}
%>							</td>
						</tr>
			    	  	<tr>
							<jsp:include  page="../../component/spaceline.jsp" /> 
				      	</tr>
					</table>
					<jsp:include  page='../../component/utilitybox2bottom.jsp' />
				</td>
	  		</tr>
<%		}
	}
%>
    	  	<tr>
				<jsp:include  page="../../component/spaceline.jsp" /> 
	      	</tr>
		</table>