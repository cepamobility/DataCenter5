<%@page language="java"
    errorPage="../exception.jsp"
	import="business.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.corporation.tables.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.queries.*"
	import="java.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>


<%@page import="communication.administration.RoleData"%><html:html>
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

	    int corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();

	    session.setAttribute("url","queries/report_apmm.jsp");

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
		messages=sys.getText();

		Collection format = new LinkedList();
		format.add(new OptionItem("HTML","HTML"));	    
		format.add(new OptionItem("PDF","PDF"));	    
		format.add(new OptionItem("XLS","XLS"));	    
		session.setAttribute("format",format);

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
		
%>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>

	<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
	</script>
	
	<script language="javascript" type='text/javascript' >
		function change1(form){
			value = form.elements['order1'].value;
			childCombo = form.elements['order2']
			if(value == 1) {
				var list = form.elements['contract1'];
				childCombo.options.length=list.options.length;
				for(i=0; i < list.length; i++) {
					childCombo.options[i].value = list.options[i].value;
					childCombo.options[i].text = list.options[i].text;
				}
				childCombo.options.selectedIndex = 0;
				childCombo.disabled = false;
			}
			if(value == 2) {
				var list = form.elements['type1'];
				childCombo.options.length=list.options.length;
				for(i=0; i < list.length; i++) {
					childCombo.options[i].value = list.options[i].value;
					childCombo.options[i].text = list.options[i].text;
				}
				childCombo.options.selectedIndex = 0;
				childCombo.disabled = false;
			}
			if(value == 0 || value == 3) {
				childCombo.options.length=1;
				childCombo.disabled = true;
			}
			childCombo2 = form.elements['order3'];
			childCombo2.options.length=1;
			childCombo2.disabled = true;
		}
		function change2(form){
			value = form.elements['order2'].value;
			childCombo = form.elements['order3']
			if(value == 1 || value == 2) {
				var list = form.elements['all2'];
				childCombo.options.length=list.options.length;
				for(i=0; i < list.length; i++) {
					childCombo.options[i].value = list.options[i].value;
					childCombo.options[i].text = list.options[i].text;
				}
				childCombo.disabled = false;
			}else {
				childCombo.options.length=1;
				childCombo.disabled = true;
			}
		}
	</script>

	<body bgcolor="#FFFFFF">
		<html:form action="/ReportVehicleUnconformities" >
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  				<tr class="guide1">
  					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("vehicle.unconformity.query")%>' />
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
			      						<tr  class="linetwo">
			        						<th align="left"> 	
			        							<%=messages.getText("query.apmm.from")%><span class="required">*</span>
			        						</th>
			         						<td align="right">
                								<html:select property="frommonth" styleClass="input" >
                  									<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
			         						</td>
			         						<td align="right">
                								<html:select property="fromyear" styleClass="input" >
                  									<html:options collection="years" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
			         						</td>
			      						</tr>
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr  class="linetwo">
			        						<th align="left"> 	
			        							<%=messages.getText("query.apmm.to")%><span class="required">*</span>
			        						</th>
			         						<td align="right">
                								<html:select property="tomonth" styleClass="input" >
                  									<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
			         						</td>
			         						<td align="right">
                								<html:select property="toyear" styleClass="input" >
                  									<html:options collection="years" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
			         						</td>
			      						</tr>
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr  class="linetwo">
			        						<th align="left"> 	
			        							<%=messages.getText("generic.messages.type")%><span class="required">*</span>
				        					</th>
			         						<td align="right" colspan="2">
                								<html:select property="type" styleClass="input" >
                  									<html:option value="0"><%=messages.getText("generic.vehicle.reportType.resumed")%></html:option>
                  									<html:option value="1"><%=messages.getText("generic.vehicle.reportType.detailed")%></html:option>
                								</html:select>
			         						</td>
				        				</tr>
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
									</table>
									<jsp:include  page="../component/utilityboxbottom.jsp" />
								</td>
							</tr>
						</table>
					</td>
	                <td width="16">&nbsp;</td>
                	<td width="50%" valign="top">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
							<jsp:include page='<%="/util/divorgUnconformity.jsp?form=ReportUnconformityForm&consult=true&default=generic.messages.all.pl"%>' />
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
									<td align="left" class="lineone" width="50%">
										<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
																      					
											<tr class="lineone">
												<td>
													<html:select property="output" styleClass="input" >
														<html:options collection="format" property="value" labelProperty="label"/>
													</html:select>					
												</td>
												<td>&nbsp;</td>
												<td align="left">
													<div id="labelDecimals" style="display: none;" class="input" ><%=messages.getText("query.apmm.showdecimals")%></div>												
												</td>
											</tr>
										</table>	
									</td>
									<td align="right"  class="lineone" width="50%">
										<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
											
											<tr class="lineone">
												<td align="right">									
													<html:submit styleClass="input"><%=messages.getText("generic.messages.open")%></html:submit>
												</td>
											</tr>
										</table>
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
<%	}	%>
</html:html>
