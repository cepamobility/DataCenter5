<%@page language="java"
    errorPage="../exception.jsp"
	import="business.*"
	import="business.administration.user.*"
	import="business.driver.*"	
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.vehicle.tables.*"	
	import="communication.datatype.tables.*"
	import="communication.datatype.*"
	import="communication.administration.user.tables.*"
	import="communication.administration.corporation.tables.*"
	import="presentation.translator.web.util.*"
	import="java.util.*"
	import="java.text.*"
	import="communication.util.*"
    import="presentation.translator.web.vehicle.*"	
	import="communication.security.*"    
	import="communication.administration.user.UserSessionData"		
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>

<html:html>
<%! RequestDispatcher dispatcher; 
	Text messages; String pathLanguage;
	SystemAccess sys; 
	UserSessionData userSessionData;	
%>

<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();
	String check = Util.checkPermission(sys,SecurityConstants.EDIT_VEHICLE);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else { 		
		
		pathLanguage="/util/jsp/language.jsp";
		
		String url = "vehicle/TrailerView.jsp";			

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		messages=sys.getText();		

		TrailerData data = null;
		ListResult vehicles = null;
		int trailerId = 0;
		if(!Util.empty(request.getParameter("trailer"))) {
		    data = sys.getVehicleReportFacade().findTrailer(new Integer(request.getParameter("trailer")),userSessionData);
		    trailerId = (new Integer(request.getParameter("trailer"))).intValue();
		    vehicles = sys.getVehicleReportFacade().findTrailerVehicles(new Integer(request.getParameter("trailer")),userSessionData);
		}else if(session.getAttribute("trailer") != null) {
		    data = sys.getVehicleReportFacade().findTrailer((Integer)session.getAttribute("trailer"),userSessionData);
		    trailerId = ((Integer)session.getAttribute("trailer")).intValue();
		    vehicles = sys.getVehicleReportFacade().findTrailerVehicles((Integer)session.getAttribute("trailer"),userSessionData);
		}		    
		
		String cancel = "layout.jsp?refer=/vehicle/TrailerList.jsp";
		if(!Util.empty(request.getParameter("vehicle"))) {
		    cancel = "layout.jsp?refer=/vehicle/VehicleView.jsp?id="+request.getParameter("vehicle");
		}else{
			cancel = "javascript:history.back();";
		}
		
		if(data != null) {	
			Format dateFormat;	
			dateFormat = new SimpleDateFormat("dd-MM-yyyy");	
%>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<script language="JavaScript" type="text/javascript">
	function confirmDelete(form,msg){
		if(confirm(msg)){
			form.submit();
		}
	}
</script>

<head/>
<body bgcolor="#FFFFFF">
	<form action='layout.jsp?refer=/vehicle/TrailerExclusion.jsp' name="delete" method="post">
		<input type="hidden" name="checkboxParamList" value='<%=String.valueOf(data.getId())%>'/>
		<input type="hidden" name="action" value="delete" />								
	</form>
	
	<form action='layout.jsp?refer=/vehicle/createTrailer.jsp?type=edit&trailer=<%=String.valueOf(data.getId())%>' 
		name='edit' method='post'>
	</form>	
	
	<form action="layout.jsp?refer=/vehicle/TrailerExclusion.jsp" name="exclusion" method="post">
		<input type="hidden" name="checkboxParamList" value='<%=String.valueOf(data.getId())%>' />		
		<input type="hidden" name="action" value='<%=(data.getExclusionDate() != null) ? "include" : "exclude" %>' />								
	</form>

	<form action='<%=cancel%>' name='back' method="post">
	</form>	


		<table width="600" border="0" cellspacing="0" cellpadding="0" align="center">
 			<tr class="guide1">
				<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("vehicle.trailer.edit")%>' />
  			</tr>
  			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
		  	<tr>			  		
		  		<jsp:include page='<%="TrailerViewOptions.jsp?excluded="+String.valueOf(data.getExclusionDate() != null)%>' />
		  	</tr>
		  	<tr>
				<jsp:include  page='<%="../component/spaceline.jsp?colspan=3" %>'/>
		  	</tr>
			<tr>
				<td width="292" valign="top">
                   	<table width="100%">
<%				if(data.getExclusionDate() != null) {	
%>  					<tr>
							<td>
								<span class="red"><%=messages.getText("generic.trailer.excluded") %></span>
							</td>
					  	</tr>
						<tr>
							<td>
								<span class="red">
									<%=messages.getText("generic.driver.excludeddate")+":"+Util.formatDate(data.getExclusionDate())%>
								</span>				
							</td>
					  	</tr>
					   	<tr>
							<jsp:include  page="../component/spaceline.jsp" />
					  	</tr>
<%				}
%>			  			<tr>
			    			<td valign="top">
								<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.administrativedata")%>' />
			        			<table width="100%" cellpadding="0" cellspacing="0">
			          				<tr class="linetwo">
			            				<th align="left"> 
			            					<%=messages.getText("generic.vehicle.vehicledata.registrationNumber")%>
			            				</th>
			            				<td align="right">
			                				<%=data.getPlate()%>
			            				</td>
			          				</tr>
									<tr  class="space">
										<th align="right">&nbsp;</th>
										<td align="right">&nbsp;</td>
									</tr>
			          				<tr class="linetwo">
			            				<th align="left" > 
			            					<%=messages.getText("generic.vehicle.vehicledata.trade")%>
		        	    				</th>
		            					<td align="right" >
			                				<%=(data.getTrade().getResourceBundleId().equals("generic.messages.nodata")) ? messages.getText("generic.messages.nodata") : data.getTrade().getResourceBundleId()%>
		            					</td>
			          				</tr>
									<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			          				<tr class="linetwo">
		    	        				<th width="224" align="left"> <%=messages.getText("generic.vehicle.vehicledata.model")%></th>
		        	    				<td align="right">
			                				<%=data.getModel()%>
		            					</td>
		          					</tr>
									<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			          				<tr class="linetwo">
			            				<th align="left"> 
			            					<%=messages.getText("generic.vehicle.vehicledata.year")%>
		        	    				</th>
		            					<td align="right">
			                				<%=String.valueOf(data.getYear())%>
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
							<td valign="top">
								<% 
									String include1 = "../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.divgeo"); 
								%>
								<jsp:include  page='<%=include1%>' />
<%			if(data.getCountry() != null) {
	    		GeoItemData geoItem1 = (GeoItemData)data.getCountry().getItemIterator().next();
%>								<table width="100%" height="100%" cellpadding="0" cellspacing="0">
									<tr class="linetwo">
										<th align="left"> <%=messages.getText(data.getCountry().getResourceKey())%> </th>
										<td align="right">
											<%=(geoItem1.getSubItems() != null) ? geoItem1.getName() : messages.getText(geoItem1.getName()) %>
										</td>
									</tr>
<%			    if(geoItem1.getSubItems() != null) {
					GeoItemData geoItem2 = (GeoItemData)geoItem1.getSubItems().getItemIterator().next();
%>									<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
									<tr class="linetwo">
										<th align="left"> <%=messages.getText(geoItem1.getSubItems().getResourceKey())%> </th>
										<td align="right">
											<%=(geoItem2.getSubItems() != null) ? geoItem2.getName() : messages.getText(geoItem2.getName()) %>
										</td>
									</tr>
<%				    if(geoItem2.getSubItems() != null) {
						GeoItemData geoItem3 = (GeoItemData)geoItem2.getSubItems().getItemIterator().next();
%>									<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
									<tr class="linetwo">
										<th align="left"> <%=messages.getText(geoItem2.getSubItems().getResourceKey())%> </th>
										<td align="right">
											<%=messages.getText(geoItem3.getName())%>
										</td>
									</tr>
<%					}
				}
%>								</table>	
<%			}
%>	
				   			<jsp:include page="../component/utilityboxbottom.jsp"/>	
					   		</td>
		   				</tr>
				   		<tr><td height="5"><img src="x" width="1" height="1"></tr>
					</table>
				</td>
				<td width="16" >&nbsp;</td>
				<td width="292" valign="top" rowspan="3">
					<table width="100%" height="100%" cellpadding="0" cellspacing="0">
<%				Iterator it = data.getDivOrgsIterator();
				while(it.hasNext()) {		
					DivOrgData divOrgData = (DivOrgData)it.next();
					if(divOrgData.getDivOrgLevels() != null) {
						ItemData item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
%>						<tr>
							<td valign="top" width="50%">
								<%
									String include2 = "../component/utilityboxtop.jsp?title="+messages.getText(divOrgData.getName());
								%>	
								<jsp:include  page='<%=include2%>' />
								<table width="100%" cellpadding="0" cellspacing="0">
									<tr class="linetwo">
										<th align="left"> <%=messages.getText(divOrgData.getDivOrgLevels().getName())%> </th>
										<td align="right"><%=item1.getName()%></td>
									</tr>
<%	    				if(item1.getSubItems() != null) {
							ItemData item2 = (ItemData)item1.getSubItems().getItemIterator().next();
%>									<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
									<tr class="linetwo">
										<th align="left"> <%=messages.getText(item1.getSubItems().getName())%> </th>
										<td align="right"><%=item2.getName()%></td>
									</tr>
<%				    		if(item2.getSubItems() != null) {
								ItemData item3 = (ItemData)item2.getSubItems().getItemIterator().next();
%>									<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
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
				<td width="100%" valign="top" colspan="3">
                   	<table width="100%">
			  			<tr>
			    			<td width="100%" valign="top">
								<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.technicaldata")%>' />
			        			<table width="100%" cellpadding="0" cellspacing="0">
		          					<tr class="linetwo">
		            					<th align="left">
		            						<%=messages.getText("generic.vehicle.vehicledata.costCenter")%>
		        	    				</th>
		    	        				<td align="right">
			                				<%=data.getCostCenter()%>
			            				</td>
			          				</tr>
									<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		          					
		          					<%
		          					if (sys.getUserSessionData().getFilter().getCorporationId().
		          							equals(new Integer(CorporationData.KLABIN))){%>
		          							<tr class="linetwo">
		            					<th align="left">
		            						<%=messages.getText("generic.vehicle.vehicledata.typeplatform")%>
		        	    				</th>
		    	        				<td align="right">
			                				<%=(data.getPlatformType()!=null && data.getPlatformType().getResourceBundleId() != null)?messages.getText(data.getPlatformType().getResourceBundleId()):"----"%>
			            				</td>
			          				</tr>
									<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		          					<% } %>
		          					
		          					<tr class="linetwo">
		            					<th align="left">
		            						<%=messages.getText("generic.vehicle.vehicledata.permissionNumber")%> 
		        	    				</th>
		    	        				<td align="right">
			                				<%=(data.getPermissionNumber() != null)? data.getPermissionNumber():"----" %>
			            				</td>
			          				</tr>
									<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			          				<tr class="linetwo">
            							<th align="left"> 
            								<%=messages.getText("generic.vehicle.vehicledata.permissionExpiryDate")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%>
			            				</th>
            							<td align="right">
			                				<%=(data.getPermissionExpiry() != null)?Util.formatDate(data.getPermissionExpiry()):"----"%>
		            					</td>
			          				</tr>
									<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			          				<tr class="linetwo">
            							<th align="left"> 
            								<%=messages.getText("generic.vehicle.vehicledata.purchaseDate")%><%=" ( "+messages.getText("generic.messages.dateformat")+" )"%>
			            				</th>
            							<td align="right">
			                				<%=(data.getPurchaseDate() != null)?Util.formatDate(data.getPurchaseDate()):"----"%>
            							</td>
          							</tr>
									<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			          				<tr class="linetwo">
            							<th align="left" > 
            								<%=messages.getText("generic.vehicle.vehicledata.ownership")%>
			            				</th>
            							<td align="right">
			                				<%=(data.getOwnerShip() == null) ? "----" : messages.getText(data.getOwnerShip().getResourceBundleId())%>
            							</td>
          							</tr>
									<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
									<tr class="linetwo">
										<td align="left" class="texts"><%=messages.getText("vehicle.data.sucta")%></td>
										<td align="right" class="linetwo">
											<% String sunca = (data.getSuctaExpiry() == null)?null:Util.formatDate(data.getSuctaExpiry()); %>
											<%=(!Util.empty(sunca)) ? sunca : "----"%>
										</td>
									</tr>
									<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
									<tr class="linetwo">
										<td align="left" class="texts"><%=messages.getText("vehicle.data.mtop")%></td>
										<td align="right" class="linetwo">
											<% String mtop = (data.getSuctaExpiry() == null)?null:Util.formatDate(data.getMtopExpiry()); %>
											<%=(!Util.empty(mtop)) ? mtop : "----"%>
										</td>
									</tr>
									<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
									<tr>
						       		<tr class="linetwo">
			            				<th align="left" > 
		    	        					<%=messages.getText("generic.vehicle.vehicledata.colour")%> 
		        	    				</th>
		            					<td align="right">
			                				<%=(data.getColour()!=null)?data.getColour():"----"%>
		            					</td>
		          					</tr>
									<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			          				<tr class="linetwo">
			            				<th width="224" align="left"> 
		    	        					<%=messages.getText("generic.vehicle.vehicledata.chasisNumber")%> 
		        	    				</th>
		            					<td align="right">
			                				<%=(data.getChasisNumber() != null)?data.getChasisNumber():"----"%>
		            					</td>
		          					</tr>
									<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			          				<tr class="linetwo">
			            				<th align="left" > 
		    	        					<%=messages.getText("generic.vehicle.vehicledata.cover")%>
		        	    				</th>
		            					<td align="right">
			                				<%=(data.getCover() == null) ? "----" : messages.getText(data.getCover().getResourceBundleId())%>
		            					</td>
			          				</tr>
									<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
          							<tr class="linetwo">
            							<th align="left"> 
			            					<%=messages.getText("vehicle.data.ensurancetype")%> 
            							</th>          
            							<td align="right">
			                				<%=(data.getEnsuranceType() == null) ? "----" : messages.getText(data.getEnsuranceType().getResourceBundleId())%>
    	    	    					</td>
	    	    	  				</tr>
									<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		          					<tr class="linetwo">
		            					<th align="left"> 
		            						<%=messages.getText("vehicle.data.ensurance")%> 
		        	    				</th>          
		    	        				<td align="right">
			                				<%=(data.getEnsurance()!=null)?data.getEnsurance():"----"%>
			            				</td>
			          				</tr>
									<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
          							<tr class="linetwo">
            							<th width="224" align="left"> 
			            					<%=messages.getText("vehicle.data.axiscount")%> 
            							</th>
            							<td align="right">
			                				<%=(data.getAxisCount()!=null)?String.valueOf(data.getAxisCount()):"----"%>
            							</td>
			          				</tr>
			          				<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
          							<tr class="texts">
            							<th width="224" align="left"> 
			            					<%=messages.getText("generic.vehicle.vehicledata.type")%>
            							</th>
            							<td align="right">
				                			<%=((data.getType()!=null && data.getType().trim().length()>0)?data.getType():"-----")%>
            							</td>
			          				</tr>
			          				<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		          					<tr class="texts">
		            					<th align="left"> 
		            						<%=messages.getText("generic.vehicle.vehicledata.abs")%>
		        	    				</th>          
		    	        				<td align="right">
			            					<%=(data.isAbs()?"SI":"NO")%>
			            				</td>
			          				</tr>
			          				<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		          					<tr class="texts">
		            					<th align="left"> 
		            						<%=messages.getText("generic.vehicle.vehicledata.suspension")%>
		        	    				</th>          
		    	        				<td align="right">
			            					<%=(data.isSuspension()?"SI":"NO") %>
			            				</td>
			          				</tr>
			          				<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		          					<tr class="texts">
		            					<th align="left"> 
		            						<%=messages.getText("generic.vehicle.vehicledata.weightcontrol")%>
		        	    				</th>          
		    	        				<td align="right">
			            					<%=(data.isWeightControl()?"SI":"NO") %>
			            				</td>
			          				</tr>
			        			</table>
      							<jsp:include page="../component/utilityboxbottom.jsp"/>  
      						</td>
      					</tr>
      				</table>
        		</td>
        	</tr>
  			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
			</tr>
			<!--  Vehiculos a los que esta asignada la plataforma -->
			<tr>
				<td width="100%" valign="top" colspan="3">
                   	<table width="100%">
			  			<tr>
			    			<td width="100%" valign="top">
								<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.vehicle")%>' />
			        			<table width="100%">
									<tr>
										<td width='32%' class="guide2" align="center">
											<%=messages.getText("vehicle.trailer.plate")%>
										</td>
					    				<td width='32%'	class="guide2" align="center">
					    					<%=messages.getText("vehicle.trailer.trade")%>
					    				</td>
										<td width='32%' class="guide2" align="center">
											<%=messages.getText("vehicle.trailer.model")%>
										</td>
									</tr>
									<%	if(vehicles == null) {
				%>					<tr>
										<td colspan="3">
											<span class="linetwo"><%=messages.getText("vehicle.data.novehicles")%></span>
										</td>								
									</tr>
									<%	}else {
											it = vehicles.getElements().iterator();
											while(it.hasNext()){
												VehicleData vehicle = (VehicleData)it.next();
												
				%>					<tr align=center align="right" class="linetwo">
				   						<td width='32%' align="right">
											<a href='layout.jsp?refer=/vehicle/VehicleView.jsp?id=<%=vehicle.getId()%>&trailer=<%=trailerId%>' > 
					           					<%=vehicle.getMatricula()%>
					           				</a>
					           			</td>
							            <td width='32%' align="right">
							            	<%=messages.getText(vehicle.getTrade().getResourceBundleId())%> 
							            </td>						                
							            <td width='32%' align="right">
							            	<%=messages.getText(vehicle.getModel())%> 
							            </td>						                
									</tr>
				<%    						}
									}
									%>
									
									
								</table>
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
		  		<jsp:include page="TrailerViewOptions.jsp" />
		  	</tr>
		  	<tr>
				<jsp:include  page='<%="../component/spaceline.jsp?colspan=3" %>'/>
		  	</tr>
		</table>
	</body>
<%		}
	}
%>
</html:html>
