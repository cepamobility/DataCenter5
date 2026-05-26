<%@page language="java"
	errorPage="../exception.jsp" 
    import="java.util.*"
    import="business.*"
    import="business.activity.*"
    import="communication.language.*"
    import="communication.security.*"
    import="communication.activity.tables.CEPAActivityConstants"
    import="communication.administration.user.*"    
    import="communication.administration.corporation.tables.*"
    import="presentation.translator.web.util.*"
%>
<%@page import="communication.cepasystem.tables.ActivityData"%>
<%@page import="business.activity.ActivityReportFacade"%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>



<%@page import="communication.cepasystem.tables.CepaSystemRegistryData"%><html:html>
	<head>
		<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	</head>
	<script type="text/javascript">
		function setAction(form1,form2,message){

			var elements=form1.elements;
			var length=elements.length;
			var i;
			var valueCheckboxParamList="";
			for(i=0;i<length;i++){
				var element=elements[i];
				if(element.type.toLowerCase()=="checkbox"){
					if(element.checked){
						if(valueCheckboxParamList==""){
							valueCheckboxParamList=element.value;
						}
						else{
							valueCheckboxParamList=element.value+","+valueCheckboxParamList;
						}	
					}	
				} 
			
			}
			if(valueCheckboxParamList.length>0){
				form2.list.value=valueCheckboxParamList;
				if(confirm(message)) {
					form2.submit();
				}
			}
		}
	</script>
	<body>
<%!
    Text messages; SystemAccess sys; 
    UserSessionData userSessionData;
    RequestDispatcher dispatcher;
