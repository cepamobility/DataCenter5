<%@page language="java"
	import="business.*"
	import="communication.language.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.driver.*"
	import="communication.driver.tables.*"
	import="communication.util.*"
	import="communication.administration.corporation.tables.*"
    errorPage="../exception.jsp"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>
<%!
	Text messages; SystemAccess sys;
	RequestDispatcher dispatcher;
%> 
<%
	boolean extended = false;
	String action = "";
	String type = "";
	String formName = "";
	
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	Collection format = new LinkedList();
	format.add(new OptionItem("HTML","HTML"));	    
	format.add(new OptionItem("PDF","PDF"));	    
	format.add(new OptionItem("XLS","XLS"));	    
	session.setAttribute("format",format);

	Collection reportType = new LinkedList();
	reportType.add(new OptionItem(messages.getText("generic.vehicle.reportType.resumed"),"resumed"));	    
	reportType.add(new OptionItem(messages.getText("generic.vehicle.reportType.detailed"),"detailed"));	
	session.setAttribute("reportType",reportType);	
		
	Collection statusList = new LinkedList();
	statusList.add(new OptionItem(messages.getText("generic.messages.all.pl"),String.valueOf(DriverData.ALL)));
	statusList.add(new OptionItem(messages.getText("generic.messages.active"),String.valueOf(DriverData.ACTIVE)));
	statusList.add(new OptionItem(messages.getText("generic.messages.excluded"),String.valueOf(DriverData.EXCLUDED)));
	session.setAttribute("statusList",statusList);
%>
<html:html>
	<script>
		function changeCombo(radio,combo) {
			var el = document.forms[2].elements[radio];
			document.forms[2].elements[combo].disabled = !el[2].checked;
		}
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
	</script>
	<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
	</script>
	<head>
		<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	</head>
	<body bgcolor="#FFFFFF">
		<html:form action='<%=(extended) ? "/FOSADriverReport" : action%>' >			
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.driver.consulting")%>' />
  				</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
	      		<tr>
    	    		<td width="40%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.personaldata")%>' />
									
