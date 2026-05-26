<%@page language="java"
    errorPage="../exception.jsp"
	import="business.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.datatype.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.queries.*"
	import="communication.datatype.tables.*"
	import="business.datatype.*"
	import="java.util.*"
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

		session.setAttribute("url","queries/report_accidentcount.jsp");

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
		messages=sys.getText();

		Collection format = new LinkedList();
		format.add(new OptionItem("HTML","HTML"));	    
		format.add(new OptionItem("PDF","PDF"));	    
		format.add(new OptionItem("XLS","XLS"));	    
		session.setAttribute("format",format);

		if(session.getAttribute("ReportAccidentCountForm") == null) {
		    ReportAccidentCountForm form = new ReportAccidentCountForm();
		    session.setAttribute("ReportAccidentCountForm",form);
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

		DataTypeReportFacade dtReportFacade = sys.getDataTypeReportFacade();		
		session.setAttribute("vt",new DataTypeUtil(sys).getVehicleType(true,true,false));
		
		
%>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>

	<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
	</script>
	<script language="javascript" type='text/javascript' >
	
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
	
	function changeOrder(){
		order1 = document.ReportAccidentCountForm.order1;
		order2 = document.ReportAccidentCountForm.order2;
		order3 = document.ReportAccidentCountForm.order3;
		order2.disabled=false;
		order3.disabled=false;
		
		if (order1.value=='Division'){
			order2.value = 'Subdivision';
			order3.value = 'Unit';
		}else{
			order2.value = 'Division';
			order3.value = 'Subdivision';
		}
		order2.disabled=true;
		order3.disabled=true;
		
	}
	
	</script>
	<body bgcolor="#FFFFFF">
		<html:form action="/Reportaccidentcount">
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  				<tr class="guide1">
  					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("query.eventcount.title")%>' />
			  	</tr>
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
				<tr>
					<td width="50%" valign="top">
                    	<table width="100%" cellpadding="0" cellspacing="0" border="0">
                    		<tr>
                     			<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("query.apmm.period")%>' />
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
          								<tr class="linetwo">
            								<th align="left" > 
            									<%=messages.getText("generic.vehicle.vehicledata.vehicletype")%>
											</th>
            								<td align="right" colspan="2">
                								<html:select property="vehicletype" styleClass="input" style="width:155;">
                  									<html:options collection="vt" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
            								</td>
            							</tr>
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
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
                								<html:select property="fromyear" styleClass="input" styleId="yearIdFrom" >
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
									</table>
									<jsp:include  page="../component/utilityboxbottom.jsp" />
								</td>
							</tr>
							<tr>
								<jsp:include  page="../component/spaceline.jsp" />
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
                       					<tr>
                         					<td valign="top"> <jsp:include page='<%="/util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=ReportAccidentCountForm&default=generic.messages.all.pl"%>' /></td>
                       					</tr>
	                     			</table>					  
								</td>
							</tr>
							<tr>
								<jsp:include  page="../component/spaceline.jsp" />
							</tr>
                    		<jsp:include page='<%="/util/reportOrder.jsp?form=ReportAccidentCountForm" %>'/>
						</table>
					</td>
	                <td width="16">&nbsp;</td>
                	<td width="50%" valign="top">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
                    		<jsp:include page='<%="/util/divorg.jsp?form=ReportAccidentCountForm&consult=true&default=generic.messages.all.pl" %>'/>
	                     </table>
    	             </td>
				</tr>
	  			<tr>
					<jsp:include  page='<%="../component/spaceline.jsp" %>'/>
	  			</tr>
	  			<tr>
					<td align="left" colspan="3" class="texts">
						<html:select property="output" styleClass="input" >
							<html:options collection="format" property="value" labelProperty="label"/>
						</html:select>							          				
					</td>
	  			</tr>
  				<tr>
  				  	<td height="18" colspan="3" valign="top">
          				<div align="right">
							<html:submit styleClass="input"><%=messages.getText("generic.messages.consult")%></html:submit>
				  		</div>          
				  	</td>
			  	</tr>
		  	</table>
		</html:form>

<%@ include  file = '../util/jsp/googleanalytics.jsp' %>
		
	</body>
<%	}	%>
</html:html>
