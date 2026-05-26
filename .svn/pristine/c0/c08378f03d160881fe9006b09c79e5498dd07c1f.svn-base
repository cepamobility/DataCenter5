<%@page import="org.apache.struts.action.DynaActionForm"%>
<%@page language="java" 
	errorPage="../exception.jsp" 
	import="business.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.ActivityTypeDataType"
	import="business.vehicle.VehicleReportFacade"
	import="java.util.*"	
	import="org.apache.struts.action.DynaActionForm"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<!-- VehicleCheckFormNalco -->

<html:html>
<%!
	RequestDispatcher dispatcher; 
	String context;String pathView; String pathMsg; 
	String pathSearch;
	String pathLanguage; Text messages; SystemAccess sys;
	Integer pageNum; boolean all; int pageSize; 
	String attribute; String value;
	UserSessionData userSessionData; 
	ActivityReportFacade activityReport;
	String str1, str2, str3,col;
	String idVehicle;
	VehicleData  vehicle;
	int columns;
	DynaActionForm vehicleCheckFormNalco; 
	String from;
	String url = "";
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();	
	
	String check = Util.checkPermission(sys,SecurityConstants.EDIT_VEHICLE);
	String check1 = Util.checkPermission(sys,SecurityConstants.MODIFY_VEHICLE);	
	String check2 = Util.checkPermission(sys,SecurityConstants.CREATE_CD_ACTIVITY);
	
	if ((check!=null) && (check1!=null) && (check2!=null))  {
		response.sendRedirect(request.getContextPath()+check);	

	}else {
		messages=sys.getText();
		pathLanguage="/util/jsp/language.jsp";	
		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
	
		context=request.getContextPath();
		pathMsg="/util/jsp/message.jsp";
	
		// session.setAttribute("url","vehicle/VehicleCheckForm.jsp");
		url = "vehicle/VehicleCheckFormNalco.jsp";
		userSessionData.setUrl(url);
		
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);
		
		String dayStr = "";
		String monthStr = "";
		String yearStr = "";
		String obsStr = "";
		String nameStr = "";	
		//String tradeStr = "";			
		//String modelStr = "";					
		String plateStr = "";					
		//String serieStr = "";			
		//String vehicleKmStr = "";		
		//String licenseNumberStr = "";				
		
		//String dayLicenseExpiryStr = "";
		//String monthLicenseExpiryStr = "";
		// String yearLicenseExpiryStr = "";		
		
		String inspectedbyStr = "";
		String distritoStr = "";
		String grupoStr = "";
		String checkboxParamListStr = "";
	
		idVehicle =(String)request.getParameter("id");
		if (idVehicle == null){
	idVehicle =(String)request.getParameter("idVehicle");
	if (idVehicle == null){
		idVehicle = (String)session.getAttribute("id");
	}
		}
		session.setAttribute("id",idVehicle);
		
		VehicleReportFacade vehicleReport = null;
		VehicleData vehicle = null;
		String plate = (String)request.getParameter("plate");
		System.out.println("VehicleCheckFormNalco - Plate:"+plate);
		
		
		vehicleReport = sys.getVehicleReportFacade();	
		if (idVehicle!=null) {				
	vehicle = vehicleReport.findVehicleByPrimaryKey(new Integer(idVehicle),userSessionData);			
		}else{
	if (!Util.empty(plate))	{
		vehicle = vehicleReport.findVehicleByPlate(plate,userSessionData);
	}
		}		
		

		String name = (String)request.getParameter("name");
		if (Util.empty(name)){
	name = "";
		}else
	nameStr = name;
		
		if (vehicle != null) {
	// tradeStr = messages.getText(vehicle.getTrade().getResourceBundleId());
	plateStr = vehicle.getMatricula();						
	//System.out.println("VehicleCheckForm - Matricula Vehiculo:"+vehicle.getMatricula());
	// modelStr = messages.getText(vehicle.getModel());						
		}else{
	plateStr = plate;
		}
		
		
		// Obtengo la cantidad de columnas a dibujar en el formulario:
		col = request.getParameter("columns");		
		if (!Util.empty(col))
	columns	 = (Integer.parseInt(col));
		else
	columns = 1;
		
		// TODO: Si el vehiculo tiene una ultima checklist mostrarla
		// asi se puede modificar
		
		// Si hubo problemas con la validación se recargan los datos ya ingresados
		vehicleCheckFormNalco = (DynaActionForm)session.getAttribute("VehicleCheckFormNalco");
		if(vehicleCheckFormNalco !=null) {
	nameStr = (String) vehicleCheckFormNalco.get("name");
	obsStr = (String) vehicleCheckFormNalco.get("obs");
	//tradeStr = (String) vehicleCheckFormNalco.get("trade");			
	plateStr = (((String) vehicleCheckFormNalco.get("plate")) != null)?
	((String) vehicleCheckFormNalco.get("plate")): plate;
	//modelStr = (String) vehicleCheckFormNalco.get("model");						
	dayStr = (String) vehicleCheckFormNalco.get("day");						
	monthStr = (String) vehicleCheckFormNalco.get("month");						
	yearStr = (String) vehicleCheckFormNalco.get("year");									
	// vehicleKmStr = (String) vehicleCheckFormNalco.get("vehicleKm");			
	
	// licenseNumberStr = (String) vehicleCheckFormNalco.get("licenseNumber");			
	
	//dayLicenseExpiryStr = (String) vehicleCheckFormNalco.get("dayLicenseExpiry");						
	//monthLicenseExpiryStr = (String) vehicleCheckFormNalco.get("monthLicenseExpiry");						
	//yearLicenseExpiryStr = (String) vehicleCheckFormNalco.get("yearLicenseExpiry");
	
	inspectedbyStr = (String) vehicleCheckFormNalco.get("inspectedby");
	distritoStr = (String) vehicleCheckFormNalco.get("distrito");
	grupoStr = (String) vehicleCheckFormNalco.get("grupo");
	checkboxParamListStr = (String) vehicleCheckFormNalco.get("checkboxParamList");
		}
		
		from = (String)session.getAttribute("from");
		if (Util.empty(from)){
	from = "";
		}	
		
		session.setAttribute("VehicleCheckFormNalco",vehicleCheckFormNalco);