<%					if(extended) {
%>									<table class="SearchBack" width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr class="linetwo" >
											<th align="left"> <%=messages.getText("generic.driver.driverdata.id")%> </th>
				         					<td align="right"><html:text property="id" size="25" styleClass="input"/></td>
										</tr>
				          				<tr>
						        			<td class="linetwo" colspan="2">&nbsp;</td>
						        		</tr>
										<tr class="linetwo" >
				        					<th align="left"> <%=messages.getText("generic.driver.driverdata.firstname")%> </th>
				         					<td align="right"><html:text property="name" size="25" styleClass="input"/></td>
										</tr>
				          				<tr>
						        			<td class="linetwo" colspan="2">&nbsp;</td>
						        		</tr>
										<tr class="linetwo">
											<th align="left"> <%=messages.getText("generic.driver.driverdata.lastname")%> </th>
				         					<td align="right"><html:text property="lastname" size="25" styleClass="input"/></td>
										</tr>
				          				<tr>
						        			<td class="linetwo" colspan="2">&nbsp;</td>
						        		</tr>
										<tr class="linetwo" >
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
						        			<td align="left" colspan="2" class="linetwo">
						        				<%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
					            			</td>
						      			</tr>
				          				<tr>
						        			<td class="linetwo" colspan="2">&nbsp;</td>
						        		</tr>
										<tr class="linetwo">
				        					<th align="left"> <%=messages.getText("generic.driver.driverdata.status")%> </th>
				         					<td align="right">
											    <html:select property="status" styleClass="input" >
													<html:options collection="statusList" property="value" labelProperty="label"/>
												</html:select>							          				
											</td>
				    					</tr>
									</table>
<%				}else {
%>									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<th align="left">
												<span class="linetwo"><%=messages.getText("generic.driver.driverdata.firstorlastname")%></span>
											</th>
											<td>
												<html:text property="name" size="25" styleClass="input" />    
											</td>
										</tr>  
										<tr>
											<td colspan="2" align="left" class="texts">
												<table>
													<tr>
														<th align="left">
															<html:radio styleClass="input" property="status" value='<%=String.valueOf(DriverData.ACTIVE)%>'><span class="linetwo"><%=messages.getText("generic.messages.active")%></span></html:radio>
														</th>
														<th align="left">
															<html:radio styleClass="input" property="status" value='<%=String.valueOf(DriverData.EXCLUDED)%>'><span class="linetwo"><%=messages.getText("generic.messages.excluded")%></span></html:radio>
														</th>
														<th align="left">
															<html:radio styleClass="input" property="status" value='<%=String.valueOf(DriverData.ALL)%>'><span class="linetwo"><%=messages.getText("generic.messages.all.pl")%></span></html:radio>
														</th>
													</tr>
												</table>
											</td>
										</tr>
									</table>
<%				}
%>									<jsp:include  page="../component/utilityboxbottom.jsp" />
								</td>
							</tr>
				      		<tr>
								<jsp:include  page="../component/spaceline.jsp" />
			      			</tr>
			      			<tr>
				    			<td valign="top">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<jsp:include page='<%="/util/divgeo"+String.valueOf(sys.getUserSessionData().getFilter().getGeoLevelCount())+type+".jsp?form="+formName+"&consult=true"%>' />
									</table>
								</td>
							</tr>
						</table>
					</td>
    				<td width="16">&nbsp;</td>
    				<td width="40%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<jsp:include page='<%="/util/divorg"+type+".jsp?form="+formName+"&consult=true"%>' />
						</table>
					</td>
  				</tr>
	      		<tr>
    	    		<td width="40%" valign="top" colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.event.driver.extradata")%>' />
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td valign="top">
									<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.driver.accident.pl")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr class="linethree">
											<td>
			        							<html:radio property="accident" value='<%=String.valueOf(DriverReportForm.ALL)%>' styleClass="input" onclick="javascript:changeCombo('accident','accidentCount')"><%=messages.getText("driver.consult.accident.all")%></html:radio>
											</td>
										</tr>
										<tr class="linethree">
											<td>
			        							<html:radio property="accident" value='<%=String.valueOf(DriverReportForm.WITH)%>' styleClass="input" onclick="javascript:changeCombo('accident','accidentCount')"><%=messages.getText("driver.consult.accident.with")%></html:radio>
											</td>
										</tr>
										<tr class="linethree">
											<td>
			        							<html:radio property="accident" value='<%=String.valueOf(DriverReportForm.AT_LEAST)%>' styleClass="input" onclick="javascript:changeCombo('accident','accidentCount')"><%=messages.getText("driver.consult.accident.atleast")%></html:radio>&nbsp;
											    <html:select property="accidentCount" styleClass="input" disabled="true">
													<html:options collection="count" property="value" labelProperty="label"/>
												</html:select>							          				
											</td>
										</tr>
										<tr class="linethree">
											<td>
			        							<html:radio property="accident" value='<%=String.valueOf(DriverReportForm.NONE)%>' styleClass="input" onclick="javascript:changeCombo('accident','accidentCount')"><%=messages.getText("driver.consult.accident.none")%></html:radio>
											</td>
										</tr>
									</table>
									<jsp:include  page="../component/utilitybox2bottom.jsp" />
								</td>
			    				<td width="6">&nbsp;</td>
								<td valign="top">
									<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.driver.incident.pl")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr class="linethree">
											<td>
			        							<html:radio property="incident" value='<%=String.valueOf(DriverReportForm.ALL)%>' styleClass="input" onclick="javascript:changeCombo('incident','incidentCount')"><%=messages.getText("driver.consult.incident.all")%></html:radio>
											</td>
										</tr>
										<tr class="linethree">
											<td>
			        							<html:radio property="incident" value='<%=String.valueOf(DriverReportForm.WITH)%>' styleClass="input" onclick="javascript:changeCombo('incident','incidentCount')"><%=messages.getText("driver.consult.incident.with")%></html:radio>
											</td>
										</tr>
										<tr class="linethree">
											<td>
			        							<html:radio property="incident" value='<%=String.valueOf(DriverReportForm.AT_LEAST)%>' styleClass="input" onclick="javascript:changeCombo('incident','incidentCount')"><%=messages.getText("driver.consult.incident.atleast")%></html:radio>&nbsp;
											    <html:select property="incidentCount" styleClass="input" disabled="true">
													<html:options collection="count" property="value" labelProperty="label"/>
												</html:select>							          				
											</td>
										</tr>
										<tr class="linethree">
											<td>
			        							<html:radio property="incident" value='<%=String.valueOf(DriverReportForm.NONE)%>' styleClass="input" onclick="javascript:changeCombo('incident','incidentCount')"><%=messages.getText("driver.consult.incident.none")%></html:radio>
											</td>
										</tr>
									</table>
									<jsp:include  page="../component/utilitybox2bottom.jsp" />
								</td>
			    				<td width="6">&nbsp;</td>
								<td valign="top">
									<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.driver.fine.pl")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr class="linethree">
											<td>
			        							<html:radio property="fine" value='<%=String.valueOf(DriverReportForm.ALL)%>' styleClass="input" onclick="javascript:changeCombo('fine','fineCount')"><%=messages.getText("driver.consult.fine.all")%></html:radio>
											</td>
										</tr>
										<tr class="linethree">
											<td>
			        							<html:radio property="fine" value='<%=String.valueOf(DriverReportForm.WITH)%>' styleClass="input" onclick="javascript:changeCombo('fine','fineCount')"><%=messages.getText("driver.consult.fine.with")%></html:radio>
											</td>
										</tr>
										<tr class="linethree">
											<td>
			        							<html:radio property="fine" value='<%=String.valueOf(DriverReportForm.AT_LEAST)%>' styleClass="input" onclick="javascript:changeCombo('fine','fineCount')"><%=messages.getText("driver.consult.fine.atleast")%></html:radio>&nbsp;
											    <html:select property="fineCount" styleClass="input" disabled="true">
													<html:options collection="count" property="value" labelProperty="label"/>
												</html:select>							          				
											</td>
										</tr>
										<tr class="linethree">
											<td>
			        							<html:radio property="fine" value='<%=String.valueOf(DriverReportForm.NONE)%>' styleClass="input" onclick="javascript:changeCombo('fine','fineCount')"><%=messages.getText("driver.consult.fine.none")%></html:radio>
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
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
<%		if(sys.getUserSessionData().isForest() && sys.getUserSessionData().getFilter().getCorporationId().intValue() != CorporationData.KLABIN) {
%>				<tr>
    	    		<td width="40%" valign="top" colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.otherdata")%>' />
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td valign="top" width="48%">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr class="linethree">
			        						<th align="left"> <%=messages.getText("generic.driver.driverdata.bpsregistry")%></th>
			   	    						<td align="right">
												<html:select property="bps" styleClass="input">
													<html:option value="all"><%=messages.getText("generic.messages.all.pl")%></html:option>
													<html:option value="yes"><%=messages.getText("generic.messages.yes")%></html:option>
													<html:option value="no"><%=messages.getText("generic.messages.no")%></html:option>
												</html:select>
			   	    						</td>
										</tr>
							      		<tr>
											<jsp:include  page="../component/spaceline.jsp" />
						      			</tr>
										<tr>
											<td valign="top" colspan="2">
												<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.driver.aptitudtest")%>' />
												<table width="100%" border="0" cellpadding="0" cellspacing="0">
													<tr class="linethree">
						        						<th align="left"> <%=messages.getText("driver.aptitudtest.status")%></th>
						   	    						<td align="right">
															<html:select property="aptitudStatus" styleClass="input">
																<html:option value='<%=String.valueOf(DriverData.ALL)%>'><%=messages.getText("generic.messages.all.pl")%></html:option>
																<html:option value='<%=String.valueOf(DriverData.EXPIRED)%>'><%=messages.getText("driver.aptitudtest.expired")%></html:option>
																<html:option value='<%=String.valueOf(DriverData.NOT_EXPIRED)%>'><%=messages.getText("driver.aptitudtest.notexpired")%></html:option>
															</html:select>
						   	    						</td>
													</tr>
										      		<tr>
														<jsp:include  page="../component/spaceline.jsp" />
									      			</tr>
													<tr class="linethree">
						        						<th align="left"> <%=messages.getText("driver.aptitudtest.result")%></th>
						   	    						<td align="right">
															<html:select property="aptitudResult" styleClass="input">
																<html:option value='<%=String.valueOf(DriverData.ALL)%>'><%=messages.getText("generic.messages.all.pl")%></html:option>
																<html:option value='<%=String.valueOf(DriverData.APT)%>'><%=messages.getText("driver.aptitudtest.apts")%></html:option>
																<html:option value='<%=String.valueOf(DriverData.NOT_APT)%>'><%=messages.getText("driver.aptitudtest.notapts")%></html:option>
															</html:select>
						   	    						</td>
													</tr>
												</table>
												<jsp:include  page="../component/utilitybox2bottom.jsp" />
											</td>
										</tr>
									</table>
								</td>
								<td valign="top" width="6">&nbsp;</td>								
								<td valign="top" width="48%">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr class="linethree">
											<td valign="top" colspan="2">
												<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("driver.modify.alcoholcontrol")%>' />
												<table width="100%" border="0" cellpadding="0" cellspacing="0">
													<tr class="linethree">
						        						<th align="left"> <%=messages.getText("driver.aptitudtest.status")%></th>
						   	    						<td align="right">
															<html:select property="alcoholStatus" styleClass="input">
																<html:option value='<%=String.valueOf(DriverData.ALL)%>'><%=messages.getText("generic.messages.all.pl")%></html:option>
																<html:option value='<%=String.valueOf(DriverData.EXPIRED)%>'><%=messages.getText("driver.alcoholcontrol.expired")%></html:option>
																<html:option value='<%=String.valueOf(DriverData.NOT_EXPIRED)%>'><%=messages.getText("driver.alcoholcontrol.notexpired")%></html:option>
															</html:select>
						   	    						</td>
													</tr>
										      		<tr>
														<jsp:include  page="../component/spaceline.jsp" />
									      			</tr>
													<tr class="linethree">
						        						<th align="left"> <%=messages.getText("driver.aptitudtest.result")%></th>
						   	    						<td align="right">
															<html:select property="alcoholResult" styleClass="input">
																<html:option value='<%=String.valueOf(DriverData.ALL)%>'><%=messages.getText("generic.messages.all.pl")%></html:option>
																<html:option value='<%=String.valueOf(DriverData.POSITIVE)%>'><%=messages.getText("generic.messages.positive")%></html:option>
																<html:option value='<%=String.valueOf(DriverData.NEGATIVE)%>'><%=messages.getText("generic.messages.negative")%></html:option>
															</html:select>
						   	    						</td>
													</tr>
										      		<tr>
														<jsp:include  page="../component/spaceline.jsp" />
									      			</tr>
													<tr class="linethree">
						        						<th align="left"> <%=messages.getText("driver.alcoholcontro.history")%></th>
						   	    						<td align="right">
															<html:select property="postiveAlcohol" styleClass="input">
																<html:option value='<%=String.valueOf(DriverData.ALL)%>'><%=messages.getText("generic.messages.all.pl")%></html:option>
																<html:option value='<%=String.valueOf(DriverData.ANY_POSITIVE)%>'><%=messages.getText("driver.alcoholconotrol.withpositive")%></html:option>
																<html:option value='<%=String.valueOf(DriverData.WITHOUT_POSITIVE)%>'><%=messages.getText("driver.alcoholconotrol.withoutpositive")%></html:option>
															</html:select>
						   	    						</td>
													</tr>
												</table>
												<jsp:include  page="../component/utilitybox2bottom.jsp" />
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
<%		}
%>				<tr>
					<td align="left" class="texts" colspan="3">
						<jsp:include  page="../component/utilitybox4top.jsp" />
							<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
								<tr class="lineone">
									<td align="left" class="lineone">
										<span class="input"><b><%=messages.getText("generic.messages.output")%></b></span>&nbsp;
									</td>
									<td align="left" class="lineone">
										<html:select property="output" styleClass="input" >
											<html:options collection="format" property="value" labelProperty="label"/>
										</html:select>							          				
									</td>
									<td align="left" class="lineone" width="90%">&nbsp;</td>
								</tr>
								<tr class="lineone">
									<td align="left" class="lineone">
										<span class="input"><b><%=messages.getText("generic.messages.type")%></b></span>&nbsp;
									</td>
									<td align="left" class="lineone">
										<html:select property="type" styleClass="input">
											<html:options collection="reportType" property="value" labelProperty="label"/>
										</html:select>							          				
									</td>
									<td align="right"  class="lineone" width="90%">
										<html:submit styleClass="input" value='<%=messages.getText("generic.messages.consult")%>' />
									</td>
								</tr>
							</table>
						<jsp:include  page="../component/utilitybox4bottom.jsp" />
					</td>
				</tr>
			</table>
		</html:form>
		<%@ include  file = '../util/jsp/googleanalytics.jsp' %>
	</body>
</html:html>
