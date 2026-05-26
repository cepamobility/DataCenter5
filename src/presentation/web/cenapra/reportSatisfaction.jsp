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
	Text messages;
	RequestDispatcher dispatcher; 
	SystemAccess sys;	
	String context; 
	UserSessionData userSessionData;
%> 
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();
	context=request.getContextPath();
	dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
	dispatcher.include(request,response);
	
	messages = sys.getText();

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
	
	Collection days = new LinkedList();
    days.add(new OptionItem("",""));
	for(int i = 1; i <= 31; i++) {
		days.add(new OptionItem(String.valueOf(i),String.valueOf(i)));
	}
	session.setAttribute("days",days);

	Collection show = new LinkedList();
	show.add(new OptionItem(messages.getText("report.imesevi.showprincipaldata"),"false"));
	show.add(new OptionItem(messages.getText("report.imesevi.showprincipaldata"),"true"));	    
	session.setAttribute("show",show);
%>

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
	</script>

<html:html>
	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>
	<body bgcolor="#FFFFFF">
		<html:form action="/CENAPRAReportSatisfaction">			
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("report.imesevi.checklistresult")%>' />
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
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr class="linetwo" >
				        					<th align="left"> <%=messages.getText("participant.data.firstname")%> </th>
				         					<td align="right"><html:text property="firstName" size="25" styleClass="input"/></td>
										</tr>
				          				<tr>
						        			<td class="linetwo" colspan="2">&nbsp;</td>
						        		</tr>
										<tr class="linetwo" >
				        					<th align="left"> <%=messages.getText("participant.data.lastname")%> </th>
				         					<td align="right"><html:text property="lastName" size="25" styleClass="input"/></td>
										</tr>
									</table>
									<jsp:include  page="../component/utilityboxbottom.jsp" />
								</td>
							</tr>
						</table>
					</td>
    				<td width="2%">&nbsp;</td>
    				<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("report.imesevi.activitydate")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td>
												<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
						      						<tr  class="linetwo">
						        						<th align="left"> 	
						        							<%=messages.getText("query.apmm.from")%><span class="required">*</span>
						        						</th>
						         						<td align="right">
			                								<html:select property="fromDay" styleClass="input" styleId="dayIdFrom">
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
						         						</td align="right">
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
			                								<html:select property="toDay" styleClass="input" styleId="dayIdTo">
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
						</table>
					</td>
  				</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
	      		<tr>
    	    		<td width="100%" valign="top" colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.personaldata")%>' />
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr class="linetwo" >
			         			<th align="left">
			         				<html:checkbox property="multiplicadores" /><%=messages.getText("datatype.scheduledactivitytype.cenapramultiplicadores")%>
			         			</th>
							</tr>			         			
							<tr class="linetwo" >
			         			<th align="left">
			         				<html:checkbox property="replicadores" /><%=messages.getText("datatype.scheduledactivitytype.cenaprareplicadores")%>
			         			</th>
							</tr>
							<tr class="linetwo" >
			         			<th align="left">
			         				<html:checkbox property="multiplicadoresNew" /><%=messages.getText("datatype.scheduledactivitytype.cenapramultiplicadoresnew")%>
			         			</th>
							</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
				<tr>
					<td align="left" class="texts">
						<html:select property="output" styleClass="input" >
							<html:options collection="format" property="value" labelProperty="label"/>
						</html:select>							          				
					</td>
					<td colspan="2" align="right" class="texts">
						<html:submit styleClass="input" ><%=messages.getText("generic.messages.consult")%></html:submit>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html:html>
