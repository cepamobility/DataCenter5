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
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.ActivityTypeDataType"
	import="business.activity.ActivityReportFacade"
	import="java.util.*"
	import="java.lang.*"
	import="communication.activity.tables.*"
	import ="org.apache.struts.action.DynaActionForm"	
%>
<!-- ImprimoCol (Activity Schedule) -->
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
//	UserSessionData userSessionData; 
//	userSessionData=sys.getUserSessionData();	
	
	// Obtengo el formulario de esa actividad para dibujarlo en el HTML:
	String idActivity =(String)request.getParameter("idActivity");
	if(idActivity==null){
		idActivity=(String)session.getAttribute("id");			
	}

	ActivityReportFacade activityReport = sys.getActivityReportFacade();
//	CEPAActivityScheduleData  activityData;
//	activityData = activityReport.findCEPAActivityScheduleByPrimaryKey(
//			new Integer(Integer.parseInt(idActivity)),userSessionData);

	CEPAActivityScheduleData activityData = (CEPAActivityScheduleData) session.getAttribute("activityData");
	if (activityData == null) System.out.println("error");
	CheckListData checkList = activityData.getDefinition().getCheckList();
	Collection points = checkList.getPoints();

	
	boolean isBTW3 =	activityData.getDefinition().getResourceKeyName().equals("datatype.scheduledactivitytype.btw3");	
	boolean isMonitor =	((activityData.getDefinition().getResourceKeyName().equals("datatype.scheduledactivitytype.monitors")) ||
			(activityData.getDefinition().getResourceKeyName().equals("datatype.scheduledactivitytype.monitors2")));
	
	// Si hubo problemas con la validación se recargan los datos ya ingresados
	DynaActionForm activityRegistryForm = (DynaActionForm)session.getAttribute("ActivityRegistryForm");
	String stringList = null;
	if (activityRegistryForm != null) {
		stringList = (String) activityRegistryForm.get("checkboxParamList");
	}	
	
	// Si existe, cargo resultado anterior	
	String idDriver=(String)request.getParameter("idDriver");
	if(idDriver==null){
		idDriver=(String)session.getAttribute("idDriver");			
	}		

	Collection itemsResult=null;
	boolean existeResult = false;
	
	CEPAActivityScheduleRegistryData registryData = (CEPAActivityScheduleRegistryData) session.getAttribute("registryData");
	if (registryData != null) {
//	if (activityReport.hasResult(new Integer(idActivity),new Integer(idDriver),sys.getUserSessionData())) {
//		registryData = activityReport.getCompleteResult(
//			new Integer(idActivity),new Integer(idDriver),sys.getUserSessionData());
			existeResult = true;
			itemsResult = registryData.getItemSet();	
	}			
	
	PointData point = null;
	ItemData item = null;
	Text messages=sys.getText();
	boolean encontrePoint = false;
	boolean encontreItem = false;
	String checkedStr = "";
	int primerPoint, ultimoPoint;
	
	primerPoint = Integer.parseInt(request.getParameter("primerPoint"));
//	int pointsByCol=Integer.parseInt(request.getParameter("pointsByCol"));
//	ultimoPoint = primerPoint+pointsByCol-1;
	
	ultimoPoint = Integer.parseInt(request.getParameter("ultimoPoint"));

	String textPoint;
	
	String namecheck = "";
	boolean asistenceRequiredPoint = false;
	for (int i = primerPoint; i <= ultimoPoint; i++){
	// Creo una tabla por cada point y un renglon por cada item de cada point.	
	// Tengo que fijarme si el orden del punto es el correcto:
		Iterator itPoints = points.iterator();
		encontrePoint = false;
		asistenceRequiredPoint = false;
		while (!encontrePoint && itPoints.hasNext())
		{
			point =	(PointData)itPoints.next();
			if (point.getPointOrder().intValue() == i)
				encontrePoint = true;
			    textPoint = point.getText();
			    asistenceRequiredPoint = textPoint.equals("generic.activity.form.btw3.mirrors") 
			    	|| textPoint.equals("generic.activity.form.btw3.reversesmovement")
				    || textPoint.equals("generic.activity.form.btw3.reverseturnrightangle")
	                || textPoint.equals("generic.activity.form.btw3.parking") 
	                || textPoint.equals("generic.activity.form.btw3.frontalsmovement")
                    || textPoint.equals("generic.activity.form.btw3.frontalrightmovement");				
		}
		if (!encontrePoint) break;
		encontreItem = false;
%>
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
        		<%=messages.getText(point.getText())%>
        		</strong></font>
        	</div>
        </td>
        <td width="9" valign="top">
        	<img src="/datacenter/util/imgs/right_form.gif" >        	
        </td>
     </tr>

	
<%
	// Si la actividad es BTW3 y en punto es de los que aparece el label "Recibió asistencia en:"...
	if ((isBTW3) && (asistenceRequiredPoint)) {  
%>     
		   <!--  Recibió asistencia en: -->
	       <tr>
	       	<td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;
	        </td>
	        <td align="left">        
		        <!--  Cambiar esto por un estilo !! -->
	    	    <font size="1" face="Arial, Helvetica, sans-serif"> 
	            <%= messages.getText("generic.activity.form.btw3.asistence")%>:
	            </font> 
	        </td>
	        <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;
	        </td>
	     </tr>
<%   
		   }
%>     

		<!--  Dibujo los items del punto: -->
<%
	for (int j= 1; j < point.getItems().size(); j++)
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
        <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;
        </td>
        <td>        
        
<%
		// Si este item ya fue seleccionado lo marco.
	checkedStr = "";			

	// Si hubo problemas con la validación se recargan los datos ya ingresados
	if (activityRegistryForm !=null) {
		if (stringList.contains(item.getId().toString()))
			{
				checkedStr = "checked"; 			
			}
	}

	if (existeResult) {
		
		if (itemsResult.contains(activityReport.getFormItem(
				point.getText(),item.getText(),sys.getUserSessionData())))
			{
				checkedStr = "checked"; 			
			}
	}	
	
	namecheck="check" + i +j;
	
	String javascriptStr ="";
//	if ((activityData.getDefinition().getResourceKeyName().equals("datatype.scheduledactivitytype.monitors")) ||
//	(activityData.getDefinition().getResourceKeyName().equals("datatype.scheduledactivitytype.monitors2"))) {
		
		
	if (isMonitor) {	
//		javascriptStr ="onclick=\"deschequeo(" + '<%=i" + "%" + ">," + '<%=j" + "%" + ">)\"";
		javascriptStr ="onclick=\"deschequeo(" + i + "," + j + ")\"";
	}	
		
				
%>        
        	<input type="checkbox" name='<%=namecheck %>' 
              value='<%=item.getId().toString() %>' class="check" <%=checkedStr%>  <%=javascriptStr %>> 
            <!--  Cambiar esto por un estilo !! -->
            <font size="1" face="Arial, Helvetica, sans-serif"> 
            <%= messages.getText(item.getText())%>
            </font> 
        </td>
        <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;
        </td>
     </tr>
<%
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
</table>




