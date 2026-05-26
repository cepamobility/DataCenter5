<%@page language="java"
	import="business.*"
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.datatype.*"
	import="communication.datatype.tables.*"
	import="communication.vehicle.tables.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.vehicle.*"
	import="communication.driver.tables.*"
	import="communication.administration.corporation.tables.*"
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
	messages = sys.getText();

	dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
	dispatcher.include(request,response);

	VehicleContractReportForm form = new VehicleContractReportForm();
	session.setAttribute("VehicleContractReportForm",form);
	
	Collection format = new LinkedList();
	format.add(new OptionItem("HTML","HTML"));	    
	format.add(new OptionItem("PDF","PDF"));	    
	format.add(new OptionItem("XLS","XLS"));	    
	request.setAttribute("format",format);
	
	Collection days = new LinkedList();
    days.add(new OptionItem("",""));
	for(int i = 1; i <= 31; i++) {
		days.add(new OptionItem(String.valueOf(i),String.valueOf(i)));
	}
	request.setAttribute("days",days);
	
	Collection years = new LinkedList();
    years.add(new OptionItem("",""));
	for(int i = 2011; i <= 2026; i++) {
		years.add(new OptionItem(String.valueOf(i),String.valueOf(i)));
	}
	request.setAttribute("years",years);
	
	Collection months = new LinkedList();
    months.add(new OptionItem("",""));
	for(int i = 1; i <= 12; i++) { 
	    months.add(new OptionItem(messages.getText("generic.month."+String.valueOf(i)),String.valueOf(i)));
	}
	request.setAttribute("months",months);
	
%>
<html:html>

<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
</script>
<script language="javascript" src='/datacenter/util/js/Prepared_Parameters.js' type='text/javascript' >
</script>

<script>
	function changeCombo(radio,combo) {
		var el = document.forms[2].elements[radio];
		document.forms[2].elements[combo].disabled = !el[2].checked;
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


	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head>
	</head>

	<body bgcolor="#FFFFFF">
		<html:form action="/VehicleContractReport">			
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("vehicle.contract.report")%>' />
  				</tr>
  				  				
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
	      		<tr>
    	    		<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						
						<!--  Periodo: -->		                 
		                 <tr>
		                  			<td>
								<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("query.apmm.period")%>' />
								<table width="100%" border="0" align="center" cellpadding="0"
										 cellspacing="0" class="default" >
		      						<tr  class="linetwo">
		        						<th align="left"> 	
		        							<%=messages.getText("query.apmm.from")%><span class="required">*</span>
		        						</th>
		        						<td align="right">
		               								<html:select property="fromDate" styleClass="input" styleId="dayIdFrom">
		                 									<html:options collection="days" property="value" labelProperty="label" styleClass="input"/>
		               								</html:select>
				         				</td>	
		         						<td align="right">
		             								<html:select property="fromMonth" styleClass="input" styleId="monthIdFrom" style="width:60;">
		               									<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
		             								</html:select>
		         						</td>
		         						<td align="right">
		             								<html:select property="fromYear" styleClass="input" styleId="yearIdFrom">
		               									<html:options collection="years" property="value" labelProperty="label" styleClass="input"/>
		             								</html:select>
		         						</td>
		         						<td width="40"  align="right">
		         							<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button1" style="cursor: pointer;"
		  											 onmouseover="this.style.background='red';" style="width:20;" onmouseout="this.style.background=''"/>
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
		        							<%=messages.getText("query.apmm.to")%><span class="required">*</span>
		        						</th>
		        						<td align="right">
		               								<html:select property="toDate" styleClass="input" styleId="dayIdTo">
		                 									<html:options collection="days" property="value" labelProperty="label" styleClass="input" />
		               								</html:select>
				         				</td>
		         						<td align="right">
		             								<html:select property="toMonth" styleClass="input" styleId="monthIdTo" style="width:60;">
		               									<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
		             								</html:select>
		         						</td>
		         						<td align="right">
		             								<html:select property="toYear" styleClass="input" styleId="yearIdTo">
		               									<html:options collection="years" property="value" labelProperty="label" styleClass="input"/>
		             								</html:select>
		         						</td>
		         						<td width="40"  align="right">
		         							<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button2" style="cursor: pointer; border: 1px solid red;"
		  											 onmouseover="this.style.background='red';" style="width:20;"  onmouseout="this.style.background=''"/>
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
						
						<!-- Info del Vehiculo: -->
							<tr>
								<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.vehicle.vehicledata")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<th align="left">
												<span class="linetwo">
												<%=messages.getText("generic.vehicle.vehicledata.vehicletype")%>
												</span>
											</th>
											<td align="right">
<%			
			DataTypeReportFacade dtReportFacade = sys.getDataTypeReportFacade();		
			session.setAttribute("vt",new DataTypeUtil(sys).getVehicleType(false,true,false));
%>
                								<html:select property="vehicleType" styleClass="input" style="width:155;">
                  									<html:options collection="vt" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
											</td>
										</tr>  
		    							<tr  class="space">
		    								<th align="right">&nbsp;</th>
		    								<td align="right">&nbsp;</td>
		    							</tr>
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
										
										<tr  class="space">
											<th align="right">&nbsp;</th>
											<td align="right">&nbsp;</td>
										</tr>
										<tr>
											<th align="left">
												<span class="linetwo">
													<%=messages.getText("generic.messages.type")%></span>
											</th>
								         	<td align="right">
												<html:select property="type" styleClass="input">
													<html:option value="<%=VehicleContractDataType.WITHOUT_CONTRACT%>">
															<%=messages.getText(VehicleContractDataType.WITHOUT_CONTRACT)%>
													</html:option>
													<html:option value="<%=VehicleContractDataType.COMODATO%>">
														<%=messages.getText(VehicleContractDataType.COMODATO)%>
													</html:option>
													<html:option value="<%=VehicleContractDataType.DISTRATO %>">
														<%=messages.getText(VehicleContractDataType.DISTRATO)%>
													</html:option>
													<html:option value="<%=VehicleContractDataType.RESPONSABILITY%>">
														<%=messages.getText(VehicleContractDataType.RESPONSABILITY)%>
													</html:option>
													<html:option value="<%=VehicleContractDataType.DEVOLUTION%>">
														<%=messages.getText(VehicleContractDataType.DEVOLUTION)%>
													</html:option>
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
										<jsp:include page='<%="/util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=VehicleContractListReportForm&consult=true"%>' />
									</table>
								</td>
							</tr>
						</table>
					</td>
    				<td width="2%">&nbsp;</td>
    				<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<jsp:include page='<%="/util/divorg.jsp?form=VehicleContractListReportForm&consult=true" %>'/>
						</table>
					</td>
  				</tr>
  				
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
	  			<tr>
					<td align="left" colspan="1" class="texts">
						<html:select property="output" styleClass="input" >
							<html:options collection="format" property="value" labelProperty="label"/>
						</html:select>							          				
					</td>
					<td>&nbsp;</td>
					<td align="right" class="texts" colspan="1">
						<input type="hidden" name="checkboxParamList" />
						<html:submit styleClass="input" 
							value='<%=messages.getText("generic.messages.consult")%>' />
					</td>
	  			</tr>
  				
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
				<tr>
					
				</tr>
			</table>
		</html:form>

<%@ include  file = '../util/jsp/googleanalytics.jsp' %>
	</body>
</html:html>
