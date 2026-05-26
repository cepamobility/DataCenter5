<%@page import="presentation.translator.web.fleet.InsertedOdometerForm"%>
<%@page language="java"
    errorPage="../exception.jsp"
	import="business.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.datatype.tables.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.queries.*"
	import="business.datatype.*"
	import="communication.fleet.tables.*"
	import="communication.util.*"
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

		session.setAttribute("url","fleet/odometersQuery.jsp");

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
		messages=sys.getText();

		if(session.getAttribute("InsertedOdometerForm") == null) {
			InsertedOdometerForm form = new InsertedOdometerForm();
		    session.setAttribute("InsertedOdometerForm",form);
		}
		
		Collection years = new LinkedList();
		years.add(new OptionItem("","0"));
		Calendar instance = Calendar.getInstance();
		for(int i = 1998; i <= instance.get(Calendar.YEAR)+1; i++) {
			years.add(new OptionItem(String.valueOf(i),String.valueOf(i)));
		}
		session.setAttribute("years",years);
		
		Collection months = new LinkedList();
		months.add(new OptionItem("","0"));
		for(int i = 1; i <= 12; i++) { 
		    months.add(new OptionItem(messages.getText("generic.month."+String.valueOf(i)),String.valueOf(i)));
		}
		session.setAttribute("months",months);

%>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>

	
	<body bgcolor="#FFFFFF">
		<html:form action="/InsertedOdometers">
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  				<tr class="guide1">
  					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("query.insertedodometers.title")%>' />
			  	</tr>
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
				<tr>
					<td valign="top">
                    	<table width="100%" cellpadding="0" cellspacing="0" border="0">
                    		<tr>
                     			<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("query.accidettype.period")%>' />
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
<%				if(!Util.empty(request.getParameter("message")))  {
%>										<tr>
											<td colspan="7" class="red">
												<%=request.getParameter("message")%>
											</td>
										</tr>
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%				}
%>			      						<tr  class="linetwo">
			        						<th align="left"> 	
			        							<%=messages.getText("query.accidettype.from")%><span class="required">*</span>
			        						</th>
			         						<td align="right">
                								<html:select property="fromMonth" styleClass="input" >
                  									<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
			         						</td>
			         						<td align="right">
                								<html:select property="fromYear" styleClass="input" >
                  									<html:options collection="years" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
			         						</td>
			      							<td align="right" width="50">&nbsp;</td>
			        						<th align="left"> 	
			        							<%=messages.getText("query.accidettype.to")%><span class="required">*</span>
			        						</th>
			         						<td align="right">
                								<html:select property="toMonth" styleClass="input" >
                  									<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
			         						</td>
			         						<td align="right">
                								<html:select property="toYear" styleClass="input" >
                  									<html:options collection="years" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
			         						</td>
			      						</tr>
									</table>
									<jsp:include  page="../component/utilityboxbottom.jsp" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
				<tr>
                	<td valign="top">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
                    		<tr>
                     			<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("query.mileage.fleet")%>' />
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
			      						<tr  class="linetwo">
			      							<td>
			      								<html:select property="fleet" size="10" style="width:560;" styleClass="input">
<%			ListResult result = sys.getFleetReportFacade().findFleetList("",FleetData.INDIVIDUAL,0,-1,sys.getUserSessionData(),FleetData.ACTIVE);
			Iterator it = result.getElements().iterator();
			while(it.hasNext()) {
			    FleetMainAttributes fleet = (FleetMainAttributes)it.next();
%>			    									<html:option value='<%=String.valueOf(fleet.getId())%>'><%=fleet.getName()+" ("+fleet.getDescription()+")"%></html:option>
<%			}
%>				  								</html:select>
			      							</td>
			      						</tr>
			      					</table>
									<jsp:include  page="../component/utilityboxbottom.jsp" />
			      				</td>
			      			</tr>
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
									<td align="right"  class="lineone" width="100%" colspan="3">
										<html:submit styleClass="input" value='<%=messages.getText("generic.messages.open")%>' />
									</td>
								</tr>
							</table>
						<jsp:include  page="../component/utilitybox4bottom.jsp" />
					</td>
				</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
		  	</table>
		</html:form>

<%@ include  file = '../util/jsp/googleanalytics.jsp' %>
		
	</body>
<%	}	%>
</html:html>
