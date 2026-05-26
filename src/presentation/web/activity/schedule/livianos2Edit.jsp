<%@page language="java" 
	errorPage="../../exception.jsp"
	import="business.*"
	import="business.activity.*"
	import="business.datatype.*"
	import="business.administration.user.*"
	import="communication.language.*"	
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.activity.tables.*"
	import="communication.administration.corporation.tables.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.ActivityTypeDataType"
	import="business.activity.ActivityReportFacade"
	import="java.util.*"
	import="communication.datatype.tables.*"
	import="communication.datatype.*"		
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>
<%!
	RequestDispatcher dispatcher; String context;String pathView; String pathMsg; 
	String pathSearch;
	String pathLanguage; Text messages; SystemAccess sys;
	Integer pageNum; boolean all; int pageSize; 
	String attribute; String value;
	UserSessionData userSessionData; 
	ActivityReportFacade activityReport;
	String str1;
	String str2;	
	String str3, url, idActivity, idDriver;		
	String checkedStr;	
	Collection itemsResult;	
	CEPAActivityScheduleRegistryData registryData;	
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");

	userSessionData=sys.getUserSessionData();
	
	String check = Util.checkPermission(sys,SecurityConstants.EDIT_CEPASCHEDULEACTIVITYREGISTRY); 
	
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		messages=sys.getText();

		pathLanguage="/util/jsp/language.jsp";
	
		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
	
		context=request.getContextPath();
		pathMsg="/util/jsp/message.jsp";
	
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);

		idActivity=(String)request.getParameter("idActivity");
		if(idActivity==null){
			idActivity=(String)session.getAttribute("id");			
		}
		// session.setAttribute("id",idActivity);
		
		idDriver=(String)request.getParameter("idDriver");
		// session.setAttribute("idDriver",idDriver);			
	
		activityReport = sys.getActivityReportFacade();
		registryData = activityReport.getCompleteResult(new Integer(idActivity),new Integer(idDriver),sys.getUserSessionData());		
		itemsResult = registryData.getItemSet();		
		
	}	
		
%>	

	<LINK REL=StyleSheet HREF="../../util/css/estilo.css" TYPE="text/css">

<head>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<META http-equiv="Expires" content="-1">		
</head>


