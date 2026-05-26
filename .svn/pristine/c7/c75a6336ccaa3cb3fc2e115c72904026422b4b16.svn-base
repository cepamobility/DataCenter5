<%@page import="communication.administration.user.UserSessionData"%>
<%@page import="org.apache.struts.action.DynaActionForm"%>
<%@page language="java" 
	errorPage="../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="business.driver.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.driver.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.driver.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.driver.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>
<%!
	RequestDispatcher dispatcher; 
	String pathLanguage; Text messages; SystemAccess sys;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.VIEW_DRIVER_LIST);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
	
		dispatcher=request.getRequestDispatcher("/util/jsp/language.jsp");
		dispatcher.include(request,response);

		messages=sys.getText();

		String action = "";
		String driverId = "";
		DriverMainAttributes driver = null;
		PointAccountData account = null;
		int pointCount = 5;
		int type = 0;
		String comment = "";
		String moveId = null;
		Calendar c = Calendar.getInstance();
		PointMoveData move = null;
		boolean otherValue = false;
		if(request.getParameter("move") != null) {
	moveId = request.getParameter("move");
	action = "/DriverModifyAssignPoints";
	move = sys.getCorporationReportFacade().getPointMove(new Integer(moveId),sys.getUserSessionData());
	c = move.getDate();
	pointCount = move.getAmount().intValue();
	type = (pointCount < 0) ? 0 : 1;
	otherValue = (Math.abs(pointCount) != 5 && Math.abs(pointCount) != 10 && Math.abs(pointCount) != 15 && Math.abs(pointCount) != 20);
	pointCount = Math.abs(pointCount);
	comment = move.getReference();
	account = sys.getCorporationReportFacade().getPointAccount(move.getAccount(), sys.getUserSessionData());
	driverId = String.valueOf(account.getReference());
	driver = (DriverMainAttributes)sys.getDriverReportFacade().getDrivers(driverId, sys.getUserSessionData()).iterator().next();
		} else {
	action = "/DriverAssignPoints";
	driverId = request.getParameter("driver");
	driver = (DriverMainAttributes)sys.getDriverReportFacade().getDrivers(driverId, sys.getUserSessionData()).iterator().next();
	account = sys.getCorporationReportFacade().getPointAccount(PointAccountData.DRIVER, driver.getId(), sys.getUserSessionData());
		}
		String date = String.valueOf(c.get(Calendar.DATE));
		String month = String.valueOf(c.get(Calendar.MONTH)+1);
		String year = String.valueOf(c.get(Calendar.YEAR));
%>


<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
<head>
	<META http-equiv="Cache-Control" content="no-cache">
	<META http-equiv="Pragma" content="no-cache">
	<META http-equiv="Expires" content="-1">
	<script type="text/javascript">
		function confirmDelete() {
			if(confirm("Confirma que desea borrar este movimiento?")) {
				document.forms["DeletePointMoveForm"].submit();
			}
		}
		function onSelect1(calendar, date) {
			var date2 = calendar.date;
			var time = date2.getTime();
	  		var input_day = document.getElementById('dayId1');
	  		var input_month = document.getElementById('monthId1');
	  		var input_year = document.getElementById('yearId1');
	  		var date3 = new Date(time);
	  		input_day.value = date3.print("%d");
	  		input_month.value = date3.print("%m");
	  		input_year.value= parseInt(date3.print("%Y"),10);
	  	}

		function checkLength(first,second) {
			var form = document.forms['DriverAssignPointsForm'];
			var firstElement = form.elements[first]; 
			var secondElement = form.elements[second];
			if(firstElement.value.length == 2) {
				secondElement.focus();
			}
		}
		
		function showOtherValue() {
			var form = document.forms['DriverAssignPointsForm'];
			if(form.elements['points'].selectedIndex == 4) {
				form.elements['otherPoint'].style.display = 'block';
			}else {
				form.elements['otherPoint'].style.display = 'none';
			}
		}
	</script>		
</head>

<body bgcolor="#FFFFFF">
	<html:form action="/DriverDeleteAssignPoints">
		<html:hidden property="move" value='<%=moveId%>'/>
		<html:hidden property="amount" value='<%=(move == null) ? "" : String.valueOf(move.getAmount())%>'/>
		<html:hidden property="account" value='<%=String.valueOf(account.getId())%>'/>
	</html:form>
	<html:form action="<%=action%>">
		<html:hidden property="driver" value='<%=driverId%>'/>
		<html:hidden property="account" value='<%=String.valueOf(account.getId())%>'/>
		<html:hidden property="move" value='<%=moveId%>'/>
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1">
				<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("web.driver.assignpoints")%>' />
	  		</tr>
    	  	<tr>
				<jsp:include  page="../component/spaceline.jsp" />
	      	</tr>
        	<tr>
    	    	<td colspan="3">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.data.driver")%>' />
					<table width='100%' border="0" align='center' cellpadding="0" cellspacing="0">
      					<tr>
        					<td width="100%">
		    					<table width="100%" border="0">
              						<tr class="linetwo">
					    	            <th align="left"><%=messages.getText("generic.driver.driverdata.firstname")%></th>
	         							<td align="right">
        	          						<%=driver.getFirstName()+" "+driver.getLastName()%>
            	  						</td>
               						</tr>
									<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
									<tr  class="linetwo"> 
										<th align="left"> <%= messages.getText("generic.driver.points")%></th>
										<td align="right"><%=String.valueOf(account.getTotal())%></td>
									</tr>
		   						</table>
          					</td>
	      				</tr>
					</table>
   					<jsp:include page="../component/utilityboxbottom.jsp"/>	
				</td>
	      	</tr>
    	  	<tr>
				<jsp:include  page="../component/spaceline.jsp" />
	      	</tr>
			<tr>
				<td width="100%" colspan="3">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.assignpoints")+"*"%>' />
					<table width="100%" cellpadding="0" cellspacing="0">
