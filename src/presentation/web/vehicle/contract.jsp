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

	dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
	dispatcher.include(request,response);

	messages = sys.getText();
	
	String vehicleId = request.getParameter("id");
	if(!Util.empty(vehicleId)) {
		VehicleData vehicle = sys.getVehicleReportFacade().findVehicleById(new Integer(vehicleId),sys.getUserSessionData());
		DriverData driver = vehicle.getDriver();
		if(vehicle != null && driver != null) {
%>
<html:html>


	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head>
	
	<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
	</script>
	<script language="javascript" src='/datacenter/util/js/Prepared_Parameters.js' type='text/javascript' >
	</script>
	<script language="javascript" type='text/javascript' >

	function yearbi(year){
		if (year % 4 != 0)
			return false;
		else{
			if (year % 100 == 0){
				if (year % 400 == 0){
					return true;
				}else{
					return false;
				}
			}else{
				return true;
			}
		}
	}
	
	function validdate(year,month,day){
		if(yearbi(year))
			febrero=29;
		else
			febrero=28;		
		if (month > 12 || month < 1){ 
			return false;
		}		
		if (month==02){
			if( day>febrero || day < 1){				
				return false;
			}
		}		
		if (((month==4) || (month==6) || (month==9) || (month==11)) && ((day>30) || (day < 1))){			
			return false;
		}		
		if (((month==1) || (month==3) || (month==5) || (month==7) || (month==8) ||
			(month==10) || (month==12)) && ((day > 31) || (day < 1))){			
			return false;
		}
		
		return true;
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

	function submitForm(){
		var year = document.getElementById("yearIdFrom").value;
		var month =document.getElementById("monthIdFrom").value;
		var day = document.getElementById("dayIdFrom").value;
		if (!validdate(year,month,day)){
			alert('<%=messages.getText("generic.vehicle.vehicledata.invalidDate") %>');
			return false;
		}else{
			document.forms["VehicleContractForm"].submit();
			
		}
		return true;
	}


	</script>
	
	</head>


	<body bgcolor="#FFFFFF">
		<html:form action="/VehicleContract"   >			
			<html:hidden property="vehicle" value="<%=vehicleId%>" />
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.vehicle.generatecontract")%>' />
  				</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
	      		<tr>
    	    		<td width="10%" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.vehicle.vehicledata")%>' />
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
      						<tr  class="linetwo">
        						<th align="left"> 	
        							<%=messages.getText("generic.messages.date")%><span class="required">*</span>
        						</th>
         						<td align="right">
             						<html:text property="day" styleClass="input"  maxlength="2" styleId="dayIdFrom" style="width:20;" />
             						<html:text property="month"  styleClass="input" maxlength="2" styleId="monthIdFrom" style="width:20;" />
             						<html:text property="year"  styleClass="input" maxlength="4" styleId="yearIdFrom" style="width:40;"/>
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
   							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr>
								<th align="left">
									<span class="linetwo">
										<%=messages.getText("generic.messages.type")%></span>
								</th>
					         	<td align="right">
									<html:select property="type" styleClass="input">
										<html:option value="vehicle.contract.comodato"><%=messages.getText("vehicle.contract.comodato")%></html:option>
										<html:option value="vehicle.contract.distrato"><%=messages.getText("vehicle.contract.distrato")%></html:option>
										<html:option value="vehicle.contract.responsability"><%=messages.getText("vehicle.contract.responsability")%></html:option>
										<html:option value="vehicle.contract.devolution"><%=messages.getText("vehicle.contract.devolution")%></html:option>
										<html:option value="vehicle.contract.comodatoonwfleet"><%=messages.getText("vehicle.contract.comodatoonwfleet")%></html:option>
									</html:select>													
								</td>
							</tr>  
   							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr>
								<th align="left">
									<span class="linetwo">
										<%=messages.getText("generic.messages.vehicle")%></span>
								</th>
					         	<td align="right" class="linetwo">
									<%=vehicle.getMatricula()%>													
								</td>
							</tr>  
   							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr>
								<th align="left">
									<span class="linetwo">
										<%=messages.getText("generic.data.driver")%></span>
								</th>
					         	<td align="right" class="linetwo">
									<%=driver.getFirstName()+" "+driver.getLastName()%>													
								</td>
							</tr>  
   							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
   							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr class="space">
								<td align="right"  class="linetwo" colspan="2">
									<input  type='button' class='input' value='<%=messages.getText("generic.vehicle.generatecontract")%>' onclick='javascript:submitForm();' />
									<html:cancel styleClass="input" value='<%=messages.getText("generic.messages.cancel")%>' />
								</td>
							</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
			</table>
		</html:form>

<%@ include  file = '../util/jsp/googleanalytics.jsp' %>
	</body>
</html:html>
<%		}
	}
%>