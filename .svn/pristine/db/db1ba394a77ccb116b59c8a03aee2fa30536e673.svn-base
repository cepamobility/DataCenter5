<%@page language="java"
	import="business.*"
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.datatype.*"
	import="communication.datatype.tables.*"
	import="communication.vehicle.tables.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.vehicle.*"
	import="communication.driver.tables.*"
	import="communication.administration.corporation.tables.*"
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

	dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
	dispatcher.include(request,response);

	messages = sys.getText();

	ComplaintReportForm form = new ComplaintReportForm();
	session.setAttribute("ComplaintReportForm",form);
	Collection format = new LinkedList();
	format.add(new OptionItem("HTML","HTML"));	    
	format.add(new OptionItem("PDF","PDF"));	    
	format.add(new OptionItem("XLS","XLS"));	    
	session.setAttribute("format",format);
	
	
//	Datos Opcionales de Vehiculos (Camiones Brasil), en particular para KLABIN
	boolean hasOptionalData = false;
	if (sys.getUserSessionData().getFilter().getCorporationId().equals(new Integer(CorporationData.KLABIN))){
		hasOptionalData = true;
	}
%>

<script>
	function changeCombo(radio,combo) {
		var el = document.forms[2].elements[radio];
		document.forms[2].elements[combo].disabled = !el[2].checked;
	}
	
	
	
</script>

<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
</script>
<script language="javascript" src='/datacenter/util/js/Prepared_Parameters.js' type='text/javascript' >
</script>

<html:html>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>

	<body bgcolor="#FFFFFF">
		<html:form action="/ComplaintReport">			
			<table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("vehicle.complaint.report")%>' />
  				</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
	      		<tr>
    	    		<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.vehicle.complaintdata")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("complaint.report.from")+":"%> </th>
							        		<td align="right" class="linetwo">
			       								<jsp:include  page='<%="../component/dateComponent.jsp?index=1&day=fromDate&month=fromMonth&year=fromYear&form=ComplaintReportForm" %>' />
							        		</td>
						         		</tr>
		    							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("complaint.report.to")+":"%> </th>
							        		<td align="right" class="linetwo">
			       								<jsp:include  page='<%="../component/dateComponent.jsp?index=2&day=toDate&month=toMonth&year=toYear&form=ComplaintReportForm" %>' />
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
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.vehicle.vehicledata")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<th align="left">
												<span class="linetwo">
													<%=messages.getText("generic.vehicle.vehicledata.registrationNumber")%></span>
											</th>
								         	<td align="right">
												<html:text property="plate" style="width:155;" styleClass="input"/>													
											</td>
										</tr>  
		    							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("complaint.report.status")+":"%> </th>
							        		<td align="right" class="linetwo">
			       								<html:select property="status" styleClass="input">
			       									<html:option value='<%=String.valueOf(VehicleData.ALL)%>'><%=messages.getText("generic.messages.all.pl")%></html:option>
			       									<html:option value='<%=String.valueOf(VehicleData.ACTIVE)%>'><%=messages.getText("generic.messages.active")%></html:option>
			       									<html:option value='<%=String.valueOf(VehicleData.EXCLUDED)%>'><%=messages.getText("generic.messages.excluded")%></html:option>
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
				    			<td valign="top">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<jsp:include page='<%="/util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=VehicleReportForm&consult=true"%>' />
									</table>
								</td>
							</tr>
						</table>
					</td>
    				<td width="2%">&nbsp;</td>
    				<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<jsp:include page='<%="/util/divorg.jsp?form=VehicleReportForm&consult=true" %>'/>
						</table>
					</td>
  				</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
				<tr>
					<td align="left" class="texts" colspan="5">
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
									<td align="right"  class="lineone" width="90%">
										<input type="hidden" name="checkboxParamList" />
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
