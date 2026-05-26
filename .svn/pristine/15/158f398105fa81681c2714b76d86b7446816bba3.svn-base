<%@page language="java"
	errorPage="../../exception.jsp"
    import="java.util.*"
    import="business.*"
    import="business.cenapra.*"
    import="communication.language.*"
    import="communication.security.*"
    import="communication.activity.tables.CEPAActivityConstants"
    import="communication.administration.user.*"    
    import="communication.administration.corporation.tables.*"
    import="communication.cenapra.tables.*"
    import="presentation.translator.web.util.*"
    import="presentation.translator.web.cenapra.*"
    import="communication.cenapra.tables.ActivityData"
	import="communication.administration.corporation.tables.ItemData"
	%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%!
	Text messages; RequestDispatcher dispatcher;
	SystemAccess sys;    
    UserSessionData userSessionData;
%>
<%
    sys=(SystemAccess)session.getAttribute("SystemAccess");
    userSessionData=sys.getUserSessionData();
    String check = Util.checkPermission(sys,SecurityConstants.IMESEVI_VIEW_ACTIVITY);
    if(check != null){
        response.sendRedirect(request.getContextPath()+check);
    }else {
        
        dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
        dispatcher.include(request,response);

        messages=sys.getText();       

        ActivityData data = null;
        CENAPRAReportFacade report = sys.getCENAPRAReportFacade();
        if(!Util.empty(request.getParameter("id"))) {
            data = report.getActivity(new Integer(request.getParameter("id")),userSessionData);
        }else {
			if(session.getAttribute("activity") != null) {
                data = report.getActivity(new Integer((String)session.getAttribute("activity")),userSessionData);
            }
        }
        
        if(data != null) {
            int type = CEPAActivityConstants.MULTIPLICADORES;
            if(data.getType().getResourceBundleId().equals(CEPAActivityConstants.CENAPRA_POBLACION_META)) {
                type = CEPAActivityConstants.POBLACION_META;
            }
            if(data.getType().getResourceBundleId().equals(CEPAActivityConstants.CENAPRA_POBLACION_META_OLD)) {
                type = CEPAActivityConstants.POBLACION_META_OLD;
            }
            if(data.getType().getResourceBundleId().equals(CEPAActivityConstants.CENAPRA_FORMADORES)) {
                type = CEPAActivityConstants.FORMADORES;
            }
            if(data.getType().getResourceBundleId().equals(CEPAActivityConstants.CENAPRA_FORMADORES_VIEJO)) {
                type = CEPAActivityConstants.FORMADORES_VIEJO;
            }
            if(data.getType().getResourceBundleId().equals(CEPAActivityConstants.CENAPRA_POBLACION_META_OLD)) {
                type = CEPAActivityConstants.POBLACION_META_OLD;
            }
            if(data.getType().getResourceBundleId().equals(CEPAActivityConstants.CENAPRA_MULTIPLICADORES_OLD)) {
                type = CEPAActivityConstants.MULTIPLICADORES_OLD;
            }
            if(data.getType().getResourceBundleId().equals(CEPAActivityConstants.CENAPRA_MULTIPLICADORES_NEW)) {
                type = CEPAActivityConstants.MULTIPLICADORES_NEW;
            }
            if(data.getType().getResourceBundleId().equals(CEPAActivityConstants.CENAPRA_RELICADORES)) {
                type = CEPAActivityConstants.REPLICADORES;
            }
%>	

<html:html>
	<head>
		<LINK REL=StyleSheet HREF="../../util/css/estilo.css" TYPE="text/css">
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
		<html:form action="/DeleteActivityParticipant">
			<html:hidden property="list" />
			<html:hidden property="activity" value='<%=String.valueOf(data.getId())%>' />
		</html:form>
		<form action="layout.jsp?refer=/cenapra/deleteActivity.jsp" name="delete" method="post">
			<input type="hidden" name="list" value='<%=String.valueOf(data.getId())%>' />		
			<input type="hidden" name="type" value="view" />		
		</form>
		<form action="layout.jsp?refer=/cenapra/addActivityParticipant.jsp" name="addparticipant" method="post">
			<input type="hidden" name="activity" value='<%=String.valueOf(data.getId())%>' />		
		</form>
		<form action="layout.jsp?refer=/cenapra/ActivityCreate.jsp" name="edit" method="post">
			<input type="hidden" name="id" value='<%=String.valueOf(data.getId())%>' />
			<input type="hidden" name="type" value="edit" />
		</form>	
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
        	<tr class="guide1">
        		<%
        			String page11 = "../component/titlecomponent.jsp?title="+messages.getText("cenapra.activity.edit");
        		%>
            	<jsp:include  page='<%=page11%>' />
	    	</tr>
    	    <tr>
        	    <jsp:include  page="../component/spaceline.jsp" />
	        </tr>
	        
	        <jsp:include  page="activityOptions.jsp" />
	  		
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
                                             			<%=messages.getText("cenapra.activity.state")%>
                                            		</td>
                                            		<td align="right" class="linetwo">
                                            			<%=messages.getText(data.getState().getResourceBundleId())%>
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
							<td width="49%" valign="top">
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
				   			<td width="2%"></td>
							<td width="49%" valign="top">
<%				Iterator it = data.getDivOrgsIterator();
				while(it.hasNext()) {		
					DivOrgData divOrgData = (DivOrgData)it.next();
					if(divOrgData.getDivOrgLevels() != null) {
						ItemData item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
%>								<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText(divOrgData.getName())%>' />
								<table width="100%"  cellpadding="0" cellspacing="0">
									<tr class="linetwo">
										<th align="left"> <%=messages.getText(divOrgData.getDivOrgLevels().getName())%> </th>
										<td align="right">
											<%=item1.getName()%>
										</td>
									</tr>
<%			    		if(item1.getSubItems() != null) {
				    		ItemData item2 = (ItemData)item1.getSubItems().getItemIterator().next();
%>									<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
									<tr class="linetwo">
										<th align="left"> <%=messages.getText(item1.getSubItems().getName())%> </th>
										<td align="right">
											<%=item2.getName()%>
										</td>
									</tr>
<%				    		if(item2.getSubItems() != null) {
					    		ItemData item3 = (ItemData)item2.getSubItems().getItemIterator().next();
%>									<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
									<tr class="linetwo">
										<th align="left"> <%=messages.getText(item2.getSubItems().getName())%> </th>
										<td align="right">
											<%=item3.getName()%>
										</td>
									</tr>
<%							}
						}
%>								</table>	
								<jsp:include page="../component/utilityboxbottom.jsp"/>	
<%					}
				}
%>			
				   			</td>
  						</tr>   
						<tr>
							<jsp:include  page="../component/spaceline.jsp" />
						</tr>
                   		<tr>
                    		<td width="100%" colspan="3">
								<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.data.observations")%>' />
								<table width="100%" height="100%" cellpadding="0" cellspacing="0">
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
								<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.participants")%>' />
								<form name="deleteParticipant">
									<table width="100%" height="100%" cellpadding="0" cellspacing="0">
    	                        		<tr>
<%			if(userSessionData.checkPermission(SecurityConstants.IMESEVI_UPDATE_ACTIVITY)) {	
%>						                	<td width="5%"	class="guide2"></td>					                
<%			}
%>						                	<td width="25%"	class="guide2" align="center"><%=messages.getText("cenapra.activity.folio")%></td>					                
						                	<td width="45%"	class="guide2" align="center"><%=messages.getText("cenapra.data.firstname")%></td>					                
						                	<td width="25%"	class="guide2" align="center"><%=messages.getText("cenapra.data.result")%></td>					                
                    	        		</tr>
<%			Collection participants = report.getActivityParticipants(data.getId(),sys.getUserSessionData());
			if(participants.size() == 0) {
%>				        	    		<tr class="linetwo">
					        	        	<td colspan="5"	align="left" class="linetwo">
					        	        		<%=messages.getText("cenapra.activity.noparticipant")%>
					        	        	</td>					                
					            	    </tr>
<%			}else {
    			it = participants.iterator();
    			String lineColor = "linetwo";
    			while(it.hasNext()) {
    			    ParticipantRegistryData registry = (ParticipantRegistryData)it.next();
%>				            			<tr class='<%=lineColor%>'>
<%					if(userSessionData.checkPermission(SecurityConstants.IMESEVI_UPDATE_ACTIVITY)) {	
%>					                		<td width="5%" align="center">
					                			<input type="checkbox" value='<%=String.valueOf(registry.getId())%>'>
						                	</td>
<%					}
%>						                	<td width="25%" align="center">
							                	<%=(registry.getNumber() == null) ? "---" : String.valueOf(registry.getNumber())%>
											</td>
											<td width="45%" align="center">
						                		<a href='<%="layout.jsp?refer=cenapra/ParticipantView.jsp?id="+String.valueOf(registry.getParticipantData().getId())%>' >
							                		<%=registry.getParticipantData().getFirstName()+" "+registry.getParticipantData().getLastName()%>
						            	    	</a>
						                	</td>					                
					    	            	<td width="25%" align="center">
<%					if(registry.isHasResult()) {					    	            	
%>					        	        		<a href='<%="layout.jsp?refer=cenapra/checkListView.jsp?registry="+String.valueOf(registry.getId())+"&type="+type%>' >
						        	        		<%=messages.getText("generic.messages.yes").toUpperCase()%>
						            	    	</a>
<%					}else {
						if(userSessionData.checkPermission(SecurityConstants.IMESEVI_UPDATE_ACTIVITY)) {	
%>					        	        		<a href='<%="layout.jsp?refer=cenapra/checkListForm.jsp?registry="+String.valueOf(registry.getId())+"&type="+type%>' >
						        	        		<%=messages.getText("generic.messages.no").toUpperCase()%>
						            	    	</a>
<%						}else {
%>    											<%=messages.getText("generic.messages.no").toUpperCase()%>
<%						}
					}
%>						            	    </td>					                
						                </tr>
<%  				lineColor = (lineColor.equals("linetwo")) ? "lineone" : "linetwo";
				}
				if(userSessionData.checkPermission(SecurityConstants.IMESEVI_UPDATE_ACTIVITY)) {	
%>  	          						<tr>
        	        						<td colspan="5" class="linetwo" align="right">
        	        							<%
        	        								String onClick1 = "javascript:setAction(document.forms['deleteParticipant'],document.forms['DeleteActivityParticipantForm'],'"+messages.getText("cenapra.deleteactivityparticipant.confirm")+"')";
        	        							%>
				                    			<input name="button2" type="button" onClick="<%=onClick1%>" value='<%=messages.getText("cenapra.activity.deleteparticipant")%>' class="input"/>
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
	   		<jsp:include  page="activityOptions.jsp" />	  		
			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
			</tr>
   		</table>
     </body>

</html:html>	
<%		}
     }  
%>