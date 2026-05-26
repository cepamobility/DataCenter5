<%@page language="java"
	import="business.*"
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.datatype.*"
	import="communication.datatype.tables.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.event.*"
	import="communication.driver.tables.*"
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
	SystemAccess sys;	String context;
%> 
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");

	context=request.getContextPath();
	dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
	dispatcher.include(request,response);
	
	messages = sys.getText();
	Collection count = new LinkedList();
	for(int i = 1; i < 6; i++) {
		count.add(new OptionItem(String.valueOf(i),String.valueOf(i)));	    
	}
	session.setAttribute("count",count);
	EventReportForm form = new EventReportForm();
	session.setAttribute("EventReportByPeriodForm",form);
	Collection format = new LinkedList();
	format.add(new OptionItem("HTML","HTML"));	    
	format.add(new OptionItem("PDF","PDF"));	    
	format.add(new OptionItem("XLS","XLS"));	    
	session.setAttribute("format",format);

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
	for(int i = 1; i <= 4; i++) { 
	    months.add(new OptionItem("Q"+String.valueOf(i),"Q"+String.valueOf(i)));
	}
	session.setAttribute("months",months);
	
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
	
</script>

<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
</script>

<html:html>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>

	<body bgcolor="#FFFFFF">
		<html:form action="/Eventreportbyperiod">			
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("query.eventbyperiod.title")%>' />
  				</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
	      		<tr>
    	    		<td width="40%" valign="top">
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
										<jsp:include page='<%="/util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=EventReportByPeriodForm&consult=true"%>' />
									</table>
								</td>
							</tr>
							<tr>
								<jsp:include  page='<%="../component/spaceline.jsp" %>'/>
							</tr>
							<jsp:include page='<%="/util/reportOrder.jsp?form=EventReportByPeriodForm"  %>'/>
						</table>
					</td>
    				<td width="16">&nbsp;</td>
    				<td width="40%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<jsp:include page='<%="/util/divorg.jsp?form=EventReportByPeriodForm&consult=true" %>'/>
						</table>
					</td>
  				</tr>
	      		<tr>
					<jsp:include  page='<%="../component/spaceline.jsp" %>'/>
      			</tr>
				<tr>
					<td align="left" class="texts">
						<html:select property="output" styleClass="input" >
							<html:options collection="format" property="value" labelProperty="label"/>
						</html:select>							          				
					</td>
					<td colspan="2" align="right" class="texts">
						<html:submit styleClass="input"><%=messages.getText("generic.messages.consult")%></html:submit>
					</td>
				</tr>
			</table>
		</html:form>

<%@ include  file = '../util/jsp/googleanalytics.jsp' %>
		
	</body>
</html:html>
