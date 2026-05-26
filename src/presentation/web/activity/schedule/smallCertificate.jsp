<%@page import="communication.administration.corporation.tables.CorporationData"%>
<%@page import="communication.alert.AlertConstants"%>
<%@page import="communication.driver.tables.DriverData"%>
<%@page language="java" 
	errorPage="../exception.jsp" 
	import="business.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="presentation.translator.web.vehicle.*"
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.ActivityTypeDataType"
	import="business.vehicle.VehicleReportFacade"
	import="java.util.*"	
	import="org.apache.struts.action.DynaActionForm"
	import="communication.activity.tables.*"
	import="business.vehicle.VehicleReportFacadeBean"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%!
	RequestDispatcher dispatcher; 
	Text messages; SystemAccess sys;
	UserSessionData userSessionData; 
	VehicleCheckData vehicleCheck;
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();	
	
	String check = Util.checkPermission(sys,SecurityConstants.VIEW_CEPASCHEDULE_ACTIVITY);	
	
	if (check!=null) { 
		response.sendRedirect(request.getContextPath()+check);	
	}else {
		messages=sys.getText();
	
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
	dispatcher.include(request,response);

		String activityId = (String)request.getParameter("activity");

		ActivityReportFacade activityReport=sys.getActivityReportFacade();
		CEPAActivityScheduleData data = activityReport.findCEPAActivityScheduleByPrimaryKey(new Integer(activityId),userSessionData); 
		
		if(data != null) {
	Collection drivers = activityReport.getParticipants(new Integer(activityId),userSessionData);
		
	String folder = "";
	    	switch(sys.getCorporationId()) {
	    		case CorporationData.DOW:
	    			folder = AlertConstants.TEMP_DIRECTORY_CARTERINHA;
	    			break;
	    		case CorporationData.FORESTALORIENTAL:
	    			folder = AlertConstants.UPM_DIRECTORY_CARNET;
	    			break;
	    		case CorporationData.MONTES_DE_PLATA:
	    			folder = AlertConstants.MDP_DIRECTORY_CARNET;
	    			break;
	    	}
%>	


<html:html>
	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script type="text/javascript">
		function send(){
			var list = "";		
			var form = document.forms['ActivitySmallCertificateForm'];
			var elements = form.elements;
			for(i = 0; i < elements.length; i++){
				var element=elements[i];
				if(element.type.toLowerCase()=="checkbox"){
					if(element.checked){
						if(list==""){
							list=element.value;
						}else{
							list=element.value+","+list;
						}	
					}	
				} 		
			}
			if(list.length > 0){
				form.list.value=list;
				form.submit();
			}
		}
		function changeSelected(selectionNumber) {
			var elements=document.forms['ActivitySmallCertificateForm'].elements;
			for(i = 0; i < elements.length; i++) {
				var element=elements[i];
				if(element.type.toLowerCase()=="checkbox"){
					if(selectionNumber == 1) {
						element.checked = true;
					}else if(selectionNumber == 2) {
						element.checked = false;
					}else {
						element.checked = !element.checked;
					}
				}
			}
		}
		</script>
	</head>

	<body>
		<table width="700" border="0" cellspacing="2" cellpadding="2" align="center">  
			<tr>
				<jsp:include  page='<%="../../component/titlecomponent.jsp?title="+messages.getText("generic.messages.smallcertificate")%>' />
  			</tr>
			<tr> 
				<td height="40" colspan="3" valign="middle">
					<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.activity.activitydata")%>' />
					<table width="100%" height="60" border="0" cellpadding="2" cellspacing="2">
	   	 				<tr> 
	       					<td width="50%" align="left" class="texts">        
	   							<%=messages.getText("generic.activity.date")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
							</td>
							<td width="50%" align="right" class="linetwo">
								<%=Util.formatDate(data.getDate())%>	
							</td>
	   					</tr>
	     				<tr> 
	        				<td width="50%" align="left" class="texts">        
	       						<%= messages.getText("generic.driver.name")%>
							</td>
							<td width="50%" align="right" class="linetwo">		
								<%=messages.getText(data.getType().getResourceBundleId()) %>                                     
	        				</td>
	     				</tr>
	     				<tr> 
	        				<td width="50%" align="left" class="texts">        
	       						<%= messages.getText("generic.vehicle.vehicledata.plate")%>
							</td>
							<td width="50%" align="right" class="linetwo">		
								<%=data.getCity()%>                                     
	        				</td>
	     				</tr>
					</table>
					<jsp:include  page="../../component/utilityboxbottom.jsp" />
				</td>
			</tr>  
	    	<tr>
	    		<td colspan="3" valign="top">
					<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.drivers")%>' />
					<table width="100%" height="60" border="0" cellpadding="2" cellspacing="2">
<%   	  				String[][] links = (String[][]) sys.getUserSessionData().getValue();
						String line = "linetwo";
   						for(int i = 0; i < links.length; i++) {
%>							<tr class="<%=line%>">
								<td width='100%' align="left" height="25">
									<a href='<%=links[i][1]%>'><%=links[i][0]%></a>
								</td>
							</tr>
<%							line = (line.equals("linetwo")) ? "lineone" : "linetwo";
						}
%>
					</table>
					<jsp:include  page="../../component/utilityboxbottom.jsp" />
				</td>
			</tr>  
		</table>
	</body>
</html:html>
<%

		}
	}
%>
