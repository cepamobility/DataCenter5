<%@page language="java" 
	errorPage="../../exception.jsp"
	import="business.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="communication.activity.tables.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.ActivityTypeDataType"
	import="business.vehicle.VehicleReportFacade"
	import="java.util.*"	
	import="communication.activity.tables.*"	
	import ="org.apache.struts.action.DynaActionForm"
%>
<html>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	Text messages = sys.getText();
	
	CheckListData checkList = sys.getVehicleReportFacade().findVehicleCheckList(sys.getUserSessionData());
	Collection points = checkList.getPoints();
	
	int[][] validValues = {{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},
			{1,1,1,1},{1,1,1,1},{1,0,0,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,0,0},{1,0,0,1},{1,0,0,1},{1,0,0,1},
			{1,0,0,1},{1,0,0,1},{1,0,0,1},{1,0,0,1},{1,0,0,1},{1,0,0,1},{1,0,0,1},{1,0,1,1},{1,1,0,0},{1,1,1,1}};

%>	
	<LINK REL=StyleSheet HREF="../../util/css/estilo.css" TYPE="text/css">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	</head>

	<body>
		<table width="650" border="0" cellspacing="0" cellpadding="0">  
 			<tr> 
 				<td colspan="5" valign="middle">
  					<table width="100%" border="0" cellpadding="0" cellspacing="0">
					    <tr> 
						   	<td width="100%" bgcolor="#CCCCCC">
								<font color="#000000" face="Helvetica">
									<%=messages.getText("generic.activity.form.vehiclechecklist")%>
				            	</font>
      						</td>
					    </tr>
  					</table>
  				</td>
  			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td>  
					<table width="100%" border="1" cellpadding="0" cellspacing="0">
						<tr> 
					    	<th align="left" valign="middle" bgcolor="#cccccc" class="print">
					        		<%=messages.getText("generic.activity.form.tranningonthejob.proofdata")%>
					        </th>
					     </tr>
     					<tr> 
     						<td>
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr><td height="2"></td></tr>
									<tr>
								        <td align="left" class="print" valign="middle" width="50%">        
										        <%= messages.getText("generic.activity.date")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
										</td>
										<td width="30%"></td>
										<td align="right" valign="middle" >
											<input type=text style="width:40; height:10;" class="printtext"/>
										</td>	
										<td align="right" valign="middle" >
											<input type=text style="width:40; height:10;" class="printtext"/>
										</td>	
										<td align="right" valign="middle" >
											<input type=text style="width:80; height:10;" class="printtext"/>
										</td>	
			     					</tr>
									<tr><td height="2"></td></tr>
			     					<tr> 
			        					<td align="left" class="print" valign="middle">        
									        <%= messages.getText("generic.driver.name")%>
										</td>
										<td align="right" valign="middle" colspan="4">		
											<input type=text name="name" style="width:250; height:10;" class="printtext" />              
			        					</td>
			     					</tr>     
									<tr><td height="2"></td></tr>
			     					<tr> 
								        <td align="left" class="print" valign="middle">        
									        <%= messages.getText("generic.driver.licenseNumber")%>
										</td>
										<td align="right" valign="middle" colspan="4">		
											<input type=text name="licenseNumber" style="width:250; height:10;" class="printtext" />                                     
			    		    			</td>
			     					</tr>     
									<tr><td height="2"></td></tr>
			     					<tr>        
			        					<td align="left" class="print" valign="middle">        
									        <%= messages.getText("generic.activity.dateLicenseExpiry")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
										</td>
										<td width="30%"></td>
										<td align="right" valign="middle" >
											<input type=text style="width:40; height:10;" class="printtext"/>
										</td>	
										<td align="right" valign="middle" >
											<input type=text style="width:40; height:10;" class="printtext"/>
										</td>	
										<td align="right" valign="middle" >
											<input type=text style="width:80; height:10;" class="printtext"/>
										</td>	
			     					</tr>
									<tr><td height="2"></td></tr>
			      					<tr>  
			        					<td align="left" class="print" valign="middle">        
		        							<%= messages.getText("generic.activity.form.vehiclecheck.plate")%>
										</td>
										<td align="right" valign="middle" colspan="4">		
											<input type=text name="plate" style="width:250; height:10;" class="printtext" />                                             
			        					</td>
			     					</tr>
									<tr><td height="2"></td></tr>
			      					<tr>
			        					<td align="left" class="print" valign="middle">        
									        <%= messages.getText("generic.vehicle.serie")%>
										</td>
										<td align="right" valign="middle" colspan="4">		
											<input type=text name="serie" style="width:250; height:10;" class="printtext" />                                                     
			        					</td>
			     					</tr>     
									<tr><td height="2"></td></tr>
			      					<tr>  
			        					<td align="left" class="print">        
									        <%= messages.getText("generic.vehiclecheck.form.vehicleKm")%>        
										</td>
										<td align="right" valign="middle" colspan="4">		
											<input type=text name="vehicleKm" style="width:250; height:10;" class="printtext" />                                                             
								        </td>
			     					</tr>
									<tr><td height="2"></td></tr>
			     				</table>
			     			</td>
			     		</tr>			
					</table>
				</td>
			</tr>  
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td>  
					<table width="100%" border="1" cellpadding="0" cellspacing="0">
<%		Collections.sort((List)points);
		Iterator itPoints = points.iterator();
		int i = 0;
		while(itPoints.hasNext()) {
			PointData point = (PointData)itPoints.next();
%>
						<tr>
    						<th width="100%" valign="middle" class="print"> 		
				        		<%=messages.getText(point.getText())%>
        					</th>
						</tr>			
						<tr>
							<td width="100%">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr><td height="2"></td></tr>
<%		 	Collection items = point.getItems();
    		Collections.sort((List)items);
			Iterator itItems = items.iterator();
			while (itItems.hasNext()) {
				ItemData item = (ItemData)itItems.next();
%>									<tr class="print"> 
		 								<td align="left" width="20%">       
							        		<%= messages.getText(item.getText())%>
										</td>	   
			 							<td align="center" width="20%">       
											<%= (validValues[i][0] == 1) ? "__" + messages.getText("generic.vehiclecheck.form.correct") : "&nbsp;"%>
		        						</td>   
		 								<td align="center" width="20%">       
											<%= (validValues[i][1] == 1) ? "__" + messages.getText("generic.vehiclecheck.form.withoutclean") : "&nbsp;"%>		
										</td>
										<td align="center" width="20%">       
											<%= (validValues[i][2] == 1) ? "__" + messages.getText("generic.vehiclecheck.form.conmarcas") : "&nbsp;"%>				
										</td>										
	 									<td align="center" width="20%">       
											<%= (validValues[i][3] == 1) ? "__" + messages.getText("generic.vehiclecheck.form.noposee") : "&nbsp;"%>				
										</td>		
									</tr>	
									<tr><td height="2"></td></tr>
<%			}
%>								</table>
							</td>
						</tr>			
<%			i++;
		}
%>					</table>	
				</td>
			</tr>			
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td colspan="5">
  					<table width="100%" border="0" cellpadding="0" cellspacing="0">
			            <tr> 
              				<th align="left" bgcolor="#f9f9f9" class="print">
           						<%= messages.getText("generic.activity.observations") %>
              				</th>
            			</tr>
            			<tr> 
              				<td>
			                	<textarea name="obs" style="border: black 1px solid;" cols="90" rows="10"></textarea>
                			</td>
            			</tr>
	       			</table>
				</td>
			</tr>
		</table>
		<script>window.print();window.close();</script>
	</body>
</html>
