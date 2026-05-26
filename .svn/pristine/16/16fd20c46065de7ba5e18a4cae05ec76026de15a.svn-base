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
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
//	UserSessionData userSessionData; 
//	userSessionData=sys.getUserSessionData();	
	

	CheckListData checkList = (CheckListData) session.getAttribute("checkList");
	Collection points = checkList.getPoints();

	
	VehicleCheckData vehicleCheck = (VehicleCheckData) session.getAttribute("vehicleCheck");
	
	Collection itemsResult=vehicleCheck.getItemValueSet();
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
		while (!encontrePoint && itPoints.hasNext())	{
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
        <td colspan="2" background="/datacenter/util/imgs/center_form.gif" valign="top"> 
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
	System.out.println("Cant:" + point.getItems().size());

	for (int j= 1; j <= point.getItems().size(); j++) {
		Iterator itItems = point.getItems().iterator();
		encontreItem = false;
		// Tengo que fijarme si el orden del item es el correcto:
		while (!encontreItem && itItems.hasNext()) {
			item = (ItemData)itItems.next();
			if (item.getItemOrder().intValue() == j)
				encontreItem = true;
		}
		if (!encontreItem) break;
			String nameRadio="radio" + j;
			
//		int idItem = checkList.getFormItem(point.getText(),item.getText(),sys.getUserSessionData());	
			
			String text="";	
			if ( vehicleCheck.getValueFromItem(item) != null) {
				if (vehicleCheck.getValueFromItem(item).contains("1")) {
					text += messages.getText("generic.vehiclecheck.form.correct");
				}
				if (vehicleCheck.getValueFromItem(item).contains("2")) {
					if (text.equals("")) {
						text += messages.getText("generic.vehiclecheck.form.withoutclean");
					}
					else {
						text += "; " + messages.getText("generic.vehiclecheck.form.withoutclean");
					}
				}
				if (vehicleCheck.getValueFromItem(item).contains("3")) {
					if (text.equals("")) {
						text += messages.getText("generic.vehiclecheck.form.conmarcas");
					}
					else {
						text += "; " + messages.getText("generic.vehiclecheck.form.conmarcas");
					}	
				}			
				if (vehicleCheck.getValueFromItem(item).contains("4")) {
					if (text.equals("")) {
						text += messages.getText("generic.vehiclecheck.form.noposee");
					}
					else {
						text += "; " + messages.getText("generic.vehiclecheck.form.noposee");
					}	
				}							
			}	
			if (text.equals("")) {
				text = messages.getText("generic.vehiclecheck.form.nodata");
			}
		
%>
	 <!--  Dibujo Item: -->
     <tr> 
        <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;
        </td>
 		<td>       
        <strong><font size="1" face="Arial, Helvetica, sans-serif">                         		 		
        <%= messages.getText(item.getText())%>
        </font></strong>
   		</td>       
        <td align="right">
        <font size="1" face="Arial, Helvetica, sans-serif"> 
		<%=text %>
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
        <td colspan="2" background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
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




