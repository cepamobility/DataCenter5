 <%@page language="java"
	errorPage="../exception.jsp" 
	import="java.util.*"
	import="business.SystemAccess"
	import="business.alert.*"
	import="business.datatype.*"
	import="business.administration.user.*"
	import="communication.datatype.*"
	import="communication.alert.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.datatype.tables.*"
	import="communication.datatype.*"
	import="communication.administration.user.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.util.ListResult"
 	import="presentation.translator.web.util.*"	
 	import="presentation.translator.web.alert.*"
 	import="communication.administration.user.*" 	
	import="communication.administration.user.UserSessionData" 
	
 %>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>
<%! RequestDispatcher dispatcher; String context;String pathView; 
	String pathMsg; String pathSearch; 
	Text messages; String pathLanguage;String action;
	SystemAccess sys;  AlertForm alertForm;
	UserSessionData userSessionData;
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData = sys.getUserSessionData();

	if(sys == null){
		response.sendRedirect("/exception.jsp");
	}
	
	String check = null;
	check = Util.checkPermission(sys,SecurityConstants.ALERT_VIEW);
	
	if(check != null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		
		messages = sys.getText();

		pathLanguage="/util/jsp/language.jsp";
	

		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
	
		context=request.getContextPath();
		pathMsg="/util/jsp/message.jsp";
	
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);
		
		String alertType= AlertConstants.ALERTTYPE_CEPA_TIPS;
		String attachFile = "";
		// String type="create";
		// if(request.getParameter("type")!=null){
		//    type = request.getParameter("type");
		// }
		
		String alertId = request.getParameter("alertId");
		if (alertId == null){
			alertId = (String)session.getAttribute("alertId");
		}
		
		if (alertId != null && Util.isNumber(alertId)){
			Integer alertIdNumber = Integer.valueOf(alertId);
			AlertData alertData = sys.getAlertReportFacade().
				findAlertByPrimaryKey(alertIdNumber,sys.getUserSessionData());
			session.removeAttribute("AlertForm");
			if (alertData != null){
				alertForm = new AlertForm(alertData);
				attachFile = alertData.getAttachment();
			}else{
				alertForm = new AlertForm();
			}
		}else{
			session.removeAttribute("AlertForm");
			alertForm = new AlertForm();			
		}
		session.setAttribute("AlertForm",alertForm);
		
		session.setAttribute("target","AlertCepaTipsEdit");
		pathView="./AlertCepaTipsEdit.jsp";
		session.setAttribute("url","alert/AlertCepaTipsEdit.jsp");
		
	  	action="/AlertCreateAction";
		
	  	DataTypeUtil dataTypeUtil = new DataTypeUtil(sys);
		session.setAttribute("frecuency",dataTypeUtil.getDataType(DataTypeConstants.ALERT_FRECUENCY,true,false,false));
		session.setAttribute("type",dataTypeUtil.getDataType(DataTypeConstants.ALERT_TYPE,true,false,false));
		
		String alertDay = "1";
		if (sys.getCorporationId() == CorporationData.BAYER){
			 alertDay = "20";
		}
		
		// Listado de Años, actual a actual + 5
		Collection years = new LinkedList();
	    years.add(new OptionItem("",""));
	    Calendar c = Calendar.getInstance();
	    int actualYear = c.get(Calendar.YEAR);
		for(int i = actualYear; i <= actualYear + 5; i++) {
			years.add(new OptionItem(String.valueOf(i),String.valueOf(i)));
		}
		request.setAttribute("years",years);
		
		// Listado de Meses
		Collection months = new LinkedList();
	    months.add(new OptionItem("",""));
		for(int i = 1; i <= 12; i++) { 
		    months.add(new OptionItem(messages.getText("generic.month."+String.valueOf(i)),String.valueOf(i)));
		}
		request.setAttribute("months",months);
		
		// Directorio donde se almacenara los adjuntos
		CorporationDirectoryData cdd = sys.getCorporationReportFacade().
			findCorporationDirectoryByPrimaryKey(sys.getCorporationId(),sys.getUserSessionData());
		
		String corporation = ""; // Nombre de la carpeta donde estan los adjuntos
		
		if (cdd == null || Util.empty(cdd.getDirectory())){
			corporation = "cepa";
		}else{
			corporation = cdd.getDirectory();
		}
		
		String directory = SecurityConstants.DATACENTER5_WAR_DIRECTORY + corporation + "/alerts";
		String linkDirectory = SecurityConstants.DATACENTER5_LINK_DIRECTORY  + corporation + "/alerts";

		
		
