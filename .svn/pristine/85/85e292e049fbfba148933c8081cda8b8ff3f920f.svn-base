<%@page language="java"
    errorPage="../exception.jsp"
	import="business.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.datatype.*"
	import="communication.datatype.tables.*"
	import="communication.administration.corporation.tables.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.queries.*"
	import="business.datatype.*"
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
	String type;
	String formEnding;
%>

<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.ADMINISTRATOR);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {        
		session.setAttribute("url","corporation/configuration.jsp");

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
		messages=sys.getText();

		String corpName = sys.getCorporationReportFacade().findCorporationName(sys.getUserSessionData().getFilter().getCorporationId(),sys.getUserSessionData());
		
		DataTypeReportFacade dataTypeReport = sys.getDataTypeReportFacade();
	    
%>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>

	<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
	</script>

	<script>
	
	function onSelectFrom(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_month = document.getElementById("monthIdFrom");
  		var input_year = document.getElementById("yearIdFrom");
  		var date3 = new Date(time);
  		input_month.value = parseInt(date3.print("%m"),10);
  		input_year.value= parseInt(date3.print("%Y"),10);
	}
	function onSelectTo(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_month = document.getElementById("monthIdTo");
  		var input_year = document.getElementById("yearIdTo");
  		var date3 = new Date(time);
  		input_month.value = parseInt(date3.print("%m"),10);
  		input_year.value= parseInt(date3.print("%Y"),10);
	}
	function changeSelection2(formName,value,selection) {
		var elements=document.forms[formName].elements;
		for(i = 0; i < elements.length; i++) {
			var element=elements[i];
			if(element.name.indexOf(value) > -1) {
				// alert(selection);
				if(selection == 1) {
					element.checked = true;
				}else if(selection == 2) {
					element.checked = false;
				}else {
					element.checked = !element.checked;
				}
			}
		}
	}
	function changeSelection(formName,value1,value2,selection) {
		var elements=document.forms[formName].elements;
		for(i = 0; i < elements.length; i++) {
			var element=elements[i];
			if((element.name.indexOf(value1) > -1) || (element.name.indexOf(value2) > -1)){
				if(selection == 1) {
					element.checked = true;
				}else if(selection == 2) {
					element.checked = false;
				}else {
					element.checked = !element.checked;
				}
			}
		}
	}
	</script>
	
	<body bgcolor="#FFFFFF">
		<html:form action='<%="/Configuration"%>' >
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  				<tr class="guide1">
  					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.corporation.configuration")%>' />
			  	</tr>
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
		      	<tr>
		      		<td>
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.corporation.data")%>' />
						<table align='center' width='100%' cellpadding="0" cellspacing="0">
					  		<tr>
								<td class="linetwo">
								<b><%=messages.getText("generic.corporation.corporationdata.name")+" :"%></b>
		   						<%=corpName %>              
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
					<td colspan="3" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("query.accidenttype.accidents")%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
<%				Collection accidents = dataTypeReport.findAll(DataTypeConstants.ALL_ACCIDENT_CLASIFICATION,sys.getUserSessionData());
				Collection corporationAccidents = dataTypeReport.findAll(DataTypeConstants.ACCIDENT_CLASIFICATION,sys.getUserSessionData());
				if(accidents != null && accidents.size() > 0) {
					
				    Iterator it = accidents.iterator();
				    while(it.hasNext()) {
				        AccidentClasificationDataType accident = (AccidentClasificationDataType)it.next();
				        String checked = (corporationAccidents.contains(accident)) ? "checked" : "";
%>							<tr>
								<td width="50%" class="linetwo">
									<input type="checkbox" name='<%=accident.getResourceBundleId()%>' <%=checked%>/><%=messages.getText(accident.getResourceBundleId())%>
								</td>
<%					    if(it.hasNext()) {
    						accident = (AccidentClasificationDataType)it.next();
					        checked = (corporationAccidents.contains(accident)) ? "checked" : "";
%>								<td width="50%" class="linetwo">
									<input type="checkbox" name='<%=accident.getResourceBundleId()%>'  <%=checked%>/><%=messages.getText(accident.getResourceBundleId())%>
								</td>
<%    					}
%>							</tr>
<%					}
				}
				formEnding = "ReportAccidentTypeForm";
%>							<tr>
								<td colspan="3" align="right">
									<input type="button" value='<%=messages.getText("generic.messages.all.pl")%>' onclick='<%="changeSelection('"+formEnding+"','datatype.accidentclasification.','datatype.collisionclasification.',1)"%>' class="input" />
									<input type="button" value='<%=messages.getText("generic.messages.none")%>' onclick='<%="changeSelection('"+formEnding+"','datatype.accidentclasification.','datatype.collisionclasification.',2)"%>' class="input" />
									<input type="button" value='<%=messages.getText("generic.messages.invertselection")%>' onclick='<%="changeSelection('"+formEnding+"','datatype.accidentclasification.','datatype.collisionclasification.',3)"%>' class="input" />
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
					<td colspan="3" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("query.accidenttype.incidents")%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
<%				Collection incidents = dataTypeReport.findAll(communication.datatype.DataTypeConstants.ALL_INCIDENT_CLASIFICATION,sys.getUserSessionData());
				Collection corporationIncidents = dataTypeReport.findAll(DataTypeConstants.INCIDENT_CLASIFICATION,sys.getUserSessionData());
				if(incidents != null && incidents.size() > 0) {
				
				    Iterator it = incidents.iterator();
				    while(it.hasNext()) {
				        IncidentClasificationDataType incident = (IncidentClasificationDataType)it.next();
				        String checked = (corporationIncidents.contains(incident)) ? "checked" : "";
%>							<tr>
								<td width="50%" class="linetwo">
									<input type="checkbox" name='<%=incident.getResourceBundleId()%>'  <%=checked%> /><%=messages.getText(incident.getResourceBundleId())%>
								</td>
<%					    if(it.hasNext()) {
    						incident = (IncidentClasificationDataType)it.next();
    						checked = (corporationIncidents.contains(incident)) ? "checked" : "";
%>								<td width="50%" class="linetwo">
									<input type="checkbox" name='<%=incident.getResourceBundleId()%>'  <%=checked%> /><%=messages.getText(incident.getResourceBundleId())%>
								</td>
<%    					}
%>							</tr>
<%					}
				}
				formEnding = "ReportAccidentTypeForm";
%>							<tr>
								<td colspan="3" align="right">
									<input type="button" value='<%=messages.getText("generic.messages.all.pl")%>' onclick='<%="changeSelection2('"+formEnding+"','datatype.incidentclasification.',1)"%>' class="input" />
									<input type="button" value='<%=messages.getText("generic.messages.none")%>' onclick='<%="changeSelection2('"+formEnding+"','datatype.incidentclasification.',2)"%>' class="input" />
									<input type="button" value='<%=messages.getText("generic.messages.invertselection")%>' onclick='<%="changeSelection2('"+formEnding+"','datatype.incidentclasification.',3)"%>' class="input" />
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
					<td colspan="3" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("query.accidenttype.fines")%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
<%				Collection fines = dataTypeReport.findAll(communication.datatype.DataTypeConstants.ALL_FINE_QUALIFY,sys.getUserSessionData());
				Collection corporationFines = dataTypeReport.findAll(DataTypeConstants.FINE_QUALIFY,sys.getUserSessionData());
				if(fines != null && fines.size() > 0) {
				    Iterator it = fines.iterator();
				    while(it.hasNext()) {
				        FineQualifyDataType fine = (FineQualifyDataType)it.next();
				        String checked = (corporationFines.contains(fine)) ? "checked" : "";
%>							<tr>
								<td width="50%" class="linetwo">
									<input type="checkbox" name='<%=fine.getResourceBundleId()%>'  <%=checked%> /><%=messages.getText(fine.getResourceBundleId())%>
								</td>
<%					    if(it.hasNext()) {
    						fine = (FineQualifyDataType)it.next();
    						checked = (corporationFines.contains(fine)) ? "checked" : "";
%>								<td width="50%" class="linetwo">
									<input type="checkbox" name='<%=fine.getResourceBundleId()%>'  <%=checked%> /><%=messages.getText(fine.getResourceBundleId())%>
								</td>
<%    					}
%>							</tr>
<%					}
				}
				formEnding = "ReportAccidentTypeForm";
%>							<tr>
								<td colspan="3" align="right">
									<input type="button" value='<%=messages.getText("generic.messages.all.pl")%>' onclick='<%="changeSelection2('"+formEnding+"','datatype.finequalify.',1)"%>' class="input" />
									<input type="button" value='<%=messages.getText("generic.messages.none")%>' onclick='<%="changeSelection2('"+formEnding+"','datatype.finequalify.',2)"%>' class="input" />
									<input type="button" value='<%=messages.getText("generic.messages.invertselection")%>' onclick='<%="changeSelection2('"+formEnding+"','datatype.finequalify.',3)"%>' class="input" />
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
  				  	<td height="18" colspan="3" valign="top">
          				<div align="right">
							<html:submit styleClass="input"><%=messages.getText("generic.messages.open")%></html:submit>
				  		</div>          
				  	</td>
			  	</tr>
		  	</table>
		</html:form>

<%@ include  file = '../util/jsp/googleanalytics.jsp' %>
		
	</body>
<%	}	%>
</html:html>
