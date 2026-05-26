<%@page import="org.apache.struts.action.DynaActionForm"%>
<%@page language="java" 
	errorPage="../../exception.jsp"
	import="business.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="communication.datatype.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.activity.tables.*"
	import="communication.util.*"
	import="communication.driver.tables.*"
	import="communication.datatype.tables.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.*"
	
	import="business.activity.ActivityReportFacade"
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
	ActivityReportFacade activityReport;
	String idActivity, url, idDriver;
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();
	DynaActionForm form = null;
	String check = Util.checkPermission(sys,SecurityConstants.CREATE_CEPASCHEDULEACTIVITYREGISTRY);	
	String check1 = Util.checkPermission(sys,SecurityConstants.CREATE_CD_ACTIVITY);		
	if ((check!=null) && (check1!=null)) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		messages=sys.getText();
	
		dispatcher = request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		idActivity = (String)request.getParameter("idActivity");
		if ( idActivity == null ){
	idActivity = (String)session.getAttribute("idActivity");			
		}
		idDriver = (String)request.getParameter("idDriver");
		if (idDriver == null){
	idDriver = (String)session.getAttribute("idDriver");
		}

		//session.setAttribute("url","activity/schedule/CEPAActivityScheduleView.jsp?idActivity="+idActivity+"&idDriver="+idDriver);
		url ="activity/schedule/CEPAActivityScheduleView.jsp?idActivity="+idActivity+"&idDriver="+idDriver;
		userSessionData.setUrl(url);
	
		ActivityReportFacade activityReport = sys.getActivityReportFacade();
		CEPAActivityScheduleRegistryData registryData = activityReport.getCompleteResult(new Integer(idActivity),new Integer(idDriver),sys.getUserSessionData());		
		
		if(registryData.getHasResult()) {
	session.setAttribute("ActivityCDCayacoRegistryForm",new ActivityCDCayacoRegistryForm(registryData));
		}else {
	if(session.getAttribute("ErrorActivityCDCayacoRegistryForm") != null) {
		session.removeAttribute("ErrorActivityCDCayacoRegistryForm");
	}else {
		session.removeAttribute("ActivityCDCayacoRegistryForm");
	}
		}
		CheckListData checkList = registryData.getActivity().getDefinition().getCheckList();	
		Collection points = checkList.getPoints();
		Collections.sort((List)points);
		Iterator pointIt = points.iterator();
%>
<!-- cdCayaco.jsp -->
	
<%@page import="communication.administration.corporation.tables.CorporationData"%>
<%@page import="communication.administration.RoleData"%><html:form action = "/CDCayacoRegistryCreate" method="post">
		<html:hidden property="activity" value='<%=idActivity%>' />
		<html:hidden property="driver" value='<%=idDriver%>' />
		<html:hidden property="url" value='<%=url%>' />		
		<center>
	  		<table width="700" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center" colspan="3">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
	    					<tr> 
	      						<td width="9">
							      	<img src="/datacenter/util/imgs/titulo_left.gif" width="9" height="40">
								</td>
	      						<th width="700" background="/datacenter/util/imgs/bg2.gif" align="center">
									<font color="#FFFFFF" size="4" face="Arial, Helvetica, sans-serif">
										<%=messages.getText("generic.activity.form.formulario")%>
	            					</font>
	      						</th>
	      						<td width="9">
							      	<img src="/datacenter/util/imgs/titulo_right.gif" width="9" height="40">
	      						</td>
	    					</tr>
	  					</table>
					</td>
				</tr>
				<tr>
		        	<td height="10"><img src="x" width="1" height="1"></td>
				</tr>
				<tr>
					<td align="center" colspan="3">
						<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
							<tr class="guide1">
								<td width="19" height="30" ><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
		   		  		  		<td height="30" class="guide1"><div align="left"><%=messages.getText("generic.activity.form.tranningonthejob.proofdata")%></div></td>
								<td width="19" height="30" ><img src="/datacenter/util/imgs/right.jpg" width="19" height="30"></td>
		  					</tr>
							<tr>
								<td class="leftLine">&nbsp;</td>
     							<td height="6"><img src="x" width="1" height="1"></td>
     							<td class="rightLine">&nbsp;</td>
							</tr>  					  					
 							<tr>
 								<td class="leftLine">&nbsp;</td>
 								<td>
									<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
     									<tr> 
							        		<td width="50%" align="left">        
								        		<font size="1" face="Arial, Helvetica, sans-serif"> 
									        		<%=messages.getText("generic.activity.instructor")%>
								        		</font> 		
											</td>
											<td width="50%" align="right">		
								        		<font size="1" face="Arial, Helvetica, sans-serif"> 		
