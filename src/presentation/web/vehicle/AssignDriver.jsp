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
	session.setAttribute("updateDriverOrTypeUse","assignDriver");

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
	session.setAttribute("driverId", driverId);	
	
	VehicleReportFacade vehicleReportFacade = sys.getVehicleReportFacade();
	VehicleData vehicleData = vehicleReportFacade.findVehicleByPrimaryKey(new Integer(idVehicle),userSessionData);
	
	if(vehicleData!=null){
		vehicleData.setUse(new TypeUseDataType("persistence.datatype.typeuse.individual"));
		VehicleForm vehicleForm=VehicleUtil.createVehicleForm(vehicleData,VehicleUtil.EDIT,messages);
		session.setAttribute("VehicleCreateForm",vehicleForm);
	}	
		
	session.setAttribute("vehicleId",vehicleData.getId());	

%>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head/>

<script language="JavaScript" src="../util/js/select_component.js" type="text/javascript">
</script>
<body bgcolor="#FFFFFF">
<html:errors/>

<html:form action="update">
<p></p>
<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
  	<td colspan="3">   
    	<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.assignDriver")%>' />      
	   	<%=messages.getText("generic.messages.confirmassignnewdriver")%>
   		<jsp:include page="../component/utilityboxbottom.jsp"/>
    </td>
  </tr>
  <tr  class="linetwo">
      <td align="right"> &nbsp;</td>
      <td align="right" >
          <html:submit styleClass="input">
		  <%=messages.getText("generic.messages.continue")%>
			</html:submit>
	</td>
	<td algin="right">
	 	<html:cancel styleClass="input">
 		<%=messages.getText("generic.messages.cancel")%>
		</html:cancel >
	</td>          
   </tr>

</table>  

</html:form>   

</body>
</html:html>
