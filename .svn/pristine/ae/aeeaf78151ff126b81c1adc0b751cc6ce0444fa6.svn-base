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

<html:html>
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
		
		Collection order1 = new LinkedList();	    
		order1.add(new OptionItem(messages.getText("generic.messages.select"),"0"));	    
		order1.add(new OptionItem(messages.getText("Contratista"),"1"));	    
		order1.add(new OptionItem(messages.getText("generic.vehicle.vehicledata.vehicletype"),"2"));	    
		order1.add(new OptionItem(messages.getText("generic.vehicle.vehicledata.registrationNumber"),"3"));	    

		Collection order2 = new LinkedList();	    
		order2.add(new OptionItem(messages.getText("generic.messages.select"),"0"));	    

		Collection order3 = new LinkedList();	    
		order3.add(new OptionItem(messages.getText("generic.messages.select"),"0"));	    

		session.setAttribute("order1",order1);
		session.setAttribute("order2",order2);
		session.setAttribute("order3",order3);

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
		<html:form action="/ReportVehiclePursuitRouteFormList" >
			<select name="contract1" style="display:none; ">
				<option value="0"><%=messages.getText("generic.messages.select")%></option>
				<option value="2"><%=messages.getText("generic.vehicle.vehicledata.vehicletype")%></option>
				<option value="3"><%=messages.getText("generic.vehicle.vehicledata.registrationNumber")%></option>
			</select>
			<select name="type1" style="display:none; ">
				<option value="0"><%=messages.getText("generic.messages.select")%></option>
				<option value="1"><%=messages.getText("Contratista")%></option>
				<option value="3"><%=messages.getText("generic.vehicle.vehicledata.registrationNumber")%></option>
			</select>
			<select name="all2" style="display:none; ">
				<option value="0"><%=messages.getText("generic.messages.select")%></option>
				<option value="3"><%=messages.getText("generic.vehicle.vehicledata.registrationNumber")%></option>
			</select>
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  				<tr class="guide1">
  					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("vehicle.seguimientoenruta.query")%>' />
			  	</tr>
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
				<tr>
					<td width="50%" valign="top">
                    	<table width="100%" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
                       					<tr>
                         					<td valign="top"> <jsp:include page='<%="/util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=ReportCheckListForm&consult=true&default=generic.messages.all.pl"%>' /></td>
                       					</tr>
	                     			</table>					  
								</td>
							</tr>		
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
									</table>
									<jsp:include  page="../component/utilityboxbottom.jsp" />
								</td>
							</tr>
							<tr>
								<jsp:include  page="../component/spaceline.jsp" />
							</tr>
						</table>
					</td>
	                <td width="16">&nbsp;</td>
                	<td width="50%" valign="top">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
                    		<jsp:include page='<%="/util/divorg.jsp?form=ReportVehiclePursuitRouteForm&consult=true&default=generic.messages.all.pl"%>' />
	                     </table>
    	             </td>
				</tr>
	  			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
				<tr>
					<td>
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("query.driver.orderby")%>' />
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
							<tr class="linetwo">
        						<th align="left" >1. </th>
        						<td align="right" colspan="2">
<%		String javascript = "javascript:change1(document.forms['ReportVehiclePursuitRouteForm']);";		%>        						
            						<html:select property="order1" styleClass="input" style="width:155;" onchange="<%=javascript%>">
              							<html:options collection="order1" property="value" labelProperty="label" styleClass="input"/>
            						</html:select>
        						</td>
        					</tr>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr class="linetwo">
         						<th align="left" >2. </th>
         						<td align="right" colspan="2">
									<%	javascript = "javascript:change2(document.forms['ReportVehiclePursuitRouteForm']);";		%>        						
             						<html:select property="order2" styleClass="input" style="width:155;"  onchange='<%=javascript%>' disabled="true">
               							<html:options collection="order2" property="value" labelProperty="label" styleClass="input" />
             						</html:select>
         						</td>
         					</tr>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr class="linetwo">
         						<th align="left" >3. </th>
         						<td align="right" colspan="2">
             						<html:select property="order3" styleClass="input" style="width:155;"  disabled="true">
               							<html:options collection="order3" property="value" labelProperty="label" styleClass="input"/>
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
					<td align="left" class="texts" colspan="3">
						<jsp:include  page="../component/utilitybox4top.jsp" />
							<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
								<tr class="lineone">
									<td align="left" class="lineone" width="49%">
										<table width="100%" border="0" cellpadding="2" cellspacing="2">
											<tr class="lineone">
												<td>
													<html:select property="outputType" styleClass="input" >
														<html:option value="0"><%=messages.getText("generic.checklist.reportType.resumed")%></html:option>
														<html:option value="1"><%=messages.getText("generic.vehicle.reportType.detailed")%></html:option>
													</html:select>
												</td>
											</tr>					
				      						<tr class="space"><td align="right">&nbsp;</td></tr>
											<tr class="lineone">
												<td>
													<html:checkbox property="acumulated" styleClass="input" >
														<span class="input"><%=messages.getText("query.pursuit.acumulate")%></span>
													</html:checkbox>
												</td>
											</tr>					
										</table>
									</td>
									<td align="right"  class="lineone" width="2%">&nbsp;</td>
									<td align="right" class="lineone" width="49%">
										<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">																      					
											<tr class="lineone">
												<td align="right">
													<html:select property="output" styleClass="input" >
														<html:options collection="format" property="value" labelProperty="label"/>
													</html:select>					
												</td>
											</tr>
				      						<tr class="lineone"><td align="right">&nbsp;</td></tr>
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
