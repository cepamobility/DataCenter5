<%@page language="java"
	errorPage="../exception.jsp"
	import="business.*"
	import="business.administration.user.*"
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.driver.tables.*"
	import="communication.datatype.tables.*"
	import="communication.datatype.*"
	import="communication.corporation.tables.*"
	import="communication.geografic.tables.*"
	import="communication.administration.user.tables.*"
	import="communication.security.*"
	import="communication.util.*"
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

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>

<%!
	Text messages;
	SystemAccess sys;
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	messages=sys.getText();

	// session.setAttribute("target","DriverList");
	DriverAdvancedSearchForm form = (DriverAdvancedSearchForm)session.getAttribute("DriverAdvancedSearchForm");

	Collection statusList = new LinkedList();
	statusList.add(new OptionItem(messages.getText("generic.messages.active"),String.valueOf(DriverData.ACTIVE)));
	statusList.add(new OptionItem(messages.getText("generic.messages.excluded"),String.valueOf(DriverData.EXCLUDED)));
	statusList.add(new OptionItem(messages.getText("generic.messages.all.pl"),String.valueOf(DriverData.ALL)));
	request.setAttribute("statusList",statusList);
	
	Collection paginationList = new LinkedList();
	paginationList.add(new OptionItem("10","10"));
	paginationList.add(new OptionItem("20","20"));
	paginationList.add(new OptionItem("30","30"));
	paginationList.add(new OptionItem("40","40"));
	paginationList.add(new OptionItem("50","50"));
	
	request.setAttribute("paginationList",paginationList);
	
	boolean isTemporal =  (request.getParameter("temporal") == null) ? false : 
		Boolean.parseBoolean(request.getParameter("temporal"));
	
