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
	
	Collection vehicles = (Collection)sys.getUserSessionData().getValue();
%>
<html:html>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head>

	<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
	</script>
	<script language="javascript" src='/datacenter/util/js/Prepared_Parameters.js' type='text/javascript' >
	</script>
	<script language="javascript" type='text/javascript' >
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
	function showDate() {
  		var contractDate = document.getElementById("contractDate");
  		var view = "block";
  		var form = document.forms['VehicleContractListForm'];
  		if(form.contractDateType.selectedIndex == 0) {
  			view = "none";
  		}
  		contractDate.style.display = view;
	}
	function doList(){

		var form = document.forms['VehicleContractListForm'];
		var elements=form.elements;
		var length=elements.length;
		var list="";

		var year = document.getElementById("yearIdFrom").value;
		var month =document.getElementById("monthIdFrom").value;
		var day = document.getElementById("dayIdFrom").value;

		// alert("Day: "+day);
		
		if (form.contractDateType.selectedIndex == 1 && (year == "" || month == "" || day== "" || !validdate(year,month,day))){
			alert('<%=messages.getText("generic.vehicle.vehicledata.invalidDate") %>');
			return false;
		}else{
		
			for(i=0;i<length;i++){
				var element=elements[i];
				if(element.type.toLowerCase()=="checkbox"){
					if(element.checked){
						if(list==""){
							list=element.value;
						}else{
							list=element.value+"<"+list;
						}	
					}	
				} 
			}
			form.list.value=list;
			form.submit();
			return true;
		}
	}
	function changeSelected(selectionNumber) {
		var elements=document.forms['VehicleContractListForm'].elements;
		for(i = 0; i < elements.length; i++) {
			var element=elements[i];
			if(element.type.toLowerCase()=="checkbox"){
				if(selectionNumber == 1) {
					element.checked = true;
				}else if(selectionNumber == 2) {
					element.checked = false;
				}else {
					element.checked = !element.checked;
				}
			}
		}
	}

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
		
		if (month == "" || month > 12 || month < 1){ 
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
	</script>


	</head>
	<body bgcolor="#FFFFFF">
		<html:form action="/VehicleContractList2">			
			<html:hidden property="list" />
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
        						<th align="left" width="200"> 	
        							<%=messages.getText("vehicle.contract.date")%><span class="required">*</span>
        						</th>
         						<td align="right" width="400">
         							<html:select property="contractDateType" onchange="showDate()" styleClass="input">
         								<html:option value="0"><%=messages.getText("vehicle.contract.purchasedate") %></html:option>
         								<html:option value="1"><%=messages.getText("vehicle.contract.insertdate") %></html:option>
         							</html:select>
         							<br>
         						</td>
         					</tr>
      						<tr  class="linetwo">
	         					<td align="right" width="400" colspan="2">
         							<div id="contractDate" style="display:none;">
	             						<html:text property="day"  styleClass="input" styleId="dayIdFrom" style="width:20;"  maxlength="2" />
	             						<html:text property="month" styleClass="input" styleId="monthIdFrom" style="width:20;"  maxlength="2" />
	             						<html:text property="year"  styleClass="input" styleId="yearIdFrom" style="width:40;" maxlength="4" />
	         							<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button1" style="cursor: pointer;"
	  											 onmouseover="this.style.background='red';" style="width:20;" onmouseout="this.style.background=''"/>
	         						</div>
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
										<%=messages.getText("generic.messages.type")%>
									</span>
								</th>
					         	<td align="right">
									<html:select property="type" styleClass="input">
										<html:option value="1"><%=messages.getText("vehicle.contract.comodato")%></html:option>
										<html:option value="2"><%=messages.getText("vehicle.contract.distrato")%></html:option>
										<html:option value="3"><%=messages.getText("vehicle.contract.responsability")%></html:option>
										<html:option value="4"><%=messages.getText("vehicle.contract.devolution")%></html:option>
										<html:option value="5"><%=messages.getText("vehicle.contract.comodatoonwfleet")%></html:option>
									</html:select>													
								</td>
							</tr>  
   							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
   							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
   							<tr>
   								<td colspan="2">
    								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      									<tr>
							                <td class="guide2" width="5%"></td>
							                <td class="guide2" width="25%" align="center"><%=messages.getText("generic.data.vehicle")%></td>
							                <td class="guide2" width="70%" align="center"><%=messages.getText("generic.data.driver")%></td>
										</tr>
<%			Iterator it = vehicles.iterator();
			boolean linetwo = true;
			while(it.hasNext()) {
				Object[] data = (Object[])it.next();	
%>										<tr>
	              							<td align="center" class='<%=(linetwo) ? "linetwo" : "lineone" %>'>
												<input type='checkbox' value='<%=data[0]%>' checked >                  
											</td>
                  							<td align="right" class='<%=(linetwo) ? "linetwo" : "lineone" %>'><%=data[1]%></td>
                  							<td align="right" class='<%=(linetwo) ? "linetwo" : "lineone" %>'><%=data[2]+" "+data[3]%></td>
                  						</tr>
<%				linetwo = !linetwo;
			}
%>									</table>
								</td>			
							</tr>
   							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr>
								<td colspan="3" align="right">
									<input type="button" value='<%=messages.getText("generic.messages.all.pl")%>' onclick="javascript:changeSelected(1);" class="input" />
									<input type="button" value='<%=messages.getText("generic.messages.none")%>' onclick="javascript:changeSelected(2);" class="input" />
									<input type="button" value='<%=messages.getText("generic.messages.invertselection")%>' onclick="javascript:changeSelected(3);" class="input" />
								</td>
							</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
   				<tr class="space">
					<td align="right"  class="linetwo" colspan="3">
						<input type='button' class='input' value='<%=messages.getText("generic.vehicle.generatecontract")%>' onclick='javascript:doList();' />
						<html:cancel styleClass="input" value='<%=messages.getText("generic.messages.cancel")%>' />
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html:html>
