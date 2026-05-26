<%@page language="java" 
	errorPage="../../exception.jsp"
	import="business.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.activity.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.ActivityTypeDataType"
	import="business.activity.ActivityReportFacade"
	import="java.util.*"	
	import="business.datatype.*"	
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
	String str3;	
	String url;
	String idDriver;
	String idActivity;
	CEPAActivityScheduleData activityData;	
	
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");

	userSessionData=sys.getUserSessionData();
	
	String check = Util.checkPermission(sys,SecurityConstants.CREATE_CEPASCHEDULEACTIVITYREGISTRY);
	
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
		if (Util.empty(idActivity)){
	idActivity=(String)request.getParameter("id");
	if(idActivity==null){
		idActivity=(String)session.getAttribute("id");			
	}
		}
		
		//session.setAttribute("id",idActivity);
		
		idDriver = (String)request.getParameter("idDriver");
		//session.setAttribute("idDriver",idDriver);			
		
		//session.setAttribute("url","activity/btw2.jsp?idActivity="+idActivity+"&idDriver="+idDriver);
		url = "activity/btw2.jsp?idActivity="+idActivity+"&idDriver="+idDriver;
		userSessionData.setUrl(url);

		activityReport = sys.getActivityReportFacade();
		
		activityData = (CEPAActivityScheduleData) session.getAttribute("activityData");		
		
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
				if ((element.checked) 
				&& ((element.name !="checkboxasistence1")) && ((element.name !="checkboxasistence2"))
				&& ((element.name !="checkboxskidcar")) 
				&& ((element.name !="optradiofrenaje")) 
				&& ((element.name !="velocityBox"))
				&& (element.name != "phrd")					
				&& (!(element.disabled))){
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
		form2.plate.value = "";		    	
		form2.skidcar.value = form1.skidcar.value;	
		form2.frenaje.value = form1.frenaje.value;			
		
		form2.vehicleType.value = form1.vehicleType.value;	
		
		for (i=0;i<form1.velocityBox.length;i++){
	       if (form1.velocityBox[i].checked == true) {
    	      form2.velocityBox.value = form1.velocityBox[i].value;
        	  break;
        	}  
		}								    			
		
		form2.noteoricoasistence.value = form1.checkboxasistence1.checked;				
		form2.nopracticoasistence.value = form1.checkboxasistence2.checked;				
		form2.phrd.value = form1.phrd.checked;	

		// Refactoring:
		form2.idDriver.value = '<%=idDriver%>';
		form2.idActivity.value = '<%=idActivity%>';
		form2.url.value = '<%=url%>';
				
		form2.submit();
	}
}

function setSKIDCAR(form){

		if (form.checkboxskidcar.checked==true) { 
			form.skidcar.value = "true";
			form.checkboxskidcar1.disabled=false;	
			form.checkboxskidcar2.disabled=false;	
			form.checkboxskidcar3.disabled=false;	
			form.checkboxskidcar4.disabled=false;	
			form.checkboxskidcar5.disabled=false;	
			form.checkboxskidcar6.disabled=false;	
			form.checkboxskidcar7.disabled=false;																			
		}
		else {
			form.skidcar.value = "false";
			form.checkboxskidcar1.disabled=true;	
			form.checkboxskidcar2.disabled=true;	
			form.checkboxskidcar3.disabled=true;	
			form.checkboxskidcar4.disabled=true;	
			form.checkboxskidcar5.disabled=true;	
			form.checkboxskidcar6.disabled=true;	
			form.checkboxskidcar7.disabled=true;				
		}				    			
}

function deshabilitoABS(form){
		form.frenaje.value = "convencional";
		form.checkboxconvencional1.disabled=false;	
		form.checkboxconvencional2.disabled=false;	
		form.checkboxconvencional3.disabled=false;	
		form.checkboxabs1.disabled=true;	
		form.checkboxabs2.disabled=true;	
		form.checkboxabs3.disabled=true;	
		form.checkboxabs4.disabled=true;	
		form.checkboxabs5.disabled=true;	
		form.checkboxabs6.disabled=true;																			
}

function deshabilitoConvencional(form){
		form.frenaje.value = "abs";
		form.checkboxconvencional1.disabled=true;	
		form.checkboxconvencional2.disabled=true;	
		form.checkboxconvencional3.disabled=true;	
		form.checkboxabs1.disabled=false;	
		form.checkboxabs2.disabled=false;	
		form.checkboxabs3.disabled=false;	
		form.checkboxabs4.disabled=false;	
		form.checkboxabs5.disabled=false;	
		form.checkboxabs6.disabled=false;																			
}

		
</script>

<body>
<div align="center"> 

