<%@page language="java"
	errorPage="../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="business.vehicle.*" 
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.corporation.tables.*"
	import="communication.administration.user.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.util.*"	
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.vehicle.*"
	import="communication.administration.user.UserSessionData" 	
%>
<%
	UserSessionData userSessionData;
	Text messages;
	SystemAccess sys;
	String id;
	boolean hasVehicleCheck = false;
	VehicleData data; VehicleReportFacade vehicleReport;
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();
	vehicleReport=sys.getVehicleReportFacade();
	messages=sys.getText();
	id=(String)request.getParameter("id");
	if(id==null){
		id=(String)session.getAttribute("id");	
	}
	data=vehicleReport.findVehicleByPrimaryKey(new Integer(id),userSessionData); 
	if (data != null)
		hasVehicleCheck = vehicleReport.vehicleCheckExist(new Integer(id));
	
%>			
				<!-- Muestro Opciones: -->		    	
					<td colspan="5">
					<jsp:include  page='<%="../component/utilitybox3top.jsp?title="+messages.getText("generic.messages.options")%>' />
					<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="texts1">					
<%				boolean par = false;	
				if(userSessionData.isDriver()) {	
%>						<tr valign="bottom">
<%
	if(userSessionData.getDriverData().getCountryName().equals(CountryData.ARGENTINA) && (Util.checkPermission(sys,SecurityConstants.DRIVER_MODIFY_OWN_DATA) == null)) {
%>                			<td style="width:50%">
								<img align="middle" border="0" src="/datacenter/util/imgs/icon2.gif" width="12">&nbsp;
								<a href="#" onClick="javascript:document.forms['edit'].submit()" class="texts" >
									<%=messages.getText("generic.messages.edit")%>
								</a>
							</td> 
<%					}
%>						</tr>
<%				}else {
					if(userSessionData.checkPermission(SecurityConstants.MODIFY_VEHICLE)) {	
						if (!par){					
%>						<tr valign="bottom">
<%						}
%>		            		<td style="width:50%">
	                			<img align="middle" border="0" src="/datacenter/util/imgs/icon2.gif" width="12">&nbsp;
								<a href="#" onClick="javascript:document.forms['edit'].submit()" class="texts" >
									<%=messages.getText("generic.messages.edit")%>
								</a>
							</td>
<%						if (par) { 
%>						</tr>
<%						}
						par = !par;
					}
					if((userSessionData.checkPermission(SecurityConstants.MODIFY_VEHICLE)) && (data.getUse().getResourceBundleId().equals("persistence.datatype.typeuse.individual"))) {	 
						if (!par){					
%>						<tr  valign="bottom">
<%						} 
%>							<td style="width:50%">                		
	            	    		<img align="middle" border="0" src="/datacenter/util/imgs/icon2.gif" width="12">&nbsp;
								<a href="#" onClick="javascript:document.forms['changeToInstitutional'].submit()" class="texts">
									<%=messages.getText("generic.messages.changeToInstitutional")%>
								</a>
							</td>
<%						if (par) { 
%>						</tr>
<%						}
						par = !par;			
					}
					if((userSessionData.checkPermission(SecurityConstants.MODIFY_VEHICLE)) && (data.getUse().getResourceBundleId().equals("persistence.datatype.typeuse.individual"))) {	
						session.setAttribute("idVehicle",id); 
						if (!par){					%>
						<tr  valign="bottom">
					<%	} %>
							<td style="width:50%">    
							<!-- Opcion de Cambiar Conductor: -->
								<img align="middle" border="0" src="/datacenter/util/imgs/icon2.gif" width="12">&nbsp;
								<a href="#"onClick="javascript:document.forms['changeDriver'].submit()" class="texts">
									<%=messages.getText("generic.messages.changeDriver")%>
								</a>
							</td>
	<%					if (par) { %>
						</tr>
					<%	}
						par = !par;				
					}
					if((userSessionData.checkPermission(SecurityConstants.MODIFY_VEHICLE)) && (data.getUse().getResourceBundleId().equals("persistence.datatype.typeuse.institutional"))) {	
						session.setAttribute("idVehicle",id);			
						if (!par){					%>
						<tr valign="bottom">
					<%	} %>
							<td style="width:50%">    
	                			<!--  Opción de Asiganr Conductor: -->
		                		<img align="middle" border="0" src="/datacenter/util/imgs/icon2.gif" width="12">&nbsp;
								<a href="#" onClick="javascript:document.forms['assignDriver'].submit()" class="texts">
									<%=messages.getText("generic.messages.assignDriver")%>
								</a>
							</td>
	<%					if (par) { %>
						</tr>
					<%	}
						par = !par;								
					}
					
					if((userSessionData.checkPermission(SecurityConstants.MODIFY_VEHICLE)) && (hasVehicleCheck)) {	
						session.setAttribute("idVehicle",id);			
						String href = "layout.jsp?refer=/vehicle/LastVehicleCheckView.jsp" + "?id=" + id;
						if (!par){					%>
						<tr valign="bottom">
					<%	} %>
							<td style="width:50%">    
	                			<!--  Opción de Asiganr Conductor: -->
		                		<img align="middle" border="0" src="/datacenter/util/imgs/icon2.gif" width="12">&nbsp;
								<a href='<%=href %>' class="texts">
									<%=messages.getText("generic.messages.lastvehiclecheckview")%>
								</a>
							</td>
	<%					if (par) { %>
						</tr>
					<%	}
						par = !par;								
					}										
					
					if((userSessionData.checkPermission(SecurityConstants.MODIFY_VEHICLE))) {	
						session.setAttribute("idVehicle",id);			
						String href = "layout.jsp?refer=/vehicle/VehicleCheckForm.jsp" + "?id=" + id;
						if(sys.getCorporationId() == CorporationData.PACIFICRUBIALES) {
							href = "layout.jsp?refer=/vehicle/SimpleCheckList.jsp" + "?id=" + id +"&plate="+data.getMatricula();
						}
						if (!par){					%>
						<tr valign="bottom">
					<%	} %>
							<td style="width:50%">    
	                			<!--  Opción de Asiganr Conductor: -->
		                		<img align="middle" border="0" src="/datacenter/util/imgs/icon2.gif" width="12">&nbsp;
								<a href='<%=href %>' class="texts">
									<%=messages.getText("generic.messages.addvehiclecheck")%>
								</a>
							</td>
	<%					if (par) { %>
						</tr>
					<%	}
						par = !par;								
					}					
				
					
					
					if((userSessionData.checkPermission(SecurityConstants.QUERY))) {	
						session.setAttribute("idVehicle",id);			
						String href = "layout.jsp?refer=/vehicle/VehicleDriversForm.jsp" + "?idVehicle=" + id;
						if (!par){					%>
						<tr valign="bottom">
					<%	} %>
							<td style="width:50%">
		                		<img align="middle" border="0" src="/datacenter/util/imgs/icon2.gif" width="12">&nbsp;
								<a href='<%=href %>' class="texts">
									<%=messages.getText("generic.messages.viewdriverlist")%>
								</a>
							</td>
	<%					if (par) { %>
						</tr>
					<%	}
						par = !par;								
					}	
					
					
					
					
					
					if(data.getExclusionDate() != null) {	
						if (!par){					%>
						<tr valign="bottom">
					<%	} %>					
							<td style="width:50%">  
								<!--  Opcion de Inclsuion: -->
								<img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;
								<a href="javascript:setAction(document.forms['exclusion'],'include');deleteIFConfirm(document.forms['exclusion'],'<%=messages.getText("generic.driver.driverdata.confirminclude")%>');" class="texts")>
				         			<%=messages.getText("generic.driver.include")%>
				         		</a>
				         	</td>
	<%					if (par) { %>
						</tr>
					<%	}
						par = !par;		
					}
					else {
						if(userSessionData.checkPermission(SecurityConstants.DELETE_VEHICLE)) {	
							if (!par){					%>
						<tr valign="bottom">
						<%	} %>
							<td style="width:50%"> 	
								<!-- Opcion de Exclusion: -->
	        					<img align="middle" border="0" src="/datacenter/util/imgs/icon2.gif" width="12">&nbsp;
								<a href="javascript:setAction(document.forms['exclusion'],'exclude');deleteIFConfirm(document.forms['exclusion'],'<%=messages.getText("generic.driver.driverdata.confirmexclude")%>');" class="texts">
				    	     		<%=messages.getText("generic.driver.exclude")%>
				        	 	</a>
				        	</td>
				        <%  if (par) { %>
						</tr>
					<%		}
					        par = !par;	
					    	if (!par){					%>
						<tr valign="bottom">
							<%	} %>
					       	<td style="width:50%">  						
	        					<!-- Opcion de Borrado: -->
		        				<img align="middle" border="0" src="/datacenter/util/imgs/icon2.gif" width="12">&nbsp;
								<a href="javascript:setAction(document.forms['exclusion'],'delete');deleteIFConfirm(document.forms['exclusion'],'<%=messages.getText("generic.driver.driverdata.confirmdelete")%>');" class="texts">
									<%=messages.getText("generic.messages.delete")%>
								</a>
							</td>
	<%						if (par) { %>
						</tr>
					<%		}
							par = !par;	
						}
					}
				}
				if(data.getCountryId().intValue() == CountryData.ARGENTINA_ID.intValue()
						&& sys.getCorporationId() == CorporationData.MONSANTO) {
					String maintenanceLink = "layout.jsp?refer=vehicle/maintenance.jsp?vehicle="+String.valueOf(data.getId());
			    	if (!par){					
%>						<tr valign="bottom">
<%					} 
%>					       	<td style="width:50%">  						
        						<!-- Opcion de Borrado: -->
	        					<img align="middle" border="0" src="/datacenter/util/imgs/icon2.gif" width="12">&nbsp;
								<a href='<%=maintenanceLink%>' class="texts">
									<%=messages.getText("generic.vehicle.addmaintenance")%>
								</a>
							</td>
<%					if (par) { 
%>						</tr>
<%					}
					par = !par;	
				}						
				if (!par){					
%>
					<tr valign="bottom">
			<%	} %>       	
						<td style="width:50%"> 	
							<img align="middle" border="0" src="/datacenter/util/imgs/icon2.gif" width="12">&nbsp;
							<a href="#" onClick="javascript:document.forms['cancel'].submit()" class="texts">
								<%=messages.getText("generic.messages.cancel")%>
							</a>
						</td>
					</tr>
					</table>
					<jsp:include page="../component/utilitybox3bottom.jsp"/>
				</td>
	  			<!-- Fin Opciones -->