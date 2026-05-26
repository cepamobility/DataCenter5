<%@page language="java" 
	errorPage="../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="business.cenapra.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.activity.tables.*"
	import="communication.cenapra.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
 	import="communication.administration.corporation.tables.ItemData"
 	import="presentation.translator.web.cenapra.*"
%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html>


<%!
	RequestDispatcher dispatcher; 
	String context;
	Text messages; SystemAccess sys;
%> 

<%
 	sys = (SystemAccess)session.getAttribute("SystemAccess");
  	String check = Util.checkPermission(sys,SecurityConstants.IMESEVI_VIEW_PARTICIPANT);
  	if(check!=null) {
  		response.sendRedirect(request.getContextPath()+check);
  	}else {

  		context=request.getContextPath();
  	
  		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
  		dispatcher.include(request,response);
  		messages=sys.getText();

  		String id = request.getParameter("id");
  		if(Util.empty(id)) {
  		    id = (String)session.getAttribute("id");
  		}
  		if(!Util.empty(id)) {
  	session.setAttribute("url","cenapra/ParticipantView.jsp?id="+id);
  	CENAPRAReportFacade report = sys.getCENAPRAReportFacade();
  	ParticipantData participant = report.findParticipantByPrimaryKey(new Integer(id),sys.getUserSessionData());
  	if(participant != null) {
 %>

	<head>
		<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	</head>
	<body bgcolor="#FFFFFF">
		<form name="delete" action="layout.jsp?refer=/cenapra/deleteParticipant.jsp"  method="post">
			<input type="hidden" name="list" value='<%=String.valueOf(participant.getId())%>' />
     		<input type="hidden" name="type" value="view" />
		</form>
		<form action="layout.jsp?refer=/cenapra/ParticipantCreate.jsp" name='edit' method="post">
			<input type="hidden" name="id" value='<%=String.valueOf(participant.getId())%>' />
			<input type="hidden" name="type" value="edit" />
		</form>	
		<html:form action="/ParticipantCreate">
			<table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
  				<tr class="guide1">
  					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("cenapra.messages.viewparticipant")%>' />
			  	</tr>
		   		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
		  		</tr>
		  		
		  		<jsp:include  page="participantOptions.jsp" />
		  		
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
				<tr>
					<td width="50%" valign="top">
                    	<table width="100%" cellpadding="0" cellspacing="0" border="0">
                    		<tr>
                     			<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.participant.personaldata")%>' />
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" >
			      						<tr  class="linetwo">
			        						<th align="left"> <%=messages.getText("cenapra.data.firstname")%></th>
			         						<td align="right"><%=participant.getFirstName()%></td>
			      						</tr>
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr class="linetwo">
			        						<th align="left"><%=messages.getText("cenapra.data.lastname")%></th>
						        			<td align="right"><%=participant.getLastName()%></td>
			      						</tr>
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr  class="linetwo">
			        						<th align="left"> <%=messages.getText("cenapra.data.birthdate")+" (dd/mm/aaaa)"%></th>
			        						<td align="right" valign="middle">
												<%=(participant.getBirthDate() == null) ? "---" : Util.formatDate(participant.getBirthDate())%>
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
								<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.participant.populationgroup")%>' />
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" >
			      						<tr  class="linetwo">
			        						<th align="left"> <%=(participant.getGroup() == null) ? "---" : messages.getText(participant.getGroup().getResourceBundleId())%></th>
			      						</tr>
<%
	if(sys.getCorporationId() == CorporationData.IMESEVI_UY) {
%>							<tr class="linetwo">
										<th align="left"> <%=messages.getText("cenapra.data.otherprofesion")%></th>
										</tr>
										<tr class="linetwo">
											<td align="left" colspan="5">
												<%=(Util.empty(participant.getOthergroup())) ? "" : participant.getOthergroup()%>
											</td>
										</tr>
<%
	}
%>			      					</table>
									<jsp:include  page="../component/utilityboxbottom.jsp" />
			      				</td>
			      			</tr>
							<tr>
								<jsp:include  page="../component/spaceline.jsp" />
							</tr>
							<tr>
								<td width="100%" valign="top">
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.data.contactdata")%>' />
										<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" >
<%
	if(sys.getCorporationId() == CorporationData.CENAPRA) {
%>											<tr class="linetwo">
				        						<th align="left"> <%=messages.getText("cenapra.data.state")%></th>
         										<td align="right"><%=(participant.getState() == null) ? "---" : participant.getState()%></td>
         									</tr>
											<tr class="linetwo">
        										<th align="left"> <%=messages.getText("cenapra.data.delegation")%></th>
         										<td align="right"><%=(participant.getDelegation() == null) ? "---" : participant.getDelegation()%></td>
         									</tr>
<%
	}
%>											<tr class="linetwo">
												<th align="left"> <%=messages.getText("cenapra.data.city")%></th>
         										<td align="right"><%=(participant.getCity() == null) ? "---" : participant.getCity()%></td>
											</tr>
<%
	if(sys.getCorporationId() == CorporationData.CENAPRA) {
%>											<tr class="linetwo">
				        						<th align="left"> <%=messages.getText("cenapra.data.postalcode")%></th>
         										<td align="right"><%=(participant.getPostalcode() == null) ? "---" : participant.getPostalcode()%></td>
											</tr>
<%
	}
%>											<tr class="linetwo">
        										<th align="left"> <%=messages.getText("cenapra.data.address")%></th>
         										<td align="right"><%=(participant.getAddress() == null) ? "---" : participant.getAddress()%></td>
				         					</tr>	
											<tr class="linetwo">
        										<th align="left"> <%=messages.getText("cenapra.data.phone")%></th>
         										<td align="right"><%=(participant.getPhone() == null) ? "---" : participant.getPhone()%></td>
				         					</tr>
											<tr class="linetwo">
				        						<th align="left"> <%=messages.getText("cenapra.data.movile")%></th>
         										<td align="right"><%=(participant.getMovile() == null) ? "---" : participant.getMovile()%></td>
											</tr>
											<tr class="linetwo">
        										<th align="left"> <%=messages.getText("cenapra.data.email")%></th>
         										<td align="right"><%=(participant.getEmail() == null) ? "---" : participant.getEmail()%></td>
         									</tr>
										</table>
									<jsp:include  page="../component/utilityboxbottom.jsp" />
								</td>
							</tr>
						</table>
					</td>
	                <td width="16">&nbsp;</td>
                	<td width="50%" valign="middle">
                    	<table width="100%" cellpadding="0" cellspacing="0" border="0">
                    		<tr>
                     			<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.divgeo")%>' />
<%
	if(participant.getCountry() != null) {
	    	GeoItemData geoItem1 = (GeoItemData)participant.getCountry().getItemIterator().next();
%>									<table width="100%" height="100%" cellpadding="0" cellspacing="0">
										<tr class="linetwo">
											<th align="left"> <%=messages.getText(participant.getCountry().getResourceKey())%> </th>
											<td align="right">
												<%=(geoItem1.getSubItems() != null) ? geoItem1.getName() : messages.getText(geoItem1.getName())%>
											</td>
										</tr>
<%
	if(geoItem1.getSubItems() != null) {
		    	GeoItemData geoItem2 = (GeoItemData)geoItem1.getSubItems().getItemIterator().next();
%>										<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
										<tr class="linetwo">
											<th align="left"> <%=messages.getText(geoItem1.getSubItems().getResourceKey())%> </th>
											<td align="right">
												<%=(geoItem2.getSubItems() != null) ? geoItem2.getName() : messages.getText(geoItem2.getName())%>
											</td>
										</tr>
<%
	if(geoItem2.getSubItems() != null) {
	    	GeoItemData geoItem3 = (GeoItemData)geoItem2.getSubItems().getItemIterator().next();
%>										<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
										<tr class="linetwo">
											<th align="left"> <%=messages.getText(geoItem2.getSubItems().getResourceKey())%> </th>
											<td align="right">
												<%=messages.getText(geoItem3.getName())%>
											</td>
										</tr>
<%
	}
	}
		}
%>									</table>	
					   				<jsp:include page="../component/utilityboxbottom.jsp"/>	
								</td>
							</tr>		
							<tr>
								<jsp:include  page="../component/spaceline.jsp" />
							</tr>
<%
	Iterator it = participant.getDivOrgsIterator();
		while(it.hasNext()) {		
	DivOrgData divOrgData = (DivOrgData)it.next();
	if(divOrgData.getDivOrgLevels() != null) {
		ItemData item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
%>							<tr>
								<td valign="top" width="50%">	
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText(divOrgData.getName())%>' />
									<table width="100%"  cellpadding="0" cellspacing="0">
										<tr class="linetwo">
											<th align="left"> <%=messages.getText(divOrgData.getDivOrgLevels().getName())%> </th>
											<td align="right"><%=item1.getName()%></td>
										</tr>
<%
	if(item1.getSubItems() != null) {
			ItemData item2 = (ItemData)item1.getSubItems().getItemIterator().next();
%>										<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr class="linetwo">
											<th align="left"> <%=messages.getText(item1.getSubItems().getName())%> </th>
											<td align="right"><%=item2.getName()%></td>
										</tr>
<%
	if(item2.getSubItems() != null) {
				ItemData item3 = (ItemData)item2.getSubItems().getItemIterator().next();
%>										<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
										<tr class="linetwo">
											<th align="left"> <%=messages.getText(item2.getSubItems().getName())%> </th>
											<td align="right"><%=item3.getName()%></td>
										</tr>
<%
	}
		}
%>									</table>
									<jsp:include page="../component/utilityboxbottom.jsp"/>	
								</td>
							</tr>
							<tr><td height="5"><img src="x" width="1" height="1"></tr>
<%
	}
		}
%>					
							<tr>
								<jsp:include  page="../component/spaceline.jsp" />
							</tr>
                    		<tr>
								<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.participant.actionorganization")%>' />
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
			      						<tr  class="linetwo">
			        						<th align="left"> <%=(participant.getOrganization() == null) ? "---" : messages.getText(participant.getOrganization().getResourceBundleId())%></th>
			      						</tr>
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%
	if(sys.getCorporationId() == CorporationData.IMESEVI_UY) {
%>							<tr class="linetwo">
										<th align="left"> <%=messages.getText("cenapra.data.otherprofesion")%></th>
										</tr>
										<tr class="linetwo">
											<td align="left" colspan="5">
												<%=(Util.empty(participant.getOtherorganization())) ? "" : participant.getOtherorganization()%>
											</td>
										</tr>
<%
	}
%>			      					</table>
									<jsp:include  page="../component/utilityboxbottom.jsp" />
			      				</td>
			      			</tr>
						</table>
					</td>
				</tr>
				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
				</tr>
				<tr>
					<td width="50%" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.data.profesion")%>' />
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" >
<%
	if(participant.isMedical()) {
%>							<tr class="linetwo">
								<td align="left"> <%="- "+messages.getText("cenapra.data.profesion.medical")%></td>
         					</tr>
<%
	}
	if(participant.isParamedical()) {
%>							<tr class="linetwo">
								<td align="left"> <%="- "+messages.getText("cenapra.data.profesion.paramedical")%></td>
         					</tr>
<%
	}
	if(participant.isNurse()) {
%>							<tr class="linetwo">
								<td align="left"> <%="- "+messages.getText("cenapra.data.profesion.nurse")%></td>
         					</tr>
<%
	}
	if(participant.isPolice()) {
%>							<tr class="linetwo">
								<td align="left"> <%="- "+messages.getText("cenapra.data.profesion.police")%></td>
         					</tr>
<%
	}
	if(participant.isVialinspector()) {
%>							<tr class="linetwo">
								<td align="left"> <%="- "+messages.getText("cenapra.data.profesion.vialinspector")%></td>
         					</tr>
<%
	}
	if(participant.isTeacher()) {
%>							<tr class="linetwo">
								<td align="left"> <%="- "+messages.getText("cenapra.data.profesion.teacher")%></td>
         					</tr>
<%
	}
	if(participant.isPrevent()) {
%>							<tr class="linetwo">
								<td align="left"> <%="- "+messages.getText("cenapra.data.profesion.prevent")%></td>
         					</tr>
<%
	}
%>							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.otherprofesion")%></th>
         					</tr>
							<tr class="linetwo">
         						<td align="left" colspan="5">
										<%=(Util.empty(participant.getOtherprofession())) ? "" : participant.getOtherprofession()%>
         						</td>
         					</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
	                <td width="16">&nbsp;</td>
                	<td width="50%" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.data.education")%>' />
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" >
		     					<tr  class="linetwo">
			        				<th align="left"> 
			        					<%=messages.getText("cenapra.participant.educationlevel")%>
			        				</th>
			        				<td align="right"> 
			        					<%=(participant.getEducation() == null) ? "---" : messages.getText(participant.getEducation().getResourceBundleId())%>
			        				</td>
	      						</tr>
	      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
								<tr class="linetwo">
    	    						<th align="left"> <%=messages.getText("cenapra.data.othereducation")%></th>
        	 					</tr>
								<tr class="linetwo">
         							<td align="left" colspan="5">
										<%=(Util.empty(participant.getOthereducation())) ? "" : participant.getOthereducation()%>
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
					<td width="50%" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.data.vialexperience")%>' />
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" >
							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.vialsecurity")%></th>
         						<td align="right">
         							<%=(participant.isSecurity()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no")%>
								</td>
         					</tr>
							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.capacitation")%></th>
         						<td align="right">
         							<%=(participant.isSecuritytrainning()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no")%>
								</td>
         					</tr>
							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.capacitationtype")%></th>
         					</tr>
							<tr class="linetwo">
         						<td align="left" colspan="5">
									<%=(Util.empty(participant.getSecuritydesc())) ? "" : participant.getSecuritydesc()%>
								</td>
         					</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
	                <td width="16">&nbsp;</td>
                	<td width="50%" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.data.teachexperience")%>' />
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" >
							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.hasteachexperience")%></th>
         						<td align="right">
         							<%=(participant.isTeachexperience()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no")%>
								</td>
         					</tr>
							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.teachfirst")%></th>
         						<td align="right">
         							<%=(participant.isFirst()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no")%>
								</td>
         					</tr>
							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.teachsecond")%></th>
         						<td align="right">
         							<%=(participant.isSecond()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no")%>
								</td>
         					</tr>
							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.teachthird")%></th>
         						<td align="right">
         							<%=(participant.isThird()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no")%>
								</td>
         					</tr>
							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.otherteach")%></th>
         					</tr>
							<tr class="linetwo">
         						<td align="left" colspan="5">
									<%=(Util.empty(participant.getOtherteach())) ? "" : participant.getOtherteach()%>
								</td>
         					</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
				<tr><jsp:include  page="../component/spaceline.jsp" /></tr>
				<tr>
					<td colspan="3" width="100%" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.data.observations")%>' />
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" >
							<tr class="linetwo">
         						<td align="left" colspan="5"><%=(Util.empty(participant.getObservations())) ? messages.getText("generic.messages.noobservations") : participant.getObservations()%></td>
         					</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
				</tr>
				<tr>
					<td colspan="3" width="100%" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.data.history")%>' />
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" >
							<tr class="guide2">
			                	<td width="50%" align="center"><%=messages.getText("cenapra.activity.date")%></td>
			                	<td width="50%" align="center"><%=messages.getText("cenapra.data.activitytype")%></td>
			                </tr>
<%
	Object[][] history = report.getParticipantHistory(new Integer(id),sys.getUserSessionData());
		if(history.length == 0) {
%>							<tr>
			                	<td colspan="2" class="linetwo" align="left"><%=messages.getText("generic.messages.noresult")%></td>
         					</tr>
<%
	}else {
    				for(int i = 0; i < history.length; i++) {
    		            int activityType = CEPAActivityConstants.MULTIPLICADORES;
    		            if(history[i][2].equals(CEPAActivityConstants.CENAPRA_POBLACION_META)) {
    		                activityType = CEPAActivityConstants.POBLACION_META;
    		            }
    		            if(history[i][2].equals(CEPAActivityConstants.CENAPRA_POBLACION_META_OLD)) {
    		                activityType = CEPAActivityConstants.POBLACION_META_OLD;
    		            }
    		            if(history[i][2].equals(CEPAActivityConstants.CENAPRA_MULTIPLICADORES_NEW)) {
    		                activityType = CEPAActivityConstants.MULTIPLICADORES_NEW;
    		            }
    		            if(history[i][2].equals(CEPAActivityConstants.CENAPRA_RELICADORES)) {
    		                activityType = CEPAActivityConstants.REPLICADORES;
    		            }
    		            if(history[i][2].equals(CEPAActivityConstants.CENAPRA_MULTIPLICADORES_OLD)) {
    		                activityType = CEPAActivityConstants.MULTIPLICADORES_OLD;
    		            }
    		            if(history[i][2].equals(CEPAActivityConstants.CENAPRA_FORMADORES)) {
    		                activityType = CEPAActivityConstants.FORMADORES;
    		            }
%>    				    	<tr class="lintwo">
								<td width="50%" align="center">
									<a href='<%="layout.jsp?refer=cenapra/checkListView.jsp?registry="+String.valueOf(history[i][0])+"&type="+activityType%>'>
										<%=Util.formatDate((Date)history[i][1])%>
									</a>
								</td>
			                	<td width="50%" align="center"><%=messages.getText((String)history[i][2])%></td>
			                </tr>
<%    				}
				}
%>  					</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
		   		<tr>
		   			<jsp:include  page="../component/spaceline.jsp" />
		   		</tr>
		   		<jsp:include  page="participantOptions.jsp" />
		  		
				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
				</tr>
		  	</table>
		</html:form>
	</body>
<%			}
		}
	}	
%>
</html:html>