<form name="form2" method="post" action="">
    <table width="600" border="0" cellspacing="0" cellpadding="0">
      <tr> 
        <td height="40" colspan="5" valign="middle">
        <!-- Titulo del Formulario -->
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr> 
              <td width="9" height="40"><img src="/datacenter/util/imgs/titulo_left.jpg" width="9" height="40"></td>
              <td height="40" background="/datacenter/util/imgs/bg2.jpg"> <div align="center"><font color="#FFFFFF" size="4" face="Arial, Helvetica, sans-serif">
              <strong>
              <%=messages.getText(activityData.getDefinition().getResourceKeyName())%>              
              </strong></font></div></td>
              <td width="9" height="40"><img src="/datacenter/util/imgs/titulo_right.jpg" width="9" height="40"></td>
            </tr>
        </table>
        </td>
      </tr>  
      <tr><td>  &nbsp;</td></tr> 
      <!-- Formulario -->
      <tr> 
        <td colspan="1" valign="top"> 
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
           
    	    <tr>
    	    	<td>&nbsp;</td>
    	    </tr>
            <tr> 
              <td width="9"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                  <strong><%= messages.getText("generic.activity.asistence")%></strong></font></div></td>
              <td width="9"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <input type="checkbox" name="checkboxasistence1" class="check"> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.asistence.noteorico")%></font> 
              </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <input type="checkbox" name="checkboxasistence2" class="check"> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.asistence.nopractico")%></font> 
              </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>            
            
            <tr> 
              <td width="9" height="19" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
              <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
              <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
            </tr>
          </table>            
		</td>
		<td colspan="1" valign="top">&nbsp;</td>
		<!-- phrd: -->
		 <td colspan="3" valign="top"> 
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="9"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                  <strong><%= messages.getText("generic.activity.phrdbycourse")%></strong></font></div></td>
              <td width="9"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <input type="checkbox" name="phrd" class="check"  > 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.phrdbycourse.isphrd")%></font> 
              </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" height="19" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
              <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
              <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
            </tr>
            <tr><td>&nbsp;</td></tr>
          </table>            
		</td>
	</tr>

      <tr> 
<!-- Tipo de vehiculo --> 
<td width="200" valign="top"> 	
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<!--  Titulo del Punto: -->
	<tr> 
    	<td width="9" valign="top">
    		<img src="/datacenter/util/imgs/left_form.gif" >
    	</td>    	
        <td background="/datacenter/util/imgs/center_form.gif" valign="top"> 
        	<div align="center">
        		<!--  Cambiar esto por un estilo !! -->
        		<font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"><strong>
        		<%=messages.getText("generic.activity.form.tranningonthejob.vehicletype")%>
        		</strong></font>
        	</div>
        </td>
        <td width="9" valign="top">
        	<img src="/datacenter/util/imgs/right_form.gif" >        	
        </td>
     </tr>
	
   
	<!--  Dibujo los items del punto: -->
	 <!--  Dibujo Item: -->
     <tr> 
     
        <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;
        </td>
        <td>        
		<font size="1" face="Arial, Helvetica, sans-serif">         
		<select name="vehicleType" class="input">
		<% 
		DataTypeReportFacade dtReportFacade = sys.getDataTypeReportFacade();		
		String value = messages.getText("generic.messages.select");
		%>
        <option VALUE='<%= value%>' class="input"><%= messages.getText(value)%></option>		
        <%		
		Iterator iter6 = dtReportFacade.findAll(DataTypeConstants.TYPEVEHICLE,userSessionData).iterator();
		while(iter6.hasNext()) {
			value = ((TypeVehicleDataType)iter6.next()).getResourceBundleId();
			if (!value.equals("datatype.vehicletype.all")) {
		%>	
           <option VALUE='<%= value%>' class="input"><%= messages.getText(value)%></option>
	    <%  
			}
		}		
		%>		
        </select> 	    				
        </font> 
        </td>
        <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;
        </td>
     </tr>
        

	 <!--  Dibujo linea inferior de los cuadrantes -->
 	 <tr> 
        <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
        <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
        <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
     </tr>
</table>
</td>
		
<td valign="top">&nbsp;</td>
<td colspan="3" width="200" valign="top">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<!--  Titulo del Punto: -->
	<tr> 
    	<td width="9" valign="top">
    		<img src="/datacenter/util/imgs/left_form.gif" >
    	</td>    	
        <td colspan= "2" background="/datacenter/util/imgs/center_form.gif" valign="top"> 
        	<div align="center">
        		<!--  Cambiar esto por un estilo !! -->
        		<font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"><strong>
        		<%=messages.getText("generic.activity.form.tranningonthejob.velocitybox")%>
        		</strong></font>
        	</div>
        </td>
        <td width="9" valign="top">
        	<img src="/datacenter/util/imgs/right_form.gif" >        	
        </td>
     </tr>
	
   
	<!--  Dibujo los items del punto: -->
	 <!--  Dibujo Item: -->
     <tr> 
        
        <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;
        </td>
        <td >        
        <INPUT type="radio" name="velocityBox" value="generic.activity.form.tranningonthejob.velocitybox.manual" >	    				
        <font size="1" face="Arial, Helvetica, sans-serif"> 
        <%= messages.getText("generic.activity.form.tranningonthejob.velocitybox.manual")%>
        </font> 
        </td>
        <td align="right">        
        <font size="1" face="Arial, Helvetica, sans-serif">         
             <INPUT type="radio" name="velocityBox" value="generic.activity.form.tranningonthejob.velocitybox.automatic">	    				
        <font size="1" face="Arial, Helvetica, sans-serif"> 
        <%= messages.getText("generic.activity.form.tranningonthejob.velocitybox.automatic")%>
        </font> 
        </td>
        <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;
        </td>
     </tr>       

	 <!--  Dibujo linea inferior de los cuadrantes -->
 	 <tr> 
        <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
        <td colspan= "2" background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
        <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
     </tr>