<%
 			if(sys.getCorporationId() == CorporationData.CIMSA && sys.getUserSessionData().getRole().getName().equals(RoleData.CDS)) {
 		%>												<%=userSessionData.getFilter().getFirstName()+" "+userSessionData.getFilter().getLastName()%>
													<html:hidden property="instructor" value='<%=userSessionData.getFilter().getFirstName()+" "+userSessionData.getFilter().getLastName()%>' />
<%
	}else {
%>													<html:text property="instructor" styleClass="input"/>	
<%
		}
	%>												</font> 
							        		</td>
									     </tr>
     									<tr> 
							        		<td width="50%" align="left">        
								        		<font size="1" face="Arial, Helvetica, sans-serif"> 
									        		<%=messages.getText("generic.activity.form.tranningonthejob.trade")%>
								        		</font> 		
											</td>
											<td width="50%" align="right">		
								        		<font size="1" face="Arial, Helvetica, sans-serif"> 		
													<html:text property="trade" styleClass="input" />                                     
												</font> 
							        		</td>
									     </tr>
      									<tr>  
		        							<td width="50%" align="left">        
										        <font size="1" face="Arial, Helvetica, sans-serif"> 
											        <%=messages.getText("generic.activity.form.tranningonthejob.plate")%>
										        </font> 		
											</td>
											<td width="50%" align="right">		
								        		<font size="1" face="Arial, Helvetica, sans-serif"> 		
													<html:text property="plate" styleClass="input" />                                             
								        		</font> 
							        		</td>
								     	</tr>       
     									<tr> 
        									<td width="50%" align="left">        
        										<font size="1" face="Arial, Helvetica, sans-serif">                 
									        		<%=messages.getText("generic.activity.form.tranningonthejob.initialHr")%>
								        		</font> 		
											</td>
											<td width="50%" align="right">
								        		<font size="1" face="Arial, Helvetica, sans-serif">                         		
													<html:text property="initialHr" style="width:23;" styleClass="input" maxlength="2"/>&nbsp;:&nbsp;                
													<html:text property="initialMinute" style="width:23;" styleClass="input" maxlength="2"/>                		
								        		</font> 		
											</td>
     									</tr>
      									<tr>  
		        							<td width="50%" align="left">        
										        <font size="1" face="Arial, Helvetica, sans-serif"> 
											        <%=messages.getText("generic.activity.form.tranningonthejob.finalHr")%>
										        </font> 		
											</td>
											<td width="50%" align="right">		
								        		<font size="1" face="Arial, Helvetica, sans-serif">                         				
													<html:text property="finalHr" style="width:23;" styleClass="input" maxlength="2"/>&nbsp;:&nbsp;                
													<html:text property="finalMinute" style="width:23;" styleClass="input" maxlength="2"/>                		
        										</font> 
        									</td>
		     							</tr>
      									<tr>
		        							<td width="50%" align="left">        
        										<font size="1" face="Arial, Helvetica, sans-serif"> 
											        <%=messages.getText("generic.activity.form.tranningonthejob.initialKm")%>
										        </font> 		
											</td>
											<td width="50%" align="right">		
        										<font size="1" face="Arial, Helvetica, sans-serif"> 		
													<html:text property="initialKm" styleClass="input" />                                                     
        										</font> 
        									</td>
		     							</tr>
        		      					<tr>  
        									<td width="50%" align="left">        
		        								<font size="1" face="Arial, Helvetica, sans-serif"> 
											        <%=messages.getText("generic.activity.form.tranningonthejob.finalKm")%>        
										        </font> 		
											</td>
											<td width="50%" align="right">		
								        		<font size="1" face="Arial, Helvetica, sans-serif"> 		
													<html:text property="finalKm" styleClass="input" />                                                             
								        		</font> 
							        		</td>
		     							</tr>
		     						</table>
								<td class="rightLine">&nbsp;</td>
							</tr>	
			  				<tr>
			                  	<td height="11" valign="top"><img src="/datacenter/util/imgs/left_base.gif" width="19" height="11"></td>
			  				  	<td class="centerBase"></td>
			  				  	<td valign="top" height="11"><img src="/datacenter/util/imgs/right_base.gif" width="19" height="11"></td>
						 	</tr>
						</table>
					</td>
				</tr>
				<tr>
		        	<td height="10"><img src="x" width="1" height="1"></td>
				</tr>
