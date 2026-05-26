<%@page language="java"
	import="business.*"
	import="communication.language.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.driver.*"
	import="communication.driver.tables.*"
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
	messages = sys.getText();
	Collection count = new LinkedList();
	for(int i = 1; i < 6; i++) {
		count.add(new OptionItem(String.valueOf(i),String.valueOf(i)));	    
	}
	session.setAttribute("count",count);
	AptitudTestReportForm form = new AptitudTestReportForm();
	session.setAttribute("AptitudTestReportForm",form);
	session.setAttribute("url","driver/aptitudTestQuery.jsp");
	dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
	dispatcher.include(request,response);	
	Collection format = new LinkedList();
	format.add(new OptionItem("HTML","HTML"));	    
	format.add(new OptionItem("PDF","PDF"));	    
	format.add(new OptionItem("XLS","XLS"));	    
	session.setAttribute("format",format);

%>

<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
</script>

<html:html>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>

	<body bgcolor="#FFFFFF">
		<html:form action="/AptitudTestReport">			
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("driver.query.aptitudtest.query")%>' />
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
										<tr>
											<th align="left">
												<span class="linetwo"><%=messages.getText("generic.driver.driverdata.firstname")%></span>
											</th>
											<td align="right">
												<html:text property="name" size="25" styleClass="input" />    
											</td>
										</tr>  
							      		<tr>
											<jsp:include  page="../component/spaceline.jsp" />
						      			</tr>
										<tr>
											<th align="left">
												<span class="linetwo"><%=messages.getText("generic.driver.driverdata.lastname")%></span>
											</th>
											<td align="right">
												<html:text property="name" size="25" styleClass="input" />    
											</td>
										</tr>  
							      		<tr>
											<jsp:include  page="../component/spaceline.jsp" />
						      			</tr>
										<tr>
											<td colspan="2" align="right" class="texts">
												<table>
													<tr>
														<th align="right">
															<html:radio styleClass="input" property="status" value='<%=String.valueOf(DriverData.ACTIVE)%>'><span class="linetwo"><%=messages.getText("generic.messages.active")%></span></html:radio>
														</th>
														<th align="right">
															<html:radio styleClass="input" property="status" value='<%=String.valueOf(DriverData.EXCLUDED)%>'><span class="linetwo"><%=messages.getText("generic.messages.excluded")%></span></html:radio>
														</th>
														<th align="right">
															<html:radio styleClass="input" property="status" value='<%=String.valueOf(DriverData.ALL)%>'><span class="linetwo"><%=messages.getText("generic.messages.all.pl")%></span></html:radio>
														</th>
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
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("driver.aptitudtest.data")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
					            		<tr class="linetwo">
				              				<th align="left" valign="middle"><%=messages.getText("driver.aptitudtest.expiryfrom") %></th>
				       						<td align="right" class="linetwo">
				       							<jsp:include  page='<%="../component/dateComponent.jsp?index=1&month=fromMonth&year=fromYear&form=AptitudTesyQueryForm" %>' />
						          			</td>
										<tr>
							      		<tr>
											<jsp:include  page="../component/spaceline.jsp" />
						      			</tr>
					            		<tr class="linetwo">
				              				<th align="left" valign="middle"><%=messages.getText("driver.aptitudtest.expiryto") %></th>
				       						<td align="right" class="linetwo">
				       							<jsp:include  page='<%="../component/dateComponent.jsp?index=2&month=toMonth&year=toYear&form=AptitudTesyQueryForm" %>' />
						          			</td>
										<tr>
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
										<jsp:include page='<%="/util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=DriverReportForm&consult=true"%>' />
									</table>
								</td>
							</tr>
						</table>
					</td>
    				<td width="16">&nbsp;</td>
    				<td width="40%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<jsp:include page='<%="/util/divorg.jsp?form=DriverReportForm&consult=true" %>'/>
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
										<span class="input"><b><%=messages.getText("generic.messages.output")%></b></span>&nbsp;
									</td>
									<td align="left" class="lineone">
										<html:select property="output" styleClass="input" >
											<html:options collection="format" property="value" labelProperty="label"/>
										</html:select>							          				
									</td>
									<td align="right" class="lineone" width="90%">
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
