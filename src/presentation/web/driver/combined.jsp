<%@page language="java"
	import="business.*"
	import="communication.language.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.driver.*"
	import="communication.driver.tables.*"
	import="communication.util.*"
	import="communication.administration.corporation.tables.*"
	import="communication.datatype.tables.GeneralDTData"
	import="communication.datatype.DataTypeConstants"
	import="communication.activity.tables.CEPAActivityConstants"
    errorPage="../exception.jsp"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>
<%!
	Text messages; SystemAccess sys;
	RequestDispatcher dispatcher;
%> 
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	int corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();
	messages = sys.getText();
	session.setAttribute("url","driver/combined.jsp");
	dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
	dispatcher.include(request,response);	
	Collection format = new LinkedList();
	format.add(new OptionItem("HTML","HTML"));	    
	format.add(new OptionItem("PDF","PDF"));	    
	format.add(new OptionItem("XLS","XLS"));	    
	session.setAttribute("format",format);
	
	Collection statusList = new LinkedList();
	statusList.add(new OptionItem(messages.getText("generic.messages.active"),String.valueOf(DriverData.ACTIVE)));
	statusList.add(new OptionItem(messages.getText("generic.messages.excluded"),String.valueOf(DriverData.EXCLUDED)));
	statusList.add(new OptionItem(messages.getText("generic.messages.all.pl"),String.valueOf(DriverData.ALL)));
	session.setAttribute("statusList",statusList);

	Collection years = new LinkedList();
	years.add(new OptionItem("",""));
	Calendar instance = Calendar.getInstance();
	for(int i = 1998; i <= instance.get(Calendar.YEAR)+1; i++) {
		years.add(new OptionItem(String.valueOf(i),String.valueOf(i)));
	}
	session.setAttribute("years",years);
		
	Collection months = new LinkedList();
	months.add(new OptionItem("",""));
	for(int i = 1; i <= 12; i++) { 
		months.add(new OptionItem(messages.getText("generic.month."+String.valueOf(i)),String.valueOf(i)));
	}
	session.setAttribute("months",months);

	Collection activities = new LinkedList();
	activities.add(new OptionItem(messages.getText("generic.messages.all"),"generic.messages.all"));                			
	Iterator iter=sys.getDataTypeReportFacade().findAll(DataTypeConstants.TYPECEPAACTIVITY,sys.getUserSessionData()).iterator();
    while(iter.hasNext()){
    	GeneralDTData itemType=(GeneralDTData)iter.next();
	    activities.add(new OptionItem(messages.getText(itemType.getResourceBundleId()),itemType.getResourceBundleId()));
	}
    session.setAttribute("activities",activities);	
    
	Collection accidents = new LinkedList();
	accidents.add(new OptionItem(messages.getText("generic.messages.all"),"generic.messages.all"));                			
	iter=sys.getDataTypeReportFacade().findAll(DataTypeConstants.OLD_ACCIDENT_CLASIFICATION,sys.getUserSessionData()).iterator();
    while(iter.hasNext()){
    	GeneralDTData itemType=(GeneralDTData)iter.next();
	    accidents.add(new OptionItem(messages.getText(itemType.getResourceBundleId()),itemType.getResourceBundleId()));
	}
    session.setAttribute("accidents",accidents);	

	Collection incidents = new LinkedList();
	incidents.add(new OptionItem(messages.getText("generic.messages.all"),"generic.messages.all"));                			
	iter=sys.getDataTypeReportFacade().findAll(DataTypeConstants.OLD_INCIDENT_CLASIFICATION,sys.getUserSessionData()).iterator();
    while(iter.hasNext()){
    	GeneralDTData itemType=(GeneralDTData)iter.next();
    	incidents.add(new OptionItem(messages.getText(itemType.getResourceBundleId()),itemType.getResourceBundleId()));
	}
    session.setAttribute("incidents",incidents);	

	Collection fines = new LinkedList();
	fines.add(new OptionItem(messages.getText("generic.messages.all"),"generic.messages.all"));                			
	iter=sys.getDataTypeReportFacade().findAll(DataTypeConstants.FINE_TYPE,sys.getUserSessionData()).iterator();
    while(iter.hasNext()){
    	GeneralDTData itemType=(GeneralDTData)iter.next();
    	fines.add(new OptionItem(messages.getText(itemType.getResourceBundleId()),itemType.getResourceBundleId()));
	}
    session.setAttribute("fines",fines);	
    
    Collection countA = new LinkedList();
	countA.add(new OptionItem(messages.getText("driver.consult.accident.all"),"6"));	    
	countA.add(new OptionItem(messages.getText("driver.consult.accident.with"),"7"));	    
	for(int i = 1; i < 6; i++) {
		countA.add(new OptionItem(messages.getText("driver.consult.accident.atleast")+" "+String.valueOf(i),String.valueOf(i)));	    
	}
	countA.add(new OptionItem(messages.getText("driver.consult.accident.none"),"0"));	    
	session.setAttribute("countA",countA);

    Collection countI = new LinkedList();
	countI.add(new OptionItem(messages.getText("driver.consult.accident.all"),"6"));	    
	countI.add(new OptionItem(messages.getText("query.accidettype.withincident"),"7"));	    
	for(int i = 1; i < 6; i++) {
		countI.add(new OptionItem(messages.getText("driver.consult.accident.atleast")+" "+String.valueOf(i),String.valueOf(i)));	    
	}
	countI.add(new OptionItem(messages.getText("query.accidettype.withoutincident"),"0"));	    
	session.setAttribute("countI",countI);

    Collection countF = new LinkedList();
	countF.add(new OptionItem(messages.getText("driver.consult.accident.all"),"6"));	    
	countF.add(new OptionItem(messages.getText("query.accidettype.withfine"),"7"));	    
	for(int i = 1; i < 6; i++) {
		countF.add(new OptionItem(messages.getText("driver.consult.accident.atleast")+" "+String.valueOf(i),String.valueOf(i)));	    
	}
	countF.add(new OptionItem(messages.getText("query.accidettype.withoutfine"),"0"));	    
	session.setAttribute("countF",countF);

	Collection relationsAE = new LinkedList();
	for(int i = 0; i < 8; i++) {
		relationsAE.add(new OptionItem(messages.getText("query.combined.relationAE"+String.valueOf(i)),String.valueOf(i)));	    
	}
	session.setAttribute("relationsAE",relationsAE);

	Collection relationsEA = new LinkedList();
	for(int i = 0; i < 8; i++) {
		relationsEA.add(new OptionItem(messages.getText("query.combined.relationEA"+String.valueOf(i)),String.valueOf(i)));	    
	}
	session.setAttribute("relationsEA",relationsEA);