<%
	boolean par = true;
	while(pointIt.hasNext()) {
		PointData point = (PointData) pointIt.next();
		String comment = registryData.getPointComment(point);
		if(par) {
%>				<tr>
<%
	}
%>					<td valign="top">
						<table border="0" cellpadding="0" cellspacing="0" class="guideSearch">
							<tr class="guide1">
								<td width="19" height="30" ><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
		   		  		  		<td height="30" class="guide1"><div align="left"><%=messages.getText(point.getText())%></div></td>
								<td width="19" height="30" ><img src="/datacenter/util/imgs/right.jpg" width="19" height="30"></td>
		  					</tr>
							<tr>
								<td class="leftLine">&nbsp;</td>
     							<td height="6"><img src="x" width="1" height="1"></td>
     							<td class="rightLine">&nbsp;</td>
							</tr>  					  					
 							<tr>
 								<td class="leftLine">&nbsp;</td>
 								<td>
 									<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
<%
  					if(point.getId().intValue() != 580) {
  				  							if(point.getId().intValue() != 573) {
  				%>										<tr class="guideSearch">
											<td align="left" class="guide2" width="60%">
												&nbsp;
											</td>
											<td align="center" class="guide2" width="13%">
												<%=messages.getText("generic.messages.yes")%>
											</td>
											<td align="center" class="guide2" width="13%">
												<%=messages.getText("generic.messages.no")%>
											</td>
										</tr>
<%
	Collection items = point.getItems();
		Collections.sort((List)items);
		Iterator itemIt = items.iterator();
		String line = "linetwo";
		while(itemIt.hasNext()) {
			ItemData item = (ItemData)itemIt.next();
			Integer valueItem = registryData.getValueFromItem(item);
			int value = (valueItem == null) ? 0 : valueItem.intValue();
	
			String posibleValue = request.getParameter("item"+String.valueOf(item.getId()));
			if (Util.isNumber(posibleValue)){
				value = (new Integer(posibleValue)).intValue();
		// System.out.println("Item value: "+value);
			}
%>										<tr class='<%=line%>'>
											<td align="left" >
												<%=messages.getText(item.getText())%>
											</td>
											<td align="center">
												<input type="radio" name='<%="item"+String.valueOf(item.getId())%>' value="1" <%=(value == 1) ? "checked" : ""%>/>
											</td>
											<td align="center">
												<input type="radio" name='<%="item"+String.valueOf(item.getId())%>' value="2" <%=(value == 2) ? "checked" : ""%>/>
											</td>
										</tr>
<%
	line = (line.equals("linetwo")) ? "lineone" : "linetwo";
		}
	}else {
%>										<tr class="guideSearch">
											<td align="left" class="guide2" width="60%">
												&nbsp;
											</td>
											<td align="center" class="guide2" width="13%">
												B
											</td>
											<td align="center" class="guide2" width="13%">
												R
											</td>
											<td align="center" class="guide2" width="13%">
												M
											</td>
										</tr>
<%
	Collection items = point.getItems();
		Collections.sort((List)items);
		Iterator itemIt = items.iterator();
		String line = "linetwo";
		while(itemIt.hasNext()) {
			ItemData item = (ItemData)itemIt.next();
			Integer valueItem = registryData.getValueFromItem(item);
			int value = (valueItem == null) ? 0 : valueItem.intValue();

			String posibleValue = request.getParameter("item"+String.valueOf(item.getId()));
			if (Util.isNumber(posibleValue)){
				value = (new Integer(posibleValue)).intValue();
			}
%>										<tr class='<%=line%>'>
											<td align="left" >
												<%=messages.getText(item.getText()) %>
											</td>
											<td align="center">
												<input type="radio" name='<%="item"+String.valueOf(item.getId())%>' value="1" <%=(value == 1) ? "checked" : "" %>/>
											</td>
											<td align="center">
												<input type="radio" name='<%="item"+String.valueOf(item.getId())%>' value="2" <%=(value == 2) ? "checked" : "" %>/>
											</td>
											<td align="center">
												<input type="radio" name='<%="item"+String.valueOf(item.getId())%>' value="3" <%=(value == 3) ? "checked" : "" %>/>
											</td>
										</tr>
<%							line = (line.equals("linetwo")) ? "lineone" : "linetwo";
						}
					}
				}
%>	
										<tr class="linetwo">
											<th align="left" colspan="4">
												<%=(point.getId().intValue() == 580) ? "" : messages.getText("generic.messages.observations") %>
											</th>
										</tr>
										<tr class="linetwo">
											<td align="left" colspan="4">
												<textarea rows="3" cols="40" name='<%="observations_"+String.valueOf(point.getId())%>'><%=(comment == null) ? "" : comment%></textarea>
											</td>
										</tr>
									</table>	
								</td>    									
					    		<td background="./util/imgs/right_line_search.jpg">&nbsp;</td>
		  					</tr>
		  					<tr>
		    					<td width="19" height="11"><img src="./util/imgs/left_base_search.jpg" width="19" height="11" /></td>
		    					<td width="562" height="11" background="./util/imgs/center_base_search.jpg"><img src="x" width="1" height="1" /></td>
					    		<td width="19" height="11"><img src="./util/imgs/right_base_search.jpg" width="19" height="11" /></td>
						  	</tr>
						</table>
					</td>
<%				if(par) {
%>					<td>&nbsp;</td>					
<%				}else {
%>				</tr>	
				<tr>
		        	<td height="10"><img src="x" width="1" height="1"></td>
				</tr>
<%				}
				par = !par;
			}