<script language="JavaScript" type="text/javascript">
function preparedCheckboxRadioParameters(form){

		var elements=form.elements;
		var length=elements.length;
		var i;
		var valueCheckboxParamList="";
		var separator="<";
		
		for(i=0;i<length;i++){
			var element=elements[i];
			if((element.type.toLowerCase()=="checkbox") || (element.type.toLowerCase()=="radio")) {
				if(element.checked){
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
		form2.note.value = form1.note.value;
		form2.obs.value = form1.obs.value;		
		form2.instructor.value = form1.instructor.value;		
    	form2.partner.value = "";	
		form2.cepaCourse.value = "true";	
		form2.conductionType.value = "";
		form2.idDriver.value = '<%=idDriver%>';
		form2.idActivity.value = '<%=idActivity%>';
		form2.url.value = '<%=url%>';			    					
		form2.submit();
	}
}
</script>
<body>
<div align="center">
  <table width="656" border="0" cellpadding="0" cellspacing="0">
    <tr> 
      <td width="9"><img src="/datacenter/util/imgs/titulo_left.gif" width="9" height="40"></td>
      <td width="844" background="/datacenter/util/imgs/bg2.gif">
		<div align="center"><font color="#FFFFFF" size="4" face="Arial, Helvetica, sans-serif">
		<strong>
		<%=messages.getText("generic.activity.form.btw1liviano")%></strong></font></div></td>
      <td width="9"><img src="/datacenter/util/imgs/titulo_right.gif" width="9" height="40"></td>
    </tr>
    <tr> 
      <td width="9">&nbsp;</td>
      <td>&nbsp;</td>
      <td width="9">&nbsp;</td>
    </tr>
	
    <!-- Formulario: -->
    <tr> 
      <td colspan="3"><form name="form2" method="post" action="">
          <table width="650" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr> 
              <td colspan="3" rowspan="33" valign="top"><table width="215" border="0" cellpadding="0" cellspacing="0">
                  <tr> 
                    <td width="9"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
                    <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"><strong>Postura</strong></font></div></td>
                    <td width="9"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
                  </tr>
                  <tr> 

              		<%
						str1 = "generic.activity.form.btw1liviano.position";
						str2 = "generic.activity.form.btw1liviano.position.pedalnext";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}	
					%>                  
                  
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td> <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>> 
                      <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw1liviano.position.pedalnext")%></font> </td>
                    <td width="9" background="form//datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                  
                  	<%
						str1 = "generic.activity.form.btw1liviano.position";
						str2 = "generic.activity.form.btw1liviano.position.steeringwheelnext";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}	
					%>  
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td> <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%= checkedStr%>>                     
                      <font size="1" face="Arial, Helvetica, sans-serif">Pr&oacute;xima 
                      al volante </font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                  
              		<%
						str1 = "generic.activity.form.btw1liviano.position";
						str2 = "generic.activity.form.btw1liviano.position.pedalfar";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}	
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td> <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>> 
                      <font size="1" face="Arial, Helvetica, sans-serif">Alejada 
                      a los pedales</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.position";
						str2 = "generic.activity.form.btw1liviano.position.steeringwheelnext";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td> <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>> 
                      <font size="1" face="Arial, Helvetica, sans-serif">Alejada 
                      al volante</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.position";
						str2 = "generic.activity.form.btw1liviano.position.steeringwheelhands";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td> <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>> 
                      <font size="1" face="Arial, Helvetica, sans-serif">Ubicaci&oacute;n 
                      de las manos al volante</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.position";
						str2 = "generic.activity.form.btw1liviano.position.onehand";						
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td> <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%= checkedStr%>> 
                      <font size="1" face="Arial, Helvetica, sans-serif">Conduce 
                      con una mano</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr>                  
                    <td width="9" height="19" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
                    <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
                  </tr>
                </table>
                <table width="215" border="0" cellpadding="0" cellspacing="0">
                  <tr> 
                    <td width="9" height="15" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
                    <td background="/datacenter/util/imgs/center_form.gif"> 
                      <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"><strong>Est. 
                        maniobra en espacio reducido</strong></font></div></td>
                    <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
                  </tr>
                  <tr> 
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td> <font size="1" face="Arial, Helvetica, sans-serif">Recibi&oacute; 
                      asistencia en:</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.signal";						
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                    <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Se&ntilde;alizaci&oacute;n</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
               		<%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.spaceandpositionbeforemaneuver";						
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							

					%>  
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                    <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Percepci&oacute;n de espacio y ubicaci&oacute;n previo a 
                      maniobrar</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.referencesearch";						
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td> <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      <font size="1" face="Arial, Helvetica, sans-serif">B&uacute;squeda 
                      de referencias</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.referenceuse";						
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}	
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                     <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Uso de referencias</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.possibilityevaluation";						
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}	
					%>                    
                    <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                     <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Criterio para valorar posibilidades</font></td>
                    <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.techniqueapply";						
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}	
					%>                    
                    <td height="19" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td> <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      <font size="1" face="Arial, Helvetica, sans-serif">T&eacute;cnica 
                      a aplicar en la maniobra</font></td>
                    <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.generalobservation";						
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td> <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      <font size="1" face="Arial, Helvetica, sans-serif">Observaci&oacute;n 
                      general</font></td>
                    <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.mirroruse";						
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}	
					%>                    
                    <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                     <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Uso de espejos</font></td>
                    <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.blindpointcontrol";						
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}	
					%>                    
                    <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                    <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Control de puntos ciegos</font></td>
                    <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.sensibilitywithcommands";						
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}	
					%>                    
                    <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                     <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Sensibilidad con los comandos</font></td>
                    <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.commandcoordination";						
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}	
					%>                    
                    <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                     <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Coordinaci&oacute;n con los comandos</font></td>
                    <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.velocitycontrol";						
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}	
					%>                    
                    <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                     <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Control y adecuaci&oacute;n de la velocidad</font></td>
                    <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.limitedmaneuver";																																																																							
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
					 <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Presenta limitaciones de maniobrabilidad</font></td>
                    <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
                    <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
                  </tr>
                </table>
                <table width="215" border="0" cellpadding="0" cellspacing="0">
                  <tr> 
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
                    <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"><strong>Regulaci&oacute;n 
                        de espejos</strong></font></div></td>
                    <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.mirrors";
						str2 = "generic.activity.form.btw1liviano.mirrors.regulationoptions";																																																																							
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
					 <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Comprende opciones de regulaci&oacute;n (abierta y cerrada)</font></td>
                    <td width="9" valign="top" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.mirrors";
						str2 = "generic.activity.form.btw1liviano.mirrors.blindpoint";																																																																							
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Comprende dificultades que se presentan con los puntos ciegos</font></td>
                    <td width="9" valign="top" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                    <td width="9" height="19" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
                    <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
                  </tr>
                </table>
                <table width="216" border="0" cellpadding="0" cellspacing="0">
                  <tr> 
                    <td valign="top"><img src="/datacenter/util/imgs/left_top_gray.gif" width="9" height="11"></td>
                    <td background="/datacenter/util/imgs/center_top_gray.gif"> <div align="left"><strong></strong></div></td>
                    <td valign="top"><strong><img src="/datacenter/util/imgs/right_top_gray.gif" width="9" height="11"></strong></td>
                  </tr>
                  <tr> 
                    <td background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
                    <td align='center' bgcolor="#f9f9f9"><div align="left"><font size="1" face="Arial, Helvetica, sans-serif">Notas</font></div></td>
                    <td background="/datacenter/util/imgs/right_gray.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                    <td background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
                    <td bgcolor="#f9f9f9"> 
					<%
              		DataTypeReportFacade dataTypeReportFacade = sys.getDataTypeReportFacade();		
      				Collection notes = dataTypeReportFacade.findAll(DataTypeConstants.TYPECEPAACTIVITYNOTE,userSessionData);
              		if (notes != null){ 
              	%>
              		<select name="note" class="input">
              		
              		<%
              			Iterator it = notes.iterator();
              			while (it.hasNext()){
              				String activityNote = ((CEPAActivityNoteDataType) it.next()).getResourceBundleId();
              				String selected = registryData.getNote().equals(activityNote)?"selected":"";
              		%>
               			  <option value='<%= activityNote %>' class="input" <%= selected %> ><%= messages.getText(activityNote) %></option>
               		<%
               			}%>
                	</select> 
                <%
                	}%></td>
                    <td background="/datacenter/util/imgs/right_gray.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                    <td valign="top" background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
                    <td bgcolor="#f9f9f9">&nbsp;</td>
                    <td valign="top" background="/datacenter/util/imgs/right_gray.gif" bgcolor="#f9f9f9">&nbsp;</td>
                  </tr>
                  <tr> 
                    <td width="9" background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
                    <td width="197" bgcolor="#f9f9f9"><font size="1" face="Arial, Helvetica, sans-serif"><strong><%= messages.getText("generic.activity.observations") %></strong></font></td>
                    <td width="10" background="/datacenter/util/imgs/right_gray.gif" bgcolor="#f9f9f9">&nbsp;</td>
                  </tr>
                  <tr> 
                    <td width="9" height="88" background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
                    <td bgcolor="#f9f9f9"><strong><font color="#FF0000" size="1" face="Arial, Helvetica, sans-serif"> 
                      <textarea name="obs" cols="22" rows="5"><%=registryData.getObs() %></textarea>
                      </font></strong></td>
                    <td width="10" background="/datacenter/util/imgs/right_gray.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                    <td width="9" height="11" valign="top"><img src="/datacenter/util/imgs/left_base_gray.gif" width="9" height="11"></td>
                    <td width="197" valign="top" background="/datacenter/util/imgs/center_base_gray.gif"><img src="/datacenter/util/imgs/center_base_gray.gif" width="1" height="11"></td>
                    <td width="10" valign="top"><img src="/datacenter/util/imgs/right_base_gray.gif" width="9" height="11"></td>
                  </tr>
                </table></td>
              <td width="23" height="15"><img src="../../../../Carla/DC5/Nova%20pasta/tranp.jpg" width="1" height="1"></td>
              <td colspan="2" rowspan="33" valign="top"><table width="215" border="0" cellpadding="0" cellspacing="0">
                  <tr> 
                    <td width="9"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
                    <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><strong><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif">Mec&aacute;nica 
                        de brazos</font></strong></div></td>
                    <td width="9"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
                  </tr>
                  <tr> 
                  	<%
						str1 = "generic.activity.form.btw1liviano.mecanicarms";
						str2 = "generic.activity.form.btw1liviano.mecanicarms.onehand";
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                  
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Resuelve con una mano</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                  	<%
						str1 = "generic.activity.form.btw1liviano.mecanicarms";
						str2 = "generic.activity.form.btw1liviano.mecanicarms.openhands";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Resuelve con mano/s abierta/s</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                  	<%
						str1 = "generic.activity.form.btw1liviano.mecanicarms";
						str2 = "generic.activity.form.btw1liviano.mecanicarms.steeringwheelinside";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Resuelve por dentro del volante</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                  	<%
						str1 = "generic.activity.form.btw1liviano.mecanicarms";
						str2 = "generic.activity.form.btw1liviano.mecanicarms.bottomlocked";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Bloqueado en parte inferior</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                  	<%
						str1 = "generic.activity.form.btw1liviano.mecanicarms";
						str2 = "generic.activity.form.btw1liviano.mecanicarms.dinamic";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Din&aacute;mica</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                  	<%
						str1 = "generic.activity.form.btw1liviano.mecanicarms";
						str2 = "generic.activity.form.btw1liviano.mecanicarms.limitedmoviments";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Movimientos limitados en general</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                  	<%
						str1 = "generic.activity.form.btw1liviano.mecanicarms";
						str2 = "generic.activity.form.btw1liviano.mecanicarms.specialwork";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Trabajo especial</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                    <td width="9" height="19" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
                    <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
                  </tr>
                </table>
                <table width="215" border="0" cellpadding="0" cellspacing="0">
                  <tr> 
                    <td width="9" height="15" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
                    <td width="632" background="/datacenter/util/imgs/center_form.gif"> <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"><strong>Frenaje 
                        exigido/sistema convencional</strong></font></div></td>
                    <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
                  </tr>
                  <tr> 
                  	<%
						str1 = "generic.activity.form.btw1liviano.requiredbraking";
						str2 = "generic.activity.form.btw1liviano.requiredbraking.incorrecttechnique";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}			
						
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td width="632"> <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      <font size="1" face="Arial, Helvetica, sans-serif">Incorrecta 
                      t&eacute;cnica de frenado</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                  	<%
						str1 = "generic.activity.form.btw1liviano.requiredbraking";
						str2 = "generic.activity.form.btw1liviano.requiredbraking.pressesfirstclutch";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}	
					%>                    
                    <td width="9" height="21" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td width="632"> <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      <font size="1" face="Arial, Helvetica, sans-serif">Oprime 
                      primero ele embrague/clutch antes que el freno</font> </td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                  	<%
						str1 = "generic.activity.form.btw1liviano.requiredbraking";
						str2 = "generic.activity.form.btw1liviano.requiredbraking.avoidwithoutcontrollingvelocity";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}														
					%>                    
                    <td height="27" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td> <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      <font size="1" face="Arial, Helvetica, sans-serif">Tendencia 
                      a esquivar sin controlar previamente la velocidad</font></td>
                    <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                  
                    <td width="9" height="19" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
                    <td width="632" background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
                  </tr>
                </table>
                <table width="215" border="0" cellpadding="0" cellspacing="0">
                  <tr>                  
                    <td width="9" height="15" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
                    <td background="/datacenter/util/imgs/center_form.gif"> 
                      <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"><strong>Frenaje 
                        exigido sistema ABS</strong></font></div></td>
                    <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
                  </tr>
                  <tr> 
                  	<%
						str1 = "generic.activity.form.btw1liviano.brakingABS";
						str2 = "generic.activity.form.btw1liviano.brakingABS.ignoranceabssystem";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Desconociemiento del sistema: Concepto y funcionamiento</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                  	<%
						str1 = "generic.activity.form.btw1liviano.brakingABS";
						str2 = "generic.activity.form.btw1liviano.brakingABS.ignorancevehiclesystem";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}	
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Desconociemiento del sistema incluido en su veh&iacute;culo</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                  	<%
						str1 = "generic.activity.form.btw1liviano.brakingABS";
						str2 = "generic.activity.form.btw1liviano.brakingABS.pressesfirstclutch";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}	
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Oprime primeiro el embrague/cluch antes que el freno</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                  	<%
						str1 = "generic.activity.form.btw1liviano.brakingABS";
						str2 = "generic.activity.form.btw1liviano.brakingABS.powerlack";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Falta potencia sobre el pedal del freno</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                  	<%
						str1 = "generic.activity.form.btw1liviano.brakingABS";
						str2 = "generic.activity.form.btw1liviano.brakingABS.freesthebrake";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}	
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Tendencia a liberar el freno/falta de permanencia del pie 
                      sobre el pedal del freno</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                  	<%
						str1 = "generic.activity.form.btw1liviano.brakingABS";
						str2 = "generic.activity.form.btw1liviano.brakingABS.coachesalternativetechniques";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}	
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Entrena en las opciones de frenaje ante fallas del sistema 
                      y/o falta de control</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                  	<%

						str1 = "generic.activity.form.btw1liviano.brakingABS";
						str2 = "generic.activity.form.btw1liviano.brakingABS.satisfactory";	
						str3 = "generic.activity.form.btw1liviano.brakingABS.notsatisfactory";
						String checkedStr1 = "";
						String checkedStr2 = "";				
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr1 = "checked"; 
						}								
						if (itemsResult.contains(activityReport.getFormItem(str1,str3,sys.getUserSessionData()))) {
							checkedStr2 = "checked"; 
						}												
					
					%>						
  
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td bgcolor="#EEEEEE"><font size="1" face="Arial, Helvetica, sans-serif"> 
        				<INPUT type="radio" name="optradio1" value='<%= activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' <%= checkedStr1%> class="check" >                       
                      Alcanza Nivel Satisfactorio</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                  
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td bgcolor="#EEEEEE"><font size="1" face="Arial, Helvetica, sans-serif"> 
			           <INPUT type="radio" name="optradio1" value='<%= activityReport.getFormItem(str1,str3,sys.getUserSessionData()).getId().toString() %>' <%= checkedStr2%> class="check">                       
                      No alcanza nivel satisfatorio</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
                    <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
                  </tr>
                </table>
                <table width="215" border="0" cellpadding="0" cellspacing="0">
                  <tr> 
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
                    <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><strong><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif">Reacciones 
                        de aceleraci&oacute;n</font></strong></div></td>
                    <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
                  </tr>
                  <tr> 
                  	<%
						str1 = "generic.activity.form.btw1liviano.accelerationreaction";
						str2 = "generic.activity.form.btw1liviano.accelerationreaction.eliminate";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}												
					%>                    
                    <td width="9" height="20" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
					 <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Eliminaci&oacute;n de la reacci&oacute;n</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                  	<%
						str1 = "generic.activity.form.btw1liviano.accelerationreaction";
						str2 = "generic.activity.form.btw1liviano.accelerationreaction.partlyeliminate";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}	
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Elimina parcialmente reacci&oacute;n instintiva de aceleraci&oacute;n</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                  	<%
						str1 = "generic.activity.form.btw1liviano.accelerationreaction";
						str2 = "generic.activity.form.btw1liviano.accelerationreaction.noteliminate";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}								
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      No elimina la reacci&oacute;n</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr>                   
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
                    <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
                  </tr>
                </table>
                <table width="215" border="0" cellpadding="0" cellspacing="0">
                  <tr> 
                    <td width="9" height="15" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
                    <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"><strong>Velocidad</strong></font></div></td>
                    <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
                  </tr>
                  <tr> 
                  	<%
						str1 = "generic.activity.form.btw1liviano.velocity";
						str2 = "generic.activity.form.btw1liviano.velocity.tendency";						
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}								
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Tendencia a desarrollar velocidad inadecuada</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                  	<%
						str1 = "generic.activity.form.btw1liviano.velocity";
						str2 = "generic.activity.form.btw1liviano.velocity.indication";								
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Indicios de desarollar velocidad inadecuada</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
                    <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
                  </tr>
                </table></td>
              <td width="8"><img src="../../../../Carla/DC5/Nova%20pasta/tranp.jpg" width="1" height="1"></td>
              <td width="425" colspan="3" rowspan="33" valign="top"><strong></strong> 
                <div align="center"><strong></strong></div>
                <table width="215" border="0" cellpadding="0" cellspacing="0">
                  <tr> 
                    <td width="9"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
                    <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><strong><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif">Uso 
                        de pedalera y palanca de cambios</font></strong></div></td>
                    <td width="9"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.pedalgearshift";
						str2 = "generic.activity.form.btw1liviano.pedalgearshift.technicaluseclutch";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
						
					%>  
					                  
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Uso t&eacute;cnico del embrague/clutch</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.pedalgearshift";
						str2 = "generic.activity.form.btw1liviano.pedalgearshift.sensibilitywithclutch";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}	
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Sensibilidad con embrague/clutch</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.pedalgearshift";
						str2 = "generic.activity.form.btw1liviano.pedalgearshift.sensibilitywithbrake";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}	
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Sensibilidad con freno</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.pedalgearshift";
						str2 = "generic.activity.form.btw1liviano.pedalgearshift.sensibilitywithaccelerator";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Sensibilidad con acelerador</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.pedalgearshift";
						str2 = "generic.activity.form.btw1liviano.pedalgearshift.coordinationclutchgearshift";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Coordinaci&oacute;n embrague/clutch - palanca de cambios</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.pedalgearshift";
						str2 = "generic.activity.form.btw1liviano.pedalgearshift.coordinationclutchaccelerator";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Coordinaci&oacute;n embrague/clutch - acelerador</font></td>
                    <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                    <td width="9" height="19" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
                    <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
                  </tr>
                </table>
                <table width="215" border="0" cellpadding="0" cellspacing="0">
                  <tr> 
                    <td width="9" height="15" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
                    <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"><strong>Uso 
                        de caja de cambios/transmisi&oacute;n</strong></font></div></td>
                    <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.boxofchanges";
						str2 = "generic.activity.form.btw1liviano.boxofchanges.sensibilityerror";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Sensibilidad en cambios de marcha</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.boxofchanges";
						str2 = "generic.activity.form.btw1liviano.boxofchanges.distributionincorrect";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}	
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Circula en marchas incorrectas</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.boxofchanges";
						str2 = "generic.activity.form.btw1liviano.boxofchanges.incorrectuseofneuterpoint";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Incorrecto uso del punto neutro/muerto</font></td>
                    <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                    <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif">&nbsp; </font></td>
                    <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr>                   
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
                    <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
                  </tr>
                </table>
                <table width="215" border="0" cellpadding="0" cellspacing="0">
                  <tr>                   
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
                    <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><strong><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif">Esquive 
                        exigido</font></strong></div></td>
                    <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.avoidrequired";
						str2 = "generic.activity.form.btw1liviano.avoidrequired.wheelblocked";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Pretende esquivar manteniendo las ruedas bloqueadas </font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.avoidrequired";
						str2 = "generic.activity.form.btw1liviano.avoidrequired.brakecoordination";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Coordinaci&oacute;n freno-volante</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.avoidrequired";
						str2 = "generic.activity.form.btw1liviano.avoidrequired.undueaccelerationreaction";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Reacci&oacute;n indebida de aceleraci&oacute;n</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.avoidrequired";
						str2 = "generic.activity.form.btw1liviano.avoidrequired.dynamic";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Falta din&aacute;mica en el uso del volante durante la maniobra</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.avoidrequired";
						str2 = "generic.activity.form.btw1liviano.avoidrequired.brakingtechniqueapplication";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Comprendi&oacute; y aplic&oacute; satisfactoriamente t&eacute;cnica 
                      de frenaje durante la maniobra</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.avoidrequired";
						str2 = "generic.activity.form.btw1liviano.avoidrequired.notbrakingtechniqueapplication";													
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      No logr&oacute; aplicar satisfactoriamente t&eacute;cnica 
                      de frenaje durante la maniobra</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.avoidrequired";
						str2 = "generic.activity.form.btw1liviano.avoidrequired.coordinationandsensibilityerrors";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Errores generales de coordinaci&oacute;n y sensibilidad 
                      en el uso de los comandos</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr>        
					
                  	<%

						str1 = "generic.activity.form.btw1liviano.avoidrequired";
						str2 = "generic.activity.form.btw1liviano.avoidrequired.satisfactory";	
						str3 = "generic.activity.form.btw1liviano.avoidrequired.notsatisfactory";
						checkedStr1 = "";
						checkedStr2 = "";				
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr1 = "checked"; 
						}								
						if (itemsResult.contains(activityReport.getFormItem(str1,str3,sys.getUserSessionData()))) {
							checkedStr2 = "checked"; 
						}												
					
					%>									
					          
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td bgcolor="#EEEEEE"><font size="1" face="Arial, Helvetica, sans-serif"> 
       				<INPUT type="radio" name="optradio2" value='<%= activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' <%= checkedStr1%> class="check" >                       					 
                      Alcanza nivel satisfactorio</font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr>         
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td bgcolor="#EEEEEE"><font size="1" face="Arial, Helvetica, sans-serif"> 
			           <INPUT type="radio" name="optradio2" value='<%= activityReport.getFormItem(str1,str3,sys.getUserSessionData()).getId().toString() %>' <%= checkedStr2%> class="check">                                              
                      No alcanza nivel satisfactorio </font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
                    <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
                  </tr>
                </table>
                <table width="215" border="0" cellpadding="0" cellspacing="0">
                  <tr> 
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
                    <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><strong><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif">Tiempo 
                        de reacci&oacute;n</font></strong></div></td>
                    <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.reactiontime";
						str2 = "generic.activity.form.btw1liviano.reactiontime.correctsmaneuvererrors";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Errores iniciales al maniobrar </font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.reactiontime";
						str2 = "generic.activity.form.btw1liviano.reactiontime.correctstimeerrors";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							

					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Errores iniciales al frenar </font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.reactiontime";
						str2 = "generic.activity.form.btw1liviano.reactiontime.satisfactory";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Alcanza nivel satisfactorio </font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.reactiontime";
						str2 = "generic.activity.form.btw1liviano.reactiontime.increasesinitiallevel";					
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Incrementa su nivel inicial </font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.reactiontime";
						str2 = "generic.activity.form.btw1liviano.reactiontime.notsatisfactory";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      No logra nivel satisfactorio </font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.reactiontime";
						str2 = "generic.activity.form.btw1liviano.reactiontime.medicalappraisal";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
					 <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      Se sugiere valoraci&oacute;n m&eacute;dica </font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
                    <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
                  </tr>
                </table>
                <table width="215" border="0" cellpadding="0" cellspacing="0">
                  <tr>                  
                    <td width="9" height="15" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
                    <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><strong><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif">F&iacute;sica 
                        aplicada a la conducci&oacute;n</font></strong></div></td>
                    <td width="9" valign="top"><strong><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></strong></td>
                  </tr>
                  <tr> 
              		<%
						str1 = "generic.activity.form.btw1liviano.physicsapplied";
						str2 = "generic.activity.form.btw1liviano.physicsapplied.notappliestechniques";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>                    
                    <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                       <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>                       
                      No aplica satisfactoriamente las t&eacute;cnicas propuestas 
                      </font></td>
                    <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                    <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                    <td><font size="1" face="Arial, Helvetica, sans-serif">&nbsp; 
                      </font></td>
                    <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
                  </tr>
                  <tr> 
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
                    <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
                    <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
                  </tr> 
                </table>
                
              </td>
            </tr>
            <tr> 
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td width="23">&nbsp;</td>
              <td width="8">&nbsp;</td>
            </tr>
            <tr> 
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td height="19">&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td height="19">&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td height="19">&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td height="54">&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td height="19">&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td height="19">&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td height="19">&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td height="197">&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td height="19">&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td height="19">&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td height="19">&nbsp;</td>
              <td rowspan="6">&nbsp;</td>
            </tr>
            <tr> 
              <td height="19">&nbsp;</td>
            </tr>
            <tr> 
              <td height="19">&nbsp;</td>
            </tr>
            <tr> 
              <td height="19">&nbsp;</td>
            </tr>
            <tr> 
              <td height="19">&nbsp;</td>
            </tr>
            <tr> 
              <td height="19">&nbsp;</td>
            </tr>
            <tr> 
              <td colspan="3" valign="top"><div align="right"><font size="1" face="Arial, Helvetica, sans-serif"></font></div></td>
              <td>&nbsp;</td>
              <td colspan="6" rowspan="3" valign="top"><table width="435" border="0" cellpadding="0" cellspacing="0">
                  <tr> 
                    <td width="9" valign="top">&nbsp;</td>
                    <td><img src="../../../../Carla/DC5/Nova%20pasta/center_top_gray.gif" width="1" height="11"></td>
                    <td width="9" valign="top">&nbsp;</td>
                  </tr>
                  <tr> 
                    <td width="9">&nbsp;</td>
                    <td>&nbsp;</td>
                    <td width="9">&nbsp;</td>
                  </tr>
                  <tr> 
                    <td width="9" valign="top">&nbsp;</td>
                    <td><img src="../../../../Carla/DC5/Nova%20pasta/center_base_gray.gif" width="1" height="11"></td>
                    <td width="9">&nbsp;</td>
                  </tr>
                </table></td>
            </tr>
            <tr> 
              <td valign="top">&nbsp;</td>
              <td>&nbsp;</td>
              <td valign="top">&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr> 
              <td width="11" valign="top">&nbsp;</td>
              <td width="200">&nbsp;</td>
              <td width="1" valign="top">&nbsp;</td>
              <td width="23">&nbsp;</td>
            </tr>
                        
           <tr>
           <td class="input2">
			<strong>Instructor</strong>
			</td>
			<td><input type=text name="instructor" value = '<%= registryData.getInstructor() %>' class="input"></td>
			</tr>
			
			<tr> 
              <td height="19">&nbsp;</td>
            </tr>
            <tr> 
              <td height="19">&nbsp;</td>
            </tr>
            
			<tr>
		   	<td  align="right">
		   		<input name="button2" type="button"  value="Aceptar" onClick="javascript:setAction(document.forms[2],document.forms['ActivityRegistryForm']);" class="input"/>
	        </td>                 							
    	  </tr>  
            
          </table>
          
          <input type="hidden" name="checkboxParamList">                          
          
  </form></td>
    </tr>
  </table>
</div>


<html:form action = "ActivityRegistrycreate" method="post">
	<html:hidden property="idActivity" value='<%=idActivity%>' />
	<html:hidden property="idDriver" value='<%=idDriver%>' />
	<html:hidden property="url" value='<%=url%>' />
	<html:hidden property="checkboxParamList" value="checkboxParamList" />
	<html:hidden property="obs" value="obs" />	
	<html:hidden property="note" value="note" />	
	<html:hidden property="instructor" value="instructor" />		
	<html:hidden property="partner" value="partner" />	
	<html:hidden property="cepaCourse" value="cepaCourse" />	
	<html:hidden property="conductionType" value="conductionType" />				
</html:form>

</body>

</html:html>
