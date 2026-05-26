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
	
	Collection format = new LinkedList();
	format.add(new OptionItem("HTML","HTML"));	    
	format.add(new OptionItem("PDF","PDF"));	    
	format.add(new OptionItem("XLS","XLS"));	    
	session.setAttribute("format",format);

	Collection statusList = new LinkedList();
	statusList.add(new OptionItem(messages.getText("generic.messages.active"),String.valueOf(DriverData.ACTIVE)));
	statusList.add(new OptionItem(messages.getText("generic.messages.excluded"),String.valueOf(DriverData.EXCLUDED)));
	statusList.add(new OptionItem(messages.getText("generic.messages.all.pl"),String.valueOf(DriverData.ALL)));
	session.setAttribute("statusList",statusList);

	session.setAttribute("url","driver/driverPointQuery.jsp");
	DriverPointReportForm form = null;
	if(session.getAttribute("DriverPointReportForm") == null) {
		form = new DriverPointReportForm();
	    session.setAttribute("DriverPointReportForm",form);
	}else {
		form = (DriverPointReportForm)session.getAttribute("DriverPointReportForm");
	}
%>

<html:html>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>

	<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
	</script>

	<script language="javascript" type='text/javascript' >
		function showValue() {
			var div = document.getElementById('showVal');
			if(document.forms['DriverPointReportForm'].operator.selectedIndex == 0) {
				div.style.display = 'none';
			}else {
				div.style.display = 'block';
			}
		}
	</script>

	<body bgcolor="#FFFFFF">
		<html:form action="/DriverPointReport">			
			<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("query.driverpoints.title")%>' />
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
										<tr class="linetwo">
											<th align="left">
												<%=messages.getText("generic.driver.driverdata.firstname")%>
											</th>
											<td align="right">
												<html:text property="firstName" size="35" styleClass="input" />    
											</td>
										</tr>  
										<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr class="linetwo">
											<th align="left">
												<%=messages.getText("generic.driver.driverdata.lastname")%>
											</th>
											<td align="right">
												<html:text property="lastName" size="35" styleClass="input" />    
											</td>
										</tr>  
				          				<tr>
						        			<td class="linetwo" colspan="2">&nbsp;</td>
						        		</tr>
										<tr class="linetwo" align="left">
				        					<th align="left"> <%=messages.getText("generic.driver.driverdata.status")%> </th>
				         					<td align="right">
											    <html:select property="status" styleClass="input" >
													<html:options collection="statusList" property="value" labelProperty="label"/>
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
								<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.points")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
			      						<tr  class="linetwo">
			         						<td align="right">
			         							<html:select property="operator" onchange="showValue()" styleClass="input">
			         								<html:option value="0"><%=messages.getText("generic.messages.all.pl") %></html:option>
			         								<html:option value="1"><%=messages.getText("generic.messages.less.than") %></html:option>
			         								<html:option value="2"><%=messages.getText("generic.messages.equals.to") %></html:option>
			         								<html:option value="3"><%=messages.getText("generic.messages.greater.than") %></html:option>
			         							</html:select>
			         						</td>
			         						<td>
			         							<div id="showVal" style="display: <%=(form.getOperator() == null || Integer.parseInt(form.getOperator()) == 0) ? "none" : "block"%>;">
			         								<html:text property="value" styleClass="input" size="5"/>
			         							</div>
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
										<jsp:include page='<%="/util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=DriverPointReportForm&consult=true"%>' />
									</table>
								</td>
							</tr>
						</table>
					</td>
    				<td width="2%">&nbsp;</td>
    				<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<jsp:include page='<%="/util/divorg.jsp?form=DriverPointReportForm&consult=true" %>'/>
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
								<td align="left" class="lineone">
									<html:select property="type" styleClass="input" >
										<html:option value="0"><%=messages.getText("generic.vehicle.reportType.resumed") %></html:option>
										<html:option value="1"><%=messages.getText("generic.vehicle.reportType.detailed") %></html:option>
									</html:select>							          				
									<html:select property="output" styleClass="input" >
										<html:options collection="format" property="value" labelProperty="label"/>
									</html:select>							          				
								</td>
								<td align="right" class="lineone">
									<html:submit styleClass="input" ><%=messages.getText("generic.messages.open")%></html:submit>
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
