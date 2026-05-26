<%@page import="org.apache.struts.action.DynaActionForm"%>
<%@page language="java" 
	   errorPage="../../exception.jsp"
	import="business.*"
	import="java.util.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
   	import="communication.datatype.tables.*"
	import="communication.datatype.*"
	import="business.datatype.*"
	import="communication.activity.tables.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.*"
	import="communication.datatype.*"	
	import="business.activity.ActivityReportFacade"
	import="java.util.ArrayList"
	import="java.util.Set"
	import="java.util.Collection"
	import="java.util.Iterator"
	import="java.lang.*"
	import="communication.activity.tables.*"
	import="org.apache.struts.action.DynaActionForm"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<html:html>
<%
	String idDriver, idActivity, url = "";
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData; 
	userSessionData=sys.getUserSessionData();	
	// Obtengo el formulario de esa actividad para dibujarlo en el HTML:
	idActivity =(String)request.getParameter("idActivity");
	ActivityReportFacade activityReport = sys.getActivityReportFacade();
	CEPAActivityScheduleData  activityData;
	activityData = activityReport.findCEPAActivityScheduleByPrimaryKey(
			new Integer(Integer.parseInt(idActivity)),userSessionData);
	CheckListData checkList = activityData.getDefinition().getCheckList();
	Collection points = checkList.getPoints();	
	PointData point = null;
	ItemData item = null;
	Text messages=sys.getText();
	boolean encontrePoint = false;
	boolean encontreItem = false;
	String checkedStr = "";
	String peer = "";
	String superior = "";
	int primerPoint, ultimoPoint, totalPoints;
	DynaActionForm activityRegistryForm; 
	
	// Me fijo si ya tiene un registro de esta actividad ese conductor:
	idDriver=(String)request.getParameter("idDriver");
	// session.setAttribute("idDriver",idDriver);			
	CEPAActivityScheduleRegistryData registryData = activityReport.getCompleteResult(
			new Integer(idActivity),new Integer(idDriver),sys.getUserSessionData());	

	String noteStr = "", obsStr, instructorStr, selNota1, selNota2, selNota3;
	boolean noTeoricoAsistence, noPracticoAsistence;
	String checkedNoTeoricoAsistenceStr, checkedNoPracticoAsistenceStr;
	
	selNota1 = " ";
	selNota2 = " ";
	selNota3 = " ";

	String back=(String)request.getParameter("page");
	if (back == null || back == ""){
		back = "layout.jsp?refer=/activity/schedule/CEPAActivityScheduleView.jsp?id="+idActivity;
	}else if (back.equals("/driver/DriverView.jsp")){
		back = "layout.jsp?refer=/driver/DriverView.jsp?id="+idDriver;
	}else if (back.equals("lastpage")){
		back = "javascript:history.back();";
	}
	
	// Notas:
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
		if (registryData.getNote().equals("generic.activity.note.notapply")) {
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
	
	
	Collection itemsResult = registryData.getItemSet();	
	Collection pointComment = registryData.getPointCommentSet();

	// Pido Instructor o Compañero y tipo
	totalPoints = points.size();
	boolean print = Boolean.parseBoolean((String)request.getParameter("print"));
	String noeditable = "";
	boolean enabled = Boolean.parseBoolean((String)request.getParameter("enabled"));
	if (!enabled) 
	{
		noeditable = " disabled ";		
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

function schoolSubmit(form1,form2){
	if(preparedCheckboxRadioParameters(form1)) {		
		form2.checkboxParamList.value = form1.checkboxParamList.value;
		form2.commentaryDriveOther.value = "";
		form2.note.value = form1.note.value;
		form2.obs.value = form1.obs.value;		
		form2.instructor.value = form1.instructor.value;	
	   	form2.partner.value = "";	
		form2.cepaCourse.value = form1.cepaCourse.value;	
		form2.plate.value = "";	   
		form2.conductionType.value = "";
		form2.skidcar.value = "";
		form2.pointCommentList.value = "";
		form2.classNumber.value = form1.classNumber.value;
		form2.vehicleType.value = form1.vehicleType.value;
		form2.idDriver.value = '<%=idDriver%>';
		form2.idActivity.value = '<%=idActivity%>';
		form2.url.value = '<%=url%>';
		form2.submit();
	}
}
function setActionActivityView(form)
{
	form.submit();
}
</script>
<body>
<form name="SchoolForm" method="post" action="">
  <!--  Titulo -->
  <table width="600" border="0" cellpadding="0" cellspacing="0" align="center">
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
  <!--  Fin Titulo -->


	<table width="600" border="0" cellpadding="0" cellspacing="0" align="center">
		<!--  dejo una linea en blanco entre el titulo y los textos -->
	    <tr>
    		<td>&nbsp;</td>
	    </tr> 
	    <tr align="left">		
			<td class="input2">
				<font size="1" face="Arial, Helvetica, sans-serif"> 
				<%=messages.getText("generic.activity.escuela.name")%>
				</font>
			</td>
			<td>
				<font size="1" face="Arial, Helvetica, sans-serif"> <% 
			if ((registryData.getDriver() != null) &&
					registryData.getDriver().getFirstName() != null) { %>
				<input type="text" name="driver" class="input" style="width:200;" disabled="disabled"
					value='<%=registryData.getDriver().getFirstName()+ " "+registryData.getDriver().getLastName()%>'  />
<%			}else{ %>
				<input type=text name="driver" style="width:200;" class="input">				
<%			}%>
				</font>
			</td>
		</tr>
		<tr align="left">		
			<td class="input2">
				<font size="1" face="Arial, Helvetica, sans-serif"> 
				<%=messages.getText("generic.activity.escuela.instructor")%>
				</font>
			</td>
			<td>
				<font size="1" face="Arial, Helvetica, sans-serif"> <% 
			if ((registryData.getInstructor() != null)) { %>
				<input type="text" name="instructor" class="input" style="width:200;" value='<%=registryData.getInstructor()%>' />
<%			}else{ %>
				<input type=text name="instructor" style="width:200;" class="input">				
<%			}%>
				</font>
			</td>
		</tr>
		
		<!-- Date -->
		<tr align="left">		
			<td class="input2">
				<font size="1" face="Arial, Helvetica, sans-serif"> 
				<%=messages.getText("generic.activity.escuela.date")+"  "%> <%=messages.getText("generic.activity.escuela.dateformat")%>
				</font>
			</td>
			<td>
				<font size="1" face="Arial, Helvetica, sans-serif"> 
					<%
						Calendar c = Calendar.getInstance();
						c.setTime(registryData.getDate());
						String registryDay = String.valueOf(c.get(Calendar.DATE));
						String registryMonth = String.valueOf(c.get(Calendar.MONTH+1));
						String registryYear = String.valueOf(c.get(Calendar.YEAR));
					%>
					<input type="text" name="dateday" class="input" style="width:40;" value='<%=registryDay%>' disabled="disabled"/>/
					<input type="text" name="datemonth" class="input" style="width:40;" value='<%=registryMonth%>' disabled="disabled"/>/
					<input type="text" name="dateyear" class="input" style="width:80;" value='<%=registryYear%>' disabled="disabled"/>
				</font>
			</td>
		</tr>
		
		<!-- fin Date -->
		<tr align="left">
			<!-- Tipo de vehiculo:  -->				
			<td width="25%">
				<font size="1" face="Arial, Helvetica, sans-serif"> 
				<%=messages.getText("generic.activity.escuela.trucktype")+":"%>
				</font>
			</td>
			<td width="75%" align="left" colspan="2">
				<font size="1" face="Arial, Helvetica, sans-serif">
				<select name="vehicleType"  style="width:200" <%=noeditable%>>
<%          String message = null;
            String messageAll = messages.getText("datatype.vehicletype.all");
			DataTypeReportFacade dtReportFacade = sys.getDataTypeReportFacade();		
			Iterator iter6 = dtReportFacade.findAll(DataTypeConstants.TYPEVEHICLE,userSessionData).iterator();
			%>
			
			<option selected value=""><%=messages.getText("generic.messages.select")%></option>
<%			

			while(iter6.hasNext()) {
				String value = ((TypeVehicleDataType)iter6.next()).getResourceBundleId();
				//No mostrar el tipo de vehiculo Todos o All
				message = messages.getText(value);
				if (!message.equals(messageAll) && (value.equals("datatype.vehicletype.other")
						|| value.equals("datatype.vehicletype.tractortrailer") ||
						value.equals("datatype.vehicletype.trator") ||
						value.equals("datatype.vehicletype.truck")))					
				{					
					if ((registryData.getVehicleType() != null) &&
						(registryData.getVehicleType().equals(value))) {%>					
							<option  value='<%=value%>' selected  ><%=message%></option>
<%					}else{						
%>							<option value='<%=value%>' ><%=message%></option>
<%					}
				}
			}
%>
				</select>
				</font>
				<!--  Variables Ocultas: -->
				<input type="hidden" name="cepaCourse" value="true" />	
				<input type="hidden" name="commentaryDrive" value="false" />	
				<input type="hidden" name="commentaryDriveOther" value="false" />				
				<input type="hidden" name="conductionType" value="" />							
				<input type="hidden" name="pointCount" value='<%=totalPoints%>' />
			</td>
			
		</tr>
		<!-- Numero de Clase: -->
		
		<tr align="left">		
			<td class="input2">
				<font size="1" face="Arial, Helvetica, sans-serif"> 
				<%=messages.getText("generic.activity.escuela.clase")%>
				</font>
			</td>
			<td class="input2">
				
				<select name="classNumber"  <%=noeditable%>>
<%			if (( registryData.getClassNumber() > 0)) {
				for (int i = 1; i<=15; i++){
					if (registryData.getClassNumber() == i){ %>
					
						<option value='<%=i%>' selected >
							<%=i%>
						</option>						
					
<%					}else{ %>
					
						<option value='<%=i%>' >
						<%=i%></option>						

<%					}
				}
			}else{ %>
			
					<option selected  value="-1" ><%=messages.getText("generic.messages.select")%></option>						
				
<%				for (int i = 1; i<=15; i++){ %>

					<option value='<%=i%>' ><%=i%></option>						
<%				}
			}
%>
				</select>
			</td>
			
		</tr>
		
		<!--  Fin numero de clase -->
	</table>	
	<br/><br/>
	
<%
	primerPoint = 1;
	ultimoPoint = totalPoints;
	for (int i = primerPoint; i <= ultimoPoint; i++){
	// Creo una tabla por cada point y un renglon por cada item de cada point.	
	// Tengo que fijarme si el orden del punto es el correcto:
		Iterator itPoints = points.iterator();
		encontrePoint = false;
		while (!encontrePoint && itPoints.hasNext())
		{
			point =	(PointData)itPoints.next();
			if (point.getPointOrder().intValue() == i)
				encontrePoint = true;
		}
		if (!encontrePoint) break;
		encontreItem = false;
%>
<table width="600" border="0" cellpadding="0" cellspacing="0" align="center">
	<!--  Titulo del Punto: -->
	<tr> 
    	<td width="9" valign="top">
    		<img src="/datacenter/util/imgs/left_form.gif" >
    	</td>    	
        <td background="/datacenter/util/imgs/center_form.gif" valign="top"> 
        	<div align="center">
        		<!--  Cambiar esto por un estilo !! -->
        		<font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"><strong>
        		<%=messages.getText(point.getText())%>
        		</strong></font>
        	</div>
        </td>
        <td width="9" valign="top">
        	<img src="/datacenter/util/imgs/right_form.gif" >
        	<input type="hidden" name="pointsCount" value='<%=totalPoints%>' />        	
        </td>
     </tr>
     

	 <!--  Subtitulos de Seleccion de los radiobutton -->
     <tr>
     	<!--  Linea Izquierda -->
        <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;
        </td>
        <td>
	        <table width="100%" >
				<tr>
					<td width="50%">&nbsp;</td>
					<td width="20%" >
						<font size="1" face="Arial, Helvetica, sans-serif">
        				<%=messages.getText("generic.activity.escuela.apply")%>
        				</font>
        			</td>
					<td width="30%" >
						<font  size="1" face="Arial, Helvetica, sans-serif">
						<%=messages.getText("generic.activity.escuela.hacerseguimiento")%>						
						</font>
					</td>	
				</tr>
	    	</table>
	    </td>
        <!--  Linea Derecha -->
        <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;
        </td>
     </tr>
    <!--  Dibujo los items del punto: -->
<%
	boolean par = true;
	for (int j= 1; j <= point.getItems().size(); j++)
	{
		Iterator itItems = point.getItems().iterator();
		encontreItem = false;
		// Tengo que fijarme si el orden del item es el correcto:
		while (!encontreItem && itItems.hasNext())
		{
			item = (ItemData)itItems.next();
			if (item.getItemOrder().intValue() == j)
				encontreItem = true;
		}
		if (!encontreItem) break;
%>
	 <!--  Dibujo Item: -->
     <tr>
      
     	<!--  Linea Izquierda -->
        <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
		<!--  Item y radiobuttons -->	 
<%		
		if (!par) {
%>       
		<td>        
<%
		} else {
%>
		<td class="lineone">
<%		}
%>
        <table width="100%" >
		<tr title='<%=messages.getText(item.getText())%>'>
		
		<td width="50%" >
            <!--  Cambiar esto por un estilo !! -->
            <font size="1" face="Arial, Helvetica, sans-serif" > 
            <%= messages.getText(item.getText())%>
            </font>          
        </td>
<%
		Integer value = registryData.getValueFromItem(item);
		if (value == null) value = new Integer(0);
		if (print) value = new Integer(-1);
		System.out.println("Item Id: "+ item.getId()+ "Item Value:"+value);
		switch (value.intValue()) {
			case 1: %>
	        	<td width="20%"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()%>-1' class="check" checked/></td>
	    	    <td width="30%"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()%>-2' class="check" <%=noeditable%> /></td>	  
<%					break;
			case 2: %>
		        <td width="20%"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()%>-1' class="check" <%=noeditable%> /></td>
		        <td width="30%"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()%>-2' class="check" checked/></td>

<%					break;
			case -1:%>
	        	<td width="20%"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()%>-1' class="check" <%=noeditable%> /></td>
    	    	<td width="30%"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()%>-2' class="check" <%=noeditable%> /></td>
        		
<%					break;
			default: %>
		        <td width="20%"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()%>-1' class="check"  checked/></td>
		        <td width="30%"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()%>-2' class="check" <%=noeditable%> /></td>

<%		}
%>
		</tr>
		</table>
		</td>
       
        <!--  Linea Derecha -->
       <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
     </tr>
<%
			par = !par;
		} // fin for Items
		
%>

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
<%
	} // fin for Points
%>

	<!-- Nota: -->
	<tr> 
    	<td width="9">
    		<img src="/datacenter/util/imgs/left_form.gif" >
    	</td>    	
        <td background="/datacenter/util/imgs/center_form.gif" valign="top"> 
        	<div align="center">
        	<!--  Cambiar esto por un estilo !! -->
        		<font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"><strong>
        		<%= messages.getText("generic.activity.escuela.calificacion") %>
        		</strong></font>
        	</div>
        </td>
        <td width="9">
        	<img src="/datacenter/util/imgs/right_form.gif" >        	
        </td>
     </tr>
     <tr class="lineone"> 
              <td background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td bgcolor="#f7f7f7"> 
              
              <%
             		DataTypeReportFacade dataTypeReportFacade = sys.getDataTypeReportFacade();		
     				Collection notes = dataTypeReportFacade.findAll(DataTypeConstants.TYPECEPAACTIVITYNOTE,userSessionData);
             		if (notes != null){ 
             	%>
             		<select name="note" class="input" <%=noeditable%>>
             		
             		<%
             			Iterator it = notes.iterator();
             			while (it.hasNext()){
             				String activityNote =  ((CEPAActivityNoteDataType) it.next()).getResourceBundleId();
             				String selected = (noteStr != null && noteStr.equals(activityNote))?"selected":"";
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
        <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
        <td valign="top" background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
        <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
     </tr>
            
            
    <!-- Observaciones: -->
     <tr>
     	<td>&nbsp;</td>
     </tr>      
   	<tr> 
    	<td width="9">
    		<img src="/datacenter/util/imgs/left_form.gif" >
    	</td>    	
        <td background="/datacenter/util/imgs/center_form.gif" valign="top"> 
        	<div align="center">
        	<!--  Cambiar esto por un estilo !! -->
        		<font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"><strong>
        		<%=messages.getText("generic.activity.escuela.observations")%>
        		</strong></font>
        	</div>
        </td>
        <td width="9">
        	<img src="/datacenter/util/imgs/right_form.gif" >        	
        </td>
     </tr>
     <tr> 
       <td width="9" height="88" background="/datacenter/util/imgs/left_gray.gif">&nbsp;
       </td>
       <td>       		
<%			String obs = registryData.getObs();
			//System.out.println("Observaciones:"+obs);
			if ( (obs != null) && (obs.trim().length() > 0)
					&& (!obs.trim().equals("")))
			{  
	%>
				<textarea name="obs" style="width:100%" rows="5" <%=noeditable%> ><%=obs.trim()%></textarea>
<%			}else{ %>
				<textarea name="obs" style="width:100%" rows="5" <%=noeditable%> ></textarea>
<%			}%> 
		
       </td>
       <td width="10" background="/datacenter/util/imgs/right_gray.gif">&nbsp;</td>
     </tr>
     <tr> 
        <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
        <td valign="top" background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
        <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
     </tr>
	 <!--  Fin Observaciones -->
</table>
<br>

<!--  Botones: -->
<table width="600" border="0" cellpadding="0" cellspacing="0" align="center">
   	<tr>   		
   		<td align="right">
 		    <input type="hidden" name="checkboxParamList" />
   			<!-- Boton de Retorno -->
   			<input name="button1" type="button" 
   			value='<%=messages.getText("generic.messages.back") %>' 
   			onClick="javascript:setActionActivityView(document.forms['FormBack']);"
   			class="input"/>			   			
   		<%if (enabled){ %>
   			<!-- Boton de Guardar Evaluacion en Cabina -->
   			<input name="button2" type="button" 
   			value='<%=messages.getText("generic.messages.save") %>' 
   			onClick="javascript:schoolSubmit(document.forms['SchoolForm'] ,document.forms['ActivityRegistryForm']);" class="input"/>   			
   		<%} %>
		</td>
	</tr>
</table>   			
</form>
<html:form action="ActivityRegistrycreate" method="post" >
	<html:hidden property="idActivity" value='<%=idActivity%>'/>
	<html:hidden property="idDriver" value='<%=idDriver%>'/>
	<html:hidden property="url" value='<%=url%>'/>
	<html:hidden property="checkboxParamList" value="checkboxParamList" />
	<html:hidden property="obs" value="" />	
	<html:hidden property="note" value="" />	
	<html:hidden property="instructor" value="" />	
	<html:hidden property="partner" value="" />	
	<html:hidden property="cepaCourse" value="" />	
	<html:hidden property="conductionType" value="" />
	<html:hidden property="plate" value="" />	
	<html:hidden property="skidcar" value="" />	
	<html:hidden property="pointCommentList" value="" />
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
	<html:hidden property="commentaryDriveOther" value="" />					
	<html:hidden property="frenaje"/>
	<html:hidden property="classNumber" value="" />	
</html:form>


<form name="FormBack" method="post" action='<%=back%>' />


</body>

</html:html>