</table>
</td>
</tr> 

      <tr> 
        <td width="200" valign="top">
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="9"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td valign="top" background="/datacenter/util/imgs/center_form.gif"> 
                <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                  <strong><%= messages.getText("generic.activity.form.btw2liviano.position")%></strong></font></div></td>
              <td width="9"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.position";
						str2 = "generic.activity.form.btw2liviano.position.pedalnext";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check"> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw2liviano.position.pedalnext")%></font> 
              </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.position";
						str2 = "generic.activity.form.btw2liviano.position.steeringwheelnext";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <input type="checkbox" name="checkbox222" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check"> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw2liviano.position.steeringwheelnext")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.position";
						str2 = "generic.activity.form.btw2liviano.position.pedalfar";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <input type="checkbox" name="checkbox222" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check"> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw2liviano.position.pedalfar")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.position";
						str2 = "generic.activity.form.btw2liviano.position.steeringwheelfar";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <input type="checkbox" name="checkbox222" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check"> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw2liviano.position.steeringwheelfar")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.position";
						str2 = "generic.activity.form.btw2liviano.position.steeringwheelhands";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <input type="checkbox" name="checkbox222" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check"> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw2liviano.position.steeringwheelhands")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.position";
						str2 = "generic.activity.form.btw2liviano.position.onehand";						
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <input type="checkbox" name="checkbox222" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check"> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw2liviano.position.onehand")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" height="19" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
              <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
              <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
            </tr>
          </table>
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="9" height="15" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                  <strong> <%= messages.getText("generic.activity.form.btw2liviano.parkinginrightangle")%> 
                  </strong></font></div></td>
              <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw2liviano.reversemaneuver.asistence")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.parkinginrightangle"; 
						str2 = "generic.activity.form.btw2liviano.parkinginrightangle.signal";						
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.parkinginrightangle.signal")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.parkinginrightangle";
						str2 = "generic.activity.form.btw2liviano.parkinginrightangle.spaceandpositionbeforemaneuver";						

					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.parkinginrightangle.spaceandpositionbeforemaneuver")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.parkinginrightangle";
						str2 = "generic.activity.form.btw2liviano.parkinginrightangle.referencesearch";						


					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check"> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw2liviano.parkinginrightangle.referencesearch")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.parkinginrightangle";
						str2 = "generic.activity.form.btw2liviano.parkinginrightangle.referenceuse";						

					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.parkinginrightangle.referenceuse")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.parkinginrightangle";
						str2 = "generic.activity.form.btw2liviano.parkinginrightangle.possibilityevaluation";						

					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.parkinginrightangle.possibilityevaluation")%> 
                </font></td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.parkinginrightangle";
						str2 = "generic.activity.form.btw2liviano.parkinginrightangle.techniqueapply";						

					%>
              <td height="19" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check"> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw2liviano.parkinginrightangle.techniqueapply")%> 
                </font></td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.parkinginrightangle";
						str2 = "generic.activity.form.btw2liviano.parkinginrightangle.generalobservation";						
						
					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check"> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw2liviano.parkinginrightangle.generalobservation")%> 
                </font></td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.parkinginrightangle";
						str2 = "generic.activity.form.btw2liviano.parkinginrightangle.mirroruse";						
			%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.parkinginrightangle.mirroruse")%> 
                </font></td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.parkinginrightangle";
						str2 = "generic.activity.form.btw2liviano.parkinginrightangle.blindpointcontrol";						

					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.parkinginrightangle.blindpointcontrol")%> 
                </font></td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.parkinginrightangle";
						str2 = "generic.activity.form.btw2liviano.parkinginrightangle.sensibilitywithcommands";						

					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.parkinginrightangle.sensibilitywithcommands")%> 
                </font></td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.parkinginrightangle";
						str2 = "generic.activity.form.btw2liviano.parkinginrightangle.commandcoordination";						

					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.parkinginrightangle.commandcoordination")%> 
                </font></td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.parkinginrightangle";
						str2 = "generic.activity.form.btw2liviano.parkinginrightangle.velocitycontrol";						

					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.parkinginrightangle.velocitycontrol")%> 
                </font></td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.parkinginrightangle";
						str2 = "generic.activity.form.btw2liviano.parkinginrightangle.limitedmaneuver";																																																																							
					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.parkinginrightangle.limitedmaneuver")%> 
                </font></td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
              <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
              <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
            </tr>
          </table>
          
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="9" height="15" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                  <strong> <%= messages.getText("generic.activity.form.btw2liviano.reversemaneuver")%> 
                  </strong></font></div></td>
              <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw2liviano.reversemaneuver.asistence")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.reversemaneuver"; 
						str2 = "generic.activity.form.btw2liviano.reversemaneuver.signal";						
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.reversemaneuver.signal")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.reversemaneuver";
						str2 = "generic.activity.form.btw2liviano.reversemaneuver.spaceandpositionbeforemaneuver";						

					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.reversemaneuver.spaceandpositionbeforemaneuver")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.reversemaneuver";
						str2 = "generic.activity.form.btw2liviano.reversemaneuver.referencesearch";						


					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check"> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw2liviano.reversemaneuver.referencesearch")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.reversemaneuver";
						str2 = "generic.activity.form.btw2liviano.reversemaneuver.referenceuse";						

					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.reversemaneuver.referenceuse")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.reversemaneuver";
						str2 = "generic.activity.form.btw2liviano.reversemaneuver.possibilityevaluation";						

					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.reversemaneuver.possibilityevaluation")%> 
                </font></td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.reversemaneuver";
						str2 = "generic.activity.form.btw2liviano.reversemaneuver.techniqueapply";						

					%>
              <td height="19" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check"> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw2liviano.reversemaneuver.techniqueapply")%> 
                </font></td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.reversemaneuver";
						str2 = "generic.activity.form.btw2liviano.reversemaneuver.generalobservation";						
						
					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check"> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw2liviano.reversemaneuver.generalobservation")%> 
                </font></td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.reversemaneuver";
						str2 = "generic.activity.form.btw2liviano.reversemaneuver.mirroruse";						
			%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.reversemaneuver.mirroruse")%> 
                </font></td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.reversemaneuver";
						str2 = "generic.activity.form.btw2liviano.reversemaneuver.blindpointcontrol";						

					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.reversemaneuver.blindpointcontrol")%> 
                </font></td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.reversemaneuver";
						str2 = "generic.activity.form.btw2liviano.reversemaneuver.sensibilitywithcommands";						

					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.reversemaneuver.sensibilitywithcommands")%> 
                </font></td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.reversemaneuver";
						str2 = "generic.activity.form.btw2liviano.reversemaneuver.commandcoordination";						

					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.reversemaneuver.commandcoordination")%> 
                </font></td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.reversemaneuver";
						str2 = "generic.activity.form.btw2liviano.reversemaneuver.velocitycontrol";						

					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.reversemaneuver.velocitycontrol")%> 
                </font></td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.reversemaneuver";
						str2 = "generic.activity.form.btw2liviano.reversemaneuver.limitedmaneuver";																																																																							
					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox223" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.reversemaneuver.limitedmaneuver")%> 
                </font></td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
              <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
              <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
            </tr>
          </table>
          
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="9" height="15" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                  <strong> <%= messages.getText("generic.activity.form.btw2liviano.velocity")%> 
                  </strong></font></div></td>
              <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.velocity";
						str2 = "generic.activity.form.btw2liviano.velocity.tendency";						
							
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.velocity.tendency")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.velocity";
						str2 = "generic.activity.form.btw2liviano.velocity.indication";								
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.velocity.indication")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" height="19" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
              <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
              <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
            </tr>
          </table>
		</td>
        <td width="3" valign="top"><img src="../btw2/imagens/tranp.jpg" width="1" height="1"> 
        </td>
        <td width="200" valign="top"> <div align="center">         
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr> 
                <td width="9"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
                <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><strong><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                    <%= messages.getText("generic.activity.form.btw2liviano.mecanicarms")%> 
                    </font></strong></div></td>
                <td width="9"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw2liviano.mecanicarms";
						str2 = "generic.activity.form.btw2liviano.mecanicarms.onehand";
					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                  <%= messages.getText("generic.activity.form.btw2liviano.mecanicarms.onehand")%> 
                  </font></td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw2liviano.mecanicarms";
						str2 = "generic.activity.form.btw2liviano.mecanicarms.openhands";	
					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                  <%= messages.getText("generic.activity.form.btw2liviano.mecanicarms.openhands")%> 
                  </font></td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw2liviano.mecanicarms";
						str2 = "generic.activity.form.btw2liviano.mecanicarms.steeringwheelinside";	
					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                  <%= messages.getText("generic.activity.form.btw2liviano.mecanicarms.steeringwheelinside")%> 
                  </font></td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw2liviano.mecanicarms";
						str2 = "generic.activity.form.btw2liviano.mecanicarms.bottomlocked";	
					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                  <%= messages.getText("generic.activity.form.btw2liviano.mecanicarms.bottomlocked")%> 
                  </font></td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw2liviano.mecanicarms";
						str2 = "generic.activity.form.btw2liviano.mecanicarms.dinamic";	
					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                  <%= messages.getText("generic.activity.form.btw2liviano.mecanicarms.dinamic")%> 
                  </font></td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw2liviano.mecanicarms";
						str2 = "generic.activity.form.btw2liviano.mecanicarms.limitedmoviments";	
					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                  <%= messages.getText("generic.activity.form.btw2liviano.mecanicarms.limitedmoviments")%> 
                  </font></td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw2liviano.mecanicarms";
						str2 = "generic.activity.form.btw2liviano.mecanicarms.specialwork";	
					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                  <%= messages.getText("generic.activity.form.btw2liviano.mecanicarms.specialwork")%> 
                  </font></td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw2liviano.mecanicarms";
						str2 = "generic.activity.form.btw2liviano.mecanicarms.anticipation";	
					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                  <%= messages.getText("generic.activity.form.btw2liviano.mecanicarms.anticipation")%> 
                  </font></td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <td width="9" height="19" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
                <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
                <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
              </tr>
            </table>
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr> 
                <td width="9" height="15" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
                <td width="632" background="/datacenter/util/imgs/center_form.gif"> 
                  <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                    <strong> <%= messages.getText("generic.activity.form.btw2liviano.braking")%> 
                    </strong></font></div></td>
                <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
              </tr>
              
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> 
                  <INPUT type="radio" name="optradiofrenaje" class="check" checked onClick="javascript:deshabilitoABS(document.forms['form2']);">                            
              <font size="1" face="Arial, Helvetica, sans-serif"> <B>Frenaje Convencional: </B>
              </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>              
              
              <tr> 
                <%
						str1 = "generic.activity.form.btw2liviano.requiredbraking";
						str2 = "generic.activity.form.btw2liviano.requiredbraking.incorrecttechnique";							
					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td width="632"> <input type="checkbox" name="checkboxconvencional1" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" > 
                  <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw2liviano.requiredbraking.incorrecttechnique")%> 
                  </font></td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw2liviano.requiredbraking";
						str2 = "generic.activity.form.btw2liviano.requiredbraking.pressesfirstclutch";	

					%>
                <td width="9" height="21" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td width="632"> <input type="checkbox" name="checkboxconvencional2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check"> 
                  <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw2liviano.requiredbraking.pressesfirstclutch")%> 
                  </font> </td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr>  
                <%
						str1 = "generic.activity.form.btw2liviano.requiredbraking";
						str2 = "generic.activity.form.btw2liviano.requiredbraking.avoidwithoutcontrollingvelocity";														
					%>
                <td height="27" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td> <input type="checkbox" name="checkboxconvencional3" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" > 
                  <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw2liviano.requiredbraking.avoidwithoutcontrollingvelocity")%> 
                  </font></td>
                <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>

            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> 
                  <INPUT type="radio" name="optradiofrenaje" class="check" onClick="javascript:deshabilitoConvencional(document.forms['form2']);">                            
              <font size="1" face="Arial, Helvetica, sans-serif"> <B>Frenaje ABS: </B>
              </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>              
              
              <tr> 
                <%
						str1 = "generic.activity.form.btw2liviano.brakingABS";
						str2 = "generic.activity.form.btw2liviano.brakingABS.ignoranceabssystem";	
						
					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                  <input type="checkbox" name="checkboxabs1" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" disabled>
                  <%= messages.getText("generic.activity.form.btw2liviano.brakingABS.ignoranceabssystem")%> 
                  </font></td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw2liviano.brakingABS";
						str2 = "generic.activity.form.btw2liviano.brakingABS.ignorancevehiclesystem";	

					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                  <input type="checkbox" name="checkboxabs2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" disabled>
                  <%= messages.getText("generic.activity.form.btw2liviano.brakingABS.ignorancevehiclesystem")%> 
                  </font></td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw2liviano.brakingABS";
						str2 = "generic.activity.form.btw2liviano.brakingABS.pressesfirstclutch";	

					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                  <input type="checkbox" name="checkboxabs3" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" disabled>
                  <%= messages.getText("generic.activity.form.btw2liviano.brakingABS.pressesfirstclutch")%> 
                  </font></td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw2liviano.brakingABS";
						str2 = "generic.activity.form.btw2liviano.brakingABS.powerlack";	

					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                  <input type="checkbox" name="checkboxabs4" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" disabled>
                  <%= messages.getText("generic.activity.form.btw2liviano.brakingABS.powerlack")%> 
                  </font></td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw2liviano.brakingABS";
						str2 = "generic.activity.form.btw2liviano.brakingABS.freesthebrake";	

					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                  <input type="checkbox" name="checkboxabs5" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" disabled>
                  <%= messages.getText("generic.activity.form.btw2liviano.brakingABS.freesthebrake")%> 
                  </font></td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw2liviano.brakingABS";
						str2 = "generic.activity.form.btw2liviano.brakingABS.coachesalternativetechniques";	

					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                  <input type="checkbox" name="checkboxabs6" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" disabled>
                  <%= messages.getText("generic.activity.form.btw2liviano.brakingABS.coachesalternativetechniques")%> 
                  </font></td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>

              <tr> 
                <td width="9" height="19" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
                <td width="632" background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
                <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
              </tr>
            </table>

            <table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr> 
                <td width="9" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
                <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><strong><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                    <%= messages.getText("generic.activity.form.btw2liviano.accelerationreaction")%> 
                    </font></strong></div></td>
                <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw2liviano.accelerationreaction";
						str2 = "generic.activity.form.btw2liviano.accelerationreaction.eliminate";	
											
					%>
                <td width="9" height="20" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                  <%= messages.getText("generic.activity.form.btw2liviano.accelerationreaction.eliminate")%> 
                  </font></td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw2liviano.accelerationreaction";
						str2 = "generic.activity.form.btw2liviano.accelerationreaction.partlyeliminate";	

					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                  <%= messages.getText("generic.activity.form.btw2liviano.accelerationreaction.partlyeliminate")%> 
                  </font></td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw2liviano.accelerationreaction";
						str2 = "generic.activity.form.btw2liviano.accelerationreaction.noteliminate";	
							
					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                  <%= messages.getText("generic.activity.form.btw2liviano.accelerationreaction.noteliminate")%> 
                  </font></td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
                <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
                <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
              </tr>
            </table>
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr> 
                <td width="9" height="15" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
                <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><strong><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                    <%= messages.getText("generic.activity.form.btw2liviano.physicsapplied")%> 
                    </font></strong></div></td>
                <td width="9" valign="top"><strong><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></strong></td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw2liviano.physicsapplied";
						str2 = "generic.activity.form.btw2liviano.physicsapplied.notappliestechniques";	
											
					%>
                <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                  <%= messages.getText("generic.activity.form.btw2liviano.physicsapplied.notappliestechniques")%> 
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
            
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="9" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                  <strong> <%= messages.getText("generic.activity.form.btw2liviano.mirrors")%> 
                  </strong></font></div></td>
              <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.mirrors";
						str2 = "generic.activity.form.btw2liviano.mirrors.beacon";																																																																							
						
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox224" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.mirrors.beacon")%> 
                </font></td>
              <td width="9" valign="top" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.mirrors";
						str2 = "generic.activity.form.btw2liviano.mirrors.signal";																																																																							
				
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox224" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.mirrors.signal")%> 
                </font></td>
              <td width="9" valign="top" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.mirrors";
						str2 = "generic.activity.form.btw2liviano.mirrors.left";																																																																							
				
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox224" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.mirrors.left")%> 
                </font></td>
              <td width="9" valign="top" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.mirrors";
						str2 = "generic.activity.form.btw2liviano.mirrors.center";																																																																							
						

				
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox224" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.mirrors.center")%> 
                </font></td>
              <td width="9" valign="top" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.mirrors";
						str2 = "generic.activity.form.btw2liviano.mirrors.right";																																																																							

						
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox224" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.mirrors.right")%> 
                </font></td>
              <td width="9" valign="top" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.mirrors";
						str2 = "generic.activity.form.btw2liviano.mirrors.blindpoint";																																																																							
				
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox224" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.mirrors.blindpoint")%> 
                </font></td>
              <td width="9" valign="top" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" height="19" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
              <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
              <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
            </tr>
          </table>

          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="9" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><strong><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                  <%= messages.getText("generic.activity.form.btw2liviano.reactiontime")%> 
                  </font></strong></div></td>
              <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.reactiontime";
						str2 = "generic.activity.form.btw2liviano.reactiontime.correctsmaneuvererrors";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.reactiontime.correctsmaneuvererrors")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.reactiontime";
						str2 = "generic.activity.form.btw2liviano.reactiontime.correctstimeerrors";	

					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.reactiontime.correctstimeerrors")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.reactiontime";
						str2 = "generic.activity.form.btw2liviano.reactiontime.satisfactory";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.reactiontime.satisfactory")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.reactiontime";
						str2 = "generic.activity.form.btw2liviano.reactiontime.notsatisfactory";	
			  %>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.reactiontime.notsatisfactory")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.reactiontime";
						str2 = "generic.activity.form.btw2liviano.reactiontime.medicalappraisal";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.reactiontime.medicalappraisal")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
              <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
              <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
            </tr>
          </table>

            
          </div></td>
        <td>&nbsp;</td>
        <td width="200" valign="top">
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="9" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><strong><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                  <%= messages.getText("generic.activity.form.btw2liviano.pedalgearshift1")%> 
                  </font></strong></div></td>
              <td width="9"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.pedalgearshift";
						str2 = "generic.activity.form.btw2liviano.pedalgearshift.technicaluseclutch";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.pedalgearshift.technicaluseclutch")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.pedalgearshift";
						str2 = "generic.activity.form.btw2liviano.pedalgearshift.sensibilitywithclutch";	

					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.pedalgearshift.sensibilitywithclutch")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.pedalgearshift";
						str2 = "generic.activity.form.btw2liviano.pedalgearshift.sensibilitywithbrake";	

					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.pedalgearshift.sensibilitywithbrake")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.pedalgearshift";
						str2 = "generic.activity.form.btw2liviano.pedalgearshift.sensibilitywithaccelerator";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.pedalgearshift.sensibilitywithaccelerator")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.pedalgearshift";
						str2 = "generic.activity.form.btw2liviano.pedalgearshift.coordinationclutchgearshift";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.pedalgearshift.coordinationclutchgearshift")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.pedalgearshift";
						str2 = "generic.activity.form.btw2liviano.pedalgearshift.coordinationclutchaccelerator";	
					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.pedalgearshift.coordinationclutchaccelerator")%> 
                </font></td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" height="19" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
              <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
              <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
            </tr>
          </table>
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="9" height="15" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"><strong> 
                  <%= messages.getText("generic.activity.form.btw2liviano.boxofchanges1")%> 
                  </strong></font></div></td>
              <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.boxofchanges";
						str2 = "generic.activity.form.btw2liviano.boxofchanges.sensibilityerror";	
						
					
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.boxofchanges.sensibilityerror")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.boxofchanges";
						str2 = "generic.activity.form.btw2liviano.boxofchanges.distributionincorrect";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.boxofchanges.distributionincorrect")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.boxofchanges";
						str2 = "generic.activity.form.btw2liviano.boxofchanges.incorrectuseofneuterpoint";	
					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.boxofchanges.incorrectuseofneuterpoint")%> 
                </font></td>
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
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="9" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><strong><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                  <%= messages.getText("generic.activity.form.btw2liviano.avoidrequired")%> 
                  </font></strong></div></td>
              <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.avoidrequired";
						str2 = "generic.activity.form.btw2liviano.avoidrequired.wheelblocked";	
						
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.avoidrequired.wheelblocked")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.avoidrequired";
						str2 = "generic.activity.form.btw2liviano.avoidrequired.brakecoordination";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.avoidrequired.brakecoordination")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.avoidrequired";
						str2 = "generic.activity.form.btw2liviano.avoidrequired.undueaccelerationreaction";	

					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.avoidrequired.undueaccelerationreaction")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.avoidrequired";
						str2 = "generic.activity.form.btw2liviano.avoidrequired.dynamic";	

					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.avoidrequired.dynamic")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.avoidrequired";
						str2 = "generic.activity.form.btw2liviano.avoidrequired.brakingtechniqueapplication";	

					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.avoidrequired.brakingtechniqueapplication")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.avoidrequired";
						str2 = "generic.activity.form.btw2liviano.avoidrequired.notbrakingtechniqueapplication";	
												
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.avoidrequired.notbrakingtechniqueapplication")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.avoidrequired";
						str2 = "generic.activity.form.btw2liviano.avoidrequired.coordinationandsensibilityerrors";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.avoidrequired.coordinationandsensibilityerrors")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
              <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
              <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
            </tr>
          </table>

          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="9" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><strong><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                  <%= messages.getText("generic.activity.form.btw2liviano.escaperoutes")%> 
                  </font></strong></div></td>
              <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.escaperoutes";
						str2 = "generic.activity.form.btw2liviano.escaperoutes.reactiontime";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.escaperoutes.reactiontime")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.escaperoutes";
						str2 = "generic.activity.form.btw2liviano.escaperoutes.velocitycontrol";	

					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.escaperoutes.velocitycontrol")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.escaperoutes";
						str2 = "generic.activity.form.btw2liviano.escaperoutes.coordination";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.escaperoutes.coordination")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.escaperoutes";
						str2 = "generic.activity.form.btw2liviano.escaperoutes.anticipation";					

					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.escaperoutes.anticipation")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.escaperoutes";
						str2 = "generic.activity.form.btw2liviano.escaperoutes.pedal";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.escaperoutes.pedal")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
              <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
              <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
            </tr>
          </table>
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="9" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><strong><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                  <%= messages.getText("generic.activity.form.btw2liviano.curve")%> 
                  </font></strong></div></td>
              <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.curve";
						str2 = "generic.activity.form.btw2liviano.curve.beforeentering";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.curve.beforeentering")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.curve";
						str2 = "generic.activity.form.btw2liviano.curve.velocityadaptation";	

					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.curve.velocityadaptation")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.curve";
						str2 = "generic.activity.form.btw2liviano.curve.perceptionandcontrol";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.curve.perceptionandcontrol")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.curve";
						str2 = "generic.activity.form.btw2liviano.curve.losscontrol";					

					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.curve.losscontrol")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.curve";
						str2 = "generic.activity.form.btw2liviano.curve.controlinexit";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw2liviano.curve.controlinexit")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
              <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
              <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
            </tr>
          </table>
        
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="9" height="15" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                  <strong> SKIDCAR </strong></font></div></td>
              <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.skidcar";
						str2 = "generic.activity.form.btw2liviano.skidcar.usa";						
							
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkboxskidcar" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" onClick="javascript:setSKIDCAR(document.forms['form2']);">
                <strong>
                <%= messages.getText("generic.activity.form.btw2liviano.skidcar.usa")%> 
                </strong>
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.skidcar";
						str2 = "generic.activity.form.btw2liviano.skidcar.pedalsensibility";								
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkboxskidcar1" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" disabled>
                <%= messages.getText("generic.activity.form.btw2liviano.skidcar.pedalsensibility")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.skidcar";
						str2 = "generic.activity.form.btw2liviano.skidcar.steeringwheelsensibility";						
							
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkboxskidcar2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" disabled>
                <%= messages.getText("generic.activity.form.btw2liviano.skidcar.steeringwheelsensibility")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.skidcar";
						str2 = "generic.activity.form.btw2liviano.skidcar.pedalusecoordination";						
							
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkboxskidcar3" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" disabled>
                <%= messages.getText("generic.activity.form.btw2liviano.skidcar.pedalusecoordination")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.skidcar";
						str2 = "generic.activity.form.btw2liviano.skidcar.steeringwheelpedalcoordination";						
							
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkboxskidcar4" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" disabled>
                <%= messages.getText("generic.activity.form.btw2liviano.skidcar.steeringwheelpedalcoordination")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.skidcar";
						str2 = "generic.activity.form.btw2liviano.skidcar.mecanicarmstechnique";						
							
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkboxskidcar5" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" disabled>
                <%= messages.getText("generic.activity.form.btw2liviano.skidcar.mecanicarmstechnique")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.skidcar";
						str2 = "generic.activity.form.btw2liviano.skidcar.mecanicarmsdynamic";						
							
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkboxskidcar6" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" disabled>
                <%= messages.getText("generic.activity.form.btw2liviano.skidcar.mecanicarmsdynamic")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw2liviano.skidcar";
						str2 = "generic.activity.form.btw2liviano.skidcar.mecanicarmsanticipation";						
							
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td><font size="1" face="Arial, Helvetica, sans-serif"> 
                <input type="checkbox" name="checkboxskidcar7" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" disabled>
                <%= messages.getText("generic.activity.form.btw2liviano.skidcar.mecanicarmsanticipation")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" height="19" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
              <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
              <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
            </tr>
          </table>        
        
          
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
              <td background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td align='center' bgcolor="#f9f9f9"><div align="left"><font size="1" face="Arial, Helvetica, sans-serif"><strong><%= messages.getText("generic.activity.note") %><strong></font></div></td>
              <td background="/datacenter/util/imgs/right_gray.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td bgcolor="#f9f9f9"> 
