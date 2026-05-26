 <%@page language="java" 
	errorPage="../../exception.jsp"
	import="java.util.*"
	import="business.SystemAccess"
	import="business.activity.*"
	import="business.datatype.*"
	import="business.administration.user.*"
	import="communication.datatype.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.datatype.tables.*"
	import="communication.administration.user.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.util.ListResult"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.ActivityTypeDataType"
	import="communication.administration.user.UserSessionData"  	
	import="communication.activity.tables.*" 
	
%>  

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData = sys.getUserSessionData();
	String check = Util.checkPermission(sys,SecurityConstants.VIEW_CEPASCHEDULE_ACTIVITY_LIST);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		session.setAttribute("url","activity/schedule/printForms.jsp");

		RequestDispatcher dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
	
		Text messages=sys.getText();
		
		DataTypeReportFacade datatypeReportFacade = sys.getDataTypeReportFacade();
%>		

		<script language="JavaScript" type="text/javascript">
		function popupform(href){	
			window.open(href, '', 'height=400,width=600,scrollbars=yes,left=(screen.width  - 600)/2;');	
		}
		</script>
		<table width="768" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1">
				<jsp:include  page='<%="../../component/titlecomponent.jsp?title="+messages.getText("web.activity.activitylist.systemactivities")%>' />
	  		</tr>
    	  	<tr>
				<jsp:include  page="../../component/spaceline.jsp" /> 
	      	</tr>
    	  	<tr>
        		<td valign="top" width="100%" >
					<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.search")%>' />
						<table width="100%"  border="0" cellpadding="0" cellspacing="0">
<%			Collection types = datatypeReportFacade.findAll(DataTypeConstants.TYPECEPAACTIVITY,userSessionData);
			Collections.sort((List)types);
			Iterator it = types.iterator();
           	while(it.hasNext()){
              	GeneralDTData itemType=(GeneralDTData)it.next();
           		if(itemType.getResourceBundleId().trim().equals("datatype.scheduledactivitytype.commentarydrive")) {
                   	if(userSessionData.getFilter().getCorporationId().equals(new Integer(CorporationData.MONSANTO))) {
                   		if(userSessionData.checkPermission(SecurityConstants.CREATE_CD_ACTIVITY)) {
%>                      	<tr>
								<td>	
									<a href="javascript:popupform('/datacenter/activity/print/VehicleCheck.jsp')">
										<%=itemType.getResourceBundleId()%>
									</a>
								</td>
							</tr>				
<%						}
                   	}
               	}else if (itemType.getResourceBundleId().trim().equals("datatype.scheduledactivitytype.commentarydriveother")) {
					if(!userSessionData.getFilter().getCorporationId().equals(new Integer(CorporationData.MONSANTO))) {
						if(userSessionData.checkPermission(SecurityConstants.CREATE_CD_ACTIVITY)) {
%>                      	<tr>
								<td>	
									<a href="javascript:popupform('/datacenter/activity/print/TranningOnTheJob.jsp')">
										<%=itemType.getResourceBundleId()%>
									</a>
								</td>
							</tr>				
<%						}
                  	}
				}else {
%>                      	<tr>
								<td>	
									<a href="javascript:popupform('/datacenter/activity/print/TranningOnTheJob.jsp')">
										<%=itemType.getResourceBundleId()%>
									</a>
								</td>
							</tr>				
<%				}
           	}
%>
				    	  	<tr>
								<jsp:include  page="../../component/spaceline.jsp" /> 
					      	</tr>
							<tr class="texts">
								<td align="right" colspan="3">
									<input name="button" type="submit" value='<%=messages.getText("generic.messages.search")%>' class="input"/>
								</td>
							</tr>	
						</table>
   					<jsp:include page="../../component/utilityboxbottom.jsp"/>	
			    </td> 
      		</tr>
	  </table>
<%		}
%>
