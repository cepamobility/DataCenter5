<%@page language="java"
    errorPage="../exception.jsp"
	import="business.*"
	import="communication.language.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.driver.*"
	import="presentation.translator.web.vehicle.*"
	import="communication.driver.tables.*"
	import="communication.security.*"
	import="communication.vehicle.tables.*"

%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>
<%!
	RequestDispatcher dispatcher; String context;String pathView; 
	String pathMsg; String pathSearch; 
	Text messages; String pathLanguage;
	SystemAccess sys; DriverData data; 
%> 
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	
	String check = null;
	String check2 = null;
    check = Util.checkPermission(sys,SecurityConstants.QUERY);   
    
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else{
		messages=sys.getText();
		// Para mensajes de error:
		pathLanguage="/util/jsp/language.jsp";
		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
		context=request.getContextPath();
		pathMsg="/util/jsp/message.jsp";
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);
		// Fin mensajes de error.
		

		Collection count = new LinkedList();
		for(int i = 1; i < 6; i++) {
			count.add(new OptionItem(String.valueOf(i),String.valueOf(i)));	    
		}
		session.setAttribute("count",count);
		
		Collection format = new LinkedList();
		format.add(new OptionItem("HTML","HTML"));	    
		format.add(new OptionItem("PDF","PDF"));	    
		format.add(new OptionItem("XLS","XLS"));	    
		session.setAttribute("format",format);
	
		session.setAttribute("url","driver/HRDQuery.jsp");
		
		if(session.getAttribute("VehicleWithoutPursuitRouteForm") == null) {
			VehicleWithoutPursuitRouteForm form = new VehicleWithoutPursuitRouteForm();
		    session.setAttribute("VehicleWithoutPursuitRouteForm",form);
		}
		
		Collection years = new LinkedList();
	    years.add(new OptionItem("",""));
		for(int i = 1998; i <= 2050; i++) {
			years.add(new OptionItem(String.valueOf(i),String.valueOf(i)));
		}
		session.setAttribute("years",years);
		
		Collection months = new LinkedList();
	    months.add(new OptionItem("",""));
		for(int i = 1; i <= 12; i++) { 
		    months.add(new OptionItem(messages.getText("generic.month."+String.valueOf(i)),String.valueOf(i)));
		}
		session.setAttribute("months",months);
		String status = String.valueOf(VehicleData.ACTIVE);

%>

<script>
	function changeCombo(radio,combo) {
		var el = document.forms[2].elements[radio];
		document.forms[2].elements[combo].disabled = !el[2].checked;
	}
</script>
<script>
function changeType(value){
	document.forms[2].type.value = value;

}
</script>
<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
</script>

<html:html>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>

	<body bgcolor="#FFFFFF">
		<html:form action="/VehicleWithoutPursuitRoute">			
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("vehicle.seguimientoenruta.query.notpursuit.query")%>' />
  				</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
	      		<tr>
    	    		<td width="40%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.vehicle.vehicledata")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<th align="left">
												<span class="linetwo"><%=messages.getText("generic.vehicle.vehicledata.matricula")%></span>
											</th>
								         	<td align="right">
												<html:text property="plate" style="width:155;" styleClass="input"/>													
											</td>
										</tr>  
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
										<jsp:include page='<%="/util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=VehicleWithoutPursuitRouteForm&consult=true"%>' />
									</table>
								</td>
							</tr>
						</table>
					</td>
    				<td width="16">&nbsp;</td>
    				<td width="40%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<jsp:include page='<%="/util/divorg.jsp?form=VehicleWithoutPursuitRouteForm&consult=true" %>'/>
						</table>
					</td>
  				</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
				<tr>
					<td align="left" class="texts">
						<html:select property="output" styleClass="input" >
							<html:options collection="format" property="value" labelProperty="label"/>
						</html:select>							          				
					</td>
					<td colspan="2" align="right" class="texts">
						<html:submit styleClass="input" ><%=messages.getText("generic.messages.open")%></html:submit>
					</td>
				</tr>
			</table>
		</html:form>
		
<%@ include  file = '../util/jsp/googleanalytics.jsp' %>				
		
	</body>
</html:html>
<% 
	}  // Fin del control de seguridad y permisos.
%>