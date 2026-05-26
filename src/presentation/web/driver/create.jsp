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
	int corporationId = sys.getCorporationId();
	String check = Util.checkPermission(sys,SecurityConstants.CREATE_DRIVER);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {

		pathLanguage="/util/jsp/language.jsp";
	
		// session.setAttribute("url","driver/create.jsp");

		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);

		context=request.getContextPath();
		pathMsg="/util/jsp/message.jsp";
	
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);
		messages=sys.getText();

		DataTypeReportFacade dtReportFacade = sys.getDataTypeReportFacade();			
		Iterator iter = dtReportFacade.findAll(DataTypeConstants.SEX, sys.getUserSessionData()).iterator();
		Collection sexs = new LinkedList();
		while(iter.hasNext()) {
			String value = ((SexDataType)iter.next()).getResourceBundleId();
			sexs.add(new OptionItem(messages.getText(value),value));
		}
		request.setAttribute("sexs",sexs);
		
		// licenseCoverageOptions, solo PEPSICO:
		if (sys.getCorporationId() == CorporationData.PEPSICO){
			LinkedList licenseCoverageOptions = new LinkedList();
			licenseCoverageOptions.add(new OptionItem(messages.getText("generic.messages.nodata"),""));
			licenseCoverageOptions.add(new OptionItem(messages.getText("driver.license.coverage.federal"),"driver.license.coverage.federal"));
			licenseCoverageOptions.add(new OptionItem(messages.getText("driver.license.coverage.local"),"driver.license.coverage.local"));
			Collections.sort(licenseCoverageOptions);
			request.setAttribute("licenseCoverageOptions",licenseCoverageOptions);
		}
		
		LinkedList educationLevelOptions = new LinkedList();
		Collection c = dtReportFacade.findAll(DataTypeConstants.EDUCATION_LEVEL, sys.getUserSessionData());
		if (c != null){
			iter = c.iterator();		
			while(iter.hasNext()) {
				String value = ((EducationLevelDataType)iter.next()).getResourceBundleId();
				educationLevelOptions.add(new OptionItem(messages.getText(value),value));
			}
		}
		Collections.sort(educationLevelOptions);
		request.setAttribute("educationLevelOptions",educationLevelOptions);

		LinkedList maritalStatusOptions = new LinkedList();
		c = dtReportFacade.findAll(DataTypeConstants.MARITAL_STATUS,sys.getUserSessionData());
		if (c != null){
			iter = c.iterator();
			while(iter.hasNext()) {
				String value = ((MaritalStatusDataType)iter.next()).getResourceBundleId();
				maritalStatusOptions.add(new OptionItem(messages.getText(value),value));
			}
		}
		Collections.sort(maritalStatusOptions);
		request.setAttribute("maritalStatusOptions",maritalStatusOptions);
		
		if (sys.getCorporationId() == CorporationData.PEPSICO){
			LinkedList puestoPepsicoOptions = new LinkedList();
			Collection c3 = dtReportFacade.findAll(DataTypeConstants.PUESTO_PEPSICO, sys.getUserSessionData());
			if (c3 != null){
				iter = c3.iterator();			
				puestoPepsicoOptions.add(new OptionItem(messages.getText("generic.messages.nodata"),""));
				while(iter != null && iter.hasNext()) {
					String value = ((PuestoPepsicoDataType)iter.next()).getResourceBundleId();
					if (value != null){
						puestoPepsicoOptions.add(new OptionItem(messages.getText(value),value));
					}
				}					
			}
			Collections.sort(puestoPepsicoOptions);
			request.setAttribute("puestoPepsicoOptions",puestoPepsicoOptions);
		}
		
		
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
		Collections.sort((List)countries);
		request.setAttribute("countries",countries);
		request.setAttribute("vehicleType",new DataTypeUtil(sys).getExtendedVehicleType(true,false,true,sys.isCorporation(CorporationData.MONSANTO)));

		Collection contractType = new LinkedList();
		Iterator iterC = dtReportFacade.findAll(DataTypeConstants.CONTRACT, sys.getUserSessionData()).iterator();
		contractType.add(new OptionItem(messages.getText("generic.messages.nodata"),""));
		if(corporationId == CorporationData.ALLERGAN) {
			contractType.add(new OptionItem(messages.getText("datatype.contract.permanent"),"datatype.contract.permanent"));				
			contractType.add(new OptionItem(messages.getText("datatype.contract.third.drives"),"datatype.contract.third.drives"));
			contractType.add(new OptionItem(messages.getText("datatype.contract.zafral"),"datatype.contract.zafral"));
		}else {
			while(iterC.hasNext()) {
				String value = ((ContractDataType)iterC.next()).getResourceBundleId();
				contractType.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
			}
		}
		request.setAttribute("contracts",contractType);
		
		Collection stateOrRegionColl = new LinkedList();
		Iterator iterC2 = dtReportFacade.findAll(DataTypeConstants.BRASIL_STATE,sys.getUserSessionData()).iterator();
		stateOrRegionColl.add(new OptionItem(messages.getText("generic.messages.nodata"),""));
		while(iterC2.hasNext()) {
			String value = ((BrasilStateDataType)iterC2.next()).getResourceBundleId();
			stateOrRegionColl.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
		}
		request.setAttribute("stateOrRegionColl",stateOrRegionColl);
		
