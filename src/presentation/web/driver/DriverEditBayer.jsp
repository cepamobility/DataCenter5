<%@page language="java"
	errorPage="../exception.jsp"
	import="business.*"
	import="business.administration.user.*"
	import="business.driver.*"
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.datatype.*"
	import="communication.datatype.tables.*"
	import="communication.corporation.tables.*"
	import="communication.driver.tables.*"
	import="communication.administration.user.tables.*"
	import="communication.administration.user.*"
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

<%! RequestDispatcher dispatcher; 
	Text messages; 
	SystemAccess sys; 
	DriverData data; 
	Integer id;
	String licenseNotExpiry = "off";
%>

<html:html>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData = sys.getUserSessionData();
	String check = null;
	if(userSessionData.isDriver() 
			&& (userSessionData.getDriverData().getCountryName().equals(CountryData.ARGENTINA)) 
		|| sys.getCorporationId() == CorporationData.BAYER) {
	    check = Util.checkPermission(sys,SecurityConstants.DRIVER_MODIFY_OWN_DATA);
	}else {
	    check = Util.checkPermission(sys,SecurityConstants.EDIT_DRIVER);
	}
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
		
		messages=sys.getText();
		
	
		String idStr = (String)request.getParameter("id");
		if(idStr == null){
			id = new Integer((String)session.getAttribute("id"));
			session.removeAttribute("id");
		}else {
			id = new Integer(idStr);
		}
		
		if(id!=null){
			// session.setAttribute("url","driver/DriverEdit.jsp?id="+String.valueOf(id));
		
			data = (userSessionData.isDriver()) ? userSessionData.getDriverData() : sys.getDriverReportFacade().findDriverByPrimaryKey(id, userSessionData);
			if(data!=null){
				DriverForm driverForm;
			    if(session.getAttribute("DriverCreateForm") == null) {
					driverForm = DriverUtil.createDriverForm(data,DriverUtil.EDIT,messages);
					session.setAttribute("DriverCreateForm",driverForm);					
			    }else{
			    	driverForm = (DriverForm)session.getAttribute("DriverCreateForm");
			    }
			    licenseNotExpiry = driverForm.getLicenseNotExpiry();
			    
				DataTypeReportFacade dtReportFacade = sys.getDataTypeReportFacade();			
				
				Iterator iter = dtReportFacade.findAll(DataTypeConstants.SEX, userSessionData).iterator();
				Collection sexs = new LinkedList();
				while(iter.hasNext()) {
					String value = ((SexDataType)iter.next()).getResourceBundleId();
					sexs.add(new OptionItem(messages.getText(value),value));
				}
				// session.setAttribute("sexs",sexs);
				request.setAttribute("sexs",sexs);

				Collection allCountries=sys.getCorporationReportFacade().findAllCountries(userSessionData);
				Collection countries = new LinkedList();
				Iterator it = allCountries.iterator();
				countries.add(new OptionItem(messages.getText("generic.messages.nodata"),""));
				while(it.hasNext()) {
					CountryData item = (CountryData)it.next();
					countries.add(new OptionItem(messages.getText(item.getResourceKey()),item.getResourceKey()));
				}    
				//session.setAttribute("countries",countries);
				request.setAttribute("countries",countries);

				request.setAttribute("vehicleType",new DataTypeUtil(sys).getVehicleType(true,false,true));
				
				Collection contractType = new LinkedList();
				Iterator iterC = dtReportFacade.findAll(DataTypeConstants.CONTRACT, userSessionData).iterator();
				contractType.add(new OptionItem(messages.getText("generic.messages.nodata"),""));
				while(iterC.hasNext()) {
					String value = ((ContractDataType)iterC.next()).getResourceBundleId();
					contractType.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
				}
				request.setAttribute("contracts",contractType);
				
%>

<LINK REL=StyleSheet HREF="./util/css/estilo.css" TYPE="text/css">

<head/>

<script language='javascript' src='./menu/js/driverFunctions.js' type='text/javascript' >
</script>


<body bgcolor="#FFFFFF">
  <html:form action="/Driverupdate">
  
<script language="javascript" type="text/javascript" >
function licenseNotExpiryChange(){
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
</script>

    <table width="700" align='center' cellpadding="0" cellspacing="0">
      <tr class="guide1">
        <jsp:include  page='<%="../component/titlecomponent.jsp?colspan=5&title="+messages.getText("web.driver.editing")+"						"+data.getLastName()+","+data.getFirstName()%>' />    
      </tr>
      <tr>
        <jsp:include  page="../component/spaceline.jsp" />    
      </tr>
      <tr>
        <td width="49%">
          <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
            <tr>
              <td valign="top"><jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.personaldata")%>' />    
                  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
                    <tr class="linetwo">
                      <th align="left"> <%=messages.getText("generic.driver.driverdata.firstname")%><span class="required">*</span></th>
                      <td align="right"><html:text property="firstName" size="25" styleClass="input"/></td>
                    </tr>
                    <tr  class="space">
                      <th align="right">&nbsp;</th>
                      <td align="right">&nbsp;</td>
                    </tr>
                    <tr  class="linetwo">
                      <th align="left"><%=messages.getText("generic.driver.driverdata.lastname")%><span class="required">*</span></th>
                      <td align="right"><html:text property="lastName" size="25" styleClass="input"/></td>
                    </tr>
                    <tr  class="space">
                      <th align="right">&nbsp;</th>
                      <td align="right">&nbsp;</td>
                    </tr>
                    <tr  class="linetwo">
                      <th align="left"> <%=messages.getText("generic.driver.driverdata.birthdate")%><span class="required">*</span></th>
                      <td align="right"><html:text property="birthDate" style="width:20;" styleClass="input"/>
                        &nbsp;/&nbsp;
                        <html:text property="birthMonth" style="width:20;" styleClass="input"/>
                        &nbsp;/&nbsp;
                        <html:text property="birthYear" style="width:40;" styleClass="input"/>
                      </td>
                    </tr>
                    <tr  class="linetwo">
	        			<td align="right" colspan="2">
	        				<%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
            			</td>
	      			</tr>
                    <tr  class="space">
                      <th align="right">&nbsp;</th>
                      <td align="right">&nbsp;</td>
                    </tr>
                    <tr  class="linetwo">
                      <th align="left" > <%=messages.getText("generic.driver.driverdata.sex")%><span class="required">*</span></th>
                      <td align="right">
                      	<html:select property="sex" styleClass="input" style="width:120;">
                          <html:options collection="sexs" property="value" labelProperty="label" styleClass="input"/>
                        </html:select>
                      </td>
                    </tr>
                    <tr  class="space">
                      <th align="right">&nbsp;</th>
                      <td align="right">&nbsp;</td>
                    </tr>
                    <tr  class="linetwo">
                      <th align="left"> <%=messages.getText("generic.driver.driverdata.nationality")%> </th>
                      <td align="right">
                      	<html:select property="nationality" styleClass="input" style="width:120;" >
                          <html:options collection="countries" property="value" labelProperty="label" styleClass="input"/>
                        </html:select>
                      </td>
                    </tr>
                    <tr  class="space">
                      <th align="right">&nbsp;</th>
                      <td align="right">&nbsp;</td>
                    </tr>
                    <tr  class="linetwo">
                      <th align="left"> <%=messages.getText("generic.driver.driverdata.city")%></th>
                      <td align="right"><html:text property="city" size="25" styleClass="input"/></td>
                    </tr>
                    <tr  class="space">
                      <th align="right">&nbsp;</th>
                      <td align="right">&nbsp;</td>
                    </tr>
                    <tr  class="linetwo">
                      <th align="left"> <%=messages.getText("generic.driver.driverdata.personaladdress")%></th>
                      <td align="left"><div align="right">
                          <html:text property="personalAddress" size="25" styleClass="input"/>
                      </div></td>
                    </tr>
                  </table>
                <jsp:include page="../component/utilityboxbottom.jsp"/>      
              </td>
            </tr>
            <tr>
              <jsp:include  page="../component/spaceline.jsp" />    
            </tr>
            <tr>
              <td>
              	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr  class="linetwo">
                  	<td>
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.divgeo")%>' />
<%				if(data.getCountry() != null) {
	    			GeoItemData geoItem1 = (GeoItemData)data.getCountry().getItemIterator().next();
%>
							<table width="100%" height="100%" cellpadding="0" cellspacing="0">
								<tr class="linetwo">
									<th align="left"> <%=messages.getText(data.getCountry().getResourceKey())%> </th>
									<td align="right">
										<%=(geoItem1.getSubItems() != null) ? geoItem1.getName() : messages.getText(geoItem1.getName()) %>
									</td>
								</tr>
<%			    	if(geoItem1.getSubItems() != null) {
				    	GeoItemData geoItem2 = (GeoItemData)geoItem1.getSubItems().getItemIterator().next();
%>									
								<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
								<tr class="linetwo">
									<th align="left"> <%=messages.getText(geoItem1.getSubItems().getResourceKey())%> </th>
									<td align="right">
										<%=(geoItem2.getSubItems() != null) ? geoItem2.getName() : messages.getText(geoItem2.getName()) %>
									</td>
								</tr>
						
<%
				    	if(geoItem2.getSubItems() != null) {
					    	GeoItemData geoItem3 = (GeoItemData)geoItem2.getSubItems().getItemIterator().next();
%>								<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
								<tr class="linetwo">
									<th align="left"> <%=messages.getText(geoItem2.getSubItems().getResourceKey())%> </th>
									<td align="right">
										<%=messages.getText(geoItem3.getName())%>
									</td>
								</tr>
<%						}
					}
				}
%>							</table>	
		   				<jsp:include page="../component/utilityboxbottom.jsp"/>
		   			</td>		
                  </tr>
              </table></td>
            </tr>
          </table>
        </td>
        <td width="2%" valign="top">&nbsp;</td>
        <td width='49%' valign="top">
        	<table width="100%" align='center' cellpadding="0" cellspacing="0">
<%			
				it = data.getDivOrgsIterator();
				while(it.hasNext()) {		
					DivOrgData divOrgData = (DivOrgData)it.next();
					if(divOrgData.getDivOrgLevels() != null) {
						ItemData item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
%>					<tr>
						<td valign="top" width="50%">	
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText(divOrgData.getName())%>' />
							<table width="100%"  cellpadding="0" cellspacing="0">
								<tr class="linetwo">
									<th align="left"> <%=messages.getText(divOrgData.getDivOrgLevels().getName())%> </th>
									<td align="right"><%=item1.getName()%></td>
								</tr>
<%	    		
			    		if(item1.getSubItems() != null) {
							ItemData item2 = (ItemData)item1.getSubItems().getItemIterator().next();
%>								<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
								<tr class="linetwo">
									<th align="left"> <%=messages.getText(item1.getSubItems().getName())%> </th>
									<td align="right"><%=item2.getName()%></td>
								</tr>
<%				    
				    		if(item2.getSubItems() != null) {
								ItemData item3 = (ItemData)item2.getSubItems().getItemIterator().next();
%>								<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
								<tr class="linetwo">
									<th align="left"> <%=messages.getText(item2.getSubItems().getName())%> </th>
									<td align="right"><%=item3.getName()%></td>
								</tr>
<%							}
						}
%>
							</table>
   							<jsp:include page="../component/utilityboxbottom.jsp"/>	
	   					</td>
	   				</tr>
	   				<tr><td height="5"><img src="x" width="1" height="1"></tr>
	   	<%					}
				}
%>
        	</table>
        </td>
      </tr>
      <tr>
        <jsp:include  page="../component/spaceline.jsp" />    
      </tr>
      <tr>
        <td valign="top"><jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.workinfo")%>' />    
            <table width="100%" align='center' cellpadding="0" cellspacing="0">
<%			if(sys.getCorporationId() == CorporationData.CIMSA) {
%>				<html:hidden property="points" value='<%=String.valueOf(data.getPoints())%>'/>
	      		<tr class="linetwo">
	        		<th align="left" > <%=messages.getText("generic.driver.driverdata.points")%></th>
	        		<td align="right">
	        				<%
	        					int eventsPoints = sys.getEventReportFacade().getEventsPoints(data.getId(),sys.getUserSessionData()).intValue();
	        				%>
	        				<%=(data.getPoints() == null) ? "--" : 
	        						String.valueOf(data.getPoints().intValue() - eventsPoints)%>	        		
	        		</td>
	      		</tr>
      			<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%			}
%>              <tr  class="linetwo">
                <th align="left" > <%=messages.getText("generic.driver.driverdata.corporationid")%></th>
                <td align="right"><html:text property="corporationId" size="25" styleClass="input"/></td>
              </tr>
              <tr  class="space">
                <th align="right">&nbsp;</th>
                <td align="right">&nbsp;</td>
              </tr>
              <tr  class="linetwo">
                <th align="left" > <%=messages.getText("generic.driver.driverdata.startdate")%></th>
                <td align="right"><html:text property="startDate" style="width:20;" styleClass="input"/>
                  &nbsp;/&nbsp;
                  <html:text property="startMonth" style="width:20;" styleClass="input"/>
                  &nbsp;/&nbsp;
                  <html:text property="startYear" style="width:40;" styleClass="input"/>
                </td>
              </tr>
              <tr  class="linetwo">
	        			<td align="right" colspan="2">
	        				<%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
            			</td>
	      			</tr>
<%			if(sys.getCorporationId() != CorporationData.CIMSA) {
%>              <tr  class="space">
                <th align="right">&nbsp;</th>
                <td align="right">&nbsp;</td>
              </tr>
              <tr  class="linetwo">
                <th align="left" > <%=messages.getText("generic.driver.driverdata.email")%></th>
                <td align="right"><html:text property="email" size="25" styleClass="input"/></td>
              </tr>
              <tr  class="space">
                <th align="right">&nbsp;</th>
                <td align="right">&nbsp;</td>
              </tr>
              <tr  class="linetwo">
                <th align="left" > <%=messages.getText("generic.driver.driverdata.phonenumber")%></th>
                <td align="right"><html:text property="phoneNumber" size="25" styleClass="input"/></td>
              </tr>
              <tr  class="space">
                <th align="right">&nbsp;</th>
                <td align="right">&nbsp;</td>
              </tr>
              <tr  class="linetwo">
                <th align="left"> <%=messages.getText("generic.driver.driverdata.officeaddress")%></th>
                <td align="right"><html:text property="officeAddress" size="25" styleClass="input"/></td>
              </tr>
<%		 	}
%>          	<tr class="space">
                <th align="right">&nbsp;</th>
                <td align="right">&nbsp;</td>
              </tr>
              <tr class="linetwo">
                <th align="left"> <%=messages.getText("generic.driver.driverdata.drives")%></th>
                <td align="right"><html:radio property="drives" value="true"><%=messages.getText("generic.messages.yes")%></html:radio>
                    <html:radio property="drives" value="false"><%=messages.getText("generic.messages.no")%></html:radio>
                </td>
              </tr>
              <tr class="space">
                <th align="right">&nbsp;</th>
                <td align="right">&nbsp;</td>
              </tr>
              <tr class="linetwo">
                <th align="left"> <%=messages.getText("generic.driver.driverdata.employee")%></th>
                <td align="right"><html:radio property="employee" value="true"><%=messages.getText("generic.messages.yes")%></html:radio>
                    <html:radio property="employee" value="false"><%=messages.getText("generic.messages.no")%></html:radio>
                </td>
              </tr>
              <tr class="space">
                <th align="right">&nbsp;</th>
                <td align="right">&nbsp;</td>
              </tr>
              <tr class="linetwo">
                <th align="left"> <%=messages.getText("generic.driver.driverdata.contact")%></th>
                <td align="right"><html:select property="contract" styleClass="input" style="width:120;" >
                    <html:options collection="contracts" property="value" labelProperty="label"/>
                  </html:select>
                </td>
              </tr>
<%		if(userSessionData.isForest()) {
%>							<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr class="linetwo">
        						<th align="left"> <%=messages.getText("generic.driver.driverdata.bpsregistry")%></th>
        						<td align="right"><html:text property="bpsregistry" size="25" styleClass="input"/></td>
      						</tr>
<%		}
		if(sys.getCorporationId() == CorporationData.LOSPIQUES || sys.getCorporationId() == CorporationData.PACIFICRUBIALES) {
%>							<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      						<tr class="linetwo">
        						<th align="left"> <%=messages.getText("generic.driver.driverdata.healthExpiry")%></th>
        						<td align="right" valign="middle">
           							<html:text property="healthExpiryDate" style="width:20;" styleClass="input" styleId="dayId5" />&nbsp;/&nbsp;
          							<html:text property="healthExpiryMonth" style="width:20;" styleClass="input" styleId="monthId5"/>&nbsp;/&nbsp;
           							<html:text property="healthExpiryYear" style="width:40;" styleClass="input" styleId="yearId5"/>
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
<%		}
%>            </table>
          <jsp:include  page="../component/utilityboxbottom.jsp" />      
        </td>
        <td >&nbsp;</td>
        <td valign="top">
        	<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.licenseinfo")%>' />    
            <table width="100%" valign="top" align='center' cellpadding="0" cellspacing="0">
<%		if(sys.getCorporationId() == CorporationData.CIMSA) {
%>		  		<tr class="linetwo">
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
<%			String checks = driverForm.getLicenseCategory();
			for(int j = 1; j <= 7; j++) {				
%>							<tr>
								<td><input type="checkbox" name='<%="option"+j%>' <%=(checks.charAt(j-1) == '1') ? "checked" : "" %> /><span class="input"><%=messages.getText("driver.internlicense.option"+j) %></span></td>		
							</tr>
<%			}
%>						</table>
						<jsp:include  page="../component/utilitybox2bottom.jsp" />
	      			</td>
	      		</tr>
      			<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
 				<tr class="linetwo">
    				<th align="left"> <%=messages.getText("generic.driver.driverdata.healthExpiry")%></th>
    				<td align="right" valign="middle">
       					<html:text property="healthExpiryDate" style="width:20;" styleClass="input" styleId="dayId5" />&nbsp;/&nbsp;
      					<html:text property="healthExpiryMonth" style="width:20;" styleClass="input" styleId="monthId5"/>&nbsp;/&nbsp;
       					<html:text property="healthExpiryYear" style="width:40;" styleClass="input" styleId="yearId5"/>
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
              <tr  class="linetwo">
                <th align="left" > <%=messages.getText("generic.driver.driverdata.licensenumber")%></th>
                <td align="right"><html:text property="licenseNumber" size="25" styleClass="input"/></td>
              </tr>
              <tr  class="space">
                <th align="right">&nbsp;</th>
                <td align="right">&nbsp;</td>
              </tr>
              <tr  class="linetwo">
                <th align="left" > <%=messages.getText("generic.driver.driverdata.licenseget")%></th>
                <td align="right">
                	<html:text property="licenseGetDate" style="width:20;" maxlength="2" styleClass="input"/>
                  	&nbsp;/&nbsp;
                  	<html:text property="licenseGetMonth" style="width:20;" maxlength="2" styleClass="input"/>
                  	&nbsp;/&nbsp;
                  	<html:text property="licenseGetYear" style="width:40;" maxlength="4" styleClass="input"/>
                </td>
              </tr>
              <tr  class="linetwo">
	        			<td align="right" colspan="2">
	        				<%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
            			</td>
	      			</tr>
              <tr  class="space">
                <th align="right">&nbsp;</th>
                <td align="right">&nbsp;</td>
              </tr>
              <tr  class="linetwo">
                <th align="left" > <%=messages.getText("generic.driver.driverdata.licenseexpiry")%> </th>
                <% if (licenseNotExpiry.equalsIgnoreCase("on")){ %>
	                <td align="right">
	                	<html:text property="licenseExpiryDate" style="width:20;" maxlength="2" styleClass="input" styleId="dayId4" disabled="true" />
	                  	&nbsp;/&nbsp;
	                  	<html:text property="licenseExpiryMonth" style="width:20;" maxlength="2" styleClass="input" styleId="monthId4" disabled="true" />
	                  	&nbsp;/&nbsp;
	                  	<html:text property="licenseExpiryYear" style="width:40;" maxlength="4" styleClass="input" styleId="yearId4" disabled="true" />
	                </td>
	            <% }else{ %>
	            	<td align="right">
	                	<html:text property="licenseExpiryDate" style="width:20;" maxlength="2" styleClass="input" styleId="dayId4" />
	                  	&nbsp;/&nbsp;
	                  	<html:text property="licenseExpiryMonth" style="width:20;" maxlength="2" styleClass="input" styleId="monthId4"  />
	                  	&nbsp;/&nbsp;
	                  	<html:text property="licenseExpiryYear" style="width:40;" maxlength="4" styleClass="input" styleId="yearId4" />
	                </td>
	            <% } %>
              </tr>
              <tr  class="linetwo">
       				<td align="right" colspan="2">
       					<%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
          			</td>
    		  </tr>
              <tr  class="linetwo">
                <th align="left" > <%=messages.getText("generic.driver.driverdata.licensenotexpiry")%></th>
                <td align="right">
              	<html:checkbox property="licenseNotExpiry" styleClass="input" styleId="licensenotexpiry" onclick="javascript:licenseNotExpiryChange();"/>
              	</td>
              </tr>
<%		if(sys.getCorporationId() != CorporationData.CIMSA) {
%>            <tr  class="space">
                <th align="right">&nbsp;</th>

                <td align="right">&nbsp;</td>
              </tr>
              <tr  class="linetwo">
                <th align="left"> <%=messages.getText("generic.driver.driverdata.licensecategory")%></th>
                <td align="right"><html:text property="licenseCategory" size="25" styleClass="input"/></td>
              </tr>
              <tr  class="space">
                <th align="right">&nbsp;</th>
                <td align="right">&nbsp;</td>
              </tr>
              <tr  class="linetwo">
                <th align="left"> <%=messages.getText("generic.driver.driverdata.vehicleallowed")%></th>
                <td align="right"><html:select property="vehicleAllowed" styleClass="input" style="width:120;" >
                    <html:options collection="vehicleType" property="value" labelProperty="label" styleClass="input"/>
                  </html:select>
                </td>
              </tr>
<%		}
%>          </table>
          <jsp:include  page="../component/utilityboxbottom.jsp" />      
        </td>
      </tr>
      <tr>
        <jsp:include  page="../component/spaceline.jsp" />    
      </tr>
      <tr>
        <td width="100%" colspan="3">
		<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.comment")%>' />    
            <table width="100%" cellpadding="0" cellspacing="0">
              <tr>
                <td><center> <html:textarea property="comment" cols="100" /></center>
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
				<div align="right">
					<html:submit styleClass="input"><%=messages.getText("generic.messages.save")%></html:submit>
					<html:cancel styleClass="input"><%=messages.getText("generic.messages.cancel")%></html:cancel >
				</div>
			</td>
		</tr>
    </table>
</html:form>
<%			}else {
%>		<jsp:include  page='<%="/util/allowedData.jsp?message=driver.error.access.driver" %>'/>
<%			}
		}
%>
</body>
<%	}	%>
</html:html>
