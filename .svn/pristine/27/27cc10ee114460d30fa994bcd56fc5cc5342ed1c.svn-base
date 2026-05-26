<%@page import="org.apache.struts.action.DynaActionForm"%>
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
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.*"
	import="communication.datatype.*"	
	import="business.activity.ActivityReportFacade"
	import="java.util.*"	
	import="org.apache.struts.action.DynaActionForm"
	import="business.datatype.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

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
	String str1, str2, str3,col, idActivity,idDriver,url;
	CEPAActivityScheduleData  activityData;
	int columns;
	DynaActionForm activityRegistryForm; 
	DataTypeReportFacade dataTypeReportFacade;
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
		
		// session.setAttribute("url","activity/schedule/btw3Form.jsp");
		url = "activity/schedule/MonitorsForm.jsp";
		userSessionData.setUrl(url);
		
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);

		idActivity =(String)request.getParameter("idActivity");
		if(idActivity==null){
			idActivity=(String)session.getAttribute("id");			
		}
		//session.setAttribute("id",idActivity);
		
		// Obtengo el formulario de esa actividad para dibujarlo en el HTML:
		activityReport = sys.getActivityReportFacade();
		
		// Obtengo la cantidad de columnas a dibujar en el formulario:
		col = request.getParameter("columns");		
		if (!Util.empty(col))
			columns	 = (Integer.parseInt(col));
		else
			columns = 1;
		
		CEPAActivityScheduleData activityData = (CEPAActivityScheduleData) session.getAttribute("activityData");
		//activityData = activityReport.findCEPAActivityScheduleByPrimaryKey(new Integer(Integer.parseInt(idActivity)),userSessionData);
		
		
		// Me fijo si ya tiene un registro de esta actividad ese conductor:
		idDriver=(String)request.getParameter("idDriver");
		if(idDriver==null){
			idDriver=(String)session.getAttribute("idDriver");			
		}		
		// session.setAttribute("idDriver",idDriver);	
		

		String noteStr = "";
		String obsStr = "";
		String instructorStr = "";	
		String selNota1 ="";
		String selNota2="";
		String selNota3="";	
		
		String checkedNoTeoricoAsistenceStr="";			
		String checkedNoPracticoAsistenceStr="";				
		boolean noTeoricoAsistence;
		boolean noPracticoAsistence;
		
		
		// Si hubo problemas con la validación se recargan los datos ya ingresados
		activityRegistryForm = (DynaActionForm)session.getAttribute("ActivityRegistryForm");
		if(activityRegistryForm !=null) {
			noteStr = (String) activityRegistryForm.get("note");
			obsStr = (String) activityRegistryForm.get("obs");
			instructorStr = (String) activityRegistryForm.get("instructor");			
			
			if (noteStr.equals("generic.activity.note.notapruved")) {
				selNota1 = "selected";
			}
			if (noteStr.equals("generic.activity.note.apruved")) {
				selNota2 = "selected";
			}
			if (noteStr.equals("generic.activity.note.apruvedwithobservations")) {
				selNota3 = "selected";
			}
			
		}

		CEPAActivityScheduleRegistryData registryData;
		
		// Borra cualquier resultado guardado en la sesión
		session.removeAttribute("registryData");
		
		if (activityReport.hasResult(new Integer(idActivity),new Integer(idDriver),sys.getUserSessionData())) {
			registryData = activityReport.getCompleteResult(
				new Integer(idActivity),new Integer(idDriver),sys.getUserSessionData());
			
			session.setAttribute("registryData",registryData);
					
			noteStr = registryData.getNote();
			obsStr = registryData.getObs();		
			instructorStr = registryData.getInstructor();	
			
			if (registryData.getNote().equals("generic.activity.note.notapruved")) {
				selNota1 = "selected";
			}
			if (registryData.getNote().equals("generic.activity.note.apruved")) {
				selNota2 = "selected";
			}
			if (registryData.getNote().equals("generic.activity.note.apruvedwithobservations")) {
				selNota3 = "selected";
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
						
		}
%>	
<LINK REL=StyleSheet HREF="../../util/css/estilo.css" TYPE="text/css">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
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
		
    	form2.noteoricoasistence.value = form1.checkboxasistence1.checked;				
		form2.nopracticoasistence.value = form1.checkboxasistence2.checked;	


		form2.idDriver.value = '<%=idDriver%>';
		form2.idActivity.value = '<%=idActivity%>';
		form2.url.value = '<%=url%>';				
		form2.submit();
	}	
}

function deschequeo(point,item){
		if ((point==1) && (item==1)) {
			document.forms['ActivityForm1'].check12.checked = false;																	
		}	
		if ((point==1) && (item==2)) {
			document.forms['ActivityForm1'].check11.checked = false;																	
		}	
		if ((point==2) && (item==1)) {
			document.forms['ActivityForm1'].check22.checked = false;																	
		}					
		if ((point==2) && (item==2)) {
			document.forms['ActivityForm1'].check21.checked = false;																	
		}						
		
}

</script>
<body>
<div align="center">


  <form name="ActivityForm1" method="post" action="">
  
 				<!--  Variables Ocultas: -->
				<input type="hidden" name="cepaCourse" value="true" />				
				<input type="hidden" name="partner" value="" />				
				<input type="hidden" name="conductionType" value="" />	
  
 <table width="600" border="0" cellspacing="0" cellpadding="0">  
 <tr> 
 <td height="40" colspan="5" valign="middle">
  <!--  Titulo -->
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr> 
      <td width="9">
      	<img src="/datacenter/util/imgs/titulo_left.gif" width="9" height="40">
      </td>
      <td width="600" background="/datacenter/util/imgs/bg2.gif">
		<div align="center">
			<font color="#FFFFFF" size="4" face="Arial, Helvetica, sans-serif">
			<strong>
			<%=messages.getText("generic.activity.form.formulario")%>:
			&nbsp;
			<%=messages.getText(activityData.getDefinition().getResourceKeyName())%>
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
  