<%				if(sys.isCorporation(CorporationData.MONSANTO) 
					&& activityData.getType().getResourceBundleId().equals(CEPAActivityConstants.BTW2)
					&& (activityData.getCountryName().equals(CountryData.MEXICO)  || activityData.getCountryName().equals(CountryData.GUATEMALA)
							|| activityData.getCountryName().equals(CountryData.NICARAGUA) || activityData.getCountryName().equals(CountryData.COSTA_RICA))) {
%>             		<select name="note" class="input">
<%					for(int i = 1; i < 7; i++) {
%>               		<option value='<%=i%>' class="input"><%= messages.getText("activity.clasification.text"+i) %></option>
<%               	}
%>                	</select> 
<%				}else {
					DataTypeReportFacade dataTypeReportFacade = sys.getDataTypeReportFacade();		
        			Collection notes = dataTypeReportFacade.findAll(DataTypeConstants.TYPECEPAACTIVITYNOTE,userSessionData);
              		if (notes != null && notes.size() > 0){ 
%>             		<select name="note" class="input">
<%						Iterator it = notes.iterator();
              			while (it.hasNext()){
              				String activityNote = ((CEPAActivityNoteDataType) it.next()).getResourceBundleId();
%>               		<option value='<%= activityNote %>' class="input"><%= messages.getText(activityNote) %></option>
<%               		}
%>                	</select> 
<%                	}
				}