%>
	
	<script language="JavaScript" src="../util/js/select_component.js" type="text/javascript">
	</script>
	<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
	</script>
	<script>
	function onSelectDriver(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayIdDriver");
  		var input_month = document.getElementById("monthIdDriver");
  		var input_year = document.getElementById("yearIdDriver");
  		var date3 = new Date(time);
  		input_day.value = date3.print("%d");
  		input_month.value = date3.print("%m");
  		input_year.value= parseInt(date3.print("%Y"),10);
  	}
  	function accept(id,name) {
		self.opener.document.forms['<%=form.getForm()%>'].elements['<%=form.getDriverId()%>'].value = id;
		self.opener.document.forms['<%=form.getForm()%>'].elements['<%=form.getName()%>'].value = name;
		window.close();
	}
	function doSubmit(all,page) {
		document.forms['DriverAdvancedSearchForm'].all.value = all;
		document.forms['DriverAdvancedSearchForm'].first.value = page;		
		document.forms['DriverAdvancedSearchForm'].submit();
	}
	</script>
	<html:form action="/Driveradvancedsearch">
		<html:hidden property="all"/>
		<html:hidden property='temporal' value='<%= (isTemporal)?"true":"false" %>'  />
		<html:hidden property="pageNum"/>
		<!-- <html:hidden property="count"/> -->
	  	<body>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	  			<tr>
	    			<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr class="guideSearch">
								<td>
									<img src="/datacenter/util/imgs/left_top_search.jpg" width="19" height="30" align="bottom">
								</td>
								<td align="left" class="guideSearch">
									<%=messages.getText("generic.driver.personaldata")%>							
								</td>
								<td>
									<img src="/datacenter/util/imgs/right_top_search.jpg" width="19" height="30" align="bottom">
								</td>
							</tr>
	  						<tr>
	    						<td class="leftLineSearch">&nbsp;</td>
	    						<td>
									<table class="SearchBack"  width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<th align="left"> <%=messages.getText("generic.driver.driverdata.id")%> </th>
				         					<td align="right"><html:text property="id" size="25" styleClass="input"/></td>
										</tr>
				          				<tr>
						        			<td  colspan="2">&nbsp;</td>
						        		</tr>
										<tr  >
				        					<th align="left"> <%=messages.getText("generic.driver.driverdata.firstname")%> </th>
				         					<td align="right"><html:text property="firstName" size="25" styleClass="input"/></td>
										</tr>
				          				<tr>
						        			<td  colspan="2">&nbsp;</td>
						        		</tr>
										<tr >
											<th align="left"> <%=messages.getText("generic.driver.driverdata.lastname")%> </th>
				         					<td align="right"><html:text property="lastName" size="25" styleClass="input"/></td>
										</tr>
				          				<tr>
						        			<td  colspan="2">&nbsp;</td>
						        		</tr>
										<tr  >
				        					<th align="left"> <%=messages.getText("generic.driver.driverdata.birthdate")%> </th>
				         					<td align="right" valign="middle">
								            	<html:text property="birthDate" style="width:18;" styleClass="input" styleId="dayIdDriver"/>&nbsp;/&nbsp;
									            <html:text property="birthMonth" style="width:18;" styleClass="input" styleId="monthIdDriver"/>&nbsp;/&nbsp;
									            <html:text property="birthYear" style="width:35;" styleClass="input" styleId="yearIdDriver"/>													
				         						<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_buttonDriver" style="cursor: pointer; border: 1px solid red;"
	     										onmouseover="this.style.background='red';" style="width:20;" onmouseout="this.style.background=''"/>
				         					</td>
											<script type="text/javascript">
													Calendar.setup({
	        											inputArea     :    "yearIdDriver",
	        											ifFormat       :    "%d/%m/%Y",
	        											showsTime      :    true,
	        											timeFormat     :    "24",
	        											button         :    "calendar_buttonDriver",
	        											step           :    1,
	        											onSelect       :    onSelectDriver,
	        											singleClick    :    true
	    											});
												</script>
				          				</tr>
				          				<tr>
						        			<td align="left" colspan="2" >
						        				<%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
					            			</td>
						      			</tr>
				          				<tr>
						        			<td colspan="2">&nbsp;</td>
						        		</tr>
										<tr >
				        					<th align="left"> <%=messages.getText("generic.driver.driverdata.status")%> </th>
				         					<td align="right">
											    <html:select property="status" styleClass="input" >
													<html:options collection="statusList" property="value" labelProperty="label"/>
												</html:select>							          				
											</td>
				    					</tr>
				          				<tr>
						        			<td colspan="2">&nbsp;</td>
						        		</tr>
										<tr>
		    								<td width="41%" align="left" class="texts">
												<%=messages.getText("generic.driver.driverdata.temporal")%>										
											</td>
		    								<td width="59%" colspan="2" align="right">
		    									<html:select property="temp" styleClass="input">
		    										<html:option value="0"><%=messages.getText("generic.messages.all.pl")%> </html:option>
		    										<html:option value="1"><%=messages.getText("driver.driverdata.onlytemporal")%> </html:option>
		    										<html:option value="2"><%=messages.getText("driver.driverdata.onlynotemporal")%> </html:option>
		    									</html:select>
		  									</td>
										</tr>
										<tr>
						        			<td  colspan="2">&nbsp;</td>
						        		</tr>
				    					<tr >
				        					<th align="left"> <%=messages.getText("generic.driver.driverdata.pagination")%> </th>
				         					<td align="right">
											    <html:select property="count" styleClass="input" >
													<html:options collection="paginationList" property="value" labelProperty="label"/>
												</html:select>							          				
											</td>
				    					</tr>
									</table>
								</td>
				  				<td class="rightLineSearch">&nbsp;</td>
							</tr>	
			  				<tr>
		            	      	<td height="11" valign="top"><img src="/datacenter/util/imgs/left_base_search.jpg" width="19" height="11"></td>
		  					  	<td class="centerBaseSearch"></td>
		  				  		<td valign="top" height="11"><img src="/datacenter/util/imgs/right_base_search.jpg" width="19" height="11"></td>
						 	</tr>
				  			<tr>
						       	<td height="10" colspan="3"><img src="x" width="1" height="1"></td>
						 	</tr>
			  				<tr>
			    				<td colspan="3" valign="top">
									<table  width="100%" border="0" cellpadding="0" cellspacing="0">
										<jsp:include page='<%="/util/divgeo2.jsp?form=DriverAdvancedSearchForm&format=search" %>'/>
									</table>									
								</td>	
							</tr>
						</table>
					</td>
    				<td width="2%">&nbsp;</td>
    				<td width="49%" valign="top">
						<table class="SearchBack"  width="100%" border="0" cellpadding="0" cellspacing="0">
							<jsp:include page='<%="/util/divorg.jsp?form=DriverAdvancedSearchForm&format=search" %>'/>
						</table>
					</td>
  				</tr>
  				<tr>
    				<td colspan="2">&nbsp;</td>
	  			</tr>
	  			
	  			
	  			
	  			<!-- Listado de Drivers: -->
  				<tr>
    				<td colspan="3" align="right">
						<html:submit value='<%=messages.getText("generic.messages.search")%>' styleClass="input" />
					</td>
  				</tr>
			</table>
			<html:hidden property="form" />
			<html:hidden property="driverId" />
			<html:hidden property="name" />
	  	</body>
	</html:form>
</html:html>