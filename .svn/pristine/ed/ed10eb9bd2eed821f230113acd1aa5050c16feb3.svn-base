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
	Collection count = new LinkedList();
	for(int i = 1; i < 6; i++) {
		count.add(new OptionItem(String.valueOf(i),String.valueOf(i)));	    
	}
	session.setAttribute("count",count);

	String action = "/Vehiclereport";
	String formName = "VehicleReportForm";
	String type = "";
	if(sys.getCorporationId() == CorporationData.ALLERGAN) {
		action = "/Vehiclemultireport";
		formName = "VehicleMultiReportForm";
		session.setAttribute("VehicleMultiReportForm",new VehicleMultiReportForm());
		type = "All";
	}else {
		session.setAttribute("VehicleReportForm",new VehicleReportForm());
	}

	Collection format = new LinkedList();
	format.add(new OptionItem("HTML","HTML"));	    
	format.add(new OptionItem("PDF","PDF"));	    
	format.add(new OptionItem("XLS","XLS"));	    
	session.setAttribute("format",format);
	
	Collection reportType = new LinkedList();
	reportType.add(new OptionItem(messages.getText("generic.vehicle.reportType.resumed"),"Resumido"));	    
	reportType.add(new OptionItem(messages.getText("generic.vehicle.reportType.detailed"),"Detallado"));	
	
	session.setAttribute("reportType",reportType);	
	
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
		<html:form action="<%=action%>">			
			<table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.vehicle.consulting")%>' />
  				</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
	      		<tr>
    	    		<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.vehicle.vehicledata")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
									
									<% if (sys.getUserSessionData().isForest()){ %>
										<tr>
											<td width='100%' align="left" colspan="2"><span class="linetwo">
												<html:checkbox property="plataformCheck">
													<%=messages.getText("vehicle.data.trailer")%>
												</html:checkbox></span>
											</td>
										</tr>
										<tr>
											<td width='100%' align="left" colspan="2"><span class="linetwo">
												<html:checkbox property="vehicleCheck">
													<%=messages.getText("generic.vehicle.vehicle")%>
												</html:checkbox></span>
											</td>
										</tr>
									<% }%>
									
										<tr>
											<th align="left">
												<span class="linetwo"><%=messages.getText("generic.vehicle.vehicledata.vehicletype")%></span>
											</th>
											<td align="right">
<%			
			DataTypeReportFacade dtReportFacade = sys.getDataTypeReportFacade();		
			session.setAttribute("vt",new DataTypeUtil(sys).getVehicleType(false,true,false));
%>
                								<html:select property="type" styleClass="input" style="width:155;">
                  									<html:options collection="vt" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
											</td>
										</tr>  
		    							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
											<th align="left">
												<span class="linetwo" ><%=messages.getText("generic.vehicle.vehicledata.trade")%></span>
											</th>
											<td align="right">
<%			Collection trade = new LinkedList();
   			trade.add(new OptionItem(messages.getText("generic.messages.select"),""));                
			Iterator iter = dtReportFacade.findAll(DataTypeConstants.TYPETRADE,sys.getUserSessionData()).iterator();
			while(iter.hasNext()) {
				String value = ((TypeTradeDataType)iter.next()).getResourceBundleId();
				trade.add(new OptionItem(value,value));				
			}
			session.setAttribute("trade",trade);
