<%@page language="java" 
	errorPage="../../exception.jsp"
	import="business.*"
	import="java.io.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.activity.tables.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.ActivityTypeDataType"
	import="business.activity.ActivityReportFacade"
	import="business.datatype.*"
	import="java.lang.*"
	import="java.text.*"
	import="communication.activity.tables.*"
    import="presentation.translator.web.vehicle.*"	
   	import="communication.datatype.tables.*"
	import="communication.datatype.*"
	import="java.util.*"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<html:html>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData; 
	userSessionData=sys.getUserSessionData();	
	// Obtengo el formulario de esa actividad para dibujarlo en el HTML:
	String idActivity =(String)request.getParameter("idActivity");
	if (Util.empty(idActivity)){
		idActivity = (String)session.getAttribute("idActivity");		
	}
	//System.out.println("BtwThailand.jsp: idActivity: "+idActivity);
	
	ActivityReportFacade activityReport = sys.getActivityReportFacade();
	CEPAActivityScheduleData  activityData;
	activityData = activityReport.findCEPAActivityScheduleByPrimaryKey(
			new Integer(idActivity.trim()),userSessionData);
	CheckListData checkList = activityData.getDefinition().getCheckList();
	Collection points = checkList.getPoints();	
	PointData point = null;
	ItemData item = null;
	ItemData item2 = null;
	Text messages=sys.getText();
	boolean encontrePoint = false;
	boolean encontreItem = false;

	int primerPoint, ultimoPoint, totalPoints;
	boolean print = false;
	String printable =(String)request.getParameter("print");
	if (Util.empty(printable)){
		print = false;
	}else{
		print = Boolean.parseBoolean(printable);
	}
	// Me fijo si ya tiene un registro de esta actividad ese conductor:
	String idDriver=(String)request.getParameter("idDriver");
	if (Util.empty(idDriver)){
		idDriver = (String)session.getAttribute("idDriver");		
	}
	//session.setAttribute("idDriver",idDriver);			
	//System.out.println("BtwThailand.jsp: idDriver: "+idDriver);
	CEPAActivityScheduleRegistryData registryData = activityReport.getCompleteResult(
			new Integer(idActivity.trim()),new Integer(idDriver.trim()),
			sys.getUserSessionData());		
	
	String checked = (registryData.getPhrd()) ? "checked" : "";
	
	String back=(String)request.getParameter("page");
	if (back == null || back == ""){
		back = "layout.jsp?refer=/activity/schedule/CEPAActivityScheduleView.jsp?id="+idActivity;
	}else if (back.equals("/driver/DriverView.jsp")){
		back = "layout.jsp?refer=/driver/DriverView.jsp?id="+idDriver;
	}else if (back.equals("lastpage")){
		back = "javascript:history.back();";
	}
	
	totalPoints = points.size();
	String noeditable = "";
	boolean enabled = Boolean.parseBoolean((String)request.getParameter("enabled"));
	if (!enabled) 
	{
		noeditable = " disabled ";		
	}
	
%>
<script language="JavaScript" type="text/javascript">
function preparedCheckboxRadioParametersBTWThailand(form){

		var elements = form.elements;
		var length = elements.length;
		var i;
		var valueCheckboxParamList="";
		var separator="<";
		for(i = 0; i < length; i++){
			var element = elements[i];
			if((element.type.toLowerCase()=="checkbox") || (element.type.toLowerCase()=="radio")) {
				if(element.checked && (element.name != "phrd")){
					if(valueCheckboxParamList==""){
						valueCheckboxParamList=element.value;
					}
					else{
						valueCheckboxParamList=element.value+"<"+valueCheckboxParamList;
					}	
				}	
			} 
		
		}		
		form.checkboxParamList.value = valueCheckboxParamList;				
		return true;
}
function setActionBTWThailand(form1,form2){
	
	if(preparedCheckboxRadioParametersBTWThailand(form1)) {
		form2.checkboxParamList.value = form1.checkboxParamList.value;
		form2.obs.value = form1.obs.value;		
		form2.instructor.value = form1.instructor.value;	
		form2.drivingExperience.value = form1.drivingExperience.value;
		form2.vehicleType.value = form1.vehicleType.value;
		form2.phrd.value = form1.phrd.checked;		
		form2.submit();
	}
}

