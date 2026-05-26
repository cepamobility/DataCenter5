<%@page language="java"
	import="business.*"
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.datatype.*"
	import="communication.datatype.tables.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.driver.*"
	import="communication.driver.tables.*"
	import="communication.administration.user.UserSessionData"
    errorPage="../exception.jsp"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>
<%!
	Text messages; 	RequestDispatcher dispatcher; 
	SystemAccess sys;	String context; UserSessionData userSessionData;
%> 
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();
	int corporationId = sys.getCorporationId();
	context=request.getContextPath();
	dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
	dispatcher.include(request,response);
	
	messages = sys.getText();
	Collection count = new LinkedList();
	for(int i = 1; i < 6; i++) {
		count.add(new OptionItem(String.valueOf(i),String.valueOf(i)));	    
	}
	session.setAttribute("count",count);
	Collection format = new LinkedList();
	format.add(new OptionItem("HTML","HTML"));	    
	format.add(new OptionItem("PDF","PDF"));	    
	format.add(new OptionItem("XLS","XLS"));	    
	session.setAttribute("format",format);

	Collection years = new LinkedList();
	Calendar instance = Calendar.getInstance();
	years.add(new OptionItem("",String.valueOf(0)));
	for(int i = 1998; i <= instance.get(Calendar.YEAR)+1; i++) {
		years.add(new OptionItem(String.valueOf(i),String.valueOf(i)));	
	}
	
	session.setAttribute("years",years);
	
	Collection months = new LinkedList();
	months.add(new OptionItem("",String.valueOf(0)));
	for(int i = 1; i <= 12; i++) { 
	    months.add(new OptionItem(messages.getText("generic.month."+String.valueOf(i)),String.valueOf(i)));
	}
	session.setAttribute("months",months);
	
	Collection record = new LinkedList();
	record.add(new OptionItem(messages.getText("generic.messages.all"),"all"));
	record.add(new OptionItem(messages.getText("generic.event.nonrecordable"),"nrecordable"));	    
	record.add(new OptionItem(messages.getText("generic.event.recordable"),"recordable"));	    	    
	session.setAttribute("record",record);

	Collection driverStatus = new LinkedList();
	driverStatus.add(new OptionItem(messages.getText("generic.messages.active"),String.valueOf(DriverData.ACTIVE)));
	driverStatus.add(new OptionItem(messages.getText("generic.messages.excluded"),String.valueOf(DriverData.EXCLUDED)));
	driverStatus.add(new OptionItem(messages.getText("generic.messages.all.pl"),String.valueOf(DriverData.ALL)));
	session.setAttribute("driverStatusList",driverStatus);
%>

<script>

    function onSelectFrom(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_month = document.getElementById("monthIdFrom");
  		var input_year = document.getElementById("yearIdFrom");
  		var date3 = new Date(time);
  		input_month.value = parseInt(date3.print("%m"),10);
  		input_year.value= parseInt(date3.print("%Y"),10);
	}
	function onSelectTo(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_month = document.getElementById("monthIdTo");
  		var input_year = document.getElementById("yearIdTo");
  		var date3 = new Date(time);
  		input_month.value = parseInt(date3.print("%m"),10);
  		input_year.value= parseInt(date3.print("%Y"),10);
	}

	function changeCombo(radio,combo) {
		var el = document.forms[2].elements[radio];
		document.forms[2].elements[combo].disabled = !el[2].checked;
	}
	function changeType(value){
		document.forms[2].type.value = value;

	}
	
	function detailedQueryState(form,value) {
		if((form.elements['injured'].checked)&& (form.elements['fatalities'].checked)&&(value == 2)) {
				form.elements['fatalities'].click();
				
				
		}else if((form.elements['fatalities'].checked)&&(form.elements['injured'].checked)&&(value == 1)){
				form.elements['injured'].click();
				
		}
		
	}
	
</script>

<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
</script>


