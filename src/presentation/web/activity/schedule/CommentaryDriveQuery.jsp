
<%@page language="java"
	errorPage="../../exception.jsp"
	import="business.*"
	import="communication.language.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.activity.schedule.*"
	import="communication.driver.tables.*"
	import="communication.security.*"
	import="communication.datatype.*"
	import="communication.administration.user.*"
	import="communication.datatype.tables.*"
	import="communication.administration.corporation.tables.CorporationData"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>
<%!
	Text messages; 
	SystemAccess sys;
	UserSessionData userSessionData;
%> 
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData = sys.getUserSessionData();
	String url;
	String check = null;
    check = Util.checkPermission(sys,SecurityConstants.QUERY);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else{
		messages=sys.getText();

		RequestDispatcher dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		Collection format = new LinkedList();
		format.add(new OptionItem("HTML","HTML"));	    
		format.add(new OptionItem("PDF","PDF"));	    
		format.add(new OptionItem("XLS","XLS"));	    
		session.setAttribute("format",format);
	
		// session.setAttribute("url","driver/CommentaryDriveQuery.jsp");
		url = "activity/schedule/CommentaryDriveQuery.jsp";
		sys.getUserSessionData().setUrl(url);
		
		if(session.getAttribute("CommentaryDriveReportForm") == null) {
		    CommentaryDriveReportForm form = new CommentaryDriveReportForm();
		    session.setAttribute("CommentaryDriveReportForm",form);
		}
		
		Collection days = new LinkedList();
		days.add(new OptionItem("",""));
		for(int i = 1; i <= 31; i++) {
		    days.add(new OptionItem(String.valueOf(i),String.valueOf(i)));
		}
		request.setAttribute("days",days);

		Collection years = new LinkedList();
	    years.add(new OptionItem("",""));
		for(int i = 1998; i <= 2050; i++) {
			years.add(new OptionItem(String.valueOf(i),String.valueOf(i)));
		}
		request.setAttribute("years",years);
		
		Collection months = new LinkedList();
	    months.add(new OptionItem("",""));
		for(int i = 1; i <= 12; i++) { 
		    months.add(new OptionItem(messages.getText("generic.month."+String.valueOf(i)),String.valueOf(i)));
		}
		request.setAttribute("months",months);
		String status = String.valueOf(DriverData.ACTIVE);

		Collection types = new LinkedList();
		types.add(new OptionItem(messages.getText("generic.messages.all"),""));
		types.add(new OptionItem(messages.getText("generic.activity.form.commentarydrive.peerdrivewithpeer"),"generic.activity.form.commentarydrive.peerdrivewithpeer"));
		types.add(new OptionItem(messages.getText("generic.activity.form.commentarydrive.commentarydrivewithsuperior"),"generic.activity.form.commentarydrive.commentarydrivewithsuperior"));
		request.setAttribute("types",types);

		Collection cds = new LinkedList();										                                     		   		
		cds.add(new OptionItem(messages.getText("datatype.scheduledactivitytype.commentarydrive"),"0"));
		cds.add(new OptionItem(messages.getText("datatype.scheduledactivitytype.commentarydriverisk"),"1"));
		request.setAttribute("cd",cds);											
		
		request.setAttribute("vehicleType",new DataTypeUtil(sys).getExtendedVehicleType(true,false,false,sys.isCorporation(CorporationData.MONSANTO)));
	    
%>

<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
</script>
<script>
	function onSelectFrom(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayIdFrom");
  		var input_month = document.getElementById("monthIdFrom");
  		var input_year = document.getElementById("yearIdFrom");
  		var date3 = new Date(time);
  		input_day.value = parseInt(date3.print("%d"),10);
  		input_month.value = parseInt(date3.print("%m"),10);
  		input_year.value= parseInt(date3.print("%Y"),10);
	}
	function onSelectTo(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayIdTo");
  		var input_month = document.getElementById("monthIdTo");
  		var input_year = document.getElementById("yearIdTo");
  		var date3 = new Date(time);
  		input_day.value = parseInt(date3.print("%d"),10);
  		input_month.value = parseInt(date3.print("%m"),10);
  		input_year.value= parseInt(date3.print("%Y"),10);
	}	
	function showType(combo) {
		if(combo.selectedIndex == 0) {
			document.forms['CommentaryDriveReportForm'].conductionType.style.display = "inline";
		}else {
			document.forms['CommentaryDriveReportForm'].conductionType.style.display = "none";
		}
	}
</script>

		<html:form action="/CommentaryDriverReport">			
			<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../../component/titlecomponent.jsp?title="+messages.getText("query.resultcommentarydrive.title")%>' />
  				</tr>
	      		<tr>
					<jsp:include  page="../../component/spaceline.jsp" />
      			</tr>
	      		<tr>
    	    		<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.personaldata")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr align="left">
			      							<td>
				      							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
					      							<tr>
														<th align="left">
															<span class="linetwo"><%=messages.getText("generic.driver.driverdata.firstname")%></span>
														</th>
														<td align="right">
															<html:text property="firstName" size="25" styleClass="input" />    
														</td>
													</tr>  
						      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
													<tr align="left">
														<th align="left"> 
															<span class="linetwo"><%=messages.getText("generic.driver.driverdata.lastname")%></span>
														</th>
														<td align="right">
															<html:text property="lastName" size="25" styleClass="input" />    
														</td>
													</tr> 
						      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
													<tr>
														<td colspan="2" align="left" class="texts">
															<table>
																<tr>
																	<td width="10%">														
																	</td>																							
											                        <td valign="middle"> 
											                          <input type="radio" class="input2" name="status" value='<%=DriverData.ACTIVE%>' <%=(Integer.parseInt(status) == DriverData.ACTIVE) ? "checked" : ""%>><span class="input2"><%=messages.getText("generic.messages.active")%></span>
																	</td>													
								    			                    <td valign="middle"> 
								                			          <input type="radio" class="input2" name="status" value='<%=DriverData.EXCLUDED%>' <%=(Integer.parseInt(status) == DriverData.EXCLUDED) ? "checked" : ""%>><span class="input2"><%=messages.getText("generic.messages.excluded")%></span>
																	</td>
											                        <td valign="middle"> 
											                          <input type="radio" class="input2" name="status" value='<%=DriverData.ALL%>' <%=(Integer.parseInt(status) == DriverData.ALL) ? "checked" : ""%>><span class="input2"><%=messages.getText("generic.messages.all.pl")%></span>
																	</td>
																</tr>
															</table>
														</td>		
													</tr>
<% 					if(sys.getUserSessionData().getFilter().getCorporationId().intValue() == communication.administration.corporation.tables.CorporationData.MONSANTO) {
%>     												<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
													<tr  class="linetwo">
						      							<th align="left"> 
						      								<%=messages.getText("generic.driver.driverdata.vehicleallowed")%><span class="required">*</span>
						      							</th>
						      							<td align="right">
						 	     							<html:select property="vehicleAllowed" styleClass="input" style="width:130;" >
						      									<html:options collection="vehicleType" property="value" labelProperty="label" styleClass="input"/>
							  								</html:select>   
														</td>	   
													</tr>
<%					}else {
%>													<html:hidden property="vehicleAllowed" value=""/>		
<%					}
%>												</table>
											</td>
										</tr> 
							      		<tr>
											<jsp:include  page="../../component/spaceline.jsp" />
						      			</tr>
						      			<tr  class="linetwo">
			      							<td>
				      							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
					      							<tr>
									        			<th align="left"> 	
									        				<%=messages.getText("driver.query.from")%><span class="required">*</span>
									        			</th>
									         			<td align="right">
						                					<html:select property="startDay" styleClass="input" styleId="dayIdFrom">
						                  						<html:options collection="days" property="value" labelProperty="label" styleClass="input"/>
						                					</html:select>
									         			</td>			        						
									         			<td align="right">
						                					<html:select property="startMonth" styleClass="input" styleId="monthIdFrom" style="width:60;">
						                  						<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
						                					</html:select>
									         			</td>
									         			<td align="right">
						                					<html:select property="startYear" styleClass="input" styleId="yearIdFrom">
						                  						<html:options collection="years" property="value" labelProperty="label" styleClass="input"/>
						                					</html:select>
									         			</td>
									         			<td width="40"  align="right">
									         				<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button1" style="cursor: pointer;" onmouseover="this.style.background='red';" style="width:20;" onmouseout="this.style.background=''"/>
						         						</td>
						         						<script type="text/javascript">
															Calendar.setup({
						        								inputArea     :    "yearIdFrom",
						        								ifFormat       :    "%d/%m/%Y",
						        								showsTime      :    true,
						        								timeFormat     :    "24",
						        								button         :    "calendar_button1",
						        								step           :    1,
						        								onSelect       :    onSelectFrom,
						        								singleClick    :    true
						    								});
														</script>
						      						</tr>
									      			<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
									      			<tr  class="linetwo">
									        			<th align="left"> 	
									        				<%=messages.getText("driver.query.to")%><span class="required">*</span>
									        			</th>
									         			<td align="right">
						                					<html:select property="endDay" styleClass="input" styleId="dayIdTo">
						                  						<html:options collection="days" property="value" labelProperty="label" styleClass="input" />
						                					</html:select>
									         			</td>			        						
									         			<td align="right">
						                					<html:select property="endMonth" styleClass="input" styleId="monthIdTo" style="width:60;">
						                  						<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
						                					</html:select>
									         			</td>
									         			<td align="right">
						                					<html:select property="endYear" styleClass="input" styleId="yearIdTo">
						                  						<html:options collection="years" property="value" labelProperty="label" styleClass="input"/>
						                					</html:select>
									         			</td>
									         			<td width="40"  align="right">
									         				<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button2" style="cursor: pointer;" onmouseover="this.style.background='red';" style="width:20;"  onmouseout="this.style.background=''"/>
						         						</td>
						         						<script type="text/javascript">
															Calendar.setup({
						   											inputArea     :    "yearIdTo",
						   											ifFormat       :    "%d/%m/%Y",
						   											showsTime      :    true,
						   											timeFormat     :    "24",
						   											button         :    "calendar_button2",
						   											step           :    1,
						   											onSelect       :    onSelectTo,
						   											singleClick    :    true
																});
														</script>
													</tr>
												</table>
											</td>	
			      						</tr>
							      		<tr>
											<jsp:include  page="../../component/spaceline.jsp" />
						      			</tr>
			      						<!--  Commentary Drive Type -->
									    <tr>
									    	<td width="100%" >
									    		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
					      							<tr class="guideSearch">
					      								<td width="10" align="left"><img src="/datacenter/util/imgs/left_top_search.jpg"></td>
														<td class="guideSearch" align="left"><%=messages.getText("generic.activity.form.commentarydrive.commentarydrivetype")%></td>
														<td width="10" align="right"><img src="/datacenter/util/imgs/right_top_search.jpg"></td>
													</tr>	
													<tr>
														<td width="10" class="leftLineSearch"></td>
						      							<td>
															<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">																
																<tr>
																	<td class="lineone" style="width:100%" align="right">
																		<html:select property="commentaryDrive" styleClass="input" style="width:200;" onchange="showType(this);">
																			<html:options collection="cd" property="value" labelProperty="label" styleClass="input" />
																		</html:select>									
																	</td>
																</tr>
																<tr><td class="lineone">&nbsp;</td></tr>
																<tr>
																	<td class="lineone" style="width:100%" align="right">
																		<html:select property="conductionType" styleClass="input" style="width:200;">
																			<html:options collection="types" property="value" labelProperty="label" styleClass="input" />
																		</html:select>									
																	</td>
																</tr>
                                           					</table>
                                           				</td>
														<td width="10" class="rightLineSearch">&nbsp;</td>
						      						</tr>
						      						<tr>
				                 						<td width="10" align="left" valign="top"><img src="/datacenter/util/imgs/left_base_search.jpg" ></td>
				 				  						<td class="centerBaseSearch">&nbsp;</td>
				 				  						<td width="10" align="right" valign="top" ><img src="/datacenter/util/imgs/right_base_search.jpg" ></td>
						 							</tr>
												</table>
											</td>	
										</tr>
									</table>		
									<jsp:include  page="../../component/utilityboxbottom.jsp" />
								</td>
							</tr>
				      		<tr>
								<jsp:include  page="../../component/spaceline.jsp" />
			      			</tr>
							<tr>
								<td>
									<jsp:include page='<%="../../util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=CommentaryDriveReportForm&consult=true"%>' />
								</td>
							</tr>
						</table>
					</td>
    				<td width="2%">&nbsp;</td>
    				<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<jsp:include page='<%="../../util/divorg.jsp?form=CommentaryDriveReportForm&consult=true" %>' />
						</table>
					</td>
  				</tr>
	      		<tr>
					<jsp:include  page="../../component/spaceline.jsp" />
      			</tr>
				<tr>
					<td align="left" class="texts" colspan="3">
						<jsp:include  page="../../component/utilitybox4top.jsp" />
							<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
								<tr class="lineone">
									<td align="left" class="lineone">
										<span class="input"><b><%=messages.getText("generic.messages.output")%></b></span>&nbsp;
									</td>
									<td align="left" class="lineone">
										<html:select property="output" styleClass="input" >
											<html:options collection="format" property="value" labelProperty="label"/>
										</html:select>							          				
									</td>
									<td align="right" class="lineone" width="90%">
										<html:submit styleClass="input" value='<%=messages.getText("generic.messages.consult")%>' />
									</td>
								</tr>
							</table>
						<jsp:include  page="../../component/utilitybox4bottom.jsp" />
					</td>			
				</tr>
			</table>
		</html:form>
<% 
	}  // Fin del control de seguridad y permisos.
%>