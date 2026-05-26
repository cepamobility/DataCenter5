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


<html:html>

<%
	// marca para indicar que el update no es de datos básicos
	session.setAttribute("updateDriverOrTypeUse","changeToInstitutional");

	sys = (SystemAccess)session.getAttribute("SystemAccess");
	if(sys==null){
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}
	
	userSessionData=sys.getUserSessionData();	
	
	messages=sys.getText();
	
	if(session.getAttribute("target")==null){
		session.setAttribute("target",request.getParameter("target"));
	}
	
	String idVehicle = request.getParameter("id");	
	session.setAttribute("url","vehicle/ChangeDriver.jsp?id="+idVehicle);

	VehicleReportFacade vehicleReportFacade = sys.getVehicleReportFacade();
	VehicleData vehicleData = vehicleReportFacade.findVehicleByPrimaryKey(new Integer(idVehicle),userSessionData);
	
	if(vehicleData!=null){
		vehicleData.setUse(new TypeUseDataType("persistence.datatype.typeuse.institutional"));
		vehicleData.setDriver(null);		
		VehicleForm vehicleForm=VehicleUtil.createVehicleForm(vehicleData,VehicleUtil.EDIT,messages);
		session.setAttribute("VehicleCreateForm",vehicleForm);
		//System.out.println("--------------CARGUE SESION--------------:");					
		
		session.setAttribute("vehicleId",vehicleData.getId());	

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

function submitForm()
{	
	document.forms[2].submit();
}
</script>
<body bgcolor="#FFFFFF">
<html:errors/>

<html:form action="/update">
 <p>

</p>
	<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr class="guide1">
			<jsp:include  page='<%="../component/titlecomponent.jsp?title=" + messages.getText("generic.messages.changeToInstitutional")%>' />
  		</tr>
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
      	</tr>		  
		<tr>			
			<!-- Opciones -->
			<td>
				<jsp:include  page='<%="../component/utilitybox3top.jsp?title="+messages.getText("generic.messages.options")%>' />
				<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="texts1">
				<tr>
					<td valign="middle">
						<img align="middle" border="0" src="/datacenter/util/imgs/icon2.gif" width="12">&nbsp;
						<a href="javascript:submitForm();" align="center" class="texts">						
							<%=messages.getText("generic.messages.changeToInstitutional")%>
					 	</a>
					</td> 
					<td valign="middle">
						<img align="middle" border="0" src="/datacenter/util/imgs/icon2.gif" width="12">&nbsp;
						<a href="javascript:history.go(-1)" align="center" class="texts">						
							<%=messages.getText("generic.messages.cancel")%>
						</a>
					</td>
				</tr>
				</table>
				<jsp:include page="../component/utilitybox3bottom.jsp"/>					
			</td>
			<!-- Fin Opciones -->
		</tr>
   		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
      	</tr>	
     
		<tr>
			<td>
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0" class="texts">			
			<!-- Comentarios -->
			<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.comment")%>' />
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<html:textarea property="comment" style="width:100%" rows="5"></html:textarea>
					</tr>
			</table>
			<jsp:include page="../component/utilityboxbottom.jsp"/>	
			</table>
			</td>
		</tr>	
	</table> 
</html:form>   

</body>
<%	}else {
%>	<jsp:include  page='<%="/util/allowedData.jsp?message=vehicle.error.access.vehicle" %>'/>
<%	}%>
</html:html>
