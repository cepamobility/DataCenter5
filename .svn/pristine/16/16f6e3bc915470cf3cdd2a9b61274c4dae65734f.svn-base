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
	import="communication.util.Filter"
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
	String str4;		
	String str5;
	String url = "";		
	String checkedStr;	
	Collection itemsResult;	
	CEPAActivityScheduleRegistryData registryData;	
	String checkedConvencionalStr;										
	String checkedABSStr;											
	String disabledABSStr;	
	String disabledConvencionalStr;		
	boolean frenajeConvencional;	
	String valorFrenaje;	
	String valorNovato = "false";		
	String checkedNovatoStr = "";											
	String disabledNovatoStr = "";		
	boolean novato = false;
	String checkVel1 = "";
	String checkVel2 = "";	
	String checkedNoTeoricoAsistenceStr;			
	String checkedNoPracticoAsistenceStr;				
	boolean noTeoricoAsistence;
	boolean noPracticoAsistence;
	boolean phrdChecked;
	String checkedPhrd = "";
	CEPAActivityScheduleData activityData;	
	String idActivity;
	String idDriver;
	String check;
	
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");

	userSessionData=sys.getUserSessionData();
	
	check = Util.checkPermission(sys,SecurityConstants.EDIT_CEPASCHEDULEACTIVITYREGISTRY); 
	
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		messages=sys.getText();

		pathLanguage="/util/jsp/language.jsp";
	
		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
		session.removeAttribute("ActivityRegistryForm");
		session.removeAttribute("form2");
		
		context=request.getContextPath();
		pathMsg="/util/jsp/message.jsp";
	
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);

		idActivity = (String)request.getParameter("idActivity");
		if (Util.empty(idActivity)){
			idActivity = (String)request.getParameter("id");
			if(idActivity == null){
				idActivity = (String)session.getAttribute("id");			
			}	
		}
		
		//session.setAttribute("id",idActivity);
		
		idDriver = (String)request.getParameter("idDriver");
		//session.setAttribute("idDriver",idDriver);			
		//session.setAttribute("url","activity/btw1Edit.jsp?idActivity="+idActivity+"&idDriver="+idDriver);
		url = "activity/btw1Edit.jsp?idActivity="+idActivity+"&idDriver="+idDriver;
		userSessionData.setUrl(url);

		activityReport = sys.getActivityReportFacade();
		registryData = activityReport.getCompleteResult(new Integer(idActivity),new Integer(idDriver),sys.getUserSessionData());			
		itemsResult = registryData.getItemSet();		
		
		checkedNovatoStr = "";
		disabledNovatoStr = "";
		valorNovato = "false";
	    novato = registryData.getNovato();
		if (novato) {
			valorNovato = "true";
			checkedNovatoStr = "checked";
			disabledNovatoStr = "disabled";
			
		}
		
		noTeoricoAsistence = !registryData.getTeoricoAsistence();
		checkedNoTeoricoAsistenceStr ="";
		if (noTeoricoAsistence) {
			checkedNoTeoricoAsistenceStr ="checked";				
		}
		
		noPracticoAsistence = !registryData.getPracticoAsistence();
		checkedNoPracticoAsistenceStr ="";							
		if (noPracticoAsistence) {
			checkedNoPracticoAsistenceStr ="checked";							
		}	
		
		phrdChecked = registryData.getPhrd();

		checkedPhrd = "";
		if (phrdChecked){
			checkedPhrd="checked";
		}
		
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
				&& ((element.name !="checkboxnovato"))
				&& ((element.name !="velocityBox")) 
				&& (element.name != "phrd")
				&& (element.name !="optradiofrenaje") && (!(element.disabled))){
							
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
		form2.plate.value="";
		form2.skidcar.value="";		    	
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

		form2.novato.value = form1.checkboxnovato.checked;
		form2.idDriver.value = '<%=idDriver%>';
		form2.idActivity.value = '<%=idActivity%>';
		form2.url.value = '<%=url%>';
		
		form2.submit();
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

function setNovato(form){
		if (form.checkboxnovato.checked==true) { 
			form.novato.value = "true";
			
			form.checkboxavoidrequired1.disabled=true;	
			form.checkboxavoidrequired2.disabled=true;	
			form.checkboxavoidrequired3.disabled=true;	
			form.checkboxavoidrequired4.disabled=true;	
			form.checkboxavoidrequired5.disabled=true;	
			form.checkboxavoidrequired6.disabled=true;	
			form.checkboxavoidrequired7.disabled=true;																			
			
			form.checkboxphysicsapplied1.disabled=true;																						
			
			form.checkboxacceleration1.disabled=true;	
			form.checkboxacceleration2.disabled=true;	
			form.checkboxacceleration3.disabled=true;						
			
			form.optradiofrenaje[0].disabled=true;	
			form.optradiofrenaje[1].disabled=true;				
			form.checkboxconvencional1.disabled=true;	
			form.checkboxconvencional2.disabled=true;	
			form.checkboxconvencional3.disabled=true;	
			form.checkboxabs1.disabled=true;	
			form.checkboxabs2.disabled=true;	
			form.checkboxabs3.disabled=true;	
			form.checkboxabs4.disabled=true;	
			form.checkboxabs5.disabled=true;	
			form.checkboxabs6.disabled=true;							
		}
		else {
			form.novato.value = "false";

			form.checkboxavoidrequired1.disabled=false;	
			form.checkboxavoidrequired2.disabled=false;	
			form.checkboxavoidrequired3.disabled=false;	
			form.checkboxavoidrequired4.disabled=false;	
			form.checkboxavoidrequired5.disabled=false;	
			form.checkboxavoidrequired6.disabled=false;	
			form.checkboxavoidrequired7.disabled=false;																			
			
			form.checkboxphysicsapplied1.disabled=false;																				
			
			form.checkboxacceleration1.disabled=false;	
			form.checkboxacceleration2.disabled=false;	
			form.checkboxacceleration3.disabled=false;	

			form.frenaje.value = "convencional";
			form.optradiofrenaje[0].checked=true;				
			form.optradiofrenaje[0].disabled=false;	
			form.optradiofrenaje[1].disabled=false;	
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
}

</script>


<body>
<div align="center"> 

		<form action="layout.jsp?refer=/activity/schedule/CEPAActivityScheduleView.jsp" name='cancel' method="post">
			<input type="hidden" name="id" value='<%=String.valueOf(registryData.getActivity().getActivityId())%>' />			
		</form>	
<form name="form2" method="post" action="">
    <table width="600" border="0" cellspacing="0" cellpadding="0">
      <tr> 
        <td height="40" colspan="5" valign="middle">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr> 
              <td width="9" height="40"><img src="/datacenter/util/imgs/titulo_left.jpg" width="9" height="40"></td>
              <td height="40" background="/datacenter/util/imgs/bg2.jpg"> 
              <div align="center">
              <font color="#FFFFFF" size="4" face="Arial, Helvetica, sans-serif">
              <strong>
              <%=messages.getText(activityData.getDefinition().getResourceKeyName())%>              
              </strong></font></div></td>
              <td width="9" height="40"><img src="/datacenter/util/imgs/titulo_right.jpg" width="9" height="40"></td>
            </tr>
          </table></td>
      </tr>
     <!--  dejo una linea en blanco entre cuadrante y cuadrante -->
     <tr>
     	<td colspan="5">&nbsp;</td>
     </tr>   
      
      <tr> 
        <td colspan="1" valign="top"> 
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="9"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                  <strong><%= messages.getText("generic.activity.asistence")%></strong></font></div></td>
              <td width="9"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <input type="checkbox" name="checkboxasistence1" class="check" <%=checkedNoTeoricoAsistenceStr%>> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.asistence.noteorico")%></font> 
              </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <input type="checkbox" name="checkboxasistence2" class="check" <%=checkedNoPracticoAsistenceStr%>> 
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
              <td> <input type="checkbox" name="phrd" class="check" <%=checkedPhrd%> > 
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
        <td width="200" valign="top"> 
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="9"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                  <strong><%= messages.getText("generic.activity.form.tranningonthejob.proofdata")%></strong></font></div></td>
              <td width="9"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <!--  Novato -->
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <input type="checkbox" name="checkboxnovato" class="check" onClick="javascript:setNovato(document.forms['form2']);" <%=checkedNovatoStr%> > 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw1liviano.novato")%></font> 
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
		
<td valign="top">&nbsp;</td>

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
		Iterator iter6 = dtReportFacade.findAll(DataTypeConstants.TYPEVEHICLE,userSessionData).iterator();
		String value = messages.getText("generic.messages.select");		
		String selectedVehicleType;
		%>
        <option VALUE='<%= value%>' class="input"><%= messages.getText(value)%></option>				
        <%
		while(iter6.hasNext()) {
			value = ((TypeVehicleDataType)iter6.next()).getResourceBundleId();
			selectedVehicleType ="";			
			if (!value.equals("datatype.vehicletype.all")) {
				if (value.equals(registryData.getVehicleType())) {
					selectedVehicleType = "selected";		
				}

		%>	
           		<option VALUE='<%= value%>' class="input" <%= selectedVehicleType%>><%= messages.getText(value)%></option>
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
     <!--  dejo una linea en blanco entre cuadrante y cuadrante -->
     <tr>
     	<td>&nbsp;</td>
     </tr>      
</table>
</td>
		
<td valign="top">&nbsp;</td>
<td width="200" valign="top">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<!--  Titulo del Punto: -->
	<tr> 
	
        <%
			// Caja de Velocidades
			checkVel1 = "";
			if ((registryData.getVelocityBox().equals("generic.activity.form.tranningonthejob.velocitybox.manual"))) {
				checkVel1 = "checked";
			}
			
			checkVel2 = "";
			if ((registryData.getVelocityBox().equals("generic.activity.form.tranningonthejob.velocitybox.automatic"))) {
				checkVel2 = "checked";
			}
    	 %>
    	 	
    	<td width="9" valign="top">
    		<img src="/datacenter/util/imgs/left_form.gif" >
    	</td>    	
        <td background="/datacenter/util/imgs/center_form.gif" valign="top"> 
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
        <td>        
        
        
        <INPUT type="radio" name="velocityBox" value="generic.activity.form.tranningonthejob.velocitybox.manual" <%=checkVel1%>>	    				
        <font size="1" face="Arial, Helvetica, sans-serif"> 
        <%= messages.getText("generic.activity.form.tranningonthejob.velocitybox.manual")%>
        </font> 
        </td>
        <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;
        </td>
     </tr>
        
      <tr>  
        <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;
        </td>
        <td>        
             <INPUT type="radio" name="velocityBox" value="generic.activity.form.tranningonthejob.velocitybox.automatic" <%=checkVel2%>>	    				
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
        <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
        <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
     </tr>
     <!--  dejo una linea en blanco entre cuadrante y cuadrante -->
     <tr>
     	<td>&nbsp;</td>
     </tr>      
</table>
</td>
		
		</tr>       
      
      
      
      <tr> 
        <td width="200" valign="top"> 
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="9"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                  <strong><%= messages.getText("generic.activity.form.btw1liviano.position")%></strong></font></div></td>
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
              <td> <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%> > 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw1liviano.position.pedalnext")%></font> 
              </td>
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
              <td> <input type="checkbox" name="checkbox222" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw1liviano.position.steeringwheelnext")%> 
                </font></td>
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
              <td> <input type="checkbox" name="checkbox222" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw1liviano.position.pedalfar")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.position";
						str2 = "generic.activity.form.btw1liviano.position.steeringwheelfar";	
						checkedStr = "";
						if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
							checkedStr = "checked"; 
						}							
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <input type="checkbox" name="checkbox222" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw1liviano.position.steeringwheelfar")%> 
                </font></td>
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
              <td> <input type="checkbox" name="checkbox222" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw1liviano.position.steeringwheelhands")%> 
                </font></td>
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
              <td> <input type="checkbox" name="checkbox222" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw1liviano.position.onehand")%> 
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
                  <strong> <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace1")%> 
                  </strong></font></div></td>
              <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.asistence")%> 
                </font></td>
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
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.signal")%> 
                </font></td>
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
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.spaceandpositionbeforemaneuver")%> 
                </font></td>
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
              <td> <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.referencesearch")%> 
                </font></td>
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
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.referenceuse")%> 
                </font></td>
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
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.possibilityevaluation")%> 
                </font></td>
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
              <td> <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.techniqueapply")%> 
                </font></td>
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
              <td> <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.generalobservation")%> 
                </font></td>
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
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.mirroruse")%> 
                </font></td>
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
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.blindpointcontrol")%> 
                </font></td>
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
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.sensibilitywithcommands")%> 
                </font></td>
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
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.commandcoordination")%> 
                </font></td>
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
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.velocitycontrol")%> 
                </font></td>
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
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.limitedmaneuver")%> 
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
              <td width="9" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                  <strong> <%= messages.getText("generic.activity.form.btw1liviano.mirrors")%> 
                  </strong></font></div></td>
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
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.mirrors.regulationoptions")%> 
                </font></td>
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
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.mirrors.blindpoint")%> 
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
              <td width="9" height="15" valign="top"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                  <strong> <%= messages.getText("generic.activity.form.btw1liviano.velocity")%> 
                  </strong></font></div></td>
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
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.velocity.tendency")%> 
                </font></td>
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
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.velocity.indication")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" height="19" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
              <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
              <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
            </tr>
          </table></td>
        <td>&nbsp;</td>
        <td width="200" valign="top"> <div align="center"> 
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr> 
                <td width="9"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
                <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><strong><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                    <%= messages.getText("generic.activity.form.btw1liviano.mecanicarms")%> 
                    </font></strong></div></td>
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
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                  <%= messages.getText("generic.activity.form.btw1liviano.mecanicarms.onehand")%> 
                  </font></td>
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
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                  <%= messages.getText("generic.activity.form.btw1liviano.mecanicarms.openhands")%> 
                  </font></td>
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
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                  <%= messages.getText("generic.activity.form.btw1liviano.mecanicarms.steeringwheelinside")%> 
                  </font></td>
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
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                  <%= messages.getText("generic.activity.form.btw1liviano.mecanicarms.bottomlocked")%> 
                  </font></td>
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
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                  <%= messages.getText("generic.activity.form.btw1liviano.mecanicarms.dinamic")%> 
                  </font></td>
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
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                  <%= messages.getText("generic.activity.form.btw1liviano.mecanicarms.limitedmoviments")%> 
                  </font></td>
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
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                  <%= messages.getText("generic.activity.form.btw1liviano.mecanicarms.specialwork")%> 
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
                    <strong> <%= messages.getText("generic.activity.form.btw1liviano.braking")%> 
                    </strong></font></div></td>
                <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
              </tr>
              
			<%      
				str1 = "generic.activity.form.btw1liviano.requiredbraking";	
				str2 = "generic.activity.form.btw1liviano.requiredbraking.default";	
				str3 = "generic.activity.form.btw1liviano.requiredbraking.incorrecttechnique";	
				str4 = "generic.activity.form.btw1liviano.requiredbraking.pressesfirstclutch";	
				str5 = "generic.activity.form.btw1liviano.requiredbraking.avoidwithoutcontrollingvelocity";	
				
				checkedConvencionalStr = "";

				if (novato){
					frenajeConvencional = true;
					checkedConvencionalStr = "checked";										
					disabledConvencionalStr = "disabled";															
					disabledABSStr = "disabled";																						
				}
				else {
					frenajeConvencional = false;		
					
					// Si no asistió al práctico en el form queda preseleccionado frenaje convencional					
					if (noPracticoAsistence) {
						checkedConvencionalStr = "checked";										
						frenajeConvencional = true;
					}
				
					if (itemsResult.contains(activityReport.getFormItem(str1,str2,sys.getUserSessionData()))) {
						checkedConvencionalStr = "checked";					
						frenajeConvencional = true;
					}				              
	
					if (itemsResult.contains(activityReport.getFormItem(str1,str3,sys.getUserSessionData()))) {
						checkedConvencionalStr = "checked";										
						frenajeConvencional = true;
					}				              
	
					if (itemsResult.contains(activityReport.getFormItem(str1,str4,sys.getUserSessionData()))) {
						checkedConvencionalStr = "checked";										
						frenajeConvencional = true;
					}				       
					
					if (itemsResult.contains(activityReport.getFormItem(str1,str5,sys.getUserSessionData()))) {
						checkedConvencionalStr = "checked";										
						frenajeConvencional = true;
					}				       				       
					if  (frenajeConvencional) {
						valorFrenaje= "convencional";					
						checkedABSStr = "";
						disabledConvencionalStr = "";					
					}
					else {
						valorFrenaje= "abs";										
						checkedABSStr = "checked";	
						disabledConvencionalStr = "disabled";										
						disabledABSStr = "";					
					}
					
				}
%>				
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> 
                  <INPUT type="radio" name="optradiofrenaje" class="check" onClick="javascript:deshabilitoABS(document.forms['form2']);" <%= checkedConvencionalStr%> <%= disabledNovatoStr%>>              
              <font size="1" face="Arial, Helvetica, sans-serif"> <B>Frenaje Convencional: </B>
              </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
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
                <td width="632"> <input type="checkbox" name="checkboxconvencional1" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%> <%= disabledConvencionalStr%> <%= disabledNovatoStr%>> 
                  <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw1liviano.requiredbraking.incorrecttechnique")%> 
                  </font></td>
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
                <td width="632"> <input type="checkbox" name="checkboxconvencional2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%> <%= disabledConvencionalStr%>> 
                  <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw1liviano.requiredbraking.pressesfirstclutch")%> 
                  </font> </td>
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
                <td> <input type="checkbox" name="checkboxconvencional3" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%> <%= disabledConvencionalStr%>> 
                  <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.form.btw1liviano.requiredbraking.avoidwithoutcontrollingvelocity")%> 
                  </font></td>
                <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>

            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> 
              <font size="1" face="Arial, Helvetica, sans-serif"> 
              </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>              
                            
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> 
                  <INPUT type="radio" name="optradiofrenaje" class="check" onClick="javascript:deshabilitoConvencional(document.forms['form2']);" <%= checkedABSStr%> <%= disabledNovatoStr%>>              
              <font size="1" face="Arial, Helvetica, sans-serif"> <B>Frenaje ABS: </B>
              </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
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
                  <input type="checkbox" name="checkboxabs1" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%> <%= disabledABSStr%>>
                  <%= messages.getText("generic.activity.form.btw1liviano.brakingABS.ignoranceabssystem")%> 
                  </font></td>
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
                  <input type="checkbox" name="checkboxabs2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%> <%= disabledABSStr%>>
                  <%= messages.getText("generic.activity.form.btw1liviano.brakingABS.ignorancevehiclesystem")%> 
                  </font></td>
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
                  <input type="checkbox" name="checkboxabs3" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%> <%= disabledABSStr%>>
                  <%= messages.getText("generic.activity.form.btw1liviano.brakingABS.pressesfirstclutch")%> 
                  </font></td>
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
                  <input type="checkbox" name="checkboxabs4" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%> <%= disabledABSStr%>>
                  <%= messages.getText("generic.activity.form.btw1liviano.brakingABS.powerlack")%> 
                  </font></td>
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
                  <input type="checkbox" name="checkboxabs5" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%> <%= disabledABSStr%>>
                  <%= messages.getText("generic.activity.form.btw1liviano.brakingABS.freesthebrake")%> 
                  </font></td>
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
                  <input type="checkbox" name="checkboxabs6" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%> <%= disabledABSStr%>>
                  <%= messages.getText("generic.activity.form.btw1liviano.brakingABS.coachesalternativetechniques")%> 
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
                    <%= messages.getText("generic.activity.form.btw1liviano.accelerationreaction")%> 
                    </font></strong></div></td>
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
                  <input type="checkbox" name="checkboxacceleration1" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%> <%= disabledNovatoStr%>>
                  <%= messages.getText("generic.activity.form.btw1liviano.accelerationreaction.eliminate")%> 
                  </font></td>
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
                  <input type="checkbox" name="checkboxacceleration2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%> <%= disabledNovatoStr%>>
                  <%= messages.getText("generic.activity.form.btw1liviano.accelerationreaction.partlyeliminate")%> 
                  </font></td>
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
                  <input type="checkbox" name="checkboxacceleration3" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%> <%= disabledNovatoStr%>>
                  <%= messages.getText("generic.activity.form.btw1liviano.accelerationreaction.noteliminate")%> 
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
                    <%= messages.getText("generic.activity.form.btw1liviano.physicsapplied")%> 
                    </font></strong></div></td>
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
                  <input type="checkbox" name="checkboxphysicsapplied1" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%> <%= disabledNovatoStr%>>
                  <%= messages.getText("generic.activity.form.btw1liviano.physicsapplied.notappliestechniques")%> 
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
          </div></td>
        <td>&nbsp;</td>
        <td width="216" valign="top"> 
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="9"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><strong><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                  <%= messages.getText("generic.activity.form.btw1liviano.pedalgearshift1")%> 
                  </font></strong></div></td>
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
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.pedalgearshift.technicaluseclutch")%> 
                </font></td>
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
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.pedalgearshift.sensibilitywithclutch")%> 
                </font></td>
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
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.pedalgearshift.sensibilitywithbrake")%> 
                </font></td>
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
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.pedalgearshift.sensibilitywithaccelerator")%> 
                </font></td>
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
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.pedalgearshift.coordinationclutchgearshift")%> 
                </font></td>
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
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.pedalgearshift.coordinationclutchaccelerator")%> 
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
                  <%= messages.getText("generic.activity.form.btw1liviano.boxofchanges1")%> 
                  </strong></font></div></td>
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
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.boxofchanges.sensibilityerror")%> 
                </font></td>
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
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.boxofchanges.distributionincorrect")%> 
                </font></td>
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
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.boxofchanges.incorrectuseofneuterpoint")%> 
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
                  <%= messages.getText("generic.activity.form.btw1liviano.avoidrequired")%> 
                  </font></strong></div></td>
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
                <input type="checkbox" name="checkboxavoidrequired1" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%> <%= disabledNovatoStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.avoidrequired.wheelblocked")%> 
                </font></td>
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
                <input type="checkbox" name="checkboxavoidrequired2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%> <%= disabledNovatoStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.avoidrequired.brakecoordination")%> 
                </font></td>
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
                <input type="checkbox" name="checkboxavoidrequired3" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%> <%= disabledNovatoStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.avoidrequired.undueaccelerationreaction")%> 
                </font></td>
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
                <input type="checkbox" name="checkboxavoidrequired4" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%> <%= disabledNovatoStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.avoidrequired.dynamic")%> 
                </font></td>
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
                <input type="checkbox" name="checkboxavoidrequired5" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%> <%= disabledNovatoStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.avoidrequired.brakingtechniqueapplication")%> 
                </font></td>
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
                <input type="checkbox" name="checkboxavoidrequired6" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%> <%= disabledNovatoStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.avoidrequired.notbrakingtechniqueapplication")%> 
                </font></td>
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
                <input type="checkbox" name="checkboxavoidrequired7" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%> <%= disabledNovatoStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.avoidrequired.coordinationandsensibilityerrors")%> 
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
                  <%= messages.getText("generic.activity.form.btw1liviano.reactiontime")%> 
                  </font></strong></div></td>
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
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.reactiontime.correctsmaneuvererrors")%> 
                </font></td>
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
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.reactiontime.correctstimeerrors")%> 
                </font></td>
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
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.reactiontime.satisfactory")%> 
                </font></td>
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
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.reactiontime.notsatisfactory")%> 
                </font></td>
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
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" <%=checkedStr%>>
                <%= messages.getText("generic.activity.form.btw1liviano.reactiontime.medicalappraisal")%> 
                </font></td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
              <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
              <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
            </tr>
          </table></td>
      </tr>
      <tr> 
        <td colspan="5"><table width="100%" border="0" cellpadding="0" cellspacing="0">
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
					&& activityData.getType().getResourceBundleId().equals(CEPAActivityConstants.BTW1)
					&& (activityData.getCountryName().equals(CountryData.MEXICO)  || activityData.getCountryName().equals(CountryData.GUATEMALA)
							|| activityData.getCountryName().equals(CountryData.NICARAGUA) || activityData.getCountryName().equals(CountryData.COSTA_RICA))
					&& registryData.getClasification() != null && registryData.getClasification().intValue() > 0) {
%>             		<select name="note" class="input">
<%					for(int i = 1; i < 7; i++) {
           				String selected = (registryData.getClasification().intValue() == i) ? "selected" : "";
%>               		<option value='<%=i%>' class="input" <%=selected%>><%= messages.getText("activity.clasification.text"+i) %></option>
<%               	}
%>                	</select> 
<%				}else {
              		DataTypeReportFacade dataTypeReportFacade = sys.getDataTypeReportFacade();		
      				Collection notes = dataTypeReportFacade.findAll(DataTypeConstants.TYPECEPAACTIVITYNOTE,userSessionData);
              		if (notes != null){ 
%>              		<select name="note" class="input">
<%						Iterator it = notes.iterator();
              			while (it.hasNext()){
              				String activityNote = ((CEPAActivityNoteDataType) it.next()).getResourceBundleId();
              				String selected = registryData.getNote().equals(activityNote)?"selected":"";
%>               			  <option value='<%= activityNote %>' class="input" <%= selected %> ><%= messages.getText(activityNote) %></option>
<%               		}
%>	            		</select> 
<%                	}
				}
%>
				</td>
              <td background="/datacenter/util/imgs/right_gray.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td valign="top" background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td bgcolor="#f9f9f9">&nbsp;</td>
              <td valign="top" background="/datacenter/util/imgs/right_gray.gif" bgcolor="#f9f9f9">&nbsp;</td>
            </tr>
            <tr> 
              <td background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td bgcolor="#f9f9f9"><font size="1" face="Arial, Helvetica, sans-serif"><strong><%=messages.getText("generic.activity.observations") %></strong></font></td>
              <td background="/datacenter/util/imgs/right_gray.gif" bgcolor="#f9f9f9">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" height="88" background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td bgcolor="#f9f9f9"><strong><font color="#FF0000" size="1" face="Arial, Helvetica, sans-serif"> 
                <textarea name="obs" cols="70" rows="5"><%=registryData.getObs().trim()%></textarea>
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
                <input type=text name="instructor" value='<%= registryData.getInstructor().trim() %>'> </td>
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
<%	if(userSessionData.canModifyActivityRegitry(registryData.getActivity().getType().getResourceBundleId())) {
%>            <input name="button2" type="button"  value="<%=messages.getText("generic.messages.save") %>" onClick="javascript:setAction(document.forms['form2'],document.forms['ActivityRegistryForm']);" class="input" align="right"/>
<%	}
%>            <input name="buttonCancel" type="button"  value="<%=messages.getText("generic.messages.cancel") %>" onClick="document.forms['cancel'].submit();" class="input" align="right"/>
          </div></td>
      </tr>
    </table>
    
        	<input type="hidden" name="checkboxParamList"/>
        	<input type="hidden" name="skidcar" value="" />
        	<input type="hidden" name="plate" value="" />
	        <input type="hidden" name="frenaje" value = '<%=valorFrenaje %>'/>    
	        <input type="hidden" name="novato" value = '<%=valorNovato %>'/>	           	                    	                 	
  
  </form>
  
</div>

<html:form action = "ActivityRegistrycreate" method="post" >
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
	<html:hidden property="novato" />	
	<html:hidden property="phrd" value="false" />					
	<html:hidden property="noteoricoasistence"/>								
	<html:hidden property="nopracticoasistence" />											
</html:form>

</body>

</html:html>