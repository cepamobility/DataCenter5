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
<%
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
	
	// Me fijo si ya tiene un registro de esta actividad ese conductor:
	String idDriver=(String)request.getParameter("idDriver");
	if (Util.empty(idDriver)){
		idDriver = (String)session.getAttribute("idDriver");
		System.out.println("CommentaryDriveOtherForm.jsp - idDriver"+idDriver);
	}
	session.setAttribute("idDriver",idDriver);			
	CEPAActivityScheduleRegistryData registryData = activityReport.getCompleteResult(
			new Integer(idActivity),new Integer(idDriver),sys.getUserSessionData());		
	// Pido Instructor o Compañero y tipo
	totalPoints = points.size();
	String noeditable = "";
	boolean enabled = Boolean.parseBoolean((String)request.getParameter("enabled"));
	if (!enabled) 
	{
		noeditable = " READONLY ";		
	}
%>

	<!-- CommentaryDriveOtherForm.jsp -->
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
	    <tr align="left">
	    	<!--  Empleado Evaluado -->
	    	<th class="linetwo">
				<%=messages.getText("generic.activity.form.commentarydriveother.nameevaluatedemployee")%>
			</th>
			<td class="linetwo">
				<%=registryData.getDriver().getFirstName() + " " + registryData.getDriver().getLastName() %>									
				&nbsp;
				<a href='<%="./layout.jsp?refer=driver/DriverView.jsp?id="+registryData.getDriver().getId()%>'>
					<input type='button' value='<%=messages.getText("generic.messages.history")%>' onclick="javascript:document.forms['history'].submit();" />
				</a>
			</td>
	    </tr>
	    <tr height="10"><td></td></tr>  
		<tr align="left" class="linetwo">
			<!-- Evaluador: -->				
			<th>
				<%=messages.getText("generic.activity.form.commentarydriveother.evaluatorname")+":"%>
			</th>
			<td>
<% 			// Si ya se escribio un compañero, lo muestro:
			if ((registryData.getPartner() != null)) { %>
				<input type="text" name="partner" class="input" width="100" value='<%=registryData.getPartner()%>' <%=noeditable%> />
<%			}else{ %>
				<input type=text name="partner" class="input" width="100" <%=noeditable%> />				
<%			}%>
				<!--  Variables Ocultas: -->
				<input type="hidden" name="cepaCourse" value="false" />	
				<input type="hidden" name="commentaryDrive" value="true" />	
				<input type="hidden" name="commentaryDriveOther" value="true" />				
				<input type="hidden" name="conductionType" value="" />
				<input type="hidden" name="note" value="" />				
				<input type="hidden" name="instructor" value="" />	
			</td>
		</tr>
	    <tr height="10"><td></td></tr>  
		<tr align="left" class="linetwo">
			<!-- Matricula: -->
			<td>
				<%=messages.getText("generic.activity.form.commentarydrive.vehicleplate")+":"%>
			</td>
			<td>
				
<% 			// Si ya se escribio un matricula, la muestro:
			if ((registryData.getPlate() != null)) { %>
				<input type=text name="plate" class="input" width="100"  value='<%=registryData.getPlate()%>' <%=noeditable%> />
<%			}else { %>
				<input type=text name="plate" class="input" width="100" <%=noeditable%> />
<%			}%>		
				<input type="hidden" name="pointCount" value='<%=totalPoints%>' <%=noeditable%> />
			</td>
		</tr>
		<tr height="10"><td></td></tr>  
	</table>		
<%
	primerPoint = 1;
	ultimoPoint = totalPoints;
	//System.out.println("Total Points: " + totalPoints);
	for (int i = primerPoint; i <= ultimoPoint; i++)
	{
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
		<tr class="guide1">
			<td width="19" height="30" ><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
	<!--  Titulo del Punto: -->
	     	<input type="hidden" name="pointsCount" value='<%=totalPoints%>' />
		  	<td height="30" class="guide1"><div align="left"><%=messages.getText(point.getText())%></div></td>
			<td width="19" height="30" ><img src="/datacenter/util/imgs/right.jpg" width="19" height="30"></td>
		</tr>
		<tr>
			<td class="leftLine">&nbsp;</td>
			<td height="6"><img src="x" width="1" height="1"></td>
			<td class="rightLine">&nbsp;</td>
		</tr>  					  					
		<tr>
    		<td class="leftLine">&nbsp;</td>
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
    		<td class="rightLine">&nbsp;</td>
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
    		<td class="leftLine">&nbsp;</td>
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
		switch (value.intValue()) {
			case 1: %>
	        	<td width="10%"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()%>-1' class="check" checked/></td>
	    	    <td width="20%"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()%>-2' class="check" <%=noeditable%> /></td>
	        	<td width="20%"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()%>-3' class="check" <%=noeditable%> /></td>
<%					break;
			case 2: %>
		        <td width="10%"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()%>-1' class="check" <%=noeditable%> /></td>
		        <td width="20%"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()%>-2' class="check" checked/></td>
		        <td width="20%"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()%>-3' class="check" <%=noeditable%> /></td>				
<%					break;
			case 3:%>
	        	<td width="10%"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()%>-1' class="check" <%=noeditable%> /></td>
	        	<td width="20%"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()%>-2' class="check" <%=noeditable%> /></td>
	        	<td width="20%"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()%>-3' class="check"  checked/></td>				
<%					break;
			case -1:%>
	        	<td width="10%"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()%>-1' class="check" <%=noeditable%> /></td>
    	    	<td width="20%"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()%>-2' class="check" <%=noeditable%> /></td>
        		<td width="20%"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()%>-3' class="check" <%=noeditable%> /></td>				
<%					break;
			default: %>
		        <td width="10%"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()%>-1' class="check"  checked/></td>
		        <td width="20%"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()%>-2' class="check" <%=noeditable%> /></td>
		        <td width="20%"><input type="radio" name='<%=item.getId().toString()%>' value='<%=item.getId().toString()%>-3' class="check" <%=noeditable%> /></td>													
<%		}
%>
					</tr>
				</table>
			</td>
       
        <!--  Linea Derecha -->
    		<td class="rightLine">&nbsp;</td>
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



