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
<!-- ImprimoColNewForms - Formularios de Tablets -->
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData; 
	userSessionData=sys.getUserSessionData();	
	
	// Obtengo el formulario de esa actividad para dibujarlo en el HTML:
	String idActivity =(String)request.getParameter("idActivity");
	if(idActivity==null){
		idActivity=(String)session.getAttribute("id");			
	}

	ActivityReportFacade activityReport = sys.getActivityReportFacade();

	CEPAActivityScheduleData activityData = (CEPAActivityScheduleData) session.getAttribute("activityData");
	if (activityData == null) System.out.println("error");
	CheckListData checkList = activityData.getDefinition().getCheckList();
	Collection points = checkList.getPoints();
	int pointsCount = (points != null)?points.size():0;
		
	// Si hubo problemas con la validación se recargan los datos ya ingresados
	DynaActionForm activityRegistryForm = (DynaActionForm)session.getAttribute("ActivityRegistryForm");
	String stringList = null;
	String pointObsList = null;
	if (activityRegistryForm != null) {
		stringList = (String) activityRegistryForm.get("checkboxParamList");
		pointObsList = (String) activityRegistryForm.get("pointCommentList");
	}	
	
	// Si existe, cargo resultado anterior	
	String idDriver=(String)request.getParameter("idDriver");
	if(idDriver==null){
		idDriver=(String)session.getAttribute("idDriver");			
	}		

	Collection itemsResult = null;
	Collection observationsResult = null;
	Collection idsObservationsResults = null;
	boolean existeResult = false;
	
	CEPAActivityScheduleRegistryData registryData = 
		(CEPAActivityScheduleRegistryData) session.getAttribute("registryData");
	
	if (registryData != null) {
			System.out.println("Existen datos para este driver/actividad...");
			existeResult = true;
			itemsResult = registryData.getItemSet();	
			System.out.println("Items marcados: " + ((itemsResult !=null)?itemsResult.size():0));
			observationsResult = registryData.getPointObservations(); // Colection<PointObservationSelectedData>
			
			if (observationsResult != null && observationsResult.size() > 0){
				idsObservationsResults = new LinkedList();
				Iterator itObs = observationsResult.iterator();
				while (itObs.hasNext()){
					PointObservationSelectedData o = (PointObservationSelectedData) itObs.next();
					if (o != null && o.getObservation() != null && o.getObservation().getIdObservation() != null){
						idsObservationsResults.add(o.getObservation().getIdObservation());
					}					
				}
			}
			
			System.out.println("Observaciones marcadas: " + ( (observationsResult != null)?observationsResult.size():"0"));
	}else{
		System.out.println("NO Existen datos para este driver/actividad... !! ");
	}
	
	PointData point = null;
	ItemData item = null;
	Text messages=sys.getText();
	boolean encontrePoint = false;
	boolean encontreItem = false;
	boolean encontreObs = false;
	String checkedStr = "";
	int primerPoint, ultimoPoint;
	
	primerPoint = Integer.parseInt(request.getParameter("primerPoint"));
//	int pointsByCol=Integer.parseInt(request.getParameter("pointsByCol"));
//	ultimoPoint = primerPoint+pointsByCol-1;
	
	ultimoPoint = Integer.parseInt(request.getParameter("ultimoPoint"));

	String textPoint;	
	String namecheck = "";
	String nameradio = "";
	
	// Javascript para cargar los items que esten deshabilitados si hay un radio butom
	String javascriptLoad = "";
	
		
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

		<!--  Dibujo los items del punto: -->
<%
	for (int j = 1; j <= point.getItems().size(); j++)
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
		if (itemsResult.contains(activityReport.getItemById
				(item.getId(),sys.getUserSessionData()))){
			checkedStr = "checked";
			System.out.println("Item marcado: "+ item.getId() + " - "  + item.getText());
		}else{
			checkedStr = "";
		}
		
	}	
	
	namecheck="check" + i + j;
	nameradio = "radio" + item.getIdRadio();
	
	String javascriptStr ="";		
	String javascriptStrRadio ="";
	if (item.getDisabledItems() != null && item.getDisabledItems().trim() != ""){
		javascriptStrRadio = "onclick=\"deshabilitar('" + item.getDisabledItems() +"',document.forms['ActivityForm1']);\""; // TODO: deshabilitar items que correspondan y desmarcar dichos items deshailitados.
	}
	
	if (item.getDisabledItems() != null && item.getDisabledItems().trim() != "" &&
			itemsResult.contains(activityReport.getItemById(item.getId(),sys.getUserSessionData()))){
		// Si deshabilita otros items y esta checkeada esta opcion:
		javascriptLoad = javascriptLoad + "deshabilitar('" + item.getDisabledItems() +"',document.forms['ActivityForm1']);";
	}
	
				
