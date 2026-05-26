<%@page language="java"
    errorPage="../exception.jsp"
	import="business.*"
	import="business.administration.user.*"
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.datatype.tables.*"
	import="communication.datatype.*"
	import="communication.corporation.tables.*"
	import="communication.geografic.tables.*"
	import="communication.administration.user.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.security.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.driver.*"
	import="java.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>

<html:html>
<%! RequestDispatcher dispatcher; String context; String pathMsg;
	Text messages; String pathLanguage;
	SystemAccess sys; UserData user; UserReportFacade userReport;
%>

<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.CREATE_DRIVER);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {

		pathLanguage="/util/jsp/language.jsp";
	
		session.setAttribute("url","cepasystem/create.jsp");

		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);

		context=request.getContextPath();
		pathMsg="/util/jsp/message.jsp";
	
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);
		messages=sys.getText();

		if(session.getAttribute("target")==null){
			session.setAttribute("target",request.getParameter("target"));
		}
		DataTypeReportFacade dtReportFacade = sys.getDataTypeReportFacade();			
		Iterator iter = dtReportFacade.findAll(DataTypeConstants.SEX, sys.getUserSessionData()).iterator();
		Collection sexs = new LinkedList();
		while(iter.hasNext()) {
			String value = ((SexDataType)iter.next()).getResourceBundleId();
			sexs.add(new OptionItem(messages.getText(value),value));
		}
		session.setAttribute("sexs",sexs);
		
		if(session.getAttribute("DriverCreateForm")==null) {
		    session.setAttribute("DriverCreateForm",new DriverForm());
		}    
		
		Collection allCountries=sys.getCorporationReportFacade().findAllCountries(sys.getUserSessionData());
		Collection countries = new LinkedList();
		Iterator it = allCountries.iterator();
		countries.add(new OptionItem(messages.getText("generic.messages.nodata"),""));
		while(it.hasNext()) {
			CountryData item = (CountryData)it.next();
			countries.add(new OptionItem(messages.getText(item.getResourceKey()),item.getResourceKey()));
		}    
		session.setAttribute("countries",countries);
		
		session.setAttribute("vehicleType",new DataTypeUtil(sys).getVehicleType(true,false,true));

		Collection contractType = new LinkedList();
		Iterator iterC = dtReportFacade.findAll(DataTypeConstants.CONTRACT, sys.getUserSessionData()).iterator();
		contractType.add(new OptionItem(messages.getText("generic.messages.nodata"),""));
		while(iterC.hasNext()) {
			String value = ((ContractDataType)iterC.next()).getResourceBundleId();
			contractType.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
		}
		session.setAttribute("contracts",contractType);
%>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
<head/>

<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
</script>
<script>
function onSelect1(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayId1");
  		var input_month = document.getElementById("monthId1");
  		var input_year = document.getElementById("yearId1");
  		var date3 = new Date(time);
  		input_day.value = date3.print("%d");
  		input_month.value = date3.print("%m");
  		input_year.value= parseInt(date3.print("%Y"),10);
  }
function onSelect2(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayId2");
  		var input_month = document.getElementById("monthId2");
  		var input_year = document.getElementById("yearId2");
  		var date3 = new Date(time);
  		input_day.value = date3.print("%d");
  		input_month.value = date3.print("%m");
  		input_year.value= parseInt(date3.print("%Y"),10);
  }
function onSelect3(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayId3");
  		var input_month = document.getElementById("monthId3");
  		var input_year = document.getElementById("yearId3");
  		var date3 = new Date(time);
  		input_day.value = date3.print("%d");
  		input_month.value = date3.print("%m");
  		input_year.value= parseInt(date3.print("%Y"),10);
  }
function onSelect4(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayId4");
  		var input_month = document.getElementById("monthId4");
  		var input_year = document.getElementById("yearId4");
  		var date3 = new Date(time);
  		input_day.value = date3.print("%d");
  		input_month.value = date3.print("%m");
  		input_year.value= parseInt(date3.print("%Y"),10);
  }  
  
