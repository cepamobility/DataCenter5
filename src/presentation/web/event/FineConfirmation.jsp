<%@page import="communication.util.Filter"%>
<%@page import="com.kitfox.svg.Line"%>
<%@page language="java" 
	import="business.*"
	import="communication.util.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.event.*"
	import="communication.language.*"
	import="communication.driver.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.event.tables.*"
	import="communication.security.*"
	import="communication.vehicle.tables.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>
<%!
	RequestDispatcher dispatcher; SystemAccess sys;	String context;
	String pathLanguage; Text messages; Integer driverId; String driverName; 
	String driverLastName; FineForm eventForm;
%>
<%	sys = (SystemAccess)session.getAttribute("SystemAccess");
	
	messages = sys.getText();
	
	if(sys == null){
		response.sendRedirect("/exception.jsp");
	}
	
	context=request.getContextPath();
	dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
	dispatcher.include(request,response);
	
//Traigo el Form en la session
	eventForm = null;
	if(session.getAttribute("FineForm")!=null){
		eventForm = (FineForm) session.getAttribute("FineForm");	
		eventForm.getObservations();
	}
//Traigo el FineDataType 
	FineDataType fineData = null;
	if(session.getAttribute("FineDataType")!=null){
		 fineData = (FineDataType) session.getAttribute("FineDataType"); 
	}

	DriverData driver = null;
	driver = fineData.getDriver();
	
	Collection la = null;
	if(session.getAttribute("Divs")!=null){	
		
		la = (Collection) session.getAttribute("Divs");
		
	}
%>
<form action="layout.jsp?refer=/event/EventList.jsp" method="post">
</form>
<script>
function atras() {  
	  window.history.back(); 
	}
</script>

	<head/>
	<body bgcolor="#FFFFFF">
		<html:form action="/FineConfirmation" >
			<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
	 			<tr class="guide1">
	 				<jsp:include  page='<%="../component/titlecomponent.jsp?colspan=5&title="+messages.getText("Desea confirmar el evento")%>' />
				</tr>
<%		if(sys.isBrasilCorporation() && sys.getEventReportFacade().existFine(fineData, sys.getUserSessionData())) {
%>
		  		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
		  		</tr>		
		  		<tr>
					<td class="redBig" colspan="3"><%=messages.getText("fine.message.alreadyexists") %></td>
		  		</tr>		
		  		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
		  		</tr>		
<%		}
%>		  		<tr>
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
			        						<td align="left" valign="middle"><strong><%=messages.getText("generic.event.driver.name")%></strong></td>
											<td align="left" valign="middle" >			
			                					<span class="linetwo"><%=(driver!=null) ? driver.getFirstName()+" "+driver.getLastName() : " "%></span>
											</td>
										</tr>
										<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			          					<tr class="linetwo">
			        						<td align="left" valign="middle"><strong><%=messages.getText("generic.event.driver.birthdate")%></strong></td>
			                         		<td>
			         	   						<span class="linetwo"><%=Util.formatDate(driver.getBirthDate())%><%="  ("+messages.getText("generic.messages.dateformat")+" )"%></span>
							    			</td>
			                       		</tr>
			     						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			           					<tr class="linetwo">
			           						<td align="left" valign="middle"><strong><%=messages.getText("generic.event.driver.startdate")%></strong></td>
			                         		<td>
			                   					<span class="linetwo"><%=Util.formatDate(driver.getInclusionDate())%><%="  ( "+messages.getText("generic.messages.dateformat")+" )"%></span>
						    				</td>
				                       	</tr>
				     					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
				           				<tr class="linetwo">
				            				<td align="left" valign="middle"><strong><%=messages.getText("generic.event.driver.licensecategory")%></strong></td>
			                          		<td>
			           							<span class="linetwo"><%=(Util.empty(driver.getLicenseCategory())) ? "-------" : driver.getLicenseCategory()%>
			                    				</span>
							    			</td>
				                       	</tr>
				     					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
				           				<tr class="linetwo">
				            				<td align="left" valign="middle"><strong><%=messages.getText("generic.event.driver.licenseexpiry")%></strong></td>
				                          	<td>
				          						<span class="linetwo"><%=Util.formatDate(driver.getLicenseExpiry())%><%="  ( "+messages.getText("generic.messages.dateformat")+" )"%></span>
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
								<td width="30%" valign="top">
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.divgeo")%>' />

