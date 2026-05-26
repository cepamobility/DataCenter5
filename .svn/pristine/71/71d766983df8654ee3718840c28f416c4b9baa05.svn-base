<%@page language="java" 
	errorPage="../exception.jsp" 
	import="business.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.alert.AlertData"
	import="communication.alert.*"
	import="communication.util.*"
	import="communication.datatype.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.ActivityTypeDataType"
 	import="communication.datatype.tables.AlertPriorityDataType"
 	import="communication.datatype.tables.AlertFrecuencyDataType"
	import="business.activity.ActivityReportFacade"
	import="business.datatype.*"
	import="business.alert.*"
	import="java.util.*"
	import="java.lang.*"
	import="communication.activity.tables.*"
	import ="org.apache.struts.action.DynaActionForm"	
%>

<script language="JavaScript" type="text/javascript">

	function modifyvehicledate(id){
			
			var priority=document.getElementById("priorityvehicle"+id).value;
			var day=document.getElementById("day"+id);
			var month=document.getElementById("month"+id);
			var year=document.getElementById("year"+id);
			var milisecondshigh=parseInt(7*24*60*60*1000); // una semana
			var milisecondsmedium=parseInt(10*24*60*60*1000); // 10 dias
			var milisecondslow=parseInt(15*24*60*60*1000); // 15 dias
			var date=new Date();
	 		var time=date.getTime();
	 		var total=0;
			if (priority=="generic.alert.priority.low"){
				total=date.setTime(parseInt(time+milisecondslow));
			}else if (priority=="generic.alert.priority.medium"){
				total=date.setTime(parseInt(time+milisecondsmedium));
			}else{
				total=date.setTime(parseInt(time+milisecondshigh));
			}
			
			day.value=date.getDate();
			month.value=date.getMonth()+1;
			year.value=date.getFullYear();
		}
		
	</script>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	CheckListData checkList = (CheckListData) session.getAttribute("checkList");
	Collection points = checkList.getPoints();
	VehicleCheckData vehicleCheck = (VehicleCheckData) session.getAttribute("vehicleCheck");
	
	PointData point = null;
	ItemData item = null;
	Text messages=sys.getText();
	boolean encontrePoint = false;
	boolean encontreItem = false;
	int primerPoint, ultimoPoint;
	boolean flag=false;
	int countFlag=0;
	Date alertdate=null;
	int date=0;
	
	primerPoint=Integer.parseInt(request.getParameter("primerPoint"));
	ultimoPoint = Integer.parseInt(request.getParameter("ultimoPoint"));
	String textPoint;
%>	
<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("web.alert.list")%>' />
<table width="100%" border="0" cellpadding="0" cellspacing="0">


<% 		for (int i = primerPoint; i <= ultimoPoint; i++){
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
		DataTypeReportFacade datatypeReportFacade = sys.getDataTypeReportFacade();
		AlertReportFacade alertReportFacade = sys.getAlertReportFacade();

	// Dibujo los items del punto:
		countFlag=0;
		for (int j= 1; j <= point.getItems().size(); j++) {
			flag=false;
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
						flag=true;
					}
					if (vehicleCheck.getValueFromItem(item).contains("3")) {
						if (text.equals("")) {
							text += messages.getText("generic.vehiclecheck.form.conmarcas");
						}
						else {
							text += "; " + messages.getText("generic.vehiclecheck.form.conmarcas");
						}	
						flag=true;
					}			
					if (vehicleCheck.getValueFromItem(item).contains("4")) {
						if (text.equals("")) {
							text += messages.getText("generic.vehiclecheck.form.noposee");
						}
						else {
							text += "; " + messages.getText("generic.vehiclecheck.form.noposee");
						}	
						flag=true;
					}		
				
				}	
				if (text.equals("")) {
					text = messages.getText("generic.vehiclecheck.form.nodata");
				}
		
%>
	 <!--  Dibujo Item: -->
