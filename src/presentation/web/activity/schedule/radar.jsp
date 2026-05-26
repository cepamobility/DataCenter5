<%@page language="java" 
	errorPage="../../exception.jsp"
	import="business.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.activity.tables.*"
	import="presentation.translator.web.util.*"
	import="communication.datatype.tables.*"
	import="communication.datatype.*"	
	import="java.util.*"
	import="business.datatype.*"	
	import="communication.administration.user.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>


<%!
	RequestDispatcher dispatcher; 
	Text messages; 
	SystemAccess sys;
	UserSessionData userSessionData;
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	String idActivity, url, idDriver;
	userSessionData = sys.getUserSessionData();
	
	String check = Util.checkPermission(sys,SecurityConstants.CREATE_CEPASCHEDULEACTIVITYREGISTRY);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		messages=sys.getText();

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		idActivity=(String)request.getParameter("idActivity");
		if(idActivity==null){
			idActivity = (String)request.getParameter("id");
			if(idActivity==null){
				idActivity=(String)session.getAttribute("idActivity");			
			}
		}
		idDriver=(String)request.getParameter("idDriver");
		if(idDriver==null){
		    idDriver=(String)session.getAttribute("idDriver");			
		}	
		
		session.setAttribute("url","activity/schedule/sipmleForm.jsp?idActivity="+idActivity+"&idDriver="+idDriver);
		url = "activity/schedule/sipmleForm.jsp?idActivity="+idActivity+"&idDriver="+idDriver;
		userSessionData.setUrl(url);
		
		CEPAActivityScheduleRegistryData registryData = sys.getActivityReportFacade().getCompleteResult(new Integer(idActivity),new Integer(idDriver),sys.getUserSessionData());

		Collection format = new LinkedList();
		format.add(new OptionItem("HTML","HTML"));	    
		format.add(new OptionItem("PDF","PDF"));	    
		session.setAttribute("format",format);
%>	
<html:html>
	<head>
		<LINK REL="StyleSheet" HREF="../../util/css/estilo.css" TYPE="text/css">
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<META http-equiv="Expires" content="-1">		
	</head>

	<body>
		<div align="center"> 
   			<table width="600" border="0" cellspacing="0" cellpadding="0">
				<html:form action = "ActivityRegistrycreate" method="post">
					<html:hidden property="idActivity" value='<%=idActivity%>'/>
					<html:hidden property="idDriver" value='<%=idDriver%>'/>
					<html:hidden property="url" value='<%=url%>'/>
					<html:hidden property="observations" value="" />
	  				<tr class="guide1">
  						<jsp:include  page='<%="../../component/titlecomponent.jsp?title="+messages.getText(registryData.getActivity().getDefinition().getResourceKeyName())%>' />
				  	</tr>
  					<tr>
						<jsp:include  page="../../component/spaceline.jsp" />
				  	</tr>
					<tr>
						<td width="100%" colspan="3">
							<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.activity.driverdata")%>' />
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr class="linetwo">
									<th align="left"> 
										<%=messages.getText("generic.driver.driverdata.name")%>
									</th>
									<td align="right"> 
										<%=registryData.getDriver().getFirstName()+" "+registryData.getDriver().getLastName()%>
									</td>
								</tr>
	      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
	      						<tr  class="linetwo">
			        				<th align="left"> <%=messages.getText("generic.driver.driverdata.birthdate")%></th>
			        				<td align="right">
			        					<%=(registryData.getDriver().getBirthDate() == null) ? "---" : Util.formatDate(registryData.getDriver().getBirthDate()) %>
			        				</td>
								</tr>
							</table>
							<jsp:include  page="../../component/utilityboxbottom.jsp" />
						</td>
					</tr>
  					<tr>
						<jsp:include  page="../../component/spaceline.jsp" />
				  	</tr>
					<tr>
						<td width="100%" colspan="3">
							<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.activity.driverresult")%>' />
							<table width="100%" cellpadding="0" cellspacing="0">
            					<tr class="lineone"> 
			        				<th align="left"> <%=messages.getText("generic.activity.note")%></th>
			        				<td align="right">
			        				
			        				<%
					             		DataTypeReportFacade dataTypeReportFacade = sys.getDataTypeReportFacade();
					     				Collection notes = dataTypeReportFacade.findAll(DataTypeConstants.TYPECEPAACTIVITYNOTE,sys.getUserSessionData());
					             		if (notes != null){ 
					             	%>
					             		<html:select property="note" styleClass="input" value='<%=(registryData == null) ? "" : registryData.getNote()%>'>
					             		
					             		<%
					             			Iterator it = notes.iterator();
					             			while (it.hasNext()){
					             				String activityNote =  ((CEPAActivityNoteDataType) it.next()).getResourceBundleId();					             				
					             		%>
					              			  <html:option value='<%= activityNote %>' styleClass="input"  ><%= messages.getText(activityNote) %></html:option>
					              		<%
					              			}%>
					               		</html:select> 
					               <%
					              		}%> 
                					</td>
                				</tr>
                			</table>
							<jsp:include  page="../../component/utilityboxbottom.jsp" />
                		</td>
                	</tr>
					<tr>
						<jsp:include  page="../../component/spaceline.jsp" />
	  				</tr>
	  				<tr>
  					  	<td height="18" colspan="3" valign="top">
        	  				<div align="right">
								<html:submit styleClass="input"><%=messages.getText("generic.messages.save")%></html:submit>
						 		<html:cancel styleClass="input"><%=messages.getText("generic.messages.cancel")%></html:cancel >
				  			</div>          
					  	</td>
				  	</tr>
				</html:form>
<%		if(registryData.getActivity().getDefinition().getResourceKeyName().
			equals(communication.activity.tables.CEPAActivityConstants.INDUCTION_TRAINING)) {
%>  				<tr>
						<jsp:include  page="../../component/spaceline.jsp" />
	  				</tr>
					<tr>
						<td align="left" class="texts" colspan="3">
							<jsp:include  page="../../component/utilitybox4top.jsp" />
							<html:form action="/InductionTrainingReport">
								<html:hidden property="idActivity" value='<%=idActivity%>'/>
								<html:hidden property="idDriver" value='<%=idDriver%>'/>
								<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
									<tr class="lineone">
										<td align="left" class="lineone">
											<span class="input"><b><%=messages.getText("generic.messages.output")%></b></span>&nbsp;
										</td>
										<td align="left" class="lineone">
											<html:select property="type" styleClass="input" >
												<html:options collection="format" property="value" labelProperty="label"/>
											</html:select>							          				
										</td>
										<td align="right"  class="lineone" width="90%">
											<html:submit styleClass="input" value='<%=messages.getText("generic.messages.consult")%>' />
										</td>
									</tr>
								</table>
							</html:form>	
							<jsp:include  page="../../component/utilitybox4bottom.jsp" />
						</td>
					</tr>
<%		}
%>			</table>
		</div>
	</body>
</html:html>
<%
	}
%>