function setActionActivityView(form)
{
	form.submit();
}

</script>
<LINK REL=StyleSheet HREF="../../util/css/estilo.css" TYPE="text/css">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<div align="center">  
  <form name="ActivityForm1" method="post" action="">
  
<table width="700" border="0" cellpadding="0" cellspacing="0">
<tr><td colspan="1" width="700">
  <!--  Titulo -->
  <table width="700" border="0" cellpadding="0" cellspacing="0">
    <tr> 
      <td width="9">
      	<img src="/datacenter/util/imgs/titulo_left.gif" width="9" height="40">
      </td>
      <td width="682" background="/datacenter/util/imgs/bg2.gif">
		<div align="center">
			<font color="#FFFFFF" size="4" face="Arial, Helvetica, sans-serif">
			<strong>
			<%=messages.getText("generic.activity.form.formulario")%>:
			&nbsp;
			<%=messages.getText("generic.activity.form.btwthailand.title")%>
            </strong>
            </font>
            <font color="#FFFFFF" size="3" face="Arial, Helvetica, sans-serif">
            <br>
			<strong>
            <%=messages.getText("generic.activity.form.btwthailand.subtitle")%>
            </strong>
            </font>            
        </div>
      </td>
      <td width="9">
      	<img src="/datacenter/util/imgs/titulo_right.gif" width="9" height="40">
      </td>
    </tr>
  </table>
</td></tr>
<tr><td width="700" colspan="1">
  <!--  Fin Titulo -->	
	<table width="700" border="0" cellpadding="0" cellspacing="0" align="left">
		<!--  dejo una linea en blanco entre el titulo y los textos -->
	    <tr>
    		<td colspan="3">&nbsp;</td>
	    </tr>	
	    
	    <tr align="left" >
	    	<!--  Empleado Evaluado -->
	    	<td width="25%">
				<font size="1" face="Arial, Helvetica, sans-serif"> 
				<%=messages.getText("generic.activity.form.btwthailand.name")%>
				</font>
			</td>
			<td width="75%" align="left" colspan="2" >
				<font size="1" face="Arial, Helvetica, sans-serif"><strong>
				<%=(!print)?registryData.getDriver().getFirstName() + " " + registryData.getDriver().getLastName():"" %>									
				</strong></font>
			</td>			
	    </tr>
	    <tr align="left">
	    	<td width="25%">
				<font size="1" face="Arial, Helvetica, sans-serif"> 
				<%=messages.getText("generic.activity.form.btwthailand.drivingexperience")%>
				</font>
			</td>
			<td width="15%" align="left">
				<font size="1" face="Arial, Helvetica, sans-serif">
<%
				if ( registryData.getDrivingExperience() != 0 ){ 
					//System.out.println("BtwThailand.jsp - Driving Experience:"+registryData.getDrivingExperience());
%>
				<input type="text" name="drivingExperience" class="input" width="100" value='<%=registryData.getDrivingExperience()%>' <%=noeditable%> >				
<%				}else{
%>
				<input type=text name="drivingExperience" class="input" width="100" <%=noeditable%> >
<%				} %>				
				</font>
			</td>	
			<td width="60%" class="input2" align="left">
			<%=" "+messages.getText("generic.activity.form.btwthailand.year(s)")%>	    	
			</td>			
	    </tr>
		<tr align="left">
			<!-- Tipo de vehiculo:  -->				
			<td width="25%">
				<font size="1" face="Arial, Helvetica, sans-serif"> 
				<%=messages.getText("generic.activity.form.btwthailand.typeofvehicle")+":"%>
				</font>
			</td>
			<td width="75%" align="left" colspan="2">
				<font size="1" face="Arial, Helvetica, sans-serif"> 
				<select name="vehicleType"  style="width:200" <%=noeditable%>>