function licenseNotExpiry(){
	var licensenotexpiry = document.getElementById("licensenotexpiry");
	if (licensenotexpiry.checked == true){
		document.getElementById("dayId4").disabled = true;
		document.getElementById("monthId4").disabled = true;
		document.getElementById("yearId4").disabled = true;
	}else{
		document.getElementById("dayId4").disabled = false;
		document.getElementById("monthId4").disabled = false;
		document.getElementById("yearId4").disabled = false;	
	}
}
  
</script>

	<body bgcolor="#FFFFFF">
		<html:form action="/Drivercreate">
			<table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
  				<tr class="guide1">
  					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.messages.adddriver")%>' />
			  	</tr>
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
				<tr>
					<td width="49%" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.personaldata")%>' />
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
			      			<tr  class="linetwo">
			        			<th align="left"> <%=messages.getText("generic.driver.driverdata.firstname")%><span class="required">*</span></th>
			         			<td align="right"><html:text property="firstName" size="25" styleClass="input"/></td>
			      			</tr>
			      			<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      			<tr class="linetwo">
			        			<th align="left"><%=messages.getText("generic.driver.driverdata.lastname")%><span class="required">*</span></th>
						    	<td align="right"><html:text property="lastName" size="25" styleClass="input"/></td>
			      			</tr>
			      			<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      			<tr  class="linetwo">
			        			<th align="left"> <%=messages.getText("generic.driver.driverdata.birthdate")%><span class="required">*</span></th>
			        			<td align="right" valign="middle">
		            				<html:text property="birthDate" style="width:20;" styleClass="input" styleId="dayId1"/>&nbsp;/&nbsp;
		            				<html:text property="birthMonth" style="width:20;" styleClass="input" styleId="monthId1"/>&nbsp;/&nbsp;
		            				<html:text property="birthYear" style="width:40;" styleClass="input" styleId="yearId1"/>
		            				<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button1" style="cursor: pointer; border: 1px solid red;"
     										onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>
			          			</td>
			          			<script type="text/javascript">
									Calendar.setup({
        								inputArea     :    "yearId1",
        								ifFormat       :    "%d/%m/%Y",
        								showsTime      :    true,
        								timeFormat     :    "24",
        								button         :    "calendar_button1",
        								step           :    1,
        								onSelect       :    onSelect1,
        								singleClick    :    true
    								});
								</script>
			      			</tr>
			      			<tr  class="linetwo">
			        			<td align="right" colspan="2">
			        				<%="( "+messages.getText("generic.supportmaterial.reports.format")+": dd/mm/aaaa )"%>
		            			</td>
			      			</tr>
			      			<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr  class="linetwo">
					        	<th align="left" > <%=messages.getText("generic.driver.driverdata.sex")%><span class="required">*</span></th>
					       		<td align="right">
						    		<html:select property="sex" styleClass="input" style="width:120;" >
										<html:options collection="sexs" property="value" labelProperty="label"/>
									</html:select>   
								</td>
					    	</tr>
			      			<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      			<tr  class="linetwo">
			        			<th align="left"> <%=messages.getText("generic.driver.driverdata.nationality")%></th>
			        			<td align="right">
			 	     				<html:select property="nationality" styleClass="input" style="width:120;" >
      									<html:options collection="countries" property="value" labelProperty="label" styleClass="input"/>
	  								</html:select>   
	  							</td>	
			      			</tr>
			      			<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      			<tr  class="linetwo">
			        			<th align="left"> <%=messages.getText("generic.driver.driverdata.city")%></th>
			        			<td align="right"><html:text property="city" size="25" styleClass="input"/></td>
			      			</tr>
			      			<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      			<tr  class="linetwo">
			        			<th align="left"> <%=messages.getText("generic.driver.driverdata.personaladdress")%></th>
			        			<td align="right"><html:text property="personalAddress" size="25" styleClass="input"/></td>
			      			</tr>
			      			<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>								
					<td width="2%"></td>
					<td width="49%" valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
                     		<tr>
                         		<td valign="top"> <jsp:include page='<%="/util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=DriverCreateForm&consult=false"%>' /></td>
                       		</tr>
	                     </table>					  
					</td>
				</tr>
				<tr><jsp:include  page="../component/spaceline.jsp" /></tr>
				<tr>
					<td valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.workinfo")%>' />
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
	      					<tr class="linetwo">
	        					<th align="left" > <%=messages.getText("generic.driver.driverdata.corporationid")%></th>
	        					<td align="right"><html:text property="corporationId" size="25" styleClass="input"/></td>
	      					</tr>
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr  class="linetwo">
        						<th align="left"> <%=messages.getText("generic.driver.driverdata.startdate")%></th>
        						<td align="right" valign="middle">
           							<html:text property="startDate" style="width:20;" styleClass="input" styleId="dayId2" />&nbsp;/&nbsp;
          							<html:text property="startMonth" style="width:20;" styleClass="input" styleId="monthId2"/>&nbsp;/&nbsp;
           							<html:text property="startYear" style="width:40;" styleClass="input" styleId="yearId2"/>
           							<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button2" style="cursor: pointer; border: 1px solid red;"
     								onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>
			          			</td>
			          			<script type="text/javascript">
									Calendar.setup({
        									inputArea     :    "yearId2",
        									ifFormat       :    "%d/%m/%Y",
        									showsTime      :    true,
        									timeFormat     :    "24",
        									button         :    "calendar_button2",
        									step           :    1,
        									onSelect       :    onSelect2,
        									singleClick    :    true
    									});
								</script>
      						</tr>
      						<tr  class="linetwo">
			        			<td align="right" colspan="2">
			        				<%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
		            			</td>
			      			</tr>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
	      					<tr class="linetwo">
	        					<th align="left" > <%=messages.getText("generic.driver.driverdata.email")%></th>
	        					<td align="right"><html:text property="email" size="25" styleClass="input"/></td>
	      					</tr>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
	      					<tr class="linetwo">
	        					<th align="left" > <%=messages.getText("generic.driver.driverdata.phonenumber")%></th>
	        					<td align="right"><html:text property="phoneNumber" size="25" styleClass="input"/></td>
	      					</tr>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr class="linetwo">
        						<th align="left"> <%=messages.getText("generic.driver.driverdata.officeaddress")%></th>
        						<td align="right"><html:text property="officeAddress" size="25" styleClass="input"/></td>
      						</tr>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr class="linetwo">
        						<th align="left"> <%=messages.getText("generic.driver.driverdata.drives")%></th>
        						<td align="right">
        							<html:radio property="drives" value="true"><%=messages.getText("generic.messages.yes")%></html:radio>
        							<html:radio property="drives" value="false"><%=messages.getText("generic.messages.no")%></html:radio>
        						</td>
      						</tr>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr class="linetwo">
        						<th align="left"> <%=messages.getText("generic.driver.driverdata.employee")%></th>
        						<td align="right">
        							<html:radio property="employee" value="true"><%=messages.getText("generic.messages.yes")%></html:radio>
        							<html:radio property="employee" value="false"><%=messages.getText("generic.messages.no")%></html:radio>
        						</td>
      						</tr>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr class="linetwo">
        						<th align="left"> <%=messages.getText("generic.driver.driverdata.contact")%></th>
        						<td align="right">        						
			      					<html:select property="contract" styleClass="input" style="width:120;" >
										<html:options collection="contracts" property="value" labelProperty="label"/>
				  					</html:select>   
				  				</td>
      						</tr>
