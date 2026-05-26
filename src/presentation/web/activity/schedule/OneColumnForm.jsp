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
	String checkedStr = "";
	String peer = "";
	String superior = "";
	int primerPoint, ultimoPoint, totalPoints;
	boolean isCommentaryDrive = false;
	boolean isCommentaryDriveOther = false;
	
	// Me fijo si ya tiene un registro de esta actividad ese conductor:
	String idDriver=(String)request.getParameter("idDriver");
	session.setAttribute("idDriver",idDriver);			
	CEPAActivityScheduleRegistryData registryData = activityReport.getCompleteResult(
			new Integer(idActivity),new Integer(idDriver),sys.getUserSessionData());		
	Collection itemsResult = registryData.getItemSet();	
	Collection pointComment = registryData.getPointCommentSet();
	// Pido Instructor o Compañero y tipo
	isCommentaryDrive =	activityData.getDefinition().getResourceKeyName().
		equals("datatype.scheduledactivitytype.commentarydrive");
	isCommentaryDriveOther = activityData.getDefinition().getResourceKeyName().
	equals("datatype.scheduledactivitytype.commentarydriveother");
	totalPoints = points.size();
	if (isCommentaryDrive || isCommentaryDriveOther)
	{
		// Si es commentary Drive muestro nombre compañero y tipo:
		// Generalizar esto para todas las actividades de una compañia
		// poniendo en la activityData un indicador tipo cepaCourse
%>

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
			<!-- Tipo de Conduccion Comentada:   -->
			<td class="input2">
				<font size="1" face="Arial, Helvetica, sans-serif"> 
				<%=messages.getText("generic.activity.form.commentarydrive.commentarydrivetype")%>
				</font>
			</td>
			<td>
<% 
			// Si fue seleccionado un tipo de conduccion 
			// en una instancia anterior la muestro:
			if ((registryData.getConductionType()!= null))
			{
				if (registryData.getConductionType().equals(
						"generic.activity.form.commentarydrive.peerdrivewithpeer"))
				{
					peer = "selected";
				  	superior = "";
				}
				else
				{
					peer = "";
					superior = "selected";
				}
			} 
%>
				<font size="1" face="Arial, Helvetica, sans-serif"> 
				<select name="conductionType">
					<option <%=peer %>  value="generic.activity.form.commentarydrive.peerdrivewithpeer"  >
						<%=messages.getText("generic.activity.form.commentarydrive.peerdrivewithpeer")%>
					</option>
					<option <%=superior %> value="generic.activity.form.commentarydrive.commentarydrivewithsuperior" >
						<%=messages.getText("generic.activity.form.commentarydrive.commentarydrivewithsuperior")%>
					</option>
				</select>
				</font>
				<!--  Variables Ocultas: -->
				<input type="hidden" name="cepaCourse" value="false" />	
				<%if (isCommentaryDrive){ %>
				<input type="hidden" name="commentaryDrive" value="true" />	
				<input type="hidden" name="commentaryDriveOther" value="false" />		
				<%} %>
				<%if (isCommentaryDriveOther){ %>
				<input type="hidden" name="commentaryDrive" value="false" />	
				<input type="hidden" name="commentaryDriveOther" value="true" />		
				<%} %>
				<input type="hidden" name="note" value="" />				
				<input type="hidden" name="instructor" value="" />				
			</td>
		</tr>	
		<tr align="left">
			<!-- Compañero o supervisor: -->
		
			<td class="input2">
				<font size="1" face="Arial, Helvetica, sans-serif"> 
				<%=messages.getText("generic.activity.form.commentarydrive.peer")%>
				</font>
			</td>
			<td>
				<font size="1" face="Arial, Helvetica, sans-serif"> 
<% 			// Si ya se escribio un copañero, lo muestro:
			if ((registryData.getPartner() != null)) { %>
				<input type="text" name="partner" class="input" value='<%=registryData.getPartner()%>' />
<%			}else{ %>
				<input type=text name="partner" class="input">				
<%			}%>
				</font>
			</td>
		</tr>
		<tr align="left">
			<!-- Matricula: -->
			<td class="input2">
				<font size="1" face="Arial, Helvetica, sans-serif"> 
				<%=messages.getText("generic.activity.form.commentarydrive.vehicleplate")%>
				</font>
			</td>
			<td>
				<font size="1" face="Arial, Helvetica, sans-serif"> 
				
<% 			// Si ya se escribio un matricula, la muestro:
			if ((registryData.getPlate() != null)) { %>
				<input type=text name="plate" class="input" value='<%=registryData.getPlate()%>' />
<%			}else { %>
				<input type=text name="plate" class="input" />
<%			}%>		
				</font>
				<input type="hidden" name="pointCount" value='<%=totalPoints%>' />
			</td>
		</tr>
		<tr>
    		<td>&nbsp;</td>
	    </tr>
	</table>	

<% 
	}
	else
	{
		// Si es un curso de cepa, muestro instructor:
%>
	<table width="600" border="0" cellpadding="0" cellspacing="0">
		<!--  dejo una linea en blanco entre el titulo y los textos -->
	    <tr>
    		<td>&nbsp;</td>
	    </tr>  
		<tr align="left">
		<!-- Instructor: -->	
			<td class="input2">
				<font size="1" face="Arial, Helvetica, sans-serif"> 
				<%=messages.getText("role.instructor.name")%>
				</font>
			</td>
			<td>
				<font size="1" face="Arial, Helvetica, sans-serif"> 				
		<% // Si ya se escribio un copañero, lo muestro:
			if ((registryData.getInstructor() != null))	{ %>
				<input type=text name="instructor" class="input" value='<%=registryData.getInstructor()%>' />
<%			}else{ %>
				<input type=text name="instructor" class="input" />
<%			}%>		
			</font>
				<!--  Variables Ocultas: -->
				<input type="hidden" name="cepaCourse" value="true" />	
				<input type="hidden" name="commentaryDrive" value="false" />		
				<input type="hidden" name="note" value="" />				
				<input type="hidden" name="partner" value="" />	
				<input type="hidden" name="conductionType" value="" />
				<input type="hidden" name="plate" value="" />
				
				<input type="hidden" name="pointCount" value='<%=totalPoints%>' />				
			</td>
		</tr>
		<tr>
    		<td>&nbsp;</td>
	    </tr>
	</table>
<%	
	}
	