%>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<!-- jsp que usada para parsearString mediante el styleClass -->
<jsp:include page= "../util/parseString.jsp"/>

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
function onSelect5(calendar, date) {
	var date2 = calendar.date;
	var time = date2.getTime();
		var input_day = document.getElementById("dayId5");
		var input_month = document.getElementById("monthId5");
		var input_year = document.getElementById("yearId5");
		var date3 = new Date(time);
		input_day.value = date3.print("%d");
		input_month.value = date3.print("%m");
		input_year.value= parseInt(date3.print("%Y"),10);
}  

function onSelect6(calendar, date) {
	var date2 = calendar.date;
	var time = date2.getTime();
		var input_day = document.getElementById("dayId6");
		var input_month = document.getElementById("monthId6");
		var input_year = document.getElementById("yearId6");
		var date3 = new Date(time);
		input_day.value = date3.print("%d");
		input_month.value = date3.print("%m");
		input_year.value= parseInt(date3.print("%Y"),10);
}  
  
function nolicense(form){
	var val = form.elements['licenseNotExpiry'].value == 0;
	form.elements['licenseExpiryDate'].disabled = val;
	form.elements['licenseExpiryMonth'].disabled = val;
	form.elements['licenseExpiryYear'].disabled = val;
	if(val) {
		form.elements['licenseExpiryDate'].value = '';
		form.elements['licenseExpiryMonth'].value = '';
		form.elements['licenseExpiryYear'].value = '';
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
                    	<table width="100%" cellpadding="0" cellspacing="0" border="0">
                    		<tr>
                     			<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.personaldata")%>' />
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" >
			      						<tr  class="linetwo">
			        						<th align="left"> <%=messages.getText("generic.driver.driverdata.firstname")%><span class="required">*</span></th>
			         						<td align="right"><html:text property="firstName" size="25" styleClass="input txtUpper"/></td>
			      						</tr>
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr class="linetwo">
			        						<th align="left"><%=messages.getText("generic.driver.driverdata.lastname")%><span class="required">*</span></th>
						        			<td align="right"><html:text property="lastName" size="25" styleClass="input txtUpper"/></td>
			      						</tr>
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr  class="linetwo">
			        						<th align="left"> <%=messages.getText("generic.driver.driverdata.birthdate")%><span class="required">*</span></th>
			        						<td align="right" valign="middle">
		            							<html:text property="birthDate" style="width:20;" styleClass="input" styleId="dayId1" maxlength="2" />&nbsp;/&nbsp;
		            							<html:text property="birthMonth" style="width:20;" styleClass="input" styleId="monthId1" maxlength="2"/>&nbsp;/&nbsp;
		            							<html:text property="birthYear" style="width:40;" styleClass="input" styleId="yearId1" maxlength="4"/>
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
					    				
<%								if (sys.getCorporationId() == CorporationData.ALLERGAN ||
										sys.getCorporationId() == CorporationData.ASTRAZENECA){ %>
										<!-- RG -->
										<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr  class="linetwo">
											<th align="left"> <%=messages.getText("generic.driver.driverdata.federativeentity")%></th>
											<td align="right"><html:text property="federativeEntity" size="25" styleClass="input"/></td>
										</tr>
										<!-- CPF -->
										<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr  class="linetwo">
											<th align="left"> <%=messages.getText("generic.driver.driverdata.licensecoverage")%></th>
											<td align="right">
												<html:text property="licenseCoverage" size="25" styleClass="input"/>
											</td>
										</tr>
<%								} 
								if (sys.getCorporationId() != CorporationData.PEPSICO){ %>
										<!-- Nationality -->					    				
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr  class="linetwo">
			        						<th align="left"> <%=messages.getText("generic.driver.driverdata.nationality")%></th>
			        						<td align="right">
			 	     							<html:select property="nationality" styleClass="input" style="width:120;" >
      												<html:options collection="countries" property="value" labelProperty="label" styleClass="input"/>
	  											</html:select>   
	  										</td>	
			      						</tr>
<%								} %>
			      						
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr  class="linetwo">
			        						<th align="left"> <%=messages.getText("generic.driver.driverdata.city")%></th>
			        						<td align="right"><html:text property="city" size="25" styleClass="input"/></td>
			      						</tr>
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						
			      						 
<%			if (sys.getCorporationId() == CorporationData.BAYER){  
%>
										<tr  class="linetwo">
											<th align="left"><%=messages.getText("generic.driver.driverdata.manager")%></th>
									        <td align="right"><html:text property="manager" size="25" styleClass="input"/></td>
										</tr>
					                	<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					                	<tr  class="linetwo">
					                      	<th align="left"> <%=messages.getText("generic.driver.driverdata.stateOrRegion")%></th>
                    						<td align="right">                      		
												<html:select property="stateOrRegion" styleClass="input" style="width:120;">
								    				<html:options collection="stateOrRegionColl" property="value" labelProperty="label" styleClass="input"/>
								     			</html:select>
								     		</td>
								     	</tr>
					                	<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<% 			} 
 			if(sys.isCorporation(CorporationData.DPA)){
%>
										<tr  class="linetwo">
											<th align="left" > <%=messages.getText("generic.driver.driverdata.state")%></th>
									        <td align="right"><html:text property="stateOrRegion" size="25" styleClass="input"/></td>
										</tr>
										<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%			}	
%>			      						<tr  class="linetwo">
			        						<th align="left"> <%=messages.getText("generic.driver.driverdata.personaladdress")%></th>
			        						<td align="right"><html:text property="personalAddress" size="25" styleClass="input"/></td>
			      						</tr>
<% 			if(sys.isCorporation(CorporationData.JJ)){%>
										<html:hidden property="idDallas" value='no'/>
<%			}
			if(sys.getCorporationId() == CorporationData.CIMSA) {
%>     								
										<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      									<tr class="linetwo">
	        								<th align="left" > <%=messages.getText("generic.driver.driverdata.phonenumber")%></th>
				        					<td align="right"><html:text property="phoneNumber" size="25" styleClass="input"/></td>
      									</tr>
      									
      									<!--  Marital Status  -->
      									<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr  class="linetwo">
					                		<th align="left" > <%=messages.getText("generic.driver.driverdata.maritalstatus")%></th>
					                		<td align="right">
					                		<html:select property="maritalStatus" styleClass="input" style="width:120;" >
					                          <html:options collection="maritalStatusOptions" property="value" labelProperty="label" styleClass="input"/>
					                        </html:select>
					              		</tr>
											
										<!--  Education Level -->
										<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr  class="linetwo">
					                		<th align="left" > <%=messages.getText("generic.driver.driverdata.educationlevel")%></th>
					                		<td align="right">
					                		<html:select property="educationLevel" styleClass="input" style="width:120;" >
					                          <html:options collection="educationLevelOptions" property="value" labelProperty="label" styleClass="input"/>
					                        </html:select>
					                		
					              		</tr>
      									
      									
      									
      									
<% 			}
%>			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
									</table>
									<jsp:include  page="../component/utilityboxbottom.jsp" />
								</td>
							</tr>
							<tr>
								<jsp:include  page="../component/spaceline.jsp" />
							</tr>
<%			if(corporationId == CorporationData.ALLERGAN) { 
%>							<tr>
								<td width="30%" valign="top">
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.hierarchicallevel")%>' />
									<table width="100%" height="100%" cellpadding="0" cellspacing="0">
										<tr  class="linetwo">
											<th align="left"> <%=messages.getText("generic.driver.driverdata.iddallas")%></th>
											<td align="right"><html:text property="idDallas" size="25" styleClass="input"/></td>
										</tr>
										<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		    							<tr class="linetwo">
		        							<th align="left"> <%=messages.getText("generic.driver.driverdata.bpsregistry")%></th>
			        						<td align="right"><html:text property="bpsregistry" size="25" styleClass="input"/></td>
		      							</tr>
										<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		    							<tr class="linetwo">
		        							<th align="left"> <%=messages.getText("generic.driver.driverdata.charge")%></th>
			        						<td align="right"><html:text property="authorizateWho" size="25" styleClass="input"/></td>
		      							</tr>
									</table>	
					   				<jsp:include page="../component/utilityboxbottom.jsp"/>	
					   			</td>
				   			</tr>
<%			}	
%>
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
                       					<tr>
                         					<td valign="top"> <jsp:include page='<%="/util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=DriverCreateForm&consult=false"%>' /></td>
                       					</tr>
	                     			</table>					  
								</td>
							</tr>		
						</table>
					</td>
	                <td width="2%">&nbsp;</td>
                	<td width="49%" valign="top">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
                    		<jsp:include page='<%="/util/divorg.jsp?form=DriverCreateForm&consult=false" %>'/>
<%			if(corporationId == CorporationData.ALLERGAN) { 
%>                    		<tr><jsp:include  page='<%="../component/spaceline.jsp" %>'/></tr>
							<tr>
								<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.complement")%>' />
									<table width="100%" height="100%" cellpadding="0" cellspacing="0">
										<tr  class="linetwo">
											<th align="left"> <%=messages.getText("generic.driver.driverdata.officeaddress")%></th>
			        						<td align="right"><html:text property="officeAddress" size="25" styleClass="input"/></td>
										</tr>
										<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr class="linetwo">
							        		<th align="left" > <%=messages.getText("generic.driver.driverdata.superviser")%></th>
				        					<td align="right"><html:text property="superviser" size="40" styleClass="input"/></td>
					   					</tr>
						      			<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					   					<tr class="linetwo">
					       					<th align="left" > <%=messages.getText("generic.driver.driverdata.manager")%></th>
				        					<td align="right"><html:text property="manager" size="40" styleClass="input"/></td>
						      			</tr>
									</table>	
					   				<jsp:include page="../component/utilityboxbottom.jsp"/>	
								</td>
							</tr>		
<%			} else if(sys.isCorporation(CorporationData.DOW)) {
%>                    		<tr><jsp:include  page='<%="../component/spaceline.jsp" %>'/></tr>
							<tr>
								<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.complement")%>' />
									<table width="100%" height="100%" cellpadding="0" cellspacing="0">
										<tr  class="linetwo">
											<th align="left" > <%=messages.getText("generic.driver.driverdata.superviserid")%></th>
											<td align="right"><html:text property="idDallas" size="40" styleClass="input"/></td>
										</tr>
										<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr  class="linetwo">
											<th align="left" > <%=messages.getText("generic.driver.driverdata.supervisername")%></th>
											<td align="right"><html:text property="bpsregistry" size="40" styleClass="input"/></td>
										</tr>
										<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr class="linetwo">
							        		<th align="left" > <%=messages.getText("generic.driver.driverdata.focalpointid")%></th>
				        					<td align="right"><html:text property="superviser" size="40" styleClass="input"/></td>
					   					</tr>
						      			<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					   					<tr class="linetwo">
					       					<th align="left" > <%=messages.getText("generic.driver.driverdata.focalpointname")%></th>
				        					<td align="right"><html:text property="manager" size="40" styleClass="input"/></td>
						      			</tr>
									</table>	
					   				<jsp:include page="../component/utilityboxbottom.jsp"/>	
								</td>
							</tr>		
<%			} 
%>	                     </table>
    	             </td>
				</tr>
				<tr><jsp:include  page='<%="../component/spaceline.jsp" %>'/></tr>
				<tr>
					<td width="50%" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.workinfo")%>' />
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
<%				if(corporationId == CorporationData.CIMSA) {
%>							<html:hidden property="points" value="25"/>
	      					<tr class="linetwo">
	        					<th align="left" > <%=messages.getText("generic.driver.driverdata.points")%></th>
	        					<td align="right">25</td>
	      					</tr>
			      			
<%				}
	 			if(sys.isCorporation(CorporationData.DPA)){
%>
							<tr  class="linetwo">
								<th align="left" > <%=messages.getText("generic.driver.driverdata.cpf")%></th>
								<td align="right"><html:text property="idDallas" size="40" styleClass="input"/></td>
							</tr>
							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr  class="linetwo">
								<th align="left" > <%=messages.getText("generic.driver.driverdata.rg")%></th>
								<td align="right"><html:text property="bpsregistry" size="40" styleClass="input"/></td>
							</tr>
							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<% 				}	
				if(sys.isCorporation(new int[]{CorporationData.MONSANTO,CorporationData.BAYER_MX})) {
%>	      					<tr class="linetwo">
	        					<th align="left" > <%=messages.getText("generic.driver.driverdata.superviser")%></th>
	        					<td align="right"><html:text property="superviser" size="40" styleClass="input"/></td>
	     					</tr>
			      			<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
	      					<tr class="linetwo">
	        					<th align="left" > <%=messages.getText("generic.driver.driverdata.manager")%></th>
	        					<td align="right"><html:text property="manager" size="40" styleClass="input"/></td>
	      					</tr>
			      			
<%				}
%>	      				
							<!--  Corporation Id, o numero de empleado -->
							<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>	
							<tr class="linetwo">
	        					<th align="left" > <%=messages.getText("generic.driver.driverdata.corporationid")%></th>
	        					<td align="right"><html:text property="corporationId" size="25" styleClass="input"/></td>
	      					</tr>
	      					
<%				if(sys.isCorporation(CorporationData.JJ)) {
					String[] structure = {"ADM SJC","ADM SP","Engineering","Facilities","Finance","General Administration","General Management","GFO","GFS","GSI","Human Resources","Legal","Logistica","Marketing","MKT Baby, Beauty, J&J Adultos","MKT Service","Operations","Oral Care, OTC, SANPRO","Profissional","Public Relations","R&D","Real State","Regional Marketing","Regional Customer Development","Regional Finance","Regional Marketing","Regulatory Affairs","Strategic & Intelligence","Strategic Planning","Total Marketing","Total Sales","Trade","UN NENO","UN RICO","UN SPKacc","UN SUL"};
%>			      			<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
	      					<tr class="linetwo">
	        					<th align="left" > <%=messages.getText("generic.driver.driverdata.superviser")%></th>
	        					<td align="right"><html:text property="superviser" size="25" styleClass="input"/></td>
	     					</tr>
			      			<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
	      					<tr class="linetwo">
	        					<th align="left" > <%=messages.getText("generic.driver.driverdata.manager")%></th>
	        					<td align="right"><html:text property="manager" size="25" styleClass="input"/></td>
	      					</tr>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr class="linetwo">
        						<th align="left"> <%=messages.getText("generic.driver.driverdata.officeaddress")%></th>
        						<td align="right"><html:text property="officeAddress" size="25" styleClass="input"/></td>
      						</tr>
			      			<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr class="linetwo">
			   					<th align="left" > <%=messages.getText("generic.driver.driverdata.bpsregistry")%></th>
			        			<td align="right">
				        			<html:select property="bpsregistry" styleClass="input">
				        				<html:option value=''><%=messages.getText("generic.messages.select")%></html:option>
<%					for(int i = 0; i < structure.length; i++) {	
%>				        				<html:option value='<%=structure[i]%>'><%=structure[i]%></html:option>
<%					}	
%>				        			</html:select>
				        		</td>
				      		</tr>
			      			
<%				}
			if (sys.getCorporationId() == CorporationData.PEPSICO){   
%>
					<!--  Pespisco: Puesto del condctor dentro de la empresa-->
					<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left" > <%=messages.getText("generic.driver.driverdata.puestopepsico")%></th>
						<td align="right">
							<html:select property="puestoPepsico" styleClass="input" style="width:120;" >
                          		<html:options collection="puestoPepsicoOptions" property="value" labelProperty="label" styleClass="input"/>
                        	</html:select>
						</td>
					</tr>

<%    		} %>
	      					
	      					
			      			<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr  class="linetwo">
        						<th align="left"> <%=messages.getText("generic.driver.driverdata.startdate")%></th>
        						<td align="right" valign="middle">
           							<html:text property="startDate" style="width:20;" styleClass="input" styleId="dayId2" maxlength="2"/>&nbsp;/&nbsp;
          							<html:text property="startMonth" style="width:20;" styleClass="input" styleId="monthId2" maxlength="2"/>&nbsp;/&nbsp;
           							<html:text property="startYear" style="width:40;" styleClass="input" styleId="yearId2" maxlength="4"/>
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
<%			if(sys.isCorporation(CorporationData.DOW)) {
%>      					<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr class="linetwo">
								<th align="left" > <%=messages.getText("generic.driver.driverdata.email")%></th>
								<td align="right"><html:text property="email" size="25" styleClass="input"/></td>
							</tr>
							<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr class="linetwo">
								<th align="left" > <%=messages.getText("generic.driver.driverdata.phonenumber")%></th>
								<td align="right"><html:text property="phoneNumber" size="25" styleClass="input"/></td>
							</tr>
		
<%			
			}else {
				if(sys.getCorporationId() != CorporationData.CIMSA) {
%>      					<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
	      					<tr class="linetwo">
	        					<th align="left" > <%=messages.getText("generic.driver.driverdata.email")%></th>
	        					<td align="right"><html:text property="email" size="25" styleClass="input"/></td>
	      					</tr>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
	      					<tr class="linetwo">
	        					<th align="left" > <%=messages.getText("generic.driver.driverdata.phonenumber")%></th>
	        					<td align="right"><html:text property="phoneNumber" size="25" styleClass="input"/></td>
	      					</tr>
	      					
<%			
					if(sys.isCorporation(CorporationData.DPA)){
%>
							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr  class="linetwo">
								<th align="left" > <%=messages.getText("generic.driver.driverdata.mobile")%></th>
       							<td align="right"><html:text property="superviser" size="25" styleClass="input"/></td>
							</tr>
<% 					}	
					if(sys.isCorporation(new int[]{CorporationData.BAYER,CorporationData.SYNGENTA,CorporationData.SYNGENTA_BR})){
							// Bayer releva los datos: coordinador y su email (officeaddress)
%>						   	<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>	
							<tr class="linetwo">
				        		<th align="left" > <%=messages.getText("generic.driver.driverdata.iddallas")%></th>
								<td align="right"><html:text property="idDallas" size="25" styleClass="input"/></td>
				     		</tr>
							<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr class="linetwo">
				        		<th align="left" > <%=messages.getText("generic.driver.driverdata.superviser")%></th>
				        		<td align="right"><html:text property="superviser" size="25" styleClass="input"/></td>
				     		</tr>
<%						if(sys.isCorporation(CorporationData.SYNGENTA_BR)){
%>						   	<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>	
							<tr class="linetwo">
								<th align="left" > <%=messages.getText("generic.driver.driverdata.manager")%></th>
								<td align="right"><html:text property="manager" size="25" styleClass="input"/></td>
							</tr>
<%						}
					} 
					if (!sys.isCorporation(new int[]{CorporationData.JJ,CorporationData.PEPSICO,CorporationData.ALLERGAN})){
%>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr class="linetwo">
        						<th align="left"> <%=messages.getText("generic.driver.driverdata.officeaddress")%></th>
        						<td align="right"><html:text property="officeAddress" size="25" styleClass="input"/></td>
      						</tr>
<% 					}
				}
				if(!sys.isCorporation(CorporationData.SYNGENTA)) {
					if(!sys.isCorporation(new int[] {CorporationData.PEPSICO,CorporationData.ALLERGAN})) {
%>	  						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr class="linetwo">
        						<th align="left"> <%=messages.getText("generic.driver.driverdata.drives")%></th>
        						<td align="right">
        							<html:radio property="drives" value="true"><%=messages.getText("generic.messages.yes")%></html:radio>
        							<html:radio property="drives" value="false"><%=messages.getText("generic.messages.no")%></html:radio>
        						</td>
      						</tr>
<%	 				}
%>     						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr class="linetwo">
        						<th align="left"> <%=messages.getText("generic.driver.driverdata.employee")%></th>
        						<td align="right">
        							<html:radio property="employee" value="true"><%=(corporationId == CorporationData.ALLERGAN) ? messages.getText("driver.data.active") : messages.getText("generic.messages.yes")%></html:radio>
        							<html:radio property="employee" value="false"><%=(corporationId == CorporationData.ALLERGAN) ? messages.getText("driver.data.inactive") : messages.getText("generic.messages.no")%></html:radio>
        						</td>
      						</tr>
<%					if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
%>							<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr class="linetwo">
        						<th align="left"> <%=messages.getText("generic.driver.driverdata.contact")%></th>
        						<td align="right">        						
			      					<html:select property="contract" styleClass="input" style="width:120;" >
										<html:options collection="contracts" property="value" labelProperty="label"/>
				  					</html:select>   
				  				</td>
      						</tr>
<% 					}
				}else {
%>							<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr  class="linetwo">
								<th align="left"><%=messages.getText("generic.driver.driverdata.manager")%></th>
							    <td align="right"><html:text property="manager" size="25" styleClass="input"/></td>
							</tr>
<%				}
				if(sys.getCorporationId() == CorporationData.ASTRAZENECA) {
%>		        <!--  Si es AZ uso authorizatewho como Cargo" -->             
	              <tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
	              <tr  class="linetwo">
	                <th align="left"> <%=messages.getText("generic.driver.driverdata.authorizatewho")%></th>
	                <td align="right"><html:text property="authorizateWho" size="25" styleClass="input"/></td>
	              </tr>
<%				}	
				if(sys.getCorporationId() == CorporationData.PEPSICO) {
%>
            <!--  Si es Pepsico uso OfficeAddess como Nombre de la persona que genera el vinculo
            	   del conductor a la empresa. "Nombre del Vínculo" -->             
              <tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
              <tr  class="linetwo">
                <th align="left"> <%=messages.getText("generic.driver.driverdata.officeaddress")%></th>
                <td align="right"><html:text property="officeAddress" size="25" styleClass="input"/></td>
              </tr>
              
              <!-- Quien autoriza la Alta y fecha en que fue solicitada -->
				<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
              <tr  class="linetwo">
                <th align="left"> <%=messages.getText("generic.driver.driverdata.authorizatewho")%></th>
                <td align="right"><html:text property="authorizateWho" size="25" styleClass="input"/>
                </td>
              </tr>   
              <tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
              <tr  class="linetwo">
                <th align="left" > <%=messages.getText("generic.driver.driverdata.authorizatedate")%></th>
                <td align="right">
                	<html:text property="authorizateDateDay" style="width:20;" styleClass="input" maxlength="2" styleId="dayId6"/>&nbsp;/&nbsp;
                 	<html:text property="authorizateDateMonth" style="width:20;" styleClass="input" maxlength="2" styleId="monthId6" />&nbsp;/&nbsp;
                  	<html:text property="authorizateDateYear" style="width:40;" styleClass="input" maxlength="4" styleId="yearId6" />
                    <html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button6" style="cursor: pointer; border: 1px solid red;"
     							onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>
			     	</td>
			     	<script type="text/javascript" >
									Calendar.setup({
        									inputArea     :    "yearId6",
        									ifFormat       :    "%d/%m/%Y",
        									showsTime      :    true,
        									timeFormat     :    "24",
        									button         :    "calendar_button6",
        									step           :    1,
        									onSelect       :    onSelect6,
        									singleClick    :    true
    									});
					</script>			          		
              </tr>
               <tr  class="linetwo">
        			<td align="right" colspan="2">
        				<%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
           			</td>
      			</tr>
<%		 	}
 %>     
 
<%			if(sys.getCorporationId() == CorporationData.MONSANTO || sys.getCorporationId() == CorporationData.BAYER) {
				Collection assignedVehicles = new LinkedList();
				iterC = dtReportFacade.findAll(DataTypeConstants.ASSIGNED_VEHICLE, sys.getUserSessionData()).iterator();
				while(iterC.hasNext()) {
					String value = ((AssignedVehicleDataType)iterC.next()).getResourceBundleId();
					assignedVehicles.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
				}
				request.setAttribute("assignedVehicles",assignedVehicles);
%>							<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr  class="linetwo">
								<th align="left"> <%=messages.getText("corporation.monsanto.assignedvehicles")%></th>
								<td align="right">
			      					<html:select property="assignedVehicle" styleClass="input" style="width:120;" >
										<html:options collection="assignedVehicles" property="value" labelProperty="label"/>
				  					</html:select>   
								</td>
							</tr>
<%			} 
			if (sys.isCorporation(new int[]{CorporationData.MONSANTO,CorporationData.ANGLO, CorporationData.BAYER_MX})){ %>
										<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr  class="linetwo">
											<th align="left"> <%=messages.getText("generic.driver.driverdata.iddallas")%></th>
											<td align="right"><html:text property="idDallas" size="25" styleClass="input"/></td>
										</tr>
<% 			} 
			if(sys.getUserSessionData().isForest() || sys.getCorporationId() == CorporationData.ASTRAZENECA){
				// bpsregistry -> Astrazeneca: Centro de costo del conductor	
%>							<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr class="linetwo">
								<th align="left"> <%=messages.getText("generic.driver.driverdata.bpsregistry")%></th>
								<td align="right"><html:text property="bpsregistry" size="25" styleClass="input"/></td>
							</tr>
<%			}		
			if(corporationId == CorporationData.BAYER) {
%>							<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr class="linetwo">
        						<th align="left"> <%=messages.getText("generic.driver.driverdata.bpsregistry")%></th>
        						<td align="right">
		        					<html:select property="bpsregistry" styleClass="input" style="width:120;">
		        						<html:option value="generic.messages.nodata"><%=messages.getText("generic.messages.nodata")%></html:option>
		        						<html:option value="generic.messages.yes"><%=messages.getText("generic.messages.yes")%></html:option>
		        						<html:option value="generic.messages.no"><%=messages.getText("generic.messages.no")%></html:option>
		        					</html:select>
		        				</td>
		      				</tr>
<%			}
			if(sys.getCorporationId() == CorporationData.LOSPIQUES || sys.getCorporationId() == CorporationData.PACIFICRUBIALES) {
%>							<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr class="linetwo">
        						<th align="left"> <%=messages.getText("generic.driver.driverdata.healthExpiry")%></th>
        						<td align="right" valign="middle">
           							<html:text property="healthExpiryDate" style="width:20;" styleClass="input" styleId="dayId5" maxlength="2"/>&nbsp;/&nbsp;
          							<html:text property="healthExpiryMonth" style="width:20;" styleClass="input" styleId="monthId5" maxlength="2"/>&nbsp;/&nbsp;
           							<html:text property="healthExpiryYear" style="width:40;" styleClass="input" styleId="yearId5" maxlength="4"  />
           							<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button5" style="cursor: pointer; border: 1px solid red;"
     								onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>
			          			</td>
			          			<script type="text/javascript">
									Calendar.setup({
        									inputArea     :    "yearId5",
        									ifFormat       :    "%d/%m/%Y",
        									showsTime      :    true,
        									timeFormat     :    "24",
        									button         :    "calendar_button5",
        									step           :    1,
        									onSelect       :    onSelect5,
        									singleClick    :    true
    									});
								</script>
      						</tr>
<%				}
			}
			if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
%>							<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr class="linetwo">
        						<th align="left"> <%=messages.getText("generic.driver.driverdata.director")%></th>
        						<td align="right">
        							<html:radio property="director" value="true"><%=messages.getText("generic.messages.yes")%></html:radio>
        							<html:radio property="director" value="false"><%=messages.getText("generic.messages.no")%></html:radio>
		        				</td>
		      				</tr>
<%			}
%>						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
    				</td>
    				<td width="16"></td>		
					<td valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.licenseinfo")%>' />
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
<%		if(sys.getCorporationId() == CorporationData.CIMSA) {
%>
	      					<tr class="linetwo">
	        					<th align="left" > <%=messages.getText("generic.driver.driverdata.externlicense")%></th>
	        					<td align="right">
	        						<html:select property="officeAddress" style="width:130;" styleClass="input">
	        							<html:option value="licensetype.general"><%=messages.getText("licensetype.general")%></html:option>
	        							<html:option value="licensetype.cardriver"><%=messages.getText("licensetype.cardriver")%></html:option>
	        							<html:option value="licensetype.motodriver"><%=messages.getText("licensetype.motodriver")%></html:option>
	        							<html:option value="licensetype.driver"><%=messages.getText("licensetype.driver")%></html:option>
	        						</html:select>
	        					</td>
	      					</tr>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
	      					<tr class="linetwo">
	      						<td colspan="2">
									<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.driver.internlicense")%>' />
										<table width="100%">
<%			for(int j = 1; j <= 7; j++) {
%>											<tr>
												<td><input type="checkbox" name='<%="option"+j%>' /><span class="input"><%=messages.getText("driver.internlicense.option"+j) %></span></td>		
											</tr>
<%			}
%>										</table>
									<jsp:include  page="../component/utilitybox2bottom.jsp" />
	      						</td>
	      					</tr>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			 				<tr class="linetwo">
			    				<th align="left"> <%=messages.getText("generic.driver.driverdata.healthExpiry")%></th>
			    				<td align="right" valign="middle">
			       					<html:text property="healthExpiryDate" style="width:20;" styleClass="input" styleId="dayId5" maxlength="2"/>&nbsp;/&nbsp;
			      					<html:text property="healthExpiryMonth" style="width:20;" styleClass="input" styleId="monthId5" maxlength="2" />&nbsp;/&nbsp;
			       					<html:text property="healthExpiryYear" style="width:40;" styleClass="input" styleId="yearId5" maxlength="4"/>
			       					<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button5" style="cursor: pointer; border: 1px solid red;"
			 							onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>
			         			</td>
			         			<script type="text/javascript">
								Calendar.setup({
			    						inputArea     :    "yearId5",
			    						ifFormat       :    "%d/%m/%Y",
			    						showsTime      :    true,
			    						timeFormat     :    "24",
			    						button         :    "calendar_button5",
			    						step           :    1,
			    						onSelect       :    onSelect5,
			    						singleClick    :    true
			    					});
								</script>
			      			</tr>
							<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%		}
