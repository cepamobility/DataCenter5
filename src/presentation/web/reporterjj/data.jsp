<%@page language="java"
	errorPage="../exception.jsp"
	import="java.util.*"
	import="java.util.LinkedList"
	import="business.*"
	import="business.driver.*" 
	import="communication.language.*"
	import="communication.security.*"
	import="communication.util.*"
	import="communication.administration.corporation.tables.*"
	import="communication.administration.corporation.tables.ItemData"
	import="communication.administration.user.tables.*"
	import="communication.administration.user.UserSessionData"
	import="communication.driver.tables.*"
	import="communication.event.tables.*"
	import="communication.activity.tables.*"
	import="communication.vehicle.tables.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.driver.*"
 	import="communication.util.DateValue"
 	import="communication.administration.RoleData"	
	import="communication.driver.tables.UnconformityData"
	import="ws.assessment.datacenter.AssessmentWSEJBService" 
	import="ws.assessment.datacenter.AssessmentWSEJB"
	import="javax.xml.ws.BindingProvider"
	import="ws.assessment.datacenter.CourseData"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>


<html:html>

<%! 
	Text messages; 
	SystemAccess sys; 
	DriverData data; 
	UserSessionData userSessionData;
%>
<%
	try{

		sys = (SystemAccess)session.getAttribute("SystemAccess");
		userSessionData = sys.getUserSessionData(); 
		int corporationId = sys.getCorporationId();

		data = sys.getDriverReportFacade().findDriverByPrimaryKey(userSessionData.getDriverData().getId(), userSessionData);
		
		messages=sys.getText();
		String linkBack = (Integer.parseInt(request.getParameter("backForm")) == 1) ? "./cd.jsp" : "./form.jsp";
%>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head/>
	<body bgcolor="#FFFFFF">	
	 	<table width="100%" height="90" border="0" cellpadding="0" cellspacing="0" class="default">
		 	<form name="logout" action="../login.jsp" method="post">
	 		</form>
			<form name="vehicles" action="<%=linkBack%>" method="post"></form>		
  			<tr>
    			<td colspan="2">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" valign="top">
      					<tr>
        					<td><jsp:include  page='<%="../topMenu.jsp?home=true" %>'/></td>
      					</tr>
    				</table>	
    			</td><!--end the top�s table -->
  			</tr>
  		</table>
  		<br>
  		<br>
		<table width="700" align='center' cellpadding="0" cellspacing="0">
			<tr class="guide1">
				<jsp:include  page='<%="../component/titlecomponent.jsp?colspan=5&title="+messages.getText("web.driver.editing")+"  "+data.getLastName()+","+data.getFirstName()%>' />
			</tr>
	   		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
	  		</tr>
	  		<tr>
				<td width="50%" valign="top">
					<table width="100%" height="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="top">
								<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.personaldata")%>' />
								<table width="100%" border="0" align='left' cellpadding="0" cellspacing="0">
									<tr>
								    	<td class="default">
											<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
												<tr  class="linetwo">
													<th align="left"> <%=messages.getText("generic.driver.driverdata.firstname")%> </th>
													<td align="right"><%=data.getFirstName() %></td>
												</tr>
						   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
												<tr  class="linetwo">
													<th align="left"><%=messages.getText("generic.driver.driverdata.lastname")%></th>
													<td align="right"><%=data.getLastName() %></td>
												</tr>
						   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
												<tr  class="linetwo">
													<th align="left"> <%=messages.getText("generic.driver.driverdata.birthdate")%></th>
													<td align="right"><%=Util.formatDate(data.getBirthDate())%></td>
												</tr>
												<tr >
										 			<td align="right" colspan="2">
										 				<%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
										    		</td>
												</tr>
						   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
												<tr  class="linetwo">
													<th align="left" > <%=messages.getText("generic.driver.driverdata.sex")%> </th>
													<td align="right"><%=messages.getText(data.getSex().getResourceBundleId())%></td>
												</tr>												
												<!-- Nacionalidad -->			
						   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
												<tr class="linetwo">
													<th align="left"><%=messages.getText("generic.driver.driverdata.nationality")%> </th>
													<td align="right"><%=messages.getText(data.getNationality())%></td>
												</tr>
												<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
												<tr  class="linetwo">
													<th align="left"><%=messages.getText("generic.driver.driverdata.city")%></th>
													<td align="right"><%=data.getCity()%></td>
												</tr>
						   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
												<tr  class="linetwo">
									        		<th align="left"> <%=messages.getText("generic.driver.driverdata.personaladdress")%></th>
													<td align="right"><%=data.getPersonalAddress()%></td>
									      		</tr>
											</table>
								      	</td>
									</tr>		      	
								</table>

				   				<jsp:include page="../component/utilityboxbottom.jsp"/>	
							</td>
				   		</tr>
						<tr>
							<td height="10"><img src="x" width="1" height="1">
						</tr>
						<tr>
							<td width="30%" valign="top">
								<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.divgeo")%>' />
<%
	if(data.getCountry() != null) {
	    			GeoItemData geoItem1 = (GeoItemData)data.getCountry().getItemIterator().next();
%>
							<table width="100%" height="100%" cellpadding="0" cellspacing="0">
								<tr class="linetwo">
									<th align="left"> <%=messages.getText(data.getCountry().getResourceKey())%> </th>
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
						%>								<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
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
%>							</table>	
			   				<jsp:include page="../component/utilityboxbottom.jsp"/>	
			   			</td>
		   			</tr>
		   			<tr><td height="5"><img src="x" width="1" height="1"></tr>
			   	</table>
			</td>
			<td width="1%" valign="top"></td>
			<td width="50%" valign="top">
				<table width="100%" height="100%" cellpadding="0" cellspacing="0">
<%
	Iterator it = data.getDivOrgsIterator();
		while(it.hasNext()) {		
	DivOrgData divOrgData = (DivOrgData)it.next();
	if(divOrgData.getDivOrgLevels() != null) {
		ItemData item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
%>					<tr>
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
%>								<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
								<tr class="linetwo">
									<th align="left"> <%=messages.getText(item1.getSubItems().getName())%> </th>
									<td align="right"><%=item2.getName()%></td>
								</tr>
<%
	if(item2.getSubItems() != null) {
				ItemData item3 = (ItemData)item2.getSubItems().getItemIterator().next();
%>								<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
								<tr class="linetwo">
									<th align="left"> <%=messages.getText(item2.getSubItems().getName())%> </th>
									<td align="right"><%=item3.getName()%></td>
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
	   	   		}
	   %>
				</table>	
			</td>
<%
	if(!sys.getUserSessionData().isDriver()) {
%>			<td width="1%" valign="top"></td>
			
		</tr>	
<%
		}
	%>		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
  		
		<tr>
			<td width="48%" valign="top">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.workinfo")%>' />
				<table width="100%" align='center' cellpadding="0" cellspacing="0">
					<tr  class="linetwo">
						<th align="left" > <%=messages.getText("generic.driver.driverdata.corporationid")%></th>
						<td align="right"><%=(data.getCorporationId()!=null)?data.getCorporationId():"----"%></td>
					</tr>
					
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left" > <%=messages.getText("generic.driver.driverdata.startdate")%></th>
						<td align="right"><%=(data.getInclusionDate() == null) ? "" : Util.formatDate(data.getInclusionDate())%></td>
					</tr>
			      	<tr  class="linetwo">
	        			<td align="right" colspan="2">
	        				<%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
            			</td>
	      			</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left" > <%=messages.getText("generic.driver.driverdata.email")%></th>
						<td align="right"><%=(data.getEmail()!=null)?data.getEmail():"----"%></td>
					</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left" > <%=messages.getText("generic.driver.driverdata.phonenumber")%></th>
						<td align="right"><%=(data.getPhoneNumber()!=null) ? data.getPhoneNumber():"----"%></td>
					</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left"> <%=messages.getText("generic.driver.driverdata.officeaddress")%></th>
						<td align="left">
							<div align="right"><%=(data.getOfficeAddress()!=null)?data.getOfficeAddress():"----"%></div>
						</td>
					</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left"> <%=messages.getText("generic.driver.driverdata.drives")%></th>
						<td align="left">
							<div align="right"><%=(data.isDrives()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no")%></div>
						</td>
					</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left"> <%=messages.getText("generic.driver.driverdata.employee")%></th>
						<td align="left">
							<div align="right"><%=(data.isEmployee()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no")%></div>
						</td>
					</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left"> <%=messages.getText("generic.driver.driverdata.contact")%></th>
						<td align="left">
							<div align="right"><%=(data.getContract() == null) ? "" : messages.getText(data.getContract().getResourceBundleId())%></div>
						</td>
					</tr>
					
        		</table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
			<td width="1%" valign="top"></td>
			<td width="48%" valign="top">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.licenseinfo")%>' />
				<table width="100%" align='center' cellpadding="0" cellspacing="0">
					<tr  class="linetwo">
						<th align="left" > <%=messages.getText("generic.driver.driverdata.licensenumber")%></th>
						<td align="right"><%=data.getLicenseNumber()%></td>
					</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left" > <%=messages.getText("generic.driver.driverdata.licenseget")%></th>
						<td align="right"><%=(data.getLicenseGet() == null) ? "" : Util.formatDate(data.getLicenseGet())%></td>
					</tr>
			      	<tr  class="linetwo">
	        			<td align="right" colspan="2">
	        				<%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
	           			</td>
	      			</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					
<%
						if (data.isLicenseNotExpiry()){
					%>
					<tr  class="linetwo">
						<th align="left" > <%=messages.getText("generic.driver.driverdata.licenseexpiry")%></th>
						<td align="right">
							<%=messages.getText("generic.driver.driverdata.licensenotexpiry")%>
						</td>
					</tr>
<%
	}else if (data.getLicenseExpiry() != null){
%>
					<tr  class="linetwo">
						<th align="left" > <%=messages.getText("generic.driver.driverdata.licenseexpiry")%></th>
						<td align="right">
							<%=Util.formatDate(data.getLicenseExpiry())%>
						</td>
					</tr>
			      	<tr  class="linetwo">
	        			<td align="right" colspan="2">
	        				<%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
            			</td>
	      			</tr>
<%
	}
%>				
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left"> <%=messages.getText("generic.driver.driverdata.licensecategory")%></th>
						<td align="right"><%=(data.getLicenseCategory() == null) ? "" : data.getLicenseCategory()%></td>
					</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left"> <%=messages.getText("generic.driver.driverdata.vehicleallowed")%></th>
						<td align="right"><%=(data.getVehicleAllowed() == null) ? "" : messages.getText(data.getVehicleAllowed().getResourceBundleId())%></td>
					</tr>
				</table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>
		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
		<tr>
			<td width="100%" colspan="5">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top">
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.vehicles")%>' />
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td width='32%' class="guide2" align="center">
										<%=messages.getText("generic.vehicle.vehicledata.registrationNumber")%>
									</td>
   									<td width='32%'	class="guide2" align="center">
   										<%=messages.getText("generic.vehicle.vehicledata.year")%>
   									</td>
				    				<td width='32%'	class="guide2" align="center">
				    					<%=messages.getText("generic.vehicle.vehicledata.country")%>
				    				</td>
								</tr>
<%
	Collection vehicles = sys.getVehicleReportFacade().findDriversVehicles(data.getId(), sys.getUserSessionData());
	if(vehicles.size() == 0) {
%>								<tr>
									<td colspan="5">
										<span class="linetwo"><%=messages.getText("generic.event.novehicles")%></span>
									</td>								
								</tr>
<%
	}else {
    			Iterator itVehicles = vehicles.iterator();
    			boolean lineone = false;
    			while(itVehicles.hasNext()) {
    			    VehicleMainAttributes vehicle = (VehicleMainAttributes)itVehicles.next();
%>									<tr align=center align="right" class='<%=(lineone) ? "lineone" : "linetwo"%>'>
	          							<td width='32%' align="center">
	              							<%=vehicle.getMatricula()%>
	              							
              							</td>
						                <td width='32%' align="center"><%=vehicle.getYear()%> </td>
						                <td width='32%' align="center"><%=messages.getText(vehicle.getCountryName())%> </td>						                
									</tr>
<%
	lineone = !lineone;
	   			}
	}
%>							</table>
							<jsp:include page="../component/utilityboxbottom.jsp"/>	
						</td>
			   		</tr>
			   	</table>
			</td>
		</tr>			
   		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
		<tr>
			<td width="100%" colspan="5">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top">
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.history")%>' />
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
					                <td width='30%' class="guide2" align="center"><%=messages.getText("generic.event.eventdata.date")%></td>
					                <td width='70%'	class="guide2" align="center"><%=messages.getText("generic.event.eventdata.clasification")%></td>
								</tr>
<%
	Collection total = sys.getActivityReportFacade().findActivityByDriver(data.getId(),sys.getUserSessionData(),false);
		Collections.sort((List)total);	
		Iterator itTotal = total.iterator();
    			boolean lineone = false;
    			boolean exist = false;
    			while(itTotal.hasNext()) {
    				CEPAActivityScheduleRegistryData  activity = (CEPAActivityScheduleRegistryData)itTotal.next();
    				String type = activity.getActivity().getType().getResourceBundleId();
    				String link = null;
    				if(type.equals(CEPAActivityConstants.REPORTERJJ_SELFEVALUATION) || type.equals(CEPAActivityConstants.REPORTERJJ_COMMENTARYDRIVE)) {
    					link = (type.equals(CEPAActivityConstants.REPORTERJJ_SELFEVALUATION)) ? "viewSelfEvaluation.jsp?id="+activity.getActivity().getActivityId() : "viewCommentaryDrive.jsp?id="+activity.getActivity().getActivityId();
    					link += "&history="+request.getParameter("backForm");
    				}
    				exist = true;
%>								<tr  class='<%=(lineone) ? "lineone" : "linetwo"%>'>
						        	<td width='30%' align="center">
<%
	if(link != null) {
%>       								<a href='<%=link%>' >
<%
	}
%>											<%=Util.formatDate(activity.getActivity().getDate())%>
<%					if(link != null) {
%>       								</a>
<%					}
%>									</td>    					
          							<td width='70%' align="center">
              							 <%=messages.getText(activity.getActivity().getType().getResourceBundleId())%> 
					              	</td>
			              		</tr>					              			            
<%    			}
				if(!exist){
%>								<tr>
									<td colspan="3">
										<span class="linetwo"><%=messages.getText("generic.event.nohistory")%></span>
									</td>								
								</tr>
<%				}
%>				    		</table>
							<jsp:include page="../component/utilityboxbottom.jsp"/>	
						</td>
			   		</tr>
			   	</table>
			</td>
		</tr>			
		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
<%		if(!sys.isCorporation(CorporationData.BAYER_MX)) {
%>			<tr>
				<td align="left" class="texts" colspan="3">
					<jsp:include  page="../component/utilitybox4top.jsp" />
					<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
						<tr class="lineone">
							<td align="right" class="lineone">
								<input type="button" value='<%=messages.getText("generic.messages.back")%>' onclick="javascript:document.forms['vehicles'].submit();">
							</td>
						</tr>
					</table>			
     					<jsp:include page="../component/utilitybox4bottom.jsp"/>
		      	</td>	
		  	</tr>
		  	<tr> 
		    	<jsp:include  page="../component/spaceline.jsp" />
		  	</tr>
<%		}
%>		</table>
		<br>
		<br>
		
	</body>
<%	}catch(Exception e){
		e.printStackTrace();
	}
%>
</html:html>