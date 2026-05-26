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
		<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText(point.getText())%>' />
		<table width="100%" border="0" align='center' cellpadding="0" cellspacing="0">

	
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
		if(sys.getUserSessionData().getValue() instanceof VehicleCheckData) {
			VehicleCheckData check = (VehicleCheckData) sys.getUserSessionData().getValue();
			String s = check.getValueFromItem(item);
			if(s != null)
				value = Integer.parseInt(s);
		}
%>
	 <!--  Dibujo Item: -->
     		<tr  class="linetwo"> 
 				<th width="20%" align="left">       
		        	<%= messages.getText(item.getText())%>
   				</th>       
   		 		<td width="20%" align="left">       
<%  	if (validValues[i-1][0] == 1) {
			nameRadio += "-1";
%>   				<INPUT type="checkbox" name='<%=nameRadio %>' value='<%= item.getId().toString() + "-1"%>' <%=(value == 1) ? "checked" : ""%>>	    				
					<%= messages.getText("generic.vehiclecheck.form.correct")%>
<%		}
%>	       		</td>   
         		<td width="20%" align="left">       
<%  	if (validValues[i-1][1] == 1) {
			nameRadio += "-2";
%>      	  		<INPUT type="checkbox" name='<%=nameRadio %>' value='<%= item.getId().toString() + "-2"%>'  <%=(value == 2) ? "checked" : ""%>>	    				
					<%= messages.getText("generic.vehiclecheck.form.withoutclean")%>		
<%		}
%>  			</td>   
      	 		<td width="20%" align="left">       
<%      if (validValues[i-1][2] == 1) {
			nameRadio += "-3";
%>      			<INPUT type="checkbox" name='<%=nameRadio %>' value='<%= item.getId().toString() + "-3"%>' <%=(value == 3) ? "checked" : ""%>>	    				
					<%= messages.getText("generic.vehiclecheck.form.conmarcas")%>				
<%		}
%>		        </td>
    	 		<td width="20%" align="left">       
<%      if (validValues[i-1][3] == 1) {
			nameRadio += "-4";
%>      			<INPUT type="checkbox" name='<%=nameRadio %>' value='<%= item.getId().toString() + "-4"%>' <%=(value == 4) ? "checked" : ""%>>	    				
					<%= messages.getText("generic.vehiclecheck.form.noposee")%>				
<%		}
%>		        </td>                                    
		     </tr>
<%
		} // fin for Items
%>
		</table>
		<jsp:include  page='../component/utilityboxbottom.jsp' />
		 <br>

<%	} // fin for Points	
%>




