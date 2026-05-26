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
	session.setAttribute("count",count);
	
	Collection format = new LinkedList();
	format.add(new OptionItem("HTML","HTML"));	    
	format.add(new OptionItem("PDF","PDF"));	    
	format.add(new OptionItem("XLS","XLS"));	    
	session.setAttribute("format",format);

	session.setAttribute("url","driver/insertedQuery.jsp");
	
	if(session.getAttribute("InsertedDriverForm") == null) {
	    GenericReportForm form = new GenericReportForm();
	    session.setAttribute("InsertedDriverForm",form);
	}
	
	Collection statusList = new LinkedList();
	statusList.add(new OptionItem(messages.getText("generic.messages.all.pl"),String.valueOf(DriverData.ALL)));
	statusList.add(new OptionItem(messages.getText("generic.messages.active"),String.valueOf(DriverData.ACTIVE)));
	statusList.add(new OptionItem(messages.getText("generic.messages.excluded"),String.valueOf(DriverData.EXCLUDED)));
	session.setAttribute("statusList",statusList);
%>
<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
</script>

<html:html>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>

	<body bgcolor="#FFFFFF">
		<html:form action="/DriverInserted">			
			<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("query.inserteddriverlog.title")%>' />
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
										<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr class="linetwo">
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
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("driver.query.period")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
			      						<tr  class="linetwo">
			        						<th align="left"> 	
			        							<%=messages.getText("driver.query.from")%>
			        						</th>
			         						<td align="right">
												<jsp:include  page='<%="../component/dateComponent.jsp?index=1&day=fromDate&month=fromMonth&year=fromYear&form=InsertedDriverForm" %>' />			         						
											</td>
			      						</tr>
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr  class="linetwo">
			        						<th align="left"> 	
			        							<%=messages.getText("driver.query.to")%>
			        						</th>
			         						<td align="right">
												<jsp:include  page='<%="../component/dateComponent.jsp?index=2&day=toDate&month=toMonth&year=toYear&form=InsertedDriverForm" %>' />			         						
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
										<jsp:include page='<%="/util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=InsertedDriverForm&consult=true"%>' />
									</table>
								</td>
							</tr>
						</table>
					</td>
    				<td width="2%">&nbsp;</td>
    				<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<jsp:include page='<%="/util/divorg.jsp?form=InsertedDriverForm&consult=true" %>'/>
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