%>
                								<html:select property="trade" styleClass="input" style="width:155;">
                  									<html:options collection="trade" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
											</td>
										</tr>
		    							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
											<th align="left">
												<span class="linetwo" ><%=messages.getText("generic.vehicle.vehicledata.model")%></span>
											</th>
								         	<td align="right">
												<html:text property="model" style="width:155;" styleClass="input"/>													
											</td>
										</tr>
		    							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
											<th align="left">
												<span class="linetwo">
													<%=messages.getText("generic.vehicle.vehicledata.matricula")%></span>
											</th>
								         	<td align="right">
												<html:text property="plate" style="width:155;" styleClass="input"/>													
											</td>
										</tr>  
		    							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
											<th align="left">
												<span class="linetwo"><%=messages.getText("generic.vehicle.vehicledata.year")%></span>
											</th>
											<td align="right">
                								<html:text property="year" style="width:35;" styleClass="input"/>
            								</td>
            							</tr>
		    							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
											<td colspan="2" align="left" class="linetwo">
												<table width="100%">
													<tr>
														<th align="left">
															<html:radio styleClass="linetwo" property="use" value='<%="persistence.datatype.typeuse.institutional"%>'><span class="linetwo"><%=messages.getText("persistence.datatype.typeuse.institutional")%></span></html:radio>
														</th>
														<th align="left">
															<html:radio styleClass="linetwo" property="use" value='<%="persistence.datatype.typeuse.individual"%>'><span class="linetwo"><%=messages.getText("persistence.datatype.typeuse.individual")%></span></html:radio>
														</th>
														<th align="left">
															<html:radio styleClass="linetwo" property="use" value=""><span class="linetwo"><%=messages.getText("generic.messages.all.pl")%></span></html:radio>
														</th>
													</tr>
												</table>
											</td>
										</tr>
		    							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
											<th align="left">
												<span class="linetwo"><%=messages.getText("generic.vehicle.vehicledata.fuel")%></span>
											</th>
											<td align="right">
<%			Collection fuel = new LinkedList();
            fuel.add(new OptionItem(messages.getText("generic.messages.select"),""));    
			Iterator iter4 = dtReportFacade.findAll(DataTypeConstants.TYPEFUEL,sys.getUserSessionData()).iterator();
			while(iter4.hasNext()) {
				String value = ((TypeFuelDataType)iter4.next()).getResourceBundleId();
				fuel.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
			}
			session.setAttribute("f",fuel);
%>
                								<html:select property="fuel" styleClass="input" style="width:155;">
                  									<html:options collection="f" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
            								</td>
            							</tr>
		    							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
											<th align="left">
												<span class="linetwo">
													<%=messages.getText("generic.vehicle.vehicledata.costCenter")%></span>
											</th>
								         	<td align="right">
												<html:text property="centerCost" style="width:155;" styleClass="input"/>													
											</td>
										</tr>
		    							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
											<td colspan="2" align="left" class="linetwo">
												<table width="100%">
													<tr>
														<th align="left">
															<html:radio styleClass="linetwo" property="status" value='<%=String.valueOf(VehicleData.ACTIVE)%>'><span class="linetwo"><%=messages.getText("generic.messages.active")%></span></html:radio>
														</th>
														<th align="left">
															<html:radio styleClass="linetwo" property="status" value='<%=String.valueOf(VehicleData.EXCLUDED)%>'><span class="linetwo"><%=messages.getText("generic.messages.excluded")%></span></html:radio>
														</th>
														<th align="left">
															<html:radio styleClass="linetwo" property="status" value='<%=String.valueOf(VehicleData.ALL)%>'><span class="linetwo"><%=messages.getText("generic.messages.all.pl")%></span></html:radio>
														</th>
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
				    			<td valign="top">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<jsp:include page='<%="/util/divgeo"+String.valueOf(sys.getUserSessionData().getFilter().getGeoLevelCount())+type+".jsp?form="+formName+"&consult=true"%>' />
									</table>
								</td>
							</tr>
						</table>
					</td>
    				<td width="2%">&nbsp;</td>
    				<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<jsp:include page='<%="/util/divorg"+type+".jsp?form="+formName+"&consult=true" %>'/>
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
									<td align="left" class="lineone" width="90%">&nbsp;</td>
								</tr>
								<tr class="lineone">
									<td align="left" class="lineone">
										<span class="input"><b><%=messages.getText("generic.messages.type")%></b></span>&nbsp;
									</td>
									<td align="left" class="lineone">
										<html:select property="reportType" styleClass="input">
											<html:options collection="reportType" property="value" labelProperty="label"/>
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