%>
	      					<tr class="linetwo">
	        					<th align="left" > <%=messages.getText("generic.driver.driverdata.licensenumber")%></th>
	        					<td align="right"><html:text property="licenseNumber" style="width:130;" styleClass="input"/></td>
	      					</tr>
<%			if(!sys.isCorporation(new int[]{CorporationData.DOW,CorporationData.ALLERGAN})) {
%>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr  class="linetwo">
								<th align="left" > <%=messages.getText("generic.driver.driverdata.licenseget")%></th>
      							<td align="right" valign="middle">
								    <html:text property="licenseGetDate" style="width:20;" styleClass="input" styleId="dayId3" maxlength="2"/>&nbsp;/&nbsp;
								    <html:text property="licenseGetMonth" style="width:20;" styleClass="input" styleId="monthId3" maxlength="2" />&nbsp;/&nbsp;
								    <html:text property="licenseGetYear" style="width:35;" styleClass="input" styleId="yearId3"  maxlength="4"/>
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
<%		}
%>
      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
    						<tr  class="linetwo">
   								<th align="left" > <%=messages.getText("generic.driver.driverdata.licenseexpiry")%></th>
        						<td align="right" valign="middle">
	            					<html:text property="licenseExpiryDate" style="width:20;" styleClass="input" maxlength="2"/>&nbsp;/&nbsp;
								    <html:text property="licenseExpiryMonth" style="width:20;" styleClass="input" maxlength="2"/>&nbsp;/&nbsp;
								    <html:text property="licenseExpiryYear" style="width:35;" styleClass="input" maxlength="4"/>
			          			</td>
      						</tr>
