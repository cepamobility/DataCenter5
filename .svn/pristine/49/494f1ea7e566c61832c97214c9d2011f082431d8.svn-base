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

		session.setAttribute("url","fleet/driverLinks.jsp");

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
		messages=sys.getText();

		if(session.getAttribute("DriverLinksForm") == null) {
			InsertedOdometerForm form = new InsertedOdometerForm();
		    session.setAttribute("DriverLinksForm",form);
		}
		
%>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>

		<script language="javascript" type='text/javascript' >
		function selectFleets(message) {
			options = document.forms['DriverLinksForm'].fleets.options;
			var fleetList="";
			var separator=",";
			for(i = 0; i < options.length; i++) {
				element = options[i];
				if(element.selected){
					if(fleetList==""){
						fleetList="'"+element.value+"'";
					}else{
						fleetList="'"+element.value+"',"+fleetList;
					}	
				}	
			}
			if(fleetList.length>0){
				document.forms['DriverLinksForm'].fleet.value = fleetList;
				document.forms['DriverLinksForm'].submit();
			}else {
				alert(message);
			}
		}
	</script>
	
	<body bgcolor="#FFFFFF">
		<html:form action="/DriverLinks">
			<html:hidden property="fleet" />
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  				<tr class="guide1">
  					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("query.driverlinks.title")%>' />
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
			      								<select name="fleets" size="10" style="width:560;" class="input" multiple>
<%			ListResult result = sys.getFleetReportFacade().findFleetList("",FleetData.INDIVIDUAL,0,-1,sys.getUserSessionData(),FleetData.ACTIVE);
			Iterator it = result.getElements().iterator();
			while(it.hasNext()) {
			    FleetMainAttributes fleet = (FleetMainAttributes)it.next();
%>			    									<option value='<%=String.valueOf(fleet.getId())%>'><%=fleet.getName()+" ("+fleet.getDescription()+")"%></option>
<%			}
%>				  								</select>
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
									<%
										String button1 = "javascript:selectFleets(\"" + messages.getText("query.mileage.selectfleet") + "\");";
									%>
										<input type="button" class="input" onclick='<%=button1%>' value='<%=messages.getText("generic.messages.open")%>' />
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