%>

<html:html>
	<head>
		<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	</head>
	<script>
		function changeCombo(radio,combo) {
			var el = document.forms[2].elements[radio];
			document.forms[2].elements[combo].disabled = !el[2].checked;
		}
		function onSelectActivityFrom(calendar, date) {
			var date2 = calendar.date;
			var time = date2.getTime();
	  		var input_month = document.getElementById("monthActivityFrom");
	  		var input_year = document.getElementById("yearActivityFrom");
	  		var date3 = new Date(time);
	  		input_month.value = parseInt(date3.print("%m"),10);
	  		input_year.value= parseInt(date3.print("%Y"),10);
		}
		function onSelectActivityTo(calendar, date) {
			var date2 = calendar.date;
			var time = date2.getTime();
	  		var input_month = document.getElementById("monthActivityTo");
	  		var input_year = document.getElementById("yearActivityTo");
	  		var date3 = new Date(time);
	  		input_month.value = parseInt(date3.print("%m"),10);
	  		input_year.value= parseInt(date3.print("%Y"),10);
		}
		function onSelectEventFrom(calendar, date) {
			var date2 = calendar.date;
			var time = date2.getTime();
	  		var input_month = document.getElementById("monthEventFrom");
	  		var input_year = document.getElementById("yearEventFrom");
	  		var date3 = new Date(time);
	  		input_month.value = parseInt(date3.print("%m"),10);
	  		input_year.value= parseInt(date3.print("%Y"),10);
		}
		function onSelectEventTo(calendar, date) {
			var date2 = calendar.date;
			var time = date2.getTime();
	  		var input_month = document.getElementById("monthEventTo");
	  		var input_year = document.getElementById("yearEventTo");
	  		var date3 = new Date(time);
	  		input_month.value = parseInt(date3.print("%m"),10);
	  		input_year.value= parseInt(date3.print("%Y"),10);
		}
		function showData(div) {
			if(div == 1) {
				var combo = document.forms['CombinedReportForm'].activities;
				if(combo.options.selectedIndex == 0) {
					document.getElementById("activityData").style.display = "none";
				}else {
					document.getElementById("activityData").style.display = "inline";
				}
			}else {
				var combo = document.forms['CombinedReportForm'].events;
				if(combo.options.selectedIndex == 0) {
					document.getElementById("eventData").style.display = "none";
				}else {
					document.getElementById("eventData").style.display = "inline";
				}
			}
		}
	</script>
	<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
	</script>
	<body bgcolor="#FFFFFF">
		<html:form action="/CombinedReport" >			
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.driver.combined")%>' />
  				</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
	      		<tr>
    	    		<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.personaldata")%>' />
									<table class="SearchBack" width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr class="linetwo" >
				        					<th align="left"> <%=messages.getText("generic.driver.driverdata.firstname")%> </th>
				         					<td align="right"><html:text property="firstName" size="25" styleClass="input"/></td>
										</tr>
				          				<tr>
						        			<td class="linetwo" colspan="2">&nbsp;</td>
						        		</tr>
										<tr class="linetwo">
											<th align="left"> <%=messages.getText("generic.driver.driverdata.lastname")%> </th>
				         					<td align="right"><html:text property="lastName" size="25" styleClass="input"/></td>
										</tr>
				          				<tr>
						        			<td class="linetwo" colspan="2">&nbsp;</td>
						        		</tr>
										<tr class="linetwo">
				        					<th align="left"> <%=messages.getText("generic.driver.driverdata.status")%> </th>
				         					<td align="right">
											    <html:select property="status" styleClass="input" >
													<html:options collection="statusList" property="value" labelProperty="label"/>
												</html:select>							          				
											</td>
				    					</tr>
									</table>
									<jsp:include  page="../component/utilityboxbottom.jsp" />
								</td>
							</tr>
				      		<tr>
								<jsp:include  page="../component/spaceline.jsp" />
			      			</tr>
			      			<tr>
				    			<td valign="top">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<jsp:include page='<%="/util/divgeo"+String.valueOf(sys.getUserSessionData().getFilter().getGeoLevelCount())+".jsp?form=CombinedReportForm&consult=true"%>' />
									</table>
								</td>
							</tr>
						</table>
					</td>
    				<td width="2%">&nbsp;</td>
    				<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<jsp:include page='<%="/util/divorg.jsp?form=CombinedReportForm&consult=true" %>'/>
						</table>
					</td>
  				</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
	      		<tr>
    	    		<td width="100%" valign="top" colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.data.activity.pl")%>' />
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
    							<th width="49%" valign="top" class="linetwo">
    								<html:select property="activities" onchange="showData(1);" style="width: 200;" styleClass="input">
    									<html:option value="0"><%=messages.getText("query.combined.withoutactivities")%></html:option>
    									<html:option value="1"><%=messages.getText("query.combined.withactivities")%></html:option>
    								</html:select>
								</th>
							</tr>
				      		<tr>
								<jsp:include  page="../component/spaceline.jsp" />
   							</tr>
							<tr>
								<td colspan="3">
									<div id="activityData" style="display: none;">
										<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("driver.query.period")%>' />
										<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			      							<tr  class="linetwo">
			      								<td  width="49%">
													<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			      										<tr class="linetwo">
			        										<th align="left"> 	
			        											<%=messages.getText("query.apmm.from")%>
			        										</th>
			         										<td align="left">
                												<html:select property="fromMonthActivity" styleClass="input" styleId="monthActivityFrom" style="width:60;">
                  													<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
                												</html:select>
			         										</td>
			         										<td align="left">
                												<html:select property="fromYearActivity" styleClass="input" styleId="yearActivityFrom">
                  													<html:options collection="years" property="value" labelProperty="label" styleClass="input"/>
                												</html:select>
			         										</td>
			         										<td width="40"  align="right">
			         											<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button1" style="cursor: pointer;" onmouseover="this.style.background='red';" style="width:20;" onmouseout="this.style.background=''"/>
				         									</td>
			         										<td width="50">&nbsp;</td>
							         						<script type="text/javascript">
																Calendar.setup({
        															inputArea     :    "yearActivityFrom",
        															ifFormat       :    "%d/%m/%Y",
        															showsTime      :    true,
        															timeFormat     :    "24",
        															button         :    "calendar_button1",
        															step           :    1,
        															onSelect       :    onSelectActivityFrom,
        															singleClick    :    true
    															});
															</script>
														</tr>
													</table>
												</td>	
				         						<td width="2%">&nbsp;</td>
			      								<td  width="49%">
													<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			      			      						<tr  class="linetwo">
							        						<th align="left"> 	
				        										<%=messages.getText("query.apmm.to")%>
				        									</th>
			    	     									<td align="left">
                												<html:select property="toMonthActivity" styleClass="input" styleId="monthActivityTo" style="width:60;">
                  													<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
                												</html:select>
			         										</td>
			         										<td align="left">
                												<html:select property="toYearActivity" styleClass="input" styleId="yearActivityTo">
                  													<html:options collection="years" property="value" labelProperty="label" styleClass="input"/>
                												</html:select>
			         										</td>
				         									<td width="40"  align="left">
				         										<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button2" style="cursor: pointer;" onmouseover="this.style.background='red';" style="width:20;"  onmouseout="this.style.background=''"/>
				         									</td>
			         										<td width="50">&nbsp;</td>
							         						<script type="text/javascript">
																Calendar.setup({
        															inputArea     :    "yearActivityTo",
        															ifFormat       :    "%d/%m/%Y",
        															showsTime      :    true,
        															timeFormat     :    "24",
        															button         :    "calendar_button2",
        															step           :    1,
        															onSelect       :    onSelectActivityTo,
        															singleClick    :    true
    															});
															</script>
			      										</tr>
			      									</table>
			      								</td>
			      							</tr>
			      						</table>
										<jsp:include  page="../component/utilitybox2bottom.jsp" />
								      	<br>
										<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.activity.activitydata.type")%>' />
										<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			      							<tr>
								         		<td align="left" colspan="3">
					                				<html:select property="activityType" styleClass="input" style="width:500; height: 200;" multiple="true">
					                  					<html:options collection="activities" property="value" labelProperty="label" styleClass="input"/>
					                				</html:select>
									         	</td>
			      							</tr>
			      						</table>
										<jsp:include  page="../component/utilitybox2bottom.jsp" />
									</div>
								</td>
							</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
	      		<tr>
    	    		<td valign="top" colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.driver.extradata")%>' />
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
    							<th width="49%" valign="top" class="linetwo" align="center">
    								<html:select property="events" onchange="showData(2);" style="width: 200;" styleClass="input">
    									<html:option value="0"><%=messages.getText("query.combined.withoutevents")%></html:option>
    									<html:option value="1"><%=messages.getText("query.combined.withevents")%></html:option>
    								</html:select>
								</th>
							</tr>
				      		<tr>
								<jsp:include  page="../component/spaceline.jsp" />
   							</tr>
							<tr>
								<td colspan="3">
									<div id="eventData" style="display: none;">
										<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("driver.query.period")%>' />
										<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
			      							<tr  class="linetwo">
			      								<td  width="49%">
													<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			      										<tr class="linetwo">
			        										<th align="left"> 	
			        											<%=messages.getText("query.apmm.from")%>
			        										</th>
			         										<td align="left">
                												<html:select property="fromMonthEvent" styleClass="input" styleId="monthEventFrom" style="width:60;">
                  													<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
                												</html:select>
			         										</td>
			         										<td align="left">
                												<html:select property="fromYearEvent" styleClass="input" styleId="yearEventFrom">
                  													<html:options collection="years" property="value" labelProperty="label" styleClass="input"/>
                												</html:select>
			         										</td>
			         										<td width="40"  align="right">
			         											<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button1" style="cursor: pointer;" onmouseover="this.style.background='red';" style="width:20;" onmouseout="this.style.background=''"/>
				         									</td>
			         										<td width="50">&nbsp;</td>
							         						<script type="text/javascript">
																Calendar.setup({
        															inputArea     :    "yearEventFrom",
        															ifFormat       :    "%d/%m/%Y",
        															showsTime      :    true,
        															timeFormat     :    "24",
        															button         :    "calendar_button1",
        															step           :    1,
        															onSelect       :    onSelectEventFrom,
        															singleClick    :    true
    															});
															</script>
														</tr>
													</table>
												</td>	
				         						<td width="2%">&nbsp;</td>
			      								<td  width="49%">
													<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			      			      						<tr  class="linetwo">
							        						<th align="left"> 	
				        										<%=messages.getText("query.apmm.to")%>
				        									</th>
			    	     									<td align="left">
                												<html:select property="toMonthEvent" styleClass="input" styleId="monthEventTo" style="width:60;">
                  													<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
                												</html:select>
			         										</td>
			         										<td align="left">
                												<html:select property="toYearEvent" styleClass="input" styleId="yearEventTo">
                  													<html:options collection="years" property="value" labelProperty="label" styleClass="input"/>
                												</html:select>
			         										</td>
				         									<td width="40"  align="left">
				         										<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button2" style="cursor: pointer;" onmouseover="this.style.background='red';" style="width:20;"  onmouseout="this.style.background=''"/>
				         									</td>
			         										<td width="50">&nbsp;</td>
							         						<script type="text/javascript">
																Calendar.setup({
        															inputArea     :    "yearEventTo",
        															ifFormat       :    "%d/%m/%Y",
        															showsTime      :    true,
        															timeFormat     :    "24",
        															button         :    "calendar_button2",
        															step           :    1,
        															onSelect       :    onSelectEventTo,
        															singleClick    :    true
    															});
															</script>
			      										</tr>
			      									</table>
			      								</td>
			      							</tr>
			      						</table>
										<jsp:include  page="../component/utilitybox2bottom.jsp" />
										<br>
										<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.event.supervise.evitability")%>' />
										<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			      							<tr>
								         		<td align="right" colspan="3">
					                				<html:select property="evitability" styleClass="input" style="width:200;">
					                  					<html:option value="0"><%=messages.getText("generic.messages.all")%></html:option>
					                  					<html:option value="1"><%=messages.getText("generic.messages.yes")%></html:option>
					                  					<html:option value="2"><%=messages.getText("generic.messages.no")%></html:option>
					                				</html:select>
									         	</td>
			      							</tr>			      							
			      						</table>
										<jsp:include  page="../component/utilitybox2bottom.jsp" />
										<br>
										<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.messages.accident")%>' />
										<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
											<tr class="linetwo">
												<th><%=messages.getText("generic.messages.count")%></th>
								         		<td align="left">
					                				<html:select property="accidentCount" styleClass="input" style="width:400;">
					                  					<html:options collection="countA" property="value" labelProperty="label" styleClass="input"/>
					                				</html:select>
									         	</td>
											</tr>
								      		<tr>
												<jsp:include  page="../component/spaceline.jsp" />
				   							</tr>
			      							<tr>
								         		<td align="left" colspan="3">
					                				<html:select property="accidentType" styleClass="input" style="width:500; height: 150;" multiple="true">
					                  					<html:options collection="accidents" property="value" labelProperty="label" styleClass="input"/>
					                				</html:select>
									         	</td>
			      							</tr>
			      						</table>
										<jsp:include  page="../component/utilitybox2bottom.jsp" />
										<br>
										<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.messages.incident")%>' />
										<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
											<tr class="linetwo">
												<th><%=messages.getText("generic.messages.count")%></th>
								         		<td align="left">
					                				<html:select property="incidentCount" styleClass="input" style="width:400;">
					                  					<html:options collection="countI" property="value" labelProperty="label" styleClass="input"/>
					                				</html:select>
									         	</td>
											</tr>
								      		<tr>
												<jsp:include  page="../component/spaceline.jsp" />
				   							</tr>
			      							<tr>
								         		<td align="left" colspan="3">
					                				<html:select property="incidentType" styleClass="input" style="width:500; height: 150;" multiple="true">
					                  					<html:options collection="incidents" property="value" labelProperty="label" styleClass="input"/>
					                				</html:select>
									         	</td>
			      							</tr>
			      						</table>
										<jsp:include  page="../component/utilitybox2bottom.jsp" />
										<br>
										<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.messages.fine")%>' />
										<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
											<tr class="linetwo">
												<th><%=messages.getText("generic.messages.count")%></th>
								         		<td align="left">
					                				<html:select property="fineCount" styleClass="input" style="width:400;">
					                  					<html:options collection="countF" property="value" labelProperty="label" styleClass="input"/>
					                				</html:select>
									         	</td>
											</tr>
								      		<tr>
												<jsp:include  page="../component/spaceline.jsp" />
				   							</tr>
			      							<tr>
								         		<td align="left" colspan="3">
					                				<html:select property="fineType" styleClass="input" style="width:500; height: 150;" multiple="true">
					                  					<html:options collection="fines" property="value" labelProperty="label" styleClass="input"/>
					                				</html:select>
									         	</td>
			      							</tr>
			      						</table>
										<jsp:include  page="../component/utilitybox2bottom.jsp" />
									</div>
								</td>
							</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
	      		<tr>
    	    		<td valign="top" colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("report.combined.event.activity")%>' />
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
     						<tr>
				         		<td align="center" colspan="3" class="input">
				         			<html:checkbox property="relationAccident"><%=messages.getText("generic.messages.accident")%></html:checkbox>&nbsp;&nbsp;
				         			<html:checkbox property="relationIncident"><%=messages.getText("generic.messages.incident")%></html:checkbox>&nbsp;&nbsp;
				         			<html:checkbox property="relationFine"><%=messages.getText("generic.messages.fine")%></html:checkbox>
				         		</td>
				         	</tr>
				      		<tr>
								<jsp:include  page="../component/spaceline.jsp" />
			      			</tr>
     						<tr>
				         		<td align="left" colspan="3">
	                				<html:select property="relationEA" styleClass="input">
	                  					<html:options collection="relationsEA" property="value" labelProperty="label" styleClass="input"/>
	                				</html:select>
					         	</td>
     						</tr>
				      		<tr>
								<jsp:include  page="../component/spaceline.jsp" />
			      			</tr>
				      		<tr>
								<jsp:include  page="../component/spaceline.jsp" />
			      			</tr>
     						<tr>
				         		<td align="left" colspan="3">
	                				<html:select property="relationAE" styleClass="input">
	                  					<html:options collection="relationsAE" property="value" labelProperty="label" styleClass="input"/>
	                				</html:select>
					         	</td>
     						</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
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
									<td align="right"  class="lineone" width="90%">
										<html:submit styleClass="input" value='<%=messages.getText("generic.messages.consult")%>' />
									</td>
								</tr>
							</table>
						<jsp:include  page="../component/utilitybox4bottom.jsp" />
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html:html>
