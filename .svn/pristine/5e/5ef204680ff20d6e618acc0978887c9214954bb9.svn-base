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
	String url;
	String idDriver;
	String idActivity;
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

		idActivity = (String)request.getParameter("idActivity");
		if(idActivity == null){
			idActivity = (String)session.getAttribute("id");			
		}
		// session.setAttribute("id",idActivity);
		
		
		idDriver=(String)request.getParameter("idDriver");
		// session.setAttribute("idDriver",idDriver);			
		
		activityReport = sys.getActivityReportFacade();
		
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
		form2.novato.value = form1.novato.value;		
		
		form2.vehicleType.value = form1.vehicleType.value;	
		
		for (i=0;i<form1.velocityBox.length;i++){
	       if (form1.velocityBox[i].checked == true) {
    	      form2.velocityBox.value = form1.velocityBox[i].value;
        	  break;
        	}  
		}				
				
		form2.noteoricoasistence.value = form1.checkboxasistence1.checked;				
		form2.nopracticoasistence.value = form1.checkboxasistence2.checked;

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

<form name="form2" method="post" action="">
    <table width="600" border="0" cellspacing="0" cellpadding="0">
      <tr> 
        <td height="40" colspan="5" valign="middle">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr> 
              <td width="9" height="30"><img src="/datacenter/util/imgs/titulo_left.jpg" width="9" height="30"></td>
              <td height="30" background="/datacenter/util/imgs/bg2.jpg"> <div align="center">
              <font color="#FFFFFF" size="2" face="Arial, Helvetica, sans-serif">
              <strong><%=messages.getText("generic.activity.form.btw1liviano")%></strong>
              </font></div></td>
              <td width="9" height="30"><img src="/datacenter/util/imgs/titulo_right.jpg" width="9" height="30"></td>
            </tr>
        </table>
        </td>
      </tr>
 
	<!--  Titulo del Punto: -->

      <tr> 
        <td colspan="5" valign="top"> 
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="9"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td class="prueba10" colspan="2"  background="/datacenter/util/imgs/center_form.gif"> <div align="center">              
                  <strong><%= messages.getText("generic.activity.asistence")%></strong>
                  </div></td>
              <td width="9"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> <input type="checkbox" name="checkboxasistence1" class="check"> 
                 <%= messages.getText("generic.activity.asistence.noteorico")%>
              </td>
              <td class="prueba8" align="right"> <input type="checkbox" name="checkboxasistence2" class="check"> 
                 <%= messages.getText("generic.activity.asistence.nopractico")%>
              </td>              
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
                        
            <tr> 
              <td width="9" height="19" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
              <td colspan="2" background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
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
              <td class= "prueba10" background="/datacenter/util/imgs/center_form.gif"> <div align="center">

                  <strong><%= messages.getText("generic.activity.form.tranningonthejob.proofdata")%></strong>

              </div></td>
              <td width="9"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> <input type="checkbox" name="checkboxnovato" class="check" onClick="javascript:setNovato(document.forms['form2']);"> 
                 <%= messages.getText("generic.activity.form.btw1liviano.novato")%> 
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
        <td class="prueba10" background="/datacenter/util/imgs/center_form.gif" valign="top"> 
        	<div align="center">
        		<!--  Cambiar esto por un estilo !! -->
				<strong>
        		<%=messages.getText("generic.activity.form.tranningonthejob.vehicletype")%>
        		</strong>
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
          <input type=text >
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
<td width="200" valign="top">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<!--  Titulo del Punto: -->
	<tr> 
    	<td width="9" valign="top">
    		<img src="/datacenter/util/imgs/left_form.gif" >
    	</td>    	
        <td  class="prueba10" colspan = "2" background="/datacenter/util/imgs/center_form.gif" valign="top"> 
        	<div align="center">
        		<!--  Cambiar esto por un estilo !! -->

        		<strong>
        		<%=messages.getText("generic.activity.form.tranningonthejob.velocitybox")%>
        		</strong>

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
        <td class="prueba8">        
        <INPUT type="radio" name="velocityBox" value="generic.activity.form.tranningonthejob.velocitybox.manual" >	    				
         
        <%= messages.getText("generic.activity.form.tranningonthejob.velocitybox.manual")%>

        </td>
        <td class="prueba8" align="right">        
             <INPUT type="radio" name="velocityBox" value="generic.activity.form.tranningonthejob.velocitybox.automatic">	    				
         
        <%= messages.getText("generic.activity.form.tranningonthejob.velocitybox.automatic")%>

        </td>
        <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;
        </td>       

     </tr>
            

	 <!--  Dibujo linea inferior de los cuadrantes -->
 	 <tr> 
        <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
        <td colspan = "2" background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
        <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
     </tr>
     <!--  dejo una linea en blanco entre cuadrante y cuadrante -->