%>
<%
   
    sys=(SystemAccess)session.getAttribute("SystemAccess");
    userSessionData=sys.getUserSessionData();
    String check = Util.checkPermission(sys,SecurityConstants.IMESEVI_VIEW_ACTIVITY);
    if(check!=null)  {
        response.sendRedirect(request.getContextPath()+check);
    }else {
        
        dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
        dispatcher.include(request,response);

        messages=sys.getText();       

        ActivityData data = null;
        String id = null;
        ActivityReportFacade report = sys.getActivityReportFacade();
        if(!Util.empty(request.getParameter("id"))) {
        	id = (String)request.getParameter("id");
            data = report.getCepaSystemActivity(new Integer(request.getParameter("id")),userSessionData);
        }else {
			if(session.getAttribute("activity") != null) {
				id = (String)session.getAttribute("activity");
                data = report.getCepaSystemActivity(new Integer((String)session.getAttribute("activity")),userSessionData);
            }
        }
        
        if(data != null) {
%>		
		<html:form action="/DeleteCepaSystemActivityDriver">
			<html:hidden property="list" />
			<html:hidden property="activity" value='<%=String.valueOf(data.getId())%>' />
		</html:form>
		<form action="layout.jsp?refer=/cepasystem/deleteActivity.jsp" name="delete" method="post">
			<input type="hidden" name="list" value='<%=String.valueOf(data.getId())%>' />		
			<input type="hidden" name="type" value="view" />		
		</form>
		<form action="layout.jsp?refer=/cepasystem/addActivityDriver.jsp" name="adddriver" method="post">
			<input type="hidden" name="activity" value='<%=String.valueOf(data.getId())%>' />		
		</form>
		<form action="layout.jsp?refer=/cepasystem/ActivityCreate.jsp" name="edit" method="post">
			<input type="hidden" name="id" value='<%=String.valueOf(data.getId())%>' />
			<input type="hidden" name="type" value="edit" />
		</form>	
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
        	<tr class="guide1">
            	<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("cenapra.activity.edit")%>' />
	    	</tr>
    	    <tr>
        	    <jsp:include  page="../component/spaceline.jsp" />
	        </tr>
	        
	        <jsp:include  page="activityOptions.jsp?id=<%=id%>" />
	  		
			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
			</tr>
    	    <tr>
        	    <td valign="top">           
            	 	<table width="100%" cellpadding="0" cellspacing="0" border="0">             
                		<tr>
                 			<td valign="top" width="100%" colspan="3">
                    			<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.administrativedata")%>' />
                        		<table width="100%" border="0" cellpadding="0" cellspacing="0">
                        			<tr>
                            			<td width="100%" align="right" valign="top">
                                    		<table width="100%" border="0" cellpadding="0" cellspacing="1">
                                       			<tr>
                                            		<td align="left" class="texts">
                                                		<%=messages.getText("generic.activity.activitydata.date")+" ("+messages.getText("generic.messages.dateformat")+")"%>
                                            		</td>
                                            		<td align="right" class="linetwo">
                                            			<%=Util.formatDate(data.getDate())%>
			          								</td>
												</tr>
                                           		<tr><td height="10" colspan="2"><img src="x" width="1" height="1"></td></tr>
		          								<tr>
        		    								<th align="left" class="texts">
               											 <%=messages.getText("cenapra.activity.instructor")%>
               										</th>
           											 <td align="right" class="linetwo">
                                            			<%=(data.getInstructor() == null) ? "" : data.getInstructor()%>
		            								</td>
        		 								 </tr>                                       
                                           		<tr><td height="10" colspan="2"><img src="x" width="1" height="1"></td></tr>
		          								<tr>
        		    								<th align="left" class="texts">
               											 <%=messages.getText("generic.activity.activitydata.city")%>
               										</th>
           											 <td align="right" class="linetwo">
                                            			<%=data.getCity()%>
		            								</td>
        		 								 </tr>                                       
                                           		<tr><td height="10" colspan="2"><img src="x" width="1" height="1"></td></tr>
                		                        <tr>
                        		                    <td align="left" class="texts">
                                             			<%=messages.getText("generic.activity.activitydata.state")%>
                                            		</td>
                                            		<td align="right" class="linetwo">
                                            			<%=messages.getText(data.getState().toString())%>
                                          			</td>
                                        		</tr> 
                                       		</table>   
                             			</td>
                          			</tr>
                        		</table>           
                        		<jsp:include page="../component/utilityboxbottom.jsp"/>   
                			</td>               
                		</tr>
						<tr>
							<jsp:include  page="../component/spaceline.jsp" />
						</tr>
                		<tr>
							<td width="100%" valign="top">
								<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.divgeo")%>' />
<%				if(data.getCountry() != null) {
			    	GeoItemData geoItem1 = (GeoItemData)data.getCountry().getItemIterator().next();
%>								<table width="100%" height="100%" cellpadding="0" cellspacing="0">
									<tr class="linetwo">
										<th align="left"> <%=messages.getText(data.getCountry().getResourceKey())%> </th>
										<td align="right">
											<%=(geoItem1.getSubItems() != null) ? geoItem1.getName() : messages.getText(geoItem1.getName()) %>
										</td>
									</tr>
<%			    	if(geoItem1.getSubItems() != null) {
				    	GeoItemData geoItem2 = (GeoItemData)geoItem1.getSubItems().getItemIterator().next();
%>									<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
									<tr class="linetwo">
										<th align="left"> <%=messages.getText(geoItem1.getSubItems().getResourceKey())%> </th>
										<td align="right">
											<%=(geoItem2.getSubItems() != null) ? geoItem2.getName() : messages.getText(geoItem2.getName()) %>
										</td>
									</tr>
<%				    	if(geoItem2.getSubItems() != null) {
					    	GeoItemData geoItem3 = (GeoItemData)geoItem2.getSubItems().getItemIterator().next();
%>									<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
									<tr class="linetwo">
										<th align="left"> <%=messages.getText(geoItem2.getSubItems().getResourceKey())%> </th>
										<td align="right">
											<%=messages.getText(geoItem3.getName())%>
										</td>
									</tr>
<%						}
					}
				}
%>								</table>	
				   				<jsp:include page="../component/utilityboxbottom.jsp"/>	
				   			</td>
  						</tr>   
						<tr>
							<jsp:include  page="../component/spaceline.jsp" />
						</tr>
                   		<tr>
                    		<td width="100%" colspan="3">
								<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.data.observations")%>' />
								<table width="100%" height="100%" valign="top" cellpadding="0" cellspacing="0">
                            		<tr class="linetwo">
                                		<td colspan="2">
                                    		<%=data.getObservations()%>
                                		</td>
                            		</tr>
								</table>
								<jsp:include  page="../component/utilityboxbottom.jsp" />
							</td>
						</tr>
   						<tr>
            				<jsp:include  page="../component/spaceline.jsp" />
    					</tr>
                   		<tr>
                    		<td width="100%" colspan="3">
								<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cepasystem.drivers")%>' />
								<form name="deleteParticipant">
									<table width="100%" height="100%" valign="top" cellpadding="0" cellspacing="0">
    	                        		<tr>
<%			if(userSessionData.checkPermission(SecurityConstants.ADMINISTRATOR)) {	
%>						                	<td width="5%"	class="guide2"></td>					                
<%			}
%>						                	<td width="60%"	class="guide2" align="center"><%=messages.getText("driver.data.firstname")%></td>					                
                    	        		</tr>
<%			Collection participants = report.getCepaSystemParticipants(data.getId(),sys.getUserSessionData());
			if(participants.size() == 0) {
%>				        	    		<tr class="linetwo">
					        	        	<td colspan="5"	align="left" class="linetwo">
					        	        		<%=messages.getText("cepasystem.activity.nodriver")%>
					        	        	</td>					                
					            	    </tr>
<%			}else {
    			Iterator it = participants.iterator();
    			String lineColor = "linetwo";
    			while(it.hasNext()) {
    			    CepaSystemRegistryData registry = (CepaSystemRegistryData)it.next();
%>				            			<tr class='<%=lineColor%>'>
<%					if(userSessionData.checkPermission(SecurityConstants.IMESEVI_UPDATE_ACTIVITY)) {	
%>					                		<td width="5%" align="center">
					                			<input type="checkbox" value='<%=String.valueOf(registry.getId())%>'>
						                	</td>
<%					}
%>						                	<td width="50%" align="center">
						                		<a href='<%="layout.jsp?refer=cepasystem/ActivityDriverView.jsp?id="+String.valueOf(registry.getDriverData().getId())%>' >
							                		<%=registry.getDriverData().getFirstName()+" "+registry.getDriverData().getLastName()%>
						            	    	</a>
						                	</td>					                
						                </tr>
<%  				lineColor = (lineColor.equals("linetwo")) ? "lineone" : "linetwo";
				}
				if(userSessionData.checkPermission(SecurityConstants.ADMINISTRATOR)) {	
%>  	          						<tr>
        	        						<td colspan="5" class="linetwo" align="right">
				                    			<input name="button2" type="button" onClick='<%="javascript:setAction(document.forms['deleteParticipant'],document.forms['DeleteCepaSystemActivityDriverForm'],'"+messages.getText("generic.messages.delete")+"');"%>' value='<%=messages.getText("generic.messages.delete")%>' class="input"/>
        									</td>
      									</tr>
<%				}
			}
%>                      			</table>
								</form>
								<jsp:include  page="../component/utilityboxbottom.jsp" />
							</td>
						</tr>
   					</table>
   				</td>
   			</tr>
	   		<tr>
	   			<jsp:include  page="../component/spaceline.jsp" />
	   		</tr>
	  		 <jsp:include  page="activityOptions.jsp?id=<%=id%>" />
			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
			</tr>
   		</table>	
<%		}
	}   
%>   
  	</body>
</html:html>  