<%			if(sys.isCorporation(CorporationData.BAYER_MX)) {
%>						<tr  class="linetwo"> 
							<th align="left"> Tipo<span class="required">*</span>:</th>
							<td align="right">
								<html:select property="assignType" styleClass="input">
									<html:option value="0">Sin Asociación</html:option>
									<html:option value="1">Asociados a eBTW</html:option>
									<html:option value="2">Asociados a eAssessment</html:option>
									<html:option value="3">Asociados a eLearning</html:option>
									<html:option value="4">Otros</html:option>
								</html:select>				
							</td>
						</tr>		
<%			}else {
%>						<html:hidden property="assignType" value="0"/>				
<%			}
%>					
						<tr  class="linetwo"> 
							<th align="left"> <%= messages.getText("generic.messages.date")%><span class="required">*</span></th>
							<td align="right">
								<html:text property='date' style='width:20;' styleClass='input' styleId='dayId1'  onkeyup="checkLength('date','month')" value="<%=date%>"/>&nbsp;/&nbsp;
		            			<html:text property='month' style='width:20;' styleClass='input' styleId='monthId1'  onkeyup="checkLength('month','year')" value="<%=month%>"/>&nbsp;/&nbsp;
		            			<html:text property='year' style='width:40;' styleClass='input' styleId='yearId1'  value="<%=year%>" />
		            				<html:img page='/component/jscalendar-1.0/img.gif' styleId='calendar_button1' style='cursor: pointer; border: 1px solid red;'
     										onmouseover="this.style.background='red';" style='width:20;' align='middle' onmouseout="this.style.background=''"/>
			          			<script type="text/javascript">
									Calendar.setup({
    									inputArea     :    'yearId1',
    									ifFormat       :    '%d/%m/%Y',
    									showsTime      :    true,
    									timeFormat     :    '24',
    									button         :    'calendar_button1',
    									step           :    1,
    									onSelect       :    onSelect1,
    									singleClick    :    true
 									});
								</script>
							</td>
						</tr>
						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo"> 
							<th align="left"> <%= messages.getText("generic.driver.points")%><span class="required">*</span></th>
							<td align="right">
								<html:select property="points" styleClass="input" onchange="showOtherValue()">
<%					for(int i = 5; i <=20; i = i+5) {
%>									<option value="<%=String.valueOf(i)%>"  <%=(i == pointCount) ? "selected" : ""%>><%=String.valueOf(i)%></option>
<%					}
%>									<option value="0"  <%=(otherValue) ? "selected" : ""%>><%=messages.getText("generic.messages.othervalue")%></option>
								</html:select>
<%					if(otherValue) {
%>								<html:text property="otherPoint" styleClass="input" style="width:20;" value="<%=String.valueOf(pointCount)%>"></html:text>
<%					}else {
%>								<html:text property="otherPoint" styleClass="input" style="display:none; width:30;" ></html:text>
<%					}
%>								<html:select property="type" styleClass="input">
									<option value="0" s <%=(type == 0) ? "selected" : ""%>><%=messages.getText("driver.points.meritorious")%></option>
									<option value="1" <%=(type == 1) ? "selected" : ""%>><%=messages.getText("driver.points.demeritorious")%></option>
								</html:select>
							</td>
						</tr>
						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr class="linetwo">
							<th align="left"> <%= messages.getText("generic.driver.comment")%><span class="required">*</span></th>
							<td align="right">
								<html:text property="comment" styleClass="input" size="60" value="<%=comment%>" />
							</td>
						</tr>
					</table>
					<jsp:include  page="../component/utilityboxbottom.jsp" />
				</td>
			</tr>
			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
 			</tr>
			<tr  class="linetwo">
				<td colspan="3">
					<jsp:include  page='../component/utilitybox4top.jsp' />
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr  class="lineone"> 
							<td align="right">
								<html:submit styleClass="input"><%=messages.getText("generic.messages.save")%></html:submit>
					 			<html:cancel styleClass="input"><%=messages.getText("generic.messages.cancel")%></html:cancel >
			<%			if(moveId != null) { 
			%>					<input type="button" value='<%=messages.getText("generic.messages.delete") %>' class="input" onclick="confirmDelete();"/>		
			<%			} 
			%>				 			
							</td>
						</tr>
					</table>
					<jsp:include  page="../component/utilitybox4bottom.jsp" />
				</td>
			</tr>
	    </table>
	</html:form>
</body>
<%	}	
%></html:html>
