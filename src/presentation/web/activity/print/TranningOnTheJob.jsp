<%@page language="java" 
	errorPage="../../exception.jsp"
	import="business.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.activity.tables.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.report.*"
	import="java.util.*"	
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html>
<%	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.CREATE_CEPASCHEDULEACTIVITYREGISTRY);	
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		Text messages=sys.getText();
		CheckListData checkList = sys.getActivityReportFacade().findCheckList(new Integer(4),sys.getUserSessionData());
%>	
	<LINK REL=StyleSheet HREF="../../util/css/estilo.css" TYPE="text/css">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	</head>
	<body>
    	<table width="600" border="0" cellspacing="0" cellpadding="0">
	    	<tr> 
        		<td height="25" colspan="5" valign="middle">
              		<font color="#000000" size="4" face="Arial, Helvetica, sans-serif">
			        	<strong>
							 Training on the Job
            	  		</strong>
            	  	</font>
				</td>
            </tr>
     		<tr>
	     		<td colspan="5">&nbsp;</td>
     		</tr>   
            <tr> 
        		<td valign="top" colspan="5"> 
          			<table width="100%" border="1" cellpadding="0" cellspacing="0">
            			<tr> 
        					<th align="center" valign="top" class="print"> 
                  				<%=messages.getText("generic.activity.form.tranningonthejob.proofdata")%>
							</th>
            			</tr>
            			<tr>
            				<td>
            					<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr><td height="2"></td></tr>
									<tr>
								        <td align="left" class="print" valign="middle" width="50%">        
									        <%= messages.getText("generic.activity.form.tranningonthejob.initialHr")%>
										</td>
										<td width="40%"></td>
										<td align="right" valign="middle" >
											<input type=text style="width:40; height:10;" class="printtext"/>
										</td>	
										<td align="right" valign="middle" >
											<input type=text style="width:40; height:10;" class="printtext"/>
										</td>	
     								</tr>
									<tr><td height="2"></td></tr>
									<tr>
								        <td align="left" class="print" valign="middle" width="50%">        
									        <%= messages.getText("generic.activity.form.tranningonthejob.finalHr")%>
										</td>
										<td width="40%"></td>
										<td align="right" valign="middle" >
											<input type=text style="width:40; height:10;" class="printtext"/>
										</td>	
										<td align="right" valign="middle" >
											<input type=text style="width:40; height:10;" class="printtext"/>
										</td>	
     								</tr>
									<tr><td height="2"></td></tr>
									<tr>
								        <td align="left" class="print" valign="middle" width="50%">        
									        <%= messages.getText("generic.activity.form.tranningonthejob.trade")%>
										</td>
										<td align="right" valign="middle" colspan="4">		
											<input type=text style="width:250; height:10;" class="printtext" />                                     
			    		    			</td>
			    		    		</tr>	
									<tr><td height="2"></td></tr>
									<tr>
								        <td align="left" class="print" valign="middle" width="50%">        
									        <%= messages.getText("generic.activity.form.tranningonthejob.plate")%>
										</td>
										<td align="right" valign="middle" colspan="4">		
											<input type=text style="width:250; height:10;" class="printtext" />                                     
			    		    			</td>
			    		    		</tr>	
									<tr><td height="2"></td></tr>
									<tr>
								        <td align="left" class="print" valign="middle" width="50%">        
									        <%= messages.getText("generic.activity.form.tranningonthejob.initialKm")%>
										</td>
										<td align="right" valign="middle" colspan="4">		
											<input type=text style="width:80; height:10;" class="printtext" />                                     
			    		    			</td>
			    		    		</tr>	
									<tr><td height="2"></td></tr>
									<tr>
								        <td align="left" class="print" valign="middle" width="50%">        
									        <%= messages.getText("generic.activity.form.tranningonthejob.finalKm")%>
										</td>
										<td align="right" valign="middle" colspan="4">		
											<input type=text style="width:80; height:10;" class="printtext" />                                     
			    		    			</td>
			    		    		</tr>	
									<tr><td height="2"></td></tr>
								</table>
							</td>
						</tr>	
					</table>
				</td>
			</tr>		
      		<tr> 
				<td valign="top">&nbsp;</td>
			</tr>
      		<tr> 
        		<td valign="top" colspan="5"> 
          			<table width="100%" border="0" cellpadding="0" cellspacing="0">
            			<tr> 