<%			if (flag){
				if (countFlag==0){
%>	
	<!--  Titulo del Punto: -->
	<tr> 
		<td height="40" colspan="5" valign="middle" class="lineone">
			<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText(point.getText())%>' />
			<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">			
<% 				}
				countFlag++;
				date++;
				AlertData alert = alertReportFacade.findAlertWithExtraInformation(AlertConstants.ALERT_VEHICLE,vehicleCheck.getVehicle().getId()+"-"+item.getId(),sys.getUserSessionData());
%> 			    <tr class="lineone">
<% 				if(alert==null){
%>					<td width="30%" align="left"><%=messages.getText(item.getText())%> : <%=text %></td>	
<%				}else{
%>					<td width="30%" align="left" style="font-weight: bold; color: red;"><%=messages.getText(item.getText())%> : <%=text %></td>							
<%				} 
%>			        <td align="left">
						<select name="priorityvehicle" id='<%="priorityvehicle"+date%>'class="input" style="width:120;" onChange="javascript:modifyvehicledate(<%=date%>);">
							<option value=""><%=messages.getText("generic.messages.select")%></option>

<%				Collection types = datatypeReportFacade.findAll(DataTypeConstants.ALERT_PRIORITY,sys.getUserSessionData());
				Iterator it = types.iterator();
           		while(it.hasNext()){
            		AlertPriorityDataType itemType=(AlertPriorityDataType)it.next();
              		if (alert==null){
%>             				<option value='<%=itemType.getResourceBundleId()%>'><%=messages.getText(itemType.getResourceBundleId())%></option>
<%					}else if (alert.getPriority().getResourceBundleId().equals(itemType.getResourceBundleId()) ){
%>							<option value='<%=itemType.getResourceBundleId()%>' selected="selected"><%=messages.getText(itemType.getResourceBundleId())%></option>
<%            		}else{
%>							<option value='<%=itemType.getResourceBundleId()%>'><%=messages.getText(itemType.getResourceBundleId())%></option>
<%	
					}	
				}
%>						</select>   											
    				</td>
    				<td align="left">
						<select name="frecuencyvehicle" id='<%="frecuencyvehicle"+date%>'class="input" style="width:120;" >										
<%				types = datatypeReportFacade.findAll(DataTypeConstants.ALERT_FRECUENCY,sys.getUserSessionData());
				it = types.iterator();
           		while(it.hasNext()){
              		AlertFrecuencyDataType itemType=(AlertFrecuencyDataType)it.next();
              		if (alert==null){
%>   						<option value='<%=itemType.getResourceBundleId()%>' ><%=messages.getText(itemType.getResourceBundleId())%></option>
<%					}else if (alert.getFrecuency().getResourceBundleId().equals(itemType.getResourceBundleId()) ){
%>							<option value='<%=itemType.getResourceBundleId()%>' selected="selected"><%=messages.getText(itemType.getResourceBundleId())%></option>					
<%					}else{
%>							<option value='<%=itemType.getResourceBundleId()%>' ><%=messages.getText(itemType.getResourceBundleId())%></option>
<%					}
				}
%>						</select>   											
    				</td>
					<td align="left" class="lineone" width="15%">
						<input type='hidden' name='<%="vehicle"+date%>' value='<%=item.getId()%>' id='<%="vehicle"+date%>'/>
						<input type='hidden' name='<%="vehicletext"+date%>' value='<%=item.getText()%>' id='<%="vehicletext"+date%>'/>
<%				if (alert==null){	 
%>						<input type="text" name='<%="day"+date%>' style="width:20;" class="input" id='<%="day"+date%>'/>/
		    			<input type="text" name='<%="month"+date%>' style="width:20;" class="input" id='<%="month"+date%>'/>/
		    			<input type="text" name='<%="year"+date%>' style="width:40;" class="input" id='<%="year"+date%>'/>
<%
	}else{ 
	alertdate =alert.getLastdate();
	Calendar calendar = Util.getCalendar(alertdate);
%>						<input type="text" name='<%="day"+date%>' style="width:20;" class="input" id='<%="day"+date%>' value='<%=calendar.get(Calendar.DAY_OF_MONTH)%>'/>/
						<input type="text" name='<%="month"+date%>' style="width:20;" class="input" id='<%="month"+date%>' value='<%=calendar.get(Calendar.MONTH)+1%>'/>/
	 					<input type="text" name='<%="year"+date%>' style="width:40;" class="input" id='<%="year"+date%>' value='<%=calendar.get(Calendar.YEAR)%>'/>
<% 				}			
%>					</td>
					<td align="left" class="lineone" width="7%">
						<input type="checkbox" name='<%="datevehicle"+date%>' id='<%="datevehicle"+date%>' />
					</td>
     			</tr>
     			<tr class="lineone">
					<jsp:include  page="../component/spaceline.jsp" />
  				</tr>
<%         }		
		} // fin for Items
		if (countFlag>0){
%>			</table>
			<jsp:include  page="../component/utilitybox2bottom.jsp" />
		</td>
	</tr> 
	<tr>
		<jsp:include  page="../component/spaceline.jsp" />
  	</tr> 	
<%		}
%>
	     
<%		
	} // fin for Points
	if (date==0){
		response.sendRedirect("vehicle/VehicleCheckAlert.jsp");
	}
	
%>
</table>
<jsp:include  page="../component/utilityboxbottom.jsp" />




