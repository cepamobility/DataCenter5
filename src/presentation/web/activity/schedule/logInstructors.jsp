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
	Collection<UserData> instructors = sys.getActivityReportFacade().getActivityInstructors(userSessionData);
	if(instructors.size() == 1 || (sys.getUserSessionData().isInstructorActivity() && sys.getActivityReportFacade().isInstructorActivities(sys.getUserSessionData()))){
%>		<jsp:include page='log.jsp'/>
<%	}else {
%>
		<script type="text/javascript">
		function doLog(){
			var form = document.forms['log'];
			var list="";
			var elements=form.elements;
			for(i=0;i<elements.length;i++){
				if(elements[i].type.toLowerCase()=="checkbox"){
					if(elements[i].checked){
						if(list==""){
							list=elements[i].value;
						}else{
							list=elements[i].value+","+list;
						}	
					}	
				} 
			}
			form.user.value = list;
			if(list.length > 0){
				form.submit();
			}
		}
		function applyAll(value){
			var form = document.forms['log'];
			var elements=form.elements;
			for(i=0;i<elements.length;i++){
				if(elements[i].type.toLowerCase()=="checkbox"){
					elements[i].checked = (value == 2) ;
				} 
			}
		}
		</script>
		<form name="log" action="layout.jsp">
			<input type="hidden" name="refer" value="/activity/schedule/log.jsp" />
			<input type="hidden" name="user"/>
		<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1">
				<jsp:include  page='<%="../../component/titlecomponent.jsp?title="+messages.getText("activity.tablet.instuctorregister")%>' />
	  		</tr>
    	  	<tr>
				<jsp:include  page="../../component/spaceline.jsp" /> 
	      	</tr>
    	  	<tr>
        		<td valign="top" class="lineone">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr class="guideSearch">
							<td width="19" ><img src="/datacenter/util/imgs/left_top_search.jpg" width="19" height="30" align="bottom"></td>
							<td align="left" class="guideSearch"><%=messages.getText("activity.tablet.selectinstructor") %></td>
							<td width="19" ><img src="/datacenter/util/imgs/right_top_search.jpg" width="19" height="30" align="bottom"></td>
						</tr>
		   				<tr>
							<td class="leftLineSearch">&nbsp;</td>
    						<td>
								<table width="100%" cellspacing="0" cellpadding="0">
						    	  	<tr>
										<jsp:include  page="../../component/spaceline.jsp" /> 
							      	</tr>
									<tr class="lineone">
<%		int half = instructors.size() / 2;
		if(instructors.size() % 2 == 1) 
			half++;
		Iterator<UserData> itInstructors = instructors.iterator();
		for(int i = 0; i < 2; i++) {
			int count = 0;
			boolean lineone = true;
%>										<td width="48%">
											<table width="100%" cellpadding="0" cellspacing="0" align="center">
												<tr>
								                	<td width="5%"	class="guide2"></td>
								                	<td width="95%"	class="guide2"><%=messages.getText("generic.activity.instructor")%></td>
								           		</tr>
<%			while(itInstructors.hasNext() && count < half) {
				UserData instructor = itInstructors.next();
%>												<tr class='<%=(lineone)?"lineone":"linetwo"%>'>
													<td><input type="checkbox" value="<%=instructor.getLoginName()%>"/></td>
													<td align="left"><%=instructor.getFirstName()+" "+instructor.getLastName()%></td>
												</tr>
<%				lineone = !lineone;
				count++;
			}	
%>											</table>
										</td>
<%			if(i == 0) {
%>										<td width="4%"></td>				
<%			}
		}
%>
									</tr>
						    	  	<tr>
										<jsp:include  page="../../component/spaceline.jsp" /> 
							      	</tr>
						    	  	<tr>
										<td colspan="3" align="right">
											<input type="button" class="input" value='<%=messages.getText("generic.messages.none")%>' onclick="applyAll(1);" />
											<input type="button" class="input" value='<%=messages.getText("generic.messages.all.pl")%>' onclick="applyAll(2);" />
											<input type="button" class="input" value='<%=messages.getText("generic.messages.open")%>' onclick="doLog();" />
										</td> 
							      	</tr>
						    	  	<tr>
										<jsp:include  page="../../component/spaceline.jsp" /> 
							      	</tr>
								</table>
								<jsp:include  page='../../component/utilitybox2bottom.jsp' />
					</td>
		  		</tr>
		   	  	<tr>
					<jsp:include  page="../../component/spaceline.jsp" /> 
		      	</tr>
			</table>
		</form>
<%		
	}
%>		