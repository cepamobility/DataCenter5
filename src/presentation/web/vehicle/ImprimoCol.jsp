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
	import="communication.util.*"
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
<!-- ImprimoCol (Vehicle) -->
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
//	UserSessionData userSessionData; 
//	userSessionData=sys.getUserSessionData();	
	

	CheckListData checkList = (CheckListData) session.getAttribute("checkList");
	Collection points = checkList.getPoints();
	
	int[][] validValues = { {1,1,1,1},
			{1,1,1,1},
			{1,1,1,1},
			{1,1,1,1},
			{1,1,1,1},
			{1,1,1,1},
			{1,1,1,1},
			{1,1,1,1},
			{1,1,1,1},
			{1,1,1,1},
			{1,0,0,1},
			{1,1,1,1},
			{1,1,1,1},
			{1,1,1,1},
			{1,1,0,0},
			{1,0,0,1},
			{1,0,0,1},
			{1,0,0,1},
			{1,0,0,1},
			{1,0,0,1},
			{1,0,0,1},
			{1,0,0,1},
			{1,0,0,1},
			{1,0,0,1},
			{1,0,0,1},
			{1,0,1,1},
			{1,1,0,0},
			{1,1,1,1}
	};

	// Si hubo problemas con la validación se recargan los datos ya ingresados
	DynaActionForm vehicleCheckForm = (DynaActionForm)session.getAttribute("VehicleCheckForm");
	String stringList = null;
	if (vehicleCheckForm != null) {
		stringList = (String) vehicleCheckForm.get("checkboxParamList");
	}	
	
	Collection itemsResult=null;
	boolean existeResult = false;
	
	PointData point = null;
	ItemData item = null;
	Text messages=sys.getText();
	boolean encontrePoint = false;
	boolean encontreItem = false;
	String checkedStr = "";
	int primerPoint, ultimoPoint;
	
	primerPoint=Integer.parseInt(request.getParameter("primerPoint"));

	ultimoPoint = Integer.parseInt(request.getParameter("ultimoPoint"));

	String textPoint;
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
			    textPoint = point.getText();
		}
		if (!encontrePoint) break;
		encontreItem = false;
		
		// Si es portugues , no se muestra el punto: generic.activity.form.vheiclecheck.documentos
		//if (!(sys.getUserSessionData().getLenguage().equalsIgnoreCase("pt") &&
		//		point.getText().equals("generic.activity.form.vheiclecheck.documentos"))){
			
		
%>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<!--  Titulo del Punto: -->
	<tr> 
    	<td width="9" valign="top">
    		<img src="/datacenter/util/imgs/left_form.gif" >
    	</td>    	
        <td colspan="5" background="/datacenter/util/imgs/center_form.gif" valign="top"> 
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

	
		<!--  Dibujo los items del punto: -->
<%
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
		String nameRadio="check" + i + j;
		int value = 0;
		String s = "";
		if(sys.getUserSessionData().getValue() instanceof VehicleCheckData) {
			VehicleCheckData check = (VehicleCheckData) sys.getUserSessionData().getValue();
			s = check.getValueFromItem(item);
			if(s != null)
				value = Integer.parseInt(s);
		}
%>
	 <!--  Dibujo Item: -->
     <tr> 
        <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;
        </td>
 		<td width="20%">       
        <font size="1" face="Arial, Helvetica, sans-serif"> 
        <%= messages.getText(item.getText())%>
        </font>
   		</td>       
   		
 		<td width="20%">       
<%      
		if (validValues[i-1][0] == 1) {
			nameRadio += "-1";
%>   	        
        <INPUT type="checkbox" name='<%=nameRadio %>' value='<%= item.getId().toString() + "-1"%>' <%=(value == 1 || (s != null && s.contains("1"))) ? "checked" : ""%>>	    				
        <font size="1" face="Arial, Helvetica, sans-serif"> 
		<%= messages.getText("generic.vehiclecheck.form.correct")%>
<%
		}
		else {
%>  		
        <font size="1" face="Arial, Helvetica, sans-serif"> 
		<%= ""%>				
<%		
		}
%>	
        </font> 
        </td>   
        
 		<td width="20%">       
<%      
		if (validValues[i-1][1] == 1) {
			nameRadio += "-2";
%>        
        <INPUT type="checkbox" name='<%=nameRadio %>' value='<%= item.getId().toString() + "-2"%>'  <%=(value == 2 || (s != null && s.contains("2"))) ? "checked" : ""%>>	    				
        <font size="1" face="Arial, Helvetica, sans-serif"> 
		<%= messages.getText("generic.vehiclecheck.form.withoutclean")%>		
<%
		}
		else {
%>  		
        <font size="1" face="Arial, Helvetica, sans-serif"> 
		<%= ""%>				
<%		
		}
%>			
        </font> 
        </td>   
      
 		<td width="20%">       
<%      
		if (validValues[i-1][2] == 1) {
			nameRadio += "-3";
%>        
        <INPUT type="checkbox" name='<%=nameRadio %>' value='<%= item.getId().toString() + "-3"%>' <%=(value == 3 || (s != null && s.contains("3"))) ? "checked" : ""%>>	    				
        <font size="1" face="Arial, Helvetica, sans-serif"> 
		<%= messages.getText("generic.vehiclecheck.form.conmarcas")%>				
<%
		}
		else {
%>  		
        <font size="1" face="Arial, Helvetica, sans-serif"> 
		<%= ""%>				
<%		
		}
%>		
        </font> 
        </td>
    
 		<td width="20%">       
<%      
		if (validValues[i-1][3] == 1) {
			nameRadio += "-4";
%>              
        <INPUT type="checkbox" name='<%=nameRadio %>' value='<%= item.getId().toString() + "-4"%>' <%=(value == 4 || (s != null && s.contains("4"))) ? "checked" : ""%>>	    				
        <font size="1" face="Arial, Helvetica, sans-serif"> 
		<%= messages.getText("generic.vehiclecheck.form.noposee")%>				
<%
		}
		else {
%>  		
        <font size="1" face="Arial, Helvetica, sans-serif"> 
		<%= ""%>				
<%		
		}
%>		
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
        <td colspan="5" background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
        <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
     </tr>

<%
		//} // fin if portugues y point documento
	} // fin for Points
	
%>
</table>




