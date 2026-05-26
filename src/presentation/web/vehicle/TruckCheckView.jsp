<%@page import="communication.administration.dpa.UnconformityTypeDPADataType"%>
<%@page language="java" 
	errorPage="../exception.jsp" 
	import="business.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="presentation.translator.web.vehicle.*"
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.ActivityTypeDataType"
	import="business.vehicle.VehicleReportFacade"
	import="java.util.*"	
	import ="org.apache.struts.action.DynaActionForm"
	import ="communication.activity.tables.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<html:html>
<%!
	Text messages; SystemAccess sys;
	UserSessionData userSessionData; 
	VehicleData  vehicle;
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();	
	
	String check = Util.checkPermission(sys,SecurityConstants.EDIT_VEHICLE);
	String check1 = Util.checkPermission(sys,SecurityConstants.MODIFY_VEHICLE);	
	String check2 = Util.checkPermission(sys,SecurityConstants.CHECKLIST_VEHICLE);	
	
	if ((check!=null) && (check1!=null) && (check2!=null))  {
		response.sendRedirect(request.getContextPath()+check);	
	}else {
		messages=sys.getText();
	
		String idCheck = request.getParameter("check");
		if(Util.empty(idCheck)) {
		    idCheck = String.valueOf(session.getAttribute("check"));
		}

		session.setAttribute("url","vehicle/TruckCheckView.jsp?check="+idCheck);
		
		VehicleCheckData vehicleCheck = sys.getVehicleReportFacade().findVehicleCheck(new Integer(idCheck),userSessionData);
		session.setAttribute("id",String.valueOf(vehicleCheck.getVehicle().getId()));
%>	
<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<script>
	function deleteIfConfirm(form,msg){
		if(confirm(msg)) {
			form.submit();
		}
	} 
</script>

<html:form action="/TruckCheckListDelete">
	<html:hidden property="check" value='<%=idCheck%>'/>
</html:form>	

<form action='<%="layout.jsp?refer=/vehicle/TruckCheckForm.jsp?type=edit&check="+vehicleCheck.getVehicleCheckId().toString()%>' name='edit' method="post">
</form>	

<body>
	<center>
	<table width="768" border="0" cellspacing="0" cellpadding="0">  
		<tr class="guide1">
			<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.activity.form.truckchecklist")%>' />
  		</tr>
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
		<tr> 
			<td height="40" colspan="5" valign="middle">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.activity.form.tranningonthejob.proofdata")%>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
   	 				<tr> 
       					<td width="50%" align="left" class="linetwo">        
   							<%=messages.getText("generic.activity.date")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<%=Util.formatDate(vehicleCheck.getDate())%>
						</td>
   					</tr>
			      	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
     				<tr> 
        				<td width="50%" align="left" class="linetwo">        
       						<%=messages.getText("generic.driver.name")%>
						</td>
						<td width="50%" align="right" class="linetwo">		
							<%=vehicleCheck.getName()%>
        				</td>
     				</tr>
			      	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
     				<tr class="linetwo"> 
        				<td width="50%" align="left">        
       						<%=messages.getText("generic.driver.licenseNumber")%>
						</td>
						<td width="50%" align="right">		
							<%=vehicleCheck.getLicenseNumber()%>
        				</td>
     				</tr>     
			      	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
     				<tr class="linetwo">        
        				<td width="50%" align="left">        
       						<%=messages.getText("generic.activity.dateLicenseExpiry")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
						</td>
						<td width="50%" align="right">
							<%=Util.formatDate(vehicleCheck.getDateLicenseExpiry())%>
						</td>
     				</tr>
			      	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
     				<tr class="linetwo"> 
        				<td width="50%" align="left">        
       						<%=messages.getText("generic.activity.form.tranningonthejob.trade")%>
						</td>
						<td width="50%" align="right">		
							<%=(vehicleCheck.getVehicle().getTrade().equals("generic.messages.nodata")) ? messages.getText(vehicleCheck.getVehicle().getTrade().getResourceBundleId()) : vehicleCheck.getVehicle().getTrade().getResourceBundleId()%>
        				</td>
     				</tr>
			      	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
      				<tr class="linetwo">
        				<td width="50%" align="left">        
       						<%=messages.getText("generic.vehicle.model")%>
						</td>
						<td width="50%" align="right">		
							<%=vehicleCheck.getVehicle().getModel()%>                                                     
        				</td>
     				</tr>     
			      	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
      				<tr class="linetwo">  
        				<td width="50%" align="left">        
					        <%=messages.getText("generic.activity.form.vehiclecheck.plate")%>
						</td>
						<td width="50%" align="right">		
							<%=vehicleCheck.getVehicle().getMatricula()%>                                                     
        				</td>
     				</tr>
			      	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
      				<tr class="linetwo">
        				<td width="50%" align="left">        
					        <font size="1" face="Arial, Helvetica, sans-serif"> 
						        <%=messages.getText("generic.vehicle.serie")%>
							</td>
						<td width="50%" align="right">		
							<%=vehicleCheck.getSerie()%>                                                     
				        </td>
				     </tr>     
			      	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
      				<tr class="linetwo">  
				        <td width="50%" align="left">        
					        <%=messages.getText("generic.vehiclecheck.form.vehicleKm")%>        
						</td>
						<td width="50%" align="right">		
							<%=String.valueOf(vehicleCheck.getVehicleKm())%>
				        </td>
				     </tr>
				</table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>  
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
<%
	CheckListData checkList = sys.getVehicleReportFacade().getCheckList(CheckListData.TRUCK_CHECKLIST,sys.getUserSessionData());
		Iterator points = checkList.getSortPoints();
%>		<tr>
			<td valign="top">
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
					<tr>
<%
	for(int i = 0; i < 3; i++) {
    		if(i == 1) {
%>    		    		<td width="2%"></td>
<%
	}else {
%>						<td width="49%" valign="top">
							<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
<%
	int count = (i == 0) ? 4 : 4;
		for(int j = 0; j < count; j++) {
		    PointData point = (PointData)points.next();
		    if(!(point.getId().equals(new Integer(137))  && vehicleCheck.getVehicle().getType().getResourceBundleId().equals("datatype.vehicletype.trator"))) {
%>								<tr>
									<td valign="top">
										<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText(point.getText())%>' />
										<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
<%
	Iterator items = point.getSortItems();
	while(items.hasNext()) {
		ItemData item = (ItemData)items.next();
%>											<tr class="linetwo">
												<td width="50%" align="left">
		   											<%=messages.getText(item.getText())%>
												</td>	
<%
		String value = vehicleCheck.getValueFromItem(item.getId());
				if(value == null) {
				    value = "1";
				}
	%>												<td width="50%" align="right">
													<%=(value.equals("1")) ? messages.getText("generic.vehiclecheck.form.correct") : messages.getText("generic.vehiclecheck.form.incorrect")%>
												</td>
											</tr>
<%
	if(items.hasNext()) {
%>			      							<tr>
												<jsp:include  page="../component/spaceline.jsp" />
									  		</tr>
<%
	}
	}
%>										</table>
										<jsp:include  page="../component/utilityboxbottom.jsp" />
									</td>	
								</tr>	
<%
		if(j < count-1) {
	%>     							<tr>
									<jsp:include  page="../component/spaceline.jsp" />
						  		</tr>
<%
	}
		    }
		}
%>							</table>
						</td>
<%
	}
		}
%>					</tr>
				</table>
			</td>
		</tr>	
	  	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
<%
	Iterator checkTrailers = sys.getVehicleReportFacade().getCheckListTrailers(new Integer(idCheck),userSessionData).iterator();
		while(checkTrailers.hasNext()) {
    		TrailerCheckData checkData = (TrailerCheckData)checkTrailers.next();
%>		<tr> 
			<td width="100%" colspan="3" valign="middle">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("activity.trailer.checklist")%>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
      				<tr>
						<jsp:include  page="../component/spaceline.jsp" />
		  			</tr>
					<tr> 
						<td height="40" colspan="3" valign="middle">
							<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.activity.form.tranningonthejob.proofdata")%>' />
								<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
   	 								<tr> 
				       					<td width="50%" align="left"  class="lineone">        
   											<%=messages.getText("generic.activity.date")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
										</td>
										<td width="50%" align="right" class="lineone">
											<%=Util.formatDate(checkData.getDate())%>
										</td>
				   					</tr>
									<tr class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
				     				<tr> 
        								<td width="50%" align="left" class="lineone">        
				       						<%=messages.getText("generic.driver.name")%>
										</td>
										<td width="50%" align="right" class="lineone">		
											<%=(Util.empty(checkData.getName())) ? "----" : checkData.getName()%>
				        				</td>
     								</tr>
									<tr class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
				     				<tr class="lineone"> 
				        				<td width="50%" align="left">        
				       						<%=messages.getText("generic.driver.licenseNumber")%>
										</td>
										<td width="50%" align="right">		
											<%=(Util.empty(checkData.getLicenseNumber())) ? "----" : checkData.getLicenseNumber()%>
				        				</td>
				     				</tr>     
									<tr class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
				     				<tr class="lineone">        
				        				<td width="50%" align="left">        
				       						<%=messages.getText("generic.activity.dateLicenseExpiry")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
										</td>
										<td width="50%" align="right">
											<%=(checkData.getDateLicenseExpiry() == null) ? "----" : Util.formatDate(checkData.getDateLicenseExpiry())%>
										</td>
				     				</tr>
									<tr class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
				     				<tr class="lineone"> 
				        				<td width="50%" align="left">        
				       						<%=messages.getText("generic.activity.form.tranningonthejob.trade")%>
										</td>
										<td width="50%" align="right">		
											<%=messages.getText(checkData.getTrailer().getTrade().getResourceBundleId())%>
				        				</td>
				     				</tr>
									<tr class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
				      				<tr class="lineone">
				        				<td width="50%" align="left">        
				       						<%= messages.getText("generic.vehicle.model")%>
										</td>
										<td width="50%" align="right">		
											<%=checkData.getTrailer().getModel()%>
				        				</td>
				     				</tr>     
									<tr class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
				      				<tr class="lineone">  
				        				<td width="50%" align="left">        
									        <%= messages.getText("generic.activity.form.vehiclecheck.plate")%>
										</td>
										<td width="50%" align="right">		
											<%=checkData.getTrailer().getPlate()%>
				        				</td>
				     				</tr>
									<tr class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
				      				<tr class="lineone">
				        				<td width="50%" align="left">        
									        <font face="Arial, Helvetica, sans-serif"> 
										        <%= messages.getText("generic.vehicle.serie")%>
											</td>
										<td width="50%" align="right">		
											<%=checkData.getSerie()%>
								        </td>
								     </tr>     
								</table>
							<jsp:include  page="../component/utilitybox2bottom.jsp" />
						</td>
					</tr>  
      				<tr>
						<jsp:include  page="../component/spaceline.jsp" />
		  			</tr>
<%		checkList = sys.getVehicleReportFacade().getCheckList(CheckListData.TRAILER_CHECKLIST,sys.getUserSessionData());
		points = checkList.getSortPoints();
%>					<tr>
						<td valign="top">
							<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
								<tr>
<%		for(int i = 0; i < 3; i++) {
    		if(i == 1) {
%>    					    		<td width="2%"></td>
<%    		}else {
%>									<td width="49%" valign="top">
										<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
<%				int count = (i == 0) ? 3 : 2;
				for(int j = 0; j < count; j++) {
				    PointData point = (PointData)points.next();
%>											<tr>
												<td valign="top">
													<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText(point.getText())%>' />
													<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
<%					Iterator items = point.getSortItems();
					while(items.hasNext()) {
						ItemData item = (ItemData)items.next();
%>														<tr class="lineone">
															<td width="50%" align="left">
		   														<%=messages.getText(item.getText())%>
															</td>	
<%						String value = checkData.getValueFromItem(item.getId());
						if(value == null) {
						    value = "1";
						}
%>															<td width="50%" align="right">
																<%=(value.equals("1")) ? messages.getText("generic.vehiclecheck.form.correct") : messages.getText("generic.vehiclecheck.form.incorrect")%>
															</td>
														</tr>
<%						if(items.hasNext()) {	
%>						      							<tr class="lineone" ><td align="right" colspan="3">&nbsp;</td></tr>
<%						}
					}
%>													</table>
													<jsp:include  page="../component/utilitybox2bottom.jsp" />
												</td>	
											</tr>	
<%					if(j < count-1) {	
%>     										<tr>
												<jsp:include  page="../component/spaceline.jsp" />
									  		</tr>
<%					}
				}
%>										</table>
									</td>
<%			}
		}
%>								</tr>
							</table>
						</td>
					</tr>	
				</table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>	
	  	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
<%		}
%>		<tr> 
			<td height="40" colspan="5" valign="middle">
				<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.activity.observations")%>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
		            <tr> 
              			<td class="lineone">
       						<%=vehicleCheck.getObs()%>
                		</td>
		            </tr>
		        </table>
				<jsp:include  page="../component/utilitybox2bottom.jsp" />
			</td>
		</tr>  
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
       	<tr>   		
	   		<td align="right">
	   			<a href='<%="layout.jsp?refer=/vehicle/VehicleView.jsp?id="+vehicleCheck.getVehicle().getId().toString()%>'>
		   			<input name="button" type="button" value='<%=messages.getText("generic.messages.accept")%>' class="input"/>	 		                             
				</a>
				<% if(userSessionData.checkPermission(SecurityConstants.MODIFY_VEHICLE) &&
						userSessionData.checkPermission(SecurityConstants.CHECKLIST_VEHICLE)){
						%>	   			
	   			<input name="button" type="button" value='<%=messages.getText("generic.messages.edit")%>' class="input" onclick="javascript:document.forms['edit'].submit();"/>
	   			<input name="button5" type="button" value='<%=messages.getText("generic.dpa.unconformity.title")%>' onClick="javascript:document.location.href='layout.jsp?refer=/dpa/DPAUnconformityCheckListForm.jsp?check=<%=idCheck%>&mode=new&type=<%=UnconformityTypeDPADataType.TYPE_VEHICLE_CHECK%>&href=/vehicle/TruckCheckView.jsp?vehicle=<%=vehicleCheck.getVehicle().getId()%>_check=<%=idCheck%>';" class="input"/>	 		                             
	   			<input name="button3" type="button" value='<%=messages.getText("generic.messages.exportpdf") %>' onClick="javascript:document.forms['TruckPDFReportForm'].submit();" class="input"/>			   			
	   			<input name="button2" type="button" value='<%=messages.getText("generic.messages.delete")%>' onClick="deleteIfConfirm(document.forms['TruckCheckListDeleteForm'],'<%=messages.getText("trailer.checklist.delete.confirm")%>');" class="input"/>
	   			<% } %>	 		                             		   			
           	</td>                 							
   	  	</tr>              
	</table>
	</center>
	<html:form action="/TruckPDFReport" method="post" >
		<html:hidden property="check" value='<%=idCheck%>' />	
	</html:form>
</body>
<%
	}	
%>	

</html:html>