<%          String message = null;
            String messageAll = messages.getText("datatype.vehicletype.all");
			DataTypeReportFacade dtReportFacade = sys.getDataTypeReportFacade();		
			Iterator iter6 = dtReportFacade.findAll(DataTypeConstants.TYPEVEHICLE,userSessionData).iterator();
			if ((registryData.getDriver().getVehicleAllowed() == null)){	%>
				<option selected value=""><%=messages.getText("generic.messages.select")%></option>
<%			}
			while(iter6.hasNext()) {
				String value = ((TypeVehicleDataType)iter6.next()).getResourceBundleId();
				//No mostrar el tipo de vehiculo Todos o All
				message = messages.getText(value);
				if (!message.equals(messageAll))
				{					
					if ((registryData.getVehicleType() != null) &&
						(registryData.getVehicleType().equals(value))) {%>					
							<option selected  value='<%=value%>'  ><%=message%></option>
<%					}else{						
%>							<option value='<%=value%>' ><%=message%></option>
<%					}
				}
			}
%>
				</select>
				</font>
				<!--  Variables Ocultas: -->
				<input type="hidden" name="cepaCourse" value="false" />	
				<input type="hidden" name="commentaryDrive" value="false" />	
				<input type="hidden" name="commentaryDriveOther" value="false" />				
				<input type="hidden" name="conductionType" value="" />
				<input type="hidden" name="note" value="" />				
			</td>
			
		</tr>
		<!-- Instructor -->
		<tr>
			<td width="25%">
				<font size="1" face="Arial, Helvetica, sans-serif"> 
				<%=messages.getText("generic.activity.instructor")+":"%>
				</font>
			</td>
			<td width="75%"  colspan="2" align="left">
				<font size="1" face="Arial, Helvetica, sans-serif"> 
<% 			if ((registryData.getInstructor() != null)) { %>
				<input type="text" name="instructor" class="input" 
				width="100" value='<%=registryData.getInstructor()%>' <%=noeditable%> />				
<%			}else{ %>
				<input type="text" name="instructor" class="input" width="100" <%=noeditable%> />				
<%			}%>
				</font>
			</td>	
			
		</tr>
		
					<tr>
		    			<td colspan="3" width="100%">&nbsp;</td>
			    	</tr>
					<tr>
						<!-- phrd: -->
				 		<td colspan="3" valign="top"> 
		          			<table width="100%" border="0" cellpadding="0" cellspacing="0">
		            			<tr> 
		              				<td width="9">
		              					<img src="/datacenter/util/imgs/left_form.gif" width="9" height="15">
		              				</td>
		              				<td background="/datacenter/util/imgs/center_form.gif"> 
		              					<div align="center">
		              						<font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
		                  						<strong><%= messages.getText("generic.activity.phrdbycourse")%></strong>
		                  					</font>
		                  				</div>
		                  			</td>
		              				<td width="9">
		              					<strong>
		              						<font color="#FFFFFF">
		              							<img src="/datacenter/util/imgs/right_form.gif" width="9" height="15">
		              						</font>
		              					</strong>
		              				</td>
		            			</tr>
		            			<tr> 
		              				<td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
		              				<td> 
		              					<input type="checkbox" name="phrd" class="check" <%=checked%> > 
		                				<font size="1" face="Arial, Helvetica, sans-serif"> 
		                					<%= messages.getText("generic.activity.phrdbycourse.isphrd")%>
		                				</font> 
		              				</td>
		              				<td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
		            			</tr>
		            			<tr> 
		              				<td width="9" height="19" valign="top">
		              					<img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11">
		              				</td>
		              				<td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
		              				<td width="9" valign="top">
		              					<img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11">
		              				</td>
		            			</tr>           
		          			</table>            
						</td>	
					</tr>	    
					<tr>
		    			<td colspan="3" width="100%">&nbsp;</td>
			    	</tr>
				</table>
			</td>
		</tr>
