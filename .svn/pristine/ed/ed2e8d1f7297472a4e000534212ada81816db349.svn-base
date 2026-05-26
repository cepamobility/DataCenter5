<%@page language="java" 
	errorPage="../exception.jsp" 
	import="business.*"
	import="business.cenapra.*"
	import="business.administration.user.*"
	import="communication.datatype.*"
	import="communication.language.*"
	import="communication.security.*"
    import="communication.activity.tables.CEPAActivityConstants"
	import="communication.activity.tables.CheckListData"
	import="communication.activity.tables.PointData"
	import="communication.activity.tables.ItemData"
	import="communication.util.*"
	import="communication.cenapra.tables.*"
	import="communication.datatype.tables.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="communication.administration.user.*" 	
	import="business.datatype.*"
	import="java.util.*"	
	import="org.apache.struts.action.DynaActionForm"
	import="communication.administration.corporation.tables.*"
%>

<%!
	RequestDispatcher dispatcher; 
	Text messages; SystemAccess sys;
	UserSessionData userSessionData; 
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();	
	String check = Util.checkPermission(sys,SecurityConstants.IMESEVI_VIEW_ACTIVITY);		
	if (check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		messages=sys.getText();
	
		session.setAttribute("url","cenapra/checkListForm.jsp");
		
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
	
		String registryId = (String)request.getParameter("registry");
		if(registryId == null){
		    registryId = (String)session.getAttribute("registry");			
		}

		String type = (String)request.getParameter("type");
		if(type == null){
		    type = (String)session.getAttribute("type");			
		}

		String title = "cenapra.checklist.form";
		    
		int[] checks = {0,22,0};
		if(sys.getCorporationId() == CorporationData.IMESEVI_UY) {
	checks[1] += 30;
		}
		switch(Integer.parseInt(type)) {
		    case CEPAActivityConstants.MULTIPLICADORES: case CEPAActivityConstants.FORMADORES:
		        checks[0] = 32;
		        checks[2] = 33;
		        break;
		    case CEPAActivityConstants.POBLACION_META:
		        checks[0] = 30;
		        checks[2] = 31;
		        break;
		    case CEPAActivityConstants.MULTIPLICADORES_NEW: case CEPAActivityConstants.FORMADORES_VIEJO:
		        checks[0] = 25;
		        checks[2] = 26;
		        break;
		    case CEPAActivityConstants.MULTIPLICADORES_OLD: case CEPAActivityConstants.REPLICADORES:
		        checks[0] = 20;
		        checks[2] = 21;
		        break;
		    case CEPAActivityConstants.POBLACION_META_OLD:
		        checks[0] = 23;
		        checks[2] = 24;
		        break;
		}
		
		if(!Util.empty(registryId)){
		    CENAPRAReportFacade report = sys.getCENAPRAReportFacade();
		    ParticipantRegistryData registry = report.getParticipantRegistry(Integer.valueOf(registryId),userSessionData);
		    Hashtable values = report.getCorrectAnswers(userSessionData);
%>
<html>
	<body>
		<center>
	  		<table width="600" border="0" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText(title)%>' />
		  		</tr>
      			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
  				</tr>
				<tr>
					<td width="100%" valign="top" colspan="5">
						<jsp:include  page='<%="../component/utilitybox3top.jsp?title="+messages.getText("generic.messages.options")%>' />
						<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
			        		<tr> 
<%
 	if(sys.getUserSessionData().checkPermission(SecurityConstants.IMESEVI_UPDATE_ACTIVITY)) {
 %>		      					<td width="3%" class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
        	  					<td width="46%">
        	  						<a href='<%="layout.jsp?refer=cenapra/checkListForm.jsp?registry="+registryId+"&type="+type%>' class="texts"> 
		    	        				<%=messages.getText("generic.messages.edit")%> 
		    	        			</a> 
		        	    		</td>
<%
	}
%>	         					<td width="3%" class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
			          			<td width="49%">
	          						<a href='<%="layout.jsp?refer=cenapra/ActivityView.jsp?id="+String.valueOf(registry.getActivityData().getId())%>' class="texts">
	          							<%=messages.getText("generic.messages.cancel")%>
	          						</a>
	          					</td>
            				</tr>
	  					</table>
						<jsp:include page="../component/utilitybox3bottom.jsp"/>
					</td>
				</tr>
				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
  				</tr>
  		       	<tr>
                 	<td>
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.activity.participant")%>' />
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
	      					<tr  class="linetwo">
	        					<th align="left"> <%=messages.getText("cenapra.data.firstname")%></th>
	         					<td align="right"><%=registry.getParticipantData().getFirstName()%></td>
	      					</tr>
	      					<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
	      					<tr class="linetwo">
	        					<th align="left"><%=messages.getText("cenapra.data.lastname")%></th>
				        		<td align="right"><%=registry.getParticipantData().getLastName()%></td>
	      					</tr>
	      					<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
	      					<tr  class="linetwo">
	        					<th align="left"> <%=messages.getText("cenapra.data.birthdate")+" (dd/mm/aaaa)"%></th>
	        					<td align="right">
									<%=(registry.getParticipantData().getBirthDate() == null) ? "---" : Util.formatDate(registry.getParticipantData().getBirthDate())%>
	        					</td>
	      					</tr>
	      					<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
	      					<tr  class="linetwo">
	        					<th align="left"> <%=messages.getText("cenapra.activity.folio")%></th>
	        					<td align="right">
									<%=(Util.empty(registry.getNumber())) ? "" : registry.getNumber()%>
	        					</td>
	      					</tr>
	      					<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
	      					<tr  class="linetwo">
	        					<th align="left"> <%=messages.getText("cenapra.activity.note")%></th>
	        					<td align="right">
<% 								if(registry.getNote() == null) {
%>									---		
<%								}else if(registry.getNote().intValue() == ParticipantRegistryData.REGULAR) {
%>									<%=messages.getText("cenapra.activity.regular")%>
<%								}else if(registry.getNote().intValue() == ParticipantRegistryData.ACEPTABLE) {
%>									<%=messages.getText("cenapra.activity.aceptable")%>
<%								}else if(registry.getNote().intValue() == ParticipantRegistryData.BUENO) {
%>									<%=messages.getText("cenapra.activity.bueno")%>
<%								}	
%>	        					</td>
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
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.checklist1.form")%>' />
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
<%			Collection selectedItems = report.getRegistryItems(Integer.valueOf(registryId),Integer.valueOf(checks[0]),userSessionData);
			CheckListData checkList = sys.getActivityReportFacade().findCheckList(new Integer(checks[0]),userSessionData);	
			Collection points = checkList.getPoints();
			Collections.sort((List)points);
			Iterator pointIt = points.iterator();
			while(pointIt.hasNext()) {
				PointData point = (PointData) pointIt.next();
%>							<tr>
								<td>
									<jsp:include  page="../component/utilitybox4top.jsp" />
										<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
											<tr>
												<th class="lineone" align="left"><%=messages.getText(point.getText())%></th>
		  									</tr>
											<tr>
												<td class="lineone">&nbsp;</td>
											</tr>  					  					
											<tr class="lineone">
												<td>
													<table width="100%" border="0">
														<tr>
<%				Collection items = point.getItems();
				Collections.sort((List)items);
				Iterator itemIt = items.iterator();
				while(itemIt.hasNext()) {
					ItemData item = (ItemData)itemIt.next();
					if(selectedItems.contains(item.getId())){
					    String correct = "";
					    if(values.containsKey(new Integer(checks[0]))) {
					        correct = (((Collection)values.get(new Integer(checks[0]))).contains(item.getId())) ? "<span class='txtOk'>Correcto</span>" : "<span class='txtNot'>Incorrecto</span>";
					    }
%>															<td align="left" class="lineone">
																<%=correct+" "+messages.getText(item.getText())%>
															</td>
<%					}
				}
%>														</tr>
													</table>
												</td>	
											</tr>
						   				</table>
									<jsp:include  page="../component/utilitybox4bottom.jsp" />
								</td>
							</tr>
				      		<tr>
								<jsp:include  page="../component/spaceline.jsp" />
				  			</tr>
<%			}
%>						</table>	
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>	
      			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
  				</tr>
	             <tr>
                 	<td>
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.checklist3.form")%>' />
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
<%			selectedItems = report.getRegistryItems(Integer.valueOf(registryId),Integer.valueOf(checks[1]),userSessionData);
			checkList = sys.getActivityReportFacade().findCheckList(new Integer(checks[1]),userSessionData);	
			points = checkList.getPoints();
			Collections.sort((List)points);
			pointIt = points.iterator();
			while(pointIt.hasNext()) {
				PointData point = (PointData) pointIt.next();
%>							<tr>
								<td>
									<jsp:include  page="../component/utilitybox4top.jsp" />
										<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
											<tr>
												<th class="lineone" align="left"><%=messages.getText(point.getText())%></th>
						  					</tr>
											<tr>
												<td class="lineone">&nbsp;</td>
											</tr>  					  					
<%				Collection items = point.getItems();
				Collections.sort((List)items);
				Iterator itemIt = items.iterator();
				while(itemIt.hasNext()) {
					ItemData item = (ItemData)itemIt.next();
					if(selectedItems.contains(item.getId())){
%>											<tr class="lineone">
												<td align="left">
													<%=messages.getText(item.getText())%>
												</td>
											</tr>
<%					}
				}
%>		   								</table>
									<jsp:include  page="../component/utilitybox4bottom.jsp" />
								</td>
							</tr>	
				      		<tr>
								<jsp:include  page="../component/spaceline.jsp" />
				  			</tr>
<%			}
%>			    			<tr>
								<td width="100%" colspan="3">
									<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("cenapra.data.observations")%>' />
										<table width="100%" cellpadding="0" cellspacing="0">
											<tr>
												<td width="270" class="lineone">
													<%=registry.getObservations()%>
												</td>
											</tr>
										</table>
									<jsp:include  page="../component/utilitybox2bottom.jsp" />
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
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.checklist2.form")%>' />
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
<%			selectedItems = report.getRegistryItems(Integer.valueOf(registryId),Integer.valueOf(checks[2]),userSessionData);
			checkList = sys.getActivityReportFacade().findCheckList(new Integer(checks[2]),userSessionData);	
			points = checkList.getPoints();
			Collections.sort((List)points);
			pointIt = points.iterator();
			while(pointIt.hasNext()) {
				PointData point = (PointData) pointIt.next();
%>							<tr>
								<td>
									<jsp:include  page="../component/utilitybox4top.jsp" />
									<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
										<tr>
											<th class="lineone" align="left"><%=messages.getText(point.getText())%></th>
		  								</tr>
										<tr>
											<td class="lineone">&nbsp;</td>
										</tr>  					  					
										<tr class="lineone">
											<td>
												<table width="100%" border="0">
													<tr>
<%				Collection items = point.getItems();
				Collections.sort((List)items);
				Iterator itemIt = items.iterator();
				while(itemIt.hasNext()) {
					ItemData item = (ItemData)itemIt.next();
					if(selectedItems.contains(item.getId())){
					    String correct = "";
					    if(values.containsKey(new Integer(checks[2]))) {
					        correct = (((Collection)values.get(new Integer(checks[2]))).contains(item.getId())) ? "<span class='txtOk'>Correcto</span>" : "<span class='txtNot'>Incorrecto</span>";
					    }
%>															<td align="left" class="lineone">
																<%=correct+" "+messages.getText(item.getText())%>
															</td>
<%					}
				}
%>
													</tr>
												</table>
											</td>
										</tr>
						  			</table>
									<jsp:include  page="../component/utilitybox4bottom.jsp" />
								</td>
							</tr>	
				      		<tr>
								<jsp:include  page="../component/spaceline.jsp" />
				  			</tr>
<%			}
%>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
      			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
  				</tr>
				<tr>
					<td width="100%" valign="top" colspan="5">
						<jsp:include  page='<%="../component/utilitybox3top.jsp?title="+messages.getText("generic.messages.options")%>' />
						<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
			        		<tr> 
<%		if(sys.getUserSessionData().checkPermission(SecurityConstants.IMESEVI_UPDATE_ACTIVITY)) {
%>		      					<td width="3%" class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
        	  					<td width="46%">
        	  						<a href='<%="layout.jsp?refer=cenapra/checkListForm.jsp?registry="+registryId+"&type="+type%>' class="texts"> 
		    	        				<%=messages.getText("generic.messages.edit")%> 
		    	        			</a> 
		        	    		</td>
<%		}
%>	         					<td width="3%" class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
			          			<td width="49%">
	          						<a href='<%="layout.jsp?refer=cenapra/ActivityView.jsp?id="+String.valueOf(registry.getActivityData().getId())%>' class="texts">
	          							<%=messages.getText("generic.messages.cancel")%>
	          						</a>
	          					</td>
            				</tr>
	  					</table>
						<jsp:include page="../component/utilitybox3bottom.jsp"/>
					</td>
				</tr>
				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
  				</tr>
			</table>
		</center>
	</body>
</html>

  
<%		}
	}
%>

