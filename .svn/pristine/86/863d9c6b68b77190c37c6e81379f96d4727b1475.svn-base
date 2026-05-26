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
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%!
	RequestDispatcher dispatcher; 
	Text messages; SystemAccess sys;
	UserSessionData userSessionData; 
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();	
	String check = Util.checkPermission(sys,SecurityConstants.IMESEVI_UPDATE_ACTIVITY);		
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
		    case CEPAActivityConstants.MULTIPLICADORES_NEW:  case CEPAActivityConstants.FORMADORES_VIEJO:
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
%>
	
<%@page import="communication.administration.corporation.tables.CorporationData"%><center>
		<html:form action="/CENAPRACheckList" method="post">
			<html:hidden property="registry" value='<%=registryId%>'/>
			<html:hidden property="type" value='<%=type%>'/>
			<html:hidden property="activity" value='<%=String.valueOf(registry.getActivityData().getId())%>'/>
	  		<table width="600" border="0" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText(title)%>' />
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
	        					<td align="right" valign="middle">
									<%=(registry.getParticipantData().getBirthDate() == null) ? "---" : Util.formatDate(registry.getParticipantData().getBirthDate())%>
	        					</td>
	      					</tr>
	      					<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
	      					<tr  class="linetwo">
	        					<th align="left"> <%=messages.getText("cenapra.activity.folio")%></th>
	        					<td align="right" valign="middle">
									<html:text property="number" value='<%=(Util.empty(registry.getNumber())) ? "" : registry.getNumber()%>' />
	        					</td>
	      					</tr>
	      					<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
	      					<tr  class="linetwo">
	        					<th align="left"> <%=messages.getText("cenapra.activity.note")%></th>
	        					<td align="right" valign="middle">
									<html:select property="note" value='<%=(registry.getNote() != null) ? String.valueOf(registry.getNote()) : String.valueOf(ParticipantRegistryData.REGULAR)%>'>
										<html:option value='<%=String.valueOf(ParticipantRegistryData.REGULAR)%>'><%=messages.getText("cenapra.activity.regular")%></html:option>
										<html:option value='<%=String.valueOf(ParticipantRegistryData.ACEPTABLE)%>'><%=messages.getText("cenapra.activity.aceptable")%></html:option>
										<html:option value='<%=String.valueOf(ParticipantRegistryData.BUENO)%>'><%=messages.getText("cenapra.activity.bueno")%></html:option>
									</html:select>
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
<%				if(checks[0] == 30) {
%>													<table width="100%" border="0">
														<tr class="lineone">
<%				}else {
%>    												<table width="100%" border="0">
<%				}
				Collection items = point.getItems();
				Collections.sort((List)items);
				Iterator itemIt = items.iterator();
				while(itemIt.hasNext()) {
					ItemData item = (ItemData)itemIt.next();
					if(checks[0] != 30) {
%>														<tr class="lineone">
<%					}
%>															<td align="left" class="lineone">
																<input type="radio" name='<%="item"+String.valueOf(point.getId())%>' value='<%=String.valueOf(item.getId()) %>' <%=(selectedItems.contains(item.getId())) ? "checked" : "" %> />
																<%=messages.getText(item.getText())%>
															</td>
<%					if(checks[0] != 30) {
%>														</tr>
<%					}
				}
				if(checks[0] == 30) {
%>														</tr>
<%				}
%>													</table>
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
%>											<tr class="lineone">
												<td align="left">
													<input type="radio" name='<%="item"+String.valueOf(point.getId())%>' value='<%=String.valueOf(item.getId()) %>' <%=(selectedItems.contains(item.getId())) ? "checked" : "" %> />
													<%=messages.getText(item.getText())%>
												</td>
											</tr>
<%				}
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
												<td width="270">
													<html:textarea property="observations" cols="70" />
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
<%				if(checks[2] == 31) {
%>												<table width="100%" border="0">
													<tr class="lineone">
<%				}else {
%>  											<table width="100%" border="0">
<%				}
				Collection items = point.getItems();
				Collections.sort((List)items);
				Iterator itemIt = items.iterator();
				while(itemIt.hasNext()) {
					ItemData item = (ItemData)itemIt.next();
					if(checks[2] != 31) {
%>													<tr class="lineone">
<%					}
%>														<td align="left" class="lineone">
															<input type="radio" name='<%="item"+String.valueOf(point.getId())%>' value='<%=String.valueOf(item.getId()) %>' <%=(selectedItems.contains(item.getId())) ? "checked" : "" %> />
															<%=messages.getText(item.getText())%>
														</td>
<%					if(checks[2] != 31) {
%>													</tr>
<%					}
				}
				if(checks[2] == 31) {
%>													</tr>
<%				}	
%>												</table>
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
		        	<td height="10" align="right">
		        		<html:submit value='<%=messages.getText("generic.messages.save")%>' styleClass="input"/>
		        		<html:cancel value='<%=messages.getText("generic.messages.cancel")%>' styleClass="input"/>
					</td>
				</tr>
			</table>
		</html:form>
	</center>

  
<%		}
	}
%>