<%		PrintUtil util = new PrintUtil();
		Column column1 = new Column(1,295);
		Column column2 = new Column(2,295);

		Header header1 = new Header("generic.activity.form.tranningonthejob.circulationarea",1);
		String[] options1 = {"generic.activity.form.tranningonthejob.circulationarea.urbana","generic.activity.form.tranningonthejob.circulationarea.suburban","generic.activity.form.tranningonthejob.circulationarea.autopista","generic.activity.form.tranningonthejob.circulationarea.road"};
		header1.addOptions(options1);
		column1.addHeader(header1);
		
		Header header3 = new Header("generic.activity.form.tranningonthejob.velocitybox",2);
		String[] options3 = {"generic.activity.form.tranningonthejob.velocitybox.manual","generic.activity.form.tranningonthejob.velocitybox.automatic"};
		header3.addOptions(options3);
		column1.addHeader(header3);

		Header header2 = new Header("generic.activity.form.tranningonthejob.vehicletype",1);
		String[] options2 = {"generic.activity.form.tranningonthejob.vehicletype.sedan","generic.activity.form.tranningonthejob.vehicletype.pickup","generic.activity.form.tranningonthejob.vehicletype.SUV","generic.activity.form.tranningonthejob.vehicletype.4x4"};
		header2.addOptions(options2);
		column2.addHeader(header2);

		Header header4 = new Header("generic.activity.form.tranningonthejob.light",2);
		String[] options4 = {"generic.activity.form.tranningonthejob.light.diurno","generic.activity.form.tranningonthejob.light.nocturno"};
		header4.addOptions(options4);
		column2.addHeader(header4);

		util.addColumn(column1);
		util.addColumn(column2);
		
		Collection columns = util.getColumns();
		Collections.sort((List)columns);
		Iterator itColumns = columns.iterator();
		int i = 1;
		while(itColumns.hasNext()) {
		    Column column = (Column)itColumns.next();
%>		    				<td width='<%=column.getWidth()%>' valign="top">
<%			Collection headers = column.getHeaders();
			Collections.sort((List)headers);
			Iterator itHeaders = headers.iterator();
			while(itHeaders.hasNext()) {
			    Header header = (Header)itHeaders.next();
%>								<table width="100%" border="1" cellpadding="0" cellspacing="0">
            						<tr> 
				        				<th align="center" valign="top" class="print"> 
                							<%= messages.getText(header.getKey())%>
										</th>
            						</tr>
            						<tr>
            							<td>
            								<table width="100%" border="0" cellspacing="0" cellpadding="0">
<%				Collection options = header.getOptions();
				Collections.sort((List)options);
				Iterator itOptions = options.iterator();
				while(itOptions.hasNext()) {
				    Option option = (Option)itOptions.next();
%>		            							<tr> 
		              								<td class="print"> 
									  					<input type="radio"/> 
														<%= messages.getText(option.getKey())%>
		              								</td>
		            							</tr>
<%				}
%>            								</table>
            							</td>
            						</tr>
								</table>
<%			}
%>							</td>
<%			if(i < columns.size()) {
    			i++;
%>							<td valign="top" width="5px"></td>
<%			}
		}
%>						</tr>
					</table>
				</td>
			</tr>			
				
			<tr> 
				<td valign="top">&nbsp;</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td width="195" valign="top">  
					
<%		Collection points = checkList.getPoints();
		Collections.sort((List)points);
		Iterator itPoints = points.iterator();
		i = 0;
		while(itPoints.hasNext()) {
			PointData point = (PointData)itPoints.next();
%>					<table width="100%" border="1" cellpadding="0" cellspacing="0">	
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
			itItems.next();
			while (itItems.hasNext()) {
				ItemData item = (ItemData)itItems.next();
%>									<tr class="print"> 
			 							<td align="left" width="20%">       
			 								<input type="checkbox" />
											<%=messages.getText(item.getText()) %>
		        						</td>   
									</tr>	
									<tr><td height="2"></td></tr>
<%			}
%>								</table>
							</td>
						</tr>			
					</table>		
<%			i++;
			if(i == 6 || i == 12) {
%>				</td>
				<td width="5"></td>			    
				<td width="195" valign="top">	
<%			}
		}
%>					
				</td>
			</tr>			
			<tr> 
				<td valign="top">&nbsp;</td>
			</tr>
      		<tr> 
        		<td colspan="5">
					<table width="100%" border="1" cellpadding="0" cellspacing="0">
						<tr> 
							<td>
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
								    	<th align="left" valign="middle" class="print">
							        		<%=messages.getText("generic.activity.form.tranningonthejob.proofdata")%>
								        </th>
								     </tr>
     								<tr> 
     									<td>
											<input type=text name="name" style="width:250; height:10;" class="printtext" />              
										</td>
									</tr>	
							     	<tr> 
										<td valign="top">&nbsp;</td>
									</tr>
									<tr> 
								    	<th align="left" valign="middle" class="print">
							        		<%=messages.getText("generic.activity.observations")%>
								        </th>
								     </tr>
     								<tr> 
     									<td>
						                	<textarea name="obs" style="border: black 1px solid;" cols="90" rows="5"></textarea>
										</td>
									</tr>	
							     	<tr> 
										<td valign="top">&nbsp;</td>
									</tr>
						      		<tr> 
		    							<th align="left" valign="middle" class="print">
						        			<%=messages.getText("generic.activity.instructor")%>
						        		</th>
						        	</tr>
						        	<tr>	
						        		<td align="left">	
											<input type=text name="name" style="width:250; height:10;" class="printtext" />              
										</td>
									</tr>
								</table>
							</td>	
						</tr>	
					</table>	
				</td>
			</tr>	
    	</table>
		<script>window.print();window.close();</script>
	</body>
<%	}	
%>	
</html>