<%		if(sys.getUserSessionData().isForest()) {
%>							<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr class="linetwo">
        						<th align="left"> <%=messages.getText("generic.driver.driverdata.bpsregistry")%></th>
        						<td align="right"><html:text property="bpsregistry" size="25" styleClass="input"/></td>
      						</tr>
<%		}
%>     					</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
    				</td>
    				<td ></td>		
					<td valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.licenseinfo")%>' />
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
	      					<tr class="linetwo">
	        					<th align="left" > <%=messages.getText("generic.driver.driverdata.licensenumber")%></th>
	        					<td align="right"><html:text property="licenseNumber" style="width:130;" styleClass="input"/></td>
	      					</tr>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr  class="linetwo">
								<th align="left" > <%=messages.getText("generic.driver.driverdata.licenseget")%></th>
      							<td align="right" valign="middle">
								    <html:text property="licenseGetDate" style="width:20;" styleClass="input" styleId="dayId3"/>&nbsp;/&nbsp;
								    <html:text property="licenseGetMonth" style="width:20;" styleClass="input" styleId="monthId3"/>&nbsp;/&nbsp;
								    <html:text property="licenseGetYear" style="width:35;" styleClass="input" styleId="yearId3"/>
          							<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button3" style="cursor: pointer; border: 1px solid red;"
     								onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>
			          			</td>
			          			<script type="text/javascript">
									Calendar.setup({
        									inputArea     :    "yearId3",
        									ifFormat       :    "%d/%m/%Y",
        									showsTime      :    true,
        									timeFormat     :    "24",
        									button         :    "calendar_button3",
        									step           :    1,
        									onSelect       :    onSelect3,
        									singleClick    :    true
    									});
								</script>
      						</tr>
      						<tr  class="linetwo">
			        			<td align="right" colspan="2">
			        				<%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
		            			</td>
			      			</tr>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
    						<tr  class="linetwo">
   								<th align="left" > <%=messages.getText("generic.driver.driverdata.licenseexpiry")%></th>
        						<td align="right" valign="middle">
	            					<html:text property="licenseExpiryDate" style="width:20;" styleClass="input" styleId="dayId4"/>&nbsp;/&nbsp;
								    <html:text property="licenseExpiryMonth" style="width:20;" styleClass="input" styleId="monthId4"/>&nbsp;/&nbsp;
								    <html:text property="licenseExpiryYear" style="width:35;" styleClass="input" styleId="yearId4"/>
          							<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button4" style="cursor: pointer; border: 1px solid red;"
     								onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>
			          			</td>
			          			<script type="text/javascript">
									Calendar.setup({
        									inputArea     :    "yearId4",
        									ifFormat       :    "%d/%m/%Y",
        									showsTime      :    true,
        									timeFormat     :    "24",
        									button         :    "calendar_button4",
        									step           :    1,
        									onSelect       :    onSelect4,
        									singleClick    :    true
    									});
								</script>
      						</tr>
      						<tr  class="linetwo">
   								<th align="left" > <%=messages.getText("generic.driver.driverdata.licensenotexpiry")%></th>
        						<td align="right" valign="middle">
	            					<html:checkbox property="licenseNotExpiry" styleClass="input" styleId="licensenotexpiry" onclick="javascript:licenseNotExpiry();"/>								    
			          			</td>
      						</tr>
							<tr  class="linetwo">
			        			<td align="right" colspan="2">
			        				<%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
		            			</td>
			      			</tr>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr class="linetwo">
      							<th align="left"> <%=messages.getText("generic.driver.driverdata.licensecategory")%></th>
      							<td align="right"><html:text property="licenseCategory" style="width:130;" styleClass="input"/></td>
							</tr>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr  class="linetwo">
      							<th align="left"> <%=messages.getText("generic.driver.driverdata.vehicleallowed")%></th>
      							<td align="right">
 	     							<html:select property="vehicleAllowed" styleClass="input" style="width:130;" >
      									<html:options collection="vehicleType" property="value" labelProperty="label" styleClass="input"/>
	  								</html:select>   
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
					<td width="100%" colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.comment")+"*"%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="3"> 
									<html:textarea property="comment" cols="90" />
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
  				  	<td height="18" colspan="3" valign="top">
          				<div align="right">
							<html:submit styleClass="input"><%=messages.getText("generic.messages.save")%></html:submit>
					 		<html:cancel styleClass="input"><%=messages.getText("generic.messages.cancel")%></html:cancel >
				  		</div>          
				  	</td>
			  	</tr>
		  	</table>
		</html:form>
	</body>
<%	}	%>
</html:html>
