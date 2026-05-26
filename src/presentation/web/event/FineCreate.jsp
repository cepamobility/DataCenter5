<%@page language="java" 
	errorPage="../exception.jsp"
	import="business.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.event.*"
	import="communication.driver.tables.*"
	import="communication.language.*"
	import="communication.datatype.*"
	import="communication.datatype.tables.*"
	import="communication.driver.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.security.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>
<%!
	RequestDispatcher dispatcher; SystemAccess sys;	String context;
	String pathLanguage; Text messages; Integer driverId; String driverName; String driverLastName;
	FineForm eventForm;
%>
<%

	try{

	sys = (SystemAccess)session.getAttribute("SystemAccess");
	if(sys == null){
		response.sendRedirect("/exception.jsp");
	}
	int corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();
	String check = null;
	if(sys.getUserSessionData().isDriver()) {
	    check = Util.checkPermission(sys,SecurityConstants.DRIVER_CREATE_FINE);
	}else {
	    check = Util.checkPermission(sys,SecurityConstants.CREATE_FINE);
	}
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		DriverData driver = null;
		if((session.getAttribute("FineForm") != null)&&((request.getParameter("vehicle")==null)||(!request.getParameter("vehicle").equals("-1")))) {
		    eventForm = (FineForm)session.getAttribute("FineForm");
			if(sys.getUserSessionData().isDriver()) {
			    driver = sys.getUserSessionData().getDriverData();
			    driverId = driver.getId();
			}else {
			    driverId = new Integer(eventForm.getDriverid());
			    driver = sys.getDriverReportFacade().findDriverByPrimaryKey(driverId,sys.getUserSessionData());
			}
		}else {
			if(sys.getUserSessionData().isDriver()) {
			    driver = sys.getUserSessionData().getDriverData();
			    driverId = driver.getId();
			}else {
			    driverId = new Integer(request.getParameter("id"));
			    driver = sys.getDriverReportFacade().findDriverByPrimaryKey(driverId,sys.getUserSessionData());
			}
		    eventForm = new FineForm(driver);
			session.setAttribute("FineForm",eventForm);
		}	    
		if((request.getParameter("vehicle") != null)&&(!request.getParameter("vehicle").equals("-1"))) {
		    eventForm.setVehicleId(request.getParameter("vehicle"));
		}
			
		context=request.getContextPath();
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
		
		messages=sys.getText();
		session.setAttribute("target","FineCreate");
	
		OptionItem emptyObject = new OptionItem(messages.getText("generic.messages.select"),"");
		OptionItem noDataObject = new OptionItem(messages.getText("generic.messages.nodata"),"generic.messages.nodata");
	
		Collection trade = new LinkedList();
		trade.add(emptyObject);                
		Iterator iter = sys.getDataTypeReportFacade().findAll(DataTypeConstants.TYPETRADE,sys.getUserSessionData()).iterator();
		while(iter.hasNext()) {
			String value = ((TypeTradeDataType)iter.next()).getResourceBundleId();
			trade.add(new OptionItem(messages.getText(value),value));				
		}
		request.setAttribute("trades",trade);
	
		Collection years = new LinkedList();
		years.add(emptyObject);
		years.add(noDataObject);
		years.add(new OptionItem(messages.getText("generic.year.beforeninety"),"generic.year.beforeninety"));
		Calendar instance = Calendar.getInstance();
		for(int i = 1990; i <= instance.get(Calendar.YEAR)+5; i++) {
			years.add(new OptionItem(String.valueOf(i),String.valueOf(i)));
		}
		request.setAttribute("years",years);
	
		Collection types = new LinkedList();
		types.add(emptyObject);                
		iter = sys.getDataTypeReportFacade().findAll(DataTypeConstants.FINE_TYPE,sys.getUserSessionData()).iterator();
		while(iter.hasNext()) {
			String value = ((FineTypeDataType)iter.next()).getResourceBundleId();
			types.add(new OptionItem(messages.getText(value),value));				
		}
		Collections.sort((List)types);
		request.setAttribute("types",types);
	
		Collection qualifies = new LinkedList();
		qualifies.add(emptyObject);                
		iter = sys.getDataTypeReportFacade().findAll(DataTypeConstants.FINE_QUALIFY,sys.getUserSessionData()).iterator();
		while(iter.hasNext()) {
			String value = ((FineQualifyDataType)iter.next()).getResourceBundleId();
			qualifies.add(new OptionItem(messages.getText(value),value));				
		}
		Collections.sort((List)qualifies);
		request.setAttribute("qualifies",qualifies);
		boolean showSpeedAttributes=false;
		if(sys.isCorporation(CorporationData.JJ)){
			if(driver!=null && !driver.getCountryName().equals(CountryData.BRASIL)){
				Iterator iterQualify=qualifies.iterator();
				qualifies=new ArrayList();
				while(iterQualify.hasNext()){
					OptionItem item=(OptionItem)iterQualify.next();
					if(! (
							item.getValue().equals("datatype.finequalify.speedto20") ||
							item.getValue().equals("datatype.finequalify.speedto50") ||
							item.getValue().equals("datatype.finequalify.speedover50") ||
							item.getValue().equals("datatype.finequalify.speedover20") ||
							item.getValue().equals("datatype.finequalify.speedover20to50")
						  )
						){
						
						qualifies.add(item);
					}
				}
				showSpeedAttributes=true;
				request.setAttribute("qualifies",qualifies);

			}else{
				Iterator iterQualify=qualifies.iterator();
				qualifies=new ArrayList();
				while(iterQualify.hasNext()){
					OptionItem item=(OptionItem)iterQualify.next();
					if(! (
							item.getValue().equals("datatype.finequalify.speedexceed")
						  )
						){
						
						qualifies.add(item);
					}
				}
				
				request.setAttribute("qualifies",qualifies);
			}
		}
	
		
%>

<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
</script>

<script language="javascript" type='text/javascript' >

	var showAttrsSpeed=<%=corporationId==CorporationData.JJ?"'S'":"'N'"%>;

	function selQualify(sel){
		if(showAttrsSpeed=='N') return;
		
		if($(sel).val()=='datatype.finequalify.speedexceed'){
			$('tr[tag="showSpeed"]').each(function(){$(this).show()});
		}else{
			$('tr[tag="showSpeed"]').each(function(){$(this).hide()});
		}
		
		
	}

	$(document).ready(
			function(){
				$('tr[tag="showSpeed"]').each(function(){$(this).hide()});
			}
	);
	
	function onSelectFine(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayIdFine");
  		var input_month = document.getElementById("monthIdFine");
  		var input_year = document.getElementById("yearIdFine");
  		var date3 = new Date(time);
  		input_day.value = date3.print("%d");
  		input_month.value = date3.print("%m");
  		input_year.value= parseInt(date3.print("%Y"),10);
  	}

	function onSelectExpiry(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
		var input_day = document.getElementById("expiryDayIdFine");
  		var input_month = document.getElementById("expiryMonthIdFine");
  		var input_year = document.getElementById("expiryYearIdFine");
  		var date4 = new Date(time);
  		input_day.value = date4.print("%d");
  		input_month.value = date4.print("%m");
  		input_year.value= parseInt(date4.print("%Y"),10);
  	}

	function onSelectLote(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
		// var input_day = document.getElementById("dayIdFine");
  		var input_month = document.getElementById("monthIdLote");
  		var input_year = document.getElementById("yearIdLote");
  		var date4 = new Date(time);
  		// input_day.value = date4.print("%d");
  		input_month.value = date4.print("%m");
  		input_year.value= parseInt(date4.print("%Y"),10);
  	}

	function changeCollision(){
		if(document.forms[0].collision[6].checked) {
			document.forms[0].pedestrian.checked = true;
		}		
	}
	function nodata(form) {
		form.elements['hour'].disabled = form.elements['nodatahour'].checked;
		form.elements['minute'].disabled = form.elements['nodatahour'].checked;
		if(form.elements['nodatahour'].checked) {
			form.elements['hour'].value = '';
			form.elements['minute'].value = '';
		}
	}
	function existEvent() {
		var form = document.forms['FineForm'];
		var id = '0';
		var driver = '<%=String.valueOf(driverId)%>';
		var vehicleId = '<%=(Util.empty(eventForm.getVehicleId())) ? "0" : eventForm.getVehicleId()%>';
		var vehicleplate = '';
		if(vehicleId == '0') {
			vehicleplate = form.vehicleplate.value;
		}
		if(vehicleId == '0' && vehicleplate == '') {
			alert('<%=messages.getText("generic.event.eventdata.vehicle.plateEmpty")%>');
		} else if(form.date.value == '' || isNaN(form.date.value)
				|| form.month.value == '' || isNaN(form.month.value)
				|| form.year.value == '' || isNaN(form.year.value)) {
			alert('<%=messages.getText("generic.event.eventdata.dateInvalid")%>');
		} else if(form.qualify.selectedindex == 0) {
			alert('<%=messages.getText("generic.event.finedata.qualifyEmptyy")%>');
		}else {
			$.getJSON("event/existFine.jsp?driver="+driver+"&hour="+form.hour.value+"&minute="+form.minute.value+"&date="+form.date.value+"&month="+form.month.value+"&year="+form.year.value+"&type="+form.qualify.value,
					function(data) {
						if(data.result) {
							var r = confirm('<%=messages.getText("fine.error.alreadyexists")%>');
							if(r) {
								form.submit();
							}
						}else {
							form.submit();
						}
					}
			);
		}
	}
</script>
	<!--  FineCreate.jsp -->
	<LINK REL="StyleSheet" HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>
	
	<html:form action="/Eventfine" >
	
		<body bgcolor="#FFFFFF">
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  				<tr class="guide1">
  					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.messages.addfine")%>' />
			  	</tr>
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
				<tr>
					<td width="292" valign="top">
                    	<table width="100%">
                    		<tr>
                     			<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.driverdata")%>' />
		        					<table width='100%' border="0" cellpadding="0" cellspacing="0">
		            					<tr class="linetwo">
	              							<td align="left" valign="middle"><strong><%=messages.getText("generic.event.driver.name")%></strong></td>
	    									<td align="left" valign="middle" >			
                       							<html:hidden property="driverid"/>
                       							<span class="linetwo"><%=driver.getFirstName()+" "+driver.getLastName()%></span>
											</td>
										</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            					<tr class="linetwo">
	              							<td align="left" valign="middle"><strong><%=messages.getText("generic.event.driver.birthdate")%></strong></td>
	                            			<td>
                       							<span class="linetwo"><%=Util.formatDate(driver.getBirthDate())%></span>
							    			</td>
	                         			</tr>
	                         			<tr  class="linetwo">
						        			<td align="right" colspan="2">
						        				<%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
				    	        			</td>
						      			</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            					<tr class="linetwo">
	              							<td align="left" valign="middle"><strong><%=messages.getText("generic.event.driver.startdate")%></strong></td>
	                            			<td>
                       							<span class="linetwo"><%=Util.formatDate(driver.getInclusionDate())%></span>
							    			</td>
	                         			</tr>
	                         			<tr  class="linetwo">
						        			<td align="right" colspan="2">
						        				<%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
				    	        			</td>
						      			</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            					<tr class="linetwo">
	              							<td align="left" valign="middle"><strong><%=messages.getText("generic.event.driver.licensecategory")%></strong></td>
	                            			<td>
                       							<span class="linetwo">
													<%=(Util.empty(driver.getLicenseCategory())) ? "----" : driver.getLicenseCategory()%>
                       							</span>
							    			</td>
	                         			</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		            					<tr class="linetwo">
	              							<td align="left" valign="middle"><strong><%=messages.getText("generic.event.driver.licenseexpiry")%></strong></td>
	                            			<td>
                       							<span class="linetwo"><%=Util.formatDate(driver.getLicenseExpiry())%></span>
							    			</td>
	                         			</tr>
	                         			<tr  class="linetwo">
						        			<td align="right" colspan="2">
						        				<%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
				    	        			</td>
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
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
                       					<tr>
                         					<td valign="top">
<%			String divgeo = "../util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=FineForm&consult=false";
			divgeo += "&city=both";
			//switch(corporationId) {
			//    case CorporationData.MONSANTO:
			//    	divgeo += "&city=city";
			//}	
%> 			             						<jsp:include page='<%=divgeo%>' />
                         					</td>
                       					</tr>
	                     			</table>					  
								</td>
							</tr>		
						</table>
					</td>
	                <td width="16">&nbsp;</td>
                	<td width="292" valign="top">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
                    		<jsp:include page='<%="../util/divorg.jsp?form=FineForm&consult=false" %>'/>
	                     </table>
    	             </td>
				</tr>
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
				<tr>
					<td colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.vehicledata")%>' />
<%					if(Util.empty(eventForm.getVehicleId())) { 
%>						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="270" class="linetwo">
									<table width="100%">
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.plate")%> <span class="required">*</span></th>
				         					<td align="right"><html:text property="vehicleplate" size="25" styleClass="input"/></td>
			         					</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.vehicle.vehicledata.trade")%><span class="required">*</span> </th>
				         					<td align="right">
                								<html:select property="vehicletrade" styleClass="input" style="width:155;">
                  									<html:options collection="trades" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
				         					</td>
										</tr>
									</table>
								</td>
								<td width="16"></td>
								<td width="270">
									<table width="100%">
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.model")%> <span class="required">*</span></th>
				         					<td align="right"><html:text property="vehiclemodel" size="25" styleClass="input"/></td>
										</tr>
		      							<tr  class="space">
		        							<th align="right">&nbsp;</th>
		        							<td align="right">&nbsp;</td>
		      							</tr>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.vehicle.vehicledata.year")%> <span class="required">*</span></th>
				         					<td align="right">
                								<html:select property="vehicleyear" styleClass="input" style="width:155;">
                  									<html:options collection="years" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
                							</td>
										</tr>				  
									</table>
								</td>
							</tr>
						</table>
<%					}else {
    					VehicleData vehicle = sys.getVehicleReportFacade().findVehicleById(new Integer(eventForm.getVehicleId()),sys.getUserSessionData());
%>						<html:hidden property="vehicleId" value='<%=request.getParameter("vehicle")%>' />
						<table>
							<tr>
								<td width="270" class="linetwo">
									<table>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.event.finedata.plate")+":"%> </th>
				         					<td align="right" class="linetwo"><%=vehicle.getMatricula()%></td>
			         					</tr>
		      							<tr  class="space">
		        							<th align="right">&nbsp;</th>
		        							<td align="right">&nbsp;</td>
		      							</tr>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.event.finedata.trade")+":"%> </th>
				         					<td align="right" class="linetwo"><%=messages.getText(vehicle.getTrade().getResourceBundleId())%></td>
										</tr>
									</table>
								</td>
								<td width="16"></td>
								<td width="270">
									<table>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.event.finedata.model")+":"%> </th>
				         					<td align="right" class="linetwo"><%=vehicle.getModel()%></td>
										</tr>
		      							<tr  class="space">
		        							<th align="right">&nbsp;</th>
		        							<td align="right">&nbsp;</td>
		      							</tr>
										<tr>
										   	<th align="left" class="linetwo"> <%=messages.getText("generic.event.finedata.year")+":"%> </th>
				         					<td align="right" class="linetwo"><%=vehicle.getYear()%></td>
										</tr>				  
									</table>
								</td>
							</tr>
						</table>    
<%					}
%>						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
	  			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
				<tr>
					<td colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.finedata")%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="270">
									<table width="100%">
										<tr>
			        						<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.date")%><span class="required">*</span></th>
			        						<td align="right" class="linetwo">
		            							<html:text property="date" style="width:20;" styleClass="input" styleId="dayIdFine" maxlength="2"/>/
		            							<html:text property="month" style="width:20;" styleClass="input" styleId="monthIdFine" maxlength="2"/>/
		            							<html:text property="year" style="width:40;" styleClass="input" styleId="yearIdFine" maxlength="4"/>
		            							<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_buttonFine" style="cursor: pointer; border: 1px solid red;"
     											onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>
			          						</td>
			          						<script type="text/javascript">
												Calendar.setup({
        											inputArea     :    "yearIdFine",
        											ifFormat       :    "%d/%m/%Y",
        											showsTime      :    true,
        											timeFormat     :    "24",
        											button         :    "calendar_buttonFine",
        											step           :    1,
        											onSelect       :    onSelectFine,
        											singleClick    :    true
    											});
										</script>
			          					</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
			        						<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.hour")%><span class="required">*</span></th>
			        						<td align="right" class="linetwo">
												<html:text property="hour" style="width:20;" styleClass="input" maxlength="2"/>&nbsp;:&nbsp;
		            							<html:text property="minute" style="width:20;" styleClass="input" maxlength="2"/>
												&nbsp;&nbsp;
<%			if(corporationId != CorporationData.MONSANTO) {
%>												<b><%=messages.getText("generic.messages.nodata")%></b><html:checkbox property="nodatahour" onchange="javascript:nodata(document.forms['FineForm']);"></html:checkbox>
<%			}else {
%>												<html:hidden property="nodatahour" value="off"/>			       
<%			}
%>					   						</td>
			          					</tr>
<%						if(sys.isCorporation(CorporationData.JJ)) {
%>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
			        						<th align="left" class="linetwo"> <%=messages.getText("generic.event.eventdata.expiry")%><span class="required">*</span></th>
			        						<td align="right" class="linetwo">
		            							<html:text property="expiryDate" style="width:20;" styleClass="input" styleId="expiryDayIdFine" maxlength="2"/>/
		            							<html:text property="expiryMonth" style="width:20;" styleClass="input" styleId="expiryMonthIdFine" maxlength="2"/>/
		            							<html:text property="expiryYear" style="width:40;" styleClass="input" styleId="expiryYearIdFine" maxlength="4"/>
		            							<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_expirybuttonFine" style="cursor: pointer; border: 1px solid red;"
     											onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>
			          						</td>
			          						<script type="text/javascript">
												Calendar.setup({
        											inputArea     :    "expiryYearIdFine",
        											ifFormat       :    "%d/%m/%Y",
        											showsTime      :    true,
        											timeFormat     :    "24",
        											button         :    "calendar_expirybuttonFine",
        											step           :    1,
        											onSelect       :    onSelectExpiry,
        											singleClick    :    true
    											});
										</script>
			          					</tr>
<%						}
%>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%						if(sys.isCorporation(CorporationData.JJ) && driver.getCountryName().equals(CountryData.MEXICO)) {
%>										<html:hidden property="fineType" value="datatype.finetype.nodata"/>
<%						} else {
%>
										<tr>
										   	<th align="left" class="linetwo"> 
										   		<%=messages.getText("generic.event.finedata.type")%><span class="required">*</span>
				         					</th>
			        						<td align="right" class="linetwo">
                								<html:select property="fineType" styleClass="input" style="width:155;">
                  									<html:options collection="types" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
			          						</td>
										</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%						}
%>
									</table>
								</td>
								<td width="16">&nbsp;</td>
								<td width="270" valign="top">
									<table width="100%">
										<tr tag="showSpeed"  >
										   	<th align="left" class="linetwo"> 
										   		<%=messages.getText("generic.event.fine.speed")%>
				         					</th>
			        						<td align="right" class="linetwo">
                								<html:text property="speed" styleClass="input" style="width:155;" />
			          						</td>
										</tr>
		      							<tr tag="showSpeed"   class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr tag="showSpeed"  >
										   	<th align="left" class="linetwo"> 
										   		<%=messages.getText("generic.event.fine.speedpermitted")%><span class="required">*</span>
				         					</th>
			        						<td align="right" class="linetwo">
				         						<html:text property="speedPermitted" size="10" styleClass="input"/>
			          						</td>
										</tr>
		      							<tr tag="showSpeed"   class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo"> 
										   		<%=messages.getText("generic.event.finedata.article")%>
				         					</th>
			        						<td align="right" class="linetwo">
                								<html:text property="article" styleClass="input" style="width:155;" />
			          						</td>
										</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo"> 
										   		<%=messages.getText("generic.event.finedata.points")%><span class="required">*</span>
				         					</th>
			        						<td align="right" class="linetwo">
				         						<html:text property="points" size="10" styleClass="input"/>
			          						</td>
										</tr>
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
										   	<th align="left" class="linetwo"> 
										   		<%=messages.getText("event.finedata.cost")%><span class="required">*</span>
				         					</th>
			        						<td align="right" class="linetwo">
				         						<html:text property="cost" size="10" styleClass="input"/>
			          						</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="3" width="100%">
									<table width="100%">
										<tr>
										   	<th align="left" class="linetwo"> 
										   		<%=messages.getText("generic.event.finedata.qualify")%><span class="required">*</span>
				         					</th>
			        						<td align="left" class="linetwo" colspan="3">
                								<html:select property="qualify" styleClass="input" onchange="selQualify(this)" >
                  									<html:options collection="qualifies" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
			          						</td>
										</tr>
									</table>
								</td>
							</tr>
				  			<tr>
								<jsp:include  page="../component/spaceline.jsp" />
				  			</tr>
							<tr>
								<td colspan="3" width="100%">
									<table width="100%">
										<tr>
											<!-- Notificaciòn de Infracción -->
											<th align="left" class="linetwo"> 
										   		<%=messages.getText("event.eventdata.infringementnotice")%>
				         					</th>
			        						<td align="right" class="linetwo">
				         						<html:text property="infringementNotice" size="20" styleClass="input"/>
			          						</td>
										</tr>
									</table>
								</td>
							</tr>
<% 							if (sys.isCorporation(new int[]{CorporationData.ALLERGAN, CorporationData.ECOLAB})){ 
									// ALLERGAN, tiene un campo de uso interno de la compania, llamado LOTE: Mes/Año
%>
							
							<tr>
								<td colspan="3" width="100%">
									<table width="100%">
										<tr>
											<th align="left" class="linetwo"><%=messages.getText("generic.event.eventdata.lote")%>&nbsp;<%=messages.getText("generic.event.eventdata.loteformat")%></th>
				        					<td align="right" class="linetwo">
			           							<html:text property="loteMonth" style="width:20;" styleClass="input" styleId="monthIdLote" maxlength="2"/>/
			           							<html:text property="loteYear" style="width:40;" styleClass="input" styleId="yearIdLote" maxlength="4"/>
			           							<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_buttonLote" style="cursor: pointer; border: 1px solid red;"
			  											onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>
			          						</td>
			          						<script type="text/javascript">
												Calendar.setup({
			     											inputArea     :    "yearIdLote",
			     											ifFormat       :    "%m/%Y",
			     											showsTime      :    true,
			     											timeFormat     :    "24",
			     											button         :    "calendar_buttonLote",
			     											step           :    1,
			     											onSelect       :    onSelectLote,
			     											singleClick    :    true
			 											});
											</script>
										
										</tr>
									</table>
								</td>
							</tr>
<%							}
%>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
	  			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
				<tr>
					<td width="100%" colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.finedata.observations")%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="270">
									<html:textarea property="observations" cols="70" />
								</td>
							</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
	  			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
	  			<tr>
	  				<td colspan="3">
						<jsp:include  page='../component/utilitybox4top.jsp' />
						<table width="100%">
							<tr>
				        		<td align="right">
				          			<div align="right">
				            			<input type="button" class="input" value='<%=messages.getText("generic.messages.save")%>' onclick="existEvent();"/>
						 				<html:cancel styleClass="input"><%=messages.getText("generic.messages.cancel")%></html:cancel >
				          			</div>
				          		</td>
				  			</tr>
						</table>
						<jsp:include  page="../component/utilitybox4bottom.jsp" />
	  				</td>
	  			</tr>
			</table>
		</body>
	</html:form>
<%	}
	
	}catch(Exception e){
		e.printStackTrace();
	}
%>	
</html:html>