<%
	primerPoint = 1;
	ultimoPoint = totalPoints;
	for (int i = primerPoint; i <= ultimoPoint; i++) {
	// Creo una tabla por cada point y un renglon por cada item de cada point.	
	// Tengo que fijarme si el orden del punto es el correcto:
		Iterator itPoints = points.iterator();
		encontrePoint = false;
		while (!encontrePoint && itPoints.hasNext())	{
			point =	(PointData)itPoints.next();
			if (point.getPointOrder().intValue() == i)
				encontrePoint = true;
		}
		if (!encontrePoint) break;
		encontreItem = false;
%>
<tr><td width="700" colspan="1">
	<table width="700" border="0" cellpadding="0" cellspacing="0">
	<!--  Titulo del Punto: -->
	<tr> 
    	<td width="9" valign="top">
    		<img src="/datacenter/util/imgs/left_form.gif" >
    	</td>    	
        <td width="682" background="/datacenter/util/imgs/center_form.gif" valign="top"> 
        	<div align="center">
        		<!--  Cambiar esto por un estilo !! -->
        		<font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"><strong>
        		<%=messages.getText(point.getText())%>
        		</strong></font>
        	</div>
     	 <input type="hidden" name="pointsCount" value='<%=totalPoints%>' />
        </td>
        <td width="9" valign="top">
        	<img src="/datacenter/util/imgs/right_form.gif" >        	
        </td>
     </tr>
     <!--  Subtitulos de Seleccion de los radiobutton -->
     <tr>
     	<!--  Linea Izquierda -->
        <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
        <td width="682" >
	        <table width="682" >
				<tr>
					<!--  Izquierda -->
					<td width="35%">&nbsp;</td>
					<td width="5%" align="center"  title='<%=messages.getText("generic.activity.form.btwthailand.unsatisfactory")%>' >
						<font size="1" face="Arial, Helvetica, sans-serif">
        				<%=messages.getText("generic.activity.form.btwthailand.U")%>
        				</font>
        			</td>
					<td width="5%" align="center"  title='<%=messages.getText("generic.activity.form.btwthailand.satisfactory")%>' >
						<font  size="1" face="Arial, Helvetica, sans-serif">
						<%=messages.getText("generic.activity.form.btwthailand.S")%>
						</font>
					</td>						
					<td width="5%" align="center"  title='<%=messages.getText("generic.activity.form.btwthailand.good")%>' >
						<font size="1" face="Arial, Helvetica, sans-serif">
						<%=messages.getText("generic.activity.form.btwthailand.G")%>
						</font>
					</td>
					
					<!-- Derecha -->
					<td width="35%">&nbsp;</td>
					<td align="center"  width="5%" title='<%=messages.getText("generic.activity.form.btwthailand.unsatisfactory")%>' >
						<font size="1" face="Arial, Helvetica, sans-serif">
        				<%=messages.getText("generic.activity.form.btwthailand.U")%>
        				</font>
        			</td>
					<td align="center"  width="5%" title='<%=messages.getText("generic.activity.form.btwthailand.satisfactory") %>' >
						<font  size="1" face="Arial, Helvetica, sans-serif">
						<%=messages.getText("generic.activity.form.btwthailand.S")%>
						</font>
					</td>						
					<td align="center" width="5%" title='<%=messages.getText("generic.activity.form.btwthailand.good")%>' >
						<font size="1" face="Arial, Helvetica, sans-serif">
						<%=messages.getText("generic.activity.form.btwthailand.G")%>
						</font>
					</td>
				</tr>
	    	</table>
	    </td>
	    <!--  Linea Derecha -->
        <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
     </tr>  
     
     	 
<!--  Dibujo los items del punto: -->
<%
	boolean par = true;
	if (point.getItems() != null)
	{
		ArrayList orderedItems = point.getOrderedItems();
		Iterator itItems = orderedItems.iterator();	
		for (int j= 1; j <= point.getItems().size()/2 ; j++)
		{
			encontreItem = false;
			if (orderedItems != null){			
				// Tengo que fijarme si el orden del item es el correcto:
				while (!encontreItem && itItems.hasNext())
				{
					item = (ItemData)itItems.next();
					if (itItems.hasNext()){
						item2 = (ItemData)itItems.next();
					}
					if (item != null)
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
		<td width="682">        
<%
			} else {
%>
		<td class="lineone" width="682" >
<%			}
%>
       		<table width="682" >
		  	  <tr>
		
				<td width="239" title='<%=messages.getText(item.getText())%>' >
		            <font size="1" face="Arial, Helvetica, sans-serif" > 
		            <%= messages.getText(item.getText())%></font>          
		        </td>
<%
			Integer value = registryData.getValueFromItem(item);			
			if (value == null) value = new Integer(0);
			// if (print) value = new Integer(-1);
			switch (value.intValue()) {
				case 1: %>
	        	<td width="34"><input type="radio"  name='<%=item.getId().toString()%>'  value='<%=item.getId().toString()+"-1" %>'   checked ></td>
	    	    <td width="34"><input type="radio" name='<%=item.getId().toString()%>'  value='<%=item.getId().toString()+"-2" %>'   <%=noeditable%> ></td>
	        	<td width="34"><input type="radio" name='<%=item.getId().toString()%>'  value='<%=item.getId().toString()+"-3" %>'   <%=noeditable%> ></td>
<%					break;
				case 2: %>
		        <td width="34"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()+"-1" %>'   <%=noeditable%> ></td>
		        <td width="34"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()+"-2" %>'   checked ></td>
		        <td width="34"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()+"-3" %>'   <%=noeditable%> ></td>				
<%					break;
				case 3:%>
	        	<td width="34"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()+"-1" %>'   <%=noeditable%> ></td>
	        	<td width="34"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()+"-2" %>'   <%=noeditable%> ></td>
	        	<td width="34"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()+"-3" %>'    checked ></td>				
<%					break;
				case -1:%>
	        	<td width="34"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()+"-1" %>'   <%=noeditable%> ></td>
    	    	<td width="34"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()+"-2" %>'   <%=noeditable%> ></td>
        		<td width="34"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()+"-3" %>'   <%=noeditable%> ></td>				
<%					break;
				default: %>
		        <td width="34"><input type="radio"   name='<%=item.getId().toString()%>'  value='<%=item.getId().toString()+"-1" %>'    checked ></td>
		        <td width="34"><input type="radio"   name='<%=item.getId().toString()%>'  value='<%=item.getId().toString()+"-2" %>'   <%=noeditable%> ></td>
		        <td width="34"><input type="radio"   name='<%=item.getId().toString()%>'  value='<%=item.getId().toString()+"-3" %>'   <%=noeditable%> ></td>													
<%					break;
				}
%>
			 
      
      
      	<!-- Derecha: -->
		<td width="239" title='<%=messages.getText(item2.getText())%>' >           
            <font size="1" face="Arial, Helvetica, sans-serif" > 
            <%=messages.getText(item2.getText())%>
            </font>          
        </td>
        
<%
			value = registryData.getValueFromItem(item2);
			if (value == null) value = new Integer(0);
			if (print) value = new Integer(-1);
			switch (value.intValue()) {
				case 1: %>
	        	<td width="34"><input type="radio" name='<%=item2.getId().toString()%>' value='<%=item2.getId().toString()+"-1"%>'   checked ></td>
	    	    <td width="34"><input type="radio" name='<%=item2.getId().toString()%>' value='<%=item2.getId().toString()+"-2"%>'   <%=noeditable%> ></td>
	        	<td width="34"><input type="radio" name='<%=item2.getId().toString()%>' value='<%=item2.getId().toString()+"-3"%>'   <%=noeditable%> ></td>
<%					break;
				case 2: %>
		        <td width="34"><input type="radio" name='<%=item2.getId().toString()%>' value='<%=item2.getId().toString()+"-1"%>'   <%=noeditable%> ></td>
		        <td width="34"><input type="radio" name='<%=item2.getId().toString()%>' value='<%=item2.getId().toString()+"-2"%>'   checked ></td>
		        <td width="34"><input type="radio" name='<%=item2.getId().toString()%>' value='<%=item2.getId().toString()+"-3"%>'   <%=noeditable%> ></td>				
<%					break;
				case 3:%>
	        	<td width="34"><input type="radio" name='<%=item2.getId().toString()%>' value='<%=item2.getId().toString()+"-1"%>'   <%=noeditable%> ></td>
	        	<td width="34"><input type="radio" name='<%=item2.getId().toString()%>' value='<%=item2.getId().toString()+"-2"%>'   <%=noeditable%> ></td>
	        	<td width="34"><input type="radio" name='<%=item2.getId().toString()%>' value='<%=item2.getId().toString()+"-3"%>'    checked ></td>				
<%					break;
				case -1:%>
	        	<td width="34"><input type="radio" name='<%=item2.getId().toString()%>' value='<%=item2.getId().toString()+"-1"%>'   <%=noeditable%> ></td>
    	    	<td width="34"><input type="radio" name='<%=item2.getId().toString()%>' value='<%=item2.getId().toString()+"-2"%>'   <%=noeditable%> ></td>
        		<td width="34"><input type="radio" name='<%=item2.getId().toString()%>' value='<%=item2.getId().toString()+"-3"%>'   <%=noeditable%> ></td>				
<%					break;
				default: %>
		        <td width="34"><input type="radio" name='<%=item2.getId().toString()%>' value='<%=item2.getId().toString()+"-1"%>'    checked ></td>
		        <td width="34"><input type="radio" name='<%=item2.getId().toString()%>' value='<%=item2.getId().toString()+"-2"%>'   <%=noeditable%> ></td>
		        <td width="34"><input type="radio" name='<%=item2.getId().toString()%>' value='<%=item2.getId().toString()+"-3"%>'   <%=noeditable%> ></td>													
<%					break;
				}
%>
				</tr>
			</table>
		</td>
        <!--  Linea Derecha -->
       <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
     </tr>
<%
				par = !par;
			}
		} // fin for Items
	}	
%>       
     <!--  Dibujo linea inferior de los cuadrantes -->
 	 <tr> 
        <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
        <td width="682" background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
        <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
     </tr>
     <!--  dejo una linea en blanco entre cuadrante y cuadrante -->
     <tr>
     	<td width="100%">&nbsp;</td>
     </tr>      
  	
</table>
</td></tr>   
  
<%
	} // fin for Points