%>	
<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<script language="JavaScript" type="text/javascript">

function onSelect1(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayId1");
  		var input_month = document.getElementById("monthId1");
  		var input_year = document.getElementById("yearId1");
  		var date3 = new Date(time);
  		input_day.value = date3.print("%d");
  		input_month.value = date3.print("%m");
  		input_year.value= parseInt(date3.print("%Y"),10);
  }

function preparedCheckboxRadioParameters(form){

		var elements=form.elements;
		var length=elements.length;
		var i;
		var valueCheckboxParamList="";
		var separator="<";
		
		for(i=0;i<length;i++){
			var element=elements[i];
			if((element.type.toLowerCase()=="checkbox") || (element.type.toLowerCase()=="radio")) {
				if (element.checked) {	
					if(valueCheckboxParamList==""){
						valueCheckboxParamList=element.value;
					}
					else{
						valueCheckboxParamList=element.value+"<"+valueCheckboxParamList;
					}	
				}	
			} 
		
		}
		form.checkboxParamList.value=valueCheckboxParamList;
		return true;

}

function setAction(form1,form2){
	if(preparedCheckboxRadioParameters(form1)) {
		form2.checkboxParamList.value = form1.checkboxParamList.value;
		form2.obs.value = form1.obs.value;
		form2.day.value = form1.day.value;		
		form2.month.value = form1.month.value;				
		form2.year.value = form1.year.value;						
		form2.name.value = form1.name.value;	
		//form2.trade.value = form1.trade.value;	
		//form2.model.value = form1.model.value;					
		form2.plate.value = form1.plate.value;							
		//form2.serie.value = form1.serie.value;									
		// form2.vehicleKm.value = form1.vehicleKm.value;									
		
		// form2.licenseNumber.value = form1.licenseNumber.value;	
		// form2.dayLicenseExpiry.value = form1.dayLicenseExpiry.value;		
		// form2.monthLicenseExpiry.value = form1.monthLicenseExpiry.value;				
		// form2.yearLicenseExpiry.value = form1.yearLicenseExpiry.value;				
		
		form2.inspectedby.value = form1.inspectedby.value;
		form2.distrito.value = form1.distrito.value;
		form2.grupo.value = form1.grupo.value;
		
		form2.submit();
	}
}

</script>

<!-- Name: <%=name%> -->
<!-- Plate: <%=plate%> -->



