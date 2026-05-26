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
	Integer pageNum; boolean all; int pageSize; 
	String id;
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
	
		// session.setAttribute("target","DriverExclude");

		id = request.getParameter("id");
		// session.setAttribute("url","driver/DriverExclusion.jsp?checkboxParamList="+checkboxParamList+"&action="+action);
		
		DriverMainAttributes data = sys.getDriverReportFacade().findDriverMainData(new Integer(id), sys.getUserSessionData());
		if(data != null) {
			
			Calendar c = Calendar.getInstance();
			sys.getUserSessionData().setUrl(null);
%>


<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
<head>
	<META http-equiv="Cache-Control" content="no-cache">
	<META http-equiv="Pragma" content="no-cache">
	<META http-equiv="Expires" content="-1">		
</head>

<script language="javascript" type='text/javascript' >


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
		var form = document.forms['AssociateMitigationPlanForm'];
		var firstElement = form.elements[first]; 
		var secondElement = form.elements[second];
		if(firstElement.value.length == 2) {
			secondElement.focus();
		}
	}
	
</script>

<body bgcolor="#FFFFFF">
	<html:form action="/DriverAssociateMitigationPlan">
		<html:hidden property="id" value='<%=id%>'/>
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1">
				<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("driver.mitigationplan.associateplan")%>' />
	  		</tr>
    	  	<tr>
				<jsp:include  page="../component/spaceline.jsp" />
	      	</tr>
        	<tr>
    	    	<td colspan="3">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.data")%>' />
					<table width='100%' border="0" align='center' cellpadding="0" cellspacing="0">
      					<tr>
        					<td width="100%">
		    					<table width="100%" border="0">
              						<tr class="linetwo">
					    	            <th align="left"><%=messages.getText("generic.driver.driverdata.firstname")%></th>
    	      							<td align="right">
        	          						<%=data.getFirstName()+" "+data.getLastName()%>
            	  						</td>
               						</tr>
						    	  	<tr>
										<jsp:include  page="../component/spaceline.jsp" />
							      	</tr>
									<tr>
				              			<th align="left" valign="middle" class="linetwo"><%=messages.getText("generic.messages.date")+" ( "+messages.getText("generic.supportmaterial.reports.format")+": dd/mm/aaaa )" %><span class="required">*</span></th>
			       						<td align="right" class="linetwo">
											<html:text property='planDate' style='width:20;' styleClass='input' styleId='dayId1'  onkeyup="checkLength('planDate','planMonth')" value='<%=String.valueOf(c.get(Calendar.DATE)) %>'/>&nbsp;/&nbsp;
				            				<html:text property='planMonth' style='width:20;' styleClass='input' styleId='monthId1'  onkeyup="checkLength('planMonth','planYear')" value='<%=String.valueOf(c.get(Calendar.MONTH)+1) %>'/>&nbsp;/&nbsp;
				            				<html:text property='planYear' style='width:40;' styleClass='input' styleId='yearId1'  value='<%=String.valueOf(c.get(Calendar.YEAR)) %>' />
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
									<tr>
										<jsp:include  page="../component/spaceline.jsp" />
							  		</tr>
              						<tr class="linetwo">
					    	            <th align="left"><%=messages.getText("generic.driver.mitigationplan")%></th>
    	      							<td align="right">
        	          						<html:select property="plan" styleClass="input">
<%				for(int i = 0; i < 4; i++) {
%>        	          							<html:option value='<%=String.valueOf(i)%>'><%=messages.getText("driver.pointreport.level"+i) %></html:option>
<%				}
%>        	          						</html:select>
            	  						</td>
               						</tr>
						    	  	<tr>
										<jsp:include  page="../component/spaceline.jsp" />
							      	</tr>
              						<tr class="linetwo">
										<td align="right" colspan="2">
											<html:submit styleClass="input"><%=messages.getText("generic.messages.save")%></html:submit>
								 			<html:cancel styleClass="input"><%=messages.getText("generic.messages.cancel")%></html:cancel >
										</td>
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
	    </table>
	</html:form>
</body>
<%		}else {
%>		<jsp:include  page='<%="/util/allowedData.jsp?message=driver.error.access.driver" %>'/>
<%		}
	}	
%></html:html>