%>        
        
        <%
        	if (item.getType().equals(ItemData.CHECK)) { 
        %>
        	<input type="checkbox" name='<%=namecheck %>'  id="<%=item.getId().toString()%>";
              value='<%=item.getId().toString() %>' class="check" <%=checkedStr%> 
              <%=javascriptStr %>> 
        <%}else if (item.getType().equals(ItemData.RADIO)){ %>
            <input type="radio" name='<%=nameradio %>' 
              value='<%=item.getId().toString() %>' class="check"  <%=checkedStr%> 
              <%=javascriptStrRadio %>   > 
         <%}else if (item.getType().equals(ItemData.TEXTO)){ 
         		// Si es texto no ponemos nada delante, esto se usa por ejemplo par:
         		// "Se le recomendo: "....
         %>
         <%} %>
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
		
		// Observaciones del punto:
		PointObservationData obs = null;
		if ( point.getOrderedPointObservationsData() != null && 
				point.getOrderedPointObservationsData().size() > 0){
			
%>
  <tr> 
        <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;
        </td>
        <td>
        	<font size="1" face="Arial, Helvetica, sans-serif">
        		<strong>
        			<%=messages.getText("generic.messages.observation") %>:
        		</strong>
        	</font> 
        </td>
        <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;
        </td>
     </tr>  
        
<%

		
			for (int j = 1; j <= point.getOrderedPointObservationsData().size(); j++)
			{
				Iterator itObs = point.getObservationsData().iterator();
				encontreObs = false;
				// Tengo que fijarme si el orden del item es el correcto:
				while (!encontreObs && itObs.hasNext())
				{	
					obs = (PointObservationData) itObs.next();
					if (obs.getObsOrder() != null && obs.getObsOrder().intValue() == j)
						encontreObs = true;
				}
				if (!encontreObs) break;
%>
				<!--  Dibujo Observation Point: -->
     <tr> 
        <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;
        </td>
        <td>      
      
        
<%
		// Si este item ya fue seleccionado lo marco.
	checkedStr = "";			

	// Si hubo problemas con la validación se recargan los datos ya ingresados
	if (activityRegistryForm !=null) {
		if (pointObsList.contains(point.getId() + "-" + obs.getIdObservation().toString())){
				checkedStr = "checked"; 			
			}
	}

	if (existeResult) {		
		if (idsObservationsResults != null && idsObservationsResults.contains(obs.getIdObservation()) ){			
			checkedStr = "checked";
			System.out.println("Observacion marcada: "+ obs.getIdObservation() + " - "  + obs.getText());
		}else{
			checkedStr = "";
			System.out.println("Observacion NO marcada: "+ obs.getIdObservation() + " - "  + obs.getText());
		}
		
	}	
	
	namecheck="obscheck" + j;	
	
	String javascriptStr ="";		
	//String javascriptStrRadio ="";
	/*
	if (item.getDisabledItems() != null && item.getDisabledItems().trim() != ""){
		javascriptStrRadio = "onclick=\"deshabilitar('" + item.getDisabledItems() +"',document.forms['ActivityForm1']);\""; // TODO: deshabilitar items que correspondan y desmarcar dichos items deshailitados.
	}
	*/		
	
	// NOTA: Valor del check de observaciones idPoint-idObservacion
%>        
        
       	<input type="checkbox" name='<%=namecheck %>'  id="<%=obs.getIdObservation().toString()%>";
              value='<%= point.getId() + "-" + obs.getIdObservation().toString() %>' class="check" <%=checkedStr%> 
              <%=javascriptStr %>       > 
            <!--  Cambiar esto por un estilo !! -->
            <font size="1" face="Arial, Helvetica, sans-serif"> 
            <%= messages.getText(obs.getText())%>
            </font> 
        </td>
        <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;
        </td>
     </tr>
<%
		} // fin for Observations Points
	} // fin if observations != null

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
<script>
<%= javascriptLoad %>
</script>