<%
	if(fineData.getCountry() != null) {
	GeoItemData geoItem1 = null;
    				geoItem1 = (GeoItemData)fineData.getCountry().getItemIterator().next();
%>
									<table width="100%" height="100%" valign="top" cellpadding="0" cellspacing="0">
										<tr class="linetwo">
											<th align="left"> <%=messages.getText(fineData.getCountry().getResourceKey())%> </th>
											<td align="right">
												<%=(geoItem1.getSubItems() != null) ? geoItem1.getName() : messages.getText(geoItem1.getName())%>
											</td>
										</tr>
<%
	if(geoItem1.getSubItems() != null) {
		    	GeoItemData geoItem2 = (GeoItemData)geoItem1.getSubItems().getItemIterator().next();
%>									
										<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
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
%>							
										<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
										<tr class="linetwo">
											<th align="left"> <%=messages.getText("web.event.state")%> </th>
											<td align="right">
												<%=(Util.empty(fineData.getState())) ? "-------" : fineData.getState()%>
											</td>
										</tr>
										<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
										<tr class="linetwo">
											<th align="left"> <%=messages.getText("web.event.city")%> </th>
											<td align="right">
												<%=(Util.empty(fineData.getCity())) ? "-------" : fineData.getCity()%>
											</td>
										</tr>
									</table>	
		   							<jsp:include page="../component/utilityboxbottom.jsp"/>
		   						</td>
		   					</tr>
		   				</table>	
		   			</td>
					<td width="2%" valign="top">&nbsp;</td>
					<td width="49%" valign="top">
						<table width="100%" height="100%" valign="top" cellpadding="0" cellspacing="0">
<%
	int levels=0;
	int indice = 0;
	
	Iterator it =  fineData.getDivOrgs().iterator();	
	Filter filter = sys.getUserSessionData().getFilter();
	String[] labels = filter.getDivOrgLabels();
	while(it.hasNext()) {		
		DivOrgData divOrgData = (DivOrgData)it.next();
		if(divOrgData.getDivOrgLevels() != null) {				
	ItemData item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
	ItemData itemData = filter.getDivOrgItem(indice, 1, item1.getId(), Filter.USER);
%>	
							<tr>
								<td valign="top" width="50%">	
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText(divOrgData.getName())%>' />
									<table width="100%" valign="top" cellpadding="0" cellspacing="0">
					   					<tr class="linetwo">
											<th align="left"> <%=messages.getText(labels[indice*3])%> </th>
											<td align="right"><%=(itemData == null) ? "---" : itemData.getName()%></td>
										</tr>		
<%
			if(item1.getSubItems() != null) {
						ItemData item2 = (ItemData)item1.getSubItems().getItemIterator().next();
						itemData = filter.getDivOrgItem(indice, 2, item2.getId(), Filter.USER);
		%>										<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr class="linetwo">
											<th align="left"> <%=messages.getText(labels[indice*3+1])%> </th>
											<td align="right"><%=(itemData == null) ? "---" : itemData.getName()%></td>
										</tr>
<%
	if(item2.getSubItems() != null) {
			ItemData item3 = (ItemData)item2.getSubItems().getItemIterator().next();
			itemData = filter.getDivOrgItem(indice, 3, item3.getId(), Filter.USER);
%>										<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
										<tr class="linetwo">
											<th align="left"> <%=messages.getText(labels[indice*3+2])%> </th>
											<td align="right"><%=(itemData == null) ? "---" : itemData.getName()%></td>
										</tr>
<%
	}
	}
%>
									</table>
  									<jsp:include page="../component/utilityboxbottom.jsp"/>	
   								</td>
   							</tr>
   							<tr><td height="5"><img src="x" width="1" height="1"></tr>
<%
	}
		indice++;
	}
%>
						</table>	
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.vehicledata")%>' />
<%
	if(fineData.getVehicle() == null) {
%>		
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="270" class="linetwo">
									<table width="100%">
										<tr>
								   			<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.plate")%></th>
				         					<td align="right" class="linetwo">
				         						<%=fineData.getPlate()%>
				         					</td>
			         					</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.vehicle.vehicledata.trade")%></th>
				         					<td align="right" class="linetwo">
				         						<%=messages.getText(fineData.getTrade())%>
				         					</td>
										</tr>
									</table>
								</td>
								<td width="16"></td>
								<td width="270">
									<table width="100%">
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.model")%> </th>
				         					<td align="right" class="linetwo">
				         						<%=fineData.getModel()%>
				         					</td>
										</tr>
		      							<tr  class="space">
		        							<th align="right">&nbsp;</th>
		        							<td align="right">&nbsp;</td>
		      							</tr>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.vehicle.vehicledata.year")%> </th>
				         					<td align="right" class="linetwo">
				         						<%=messages.getText(fineData.getYear())%>
		              							</td>
										</tr>				  
									</table>
								</td>
							</tr>
						</table>
<%
	}else {
  					VehicleData vehicle = fineData.getVehicle();
%>						<table>
							<tr>
								<td width="270" class="linetwo">
									<table>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.event.finedata.plate")+":"%> </th>
				         					<td align="right" class="linetwo"><%=vehicle.getMatricula()%></td>
			         					</tr>
		      							<tr  class="space">
		        							<th align="right">&nbsp;</th>
		        							<td align="right">&nbsp;</td>
		      							</tr>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.event.finedata.trade")+":"%> </th>
				         					<td align="right" class="linetwo"><%=messages.getText(vehicle.getTrade().getResourceBundleId())%></td>
										</tr>
									</table>
								</td>
								<td width="16"></td>
								<td width="270">
									<table>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.event.finedata.model")+":"%> </th>
				         					<td align="right" class="linetwo"><%=vehicle.getModel()%></td>
										</tr>
		      							<tr  class="space">
		        							<th align="right">&nbsp;</th>
		        							<td align="right">&nbsp;</td>
		      							</tr>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.event.finedata.year")+":"%> </th>
				         					<td align="right" class="linetwo"><%=vehicle.getYear()%></td>
										</tr>				  
									</table>
								</td>
							</tr>
						</table>    
<%
    	}
    %>						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
	  			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
				<tr>
					<td colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.finedata")%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="270">
									<table width="100%">
										<tr>
			        						<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.date")%></th>
			        						<td align="right" class="linetwo">
			        							<%=Util.formatDate(fineData.getDate())%>
			          						</td>
			          					</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
			        						<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.hour")%></th>
			        						<td align="right" class="linetwo">
	         									<%=(fineData.getHour() == null) ? messages.getText("generic.messages.nodata") : Util.formatHour(fineData.getHour())%>
			          						</td>
			          					</tr>
<%
	if(sys.isCorporation(CorporationData.JJ)) {
%>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
			        						<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.expiry")%></th>
			        						<td align="right" class="linetwo">
			        							<%=Util.formatDate(fineData.getExpiry())%>
			          						</td>
			          					</tr>
<%						}
						if(!(sys.isCorporation(CorporationData.JJ) && driver.getCountryName().equals(CountryData.MEXICO))) {
%>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo"> 
										   		<%=messages.getText("generic.event.finedata.type")%>
				         					</th>
			        						<td align="right" class="linetwo">
										   		<%=messages.getText(fineData.getFineType().getResourceBundleId())%>
			          						</td>
										</tr>
<%						}
%>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
									</table>
								</td>
								<td width="16">&nbsp;</td>
								<td width="270" valign="top">
									<table width="100%">
										<% if(sys.isCorporation(CorporationData.JJ) && fineData.getQualify().getResourceBundleId().equals("datatype.finequalify.speedexceed")){ %>
											<tr tag="showSpeed"  >
											   	<th align="left" class="linetwo"> 
											   		<%=messages.getText("generic.event.fine.speed")%>
					         					</th>
				        						<td align="right" class="linetwo">
	                								<%=(fineData.getSpeed() == null) ? "----" : fineData.getSpeed()%>
	                							</td>
											</tr>
			      							<tr tag="showSpeed"   class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
											<tr tag="showSpeed"  >
											   	<th align="left" class="linetwo"> 
											   		<%=messages.getText("generic.event.fine.speedpermitted")%><span class="required">*</span>
					         					</th>
				        						<td align="right" class="linetwo">
					         						<%=(fineData.getPermittedSpeed() == null) ? "----" : fineData.getPermittedSpeed()%>
				          						</td>
											</tr>
											<tr tag="showSpeed"   class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<% } %>
										<tr>
										   	<th align="left" class="linetwo"> 
										   		<%=messages.getText("generic.event.finedata.article")%>
				         					</th>
			        						<td align="right" class="linetwo">
										   		<%=(fineData.getArticle() == null) ? "----" : fineData.getArticle()%>
			          						</td>
										</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo"> 
										   		<%=messages.getText("generic.event.finedata.points")%>
				         					</th>
			        						<td align="right" class="linetwo">
										   		<%=fineData.getPoints()%>
			          						</td>
										</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo"> 
										   		<%=messages.getText("event.finedata.cost")%>
				         					</th>
			        						<td align="right" class="linetwo">
										   		<%=String.valueOf(fineData.getCost())%>
			          						</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="3" width="100%">
									<table width="100%">
										<tr>
										   	<th align="left" class="linetwo"> 
										   		<%=messages.getText("generic.event.finedata.qualify")%>
				         					</th>
			        						<td align="left" class="linetwo" >
										   		<%=messages.getText(fineData.getQualify().getResourceBundleId())%>
			          						</td>
										</tr>
									</table>
								</td>
							</tr>					
				  			<tr>
								<jsp:include  page="../component/spaceline.jsp" />
				  			</tr>
							<tr>
								<td colspan="3" width="100%">
									<table width="100%">
										<tr>
											<!-- Notificaciòn de Infracción -->
											<th align="left" class="linetwo"> 
							   					<%=messages.getText("event.eventdata.infringementnotice")%>
	         								</th>
	       									<td align="right" class="linetwo"><%=(fineData.getInfringementNotice()==null)?"----":fineData.getInfringementNotice() %>
	         									</td>
	         								</tr>
									</table>
								</td>
							</tr>
<%					if (sys.isCorporation(new int[]{CorporationData.ALLERGAN, CorporationData.ECOLAB})){ 
%>
							<tr>
								<td colspan="3" width="100%">
									<table width="100%">
										<tr>
											<th align="left" class="linetwo"><%=messages.getText("generic.event.eventdata.lote")%>&nbsp;<%=messages.getText("generic.event.eventdata.loteformat")%></th>
			        						<td align="right" class="linetwo">
<% 						if (fineData.getLote() != null){
			        		Calendar c = Calendar.getInstance();
   							c.setTime(fineData.getLote()); 
%>			        							<%= String.valueOf(c.get(Calendar.MONTH)+1) + "/" + String.valueOf(c.get(Calendar.YEAR))%>
<%						}else{ 
%>			        							----
<%     					} %>
			        						</td>
         								</tr>
									</table>
								</td>
							</tr>
<%					}
%>						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
	  			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
				<tr>
					<td width="100%" colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.finedata.observations")+"*"%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="270" class="linetwo">
									<%=(fineData.getObservations() == null) ? messages.getText("generic.event.noobservations") : fineData.getObservations()%>
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
	  				<td colspan="3">                     
						<jsp:include  page='../component/utilitybox4top.jsp' />
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
						<jsp:include  page="../component/utilitybox4bottom.jsp" />
	  				</td>
	  			</tr>
	  		</table>
		</html:form>
	</body>
</html:html>
