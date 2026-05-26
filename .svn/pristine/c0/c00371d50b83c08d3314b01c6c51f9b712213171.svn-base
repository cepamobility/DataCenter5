<%@page language="java"
	errorPage="../exception.jsp"
	import="business.*"
	import="business.fleet.*" 
	import="communication.language.*"
	import="communication.security.*"
	import="communication.fleet.tables.*"
	import="communication.administration.user.*"
	import="communication.administration.corporation.tables.*"
	import="communication.vehicle.tables.*"
	import="presentation.translator.web.user.*"
	import="presentation.translator.web.util.*"
	import="java.util.*"
	import="communication.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>
<%! Text messages; String pathLanguage;
	FleetReportFacade fleetReport; 
	SystemAccess sys;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData=sys.getUserSessionData();
	String check = Util.checkPermission(sys,SecurityConstants.FLEET_INSERT_DATA);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		String id=(String)request.getParameter("id");
		if(id==null){
			id=(String)session.getAttribute("id");
			session.removeAttribute("id");
		}
		session.setAttribute("url","fleet/FleetView.jsp?id="+id);
		if(id!=null) 	{	
			messages=sys.getText();
			fleetReport = sys.getFleetReportFacade();
			Object[] data = fleetReport.findFleetData(new Integer(id),sys.getUserSessionData());
			if(data != null) {
				boolean linetwo = false;
				int principalCount =  sys.getUserSessionData().getFilter().getPrincipalCount();
%>
	<LINK REL=StyleSheet HREF="./util/css/estilo.css" TYPE="text/css">
	<head>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<META http-equiv="Expires" content="-1">		
	</head>
	<script language="javascript" src='./util/js/Prepared_Parameters.js' type='text/javascript' >
	</script>
	<script>
		function setAction(form,action){
			form.action.value=action;
		}
		function confirmDelete() {
			return confirm('<%=messages.getText("fleet.confirm.deletedivision")%>');
		}
	</script>
	<body bgcolor="#FFFFFF">
		<html:form action="/DeleteFleetDivisionDetail" onsubmit="return confirmDelete();">
			<html:hidden property="fleet" value='<%=id%>' />
			<table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("web.fleet.deletedetail")%>' />
	  			</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
<%				// ExclusionDate
				if(data[3] != null) {
%> 		 		<tr>
					<td colspan="5">
						<span class="red">&nbsp;&nbsp;<%=messages.getText("generic.fleet.inactivefleet") %></span>
					</td>
		  		</tr>
				<tr>
					<td colspan="5">
						<span class="red">&nbsp;&nbsp;<%=messages.getText("generic.fleet.exclusiondate") +": "+Util.formatDate((Date)data[3])%>
						</span>				
					</td>
		  		</tr>
				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
<% 				} 
%>				
	  			<tr>
    				<td>
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.fleet.fleetdata")%>' />
						<table width="100%">
					      	<tr  class="linetwo">
					        	<th align="left"> <%=messages.getText("generic.fleet.fleetdata.name")%> </th>
					         	<td align="right"><%=data[0]%></td>
					      	</tr>
					      	<tr  class="space"><th align="right"></th><td align="right"></td></tr>
      						<tr  class="linetwo">
        						<th align="left" valign="top"><%=messages.getText("generic.fleet.fleetdata.description")%></th>
					         	<td align="right"><%=data[1]%></td>
      						</tr>
					      	<tr  class="space"><th align="right"></th><td align="right"></td></tr>
      						<tr  class="linetwo">
        						<th align="left"> <%=messages.getText("generic.fleet.fleetdata.period")%></th>
    				         	<td align="right"><%=data[2].equals(FleetData.MONTHLY) ? messages.getText("generic.fleet.fleetdata.monthly") : messages.getText("generic.fleet.fleetdata.byquarter")%></td>
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
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.fleet.fleetdata.period")%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
		              			<th align="left" valign="middle" class="linetwo"><%=messages.getText("query.messages.from")+": "%><span class="required">*</span></th>
			       				<td align="left" class="linetwo">
			       					<jsp:include  page='<%="../component/dateComponent.jsp?index=1&month=fromMonth&year=fromYear&form=DeleteFleetDivisionDetailForm" %>' />
					          	</td>
					          	<td width="40%"></td>
								<th align="right" valign="middle" class="linetwo"><%=messages.getText("query.messages.to")+": "%><span class="required">*</span></th>
			       				<td align="right" class="linetwo">
			       					<jsp:include  page='<%="../component/dateComponent.jsp?index=2&month=toMonth&year=toYear&form=DeleteFleetDivisionDetailForm" %>' />
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
									<td align="right"  class="lineone" width="100%">
										<html:submit styleClass="input"><%=messages.getText("generic.messages.delete")%></html:submit>
										<html:cancel styleClass="input"><%=messages.getText("generic.messages.cancel")%></html:cancel>
									</td>
								</tr>
							</table>
						<jsp:include  page="../component/utilitybox4bottom.jsp" />
					</td>
	  			</tr>
	        </table>
	 	</html:form>
	</body>
<%			}
		}else {
%>		<jsp:include  page='<%="/util/allowedData.jsp?message=fleet.error.divisions" %>'/>
<%		}
	}
%>
</html:html>