%>
	
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
        </td>
        <td width="9" valign="top">
        	<img src="/datacenter/util/imgs/right_form.gif" >
        	<input type="hidden" name="pointsCount" value='<%=totalPoints%>' />        	
        </td>
     </tr>
     
	
<% 		// Si es comentary Drive pongo un titulo de: Necesita Mejorar en los items.
		if (isCommentaryDrive) 
     	{
%>    	
	   <!--  Necesita Mejorar? -->
       <tr>
       	<td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;
        </td>
        <td align="left">        
	        <!--  Cambiar esto por un estilo !! -->
    	    <font size="1" face="Arial, Helvetica, sans-serif"> 
            <%= messages.getText("generic.activity.form.commentarydrive.needstoimprove")%>?
            </font> 
        </td>
        <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;
        </td>
     </tr>
<%		
     	}
%>     
		<!-- Dibujo los items del punto: -->
<%
	for (int j= 1; j <= point.getItems().size(); j++)
	{
		Iterator itItems = point.getItems().iterator();
		encontreItem = false;
		// Tengo que fijarme si el orden del item es el correcto:
		while (!encontreItem && itItems.hasNext()) {
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
		
		if (itemsResult.contains(activityReport.getFormItem(
			point.getText(),item.getText(),sys.getUserSessionData())))
		{
			checkedStr = "checked"; 			
		}
		else
		{
			checkedStr = "";	
		}
%>
        	<input type="checkbox" name="checkbox2" 
              value='<%=item.getId().toString() %>' class="check" <%=checkedStr%>> 
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
		
		if (isCommentaryDrive || isCommentaryDriveOther)
		{
		// Si es commentaryDrive Dibujo un comentario para cada Point
%>	
 	<tr> 
    	<td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>   	
    	<td>
    		<table width="100%" border="0" cellpadding="0" cellspacing="0">
    		<tr>
    			<td valign="top" style="width:15%">
    				<font size="1" face="Arial, Helvetica, sans-serif"> 		       		
		       		&nbsp;<%=messages.getText("generic.activity.observations")%>&nbsp;	
		       		</font>	       		
		       	</td>
		       	<td valign="top" align="left" style="width:85%" >
		       		<font size="1" face="Arial, Helvetica, sans-serif"> 
		       		<%	ArrayList pointCommentList = registryData.getOrderedPointCommentSet();
	       			
	       			if (pointCommentList != null && pointCommentList.size() > 0 &&
	       					((PointCommentData)pointCommentList.get(i-1)).getComment() != null &&
	       					!((PointCommentData)pointCommentList.get(i-1)).getComment().trim().equals("_"))
	       				// "_" es dummy para contabilizar el comentario en los splits, se usa cuando no se ponen
	       				// comentarios.
	       			{ 		
		       		%>
						<textarea name="pointComment<%=i-1%>" style="width:80%" rows="1"><%=((PointCommentData)pointCommentList.get(i-1)).getComment().trim()%></textarea>
		<%			}else{ %>
						<textarea name="pointComment<%=i-1%>" style="width:80%" rows="1"></textarea>
		<%			}%>  	
					</font>	        	
		        </td> 		             
		    </tr>
		    </table>
        </td>        
        <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
     </tr>
 <%		} %>	
	
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
				<textarea name="obs" style="width:100%" rows="5"><%=obs.trim()%></textarea>
<%			}else{ %>
				<textarea name="obs" style="width:100%" rows="5"></textarea>
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