%>

<tr><td width="700" colspan="1">
    <!-- Observaciones: -->
    <table width="700" border="0" cellpadding="0" cellspacing="0">
   	<tr> 
    	<td width="9">
    		<img src="/datacenter/util/imgs/left_form.gif" >
    	</td>    	
        <td width="682" background="/datacenter/util/imgs/center_form.gif" valign="top"> 
        	<div align="center">
        	<!--  Cambiar esto por un estilo !! -->
        		<font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"><strong>
        		<%=messages.getText("generic.activity.form.btwthailand.comments")%>
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
			{  %>
				<textarea name="obs" style="width:100%" rows="5" <%=noeditable%> ><%=obs.trim()%></textarea>
<%			}else{ %>
				<textarea name="obs" style="width:100%" rows="5" <%=noeditable%> ></textarea>
<%			}%> 
		
       </td>
       <td width="9" background="/datacenter/util/imgs/right_gray.gif">&nbsp;</td>
     </tr>
     <tr> 
        <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
        <td width="682" valign="top" background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
        <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
     </tr>
    </table>
    </td></tr>
	 <!--  Fin Observaciones -->
	 
	 <!--  Definiciones -->
	 <tr><td colspan="1" width="700">
	<table width="700" border="0" cellpadding="0" cellspacing="0">
		<tr>
    		<td width="100%">&nbsp;</td>
	    </tr>   
		<tr>
			<td width="100%">
				<font color="#000000" size="1" face="Arial, Helvetica, sans-serif"><strong>
    		    <%=messages.getText("generic.activity.form.btwthailand.definitions")%><br>
	       		</strong>
	       		<%=messages.getText("generic.activity.form.btwthailand.U")%>: <%=messages.getText("generic.activity.form.btwthailand.unsatisfactory") %><br>
	       		<%=messages.getText("generic.activity.form.btwthailand.S")%>: <%=messages.getText("generic.activity.form.btwthailand.satisfactory") %><br>
	       		<%=messages.getText("generic.activity.form.btwthailand.G")%>: <%=messages.getText("generic.activity.form.btwthailand.good") %><br>
	       		<%=messages.getText("generic.activity.form.btwthailand.definitions.bewagon") %><br>
	       		<%=messages.getText("generic.activity.form.btwthailand.definitions.msm") %><br>
	       		<%=messages.getText("generic.activity.form.btwthailand.definitions.the8commandments") %><br>
	       		&nbsp;&nbsp;<%=messages.getText("generic.activity.form.btwthailand.definitions.1") %><br>
	       		&nbsp;&nbsp;<%=messages.getText("generic.activity.form.btwthailand.definitions.2") %><br>
	       		&nbsp;&nbsp;<%=messages.getText("generic.activity.form.btwthailand.definitions.3") %><br>
	       		&nbsp;&nbsp;<%=messages.getText("generic.activity.form.btwthailand.definitions.4") %><br>
	       		&nbsp;&nbsp;<%=messages.getText("generic.activity.form.btwthailand.definitions.5") %><br>
	       		&nbsp;&nbsp;<%=messages.getText("generic.activity.form.btwthailand.definitions.6") %><br>
	       		&nbsp;&nbsp;&nbsp;&nbsp;<%=messages.getText("generic.activity.form.btwthailand.definitions.6.1") %><br>
	       		&nbsp;&nbsp;&nbsp;&nbsp;<%=messages.getText("generic.activity.form.btwthailand.definitions.6.2") %><br>
	       		&nbsp;&nbsp;&nbsp;&nbsp;<%=messages.getText("generic.activity.form.btwthailand.definitions.6.3") %><br>
	       		&nbsp;&nbsp;&nbsp;&nbsp;<%=messages.getText("generic.activity.form.btwthailand.definitions.6.4") %><br>
	       		&nbsp;&nbsp;<%=messages.getText("generic.activity.form.btwthailand.definitions.7") %><br>
	       		&nbsp;&nbsp;<%=messages.getText("generic.activity.form.btwthailand.definitions.8") %><br>	       		
	       		</font>
			</td>			
		</tr>
		<tr>
    		<td width="100%">&nbsp;</td>
	    </tr>   		
	</table>
	</td></tr>
	<!--  Fin Definiciones -->
	 <!--  Footer: Boton Aceptar -->
	 <tr><td width="700" colspan="1">
	 
	 <table width="700" border="0" cellpadding="0" cellspacing="0" align="center">
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
	   			value='<%=messages.getText("generic.messages.accept") %>'
	   			onClick="javascript:setActionBTWThailand(document.forms['ActivityForm1'] ,document.forms['ActivityRegistryForm']);" class="input"/>   			
       	<%} %>
		</td>
	</tr>
	</table>  
	 
	 </td></tr>
