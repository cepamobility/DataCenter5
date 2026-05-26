<%@page language="java"
errorPage="../exception.jsp"
	import="business.*"
	import="communication.language.*"
	import="communication.util.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.driver.*"
	import="communication.driver.tables.*"

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
%> 
<%
	context=request.getContextPath();
	dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
	dispatcher.include(request,response);
	
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	messages = sys.getText();
	Collection count = new LinkedList();
	for(int i = 1; i < 6; i++) {
		count.add(new OptionItem(String.valueOf(i),String.valueOf(i)));	    
	}
	request.setAttribute("count",count);
	
	Collection format = new LinkedList();
	format.add(new OptionItem("HTML","HTML"));	    
	format.add(new OptionItem("PDF","PDF"));	    
	format.add(new OptionItem("XLS","XLS"));	    
	request.setAttribute("format",format);
	

	Collection types = new LinkedList();
	types.add(new OptionItem(messages.getText("generic.messages.all.pl"),String.valueOf(0)));
	types.add(new OptionItem(messages.getText("driver.telematiccontrol.speed"),String.valueOf(TelematicControlData.TYPE_EXCESSIVE_SPEED)));
	types.add(new OptionItem(messages.getText("driver.telematiccontrol.nightdrive"),String.valueOf(TelematicControlData.TYPE_NIGTH_DRIVE)));
	request.setAttribute("types",types);

	session.setAttribute("url","driver/telematicControlReport.jsp");
	
	TelematicControlQueryForm form = new TelematicControlQueryForm(String.valueOf(DriverData.ACTIVE));
	session.setAttribute("TelematicControlQueryForm",form);
	
	Collection years = new LinkedList();
    years.add(new OptionItem("",""));
	for(int i = 2011; i <= 2050; i++) {
		years.add(new OptionItem(String.valueOf(i),String.valueOf(i)));
	}
	request.setAttribute("years",years);
	
	Collection months = new LinkedList();
    months.add(new OptionItem("",""));
	for(int i = 1; i <= 12; i++) { 
	    months.add(new OptionItem(messages.getText("generic.month."+String.valueOf(i)),
	    		String.valueOf(i)));
	}
	request.setAttribute("months",months);
	
	
	Collection days = new LinkedList();
    days.add(new OptionItem("",""));
    for(int i = 1; i <= 31; i++) { 
	    days.add(new OptionItem(String.valueOf(i),String.valueOf(i) ));
	}
	request.setAttribute("days",days);
	
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
<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
</script>

<html:html>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head></head>

	<body bgcolor="#FFFFFF">
		<html:form action="/TelematicControlReport">			
			<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("query.telematiccontrolquery.title")%>' />
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
											<th align="left">
												<%=messages.getText("generic.driver.driverdata.firstname")%>
											</th>
											<td>
												<html:text property="firstname" size="35" styleClass="input" />    
											</td>
										</tr>  
										<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr class="linetwo">
											<th align="left">
												<%=messages.getText("generic.driver.driverdata.lastname")%>
											</th>
											<td>
												<html:text property="lastname" size="35" styleClass="input" />    
											</td>
										</tr>  
										<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      									      						
			      						<tr>
											<td height="20" colspan="2" align="left" valign="top" class="texts">
												<table width="60%" border="0" align="right" cellpadding="0" cellspacing="0" class="specialarea">
		                                    		<tr>
		                                        		<td width="29%" valign="middle">
		                                        			<input type="radio" class="specialarea" name="status" value='<%=String.valueOf(DriverData.ACTIVE)%>' checked="checked">
			                                            	<%=messages.getText("generic.messages.active")%> 
		    	                                        </td>
		        	                                    <td width="37%" valign="middle">
		            	                                	<input type="radio" class="specialarea" name="status" value='<%=String.valueOf(DriverData.EXCLUDED)%>' >
		                	                            	<%=messages.getText("generic.messages.excluded")%> 
		                    	                        </td>
		                        	                    <td width="34%" align="left" valign="middle">
		                            	                	<input type="radio" class="specialarea" name="status" value='<%=String.valueOf(DriverData.ALL)%>' >
		                                    	        	<%=messages.getText("generic.messages.all.pl")%> 
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
								<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.telematiccontrol")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
			      						<tr  class="linetwo">
			        						<th align="left"> 	
			        							<%=messages.getText("driver.query.from")%><span class="required">*</span>
			        						</th>
			        						<td align="right">
                								<html:select property="fromday" styleClass="input"  styleId="dayIdFrom">
                  									<html:options collection="days" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
                								&nbsp;
                								<html:select property="frommonth" styleClass="input" style="width:60;" styleId="monthIdFrom">
                  									<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
                								&nbsp;
                								<html:select property="fromyear" styleClass="input" styleId="yearIdFrom">
                  									<html:options collection="years" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
                								&nbsp;
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
			        							<%=messages.getText("driver.query.to")%><span class="required">*</span>
			        						</th>
			        						<td align="right">
                								<html:select property="today" styleClass="input"  styleId="dayIdTo">
                  									<html:options collection="days" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
                								&nbsp;
                								<html:select property="tomonth" styleClass="input" styleId="monthIdTo" style="width:60;">
                  									<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
                								&nbsp;
                								<html:select property="toyear" styleClass="input" styleId="yearIdTo">
                  									<html:options collection="years" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
                								&nbsp;
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
										<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr class="linetwo" >
											<th align="left" class="specialarea" >
												<%=messages.getText("driver.telematic.type")%>
											</th>
											<td align="right" >
									        	<html:select property="type" styleClass="input" >
									        		<html:options collection="types" property="value" labelProperty="label"/>
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
										<jsp:include page='<%="/util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=TelematicControlReportForm&consult=true"%>' />
									</table>
								</td>
							</tr>
							
							
						</table>
					</td>
    				<td width="2%">&nbsp;</td>
    				<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<jsp:include page='<%="/util/divorg.jsp?form=TelematicControlReportForm&consult=true" %>'/>
						</table>
					</td>
  				</tr>
				<!--  Datos de los controles telematicos -->
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
				<tr>
					<td align="left" class="texts" colspan="3">
						<jsp:include  page="../component/utilitybox4top.jsp" />
						<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
							<tr class="lineone">									
								<td align="left">
										<span class="input"><b><%=messages.getText("generic.messages.output")%></b></span>&nbsp;
								</td>
								<td align="left" >
									<html:select property="output" styleClass="input" >
										<html:options collection="format" property="value" labelProperty="label"/>
									</html:select>							          				
								</td>
								<td align="right"  width="90%">
									<html:submit styleClass="input" value='<%=messages.getText("generic.messages.consult")%>' />
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