<%		if(sys.getCorporationId() != CorporationData.ALLERGAN) {
%>						
              				<tr  class="linetwo">
                				<th align="left" > <%=messages.getText("user.expirytype.withexpiry")%></th>
                				<td align="right">
				              		<html:select property="licenseNotExpiry" onchange="javascript:nolicense(document.forms['DriverCreateForm']);" styleClass="input">
				              			<html:option value="0"><%=messages.getText("generic.messages.no")%></html:option>
				              			<html:option value="1"><%=messages.getText("generic.messages.yes")%></html:option>
				              		</html:select>
              					</td>
              				</tr>
<%		}
%>						
							<tr  class="linetwo">
			        			<td align="right" colspan="2">
			        				<%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
		            			</td>
			      			</tr>
<%			if(!sys.isCorporation(new int[]{CorporationData.DOW,CorporationData.CIMSA})) {
%>						
							<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr class="linetwo">
      							<th align="left"> <%=messages.getText("generic.driver.driverdata.licensecategory")%></th>
      							<td align="right"><html:text property="licenseCategory" style="width:130;" styleClass="input"/></td>
							</tr>
							
<%				if (sys.getCorporationId() == CorporationData.PEPSICO){ %>
							<!-- Entidad Federativa, PESPSICO -->
							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr  class="linetwo">
								<th align="left"> <%=messages.getText("generic.driver.driverdata.federativeentity")%></th>
								<td align="right"><html:text property="federativeEntity" size="25" styleClass="input"/></td>
							</tr>
							<!-- Cobertura de la Licencia, PEPSICO -->
							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr  class="linetwo">
								<th align="left"> <%=messages.getText("generic.driver.driverdata.licensecoverage")%></th>
								<td align="right">
									<html:select property="licenseCoverage" styleClass="input" style="width:120;" >
		                    				<html:options collection="licenseCoverageOptions" property="value" labelProperty="label" styleClass="input"/>
		                  			</html:select>
								</td>
							</tr>
<%				} 
				if(corporationId != CorporationData.ALLERGAN) {
%>	  						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr  class="linetwo">
      							<th align="left"> <%=messages.getText("generic.driver.driverdata.vehicleallowed")%></th>
      							<td align="right">
 	     							<html:select property="vehicleAllowed" styleClass="input" style="width:130;" >
      									<html:options collection="vehicleType" property="value" labelProperty="label" styleClass="input"/>
	  								</html:select>   
	  							</td>
							</tr>
<%				}
			}
			if(sys.isCorporation(CorporationData.MONSANTO)) {
%>	  						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
							<tr  class="linetwo">
								<th align="left"> <%=messages.getText("generic.driver.driverdata.licenseobs")%></th>
							</tr>
							<tr  class="linetwo">
								<td align="left" colspan="2">
									<html:textarea property="licenseObs" styleClass="input" cols="50" rows="5"></html:textarea>
								</td>
							</tr>
<%			}
%>						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
<%
      if(corporationId == CorporationData.BAYER) { 
%>						<br>		
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.hrd")%>' />
						<table width="100%" height="100%" cellpadding="0" cellspacing="0">
							<tr  class="linetwo">
								<th align="left"> <%=messages.getText("generic.driver.driverdata.nohrd")%></th>
								<td align="right"><html:checkbox property="noHrd"  styleClass="input"/></td>
							</tr>
							<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			 				<tr class="linetwo">
			     				<th align="left"> <%=messages.getText("generic.driver.driverdata.nohrdcomment")%></th>
			      				<td align="right"><html:text property="noHrdComment" size="25" styleClass="input"/></td>
			   				</tr>
						</table>	
			   			<jsp:include page="../component/utilityboxbottom.jsp"/>	
<%	 } %>
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