</table>
</form>       

<form name="FormBack" method="post" action='<%=back%>' ></form>

<html:form action = "ActivityRegistrycreate" method="post">
	<html:hidden property="idDriver" value="<%=idDriver %>" />
	<html:hidden property="idActivity" value="<%=idActivity %>" />
	<html:hidden property="checkboxParamList" value="checkboxParamList" />
	<html:hidden property="obs" value="obs" />		
	<html:hidden property="instructor" value="instructor" />	
	<html:hidden property="plate" value="plate" />	
	<html:hidden property="skidcar" value="skidcar" />	
	<html:hidden property="pointCommentList" value="" />
	<html:hidden property="trade" value="" />	
	<html:hidden property="initialKm" value="-1" />
	<html:hidden property="finalKm" value="-1" />
	<html:hidden property="initialHr" />
	<html:hidden property="finalHr" />	
	<html:hidden property="initialMinute" />
	<html:hidden property="finalMinute" />						
	<html:hidden property="vehicleType" value="vehicleType" />									
	<html:hidden property="circulationAreaHighWay" value="false" />										
	<html:hidden property="circulationAreaRoad" value="false" />
	<html:hidden property="circulationAreaSubUrban" value="false" />
	<html:hidden property="circulationAreaUrban" value="false" />	
	<html:hidden property="velocityBox" value="" />											
	<html:hidden property="light" value="" />
	<html:hidden property="commentaryDriveOther" value="" />					
	<html:hidden property="frenaje"/>	
	<html:hidden property="phrd"/>	
	<html:hidden property="drivingExperience" value="drivingExperience" />
</html:form>

<form name="FormBack" method="post" action="<%=back%>" ></form>

</body>

</html:html>