%>				<tr>
					<td align="center" colspan="3">
						<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
							<tr class="guide1">
								<td width="19" height="30" ><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
		   		  		  		<td height="30" class="guide1"><div align="left"><%=messages.getText("generic.messages.observations")%></div></td>
								<td width="19" height="30" ><img src="/datacenter/util/imgs/right.jpg" width="19" height="30"></td>
		  					</tr>
							<tr>
								<td class="leftLine">&nbsp;</td>
     							<td height="6"><img src="x" width="1" height="1"></td>
     							<td class="rightLine">&nbsp;</td>
							</tr>  					  					
 							<tr>
 								<td class="leftLine">&nbsp;</td>
 								<td>
			    					<table width="100%" border="0" cellpadding="0" cellspacing="0">
			    						<tr>
					       					<td valign="top" align="left" style="width:85%" >
					       						<font size="1" face="Arial, Helvetica, sans-serif"> 
													<html:textarea property="observations" style="width:80%" rows="3" styleClass="input"></html:textarea>
												</font>	        	
					        				</td> 		             
					    				</tr>
					    			</table>
			        			</td>        
								<td class="rightLine">&nbsp;</td>
							</tr>	
			  				<tr>
			                  	<td height="11" valign="top"><img src="/datacenter/util/imgs/left_base.gif" width="19" height="11"></td>
			  				  	<td class="centerBase"></td>
			  				  	<td valign="top" height="11"><img src="/datacenter/util/imgs/right_base.gif" width="19" height="11"></td>
						 	</tr>
           				</table>
			    	 </td>
			   	</tr>
				<tr>
		        	<td height="10"><img src="x" width="1" height="1"></td>
				</tr>
				<tr>
		        	<td height="10" align="right" colspan="3">
		        		<html:submit value='<%=messages.getText("generic.messages.save")%>' styleClass="input"/>
		        		<html:cancel value='<%=messages.getText("generic.messages.cancel")%>' styleClass="input"/>
					</td>
				</tr>
			</table>
		</center>
	</html:form>
<%	}
%>

