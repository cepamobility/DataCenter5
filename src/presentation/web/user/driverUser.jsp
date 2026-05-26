<%@page language="java"
	errorPage="../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="business.driver.*"
	import="communication.driver.tables.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.*"
	import="presentation.translator.web.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>

<html:html>

<%! RequestDispatcher dispatcher; 
	Text messages; 
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.CREATE_USER);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		Text messages = sys.getText();

		String driverId = request.getParameter("id");
		if(Util.empty(driverId)) {
		    driverId = (String)session.getAttribute("driver");
		}
		session.setAttribute("url","user/driverUser.jsp?id="+driverId);

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
		
		DriverReportFacade report = sys.getDriverReportFacade();
		DriverData driver = report.findDriverByPrimaryKey(new Integer(driverId),sys.getUserSessionData());
		
		Collection options = new LinkedList();
		options.add(new OptionItem(messages.getText("generic.messages.no"),"false"));
		options.add(new OptionItem(messages.getText("generic.messages.yes"),"true"));
		session.setAttribute("options",options);
%>
	<head>
		<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	</head>
	<script>
		function showPassword(insert){			
			if (insert.selectedIndex != 0){				
				document.getElementById('type').style.display = "";
			}else{
				document.getElementById('type').style.display = "none";
			}
		}
	</script>
	<jsp:include page='<%="/language/findalllanguage.jsp" %>'/>
	<body bgcolor="#FFFFFF">
		<html:form action="/DriverUserCreate">
			<html:hidden property="driver" value='<%=driverId%>' />
			<html:hidden property="loginname" value='<%=String.valueOf(driver.getEmail())%>' />
			<table width="450" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.user.newdriveruser")%>' />
		  		</tr>
		      	<tr>
					<jsp:include  page="../component/spaceline.jsp" />
		  		</tr>