%>
<head>
<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<script>
	function test(){
		document.forms['AlertForm'].test.value = 'TEST';
		document.forms['AlertForm'].submit()
	}
	function save(){
		document.forms['AlertForm'].test.value = '';
		document.forms['AlertForm'].submit();
	}
	
	function deleteIFConfirm(form,msg){
		form.submit();
	}
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

  	
</script>
</head>
<body bgcolor="#FFFFFF">

	<form action="layout.jsp?refer=/alert/alertList.jsp" name='cancel' method="post">
		<input type="hidden" name="target" value="alertView" />
		
	</form>	
	
	
	<html:form action='<%=action%>' >
	<table width="500px" align='center' cellpadding="0" cellspacing="0">
		<tr class="guide1">
			<jsp:include  page='<%="../component/titlecomponent.jsp?colspan=5&title="+messages.getText("web.alert.view")%>' />
		</tr>
   		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>  		  			
		<tr>
			<td valign="top" width="100%" >
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.alert.data")%>' />	
						<table width="100%"  border="0" cellpadding="0" cellspacing="0">
							<tr class="linetwo">
								<td width="41%" align="left">
									<%=messages.getText("generic.messages.initialdate")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%>
								</td>
								
								<% if (sys.getCorporationId() == CorporationData.BAYER){
											// Bayer solo elige mes/año y se fija fecha a 20	
									%>
								<td width="59%" align="right">
									<html:hidden property="startDate" value="20" /><%=alertDay %>/		            				
		            				<html:select property="startMonth" styleClass="input" style="width:60;" styleId="monthId1">
                  								<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
                					</html:select>/                					
		            				<html:select property="startYear" styleClass="input" styleId="yearId1" style="width:60;">
                  							<html:options collection="years" property="value" labelProperty="label" styleClass="input"/>
                					</html:select>
		            				
			          			</td>
								<% }else{ %>
								<td width="59%"  align="right">
									<html:text property="startDate" style="width:20;" styleClass="input" styleId="dayId1"/>/
		            				<html:text property="startMonth" style="width:20;" styleClass="input" styleId="monthId1"/>/
		            				<html:text property="startYear" style="width:40;" styleClass="input" styleId="yearId1"/>
		            				<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button1" style="cursor: pointer; border: 1px solid red;"
     										onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>
			          			</td>
          						<script type="text/javascript">
									Calendar.setup({
										inputArea     :    "yearId1",
      									ifFormat       :    "%d/%m/%Y",
      									showsTime      :    false,
      									timeFormat     :    "24",
      									button         :    "calendar_button1",
      									step           :    1,
      									onSelect       :    onSelect1,
      									date:	.....................................<- 20 del mes corriente o 20 sel siguiete si hoy en mayor que 20.
      									singleClick    :    true
    								});
								</script>
								
								<% } // fin if bayer%>
	    					</tr>   					
	    					
				    	  	<tr>
								<jsp:include  page="../component/spaceline.jsp" /> 
					      	</tr>
					      	
					      	<!-- Tipo de Alerta -->
					      	<tr class="linetwo">
								<td align="left">
									<%=messages.getText("generic.alert.alerttype")%>
								</td>
								<td align="right">
									<html:hidden property="alertType" value="<%=alertType%>" />
									<%=messages.getText(alertType)%>
									<!-- 								
									<html:select property="alertType" styleClass="input" style="width:155;">
                  						<html:options collection="type" property="value" labelProperty="label" styleClass="input"/>
                					</html:select>
									-->										
    					  	    </td>																									
							</tr>
							
							<tr>
								<jsp:include  page="../component/spaceline.jsp" /> 
					      	</tr>
					      	
					      	<!-- Estado de la alerta -->
					      	
							<tr class="linetwo">
								<td align="left">
									<%=messages.getText("generic.alert.alertstate")%>
									<input type="hidden" name="test" value="" />
								</td>
								<td align="right">
									<% if (alertForm != null && alertForm.getAlertId() != null &&
											alertForm.getTest() != null &&
											alertForm.getTest().equalsIgnoreCase("TEST")){ 
											// Test, nextdate = 2100-01-01
									%>									
										<%=messages.getText("generic.alert.alertstate.test")%>										
									<% }else if (alertForm != null && alertForm.getAlertId() != null &&
											alertForm.getTest() == null &&
											(alertForm.getEnd() == null || 
													alertForm.getEnd().compareTo(
															Calendar.getInstance().getTime()) >= 0 )){
											// Alerta Activda, no finalizada
									%>
										<%=messages.getText("generic.alert.alertstate.active")%>
									
									<% }else  if (alertForm != null && alertForm.getAlertId() != null &&
											alertForm.getTest() == null &&
											alertForm.getEnd() != null && 
												alertForm.getEnd().compareTo(Calendar.getInstance().getTime()) < 0 ){
										// Finalizada, no activa
									%>
										<%=messages.getText("generic.alert.alertstate.finalized")%>
									<% }else{ 
											// Alerta nueva
									%>
										<%=messages.getText("generic.alert.alertstate.new")%>
									<% } %>
								</td>																									
							</tr>		
				    	  	<tr>
								<jsp:include  page="../component/spaceline.jsp" /> 
					      	</tr>
					      	
					      	
					      	<!-- Periodicidad, UNICA VEZ para CEPA Tips -->
					      	<!-- 
							<tr class="texts">
								<td align="left">
									<%=messages.getText("generic.alert.alertperiod")%>
								</td>
								<td align="right">
									<%=messages.getText("generic.alert.frecuency.na") %>
									<html:hidden property="frecuency" value="generic.alert.frecuency.na" />
								</td>																									
							</tr>		
				    	  	<tr>
								<jsp:include  page="../component/spaceline.jsp" /> 
					      	</tr>
					      	 -->
					      	
					      	<!-- Frecuencia en dias, no aplica para CEPA TIps
							<tr class="texts">
								<td align="left">
									<%=messages.getText("generic.alert.alertfrecuency")%>
								</td>
								<td align="right">
								<html:text property="dailyFrecuency" style="width:20;" styleClass="input" styleId="dailyFrecuency"/>							
    					  	    </td>																									
							</tr>
							 -->
							 
						</table>
						<jsp:include page="../component/utilityboxbottom.jsp"/>
						
			</td>
			</tr>
			
			<tr>
				<jsp:include  page="../component/spaceline.jsp" /> 
	      	</tr>
			
			<tr>
				<td width="100%" valign="top">
				<table width="100%" height="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top" width="48%">	
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.alert.data.mail")%>' />
							<table width="100%"  cellpadding="0" cellspacing="0" >
								<tr class="linetwo">
									<td class="linetwo">
										<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.alert.search.subject")%>' />
											<table width="100%" cellpadding="0" cellspacing="0">
												<tr>
											  	 <td width="100%" align="center">
												 	<html:textarea property="subject" cols="60" rows="1" ></html:textarea>
											   	</td>
												</tr>
												<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
											</table>
											<jsp:include  page="../component/utilitybox2bottom.jsp" />
				      				</td>
				      			</tr>
				      			
				      			<!--  body -->
								<tr class="space"><td>&nbsp;</td></tr>
								<tr class="linetwo">
									<td class="linetwo">
										<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.alert.search.body")%>' />
											<!-- <div id="scrollalert2"> -->
												<table width="100%" cellpadding="0" cellspacing="0">
													<tr>
											  	 		<td  align="center">
												 			<html:textarea property="body" cols="60" rows="10" ></html:textarea>
											   			</td>
													</tr>													
												</table>
				      						<!-- </div> -->
											<jsp:include  page="../component/utilitybox2bottom.jsp" />
				      				</td>
				      			</tr>
				      			
				      			<!--  Attachments -->
				      			<tr class="space"><td>&nbsp;</td></tr>
								<tr class="linetwo">
									<td class="linetwo">
										<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.alert.attachments")%>' />
											<table width="100%" cellpadding="0" cellspacing="0">
													<tr><td><%=attachFile %></td></tr>
													<tr>
														<!--  
														<th align="left">
						      								<%=messages.getText("generic.supportmaterial.reports.file")%>:
						      							</th>
						      							-->
						      							<td align="left">
						      								<html:file property="file" />
						      							</td>	
													</tr>
											</table>				      						
											<jsp:include  page="../component/utilitybox2bottom.jsp" />
				      				</td>
				      			</tr>
				      			
				      			<!--  Destinatarios -->
				      			      			
				      			
								<tr class="space"><td>&nbsp;</td></tr>
								<tr class="linetwo">
									<td class="linetwo">
										<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.message.sendemail")%>' />
										<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
											<tr>
												<td>
													<%=" - "+messages.getText("alert.sendto.activedrivers") %>
												</td>
											</tr>
											<tr>
												<td>
													<%=" - "+messages.getText("alert.sendto.otheremails") %>
												</td>
											</tr>
											<tr class="linetwo">
												<td style="width:130;" align="left" class="lineone"><%=messages.getText("alert.count.email")%></td>
													<td align="left" class="linetwo">
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
		  										<td class="linetwo" colspan="5">
		  											<div id="divemail1">
		  											<table class="linetwo">
		  												<tr>
															<th align="left" class="linetwo"> <%=messages.getText("alert.message.email")+" 1"%></th>
				       	 									<td align="right" class="linetwo"><html:text property="email1" style="width:200;" styleClass="input" styleId="email1"/></td>
														</tr>
		      											<tr>
															<jsp:include  page="../component/spaceline.jsp" />
		  												</tr>
		  											</table>
		  											</div>
		  											<div id="divemail2" style="display:none">
		  											<table class="linetwo">
		  	       										<tr>
															<th align="left" class="linetwo"> <%=messages.getText("alert.message.email")+" 2"%></th>
				     	   									<td align="right" class="linetwo"><html:text property="email2" style="width:200;" styleClass="input" styleId="email2"/></td>
														</tr>
														<tr>
															<jsp:include  page="../component/spaceline.jsp" />
		  												</tr>
		  											</table>
		  											</div>
		  											<div id="divemail3" style="display:none">
		  											<table class="linetwo">
		  												<tr>
															<th align="left" class="linetwo"> <%=messages.getText("alert.message.email")+" 3"%></th>
				     										<td align="right" class="linetwo"><html:text property="email3" style="width:200;" styleClass="input" styleId="email3"/></td>
														</tr>
														<tr>
															<jsp:include  page="../component/spaceline.jsp" />
		  												</tr>
		  											</table>
		  											</div>
		  											<div id="divemail4" style="display:none">
		  											<table class="linetwo">
		  												<tr>
															<th align="left" class="linetwo"> <%=messages.getText("alert.message.email")+" 4"%></th>
				        									<td align="right" class="linetwo"><html:text property="email4" style="width:200;" styleClass="input" styleId="email4"/></td>
														</tr>
														<tr>
															<jsp:include  page="../component/spaceline.jsp" />
		  												</tr>
		  											</table>
		  											</div>
		  											<div id="divemail5" style="display:none">
		  											<table class="linetwo">
		  												<tr>
															<th align="left" class="linetwo"> <%=messages.getText("alert.message.email")+" 5"%></th>
				       	 									<td align="right" class="linetwo"><html:text property="email5" style="width:200;" styleClass="input" styleId="email5"/></td>
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
							<jsp:include page="../component/utilityboxbottom.jsp"/>
						</td>
					</tr>
				</table>
				
			</td>
								
		</tr>
		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
  		
  		<tr>
			<td width="100%" valign="top" colspan="5">
				<div align="right">
				<!--  Cancelar -->
					<input name="button1" type="button"  
            			value="<%=messages.getText("generic.messages.back")%>" 
            			onClick="javascript:document.forms['cancel'].submit()" 
            			class="input" align="right"/>
				<!--  Test, envia email de prueba -->
				 	<input name='button2' type='button'  
				 	    title='<%=messages.getText("generic.messages.testmessage")%>'
            			value='<%=messages.getText("generic.messages.test")%>' 
            			onClick='javascript:test();' 
            			class='input' align='right'/>
				<!--  Guardar -->
            		<input name="button3" type="button"  
            			value='<%=messages.getText("generic.messages.save")%>' 
            			onClick='javascript:save();' 
            			class="input" align="right"/>
          		</div>
          	</td>
		</tr>
					
	</table>
	
	<br />
	<br />
	</html:form>	
<%		
	}
%>
</body>
</html:html>