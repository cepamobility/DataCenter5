<%@page language="java"
	errorPage="../exception.jsp"
	import="business.*"
	import="business.administration.user.*"
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.datatype.tables.*" 
	import="communication.datatype.*"
	import="communication.administration.user.tables.*"
	import="presentation.translator.web.util.*"
	import="java.util.*"
	import="business.vehicle.VehicleReportFacade"
	import="communication.vehicle.tables.VehicleData"	
	import="presentation.translator.web.vehicle.*"	
	import="communication.administration.user.UserSessionData"			
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>

<%! RequestDispatcher dispatcher; String context; String pathMsg;
	Text messages; String pathLanguage;
	SystemAccess sys; 
	UserSessionData userSessionData;		 
%>

<%

	pathLanguage="/util/jsp/language.jsp";
	
	session.setAttribute("url","vehicle/_preview.jsp");

	dispatcher=request.getRequestDispatcher(pathLanguage);
	dispatcher.include(request,response);

	context=request.getContextPath();
	pathMsg="/util/jsp/message.jsp";
	
	dispatcher=request.getRequestDispatcher(pathMsg);
	dispatcher.include(request,response);
%>


<%@page import="communication.driver.tables.DriverMainAttributes"%>

<html:html>
<%
	// marca para indicar que el update no es de datos b�sicos
	session.setAttribute("updateDriverOrTypeUse","changeDriver");

	sys = (SystemAccess)session.getAttribute("SystemAccess");
	if(sys==null){
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}

	userSessionData=sys.getUserSessionData();		
	
	messages=sys.getText();
	
	if(session.getAttribute("target")==null){
		session.setAttribute("target",request.getParameter("target"));
	}
	
	String idVehicle = (String)session.getAttribute("idVehicle");	
	String driverId = request.getParameter("id");
	session.setAttribute("url","vehicle/ChangeDriver.jsp?id="+driverId);
	
	VehicleReportFacade vehicleReportFacade = sys.getVehicleReportFacade();
	VehicleData vehicleData = vehicleReportFacade.findVehicleByPrimaryKey(new Integer(idVehicle),userSessionData);
	
	if(vehicleData!=null){
		// DriverMainAttributes newDriver = sys.getDriverReportFacade().findDriverMainData(Integer.parseInt(driverId),userSessionData);  
		DriverMainAttributes newDriver = sys.getDriverReportFacade().findDriverMainData(new Integer(driverId),userSessionData);
%>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head/>

<script language="JavaScript" src="../util/js/select_component.js" type="text/javascript">
</script>
<script>
function cambio(parent,child){
	var parentCombo = document.forms[0].elements[parent];
	var childCombo = document.forms[0].elements[child];
	var divi = parentCombo.options[parentCombo.selectedIndex].text+parentCombo.options[parentCombo.selectedIndex].value;
	var list = document.forms[0].elements[divi];
	childCombo.options.length=list.options.length;
	for(i=0; i < list.length; i++) {
		childCombo.options[i].value = list.options[i].value;
		childCombo.options[i].text = list.options[i].text;
	}
}
</script>
<body bgcolor="#FFFFFF">
	<html:errors/>
	<html:form action="/VehicleChangeUser">
		<html:hidden property="id" value='<%=idVehicle%>'/>
		<html:hidden property="driver" value='<%=driverId%>'/>
		<html:hidden property="type" value='<%=String.valueOf(DriverMainAttributes.CHANGE_DRIVER)%>' />
 		<table width="400" border="0" align="center" cellpadding="0" cellspacing="0">
  			<tr>
  				<td align="center" valign="middle">
   					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.confirmchangedriver")%>' />
   					<table width="100%">
   						<tr>
							<th align="left">
								<span class="linetwo" ><%=messages.getText("vehicle.changeuse.vehicle")%></span>
							</th>
							<td align="right">
								<span class="linetwo" ><%=vehicleData.getMatricula()%></span>
							</td>
   						</tr>
   						<tr>
							<th align="left">
								<span class="linetwo" ><%=messages.getText("vehicle.changeuse.oldvehicle")%></span>
							</th>
							<td align="right">
								<span class="linetwo" ><%=vehicleData.getDriver().getFirstName()+" "+vehicleData.getDriver().getLastName()%></span>
							</td>
   						</tr>
   						<tr>
							<th align="left">
								<span class="linetwo" ><%=messages.getText("vehicle.changeuse.newdriver")%></span>
							</th>
							<td align="right">
								<span class="linetwo" ><%=newDriver.getFirstName()+" "+newDriver.getLastName()%></span>
							</td>
   						</tr>
   						<tr>
							<td align="left" colspan="2">
								<span class="linetwo" ><%=messages.getText("generic.driver.comment")%></span>
							</td>
						</tr>
   						<tr>
							<td colspan="2">
								<html:textarea property="comment" cols="50"/>
							</td>
						</tr>
  						<tr  class="linetwo">
        					<td align="right" colspan="2">
            					<html:submit styleClass="input">	
            						<%=messages.getText("generic.messages.continue")%>
								</html:submit>
		 						<html:cancel styleClass="input">
		 							<%=messages.getText("generic.messages.cancel")%>
	 							</html:cancel >
							</td>
      					</tr>
   					</table>
   					<jsp:include  page="../component/utilityboxbottom.jsp" />
   				</td>
   			</tr>
   		</table>
	</html:form>   
</body>
<%	}else {
%>	<jsp:include  page='<%="/util/allowedData.jsp?message=vehicle.error.access.vehicle" %>'/>
<%	}%>
</html:html>