<%@page import="communication.administration.corporation.tables.CorporationData"%><html:html>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>

	<body bgcolor="#FFFFFF">
		<html:form action="/Eventreport">			
			<table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.events.consulting")%>' />
  				</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
	      		<tr>
    	    		<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.eventdata")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td>
												<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
						      						<tr  class="linetwo">
						        						<th align="left"> 	
						        							<%=messages.getText("query.apmm.from")%><span class="required">*</span>
						        						</th>
						         						<td align="right">
			                								<html:select property="month1" styleClass="input" styleId="monthIdFrom" style="width:60;">
			                  									<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
			                								</html:select>
						         						</td>
						         						<td align="right">
			                								<html:select property="year1" styleClass="input" styleId="yearIdFrom">
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
			                								<html:select property="month2" styleClass="input" styleId="monthIdTo" style="width:60;">
			                  									<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
			                								</html:select>
						         						</td>
						         						<td align="right">
			                								<html:select property="year2" styleClass="input" styleId="yearIdTo">
			                  									<html:options collection="years" property="value" labelProperty="label" styleClass="input"/>
			                								</html:select>
						         						</td>
						         						<td width="40"  align="right">
			         										<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button2" style="cursor: pointer;"
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
													<!--  <tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
													<tr class="linetwo">
														<th align="left"> 	
						        							//<%//=messages.getText("generic.event.record")%><span> </span>
						        						</th>
						         						<td align="right" colspan="2">
			                								<html:select property="record" styleClass="input" >
			                  									<html:options collection="record" property="value" labelProperty="label" styleClass="input"/>
			                								</html:select>
						         						</td>
													</tr> -->
												</table>
											</td>
										
										<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
											<th align="right">
												<html:checkbox styleClass="input" property="accidents" ><span class="input2"><%=messages.getText("generic.event.accident")%></span></html:checkbox> 																			
												<html:checkbox styleClass="input" property="incidents" ><span class="input2"><%=messages.getText("generic.event.incident")%></span></html:checkbox> 
<%		if(sys.isCorporation(new int[]{CorporationData.MONSANTO,CorporationData.WEYERHAEUSER})) {
%>												<html:checkbox styleClass="input" property="nearmiss" ><span class="input2"><%=messages.getText("generic.event.nearmiss")%></span></html:checkbox>	
<%		}
%>
												<html:checkbox styleClass="input" property="fines" ><span class="input2"><%=messages.getText("generic.event.fine")%></span></html:checkbox>											
											</th>
										</tr>	
										<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
											<td>
												<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
						      						<tr  class="linetwo">
						        						<th align="left"> 	
						        							<%=messages.getText("generic.data.driver")%>
						        						</th>
						         						<td align="right" colspan="2">
			                								<html:select property="driverStatus" styleClass="input" >
			                  									<html:options collection="driverStatusList" property="value" labelProperty="label" styleClass="input"/>
			                								</html:select>
						         						</td>
						         					</tr>
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
			      			<tr>
				    			<td valign="top">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<jsp:include page='<%="/util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=EventReportForm&consult=true"%>' />
									</table>
								</td>
							</tr>
<%		if(corporationId == CorporationData.MONSANTO) {
%>
							<tr>
								<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.eventdata.working")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td>
												<html:checkbox property="workingYes" styleClass="input"><span class="input2"><%=messages.getText("generic.messages.yes")%></span></html:checkbox>
						         			</td>	
											<td>
												<html:checkbox property="workingNo" styleClass="input"><span class="input2"><%=messages.getText("generic.messages.no")%></span></html:checkbox>
						         			</td>	
											<td>
												<html:checkbox property="workingNoData" styleClass="input"><span class="input2"><%=messages.getText("generic.messages.nodata")%></span></html:checkbox>
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
%>						</table>
					</td>
    				<td width="2%">&nbsp;</td>
    				<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<jsp:include page='<%="/util/divorg.jsp?form=EventReportForm&consult=true" %>'/>
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
									<td align="left" class="texts">
										<html:select property="output" styleClass="input" >
											<html:options collection="format" property="value" labelProperty="label"/>
										</html:select>							          				
									</td>
									<td colspan="2" align="right" class="texts">
															
										<html:submit styleClass="input" onclick="javascript:changeType('resumed')"><%=messages.getText("generic.messages.consult")%></html:submit>
										&nbsp;
										<html:hidden property="type"/>
<%                 			if(sys.isCorporation(new int[]{CorporationData.SYNGENTA,CorporationData.SYNGENTA_BR}) || userSessionData.isAccountManager() || userSessionData.isAdministrator()){					
%>										<html:submit styleClass="input" onclick="javascript:changeType('detailed')"><%=messages.getText("generic.messages.consultdetailed")%></html:submit>
<%							} 
%>
									</td>
								</tr>
							</table>
						<jsp:include  page="../component/utilitybox4bottom.jsp" />
					</td>
				</tr>
			</table>
		</html:form>

<%@ include  file = '../util/jsp/googleanalytics.jsp' %>	
	</body>
</html:html>
