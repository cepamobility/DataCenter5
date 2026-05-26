<%@page language="java" 
    errorPage="../exception.jsp"
	import="business.*"
	import="business.datatype.*"
	import="business.vehicle.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.travel.*"
	import="communication.travel.tables.*"
	import="communication.driver.tables.*"
	import="communication.language.*"
	import="communication.datatype.*"
	import="communication.datatype.tables.*"
	import="communication.driver.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.administration.user.*"
	import="communication.vehicle.tables.*"
	import="communication.security.*"
	import="communication.activity.tables.CEPAActivityScheduleRegistryData"
	import="communication.activity.tables.CEPAActivityConstants"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%!RequestDispatcher dispatcher; SystemAccess sys;	String context;
	Text messages; Integer id; 
	TravelDataType travelDataType;
	UserSessionData userSessionData;
	VehicleData vehicle = null;%>
<%
	try{

		sys = (SystemAccess)session.getAttribute("SystemAccess");
		if(sys == null){
	response.sendRedirect("/exception.jsp");
		}
	
		context=request.getContextPath();
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
	
		userSessionData = sys.getUserSessionData();
		messages=sys.getText();
		id = new Integer(request.getParameter("id"));
		String check = Util.checkPermission(sys,SecurityConstants.MODIFY_EVENT);
		if(check!=null) {
	response.sendRedirect(request.getContextPath()+check);
		}else {
	String accidentCollision="";
	context=request.getContextPath();
	dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
	dispatcher.include(request,response);
		
	messages=sys.getText();
	travelDataType = sys.getEventReportFacade().findTravelByPrimaryKey(id,sys.getUserSessionData());
	
	if(travelDataType != null) {

		if(travelDataType.getConfirmedLeave() != null) {
			TravelCheckPointForm form = new TravelCheckPointForm(travelDataType,sys.getEventReportFacade().getTravelCheckPoints(travelDataType.getId(),sys.getUserSessionData()));
			session.setAttribute("TravelCheckPointForm",form);
		}
		
		Collection commList = new LinkedList();
		commList.add(new OptionItem(messages.getText("generic.messages.select"),""));
		commList.add(new OptionItem(messages.getText("checkpoint.way.phone"),"checkpoint.way.phone"));
		commList.add(new OptionItem(messages.getText("checkpoint.way.mobile"),"checkpoint.way.mobile"));
		commList.add(new OptionItem(messages.getText("checkpoint.way.email"),"checkpoint.way.email"));
		commList.add(new OptionItem(messages.getText("checkpoint.way.sms"),"checkpoint.way.sms"));
		session.setAttribute("commList",commList);
		
		Collection commFromList = new LinkedList();
		commFromList.add(new OptionItem(messages.getText("generic.messages.select"),""));
		commFromList.add(new OptionItem(messages.getText("checkpoint.commfrom.driver"),"checkpoint.commfrom.driver"));
		commFromList.add(new OptionItem(messages.getText("checkpoint.commfrom.afb"),"checkpoint.commfrom.afb"));
		commFromList.add(new OptionItem(messages.getText("checkpoint.commfrom.cepa"),"checkpoint.commfrom.cepa"));
		session.setAttribute("commFromList",commFromList);
		
		int stops = (travelDataType.getStops() == null) ? 0 : travelDataType.getStops().intValue();
%>
			<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
			</script>
			<script language="javascript" src='./menu/js/refactors.js' type='text/javascript' >
			</script>
			<script language="JavaScript" type="text/javascript">
				function confirmDelete(form,msg){
					if(confirm(msg)){
						form.submit();
					}
				}
			</script>
			<html:form action="/TravelCheckPoint" >
				<html:hidden property="travel" value='<%=String.valueOf(id)%>'/>
				<html:hidden property="stops" value='<%=String.valueOf(stops)%>'/>
				<table align="center">
					<tr>
						<jsp:include  page="../component/spaceline.jsp" />
				  	</tr>
	 				<tr class="guide1">
	  					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.travel.checkpoint")%>' />
				  	</tr>
					<tr>
						<jsp:include  page="../component/spaceline.jsp" />
				  	</tr>
					<tr>
						<td width="49%" valign="top">
                    		<table width="100%">
                    			<tr>
                     				<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.driverdata")%>' />
		        					<table width='100%' border="0" cellpadding="0" cellspacing="0">
		            					<tr class="linetwo">
	              							<th align="left" valign="middle"><%=messages.getText("travel.driver.name")%></th>
	    									<td align="right" valign="middle" >			
                       							<%=travelDataType.getDriver().getFirstName()+" "+travelDataType.getDriver().getLastName()%>
											</td>
	                         			</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            					<tr class="linetwo">
	              							<th align="left" valign="middle"><%=messages.getText("travel.driver.mobile")%></th>
	                            			<td align="right">
                       							<%=(travelDataType.getMobile() == null) ? "---" : travelDataType.getMobile()%>
							    			</td>
	                         			</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            					<tr class="linetwo">
	              							<th align="left" valign="middle"><%=messages.getText("travel.driver.radio")%></th>
	                            			<td align="right">
	                            				<%=(Util.empty(travelDataType.getRadio())) ? "---" : travelDataType.getRadio()%>
							    			</td>
	                         			</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            					<tr class="linetwo">
	              							<th align="left" valign="middle"><%=messages.getText("travel.driver.charge")%></th>
	    									<td align="right" valign="middle" >			
	    										<%=(travelDataType.getCharge() == null) ? "---" : travelDataType.getCharge()%>
											</td>
	                         			</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            					<tr class="linetwo">
	              							<th align="left" valign="middle"><%=messages.getText("travel.driver.other")%></th>
	                            			<td align="right">
	                            				<%=(Util.empty(travelDataType.getOther())) ? "---" : travelDataType.getOther()%>
							    			</td>
	                         			</tr>
	                     			</table>
									<jsp:include  page="../component/utilityboxbottom.jsp" />
								</td>
							</tr>
						</table>
					</td>
					<td width="2%" valign="top">&nbsp;</td>
					<td width="49%" valign="top">
						<table width="100%" height="100%" cellpadding="0" cellspacing="0">
<%			
				Iterator it = travelDataType.getDivOrgsIterator();
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
<%	    				if(item1.getSubItems() != null) {
							ItemData item2 = (ItemData)item1.getSubItems().getItemIterator().next();
%>											<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
											<tr class="linetwo">
												<th align="left"> <%=messages.getText(item1.getSubItems().getName())%> </th>
												<td align="right"><%=item2.getName()%></td>
											</tr>
<%				    		if(item2.getSubItems() != null) {
								ItemData item3 = (ItemData)item2.getSubItems().getItemIterator().next();
%>											<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
											<tr class="linetwo">
												<th align="left"> <%=messages.getText(item2.getSubItems().getName())%> </th>
												<td align="right"><%=item3.getName()%></td>
											</tr>
<%							}
						}
%>
										</table>
   									<jsp:include page="../component/utilityboxbottom.jsp"/>	
	   							</td>
	   						</tr>
	   						<tr><td height="5"><img src="x" width="1" height="1"></tr>
<%					}
				}
%>
						</table>	
					</td>
				</tr>
				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
				<tr>
					<td colspan="3" width="600">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("travel.checkpoint.confirmation")%>' />
		        		<table width='100%' border="0" cellpadding="0" cellspacing="0">
		            		<tr class="linetwo">
	              				<th align="left" valign="middle"><%=messages.getText("travel.checkpoint.date") %></th>
	       						<td align="right" class="linetwo" width="25%">
	       							<jsp:include  page='<%="../component/dateComponent.jsp?index=1&day=date&month=month&year=year&form=TravelCheckPointForm" %>' />
			          			</td>
								<td>&nbsp;</td>
	              				<th align="left" valign="middle"><%=messages.getText("travel.checkpoint.hour")%></th>
	                            <td align="right">
									<html:text property="hour" style="width:23;" styleClass="input"/>&nbsp;:&nbsp;
		        					<html:text property="minute" style="width:23;" styleClass="input"/>
							    </td>
							</tr>
   							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            		<tr class="linetwo">
	              				<th align="left" valign="middle"><%=messages.getText("travel.checkpoint.comunicationway") %></th>
	       						<td align="right" class="linetwo" width="25%">
									<html:select property="way" styleClass="input">
                  						<html:options collection="commList" property="value" labelProperty="label" styleClass="input"/>
		            				</html:select>
		            			</td>
								<td>&nbsp;</td>
	              				<th align="left" valign="middle"><%=messages.getText("travel.checkpoint.monitor")%></th>
	                            <td align="right">
									<html:text property="monitor" style="width:120;" styleClass="input"/>
							    </td>
							</tr>
   							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            		<tr class="linetwo">
	              				<th colspan="5" align="left" valign="middle">
	              					<%=messages.getText("travel.checkpoint.comments") %>
	              				</th>
	              			</tr>
		            		<tr class="linetwo">
		            			<td colspan="5">
									<html:textarea property="comment" cols="80" rows="3" />
								</td>
	              			</tr>
						</table>
		            	<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
	  			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
<%				for(int i = 1; i <= stops; i++){
					int j = i+1;
%>	  			<tr>
					<td colspan="3" width="600">
						<html:hidden property='<%="id"+i%>'/>
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("travel.checkpoint.verification")%>' />
		        		<table width='100%' border="0" cellpadding="0" cellspacing="0">
		            		<tr class="linetwo">
	              				<th align="left" valign="middle"><%=messages.getText("travel.checkpoint.date") %></th>
	       						<td align="right" class="linetwo" width="25%">
	       							<jsp:include  page='<%="../component/dateComponent.jsp?index="+j+"&day=date"+i+"&month=month"+i+"&year=year"+i+"&form=TravelCheckPointForm"%>' />
			          			</td>
								<td>&nbsp;</td>
	              				<th align="left" valign="middle"><%=messages.getText("travel.checkpoint.hour")%></th>
	                            <td align="right">
									<html:text property='<%="hour"+i%>' style="width:23;" styleClass="input"/>&nbsp;:&nbsp;
		        					<html:text property='<%="minute"+i%>' style="width:23;" styleClass="input"/>
							    </td>
							</tr>
   							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            		<tr class="linetwo">
	              				<th align="left" valign="middle"><%=messages.getText("travel.checkpoint.local")%></th>
	                            <td align="right">
									<html:text property='<%="local"+i%>' style="width:120;" styleClass="input"/>
							    </td>
								<td>&nbsp;</td>
	              				<th align="left" valign="middle"><%=messages.getText("travel.checkpoint.comunicationway") %></th>
	       						<td align="right" class="linetwo" width="25%">
									<html:select property='<%="way"+i%>' styleClass="input">
                  						<html:options collection="commList" property="value" labelProperty="label" styleClass="input"/>
		            				</html:select>
		            			</td>
							</tr>
   							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            		<tr class="linetwo">
	              				<th align="left" valign="middle"><%=messages.getText("travel.checkpoint.communication")%></th>
	                            <td align="right">
									<html:select property='<%="communication"+i%>' styleClass="input">
                  						<html:options collection="commFromList" property="value" labelProperty="label" styleClass="input"/>
									</html:select>
							    </td>
								<td>&nbsp;</td>
	              				<th align="left" valign="middle" class="input">
	              					<html:radio property='<%="result"+i%>' value="1">
	              						<b><%=messages.getText("travel.checkpoint.resultok") %></b>
	              					</html:radio>
	              				</th>
	              				<th align="left" valign="middle" class="input">
	              					<html:radio property='<%="result"+i%>' value="2">
	              						<b><%=messages.getText("travel.checkpoint.resultincidents") %></b>
	              					</html:radio>
	              				</th>
							</tr>
   							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            		<tr class="linetwo">
	              				<th colspan="5" align="left" valign="middle">
	              					<%=messages.getText("travel.checkpoint.comments") %>
	              				</th>
	              			</tr>
		            		<tr class="linetwo">
		            			<td colspan="5">
									<html:textarea property='<%="comment"+i%>' cols="80" rows="3" />
								</td>
	              			</tr>
						</table>
		            	<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
	  			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
<%				}
%>				<tr>
	  				<td colspan="3">
						<table width="100%">
							<tr>
				        		<td align="right">
				          			<div align="right">
				            			<html:submit styleClass="input"><%=messages.getText("generic.messages.save")%></html:submit>
						 				<html:cancel styleClass="input"><%=messages.getText("generic.messages.cancel")%></html:cancel >
				          			</div>
				          		</td>
				  			</tr>
						</table>
	  				</td>
	  			</tr>
			</table>
		</html:form>
			
<%			}
		}	
	}catch(Exception e){
		e.printStackTrace();
	}
%>	
