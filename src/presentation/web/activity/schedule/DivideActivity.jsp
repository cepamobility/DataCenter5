<%@page language="java"
	errorPage="../../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="business.activity.*" 
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.corporation.tables.*"
	import="communication.administration.corporation.tables.ItemData"
	import="communication.administration.user.tables.*"
	import="communication.activity.tables.*"
	import="communication.activity.tables.CEPAActivityConstants"
	import="communication.util.*"	
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*"
	import="communication.administration.user.UserSessionData" 	
	import="communication.driver.tables.DriverData" 		
	import="communication.driver.tables.DriverData" 		
	import="presentation.translator.web.util.Util"
	import="communication.activity.tables.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%! RequestDispatcher dispatcher; 
	
	Text messages; 
	SystemAccess sys; 
	CEPAActivityScheduleData data; 
	ActivityReportFacade activityReport;
	UserSessionData userSessionData;		
	Collection drivers = new LinkedList();
	
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	
	String idActivity = "";
	
	userSessionData =sys.getUserSessionData();
	
	String check = Util.checkPermission(sys,SecurityConstants.VIEW_CEPASCHEDULE_ACTIVITY);
	String check1 = Util.checkPermission(sys,SecurityConstants.MODIFY_CD_ACTIVITY);
	
	if ((check!=null) && (check1!=null))  {	
		response.sendRedirect(request.getContextPath()+check);
	}else {
		messages=sys.getText();

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		idActivity = request.getParameter("activity");
		
		// Refactoring:
		// session.setAttribute("url","activity/schedule/CEPAActivityScheduleView.jsp?id="+idActivity);
		userSessionData.setUrl("activity/schedule/DivideActivity.jsp?idActivity="+idActivity);
		
		if (session.getAttribute("from") != null) { //Variable seteada en la pg. de CommentaryForm y utilizada en VehicleCreateForm
			session.removeAttribute("from");
		}		
		
		if(idActivity != null){	

			activityReport=sys.getActivityReportFacade();
			data = activityReport.findCEPAActivityScheduleByPrimaryKey(new Integer(idActivity),userSessionData); 
			drivers = activityReport.getParticipants(new Integer(idActivity),userSessionData);
			
			
			if(data != null){
%>
	<body>
		<html:form action="/Activitydivide">
		<html:hidden property="id" value="<%=idActivity%>"/>
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  			<tr>
   				<jsp:include  page='<%="../../component/titlecomponent.jsp?colspan=3&title="+messages.getText("web.activity.divide")+" "+messages.getText(data.getType().getResourceBundleId())+" "+messages.getText(data.getCountryName())+" " + Util.formatDate(data.getDate()) %>' />			
    		</tr>
  			<tr>
    			<jsp:include  page="../../component/spaceline.jsp" />
			</tr>
	    	<tr>
	    		<td valign="top">
					<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.administrativedata")%>' />
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td align="left" class="texts"><%=messages.getText("generic.activity.activitydata.date")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%></td>
							<td align="right" class="linetwo"><%=Util.formatDate(data.getDate()) %></td>			
						</tr>										
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr>
							<td align="left" class="texts"><%=messages.getText("generic.activity.activitydata.type")%></td>
							<td align="right" class="linetwo"><%=messages.getText(data.getType().getResourceBundleId())%></td>
						</tr>
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr>
							<td align="left" class="texts"><%=messages.getText("generic.activity.activitydata.city")%></td>
							<td align="right" class="linetwo"><%=data.getCity()%></td>
						</tr>				
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr>
							<td align="left" class="texts"><%=messages.getText("generic.activity.activitydata.state")%></td>
							<td align="right" class="linetwo"><%=messages.getText(data.getState().getResourceBundleId())%></td>
						</tr>
					</table>
					<jsp:include page="../../component/utilityboxbottom.jsp"/>
				</td>
			</tr>
	  		<tr>
				<jsp:include  page="../../component/spaceline.jsp" />
	  		</tr>   
	  		<tr>
				<td class="texts"><%=messages.getText("activity.divide.messagepre") %></td>
	  		</tr>   
	  		<tr>
				<jsp:include  page="../../component/spaceline.jsp" />
	  		</tr>   
<%				HashMap activities = data.divide(drivers);
				Iterator keys = activities.keySet().iterator();
				int index = 1;
				while(keys.hasNext()) {
					CEPAActivityScheduleData newActivity = (CEPAActivityScheduleData)activities.get(keys.next());
					Iterator it = newActivity.getDivOrgsIterator();
%>			
			<tr>
				<td valign="top">
					<jsp:include  page='<%="../../component/utilitybox2top.jsp?title="+messages.getText("module.activity.name")+" "+index%>' />
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
<%					while(it.hasNext()) {		
						DivOrgData divOrgData = (DivOrgData)it.next();
						if(divOrgData.getDivOrgLevels() != null) {
							ItemData item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
							String pageTitulo = "<b>"+messages.getText(divOrgData.getName())+":</b> "+item1.getName();
		    				if(item1.getSubItems() != null) {
								ItemData item2 = (ItemData)item1.getSubItems().getItemIterator().next();
								pageTitulo += " / " + item2.getName();
								if(item2.getSubItems() != null) {
									ItemData item3 = (ItemData)item2.getSubItems().getItemIterator().next();
									pageTitulo += " / " + item3.getName();
								} // end if(item2.getSubItems() != null)
							} // end if(item1.getSubItems() != null) 
%>			
						<tr>
							<td class="lineone"><%=pageTitulo%></td>
						</tr>   
<%						}
					}
%>
						<tr>
							<td class="lineone">&nbsp;</td>
				  		</tr>   
						<tr>
							<td valign="top">
								<jsp:include  page='<%="../../component/utilitybox2top.jsp?title="+messages.getText("generic.data.driver.pl")%>' />
								<table width="100%" cellpadding="0" cellspacing="0" border="0">
<%					Iterator drivers = newActivity.getDrivers().iterator();
					while(drivers.hasNext()) {
						DriverData driver = (DriverData)drivers.next();
%>									<tr>
										<td class="lineone"><%=driver.getFirstName()+" "+driver.getLastName()%></td>
									</tr>   
<%					}

%>								</table>
								<jsp:include page="../../component/utilitybox2bottom.jsp"/>
							</td>
						</tr>
					</table>
					<jsp:include page="../../component/utilitybox2bottom.jsp"/>
				</td>
			</tr>
			<tr>
				<jsp:include  page="../../component/spaceline.jsp" />
	  		</tr>   
<%					index++;
				}
%>		
       		<tr>
         		<jsp:include  page="../../component/spaceline.jsp" />
       		</tr>
       		<tr>
             	<td height="18" colspan="3" valign="top" align="right">
           			<html:submit styleClass="input"> <%=messages.getText("generic.messages.confirm")%> </html:submit>                                                  
           			<html:cancel styleClass="input"> <%=messages.getText("generic.messages.cancel")%> </html:cancel>                                                  
 		        </td>
     		</tr>                                         
		</table>
		</html:form>
<%			}else {
%>	
		<jsp:include  page='<%="/util/allowedData.jsp?message=activity.error.access.activity" %>'/>
<%			}
%>		
	</body>
<%
		}else{
			dispatcher = request.getRequestDispatcher("/activity/schedule/CEPAActivityScheduleList.jsp");
			dispatcher.forward(request,response);
		}
	}
%>	
