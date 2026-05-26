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
	import="java.util.ArrayList"
	import="java.util.Set"
	import="java.util.Collection"
	import="java.util.Iterator"
	import="java.lang.*"
	import="communication.activity.tables.*"
%>

<html:html>
<%
	System.out.println("PRINT EMPIEZA");
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData; 
	userSessionData=sys.getUserSessionData();	
	// Obtengo el formulario de esa actividad para dibujarlo en el HTML:
	String idActivity =(String)request.getParameter("idActivity");
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
	int primerPoint, ultimoPoint, totalPoints;
	boolean print = Boolean.parseBoolean((String)request.getParameter("print"));
	
	// Pido Instructor o Compañero y tipo
	totalPoints = points.size();
	System.out.println("PRINT dibujo");
	// Muestro nombre compañero
%>

<BODY onload="window.print()">
  <form name="ActivityForm1" method="post" action="">
 <!--  Titulo -->
  <table width="600" border="0" cellpadding="0" cellspacing="0">
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

	<!-- Commentary Drive: -->
	<table width="600" border="0" cellpadding="0" cellspacing="0">
		<!--  dejo una linea en blanco entre el titulo y los textos -->
	    <tr>
    		<td>&nbsp;</td>
	    </tr>  
	    <tr align="legt">
	    	<!--  Empleado Evaluado -->
	    	<td class="input2">
				<font size="1" face="Arial, Helvetica, sans-serif"> 
				<%=messages.getText("generic.activity.form.commentarydriveother.nameevaluatedemployee")%>:
				</font>
			</td>
			<td>&nbsp;</td>
	    </tr>
		<tr align="left">
			<!-- Evaluador: -->				
			<td class="input2">
				<font size="1" face="Arial, Helvetica, sans-serif"> 
				<%=messages.getText("generic.activity.form.commentarydriveother.evaluatorname")+":"%>
				</font>
			</td>
			<td>
				<font size="1" face="Arial, Helvetica, sans-serif"> 
					<input type=text name="partner" class="input">				
				</font>
				<!--  Variables Ocultas: -->
				<input type="hidden" name="cepaCourse" value="false" />	
				<input type="hidden" name="commentaryDrive" value="true" />			
				<input type="hidden" name="note" value="" />				
				<input type="hidden" name="instructor" value="" />	
			</td>
		</tr>
		<tr align="left">
			<!-- Matricula: -->
			<td class="input2">
				<font size="1" face="Arial, Helvetica, sans-serif"> 
				<%=messages.getText("generic.activity.form.commentarydrive.vehicleplate")+":"%>
				</font>
			</td>
			<td>
				<font size="1" face="Arial, Helvetica, sans-serif"> 
					<input type=text name="plate" class="input" />
	
				</font>
				<input type="hidden" name="pointCount" value='<%=totalPoints%>' />
			</td>
		</tr>
		<tr>
    		<td>&nbsp;</td>
	    </tr>
	</table>		
<%
	primerPoint = 1;
	ultimoPoint = totalPoints;
	//System.out.println("Total Points: " + totalPoints);
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
<table width="600" border="0" cellpadding="0" cellspacing="0">
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
     	 <input type="hidden" name="pointsCount" value='<%=totalPoints%>' />
        </td>
        <td width="9" valign="top">
        	<img src="/datacenter/util/imgs/right_form.gif" >        	
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
					<td width="10%" >
						<font size="1" face="Arial, Helvetica, sans-serif">
        				<%=messages.getText("generic.activity.form.commentarydriveother.optionyes")%>
        				</font>
        			</td>
					<td width="20%" >
						<font  size="1" face="Arial, Helvetica, sans-serif">
						<%=messages.getText("generic.activity.form.commentarydriveother.optionno")%>
						&nbsp;&nbsp;&nbsp;
						<%=messages.getText("generic.activity.form.commentarydriveother.optionnofix")%>
						</font>
					</td>						
					<td width="20%" >
						<font size="1" face="Arial, Helvetica, sans-serif">
						<%=messages.getText("generic.activity.form.commentarydriveother.requirespursuit")%>
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
       	<td width="10%"><input type="radio" name='<%=item.getId().toString()%>' value="1" class="check" /></td>
    	<td width="20%"><input type="radio" name='<%=item.getId().toString()%>' value="2" class="check" /></td>
   		<td width="20%"><input type="radio" name='<%=item.getId().toString()%>' value="3" class="check" /></td>				
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
    <!-- Observaciones: -->
   	<tr> 
    	<td width="9">
    		<img src="/datacenter/util/imgs/left_form.gif" >
    	</td>    	
        <td background="/datacenter/util/imgs/center_form.gif" valign="top"> 
        	<div align="center">
        	<!--  Cambiar esto por un estilo !! -->
        		<font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"><strong>
        		<%=messages.getText("generic.activity.observations")%>
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
		<textarea name="obs" style="width:100%" rows="5"></textarea>
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
</form>
</BODY>

</html:html>