%>           	 </td>
              <td background="/datacenter/util/imgs/right_gray.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td valign="top" background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td bgcolor="#f9f9f9">&nbsp;</td>
              <td valign="top" background="/datacenter/util/imgs/right_gray.gif" bgcolor="#f9f9f9">&nbsp;</td>
            </tr>
            <tr> 
              <td background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td bgcolor="#f9f9f9"><font size="1" face="Arial, Helvetica, sans-serif"><strong><%= messages.getText("generic.activity.observations") %></strong></font></td>
              <td background="/datacenter/util/imgs/right_gray.gif" bgcolor="#f9f9f9">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" height="88" background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td bgcolor="#f9f9f9"><strong><font color="#FF0000" size="1" face="Arial, Helvetica, sans-serif"> 
                <textarea name="obs" cols="70" rows="5"></textarea>
                </font></strong></td>
              <td background="/datacenter/util/imgs/right_gray.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td valign="top" background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td bgcolor="#f9f9f9">&nbsp;</td>
              <td valign="top" background="/datacenter/util/imgs/right_gray.gif" bgcolor="#f9f9f9">&nbsp;</td>
            </tr>
            <tr> 
              <td background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td bgcolor="#f9f9f9"><font size="1" face="Arial, Helvetica, sans-serif"> 
                <strong><%= messages.getText("generic.activity.instructor")%></strong></font> 
                <input type=text name="instructor"> </td>
              <td background="/datacenter/util/imgs/right_gray.gif" bgcolor="#f9f9f9">&nbsp;</td>
            </tr>
            <tr> 
              <td height="11" valign="top"><img src="/datacenter/util/imgs/left_base_gray.gif" width="9" height="11"></td>
              <td valign="top" background="/datacenter/util/imgs/center_base_gray.gif"><img src="/datacenter/util/imgs/center_base_gray.gif" width="1" height="11"></td>
              <td valign="top"><img src="/datacenter/util/imgs/right_base_gray.gif" width="9" height="11"></td>
            </tr>
          </table></td>
      </tr>
      <tr> 
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr> 
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td><div align="right"> 
            <input name="button2" type="button"  value="Aceptar" onClick="javascript:setAction(document.forms['form2'],document.forms['ActivityRegistryForm']);" class="input" align="right"/>
          </div></td>
      </tr>
    </table>
    
        <input type="hidden" name="checkboxParamList"/>
        <input type="hidden" name="skidcar"/>
        <input type="hidden" name="plate" value=""/>
        <input type="hidden" name="frenaje" value ="convencional"/>        
        
  </form>
  