<!-- Otros Datos --> 
<tr>
<td>
<table width="100%" border="0" cellpadding="0" cellspacing="0">

<!-- Area de circulacion --> 
<tr>
<!-- Tipo de vehiculo --> 
<td width="300" valign="top"> 	
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<!--  Titulo del Punto: -->
      <tr> 
        <td colspan="5" valign="top"> 
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="9"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                  <strong><%= messages.getText("generic.activity.asistence")%></strong></font></div></td>
              <td width="9"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <input type="checkbox" name="checkboxasistence1" class="check" <%=checkedNoTeoricoAsistenceStr %>> 
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
	</tr>
     <!--  dejo una linea en blanco entre cuadrante y cuadrante -->
     <tr>
     	<td>&nbsp;</td>
     </tr>      
</table>
</td>
</tr>
</table>
</td>
</tr>

<tr>
<td>
  <jsp:include  page='<%="ThreeColumnForm.jsp?cantCol=1" %>'/>
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
              
              
             <%
             		dataTypeReportFacade = sys.getDataTypeReportFacade();		
     				Collection notes = dataTypeReportFacade.findAll(DataTypeConstants.TYPECEPAACTIVITYNOTE,userSessionData);
             		if (notes != null){ 
             	%>
             		<select name="note" class="input">
             		
             		<%
             			Iterator it = notes.iterator();
             			while (it.hasNext()){
             				String activityNote =  ((CEPAActivityNoteDataType) it.next()).getResourceBundleId();
             				String selected = noteStr.equals(activityNote)?"selected":"";
             		%>
              			  <option value='<%= activityNote %>' class="input" <%= selected %> ><%= messages.getText(activityNote) %></option>
              		<%
              			}%>
               		</select> 
               <%
              		}%>
                
              </td>
              <td background="/datacenter/util/imgs/right_gray.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td valign="top" background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td bgcolor="#f9f9f9">&nbsp;</td>
              <td valign="top" background="/datacenter/util/imgs/right_gray.gif" bgcolor="#f9f9f9">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td  bgcolor="#f9f9f9"><font size="1" face="Arial, Helvetica, sans-serif"><strong><%= messages.getText("generic.activity.observations") %></strong></font></td>
              <td width="10" background="/datacenter/util/imgs/right_gray.gif" bgcolor="#f9f9f9">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" height="88" background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td bgcolor="#f9f9f9"><strong><font color="#FF0000" size="1" face="Arial, Helvetica, sans-serif"> 
                <textarea name="obs" cols="70" rows="5"><%= obsStr%></textarea>
                </font></strong></td>
              <td width="10" background="/datacenter/util/imgs/right_gray.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td valign="top" background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td bgcolor="#f9f9f9">&nbsp;</td>
              <td valign="top" background="/datacenter/util/imgs/right_gray.gif" bgcolor="#f9f9f9">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td bgcolor="#f9f9f9"><font size="1" face="Arial, Helvetica, sans-serif"> 
                <strong><%= messages.getText("generic.activity.instructor")%></strong></font> 
                <input type=text name="instructor" value = '<%= instructorStr%>'> </td>
              <td width="10" background="/datacenter/util/imgs/right_gray.gif" bgcolor="#f9f9f9">&nbsp;</td>
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
		   			value="Aceptar" onClick="javascript:setAction(document.forms['ActivityForm1'] ,document.forms['ActivityRegistryForm']);" class="input"/>	 		                             
            	</td>                 							
    	  	</tr>              
 </table>

</form>
      
<html:form action = "ActivityRegistrycreate" method="post">
	<html:hidden property="idDriver" value='<%=idDriver%>' />
	<html:hidden property="idActivity" value='<%=idActivity%>' />
	<html:hidden property="url" value='<%=url%>' />
	<html:hidden property="checkboxParamList" value="checkboxParamList" />
	<html:hidden property="obs" value="obs" />	
	<html:hidden property="note" value="note" />	
	<html:hidden property="instructor" value="instructor" />		
	<html:hidden property="partner" value="" />	
	<html:hidden property="cepaCourse" value="" />	
	<html:hidden property="conductionType" value="" />	
	<html:hidden property="trade" value="trade" />	
	<html:hidden property="plate" value="plate" />	
	<html:hidden property="initialKm" value="initialKm" />
	<html:hidden property="finalKm" value="finalKm" />
	<html:hidden property="initialHr" />
	<html:hidden property="initialMinute" />	
	<html:hidden property="finalHr" />				
	<html:hidden property="finalMinute" />			
	<html:hidden property="vehicleType" value="vehicleType" />									
	<html:hidden property="circulationAreaHighWay" value="false" />
	<html:hidden property="circulationAreaRoad" value="false" />
	<html:hidden property="circulationAreaSubUrban" value="false" />
	<html:hidden property="circulationAreaUrban" value="false" />										
	<html:hidden property="velocityBox" value="velocityBox" />											
	<html:hidden property="light" value="light" />	
	<html:hidden property="pointCommentList" value="" />			
	<html:hidden property="frenaje" />														
	<html:hidden property="noteoricoasistence" value="false" />								
	<html:hidden property="nopracticoasistence" value="false" />									
</html:form>
</div>
</body>

<%
	}	
%>	

</html:html>
