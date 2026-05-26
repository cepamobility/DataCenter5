<%@page language="java"
    errorPage="../exception.jsp"
	import="business.*"
	import="communication.language.*"
	import="communication.security.*"
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

		session.setAttribute("url","queries/reportKmLtr.jsp");

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
		messages=sys.getText();

		Collection format = new LinkedList();
		format.add(new OptionItem("HTML","HTML"));	    
		format.add(new OptionItem("PDF","PDF"));	    
		format.add(new OptionItem("XLS","XLS"));	    
		session.setAttribute("format",format);

		if(session.getAttribute("ReportConsumedFuelForm") == null) {
		    ReportConsumedFuelForm form = new ReportConsumedFuelForm();
		    session.setAttribute("ReportConsumedFuelForm",form);
		}
		
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

	<body bgcolor="#FFFFFF">
		<html:form action="/ReportKmLtr">
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  				<tr class="guide1">
  					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("fleet.query.kmltr.title")%>' />
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
                         					<td valign="top"> <jsp:include page='<%="/util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=ReportConsumedFuelForm&consult=true&default=generic.messages.all.pl"%>' /></td>
                       					</tr>
	                     			</table>					  
								</td>
							</tr>		
							<tr>
								<jsp:include  page="../component/spaceline.jsp" />
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
						</table>
					</td>
	                <td width="16">&nbsp;</td>
                	<td width="50%" valign="top">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
                    		<jsp:include page='<%="/util/divorg.jsp?form=ReportConsumedFuelForm&consult=true&default=generic.messages.all.pl"  %>'/>
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
									<td align="right"  class="lineone" width="90%">
										<html:submit styleClass="input"><%=messages.getText("generic.messages.open")%></html:submit>
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
