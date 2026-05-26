<%@page language="java"
	errorPage="../exception.jsp"
	import="java.util.*"
	import="java.util.LinkedList"
	import="business.*"
	import="business.driver.*" 
	import="communication.language.*"
	import="communication.security.*"
	import="communication.util.*"
	import="communication.administration.corporation.tables.*"
	import="communication.administration.user.tables.*"
	import="communication.driver.tables.*"
	import="communication.event.tables.*"
	import="communication.activity.tables.*"
	import="communication.vehicle.tables.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.driver.*"
 	import="communication.util.DateValue"
 %>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>
<%! RequestDispatcher dispatcher; String context;String pathView; 
	String pathMsg; String pathSearch; 
	Text messages; String pathLanguage;
	SystemAccess sys; DriverData data; 
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	
	String check = Util.checkPermission(sys,SecurityConstants.MODIFY_DRIVER);
	
	// Permiso para Tests de Alcholemia
	String check2 = Util.checkPermission(sys,SecurityConstants.TESTS_DRIVER);
	
	if(check!=null && check2!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		messages=sys.getText();

		pathLanguage="/util/jsp/language.jsp";
	

		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
	
		context=request.getContextPath();
		pathMsg="/util/jsp/message.jsp";
	
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);

		String id=(String)request.getParameter("id");
		if(id==null){
	id=(String)request.getAttribute("id");
	request.removeAttribute("id");
		}
		if(id==null){
	id=(String)session.getAttribute("id");
	session.removeAttribute("id");
		}
		if(id!=null){	
	data=sys.getDriverReportFacade().findDriverByPrimaryKey(new Integer(id), sys.getUserSessionData());
		}
		session.setAttribute("url","driver/DriverView.jsp?id="+id);
	
		if(data!=null){
%>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

	<script>
	function onSelect(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayId");
  		var input_month = document.getElementById("monthId");
  		var input_year = document.getElementById("yearId");
  		var date3 = new Date(time);
  		input_day.value = date3.print("%d");
  		input_month.value = date3.print("%m");
  		input_year.value= parseInt(date3.print("%Y"),10);
  	}
	</script>
	<head/>
<body bgcolor="#FFFFFF">
	<table width="500" align='center' cellpadding="0" cellspacing="0">
		<tr class="guide1">
			<jsp:include  page='<%="../component/titlecomponent.jsp?colspan=5&title="+messages.getText("web.driver.editing")+"  "+data.getLastName()+","+data.getFirstName()%>' />
		</tr>
		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
		<tr>
			<td width="100%" colspan="5">
				<html:form action="/AddProvisionalBTW">
					<html:hidden property="driver" value='<%=String.valueOf(data.getId())%>'/>
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="top">
								<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("driver.provisionalbtw.new")%>' />
								<table width="100%" cellpadding="0" cellspacing="0">
									<tr>
										<th class="linetwo" align="left"><%=messages.getText("driver.provisionalbtw.date")+" ( "+messages.getText("generic.supportmaterial.reports.format")+": dd/mm/aaaa )"%></th>
										<td class="linetwo">&nbsp;</td>
										<td class="linetwo" align="right">
	            							<html:text property="btwDate" style="width:20;" styleClass="input" styleId="dayId"/>&nbsp;/&nbsp;
	            							<html:text property="btwMonth" style="width:20;" styleClass="input" styleId="monthId"/>&nbsp;/&nbsp;
	            							<html:text property="btwYear" style="width:40;" styleClass="input" styleId="yearId"/>
		            						<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button" style="cursor: pointer; border: 1px solid red;"
     										onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>
			          					</td>
			          					<script type="text/javascript">
												Calendar.setup({
        											inputArea     :    "yearId",
        											ifFormat       :    "%d/%m/%Y",
        											showsTime      :    true,
        											timeFormat     :    "24",
        											button         :    "calendar_button",
        											step           :    1,
        											onSelect       :    onSelect,
        											singleClick    :    true
    											});
										</script>
									</tr>
									<tr>
										<jsp:include  page="../component/spaceline.jsp" />
							  		</tr>
									<tr>
										<th class="linetwo" align="left"><%=messages.getText("driver.provisionalbtw.result")%></th>
										<td class="linetwo">&nbsp;</td>
										<td class="linetwo" align="right">
											<html:select property="result" styleClass="input">
												<html:option value='<%=CEPAActivityConstants.APRUVED%>'><%=messages.getText(CEPAActivityConstants.APRUVED)%></html:option>
												<html:option value='<%=CEPAActivityConstants.NOTAPRUVED%>'><%=messages.getText(CEPAActivityConstants.NOTAPRUVED)%></html:option>
											</html:select>
		          						</td>
									</tr>
									<tr>
										<jsp:include  page="../component/spaceline.jsp" />
							  		</tr>
									<tr>
										<td colspan="3" class="linetwo" align="right">
											<html:submit value='<%=messages.getText("generic.messages.save")%>' styleClass="input"/>
											<html:cancel value='<%=messages.getText("generic.messages.cancel")%>' styleClass="input"/>
										</td>
									</tr>	
								</table>
								<jsp:include page="../component/utilityboxbottom.jsp"/>	
							</td>
				   		</tr>
				   	</table>
			   	</html:form>
			</td>
		</tr>			
<%		}else {
%>		<jsp:include  page='<%="/util/allowedData.jsp?message=driver.error.access.driver" %>'/>
<%		}
	}
%>
</body>
</html:html>