<body>
<div align="center">


  <form name="Form1" method="post" action="">
  
  
 <table width="600" border="0" cellspacing="0" cellpadding="0">  
 <tr> 
 <td height="40" colspan="5" valign="middle">
  <!--  Titulo -->
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr> 
      <td width="9">
      	<img src="/datacenter/util/imgs/titulo_left.gif" width="9" height="40">
      </td>
      <td width="582" background="/datacenter/util/imgs/bg2.gif">
		<div align="center">
			<font color="#FFFFFF" size="4" face="Arial, Helvetica, sans-serif">
			<strong>		
			<%=messages.getText("generic.activity.form.vehiclechecklist")%>
            </strong>
            </font>
        </div>
      </td>
      <td width="9">
      	<img src="/datacenter/util/imgs/titulo_right.gif" width="9" height="40">
      </td>
    </tr>
  </table>
  </td>
  </tr>
  <!--  Fin Titulo -->
  
  
<tr>
<td colspan="5">  
<!--  Datos Básicos de la Prueba --> 
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<!--  Titulo del Punto: -->
	<tr> 
    	<td width="9" valign="top">
    		<img src="/datacenter/util/imgs/left_form.gif" >
    	</td>    	
        <td colspan="2" background="/datacenter/util/imgs/center_form.gif" valign="top"> 
        	<div align="center">
        		<!--  Cambiar esto por un estilo !! -->
        		<font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"><strong>
        		<%=messages.getText("generic.activity.form.tranningonthejob.proofdata")%>
        		</strong></font>
        	</div>
        </td>
        <td width="9" valign="top">
        	<img src="/datacenter/util/imgs/right_form.gif" >        	
        </td>
     </tr>
	
   
	<!-- Nombre del Conductor -->
	<tr> 
        <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;
        </td>        
        <td width="50%" align="left">        
        <font size="1" face="Arial, Helvetica, sans-serif"> 
        <%= messages.getText("vehiclechecknalco.drivername")%>
        </font> 		
		</td>
		<td width="50%" align="right">		
        <font size="1" face="Arial, Helvetica, sans-serif"> 		
		<input type=text name="name" class="input" value = '<%= nameStr%>' <%=(from.equals("commentary"))?"disabled":"" %> />                                     
        </font> 
        </td>
        <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;
        </td>
     </tr>
	
	<!-- Numero del Vehiculo -->
	  <tr>  
        <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;
        </td>
        <td width="50%" align="left">        
        <font size="1" face="Arial, Helvetica, sans-serif">
        <%= messages.getText("vehiclechecknalco.vehiclenumber") %>
        &nbsp;(<%= messages.getText("generic.activity.form.vehiclecheck.plate")%>)
        </font> 		
		</td>
		<td width="50%" align="right">		
        <font size="1" face="Arial, Helvetica, sans-serif"> 		
		<input type=text name="plate" class="input" value = '<%=plateStr%>' disabled /> <!-- < % =(plateStr.equalsIgnoreCase(""))?"":"disabled" % > -->                                             
        </font> 
        </td>
        <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;
        </td>
     </tr>
     
     <!-- Inspeccionado por -->
       <tr>  
        <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;
        </td>
        <td width="50%" align="left">        
        <font size="1" face="Arial, Helvetica, sans-serif"> 
        <%= messages.getText("vehiclechecknalco.inspectedby")%>
        </font> 		
		</td>
		<td width="50%" align="right">		
        <font size="1" face="Arial, Helvetica, sans-serif"> 		
		<input type=text name="inspectedby" class="input" value = '<%=inspectedbyStr%>' />                                              
        </font> 
        </td>
        <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;
        </td>
     </tr>
     
     <!-- Distrito -->
       <tr>  
        <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;
        </td>
        <td width="50%" align="left">        
        <font size="1" face="Arial, Helvetica, sans-serif"> 
        <%= messages.getText("vehiclechecknalco.distrito")%>
        </font> 		
		</td>
		<td width="50%" align="right">		
        <font size="1" face="Arial, Helvetica, sans-serif"> 		
		<input type=text name="distrito" class="input" value='<%=distritoStr%>' />                                              
        </font> 
        </td>
        <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;
        </td>
     </tr>
	
	 <!-- Grupo -->
     <tr>  
        <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;
        </td>
        <td width="50%" align="left">        
        <font size="1" face="Arial, Helvetica, sans-serif"> 
        <%= messages.getText("vehiclechecknalco.grupo")%>
        </font> 		
		</td>
		<td width="50%" align="right">		
        <font size="1" face="Arial, Helvetica, sans-serif"> 		
		<input type=text name="grupo" class="input" value='<%=grupoStr%>' />                                              
        </font> 
        </td>
        <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;
        </td>
     </tr>
     
     <!--  Fecha -->
     <tr> 
        
        <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;
        </td>
        
        <td width="50%" align="left">        
        <font size="1" face="Arial, Helvetica, sans-serif">                 
        <%= messages.getText("generic.activity.date")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
        </font> 		
		</td>
		<td width="50%" align="right">
        <font size="1" face="Arial, Helvetica, sans-serif">                         		
		<input type=text style="width:20;" name="day" class="input" value ='<%= dayStr%>' id="dayId1"/>&nbsp;/&nbsp;                
		<input type=text style="width:20;" name="month" class="input" value ='<%= monthStr%>' id="monthId1"/>&nbsp;/&nbsp; 
		<input type=text style="width:26;" name="year" class="input" value ='<%= yearStr%>' id="yearId1"/>&nbsp;           		    		
        </font>
        <img src="component/jscalendar-1.0/img.gif" id="calendar_button1" style="cursor: pointer; border: 1px solid red;"
     	onmouseover="this.style.background='red';" style="width:20;"  onmouseout="this.style.background=''"/>
			          						
		<script type="text/javascript">
			Calendar.setup({
        		inputArea     :    "yearId1",
        		ifFormat       :    "%d/%m/%Y",
        		showsTime      :    true,
        		timeFormat     :    "24",
        		button         :    "calendar_button1",
        		step           :    1,
        		onSelect       :    onSelect1,
        		singleClick    :    true
    		});
		</script> 		
		</td>
        <td width="9" background="/datacenter/util/imgs/line_right.gif">
        </td>
     </tr>   
      

	 <!--  Dibujo linea inferior de los cuadrantes -->
 	 <tr> 
        <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
        <td colspan= "2" background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
        <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
     </tr> 
  
