<%@page language="java"
    errorPage="../exception.jsp"
	import="business.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.datatype.tables.*"
	import="communication.datatype.*"
	import="communication.vehicle.tables.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.queries.*"
	import="business.datatype.*"
	import="communication.fleet.tables.*"
	import="communication.util.*"
	import="java.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>

<%! RequestDispatcher dispatcher;
	Text messages; 
	SystemAccess sys; 
%>
<%	sys = (SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.QUERY);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {

		session.setAttribute("url","queries/reportMileage.jsp");

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
		messages=sys.getText();

		Collection format = new LinkedList();
		format.add(new OptionItem("HTML","HTML"));	    
		format.add(new OptionItem("PDF","PDF"));	    
		format.add(new OptionItem("XLS","XLS"));	    
		session.setAttribute("format",format);

		if(session.getAttribute("ReportOdometerLogForm") == null) {
		    ReportOdometerLogForm form = new ReportOdometerLogForm();
		    session.setAttribute("ReportOdometerLogForm",form);
		}
		
		Collection years = new LinkedList();
		years.add(new OptionItem("","0"));
		Calendar instance = Calendar.getInstance();
		for(int i = 1998; i <= instance.get(Calendar.YEAR)+1; i++) {
			years.add(new OptionItem(String.valueOf(i),String.valueOf(i)));
		}
		session.setAttribute("years",years);
		
		Collection months = new LinkedList();
		months.add(new OptionItem("","0"));
		for(int i = 1; i <= 12; i++) { 
		    months.add(new OptionItem(messages.getText("generic.month."+String.valueOf(i)),String.valueOf(i)));
		}
		session.setAttribute("months",months);
		
		Collection days = new LinkedList();
		days.add(new OptionItem("",""));
		for(int i = 1; i <= 31; i++) {
		    days.add(new OptionItem(String.valueOf(i),String.valueOf(i)));
		}
		session.setAttribute("days",days);

		DataTypeReportFacade dtReportFacade = sys.getDataTypeReportFacade();		
		session.setAttribute("vt",new DataTypeUtil(sys).getVehicleType(true,true,false));

%>	<script language="javascript" type='text/javascript' >
		function selectFleets(message) {
			options = document.forms['ReportOdometerLogForm'].fleets.options;
			var fleetList="";
			var separator=",";
			for(i = 0; i < options.length; i++) {
				element = options[i];
				if(element.selected){
					if(fleetList==""){
						fleetList=element.value;
					}else{
						fleetList=element.value+","+fleetList;
					}	
				}	
			}
			document.forms['ReportOdometerLogForm'].fleet.value = fleetList;
			document.forms['ReportOdometerLogForm'].submit();
		}
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
	</script>
		<html:form action="/ReportOdometerLog">
			<html:hidden property="fleet" />
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  				<tr class="guide1">
  					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("query.logodometer.title")%>' />
			  	</tr>
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
				<tr>
					<td valign="top">
                    	<table width="100%" cellpadding="0" cellspacing="0" border="0">
                    		<tr>
                     			<td width="290" valign="top">
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
						      			<tr  class="linetwo">
			      							<td>
												<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("query.odometerlog.period")%>' />
				      							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
					      							<tr>
									        			<th align="left"> 	
									        				<%=messages.getText("driver.query.from")%>
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
									        				<%=messages.getText("driver.query.to")%>
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
												<jsp:include  page="../component/utilityboxbottom.jsp" />
											</td>	
			      						</tr>
						  				<tr>
											<jsp:include  page="../component/spaceline.jsp" />
									  	</tr>
						      			<tr  class="linetwo">
			      							<td>
												<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("query.odometerlog.period")%>' />
				      							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
						      						<tr  class="linetwo">
						        						<th align="left"> 	
						        							<%=messages.getText("query.accidettype.from")%>
						        						</th>
						         						<td align="right">
			                								<html:select property="frommonth" styleClass="input" >
			                  									<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
			                								</html:select>
						         						</td>
						         						<td align="right">
			                								<html:select property="fromyear" styleClass="input" >
			                  									<html:options collection="years" property="value" labelProperty="label" styleClass="input"/>
			                								</html:select>
						         						</td>
						      						</tr>
						      						<tr  class="linetwo"><td>&nbsp;</td></tr>
						      						<tr  class="linetwo">
						        						<th align="left"> 	
						        							<%=messages.getText("query.accidettype.to")%>
						        						</th>
						         						<td align="right">
			                								<html:select property="tomonth" styleClass="input" >
			                  									<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
			                								</html:select>
						         						</td>
						         						<td align="right">
			                								<html:select property="toyear" styleClass="input" >
			                  									<html:options collection="years" property="value" labelProperty="label" styleClass="input"/>
			                								</html:select>
						         						</td>
						      						</tr>
												</table>
												<jsp:include  page="../component/utilityboxbottom.jsp" />
											</td>
										</tr>
									</table>
								</td>
                     			<td width="20">&nbsp;</td>
								<td valign="top">
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.vehicle.vehicledata")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<th align="left">
												<span class="linetwo">
													<%=messages.getText("generic.vehicle.vehicledata.matricula")%></span>
											</th>
								         	<td align="right">
												<html:text property="plate" style="width:155;" styleClass="input"/>													
											</td>
										</tr>  
		    							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
											<th align="left">
												<span class="linetwo"><%=messages.getText("generic.vehicle.vehicledata.vehicletype")%></span>
											</th>
											<td align="right">
                								<html:select property="type" styleClass="input" style="width:155;">
                  									<html:options collection="vt" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
											</td>
										</tr>  
		    							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
											<td colspan="2" align="left" class="linetwo">
												<table>
													<tr>
														<th align="left">
															<html:radio styleClass="linetwo" property="use" value="persistence.datatype.typeuse.institutional"><span class="linetwo"><%=messages.getText("persistence.datatype.typeuse.institutional")%></span></html:radio>
														</th>
														<th align="left">
															<html:radio styleClass="linetwo" property="use" value="persistence.datatype.typeuse.individual"><span class="linetwo"><%=messages.getText("persistence.datatype.typeuse.individual")%></span></html:radio>
														</th>
														<th align="left">
															<html:radio styleClass="linetwo" property="use" value=""><span class="linetwo"><%=messages.getText("generic.messages.all.pl")%></span></html:radio>
														</th>
													</tr>
												</table>
											</td>
										</tr>
		    							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
											<td colspan="2" align="left" class="linetwo">
												<table>
													<tr>
														<th align="left">
															<html:radio styleClass="linetwo" property="status" value='<%=String.valueOf(VehicleData.ACTIVE)%>'><span class="linetwo"><%=messages.getText("generic.messages.active")%></span></html:radio>
														</th>
														<th align="left">
															<html:radio styleClass="linetwo" property="status" value='<%=String.valueOf(VehicleData.EXCLUDED)%>'><span class="linetwo"><%=messages.getText("generic.messages.excluded")%></span></html:radio>
														</th>
														<th align="left">
															<html:radio styleClass="linetwo" property="status" value='<%=String.valueOf(VehicleData.ALL)%>'><span class="linetwo"><%=messages.getText("generic.messages.all.pl")%></span></html:radio>
														</th>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									<jsp:include  page="../component/utilityboxbottom.jsp" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
				<tr>
                	<td valign="top">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
                    		<tr>
                     			<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("query.mileage.fleet")%>' />
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
			      						<tr  class="linetwo">
			      							<td>
			      								<select name="fleets" size="10" style="width:560;" class="input" multiple>
<%			ListResult result = sys.getFleetReportFacade().findFleetList("",-1,0,-1,sys.getUserSessionData(),FleetData.ACTIVE);
			Iterator it = result.getElements().iterator();
			while(it.hasNext()) {
			    FleetMainAttributes fleet = (FleetMainAttributes)it.next();
			    if(fleet.getType().intValue() == FleetData.INDIVIDUAL) {
%>			    									<option value='<%=String.valueOf(fleet.getId())%>'><%=fleet.getName()+" ("+fleet.getDescription()+")"%></option>
<%				}
			}
%>				  								</select>
			      							</td>
			      						</tr>
			      					</table>
									<jsp:include  page="../component/utilityboxbottom.jsp" />
			      				</td>
			      			</tr>
	                     </table>
    	             </td>
				</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
				<tr>
					<td align="left" class="texts" colspan="3">
						<jsp:include  page="../component/utilitybox4top.jsp" />
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
									<td align="left" class="lineone" width="90%">&nbsp;</td>
								</tr>
								<tr class="lineone">
									<td align="right"  class="lineone" width="100%" colspan="3">
										<%
											String onclick1a = "javascript:selectFleets('"+messages.getText("query.mileage.selectfleet")+"');";
										%>
										<input type="button" class="input" onclick="<%=onclick1a%>" value='<%=messages.getText("generic.messages.open")%>' />
									</td>
								</tr>
							</table>
						<jsp:include  page="../component/utilitybox4bottom.jsp" />
					</td>
				</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
		  	</table>
		</html:form>
<%	}	
%>
