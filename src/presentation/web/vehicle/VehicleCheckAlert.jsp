<%@page language="java" 
	errorPage="../exception.jsp" 
	import="business.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.ActivityTypeDataType"
	import="business.vehicle.VehicleReportFacade"
	import="java.util.*"	
	import ="org.apache.struts.action.DynaActionForm"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<script language="JavaScript" type="text/javascript">

function preparedCheckAlertVehicle(form){
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
			checktruck=document.getElementById("datevehicle"+i);
			if (checktruck!=null){
				if (!checktruck.checked){
					day=document.getElementById("day"+i);
					iditem=document.getElementById("vehicle"+i).value;
					itemtext=document.getElementById("vehicletext"+i).value;
					month=document.getElementById("month"+i);
					year=document.getElementById("year"+i);
					priority=document.getElementById("priorityvehicle"+i).value;
					frecuency=document.getElementById("frecuencyvehicle"+i).value;
					
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



function setActionForm(form,text){
		if((preparedCheckAlertVehicle(form))&&(preparedEmailList(form))) {
			form.submit();
		}else{
			alert(text);
		}
}

</script>
<html:html>
<%!
	RequestDispatcher dispatcher; 
	String context;String pathView; String pathMsg; 
	String pathSearch;
	String pathLanguage; Text messages; SystemAccess sys;
	Integer pageNum; boolean all; int pageSize; 
	String attribute; String value;
	UserSessionData userSessionData; 
	ActivityReportFacade activityReport;
	String str1, str2, str3,col, idVehicle;
	int columns;
//	DynaActionForm vehicleCheckForm; 
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();	

	String check = Util.checkPermission(sys,SecurityConstants.ALERT_VIEW);
		
	if (check!=null)  {
		response.sendRedirect(request.getContextPath()+check);	
	}else {
		messages=sys.getText();
		pathLanguage="/util/jsp/language.jsp";	
		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
	
		context=request.getContextPath();
		pathMsg="/util/jsp/message.jsp";
	
		session.setAttribute("url","vehicle/VehicleCheckAlert.jsp");
		
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);
		
		String obsStr = "";
		String nameStr = "";	
		String tradeStr = "";			
		String modelStr = "";					
		String plateStr = "";					
		String serieStr = "";			
		String vehicleKmStr = "";					
		String day = "";		
		String month = "";		
		String year = "";	
		
		String dayLicenseExpiry = "";		
		String monthLicenseExpiry = "";		
		String yearLicenseExpiry = "";			
		
		String licenseNumberStr ="";
	
		String idVehicle =(String)request.getParameter("id");
		if (idVehicle==null){
	Integer idVehicleaux = (Integer)session.getAttribute("idVehicleAlert");
	idVehicle= String.valueOf(idVehicleaux.intValue());
		}
		if(idVehicle==null){
	Integer idVehicleaux = (Integer)session.getAttribute("id");
	idVehicle= String.valueOf(idVehicleaux.intValue());
		}
		session.setAttribute("id",idVehicle);
		
		VehicleReportFacade vehicleReport = null;
		VehicleCheckData  vehicleCheck = null;
		if (idVehicle!=null) {
	vehicleReport = sys.getVehicleReportFacade(); 
	vehicleCheck = vehicleReport.findLastVehicleCheck(new Integer(idVehicle),userSessionData);
	if (vehicleCheck != null) {
		if (vehicleCheck.getVehicle().getTrade() != null) {
	tradeStr = messages.getText(vehicleCheck.getVehicle().getTrade().getResourceBundleId());
		}
		if (vehicleCheck.getVehicle().getModel() != null) {
	modelStr = vehicleCheck.getVehicle().getModel();						
		}
		if (vehicleCheck.getVehicle().getMatricula() != null) {				
	plateStr = vehicleCheck.getVehicle().getMatricula();				
		}
		if (vehicleCheck.getSerie() != null) {	
	serieStr = messages.getText(vehicleCheck.getSerie());					
		}
		if (vehicleCheck.getVehicleKm()!=null) {
	vehicleKmStr = vehicleCheck.getVehicleKm().toString();				
		}	
		if (vehicleCheck.getObs() != null) {
	obsStr = vehicleCheck.getObs();					
		}
		if (vehicleCheck.getName() != null) {
	nameStr = vehicleCheck.getName();
		}	
		
		if (vehicleCheck.getLicenseNumber() != null) {
	licenseNumberStr = vehicleCheck.getLicenseNumber();
		}					
		
		if (vehicleCheck.getDate() != null) {
	Calendar date = Calendar.getInstance();					
	date.setTime(vehicleCheck.getDate());
	day=String.valueOf(date.get(Calendar.DATE));
	month=String.valueOf(date.get(Calendar.MONTH)+1);				
	year=String.valueOf(date.get(Calendar.YEAR));					
		}
		
		if (vehicleCheck.getDateLicenseExpiry() != null) {
	Calendar date = Calendar.getInstance();					
	date.setTime(vehicleCheck.getDateLicenseExpiry());
	dayLicenseExpiry=String.valueOf(date.get(Calendar.DATE));
	monthLicenseExpiry=String.valueOf(date.get(Calendar.MONTH)+1);				
	yearLicenseExpiry=String.valueOf(date.get(Calendar.YEAR));					
		}				

	}	
	
	session.setAttribute("vehicleCheck",vehicleCheck);
		}	
		
		col = request.getParameter("columns");		
		if (!Util.empty(col))
	columns	 = (Integer.parseInt(col));
		else
	columns = 1;
		
		String action = "layout.jsp?refer=/vehicle/VehicleView.jsp" + "?id=" + idVehicle;
%>	
<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<div align="center">
<html:form  action='<%="/VehicleFormAlert"%>'>
   <html:hidden property="vehiclecheck" value='<%=idVehicle%>' />
   <html:hidden property="vehicleplate" value='<%=vehicleCheck.getVehicle().getMatricula()%>' />
   <html:hidden property="truckdatelist" />
   <html:hidden property="emaillist" />

 <table width="800" border="0" cellspacing="0" cellpadding="0">  
 	<tr class="guide1">
		<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.activity.form.checklistalertdate")%>' />
 	</tr>
 	<tr>
		<jsp:include  page="../component/spaceline.jsp" />
  	</tr>
 	<tr width="100%"> 
		<td height="40" colspan="3" valign="middle" >
			<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.activity.form.tranningonthejob.proofdata")%>' />
			<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
     			<tr class="linetwo"> 
        			<td width="50%" align="left"><%= messages.getText("generic.activity.date")%></td>
					<td width="50%" align="right"><%= day%>/<%= month%>/<%= year%></td>  
     			</tr>
     			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
     			<tr class="linetwo"> 
       				<td width="50%" align="left"><%= messages.getText("generic.driver.name")%></td>
					<td width="50%" align="right"><%= nameStr%></td>
     			</tr>     
     			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
     			<tr class="linetwo"> 
       				<td width="50%" align="left"><%= messages.getText("generic.driver.licenseNumber")%></td>
					<td width="50%" align="right"><%= licenseNumberStr%></td> 
    			</tr>     
     			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
     			<tr class="linetwo"> 
       				<td width="50%" align="left"><%= messages.getText("generic.activity.date")%></td>
					<td width="50%" align="right"><%= dayLicenseExpiry%>/<%= monthLicenseExpiry%>/<%= yearLicenseExpiry%></td>
				</tr>
     			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
     			<tr class="linetwo">         
        			<td width="50%" align="left"><%= messages.getText("generic.activity.form.tranningonthejob.trade")%></td>
					<td width="50%" align="right"><%= tradeStr%></td>
     			</tr>
      			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
      			<tr class="linetwo">
        			<td width="50%" align="left"><%= messages.getText("generic.vehicle.model")%></td>
					<td width="50%" align="right"><%= modelStr%></td>
     			</tr>      
     			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
     			<tr class="linetwo">  
        			<td width="50%" align="left"><%= messages.getText("generic.activity.form.vehiclecheck.plate")%></td>
					<td width="50%" align="right"><%= plateStr%> </td>
     			</tr>
     			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
     			<tr class="linetwo">
        			<td width="50%" align="left"><%= messages.getText("generic.vehicle.serie")%></td>
					<td width="50%" align="right"><%= serieStr%></td>
     			</tr>
     			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>       
     			<tr class="linetwo">  
        			<td width="50%" align="left"><%= messages.getText("generic.vehiclecheck.form.vehicleKm")%></td>
					<td width="50%" align="right"><%= vehicleKmStr%></td>                      
     			</tr>
     		</table>
     		<jsp:include  page="../component/utilityboxbottom.jsp" />
     	</td>
    </tr>
    <tr>
		<jsp:include  page="../component/spaceline.jsp" />
	</tr>
	<tr>
		<td>
  			<jsp:include  page='<%="NColumnsFormView.jsp?cantCol=1&type=alert" %>'/>
		</td>  
	</tr>
	<tr>
		<jsp:include  page="../component/spaceline.jsp" />
	</tr>
	<tr > 
			<td width="60%" colspan="2" valign="middle">
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
		  								<td class="lineone">
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
	   		<input type="hidden" name="checkboxParamList" />   
	   		<button type="button" onClick="javascript:setActionForm(document.forms['CheckFormAlert'],'<%=messages.getText("generic.alert.invaliddata")%>');" class="input"><%=messages.getText("generic.messages.accept")%></button>	 		                             
	 		<html:cancel styleClass="input"><%=messages.getText("generic.messages.cancel")%></html:cancel >		                             		   			
    	</td>             
            	
	</tr>              
  </table>
</html:form>
</div>
</body>

<%
	}	
%>	
	<html:form action="VehicleCheckdelete">
	</html:form>	

</html:html>