</div>

<html:form action = "ActivityRegistrycreate" method="post">
	<html:hidden property="idDriver" value='<%=idDriver%>' />
	<html:hidden property="idActivity" value='<%=idActivity%>' />
	<html:hidden property="url" value='<%=url%>' />	
	<html:hidden property="checkboxParamList" value="checkboxParamList" />
	<html:hidden property="obs" value="obs" />	
	<html:hidden property="note" value="note" />	
	<html:hidden property="instructor" value="instructor" />		
	<html:hidden property="partner" value="partner" />	
	<html:hidden property="cepaCourse" value="cepaCourse" />	
	<html:hidden property="conductionType" value="conductionType" />
	<html:hidden property="plate" value="" />			
	<html:hidden property="skidcar" value="skidcar" />				
	<html:hidden property="trade" value="" />	
	<html:hidden property="initialKm" value="-1" />
	<html:hidden property="finalKm" value="-1" />
	<html:hidden property="initialHr" />
	<html:hidden property="finalHr" />	
	<html:hidden property="initialMinute" />
	<html:hidden property="finalMinute" />										
	<html:hidden property="vehicleType" value="" />									
	<html:hidden property="circulationAreaHighWay" value="false" />
	<html:hidden property="circulationAreaRoad" value="false" />
	<html:hidden property="circulationAreaSubUrban" value="false" />
	<html:hidden property="circulationAreaUrban" value="false" />										
	<html:hidden property="velocityBox" value="" />											
	<html:hidden property="light" value="" />	
	<html:hidden property="pointCommentList" value="" />			
	<html:hidden property="frenaje" value="frenaje" />					
	<html:hidden property="novato" value="false" />		
	<html:hidden property="phrd" value="false" />			
	<html:hidden property="noteoricoasistence" value="false" />								
	<html:hidden property="nopracticoasistence" value="false" />															
</html:form>

</body>

</html:html>