</table>
</td>
</tr> 
      <tr> 
        <td width="200" valign="top">
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="9"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td class = "prueba10" background="/datacenter/util/imgs/center_form.gif"> <div align="center">

                  <strong><%= messages.getText("generic.activity.form.btw1liviano.position")%></strong>

               </div></td>
              <td width="9"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.position";
						str2 = "generic.activity.form.btw1liviano.position.pedalnext";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> <input type="checkbox" name="checkbox2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check"> 
                 <%= messages.getText("generic.activity.form.btw1liviano.position.pedalnext")%> 
              </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.position";
						str2 = "generic.activity.form.btw1liviano.position.steeringwheelnext";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> <input type="checkbox" name="checkbox222" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check"> 
                 <%= messages.getText("generic.activity.form.btw1liviano.position.steeringwheelnext")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.position";
						str2 = "generic.activity.form.btw1liviano.position.pedalfar";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> <input type="checkbox" name="checkbox222" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check"> 
                 <%= messages.getText("generic.activity.form.btw1liviano.position.pedalfar")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.position";
						str2 = "generic.activity.form.btw1liviano.position.steeringwheelfar";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> <input type="checkbox" name="checkbox222" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check"> 
                 <%= messages.getText("generic.activity.form.btw1liviano.position.steeringwheelfar")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.position";
						str2 = "generic.activity.form.btw1liviano.position.steeringwheelhands";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> <input type="checkbox" name="checkbox222" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check"> 
                 <%= messages.getText("generic.activity.form.btw1liviano.position.steeringwheelhands")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.position";
						str2 = "generic.activity.form.btw1liviano.position.onehand";						
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> <input type="checkbox" name="checkbox222" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check"> 
                 <%= messages.getText("generic.activity.form.btw1liviano.position.onehand")%> 
                </td>
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
              <td class = "prueba10" background="/datacenter/util/imgs/center_form.gif"> <div align="center">
                  <strong> <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace1")%> 
                  </strong>
                  </div></td>
              <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8">  <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.asistence")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.signal";						
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> 
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.signal")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.spaceandpositionbeforemaneuver";						

					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> 
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.spaceandpositionbeforemaneuver")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.referencesearch";						


					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8" > <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check"> 
                 <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.referencesearch")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.referenceuse";						

					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> 
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.referenceuse")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.possibilityevaluation";						

					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> 
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.possibilityevaluation")%> 
                </td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.techniqueapply";						

					%>
              <td height="19" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check"> 
                <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.techniqueapply")%> 
                </td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.generalobservation";						
						
					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check"> 
                <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.generalobservation")%> 
                </td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.mirroruse";						
					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> 
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.mirroruse")%> 
                </td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.blindpointcontrol";						

					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8">
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.blindpointcontrol")%> 
                </td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.sensibilitywithcommands";						

					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> 
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.sensibilitywithcommands")%> 
                </td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.commandcoordination";						

					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> 
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.commandcoordination")%> 
                </td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.velocitycontrol";						

					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> 
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.velocitycontrol")%> 
                </td>
              <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.maneuverinreducedspace";
						str2 = "generic.activity.form.btw1liviano.maneuverinreducedspace.limitedmaneuver";																																																																							
					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> 
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.maneuverinreducedspace.limitedmaneuver")%> 
                </td>
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
              <td class = "prueba10" background="/datacenter/util/imgs/center_form.gif"> <div align="center">

                  <strong> <%= messages.getText("generic.activity.form.btw1liviano.mirrors")%> 
                  </strong>

                  </div></td>
              <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.mirrors";
						str2 = "generic.activity.form.btw1liviano.mirrors.regulationoptions";																																																																							
						
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> 
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.mirrors.regulationoptions")%> 
                </td>
              <td width="9" valign="top" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.mirrors";
						str2 = "generic.activity.form.btw1liviano.mirrors.blindpoint";																																																																							
						
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> 
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.mirrors.blindpoint")%> 
                </td>
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
              <td class = "prueba10" background="/datacenter/util/imgs/center_form.gif"> <div align="center">
              
                  <strong> <%= messages.getText("generic.activity.form.btw1liviano.velocity")%> 
                  </strong>

                  </div></td>
              <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.velocity";
						str2 = "generic.activity.form.btw1liviano.velocity.tendency";						
							
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> 
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.velocity.tendency")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.velocity";
						str2 = "generic.activity.form.btw1liviano.velocity.indication";								
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> 
                <input type="checkbox" name="checkbox22" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.velocity.indication")%> 
                </td>
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
                <td class = "prueba10" background="/datacenter/util/imgs/center_form.gif"> <div align="center"><strong>

                    <%= messages.getText("generic.activity.form.btw1liviano.mecanicarms")%> 

                    </strong></div></td>
                <td width="9"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw1liviano.mecanicarms";
						str2 = "generic.activity.form.btw1liviano.mecanicarms.onehand";
					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td class="prueba8"> 
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                  <%= messages.getText("generic.activity.form.btw1liviano.mecanicarms.onehand")%> 
                  </td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              
              <tr> 
                <%
						str1 = "generic.activity.form.btw1liviano.mecanicarms";
						str2 = "generic.activity.form.btw1liviano.mecanicarms.openhands";	
					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td class="prueba8"> 
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                  <%= messages.getText("generic.activity.form.btw1liviano.mecanicarms.openhands")%> 
                  </td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw1liviano.mecanicarms";
						str2 = "generic.activity.form.btw1liviano.mecanicarms.steeringwheelinside";	
					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td  class="prueba8"> 
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                  <%= messages.getText("generic.activity.form.btw1liviano.mecanicarms.steeringwheelinside")%> 
                  </td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw1liviano.mecanicarms";
						str2 = "generic.activity.form.btw1liviano.mecanicarms.bottomlocked";	
					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td class="prueba8"> 
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                  <%= messages.getText("generic.activity.form.btw1liviano.mecanicarms.bottomlocked")%> 
                  </td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw1liviano.mecanicarms";
						str2 = "generic.activity.form.btw1liviano.mecanicarms.dinamic";	
					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td class="prueba8"> 
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                  <%= messages.getText("generic.activity.form.btw1liviano.mecanicarms.dinamic")%> 
                  </td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw1liviano.mecanicarms";
						str2 = "generic.activity.form.btw1liviano.mecanicarms.limitedmoviments";	
					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td class="prueba8"> 
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                  <%= messages.getText("generic.activity.form.btw1liviano.mecanicarms.limitedmoviments")%> 
                  </td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw1liviano.mecanicarms";
						str2 = "generic.activity.form.btw1liviano.mecanicarms.specialwork";	
					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td class="prueba8"> 
                  <input type="checkbox" name="checkbox23" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                  <%= messages.getText("generic.activity.form.btw1liviano.mecanicarms.specialwork")%> 
                  </td>
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
                <td class = "prueba10" background="/datacenter/util/imgs/center_form.gif"> 
                  <div align="center">
                    <strong> <%= messages.getText("generic.activity.form.btw1liviano.braking1")%> 
                    </strong>
                    </div></td>
                <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
              </tr>
              
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> 
                  <INPUT type="radio" name="optradiofrenaje" class="check" checked onClick="javascript:deshabilitoABS(document.forms['form2']);">                            
              <B>Frenaje Convencional: </B>
              </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>              
              
              <tr> 
                <%
						str1 = "generic.activity.form.btw1liviano.requiredbraking";
						str2 = "generic.activity.form.btw1liviano.requiredbraking.incorrecttechnique";							
					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td class="prueba8"> <input type="checkbox" name="checkboxconvencional1" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" > 
                  <%= messages.getText("generic.activity.form.btw1liviano.requiredbraking.incorrecttechnique")%> 
                  </td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw1liviano.requiredbraking";
						str2 = "generic.activity.form.btw1liviano.requiredbraking.pressesfirstclutch";	

					%>
                <td width="9" height="21" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td class="prueba8"> <input type="checkbox" name="checkboxconvencional2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check"> 
                  <%= messages.getText("generic.activity.form.btw1liviano.requiredbraking.pressesfirstclutch")%> 
                  </td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr>  
                <%
						str1 = "generic.activity.form.btw1liviano.requiredbraking";
						str2 = "generic.activity.form.btw1liviano.requiredbraking.avoidwithoutcontrollingvelocity";														
					%>
                <td height="27" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td class="prueba8"> <input type="checkbox" name="checkboxconvencional3" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" > 
                  <%= messages.getText("generic.activity.form.btw1liviano.requiredbraking.avoidwithoutcontrollingvelocity")%> 
                  </td>
                <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>

            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8"> 
                  <INPUT type="radio" name="optradiofrenaje" class="check" onClick="javascript:deshabilitoConvencional(document.forms['form2']);">                            
              <B>Frenaje ABS: </B>
              </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>              
              
              <tr> 
                <%
						str1 = "generic.activity.form.btw1liviano.brakingABS";
						str2 = "generic.activity.form.btw1liviano.brakingABS.ignoranceabssystem";	
						
					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td class="prueba8"> 
                  <input type="checkbox" name="checkboxabs1" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" disabled>
                  <%= messages.getText("generic.activity.form.btw1liviano.brakingABS.ignoranceabssystem")%> 
                  </td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw1liviano.brakingABS";
						str2 = "generic.activity.form.btw1liviano.brakingABS.ignorancevehiclesystem";	

					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td class="prueba8"> 
                  <input type="checkbox" name="checkboxabs2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" disabled>
                  <%= messages.getText("generic.activity.form.btw1liviano.brakingABS.ignorancevehiclesystem")%> 
                  </td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw1liviano.brakingABS";
						str2 = "generic.activity.form.btw1liviano.brakingABS.pressesfirstclutch";	

					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td class="prueba8">
                  <input type="checkbox" name="checkboxabs3" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" disabled>
                  <%= messages.getText("generic.activity.form.btw1liviano.brakingABS.pressesfirstclutch")%> 
                  </td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw1liviano.brakingABS";
						str2 = "generic.activity.form.btw1liviano.brakingABS.powerlack";	

					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td class="prueba8">
                  <input type="checkbox" name="checkboxabs4" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" disabled>
                  <%= messages.getText("generic.activity.form.btw1liviano.brakingABS.powerlack")%> 
                  </td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw1liviano.brakingABS";
						str2 = "generic.activity.form.btw1liviano.brakingABS.freesthebrake";	

					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td class="prueba8">
                  <input type="checkbox" name="checkboxabs5" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" disabled>
                  <%= messages.getText("generic.activity.form.btw1liviano.brakingABS.freesthebrake")%> 
                  </td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw1liviano.brakingABS";
						str2 = "generic.activity.form.btw1liviano.brakingABS.coachesalternativetechniques";	

					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td class="prueba8">
                  <input type="checkbox" name="checkboxabs6" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check" disabled>
                  <%= messages.getText("generic.activity.form.btw1liviano.brakingABS.coachesalternativetechniques")%> 
                  </td>
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
                <td class="prueba10" background="/datacenter/util/imgs/center_form.gif"> <div align="center"><strong>

                    <%= messages.getText("generic.activity.form.btw1liviano.accelerationreaction")%> 

                    </strong></div></td>
                <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw1liviano.accelerationreaction";
						str2 = "generic.activity.form.btw1liviano.accelerationreaction.eliminate";	
											
					%>
                <td width="9" height="20" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td class="prueba8">
                  <input type="checkbox" name="checkboxacceleration1" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                  <%= messages.getText("generic.activity.form.btw1liviano.accelerationreaction.eliminate")%> 
                  </td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw1liviano.accelerationreaction";
						str2 = "generic.activity.form.btw1liviano.accelerationreaction.partlyeliminate";	

					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td class="prueba8">
                  <input type="checkbox" name="checkboxacceleration2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                  <%= messages.getText("generic.activity.form.btw1liviano.accelerationreaction.partlyeliminate")%> 
                  </td>
                <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw1liviano.accelerationreaction";
						str2 = "generic.activity.form.btw1liviano.accelerationreaction.noteliminate";	
							
					%>
                <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td class="prueba8">
                  <input type="checkbox" name="checkboxacceleration3" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                  <%= messages.getText("generic.activity.form.btw1liviano.accelerationreaction.noteliminate")%> 
                  </td>
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
                <td class = "prueba10" background="/datacenter/util/imgs/center_form.gif"> <div align="center"><strong>

                    <%= messages.getText("generic.activity.form.btw1liviano.physicsapplied")%> 
                    </strong></div></td>
                <td width="9" valign="top"><strong><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></strong></td>
              </tr>
              <tr> 
                <%
						str1 = "generic.activity.form.btw1liviano.physicsapplied";
						str2 = "generic.activity.form.btw1liviano.physicsapplied.notappliestechniques";	
											
					%>
                <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
                <td class="prueba8">
                  <input type="checkbox" name="checkboxphysicsapplied1" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                  <%= messages.getText("generic.activity.form.btw1liviano.physicsapplied.notappliestechniques")%> 
                  </td>
                <td background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
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
              <td width="9"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td class = "prueba10" background="/datacenter/util/imgs/center_form.gif"> <div align="center"><strong>

                  <%= messages.getText("generic.activity.form.btw1liviano.pedalgearshift1")%> 

                  </strong></div></td>
              <td width="9"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.pedalgearshift";
						str2 = "generic.activity.form.btw1liviano.pedalgearshift.technicaluseclutch";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8">
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.pedalgearshift.technicaluseclutch")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.pedalgearshift";
						str2 = "generic.activity.form.btw1liviano.pedalgearshift.sensibilitywithclutch";	

					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8">
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.pedalgearshift.sensibilitywithclutch")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.pedalgearshift";
						str2 = "generic.activity.form.btw1liviano.pedalgearshift.sensibilitywithbrake";	

					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8">
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.pedalgearshift.sensibilitywithbrake")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.pedalgearshift";
						str2 = "generic.activity.form.btw1liviano.pedalgearshift.sensibilitywithaccelerator";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8">
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.pedalgearshift.sensibilitywithaccelerator")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.pedalgearshift";
						str2 = "generic.activity.form.btw1liviano.pedalgearshift.coordinationclutchgearshift";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8">
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.pedalgearshift.coordinationclutchgearshift")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.pedalgearshift";
						str2 = "generic.activity.form.btw1liviano.pedalgearshift.coordinationclutchaccelerator";	
					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8">
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.pedalgearshift.coordinationclutchaccelerator")%> 
                </td>
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
              <td class = "prueba10" background="/datacenter/util/imgs/center_form.gif"> <div align="center">

              <strong> 
                  <%= messages.getText("generic.activity.form.btw1liviano.boxofchanges1")%> 
                  </strong>

                  </div></td>
              <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.boxofchanges";
						str2 = "generic.activity.form.btw1liviano.boxofchanges.sensibilityerror";	
						
					
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8">
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.boxofchanges.sensibilityerror")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.boxofchanges";
						str2 = "generic.activity.form.btw1liviano.boxofchanges.distributionincorrect";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8">
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.boxofchanges.distributionincorrect")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.boxofchanges";
						str2 = "generic.activity.form.btw1liviano.boxofchanges.incorrectuseofneuterpoint";	
					%>
              <td background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8">
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.boxofchanges.incorrectuseofneuterpoint")%> 
                </td>
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
              <td class = "prueba10" background="/datacenter/util/imgs/center_form.gif"> <div align="center"><strong>
               
                  <%= messages.getText("generic.activity.form.btw1liviano.avoidrequired")%> 

                  </strong></div></td>
              <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.avoidrequired";
						str2 = "generic.activity.form.btw1liviano.avoidrequired.wheelblocked";	
						
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8">
                <input type="checkbox" name="checkboxavoidrequired1" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.avoidrequired.wheelblocked")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.avoidrequired";
						str2 = "generic.activity.form.btw1liviano.avoidrequired.brakecoordination";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8">
                <input type="checkbox" name="checkboxavoidrequired2" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.avoidrequired.brakecoordination")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.avoidrequired";
						str2 = "generic.activity.form.btw1liviano.avoidrequired.undueaccelerationreaction";	

					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8">
                <input type="checkbox" name="checkboxavoidrequired3" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.avoidrequired.undueaccelerationreaction")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.avoidrequired";
						str2 = "generic.activity.form.btw1liviano.avoidrequired.dynamic";	

					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8">
                <input type="checkbox" name="checkboxavoidrequired4" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.avoidrequired.dynamic")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.avoidrequired";
						str2 = "generic.activity.form.btw1liviano.avoidrequired.brakingtechniqueapplication";	

					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8">
                <input type="checkbox" name="checkboxavoidrequired5" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.avoidrequired.brakingtechniqueapplication")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.avoidrequired";
						str2 = "generic.activity.form.btw1liviano.avoidrequired.notbrakingtechniqueapplication";	
												
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8">
                <input type="checkbox" name="checkboxavoidrequired6" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.avoidrequired.notbrakingtechniqueapplication")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.avoidrequired";
						str2 = "generic.activity.form.btw1liviano.avoidrequired.coordinationandsensibilityerrors";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8">
                <input type="checkbox" name="checkboxavoidrequired7" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.avoidrequired.coordinationandsensibilityerrors")%> 
                </td>
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
              <td class = "prueba10" background="/datacenter/util/imgs/center_form.gif"> <div align="center"><strong> 
                  <%= messages.getText("generic.activity.form.btw1liviano.reactiontime")%> 
                  </strong></div></td>
              <td width="9" valign="top"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.reactiontime";
						str2 = "generic.activity.form.btw1liviano.reactiontime.correctsmaneuvererrors";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8">
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.reactiontime.correctsmaneuvererrors")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.reactiontime";
						str2 = "generic.activity.form.btw1liviano.reactiontime.correctstimeerrors";	

					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8">
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.reactiontime.correctstimeerrors")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.reactiontime";
						str2 = "generic.activity.form.btw1liviano.reactiontime.satisfactory";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8">
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.reactiontime.satisfactory")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.reactiontime";
						str2 = "generic.activity.form.btw1liviano.reactiontime.notsatisfactory";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8">
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.reactiontime.notsatisfactory")%> 
                </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <%
						str1 = "generic.activity.form.btw1liviano.reactiontime";
						str2 = "generic.activity.form.btw1liviano.reactiontime.medicalappraisal";	
					%>
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td class="prueba8">
                <input type="checkbox" name="checkbox24" value='<%=activityReport.getFormItem(str1,str2,sys.getUserSessionData()).getId().toString() %>' class="check">
                <%= messages.getText("generic.activity.form.btw1liviano.reactiontime.medicalappraisal")%> 
                </td>
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
        <td colspan="5">
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td valign="top"><img src="/datacenter/util/imgs/left_top_gray.gif" width="9" height="11"></td>
              <td colspan= "2" background="/datacenter/util/imgs/center_top_gray.gif"> <div align="left"><strong><img src="/datacenter/util/imgs/center_base_gray.gif" width="1" height="11"></strong></div></td>
              <td valign="top"><strong><img src="/datacenter/util/imgs/right_top_gray.gif" width="9" height="11"></strong></td>
            </tr>
            <tr>
	          <td background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td> 
              <td class="pruebablack10" bgcolor="#f9f9f9">
              <strong><%= messages.getText("generic.activity.note") %><strong>
              <input type=text >
              </td>
              <td  class="pruebablack10" bgcolor="#f9f9f9">
              <strong><%= messages.getText("generic.activity.instructor") %></strong>
              <input type=text >
              </td>
		     <td background="/datacenter/util/imgs/right_gray.gif">&nbsp;</td>              
             </tr> 
             <tr>            
            <tr> 
              <td  background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td  class="pruebablack10" colspan="2" bgcolor="#f9f9f9">
              <strong><%= messages.getText("generic.activity.observations") %></strong></td>
              <td  background="/datacenter/util/imgs/right_gray.gif" bgcolor="#f9f9f9">&nbsp;</td>
            </tr>
            <tr> 
              <td background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td class="prueba8" colspan="2" bgcolor="#f9f9f9"><strong>
                <textarea name="obs" cols="70" rows="3"></textarea>
                </strong></td>
              <td  background="/datacenter/util/imgs/right_gray.gif">&nbsp;</td>
            </tr>            
            
            <tr> 
              <td  height="11" valign="top"><img src="/datacenter/util/imgs/left_base_gray.gif" width="9" height="11"></td>
              <td  colspan="2" valign="top" background="/datacenter/util/imgs/center_base_gray.gif" ><img src="/datacenter/util/imgs/center_base_gray.gif" width="1" height="11"></td>
              <td  valign="top"><img src="/datacenter/util/imgs/right_base_gray.gif" width="9" height="11"></td>
            </tr> 
          </table></td>
      </tr>
    </table>
    
        	<input type="hidden" name="checkboxParamList"/>
        	<input type="hidden" name="skidcar" value="" />
        	<input type="hidden" name="plate" value="" />
	        <input type="hidden" name="frenaje" value ="convencional"/>       
	        <input type="hidden" name="novato" value="false"/>	                 	           

  </form>
  
</div>

<html:form action = "ActivityRegistrycreate" method="post">
	<html:hidden property="idDriver" value="" />
	<html:hidden property="idActivity" value="" />
	<html:hidden property="url" value="" />
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
	<html:hidden property="noteoricoasistence" value="false" />								
	<html:hidden property="nopracticoasistence" value="false" />									
</html:form>

</body>

</html:html>