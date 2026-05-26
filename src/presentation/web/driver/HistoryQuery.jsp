<%@page language="java"
	import="business.*"
	import="communication.language.*"
	import="communication.util.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.driver.*"
	import="communication.driver.tables.*"
	import="communication.security.*"
	import="communication.administration.corporation.tables.CorporationData"
    errorPage="../exception.jsp"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>
<%!
	Text messages; 
	SystemAccess sys; 
%> 
<%
 	sys = (SystemAccess)session.getAttribute("SystemAccess");
  	
  	String check = null;
      check = Util.checkPermission(sys,SecurityConstants.QUERY);
  	if(check!=null) {
  		response.sendRedirect(request.getContextPath()+check);
  	}else{
  		int corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();
  		messages=sys.getText();

  		RequestDispatcher dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
  		dispatcher.include(request,response);

  		Collection format = new LinkedList();
  		format.add(new OptionItem("HTML","HTML"));	    
  		format.add(new OptionItem("PDF","PDF"));	    
  		format.add(new OptionItem("XLS","XLS"));	    
  		session.setAttribute("format",format);
  	
  		session.setAttribute("url","driver/HistoryQuery.jsp");
  		
  		if(session.getAttribute("DriverHistoryQueryForm") == null) {
  		    DriverHistoryQueryForm form = new DriverHistoryQueryForm();
  		    session.setAttribute("DriverHistoryQueryForm",form);
  		}
  		
  		Collection years = new LinkedList();
  	    years.add(new OptionItem("",""));
  		for(int i = 1998; i <= 2050; i++) {
  	years.add(new OptionItem(String.valueOf(i),String.valueOf(i)));
  		}
  		session.setAttribute("years",years);
  		
  		Collection months = new LinkedList();
  	    months.add(new OptionItem("",""));
  		for(int i = 1; i <= 12; i++) { 
  		    months.add(new OptionItem(messages.getText("generic.month."+String.valueOf(i)),String.valueOf(i)));
  		}
  		session.setAttribute("months",months);
  		String status = String.valueOf(DriverData.ACTIVE);
 %>
		<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
		</script>

		<html:form action="/DriverHistoryQuery">			
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("query.historydriver.title")%>' />
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
										<tr align="left">
											<th>
												<span class="linetwo"><%=messages.getText("generic.driver.driverdata.firstname")%></span>
											</th>
											<td>
												<html:text property="firstname" size="35" styleClass="input" />    
											</td>
										</tr>  
										<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						      			<tr align="left">
											<th>
												<span class="linetwo"><%=messages.getText("generic.driver.driverdata.lastname")%></span>
											</th>
											<td>
												<html:text property="lastname" size="35" styleClass="input" />    
											</td>
										</tr> 
										<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						      			<tr>
											<td colspan="2" align="left" class="texts">
												<table width="100%">
													<tr>
														<td width="10%">														
														</td>																							
								                        <td valign="middle"> 
								                          <input type="radio" class="input2" name="status" value='<%=DriverData.ACTIVE%>' <%=(Integer.parseInt(status) == DriverData.ACTIVE) ? "checked" : ""%>><span class="input2"><%=messages.getText("generic.messages.active")%></span>
														</td>													
								                        <td valign="middle"> 
								                          <input type="radio" class="input2" name="status" value='<%=DriverData.EXCLUDED%>' <%=(Integer.parseInt(status) == DriverData.EXCLUDED) ? "checked" : ""%>><span class="input2"><%=messages.getText("generic.messages.excluded")%></span>
														</td>
								                        <td valign="middle"> 
								                          <input type="radio" class="input2" name="status" value='<%=DriverData.ALL%>' <%=(Integer.parseInt(status) == DriverData.ALL) ? "checked" : ""%>><span class="input2"><%=messages.getText("generic.messages.all.pl")%></span>
														</td>
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
<%		if(sys.isCorporation(new int[] {CorporationData.JJ,CorporationData.NALCO,CorporationData.MONTES_DE_PLATA})) {
%>									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<jsp:include page='<%="/util/divgeo2.jsp?form=DriverHistoryQueryForm&consult=true" %>'/>
									</table>
<%		}else {
%>									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.divgeo")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr align="left">
											<th>
												<span class="linetwo"><%=messages.getText("corporation.monsanto.geolabel1")%></span>
											</th>
											<td>
												<span class="linetwo">BRA</span>
											</td>
										</tr>  
							      		<tr>
											<jsp:include  page="../component/spaceline.jsp" />
						      			</tr>
										<tr align="left">
											<th>
												<span class="linetwo"><%=messages.getText("corporation.country")%></span>
											</th>
											<td>
												<span class="linetwo"><%=messages.getText("datatype.country.br")%></span>
												<input type="hidden" name="country2" value="32">
												<input type="hidden" name="country1" value='<%=DriverHistoryQueryForm.getCountry1(sys.getUserSessionData().getFilter().getCorporationId().intValue())%>'>
											</td>
										</tr>  
									</table>	
									<jsp:include  page="../component/utilityboxbottom.jsp" />
<%		}
%>								</td>								
							</tr>
						</table>
					</td>
    				<td width="2%">&nbsp;</td>
    				<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<jsp:include page='<%="/util/divorg.jsp?form=DriverHistoryQueryForm&consult=true" %>'/>
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
<% 
	}  // Fin del control de seguridad y permisos.
%>