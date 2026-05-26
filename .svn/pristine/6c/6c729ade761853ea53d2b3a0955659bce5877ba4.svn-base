<%@page language="java"
    errorPage="../exception.jsp"
	import="business.*"
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.datatype.*"
	import="communication.datatype.tables.*"
	import="communication.vehicle.tables.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.vehicle.*"
	import="communication.security.*"
	import="communication.driver.tables.*"
 	import="communication.administration.user.*" 
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>
<%!
	Text messages; SystemAccess sys;
%> 
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	messages = sys.getText();
	
	UserSessionData userSessionData=sys.getUserSessionData();	
	String check = Util.checkPermission(sys,SecurityConstants.QUERY);	
	RequestDispatcher dispatcher;
	String pathMsg;
	String pathLanguage;
	String context;
	if (check!=null)  
	{
		response.sendRedirect(request.getContextPath()+check);
	}
	else 
	{
		messages=sys.getText();
		pathLanguage="/util/jsp/language.jsp";	
		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
	
		context=request.getContextPath();
		pathMsg="/util/jsp/message.jsp";
	
		session.setAttribute("url","vehicle/SuctaMTOPQuery.jsp");
		
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);
		session.removeAttribute("Msg");
	
		Collection count = new LinkedList();
		for(int i = 1; i < 6; i++) {
			count.add(new OptionItem(String.valueOf(i),String.valueOf(i)));	    
		}
		session.setAttribute("count",count);
		SuctaMTOPQueryForm form = new SuctaMTOPQueryForm();
		session.setAttribute("VehicleReportForm",form);
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
		
		//for(int i = 1; i <= 4; i++) { 
		  //  months.add(new OptionItem("Q"+String.valueOf(i),"Q"+String.valueOf(i)));
		//}
		session.setAttribute("months",months);

%>

<script>
	function changeCombo(radio,combo) {
		var el = document.forms[2].elements[radio];
		document.forms[2].elements[combo].disabled = !el[2].checked;
	}
</script>

<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
</script>

<html:html>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>

	<body bgcolor="#FFFFFF">
		<html:form action="/SuctaMTOPQueryReport">			
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.messages.suctamtopquery.title.query")%>' />
  				</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
				
			
			
				<!-- Datos del Driver -->
	      		<tr>
    	    		<td width="40%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.vehicle.vehicledata")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										
											<tr>
											<td colspan="2" align="left" class="linetwo">
												<table>
													<tr>
														<th align="left">
															<html:radio styleClass="linetwo" property="vehicleorplataform" value="vehicle"><span class="linetwo"><%=messages.getText("generic.messages.suctamtopquery.vehicle")%></span></html:radio>
														</th>
														<th align="left">
															<html:radio styleClass="linetwo" property="vehicleorplataform" value="trailer"><span class="linetwo"><%=messages.getText("generic.messages.suctamtopquery.plataform")%></span></html:radio>
														</th>
													</tr>
												</table>
											</td>
										</tr>
										<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
									
										<tr>
											<th align="left">
												<span class="linetwo"><%=messages.getText("generic.vehicle.vehicledata.matricula")%></span>
											</th>
								         	<td align="right">
												<html:text property="plate" style="width:155;" styleClass="input"/>													
											</td>
										</tr>  
										
										
		    							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
											<td colspan="2" align="left" class="linetwo">
												<table>
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
										<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
											<td colspan="2" align="left" class="linetwo">
												<table>
													<tr>
														<th align="left">
															<html:radio styleClass="linetwo" property="suctaormtop" value="sucta"><span class="linetwo"><%=messages.getText("generic.messages.suctamtopquery.sucta")%></span></html:radio>
														</th>
														<th align="left">
															<html:radio styleClass="linetwo" property="suctaormtop" value="mtop"><span class="linetwo"><%=messages.getText("generic.messages.suctamtopquery.mtop")%></span></html:radio>
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
			      			
			      			<!--  Periodo -->
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
							
							<!-- Divisiones Geograficas -->
			      			<tr>
				    			<td valign="top">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<jsp:include page='<%="/util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=VehicleReportForm&consult=true"%>' />
									</table>
								</td>
							</tr>
						</table>
					</td>
    				<td width="16">&nbsp;</td>
    				<td width="40%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<jsp:include page='<%="/util/divorg.jsp?form=VehicleReportForm&consult=true"%>' />
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
									<td align="right"  class="lineone" width="90%">
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
<%
}
%>