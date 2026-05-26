<%@page language="java"
    errorPage="../exception.jsp"
	import="business.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.corporation.tables.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.queries.*"
	import="java.util.*"
	import="communication.administration.RoleData"
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
	SystemAccess sys; 
%>

<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.QUERY);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {

	    int corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();

	    session.setAttribute("url","queries/report_apmm.jsp");

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
		messages=sys.getText();

		Collection format = new LinkedList();
		format.add(new OptionItem("HTML","HTML"));	    
		format.add(new OptionItem("PDF","PDF"));	    
		format.add(new OptionItem("XLS","XLS"));	    
		session.setAttribute("format",format);

		if(session.getAttribute("ReportAPMMForm") == null) {
	        ReportAPMMForm form = new ReportAPMMForm();
		    session.setAttribute("ReportAPMMForm",form);
		}
		
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

		Collection types = new LinkedList();
		types.add(new OptionItem(messages.getText("generic.messages.all"),"0"));
		types.add(new OptionItem(messages.getText("report.fleets.onlycolective"),"1"));
		types.add(new OptionItem(messages.getText("report.fleets.onlyindividual"),"2"));
		session.setAttribute("fleetTypes",types);
%>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>

	<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
	</script>
	
	<script language="javascript">
	
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
	    
		function showDecimals(form,show,labelDecimals) {
			if(form.elements['output'].value == 'XLS') {
				if(show) {
					form.elements['decimals'].style.display = 'block';
					labelDecimals.style.display = 'block';
				}else {
					form.elements['decimals'].checked = false;
					form.elements['decimals'].style.display = 'none';
					labelDecimals.style.display = 'none';
				}
			}else {
				form.elements['decimals'].checked = false;
				form.elements['decimals'].style.display = 'none';
				labelDecimals.style.display = 'none';
			}
		}
	</script>

	<body bgcolor="#FFFFFF">
		<html:form action="/Reportapmk" >
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  				<tr class="guide1">
  					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("fleet.query.apmm.division")%>' />
			  	</tr>
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
				<tr>
					<td width="50%" valign="top">
                    	<table width="100%" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
                       					<tr>
                         					<td valign="top"> <jsp:include page='<%="/util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+"All.jsp?form=ReportAPMMForm&consult=true&default=generic.messages.all.pl"%>' /></td>
                       					</tr>
	                     			</table>					  
								</td>
							</tr>		
                    		<tr>
                     			<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("query.apmm.period")%>' />
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
			      						<tr  class="linetwo">
			        						<th align="left"> 	
			        							<%=messages.getText("query.apmm.from")%><span class="required">*</span>
			        						</th>
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
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr  class="linetwo">
			        						<th align="left"> 	
			        							<%=messages.getText("query.apmm.to")%><span class="required">*</span>
			        						</th>
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
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr  class="linetwo">
			        						<th align="left"> 	
			        							<%=messages.getText("generic.data.fleet.pl")%><span class="required">*</span>
			        						</th>
			         						<td align="right" colspan="3">
                								<html:select property="fleetTypes" styleClass="input">
                  									<html:options collection="fleetTypes" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
			         						</td>
										</tr>				      						
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
									</table>
									<jsp:include  page="../component/utilityboxbottom.jsp" />
								</td>
							</tr>
							<tr>
								<jsp:include  page="../component/spaceline.jsp" />
							</tr>
	                   		<jsp:include page='<%="/util/reportOrder.jsp?form=ReportAPMMForm"%>' />
						</table>
					</td>
	                <td width="16">&nbsp;</td>
                	<td width="50%" valign="top">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
						
							<% if (sys.getUserSessionData().getRole().getName().equals(RoleData.CONTRACTOR)){
								// Si es un usuario de Rol Contratista solo muestro las divisiones organizacionales
								// a las que tiene acceso:
							%>
								<jsp:include page='<%="/util/divorgContractor.jsp?form=ReportAPMMForm"%>' />
							<% }else{ 
								// Por defecto muestro todas las divisiones orgranizacionales
							%>
    	                		<jsp:include page='<%="/util/divorgAll.jsp?form=ReportAPMMForm&consult=true&default=generic.messages.all.pl"%>' />
	                    	<% } %>
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
									<td align="left" class="lineone" width="50%">
										<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
											<tr class="lineone">
												<td>
												<%
													String onchange1 = "javascript:showDecimals(document.forms['ReportAPMMForm']," + String.valueOf(sys.getUserSessionData().isCepaUser()) + ",labelDecimals);";
												%>
													<html:select property="output" styleClass="input" onchange='<%=onchange1%>'>
														<html:options collection="format" property="value" labelProperty="label"/>
													</html:select>					
												</td>
												<td>&nbsp;</td>
												<td align="right">
													<html:checkbox property="decimals" style="display:none" styleClass="input" ></html:checkbox>		          																	
												</td>
												<td align="left">
													<div id="labelDecimals" style="display: none;" class="input" ><%=messages.getText("query.apmm.showdecimals")%></div>												
												</td>
											</tr>
										</table>	
									</td>
									<td align="right"  class="lineone" width="50%">
										<html:submit styleClass="input"><%=messages.getText("generic.messages.open")%></html:submit>
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
<%	}	%>
</html:html>
