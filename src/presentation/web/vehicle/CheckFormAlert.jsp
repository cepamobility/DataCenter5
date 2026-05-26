<%@page language="java" 
	errorPage="../exception.jsp" 
	import="business.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="business.alert.*"
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.util.*"
	import="communication.datatype.*"
	import="communication.alert.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="presentation.translator.web.vehicle.*"
 	import="communication.administration.user.*" 
 	import="communication.datatype.tables.AlertPriorityDataType"
 	import="communication.datatype.tables.AlertFrecuencyDataType"
	import="business.vehicle.VehicleReportFacade"
	import="java.util.*"	
	import ="org.apache.struts.action.DynaActionForm"
	import ="communication.activity.tables.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>


<html:html>
<%	RequestDispatcher dispatcher; 
	Text messages; SystemAccess sys;
	UserSessionData userSessionData; int date;
	VehicleData  vehicle; AlertData alert; 
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();	
	
	String check = Util.checkPermission(sys,SecurityConstants.ALERT_VIEW);
	date=0;
	if ((check!=null))  {
		response.sendRedirect(request.getContextPath()+check);	
	}else {
		messages=sys.getText();
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
		String idCheck = request.getParameter("check");
		if(Util.empty(idCheck)) {
		    idCheck = String.valueOf(session.getAttribute("check"));
		    System.out.println(idCheck);
		}
		Date alertdate=null;
		CheckFormAlert form = null;
		String idTrailer=null;
		String trailerPlate="0";
		session.setAttribute("url","vehicle/CheckFormAlert.jsp?check="+idCheck);
		
		VehicleCheckData vehicleCheck = sys.getVehicleReportFacade().findVehicleCheck(new Integer(idCheck),userSessionData);
		String idVehicle=String.valueOf(vehicleCheck.getVehicle().getId());
		Collection trailerChecks = sys.getVehicleReportFacade().getCheckListTrailers(new Integer(idCheck),sys.getUserSessionData());
		
		session.setAttribute("id",idVehicle);
		form = new CheckFormAlert();
		form.setVehiclecheck(new Integer(idVehicle));
		form.setVehicleplate(vehicleCheck.getVehicle().getMatricula());
		Collection trailersPlates= new LinkedList();
		Collection trailersChecks=new LinkedList();
		Iterator trailerIt = trailerChecks.iterator();
		int countTrailers=0;
		int countTrailersAux=0;
		TrailerCheckData checkData = null;
		while(trailerIt.hasNext()) {
	checkData = (TrailerCheckData)trailerIt.next();
	if (checkData!=null){
		countTrailersAux++;
		trailersChecks.add(checkData.getTrailerCheckId());
		trailersPlates.add(checkData.getTrailer().getPlate());
	}
		}
		form.setTrailerschecks(trailersChecks);
		form.setTrailersplates(trailersPlates);
		session.setAttribute("CheckFormAlert",form);
		
		DataTypeReportFacade datatypeReportFacade = sys.getDataTypeReportFacade();
		AlertReportFacade alertReportFacade = sys.getAlertReportFacade();
%>	
<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<script>
	function deleteIfConfirm(form,msg){
		if(confirm(msg)) {
			form.submit();
		}
	} 
</script>
<script language="JavaScript" type="text/javascript">


	function modifytruckdate(id){
		
		var priority=document.getElementById("prioritytruck"+id).value;
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
	
	function modifytrailerdate(id){
		var priority=document.getElementById("prioritytrailer"+id).value;
		var day=document.getElementById("daytrailer"+id);
		var month=document.getElementById("monthtrailer"+id);
		var year=document.getElementById("yeartrailer"+id);
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

	function changeemail(){
		var countemail=document.getElementById("countemail");
		email=null;
		for(i=1;i<=5;i++){
			email=document.getElementById("divemail"+i);
			if (i<=countemail.value){
				email.style.display = 'block';
			}else {
				email.style.display = 'none';
			}
		}
	
	}
	
	
	function preparedEmailList(form){
		var email=null;
		var emailList="";
		var count=0;
		for(i=1;i<=5;i++){
			email=document.getElementById("email"+i);
			if ((email!=null)&&(email.value!="")){
				if(emailList==""){
					emailList = email.value;
				}else{
					emailList = email.value+"<"+emailList;
				}
			count++;			
			}
		}
		if (count==0){
			return false;
		}
		form.emaillist.value=emailList;
		return true;
		
	}
	
	function preparedCheckAlertTruck(form){
		var elements=form.elements;
		var length=elements.length;
		var i;
		var dayList="";
		var itemList="";
		var monthList="";
		var yearList="";
		var separator="<";
		var day=null;
		var month=null;
		var year=null;
		var iditem=null;
		var itemtext="";
		var priority=null;
		var frecuency=null;
		var dateList="";
		var checktruck;
		for(i=1;i<=length;i++){
			checktruck=document.getElementById("datetruck"+i);
			if (checktruck!=null){
				if (!checktruck.checked){
					day=document.getElementById("day"+i);
					iditem=document.getElementById("truck"+i).value;
					itemtext=document.getElementById("trucktext"+i).value;
					month=document.getElementById("month"+i);
					year=document.getElementById("year"+i);
					priority=document.getElementById("prioritytruck"+i).value;
					frecuency=document.getElementById("frecuencytruck"+i).value;
					
					if ((day.value!="")&&(month.value!="")&&(year.value!="")&&(priority!="")&&(frecuency!="")) {	
						if((day.value<=31)&&(month.value<=12)&&(year.value<=3000)){
							if(dateList==""){
								dateList = iditem+"	"+new Date(year.value,month.value-1,day.value)+"	"+priority+"	"+frecuency+"	"+itemtext;
							}else{
								dateList = iditem+"	"+new Date(year.value,month.value-1,day.value)+"	"+priority+"	"+frecuency+"	"+itemtext+"<"+dateList;
							}
						}else{
							return false;
						}
					
					}else{
						return false;
					}
					
				
				}else{
					if(dateList==""){
						dateList = "true  ";
					}else{
						dateList = "true  "+"<"+dateList;
					}			
				}
			}else{
				form.truckdatelist.value=dateList;
				return true;
			}
		}
	}
	
	
	function preparedCheckAlertTrailer(form){
		var elements=form.elements;
		var length=elements.length;
		var i;
		var numbertrailers=0;
		var maxtrailers=document.getElementById("trailerChecks").value;
	
		while(numbertrailers<maxtrailers){
			
			numbertrailers++;
			var dayList="";
			var monthList="";
			var yearList="";
			var separator="<";
			var day=null;
			var month=null;
			var year=null;
			var iditem=null;
			var itemtext="";
			var dateList="";
			var priority=null;
			var frecuency=null;
			for(i=1;i<=length;i++){
				checktruck=document.getElementById("datetrailer"+numbertrailers+"_"+i);
				if (checktruck!=null){
					if (!checktruck.checked){
						day=document.getElementById("daytrailer"+numbertrailers+"_"+i);
						iditem=document.getElementById("trailer"+numbertrailers+"_"+i).value;
						itemtext=document.getElementById("trailertext"+numbertrailers+"_"+i).value;
						month=document.getElementById("monthtrailer"+numbertrailers+"_"+i);
						year=document.getElementById("yeartrailer"+numbertrailers+"_"+i);
						priority=document.getElementById("prioritytrailer"+numbertrailers+"_"+i).value;
						frecuency=document.getElementById("frecuencytrailer"+numbertrailers+"_"+i).value;
						if ((day.value!="")&&(month.value!="")&&(year.value!="")&&(priority!="")&&(frecuency!="")) {
							if((day.value<=31)&&(month.value<=12)&&(year.value<=3000)){
								if(dateList==""){
									dateList = iditem+"	"+new Date(year.value,month.value-1,day.value)+"	"+priority+"	"+frecuency+"	"+itemtext;
								}else{
									dateList = iditem+"	"+new Date(year.value,month.value-1,day.value)+"	"+priority+"	"+frecuency+"	"+itemtext+"<"+dateList;
								}
							}else{
								return false;
							}
						}else{
							return false;
						}
					
				
					}else{
						if(dateList==""){
							dateList = "true  ";
						
						}else{
							dateList = "true  "+"<"+dateList;
						
						}			
					}
				}else{
					if((form.trailersdatelist.value==null)||(form.trailersdatelist.value=="")){
						form.trailersdatelist.value=dateList;
					}else{
						form.trailersdatelist.value=form.trailersdatelist.value+">"+dateList;
					}
					i=length+1;
					
				}
			}
		}
		return true;
	}

	function setAction(form,text){
		if((preparedCheckAlertTruck(form))&&(preparedCheckAlertTrailer(form))&&(preparedEmailList(form))) {
			form.submit();
		}else{
			alert(text);
		}
	}
</script>

<html:form action="/TruckCheckListDelete">
	<html:hidden property="check" value='<%=idCheck%>'/>
</html:form>	

<form action='<%="layout.jsp?refer=/vehicle/TruckCheckForm.jsp?type=edit&check="+vehicleCheck.getVehicleCheckId().toString()%>' name='edit' method="post">
</form>	

<body>

	<center>
	<html:form action='<%="/CheckFormAlert"%>'>
	<html:hidden property="truckdatelist" />
	<html:hidden property="trailersdatelist" />
	<html:hidden property="emaillist" />
	<input type='hidden' name="trailerChecks" value='<%=countTrailersAux%>' id="trailerChecks"/>
							
	<table width="700" border="0" cellspacing="0" cellpadding="0">  
		<tr class="guide1" width="100%">
			<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.activity.form.checklistalertdate")%>' />
  		</tr>
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
		<tr> 
			<td height="40" colspan="5" valign="middle">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.activity.form.tranningonthejob.proofdata")%>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
   	 				<tr> 
       					<td width="50%" align="left" class="linetwo">        
   							<%=messages.getText("generic.activity.date")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<%=Util.formatDate(vehicleCheck.getDate())%>
						</td>
   					</tr>
			      	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
     				<tr> 
        				<td width="50%" align="left" class="linetwo">        
       						<%=messages.getText("generic.driver.name")%>
						</td>
						<td width="50%" align="right" class="linetwo">		
							<%=vehicleCheck.getName()%>
        				</td>
     				</tr>
			      	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
     				<tr class="linetwo"> 
        				<td width="50%" align="left">        
       						<%=messages.getText("generic.driver.licenseNumber")%>
						</td>
						<td width="50%" align="right">		
							<%=vehicleCheck.getLicenseNumber()%>
        				</td>
     				</tr>     
			      	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
     				<tr class="linetwo">        
        				<td width="50%" align="left">        
       						<%=messages.getText("generic.activity.dateLicenseExpiry")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
						</td>
						<td width="50%" align="right">
							<%=Util.formatDate(vehicleCheck.getDateLicenseExpiry())%>
						</td>
     				</tr>
			      	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
     				<tr class="linetwo"> 
        				<td width="50%" align="left">        
       						<%=messages.getText("generic.activity.form.tranningonthejob.trade")%>
						</td>
						<td width="50%" align="right">		
							<%=(vehicleCheck.getVehicle().getTrade().equals("generic.messages.nodata")) ? messages.getText(vehicleCheck.getVehicle().getTrade().getResourceBundleId()) : vehicleCheck.getVehicle().getTrade().getResourceBundleId()%>
        				</td>
     				</tr>
			      	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
      				<tr class="linetwo">
        				<td width="50%" align="left">        
       						<%=messages.getText("generic.vehicle.model")%>
						</td>
						<td width="50%" align="right">		
							<%=vehicleCheck.getVehicle().getModel()%>                                                     
        				</td>
     				</tr>     
			      	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
      				<tr class="linetwo">  
        				<td width="50%" align="left">        
					        <%=messages.getText("generic.activity.form.vehiclecheck.plate")%>
						</td>
						<td width="50%" align="right">		
							<%=vehicleCheck.getVehicle().getMatricula()%>                                                     
        				</td>
     				</tr>
			      	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
      				<tr class="linetwo">
        				<td width="50%" align="left">        
					        <font size="1" face="Arial, Helvetica, sans-serif"> 
						        <%=messages.getText("generic.vehicle.serie")%>
							</td>
						<td width="50%" align="right">		
							<%=vehicleCheck.getSerie()%>                                                     
				        </td>
				     </tr>     
			      	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
      				<tr class="linetwo">  
				        <td width="50%" align="left">        
					        <%=messages.getText("generic.vehiclecheck.form.vehicleKm")%>        
						</td>
						<td width="50%" align="right">		
							<%=String.valueOf(vehicleCheck.getVehicleKm())%>
				        </td>
				     </tr>
				</table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>  
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
<%
	CheckListData checkList = sys.getVehicleReportFacade().getCheckList(CheckListData.TRUCK_CHECKLIST,sys.getUserSessionData());
		Iterator points = checkList.getSortPoints();
%>		<tr>
			<td valign="top" colspan="5">
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="49%" valign="top">
							<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td valign="top">
								<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.activity.form.checklistalertdateitems")%>' />
								<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
								<tr class="linetwo">
									<td width="30%" align="left"><%=messages.getText("alert.checklist.items")%></td>
									<td style="width:120;" align="left"><%=messages.getText("generic.alert.alertpriority")%></td>
									<td style="width:120;" align="left"><%=messages.getText("generic.alert.alertfrecuency")%></td>
									<td width="15%" align="left"><%=messages.getText("generic.alert.search.lastdate")%></td>
									<td width="7%" align="left"><%=messages.getText("generic.message.disable")%></td>
								</tr>
								<tr >
									<jsp:include  page="../component/spaceline.jsp" />
  								</tr>
								
<%
									date=0;
																for(int i = 0; i < 3; i++) {
														    		if(i == 1) {
								%>    		    		
<%
    		    			}else {
    		    		%>						
<%
							int count = (i == 0) ? 4 : 4;
												for(int j = 0; j < count; j++) {
												    PointData point = (PointData)points.next();
												    if(!(point.getId().equals(new Integer(137))  && vehicleCheck.getVehicle().getType().getResourceBundleId().equals("datatype.vehicletype.trator"))) {
						%>								
<%
									Iterator items = point.getSortItems();
																	while(items.hasNext()) {
																		ItemData item = (ItemData)items.next();
																		String value = vehicleCheck.getValueFromItem(item.getId());
																		if((value!=null) && (value.equals("2"))) {
																				
																				alert = alertReportFacade.findAlertWithExtraInformation(AlertConstants.ALERT_TRUCK,idVehicle+"-"+item.getId(),sys.getUserSessionData());
																				date++;
								%>										
								<tr class="linetwo">
<%
	if(alert==null){
%>
										<td width="30%" align="left">
		   									<%=messages.getText(item.getText())%>
										</td>	
<%
		}else{
	%>										<td width="30%" align="left" style="font-weight: bold; color: red;">
		   									<%=messages.getText(item.getText())%>
										</td>
										
<%
											}
										%>
										<td align="left">
											<select name="prioritytruck" id='<%="prioritytruck"+date%>'class="input" style="width:120;" onChange="javascript:modifytruckdate(<%=date%>);">
												<option value=""><%=messages.getText("generic.messages.select")%></option>

<%
	Collection types = datatypeReportFacade.findAll(DataTypeConstants.ALERT_PRIORITY,userSessionData);
		Iterator it = types.iterator();
           				while(it.hasNext()){
              				AlertPriorityDataType itemType=(AlertPriorityDataType)it.next();
              							if (alert==null){
%>             									<option value='<%=itemType.getResourceBundleId()%>'><%=messages.getText(itemType.getResourceBundleId())%></option>
<%
	}else if (alert.getPriority().getResourceBundleId().equals(itemType.getResourceBundleId()) ){
%>												<option value='<%=itemType.getResourceBundleId()%>' selected="selected"><%=messages.getText(itemType.getResourceBundleId())%></option>
<%
	}else{
%>											<option value='<%=itemType.getResourceBundleId()%>'><%=messages.getText(itemType.getResourceBundleId())%></option>
<%
	}	
		}
%>											</select>   											
    					  	    		</td>
    									<td align="left">
											<select name="frecuencytruck" id='<%="frecuencytruck"+date%>'class="input" style="width:120;" >
												
<%
													types = datatypeReportFacade.findAll(DataTypeConstants.ALERT_FRECUENCY,userSessionData);
																										 it = types.iterator();
																						           				while(it.hasNext()){
																						              				AlertFrecuencyDataType itemType=(AlertFrecuencyDataType)it.next();
																						              						if (alert==null){
												%>   										<option value='<%=itemType.getResourceBundleId()%>' ><%=messages.getText(itemType.getResourceBundleId())%></option>
<%
	}else if (alert.getFrecuency().getResourceBundleId().equals(itemType.getResourceBundleId()) ){
%>											<option value='<%=itemType.getResourceBundleId()%>' selected="selected"><%=messages.getText(itemType.getResourceBundleId())%></option>
          							
<%
          								}else{
          							%>											<option value='<%=itemType.getResourceBundleId()%>' ><%=messages.getText(itemType.getResourceBundleId())%></option>
<%
	}
		}
%>										</select>   											
    					  	    		</td>
										<td align="left" class="linetwo" width="15%">
											<input type='hidden' name='<%="truck"+date%>' value='<%=item.getId()%>' id='<%="truck"+date%>'/>
											<input type='hidden' name='<%="trucktext"+date%>' value='<%=item.getText()%>' id='<%="trucktext"+date%>'/>
<%
	if (alert==null){
%>											<input type="text" name='<%="day"+date%>' style="width:20;" class="input" id='<%="day"+date%>'/>/
		            						<input type="text" name='<%="month"+date%>' style="width:20;" class="input" id='<%="month"+date%>'/>/
		            						<input type="text" name='<%="year"+date%>' style="width:40;" class="input" id='<%="year"+date%>'/>
<%
	}else{ 
							alertdate =alert.getLastdate();
							Calendar calendar = Util.getCalendar(alertdate);
%>
											<input type="text" name='<%="day"+date%>' style="width:20;" class="input" id='<%="day"+date%>' value='<%=calendar.get(Calendar.DAY_OF_MONTH)%>'/>/
		            						<input type="text" name='<%="month"+date%>' style="width:20;" class="input" id='<%="month"+date%>' value='<%=calendar.get(Calendar.MONTH)+1%>'/>/
		            						<input type="text" name='<%="year"+date%>' style="width:40;" class="input" id='<%="year"+date%>' value='<%=calendar.get(Calendar.YEAR)%>'/>
<%
	}
%>				       					</td>
			          					<td align="left" class="linetwo" width="7%">
											<input type="checkbox" name='<%="datetruck"+date%>' id='<%="datetruck"+date%>' />
			          					</td>
									</tr>
<%
	if(items.hasNext()) {
%>						      		<tr>
										<jsp:include  page="../component/spaceline.jsp" />
			  						</tr>
<%
	}
					
		}
	}
		
		    }
		}
%>							    
<%
							    	}
							    					    		}
							    %>						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
						</td>	
						</tr>	
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>	
	  	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
<%
	trailerIt = trailerChecks.iterator();
countTrailers=0;
while(trailerIt.hasNext()) {
	countTrailers++;
	checkData = (TrailerCheckData)trailerIt.next();
	if (checkData!=null){
%>		<tr> 
			<td width="100%" colspan="5"valign="middle">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("activity.trailer.checklist")+" "+checkData.getTrailer().getPlate()%>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
      				<tr>
						<jsp:include  page="../component/spaceline.jsp" />
		  			</tr>
					<tr> 
						<td height="40" colspan="3" valign="middle">
							<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.activity.form.tranningonthejob.proofdata")%>' />
								<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
   	 								<tr> 
				       					<td width="50%" align="left"  class="lineone">        
   											<%=messages.getText("generic.activity.date")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
										</td>
										<td width="50%" align="right" class="lineone">
											<%=Util.formatDate(checkData.getDate())%>
										</td>
				   					</tr>
									<tr class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
				     				<tr> 
        								<td width="50%" align="left" class="lineone">        
				       						<%=messages.getText("generic.driver.name")%>
										</td>
										<td width="50%" align="right" class="lineone">		
											<%=(Util.empty(checkData.getName())) ? "----" : checkData.getName()%>
				        				</td>
     								</tr>
									<tr class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
				     				<tr class="lineone"> 
				        				<td width="50%" align="left">        
				       						<%=messages.getText("generic.driver.licenseNumber")%>
										</td>
										<td width="50%" align="right">		
											<%=(Util.empty(checkData.getLicenseNumber())) ? "----" : checkData.getLicenseNumber()%>
				        				</td>
				     				</tr>     
									<tr class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
				     				<tr class="lineone">        
				        				<td width="50%" align="left">        
				       						<%=messages.getText("generic.activity.dateLicenseExpiry")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
										</td>
										<td width="50%" align="right">
											<%=(checkData.getDateLicenseExpiry() == null) ? "----" : Util.formatDate(checkData.getDateLicenseExpiry())%>
										</td>
				     				</tr>
									<tr class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
				     				<tr class="lineone"> 
				        				<td width="50%" align="left">        
				       						<%=messages.getText("generic.activity.form.tranningonthejob.trade")%>
										</td>
										<td width="50%" align="right">		
											<%=messages.getText(checkData.getTrailer().getTrade().getResourceBundleId())%>
				        				</td>
				     				</tr>
									<tr class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
				      				<tr class="lineone">
				        				<td width="50%" align="left">        
				       						<%=messages.getText("generic.vehicle.model")%>
										</td>
										<td width="50%" align="right">		
											<%=checkData.getTrailer().getModel()%>
				        				</td>
				     				</tr>     
									<tr class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
				      				<tr class="lineone">  
				        				<td width="50%" align="left">        
									        <%=messages.getText("generic.activity.form.vehiclecheck.plate")%>
										</td>
										<td width="50%" align="right">		
											<%=checkData.getTrailer().getPlate()%>
				        				</td>
				     				</tr>
									<tr class="lineone"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
				      				<tr class="lineone">
				        				<td width="50%" align="left">        
									        <font face="Arial, Helvetica, sans-serif"> 
										        <%=messages.getText("generic.vehicle.serie")%>
											</td>
										<td width="50%" align="right">		
											<%=checkData.getSerie()%>
								        </td>
								     </tr>     
								</table>
							<jsp:include  page="../component/utilitybox2bottom.jsp" />
						</td>
					</tr>  
      				<tr>
						<jsp:include  page="../component/spaceline.jsp" />
		  			</tr>
<%
	checkList = sys.getVehicleReportFacade().getCheckList(CheckListData.TRAILER_CHECKLIST,sys.getUserSessionData());
		points = checkList.getSortPoints();
%>					<tr>
						<td valign="top">
							<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="49%" valign="top">
										<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td valign="top">
												<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.activity.form.checklistalerttrailer")%>' />
												 <table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
												 <tr class="lineone">
													<td width="30%" align="left"><%=messages.getText("alert.checklist.items")%></td>
													<td style="width:120;" align="left"><%=messages.getText("generic.alert.alertpriority")%></td>
													<td style="width:120;" align="left"><%=messages.getText("generic.alert.alertfrecuency")%></td>
													<td width="15%" align="left"><%=messages.getText("generic.alert.search.lastdate")%></td>
													<td width="7%" align="left"><%=messages.getText("generic.message.disable")%></td>
												</tr>
												<tr class="lineone">
													<jsp:include  page="../component/spaceline.jsp" />
  												</tr>
<%
	date=0;
		for( int i = 0; i < 3; i++) {
	
    		if(i == 1) {
%>    					    	
<%
    					    		}else {
    					    	%>							
<%
								int count = (i == 0) ? 3 : 2;
														for(int j = 0; j < count; j++) {
														    PointData point = (PointData)points.next();
							%>											
<%
												Iterator items = point.getSortItems();
																							while(items.hasNext()) {
																								ItemData item = (ItemData)items.next();
																								String value = checkData.getValueFromItem(item.getId());
																								
																								if((value!=null) && (value.equals("2"))) {
																									
																									alert = alertReportFacade.findAlertWithExtraInformation(AlertConstants.ALERT_TRAILER,checkData.getTrailerCheckId()+"-"+item.getId(),sys.getUserSessionData());
																									date++;
											%>								<tr class="lineone">
<%
	if (alert==null){
%>									<td width="30%" align="left">
		   								<%=messages.getText(item.getText())%>
									</td>
<%
	}else{
%>									<td width="30%" align="left" style="font-weight: bold; color: red;">
		   								<%=messages.getText(item.getText())%>
									</td>
<%
	}
%>								<td align="left">
											<select name='<%="prioritytrailer"+countTrailers+"_"+date%>' id='<%="prioritytrailer"+countTrailers+"_"+date%>' class="input" style="width:120;" onChange="javascript:modifytrailerdate(<%="'"+countTrailers+"_"+date+"'"%>);">
												<option value=""><%=messages.getText("generic.messages.select")%></option>
<%
	Collection types = datatypeReportFacade.findAll(DataTypeConstants.ALERT_PRIORITY,userSessionData);
			
			Iterator it2 = types.iterator();
           					while(it2.hasNext()){
              					AlertPriorityDataType itemType=(AlertPriorityDataType)it2.next();
              					if (alert==null){
%>             									<option value='<%=itemType.getResourceBundleId()%>' ><%=messages.getText(itemType.getResourceBundleId())%></option>
<%
	}else if (alert.getPriority().getResourceBundleId().equals(itemType.getResourceBundleId()) ){
%>											<option value='<%=itemType.getResourceBundleId()%>' selected="selected"><%=messages.getText(itemType.getResourceBundleId())%></option>
<%
	}else{
%>											<option value='<%=itemType.getResourceBundleId()%>'><%=messages.getText(itemType.getResourceBundleId())%></option>
<%
	}
			}
%>

											</select>   											
    					  	    	</td>
    								<td align="left">
											<select name='<%="frecuencytrailer"+countTrailers+"_"+date%>' id='<%="frecuencytrailer"+countTrailers+"_"+date%>' class="input" style="width:120;" >
												
<%
													types = datatypeReportFacade.findAll(DataTypeConstants.ALERT_FRECUENCY,userSessionData);
																											
																											 Iterator it = types.iterator();
																						           					while(it.hasNext()){
																						              					AlertFrecuencyDataType itemType=(AlertFrecuencyDataType)it.next();
																						              					if (alert==null){
												%>             									<option value='<%=itemType.getResourceBundleId()%>' ><%=messages.getText(itemType.getResourceBundleId())%></option>
<%
	}else if (alert.getFrecuency().getResourceBundleId().equals(itemType.getResourceBundleId()) ){
%>											<option value='<%=itemType.getResourceBundleId()%>' selected="selected"><%=messages.getText(itemType.getResourceBundleId())%></option>
<%
	}else{
%>												<option value='<%=itemType.getResourceBundleId()%>' ><%=messages.getText(itemType.getResourceBundleId())%></option>
<%
	}
			}
%>
										</select>   											
    					  	    	</td>	
									
								
									<td align="left" class="lineone" width="15%">
											<input type='hidden' name='<%="trailer"+countTrailers+"_"+date%>' value='<%=item.getId()%>' id='<%="trailer"+countTrailers+"_"+date%>'/>
											<input type='hidden' name='<%="trailertext"+countTrailers+"_"+date%>' value='<%=item.getText()%>' id='<%="trailertext"+countTrailers+"_"+date%>'/>
<%
	if (alert==null){
%>											
											<input type="text" name='<%="daytrailer"+countTrailers+"_"+date%>' style="width:20;" class="input" id='<%="daytrailer"+countTrailers+"_"+date%>'/>/
		            						<input type="text" name='<%="monthtrailer"+countTrailers+"_"+date%>' style="width:20;" class="input" id='<%="monthtrailer"+countTrailers+"_"+date%>'/>/
		            						<input type="text" name='<%="yeartrailer"+countTrailers+"_"+date%>' style="width:40;" class="input" id='<%="yeartrailer"+countTrailers+"_"+date%>'/>
<%
	}else{ 
							alertdate =alert.getLastdate();
							Calendar calendartrailer = Util.getCalendar(alertdate);
%>	
											<input type="text" name='<%="daytrailer"+countTrailers+"_"+date%>' style="width:20;" class="input" id='<%="daytrailer"+countTrailers+"_"+date%>' value='<%=calendartrailer.get(Calendar.DAY_OF_MONTH)%>'/>/
		            						<input type="text" name='<%="monthtrailer"+countTrailers+"_"+date%>' style="width:20;" class="input" id='<%="monthtrailer"+countTrailers+"_"+date%>' value='<%=calendartrailer.get(Calendar.MONTH)+1%>'/>/
		            						<input type="text" name='<%="yeartrailer"+countTrailers+"_"+date%>' style="width:40;" class="input" id='<%="yeartrailer"+countTrailers+"_"+date%>' value='<%=calendartrailer.get(Calendar.YEAR)%>'/>
<% 									}			
%>			          				
			          				</td>
			          				<td align="left" class="lineone" width="7%">
			          				<input type="checkbox" name='<%="datetrailer"+countTrailers+"_"+date%>' id='<%="datetrailer"+countTrailers+"_"+date%>'/>
			          				</td>
								</tr>
<%							if(items.hasNext()) {	
%>						      	<tr class="lineone">
									<jsp:include  page="../component/spaceline.jsp" />
			  					</tr>
<%							}
						}
					}

				}	
%>											
<%			}
		
		}
%>								
											</table>
												<jsp:include  page="../component/utilitybox2bottom.jsp" />
											</td>	
										</tr>	
									</table>
									</td>		
								</tr>
							</table>
											
						</td>
					</tr>	
				</table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>	
	  	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
<%		
	}
}


%>		
      	<tr > 
			<td width="100%" colspan="2" valign="middle">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.message.sendemail")%>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
      				<tr> 
						<td height="40" colspan="2" valign="middle">
							<jsp:include  page='<%="../component/utilitybox2top.jsp?title= " %>'/>
								<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0" class="lineone">
									<tr class="lineone">
										<td style="width:130;" align="left" class="lineone"><%=messages.getText("alert.count.email")%></td>
										<td align="left" class="lineone">
		  									<select name="countemail" id="countemail" class="input" style="width:40;" onChange="javascript:changeemail();">
		  										<option value="1" >1</option>
		  										<option value="2" >2</option>
		  										<option value="3" >3</option>
		  										<option value="4" >4</option>
		  										<option value="5" >5</option>
		  									</select>
		  								</td>
		  							</tr>
		  							<tr>
										<jsp:include  page="../component/spaceline.jsp" />
		  							</tr>
		  							<tr>
		  								<td class="lineone" colspan="5">
		  									<div id="divemail1">
		  										<table class="lineone">
		  											<tr>
														<th align="left" class="lineone"> <%=messages.getText("alert.message.email")+" 1"%></th>
				       	 								<td align="right" class="lineone"><input type='text' style="width:200;" class="input" name="email1" value="" id="email1"/></td>
													</tr>
		      										<tr>
														<jsp:include  page="../component/spaceline.jsp" />
		  											</tr>
		  										</table>
		  									</div>
		  									<div id="divemail2" style="display:none">
		  										<table class="lineone">
		  	       									<tr>
														<th align="left" class="lineone"> <%=messages.getText("alert.message.email")+" 2"%></th>
				     	   								<td align="right" class="lineone"><input type='text' style="width:200;" class="input" name="email2" value="" id="email2"/></td>
													</tr>
													<tr>
														<jsp:include  page="../component/spaceline.jsp" />
		  											</tr>
		  										</table>
		  									</div>
		  									<div id="divemail3" style="display:none">
		  										<table class="lineone">
		  											<tr>
														<th align="left" class="lineone"> <%=messages.getText("alert.message.email")+" 3"%></th>
				     									<td align="right" class="lineone"><input type='text' style="width:200;" class="input" name="email3" value="" id="email3"/></td>
													</tr>
													<tr>
														<jsp:include  page="../component/spaceline.jsp" />
		  											</tr>
		  										</table>
		  									</div>
		  									<div id="divemail4" style="display:none">
		  										<table class="lineone">
		  											<tr>
														<th align="left" class="lineone"> <%=messages.getText("alert.message.email")+" 4"%></th>
				        								<td align="right" class="lineone"><input type='text' style="width:200;" class="input" name="email4" value="" id="email4"/></td>
													</tr>
													<tr>
														<jsp:include  page="../component/spaceline.jsp" />
		  											</tr>
		  										</table>
		  									</div>
		  									<div id="divemail5" style="display:none">
		  										<table class="lineone">
		  											<tr>
														<th align="left" class="lineone"> <%=messages.getText("alert.message.email")+" 5"%></th>
				       	 								<td align="right" class="lineone"><input type='text' style="width:200;" class="input" name="email5" value="" id="email5"/></td>
													</tr>
													<tr>
														<jsp:include  page="../component/spaceline.jsp" />
		  											</tr>
		  										</table>
		  									</div>
		  								</td>
		  							</tr>
		  						</table>
		  						<jsp:include  page="../component/utilitybox2bottom.jsp" />
		  					</td>
		  				</tr>
				</table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>	 
				 			

       	<tr>   		
	   		<td align="right">     		
	   			<button type="button" onClick="javascript:setAction(document.forms['CheckFormAlert'],'<%=messages.getText("generic.alert.invaliddata")%>');" class="input"><%=messages.getText("generic.messages.accept")%></button>	 		                             
	 			<html:cancel styleClass="input"><%=messages.getText("generic.messages.cancel")%></html:cancel >
           	</td>                 							
   	  	</tr>              
	</table>
	</html:form>
	</center>
	
</body>
<%
}	
 %>	

</html:html>
