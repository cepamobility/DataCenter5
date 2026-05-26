 <%@page language="java" 
	errorPage="../exception.jsp" 
	import="java.util.*"
	import="business.SystemAccess"
	import="communication.administration.user.UserSessionData"
	import="communication.language.Text"
	import="communication.cenapra.tables.ReportActivityData"	
%>  

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData = sys.getUserSessionData();
	Text messages=sys.getText();

	Collection result = sys.getCENAPRAReportFacade().getActivityResume(sys.getUserSessionData(),sys.getText());
	Collections.sort((List)result);		
		
%>		
		
<%@page import="communication.administration.corporation.tables.CorporationData"%><script type="text/javascript">
			function viewState() {
				var combo = document.getElementById("state");
				var visibility = "none";
				var value = combo.options[combo.options.selectedIndex].value; 
				if(value == 0) {
					visibility = "block";
				}
<%	Iterator it = result.iterator();
	while(it.hasNext()) {
		ReportActivityData activity = (ReportActivityData)it.next();
%>				document.getElementById('<%="estado"+String.valueOf(activity.getStateId())%>').style.display = visibility;		
<%	}
%>
				if(value > 0) {
					document.getElementById('estado'+value).style.display = "block";
				}
			}
		</script>
		
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td colspan="3" class="title1"><%=messages.getText("cenapra.start.title")%></td>
	  		</tr>
    	  	<tr>
				<jsp:include  page="../component/spaceline.jsp" /> 
	      	</tr>
    	  	<tr>
				<td colspan="3" class="linetwo" align="left">
					<%=messages.getText("corporation.imesevi.state")+": "%>
					<select id="state" name="state" onchange="viewState();">
						<option value="0"><%=messages.getText("generic.messages.all.pl")%></option>		
<%	it = result.iterator();
	while(it.hasNext()) {
		ReportActivityData activity = (ReportActivityData)it.next();
%>						<option value='<%=String.valueOf(activity.getStateId())%>'><%=activity.getStateName()%></option>		
<%	}
%>					</select>
				</td>
	      	</tr>
    	  	<tr>
				<jsp:include  page="../component/spaceline.jsp" /> 
	      	</tr>
<%	it = result.iterator();
	while(it.hasNext()) {
		ReportActivityData activity = (ReportActivityData)it.next();
%>	      	
    	  	<tr>
        		<td valign="top" width="60%" >
        			<div id='<%="estado"+String.valueOf(activity.getStateId())%>'>
        			<table width="100%" border="1">
        				<tr>
        					<td colspan="3" class="title2">
        						<%=activity.getStateName()%>
        					</td>
        				</tr>
        				<tr>
        					<td width="50%" class="guide2" align="left"><%=messages.getText("cenapra.activity.type")%></td>
        					<td width="25%" class="guide2" align="center"><%=messages.getText("generic.data.activity.pl")%></td>
        					<td width="25%" class="guide2" align="center"><%=messages.getText("cenapra.participants")%></td>
        				</tr>
<%		int top = (sys.getCorporationId() == CorporationData.CENAPRA) ? 4 : 3;
		for(int i = 0; i < top; i++) {
			int k = i - 1;
			switch(i) {
				case 0:
					k = 2;
					break;
				case 3:
					k = 3; 
			}
%>						<tr>
        					<td class="linetwo" align="left"><%=messages.getText(activity.getTypeName(k))%></td>
        					<td class="linetwo" align="center"><%=String.valueOf(activity.getActivities()[k])%></td>
        					<td class="linetwo" align="center"><%=String.valueOf(activity.getParticipants()[k])%></td>
        				</tr>
<%		}
%>        				
        			</table>
        			</div>
				</td>
      		</tr>
    	  	<tr>
				<jsp:include  page="../component/spaceline.jsp" /> 
	      	</tr>
<%	}
%>      		
	  </table>