<%		if(Util.empty(driver.getEmail())) {	  		
%>				<tr class="guideSearch">
					<td width="19" height="30"><img src="/datacenter/util/imgs/left_top_search.jpg" width="19" height="30" /></td>
					<td width="412" height="30">&nbsp;</td>
					<td width="19" height="30"><img src="/datacenter/util/imgs/right_top_search.jpg" width="19" height="30" /></td>
				</tr>
				<tr>
					<td background="/datacenter/util/imgs/left_line_search.jpg">&nbsp;</td>
	  				<td align="center" class="lineone">
	  					<table cellpadding="0" cellspacing="0" border="0">
	  						<tr>
		    		  			<td align="center" class="lineone">
		    						<span class="success">		    			
		    							<%=messages.getText("generic.driveruser.nomail") %>	
		    						</span>
		    		  			</td>
		    		  		</tr>
		    		  		<tr>
		    		  			<td>&nbsp;</td>
		    		  		</tr>
	  						<tr>
		    		  			<td align="center" class="lineone">
		    						<span class="success">		    			
										<img src="/datacenter/util/imgs/icon1.jpg" height="12" width="12">
										<a href='<%="layout.jsp?refer=/driver/DriverView.jsp?id="+driverId%>' class="linksuccess">
											<%=messages.getText("generic.driveruser.viewdriver")%>
										</a>
									</span>
								</td>
							</tr>
		    		  		<tr>
		    		  			<td>&nbsp;</td>
		    		  		</tr>
	  						<tr>
		    		  			<td align="center" class="lineone">
		    						<span class="success">		    			
										<img src="/datacenter/util/imgs/icon1.jpg" height="12" width="12">
										<a href="layout.jsp?refer=/user/UserNEDList.jsp" class="linksuccess">
											<%=messages.getText("generic.driveruser.backtouserlist")%>
										</a>
									</span>
								</td>
							</tr>
		    		  		<tr>
		    		  			<td>&nbsp;</td>
		    		  		</tr>
	  					</table>
	  				</td>
		    		<td background="/datacenter/util/imgs/right_line_search.jpg">&nbsp;</td>
		  		</tr>
		  		<tr>
		    		<td width="19" height="11"><img src="/datacenter/util/imgs/left_base_search.jpg" width="19" height="11" /></td>
		    		<td width="412" height="11" background="/datacenter/util/imgs/center_base_search.jpg">
		    			<img src="x" width="1" height="1" />
		    		</td>
		    		<td width="19" height="11"><img src="/datacenter/util/imgs/right_base_search.jpg" width="19" height="11" /></td>
			  	</tr>
<%		}else if(sys.getUserReportFacade().userExist(driver.getEmail(),sys.getUserSessionData())) {
%>				<tr class="guideSearch">
					<td width="19" height="30"><img src="/datacenter/util/imgs/left_top_search.jpg" width="19" height="30" /></td>
					<td width="412" height="30">&nbsp;</td>
					<td width="19" height="30"><img src="/datacenter/util/imgs/right_top_search.jpg" width="19" height="30" /></td>
				</tr>
				<tr>
					<td background="/datacenter/util/imgs/left_line_search.jpg">&nbsp;</td>
					<td align="center" class="lineone">
						<table cellpadding="0" cellspacing="0" border="0">
							<tr>
		  						<td align="center" class="lineone">
									<span class="success">		    			
										<%=messages.getText("generic.driveruser.alreadyexist") %>	
									</span>
		  						</td>
					  		</tr>
		  					<tr>
					  			<td>&nbsp;</td>
		  					</tr>
							<tr>
					  			<td align="center" class="lineone">
									<span class="success">		    			
										<img src="/datacenter/util/imgs/icon1.jpg" height="12" width="12">
										<a href='<%="layout.jsp?refer=/user/UserView.jsp?uid="+driver.getEmail()%>' class="linksuccess">
											<%=messages.getText("generic.driveruser.viewduser")%>
										</a>
									</span>
								</td>
							</tr>
		  					<tr>
				  				<td>&nbsp;</td>
					  		</tr>
							<tr>
					  			<td align="center" class="lineone">
									<span class="success">		    			
										<img src="/datacenter/util/imgs/icon1.jpg" height="12" width="12">
										<a href="layout.jsp?refer=/user/UserNEDList.jsp" class="linksuccess">
											<%=messages.getText("generic.driveruser.backtouserlist")%>
										</a>
									</span>
								</td>
							</tr>
					  		<tr>
		  						<td>&nbsp;</td>
					  		</tr>
						</table>
					</td>
					<td background="/datacenter/util/imgs/right_line_search.jpg">&nbsp;</td>
				</tr>
				<tr>
					<td width="19" height="11"><img src="/datacenter/util/imgs/left_base_search.jpg" width="19" height="11" /></td>
					<td width="412" height="11" background="/datacenter/util/imgs/center_base_search.jpg">
						<img src="x" width="1" height="1" />
					</td>
					<td width="19" height="11"><img src="/datacenter/util/imgs/right_base_search.jpg" width="19" height="11" /></td>
				</tr>
<%		}else {
%>			  	<tr>
		    		<td valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("web.user.userview.systemuserdata")%>' />
						<table>
					      	<tr  class="linetwo">
					        	<th width="224" align="left"> <%=messages.getText("generic.user.userdata.nickname")%></th>
					         	<td align="right">
				            		<%=driver.getEmail()%>
					          	</td>
					      	</tr>
					      	<tr  class="space">
					        	<th align="right"></th>
					        	<td align="right"></td>
					      	</tr>
					      	<tr>
					      		<td colspan="2">
	  								<table cellpadding="0" cellspacing="0" border="0" width="100%">
										<tr class="guideSearch">
											<td width="19" height="30"><img src="/datacenter/util/imgs/left_top_search.jpg" width="19" height="30" /></td>
											<td width="362" height="30">&nbsp;</td>
											<td width="19" height="30"><img src="/datacenter/util/imgs/right_top_search.jpg" width="19" height="30" /></td>
										</tr>
										<tr>
											<td background="/datacenter/util/imgs/left_line_search.jpg">&nbsp;</td>
							  				<td align="center" class="lineone" width="100%">
	  											<table cellpadding="0" cellspacing="0" border="0" width="100%">
						      						<tr  class="lineone">
						        						<th align="left" > <%=messages.getText("generic.driveruser.insertpassword")%></th>
						        						<td align="right" >
															<html:select property="insertpassword" styleClass="input" onchange="javascript:showPassword(this);">
																<html:options collection="options" property="value" labelProperty="label"/>
															</html:select>
						      							</td>
													</tr>
												    <tr  class="lineone" name="type" id="type" style="display:none;" >
												    	<td colspan="2" width="100%">
															<table cellpadding="0" cellspacing="0" border="0" width="100%">
									      						<tr  class="lineone">
															       	<th align="right"></th>
															       	<td align="right"></td>
															     </tr>
										      					<tr  class="lineone" >
										        					<th align="left"><%=messages.getText("generic.user.userdata.pass")%><span class="required">*</span></th>
										        					<td align="right">
										           						<html:password property="password" size="25" styleClass="input"/>
										          					</td>
										      					</tr>
															    <tr  class="lineone">
															       	<th align="right"></th>
															       	<td align="right"></td>
															     </tr>
										      					<tr  class="lineone">
										        					<th align="left"> <%=messages.getText("web.user.create.confirmpassword")%><span class="required">*</span></th>
										        					<td align="right">
										           						<html:password property="confirm" size="25" styleClass="input"/>
										          					</td>
										      					</tr>
										      				</table>
										      			</td>
										      		</tr>
										      	</table>
							  				</td>
								    		<td background="/datacenter/util/imgs/right_line_search.jpg">&nbsp;</td>
								  		</tr>
								  		<tr>
								    		<td width="19" height="11"><img src="/datacenter/util/imgs/left_base_search.jpg" width="19" height="11" /></td>
								    		<td width="362" height="11" background="/datacenter/util/imgs/center_base_search.jpg">
								    			<img src="x" width="1" height="1" />
								    		</td>
								    		<td width="19" height="11"><img src="/datacenter/util/imgs/right_base_search.jpg" width="19" height="11" /></td>
									  	</tr>
				      				</table>
					      		</td>
					      	</tr>
					      	<tr  class="space">
					        	<th align="right"></th>
					        	<td align="right"></td>
					      	</tr>
      						<tr  class="linetwo">
        						<th align="left" > <%=messages.getText("generic.user.userdata.firstname")%></th>
        						<td align="right">
					            	<%=driver.getFirstName()%>
          						</td>
      						</tr>
					      	<tr  class="space">
					        	<th align="right"></th>
					        	<td align="right"></td>
					      	</tr>
      						<tr  class="linetwo">
        						<th align="left"> <%=messages.getText("generic.user.userdata.lastname")%></th>
        						<td align="right">
					            	<%=driver.getLastName()%>
          						</td>
      						</tr>
					      	<tr  class="space">
					        	<th align="right"></th>
					        	<td align="right"></td>
					      	</tr>
      						<tr  class="linetwo">
        						<th align="left"> <%=messages.getText("generic.user.userdata.mail")%></th>
        						<td align="right">
				            		<%=driver.getEmail()%>
          						</td>
      						</tr>
					      	<tr  class="space">
					        	<th align="right"></th>
					        	<td align="right"></td>
					      	</tr>
      						<tr  class="linetwo">
        						<th align="left" > <%=messages.getText("generic.user.userdata.language")%></th>
        						<td align="left" >
									<html:select property="language" styleClass="input" style="width:160; ">
										<html:options collection="languageOptionList" property="value" labelProperty="label"/>
									</html:select>
      							</td>
							</tr>
					      	<tr  class="space">
					        	<th align="right"></th>
					        	<td align="right"></td>
					      	</tr>
      						<tr  class="linetwo">
        						<th align="left" colspan="2"> 
        							<html:checkbox property="sendmail"></html:checkbox>
        							<%=messages.getText("generic.driveruser.sendemail")%>
        						</th>
							</tr>
					      	<tr  class="space">
					        	<th align="right"></th>
					        	<td align="right"></td>
					      	</tr>
      						<tr class="linetwo">
        						<td align="right"> </td>
        						<td align="right">
          							<div align="right">
							            <html:submit styleClass="input">
											<%=messages.getText("generic.messages.save")%>
										</html:submit>
							            <html:cancel styleClass="input">
											<%=messages.getText("generic.messages.cancel")%>
										</html:cancel>
          							</div>
          						</td>
      						</tr>
      					</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
    				</td>
  				</tr>
<%		}
%>  				
			</table>
		</html:form>
	</body>
<%	}	
%>
</html:html>
