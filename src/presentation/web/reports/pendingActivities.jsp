<%@page language="java"
	import="business.*"
	import="communication.language.*"
	import="communication.util.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.activity.schedule.*"
	import="communication.driver.tables.*"
	import="communication.datatype.*"
	import="communication.datatype.tables.*"	
	import="communication.administration.user.*"
	import="communication.administration.corporation.tables.*"
	import="communication.activity.tables.*"
	import="business.activity.*"
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
	RequestDispatcher dispatcher; String context;
	UserSessionData userSessionData;	
%> 
<%
	context=request.getContextPath();
	dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
	dispatcher.include(request,response);
	
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	
	userSessionData=sys.getUserSessionData();	
	messages = sys.getText();
	Collection count = new LinkedList();
	for(int i = 1; i < 6; i++) {
		count.add(new OptionItem(String.valueOf(i),String.valueOf(i)));	    
	}
	session.setAttribute("count",count);
	
	Collection format = new LinkedList();
	format.add(new OptionItem("HTML","HTML"));	    
	format.add(new OptionItem("XLS","XLS"));	    
	session.setAttribute("format",format);

	Collection days = new LinkedList();
    days.add(new OptionItem("",""));
	for(int i = 1; i <= 31; i++) {
		days.add(new OptionItem(String.valueOf(i),String.valueOf(i)));
	}
	session.setAttribute("days",days);
	
	Collection years = new LinkedList();
    years.add(new OptionItem("",""));
	for(int i = 1998; i <= 2026; i++) {
		years.add(new OptionItem(String.valueOf(i),String.valueOf(i)));
	}
	session.setAttribute("years",years);
	
	Collection months = new LinkedList();
    months.add(new OptionItem("",""));
	for(int i = 1; i <= 12; i++) { 
	    months.add(new OptionItem(messages.getText("generic.month."+String.valueOf(i)),String.valueOf(i)));
	}
	session.setAttribute("months",months);
		
	Collection col1=new LinkedList();
   	col1.add(new OptionItem(messages.getText("generic.messages.active"),String.valueOf(DriverData.ACTIVE)));
   	col1.add(new OptionItem(messages.getText("generic.messages.excluded"),String.valueOf(DriverData.EXCLUDED)));   	
   	col1.add(new OptionItem(messages.getText("generic.messages.all.pl"),String.valueOf(DriverData.ALL)));   	
                   
	session.setAttribute("colstatus",col1);			

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
    
	function changeCombo(radio,combo) {
		var el = document.forms[2].elements[radio];
		document.forms[2].elements[combo].disabled = !el[2].checked;
	}
</script>
<script>
function changeType(value){
	document.forms[2].type.value = value;

}
</script>
<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
</script>

		<html:form action="/PendingActivities">			
			<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("query.activity.pendingactivities")%>' />
  				</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
	      		<tr>
    	    		<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("query.activityexpiry.basicdata")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
			      						<tr class="linetwo">
        									<th align="left">
        										<%=messages.getText("generic.driver.mitigationplan")%>
        									</th>			
        								</tr>
			      						<tr class="linetwo">
        									<td align="left" colspan="4">
        										<html:select property="mitigation" multiple="true" styleClass="input" style="width:250px;" >
<%				for(int i = 2; i < 9; i++) {
%>        											<html:option value='<%=String.valueOf(i)%>'><%=messages.getText("driver.mitigationplan.activity"+i)%></html:option>								
<%				}
%>        										</html:select>
        									</td>
        								</tr>
										<tr class="linetwo"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr class="linetwo">
        									<th align="left">
        										<%=messages.getText("generic.driver.anualplan")%>
        									</th>			
        								</tr>
			      						<tr class="linetwo">
        									<td align="left" colspan="4">
        										<html:select property="anual" multiple="true" styleClass="input" style="width:250px;" >
<%				for(int i = 1; i < 12; i++) {
%>        											<html:option value='<%=String.valueOf(i)%>'><%=messages.getText("driver.anualplan.activity"+i)%></html:option>								
<%				}
%>        										</html:select>
        									</td>
        								</tr>
										<tr class="linetwo"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr class="linetwo">
        									<th align="left">
        										<%=messages.getText("query.activityexpiry.driverstate")%><span class="required">*</span>
        									</th>			
        									<td align="right" colspan="4">
												<html:select property="status" styleClass="input" style="width:155;" >
													<html:options collection="colstatus" property="value" labelProperty="label" styleClass="input" />												
												</html:select>   											
         									</td>								
      									</tr>
										<tr class="linetwo"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr  class="linetwo">
			        						<th align="left"> 	
			        							<%=messages.getText("driver.query.from")%>
			        						</th>
			         						<td align="right">
                								<html:select property="fromday" styleClass="input" styleId="dayIdFrom" >
                  									<html:options collection="days" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
			         						</td>			        						
			         						<td align="right">
                								<html:select property="frommonth" styleClass="input" styleId="monthIdFrom" style="width:60;">
                  									<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
			         						</td>
			         						<td align="right">
                								<html:select property="fromyear" styleClass="input" styleId="yearIdFrom">
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
			      						<tr class="linetwo"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr  class="linetwo">
			        						<th align="left"> 	
			        							<%=messages.getText("driver.query.to")%><span class="required">*</span>
			        						</th>
			         						<td align="right">
                								<html:select property="today" styleClass="input" styleId="dayIdTo">
                  									<html:options collection="days" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
			         						</td>			        						
			         						<td align="right">
                								<html:select property="tomonth" styleClass="input" styleId="monthIdTo" style="width:60;">
                  									<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
			         						</td>
			         						<td align="right">
                								<html:select property="toyear" styleClass="input" styleId="yearIdTo">
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
									<jsp:include  page="../component/utilityboxbottom.jsp" />
								</td>
							</tr>
				      		<tr>
								<jsp:include  page="../component/spaceline.jsp" />
			      			</tr>
			      			<tr>
				    			<td valign="top">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<jsp:include page='<%="/util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=PendingActivitiesReportForm"%>' />
									</table>
								</td>
							</tr>
						</table>
					</td>
    				<td width="2%">&nbsp;</td>
    				<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<jsp:include page='<%="../util/divorg.jsp?form=PendingActivitiesReportForm&consult=true"%>' />
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
								<tr>
									<td align="left" class="lineone">
										<html:select property="output" styleClass="input" >
											<html:options collection="format" property="value" labelProperty="label"/>
										</html:select>							          				
									</td>
									<td colspan="2" align="right" class="lineone">
										<html:submit styleClass="input" ><%=messages.getText("generic.messages.open")%></html:submit>
									</td>
								</tr>
							</table>
						<jsp:include  page="../component/utilitybox4bottom.jsp" />
					</td>
				</tr>
			</table>
		</html:form>

<%@ include file = '../util/jsp/googleanalytics.jsp' %>
		