</table>
<!-- Fin de Datos Básicos de la Prueba --> 
</td>
</tr>  

<tr>
<td>
  <jsp:include  page='<%="NColumnsFormNalco.jsp?cantCol=1" %>'/>
</td>  
</tr>

<tr>
<td colspan="5">

  <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td valign="top"><img src="/datacenter/util/imgs/left_top_gray.gif" width="9" height="11"></td>
              <td background="/datacenter/util/imgs/center_top_gray.gif"> <div align="left"><strong><img src="/datacenter/util/imgs/center_base_gray.gif" width="1" height="11"></strong></div></td>
              <td valign="top"><strong><img src="/datacenter/util/imgs/right_top_gray.gif" width="9" height="11"></strong></td>
            </tr>    
            <tr> 
              <td width="9" background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td  bgcolor="#f9f9f9"><font size="1" face="Arial, Helvetica, sans-serif"><strong><%= messages.getText("generic.activity.observations") %></strong></font></td>
              <td width="10" background="/datacenter/util/imgs/right_gray.gif" bgcolor="#f9f9f9">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" height="88" background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td bgcolor="#f9f9f9"><strong><font color="#FF0000" size="1" face="Arial, Helvetica, sans-serif"> 
                <textarea name="obs" cols="100" rows="5"><%= obsStr%></textarea>
                </font></strong></td>
              <td width="10" background="/datacenter/util/imgs/right_gray.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" height="11" valign="top"><img src="/datacenter/util/imgs/left_base_gray.gif" width="9" height="11"></td>
              <td  valign="top" background="/datacenter/util/imgs/center_base_gray.gif"><img src="/datacenter/util/imgs/center_base_gray.gif" width="1" height="11"></td>
              <td width="10" valign="top"><img src="/datacenter/util/imgs/right_base_gray.gif" width="9" height="11"></td>
            </tr>
       </table>
</td>
</tr>

        	<tr>   		
		   		<td align="right">
		   		    <input type="hidden" name="checkboxParamList" />   
		   			<input name="button2" type="button" 
		   			value='<%=messages.getText("generic.messages.accept") %>' 
		   			onClick="javascript:setAction(document.forms['Form1'] ,document.forms['VehicleCheckFormNalco']);" class="input"/>	 		                             
            	</td>                 							
    	  	</tr>              
 </table>

</form>
      
<html:form action="VehicleCheckCreateNalco" method="post" >
	<html:hidden property="checkboxParamList" value="checkboxParamList" />
	<html:hidden property="obs" value="obs" />	
	<html:hidden property="day" value="" />		
	<html:hidden property="month" value="" />		
	<html:hidden property="year" value="" />	
	<html:hidden property="name" value="" />		
	<html:hidden property="plate" value="" />
	<html:hidden property="inspectedby" value="" />
	<html:hidden property="distrito" value="" />
	<html:hidden property="grupo" value="" />
</html:form>
</div>
</body>

<%
	}	
%>	

</html:html>
